package server;

import java.io.IOException;
import java.net.ServerSocket;

import Service.ServiceConnexion;

public class Serveur {
	private ServerSocket listen_socket;
	
	
	// Cree un serveur TCP - objet de la classe ServerSocket
	public Serveur(int port) {
		try {listen_socket = new ServerSocket(port);
		System.out.println("### SERVEUR BIEN LANCÉ SUR "
		+listen_socket.getInetAddress().toString()+":"+port+" ###");} 
		catch (IOException e) {
			System.err.println("### IMPOSSIBLE DE LANCER LE SERVEUR ###");
			throw new RuntimeException(e);
		}
		
	}
	
	public void connectUser() {
		try {
			while(true)
				new ServiceConnexion(listen_socket.accept()).start();
		}
		catch (IOException e) { 
			try {this.listen_socket.close();} catch (IOException e1) {}
			System.err.println("Pb sur le port d'écoute :"+e);
		}
	}
	// Le serveur ecoute et accepte les connections.
	// pour chaque connection, il cree un ServiceConnexion, 
	// qui va la traiter.
	
		
		
	
}
