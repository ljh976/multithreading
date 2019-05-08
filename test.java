//package hw7;

public class test {


public String test1() { return "Test1 should not output"; }
public static String test2() { return "Test2 should not output"; }
public static boolean test3(int i, String[] ss, Double d) {
  return false; }
public static boolean test4() { return false; } // failed: test4 
public static boolean test5() { return true; } // ok: test5
public boolean test6() { return false; }
private boolean test7() { return false; }
private boolean test8(int i) { return true; }
public boolean test9() { return true; }
public static int test10() { return 1; }
public static boolean test11() { return false; } // failed: test11 
public static boolean test12() { return true; } // ok: test12

}
