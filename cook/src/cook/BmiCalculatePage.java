package cook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BmiCalculatePage {
	//使用name或id定义页面元素
	@FindBy(id="heightCMS")
	@CacheLookup
	public WebElement heightCMS;
	public WebElement weightKg;
	public WebElement calculat;
	public WebElement bmi;
	public WebElement bmi_category;
	//构造函数来初始化元素
public BmiCalculatePage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}

}
