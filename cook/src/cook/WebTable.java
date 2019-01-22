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
	// 得到表格的行数
	public int getRowCount() {
		List<WebElement> rowcounts=webElement.findElements(By.tagName("tr"));
		return rowcounts.size();
	}
	// 得到指定行的列数
	public int getColCount(int rowIdx) {
		try {
			List<WebElement> rowCounts=webElement.findElements(By.tagName("td"));
			  // 取得当前的tr
			WebElement rowNum=rowCounts.get(rowIdx);
			// 计算当前的td数
			List<WebElement> colCounts=rowNum.findElements(By.tagName("td"));
			return colCounts.size();
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Failed to get the cell");
		}
	}
	// 得到指定单元格的内容
	public String getCellText(int rowIdx,int colIdx) {
		try {
			List<WebElement> rowCounts=webElement.findElements(By.tagName("tr"));
			  // 得到对应的行数
			WebElement currentRow=rowCounts.get(rowIdx);
			List<WebElement> td=currentRow.findElements(By.tagName("td"));
			// 取得对应的单元格
			WebElement cell=td.get(colIdx);
			return cell.getText();
		} catch (Exception e) {
			throw new NoSuchElementException("Failed to get the cell");
		}
	}
}
