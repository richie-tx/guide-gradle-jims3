/*
 * Created on May 17, 2007
 *
 */
package messaging.programreferral.reply;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.Name;



/**
 *
 */
public class ProgramReferralResponseEvent extends ResponseEvent implements Comparable, IAddressable{
	
	private String referralId;
	
	private boolean excluded = false;

	private String outComeDesc;

	private Date acknowledgementDate;

	private String assignedHours;
	private float creditHours;

	private Date beginDate;

	private Date endDate;

	private boolean courtOrdered;	
	
	private String referralStatusCd;
	private String referralSubStatusCd;
	
	private String referralStatusDescription;
	private String referralSubStatusDescription;
		
	private String provProgramId;
	private String provProgramCode;
	private String juvProgRefId;
	
	private String casefileId;
	private String supervisionName;	
	private String supervisionType;

	

	private Date sentDate;
	private Date lastActionDate;
	
	private String outComeCd;
	private String outComeSubcategoryCd;

	private String statusReason;
			
	private String juvenileId;	
	private String juvenileLastName;
	private String juvenileFirstName;
	private String juvenileMiddleName;
	private String juvenileFullName;
	
	private String officerId;
	private String officerLastName;
	private String officerMiddleName;
	private String officerFirstName;
	private String officerFullName;
	private Name formattedOfficerName = new Name();
	
	private String providerProgramName;
	
	private String juvServiceProviderId;
	private String juvServiceProviderName;
	private String phone;
	private String extnNum;
	private boolean inHouse;
	private String createdBy;
	/*private String notificationMessage; //added for notification
	private String subject;//added for notification
	private String identity;//added for notification
	*/
	private List referralComments;
	
	private String programScheduleTypeId;//added for 11099
	
	//Added for US 32107 - Restricted Access marker
	private String restrictedAccess;
	private String fundSource;
	private String juvLocUnitId;
	private int timeInProgram;
	
	private String contactName;
	private String contactPhone;
	
	private String officerLocationUnit;
	private String officerLocationUnitName;

	/**
	 * @return Returns the officerLocationUnit.
	 */
	public String getOfficerLocationUnit() {
	    return officerLocationUnit;
	}

	/**
	 * @param officerLocationUnit The officerLocationUnit to set.
	 */
	public void setOfficerLocationUnit(String officerLocationUnit) {
	    this.officerLocationUnit = officerLocationUnit;
	}

	/**
	 * @return Returns the officerLocationUnitName.
	 */
	public String getOfficerLocationUnitName() {
	    return officerLocationUnitName;
	}

	/**
	 * @param officerLocationUnitName The officerLocationUnitName to set.
	 */
	public void setOfficerLocationUnitName(String officerLocationUnitName) {
	    this.officerLocationUnitName = officerLocationUnitName;
	}

	
	/**
	 * @return Returns the acknowledgementDate.
	 */
	public Date getAcknowledgementDate() {
		return acknowledgementDate;
	}
	/**
	 * @param acknowledgementDate The acknowledgementDate to set.
	 */
	public void setAcknowledgementDate(Date acknowledgementDate) {
		this.acknowledgementDate = acknowledgementDate;
	}
	/**
	 * @return Returns the assignedHours.
	 */
	public String getAssignedHours() {
		return assignedHours;
	}
	/**
	 * @param assignedHours The assignedHours to set.
	 */
	public void setAssignedHours(String assignedHours) {
		this.assignedHours = assignedHours;
	}
	
	public float getCreditHours( )
	{	    
		return this.creditHours;
	}
	
	public void setCreditHours(float credithours){
	    
	    this.creditHours = credithours;
	}
	
