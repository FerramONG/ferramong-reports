package ferramong.reports.controllers;

import ferramong.reports.entities.Purchases;
import ferramong.reports.entities.Tool;
import ferramong.reports.models.Payment;
import ferramong.reports.services.ReportsService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(
        origins = CorsConfiguration.ALL,
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
public class ReportsController {

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
        List<Payment> purchases = Purchases.getAllOngPurchases(start, end);

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
        List<Payment> purchases = Purchases.getAllDwellerPurchases(idDweller);

        return ResponseEntity.ok().body(purchases);
    }
}
