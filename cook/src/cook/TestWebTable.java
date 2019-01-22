package cook;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestWebTable {
	@Test
	public void testTab() {
		WebDriver driver=new ChromeDriver();
		driver.get("http://www.w3school.com.cn/tags/tag_table.asp");
		//得到table元素
		WebTable webTable=new WebTable(driver.findElement(By.tagName("table")));
		//验证总行数
		assertEquals(10, webTable.getRowCount());
		//验证第2行的列数
		assertEquals(4, webTable.getColCount(1));
		//验证第4行，第3列的单元格内容
		assertEquals("规定表格边框的宽度", webTable.getCellText(3, 2));
		driver.close();
	}
	
}
