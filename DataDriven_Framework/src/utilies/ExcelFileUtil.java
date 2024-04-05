package utilies;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	//Constructor for reading excel path
	public  ExcelFileUtil(String Excelpath) throws Throwable
	{
		FileInputStream fi = new FileInputStream(Excelpath);
		wb = new XSSFWorkbook(fi);
		
	}
	//Count no of rows in sheet
	public int rowCount (String SheetName)
	{
		return wb.getSheet(SheetName).getLastRowNum();
	}
	//Read cell data
	public String getCellData(String Sheetname,int row,int column)
	{
	String data ="";
	if (wb.getSheet(Sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC) 
	{
    int celldata = (int)wb.getSheet(Sheetname).getRow(row).getCell(column).getNumericCellValue();
    data = String.valueOf(celldata);
	}
	else
	{
		data = wb.getSheet(Sheetname).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
}
	//Method for writing results
	public void setCellData(String sheetName,int row,int cell,String status,String WriteExcel)throws Throwable
	{
		//Get sheet from wb
		XSSFSheet ws = wb.getSheet(sheetName);
		//Get row from sheet
		XSSFRow rowNum = ws.getRow(row);
		//Create cell
		XSSFCell cell = rowNum.createCell(column);
		//Write status into cell
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
		//Create object for cell style	
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(column).setcellstyle();
		}
		else if (status.equalsIgnoreCase("Fail"))
		{
			//Create object for cell style	
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(column).setcellstyle();
		}
		else if (status.equalsIgnoreCase("Blocked"))
		{
			//Create object for cell style	
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			ws.getRow(row).getCell(column).setcellstyle();
		}
		FileOutputStream fo = new FileOutputStream(WriteExcel);
		wb.write(fo);
		
	}
	
}
