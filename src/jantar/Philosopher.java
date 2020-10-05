package jantar;

public class Philosopher implements Runnable {

	private int eatingTime;
	private DinnerTable dinner;
	private int thinkingTime;
	private int id;

	public Philosopher(int id, int eatingTime, int thinkingTime, DinnerTable dinner) {
		this.id = id;
		this.eatingTime = eatingTime;
		this.thinkingTime = thinkingTime;
		this.dinner = dinner;
		new Thread((Runnable) this, "Philosopher" + id).start();
	}

	@Override
	public void run() {
		while (true) {
			thinking();
			takeForks(id);
			eating();
			putForks(id);
		}
	}

	private void thinking() {
		try {
			System.out.println(String.format("The philosopher(%d) is thinking.", this.id));
			Thread.sleep(this.thinkingTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void takeForks(int id) {
		System.out.println(String.format("Philosopher (%d) is trying get two forks", this.id));
		dinner.takeForks(id);
	}

	private void eating() {
		try {
			System.out.println(String.format("The philosopher(%d) is eating.", this.id));
			Thread.sleep(this.eatingTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void putForks(int id) {
		System.out.println("Philosopher " + id + " is return the forks");
		dinner.putForks(id);
	}

}