/*
 * Created on Dec 4, 2007
 */
package pd.supervision.administercaseload;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import pd.codetable.supervision.SupervisionCode;

import messaging.administercaseload.GetSuperviseeByDefendantIdsEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author cc_rbhat
 */
public class Supervisee extends PersistentObject {

	public static Iterator findAll(IEvent event) {
		return new Home().findAll(event, Supervisee.class);
	}

	static public Iterator findAll(String attrName, String attrValue) {
		Iterator supervisee = null;
		IHome home = new Home();
		supervisee = home.findAll(attrName, attrValue, Supervisee.class);
		return supervisee;
	}

	/**
	 * @param defendantId
	 * @return
	 */
	public static Supervisee findByDefendantId(String defendantId) {
		IHome home = new Home();
		Iterator iterator = home.findAll("defendantId", defendantId, Supervisee.class);
		Supervisee supervisee = null;
		if (iterator.hasNext()) {
			supervisee = (Supervisee) iterator.next();
		}
		return supervisee;
	}

	public static Map getSuperviseesByIds(String defendantIds){
		Map superviseeMap = new HashMap();
        GetSuperviseeByDefendantIdsEvent gEvent = new GetSuperviseeByDefendantIdsEvent();
        gEvent.setDefendantIds(defendantIds);
        Iterator superviseeIter = Supervisee.findAll(gEvent);
        while(superviseeIter.hasNext()){
        	Supervisee sup = (Supervisee) superviseeIter.next();
        	if (sup.isCurrentlySupervised()){
        		superviseeMap.put(sup.getDefendantId(), sup);
        	}
        }
        return superviseeMap;
	}

	private String assignedProgramUnitId;

	private String assignedStaffPositionId;
	
	private String caseloadCreditStaffPositionId;
	
	private String comments;
	private boolean currentlySupervised;

	private String defendantId;
	
	private Date dnaCollectedDate;
	private boolean dnaFlagInd;
	/**
	 * Properties for history
	 * 
	 * @referencedType pd.supervision.administercaseload.SuperviseeHistory
	 */
	private java.util.Collection history = null;
	
	private Date losEffectiveDate;
	
	private SupervisionCode programTracker;
	private Date programTrackerEffectiveDate;

	private Date programTrackerEndDate;
	private String programTrackerId;

	private Date programUnitAssignmentDate;

	SupervisionLevelOfServiceCode supervisionLevel;

	private String supervisionLevelId;

	private String zipCode;

	/**
	 * Default constructor
	 *  
	 */
	public Supervisee() {
	}
	public void bind() {
		new Home().bind(this);		
	}
	/**
	 * Clears all pd.supervision.administercaseload.SupervisionLevelHistory from
	 * class relationship collection.
	 */
	public void clearHistory() {
		initHistory();
		history.clear();
	}

	/**
	 * @return Returns the assignedProgramUnitId.
	 */
	public String getAssignedProgramUnitId() {
		fetch();
		return assignedProgramUnitId;
	}

	/**
	 * @return Returns the assignedStaffPositionId.
	 */
	public String getAssignedStaffPositionId() {
		fetch();
		return assignedStaffPositionId;
	}
	/**
	 * @return Returns the caseloadCreditStaffPositionId.
	 */
	public String getCaseloadCreditStaffPositionId() {
		fetch();
		return caseloadCreditStaffPositionId;
	}

	public String getComments() {
		fetch();
		return comments;
	}

	/**
	 * @return Returns the spn.
	 */
	public String getDefendantId() {
		fetch();
		return this.defendantId;
	}
	
	/**
	 * @return the dnaCollectedDate
	 */
	public Date getDnaCollectedDate() {
		fetch();
		return dnaCollectedDate;
	}
	
	
	/**
	 * returns a collection of
	 * pd.supervision.administercaseload.SuperviseeHistory
	 */
	public java.util.Collection getHistory() {
		fetch();
		initHistory();
		// If history is null do not return null : Return array with no contents
		if (history == null) {
			history = new ArrayList();
		}
		return history;
	}

	/**
	 * @return Returns the losEffectiveDate.
	 */
	public Date getLosEffectiveDate() {
		fetch();
		return losEffectiveDate;
	}
	
