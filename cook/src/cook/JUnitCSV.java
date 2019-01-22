package cook;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


@RunWith(Parameterized.class)
public class JUnitCSV {
	private WebDriver driver;
	private String height;
	private String weight;
	private String bmi;
	private String bmiCategory;
//参数化 
@Parameters
public static Collection<String[]> testData(){
	return getTestData("d:\\data\\data.csv");
}
//构造函数赋值 
public JUnitCSV(String height, String weight, String bmi, String bmiCategory) {
	this.height = height;
	this.weight = weight;
	this.bmi = bmi;
	this.bmiCategory = bmiCategory;
}
//读取CSV中的文件 
public static Collection<String[]> getTestData(String path){
	List<String[]> records=new ArrayList<String[]>();
	String row;
	try {
		BufferedReader br =  new BufferedReader(new FileReader(path));
		while ((row=br.readLine())!=null) {
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
