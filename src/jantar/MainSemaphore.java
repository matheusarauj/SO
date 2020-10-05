package jantar;

public class MainSemaphore {
	private static int PHILOSOPHERS = 6;
	private static int[] THINKING_TIME = { 100, 100, 100, 100, 100, 100 };
	private static int[] EATING_TIME = { 100, 100, 100, 100, 100, 100 };

	public static void main(String[] args) {
		DinnerTable dinner = new DinnerTableSemaphore(PHILOSOPHERS);

		for (int i = 0; i < PHILOSOPHERS; i++) {
			new Philosopher(i, EATING_TIME[i], THINKING_TIME[i], dinner);
		}
	}

}
