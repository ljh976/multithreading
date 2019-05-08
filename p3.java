//package hw7;
import java.lang.Class;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

class MethodInfoTest
{
	static Class p4c; 
	Object p4o;
	static String s;
	// classes
/*	public static class A 
	{
  		void foo(int T1, char T2) { }
  		int bar(int T1, long T2, float T3) { return 1; }
  		static double doo() {
  			return 0;
  		}
	}*/
	
	// functions
	static void displayMethodInfo(Object obj)
	{
		// foo (A, T1, T2) -> void

		Class c = obj.getClass();
		
		Method methodsList[] = c.getDeclaredMethods();
		for (Method m : methodsList)
		{
			
			// method's name
			System.out.print(m.getName() + "(");
			
			// method's class
			System.out.print(m.getDeclaringClass().toString());
			
			// list of types for method
			Type[] typesList = m.getGenericParameterTypes();
			for (Type t : typesList)
			{
				System.out.print(", " + t.toString());
			}
			
			// method's return type
			System.out.print(") -> " + m.getGenericReturnType().toString());
			System.out.println();
			
		}
	}
	

	
	@SuppressWarnings("unchecked")
	public static void TestX (Object obj) throws ClassNotFoundException, IllegalArgumentException {
		p4c =  obj.getClass();
		List<String> strs = new ArrayList<String>();
		
		Method[] methods = p4c.getMethods();
		//System.out.println(methods.length);
		
		StringBuffer sb = new StringBuffer();
		
		for( Method method : methods ){
	
			if (isTestable(method)) {
			sb.append(method.getName());
			strs.add(method.getName());
			// print method parameters
			Class<?>[] argTypes = method.getParameterTypes();
			sb.append("(");
			int size = argTypes.length;
			for( Class<?> argType : argTypes ){
				String argName = argType.getName();
				sb.append(argName + " val");
				if( --size != 0 ){
					sb.append(", ");
				}
			}
			sb.append(")");

			// print return types of methods
			Class<?> returnType = method.getReturnType();
			sb.append(" : " + returnType.getName());

			System.out.println(sb);
			sb.setLength(0);
			
			// invoking the method
			
		try {
			System.out.println(method.invoke(null));
			if ((boolean) method.invoke(null) == true)
			System.out.println("OK: "+method.getName() + " succeeded");
			else System.out.println("FAILED: "+ method.getName() + " failed");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
			
		}
		
	}
	

	private static boolean isTestable(Method m) {
		
		//System.out.println("isTestable working.");
		try { 
		int mods = m.getModifiers();
		if (Modifier.isPublic(mods) && Modifier.isStatic(mods) &&
			(m.getParameterTypes().length==0)&&(m.getReturnType().toString().equals("boolean"))
			&& (m.getName().length()>3) && (m.getName().substring(0,4).equals("test"))) 
		{
		return true;	
		}
		} catch (Exception e) {
			System.out.println("Exception occurred while method test conditions " + m.getName());
			
		} return false;
		
	}

}

