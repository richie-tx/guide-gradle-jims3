package ui.juvenilecase.districtCourtHearings.action;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.officer.GetOfficerProfilesByAttributeEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.referral.JJSOffense;

import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

/**
 * 
 * @author sthyagarajan
 *
 */
public class HandleJuvenileDistrictCourtReferralSummaryAction extends JIMSBaseAction
{
    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.petitionUpdateBtn", "goToPetitionUpdate");
	keyMap.put("button.initialSettingBtn", "goToInitialSetting");
	keyMap.put("button.courtMainMenu","courtMainMenu");
	keyMap.put("button.go", "referralInquiry");
    }

    /**
     * @roseuid 467FB5C80014
     */
    public HandleJuvenileDistrictCourtReferralSummaryAction()
    {

    }

    /**
     * refresh
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm,HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	 return aMapping.findForward(UIConstants.COURT_MAIN_MENU);
    }
    
    /**
     * returnSelect
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward goToInitialSetting(ActionMapping aMapping, ActionForm aForm,HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	String referralNum = courtHearingForm.getReferralRec();
	Collection<JuvenileProfileReferralListResponseEvent> referralList = courtHearingForm.getReferralList();
	Iterator<JuvenileProfileReferralListResponseEvent> referralItr = referralList.iterator();
	while (referralItr.hasNext()){
		JuvenileProfileReferralListResponseEvent referral = referralItr.next();
		if(referralNum.equals(referral.getReferralNumber())){
			courtHearingForm.setReferralNumber(referralNum);
			break;
		}
	}
	return aMapping.findForward("initialSetting");
	
    }
    
    /**
     * back
     */
    public ActionForward goToPetitionUpdate(ActionMapping aMapping, ActionForm aForm,HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
    	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
    	String referralNum = courtHearingForm.getReferralRec();
    	Collection<JuvenileProfileReferralListResponseEvent> referralList = courtHearingForm.getReferralList();
    	Iterator<JuvenileProfileReferralListResponseEvent> referralItr = referralList.iterator();
    	while (referralItr.hasNext()){
    		JuvenileProfileReferralListResponseEvent referral = referralItr.next();
    		if(referralNum.equals(referral.getReferralNumber())){
    			courtHearingForm.setReferralNumber(referralNum);
    			break;
    		}
    	}
    courtHearingForm.setPrevAction("FromReferralInquiry");
	return aMapping.findForward("petitionUpdate");
    }
    
    /**
     * cancel
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	String forwardStr = UIConstants.CANCEL;
	return aMapping.findForward(forwardStr);
    }
    
    /** courtMainMenu
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward courtMainMenu(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
    {
 	   CourtHearingForm form = (CourtHearingForm)aForm;
 	   form.reset();
 	   return aMapping.findForward(UIConstants.COURT_MAIN_MENU); 
    }
    
    /**
     * referralInquiry
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward referralInquiry(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.clearForm();
	courtHearingForm.clear();

	String juvNum = aRequest.getParameter("juvnum");

	if (juvNum != null && !"".equals(juvNum))
	{

	    courtHearingForm.setJuvenileNumber(juvNum);
	    aRequest.removeAttribute("juvnum");
	}

	// 	Get Juvenile Profile Information
	List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvNum);
	GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
	reqProfileMain.setJuvenileNum(juvNum);
	CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
	JuvenileProfileDetailResponseEvent juvProfile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
	if (juvProfile == null)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile  Record Not Found. Please Enter A New Juv No"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	else
	{
	    Name name = new Name(juvProfile.getFirstName(), juvProfile.getMiddleName(), juvProfile.getLastName(), juvProfile.getNameSuffix());
	    courtHearingForm.setJuvenileName(name.getFormattedName());
	    
	    JuvenileProfileDetailResponseEvent respEvent = new JuvenileProfileDetailResponseEvent();
	    respEvent.setPreferredFirstName(juvProfile.getPreferredFirstName() );
	    courtHearingForm.setProfileDetail(respEvent);
	}
	courtHearingForm.setProfileDetail(juvProfile);

	// Get Juvenile Facility/Detention Information
	JuvenileFacilityHeaderResponseEvent facilityHeaderResp = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(juvNum);
	if (facilityHeaderResp != null)
	{
	    //pull status from jjs header.Bug fix #57130
	    courtHearingForm.setFacilityHeader(facilityHeaderResp);
	    courtHearingForm.setFacilityStatus(facilityHeaderResp.getFacilityStatus());
	    courtHearingForm.setFacilityStatusDesc(JuvenileFacilityHelper.getCodeTableDescription(PDCodeTableConstants.FACILITY_STATUS, facilityHeaderResp.getFacilityStatus()));

	    //pull detention facility from jjs detention.Bug fix #57130 
	    JJSFacility facility = JuvenileFacilityHelper.getCurrentFacilityRecord(juvNum);
	    courtHearingForm.setDetainedFacility(facility.getDetainedFacility());
	    courtHearingForm.setDetainedFacilityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, facility.getDetainedFacility()));
	}
	Collection<JuvenileProfileReferralListResponseEvent> referralList = JuvenileFacilityHelper.getJuvReferralDetails(juvNum);
	Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referralList.iterator();
	while (referralIterator.hasNext())
	{	    
	    JuvenileProfileReferralListResponseEvent referral = referralIterator.next();
	    
	    Collection<JJSOffense> offenses = referral.getOffenses();
	    if (offenses != null)
	    {
		Iterator<JJSOffense> offenseItr = offenses.iterator();
		while (offenseItr.hasNext())
		{
		    JJSOffense offense = offenseItr.next();
		    
		    referral.setOffenseCategory(offense.getOffenseCategory());
		    
		    if (offense.getSequenceNum().equalsIgnoreCase("1"))
		    {
			referral.setOffense(offense.getOffenseCodeId());
			referral.setOffenseDesc(offense.getOffenseDescription());
			if ( "TRNDSP".equals(offense.getOffenseCodeId()) 
				   || "TRNSIN".equals(offense.getOffenseCodeId())
				   || "REGION".equals(offense.getOffenseCodeId())
			           || "ISCOIN".equals(offense.getOffenseCodeId())
			       ){
			       for ( JuvenileCasefileTransferredOffenseResponseEvent transferredOffense : transferredOffenses ) {
				   if ( referral.getReferralNumber().equals(transferredOffense.getReferralNum()) ) {
				       referral.setOffenseDesc( transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory() );
				       referral.setPetitionAllegationDescr(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory() );
				   }
			       }
			  
			} 
			break;
		    }
		}
	    }
	    // Get and set all jpo's from referrals first so they are filled in assigned casefiles.
	     if(StringUtils.isNotEmpty(referral.getJpoId()) || (referral.getJpoId() != null )){
		 
		 GetOfficerProfilesByAttributeEvent event = (GetOfficerProfilesByAttributeEvent) 
				 EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILESBYATTRIBUTE);
		 event.setAttributeName("logonId");
		 if( !referral.getJpoId().startsWith("UV")){
		     
		     event.setAttributeValue("UV" + referral.getJpoId());
		 }else{
		     event.setAttributeValue(referral.getJpoId());//inassignjpoid		     
		 }
		 
		 List<OfficerProfileResponseEvent> officerProfileList = MessageUtil.postRequestListFilter(event, OfficerProfileResponseEvent.class);
		 if ( officerProfileList.size() > 0 )
		 {
		     OfficerProfileResponseEvent offResp = officerProfileList.get(0);
		     referral.setJpoId( offResp.getUserId());
		     String officerFullName = offResp.getLastName() + ", " + offResp.getFirstName();
		     referral.setJpo(officerFullName);
		 }
	     }
	    //for each referral get the casefiles, then find the supervision Category, Supervision Type of the most recent casefile assign /highest seq Num - confirmed with Carla 7/17/2018 [email]
	    JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(juvNum, referral.getReferralNumber());
	    Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();

	    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
		@Override
		public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
		{
		    if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
			return evt1.getOID().compareTo(evt2.getOID());//sort based on OID bug fix for 152448
		    else
			return -1;
		}
	    }));
	    Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
	    while (caseFileRefItr.hasNext())
	    {
			JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
        		OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
        		if (officerProfileResponse != null)
        		{
        		    referral.setJpoId(officerProfileResponse.getUserId());
        		    String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
        		    referral.setJpo(officerFullName);
        		}
        		if(caseFileReferral.getCaseStatusCd().equalsIgnoreCase("A"))//check active only for supervision check bug fix for 152448
                	{
                		referral.setSupervisionCategoryId(caseFileReferral.getSupervisionCat());
                		referral.setSupervisionType(caseFileReferral.getSupervisionType());
                		referral.setSupervisionTypeId(caseFileReferral.getSupervisionTypeCd());
                		referral.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, referral.getSupervisionCategoryId()));
        		}
        		break;
		
	    }
	}
	
	 Collections.sort((List<JuvenileProfileReferralListResponseEvent>) referralList, Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
		@Override
		public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
		{
		    if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
			return evt1.getReferralNumber().compareTo(evt2.getReferralNumber());//bug 160275
		    else
			return -1;
		}
	    }));
	 
	JuvenileFacilityHelper.populateTransferTo(juvNum, referralList);
	
	courtHearingForm.setReferralList(referralList);
        courtHearingForm.setSearchResultSize(referralList.size());
	String supCatDescr = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, courtHearingForm.getSupervisionCategoryId());
	courtHearingForm.setSupervisionCategory(supCatDescr);
	String formJPO = courtHearingForm.getJpoOfRecID();
	if (formJPO == "" || formJPO == null)
	{
	    JuvenileDistrictCourtHelper.jpoOfRecord(juvNum, courtHearingForm);//if we don't have the info in the profileDetail
	}
	courtHearingForm.setJuvenileNumber(juvNum);
	return aMapping.findForward(UIConstants.COURT_REFERRAL_SUMMARY);
    }

    

}
