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
	 //����ҳ��ʵ�� 
	BmiCalculatePage bmiCalculatePage=new BmiCalculatePage(driver);
	//ֱ�ӵ���ҳ���Ԫ���������Ԫ�ض�λ������������
	bmiCalculatePage.heightCMS.sendKeys("181");
	bmiCalculatePage.weightKg.sendKeys("80");
	bmiCalculatePage.calculat.click();
	 //����
	assertEquals("24.4", bmiCalculatePage.bmi.getAttribute("value"));
	assertEquals("Normal", bmiCalculatePage.bmi_category.getAttribute("value"));
	driver.close();
	}
}
