package tools;

import java.util.LinkedList;

public class User {
	private String name;
	private boolean connected;
	private boolean doNotDisturb;
	private LinkedList<Conversation> history;
	
	public User(String name) {
		this.name = name;
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

	public  LinkedList<Conversation> getL() {
		return history;
	}

	public  void setL(LinkedList<Conversation> l) {
		this.history = l;
	}
	
	public synchronized  void addConv(Conversation c) {
		this.history.add(c);
	}

	public synchronized boolean isDoNotDisturb() {
		return doNotDisturb;
	}

	public synchronized void setDoNotDisturb(boolean doNotDisturb) {
		this.doNotDisturb = doNotDisturb;
	}
	
	
	
	
}
