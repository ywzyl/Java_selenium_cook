package cook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class BmiCalculatePage_div {
	private WebElement heightCMS;
	private WebElement weightKg;
	private WebElement Calculate;
	private WebElement bmi;
	private WebElement bmi_category;
	private String url="http://www.baidu.com/";
	private WebDriver driver;

public BmiCalculatePage_div() {
	driver=new ChromeDriver();
	PageFactory.initElements(driver, this);
}
public void load() {
	this.driver.get(url);
}
public void close() {
	this.driver.close();
}
public void calculateBmi(String height,String weight) {
	heightCMS.sendKeys(height);
	weightKg.sendKeys(weight);
	Calculate.click();
}
public String getBmi() {
	return bmi.getAttribute("value");
}
public String getBmiCategory() {
	return bmi_category.getAttribute("value");
}
}
