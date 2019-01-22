package cook;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SearchResults {
	public SearchResults (String query) {
		PageFactory.initElements(HomePage.driver(), this);
	}
	public List<String> getProducts(){
		   //得到查询结果 
		List<String> products= new ArrayList<String>();
		List<WebElement> productList=HomePage.driver().findElements(By.className("text"));
		for (WebElement item : productList) {
			products.add(item.findElement(By.cssSelector("h2>a")).getText());			
		}
		return products;
	}
}
