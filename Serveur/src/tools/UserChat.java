package tools;

import java.util.LinkedList;

public class UserChat {
	private String name;
	private boolean connected;
	private LinkedList<Conversation> l;
	
	public UserChat(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public LinkedList<Conversation> getL() {
		return l;
	}

	public void setL(LinkedList<Conversation> l) {
		this.l = l;
	}
	
	public void addMsg(Conversation c) {
		this.l.add(c);
	}
	
	
	
	
}
