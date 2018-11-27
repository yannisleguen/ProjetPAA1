package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.sql.Timestamp;

import tools.User;

public class ThreadDiscussion extends Thread {

	private User u1;
	private User u2;
	private BufferedReader in;
	private PrintWriter out;
	
	
	public ThreadDiscussion(User u1, User u2) {

	
		this.u1 = u1;
		this.u2 = u2;
		this.in = u1.getIn();
		this.out = u2.getOut();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		String message = "";
		boolean end = false;
		u1.addMessage("Conversation avec " + u2.getName() + " démarre...");
		u2.addMessage("Conversation avec " + u1.getName() + " démarre...");
		do {
			try {
				message = in.readLine();
				if (message.equals("stop")) {
					message = u1.getName()+ " a quitté la conversation";
					end = true;
					
				}
				
				out.println(message);
				String msg = u1.getName()+ " à " + u2.getName() + " / " + new Timestamp(System.currentTimeMillis()).toString() + " / " + message;
				u1.addMessage(msg);
				u2.addMessage(msg);
			} catch (SocketException e) {
				out.println("Retour home");
				end = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!end);
		
	}
	
	

	

}
