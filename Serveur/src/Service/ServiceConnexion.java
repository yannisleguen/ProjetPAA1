package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import tools.User;

public class ServiceConnexion extends Service {
	Socket client;
	User currentUser;
	BufferedReader in;
	PrintWriter out;
	private static HashMap<String, User> listUser = new HashMap<String, User>();
	private static ArrayList<User> listOfConnectedUser = new ArrayList<User>();
	private ConcurrentLinkedQueue<Object> goodQueue = new ConcurrentLinkedQueue<Object>();
	
	private ServiceDemanceDiscussion chat;
	
	public ServiceConnexion(Socket client) {
		super(client);
		this.client = client;
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		try {
			
			this.in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			this.out = new PrintWriter (client.getOutputStream ( ), true);
			out.println("##### Entrer votre nom d'utilisateur : ###");
			String userConnected = "";
			userConnected = in.readLine();
			System.out.println("log user = "+userConnected);
			
			connectUser(userConnected);
			out.println(listUserConnectedToString());
			
			 chat = new ServiceDemanceDiscussion(client,currentUser,listOfConnectedUser,goodQueue);
			
			Thread.currentThread().setName(currentUser.getName());
			
			
			int choix;
			out.println("##### Tapez l'action souhaitée: ### jump"+serviceToString());
			while (true){
			
			choix = Integer.parseInt(in.readLine());
			switch (choix) {
            case 1: chat.run();
                    break;
            case 2: out.println(listUserConnectedToString() + "##### Tapez l'action souhaitée: ### jump"+serviceToString());
            		break;
            case 3:  new ServiceHisto(client,currentUser).run();
            		break;
            case 4: deconnectUser();
            		out.println("STOP");
            		break;
            case 5: out.println("En attente...");
				    break;
	 			    
			}
			if (choix == 4) {
				break;
			}else if (choix == 5) {
				String line = "";
				while(true) {
					//line = in.readLine();
				}
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
				+   "# 5 Attendre une connexion  jump"
				+ "  # Ne faites rien pour que quelqu'un entre conversation avec vous  ";
		
	}
	
	public synchronized void connectUser(String user) {
		if (isUserExist(user)) {
			this.currentUser = listUser.get(user);
			currentUser.setConnected(true);
			//Thread.currentThread().setName(currentUser.getName());
		}else {
			currentUser = new User(user,in,out,chat);
			currentUser.setConnected(true);
			listUser.put(user, currentUser);
			//Thread.currentThread().setName(currentUser.getName());
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
		this.currentUser.setConnected(false);	
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
