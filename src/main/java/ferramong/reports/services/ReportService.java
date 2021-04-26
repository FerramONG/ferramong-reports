package ferramong.reports.services;

//import ferramong.reports.repositories.ReportsRepository;

import ferramong.reports.entities.Purchases;
import ferramong.reports.models.DwellerHistory;
import ferramong.reports.models.Payment;
//import ferramong.reports.models.Purchase;
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

    public byte[] exportReportSales(Date start, Date end) throws FileNotFoundException, JRException {
        //Load file and compile it
        String path = "src/main/resources";
        //Payment[] payments={};
        List<Payment> payments;
        //payments = Arrays.asList(new Payment());
                //Purchases.getAllOngToolsPurchases(start, end);
        payments=Purchases.getAllOngCreditoolsPurchases(start,end);
        //File file= ResourceUtils.getFile("classpath:vendas-ong.jrxml");
        File file= ResourceUtils.getFile(path + "/vendas-ong.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(payments);
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);

    }

    public byte[] exportReportPurchases(int idDweller) throws FileNotFoundException, JRException {
        //Load file and compile it
        String path = "src/main/resources";
        //Payment[] payments={};
        //List<Payment> payments = new ArrayList<Payment>();
        List<DwellerHistory> compras = new ArrayList<DwellerHistory>();
        compras=Purchases.getAllDwellerCreditoolsPurchases(idDweller);

        int i;
        int n = compras.size();
        for(i = 0; i<n; i++){
            compras.get(i).setCredit(compras.get(i).getCredit()*10);
            compras.get(i).setBalance(Purchases.getBalance(idDweller));
        }

        //payments = Arrays.asList(new Payment());
        //Purchases.getAllOngToolsPurchases(start, end);
        //payments=Purchases.getAllDwellerCreditoolsPurchases(idDweller);
        //File file= ResourceUtils.getFile("classpath:vendas-ong.jrxml");
        File file= ResourceUtils.getFile(path + "/historico.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(compras);
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);

    }





}
