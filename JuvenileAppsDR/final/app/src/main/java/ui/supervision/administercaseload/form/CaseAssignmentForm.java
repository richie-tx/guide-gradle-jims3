/*
 * Created on May 10, 2007
 *
 */
package ui.supervision.administercaseload.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.administercaseload.to.CaseloadSummaryTO;
import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.cscdstaffposition.GetCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.manageworkgroup.GetWorkGroupsEvent;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import messaging.task.domintf.ITask;
import messaging.task.domintf.ITaskState;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.CaseloadConstants;
import naming.UIConstants;
import naming.WorkGroupControllerServiceNames;

import org.apache.struts.action.ActionForm;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.adminstaff.UIAdminStaffHelper;
import ui.task.ITaskRestorable;

/**
 * @author cc_rsojitrawala
 *  
 */
public class CaseAssignmentForm extends ActionForm implements ITaskRestorable
{	
	// Default Elements in all forms
    private static List emptyColl = new ArrayList(); 
	
	/**
     * @return Returns the emptyColl.
     */
    public static List getEmptyColl()
    {
        return emptyColl;
    } 
	
	/**
     * @param emptyColl
     *        The emptyColl to set.
     */
    public static void setEmptyColl(List emptyColl)
    {
        CaseAssignmentForm.emptyColl = emptyColl;
    } 
	
	private String action = ""; 
	
	/**
     * List of active cases associated with the defendantId;
     */
    private List activeCases;
	/**
     * List containing subset of active cases selected by user for reassignment. 
     */
    private List activeCasesSelectedForReassignment;
	private String activityInd;
	public String agencyId;
	private String allowTransfers;
	private String assignmentReturnReason = "";
	private String calendarEndDateStr = "";
	private String calendarStartDateStr = "";
	
	private String caseAssignmentId;
	
	
	public List caseAssignments ;
	//Case load of an officer
    private List caseloads;
		
	
    private String casenoteDate;

    private String casenoteText = "";

    private String casenoteTime;
    
    
    // For division reassignment
    
    private String caseNum; 
	private String userAgency;

    /**
     * Array of Case Assignment Ids, pertaining to the cases selected by officer for reassignment.
     */
    private String[] casesSelectedForReassignment; 
    
    // For division reassignment
    
    private ICaseAssignment caseToAcknowledge; 
    
	private String cdi;
	private boolean closeCases = false;
    private List closeCasesList = new ArrayList();
    private List closeCasesResultBeanList = new ArrayList();
    private boolean closeCasesSuccess = false;

    private String confirmationMessageString = "<No message set.  Needs message to be set >" ;

    private String courtNumber;

    /**
     * List of courts
     */
    private List courts;

    /**
     * Workgroups of type "RC"
     */
    private List courtServicesWorkGroups;

    /**
	 * Days left for probation
	 */
	private String daysLeft;

    private String defendantId;

    /**
     * Search by - SPN, Defendant Name or Officer case load.
     */
    private String defendantSearchCriteria;
    
    private boolean delete = false;

    public List divisionList ;

    private String divisionName = "<Division not defined>" ;

    private String errorMsg = "";

    private String inOutEndDateStr = "";

    private String inOutStartDateStr = "";
    
    private boolean isDivisionReassignment = false ;
    
    /**
	 * Supervisee's jail indicator
	 */
    private String jailIndicator;

    /**
     * Date when supervisee had last face to face with officer
     */
    private String lastFaceToFaceDate;
    
    /**
	 * Level of supervision for the defendant
	 */
	private String levelOfSupervision;

    private boolean listsSet = false;

    /**
     * Date scheduled for next office visit of supervisee
     */
    private String nextOfficeVisitDate;

    private String offenseDesc;

    private String officerAssignmentDate = "";

    private OfficerCaseload officerCaseload;

    private IName officerName;

    private String officerNameStr;

    private String officerPOI = "";

    private String officerPositionId = "";

    private String officerPositionName = "";

    private Date orderFiledDate;

    private List organizations; //group of organizations

    private String previousScenario;

    private String previousServiceInd;

    private String programUnitAllocationDate = "";

    private String programUnitId = "";

    /**
     * Program units within a division.
     */
    private List programUnitList;
    
    private String programUnitName = "";
    
   
    // Begin of Closure Program Unit Data
    public boolean programRefInfo = false;
    private boolean closeProgramUnitRef;
    private String closeProgramUnitName = "";
    private String criminalCaseId = "";
	private String programEndDateAsStr = "";
	private String reasonForDischargeId = "";
	private String csProgReferralToCloseId = "";
	private String reasonForDischargeDesc = "";
	private boolean isProgramUnitRef = false;	
	private String stateReportingCode = "";
	// End of Program Unit Data
	
	// Begin of New Program Unit Data
    public boolean nprogramRefInfo = false;
    private boolean newProgramUnitRef;
    private String newProgramUnitName = "";
    private String referralDateAsStr = "";
	private String programBeginDateAsStr = "";
	private String reasonForPlacementId = "";
	private String reasonForPlacementDesc = "";
	private boolean nisProgramUnitRef = false;	
	private String nstateReportingCode = "";
	private boolean taskProcessed = false;
	// End of New Program Unit Data
	
    private String  reassignDivisionLiteral =  UIConstants.REASSIGN_DIVISION;

    /**
     * Id of the court to which the supervisee is reassigned to
     */
    private String reassignedCourtId;

    /**
     * Description of the work group to which the supervisee is reassigned to
     */
    private String reassignedWorkGroupDescription;

    /**
     * Id of the work group to which the supervisee is reassigned to
     */
    private String reassignedWorkGroupId;
    
    /**
     * Name of the work group to which the supervisee is reassigned to
     */
    private String reassignedWorkGroupName;

    /**
     * Represents the type of reassignment done to supervisee. A supervisee
     * can be reassigned to an Officer, another Program Unit or a CLO.
     */
    private String reassignmentType;

    /**
     * Next action associated with task.
     */
    private String requestReassignmentTaskNextAction;

    /**
     * Notes assigned to the task.
     */
    private String requestReassignmentTaskNote;

    /**
     * The subject of a task.
     */
    private String requestReassignmentTaskSubject;

    private String scenario;

    private String searchCaseloadBy = "";

    private String searchCaseloadBySelected = "";

    private String secondaryAction = "";

    private String selectedCalendarCategory = "";
    
    /*******************************
     ***** Admin Caseload section **
     *******************************/
    
    private String[] selectedCaseAssignments;
	private String selectedValue = "";
	private boolean showResults;
    public String staffPositionDescription ;
    /**
     *  Position Id of the previously assigned officer.
     */
    private String staffPositionIdBeforeReassignment;
    public List staffPositionsFromService ;
	private IName superviseeName;
	
    private String superviseeNameStr;
    private SuperviseeTransferCasesInfo superviseeTransferCases;
    private String supervisionOrderId = "";
    private List supervisionStaff; // group of supervisors and asst supervisors
    private String supervisorFirstName;
    private String supervisorLastName;
    private String supervisorMiddleName;
    private IName supervisorName;
    private String supervisorPositionId = "";
    private String supervisorPositionType = "";
    private String taskId = "";
    private boolean update = false;
    private String userSecurityFeature;
    private boolean viewCalReportFeatureAssigned = false;
    
