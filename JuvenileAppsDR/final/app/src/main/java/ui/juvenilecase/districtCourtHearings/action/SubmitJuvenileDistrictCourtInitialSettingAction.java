package ui.juvenilecase.districtCourtHearings.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetAllJuvenileOffenseCodesEvent;
import messaging.codetable.GetAttorneyNameAndBarNumEvent;
import messaging.codetable.GetJuvenileCourtsEvent;
import messaging.codetable.criminal.reply.JuvenileCourtDecisionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCourtResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByChainAndSeqNumEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByRefereeCourtEvent;
import messaging.districtCourtHearings.GetJJSCLAncillaryCourtByChainAndSeqNumEvent;
import messaging.districtCourtHearings.GetJJSCLAncillaryCourtByReferreeCourtEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByChainAndSeqNumEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByRefereeCourtEvent;
import messaging.districtCourtHearings.SaveJJSCLCourtEvent;
import messaging.districtCourtHearings.UpdateJJSCLCourtSettingEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileNumControlResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.SaveJJSLastAttorneyEvent;
import messaging.juvenilecase.SaveProdSupportLastAttorneyEvent;
import messaging.juvenilecase.reply.AttorneyNameAndAddressResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.juvenilewarrant.SaveJuvenilePetitionInformationEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.officer.GetOfficerProfilesByAttributeEvent;
import messaging.productionsupport.GetTransOffenseReferralsQueryEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.security.ISecurityManager;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import net.minidev.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JJSChainNumControl;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSLastAttorney;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;
import pd.security.PDSecurityHelper;
import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.SubpoenaReportBean;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author sthyagarajan
 */
public class SubmitJuvenileDistrictCourtInitialSettingAction extends JIMSBaseAction
{

