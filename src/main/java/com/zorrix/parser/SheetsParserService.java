package com.zorrix.parser;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static com.zorrix.Constants.fileName;

public class SheetsParserService {
    private final String nullStr = "null";

    public SheetsParserService() {

    }

    /*
    * method, which parsing whole xlsx sheet,
    * writing it to the 7 DayNSubjects,
    * putting 4 weeks (each made of 7 DayNSubjects) into the map in output;
    */
    public HashMap<Integer, ArrayList<DayNSubjects>> parseSubjects() throws IOException, InvalidFormatException {
        HashMap<Integer, ArrayList<DayNSubjects>> result = new HashMap<>();

        FileInputStream fileInputStream = new FileInputStream(fileName);

        XSSFWorkbook wb = (XSSFWorkbook) WorkbookFactory.create(fileInputStream);


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
                Cell currentDayCell = daysIterator.next();
                String currentDay = currentDayCell.getStringCellValue().toLowerCase();

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

        fileInputStream.close();
        wb.close();
        return result;
    }
}
