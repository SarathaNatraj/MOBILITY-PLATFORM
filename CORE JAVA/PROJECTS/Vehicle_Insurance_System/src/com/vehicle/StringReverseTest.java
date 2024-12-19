package com.vehicle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringReverseTest {

	@Test
	void testReverse() {
		//fail("Not yet implemented");
		
		StringReverse str = new StringReverse();
		
		assertEquals("olleh", str.reverse("hello"));
		assertNull(str.reverse(null));
		//assertNull(" ",str.reverse(" "));
	}

}
