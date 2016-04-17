package debugger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelWriter {
	private Workbook book; 
	private Sheet sheet;
	
	public ExcelWriter(){
	}

	public void start(){
		book = new XSSFWorkbook(); 
		sheet = book.createSheet("data");  
        Row row = sheet.createRow((short) 0); 
        
        String titles[] = {"operation",
        		"class name",
        		"line number",
        		"reason"};
        for(int i = 0; i < titles.length; i++){
        	row.createCell(i).setCellValue(titles[i]); 
        }
        
	}
	
	public void export(List<LogContent> logs, String fileName){
		int rowNo = 1;
        try {
        	for(LogContent log: logs){
        		Row row = sheet.createRow(rowNo);
        		
        		row.createCell(0).setCellValue(log.getBreakPointOperation());
        		row.createCell(1).setCellValue(log.getClassName());
        		row.createCell(2).setCellValue(log.getLineNumber());
        		row.createCell(3).setCellValue(log.getReason());  
        		
        		rowNo++;
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        for(int i=0; i<4; i++){
        	sheet.autoSizeColumn(i);
        }
        
        writeToExcel(fileName, logs);
	}

	private void writeToExcel(String fileName, List<LogContent> logs){
		
		File file = new File(fileName + ".xlsx");
		if(file.exists() && logs.size()==1){
			String newOldFileName = fileName + UUID.randomUUID() + ".xlsx"; 
			File newOldFile = new File(newOldFileName);
			file.renameTo(newOldFile);
		}
		
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName + ".xlsx");
			book.write(fileOut); 
			fileOut.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