    private boolean viewProgramReferralCaseload = false;
    
    /**
     * Supervisee's warrant indicator
     */
    private String warrantIndicator;
    
	private String workflowInd;
    
    private String workGroupId = "";    
    private String workGroupName = "";
    
    private List workGroups; //group of workgroups
    /**
     *  
     */
    public CaseAssignmentForm()
    {
        super();
        caseToAcknowledge = new CaseAssignmentTO();
        activeCases = new ArrayList();
        caseloads = new ArrayList();
        workGroups = new ArrayList();
        closeCasesList = new ArrayList();
        closeCasesResultBeanList = new ArrayList();
        supervisionStaff = new ArrayList();
        superviseeName = new NameBean();
        superviseeNameStr = "";
        supervisorName = new NameBean();
        
        courtServicesWorkGroups = new ArrayList();
        courts = new ArrayList();
        programUnitList = new ArrayList();
        casesSelectedForReassignment = new String[1]; 
        officerCaseload = new OfficerCaseload();
        officerCaseload.setSupervisorsInDivision(new ArrayList());
        superviseeTransferCases = new SuperviseeTransferCasesInfo();        
    }
    
    public void clear()
    {
        //	action="";
        //	secondaryAction="";
        update = false;
        delete = false;
        closeCases = false;
        closeCasesSuccess = false;
        selectedValue = "";
        showResults = false;
        divisionName = "" ; 
        caseloads = null;
        supervisionOrderId = "";
        caseAssignmentId = null;
        defendantId = "";
        superviseeName = new NameBean();
        taskId = "";
        activeCases = new ArrayList();
        closeCasesList = new ArrayList();
        closeCasesResultBeanList = new ArrayList();
        caseToAcknowledge = new CaseAssignmentTO();
        offenseDesc = "";
        officerName = new NameBean();
        officerNameStr = "";
        officerPOI = "";
        officerPositionId = "";
        officerPositionName = "";
        supervisorPositionId = "";
        supervisorPositionType = null;
        scenario = null;
        previousScenario = null;
        organizations = new ArrayList();
        caseloads = new ArrayList();
        programUnitId = null;
        workflowInd = null;
        workGroupId = null;
        workGroupName = null;
        supervisorName = new NameBean();
        superviseeNameStr = "";
        searchCaseloadBy = null;
        searchCaseloadBySelected = null;
        this.orderFiledDate = null;
        errorMsg = "";
        
        //reassign supervisee
//        searchResults = new ArrayList();
        defendantSearchCriteria = "";
//        defendantToBeReassigned = "";
        reassignmentType = "";
        courtServicesWorkGroups = new ArrayList();
        courts = new ArrayList();
        reassignedCourtId = "";
        reassignedWorkGroupId = "";
        reassignedWorkGroupName = "";
        reassignedWorkGroupDescription = "";
        programUnitList = new ArrayList();
        casesSelectedForReassignment = new String[1];
        requestReassignmentTaskSubject = "";
        requestReassignmentTaskNextAction = "";
        requestReassignmentTaskNote = "";
        staffPositionIdBeforeReassignment = "";
        supervisionStaff = new ArrayList();
        officerCaseload.clear();
        jailIndicator = "";
        warrantIndicator = "";
        lastFaceToFaceDate = "";
        nextOfficeVisitDate = "";
        userSecurityFeature = "";
        superviseeTransferCases.clear();      
        selectedCalendarCategory = "";
//      to set the current system month by default
        setCurrentSystemMonth();
        viewCalReportFeatureAssigned = false;
        programUnitAllocationDate = "";
        officerAssignmentDate = "";
        assignmentReturnReason = "";
        allowTransfers = "";
        viewProgramReferralCaseload = false;
        // Program Referral Closure Data
        programRefInfo = false;
        closeProgramUnitRef = false;
        closeProgramUnitName = "";
        criminalCaseId = "";
    	programEndDateAsStr = "";
    	reasonForDischargeId = "";
    	csProgReferralToCloseId = "";
    	reasonForDischargeDesc = "";
    	isProgramUnitRef = false;
    	stateReportingCode = "";
    	// New Program Referral Unit Data
        nprogramRefInfo = false;
        newProgramUnitRef = false;
        newProgramUnitName = "";
        referralDateAsStr = "";
    	programBeginDateAsStr = "";
    	reasonForPlacementId = "";
    	reasonForPlacementDesc = "";
    	nisProgramUnitRef = false;	
    	nstateReportingCode = "";
    	// End of New Program Unit Data
    }
    
    public void clearAll(){
    	
    	this.clear();
    	casenoteDate = "";
    	casenoteText = "";
    	caseNum = "";
    	courtNumber = "";
    	defendantId = "";
    	programUnitName = "";    	
    	
    	
    }
    
    /**
     * @return Returns the action.
     */
    public String getAction()
    {
        return action;
    }
    
    /**
     * @return Returns the activeCases.
     */
    public List getActiveCases()
    {
        return activeCases;
    }
 
    /**
	 * @return Returns the activeCasesSelectedForReassignment.
	 */
	public List getActiveCasesSelectedForReassignment() {
		return activeCasesSelectedForReassignment;
	}
    
    /**
     * @return Returns the activityInd.
     */
    public String getActivityInd()
    {
        return activityInd;
    }
    
    /**
	 * @return the agencyId
	 */
	public String getAgencyId() {

		if ( this.agencyId == null ){
			
			this.agencyId = SecurityUIHelper.getUserAgencyId();
		}
		return agencyId;
	}
    
    /**
	 * @return the allowTransfers
	 */
	public String getAllowTransfers() {
		return allowTransfers;
	}
    
    /**
	 * Returns the reason for returning the assignment.
	 * @return
	 */
	public String getAssignmentReturnReason() {
		return assignmentReturnReason;
	}
    
    /**
	 * @return the calendarEndDateStr
	 */
	public String getCalendarEndDateStr() {
		return calendarEndDateStr;
	}
    
    /**
	 * @return the calendarStartDateStr
	 */
	public String getCalendarStartDateStr() {
		return calendarStartDateStr;
	}
    
    /**
     * @return
     */
    public String getCaseAssignmentId()
    {
        return this.caseAssignmentId;
    }

    /**
	 * @return Returns the caseAssignments.
	 */
	public List getCaseAssignments() {
		return caseAssignments;
	}
    
    /**
     * @return Returns the caseloads.
     */
    public List getCaseloads()
    {
        return caseloads;
    }
    
    /**
     * @return Returns the casenoteDate.
     */
    public String getCasenoteDate()
    {
        return casenoteDate;
    }
	/**
     * @return Returns the casenoteText.
     */
    public String getCasenoteText()
    {
        return casenoteText;
    }
	/**
     * @return Returns the casenoteTime.
     */
    public String getCasenoteTime()
    {
        return casenoteTime;
    }
    
	/**
     * @return Returns the caseNum.
     */
    public String getCaseNum()
    {
        return caseNum;
    }
	/**
	 * @return Returns the casesSelectedForReassignment.
	 */
	public String[] getCasesSelectedForReassignment() {
		return casesSelectedForReassignment;
	}
	
