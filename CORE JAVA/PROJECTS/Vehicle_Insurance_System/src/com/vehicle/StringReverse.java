package com.vehicle;

public class StringReverse {
	public String reverse(String str) {
		if(str == null) return null;
		//if(str == " ") return " ";
		return new StringBuilder(str).reverse().toString();
	}

}
