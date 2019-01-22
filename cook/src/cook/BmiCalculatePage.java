package cook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BmiCalculatePage {
	//ʹ��name��id����ҳ��Ԫ��
	@FindBy(id="heightCMS")
	@CacheLookup
	public WebElement heightCMS;
	public WebElement weightKg;
	public WebElement calculat;
	public WebElement bmi;
	public WebElement bmi_category;
	//���캯������ʼ��Ԫ��
public BmiCalculatePage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}

}
