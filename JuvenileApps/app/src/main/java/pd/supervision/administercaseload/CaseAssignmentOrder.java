package pd.supervision.administercaseload;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.administercaseload.GetCaseloadEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.contact.domintf.IName;
import messaging.contact.domintf.IPhoneNumber;
import messaging.contact.to.NameBean;
import messaging.contact.to.PhoneNumberBean;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.CollectionUtil;
import naming.CaseloadConstants;
import naming.PDConstants;
import pd.common.util.StringUtil;
import pd.contact.user.UserProfile;
import pd.criminalcase.CriminalCase;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;

/**
 * @author Jim Fisher
 */
public class CaseAssignmentOrder extends CaseAssignment
{

    public static List findAllByOfficer(String aPositionId)
    {
        IHome home = new Home();
        return CollectionUtil.iteratorToList(home.findAll("assignedStaffPositionId", aPositionId, CaseAssignmentOrder.class));
    }

    public static List findAllBySupervisee(String aDefendantId)
    {
        IHome home = new Home();
        aDefendantId = StringUtil.padString(aDefendantId, CaseloadConstants.DEFENDANT_ID_PAD_CHAR, CaseloadConstants.DEFENDANT_ID_LEN);
        return CollectionUtil.iteratorToList(home.findAll("defendantId", aDefendantId, CaseAssignmentOrder.class));
    }

    public static List findAllBySuperviseeName(GetCaseloadEvent anEvent)
    {
        IHome home = new Home();
        return CollectionUtil.iteratorToList(home.findAll(anEvent, CaseAssignmentOrder.class));
    }
    
    public static Iterator findAllByEvent(IEvent anEvent)
    {
        return new Home().findAll(anEvent, CaseAssignmentOrder.class);
    }
    
    public static List findAllEvent(IEvent anEvent)
    {
    	IHome home = new Home();
    	return CollectionUtil.iteratorToList(home.findAll(anEvent, CaseAssignmentOrder.class));
    }
    
    public static List findByCaseNumber(String caseNumber)
    {
        IHome home = new Home();
        return CollectionUtil.iteratorToList(home.findAll("criminalCaseId", caseNumber, CaseAssignmentOrder.class));
    }
    
    private String courtId;

    private String criminalCaseId;
    private CriminalCase criminalCase;

    private String defendantId;

    private String officerUserId;

    private String orderStatusCode;

    private String programUnitName;
    
    private String supervisorPositionId;
    
    private Date orderFiledDate;
    
    private int versionNumber;
    
    private String supervisionOrderId;
    
    private String versionTypeCode;
    
    private String probationOfficerInd;
    
    private Date supervisorAllocatedDate;
    
    /// this is for another view
    private Date supervisionBeginDate;
    private Date supervisionEndDate;
    private String caseStatusId;
    private String defendantStatusId;
    private String defendantName;
    private String jailInd;
    private String warrantInd;
    // this is added to facilitate a view, do not remove
    private String caseAssignId;
    private String pmKeyCls;
    
    private String offenseCode;
    

	public String getCaseAssignId() {
		return caseAssignId;
	}

	public void setCaseAssignId(String caseAssignmentId) {
		this.caseAssignId = caseAssignmentId;
	}

	/**
	 * @return the supervisorAllocatedDate
	 */
	public Date getSupervisorAllocatedDate() {
		return supervisorAllocatedDate;
	}

	/**
	 * @param supervisorAllocatedDate the supervisorAllocatedDate to set
	 */
	public void setSupervisorAllocatedDate(Date supervisorAllocatedDate) {
		this.supervisorAllocatedDate = supervisorAllocatedDate;
	}

	/**
	 * @return Returns the versionTypeCode.
	 */
	public String getVersionTypeCode() {
		return versionTypeCode;
	}
	/**
	 * @param versionTypeCode The versionTypeCode to set.
	 */
	public void setVersionTypeCode(String versionTypeCode) {
		this.versionTypeCode = versionTypeCode;
	}
	/**
	 * @return Returns the supervisionOrderId.
	 */
	public String getSupervisionOrderId() {
		return supervisionOrderId;
	}
	/**
	 * @param supervisionOrderId The supervisionOrderId to set.
	 */
	public void setSupervisionOrderId(String supervisionOrderId) {
		this.supervisionOrderId = supervisionOrderId;
	}
    /**
     * @return Returns the courtId.
     */
    public String getCourtId()
    {
        fetch();
        return courtId;
    }

    /**
     * @return Returns the criminalCaseId.
     */
    public String getCriminalCaseId()
    {
        fetch();
        return criminalCaseId;
    }

