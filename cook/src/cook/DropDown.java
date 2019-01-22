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
		//�õ������б��
		Select make=new Select(driver.findElement(By.name("make")));
		//��֤�����б�Ĳ�֧�ֶ�ѡ 
		assertFalse(make.isMultiple());
		//��֤�����б������
		assertEquals(4, make.getOptions().size());
		//ʹ�ÿɼ��ı�����ѡ��ѡ�� 
		make.selectByVisibleText("Benz");
		assertEquals("Benz", make.getFirstSelectedOption().getText());
		//ͨ��value������ѡ��ѡ��
		make.selectByValue("Audi");
		assertEquals("Audi", make.getFirstSelectedOption().getText());
		//ͨ��������ѡ��ѡ��
		make.selectByIndex(0);
		assertEquals("BMW", make.getFirstSelectedOption().getText());		
	}
	public void testMultipleSelectLis() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		//�õ������б�� 
		Select color=new Select(driver.findElement(By.name("color")));
		//��֤�����б�֧�ֶ�ѡ
		assertTrue(color.isMultiple());
		//��֤�����б������
		assertEquals(4, color.getOptions().size());
		//�ÿɼ��ı�����ѡ��ѡ��
		color.selectByVisibleText("Black");
		color.selectByVisibleText("Red");
		color.selectByVisibleText("Silver");
		//��֤��ѡ��ѡ��
		List<String> exp_sel_options=Arrays.asList(new String[] {"Black","Red","Silver"});
		List<String> act_sel_options=new ArrayList<String>();
		for(WebElement option:color.getAllSelectedOptions()) {
			act_sel_options.add(option.getText());
		}
		//��֤ѡ���ѡ���������������һ����
		assertArrayEquals(exp_sel_options.toArray(), act_sel_options.toArray());
		//��֤3��ѡ���Ѿ���ѡ����
		assertEquals(3, color.getAllSelectedOptions().size());
		//ͨ���ɼ����ı�ȡ����ѡѡ��
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
		//ʹ��valueֵ����λ��ѡ��ť
		WebElement apple=driver.findElement(By.cssSelector("input[value='Apple']"));
		//����Ƿ���ѡ�����û������ѡ��
		if (apple.isSelected()) {
			apple.click();
		}
		//��֤appleѡ���Ѿ�ѡ�� 
		assertTrue(apple.isSelected());
		//Ҳ���Եõ����еĵ�ѡ��ť 
		List<WebElement> fruit=driver.findElements(By.name("fruit"));
		//��ѯOrangeѡ���Ƿ���ڣ����������ѡ��
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
		//�ȴ�10��
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement p4button=driver.findElement(By.linkText("page4"));
		p4button.clear();
		WebElement message=driver.findElement(By.id("pageContent"));
		//�ȴ�Ajax�����ݳ��� 
		Thread.sleep(4000);
		assertTrue(message.getText().contains("Nunc nibh tortor"));
	}
	
	public void testWithImplicitWait() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		WebElement button=driver.findElement(By.linkText("RUN CODE"));
		button.click();
		//���õȴ�ʱ��Ϊ5�룬��ʾ�ȴ�
		WebDriverWait wait=new WebDriverWait(driver, 5);
		//����apply�ķ���ֵΪBoolean
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
		//����һ���µ�ExpecctedCondition�ӿڣ��ͱ���ʵ��apply����
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
		  //�ж�������ť�Ƿ���� 
		if (isElementPresent(By.id("sul"))) {
			   //�����ť 
			driver.findElement(By.id("su")).click();
		}else {
			fail("Ԫ�ز�����");
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
		  //ʹ��valueֵ����λ��ѡ��ť 
		WebElement apple=driver.findElement(By.cssSelector("input[value='Apple']"));
		  //����Ƿ���ѡ�����û������ѡ�� 
		if (!apple.isSelected()) {
			apple.click();
		}
		assertTrue(apple.isSelected());
		  //Ҳ���Եõ����еĵ�ѡ��ť 
		List<WebElement> fruit=driver.findElements(By.name("fruit"));
		for (WebElement allfruit : fruit) {
			  //��ѯOrangeѡ���Ƿ���ڣ����������ѡ�� 
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
		//���游���� 
		String parentWindowId=driver.getWindowHandle();
		 //�����ť��������
		WebElement helpButton=driver.findElement(By.id("helpbutton1"));
		helpButton.click();
		//ת��HelpWindow
		try {
			driver.switchTo().window("HelpWindow");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��֤�´�������ı� 
		assertEquals("PopUpWindow", driver.findElement(By.tagName("p")).getText());
		driver.close();
		//�ص�������
		driver.switchTo().window(parentWindowId);
		//��֤�����ڵ�title
		assertTrue(driver.getTitle().equals("help"));
		driver.close();
	}
	
	
	public void testWindos() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		  //���游���� 
		String parentWindowId=driver.getWindowHandle();
		WebElement buttone2=driver.findElement(By.id("helpbutton2"));
		buttone2.click();
		   //�õ����еĴ��� 
		Set<String> allWindowsId=driver.getWindowHandles();
		//ͨ��title�õ��µĴ���
		for (String windowId : allWindowsId) {
			if (driver.switchTo().window(windowId).getTitle().equals("PopUpWindow")) {
				driver.switchTo().window(windowId);
				break;
			}
		}
		 //��֤�´��ڵ��ı�
		assertEquals("PopUpWindow", driver.findElement(By.tagName("p")).getText());
		driver.close();
		driver.switchTo().window(parentWindowId);
		driver.close();
	}
	
	public void testAlert() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		//�����ť����alert��
		WebElement button=driver.findElement(By.id("alert"));
		button.click();
		//��ȡalert����
		Alert alertBox=driver.switchTo().alert();
		alertBox.accept();
		//��֤alert�����������
		assertEquals("Hello", alertBox.getText());
		driver.close();
	}
	
	WebDriver driver2=new ChromeDriver();
	public void testConfirm() {
		driver2.get("https://www.baidu.com/");
		//���ȷ����ť
		getConfirmBox().accept();
		//��֤����������
		assertEquals("�����ȷ����ť",driver2.findElement(By.cssSelector("span")).getText());
		//���ȡ����ť
		getConfirmBox().dismiss();
		assertEquals("�����ȡ����ť", driver2.findElement(By.cssSelector("span")).getText());
		driver2.close();
	}
	//��װ�õ����ڵķ���
	private Alert getConfirmBox() {
		WebElement button=driver2.findElement(By.id("confirm"));
		button.click();
		//��ȡȷ����ʾ��
		Alert confirmBox=driver2.switchTo().alert();
		assertEquals("����ȷ�Ͽ�", confirmBox.getText());
		return confirmBox;
	}
	
	public void testFrame() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		  //ͨ��id��λ����ߵĿ�� 
		driver.switchTo().frame("left");
		String leftMsg=driver.findElement(By.tagName("p")).getText();
		assertEquals("left",leftMsg);
		  //�ص���ʼ�Ľ��� 
		driver.switchTo().defaultContent();
		  //ͨ��name��λ���ұߵĿ�� 
		driver.switchTo().frame("right");
		String rightMsg=driver.findElement(By.tagName("p")).getText();
		assertEquals("right", rightMsg);
		driver.close();		
	}
	
	public void testFrame2() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		  //ͨ��index����λ��� 
		driver.switchTo().frame(1);
		String middleMsg=driver.findElement(By.tagName("p")).getText();
		assertEquals("middle", middleMsg);
		driver.close();
	}
	
	public void testIframe() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.baidu.com/");
		  //���Ȼ�ø����� 
		driver.switchTo().frame("left");
		  //ȡ��iframeԪ�� 
		WebElement webIframe=driver.findElement(By.tagName("iframe"));
		  //���iframe���� 
		driver.switchTo().frame(webIframe);
		  //��֤iframe�����ҳ������ 
		String actualText=driver.findElement(By.linkText("ABC")).getText();
		assertEquals("ABC", actualText);
		driver.switchTo().defaultContent();
		driver.close();
	}
	
	
	
	
}

