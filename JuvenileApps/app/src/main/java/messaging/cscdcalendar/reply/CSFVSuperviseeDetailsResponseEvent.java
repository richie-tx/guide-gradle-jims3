package messaging.cscdcalendar.reply;

import mojo.km.messaging.ResponseEvent;

public class CSFVSuperviseeDetailsResponseEvent extends ResponseEvent {

	private String addressDesc;

	private String comments;

	private String caution;
	
	private String superviseeId;

	public String getSuperviseeId() {
		return superviseeId;
	}

	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}

	public String getAddressDesc() {
		return addressDesc;
	}

	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCaution() {
		return caution;
	}

	public void setCaution(String caution) {
		this.caution = caution;
	}

	
	
}
