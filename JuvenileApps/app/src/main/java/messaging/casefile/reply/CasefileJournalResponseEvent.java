package messaging.casefile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class CasefileJournalResponseEvent extends ResponseEvent implements Comparable {

	private Date date;
	private String subject;
	private String text;
	private String updateComments;
	private String createdBy;
	private String time;
		
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int compareTo(Object o) {
		return 0;
	}
	public String getTime()
	{
	    return time;
	}
	public void setTime(String time)
	{
	    this.time = time;
	}
	
	public String getUpdateComments() {
		return updateComments;
	}
	public void setUpdateComments(String updateComments) {
		this.updateComments = updateComments;
	}
}
