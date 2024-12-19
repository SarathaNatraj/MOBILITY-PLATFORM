package com.vehicle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class CalculatorTest {
	
	Calculator c= new Calculator();
	@Before  // Run before each test in class
	public void setUp() {
		
	}


	@Test
	void testAdd() {
		//Arrange
		
		
		//Action
		int result = c.add(2, 3);
		
		//Assertion 
		assertEquals(5,result); //result = 5
	}
	
	@Test
	void testAddOther() {
		//Arrange
		
		
		//Action
		int result = c.add(-2, 3);
		
		//Assertion 
		assertEquals(-1,result); //result = 5
	}
	
	
	@Test
	void testSub() {
		//Arrange
	//	Calculator c = new Calculator();
		
		//Action
		int result = c.sub(2, 3);
		
		//Assertion 
		assertEquals(-1,result); //result = 5
	}
	
	
	@Test
	void testDiv() {
		//Arrange
		//Calculator c = new Calculator();
		
		//Action
	//	int result = c.divide(5, 0);
		
		//Assertion 
		assertThrows(Exception.class, ()-> c.divide(5, 0));
	}
	

}
