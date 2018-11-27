package Service;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

import tools.User;

public class ServiceDemanceDiscussion extends Service {

	
	private User currentUser;
	private User u;
	private boolean nulluser = true;
	LinkedList<User> listOfConnectedUser;
	
	
	
	public ServiceDemanceDiscussion(Socket client, User currentUser, LinkedList<User> listOfConnectedUser) {
		super(client);
		this.currentUser = currentUser;
		this.listOfConnectedUser = listOfConnectedUser;
	
		
		
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
				nulluser = false;
			
		}
		if(!nulluser) {
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
				currentUser.setDoNotDisturb(false);
				u.setDoNotDisturb(false);
				u.setFlagEndOfConv(true);
				currentUser.setFlagEndOfConv(true);
			}else if(u.isDoNotDisturb()){
				currentUser.getOut().println("User pas dispo, retour au menu");
				u.addMessage(currentUser.getName()+" a essayé de vous joindre");
			}
		}else {
			currentUser.getOut().println("User inconnu, retour au menu");
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
