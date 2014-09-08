package threads;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Workshop {

	private Random rand;
	private Calendar cal;
	long now = System.currentTimeMillis();
	SimpleDateFormat t = new SimpleDateFormat("hh:mm a");
	ReentrantLock lock = new ReentrantLock();

	public Workshop() {
		rand = new Random();
		cal = Calendar.getInstance();
		cal.setTimeInMillis(now);
		cal.set(cal.get(cal.YEAR),cal.get(cal.DATE), 14, 15, 00);
	}

	public void file(String name) {
		int time = rand.nextInt(1000) + 2000;
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			long x =System.currentTimeMillis();
			cal.add(Calendar.MINUTE,(int)(x-now)/100);
			cal.add(Calendar.SECOND,(int)(x-now)%100);
			System.out.println(name + " has finished filing in "+(time/100)+" min and at " +t.format(cal.getTime()));
			now=x;
		}
	}

	public void cut(String name) {
		int time = rand.nextInt(2000) + 4000;
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			long x =System.currentTimeMillis();
			cal.add(Calendar.MINUTE,(int)(x-now)/100);
			cal.add(Calendar.SECOND,(int)(x-now)%100);
			System.out.println(name + " has finished cutting in "+(time/100)+" min at " +t.format(cal.getTime()));
			now=x;
		}
	}

	public void punch(String name) {
		lock.lock();
		int time = rand.nextInt(500) + 500;
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			long x =System.currentTimeMillis();
			cal.add(Calendar.MINUTE,(int)(x-now)/100);
			cal.add(Calendar.SECOND,(int)(x-now)%100);
			System.out.println(name + " has finished punching in "+(time/100)+" min at " +t.format(cal.getTime()));
			now=x;
			lock.unlock();
		}
	}

	public synchronized void getReady() {

		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void start() {
		System.out.println("Okay you can all start now");
		this.notifyAll();

	}

}
