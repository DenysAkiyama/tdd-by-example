package guru.springframework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jdk.jfr.Experimental;

class FooTest {

	@Test
	void testGetBar() {
		Foo foo = new Foo();
		String result = foo.getBar();
		assertEquals("FooBar", result);

	}

}
