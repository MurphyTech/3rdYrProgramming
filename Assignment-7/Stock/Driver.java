/**
 * David Murphy
 * d.murphy53@nuigalway.ie
 * 12493252
 */
package stock;

public class Driver implements Runnable{
	
	
	private StockRoom stock;
	public static int _uid =0; //User ID, 
	public String name;
	
	//Create new StockRoom w/ store ref
	public Driver(StockRoom store){
		this.name = "Driver #" + (++_uid);
		stock = store;
	}
	
	public void addToStock(String item){
		System.out.println(String.format("%s is adding '%s' to the stock room.", this.name, item));
		stock.addToStock(item);
	}
	
	public void remove(String item){
		System.out.println(String.format("%s is removing '%s' from the stock room", this.name, item));
		stock.remove(item);
	}
	
	public void checkStock(String item){
		System.out.println(String.format("%s is checking on '%s' in the stock room", this.name, item));
		stock.checkStock(item);
	}
	
	public void doSomething(){
		System.out.println("Crap, BOSS is here! Quick, Do Something!!!");
		String item = this.getRandomStockItem();
		System.out.println("Got Stock: " + this.name);
		
		this.addToStock(item);
		System.out.println("Adding");
		
		//remove or check
		if (Math.random() > 0.5){
			this.remove(item);
			System.out.println("Removing");
		}
		else {
			this.checkStock(item);
			System.out.println("Checking");
		}
	}
	

	@Override
	public void run() {
		
		while(true){
			try{
				Thread.sleep(500);	
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			System.out.println("Calling 'doSomething' ");
			doSomething();
		}
		
	}
	
	public static String _alphaNuma = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	public int _count = 0;
	public int _hits = 0;
	public String[] _stock = new String[300];

	//Generate Random stock item
		private String getRandomStockItem() {
			if (_hits % 14 == 0 && _count < _stock.length){
				System.out.println("Reurn opt1");
				return String.format("%s%s-%d", _alphaNuma.charAt(Main.random(0,36)), _alphaNuma.charAt(Main.random(0,36)), Main.random(0,256));
			}
			else{
				System.out.println("Reurn opt2");
				return _stock[Main.random(0, _count)];
			}	
		}
}



//Unnecessary
	/*public void main(String args[]){
		System.out.println("Starting main Thread...");
		StockRoom test = new StockRoom();
		Thread t = new Thread(test);
		t.start();
		System.out.println("End of main Thread...");
		//04/11/15
		for (i=0; i<10; i++){
			//Create some threads
			Thread thread = new Thread(new Driver(room))
		}
		
		
		//StockRoom thread1 =;
		//Thread thread1 = new Thread(new StockRoom());
		//
		//thread1.start();
		//thread1.setStock(5);
		//(new StockRoom("Test")).start();
	}*/



