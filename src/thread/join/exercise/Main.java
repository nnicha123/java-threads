package thread.join.exercise;

import java.math.BigInteger;

public class Main {
	public static void main(String[] args) {
		ComplexCalculation cal = new ComplexCalculation();
		BigInteger result = cal.calculateResult(BigInteger.valueOf(10), BigInteger.valueOf(2), BigInteger.valueOf(20), BigInteger.valueOf(2));
		System.out.println("result: " + result);
		
	}
	
	private static class ComplexCalculation {
		
		public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) {
			BigInteger result;
			PowerCalculatingThread thread1 = new PowerCalculatingThread(base1, power1);
			PowerCalculatingThread thread2 = new PowerCalculatingThread(base2, power2);
			
			thread1.start();
			thread2.start();
			
			try {
				thread1.join();
				thread2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			result = thread1.getResult().add(thread2.getResult());
			
			return result;
		}
		
		private static class PowerCalculatingThread extends Thread {
			private BigInteger result = BigInteger.ONE;
			private BigInteger base;
			private BigInteger power;
			
			public PowerCalculatingThread(BigInteger base, BigInteger power) {
				this.base = base;
				this.power = power;
			}
			
			@Override
			public void run() {
				result = BigInteger.ONE;
				
				for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
					if(Thread.currentThread().isInterrupted()) {
						System.out.println("Prematurely interrupted computation");
						result = BigInteger.ZERO;
					}
					result = result.multiply(base);
				}

			}
			
			public BigInteger getResult() {
				return result;
			}
		}
		
	}
}