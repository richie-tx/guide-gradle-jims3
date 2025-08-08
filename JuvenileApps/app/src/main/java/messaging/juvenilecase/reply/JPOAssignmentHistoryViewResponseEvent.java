package messaging.juvenilecase.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class JPOAssignmentHistoryViewResponseEvent extends ResponseEvent implements Comparable {
	private String caseFileId;
	private String serviceUnitId;
	private String assignmentLevelId;
	private String referralNumber;
	private Date assignmentAddDate;
	private Date jpoAssignmentDate;
	private String officerProfileId;
	private String jpoName;
	private String jpoLogonId;
	private String assignedByName;
	private String assignedByLogonId;
	private String isMAYSINeededforReferrral;
	
	
	//US 79648
	private String assignmentType;
	private String assignmentTypeDescr;
	
	public String getCaseFileId() {
		return caseFileId;
	}
	public void setCaseFileId(String caseFileId) {
		this.caseFileId = caseFileId;
	}
	public String getServiceUnitId() {
		return serviceUnitId;
	}
	public void setServiceUnitId(String serviceUnitId) {
		this.serviceUnitId = serviceUnitId;
	}
	public String getAssignmentLevelId() {
		return assignmentLevelId;
	}
	public void setAssignmentLevelId(String assignmentLevelId) {
		this.assignmentLevelId = assignmentLevelId;
	}
	public String getReferralNumber() {
		return referralNumber;
	}
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
	public Date getAssignmentAddDate() {
		return assignmentAddDate;
	}
	public void setAssignmentAddDate(Date assignmentAddDate) {
		this.assignmentAddDate = assignmentAddDate;
	}
	public Date getJpoAssignmentDate() {
		return jpoAssignmentDate;
	}
	public void setJpoAssignmentDate(Date jpoAssignmentDate) {
		this.jpoAssignmentDate = jpoAssignmentDate;
	}
	public String getOfficerProfileId() {
		return officerProfileId;
	}
	public void setOfficerProfileId(String officerProfileId) {
		this.officerProfileId = officerProfileId;
	}
	public String getJpoName() {
		return jpoName;
	}
	public void setJpoName(String jpoName) {
		this.jpoName = jpoName;
	}
	public String getJpoLogonId() {
		return jpoLogonId;
	}
	public void setJpoLogonId(String jpoLogonId) {
		this.jpoLogonId = jpoLogonId;
	}
	public String getAssignedByName() {
		return assignedByName;
	}
	public void setAssignedByName(String assignedByName) {
		this.assignedByName = assignedByName;
	}
	public String getAssignedByLogonId() {
		return assignedByLogonId;
	}
	public void setAssignedByLogonId(String assignedByLogonId) {
		this.assignedByLogonId = assignedByLogonId;
	}
	public int compareTo(Object obj) {
		if(obj==null)
			return -1;
		JPOAssignmentHistoryViewResponseEvent evt = (JPOAssignmentHistoryViewResponseEvent) obj;
		int result = 0;
		if(getJpoAssignmentDate() != null && evt.getJpoAssignmentDate() != null )
		{
			try{
				if(this.jpoAssignmentDate!=null || evt.getJpoAssignmentDate()!=null){
					if(evt.getJpoAssignmentDate()==null)
						return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
					if(this.jpoAssignmentDate==null)
						return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
					
					if(this.jpoAssignmentDate==null)
						return -1;
					if(evt.getJpoAssignmentDate()==null)
						return 1;
					result= evt.getJpoAssignmentDate().compareTo(this.jpoAssignmentDate); // backwards in order to get list to show up most recent first
				}
			}
			catch(NumberFormatException e){
				result = 0;
			}
		} else {
			if(getAssignmentAddDate() != null && evt.getAssignmentAddDate() != null )
			{
				try{
					if(this.assignmentAddDate!=null || evt.getAssignmentAddDate()!=null){
						if(evt.getAssignmentAddDate()==null)
							return 1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
						if(this.assignmentAddDate==null)
							return -1; // this makes any null objects go to the bottom change this to 1 if you want the top of the list
						
						if(this.assignmentAddDate==null)
							return -1;
						if(evt.getAssignmentAddDate()==null)
							return 1;
						result= evt.getAssignmentAddDate().compareTo(this.assignmentAddDate); // backwards in order to get list to show up most recent first
					}
				}
				catch(NumberFormatException e){
					result = 0;
				}
			}
		}
		return result;
	}
	/**
	 * @return the assignmentType
	 */
	public String getAssignmentType()
	{
	    return assignmentType;
	}
	/**
	 * @param assignmentType the assignmentType to set
	 */
	public void setAssignmentType(String assignmentType)
	{
	    this.assignmentType = assignmentType;
	}
	/**
	 * @return the assignmentTypeDescr
	 */
	public String getAssignmentTypeDescr()
	{
	    return assignmentTypeDescr;
	}
	/**
	 * @param assignmentTypeDescr the assignmentTypeDescr to set
	 */
    public void setAssignmentTypeDescr(String assignmentTypeDescr)
    {
	this.assignmentTypeDescr = assignmentTypeDescr;
    }

    public String getIsMAYSINeededforReferrral()
    {
	return isMAYSINeededforReferrral;
    }

    public void setIsMAYSINeededforReferrral(String isMAYSINeededforReferrral)
    {
	this.isMAYSINeededforReferrral = isMAYSINeededforReferrral;
    }

}
