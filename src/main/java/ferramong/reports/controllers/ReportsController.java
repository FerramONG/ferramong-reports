package ferramong.reports.controllers;

import ferramong.reports.entities.Purchases;
import ferramong.reports.models.Payment;
import ferramong.reports.services.ReportService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(
        origins = CorsConfiguration.ALL,
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
public class ReportsController {

    @Autowired
    private ReportService reportService;

    /**
     * Generates ONG purchases report within a data range.
     *
     * <h2>CURL example</h2>
     * <code>
     *      curl "https://ferramong-reports.herokuapp.com/purchases/ong/2021-03-27/2021-03-28"
     * </code>
     *
     * @param       start Start range
     * @param       end End range
     *
     * @return      Purchases in the interval [start; end]
     */
    @GetMapping("/purchases/ong/{start}/{end}")
    public ResponseEntity<List<Payment>> getAllOngPurchases(@PathVariable("start")
                                                            @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
                                                            @PathVariable("end")
                                                            @DateTimeFormat(pattern="yyyy-MM-dd") Date end) {
        List<Payment> purchases = Purchases.getAllOngToolsPurchases(start, end);

        return ResponseEntity.ok().body(purchases);
    }

    /**
     * Generates dweller purchases report.
     *
     * <h2>CURL example</h2>
     * <code>
     *      curl "https://ferramong-reports.herokuapp.com/purchases/dweller/1"
     * </code>
     *
     * @param       idDweller Dweller id
     *
     * @return      PDF report
     */
    @GetMapping("/purchases/dweller/{id_dweller}")
    public ResponseEntity<List<Payment>> getAllDwellerPurchases(@PathVariable("id_dweller") int idDweller) {
        List<Payment> purchases = Purchases.getAllDwellerToolsPurchases(idDweller);

        return ResponseEntity.ok().body(purchases);
    }

    @ResponseBody
    @GetMapping(value="/sales/report/{start}/{end}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void generateOngReport(@PathVariable("start")
                                @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
                                @PathVariable("end")
                                @DateTimeFormat(pattern="yyyy-MM-dd") Date end,
                               HttpServletResponse response) throws IOException, JRException {


        byte[] pdf = reportService.exportReportSales(start,end);
        streamReport(response, pdf, "vendas.pdf");
    }

    protected void streamReport(HttpServletResponse response, byte[] data, String name)
            throws IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "inline; filename=" + name);
        response.setContentLength(data.length);

        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }

    @ResponseBody
    @GetMapping(value="/purchases/report/{id_dweller}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void generateDwellerReport(@PathVariable("id_dweller") int idDweller,
                                        HttpServletResponse response) throws IOException, JRException {
        byte[] pdf = reportService.exportReportPurchases(idDweller);
        streamReport(response, pdf, "historico.pdf");
    }
}