    /**
     * @return Returns the defendantId.
     */
    public String getDefendantId()
    {
        fetch();
        return defendantId;
    }

    /**
     * @return Returns the officerUserId.
     */
    public String getOfficerUserId()
    {
        fetch();
        return officerUserId;
    }

    /**
     * @return Returns the orderStatusCode.
     */
    public String getOrderStatusCode()
    {
        fetch();
        return orderStatusCode;
    }

    /**
     * @return Returns the programUnitName.
     */
    public String getProgramUnitName()
    {
        fetch();
        return programUnitName;
    }

    /**
     * @param courtId
     *        The courtId to set.
     */
    public void setCourtId(String courtId)
    {
        if (courtId.length() > 3)
        {
            this.courtId = courtId.substring(3);
            if (this.courtId.length() > 3)
            {
            	this.courtId = this.courtId.substring(1,4);           	
            	
            } 
        }else{
        	this.courtId = courtId;
        }
    }

    /**
     * @param criminalCaseId
     *        The criminalCaseId to set.
     */
    public void setCriminalCaseId(String criminalCaseId)
    {
        this.criminalCaseId = criminalCaseId;
    }

    /**
     * @param defendantId
     *        The defendantId to set.
     */
    public void setDefendantId(String defendantId)
    {
        this.defendantId = defendantId;
    }

    /**
     * @param officerUserId
     *        The officerUserId to set.
     */
    public void setOfficerUserId(String officerUserId)
    {
        this.officerUserId = officerUserId;
    }

    /**
     * @param orderStatusCode
     *        The orderStatusCode to set.
     */
    public void setOrderStatusCode(String orderStatusCode)
    {
        this.orderStatusCode = orderStatusCode;
    }

    /**
     * @param programUnitName
     *        The programUnitName to set.
     */
    public void setProgramUnitName(String programUnitName)
    {
        this.programUnitName = programUnitName;
    }

    /**
     * @return
     */
    public ICaseAssignment valueObject()
    {
        ICaseAssignment caseAssignment = super.valueObject();

        caseAssignment.setProgramUnitName(this.getProgramUnitName());
        caseAssignment.setDefendantId(this.getDefendantId());
        caseAssignment.setOrderStatusCode(this.getOrderStatusCode());
        caseAssignment.setCourtId(this.getCourtId());
               
        //this has been added to prevent null pointer exception of phonenumber
        caseAssignment.setOfficerPhoneNumber(new PhoneNumberBean());
        
        CSCDStaffPosition assignedStaffPosition = this.getAssignedStaffPosition();
		if (assignedStaffPosition != null) {
			UserProfile userProfile = assignedStaffPosition.getUserProfile();
			if (userProfile != null) {
				caseAssignment.setOfficerName(userProfile.getName());
			}
		}
		
        return caseAssignment;
    }
    private static final String NO_OFFICER_ASSIGNED = "NO OFFICER ASSIGNED";
    /**
     * @return
     */
    public ICaseAssignment detailsValueObject()
    {
        ICaseAssignment caseAssignment = this.valueObject();
        caseAssignment.setCdi(caseAssignment.getCriminalCaseId().substring(0,3));
        UserProfile user = null;
        CSCDStaffPosition sPos1 = this.getAssignedStaffPosition();
        if(sPos1 != null){
        	caseAssignment.setAssignedStaffPositionName(sPos1.getPositionName());  
        	caseAssignment.setProbationOfficerInd(sPos1.getProbationOfficerInd());
        	IPhoneNumber phone = new PhoneNumberBean(sPos1.getPhoneNum());
        	caseAssignment.setOfficerPhoneNumber(phone);
        	caseAssignment.setOfficerAssignDate(this.getOfficerAssignDate());

        	if(sPos1.getUserProfileId() != null && !"".equals(sPos1.getUserProfileId())){
        		user = UserProfile.find(sPos1.getUserProfileId());
        		if (user != null) {
                	caseAssignment.setOfficerName(user.getName()); 
                }
        	}        	
        }
        IName aName = null;
        if (caseAssignment.getOfficerName() == null){
        	aName = new NameBean();
        	aName.setLastName(NO_OFFICER_ASSIGNED);
        	aName.setFirstName(PDConstants.BLANK);
        	aName.setMiddleName(PDConstants.BLANK);
        	caseAssignment.setOfficerName(aName);
        }
        
        if(this.getAllocatedStaffPositionId() != null && !"".equals(this.getAllocatedStaffPositionId())){
	        CSCDStaffPosition sPos = CSCDStaffPosition.find(this.getAllocatedStaffPositionId());
	        if(sPos != null && sPos.getUserProfileId() != null){
	        	user = UserProfile.find(sPos.getUserProfileId());
	        	if (user != null) {
	            	caseAssignment.setSupervisorName(user.getName());
	            }
	        }
        }        
        if (caseAssignment.getSupervisorName() == null){
        	caseAssignment.setSuperviseeName(NO_OFFICER_ASSIGNED);
        }
        
        String userId = caseAssignment.getAcknowledgeUserId();
        if(userId != null && !"".equals(userId)){
        	user = UserProfile.find(userId);
        	if(user != null){
            	caseAssignment.setAcknowledgeUserName(user.getName());
            	caseAssignment.setAcknowledgeUserId(user.getLogonId());
            }     
        }  
        
        if (caseAssignment.getAcknowledgeUserName() == null){
        	aName = new NameBean();
        	aName.setLastName(NO_OFFICER_ASSIGNED);
        	aName.setFirstName(PDConstants.BLANK);
        	aName.setMiddleName(PDConstants.BLANK);
        	caseAssignment.setAcknowledgeUserName(aName);
        }
        return caseAssignment;
    }
    
