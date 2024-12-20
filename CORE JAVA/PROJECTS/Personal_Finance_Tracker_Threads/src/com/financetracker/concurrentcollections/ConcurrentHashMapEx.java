package com.financetracker.concurrentcollections;

import java.time.LocalTime;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(" Current Time : "+LocalTime.now());
		
		ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();
		map.put("One", 1);
		map.put("Two", 2);
		map.forEach((key, value) -> System.out.println(key + ": " + value));
		
		


	}

}
