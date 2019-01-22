package cook;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class WebTable {
	private WebElement webElement;
	public WebTable(WebElement webElement) {
		this.webElement=webElement;
	}
	// �õ���������
	public int getRowCount() {
		List<WebElement> rowcounts=webElement.findElements(By.tagName("tr"));
		return rowcounts.size();
	}
	// �õ�ָ���е�����
	public int getColCount(int rowIdx) {
		try {
			List<WebElement> rowCounts=webElement.findElements(By.tagName("td"));
			  // ȡ�õ�ǰ��tr
			WebElement rowNum=rowCounts.get(rowIdx);
			// ���㵱ǰ��td��
			List<WebElement> colCounts=rowNum.findElements(By.tagName("td"));
			return colCounts.size();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Failed to get the cell");
		}
	}
	// �õ�ָ����Ԫ�������
	public String getCellText(int rowIdx,int colIdx) {
		try {
			List<WebElement> rowCounts=webElement.findElements(By.tagName("tr"));
			  // �õ���Ӧ������
			WebElement currentRow=rowCounts.get(rowIdx);
			List<WebElement> td=currentRow.findElements(By.tagName("td"));
			// ȡ�ö�Ӧ�ĵ�Ԫ��
			WebElement cell=td.get(colIdx);
			return cell.getText();
		} catch (Exception e) {
			throw new NoSuchElementException("Failed to get the cell");
		}
	}
}
