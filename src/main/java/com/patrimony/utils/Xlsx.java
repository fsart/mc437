package com.patrimony.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Xlsx {

    public static void parse (InputStream inputStream) {
        try {
            // write the inputStream to a FileOutputStream
            OutputStream outputStream = new FileOutputStream(new File("./temp.xlsx"));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            inputStream.close();
            outputStream.close();

            System.out.println("Done!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void read () {
        File excel =  new File ("./temp.xlsx");
        FileInputStream fis = new FileInputStream(excel);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet ws = wb.getSheet("Input");

        int rowNum = ws.getLastRowNum() + 1;
        int colNum = ws.getRow(0).getLastCellNum();
        String [][] data = new String [rowNum] [colNum];

        for(int i = 0; i <rowNum; i++){
            XSSFRow row = ws.getRow(i);
            for (int j = 0; j < colNum; j++){
                XSSFCell cell = row.getCell(j);
                String value = cell.toString();
                data[i][j] = value;
                System.out.println ("the value is " + value);
            }
        }
    }
}
