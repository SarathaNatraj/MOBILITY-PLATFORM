package lambdaexpressions;

public class LambdaMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Without Lambda Expression
		Runnable r1 = new Runnable() {
		    @Override
		    public void run() {
		        System.out.println("Hello World!");
		    }
		};
		
		// With Lambda Expression
		Runnable r2 = () -> System.out.println("Hello World!");

		Thread t1 = new Thread(r1);
		t1.start();
		
		Thread t2 = new Thread(r2);
		t2.start();
		
	}

}
