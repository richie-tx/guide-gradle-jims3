/*
 * Created on Mar 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.contact.domintf.IPhoneNumber;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import ui.supervision.programReferral.AppointmentInfoBean;
import ui.supervision.programReferral.CSCServiceProviderProgLocBean;
import ui.supervision.programReferral.ReferralFormField;
import ui.supervision.programReferral.ReferralTypeBean;

/**
 * 
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSCProgRefForm extends ActionForm {

public class RefTypeCodeNRefTypeNumNRefIdValue
	{
		private String progRefId;
		private String refNum;
		private String refTypeCd;
		private String refTypeDesc;
		
		public RefTypeCodeNRefTypeNumNRefIdValue(String aRefTypeCd, String aRefTypeDesc, String aRefNum, String aProgRefId)
		{
			refNum=aRefNum;
			refTypeCd=aRefTypeCd;
			progRefId=aProgRefId;
			refTypeDesc=aRefTypeDesc;
		}
		
		/**
		 * @return the progRefId
		 */
		public String getProgRefId() {
			return progRefId;
		}
		/**
		 * @return Returns the refNum.
		 */
		public String getRefNum() {
				return refNum;
		}

		/**
		 * @return the refTypeCd
		 */
		public String getRefTypeCd() {
			return refTypeCd;
		}

		/**
		 * @return the refTypeDesc
		 */
		public String getRefTypeDesc() {
			return refTypeDesc;
		}

		/**
		 * @param progRefId the progRefId to set
		 */
		public void setProgRefId(String progRefId) {
			this.progRefId = progRefId;
		}

		/**
		 * @param refNum The refNum to set.
		 */
		public void setRefNum(String refNum) {
			this.refNum = refNum;
		}

		/**
		 * @param refTypeCd the refTypeCd to set
		 */
		public void setRefTypeCd(String refTypeCd) {
			this.refTypeCd = refTypeCd;
		}

		/**
		 * @param refTypeDesc the refTypeDesc to set
		 */
		public void setRefTypeDesc(String refTypeDesc) {
			this.refTypeDesc = refTypeDesc;
		}
}

	//	 Default Elements in all forms
	private String action = "";
	
	private String agencyId;
	private String userAgency;
	
	// FORM SPECIFIC VALUES
	
	//	to store appointment card info
	private AppointmentInfoBean appointmentInfoBean;
	private boolean autoReferral = false;
	private List availableReferralTypesList;  // Referral Type Bean
	private List availableReferralGroupDescriptionList;  
	private List availableSPList;  // CSCServiceProviderBean
	//	Casenote fields
	private String casenoteComments;
	//	stores the available active cases of a defendant
	private List casesAvailableList=new ArrayList();
	
//	stores the supervision order condition for the selected case
	private List conditionsList; // SupervisionOrderConditionBean; // CSCCaseInfoBean
	private String confinementLengthDays;
	
	private String confinementLengthMonths;
	
	private String confinementLengthYears;
	private boolean contractProgram;
	//	store the selected criminal CaseId
	private String criminalCaseId;
	
	private String exitComments;
	private List filteredSPList;  // CSCServiceProviderBean
	private boolean incarcerationReferral = false;
	private boolean isProgramUnitRef = false;
	
	private String oldReferralTypeCd;
	private List prCasenotesList; //Casenote
	private boolean printPacketFlow = false;

	private String progLocId;
	private String programBeginDateAsStr;
	private String programEndDateAsStr;
	//	to store program task comments
	private String programIssueTaskText;
//	stores the OID of the selected Program Referral
	private String progRefId;
	private Collection progRefIdsToDeleteList = new ArrayList();
	private List progressedReferralTypesList;  // ReferralTypeBean
	private String reasonForDischargeDesc;
	private String reasonForDischargeId;
	
	private String reasonForPlacementDesc;
	private String reasonForPlacementId;
	private String referralDateAsStr;
	private List referralformFieldsList; // ReferralFormField
		
	private String referralFormId;
	private String referralFormName;
	//	Referral Form fields
	private List referralFormsBeanList; //ReferralFormBean
	private CSCServiceProviderProgLocBean referralProgramLocBean;
	//	used to store the details of selected program referral
	private String referralStatusCd;
	//	Map of ReferralTypeCd(Key) and RefTypeCodeNRefTypeNumNRefIdValue(value) 
	private Map referralTypeCdNDetailsMap=new HashMap();
	//	stores referralTypeCd(Key) and programReferralId(value)
	private Hashtable referralTypeCdNPgmRefIdMap = new Hashtable();
	private String referralTypeCode;
	
//	Used to store the selected Referral Type in case of Generate FOrm during create/Update_Init process
	private String referralTypeDesc;
	private List referralTypesList = new ArrayList(); //RefTypeCodeNRefTypeNumNRefIdValue
	
	private boolean removeExtNEntryWithOutRestrc = false;
	
	//	set to true if the user is allowed to remove entry/exit on a referral with restriction
	private boolean removeExtNEntryWithRestrc = false;
	private boolean scheduleDateTimeSelected = false;
	private String scheduledDateAsStr;
	private String scheduledTime;
	private String secondaryAction = "";
	//	used to store selected value of ContractProgram for ServiceProvider filter
	private String selectedContractProgram;
	//	used to store selected languages for ServiceProvider filter
	private String[] selectedLanguagesIds;
	//	used to store the (progId-LocId) of the selected programs
	private String selectedPrgmLocIds;
	private List selectedReferralTypesList;  // ReferralTypeBean
	//	used to store selected regions for ServiceProvider filter
	private String[] selectedRegionIds;
	private List selectedServiceProviderProgLocList; // CSCServiceProviderProgLocBean
	//	used to store selected Sex for ServiceProvider filter
	private String selectedSexSpecificId;
	private String[] selectedSPIds;
	private String selectedSPIdsString;
	private List selectedSPList;  // CSCServiceProviderProgramsBean
	
	private String selectedValue = "";
		
	private boolean sentToState = false;
		
	private String serviceProviderId;
	private String spn;
	//  used to store print packet info 
	private List spPacketBeanList; // CSCServiceProviderPacketBean
	
	private String submitComments;
	private String tracerNum;
	private String userEnteredScheduledDateAsStr;
	private String userEnteredScheduledTime;
	
