package jantar;

import java.util.concurrent.Semaphore;

public class DinnerTableSemaphore implements DinnerTable {

	private Semaphore mutex;
	private PhilosopherState[] philosopherState;
	private Semaphore[] s;
	private int X;

	public DinnerTableSemaphore(int X) {
		this.mutex = new Semaphore(1);
		this.philosopherState = new PhilosopherState[X];
		this.s = new Semaphore[X];
		this.X = X;

		for (int i = 0; i < X; i++) {
			this.philosopherState[i] = PhilosopherState.THINKING;
			this.s[i] = new Semaphore(0);
		}
	}

	public int right(int id) {
		return (1 + id) % this.X;
	}

	public int left(int id) {
		return (this.X + id - 1) % this.X;
	}

	public void takeForks(int id) {
		try {
			this.mutex.acquire();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}

		philosopherState[id] = PhilosopherState.HUNGRY;
		this.test(id);

		this.mutex.release();

		try {
			s[id].acquire();
			System.out.println(("The philosopher" + id + " took the cutlery."));
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}
	}

	public void putForks(int id) {
		try {
			this.mutex.acquire();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}

		philosopherState[id] = PhilosopherState.THINKING;
		this.test(left(id));
		this.test(right(id));

		this.mutex.release();
	}

	private void test(int id) {
		if (philosopherState[id] == PhilosopherState.HUNGRY
				&& philosopherState[this.left(id)] != PhilosopherState.EATING
				&& philosopherState[this.right(id)] != PhilosopherState.EATING) {
			philosopherState[id] = PhilosopherState.EATING;
			s[id].release();
		}
	}

}