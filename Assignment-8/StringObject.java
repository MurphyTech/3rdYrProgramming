/**
 * David Murphy
 * 12493252
 * d.murphy53@nuigalway.ie
 */
package assign8;

import java.util.ArrayDeque;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StringObject {
	public ArrayDeque<String> que = new ArrayDeque<String>();
	private final static Lock lock = new ReentrantLock();
	final static Condition notFull = lock.newCondition();
	final static Condition notEmpty = lock.newCondition();
	

	public int maxSize = 0;
	
	public StringObject(int size){
		this.maxSize = size;
	}

	public String get() throws InterruptedException  {
		
		try{
			//LOCK THREAD
			lock.lock();
			
			if (this.que.size() <= 0){
				System.out.println("Waiting on data!!!");
				
				while (this.que.size() <= 0)
					notEmpty.await();
				
				System.out.println("Item pushed...");
			}
			System.out.println("Consuming");
			String name;
			//REMOVE FROM QUEUE
			name = que.pop();
			System.out.println(name);
			notFull.signal();
			statistic();
			return name;
		}
		finally{
			//UNLOCK THREAD
			lock.unlock();
		}
		
	}

	public void put() throws InterruptedException {
		
		try{
			//LOCK THREAD
			lock.lock();
			
			if (this.maxSize > 0){
				if (this.que.size() >= maxSize){
					System.out.println("Full");
					while (this.que.size() >= maxSize)
						notFull.await();
				}
			}
			System.out.println("Producing");
			char[] chars ="ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
			StringBuilder sb = new StringBuilder();
			Random random = new Random();
			
			for (int i=0; i<10; i++)
			{
				char c =chars[random.nextInt(chars.length)];
				sb.append(c);
			}
			//ADD TO QUEUE
			que.add(sb.toString());
			notEmpty.signal();
			statistic();
		}
		finally{
			//UNLOCK THREAD
			lock.unlock();
		}
		
	}

	//PRINT SOMETHING
	private void statistic() {
		int length = que.size();
		System.out.println("Queue Stats: There are " + length + " elements in the queue at present.");
		
	}

}