	/**
	 * @return Returns the beginDate.
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	/**
	 * @param beginDate The beginDate to set.
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId The casefileId to set.
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	/**
	 * @return Returns the courtOrdered.
	 */
	public boolean isCourtOrdered() {
		return courtOrdered;
	}
	/**
	 * @param courtOrdered The courtOrdered to set.
	 */
	public void setCourtOrdered(boolean courtOrdered) {
		this.courtOrdered = courtOrdered;
	}
	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the juvenileFirstName.
	 */
	public String getJuvenileFirstName() {
		return juvenileFirstName;
	}
	/**
	 * @param juvenileFirstName The juvenileFirstName to set.
	 */
	public void setJuvenileFirstName(String juvenileFirstName) {
		this.juvenileFirstName = juvenileFirstName;
	}
	/**
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId() {
		return juvenileId;
	}
	/**
	 * @param juvenileId The juvenileId to set.
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	/**
	 * @return Returns the juvenileLastName.
	 */
	public String getJuvenileLastName() {
		return juvenileLastName;
	}
	/**
	 * @param juvenileLastName The juvenileLastName to set.
	 */
	public void setJuvenileLastName(String juvenileLastName) {
		this.juvenileLastName = juvenileLastName;
	}
	/**
	 * @return Returns the juvenileMiddleName.
	 */
	public String getJuvenileMiddleName() {
		return juvenileMiddleName;
	}
	/**
	 * @param juvenileMiddleName The juvenileMiddleName to set.
	 */
	public void setJuvenileMiddleName(String juvenileMiddleName) {
		this.juvenileMiddleName = juvenileMiddleName;
	}
	/**
	 * @return Returns the juvServiceProviderId.
	 */
	public String getJuvServiceProviderId() {
		return juvServiceProviderId;
	}
	/**
	 * @param juvServiceProviderId The juvServiceProviderId to set.
	 */
	public void setJuvServiceProviderId(String juvServiceProviderId) {
		this.juvServiceProviderId = juvServiceProviderId;
	}
	/**
	 * @return Returns the juvServiceProviderName.
	 */
	public String getJuvServiceProviderName() {
		return juvServiceProviderName;
	}
	/**
	 * @param juvServiceProviderName The juvServiceProviderName to set.
	 */
	public void setJuvServiceProviderName(String juvServiceProviderName) {
		this.juvServiceProviderName = juvServiceProviderName;
	}
	/**
	 * @return Returns the officerFirstName.
	 */
	public String getOfficerFirstName() {
		return officerFirstName;
	}
	/**
	 * @param officerFirstName The officerFirstName to set.
	 */
	public void setOfficerFirstName(String officerFirstName) {
		this.officerFirstName = officerFirstName;
	}
	/**
	 * @return Returns the officerId.
	 */
	public String getOfficerId() {
		return officerId;
	}
	/**
	 * @param officerId The officerId to set.
	 */
	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}
	/**
	 * @return Returns the officerLastName.
	 */
	public String getOfficerLastName() {
		return officerLastName;
	}
	/**
	 * @param officerLastName The officerLastName to set.
	 */
	public void setOfficerLastName(String officerLastName) {
		this.officerLastName = officerLastName;
	}
	/**
	 * @return Returns the officerMiddleName.
	 */
	public String getOfficerMiddleName() {
		return officerMiddleName;
	}
	/**
	 * @param officerMiddleName The officerMiddleName to set.
	 */
	public void setOfficerMiddleName(String officerMiddleName) {
		this.officerMiddleName = officerMiddleName;
	}
	/**
	 * @return Returns the providerProgramName.
	 */
	public String getProviderProgramName() {
		return providerProgramName;
	}
	/**
	 * @param providerProgramName The providerProgramName to set.
	 */
	public void setProviderProgramName(String providerProgramName) {
		this.providerProgramName = providerProgramName;
	}
	/**
	 * @return Returns the provProgramId.
	 */
	public String getProvProgramId() {
		return provProgramId;
	}
	/**
	 * @param provProgramId The provProgramId to set.
	 */
	public void setProvProgramId(String provProgramId) {
		this.provProgramId = provProgramId;
	}
	
	public String getProvProgramCode() {
		return provProgramCode;
	}
	
	public void setProvProgramCode(String programCode) {
		this.provProgramCode = programCode;
	}

	/**
	 * @return Returns the sentDate.
	 */
	public Date getSentDate() {
		return sentDate;
	}
	/**
	 * @param sentDate The sentDate to set.
	 */
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	/**
	 * @return Returns the statusReason.
	 */
	public String getStatusReason() {
		return statusReason;
	}
	/**
	 * @param statusReason The statusReason to set.
	 */
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	/**
	 * @return Returns the referralId.
	 */
	public String getReferralId() {
		return referralId;
	}
	/**
	 * @param referralId The referralId to set.
	 */
	public void setReferralId(String referralId) {
		this.referralId = referralId;
	}

	/**
	 * @return Returns the referralComments.
	 */
	public List getReferralComments() {
		return referralComments;
	}
	/**
	 * @param referralComments The referralComments to set.
	 */
	public void setReferralComments(List referralComments) {
		this.referralComments = referralComments;
	}
	
	public String getJuvenileName(){
		Name name = new Name(this.getJuvenileFirstName(),this.getJuvenileMiddleName(),this.getJuvenileLastName());
		return name.getFormattedName();		
	}
	
	public String getOfficerName(){
		Name name = new Name(this.getOfficerFirstName(),this.getOfficerMiddleName(),this.getOfficerLastName());
		return name.getFormattedName();		
	}
	/**
	 * @return Returns the lastActionDate.
	 */
	public Date getLastActionDate() {
		return lastActionDate;
	}
	/**
	 * @param lastActionDate The lastActionDate to set.
	 */
	public void setLastActionDate(Date lastActionDate) {
		this.lastActionDate = lastActionDate;
	}
	/**
	 * @return Returns the outComeCd.
	 */
	public String getOutComeCd() {
		return outComeCd;
	}
	/**
	 * @param outComeCd The outComeCd to set.
	 */
	public void setOutComeCd(String outComeCd) {
		this.outComeCd = outComeCd;
	}

	/**
	 * @return the outComeSubcategoryCd
	 */
	public String getOutComeSubcategoryCd() {
		return outComeSubcategoryCd;
	}
	/**
	 * @param outComeSubcategoryCd the outComeSubcategoryCd to set
	 */
	public void setOutComeSubcategoryCd(String outComeSubcategoryCd) {
		this.outComeSubcategoryCd = outComeSubcategoryCd;
	}
	/**
	 * @return Returns the referralStatusCd.
	 */
	public String getReferralStatusCd() {
		return referralStatusCd;
	}
	/**
	 * @param referralStatusCd The referralStatusCd to set.
	 */
	public void setReferralStatusCd(String referralStatusCd) {
		this.referralStatusCd = referralStatusCd;
	}

	/**
	 * @return Returns the referralSubStatusCd.
	 */
	public String getReferralSubStatusCd() {
		return referralSubStatusCd;
	}
	/**
	 * @param referralSubStatusCd The referralSubStatusCd to set.
	 */
	public void setReferralSubStatusCd(String referralSubStatusCd) {
		this.referralSubStatusCd = referralSubStatusCd;
	}
	
	