    /**
     * @return Returns the caseToAcknowledge.
     */
    public ICaseAssignment getCaseToAcknowledge()
    {
        return caseToAcknowledge;
    }

    /**
     * @return Returns the cdi.
     */
    public String getCdi()
    {
        return cdi;
    }

    public List getCloseCasesList() {
		return closeCasesList;
	}

    /**
	 * @return the closeCasesResultBeanList
	 */
	public List getCloseCasesResultBeanList() {
		return closeCasesResultBeanList;
	}

    /**
	 * @return Returns the confirmationMessageString.
	 */
	public String getConfirmationMessageString() {
		return confirmationMessageString;
	}

    /**
     * @return Returns the courtNumber.
     */
    public String getCourtNumber()
    {
        return courtNumber;
    }

    /**
	 * @return Returns the courts.
	 */
	public List getCourts() {
		return courts;
	}

    /**
	 * @return Returns the courtServicesWorkGroups.
	 */
	public List getCourtServicesWorkGroups() {
        return courtServicesWorkGroups;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isCloseProgramUnitRef() {
		return closeProgramUnitRef;
	}

	/**
	 * 
	 * @param closeProgramUnitRef
	 */
	public void setCloseProgramUnitRef(boolean closeProgramUnitRef) {
		this.closeProgramUnitRef = closeProgramUnitRef;
	}

	/**
	 * @return the closeProgramUnitName
	 */
	public String getCloseProgramUnitName() {
		return closeProgramUnitName;
	}

	/**
	 * @param closeProgramUnitName the closeProgramUnitName to set
	 */
	public void setCloseProgramUnitName(String closeProgramUnitName) {
		this.closeProgramUnitName = closeProgramUnitName;
	}

	/**
	 * 
	 * @return
	 */
    public String getCriminalCaseId() {
		return criminalCaseId;
	}

    /**
     * 
     * @param criminalCaseId
     */
    
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}

	/**
	 * @return Returns the daysLeft.
	 */
	public String getDaysLeft() {
		return daysLeft;
	}
    /**
     * @return The absolute value of daysLeft.
     */
    public String getDaysLeftAbsValue() {
    	return String.valueOf(Math.abs(Integer.parseInt(daysLeft)));
    }
    /**
     * @return Returns the spn.
     */
    public String getDefendantId()
    {
        return defendantId;
    }

    /**
	 * @return Returns the defendantSearchCriteria.
	 */
	public String getDefendantSearchCriteria() {
		return defendantSearchCriteria;
	}

    /**
	 * @return Returns the divisionList.
	 */
	public List getDivisionList() {
		return divisionList;
	}
    /**
	 * @return Returns the divisionName.
	 */
	public String getDivisionName() {
		return divisionName;
	}
    public String getErrorMsg() {
		return errorMsg;
	}

    public String getInOutEndDateStr() {
		return inOutEndDateStr;
	}
	
    public String getInOutStartDateStr() {
		return inOutStartDateStr;
	}

    /**
	 * @return Returns the isDivisionReassignment.
	 */
	public boolean getIsDivisionReassignment() {
		return isDivisionReassignment;
	}

    /**
	 * @return the jailIndicator
	 */
	public String getJailIndicator() {
		return jailIndicator;
	}

    /**
	 * @return the lastFaceToFaceDate
	 */
	public String getLastFaceToFaceDate() {
		return lastFaceToFaceDate;
	}

    /**
	 * @return Returns the levelOfSupervision.
	 */
	public String getLevelOfSupervision() {
		return levelOfSupervision;
	}

    /**
	 * @return the nextOfficeVisitDate
	 */
	public String getNextOfficeVisitDate() {
		return nextOfficeVisitDate;
	}

    /**
     * @param string
     */
    public String getOffenseDesc() {
        return offenseDesc;
    }

    /**
	 * Returns the date when a case was assigned to an officer
	 * @return
	 */
	public String getOfficerAssignmentDate() {
		return officerAssignmentDate;
	}

    /**
	 * @return Returns the officerCaseload.
	 */
	public OfficerCaseload getOfficerCaseload() {
		return officerCaseload;
	}

    /**
     * @return Returns the officerName.
     */
    public IName getOfficerName()
    {
        if (officerPositionId != null)
        {
            int len = caseloads.size();

            for (int i = 0; i < len; i++)
            {
                CaseloadSummaryTO caseload = (CaseloadSummaryTO) caseloads.get(i);

                if (this.officerPositionId.equals(caseload.getOfficerPositionId()))
                {
                    officerName = caseload.getOfficerName();
                    break;
                }
            }
        }
        else if (officerName == null)
        {
            officerName = new NameBean("", "", "");
        }

        this.caseToAcknowledge.setOfficerName(officerName);

        return officerName;
    }

    /**
	 * @return Returns the officerNameStr.
	 */
	public String getOfficerNameStr() {
		return officerNameStr;
	}

    /**
     * @return Returns the officerPOI.
     */
    public String getOfficerPOI()
    {
        return officerPOI;
    }

    /**
     * @return Returns the officerPositionId.
     */
    public String getOfficerPositionId()
    {
        return officerPositionId;
    }

    /**
     * @return Returns the officerPositionName.
     */
    public String getOfficerPositionName()
    {
        if (officerPositionId != null)
        {
            int len = caseloads.size();

            for (int i = 0; i < len; i++)
            {
                CaseloadSummaryTO caseload = (CaseloadSummaryTO) caseloads.get(i);

                if (this.officerPositionId.equals(caseload.getOfficerPositionId()))
                {
                    officerPositionName = caseload.getPositionName();
                    break;
                }
            }
        }
        else if (officerPositionName == null)
        {
            officerPositionName = "";
        }

        return officerPositionName;
    }

    /**
	 * @return the orderFiledDate
	 */
	public Date getOrderFiledDate() {
		return orderFiledDate;
	}

    /**
     * @return Returns the organizations.
     */
    public Collection getOrganizationList()
    {
        if (organizations == null || organizations.size() < 1)
        {
            organizations = new ArrayList( UIAdminStaffHelper.getActiveOrganizationalHeirarchy() );
        }
        return organizations;
    }

	/**
     * @return Returns the previousScenario.
     */
    public String getPreviousScenario()
    {
        return previousScenario;
    }
    /**
     * @return
     */
    public Object getPreviousServiceInd()
    {
        return this.previousServiceInd;
    }

    /**
     * 
     * @return
     */
    public boolean isProgramRefInfo() {
		return programRefInfo;
	}

    /**
     * 
     * @param programRefInfo
     */
	public void setProgramRefInfo(boolean programRefInfo) {
		this.programRefInfo = programRefInfo;
	}

	/**
     * @return Returns the programUnitAllocationDate.
     */
    public String getProgramUnitAllocationDate()
    {
        return programUnitAllocationDate;
    }

    /**
     * @return Returns the programUnitId.
     */
    public String getProgramUnitId()
    {
        return programUnitId;
    }

    /**
	 * @return Returns the programUnits.
	 */
	public List getProgramUnitList() {
		return programUnitList;
	}

    /**
     * @return Returns the programUnitName.
     */
    public String getProgramUnitName()
    {
        return programUnitName;
    }

    /**
	 * @return Returns the reassignDivisionLiteral.
	 */
	public String getReassignDivisionLiteral() {
		return reassignDivisionLiteral;
	}

