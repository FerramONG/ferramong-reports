package ferramong.reports.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DwellerHistory implements Serializable {

    private int id;

    private Date date;

    private double total; //valor pago pelo cliente

    private double credit; //valor a ser convertido pelo total

    private double balance; //saldo

}