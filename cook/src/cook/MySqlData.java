package cook;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(value=Parameterized.class)
public class MySqlData {
	private WebDriver driver;
	private String height;
	private String weight;
	private String bmi;
	private String bmiCategory;
@Parameters
public static Collection<String[]> testData() throws SQLException{
	List<String[]> records=new ArrayList<String[]>();
	//连接数据库
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","");
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("select * from testdata");
	//取得结果集
	ResultSetMetaData rsMetaData=rs.getMetaData();
	//取得例数
	int cols=rsMetaData.getColumnCount();
	while (rs.next()) {
		String fileds[]=new String[cols];
		int col=0;
		for (int colIdx = 0; colIdx <=cols; colIdx++) {
			fileds[col]=rs.getString(colIdx);
			col++;
		}
		records.add(fileds);
	}
	rs.close();
	st.close();
	con.close();
	return records;
}
public MySqlData(String height, String weight, String bmi, String bmiCategory) {
	super();
	this.height = height;
	this.weight = weight;
	this.bmi = bmi;
	this.bmiCategory = bmiCategory;
}
@Before
public void setUp() {	
	driver=new ChromeDriver();
	driver.get("d:\\demo\\BMICalculator.html");
}
@Test
public void testBMICal() {
	WebElement heightField=driver.findElement(By.name("heightCMS"));
	heightField.sendKeys(height);
	WebElement weightField=driver.findElement(By.name("weightKg"));
	weightField.sendKeys(weight);
	WebElement calcButton=driver.findElement(By.id("calculate"));
	calcButton.click();
	WebElement bmiLabel=driver.findElement(By.name("bmi"));
	assertEquals(bmi, bmiLabel.getAttribute("value"));
	WebElement bmiCategoryLabel=driver.findElement(By.name("bmi_category"));
	assertEquals(bmiCategory, bmiCategoryLabel.getAttribute("value"));
}

@After
public void tearDown() {
	driver.close();
}
}
