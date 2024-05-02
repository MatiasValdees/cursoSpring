package cl.bytnet.best_travel.Infraestructure.Service;

import cl.bytnet.best_travel.Domain.Entities.CustomerEntity;
import cl.bytnet.best_travel.Domain.Repositories.JPA.CustomerRepository;
import cl.bytnet.best_travel.Infraestructure.AbstractService.IReportService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

@AllArgsConstructor
@Log4j2
@Service
public class ExcelService implements IReportService {
    private final CustomerRepository customerRepository;
    private static final String SHEET_NAME = "Customer total sales";
    private static final String FONT_TYPE = "Arial";
    private static final String COLUMN_CUSTOMER_ID = "id";
    private static final String COLUMN_CUSTOMER_NAME = "name";
    private static final String COLUMN_CUSTOMER_PURCHASES = "purchases";
    private static final String REPORTS_PATH_WITH_NAME = "reports/Sales-%s";
    private static final String REPORTS_PATH = "reports";
    private static final String FILE_TYPE= ".xlsx";
    private static final String FILE_NAME= "Sales-%s.xlsx";

    private void createReport(){
        var workbook=new XSSFWorkbook();
        var sheet= workbook.createSheet(SHEET_NAME);
        sheet.setColumnWidth(0,5000);
        sheet.setColumnWidth(0,8000);
        sheet.setColumnWidth(0,2000);

        var header=sheet.createRow(0);
        var headerStyle=workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.VIOLET.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        var font=workbook.createFont();
        font.setFontName(FONT_TYPE);
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        var  headerCell=header.createCell(0);
        headerCell.setCellValue(COLUMN_CUSTOMER_ID);
        headerCell.setCellStyle(headerStyle);

        headerCell=header.createCell(1);
        headerCell.setCellValue(COLUMN_CUSTOMER_NAME);
        headerCell.setCellStyle(headerStyle);

        headerCell=header.createCell(2);
        headerCell.setCellValue(COLUMN_CUSTOMER_PURCHASES);
        headerCell.setCellStyle(headerStyle);

        var style=workbook.createCellStyle(); //en work se crea e; estilo
        style.setWrapText(true);

        var customers=customerRepository.findAll();
        var rowPos=1;
        for(CustomerEntity customer:customers){
            var row= sheet.createRow(rowPos);
            var cell=row.createCell(0);
            cell.setCellValue(customer.getDni());
            cell.setCellStyle(style);

            cell=row.createCell(1);
            cell.setCellValue(customer.getFullName());
            cell.setCellStyle(style);

            cell=row.createCell(2);
            cell.setCellValue(getTotalPurchase(customer));
            cell.setCellStyle(style);
            rowPos++;
        }

        var report= new File(String.format(REPORTS_PATH_WITH_NAME, LocalDate.now().getMonth()));
        var path=report.getAbsolutePath();
        var fileLocation=path+FILE_TYPE;
        try(var out=new FileOutputStream(fileLocation)){
            workbook.write(out);
            workbook.close();
        }catch (IOException e){
            log.info(e);
        }




    }

    private static int getTotalPurchase(CustomerEntity customer){
        return customer.getTotalLodgings()+ customer.getTotalFlights()+customer.getTotalTours();
    }
    @Override
    public byte[] readFile() {
        try{
            createReport();
            var path= Paths.get(REPORTS_PATH,String.format(FILE_NAME,LocalDate.now().getMonth()));
            return Files.readAllBytes(path);
        }catch (IOException e){
            log.info(e);
            throw new RuntimeException();
        }
    }


}
