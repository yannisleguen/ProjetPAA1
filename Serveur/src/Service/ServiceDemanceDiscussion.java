package Service;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;


import tools.User;

public class ServiceDemanceDiscussion extends Service {

	private Socket client;
	private User currentUser;
	private User u;
	ArrayList<User> listOfConnectedUser;
	ConcurrentLinkedQueue<Object> goodQueue;
	
	
	public ServiceDemanceDiscussion(Socket client, User currentUser, ArrayList<User> listOfConnectedUser, ConcurrentLinkedQueue<Object> goodQueue) {
		super(client);
		this.client = client;
		this.currentUser = currentUser;
		this.listOfConnectedUser = listOfConnectedUser;
		this.goodQueue = goodQueue;
		
		
	}

	@Override
	public void run() {
		String choix ="";
		currentUser.getOut().println("Choisissez à qui parler :jump"+listUserConnectedToString());
		try {
			choix = currentUser.getIn().readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (User user : listOfConnectedUser) {
			if (user.getName().equals(choix)) {
				u = user;
			}
		}
		synchronized (this) {
			if (!u.isDoNotDisturb()) {
				currentUser.setDoNotDisturb(true);
				u.setDoNotDisturb(true);
				//u.getMsg().goodQueue.poll();
				u.getOut().println("Conversation avec " + currentUser.getName() + " démarre...");
				currentUser.getOut().println("Conversation avec " + u.getName() + " démarre...");
				
				ThreadDiscussion t1 = new ThreadDiscussion(currentUser, u);
				ThreadDiscussion t2 = new ThreadDiscussion(u,currentUser);
	
				t1.start();
				t2.start();
				
				try {
					t1.join();
					t2.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				t1.interrupt();
				t2.interrupt();
				
				
				
			}else
			{
				currentUser.getOut().println("User pas dispo");
			}
		}
		
	}
	
	public synchronized String listUserConnectedToString() {
		String result ="";
		for (User user : listOfConnectedUser) {
			if (user.isConnected())
			result+="USER : "+user.getName()+" Occupé : "+user.isDoNotDisturb()+ "jump";
		}
		return result;
	}

}
