package thread;

import java.io.BufferedReader;
import java.io.IOException;

public class RecieverThread implements Runnable {

	private BufferedReader in;
	
	public RecieverThread(BufferedReader in) {
		this.in = in;
	}
	
	@Override
	public void run() {
		String request = "";
		try {
			while (!request.equals("STOP")) {
				try {
					request = in.readLine();
					request = request.replace("jump", "\n");
					System.out.println(request);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		catch (NullPointerException e) {
			
		}
		
	}
	
	public void runGetMessage() {
		new Thread(this).start();
	}
}
