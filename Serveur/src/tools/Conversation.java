package tools;

import java.util.ArrayList;
import java.util.Date;

public class Conversation {
	
	private ArrayList<Message> listMsg;
	private Date dateCreation;
	private Date dateLastMsg;
	private int nbPerson;
	
	public Conversation(User sender,User recipient) {
		nbPerson = 2;
		sender.setDoNotDisturb(true);
		recipient.setDoNotDisturb(true);
		listMsg = new ArrayList<Message>();
		dateCreation = new Date();
		
	}
	
	public void sendMessage(User sender,User recipient,String content) {
		Message myNewMsg = new Message(recipient,sender,content);
		dateLastMsg = myNewMsg.getDate();
		listMsg.add(myNewMsg);
	}

	public void leaveConversation(User sender,User recipient) {
		sender.setDoNotDisturb(false);
		recipient.setDoNotDisturb(false);
		nbPerson = 0;
		
	}
	

	public synchronized ArrayList<Message> getListMsg() {
		return listMsg;
	}

	public  synchronized void setListMsg(ArrayList<Message> listMsg) {
		this.listMsg = listMsg;
	}

	public synchronized Date getDateCreation() {
		return dateCreation;
	}

	public synchronized void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public synchronized Date getDateLastMsg() {
		return dateLastMsg;
	}

	public synchronized void setDateLastMsg(Date dateLastMsg) {
		this.dateLastMsg = dateLastMsg;
	}

	public synchronized int getNbPerson() {
		return nbPerson;
	}

	public synchronized void setNbPerson(int nbPerson) {
		this.nbPerson = nbPerson;
	}
	
	
}
