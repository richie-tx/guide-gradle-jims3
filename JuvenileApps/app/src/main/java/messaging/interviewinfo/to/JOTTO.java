/*
 * Created on Jan 18, 2008
 *
 */
package messaging.interviewinfo.to;

import java.util.List;

/**
 * @author awidjaja
 * This class is used to communicate information between PD - UI - UJAC, 
 * mainly for Conduct Interview - Parental Rights (HireAttorney) print template.
 * Each JOT Record will have a list of JOTCharges associated to it, as well as 
 * other information about the event (date/time, juvenileCoActors, adultCoActors, 
 * propertyLoss).
 */
public class JOTTO {
	private List jotCharges;
	private String dateTakenIntoCustody;
	private String timeTakenIntoCustody;
	private String juvenileCoActors;
	private List adultCoActors;
	private List propertyLossList;
		
	public List getAdultCoActors() {
		return adultCoActors;
	}
	public void setAdultCoActors(List adultCoActors) {
		this.adultCoActors = adultCoActors;
	}
	public List getPropertyLossList() {
		return propertyLossList;
	}
	public void setPropertyLossList(List propertyLossList) {
		this.propertyLossList = propertyLossList;
	}
	public String getDateTakenIntoCustody() {
		return dateTakenIntoCustody;
	}
	public void setDateTakenIntoCustody(String dateTakenIntoCustody) {
		this.dateTakenIntoCustody = dateTakenIntoCustody;
	}
	public List getJotCharges() {
		return jotCharges;
	}
	public void setJotCharges(List jotCharges) {
		this.jotCharges = jotCharges;
	}
	public String getJuvenileCoActors() {
		return juvenileCoActors;
	}
	public void setJuvenileCoActors(String juvenileCoActors) {
		this.juvenileCoActors = juvenileCoActors;
	}
	public String getTimeTakenIntoCustody() {
		return timeTakenIntoCustody;
	}
	public void setTimeTakenIntoCustody(String timeTakenIntoCustody) {
		this.timeTakenIntoCustody = timeTakenIntoCustody;
	}
}
