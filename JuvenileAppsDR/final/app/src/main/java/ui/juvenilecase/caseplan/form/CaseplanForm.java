/*
 * Created on Jun 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.caseplan.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import messaging.caseplan.reply.CaseplanDocJuvDetailsResponseEvent;
import messaging.caseplan.reply.PlacementInfoResponseEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.common.form.AddressValidationForm;
import ui.juvenilecase.UIJuvenileCaseworkHelper;

/**
 * @author awidjaja
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CaseplanForm extends AddressValidationForm
{
	public static final String MEDICAL="MD";
	public static final String EDUCATIONAL = "ED";
	public static final String EMOTIONAL = "EM";
	public static final String FAMILY_PARTICIPATION = "FP";
	public static final String FAMILY_SERVICES = "FS";
	public static final String PREPARATION_FOR_ADULT_LIVING = "PA";
	public static final String RECREATIONAL = "RC";
	public static final String SAFETY_SECURITY = "SS";
	public static final String SOCIALIZATION = "SC";	
	public static final String SUPPORT_SERVICES = "SP";
	public static final String CONTACT_FREQUENCY = "CF";
	
	private String supervisionEndDateStr; 
	
	private Collection goals;
	private Caseplan currentCaseplan = new Caseplan();
	private PlacementInfo currentPlacementInfo = new PlacementInfo();
	private GoalInfo currentGoalInfo = new GoalInfo();	
	
	//will differ from juvenile to juvenile, so it shouldnt' be static
	private Collection personsResponsibleList;
	private Collection cpActivities;
	private Collection jpoReviews;
	
	//List of drop downs
	private static Collection facilityList;
	private static Collection facilityReleaseReasonList;
	private static Collection levelOfCareList;
	private static Collection permanencyListPlan;
	private static Collection dormainTypeList;
	private static Collection timeFrameList;
	
	// jpo review summary list of goals
	private Collection jpoReviewGoalList;
	
	// caseplan list for profile
	private Collection caseplanList;
	// activities list for profile
	private Collection profileActivityList;
	
	private String casefileId;
	private String juvenileNum;
	private String comments = "";
	private String supervisionType = "";
	private String placementInfoExist = "";
	private String caseplanExist = "";
	//for navigational purposes only
	private String action;
	private String status;
	private String selectedValue = "";
	private boolean goalInfoEditable = false;
	private boolean caseplanInfoEditable = false;
	private String caseplanInfoFrmDb;
	private String reqReviewComments;
	private boolean allowUpdates = false;
	private String secondaryAction = "";
	
	//Used to generate caseplan 
	private CaseplanDocJuvDetailsResponseEvent juvDetails;
	private ArrayList goalDetailsList;
	
	private PlacementInfoResponseEvent placementInfo;
	private ArrayList juvenileSchoolHistory;
	private UIJuvenileContact medicalContact = new UIJuvenileContact();
	private UIJuvenileContact dentistContact = new UIJuvenileContact();
	private ArrayList medicationList = new ArrayList();
	private ArrayList healthIssueList = new ArrayList();
	
	//non residential need to have type of medical coverage
	private ArrayList typesOfMedicalCoverage = new ArrayList();
	
	private String loggedOnUserId;
	private boolean isJuvProfile;
	private boolean inReviewForCLM = false ;
	
	//added for Copy Caseplan feature	
	private JuvenileProfileCasefileListResponseEvent selectedCasefile;
	private Collection casefiles;
	private String submitAction;
	private boolean found=false;
	private String supervisionTypeDescription="";
	
	//added for Caseplan Acknowledgment
	private Acknowledgment currentJuvenileAcknowledgment = new Acknowledgment();
	private Acknowledgment currentGuardianAcknowledgment = new Acknowledgment();
	private Collection previousAcknowledgements;
	
	
	//generate caseplan tab added 
	private String priorServices;
	private String contactInformation;
	private String supervisionLevelId;
	private static Collection supervisionLevelList;
	private String supervisionLevel;
	private boolean supLevelAppro;
	private String supLevelApproStr;
	private String recomSupervisionLevelId;
	private String recomSupervisionLevel;
	//ended
	
	//added for User story 11191 Add Title IV in caseplan
	
	private boolean juvFosterCareCandidate;
	private String juvFosterCareCandidateStr;
	private String juvFosterCareCandidateStr2; //added to show checked/unchecked value in the checkbox in the report
	private String socialHistDated="";
	private String psychologicalRepDated="";
	private String riskAssesmentDated="";
	private String titleIVEComment="";
	private String dtDeterminationMade = "";
	private String otherDated = "";
	private String explanation = "";
	
	//added for Participation in development of caseplan & distribution User story 11146
    private String othername="";
    private String childDtNotified="";
    private String familyDtNotified="";
    private String caregiverDtNotified="";
    private String otherDtNotified="";
    private String childNotifMethod="";
    private String familyNotifMethod="";
    private String caregiverNotifMethod="";
    private String otherNameNotifMethod="";
    private String childDtOfParticipation="";
    private String familyDtOfParticipation="";
    private String caregiverDtOfParticipation="";
    private String otherNameDtOfParticipation="";
    private String childMailedDt="";
    private String familyMailedDt="";
    private String caregiverMailedDt="";
    private String otherNameMailedDt="";
    private boolean jpoMaintainContact;
	private String jpoMaintainContactStr;
	private String jpoMaintainExplain;
	
	
	/**
	 * @param aRequest
	 */
	public void clearMedicalContact()
	{
		medicalContact = new UIJuvenileContact();
		dentistContact = new UIJuvenileContact();
	}
	
	public CaseplanForm()
	{
		currentGoalInfo = new GoalInfo();
	}
	
	/**
	 * @param aRequest
	 */
	public void clearComments()
	{
		comments = "";
	}
	
	public void clear()
	{
		currentCaseplan = new Caseplan();
		inReviewForCLM = false ;
				
	}
	
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest) {
		currentGoalInfo.selectedRules = new String[1];
    }
	
	/**
	 * @return Returns the currentGoalInfo.
	 */
	public GoalInfo getCurrentGoalInfo() {
		return currentGoalInfo;
	}
	/**
	 * @param currentGoalInfo The currentGoalInfo to set.
	 */
	public void setCurrentGoalInfo(GoalInfo currentGoalInfo) {
		this.currentGoalInfo = currentGoalInfo;
	}
	
	/**
	 * @return Returns the priorServices.
	 */
	public String getPriorServices() {
		return priorServices;
	}
	/**
	 * @param intervention The intervention to set.
	 */
	public void setPriorServices(String priorServices) {
		this.priorServices = priorServices;
	}

	/**
	 * @return Returns the contactInformation.
	 */
	public String getContactInformation() {
		return contactInformation;
	}
	/**
	 * @param contactInformation The contactInformation to set.
	 */
	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}	
	
	public String getSupervisionLevelId()
    {
        return supervisionLevelId;
    }
	
	public void setSupervisionLevelId(String string)
    {
        supervisionLevelId = string;
        if (supervisionLevelId == null || supervisionLevelId.equals(""))
        {
            supervisionLevel = "";
            return;
        }

        if (CaseplanForm.supervisionLevelList != null && CaseplanForm.supervisionLevelList.size() > 0)
        {
            supervisionLevel = CodeHelper.getCodeDescriptionByCode(CaseplanForm.supervisionLevelList, supervisionLevelId);
        }
    }
	
	public String getRecomSupervisionLevelId()
    {
        return recomSupervisionLevelId;
    }
	
	public void setRecomSupervisionLevelId(String string)
    {
		recomSupervisionLevelId = string;
        if (recomSupervisionLevelId == null || recomSupervisionLevelId.equals(""))
        {
        	recomSupervisionLevel = "";
            return;
        }

        if (CaseplanForm.supervisionLevelList != null && CaseplanForm.supervisionLevelList.size() > 0)
        {
        	recomSupervisionLevel = CodeHelper.getCodeDescriptionByCode(CaseplanForm.supervisionLevelList, recomSupervisionLevelId);
        }
    }
	
	
	/**
	 * @return the supLevelAppro
	 */
	public boolean isSupLevelAppro() {
		return supLevelAppro;
	}

	/**
	 * @param supLevelAppro the supLevelAppro to set
	 */
	
	public void setSupLevelAppro(boolean supLevelAppro) {
		
		this.supLevelAppro = supLevelAppro;
		if(supLevelAppro){
			supLevelApproStr = "Yes";
		}
		else{
			supLevelApproStr = "No";
		}
		
	}

	/**
	 * @return the supLevelApproStr
	 */
	public String getSupLevelApproStr() {
		return supLevelApproStr;
	}

	/**
	 * @param supLevelApproStr the supLevelApproStr to set
	 */
	
	public void setSupLevelApproStr(String supLevelApproStr) {
		this.supLevelApproStr = supLevelApproStr;		
		if(supLevelApproStr!=null && !supLevelApproStr.equals("")){
			if(supLevelApproStr.equalsIgnoreCase("Yes")){
				supLevelAppro = true;
			}
			else{
				supLevelAppro = false;
			}
			
		}
	}
	
	
	/**
	 * @return the jpoMaintainContact
	 */
	public boolean isJpoMaintainContact() {
		return jpoMaintainContact;
	}

	/**
	 * @param jpoMaintainContact the jpoMaintainContact to set
	 */
	
	public void setJpoMaintainContact(boolean jpoMaintainContact) {
		
		this.jpoMaintainContact = jpoMaintainContact;
		if(jpoMaintainContact){
			jpoMaintainContactStr = "Yes";
		}
		else{
			jpoMaintainContactStr = "No";
		}
		
	}

	/**
	 * @return the jpoMaintainContactStr
	 */
	public String getJpoMaintainContactStr() {
		return jpoMaintainContactStr;
	}

	/**
	 * @param jpoMaintainContactStr the jpoMaintainContactStr to set
	 */
	
	public void setJpoMaintainContactStr(String jpoMaintainContactStr) {
		this.jpoMaintainContactStr = jpoMaintainContactStr;		
		if(jpoMaintainContactStr!=null && !jpoMaintainContactStr.equals("")){
			if(jpoMaintainContactStr.equalsIgnoreCase("Yes")){
				jpoMaintainContact = true;
			}
			else{
				jpoMaintainContact = false;
			}
			
		}
	}
	
	
	/**
	 * @return the jpoMaintainExplain
	 */
	public String getJpoMaintainExplain() {
		return jpoMaintainExplain;
	}

	/**
	 * @param jpoMaintainExplain the jpoMaintainExplain to set
	 */
	public void setJpoMaintainExplain(String jpoMaintainExplain) {
		this.jpoMaintainExplain = jpoMaintainExplain;
	}

	/**
	 * @return the juvFosterCareCandidate
	 */
	public boolean isJuvFosterCareCandidate() {
		return juvFosterCareCandidate;
	}

	/**
	 * @param juvFosterCareCandidate the juvFosterCareCandidate to set
	 */

	public void setJuvFosterCareCandidate(boolean juvFosterCareCandidate) {
		
		this.juvFosterCareCandidate = juvFosterCareCandidate;
		if(juvFosterCareCandidate){
			juvFosterCareCandidateStr = "Yes";
		}
		else{
			juvFosterCareCandidateStr = "No";
		}
		//juvFosterCareCandidateStr=Boolean.toString(juvFosterCareCandidate);
	}
	
	
	
	
	/**
	 * @return the juvFosterCareCandidateStr
	 */
	public String getJuvFosterCareCandidateStr() {
		return juvFosterCareCandidateStr;
	}
	
	/**
	 * @param detentionVisitationAsStr the detentionVisitationAsStr to set
	 */
	public void setJuvFosterCareCandidateStr(String juvFosterCareCandidateStr) {
		this.juvFosterCareCandidateStr = juvFosterCareCandidateStr;		
		if(juvFosterCareCandidateStr!=null && !juvFosterCareCandidateStr.equals("")){
			if(juvFosterCareCandidateStr.equalsIgnoreCase("Yes")){
				juvFosterCareCandidate = true;
			}
			else{
				juvFosterCareCandidate = false;
			}
			//juvFosterCareCandidate=(Boolean.valueOf(juvFosterCareCandidateStr).booleanValue());
		}
	}


	/**
	 * @return the juvFosterCareCandidateStr2
	 */
	public String getJuvFosterCareCandidateStr2() {
		return juvFosterCareCandidateStr2;
	}

	/**
	 * @param juvFosterCareCandidateStr2 the juvFosterCareCandidateStr2 to set
	 */
	public void setJuvFosterCareCandidateStr2(String juvFosterCareCandidateStr2) {
		this.juvFosterCareCandidateStr2 = juvFosterCareCandidateStr2;
	}

	/**
	 * @return the socialHistDated
	 */
	public String getSocialHistDated() {
		return socialHistDated;
	}

	/**
	 * @param socialHistDated the socialHistDated to set
	 */
	public void setSocialHistDated(String socialHistDated) {
		this.socialHistDated = socialHistDated;
	}

	/**
	 * @return the psychologicalRepDated
	 */
	public String getPsychologicalRepDated() {
		return psychologicalRepDated;
	}

	/**
	 * @param psychologicalRepDated the psychologicalRepDated to set
	 */
	public void setPsychologicalRepDated(String psychologicalRepDated) {
		this.psychologicalRepDated = psychologicalRepDated;
	}

	/**
	 * @return the riskAssesmentDated
	 */
	public String getRiskAssesmentDated() {
		return riskAssesmentDated;
	}

	/**
	 * @param riskAssesmentDated the riskAssesmentDated to set
	 */
	public void setRiskAssesmentDated(String riskAssesmentDated) {
		this.riskAssesmentDated = riskAssesmentDated;
	}

	/**
	 * @return the titleIVEComment
	 */
	public String getTitleIVEComment() {
		return titleIVEComment;
	}

	/**
	 * @param titleIVEComment the titleIVEComment to set
	 */
	public void setTitleIVEComment(String titleIVEComment) {
		this.titleIVEComment = titleIVEComment;
	}

	/**
	 * @return the dtDeterminationMade
	 */
	public String getDtDeterminationMade() {
		return dtDeterminationMade;
	}

	/**
	 * @param dtDeterminationMade the dtDeterminationMade to set
	 */
	public void setDtDeterminationMade(String dtDeterminationMade) {
		this.dtDeterminationMade = dtDeterminationMade;
	}

	/**
	 * @return the otherDated
	 */
	public String getOtherDated() {
		return otherDated;
	}

	/**
	 * @param otherDated the otherDated to set
	 */
	public void setOtherDated(String otherDated) {
		this.otherDated = otherDated;
	}
	

	/**
	 * @return the explanation
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * @param explanation the explanation to set
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	/**
	 * @return the othername
	 */
	public String getOthername() {
		return othername;
	}

	/**
	 * @param othername the othername to set
	 */
	public void setOthername(String othername) {
		this.othername = othername;
	}

	/**
	 * @return the childDtNotified
	 */
	public String getChildDtNotified() {
		return childDtNotified;
	}

	/**
	 * @param childDtNotified the childDtNotified to set
	 */
	public void setChildDtNotified(String childDtNotified) {
		this.childDtNotified = childDtNotified;
	}

	/**
	 * @return the familyDtNotified
	 */
	public String getFamilyDtNotified() {
		return familyDtNotified;
	}

	/**
	 * @param familyDtNotified the familyDtNotified to set
	 */
	public void setFamilyDtNotified(String familyDtNotified) {
		this.familyDtNotified = familyDtNotified;
	}

	/**
	 * @return the caregiverDtNotified
	 */
	public String getCaregiverDtNotified() {
		return caregiverDtNotified;
	}

	/**
	 * @param caregiverDtNotified the caregiverDtNotified to set
	 */
	public void setCaregiverDtNotified(String caregiverDtNotified) {
		this.caregiverDtNotified = caregiverDtNotified;
	}

	/**
	 * @return the otherDtNotified
	 */
	public String getOtherDtNotified() {
		return otherDtNotified;
	}

	/**
	 * @param otherDtNotified the otherDtNotified to set
	 */
	public void setOtherDtNotified(String otherDtNotified) {
		this.otherDtNotified = otherDtNotified;
	}

	/**
	 * @return the childNotifMethod
	 */
	public String getChildNotifMethod() {
		return childNotifMethod;
	}

	/**
	 * @param childNotifMethod the childNotifMethod to set
	 */
	public void setChildNotifMethod(String childNotifMethod) {
		this.childNotifMethod = childNotifMethod;
	}

	/**
	 * @return the familyNotifMethod
	 */
	public String getFamilyNotifMethod() {
		return familyNotifMethod;
	}

	/**
	 * @param familyNotifMethod the familyNotifMethod to set
	 */
	public void setFamilyNotifMethod(String familyNotifMethod) {
		this.familyNotifMethod = familyNotifMethod;
	}

	/**
	 * @return the caregiverNotifMethod
	 */
	public String getCaregiverNotifMethod() {
		return caregiverNotifMethod;
	}

	/**
	 * @param caregiverNotifMethod the caregiverNotifMethod to set
	 */
	public void setCaregiverNotifMethod(String caregiverNotifMethod) {
		this.caregiverNotifMethod = caregiverNotifMethod;
	}

	/**
	 * @return the otherNameNotifMethod
	 */
	public String getOtherNameNotifMethod() {
		return otherNameNotifMethod;
	}

	/**
	 * @param otherNameNotifMethod the otherNameNotifMethod to set
	 */
	public void setOtherNameNotifMethod(String otherNameNotifMethod) {
		this.otherNameNotifMethod = otherNameNotifMethod;
	}

	/**
	 * @return the childDtOfParticipation
	 */
	public String getChildDtOfParticipation() {
		return childDtOfParticipation;
	}

	/**
	 * @param childDtOfParticipation the childDtOfParticipation to set
	 */
	public void setChildDtOfParticipation(String childDtOfParticipation) {
		this.childDtOfParticipation = childDtOfParticipation;
	}

	/**
	 * @return the familyDtOfParticipation
	 */
	public String getFamilyDtOfParticipation() {
		return familyDtOfParticipation;
	}

	/**
	 * @param familyDtOfParticipation the familyDtOfParticipation to set
	 */
	public void setFamilyDtOfParticipation(String familyDtOfParticipation) {
		this.familyDtOfParticipation = familyDtOfParticipation;
	}

	/**
	 * @return the caregiverDtOfParticipation
	 */
	public String getCaregiverDtOfParticipation() {
		return caregiverDtOfParticipation;
	}

	/**
	 * @param caregiverDtOfParticipation the caregiverDtOfParticipation to set
	 */
	public void setCaregiverDtOfParticipation(String caregiverDtOfParticipation) {
		this.caregiverDtOfParticipation = caregiverDtOfParticipation;
	}

	/**
	 * @return the otherNameDtOfParticipation
	 */
	public String getOtherNameDtOfParticipation() {
		return otherNameDtOfParticipation;
	}

	/**
	 * @param otherNameDtOfParticipation the otherNameDtOfParticipation to set
	 */
	public void setOtherNameDtOfParticipation(String otherNameDtOfParticipation) {
		this.otherNameDtOfParticipation = otherNameDtOfParticipation;
	}

	/**
	 * @return the childMailedDt
	 */
	public String getChildMailedDt() {
		return childMailedDt;
	}

	/**
	 * @param childMailedDt the childMailedDt to set
	 */
	public void setChildMailedDt(String childMailedDt) {
		this.childMailedDt = childMailedDt;
	}

	/**
	 * @return the familyMailedDt
	 */
	public String getFamilyMailedDt() {
		return familyMailedDt;
	}

	/**
	 * @param familyMailedDt the familyMailedDt to set
	 */
	public void setFamilyMailedDt(String familyMailedDt) {
		this.familyMailedDt = familyMailedDt;
	}

	/**
	 * @return the caregiverMailedDt
	 */
	public String getCaregiverMailedDt() {
		return caregiverMailedDt;
	}

	/**
	 * @param caregiverMailedDt the caregiverMailedDt to set
	 */
	public void setCaregiverMailedDt(String caregiverMailedDt) {
		this.caregiverMailedDt = caregiverMailedDt;
	}

	/**
	 * @return the otherNameMailedDt
	 */
	public String getOtherNameMailedDt() {
		return otherNameMailedDt;
	}

	/**
	 * @param otherNameMailedDt the otherNameMailedDt to set
	 */
	public void setOtherNameMailedDt(String otherNameMailedDt) {
		this.otherNameMailedDt = otherNameMailedDt;
	}

	public Collection getSupervisionLevelList()
    {
        return supervisionLevelList;
    }
	
	public void setSupervisionLevelList(Collection supervisionLevelList)
    {
		CaseplanForm.supervisionLevelList = supervisionLevelList;
    }
	
	
	
	public String getSupervisionLevel()
    {
        return supervisionLevel;
    }
	
	 public void setSupervisionLevel(String string)
    {
        supervisionLevel = string;
    }
	 

		public String getRecomSupervisionLevel()
	    {
	        return recomSupervisionLevel;
	    }
		
		 public void setRecomSupervisionLevel(String string)
	    {
			this.recomSupervisionLevel = string;
	    }
	
	
	
	/**
	 * @return Returns the supervisionEndDateStr. 
	 */
	public String getSupervisionEndDateStr() {
		return supervisionEndDateStr;
	}
	/**
	 * @param supervisionEndDateStr The supervisionEndDateStr to set. 
	 */
	public void setSupervisionEndDateStr(String supervisionEndDateStr) {
		this.supervisionEndDateStr = supervisionEndDateStr;
	}
	

	/**
	 * @return Returns the goals.
	 */
	public Collection getGoals() {
		return goals;
	}
	/**
	 * @param goals The goals to set.
	 */
	public void setGoals(Collection goals) {
		this.goals = goals;
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
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	
	/**
	 * @return Returns the currentCaseplan.
	 */
	public Caseplan getCurrentCaseplan() {
		return currentCaseplan;
	}
	/**
	 * @param currentCaseplan The currentCaseplan to set.
	 */
	public void setCurrentCaseplan(Caseplan currentCaseplan) {
		this.currentCaseplan = currentCaseplan;
	}
	/**
	 * @return Returns the currentPlacementInfo.
	 */
	public PlacementInfo getCurrentPlacementInfo() {
		return currentPlacementInfo;
	}
	/**
	 * @param currentPlacementInfo The currentPlacementInfo to set.
	 */
	public void setCurrentPlacementInfo(PlacementInfo currentPlacementInfo) {
		this.currentPlacementInfo = currentPlacementInfo;
	}
	
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the action.
	 */
	public String getSubmitAction() {
		return submitAction;
	}
	/**
	 * @param action The action to set.
	 */
	public void setSubmitAction(String submitAction) {
		this.submitAction = submitAction;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return Returns the dormainTypeList.
	 */
	public Collection getDormainTypeList() {
		return dormainTypeList;
	}
	/**
	 * @param dormainTypeList The dormainTypeList to set.
	 */
	public void setDormainTypeList(Collection dormainTypeList) {
		CaseplanForm.dormainTypeList = dormainTypeList;
	}
	/**
	 * @return Returns the timeFrameList.
	 */
	public Collection getTimeFrameList() {
		return timeFrameList;
	}
	/**
	 * @param timeFrameList The timeFrameList to set.
	 */
	public void setTimeFrameList(Collection timeFrameList) {
		CaseplanForm.timeFrameList = timeFrameList;
	}
	/**
	 * @return Returns the personsResponsibleList.
	 */
	public Collection getPersonsResponsibleList() {
		return personsResponsibleList;
	}
	/**
	 * @param personsResponsibleList The personsResponsibleList to set.
	 */
	public void setPersonsResponsibleList(Collection personsResponsibleList) {
		this.personsResponsibleList = personsResponsibleList;
	}

	/**
	 * @return Returns the facilityList.
	 */
	public Collection getFacilityList() {
		facilityList = CodeHelper.getJuvenileFacilities(true);
		return facilityList;
	}
	/**
	 * @param facilityList The facilityList to set.
	 */
	public void setFacilityList(Collection facilityList) {
		CaseplanForm.facilityList = facilityList;
	}
	/**
	 * @return Returns the facilityReleaseReasonList.
	 */
	public Collection getFacilityReleaseReasonList() {
		return facilityReleaseReasonList;
	}
	/**
	 * @param facilityReleaseReasonList The facilityReleaseReasonList to set.
	 */
	public void setFacilityReleaseReasonList(Collection facilityReleaseReasonList) {
		CaseplanForm.facilityReleaseReasonList = facilityReleaseReasonList;
	}
	/**
	 * @return Returns the levelOfCareList.
	 */
	public Collection getLevelOfCareList() {
		return levelOfCareList;
	}
	/**
	 * @param levelOfCareList The levelOfCareList to set.
	 */
	public void setLevelOfCareList(Collection levelOfCareList) {
		CaseplanForm.levelOfCareList = levelOfCareList;
	}
	/**
	 * @return Returns the permanencyListPlan.
	 */
	public Collection getPermanencyListPlan() {
		return CodeHelper.getCodes("PERMANENCY_PLAN",true);
	}
	/**
	 * @param permanencyListPlan The permanencyListPlan to set.
	 */
	public void setPermanencyListPlan(Collection permanencyListPlan) {
		CaseplanForm.permanencyListPlan = permanencyListPlan;
	}
	/**
	 * @return Returns the isGoalInfoEditable.
	 */
	public boolean isGoalInfoEditable() {
		return goalInfoEditable;
	}
	/**
	 * @param isGoalInfoEditable The isGoalInfoEditable to set.
	 */
	public void setGoalInfoEditable(boolean goalInfoEditable) {
		this.goalInfoEditable = goalInfoEditable;
	}
	
	/**
	 * @return Returns the isCaseplanInfoEditable.
	 */
	public boolean isCaseplanInfoEditable() {
		return caseplanInfoEditable;
	}
	/**
	 * @return Returns the getCaseplanInfoFrmDb.
	 */
	public String getCaseplanInfoFrmDb() {
		return caseplanInfoFrmDb;
	}
	/**
	 * @param isCaseplanInfoFrmDb The isCaseplanInfoFrmDb to set.
	 */
	public void setCaseplanInfoFrmDb(String caseplanInfoFrmDb) {
		this.caseplanInfoFrmDb = caseplanInfoFrmDb;
	}
	
	/**
	 * @param isCaseplanInfoEditable The isCaseplanInfoEditable to set.
	 */
	public void setCaseplanInfoEditable(boolean caseplanInfoEditable) {
		this.caseplanInfoEditable = caseplanInfoEditable;
	}
	/**
	
	/**
	 * @return Returns the isGoalInfoEditable.
	 */
	public boolean isFound() {
		return found;
	}
	/**
	 * @param isGoalInfoEditable The isGoalInfoEditable to set.
	 */
	public void setFound(boolean found) {
		this.found = found;
	}
	
	/**
	 * @return Returns the previousAcknowledgements.
	 */
	public Collection getPreviousAcknowledgements() {
		return previousAcknowledgements;
	}
	/**
	 * @param previousAcks 
	 */
	public void setPreviousAcknowledgements(Collection previousAcks) {
		this.previousAcknowledgements = previousAcks;
	}
	
	/**
	 * @return Returns the currentJuvenileAcknowledgment.
	 */
	public Acknowledgment getCurrentJuvenileAcknowledgment() {
		return currentJuvenileAcknowledgment;
	}
	/**
	 * @param currentJuvAck 
	 */
	public void setCurrentJuvenileAcknowledgment(Acknowledgment currentJuvAck) {
		this.currentJuvenileAcknowledgment = currentJuvAck;
	}
	
	/**
	 * @return Returns the currentGuardianAcknowledgment.
	 */
	public Acknowledgment getCurrentGuardianAcknowledgment() {
		return currentGuardianAcknowledgment;
	}
	/**
	 * @param currentGuardianAck 
	 */
	public void setCurrentGuardianAcknowledgment(Acknowledgment currentGuardianAck) {
		this.currentGuardianAcknowledgment = currentGuardianAck;
	}
	
	public class Caseplan
	{
		private String status;
		private String createDate;
		private String reviewDate;
		private String Comments;
		private String caseplanId = "";
		private String statusCd;
		
		private Collection goalList = new ArrayList();
		
		public void clear()
		{
			status = "";
			statusCd= "";
			createDate = "";
			reviewDate = "";
			goalList = new ArrayList();
		}
		/**
		 * @return Returns the comments.
		 */
		public String getComments() {
			return Comments;
		}
		/**
		 * @param comments The comments to set.
		 */
		public void setComments(String comments) {
			Comments = comments;
		}
		/**
		 * @return Returns the createDate.
		 */
		public String getCreateDate() {
			return createDate;
		}
		/**
		 * @param createDate The createDate to set.
		 */
		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}
		/**
		 * @return Returns the reviewDate.
		 */
		public String getReviewDate() {
			return reviewDate;
		}
		/**
		 * @param reviewDate The reviewDate to set.
		 */
		public void setReviewDate(String reviewDate) {
			this.reviewDate = reviewDate;
		}
		/**
		 * @return Returns the status.
		 */
		public String getStatus() {
			return status;
		}
		/**
		 * @param status The status to set.
		 */
		public void setStatus(String status) {
			this.status = status;
		}

		/**
		 * @return Returns the statusCd.
		 */
		public String getStatusCd() {
			return statusCd;
		}
		/**
		 * @param statusCd The statusCd to set.
		 */
		public void setStatusCd(String statusCd) {
			this.statusCd = statusCd;
			this.status = CodeHelper.getCodeDescriptionByCode(
						CodeHelper.getCodes("CASEPLAN_STATUS"), statusCd);
		}


		/**
		 * @return Returns the goalList.
		 */
		public Collection getGoalList() {
			return goalList;
		}
		/**
		 * @param goalList The goalList to set.
		 */
		public void setGoalList(Collection goalList) {
			this.goalList = goalList;
		}
		/**
		 * @return Returns the caseplanId.
		 */
		public String getCaseplanId() {
			return caseplanId;
		}
		/**
		 * @param caseplanId The caseplanId to set.
		 */
		public void setCaseplanId(String caseplanId) {
			this.caseplanId = caseplanId;
		}
	}
	
	public static class PlacementInfo
	{
		private String entryDate=DateUtil.getCurrentDateString("MM/dd/yyyy");
		private String closestFacilityAvailable = "";
		private String leastRestrictiveEnv = "";
		private String proximityConsidered = "";
		private String reasonChildRequiresPlacement = "";
		private String specificServicesProvided = "";
		private String reasonChildIsPlacedOutsideOfTexas = "";
		private String facilityId = "";
		private String facilityStr = "";
		private String facilityReleaseReasonId = "";
		private String facilityReleaseReasonStr = "";
		private String expectedReleaseDate = "";
		private String levelOfCareId = "";
		private String levelOfCareStr = "";
		private String permanencyPlanId = "";
		private String permanencyPlanStr = "";
		private String specialNotes = "";
		private String placementID;
		
		public PlacementInfo(){}
		public void clear(){}

		/**
		 * @return Returns the placementID.
		 */
		public String getPlacementID() {
			return placementID;
		}
		/**
		 * @param placementID The placementID to set.
		 */
		public void setPlacementID(String placementID) {
			this.placementID = placementID;
		}
		/**
		 * @return Returns the entryDate.
		 */
		public String getEntryDate() {
			return entryDate;
		}
		/**
		 * @param entryDate The entryDate to set.
		 */
		public void setEntryDate(String entryDate) {
			this.entryDate = entryDate;
		}
		/**
		 * @return Returns the expectedReleaseDate.
		 */
		public String getExpectedReleaseDate() {
			return expectedReleaseDate;
		}
		/**
		 * @param expectedReleaseDate The expectedReleaseDate to set.
		 */
		public void setExpectedReleaseDate(String expectedReleaseDate) {
			this.expectedReleaseDate = expectedReleaseDate;
		}
		/**
		 * @return Returns the facilityId.
		 */
		public String getFacilityId() {
			return facilityId;
		}
		/**
		 * @param facilityId The facilityId to set.
		 */
		public void setFacilityId(String facilityId) 
		{
			this.facilityId = facilityId;
			this.facilityStr = "";
			if( notNullNotEmptyString( facilityId ) )
			{
				this.facilityStr = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, facilityId);
			}
		}
		/**
		 * @return Returns the facilityReleaseReasonId.
		 */
		public String getFacilityReleaseReasonId() {
			return facilityReleaseReasonId;
		}
		/**
		 * @param facilityReleaseReasonId The facilityReleaseReasonId to set.
		 */
		public void setFacilityReleaseReasonId(String facilityReleaseReasonId) 
		{
			this.facilityReleaseReasonId = facilityReleaseReasonId;
			if( notNullNotEmptyString( facilityReleaseReasonId ) )
			{
				this.facilityReleaseReasonStr= CodeHelper.getCodeDescriptionByCode(CaseplanForm.facilityReleaseReasonList, facilityReleaseReasonId);				
			}
		}
		/**
		 * @return Returns the isClosestFacilityAvailable.
		 */
		public String getClosestFacilityAvailable() {
			return closestFacilityAvailable;
		}
		/**
		 * @param isClosestFacilityAvailable The isClosestFacilityAvailable to set.
		 */
		public void setClosestFacilityAvailable(String isClosestFacilityAvailable) {
			this.closestFacilityAvailable = isClosestFacilityAvailable;
		}
		/**
		 * @return Returns the isLeastRestrictiveEnv.
		 */
		public String getLeastRestrictiveEnv() {
			return leastRestrictiveEnv;
		}
		/**
		 * @param isLeastRestrictiveEnv The isLeastRestrictiveEnv to set.
		 */
		public void setLeastRestrictiveEnv(String isLeastRestrictiveEnv) {
			this.leastRestrictiveEnv = isLeastRestrictiveEnv;
		}
		/**
		 * @return Returns the levelOfCareId.
		 */
		public String getLevelOfCareId() {
			return levelOfCareId;
		}
		/**
		 * @param levelOfCareId The levelOfCareId to set.
		 */
		public void setLevelOfCareId(String levelOfCareId) 
		{
			this.levelOfCareId = levelOfCareId;
			if( notNullNotEmptyString( levelOfCareId ) )
			{
				this.levelOfCareStr = CodeHelper.getCodeDescriptionByCode(
						CaseplanForm.levelOfCareList, levelOfCareId);				
			}
		}
		/**
		 * @return Returns the permanencyPlanId.
		 */
		public String getPermanencyPlanId() {
			return permanencyPlanId;
		}
		/**
		 * @param permanencyPlanId The permanencyPlanId to set.
		 */
		public void setPermanencyPlanId(String permanencyPlanId) 
		{
			this.permanencyPlanId = permanencyPlanId;
			if( notNullNotEmptyString( permanencyPlanId ) )
			{
				this.permanencyPlanStr= CodeHelper.getCodeDescriptionByCode(
						CaseplanForm.permanencyListPlan, permanencyPlanId);				
			}
		}
		/**
		 * @return Returns the proximityConsidered.
		 */
		public String getProximityConsidered() {
			return proximityConsidered;
		}
		/**
		 * @param proximityConsidered The proximityConsidered to set.
		 */
		public void setProximityConsidered(String proximityConsidered) {
			this.proximityConsidered = proximityConsidered;
		}
		/**
		 * @return Returns the reasonChildIsPlacedOutsideOfTexas.
		 */
		public String getReasonChildIsPlacedOutsideOfTexas() {
			return reasonChildIsPlacedOutsideOfTexas;
		}
		/**
		 * @param reasonChildIsPlacedOutsideOfTexas The reasonChildIsPlacedOutsideOfTexas to set.
		 */
		public void setReasonChildIsPlacedOutsideOfTexas(String reasonChildIsPlacedOutsideOfTexas) {
			this.reasonChildIsPlacedOutsideOfTexas = reasonChildIsPlacedOutsideOfTexas;
		}
		/**
		 * @return Returns the reasonChildRequiresPlacement.
		 */
		public String getReasonChildRequiresPlacement() {
			return reasonChildRequiresPlacement;
		}
		/**
		 * @param reasonChildRequiresPlacement The reasonChildRequiresPlacement to set.
		 */
		public void setReasonChildRequiresPlacement(String reasonChildRequiresPlacement) {
			this.reasonChildRequiresPlacement = reasonChildRequiresPlacement;
		}
		/**
		 * @return Returns the specialNotes.
		 */
		public String getSpecialNotes() {
			return specialNotes;
		}
		/**
		 * @param specialNotes The specialNotes to set.
		 */
		public void setSpecialNotes(String specialNotes) {
			this.specialNotes = specialNotes;
		}
		/**
		 * @return Returns the specificServicesProvided.
		 */
		public String getSpecificServicesProvided() {
			return specificServicesProvided;
		}
		/**
		 * @param specificServicesProvided The specificServicesProvided to set.
		 */
		public void setSpecificServicesProvided(String specificServicesProvided) {
			this.specificServicesProvided = specificServicesProvided;
		}
		/**
		 * @return Returns the facilityStr.
		 */
		public String getFacilityStr() {
			return facilityStr;
		}
		/**
		 * @return Returns the facilityReleaseReasonStr.
		 */
		public String getFacilityReleaseReasonStr() {
			return facilityReleaseReasonStr;
		}
		/**
		 * @return Returns the levelOfCareStr.
		 */
		public String getLevelOfCareStr() {
			return levelOfCareStr;
		}
		/**
		 * @return Returns the permanencyPlanStr.
		 */
		public String getPermanencyPlanStr() {
			return permanencyPlanStr;
		}

		/*
		 * @param str
		 * @return
		 */
		private boolean notNullNotEmptyString( String str )
		{
			return( str != null &&  (str.length() > 0) ) ;
		}
	}
	
	public static class GoalInfo
	{
		private String domainTypeCd = "";
		private String domainTypeStr = "";
		private String[] personsResponsibleIds;		
		private String timeFrameCd;
		private String timeFrameStr = "";
		private String otherTimeFrameDesc = "";
		private String goal;
		private String progressNotes;
		private String intervention; //for adding intervention JIMS200075816 
		private String endRecommendations;
		private String goalId = "";
		private String[] selectedRules;
		private String statusCd;
		private String statusStr;
		private boolean goalEnded = false;
		
		
		//The following fields are being added to be able to save 
		// the proper status of the goal and flip from Approved back to Pending
		private String oldDomainTypeCd = "";
		private String[] oldPersonsResponsibleIds;	
		private String oldTimeFrameCd;
		private String oldGoal;
		private String[] oldSelectedRules;
		//added for bug #14827 starts
		private String oldProgressNotes;
		private String oldIntervention;
		//added for bug #14827 ends
		
		//collections
		private Collection personsResponsibleDisplay = new ArrayList();
		private Collection associatedRules = new ArrayList();
		private Collection selectedRulesList = new ArrayList();
		
		public GoalInfo()
		{
			statusStr = "PENDING";	
		}

		public void clear(){
			//selectedRules = new String[0];		
			selectedRulesList = new ArrayList();
		}
		
		public void clearAssociatedRules()
		{
			associatedRules = new ArrayList();
		}

		/*
		 * @param str
		 * @return
		 */
		private boolean notNullNotEmptyString( String str )
		{
			return( str != null &&  (str.length() > 0) ) ;
		}
		
		/*
		 * @return
		 */
		public boolean majorGoalChange()
		{
			boolean majorChange = false;
			
			if( notNullNotEmptyString( goalId ) )
			{
				if(oldDomainTypeCd.equals(domainTypeCd))
				{
					if(oldTimeFrameCd.equals(timeFrameCd))
					{
						if(oldGoal.equals(goal))
						{
							if(oldPersonsResponsibleIds.length == personsResponsibleIds.length)
							{
								String newPerson = "";
								String oldPerson = "";
								boolean personFound = false;
								for(int loopX = 0; loopX < oldPersonsResponsibleIds.length; loopX++)
								{
									oldPerson = oldPersonsResponsibleIds[loopX];
									personFound = false;
									for(int loopY = 0; loopY < personsResponsibleIds.length; loopY++)
									{
										newPerson = personsResponsibleIds[loopY];
										if( !personFound  && oldPerson.equals(newPerson) )
										{
											personFound = true;
											break;
										}
									}

									if( !personFound )
									{
										return( majorChange = true );
									}
								}
							}
							else
							{
								majorChange = true;
							}
							if(!oldProgressNotes.equals(progressNotes)){
								majorChange=true;
							}
							if(!oldIntervention.equals(intervention)){
								majorChange=true;
							}
						}
						else
						{
							majorChange = true;
						}				
					}
					else
					{
						majorChange = true;
					}
				}
				else
				{
					majorChange = true;
				}
			}
			
			return majorChange;
		}
		
		/**
		 * @return the oldProgressNotes
		 */
		public String getOldProgressNotes() {
			return oldProgressNotes;
		}

		/**
		 * @param oldProgressNotes the oldProgressNotes to set
		 */
		public void setOldProgressNotes(String oldProgressNotes) {
			this.oldProgressNotes = oldProgressNotes;
		}

		/**
		 * @return the oldIntervention
		 */
		public String getOldIntervention() {
			return oldIntervention;
		}

		/**
		 * @param oldIntervention the oldIntervention to set
		 */
		public void setOldIntervention(String oldIntervention) {
			this.oldIntervention = oldIntervention;
		}

		/**
		 * @return Returns the goalEnded.
		 */
		public boolean isGoalEnded() {
			return goalEnded;
		}
		/**
		 * @param goalEnded The goalEnded to set.
		 */
		public void setGoalEnded(boolean goalEnded) {
			this.goalEnded = goalEnded;
		}
		/**
		 * @return Returns the domainTypeCd.
		 */
		public String getDomainTypeCd() {
			return domainTypeCd;
		}
		/**
		 * @param domainTypeCd The domainTypeCd to set.
		 */
		public void setDomainTypeCd(String domainTypeCd) {
			this.domainTypeCd = domainTypeCd;
			this.domainTypeStr = CodeHelper.getCodeDescriptionByCode(
						CodeHelper.getCodes("GOAL_DOMAIN_TYPE"), domainTypeCd);
		}
		
		/**
		 * @return Returns the domainTypeCd.
		 */
		public String getStatusCd() {
			return statusCd;
		}
		/**
		 * @param domainTypeCd The domainTypeCd to set.
		 */
		public void setStatusCd(String stCd) {
			this.statusCd = stCd;
			this.statusStr = CodeHelper.getCodeDescriptionByCode(CodeHelper.getCodes("GOAL_STATUS"), statusCd);
		}
		
		public String getStatusStr() {
			return this.statusStr;
		}
		/**
		 * @return Returns the goal.
		 */
		public String getGoal() {
			return goal;
		}
		/**
		 * @param goal The goal to set.
		 */
		public void setGoal(String goal) {
			this.goal = goal;
		}
		/**
		 * @return Returns the personsResponsibleDisplay.
		 */
		public Collection getPersonsResponsibleDisplay() {
			return personsResponsibleDisplay;
		}
		/**
		 * @param personsResponsibleDisplay The personsResponsibleDisplay to set.
		 */
		public void setPersonsResponsibleDisplay(Collection personsResponsibleDisplay) {
			this.personsResponsibleDisplay = personsResponsibleDisplay;
		}
		/**
		 * @return Returns the personsResponsibleIds.
		 */
		public String[] getPersonsResponsibleIds() {
			return personsResponsibleIds;
		}
		/**
		 * @param personsResponsibleIds The personsResponsibleIds to set.
		 */
		public void setPersonsResponsibleIds(String[] personsResponsibleIds) {
			this.personsResponsibleIds = personsResponsibleIds;
		}
		/**
		 * @return Returns the progressNotes.
		 */
		public String getProgressNotes() {
			return progressNotes;
		}
		/**
		 * @param progressNotes The progressNotes to set.
		 */
		public void setProgressNotes(String progressNotes) {
			this.progressNotes = progressNotes;
		}
		/**
		 * @return Returns the intervention.
		 */
		public String getIntervention() {
			return intervention;
		}
		/**
		 * @param intervention The intervention to set.
		 */
		public void setIntervention(String intervention) {
			this.intervention = intervention;
		}
		/**
		 * @return Returns the timeFrameCd.
		 */
		public String getTimeFrameCd() {
			return timeFrameCd;
		}
		/**
		 * @param timeFrameCd The timeFrameCd to set.
		 */
		public void setTimeFrameCd(String timeFrameCd) {
			this.timeFrameCd = timeFrameCd;
			this.timeFrameStr = CodeHelper.getCodeDescriptionByCode(CodeHelper.getCodes("GOAL_TIMEFRAME"), timeFrameCd);
		}
		/**
		 * @return the otherTimeFrameDesc
		 */
		public String getOtherTimeFrameDesc() {
			return otherTimeFrameDesc;
		}

		/**
		 * @param otherTimeFrameDesc the otherTimeFrameDesc to set
		 */
		public void setOtherTimeFrameDesc(String otherTimeFrameDesc) {
			this.otherTimeFrameDesc = otherTimeFrameDesc;
		}		
		/**
		 * @return Returns the endRecommendations.
		 */
		public String getEndRecommendations() {
			return endRecommendations;
		}
		/**
		 * @param endRecommendations The endRecommendations to set.
		 */
		public void setEndRecommendations(String endRecommendations) {
			this.endRecommendations = endRecommendations;
		}
		/**
		 * @return Returns the domainTypeStr.
		 */
		public String getDomainTypeStr() {
			return domainTypeStr;
		}
		/**
		 * @param domainTypeStr The domainTypeStr to set.
		 */
		public void setDomainTypeStr(String domainTypeStr) {
			this.domainTypeStr = domainTypeStr;
		}
		/**
		 * @return Returns the timeFrameStr.
		 */
		public String getTimeFrameStr() {
			return timeFrameStr;
		}
		/**
		 * @param timeFrameStr The timeFrameStr to set.
		 */
		public void setTimeFrameStr(String timeFrameStr) {
			this.timeFrameStr = timeFrameStr;
			
		}
		/**
		 * @return Returns the associatedRules.
		 */
		public Collection getAssociatedRules() {
			return associatedRules;
		}
		/**
		 * @param associatedRules The associatedRules to set.
		 */
		public void setAssociatedRules(Collection associatedRules) {
			this.associatedRules = associatedRules;
		}
		/**
		 * @return Returns the goalId.
		 */
		public String getGoalId() {
			return goalId;
		}
		/**
		 * @param goalId The goalId to set.
		 */
		public void setGoalId(String goalId) {
			this.goalId = goalId;
		}
		/**
		 * @return Returns the selectedRules.
		 */
		public String[] getSelectedRules() {
			return selectedRules;
		}
		/**
		 * @param selectedRules The selectedRules to set.
		 */
		public void setSelectedRules(String[] selectedRules) {
			this.selectedRules = selectedRules;
		}
		/**
		 * @return Returns the selectedRulesList.
		 */
		public Collection getSelectedRulesList() {
			return selectedRulesList;
		}
		/**
		 * @param selectedRulesList The selectedRulesList to set.
		 */
		public void setSelectedRulesList(Collection selectedRulesList) {
			this.selectedRulesList = selectedRulesList;
		}
		/**
		 * @return Returns the oldDomainTypeCd.
		 */
		public String getOldDomainTypeCd() {
			return oldDomainTypeCd;
		}
		/**
		 * @param oldDomainTypeCd The oldDomainTypeCd to set.
		 */
		public void setOldDomainTypeCd(String oldDomainTypeCd) {
			this.oldDomainTypeCd = oldDomainTypeCd;
		}
		/**
		 * @return Returns the oldGoal.
		 */
		public String getOldGoal() {
			return oldGoal;
		}
		/**
		 * @param oldGoal The oldGoal to set.
		 */
		public void setOldGoal(String oldGoal) {
			this.oldGoal = oldGoal;
		}
		/**
		 * @return Returns the oldPersonsResponsibleIds.
		 */
		public String[] getOldPersonsResponsibleIds() {
			return oldPersonsResponsibleIds;
		}
		/**
		 * @param oldPersonsResponsibleIds The oldPersonsResponsibleIds to set.
		 */
		public void setOldPersonsResponsibleIds(String[] oldPersonsResponsibleIds) {
			this.oldPersonsResponsibleIds = oldPersonsResponsibleIds;
		}
		/**
		 * @return Returns the oldTimeFrameCd.
		 */
		public String getOldTimeFrameCd() {
			return oldTimeFrameCd;
		}
		/**
		 * @param oldTimeFrameCd The oldTimeFrameCd to set.
		 */
		public void setOldTimeFrameCd(String oldTimeFrameCd) {
			this.oldTimeFrameCd = oldTimeFrameCd;
		}
		/**
		 * @return Returns the oldSelectedRules.
		 */
		public String[] getOldSelectedRules() {
			return oldSelectedRules;
		}
		/**
		 * @param oldSelectedRules The oldSelectedRules to set.
		 */
		public void setOldSelectedRules(String[] oldSelectedRules) {
			this.oldSelectedRules = oldSelectedRules;
		}
	}
	
	
	public static class PersonResponsible implements Comparable 
	{
		private String name;
		private String type;
		private String description;
		
		
		public String getName() {
			return name;
		}
		
		public void setName(String n) {
			name = n;
		}
		
		public String getType() {
			return type;
		}
		
		public void setType(String n) {
			type = n;
		}
		
		public int compareTo(Object obj) 
		{
			PersonResponsible evt = (PersonResponsible) obj;
			String person1 = name.trim();
			String person2 = evt.getName().trim();		
			
			return person1.compareToIgnoreCase(person2);
		}
		
		/**
		 * @return Returns the description.
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * @param description The description to set.
		 */
		public void setDescription(String description) {
			this.description = description;
		}
	}
	
	
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	
	/**
	 * @return Returns the supervisionType.
	 */
	public String getSupervisionType() {
		return supervisionType;
	}
	/**
	 * @param supervisionType The supervisionType to set.
	 */
	public void setSupervisionType(String supervisionType) {
		this.supervisionType = supervisionType;
	}
	/**
	 * @return Returns the placementInfoExist.
	 */
	public String getPlacementInfoExist() {
		return placementInfoExist;
	}
	/**
	 * @param placementInfoExist The placementInfoExist to set.
	 */
	public void setPlacementInfoExist(String placementInfoExist) {
		this.placementInfoExist = placementInfoExist;
	}
	/**
	 * @return Returns the caseplanExist.
	 */
	public String getCaseplanExist() {
		return caseplanExist;
	}
	/**
	 * @param caseplanExist The caseplanExist to set.
	 */
	public void setCaseplanExist(String caseplanExist) {
		this.caseplanExist = caseplanExist;
	}
	/**
	 * @return Returns the cpActivities.
	 */
	public Collection getCpActivities() {
		return cpActivities;
	}
	/**
	 * @param cpActivities The cpActivities to set.
	 */
	public void setCpActivities(Collection cpActivities) {
		this.cpActivities = cpActivities;
	}
	/**
	 * @return Returns the jpoReviewGoalList.
	 */
	public Collection getJpoReviewGoalList() {
		return jpoReviewGoalList;
	}
	/**
	 * @param jpoReviewGoalList The jpoReviewGoalList to set.
	 */
	public void setJpoReviewGoalList(Collection jpoReviewGoalList) {
		this.jpoReviewGoalList = jpoReviewGoalList;
	}
	/**
	 * @return Returns the reqReviewComments.
	 */
	public String getReqReviewComments() {
		return reqReviewComments;
	}
	/**
	 * @param reqReviewComments The reqReviewComments to set.
	 */
	public void setReqReviewComments(String reqReviewComments) {
		this.reqReviewComments = reqReviewComments;
	}
	
	/**
	 * @return the allowUpdates
	 */
	public boolean isAllowUpdates() {
		return allowUpdates;
	}
	/**
	 * @param allowUpdates the allowUpdates to set
	 */
	public void setAllowUpdates(boolean allowUpdates) {
		this.allowUpdates = allowUpdates;
	}
	/**
	 * @return the secondaryAction
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}
	/**
	 * @param secondaryAction the secondaryAction to set
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}
	/**
	 * @return Returns the caseplanList.
	 */
	public Collection getCaseplanList() {
		return caseplanList;
	}
	/**
	 * @param caseplanList The caseplanList to set.
	 */
	public void setCaseplanList(Collection caseplanList) {
		this.caseplanList = caseplanList;
	}
	/**
	 * @return Returns the profileActivityList.
	 */
	public Collection getProfileActivityList() {
		return profileActivityList;
	}
	/**
	 * @param profileActivityList The profileActivityList to set.
	 */
	public void setProfileActivityList(Collection profileActivityList) {
		this.profileActivityList = profileActivityList;
	}
	/**
	 * @return Returns the goalDetailsList.
	 */
	public Collection getGoalDetailsList() {
		return goalDetailsList;
	}
	/**
	 * @param goalDetailsList The goalDetailsList to set.
	 */
	public void setGoalDetailsList(ArrayList goalDetailsList) {
		this.goalDetailsList = goalDetailsList;
	}
	
	/**
	 * @return Returns the juvDetails.
	 */
	public CaseplanDocJuvDetailsResponseEvent getJuvDetails() {
		return juvDetails;
	}
	/**
	 * @param juvDetails The juvDetails to set.
	 */
	public void setJuvDetails(CaseplanDocJuvDetailsResponseEvent juvDetails) {
		this.juvDetails = juvDetails;
	}
	
	/**
	 * @return Returns the juvenileSchoolHistory.
	 */
	public Collection getJuvenileSchoolHistory() {
		return juvenileSchoolHistory;
	}
	/**
	 * @param juvenileSchoolHistory The juvenileSchoolHistory to set.
	 */
	public void setJuvenileSchoolHistory(ArrayList juvenileSchoolHistory) {
		this.juvenileSchoolHistory = juvenileSchoolHistory;
	}

	/*
	 * @return
	 */
	public JuvenileSchoolHistoryResponseEvent getJuvenileLatestSchoolHistory()
	{
		if( juvenileSchoolHistory == null || juvenileSchoolHistory.size() == 0 ) 
		{
			return new JuvenileSchoolHistoryResponseEvent();
		}
		else if(juvenileSchoolHistory.size() == 1) 
		{
			return (JuvenileSchoolHistoryResponseEvent)juvenileSchoolHistory.get(0);
		}
		else 
		{
			JuvenileSchoolHistoryResponseEvent latestSchoolHistory = null;
			for(Iterator iter = juvenileSchoolHistory.iterator();
					iter.hasNext(); /*empty*/) 
			{
				JuvenileSchoolHistoryResponseEvent schoolHistoryRE = (JuvenileSchoolHistoryResponseEvent)iter.next();
				if( latestSchoolHistory == null || 
					latestSchoolHistory.getCreateDate().compareTo(schoolHistoryRE.getCreateDate()) < 0)  
				{
					latestSchoolHistory = schoolHistoryRE;
				}
			}
			
			return latestSchoolHistory;			
		}
	}
	
	/**
	 * @return Returns the placementInfo.
	 */
	public PlacementInfoResponseEvent getPlacementInfo() {
		return placementInfo;
	}
	/**
	 * @param placementInfo The placementInfo to set.
	 */
	public void setPlacementInfo(PlacementInfoResponseEvent placementInfo) {
		this.placementInfo = placementInfo;
	}

	/*
	 * @param goalType
	 * @return
	 */
	public ArrayList getGoalsByGoalType(String goalType)
	{
		ArrayList goals = new ArrayList();
		for(Iterator iter = this.getGoalDetailsList().iterator(); 
				iter.hasNext(); /*empty*/)
		{
			GoalInfo goal = (GoalInfo)iter.next();
			if( goal.getDomainTypeCd().equals(goalType) )
			{
				goals.add(goal);
			}
		}
		
		return goals;
	}


	/* Will get rid of these once we figure out a way to pass in value to getter in UJAC*/
	public ArrayList getMedicalGoals()
	{
		return getGoalsByGoalType(CaseplanForm.MEDICAL);
	}
	public ArrayList getSafetySecurityGoals()
	{
		return getGoalsByGoalType(CaseplanForm.SAFETY_SECURITY);
	}
	public ArrayList getRecreationalGoals()
	{
		return getGoalsByGoalType(CaseplanForm.RECREATIONAL);
	}
	public ArrayList getEducationalGoals()
	{
		return getGoalsByGoalType(CaseplanForm.EDUCATIONAL);
	}
	public ArrayList getEmotionalGoals()
	{
		return getGoalsByGoalType(CaseplanForm.EMOTIONAL);
	}
	public ArrayList getSocializationGoals()
	{
		return getGoalsByGoalType(CaseplanForm.SOCIALIZATION);
	}
	public ArrayList getPreparationForAdultLivingGoals()
	{
		return getGoalsByGoalType(CaseplanForm.PREPARATION_FOR_ADULT_LIVING);
	}
	public ArrayList getFamilyServicesGoals()
	{
		return getGoalsByGoalType(CaseplanForm.FAMILY_SERVICES);
	}
	public ArrayList getSupportServicesGoals()
	{
		return getGoalsByGoalType(CaseplanForm.SUPPORT_SERVICES);
	}
	public ArrayList getContactFrequency()
	{
		return getGoalsByGoalType(CaseplanForm.CONTACT_FREQUENCY);
	}
	public ArrayList getFamilyParticipation()
	{
		return getGoalsByGoalType(CaseplanForm.FAMILY_PARTICIPATION);
	}
    
	/**
	 * @return Returns the dentistContact.
	 */
	public UIJuvenileContact getDentistContact() {
		return dentistContact;
	}
	/**
	 * @param dentistContact The dentistContact to set.
	 */
	public void setDentistContact(UIJuvenileContact dentistContact) {
		this.dentistContact = dentistContact;
	}
	/**
	 * @return Returns the medicalContact.
	 */
	public UIJuvenileContact getMedicalContact() {
		return medicalContact;
	}
	/**
	 * @param medicalContact The medicalContact to set.
	 */
	public void setMedicalContact(UIJuvenileContact medicalContact) {
		this.medicalContact = medicalContact;
	}
	
	public Date getTodaysDate()
	{
		return new Date();
	}
	/**
	 * @return Returns the healthIssueList.
	 */
	public ArrayList getHealthIssueList() {
		return healthIssueList;
	}
	/**
	 * @param healthIssueList The healthIssueList to set.
	 */
	public void setHealthIssueList(ArrayList healthIssueList) {
		this.healthIssueList = healthIssueList;
	}
	/**
	 * @return Returns the medicationList.
	 */
	public ArrayList getMedicationList() {
		return medicationList;
	}
	/**
	 * @param medicationList The medicationList to set.
	 */
	public void setMedicationList(ArrayList medicationList) {
		this.medicationList = medicationList;
	}
	/**
	 * @return Returns the loggedOnUserId.
	 */
	public String getLoggedOnUserId() {
		return loggedOnUserId;
	}
	/**
	 * @param loggedOnUserId The loggedOnUserId to set.
	 */
	public void setLoggedOnUserId(String loggedOnUserId) {
		this.loggedOnUserId = loggedOnUserId;
	}
	/**
	 * @return Returns the jpoReviews.
	 */
	public Collection getJpoReviews() {
		return jpoReviews;
	}
	/**
	 * @param jpoReviews The jpoReviews to set.
	 */
	public void setJpoReviews(Collection jpoReviews) {
		this.jpoReviews = jpoReviews;
	}

	/*
	 * @return
	 */
	public boolean isResidential() 
	{
		if( supervisionType != null )
		{
			String myCat = this.getSupervisionCategoryId();
			if(myCat != null && myCat.equals(UIConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES)) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @return Returns the typesOfMedicalCoverage.
	 */
	public ArrayList getTypesOfMedicalCoverage() {
		return typesOfMedicalCoverage;
	}
	/**
	 * @param typesOfMedicalCoverage The typesOfMedicalCoverage to set.
	 */
	public void setTypesOfMedicalCoverage(ArrayList typesOfMedicalCoverage) {
		this.typesOfMedicalCoverage = typesOfMedicalCoverage;
	}
	/**
	 * @return Returns the isJuvProfile.
	 */
	public boolean isJuvProfile() {
		return isJuvProfile;
	}
	/**
	 * @param isJuvProfile The isJuvProfile to set.
	 */
	public void setJuvProfile(boolean isJuvProfile) {
		this.isJuvProfile = isJuvProfile;
	}
	
	/*
	 * @return
	 */
	public String getSupervisionCategoryId()
	{
		if( supervisionType != null && !supervisionType.equals(""))
		{
			String cat = UIJuvenileCaseworkHelper.getSupCatFromType(supervisionType);
			if( cat != null )
			{
				return cat;
			}
		}

		return "";
	}

	public boolean isInReviewForCLM( )
	{
		return inReviewForCLM ;
	}

	public void setInReviewForCLM( boolean inReviewForCLM )
	{
		this.inReviewForCLM = inReviewForCLM ;
	}
	
	public JuvenileProfileCasefileListResponseEvent getSelectedCasefile() {
		return selectedCasefile;
	}

	public void setSelectedCasefile(
			JuvenileProfileCasefileListResponseEvent selectedCasefile) {
		this.selectedCasefile = selectedCasefile;
	}
	
	public Collection getCasefiles() {
		return casefiles;
	}

	public void setCasefiles(Collection casefiles) {
		this.casefiles = casefiles;
	}
	
	/**
	 * @return Returns the supervisionTypeDescription.
	 */
	public String getSupervisionTypeDescription() {
		return supervisionTypeDescription;
	}
	/**
	 * @param status The supervisionTypeDescription to set.
	 */
	public void setSupervisionTypeDescription(String supervisionTypeDescription) {
		this.supervisionTypeDescription = supervisionTypeDescription;
	}
	
	public static class Acknowledgment
	{
		private String signatureStatus;
		private Date entryDate;
		private String entryDateStr;
		private String explanation;
		public Acknowledgment(){
			entryDate=new Date();
		}
		
		
		public void setSignatureStatus(String signatureStatus) {
			this.signatureStatus = signatureStatus;
		}
		
		/**
		 * @return Returns the signatureStatus.
		 */
		public String getSignatureStatus() {
			return signatureStatus;
		}
		
		public void setEntryDate(Date entryDate) {
			this.entryDate = entryDate;
		}		
		/**
		 * @return Returns the entryDate.
		 */
		public Date getEntryDate() {
			return entryDate;
		}
		
		public void setExplanation(String explanation) {
			this.explanation = explanation;
		}		
		/**
		 * @return Returns the explanation.
		 */
		public String getExplanation() {
			return explanation;
		}
		/**
		 * @return Returns the entryDateStr.
		 */
		public String getEntryDateStr() {
			return entryDateStr;
		}
		
		public void setEntryDateStr(String entryDateStr) {
			this.entryDateStr = entryDateStr;
		}		
	}
		
		
}

