package Utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;





import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by hy on 2017/3/31.
 *
 *
 *
 *
 */
public class excelIterator implements Iterator{
    private  int rowNum;
    private int curRowNum=1;
    private String titles[];
    private Sheet sheet;
    private int colNum;
    private Workbook workbook;
    public excelIterator(String path,String sheetName) throws IOException {
        readExcel(path,sheetName);
    }
    public  void readExcel(String filepath,String sheetName) throws IOException {
        FileInputStream fileInputStream=new FileInputStream(filepath+".xls");
        workbook=new HSSFWorkbook(fileInputStream);
        sheet=workbook.getSheet(sheetName);
        Row row=sheet.getRow(0);
        rowNum=sheet.getPhysicalNumberOfRows();
        colNum=row.getPhysicalNumberOfCells();
        titles=new String[colNum];
        
        Iterator <Cell> itr=row.cellIterator();
        int count=0;
        while (itr.hasNext()){
            Cell cell=itr.next();
            cell.setCellType(Cell.CELL_TYPE_STRING);
            titles[count]=cell.getStringCellValue();
            count++;
        }
    }
    public boolean hasNext() {
        if (rowNum==0||this.curRowNum>=rowNum){
            return false;
        }
        else{
            return true;
        }
    }

    public Object[] next() {
                 //  迭代器的next()方法
            Row row = sheet.getRow(this.curRowNum);   //      curRowNum:当前行数
            Map<String, String> map = new HashMap<String, String>();
            String value=null;
            for (int i = 0; i < colNum; i++) {
                Cell cell = row.getCell(i);
                if(row.getCell(i)!=null){
                    row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                    value = cell.getStringCellValue();
                    String value_s=value.toString();
                    map.put(titles[i], value_s);
                }
                //cell.setCellType(Cell.CELL_TYPE_STRING);


            }
            this.curRowNum++;
            Object object[] = new Object[1];
            object[0] = map;
            return object;

    }

    public void remove() {

    }


    public static void main(String[] args) {
        try {
            excelIterator c=new excelIterator("C:\\Users\\Administrator\\Desktop\\test","test");//C:\Users\Administrator\Desktop
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}