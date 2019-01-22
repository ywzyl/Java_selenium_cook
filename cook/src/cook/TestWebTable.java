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
		//�õ�tableԪ��
		WebTable webTable=new WebTable(driver.findElement(By.tagName("table")));
		//��֤������
		assertEquals(10, webTable.getRowCount());
		//��֤��2�е�����
		assertEquals(4, webTable.getColCount(1));
		//��֤��4�У���3�еĵ�Ԫ������
		assertEquals("�涨���߿�Ŀ��", webTable.getCellText(3, 2));
		driver.close();
	}
	
}