//	used to store the values of user entered Service Provider
	private boolean userEnteredServiceProvider;	
	private String userEnteredServiceProviderName;
	
	private String userEnteredServiceProviderRefTypeCd; 
	private String userEnteredServiceProviderRefTypeDesc; 
	private IPhoneNumber userEnteredSPFax;


	private IPhoneNumber userEnteredSPPhone;
	public void calculateReferralTypes(Map savedReferralTypeCdNRefIdMap)
	{
		this.referralTypeCdNDetailsMap=new HashMap();
		int currentNum=1;
		
		if(selectedReferralTypesList!=null && selectedReferralTypesList.size()>0)
		{
			for(int loopX=0; loopX<selectedReferralTypesList.size(); loopX++)
			{
				ReferralTypeBean myBean=(ReferralTypeBean)selectedReferralTypesList.get(loopX);
				
				if(!referralTypeCdNDetailsMap.containsKey(myBean.getReferralTypeCode()))
				{
					String refTypeNum = Integer.toString(currentNum);
					String refTypeCd = myBean.getReferralTypeCode();
					String refTypeDesc = myBean.getReferralTypeDesc();
					String progRefId = (String)savedReferralTypeCdNRefIdMap.get(refTypeCd);
					
					RefTypeCodeNRefTypeNumNRefIdValue refTypeCodeAndNumMapObj = new RefTypeCodeNRefTypeNumNRefIdValue(refTypeCd, refTypeDesc, refTypeNum, progRefId);
					referralTypeCdNDetailsMap.put(myBean.getReferralTypeCode(),refTypeCodeAndNumMapObj);
					referralTypesList.add(refTypeCodeAndNumMapObj);
					currentNum++;
				}
				myBean.setReferralTypeNum(((RefTypeCodeNRefTypeNumNRefIdValue)referralTypeCdNDetailsMap.get(myBean.getReferralTypeCode())).getRefNum());
				
			}
		}
	}
	
	
	public void clearAll()
	 {
		action = "";
		secondaryAction = "";
		
		selectedValue = "";
		
		autoReferral = false;
		incarcerationReferral = false;
		
		casesAvailableList=new ArrayList();
		criminalCaseId = "";
		
		conditionsList = new ArrayList(); 
		
		availableReferralTypesList = new ArrayList();
		availableReferralGroupDescriptionList = new ArrayList();
		selectedReferralTypesList = new ArrayList();
		progressedReferralTypesList = new ArrayList(); 
		
		referralTypeCdNDetailsMap = new HashMap();
		referralTypeCdNPgmRefIdMap = new Hashtable();
		referralTypesList = new ArrayList();
		progRefIdsToDeleteList = new ArrayList();
		
		availableSPList = new ArrayList();  
		filteredSPList = new ArrayList();
		selectedSPList = new ArrayList(); 

		progRefId = "";
		referralStatusCd = "";
		oldReferralTypeCd = "";
		referralTypeCode = "";
		referralTypeDesc = "";
		serviceProviderId = "";
		progLocId = "";
		scheduledDateAsStr = "";
		scheduledTime = "";
		
		selectedRegionIds = new String[0];
		selectedLanguagesIds = new String[0];
		selectedSexSpecificId = "";
		selectedContractProgram = "";
		
		userEnteredServiceProvider = false;
		userEnteredServiceProviderName = "";
		userEnteredServiceProviderRefTypeCd = "";
		userEnteredServiceProviderRefTypeDesc = "";
		userEnteredSPPhone = new PhoneNumber("");;
		userEnteredSPFax = new PhoneNumber("");
		userEnteredScheduledDateAsStr = "";
		userEnteredScheduledTime = "";
		
		selectedPrgmLocIds = "";
		selectedServiceProviderProgLocList = new ArrayList(); 
		
		scheduleDateTimeSelected = false;
		
		referralDateAsStr = "";
		programBeginDateAsStr = "";
		programEndDateAsStr = "";
		reasonForDischargeId = "";
		reasonForDischargeDesc = "";
		contractProgram = false;
		tracerNum = "";
		confinementLengthYears = "";
		confinementLengthMonths = "";
		confinementLengthDays = "";
		reasonForPlacementId = "";
		reasonForPlacementDesc = "";
		submitComments = "";
		exitComments = "";
		referralProgramLocBean = null;
		
		programIssueTaskText = "";
		
		appointmentInfoBean = null;
		
		spPacketBeanList = new ArrayList(); 
		selectedSPIds = new String[0];
		selectedSPIdsString = "";
		
		referralFormsBeanList = new ArrayList();
		referralFormId = "";
		referralFormName = "";
		referralformFieldsList = new ArrayList(); 
		
		casenoteComments = "";	
		prCasenotesList = new ArrayList(); 
			
		removeExtNEntryWithRestrc = false;
		removeExtNEntryWithOutRestrc = false;
		sentToState = false;
		
		printPacketFlow = false;
	 }
	
	
	public void clearDefendantId()
	 {
		 spn = "";
	 }
	
	
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	
	
	 /**
	 * @return the agencyId
	 */
	public String getAgencyId() {
		return agencyId;
	}
	 
	 
	 /**
	 * @return the appointmentInfoBean
	 */
	public AppointmentInfoBean getAppointmentInfoBean() {
		return appointmentInfoBean;
	}
	

	/**
	 * @return Returns the availableReferralTypesList.
	 */
	public List getAvailableReferralTypesList() {
		return availableReferralTypesList;
	}
	
	/**
	 * @return Returns the availableReferralTypesList.
	 */
	public List getAvailableReferralGroupDescriptionList() {
		return availableReferralGroupDescriptionList;
	}
	
	
	
	/**
	 * @return Returns the availableSPList.
	 */
	public List getAvailableSPList() {
		return availableSPList;
	}
	/**
	 * @return the casenoteComments
	 */
	public String getCasenoteComments() {
		return casenoteComments;
	}
	/**
	 * @return Returns the casesAvailableList.
	 */
	public List getCasesAvailableList() {
		return casesAvailableList;
	}
	/**
	 * @return Returns the conditionsList.
	 */
	public List getConditionsList() {
		return conditionsList;
	}
	/**
	 * @return Returns the confinementLengthDays.
	 */
	public String getConfinementLengthDays() {
		return confinementLengthDays;
	}
	/**
	 * @return Returns the confinementLengthMonths.
	 */
	public String getConfinementLengthMonths() {
		return confinementLengthMonths;
	}
	/**
	 * @return Returns the confinementLengthYears.
	 */
	public String getConfinementLengthYears() {
		return confinementLengthYears;
	}
	/**
	 * @return the criminalCaseId
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	/**
	 * @return the exitComments
	 */
	public String getExitComments() {
		return exitComments;
	}
	
	/**
	 * @return Returns the filteredSPList.
	 */
	public List getFilteredSPList() {
		return filteredSPList;
	}
	/**
	 * @return the oldReferralTypeCd
	 */
	public String getOldReferralTypeCd() {
		return oldReferralTypeCd;
	}
	/**
	 * @return the prCasenotesList
	 */
	public List getPrCasenotesList() {
		return prCasenotesList;
	}
	/**
	 * @return the progLocId
	 */
	public String getProgLocId() {
		return progLocId;
	}
	/**
	 * @return Returns the programBeginDate.
	 */
	public Date getProgramBeginDate()
	{
		Date programBeginDate = null;
		try
		{
			programBeginDate = DateUtil.stringToDate(this.programBeginDateAsStr, DateUtil.DATE_FMT_1);
		}
		catch(Exception ex)
		{
			programBeginDate = null;
		}
		return programBeginDate;
	}
	/**
	 * @return Returns the programBeginDateAsStr.
	 */
	public String getProgramBeginDateAsStr() {
		return programBeginDateAsStr;
	}
	/**
	 * @return Returns the programEndDate.
	 */
	public Date getProgramEndDate()
	{
		Date programEndDate = null;
		try
		{
			programEndDate = DateUtil.stringToDate(this.programEndDateAsStr, DateUtil.DATE_FMT_1);
		}
		catch(Exception ex)
		{
			programEndDate = null;
		}
		return programEndDate;
	}
	/**
	 * @return Returns the programEndDateAsStr.
	 */
	public String getProgramEndDateAsStr() {
		return programEndDateAsStr;
	}
	/**
	 * @return the programIssueTaskText
	 */
	public String getProgramIssueTaskText() {
		return programIssueTaskText;
	}
	/**
	 * @return Returns the progRefId.
	 */
	public String getProgRefId() {
		return progRefId;
	}
	/**
	 * @return the progRefIdsToDeleteList
	 */
	public Collection getProgRefIdsToDeleteList() {
		return progRefIdsToDeleteList;
	}


	/**
	 * @return the progressedReferralTypesList
	 */
	public List getProgressedReferralTypesList() {
		return progressedReferralTypesList;
	}


	/**
	 * @return Returns the reasonForDischargeDesc.
	 */
	public String getReasonForDischargeDesc() {
		return reasonForDischargeDesc;
	}
	/**
	 * @return Returns the reasonForDischargeId.
	 */
	public String getReasonForDischargeId() {
		return reasonForDischargeId;
	}
	/**
	 * @return Returns the reasonForPlacementDesc.
	 */
	public String getReasonForPlacementDesc()
	{
		return reasonForPlacementDesc;
	}
	/**
	 * @return Returns the reasonForPlacementId.
	 */
	public String getReasonForPlacementId() {
		return reasonForPlacementId;
	}
	/**
	 * 
	 * @return
	 */
	public Date getReferralDate()
	{
		Date referralDate = null;
		try
		{
			referralDate = DateUtil.stringToDate(this.referralDateAsStr, DateUtil.DATE_FMT_1);
		}
		catch(Exception ex)
		{
			referralDate = null;
		}
		return referralDate;
	}
	/**
	 * @return the referralDateAsStr
	 */
	public String getReferralDateAsStr() {
		return referralDateAsStr;
	}
	/**
	 * @return the referralformFieldsList
	 */
	public List getReferralformFieldsList() {
		return referralformFieldsList;
	}
	/**
	 * @return the referralFormId
	 */
	public String getReferralFormId() {
		return referralFormId;
	}
	/**
	 * @return the referralFormName
	 */
	public String getReferralFormName() {
		return referralFormName;
	}
	/**
	 * @return the referralFormsBeanList
	 */
	public List getReferralFormsBeanList() {
		return referralFormsBeanList;
	}
	/**
	 * @return the referralProgramLocBean
	 */
	public CSCServiceProviderProgLocBean getReferralProgramLocBean() {
		return referralProgramLocBean;
	}
	/**
	 * @return the referralStatusCd
	 */
	public String getReferralStatusCd() {
		return referralStatusCd;
	}
	/**
	 * @return the referralTypeCdNDetailsMap
	 */
	public Map getReferralTypeCdNDetailsMap() {
		return referralTypeCdNDetailsMap;
	}
	/**
	 * @return the referralTypeCdNPgmRefIdMap
	 */
	public Hashtable getReferralTypeCdNPgmRefIdMap() {
		return referralTypeCdNPgmRefIdMap;
	}
	public String getReferralTypeCode() {
		return referralTypeCode;
	}
	/**
	 * @return the referralTypeDesc
	 */
	public String getReferralTypeDesc() {
		return referralTypeDesc;
	}
	/**
	 * @return the referralTypesList
	 */
	public List getReferralTypesList() {
		return referralTypesList;
	}
	/**
	 * @return the scheduledDateAsStr
	 */
	public String getScheduledDateAsStr() {
		return scheduledDateAsStr;
	}
	/**
	 * @return the scheduledTime
	 */
	public String getScheduledTime() {
		return scheduledTime;
	}
	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}
	/**
	 * @return the selectedContractProgram
	 */
	public String getSelectedContractProgram() {
		return selectedContractProgram;
	}
	/**
	 * @return the selectedLanguagesIds
	 */
	public String[] getSelectedLanguagesIds() {
		return selectedLanguagesIds;
	}
	/**
	 * @return the selectedPrgmLocIds
	 */
	public String getSelectedPrgmLocIds() {
		return selectedPrgmLocIds;
	}
	/**
	 * @return Returns the selectedReferralTypesList.
	 */
	public List getSelectedReferralTypesList() {
		return selectedReferralTypesList;
	}
	/**
	 * @return Returns the selectedRegionIds.
	 */
	public String[] getSelectedRegionIds() {
		return selectedRegionIds;
	}
	/**
	 * @return the selectedServiceProviderProgLocList
	 */
	public List getSelectedServiceProviderProgLocList() {
		return selectedServiceProviderProgLocList;
	}
	/**
	 * @return the selectedSexSpecificId
	 */
	public String getSelectedSexSpecificId() {
		return selectedSexSpecificId;
	}
	/**
	 * @return the selectedSPIds
	 */
	public String[] getSelectedSPIds() {
		return selectedSPIds;
	}
	/**
	 * @return the selectedSPIdsString
	 */
	public String getSelectedSPIdsString() {
		return selectedSPIdsString;
	}
	/**
	 * @return Returns the selectedSPList.
	 */
	public List getSelectedSPList() {
		return selectedSPList;
	}
	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	/**
	 * @return the serviceProviderId
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}
	
	/**
	 * @return Returns the spn.
	 */
	public String getSpn() {
		return spn;
	}
	
	
	
	/**
	 * @return the spPacketBeanList
	 */
	public List getSpPacketBeanList() {
		return spPacketBeanList;
	}
	/**
	 * @return the submitComments
	 */
	public String getSubmitComments() {
		return submitComments;
	}
	/**
	 * @return Returns the tracerNum.
	 */
	public String getTracerNum() {
		return tracerNum;
	}
	
	/**
	 * @return the userAgency
	 */
	public String getUserAgency() {
		return userAgency;
	}

	/**
	 * 
	 * @return
	 */
	public Date getUserEnteredScheduledDate()
	{
		Date scheduleDate = null;
		try
		{
			scheduleDate = DateUtil.stringToDate(userEnteredScheduledDateAsStr, DateUtil.DATE_FMT_1);
		}
		catch(Exception ex)
		{
			scheduleDate = null;
		}
		return scheduleDate;
	}

	/**
	 * @return the userEnteredScheduledDateAsStr
	 */
	public String getUserEnteredScheduledDateAsStr() {
		return userEnteredScheduledDateAsStr;
	}

	/**
	 * @return the userEnteredScheduledTime
	 */
	public String getUserEnteredScheduledTime() {
		return userEnteredScheduledTime;
	}

	public String getUserEnteredServiceProviderName() {
		return userEnteredServiceProviderName;
	}

	/**
	 * @return the userEnteredServiceProviderRefTypeCd
	 */
	public String getUserEnteredServiceProviderRefTypeCd() {
		return userEnteredServiceProviderRefTypeCd;
	}


	/**
	 * @return the userEnteredServiceProviderRefTypeDesc
	 */
	public String getUserEnteredServiceProviderRefTypeDesc() {
		return userEnteredServiceProviderRefTypeDesc;
	}

	/**
	 * @return the userEnteredSPFax
	 */
	public IPhoneNumber getUserEnteredSPFax() {
		return userEnteredSPFax;
	}


	/**
	 * @return the userEnteredSPPhone
	 */
	public IPhoneNumber getUserEnteredSPPhone() {
		return userEnteredSPPhone;
	}

	/**
	 * @return the autoReferral
	 */
	public boolean isAutoReferral() {
		return autoReferral;
	}

	/**
	 * @return the contractProgram
	 */
	public boolean isContractProgram() {
		return contractProgram;
	}


	/**
	 * @return the incarcerationReferral
	 */
	public boolean isIncarcerationReferral() {
		return incarcerationReferral;
	}

	/**
	 * @return the printPacketFlow
	 */
	public boolean isPrintPacketFlow() {
		return printPacketFlow;
	}

	/**
	 * @return the isProgramUnitRef
	 */
	public boolean isProgramUnitRef() {
		return isProgramUnitRef;
	}

	public boolean isRemoveExtNEntryWithOutRestrc() {
		return removeExtNEntryWithOutRestrc;
	}

	/**
	 * @return the removeExtNEntryWithRestrc
	 */
	public boolean isRemoveExtNEntryWithRestrc() {
		return removeExtNEntryWithRestrc;
	}

	/**
	 * @return the scheduleDateTimeSelected
	 */
	public boolean isScheduleDateTimeSelected() {
		return scheduleDateTimeSelected;
	}
	/**
	 * @return the sentToState
	 */
	public boolean isSentToState() {
		return sentToState;
	}
	/**
	 * @return the userEnteredServiceProvider
	 */
	public boolean isUserEnteredServiceProvider() {
		return userEnteredServiceProvider;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.ServletRequest)
	 */
	public void reset(ActionMapping mapping, javax.servlet.http.HttpServletRequest request)
    {
		String resetRefTypeCheckBoxes = request.getParameter("clearRefTypeCheckBoxes");
		if((resetRefTypeCheckBoxes != null) && (resetRefTypeCheckBoxes.trim().equalsIgnoreCase("true")))
		{
			resetCheckBoxesAvailableReferralTypesList();
		}
		
		String resetSPCheckBoxes = request.getParameter("clearSPCheckBoxes");
		if((resetSPCheckBoxes != null) && (resetSPCheckBoxes.trim().equalsIgnoreCase("true")))
		{
			 selectedSPIds = new String[0];
			 userEnteredServiceProvider = false;
		}
		
		String resetFieldCheckBoxes = request.getParameter("clearRefFormFieldCheckBoxes");
		if((resetFieldCheckBoxes != null) && (resetFieldCheckBoxes.trim().equalsIgnoreCase("true")))
		{
			resetRefFormFieldCheckBoxes();
		}
    }

	/**
	 * 
	 */
	private void resetCheckBoxesAvailableReferralTypesList()
	{
		if(availableReferralTypesList!=null && availableReferralTypesList.size()>0)
		{
			for(int loopX=0;loopX<availableReferralTypesList.size(); loopX++)
			{
				ReferralTypeBean myBean=(ReferralTypeBean)availableReferralTypesList.get(loopX);
				myBean.setSelected(false);
			}
		}
	}

	/**
	 * 
	 */
	private void resetRefFormFieldCheckBoxes()
	{
		List refFormFieldList = this.getReferralformFieldsList();
		if((refFormFieldList != null) && (refFormFieldList.size() > 0))
		{
			Iterator questItr = refFormFieldList.iterator();
			
			while(questItr.hasNext())
			{
				ReferralFormField question = (ReferralFormField) questItr.next();
				if(question.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_SINGLE_CHECKBOX))
				{
					question.setResponseId("");
				}
				else
				if(question.getUiControlType().equalsIgnoreCase(ReferralFormField.UI_CNTRL_TYPE_CHECKBOX))
				{
					question.setSelectedResponseIdsArr(null);
				}
			}
		}
	}

	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @param agencyId the agencyId to set
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	/**
	 * @param appointmentInfoBean the appointmentInfoBean to set
	 */
	public void setAppointmentInfoBean(AppointmentInfoBean appointmentInfoBean) {
		this.appointmentInfoBean = appointmentInfoBean;
	}

	/**
	 * @param autoReferral the autoReferral to set
	 */
	public void setAutoReferral(boolean autoReferral) {
		this.autoReferral = autoReferral;
	}

	/**
	 * @param availableReferralTypesList The availableReferralTypesList to set.
	 */
	public void setAvailableReferralTypesList(List availableReferralTypesList) {
		this.availableReferralTypesList = availableReferralTypesList;
	}

	/**
	 * @param availableReferralGroupDescriptionList The availableReferralGroupDescriptionList to set.
	 */
	public void setAvailableReferralGroupDescriptionList(List availableReferralGroupDescriptionList) {
		this.availableReferralGroupDescriptionList = availableReferralGroupDescriptionList;
	}
	

	/**
	 * @param availableSPList The availableSPList to set.
	 */
	public void setAvailableSPList(List availableSPList) {
		this.availableSPList = availableSPList;
	}


	/**
	 * @param casenoteComments the casenoteComments to set
	 */
	public void setCasenoteComments(String casenoteComments) {
		this.casenoteComments = casenoteComments;
	}

	/**
	 * @param casesAvailableList The casesAvailableList to set.
	 */
	public void setCasesAvailableList(List casesAvailableList) {
		this.casesAvailableList = casesAvailableList;
	}

	/**
	 * @param conditionsList The conditionsList to set.
	 */
	public void setConditionsList(List conditionsList) {
		this.conditionsList = conditionsList;
	}

	/**
	 * @param confinementLengthDays The confinementLengthDays to set.
	 */
	public void setConfinementLengthDays(String confinementLengthDays) {
		this.confinementLengthDays = confinementLengthDays;
	}

	/**
	 * @param confinementLengthMonths The confinementLengthMonths to set.
	 */
	public void setConfinementLengthMonths(String confinementLengthMonths) {
		this.confinementLengthMonths = confinementLengthMonths;
	}

	/**
	 * @param confinementLengthYears The confinementLengthYears to set.
	 */
	public void setConfinementLengthYears(String confinementLengthYears) {
		this.confinementLengthYears = confinementLengthYears;
	}

	/**
	 * @param contractProgram the contractProgram to set
	 */
	public void setContractProgram(boolean contractProgram) {
		this.contractProgram = contractProgram;
	}

	/**
	 * @param criminalCaseId the criminalCaseId to set
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}


	/**
	 * @param exitComments the exitComments to set
	 */
	public void setExitComments(String exitComments) {
		this.exitComments = exitComments;
	}

	/**
	 * @param filteredSPList The filteredSPList to set.
	 */
	public void setFilteredSPList(List filteredSPList) {
		this.filteredSPList = filteredSPList;
	}

	/**
	 * @param incarcerationReferral the incarcerationReferral to set
	 */
	public void setIncarcerationReferral(boolean incarcerationReferral) {
		this.incarcerationReferral = incarcerationReferral;
	}

	/**
	 * @param oldReferralTypeCd the oldReferralTypeCd to set
	 */
	public void setOldReferralTypeCd(String oldReferralTypeCd) {
		this.oldReferralTypeCd = oldReferralTypeCd;
	}

	/**
	 * @param prCasenotesList the prCasenotesList to set
	 */
	public void setPrCasenotesList(List prCasenotesList) {
		this.prCasenotesList = prCasenotesList;
	}

	/**
	 * @param printPacketFlow the printPacketFlow to set
	 */
	public void setPrintPacketFlow(boolean printPacketFlow) {
		this.printPacketFlow = printPacketFlow;
	}


	/**
	 * @param progLocId the progLocId to set
	 */
	public void setProgLocId(String progLocId) {
		this.progLocId = progLocId;
	}


	/**
	 * @param programBeginDate The programBeginDate to set.
	 */
	public void setProgramBeginDate(Date programBeginDate)
	{
		this.programBeginDateAsStr = "";
		try
		{
			this.programBeginDateAsStr = DateUtil.dateToString(programBeginDate, DateUtil.DATE_FMT_1);
		}
		catch(Exception ex)
		{
			this.programBeginDateAsStr = "";
		}
	}


	/**
	 * @param programBeginDateAsStr The programBeginDateAsStr to set.
	 */
	public void setProgramBeginDateAsStr(String programBeginDateAsStr) {
		this.programBeginDateAsStr = programBeginDateAsStr;
	}

	/**
	 * @param programEndDate The programEndDate to set.
	 */
	public void setProgramEndDate(Date programEndDate) 
	{
		this.programEndDateAsStr = "";
		try
		{
			this.programEndDateAsStr = DateUtil.dateToString(programEndDate, DateUtil.DATE_FMT_1);
		}
		catch(Exception ex)
		{
			this.programEndDateAsStr = "";
		}
	}

	/**
	 * @param programEndDateAsStr The programEndDateAsStr to set.
	 */
	public void setProgramEndDateAsStr(String programEndDateAsStr) {
		this.programEndDateAsStr = programEndDateAsStr;
	}

	/**
	 * @param programIssueTaskText the programIssueTaskText to set
	 */
	public void setProgramIssueTaskText(String programIssueTaskText) {
		this.programIssueTaskText = programIssueTaskText;
	}

	/**
	 * @param isProgramUnitRef the isProgramUnitRef to set
	 */
	public void setProgramUnitRef(boolean isProgramUnitRef) {
		this.isProgramUnitRef = isProgramUnitRef;
	}


	/**
	 * @param progRefId The progRefId to set.
	 */
	public void setProgRefId(String progRefId) {
		this.progRefId = progRefId;
	}


	/**
	 * @param progRefIdsToDeleteList the progRefIdsToDeleteList to set
	 */
	public void setProgRefIdsToDeleteList(Collection progRefIdsToDeleteList) {
		this.progRefIdsToDeleteList = progRefIdsToDeleteList;
	}

	/**
	 * @param progressedReferralTypesList the progressedReferralTypesList to set
	 */
	public void setProgressedReferralTypesList(List progressedReferralTypesList) {
		this.progressedReferralTypesList = progressedReferralTypesList;
	}

	/**
	 * @param reasonForDischargeDesc The reasonForDischargeDesc to set.
	 */
	public void setReasonForDischargeDesc(String reasonForDischargeDesc) {
		this.reasonForDischargeDesc = reasonForDischargeDesc;
	}

	/**
	 * @param reasonForDischargeId The reasonForDischargeId to set.
	 */
	public void setReasonForDischargeId(String reasonForDischargeId) 
	{
		this.reasonForDischargeId = reasonForDischargeId;
		
		reasonForDischargeDesc = "";
		if(reasonForDischargeId!=null && !(reasonForDischargeId.trim().equalsIgnoreCase("")))
		{
			reasonForDischargeDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JIMS2_DISCHARGE_REASON, reasonForDischargeId);
		}
	}
	/**
	 * @param reasonForPlacementDesc The reasonForPlacementDesc to set.
	 */
	public void setReasonForPlacementDesc(String reasonForPlacementDesc) {
		this.reasonForPlacementDesc = reasonForPlacementDesc;
	}
	/**
	 * @param reasonForPlacementId The reasonForPlacementId to set.
	 */
	public void setReasonForPlacementId(String reasonForPlacementId)
	{
		this.reasonForPlacementId = reasonForPlacementId;
		
		reasonForPlacementDesc = "";
		if(reasonForPlacementId!=null && !(reasonForPlacementId.trim().equalsIgnoreCase("")))
		{
			reasonForPlacementDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.REASON_FOR_PLACEMENT, reasonForPlacementId);
		}
	}

	/**
	 * 
	 * @param referralDate
	 */
	public void setReferralDate(Date referralDate)
	{
		this.referralDateAsStr = "";
		try
		{
			this.referralDateAsStr = DateUtil.dateToString(referralDate, DateUtil.DATE_FMT_1);
		}
		catch(Exception ex)
		{
			this.referralDateAsStr = "";
		}
	}

	/**
	 * @param referralDateAsStr the referralDateAsStr to set
	 */
	public void setReferralDateAsStr(String referralDateAsStr) {
		this.referralDateAsStr = referralDateAsStr;
	}
	/**
	 * @param referralformFieldsList the referralformFieldsList to set
	 */
	public void setReferralformFieldsList(List referralformFieldsList) {
		this.referralformFieldsList = referralformFieldsList;
	}
	/**
	 * @param referralFormId the referralFormId to set
	 */
	public void setReferralFormId(String referralFormId) {
		this.referralFormId = referralFormId;
	}
	/**
	 * @param referralFormName the referralFormName to set
	 */
	public void setReferralFormName(String referralFormName) {
		this.referralFormName = referralFormName;
	}
	/**
	 * @param referralFormsBeanList the referralFormsBeanList to set
	 */
	public void setReferralFormsBeanList(List referralFormsBeanList) {
		this.referralFormsBeanList = referralFormsBeanList;
	}


	/**
	 * @param referralProgramLocBean the referralProgramLocBean to set
	 */
	public void setReferralProgramLocBean(
			CSCServiceProviderProgLocBean referralProgramLocBean) {
		this.referralProgramLocBean = referralProgramLocBean;
	}
	/**
	 * @param referralStatusCd the referralStatusCd to set
	 */
	public void setReferralStatusCd(String referralStatusCd) {
		this.referralStatusCd = referralStatusCd;
	}
	/**
	 * @param referralTypeCdNDetailsMap the referralTypeCdNDetailsMap to set
	 */
	public void setReferralTypeCdNDetailsMap(Map referralTypeCdNDetailsMap) {
		this.referralTypeCdNDetailsMap = referralTypeCdNDetailsMap;
	}

	/**
	 * @param referralTypeCdNPgmRefIdMap the referralTypeCdNPgmRefIdMap to set
	 */
	public void setReferralTypeCdNPgmRefIdMap(Hashtable referralTypeCdNPgmRefIdMap) {
		this.referralTypeCdNPgmRefIdMap = referralTypeCdNPgmRefIdMap;
	}

	public void setReferralTypeCode(String referralTypeCode) {
		this.referralTypeCode = referralTypeCode;
	}

	/**
	 * @param referralTypeDesc the referralTypeDesc to set
	 */
	public void setReferralTypeDesc(String referralTypeDesc) {
		this.referralTypeDesc = referralTypeDesc;
	}

	/**
	 * @param referralTypesList the referralTypesList to set
	 */
	public void setReferralTypesList(List referralTypesList) {
		this.referralTypesList = referralTypesList;
	}

	public void setRemoveExtNEntryWithOutRestrc(boolean removeExtNEntryWithOutRestrc) {
		this.removeExtNEntryWithOutRestrc = removeExtNEntryWithOutRestrc;
	}

	/**
	 * @param removeExtNEntryWithRestrc the removeExtNEntryWithRestrc to set
	 */
	public void setRemoveExtNEntryWithRestrc(boolean removeExtNEntryWithRestrc) {
		this.removeExtNEntryWithRestrc = removeExtNEntryWithRestrc;
	}

	/**
	 * @param scheduleDateTimeSelected the scheduleDateTimeSelected to set
	 */
	public void setScheduleDateTimeSelected(boolean scheduleDateTimeSelected) {
		this.scheduleDateTimeSelected = scheduleDateTimeSelected;
	}

	/**
	 * @param scheduledDateAsStr the scheduledDateAsStr to set
	 */
	public void setScheduledDateAsStr(String scheduledDateAsStr) {
		this.scheduledDateAsStr = scheduledDateAsStr;
	}

	/**
	 * @param scheduledTime the scheduledTime to set
	 */
	public void setScheduledTime(String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	/**
	 * @param secondaryAction The secondaryAction to set.
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}

	/**
	 * @param selectedContractProgram the selectedContractProgram to set
	 */
	public void setSelectedContractProgram(String selectedContractProgram) {
		this.selectedContractProgram = selectedContractProgram;
	}

	/**
	 * @param selectedLanguagesIds the selectedLanguagesIds to set
	 */
	public void setSelectedLanguagesIds(String[] selectedLanguagesIds) {
		this.selectedLanguagesIds = selectedLanguagesIds;
	}

	/**
	 * @param selectedPrgmLocIds the selectedPrgmLocIds to set
	 */
	public void setSelectedPrgmLocIds(String selectedPrgmLocIds) {
		this.selectedPrgmLocIds = selectedPrgmLocIds;
	}
	/**
	 * @param selectedReferralTypesList The selectedReferralTypesList to set.
	 */
	public void setSelectedReferralTypesList(List selectedReferralTypesList) {
		this.selectedReferralTypesList = selectedReferralTypesList;
	}
	/**
	 * @param selectedRegionIds The selectedRegionIds to set.
	 */
	public void setSelectedRegionIds(String[] selectedRegionIds) {
		this.selectedRegionIds = selectedRegionIds;
	}
	/**
	 * @param selectedServiceProviderProgLocList the selectedServiceProviderProgLocList to set
	 */
	public void setSelectedServiceProviderProgLocList(
			List selectedServiceProviderProgLocList) {
		this.selectedServiceProviderProgLocList = selectedServiceProviderProgLocList;
	}
	/**
	 * @param selectedSexSpecificId the selectedSexSpecificId to set
	 */
	public void setSelectedSexSpecificId(String selectedSexSpecificId) {
		this.selectedSexSpecificId = selectedSexSpecificId;
	}
	/**
	 * @param selectedSPIds the selectedSPIds to set
	 */
	public void setSelectedSPIds(String[] selectedSPIds) {
		this.selectedSPIds = selectedSPIds;
	}
	/**
	 * @param selectedSPIdsString the selectedSPIdsString to set
	 */
	public void setSelectedSPIdsString(String selectedSPIdsString) {
		this.selectedSPIdsString = selectedSPIdsString;
	}
	/**
	 * @param selectedSPList The selectedSPList to set.
	 */
	public void setSelectedSPList(List selectedSPList) {
		this.selectedSPList = selectedSPList;
	}
	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	/**
	 * @param sentToState the sentToState to set
	 */
	public void setSentToState(boolean sentToState) {
		this.sentToState = sentToState;
	}
	/**
	 * @param serviceProviderId the serviceProviderId to set
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	/**
	 * @param spn The spn to set.
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}
	/**
	 * @param spPacketBeanList the spPacketBeanList to set
	 */
	public void setSpPacketBeanList(List spPacketBeanList) {
		this.spPacketBeanList = spPacketBeanList;
	}
	/**
	 * @param submitComments the submitComments to set
	 */
	public void setSubmitComments(String submitComments) {
		this.submitComments = submitComments;
	}
	/**
	 * @param tracerNum The tracerNum to set.
	 */
	public void setTracerNum(String tracerNum) {
		this.tracerNum = tracerNum;
	}
	
	/**
	 * @param userAgency the userAgency to set
	 */
	public void setUserAgency(String userAgency) {
		this.userAgency = userAgency;
	}

	/**
	 * @param userEnteredScheduledDateAsStr the userEnteredScheduledDateAsStr to set
	 */
	public void setUserEnteredScheduledDateAsStr(
			String userEnteredScheduledDateAsStr) {
		this.userEnteredScheduledDateAsStr = userEnteredScheduledDateAsStr;
	}
	/**
	 * @param userEnteredScheduledTime the userEnteredScheduledTime to set
	 */
	public void setUserEnteredScheduledTime(String userEnteredScheduledTime) {
		this.userEnteredScheduledTime = userEnteredScheduledTime;
	}
	/**
	 * @param userEnteredServiceProvider the userEnteredServiceProvider to set
	 */
	public void setUserEnteredServiceProvider(boolean userEnteredServiceProvider) {
		this.userEnteredServiceProvider = userEnteredServiceProvider;
	}
	public void setUserEnteredServiceProviderName(String userEnteredServiceProviderName) {
		this.userEnteredServiceProviderName = userEnteredServiceProviderName;
	}
	/**
	 * @param userEnteredServiceProviderRefTypeCd the userEnteredServiceProviderRefTypeCd to set
	 */
	public void setUserEnteredServiceProviderRefTypeCd(String userEnteredServiceProviderRefTypeCd)
	{
		this.userEnteredServiceProviderRefTypeCd = userEnteredServiceProviderRefTypeCd;
		
		this.userEnteredServiceProviderRefTypeDesc = "";
		if(userEnteredServiceProviderRefTypeCd!=null && !(userEnteredServiceProviderRefTypeCd.trim().equals("")))
		{
			RefTypeCodeNRefTypeNumNRefIdValue refTypeDetailsObj = (RefTypeCodeNRefTypeNumNRefIdValue)referralTypeCdNDetailsMap.get(userEnteredServiceProviderRefTypeCd);
			this.userEnteredServiceProviderRefTypeDesc = refTypeDetailsObj.getRefTypeDesc();
		}
	}
	/**
	 * @param userEnteredServiceProviderRefTypeDesc the userEnteredServiceProviderRefTypeDesc to set
	 */
	public void setUserEnteredServiceProviderRefTypeDesc(
			String userEnteredServiceProviderRefTypeDesc) {
		this.userEnteredServiceProviderRefTypeDesc = userEnteredServiceProviderRefTypeDesc;
	}
	/**
	 * @param userEnteredSPFax the userEnteredSPFax to set
	 */
	public void setUserEnteredSPFax(IPhoneNumber userEnteredSPFax) {
		this.userEnteredSPFax = userEnteredSPFax;
	}
	/**
	 * @param userEnteredSPPhone the userEnteredSPPhone to set
	 */
	public void setUserEnteredSPPhone(IPhoneNumber userEnteredSPPhone) {
		this.userEnteredSPPhone = userEnteredSPPhone;
	}
	
}// END CLASS
