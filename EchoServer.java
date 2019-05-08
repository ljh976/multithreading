//package hw7;
/* Skeleton code for EchoServer (Assignment 7, Problem 1)
Written by Hyunyoung Lee for CSCE 314 students
*/
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
public class EchoServer implements Runnable {
// Using "static" enables these fields shared among all EchoServer instances
private static final Queue<String> requests = new LinkedList<String>();
private static Lock changeLock = new ReentrantLock();
private static Condition newMessage = changeLock.newCondition();
// Instance members for each thread
private String title;
private int id;
private int messageNo = 0;




public EchoServer(String t, int i) {
// assign instance member fields and
// start a new thread for this EchoServer instance
	title = t;
	id = i;
	
	
	new Thread(this).start();
	
	
	
}
public void echo(String s) throws InterruptedException {
// add the String s to the message queue; to do so,
// first, aquire the lock
// second, add s to requests
// third, signal every thread that is waiting on the lock, and
// finally, release the lock
// you need to use try-finally block
	changeLock.lock(); //lock
	
	try {
	requests.add(s);
	//newMessage.signal();
	newMessage.signalAll();// for p2
	//newMessage.await();
	}
	finally  {
	changeLock.unlock();
	}
	
}
public void run() {
	
for (;;) { // the threads keep adding & echoing the messages
	
if(title.equals("manager")) { // if this is manager
// aquire the lock
// check if there is a message in the queue to print out
// if so, invoke realEcho with the message removed from the queue
// if not, within a try-catch block, await on the condition to
// change such that new messages are added.
// You need to catch InterruptedException
// release the lock
	System.out.println("with manger");
	changeLock.lock();
//	System.out.println(requests.isEmpty());
	if (!requests.isEmpty()) {
		realEcho(requests.remove());//invoke realEcho remove
	}
	
	else {
		try {
		newMessage.await();
		//newMessage.signal();
		//newMessage.signalAll();
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	//realEcho(requests.poll());

	changeLock.unlock();
} 

else { // not manager, i.e., any other EchoServer instance
// insert the message to the queue by invoking echo method, e.g.,
// echo("Message " + (++messageNo) + " from " + title);
// Thread.sleep(100+500*(messageNo%id));
	
	//System.out.println("TEST");
	try {
		System.out.println("without manger");
		changeLock.lock();
		
		echo("Message " + (++messageNo) + " from " + title);
		//newMessage.signalAll();
		//realEcho(requests.poll());
	} catch (InterruptedException e) {
		//TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//newMessage.signal();
	//newMessage.signalAll();
	changeLock.unlock();
	try {
		Thread.sleep(100+500*(messageNo%id));
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
}

}

}
private void realEcho(String s) {
System.out.println("Echoing ... " + s);
}




}