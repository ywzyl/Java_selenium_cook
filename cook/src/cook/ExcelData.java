package cook;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(value=Parameterized.class)
public class ExcelData {
	private WebDriver driver;
	private String height;
	private String weight;
	private String bmi;
	private String bmiCategory;
	 //参数化 
@Parameters
public static Collection<String []> testData() throws IOException{
	InputStream is=new FileInputStream("d://data//data.xlsx");
	XSSFWorkbook workbook=new XSSFWorkbook(is);
	  //获得工作表 	
	XSSFSheet sheet=workbook.getSheetAt(0);
	  //得到总行数   getLastRowNum  如果sheet中一行数据都没有则返回-1，只有第一行有数据则返回0，最后有数据的行是第n行则返回 n-1；
	int rowNum=sheet.getLastRowNum();
	List<String[]> records=new ArrayList<String[]>();
	for (int i = 1; i < rowNum; i++) {
		   //当前行 
		XSSFRow row=sheet.getRow(i);
		//getLastCellNum   如果row中一列数据都没有则返回-1，只有第一列有数据则返回1，最后有数据的列是第n列则返回 n；
		int colNum=row.getLastCellNum();
		String[] data=new String[colNum];
		
		for (int j = 0; j < colNum; j++) {
			data[j] =row.getCell(j).getStringCellValue();
			System.out.println(data[j]);
		}
		records.add(data);
	}
	return records;
}
//构造函数赋值 
public ExcelData(String height, String weight, String bmi, String bmiCategory) {
	this.height = height;
	this.weight = weight;
	this.bmi = bmi;
	this.bmiCategory = bmiCategory;
}
//读取EXCEL中的文件 
public static Collection<String[]> getTestData(String path){
	List<String[]> records=new ArrayList<String[]>();
	String row;
	try {
		BufferedReader br=new BufferedReader(new FileReader(path));
		//readLine()是读取流读数据的时候用的，同时会以字符串形式返回这一行的数据，当读取完所有的数据时会返回null。
		while((row=br.readLine())!=null) {
			String fields[]=row.split(",");
			records.add(fields);
		}
		br.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return records;
}
@Before
public void setUp() {
	driver=new ChromeDriver();
	driver.get("http://www.baidu.com/");
}
@Test
public void testCsv() {	
	WebElement heightField=driver.findElement(By.name("heightCMS"));
	heightField.sendKeys(height);
	WebElement weightField=driver.findElement(By.name("weightKg"));
	weightField.sendKeys(weight);
	WebElement calcButton=driver.findElement(By.id("calculate"));
	calcButton.click();
	WebElement bmiLabel=driver.findElement(By.name("bmi"));
	assertEquals(bmi, bmiLabel.getAttribute("value"));
	WebElement bmiCategoryLabel=driver.findElement(By.name("bmi_category"));
	assertEquals(bmiCategory, bmiCategoryLabel.getAttribute("value"));
	
}
@After
public void tearDown() {
	driver.close();
}
}