	/**
	 * @return Returns the supervisorPositionId.
	 */
	public String getSupervisorPositionId() {
	     fetch();
		return supervisorPositionId;
	}
	/**
	 * @param supervisorPositionId The supervisorPositionId to set.
	 */
	public void setSupervisorPositionId(String supervisorPositionId) {
		this.supervisorPositionId = supervisorPositionId;
	}
	public CriminalCase getCriminalCase()
	{
		fetch();
		initCriminalCase();
		return criminalCase;
	}
	/**
	 * Initialize class relationship to class pd.criminalcase.CriminalCase
	 */
	private void initCriminalCase()
	{
		if (criminalCase == null)
		{
			criminalCase = (CriminalCase) new mojo.km.persistence.Reference(criminalCaseId,
					CriminalCase.class).getObject();
		}
	}

	/**
	 * @return Returns the orderFiledDate.
	 */
	public Date getOrderFiledDate() {
		return orderFiledDate;
	}
	/**
	 * @param orderFiledDate The orderFiledDate to set.
	 */
	public void setOrderFiledDate(Date orderFiledDate) {
		this.orderFiledDate = orderFiledDate;
	}
	public String getPmKeyCls() {
		return pmKeyCls;
	}

	public void setPmKeyCls(String pmKeyCls) {
		this.pmKeyCls = pmKeyCls;
	}

	/**
	 * @return Returns the versionNumber.
	 */
	public int getVersionNumber() {
		return versionNumber;
	}
	/**
	 * @param versionNumber The versionNumber to set.
	 */
	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}

	/**
	 * @return the probationOfficerInd
	 */
	public String getProbationOfficerInd() {
		return probationOfficerInd;
	}

	/**
	 * @param probationOfficerInd the probationOfficerInd to set
	 */
	public void setProbationOfficerInd(String probationOfficerInd) {
		this.probationOfficerInd = probationOfficerInd;
	}

	public Date getSupervisionBeginDate() {
		return supervisionBeginDate;
	}

	public void setSupervisionBeginDate(Date supervisionBeginDate) {
		this.supervisionBeginDate = supervisionBeginDate;
	}

	public Date getSupervisionEndDate() {
		return supervisionEndDate;
	}

	public void setSupervisionEndDate(Date supervisionEndDate) {
		this.supervisionEndDate = supervisionEndDate;
	}

	public String getCaseStatusId() {
		return caseStatusId;
	}

	public void setCaseStatusId(String caseStatusId) {
		this.caseStatusId = caseStatusId;
	}

	public String getDefendantStatusId() {
		return defendantStatusId;
	}

	public void setDefendantStatusId(String defendantStatusId) {
		this.defendantStatusId = defendantStatusId;
	}

	public String getDefendantName() {
		return defendantName;
	}

	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}

	public String getJailInd() {
		return jailInd;
	}

	public void setJailInd(String jailInd) {
		this.jailInd = jailInd;
	}

	public String getWarrantInd() {
		return warrantInd;
	}

	public void setWarrantInd(String warrantInd) {
		this.warrantInd = warrantInd;
	}

	public void setCriminalCase(CriminalCase criminalCase) {
		this.criminalCase = criminalCase;
	}

	public String getOffenseCode() {
		fetch();
		return offenseCode;
	}

	public void setOffenseCode(String offenseCode) {
        if (this.offenseCode == null || !this.offenseCode.equals(offenseCode)) {
            markModified();
        }
		this.offenseCode = offenseCode;
	}

	public static Iterator findAllByOfficerEvent(IEvent event){
	        IHome home = new Home();
	        return home.findAll(event, CaseAssignmentOrder.class);
	}

}
