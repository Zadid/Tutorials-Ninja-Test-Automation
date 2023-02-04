package org.example;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadData {
        public Map[] readFromExcel(String fileDir, String fileName, String sheetName) throws IOException {
            String[][] dataArr;
            File file = new File(fileDir + "\\" + fileName);
            FileInputStream inputStream = new FileInputStream(file);
            Workbook extractDataWorkbook = null;

            String fileExtensionName = fileName.substring(fileName.indexOf("."));
            if (fileExtensionName.equals(".xlsx")) {
                extractDataWorkbook = new XSSFWorkbook(inputStream);
            } else if (fileExtensionName.equals(".xls")) {
                extractDataWorkbook = new HSSFWorkbook(inputStream);
            }

            Sheet extractDataSheet = extractDataWorkbook.getSheet(sheetName);
            int numOfRow = (extractDataSheet.getLastRowNum() - extractDataSheet.getFirstRowNum()) + 1;
            int numOfColumn = extractDataSheet.getRow(0).getPhysicalNumberOfCells();
            dataArr = new String[numOfRow][numOfColumn];
            for (int i = 0; i < numOfRow; i++) {
                Row row = extractDataSheet.getRow(i);
                for (int j = 0; j < numOfColumn; j++) {
                    Cell cell = row.getCell(j);
                    if(cell == null || cell.getCellType() == CellType.BLANK){
                        dataArr[i][j] = " ";
                        //System.out.println(dataArr[0][j]);
                    }else {
                        dataArr[i][j] = cell.toString();
                    }
                }
            }

            Map<String,String> map[] = new Map[dataArr.length];
            for (int i = 1; i < numOfRow; i++) {
                map[i] = new HashMap<>();
                for (int j = 0; j < numOfColumn; j++){
                    map[i].put(dataArr[0][j], dataArr[i][j]);
                }
                System.out.println(map[i]);
            }
            return map;
        }
}
