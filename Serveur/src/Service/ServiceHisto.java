package Service;

import java.net.Socket;
import java.util.Vector;

import tools.User;

public class ServiceHisto extends Service {

	User c;
	private Vector<String> history;

	public ServiceHisto(Socket client,User c) {
		super(client);
		this.c = c;
		history = c.getHistory();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		c.getOut().println(historyToString());

	}
	
	public synchronized String historyToString() {
		String lines ="";
		for (String string : history) {
			lines+= string +"jump";
		}
		return lines;
	}

}
