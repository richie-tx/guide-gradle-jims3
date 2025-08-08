package ui.juvenilecase.districtCourtHearings.action;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetCourtActivityByYouthEvent;
import messaging.juvenilecase.GetDetailsbyPetitionNumEvent;
import messaging.juvenilecase.GetJJSJuvenileEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
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
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;

import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

/**
 * 
 * @author anpillai
 *
 */
public class HandleJuvenileDistrictCourtPetitionCJISSearchAction extends JIMSBaseAction
{
    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected void addButtonMapping(Map keyMap)
    {	
	keyMap.put("button.courtMainMenu","courtMainMenu");
	keyMap.put("button.go", "petCJISsearch");
	keyMap.put("button.courtActivityByYouth2", "youthCourtActivity");
    }

    /**
     * @roseuid 467FB5C80014
     */
    public HandleJuvenileDistrictCourtPetitionCJISSearchAction()
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
     * re-search
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward petCJISsearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	String juvNum = courtHearingForm.getJuvenileNumber();
	String typeCode = courtHearingForm.getTypeCode();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	// 	Get Juvenile Profile Information
	if (juvNum != null && !juvNum.isEmpty())
	{
	    if (typeCode.isEmpty())
	    {
		GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
		reqProfileMain.setJuvenileNum(juvNum);
		CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
		JuvenileProfileDetailResponseEvent juvProfile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
		if (juvProfile == null)
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile  Record Not Found. Please enter a new number"));
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
		else
		{
		    Name name = new Name(juvProfile.getFirstName(), juvProfile.getMiddleName(), juvProfile.getLastName(), juvProfile.getNameSuffix());
		    courtHearingForm.setJuvenileName(name.getFormattedName());

		    JuvenileProfileDetailResponseEvent respEvent = new JuvenileProfileDetailResponseEvent();
		    respEvent.setPreferredFirstName(juvProfile.getPreferredFirstName());
		    courtHearingForm.setProfileDetail(respEvent);
		}
		courtHearingForm.setProfileDetail(juvProfile);
		courtHearingForm.setOrigjuvNum(juvNum);
	    }
	    else
		if (typeCode.equalsIgnoreCase("pet"))
		{
		    GetDetailsbyPetitionNumEvent petitionEvt = (GetDetailsbyPetitionNumEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETDETAILSBYPETITIONNUM);
		    petitionEvt.setPetitionNum(juvNum);
		    dispatch.postEvent(petitionEvt);
		    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		    List<PetitionResponseEvent> petresEvts = MessageUtil.compositeToList(compositeResponse, PetitionResponseEvent.class);
		    PetitionResponseEvent petition = new PetitionResponseEvent();
		    if (petresEvts.size() > 0)
		    {
			petition = petresEvts.get(0);
			String juvNo = petition.getJuvenileNum();
			if (juvNo != null && !juvNo.isEmpty())
			{
			    //courtHearingForm.setJuvenileNumber(juvNum);
			    GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
			    reqProfileMain.setJuvenileNum(juvNo);
			    CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
			    JuvenileProfileDetailResponseEvent juvProfile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
			    if (juvProfile == null)
			    {
				    ActionErrors errors = new ActionErrors();
				    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile  Record Not Found. Please enter a new number"));
				    saveErrors(aRequest, errors);
				    return aMapping.findForward(UIConstants.FAILURE);
			    }
			    else
			    {
				Name name = new Name(juvProfile.getFirstName(), juvProfile.getMiddleName(), juvProfile.getLastName(), juvProfile.getNameSuffix());
				courtHearingForm.setJuvenileName(name.getFormattedName());

				JuvenileProfileDetailResponseEvent respEvent = new JuvenileProfileDetailResponseEvent();
				respEvent.setPreferredFirstName(juvProfile.getPreferredFirstName());
				courtHearingForm.setProfileDetail(respEvent);
			    }
			    courtHearingForm.setProfileDetail(juvProfile);
			    courtHearingForm.setOrigjuvNum(juvNo);
			}
		    }
		    else
		    {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Petition Number entered is not valid"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		    }

		}
		else
		    if (typeCode.equalsIgnoreCase("cjis")) //add this after  cjis search in pet table- like search
		    {
			List<PetitionResponseEvent> petresEvts = InterviewHelper.getPetitionsForCJIS(juvNum);//get ALL petitions for cjis num like
			PetitionResponseEvent petition = new PetitionResponseEvent();
			if (petresEvts.size() > 0)
			{
			    petition = petresEvts.get(0);
			    String juvNo = petition.getJuvenileNum();
			    if (juvNo != null && !juvNo.isEmpty())
			    {
				GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
				reqProfileMain.setJuvenileNum(juvNo);
				CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
				JuvenileProfileDetailResponseEvent juvProfile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
				if (juvProfile == null)
				{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile  Record Not Found. Please enter a new number"));
					    saveErrors(aRequest, errors);
					    return aMapping.findForward(UIConstants.FAILURE);
				}
				else
				{
				    Name name = new Name(juvProfile.getFirstName(), juvProfile.getMiddleName(), juvProfile.getLastName(), juvProfile.getNameSuffix());
				    courtHearingForm.setJuvenileName(name.getFormattedName());

				    JuvenileProfileDetailResponseEvent respEvent = new JuvenileProfileDetailResponseEvent();
				    respEvent.setPreferredFirstName(juvProfile.getPreferredFirstName());
				    courtHearingForm.setProfileDetail(respEvent);
				}
				courtHearingForm.setProfileDetail(juvProfile);
				courtHearingForm.setOrigjuvNum(juvNo);
			    }
			}
			else
			{
			    ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "CJIS Number entered is not valid"));
			    saveErrors(aRequest, errors);
			    return aMapping.findForward(UIConstants.FAILURE);
			}

		    }
		    else
		    {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Type Code"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		    }
	    Collection<PetitionResponseEvent> referralList = JuvenileFacilityHelper.getJuvReferralDetailsforPetCJISSearch(juvNum, typeCode);

	    Collections.sort((List<PetitionResponseEvent>) referralList, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
		@Override
		public int compare(PetitionResponseEvent evt1, PetitionResponseEvent evt2)
		{
		    if (evt1.getReferralNum() != null && evt2.getReferralNum() != null)
			return evt1.getReferralNum().compareTo(evt2.getReferralNum());//bug 160275
		    else
			return -1;
		}
	    }));

	    courtHearingForm.setPetitionList(referralList);
	    courtHearingForm.setSearchResultSize(referralList.size());
	    if (referralList.size() > 0)
	    {
		courtHearingForm.setJuvenileNumber(juvNum);
		return aMapping.findForward(UIConstants.COURT_PETCJIS_SUMMARY);
	    }
	    else
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No records found"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}
	else
	    return aMapping.findForward(UIConstants.FAILURE);
    }
    public ActionForward youthCourtActivity(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	
	String juvenileNum = aRequest.getParameter("juvnum");
	GetJJSJuvenileEvent request = (GetJJSJuvenileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSJUVENILE);
	List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvenileNum);
	 
	request.setJuvenileNum(juvenileNum);

	JJSJuvenileResponseEvent juvenileResp = (JJSJuvenileResponseEvent) MessageUtil.postRequest(request, JJSJuvenileResponseEvent.class);

	if (juvenileResp != null && juvenileResp.getJuvenileNum() != null)
	{

	    Name name = new Name(juvenileResp.getFirstName(), juvenileResp.getMiddleName(), juvenileResp.getLastName());

	    courtHearingForm.setJuvenileName(name.getFormattedName());
	    courtHearingForm.setRaceId(  nvl(juvenileResp.getOriginalRaceId(), juvenileResp.getRaceId() ) );
	    courtHearingForm.setSexId(juvenileResp.getSexId());
	    courtHearingForm.setRectype(juvenileResp.getRectype());

	    if (juvenileResp.getBirthDate() != null)
	    { // Get age based on
	      // year
		int age = UIUtil.getAgeInYears(juvenileResp.getBirthDate());
		if (age > 0)
		{
		    courtHearingForm.setJuvAge(String.valueOf(age));
		}
		else
		{
		    courtHearingForm.setJuvAge(UIConstants.EMPTY_STRING);
		}
	    }

	    GetCourtActivityByYouthEvent courtEvt = (GetCourtActivityByYouthEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCOURTACTIVITYBYYOUTH);
	    courtEvt.setJuvenileNum(juvenileNum);

	    List<DocketEventResponseEvent> docketRespList = MessageUtil.postRequestListFilter(courtEvt, DocketEventResponseEvent.class);

	    Collections.sort((List<DocketEventResponseEvent>) docketRespList, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
		{
		    return new CompareToBuilder().append(Integer.valueOf(evt1.getChainNumber()), Integer.valueOf(evt2.getChainNumber())).append(Integer.valueOf(evt1.getSeqNum()), Integer.valueOf(evt2.getSeqNum())).toComparison();
		}
	    }));
	    
	   
	    if ( docketRespList != null ) {
		for ( DocketEventResponseEvent resp : docketRespList ) {
		   
		    if ( "TRNDSP".equals(resp.getPetitionAllegation()) 
				   || "TRNSIN".equals(resp.getPetitionAllegation())
				   || "REGION".equals(resp.getPetitionAllegation())
			           || "ISCOIN".equals(resp.getPetitionAllegation())
			       ){
			
			       for ( JuvenileCasefileTransferredOffenseResponseEvent transferredOffense : transferredOffenses ) {
				   if ( resp.getReferralNum().equals(transferredOffense.getReferralNum()) ) {
				       resp.setPetitionAllegationDesc(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory() );
				   }
			       }
			  
		    }
		}
	    }

	    JuvenileProfileDetailResponseEvent respEvent = new JuvenileProfileDetailResponseEvent();
	    respEvent.setPreferredFirstName(juvenileResp.getPreferredFirstName() );
	    courtHearingForm.setProfileDetail(respEvent);
	    courtHearingForm.setDktSearchResults(docketRespList);
	    courtHearingForm.setSearchResultSize(docketRespList.size());
	    
	    //User story 156683
	    JuvenileFacilityHeaderResponseEvent facilityHeaderResp = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(juvenileNum);
	    if (facilityHeaderResp != null ) {
		courtHearingForm.setFacilityHeader(facilityHeaderResp);
		courtHearingForm.setFacilityStatus(facilityHeaderResp.getFacilityStatus());
	    }
	}
	else
	{

	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No Records Found For Juvenile Number"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	
	courtHearingForm.setJuvenileNumber(juvenileNum);
	return aMapping.findForward("youthCourtActivity");
    }
    private String  nvl( String value1, String value2 ) {
	return (value1 != null && value1.length() >0 ) ? value1: value2;
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
    
    
}
