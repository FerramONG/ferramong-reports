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
public class Payment implements Serializable {

    private int id;

    private int id_dweller;

    private Date date;

    private double total;

    private String type;    // credit, debit, money, creditools
}