    /**
	 * @return Returns the reassignedCourtId.
	 */
	public String getReassignedCourtId() {
		return reassignedCourtId;
	}

    public String getReferralDateAsStr() {
		return referralDateAsStr;
	}

	public boolean isProgramUnitRef() {
		return isProgramUnitRef;
	}

	public void setProgramUnitRef(boolean isProgramUnitRef) {
		this.isProgramUnitRef = isProgramUnitRef;
	}

	public void setReferralDateAsStr(String referralDateAsStr) {
		this.referralDateAsStr = referralDateAsStr;
	}

	public String getProgramBeginDateAsStr() {
		return programBeginDateAsStr;
	}

	public void setProgramBeginDateAsStr(String programBeginDateAsStr) {
		this.programBeginDateAsStr = programBeginDateAsStr;
	}

	public String getProgramEndDateAsStr() {
		return programEndDateAsStr;
	}

	public void setProgramEndDateAsStr(String programEndDateAsStr) {
		this.programEndDateAsStr = programEndDateAsStr;
	}

	public String getReasonForDischargeId() {
		return reasonForDischargeId;
	}

	public void setReasonForDischargeId(String reasonForDischargeId) {
		this.reasonForDischargeId = reasonForDischargeId;
	}

	public String getReasonForDischargeDesc() {
		return reasonForDischargeDesc;
	}

	public void setReasonForDischargeDesc(String reasonForDischargeDesc) {
		this.reasonForDischargeDesc = reasonForDischargeDesc;
	}

	public String getReasonForPlacementId() {
		return reasonForPlacementId;
	}

	public void setReasonForPlacementId(String reasonForPlacementId) {
		this.reasonForPlacementId = reasonForPlacementId;
	}

	public String getReasonForPlacementDesc() {
		return reasonForPlacementDesc;
	}

	public void setReasonForPlacementDesc(String reasonForPlacementDesc) {
		this.reasonForPlacementDesc = reasonForPlacementDesc;
	}

	/**
	 * @return Returns the reassignedWorkGroupDescription.
	 */
	public String getReassignedWorkGroupDescription() {
		return reassignedWorkGroupDescription;
	}

    /**
	 * @return Returns the reassignedWorkGroupId.
	 */
	public String getReassignedWorkGroupId() {
		return reassignedWorkGroupId;
	}

    /**
	 * @return Returns the reassignedWorkGroupName.
	 */
	public String getReassignedWorkGroupName() {
		return reassignedWorkGroupName;
	}

    /**
	 * @return Returns the reassignmentType.
	 */
	public String getReassignmentType() {
		return reassignmentType;
	}
	/**
	 * 
	 * @return
	 */
	public String getUserAgency() {
		return UIUtil.getCurrentUserAgencyID();
	}
	/**
	 * 
	 * @param userAgency
	 */
	public void setUserAgency(String userAgency) {
		this.userAgency = userAgency;
	}

    /**
	 * @return Returns the requestReassignmentTaskNextAction.
	 */
	public String getRequestReassignmentTaskNextAction() {
		return requestReassignmentTaskNextAction;
	}

    /**
	 * @return Returns the requestReassignmentTaskNote.
	 */
	public String getRequestReassignmentTaskNote() {
		return requestReassignmentTaskNote;
	}

    /**
	 * @return Returns the requestReassignmentTaskSubject.
	 */
	public String getRequestReassignmentTaskSubject() {
		return requestReassignmentTaskSubject;
	}

    /**
     * @return Returns the scenario.
     */
    public String getScenario()
    {
        return scenario;
    }

    /**
     * @return Returns the searchCaseloadBy.
     */
    public String getSearchCaseloadBy()
    {
        return searchCaseloadBy;
    }
    /**
     * @return Returns the searchCaseloadBySelected.
     */
    public String getSearchCaseloadBySelected()
    {
        return searchCaseloadBySelected;
    }

    /**
     * @return Returns the secondaryAction.
     */
    public String getSecondaryAction()
    {
        return secondaryAction;
    }

    /**
	 * @return the selectedCalendarCategory
	 */
	public String getSelectedCalendarCategory() {
		return selectedCalendarCategory;
	}

    /**
     * @return Returns the selectedCaseAssignments.
     */
    public String[] getSelectedCaseAssignments()
    {
        return selectedCaseAssignments;
    }

    /**
     * @return Returns the selectedValue.
     */
    public String getSelectedValue()
    {
        return selectedValue;
    }

    /**
	 * @return Returns the staffPositionDescription.
	 */
	public String getStaffPositionDescription() {
		return staffPositionDescription;
	}

    /**
	 * @return Returns the staffPositionIdBeforeReassignment.
	 */
	public String getStaffPositionIdBeforeReassignment() {
		return staffPositionIdBeforeReassignment;
	}

    /**
	 * @return Returns the staffPositionsFromService.
	 */
	public List getStaffPositionsFromService() {
		return staffPositionsFromService;
	}

    /**
     * @return Returns the superviseeName.
     */
    public IName getSuperviseeName()
    {
        return superviseeName;
    }

    /**
	 * @return Returns the superviseeNameStr.
	 */
	public String getSuperviseeNameStr() {
		return superviseeNameStr;
	}

    /**
	 * @return the superviseeTransferCases
	 */
	public SuperviseeTransferCasesInfo getSuperviseeTransferCases() {
		return superviseeTransferCases;
	}

    /**
     * @return Returns the supervisionOrderId.
     */
    public String getSupervisionOrderId()
    {
        return supervisionOrderId;
    }

    /**
     * @return Returns the supervisionStaff.
     */
    public List getSupervisionStaff()
    {
    	List tempList = new ArrayList();
        if (supervisionStaff == null ||  supervisionStaff.size() == 0) {
            GetCSCDSupervisionStaffEvent requestEvent = (GetCSCDSupervisionStaffEvent) EventFactory
            .getInstance(CSCDStaffPositionControllerServiceNames.GETCSCDSUPERVISIONSTAFF);

            requestEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
            requestEvent.setProgramUnitId(this.getProgramUnitId());

            tempList = MessageUtil.postRequestListFilter(requestEvent, CSCDSupervisionStaffResponseEvent.class); 
            supervisionStaff = new ArrayList( tempList );
        }
        return supervisionStaff;
    }
    
    /**
     * @return Returns the supervisorFirstName.
     */
    public String getSupervisorFirstName()
    {
        return supervisorFirstName;
    }
    /**
     * @return Returns the supervisorLastName.
     */
    public String getSupervisorLastName()
    {
        return supervisorLastName;
    }

    /**
     * @return Returns the supervisorMiddleName.
     */
    public String getSupervisorMiddleName()
    {
        return supervisorMiddleName;
    }

