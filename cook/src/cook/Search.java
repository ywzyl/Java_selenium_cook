package cook;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search {
	 //��λԪ�� 
	private WebElement search;
	@FindBy(css="button.button")
	private WebElement searchButton;
	public Search() {
		PageFactory.initElements(HomePage.driver(), this);
	}
	 //�����ѯ���ݵķ��� 
	public SearchResults searchInStore(String query) {
		search.sendKeys(query);
		searchButton.click();
		return new SearchResults(query);
	}
}
