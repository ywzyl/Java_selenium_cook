package cook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SearchTest {
	@Test
	public void testProductSearch() {	
		HomePage homePage=new HomePage();
		homePage.load();
		//��ѯ��Ʒ
		SearchResults searchResult=homePage.Search().searchInStore("sony");
		//��֤��Ʒ������
		assertEquals(2, searchResult.getProducts().size());
		assertTrue(searchResult.getProducts().contains("Sony Ericsson W810i"));
		homePage.close();
	}
}