    /**
     * @return Returns the supervisorName.
     */
    public IName getSupervisorName() 
    {
        if (supervisorName == null || supervisorName.getFormattedName().trim().length() == 0) {
            if (supervisorPositionId != null) {
                for (int i = 0; i < supervisionStaff.size(); i++) {
                    CSCDSupervisionStaffResponseEvent supervisor = (CSCDSupervisionStaffResponseEvent) supervisionStaff.get(i);
                    if (this.supervisorPositionId.equals(supervisor.getStaffPositionId())) {
                        supervisorName = new NameBean(supervisor.getAssignedName().getFirstName(), supervisor
                                .getAssignedName().getMiddleName(), supervisor.getAssignedName().getLastName());
                        break;
                    }
                }        	
            } else if (this.supervisorFirstName != null && this.supervisorLastName != null) {
            		supervisorName = new NameBean(this.supervisorFirstName, this.supervisorMiddleName, this.supervisorLastName);
            } else {
            	supervisorName = new NameBean("", "", "");
            }            
        }        
        return supervisorName;
    }

    /**
     * @return Returns the supervisorPositionId.
     */
    public String getSupervisorPositionId()
    {
        return supervisorPositionId;
    }
    /**
     * @return Returns the supervisorPositionType.
     */
    public String getSupervisorPositionType()
    {
        if (supervisorPositionId != null)
        {
            int len = supervisionStaff.size();

            for (int i = 0; i < len; i++)
            {
                CSCDSupervisionStaffResponseEvent supervisor = (CSCDSupervisionStaffResponseEvent) supervisionStaff.get(i);

                if (this.supervisorPositionId.equals(supervisor.getStaffPositionId()))
                {
                    supervisorPositionType = supervisor.getStaffPositionType();
                    break;
                }
            }
        }
        else if (supervisorPositionType == null)
        {
            supervisorPositionType = "";
        }

        return supervisorPositionType;
    }
    /**
     * @return Returns the taskId.
     */
    public String getTaskId()
    {
        return taskId;
    }

	/**
	 * @return the userSecurityFeature
	 */
	public String getUserSecurityFeature() {
		return userSecurityFeature;
	}
	
    /**
	 * @return the warrantIndicator
	 */
	public String getWarrantIndicator() {
		return warrantIndicator;
	}

    /**
     * @return Returns the workflowInd.
     */
    public String getWorkflowInd()
    {
        return workflowInd;
    }

    /**
     * @return Returns the workGroupId.
     */
    public String getWorkGroupId()
    {
        return workGroupId;
    }

    public String getWorkGroupName()
    {
        if (workGroupId != null)
        {
            int len = workGroups.size();

            for (int i = 0; i < len; i++)
            {
                WorkGroupResponseEvent eachWorkGroup = (WorkGroupResponseEvent) workGroups.get(i);

                if (this.workGroupId.equals(eachWorkGroup.getWorkgroupId()))
                {
                    workGroupName = eachWorkGroup.getWorkgroupName();
                    break;
                }
            }
        }
        else if (workGroupName == null)
        {
            workGroupName = "";
        }

        return workGroupName;
    }

    private List getWorkGroups(String agencyId)
    {
        GetWorkGroupsEvent event = (GetWorkGroupsEvent) EventFactory
                .getInstance(WorkGroupControllerServiceNames.GETWORKGROUPS);

        event.setAgencyId(SecurityUIHelper.getUserAgencyId());

        return MessageUtil.postRequestListFilter(event, WorkGroupResponseEvent.class);
    }

    /**
     * @return Returns the workGroupsList.
     */
    public List getWorkGroupsList()
    {
        if (workGroups == null || workGroups.size() == 0)
        {
            workGroups = getWorkGroups(SecurityUIHelper.getUserAgencyId());
        }
        return workGroups;
    }

    public boolean isCloseCases() {
		return closeCases;
	}

    /**
	 * @return the closeCasesSuccess
	 */
	public boolean isCloseCasesSuccess() {
		return closeCasesSuccess;
	}

    /**
     * @return Returns the delete.
     */
    public boolean isDelete()
    {
        return delete;
    }

    /**
     * @return Returns the listsSet.
     */
    public boolean isListsSet()
    {
        return listsSet;
    }

    /**
     * @return Returns the showResults.
     */
    public boolean isShowResults()
    {
        return showResults;
    }

    /**
     * @return Returns the update.
     */
    public boolean isUpdate()
    {
        return update;
    }

    /**
	 * @return the viewCalReportFeatureAssigned
	 */
	public boolean isViewCalReportFeatureAssigned() {
		return viewCalReportFeatureAssigned;
	}

    /**
	 * @return the viewProgramReferralCaseload
	 */
	public boolean isViewProgramReferralCaseload() {
		return viewProgramReferralCaseload;
	}

    public void restore(ITask task)
    {
        ITaskState taskState = task.getTaskState();

        this.setScenario((String) taskState.get(CaseloadConstants.SCENARIO));
        this.setActivityInd((String) taskState.get(CaseloadConstants.ACTIVITY_IND));
        this.setWorkflowInd((String) taskState.get(CaseloadConstants.ACTIVITY_IND));

        this.setSupervisionOrderId((String) taskState.get(CaseloadConstants.SUPERVISION_ORDER_ID));

        // TODO TEMPORARY - REMOVE THIS AND INTEGRATE WITH TASKS
        this.setDefendantId((String) taskState.get(CaseloadConstants.DEFENDANT_ID));
        this.setCaseAssignmentId((String) taskState.get(CaseloadConstants.CASE_ASSIGNMENT_ID));

        this.setSupervisorPositionId((String) taskState.get(CaseloadConstants.SUPERVISOR_POSITION_ID));
        this.getSupervisorName().setFirstName((String) taskState.get(CaseloadConstants.SUPERVISOR_FIRST_NAME));
        this.getSupervisorName().setMiddleName((String) taskState.get(CaseloadConstants.SUPERVISOR_MIDDLE_NAME));
        this.getSupervisorName().setLastName((String) taskState.get(CaseloadConstants.SUPERVISOR_LAST_NAME));

    }

    /**
     * @param action
     *        The action to set.
     */
    public void setAction(String action)
    {
        this.action = action;
    }

    /**
     * @param activeCases
     *        The activeCases to set.
     */
    public void setActiveCases(List activeCases)
    {
        this.activeCases = activeCases;
    }

	/**
	 * @param activeCasesSelectedForReassignment The activeCasesSelectedForReassignment to set.
	 */
	public void setActiveCasesSelectedForReassignment(List activeCasesSelectedForReassignment) {
		this.activeCasesSelectedForReassignment = activeCasesSelectedForReassignment;
	}
	
    /**
     * @param activityInd
     *        The activityInd to set.
     */
    public void setActivityInd(String activityInd)
    {
        this.activityInd = activityInd;
    }

    /**
	 * @param agencyId the agencyId to set
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

    /**
	 * @param allowTransfers the allowTransfers to set
	 */
	public void setAllowTransfers(String allowTransfers) {
		this.allowTransfers = allowTransfers;
	}

    /**
	 * Sets the reason for returning the assignment.
	 * @param assignmentReturnReason
	 */
	public void setAssignmentReturnReason(String assignmentReturnReason) {
		this.assignmentReturnReason = assignmentReturnReason;
	}
    
    /**
	 * @param calendarEndDateStr the calendarEndDateStr to set
	 */
	public void setCalendarEndDateStr(String calendarEndDateStr) {
		this.calendarEndDateStr = calendarEndDateStr;
	}
    
    /**
	 * @param calendarStartDateStr the calendarStartDateStr to set
	 */
	public void setCalendarStartDateStr(String calendarStartDateStr) {
		this.calendarStartDateStr = calendarStartDateStr;
	}

