package thread;

import java.io.PrintWriter;
import java.util.Scanner;

public class SenderThread implements Runnable {

	private PrintWriter out;
	
	public SenderThread(PrintWriter out) {
		this.out = out;
	}
	
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		String reponse = "";
		while (!reponse.equals("STOP")) {
			reponse = sc.nextLine();
			out.println(reponse);
		} 
		sc.close();
	}	
	
	public void runSendMessage() {
		new Thread(this).start();
	}
}
