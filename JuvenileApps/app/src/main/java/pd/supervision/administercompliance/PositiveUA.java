package pd.supervision.administercompliance;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.UpdateNCPositiveUAEvent;
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
 * @roseuid 47DA99E00329
 */
public class PositiveUA extends PersistentObject
{
	private Timestamp occurenceDate;
	private String substance;
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
	 * 
	 * @return Returns the substance.
	 */
	public String getSubstance()
	{
		fetch();
		return substance;
	}

	/**
	 * 
	 * @param substance The substance to set.
	 */
	public void setSubstance(String substance)
	{
		if (this.substance == null || !this.substance.equals(substance))
		{
			markModified();
		}
		this.substance = substance;
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
        return new Home().findAll(anEvent, PositiveUA.class);
    }
    public static Iterator findAll(String attrName, String attrValue){
        return new Home().findAll(attrName, attrValue, PositiveUA.class);
    }
    
    public static Iterator findAllByNumericParam(String attrName, String attrValue){
        return new Home().findAll(attrName, new Integer(attrValue), PositiveUA.class);
    }
    
    public static PositiveUA find(String positiveUAId){
        return (PositiveUA) new Home().find(positiveUAId, PositiveUA.class);
    }
    
    public static Map findAllByNumericParameter(String attrName, String attrValue){
        Map map = new HashMap();
    	Iterator iter =  new Home().findAll(attrName, new Integer(attrValue), PositiveUA.class);
        while(iter.hasNext()){
        	PositiveUA pua = (PositiveUA) iter.next();
        	map.put(pua.getOID(), pua);
        }
        return map;
    }
    
    /**
	 * @return
	 */
	public NonComplianceEventResponseEvent getResponse() {
		NonComplianceEventResponseEvent resp = new NonComplianceEventResponseEvent();
		resp.setDateTime(this.occurenceDate);
		resp.setDetails(this.getSubstance());
		resp.setNonComplianceEventId(this.getOID());
		resp.setNcResponseId(new StringBuffer("").append(this.getNcResponseId()).toString());
		resp.setManualAdded(this.isManualAdded());
		return resp;
	}

	/**
	 * @param event
	 * @param ncResponseId
	 */
	public void setPositiveUA(UpdateNCPositiveUAEvent event, String ncResponseId) {
		this.setOccurenceDate(event.getOccurenceDate());
		this.setSubstance(event.getSubstance());
		this.setNcResponseId(Integer.parseInt(ncResponseId));	
		this.setManualAdded(event.isManualAdded());
	}
	
	public void commit() {
		new Home().bind(this);
	}
}
