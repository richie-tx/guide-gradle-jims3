package pd.supervision.administercompliance;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.UpdateNCCourtActivityEvent;
import messaging.administercompliance.reply.NCPreviousCourtActivityResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.DateUtil;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * 
 * @roseuid 47DA99E2006A
 */
public class PreviousCourtActivity extends PersistentObject
{
	private Timestamp occurenceDate;
	private String activity;
	private String disposition;
	private String type;
	private int ncResponseId; // this is the oid of violation report FK
	private String summaryOfCourtActivity;
	private String subType;
	private boolean manualAdded;
	private String typeOfCourtActionComment;
	
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
	 * @return the subType
	 */
	public String getSubType() {
		fetch();
		return subType;
	}
	/**
	 * @param subType the subType to set
	 */
	public void setSubType(String subType) {
		if (this.subType == null || !this.subType.equals(subType))
		{
			markModified();
		}
		this.subType = subType;
	}
	/**
	 * @return Returns the summaryOfCourtActivity.
	 */
	public String getSummaryOfCourtActivity() {
		fetch();
		return summaryOfCourtActivity;
	}
	/**
	 * @param summaryOfCourtActivity The summaryOfCourtActivity to set.
	 */
	public void setSummaryOfCourtActivity(String summaryOfCourtActivity) {
		if (this.summaryOfCourtActivity == null || !this.summaryOfCourtActivity.equals(summaryOfCourtActivity))
		{
			markModified();
		}
		this.summaryOfCourtActivity = summaryOfCourtActivity;
	}
	/**
	 * 
	 * @return Returns the activity.
	 */
	public String getActivity()
	{
		fetch();
		return activity;
	}

	/**
	 * 
	 * @param activity The activity to set.
	 */
	public void setActivity(String activity)
	{
		if (this.activity == null || !this.activity.equals(activity))
		{
			markModified();
		}
		this.activity = activity;
	}



	/**
	 * 
	 * @return Returns the disposition.
	 */
	public String getDisposition()
	{
		fetch();
		return disposition;
	}

	/**
	 * 
	 * @param disposition The disposition to set.
	 */
	public void setDisposition(String disposition)
	{
		if (this.disposition == null || !this.disposition.equals(disposition))
		{
			markModified();
		}
		this.disposition = disposition;
	}

	/**
	 * 
	 * @return Returns the occerenceDate.
	 */
	public Timestamp getOccurenceDate()
	{
		fetch();
		return occurenceDate;
	}

	/**
	 * 
	 * @param occerenceDate The occerenceDate to set.
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
	 * @return Returns the type.
	 */
	public String getType()
	{
		fetch();
		return type;
	}

	/**
	 * 
	 * @param type The type to set.
	 */
	public void setType(String type)
	{
		if (this.type == null || !this.type.equals(type))
		{
			markModified();
		}
		this.type = type;
	}
	/**
	 * 
	 * @return Returns the typeOfCourtActionComment.
	 */
	public String getTypeOfCourtActionComment()
	{
		fetch();
		return typeOfCourtActionComment;
	}

	/**
	 * 
	 * @param typeOfCourtActionComment The typeOfCourtActionComment to set.
	 */
	public void setTypeOfCourtActionComment(String typeOfCourtActionComment)
	{
		if (this.typeOfCourtActionComment == null || !this.typeOfCourtActionComment.equals(typeOfCourtActionComment))
		{
			markModified();
		}
		this.typeOfCourtActionComment = typeOfCourtActionComment;
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
		if (this.ncResponseId != ncResponseId)
		{
			markModified();
		}
		this.ncResponseId = ncResponseId;
	}
	
	public static Iterator findAll(IEvent anEvent){
        return new Home().findAll(anEvent, PreviousCourtActivity.class);
    }
    public static Iterator findAll(String attrName, String attrValue){
        return new Home().findAll(attrName, attrValue, PreviousCourtActivity.class);
    }
    
    public static Iterator findAllByNumericParam(String attrName, String attrValue){
        return new Home().findAll(attrName, new Integer(attrValue), PreviousCourtActivity.class);
    }
    
    public static Map findAllByNumericParameter(String attrName, String attrValue){
        Map map = new HashMap();
    	Iterator iter =  new Home().findAll(attrName, new Integer(attrValue), PreviousCourtActivity.class);
        while(iter.hasNext()){
        	PreviousCourtActivity pca = (PreviousCourtActivity) iter.next();
        	map.put(pca.getOID(), pca);
        }
        return map;
    }
    
	/**
	 * @param courtActivityId
	 * @return
	 */
	public static PreviousCourtActivity find(String courtActivityId) {
		return (PreviousCourtActivity) new Home().find(courtActivityId, PreviousCourtActivity.class);
	}

	/**
	 * @return
	 */
	public NCPreviousCourtActivityResponseEvent getCourtActivityResponseEvent() {
		NCPreviousCourtActivityResponseEvent resp = new NCPreviousCourtActivityResponseEvent();
//		resp.setActivity(this.getActivity());
		resp.setTypeOfCourtActionComment(this.getTypeOfCourtActionComment());
		resp.setPreviousCourtActivityId(this.getOID());
		resp.setOccurenceDate(this.getOccurenceDate());
		if (this.getOccurenceDate() != null){
			resp.setOccurenceDateStr(DateUtil.dateToString(this.getOccurenceDate(), DateUtil.DATE_FMT_1));
		}
		resp.setSummaryOfCourtAction(this.getSummaryOfCourtActivity());		
		resp.setType(this.getType());
		//TSV 12/27/2011 JIMS200071520 - The ResponseEvent was setting the subType to "Violation" or "Motion" when we need all to the reset to "Other"
		//resp.setSubType(this.getSubType());
		resp.setSubType(this.getSubType());
		resp.setDisposition(this.getDisposition());
		resp.setNcResponseId(new StringBuffer("").append(this.getNcResponseId()).toString());
		resp.setManualAdded(this.isManualAdded());
		return resp;		
	}
	
	/**
	 * @param event
	 * @param ncResponseId
	 */
	public void setPreviousCourtActivity(UpdateNCCourtActivityEvent event, String ncResponseId) {
//		this.setActivity(event.getActivity());
		this.setTypeOfCourtActionComment(event.getTypeOfCourtActionComment());
		this.setDisposition(event.getDisposition());
		this.setOccurenceDate(event.getOccurenceDate());
		this.setType(event.getType());
		this.setSubType(event.getSubType());
		this.setNcResponseId(Integer.parseInt(ncResponseId));	
		this.setSummaryOfCourtActivity(event.getSummaryOfCourtActivity());
		this.setManualAdded(event.isManualAdded());
	}
	
	public void commit() {
		new Home().bind(this);
	}
}
