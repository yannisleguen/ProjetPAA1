package server;

import java.io.IOException;
import java.net.ServerSocket;

import Service.ServiceConnexion;

public class Serveur implements Runnable {
	private ServerSocket listen_socket;
	
	
	// Cree un serveur TCP - objet de la classe ServerSocket
	public Serveur(int port) {
		try {
			listen_socket = new ServerSocket(port);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
}
	// Le serveur ecoute et accepte les connections.
	// pour chaque connection, il cree un ServiceInversion, 
	// qui va la traiter.
	@Override
	public void run() {
		try {
			while(true)
				new ServiceConnexion(listen_socket.accept()).start();
		}
		catch (IOException e) { 
			try {this.listen_socket.close();} catch (IOException e1) {}
			System.err.println("Pb sur le port d'écoute :"+e);
		}
}

		
	
}
