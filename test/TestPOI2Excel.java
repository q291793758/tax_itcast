import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;

public class TestPOI2Excel {

    @Test
    public void testWrite03Excel() throws IOException {

        //创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("工作表名1");
        HSSFSheet sheet1 = workbook.createSheet("工作表名2");
        //创建第三行
        HSSFRow row = sheet.createRow(2);  //第3行 下表从0开始
        //创建单元格
        HSSFCell cell = row.createCell(2); //第3列  下表从0开始
        cell.setCellValue("第三行第三列内容");
        //输出到硬盘
        OutputStream out=new FileOutputStream(new File("c:\\test03poi.xls"));
        workbook.write(out);
        workbook.close();
        out.close();
    }

    @Test
    public void testRead03excel() throws IOException {
        FileInputStream in = new FileInputStream("c:\\test03poi.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(in);
        HSSFSheet sheet = workbook.getSheet("工作表名1");
        HSSFRow row = sheet.getRow(2);
        HSSFCell cell = row.getCell(2);

    }

    @Test
    public void test03And01() throws IOException, InterruptedException {
        String fileName = "c:\\test03poi.xlsx";
        if (fileName.matches("^.+\\.(?i)(xls)|.+\\.(?i)(xlsx)$")) {
            boolean is03Excel =fileName.matches("^.+\\.(?i)(xls)$");
            FileInputStream in = new FileInputStream(new File(fileName));
            //检查是xls  还是 xlsx文档
            Workbook workbook = is03Excel? new HSSFWorkbook(in):new XSSFWorkbook(in);
            Sheet sheet =  workbook.getSheetAt(0);
            Row row = sheet.getRow(2);

            Cell cell = row.getCell(2);
            String value = cell.getStringCellValue();
            System.out.println("value = " + value);
        }
    }

}
