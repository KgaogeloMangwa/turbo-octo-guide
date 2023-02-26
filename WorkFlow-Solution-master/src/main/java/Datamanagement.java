import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;

public class Datamanagement {


//    directly connect to the database
    private Connection connect() throws SQLException {
        Connection connect = DriverManager.getConnection("/home/prince/Downloads/WorkFlow-Solution-master/mycustomers.db");
        return connect;
    }

//    This method gets called by the process query to execute the query by adding and saving the data in the database
    private void insertExecution(String query) throws SQLException {
        Connection connect = this.connect();
        connect().createStatement().execute(query);
        connect.close();
    }

//should we need to add more data to the income and expenditure table
    public void processQuery(String rawquery) throws SQLException {

        String[] queryArray = rawquery.split(":");

        StringBuilder sb = new StringBuilder("INSERT INTO");
        sb.append("'Customer' ('Month', 'Income', 'Expenses')");
        sb.append("VALUES (?");
        sb.append(",'");
        sb.append(queryArray[0]);
        sb.append(",'");
        sb.append(queryArray[1]);
        sb.append(",'");
        sb.append(queryArray[2]);
//        System.out.println(queryArray[1]);
        System.out.println(sb);
        this.insertExecution(sb.toString());


    }


// to loop through the spreadsheet and return the values as a string
//    two while loops one iterate through the rows and the other through the cells
//    looks for specific type of data an extract it while creating a string
//    which it passes to the query should we need to add more data
    public void loopExcelSheet(XSSFSheet sheet) throws SQLException {
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            StringBuilder sb = new StringBuilder();
            while (cellIterator.hasNext()) {
//                sb = new StringBuilder();

                Cell cell = cellIterator.next();
                cell.getCellType();

                if (cell.getCellType().equals(CellType.STRING)) {

                    sb.append(cell.getStringCellValue() + ":");
                }

                else if (cell.getCellType().equals(CellType.NUMERIC)) ;
                {
                    sb.append(cell.getStringCellValue() + ":");

                }

                if (cell.getCellType().equals(CellType.BLANK)) ;
                {
//                    System.out.println("*");
                    sb.append("++:");
                }
            }
//            System.out.println(sb);

            this.processQuery(sb.toString());
        }

    }
}