    /**
     * @param caseAssignmentId
     *        The caseAssignmentId to set.
     */
    public void setCaseAssignmentId(String caseAssignmentId)
    {
        this.caseAssignmentId = caseAssignmentId;
    }

    /**
	 * @param caseAssignments The caseAssignments to set.
	 */
	public void setCaseAssignments(List caseAssignments) {
		this.caseAssignments = caseAssignments;
	}

    /**
     * @param caseloads
     *        The caseloads to set.
     */
    public void setCaseloads(List aCaseloads)
    {
        this.caseloads = aCaseloads;
    }

    /**
     * @param casenoteDate
     *        The casenoteDate to set.
     */
    public void setCasenoteDate(String casenoteDate)
    {
        this.casenoteDate = casenoteDate;
    }

    /**
     * 
     * @return
     */
    public String getStateReportingCode() {
		return stateReportingCode;
	}

    /**
     * 
     * @param stateReportingCode
     */
	public void setStateReportingCode(String stateReportingCode) {
		this.stateReportingCode = stateReportingCode;
	}

	/**
	 * @return the nprogramRefInfo
	 */
	public boolean isNprogramRefInfo() {
		return nprogramRefInfo;
	}

	/**
	 * @param nprogramRefInfo the nprogramRefInfo to set
	 */
	public void setNprogramRefInfo(boolean nprogramRefInfo) {
		this.nprogramRefInfo = nprogramRefInfo;
	}

	/**
	 * @return the newProgramUnitRef
	 */
	public boolean isNewProgramUnitRef() {
		return newProgramUnitRef;
	}

	/**
	 * @param newProgramUnitRef the newProgramUnitRef to set
	 */
	public void setNewProgramUnitRef(boolean newProgramUnitRef) {
		this.newProgramUnitRef = newProgramUnitRef;
	}

	/**
	 * @return the newProgramUnitName
	 */
	public String getNewProgramUnitName() {
		return newProgramUnitName;
	}

	/**
	 * @param newProgramUnitName the newProgramUnitName to set
	 */
	public void setNewProgramUnitName(String newProgramUnitName) {
		this.newProgramUnitName = newProgramUnitName;
	}

	/**
	 * @return the nisProgramUnitRef
	 */
	public boolean isNisProgramUnitRef() {
		return nisProgramUnitRef;
	}

	/**
	 * @param nisProgramUnitRef the nisProgramUnitRef to set
	 */
	public void setNisProgramUnitRef(boolean nisProgramUnitRef) {
		this.nisProgramUnitRef = nisProgramUnitRef;
	}

	/**
	 * @return the nstateReportingCode
	 */
	public String getNstateReportingCode() {
		return nstateReportingCode;
	}

	/**
	 * @param nstateReportingCode the nstateReportingCode to set
	 */
	public void setNstateReportingCode(String nstateReportingCode) {
		this.nstateReportingCode = nstateReportingCode;
	}

	/**
     * @param casenoteText
     *        The casenoteText to set.
     */
    public void setCasenoteText(String casenoteText)
    {
        this.casenoteText = casenoteText;
    }

    /**
     * @param casenoteTime
     *        The casenoteTime to set.
     */
    public void setCasenoteTime(String aCasenoteTime)
    {
        if (aCasenoteTime != null && (aCasenoteTime.length() == 4 || aCasenoteTime.length() == 7))
        {
            aCasenoteTime = "0" + aCasenoteTime;
        }
        this.casenoteTime = aCasenoteTime;
    }

    /**
     * @param criminalCaseId
     */
    public void setCaseNum(String aCaseNum)
    {
        this.caseNum = aCaseNum;
    }

    /**
	 * @param casesSelectedForReassignment The casesSelectedForReassignment to set.
	 */
	public void setCasesSelectedForReassignment(String[] casesSelectedForReassignment) {
		this.casesSelectedForReassignment = casesSelectedForReassignment;
	}
    
    
	/**
     * @param caseToAcknowledge
     *        The caseToAcknowledge to set.
     */
    public void setCaseToAcknowledge(ICaseAssignment caseToAcknowledge)
    {
        this.caseToAcknowledge = caseToAcknowledge;
    }
	/**
     * @param string
     */
    public void setCdi(String string) {
        this.cdi = string;
    }
	
	
	public void setCloseCases(boolean closeCases) {
		this.closeCases = closeCases;
	}
	public void setCloseCasesList(List closeCasesList) {
		this.closeCasesList = closeCasesList;
	}
	
