package com.erip.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ParserXlsx implements Parser {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void parse(File file) {

        File excelFile = file;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(excelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            logger.info("parse1");
            XSSFSheet sheet = workbook.getSheetAt(0);
            logger.info("parse2");
            XSSFRow firstRow = sheet.getRow(sheet.getFirstRowNum());
            XSSFCell firstCell = firstRow.getCell(firstRow.getFirstCellNum());
            firstCell.toString();

            Row headerRow = sheet.getRow(sheet.getFirstRowNum() + 2);
            logger.info("parse3");
            parseHeaderRow(headerRow);
            logger.info("parse4");

            /*for (int i = sheet.getFirstRowNum() + 3; i <= sheet.getLastRowNum(); i++) {
                testRules.add(parseRow(sheet.getRow(i)));
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void parseHeaderRow(Row row) {
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell == null) {
                return;
            }
            String value = cell.getStringCellValue();
          /*  if (!StringUtils.isBlank(value)) {
                if (ruleHeaders.isEmpty()) {
                    firstRuleColumn = i;
                }
                ruleHeaders.put(i, parseRuleByName(value));
                lastRuleColumn = i;
            }*/
        }
    }

    /*private TestRule parseRow(Row row) {
        int padding = 0;
        List<RuleDecisionResult> rules = new ArrayList<>();
        for (int i = firstRuleColumn; i <= lastRuleColumn + padding; i++) {
            Cell cell = row.getCell(i);
            if (!StringUtils.isBlank(cell.getStringCellValue())) {
                rules.add(parseRule(cell, i - padding));
            } else {
                padding++;
            }
        }
        return new TestRule(rules, parseDecision(row.getCell(lastRuleColumn + padding + 1).getStringCellValue()),
            row.getRowNum());
    }*/

}
