package com.zorrix.parser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ooxml.*;
import org.apache.poi.hssf.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static com.zorrix.Constants.FILE_NAME;

//service responding for parsing .xlsx file with schedule
public class SheetsParserService {
    /*
    * method, which parsing whole xlsx sheet,
    * writing it to the 7 DayNSubjects,
    * putting 4 weeks (each made of 7 DayNSubjects) into the map in output;
    */
    public HashMap<Integer, ArrayList<DayNSubjects>> parseSubjects() throws IOException {
        //init the result hashmap
        HashMap<Integer, ArrayList<DayNSubjects>> result = new HashMap<>();

//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);

        FileInputStream inputStream = new FileInputStream("src/main/resources/" + FILE_NAME);

        XSSFWorkbook wb = (XSSFWorkbook) WorkbookFactory.create(inputStream);

        XSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        Row row = rowIterator.next();
        row.createCell(0).setCellValue(" ");

        int weekIterator = 1;

        //iterating trough each row in the sheet
        while (rowIterator.hasNext()){
            Iterator<Cell> cellIterator = rowIterator.next().cellIterator();

            Cell firstCellSkip = sheet.getRow(0).getCell(1);
            Iterator<Cell> daysIterator = firstCellSkip.getRow().cellIterator();

            ArrayList<DayNSubjects> week = new ArrayList<>();

            //iterating trough each cell in the rowIterator row
            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                String temp;

                //iterating trough each day of week, writing its name into the currentDay
                Cell currentDayCell = daysIterator.next();
                String currentDay = currentDayCell.getStringCellValue().toLowerCase();

                String nullStr = "null";
                switch (cell.getCellType()) {
                    case _NONE, BLANK, BOOLEAN, FORMULA, ERROR:
                        continue;
                    case NUMERIC:
                        break;
                    case STRING:
                        temp = cell.getStringCellValue();
                        if (temp.contains(nullStr) || temp.contains("Weeks")) continue;
                        temp = cell.getStringCellValue().toLowerCase();

                        //putting each currentDay's subject into the subjects array
                        String[] subjects = temp.split("[,_;:]");
                        week.add(new DayNSubjects(currentDay, subjects));
                        break;
                }
            }
            result.put(weekIterator, week);
            weekIterator++;
        }

        inputStream.close();
        wb.close();
        return result;
    }
}
