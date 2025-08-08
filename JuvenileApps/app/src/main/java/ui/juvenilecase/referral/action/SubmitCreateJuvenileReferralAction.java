package ui.juvenilecase.referral.action;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

import messaging.codetable.GetAllJuvenileOffenseCodesEvent;
import messaging.codetable.GetNonDetentionJuvenileFacilitiesEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileRefAssgnmtResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.GetJuvenileFacilityDetailsEvent;
import messaging.interviewinfo.to.JOTTO;
import messaging.juvenile.GetJuvenileMasterStatusEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.SaveJuvenilePurgeEvent;
import messaging.juvenile.reply.JJSReferralResponseEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import messaging.juvenile.reply.JuvenileMasterStatusResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetActiveCasefileReferralsEvent;
import messaging.juvenilecase.GetAllReferralsByCasefileIdEvent;
import messaging.juvenilecase.GetCasefilesForReferralsEvent;
import messaging.juvenilecase.GetJJSMsReferralDetailsEvent;
import messaging.juvenilecase.GetJJSReferralDetailsEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.GetJuvenileCasefileRiskNeedsLevelEvent;
import messaging.juvenilecase.GetJuvenileCasefilesEvent;
import messaging.juvenilecase.GetJuvenileDetentionFacilitiesEvent;
import messaging.juvenilecase.SaveJuvenileRiskNeedLevelCustomEvent;
import messaging.juvenilecase.reply.JuvenileAliasResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilecase.reply.PactRiskLevelResponseEvent;
import messaging.juvenilewarrant.UpdateJJSPetitionsTerminationDateEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingChargeResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingResponseEvent;
import messaging.officer.GetOfficerProfilesByAttributeEvent;
import messaging.referral.GetJJSPartialReferralEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.GetJOTDetailsEvent;
import messaging.referral.GetJuvenileJOTPetitionDetailsEvent;
import messaging.referral.GetJuvenileProfileReferralListEvent;
import messaging.referral.SaveJJSReferralEvent;
import messaging.referral.UpdateJuvenileReferralEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFacilityControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.codetable.criminal.JuvenileReferralDispositionCode;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.SupervisionTypeMap;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSSVIntakeHistory;
import pd.juvenilewarrant.JuvenileOffenderTracking;
import pd.juvenilewarrant.JuvenileOffenderTrackingCoActor;
import pd.km.util.Name;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.contact.user.helper.UIUserFormHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.casefile.form.CommonAppForm;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.referral.JuvenileReferralMemberDetailsBean;
import ui.juvenilecase.referral.JuvenileReferralOffenseBean;
import ui.juvenilecase.referral.ReferralReceiptPrintBean;
import ui.juvenilecase.referral.form.JuvenileReferralForm;
import ui.security.SecurityUIHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author ugopinath
 */
public class SubmitCreateJuvenileReferralAction extends LookupDispatchAction
{
    /**
     * Key Method Map
     */
    protected Map getKeyMethodMap()
    {
	Map<String, String> keyMap = new HashMap<String, String>();
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.generateReferralReceipt", "generateReferralReceipt");
	keyMap.put("button.addReferral", "submitCreateReferral");
	keyMap.put("button.addToList", "addOffense");
	keyMap.put("button.remove", "removeOffense");
	keyMap.put("button.changeAssessmentType", "loadSupervision");
	keyMap.put("button.validate", "validateJPO");
	keyMap.put("button.validateOffenseCode", "validateOffenseCode");
	keyMap.put("button.searchForOffenseCode", "goToOffenseSearch");
	keyMap.put("button.changeSuprCat", "loadSupervisionTypes");
	keyMap.put("button.createNextReferral", "createNextReferral");
	keyMap.put("button.refresh", "refresh");
	keyMap.put("button.updateReferral", "updateReferral");
	keyMap.put("button.loadReferral", "loadReferral");
	keyMap.put("button.updateOffense", "updateOffense");
	keyMap.put("button.loadOffense", "loadOffense");
	keyMap.put("button.returnToSearchJuv", "returnToSearchJuv");
	keyMap.put("button.select", "selectOfficerCode");
	keyMap.put("button.changeSuprType", "changeSupervisionType");
	keyMap.put("button.changeAssignmentType", "changeAssignmentType");
	keyMap.put("button.createReseal", "createResealReferral");
	keyMap.put("button.resealSupervision", "loadResealSupervision");
	keyMap.put("button.chargeReferral", "assignAdminReferralNum");
	return keyMap;
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
    public ActionForward submitCreateReferral(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setMessage("");
	form.setSubsequentMessage("");

	String assignmentTypeSBQ = null;
	if (form.getAssignmentType() != null && form.getAssignmentType().equalsIgnoreCase("SBQ"))
	{
	    assignmentTypeSBQ = form.getAssignmentType();
	}

	JJSJuvenile jjsJuv = JJSJuvenile.find(form.getJuvenileNum());
	Date bDate = jjsJuv.getBirthDate();
	int juvAge = getAgeInYears(bDate);

	if (form.getOffenseList().isEmpty())
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Please enter a valid offense to the Offense List"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	OfficerProfileResponseEvent officerResp = new OfficerProfileResponseEvent();
	if (form.getJpo() != null && !(form.getJpo().equalsIgnoreCase("UVANC") || form.getJpo().equalsIgnoreCase("ANC") || form.getJpo().equalsIgnoreCase("UVREC") || form.getJpo().equalsIgnoreCase("REC")))
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
	if (form.getJpo() != null && (form.getJpo().equalsIgnoreCase("UVREC") || form.getJpo().equalsIgnoreCase("REC")) && !form.getAssignmentType().equalsIgnoreCase("REC"))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "JPO code UVREC does not qualify for this assignment type.  Please modify"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	Iterator intakeDecIter = form.getIntakeDecisions().iterator();
	JuvenileReferralDispositionCode disp = new JuvenileReferralDispositionCode();
	while (intakeDecIter.hasNext())
	{
	    disp = (JuvenileReferralDispositionCode) intakeDecIter.next();
	    if (form.getNewRefIntakeDecision().equals(disp.getCode()))
	    {
		if (!"AD".equals(disp.getFund()) && !"CR".equals(disp.getFund()) && "PRE".equals(form.getAssignmentType()))
		{
		    form.setAssignmentType("");
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Assignment Type does not match the Intake Decision for Referral # " + form.getNewRefNum()));
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
	    }
	    //if (disp.getCode().equals(form.getNewRefIntakeDecision()))
	    //break;
	}

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetJuvenileProfileReferralListEvent event = (GetJuvenileProfileReferralListEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLIST);
	event.setJuvenileNum(form.getJuvenileNum());
	dispatch.postEvent(event);
	CompositeResponse responses = (CompositeResponse) dispatch.getReply();
	ArrayList<JuvenileProfileReferralListResponseEvent> juvProfRefListEvt = (ArrayList) MessageUtil.compositeToCollection(responses, JuvenileProfileReferralListResponseEvent.class);
	for (JuvenileProfileReferralListResponseEvent response : juvProfRefListEvt)
	{
	    if (response.getReferralNumber().equals(form.getNewRefNum()))
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The referral number already exists. Please try again."));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}

	JJSReferralResponseEvent newReferral = new JJSReferralResponseEvent();
	newReferral.setReferralNum(form.getNewRefNum());
	newReferral.setReferralDate(DateUtil.stringToDate(form.getNewRefDate(), DateUtil.DATE_FMT_1));
	newReferral.setRefSource(form.getNewRefSource());
	newReferral.setIntakeDecision(form.getNewRefIntakeDecision());
	newReferral.setIntakeDecisionDescr(disp.getDescription());
	newReferral.setIntakeDate(DateUtil.stringToDate(form.getNewRefIntakeDate(), DateUtil.DATE_FMT_1));
	newReferral.setSupervisionType(form.getSupervisionType());
	newReferral.setJpo(form.getJpo());
	if (officerResp != null)
	    newReferral.setJpoDescr(officerResp.getFormattedName());
	if (form.getAssignmentType() != null && form.getAssignmentType().equalsIgnoreCase("REC"))
	{
	    List types = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_TYPE");
	    Iterator<JuvenileCodeTableChildCodesResponseEvent> typesIter = types.iterator();
	    ArrayList tempTypes = new ArrayList();
	    while (typesIter.hasNext())
	    {
		JuvenileCodeTableChildCodesResponseEvent resp = typesIter.next();
		if (resp.getParentId() != null && resp.getParentId().equals(form.getSupervisionCat()))
		    newReferral.setSupervisionTypeDescr(resp.getDescription());
	    }
	}
	else
	    newReferral.setSupervisionTypeDescr(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, form.getSupervisionType()));
	//go through offense list and make a comma separated string for display
	Iterator offenseIter = form.getOffenseList().iterator();
	String offensesStr = "";
	int seqNum = 1;
	while (offenseIter.hasNext())
	{
	    JuvenileReferralOffenseBean resp = (JuvenileReferralOffenseBean) offenseIter.next();
	    offensesStr += resp.getOffenseCode();
	    if (offenseIter.hasNext())
		offensesStr += ", ";
	    resp.setSequenceNum(seqNum + "");
	    seqNum++;
	}

	//YTD - Referral Type and TJJDReferral Date
	//if Assignment Type Transferred and ISCOIN offense - Ref Type IC
	//Transferred and no iscoin - Ref Type TR
	//No transferred - Ref Type PA

	if (form.getAssignmentType() != null && form.getAssignmentType().equalsIgnoreCase("TRN"))
	{
	    boolean iscoinFound = false;
	    Date offenseDate = null;
	    Iterator iter = form.getOffenseList().iterator();
	    while (iter.hasNext())
	    {
		JuvenileReferralOffenseBean offBean = (JuvenileReferralOffenseBean) iter.next();
		if (offBean.getOffenseCode() != null && offBean.getOffenseCode().equalsIgnoreCase("ISCOIN"))
		{
		    iscoinFound = true;
		    offenseDate = offBean.getOffenseDate();
		    break;
		}

		JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode", offBean.getOffenseCode());
		if (offCode.getSeveritySubtype() != null && !offCode.getSeveritySubtype().equalsIgnoreCase("T"))
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "This assignment type requires an out of state or out of county level offense: TRNSIN, TRNDSP, ISCOIN or REGION.  Please check assignment type"));
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
	    }
	    if (iscoinFound)
	    {
		form.setFormalReferralType("IC");
		form.setTJJDReferralDate(offenseDate);
	    }
	    else
	    {
		form.setFormalReferralType("TR");
		form.setTJJDReferralDate(DateUtil.getCurrentDate());
	    }
	}
	else
	{
	    form.setFormalReferralType("PA");
	    //form.setTJJDReferralDate(DateUtil.getCurrentDate());
	}
	SaveJJSReferralEvent saveEvent = (SaveJJSReferralEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.SAVEJJSREFERRAL);
	saveEvent = setSaveDetails(saveEvent, form);
	saveEvent.setActionFlag("createReferral"); //BUG 87290
	saveEvent.setExclMessage("");
	dispatch.postEvent(saveEvent);
	CompositeResponse response = (CompositeResponse) dispatch.getReply();
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
	if (error != null)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", error.getMessage()));
	    saveErrors(aRequest, errors);
	    // return aMapping.findForward(UIConstants.FAILURE);
	}
	JuvenileCasefileReferralResponseEvent casefileRefResp = (JuvenileCasefileReferralResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileReferralResponseEvent.class);
	//get the user logged in
	String logonId = SecurityUIHelper.getLogonId();
	if (casefileRefResp != null)
	{
	    if (casefileRefResp.getCaseFileId() != null)
	    {
		Name fullName = new Name(form.getFirstName(), form.getMiddleName(), form.getLastName(), form.getNameSuffix());
		StringBuffer formattedName = new StringBuffer(fullName.getFormattedName());

		String comment = "Referral " + casefileRefResp.getReferralNumber() + ", Offense(s): " + casefileRefResp.getOffenseCodes() + " has been created for Juvenile: " + casefileRefResp.getJuvenileNum() + ", " + formattedName.toString() + ". Referral Record Created by: " + logonId + "' " + DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1);

		UIJuvenileHelper.createReferralActivity(casefileRefResp.getCaseFileId(), comment);
		if (form.getSupervisionCat().equalsIgnoreCase("PP"))
		{
		    if (form.getJpo().equalsIgnoreCase(logonId))
			form.setActivateSupervision("Y");
		    form.setCurrentCasefileId(casefileRefResp.getCaseFileId());
		}
	    }

	    form.clearRefDetails();
	    form.setNewRefNum("");
	    form.setNewRefDate("");
	    form.setAssignmentDateStr("");
	    form.setAssignmentTypes(new ArrayList());

	    form.setAction("confirmCreate");

