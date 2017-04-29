package calc;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
// test 101
public class CalculatorTest {
	private static Calculator calculator;
//Testing comment --VM:Testing
	@BeforeClass
	public static void setup() throws Exception {
		calculator = new Calculator();
	}

	@Test
	public void testAdd() throws Exception {
		int result = 5;
		int calResult = calculator.add(2, 3);
		assertEquals(result, calResult);
	}

	@Test
	public void checkNegativeNumber() throws Exception {
		try {
			calculator.add(2, -3);
		} catch (NegativeNumberException e) {
			assertEquals("y is less then zero", e.getMessage());
		}
	}
}