/*
	*//**
	 * @return Returns the notificationMessage.
	 *//*
	public String getNotificationMessage() {
		return notificationMessage;
	}
	*//**
	 * @param notificationMessage The notificationMessage to set.
	 *//*
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
	*//**
	 * @return Returns the subject.
	 *//*
	public String getSubject() {
		return subject;
	}
	*//**
	 * @param subject The subject to set.
	 *//*
	public void setSubject(String subject) {
		this.subject = subject;
	}
	*//**
	 * @return Returns the identity.
	 *//*
	public String getIdentity() {
		return identity;
	}
	*//**
	 * @param identity The identity to set.
	 *//*
	public void setIdentity(String identity) {
		this.identity = identity;
	}
*/


	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		if (obj==null || ! (obj instanceof ProgramReferralResponseEvent))
			return 0;		
		ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent)obj;
		//return ((this.getLastActionDate()==null || resp.getLastActionDate()==null) ? 0 : this.getLastActionDate().compareTo(resp.getLastActionDate()));
		return ((this.getLastActionDate()==null || resp.getLastActionDate()==null) ? 0 : resp.getLastActionDate().compareTo(this.getLastActionDate()));
	}
	
	public static Comparator refDateComparator = new Comparator() {
		public int compare(Object ref1, Object ref2) {
			 Date date1 = ((ProgramReferralResponseEvent)ref1).getSentDate();
			 Date date2 = ((ProgramReferralResponseEvent)ref2).getSentDate();			
		     int result = 0;

		        try
		        {
		            if (date1 != null || date2 != null)
		            {
		                if (date1 == null)
		                    return -1; // this makes any null objects go to the bottom change this to 1 if
		                               // you want the top of the list
		                if (date2 == null)
		                    return 1; // this makes any null objects go to the bottom change this to -1 if
		                              // you want the top of the list
		                //result = date1.compareTo(date2); // backwards in order to
		                                                                            // get list to show up
		                                                                            // most recent first
		                result = date2.compareTo(date1);
		            }

		        }
		        catch (NumberFormatException e)
		        {
		            result = 0;
		        }

		        return result;
		}	
	};
	
	public static Comparator refNameComparator = new Comparator() {
		public int compare(Object ref1, Object ref2) {
			String name1 = ((ProgramReferralResponseEvent)ref1).getJuvenileLastName();
			String name2 = ((ProgramReferralResponseEvent)ref2).getJuvenileLastName();			

			int result = 0;

			if (name1 != null || name2 != null)
			{
				if (name1 == null)
					return -1; // this makes any null objects go to the bottom change this to 1 if
				// you want the top of the list
				if (name2 == null)
					return 1; // this makes any null objects go to the bottom change this to -1 if
				// you want the top of the list
				result = name1.compareTo(name2);
			}
			return result;
		}	
	};
	
	public static Comparator CaseReviewJournalSummaryProgramReferralComparator = new Comparator() {
		public int compare(Object referral, Object otherReferral) {
			
		  int result = 0;
		  String referralStatusCd = ((ProgramReferralResponseEvent)referral).getReferralStatusCd();
		  String otherReferralStatusCd = ((ProgramReferralResponseEvent)otherReferral).getReferralStatusCd();
		  String providerName = ((ProgramReferralResponseEvent)referral).getJuvServiceProviderName();
		  String otherProviderName = ((ProgramReferralResponseEvent)otherReferral).getJuvServiceProviderName();
		  
		  if(referralStatusCd == null || otherReferralStatusCd.equals("")){
			  result = -1;
		  } else if(referralStatusCd == null || otherReferralStatusCd.equals("")){
			  result = 1;
		  }else{
			  result = referralStatusCd.compareTo(otherReferralStatusCd);
		  }
		  
		  if(result == 0){
			  if(providerName == null || providerName.equals(""))
			  {
				  result = -1;
			  }else if(otherProviderName == null || otherProviderName.equals(""))
			  {
				  result = 1;
			  }
			  else 
			  {
				  result = providerName.compareTo(otherProviderName);
			  }
		  }
		  return result;
		}	
	};
	/**
	 * @return Returns the referralStatusDescription.
	 */
	public String getReferralStatusDescription() {
		return referralStatusDescription;
	}
	/**
	 * @param referralStatusDescription The referralStatusDescription to set.
	 */
	public void setReferralStatusDescription(String referralStatusDescription) {
		this.referralStatusDescription = referralStatusDescription;
	}
	/**
	 * @return Returns the referralSubStatusDescription.
	 */
	public String getReferralSubStatusDescription() {
		return referralSubStatusDescription;
	}
	/**
	 * @param referralSubStatusDescription The referralSubStatusDescription to set.
	 */
	public void setReferralSubStatusDescription(String referralSubStatusDescription) {
		this.referralSubStatusDescription = referralSubStatusDescription;
	}
	
	public static Comparator CasefileIdComparator = new Comparator() {
		public int compare(Object response1, Object response2) {
			if (response1==null || ! (response1 instanceof ProgramReferralResponseEvent))
				return 0;		
			if (response2==null || ! (response2 instanceof ProgramReferralResponseEvent))
				return 0;			
			
		  String casefileId1 = ((ProgramReferralResponseEvent)response1).getCasefileId();
		  String casefileId2 = ((ProgramReferralResponseEvent)response2).getCasefileId();
		  
		  if (casefileId1==null) return  1;
		  if (casefileId2==null) return  -1;
		  return casefileId1.compareTo(casefileId2);
		}	
	};
	/**
	 * @return Returns the extnNum.
	 */
	public String getExtnNum() {
		return extnNum;
	}
	/**
	 * @param extnNum The extnNum to set.
	 */
	public void setExtnNum(String extnNum) {
		this.extnNum = extnNum;
	}
	/**
	 * @return Returns the inHouse.
	 */
	public boolean isInHouse() {
		return inHouse;
	}
	/**
	 * @param inHouse The inHouse to set.
	 */
	public void setInHouse(boolean inHouse) {
		this.inHouse = inHouse;
	}
	/**
	 * @return Returns the phone.
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isExcluded() {
		return excluded;
	}
	public void setExcluded(boolean excluded) {
		this.excluded = excluded;
	}
	public boolean isIncluded() {
		return ! excluded;
	}
	
	public void setIncluded(boolean included) {
		this.excluded = !included;
	}
	public String getOutComeDesc() {
		return outComeDesc;
	}
	public void setOutComeDesc(String outComeDesc) {
		this.outComeDesc = outComeDesc;
	}
	public String getJuvenileFullName() {
		return juvenileFullName;
	}
	public void setJuvenileFullName(String juvenileFullName) {
		this.juvenileFullName = juvenileFullName;
	}
	 /**
     * @return
     */
    public Name getFormattedOfficerName()
    {
        return formattedOfficerName;
    }
    
   
	public String getOfficerFullName() {
		return officerFullName;
	}
	public void setOfficerFullName(String officerFullName) {
		this.officerFullName = officerFullName;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getRestrictedAccess() {
		return restrictedAccess;
	}
	public void setRestrictedAccess(String restrictedAccess) {
		this.restrictedAccess = restrictedAccess;
	}
	/**
	 * @return the programScheduleTypeId
	 */
	public String getProgramScheduleTypeId() {
		return programScheduleTypeId;
	}
	/**
	 * @param programScheduleTypeId the programScheduleTypeId to set
	 */
	public void setProgramScheduleTypeId(String programScheduleTypeId) {
		this.programScheduleTypeId = programScheduleTypeId;
	}
	public String getFundSource()
	{
	    return fundSource;
	}
	public void setFundSource(String fundSource)
	{
	    this.fundSource = fundSource;
	}
	public String getJuvLocUnitId()
	{
	    return juvLocUnitId;
	}
	public void setJuvLocUnitId(String juvLocUnitId)
	{
	    this.juvLocUnitId = juvLocUnitId;
	}
	public int getTimeInProgram()
	{
	    return timeInProgram;
	}
	public void setTimeInProgram(int timeInProgram)
	{
	    this.timeInProgram = timeInProgram;
	}
	public String getJuvProgRefId()
	{
	    return juvProgRefId;
	}
	public void setJuvProgRefId(String juvProgRefId)
	{
	    this.juvProgRefId = juvProgRefId;
	}
	
	public String getContactName(){
	    return this.contactName;
	}
	public void setContactName(String contactName){
	    this.contactName = contactName;
	}
	
	public String getContactPhone(){
	    return this.contactPhone;
	}
	public void setContactPhone(String contactPhone){
	    this.contactPhone = contactPhone;
	}
	public String getSupervisionName()
	{
	    return supervisionName;
	}
	public void setSupervisionName(String supervisionName)
	{
	    this.supervisionName = supervisionName;
	}
	public String getSupervisionType()
	{
	    return supervisionType;
	}
	public void setSupervisionType(String supervisionType)
	{
	    this.supervisionType = supervisionType;
	}
}