	    if (jjsJuv != null)
	    {
		jjsJuv.setLastReferralNum(casefileRefResp.getReferralNumber());
		new Home().bind(jjsJuv);
	    }
	}

	if (form.getAgeAtOffense() >= 17)
	{
	    if (form.getIsNumericCode23() != null && form.getIsNumericCode23().equals("yes"))
	    {
		form.setMessage("Referral " + casefileRefResp.getReferralNumber() + " was successfully created.");
		form.setSubsequentMessage("Juvenile is between the ages of 17 - 21. The record has been processed for referral creation and SUBSEQUENT assignment to Supervision : " + casefileRefResp.getCaseFileId());

	    }
	    else
		if (form.getIsTransferredOffense() != null && form.getIsTransferredOffense().equals("yes"))
		{
		    form.setMessage("Referral " + casefileRefResp.getReferralNumber() + " was successfully created.");
		    form.setSubsequentMessage("Juvenile is between the ages of 17 - 21. The record has been processed for referral creation and assignment. Supervision : " + casefileRefResp.getCaseFileId());

		}
		else
		{
		    form.setMessage("Referral " + casefileRefResp.getReferralNumber() + " was successfully created.");
		}
	}
	else
	    if (assignmentTypeSBQ != null && (form.getAgeAtOffense() >= 10 && form.getAgeAtOffense() <= 16))
	    {

		form.setMessage("Referral " + casefileRefResp.getReferralNumber() + " was successfully created.");
		form.setSubsequentMessage("The record has been assigned for record creation and SUBSEQUENT assignment to: " + form.getSubsequentCasefileId());

	    }
	    else
	    {
		form.setMessage("Referral " + casefileRefResp.getReferralNumber() + " was successfully created.");
	    }

	newReferral.setOffenseList(offensesStr);
	form.getReferralList().add(newReferral);
	//form.setAction("");

	return aMapping.findForward(UIConstants.SUCCESS);
    }

    private SaveJJSReferralEvent setSaveDetails(SaveJJSReferralEvent saveEvt, JuvenileReferralForm form)
    {
	saveEvt.setReferralNum(form.getNewRefNum());
	saveEvt.setReferralDate(DateUtil.stringToDate(form.getNewRefDate(), DateUtil.DATE_FMT_1));
	saveEvt.setReferralSource(form.getNewRefSource());
	//default PIA status to P if ref source is 89 - task 169593
	if (form.getNewRefSource().equalsIgnoreCase("89"))
	    saveEvt.setPIAstatus("P");
	saveEvt.setIntakeDate(DateUtil.stringToDate(form.getNewRefIntakeDate(), DateUtil.DATE_FMT_1));
	saveEvt.setIntakeDecisionId(form.getNewRefIntakeDecision());
	UIJuvenileHelper.PopulateReferralTJPCdispCode(saveEvt, saveEvt.getIntakeDecisionId()); //179484 populate TJPCdisp Code field
	saveEvt.setOffenses(form.getOffenseList());
	saveEvt.setOffenseTotal(form.getOffenseList().size() + "");
	saveEvt.setJuvenileNum(form.getJuvenileNum());
	saveEvt.setCasefileGenerate(form.getCasefileGenerate());
	saveEvt.setInvestigationNum(form.getInvestigationNum());
	saveEvt.setKeyMapLocation(form.getKeyMapLocation());
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
	    else
		if (form.getAssignmentType().equalsIgnoreCase("PRE"))
		{
		    if (form.getSupervisionCat() != null)
		    {
			if (form.getSupervisionCat().equalsIgnoreCase("AD"))
			{
			    saveEvt.setCtAssignJPOId(form.getJpo());
			}
			else
			{
			    saveEvt.setJpoID(form.getJpo());
			}
		    }
		}

		/**
		 * If the Assignment Type falls into Post-Adjudication category,
		 * insert assigned JPO code into the Probation Officer
		 * (PROBATIONJPOID) column. This applies to the following
		 * Assignment Type(s): POST-ADJUDICATION
		 */

		else
		    if (form.getAssignmentType().equalsIgnoreCase("PAD"))
		    {
			if (form.getSupervisionCat() != null)
			{
			    if (form.getSupervisionCat().equalsIgnoreCase("AR") || form.getSupervisionCat().equalsIgnoreCase("AC"))
			    {
				saveEvt.setProbJPOId(form.getJpo());
			    }
			    else
			    {
				saveEvt.setJpoID(form.getJpo());
			    }
			}
		    }
		    /**
		     * If Assignment Type equals TRANSFERRED OFFENSE (OOC), then
		     * refer to Supervision Category to determine proper
		     * insertion. If Supervision Category = PRE-PETITION, then
		     * insert assigned JPO code into the Intake Officer
		     * (INASSIGNJPOID) column. If Supervision Category =
		     * PRE-ADJUDICATION, then insert assigned JPO code into the
		     * Court Officer (CTASSINGJPOID) column. If Supervision
		     * Category = POST-ADJUDICATION COMMUNITY or
		     * POST-ADJUDICATION RESIDENTIAL, then insert JPO code into
		     * Probation Officer (PROBATIONJPOID) column.
		     */

		    else
			if (form.getAssignmentType().equalsIgnoreCase("TRN"))
			{
			    if (form.getSupervisionCat() != null)
			    {
				if (form.getSupervisionCat().equalsIgnoreCase("PP"))
				{
				    saveEvt.setJpoID(form.getJpo());
				}
				else
				    if (form.getSupervisionCat().equalsIgnoreCase("AD"))
				    {
					saveEvt.setCtAssignJPOId(form.getJpo());
				    }
				    else
					if (form.getSupervisionCat().equalsIgnoreCase("AC") || form.getSupervisionCat().equalsIgnoreCase("AR"))
					{
					    saveEvt.setProbJPOId(form.getJpo());
					}
					else
					{
					    saveEvt.setJpoID(form.getJpo());
					}
			    }
			}
			else
			{
			    saveEvt.setJpoID(form.getJpo());
			}
	}

	//User-story 8814

	saveEvt.setSubsequentCasefileId(form.getSubsequentCasefileId());
	saveEvt.setRefExcludedReporting(form.getRefExcludedReporting());
	return saveEvt;
    }

    /**
     * create Referral
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward createNextReferral(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward(UIConstants.CREATE);
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;

	//form.setReferralList(new ArrayList());
	form.setOriginalChargeReferrals(new ArrayList());
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetJuvenileProfileReferralListEvent event = (GetJuvenileProfileReferralListEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLIST);
	event.setJuvenileNum(form.getJuvenileNum());
	dispatch.postEvent(event);
	CompositeResponse responses = (CompositeResponse) dispatch.getReply();
	Collection<JuvenileProfileReferralListResponseEvent> juvProfRefListEvt = MessageUtil.compositeToCollection(responses, JuvenileProfileReferralListResponseEvent.class);
	// Perform Error handling
	MessageUtil.processReturnException(responses);
	//sorts in descending order by Referral Num. 
	Collections.sort((List<JuvenileProfileReferralListResponseEvent>) juvProfRefListEvt, new Comparator<JuvenileProfileReferralListResponseEvent>() {
	    @Override
	    public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
	    {
		if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
		    return evt2.getReferralNumber().compareTo(evt1.getReferralNumber());
		else
		    return -1;
	    }
	});

	Iterator<JuvenileProfileReferralListResponseEvent> refIter = juvProfRefListEvt.iterator();
	if (refIter.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent refResp = (JuvenileProfileReferralListResponseEvent) refIter.next();
	    int refNum = 0;
	    if (Integer.valueOf(refResp.getReferralNumber()) % 10 == 0)
	    {
		refNum = (Integer.valueOf(refResp.getReferralNumber())) + 10;

	    }
	    else
	    {
		StringBuffer sb = new StringBuffer(refResp.getReferralNumber().substring(0, 3)).append(0);
		refNum = (Integer.valueOf(sb.toString())) + 10;
	    }

	    form.setNewRefNum(String.valueOf(refNum));
	}
	else
	{
	    form.setNewRefNum("1010");
	}

	form.setNewRefDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	form.setAssignmentDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	form.setAssignmentTypes(ComplexCodeTableHelper.getRefAssmntTypeCodes());
	form.clearRefDetails();
	form.setAction("createReferral");
	form.setActivateSupervision("N");
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
    public ActionForward validateJPO(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	//Validate the jpo
	OfficerProfileResponseEvent resp = validateJPOEntered(form);
	if (resp == null)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile Probation Officer is invalid. Please modify"));
	    saveErrors(aRequest, errors);
	    if (form.getAction() == null || form.getAction().equalsIgnoreCase("updateReferral"))
	    {
		form.setUpdateRefStatFlag("");
		return aMapping.findForward("updateReferral");
	    }
	    else
		return aMapping.findForward(UIConstants.FAILURE);
	}
	else
	{
	    ActionMessages messageHolder = new ActionMessages();
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.jpoValid"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	}

	if (form.getAction() != null && form.getAction().equalsIgnoreCase("overrideAssignment "))
	{//changes for Override assignment US 71181
	    return aMapping.findForward("overrideAssignment");
	}
	else
	{
	    if (form.getAction() == null || form.getAction().equalsIgnoreCase("updateReferral"))
	    {
		form.setUpdateRefStatFlag("");
		return aMapping.findForward("updateReferral");
	    }
	    else
	    {
		form.setAction("");
		return aMapping.findForward(UIConstants.SUCCESS);
	    }
	}
    }

    private OfficerProfileResponseEvent validateJPOEntered(JuvenileReferralForm form)
    {
	if (form.getJpo() != null && !form.getJpo().equals("") && form.getJpo().length() <= 3)
	{
	    form.setJpo("UV" + form.getJpo());
	}

	//Validate the jpo
	OfficerProfileResponseEvent officerProfile = UIUserFormHelper.getUserOfficerProfile(form.getJpo());

	return officerProfile;
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
    public ActionForward loadSupervision(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setSupervisionCat("");
	form.setSupervisionType("");
	form.setSupervisionTypes(new ArrayList());
	form.setSupervisionCategories(new ArrayList());
	//for Subsequent assignment type get the current casefile details
	if (form.getAssignmentType() != null && (form.getAssignmentType().equalsIgnoreCase("PCO") || form.getAssignmentType().equalsIgnoreCase("PAD") || form.getAssignmentType().equalsIgnoreCase("DAD")))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "This assignment type requires the referral is processed in court. Please modify"));
	    saveErrors(aRequest, errors);
	    if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
	    {
		return (aMapping.findForward("updateReferral"));
	    }
	    else
	    {
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}
	if (form.getAssignmentType() != null && form.getAssignmentType().equalsIgnoreCase("SBQ"))
	{
	    JuvenileReferralHelper.jpoOfRecord(form.getJuvenileNum(), form);
	    if (form.getSubsequentCasefileId() == null || form.getSubsequentCasefileId().equals(""))
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "An Active Supervision is Required to Assign a Subsequent Referral"));
		saveErrors(aRequest, errors);
		if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
		{
		    return (aMapping.findForward("updateReferral"));
		}
		else
		{
		    return aMapping.findForward(UIConstants.FAILURE);
		}
	    }
	}
	else
	    if (form.getAssignmentType() != null && (form.getAssignmentType().equalsIgnoreCase("TRN")))
	    {

		//go through list and set description with code
		Iterator iter = CodeHelper.getActiveCodes(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, true).iterator();
		ArrayList tempArray = new ArrayList();
		while (iter.hasNext())
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
		while (iter.hasNext())
		{
		    JuvenileCodeTableChildCodesResponseEvent resp = iter.next();
		    resp.setDescriptionWithCode(resp.getCode() + "-" + resp.getDescription());
		    if (resp.getParentId() != null && resp.getParentId().equals(form.getAssignmentType()))
		    {
			tempCategories.add(resp);

			if (resp.getCode().equalsIgnoreCase("PP") || resp.getCode().equalsIgnoreCase("ID") || resp.getCode().equalsIgnoreCase("AD"))
			{
			    Iterator<SupervisionTypeMap> supervisioncategoryIter = SupervisionTypeMap.findAll("supervisionCatId", resp.getCode());
			    HashMap<String, CodeResponseEvent> sMap = new HashMap<String, CodeResponseEvent>();
			    SupervisionTypeMap sTypeMap = new SupervisionTypeMap();
			    while (supervisioncategoryIter.hasNext())
			    {
				sTypeMap = (SupervisionTypeMap) supervisioncategoryIter.next();
				Code code = Code.find(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, sTypeMap.getSupervisionTypeId());
				if (code.getStatus() != null && (code.getStatus().equalsIgnoreCase("ACTIVE") || code.getStatus().equalsIgnoreCase("A")))
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
			//break;
		    }
		}

		if (tempCategories.size() == 1)
		    form.setSupervisionCat(((JuvenileCodeTableChildCodesResponseEvent) tempCategories.get(0)).getCode());
		Collections.sort(tempCategories);
		form.setSupervisionCategories(tempCategories);
		if (form.getAssignmentType() != null && !(form.getAssignmentType().equalsIgnoreCase("INT") || form.getAssignmentType().equalsIgnoreCase("IAW") || form.getAssignmentType().equalsIgnoreCase("PRE")))
		{
		    List types = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_TYPE");
		    Iterator<JuvenileCodeTableChildCodesResponseEvent> typesIter = types.iterator();
		    ArrayList tempTypes = new ArrayList();
		    while (typesIter.hasNext())
		    {
			JuvenileCodeTableChildCodesResponseEvent resp = typesIter.next();
			if (resp.getParentId() != null && resp.getParentId().equals(form.getSupervisionCat()))
			{
			    resp.setDescriptionWithCode(resp.getCode() + "-" + resp.getDescription());
			    tempTypes.add(resp);
			    form.setSupervisionType(resp.getCode());
			    break;
			}
		    }
		    Collections.sort(tempTypes);
		    form.setSupervisionTypes(tempTypes);
		}
		if (form.getAssignmentType() != null && form.getAssignmentType().equalsIgnoreCase("REC"))
		    form.setJpo("UVREC");
		else
		    form.setJpo("");
	    }
	//get the casefilegenerate flag for the assignment type
	Iterator assgnmntTypeIter = form.getAssignmentTypes().iterator();
	while (assgnmntTypeIter.hasNext())
	{
	    JuvenileRefAssgnmtResponseEvent resp = (JuvenileRefAssgnmtResponseEvent) assgnmntTypeIter.next();
	    if (resp.getCode() != null && resp.getCode().equalsIgnoreCase(form.getAssignmentType()))
	    {
		form.setCasefileGenerate(resp.getCasefileGenerate());
		break;
	    }
	}
	if (form.getAction() != null && !form.getAction().equalsIgnoreCase("updateReferral"))
	{
	    form.setAction("");
	    return aMapping.findForward(UIConstants.SUCCESS);
	}
	else
	{
	    form.setLoadAssmntFlag("Y");
	    form.setUpdateRefStatFlag("");
	    return (aMapping.findForward("updateReferral"));
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
    public ActionForward loadResealSupervision(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setSupervisionCat("");
	form.setSupervisionType("");
	form.setSupervisionTypes(new ArrayList());
	form.setSupervisionCategories(new ArrayList());
	ActionForward forward = aMapping.findForward("resealError");

	GetJJSReferralEvent referralEvent = (GetJJSReferralEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJJSREFERRAL);
	referralEvent.setJuvenileNum(form.getJuvenileNum());
	referralEvent.setReferralNum(form.getNewRefNum());

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(referralEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	JuvenileProfileReferralListResponseEvent respEvent = (JuvenileProfileReferralListResponseEvent) MessageUtil.filterComposite(compositeResponse, JuvenileProfileReferralListResponseEvent.class);

	if (respEvent != null && respEvent.getCloseDate() == null)
	{

	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile referral is currently active. Referral must be CLOSED to allow reseal."));
	    saveErrors(aRequest, errors);
	    return forward;
	}

	String selectedVal = form.getNewRefNum();
	String partial = "";
	try
	{
	    partial = selectedVal.substring(0, 3);
	    partial = partial + '%';
	}
	catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    //e.printStackTrace();
	}

	GetJJSPartialReferralEvent partialEvent = (GetJJSPartialReferralEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJJSPARTIALREFERRAL);
	partialEvent.setJuvenileNum(form.getJuvenileNum());
	partialEvent.setReferralNum(partial);

	dispatch.postEvent(partialEvent);
	compositeResponse = (CompositeResponse) dispatch.getReply();

	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);

	Collection<JuvenileProfileReferralListResponseEvent> partialResp = MessageUtil.compositeToCollection(compositeResponse, JuvenileProfileReferralListResponseEvent.class);

	Collections.sort((List<JuvenileProfileReferralListResponseEvent>) partialResp, Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
	    @Override
	    public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
	    {
		return Integer.valueOf(evt1.getReferralNumber()).compareTo(Integer.valueOf(evt2.getReferralNumber()));
	    }
	}));

	Iterator<JuvenileProfileReferralListResponseEvent> iter = partialResp.iterator();
	if (iter.hasNext())
	{

	    JuvenileProfileReferralListResponseEvent refResp = iter.next();
	    int newReferralNum = Integer.parseInt(refResp.getReferralNumber());
	    newReferralNum = newReferralNum + 1;

	    if (Integer.valueOf(newReferralNum) % 10 == 0)
	    {

		String tempRefNum = form.getMaxCrimReferral();
		if (newReferralNum <= Integer.parseInt(tempRefNum))
		{

		    StringBuffer sb = new StringBuffer(tempRefNum.substring(0, 3)).append(0);
		    newReferralNum = (Integer.valueOf(sb.toString())) + 10;
		}

	    }
	    form.setNewRefNum(String.valueOf(newReferralNum));
	}

	/*JuvenileReferralOffenseBean offenseResp = new JuvenileReferralOffenseBean();
	JuvenileOffenseCodeResponseEvent jocEvent = JuvenileReferralHelper.validateOffenseCd(form.getOffenseCode());
	
	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	Date offenseDate = null;
	
	try
	{
	    offenseDate = df.parse(form.getOffenseDate());
	}
	catch (ParseException e1)
	{
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	
	form.getReferralOffenseBean().setOffenseDescription(jocEvent.getShortDescription());
	form.getReferralOffenseBean().setOffenseCode(jocEvent.getOffenseCode());
	if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
	{
	    form.getReferralOffenseBean().setKeyMapLocation(form.getKeyMapLocation());
	    form.getReferralOffenseBean().setInvestigationNum(form.getInvestigationNum());
	}
	if (form.getOffenseDate() != null)
	    form.getReferralOffenseBean().setOffenseDate(DateUtil.stringToDate(form.getOffenseDate(), DateUtil.DATE_FMT_1));
	try
	{
	    BeanUtils.copyProperties(offenseResp, form.getReferralOffenseBean());
	}
	catch (IllegalAccessException e)
	{
	    e.printStackTrace();
	}
	catch (InvocationTargetException e)
	{
	    e.printStackTrace();
	}*/

	//build offense list
	List<JuvenileReferralOffenseBean> tempOffenses = new ArrayList<JuvenileReferralOffenseBean>();
	JuvenileReferralOffenseBean offenseResp = new JuvenileReferralOffenseBean();
	offenseResp.setOffenseID(form.getOffenseId());
	offenseResp.setOffenseCode(form.getOffenseCode());
	offenseResp.setReferralNum(form.getNewRefNum());
	offenseResp.setJuvenileNum(form.getJuvenileNum());
	JuvenileOffenseCodeResponseEvent jocEvent = JuvenileReferralHelper.validateOffenseCd(form.getOffenseCode());
	if (jocEvent != null)
	{

	    offenseResp.setOffenseDescription(jocEvent.getShortDescription());
	}
	offenseResp.setInvestigationNum(null);
	offenseResp.setKeyMapLocation(null);
	offenseResp.setOffenseDate(DateUtil.getCurrentDate());
	offenseResp.setSequenceNum("1");

	form.getOffenseList().add(offenseResp);
	form.getOffenseCodeList().add(form.getOffenseCode());
	form.getReferralOffenseBean().clear();

	//Get the Intake decisions	    
	form.setIntakeDecisions(JuvenileCaseHelper.getReferralDecisions());
	Collections.sort((List) form.getIntakeDecisions(), JuvenileReferralDispositionCode.CodeComparator);
	form.setAssignmentTypes(ComplexCodeTableHelper.getRefAssmntTypeCodes());
	form.setRefSources(JuvenileReferralHelper.getAllReferralSources());
	form.setSupervisionCategories(CodeHelper.getSupervisionCategories());
	form.setSupervisionTypes(CodeHelper.getSupervisionTypes());
	//form.setReferralList(new ArrayList());
	form.setAction("createReseal");
	form.setActivateSupervision("N");
	form.setFormalReferralType("PA");
	form.setAssignmentType("ADM");
	form.setSupervisionCat("PP");
	form.setSupervisionType("ISS");

	form.setLoadAssmntFlag("Y");
	form.setUpdateRefStatFlag("");
	form.setOriginalChargeReferrals(null);
	return (aMapping.findForward("resealSuccess"));

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
	if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
	{
	    return (aMapping.findForward("updateReferral"));
	}
	form.setAction("");
	form.setUpdateRefStatFlag("");
	return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * Generate
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward generateReferralReceipt(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String fromProfile = aRequest.getParameter("fromProfile");

	StringBuffer fullName = new StringBuffer();

	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	String juvenileNum = "";
	String refNum = "";

	if (StringUtils.isNotEmpty(form.getJuvenileNum()))
	{
	    juvenileNum = form.getJuvenileNum();
	}

	//get the form from the briefing details page.
	JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("REFERRAL_BRIEFINGDETAILS_FORM");

	briefingDetailsForm.setFromProfile((fromProfile == null) ? "" : fromProfile);
	briefingDetailsForm.setJuvenileNum(juvenileNum);

	setProfileDetail(juvenileNum, briefingDetailsForm);

	String casefileId = aRequest.getParameter("supervisionNum");

	if (stringNullOrNotEqual(fromProfile, "profileBriefingDetails") && notNullNotEmptyString(casefileId))
	{
	    setCurrentCasefile(casefileId, briefingDetailsForm);
	}

	JuvenileReferralHelper.populateReferralForm(briefingDetailsForm, form);

	ReferralReceiptPrintBean myReportBean = new ReferralReceiptPrintBean();

	myReportBean.setDetained("N");
	myReportBean.setFacility("");

	//get the user logged in
	String logonId = SecurityUIHelper.getLogonId();

	if (StringUtils.isNotEmpty(logonId))
	{
	    myReportBean.setLcUser(logonId);
	}
	else
	{
	    myReportBean.setLcUser("");
	}

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	// Send PD Request Event
	GetJJSMsReferralDetailsEvent request = (GetJJSMsReferralDetailsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSMSREFERRALDETAILS);
	//GetJuvenileProfileReferralListEvent event = (GetJuvenileProfileReferralListEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEPROFILEREFERRALLIST);
	request.setJuvenileNum(juvenileNum);

	List referrals = MessageUtil.postRequestListFilter(request, JuvenileProfileReferralListResponseEvent.class);

	//sorts in descending order by Referral Num. 
	Collections.sort((List<JuvenileProfileReferralListResponseEvent>) referrals, new Comparator<JuvenileProfileReferralListResponseEvent>() {
	    @Override
	    public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
	    {
		if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
		{
		    return evt2.getReferralNumber().compareTo(evt1.getReferralNumber());
		}
		else
		{
		    return -1;
		}
	    }
	});

	Iterator<JuvenileProfileReferralListResponseEvent> refIter = referrals.iterator();
	while (refIter.hasNext())
	{

	    JuvenileProfileReferralListResponseEvent referral = refIter.next();
	    if (Integer.valueOf(referral.getReferralNumber()) % 10 == 0)
	    {
		StringBuffer unit = new StringBuffer();
		StringBuffer caseNum = new StringBuffer();

		unit.append(referral.getSupervisionCategoryId());
		unit.append(" ");
		unit.append(referral.getJpoId());
		myReportBean.setInAssignUnitOfficer(unit.toString());

		caseNum.append(juvenileNum);
		caseNum.append(" ");
		caseNum.append(referral.getReferralNumber());
		// set caseNum
		myReportBean.setCaseNumber(caseNum.toString());
		myReportBean.setReferralSource(referral.getReferralSourceDesc());
		myReportBean.setReferralDate(referral.getReferralDate());
		myReportBean.setOffenseAlphaCode(referral.getOffense());
		myReportBean.setInvestigationNumber(referral.getOffenseInvestNum());
		myReportBean.setOffenseDate(referral.getOffenseDate());
		refNum = referral.getReferralNumber();

		if (form.getJotInfo() != null)
		{
		    if (form.getJotInfo().getAdultCoActors() != null)
		    {
			int adultCoActors = form.getJotInfo().getAdultCoActors().size();
			myReportBean.setNumberOfCoActors(Integer.toString(adultCoActors));
		    }
		}
		else
		{
		    myReportBean.setNumberOfCoActors("");
		}
		break;
	    }
	}

	//add referrals to JuvenileBriefingDetailsForm	and myReportBean
	if (referrals != null)
	{
	    setNumberOfDetentionDays(myReportBean, referrals, juvenileNum, refNum);
	    briefingDetailsForm.setJuvProfRefListDetails(referrals);
	    myReportBean.setJuvProfRefList(referrals);
	    myReportBean.setRefCnt(referrals.size());
	}

	// set juvenile formatted name

	if (StringUtils.isNotEmpty(form.getLastName()))
	{
	    fullName.append(form.getLastName());
	}
	if (StringUtils.isNotEmpty(form.getFirstName()))
	{
	    fullName.append(", ");
	    fullName.append(form.getFirstName());
	}
	if (StringUtils.isNotEmpty(form.getMiddleName()))
	{
	    fullName.append(" ");
	    fullName.append(form.getMiddleName());
	}
	if (StringUtils.isNotEmpty(form.getNameSuffix()))
	{
	    fullName.append(" ");
	    fullName.append(form.getNameSuffix());
	}

	myReportBean.setFormattedName(fullName.toString());

	fullName = new StringBuffer();

	// set Race/Sex/Age

	StringBuffer rSA = new StringBuffer();

	if (StringUtils.isNotEmpty(form.getRaceId()))
	{
	    rSA.append(form.getRaceId());
	}
	if (StringUtils.isNotEmpty(form.getSexId()))
	{
	    rSA.append(" ");
	    rSA.append(form.getSexId());
	}
	if (StringUtils.isNotEmpty(briefingDetailsForm.getAge()))
	{
	    rSA.append(" ");
	    rSA.append(briefingDetailsForm.getAge());
	}

	myReportBean.setRaceSexAge(rSA.toString());

	rSA = new StringBuffer();

	// set DOB

	if (StringUtils.isNotEmpty(form.getDateOfBirth()))
	{
	    myReportBean.setDateOfBirth(form.getDateOfBirth());
	}

	//setVerifiDOB (JUVENILE_MASTER.VerifiedDOB);
	if (briefingDetailsForm.getProfileDetail().isVerifiedDOB())
	{
	    myReportBean.setVerifiDOB("Y");
	}
	else
	{
	    myReportBean.setVerifiDOB("N");
	}

	/*
	 * if(JUVENILE_MASTER.MasterStatus == Active, Pending or No Casefile setStatus("A");
	 * if(JUVENILE_MASTER.MasterStatus == Closed setStatus("C");
	 */
	if (StringUtils.isNotEmpty(briefingDetailsForm.getProfileDetail().getStatusId()))
	{
	    if (briefingDetailsForm.getProfileDetail().getStatusId().equalsIgnoreCase("C"))
	    {
		myReportBean.setStatus("C");
	    }
	    else
	    {
		myReportBean.setStatus("A");
	    }
	}

	// set juvenile address

	Address address = form.getJuvAddress();
	if (address != null)
	{
	    StringBuffer formattedAddress = new StringBuffer();
	    if (StringUtils.isNotEmpty(address.getStreetNum()))
	    {
		formattedAddress.append(address.getStreetNum());
	    }
	    if (StringUtils.isNotEmpty(address.getStreetNumSuffix()))
	    {
		formattedAddress.append("");
		formattedAddress.append(address.getStreetNumSuffix());
	    }
	    if (StringUtils.isNotEmpty(address.getStreetName()))
	    {
		formattedAddress.append(" ");
		formattedAddress.append(address.getStreetName());
	    }
	    if (StringUtils.isNotEmpty(address.getStreetType()))
	    {
		formattedAddress.append(" ");
		formattedAddress.append(address.getStreetType());
	    }
	    if (StringUtils.isNotEmpty(address.getAptNum()))
	    {
		formattedAddress.append(" ");
		formattedAddress.append(address.getAptNum());
	    }
	    if (StringUtils.isNotEmpty(address.getAddress2()))
	    {
		formattedAddress.append(" ");
		formattedAddress.append(address.getAddress2());
	    }
	    if (StringUtils.isNotEmpty(address.getCity()))
	    {
		formattedAddress.append(" ");
		formattedAddress.append(address.getCity());
	    }
	    if (StringUtils.isNotEmpty(address.getStateCode()))
	    {
		formattedAddress.append(", ");
		formattedAddress.append(address.getStateCode());
	    }

	    // set formatted zip code

	    StringBuffer formattedZipCode = new StringBuffer();

	    if (StringUtils.isNotEmpty(address.getZipCode()))
	    {
		formattedZipCode.append(address.getZipCode());
	    }
	    if (StringUtils.isNotEmpty(address.getAdditionalZipCode()))
	    {
		formattedZipCode.append("-");
		formattedZipCode.append(address.getAdditionalZipCode());
	    }

	    if (StringUtils.isNotEmpty(formattedZipCode.toString()))
	    {
		formattedAddress.append(" ");
		formattedAddress.append(formattedZipCode.toString());
	    }

	    formattedZipCode = new StringBuffer();

	    myReportBean.setJuvFormattedAddress(formattedAddress.toString());

	    formattedAddress = new StringBuffer();

	    // set county

	    myReportBean.setCounty(address.getCounty());
	}
	else
	{
	    myReportBean.setCounty("");
	}

	address = new Address();

	// set formatted SSN

	if (StringUtils.isNotEmpty(form.getFormattedSSN()))
	{
	    myReportBean.setFormattedSSN(form.getFormattedSSN());
	}
	else
	    if (StringUtils.isNotEmpty(form.getCompleteSSN()))
	    {
		String ssn = form.getCompleteSSN();
		if (ssn != null && ssn.length() > 8)
		{
		    StringBuffer ss = new StringBuffer();
		    String formatedValue = ssn.substring(3, 7);
		    if (ssn.length() == 9)
		    {
			ss.append("XXX-XX-");
			ss.append(ssn.substring(5, 9));
			myReportBean.setFormattedSSN(ss.toString());
		    }
		    else
			if (formatedValue.startsWith("-") && formatedValue.endsWith("-"))
			{
			    ss.append("XXX-XX-");
			    ss.append(ssn.substring(7, 11));
			    myReportBean.setFormattedSSN(ss.toString());
			}
		    ss = new StringBuffer();
		}
		ssn = "";
	    }
	    else
	    {
		myReportBean.setFormattedSSN("No SSN on file");
	    }

	// set school

	if (StringUtils.isNotEmpty(form.getSchoolName()))
	{
	    myReportBean.setSchool(form.getSchoolName());
	}

	// check if guardian is other

	String guardianIsOther = "False";

	// set guardian phone to contact phone

	if (briefingDetailsForm.getGuardians() != null)
	{
	    List<JuvenileReferralMemberDetailsBean> juvGuardianList = JuvenileReferralHelper.populateFromGuardianBeanToMemBean(briefingDetailsForm.getGuardians());
	    Iterator<JuvenileReferralMemberDetailsBean> guardianList = juvGuardianList.iterator();
	    while (guardianList.hasNext())
	    {
		JuvenileReferralMemberDetailsBean memberBean = (JuvenileReferralMemberDetailsBean) guardianList.next();

		String relationShip = "";
		String relationShipId = "";

		if (StringUtils.isNotEmpty(memberBean.getRelationshipDesc()))
		{
		    relationShip = memberBean.getRelationshipDesc();
		}
		if (StringUtils.isNotEmpty(memberBean.getRelationshipId()))
		{
		    relationShipId = memberBean.getRelationshipId();
		}

		if (StringUtils.isNotEmpty(memberBean.getPrimaryContact()) && memberBean.getPrimaryContact().equalsIgnoreCase("True"))
		{
		    // the juvenile phone is the guardian's phone
		    if (StringUtils.isNotEmpty(memberBean.getFormattedPhone()))
		    {
			myReportBean.setContactPhone(memberBean.getFormattedPhone());
		    }
		    // the juvenile address is the guardian's address
		    if (StringUtils.isEmpty(myReportBean.getJuvFormattedAddress()))
		    {
			myReportBean.setJuvFormattedAddress(memberBean.getFormattedAddress());
		    }
		    if ((!relationShip.equalsIgnoreCase("BIRTH FATHER")) && (!relationShip.equalsIgnoreCase("BIRTH MOTHER")) && (!relationShip.equalsIgnoreCase("")) || (!relationShipId.equalsIgnoreCase("BF")) && (!relationShipId.equalsIgnoreCase("BM")) && (!relationShipId.equalsIgnoreCase("")))
		    {
			if (StringUtils.isNotEmpty(memberBean.getFormattedName()))
			{
			    myReportBean.setOtherName(memberBean.getFormattedName());
			}
			if (StringUtils.isNotEmpty(memberBean.getFormattedAddress()))
			{
			    myReportBean.setOtherAddress(memberBean.getFormattedAddress());
			}
			else
			{
			    myReportBean.setOtherAddress("No address on file");
			}
			if (StringUtils.isNotEmpty(memberBean.getFormattedPhone()))
			{
			    myReportBean.setOtherPhone(memberBean.getFormattedPhone());
			}
			else
			{
			    myReportBean.setOtherPhone("No phone on file");
			}
			if (StringUtils.isNotEmpty(memberBean.getRelationshipDesc()))
			{
			    myReportBean.setOtherRelationship(memberBean.getRelationshipDesc());
			}
			guardianIsOther = "True";
		    }
		}
	    }
	}

	if (StringUtils.isEmpty(myReportBean.getJuvFormattedAddress()))
	{
	    myReportBean.setJuvFormattedAddress("NONE LISTED");
	}

	// set family members

	if (briefingDetailsForm.getFamilyMembers() != null)
	{
	    List<JuvenileReferralMemberDetailsBean> juvFamilyList = JuvenileReferralHelper.populateFromGuardianBeanToMemBean(briefingDetailsForm.getFamilyMembers());
	    form.setMemberDetailsBeanList(juvFamilyList);
	    Iterator<JuvenileReferralMemberDetailsBean> familyMemberList = juvFamilyList.iterator();
	    while (familyMemberList.hasNext())
	    {
		JuvenileReferralMemberDetailsBean memberBean = (JuvenileReferralMemberDetailsBean) familyMemberList.next();
		if (StringUtils.isNotEmpty(memberBean.getRelationshipDesc()) || StringUtils.isNotEmpty(memberBean.getRelationshipId()))
		{

		    String relationShip = "";
		    String relationShipId = "";

		    if (StringUtils.isNotEmpty(memberBean.getRelationshipDesc()))
		    {
			relationShip = memberBean.getRelationshipDesc();
		    }
		    if (StringUtils.isNotEmpty(memberBean.getRelationshipId()))
		    {
			relationShipId = memberBean.getRelationshipId();
		    }

		    if (relationShip.equalsIgnoreCase("BIRTH FATHER") || relationShipId.equalsIgnoreCase("BF"))
		    {
			if (StringUtils.isNotEmpty(memberBean.getFormattedName()))
			{
			    myReportBean.setFathersName(memberBean.getFormattedName());
			}
			if (StringUtils.isNotEmpty(memberBean.getFormattedAddress()))
			{
			    myReportBean.setFathersAddress(memberBean.getFormattedAddress());
			}
			else
			{
			    myReportBean.setFathersAddress("No address on file");
			}
			if (StringUtils.isNotEmpty(memberBean.getFormattedPhone()))
			{
			    myReportBean.setFathersPhone(memberBean.getFormattedPhone());
			}
			else
			{
			    myReportBean.setFathersPhone("No phone on file");
			}
		    }
		    if (relationShip.equalsIgnoreCase("BIRTH MOTHER") || relationShipId.equalsIgnoreCase("BM"))
		    {
			if (StringUtils.isNotEmpty(memberBean.getFormattedName()))
			{
			    myReportBean.setMothersName(memberBean.getFormattedName());
			}
			if (StringUtils.isNotEmpty(memberBean.getFormattedAddress()))
			{
			    myReportBean.setMothersAddress(memberBean.getFormattedAddress());
			}
			else
			{
			    myReportBean.setMothersAddress("No address on file");
			}
			if (StringUtils.isNotEmpty(memberBean.getFormattedPhone()))
			{
			    myReportBean.setMothersPhone(memberBean.getFormattedPhone());
			}
			else
			{
			    myReportBean.setMothersPhone("No phone on file");
			}
		    }
		    // check if guardian is other
		    if (guardianIsOther == "False")
		    {

			// only 1 RELATIVE OR SIGNIFICANT OTHER PERSON
			if ((!relationShip.equalsIgnoreCase("BIRTH FATHER")) && (!relationShip.equalsIgnoreCase("BIRTH MOTHER")) && (!relationShip.equalsIgnoreCase("")) || (!relationShipId.equalsIgnoreCase("BF")) && (!relationShipId.equalsIgnoreCase("BM")) && (!relationShipId.equalsIgnoreCase("")))
			{
			    if (StringUtils.isNotEmpty(memberBean.getFormattedName()))
			    {
				myReportBean.setOtherName(memberBean.getFormattedName());
			    }
			    if (StringUtils.isNotEmpty(memberBean.getFormattedAddress()))
			    {
				myReportBean.setOtherAddress(memberBean.getFormattedAddress());
			    }
			    else
			    {
				myReportBean.setOtherAddress("No address on file");
			    }
			    if (StringUtils.isNotEmpty(memberBean.getFormattedPhone()))
			    {
				myReportBean.setOtherPhone(memberBean.getFormattedPhone());
			    }
			    else
			    {
				myReportBean.setOtherPhone("No phone on file");
			    }
			    if (StringUtils.isNotEmpty(memberBean.getRelationshipDesc()))
			    {
				myReportBean.setOtherRelationship(memberBean.getRelationshipDesc());
			    }
			}
		    }
		    relationShip = "";
		}
	    }
	}

	// set alias list 

	StringBuffer aliasBuffer = new StringBuffer();

	Collection<JuvenileAliasResponseEvent> juvenileAliasList = new ArrayList<JuvenileAliasResponseEvent>();

	juvenileAliasList = briefingDetailsForm.getProfileDetail().getAliasList();

	if (juvenileAliasList != null)
	{
	    Iterator<JuvenileAliasResponseEvent> iter = juvenileAliasList.iterator();
	    while (iter.hasNext())
	    {
		JuvenileAliasResponseEvent alias = iter.next();
		aliasBuffer.append(alias.getAliasName());
		aliasBuffer.append(", ");
	    }
	}

	myReportBean.setAlias(aliasBuffer.toString());

	aRequest.getSession().setAttribute("reportInfo", myReportBean);
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	//let the pdfManager know that the report should be saved in the request during report creation
	aRequest.setAttribute("isPdfSaveNeeded", "true");
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.REFERRAL_RECEIPT);
	/*
	    	byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
	    
	    	if( pdfDocument == null || pdfDocument.length < 1 )
	    	{
	    		sendToErrorPage( aRequest, "error.generic", "Problems generating report. " + contactAdmin ) ;
	    		return aMapping.findForward(UIConstants.FAILURE);
	    	}
	    	
	    	CompositeResponse compResp = sendPrintRequest(
	    			"REPORTING::REFERRAL_RECEIPT", myReportBean, null);
	    	
	    	ReportResponseEvent aReportRespEvt = (ReportResponseEvent)
	    			MessageUtil.filterComposite(compResp, ReportResponseEvent.class);
	    	
	    	if( aReportRespEvt == null || 
	    			aReportRespEvt.getContent() == null ||
	    			aReportRespEvt.getContent().length < 1 )
	    	{
	    		sendToErrorPage( aRequest, "error.generic",
	    				"Problems generating report. " +contactAdmin ) ;
	    		return aMapping.findForward(UIConstants.FAILURE);
	    	}*/
	/*
		SaveInterviewDocumentEvent saveEvt = new SaveInterviewDocumentEvent();
		saveEvt.setCasefileId(form.getCasefileId());
		saveEvt.setDocument(pdfDocument);
		saveEvt.setDocumentTypeCodeId("RAA");
		// CODE_TABLE_NAME = INTERVIEW_DOCTYPE);
	
		CompositeResponse response1 = postRequestEvent(saveEvt);
		InterviewReportResponseEvent respEvt = (InterviewReportResponseEvent)
				MessageUtil.filterComposite(response1, InterviewReportResponseEvent.class);
		if( respEvt == null )
		{
			sendToErrorPage( aRequest, "error.generic", 
					"Problems saving the generated report. " +contactAdmin ) ;
			return aMapping.findForward(UIConstants.FAILURE);
		} */

	// no need to forward since response has already been committed.
	return aMapping.findForward(UIConstants.SUCCESS);
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
     * Cancel
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
	form.setOffenseCode("");
	form.setOffenseCodeDesc("");
	form.setOffenseDate("");
	//form.setAction("");
	form.setUpdateOffenseFlag("N");
	if (form.getAction() == null || form.getAction().equalsIgnoreCase("updateReferral"))
	{
	    form.setKeyMapLocation("");
	    form.setInvestigationNum("");
	    form.setUpdateRefStatFlag("");
	    return aMapping.findForward("updateReferral");
	}
	else
	{
	    form.getReferralOffenseBean().setKeyMapLocation("");
	    form.getReferralOffenseBean().setInvestigationNum("");
	    return aMapping.findForward(UIConstants.SUCCESS);
	}
    }

    /**
     * Add offense
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward addOffense(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setIsNumericCode23(null);
	form.setIsTransferredOffense(null);
	//form.setReferralList(new ArrayList());
	form.setOriginalChargeReferrals(new ArrayList());
	form.setAdminReferralFlag("");

	if (form.getAction() != null && !form.getAction().equalsIgnoreCase("updateReferral"))
	    form.setAction("addToOffenseList");
	if (form.getOffenseList().size() == 11)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Offense list must not exceed 11 entries. Please remove an Offense from list to proceed"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	//	form.setOffenseCode(form.getOffenseCode());
	JuvenileReferralOffenseBean offenseResp = new JuvenileReferralOffenseBean();
	JuvenileOffenseCodeResponseEvent jocEvent = JuvenileReferralHelper.validateOffenseCd(form.getOffenseCode());
	JJSJuvenile jjsJuv = JJSJuvenile.find(form.getJuvenileNum());
	Date bDate = jjsJuv.getBirthDate();
	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	Date offenseDate = null;

	try
	{
	    offenseDate = df.parse(form.getOffenseDate());
	}
	catch (ParseException e1)
	{
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	/*JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode",form.getOffenseCode());
	if (offenseCode != null)
	{
	    jocEvent.setNumericCode(offenseCode.getNumericCode());
	}*/
	//	String offnsecd=form.getOffenseCode();
	int ageAtOffense = getAgeAtOffense(bDate, offenseDate);
	int getAgeInYears = getAgeInYears(bDate);
	form.setAgeAtOffense(getAgeAtOffense(bDate, offenseDate));
	if (jocEvent == null)
	{
	    //form.setAction("validateOffenseCd"); BUG 87252 
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Discontinued or Invalid Offense Code. Please Correct and Retry"));
	    saveErrors(aRequest, errors);
	    if (form.getAction() == null || form.getAction().equalsIgnoreCase("updateReferral")) //BUG 87252 
	    {
		form.setUpdateRefStatFlag("");
		return aMapping.findForward("updateReferral");
	    }
	    else
	    {
		form.setAction("");
		return aMapping.findForward(UIConstants.SUCCESS);
	    }
	}
	else
	{
	    form.getReferralOffenseBean().setOffenseDescription(jocEvent.getShortDescription());
	    form.getReferralOffenseBean().setOffenseCode(jocEvent.getOffenseCode());
	    if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
	    {
		form.getReferralOffenseBean().setKeyMapLocation(form.getKeyMapLocation());
		form.getReferralOffenseBean().setInvestigationNum(form.getInvestigationNum());
	    }
	    if (form.getOffenseDate() != null)
		form.getReferralOffenseBean().setOffenseDate(DateUtil.stringToDate(form.getOffenseDate(), DateUtil.DATE_FMT_1));
	    try
	    {
		BeanUtils.copyProperties(offenseResp, form.getReferralOffenseBean());
	    }
	    catch (IllegalAccessException e)
	    {
		e.printStackTrace();
	    }
	    catch (InvocationTargetException e)
	    {
		e.printStackTrace();
	    }

	    //check for severitySubType R - Administrative offense
	    // if (jocEvent.getSeveritySubtype() != null)
	    //  {
	    if ("Y".equalsIgnoreCase(jocEvent.getAgeRestrict()) && (getAgeInYears > 21 || getAgeInYears < 10))
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile's age does not qualify for  offense addition.  Contact Data Corrections for assistance"));
		saveErrors(aRequest, errors);
		if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
		{
		    return (aMapping.findForward("updateReferral"));
		}
		else
		{
		    return aMapping.findForward(UIConstants.FAILURE);
		}
	    }
	    if ("Y".equalsIgnoreCase(jocEvent.getAgeRestrict()) && ageAtOffense >= 17)
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile does not qualify for offense addition, based on age and date of offense.  Contact Data Corrections for assistance"));
		saveErrors(aRequest, errors);
		if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
		{
		    return (aMapping.findForward("updateReferral"));
		}
		else
		{
		    return aMapping.findForward(UIConstants.FAILURE);
		}
	    }

	    if (getAgeInYears > 21 || getAgeInYears < 10)
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile does not qualify for offense addition.  Contact Data Corrections for assistance"));
		saveErrors(aRequest, errors);
		if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
		{
		    return (aMapping.findForward("updateReferral"));
		}
		else
		{
		    return aMapping.findForward(UIConstants.FAILURE);
		}
	    }

	    if ("R".equalsIgnoreCase(jocEvent.getSeveritySubtype()) || "Z".equalsIgnoreCase(jocEvent.getSeveritySubtype())) //Task 87081
	    {
		form.setAssignmentType("ADM");
		form.setDisbleAssignment("Y");
		form.setSupervisionCat("PP");
		form.setSupervisionType("ISS");
		form.setCasefileGenerate("N");
		//GET ALL SUPERVISION CATEGORIES AND TYPES
		form.setSupervisionCategories(CodeHelper.getSupervisionCategories());
		form.setSupervisionTypes(CodeHelper.getSupervisionTypes());
		form.setJpo("UVANC");
	    }
	    else
		if (ageAtOffense < 17)
		{
		    if (jocEvent.getSeveritySubtype() != null && jocEvent.getSeveritySubtype().equalsIgnoreCase("T")) //transferred offenses
		    {
			form.setAssignmentType("TRN");
			form.setDisableForTRN("Y");
			form.setCasefileGenerate("Y");
			form.setSupervisionCategories(CodeHelper.getSupervisionCategories());
			form.setSupervisionTypes(CodeHelper.getSupervisionTypes());
		    }
		}
		else

		    if (ageAtOffense >= 17 && "T".equalsIgnoreCase(jocEvent.getSeveritySubtype()))
		    {

			form.setIsTransferredOffense("yes");
			form.setAgeAtOffense(getAgeAtOffense(bDate, offenseDate));
			form.setAssignmentType("TRN");
			form.setDisableForTRN("Y");
			form.setCasefileGenerate("Y");
			form.setSupervisionCategories(CodeHelper.getSupervisionCategories());
			form.setSupervisionTypes(CodeHelper.getSupervisionTypes());

		    }

	    if ("N".equalsIgnoreCase(jocEvent.getAgeRestrict()) && "23".equals(jocEvent.getNumericCode()))
	    {
		form.setAgeAtOffense(getAgeAtOffense(bDate, offenseDate));
		form.setIsNumericCode23("yes");
		form.setAssignmentType("SBQ");
		//form.setCasefileGenerate("Y");
		//JuvenileReferralHelper.jpoOfRecord(form.getJuvenileNum(), form); is this really needed here??? RRY
		String supervisionType = "";
		String supervisionTypeId = "";
		String supervisionCategoryId = "";
		String supervisionCategory = "";
		GetJuvenileCasefilesEvent getEvent = new GetJuvenileCasefilesEvent();
		getEvent.setJuvenileNum(form.getJuvenileNum());

		List<JuvenileCasefileResponseEvent> juvenileCasfileResps = MessageUtil.postRequestListFilter(getEvent, JuvenileCasefileResponseEvent.class);
		if (juvenileCasfileResps.size() > 0)
		{
		    Collections.sort((List<JuvenileCasefileResponseEvent>) juvenileCasfileResps, new Comparator<JuvenileCasefileResponseEvent>() {
			@Override
			public int compare(JuvenileCasefileResponseEvent evt1, JuvenileCasefileResponseEvent evt2)
			{
			    if (evt1.getSequenceNum() != null && evt2.getSequenceNum() != null)
				return Integer.valueOf(evt2.getSequenceNum()).compareTo(Integer.valueOf(evt1.getSequenceNum()));
			    else
				return -1;
			}
		    });

		    //set supervision to form
		    for (int x = 0; x < juvenileCasfileResps.size(); x++)
		    {

			JuvenileCasefileResponseEvent casefile = juvenileCasfileResps.get(x);
			if ("A".equalsIgnoreCase(casefile.getCaseStatusId()))
			{

			    GetOfficerProfilesByAttributeEvent event = (GetOfficerProfilesByAttributeEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILESBYATTRIBUTE);
			    event.setAttributeName("OID");
			    event.setAttributeValue(casefile.getProbationOfficeId());
			    List<OfficerProfileResponseEvent> officerprofiles = MessageUtil.postRequestListFilter(event, OfficerProfileResponseEvent.class);
			    if (officerprofiles.size() > 0)
			    {
				form.setJpo(officerprofiles.get(0).getUserId());
			    }
			    form.setSubsequentCasefileId(casefile.getSupervisionNum());
			    supervisionType = casefile.getSupervisionType();
			    supervisionTypeId = casefile.getSupervisionTypeId();
			    supervisionCategoryId = casefile.getSupervisionCategoryId();
			    supervisionCategory = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, supervisionCategoryId);
			    break;
			}
		    }

		    //Supervision Category
		    CodeResponseEvent supervisionResp = new CodeResponseEvent();
		    supervisionResp.setCode(supervisionCategoryId);
		    supervisionResp.setDescription(supervisionCategory);
		    supervisionResp.setDescriptionWithCode(supervisionCategoryId + "-" + supervisionCategory);
		    ArrayList temp = new ArrayList();
		    temp.add(supervisionResp);
		    form.setSupervisionCategories(temp);
		    //Supervision Type
		    supervisionResp = new CodeResponseEvent();
		    supervisionResp.setCode(supervisionTypeId);
		    supervisionResp.setDescription(supervisionType);
		    supervisionResp.setDescriptionWithCode(supervisionTypeId + "-" + supervisionType);
		    temp = new ArrayList();
		    temp.add(supervisionResp);
		    form.setSupervisionTypes(temp);

		    form.setSupervisionCat(supervisionCategoryId);
		    form.setSupervisionType(supervisionTypeId);

		}

		if (form.getSubsequentCasefileId() == null || form.getSubsequentCasefileId().equals(""))
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "An Active Supervision is Required to Assign a Subsequent Referral"));
		    saveErrors(aRequest, errors);

		    if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
		    {
			return (aMapping.findForward("updateReferral"));
		    }
		    else
		    {
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		}

		form.setDisbleAssignment("Y");
	    }

	    /*    		    	else
	            			if("Y".equalsIgnoreCase(jocEvent.getAgeRestrict()) && ageAtOffense >= 17)
	            			{
	            			    ActionErrors errors = new ActionErrors();
	            				 errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile's age exceeds offense addition.  Contact Data Corrections for assistance"));
	            				 saveErrors(aRequest, errors);
	            				 return aMapping.findForward(UIConstants.FAILURE);
	            			}*/

	    //}
	    if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
	    {
		offenseResp.setSequenceNum(form.getNextOffenseSeqNum());
		form.setNextOffenseSeqNum(Integer.parseInt(form.getNextOffenseSeqNum()) + 1 + "");
	    }

	    //check to see if admin offense and crim are on the same list
	    List<JuvenileReferralOffenseBean> tempList = form.getOffenseList();
	    Iterator listIter = tempList.iterator();

	    while (listIter.hasNext())
	    {

		JuvenileReferralOffenseBean bean = (JuvenileReferralOffenseBean) listIter.next();
		JuvenileOffenseCodeResponseEvent code = JuvenileReferralHelper.validateOffenseCd(bean.getOffenseCode());
		ActionErrors errors = new ActionErrors();

		if ("AC".equalsIgnoreCase(code.getCategory()) && !jocEvent.getCategory().equalsIgnoreCase(code.getCategory()))
		{
		    form.setDisableAddRefBtn(true);
		    if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
		    {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Criminal Offenses and Administrative Offenses cannot be added to same referral. Offense creation has not been completed."));
			saveErrors(aRequest, errors);
			return (aMapping.findForward("updateReferral"));
		    }
		    else
		    {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Criminal Offenses and Administrative Offenses cannot be added to same referral. Offense creation has not been completed."));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		    }

		}
		else
		{

		    if (!"AC".equalsIgnoreCase(code.getCategory()) && "AC".equalsIgnoreCase(jocEvent.getCategory()))
		    {
			form.setDisableAddRefBtn(true);
			if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
			{
			    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Criminal Offenses and Administrative Offenses cannot be added to same referral. Offense creation has not been completed."));
			    saveErrors(aRequest, errors);
			    return (aMapping.findForward("updateReferral"));
			}
			else
			{
			    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Criminal Offenses and Administrative Offenses cannot be added to same referral. Offense creation has not been completed."));
			    saveErrors(aRequest, errors);
			    return aMapping.findForward(UIConstants.FAILURE);
			}

		    }
		}
	    }
	    form.getOffenseList().add(offenseResp);
	    form.getOffenseCodeList().add(form.getOffenseCode());
	    form.getReferralOffenseBean().clear();
	    form.setOffenseCode("");
	    form.setKeyMapLocation("");
	    form.setInvestigationNum("");
	    form.setOffenseDate("");

	    //SEVERITY = 0  AND SEVERITYSUBTYPE <> 'T' 
	    if ("0".equalsIgnoreCase(jocEvent.getSeverity()) && !"T".equalsIgnoreCase(jocEvent.getSeveritySubtype()) && !"P".equalsIgnoreCase(jocEvent.getSeveritySubtype()))
	    {

		List<JuvenileProfileReferralListResponseEvent> addReferralsList = new ArrayList<JuvenileProfileReferralListResponseEvent>();

		GetJJSReferralDetailsEvent event = (GetJJSReferralDetailsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSREFERRALDETAILS);
		event.setJuvenileNum(form.getJuvenileNum());

		List<JuvenileProfileReferralListResponseEvent> juvProfRefListEvt = MessageUtil.postRequestListFilter(event, JuvenileProfileReferralListResponseEvent.class);

		//sorts in descending order by Referral Num. 
		Collections.sort((List<JuvenileProfileReferralListResponseEvent>) juvProfRefListEvt, new Comparator<JuvenileProfileReferralListResponseEvent>() {
		    @Override
		    public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
		    {
			if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
			    return evt2.getReferralNumber().compareTo(evt1.getReferralNumber());
			else
			    return -1;
		    }
		});

		if (juvProfRefListEvt.size() > 0)
		{

		    JuvenileProfileReferralListResponseEvent topResp = juvProfRefListEvt.get(0);
		    form.setMaxCrimReferral(topResp.getReferralNumber());
		}

		for (int x = 0; x < juvProfRefListEvt.size(); x++)
		{

		    JuvenileProfileReferralListResponseEvent resp = juvProfRefListEvt.get(x);
		    if (resp.getCloseDate() == null && (Integer.valueOf(resp.getReferralNumber()) % 10 == 0) && !"AC".equalsIgnoreCase(resp.getOffenseCategory()))
		    {
			addReferralsList.add(resp);
		    }
		    else
			if (resp.getCloseDate() == null && "AC".equalsIgnoreCase(resp.getOffenseCategory()) && StringUtils.isEmpty(resp.getInactiveInd()) && ("T".equalsIgnoreCase(resp.getSeveritySubtype()) || "P".equalsIgnoreCase(resp.getSeveritySubtype())))
			{
			    addReferralsList.add(resp);
			}
		}

		// Add to Original Charge referrals to form
		if (addReferralsList.size() > 0)
		{

		    form.setOriginalChargeReferrals(addReferralsList);
		    form.setAdminReferralFlag("Y");

		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "The associated Criminal Charge Referral must be selected below to create the Admin Referral"));
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.FAILURE);

		}

	    }
	}
	if (form.getAction() != null && !form.getAction().equalsIgnoreCase("updateReferral"))
	{
	    form.setAction("");
	    return aMapping.findForward(UIConstants.SUCCESS);
	}
	else
	{
	    form.setUpdateRefStatFlag("");
	    return aMapping.findForward("updateReferral");
	}
    }

    private static int getAgeAtOffense(Date dob, Date offenseDate)
    {
	if (dob == null)
	{
	    return 0;
	}
	Calendar birthdate = Calendar.getInstance();
	birthdate.setTime(dob);
	Calendar dateOfOffense = Calendar.getInstance();
	dateOfOffense.setTime(offenseDate);
	int age = 0;
	age = dateOfOffense.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
	birthdate.add(Calendar.YEAR, age);
	if (dateOfOffense.before(birthdate))
	{
	    age--;
	}
	return age;
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
     * removeOffense
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward removeOffense(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm myForm = (JuvenileReferralForm) aForm;
	boolean found = false;

	String mySelectedOffense = myForm.getSelectedValue();
	if (notNullNotEmptyString(mySelectedOffense))
	{
	    if (myForm.getOffenseList() != null && myForm.getOffenseList().size() > 0)
	    {
		if (!(myForm.getOffenseList().size() <= Integer.parseInt(mySelectedOffense)))
		{

		    ((List<JuvenileReferralOffenseBean>) myForm.getOffenseList()).remove(Integer.parseInt(mySelectedOffense));
		    //if this is the only administrative offense then assignment details will reset
		    Iterator offIter = myForm.getOffenseList().iterator();
		    while (offIter.hasNext())
		    {
			JuvenileReferralOffenseBean tempOffense = (JuvenileReferralOffenseBean) offIter.next();
			JuvenileOffenseCodeResponseEvent jocEvent = JuvenileReferralHelper.validateOffenseCd(tempOffense.getOffenseCode());
			if (jocEvent.getSeveritySubtype() != null && jocEvent.getSeveritySubtype().equalsIgnoreCase("R") || jocEvent.getSeveritySubtype().equalsIgnoreCase("Z")) //Task 87081
			{
			    found = true;
			    myForm.setAssignmentType("ADM");
			    myForm.setDisbleAssignment("Y");
			    myForm.setSupervisionCat("PP");
			    myForm.setSupervisionType("ISS");
			    myForm.setCasefileGenerate("N");
			    //GET ALL SUPERVISION CATEGORIES AND TYPES
			    myForm.setSupervisionCategories(CodeHelper.getSupervisionCategories());
			    myForm.setSupervisionTypes(CodeHelper.getSupervisionTypes());
			    myForm.setJpo("UVANC");
			    break;
			}
			else
			    if (jocEvent.getSeveritySubtype() != null && jocEvent.getSeveritySubtype().equalsIgnoreCase("T"))
			    {
				found = true;
				myForm.setAssignmentType("TRN");
				myForm.setDisableForTRN("Y");
				myForm.setCasefileGenerate("Y");
				myForm.setSupervisionCategories(CodeHelper.getSupervisionCategories());
				myForm.setSupervisionTypes(CodeHelper.getSupervisionTypes());
				break;
			    }
		    }
		    if (!found)
		    {
			myForm.setAssignmentType("");
			myForm.setDisbleAssignment("N");
			myForm.setDisableForTRN("N");
			myForm.setSupervisionCat("");
			myForm.setSupervisionType("");
			myForm.setJpo("");
			myForm.setSupervisionCategories(new ArrayList());
			myForm.setSupervisionTypes(new ArrayList());
		    }
		}
	    }
	    myForm.getReferralOffenseBean().clear();
	}
	myForm.setOffenseDate("");
	myForm.setAction("");
	return aMapping.findForward(UIConstants.SUCCESS);
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
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;

	JuvenileOffenseCodeResponseEvent jocEvent = JuvenileReferralHelper.validateOffenseCd(form.getOffenseCode());
	if (jocEvent != null)
	{
	    String lit = jocEvent.getShortDescription() + " (" + jocEvent.getCategory() + ")";
	    form.setOffenseCodeDesc(lit);
	    //form.setPetitionAllegationSev(jocEvent.getSeverity());

	    ActionMessages messageHolder = new ActionMessages();
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.offenseCodeValid"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	}
	else
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Discontinued or Invalid Offense Code. Please Correct and Retry"));
	    saveErrors(aRequest, errors);
	    if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
		return aMapping.findForward("updateReferral");
	    else
		return aMapping.findForward(UIConstants.FAILURE);
	}
	String forward = "";
	if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
	    forward = "updateReferral";
	else
	{
	    forward = UIConstants.SUCCESS;
	    form.setAction("validateOffenseCd");
	}
	form.setUpdateRefStatFlag("");
	return aMapping.findForward(forward);
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
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;

	if (form.getAction() != null && !form.getAction().equalsIgnoreCase("updateReferral"))
	    form.setAction("CreateReferralOffenseSearch");
	//form.setConfirmMsg(UIConstants.EMPTY_STRING);
	form.setMessage(UIConstants.EMPTY_STRING);
	form.setAlphaCodeId(UIConstants.EMPTY_STRING);
	form.setShortDesc(UIConstants.EMPTY_STRING);
	form.setDpsCodeId(UIConstants.EMPTY_STRING);
	form.setCategoryId(UIConstants.EMPTY_STRING);
	form.setSelectedValue(UIConstants.EMPTY_STRING);
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
	for (int x = 0; x < temp1.size(); x++)
	{
	    CodetableDataResponseEvent ctrEvent = (CodetableDataResponseEvent) temp1.get(x);
	    ocEvent = new JuvenileCasefileOffenseCodeResponseEvent();
	    eventValues = CollectionUtil.iteratorToList(ctrEvent.getValueMap().values().iterator());
	    inactive = UIConstants.EMPTY_STRING;
	    // value of 'y' is based on DISPLAYORDER value in CODETBLREGATTR
	    // table for CODETBLREG_ID = 27
	    //some of the indexes 'Y' fixed as part of BUG# 84401
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
		if (y == 3)
		{
		    ocEvent.setReportGroup(eventValues.get(y).toString());
		}
		if (y == 4)
		{
		    ocEvent.setShortDescription(eventValues.get(y).toString());
		}
		if (y == 6)
		{
		    ocEvent.setLongDescription(eventValues.get(y).toString());
		}
		if (y == 5)
		{
		    ocEvent.setDpsCode(eventValues.get(y).toString());
		}
		if (y == 8)
		{
		    ocEvent.setSeverity(eventValues.get(y).toString());
		}
		if (y == 15)
		{
		    ocEvent.setSeverityType(eventValues.get(y).toString());
		}
		if (y == 14)
		{
		    ocEvent.setSeveritySubType(eventValues.get(y).toString());
		}
		if (y == 13)
		{
		    inactive = eventValues.get(y).toString();
		}
	    }
	    if (UIConstants.EMPTY_STRING.equals(inactive))
	    {
		temp2.add(ocEvent);
	    }
	}
	form.setCodetableDataList(temp2);
	temp1 = null;
	temp2 = null;
	eventValues = null;
	inactive = null;*/
	GetAllJuvenileOffenseCodesEvent requestEvent = (GetAllJuvenileOffenseCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETALLJUVENILEOFFENSECODES);
	List<JuvenileCasefileOffenseCodeResponseEvent> codes = MessageUtil.postRequestListFilter(requestEvent, JuvenileCasefileOffenseCodeResponseEvent.class);
	List<JuvenileCasefileOffenseCodeResponseEvent> filteredCodes = new ArrayList<>();
	for (JuvenileCasefileOffenseCodeResponseEvent code : codes)
	{
	    if (!"Y".equalsIgnoreCase(code.getInactiveInd()) && !"Y".equalsIgnoreCase(code.getDiscontCode()))
	    {
		filteredCodes.add(code);
	    }
	}
	Collections.sort(filteredCodes);

	form.setCodetableDataList(filteredCodes);
	form.setUpdateRefStatFlag("");
	return aMapping.findForward("searchOffenses");
    }

    /*
     * 
     */
    private boolean notNullNotEmptyString(String str)
    {
	return (str != null && (str.length() > 0));
    }

    /**
     * stringNullOrNotEqual
     * 
     * @param theString
     * @param comparedTo
     * @return boolean: string is not null and is not equal to the compared to
     *         string
     */
    private boolean stringNullOrNotEqual(String theString, String comparedTo)
    {
	return (theString == null || !theString.trim().equalsIgnoreCase(comparedTo));
    }

    /**
     * sets the current casefile for the juv
     * 
     * @param casefileId
     * @param form
     */
    private void setCurrentCasefile(String casefileId, JuvenileBriefingDetailsForm form)
    {
	form.setSupervisionNum(casefileId);
	GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);

	getCasefileEvent.setSupervisionNumber(casefileId);

	CompositeResponse response = MessageUtil.postRequest(getCasefileEvent);
	JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileResponseEvent.class);

	// Populate casefile found
	if (casefile != null)
	{
	    form.setCurrentCasefile(casefile);
	}
    }

    /**
     * setProfileDetail gets the juv profile info
     * 
     * @param juvenileNum
     * @param form
     */
    private void setProfileDetail(String juvenileNum, JuvenileBriefingDetailsForm form)
    {
	GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

	reqProfileMain.setJuvenileNum(juvenileNum);
	reqProfileMain.setFromProfile(form.getFromProfile());
	CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
	JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);

	form.setJisInfo(new JuvenileJISResponseEvent());
	if (juvProfileRE != null)
	{
	    form.setProfileDetail(juvProfileRE);
	    form.setProfileDescriptions();
	    if (juvProfileRE.getDateOfBirth() != null)
	    { // Get age based on
	      // year
		int age = UIUtil.getAgeInYears(juvProfileRE.getDateOfBirth());
		if (age > 0)
		{
		    form.setAge(String.valueOf(age));
		}
		else
		{
		    form.setAge(UIConstants.EMPTY_STRING);
		}
	    }
	    Collection jisResps = juvProfileRE.getJisInfo();
	    Collections.sort((List) jisResps);
	    Iterator jisIter = jisResps.iterator();
	    if (jisIter.hasNext())
	    {
		JuvenileJISResponseEvent jis = (JuvenileJISResponseEvent) jisIter.next();
		form.setJisInfo(jis);
	    }

	    form.setInMentalHealthServices(juvProfileRE.isMentalHealthServices());
	}
    }

    protected void sendToErrorPage(HttpServletRequest aRequest, String msgKey, String param)
    {
	ActionErrors errors = new ActionErrors();
	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msgKey, param));
	saveErrors(aRequest, errors);
    }

    private void setNumberOfDetentionDays(ReferralReceiptPrintBean referralReceiptReportBean, Collection juvProfRefListEvt, String juvNumber, String referralNum)
    {

	String juvenileNum = juvNumber;
	String refNum = referralNum;
	List<CommonAppForm.Placement> detentionFacilities = new ArrayList<CommonAppForm.Placement>();

	GetNonDetentionJuvenileFacilitiesEvent facilityEvent = new GetNonDetentionJuvenileFacilitiesEvent();
	CompositeResponse response = MessageUtil.postRequest(facilityEvent);
	List<JuvenileFacilityResponseEvent> facilityColl = MessageUtil.compositeToList(response, JuvenileFacilityResponseEvent.class);

	Collection referrals = (Collection) juvProfRefListEvt;

	ReferralReceiptPrintBean myReportBean = (ReferralReceiptPrintBean) referralReceiptReportBean;

	// for each referral get the detention facilities
	Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referrals.iterator();
	while (referralIterator.hasNext())
	{
	    GetJuvenileDetentionFacilitiesEvent det = (GetJuvenileDetentionFacilitiesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILEDETENTIONFACILITIES);
	    String refNo = null;
	    JuvenileProfileReferralListResponseEvent referral = referralIterator.next();
	    if (referral.getReferralNumber() != null)
	    {
		if (referral.getReferralNumber().contains("-"))
		{
		    refNo = referral.getReferralNumber().split("-")[0];
		}
		else
		{
		    refNo = referral.getReferralNumber();
		}
	    }
	    det.setReferralNum(refNo);
	    det.setJuvenileNum(juvenileNum);
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch.postEvent(det);
	    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	    MessageUtil.processReturnException(compositeResponse);

	    long totalDays = 0;

	    Collection<JuvenileDetentionFacilitiesResponseEvent> facilities = MessageUtil.compositeToCollection(compositeResponse, JuvenileDetentionFacilitiesResponseEvent.class);
	    if (facilities != null)
	    {
		for (JuvenileDetentionFacilitiesResponseEvent detentionResp : facilities)
		{
		    Iterator<JuvenileFacilityResponseEvent> iter = facilityColl.iterator();
		    while (iter.hasNext())
		    {
			JuvenileFacilityResponseEvent resp = iter.next();
			if (resp.getCode().equals(detentionResp.getDetainedFacility()))
			{
			    if (detentionResp.getReleaseDate() != null)
			    {
				long days = TimeUnit.MILLISECONDS.toDays((detentionResp.getReleaseDate().getTime() - detentionResp.getAdmitDate().getTime())) + 1;
				totalDays = totalDays + days;
			    }
			    else
			    {
				long days = TimeUnit.MILLISECONDS.toDays((DateUtil.getCurrentDate().getTime() - detentionResp.getAdmitDate().getTime())) + 1;
				totalDays = totalDays + days;
			    }
			    if (refNum.equals(referral.getReferralNumber()) && totalDays > 0)
			    {
				myReportBean.setDetained("Y");
				myReportBean.setFacility(detentionResp.getDetainedFacilityDesc());
			    }
			}
		    }
		}
		referral.setDetDays(totalDays);
	    }
	}
    }

    /**
     * update Referral
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward loadReferral(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward(UIConstants.UPDATE);
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setAction("updateReferral");
	form.setUpdateMessage("");
	form.setSubsequentMessage("");
	form.setUpdateAsmntTypeFlag("");
	form.setOffenseList(null);
	String selectedVal = form.getSelectedValue();
	List<JJSSVIntakeHistory> intakeHistoryList = new ArrayList<JJSSVIntakeHistory>();
	Iterator iter = form.getReferralList().iterator();
	while (iter.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent resp = (JuvenileProfileReferralListResponseEvent) iter.next();
	    if (resp != null && resp.getReferralNumber().equals(selectedVal))
	    {
		form.setNewRefNum(selectedVal);
		form.setNewRefDate(DateUtil.dateToString(resp.getReferralDate(), DateUtil.DATE_FMT_1));
		form.setNewRefSource(resp.getReferralSource());
		form.setNewRefRecType(resp.getRecType());
		form.setNewRefIntakeDecision(resp.getIntakeDecisionId());
		form.setCourtdecisionDate(DateUtil.dateToString(resp.getCourtDate(), DateUtil.DATE_FMT_1));	
		form.setNewRefIntakeDate(resp.getIntakeDecDate());
		form.setTjjdDateStr(resp.getTJJDDate());
		form.setProbationStartDate(DateUtil.dateToString(resp.getProbationStartDate(), DateUtil.DATE_FMT_1));
		form.setProbationEndDate(DateUtil.dateToString(resp.getProbationEndDate(), DateUtil.DATE_FMT_1));
		form.setLcUser(resp.getLcUser());
		form.setInvestigationNum("");
		form.setKeyMapLocation("");
		form.setUpdNewRefSource(resp.getReferralSource());
		form.setUpdNewRefIntakeDecision(resp.getIntakeDecisionId());
		if (form.getJotInfo() != null)
		{
		    form.getJotInfo().setAdultCoActors(new ArrayList());
		    form.getJotInfo().setJuvenileCoActors("");
		}
		IName name = SecurityUIHelper.getUserName(resp.getLcUser());
		form.setLcUserName(name.getFormattedName());
		form.setUpdateRefStatFlag("loadRef");
		JuvenileReferralOffenseBean offense = new JuvenileReferralOffenseBean();

		Collection sortedOffenses = resp.getOffenses();

		Collections.sort((List<JJSOffense>) sortedOffenses, new Comparator<JJSOffense>() {
		    @Override
		    public int compare(JJSOffense evt1, JJSOffense evt2)
		    {
			return Integer.valueOf(evt2.getSequenceNum()).compareTo(Integer.valueOf(evt1.getSequenceNum()));
		    }
		});
		Iterator offenseIter = sortedOffenses.iterator();
		ArrayList tempOffenses = new ArrayList();
		Date mostRecentOffenseDate = new Date();
		while (offenseIter.hasNext())
		{
		    JJSOffense off = (JJSOffense) offenseIter.next();
		    offense.setOffenseID(off.getOID());
		    offense.setOffenseCode(off.getOffenseCodeId());
		    offense.setOffenseDescription(off.getOffenseDescription());
		    offense.setInvestigationNum(off.getInvestigationNumber());
		    offense.setKeyMapLocation(off.getKeyMapLocation());
		    offense.setOffenseDate(off.getOffenseDate());
		    offense.setSequenceNum(off.getSequenceNum());
		    offense.setOldOffenseCode(off.getOffenseCodeId());
		    if (off.getOffenseDate() != null && off.getOffenseDate().before(mostRecentOffenseDate))
			mostRecentOffenseDate = off.getOffenseDate();
		    tempOffenses.add(offense);
		    offense = new JuvenileReferralOffenseBean();
		}
		form.setEarliestOffenseDate(DateUtil.dateToString(mostRecentOffenseDate, DateUtil.DATE_FMT_1));
		form.setOffenseList(tempOffenses);
		//get the next seq num
		// sorts in descending order by seq num.
		Collection offenses = resp.getOffenses();
		Collections.sort((List<JuvenileReferralOffenseBean>) tempOffenses, new Comparator<JuvenileReferralOffenseBean>() {
		    @Override
		    public int compare(JuvenileReferralOffenseBean evt1, JuvenileReferralOffenseBean evt2)
		    {
			return Integer.valueOf(evt2.getSequenceNum()).compareTo(Integer.valueOf(evt1.getSequenceNum()));
		    }
		});
		offenseIter = tempOffenses.iterator();
		if (offenseIter.hasNext())
		{
		    JuvenileReferralOffenseBean tempOffense = (JuvenileReferralOffenseBean) offenseIter.next();
		    form.setNextOffenseSeqNum(Integer.parseInt(tempOffense.getSequenceNum()) + 1 + "");

		}
		form.setAssignmentTypes(ComplexCodeTableHelper.getRefAssmntTypeCodes());
		form.setUpdateRefStat(resp.getReferralStatus());
		form.setUpdateRefCloseDt(DateUtil.dateToString(resp.getCloseDate(), DateUtil.DATE_FMT_1));
		form.setCurrentCasefileId(resp.getCasefileId());
		//get the assignment details  
		if (resp.getAssignmentDate() == null)
		{
		    form.setAssignmentType("REC");
		    form.setSupervisionCat("RC");
		    form.setSupervisionType("RCV");
		    form.setJpo("UVREC");
		    form.setAssignmentDateStr(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
		    List categories = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_CATEGORY");
		    Iterator<JuvenileCodeTableChildCodesResponseEvent> catIter = categories.iterator();
		    ArrayList tempCategories = new ArrayList();
		    while (catIter.hasNext())
		    {
			JuvenileCodeTableChildCodesResponseEvent catResp = catIter.next();
			catResp.setDescriptionWithCode(catResp.getCode() + "-" + catResp.getDescription());
			if (catResp.getParentId() != null && catResp.getParentId().equals(form.getAssignmentType()))
			{
			    tempCategories.add(catResp);
			}
		    }
		    form.setSupervisionCategories(tempCategories);
		    List types = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_TYPE");
		    Iterator<JuvenileCodeTableChildCodesResponseEvent> typesIter = types.iterator();
		    ArrayList tempTypes = new ArrayList();
		    while (typesIter.hasNext())
		    {
			JuvenileCodeTableChildCodesResponseEvent supTypeResp = typesIter.next();
			if (supTypeResp.getParentId() != null && supTypeResp.getParentId().equals(form.getSupervisionCat()))
			{
			    supTypeResp.setDescriptionWithCode(supTypeResp.getCode() + "-" + supTypeResp.getDescription());
			    tempTypes.add(supTypeResp);
			    form.setSupervisionType(supTypeResp.getCode());
			    break;
			}
		    }
		    Collections.sort(tempTypes);
		    form.setSupervisionTypes(tempTypes);
		}
		else
		{
		    form.setSupervisionType(resp.getSupervisionTypeId());
		    form.setSupervisionCat(resp.getSupervisionCategoryId());
		    form.setAssignmentDateStr(resp.getAssignmentDate());
		    form.setJpo(resp.getJpoId());
		    form.setAssignmentType(resp.getAssignmentType());
		    //Supervision Category
		    CodeResponseEvent supervisionResp = new CodeResponseEvent();
		    supervisionResp.setCode(resp.getSupervisionCategoryId());
		    supervisionResp.setDescription(resp.getSupervisionCategory());
		    supervisionResp.setDescriptionWithCode(resp.getSupervisionCategoryId() + "-" + resp.getSupervisionCategory());
		    ArrayList temp = new ArrayList();
		    temp.add(supervisionResp);
		    form.setSupervisionCategories(temp);

		    supervisionResp = new CodeResponseEvent();
		    supervisionResp.setCode(resp.getSupervisionTypeId());
		    supervisionResp.setDescription(resp.getSupervisionType());
		    supervisionResp.setDescriptionWithCode(resp.getSupervisionTypeId() + "-" + resp.getSupervisionType());
		    temp = new ArrayList();
		    temp.add(supervisionResp);
		    form.setSupervisionTypes(temp);
		}
		if (resp.getDaLogNum() != null)
		{
		    //get the corespondents
		    form.setJotInfo(getCoActorsandCorespondents(resp.getDaLogNum()));
		    GetJOTDetailsEvent jotEvent = (GetJOTDetailsEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJOTDETAILS);
		    jotEvent.setDaLogNum(resp.getDaLogNum());
		    CompositeResponse jotResponse = MessageUtil.postRequest(jotEvent);
		    JuvenileOffenderTrackingResponseEvent juvenileOffenderTrackingResponse = (JuvenileOffenderTrackingResponseEvent) MessageUtil.filterComposite(jotResponse, JuvenileOffenderTrackingResponseEvent.class);
		    form.setDaLogNum(resp.getDaLogNum());
		    if (juvenileOffenderTrackingResponse != null)
		    {
			try
			{
			    String daDateOut = String.valueOf(juvenileOffenderTrackingResponse.getDaDateOut());
			    if (daDateOut != null || daDateOut.length() > 0)
			    {
				form.setDaDateOut(new SimpleDateFormat("yyyyMMdd").parse(daDateOut));
			    }
			    else
			    {
				form.setDaDateOut(null);
			    }
			}
			catch (Exception e)
			{
			}
			GetJuvenileJOTPetitionDetailsEvent jotpetEvent = (GetJuvenileJOTPetitionDetailsEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEJOTPETITIONDETAILS);
			jotpetEvent.setDalogNum(resp.getDaLogNum());
			jotpetEvent.setReferralNum(resp.getReferralNumber());
			CompositeResponse jotpetResponse = MessageUtil.postRequest(jotpetEvent);
			JuvenileOffenderTrackingChargeResponseEvent rsp = (JuvenileOffenderTrackingChargeResponseEvent) MessageUtil.filterComposite(jotpetResponse, JuvenileOffenderTrackingChargeResponseEvent.class);

			if (rsp != null)
			{
			    if (rsp.getRejIndicator() != null && !rsp.getRejIndicator().isEmpty() && rsp.getRejIndicator().equalsIgnoreCase("Y"))
				form.setLogStatus("REJECTED");
			    else
				form.setLogStatus(juvenileOffenderTrackingResponse.getLogStatus());
			    if (rsp.getCJISNum() != null && !rsp.getCJISNum().isEmpty())
				form.setCJIS(rsp.getCJISNum());
			    else
				form.setCJIS(juvenileOffenderTrackingResponse.getCjisNum());
			}
		    }
		    else
		    {
			form.setDaDateOut(null);
			form.setLogStatus(null);
			form.setCJIS(null);
		    }
		    //
		}
		else
		{
		    form.setDaLogNum(null);
		    form.setDaDateOut(null);
		    form.setLogStatus(null);
		    form.setCJIS(null);
		}

		intakeHistoryList = JuvenileReferralHelper.getIntakeHisoryForJuvNumRefNum(form.getJuvenileNum(), selectedVal);
		if (intakeHistoryList != null && intakeHistoryList.size() > 0)
		{
		    form.setIntakeHistoryList(intakeHistoryList);
		}

	    }

	}

	return (aMapping.findForward("updateReferral"));
    }

    /**
     * update Offense
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward updateOffense(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward(UIConstants.UPDATE);
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	String selectedVal = form.getSelectedOffense();
	form.setUpdateOffenseMsg("");
	form.setUpdateOffenseError("");

	//Bug #85184
	//Iterator<JuvenileReferralOffenseBean> iter = form.getOffenseList().iterator();
	int i = Integer.parseInt(selectedVal);
	JuvenileReferralOffenseBean offense = form.getOffenseList().get(i);
	if (offense != null)
	{
	    JuvenileOffenseCodeResponseEvent newJocEvent = JuvenileReferralHelper.validateOffenseCd(form.getOffenseCode());
	    if (newJocEvent == null) //BUG 87253
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Discontinued or Invalid Offense Code. Please Correct and Retry"));
		saveErrors(aRequest, errors);
		if (form.getAction() == null || form.getAction().equalsIgnoreCase("updateReferral"))
		{
		    form.setUpdateRefStatFlag("");
		    return aMapping.findForward("updateReferral");
		}
		else
		{
		    form.setAction("");
		    return aMapping.findForward(UIConstants.SUCCESS);
		}
	    }
	    else
	    {
		JJSJuvenile jjsJuv = JJSJuvenile.find(form.getJuvenileNum());
		Date bDate = jjsJuv.getBirthDate();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date offenseDate = null;
		try
		{
		    offenseDate = df.parse(form.getOffenseDate());
		}
		catch (ParseException e1)
		{
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}
		JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode", form.getOffenseCode());
		//		if (offenseCode != null)
		//		{
		//		    jocEvent.setNumericCode(offenseCode.getNumericCode());
		//		}
		int ageAtOffense = getAgeAtOffense(bDate, offenseDate);
		int getAgeInYears = getAgeInYears(bDate);

		if (getAgeInYears > 21 || getAgeInYears < 10)
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile's age exceeds assignment permissions.  Referral Update is not permitted. Contact Data Corrections for assistance"));
		    saveErrors(aRequest, errors);
		    return aMapping.findForward("updateReferral");
		}

		if (ageAtOffense >= 17 && (form.getOffenseCode().equalsIgnoreCase("ISCOIN") || offenseCode.getNumericCode().equals("23")))
		{
		    form.setUpdateOffenseMsg("yes");

		}

		if ((getAgeInYears >= 17 && getAgeInYears <= 21) && (ageAtOffense >= 17) && "Y".equalsIgnoreCase(newJocEvent.getAgeRestrict()))
		{
		    // form.setUpdateOffenseError("yes");
		    //form.setOffenseCode(offense.getOldOffenseCode());
		    form.setOffenseCode("");
		    form.setOffenseDate("");
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile's age exceeds assignment permissions. Referral Update is not permitted. Contact Data Corrections for assistance"));
		    saveErrors(aRequest, errors);
		    return aMapping.findForward("updateReferral");
		}

		offense.setOffenseDate(DateUtil.stringToDate(form.getOffenseDate(), DateUtil.DATE_FMT_1));
		offense.setKeyMapLocation(form.getKeyMapLocation());
		offense.setInvestigationNum(form.getInvestigationNum());

		JuvenileOffenseCodeResponseEvent jocEvent = JuvenileReferralHelper.validateOffenseCd(offense.getOldOffenseCode());
		offense.setOffenseCode(form.getOffenseCode());
		offense.setOffenseDescription(newJocEvent.getShortDescription());
		//check for severitySubType R - Administrative offense

		//check if new offense is administrative
		//Bug #86409
		//check if the code has changed - one scenario user changes offense code and then changes back
		if (newJocEvent.getCategory() != null && !"AC".equalsIgnoreCase(newJocEvent.getCategory()) && !form.getOffenseCode().equalsIgnoreCase(offense.getOldOffenseCode()))
		{
		    //enable the assignment type section
		    if (!"AC".equalsIgnoreCase(newJocEvent.getCategory()) && !"AC".equalsIgnoreCase(jocEvent.getCategory()))
		    {

		    }
		    else
		    {
			form.setUpdateAsmntTypeFlag("Y");
		    }

		}
		/*else
		if (newJocEvent.getCategory() != null && !newJocEvent.getCategory().equalsIgnoreCase("AC") && !newJocEvent.getSeverity().equals(0) && !form.getOffenseCode().equalsIgnoreCase(offense.getOldOffenseCode())) //Task 87081
		{
		    form.setUpdateAsmntTypeFlag("Y"); //enable the assignment type section			
		}
		else
		    form.setUpdateAsmntTypeFlag("N");*/

	    }
	}
	/*while(iter.hasNext())
	{
	    JuvenileReferralOffenseBean offense = (JuvenileReferralOffenseBean)iter.next();
	    if(offense!=null)
	    {		
		offense.setOffenseDate(DateUtil.stringToDate(form.getOffenseDate(), DateUtil.DATE_FMT_1));
		offense.setKeyMapLocation(form.getKeyMapLocation());
		offense.setInvestigationNum(form.getInvestigationNum());
		JuvenileOffenseCodeResponseEvent jocEvent = JuvenileReferralHelper.validateOffenseCd(offense.getOffenseCode());
		offense.setOffenseCode(form.getOffenseCode());
		 JuvenileOffenseCodeResponseEvent newJocEvent = JuvenileReferralHelper.validateOffenseCd(form.getOffenseCode());
		offense.setOffenseDescription(newJocEvent.getShortDescription());
		//check for severitySubType R - Administrative offense
		if(jocEvent.getSeveritySubtype()!= null && jocEvent.getSeveritySubtype().equalsIgnoreCase("R"))
		{
		    //check if new offense is administrative
		   
		    if(newJocEvent.getSeveritySubtype()!= null && !newJocEvent.getSeveritySubtype().equalsIgnoreCase("R"))
		    {
			//enable the assignment type section
			form.setUpdateAsmntTypeFlag("Y");
			
			break;
		    }
		}
	    }
	}*/
	form.setOffenseCode("");
	form.setOffenseDate("");
	form.setKeyMapLocation("");
	form.setInvestigationNum("");
	form.setUpdateOffenseFlag("N");
	form.setUpdateRefStatFlag("");
	return (aMapping.findForward("updateReferral"));
    }

    /**
     * load Offense
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward loadOffense(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward(UIConstants.UPDATE);
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	String selectedVal = form.getSelectedOffense();
	//Iterator<JuvenileReferralOffenseBean> iter = form.getOffenseList().iterator();
	int i = Integer.parseInt(selectedVal);
	if (!form.getOffenseList().isEmpty() && form.getOffenseList().size() >= i)
	{
	    JuvenileReferralOffenseBean offense = form.getOffenseList().get(i);
	    if (offense != null)
	    {
		form.setOffenseCode(offense.getOffenseCode());
		form.setOffenseCodeDesc(offense.getOffenseDescription());
		form.setKeyMapLocation(offense.getKeyMapLocation());
		form.setInvestigationNum(offense.getInvestigationNum());
		form.setOffenseDate(offense.getOffenseDateFormatted());
		form.setOffenseId(offense.getOffenseID());

	    }

	}
	form.setUpdateOffenseFlag("Y");
	form.setUpdateRefStatFlag("");
	return (aMapping.findForward("updateReferral"));
    }

    /**
     * return To Search Juv
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward returnToSearchJuv(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.clearRefDetails();
	return aMapping.findForward("returnToSearch");
    }

    /**
     * update Referral
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward updateReferral(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setMessage("");
	form.setUpdateMessage("");
	form.setSubsequentMessage("");

	//Check offense Age Restrict again
	JuvenileOffenseCodeResponseEvent jocEvent = null;
	JJSJuvenile jjsJuv = JJSJuvenile.find(form.getJuvenileNum());
	Date bDate = jjsJuv.getBirthDate();
	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	int juvAge = getAgeInYears(bDate);

	String assignmentTypeSBQ = null;
	if (form.getAssignmentType() != null && form.getAssignmentType().equalsIgnoreCase("SBQ"))
	{
	    assignmentTypeSBQ = form.getAssignmentType();
	}

	List<JuvenileReferralOffenseBean> refOffenseBean = form.getOffenseList();
	for (int x = 0; x < refOffenseBean.size(); x++)
	{

	    JuvenileReferralOffenseBean bean = refOffenseBean.get(x);
	    jocEvent = JuvenileReferralHelper.validateOffenseCd(bean.getOffenseCode());
	    int ageAtOffense = getAgeAtOffense(bDate, bean.getOffenseDate());
	    int getAgeInYears = getAgeInYears(bDate);
	    form.setAgeAtOffense(getAgeAtOffense(bDate, bean.getOffenseDate()));
	    if (jocEvent != null && "Y".equalsIgnoreCase(jocEvent.getAgeRestrict()) && (getAgeInYears > 21 || getAgeInYears < 10))
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile's age does not qualify for  offense addition.  Contact Data Corrections for assistance"));
		saveErrors(aRequest, errors);
		if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
		{
		    return (aMapping.findForward("updateReferral"));
		}
		else
		{
		    return aMapping.findForward(UIConstants.FAILURE);
		}
	    }
	    if ((getAgeInYears >= 17 && getAgeInYears <= 21) && (ageAtOffense >= 17) && jocEvent != null && "Y".equalsIgnoreCase(jocEvent.getAgeRestrict()))
	    {
		form.setOffenseCode("");
		form.setOffenseDate("");
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile's age exceeds assignment permissions. Referral Update is not permitted. Contact Data Corrections for assistance"));
		saveErrors(aRequest, errors);
		return aMapping.findForward("updateReferral");
	    }

	    if (jocEvent != null && "N".equalsIgnoreCase(jocEvent.getAgeRestrict()) && "23".equals(jocEvent.getNumericCode()) && (getAgeInYears > 21 || getAgeInYears < 10))
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile does not qualify for offense addition.  Contact Data Corrections for assistance"));
		saveErrors(aRequest, errors);
		if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
		{
		    return (aMapping.findForward("updateReferral"));
		}
		else
		{
		    return aMapping.findForward(UIConstants.FAILURE);
		}
	    }
	    //End Age Restrict Validations

	}

	JuvenileProfileReferralListResponseEvent tempResp = new JuvenileProfileReferralListResponseEvent();
	if (form.getUpdateRefStat() != null && form.getUpdateRefStat().equalsIgnoreCase("ACTIVE"))
	{
	    form.setUpdateRefCloseDt("");
	}
	if (form.getUpdateAsmntTypeFlag().equalsIgnoreCase("Y") && form.getLoadAssmntFlag().equalsIgnoreCase("N"))
	{

	    return (aMapping.findForward("updateReferral"));
	}
	OfficerProfileResponseEvent officerResp = new OfficerProfileResponseEvent();
	if (form.getUpdateAsmntTypeFlag().equalsIgnoreCase("Y") && form.getJpo() != null && !(form.getJpo().equalsIgnoreCase("UVANC") || form.getJpo().equalsIgnoreCase("ANC") || form.getJpo().equalsIgnoreCase("UVREC") || form.getJpo().equalsIgnoreCase("REC")))
	{
	    officerResp = validateJPOEntered(form);
	    if (officerResp == null)
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile Probation Officer is invalid. Please modify"));
		saveErrors(aRequest, errors);
		return (aMapping.findForward("updateReferral"));
	    }
	}
	if (form.getUpdateAsmntTypeFlag().equalsIgnoreCase("Y") && !form.getAssignmentType().equalsIgnoreCase("REC") && (form.getJpo().equalsIgnoreCase("UVREC") || form.getJpo().equalsIgnoreCase("REC")))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "JPO code UVREC does not qualify for this assignment type.  Please modify"));
	    saveErrors(aRequest, errors);
	    return (aMapping.findForward("updateReferral"));
	}
	if (form.getUpdateRefStat() != null && form.getUpdateRefStat().equalsIgnoreCase("CLOSED"))
	{
	    //go through the referral list and check if the referral status has changed
	    Iterator referrals = form.getReferralList().iterator();
	    while (referrals.hasNext())
	    {
		JuvenileProfileReferralListResponseEvent refResp = (JuvenileProfileReferralListResponseEvent) referrals.next();
		if (refResp.getReferralNumber().equals(form.getNewRefNum()))
		{
		    tempResp = refResp;
		    if (!refResp.getReferralStatus().equalsIgnoreCase(form.getUpdateRefStat())) //referral status has changed
		    {
			form.setUpdateRefStatFlag("Y");
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			//check if Juvenile in Facility		       
			JJSHeader head = JuvenileFacilityHelper.getJJSHeader(form.getJuvenileNum());
			if (head != null)
			{
			    GetJuvenileFacilityDetailsEvent event = (GetJuvenileFacilityDetailsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYDETAILS);
			    event.setJuvenileNum(form.getJuvenileNum());
			    event.setReferralNum(form.getNewRefNum());
			    event.setFacilitySequenceNum(head.getLastSequenceNumber());

			    dispatch.postEvent(event);
			    IEvent replyEvent = dispatch.getReply();
			    CompositeResponse responses = (CompositeResponse) replyEvent;

			    Collection<JuvenileDetentionFacilitiesResponseEvent> juvFacilityDetails = MessageUtil.compositeToCollection(responses, JuvenileDetentionFacilitiesResponseEvent.class);
			    Iterator facIter = juvFacilityDetails.iterator();
			    JuvenileDetentionFacilitiesResponseEvent detFac = new JuvenileDetentionFacilitiesResponseEvent();
			    if (facIter.hasNext())
			    {
				detFac = (JuvenileDetentionFacilitiesResponseEvent) facIter.next();
			    }
			    if (head.getReferralNumber() != null && head.getReferralNumber().equalsIgnoreCase(form.getNewRefNum()))
			    {
				if (head.getFacilityStatus() != null)
				{
				    ActionErrors errors = new ActionErrors();
				    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile is currently booked in a facility. Referral cannot be closed"));
				    saveErrors(aRequest, errors);
				    return (aMapping.findForward("updateReferral"));
				}
				else
				    if (detFac.getReleaseReason() != null && (detFac.getReleaseReason().equalsIgnoreCase("E") || detFac.getReleaseReason().equalsIgnoreCase("T")))
				    {
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile is actively admitted in a facility. Referral cannot be closed"));
					saveErrors(aRequest, errors);
					return (aMapping.findForward("updateReferral"));
				    }
				    else
					if (detFac.getReleaseTo() != null && detFac.getReleaseTo().equalsIgnoreCase("NTS"))
					{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile is currently in transfer to a facility. Referral cannot be closed"));
					    saveErrors(aRequest, errors);
					    return (aMapping.findForward("updateReferral"));
					}
			    }
			} //end check facility

			//check Active casefiles
			List<JuvenileProfileReferralListResponseEvent> openReferralsOnlyList = new ArrayList<JuvenileProfileReferralListResponseEvent>();
			GetActiveCasefileReferralsEvent disEvent = (GetActiveCasefileReferralsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETACTIVECASEFILEREFERRALS);
			disEvent.setJuvenileNum(form.getJuvenileNum());

			openReferralsOnlyList = (List<JuvenileProfileReferralListResponseEvent>) MessageUtil.postRequestListFilter(disEvent, JuvenileProfileReferralListResponseEvent.class);

			boolean cntrlUsedElse = false;
			Iterator refListIter = openReferralsOnlyList.iterator();
			Map<String, Integer> activeReferralMap = new HashMap();
			while (refListIter.hasNext())
			{

			    JuvenileProfileReferralListResponseEvent referralResp = (JuvenileProfileReferralListResponseEvent) refListIter.next();
			    if (referralResp.getControllingReferralId() != null && StringUtils.isNotBlank(referralResp.getControllingReferralId().trim()))
			    {
				int cntrlRefNum = Integer.parseInt(referralResp.getControllingReferralId());
				activeReferralMap.put(referralResp.getReferralNumber(), cntrlRefNum);
			    }

			}
			JuvenileProfileReferralListResponseEvent onlyFirstRec = null;
			if (openReferralsOnlyList.size() > 0)
			{

			    Collections.sort((List<JuvenileProfileReferralListResponseEvent>) openReferralsOnlyList, new Comparator<JuvenileProfileReferralListResponseEvent>() {
				@Override
				public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
				{
				    if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
				    {
					return evt1.getReferralNumber().compareTo(evt2.getReferralNumber());
				    }
				    else
				    {
					return -1;
				    }
				}
			    });
			    onlyFirstRec = new JuvenileProfileReferralListResponseEvent();
			    onlyFirstRec = openReferralsOnlyList.get(0);
			}

			// Only look for actice casefiles for the referral
			// If found do not allow closure if its the last
			boolean altCasefileFound = false;
			GetCasefilesForReferralsEvent referralWithCasefiles = (GetCasefilesForReferralsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCASEFILESFORREFERRALS);
			referralWithCasefiles.setJuvenileNum(form.getJuvenileNum());
			referralWithCasefiles.setReferralNum(form.getNewRefNum());

			List<JuvenileProfileReferralListResponseEvent> casefileRefList = MessageUtil.postRequestListFilter(referralWithCasefiles, JuvenileProfileReferralListResponseEvent.class);

			for (int x = 0; x < casefileRefList.size(); x++)
			{

			    JuvenileProfileReferralListResponseEvent resp = casefileRefList.get(x);
			    if (resp.getCasefileId() != null && !resp.getCasefileId().equals(refResp.getCasefileId()))
			    {

				altCasefileFound = true;
			    }
			}

			// Check to see if referral to close has is the controlling referral
			// If no controlling referral check to see if there is only 1 active casefile
			// Check if referral to close if the lowest active referral
			// No longer needed Cassandra Landry
			/*			if ( "A".equalsIgnoreCase(refResp.getCaseStatus()) && ("PP".equalsIgnoreCase(refResp.getSupervisionCategoryId()) || "AD".equalsIgnoreCase(refResp.getSupervisionCategoryId())))
						{
						    ActionErrors errors = new ActionErrors();
						    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile referral cannot be closed. Referral is associated to active supervision(s)"));
						    saveErrors(aRequest, errors);
						    return (aMapping.findForward("updateReferral"));
						}
						else*/
			if ("A".equalsIgnoreCase(refResp.getCaseStatus()) || "CP".equalsIgnoreCase(refResp.getCaseStatus()))
			{
			    boolean isLastCasefile = false;
			    Iterator<Map.Entry<String, Integer>> itr = activeReferralMap.entrySet().iterator();
			    while (itr.hasNext())
			    {
				Map.Entry<String, Integer> entry = itr.next();
				if (entry.getValue() == Integer.parseInt(form.getNewRefNum()))
				{

				    if (entry.getKey().equalsIgnoreCase(form.getNewRefNum()))
				    {

					cntrlUsedElse = true;
				    }
				}
			    }
			    /*			        JuvenileCasefileResponseEvent casefile = null;
			      //check if the referral is the controlling ref
			    				GetJuvenileCasefileByCasefileIdEvent getCasefileEvent = (GetJuvenileCasefileByCasefileIdEvent)
			    					EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEBYCASEFILEID ) ;
			    				getCasefileEvent.setSupervisionNumber( refResp.getCasefileId() ) ;	
			    			
			    				List<JuvenileCasefileResponseEvent> casefileList = 
			    					MessageUtil.postRequestListFilter( getCasefileEvent, JuvenileCasefileResponseEvent.class );
			    				if( casefileList.size() >0 ){
			    				    
			    				    casefile = casefileList.get(0);
			    				    
			    				}*/

			    GetAllReferralsByCasefileIdEvent casefileEvt = (GetAllReferralsByCasefileIdEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETALLREFERRALSBYCASEFILEID);
			    casefileEvt.setJuvenileNum(form.getJuvenileNum());
			    casefileEvt.setCaseFileId(refResp.getCasefileId());

			    List<JuvenileCasefileReferralResponseEvent> casefiles = MessageUtil.postRequestListFilter(casefileEvt, JuvenileCasefileReferralResponseEvent.class);

			    if (casefiles.size() == 1)
			    {

				isLastCasefile = true;
			    }
			    else
				if (casefiles.size() > 1)
				{

				    Collections.sort((List<JuvenileCasefileReferralResponseEvent>) casefiles, new Comparator<JuvenileCasefileReferralResponseEvent>() {
					@Override
					public int compare(JuvenileCasefileReferralResponseEvent evt1, JuvenileCasefileReferralResponseEvent evt2)
					{
					    if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
					    {
						return evt1.getReferralNumber().compareTo(evt2.getReferralNumber());
					    }
					    else
					    {
						return -1;
					    }
					}
				    });

				    JuvenileCasefileReferralResponseEvent lowest = casefiles.get(0);
				    if (lowest.getReferralNumber().equals(form.getNewRefNum()))
				    {

					isLastCasefile = true;
				    }
				}

			    if (onlyFirstRec != null && (onlyFirstRec.getReferralNumber().equalsIgnoreCase(form.getNewRefNum())) || cntrlUsedElse)
			    {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile referral cannot be closed. Referral is the controlling referral associated to an active supervision(s)"));
				saveErrors(aRequest, errors);
				return (aMapping.findForward("updateReferral"));
			    }
			    else
				if (isLastCasefile)
				{
				    ActionErrors errors = new ActionErrors();
				    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Referral is the controlling referral assigned to an active supervision. Referral Status cannot equal CLOSED"));
				    saveErrors(aRequest, errors);
				    return (aMapping.findForward("updateReferral"));
				}
			    //check if the referral is not the controlling ref but it's the only referral for that casefile
			    /*else
			        if (casefile != null && casefile.getCaseStatusId().equalsIgnoreCase("A") && casefile.getControllingReferral() != null && !casefile.getControllingReferral().equalsIgnoreCase(form.getNewRefNum()))
			        {
			    	//go through the referrals list and check if this casefile is associated to any other referral
			    	Iterator referralsForCasefile = casefiles.iterator();
			    	boolean found = false;
			    	while (referralsForCasefile.hasNext())
			    	{
			    	    JuvenileCasefileReferralResponseEvent refForCasefileResp = (JuvenileCasefileReferralResponseEvent) referralsForCasefile.next();
			    	    if (!refForCasefileResp.getReferralNumber().equalsIgnoreCase(form.getNewRefNum()) && refForCasefileResp.getCaseFileId().equalsIgnoreCase(refResp.getCasefileId()))
			    	    {
			    		found = true;
			    		break;
			    	    }
			    	}
			    	if (!found)
			    	{
			    	    ActionErrors errors = new ActionErrors();
			    	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile referral cannot be closed. Referral is associated to active supervision(s)"));
			    	    saveErrors(aRequest, errors);
			    	    return (aMapping.findForward("updateReferral"));
			    	}
			        }*/
			}
			else
			{

			    if (altCasefileFound)
			    {

				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Referral is the controlling referral assigned to an active supervision. Referral Status cannot equal CLOSED"));
				saveErrors(aRequest, errors);
				return (aMapping.findForward("updateReferral"));
			    }
			}
			//US 87632
			if (refResp.getCourtId() != null && !refResp.getCourtId().equals(""))
			{
			    if (refResp.getCourtDisposition() == null)
			    {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile has scheduled court appearance. Verify the Referral\'s Court Date and Court Decision"));
				saveErrors(aRequest, errors);
				return (aMapping.findForward("updateReferral"));
			    }
			    if (refResp.getCourtDisposition() != null)
			    {
				JuvenileDispositionCode juvenileDispositionCode = JuvenileDispositionCode.find("codeAlpha", refResp.getCourtDisposition());
				/* if (juvenileDispositionCode == null || !juvenileDispositionCode.getOptionCode().equalsIgnoreCase("F"))
				 {*/
				if (juvenileDispositionCode == null || juvenileDispositionCode.getCodeNum().equals("") || juvenileDispositionCode.getCodeNum() == null || !(Integer.valueOf(juvenileDispositionCode.getCodeNum()) >= 1))
				{
				    ActionErrors errors = new ActionErrors();
				    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile has scheduled court appearance. Verify the Referral\'s Court Date and Court Decision"));
				    saveErrors(aRequest, errors);
				    return (aMapping.findForward("updateReferral"));
				}
			    }
			}

		    }
		    // Check referral close info
		    if (form.getUpdateRefCloseDt() != null && StringUtils.isNotBlank(form.getUpdateRefCloseDt()))
		    {

			Collection decisions = form.getIntakeDecisions();
			Iterator descIter = decisions.iterator();
			String intakeDesc = refResp.getIntakeDecisionId();
			if (!intakeDesc.equalsIgnoreCase(form.getNewRefIntakeDecision()))
			{

			    intakeDesc = form.getNewRefIntakeDecision();
			}

			while (descIter.hasNext())
			{

			    JuvenileReferralDispositionCode codes = (JuvenileReferralDispositionCode) descIter.next();
			    if (intakeDesc.equalsIgnoreCase(codes.getCode()))
			    {

				if ("000".equalsIgnoreCase(codes.getTjpcCode()) && (refResp.getCourtDate() == null && StringUtils.isEmpty(refResp.getCourtDisposition())))
				{

				    form.setUpdateRefCloseDt(null);
				    ActionErrors errors = new ActionErrors();
				    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile needs a final intake decision.  Please correct and resubmit."));
				    saveErrors(aRequest, errors);
				    return (aMapping.findForward("updateReferral"));
				}
			    }
			}

		    }

		    // check for 060 and populate pact
		    if ("R2C".equalsIgnoreCase(refResp.getIntakeDecisionId()))
		    {
			boolean recordFound = false;

			SaveJuvenileRiskNeedLevelCustomEvent r2cEvent = (SaveJuvenileRiskNeedLevelCustomEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SAVEJUVENILERISKNEEDLEVELCUSTOM);
			// look for existing r/n scores
			GetJuvenileCasefileRiskNeedsLevelEvent reqEvent = (GetJuvenileCasefileRiskNeedsLevelEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILERISKNEEDSLEVEL);
			reqEvent.setJuvenileNum(form.getJuvenileNum());
			reqEvent.setCaseFileId(null);

			List<PactRiskLevelResponseEvent> pactRNList = MessageUtil.postRequestListFilter(reqEvent, PactRiskLevelResponseEvent.class);
			if (pactRNList.size() == 0 && pactRNList.isEmpty())
			{

			    r2cEvent.setRiskLvl("A");
			    r2cEvent.setNeedsLvl("A");
			    r2cEvent.setLastPactDate(DateUtil.stringToDate(refResp.getIntakeDecDate(), DateUtil.DATE_FMT_1));
			    r2cEvent.setPactId(null);
			    if (refResp.getCasefileId() != null)
			    {

				r2cEvent.setCaseFileId(refResp.getCasefileId());
			    }
			    r2cEvent.setJuvenileNum(form.getJuvenileNum());
			    r2cEvent.setReferralNumber(refResp.getReferralNumber());
			    r2cEvent.setStatus("SYS GENERATE");

			}
			else
			{

			    for (int x = 0; x < pactRNList.size(); x++)
			    {

				PactRiskLevelResponseEvent pactResp = pactRNList.get(x);
				if (refResp.getReferralNumber().equals(pactResp.getReferralNumber()))
				{
				    recordFound = true;
				    break;
				}

			    }
			    if (!recordFound)
			    {
				r2cEvent.setRiskLvl("A");
				r2cEvent.setNeedsLvl("A");
				r2cEvent.setLastPactDate(DateUtil.stringToDate(refResp.getIntakeDecDate(), DateUtil.DATE_FMT_1));
				r2cEvent.setPactId(null);
				if (refResp.getCasefileId() != null)
				{

				    r2cEvent.setCaseFileId(refResp.getCasefileId());
				}
				r2cEvent.setJuvenileNum(form.getJuvenileNum());
				r2cEvent.setReferralNumber(refResp.getReferralNumber());
				r2cEvent.setStatus("SYS GENERATE");
			    }

			}
			if (r2cEvent.getStatus() != null)
			{
			    MessageUtil.postRequest(r2cEvent, GetJuvenileCasefileRiskNeedsLevelEvent.class);
			}

		    }

		    break;

		}
	    }
	}
	else
	    if (form.getUpdateRefStat() != null && form.getUpdateRefStat().equalsIgnoreCase("ACTIVE"))
	    {
		//go through the referral list and check if the referral status has changed
		Iterator referrals = form.getReferralList().iterator();
		while (referrals.hasNext())
		{
		    JuvenileProfileReferralListResponseEvent refResp = (JuvenileProfileReferralListResponseEvent) referrals.next();
		    if (refResp.getReferralNumber().equals(form.getNewRefNum()))
		    {
			tempResp = refResp;
			if (!refResp.getReferralStatus().equalsIgnoreCase(form.getUpdateRefStat())) //referral status has changed
			{
			    form.setUpdateRefCloseDt("");
			    form.setUpdateRefStatFlag("Y");
			}
		    }
		}

		//YTD - Referral Type and TJJDReferral Date
		//if Assignment Type Transferred and ISCOIN offense - Ref Type IC
		//Transferred and no iscoin - Ref Type TR
		//No transferred - Ref Type PA
		if (form.getAssignmentType() != null && form.getAssignmentType().equalsIgnoreCase("TRN"))
		{
		    boolean iscoinFound = false;
		    Date offenseDate = null;
		    Iterator iter = form.getOffenseList().iterator();
		    while (iter.hasNext())
		    {
			JuvenileReferralOffenseBean offBean = (JuvenileReferralOffenseBean) iter.next();
			if (offBean.getOffenseCode() != null && offBean.getOffenseCode().equalsIgnoreCase("ISCOIN"))
			{
			    iscoinFound = true;
			    offenseDate = offBean.getOffenseDate();
			    break;
			}

			JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode", offBean.getOffenseCode());
			if (offCode.getSeveritySubtype() != null && !offCode.getSeveritySubtype().equalsIgnoreCase("T"))
			{
			    ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "This assignment type requires an out of state or out of county level offense: TRNSIN, TRNDSP, ISCOIN or REGION.  Please check assignment type"));
			    saveErrors(aRequest, errors);
			    return aMapping.findForward("updateReferral");
			}
		    }
		    if (iscoinFound)
		    {
			form.setFormalReferralType("IC");
			form.setTJJDReferralDate(offenseDate);
		    }
		    else
		    {
			form.setFormalReferralType("TR");
			form.setTJJDReferralDate(DateUtil.getCurrentDate());
		    }
		}
	    }

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	UpdateJuvenileReferralEvent updateEvent = (UpdateJuvenileReferralEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.UPDATEJUVENILEREFERRAL);
	updateEvent = setUpdateDetails(updateEvent, form);
	dispatch.postEvent(updateEvent);
	CompositeResponse response = (CompositeResponse) dispatch.getReply();
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);

	if (error != null)
	{
	    ActionErrors errors = new ActionErrors();
	    if (error.getErrorLogId() != null && error.getErrorLogId().equalsIgnoreCase("notificationError"))
	    {
		//do nothing
	    }
	    else
	    {
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    // return aMapping.findForward(UIConstants.FAILURE);// as per Cassandra any failure in sending notifications or MAYSI should still continue with assignment creation / update
	}
	//else if ((form.getAssignmentType() != null && form.getAssignmentType().equalsIgnoreCase("SBQ")) && (juvAge >= 10 && juvAge <= 16))
	//{
	//    form.setUpdateMessage("Referral " + form.getNewRefNum() + " was successfully updated.");
	//    form.setSubsequentMessage("The record has been assigned for record creation and SUBSEQUENT assignment to: " + form.getSubsequentCasefileId());		    
	//}
	else
	{
	    form.setUpdateMessage("Referral " + form.getNewRefNum() + " was successfully updated.");
	}

	if (form.getUpdateRefCloseDt() != null && StringUtils.isNotBlank(form.getUpdateRefCloseDt()))
	{

	    //Check here for masterStatus = 'C'
	    GetJuvenileMasterStatusEvent statusEvent = (GetJuvenileMasterStatusEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEMASTERSTATUS);

	    statusEvent.setJuvenileNum(form.getJuvenileNum());

	    JuvenileMasterStatusResponseEvent status = (JuvenileMasterStatusResponseEvent) MessageUtil.postRequest(statusEvent, JuvenileMasterStatusResponseEvent.class);
	    if (status != null && "C".equalsIgnoreCase(status.getStatusId()))
	    {

		// Update JJSMSPETITION termination Date
		// dispositioncode 1304 dps change
		    Iterator iter = form.getReferralList().iterator();
		    String referralNum = "";
		    String refNo = "";
		    String referraldisp = "";
		    HashMap reffDisp = new HashMap();
		    JuvenileDispositionCode juvDispCode =null;

    		while (iter.hasNext())
    		{
    		    // dispositioncode 1304 dps change				
    		    JuvenileProfileReferralListResponseEvent resp = (JuvenileProfileReferralListResponseEvent) iter.next();
    		    if (resp.getReferralNumber() != null)
    		    {
    			//if (resp.getReferralNumber().contains("-"))
    			referralNum = resp.getReferralNumber();
    			referraldisp = resp.getFinalDisposition();
    			//add key value pair reff, disp	                			
    			if (referraldisp != null && !referraldisp.isEmpty())
    			{
    			    juvDispCode = JuvenileDispositionCode.find("codeAlpha", referraldisp);
    			    if (juvDispCode != null && juvDispCode.getDpsCode() != null && !juvDispCode.getDpsCode().isEmpty()&& !juvDispCode.getDpsCode().equalsIgnoreCase("000"))
    				reffDisp.put(referralNum, juvDispCode.getDpsCode());
    			}
    		    }
    		}
		UpdateJJSPetitionsTerminationDateEvent updt = (UpdateJJSPetitionsTerminationDateEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.UPDATEJJSPETITIONSTERMINATIONDATE);
		updt.setJuvenileNum(form.getJuvenileNum());
		updt.setReferralNum(form.getNewRefNum());
		updt.setTerminationDate(DateUtil.stringToDate(form.getUpdateRefCloseDt(), DateUtil.DATE_FMT_1));
		updt.setReffDps(reffDisp);
		MessageUtil.postRequest(updt);
	    }
	}

	return (aMapping.findForward("submitUpdateReferral"));

    }

    private UpdateJuvenileReferralEvent setUpdateDetails(UpdateJuvenileReferralEvent updateEvt, JuvenileReferralForm form)
    {
	updateEvt.setReferralNum(form.getNewRefNum()); 
	updateEvt.setReferralDate(DateUtil.stringToDate(form.getNewRefDate(), DateUtil.DATE_FMT_1));
	updateEvt.setReferralSource(form.getNewRefSource());
	updateEvt.setIntakeDate(DateUtil.stringToDate(form.getNewRefIntakeDate(), DateUtil.DATE_FMT_1));
	updateEvt.setIntakeDecisionId(form.getNewRefIntakeDecision());
	UIJuvenileHelper.PopulateReferralTJPCdisp(updateEvt,form.getNewRefIntakeDecision());
	updateEvt.setOffenses(form.getOffenseList());
	updateEvt.setOffenseTotal(form.getOffenseList().size() + "");
	updateEvt.setJuvenileNum(form.getJuvenileNum());
	updateEvt.setCasefileGenerate(form.getCasefileGenerate());
	updateEvt.setInvestigationNum(form.getInvestigationNum());
	updateEvt.setKeyMapLocation(form.getKeyMapLocation());
	updateEvt.setTJJDReferralDate(form.getTJJDReferralDate());
	updateEvt.setReferralTypeInd(form.getFormalReferralType());
	updateEvt.setAssignmentType(form.getAssignmentType());
	updateEvt.setSupervisionCat(form.getSupervisionCat());
	updateEvt.setSupervisionType(form.getSupervisionType());
	updateEvt.setAssignmentDate(DateUtil.stringToDate(form.getAssignmentDateStr(), DateUtil.DATE_FMT_1));
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
			updateEvt.setJpoID(form.getJpo());
		    }
		}
	    }
	    /**
	     * If the Assignment Type falls into Pre-Adjudication category,
	     * insert assigned JPO code into the Court Officer (CTASSIGNJPOID)
	     * column. This applies to the following Assignment Type(s):
	     * PRE-ADJUDICATION
	     */
	    else
		if (form.getAssignmentType().equalsIgnoreCase("PRE"))
		{
		    if (form.getSupervisionCat() != null)
		    {
			if (form.getSupervisionCat().equalsIgnoreCase("AD"))
			{
			    updateEvt.setCtAssignJPOId(form.getJpo());
			}
			else
			{
			    updateEvt.setJpoID(form.getJpo());
			}
		    }
		}

		/**
		 * If the Assignment Type falls into Post-Adjudication category,
		 * insert assigned JPO code into the Probation Officer
		 * (PROBATIONJPOID) column. This applies to the following
		 * Assignment Type(s): POST-ADJUDICATION
		 */

		else
		    if (form.getAssignmentType().equalsIgnoreCase("PAD"))
		    {
			if (form.getSupervisionCat() != null)
			{
			    if (form.getSupervisionCat().equalsIgnoreCase("AR") || form.getSupervisionCat().equalsIgnoreCase("AC"))
			    {
				updateEvt.setProbJPOId(form.getJpo());
			    }
			    else
			    {
				updateEvt.setJpoID(form.getJpo());
			    }
			}
		    }
		    /**
		     * If Assignment Type equals TRANSFERRED OFFENSE (OOC), then
		     * refer to Supervision Category to determine proper
		     * insertion. If Supervision Category = PRE-PETITION, then
		     * insert assigned JPO code into the Intake Officer
		     * (INASSIGNJPOID) column. If Supervision Category =
		     * PRE-ADJUDICATION, then insert assigned JPO code into the
		     * Court Officer (CTASSINGJPOID) column. If Supervision
		     * Category = POST-ADJUDICATION COMMUNITY or
		     * POST-ADJUDICATION RESIDENTIAL, then insert JPO code into
		     * Probation Officer (PROBATIONJPOID) column.
		     */

		    else
			if (form.getAssignmentType().equalsIgnoreCase("TRN"))
			{
			    if (form.getSupervisionCat() != null)
			    {
				if (form.getSupervisionCat().equalsIgnoreCase("PP"))
				{
				    updateEvt.setJpoID(form.getJpo());
				}
				else
				    if (form.getSupervisionCat().equalsIgnoreCase("AD"))
				    {
					updateEvt.setCtAssignJPOId(form.getJpo());
				    }
				    else
					if (form.getSupervisionCat().equalsIgnoreCase("AC") || form.getSupervisionCat().equalsIgnoreCase("AR"))
					{
					    updateEvt.setProbJPOId(form.getJpo());
					}
					else
					{
					    updateEvt.setJpoID(form.getJpo());
					}
			    }
			}

			else
			{
			    updateEvt.setJpoID(form.getJpo());
			}
	}
	//User-story 8814	
	updateEvt.setSubsequentCasefileId(form.getSubsequentCasefileId());
	updateEvt.setProbationStartDate(DateUtil.stringToDate(form.getProbationStartDate(), DateUtil.DATE_FMT_1));
	updateEvt.setProbationEndDate(DateUtil.stringToDate(form.getProbationEndDate(), DateUtil.DATE_FMT_1));
	updateEvt.setUpdateAsmntTypeFlag(form.getUpdateAsmntTypeFlag());
	updateEvt.setRefStatus(form.getUpdateRefStat());
	updateEvt.setCasefileId(form.getCurrentCasefileId());
	updateEvt.setClosedDate(DateUtil.stringToDate(form.getUpdateRefCloseDt(), DateUtil.DATE_FMT_1));
	return updateEvt;
    }

    private JOTTO getCoActorsandCorespondents(String daLogNum)
    {
	JOTTO jotto = new JOTTO();
	JuvenileOffenderTracking jot = JuvenileOffenderTracking.find(daLogNum);
	if (jot != null)
	    jotto.setJuvenileCoActors(jot.getCoDefendants());
	List adultCoActors = new ArrayList();
	Iterator<JuvenileOffenderTrackingCoActor> coActors = JuvenileOffenderTrackingCoActor.findAllByDaLogNum(daLogNum);
	while (coActors.hasNext())
	{
	    JuvenileOffenderTrackingCoActor actor = (JuvenileOffenderTrackingCoActor) coActors.next();
	    adultCoActors.add(actor);
	}

	jotto.setAdultCoActors(adultCoActors);
	return jotto;

    }

    public ActionForward selectOfficerCode(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward("createReferral");
    }

    public ActionForward changeSupervisionType(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward("createReferral");
    }

    public ActionForward changeAssignmentType(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward("updateReferral");
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward createResealReferral(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	form.setSubsequentMessage("");
	form.setCasefileGenerate("N");

	if (form.getOffenseList().isEmpty())
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "A Closed original charge referral was not found. Please correct"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward("resealError");
	}

	JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("REFERRAL_BRIEFINGDETAILS_FORM");
	String masterRectype = briefingDetailsForm.getProfileDetail().getRecType();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	SaveJJSReferralEvent saveEvent = (SaveJJSReferralEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.SAVEJJSREFERRAL);
	saveEvent = setSaveDetails(saveEvent, form);
	saveEvent.setActionFlag("createReferral");
	MessageUtil.postRequest(saveEvent);
	CompositeResponse response = (CompositeResponse) dispatch.getReply();
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
	if (error != null)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", error.getMessage()));
	    saveErrors(aRequest, errors);
	}
	else
	{

	    if ("I.JUVENILE".equalsIgnoreCase(masterRectype))
	    {

		SaveJuvenilePurgeEvent undoPurge = (SaveJuvenilePurgeEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEPURGE);
		undoPurge.setJuvenileNum(form.getJuvenileNum());
		undoPurge.setRecType("JUVENILE");
		MessageUtil.postRequest(undoPurge);
	    }
	    form.setUpdateMessage(new String("Referral " + form.getNewRefNum() + " was successfully created."));

	}
	form.setAction("confirmCreate");
	return aMapping.findForward("resealSuccess");
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward assignAdminReferralNum(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ActionForward forward = aMapping.findForward(UIConstants.UPDATE);
	JuvenileReferralForm form = (JuvenileReferralForm) aForm;
	String selectedVal = form.getSelectedValue();
	String partial = "";
	try
	{
	    partial = selectedVal.substring(0, 3);
	    partial = partial + '%';
	}
	catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    //e.printStackTrace();
	}

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetJJSPartialReferralEvent referralEvent = (GetJJSPartialReferralEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJJSPARTIALREFERRAL);
	referralEvent.setJuvenileNum(form.getJuvenileNum());
	referralEvent.setReferralNum(partial);

	dispatch.postEvent(referralEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);

	Collection<JuvenileProfileReferralListResponseEvent> respEvent = MessageUtil.compositeToCollection(compositeResponse, JuvenileProfileReferralListResponseEvent.class);

	Collections.sort((List<JuvenileProfileReferralListResponseEvent>) respEvent, Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
	    @Override
	    public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
	    {
		return Integer.valueOf(evt1.getReferralNumber()).compareTo(Integer.valueOf(evt2.getReferralNumber()));
	    }
	}));

	Iterator<JuvenileProfileReferralListResponseEvent> iter = respEvent.iterator();
	if (iter.hasNext())
	{

	    JuvenileProfileReferralListResponseEvent refResp = iter.next();
	    int newReferralNum = Integer.parseInt(refResp.getReferralNumber());
	    newReferralNum = newReferralNum + 1;

	    if (Integer.valueOf(newReferralNum) % 10 == 0)
	    {

		String tempRefNum = form.getMaxCrimReferral();
		if (newReferralNum <= Integer.parseInt(tempRefNum))
		{

		    StringBuffer sb = new StringBuffer(tempRefNum.substring(0, 3)).append(0);
		    newReferralNum = (Integer.valueOf(sb.toString())) + 10;
		}

	    }
	    form.setNewRefNum(String.valueOf(newReferralNum));
	}

	//JuvenileReferralOffenseBean offenseResp = form.getOffenseListBU().get(0);
	//form.getOffenseList().add(offenseResp);
	form.setAction("addToOffenseList");
	form.setOriginalChargeReferrals(null);
	form.setUpdateOffenseMsg("");
	form.setUpdateOffenseError("");
	return (aMapping.findForward("createReferral"));
    }

}
