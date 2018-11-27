package tools;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Vector;

import Service.ServiceDemanceDiscussion;



public class User {
	private String name;
	private boolean connected;
	private boolean doNotDisturb;
	private Vector<String> history;
	private PrintWriter out;
	private BufferedReader in;
	private ServiceDemanceDiscussion msg;
	
	public User(String name, BufferedReader in, PrintWriter out,ServiceDemanceDiscussion msg) {
		this.name = name;
		this.in = in;
		this.out = out;
		this.msg = msg;
		this.history = new Vector<String>();
		//init
		
	}
	
	
	public Vector<String> getHistory() {
		return history;
	}


	public void setHistory(Vector<String> history) {
		this.history = history;
	}


	public ServiceDemanceDiscussion getMsg() {
		return msg;
	}


	public void setMsg(ServiceDemanceDiscussion msg) {
		this.msg = msg;
	}


	
	
	
	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}
	

	
	

	public synchronized String getName() {
		return name;
	}

	public synchronized  void setName(String name) {
		this.name = name;
	}

	public synchronized  boolean isConnected() {
		return connected;
	}

	public synchronized  void setConnected(boolean connected) {
		this.connected = connected;
	}

	public  Vector<String> getL() {
		return history;
	}

	public void setL(Vector<String> l) {
		this.history = l;
	}
	
	

	public synchronized boolean isDoNotDisturb() {
		return doNotDisturb;
	}

	public synchronized void setDoNotDisturb(boolean doNotDisturb) {
		this.doNotDisturb = doNotDisturb;
	}
	public synchronized void addMessage(String msg) {
		this.history.add(msg);
	}
	
	public synchronized void initHistory() {
		this.history.add("-----------------------------------------------------------");
	}
	
	
	
	
	
}
