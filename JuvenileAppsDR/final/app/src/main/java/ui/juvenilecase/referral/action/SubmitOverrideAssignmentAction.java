package ui.juvenilecase.referral.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetJuvenileDispositionCodeEvent;
import messaging.codetable.GetJuvenileOffenseCodeEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileRefAssgnmtResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.referral.SaveJJSReferralEvent;
import messaging.referral.SaveOverrideAssignmentEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

import pd.codetable.Code;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.SupervisionTypeMap;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import pd.km.util.Name;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.contact.user.helper.UIUserFormHelper;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.referral.form.JuvenileReferralForm;
import ui.security.SecurityUIHelper;

/**
 * @author nemathew
 */
public class SubmitOverrideAssignmentAction extends LookupDispatchAction
{
    /**
     * Key Method Map
     */
    protected Map getKeyMethodMap()
    {
	Map<String, String> keyMap = new HashMap<String, String>();
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.submit", "submitOverrideAssignment");
	keyMap.put("button.changeAssessmentType", "loadSupervision");
	keyMap.put("button.validate", "validateJPO");
	keyMap.put("button.changeSuprCat", "loadSupervisionTypes");
	keyMap.put("button.refresh", "refresh");
	keyMap.put("button.back", "back");
	keyMap.put("button.updateReferral", "updateReferral");
	keyMap.put("button.nextOverride", "newOverride");
	keyMap.put("button.select", "selectOfficerCode");
	keyMap.put("button.changeSuprType", "changeSupervisionType");
	return keyMap;
    }

