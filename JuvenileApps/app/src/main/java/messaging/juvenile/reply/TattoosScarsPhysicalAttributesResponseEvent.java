package messaging.juvenile.reply;

import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

public class TattoosScarsPhysicalAttributesResponseEvent extends ResponseEvent{
	
	private Collection Scars;
	private Collection otherTattooComments;
	private Collection tattoos;
	private String tattooEntryDate;
	private String scarEntryDate;
	
	
	public Collection getScars() {
		return Scars;
	}
	public void setScars(Collection scars) {
		Scars = scars;
	}
	public Collection getOtherTattooComments() {
		return otherTattooComments;
	}
	public void setOtherTattooComments(Collection otherTattooComments) {
		this.otherTattooComments = otherTattooComments;
	}
	public Collection getTattoos() {
		return tattoos;
	}
	public void setTattoos(Collection tattoos) {
		this.tattoos = tattoos;
	}
	public String getTattooEntryDate() {
		return tattooEntryDate;
	}
	public void setTattooEntryDate(String tattooEntryDate) {
		this.tattooEntryDate = tattooEntryDate;
	}
	public String getScarEntryDate() {
		return scarEntryDate;
	}
	public void setScarEntryDate(String scarEntryDate) {
		this.scarEntryDate = scarEntryDate;
	}


}
