package threads;

public class BNMIT {

	
	public static void main(String args[]){
		
		Workshop bnmit_workshop = new Workshop();
		
		new Thread(new Student("Akshay",bnmit_workshop)).start();
		new Thread(new Student("Namana",bnmit_workshop)).start();
		new Thread(new Student("Nidhi",bnmit_workshop)).start();
		new Thread(new Student("Kunal",bnmit_workshop)).start();
		new Thread(new Student("Kushal",bnmit_workshop)).start();
		new Thread(new Student("Meghana",bnmit_workshop)).start();
		new Thread(new Student("Deeksha",bnmit_workshop)).start();
		
		try {
			
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bnmit_workshop.start();
		
		
		
		
		
	}
	
}
