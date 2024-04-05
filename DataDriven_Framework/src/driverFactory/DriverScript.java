package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilies.ExcelFileUtil;

public class DriverScript extends AppUtil {

	String inputpath = "./Fileinput/LoginData.xlxs";
	String outputpath = "./Fileoutput/DataDrivenResults.xlsx";
	@Test
	public void startTest() throws Throwable
	{
		//Create object for Excel file util class
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//Count no of rows in login sheet
		int rc = xl.rowCount("Login");
		Reporter.log("No of rows are::+"+rc,true);
		for (int i =1;i<=rc;i++)
		{
			// read cell data
			String user = xl.getCellData("Login", i, 0);
			String pass = xl.getCellData("Login", i, 1);
			boolean res = FunctionLibrary.Check_Login(user, pass);
			if (res)
			{
				//If res is true write as Login success into results cell
				xl.setCellData("Login", i, 2, "Login Success", outputpath);
				//Write as pass into status cell
				xl.setCellData("Login", i, 3, "Pass", outputpath);
				
			}
			else
			{
				File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./screenshot/itration."+i+"LoginPage.png"));
				//Capture error message
				String error_message = driver .findElement(By.xpath("//div[@class='alert alert-danger alert-dismissable']")).getText();
				xl.setCellData("Login", i, 2, error_message,outputpath);
				xl.setCellData("Login", i, 2, "Fail", outputpath);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
