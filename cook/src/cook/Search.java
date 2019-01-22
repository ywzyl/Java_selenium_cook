package cook;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search {
	 //定位元素 
	private WebElement search;
	@FindBy(css="button.button")
	private WebElement searchButton;
	public Search() {
		PageFactory.initElements(HomePage.driver(), this);
	}
	 //输入查询内容的方法 
	public SearchResults searchInStore(String query) {
		search.sendKeys(query);
		searchButton.click();
		return new SearchResults(query);
	}
}
