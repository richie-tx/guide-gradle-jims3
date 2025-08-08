/*
 * Created on Oct 9, 2012
 *
 */
package ui.juvenilecase.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author cshimek
 *  
 */
public class JuvenileGEDProgramForm extends ActionForm
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String action = "";
	
    private boolean awarded;
    private String awardedDateStr;
    private String completionDateStr;
    private String enrollmentDateStr;
    private String enrollmentStatusDesc;
    private String enrollmentStatusId;
    private String otherProgramDesc;
    private String participationLevelDesc;
    private String participationLevelId;
    private String programDesc;
    private String programId;
    private String verificationDateStr;
    private String juvenileId;
    
    private List enrollmentStatuses;
    private List participationLevels;
    private List programs;
    private boolean gedCompleted;


    public void clear()
    {
    	this.awarded = false;
    	this.awardedDateStr = "";
    	this.completionDateStr = "";
    	this.enrollmentDateStr = "";
    	this.verificationDateStr = "";
    	this.enrollmentStatusDesc = "";	
    	this.enrollmentStatusId = "";
    	this.participationLevelDesc = "";
    	this.participationLevelId = "";
    	this.programId = "";
    	this.programDesc = "";
    	this.otherProgramDesc = "";
    	this.gedCompleted = false;
    }


	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the awarded
	 */
	public boolean isAwarded() {
		return awarded;
	}

	/**
	 * @param awarded the awarded to set
	 */
	public void setAwarded(boolean awarded) {
		this.awarded = awarded;
	}

	/**
	 * @return the awardedDateStr
	 */
	public String getAwardedDateStr() {
		return awardedDateStr;
	}

	/**
	 * @param awardedDateStr the awardedDateStr to set
	 */
	public void setAwardedDateStr(String awardedDateStr) {
		this.awardedDateStr = awardedDateStr;
	}

	/**
	 * @return the completionDateStr
	 */
	public String getCompletionDateStr() {
		return completionDateStr;
	}

	/**
	 * @param completionDateStr the completionDateStr to set
	 */
	public void setCompletionDateStr(String completionDateStr) {
		this.completionDateStr = completionDateStr;
	}

	/**
	 * @return the enrollmentDateStr
	 */
	public String getEnrollmentDateStr() {
		return enrollmentDateStr;
	}

	/**
	 * @param enrollmentDateStr the enrollemntDateStr to set
	 */
	public void setEnrollmentDateStr(String enrollmentDateStr) {
		this.enrollmentDateStr = enrollmentDateStr;
	}

	/**
	 * @return the enrollmentStatusDesc
	 */
	public String getEnrollmentStatusDesc() {
		return enrollmentStatusDesc;
	}

	/**
	 * @param enrollmentStatusDesc the enrollmentStatusDesc to set
	 */
	public void setEnrollmentStatusDesc(String enrollmentStatusDesc) {
		this.enrollmentStatusDesc = enrollmentStatusDesc;
	}

	/**
	 * @return the enrollmentStatusId
	 */
	public String getEnrollmentStatusId() {
		return enrollmentStatusId;
	}

	/**
	 * @param enrollmentStatusId the enrollmentStatusId to set
	 */
	public void setEnrollmentStatusId(String enrollmentStatusId) {
		this.enrollmentStatusId = enrollmentStatusId;
	}

	/**
	 * @return the otherProgramDesc
	 */
	public String getOtherProgramDesc() {
		return otherProgramDesc;
	}

	/**
	 * @param otherProgramDesc the otherProgramDesc to set
	 */
	public void setOtherProgramDesc(String otherProgramDesc) {
		this.otherProgramDesc = otherProgramDesc;
	}

	/**
	 * @return the participationLevelDesc
	 */
	public String getParticipationLevelDesc() {
		return participationLevelDesc;
	}

	/**
	 * @param participationLevelDesc the participationLevelDesc to set
	 */
	public void setParticipationLevelDesc(String participationLevelDesc) {
		this.participationLevelDesc = participationLevelDesc;
	}

	/**
	 * @return the participationLevelId
	 */
	public String getParticipationLevelId() {
		return participationLevelId;
	}

	/**
	 * @param participationLevelId the participationLevelId to set
	 */
	public void setParticipationLevelId(String participationLevelId) {
		this.participationLevelId = participationLevelId;
	}

	/**
	 * @return the programDesc
	 */
	public String getProgramDesc() {
		return programDesc;
	}

	/**
	 * @param programDesc the programDesc to set
	 */
	public void setProgramDesc(String programDesc) {
		this.programDesc = programDesc;
	}

	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}

	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}

	/**
	 * @return the verificationDateStr
	 */
	public String getVerificationDateStr() {
		return verificationDateStr;
	}

	/**
	 * @param verificationDateStr the verificationDateStr to set
	 */
	public void setVerificationDateStr(String verificationDateStr) {
		this.verificationDateStr = verificationDateStr;
	}

	/**
	 * @return the juvenileId
	 */
	public String getJuvenileId() {
		return juvenileId;
	}

	/**
	 * @param juvenileId the juvenileId to set
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	/**
	 * @return the enrollmentStatuses
	 */
	public List getEnrollmentStatuses() {
		return enrollmentStatuses;
	}

	/**
	 * @param enrollmentStatuses the enrollmentStatuses to set
	 */
	public void setEnrollmentStatuses(List enrollmentStatuses) {
		this.enrollmentStatuses = enrollmentStatuses;
	}

	/**
	 * @return the participationLevels
	 */
	public List getParticipationLevels() {
		return participationLevels;
	}

	/**
	 * @param participationLevels the participationLevels to set
	 */
	public void setParticipationLevels(List participationLevels) {
		this.participationLevels = participationLevels;
	}

	/**
	 * @return the programs
	 */
	public List getPrograms() {
		return programs;
	}

	/**
	 * @param programs the programs to set
	 */
	public void setPrograms(List programs) {
		this.programs = programs;
	}


	public boolean isGedCompleted()
	{
	    return gedCompleted;
	}


	public void setGedCompleted(boolean gedCompleted)
	{
	    this.gedCompleted = gedCompleted;
	}

}