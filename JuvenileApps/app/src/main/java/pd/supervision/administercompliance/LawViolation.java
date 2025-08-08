package pd.supervision.administercompliance;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.UpdateNCLawViolationEvent;
import messaging.administercompliance.reply.NCLawViolationResponseEvent;
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
 * @roseuid 47DA99E00099
 */
public class LawViolation extends PersistentObject
{
	private String caseId;
	private String crt;
	private Date offenseDate;
	private String offenseDateAsString;
	private String offenseLiteral;
	private String offenseLevel;
	private String offenseDegree;
	private int ncResponseId;
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
	
	/**
	 * 
	 * @return Returns the caseId.
	 */
	public String getCaseId()
	{
		fetch();
		return caseId;
	}

	/**
	 * 
	 * @param caseId The caseId to set.
	 */
	public void setCaseId(String caseId)
	{
		if (this.caseId == null || !this.caseId.equals(caseId))
		{
			markModified();
		}
		this.caseId = caseId;
	}

	
	/**
	 * 
	 * @return Returns the crt.
	 */
	public String getCrt()
	{
		fetch();
		return crt;
	}

	/**
	 * 
	 * @param crt The crt to set.
	 */
	public void setCrt(String crt)
	{
		if (this.crt == null || !this.crt.equals(crt))
		{
			markModified();
		}
		this.crt = crt;
	}

	/**
	 * 
	 * @return Returns the offenseDate.
	 */
	public Date getOffenseDate()
	{
		fetch();
		return offenseDate;
	}

	/**
	 * 
	 * @param offenseDate The offenseDate to set.
	 */
	public void setOffenseDate(Date offenseDate)
	{
		if (this.offenseDate == null || !this.offenseDate.equals(offenseDate))
		{
			markModified();
		}
		this.offenseDate = offenseDate;
	}

	/**
	 * 
	 * @return Returns the offenseDegree.
	 */
	public String getOffenseDegree()
	{
		fetch();
		return offenseDegree;
	}

	/**
	 * 
	 * @param offenseDegree The offenseDegree to set.
	 */
	public void setOffenseDegree(String offenseDegree)
	{
		if (this.offenseDegree == null || !this.offenseDegree.equals(offenseDegree))
		{
			markModified();
		}
		this.offenseDegree = offenseDegree;
	}

	/**
	 * 
	 * @return Returns the offenseLevel.
	 */
	public String getOffenseLevel()
	{
		fetch();
		return offenseLevel;
	}

	/**
	 * 
	 * @param offenseLevel The offenseLevel to set.
	 */
	public void setOffenseLevel(String offenseLevel)
	{
		if (this.offenseLevel == null || !this.offenseLevel.equals(offenseLevel))
		{
			markModified();
		}
		this.offenseLevel = offenseLevel;
	}

	/**
	 * 
	 * @return Returns the offenseLiteral.
	 */
	public String getOffenseLiteral()
	{
		fetch();
		return offenseLiteral;
	}

	/**
	 * 
	 * @param offenseLiteral The offenseLiteral to set.
	 */
	public void setOffenseLiteral(String offenseLiteral)
	{
		if (this.offenseLiteral == null || !this.offenseLiteral.equals(offenseLiteral))
		{
			markModified();
		}
		this.offenseLiteral = offenseLiteral;
	}

	/**
	 * @return Returns the offenseDateAsString.
	 */
	public String getOffenseDateAsString() {
		return offenseDateAsString;
	}
	/**
	 * @param offenseDateAsString The offenseDateAsString to set.
	 */
	public void setOffenseDateAsString(String offenseDateAsString) {
		this.offenseDateAsString = offenseDateAsString;
	}
	
	public static Iterator findAll(IEvent anEvent){
        return new Home().findAll(anEvent, LawViolation.class);
    }
    public static Iterator findAll(String attrName, String attrValue){
        return new Home().findAll(attrName, attrValue, LawViolation.class);
    }
    
    public static Iterator findAllByNumericParam(String attrName, String attrValue){
        return new Home().findAll(attrName, new Integer(attrValue), LawViolation.class);
    }
    
	public static Map findAllByNumericParameter(String attrName, String attrValue){
        Map map = new HashMap();
    	Iterator iter =  new Home().findAll(attrName, new Integer(attrValue), LawViolation.class);
        while(iter.hasNext()){
        	LawViolation lv = (LawViolation) iter.next();
        	map.put(lv.getOID(), lv);
        }
        return map;
    }
    
	/**
	 * @param lawViolationId
	 * @return
	 */
	public static LawViolation find(String lawViolationId) {
		return (LawViolation) new Home().find(lawViolationId, LawViolation.class);
	}	
    
    /**
	 * @return
	 */
	public NCLawViolationResponseEvent getResponse() {
		NCLawViolationResponseEvent resp = new NCLawViolationResponseEvent();
		resp.setCaseId(this.getCaseId());
		resp.setCourtId(this.getCrt());
		resp.setLawViolationId(this.getOID());
		resp.setNcResponseId(new StringBuffer("").append(this.getNcResponseId()).toString());
						
		if(this.offenseDateAsString != null && !"".equals(this.offenseDateAsString)){
		    String year = this.offenseDateAsString.substring(0,4);
		    String month = this.offenseDateAsString.substring(4,6);
		    String day = this.offenseDateAsString.substring(6);
		    StringBuffer formattedDate = new StringBuffer();
		    formattedDate.append(month);
		    formattedDate.append("/");
		    formattedDate.append(day);
		    formattedDate.append("/");
		    formattedDate.append(year);
			resp.setOffenseDate(DateUtil.stringToDate(formattedDate.toString(),DateUtil.DATE_FMT_1));
		}else{
			resp.setOffenseDate(this.getOffenseDate());
		}
		resp.setOffenseDegree(this.getOffenseDegree());
		resp.setOffenseLevel(this.getOffenseLevel());
		resp.setOffenseLitrel(this.getOffenseLiteral());
		resp.setManualAdded(this.isManualAdded());
		return resp;
	}
	/**
	 * @param lvEvent
	 * @param ncResponseId
	 */
	public void setLawViolation(UpdateNCLawViolationEvent lvEvent, String ncResponseId) {
		this.setCaseId(lvEvent.getCaseId());
		this.setCrt(lvEvent.getCrt());
		this.setOffenseDate(lvEvent.getOffenseDate());
		this.setOffenseDegree(lvEvent.getOffenseDegree());
		this.setOffenseLevel(lvEvent.getOffenseLevel());
		this.setOffenseLiteral(lvEvent.getOffenseLiteral());
		this.setNcResponseId(Integer.parseInt(ncResponseId));	
		this.setManualAdded(lvEvent.isManualAdded());
	}
	
	public void commit() {
		new Home().bind(this);
	}
}
