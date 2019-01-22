package cook;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


@RunWith(value=Parameterized.class)
public class LockTest {
	private static WebDriver driver;
	private String height;
	private String weight;
	private String bmi;
	private String bmiCategory;
		
@Parameters
public static Collection testData() {
	return Arrays.asList(new Object[][] {
		{"160","45","17.6","Underweight"},
		{"168","70","24.8","Normal"},
		{"181","89","27.2","Overweight"},
		{"178","100","31.6","Obesity"},
	});
	}
	 //LockTest的构造函数，用来赋值 
public LockTest(String height,String weight,String bmi,String bmiCategory) {
	this.height=height;
	this.weight=weight;
	this.bmi=bmi;
	this.bmiCategory=bmiCategory;
}
	
@Test
public void testBMI() {
	driver=new ChromeDriver();
	driver.get("http://www.baidu.com/");
		  //输入身高 
	WebElement heightField=driver.findElement(By.name("heightCMS"));
	heightField.sendKeys(height);
		  //输入体重 
	WebElement weightField=driver.findElement(By.name("weightKg"));
	weightField.sendKeys(weight);
		  //点击计算 
	WebElement calcButton=driver.findElement(By.id("Calculate"));
	calcButton.click();
	     //验证计算后的BMI值 
	WebElement bmiLabel=driver.findElement(By.name("bmi"));
	assertEquals(bmi, bmiLabel.getAttribute("value"));
	     //验证分类的结果 
	WebElement bmiCategoryLabel=driver.findElement(By.name("bmi_category"));
	assertEquals(bmiCategory, bmiCategoryLabel.getAttribute("value"));
	driver.close();
}
	
@After
public void tearDown() {
	driver.close();
	}
}
