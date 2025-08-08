package pd.supervision.administercompliance;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import messaging.administercompliance.reply.NonComplianceEventResponseEvent;
import messaging.administercompliance.reply.NonComplianceEventTypeResponseEvent;
import messaging.administercompliance.CreateNonCompliantEventEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;
import naming.SupervisionOrderConditionConstants;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * When a condition is set to non-compliant, the event(s) leading to this are
 * documented.  Event Types come from Events configured in the Condition in MSO.
 */
public class NonComplianceEvent extends PersistentObject
{
	private Timestamp dateTime;
	private String details;
	private int sprOrderConditionId;
	private String nonComplianceEventId;
	private String newEventType;
		
	private Collection nonComplianceEventTypes = null;
	

	/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public NonComplianceEvent()
	{
	}
	
	public static Iterator findAllByNumericParam(String attributeName, String attributeValue){
		return new Home().findAll(attributeName, new Integer(attributeValue), NonComplianceEvent.class);
	}
	
	public static NonComplianceEvent find(String nonComplianceEventId){
		return (NonComplianceEvent) new Home().find(nonComplianceEventId, NonComplianceEvent.class);
	}
	
	public static Iterator findAll(IEvent event){
		return new Home().findAll(event, NonComplianceEvent.class);
	}
	
	public static Iterator findAll(String attributeName, String attributeValue){
		return new Home().findAll(attributeName, attributeValue, NonComplianceEvent.class);
	}

	/**
	 * 
	 * @return Returns the dateTime.
	 */
	public Timestamp getDateTime()
	{
		fetch();
		return dateTime;
	}

