/*
 * Created on May 9, 2007
 *
 */
package ui.juvenilecase.programreferral.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.interviewinfo.to.SocialHistoryReportDataTO;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import naming.PDCodeTableConstants;

import org.apache.struts.action.ActionForm;

import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.programreferral.UIProgramReferralBean;

/**
 * @author dwilliamson
 *
 */
public class ProgramReferralForm extends ActionForm
{
	private static Collection emptyColl = new ArrayList();
	
	private List activeReferralList;
	private int activeReferralListSize;
	private List closedReferralList;
	private List rejectedReferralList;
	private Collection serviceProviderList;
	private Collection programNameList;
	private Collection programNames;
	private Collection supervisionTypes;
	private Collection juvLocations;
	
	private String action = "";
	private String selectedValue = "";
	private boolean allowCreate = true;
	private JuvenileProfileCasefileListResponseEvent selectedCasefile;
	private String outComeSubcategoryOptCd;
	private String outComeSubcategoryReqdCd;
	
	private CalendarServiceEventResponseEvent currentServiceEvent;
	
	private UIProgramReferralBean programReferral;
	
	private CalendarServiceEventResponseEvent otherCurrentServiceEvent;
	private UIProgramReferralBean otherProgramReferral;
	
	private List outComeList;
	private List outComeSubcategoryOptList;
	private List outComeSubcategoryReqdList;
	
	 //<KISHORE>JIMS200060472 : Add Social Hist. link to Program Ref Detail(UI)-KK
	private Collection reportHistory = emptyColl;	//InterviewReportHeaderResponseEvent
	public SocialHistoryReportDataTO socialHistoryData;
	private Map referralStatusMap;
	
	//Added for US 32107
	private String restrictedAccessFeature;
	
	private String supervisionTypeId;
	private String locationId;
	private String officerLastName;
	private String officerFirstName;
	private String officerMiddleName;
	private String[] selectedPrograms;
	private Map<String, Integer> groupMap = new HashMap<String, Integer>();
	private String transferredProgRef;
	private String casefileClosingEndDate;
	private boolean sendEmailToContacts;
	
	public ProgramReferralForm()
	{
		emptyColl = new ArrayList();
		activeReferralList = (List) emptyColl;
		closedReferralList = (List) emptyColl;
		outComeList = (List) emptyColl;
		outComeSubcategoryOptList = (List) emptyColl;
		outComeSubcategoryReqdList = (List) emptyColl;	
		programReferral = new UIProgramReferralBean();
		otherProgramReferral = null;
		currentServiceEvent =null;
		otherCurrentServiceEvent = null;
		action = "";
		selectedValue = "";
		casefileClosingEndDate= "";
		reportHistory = (List) emptyColl;
		socialHistoryData = new SocialHistoryReportDataTO();
		this.serviceProviderList = (List) emptyColl;
		this.programNameList     = (List) emptyColl;
		this.sendEmailToContacts = false;
	}
	
	public void clearAll()
	{
		activeReferralList = (List) emptyColl;
		closedReferralList = (List) emptyColl;
		programReferral = new UIProgramReferralBean();
		currentServiceEvent = null;
		otherCurrentServiceEvent = null;
		reportHistory = (List) emptyColl;
		socialHistoryData = new SocialHistoryReportDataTO();
		this.allowCreate = false;
		this.setOutComeSubcategoryOptCd(null);
		this.setOutComeSubcategoryReqdCd(null);
		this.supervisionTypeId= "";
		this.selectedValue = "";
		this.programNameList = (List) emptyColl;
		this.selectedPrograms = null;
		this.locationId= "";
		this.officerFirstName = "";
		this.officerLastName = "";
		this.officerMiddleName="";
		this.transferredProgRef = "";
		this.casefileClosingEndDate="";
	}
	
		
	public static class CasefileReferral
	{
		public String casefileId;
		public String supervisionName;		
		
		public List programReferralList;
		
