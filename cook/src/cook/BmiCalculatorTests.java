package cook;

import static org.junit.Assert.assertEquals;

import java.sql.DriverManager;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BmiCalculatorTests {
@Test
public void testBmiCalc() {
	WebDriver  driver=new ChromeDriver();
	driver.get("d:\\demo\\BMICalculator.html");
	 //创建页面实例 
	BmiCalculatePage bmiCalculatePage=new BmiCalculatePage(driver);
	//直接调用页面的元素无需关心元素定位，并输入数据
	bmiCalculatePage.heightCMS.sendKeys("181");
	bmiCalculatePage.weightKg.sendKeys("80");
	bmiCalculatePage.calculat.click();
	 //断言
	assertEquals("24.4", bmiCalculatePage.bmi.getAttribute("value"));
	assertEquals("Normal", bmiCalculatePage.bmi_category.getAttribute("value"));
	driver.close();
	}
}
