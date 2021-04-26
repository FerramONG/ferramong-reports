package ferramong.reports.entities;

import ferramong.reports.models.DwellerHistory;
import ferramong.reports.models.Payment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Purchases {

    private int idDweller;

    private Purchases() {
    }

    private Purchases(int idDweller) {
        this.idDweller = idDweller;
    }

    public static List<Payment> getAllOngToolsPurchases(Date start, Date end) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<List<Payment>> responseEntity = rest.exchange(
                "https://ferramong-pay.herokuapp.com/purchases/tools/ong/" + df.format(start) + "/" + df.format(end),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Payment>>() {
                }
        );
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();

        return responseEntity.getBody();
    }

    public static List<Payment> getAllOngCreditoolsPurchases(Date start, Date end) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<List<Payment>> responseEntity = rest.exchange(
                "https://ferramong-pay.herokuapp.com/purchases/creditools/ong/" + df.format(start) + "/" + df.format(end),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Payment>>() {
                }
        );
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();

        return responseEntity.getBody();
    }

    public static List<Payment> getAllDwellerToolsPurchases(int idDweller) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<List<Payment>> responseEntity = rest.exchange(
                "https://ferramong-pay.herokuapp.com/purchases/tools/dweller/" + idDweller,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Payment>>() {
                }
        );
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();

        return responseEntity.getBody();
    }

    public static List<DwellerHistory> getAllDwellerCreditoolsPurchases(int idDweller) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<List<DwellerHistory>> responseEntity = rest.exchange(
                "https://ferramong-pay.herokuapp.com/purchases/creditools/dweller/" + idDweller,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<DwellerHistory>>() {
                }
        );
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();

        return responseEntity.getBody();
    }

    public static double getBalance(int idDweller) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange(
                "https://ferramong-creditools.herokuapp.com/wallet/dweller/" + String.valueOf(idDweller),
                HttpMethod.GET,
                requestEntity,
                String.class
        );
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();

        return Double.parseDouble(responseEntity.getBody());
    }
}
