package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Appli {
	
	/*
	 * Ce client se connecte à un serveur dont le protocole est TCP IP
	 * Le client écoute puis envoie
	 * il n'y a plusieurs échanges, jusqu'à ce que le client quitte ou se déconnecte (while true)
	 * Seul des String sont échangés entre le client et le serveur
	 */
	private final static int PORT_SERVER = 2018; 
	private final static String HOST = "localhost"; //on est en local 127.0.0.1
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket s = null;
		
		try {
			s = new Socket(HOST, PORT_SERVER);
		
			BufferedReader in = new BufferedReader (new InputStreamReader(s.getInputStream ( )));
			PrintWriter out = new PrintWriter (s.getOutputStream ( ), true);
			BufferedReader keybord = new BufferedReader(new InputStreamReader(System.in)); 
			
			//Information sur le serveur
			System.out.println("Connecté au serveur " + s.getInetAddress() + ":"+ s.getPort());
			
			String line ="";
			String sender="";
			
			//Début du process client serveur fin du cycle si line = stop 
			line = in.readLine();
			//2 . DISPLAY
			line = line.replace("jump", "\n");
			System.out.print(line);
			//3 . WRITE 
			sender = keybord.readLine();
			// 4 . SEND
			out.println(sender);
			
			line = in.readLine();
			//2 . DISPLAY
			line = line.replace("jump", "\n");
			System.out.print(line);
			
			
			
			while (!line.toUpperCase().equals("STOP") || !sender.toUpperCase().equals("STOP")) {
				//1 . LISTEN
				line = in.readLine();
				//2 . DISPLAY
				line = line.replace("jump", "\n");
				System.out.print(line);
				//3 . WRITE 
				sender = keybord.readLine();
				// 4 . SEND
				out.println(sender);
			}
			s.close();
			System.out.println("Fin de la connexion");
		
		
		} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
			System.err.println("Host inconnu");
			e1.printStackTrace();
		} catch (IOException e1) {
				// TODO Auto-generated catch block
			System.err.println("Fin de la connexion");
			e1.printStackTrace();
		}
		
		
		try { if (s != null)s.close();} 
		catch (IOException e) {e.printStackTrace();}

	}

}
