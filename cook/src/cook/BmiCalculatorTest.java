package cook;

import static org.junit.Assert.assertEquals;

public class BmiCalculatorTest {
	public void testBmi() {
		BmiCalculatePage_div bcp=new BmiCalculatePage_div();
		 //打开页面
		bcp.load();
		 //计算BMI
		bcp.calculateBmi("180", "80");
		//验证BMI值 
		assertEquals("24.7", bcp.getBmi());
		//验证分类
		assertEquals("Normal", bcp.getBmiCategory());
		 //关闭页面
		bcp.close();
	}
}
