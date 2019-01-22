package cook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	private static WebDriver driver;
	//ÓÃÀ´´«µÝWebDriver
	public static WebDriver driver() {
		return driver;
	}
	public HomePage() {
		driver=new ChromeDriver();
		PageFactory.initElements(driver, this);
	}
	
	public void load() {
		driver.get("http://www.baidu.com/");
	}
	public void close() {
		driver.close();
	}
	public Search Search() {
		Search search= new Search();
		return search;
	}
}