	/**
	 * @param closeCasesResultBeanList the closeCasesResultBeanList to set
	 */
	public void setCloseCasesResultBeanList(List closeCasesResultBeanList) {
		this.closeCasesResultBeanList = closeCasesResultBeanList;
	}
	/**
	 * @param closeCasesSuccess the closeCasesSuccess to set
	 */
	public void setCloseCasesSuccess(boolean closeCasesSuccess) {
		this.closeCasesSuccess = closeCasesSuccess;
	}
	/**
	 * @param confirmationMessageString The confirmationMessageString to set.
	 */
	public void setConfirmationMessageString(String confirmationMessageString) {
		this.confirmationMessageString = confirmationMessageString;
	}
	/**
     * @param courtNumber
     *        The courtNumber to set.
     */
    public void setCourtNumber(String courtNumber)
    {
        this.courtNumber = courtNumber;
    }
	/**
	 * @param courts The courts to set.
	 */
	public void setCourts(List courts) {
		this.courts = courts;
	}
	/**
	 * @param courtServicesWorkGroups The courtServicesWorkGroups to set.
	 */
	public void setCourtServicesWorkGroups(List courtServicesWorkGroups) {
		this.courtServicesWorkGroups = courtServicesWorkGroups;
	}
	/**
     * 
     *
     */
    private void setCurrentSystemMonth()
    {
    	Calendar cal = Calendar.getInstance();
 		
 		int firstDate = cal.getActualMinimum(Calendar.DATE);
 		cal.set(Calendar.DAY_OF_MONTH, firstDate);
 		Date startDate = cal.getTime();
 		calendarStartDateStr = DateUtil.dateToString(startDate, DateUtil.DATE_FMT_1);
 		inOutStartDateStr = DateUtil.dateToString(startDate, DateUtil.DATE_FMT_1);
 		
 		int lastDate = cal.getActualMaximum(Calendar.DATE);
 		cal.set(Calendar.DATE, lastDate);
 		Date endDate = cal.getTime();
 		calendarEndDateStr = DateUtil.dateToString(endDate, DateUtil.DATE_FMT_1);
 		inOutEndDateStr = DateUtil.dateToString(endDate, DateUtil.DATE_FMT_1);
    }
	/**
	 * @param daysLeft The daysLeft to set.
	 */
	public void setDaysLeft(String daysLeft) {
		this.daysLeft = daysLeft;
	}
	/**
     * @param spn
     *        The spn to set.
     */
    public void setDefendantId(String aDefendantId)
    {
        this.defendantId = aDefendantId;
    }
	/**
	 * @param defendantSearchCriteria The defendantSearchCriteria to set.
	 */
	public void setDefendantSearchCriteria(String defendantSearchCriteria) {
		this.defendantSearchCriteria = defendantSearchCriteria;
	}
	/**
     * @param delete
     *        The delete to set.
     */
    public void setDelete(boolean delete)
    {
        this.delete = delete;
    }
	/**
	 * @param divisionList The divisionList to set.
	 */
	public void setDivisionList(List divisionList) {
		this.divisionList = divisionList;
	}
	/**
	 * @param divisionName The divisionName to set.
	 */
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public void setInOutEndDateStr(String inOutEndDateStr) {
		this.inOutEndDateStr = inOutEndDateStr;
	}
	public void setInOutStartDateStr(String inOutStartDateStr) {
		this.inOutStartDateStr = inOutStartDateStr;
	}
	/**
	 * @param isDivisionReassignment The isDivisionReassignment to set.
	 */
	public void setIsDivisionReassignment(boolean isDivisionReassignment) {
		this.isDivisionReassignment = isDivisionReassignment;
	}
    /**
	 * @param jailIndicator the jailIndicator to set
	 */
	public void setJailIndicator(String jailIndicator) {
		this.jailIndicator = jailIndicator;
	}
	/**
	 * @param lastFaceToFaceDate the lastFaceToFaceDate to set
	 */
	public void setLastFaceToFaceDate(String lastFaceToFaceDate) {
		this.lastFaceToFaceDate = lastFaceToFaceDate;
	}
	/**
	 * @param levelOfSupervision The levelOfSupervision to set.
	 */
	public void setLevelOfSupervision(String levelOfSupervision) {
		this.levelOfSupervision = levelOfSupervision;
	}
	/**
     * @param listsSet
     *        The listsSet to set.
     */
    public void setListsSet(boolean listsSet)
    {
        this.listsSet = listsSet;
    }
	/**
	 * @param nextOfficeVisitDate the nextOfficeVisitDate to set
	 */
	public void setNextOfficeVisitDate(String nextOfficeVisitDate) {
		this.nextOfficeVisitDate = nextOfficeVisitDate;
	}
	/**
     * @param offense
     *        The offense to set.
     */
    public void setOffenseDesc(String offenseDesc)
    {
        this.offenseDesc = offenseDesc;
    }
	/**
	 * Sets the date when a case was assigned to an officer
	 * @param officerAssignmentDate
	 */
	public void setOfficerAssignmentDate(String officerAssignmentDate) {
		this.officerAssignmentDate = officerAssignmentDate;
	}
	/**
	 * @param officerCaseload The officerCaseload to set.
	 */
	public void setOfficerCaseload(OfficerCaseload officerCaseload) {
		this.officerCaseload = officerCaseload;
	}
	/**
     * @param officerName
     *        The officerName to set.
     */
    public void setOfficerName(IName officerName)
    {
        this.officerName = officerName;
    }
	/**
	 * @param officerNameStr The officerNameStr to set.
	 */
	public void setOfficerNameStr(String officerNameStr) {
		this.officerNameStr = officerNameStr;
	}
	/**
     * @param officerPOI
     *        The officerPOI to set.
     */
    public void setOfficerPOI(String officerPOI)
    {
        this.officerPOI = officerPOI;
    }
	/**
     * @param officerPositionId
     *        The officerPositionId to set.
     */
    public void setOfficerPositionId(String officerPositionId)
    {
        this.officerPositionId = officerPositionId;
    }
	/**
     * @param officerPositionName
     *        The officerPositionName to set.
     */
    public void setOfficerPositionName(String officerPositionName)
    {
        this.officerPositionName = officerPositionName;
    }
	/**
	 * @param orderFiledDate the orderFiledDate to set
	 */
	public void setOrderFiledDate(Date orderFiledDate) {
		this.orderFiledDate = orderFiledDate;
	}
	/**
     * @param organizations
     *        The organizations to set.
     */
    public void setOrganizationList(List organizations)
    {
        this.organizations = organizations;
    }
	/**
     * @param previousScenario
     *        The previousScenario to set.
     */
    public void setPreviousScenario(String previousScenario)
    {
        this.previousScenario = previousScenario;
    }
	/**
     * @param serviceInd2
     */
    public void setPreviousServiceInd(String aServiceInd)
    {
        this.previousServiceInd = aServiceInd;
    }
	/**
     * @param programUnitAllocationDate
     *        The programUnitAllocationDate to set.
     */
    public void setProgramUnitAllocationDate(String programUnitAllocationDate)
    {
        this.programUnitAllocationDate = programUnitAllocationDate;
    }
	/**
     * @param programUnitId
     *        The programUnitId to set.
     */
    public void setProgramUnitId(String programUnitId)
    {
        this.programUnitId = programUnitId;
        this.setProgramUnitName(UIAdminStaffHelper.getOrganizationName(programUnitId, organizations));

    }
	/**
	 * @param programUnits The programUnits to set.
	 */
	public void setProgramUnitList(List programUnits) {
		this.programUnitList = programUnits;
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
     * 
     * @return
     */
	public String getCsProgReferralToCloseId() {
		return csProgReferralToCloseId;
	}

	/**
	 * 
	 * @param csProgReferralToCloseId
	 */
	public void setCsProgReferralToCloseId(String csProgReferralToCloseId) {
		this.csProgReferralToCloseId = csProgReferralToCloseId;
	}

	/**
	 * @param reassignDivisionLiteral The reassignDivisionLiteral to set.
	 */
	public void setReassignDivisionLiteral(String reassignDivisionLiteral) {
		this.reassignDivisionLiteral = reassignDivisionLiteral;
	}
	/**
	 * @param reassignedCourtId The reassignedCourtId to set.
	 */
	public void setReassignedCourtId(String reassignedCourtId) {
		this.reassignedCourtId = reassignedCourtId;
	}
	/**
	 * @param reassignedWorkGroupDescription The reassignedWorkGroupDescription to set.
	 */
	public void setReassignedWorkGroupDescription(String reassignedWorkGroupDescription) {
		this.reassignedWorkGroupDescription = reassignedWorkGroupDescription;
	}
	/**
	 * @param reassignedWorkGroupId The reassignedWorkGroupId to set.
	 */
	public void setReassignedWorkGroupId(String reassignedWorkGroupId) {
		this.reassignedWorkGroupId = reassignedWorkGroupId;
	}
	/**
	 * @param reassignedWorkGroupName The reassignedWorkGroupName to set.
	 */
	public void setReassignedWorkGroupName(String reassignedWorkGroupName) {
		this.reassignedWorkGroupName = reassignedWorkGroupName;
	}
	/**
	 * @param reassignmentType The reassignmentType to set.
	 */
	public void setReassignmentType(String reassignmentType) {
		this.reassignmentType = reassignmentType;
	}
	/**
	 * @param requestReassignmentTaskNextAction The requestReassignmentTaskNextAction to set.
	 */
	public void setRequestReassignmentTaskNextAction(String requestReassignmentTaskNextAction) {
		this.requestReassignmentTaskNextAction = requestReassignmentTaskNextAction;
	}
	/**
	 * @param requestReassignmentTaskNote The requestReassignmentTaskNote to set.
	 */
	public void setRequestReassignmentTaskNote(String requestReassignmentTaskNote) {
		this.requestReassignmentTaskNote = requestReassignmentTaskNote;
	}
	/**
	 * @param requestReassignmentTaskSubject The requestReassignmentTaskSubject to set.
	 */
	public void setRequestReassignmentTaskSubject(String requestReassignmentTaskSubject) {
		this.requestReassignmentTaskSubject = requestReassignmentTaskSubject;
	}
	
	/**
     * @param scenario
     *        The scenario to set.
     */
    public void setScenario(String scenario)
    {
        this.scenario = scenario;
    }
	/**
     * @param searchCaseloadBy
     *        The searchCaseloadBy to set.
     */
    public void setSearchCaseloadBy(String searchCaseloadBy)
    {
        this.searchCaseloadBy = searchCaseloadBy;
    }
	/**
     * @param searchCaseloadBySelected
     *        The searchCaseloadBySelected to set.
     */
    public void setSearchCaseloadBySelected(String searchCaseloadBySelected)
    {
        this.searchCaseloadBySelected = searchCaseloadBySelected;
    }
	/**
     * @param secondaryAction
     *        The secondaryAction to set.
     */
    public void setSecondaryAction(String secondaryAction)
    {
        this.secondaryAction = secondaryAction;
    }
	
	/**
	 * @param selectedCalendarCategory the selectedCalendarCategory to set
	 */
	public void setSelectedCalendarCategory(String selectedCalendarCategory) {
		this.selectedCalendarCategory = selectedCalendarCategory;
	}
	/**
     * @param selectedCaseAssignments
     *        The selectedCaseAssignments to set.
     */
    public void setSelectedCaseAssignments(String[] selectedCaseAssignments)
    {
        this.selectedCaseAssignments = selectedCaseAssignments;
    }
	/**
     * @param selectedValue
     *        The selectedValue to set.
     */
    public void setSelectedValue(String selectedValue)
    {
        this.selectedValue = selectedValue;
    }
	/**
     * @param showResults
     *        The showResults to set.
     */
    public void setShowResults(boolean showResults)
    {
        this.showResults = showResults;
    }
	/**
	 * @param staffPositionDescription The staffPositionDescription to set.
	 */
	public void setStaffPositionDescription(String staffPositionDescription) {
		this.staffPositionDescription = staffPositionDescription;
	}
	/**
	 * @param staffPositionIdBeforeReassignment The staffPositionIdBeforeReassignment to set.
	 */
	public void setStaffPositionIdBeforeReassignment(String staffPositionIdBeforeReassignment) {
		this.staffPositionIdBeforeReassignment = staffPositionIdBeforeReassignment;
	}
	/**
	 * @param staffPositionsFromService The staffPositionsFromService to set.
	 */
	public void setStaffPositionsFromService(List staffPositionsFromService) {
		this.staffPositionsFromService = staffPositionsFromService;
	}
	/**
     * @param superviseeName
     *        The superviseeName to set.
     */
    public void setSuperviseeName(IName superviseeName)
    {
        this.superviseeName = superviseeName;
    }
	/**
	 * @param superviseeNameStr The superviseeNameStr to set.
	 */
	public void setSuperviseeNameStr(String superviseeNameStr) {
		this.superviseeNameStr = superviseeNameStr;
	}
	/**
	 * @param superviseeTransferCases the superviseeTransferCases to set
	 */
	public void setSuperviseeTransferCases(
			SuperviseeTransferCasesInfo superviseeTransferCases) {
		this.superviseeTransferCases = superviseeTransferCases;
	}
	/**
     * @param supervisionOrderId
     *        The supervisionOrderId to set.
     */
    public void setSupervisionOrderId(String supervisionOrderId)
    {
        this.supervisionOrderId = supervisionOrderId;
    }
	/**
     * @param supervisionStaff
     *        The supervisionStaff to set.
     */
    public void setSupervisionStaff(List supervisionStaff)
    {
        this.supervisionStaff = supervisionStaff;
    }
	/**
     * @param supervisorFirstName
     *        The supervisorFirstName to set.
     */
    public void setSupervisorFirstName(String supervisorFirstName)
    {
        this.supervisorFirstName = supervisorFirstName;
    }
	/**
     * @param supervisorLastName
     *        The supervisorLastName to set.
     */
    public void setSupervisorLastName(String supervisorLastName)
    {
        this.supervisorLastName = supervisorLastName;
    }
	/**
     * @param supervisorLastName
     *        The supervisorLastName to set.
     */
    public void setSupervisorMiddleName(String supervisorMiddleName)
    {
        this.supervisorLastName = supervisorMiddleName;
    }
	/**
     * @param supervisorName
     *        The supervisorName to set.
     */
    public void setSupervisorName(IName supervisorName)
    {
        this.supervisorName = supervisorName;
    }
	/**
     * @param supervisorPositionId
     *        The supervisorPositionId to set.
     */
    public void setSupervisorPositionId(String supervisorPositionId)
    {
        this.supervisorPositionId = supervisorPositionId;
    }
	/**
     * @param supervisorPositionType
     *        The supervisorPositionType to set.
     */
    public void setSupervisorPositionType(String supervisorPositionType)
    {
        this.supervisorPositionType = supervisorPositionType;
    }
	/**
     * @param taskId
     *        The taskId to set.
     */
    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }
    /**
     */
	public boolean isTaskProcessed() {
		return taskProcessed;
	}

