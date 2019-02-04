package id.co.roxas.efim.common.tester.anotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


public class MyCustomAnnotationTest {
	
	String il;
	String ul;
	
	
	public MyCustomAnnotationTest(String il, String ul) {
		this.il = il;
		this.ul = ul;
	}
	
	@MyCustomAnnotation
	(idLicense = "123002", userLicense = "12092011")
	public String myString() {
		return "yos";
	}



	public static void main(String args[]) throws NoSuchMethodException, SecurityException {
		MyCustomAnnotationTest m = new MyCustomAnnotationTest("1", "2");
		//System.err.println(m.il);
		
		Class c = m.getClass();
		Method met = c.getMethod("myString");
		MyCustomAnnotation man = met.getAnnotation(MyCustomAnnotation.class);
		System.out.println(man.idLicense());
	}
	
}
