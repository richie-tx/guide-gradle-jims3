package pd.supervision.administercompliance;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.UpdateNCTreatmentEvent;
import messaging.administercompliance.reply.NCTreatmentResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * 
 * @roseuid 47DA99E80193
 */
public class Treatment extends PersistentObject
{
	private String referralType;
	private String serviceProviderName;
	private Date beginDate;
	private String beginDateAsString;
	private String endDateAsString;
	private Date exitDate;
	private String reasonForDischargeId;
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
	
	private Code reasonForDischargeCode = null;

	/**
	 * 
	 * @return Returns the beginDate.
	 */
	public Date getBeginDate()
	{
		fetch();
		return beginDate;
	}

	/**
	 * 
	 * @param beginDate The beginDate to set.
	 */
	public void setBeginDate(Date beginDate)
	{
		if (this.beginDate == null || !this.beginDate.equals(beginDate))
		{
			markModified();
		}
		this.beginDate = beginDate;
	}

	/**
	 * 
	 * @return Returns the exitDate.
	 */
	public Date getExitDate()
	{
		fetch();
		return exitDate;
	}

	/**
	 * 
	 * @param exitDate The exitDate to set.
	 */
	public void setExitDate(Date exitDate)
	{
		if (this.exitDate == null || !this.exitDate.equals(exitDate))
		{
			markModified();
		}
		this.exitDate = exitDate;
	}

	/**
	 * 
	 * @return Returns the reasonForDischargeId.
	 */
	public String getReasonForDischargeId()
	{
		fetch();
		return reasonForDischargeId;
	}

	/**
	 * 
	 * @param reasonForDischargeId The reasonForDischargeId to set.
	 */
	public void setReasonForDischargeId(String reasonForDischargeId)
	{
		if (this.reasonForDischargeId == null || !this.reasonForDischargeId.equals(reasonForDischargeId))
		{
			markModified();
		}
		this.reasonForDischargeId = reasonForDischargeId;
	}

	/**
	 * 
	 * @return Returns the referralType.
	 */
	public String getReferralType()
	{
		fetch();
		return referralType;
	}

	/**
	 * 
	 * @param referralType The referralType to set.
	 */
	public void setReferralType(String referralType)
	{
		if (this.referralType == null || !this.referralType.equals(referralType))
		{
			markModified();
		}
		this.referralType = referralType;
	}

	/**
	 * 
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName()
	{
		fetch();
		return serviceProviderName;
	}

	/**
	 * 
	 * @param serviceProviderName The serviceProviderName to set.
	 */
	public void setServiceProviderName(String serviceProviderName)
	{
		if (this.serviceProviderName == null || !this.serviceProviderName.equals(serviceProviderName))
		{
			markModified();
		}
		this.serviceProviderName = serviceProviderName;
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
	
	/**
	 * @return Returns the beginDateAsString.
	 */
	public String getBeginDateAsString() {
		return beginDateAsString;
	}
	/**
	 * @param beginDateAsString The beginDateAsString to set.
	 */
	public void setBeginDateAsString(String beginDateAsString) {
		this.beginDateAsString = beginDateAsString;
	}
	/**
	 * @return Returns the endDateAsString.
	 */
	public String getEndDateAsString() {
		return endDateAsString;
	}
	/**
	 * @param endDateAsString The endDateAsString to set.
	 */
	public void setEndDateAsString(String endDateAsString) {
		this.endDateAsString = endDateAsString;
	}
	
	public static Iterator findAll(IEvent anEvent){
        return new Home().findAll(anEvent, Treatment.class);
    }
    public static Iterator findAll(String attrName, String attrValue){
        return new Home().findAll(attrName, attrValue, Treatment.class);
    }
    
    public static Iterator findAllByNumericParam(String attrName, String attrValue){
        return new Home().findAll(attrName, new Integer(attrValue), Treatment.class);
    }
    
    public static Treatment find(String treatmentId){
        return (Treatment) new Home().find(treatmentId, Treatment.class);
    }
    
    public static Map findAllByNumericParameter(String attrName, String attrValue){
        Map map = new HashMap();
    	Iterator iter =  new Home().findAll(attrName, new Integer(attrValue), Treatment.class);
        while(iter.hasNext()){
        	Treatment t = (Treatment) iter.next();
        	map.put(t.getOID(), t);
        }
        return map;
    }

	/**
	 * @return
	 */
	public NCTreatmentResponseEvent getResponse() {
		NCTreatmentResponseEvent resp = new NCTreatmentResponseEvent();
		resp.setProgramBeginDate(this.getBeginDate());
		resp.setProgramEndDate(this.getExitDate());
		resp.setNcResponseId(new StringBuffer("").append(this.getNcResponseId()).toString());
		//resp.set(this.getReasonForDischargeId());
		if(this.getReasonForDischargeId() != null && !this.getReasonForDischargeId().equals("")){
			Code code = this.getReasonForDischargeCode();
			if(code != null){
				resp.setDischargeReason(code.getDescription());					
			}
		}
		resp.setReferralTypeCode(this.getReferralType());
		resp.setNewServiceProviderName(this.getServiceProviderName());
		resp.setProgramReferralId(this.getOID());
		resp.setManualAdded(this.isManualAdded());
		return resp;
	}
	
	/**
	 * @param event
	 * @param ncResponseId
	 */
	public void setTreatment(UpdateNCTreatmentEvent event, String ncResponseId) {
        this.setBeginDate(event.getBeginDate());
        this.setExitDate(event.getExitDate());
        this.setReasonForDischargeId(event.getDischargeResonId());
        this.setReferralType(event.getReferalType());
        this.setServiceProviderName(event.getServiceProviderName());
        this.setNcResponseId(Integer.parseInt(ncResponseId));
		this.setManualAdded(event.isManualAdded());
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	* @return status
	*/
	public Code getReasonForDischargeCode()
	{
		fetch();
		initReasonForDischargeCode();
		return reasonForDischargeCode;
	}
	
	
	private void initReasonForDischargeCode() {
		if (reasonForDischargeCode == null)
		{
			try
			{
				reasonForDischargeCode =
					(Code) new mojo
						.km
						.persistence
						.Reference(reasonForDischargeId, Code.class, naming.PDCodeTableConstants.JIMS2_DISCHARGE_REASON)
						.getObject();
			}
			catch (Throwable t)
			{
				reasonForDischargeCode = null;
			}
		}	
	}
	
	public void commit() {
		new Home().bind(this);
	}
}