    private static int getAgeInYears(Date ageDate)
    {
	if (ageDate == null)
	{
	    return 0;
	}
	Calendar birthdate = Calendar.getInstance();
	birthdate.setTime(ageDate);
	Calendar now = Calendar.getInstance();

	int age = 0;
	age = now.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
	birthdate.add(Calendar.YEAR, age);
	if (now.before(birthdate))
	{
	    age--;
	}
	return age;
    }
    /**
     * Submit
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward submitOverrideAssignment(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setAction("overrideAssignment");
	String overrideEligible = "false";

	OfficerProfileResponseEvent officerResp = new OfficerProfileResponseEvent();
	if (form.getJpo() != null && !(form.getJpo().equalsIgnoreCase("UVANC") && form.getJpo().equalsIgnoreCase("UVREC")))
	{
	    officerResp = validateJPOEntered(form);
	    if (officerResp == null)
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile Probation Officer is invalid. Please modify"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}
	
	//JJSJuvenile jjsJuv = JJSJuvenile.find(form.getJuvenileNum());
	Date bDate = DateUtil.stringToDate(form.getDateOfBirth(),DateUtil.DATE_FMT_1);
	int getAgeInYears=getAgeInYears(bDate);
	if(getAgeInYears > 21 || getAgeInYears < 10)
	    {
		ActionErrors errors = new ActionErrors();
		 errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile's age exceeds assignment permissions. Casefile assignment is not permitted. Contact Data Corrections for assistance"));
		 saveErrors(aRequest, errors);
		 return aMapping.findForward(UIConstants.FAILURE);
	    }
	
	//To get the list of referrals selected to do the override
	String refNums[] = form.getSelectedRefToOverride();
	List<String> refOverrideList = Arrays.asList(refNums[0].split(","));
	ArrayList<JuvenileProfileReferralListResponseEvent> overrideReferralsList = new ArrayList<JuvenileProfileReferralListResponseEvent>();
	if (refOverrideList != null && refOverrideList.size() > 0){
	    for (int i = 0; i < refOverrideList.size(); i++)
	    {
		String refNum = refOverrideList.get(i);
		ArrayList<JuvenileProfileReferralListResponseEvent> tempList = (ArrayList<JuvenileProfileReferralListResponseEvent>) form.getReferralList();
		for( int x=0;x<tempList.size();x++){
		    
		    JuvenileProfileReferralListResponseEvent referralRespEvt = tempList.get(x);
			if (referralRespEvt != null && refNum.equals(referralRespEvt.getReferralNumber()))
			{
			    if( "C".equalsIgnoreCase( referralRespEvt.getCaseStatus() ) && !referralRespEvt.getSupervisionCategoryId().equalsIgnoreCase(form.getSupervisionCat())){
				
				 ActionErrors errors = new ActionErrors();
				    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Cannot Override an assignment on a closed casefile"));
				    saveErrors(aRequest, errors);
				    return aMapping.findForward(UIConstants.FAILURE);
				
			    }
			    overrideReferralsList.add(referralRespEvt);
			    break;
			}
		}
		
	    }
	}//ends: get the list of referrals selected to do the override
	
	String selectedSupervisionCat = form.getSupervisionCat();//
	
	Iterator assItr = overrideReferralsList.iterator();
	while(assItr.hasNext()){
	    JuvenileProfileReferralListResponseEvent respEve= (JuvenileProfileReferralListResponseEvent) assItr.next();
	    
	    if(form.getAssignmentType().equalsIgnoreCase("TRN")){
		JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode",respEve.getOffense());
		if(offCode.getSeveritySubtype()!=null && !offCode.getSeveritySubtype().equalsIgnoreCase("T"))
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "This assignment type requires an out of state or out of county level offense: TRNSIN, TRNSDP, ISCOIN OR REGION. Please check assignment type"));
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
	    }
	    
	}
	
	
	//US 86648
	Iterator overrideRefsItr4 = overrideReferralsList.iterator();
	while (overrideRefsItr4.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent overrideRefRespEvt = (JuvenileProfileReferralListResponseEvent) overrideRefsItr4.next();
	    if(overrideRefRespEvt != null && overrideRefRespEvt.getIntakeDecisionId()== null || overrideRefRespEvt.getIntakeDecDate() == null || overrideRefRespEvt.getIntakeDecisionId().equalsIgnoreCase("") || overrideRefRespEvt.getIntakeDecDate().equalsIgnoreCase("")){
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "One or more referrals has missing referral details.  Please update required details before assignment"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }

	}
	
	//if there are multiple referrals for override processing, the supervision Type associated to all the referrals should be equivalent Section 4.8.11
	if (overrideReferralsList != null && overrideReferralsList.size() > 1)
	{
	    Iterator overrideRefsItr = overrideReferralsList.iterator();
	    String prevSupCat = "";
	    int i=0; 		 //counter to eliminate the compare for the first record
	    while (overrideRefsItr.hasNext())
	    {
		JuvenileProfileReferralListResponseEvent overrideRefRespEvtTemp = (JuvenileProfileReferralListResponseEvent) overrideRefsItr.next();
		String currSupCatId = overrideRefRespEvtTemp.getSupervisionCategoryId();
		if(i>=1){
		   if(currSupCatId!= null && !currSupCatId.equalsIgnoreCase(prevSupCat)) {
		       ActionErrors errors = new ActionErrors();
		       errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Only one supervision category can be overridden per transaction"));
		       saveErrors(aRequest, errors);	
		       return aMapping.findForward(UIConstants.FAILURE);
		   }
		}
		i++;
		prevSupCat = currSupCatId;
	    }
	} //end of section 4.8.10
	
	// check if the selected referral number have an assignment with the (selected) supervision category  in its history, 
	//which is identified as a parallel assignment
	Iterator overrideRefsItr2 = overrideReferralsList.iterator();
	while (overrideRefsItr2.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent overrideRefRespEvt = (JuvenileProfileReferralListResponseEvent) overrideRefsItr2.next();
	    JuvenileProfileReferralListResponseEvent refCasefileListRespEvt = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(form.getJuvenileNum(), overrideRefRespEvt.getReferralNumber());
	    
	    if ( "PC".equalsIgnoreCase(selectedSupervisionCat) )
	    {
		if (overrideRefRespEvt.getSupervisionCategoryId() != null && overrideRefRespEvt.getSupervisionCategoryId().equalsIgnoreCase("RC"))
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Supervision entry does not qualify for override.  Please contact Data Corrections"));
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
		else
		{
		    overrideEligible = "true"; //91085
		}
	    }
	
	    Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileListRespEvt.getCasefileReferrals();
	    Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
	    while (caseFileRefItr.hasNext())
	    {
		JuvenileCasefileReferral casefileReferral = (JuvenileCasefileReferral) caseFileRefItr.next();
		if (selectedSupervisionCat.equalsIgnoreCase("PC"))
		{
		    /**
		     * 4) [US 87861] If the entry supervision Assignment Type =
		     * Post Court, and the selected referral(s) does/do not
		     * include history of supervision assignment type or
		     * supervision category = Pre=Adjudication, then do not
		     * permit assignment. This is considered an illogical
		     * sequence. A Pre-Adjudication (court) level supervision
		     * should precede a Post-Court level supervision category.
		     */

