package id.co.roxas.efim.common.TestBima.Test1;

public class MyThread extends Thread{

	    MyThread() 
	    {
	        System.out.print(" MyThread");
	    }
	    public void run() 
	    {
	        System.out.print(" bar");
	    }
	    public void run(String s) 
	    {
	        System.out.println(" baz");
	    }
}