	/**
	 * 
	 * @param dateTime The dateTime to set.
	 */
	public void setDateTime(Timestamp dateTime)
	{
		if (this.dateTime == null || !this.dateTime.equals(dateTime))
		{
			markModified();
		}
		this.dateTime = dateTime;
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
	 * @return Returns the sprOrderCondId.
	 */
	public int getSprOrderConditionId()
	{
		fetch();
		return sprOrderConditionId;
	}

	/**
	 * 
	 * @param sprOrderCondId The sprOrderCondId to set.
	 */
	public void setSprOrderConditionId(int sprOrderCondId)
	{
		if (this.sprOrderConditionId != sprOrderCondId)
		{
			markModified();
		}
		this.sprOrderConditionId = sprOrderCondId;
	}
	
    public String setNonComplianceEvent(CreateNonCompliantEventEvent unceEvent){
		String date = unceEvent.getOccurrenceDate();
		if(date != null && !date.equals("")){
			String[] dates = date.split("/");
			date = dates[2] + "-" + dates[0] + "-" + dates[1];			
			
			String time = unceEvent.getOccurrenceTime();
			StringBuffer tempTime = new StringBuffer("");
						
			if(time != null && !time.equals("")){
				String[] times = time.split(":");
				if(times != null){
					int hour = Integer.parseInt(times[0]);
					String minute = times[1];
					String second = "00";
					if(times.length == 3){
						second = times[2];
					}
				
					if(("" + hour).length() == 1){
						tempTime.append("0");
					}
					tempTime.append(hour);
					tempTime.append(":");
					tempTime.append(minute);
					tempTime.append(":");
					tempTime.append(second);
					
					String formattedDate = new StringBuffer(date).append(" ").append(tempTime.toString()).toString();
					Date tempDate = DateUtil.stringToDate(formattedDate, SupervisionOrderConditionConstants.NONCOMPLIANCE_DATEFORMAT);
					this.setDateTime(new Timestamp(tempDate.getTime()));					
				}
			} else {
				tempTime.append("00");
				tempTime.append(":");
				tempTime.append("00");
				tempTime.append(":");
				tempTime.append("00");
				
				String formattedDate = new StringBuffer(date).append(" ").append(tempTime.toString()).toString();
				Date tempDate = DateUtil.stringToDate(formattedDate, SupervisionOrderConditionConstants.NONCOMPLIANCE_DATEFORMAT);
				this.setDateTime(new Timestamp(tempDate.getTime()));	
			}
			
		}
		this.setDetails(unceEvent.getDetails());
		this.setSprOrderConditionId(unceEvent.getSprOrderConditionId());
		this.setNewEventType(unceEvent.getNewEventType());
		new Home().bind(this);
		return this.getOID();
	}
	
	
	public NonComplianceEventResponseEvent getResponseEvent(HashMap supervisionTypes){
		NonComplianceEventResponseEvent resp = new NonComplianceEventResponseEvent();
		resp.setDateTime(this.getDateTime());
		resp.setDetails(this.getDetails());
		resp.setNonComplianceEventId(this.getOID());
		Iterator iterator = NonComplianceEventType.findAllByNumericParam(SupervisionOrderConditionConstants.NONCOMPLIANCE_EVENT_ID, this.getOID());
		
		StringBuffer buf = new StringBuffer();
		StringBuffer buf1 = new StringBuffer();
		while(iterator.hasNext()){
			NonComplianceEventType ncet = (NonComplianceEventType) iterator.next();
			if(ncet != null){
				String desc = (String) supervisionTypes.get(ncet.getNonComplianceEventCodeId());
				if(desc != null && !desc.equals("")){
					buf.append(desc);
					buf1.append(ncet.getNonComplianceEventCodeId());
					if(iterator.hasNext()){
						buf.append(", ");
						buf1.append(",");
					}
				}
			}
		}
		
		if(this.newEventType != null && !this.newEventType.equals("")){
			if(buf.toString().length() > 1){
			    buf.append(", ");
			    buf1.append(",");
			}
			buf.append(this.getNewEventType());
			buf1.append(this.getNewEventType());
		}
		resp.setEventTypes(buf.toString());
		resp.setEventTypesId(buf1.toString());
		resp.setNewEventType(this.getNewEventType());
		return resp;
	}
	
	public NonComplianceEventResponseEvent getPositiveUAResponseEvent(HashMap supervisionTypes){
		NonComplianceEventResponseEvent resp = new NonComplianceEventResponseEvent();
		resp.setDateTime(this.getDateTime());		
		resp.setNonComplianceEventId(this.getOID());
		Iterator iterator = NonComplianceEventType.findAllByNumericParam(SupervisionOrderConditionConstants.NONCOMPLIANCE_EVENT_ID, this.getOID());
		
		//StringBuffer buf = new StringBuffer();
		boolean isPositiveUA = false;
		while(iterator.hasNext()){
			NonComplianceEventType ncet = (NonComplianceEventType) iterator.next();
			if(ncet != null && supervisionTypes.containsKey(ncet.getNonComplianceEventCodeId())){
				isPositiveUA = true;
			}
		}
		
		if(!isPositiveUA){
			return null;
		}
		
		resp.setDetails(this.details);
		return resp;
	}
	

	/**
	 * @return
	 */
	public NonComplianceEventTypeResponseEvent getNewEventTypeResponseEvent() {
		NonComplianceEventTypeResponseEvent resp = new NonComplianceEventTypeResponseEvent();
		resp.setNonComplianceEventCodeId(SupervisionOrderConditionConstants.NEWEVENTTYPECODE);
		resp.setNonComplianceEventCodeDesc(this.getNewEventType());		
		return resp;	
	}
	
	/**
	 * @return Returns the nonComplianceEventId.
	 */
	public String getNonComplianceEventId() {
		fetch();
		return this.getOID();
	}
	/**
	 * @param nonComplianceEventId The nonComplianceEventId to set.
	 */
	public void setNonComplianceEventId(String nonComplianceEventId) {
		if (this.nonComplianceEventId == null || !this.nonComplianceEventId.equals(nonComplianceEventId))
		{
			markModified();
		}
		this.setOID(nonComplianceEventId);
		this.nonComplianceEventId = nonComplianceEventId;
	}
	
	/**
	 * @return Returns the newEventType.
	 */
	public String getNewEventType() {
		fetch();
		return newEventType;
	}
	/**
	 * @param newEventType The newEventType to set.
	 */
	public void setNewEventType(String newEventType) {
		if (this.newEventType == null || !this.newEventType.equals(newEventType))
		{
			markModified();
		}
		this.newEventType = newEventType;
	}

	public void bind() {
		new Home().bind(this);		
	}	
}
