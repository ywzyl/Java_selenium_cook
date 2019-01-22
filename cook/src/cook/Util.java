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
		//JavaScript中每个函数都会有一个Arguments对象的实例arguments，它引用着函数的实参，而且可以用数组下标的方式来引用arguments的元素。
		driver.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element,attributeName,value);		
	}
	//static修饰成员方法最大的作用，就是可以使用"类名.方法名"的方式操作方法，避免了先要new出对象的繁琐和资源消耗
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
	
	//页面元素截图
	public static File captureElement(WebElement element) throws Exception {
		WrapsDriver wrapsDriver=(WrapsDriver) element;
		// 截图整个页面
		File screen=((TakesScreenshot)wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
		BufferedImage img=ImageIO.read(screen);
		// 获得元素的高度和宽度
		int width=element.getSize().getWidth();
		int height=element.getSize().getHeight();
		// 创建一个矩形使用上面的高度，和宽度
		Rectangle rect=new Rectangle(width, height);
		// 得到元素的坐标
		Point p=element.getLocation();
		//存为png格式
		BufferedImage dest=img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
		ImageIO.write(dest, "png", screen);
		return screen;
		
	}
}
