package com.financetracker;

public class GenerateReport  implements Runnable{

	
	@Override
	public void run() {
		try {
		//Running state
		// TODO Auto-generated method stub
		
		
		for (int i = 0; i < 5; i++) {
			
			Thread.sleep(1000);
			System.out.println(" Inside run of Gen Report 1");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