    /**
     * updatePetition
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward updatePetition(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setAction("petitionUpdate");
	form.setCurrAction("FromInitialSetting");
	//check if user has feature to see disposed ones
	String feature = "JCW-PETITIONCORRECTION";
	boolean grantedFeature = false;
	try
	{
		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		if (securityManager != null)
		{
		     grantedFeature = securityManager.isAllowed(feature);	                
		}
	}
	catch (Throwable e)
	{
		// ignore any exception as this is not visible to the user        
	}
	// get Court Information
	List<DocketEventResponseEvent> crtdktRespEvts = JuvenileDistrictCourtHelper.getCourtDocket(form.getJuvenileNumber(), form.getReferralNumber());
	if (crtdktRespEvts != null && !crtdktRespEvts.isEmpty())
	{
	    Iterator<DocketEventResponseEvent> crtdktRespEvtsItr = crtdktRespEvts.iterator();
	    if (form.getChainNumber() == null)
	    {
		while (crtdktRespEvtsItr.hasNext())
		{
		    DocketEventResponseEvent crtDocResp = (DocketEventResponseEvent) crtdktRespEvtsItr.next();
		    if (crtDocResp != null)
		    {
			form.setBarNumber(crtDocResp.getBarNum());
			form.setAttorneyConnection(crtDocResp.getAttorneyConnection());
			form.setAttorneyName(crtDocResp.getAttorneyName());
			form.setCourtId(crtDocResp.getCourt());
			form.setHearingType(crtDocResp.getHearingType().trim());
			form.setCourtDate(crtDocResp.getLastCourtDate());
			form.setCourtTime(JuvenileFacilityHelper.stripColonInDate(crtDocResp.getCourtTime()));
			form.setAmendmentDate(crtDocResp.getPetitionAmendmentDate());
			form.setJuvAge(crtDocResp.getAge());
			break;
		    }
		}
	    }
	}
	else
	{
	    form.setAction("courtHearing");
	    form.setCursorPosition("courtId");
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Petition cannot be updated. Initial Setting has not been docketed"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.INITIAL_SETTING_DISPLAY);
	}

	String subpoenas[] = form.getSelectedSubpoenasToBePrinted();
	StringBuffer buf = new StringBuffer();
	if (subpoenas != null && !form.getSelectedSubpoenas().equalsIgnoreCase("noSubpoenasSelected"))
	{
	    for (int i = 0; i < subpoenas.length; i++)
	    {
		String subpoena = subpoenas[i];
		buf.append(subpoena);
		if (i != subpoenas.length - 1)
		{
		    buf.append("-");
		}
	    }
	    form.setSelectedSubpoenas(buf.toString());
	}
	else
	{
	    form.setSelectedSubpoenas("");
	}

	JuvenileProfileReferralListResponseEvent profileRefEvent = JuvenileFacilityHelper.getJuvReferralDetails(form.getJuvenileNumber(), form.getReferralNumber());
	if (profileRefEvent != null)
	{
	    /**
	     * {Bug 64304): If CourtResult and/or Court Disposition is populated
	     * for the associated referral/petition, display
	     * "CASE DISPOSED.  INFORMATION CANNOT BE MODIFIED." Proposed
	     * requirement: An additional parameter needs to be added: If
	     * CourtResult and/or Court Disposition is populated AND there is no
	     * future date (Reset Date) associated to the setting....
	     */
	    // bug fix #64231 //bug 64800
	    if (!grantedFeature&&((profileRefEvent.getCourtResult() != null && !profileRefEvent.getCourtResult().isEmpty()) || (profileRefEvent.getCourtDisposition() != null && !profileRefEvent.getCourtDisposition().isEmpty())) && (!DateUtil.stringToDate(form.getCourtDate(), "MM/dd/yyyy").after(JuvenileDistrictCourtHelper.getDateWithoutTime(DateUtil.getCurrentDate())) && !DateUtil.stringToDate(form.getCourtDate(), "MM/dd/yyyy").equals(JuvenileDistrictCourtHelper.getDateWithoutTime(DateUtil.getCurrentDate()))))
	    {		
		form.setAction(form.getPrevAction());
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Case Disposed. Information cannot be modified"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    else
	    {

		List<PetitionResponseEvent> petitionResps = InterviewHelper.getPetitionsRespForReferral(form.getJuvenileNumber(), form.getReferralNumber());
		//sort by highest seq number.
		Collections.sort((List<PetitionResponseEvent>) petitionResps, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
		    @Override
		    public int compare(PetitionResponseEvent evt1, PetitionResponseEvent evt2)
		    {
			if (evt1.getSequenceNum() != null && evt2.getSequenceNum() != null)
			    return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
			else
			    return -1;
		    }
		}));
		// get highest seq number
		if (petitionResps != null && !petitionResps.isEmpty())
		{
		    Iterator<PetitionResponseEvent> petitionIter = petitionResps.iterator();
		    if (petitionIter.hasNext())
		    {
			PetitionResponseEvent respEvent = petitionIter.next();
			if (respEvent != null)
			{
			    form.setPetitionStatus(respEvent.getPetitionStatus());
			    form.setFilingDate(DateUtil.dateToString(respEvent.getPetitionDate(), DateUtil.DATE_FMT_1));
			    form.setPetitionType(respEvent.getPetitionType());
			    form.setPetitionNumber(respEvent.getPetitionNum());
			    form.setPetitionAmendment(respEvent.getAmend());
			    form.setPetitionAllegation(respEvent.getOffenseCodeId());
			    if (form.getPetitionAllegationSev() == null)
			    {
				if (respEvent != null)
				{
				    form.setPetitionAllegationSev(respEvent.getSeverity());
				}
			    }
			    form.setPetitionSeqNum(respEvent.getSequenceNum());//add CJIS too here
			    form.setCJISNum(respEvent.getPetCJISNum());
			}
		    }
		}
	    }
	}
	return aMapping.findForward(UIConstants.PETITION_UPDATE_DISPLAY);
    }

    /**
     * hearingCorrection
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward hearingCorrection(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setAction("hearingCorrection");
	String hearingTypeCode = form.getHearingType();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	JuvenileHearingTypeCode hearingType = null;
	if (hearingTypeCode != null && !hearingTypeCode.isEmpty())
	{
	    hearingType = JuvenileHearingTypeCode.find(hearingTypeCode);
	    if (hearingType != null)
	    {
		if (hearingType.getCategoryInd() != null && hearingType.getCategoryInd().equalsIgnoreCase("A"))
		{
		    form.setAction("courtHearing");
		    form.setActionError("courtHearingError");
		    form.setCursorPosition("hearingType");
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Hearing Type cannot be category of 'A'"));
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
	    }
	}
	JuvenileProfileReferralListResponseEvent profileRefEvent = JuvenileFacilityHelper.getJuvReferralDetails(form.getJuvenileNumber(), form.getReferralNumber());
	if (profileRefEvent != null)
	{
	    if ((profileRefEvent.getCourtResult() != null && !profileRefEvent.getCourtResult().isEmpty()) || (profileRefEvent.getCourtDisposition() != null && !profileRefEvent.getCourtDisposition().isEmpty())) //bug fix #64304
	    {
		form.setAction("courtHearing");
		form.setActionError("courtHearingError");
		form.setCursorPosition("hearingType");
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Hearing cannot be changed Result/Disposition already entered"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}
	//update court record 
	//update last attorney record too 115545
	UpdateJJSCLCourtSettingEvent updateCourtEvt = (UpdateJJSCLCourtSettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLCOURTSETTING);

	GetJJSCourtEvent courtEvt = (GetJJSCourtEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSCOURT);
	courtEvt.setJuvenileNumber(form.getJuvenileNumber());
	courtEvt.setReferralNumber(form.getReferralNumber());
	dispatch.postEvent(courtEvt);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	List<DocketEventResponseEvent> crtdktRespEvts = MessageUtil.compositeToList(compositeResponse, DocketEventResponseEvent.class);
	//seq num 10
	String jjclCourtId=null;
	if (crtdktRespEvts != null && !crtdktRespEvts.isEmpty())
	{
	    Iterator<DocketEventResponseEvent> crtdktRespEvtsItr = crtdktRespEvts.iterator();
	    while (crtdktRespEvtsItr.hasNext())
	    {
		DocketEventResponseEvent crtDocResp = (DocketEventResponseEvent) crtdktRespEvtsItr.next();
		if (crtDocResp != null && crtDocResp.getSeqNum().equals("10"))
		{
		    updateCourtEvt.setDocketEventId(crtDocResp.getDocketEventId());
		    jjclCourtId=crtDocResp.getDocketEventId();
		    break;
		}
	    }
	}
	updateCourtEvt.setJuvenileNumber(form.getJuvenileNumber());
	updateCourtEvt.setReferralNumber(form.getReferralNumber());
	updateCourtEvt.setCourtId(form.getCourtId());
	updateCourtEvt.setCourtDate(DateUtil.stringToDate(form.getCourtDate(), DateUtil.DATE_FMT_1));
	updateCourtEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(form.getCourtTime()));
	if (form.getAttorneyConnection() != null)
	{
	    updateCourtEvt.setAttorneyConnection(form.getAttorneyConnection());
	}
	if (form.isBarNumberValidated())
	{
	    updateCourtEvt.setBarNumber(form.getBarNumber());
	}
	else
	{
	    form.setPrevAction(form.getAction());
	    //validate bar Number
	    if (form.getBarNumber() != null && !form.getBarNumber().isEmpty())
	    {
		GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
		request.setBarNum(form.getBarNumber());
		dispatch.postEvent(request);
		CompositeResponse compResp = (CompositeResponse) dispatch.getReply();

		Map dataMap = MessageUtil.groupByTopic(compResp);
		MessageUtil.processReturnException(dataMap);
		Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compResp, AttorneyNameAndAddressResponseEvent.class);
		ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
		if (error != null || attorneyDataList.isEmpty())
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Bar Number. Please Correct and Retry"));
		    saveErrors(aRequest, errors);
		    form.setAttorneyName("");
		    form.setAction("courtHearing");
		    form.setActionError("courtHearingError");
		    form.setCursorPosition("barNumber");
		    return aMapping.findForward(UIConstants.FAILURE);
		}
		updateCourtEvt.setBarNumber(form.getBarNumber());
	    }
	}
	updateCourtEvt.setAttorneyName(form.getAttorneyName());
	if (hearingType != null)
	{
	    updateCourtEvt.setHearingType(hearingType.getCode());
	    if (hearingType.getCategoryInd() != null && !hearingType.getCategoryInd().equalsIgnoreCase("A"))
	    {
		updateCourtEvt.setHearingCategory(hearingType.getCategoryInd());
		updateCourtEvt.setIssueFlag("I");
		updateCourtEvt.setJuryFlag(hearingType.getIssueInd());
	    }
	    if (hearingType.getIssueInd() != null && hearingType.getIssueInd().equalsIgnoreCase("B"))
	    {
		updateCourtEvt.setIssueFlag("B");
		updateCourtEvt.setOptionFlag(hearingType.getOptionInd());
	    }
	}
	updateCourtEvt.setLcDate(DateUtil.getCurrentDate());
	updateCourtEvt.setLcTime(Calendar.getInstance().getTime());
	updateCourtEvt.setLcUser(PDSecurityHelper.getLogonId());

	CompositeResponse resp = MessageUtil.postRequest(updateCourtEvt);
	DocketEventResponseEvent docket = (DocketEventResponseEvent) MessageUtil.filterComposite(resp, DocketEventResponseEvent.class);
	Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	if (errorResponse != null)
	{
	    ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
	    try
	    {
		handleFatalUnexpectedException(myEvt.getMessage());
	    }
	    catch (GeneralFeedbackMessageException e)
	    {
		e.printStackTrace();
	    }
	}
	//add last attorney table update 115545
	
	        SaveJJSLastAttorneyEvent updatelstattorneyEvent =(SaveJJSLastAttorneyEvent)
		EventFactory.getInstance( JuvenileCaseControllerServiceNames.SAVEJJSLASTATTORNEY );
		
	        updatelstattorneyEvent.setJuvenileNumber(form.getJuvenileNumber());
	        if(form.getBarNumber().isEmpty()||form.getBarNumber()==null)
		    updatelstattorneyEvent.setBarNumber(null); 
		else
		    updatelstattorneyEvent.setBarNumber(form.getBarNumber());	        	
	        updatelstattorneyEvent.setAttorneyName(form.getAttorneyName());
	        updatelstattorneyEvent.setAttorneyConnection(form.getAttorneyConnection());
	        updatelstattorneyEvent.setJjclcourtId(jjclCourtId);
	        updatelstattorneyEvent.setRecType("COURT");
		//IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
		dispatch.postEvent( updatelstattorneyEvent );
		CompositeResponse composite = (CompositeResponse)dispatch.getReply();
				
		MessageUtil.processReturnException( composite );		
	//
	if (docket.getUpdateFlag() != null && docket.getUpdateFlag().equalsIgnoreCase("true"))
	{
	    //update referral rec
	    GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
	    jjsEvt.setJuvenileNum(form.getJuvenileNumber());
	    jjsEvt.setReferralNum(form.getReferralNumber());
	    Iterator<JJSReferral> refIter = JJSReferral.findAll(jjsEvt);

	    if (refIter.hasNext())
	    {
		JJSReferral ref = refIter.next();		
		if (ref != null && ref.getCloseDate() == null)
		{
		    // any change to the court TJJD Paper Formalized
		    //bug fix for 105751
		    if (!ref.getReferralTypeInd().equalsIgnoreCase("TR"))
		    {
			//task 174215 We need an exception for JUV_OFFENSECATEGORY =AC.  The referraltypeind should always be PA for category ACs.
			    JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode",form.getOffenseDetail().getOffenseCodeId());
			    if(offCode!=null)
			    {
        			    if(offCode.getCategory()!= null && offCode.getCategory().equalsIgnoreCase("AC"))
        			    	ref.setReferralTypeInd("PA");
        			    else
        				 ref.setReferralTypeInd("PF");//U.S #80603
    			    }
			    else
				ref.setReferralTypeInd("PF");			   
			    //task 171521
			    ref.setCountyREFD("101");
		    }
		    else
		    {
			String countyId="";
    	    		GetTransOffenseReferralsQueryEvent getEvent = new GetTransOffenseReferralsQueryEvent();
    	    		getEvent.setJuvenileNumber(jjsEvt.getJuvenileNum());
    	    		getEvent.setReferralNumber(jjsEvt.getReferralNum());
                	Iterator<JJSTransferredOffenseReferral> transOffenseReferralsIter = JJSTransferredOffenseReferral.findAll(getEvent);                	    
                	while(transOffenseReferralsIter.hasNext()) 
                	{
                	    JJSTransferredOffenseReferral transOffenseReferral = (JJSTransferredOffenseReferral) transOffenseReferralsIter.next();
                	    countyId=transOffenseReferral.getFromCountyCode();
                	    ref.setCountyREFD(countyId);
                	}
                	   
    	    	    } 
		    ref.setCourtId(form.getCourtId());
		    ref.setCourtDate(DateUtil.stringToDate(form.getCourtDate(), DateUtil.DATE_FMT_1));
		    ref.setLcDate(DateUtil.getCurrentDate());
		    ref.setLcTime(Calendar.getInstance().getTime());
		    ref.setLcUser(PDSecurityHelper.getLogonId());
		    new Home().bind(ref);
		}
	    }

	    ActionMessages messageHolder = new ActionMessages();
	    //form.setActionMessage("ValidateMessage");
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.hearingInfoUpdated"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	    form.setCursorPosition("plaintiff");
	    form.setAction("subpoenas");
	    form.setHearingCorrectionFlag("");
	}
	else
	{
	    form.setAction("courtHearing"); // information not updated, as none of the fields changed.
	    form.setActionError("courtHearingError");
	}
	form.setPrevAction(form.getAction());
	return aMapping.findForward(UIConstants.INITIAL_SETTING_DISPLAY);
    }

    /**
     * printSubpoena
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	//add the update code here after checking the flag if else 

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	boolean isCourtRecFound = false;
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setAction("SubmitInitialSetting");
	
	if(JuvenileDistrictCourtHelper.isReadyForCourtAction(form)){
	    form.setIsCourtActionReady(true);
	}

	if (form.getPetitionCorrectionFlag().equals("petitionCorrection"))
	{
	    //CourtHearingForm form = (CourtHearingForm) aForm;
	    form.setPrevAction(form.getPrevAction());
	    form.setPetitionCorrectionFlag("");

	    int petitionJuvCount = 0;
	    String duplicateJuvenile = " ";
	    List<String> petReferralNumList = new ArrayList<String>();

	    JuvenileOffenseCodeResponseEvent jocEvent = JuvenileDistrictCourtHelper.validateOffenseCd(form.getPetitionAllegation());
	    if (jocEvent == null)
	    {
		form.setAction("validateOffenseCd");
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Discontinued or Invalid Allegation Code, Please Correct and Retry"));
		saveErrors(aRequest, errors);
		form.setPetitionCorrectionFlag("petitionCorrection");
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    else
	    {
		//task 175919
		if(jocEvent.getSeverity()!=null && jocEvent.getSeveritySubtype()!=null)
		{
		    if((jocEvent.getSeverity().equals("0") || jocEvent.getSeverity().equals("1"))&&!jocEvent.getSeveritySubtype().equalsIgnoreCase("T") && form.getPetitionStatus().equalsIgnoreCase("F"))
		    {
			form.setAction("validateOffenseCd");
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The entered petition allegation does not qualify for a status of Filed. Please review and submit again"));
			saveErrors(aRequest, errors);	
			form.setPetitionCorrectionFlag("petitionCorrection");
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		    else 
			    form.setValidateMsg("");
		}
		else 
		    form.setValidateMsg("");
	    }

	    List<PetitionResponseEvent> petitionResps = JuvenileDistrictCourtHelper.getPetitions(form.getPetitionNumber());//sorted in the method.

	    // check for duplicate juvenile.
	    if (petitionResps != null)
	    {
		Iterator<PetitionResponseEvent> petitionIter = petitionResps.iterator();
		while (petitionIter.hasNext())
		{
		    PetitionResponseEvent petition = petitionIter.next();

		    if (petition != null)
		    {
			if (!petition.getJuvenileNum().equals(form.getJuvenileNumber()))
			{
			    petitionJuvCount++;
			    duplicateJuvenile = petition.getJuvenileNum();
			    break;
			}
			else
			{
			    if (!petition.getReferralNum().equals(form.getReferralNumber()))
			    {
				petReferralNumList.add(petition.getReferralNum());
			    }
			}
		    }
		}
	    }
	    if (petitionJuvCount > 0)
	    {
		form.setAction("validatePetitionNum");
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "A different juvenile " + duplicateJuvenile + " already assigned to this petition number"));
		saveErrors(aRequest, errors);
		form.setPetitionCorrectionFlag("petitionCorrection");
		return aMapping.findForward(UIConstants.FAILURE);		
	    }

	    // #83982 retrieve highest seqNum for that referral and juvenile for the creation of new record.
	    List<PetitionResponseEvent> petitions = JuvenileDistrictCourtHelper.getPetitionsByJuvAndRefNum(form.getJuvenileNumber(), form.getReferralNumber());
	    if (petitions != null)
	    {
		Iterator<PetitionResponseEvent> petitionsItr = petitions.iterator();
		if (petitionsItr.hasNext())
		{
		    PetitionResponseEvent responseEvent = petitionsItr.next();
		    if (responseEvent != null)
		    {
			form.setPetitionSeqNum(responseEvent.getSequenceNum());
		    }
		}
	    }

	    //if(form.getCurrAction().equalsIgnoreCase("submitPetitionUpdate")){
	    // update petition.
	    SaveJuvenilePetitionInformationEvent event = (SaveJuvenilePetitionInformationEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.SAVEJUVENILEPETITIONINFORMATION);
	    event.setJuvenileNum(form.getJuvenileNumber());
	    event.setLcDate(DateUtil.getCurrentDate());
	    event.setLcTime(Calendar.getInstance().getTime());
	    event.setLcUser(PDSecurityHelper.getLogonId());
	    event.setOffenseCodeId(form.getPetitionAllegation());
	    //commented the null check as allegationseverity needs to be refreshed even if validateoffense button is not used bug 143101
	   /* if (form.getPetitionAllegationSev() == null || (form.getPetitionAllegationSev() != null && form.getPetitionAllegationSev().isEmpty()))
	    {*/
		if (jocEvent != null)
		{
		    form.setPetitionAllegationSev(jocEvent.getSeverity());
		}
	    //}
	    event.setSeverity(form.getPetitionAllegationSev());
	    //event.setAmend(form.getPetitionAmendment());
	    event.setPetitionDate(DateUtil.stringToDate(form.getFilingDate(), DateUtil.DATE_FMT_1));
	    event.setPetitionNum(form.getPetitionNumber());
	    event.setStatus(form.getPetitionStatus());
	    event.setType(form.getPetitionType());
	    event.setSequenceNum(form.getPetitionSeqNum());
	    event.setAmendmentDate(DateUtil.stringToDate(form.getAmendmentDate(), DateUtil.DATE_FMT_1));
	    event.setJuvenileNum(form.getJuvenileNumber());
	    event.setReferralNum(form.getReferralNumber());
	    //task 172545
	    if(form.getPetitionStatus().equalsIgnoreCase("F"))
		event.setCJISNum(form.getOffenseDetail().getCjisNum());

	    List<DocketEventResponseEvent> crtdktRespEvts = JuvenileDistrictCourtHelper.getCourtDocket(form.getJuvenileNumber(), form.getReferralNumber());
	    if (crtdktRespEvts != null && !crtdktRespEvts.isEmpty())
	    {
		Iterator<DocketEventResponseEvent> crtdktRespEvtsItr = crtdktRespEvts.iterator();
		if (crtdktRespEvtsItr.hasNext())
		{
		    DocketEventResponseEvent crtDocResp = (DocketEventResponseEvent) crtdktRespEvtsItr.next();
		    if (crtDocResp != null)
		    {
			event.setCrtChainNum(crtDocResp.getChainNumber());
			event.setCrtSeqNum(crtDocResp.getSeqNum());
		    }
		}
	    }// end of if

	    CompositeResponse compositeResponse = MessageUtil.postRequest(event);
	    PetitionResponseEvent resp = (PetitionResponseEvent) MessageUtil.filterComposite(compositeResponse, PetitionResponseEvent.class);
	    Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	    if (errorResponse != null)
	    {
		ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
		try
		{
		    handleFatalUnexpectedException(myEvt.getMessage());
		}
		catch (GeneralFeedbackMessageException e)
		{
		    e.printStackTrace();
		}
	    }

	    if (resp.getUpdateFlag() != null && resp.getUpdateFlag().equalsIgnoreCase("true"))
	    {
		// petitionInformation.
		ActionMessages messageHolder = new ActionMessages();
		//form.setActionMessage("ValidateMessage");
		messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.recordSuccesfullyUpdated"));
		aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
		saveMessages(aRequest, messageHolder);
		form.setAction("submitPetitionUpdate");
	    }
	    //}
	    form.setCurrAction("petitionChange");
	    form.setAction("courtHearing");
	    //form.setPrevAction(form.getAction());
	}
	else
	{
	    //form.setHearingCorrectionFlag("");
	    int petitionJuvCount = 0;
	    String duplicateJuvenile = " ";
	    List<String> petReferralNumList = new ArrayList<String>();
	    PetitionResponseEvent petitionResponseEvent=null;
	  //modified below to take the highest seqnum petition record bug 99668
	    /*GetJJSPetitionsEvent petitionEvent = (GetJJSPetitionsEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJJSPETITIONS);
	    petitionEvent.setJuvenileNum(form.getJuvenileNumber());
	    petitionEvent.setReferralNum(form.getReferralNumber());

	    CompositeResponse compositeResp = MessageUtil.postRequest(petitionEvent);
	    PetitionResponseEvent petitionResponseEvent = (PetitionResponseEvent) MessageUtil.filterComposite(compositeResp, PetitionResponseEvent.class);

	    Object errorResp = MessageUtil.filterComposite(compositeResp, ErrorResponseEvent.class);
	    if (errorResp != null)
	    {
		ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResp;
		try
		{
		    handleFatalUnexpectedException(myEvt.getMessage());
		}
		catch (GeneralFeedbackMessageException e)
		{
		    e.printStackTrace();
		}
	    }*/	    
	    
	    List<PetitionResponseEvent> petitionReps = InterviewHelper.getPetitionsRespForReferral(form.getJuvenileNumber(), form.getReferralNumber());
		//sort by highest seq number.
		Collections.sort((List<PetitionResponseEvent>) petitionReps, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
		    @Override
		    public int compare(PetitionResponseEvent evt1, PetitionResponseEvent evt2)
		    {
			if (evt1.getSequenceNum() != null && evt2.getSequenceNum() != null)
			    return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
			else
			    return -1;
		    }
		}));
		if (petitionReps != null && !petitionReps.isEmpty())
		{
		    Iterator<PetitionResponseEvent> petitionIter = petitionReps.iterator();
		    if (petitionIter.hasNext())
		    {
			petitionResponseEvent = petitionIter.next();			
		    }
		}
		///// 99668
		JuvenileOffenseCodeResponseEvent jocEvent = JuvenileDistrictCourtHelper.validateOffenseCd(form.getPetitionAllegation());
		    if (jocEvent == null)
		    {
			form.setAction("validateOffenseCd");
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Discontinued or Invalid Allegation Code, Please Correct and Retry"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		    else
		    {
			//task 175919
			if(jocEvent.getSeverity()!=null && jocEvent.getSeveritySubtype()!=null)
			{
			    if((jocEvent.getSeverity().equals("0") || jocEvent.getSeverity().equals("1"))&&!jocEvent.getSeveritySubtype().equalsIgnoreCase("T") && form.getPetitionStatus().equalsIgnoreCase("F"))
			    {
				form.setAction("validateOffenseCd");
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The entered petition allegation does not qualify for a status of Filed. Please review and submit again"));
				saveErrors(aRequest, errors);				
				return aMapping.findForward(UIConstants.FAILURE);
			    }
			    else 
				    form.setValidateMsg("");
			}
			else 
			    form.setValidateMsg("");
		    }
	    if (petitionResponseEvent != null) // if a petition already exists. proceed to hearing entry.
	    {

		if (petitionResponseEvent.getPetitionNum() != null && !petitionResponseEvent.getPetitionNum().isEmpty() && (form.getCourtId() == null || (form.getCourtId() != null && form.getCourtId().isEmpty())))
		{
		    form.setAction("courtHearing");
		    ActionMessages messageHolder = new ActionMessages();
		    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.readyForHearingInformation"));
		    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
		    saveMessages(aRequest, messageHolder);
		    form.setCursorPosition("courtId");
		    return aMapping.findForward(UIConstants.INITIAL_SETTING_DISPLAY); //check to avoid re submission of same petition no.
		}
		else
		{
		    String hearingTypeCode = form.getHearingType();
		    if (hearingTypeCode != null && !hearingTypeCode.isEmpty())
		    {
			JuvenileHearingTypeCode hearingType = JuvenileHearingTypeCode.find(hearingTypeCode);
			if (hearingType != null)
			{
			    if (hearingType.getCategoryInd() != null && hearingType.getCategoryInd().equalsIgnoreCase("A"))
			    {
				form.setAction("courtHearing");
				form.setActionError("courtHearingError");
				form.setCursorPosition("hearingType");
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Hearing Type cannot be category of 'A'"));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.FAILURE);
			    }
			    //proceed to saving.. Court Hearing information in JJSCL Court.
			    //If Court ID, Court Date/Time and Hearing Type are populated, determine if Court calendar record exists for the/Petition# associated to the JUV#/Ref# where CourtID and CourtDate are the same as the screen Court ID and screen Court Date�.       
			    GetJJSCourtEvent courtEvt = (GetJJSCourtEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSCOURT);
			    courtEvt.setJuvenileNumber(form.getJuvenileNumber());
			    courtEvt.setReferralNumber(form.getReferralNumber());
			    dispatch.postEvent(courtEvt);
			    CompositeResponse response = (CompositeResponse) dispatch.getReply();
			    // Court Response.
			    List<DocketEventResponseEvent> crtdktRespEvts = MessageUtil.compositeToList(response, DocketEventResponseEvent.class);
			    // sort by highest chain number
			    Collections.sort((List<DocketEventResponseEvent>) crtdktRespEvts, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
				@Override
				public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
				{
				    if (evt1.getChainNumber() != null && evt2.getChainNumber() != null)
					return Integer.valueOf(evt1.getChainNumber()).compareTo(Integer.valueOf(evt2.getChainNumber()));
				    else
					return -1;
				}
			    }));

			    if (crtdktRespEvts != null && !crtdktRespEvts.isEmpty())
			    {
				Iterator<DocketEventResponseEvent> crtdktRespEvtsItr = crtdktRespEvts.iterator();
				while (crtdktRespEvtsItr.hasNext())
				{
				    DocketEventResponseEvent crtDocResp = (DocketEventResponseEvent) crtdktRespEvtsItr.next();
				    if (crtDocResp != null && (crtDocResp.getCourt() != null && crtDocResp.getCourt().equals(form.getCourtId())) && (crtDocResp.getCourtDate() != null && crtDocResp.getCourtDate().equals(form.getCourtDate())))
				    {
					if (crtDocResp.getPetitionNumber() != null && (crtDocResp.getPetitionNumber().trim().equals(form.getPetitionNumber().trim())))
					{
					    isCourtRecFound = true;
					    form.setJuvAge(crtDocResp.getAge());
					    form.setAmendmentDate(crtDocResp.getPetitionAmendmentDate());
					    break;
					}
				    }
				}
			    }
			    else
			    { // create a new court record.
				// to do add to last attorney table too
				String jjclCourtId=null;
				SaveJJSCLCourtEvent event = (SaveJJSCLCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.SAVEJJSCLCOURT);
				if (petitionResponseEvent.getAmend() != null && !petitionResponseEvent.getAmend().isEmpty())
				{
				    event.setAmendmentDate(petitionResponseEvent.getPetitionDate());
				}
				event.setAttorneyConnection(form.getAttorneyConnection());
				event.setAttorneyName(form.getAttorneyName());
				if (form.isBarNumberValidated())
				{
				    event.setBarNumber(form.getBarNumber());
				}
				else
				{
				    form.setPrevAction(form.getAction());
				    //validate bar Number
				    if (form.getBarNumber() != null && !form.getBarNumber().isEmpty())
				    {
					GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
					request.setBarNum(form.getBarNumber());
					dispatch.postEvent(request);
					CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

					Map dataMap = MessageUtil.groupByTopic(compositeResponse);
					MessageUtil.processReturnException(dataMap);
					Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
					ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
					if (error != null || attorneyDataList.isEmpty())
					{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Bar Number. Please Correct and Retry"));
					    saveErrors(aRequest, errors);
					    form.setAttorneyName("");
					    form.setAction("courtHearing");
					    form.setActionError("courtHearingError");
					    form.setCursorPosition("barNumber");
					    return aMapping.findForward(UIConstants.FAILURE);
					}
					event.setBarNumber(form.getBarNumber());
				    }
				}
				event.setComments(null); // comes from court action
				event.setCourtDate(DateUtil.stringToDate(form.getCourtDate(), DateUtil.DATE_FMT_1));
				event.setCourtId(form.getCourtId());
				event.setCourtTime(JuvenileFacilityHelper.getDateWithColon(form.getCourtTime()));
				event.setFilingDate(petitionResponseEvent.getPetitionDate());

				event.setHearingCategory(hearingType.getCategoryInd());
				if (form.getCourtDisposition() != null)
				{ // comes from court action
				    event.setHearingDisposition(null);
				    event.setHearingResult(null);
				}
				event.setHearingType(hearingType.getCode());
				event.setIssueFlag("I");
				event.setJuryFlag(hearingType.getIssueInd());
				if (hearingType.getIssueInd() != null && hearingType.getIssueInd().equalsIgnoreCase("B"))
				{
				    event.setIssueFlag("B");
				    event.setJuryFlag(null);
				}

				event.setJuvenileNumber(form.getJuvenileNumber());
				event.setOptionFlag(hearingType.getOptionInd());

				event.setLcDate(DateUtil.getCurrentDate());
				event.setLcTime(Calendar.getInstance().getTime());
				event.setLcUser(PDSecurityHelper.getLogonId());

				event.setPetitionAllegation(petitionResponseEvent.getOffenseCodeId());
				event.setPetitionNumber(petitionResponseEvent.getPetitionNum());
				event.setPetitionStatus(petitionResponseEvent.getPetitionStatus());
				event.setPrevNotes("R/R " + DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));
				event.setReferralNumber(form.getReferralNumber());
				event.setSeqNumber("10");
				event.setResetHearingType(null); // comes from court action
				event.setTjpcSeqNumber(null);

				CompositeResponse compositeResponse = MessageUtil.postRequest(event);
				DocketEventResponseEvent docrespEvt = (DocketEventResponseEvent) MessageUtil.filterComposite(compositeResponse, DocketEventResponseEvent.class);
				if (docrespEvt != null)
				{
				    jjclCourtId=docrespEvt.getDocketEventId();
				}
				Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
				if (errorResponse != null)
				{
				    ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
				    try
				    {
					handleFatalUnexpectedException(myEvt.getMessage());
				    }
				    catch (GeneralFeedbackMessageException e)
				    {
					e.printStackTrace();
				    }
				}
				//add last attorney new record 
					SaveJJSLastAttorneyEvent updatelstattorneyEvent =(SaveJJSLastAttorneyEvent)
					EventFactory.getInstance( JuvenileCaseControllerServiceNames.SAVEJJSLASTATTORNEY );
					
				        updatelstattorneyEvent.setJuvenileNumber(form.getJuvenileNumber());
				        if(form.getBarNumber().isEmpty()||form.getBarNumber()==null)
					    updatelstattorneyEvent.setBarNumber(null); 
					else
					    updatelstattorneyEvent.setBarNumber(form.getBarNumber());				        	
				        updatelstattorneyEvent.setAttorneyName(form.getAttorneyName());				        
				        updatelstattorneyEvent.setAttorneyConnection(form.getAttorneyConnection());
				        updatelstattorneyEvent.setRecType("COURT");
				        //get docketid of the saved and insert here
				        updatelstattorneyEvent.setJjclcourtId(jjclCourtId);
					//IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
					dispatch.postEvent( updatelstattorneyEvent );
					CompositeResponse composite = (CompositeResponse)dispatch.getReply();
							
					MessageUtil.processReturnException( composite );
				//
				IHome home = new Home();
				JJSChainNumControl chainNumControl = null;
				Iterator<JJSChainNumControl> chainNumIter = JJSChainNumControl.findAll();
				// update chain num
				if (chainNumIter.hasNext())
				{
				    chainNumControl = (JJSChainNumControl) chainNumIter.next();
				    String nextChainNum = chainNumControl.getNextChainNum();
				    if (nextChainNum != null && !nextChainNum.equals(" "))
				    {
					int num = Integer.parseInt(nextChainNum);
					chainNumControl.setNextChainNum("" + (num + 1));

					chainNumControl.setLcDate(DateUtil.getCurrentDate());
					chainNumControl.setLcTime(Calendar.getInstance().getTime());
					chainNumControl.setLcUser(PDSecurityHelper.getLogonId());
					home.bind(chainNumControl);
					// added for deletion purpose
					form.setChainNumber(chainNumControl.getNextChainNum());

				    }
				}

				// update Referral
				GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
				jjsEvt.setJuvenileNum(form.getJuvenileNumber());
				jjsEvt.setReferralNum(form.getReferralNumber());
				Iterator<JJSReferral> refIter = JJSReferral.findAll(jjsEvt);

				if (refIter.hasNext())
				{
				    JJSReferral ref = refIter.next();
				    if (ref != null && ref.getCloseDate() == null)
				    {
					//bug fix for 105751
					if (!ref.getReferralTypeInd().equalsIgnoreCase("TR"))
					{
					  //task 174215 We need an exception for JUV_OFFENSECATEGORY =AC.  The referraltypeind should always be PA for category ACs.
					    JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode",form.getOffenseDetail().getOffenseCodeId());
					    if(offCode!=null)
					    {
		        			    if(offCode.getCategory()!= null && offCode.getCategory().equalsIgnoreCase("AC"))
		        			    	ref.setReferralTypeInd("PA");
		        			    else
		        				 ref.setReferralTypeInd("PF");//U.S #80603
		    			    }
					    else
						ref.setReferralTypeInd("PF");	
					   
					  //task 171521
					     ref.setCountyREFD("101");
					    
					}
					//task 171521
					else
					{
						String countyId="";
			    	    		GetTransOffenseReferralsQueryEvent getEvent = new GetTransOffenseReferralsQueryEvent();
			    	    		getEvent.setJuvenileNumber(jjsEvt.getJuvenileNum());
			    	    		getEvent.setReferralNumber(jjsEvt.getReferralNum());
			                	Iterator<JJSTransferredOffenseReferral> transOffenseReferralsIter = JJSTransferredOffenseReferral.findAll(getEvent);                	    
			                	while(transOffenseReferralsIter.hasNext()) 
			                	{
			                	    JJSTransferredOffenseReferral transOffenseReferral = (JJSTransferredOffenseReferral) transOffenseReferralsIter.next();
			                	    countyId=transOffenseReferral.getFromCountyCode();
			                	    ref.setCountyREFD(countyId);
			                	}
			                	   
			    	    	} 
					//
					
					ref.setCourtId(form.getCourtId());
					ref.setCourtDate(DateUtil.stringToDate(form.getCourtDate(), DateUtil.DATE_FMT_1));
					ref.setLcDate(DateUtil.getCurrentDate());
					ref.setLcTime(Calendar.getInstance().getTime());
					ref.setLcUser(PDSecurityHelper.getLogonId());
					ref.setSeverityType(jocEvent.getSeverityType());					
					// check tjjd before
					home.bind(ref);
				    }
				}

				// All went through, show the message and disable
				// the petitionInformation.
				ActionMessages messageHolder = new ActionMessages();
				messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.recordsAdded"));
				aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
				saveMessages(aRequest, messageHolder);
				//set it back to initial setting.
				form.setAction("subpoenas");
				form.setPrevAction(form.getAction());
				form.setActionError("");
				form.setHearingCorrectionFlag("");
				return aMapping.findForward(UIConstants.INITIAL_SETTING_DISPLAY);
			    }
			    //if(isCourtRecFound){ // update court record.
			    if (form.getHearingCorrectionFlag().equalsIgnoreCase("hearingCorrection"))
			    {
				return hearingCorrection(aMapping, aForm, aRequest, aResponse); // call hearing correction.
			    }
			    //}
			}//if hearingType Check.
			
		    }
		}
	    }
	    if (petitionResponseEvent == null)
	    {
		//check for duplicate juvenile.
		List<PetitionResponseEvent> petitionResps = JuvenileDistrictCourtHelper.getPetitions(form.getPetitionNumber());
		// check for duplicate juvenile.
		if (petitionResps != null)
		{
		    Iterator<PetitionResponseEvent> petitionIter = petitionResps.iterator();
		    while (petitionIter.hasNext())
		    {
			PetitionResponseEvent petition = petitionIter.next();
			if (petition != null)
			{
			    if (!petition.getJuvenileNum().equals(form.getJuvenileNumber()))
			    {
				petitionJuvCount++;
				duplicateJuvenile = petition.getJuvenileNum();
				break;
			    }
			    else
			    {
				if (!petition.getReferralNum().equals(form.getReferralNumber()))
				{
				    petReferralNumList.add(petition.getReferralNum());
				}
			    }
			}
		    }
		}
		if (petitionJuvCount > 0)
		{
		    form.setAction("validatePetitionNum");
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "A different juvenile " + duplicateJuvenile + " already assigned to this petition number"));
		    saveErrors(aRequest, errors);
		    form.setPrevAction(form.getAction());
		    return aMapping.findForward(UIConstants.FAILURE);
		}

		//List<JSONObject> juvenileDatesFormattedList = JuvenileDistrictCourtHelper.juvenileHolidays();

		if (form.getPetitionStatus().equals("R"))
		{
		    String finalDispEntered = "false";
		    form.setFinalDispEntered(finalDispEntered);
		    //if using the same petitionnum which was used already it goes inside the if 
		    if (!petReferralNumList.isEmpty())
		    {
			Iterator<String> petRefItr = petReferralNumList.iterator();
			while (petRefItr.hasNext())
			{
			    String petRefNum = petRefItr.next();
			    //those who dont have feature of purge/sealed will show error--research task 151750
			    //JuvenileProfileReferralListResponseEvent profileReferralResp = JuvenileFacilityHelper.getJuvReferralDetails(form.getJuvenileNumber(), petRefNum);
			    JuvenileProfileReferralListResponseEvent profileReferralResp = JuvenileFacilityHelper.getJuvReferralDetailsWithoutFilter(form.getJuvenileNumber(), petRefNum); 
			    if (profileReferralResp != null)
			    {
				JuvenileDispositionCodeResponseEvent courtDisposition = profileReferralResp.getJuvDispositionCode();
				if (courtDisposition != null)
				{
				    if (courtDisposition.getCategoryCode() != null && courtDisposition.getOptionCode() != null)
				    {
					if ((courtDisposition.getCategoryCode().equals("A") || courtDisposition.getCategoryCode().equals("B") || courtDisposition.getCategoryCode().equals("N")) && courtDisposition.getOptionCode().equals("N"))
					{
					    finalDispEntered = "true";
					    form.setFinalDispEntered(finalDispEntered);
					    form.setCourtDisposition(courtDisposition);
					    break;
					}
					else
					    if ((courtDisposition.getCategoryCode().equals("N") || courtDisposition.getCategoryCode().equals("P")) && courtDisposition.getOptionCode().equals("F"))
					    {
						finalDispEntered = "true";
						form.setFinalDispEntered(finalDispEntered);
						form.setCourtDisposition(courtDisposition);
						break;
					    }
				    }
				}
			    }
			}
		    }
		    //Reopen not valid, no final discision found.
		    if (form.getFinalDispEntered().equalsIgnoreCase("False"))
		    {
			form.setPrevAction(form.getAction());
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		}
		else if(!petReferralNumList.isEmpty())
		{
		    form.setAction("validatePetitionNum");
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Another referral of this juvenile already assigned to this petition number"));
		    saveErrors(aRequest, errors);
		    form.setPrevAction(form.getAction());
		    return aMapping.findForward(UIConstants.FAILURE);
		}

		//Persist Data.
		SaveJuvenilePetitionInformationEvent event = (SaveJuvenilePetitionInformationEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.SAVEJUVENILEPETITIONINFORMATION);
		event.setJuvenileNum(form.getJuvenileNumber());
		event.setLcDate(DateUtil.getCurrentDate());
		event.setLcTime(Calendar.getInstance().getTime());
		event.setLcUser(PDSecurityHelper.getLogonId());
		event.setOffenseCodeId(form.getPetitionAllegation());
		//commented the null check as allegationseverity needs to be refreshed even if validateoffense button is not used bug 143101
		/*if (form.getPetitionAllegationSev() == null || (form.getPetitionAllegationSev() != null && form.getPetitionAllegationSev().isEmpty()))
		{*/
		    if (jocEvent != null)
		    {
			form.setPetitionAllegationSev(jocEvent.getSeverity());
		    }
		//}
		event.setSeverity(form.getPetitionAllegationSev());
		//event.setAmend(form.getPetitionAmendment());
		event.setPetitionDate(DateUtil.stringToDate(form.getFilingDate(), DateUtil.DATE_FMT_1));
		event.setPetitionNum(form.getPetitionNumber());
		event.setStatus(form.getPetitionStatus());
		event.setType(form.getPetitionType());
		event.setRecType("PETITION");
		event.setReferralNum(form.getReferralNumber());
		event.setSequenceNum("1");
		event.setTjpcSeqNum(null);
		//task 172545
		 if(form.getPetitionStatus().equalsIgnoreCase("F"))
		     event.setCJISNum(form.getOffenseDetail().getCjisNum());
		CompositeResponse compositeResponse = MessageUtil.postRequest(event);
		Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
		if (errorResponse != null)
		{
		    ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
		    try
		    {
			handleFatalUnexpectedException(myEvt.getMessage());
		    }
		    catch (GeneralFeedbackMessageException e)
		    {
			e.printStackTrace();
		    }
		}
		else
		{
		    // update Referral
		    GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
		    jjsEvt.setJuvenileNum(form.getJuvenileNumber());
		    jjsEvt.setReferralNum(form.getReferralNumber());
		    Iterator<JJSReferral> refIter = JJSReferral.findAll(jjsEvt);

		    if (refIter.hasNext())
		    {
			JJSReferral ref = refIter.next();
			if (ref != null && ref.getCloseDate() == null)
			{
			    ref.setTotalPetitions("1");
			    ref.setLcDate(DateUtil.getCurrentDate());
			    ref.setLcTime(Calendar.getInstance().getTime());
			    ref.setLcUser(PDSecurityHelper.getLogonId());
			    IHome home = new Home();
			    home.bind(ref);
			}
		    }

		    // All went through, show the message and disable the petitionInformation.
		    ActionMessages messageHolder = new ActionMessages();
		    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.petitionAdded"));
		    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
		    saveMessages(aRequest, messageHolder);
		    form.setAction("courtHearing"); // set it back to initial setting to disable it.
		}
	    }
	    else
	    {
		//do the subpoenas print.
		form.setAction("subpoenas");
		String subpoenaQueryString = aRequest.getParameter("data");
		form.setQueryString(subpoenaQueryString);

		if (form.getQueryString() != null && !form.getQueryString().isEmpty())
		{
		    form.setSubpoenaPrinted(true);
		    SubpoenaReportBean rptBean = new SubpoenaReportBean();

		    JuvenileDistrictCourtHelper.populateSubpoenaReportBean(rptBean, form);

		    aRequest.getSession().setAttribute("reportInfo", rptBean);
		    //let the pdfManager know that the report should be saved in the request during report creation
		    BFOPdfManager pdfManager = BFOPdfManager.getInstance();

		    if (subpoenaQueryString.equalsIgnoreCase("J"))
		    { //JUVENILE
			if (form.getCert() != null && form.getCert().equalsIgnoreCase("Y"))
			{
			    if (rptBean.getCertCode().equalsIgnoreCase("54.02(J)"))
			    {
				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_CERT_OVER17_JUVENILE);
			    }
			    else
			    {
				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_CERT_17_JUVENILE);
			    }
			}
			else
			{
			    pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_STD_JUVENILE); //std
			}
		    }
		    if (subpoenaQueryString.equalsIgnoreCase("F"))
		    {//FATHER
			if (rptBean.getMemberName() != null && !rptBean.getMemberName().getFormattedName().isEmpty())
			{
			    if (form.getCert() != null && form.getCert().equalsIgnoreCase("Y"))
			    {
				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_CERT_GUARD_FATHER);
			    }
			    else
			    {
				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_STD_GUARD_FATHER); //std
			    }
			}
		    }
		    if (subpoenaQueryString.equalsIgnoreCase("M"))
		    { //MOTHER
			if (rptBean.getMemberName() != null && !rptBean.getMemberName().getFormattedName().isEmpty())
			{
			    if (form.getCert() != null && form.getCert().equalsIgnoreCase("Y"))
			    {
				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_CERT_GUARD_MOTHER);
			    }
			    else
			    {
				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_STD_GUARD_MOTHER); //std
			    }
			}
		    }
		    if (subpoenaQueryString.equalsIgnoreCase("O"))
		    { //OTHER
			if (rptBean.getMemberName() != null && !rptBean.getMemberName().getFormattedName().isEmpty())
			{
			    if (form.getCert() != null && form.getCert().equalsIgnoreCase("Y"))
			    {
				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_CERT_GUARD_OTHER);
			    }
			    else
			    {
				pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_STD_GUARD_OTHER);//std
			    }
			}
		    }
		    form.setPrevAction(form.getAction());
		} //end of if.
	    }
	}//end of else -- its hearing Correction
	return aMapping.findForward(UIConstants.INITIAL_SETTING_DISPLAY);
    }

    /**
     * courtMainMenu
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward courtMainMenu(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.reset();
	form.setCourtDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));
	form.setAction("initialSetting");
	return aMapping.findForward(UIConstants.COURT_MAIN_MENU);
    }

    /**
     * validateOffenseCode
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward validateOffenseCode(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setAction("validateOffenseCd");

	JuvenileOffenseCodeResponseEvent jocEvent = JuvenileDistrictCourtHelper.validateOffenseCd(form.getPetitionAllegation());
	if (jocEvent != null)
	{
	  //add error message here after checking severity and subtype
	  //task 175919
	  if(jocEvent.getSeverity()!=null && jocEvent.getSeveritySubtype()!=null)
	  {
	     if((jocEvent.getSeverity().equals("0") || jocEvent.getSeverity().equals("1"))&&!jocEvent.getSeveritySubtype().equalsIgnoreCase("T") && form.getPetitionStatus().equalsIgnoreCase("F"))
	     {
	 	form.setAction("validateOffenseCd");
	 	ActionErrors errors = new ActionErrors();
	 	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The entered petition allegation does not qualify for a status of Filed. Please review and submit again"));
	 	saveErrors(aRequest, errors);	 	
	 	return aMapping.findForward(UIConstants.FAILURE);
	     }
	  		    
	  }
	    String lit = jocEvent.getShortDescription() + " (" + jocEvent.getCategory() + ")";
	    form.setPetitionAllegationDesc(lit);
	    form.setPetitionAllegationSev(jocEvent.getSeverity());

	    ActionMessages messageHolder = new ActionMessages();
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.allegationEnteredValid"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	}
	else
	{
	    form.setCursorPosition("Allegation");
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Discontinued or Invalid Allegation Code, Please Correct and Retry"));
	    saveErrors(aRequest, errors);
	    form.setPrevAction(form.getAction());
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	form.setPrevAction(form.getAction());
	return aMapping.findForward(UIConstants.INITIAL_SETTING_DISPLAY);
    }

    /**
     * goToOffenseSearch
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward goToOffenseSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setAction("SubmitInitialSetting");
	form.setConfirmMsg(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setAlphaCodeId(UIConstants.EMPTY_STRING);
	form.setShortDesc(UIConstants.EMPTY_STRING);
	form.setDpsCodeId(UIConstants.EMPTY_STRING);
	form.setCategoryId(UIConstants.EMPTY_STRING);
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setNcicCode(UIConstants.EMPTY_STRING);
	form.setOffenseResultList(new ArrayList());
	// This code to retrieve code table data from JJS code table is based on code from 'Search Code Table' left Nav option
	/*IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetCodetableRecordEvent request = (GetCodetableRecordEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETCODETABLERECORD);
	request.setCodetableRegId("27");
	request.setType("CX");
	dispatch.postEvent(request);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	if (error != null)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.generic", error.getMessage()));
	    saveErrors(aRequest, errors);
	    String forwardStr = UIConstants.FAILURE;
	    form.setPrevAction(form.getAction());
	    return aMapping.findForward(forwardStr);
	}

	Collection codetableDataList = MessageUtil.compositeToCollection(compositeResponse, CodetableDataResponseEvent.class);

	if (codetableDataList == null || codetableDataList.isEmpty())
	{
	    codetableDataList = new ArrayList();
	}
	Collections.sort((ArrayList) codetableDataList);
	// load codeTableDataList info into display event
	JuvenileCasefileOffenseCodeResponseEvent ocEvent = new JuvenileCasefileOffenseCodeResponseEvent();
	List temp1 = (List) codetableDataList;
	List temp2 = new ArrayList();
	List eventValues = new ArrayList();
	String inactive = UIConstants.EMPTY_STRING;
	String discontCode=UIConstants.EMPTY_STRING; // U.S 58355
	for (int x = 0; x < temp1.size(); x++)
	{
	    CodetableDataResponseEvent ctrEvent = (CodetableDataResponseEvent) temp1.get(x);
	    ocEvent = new JuvenileCasefileOffenseCodeResponseEvent();
	    eventValues = CollectionUtil.iteratorToList(ctrEvent.getValueMap().values().iterator());
	    inactive = UIConstants.EMPTY_STRING;
	    discontCode=UIConstants.EMPTY_STRING; // U.S 58355
	    // value of 'y' Index of the DISPLAYORDER (sort the display order first and then assign the index to y) value in CODETBLREGATTR
	    // table for CODETBLREG_ID = 27
	    for (int y = 0; y < eventValues.size(); y++)
	    {
		if (y == 0)
		{
		    ocEvent.setAlphaCode(eventValues.get(y).toString());
		}
		if (y == 1)
		{
		    ocEvent.setNumericCode(eventValues.get(y).toString());
		}
		if (y == 2)
		{
		    ocEvent.setCategory(eventValues.get(y).toString());
		}
		if (y == 5)
		{
		    ocEvent.setReportGroup(eventValues.get(y).toString());
		}
		if (y == 6)
		{
		    ocEvent.setShortDescription(eventValues.get(y).toString());
		}
		if (y == 7)
		{
		    ocEvent.setLongDescription(eventValues.get(y).toString());
		}
		if (y == 9)
		{
		    ocEvent.setDpsCode(eventValues.get(y).toString());
		}
		if (y == 10)
		{
		    ocEvent.setSeverity(eventValues.get(y).toString());
		}
		if (y == 11)
		{
		    ocEvent.setSeverityType(eventValues.get(y).toString());
		}
		if (y == 12)
		{
		    ocEvent.setSeveritySubType(eventValues.get(y).toString());
		}
		if (y == 13)
		{
		    inactive = eventValues.get(y).toString();
		}
		// U.S 58355
		if (y == 16)
		{
		    discontCode = eventValues.get(y).toString();
		}
		// U.S 58355
	    }
	    if (UIConstants.EMPTY_STRING.equals(inactive) && UIConstants.EMPTY_STRING.equals(discontCode)) // U.S 58355
	    {
		temp2.add(ocEvent);
	    }
	}
	form.setCodetableDataList(temp2);
	temp1 = null;
	temp2 = null;*/
	//eventValues = null;
	//inactive = null;
	GetAllJuvenileOffenseCodesEvent requestEvent = (GetAllJuvenileOffenseCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETALLJUVENILEOFFENSECODES);
	List<JuvenileCasefileOffenseCodeResponseEvent> codes = MessageUtil.postRequestListFilter(requestEvent, JuvenileCasefileOffenseCodeResponseEvent.class);
	List<JuvenileCasefileOffenseCodeResponseEvent> filteredCodes = new ArrayList<>();
	for ( JuvenileCasefileOffenseCodeResponseEvent code : codes ) {
	    /*if ( !"Y".equalsIgnoreCase( code.getInactiveInd() )
		    &&  !"Y".equalsIgnoreCase( code.getDiscontCode() ) ) {*/ //commenting as the search offense screen need to show the invalid or discontinued message 174995
		filteredCodes.add( code );
	    //}
	}
	Collections.sort(filteredCodes);

	form.setCodetableDataList(filteredCodes);
	form.setPrevAction(form.getAction());
	return aMapping.findForward(UIConstants.COURT_OFFENSE_SUCCESS);
    }

    /**
     * validateBarNumber
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward validateBarNumber(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setAction("courtHearing");
	AttorneyNameAndAddressResponseEvent attorneyRespEvt = null;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
	request.setBarNum(form.getBarNumber());
	dispatch.postEvent(request);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);
	Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	if (error != null || attorneyDataList.isEmpty())
	{
	    form.setCursorPosition("barNumber");
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Bar Number. Please Correct and Retry"));
	    saveErrors(aRequest, errors);
	    form.setAction("validateBarNumber");
	    form.setAttorneyName("");
	    form.setPrevAction(form.getAction());
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	if (attorneyDataList != null & !attorneyDataList.isEmpty())
	{
	    for (int x = 0; x < attorneyDataList.size();)
	    {
		AttorneyNameAndAddressResponseEvent respEvent = ((List<AttorneyNameAndAddressResponseEvent>) attorneyDataList).get(x);
		attorneyRespEvt = new AttorneyNameAndAddressResponseEvent();
		attorneyRespEvt.setAttyName(respEvent.getAttyName());
		attorneyRespEvt.setBarNum(respEvent.getBarNum());
		attorneyRespEvt.setAttyNameHistory(respEvent.getAttyNameHistory());
		break;
	    }
	}
	if (attorneyRespEvt != null)
	{
	    form.setAction("validateBarNumber");
	    form.setActionMessage("ValidateMessage");
	    ActionMessages messageHolder = new ActionMessages();
	    form.setAttorneyName(attorneyRespEvt.getAttyName());
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.barNumberValid"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	}
	form.setPrevAction(form.getAction());
	return aMapping.findForward(UIConstants.INITIAL_SETTING_DISPLAY);
    }

    /**
     * SearchAttorney
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward searchAttorney(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setAction("searchAttorney");
	form.setConfirmMsg(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setActionMessage("ValidateMessage");
	//form.setAttorneyName(UIConstants.EMPTY_STRING);
	//form.setBarNumber(UIConstants.EMPTY_STRING);
	//form.setAttorneyNameHistory(UIConstants.EMPTY_STRING);

	return aMapping.findForward(UIConstants.ATTORNEY_SEARCH_SUCCESS);
    }
    
    public ActionForward referralSummary(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	String juvNum = courtHearingForm.getJuvenileNumber();
	courtHearingForm.reset();

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
		     event.setAttributeValue(referral.getJpoId());
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
	     
	    /*  if(!referral.getJpoId().isEmpty() ){
	      referral.setJpoId("UV" + referral.getJpoId());
	    IName name = SecurityUIHelper.getUserName(referral.getJpoId());
	    if (name != null)
	    {
	    	if (! name.getFormattedName().equalsIgnoreCase("NOT AVAILABLE")){
	    	    referral.setJpo(name.getFormattedName());
	    	}
	    	else{
	    		OfficerProfileResponseEvent respEvent = PDOfficerProfileHelper.getOfficerProfileByLogonId(referral.getJpoId());
	    		if (respEvent != null) {
	    			IName nameAlt = respEvent.getOfficerName();
	    			if (nameAlt != null) {
	    			    referral.setJpo(respEvent.getOfficerName().getFormattedName());
	    			}
	    		}
	    	}
	    }
	      }*/
	    //for each referral get the casefiles, then find the supervision Category, Supervision Type of the most recent casefile assign /highest seq Num - confirmed with Carla 7/17/2018 [email]
	    JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(juvNum, referral.getReferralNumber());
	    Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();

	    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
		@Override
		public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
		{
		    if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
			//return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());	
			return evt1.getRefSeqNum().compareTo(evt2.getRefSeqNum());
		    else
			return -1;
		}
	    }));
	    Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
	    while (caseFileRefItr.hasNext())
	    {
		//add if to check active
		JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
		if(caseFileReferral.getCaseStatusCd().equalsIgnoreCase("A"))
        	{
        		referral.setSupervisionCategoryId(caseFileReferral.getSupervisionCat());
        		referral.setSupervisionType(caseFileReferral.getSupervisionType());
        		referral.setSupervisionTypeId(caseFileReferral.getSupervisionTypeCd());
        		referral.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, referral.getSupervisionCategoryId()));
        		// referral.setJpoId(caseFileReferral.getOfficerID());
        		OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
        		if (officerProfileResponse != null)
        		{
        		    referral.setJpoId(officerProfileResponse.getUserId());
        		    String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
        		    referral.setJpo(officerFullName);
        		}
        		break;
		}
	    }
	    //commented the below piece of code for BUG# 71197
	    /*	    //for each referral get the casefiles, then find the supervision Category, Supervision Type and JPO   	
	    	    JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getCasefilesFromReferral(juvNum, referral.getReferralNumber());
	    	    JPOofRecSupervisionTypeCategoryResponseEvent codeResp = JuvenileDistrictCourtHelper.setJPOOfRecordForReferralList(refCasefileList.getCasefileReferrals(), juvNum);
	    	    if (!codeResp.getJpoId().isEmpty())
	    	    {
	    		OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(codeResp.getJpoId());
	    		referral.setJpoId(officerProfileResponse.getUserId());
	    		String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
	    		referral.setJpo(officerFullName);
	    		referral.setSupervisionTypeId(codeResp.getSupervisionTypeId());
	    		referral.setSupervisionType(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, codeResp.getSupervisionTypeId()));
	    		referral.setSupervisionCategoryId(codeResp.getSupervisionCategoryId());
	    		referral.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, codeResp.getSupervisionCategoryId()));
	    	    }*/
	}
	
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
    
    /**
     * courtAction
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward courtAction(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.setAction("submitCourtAction");
	courtHearingForm.setDktSearchResults(Collections.EMPTY_LIST); //empty the list

	String courtId = courtHearingForm.getCourtId();
	String date = courtHearingForm.getCourtDate();

	List<DocketEventResponseEvent> dockets = null;
	Map<String, DocketEventResponseEvent> docketsMap = new HashMap<String, DocketEventResponseEvent>();
	Map<String, DocketEventResponseEvent> originalDocketsMap = new HashMap<String, DocketEventResponseEvent>();

	// check if it is a valid court Id's
	if (courtId != null && !courtId.isEmpty())
	{
	    //Settings scheduled in Juvenile Referee courts cannot be retrieved using this option.
	    if (courtId.equals("001"))
	    {
		courtId = "1";
	    }
	    else
	    {
		if (courtId.equals("002"))
		{
		    courtId = "2";
		}
	    }
	    //CODETABLE
	    GetJuvenileCourtsEvent courtEvent = (GetJuvenileCourtsEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTS);
	    courtEvent.setCourt(courtId);
	    CompositeResponse courtRoomResp = MessageUtil.postRequest(courtEvent);
	    JuvenileCourtResponseEvent crtRespEvt = (JuvenileCourtResponseEvent) MessageUtil.filterComposite(courtRoomResp, JuvenileCourtResponseEvent.class);

	    /*
	     * If the court is a detention court: the application will default to the search detailed in the Process Juvenile Detention Hearings.doc
	     */
	    if (crtRespEvt != null)
	    {
		if (crtRespEvt.getRefereesCourtInd() != null && crtRespEvt.getRefereesCourtInd().equals("Y"))
		{
		    courtHearingForm.setAction("error");
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Referee Court Not Valid For This Option, Please Correct And Retry"));
		    saveErrors(aRequest, errors);
		    courtHearingForm.setCursorPosition("courtId");
		    return aMapping.findForward(UIConstants.SUCCESS);
		}
	    }
	    //83426
	    List<JuvenileDispositionCodeResponseEvent> juvenileDispositions = JuvenileDistrictCourtHelper.getSortedCourtDecisions();
	    courtHearingForm.setCourtDecisionsResponses(juvenileDispositions);
	    List<JSONObject> districtcourtDecisionsList = JuvenileDistrictCourtHelper.districtcourtDecisionResponses(juvenileDispositions);
	    courtHearingForm.setDistrictcourtDecisionsList(districtcourtDecisionsList);
	    //court calendar record. JJSCLCOURT
	    GetJJSCLCourtByRefereeCourtEvent jjsCLCrtEvent = (GetJJSCLCourtByRefereeCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYREFEREECOURT);
	    jjsCLCrtEvent.setCourtDate(date);
	    jjsCLCrtEvent.setCourtId(courtId);
	    dispatch.postEvent(jjsCLCrtEvent);
	    CompositeResponse resp = (CompositeResponse) dispatch.getReply();
	    List<DocketEventResponseEvent> crtDktRespEvts = MessageUtil.compositeToList(resp, DocketEventResponseEvent.class);

	    if (crtDktRespEvts != null && !crtDktRespEvts.isEmpty())
	    {
		Iterator<DocketEventResponseEvent> dktRespEvtsItr = crtDktRespEvts.iterator();

		while (dktRespEvtsItr.hasNext())
		{
		    DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) dktRespEvtsItr.next();
		    if (docRespEvent != null)
		    {
			docRespEvent.setCourtTime(JuvenileFacilityHelper.stripColonInDate(docRespEvent.getCourtTime()));
			
			//set Court Date and time
			if (docRespEvent.getCourtResult() != null && !docRespEvent.getCourtResult().isEmpty())
			{
			    // court Decision Code table.83426
			    // GetJuvenileDispositionCodeEvent courtDisp = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
			    //  courtDisp.setCode(docRespEvent.getCourtResult());
			    //    CompositeResponse dispCompResp = postRequestEvent(courtDisp);
			    //   Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
			    if (juvenileDispositions != null)
			    {
				Iterator<JuvenileDispositionCodeResponseEvent> juvDispItr = juvenileDispositions.iterator();
				while (juvDispItr.hasNext())
				{
				    JuvenileDispositionCodeResponseEvent juvDisp = juvDispItr.next();

				    if (juvDisp != null && juvDisp.getCodeAlpha() != null && juvDisp.getCodeAlpha().equalsIgnoreCase(docRespEvent.getCourtResult()) && juvDisp.getOptionCode() != null && juvDisp.getOptionCode().equalsIgnoreCase("R"))
				    {
					GetJJSCLCourtByChainAndSeqNumEvent courtEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
					courtEvt.setChainNumber(docRespEvent.getChainNumber());
					courtEvt.setSeqNumber(String.valueOf(Integer.valueOf(docRespEvent.getSeqNum()) + 10));
					dispatch.postEvent(courtEvt);

					CompositeResponse courtResp = (CompositeResponse) dispatch.getReply();
					List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtResp, DocketEventResponseEvent.class);
					Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

					while (docketResponsesItr.hasNext())
					{
					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
					    if (docResp != null)
					    {
						docRespEvent.setActionDate(docResp.getCourtDate());
						docRespEvent.setOldactionDate(docResp.getCourtDate());
						docRespEvent.setActionTime(JuvenileFacilityHelper.stripColonInDate(docResp.getCourtTime()));
						docRespEvent.setOldactionTime(JuvenileFacilityHelper.stripColonInDate(docResp.getCourtTime()));
						docRespEvent.setOriginalActionDate(docResp.getCourtDate());
						break;
					    }
					}
				    }
				}// while
			    }
			}
			else
			{
			    docRespEvent.setActionDate("");
			    docRespEvent.setActionTime("");
			}

			if (docRespEvent.getJuvenileNumber() != null && docRespEvent.getReferralNum() != null)
			{
			    // Get petition
			    List<PetitionResponseEvent> petitionResponses = JuvenileDistrictCourtHelper.getPetitionsByJuvAndRefNum(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
			    Iterator<PetitionResponseEvent> petitionIter = petitionResponses.iterator();
			    if (petitionIter != null && petitionIter.hasNext())
			    {
				PetitionResponseEvent petition = petitionIter.next();
				if (petition != null)
				{
				    if (petition.getPetitionNum() != null)
				    {
					docRespEvent.setPetitionNumber(petition.getPetitionNum());
					docRespEvent.setPetitionStatusCd(petition.getPetitionStatus());
					if (petition.getPetitionStatus() != null)
					{
					    if ("F".equalsIgnoreCase(petition.getPetitionStatus()))
					    {
						docRespEvent.setPetitionStatus("FILED");
					    }
					    else
						if ("R".equalsIgnoreCase(petition.getPetitionStatus()))
						{
						    docRespEvent.setPetitionStatus("REOPENED");
						}
					}
					docRespEvent.setPetitionAmendment(petition.getAmend());
					docRespEvent.setPetitionType(petition.getPetitionType());
					docRespEvent.setPetitionTypeDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PETITION_TYPE, petition.getPetitionType()));
					docRespEvent.setPetitionAllegation(petition.getOffenseCodeId());
				    }
				}
			    }
			    // get the JPO ID. //83426
			    String ctAssignJpoId = "";
			    JJSReferral ref = JuvenileDistrictCourtHelper.getRefInfo(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
			    if (ref != null)
			    {
				ctAssignJpoId = ref.getCtAssignJPOId();
			    }
			    if (ctAssignJpoId != null && !ctAssignJpoId.isEmpty())
			    {
				if (ctAssignJpoId.trim().length() < 4)
				{
				    docRespEvent.setProbationOfficerCd("UV" + ctAssignJpoId);
				}
				else
				{
				    docRespEvent.setProbationOfficerCd(ctAssignJpoId);
				}
				//officer name for hovering.
				OfficerProfileResponseEvent respEvent = PDOfficerProfileHelper.getOfficerProfileByLogonId(docRespEvent.getProbationOfficerCd());
				if (respEvent != null)
				{
				    IName name = respEvent.getOfficerName();
				    if (name != null)
				    {
					docRespEvent.setProbationOfficer(respEvent.getOfficerName().getFormattedName());
				    }
				}
			    }
			    else
			    {
				docRespEvent.setProbationOfficerCd("");
			    }
			    //added for CourtAction JuvenileNumber without 0 prefix #100626
			    if (docRespEvent.getJuvenileNumber() != null)
			    {	
				docRespEvent.setJuvNum(docRespEvent.getJuvenileNumber());
			    }
			    
			    //pad zero in juv num
			    if (docRespEvent.getJuvenileNumber() != null && !docRespEvent.getJuvenileNumber().substring(0, 1).matches("0"))
			    {	
				docRespEvent.setJuvenileNumber("0" + docRespEvent.getJuvenileNumber());
			    }

			    //reset Hearing Type
			    if (docRespEvent.getResetHearingType() == null || (docRespEvent.getResetHearingType() != null && docRespEvent.getResetHearingType().isEmpty()))
			    {
				docRespEvent.setResetHearingType(docRespEvent.getHearingType());
				docRespEvent.setOldresetHearingType(docRespEvent.getHearingType());
			    }
			    docketsMap.put(docRespEvent.getDocketEventIdKey(), docRespEvent);

			    // add it to the new object to maintain originality
			    DocketEventResponseEvent origDocket = new DocketEventResponseEvent();
			    try
			    {
				//ConvertUtils.register(new DateConverter(null), Date.class);
				java.util.Date defaultValue = null;
				Converter converter = new DateConverter(defaultValue);
				BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
				beanUtilsBean.getConvertUtils().register(converter, java.util.Date.class);
				BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
				BeanUtils.copyProperties(origDocket, docRespEvent);
			    }
			    catch (IllegalAccessException e)
			    {
				e.printStackTrace();
			    }
			    catch (InvocationTargetException e)
			    {
				e.printStackTrace();
			    }
			    originalDocketsMap.put(docRespEvent.getDocketEventIdKey(), origDocket); //original docket map
			    // add it to the new object to maintain originality
			} // juvenile no check
		    }
		}
		courtHearingForm.setCourtHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes("COURT"));
	    }

	    //GET JJSCLDETENTION RECORDS
	    Map<String, JuvenileCourtDecisionCodeResponseEvent> courtDecisionsMap = new HashMap<String, JuvenileCourtDecisionCodeResponseEvent>();
	    GetJJSCLDetentionByRefereeCourtEvent jjsdetnCrtEvent = (GetJJSCLDetentionByRefereeCourtEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYREFEREECOURT);
	    jjsdetnCrtEvent.setCourtDate(DateUtil.stringToDate(courtHearingForm.getCourtDate(), DateUtil.DATE_FMT_1));
	    jjsdetnCrtEvent.setCourtId(courtId);
	    dispatch.postEvent(jjsdetnCrtEvent);
	    CompositeResponse clDetnResp = (CompositeResponse) dispatch.getReply();
	    List<DocketEventResponseEvent> clDetnDktRespEvts = MessageUtil.compositeToList(clDetnResp, DocketEventResponseEvent.class);

	    //pre-load new court decision code table //83426
	    List<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = JuvenileCaseHelper.getCourtDecisionsNew();

	    if (clDetnDktRespEvts != null && !clDetnDktRespEvts.isEmpty())
	    {
		Iterator<DocketEventResponseEvent> clDetnDktRespItr = clDetnDktRespEvts.iterator();
		while (clDetnDktRespItr.hasNext())
		{
		    DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) clDetnDktRespItr.next();
		    if (docRespEvent != null)
		    {
			//83426
			//disable reset Date when the required Reset is NO
			//GetJuvenileCourtDecisionCodesEvent courtDecision = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
			//Collection<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = MessageUtil.postRequestListFilter(courtDecision, JuvenileCourtDecisionCodeResponseEvent.class);
			if (crtDecisions != null)
			{
			    Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
			    while (crtDecisionsItr.hasNext())
			    {
				JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
				if (crtDecision != null)
				{
				    //filter
				    if (docRespEvent.getHearingType() != null)
				    {
					if (docRespEvent.getHearingType().equalsIgnoreCase("DT"))
					{
					    if (crtDecision.getDecision() != null && crtDecision.getDecision().equalsIgnoreCase("DT") || crtDecision.getDecision().equalsIgnoreCase("BOTH"))
					    {
						courtDecisionsMap.put(crtDecision.getCode(), crtDecision);
					    }
					}
					else
					{
					    if (docRespEvent.getHearingType().equalsIgnoreCase("PC"))
					    {
						if (crtDecision.getDecision() != null && crtDecision.getDecision().equalsIgnoreCase("PC") || crtDecision.getDecision().equalsIgnoreCase("BOTH"))
						{
						    courtDecisionsMap.put(crtDecision.getCode(), crtDecision);
						}
					    }
					}
				    }
				}
			    }
			}

			docRespEvent.setCourtTime(JuvenileFacilityHelper.stripColonInDate(docRespEvent.getCourtTime()));
			//set Court Date and time
			if (docRespEvent.getCourtResult() != null && !docRespEvent.getCourtResult().isEmpty())
			{
			    //83426
			    // GetJuvenileCourtDecisionCodesEvent courtDecsn = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
			    //  courtDecsn.setCode(docRespEvent.getCourtResult());
			    //  Collection<JuvenileCourtDecisionCodeResponseEvent> crtDescisions = MessageUtil.postRequestListFilter(courtDecsn, JuvenileCourtDecisionCodeResponseEvent.class);
			    if (crtDecisions != null)
			    {
				Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
				while (crtDecisionsItr.hasNext())
				{
				    JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
				    if (crtDecision != null)
				    {
					if (crtDecision.getResetAllowed().equalsIgnoreCase("Y") || crtDecision.getResetAllowed().equalsIgnoreCase("R"))
					{
					    docRespEvent.setDisableResetDate("false");
					    break;
					}
					else
					{
					    docRespEvent.setDisableResetDate("true");
					    break;
					}
				    }
				}
			    }
			    // court Decision Code table.
			    //83426
			    //   GetJuvenileDispositionCodeEvent courtDisp = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
			    //    courtDisp.setCode(docRespEvent.getCourtResult());
			    //    CompositeResponse dispCompResp = postRequestEvent(courtDisp);
			    //    Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
			    if (juvenileDispositions != null)
			    {
				Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvenileDispositions.iterator();
				while (juvCodeIter.hasNext())
				{
				    JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
				    if (dispResp != null && dispResp.getCodeAlpha() != null && dispResp.getCodeAlpha().equalsIgnoreCase(docRespEvent.getCourtResult()) && dispResp.getOptionCode() != null && dispResp.getOptionCode().equals("R"))
				    {
					GetJJSCLDetentionByChainAndSeqNumEvent detentionEvt = (GetJJSCLDetentionByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAINANDSEQNUM);
					detentionEvt.setChainNumber(docRespEvent.getChainNumber());
					detentionEvt.setSeqNumber(String.valueOf(Integer.valueOf(docRespEvent.getSeqNum()) + 10));
					dispatch.postEvent(detentionEvt);

					CompositeResponse detnResp = (CompositeResponse) dispatch.getReply();
					List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(detnResp, DocketEventResponseEvent.class);
					Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

					while (docketResponsesItr.hasNext())
					{
					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
					    if (docResp != null)
					    {
						docRespEvent.setOriginalActionDate(docResp.getCourtDate());
						docRespEvent.setActionDate(docResp.getCourtDate());
						docRespEvent.setActionTime(JuvenileFacilityHelper.stripColonInDate(docResp.getCourtTime()));
						break;
					    }
					}
				    }
				}
			    }
			}
			else
			{ //do not set as in m204
			    docRespEvent.setActionDate("");
			    docRespEvent.setActionTime("");
			}

			if (docRespEvent.getJuvenileNumber() != null && docRespEvent.getReferralNum() != null)
			{
			  //task 130940- to show J with juvnum only if petitionnum is null.
			    if (docRespEvent.getPetitionNumber() == null)
			        docRespEvent.setPetitionNumber("J0" + docRespEvent.getJuvenileNumber());
			    // get the JPO ID. //83426
			    String ctAssignJpoId = "";
			    JJSReferral ref = JuvenileDistrictCourtHelper.getRefInfo(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
			    if (ref != null)
			    {
				ctAssignJpoId = ref.getCtAssignJPOId();
			    }
			    if (ctAssignJpoId != null && !ctAssignJpoId.isEmpty())
			    {
				if (ctAssignJpoId.trim().length() < 4)
				{
				    docRespEvent.setProbationOfficerCd("UV" + ctAssignJpoId);
				}
				else
				{
				    docRespEvent.setProbationOfficerCd(ctAssignJpoId);
				}
				//officer name for hovering.
				OfficerProfileResponseEvent respEvent = PDOfficerProfileHelper.getOfficerProfileByLogonId(docRespEvent.getProbationOfficerCd());
				if (respEvent != null)
				{
				    IName name = respEvent.getOfficerName();
				    if (name != null)
				    {
					docRespEvent.setProbationOfficer(respEvent.getOfficerName().getFormattedName());
				    }
				}
			    }
			    else
			    {
				docRespEvent.setProbationOfficerCd("");
			    }
			    //added for CourtAction JuvenileNumber without 0 prefix #100626
			    if (docRespEvent.getJuvenileNumber() != null)
			    {	
				docRespEvent.setJuvNum(docRespEvent.getJuvenileNumber());
			    }
			    //pad zero in juv num
			    if (docRespEvent.getJuvenileNumber() != null && !docRespEvent.getJuvenileNumber().substring(0, 1).matches("0"))
			    {
				docRespEvent.setJuvenileNumber("0" + docRespEvent.getJuvenileNumber());
			    }
			    //Detention calendar records: Will stay the same as the PetitionType value unless user resets the setting; in which case it will be set to �DT.� 
			    //filter
			    if (docRespEvent.getHearingType() != null && docRespEvent.getHearingType().equalsIgnoreCase("DT"))
			    {
				docRespEvent.setResetHearingType(docRespEvent.getHearingType());
				docRespEvent.setResetHearingTypeDesc(docRespEvent.getHearingTypeDesc());
			    }
			    else
				if (docRespEvent.getHearingType() != null && docRespEvent.getHearingType().equalsIgnoreCase("PC"))
				{
				    docRespEvent.setResetHearingType(docRespEvent.getHearingType());
				    docRespEvent.setResetHearingTypeDesc(docRespEvent.getHearingTypeDesc());
				}

			    docketsMap.put(docRespEvent.getDocketEventIdKey(), docRespEvent);
			    // add it to the new object to maintain originality
			    DocketEventResponseEvent origDocket = new DocketEventResponseEvent();
			    try
			    {
				    java.util.Date defaultValue = null;
				    Converter converter = new DateConverter(defaultValue);
				    BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
				    beanUtilsBean.getConvertUtils().register(converter, java.util.Date.class);
				    BeanUtils.copyProperties(origDocket, docRespEvent);
			    }
			    catch (IllegalAccessException e)
			    {
				e.printStackTrace();
			    }
			    catch (InvocationTargetException e)
			    {
				e.printStackTrace();
			    }
			    originalDocketsMap.put(docRespEvent.getDocketEventIdKey(), origDocket); //original docket map
			    // add it to the new object to maintain originality
			}
		    }
		}// while

		courtHearingForm.setHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes(null));
		List<JuvenileCourtDecisionCodeResponseEvent> courtDecisionResponseEvts = new ArrayList(courtDecisionsMap.values());
		Collections.sort(courtDecisionResponseEvts, new Comparator<JuvenileCourtDecisionCodeResponseEvent>() {
		    @Override
		    public int compare(JuvenileCourtDecisionCodeResponseEvent evt1, JuvenileCourtDecisionCodeResponseEvent evt2)
		    {
			if (evt1.getCode() != null && evt2.getCode() != null)
			    return evt1.getCode().compareTo(evt2.getCode());
			else
			    return -1;
		    }
		});
		List<JSONObject> courtDecisionsList = JuvenileDistrictCourtHelper.courtDecisionResponses(courtDecisionResponseEvts);
		courtHearingForm.setCourtDecisionsList(courtDecisionsList);
		courtHearingForm.setDetentionHearingResults(courtDecisionResponseEvts);
	    } //if
	      //bug:76303

	    // The last Court Date in a regular District Court for which there is no Result or Disposition for the setting. JJSCLANCILLARY
	    GetJJSCLAncillaryCourtByReferreeCourtEvent ancillaryCrtEvt = (GetJJSCLAncillaryCourtByReferreeCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYREFERREECOURT);
	    ancillaryCrtEvt.setCourtDate(date);
	    ancillaryCrtEvt.setCourtId(courtId);
	    dispatch.postEvent(ancillaryCrtEvt);
	    CompositeResponse compResp = (CompositeResponse) dispatch.getReply();
	    List<DocketEventResponseEvent> ancillaryDktsResp = MessageUtil.compositeToList(compResp, DocketEventResponseEvent.class);
	    if (ancillaryDktsResp != null && !ancillaryDktsResp.isEmpty())
	    {
		Iterator<DocketEventResponseEvent> dktRespEvtsItr = ancillaryDktsResp.iterator();

		while (dktRespEvtsItr.hasNext())
		{
		    DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) dktRespEvtsItr.next();
		    if (docRespEvent != null)
		    {
			docRespEvent.setCourtTime(JuvenileFacilityHelper.stripColonInDate(docRespEvent.getCourtTime()));
			if (docRespEvent.getCourtResult() != null && !docRespEvent.getCourtResult().isEmpty())
			{
			    //83426
			    // court Decision Code table.
			    //  GetJuvenileDispositionCodeEvent courtDisp = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
			    //  courtDisp.setCode(docRespEvent.getCourtResult());
			    //  CompositeResponse dispCompResp = postRequestEvent(courtDisp);
			    //  Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
			    if (juvenileDispositions != null)
			    {
				Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvenileDispositions.iterator();
				while (juvCodeIter.hasNext())
				{
				    JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
				    if (dispResp != null && dispResp.getCodeAlpha() != null && dispResp.getCodeAlpha().equalsIgnoreCase(docRespEvent.getCourtResult()) && dispResp.getOptionCode() != null && dispResp.getOptionCode().equals("R"))
				    {
					GetJJSCLAncillaryCourtByChainAndSeqNumEvent ancillaryEvt = (GetJJSCLAncillaryCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYCHAINANDSEQNUM);
					ancillaryEvt.setChainNumber(docRespEvent.getChainNumber());
					ancillaryEvt.setSeqNumber(String.valueOf(Integer.valueOf(docRespEvent.getSeqNum()) + 10));
					dispatch.postEvent(ancillaryEvt);

					CompositeResponse ancillaryResp = (CompositeResponse) dispatch.getReply();
					List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(ancillaryResp, DocketEventResponseEvent.class);
					Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

					while (docketResponsesItr.hasNext())
					{

					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
					    if (docResp != null)
					    {
						docRespEvent.setOriginalActionDate(docResp.getCourtDate());
						docRespEvent.setActionDate(docResp.getCourtDate());
						docRespEvent.setActionTime(JuvenileFacilityHelper.stripColonInDate(docResp.getCourtTime()));
						break;
					    }
					}
				    }
				}
			    }
			}
			else
			{
			    docRespEvent.setActionDate("");
			    docRespEvent.setActionTime("");
			} 
			//added for CourtAction JuvenileNumber without 0 prefix #100626
			if (docRespEvent.getJuvenileNumber() != null)
			 {	
			    docRespEvent.setJuvNum(docRespEvent.getJuvenileNumber());
			 }
			//pad zero in juv num
			if (docRespEvent.getJuvenileNumber() != null && !docRespEvent.getJuvenileNumber().substring(0, 1).matches("0"))
			{
			    docRespEvent.setJuvenileNumber("0" + docRespEvent.getJuvenileNumber());
			}
			if (docRespEvent.getSettingReason() != null && !docRespEvent.getSettingReason().isEmpty())
			{
			    docRespEvent.setResetHearingType(docRespEvent.getSettingReason());
			}
			else
			{
			    docRespEvent.setResetHearingType(docRespEvent.getHearingType());
			}

			docketsMap.put(docRespEvent.getDocketEventIdKey(), docRespEvent);
			// add it to the new object to maintain originality
			DocketEventResponseEvent origDocket = new DocketEventResponseEvent();
			try
			{
			    java.util.Date defaultValue = null;
			    Converter converter = new DateConverter(defaultValue);
			    BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
			    beanUtilsBean.getConvertUtils().register(converter, java.util.Date.class);
			    BeanUtils.copyProperties(origDocket, docRespEvent);
			}
			catch (IllegalAccessException e)
			{
			    e.printStackTrace();
			}
			catch (InvocationTargetException e)
			{
			    e.printStackTrace();
			}
			originalDocketsMap.put(docRespEvent.getDocketEventIdKey(), origDocket); //original docket map
			// add it to the new object to maintain originality
		    }
		}
		courtHearingForm.setAncillaryHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes("ANCILLARY"));
	    }

	    //Using bean comparator. 
	    /* ArrayList<ReverseComparator> sortFields = new ArrayList<ReverseComparator>();
	     sortFields.add(new ReverseComparator(new BeanComparator("issueFlag")));
	     sortFields.add(new ReverseComparator(new BeanComparator("courtTime")));
	     sortFields.add(new ReverseComparator(new BeanComparator("docketType")));
	     sortFields.add(new ReverseComparator(new BeanComparator("petitionNumber")));
	     sortFields.add(new ReverseComparator(new BeanComparator("juvenileNumber")));
	     ComparatorChain multiSort = new ComparatorChain(sortFields);
	     Collections.sort(dockets, multiSort);*/

	    if (!docketsMap.isEmpty())
	    {
		dockets = new ArrayList<DocketEventResponseEvent>(docketsMap.values());

		//sort by docket type.
		Collections.sort((List<DocketEventResponseEvent>) dockets, new Comparator<DocketEventResponseEvent>() {
		    public int compare(DocketEventResponseEvent det1, DocketEventResponseEvent det2)
		    {
			if (det2.getDocketType() != null && det1.getDocketType() != null)
			    return det2.getDocketType().compareTo(det1.getDocketType());
			else
			    return -1;
		    }
		});

		Collections.sort((List<DocketEventResponseEvent>) dockets, new Comparator<DocketEventResponseEvent>() {
		    @Override
		    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
		    {

			int cmp = 0;
			//hearing Type
			if (isEmpty(evt1.getHearingType()))
			{
			    if (isNotEmpty(evt2.getHearingType()))
			    {
				cmp = -1;
				return cmp;
			    }
			    else
			    {
				cmp = 0;
				return cmp;
			    }

			}
			else
			    if (isEmpty(evt2.getHearingType()))
			    {
				cmp = 1;
				return cmp;
			    }
			    else
			    {
				cmp = evt1.getHearingType().compareTo(evt2.getHearingType());
				if (cmp == 0)
				{
				    //court Time
				    if (isEmpty(evt1.getCourtTime()))
				    {
					if (isNotEmpty(evt2.getCourtTime()))
					{
					    cmp = -1;
					    return cmp;
					}
					else
					{
					    cmp = 0;
					    return cmp;
					}
				    }
				    else
					if (isEmpty(evt2.getCourtTime()))
					{
					    cmp = 1;
					    return cmp;
					}
					else
					{
					    cmp = evt1.getCourtTime().compareTo(evt2.getCourtTime());
					    //petition Num
					    if (cmp == 0)
					    {
						if (isEmpty(evt1.getPetitionNumber()))
						{
						    if (isNotEmpty(evt2.getPetitionNumber()))
						    {
							cmp = -1;//(one of them empty)
							return cmp;
						    }
						    else
						    {
							cmp = 0; //(equals)
							return cmp;
						    }
						}
						else
						    if (isEmpty(evt2.getPetitionNumber()))
						    {
							cmp = 1; //second one empty
							return cmp;
						    }
						    else
						    {
							/* String petitionNum1;
							 String petitionNum2;
							 
							 if(evt1.getPetitionNumber().startsWith("J")){
							petitionNum1 = evt1.getPetitionNumber().split("J")[1];
							 }else if(evt1.getPetitionNumber().endsWith("J")){
							petitionNum1 = evt1.getPetitionNumber().split("J")[0];
							 }else if(evt1.getPetitionNumber().contains("-")){
							petitionNum1=evt1.getPetitionNumber().split("-")[0]+evt1.getPetitionNumber().split("-")[1];
							 }else{
							petitionNum1=evt1.getPetitionNumber();
							 }

							 if(evt2.getPetitionNumber().startsWith("J")){
							petitionNum2 = evt2.getPetitionNumber().split("J")[1];
							 }else if(evt2.getPetitionNumber().endsWith("J")){
							petitionNum2 = evt2.getPetitionNumber().split("J")[0];
							 }else if(evt2.getPetitionNumber().contains("-")){
							petitionNum2=evt2.getPetitionNumber().split("-")[0]+evt2.getPetitionNumber().split("-")[1];
							 }else{
							petitionNum2=evt2.getPetitionNumber();
							 }*/
							cmp = evt1.getPetitionNumber().compareTo(evt2.getPetitionNumber());
							if (cmp == 0)
							{
							    if (isEmpty(evt1.getJuvenileNumber()))
							    {
								if (isNotEmpty(evt2.getJuvenileNumber()))
								{
								    cmp = -1;
								    return cmp;
								}
								else
								{
								    cmp = 0;
								    return cmp;
								}
							    }
							    else
								if (isEmpty(evt2.getJuvenileNumber()))
								{
								    cmp = 1;
								    return cmp;
								}
								else
								{
								    cmp = evt1.getJuvenileNumber().compareTo(evt2.getJuvenileNumber());
								}
							}
						    }
					    }
					}
				}
			    } //main else
			return cmp;
		    }
		});

		//courtHearingForm.setOriginalDktSearchResultsMap(originalDocketsMap);
		aRequest.getSession().setAttribute("originalSetting", originalDocketsMap);
		courtHearingForm.setDktSearchResultsMap(docketsMap);
		courtHearingForm.setAllDktSearchResults(dockets);

		List<JSONObject> juvenileDatesFormattedList = JuvenileDistrictCourtHelper.juvenileHolidays();
		courtHearingForm.setHolidaysList(juvenileDatesFormattedList);

		List<JuvenileDispositionCodeResponseEvent> courtDecisions = JuvenileDistrictCourtHelper.getSortedCourtDecisions();
		courtHearingForm.setCourtDecisionsResponses(courtDecisions);
		List<JuvenileDispositionCodeResponseEvent> courtDispDecisions = JuvenileDistrictCourtHelper.getCourtDispositionCodeTypeDorB();//this is the right one
		courtHearingForm.setCourtDispositionResponses(courtDispDecisions);
		List<JuvenileDispositionCodeResponseEvent> courtResultDecisions = JuvenileDistrictCourtHelper.getCourtResultCodeTypeRorB();//this is the right one
		courtHearingForm.setCourtResultResponses(courtResultDecisions);
		//courtHearingForm.setHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes(null));
	    }
	    if (courtHearingForm.getAllDktSearchResults() == null || (courtHearingForm.getAllDktSearchResults() != null && courtHearingForm.getAllDktSearchResults().isEmpty()))
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No Records Found Please Correct and Retry"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    else
	    {
		courtHearingForm.setSearchResultSize(dockets.size());
	    }
	}//court Id and date check
	return aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY);
    }
    
    
    /**
     * isEmpty
     * 
     * @param str
     * @return
     */
    private boolean isEmpty(String str)
    {
	return str == null || str.isEmpty();
    }

    /**
     * isNotEmpty
     * 
     * @param str
     * @return
     */
    private boolean isNotEmpty(String str)
    {
	return !isEmpty(str);
    }
    public ActionForward petitionCorrection(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward(UIConstants.INITIAL_SETTING_DISPLAY);
    
    }

    /*public ActionForward petitionCorrection(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
    CourtHearingForm form = (CourtHearingForm) aForm;
    form.setAction("petitionCorrection");
    String hearingTypeCode = form.getHearingType();
    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

    JuvenileHearingTypeCode hearingType = null;
    if (hearingTypeCode != null && !hearingTypeCode.isEmpty())
    {
        hearingType = JuvenileHearingTypeCode.find(hearingTypeCode);
        if (hearingType != null)
        {
    	if (hearingType.getCategoryInd() != null && hearingType.getCategoryInd().equalsIgnoreCase("A"))
    	{
    	    form.setAction("courtHearing");
    	    form.setActionError("courtHearingError");
    	    form.setCursorPosition("hearingType");
    	    ActionErrors errors = new ActionErrors();
    	    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.generic", "Hearing Type cannot be category of 'A'"));
    	    saveErrors(aRequest, errors);
    	    return aMapping.findForward(UIConstants.FAILURE);
    	}
        }
    }
    JuvenileProfileReferralListResponseEvent profileRefEvent = JuvenileFacilityHelper.getJuvReferralDetails(form.getJuvenileNumber(), form.getReferralNumber());
    if (profileRefEvent != null)
    {
        if ((profileRefEvent.getCourtResult() != null && !profileRefEvent.getCourtResult().isEmpty()) || (profileRefEvent.getCourtDisposition() != null && !profileRefEvent.getCourtDisposition().isEmpty())) //bug fix #64304
        {
    	form.setAction("courtHearing");
    	form.setActionError("courtHearingError");
    	form.setCursorPosition("hearingType");
    	ActionErrors errors = new ActionErrors();
    	errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.generic", "Hearing cannot be changed Result/Disposition already entered"));
    	saveErrors(aRequest, errors);
    	return aMapping.findForward(UIConstants.FAILURE);
        }
    }
    //update court record
    UpdateJJSCLCourtSettingEvent updateCourtEvt = (UpdateJJSCLCourtSettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLCOURTSETTING);
    
    GetJJSCourtEvent courtEvt = (GetJJSCourtEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSCOURT);
    courtEvt.setJuvenileNumber(form.getJuvenileNumber());
    courtEvt.setReferralNumber(form.getReferralNumber());
    dispatch.postEvent(courtEvt);
    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
    List<DocketEventResponseEvent> crtdktRespEvts = MessageUtil.compositeToList(compositeResponse, DocketEventResponseEvent.class);
    //seq num 10
    if (crtdktRespEvts != null && !crtdktRespEvts.isEmpty())
    {
        Iterator<DocketEventResponseEvent> crtdktRespEvtsItr = crtdktRespEvts.iterator();
        if (crtdktRespEvtsItr.hasNext())
        {
    	DocketEventResponseEvent crtDocResp = (DocketEventResponseEvent) crtdktRespEvtsItr.next();
    	if (crtDocResp != null && crtDocResp.getSeqNum().equals("10"))
    	{
    	    updateCourtEvt.setDocketEventId(crtDocResp.getDocketEventId());
    	}
        }
    }
    updateCourtEvt.setJuvenileNumber(form.getJuvenileNumber());
    updateCourtEvt.setReferralNumber(form.getReferralNumber());
    updateCourtEvt.setCourtId(form.getCourtId());
    updateCourtEvt.setCourtDate(DateUtil.stringToDate(form.getCourtDate(),DateUtil.DATE_FMT_1));
    updateCourtEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(form.getCourtTime()));
    if (form.getAttorneyConnection() != null)
    {
        updateCourtEvt.setAttorneyConnection(form.getAttorneyConnection());
    }
    if (form.isBarNumberValidated())
    {
        updateCourtEvt.setBarNumber(form.getBarNumber());
    }
    else
    {
        form.setPrevAction(form.getAction());
        //validate bar Number
        if (form.getBarNumber() != null && !form.getBarNumber().isEmpty())
        {
    	GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
    	request.setBarNum(form.getBarNumber());
    	dispatch.postEvent(request);
    	CompositeResponse compResp = (CompositeResponse) dispatch.getReply();

    	Map dataMap = MessageUtil.groupByTopic(compResp);
    	MessageUtil.processReturnException(dataMap);
    	Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compResp, AttorneyNameAndAddressResponseEvent.class);
    	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
    	if (error != null || attorneyDataList.isEmpty())
    	{
    	    ActionErrors errors = new ActionErrors();
    	    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.generic", "Invalid Bar Number. Please Correct and Retry"));
    	    saveErrors(aRequest, errors);
    	    form.setAttorneyName("");
    	    form.setAction("courtHearing");
    	    form.setActionError("courtHearingError");
    	    form.setCursorPosition("barNumber");
    	    return aMapping.findForward(UIConstants.FAILURE);
    	}
    	updateCourtEvt.setBarNumber(form.getBarNumber());
        }
    }
    updateCourtEvt.setAttorneyName(form.getAttorneyName());
    if (hearingType != null)
    {
        updateCourtEvt.setHearingType(hearingType.getCode());
        if (hearingType.getCategoryInd() != null && !hearingType.getCategoryInd().equalsIgnoreCase("A"))
        {
    	updateCourtEvt.setHearingCategory(hearingType.getCategoryInd());
    	updateCourtEvt.setIssueFlag("I");
    	updateCourtEvt.setJuryFlag(hearingType.getIssueInd());
        }
        if (hearingType.getIssueInd() != null && hearingType.getIssueInd().equalsIgnoreCase("B"))
        {
    	updateCourtEvt.setIssueFlag("B");
    	updateCourtEvt.setOptionFlag(hearingType.getOptionInd());
        }
    }
    updateCourtEvt.setLcDate(DateUtil.getCurrentDate());
    updateCourtEvt.setLcTime(Calendar.getInstance().getTime());
    updateCourtEvt.setLcUser(PDSecurityHelper.getLogonId());

    CompositeResponse resp = MessageUtil.postRequest(updateCourtEvt);
    DocketEventResponseEvent docket = (DocketEventResponseEvent) MessageUtil.filterComposite(resp, DocketEventResponseEvent.class);
    Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
    if (errorResponse != null)
    {
        ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
        try
        {
    	handleFatalUnexpectedException(myEvt.getMessage());
        }
        catch (GeneralFeedbackMessageException e)
        {
    	e.printStackTrace();
        }
    }
    if (docket.getUpdateFlag() != null && docket.getUpdateFlag().equalsIgnoreCase("true"))
    {
        //update referral rec
        GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
        jjsEvt.setJuvenileNum(form.getJuvenileNumber());
        jjsEvt.setReferralNum(form.getReferralNumber());
        Iterator<JJSReferral> refIter = JJSReferral.findAll(jjsEvt);

        if (refIter.hasNext())
        {
    	JJSReferral ref = refIter.next();
    	if (ref != null && ref.getCloseDate() == null)
    	{
    	    // any change to the court TJJD Paper Formalized
    	    ref.setReferralTypeInd("PF");
    	    ref.setCourtId(form.getCourtId());
    	    ref.setCourtDate(DateUtil.stringToDate(form.getCourtDate(), DateUtil.DATE_FMT_1));
    	    ref.setLcDate(DateUtil.getCurrentDate());
    	    ref.setLcTime(Calendar.getInstance().getTime());
    	    ref.setLcUser(PDSecurityHelper.getLogonId());
    	    new Home().bind(ref);
    	}
        }

        ActionMessages messageHolder = new ActionMessages();
        messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.hearingInfoUpdated"));
        aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
        saveMessages(aRequest, messageHolder);
        form.setCursorPosition("plaintiff");
        form.setAction("subpoenas");
        form.setHearingCorrectionFlag("");
    }
    else
    {
        form.setAction("petitionCorrection"); // information not updated, as none of the fields changed.
        form.setActionError("courtHearingError");
    }
    form.setPrevAction(form.getAction());
    return aMapping.findForward(UIConstants.INITIAL_SETTING_DISPLAY);
    }*/
    /*
    * (non-Javadoc)
    * 
    * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
    */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.validateOffenseCode", "validateOffenseCode");
	keyMap.put("button.searchForOffenseCode", "goToOffenseSearch");
	keyMap.put("button.searchAttorney", "searchAttorney");
	keyMap.put("button.hearingCorrection", "hearingCorrection");
	keyMap.put("button.petitionCorrection", "petitionCorrection");
	keyMap.put("button.petitionUpdateBtn", "updatePetition");
	keyMap.put("button.validateBarNumber", "validateBarNumber");
	keyMap.put("button.submit", "submit");
	keyMap.put("button.courtMainMenu", "courtMainMenu");
	keyMap.put("button.back", "back");
	keyMap.put("button.referralSummary", "referralSummary");
	keyMap.put("button.courtAction2", "courtAction");
    }
}
