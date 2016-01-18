/**
 * David Murphy
 * 12493252
 * d.murphy53@nuigalway.ie
 */
package assign8;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FIFO {
	public static ExecutorService pool;
	public static StringObject queue;
	public static Random random = new Random();
	public static int producercount = 20;
	public static int consumercount = 20;
	public static Producer[] producers = new Producer[producercount];
	public static Consumer[] consumers = new Consumer[consumercount]; 
	public static int maxsize = 4;

	public static void main(String[] args){
		//CREATE FIFO QUEUE
		queue = new StringObject(maxsize);
		//CREATE THREAD POOL
		pool = Executors.newFixedThreadPool(random.nextInt(50));
		//CREATE PRODUCER THREADS
		System.out.println("Starting Producer Threads");
		for (int i=0; i<producercount; i++){
			producers[i] = new Producer(i, queue);
		}
		//CREATE CONSUMER THREADS
		System.out.println("Starting Consumer Threads");
		for (int i=0; i<consumercount; i++){
			consumers[i] = new Consumer(i, queue);
		}
		
		int iter = 0;
		while (++iter < 20){
			//PICK AT RANDOM
			pool.submit(producers[random.nextInt(producers.length)]);
			pool.submit(consumers[random.nextInt(consumers.length)]);
		}
		
		System.out.println("Tasks gone");
		System.out.println("Shutting down thread pool!!!");
		
		pool.shutdown();
	}
	
}