	/**
	 * @return the dnaFlagInd
	 */
	public boolean isDnaFlagInd() {
		fetch();
		return dnaFlagInd;
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
	
	public Date getProgramUnitAssignmentDate() {
		fetch();
		return programUnitAssignmentDate;
	}

	public SupervisionLevelOfServiceCode getSupervisionLevel() {
		fetch();
		initSupervisionLevel();
		return supervisionLevel;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getSupervisionLevelId() {
		fetch();
		return supervisionLevelId;
	}

	public String getZipCode() {
		fetch();
		return zipCode;
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.supervision.administercaseload.SuperviseeHistory
	 */
	private void initHistory() {
		if (history == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			history = new mojo.km.persistence.ArrayList(SuperviseeHistory.class,
					"superviseeId", "" + getOID());
		}
	}
	private void initProgramTracker() {
		if (programTracker == null)
		{
			programTracker = (SupervisionCode) new mojo.km.persistence.Reference(
					programTrackerId, SupervisionCode.class).getObject();
		}
	}

	private void initSupervisionLevel() {
		if (supervisionLevel == null)
		{
			supervisionLevel = (SupervisionLevelOfServiceCode) new mojo.km.persistence.Reference(
					supervisionLevelId, SupervisionLevelOfServiceCode.class).getObject();
		}
	}

	/**
	 * insert a pd.supervision.administercaseload.SuperviseeHistory into
	 * class relationship collection.
	 */
	public void insertHistory(SuperviseeHistory anObject) {
		initHistory();
		history.add(anObject);
	}

	/**
	 * @return Returns the currentlySupervised.
	 */
	public boolean isCurrentlySupervised() {
		fetch();
		return currentlySupervised;
	}

	/**
	 * Removes a pd.supervision.administercaseload.SuperviseeHistory from
	 * class relationship collection.
	 */
	public void removeHistory(SuperviseeHistory anObject) {
		initHistory();
		history.remove(anObject);
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

	public void setComments(String comments) {
		if (this.comments == null
				|| !this.comments.equals(comments)) {
			markModified();
		}
		this.comments = comments;
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

	/**
	 * @param spn
	 *            The spn to set.
	 */
	public void setDefendantId(String defendantId) {
		if (this.defendantId == null || !this.defendantId.equals(defendantId)) {
			markModified();
		}
		this.defendantId = defendantId;
	}
		
	/**
	 * @param dnaCollectedDate the dnaCollectedDate to set
	 */
	public void setDnaCollectedDate(Date dnaCollectedDate) {
		if (this.dnaCollectedDate == null || !this.dnaCollectedDate.equals(dnaCollectedDate)) {
			markModified();
		}
		this.dnaCollectedDate = dnaCollectedDate;
	}
	
	/**
	 * @param dnaFlagInd the dnaFlagInd to set
	 */
	public void setDnaFlagInd(boolean dnaFlagInd) {
		if (this.dnaFlagInd != dnaFlagInd) {
			markModified();			
		}
		this.dnaFlagInd = dnaFlagInd;
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

	public void setProgramUnitAssignmentDate(Date programUnitAssignmentDate) {
		if (this.programUnitAssignmentDate == null || !this.programUnitAssignmentDate.equals(programUnitAssignmentDate)) {
			markModified();
		}
		this.programUnitAssignmentDate = programUnitAssignmentDate;
	}

	public void setSupervisionLevel(SupervisionLevelOfServiceCode supervisionLevel) {
		if (this.supervisionLevel == null || !this.supervisionLevel.equals(supervisionLevel))
		{
			markModified();
		}
		if (supervisionLevel.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(supervisionLevel);
		}
		setSupervisionLevelId("" + supervisionLevel.getOID());
		this.supervisionLevel = (SupervisionLevelOfServiceCode) new mojo.km.persistence.Reference(
				supervisionLevel).getObject();
	}

	public void setSupervisionLevelId(String supervisionLevelId) {
		if (this.supervisionLevelId == null || !this.supervisionLevelId.equals(supervisionLevelId)) {
			markModified();
		}
		this.supervisionLevelId = supervisionLevelId;
	}

	public void setZipCode(String zipCode) {
		if (this.zipCode == null || !this.zipCode.equals(zipCode)) {
			markModified();
		}
		this.zipCode = zipCode;		
	}
	
	
}