		/**
		 * 
		 */
		public CasefileReferral() {
			programReferralList = new ArrayList();			
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
		public String getSupervisionName()
		{
		    return supervisionName;
		}
		public void setSupervisionName(String supervisionName)
		{
		    this.supervisionName = supervisionName;
		}
		
		/**
		 * @return Returns the programReferralList.
		 */
		public List getProgramReferralList() {
			return programReferralList;
		}
		/**
		 * @param programReferralList The programReferralList to set.
		 */
		public void setProgramReferralList(List programReferralList) {
			this.programReferralList = programReferralList;
		}
	}
// end public static class CasefileReferral
		
	
	/**
	 * @return Returns the emptyColl.
	 */
	public static Collection getEmptyColl() {
		return emptyColl;
	}
	/**
	 * @param emptyColl The emptyColl to set.
	 */
	public static void setEmptyColl(Collection emptyColl) {
		ProgramReferralForm.emptyColl = emptyColl;
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
	 * @return Returns the activeReferralList.
	 */
	public List getActiveReferralList() {
		return activeReferralList;
	}
	/**
	 * @param activeReferralList The activeReferralList to set.
	 */
	public void setActiveReferralList(List activeReferralList) {
		this.activeReferralList = activeReferralList;
	}
	/**
	 * @return Returns the closedReferralList.
	 */
	public List getClosedReferralList() {
		return closedReferralList;
	}
	/**
	 * @param closedReferralList The closedReferralList to set.
	 */
	public void setClosedReferralList(List closedReferralList) {
		this.closedReferralList = closedReferralList;
	}
	/**
	 * @return Returns the programReferral.
	 */
	public UIProgramReferralBean getProgramReferral() {
		return programReferral;
	}
	/**
	 * @param programReferral The programReferral to set.
	 */
	public void setProgramReferral(UIProgramReferralBean programReferral) {
		this.programReferral = programReferral;
	}	
	/**
	 * @return Returns the currentServiceEvent.
	 */
	public CalendarServiceEventResponseEvent getCurrentServiceEvent() {
		return currentServiceEvent;
	}
	/**
	 * @param currentServiceEvent The currentServiceEvent to set.
	 */
	public void setCurrentServiceEvent(CalendarServiceEventResponseEvent currentServiceEvent) {
		this.currentServiceEvent = currentServiceEvent;
	}
	/**
	 * @return Returns the otherCurrentServiceEvent.
	 */
	public CalendarServiceEventResponseEvent getOtherCurrentServiceEvent() {
		return otherCurrentServiceEvent;
	}
	/**
	 * @param otherCurrentServiceEvent The otherCurrentServiceEvent to set.
	 */
	public void setOtherCurrentServiceEvent(CalendarServiceEventResponseEvent otherCurrentServiceEvent) {
		this.otherCurrentServiceEvent = otherCurrentServiceEvent;
	}
	/**
	 * @return Returns the otherProgramReferral.
	 */
	public UIProgramReferralBean getOtherProgramReferral() {
		return otherProgramReferral;
	}
	/**
	 * @param otherProgramReferral The otherProgramReferral to set.
	 */
	public void setOtherProgramReferral(UIProgramReferralBean otherProgramReferral) {
		this.otherProgramReferral = otherProgramReferral;
	}
	/**
	 * @return Returns the outComeList.
	 */
	public List getOutComeList() 
	{
		if( outComeList == null || outComeList.size() < 1 )
		{
			outComeList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.PROGRAM_REFERRAL_OUTCOME);
		}
		return outComeList;
	}
	/**
	 * @param outComeList The outComeList to set.
	 */
	public void setOutComeList(List outComeList) {
		this.outComeList = outComeList;
	}

	/**
	 * @return the outComeSubcategoryOptList
	 */
	public List getOutComeSubcategoryOptList() {
		return outComeSubcategoryOptList;
	}

	/**
	 * @param outComeSubcategoryOptList the outComeSubcategoryOptList to set
	 */
	public void setOutComeSubcategoryOptList(List outComeSubcategoryOptList) {
		this.outComeSubcategoryOptList = outComeSubcategoryOptList;
	}

	/**
	 * @return the outComeSubcategoryReqdList
	 */
	public List getOutComeSubcategoryReqdList() {
		return outComeSubcategoryReqdList;
	}

	/**
	 * @param outComeSubcategoryReqdList the outComeSubcategoryReqdList to set
	 */
	public void setOutComeSubcategoryReqdList(List outComeSubcategoryReqdList) {
		this.outComeSubcategoryReqdList = outComeSubcategoryReqdList;
	}

	public Collection getReportHistory() {
		return reportHistory;
	}

	public void setReportHistory(Collection reportHistory) {
		this.reportHistory = reportHistory;
	}

	public SocialHistoryReportDataTO getSocialHistoryData() {
		return socialHistoryData;
	}

	public void setSocialHistoryData(SocialHistoryReportDataTO socialHistoryData) {
		this.socialHistoryData = socialHistoryData;
	}

	public void setRejectedReferralList(List rejectedReferralList) {
		this.rejectedReferralList = rejectedReferralList;
	}

	public List getRejectedReferralList() {
		return rejectedReferralList;
	}

	public String getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	/**
	 * @return the allowCreate
	 */
	public boolean isAllowCreate() {
		return allowCreate;
	}

	/**
	 * @param allowCreate the allowCreate to set
	 */
	public void setAllowCreate(boolean allowCreate) {
		this.allowCreate = allowCreate;
	}

	public JuvenileProfileCasefileListResponseEvent getSelectedCasefile() {
		return selectedCasefile;
	}

	public void setSelectedCasefile(
			JuvenileProfileCasefileListResponseEvent selectedCasefile) {
		this.selectedCasefile = selectedCasefile;
	}

	/**
	 * @return the serviceProviderList
	 */
	public Collection getServiceProviderList() {
		return serviceProviderList;
	}

	/**
	 * @param serviceProviderList the serviceProviderList to set
	 */
	public void setServiceProviderList(Collection serviceProviderList) {
		this.serviceProviderList = serviceProviderList;
	}

