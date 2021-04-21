package ferramong.reports.services;

//import ferramong.reports.repositories.ReportsRepository;

import ferramong.reports.entities.Purchases;
import ferramong.reports.models.Payment;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

/*
 * Service
 *      Terá a lógica principal
 *      Não tem a ver com modelagem de dados
 *      Não faz comunicação direta com bd (quem faz é 'repositories')
 *		Não é um estado (por outro lado, 'models' são)
 *		Não modela dados (quem faz isso são os 'models')
 */

@Service
@AllArgsConstructor
public class ReportService {

    public String exportReportSales(Date start, Date end) throws FileNotFoundException, JRException {
        //Load file and compile it
        String path = "src/main/resources/relatorio";
        //Payment[] payments={};
        List<Payment> payments;
        //payments = Arrays.asList(new Payment());
                //Purchases.getAllOngToolsPurchases(start, end);
        payments=Purchases.getAllOngCreditoolsPurchases(start,end);
        File file= ResourceUtils.getFile("classpath:vendas-ong.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(payments);
        Map<String, Object> parameters = new HashMap<>();
        //parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\vendas.pdf");

        return "report generated in path : " + path;

    }

    public String exportReportPurchases(int id_dweller) throws FileNotFoundException, JRException {
        //Load file and compile it
        String path = "src/main/resources/relatorio";
        //Payment[] payments={};
        List<Payment> payments;
        //payments = Arrays.asList(new Payment());
        //Purchases.getAllOngToolsPurchases(start, end);
        payments=Purchases.getAllDwellerCreditoolsPurchases(id_dweller);
        File file= ResourceUtils.getFile("classpath:vendas-ong.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(payments);
        Map<String, Object> parameters = new HashMap<>();
        //parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\compras.pdf");

        return "report generated in path : " + path;

    }





}
