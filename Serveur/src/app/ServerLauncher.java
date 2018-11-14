package app;

import server.Serveur;

public class ServerLauncher {
	private final static int PORT_SERVER = 2018; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Serveur blablatchat = new Serveur(PORT_SERVER);
		blablatchat.connectUser();

	}

}
