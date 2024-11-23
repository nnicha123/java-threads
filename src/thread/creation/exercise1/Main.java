package thread.creation.exercise1;

import java.util.ArrayList;
import java.util.List;

public class Main {
		
	public static void main(String[] args) {
//		Code to initialise and run tasks
	}
	
	public class MultiExecutor {
		
		private final List<Runnable> tasks;
		
		public MultiExecutor(List<Runnable> tasks) {
			this.tasks = tasks;
		}
		
		public void executeAll() {
			List<Thread> threads = new ArrayList<>(tasks.size());
			
			for(Runnable task:tasks) {
				Thread thread = new Thread(task);
				threads.add(new Thread(thread));
			}
			
			for(Thread thread:threads) {
				thread.start();
			}
		}
	}
	
}