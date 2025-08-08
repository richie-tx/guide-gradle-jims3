/*
 * Created on Dec 4, 2007
 */
package pd.supervision.administercaseload;

import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import pd.codetable.supervision.SupervisionCode;

/**
 * @author cc_rbhat
 */
public class SuperviseeHistory extends PersistentObject implements Comparable {

	private String type;

	private String superviseeId;

	private Date losEffectiveDate;

	private String supervisionLevelId;

	private String caseloadCreditStaffPositionId;

	private String assignedProgramUnitId;

	private String assignedStaffPositionId;
	
	private boolean currentlySupervised;
	
	private String comments;
	
	private Date entryDate;

	private String userName;
	
	private Date programUnitAssignmentDate;
	
	private String zipCode;
	
	private SupervisionCode programTracker;
	private Date programTrackerEffectiveDate;
	private Date programTrackerEndDate;
	private String programTrackerId;
	
	private Date dnaCollectedDate;
	
	private boolean dnaFlagInd;
	

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		fetch();
		return type;
	}

	/**
	 * @return Returns the assignedProgramUnitId.
	 */
	public String getAssignedProgramUnitId() {
		fetch();
		return assignedProgramUnitId;
	}

	/**
	 * @return Returns the caseloadCreditStaffPositionId.
	 */
	public String getCaseloadCreditStaffPositionId() {
		fetch();
		return caseloadCreditStaffPositionId;
	}

	/**
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId() {
		fetch();
		return superviseeId;
	}
	public String getUserName() {
		fetch();
		return userName;
	}
	/**
	 * @return Returns the losEffectiveDate.
	 */
	public Date getLosEffectiveDate() {
		fetch();
		return losEffectiveDate;
	}

	public Date getEntryDate() {
		fetch();
		return entryDate;
	}
	
	/**
	 * @return Returns the supervisionLevelId.
	 */
	public String getSupervisionLevelId() {
		fetch();
		return supervisionLevelId;
	}

	/**
	 * @return Returns the assignedStaffPositionId.
	 */
	public String getAssignedStaffPositionId() {
		fetch();
		return assignedStaffPositionId;
	}
	
	/**
	 * @return Returns the currentlySupervised.
	 */
	public boolean isCurrentlySupervised() {
		fetch();
		return currentlySupervised;
	}

	public Date getProgramUnitAssignmentDate() {
		fetch();
		return programUnitAssignmentDate;
	}
	
	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type) {
		if (this.type == null || !this.type.equals(type)) {
			markModified();
		}
		this.type = type;
	}

	/**
	 * @param assignedProgramUnitId
	 *            The assignedProgramUnitId to set.
	 */
	public void setAssignedProgramUnitId(String assignedProgramUnitId) {
		if (this.assignedProgramUnitId == null || !this.assignedProgramUnitId.equals(assignedProgramUnitId)) {
			markModified();
		}
		this.assignedProgramUnitId = assignedProgramUnitId;
	}

	/**
	 * @param caseloadCreditStaffPositionId
	 *            The caseloadCreditStaffPositionId to set.
	 */
	public void setCaseloadCreditStaffPositionId(String caseloadCreditStaffPositionId) {
		if (this.caseloadCreditStaffPositionId == null
				|| !this.caseloadCreditStaffPositionId.equals(caseloadCreditStaffPositionId)) {
			markModified();
		}
		this.caseloadCreditStaffPositionId = caseloadCreditStaffPositionId;
	}

	/**
	 * @param superviseeId
	 *            The superviseeId to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		if (this.superviseeId == null || !this.superviseeId.equals(superviseeId)) {
			markModified();
		}
		this.superviseeId = superviseeId;
	}
	public void setUserName(String userName) {
		if (this.userName == null || !this.userName.equals(userName)) {
			markModified();
		}
		this.userName = userName;
	}	

	/**
	 * @param losEffectiveDate
	 *            The losEffectiveDate to set.
	 */
	public void setLosEffectiveDate(Date losEffectiveDate) {
		if (this.losEffectiveDate == null || !this.losEffectiveDate.equals(losEffectiveDate)) {
			markModified();
		}
		this.losEffectiveDate = losEffectiveDate;
	}

	public void setEntryDate(Date entryDate) {
		if (this.entryDate == null || !this.entryDate.equals(entryDate)) {
			markModified();
		}
		this.entryDate = entryDate;
	}

	/**
	 * @param supervisionLevelId
	 *            The supervisionLevelId to set.
	 */
	public void setSupervisionLevelId(String supervisionLevelId) {
		if (this.supervisionLevelId == null || !this.supervisionLevelId.equals(supervisionLevelId)) {
			markModified();
		}
		this.supervisionLevelId = supervisionLevelId;
	}

	/**
	 * @param assignedStaffPositionId
	 *            The assignedStaffPositionId to set.
	 */
	public void setAssignedStaffPositionId(String assignedStaffPositionId) {
		if (this.assignedStaffPositionId == null || !this.assignedStaffPositionId.equals(assignedStaffPositionId)) {
			markModified();
		}
		this.assignedStaffPositionId = assignedStaffPositionId;
	}

	/**
	 * @param currentlySupervised The currentlySupervised to set.
	 */
	public void setCurrentlySupervised(boolean currentlySupervised) {
		if (this.currentlySupervised != currentlySupervised) {
			markModified();			
		}
		this.currentlySupervised = currentlySupervised;
	}

	public void setProgramUnitAssignmentDate(Date programUnitAssignmentDate) {
		if (this.programUnitAssignmentDate == null || !this.programUnitAssignmentDate.equals(programUnitAssignmentDate)) {
			markModified();
		}
		this.programUnitAssignmentDate = programUnitAssignmentDate;
	}	

	public String getComments() {
		fetch();
		return comments;
	}

	public void setComments(String comments) {
		if (this.comments == null || !this.comments.equals(comments)) {
			markModified();
		}
		
		this.comments = comments;
	}
	
	public String getZipCode() {
		fetch();
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		if (this.zipCode == null || !this.zipCode.equals(zipCode)) {
			markModified();
		}
		this.zipCode = zipCode;		
	}

	static public Iterator findAll(String attrName, String attrValue) {
		Iterator superviseeHistory = null;
		IHome home = new Home();
		superviseeHistory = home.findAll(attrName, attrValue, SuperviseeHistory.class);
		return superviseeHistory;
	}
	static public SuperviseeHistory find(String superviseeHistoryId) {
		IHome home = new Home();
		return (SuperviseeHistory) home.find(superviseeHistoryId, SuperviseeHistory.class);
	}

	public int compareTo(Object o) {
		int returnVal = 1;
		SuperviseeHistory history = null;
		if (o != null){
			history = (SuperviseeHistory) o;
			returnVal = this.getOID().compareTo(history.getOID());
		}
		return returnVal;
	}
	public SupervisionCode getProgramTracker() {
		fetch();
		initProgramTracker();
		return programTracker;

	}

	public Date getProgramTrackerEffectiveDate() {
		fetch();
		return programTrackerEffectiveDate;
	}

	public Date getProgramTrackerEndDate() {
		fetch();
		return programTrackerEndDate;
	}
	
	public String getProgramTrackerId() {
		fetch();
		return programTrackerId;
	}
	public void setProgramTracker(SupervisionCode programTracker) {
		if (this.programTracker == null || !this.programTracker.equals(programTracker))
		{
			markModified();
		}
		if (programTracker.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(programTracker);
		}
		setProgramTrackerId("" + programTracker.getOID());
		this.programTracker = (SupervisionCode) new mojo.km.persistence.Reference(
				programTracker).getObject();

	}

	public void setProgramTrackerEffectiveDate(Date programTrackerEffectiveDate) {
		if (this.programTrackerEffectiveDate == null || !this.programTrackerEffectiveDate.equals(programTrackerEffectiveDate)) {
			markModified();
		}
		this.programTrackerEffectiveDate = programTrackerEffectiveDate;
	}

	public void setProgramTrackerEndDate(Date programTrackerEndDate) {
		if (this.programTrackerEndDate == null || !this.programTrackerEndDate.equals(programTrackerEndDate)) {
			markModified();
		}
		this.programTrackerEndDate = programTrackerEndDate;
	}
	
	public void setProgramTrackerId(String programTrackerId) {
		if (this.programTrackerId == null || !this.programTrackerId.equals(programTrackerId)) {
			markModified();
		}
		this.programTrackerId = programTrackerId;
	}
	private void initProgramTracker() {
		if (programTracker == null)
		{
			programTracker = (SupervisionCode) new mojo.km.persistence.Reference(
					programTrackerId, SupervisionCode.class).getObject();
		}
	}
		
    public Date getDnaCollectedDate() {
    	fetch();
		return dnaCollectedDate;
	}

	public void setDnaCollectedDate(Date dnaCollectedDate) {
		if (this.dnaCollectedDate == null || !this.dnaCollectedDate.equals(dnaCollectedDate)) {
			markModified();
		}
		this.dnaCollectedDate = dnaCollectedDate;
	}

	public boolean isDnaFlagInd() {
		fetch();
		return dnaFlagInd;
	}

	public void setDnaFlagInd(boolean dnaFlagInd) {
		if (this.dnaFlagInd != dnaFlagInd) {
			markModified();
		}	
		this.dnaFlagInd = dnaFlagInd;
	}

	public void bind()
    {
        IHome home = new Home();
        home.bind(this);
    }//end of bind()
    
    public static List findAll(IEvent anEvent){
    	IHome home = new Home();
    	Iterator iter = home.findAll(anEvent, SuperviseeHistory.class);
    	List aList = CollectionUtil.iteratorToList(iter);
    	return aList;
    }
    
	/**
	 * sort based on the Effective Date
	 */
	public static Comparator SuperviseeHistoryEffectiveDateComparator = new Comparator() {
		public int compare(Object superviseeHistoryVersion, Object previousSuperviseeHistoryVersion){
		
		if(superviseeHistoryVersion == null || previousSuperviseeHistoryVersion == null || !(superviseeHistoryVersion instanceof SuperviseeHistory) 
				||  !(previousSuperviseeHistoryVersion instanceof SuperviseeHistory))
		{
			return 1;
		}
		SuperviseeHistory currentHistory = (SuperviseeHistory)superviseeHistoryVersion;
		SuperviseeHistory previousHistory = (SuperviseeHistory)previousSuperviseeHistoryVersion;
		Date date1 = currentHistory.losEffectiveDate;
		Date date2 = previousHistory.losEffectiveDate;
		
		// check if both the effective date fields on the bean are set. if they are sort on this, if not return equal
		if(date1 != null && date2 != null){
				return DateUtil.compare(date1, date2, DateUtil.DATE_FMT_1);
			}else{
				return 0;
			}
		}
	};
	
	/**
	 * sort based on the oid
	 */
	public static Comparator SuperviseeHistoryOidComparator = new Comparator() {
		public int compare(Object superviseeHistoryVersion, Object previousSuperviseeHistoryVersion){
		
		if(superviseeHistoryVersion == null || previousSuperviseeHistoryVersion == null || !(superviseeHistoryVersion instanceof SuperviseeHistory) 
				||  !(previousSuperviseeHistoryVersion instanceof SuperviseeHistory))
		{
			return 0;
		}
		SuperviseeHistory currentHistory = (SuperviseeHistory)superviseeHistoryVersion;
		SuperviseeHistory previousHistory = (SuperviseeHistory)previousSuperviseeHistoryVersion;

		return previousHistory.getOID().compareTo(currentHistory.getOID());
		}
	};
}
