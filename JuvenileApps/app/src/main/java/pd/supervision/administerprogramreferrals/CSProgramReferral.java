/*
 * Created on Mar 25, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerprogramreferrals;

import java.util.Date;
import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSProgramReferral extends PersistentObject 
{
	/*************** Member Variables ********************/
	private String programReferralId;
	private String criminalCaseId;
	private String defendantId;
	private String referralStatusCode;
	private String referralTypeCode;
	private Date referralDate;
	private String programLocationId;
	private String newServiceProviderName;
	private String newServiceProviderPhone;
	private String newServiceProviderFax;
	private Date scheduleDate;
	private Date programBeginDate;
	private boolean contractProgram;
	private String tracerNumber;
	private String placementReasonCd;
	private int confinementYears;
	private int confinementMonths;
	private int confinementDays;
	private String submitComments;
	private String exitComments;
	private Date programEndDate;
	private String dischargeReasonCd;
	private boolean autoReferral;
	private boolean incarcerationReferral;
	private boolean programUnitReferral;
	
	
	/*************** Getters & Setters ********************/
	
	
	
	public CSProgramReferral()
	{		
	}
	
	
	/************ CSProgramReferral Lookup Methods ***********************/

    /**
     * Find CSProgramReferral by OID
     */
	static public CSProgramReferral find(String oid)
	{
	    	//initialize lookup objects
		IHome home = new Home();

			//use delegate to locate given program referral entity
		CSProgramReferral csProgramReferral = (CSProgramReferral) home.find(oid, CSProgramReferral.class);
		return csProgramReferral;
	}//end of find()
    
    /**
     * Find all CSProgramReferral objects
     */
	static public Iterator findAll()
	{
	    	//initialize lookup objects
	    IHome home = new Home();
	    
	    	//use delegate to locate all program referral objects
		Iterator iter = home.findAll(CSProgramReferral.class);
		return iter;
	}//end of findAll()
	
    /**
     * Find all CSProgramReferral objects matching the given event attributes
     */
	static public Iterator findAll(IEvent event)
	{
	    	//initialize lookup objects
		IHome home = new Home();
		
			//use delegate to lookup program referrals matching the given event values
		return home.findAll(event, CSProgramReferral.class);
	}//end of findAll()

    /**
     * Find all CSProgramReferral objects matching the given event attributes
     */	
	static public Iterator findAll(String attrName, String attrValue) {
    		
	    	//initialize lookup objects
	    IHome home = new Home();
		Iterator csProgramReferrals = null;
		
			//use delegate to lookup program referrals with the given attribute/value matches
		csProgramReferrals = home.findAll(attrName, attrValue, CSProgramReferral.class);
		return csProgramReferrals;
	}
	/************ CSProgramReferral Lookup Methods ***********************/
	
	/**
	 * Bind entity to database
	 *
	 */
    public void bind()
    {
        IHome home = new Home();
        home.bind(this);
        
    }//end of bind()


	/**
	 * @return Returns the referralDate.
	 */
	public Date getReferralDate() {
		fetch();
		return referralDate;
	}
	/**
	 * @param referralDate The referralDate to set.
	 */
	public void setReferralDate(Date referralDate) {
		if (this.referralDate == null || !this.referralDate.equals(referralDate))
		{
			markModified();
		}
		this.referralDate = referralDate;
	}
	
	
	/**
	 * @return the programReferralId
	 */
	public String getProgramReferralId() {
		fetch();
		return programReferralId;
	}
	/**
	 * @return the submitComments
	 */
	public String getSubmitComments() {
		fetch();
		return submitComments;
	}


	/**
	 * @param submitComments the submitComments to set
	 */
	public void setSubmitComments(String submitComments) {
		if (this.submitComments == null || !this.submitComments.equals(submitComments))
		{
			markModified();
		}
		this.submitComments = submitComments;
	}


	/**
	 * @return the exitComments
	 */
	public String getExitComments() {
		fetch();
		return exitComments;
	}


	/**
	 * @param exitComments the exitComments to set
	 */
	public void setExitComments(String exitComments) {
		if (this.exitComments == null || !this.exitComments.equals(exitComments))
		{
			markModified();
		}
		this.exitComments = exitComments;
	}


	/**
	 * @return Returns the confinementDays.
	 */
	public int getConfinementDays() {
		fetch();
		return confinementDays;
	}
	/**
	 * @param confinementDays The confinementDays to set.
	 */
	public void setConfinementDays(int confinementDays) {
		if (this.confinementDays != confinementDays)
		{
			markModified();
		}
		this.confinementDays = confinementDays;
	}
	/**
	 * @return Returns the confinementMonths.
	 */
	public int getConfinementMonths() {
		fetch();
		return confinementMonths;
	}
	/**
	 * @param confinementMonths The confinementMonths to set.
	 */
	public void setConfinementMonths(int confinementMonths) {
		if (this.confinementMonths != confinementMonths)
		{
			markModified();
		}
		this.confinementMonths = confinementMonths;
	}
	/**
	 * @return Returns the confinementYears.
	 */
	public int getConfinementYears() {
		fetch();
		return confinementYears;
	}
	/**
	 * @param confinementYears The confinementYears to set.
	 */
	public void setConfinementYears(int confinementYears) {
		if (this.confinementYears != confinementYears)
		{
			markModified();
		}
		this.confinementYears = confinementYears;
	}
	/**
	 * @return Returns the criminalCaseId.
	 */
	public String getCriminalCaseId() {
		fetch();
		return criminalCaseId;
	}
	/**
	 * @param criminalCaseId The criminalCaseId to set.
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		if (this.criminalCaseId == null || !this.criminalCaseId.equals(criminalCaseId))
		{
			markModified();
		}
		this.criminalCaseId = criminalCaseId;
	}
	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		fetch();
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		if (this.defendantId == null || !this.defendantId.equals(defendantId))
		{
			markModified();
		}
		this.defendantId = defendantId;
	}
	/**
	 * @return the dischargeReasonCd
	 */
	public String getDischargeReasonCd() {
		fetch();
		return dischargeReasonCd;
	}
	/**
	 * @param dischargeReasonCd the dischargeReasonCd to set
	 */
	public void setDischargeReasonCd(String dischargeReasonCd) {
		if (this.dischargeReasonCd == null || !this.dischargeReasonCd.equals(dischargeReasonCd))
		{
			markModified();
		}
		this.dischargeReasonCd = dischargeReasonCd;
	}
	
	
	/**
	 * @return Returns the newServiceProviderFax.
	 */
	public String getNewServiceProviderFax() {
		fetch();
		return newServiceProviderFax;
	}
	/**
	 * @param newServiceProviderFax The newServiceProviderFax to set.
	 */
	public void setNewServiceProviderFax(String newServiceProviderFax) {
		if (this.newServiceProviderFax == null || !this.newServiceProviderFax.equals(newServiceProviderFax))
		{
			markModified();
		}
		this.newServiceProviderFax = newServiceProviderFax;
	}
	/**
	 * @return Returns the newServiceProviderName.
	 */
	public String getNewServiceProviderName() {
		fetch();
		return newServiceProviderName;
	}
	/**
	 * @param newServiceProviderName The newServiceProviderName to set.
	 */
	public void setNewServiceProviderName(String newServiceProviderName) {
		if (this.newServiceProviderName == null || !this.newServiceProviderName.equals(newServiceProviderName))
		{
			markModified();
		}
		this.newServiceProviderName = newServiceProviderName;
	}
	/**
	 * @return Returns the newServiceProviderPhone.
	 */
	public String getNewServiceProviderPhone() {
		fetch();
		return newServiceProviderPhone;
	}
	/**
	 * @param newServiceProviderPhone The newServiceProviderPhone to set.
	 */
	public void setNewServiceProviderPhone(String newServiceProviderPhone) {
		if (this.newServiceProviderPhone == null || !this.newServiceProviderPhone.equals(newServiceProviderPhone))
		{
			markModified();
		}
		this.newServiceProviderPhone = newServiceProviderPhone;
	}
	/**
	 * @return the placementReasonCd
	 */
	public String getPlacementReasonCd() {
		fetch();
		return placementReasonCd;
	}


	/**
	 * @param placementReasonCd the placementReasonCd to set
	 */
	public void setPlacementReasonCd(String placementReasonCd) {
		if (this.placementReasonCd == null || !this.placementReasonCd.equals(placementReasonCd))
		{
			markModified();
		}
		this.placementReasonCd = placementReasonCd;
	}


	/**
	 * @return Returns the programBeginDate.
	 */
	public Date getProgramBeginDate() {
		fetch();
		return programBeginDate;
	}
	/**
	 * @param programBeginDate The programBeginDate to set.
	 */
	public void setProgramBeginDate(Date programBeginDate) {
		if (this.programBeginDate == null || !this.programBeginDate.equals(programBeginDate))
		{
			markModified();
		}
		this.programBeginDate = programBeginDate;
	}
	/**
	 * @return Returns the programEndDate.
	 */
	public Date getProgramEndDate() {
		fetch();
		return programEndDate;
	}
	/**
	 * @param programEndDate The programEndDate to set.
	 */
	public void setProgramEndDate(Date programEndDate) {
		if (this.programEndDate == null || !this.programEndDate.equals(programEndDate))
		{
			markModified();
		}
		this.programEndDate = programEndDate;
	}
	/**
	 * @return Returns the programLocationId.
	 */
	public String getProgramLocationId() {
		fetch();
		return programLocationId;
	}
	/**
	 * @param programLocationId The programLocationId to set.
	 */
	public void setProgramLocationId(String programLocationId) {
		if (this.programLocationId == null || !this.programLocationId.equals(programLocationId))
		{
			markModified();
		}
		this.programLocationId = programLocationId;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isProgramUnitReferral() {
		
		fetch();
		return programUnitReferral;
	}


	/**
	 * 
	 * @param programUnitReferral
	 */
	public void setProgramUnitReferral(boolean programUnitReferral) {
		
		if ( this.programUnitReferral != programUnitReferral )
		{
			markModified();
		}
		
		this.programUnitReferral = programUnitReferral;
	}


	/**
	 * @return Returns the referralStatusCode.
	 */
	public String getReferralStatusCode() {
		fetch();
		return referralStatusCode;
	}
	/**
	 * @param referralStatusCode The referralStatusCode to set.
	 */
	public void setReferralStatusCode(String referralStatusCode) {
		if (this.referralStatusCode == null || !this.referralStatusCode.equals(referralStatusCode))
		{
			markModified();
		}
		this.referralStatusCode = referralStatusCode;
	}
	/**
	 * @return Returns the referralTypeCode.
	 */
	public String getReferralTypeCode() {
		fetch();
		return referralTypeCode;
	}
	/**
	 * @param referralTypeCode The referralTypeCode to set.
	 */
	public void setReferralTypeCode(String referralTypeCode) {
		if (this.referralTypeCode == null || !this.referralTypeCode.equals(referralTypeCode))
		{
			markModified();
		}
		this.referralTypeCode = referralTypeCode;
	}
	/**
	 * @return Returns the scheduleDate.
	 */
	public Date getScheduleDate() {
		fetch();
		return scheduleDate;
	}
	/**
	 * @param scheduleDate The scheduleDate to set.
	 */
	public void setScheduleDate(Date scheduleDate) {
		if (this.scheduleDate == null || !this.scheduleDate.equals(scheduleDate))
		{
			markModified();
		}
		this.scheduleDate = scheduleDate;
	}
	/**
	 * @return Returns the tracerNumber.
	 */
	public String getTracerNumber() {
		fetch();
		return tracerNumber;
	}
	/**
	 * @param tracerNumber The tracerNumber to set.
	 */
	public void setTracerNumber(String tracerNumber) {
		if (this.tracerNumber == null || !this.tracerNumber.equals(tracerNumber))
		{
			markModified();
		}
		this.tracerNumber = tracerNumber;
	}
	
	/**
	 * @return the isIncarcerationReferral
	 */
	public boolean isIncarcerationReferral() {
		fetch();
		return incarcerationReferral;
	}


	/**
	 * @param isIncarcerationReferral the isIncarcerationReferral to set
	 */
	public void setIncarcerationReferral(boolean incarcerationReferral) {
		if (this.incarcerationReferral != incarcerationReferral)
		{
			markModified();
		}
		this.incarcerationReferral = incarcerationReferral;
	}
	

	/**
	 * @return the isAutoReferral
	 */
	public boolean isAutoReferral() {
		fetch();
		return autoReferral;
	}

	/**
	 * @param isAutoReferral the isAutoReferral to set
	 */
	public void setAutoReferral(boolean autoReferral) {
		if (this.autoReferral != autoReferral)
		{
			markModified();
		}
		this.autoReferral = autoReferral;
	}
	/**
	 * @return the isContractProgram
	 */
	public boolean isContractProgram() {
		fetch();
		return contractProgram;
	}
	/**
	 * 
	 * @param isContractProgram the isContractProgram to set
	 */
	public void setContractProgram(boolean contractProgram) {
		if (this.contractProgram != contractProgram)
		{
			markModified();
		}
		this.contractProgram = contractProgram;
	}
}//end of CSProgramReferral class
