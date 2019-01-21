package tester;

public class TestingThread {

	    public static void main(String[] args){
	        ThreadB b = new ThreadB();
	        b.start();
	 
	        synchronized(b){
	            try{
	                System.out.println("Waiting for b to complete...");
	                b.wait();
	            }catch(InterruptedException e){
	                e.printStackTrace();
	            }
	            while(true) {
	            System.out.println("Total is: " + b.total);
	            }
	        }
	    }
	}
	 
	class ThreadB extends Thread{
	    int total;
	    @Override
	    public void run(){
	        synchronized(this){
	            for(int i=0; i<100 ; i++){
	            	System.err.println("count " + i);
	            	try {
						sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                total += i;
	            }
	            notify();
	        }
	    }
	}

