package cook;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG {
WebDriver driver;
@DataProvider
public Object[][] testData(){
	return new Object[][] {
		{"160","45","17.6","Underweight"}, 
	       {"168","70","24.8","Normal"}, 
	       {"181","89","27.2","Overweight"}, 
	       {"178","100","31.6","Obesity"},};}	
@BeforeTest
public void setUp() {
	driver=new ChromeDriver();
}
@Test(dataProvider="testData")
public void testBMICal(String height,String weight,String bmi,String bmiCategory) {
	driver.get("http://www.baidu.com/");
	  //输入身高 
	WebElement heightField=driver.findElement(By.name("heightCMS"));
	heightField.sendKeys(height);
	  //输入体重 
	WebElement wegihtField=driver.findElement(By.name("weightKg"));
	wegihtField.sendKeys(weight);
	  //点击计算 
	WebElement calcButton=driver.findElement(By.id("calculate"));
	calcButton.click();
    //验证计算后的BMI值 
	WebElement bmiLabel=driver.findElement(By.name("bmi"));
	assertEquals(bmi, bmiLabel.getAttribute("value"));
    //验证分类的结果 
	WebElement bmiCategoryLabel=driver.findElement(By.name("bmi_category"));
	assertEquals(bmiCategory, bmiCategoryLabel.getAttribute("value"));
}
@AfterTest
public void tearDown() {
	driver.quit();
}
}
