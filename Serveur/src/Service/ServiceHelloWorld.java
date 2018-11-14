package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServiceHelloWorld implements Runnable{

	private Socket client;
	
	public ServiceHelloWorld(Socket socket) {
		// TODO Auto-generated constructor stub
		client = socket;
		
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			out.println("Hello world");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
