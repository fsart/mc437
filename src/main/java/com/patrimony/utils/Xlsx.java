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

    public static void read (InputStream inputStream) {
        try {
            XSSFWorkbook wb = new XSSFWorkbook(InputStream inputStream);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
