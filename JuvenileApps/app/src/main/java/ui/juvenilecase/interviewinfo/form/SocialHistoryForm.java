/*
 * Created on Jun 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.interviewinfo.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import messaging.codetable.criminal.reply.JuvenileCourtResponseEvent;
import messaging.interviewinfo.to.ExcludedTO;
import messaging.interviewinfo.to.FamilyInformationTO;
import messaging.interviewinfo.to.SocialHistoryReportDataTO;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import ui.common.CodeHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.programreferral.UIProgramReferralBean;

/**
 * @author awidjaja To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SocialHistoryForm extends ActionForm
{
	private static Collection					emptyColl									= new ArrayList();

	// List of drop downs
	private static Collection					courtList									= emptyColl;
	private static Collection					notificationMethodList		= emptyColl;
	private static Collection					notifiedPersonList				= emptyColl;
	public SocialHistoryReportDataTO	socialHistoryData;
	private String										currentTab								= "";
	private String										casefileId								= "";
	private String										juvenileNum								= "";
	private int												selectedJPOData						= 0;
	private Collection								detentionReasonsList			= emptyColl;
	private String										detentionReasonIndex			= "";
	private String										notifiedPersonId					= "";
	private String										assignedCourtId						= "";
	private String										notificationMethodId			= "";
	// for taking user's input and converting it to date object
	private String										personNotificationDateStr	= "";
	private Collection								jpCourtReferrals					= new ArrayList();	// JPCourtReferralResponseEvent
	private Collection								juvenileCourts						= new ArrayList();	// JuvenileCourtsResponseEvents

	private String										selJuvNumber							= ""; // to indicate which name the user want to look in detail
	private String										selCourtName							= "";
	private String										selJudgeName							= "";

	private String										attorneyNotificationDateStr;
	private Collection								activities;
	private List											activeReferralList;
	private List											programReferralList;
	private UIProgramReferralBean			programReferral;
	private List											closedReferralList;
	//added for US 11078, Task 47899
	private Collection facilityHistory;
	private String errorMessage = "";
	
	
	public Collection getFacilityHistory() {
	    return facilityHistory;
	}

	public void setFacilityHistory(Collection facilityHistory) {
	    this.facilityHistory = facilityHistory;
	}

	public List getActiveReferralList()
	{
		return activeReferralList;
	}

	public void setActiveReferralList(List activeReferralList)
	{
		this.activeReferralList = activeReferralList;
	}

	public List getClosedReferralList()
	{
		return closedReferralList;
	}

	public void setClosedReferralList(List closedReferralList)
	{
		this.closedReferralList = closedReferralList;
	}

	/**
	 * @return Returns the juvenileCourts.
	 */
	public Collection getJuvenileCourts()
	{
		return juvenileCourts;
	}

	/**
	 * @param juvenileCourts
	 *          The juvenileCourts to set.
	 */
	public void setJuvenileCourts(Collection juvenileCourts)
	{
		this.juvenileCourts = juvenileCourts;
	}

	public SocialHistoryForm()
	{
		programReferralList = new ArrayList();
		programReferral = null;

		if( detentionReasonsList.isEmpty() )
		{
			detentionReasonsList = new ArrayList();
			detentionReasonsList.add("Likely to abscond or be removed from the jurisdiction of the court.");
			detentionReasonsList.add("Suitable supervision, care, or protection is not being provided by a parent, guardian, custodian, or other person.");
			detentionReasonsList.add("Has no parent, guardian, custodian, or other person able to return youth to the court when required.");
			detentionReasonsList.add("May be dangerous to himself or may threaten the safety of the public if released.");
			detentionReasonsList.add("Has previously been found to be a delinquent child or has previously been convicted of a penal offense punishable by a term in jail or prison and is likely to commit an offense if released.");
		}
	}

	// Will be called at every submit
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
	{
		String resetTabName = (String)aRequest.getParameter("resetTabName");
		String pagerOffset = (String)aRequest.getParameter("pager.offset");

		List listToReset = null;
		List myListToReset = null;

		if( "financialHistory".equals(resetTabName) )
		{
			List familyFinancialHistory = socialHistoryData.getFamilyFinancialHistory();
			if( familyFinancialHistory != null && 
					familyFinancialHistory.size() > 0 )
			{
				for(Iterator<FamilyInformationTO> iter = familyFinancialHistory.iterator(); 
						iter.hasNext(); /*empty*/)
				{
					FamilyInformationTO to = iter.next();
					listToReset = to.getEmploymentHistory();
					// set excluded = true (means NOT included initially)
					resetList(listToReset, true, pagerOffset);
				}
			}
		}
		else
		{
			if( "presentOffense".equals(resetTabName) )
			{
				listToReset = socialHistoryData.getPresentOffenses();
				listToReset = socialHistoryData.getPresentOffensesForGeneric();
			}
			else if( "jobs".equals(resetTabName) )
			{
				listToReset = socialHistoryData.getEmploymentHistory();
			}
			else if( "juvInfo".equals(resetTabName) )
			{
				
				listToReset = socialHistoryData.getFamilyInformation();
				resetList(listToReset, true, pagerOffset);
				
				listToReset = socialHistoryData.getGangTraits();
				resetList(listToReset, pagerOffset);
				listToReset = socialHistoryData.getSubstanceAbuseTraits();
				resetList(listToReset, pagerOffset);
				listToReset = socialHistoryData.getStrengthTraits();
				resetList(listToReset, pagerOffset);
				myListToReset = socialHistoryData.getProgramReferralList();
				myResetList(myListToReset, pagerOffset);
				listToReset = socialHistoryData.getSubstanceAbuseInformation();

			}
			else if( "school".equals(resetTabName) )
			{
				listToReset = socialHistoryData.getEducationalHistory();
			}
			else if( "warrantHistory".equals(resetTabName) )
			{
				listToReset = socialHistoryData.getWarrantHistory();
			}
			else if( "supervisionRules".equals(resetTabName) )
			{
				listToReset = socialHistoryData.getCompliantSupervisionRules();
				resetList(listToReset, pagerOffset);
				listToReset = socialHistoryData.getNoncompliantSupervisionRules();
			}
			else if( "referralHistory".equals(resetTabName) )
			{
				listToReset = socialHistoryData.getJPCourtReferrals();
				resetList(listToReset, true, pagerOffset);
				listToReset = socialHistoryData.getReferralHistory();
			}

			resetList(listToReset, pagerOffset);
		}

	}

	/*
	 * 
	 */
	private List currentlyViewedList(List list, String pagerOffset)
	{
		if( pagerOffset != null && pagerOffset.length() > 0 )
		{
			int from = Integer.parseInt(pagerOffset);
			int to = Integer.parseInt(pagerOffset) + 10;
			if( to > list.size() )
				to = list.size();

			return list.subList(from, to);
		}
		return list;
	}

	/*
	 * 
	 */
	private void myResetList(List list, String pagerOffset)
	{
		myResetList(list, false, pagerOffset);

	}

	/*
	 * 
	 */
	private void myResetList(List list, boolean value, String pagerOffset)
	{
		if( list != null && !list.isEmpty() )
		{
			list = currentlyViewedList(list, pagerOffset);
			for(Iterator<ProgramReferralResponseEvent> iter = list.iterator(); 
					iter.hasNext(); /*empty*/)
			{
				ProgramReferralResponseEvent evnt = iter.next();
				evnt.setExcluded(value);

			}
		}
	}

	/*
	 * 
	 */
	private void resetList(List list, String pagerOffset)
	{
		resetList(list, false, pagerOffset);
	}

	/*
	 * 
	 */
	private void resetList(List list, boolean value, String pagerOffset)
	{
		if( list != null && !list.isEmpty() )
		{
			list = currentlyViewedList(list, pagerOffset);
			for(Iterator<ExcludedTO> iter = list.iterator(); iter.hasNext(); /*empty*/)
			{
				ExcludedTO excludedTO = iter.next();
				excludedTO.setExcluded(value);
			}
		}
	}

	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId()
	{
		return casefileId;
	}

	/**
	 * @param casefileId
	 *          The casefileId to set.
	 */
	public void setCasefileId(String casefileId)
	{
		this.casefileId = casefileId;
	}

	public void clear()
	{

	}

	/**
	 * @return Returns the courtList.
	 */
	public static Collection getCourtList()
	{
		return courtList;
	}

	/**
	 * @param courtList
	 *          The courtList to set.
	 */
	public static void setCourtList(Collection courtList)
	{
		SocialHistoryForm.courtList = courtList;
	}

	/**
	 * @return Returns the notificationMethodList.
	 */
	public static Collection getNotificationMethodList()
	{
		return notificationMethodList;
	}

	/**
	 * @param notificationMethodList
	 *          The notificationMethodList to set.
	 */
	public static void setNotificationMethodList(Collection notificationMethodList)
	{
		SocialHistoryForm.notificationMethodList = notificationMethodList;
	}

	/**
	 * @return Returns the socialHistoryData.
	 */
	public SocialHistoryReportDataTO getSocialHistoryData()
	{
		return socialHistoryData;
	}

	/**
	 * @param socialHistoryData
	 *          The socialHistoryData to set.
	 */
	public void setSocialHistoryData(SocialHistoryReportDataTO socialHistoryData)
	{
		this.socialHistoryData = socialHistoryData;
	}

	/**
	 * @return Returns the currentTab.
	 */
	public String getCurrentTab()
	{
		return currentTab;
	}

	/**
	 * @param currentTab
	 *          The currentTab to set.
	 */
	public void setCurrentTab(String currentTab)
	{
		this.currentTab = currentTab;
	}

	/**
	 * @return Returns the selectedJPOData.
	 */
	public int getSelectedJPOData()
	{
		return selectedJPOData;
	}

	/**
	 * @param selectedJPOData
	 *          The selectedJPOData to set.
	 */
	public void setSelectedJPOData(int selectedJPOData)
	{
		this.selectedJPOData = selectedJPOData;
	}

	/**
	 * @return Returns the detentionReasonsList.
	 */
	public Collection getDetentionReasonsList()
	{
		return detentionReasonsList;
	}

	/**
	 * @param detentionReasonsList
	 *          The detentionReasonsList to set.
	 */
	public void setDetentionReasonsList(Collection detentionReasonsList)
	{
		this.detentionReasonsList = detentionReasonsList;
	}

	/*
	 * 
	 */
	public void setDetentionReasonIndex(String index)
	{
		detentionReasonIndex = index;
		String detentionReasons = (String)((ArrayList)detentionReasonsList).get(Integer.parseInt(index));
		this.socialHistoryData.setDetentionReasons(detentionReasons);
	}

	/*
	 * 
	 */
	public String getDetentionReasonIndex()
	{
		return detentionReasonIndex;
	}

	/**
	 * @return Returns the notifiedPersonList.
	 */
	public Collection getNotifiedPersonList()
	{
		return notifiedPersonList;
	}

	/**
	 * @param notifiedPersonList
	 *          The notifiedPersonList to set.
	 */
	public void setNotifiedPersonList(Collection notifiedPersonList)
	{
		SocialHistoryForm.notifiedPersonList = notifiedPersonList;
	}

	/**
	 * @return Returns the notifiedPersonId.
	 */
	public String getNotifiedPersonId()
	{
		return notifiedPersonId;
	}

	/**
	 * @param notifiedPersonId
	 *          The notifiedPersonId to set.
	 */
	public void setNotifiedPersonId(String notifiedPersonId)
	{
		this.notifiedPersonId = notifiedPersonId;
		for(Iterator<JuvenileFamilyForm.MemberList> iter = notifiedPersonList.iterator(); 
				iter.hasNext(); /*empty*/)
		{
			JuvenileFamilyForm.MemberList member = iter.next();
			if( member.getMemberNumber().equals(notifiedPersonId) )
			{
				this.socialHistoryData.setNotifiedPerson(member.getMemberName().getFormattedName());
			}
		}
	}

	/**
	 * @return Returns the personNotificationDateStr.
	 */
	public String getPersonNotificationDateStr()
	{
		return personNotificationDateStr;
	}

	/**
	 * @param attorneyNotificationDateStr
	 *          The attorneyNotificationDateStr to set.
	 */
	public void setPersonNotificationDateStr(String personNotificationDateStr)
	{
		this.personNotificationDateStr = personNotificationDateStr;

		Date iDate = DateUtil.stringToDate(personNotificationDateStr, UIConstants.DATE_FMT_1);
		if( iDate != null )
		{
			this.socialHistoryData.setPersonNotificationDate(iDate);
		}
	}

	/**
	 * @return Returns the personNotificationDateStr.
	 */
	public String getAttorneyNotificationDateStr()
	{
		return attorneyNotificationDateStr;
	}

	/**
	 * @param attorneyNotificationDateStr
	 *          The attorneyNotificationDateStr to set.
	 */
	public void setAttorneyNotificationDateStr(String attorneyNotificationDateStr)
	{
		this.attorneyNotificationDateStr = attorneyNotificationDateStr;

		Date iDate = DateUtil.stringToDate(attorneyNotificationDateStr, UIConstants.DATE_FMT_1);
		if( iDate != null )
		{
			this.socialHistoryData.setAttorneyNotificationDate(iDate);
		}
	}

	/**
	 * @return Returns the jpCourtReferrals.
	 */
	public Collection getJpCourtReferrals()
	{
		return jpCourtReferrals;
	}

	/**
	 * @param jpCourtReferrals
	 *          The jpCourtReferrals to set.
	 */
	public void setJpCourtReferrals(Collection jpCourtReferrals)
	{
		this.jpCourtReferrals = jpCourtReferrals;
	}

	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param juvenileNum
	 *          The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		this.juvenileNum = juvenileNum;
	}

	public Collection getDetentionNotificationMethod()
	{
		return CodeHelper.getCodes(PDCodeTableConstants.DETENTION_NOTIFICATION_METHOD);
	}

	/**
	 * @return Returns the assignedCourtId.
	 */
	public String getAssignedCourtId()
	{
		return assignedCourtId;
	}

	/**
	 * @param assignedCourtId
	 *          The assignedCourtId to set.
	 */
	public void setAssignedCourtId(String assignedCourtId)
	{
		this.assignedCourtId = assignedCourtId;

		for(Iterator<JuvenileCourtResponseEvent> iter = juvenileCourts.iterator(); 
				iter.hasNext(); /*empty*/)
		{
			JuvenileCourtResponseEvent court = iter.next();
			if( court.getCode().equals(assignedCourtId) )
			{
				this.getSocialHistoryData().getPresentOffense().setCourtName(court.getDescription());
			}
		}

		this.getSocialHistoryData().getPresentOffense().setCourtCodeId(assignedCourtId);

	}

	/**
	 * @return Returns the notificationMethodId.
	 */
	public String getNotificationMethodId()
	{
		return notificationMethodId;
	}

	/**
	 * @param notificationMethodId
	 *          The notificationMethodId to set.
	 */
	public void setNotificationMethodId(String notificationMethodId)
	{
		this.notificationMethodId = notificationMethodId;
		String notificationMethodDescription = CodeHelper.getCodeDescription(notifiedPersonList, notificationMethodId);
		this.getSocialHistoryData().setNotifiedMethod(notificationMethodDescription);

	}

	/**
	 * @return Returns the selJuvNumber.
	 */
	public String getSelJuvNumber()
	{
		return selJuvNumber;
	}

	/**
	 * @param selJuvNumber
	 *          The selJuvNumber to set.
	 */
	public void setSelJuvNumber(String selJuvNumber)
	{
		this.selJuvNumber = selJuvNumber;
	}

	/**
	 * @return Returns the activities.
	 */
	public Collection getActivities()
	{
		return activities;
	}

	/**
	 * @param activities
	 *          The activities to set.
	 */
	public void setActivities(Collection activities)
	{
		this.activities = activities;
	}

	public List getProgramReferralList()
	{
		return programReferralList;
	}

	public void setProgramReferralList(List programReferralList)
	{
		this.programReferralList = programReferralList;
	}

	public UIProgramReferralBean getProgramReferral()
	{
		return programReferral;
	}

	public void setProgramReferral(UIProgramReferralBean programReferral)
	{
		this.programReferral = programReferral;
	}

	public String getSelCourtName()
	{
		return selCourtName;
	}

	public void setSelCourtName(String selCourtName)
	{
		this.selCourtName = selCourtName;
	}

	public String getSelJudgeName()
	{
		return selJudgeName;
	}

	public void setSelJudgeName(String selJudgeName)
	{
		this.selJudgeName = selJudgeName;
	}

	public String getErrorMessage()
	{
	    return errorMessage;
	}

	public void setErrorMessage(String errorMessage)
	{
	    this.errorMessage = errorMessage;
	}

}
