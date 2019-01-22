package cook;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class DropDown {
	public void testDropDown() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		//得到下拉列表框
		Select make=new Select(driver.findElement(By.name("make")));
		//验证下拉列表的不支持多选 
		assertFalse(make.isMultiple());
		//验证下拉列表的数量
		assertEquals(4, make.getOptions().size());
		//使用可见的本文来选择选项 
		make.selectByVisibleText("Benz");
		assertEquals("Benz", make.getFirstSelectedOption().getText());
		//通过value属性来选择选项
		make.selectByValue("Audi");
		assertEquals("Audi", make.getFirstSelectedOption().getText());
		//通过索引来选择选项
		make.selectByIndex(0);
		assertEquals("BMW", make.getFirstSelectedOption().getText());		
	}
	public void testMultipleSelectLis() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		//得到下拉列表框 
		Select color=new Select(driver.findElement(By.name("color")));
		//验证下拉列表支持多选
		assertTrue(color.isMultiple());
		//验证下拉列表的数量
		assertEquals(4, color.getOptions().size());
		//用可见的本文来选择选项
		color.selectByVisibleText("Black");
		color.selectByVisibleText("Red");
		color.selectByVisibleText("Silver");
		//验证所选的选项
		List<String> exp_sel_options=Arrays.asList(new String[] {"Black","Red","Silver"});
		List<String> act_sel_options=new ArrayList<String>();
		for(WebElement option:color.getAllSelectedOptions()) {
			act_sel_options.add(option.getText());
		}
		//验证选择的选项和我们期望的是一样的
		assertArrayEquals(exp_sel_options.toArray(), act_sel_options.toArray());
		//验证3个选项已经被选择了
		assertEquals(3, color.getAllSelectedOptions().size());
		//通过可见的文本取消已选选项
		color.deselectByVisibleText("Black");
		assertEquals(2, color.getAllSelectedOptions().size());
		color.deselectByValue("Red");
		assertEquals(1, color.getAllSelectedOptions().size());
		color.deselectByIndex(0);
		assertEquals(0, color.getAllSelectedOptions().size());		
	}
	public void testRadioButton() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		//使用value值来定位单选按钮
		WebElement apple=driver.findElement(By.cssSelector("input[value='Apple']"));
		//检查是否已选择，如果没有则点击选择
		if (apple.isSelected()) {
			apple.click();
		}
		//验证apple选项已经选中 
		assertTrue(apple.isSelected());
		//也可以得到所有的单选按钮 
		List<WebElement> fruit=driver.findElements(By.name("fruit"));
		//查询Orange选项是否存在，如果存在则选择
		for(WebElement allFruit:fruit) {
			if (allFruit.getAttribute("value").equals("Orange")) {
				if (!allFruit.isSelected()) {
					allFruit.click();
					assertTrue(allFruit.isSelected());
					break;
				}
			}
		}
			
		}
	public void testImplicitWait() throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		//等待10秒
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement p4button=driver.findElement(By.linkText("page4"));
		p4button.clear();
		WebElement message=driver.findElement(By.id("pageContent"));
		//等待Ajax的内容出现 
		Thread.sleep(4000);
		assertTrue(message.getText().contains("Nunc nibh tortor"));
	}
	
	public void testWithImplicitWait() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		WebElement button=driver.findElement(By.linkText("RUN CODE"));
		button.click();
		//设置等待时间为5秒，显示等待
		WebDriverWait wait=new WebDriverWait(driver, 5);
		//这里apply的返回值为Boolean
		Boolean clasname=wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {				
				return d.findElement(By.cssSelector(".jq-codeDemo p")).getAttribute("class").contains("ohmy");
			}
			
		});
	}
	@Test
	public void testDisplayed() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		WebElement button=driver.findElement(By.linkText("RUN CODE"));
		button.click();
		WebDriverWait wait =new WebDriverWait(driver, 5);
		//创建一个新的ExpecctedCondition接口，就必须实现apply方法
		Boolean classname=wait.until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(WebDriver d) {
				return d.findElement(By.cssSelector(".jp-codeDemo p")).isDisplayed();
			}
		});
		driver.close();
	}
	
	
	WebDriver driver=new ChromeDriver();
	public void testElementPresent() {		
		driver.get("https://www.baidu.com/");
		driver.findElement(By.id("kw")).sendKeys("selenium");
		  //判断搜索按钮是否存在 
		if (isElementPresent(By.id("sul"))) {
			   //点击按钮 
			driver.findElement(By.id("su")).click();
		}else {
			fail("元素不存在");
		}
	}
	
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void testRadioButton1() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		  //使用value值来定位单选按钮 
		WebElement apple=driver.findElement(By.cssSelector("input[value='Apple']"));
		  //检查是否已选择，如果没有则点击选择 
		if (!apple.isSelected()) {
			apple.click();
		}
		assertTrue(apple.isSelected());
		  //也可以得到所有的单选按钮 
		List<WebElement> fruit=driver.findElements(By.name("fruit"));
		for (WebElement allfruit : fruit) {
			  //查询Orange选项是否存在，如果存在则选择 
			if (allfruit.getAttribute("value").equals("Orange")) {
				if (!allfruit.isSelected()) {
					allfruit.click();
					assertTrue(allfruit.isSelected());
					break;
				}
			}
		}
	}
	
	public void testWindowPopup() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		//保存父窗口 
		String parentWindowId=driver.getWindowHandle();
		 //点击按钮弹出窗口
		WebElement helpButton=driver.findElement(By.id("helpbutton1"));
		helpButton.click();
		//转到HelpWindow
		try {
			driver.switchTo().window("HelpWindow");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//验证新窗口里的文本 
		assertEquals("PopUpWindow", driver.findElement(By.tagName("p")).getText());
		driver.close();
		//回到父窗口
		driver.switchTo().window(parentWindowId);
		//验证父窗口的title
		assertTrue(driver.getTitle().equals("help"));
		driver.close();
	}
	
	
	public void testWindos() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		  //保存父窗口 
		String parentWindowId=driver.getWindowHandle();
		WebElement buttone2=driver.findElement(By.id("helpbutton2"));
		buttone2.click();
		   //得到所有的窗口 
		Set<String> allWindowsId=driver.getWindowHandles();
		//通过title得到新的窗口
		for (String windowId : allWindowsId) {
			if (driver.switchTo().window(windowId).getTitle().equals("PopUpWindow")) {
				driver.switchTo().window(windowId);
				break;
			}
		}
		 //验证新窗口的文本
		assertEquals("PopUpWindow", driver.findElement(By.tagName("p")).getText());
		driver.close();
		driver.switchTo().window(parentWindowId);
		driver.close();
	}
	
	public void testAlert() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		//点击按钮弹出alert框
		WebElement button=driver.findElement(By.id("alert"));
		button.click();
		//获取alert窗口
		Alert alertBox=driver.switchTo().alert();
		alertBox.accept();
		//验证alert窗口里的文字
		assertEquals("Hello", alertBox.getText());
		driver.close();
	}
	
	WebDriver driver2=new ChromeDriver();
	public void testConfirm() {
		driver2.get("https://www.baidu.com/");
		//点击确定按钮
		getConfirmBox().accept();
		//验证点击后的文字
		assertEquals("点击了确定按钮",driver2.findElement(By.cssSelector("span")).getText());
		//点击取消按钮
		getConfirmBox().dismiss();
		assertEquals("点击了取消按钮", driver2.findElement(By.cssSelector("span")).getText());
		driver2.close();
	}
	//封装得到窗口的方法
	private Alert getConfirmBox() {
		WebElement button=driver2.findElement(By.id("confirm"));
		button.click();
		//获取确认提示框
		Alert confirmBox=driver2.switchTo().alert();
		assertEquals("我是确认框", confirmBox.getText());
		return confirmBox;
	}
	
	public void testFrame() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		  //通过id定位到左边的框架 
		driver.switchTo().frame("left");
		String leftMsg=driver.findElement(By.tagName("p")).getText();
		assertEquals("left",leftMsg);
		  //回到初始的焦点 
		driver.switchTo().defaultContent();
		  //通过name定位到右边的框架 
		driver.switchTo().frame("right");
		String rightMsg=driver.findElement(By.tagName("p")).getText();
		assertEquals("right", rightMsg);
		driver.close();		
	}
	
	public void testFrame2() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		  //通过index来定位框架 
		driver.switchTo().frame(1);
		String middleMsg=driver.findElement(By.tagName("p")).getText();
		assertEquals("middle", middleMsg);
		driver.close();
	}
	
	public void testIframe() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		  //首先获得父窗口 
		driver.switchTo().frame("left");
		  //取得iframe元素 
		WebElement webIframe=driver.findElement(By.tagName("iframe"));
		  //获得iframe窗口 
		driver.switchTo().frame(webIframe);
		  //验证iframe里面的页面内容 
		String actualText=driver.findElement(By.linkText("ABC")).getText();
		assertEquals("ABC", actualText);
		driver.switchTo().defaultContent();
		driver.close();
	}
	
	
	
	
}

