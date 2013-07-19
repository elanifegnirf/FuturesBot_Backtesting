

public class Run_thread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			NewDdeClient_Thread client1 = new NewDdeClient_Thread(10);
			client1.start();
			Thread.sleep(1000);
			NewDdeClient_Thread client2 = new NewDdeClient_Thread(12);
			client2.start();
			Thread.sleep(1000);
			NewDdeClient_Thread client3 = new NewDdeClient_Thread(14);
			client3.start();
			Thread.sleep(1000);
			NewDdeClient_Thread client4 = new NewDdeClient_Thread(16);
			client4.start();
			Thread.sleep(1000);
			NewDdeClient_Thread client5 = new NewDdeClient_Thread(18);
			client5.start();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
