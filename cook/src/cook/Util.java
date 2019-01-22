package cook;


import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;

public class Util {
	public static void setAttribute(WebElement element,String attributeName,String value) {
		WrapsDriver wrappedElement=(WrapsDriver) element;
		JavascriptExecutor driver=(JavascriptExecutor) wrappedElement.getWrappedDriver();
		//JavaScript��ÿ������������һ��Arguments�����ʵ��arguments���������ź�����ʵ�Σ����ҿ����������±�ķ�ʽ������arguments��Ԫ�ء�
		driver.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element,attributeName,value);		
	}
	//static���γ�Ա�����������ã����ǿ���ʹ��"����.������"�ķ�ʽ������������������Ҫnew������ķ�������Դ����
	public  static void highlightElement(WebElement element) {
		for (int i = 0; i < 3; i++) {
			WrapsDriver wrappedElement=(WrapsDriver) element;
			JavascriptExecutor driver=(JavascriptExecutor) wrappedElement.getWrappedDriver();
			try {
				driver.executeScript("arguments[0].setAttribute('style',arguments[1])", element,"color:green;border:2px solid yellow");
				Thread.sleep(800);
				driver.executeScript("arguments[0].setAttribute('style',arguments[1])", element,"");
				Thread.sleep(400);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
	}
	
	//ҳ��Ԫ�ؽ�ͼ
	public static File captureElement(WebElement element) throws Exception {
		WrapsDriver wrapsDriver=(WrapsDriver) element;
		// ��ͼ����ҳ��
		File screen=((TakesScreenshot)wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
		BufferedImage img=ImageIO.read(screen);
		// ���Ԫ�صĸ߶ȺͿ��
		int width=element.getSize().getWidth();
		int height=element.getSize().getHeight();
		// ����һ������ʹ������ĸ߶ȣ��Ϳ��
		Rectangle rect=new Rectangle(width, height);
		// �õ�Ԫ�ص�����
		Point p=element.getLocation();
		//��Ϊpng��ʽ
		BufferedImage dest=img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
		ImageIO.write(dest, "png", screen);
		return screen;
		
	}
}
