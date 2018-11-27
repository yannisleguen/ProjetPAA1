package Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServiceRefresh extends Service {
	String list;
	Socket client;
	public ServiceRefresh(Socket client,String list) {
		super(client);
		// TODO Auto-generated constructor stub
		this.list = list;
		this.client = client;
	}

	@Override
	public void run() {
		try {
			//BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			out.println(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
