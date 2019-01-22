package cook;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class SetValue {
	
	
	@Test
	public void testSet() {
		WebDriver driver=new ChromeDriver();
		driver.get("http://www.baidu.com");
		WebElement wb=driver.findElement(By.id("kw"));
		Util.setAttribute(wb, "value", "test");
	}
	
	
	
	@Test
	public void testSreenShot() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.baidu.com");
		WebElement wb=driver.findElement(By.cssSelector("#nv a"));
		try {
			FileUtils.copyFile(Util.captureElement(wb), new File("c:\\a.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
