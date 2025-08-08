package pd.supervision.administercompliance;

import java.util.Iterator;

import messaging.administercompliance.UpdateNCCommunityServiceEvent;
import messaging.administercompliance.reply.NCCommunityServiceResponseEvent;
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

public class CommunityService extends PersistentObject
{
	private String hoursOrdered;
	private String hoursCompleted;
	private String caseId;
	private int ncResponseId; // this is the oid of violation report FK
	
	
	/**
	 * @return Returns the caseId.
	 */
	public String getCaseId() {
		fetch();
		return caseId;
	}
	/**
	 * @param caseId The caseId to set.
	 */
	public void setCaseId(String caseId) {
		if (this.caseId == null || !this.caseId.equals(caseId))
		{
			markModified();
		}
		this.caseId = caseId;
	}
	
	/**
	 * 
	 * @return Returns the hoursCompleted.
	 */
	public String getHoursCompleted()
	{
		fetch();
		return hoursCompleted;
	}

	/**
	 * 
	 * @param hours_Completed The hoursCompleted to set.
	 */
	public void setHoursCompleted(String hoursCompleted)
	{
		if (this.hoursCompleted == null || !this.hoursCompleted.equals(hoursCompleted))
		{
			markModified();
		}
		this.hoursCompleted = hoursCompleted;
	}

	/**
	 * 
	 * @return Returns the hoursOrdered.
	 */
	public String getHoursOrdered()
	{
		fetch();
		return hoursOrdered;
	}

	/**
	 * 
	 * @param hoursOrdered The hoursOrdered to set.
	 */
	public void setHoursOrdered(String hoursOrdered)
	{
		if (this.hoursOrdered == null || !this.hoursOrdered.equals(hoursOrdered))
		{
			markModified();
		}
		this.hoursOrdered = hoursOrdered;
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
	
	public static CommunityService find(String communityServiceId){
        return (CommunityService) new Home().find(communityServiceId, CommunityService.class);
    }
	
	public static Iterator findAll(IEvent anEvent){
        return new Home().findAll(anEvent, CommunityService.class);
    }
    public static Iterator findAll(String attrName, String attrValue){
        return new Home().findAll(attrName, attrValue, CommunityService.class);
    }
    
    public static Iterator findAllByNumericParam(String attrName, String attrValue){
        return new Home().findAll(attrName, new Integer(attrValue), CommunityService.class);
    }

	/**
	 * @return NCCommunityServiceResponseEvent
	 */
	public NCCommunityServiceResponseEvent getResponse() {
		NCCommunityServiceResponseEvent resp = new NCCommunityServiceResponseEvent();
		resp.setCaseId(this.getCaseId());
		resp.setCommunityServiceId(this.getOID());
		resp.setHoursCompleted(this.getHoursCompleted());
		resp.setHoursOrdered(this.getHoursOrdered());
		resp.setNcResponseId(new StringBuffer("").append(this.getNcResponseId()).toString());
		return resp;
	}
	/**
	 * @param event
	 * @param ncResponseId
	 */
	public void setCommunityService(UpdateNCCommunityServiceEvent event,String ncResponseId) {
		this.setCaseId(event.getCaseId());
		this.setHoursCompleted(event.getHoursCompleted());
		this.setHoursOrdered(event.getHoursOrdered());		
		this.setNcResponseId(Integer.parseInt(ncResponseId));
	}
	
	public void commit() {
		new Home().bind(this);
	}
}
