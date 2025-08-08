package ui.juvenilecase.districtCourtHearings.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetAllJuvenileOffenseCodesEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.DeleteJuvenilePetitionInformationEvent;
import messaging.juvenilewarrant.SaveJuvenilePetitionInformationEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ActivityConstants;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import pd.juvenilecase.JuvenileCasefileReferral;
import pd.security.PDSecurityHelper;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.SubpoenaReportBean;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * 
 * @author sthyagarajan
 * 
 */
public class SubmitJuvenileDistrictCourtPetitionUpdateAction extends JIMSBaseAction
{

    @Override
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.submit", "submitPetitionUpdate");
	keyMap.put("button.printSubpoenas", "printSubpoenas");
	keyMap.put("button.deletePetition", "deletePetition");
	keyMap.put("button.validateOffense","validateOffenseCode");
	keyMap.put("button.searchForOffense","goToOffenseSearch");
	keyMap.put("button.back","back");
	keyMap.put("button.courtMainMenu","courtMainMenu");
    }

    /**
     * petitionUpdate
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward submitPetitionUpdate(ActionMapping aMapping,ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setPrevAction(form.getPrevAction());
	
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
	    return aMapping.findForward(UIConstants.PETITION_UPDATE_DISPLAY);
	} else
	{
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
			if (!petition.getReferralNum().equals(form.getReferralNumber())){
			    petReferralNumList.add(petition.getReferralNum());
			}
		    }
		}
	    }
	}
	if (petitionJuvCount > 0)
	{
	    form.setAction("validateOffenseCd");
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "A different juvenile " + duplicateJuvenile + " already assigned to this petition number"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.PETITION_UPDATE_DISPLAY);
	}
	
	
	// #83982 retrieve highest seqNum for that referral and juvenile for the creation of new record.
	  List<PetitionResponseEvent> petitions = JuvenileDistrictCourtHelper.getPetitionsByJuvAndRefNum(form.getJuvenileNumber(),form.getReferralNumber());
	    if(petitions!=null){
		Iterator<PetitionResponseEvent> petitionsItr = petitions.iterator();
		if(petitionsItr.hasNext()){
		    PetitionResponseEvent responseEvent =  petitionsItr.next();
		    if(responseEvent!=null){
			form.setPetitionSeqNum(responseEvent.getSequenceNum());//add CJIS too here
			form.setCJISNum(responseEvent.getPetCJISNum());
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
	//bug fix for 143175 - severity was saving as the severity of the previous -now it refreshes everytime
	/*if (form.getPetitionAllegationSev() == null || (form.getPetitionAllegationSev()!=null && form.getPetitionAllegationSev().isEmpty()))
	{*/
	    if (jocEvent != null)
	    {
		form.setPetitionAllegationSev(jocEvent.getSeverity());
	    }
	//}
	event.setSeverity(form.getPetitionAllegationSev());
	event.setAmend(form.getPetitionAmendment());
	event.setPetitionDate(DateUtil.stringToDate(form.getFilingDate(), DateUtil.DATE_FMT_1));
	event.setPetitionNum(form.getPetitionNumber());
	event.setStatus(form.getPetitionStatus());
	//depending on petition status add cjis
	if(form.getPetitionStatus().equalsIgnoreCase("F"))
	    event.setCJISNum(form.getCJISNum());
	event.setType(form.getPetitionType());
	event.setSequenceNum(form.getPetitionSeqNum());
	event.setAmendmentDate(DateUtil.stringToDate(form.getAmendmentDate(), DateUtil.DATE_FMT_1));
	event.setJuvenileNum(form.getJuvenileNumber());
	event.setReferralNum(form.getReferralNumber());

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
	    } catch (GeneralFeedbackMessageException e)
	    {
		e.printStackTrace();
	    }
	}

	if (resp.getUpdateFlag() != null && resp.getUpdateFlag().equalsIgnoreCase("true"))
	{
	    // petitionInformation.
	    ActionMessages messageHolder = new ActionMessages();
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.recordSuccesfullyUpdated"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	    form.setAction("submitPetitionUpdate");
	}
	//}
	form.setCurrAction("FromInitialSetting");
	return aMapping.findForward(UIConstants.PETITION_UPDATE_DISPLAY); 
    }
    
    /**
     * deletePetition
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward deletePetition(ActionMapping aMapping,ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	
	String juvNum = form.getJuvenileNumber();
	String refNum = form.getReferralNumber();
	String petitionNum = form.getPetitionNumber();
	String chainNum = form.getChainNumber();
	String seqNum = form.getPetitionSeqNum();

	DeleteJuvenilePetitionInformationEvent deletePetitionEvt = (DeleteJuvenilePetitionInformationEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.DELETEJUVENILEPETITIONINFORMATION);
	deletePetitionEvt.setJuvenileNum(juvNum);
	deletePetitionEvt.setChainNum(chainNum);
	deletePetitionEvt.setReferralNum(refNum);
	deletePetitionEvt.setSeqNum(seqNum);
	deletePetitionEvt.setPetitionNum(petitionNum);

	CompositeResponse compositeResponse = MessageUtil.postRequest(deletePetitionEvt);
	PetitionResponseEvent resp = (PetitionResponseEvent) MessageUtil.filterComposite(compositeResponse, PetitionResponseEvent.class);
	Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	if (errorResponse != null)
	{
	    ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
	    try
	    {
		handleFatalUnexpectedException(myEvt.getMessage());
	    } catch (GeneralFeedbackMessageException e)
	    {
		e.printStackTrace();
	    }
	}
	if (resp.getDeleteFlag() != null && resp.getDeleteFlag().equalsIgnoreCase("true"))
	{
	    // petitionInformation.
	    form.setAction("deletePetition");
	    ActionMessages messageHolder = new ActionMessages();
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.recordSuccesfullyDeleted"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);

	    String title = "Petition Deleted.";
	    StringBuffer comments = new StringBuffer();
	    comments.append("The petition identified by Petition#: ")
        	    .append(form.getPetitionNumber())
        	    .append("and associated to Referral# ")
        	    .append(form.getReferralNumber())
        	    .append(" has been deleted.");

	    // get supervision number from jccasefile assignment .
	    String supervisionNumber = "";
	    JuvenileProfileReferralListResponseEvent profileResp = JuvenileFacilityHelper.getCasefilesFromReferral(form.getJuvenileNumber(), form.getReferralNumber());
	    Collection<JuvenileCasefileReferral> casefilesResp = profileResp.getCasefileReferrals();
	    if (casefilesResp != null && !casefilesResp.isEmpty())
	    {
		// sorts in descending order by assignment Date num.Get the most
		// recent record.
		Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
		    @Override
		    public int compare(JuvenileCasefileReferral evt1,
			    JuvenileCasefileReferral evt2)
		    {
			if (evt1.getCaseFile() != null && evt1.getCaseFile().getAssignmentAddDate() != null && evt2.getCaseFile() != null && evt2.getCaseFile().getAssignmentAddDate() != null)
			    return evt1.getCaseFile().getAssignmentAddDate().compareTo(evt1.getCaseFile().getAssignmentAddDate());
			else
			    return -1;
		    }
		}));

		Iterator<JuvenileCasefileReferral> casefileItr = casefilesResp.iterator();
		while (casefileItr.hasNext())
		{
		    JuvenileCasefileReferral responseEvt = casefileItr.next();
		    if (responseEvt != null)
		    {
			String supervisionCategory;
			try
			{
			    // check for the pre-adjudication category.
			    supervisionCategory = JuvenileDistrictCourtHelper.getSupCatFromType(responseEvt.getSupervisionTypeCd());
			    if (PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_PRE_ADJ.equalsIgnoreCase(supervisionCategory) && responseEvt.getCaseStatusCd().equalsIgnoreCase("A"))
			    {
				supervisionNumber = responseEvt.getCaseFileId();
				JuvenileFacilityHelper.createActivity(supervisionNumber, ActivityConstants.ACTIVITY_CATEGORY_PETITION, ActivityConstants.ACTIVITY_TYPE_JUDICIAL, ActivityConstants.PETITION_DELETED, title, comments.toString(),null,DateUtil.getCurrentDate(),null);
				break;
			    }
			} catch (RuntimeException e)
			{
			    e.printStackTrace();
			} catch (Exception e)
			{
			    e.printStackTrace();
			}
		    }
		}
	    }/*else{ if needed we will add this back.
		// show the following message.
		messageHolder = new ActionMessages();
		messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.deleteActivity"));
		aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
		saveMessages(aRequest, messageHolder);
	    }*/
	}
	return aMapping.findForward(UIConstants.PETITION_UPDATE_DISPLAY);
    }
    
    /**
     * courtMainMenu
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
 	   form.setCourtDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));
 	   form.setAction("petitionUpdate");
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
    public ActionForward validateOffenseCode(ActionMapping aMapping,
	    ActionForm aForm, HttpServletRequest aRequest,
	    HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setPrevAction(form.getPrevAction());
	form.setCurrAction("FromInitialSetting");
	JuvenileOffenseCodeResponseEvent jocEvent = JuvenileDistrictCourtHelper.validateOffenseCd(form.getPetitionAllegation());
	if (jocEvent != null)
	{
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
	}
	return aMapping.findForward(UIConstants.PETITION_UPDATE_DISPLAY);
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
    public ActionForward goToOffenseSearch(ActionMapping aMapping,ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setPrevAction(form.getPrevAction());
	form.setCurrAction("FromInitialSetting");
	form.setAction("petitionUpdate");
	form.setConfirmMsg(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setAlphaCodeId(UIConstants.EMPTY_STRING);
	form.setShortDesc(UIConstants.EMPTY_STRING);
	form.setDpsCodeId(UIConstants.EMPTY_STRING);
	form.setCategoryId(UIConstants.EMPTY_STRING);
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setOffenseResultList(new ArrayList());
	// This code to retrieve code table data from JJS code table is based on
	// code from 'Search Code Table' left Nav option
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
	String discontCode =  UIConstants.EMPTY_STRING; // U.S 58355
	for (int x = 0; x < temp1.size(); x++)
	{
	    CodetableDataResponseEvent ctrEvent = (CodetableDataResponseEvent) temp1.get(x);
	    ocEvent = new JuvenileCasefileOffenseCodeResponseEvent();
	    eventValues = CollectionUtil.iteratorToList(ctrEvent.getValueMap().values().iterator());
	    inactive = UIConstants.EMPTY_STRING;
	    discontCode =  UIConstants.EMPTY_STRING; // U.S 58355
	    // value of 'y' is based on DISPLAYORDER value in CODETBLREGATTR
	    // table for CODETBLREG_ID = 27
	    for (int y = 1; y < eventValues.size(); y++)
	    {
		if (y == 1)
		{
		    ocEvent.setAlphaCode(eventValues.get(y).toString());
		}
		if (y == 2)
		{
		    ocEvent.setNumericCode(eventValues.get(y).toString());
		}
		if (y == 3)
		{
		    ocEvent.setCategory(eventValues.get(y).toString());
		}
		if (y == 4)
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
		//// U.S 58355
		if (y == 16)
		{
		    discontCode = eventValues.get(y).toString();
		}
		// U.S 58355
	    }
	    if (UIConstants.EMPTY_STRING.equals(inactive) && UIConstants.EMPTY_STRING.equals(discontCode))  // U.S 58355
	    {
		temp2.add(ocEvent);
	    }
	    
	}*/
	GetAllJuvenileOffenseCodesEvent requestEvent = (GetAllJuvenileOffenseCodesEvent) EventFactory
						.getInstance(CodeTableControllerServiceNames.GETALLJUVENILEOFFENSECODES);
	List<JuvenileCasefileOffenseCodeResponseEvent> codes = MessageUtil.postRequestListFilter(requestEvent, JuvenileCasefileOffenseCodeResponseEvent.class);
	List<JuvenileCasefileOffenseCodeResponseEvent> filteredCodes = new ArrayList<>();
	for ( JuvenileCasefileOffenseCodeResponseEvent code : codes ) {
	    /*if ( !"Y".equalsIgnoreCase( code.getInactiveInd() )
		    &&  !"Y".equalsIgnoreCase( code.getDiscontCode() ) ) {*///commenting as the search offense screen need to show the invalid or discontinued message 174995
		filteredCodes.add( code );
	    //}
	}   
            	Collections.sort(filteredCodes);

	form.setCodetableDataList(filteredCodes);
	/*temp1 = null;
	temp2 = null;
	eventValues = null;
	inactive = null;*/

	return aMapping.findForward(UIConstants.COURT_OFFENSE_SUCCESS);
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
    public ActionForward printSubpoenas(ActionMapping aMapping,ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setPrevAction(form.getPrevAction());
	
	form.setCurrAction(form.getPrevAction());
	// do the subpoenas print.
	form.setAction("printSubpoenas");
	String subpoenaQueryString = aRequest.getParameter("data");
	form.setQueryString(subpoenaQueryString);

	if (form.getQueryString() != null && !form.getQueryString().isEmpty())
	{
	    form.setSubpoenaPrinted(true);
	    SubpoenaReportBean rptBean = new SubpoenaReportBean();
	    // 83982 set petition and amemdment information in the form
	    List<PetitionResponseEvent> petitions = JuvenileDistrictCourtHelper.getPetitionsByJuvAndRefNum(form.getJuvenileNumber(),form.getReferralNumber());
	    if(petitions!=null){
		Iterator<PetitionResponseEvent> petitionsItr = petitions.iterator();
		if(petitionsItr.hasNext()){
		    PetitionResponseEvent responseEvent =  petitionsItr.next();
		    if(responseEvent!=null){
			form.setPetitionStatus(responseEvent.getPetitionStatus());
			form.setPetitionType(responseEvent.getPetitionType());
			form.setPetitionNumber(responseEvent.getPetitionNum());
			form.setPetitionAmendment(responseEvent.getAmend());
			form.setPetitionAllegation(responseEvent.getOffenseCodeId());
			//court information
		    }
		}
	    }
	    List<DocketEventResponseEvent> courtDockets = JuvenileDistrictCourtHelper.getCourtDocket(form.getJuvenileNumber(), form.getReferralNumber());
	    if (courtDockets != null)
	    {
		Iterator<DocketEventResponseEvent> courtDocketsItr = courtDockets.iterator();
		if (courtDocketsItr.hasNext())
		{
		    DocketEventResponseEvent responseEvent = courtDocketsItr.next();
		    if (responseEvent != null)
		    {
			//court information
			form.setFilingDate(responseEvent.getFilingDate());
			form.setAmendmentDate(responseEvent.getPetitionAmendmentDate());
		    }
		}
	    }
	    JuvenileDistrictCourtHelper.populateSubpoenaReportBean(rptBean, form);

	    aRequest.getSession().setAttribute("reportInfo", rptBean);

	    // let the pdfManager know that the report should be saved in the
	    // request during report creation
	    BFOPdfManager pdfManager = BFOPdfManager.getInstance();

	    if (subpoenaQueryString.equalsIgnoreCase("J"))
	    { // JUVENILE
		if (form.getCert() != null && form.getCert().equalsIgnoreCase("Y"))
		{
		    if (rptBean.getCertCode().equalsIgnoreCase("54.02(J)"))
		    {
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_CERT_OVER17_JUVENILE);
		    } else
		    {
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_CERT_17_JUVENILE);
		    }
		} else
		{
		    pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_STD_JUVENILE); // std
		}
	    }
	    if (subpoenaQueryString.equalsIgnoreCase("F"))
	    {// FATHER
		if (rptBean.getMemberName() != null && !rptBean.getMemberName().getFormattedName().isEmpty())
		{
		    if (form.getCert() != null && form.getCert().equalsIgnoreCase("Y"))
		    {
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_CERT_GUARD_FATHER);
		    } else
		    {
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_STD_GUARD_FATHER); // std
		    }
		}
	    }
	    if (subpoenaQueryString.equalsIgnoreCase("M"))
	    { // MOTHER
		if (rptBean.getMemberName() != null && !rptBean.getMemberName().getFormattedName().isEmpty())
		{
		    if (form.getCert() != null && form.getCert().equalsIgnoreCase("Y"))
		    {
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_CERT_GUARD_MOTHER);
		    } else
		    {
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_STD_GUARD_MOTHER); // std
		    }
		}
	    }
	    if (subpoenaQueryString.equalsIgnoreCase("O"))
	    { // OTHER
		if (rptBean.getMemberName() != null && !rptBean.getMemberName().getFormattedName().isEmpty())
		{
		    if (form.getCert() != null && form.getCert().equalsIgnoreCase("Y"))
		    {
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_CERT_GUARD_OTHER);
		    } else
		    {
			pdfManager.createPDFReport(aRequest, aResponse, PDFReport.SUBPOENA_STD_GUARD_OTHER);// std
		    }
		}
	    }
	}
	return aMapping.findForward(UIConstants.PETITION_UPDATE_DISPLAY);
    }
    
    /**
     * Back Button.
	bug 64138
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	
	if(form.getPrevAction().equalsIgnoreCase("FromMainMenu")){
	    form.reset();
	    return aMapping.findForward(UIConstants.COURT_MAIN_MENU);
	}
	// check below added for referral Inquiry
	if(form.getPrevAction().equalsIgnoreCase("FromReferralInquiry")){
	   // form.reset();
	    return aMapping.findForward(UIConstants.REFERRAL_INQUIRY);
	}
	
	if(form.getCurrAction().equalsIgnoreCase("FromInitialSetting")){
	    form.setAction(form.getPrevAction());
	    String subpoenas[] = form.getSelectedSubpoenasToBePrinted();
	    StringBuffer buf = new StringBuffer();
	    if(subpoenas!=null && !form.getSelectedSubpoenas().equalsIgnoreCase("noSubpoenasSelected")){
		for (int i = 0; i < subpoenas.length; i++)
        	{
        	    String subpoena = subpoenas[i];
        	    buf.append(subpoena);
        	    if(i!=subpoenas.length-1){
        		    buf.append("-");
        	    }
        	}
        	form.setSelectedSubpoenas(buf.toString());
	    }else{
		form.setSelectedSubpoenas("");
	    }
	    return aMapping.findForward(UIConstants.INITIAL_SETTING_DISPLAY);
	}
	return null;
    }
}