	public void setTaskProcessed(boolean taskProcessed) {
		this.taskProcessed = taskProcessed;
	}

	/**
     * @param update
     *        The update to set.
     */
    public void setUpdate(boolean update)
    {
        this.update = update;
    }
	/**
	 * @param userSecurityFeature the userSecurityFeature to set
	 */
	public void setUserSecurityFeature(String userSecurityFeature) {
		this.userSecurityFeature = userSecurityFeature;
	}
	/**
	 * @param viewCalReportFeatureAssigned the viewCalReportFeatureAssigned to set
	 */
	public void setViewCalReportFeatureAssigned(boolean viewCalReportFeatureAssigned) {
		this.viewCalReportFeatureAssigned = viewCalReportFeatureAssigned;
	}
	/**
	 * @param viewProgramReferralCaseload the viewProgramReferralCaseload to set
	 */
	public void setViewProgramReferralCaseload(boolean viewProgramReferralCaseload) {
		this.viewProgramReferralCaseload = viewProgramReferralCaseload;
	}
	/**
	 * @param warrantIndicator the warrantIndicator to set
	 */
	public void setWarrantIndicator(String warrantIndicator) {
		this.warrantIndicator = warrantIndicator;
	}
	/**
     * @param workflowInd
     *        The workflowInd to set.
     */
    public void setWorkflowInd(String workflowInd)
    {
        this.workflowInd = workflowInd;
    }
	/**
     * @param workGroupId
     *        The workGroupId to set.
     */
    public void setWorkGroupId(String workGroupId)
    {
        this.workGroupId = workGroupId;
    }
	/**
     * @param workGroupName
     *        The workGroupName to set.
     */
    public void setWorkGroupName(String workGroupName)
    {
        this.workGroupName = workGroupName;
    }
	/**
     * @param workGroupsList
     *        The workGroupsList to set.
     */
    public void setWorkGroupsList(List workGroups)
    {
        this.workGroups = workGroups;
    }
   
}
