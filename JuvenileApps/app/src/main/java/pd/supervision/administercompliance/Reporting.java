package pd.supervision.administercompliance;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.UpdateNCReportingEvent;
import messaging.administercompliance.reply.NonComplianceEventResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * 
 * @roseuid 47DA99E70099
 */
public class Reporting extends PersistentObject
{
	private Timestamp occurenceDate;
	private String eventTypes;
	private String details;
	private int ncResponseId; // this is the oid of violation report FK
	private boolean manualAdded;
	
	/**
	 * @return the manualAdded
	 */
	public boolean isManualAdded() {
		fetch();
		return manualAdded;
	}
	/**
	 * @param manualAdded the manualAdded to set
	 */
	public void setManualAdded(boolean manualAdded) {
		if (this.manualAdded != manualAdded)
		{
			markModified();
		}
		this.manualAdded = manualAdded;
	}
	
	
	/**
	 * 
	 * @return Returns the details.
	 */
	public String getDetails()
	{
		fetch();
		return details;
	}

	/**
	 * 
	 * @param details The details to set.
	 */
	public void setDetails(String details)
	{
		if (this.details == null || !this.details.equals(details))
		{
			markModified();
		}
		this.details = details;
	}

	/**
	 * 
	 * @return Returns the eventTypes.
	 */
	public String getEventTypes()
	{
		fetch();
		return eventTypes;
	}

	/**
	 * 
	 * @param eventTypes The eventTypes to set.
	 */
	public void setEventTypes(String eventTypes)
	{
		if (this.eventTypes == null || !this.eventTypes.equals(eventTypes))
		{
			markModified();
		}
		this.eventTypes = eventTypes;
	}

	/**
	 * 
	 * @return Returns the occurenceDate.
	 */
	public Timestamp getOccurenceDate()
	{
		fetch();
		return occurenceDate;
	}

	/**
	 * 
	 * @param occurenceDate The occurenceDate to set.
	 */
	public void setOccurenceDate(Timestamp occurenceDate)
	{
		if (this.occurenceDate == null || !this.occurenceDate.equals(occurenceDate))
		{
			markModified();
		}
		this.occurenceDate = occurenceDate;
	}
	
	/**
	 * @return Returns the ncResponseId.
	 */
	public int getNcResponseId() {
		fetch();
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(int ncResponseId) {
		if (this.ncResponseId != this.ncResponseId)
		{
			markModified();
		}
		this.ncResponseId = ncResponseId;
	}
	
	public static Iterator findAll(IEvent anEvent){
        return new Home().findAll(anEvent, Reporting.class);
    }
    public static Iterator findAll(String attrName, String attrValue){
        return new Home().findAll(attrName, attrValue, Reporting.class);
    }
    
    public static Iterator findAllByNumericParam(String attrName, String attrValue){
        return new Home().findAll(attrName, new Integer(attrValue), Reporting.class);
    }
    
    public static Map findAllByNumericParameter(String attrName, String attrValue){
        Map map = new HashMap();
    	Iterator iter =  new Home().findAll(attrName, new Integer(attrValue), Reporting.class);
        while(iter.hasNext()){
        	Reporting rep = (Reporting) iter.next();
        	map.put(rep.getOID(), rep);
        }
        return map;
    }
    
    /**
	 * @param reportingid
	 * @return
	 */
	public static Reporting find(String reportingId) {
		return (Reporting) new Home().find(reportingId, Reporting.class);
	}	

	/**
	 * @return
	 */
	public NonComplianceEventResponseEvent getResponse() {
		NonComplianceEventResponseEvent resp = new NonComplianceEventResponseEvent();
		resp.setDateTime(this.occurenceDate);
		resp.setDetails(this.getDetails());
		resp.setNonComplianceEventId(this.getOID());
		resp.setEventTypes(this.getEventTypes());
		resp.setNcResponseId(new StringBuffer("").append(this.getNcResponseId()).toString());
		resp.setManualAdded(this.isManualAdded());
		return resp;
	}
	
	/**
	 * @return
	 */
	public NonComplianceEventResponseEvent getResponseEvent(Map supervisionTypes) {
		NonComplianceEventResponseEvent resp = new NonComplianceEventResponseEvent();
		resp.setDateTime(this.occurenceDate);
		resp.setDetails(this.getDetails());
		resp.setNonComplianceEventId(this.getOID());
		StringBuffer buf = new StringBuffer();
		if(this.getEventTypes() != null && !this.getEventTypes().equals("")){
			String[] eventTypesId = this.getEventTypes().split(",");
			if(eventTypesId != null){
				for(int i=0;i<eventTypesId.length;i++){
					String eventTypeId = eventTypesId[i];
					String desc = (String) supervisionTypes.get(eventTypeId);
					if(desc != null && !desc.equals("")){
						buf.append(desc);
					}else{
						buf.append(eventTypeId);
					}
					if(i != eventTypesId.length-1){
						buf.append(", ");
					}
				}
			}			
		}		
		resp.setEventTypesId(this.getEventTypes());
		resp.setEventTypes(buf.toString());
		resp.setNcResponseId(new StringBuffer("").append(this.getNcResponseId()).toString());
		resp.setManualAdded(this.isManualAdded());
		return resp;
	}
	

	/**
	 * @param event
	 * @param ncResponseId
	 */
	public void setReporting(UpdateNCReportingEvent event, String ncResponseId) {
		this.setDetails(event.getDetails());
		this.setEventTypes(event.getEventTypes());
		this.setOccurenceDate(event.getOccurencedate());
		this.setNcResponseId(Integer.parseInt(ncResponseId));
		this.setManualAdded(event.isManualAdded());
	}
	
	public void commit() {
		new Home().bind(this);
	}
}
