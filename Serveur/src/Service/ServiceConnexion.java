package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import tools.UserChat;

public class ServiceConnexion extends Service {
	Socket client;
	static ArrayList<UserChat> listUser;
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
			connectUser(userConnected);
			out.println("##### Tapez l'action souhaitez: ### \n"+serviceToString());
			int choix = Integer.parseInt(in.readLine());
			switch (choix) {
            case 1: 
            		new ServiceDiscussion(client,userConnected).run();
                     break;
            case 2:  new ServiceRefresh(client).run();;
            		 break;
            		 
            case 3:  new ServiceHisto(client).run();
   		 			break; 
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String serviceToString() {
		return    "  # 1 pour entrer en discussion \n"
				+ "  # 2 pour rafraichir la liste des utilisateurs connectés \n"
				+ "  # 3 Afficher l'historique des messages envoyés \n"
				+ "  # 4 Se déconnecter  \n"
				+ "  # Ne faites rien pour que quelqu'un entre conversation avec vous  ";
		
	}
	
	public void connectUser(String user) {
		boolean state = false;
		for (UserChat userChat : listUser) {
			if (userChat.getName().equals(user)) {
				userChat.setConnected(true);
				state = true;
			}
			
		}
		if (!state) {
				this.listUser.add(new UserChat(user));
		}
	
}
	
	public void deconnectUser(String user) {
		for (UserChat userChat : listUser ) {
			if ( userChat.getName().equals(user)) {
				userChat.setConnected(false);
			}
			
		}
	}
	
}
