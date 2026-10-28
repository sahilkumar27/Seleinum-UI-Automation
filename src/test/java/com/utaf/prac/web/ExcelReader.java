//package com.utaf.prac.web;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ExcelReader {
//
//    public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) {
//        String cellValue = null;
//        try (FileInputStream fis = new FileInputStream(filePath);
//             Workbook workbook = new XSSFWorkbook(fis)) {
//
//            Sheet sheet = workbook.getSheet(sheetName);
//
//            if (sheet == null) {
//                throw new RuntimeException("Sheet '" + sheetName + "' not found in workbook");
//            }
//
//            Row row = sheet.getRow(rowNum);
//            if (row == null) {
//                throw new RuntimeException("Row " + rowNum + " not found in sheet '" + sheetName + "'");
//            }
//
//            Cell cell = row.getCell(colNum);
//            if (cell != null) {
//                cellValue = cell.toString();
//            } else {
//                cellValue = ""; // Return empty string for blank cells
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return cellValue;
//    }
//
//    public static List<Map<String, String>> getTestDataFromSheet(String filePath, String sheetName) {
//        List<Map<String, String>> testDataList = new ArrayList<>();
//
//        try (FileInputStream fis = new FileInputStream(filePath);
//             Workbook workbook = new XSSFWorkbook(fis)) {
//
//            Sheet sheet = workbook.getSheet(sheetName);
//
//            if (sheet == null) {
//                throw new RuntimeException("Sheet '" + sheetName + "' not found in workbook");
//            }
//
//            // Get header row
//            Row headerRow = sheet.getRow(0);
//            if (headerRow == null) {
//                throw new RuntimeException("Header row not found in sheet '" + sheetName + "'");
//            }
//
//            // Create column index mapping
//            Map<String, Integer> columnIndexMap = new HashMap<>();
//            for (Cell cell : headerRow) {
//                columnIndexMap.put(cell.toString(), cell.getColumnIndex());
//            }
//
//            // Process data rows (skip header row)
//            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//                Row row = sheet.getRow(i);
//                if (row != null) {
//                    Map<String, String> rowData = new HashMap<>();
//                    for (Map.Entry<String, Integer> entry : columnIndexMap.entrySet()) {
//                        String columnName = entry.getKey();
//
//    public static void main(String[] args) {
//        // Example usage
//        String filePath = "src/test/resources/loginTestData.xlsx";
//        List<Map<String, String>> testData = getTestDataFromSheet(filePath, "Sheet1");
//
//        for (Map<String, String> row : testData) {
//            System.out.println("Username: " + row.get("Username") + ", Password: " + row.get("Password"));
//        }
//    }
//}
//                        int columnIndex = entry.getValue();
//
//                        Cell cell = row.getCell(columnIndex);
//                        String cellValue = "";
//                        if (cell != null) {
//                            switch (cell.getCellType()) {
//                                case STRING:
//                                    cellValue = cell.getStringCellValue();
//                                    break;
//                                case NUMERIC:
//                                    if (DateUtil.isCellDateFormatted(cell)) {
//                                        cellValue = cell.getDateCellValue().toString();
//                                    } else {
//                                        cellValue = String.valueOf(cell.getNumericCellValue());
//                                    }
//                                    break;
//                                case BOOLEAN:
//                                    cellValue = String.valueOf(cell.getBooleanCellValue());
//                                    break;
//                                case FORMULA:
//                                    cellValue = cell.getCellFormula();
//                                    break;
//                                default:
//                                    cellValue = "";
//                            }
//                        }
//                        rowData.put(columnName, cellValue);
//                    }
//                    testDataList.add(rowData);
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return testDataList;
//    }
//}
