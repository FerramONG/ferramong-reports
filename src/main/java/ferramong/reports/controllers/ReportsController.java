package ferramong.reports.controllers;

import ferramong.reports.entities.Tool;
import ferramong.reports.services.ReportsService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/*
* Controller
*   Deve ter minimo de logica
*	Serve para chamar serviços
*	Mapeia endpoints
* */

@RestController
@AllArgsConstructor
public class ReportsController {

    /**
     * Gets ONG purchases within a date range.
     *
     * <h2>CURL example</h2>
     * <code>
     *      curl "https://ferramong-pay.herokuapp.com/purchases/ong/2021-03-27/2021-03-28"
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
        return ResponseEntity.ok().body(paymentService.getAllOngPurchases(start, end));
    }

    /**
     * Gets dweller purchases.
     *
     * <h2>CURL example</h2>
     * <code>
     *      curl "https://ferramong-pay.herokuapp.com/purchases/dweller/1"
     * </code>
     *
     * @param       idDweller Dweller id
     *
     * @return      Dweller purchases
     */
    @GetMapping("/purchases/dweller/{id_dweller}")
    public ResponseEntity<List<Payment>> getAllDwellerPurchases(@PathVariable("id_dweller") int idDweller) {
        return ResponseEntity.ok().body(paymentService.getAllDwellerPurchases(idDweller));
    }
}