	/**
	 * @return the programNameList
	 */
	public Collection getProgramNameList() {
		return programNameList;
	}

	/**
	 * @param programNameList the programNameList to set
	 */
	public void setProgramNameList(Collection programNameList) {
		this.programNameList = programNameList;
	}

	/**
	 * @return the programNames
	 */
	public Collection getProgramNames() {
		return programNames;
	}

	/**
	 * @param programNames the programNames to set
	 */
	public void setProgramNames(Collection programNames) {
		this.programNames = programNames;
	}

	public void setReferralStatusMap(Map referralStatusMap) {
		this.referralStatusMap = referralStatusMap;
	}

	public Map getReferralStatusMap() {
		
		List <CodeResponseEvent> codeList = CodeHelper.getCodes(PDCodeTableConstants.PROGRAM_REFERRAL_STATUS);
		
		this.referralStatusMap = new HashMap();
		CodeResponseEvent cre = null;
		
		for (int i = 0; i < codeList.size(); i++) {
			cre = codeList.get(i);
			this.referralStatusMap.put(cre.getCode(), cre.getDescription());
		}
		
		cre = null;
		codeList = null;
		
		return this.referralStatusMap;
	}

	/**
	 * @return the outComeSubcategoryOptCd
	 */
	public String getOutComeSubcategoryOptCd() {
		return outComeSubcategoryOptCd;
	}

	/**
	 * @param outComeSubcategoryOptCd the outComeSubcategoryOptCd to set
	 */
	public void setOutComeSubcategoryOptCd(String outComeSubcategoryOptCd) {
		this.outComeSubcategoryOptCd = outComeSubcategoryOptCd;
	}

	/**
	 * @return the outComeSubcategoryReqdCd
	 */
	public String getOutComeSubcategoryReqdCd() {
		return outComeSubcategoryReqdCd;
	}

	/**
	 * @param outComeSubcategoryReqdCd the outComeSubcategoryReqdCd to set
	 */
	public void setOutComeSubcategoryReqdCd(String outComeSubcategoryReqdCd) {
		this.outComeSubcategoryReqdCd = outComeSubcategoryReqdCd;
	}

	public String getRestrictedAccessFeature() {
		return restrictedAccessFeature;
	}

	public void setRestrictedAccessFeature(String restrictedAccessFeature) {
		this.restrictedAccessFeature = restrictedAccessFeature;
	}

	public Collection getSupervisionTypes()
	{
	    return supervisionTypes;
	}

	public void setSupervisionTypes(Collection supervisionTypes)
	{
	    this.supervisionTypes = supervisionTypes;
	}

	public Collection getJuvLocations()
	{
	    return juvLocations;
	}

	public void setJuvLocations(Collection juvLocations)
	{
	    this.juvLocations = juvLocations;
	}

	public String getSupervisionTypeId()
	{
	    return supervisionTypeId;
	}

	public void setSupervisionTypeId(String supervisionTypeId)
	{
	    this.supervisionTypeId = supervisionTypeId;
	}

	public String getLocationId()
	{
	    return locationId;
	}

	public void setLocationId(String locationId)
	{
	    this.locationId = locationId;
	}

	public String getOfficerLastName()
	{
	    return officerLastName;
	}

	public void setOfficerLastName(String officerLastName)
	{
	    this.officerLastName = officerLastName;
	}

	public String getOfficerFirstName()
	{
	    return officerFirstName;
	}

	public void setOfficerFirstName(String officerFirstName)
	{
	    this.officerFirstName = officerFirstName;
	}

	public String getOfficerMiddleName()
	{
	    return officerMiddleName;
	}

	public void setOfficerMiddleName(String officerMiddleName)
	{
	    this.officerMiddleName = officerMiddleName;
	}

	public int getActiveReferralListSize()
	{
	    return activeReferralListSize;
	}

	public void setActiveReferralListSize(int activeReferralListSize)
	{
	    this.activeReferralListSize = activeReferralListSize;
	}

	public String[] getSelectedPrograms()
	{
	    return selectedPrograms;
	}

	public void setSelectedPrograms(String[] selectedPrograms)
	{
	    this.selectedPrograms = selectedPrograms;
	}

	public Map<String, Integer> getGroupMap()
	{
	    return groupMap;
	}

	public void setGroupMap(Map<String, Integer> groupMap)
	{
	    this.groupMap = groupMap;
	}
	
	public String getTransferredProgRef()
	{
	    return transferredProgRef;
	}

	public void setTransferredProgRef(String transferredProgRef)
	{
	    this.transferredProgRef = transferredProgRef;
	}

	public String getCasefileClosingEndDate()
	{
	    return casefileClosingEndDate;
	}

	public void setCasefileClosingEndDate(String casefileClosingEndDate)
	{
	    this.casefileClosingEndDate = casefileClosingEndDate;
	}

	public boolean isSendEmailToContacts()
	{
	    return sendEmailToContacts;
	}

	public void setSendEmailToContacts(boolean sendEmailToContacts)
	{
	    this.sendEmailToContacts = sendEmailToContacts;
	}	
	
}


