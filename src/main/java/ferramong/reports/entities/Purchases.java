package ferramong.reports.entities;

import ferramong.reports.models.Payment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Purchases {

    private Purchases() {
    }

    public static List<Payment> getAllOngToolsPurchases(Date start, Date end) {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

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
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

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

    public static List<Payment> getAllDwellerCreditoolsPurchases(int idDweller) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<List<Payment>> responseEntity = rest.exchange(
                "https://ferramong-pay.herokuapp.com/purchases/creditools/dweller/" + idDweller,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Payment>>() {
                }
        );
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();

        return responseEntity.getBody();
    }
}
