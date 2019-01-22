package cook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BmiCalculatePage_xpath_css {
@FindBy(css="ul input")
@CacheLookup
public WebElement heightCMS;
@FindBy(xpath="//ul/li[2]/div/input")
public WebElement weightKg;
public WebElement calculate;
public WebElement bmi;
public WebElement bmi_category;
public BmiCalculatePage_xpath_css (WebDriver driver) {
	PageFactory.initElements(driver, this);
}
}
