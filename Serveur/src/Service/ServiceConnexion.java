package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import tools.User;

public class ServiceConnexion extends Service {
	Socket client;
	User currentUser;
	private static HashMap<String, User> listUser = new HashMap<String, User>();
	private static ArrayList<User> listOfConnectedUser = new ArrayList<User>();
	
	public ServiceConnexion(Socket client) {
		super(client);
		this.client = client;
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferedReader in;
		try {
			
			in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			out.println("##### Entrer votre nom d'utilisateur : ###");
			String userConnected = "";
			userConnected = in.readLine();
			System.out.println("log user = "+userConnected);
			try {
				Thread.currentThread().sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connectUser(userConnected);
			out.println(listUserConnectedToString());
			
			
			int choix;
			
			while (true) {
			out.println("##### Tapez l'action souhaitez: ### jump"+serviceToString());
			choix = Integer.parseInt(in.readLine());
			switch (choix) {
            case 1: 
            		
            	    new ServiceDiscussion(client,userConnected).run();
                    // break;
            case 2:  out.println(listUserConnectedToString());
            		 
            		 
            case 3:  new ServiceHisto(client).run();
   		 			
   		 			
            case 4: 
            		deconnectUser();
            		out.println("STOP");
            case 5: 
        		new ServiceHelloWorld(client).run();
	 			    
			}
			
			}
			
			
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String serviceToString() {
		return    "  # 1 pour entrer en discussion jump"
				+ "  # 2 pour rafraichir la liste des utilisateurs connectés jump"
				+ "  # 3 Afficher l'historique des messages envoyés jump"
				+ "  # 4 Se déconnecter  jump"
				+ "  # Ne faites rien pour que quelqu'un entre conversation avec vous  ";
		
	}
	
	public synchronized void connectUser(String user) {
		if (isUserExist(user)) {
			this.currentUser = listUser.get(user);
			currentUser.setConnected(true);
		}else {
			currentUser = new User(user);
			listUser.put(user, currentUser);
		}
		listOfConnectedUser.add(currentUser);
	
}
	
	public boolean isUserExist(String user) {
		if(listUser.containsKey(user)) {
			return true;
		}
		return false;
	}
	
	public void deconnectUser() {
		listUser.get(this.currentUser).setConnected(false);;		
	}
	public synchronized String listUserConnectedToString() {
		String result ="";
		for (User user : listOfConnectedUser) {
			result+="USER : "+user.getName()+" Occupé : "+user.isDoNotDisturb()+ "jump";
		}
		return result;
	}
	
	
}
