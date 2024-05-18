package example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XLSXActions<E> {
    private static final String EXTENSION = ".xlsx";
    private static final String PATH_TO_FILE_DIRECTORY = "./";
    /**
     * Writing Data to XLSX
     * @param filePath       - file name
     * @param header         - array of keys
     * @param nameSheet      - name of current sheet
     * @param inputDataTable - external data to upload it into XLSX
     */
    public static <E> void writeFile (
                E[][] inputDataTable,
                String filePath,
                String[] header,
                String nameSheet
            ) {

        FileOutputStream fileOut = null;
        Workbook workbook = null;
        try {
            String pathFile = Paths.get(PATH_TO_FILE_DIRECTORY + filePath + EXTENSION).toString();
            if (!Files.exists(Paths.get(pathFile))) {
                // Creating a new Excel Book
                workbook = new XSSFWorkbook();
            } else {
                // Switch to Excel Book
                FileInputStream fileInputStream = new FileInputStream(filePath + EXTENSION);
                workbook = new XSSFWorkbook(fileInputStream);
            }

            Sheet sheet = workbook.getSheet(nameSheet);
            if (sheet == null)
                sheet = workbook.createSheet(nameSheet);

            // Creating List and implements Header into it
            Row row = sheet.createRow(0);
            for (int j = 0; j < header.length; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(header[j]);
            }

            for (int i = 0; i < inputDataTable.length; i++) {
                Row currentRow = sheet.getRow(i + 1);
                if (currentRow == null) {
                    currentRow = sheet.createRow(i + 1);
                }
                for (int j = 0; j < inputDataTable[0].length; j++) {
                    Cell cell = currentRow.createCell(j);
                    cell.setCellValue(inputDataTable[i][j].toString());
                }
            }

            // Saving Excel book into file
            fileOut = new FileOutputStream(filePath + EXTENSION);
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOut != null) {
                    fileOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Class to read a XLSX file and return its contents as a String matrix.
     * @param filePath      The path to the XLSX file.
     * @param sheetPosition The position of the sheet to read.
     * @return A 2D String array representing the contents of the XLSX file.
     */
    public static String[][] parserFromXLSX(
            String filePath,
            int sheetPosition
    ) {
        String[][] outputData = null;
        try {
            // Opening File
            FileInputStream file = new FileInputStream(filePath);

            // Creating Object working with XLSX
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Getting 1st Sheet
            XSSFSheet sheet = workbook.getSheetAt(sheetPosition - 1);

            int numCols = 0;
            for (Row row : sheet) {
                numCols = Math.max(numCols, row.getPhysicalNumberOfCells());
            }
            outputData = new String[sheet.getPhysicalNumberOfRows()]
                    [numCols];
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < numCols; j++) {
                    Cell cell = row.getCell(j);
                    outputData[i][j] = (cell != null) ? cell.toString() : "\t";
                }
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputData;
    }

    public static int getSheetsNum(String filePath) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(filePath);
            return workbook.getNumberOfSheets();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static <E> void writeFile (
                E[] tableData,
                String filePath,
                String[] header,
                String nameSheet
    ){
        FileOutputStream fileOut = null;
        Workbook workbook = null;
        try {
            //Переходим в существующую книгу Excel
            FileInputStream fileInputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileInputStream);

            //Получаю лист
            Sheet sheet = workbook.getSheet(nameSheet);
            if(sheet == null){ // если листа нету то getSheet() вернет null
                sheet = workbook.createSheet(nameSheet);
                Row row = sheet.createRow(0);
                for (int j = 0; j < header.length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(header[j]);
                }
            }

            Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
            for (int i = 0; i < tableData.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(tableData[i].toString());
            }

            // Сохраняем книгу Excel в файл
            fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOut != null) {
                    fileOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public static<E> void writeFile(
//            E[] inputDataTable,
//            String filePath,
//            String[] header,
//            String nameSheet
//    ) {
//        FileOutputStream fileOut = null;
//        Workbook workbook = null;
//        try {
//            String pathFile = Paths.get(PATH_TO_FILE_DIRECTORY + filePath + EXTENSION).toString();
//
//            // Switch to Excel Book
//            FileInputStream fileInputStream = new FileInputStream(filePath + EXTENSION);
//            workbook = new XSSFWorkbook(fileInputStream);
//
//            Sheet sheet = workbook.getSheet(nameSheet);
//            if (sheet == null) {
//                sheet = workbook.createSheet(nameSheet);
//                Row row = sheet.createRow(0);
//                for (int i = 0; i < header.length; i++) {
//                    Cell cell = row.createCell(i);
//                    cell.setCellValue(header[i]);
//                }
//            }
//            // Creating List and implements Header into it
//            Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
//            for (int j = 0; j < header.length; j++) {
//                Cell cell = row.createCell(j);
//                cell.setCellValue(header[j]);
//            }
//
//            // Saving Excel book into file
//            fileOut = new FileOutputStream(filePath + EXTENSION);
//            workbook.write(fileOut);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (fileOut != null) {
//                    fileOut.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}