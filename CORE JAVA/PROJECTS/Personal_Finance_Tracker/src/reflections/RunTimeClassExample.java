package reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import reflections.*;

public class RunTimeClassExample {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		// TODO Auto-generated method stub
		
		
		Transaction t1 = new Transaction(1, "Salary", 5000);
		System.out.println(" Original Transaction : "+t1);
		
		Class<?> transClass = t1.getClass();
		System.out.println(" Runtime class : "+transClass);
		System.out.println(" Runtime Class name : "+ transClass.getName());

		
		//accessing  and modifying the private field
		Field amountField = transClass.getDeclaredField("amount");
		amountField.setAccessible(true);
		amountField.set(t1, 6000); //t1.setAmount(6000)
		
		
		//accessing the private method
		Method setAmountMethod = transClass.getDeclaredMethod("setAmount", double.class);
		setAmountMethod.setAccessible(true);
		setAmountMethod.invoke(t1, 7000); //t1.amount=7000
		
		System.out.println(" Updated Transaction : "+t1);
		
		
		//display all the fields
		System.out.println(" Display all the fields");
		for(Field f : transClass.getDeclaredFields()) {
			System.out.println(" Field Name : "+f.getName()+" Type : "+f.getType());
		}
		
		
		//display all the methods
				System.out.println(" Display all the methods");
				for(Method m : transClass.getDeclaredMethods()) {
					System.out.println(" Method Name : "+m.getName()+" Type : "+m.getParameterCount());
				}
	}

}
