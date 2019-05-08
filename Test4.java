//package hw7;

public class Test4 {

	public static void main(String[] args) throws ClassNotFoundException {
		
		System.out.println("the class name: "+args[0]);
		
		
		Class cl = Class.forName(args[0]); 
		System.out.println(cl.getName());
		//cl t1 = new cl();
		test t = new test();
		//A a = new A()*/;	
		MethodInfoTest.TestX(cl);
		

		MethodInfoTest.TestX(t);
		
	}

}
