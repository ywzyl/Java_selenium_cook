package cook;

import static org.junit.Assert.assertEquals;

public class BmiCalculatorTest {
	public void testBmi() {
		BmiCalculatePage_div bcp=new BmiCalculatePage_div();
		 //��ҳ��
		bcp.load();
		 //����BMI
		bcp.calculateBmi("180", "80");
		//��֤BMIֵ 
		assertEquals("24.7", bcp.getBmi());
		//��֤����
		assertEquals("Normal", bcp.getBmiCategory());
		 //�ر�ҳ��
		bcp.close();
	}
}
