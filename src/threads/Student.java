package threads;

public class Student implements Runnable {

	private String name;
	private Workshop w;
	private static String punching;
	private static boolean changed=false;

	public Student(String name, Workshop workshop) {
		this.name = name;
		this.w = workshop;
	}

	@Override
	public void run() {
		int waiting = 1;
		this.w.getReady();
		this.w.file(this.name);
		this.w.cut(this.name);
		while (waiting > 0) {
			if (this.w.lock.isLocked() && (changed || waiting==1)){
				System.out.println(this.name + " is waiting in queue for "+this.punching);
				waiting++;
				changed=false;
			}
			else if(this.w.lock.tryLock()){
				this.punching=this.name;
				this.w.punch(this.name);
				waiting = 0;
				this.w.lock.unlock();
				changed=true;
			}
		}
		System.out.println(this.name + " has finished and is going home");
	}

}
