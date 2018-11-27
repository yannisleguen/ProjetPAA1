package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import thread.RecieverThread;
import thread.SenderThread;

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
				//Information sur le serveur 
				
				System.out.println("Connecté au serveur " + s.getInetAddress() + ":"+ s.getPort());
				BufferedReader in = new BufferedReader (new InputStreamReader(s.getInputStream ( )));
				PrintWriter out = new PrintWriter (s.getOutputStream ( ), true);
				
				new RecieverThread(in).runGetMessage();
				new SenderThread(out).runSendMessage();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			
			
		
		
			
			

	}

}
