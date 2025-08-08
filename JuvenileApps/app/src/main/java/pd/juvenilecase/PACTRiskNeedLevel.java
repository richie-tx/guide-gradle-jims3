package pd.juvenilecase;

import messaging.juvenilecase.reply.PactRiskLevelResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

import java.util.Date;
import java.util.Iterator;

/**
 * @author ugopinath This entity represents the PACTRiskNeedLevel record that is
 *         fetched from SQL
 */
public class PACTRiskNeedLevel extends PersistentObject
{
    private String caseFileId;
    private String referralNumber;
    private String juvenileNumber;
    private String riskLvl;
    private String needsLvl;
    private Date lastPactDate;
    private String riskNeedLvlId;
    private String status;
    private int pactId;

    /**
     * @roseuid 4276853503B9
     */
    public PACTRiskNeedLevel()
    {
    }

    /**
     * @return PACTRiskNeedLevel
     * @param riskNeedLevelId
     */
    static public PACTRiskNeedLevel find(String riskNeedLevelId)
    {
	IHome home = new Home();
	PACTRiskNeedLevel riskNeedLevel = (PACTRiskNeedLevel) home.find(riskNeedLevelId, PACTRiskNeedLevel.class);
	return riskNeedLevel;
    }

    /**
     * @return Iterator riskNeedLevel
     * @param attrName
     * name fo the attribute for where clause
     * @param attrValue
     * value to be checked in the where clause
     */
    static public Iterator findAll(String attrName, String attrValue)
    {
	IHome home = new Home();
	Iterator riskNeedLevel = home.findAll(attrName, attrValue, PACTRiskNeedLevel.class);
	return riskNeedLevel;
    }

    static public Iterator findAll(IEvent event)
    {
	IHome home = new Home();
	Iterator riskNeedLevels = home.findAll(event, PACTRiskNeedLevel.class);
	return riskNeedLevels;
    }

    /**
     * Access method for the referralNumber property.
     * 
     * @return the current value of the referralNumber property
     */
    public String getReferralNumber()
    {
	fetch();
	return referralNumber;
    }

    /**
     * Sets the value of the referralNumber property.
     * 
     * @param aReferralNumber
     * the new value of the referralNumber property
     */
    public void setReferralNumber(String aReferralNumber)
    {
	if (this.referralNumber == null || !this.referralNumber.equals(aReferralNumber))
	{
	    markModified();
	}
	referralNumber = aReferralNumber;
    }

    /**
     * @return
     */
    public String getCaseFileId()
    {
	fetch();
	return caseFileId;
    }

    /**
     * Sets the value of the caseFileId property.
     * 
     * @param aCaseFileId
     * the new value of the caseFileId property
     */
    public void setCaseFileId(String aCaseFileId)
    {
	if (this.caseFileId == null || !this.caseFileId.equals(aCaseFileId))
	{
	    markModified();
	}
	caseFileId = aCaseFileId;
    }

    /**
     * @return the riskLvl
     */
    public String getRiskLvl()
    {
	fetch();
	return riskLvl;
    }

    /**
     * @param riskLvl
     * the riskLvl to set
     */
    public void setRiskLvl(String riskLvl)
    {
	if (this.riskLvl == null || !this.riskLvl.equals(riskLvl))
	{
	    markModified();
	}
	this.riskLvl = riskLvl;
    }

    /**
     * @return the needsLvl
     */
    public String getNeedsLvl()
    {
	fetch();
	return needsLvl;
    }

    /**
     * @param needsLvl
     * the needsLvl to set
     */
    public void setNeedsLvl(String needsLvl)
    {
	if (this.needsLvl == null || !this.needsLvl.equals(needsLvl))
	{
	    markModified();
	}
	this.needsLvl = needsLvl;
    }

    /**
     * @return the lastPactDate
     */
    public Date getLastPactDate()
    {
	fetch();
	return lastPactDate;
    }

    /**
     * @param lastPactDate
     * the lastPactDate to set
     */
    public void setLastPactDate(Date lastPactDate)
    {
	if (this.lastPactDate == null || !this.lastPactDate.equals(lastPactDate))
	{
	    markModified();
	}
	this.lastPactDate = lastPactDate;
    }

    /**
     * @return the juvenileNumber
     */
    public String getJuvenileNumber()
    {
	fetch();
	return juvenileNumber;
    }

    /**
     * @param juvenileNumber
     * the juvenileNumber to set
     */
    public void setJuvenileNumber(String juvenileNumber)
    {
	if (this.juvenileNumber == null || !this.juvenileNumber.equals(juvenileNumber))
	{
	    markModified();
	}
	this.juvenileNumber = juvenileNumber;
    }

    /**
     * @return the riskNeedLvlId
     */
    public String getRiskNeedLvlId()
    {
	fetch();
	return riskNeedLvlId;
    }

    /**
     * @param riskNeedLvlId
     * the riskNeedLvlId to set
     */
    public void setRiskNeedLvlId(String aRiskNeedLvlId)
    {
	if (this.riskNeedLvlId == null || !this.riskNeedLvlId.equals(aRiskNeedLvlId))
	{
	    markModified();
	}
	this.riskNeedLvlId = aRiskNeedLvlId;
    }

    /**
     * @return the status
     */
    public String getStatus()
    {
	fetch();
	return status;
    }

    /**
     * @param status
     * the status to set
     */
    public void setStatus(String aStatus)
    {
	if (this.status == null || !this.status.equals(aStatus))
	{
	    markModified();
	}
	this.status = aStatus;
    }

    public int getPactId()
    {
	fetch();
        return pactId;
    }

    public void setPactId(int pactId)
    {
	if ( this.pactId != pactId )
	{
	    markModified();
	}
        this.pactId = pactId;
    }
    
    /**
     * 
     * @return
     */
    public PactRiskLevelResponseEvent valueObj(){
	
	PactRiskLevelResponseEvent response = new PactRiskLevelResponseEvent();
	
	response.setCaseFileId(this.getCaseFileId());
	response.setJuvenileNumber(this.getJuvenileNumber());
	response.setLastPactDate(this.getLastPactDate());
	response.setNeedsLvl(this.getNeedsLvl());
	response.setPactId(this.getPactId());
	response.setReferralNumber(this.getReferralNumber());
	response.setRiskLvl(this.getRiskLvl());
	response.setRiskNeedLvlId(this.getRiskNeedLvlId());
	response.setStatus(this.getStatus());
	
	return response;	
    }

  }