		    if (casefileReferral.getSupervisionCat() != null && casefileReferral.getSupervisionCat().equalsIgnoreCase("AD"))
		    {
			overrideEligible = "true";
			break;
		    }
		    else
		    {
			overrideEligible = "false";
		    }
		}
		else
		{
		    if (casefileReferral != null && casefileReferral.getSupervisionCat() != null)
		    {
			if (casefileReferral.getSupervisionCat().equalsIgnoreCase(selectedSupervisionCat))
			{
			    overrideEligible = "true";
			    break;
			}
			else
			{
			    overrideEligible = "false";
			}
		    }
		}
	    } //while(1)
	    
	    if (selectedSupervisionCat.equalsIgnoreCase("PC"))
	    {
		//87861
		/**
		 * 5) [US 87861] If the entry Assignment Type = Post Court, and
		 * the selected referral(s) includes a court disposition,
		 * {JUVENILE_REFERRAL.CourtDisposition} verify the court
		 * decision option code. If the court decision/disposition is
		 * considered a final decision code (Disposition has Option =
		 * ‘F’ based on Court Decisions, JJS90.2), then do no permit
		 * referral assignment. Display on screen message: A final Court
		 * Decision is associated to the selected referral(s). Please
		 * create the correct Post-Adjudicated level assignment.
		 */
		if (overrideRefRespEvt.getCourtDisposition() != null && !overrideRefRespEvt.getCourtDisposition().equalsIgnoreCase(""))
		{
		    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		    GetJuvenileDispositionCodeEvent courtDispEvt = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
		    courtDispEvt.setCode(overrideRefRespEvt.getCourtDisposition());
		    dispatch.postEvent(courtDispEvt);
		    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		    List<JuvenileDispositionCodeResponseEvent> courtDispResps = (List<JuvenileDispositionCodeResponseEvent>) MessageUtil.compositeToCollection(compositeResponse, JuvenileDispositionCodeResponseEvent.class);

		    if (courtDispResps != null)
		    {
			Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = courtDispResps.iterator();
			if (juvCodeIter.hasNext())
			{
			    JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
			    if (dispResp != null && dispResp.getOptionCode() != null && dispResp.getOptionCode().equalsIgnoreCase("F"))
			    {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "A final Court Decision is associated to the selected referral(s).  Please create the correct Post-Adjudicated level assignment"));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.FAILURE);
			    }
			}
		    }
		}
	    }
	}

	if (overrideEligible.equalsIgnoreCase("false"))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Supervision entry does not qualify for override.  Please contact Data Corrections"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	} //end of section 4.8.10
	

	
	Iterator overrideRefsItr3 = overrideReferralsList.iterator();
	while (overrideRefsItr3.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent overrideRefRespEvt = (JuvenileProfileReferralListResponseEvent) overrideRefsItr3.next();
	    
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    SaveJJSReferralEvent saveEvent = (SaveJJSReferralEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.SAVEJJSREFERRAL);
	    saveEvent.setActionFlag("fromOverRide");
	    saveEvent = setSaveDetails(saveEvent, form, overrideRefRespEvt); //JuvenileReferralHelper.setSaveDetails(saveEvent, form, overrideRefRespEvt);
	    dispatch.postEvent(saveEvent);
	    CompositeResponse response = (CompositeResponse) dispatch.getReply();
	    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
	    if (error != null)
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", error.getMessage()));
		if (error.getErrorLogId() != null && error.getErrorLogId().equalsIgnoreCase("notificationError"))
		{
		    //do nothing
		}else{
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.FAILURE); // as per Cassandra any failure in sending notifications or MAYSI should still continue with assignment creation / update
		}
	    }
	    
	    //update JJSREFERRAL FOR #89887
	    SaveOverrideAssignmentEvent saveEvt = (SaveOverrideAssignmentEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.SAVEOVERRIDEASSIGNMENT);
	    saveEvt.setReferralId( overrideRefRespEvt.getReferralOID());

	    if(saveEvent.getJpoID()!=null && !saveEvent.getJpoID().equalsIgnoreCase("")){
		saveEvt.setJpoID(saveEvent.getJpoID());
	    }
	    if(saveEvent.getCtAssignJPOId()!=null && !saveEvent.getCtAssignJPOId().equalsIgnoreCase("")){
		saveEvt.setCtAssignJPOId(saveEvent.getCtAssignJPOId());
	    }
	    if(saveEvent.getProbJPOId()!=null && !saveEvent.getProbJPOId().equalsIgnoreCase("")){
		saveEvt.setProbJPOId(saveEvent.getProbJPOId());
	    }

	    
	    //generate Override Assignment Activity 4.8.12.4
	    JuvenileCasefileReferralResponseEvent casefileRefResp = (JuvenileCasefileReferralResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileReferralResponseEvent.class);
	    String logonId = SecurityUIHelper.getLogonId();//get the user logged in
	    if (casefileRefResp != null)
	    {
		if (casefileRefResp.getCaseFileId() != null)
		{
		    Name fullName = new Name(form.getFirstName(), form.getMiddleName(), form.getLastName(), form.getNameSuffix());
		    StringBuffer formattedName = new StringBuffer(fullName.getFormattedName());
		    String comment = "Referral " + casefileRefResp.getReferralNumber() +  " has been overridden for Juvenile: " + casefileRefResp.getJuvenileNum() + ", " + formattedName.toString() + ". Referral Record Overridden by: " + logonId + "' " + DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1);
		   // UIJuvenileHelper.createReferralOverrideActivity(casefileRefResp.getCaseFileId(), comment);
		}
	    }
	}

	ActionMessages messageHolder = new ActionMessages();
	if(form.getJpo().equalsIgnoreCase("UVANC") || form.getJpo().equalsIgnoreCase("ANC")){
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.overrideRefSuccessAlt"));
	}else {
	messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.overrideRefSuccess"));
	}
	aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	saveMessages(aRequest, messageHolder);
	form.setAction("overrideSuccess");
	//return aMapping.findForward(UIConstants.SUCCESS);
	return refreshPage(aMapping, aForm, aRequest, aResponse);

    }  
    
    /**
     * @param saveEvt
     * @param form
     * @param respEvent
     * @return
     */
    private SaveJJSReferralEvent setSaveDetails (SaveJJSReferralEvent saveEvt, JuvenileReferralForm form, JuvenileProfileReferralListResponseEvent respEvent)
    {
	saveEvt.setReferralNum(respEvent.getReferralNumber());
	saveEvt.setReferralSource(respEvent.getReferralSource());
	saveEvt.setIntakeDate(DateUtil.stringToDate(respEvent.getIntakeDecDate(), DateUtil.DATE_FMT_1));	
	saveEvt.setIntakeDecisionId(respEvent.getIntakeDecisionId());
	saveEvt.setOffenses(respEvent.getOffenses());
	saveEvt.setOffenseTotal(respEvent.getOffenses().size()+"");
	saveEvt.setJuvenileNum(form.getJuvenileNum());
	saveEvt.setCasefileGenerate(form.getCasefileGenerate()); 
	saveEvt.setTJJDReferralDate(form.getTJJDReferralDate());
	saveEvt.setReferralTypeInd(form.getFormalReferralType());
	saveEvt.setAssignmentType(form.getAssignmentType());
	saveEvt.setSupervisionCat(form.getSupervisionCat());
	saveEvt.setSupervisionType(form.getSupervisionType());
	saveEvt.setAssignmentDate(DateUtil.stringToDate(form.getAssignmentDateStr(), DateUtil.DATE_FMT_1));
	//89887 - 88414
	if (form.getAssignmentType() != null)
	{

	    /**
	     * At point of referral assignment, insert the Officer ID code into
	     * the JJS_MS_REFERRAL table, based on the following conditions: If
	     * the Assignment Type falls into Pre-Petition category insert
	     * assigned JPO code into Intake Officer (INASSIGNJPOID) column.
	     * This applies to the following Assignment Type(s): INTAKE
	     */
	    if (form.getAssignmentType().equalsIgnoreCase("INT"))
	    {
		if (form.getSupervisionCat() != null)
		{
		    if (form.getSupervisionCat().equalsIgnoreCase("PP"))
		    {
			saveEvt.setJpoID(form.getJpo());
		    }
		}
	    }
	    /**
	     * If the Assignment Type falls into Pre-Adjudication category,
	     * insert assigned JPO code into the Court Officer (CTASSIGNJPOID)
	     * column. This applies to the following Assignment Type(s):
	     * PRE-ADJUDICATION
	     */
	    else if (form.getAssignmentType().equalsIgnoreCase("PRE"))
	    {
		if (form.getSupervisionCat() != null)
		{
		    if (form.getSupervisionCat().equalsIgnoreCase("AD"))
		    {
			saveEvt.setCtAssignJPOId(form.getJpo());
		    }else{
		    	saveEvt.setJpoID(form.getJpo());
		    }
		}
	    }

	    /**
	     * If the Assignment Type falls into Post-Adjudication category,
	     * insert assigned JPO code into the Probation Officer
	     * (PROBATIONJPOID) column. This applies to the following Assignment
	     * Type(s): POST-ADJUDICATION
	     */

	   else if (form.getAssignmentType().equalsIgnoreCase("PAD"))
	    {
		if (form.getSupervisionCat() != null)
		{
		    if (form.getSupervisionCat().equalsIgnoreCase("AR") || form.getSupervisionCat().equalsIgnoreCase("AC"))
		    {
			saveEvt.setProbJPOId(form.getJpo());
		    }else{
		    	saveEvt.setJpoID(form.getJpo());
		    }
		}
	    }
	    /**
	     * If Assignment Type equals TRANSFERRED OFFENSE (OOC), then refer
	     * to Supervision Category to determine proper insertion. If
	     * Supervision Category = PRE-PETITION, then insert assigned JPO
	     * code into the Intake Officer (INASSIGNJPOID) column. If
	     * Supervision Category = PRE-ADJUDICATION, then insert assigned JPO
	     * code into the Court Officer (CTASSINGJPOID) column. If
	     * Supervision Category = POST-ADJUDICATION COMMUNITY or
	     * POST-ADJUDICATION RESIDENTIAL, then insert JPO code into
	     * Probation Officer (PROBATIONJPOID) column.
	     */

	   else if (form.getAssignmentType().equalsIgnoreCase("TRN"))
	    {
		if (form.getSupervisionCat() != null)
		{
		    if (form.getSupervisionCat().equalsIgnoreCase("PP"))
		    {
			saveEvt.setJpoID(form.getJpo());
		    }else if(form.getSupervisionCat().equalsIgnoreCase("AD")){
			saveEvt.setCtAssignJPOId(form.getJpo());
		    }else if(form.getSupervisionCat().equalsIgnoreCase("AC") || form.getSupervisionCat().equalsIgnoreCase("AR")){
			saveEvt.setProbJPOId(form.getJpo());
		    }else{
		    	saveEvt.setJpoID(form.getJpo());
		    }
		}
	    }else{
	       saveEvt.setJpoID(form.getJpo());
	    }
	}
	
	//User-story 8814
	saveEvt.setSubsequentCasefileId(form.getSubsequentCasefileId());
	saveEvt.setOverrideReason(form.getOverrideReasonStr());
	saveEvt.setOverrideOtherComment(form.getOverrideOTHComments());
	return saveEvt;
    }

    /**
     * Submit
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward validateJPO(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;	
	//Validate the jpo
	OfficerProfileResponseEvent resp = validateJPOEntered(form);
	if(resp == null)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile Probation Officer is invalid. Please modify"));
	    saveErrors(aRequest, errors);
	    if ("manageAssignment".equalsIgnoreCase(form.getAction()))
	    {
		return aMapping.findForward(UIConstants.MANAGE_ASSIGNMENT);
	    }
	    else
	    {
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}
	else
	{
	    ActionMessages messageHolder = new ActionMessages();	   
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.jpoValid"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);	   
	}
	if ("manageAssignment".equalsIgnoreCase(form.getAction())){
	    return aMapping.findForward(UIConstants.MANAGE_ASSIGNMENT);
	}else{
    	form.setAction("overrideAssignment");
    	return aMapping.findForward(UIConstants.SUCCESS);
	}
    }  
    
    /**
     * @param form
     * @return
     */
    private OfficerProfileResponseEvent validateJPOEntered(JuvenileReferralForm form)
    {
	
	if(form.getJpo()!= null && !form.getJpo().equals("") && form.getJpo().length()<= 3)	
	{
	    form.setJpo("UV" + form.getJpo().toUpperCase()); //bug 84455
	}else{
	    form.setJpo(form.getJpo().toUpperCase());//BUG 84455
	}
	if ("manageAssignment".equalsIgnoreCase(form.getAction()) || "manageAssignmentfromRefBrief".equalsIgnoreCase(form.getAction()))
	{
	    form.setAction("manageAssignment");
	}
	else
	{
	    form.setAction("overrideAssignment");
	}
	//Validate the jpo
	OfficerProfileResponseEvent officerProfile = UIUserFormHelper.getUserOfficerProfile(form.getJpo());
	return officerProfile;
    }
    /**
     * loadSupervision
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward loadSupervision(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setSupervisionCat("");
	form.setSupervisionType("");
	form.setSupervisionTypes(new ArrayList());
	form.setSupervisionCategories(new ArrayList());
	if ("manageAssignment".equalsIgnoreCase(form.getAction()) || "manageAssignmentfromRefBrief".equalsIgnoreCase(form.getAction()))
	{
	    form.setAction("manageAssignment");
	}
	else
	{
	    form.setAction("overrideAssignment");
	}
	if(form.getAssignmentType()!=null && form.getAssignmentType().equalsIgnoreCase("ADM"))
	{
	    //GET ALL SUPERVISION CATEGORIES AND TYPES
	    form.setSupervisionCategories(CodeHelper.getSupervisionCategories());
	    form.setSupervisionTypes(CodeHelper.getSupervisionTypes());
	    form.setDisbleAssignment("Y");
	    form.setSupervisionCat("PP");
	    form.setSupervisionType("ISS");
	    form.setJpo("UVANC");
	    form.setCasefileGenerate("N");//as in Create Referral section  
	}else if(form.getAssignmentType()!=null && form.getAssignmentType().equalsIgnoreCase("SBQ"))
	{
	    JuvenileReferralHelper.jpoOfRecord(form.getJuvenileNum(), form);
	    if(form.getSubsequentCasefileId()==null || form.getSubsequentCasefileId().equals(""))
	    {
		 ActionErrors errors = new ActionErrors();
		 errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "An Active Supervision is Required to use Assignment Type: Subsequent"));
		 saveErrors(aRequest, errors);
		 if (form.getAction().equalsIgnoreCase("manageAssignment"))
		 {
		     return aMapping.findForward("manageAssignment");
		 }
		 else
		 {
		     return aMapping.findForward(UIConstants.FAILURE);
		 }
	    }
	}
	else if(form.getAssignmentType()!=null && (form.getAssignmentType().equalsIgnoreCase("TRN") ))
	{
	   
	   //go through list and set description with code
	   Iterator iter = CodeHelper.getActiveCodes(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, true).iterator();
	   ArrayList tempArray = new ArrayList();
	   while(iter.hasNext())
	   {
	       CodeResponseEvent resp = (CodeResponseEvent) iter.next();
	       resp.setDescriptionWithCode(resp.getCode() + "-" + resp.getDescription());
	       tempArray.add(resp);
	   }
	   Collections.sort(tempArray);
	   form.setSupervisionCategories(tempArray);
	  // form.setSupervisionTypes(CodeHelper.getSupervisionTypes());	
	   form.setJpo("");
	} 
	else
	{
	    List categories = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_CATEGORY");	   
	    Iterator<JuvenileCodeTableChildCodesResponseEvent> iter = categories.iterator();
	    ArrayList tempCategories = new ArrayList();
	    while(iter.hasNext())
	    {
		JuvenileCodeTableChildCodesResponseEvent resp = iter.next();
		resp.setDescriptionWithCode(resp.getCode() + "-" + resp.getDescription());
		if(resp.getParentId()!=null && resp.getParentId().equals(form.getAssignmentType()))
		{
		    tempCategories.add(resp);
		   
		    if(resp.getCode().equalsIgnoreCase("PP") || resp.getCode().equalsIgnoreCase("ID") || resp.getCode().equalsIgnoreCase("AD") || resp.getCode().equalsIgnoreCase("DA"))
		    {
			Iterator<SupervisionTypeMap> supervisioncategoryIter = SupervisionTypeMap.findAll("supervisionCatId", resp.getCode());
			HashMap<String, CodeResponseEvent> sMap = new HashMap<String, CodeResponseEvent>();
			SupervisionTypeMap sTypeMap = new SupervisionTypeMap();
			  while (supervisioncategoryIter.hasNext())
			    {
			      sTypeMap = (SupervisionTypeMap)supervisioncategoryIter.next();
			      Code code = Code.find(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, sTypeMap.getSupervisionTypeId());
			      if(code.getStatus()!=null && (code.getStatus().equalsIgnoreCase("ACTIVE") || code.getStatus().equalsIgnoreCase("A")))
			      {
				  CodeResponseEvent myVal = new CodeResponseEvent();
				  myVal.setCode(code.getCode());
				  myVal.setDescription(code.getDescription());
				  myVal.setDescriptionWithCode(code.getCode() + "-" + code.getDescription());
				  sMap.put(myVal.getCode(), myVal);
			      }
			    }
			  List<CodeResponseEvent> mapValues = new ArrayList<CodeResponseEvent>(sMap.values());
			  Collections.sort(mapValues);
			  form.setSupervisionTypes(mapValues);
			  
		    }
		}
	    }	
	    if(tempCategories.size()==1)
		form.setSupervisionCat(((JuvenileCodeTableChildCodesResponseEvent)tempCategories.get(0)).getCode());
	    Collections.sort(tempCategories);
	    form.setSupervisionCategories(tempCategories);
	    if(form.getAssignmentType()!=null && !(form.getAssignmentType().equalsIgnoreCase("INT") || form.getAssignmentType().equalsIgnoreCase("IAW") || form.getAssignmentType().equalsIgnoreCase("PRE") || form.getAssignmentType().equalsIgnoreCase("DAD")))
	    {
        	    List types = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_TYPE");
        	    Iterator<JuvenileCodeTableChildCodesResponseEvent> typesIter = types.iterator();
        	    ArrayList tempTypes = new ArrayList();
        	    while(typesIter.hasNext())
        	    {
        		JuvenileCodeTableChildCodesResponseEvent resp = typesIter.next();
        		if(resp.getParentId()!=null && resp.getParentId().equals(form.getSupervisionCat()))
        		{
        		    resp.setDescriptionWithCode(resp.getCode() + "-" + resp.getDescription());
        		    tempTypes.add(resp);
        		    //commented below for looping through all and not defaulting to any- bug fix for 130627
        		    //form.setSupervisionType(resp.getCode());        		    
        		    //break;
        		}
        	    }	   
        	    Collections.sort(tempTypes);
        	    form.setSupervisionTypes(tempTypes);
	    }
	    if(form.getAssignmentType()!=null && form.getAssignmentType().equalsIgnoreCase("REC")){
		 form.setJpo("UVREC");
		 form.setCasefileGenerate("N");
	    } else {
		 form.setJpo("");//TODO need to check is this else part is needed for all the other cases 
	    }
	}	
	//get the casefilegenerate flag for the assignment type
	Iterator assgnmntTypeIter = form.getAssignmentTypes().iterator();
	while(assgnmntTypeIter.hasNext())
	{
	    JuvenileRefAssgnmtResponseEvent resp = (JuvenileRefAssgnmtResponseEvent)assgnmntTypeIter.next();
	    if(resp.getCode()!= null && resp.getCode().equalsIgnoreCase(form.getAssignmentType()))
	    {
		form.setCasefileGenerate(resp.getCasefileGenerate());
		break;
	    }
	}
	if (form.getAction().equalsIgnoreCase("manageAssignment"))
	{
	    return aMapping.findForward("manageAssignment");
	}
	else
	{
	    return aMapping.findForward("overrideAssignment");
	}
	    
	
    }    
    
    /**
     * Submit
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward loadSupervisionTypes(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	if ("manageAssignment".equalsIgnoreCase(form.getAction()) || "manageAssignmentfromRefBrief".equalsIgnoreCase(form.getAction())){
	    form.setAction("manageAssignment");
	}
	else {
	    form.setAction("overrideAssignment");
	}
	Iterator<SupervisionTypeMap> supervisioncategoryIter = SupervisionTypeMap.findAll("supervisionCatId", form.getSupervisionCat());
	HashMap supTypeMap = new HashMap();
	while (supervisioncategoryIter.hasNext())
	{
	    SupervisionTypeMap typeMap = (SupervisionTypeMap) supervisioncategoryIter.next();
	    Code code = Code.find(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, typeMap.getSupervisionTypeId());
	    if (code.getStatus() != null && (code.getStatus().equalsIgnoreCase("ACTIVE") || code.getStatus().equalsIgnoreCase("A")))
	    {
		CodeResponseEvent myVal = new CodeResponseEvent();
		myVal.setCode(code.getCode());
		myVal.setDescription(code.getDescription());
		myVal.setDescriptionWithCode(code.getCode() + "-" + code.getDescription());
		supTypeMap.put(myVal.getCode(), myVal);
	    }
	}
	List<CodeResponseEvent> mapValues = new ArrayList<CodeResponseEvent>(supTypeMap.values());
	Collections.sort(mapValues);
	form.setSupervisionTypes(mapValues);

	if (form.getAction().equalsIgnoreCase("manageAssignment"))
	{
	    return aMapping.findForward("manageAssignment");
	}
	else
	{
	    return aMapping.findForward(UIConstants.SUCCESS);
	}

    }
   
     /**
     * Cancel
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
	return forward;
    }

    /**
     * refresh 
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setAction("overrideAssignment");
	form.setJpo("");
	form.setOverrideReasonStr("");
	form.setAssignmentDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	form.setAssignmentType("");
	form.setSupervisionCat("");
	form.setSupervisionType("");
	form.setOverrideOTHComments("");
	return aMapping.findForward(UIConstants.SUCCESS);
    }
    
    /**
     * Back
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward(UIConstants.BACK);
	return forward;
    }
   
    
    /**
     * @param str
     * @return
     */
    private boolean notNullNotEmptyString(String str)
    {
	return (str != null && (str.length() > 0));
    }

    /**
     * @param aRequest
     * @param msgKey
     * @param param
     */
    protected void sendToErrorPage(HttpServletRequest aRequest, String msgKey, String param)
    {
	ActionErrors errors = new ActionErrors();
	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msgKey, param));
	saveErrors(aRequest, errors);
    }
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward refreshPage(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm referralForm = (JuvenileReferralForm) aForm;
	Map<String, JuvenileProfileReferralListResponseEvent> referralsMap = new HashMap<String, JuvenileProfileReferralListResponseEvent>();
	Collection<JuvenileProfileReferralListResponseEvent> referralList = JuvenileFacilityHelper.getJuvReferralDetails(referralForm.getJuvenileNum());
	ArrayList<JuvenileProfileReferralListResponseEvent> openReferralsOnlyList = new ArrayList<JuvenileProfileReferralListResponseEvent>();
	Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referralList.iterator();
	while (referralIterator.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent referral = referralIterator.next();
	    if (referral != null && referral.getCloseDate() == null)
	    {
		String sevSubType="";
		//if (referral.getMostSevereOffense() != null && !("R").equalsIgnoreCase(referral.getMostSevereOffense().getSeveritySubtype()))
		//referral is not an administrative level offense, check the severitySubType of offense with SEQNUM = 1
		if (referral.getOffenses() != null)
		{ 
		    Iterator<JJSOffense> offenseItr2 = referral.getOffenses().iterator();
		    while (offenseItr2.hasNext())
		    {
			JJSOffense offense = offenseItr2.next();
			if (offense.getSequenceNum().equalsIgnoreCase("1"))
			{
			    sevSubType = offense.getOffenseCode().getSeveritySubtype();
			    break;
			}
			
		    }
		    if (! "R".equalsIgnoreCase(sevSubType)){
		 
		    //locate the petition record with the highest sequence number (JUVENILE_PETITION. PetitionSequenceNumber) associated to the Juvenile Number and Referral Number from the setting record.
		    //If there is an associated PETITION record, display the petition allegation. //of the highest seq Num 
		    List<PetitionResponseEvent> petitionResponses = InterviewHelper.getPetitionsRespForReferral(referralForm.getJuvenileNum(), referral.getReferralNumber());
		    if (petitionResponses != null && !petitionResponses.isEmpty())
		    {
			Collections.sort((List<PetitionResponseEvent>) petitionResponses, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
			    @Override
			    public int compare(PetitionResponseEvent evt1, PetitionResponseEvent evt2)
			    {
				if (evt1.getSequenceNum() != null && evt2.getSequenceNum() != null)
				    return Integer.valueOf(evt2.getSequenceNum()).compareTo(Integer.valueOf(evt1.getSequenceNum()));
				else
				    return -1;
			    }
			}));	
			Iterator<PetitionResponseEvent> petitionRespItr = petitionResponses.iterator();
			while (petitionRespItr.hasNext())
			{
			    PetitionResponseEvent petitionResp = petitionRespItr.next();
			    if (petitionResp != null)
			    {
				referral.setPetitionNumber(petitionResp.getPetitionNum());
				referral.setOffense(petitionResp.getOffenseCodeId());
				referral.setOffenseDesc(petitionResp.getOffenseShortDesc());
				if (petitionResp.getOffenseCodeId() != null)
				{
				   // Code copied from HandleJuvenileProfileTransferredOffensesSelectionAction
				    GetJuvenileOffenseCodeEvent jocEvent = new GetJuvenileOffenseCodeEvent();
				    jocEvent.setAlphaCode(petitionResp.getOffenseCodeId());
				    List events = MessageUtil.postRequestListFilter(jocEvent, JuvenileOffenseCodeResponseEvent.class);
				    if (events != null & !events.isEmpty())
				    {
					for (int x = 0; x < events.size(); x++)
					{
					    JuvenileOffenseCodeResponseEvent respEvent = (JuvenileOffenseCodeResponseEvent) events.get(x);
					   
					    //if (!"Y".equals(respEvent.getInactiveInd()) && petitionResp.getOffenseCodeId().equalsIgnoreCase(respEvent.getOffenseCode()) && !(respEvent.getDpsOffenseCode().isEmpty()))
					    if (petitionResp.getOffenseCodeId().equalsIgnoreCase(respEvent.getOffenseCode()))
					    {
						referral.setOffenseCategory(respEvent.getCategory());
						break;
					    }
					}
				    }
				}
				break;
			    }
			}
		    }
		    else
			// if there isn’t an associated PETITION record, display the short description of the offense associated to the referral.
			// if multiple offenses, take offense with seqNum = 1 (oldest offense)
		    {
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
				    referral.setOffenseCategory(offense.getCatagory());
				    break;
				}
			    }
			}
		    }
		

		    JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(referralForm.getJuvenileNum(), referral.getReferralNumber());
		    Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();

		    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
			@Override
			public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
			{
			 //89766 User-story
			    if (evt1.getRefSeqNum() != null && evt2.getRefSeqNum() != null)
			    {
				  int seq1 = Integer.parseInt(evt1.getRefSeqNum());
				    int seq2 = Integer.parseInt(evt2.getRefSeqNum());
				    Integer seq1Int = new Integer(seq1);
				    Integer seq2Int = new Integer(seq2);
				return seq1Int.compareTo(seq2Int);
			    }
			    else
				return -1;
			}
		    }));
		    
		    //RRY-- Needed to group by assignment date first-- RRY
		    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
			@Override
			public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
			{
			    if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
				    return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());
			    else
				return -1;
			    }
		    }));
		    Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
		    if (!caseFileRefItr.hasNext())
		    {
			referral.setAssignmentType(refCasefileList.getAssignmentType());
			referral.setAssignmentDate(refCasefileList.getAssignmentDate());
			referral.setSupervisionCategoryId(refCasefileList.getSupervisionCategoryId());
			referral.setSupervisionTypeId(refCasefileList.getSupervisionTypeId());
			referral.setSupervisionCategory(refCasefileList.getSupervisionCategory());
			referral.setSupervisionType(refCasefileList.getSupervisionType());
			referral.setJpoId(refCasefileList.getJpo());
		    }
		    while (caseFileRefItr.hasNext())
		    {
			JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
			referral.setSupervisionCategoryId(caseFileReferral.getSupervisionCat());
			referral.setSupervisionType(caseFileReferral.getSupervisionType());
			referral.setSupervisionTypeId(caseFileReferral.getSupervisionTypeCd());
			referral.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, referral.getSupervisionCategoryId()));
			OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
			if (officerProfileResponse != null)
			{
			    referral.setJpoId(officerProfileResponse.getUserId());
			    String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
			    referral.setJpo(officerFullName);
			}
			break;
		    }
		    if (referral.getFinalDisposition() == null)
		    {
			referral.setCourtDate(null); //court date is same as the final disposition date, but only when there is a final disposition 
		    }
		    openReferralsOnlyList.add(referral);
		    referralsMap.put(referral.getReferralNumber(), referral);//putting all the referrals that can be overridden in a Map
		}
	    }
	}
	}
	if (openReferralsOnlyList.size() > 0)
	{
	    //sort referrals by ref num desc
	    Collections.sort((List<JuvenileProfileReferralListResponseEvent>) openReferralsOnlyList, Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
		@Override
		public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
		{
		    if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
			return Integer.valueOf(evt1.getReferralNumber()).compareTo(Integer.valueOf(evt2.getReferralNumber()));
		    else
			return -1;
		}
	    }));
	    referralForm.setReferralListMap(referralsMap);
	    referralForm.setReferralList(openReferralsOnlyList);
	}
	referralForm.setAction("overrideSuccess");
	//return (aMapping.findForward(UIConstants.REFRESH));
	return aMapping.findForward(UIConstants.SUCCESS);
    }
    
    /** newOverride BUG: 84949
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward newOverride(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setAction("overrideAssignment");
	form.setAssignmentType(""); 
	form.setSupervisionCat("");
	form.setSupervisionType("");
	form.setJpo("");
	form.setDisbleAssignment("N");
	form.setOverrideReasonStr("");
	form.setOverrideOTHComments("");
	form.setAssignmentDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	return aMapping.findForward(UIConstants.SUCCESS);
    }
    
    /**
     * Submit
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward selectOfficerCode(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward("overrideAssignment");
    }
    
    /**
     * Submit
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    
    public ActionForward changeSupervisionType(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward("overrideAssignment");
    }
}