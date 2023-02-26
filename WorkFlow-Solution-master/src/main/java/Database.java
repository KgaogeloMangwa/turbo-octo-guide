import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Database {


//   method to load Excel files designed to load more than one sheet
//    and process much larger Excel files
public static void loadExcel() throws IOException, SQLException {
    FileInputStream fi = new FileInputStream("/home/prince/Downloads/WorkFlow-Solution-master/upload/CustomersIncome&Expenditure.xlsx");

    XSSFWorkbook wb = new XSSFWorkbook(fi);

    Datamanagement dm = new Datamanagement();

    for (int i = 0; i < wb.getNumberOfSheets(); i++)
    {
        XSSFSheet sheet = wb.getSheetAt(i);
        dm.loopExcelSheet(sheet);
    }
// Ideally we would want the workbook and the file to close after loading
    wb.close();
    fi.close();
}

//just to call the load method
    public static void main(String[] args) throws IOException, SQLException {

        loadExcel();

    }
} 