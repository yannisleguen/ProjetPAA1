package tools;

import java.util.Date;

public class Message {
	private String content;
	private User sender;
	private User recipient;
	private Date date;
	
	public Message(User recipient, User sender, String content)
	{
		this.recipient = recipient;
		this.sender = sender;
		this.content = content;
		this.date = new Date();
	}
	
	public String toString() {
		String myStringuedMsg = sender+" a écrit : "+content+" le "+date;
		return myStringuedMsg;
	}

	public synchronized String getContent() {
		return content;
	}

	public synchronized void setContent(String content) {
		this.content = content;
	}

	public synchronized User getSender() {
		return sender;
	}

	public synchronized void setSender(User sender) {
		this.sender = sender;
	}

	public synchronized User getRecipient() {
		return recipient;
	}

	public synchronized void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public synchronized Date getDate() {
		return date;
	}

	public synchronized void setDate(Date date) {
		this.date = date;
	}
	
	
}
