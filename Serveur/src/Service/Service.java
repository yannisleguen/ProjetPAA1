package Service;

import java.net.Socket;

public abstract class Service implements Runnable{
	private Socket client;
	
	public Service (Socket client) {
		this.client = client;
	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}
	
	// lancement du service
		public void start() {
			(new Thread(this)).start();		
	}
	
}
