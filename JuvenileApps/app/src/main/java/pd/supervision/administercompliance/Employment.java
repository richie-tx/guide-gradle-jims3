package pd.supervision.administercompliance;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.UpdateNCEmploymentEvent;
import messaging.administercompliance.reply.NCEmploymentResponseEvent;
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
 * @roseuid 47DA99DD0377
 */
public class Employment extends PersistentObject
{
	private String employerName;
	private String jobTitle;
	private String startDateAsString;
	private String statusId;
	private String seqNum;
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
	 * @return Returns the employerName.
	 */
	public String getEmployerName()
	{
		fetch();
		return employerName;
	}

	/**
	 * 
	 * @param employerName The employerName to set.
	 */
	public void setEmployerName(String employerName)
	{
		if (this.employerName == null || !this.employerName.equals(employerName))
		{
			markModified();
		}
		this.employerName = employerName;
	}

	/**
	 * 
	 * @return Returns the jobTitle.
	 */
	public String getJobTitle()
	{
		fetch();
		return jobTitle;
	}

	/**
	 * 
	 * @param jobTitle The jobTitle to set.
	 */
	public void setJobTitle(String jobTitle)
	{
		if (this.jobTitle == null || !this.jobTitle.equals(jobTitle))
		{
			markModified();
		}
		this.jobTitle = jobTitle;
	}	

	/**
	 * 
	 * @return Returns the statusId.
	 */
	public String getStatusId()
	{
		fetch();
		return statusId;
	}

	/**
	 * 
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId)
	{
		if (this.statusId == null || !this.statusId.equals(statusId))
		{
			markModified();
		}
		this.statusId = statusId;
	}

	/**
	 * @return Returns the ncResponseId.
	 */
	public int getNcResponseId() {
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(int ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	/**
	 * 
	 * @return
	 */
	public String getSeqNum() {
		fetch();
		return seqNum;
	}
	/**
	 * 
	 * @param seqNum
	 */
	public void setSeqNum(String seqNum) {
		if (this.seqNum == null || !this.seqNum.equals(seqNum))
		{
			markModified();
		}
		
		this.seqNum = seqNum;
	}
	/**
	 * 
	 * @param anEvent
	 * @return
	 */
	public static Iterator findAll(IEvent anEvent){
        return new Home().findAll(anEvent, Employment.class);
    }
    public static Iterator findAll(String attrName, String attrValue){
        return new Home().findAll(attrName, attrValue, Employment.class);
    }
    
    public static Iterator findAllByNumericParam(String attrName, String attrValue){
        return new Home().findAll(attrName, new Integer(attrValue), Employment.class);
    }
    
	/**
	 * @param employmentId
	 * @return
	 */
	public static Employment find(String employmentId) {
		return (Employment) new Home().find(employmentId, Employment.class);
	}
	
	public static Map findAllByNumericParameter(String attrName, String attrValue){
        Map map = new HashMap();
    	Iterator iter =  new Home().findAll(attrName, new Integer(attrValue), Employment.class);
        while(iter.hasNext()){
        	Employment emp = (Employment) iter.next();
        	map.put(emp.getOID(), emp);
        }
        return map;
    }

	/**
	 * @return
	 */
	public NCEmploymentResponseEvent getResponse() {
		NCEmploymentResponseEvent resp = new NCEmploymentResponseEvent();
		resp.setEmployerName(this.getEmployerName());
		resp.setEmploymentId(this.getOID());
		resp.setJobTitle(this.getJobTitle());
		resp.setNcResponseId(new StringBuffer("").append(this.getNcResponseId()).toString());
		resp.setStatusId(this.getStatusId());
		resp.setManualAdded(this.isManualAdded());
		resp.setSeqNum( this.getSeqNum() );
		return resp;
	}

	/**
	 * @param event
	 * @param ncResponseId
	 */
	public void setEmployment(UpdateNCEmploymentEvent event, String ncResponseId) {
		this.setEmployerName(event.getEmployerName());
		this.setJobTitle(event.getJobTitle());
		this.setStatusId(event.getStatusId());
		this.setNcResponseId(Integer.parseInt(ncResponseId));	
		this.setManualAdded(event.isManualAdded());
		this.setSeqNum( event.getSeqNum() );
	}
	/**
	 * @return Returns the startDateAsString.
	 */
	public String getStartDateAsString() {
		return startDateAsString;
	}
	/**
	 * @param startDateAsString The startDateAsString to set.
	 */
	public void setStartDateAsString(String startDateAsString) {
		this.startDateAsString = startDateAsString;
	}
	
	public void commit() {
		new Home().bind(this);		
	}
}
