package test.ex9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ex9.TemperatureConverter;

//testing junit inside spring container preffered in industry

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:app-ctx9.xml")
public class testTemperature2 {
	@Autowired
	private TemperatureConverter tc;
	
	@Test
	public void testFtoc() {
		int c = tc.ftoc(102);
		Assertions.assertEquals(38,	c);
	}
	@Test
	public void testcTof() {
		int f = tc.ctof(36);
		Assertions.assertEquals(96,	f);
	}
}
