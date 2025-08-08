package messaging.juvenile;

import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeRequest;

public class SaveJuvenileTattooAndScarsEvent extends CompositeRequest{
	private String juvenileId;
	private String otherTattooComments;
	private String[] tattoos;
	private String[] scars;
	private Date entryDate;
	
	public String getJuvenileId() {
		return juvenileId;
	}
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	public String getOtherTattooComments() {
		return otherTattooComments;
	}
	public void setOtherTattooComments(String otherTattooComments) {
		this.otherTattooComments = otherTattooComments;
	}
	public String[] getTattoos() {
		return tattoos;
	}
	public void setTattoos(String[] tattoos) {
		this.tattoos = tattoos;
	}
	public String[] getScars() {
		return scars;
	}
	public void setScars(String[] scars) {
		this.scars = scars;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

}
