package jantar;

public class DinnerTableMonitor implements DinnerTable {

	private final int X;
	private final PhilosopherState[] philosopherState;
	private final Integer[] philosophers;

	public DinnerTableMonitor(int X) {
		this.philosopherState = new PhilosopherState[X];
		this.philosophers = new Integer[X];
		this.X = X;

		for (int i = 0; i < X; i++) {
			this.philosopherState[i] = PhilosopherState.THINKING;
		}
	}

	public int right(int id) {
		return (1 + id) % this.X;
	}

	public int left(int id) {
		return (this.X + id - 1) % this.X;
	}

	public void takeForks(int id) {
		synchronized (philosophers[id]) {
			philosopherState[id] = PhilosopherState.HUNGRY;
			this.test(id);
			while (philosopherState[id] != PhilosopherState.EATING) {
				try {
					this.philosophers[id].wait();
				} catch (InterruptedException e) {
					System.out.println("InterruptedException caught");
				}
			}
		}
	}

	public void putForks(int id) {
		synchronized (philosophers[id]) {
			philosopherState[id] = PhilosopherState.THINKING;
			this.test(left(id));
			this.test(right(id));
		}
	}

	private void test(int id) {
		if (philosopherState[id] == PhilosopherState.HUNGRY
				&& philosopherState[this.left(id)] != PhilosopherState.EATING
				&& philosopherState[this.right(id)] != PhilosopherState.EATING) {
			philosopherState[id] = PhilosopherState.EATING;
			System.out.println("The philosopher " + id + " took the cutlery.");
			synchronized (philosophers[id]) {
				philosophers[id].notify();
			}
		}
	}

}