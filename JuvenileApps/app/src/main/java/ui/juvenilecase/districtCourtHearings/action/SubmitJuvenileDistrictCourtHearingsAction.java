package ui.juvenilecase.districtCourtHearings.action;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.servlet.http.HttpSession;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetAttorneyNameAndBarNumEvent;
import messaging.codetable.GetJuvenileCourtDecisionCodesEvent;
import messaging.codetable.GetJuvenileCourtsEvent;
import messaging.codetable.GetJuvenileDispositionCodeEvent;
import messaging.codetable.criminal.reply.JuvenileCourtDecisionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCourtResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.detentionCourtHearings.DeleteJJSCLDetentionSettingEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByChainAndSeqNumEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByChainEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByJuvNumEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByRefereeCourtEvent;
import messaging.detentionCourtHearings.UpdateJJSCLDetentionSettingEvent;
import messaging.detentionCourtHearings.reply.JuvenileDetentionNotificationResponseEvent;
import messaging.districtCourtHearings.DeleteJJSCLAncillarySettingEvent;
import messaging.districtCourtHearings.DeleteJJSCLCourtSettingEvent;
import messaging.districtCourtHearings.GetJJSAllCourtByRefereeCourtEvent;
import messaging.districtCourtHearings.GetJJSCLAncillaryCourtByChainAndSeqNumEvent;
import messaging.districtCourtHearings.GetJJSCLAncillaryCourtByReferreeCourtEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByChainAndSeqNumEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByPetitionNumEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByRefereeCourtEvent;
import messaging.districtCourtHearings.UpdateJJSCLAncillarySettingEvent;
import messaging.districtCourtHearings.UpdateJJSCLCourtSettingEvent;
import messaging.districtCourtHearings.UpdateJJSDetentionFromDistrictCourtEvent;
import messaging.districtCourtHearings.UpdateJJSHeaderFromDetentionCourtEvent;
import messaging.districtCourtHearings.UpdateJJSHeaderFromDistrictCourtEvent;
import messaging.districtCourtHearings.UpdateJuvenileMasterFromDistrictCourtEvent;
import messaging.districtCourtHearings.UpdateJuvenileReferralFromDistrictCourtEvent;
import messaging.districtCourtHearings.reply.JuvenileDistrictCourtNotificationResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.juvenilecase.GetJJSCourtbyOIDEvent;
import messaging.juvenilecase.GetJJSDetentionbyOIDEvent;
import messaging.juvenilecase.SaveJJSLastAttorneyEvent;
import messaging.juvenilecase.UpdateJJSLastAttorneyGALEvent;
import messaging.juvenilecase.reply.AttorneyNameAndAddressResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.productionsupport.GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent;
import messaging.productionsupport.GetProductionSupportCourtRecordsEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import mojo.naming.NotificationControllerSerivceNames;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProductionSupportControllerServiceNames;
import naming.UIConstants;
import net.minidev.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import pd.codetable.SimpleCodeTableHelper;
import pd.codetable.criminal.JuvenileDates;
import pd.codetable.criminal.PDCriminalCodeTableHelper;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilewarrant.JJSPetition;
import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.security.SecurityUIHelper;

/**
 * @author sthyagarajan
 */
public class SubmitJuvenileDistrictCourtHearingsAction extends JIMSBaseAction
{

    @Override
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.deleteSetting", "deleteSetting");
	keyMap.put("button.search", "searchAttorney");
	keyMap.put("button.validate", "validateBarNumber");
	keyMap.put("button.searchGal","searchGuardianAdlitem");
	keyMap.put("button.validateGAL","validateGALBarNumber");
	keyMap.put("button.validateaty2Barnum","validateAty2BarNumber");
	keyMap.put("button.go", "go");
	keyMap.put("button.submit", "submit");
	keyMap.put("button.courtMainMenu", "courtMainMenu");
	keyMap.put("button.attorneyConfirmation", "AttorneyConfirmation"); 
	keyMap.put("button.attorneyBypass", "AttorneyBypass");
	keyMap.put("button.searchAty2","searchAttorney2");
	keyMap.put("button.setdeletFlag","setdeleteFlag");
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
	return (aMapping.findForward(UIConstants.REFRESH));
    }
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward setdeleteFlag(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.setAction("deleteSetting");
	courtHearingForm.setDeleteFlag(null);
	return (aMapping.findForward(UIConstants.REFRESH));
    }


    /**
     * Delete Setting
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward deleteSetting(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.setAction("deleteSetting");
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	String pagerOffset = (String) aRequest.getParameter("pager.offset");
	courtHearingForm.setPagerOffset(pagerOffset);
	
	Map<String, DocketEventResponseEvent> dktSearchResultsMap = courtHearingForm.getDktSearchResultsMap();
	DocketEventResponseEvent currentSetting = dktSearchResultsMap.get(courtHearingForm.getCurrentSettingDocketEventIdKey());

	if (courtHearingForm.getDeleteFlag().equalsIgnoreCase("true"))
	{

	    if (courtHearingForm.getDeleteDocketEventId() != null && !courtHearingForm.getDeleteDocketEventId().isEmpty())
	    {
		if (courtHearingForm.getDeleteDocketEventType().equalsIgnoreCase("COURT"))
		{
		    //currentSeqNum = docResp.getSeqNum();
		    DeleteJJSCLCourtSettingEvent deleteCourtSettingEvt = (DeleteJJSCLCourtSettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.DELETEJJSCLCOURTSETTING);
		    deleteCourtSettingEvt.setDocketEventId(courtHearingForm.getDeleteDocketEventId());		    

		    CompositeResponse compositeResponse = MessageUtil.postRequest(deleteCourtSettingEvt);
		    DocketEventResponseEvent resp = (DocketEventResponseEvent) MessageUtil.filterComposite(compositeResponse, DocketEventResponseEvent.class);
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
		    if (resp.getDeleteFlag() != null && resp.getDeleteFlag().equalsIgnoreCase("true"))
		    {
			// after deletion process.
			if (dktSearchResultsMap.containsKey(courtHearingForm.getDeleteDocketEventIdKey()))
			{
			    dktSearchResultsMap.remove(courtHearingForm.getDeleteDocketEventIdKey()); //remove from the main list after deletion.
			    courtHearingForm.setAllDktSearchResults(new ArrayList<DocketEventResponseEvent>(dktSearchResultsMap.values()));
			    courtHearingForm.setSearchResultSize(dktSearchResultsMap.size());
			    courtHearingForm.setDktSearchResultsMap(dktSearchResultsMap);
			}

			/**
			 * check email 10/3/2018 from regina:Subj: Deletion of
			 * subsequent calendar record The current setting�s
			 * RESULT, DISPOSITION should be deleted/removed/erased
			 * and the on-screen display of the fields refreshed.
			 * The screen ACTION DATE and screen ACTION TIME will be
			 * null as the associated calendar record has been
			 * deleted.
			 */
			if (courtHearingForm.getIsLastSetting() != null && courtHearingForm.getIsLastSetting().equalsIgnoreCase("false"))
			{
			    UpdateJJSCLCourtSettingEvent updateCourtEvt = (UpdateJJSCLCourtSettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLCOURTSETTING);
			    updateCourtEvt.setDocketEventId(courtHearingForm.getCurrentSettingDocketEventId());
			    updateCourtEvt.setChainNumber(courtHearingForm.getCurrentSettingChainNumber());
			    updateCourtEvt.setHearingResult("");
			    updateCourtEvt.setHearingDisposition("");
			    updateCourtEvt.setNewRecordCreated(false);

			    CompositeResponse compResp = MessageUtil.postRequest(updateCourtEvt);
			    DocketEventResponseEvent docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(compositeResponse, DocketEventResponseEvent.class);
			    Object errResponse = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
			    if (errResponse != null)
			    {
				ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
				try
				{
				    handleFatalUnexpectedException(myEvt.getMessage());
				}
				catch (GeneralFeedbackMessageException e)
				{
				    e.printStackTrace();
				}
			    }
			    currentSetting.setCourtResult("");
			    currentSetting.setOldcourtResult("");
			    currentSetting.setDisposition("");
			    currentSetting.setActionDate("");
			    currentSetting.setActionTime("");
			}

			/**
			 * If the user has confirmed a delete request and
			 * application has successfully processed the deletion
			 * for a Court calendar record, the referral record must
			 * be updated. Retrieve the Court Date, Petition # and
			 * Referral # from the record.
			 */

			DocketEventResponseEvent docket = courtHearingForm.getDeletedDocket();
			if (docket != null)
			{
			    GetJJSCLCourtByPetitionNumEvent courtEvt = (GetJJSCLCourtByPetitionNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYPETITIONNUM);
			    courtEvt.setJuvenileNumber(docket.getJuvenileNumber());
			    courtEvt.setReferralNumber(docket.getReferralNum());
			    courtEvt.setPetitionNumber(docket.getPetitionNumber());

			    CompositeResponse response = MessageUtil.postRequest(courtEvt);

			    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(response, DocketEventResponseEvent.class);

			    Object errorResp = MessageUtil.filterComposite(response, ErrorResponseEvent.class);
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
			    }

			    /**
			     * Locate all other calendar records (where the
			     * Hearing Result is populated) for the juvenile
			     * with the same petition and referral number as the
			     * current calendar record. Sort the records in by
			     * ascending ChainNumber. Within ChainNumber
			     * groupings, sort by descending
			     * ChainSequenceNumber. The setting that will be
			     * used to update the referral per the ranking logic
			     * below is the one that is the highest chain number
			     * and the setting with highest SequenceNumber.
			     */
			    //chain number
			    Collections.sort((List<DocketEventResponseEvent>) docketResponses, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
				@Override
				public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
				{
				    if (evt1.getChainNumber() != null && evt2.getChainNumber() != null)
					return Integer.valueOf(evt1.getChainNumber()).compareTo(Integer.valueOf(evt2.getChainNumber()));
				    else
					return -1;
				}
			    }));

			    //Seq Number
			    Collections.sort((List<DocketEventResponseEvent>) docketResponses, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
				@Override
				public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
				{
				    if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
					return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
				    else
					return -1;
				}
			    }));

			    /**
			     * If the deleted setting is the only setting in the
			     * chain, null the referral�s CourtDate,
			     * CourtDisposition, CourtID, PIAstatus,
			     * CourtResult, and CourtResultType. NOTE: ***Legacy
			     * - RESET.SEQ.NUM = record�s ChainSequenceNumber;
			     * SETTING.COUNT=1 translates to �only setting in
			     * current chain.� ****
			     */

			    if (courtHearingForm.getIsOnlySetting() != null && courtHearingForm.getIsOnlySetting().equalsIgnoreCase("true"))
			    {
				//update Juvenile Referral information.
				UpdateJuvenileReferralFromDistrictCourtEvent juvenileReferralUpdateEvent = (UpdateJuvenileReferralFromDistrictCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJUVENILEREFERRALFROMDISTRICTCOURT);
				juvenileReferralUpdateEvent.setDeleteFlag("true");
				juvenileReferralUpdateEvent.setIsOnlySetting("true");
				juvenileReferralUpdateEvent.setJuvenileNumber(currentSetting.getJuvenileNumber());
				juvenileReferralUpdateEvent.setReferralNumber(currentSetting.getReferralNum());
				juvenileReferralUpdateEvent.setCourtDisposition(currentSetting.getDisposition());
				juvenileReferralUpdateEvent.setPetitionNum(currentSetting.getPetitionNumber());
				juvenileReferralUpdateEvent.setPetitionStatus(currentSetting.getPetitionStatus());
				CompositeResponse juvenileReferralCompResponse = postRequestEvent(juvenileReferralUpdateEvent);

			    }
			    else
			    {
				UpdateJuvenileReferralFromDistrictCourtEvent juvenileReferralUpdateEvent = (UpdateJuvenileReferralFromDistrictCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJUVENILEREFERRALFROMDISTRICTCOURT);
				juvenileReferralUpdateEvent.setDeleteFlag("true");
				juvenileReferralUpdateEvent.setCourtDisposition(currentSetting.getDisposition());
				juvenileReferralUpdateEvent.setCourtSettings(docketResponses);
				juvenileReferralUpdateEvent.setCourtDate(currentSetting.getCourtDate());
				juvenileReferralUpdateEvent.setJuvenileNumber(currentSetting.getJuvenileNumber());
				juvenileReferralUpdateEvent.setReferralNumber(currentSetting.getReferralNum());
				juvenileReferralUpdateEvent.setPetitionNum(currentSetting.getPetitionNumber());
				juvenileReferralUpdateEvent.setPetitionStatus(currentSetting.getPetitionStatus());
				juvenileReferralUpdateEvent.setIsLastSetting("false");
				CompositeResponse juvenileReferralCompResponse = postRequestEvent(juvenileReferralUpdateEvent);
				
				//more than one setting.
				/*Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
				while (docketResponsesItr.hasNext())
				{
				    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
				    //action date and time (derived value) gets persisted in the court date and court time field on update of court action
				    if (docResp != null)
				    {
					//update Juvenile Referral information.
					UpdateJuvenileReferralFromDistrictCourtEvent juvenileReferralUpdateEvent = (UpdateJuvenileReferralFromDistrictCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJUVENILEREFERRALFROMDISTRICTCOURT);
					juvenileReferralUpdateEvent.setDeleteFlag("true");
					juvenileReferralUpdateEvent.setCourtDate(currentSetting.getCourtDate());
					juvenileReferralUpdateEvent.setCourtDisposition(docResp.getDisposition());
					juvenileReferralUpdateEvent.setCourtResult(docResp.getCourtResult());
					juvenileReferralUpdateEvent.setJuvenileNumber(currentSetting.getJuvenileNumber());
					juvenileReferralUpdateEvent.setReferralNumber(currentSetting.getReferralNum());
					juvenileReferralUpdateEvent.setDecisionType(docResp.getHearingType());
					juvenileReferralUpdateEvent.setIsLastSetting("false");
					CompositeResponse juvenileReferralCompResponse = postRequestEvent(juvenileReferralUpdateEvent);
				    }
				}*/
			    }
			}

			//update Referral Record.
			

			//call reset explicitly.
			courtHearingForm.reset(aMapping, aRequest);
			courtHearingForm.setPrevAction("deleteSetting");
			// add new function to email data correction.			
			DocketEventResponseEvent deldocket = courtHearingForm.getDeletedDocket();			
			if(deldocket!=null)
			    sendemailonDelete(deldocket);
			courtHearingForm.setDeleteFlag("");
			courtHearingForm.setDeleteDocketEventId(null);
			ActionMessages messageHolder = new ActionMessages();
			messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.settingRecordDeleted"));
			aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
			saveMessages(aRequest, messageHolder);
			return refreshPage(aMapping, aForm, aRequest, aResponse);
		    }
		} //court check
		if (courtHearingForm.getDeleteDocketEventType().equalsIgnoreCase("ANCILLARY"))
		{
		    //currentSeqNum = docResp.getSeqNum();
		    DeleteJJSCLAncillarySettingEvent deleteAncillarySettingEvt = (DeleteJJSCLAncillarySettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.DELETEJJSCLANCILLARYSETTING);
		    deleteAncillarySettingEvt.setDocketEventId(courtHearingForm.getDeleteDocketEventId());

		    CompositeResponse compositeResponse = MessageUtil.postRequest(deleteAncillarySettingEvt);
		    DocketEventResponseEvent resp = (DocketEventResponseEvent) MessageUtil.filterComposite(compositeResponse, DocketEventResponseEvent.class);
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
		    if (resp.getDeleteFlag() != null && resp.getDeleteFlag().equalsIgnoreCase("true"))
		    {
			// after deletion process.
			if (dktSearchResultsMap.containsKey(courtHearingForm.getDeleteDocketEventIdKey()))
			{
			    dktSearchResultsMap.remove(courtHearingForm.getDeleteDocketEventIdKey()); //remove from the main list after deletion.
			    courtHearingForm.setDktSearchResultsMap(dktSearchResultsMap);
			    courtHearingForm.setAllDktSearchResults(new ArrayList<DocketEventResponseEvent>(dktSearchResultsMap.values()));
			    courtHearingForm.setSearchResultSize(dktSearchResultsMap.size());
			}
			/**
			 * check email 10/3/2018 from regina:Subj: Deletion of
			 * subsequent calendar record The current setting�s
			 * RESULT, DISPOSITION should be deleted/removed/erased
			 * and the on-screen display of the fields refreshed.
			 * The screen ACTION DATE and screen ACTION TIME will be
			 * null as the associated calendar record has been
			 * deleted.
			 */
			if (courtHearingForm.getIsLastSetting() != null && courtHearingForm.getIsLastSetting().equalsIgnoreCase("false"))
			{
			    UpdateJJSCLAncillarySettingEvent updateAncillaryEvt = (UpdateJJSCLAncillarySettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLANCILLARYSETTING);
			    updateAncillaryEvt.setChainNum(courtHearingForm.getCurrentSettingChainNumber());
			    updateAncillaryEvt.setDocketEventId(courtHearingForm.getCurrentSettingDocketEventId());
			    updateAncillaryEvt.setHearingResult("");
			    updateAncillaryEvt.setHearingDisposition("");

			    CompositeResponse compResp = MessageUtil.postRequest(updateAncillaryEvt);
			    DocketEventResponseEvent docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(compositeResponse, DocketEventResponseEvent.class);
			    Object errResponse = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
			    if (errResponse != null)
			    {
				ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
				try
				{
				    handleFatalUnexpectedException(myEvt.getMessage());
				}
				catch (GeneralFeedbackMessageException e)
				{
				    e.printStackTrace();
				}
			    }
			    currentSetting.setCourtResult("");
			    currentSetting.setDisposition("");
			    currentSetting.setActionDate("");
			    currentSetting.setActionTime("");
			}
			
			courtHearingForm.setDeleteFlag("");
			courtHearingForm.setDeleteDocketEventId(null);
			
			//call reset explicitly.
			courtHearingForm.reset(aMapping, aRequest);
			courtHearingForm.setPrevAction("deleteSetting");
			
			ActionMessages messageHolder = new ActionMessages();
			messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.settingRecordDeleted"));
			aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
			saveMessages(aRequest, messageHolder);
			return refreshPage(aMapping, aForm, aRequest, aResponse);
		    }
		} //ancillary check

		if (courtHearingForm.getDeleteDocketEventType().equalsIgnoreCase("DETENTION"))
		{
		    DeleteJJSCLDetentionSettingEvent deleteDetentionSettingEvt = (DeleteJJSCLDetentionSettingEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.DELETEJJSCLDETENTIONSETTING);
		    deleteDetentionSettingEvt.setDocketEventId(courtHearingForm.getDeleteDocketEventId());

		    CompositeResponse compositeResponse = MessageUtil.postRequest(deleteDetentionSettingEvt);
		    DocketEventResponseEvent resp = (DocketEventResponseEvent) MessageUtil.filterComposite(compositeResponse, DocketEventResponseEvent.class);
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
		    if (resp.getDeleteFlag() != null && resp.getDeleteFlag().equalsIgnoreCase("false"))
		    {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Error Occured While Deleting the Setting.Please Contact Support."));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		    }

		    if (resp.getDeleteFlag() != null && resp.getDeleteFlag().equalsIgnoreCase("true"))
		    {
			// after deletion process.
			if (dktSearchResultsMap.containsKey(courtHearingForm.getDeleteDocketEventIdKey()))
			{
			    dktSearchResultsMap.remove(courtHearingForm.getDeleteDocketEventIdKey()); //remove from the main list after deletion.
			    courtHearingForm.setDktSearchResultsMap(dktSearchResultsMap);
			    courtHearingForm.setAllDktSearchResults(new ArrayList<DocketEventResponseEvent>(dktSearchResultsMap.values()));
			    courtHearingForm.setSearchResultSize(dktSearchResultsMap.size());
			}

			courtHearingForm.setDeleteFlag("");
			courtHearingForm.setDeleteDocketEventId(null);
			
			//call reset explicitly.
			courtHearingForm.reset(aMapping, aRequest);
			courtHearingForm.setPrevAction("deleteSetting");
			
			ActionMessages messageHolder = new ActionMessages();
			messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.settingRecordDeleted"));
			aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
			saveMessages(aRequest, messageHolder);
			return refreshPage(aMapping, aForm, aRequest, aResponse);
		    }
		} //detention check
	    }
	}
	else
	{
	    //setDktSearchResultsMap
	    ArrayList<DocketEventResponseEvent> dockets = (ArrayList<DocketEventResponseEvent>) courtHearingForm.getUpdatedDocketList();
	    if (dockets != null)
	    {
		for (int i = 0; i < dockets.size(); i++)
		{
		    DocketEventResponseEvent docRespEvent = dockets.get(i);

		    if (docRespEvent != null)
		    {
			if (docRespEvent.getDeleteSetting() != null && !docRespEvent.getDeleteSetting().isEmpty())
			{
			    //reset the delete setting flag in the main list:
			    DocketEventResponseEvent respEvt = dktSearchResultsMap.get(docRespEvent.getDocketEventIdKey());
			    respEvt.setDeleteSetting(null);
			    dktSearchResultsMap.put(docRespEvent.getDocketEventIdKey(), respEvt);

			    if (docRespEvent.getDocketType().equalsIgnoreCase("COURT"))
			    {
				GetJJSCLCourtByChainAndSeqNumEvent courtEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
				courtEvt.setChainNumber(docRespEvent.getChainNumber());
				dispatch.postEvent(courtEvt);
				CompositeResponse courtResp = (CompositeResponse) dispatch.getReply();
				List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtResp, DocketEventResponseEvent.class);

				Collections.sort((List<DocketEventResponseEvent>) docketResponses, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
				    @Override
				    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
				    {
					if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
					    return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
					else
					    return -1;
				    }
				}));

				//Is deleted setting the last setting
				boolean isOnlySetting = false;
				int dSize = docketResponses.size();
				if (dSize == 1)
				{
				    isOnlySetting = true;
				    courtHearingForm.setIsOnlySetting("true");
				}else{
				    courtHearingForm.setIsOnlySetting("false");
				}

				Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
				if (docketResponsesItr.hasNext())
				{
				    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
				    //action date and time (derived value) gets persisted in the court date and court time field on update of court action
				    if (docResp != null && docRespEvent.getSeqNum() != null)
				    {
					//deleted Docket
					courtHearingForm.setDeletedDocket(docResp);
					if (isOnlySetting || docResp.getSeqNum()!=null && docResp.getSeqNum().equals(docRespEvent.getSeqNum())) //last setting
					{
					    if (docResp.getSeqNum().equals(docRespEvent.getSeqNum()))
					    {
						courtHearingForm.setIsLastSetting("true");
					    }
					    docResp.setDeleteFlag("true");
					    courtHearingForm.setDeleteFlag("true");
					    courtHearingForm.setDeleteSettingDate(docRespEvent.getCourtDate());
					    courtHearingForm.setDeleteDocketEventType("COURT");
					    courtHearingForm.setCurrentSettingChainNumber(docRespEvent.getChainNumber());
					    courtHearingForm.setDeleteDocketEventId(docResp.getDocketEventId());
					    courtHearingForm.setDeleteDocketEventIdKey(docResp.getDocketEventIdKey());
					    courtHearingForm.setCurrentSettingDocketEventId(docRespEvent.getDocketEventId());
					    courtHearingForm.setCurrentSettingDocketEventIdKey(docRespEvent.getDocketEventIdKey());
					}
					else
					    if ((Integer.parseInt(docResp.getSeqNum()) - Integer.parseInt(docRespEvent.getSeqNum())) == 10)
					    {
						courtHearingForm.setIsLastSetting("false");
						docResp.setDeleteFlag("true");
						courtHearingForm.setDeleteFlag("true");
						courtHearingForm.setDeleteSettingDate(docResp.getCourtDate());
						courtHearingForm.setDeleteDocketEventType("COURT");
						courtHearingForm.setCurrentSettingChainNumber(docRespEvent.getChainNumber());
						courtHearingForm.setDeleteDocketEventId(docResp.getDocketEventId());
						courtHearingForm.setDeleteDocketEventIdKey(docResp.getDocketEventIdKey());
						courtHearingForm.setCurrentSettingDocketEventId(docRespEvent.getDocketEventId());
						courtHearingForm.setCurrentSettingDocketEventIdKey(docRespEvent.getDocketEventIdKey());
					    }
					    else
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Back-Out Allowed Only On The Last Record In Chain. Subsequent Records Have Been Found"));
						saveErrors(aRequest, errors);
						return aMapping.findForward(UIConstants.FAILURE);
					    }
				    }
				}
			    }//end of if court

			    if (docRespEvent.getDocketType().equalsIgnoreCase("ANCILLARY"))
			    {
				GetJJSCLAncillaryCourtByChainAndSeqNumEvent ancillaryEvt = (GetJJSCLAncillaryCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYCHAINANDSEQNUM);
				ancillaryEvt.setChainNumber(docRespEvent.getChainNumber());
				dispatch.postEvent(ancillaryEvt);
				CompositeResponse ancillaryResp = (CompositeResponse) dispatch.getReply();

				List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(ancillaryResp, DocketEventResponseEvent.class);
				/*Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();*/

				Collections.sort((List<DocketEventResponseEvent>) docketResponses, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
				    @Override
				    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
				    {
					if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
					    return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
					else
					    return -1;
				    }
				}));
				//Is deleted setting the last setting
				boolean isOnlySetting = false;
				int dSize = docketResponses.size();
				if (dSize == 1)
				{
				    isOnlySetting = true;
				    courtHearingForm.setIsOnlySetting("true");
				}
				Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
				if (docketResponsesItr.hasNext())
				{
				    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
				    //action date and time (derived value) gets persisted in the court date and court time field on update of court action
				    if (docResp != null && docRespEvent.getSeqNum() != null)
				    {
					//deleted Docket
					courtHearingForm.setDeletedDocket(docResp);
					if (isOnlySetting || docResp.getSeqNum().equals(docRespEvent.getSeqNum()))
					{
					    if (docResp.getSeqNum().equals(docRespEvent.getSeqNum()))
					    {
						courtHearingForm.setIsLastSetting("true");
					    }
					    docResp.setDeleteFlag("true");
					    courtHearingForm.setDeleteFlag("true");
					    courtHearingForm.setCurrentSettingChainNumber(docRespEvent.getChainNumber());
					    courtHearingForm.setDeleteSettingDate(docRespEvent.getCourtDate());
					    courtHearingForm.setDeleteDocketEventType("ANCILLARY");
					    courtHearingForm.setDeleteDocketEventId(docRespEvent.getDocketEventId());
					    courtHearingForm.setDeleteDocketEventIdKey(docRespEvent.getDocketEventIdKey());
					    courtHearingForm.setCurrentSettingDocketEventId(docRespEvent.getDocketEventId());
					    courtHearingForm.setCurrentSettingDocketEventIdKey(docRespEvent.getDocketEventIdKey());
					}
					else
					    if ((Integer.parseInt(docResp.getSeqNum()) - Integer.parseInt(docRespEvent.getSeqNum())) == 10)
					    {
						courtHearingForm.setIsLastSetting("false");
						docResp.setDeleteFlag("true");
						courtHearingForm.setDeleteFlag("true");
						courtHearingForm.setCurrentSettingChainNumber(docRespEvent.getChainNumber());
						courtHearingForm.setDeleteSettingDate(docResp.getCourtDate());
						courtHearingForm.setDeleteDocketEventType("ANCILLARY");
						courtHearingForm.setDeleteDocketEventId(docResp.getDocketEventId());
						courtHearingForm.setDeleteDocketEventIdKey(docResp.getDocketEventIdKey());
						courtHearingForm.setCurrentSettingDocketEventId(docRespEvent.getDocketEventId());
						courtHearingForm.setCurrentSettingDocketEventIdKey(docRespEvent.getDocketEventIdKey());
					    }
					    else
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Back-Out Allowed Only On The Last Record In Chain. Subsequent Records Have Been Found"));
						saveErrors(aRequest, errors);
						return aMapping.findForward(UIConstants.FAILURE);
					    }
				    }
				}
			    }//Ancillary

			    /**
			     * 
			     2. If the user has selected a setting and
			     * requested the delete function: A. The last
			     * Calendar Detention record (highest SEQ.NUM) in
			     * the current chain should be deleted if the Court
			     * Date on that Calendar Detention record is the
			     * same as the �Reset To� (date) populating the
			     * current Calendar Detention record. In addition,
			     * delete the Result (a.k.a. Hearing Decision) and
			     * the Disposition from the current calendar
			     * detention record and clear the on-screen fields.
			     * {NOTE: The �last� record (by Sequence Number) can
			     * have a court date preceding the current Hearing
			     * Date� {ex. Juvenile appeared in court at an
			     * earlier date than scheduled.} OR, B. If the last
			     * Calendar Detention record (highest SEQ.NUM) in
			     * the current calendar chain does not have the same
			     * date as the �Reset To� date populating the
			     * current Calendar Detention record {i.e. Reset
			     * calendar record is not the last detention hearing
			     * for the current chain number}: 1) Display:
			     * �BACK-OUT ALLOWED ONLY ON THE LAST RECORD IN
			     * CHAIN; SUBSEQUENT RECORDS HAVE BEEN FOUND.� 2)
			     * Wait for action from user.
			     */
			    if (docRespEvent.getDocketType().equalsIgnoreCase("DETENTION"))
			    {
				GetJJSCLDetentionByChainAndSeqNumEvent detentionEvt = (GetJJSCLDetentionByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAINANDSEQNUM);
				detentionEvt.setChainNumber(docRespEvent.getChainNumber());
				dispatch.postEvent(detentionEvt);
				CompositeResponse detentionResp = (CompositeResponse) dispatch.getReply();

				List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(detentionResp, DocketEventResponseEvent.class);
				
				Collections.sort((List<DocketEventResponseEvent>) docketResponses, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
				    @Override
				    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
				    {
					if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
					    return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
					else
					    return -1;
				    }
				}));

				//Is deleted setting the last setting
				boolean isOnlySetting = false;
				int dSize = docketResponses.size();
				if (dSize == 1)
				{
				    isOnlySetting = true;
				    courtHearingForm.setIsOnlySetting("true");
				}
				Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
				if (docketResponsesItr.hasNext())
				{
				    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();

				    //action date and time (derived value) gets persisted in the court date and court time field on update of court action
				    if (docResp != null && docRespEvent.getSeqNum() != null)
				    {
					//deleted Docket
					courtHearingForm.setDeletedDocket(docResp);
					if (isOnlySetting || docResp.getSeqNum().equals(docRespEvent.getSeqNum())) //last setting
					{
					    if (docResp.getSeqNum().equals(docRespEvent.getSeqNum()))
					    {
						courtHearingForm.setIsLastSetting("true");
					    }
					    docResp.setDeleteFlag("true");
					    courtHearingForm.setDeleteFlag("true");
					    courtHearingForm.setCurrentSettingChainNumber(docRespEvent.getChainNumber());

					    courtHearingForm.setDeleteSettingDate(docRespEvent.getCourtDate());
					    courtHearingForm.setDeleteDocketEventType("DETENTION");
					    courtHearingForm.setDeleteDocketEventId(docRespEvent.getDocketEventId());
					    courtHearingForm.setDeleteDocketEventIdKey(docRespEvent.getDocketEventIdKey());
					    courtHearingForm.setCurrentSettingDocketEventId(docRespEvent.getDocketEventId());
					    courtHearingForm.setCurrentSettingDocketEventIdKey(docRespEvent.getDocketEventIdKey());
					}
					else
					    if ((Integer.parseInt(docResp.getSeqNum()) - Integer.parseInt(docRespEvent.getSeqNum())) == 10)
					    {
						courtHearingForm.setIsLastSetting("false");
						docResp.setDeleteFlag("true");
						courtHearingForm.setDeleteFlag("true");
						courtHearingForm.setCurrentSettingChainNumber(docRespEvent.getChainNumber());

						courtHearingForm.setDeleteSettingDate(docResp.getCourtDate());
						courtHearingForm.setDeleteDocketEventType("DETENTION");
						courtHearingForm.setDeleteDocketEventId(docResp.getDocketEventId());
						courtHearingForm.setDeleteDocketEventIdKey(docResp.getDocketEventIdKey());
						courtHearingForm.setCurrentSettingDocketEventId(docRespEvent.getDocketEventId());
						courtHearingForm.setCurrentSettingDocketEventIdKey(docRespEvent.getDocketEventIdKey());
					    }
					    else
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Back-Out Allowed Only On The Last Record In Chain. Subsequent Records Have Been Found"));
						saveErrors(aRequest, errors);
						return aMapping.findForward(UIConstants.FAILURE);
					    }
				    }
				}
			    }//detention check
			}
		    }
		}
	    }
	}

	return aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY);
    }

    /**
     * Submit Court Action
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward go(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.clear();
	
	courtHearingForm.setAction("submitCourtAction");
	String courtId = courtHearingForm.getCourtId();
	String date = courtHearingForm.getCourtDate();

	if (courtId == null || (courtId != null && courtId.isEmpty()))
	{
	    courtHearingForm.setAction("error");
	    courtHearingForm.setCursorPosition("courtId");
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Court Missing, Please Correct And Retry"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);

	}
	if (date == null || (date != null && date.isEmpty()))
	{
	    courtHearingForm.setAction("error");
	    courtHearingForm.setCursorPosition("courtDate");
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Date Missing, Please Correct And Retry"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}

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
		if (crtRespEvt.getRefereesCourtInd() != null && crtRespEvt.getRefereesCourtInd().equalsIgnoreCase("Y"))
		{
		    courtHearingForm.setAction("error");
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Referee Court Not Valid For This Option, Please Correct And Retry"));
		    saveErrors(aRequest, errors);
		    courtHearingForm.setCursorPosition("courtId");
		    return aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY);
		}
	    }
	    //task 174215 for performance improvement with single fetch for all tables
	    List<JuvenileDispositionCodeResponseEvent> juvenileDispositions = JuvenileDistrictCourtHelper.getSortedCourtDecisions();
	    courtHearingForm.setCourtDecisionsResponses(juvenileDispositions);
	    List<JSONObject> districtcourtDecisionsList = JuvenileDistrictCourtHelper.districtcourtDecisionResponses(juvenileDispositions);
	    courtHearingForm.setDistrictcourtDecisionsList(districtcourtDecisionsList);
	    courtHearingForm.setCourtHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes("COURT"));
	    
	    Map<String, JuvenileCourtDecisionCodeResponseEvent> courtDecisionsMap = new HashMap<String, JuvenileCourtDecisionCodeResponseEvent>();
	    List<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = JuvenileCaseHelper.getCourtDecisionsNew();

	    GetJJSAllCourtByRefereeCourtEvent jjsCLCrtEvent = (GetJJSAllCourtByRefereeCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSALLCOURTBYREFEREECOURT);
	    jjsCLCrtEvent.setCourtDate(date);
	    jjsCLCrtEvent.setCourtId(courtId);
	    CompositeResponse resp = MessageUtil.postRequest(jjsCLCrtEvent);
	    List<DocketEventResponseEvent> crtDktRespEvts = MessageUtil.compositeToList(resp, DocketEventResponseEvent.class);

	    if (crtDktRespEvts != null && !crtDktRespEvts.isEmpty())
	    {
		Iterator<DocketEventResponseEvent> dktRespEvtsItr = crtDktRespEvts.iterator();

		while (dktRespEvtsItr.hasNext())
		{
		    DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) dktRespEvtsItr.next();
		    if (docRespEvent != null)
		    {
			if (docRespEvent.getRecType().equalsIgnoreCase("COURT")||docRespEvent.getRecType().equalsIgnoreCase("I.COURT")||docRespEvent.getRecType().equalsIgnoreCase("S.COURT"))
			{
        			if (docRespEvent.getCourtResult() != null && !docRespEvent.getCourtResult().isEmpty())
        			{
        			    if (juvenileDispositions != null)
        			    {
        				Iterator<JuvenileDispositionCodeResponseEvent> juvDispItr = juvenileDispositions.iterator();
        				while (juvDispItr.hasNext())
        				{
        				    JuvenileDispositionCodeResponseEvent juvDisp = juvDispItr.next();
        				    if (juvDisp != null && juvDisp.getCodeAlpha() != null && juvDisp.getCodeAlpha().equalsIgnoreCase(docRespEvent.getCourtResult()) && juvDisp.getOptionCode() != null && juvDisp.getOptionCode().equalsIgnoreCase("R"))
        				    {
        					GetProductionSupportCourtRecordsEvent courtEvt = (GetProductionSupportCourtRecordsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCOURTRECORDS);//reusing the existing mapping by paasing juvnum and chainnum to jjclcourt
        
        					courtEvt.setJuvenileNumber(docRespEvent.getJuvenileNumber());
        					courtEvt.setChainNumber(docRespEvent.getChainNumber());
        
        					dispatch.postEvent(courtEvt);
        
        					CompositeResponse courtResp = (CompositeResponse) dispatch.getReply();
        					List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtResp, DocketEventResponseEvent.class);
        					if (docketResponses != null && !docketResponses.isEmpty())
        					{
        					    // sort Seq Number asc
        					    Collections.sort((List<DocketEventResponseEvent>) docketResponses, new Comparator<DocketEventResponseEvent>() {
        						@Override
        						public int compare(DocketEventResponseEvent crt1, DocketEventResponseEvent crt2)
        						{
        						    if (crt1.getSeqNum() != null && crt2.getSeqNum() != null)
        							return Integer.valueOf(crt1.getSeqNum()).compareTo(Integer.valueOf(crt2.getSeqNum()));
        						    else
        							return -1;
        						}
        					    });
        					}
        
        					Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
        					int currentSeqnum = Integer.valueOf(docRespEvent.getSeqNum());
        					while (docketResponsesItr.hasNext())
        					{
        					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
        					    int nextSeqnum = Integer.valueOf(docResp.getSeqNum());
        					    if (docResp != null && nextSeqnum > currentSeqnum)
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
        				}//while end 
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
        			}//end of if (resp.getJuvenileNumber() != null && resp.getReferralNum() != null)
			}//END OF COURT
			if (docRespEvent.getRecType().equalsIgnoreCase("DETENTION")||docRespEvent.getRecType().equalsIgnoreCase("I.DETENTION")||docRespEvent.getRecType().equalsIgnoreCase("S.DETENTION"))
			{
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
			    

			    if (docRespEvent.getCourtResult() != null && !docRespEvent.getCourtResult().isEmpty())
			    {
				//83426
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
				if (juvenileDispositions != null)
				{
				    Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvenileDispositions.iterator();
				    while (juvCodeIter.hasNext())
				    {
					JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
					if (dispResp != null && dispResp.getCodeAlpha() != null && dispResp.getCodeAlpha().equalsIgnoreCase(docRespEvent.getCourtResult()) && dispResp.getOptionCode() != null && dispResp.getOptionCode().equals("R"))
					{
					    GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent detentionEvt = (GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETJJSCLDETENTIONBYJUVNUMREFNUMCHAINNUMCOURTDATE);
					    detentionEvt.setJuvenileNumber(docRespEvent.getJuvenileNumber());
					    detentionEvt.setChainNumber(docRespEvent.getChainNumber());

					    dispatch.postEvent(detentionEvt);

					    CompositeResponse detnResp = (CompositeResponse) dispatch.getReply();
					    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(detnResp, DocketEventResponseEvent.class);
					    if (docketResponses != null && !docketResponses.isEmpty())
					    {
						// sort Seq Number asc
						Collections.sort((List<DocketEventResponseEvent>) docketResponses, new Comparator<DocketEventResponseEvent>() {
						    @Override
						    public int compare(DocketEventResponseEvent crt1, DocketEventResponseEvent crt2)
						    {
							if (crt1.getSeqNum() != null && crt2.getSeqNum() != null)
							    return Integer.valueOf(crt1.getSeqNum()).compareTo(Integer.valueOf(crt2.getSeqNum()));
							else
							    return -1;
						    }
						});
					    }
					    Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
					    int currentSeqnum = Integer.valueOf(docRespEvent.getSeqNum());
					    while (docketResponsesItr.hasNext())
					    {
						DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
						int nextSeqnum = Integer.valueOf(docResp.getSeqNum());
						if (docResp != null && nextSeqnum > currentSeqnum)
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
			    }

			}//DETENTION if end
			 //
			if (docRespEvent.getRecType().equalsIgnoreCase("ANCILLARY")||docRespEvent.getRecType().equalsIgnoreCase("I.ANCILLARY")||docRespEvent.getRecType().equalsIgnoreCase("S.ANCILLARY"))
			{
			    if (docRespEvent.getCourtResult() != null && !docRespEvent.getCourtResult().isEmpty())
			    {

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
			}

			//
			//only this if above can be moved to command
			/*if (docRespEvent.getJuvenileNumber() != null && docRespEvent.getReferralNum() != null)
			{*/
			docketsMap.put(docRespEvent.getDocketEventIdKey(), docRespEvent);

			// add it to the new object to maintain originality
			DocketEventResponseEvent origDocket = new DocketEventResponseEvent();
			try
			{
			    //ConvertUtils.register(new DateConverter(null), Date.class);
			    Date defaultValue = null;
			    Converter converter = new DateConverter(defaultValue);
			    BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
			    beanUtilsBean.getConvertUtils().register(converter, Date.class);
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

			//} ///end of if (docRespEvent.getJuvenileNumber() != null && docRespEvent.getReferralNum() != null)
		    }
		}
	    }
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
	    courtHearingForm.setCourtDecisionsList(courtDecisionsList);//add for district dropdown too
	    courtHearingForm.setDetentionHearingResults(courtDecisionResponseEvts);	    
	    courtHearingForm.setAncillaryHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes("ANCILLARY"));
	    
	    // //83426
	    //commented as task 174215
	    /*List<JuvenileDispositionCodeResponseEvent> juvenileDispositions = JuvenileDistrictCourtHelper.getSortedCourtDecisions();
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
			    //83426
			    // court Decision Code table.
			   // GetJuvenileDispositionCodeEvent courtDisp = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
			   // courtDisp.setCode(docRespEvent.getCourtResult());
			  //  CompositeResponse dispCompResp = postRequestEvent(courtDisp);
			 //   Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
			    if (juvenileDispositions != null)
			    {
				Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvenileDispositions.iterator();
				while (juvCodeIter.hasNext())
				{
				    JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
				    if (dispResp != null 
					    && dispResp.getCodeAlpha()!=null 
					    && dispResp.getCodeAlpha().equalsIgnoreCase(docRespEvent.getCourtResult()) 
					    && dispResp.getOptionCode()!=null && dispResp.getOptionCode().equalsIgnoreCase("R"))
				    {
					GetJJSCLCourtByChainAndSeqNumEvent courtEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
					courtEvt.setChainNumber(docRespEvent.getChainNumber());
					courtEvt.setSeqNumber(String.valueOf(Integer.valueOf(docRespEvent.getSeqNum()) + 10));
					GetProductionSupportCourtRecordsEvent courtEvt = (GetProductionSupportCourtRecordsEvent)
							EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCOURTRECORDS);//reusing the existing mapping by paasing juvnum and chainnum to jjclcourt

					courtEvt.setJuvenileNumber(docRespEvent.getJuvenileNumber());
					courtEvt.setChainNumber(docRespEvent.getChainNumber());
					dispatch.postEvent(courtEvt);

					CompositeResponse courtResp = (CompositeResponse) dispatch.getReply();
					List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtResp, DocketEventResponseEvent.class);
					if (docketResponses != null && !docketResponses.isEmpty())
					{
					 // sort Seq Number asc
					    Collections.sort((List<DocketEventResponseEvent>) docketResponses, new Comparator<DocketEventResponseEvent>() {
						@Override
						public int compare(DocketEventResponseEvent crt1, DocketEventResponseEvent crt2)
						{
						    if (crt1.getSeqNum() != null && crt2.getSeqNum() != null)
							return Integer.valueOf(crt1.getSeqNum()).compareTo(Integer.valueOf(crt2.getSeqNum()));
						    else
							return -1;
						}
					    });
					}
					Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
					int currentSeqnum=Integer.valueOf(docRespEvent.getSeqNum());
					while (docketResponsesItr.hasNext())
					{
					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
					    int nextSeqnum=Integer.valueOf(docResp.getSeqNum());
					    //action date and time (derived value) gets persisted in the court date and court time field on update of court action
					    if (docResp != null && nextSeqnum > currentSeqnum)
					    {
						docRespEvent.setActionDate(docResp.getCourtDate());
						docRespEvent.setActionTime(JuvenileFacilityHelper.stripColonInDate(docResp.getCourtTime()));
						docRespEvent.setOriginalActionDate(docResp.getCourtDate());
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
			    
			    //83426
			    String ctAssignJpoId  = "";
			    JJSReferral ref  = JuvenileDistrictCourtHelper.getRefInfo(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
			    if(ref!=null){
				ctAssignJpoId = ref.getCtAssignJPOId();
			    }
			    if (ctAssignJpoId!=null && !ctAssignJpoId.isEmpty())
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
			    }else{
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
		    } // juvenile no check
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

	    //pre-load new court decision code table
	    List<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = JuvenileCaseHelper.getCourtDecisionsNew();
	    
	    if (clDetnDktRespEvts != null && !clDetnDktRespEvts.isEmpty())
	    {
		Iterator<DocketEventResponseEvent> clDetnDktRespItr = clDetnDktRespEvts.iterator();
		while (clDetnDktRespItr.hasNext())
		{
		    DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) clDetnDktRespItr.next();
		    if (docRespEvent != null)
		    {
			//disable reset Date when the required Reset is NO
			//GetJuvenileCourtDecisionCodesEvent courtDecision = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
		//	Collection<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = MessageUtil.postRequestListFilter(courtDecision, JuvenileCourtDecisionCodeResponseEvent.class);
			if (crtDecisions != null)
			{
			    Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
			    while (crtDecisionsItr.hasNext())
			    {
				JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
				if (crtDecision != null)
				{
				    //filter
				    if (docRespEvent.getHearingType() != null && docRespEvent.getHearingType().equalsIgnoreCase("DT"))
				    {
					if (crtDecision.getDecision() != null && crtDecision.getDecision().equalsIgnoreCase("DT") || crtDecision.getDecision().equalsIgnoreCase("BOTH"))
					{
					    courtDecisionsMap.put(crtDecision.getCode(), crtDecision);
					}
				    }
				    else
				    {
					if (docRespEvent.getHearingType() != null && docRespEvent.getHearingType().equalsIgnoreCase("PC"))
					{
					    if (crtDecision.getDecision() != null && crtDecision.getDecision().equalsIgnoreCase("PC") || crtDecision.getDecision().equalsIgnoreCase("BOTH"))
					    {
						courtDecisionsMap.put(crtDecision.getCode(), crtDecision);
					    }
					}
				    }

				    if (crtDecision.getResetAllowed() != null && !crtDecision.getResetAllowed().isEmpty())
				    {
					if (crtDecision.getResetAllowed() != null && crtDecision.getResetAllowed().equalsIgnoreCase("N"))
					{
					    docRespEvent.setDisableResetDate("true");
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
			  //  GetJuvenileCourtDecisionCodesEvent courtDecsn = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
			//    courtDecsn.setCode(docRespEvent.getCourtResult());
			//    Collection<JuvenileCourtDecisionCodeResponseEvent> crtDescisions = MessageUtil.postRequestListFilter(courtDecsn, JuvenileCourtDecisionCodeResponseEvent.class);
			    if (crtDecisions != null)
			    {
				Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
				while (crtDecisionsItr.hasNext())
				{
				    JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
				    if (crtDecision != null)
				    {
					if (crtDecision.getResetAllowed()!=null && crtDecision.getResetAllowed().equalsIgnoreCase("Y") || crtDecision.getResetAllowed().equalsIgnoreCase("R"))
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
			    //83426
			    // court Decision Code table.
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

				    if (dispResp != null && dispResp.getCodeAlpha()!=null && dispResp.getCodeAlpha().equalsIgnoreCase(docRespEvent.getCourtResult()) && dispResp.getOptionCode()!=null && dispResp.getOptionCode()!=null && dispResp.getOptionCode().equals("R"))
				    {
					//change here
					GetJJSCLDetentionByChainAndSeqNumEvent detentionEvt = (GetJJSCLDetentionByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAINANDSEQNUM);
					detentionEvt.setChainNumber(docRespEvent.getChainNumber());
					detentionEvt.setSeqNumber(String.valueOf(Integer.valueOf(docRespEvent.getSeqNum()) + 10));
					GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent detentionEvt = (GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETJJSCLDETENTIONBYJUVNUMREFNUMCHAINNUMCOURTDATE);
					detentionEvt.setJuvenileNumber(docRespEvent.getJuvenileNumber());
					detentionEvt.setChainNumber(docRespEvent.getChainNumber());	
					dispatch.postEvent(detentionEvt);

					CompositeResponse detnResp = (CompositeResponse) dispatch.getReply();
					List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(detnResp, DocketEventResponseEvent.class);
					if (docketResponses != null && !docketResponses.isEmpty())
					{
					 // sort Seq Number asc
					    Collections.sort((List<DocketEventResponseEvent>) docketResponses, new Comparator<DocketEventResponseEvent>() {
						@Override
						public int compare(DocketEventResponseEvent crt1, DocketEventResponseEvent crt2)
						{
						    if (crt1.getSeqNum() != null && crt2.getSeqNum() != null)
							return Integer.valueOf(crt1.getSeqNum()).compareTo(Integer.valueOf(crt2.getSeqNum()));
						    else
							return -1;
						}
					    });
					}	
					Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
					int currentSeqnum=Integer.valueOf(docRespEvent.getSeqNum());
					while (docketResponsesItr.hasNext())
					{
					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
					    int nextSeqnum=Integer.valueOf(docResp.getSeqNum());
					    //action date and time (derived value) gets persisted in the court date and court time field on update of court action
					    if (docResp != null && nextSeqnum > currentSeqnum)
					    {
						docRespEvent.setActionDate(docResp.getCourtDate());
						docRespEvent.setOriginalActionDate(docResp.getCourtDate());
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

			if (docRespEvent.getJuvenileNumber() != null && docRespEvent.getReferralNum() != null)
			{
			    //bug fix for 131100 to show J0+juvnum if no petitionnum in docket record.
			    if (docRespEvent.getPetitionNumber() == null)
				docRespEvent.setPetitionNumber("J0" + docRespEvent.getJuvenileNumber()); // bug no:

			     // get the JPO ID.commented below is followed after JJS referral conversion
			     JuvenileProfileReferralListResponseEvent clDetProfileResp = JuvenileFacilityHelper.getCasefilesFromReferral(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
			     Collection<JuvenileCasefileReferral> clDetCasefilesResp = clDetProfileResp.getCasefileReferrals();

			     // sorts in descending order by assignment Date num.
			     Collections.sort((List<JuvenileCasefileReferral>) clDetCasefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
			    @Override
			    public int compare(JuvenileCasefileReferral evt1,JuvenileCasefileReferral evt2)
			    {
			        if (evt1.getCaseFile() != null && evt1.getCaseFile().getAssignmentAddDate() != null && evt2.getCaseFile() != null && evt2.getCaseFile().getAssignmentAddDate() != null)
			    	return evt1.getCaseFile().getAssignmentAddDate().compareTo(evt1.getCaseFile().getAssignmentAddDate());
			        else
			    	return -1;
			    }
			     }));
			     Iterator<JuvenileCasefileReferral> clDetCasefileItr = clDetCasefilesResp.iterator();
			     while (clDetCasefileItr.hasNext())
			     {
			    JuvenileCasefileReferral responseEvt = clDetCasefileItr.next();
			    if (responseEvt != null && responseEvt.getSupervisionCat()!=null && responseEvt.getSupervisionCat().equalsIgnoreCase("AD"))
			    {
			        docRespEvent.setProbationOfficerCd(responseEvt.getCaseFile().getProbationOfficer().getLogonId());
			        break;
			    }
			     }

			    // get the JPO ID. //83426
			    String ctAssignJpoId  = "";
			    JJSReferral ref  = JuvenileDistrictCourtHelper.getRefInfo(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
			    if(ref!=null){
				ctAssignJpoId = ref.getCtAssignJPOId();
			    }
			    if (ctAssignJpoId!=null && !ctAssignJpoId.isEmpty())
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
			    }else{
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
		}
		courtHearingForm.setHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes(null));
		List<JuvenileCourtDecisionCodeResponseEvent> courtDecisionResponseEvts = new ArrayList<JuvenileCourtDecisionCodeResponseEvent>(courtDecisionsMap.values());
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
	    }

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
			   // GetJuvenileDispositionCodeEvent courtDisp = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
			 //   courtDisp.setCode(docRespEvent.getCourtResult());
			 //   CompositeResponse dispCompResp = postRequestEvent(courtDisp);
			 //   Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
			    if (juvenileDispositions != null)
			    {
				Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvenileDispositions.iterator();
				while (juvCodeIter.hasNext())
				{
				    JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
				    if (dispResp != null &&dispResp.getCodeAlpha()!=null && dispResp.getCodeAlpha().equalsIgnoreCase(docRespEvent.getCourtResult())&& dispResp.getOptionCode()!=null &&dispResp.getOptionCode().equals("R"))
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
					    //action date and time (derived value) gets persisted in the court date and court time field on update of court action
					    if (docResp != null)
					    {
						docRespEvent.setActionDate(docResp.getCourtDate());
						docRespEvent.setActionTime(JuvenileFacilityHelper.stripColonInDate(docResp.getCourtTime()));
						docRespEvent.setOriginalActionDate(docResp.getCourtDate());
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
			//reset Hearing Type Logic
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
	    }*/

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
							cmp = 0; //(equalsIgnoreCase)
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
     * Update Court Action
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)//,String docketeventId
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.setAction("updateCourtAction");
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	String pagerOffset = (String) aRequest.getParameter("pager.offset");
	courtHearingForm.setPagerOffset(pagerOffset);
	Map<String, DocketEventResponseEvent> originalDktSearchResultsMap = (Map<String, DocketEventResponseEvent>) aRequest.getSession().getAttribute("originalSetting");//courtHearingForm.getOriginalDktSearchResultsMap();
	
	String currentSettingJuvenileNum = null;
	boolean isRecordUpdated = false;
	boolean isNewRecordCreated = false;
	boolean isEmailsend = false;
	boolean isdispositionemailSend = false;
	boolean isSubsequentRecUpdated = false;
	
	boolean isOnlySettingDeleted= false;

	boolean atleastOneSettingChanged = false;
	ArrayList<DocketEventResponseEvent> dockets=new ArrayList<DocketEventResponseEvent>();
	/*if(!docketeventId.isEmpty()&&docketeventId!=null)
	{
	      
	    Collection<DocketEventResponseEvent> docketEventList = courtHearingForm.getUpdatedDocketList() ;
		for( DocketEventResponseEvent currentEvent : docketEventList)
		{
			 if( currentEvent.getDocketEventId().equals(docketeventId))
			 {
			     dockets.add(currentEvent);
			     break;
			 }
		}
	    
	}
	else*/
	dockets = (ArrayList<DocketEventResponseEvent>) courtHearingForm.getUpdatedDocketList();
	if (dockets != null)
	{
	    for (int i = 0; i < dockets.size(); i++)
	    {
		DocketEventResponseEvent currentSettingResp = dockets.get(i);
		System.out.println("Chain number" +  currentSettingResp.getChainNumber());
		
		System.out.println("petitionAmendmentDate: " + currentSettingResp.getPetitionAmendmentDate());

		if (currentSettingResp != null)
		{
		    if (currentSettingResp.getRecType() != null && currentSettingResp.getRecType().contains("."))
		    {
			continue; //skip the i. and s. records for processing.
		    }		
		    /*if(currentSettingResp.getBarNum()==null ||currentSettingResp.getBarNum().isEmpty())
		    {
			currentSettingResp.setAttorneyName("");
			currentSettingResp.setAttorneyConnection("");
			currentSettingResp.setAttorneyConnectionDesc("");
		    }*/
		    DocketEventResponseEvent originalSettingResp = new DocketEventResponseEvent();
		    if (originalDktSearchResultsMap != null)
		    {
			originalSettingResp = originalDktSearchResultsMap.get(currentSettingResp.getDocketEventIdKey());
			System.out.println("originalSettingResp amendmentDate: " + originalSettingResp.getPetitionAmendmentDate());
		    }

		    if (((currentSettingResp.getCourtResult() == null && originalSettingResp.getCourtResult() == null || currentSettingResp.getCourtResult() != null && currentSettingResp.getCourtResult().isEmpty() && originalSettingResp.getCourtResult() == null) || (originalSettingResp.getCourtResult() != null && currentSettingResp.getCourtResult() != null && currentSettingResp.getCourtResult().equalsIgnoreCase(originalSettingResp.getCourtResult()))) 
			    && ((currentSettingResp.getDisposition() == null && originalSettingResp.getDisposition() == null || currentSettingResp.getDisposition() != null && currentSettingResp.getDisposition().isEmpty() && originalSettingResp.getDisposition() == null) || (originalSettingResp.getDisposition() != null && currentSettingResp.getDisposition() != null && currentSettingResp.getDisposition().equalsIgnoreCase(originalSettingResp.getDisposition()))) 
			    && ((currentSettingResp.getTransferTo() == null && originalSettingResp.getTransferTo() == null || currentSettingResp.getTransferTo() != null && currentSettingResp.getTransferTo().isEmpty() && originalSettingResp.getTransferTo() == null) || (currentSettingResp.getTransferTo() != null && originalSettingResp.getTransferTo() != null && currentSettingResp.getTransferTo().equalsIgnoreCase(originalSettingResp.getTransferTo()))) 
			    && ((currentSettingResp.getActionDate() == null && originalSettingResp.getActionDate() == null || currentSettingResp.getActionDate() != null && currentSettingResp.getActionDate().isEmpty() && originalSettingResp.getActionDate() == null) || (currentSettingResp.getActionDate() != null && originalSettingResp.getActionDate() != null && currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))) 
			    && ((currentSettingResp.getActionTime() == null && originalSettingResp.getActionTime() == null || currentSettingResp.getActionTime() != null && currentSettingResp.getActionTime().isEmpty() && originalSettingResp.getActionTime() == null) || (currentSettingResp.getActionTime() != null && originalSettingResp.getActionTime() != null && currentSettingResp.getActionTime().equalsIgnoreCase(originalSettingResp.getActionTime()))) 
			    && ((currentSettingResp.getResetHearingType() == null && originalSettingResp.getResetHearingType() == null || currentSettingResp.getResetHearingType() != null && currentSettingResp.getResetHearingType().isEmpty() && originalSettingResp.getResetHearingType() == null) || (currentSettingResp.getResetHearingType() != null && originalSettingResp.getHearingType() != null && currentSettingResp.getResetHearingType().equalsIgnoreCase(originalSettingResp.getResetHearingType()))) 
			    && ((currentSettingResp.getPrevNotes() == null && originalSettingResp.getPrevNotes() == null || currentSettingResp.getPrevNotes() != null && currentSettingResp.getPrevNotes().isEmpty() && originalSettingResp.getPrevNotes() == null) || (currentSettingResp.getPrevNotes() != null && originalSettingResp.getPrevNotes() != null && currentSettingResp.getPrevNotes().equalsIgnoreCase(originalSettingResp.getPrevNotes()))) 
			    && ((currentSettingResp.getAttorneyConnection() == null && originalSettingResp.getAttorneyConnection() == null || currentSettingResp.getAttorneyConnection() != null && currentSettingResp.getAttorneyConnection().isEmpty() && originalSettingResp.getAttorneyConnection() == null) || (currentSettingResp.getAttorneyConnection() != null && originalSettingResp.getAttorneyConnection() != null && currentSettingResp.getAttorneyConnection().equalsIgnoreCase(originalSettingResp.getAttorneyConnection()))) 
			    && ((currentSettingResp.getComments() == null && originalSettingResp.getComments() == null || currentSettingResp.getComments() != null && currentSettingResp.getComments().isEmpty() && originalSettingResp.getComments() == null) || (currentSettingResp.getComments() != null && originalSettingResp.getComments() != null && currentSettingResp.getComments().equalsIgnoreCase(originalSettingResp.getComments()))) 
			    && ((currentSettingResp.getBarNum() == null && originalSettingResp.getBarNum() == null || currentSettingResp.getBarNum() != null && currentSettingResp.getBarNum().isEmpty() && originalSettingResp.getBarNum() == null) || (currentSettingResp.getBarNum() != null && originalSettingResp.getBarNum() != null && currentSettingResp.getBarNum().equalsIgnoreCase(originalSettingResp.getBarNum())))
			    && ((currentSettingResp.getAttorneyName() == null && originalSettingResp.getAttorneyName() == null || currentSettingResp.getAttorneyName() != null && currentSettingResp.getAttorneyName().isEmpty() && originalSettingResp.getAttorneyName() == null) || (currentSettingResp.getAttorneyName() != null && originalSettingResp.getAttorneyName() != null && currentSettingResp.getAttorneyName().equalsIgnoreCase(originalSettingResp.getAttorneyName())))
			    && ((currentSettingResp.getGalBarNum() == null && originalSettingResp.getGalBarNum() == null || currentSettingResp.getGalBarNum() != null && currentSettingResp.getBarNum().isEmpty() && originalSettingResp.getGalBarNum() == null) || (currentSettingResp.getGalBarNum() != null && originalSettingResp.getGalBarNum() != null && currentSettingResp.getGalBarNum().equalsIgnoreCase(originalSettingResp.getGalBarNum())))
			    && ((currentSettingResp.getGalName() == null && originalSettingResp.getGalName() == null || currentSettingResp.getGalName() != null && currentSettingResp.getGalName().isEmpty() && originalSettingResp.getGalName() == null) || (currentSettingResp.getGalName() != null && originalSettingResp.getGalName() != null && currentSettingResp.getGalName().equalsIgnoreCase(originalSettingResp.getGalName())))
			    && ((currentSettingResp.getAttorney2BarNum() == null && originalSettingResp.getAttorney2BarNum() == null || currentSettingResp.getAttorney2BarNum() != null && currentSettingResp.getAttorney2BarNum().isEmpty() && originalSettingResp.getAttorney2BarNum() == null) || (currentSettingResp.getAttorney2BarNum() != null && originalSettingResp.getAttorney2BarNum() != null && currentSettingResp.getAttorney2BarNum().equalsIgnoreCase(originalSettingResp.getAttorney2BarNum())))
			    && ((currentSettingResp.getAttorney2Name()== null && originalSettingResp.getAttorney2Name() == null || currentSettingResp.getAttorney2Name() != null && currentSettingResp.getAttorney2Name().isEmpty() && originalSettingResp.getAttorney2Name() == null) || (currentSettingResp.getAttorney2Name() != null && originalSettingResp.getAttorney2Name() != null && currentSettingResp.getAttorney2Name().equalsIgnoreCase(originalSettingResp.getAttorney2Name())))
			    && ((currentSettingResp.getAttorney2Connection() == null && originalSettingResp.getAttorney2Connection() == null || currentSettingResp.getAttorney2Connection() != null && currentSettingResp.getAttorney2Connection().isEmpty() && originalSettingResp.getAttorney2Connection() == null) || (currentSettingResp.getAttorney2Connection() != null && originalSettingResp.getAttorney2Connection() != null && currentSettingResp.getAttorney2Connection().equalsIgnoreCase(originalSettingResp.getAttorney2Connection())))
			    && ((currentSettingResp.getAppAttorney()== null && originalSettingResp.getAppAttorney() == null || currentSettingResp.getAppAttorney() != null && currentSettingResp.getAppAttorney().isEmpty() && originalSettingResp.getAppAttorney() == null) || (currentSettingResp.getAppAttorney() != null && originalSettingResp.getAppAttorney() != null && currentSettingResp.getAppAttorney().equalsIgnoreCase(originalSettingResp.getAppAttorney())))
			    && ((currentSettingResp.getInterpreter()== null && originalSettingResp.getInterpreter() == null || currentSettingResp.getInterpreter() != null && currentSettingResp.getInterpreter().isEmpty() && originalSettingResp.getInterpreter() == null) || (currentSettingResp.getInterpreter() != null && originalSettingResp.getInterpreter() != null && currentSettingResp.getInterpreter().equalsIgnoreCase(originalSettingResp.getInterpreter())))) 	
		    {
			continue;
		    }
		    else
		    {
			atleastOneSettingChanged = true;
			isOnlySettingDeleted= false;

			if (currentSettingResp.getJuvenileNumber() != null && !currentSettingResp.getJuvenileNumber().isEmpty())
			{
			    currentSettingJuvenileNum = currentSettingResp.getJuvenileNumber().substring(1, currentSettingResp.getJuvenileNumber().length());
			}

			GetJuvenileCourtDecisionCodesEvent courtDecsn = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
			courtDecsn.setCode(currentSettingResp.getCourtResult());
			Collection<JuvenileCourtDecisionCodeResponseEvent> crtDescisions = MessageUtil.postRequestListFilter(courtDecsn, JuvenileCourtDecisionCodeResponseEvent.class);
			if (crtDescisions != null)
			{
			    Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDescisions.iterator();
			    while (crtDecisionsItr.hasNext())
			    {
				JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
				if (crtDecision != null)
				{
				    if (crtDecision.getResetAllowed()!=null && crtDecision.getResetAllowed().equalsIgnoreCase("Y") || crtDecision.getResetAllowed().equalsIgnoreCase("R"))
				    {
					currentSettingResp.setDisableResetDate("false");
					break;
				    }
				    else
				    {
					currentSettingResp.setDisableResetDate("true");
					break;
				    }
				}
			    }
			}

			/*********************************************************************** Validation Starts ***************************************************************************************/

			if (currentSettingResp.getActionTime() != null && !currentSettingResp.getActionTime().isEmpty())
			{
			    //validate length
			    if (currentSettingResp.getActionTime().length() < 4)
			    {
				courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
				courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Time Entered. Please Correct And Retry"));
				saveErrors(aRequest, errors);
				courtHearingForm.setAction("error");
				courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
				courtHearingForm.setPrevAction(courtHearingForm.getAction());
				return aMapping.findForward(UIConstants.FAILURE);
			    }
			    
			    String regex = "^[0-9]*$";
			    if (currentSettingResp.getActionTime().matches(regex))
			    {
				SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
				String actionTimeStr = JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime());
				Date actionTime;
				try
				{
				    actionTime = parser.parse(actionTimeStr);
				    Date startTime = parser.parse("06:00");
				    Date endTime = parser.parse("23:59");
				    if (actionTime.before(startTime) || actionTime.after(endTime))
				    {
					courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Time Entered. Please Correct And Retry"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				}
				catch (ParseException e)
				{
				    e.printStackTrace();
				    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
				    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
				    ActionErrors errors = new ActionErrors();
				    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Time Entered. Please Correct And Retry"));
				    saveErrors(aRequest, errors);
				    courtHearingForm.setAction("error");
				    courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
				    courtHearingForm.setPrevAction(courtHearingForm.getAction());
				    return aMapping.findForward(UIConstants.FAILURE);

				}
			    }
			    else
			    {
				courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
				courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Time Entered. Please Correct And Retry"));
				saveErrors(aRequest, errors);
				courtHearingForm.setAction("error");
				courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
				courtHearingForm.setPrevAction(courtHearingForm.getAction());
				return aMapping.findForward(UIConstants.FAILURE);

			    }
			}

			String actionTimeStr = JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime());
			Date actionTime = DateUtil.stringToDate(actionTimeStr, DateUtil.TIME24_FMT_1);

			String courtTimeStr = JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getCourtTime());
			Date courtTime = DateUtil.stringToDate(courtTimeStr, DateUtil.TIME24_FMT_1);

			if (currentSettingResp.getBarNum() != null && !currentSettingResp.getBarNum().isEmpty() && !currentSettingResp.getBarNum().equalsIgnoreCase(originalSettingResp.getBarNum()))
			{
			    //validate bar Number
			    GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
			    request.setBarNum(currentSettingResp.getBarNum());
			    dispatch.postEvent(request);
			    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

			    Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			    Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
			    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
			    Collection exceptions = (Collection) dataMap.get(ReturnException.RETURN_EXCEPTION_TOPIC);
			    if (error != null || attorneyDataList.isEmpty() || exceptions != null)
			    {
				courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
				courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Bar Number. Please Correct"));
				saveErrors(aRequest, errors);
				courtHearingForm.setAction("error");
				courtHearingForm.setCursorPosition("#barNum-" + currentSettingResp.getDocketEventIdKey());
				courtHearingForm.setPrevAction(courtHearingForm.getAction());
				return aMapping.findForward(UIConstants.FAILURE);
			    }
			}
			
			if (currentSettingResp.getGalBarNum() != null && !currentSettingResp.getGalBarNum().isEmpty() && !currentSettingResp.getGalBarNum().equalsIgnoreCase(originalSettingResp.getGalBarNum()))
			{
			    //validate bar Number
			    GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
			    request.setBarNum(currentSettingResp.getGalBarNum());
			    dispatch.postEvent(request);
			    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

			    Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			    Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
			    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
			    Collection exceptions = (Collection) dataMap.get(ReturnException.RETURN_EXCEPTION_TOPIC);
			    if (error != null || attorneyDataList.isEmpty() || exceptions != null)
			    {
				courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
				courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid GAL Bar Number. Please Correct"));
				saveErrors(aRequest, errors);
				courtHearingForm.setAction("error");
				courtHearingForm.setCursorPosition("#galBarNum-" + currentSettingResp.getDocketEventIdKey());
				courtHearingForm.setPrevAction(courtHearingForm.getAction());
				return aMapping.findForward(UIConstants.FAILURE);
			    }else{
				
				courtHearingForm.setAdlitemUpdateFlag(true);
			    }
			}
			//validate attorney 2 barnum
			if (currentSettingResp.getAttorney2BarNum() != null && !currentSettingResp.getAttorney2BarNum().isEmpty() && !currentSettingResp.getAttorney2BarNum().equalsIgnoreCase(originalSettingResp.getAttorney2BarNum()))
			{
			    //validate bar Number
			    GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
			    request.setBarNum(currentSettingResp.getAttorney2BarNum());
			    dispatch.postEvent(request);
			    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

			    Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			    Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
			    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
			    Collection exceptions = (Collection) dataMap.get(ReturnException.RETURN_EXCEPTION_TOPIC);
			    if (error != null || attorneyDataList.isEmpty() || exceptions != null)
			    {
				courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
				courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Attorney2 Bar Number. Please Correct"));
				saveErrors(aRequest, errors);
				courtHearingForm.setAction("error");
				courtHearingForm.setCursorPosition("#2ndAttorneyBarnum-" + currentSettingResp.getDocketEventIdKey());
				courtHearingForm.setPrevAction(courtHearingForm.getAction());
				return aMapping.findForward(UIConstants.FAILURE);
			    }else{
				
				courtHearingForm.setAdlitemUpdateFlag(true);
			    }
			}
			//
			//3.4.6.1 If screen Reset Hearing Type field is null, display �HEARING TYPE VALUE IS MISSING, PLEASE CORRECT AND RETRY.�
			if (currentSettingResp.getResetHearingType() != null && currentSettingResp.getResetHearingType().isEmpty() && !currentSettingResp.getResetHearingType().equalsIgnoreCase(originalSettingResp.getResetHearingType()))
			{
			    ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Hearing Type Value Is Missing, Please Correct And Retry"));
			    saveErrors(aRequest, errors);
			    courtHearingForm.setAction("error");
			    courtHearingForm.setCursorPosition("#hearingType-" + currentSettingResp.getDocketEventIdKey());
			    courtHearingForm.setPrevAction(courtHearingForm.getAction());
			    return aMapping.findForward(UIConstants.FAILURE);
			}
			

			//2)3.4.6.1 If the screen Action Date field is the same as the screen Court Date (inquiry date), 
			//determine if the Action Time is less than the CourtTime/HearingTime on the record AND the screen Result is not �Administrative Reset (A/R)� or �Transfer (TRN),�
			//display �INVALID HEARING DATE, TIME AND RESULT COMBINATION.�  Wait for user response.
			//referralDate = new Date(referralDate);

			if (courtTime != null && currentSettingResp.getActionDate() != null && currentSettingResp.getActionTime() != null && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()) && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
			{
			    if (currentSettingResp.getActionDate()!=null && currentSettingResp.getActionDate().equalsIgnoreCase(currentSettingResp.getCourtDate()) && actionTime.before(courtTime))
			    {
				if (currentSettingResp.getCourtResult()!=null && !currentSettingResp.getCourtResult().equalsIgnoreCase("A/R") || !currentSettingResp.getTransferFlag().equalsIgnoreCase("1"))//add checkbox change !currentSettingResp.getCourtResult().equalsIgnoreCase("TRN")
				{
				    ActionErrors errors = new ActionErrors();
				    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Hearing Date, Time And Result Combination"));
				    saveErrors(aRequest, errors);
				    courtHearingForm.setAction("error");
				    courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
				    courtHearingForm.setPrevAction(courtHearingForm.getAction());
				    return aMapping.findForward(UIConstants.FAILURE);
				}
			    }
			}
			//83836
			/*// point d) added by cassandra after discussion.
			if ((currentSettingResp.getTransferTo() == null || currentSettingResp.getTransferTo() != null && currentSettingResp.getTransferTo().isEmpty()) 
				&& currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true") 
				&& courtHearingForm.getCourtDate() != null && currentSettingResp.getActionDate() != null  &&  courtHearingForm.getCourtDate().equalsIgnoreCase(currentSettingResp.getActionDate()))
			{
			    ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.generic", "Setting Currently Exists For This Date"));
			    saveErrors(aRequest, errors);
			    courtHearingForm.setAction("error");
			    courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
			    courtHearingForm.setPrevAction(courtHearingForm.getAction());
			    return aMapping.findForward(UIConstants.FAILURE);

			}*/

			/**
			 * If docketType is detention.
			 */
			if (currentSettingResp.getDocketType().equalsIgnoreCase("DETENTION"))
			{
			    if (currentSettingResp.getTransferTo() != null && !currentSettingResp.getTransferTo().equalsIgnoreCase(originalSettingResp.getTransferTo()))
			    {
				if (!currentSettingResp.getTransferTo().isEmpty())
				{
				    String districtCourts[] = { "313", "314", "315" };
				    //Case1:
				    //search district court + results -> detention Court.
				    //District Courts (300, 313, 314, 315) can transfer Detention hearings ONLY to 001 (Referee Court).
				    if (Arrays.asList(districtCourts).contains(courtHearingForm.getCourtId()))
				    {
					if (!currentSettingResp.getTransferTo().equalsIgnoreCase("001"))
					{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "District Courts (313, 314, 315) Can Transfer Detention Hearings ONLY To 001 (Referee Court)"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#transferTo-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				    //case 2:
				    //300th can transfer Detention hearings to Detention Court (001).
				    if (courtHearingForm.getCourtId()!=null && courtHearingForm.getCourtId().equalsIgnoreCase("300"))
				    {
					if (currentSettingResp.getTransferTo()!=null && !currentSettingResp.getTransferTo().equalsIgnoreCase("001"))
					{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "300th Can Transfer Detention Hearings To Detention Court (001)"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#transferTo-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				}
			    }

			    if (originalSettingResp != null)
			    {
				/**
				 * 3.4.6.2 8/10/18:�If Result, Disposition and
				 * Action Date/Time fields were populated during
				 * retrieval; Result has been changed to �OFF�;
				 * and user has NOT removed/erased the retrieved
				 * value from Dispostion, Action Date or Action
				 * Time {Legacy code: around line 1179}, display
				 * �INAPPROPRIATE RESULT CODE {Legacy code:
				 * around line 537.}� Place cursor in the screen
				 * RESULT field. Wait for user response.
				 */

				if (originalSettingResp.getCourtResult() != null && !originalSettingResp.getCourtResult().isEmpty() && originalSettingResp.getDisposition() != null && !originalSettingResp.getDisposition().isEmpty() && originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().isEmpty() && originalSettingResp.getActionTime() != null && !originalSettingResp.getActionTime().isEmpty())
				{
				    if (!originalSettingResp.getCourtResult().equalsIgnoreCase(currentSettingResp.getCourtResult()) && currentSettingResp.getCourtResult()!=null && currentSettingResp.getCourtResult().equalsIgnoreCase("OFF") && (currentSettingResp.getDisposition()!=null && !currentSettingResp.getDisposition().isEmpty() || (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true"))))
				    {
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropriate Result Code"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				}

				/**
				 * 4.9.4- detention document 1. If the Result,
				 * or Reset To, or Bar# or Attorney Name fields
				 * [unable to modify Attorney Name] are
				 * populated {even if populated with data
				 * entered prior to current session}: a)
				 * Determine if there is a juvenile master
				 * associated to the setting�s juvenile; If a
				 * Juvenile Master record is not located for the
				 * juvenile on the detention record, in the
				 * Juvenile Number field display: �CANNOT CHANGE
				 * INFORMATION � JUVENILE RECORD DOES NOT
				 * EXIST.�
				 */
				if (currentSettingResp.getCourtResult() != null && !currentSettingResp.getCourtResult().isEmpty() || (currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true") && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty()) || (currentSettingResp.getBarNum() != null && !currentSettingResp.getBarNum().isEmpty()))
				{
				    if (currentSettingJuvenileNum != null && !currentSettingJuvenileNum.isEmpty())
				    {
					boolean isJuvnileFound = JuvenileDistrictCourtHelper.isJuvenileFound(currentSettingJuvenileNum);

					if (!isJuvnileFound)
					{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Cannot Change Information - Juvenile Record Does Not Exist"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				}
			    }

			    /* 4.9.5 Detention Document (a).	If the screen Result field and screen Reset Date are populated for a setting,
			    OTES:  If user populated Reset Date, Result field must be populated.  If user populated Result field with A/R or TRN, then Reset Date must be populated.  If Reset Date is populated and Result is NOT populated, display error message: �RESULT CODE MUST BE ENTERED - CORRECT AND CONTINUE�
			    LEGAC PROCESS: If user populated the Reset To field without a RESULT value, a new hearing was created for the �Reset To� date. All attributes, except Hearing Date, Record ID, Transfer Court, Sequence Number, LCD and LCT and Reset To, are populated with data from the current hearing.*/

			    if ((currentSettingResp.getCourtResult() == null || (currentSettingResp.getCourtResult() != null && currentSettingResp.getCourtResult().isEmpty())) && (currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true") && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty()))
			    {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Result Code Must Be Entered - Correct And Continue"));
				saveErrors(aRequest, errors);
				courtHearingForm.setAction("error");
				courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventId());
				courtHearingForm.setPrevAction(courtHearingForm.getAction());
				return aMapping.findForward(UIConstants.FAILURE);
			    }

			    /*#83836 4.9.5 Detention Document c.If the current Court ID is the same as the Court ID entered in the Transfer To field and the current Hearing Date is the same as the value entered in the Reset To field,  for the same chain number, then display �CANNOT RESET TO SELECTED DATE AND COURT�.*/
			    if (courtHearingForm.getCourtId()!=null && courtHearingForm.getCourtId().equalsIgnoreCase(currentSettingResp.getTransferTo()) && currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true") && courtHearingForm.getCourtDate()!=null && courtHearingForm.getCourtDate().equalsIgnoreCase(currentSettingResp.getActionDate()))
			    {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Cannot Reset To Selected Date And Court"));
				saveErrors(aRequest, errors);
				courtHearingForm.setAction("error");
				courtHearingForm.setCursorPosition("#transferTo-" + currentSettingResp.getDocketEventIdKey());
				courtHearingForm.setPrevAction(courtHearingForm.getAction());
				return aMapping.findForward(UIConstants.FAILURE);

			    }

			    /**
			     * NOTE: ResetDate for subsequent Detention Hearings
			     * in the same chain following a finding of Probable
			     * Cause cannot be more than 18 days in the future.
			     * Display error message: �RESET DATE CANNOT BE MORE
			     * THAN 10 DAYS BEYOND CURRENT DATE.�
			     */
			    //TODO Req Bug
			    if (currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true"))
			    {
				boolean resetDateError = false;
				Date resetDate = DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1);
				if (resetDate != null)
				{
				    Calendar cal = Calendar.getInstance();
				    cal.setTime(resetDate);
				    resetDate = cal.getTime();

				    Calendar today = Calendar.getInstance();
				    today.setTime(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
				    today.add(Calendar.DATE, 18);
				    Date todayDate18 = today.getTime();

				    if (resetDate.after(todayDate18))
				    {
					GetJuvenileCourtDecisionCodesEvent courtDisp = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
					courtDisp.setCode(currentSettingResp.getCourtResult());
					Collection<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = MessageUtil.postRequestListFilter(courtDisp, JuvenileCourtDecisionCodeResponseEvent.class);
					if (crtDecisions != null)
					{
					    Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
					    while (crtDecisionsItr.hasNext())
					    {
						JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
						if (crtDecision != null)
						{
						    if (crtDecision.getAction()!=null && crtDecision.getAction().equalsIgnoreCase("DETAINED"))
						    {
							if (crtDecision.getResetAllowed()!=null && crtDecision.getResetAllowed().equalsIgnoreCase("R"))
							{

							    resetDateError = true;
							    break;

							}
							if (currentSettingResp.getCourtResult()!=null && currentSettingResp.getCourtResult().equalsIgnoreCase("CPO") && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
							{
							    resetDateError = true;
							    break;
							}
						    }

						    if (crtDecision.getAction()!=null && crtDecision.getAction().equalsIgnoreCase("OFF DOCKET/RESET") /*&& (currentSettingResp.getTransferTo() != null && !currentSettingResp.getTransferTo().isEmpty())*/)
						    {
							if (crtDecision.getResetAllowed().equalsIgnoreCase("R"))
							{
							    resetDateError = true;
							    break;
							}
						    }
						}
					    }
					    if (resetDateError)
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Reset Date Outside Of Permissible Limits"));
						saveErrors(aRequest, errors);
						courtHearingForm.setAction("error");
						courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
						courtHearingForm.setPrevAction(courtHearingForm.getAction());
						return aMapping.findForward(UIConstants.FAILURE);
					    }
					}
				    }
				}
			    }

			    /**
			     * Validation added after discussing with Lakshmi.
			     * AS this is handled in detention side as well.
			     */
			    if (currentSettingResp.getCourtResult() != null && !currentSettingResp.getCourtResult().isEmpty() && currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true"))
			    {

				//if (currentSettingResp.getCourtResult().equalsIgnoreCase("TRN"))////add checkbox change
				if (currentSettingResp.getTransferFlag().equalsIgnoreCase("1"))
				{
				    if (currentSettingResp.getTransferTo() == null || currentSettingResp.getTransferTo() != null && currentSettingResp.getTransferTo().isEmpty())
				    {
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Transfer Court Must Be Populated - Correct And Continue"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#transferTo:" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				}

				GetJuvenileCourtDecisionCodesEvent courtDisp = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
				courtDisp.setCode(currentSettingResp.getCourtResult());
				Collection<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = MessageUtil.postRequestListFilter(courtDisp, JuvenileCourtDecisionCodeResponseEvent.class);
				if (crtDecisions != null)
				{
				    Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
				    while (crtDecisionsItr.hasNext())
				    {
					JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
					if (crtDecision != null)
					{
					    if (crtDecision.getResetAllowed() != null && crtDecision.getResetAllowed().equalsIgnoreCase("R") || crtDecision.getResetAllowed().equalsIgnoreCase("Y"))
					    {
						if (currentSettingResp.getActionDate() == null || (currentSettingResp.getActionDate() != null && currentSettingResp.getActionDate().isEmpty()))
						{
						    ActionErrors errors = new ActionErrors();
						    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Reset Date Must Be Entered - Correct And Continue"));
						    saveErrors(aRequest, errors);
						    courtHearingForm.setAction("error");
						    courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
						    courtHearingForm.setPrevAction(courtHearingForm.getAction());
						    return aMapping.findForward(UIConstants.FAILURE);
						}
						
						
						//if action Date is prior to the reset date, throw an error.
						if (currentSettingResp.getActionDate() != null  && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
						{
						    GetJJSCLDetentionByChainAndSeqNumEvent detentionEvt = (GetJJSCLDetentionByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAINANDSEQNUM);
						    detentionEvt.setChainNumber(currentSettingResp.getChainNumber());
						    dispatch.postEvent(detentionEvt);
						    CompositeResponse detentionResp = (CompositeResponse) dispatch.getReply();

						    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(detentionResp, DocketEventResponseEvent.class);
						    //Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

						    Collections.sort((List<DocketEventResponseEvent>) docketResponses, new Comparator<DocketEventResponseEvent>() {
							@Override
							public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
							{
							    if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
								return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
							    else
								return -1;
							}
						    });
						    Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
							//83836
						    while (docketResponsesItr.hasNext())
						    {
							DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
							/**
							  * #83836
							  * d.If the Court ID entered in the Transfer To field, along with the date entered in the Reset To field matches an existing record with the same Reset Date and Court ID, for the same chain number, then display �ANOTHER SETTING CURRENTLY EXISTS FOR THIS COURT AND DATE�.
							  */
							if (docResp.getCourt() != null && docResp.getCourt().equalsIgnoreCase(currentSettingResp.getTransferTo()) 
								&& currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true") 
								&& docResp.getCourtDate() != null && docResp.getCourtDate().equalsIgnoreCase(currentSettingResp.getActionDate()))
							{
							    ActionErrors errors = new ActionErrors();
							    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Another Setting currently Exists For This Court And Date"));
							    saveErrors(aRequest, errors);
							    courtHearingForm.setAction("error");
							    courtHearingForm.setCursorPosition("#transferTo-" + currentSettingResp.getDocketEventIdKey());
							    courtHearingForm.setPrevAction(courtHearingForm.getAction());
							    return aMapping.findForward(UIConstants.FAILURE);
							}
							
							//action date and time (derived value) gets persisted in the court date and court time field on update of court action //84105
							if (docResp != null && docResp.getSeqNum() != null && docResp.getSeqNum().equals("10") && !currentSettingResp.getCourt().equalsIgnoreCase("300"))
							{
							    if (docResp.getHearingType()!=null && docResp.getHearingType().equalsIgnoreCase("PC"))
							    {
								Date pcHearingDate = DateUtil.stringToDate(docResp.getCourtDate(), DateUtil.DATE_FMT_1);
								Date actionDate = DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1);
								if (actionDate.before(pcHearingDate))
								{
								    ActionErrors errors = new ActionErrors();
								    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Reset Date Cannot Precede The Court Date For The Associated Probable Cause Hearing"));
								    saveErrors(aRequest, errors);
								    courtHearingForm.setAction("error");
								    courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
								    courtHearingForm.setPrevAction(courtHearingForm.getAction());
								    return aMapping.findForward(UIConstants.FAILURE);
								}
							    }
							}
						    }
						}
					    }
					}
				    }
				}
			    }

			    /**
			     * 3.1.3.14 Determine Adjudicated Detention calendar
			     * record status Locate all Detention calendar
			     * records for the current juvenile where the
			     * assigned court is the same as the current
			     * setting�s court; and the court date as the same
			     * as the inquiry date. If a located record�s Result
			     * and HearingDecision matches the values in the
			     * screen Result and Disposition fields, the owning
			     * referral has already been adjudicated. The
			     * changes/entries to the current setting will not
			     * be saved. New as of 08/01/18: Display message,
			     * �REFERRAL WAS PREVIOUSLY ADJUDICATED.� If the
			     * screen Disposition is the same as the court ID
			     * used in the search, display �JUVENILE ALREADY HAS
			     * COURT HEARING FOR THIS DATE AND TIME.� Position
			     * the cursor in the Action Date field. Wait for
			     * user response.
			     */

			    if (currentSettingJuvenileNum != null && !currentSettingJuvenileNum.isEmpty())
			    {
				GetJJSCLDetentionByJuvNumEvent jjsdetnCrtEvent = (GetJJSCLDetentionByJuvNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYJUVNUM);
				jjsdetnCrtEvent.setJuvenileNumber(currentSettingJuvenileNum);
				dispatch.postEvent(jjsdetnCrtEvent);
				CompositeResponse clDetnResp = (CompositeResponse) dispatch.getReply();
				List<DocketEventResponseEvent> clDetnDktRespEvts = MessageUtil.compositeToList(clDetnResp, DocketEventResponseEvent.class);

				if (clDetnDktRespEvts != null && !clDetnDktRespEvts.isEmpty())
				{
				    Iterator<DocketEventResponseEvent> clDetnDktRespItr = clDetnDktRespEvts.iterator();
				    while (clDetnDktRespItr.hasNext())
				    {
					DocketEventResponseEvent respEvt = (DocketEventResponseEvent) clDetnDktRespItr.next();
					if (respEvt != null)
					{
					    if (respEvt.getCourt() != null && respEvt.getCourt().equalsIgnoreCase(currentSettingResp.getCourt()))
					    {
						if (respEvt.getCourtDate() != null && respEvt.getCourtDate().equalsIgnoreCase(currentSettingResp.getCourtDate()))
						{
						    //Referral Was Previously Adjudicated - A setting associated to the referral has a result and a disposition.
						    if (respEvt.getCourtResult()!=null && respEvt.getDisposition()!=null && respEvt.getCourtResult().equalsIgnoreCase(currentSettingResp.getCourtResult()) && respEvt.getDisposition().equalsIgnoreCase(currentSettingResp.getDisposition()))
						    {
							ActionErrors errors = new ActionErrors();
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Referral Was Previously Adjudicated"));
							saveErrors(aRequest, errors);
							courtHearingForm.setAction("error");
							courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
							courtHearingForm.setPrevAction(courtHearingForm.getAction());
							return aMapping.findForward(UIConstants.FAILURE);
						    }
						}
					    }
					   /* 83836*//**
					     * If the screen Disposition is the
					     * same as the court ID used in the
					     * search, display �JUVENILE ALREADY
					     * HAS COURT HEARING FOR THIS DATE
					     * AND TIME.� Position the cursor in
					     * the Action Date field. Wait for
					     * user response.
					     *//*
					    if (respEvt.getCourt() != null && respEvt.getCourt().equalsIgnoreCase(currentSettingResp.getTransferTo()))
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.generic", "Juvenile Already Has Court Hearing For This Date And Time"));
						saveErrors(aRequest, errors);
						courtHearingForm.setAction("error");
						courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
						courtHearingForm.setPrevAction(courtHearingForm.getAction());
						return aMapping.findForward(UIConstants.FAILURE);
					    }*/
					}
				    }
				}
			    }
			} //detention validations

			//validations for ancillary and court
			if (currentSettingResp.getDocketType().equalsIgnoreCase("ANCILLARY") || currentSettingResp.getDocketType().equalsIgnoreCase("COURT"))
			{
			    /** 83836
				 * 3.4.6.1 3) Locate the calendar record for the current
				 * setting using its Chain Number and Chain Sequence
				 * Number; determine if the information the user
				 * submitted (Date/Time/Juvenile #) is the same as the
				 * retrieved calendar record. If true, display �JUVENILE
				 * ALREADY HAS COURT HEARING FOR THIS DATE AND TIME.�
				 * Wait for user response.
				 */
				if (currentSettingResp.getActionDate() != null && currentSettingResp.getActionTime() != null && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()) && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
				{
				    if (currentSettingResp.getActionDate().equalsIgnoreCase(currentSettingResp.getCourtDate()) && currentSettingResp.getActionTime().equalsIgnoreCase(currentSettingResp.getCourtTime()))
				    {
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile Already Has Court Hearing For This Date And Time"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				}
				
			    //Legacy process}: User entered �TRN� in Result field and a valid referee court in the Disposition field. Populated File Date with reset date
			    //{JIMS2 process}: User will supply �TRN� for Result field, new court in �Transfer To� field.  Action date is also required.

			    if ((currentSettingResp.getCourtResult() != null && !currentSettingResp.getCourtResult().isEmpty()))
			    {
				//if (currentSettingResp.getCourtResult().equalsIgnoreCase("TRN"))//add checkbox change
				if (currentSettingResp.getTransferFlag().equalsIgnoreCase("1"))
				{
				    if (currentSettingResp.getTransferTo() == null || (currentSettingResp.getTransferTo() != null && currentSettingResp.getTransferTo().isEmpty()))
				    {
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Enter A Valid Court (313, 314, 315) In The Transfer To Field"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#transferTo-" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				    else
				    {
					String validCourts[] = {"313", "314", "315" };
					//generic case:
					if (!Arrays.asList(validCourts).contains(currentSettingResp.getTransferTo()))
					{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Court Id. Please Correct and Retry"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#transferTo-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				}
			    }

			    /**
			     * 3.4.6.3 1)a Activity: Validate Ancillary or Court
			     * calendar record entry/update {tested with records
			     * from 315/05-06-2016; 314/05-06-2015} 1) If screen
			     * Result and/or Disposition, and the Action Date
			     * are populated. And the screen Result�s and/or
			     * DispositionOption = �R� (reset) and � a) If
			     * screen Action Time is not populated, displays
			     * �FILE TIME MISSING, PLEASE CORRECT AND RETRY.�
			     * Wait for user response
			     */
			    if ((!currentSettingResp.getCourtResult().isEmpty() || !currentSettingResp.getDisposition().isEmpty()) && currentSettingResp.getActionDate()!=null && !currentSettingResp.getActionDate().isEmpty() && currentSettingResp.getActionTime().isEmpty())
			    {
				GetJuvenileDispositionCodeEvent courtDisp = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
				courtDisp.setCode(currentSettingResp.getCourtResult());
				CompositeResponse dispCompResp = postRequestEvent(courtDisp);
				Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
				if (juvDisp != null)
				{
				    Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvDisp.iterator();
				    if (juvCodeIter.hasNext())
				    {
					JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
					if (dispResp != null && dispResp.getOptionCode()!=null && dispResp.getOptionCode().equalsIgnoreCase("R"))
					{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "File Time Missing, Correct and Retry"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				}
			    }

			    /**
			     * * 3.4.6.3 - original docket comparison.
			     */

			    if (originalSettingResp != null)
			    {
				/**
				 * 3.4.6.5 If the Action Date and Action Time
				 * entered are the same as the current setting�s
				 * information (courtdate and time)and the
				 * Transfer To field contains the same value as
				 * the current setting�s CourtID, user is
				 * attempting to reset record to the exact same
				 * date and time, display: 'JUVENILE ALREADY HAS
				 * COURT HEARING FOR THIS 'DATE AND TIME.
				 * Position the cursor in Action Date field.
				 * Wait for user response.
				 */

				if (originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().isEmpty()&& originalSettingResp.getActionTime() != null && !originalSettingResp.getActionTime().isEmpty() && originalSettingResp.getTransferTo() != null)
				{
				    if (currentSettingResp.getActionDate() != null && currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getCourtDate())
					    && currentSettingResp.getActionTime() != null && currentSettingResp.getActionTime().equalsIgnoreCase(originalSettingResp.getCourtTime()) 
					    && currentSettingResp.getTransferTo() != null && currentSettingResp.getTransferTo().equalsIgnoreCase(originalSettingResp.getCourt()))
				    {
					courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile Already Has Court Hearing For This Date And Time"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				}

				/**
				 * 3.4.6.3 2) 08/10/18: If Result AND Action
				 * Date/Time fields were originally populated
				 * during retrieval; and, user has removed the
				 * original value in the Result field and,
				 * Result field has not been repopulated,
				 * display �RESULT VALUE MISSING, CORRECT AND
				 * RETRY.� Posititon cursor in the Result field.
				 * Wait for user response.
				 */
				if (originalSettingResp.getCourtResult() != null && !originalSettingResp.getCourtResult().isEmpty())
				{
				    if (originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().isEmpty() && originalSettingResp.getActionTime() != null && !originalSettingResp.getActionTime().isEmpty() && (originalSettingResp.getDisposition() == null || (originalSettingResp.getDisposition() != null && originalSettingResp.getDisposition().isEmpty())))
				    {
					if (currentSettingResp.getCourtResult() == null || (currentSettingResp.getCourtResult() != null && currentSettingResp.getCourtResult().isEmpty()) && (currentSettingResp.getDisposition() == null || (currentSettingResp.getDisposition() != null && currentSettingResp.getDisposition().isEmpty())))
					{
					    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Result Value Missing, Correct And Retry"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }

				    /**
				     * 5) 08/09/18: If Result, Disposition and
				     * Action Date fields were populated during
				     * retrieval; and, user removed the
				     * retrieved Result value and has not
				     * re-populated the field; but, the original
				     * disposition continues to populate
				     * Disposition field, display �DISPOSITION
				     * VALUE MUST BE BLANK WHEN RESULT AND DATE
				     * ARE BLANK.� Position the cursor in
				     * Disposition field. Wait for user
				     * response.
				     */
				    if (originalSettingResp.getDisposition() != null && !originalSettingResp.getDisposition().isEmpty() && originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().isEmpty())
				    {
					if (currentSettingResp.getCourtResult() == null || (currentSettingResp.getCourtResult() != null && currentSettingResp.getCourtResult().isEmpty()) && currentSettingResp.getDisposition()!=null && currentSettingResp.getDisposition().equalsIgnoreCase(originalSettingResp.getDisposition()) && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty())
					{
					    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Disposition Value Must be Blank When Result And Date Are Blank"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
					/**
					 * 08/16/18: If Result, Disposition and
					 * Action Date fields were populated
					 * during retrieval, and user has
					 * changed Result to �OFF� and removed
					 * the Disposition and Action Date/Time
					 * fields, system executes logic to
					 * delete the setting. If setting is not
					 * last record in chain and, display:
					 * �ONLY LAST SETTING MAY BE DELETED �
					 * SELECT SUBMIT TO CONTINUE.�
					 * [313/07-21-2016 (201602660J)]
					 */
					if (currentSettingResp.getCourtResult()!=null && originalSettingResp.getCourtResult()!=null && !originalSettingResp.getCourtResult().equalsIgnoreCase(currentSettingResp.getCourtResult()) && currentSettingResp.getCourtResult().equalsIgnoreCase("OFF"))
					{
					    if (currentSettingResp.getDisposition() == null || (currentSettingResp.getDisposition() != null && currentSettingResp.getDisposition().isEmpty()) && (currentSettingResp.getActionDate() == null || (currentSettingResp.getActionDate() != null && currentSettingResp.getActionDate().isEmpty())) || (currentSettingResp.getActionTime() == null || (currentSettingResp.getActionTime() != null && currentSettingResp.getActionTime().isEmpty())))
					    {
						if (currentSettingResp.getChainNumber() != null && currentSettingResp.getDocketType().equalsIgnoreCase("COURT"))
						{

						    GetJJSCLCourtByChainAndSeqNumEvent courtEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
						    courtEvt.setChainNumber(currentSettingResp.getChainNumber());
						    dispatch.postEvent(courtEvt);
						    CompositeResponse courtResp = (CompositeResponse) dispatch.getReply();
						    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtResp, DocketEventResponseEvent.class);

						    Collections.sort((List<DocketEventResponseEvent>) docketResponses, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
							@Override
							public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
							{
							    if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
								return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
							    else
								return -1;
							}
						    }));
						    Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

						    while (docketResponsesItr.hasNext())
						    {
							DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
							//action date and time (derived value) gets persisted in the court date and court time field on update of court action
							if (docResp != null && !docResp.getSeqNum().equals(Integer.parseInt(currentSettingResp.getSeqNum()) + 10))
							{
							    ActionErrors errors = new ActionErrors();
							    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Only Last Setting May Be Deleted - Select Submit To Continue"));
							    saveErrors(aRequest, errors);
							    courtHearingForm.setAction("error");
							    courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
							    courtHearingForm.setPrevAction(courtHearingForm.getAction());
							    return aMapping.findForward(UIConstants.FAILURE);
							}
						    }//while
						}

						if (currentSettingResp.getChainNumber() != null && currentSettingResp.getDocketType().equalsIgnoreCase("ANCILLARY"))
						{
						    GetJJSCLAncillaryCourtByChainAndSeqNumEvent ancillaryEvt = (GetJJSCLAncillaryCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYCHAINANDSEQNUM);
						    ancillaryEvt.setChainNumber(currentSettingResp.getChainNumber());
						    dispatch.postEvent(ancillaryEvt);
						    CompositeResponse ancillaryResp = (CompositeResponse) dispatch.getReply();
						    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(ancillaryResp, DocketEventResponseEvent.class);

						    Collections.sort((List<DocketEventResponseEvent>) docketResponses, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
							@Override
							public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
							{
							    if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
								return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
							    else
								return -1;
							}
						    }));

						    Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
						    while (docketResponsesItr.hasNext())
						    {
							DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
							if (docResp != null && !docResp.getSeqNum().equals(Integer.parseInt(currentSettingResp.getSeqNum()) + 10))
							{
							    ActionErrors errors = new ActionErrors();
							    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Only Last Setting May Be Deleted - Select Submit To Continue"));
							    saveErrors(aRequest, errors);
							    courtHearingForm.setAction("error");
							    courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
							    courtHearingForm.setPrevAction(courtHearingForm.getAction());
							    return aMapping.findForward(UIConstants.FAILURE);
							}
						    }//while
						}
					    }
					}
				    } //disposition,action date,results check

				    /**
				     * 14)08/09/18; If Result and Disposition
				     * were originally populated during
				     * retrieval�and user has removed only the
				     * Disposition value and has not
				     * re-populated the screen Disposition
				     * (tested with 201401872J, REV and CCP),
				     * display �INAPPROPRIATE RESULT;
				     * DISPOSITION AND ACTION DATE ARE BLANK.�
				     * Position cursor in screen Results field.
				     * Wait for user response.
				     */

				    if (originalSettingResp.getDisposition() != null && !originalSettingResp.getDisposition().isEmpty() && originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().isEmpty())
				    {
					if (currentSettingResp.getDisposition() == null || (currentSettingResp.getDisposition() != null && currentSettingResp.getDisposition().isEmpty()))
					{
					    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Result. Disposition And Action Date Are Blank"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				} //original court result not empty check

				/**
				 * 8) If the Disposition and Action Date were
				 * originally populated during retrieval, and
				 * screen Result was not and has not been
				 * populated, and user has removed original
				 * Disposition, display �DISPOSITION VALUE
				 * MISSING.� Position cursor in the Disposition
				 * field. Wait for user response.
				 */
				if (originalSettingResp.getCourtResult() == null || (originalSettingResp.getCourtResult() != null && originalSettingResp.getCourtResult().isEmpty()))
				{
				    if (originalSettingResp.getDisposition() != null && !originalSettingResp.getDisposition().isEmpty() && originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().isEmpty())
				    {
					if (currentSettingResp.getDisposition() == null || (currentSettingResp.getDisposition() != null && currentSettingResp.getDisposition().isEmpty()))
					{
					    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Disposition Value Missing"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				} //end if 

				/**
				 * 9) 08/10/2018: If user has entered �TRN� as
				 * Result {not populated during retrieval} and
				 * Action Date was not populated with retrieval
				 * and user has not populated Action Date,
				 * display �DATE VALUE MISSING, PLEASE CORRECT
				 * AND RETRY.� Wait for user response.
				 */
				if (originalSettingResp.getCourtResult() == null || (originalSettingResp.getCourtResult() != null && originalSettingResp.getCourtResult().isEmpty()) && originalSettingResp.getActionDate() == null || (originalSettingResp.getActionDate() != null && originalSettingResp.getActionDate().isEmpty()))
				{
				    if (currentSettingResp.getCourtResult() != null && currentSettingResp.getTransferFlag().equalsIgnoreCase("1") && currentSettingResp.getActionDate()!=null && currentSettingResp.getActionDate().isEmpty())//add checkbox change
				    {
					courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Date Value Missing, Please Correct And Retry"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				}

				/**
				 * If the Action date occurs before the inquiry
				 * Court Date AND if the screen Result is not an
				 * Administrative Reset (A/R) or a Transfer
				 * (TRN), display: DATE CANNOT BE LESS THAN
				 * {inquiry date} IF RESULT IS NOT
				 * ADMINISTRATIVE RESET (A/R) OR TRANSFER (TRN).
				 * Position the cursor in Action Date field.
				 * Wait for user response. NOTE: Only a judge�s
				 * reset (A/R or TRN) request can be backdated.
				 * Attorney�s reset requests cannot be
				 * backdated.
				 */
				if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && currentSettingResp.getCourtDate() != null && !currentSettingResp.getCourtDate().isEmpty())
				{
				    if (DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1).before(DateUtil.stringToDate(currentSettingResp.getCourtDate(), DateUtil.DATE_FMT_1)))
				    {
					if (currentSettingResp.getCourtResult() != null && (!currentSettingResp.getCourtResult().equalsIgnoreCase("A/R") && !currentSettingResp.getTransferFlag().equalsIgnoreCase("1")))//add checkbox change
					{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Date Cannot Be Less Than " + currentSettingResp.getCourtDate() + ". If Result Is Not Administrative(A/R) Or Transfer(TRN)"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				}

				/**
				 * 10) 08/10/2018: If user has entered �TRN� as
				 * Result {not populated during retrieval} and
				 * Action Time was not populated with retrieval
				 * and user has not populaated Action Time,
				 * display �FILE TIME MISSING, PLEASE CORRECT
				 * AND RETRY.� Position curson in the Action
				 * Time field. Wait for user response.
				 */
				if (originalSettingResp.getCourtResult() == null || (originalSettingResp.getCourtResult() != null && originalSettingResp.getCourtResult().isEmpty()) && originalSettingResp.getActionTime() == null || (originalSettingResp.getActionTime() != null && originalSettingResp.getActionTime().isEmpty()))
				{
				    if (currentSettingResp.getCourtResult() != null && currentSettingResp.getTransferFlag().equalsIgnoreCase("1"))//add checkbox change
				    {
					if (currentSettingResp.getActionTime() != null && currentSettingResp.getActionTime().isEmpty())
					{
					    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "File Time Missing, Please Correct And Retry"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
					/**
					 * 11) 08/10/2018: If screen Result
					 * contains =�TRN� and the Action Date
					 * and Time are populated, and
					 * TransferTo/Transfer Court is a valid
					 * juvenile Referee court, display �ONLY
					 * A DETENTION HEARING CAN BE
					 * TRANSFERRED TO REFEREE COURT.�
					 * Position cursor in
					 * TransferTo/Transfer Court field. Wait
					 * for user response.
					 */
					if (currentSettingResp.getActionDate()!=null && currentSettingResp.getActionTime()!=null && !currentSettingResp.getActionTime().isEmpty() && !currentSettingResp.getActionDate().isEmpty() && currentSettingResp.getTransferTo() != null && !currentSettingResp.getTransferTo().isEmpty())
					{
					    String refereeCourts[] = { "001", "002" };
					    if (Arrays.asList(refereeCourts).contains(currentSettingResp.getTransferTo()))
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Only A Detention Hearing Can Be Transferred To Referee Court."));
						saveErrors(aRequest, errors);
						courtHearingForm.setAction("error");
						courtHearingForm.setCursorPosition("#transferTo-" + currentSettingResp.getDocketEventIdKey());
						courtHearingForm.setPrevAction(courtHearingForm.getAction());
						return aMapping.findForward(UIConstants.FAILURE);
					    }
					}
				    }
				}

				/**
				 * 12) A) User has populated screen Result =
				 * �TRN� {Result was not populated during
				 * retrieval} and TransferTo/Transfer Court is
				 * populated with a valid juvenile court and has
				 * Disposition field is populated with a valid
				 * hearing decision, display �INVALID
				 * DISPOSITION FOR TRANSFER, PLEASE CORRECT AND
				 * RETRY� Position cursor in Disposition field.
				 * Wait for user response. {Scenario only in
				 * JIMS2; modified legacy process of identifying
				 * Transfers}
				 */
				String validJuvenileCourts[] = { "313", "314", "315", "300" };
				if (originalSettingResp.getCourtResult() != null && currentSettingResp.getCourtResult() != null && (!originalSettingResp.getCourtResult().equalsIgnoreCase("TRN") && currentSettingResp.getTransferFlag().equalsIgnoreCase("1")))//add checkbox change and need clarification from Carla
				{
				    if (currentSettingResp.getTransferTo() != null && !currentSettingResp.getTransferTo().isEmpty() && Arrays.asList(validJuvenileCourts).contains(currentSettingResp.getTransferTo()) && currentSettingResp.getDisposition() != null && !currentSettingResp.getDisposition().isEmpty())
				    {
					courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Disposition For Transfer, Please Correct and Retry"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				}

				/**
				 * 12th B )- Requirement changed while
				 * implementing. Check user-story 59852
				 */
				if (currentSettingResp.getCourtResult() != null && !currentSettingResp.getCourtResult().isEmpty() && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && currentSettingResp.getDisposition() != null && !currentSettingResp.getDisposition().isEmpty())
				{
				    // court Decision Code table.
				    GetJuvenileDispositionCodeEvent dispositionEvt = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
				    dispositionEvt.setCode(currentSettingResp.getDisposition());
				    CompositeResponse dispCompResp = postRequestEvent(dispositionEvt);
				    Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
				    if (juvDisp != null)
				    {
					Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvDisp.iterator();
					if (juvCodeIter.hasNext())
					{
					    JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
					    /**
					     * 1. If the Option of the screen
					     * Disposition is �A� or, �R�,
					     * display: INAPPROPRIATE
					     * DISPOSITION, OPTION CANNOT BE 'A'
					     * or 'R�. Position cursor in
					     * Disposition field. Wait for user
					     * response.
					     */
					    if (dispResp != null && dispResp.getOptionCode()!=null && (dispResp.getOptionCode().equalsIgnoreCase("A") || dispResp.getOptionCode().equalsIgnoreCase("R")))
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Disposition, Option Cannot Be 'A' or 'R'"));
						saveErrors(aRequest, errors);
						courtHearingForm.setAction("error");
						courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
						courtHearingForm.setPrevAction(courtHearingForm.getAction());
						return aMapping.findForward(UIConstants.FAILURE);
					    }

					    /*3.4.6.3
					     *  2.	If the current setting is a Court calendar record, AND the Category associated to the value in the screen Disposition field is(U.S. 59852)  not  �N� or �P� or �B� or �C� or �0,� display:  INAPPROPRIATE DISPOSITION, CATEGORY MUST BE �N�, �P', 'B', 'C' OR '0�. Position cursor in Disposition field.  Wait for user response.  
					     {313  09202016 and Petiton# 201603979J)*/

					    if (currentSettingResp.getDocketType().equalsIgnoreCase("COURT") && dispResp != null && dispResp.getCategoryCode() != null 
						    && (!dispResp.getCategoryCode().equalsIgnoreCase("N") && !dispResp.getCategoryCode().equalsIgnoreCase("P") 
							    && !dispResp.getCategoryCode().equalsIgnoreCase("B") && !dispResp.getCategoryCode().equalsIgnoreCase("C") && !dispResp.getCategoryCode().equalsIgnoreCase("0")))
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Disposition, Category Must Be 'N','P','B','C' or '0'"));
						saveErrors(aRequest, errors);
						courtHearingForm.setAction("error");
						courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
						courtHearingForm.setPrevAction(courtHearingForm.getAction());
						return aMapping.findForward(UIConstants.FAILURE);
					    }

					    /**
					     * 12 C) 3. If the current setting
					     * is an Ancillary calendar record
					     * AND the Category associated to
					     * the value in the screen
					     * Disposition field is not �A� or
					     * �B� or �C� or �0,� display:
					     * INAPPROPRIATE DISPOSITION,
					     * CATEGORY MUST BE 'A', 'B', 'C' OR
					     * '0'. Position cursor in
					     * Disposition field. Wait for user
					     * response. NOTE: Entries on the
					     * COURT DECISION code table MUST
					     * have a value in the CATEGORY;
					     * blank is no longer possible.
					     */
					    if (currentSettingResp.getDocketType().equalsIgnoreCase("ANCILLARY") && dispResp != null 
						    && (dispResp.getCategoryCode()!=null && !dispResp.getCategoryCode().equalsIgnoreCase("A") && !dispResp.getCategoryCode().equalsIgnoreCase("B") 
							    && !dispResp.getCategoryCode().equalsIgnoreCase("C") && !dispResp.getCategoryCode().equalsIgnoreCase("0")))
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Disposition, Category Must Be 'A', 'B', 'C' Or '0'"));
						saveErrors(aRequest, errors);
						courtHearingForm.setAction("error");
						courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
						courtHearingForm.setPrevAction(courtHearingForm.getAction());
						return aMapping.findForward(UIConstants.FAILURE);
					    }
					}
				    }
				}

				/**
				 * 3.4.6.3 13)08/10/18: If screen Action
				 * Date/Time and Result were populated during
				 * original retrieval, but user has replaced
				 * original Result, locate the Option associated
				 * to the screen Result field value AND�
				 * a.08/10/18�If the Option is not �A� or �R,�
				 * display �INAPPROPRIATE RESULT, MUST HAVE
				 * OPTION VALUE OF, ''A'', ��R.� Wait for user
				 * response. NOTE: Entries on the COURT DECISION
				 * code table MUST have an OPTION value; blank
				 * is no longer possible.
				 */
				if (originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().isEmpty() && originalSettingResp.getActionTime() != null && !originalSettingResp.getActionTime().isEmpty() && originalSettingResp.getCourtResult() != null)
				{
				    //bug #81255
				    if (currentSettingResp.getCourtResult()!=null && !currentSettingResp.getCourtResult().isEmpty() && !originalSettingResp.getCourtResult().equalsIgnoreCase(currentSettingResp.getCourtResult()))
				    {

					/**
					 * 3) If Result AND Action Date/Time
					 * fields were populated during
					 * retrieval; Result has been changed to
					 * �OFF�; and {IF $VERIFY (%RESET.IND
					 * (%LINE.NUM), �NAB�*} user has NOT
					 * removed/erased the retrieved value
					 * from Action Date {Legacy code: around
					 * line 1179} �display �INAPPROPRIATE
					 * RESULT CODE.� Place cursor in the
					 * screen RESULT field. Wait for user
					 * response.
					 */
					if (currentSettingResp.getCourtResult()!=null && currentSettingResp.getCourtResult().equalsIgnoreCase("OFF") && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty())
					{
					    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Result Code"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}

					GetJuvenileDispositionCodeEvent resultEvt = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
					resultEvt.setCode(currentSettingResp.getCourtResult());
					CompositeResponse resultCompResp = postRequestEvent(resultEvt);
					Collection<JuvenileDispositionCodeResponseEvent> juvResult = MessageUtil.compositeToCollection(resultCompResp, JuvenileDispositionCodeResponseEvent.class);
					if (juvResult != null)
					{
					    Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvResult.iterator();
					    if (juvCodeIter.hasNext())
					    {
						JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
						/**
						 * 3.6.4.3 13)a If the Option of
						 * the screen Disposition is �A�
						 * or, �R�, display:
						 * INAPPROPRIATE DISPOSITION,
						 * OPTION CANNOT BE 'A' or 'R�.
						 * Position cursor in
						 * Disposition field. Wait for
						 * user response.
						 */
						if (dispResp != null && dispResp.getOptionCode()!=null && !dispResp.getOptionCode().equalsIgnoreCase("A") && !dispResp.getOptionCode().equalsIgnoreCase("R"))
						{
						    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
						    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
						    ActionErrors errors = new ActionErrors();
						    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Result, Must Have Option Value Of 'A','R'"));
						    saveErrors(aRequest, errors);
						    courtHearingForm.setAction("error");
						    courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
						    courtHearingForm.setPrevAction(courtHearingForm.getAction());
						    return aMapping.findForward(UIConstants.FAILURE);
						}

						/**
						 * 3.4.6.3 13) b.08/14/18�COURT
						 * ONLY�If the current setting
						 * is a Court calendar record,
						 * result�s Option = A, or R,
						 * AND the Category is not �N�
						 * or �P� or �B� or �C� or �0,�
						 * display �INAPPROPRIATE
						 * RESULT, CATEGORY MUST BE �N�,
						 * ''P'', ''B'', ''C'' OR ''0.''
						 * Position cursor in screen
						 * Results field. Wait for user
						 * response. NOTE: Entries on
						 * the COURT DECISION code table
						 * MUST have a value in the
						 * CATEGORY; blank is no longer
						 * possible. Created decision
						 * code �ZZZ� to test.
						 */
						if (currentSettingResp.getDocketType().equalsIgnoreCase("COURT") && dispResp != null && dispResp.getOptionCode()!=null && (dispResp.getOptionCode().equalsIgnoreCase("A") || dispResp.getOptionCode().equalsIgnoreCase("R")))
						{
						    if (dispResp.getCategoryCode()!=null && !dispResp.getCategoryCode().equalsIgnoreCase("N") 
							    && !dispResp.getCategoryCode().equalsIgnoreCase("P")
							    && !dispResp.getCategoryCode().equalsIgnoreCase("B") 
							    && !dispResp.getCategoryCode().equalsIgnoreCase("C") && !dispResp.getCategoryCode().equalsIgnoreCase("0"))
						    {
							courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
							courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
							ActionErrors errors = new ActionErrors();
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Result, Category Must Be 'N','P','B','C' or '0'"));
							saveErrors(aRequest, errors);
							courtHearingForm.setAction("error");
							courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
							courtHearingForm.setPrevAction(courtHearingForm.getAction());
							return aMapping.findForward(UIConstants.FAILURE);
						    }
						}

						/**
						 * 3.4.6.3 13)
						 * C.08/10/18�ANCILLARY ONLY: If
						 * the current setting is an
						 * Ancillary calendar record,
						 * result�s Option = A, R or O,
						 * AND the Category is not ��A�
						 * or �B� or �C� or �0,� display
						 * �INAPPROPRIATE RESULT,
						 * CATEGORY MUST BE, ''A'',
						 * ''B'', ''C'' OR ''0.''
						 * Position cursor in screen
						 * Results field. Wait for user
						 * response. NOTE: Entries on
						 * the COURT DECISION code table
						 * MUST have an OPTION value;
						 * blank is no longer possible.
						 */
						if (currentSettingResp.getDocketType().equalsIgnoreCase("ANCILLARY") && dispResp != null && dispResp.getOptionCode()!=null && 
							(dispResp.getOptionCode().equalsIgnoreCase("A") || dispResp.getOptionCode().equalsIgnoreCase("R") || dispResp.getOptionCode().equalsIgnoreCase("O")))
						{
						    if (dispResp.getCategoryCode()!=null && !dispResp.getCategoryCode().equalsIgnoreCase("A") 
							    && !dispResp.getCategoryCode().equalsIgnoreCase("B") 
							    && !dispResp.getCategoryCode().equalsIgnoreCase("C") 
							    && !dispResp.getCategoryCode().equalsIgnoreCase("0"))
						    {
							courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
							courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
							ActionErrors errors = new ActionErrors();
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Result, Category Must Be ''A'', ''B'', ''C'' OR ''0''"));
							saveErrors(aRequest, errors);
							courtHearingForm.setAction("error");
							courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
							courtHearingForm.setPrevAction(courtHearingForm.getAction());
							return aMapping.findForward(UIConstants.FAILURE);
						    }
						}// end if
					    }
					} //disp if
				    }// if
				} //action date,time, result not null check (13th)

				/**
				 * 15) COURT ONLY: If the Option for the screen
				 * Disposition = �F� AND the Option for the
				 * result is not �A�: Locate the calendar record
				 * for the setting by ChainNumber and
				 * ChainSequenceNumber. Retrieve the Petition
				 * Allegation, referral number and juvenile
				 * number associated to the calendar record. If
				 * the Petition Allegation is not �CHCUST
				 * (Change of Custody)", Locate the Referral
				 * record associated, by juvenile number and
				 * referral number, to the calendar record.
				 * Retrieve the Decision Type (CourtResultType)
				 * from the referral record. If the Decision
				 * type (CourtResultType) is not �A,� display
				 * �NO ADJUDICATION FOUND.� Wait for user
				 * response.
				 */
				if (currentSettingResp.getDocketType().equalsIgnoreCase("COURT"))
				{
				    /**
				     * 4) 09/06/18: If there is not referral
				     * record for the the setting�s identified
				     * referral, display �REFERRAL (referral #)
				     * NOT FOUND FOR THIS JUVENILE.�
				     */
				    if (currentSettingJuvenileNum != null && currentSettingResp.getReferralNum() != null)
				    {
					//String juvenileNum = currentSettingResp.getJuvenileNumber().substring(1, currentSettingResp.getJuvenileNumber().length());
					boolean doesReferralExists = JuvenileDistrictCourtHelper.doesReferralExists(currentSettingResp.getReferralNum(), currentSettingJuvenileNum);
					//referral not exist and user modifies any of those fields.
					if (!doesReferralExists)
					{
					    if ((originalSettingResp.getCourtResult() == null && currentSettingResp.getCourtResult()!=null && !currentSettingResp.getCourtResult().isEmpty()) 
						    || (originalSettingResp.getCourtResult() != null && !originalSettingResp.getCourtResult().equalsIgnoreCase(currentSettingResp.getCourtResult())) 
						    || ((originalSettingResp.getDisposition() == null && currentSettingResp.getDisposition()!=null && !currentSettingResp.getDisposition().isEmpty()) 
						    || (originalSettingResp.getDisposition() != null && !originalSettingResp.getDisposition().equalsIgnoreCase(currentSettingResp.getDisposition()))) 
						    || ((originalSettingResp.getTransferTo() == null && currentSettingResp.getTransferTo()!=null && !currentSettingResp.getTransferTo().isEmpty()) 
						    || (originalSettingResp.getTransferTo() != null && !originalSettingResp.getTransferTo().equalsIgnoreCase(currentSettingResp.getTransferTo()))) 
						    || ((originalSettingResp.getActionDate() == null && currentSettingResp.getActionDate()!=null && !currentSettingResp.getActionDate().isEmpty())
						    || (originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().equalsIgnoreCase(currentSettingResp.getActionDate()))) 
						    || ((originalSettingResp.getActionTime() == null && currentSettingResp.getActionTime()!=null && !currentSettingResp.getActionTime().isEmpty()) 
						    || (originalSettingResp.getActionTime() != null && !originalSettingResp.getActionTime().equalsIgnoreCase(currentSettingResp.getActionTime()))) 
						    || ((originalSettingResp.getHearingType() == null && currentSettingResp.getHearingType()!=null && !currentSettingResp.getHearingType().isEmpty()) 
						    || (originalSettingResp.getHearingType() != null && !originalSettingResp.getHearingType().equalsIgnoreCase(currentSettingResp.getHearingType()))))
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Referral (" + currentSettingResp.getReferralNum() + ") Not Found For This Juvenile"));
						saveErrors(aRequest, errors);
						courtHearingForm.setAction("error");
						courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
						courtHearingForm.setPrevAction(courtHearingForm.getAction());
						return aMapping.findForward(UIConstants.FAILURE);
					    }
					}
				    }

				    if (currentSettingResp.getCourtResult()!=null && !currentSettingResp.getCourtResult().isEmpty() && currentSettingResp.getDisposition()!=null && !currentSettingResp.getDisposition().isEmpty())
				    {
					JuvenileProfileReferralListResponseEvent profileRefResp = null;
					//court Result
					GetJuvenileDispositionCodeEvent resultEvt = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
					resultEvt.setCode(currentSettingResp.getCourtResult());
					CompositeResponse resultCompResp = postRequestEvent(resultEvt);
					Collection<JuvenileDispositionCodeResponseEvent> juvResult = MessageUtil.compositeToCollection(resultCompResp, JuvenileDispositionCodeResponseEvent.class);

					//disposition
					GetJuvenileDispositionCodeEvent dispositionEvt = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
					dispositionEvt.setCode(currentSettingResp.getDisposition());
					CompositeResponse dispCompResp = postRequestEvent(dispositionEvt);
					Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
					if (juvDisp != null && juvResult != null && juvResult.size() == juvDisp.size())
					{
					    Iterator<JuvenileDispositionCodeResponseEvent> juvResultIter = juvResult.iterator();
					    Iterator<JuvenileDispositionCodeResponseEvent> dispositionsItr = juvDisp.iterator();
					    if (juvResultIter.hasNext())
					    {
						JuvenileDispositionCodeResponseEvent courtResult = juvResultIter.next();
						JuvenileDispositionCodeResponseEvent disp = dispositionsItr.next();

						/*15)	3.4.6.3 COURT ONLY: If the Option for the screen Disposition = �F� AND the Option for the result is not �A�: 
						    Locate the calendar record for the setting by ChainNumber and ChainSequenceNumber.  
						    Retrieve the Petition Allegation, referral number and juvenile number associated to the calendar record.

						    If the Petition Allegation is not �CHCUST (Change of Custody)", 	
						    Locate the Referral record associated, by juvenile number and referralnumber, to the calendar record.
						    Retrieve the Decision Type (CourtResultType) from the referral record. 
						    If the Decision type (CourtResultType) is not �A,� display �NO ADJUDICATION FOUND.�  Wait for user response.*/

						//if (disp != null && disp.getOptionCode()!=null  && (disp.getOptionCode().equalsIgnoreCase("F") && courtResult!=null && courtResult.getOptionCode()!=null &&  !courtResult.getOptionCode().equalsIgnoreCase("A")))
						if (disp != null && disp.getOptionCode()!=null  && (disp.getOptionCode().equalsIgnoreCase("F") && courtResult!=null && courtResult.getOptionCode()!=null && !courtResult.getOptionCode().equalsIgnoreCase("A") &&  !courtResult.getCodeNum().equalsIgnoreCase("99")))
						{
						    GetJJSCLCourtByChainAndSeqNumEvent courtEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
						    courtEvt.setChainNumber(currentSettingResp.getChainNumber());
						    courtEvt.setSeqNumber(currentSettingResp.getSeqNum());
						    dispatch.postEvent(courtEvt);
						    CompositeResponse courtResp = (CompositeResponse) dispatch.getReply();
						    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtResp, DocketEventResponseEvent.class);
						    Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

						    while (docketResponsesItr.hasNext())
						    {
							DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
							if (docResp.getJuvenileNumber() != null && docResp.getReferralNum() != null)
							{
							    // Get petition
							    GetJJSPetitionsEvent petitionEvent = new GetJJSPetitionsEvent();
							    petitionEvent.setJuvenileNum(docResp.getJuvenileNumber());
							    petitionEvent.setReferralNum(docResp.getReferralNum());
							    Iterator<JJSPetition> petitionIter = JJSPetition.findAll(petitionEvent);
							    if (petitionIter != null && petitionIter.hasNext())
							    {
								JJSPetition petition = petitionIter.next();
								if (petition != null)
								{
								    if (petition.getOffenseCodeId() != null && !petition.getOffenseCodeId().equalsIgnoreCase("CHCUST"))
								    {
									profileRefResp = JuvenileFacilityHelper.getJuvReferralDetails(docResp.getJuvenileNumber(), docResp.getReferralNum());
									if (profileRefResp != null && profileRefResp.getCourtResultDisposition() != null)
									{
									    JuvenileDispositionCodeResponseEvent juvdisp = profileRefResp.getCourtResultDisposition();
									    if (juvdisp != null)
									    {
										//if (juvdisp.getOptionCode() != null && !juvdisp.getOptionCode().get.equalsIgnoreCase("A"))
										if (juvdisp.getCodeNum() != null && !juvdisp.getCodeNum().equalsIgnoreCase("99"))
										{
										    ActionErrors errors = new ActionErrors();
										    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No Adjudication Found"));
										    saveErrors(aRequest, errors);
										    courtHearingForm.setAction("error");
										    courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
										    courtHearingForm.setPrevAction(courtHearingForm.getAction());
										    return aMapping.findForward(UIConstants.FAILURE);
										}
									    }
									}
								    }
								}
							    }
							} //juv num check
						    }// end of while
						}//if disp check
					    }
					}
				    }
				}// court only check 15 th and 16th (a)
			    } //original docket null check 

			    /********************************************** Validation Complete ***************************************************************************************/
			    //    boolean isNewCourtRecordCreated = false;
			    //    boolean isNewAncillaryRecordCreated = false;

			    //    boolean isSubsequentCourtRecordAvail = false;
			    //    boolean isSubsequentAncillaryRecordAvail = false;

			    boolean isResetForCourt = false;
			    boolean isResetForAncillary = false;

			    /**
			     * NOTE: In legacy, JUVENILE_JJS_DETENTION_HEARING.
			     * AlternateSettingIndicator is not populated as
			     * part of any system-initiated process; it can be
			     * populated only through a Correction screen. If
			     * the Option value = �R� for a Result or
			     * Disposition, a new calendar record (of the same
			     * record type) must be created. All Calendar record
			     * types: Chain number is the same as Chain number
			     * in current setting. Chain Sequence number: find
			     * the last setting record in the current chain, Add
			     * +10 to the located setting�s Chain Sequence
			     * Number. Resulting value becomes new settings
			     * Chain Sequence number. ResetHearingType = current
			     * setting�s PetitionType if user has not supplied a
			     * value. LastChangeDate/Time = current system
			     * date/time LastChangeUser = current session user
			     * HearingCategory: Category for Disposition always
			     * takes precedence over the category associated to
			     * a Result value.
			     */
			    GetJuvenileDispositionCodeEvent dispositionEvt = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
			    JuvenileDispositionCodeResponseEvent resultDisp = null;
			    JuvenileDispositionCodeResponseEvent dispResp = null;

			    //  if(currentSettingResp.getCourtResult()!=null && (currentSettingResp.getCourtResult().equalsIgnoreCase("RST") || !currentSettingResp.getCourtResult().equalsIgnoreCase(originalSettingResp.getCourtResult()))){
			    if(currentSettingResp.getCourtResult()!=null&&!currentSettingResp.getCourtResult().isEmpty())
			    {
        			    //check for result.
        			    dispositionEvt.setCode(currentSettingResp.getCourtResult());
        			    dispatch.postEvent(dispositionEvt);
        			    CompositeResponse dispCompResp = (CompositeResponse) dispatch.getReply();
        			    Collection<JuvenileDispositionCodeResponseEvent> juvResult = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
        
        			    if (juvResult != null)
        			    {
        				Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvResult.iterator();
        				if (juvCodeIter.hasNext())
        				{
        				    resultDisp = juvCodeIter.next();
        				    if (resultDisp != null)
        				    {
        					if (resultDisp.getOptionCode()!=null && resultDisp.getOptionCode().equalsIgnoreCase("R"))
        					{
        					    if (currentSettingResp.getDocketType().equalsIgnoreCase("COURT"))
        					    {
        						isResetForCourt = true;
        					    }
        
        					    if (currentSettingResp.getDocketType().equalsIgnoreCase("ANCILLARY"))
        					    {
        						isResetForAncillary = true; //ancillary
        					    }
        					}
        				    }
        				}
        			    }
			    }
			    //  }
			    //    if (currentSettingResp.getDisposition() != null && !currentSettingResp.getDisposition().equalsIgnoreCase(originalSettingResp.getDisposition()))
			    //    {
			    if (currentSettingResp.getDisposition() != null && !currentSettingResp.getDisposition().isEmpty())
			    {
        			    // for disposition
        			    dispositionEvt.setCode(currentSettingResp.getDisposition());
        			    dispatch.postEvent(dispositionEvt);
        			    CompositeResponse compDisp = (CompositeResponse) dispatch.getReply();
        			    Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(compDisp, JuvenileDispositionCodeResponseEvent.class);
        
        			    if (juvDisp != null)
        			    {
        				Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvDisp.iterator();
        				if (juvCodeIter.hasNext())
        				{
        				    dispResp = juvCodeIter.next();
        				    if (dispResp != null)
        				    {
        					if (dispResp.getOptionCode()!=null && dispResp.getOptionCode().equalsIgnoreCase("R"))
        					{
        					    if (currentSettingResp.getDocketType().equalsIgnoreCase("COURT"))
        					    {
        						isResetForCourt = true;
        					    }
        
        					    if (currentSettingResp.getDocketType().equalsIgnoreCase("ANCILLARY"))
        					    {
        						isResetForAncillary = true; //ancillary
        					    }
        					}
        				    }
        				}
        			    }
			    }
			    //  }
			    /* if (isResetForCourt)
			     {
			    GetJJSCLCourtByChainAndSeqNumEvent courtEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
			    courtEvt.setChainNumber(currentSettingResp.getChainNumber());
			    courtEvt.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum()) + 10));
			    dispatch.postEvent(courtEvt);

			    CompositeResponse courtResp = (CompositeResponse) dispatch.getReply();
			    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtResp, DocketEventResponseEvent.class);
			    if (docketResponses == null || (docketResponses != null && docketResponses.isEmpty()))
			    {
			        isNewCourtRecordCreated = true;
			    }
			    else
			    {
			        isSubsequentCourtRecordAvail = true;
			    }
			     }
			     if (isResetForAncillary)
			     {
			    GetJJSCLAncillaryCourtByChainAndSeqNumEvent ancillaryEvt = (GetJJSCLAncillaryCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYCHAINANDSEQNUM);
			    ancillaryEvt.setChainNumber(currentSettingResp.getChainNumber());
			    ancillaryEvt.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum()) + 10));
			    dispatch.postEvent(ancillaryEvt);
			    CompositeResponse ancillaryCompResp = (CompositeResponse) dispatch.getReply();
			    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(ancillaryCompResp, DocketEventResponseEvent.class);
			    if (docketResponses == null || (docketResponses != null && docketResponses.isEmpty()))
			    {
			        isNewAncillaryRecordCreated = true;
			    }
			    else
			    {
			        isSubsequentAncillaryRecordAvail = true;
			    }
			     }*/
			    /////////////////////////////////////////////////////////////////ACTUAL UPDATE PLUS IT CREATE NEW RECORD IF CONDITION BECOMES TRUE////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			    /**
			     * same logic as in delete function. deletion
			     * happens during submission. section 3.4.6.20
			     * delete calendar record. Court Calendar record The
			     * entry of Result code � OFF� when current setting
			     * is the only setting in the chain will result in
			     * the deletion of the setting and no other settings
			     * will be associated to the chain number
			     * (subsequent settings will not be associated to
			     * the chain.
			     */
			    if (currentSettingResp.getDocketType().equalsIgnoreCase("COURT"))
			    {
				String seqNumForNewRec = null;
				GetJJSCLCourtByChainAndSeqNumEvent courtEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
				courtEvt.setChainNumber(currentSettingResp.getChainNumber());
				dispatch.postEvent(courtEvt);
				CompositeResponse courtResp = (CompositeResponse) dispatch.getReply();
				List<DocketEventResponseEvent> dktResps = MessageUtil.compositeToList(courtResp, DocketEventResponseEvent.class);

				Collections.sort((List<DocketEventResponseEvent>) dktResps, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
				    @Override
				    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
				    {
					if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
					    return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
					else
					    return -1;
				    }
				}));

				if (dktResps != null)
				{

				    if (dktResps.iterator().hasNext())
				    {
					DocketEventResponseEvent response = dktResps.iterator().next();
					if (response != null)
					{
					    seqNumForNewRec = String.valueOf(Integer.valueOf(response.getSeqNum()) + 10);
					}
				    }

				    if (currentSettingResp.getCourtResult()!=null && currentSettingResp.getCourtResult().equalsIgnoreCase("OFF"))
				    {
					//Is deleted setting the last setting
					boolean isOnlySetting = false;
					int dSize = dktResps.size();
					if (dSize == 1)
					{
					    isOnlySetting = true;
					}
					if (isOnlySetting)
					{
					    Map<String, DocketEventResponseEvent> dktSearchResultsMap = courtHearingForm.getDktSearchResultsMap();
					    DeleteJJSCLCourtSettingEvent deleteCourtSettingEvt = (DeleteJJSCLCourtSettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.DELETEJJSCLCOURTSETTING);
					    deleteCourtSettingEvt.setDocketEventId(currentSettingResp.getDocketEventId());

					    CompositeResponse compositeResponse = MessageUtil.postRequest(deleteCourtSettingEvt);
					    DocketEventResponseEvent dktResp = (DocketEventResponseEvent) MessageUtil.filterComposite(compositeResponse, DocketEventResponseEvent.class);
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
					    if (dktResp.getDeleteFlag() != null && dktResp.getDeleteFlag().equalsIgnoreCase("true"))
					    {
						isOnlySettingDeleted = true;
						// after deletion process.
						if (dktSearchResultsMap.containsKey(currentSettingResp.getDocketEventIdKey()))
						{
						    dktSearchResultsMap.remove(currentSettingResp.getDocketEventIdKey()); //remove from the main list after deletion.
						    courtHearingForm.setAllDktSearchResults(new ArrayList<DocketEventResponseEvent>(dktSearchResultsMap.values()));
						    courtHearingForm.setSearchResultSize(dktSearchResultsMap.size());
						    courtHearingForm.setDktSearchResultsMap(dktSearchResultsMap);
						}

						//update Juvenile Referral information.
						UpdateJuvenileReferralFromDistrictCourtEvent juvenileReferralUpdateEvent = (UpdateJuvenileReferralFromDistrictCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJUVENILEREFERRALFROMDISTRICTCOURT);
						juvenileReferralUpdateEvent.setDeleteFlag("true");
						juvenileReferralUpdateEvent.setIsOnlySetting("true");
						juvenileReferralUpdateEvent.setJuvenileNumber(currentSettingJuvenileNum);
						juvenileReferralUpdateEvent.setReferralNumber(currentSettingResp.getReferralNum());
						juvenileReferralUpdateEvent.setPetitionNum(currentSettingResp.getPetitionNumber());
						juvenileReferralUpdateEvent.setPetitionStatus(currentSettingResp.getPetitionStatus());
						CompositeResponse juvenileReferralCompResponse = postRequestEvent(juvenileReferralUpdateEvent);
					    }
					}
				    }
				}
				if (!isOnlySettingDeleted)
				{
				    /**
				     * All Calendar record types: Chain number
				     * is the same as Chain number in current
				     * setting. Chain Sequence number: find the
				     * last setting record in the current chain,
				     * Add +10 to the located setting�s Chain
				     * Sequence Number. Resulting value becomes
				     * new settings Chain Sequence number.
				     * ResetHearingType = current setting�s
				     * PetitionType if user has not supplied a
				     * value. LastChangeDate/Time = current
				     * system date/time LastChangeUser = current
				     * session user HearingCategory: Category
				     * for Disposition always takesRcour
				     * precedence over the category associated
				     * to a Result value.
				     */
				    //existing record.
				    UpdateJJSCLCourtSettingEvent updateCourtEvt = (UpdateJJSCLCourtSettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLCOURTSETTING);
				    updateCourtEvt.setDocketEventId(currentSettingResp.getDocketEventId());

				    updateCourtEvt.setAttorneyConnection(currentSettingResp.getAttorneyConnection());
				    updateCourtEvt.setAttorneyName(currentSettingResp.getAttorneyName());
				    updateCourtEvt.setBarNumber(currentSettingResp.getBarNum());
				    /*if (currentSettingResp.getBarNum() != null && currentSettingResp.getBarNum().equals(""))
				    {
					updateCourtEvt.setBarNumber(null);
				    }
				    else
				    {
					updateCourtEvt.setBarNumber(currentSettingResp.getBarNum());
				    }*/
				    // Adlitem
				    updateCourtEvt.setGalName(currentSettingResp.getGalName());
				    updateCourtEvt.setGalBarNum(currentSettingResp.getGalBarNum());
				 /*   if (currentSettingResp.getGalBarNum() != null && currentSettingResp.getGalBarNum().equals(""))
				    {
					updateCourtEvt.setGalBarNum(null);
				    }
				    else
				    {
					updateCourtEvt.setGalBarNum(currentSettingResp.getGalBarNum());
					updateCourtEvt.setUpdateSubAdLitem(true);
				    }*/
				    //add GAL to last attorney table too-name,barnum and galadddate
				    // gal changes for task 158461
				    /*if (currentSettingResp.getGalBarNum() != null && !currentSettingResp.getGalBarNum().isEmpty())
				    {*/
					UpdateJJSLastAttorneyGALEvent updatelstattorneyEvent =(UpdateJJSLastAttorneyGALEvent)
						EventFactory.getInstance( JuvenileCaseControllerServiceNames.UPDATEJJSLASTATTORNEYGAL );
					updatelstattorneyEvent.setJuvenileNumber(currentSettingResp.getJuvNum());
					updatelstattorneyEvent.setGalName(currentSettingResp.getGalName());
					updatelstattorneyEvent.setGalbarNumber(currentSettingResp.getGalBarNum());
					/*updatelstattorneyEvent.setRecType(currentSettingResp.getRecType());
					updatelstattorneyEvent.setJjclcourtId(currentSettingResp.getDocketEventId());*/
					
					dispatch.postEvent( updatelstattorneyEvent );
			        	CompositeResponse composite = (CompositeResponse)dispatch.getReply();
			        				
			        	MessageUtil.processReturnException( composite );
				    //}
				    //
				    //null check
				    //attorney 2 details
				    updateCourtEvt.setAttorney2Name(currentSettingResp.getAttorney2Name());
				    updateCourtEvt.setAttorney2Connection(currentSettingResp.getAttorney2Connection());
				    updateCourtEvt.setAttorney2BarNum(currentSettingResp.getAttorney2BarNum());
				    /*if (currentSettingResp.getAttorney2BarNum() != null && currentSettingResp.getAttorney2BarNum().equals(""))
				    {
					updateCourtEvt.setAttorney2BarNum(null);
				    }
				    else
				    {
					updateCourtEvt.setAttorney2BarNum(currentSettingResp.getAttorney2BarNum());
				    }*/
				    //
				    if (StringUtil.isEmpty(currentSettingResp.getComments()))
					updateCourtEvt.setComments(null); 
				    else
					updateCourtEvt.setComments(currentSettingResp.getComments());
				    updateCourtEvt.setChainNumber(currentSettingResp.getChainNumber());
				    updateCourtEvt.setCourtId(currentSettingResp.getCourt());
				    updateCourtEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
				    updateCourtEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));

				    if (dispResp != null && currentSettingResp.getDisposition() != null && !currentSettingResp.getDisposition().isEmpty())
				    {
					updateCourtEvt.setHearingCategory(dispResp.getCategoryCode()); //category Code
				    }
				    else
					if (resultDisp != null && currentSettingResp.getCourtResult() != null && !currentSettingResp.getCourtResult().isEmpty())
					{
					    updateCourtEvt.setHearingCategory(resultDisp.getCategoryCode()); //category Code
					}
				    updateCourtEvt.setHearingDisposition(currentSettingResp.getDisposition());
				    UIJuvenileHelper.PopulateTJPCdisp(updateCourtEvt, currentSettingResp.getDisposition());  //US 179485 - save disposition code to TJPCDisp field in JJSCLCOURT table
				    updateCourtEvt.setHearingResult(currentSettingResp.getCourtResult());
				    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty())
					updateCourtEvt.setResetDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
				    updateCourtEvt.setHearingType(currentSettingResp.getHearingType()); //hearing Type
				    updateCourtEvt.setJuvenileNumber( currentSettingResp.getJuvenileNumber() );
				    updateCourtEvt.setPrevNotes(currentSettingResp.getPrevNotes());

				    updateCourtEvt.setResetHearingType(currentSettingResp.getResetHearingType());

				    updateCourtEvt.setTransferTo(currentSettingResp.getTransferTo());
				    updateCourtEvt.setNewRecordCreated(false);
				    // add appellate attorney
				    updateCourtEvt.setAppAttorney(currentSettingResp.getAppAttorney());
				    //  task 168662
				    updateCourtEvt.setInterpreter(currentSettingResp.getInterpreter());
				    CompositeResponse comp = MessageUtil.postRequest(updateCourtEvt);
				    DocketEventResponseEvent docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(comp, DocketEventResponseEvent.class);
				    Object errResponse = MessageUtil.filterComposite(comp, ErrorResponseEvent.class);
				    if (errResponse != null)
				    {
					ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
					try
					{
					    handleFatalUnexpectedException(myEvt.getMessage());
					}
					catch (GeneralFeedbackMessageException e)
					{
					    e.printStackTrace();
					}
				    }
				    if (docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
				    {
					isRecordUpdated = true;

					if (resultDisp != null && resultDisp.getOptionCode() != null && !resultDisp.getOptionCode().equalsIgnoreCase("R"))
					{
					    currentSettingResp.setActionDate("");
					    currentSettingResp.setActionTime("");
					}
					
					if(docEvtResp.getResetHearingUpdateFlag().equalsIgnoreCase("true") ){
					    if(currentSettingResp.getResetHearingType()!=null && !currentSettingResp.getResetHearingType().equalsIgnoreCase(originalSettingResp.getResetHearingType())){
						// court Decision Code table. Bug no: 
						GetJJSCLCourtByChainAndSeqNumEvent courtbyChainEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
						courtbyChainEvt.setChainNumber(currentSettingResp.getChainNumber());
						courtbyChainEvt.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum()) + 10));
						dispatch.postEvent(courtbyChainEvt);

						CompositeResponse courtchainResp = (CompositeResponse) dispatch.getReply();
						List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtchainResp, DocketEventResponseEvent.class);
						Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

						if (docketResponsesItr.hasNext())
						{
						    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
						    if (docResp != null)
						    {
							updateCourtEvt = (UpdateJJSCLCourtSettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLCOURTSETTING);
							updateCourtEvt.setDocketEventId(docResp.getDocketEventId());
							updateCourtEvt.setNewRecordCreated(false);
							//bug #83690
							if (currentSettingResp.getResetHearingType() != null && !currentSettingResp.getResetHearingType().equals(originalSettingResp.getResetHearingType()))
							{
							    updateCourtEvt.setHearingType(currentSettingResp.getResetHearingType());
							}
							comp = MessageUtil.postRequest(updateCourtEvt);
							docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(comp, DocketEventResponseEvent.class);
							errResponse = MessageUtil.filterComposite(comp, ErrorResponseEvent.class);
							if (errResponse != null)
							{
							    ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
							    try
							    {
								handleFatalUnexpectedException(myEvt.getMessage());
							    }
							    catch (GeneralFeedbackMessageException e)
							    {
								e.printStackTrace();
							    }
							}
							if (docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
							{
							  isSubsequentRecUpdated = true;
							}
						    } //if
						}
					    }
					}
				    }

				    //update subsequent record
				    /**
				     * 3.4.6.8 Activity: Update existing
				     * subsequent calendar record for resets If
				     * screen Action Date is populated and the
				     * Result has an Option = �R�, and a
				     * subsequent calender record is in the same
				     * chain as the current setting, (Chain
				     * Sequence number is larger than current
				     * chain sequence number AND is the next one
				     * in sequential order), change the
				     * subsequent calendar record�s
				     * CourtDate/HearingDate to the screen
				     * ResetDate/ActionDate of the current
				     * setting.
				     */
				    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
				    {
					// court Decision Code table. Bug no: 
					/*if (isSubsequentCourtRecordAvail)
					{
					GetJJSCLCourtByChainAndSeqNumEvent courtbyChainEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
					courtbyChainEvt.setChainNumber(currentSettingResp.getChainNumber());
					courtbyChainEvt.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum()) + 10));
					dispatch.postEvent(courtbyChainEvt);

					CompositeResponse courtchainResp = (CompositeResponse) dispatch.getReply();
					List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtchainResp, DocketEventResponseEvent.class);
					Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

					while (docketResponsesItr.hasNext())
					{
					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
					    if (docResp != null)
					    {

						updateCourtEvt = (UpdateJJSCLCourtSettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLCOURTSETTING);
						updateCourtEvt.setDocketEventId(docResp.getDocketEventId());
						updateCourtEvt.setChainNumber(currentSettingResp.getChainNumber());
						*//**
					 * change the subsequent calendar
					 * record�s CourtDate /HearingDate to
					 * the screen ResetDate /ActionDate of
					 * the current setting.
					 */
					/*
					if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					{
					 updateCourtEvt.setCourtDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
					}
					else
					{
					 updateCourtEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
					}
					//time
					if (currentSettingResp.getActionTime() != null && !currentSettingResp.getActionTime().isEmpty() && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
					{
					 updateCourtEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime()));
					}
					else
					{
					 updateCourtEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));
					}
					updateCourtEvt.setNewRecordCreated(false);

					comp = MessageUtil.postRequest(updateCourtEvt);
					docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(comp, DocketEventResponseEvent.class);
					errResponse = MessageUtil.filterComposite(comp, ErrorResponseEvent.class);
					if (errResponse != null)
					{
					 ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
					 try
					 {
					handleFatalUnexpectedException(myEvt.getMessage());
					 }
					 catch (GeneralFeedbackMessageException e)
					 {
					e.printStackTrace();
					 }
					}
					if (docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
					{
					 isSubsequentRecUpdated = true;
					}
					}
					} //while
					}*/
					/**
					 * Court Calendar record Populate the
					 * new calendar record with the values
					 * from the existing calendar record,
					 * except for those attributes modified
					 * or newly populated by the user. The
					 * new/modified values should populate
					 * the corresponding attributes on the
					 * new calendar record. Previous Notes,
					 * Comments, Result and Disposition,
					 * ActionDate/Time are NOT copied to new
					 * calendar record. ActionDate/Time on
					 * previous calendar record in same
					 * chain becomes the CourtDate/Time on
					 * the new record.
					 */
					if (isResetForCourt)
					{
					    updateCourtEvt = new UpdateJJSCLCourtSettingEvent();
					    updateCourtEvt.setChainNumber(currentSettingResp.getChainNumber());
					    updateCourtEvt.setSeqNumber(seqNumForNewRec);
					    /*if (dispResp != null && currentSettingResp.getDisposition() != null && !currentSettingResp.getDisposition().isEmpty())
					    {
					        updateCourtEvt.setHearingCategory(dispResp.getCategoryCode()); //category Code
					    }
					    else if (resultDisp != null && currentSettingResp.getCourtResult() != null && !currentSettingResp.getCourtResult().isEmpty())
					    {
					        updateCourtEvt.setHearingCategory(resultDisp.getCategoryCode()); //category Code
					    }*/

					    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					    {
						updateCourtEvt.setCourtDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
					    }
					    else
					    {
						updateCourtEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
					    }
					    //time
					    if (currentSettingResp.getActionTime() != null && !currentSettingResp.getActionTime().isEmpty() && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
					    {
						updateCourtEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime()));
					    }
					    else
					    {
						updateCourtEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));
					    }
					    if (StringUtil.isEmpty(currentSettingResp.getAttorneyConnection()))
						updateCourtEvt.setAttorneyConnection(null); 
					    else
						updateCourtEvt.setAttorneyConnection(currentSettingResp.getAttorneyConnection());
					    if (StringUtil.isEmpty(currentSettingResp.getAttorneyName()))
						updateCourtEvt.setAttorneyName(null); 
					    else
						updateCourtEvt.setAttorneyName(currentSettingResp.getAttorneyName());					    
					    if (currentSettingResp.getBarNum() != null && currentSettingResp.getBarNum().equals(""))
					    {
						updateCourtEvt.setBarNumber(null);
					    }
					    else
					    {
						updateCourtEvt.setBarNumber(currentSettingResp.getBarNum());
					    }
					    // attorney 2 details
					    if (StringUtil.isEmpty(currentSettingResp.getAttorney2Connection()))
						updateCourtEvt.setAttorney2Connection(null); 
					    else
						updateCourtEvt.setAttorney2Connection(currentSettingResp.getAttorney2Connection());
					    if (StringUtil.isEmpty(currentSettingResp.getAttorney2Name()))
						updateCourtEvt.setAttorney2Name(null); 
					    else
						updateCourtEvt.setAttorney2Name(currentSettingResp.getAttorney2Name());					    
					    if (currentSettingResp.getAttorney2BarNum() != null && currentSettingResp.getAttorney2BarNum().equals(""))
					    {
						updateCourtEvt.setAttorney2BarNum(null);
					    }
					    else
					    {
						updateCourtEvt.setAttorney2BarNum(currentSettingResp.getAttorney2BarNum());
					    }
					    updateCourtEvt.setAppAttorney(currentSettingResp.getAppAttorney());
					    // task 168662
					    updateCourtEvt.setInterpreter(currentSettingResp.getInterpreter());
					    if (currentSettingResp.getGalBarNum() != null && currentSettingResp.getGalBarNum().equals(""))
					    {
						updateCourtEvt.setGalBarNum(null);
					    }
					    else
					    {
						updateCourtEvt.setGalBarNum(currentSettingResp.getGalBarNum());
						updateCourtEvt.setGalName( currentSettingResp.getGalName());
						updateCourtEvt.setUpdateSubAdLitem(true);
					    }
					    
					    if (currentSettingResp.getTransferTo() != null && !currentSettingResp.getTransferTo().isEmpty())
					    {
						if (currentSettingResp.getTransferTo().contains("0"))
						{
						    String transferTo = currentSettingResp.getTransferTo().substring(2);
						    updateCourtEvt.setCourtId(transferTo);
						}
						else
						{
						    updateCourtEvt.setCourtId(currentSettingResp.getTransferTo());
						}
					    }
					    else
					    {
						updateCourtEvt.setCourtId(currentSettingResp.getCourt());
					    }
					    				    
					    updateCourtEvt.setFilingDate(DateUtil.stringToDate(currentSettingResp.getFilingDate(), DateUtil.DATE_FMT_1));
					    updateCourtEvt.setIssueFlag(currentSettingResp.getIssueFlag());
					    updateCourtEvt.setJuryFlag(currentSettingResp.getJuryFlag());
					    updateCourtEvt.setJuvenileNumber(currentSettingJuvenileNum);
					    updateCourtEvt.setOptionFlag(currentSettingResp.getOptionFlag());
					    updateCourtEvt.setPetitionAllegation(currentSettingResp.getPetitionAllegation());
					    //bug fix for 112974
					    if (StringUtil.isEmpty(currentSettingResp.getComments()))
						updateCourtEvt.setComments(null); 
					    else
						updateCourtEvt.setComments(currentSettingResp.getComments());
					    if (StringUtil.isEmpty(currentSettingResp.getPetitionAmendment()))
						updateCourtEvt.setPetitionAmendment(null); 
					    else
						updateCourtEvt.setPetitionAmendment(currentSettingResp.getPetitionAmendment());					    
					    //
					    updateCourtEvt.setPrevNotes(currentSettingResp.getPrevNotes());
					    updateCourtEvt.setPetitionNumber(currentSettingResp.getPetitionNumber());
					    updateCourtEvt.setPetitionStatus(currentSettingResp.getPetitionStatusCd());
					    updateCourtEvt.setRecType(currentSettingResp.getDocketType());
					    updateCourtEvt.setReferralNumber(currentSettingResp.getReferralNum());
					    updateCourtEvt.setResetHearingType(currentSettingResp.getResetHearingType());
					    updateCourtEvt.setHearingType(currentSettingResp.getHearingType()); //hearing Type 84103
					    if(currentSettingResp.getResetHearingType()!=null && !currentSettingResp.getResetHearingType().isEmpty()){
						 updateCourtEvt.setHearingType(currentSettingResp.getResetHearingType());
					    }
					    
					    updateCourtEvt.setTjpcSeqNumber(currentSettingResp.getTjpcSeqNum());
					    updateCourtEvt.setAmendmentDate(DateUtil.stringToDate(currentSettingResp.getPetitionAmendmentDate(), DateUtil.DATE_FMT_1));
					    updateCourtEvt.setNewRecordCreated(true);

					    CompositeResponse compResponse = MessageUtil.postRequest(updateCourtEvt);
					    DocketEventResponseEvent docket = (DocketEventResponseEvent) MessageUtil.filterComposite(compResponse, DocketEventResponseEvent.class);
					    Object errorResponse = MessageUtil.filterComposite(comp, ErrorResponseEvent.class);
					    if (errorResponse != null)
					    {
						ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
						try
						{
						    handleFatalUnexpectedException(myEvt.getMessage());
						}
						catch (GeneralFeedbackMessageException e)
						{
						    e.printStackTrace();
						}
					    }
					    if (docket != null && docket.getIsNewRecordCreated().equalsIgnoreCase("true"))
					    {
						isNewRecordCreated = true;
						//code to email JPO task 148174
						isEmailsend = sendJPOemail(currentSettingResp, originalSettingResp);
						    
					    }
					    // as part of task 150244 to reset the flag of temp release in jjs_header on reset 
					    //add todays date compare
					    UpdateJJSHeaderFromDetentionCourtEvent updateHeaderEvt = (UpdateJJSHeaderFromDetentionCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSHEADERFROMDETENTIONCOURT);
					    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
					    Date currDate = new Date();
					    String today = sdf.format(currDate).trim();
					    if (!("".equalsIgnoreCase(currentSettingResp.getCourtResult())) && currentSettingResp.getCourtResult() != null && currentSettingResp.getCourtDate().trim().equals(today))
						{						 
						    updateHeaderEvt.setDistreleasedecisionStatus(true);
						    updateHeaderEvt.setJuvenileNumber(currentSettingJuvenileNum);
						    CompositeResponse headerResponse = MessageUtil.postRequest(updateHeaderEvt);
						    JuvenileFacilityHeaderResponseEvent headerRespEvt = (JuvenileFacilityHeaderResponseEvent) MessageUtil.filterComposite(headerResponse, JuvenileFacilityHeaderResponseEvent.class);
						} 
					}
				    }

				    //update Juvenile Master Status.
				    if (isRecordUpdated)
				    {
					boolean updateMaster = false;

					//update juvenile master information.
					UpdateJuvenileMasterFromDistrictCourtEvent juvenileMasterUpdateEvent = (UpdateJuvenileMasterFromDistrictCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJUVENILEMASTERFROMDISTRICTCOURT);

					juvenileMasterUpdateEvent.setJuvenileNumber(currentSettingJuvenileNum);
					juvenileMasterUpdateEvent.setOriginalActionDate(originalSettingResp.getActionDate());
					if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					{
					    juvenileMasterUpdateEvent.setActionDate(currentSettingResp.getActionDate());
					}
					juvenileMasterUpdateEvent.setOriginalCourtResult(originalSettingResp.getCourtResult());
					juvenileMasterUpdateEvent.setOriginalDisposition(originalSettingResp.getDisposition());
					juvenileMasterUpdateEvent.setCourtDate(currentSettingResp.getCourtDate());
					juvenileMasterUpdateEvent.setCourtResult(currentSettingResp.getCourtResult());
					juvenileMasterUpdateEvent.setDisposition(currentSettingResp.getDisposition());
					CompositeResponse juvenileCompResponse = postRequestEvent(juvenileMasterUpdateEvent);
					
					//update Juvenile Referral information.
					UpdateJuvenileReferralFromDistrictCourtEvent juvenileReferralUpdateEvent = (UpdateJuvenileReferralFromDistrictCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJUVENILEREFERRALFROMDISTRICTCOURT);
					juvenileReferralUpdateEvent.setCourtId(courtHearingForm.getCourtId());
					juvenileReferralUpdateEvent.setCourtDate(currentSettingResp.getCourtDate());
					if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					{
					    juvenileReferralUpdateEvent.setActionDate(currentSettingResp.getActionDate());
					}
					juvenileReferralUpdateEvent.setCourtDisposition(currentSettingResp.getDisposition());
					juvenileReferralUpdateEvent.setCourtResult(currentSettingResp.getCourtResult());//if trn with checkbox pass that too
					juvenileReferralUpdateEvent.setJuvenileNumber(currentSettingJuvenileNum);
					juvenileReferralUpdateEvent.setReferralNumber(currentSettingResp.getReferralNum());
					juvenileReferralUpdateEvent.setOriginalCourtResult(originalSettingResp.getCourtResult());
					juvenileReferralUpdateEvent.setOriginalCourtDisposition(originalSettingResp.getDisposition());
					juvenileReferralUpdateEvent.setTransferTo(currentSettingResp.getTransferTo());
					juvenileReferralUpdateEvent.setPetitionNum(currentSettingResp.getPetitionNumber());
					juvenileReferralUpdateEvent.setPetitionStatus(currentSettingResp.getPetitionStatus());
					// task 164542
					if(currentSettingResp.getTransferFlag().equalsIgnoreCase("1"))
					    juvenileReferralUpdateEvent.setTransferFlag("1");
					else
					    juvenileReferralUpdateEvent.setTransferFlag("0");
					//
					CompositeResponse juvenileReferralCompResponse = postRequestEvent(juvenileReferralUpdateEvent);
					if(isdispositionemailSend==false && currentSettingResp.getDisposition()!=null &&  (originalSettingResp.getDisposition()==null ||originalSettingResp.getDisposition().isEmpty()))
					{
					    isdispositionemailSend = senddispositionEmail(currentSettingResp, originalSettingResp);
					}
					
					//

				    }

				    //notification.
				    //section 3.4.16.14
				    /*Final Disposititon Alert: If the user-supplied disposition has Option = �F� and the setting�s previous Disposition did not have Option = �F�,   a final Disposition alert needs to be sent.*/
				    if (originalSettingResp.getDisposition() != null && !originalSettingResp.getDisposition().isEmpty())
				    {
					GetJuvenileDispositionCodeEvent juvDispositionEvent = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
					juvDispositionEvent.setCode(originalSettingResp.getDisposition());
					CompositeResponse resp = postRequestEvent(juvDispositionEvent);
					Collection<JuvenileDispositionCodeResponseEvent> juvDisposition = MessageUtil.compositeToCollection(resp, JuvenileDispositionCodeResponseEvent.class);
					JuvenileDispositionCodeResponseEvent responseEvt = null;

					if (juvDisposition != null)
					{
					    Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvDisposition.iterator();
					    if (juvCodeIter.hasNext())
					    {
						responseEvt = juvCodeIter.next();
						if (responseEvt != null)
						{
						    //final dispostion
						    if (dispResp != null && dispResp.getOptionCode()!=null && dispResp.getOptionCode().equalsIgnoreCase("F") && responseEvt.getOptionCode()!=null && !responseEvt.getOptionCode().equalsIgnoreCase("F"))
						    {
							JuvenileProfileReferralListResponseEvent profileResp = JuvenileFacilityHelper.getCasefilesFromReferral(currentSettingJuvenileNum, currentSettingResp.getReferralNum());
							Collection<JuvenileCasefileReferral> casefilesResp = profileResp.getCasefileReferrals();

							Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
							    @Override
							    public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
							    {
								if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
								    return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());
								else
								    return -1;
							    }
							}));

							Iterator<JuvenileCasefileReferral> casefileItr = casefilesResp.iterator();
							while (casefileItr.hasNext())
							{
							    JuvenileCasefileReferral casefileReferral = casefileItr.next();
							    if (casefileReferral != null)
							    {
								OfficerProfile officerProfile = casefileReferral.getCaseFile().getProbationOfficer();
								if (officerProfile != null && officerProfile.getLogonId() != null)
								{
								    JuvenileDistrictCourtNotificationResponseEvent respEvt = new JuvenileDistrictCourtNotificationResponseEvent();
								    respEvt.setSubject("Court Has Issued Final Disposition");
								    respEvt.setIdentity(officerProfile.getLogonId());
								    respEvt.setJuvenileNum(currentSettingJuvenileNum);

								    String notifMessage = "A final disposition was handed down by the court on " + currentSettingResp.getCourtDate() + " for " + currentSettingResp.getJuvenileName() + ", Juvenile# " + currentSettingJuvenileNum + " and Petition# " + currentSettingResp.getPetitionNumber();
								    respEvt.setNotificationMessage(notifMessage);

								    CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
								    notificationEvent.setNotificationTopic("JC.DISTRICT.COURT.FINAL.DISPOSITION.NOTIFICATION");
								    notificationEvent.setSubject(respEvt.getSubject());
								    notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
								    notificationEvent.addContentBean(respEvt);
								    CompositeResponse response = MessageUtil.postRequest(notificationEvent);
								    MessageUtil.processReturnException(response);
								}
								//CLMS
								if (officerProfile != null && officerProfile.getManagerLogonId() != null)
								{
								    JuvenileDistrictCourtNotificationResponseEvent respEvt = new JuvenileDistrictCourtNotificationResponseEvent();
								    respEvt.setSubject("Court Has Issued Final Disposition");
								    respEvt.setIdentity(officerProfile.getManagerLogonId());
								    respEvt.setJuvenileNum(currentSettingJuvenileNum);

								    String notifMessage = "A final disposition was handed down by the court on " + currentSettingResp.getCourtDate() + " for " + currentSettingResp.getJuvenileName() + ", Juvenile# " + currentSettingJuvenileNum + " and Petition# " + currentSettingResp.getPetitionNumber();
								    respEvt.setNotificationMessage(notifMessage);

								    CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
								    notificationEvent.setNotificationTopic("JC.DISTRICT.COURT.FINAL.DISPOSITION.NOTIFICATION");
								    notificationEvent.setSubject(respEvt.getSubject());
								    notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
								    notificationEvent.addContentBean(respEvt);
								    CompositeResponse response = MessageUtil.postRequest(notificationEvent);
								    MessageUtil.processReturnException(response);
								}
							    }
							}
						    }
						    //deferred disposition
						    if (dispResp != null && dispResp.getjPCCode() != null && dispResp.getjPCCode().equalsIgnoreCase("100") && responseEvt.getSubGroupInd() != null && responseEvt.getSubGroupInd().equalsIgnoreCase("I"))
						    {
							JuvenileProfileReferralListResponseEvent profileResp = JuvenileFacilityHelper.getCasefilesFromReferral(currentSettingJuvenileNum, currentSettingResp.getReferralNum());
							Collection<JuvenileCasefileReferral> casefilesResp = profileResp.getCasefileReferrals();

							Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
							    @Override
							    public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
							    {
								if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
								    return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());
								else
								    return -1;
							    }
							}));

							Iterator<JuvenileCasefileReferral> casefileItr = casefilesResp.iterator();
							while (casefileItr.hasNext())
							{
							    JuvenileCasefileReferral casefileReferral = casefileItr.next();
							    if (casefileReferral != null)
							    {
								OfficerProfile officerProfile = casefileReferral.getCaseFile().getProbationOfficer();
								if (officerProfile != null && officerProfile.getLogonId() != null)
								{
								    JuvenileDistrictCourtNotificationResponseEvent respEvt = new JuvenileDistrictCourtNotificationResponseEvent();
								    respEvt.setSubject("Court Has Issued Deferred Adjudication Order");
								    respEvt.setIdentity(officerProfile.getLogonId());
								    respEvt.setJuvenileNum(currentSettingJuvenileNum);

								    String notifMessage = "Court has ordered deferred adjudication for " + currentSettingResp.getJuvenileName() + ",Juvenile# " + currentSettingJuvenileNum + " and Petition# " + currentSettingResp.getPetitionNumber() + ". On " + currentSettingResp.getCourtDate();
								    respEvt.setNotificationMessage(notifMessage);

								    CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
								    notificationEvent.setNotificationTopic("JC.DISTRICT.COURT.DEFERRED.ADJUDICATION.NOTIFICATION");
								    notificationEvent.setSubject(respEvt.getSubject());
								    notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
								    notificationEvent.addContentBean(respEvt);
								    CompositeResponse response = MessageUtil.postRequest(notificationEvent);
								    MessageUtil.processReturnException(response);
								}
								//CLMS
								if (officerProfile != null && officerProfile.getManagerLogonId() != null)
								{
								    JuvenileDistrictCourtNotificationResponseEvent respEvt = new JuvenileDistrictCourtNotificationResponseEvent();
								    respEvt.setSubject("Court Has Issued Deferred Adjudication Order");
								    respEvt.setIdentity(officerProfile.getManagerLogonId());
								    respEvt.setJuvenileNum(currentSettingJuvenileNum);

								    String notifMessage = "Court has ordered deferred adjudication for " + currentSettingResp.getJuvenileName() + ",Juvenile# " + currentSettingJuvenileNum + " and Petition# " + currentSettingResp.getPetitionNumber() + ". On " + currentSettingResp.getCourtDate();
								    respEvt.setNotificationMessage(notifMessage);

								    CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
								    notificationEvent.setNotificationTopic("JC.DISTRICT.COURT.DEFERRED.ADJUDICATION.NOTIFICATION");
								    notificationEvent.setSubject(respEvt.getSubject());
								    notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
								    notificationEvent.addContentBean(respEvt);
								    CompositeResponse response = MessageUtil.postRequest(notificationEvent);
								    MessageUtil.processReturnException(response);
								}
							    }
							}
						    }
						}
					    }
					}
				    }
				}
			    } //court

			    /**
			     * Ancillary Calendar record The entry of Result
			     * code � OFF� when current setting is the only
			     * setting in the chain will result in the deletion
			     * of the setting and no other settings will be
			     * associated to the chain number (subsequent
			     * settings will not be associated to the chain).
			     */
			    if (currentSettingResp.getDocketType().equalsIgnoreCase("ANCILLARY"))
			    {
				String seqNumForNewRec = null;
				GetJJSCLAncillaryCourtByChainAndSeqNumEvent ancillaryEvt = (GetJJSCLAncillaryCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYCHAINANDSEQNUM);
				ancillaryEvt.setChainNumber(currentSettingResp.getChainNumber());
				dispatch.postEvent(ancillaryEvt);
				CompositeResponse ancillaryResp = (CompositeResponse) dispatch.getReply();

				List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(ancillaryResp, DocketEventResponseEvent.class);
				Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

				Collections.sort((List<DocketEventResponseEvent>) docketResponses, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
				    @Override
				    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
				    {
					if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
					    return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
					else
					    return -1;
				    }
				}));

				if (docketResponses != null)
				{
				    if (docketResponsesItr.hasNext())
				    {
					DocketEventResponseEvent evt = docketResponses.iterator().next();
					if (evt != null)
					{
					    seqNumForNewRec = String.valueOf(Integer.valueOf(evt.getSeqNum()) + 10);
					}
				    }

				    if (currentSettingResp.getCourtResult()!=null && currentSettingResp.getCourtResult().equalsIgnoreCase("OFF"))
				    {
					//Is deleted setting the only setting
					boolean isOnlySetting = false;
					int dSize = docketResponses.size();
					if (dSize == 1)
					{
					    isOnlySetting = true;
					}
					if (isOnlySetting)
					{
					    Map<String, DocketEventResponseEvent> dktSearchResultsMap = courtHearingForm.getDktSearchResultsMap();
					    DeleteJJSCLAncillarySettingEvent deleteAncillarySettingEvt = (DeleteJJSCLAncillarySettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.DELETEJJSCLANCILLARYSETTING);
					    deleteAncillarySettingEvt.setDocketEventId(currentSettingResp.getDocketEventId());

					    CompositeResponse compositeResponse = MessageUtil.postRequest(deleteAncillarySettingEvt);
					    DocketEventResponseEvent resp = (DocketEventResponseEvent) MessageUtil.filterComposite(compositeResponse, DocketEventResponseEvent.class);
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
					    if (resp.getDeleteFlag() != null && resp.getDeleteFlag().equalsIgnoreCase("true"))
					    {
						isOnlySettingDeleted = true;
						// after deletion process.
						if (dktSearchResultsMap.containsKey(currentSettingResp.getDocketEventIdKey()))
						{
						    dktSearchResultsMap.remove(currentSettingResp.getDocketEventIdKey()); //remove from the main list after deletion.
						    courtHearingForm.setDktSearchResultsMap(dktSearchResultsMap);
						    courtHearingForm.setAllDktSearchResults(new ArrayList<DocketEventResponseEvent>(dktSearchResultsMap.values()));
						    courtHearingForm.setSearchResultSize(dktSearchResultsMap.size());
						}
					    }
					}
				    } //last setting check
				}

				if (!isOnlySettingDeleted)
				{
				    /**
				     * All Calendar record types: Chain number
				     * is the same as Chain number in current
				     * setting. Chain Sequence number: find the
				     * last setting record in the current chain,
				     * Add +10 to the located setting�s Chain
				     * Sequence Number. Resulting value becomes
				     * new settings Chain Sequence number.
				     * ResetHearingType = current setting�s
				     * PetitionType if user has not supplied a
				     * value. LastChangeDate/Time = current
				     * system date/time LastChangeUser = current
				     * session user HearingCategory: Category
				     * for Disposition always takes precedence
				     * over the category associated to a Result
				     * value.
				     */
				    //update existing record
				    UpdateJJSCLAncillarySettingEvent updateAncillaryEvt = (UpdateJJSCLAncillarySettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLANCILLARYSETTING);
				    updateAncillaryEvt.setDocketEventId(currentSettingResp.getDocketEventId());
				    updateAncillaryEvt.setAttorneyConnection(currentSettingResp.getAttorneyConnection());
				    updateAncillaryEvt.setAttorneyName(currentSettingResp.getAttorneyName());
				    if (currentSettingResp.getBarNum() != null && currentSettingResp.getBarNum().equals(""))
				    {
					updateAncillaryEvt.setBarNum(null);
				    }
				    else
				    {
					updateAncillaryEvt.setBarNum(currentSettingResp.getBarNum());
				    }
				    
				   /* updateAncillaryEvt.setGalName(currentSettingResp.getGalName());
				    if (currentSettingResp.getGalBarNum() != null && currentSettingResp.getGalBarNum().equals(""))
				    {
					updateAncillaryEvt.setGalBarNum(null);
				    }
				    else
				    {
					updateAncillaryEvt.setGalBarNum(currentSettingResp.getGalBarNum());
				    }*/
				    updateAncillaryEvt.setComments(currentSettingResp.getComments());
				    updateAncillaryEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
				    updateAncillaryEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));
				    updateAncillaryEvt.setCourtId(currentSettingResp.getCourt());
				    updateAncillaryEvt.setHearingDisposition(currentSettingResp.getDisposition());
				    updateAncillaryEvt.setHearingResult(currentSettingResp.getCourtResult());
				    updateAncillaryEvt.setSettingReason(currentSettingResp.getSettingReason()); //hearing Type
				    updateAncillaryEvt.setPrevNotes(currentSettingResp.getPrevNotes());
				    updateAncillaryEvt.setResetHearingType(currentSettingResp.getResetHearingType());
				    updateAncillaryEvt.setTransferTo(currentSettingResp.getTransferTo());
				    updateAncillaryEvt.setRespondentName(currentSettingResp.getRespondantName());
				    updateAncillaryEvt.setNewRecordCreated(false);
				    //task 168662
				    updateAncillaryEvt.setInterpreter(currentSettingResp.getInterpreter());

				    CompositeResponse comp = MessageUtil.postRequest(updateAncillaryEvt);
				    DocketEventResponseEvent docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(comp, DocketEventResponseEvent.class);
				    Object errResponse = MessageUtil.filterComposite(comp, ErrorResponseEvent.class);
				    if (errResponse != null)
				    {
					ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
					try
					{
					    handleFatalUnexpectedException(myEvt.getMessage());
					}
					catch (GeneralFeedbackMessageException e)
					{
					    e.printStackTrace();
					}
				    }
				    if (docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
				    {
					isRecordUpdated = true;
					
					//83690
					if(docEvtResp.getResetHearingUpdateFlag().equalsIgnoreCase("true"))
					{
					    if(currentSettingResp.getResetHearingType()!=null && !currentSettingResp.getResetHearingType().equalsIgnoreCase(originalSettingResp.getResetHearingType()))
					    {
						GetJJSCLAncillaryCourtByChainAndSeqNumEvent ancillaryByChainEvt = (GetJJSCLAncillaryCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYCHAINANDSEQNUM);
						ancillaryByChainEvt.setChainNumber(currentSettingResp.getChainNumber());
						ancillaryByChainEvt.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum())+ 10));
						dispatch.postEvent(ancillaryByChainEvt);
						CompositeResponse ancillaryCompResp = (CompositeResponse) dispatch.getReply();

						List<DocketEventResponseEvent> docketResps = MessageUtil.compositeToList(ancillaryCompResp, DocketEventResponseEvent.class);
						Iterator<DocketEventResponseEvent> docEvtItr = docketResps.iterator();

						if (docEvtItr.hasNext())
						{
						    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docEvtItr.next();
						    //action date and time (derived value) gets persisted in the court date and court time field on update of court action
						    if (docResp != null)
						    {
							updateAncillaryEvt = (UpdateJJSCLAncillarySettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLANCILLARYSETTING);
							updateAncillaryEvt.setDocketEventId(docResp.getDocketEventId());
							updateAncillaryEvt.setNewRecordCreated(false);
							//bug #83690
							if (currentSettingResp.getResetHearingType() != null && !currentSettingResp.getResetHearingType().equals(originalSettingResp.getResetHearingType()))
							{
							    updateAncillaryEvt.setSettingReason(currentSettingResp.getResetHearingType());
							}
							comp = MessageUtil.postRequest(updateAncillaryEvt);
							docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(comp, DocketEventResponseEvent.class);
							errResponse = MessageUtil.filterComposite(comp, ErrorResponseEvent.class);
							if (errResponse != null)
							{
							    ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
							    try
							    {
								handleFatalUnexpectedException(myEvt.getMessage());
							    }
							    catch (GeneralFeedbackMessageException e)
							    {
								e.printStackTrace();
							    }
							}
							if (docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
							{
							  isSubsequentRecUpdated = true;
							}
						    }
						}
					    }
					 }
				    }

				    //update subsequent record
				    /**
				     * 3.4.6.8 Activity: Update existing
				     * subsequent calendar record for resets If
				     * screen Action Date is populated and the
				     * Result has an Option = �R�, and a
				     * subsequent calender record is in the same
				     * chain as the current setting, (Chain
				     * Sequence number is larger than current
				     * chain sequence number AND is the next one
				     * in sequential order), change the
				     * subsequent calendar record�s
				     * CourtDate/HearingDate to the screen
				     * ResetDate/ActionDate of the current
				     * setting.
				     */
				    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
				    {
					//ancillary
					/*   if (isSubsequentAncillaryRecordAvail)
					   {
					GetJJSCLAncillaryCourtByChainAndSeqNumEvent ancillaryByChainEvt = (GetJJSCLAncillaryCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYCHAINANDSEQNUM);
					ancillaryByChainEvt.setChainNumber(currentSettingResp.getChainNumber());
					ancillaryByChainEvt.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum())+ 10));
					dispatch.postEvent(ancillaryByChainEvt);
					CompositeResponse ancillaryCompResp = (CompositeResponse) dispatch.getReply();

					List<DocketEventResponseEvent> docketResps = MessageUtil.compositeToList(ancillaryCompResp, DocketEventResponseEvent.class);
					Iterator<DocketEventResponseEvent> docEvtItr = docketResps.iterator();

					while (docEvtItr.hasNext())
					{
					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docEvtItr.next();
					    //action date and time (derived value) gets persisted in the court date and court time field on update of court action
					    if (docResp != null)
					    {
						updateAncillaryEvt = (UpdateJJSCLAncillarySettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLANCILLARYSETTING);
						updateAncillaryEvt.setDocketEventId(docResp.getDocketEventId());
						*//**
					 * change the subsequent calendar
					 * record�s CourtDate /HearingDate to
					 * the screen ResetDate /ActionDate of
					 * the current setting.
					 */
					/*
					if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					{
					 updateAncillaryEvt.setCourtDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
					}
					else
					{
					 updateAncillaryEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
					}
					//time
					if (currentSettingResp.getActionTime() != null && !currentSettingResp.getActionTime().isEmpty() && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
					{
					 updateAncillaryEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime()));
					}
					else
					{
					 updateAncillaryEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));
					}

					updateAncillaryEvt.setNewRecordCreated(false);

					comp = MessageUtil.postRequest(updateAncillaryEvt);
					docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(comp, DocketEventResponseEvent.class);
					errResponse = MessageUtil.filterComposite(comp, ErrorResponseEvent.class);
					if (errResponse != null)
					{
					 ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
					 try
					 {
					handleFatalUnexpectedException(myEvt.getMessage());
					 }
					 catch (GeneralFeedbackMessageException e)
					 {
					e.printStackTrace();
					 }
					}
					if (docEvtResp != null && docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
					{
					 isSubsequentRecUpdated = true;
					}
					}
					} //while
					} //ancillary if
					*//**
					 * Court Calendar record Populate the
					 * new calendar record with the values
					 * from the existing calendar record,
					 * except for those attributes modified
					 * or newly populated by the user. The
					 * new/modified values should populate
					 * the corresponding attributes on the
					 * new calendar record. Previous Notes,
					 * Comments, Result and Disposition,
					 * ActionDate/Time are NOT copied to new
					 * calendar record. ActionDate/Time on
					 * previous calendar record in same
					 * chain becomes the CourtDate/Time on
					 * the new record.
					 */
					if (isResetForAncillary)
					{

					    updateAncillaryEvt = new UpdateJJSCLAncillarySettingEvent();
					    updateAncillaryEvt.setSeqNumber(seqNumForNewRec);

					    //date
					    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					    {
						updateAncillaryEvt.setCourtDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
					    }
					    else
					    {
						updateAncillaryEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
					    }
					    //time
					    if (currentSettingResp.getActionTime() != null && !currentSettingResp.getActionTime().isEmpty() && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
					    {
						updateAncillaryEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime()));
					    }
					    else
					    {
						updateAncillaryEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));
					    }

					    updateAncillaryEvt.setAttorneyConnection(currentSettingResp.getAttorneyConnection());
					    updateAncillaryEvt.setAttorneyName(currentSettingResp.getAttorneyName());
					    if (currentSettingResp.getBarNum() != null && currentSettingResp.getBarNum().equals(""))
					    {
						updateAncillaryEvt.setBarNum(null);
					    }
					    else
					    {
						updateAncillaryEvt.setBarNum(currentSettingResp.getBarNum());
					    }					    	
					    if (currentSettingResp.getTransferTo() != null && !currentSettingResp.getTransferTo().isEmpty())
					    {
						if (currentSettingResp.getTransferTo().contains("0"))
						{
						    String transferTo = currentSettingResp.getTransferTo().substring(2);
						    updateAncillaryEvt.setCourtId(transferTo);
						}
						else
						{
						    updateAncillaryEvt.setCourtId(currentSettingResp.getTransferTo());
						}
					    }
					    else
					    {
						updateAncillaryEvt.setCourtId(currentSettingResp.getCourt());
					    }
					    updateAncillaryEvt.setComments(currentSettingResp.getComments());
					    updateAncillaryEvt.setFilingDate(DateUtil.stringToDate(currentSettingResp.getFilingDate(), DateUtil.DATE_FMT_1));
					    updateAncillaryEvt.setIssueFlag(currentSettingResp.getIssueFlag());
					    updateAncillaryEvt.setJuryFlag(currentSettingResp.getJuryFlag());
					    updateAncillaryEvt.setPetitionAmendment(currentSettingResp.getPetitionAmendment());
					    updateAncillaryEvt.setPetitionNum(currentSettingResp.getPetitionNumber());
					    updateAncillaryEvt.setRecType(currentSettingResp.getDocketType());
					    updateAncillaryEvt.setResetHearingType(currentSettingResp.getResetHearingType());
					    updateAncillaryEvt.setTjpcSeqNumber(currentSettingResp.getTjpcSeqNum());
					    //  updateAncillaryEvt.setTransferTo(currentSettingResp.getTransferTo());
					    updateAncillaryEvt.setRespondentName(currentSettingResp.getRespondantName());
					    updateAncillaryEvt.setChainNum(currentSettingResp.getChainNumber());
					  //task 168662
					    updateAncillaryEvt.setInterpreter(currentSettingResp.getInterpreter());
					    if (currentSettingResp.getResetHearingType() != null && !currentSettingResp.getResetHearingType().isEmpty())
					    {
						updateAncillaryEvt.setSettingReason(currentSettingResp.getResetHearingType());
					    }
					    else
					    {
						updateAncillaryEvt.setSettingReason(originalSettingResp.getSettingReason());
					    }
					    /**
					     * If the record�s TypeCase = �C�,
					     * then Set NOTES (temp) = �DFPS�.
					     * If the record�s TypeCase = �P�,
					     * then Set NOTES (temp) =
					     * �PRIVATE�. If the record�s
					     * TypeCase = I, display, then Set
					     * NOTES (temp) = �IMMIGRATION�.
					     */
					    updateAncillaryEvt.setTypeCase(currentSettingResp.getTypeCase());
					    updateAncillaryEvt.setNewRecordCreated(true);
					    CompositeResponse compResponse = MessageUtil.postRequest(updateAncillaryEvt);
					    DocketEventResponseEvent docket = (DocketEventResponseEvent) MessageUtil.filterComposite(compResponse, DocketEventResponseEvent.class);
					    Object errorResponse = MessageUtil.filterComposite(compResponse, ErrorResponseEvent.class);
					    if (errorResponse != null)
					    {
						ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
						try
						{
						    handleFatalUnexpectedException(myEvt.getMessage());
						}
						catch (GeneralFeedbackMessageException e)
						{
						    e.printStackTrace();
						}
					    }
					    if (docket != null && docket.getIsNewRecordCreated().equalsIgnoreCase("true"))
					    {
						isNewRecordCreated = true;
					    }					  
					}
				    }
				}
			    } //ancillary
			}//court / ancillary

			if (currentSettingResp.getDocketType().equalsIgnoreCase("DETENTION"))
			{
			    String seqNumForNewRecord = null;
			    String detentionId	      = "";
			    GetJJSCLDetentionByChainEvent getDetentionEvt = (GetJJSCLDetentionByChainEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAIN);
			    getDetentionEvt.setChainNumber(currentSettingResp.getChainNumber());
			    dispatch.postEvent(getDetentionEvt);
			    CompositeResponse detentionResp = (CompositeResponse) dispatch.getReply();
			    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(detentionResp, DocketEventResponseEvent.class);

			    Collections.sort((List<DocketEventResponseEvent>) docketResponses, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
				@Override
				public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
				{
				    if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
					return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
				    else
					return -1;
				}
			    }));

			    if (docketResponses != null)
			    {
				if (docketResponses.iterator().hasNext())
				{
				    DocketEventResponseEvent docRespEvt = docketResponses.iterator().next();
				    if (docRespEvt != null)
				    {
					seqNumForNewRecord = String.valueOf(Integer.valueOf(docRespEvt.getSeqNum()) + 10);
					detentionId 	   = docRespEvt.getDetentionId();
					
				    }
				}
			    }

			    boolean isReset = false;
			    JuvenileCourtDecisionCodeResponseEvent crtResultDecision = null;
			    GetJuvenileCourtDecisionCodesEvent courtResultDisp = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
			    courtResultDisp.setCode(currentSettingResp.getCourtResult());
			    Collection<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = MessageUtil.postRequestListFilter(courtResultDisp, JuvenileCourtDecisionCodeResponseEvent.class);
			    if (crtDecisions != null)
			    {
				Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
				if (crtDecisionsItr.hasNext())
				{
				    crtResultDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
				    if (crtResultDecision != null)
				    {
					if (crtResultDecision.getResetAllowed()!=null && crtResultDecision.getResetAllowed().equalsIgnoreCase("Y") || crtResultDecision.getResetAllowed().equalsIgnoreCase("R")) //reset required is Y or R
					{
					    isReset = true;
					}
				    }
				}
			    }

			    //update the existing record;
			    UpdateJJSCLDetentionSettingEvent detentionEvt = (UpdateJJSCLDetentionSettingEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.UPDATEJJSCLDETENTIONSETTING);
			    detentionEvt.setDocketEventId(currentSettingResp.getDocketEventId());
			    detentionEvt.setCourtId(currentSettingResp.getCourt());
			    detentionEvt.setHearingType(currentSettingResp.getHearingType());
			    detentionEvt.setDetentionId(detentionId);
			    if (isReset)
			    {
				detentionEvt.setTransferTo(currentSettingResp.getTransferTo());
			    }
			    else
			    {
				detentionEvt.setTransferTo(null);
			    }
			    detentionEvt.setHearingDisposition(currentSettingResp.getDisposition());
			    detentionEvt.setHearingResult(currentSettingResp.getCourtResult());
			    //task 187260
			    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty())
				detentionEvt.setResettoDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
			    detentionEvt.setGalName(currentSettingResp.getGalName());
			    detentionEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
			    detentionEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));
			    detentionEvt.setAttorneyConnection(currentSettingResp.getAttorneyConnection());
			    detentionEvt.setAttorneyName(currentSettingResp.getAttorneyName());
			    detentionEvt.setBarNumber(currentSettingResp.getBarNum());
			    /*if (currentSettingResp.getBarNum() != null && currentSettingResp.getBarNum().equals(""))
			    {
				detentionEvt.setBarNumber(null);
			    }
			    else
			    {
				detentionEvt.setBarNumber(currentSettingResp.getBarNum());
			    }*/
			    /*if (currentSettingResp.getGalBarNum() != null && currentSettingResp.getGalBarNum().equals(""))
			    {
				detentionEvt.setGalBarNum(null);
			    }
			    else
			    {
				detentionEvt.setGalBarNum(currentSettingResp.getGalBarNum());
			    }*/
			    detentionEvt.setGalBarNum(currentSettingResp.getGalBarNum());
			    //add GAL to last attorney table too-name,barnum and galadddate as current date(only if not null)
			    // gal changes for task 158461
			    /*if (currentSettingResp.getGalBarNum() != null && !currentSettingResp.getGalBarNum().isEmpty())
			    {*/
				UpdateJJSLastAttorneyGALEvent updatelstattorneyEvent =(UpdateJJSLastAttorneyGALEvent)
					EventFactory.getInstance( JuvenileCaseControllerServiceNames.UPDATEJJSLASTATTORNEYGAL );
				updatelstattorneyEvent.setJuvenileNumber(currentSettingResp.getJuvNum());
				updatelstattorneyEvent.setGalName(currentSettingResp.getGalName());
				updatelstattorneyEvent.setGalbarNumber(currentSettingResp.getGalBarNum());					
				
				dispatch.postEvent( updatelstattorneyEvent );
		        	CompositeResponse composite = (CompositeResponse)dispatch.getReply();
		        				
		        	MessageUtil.processReturnException( composite );
			    //}
			    //
			  //attorney 2 details
			    detentionEvt.setAttorney2Name(currentSettingResp.getAttorney2Name());
			    detentionEvt.setAttorney2Connection(currentSettingResp.getAttorney2Connection());
			    detentionEvt.setAttorney2BarNum(currentSettingResp.getAttorney2BarNum());
			    /*if (currentSettingResp.getAttorney2BarNum() != null && currentSettingResp.getAttorney2BarNum().equals(""))
			    {
				detentionEvt.setAttorney2BarNum(null);
			    }
			    else
			    {
				detentionEvt.setAttorney2BarNum(currentSettingResp.getAttorney2BarNum());
			    }*/
			    //
			    if (StringUtil.isEmpty(currentSettingResp.getComments()))
				detentionEvt.setComments(null); 
			    else
				detentionEvt.setComments(currentSettingResp.getComments());
			    // task 168662
			    detentionEvt.setInterpreter(currentSettingResp.getInterpreter());

			    detentionEvt.setNewRecordCreated(false);

			    CompositeResponse clDetnResp = MessageUtil.postRequest(detentionEvt);
			    DocketEventResponseEvent docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(clDetnResp, DocketEventResponseEvent.class);
			    Object errorResponse = MessageUtil.filterComposite(clDetnResp, ErrorResponseEvent.class);
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
			    if (docEvtResp != null && docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
			    {
				isRecordUpdated = true;
				/*//83690
				if (!currentSettingResp.getSeqNum().equals("10") && docEvtResp.getResetHearingUpdateFlag().equalsIgnoreCase("true"))
				{
				    if (currentSettingResp.getResetHearingType()!=null &&  !currentSettingResp.getResetHearingType().equalsIgnoreCase(originalSettingResp.getResetHearingType()))
				    {
					GetJJSCLDetentionByChainAndSeqNumEvent detentionRec = (GetJJSCLDetentionByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAINANDSEQNUM);
					detentionRec.setChainNumber(currentSettingResp.getChainNumber());
					detentionRec.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum()) + 10));
					dispatch.postEvent(detentionRec);
					CompositeResponse resp = (CompositeResponse) dispatch.getReply();

					List<DocketEventResponseEvent> docketResps = MessageUtil.compositeToList(resp, DocketEventResponseEvent.class);
					Iterator<DocketEventResponseEvent> docEvtItr = docketResps.iterator();

					if (docEvtItr.hasNext())
					{
					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docEvtItr.next();
					    //action date and time (derived value) gets persisted in the court date and court time field on update of court action
					    if (docResp != null)
					    {
						detentionEvt = (UpdateJJSCLDetentionSettingEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.UPDATEJJSCLDETENTIONSETTING);
						detentionEvt.setDocketEventId(docResp.getDocketEventId());
						detentionEvt.setNewRecordCreated(false);

						//bug #83690
						if (currentSettingResp.getResetHearingType() != null && !currentSettingResp.getResetHearingType().equals(originalSettingResp.getResetHearingType()))
						{
						    detentionEvt.setHearingType(currentSettingResp.getResetHearingType());
						}
						clDetnResp = MessageUtil.postRequest(detentionEvt);
						docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(clDetnResp, DocketEventResponseEvent.class);
						errorResponse = MessageUtil.filterComposite(clDetnResp, ErrorResponseEvent.class);
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
						if (docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
						{
						  isSubsequentRecUpdated = true;
						}
					    }
					}
				    }
				}*/
			    }
			    // after update
			    if (currentSettingResp.getCourtResult() != null && !currentSettingResp.getCourtResult().isEmpty())
			    {
				/**
				 * 4.9.12 - detention. US 71272: If the
				 * hearing�s RESULT or DISPOSITION has the
				 * *Action {DETENTION_COURT_DECISIONS.Action} of
				 * "Detained" and the juvenile�s header record
				 * FacilityStatus is not null; and, the setting
				 * is the first setting in the chain with
				 * �Action = Detained�, the hearing�s court date
				 * should be used to populate the juvenile�s
				 * Header.DetainedDate attribute. This
				 * information will be applied to the most
				 * recent facility admission_release record
				 * where the Booking Referral = referral of the
				 * calendar record. See ....\Detention Court
				 * Decisions CORRECTED09_28_18.docx
				 */
				/**
				 * 3.4.6.22 - district court U.S. 74417: If this
				 * is the first detention hearing setting in the
				 * chain with a Result or Disposition that has
				 * the associated action of "Detained" and the
				 * juvenile is in a facility, the Header
				 * record�s DetainedDate attribute should be
				 * populated with the setting�s court
				 * date/hearing date. This information will be
				 * applied to the most recent facility
				 * admission_release record where the Booking
				 * Referral = referral of the calendar record
				 * and the juvenile�s header record
				 * FacilityStatus is not null.
				 */

				boolean updateFacilityDetails = false;
				JuvenileFacilityHeaderResponseEvent headerResp = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(currentSettingJuvenileNum);
				//result.
				/* GetJuvenileCourtDecisionCodesEvent courtResultEvt = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
				 courtResultEvt.setCode(currentSettingResp.getCourtResult());
				 Collection<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = MessageUtil.postRequestListFilter(courtResultEvt, JuvenileCourtDecisionCodeResponseEvent.class);
				 if (crtDecisions != null)
				 {
				Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
				while (crtDecisionsItr.hasNext())
				{
				    JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();*/
				if (crtResultDecision != null)
				{
				    if (crtResultDecision.getAction()!=null && (crtResultDecision.getAction().equalsIgnoreCase("DETAINED")||crtResultDecision.getAction().equalsIgnoreCase("OFF DOCKET/RESET")))// added check "OFF DOCKET/RESET" for 186495 for US 186451
				    {
					if ((currentSettingResp.getHearingType()!=null && currentSettingResp.getHearingType().equalsIgnoreCase("DT") || currentSettingResp.getHearingType().equalsIgnoreCase("PC")) && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
					{

					    if (headerResp != null && headerResp.getFacilityStatus() != null && !headerResp.getFacilityStatus().isEmpty())
					    {
						updateFacilityDetails = true;
					    }
					}
				    }
				}
				/*}
				}*/
				//disposition
				if (currentSettingResp.getDisposition() != null && currentSettingResp.getDisposition().isEmpty())
				{
				    GetJuvenileCourtDecisionCodesEvent courtDisp = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
				    courtDisp.setCode(currentSettingResp.getDisposition());
				    Collection<JuvenileCourtDecisionCodeResponseEvent> crtDispDecisions = MessageUtil.postRequestListFilter(courtDisp, JuvenileCourtDecisionCodeResponseEvent.class);
				    if (crtDecisions != null)
				    {
					Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDispDecisions.iterator();
					if (crtDecisionsItr.hasNext())
					{
					    JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
					    if (crtDecision != null)
					    {
						if (crtDecision.getAction()!=null && crtDecision.getAction().equalsIgnoreCase("DETAINED"))
						{
						    if ((currentSettingResp.getHearingType()!=null && currentSettingResp.getHearingType().equalsIgnoreCase("DT") || currentSettingResp.getHearingType().equalsIgnoreCase("PC")) && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
						    {
							if (headerResp != null && headerResp.getFacilityStatus() != null && !headerResp.getFacilityStatus().isEmpty())
							{
							    updateFacilityDetails = true;
							}
						    }
						}
					    }
					}
				    }
				}
				if (updateFacilityDetails)
				{
				    /* 3.4.6.11.1 District Court:	Activity: Update juvenile�s Header with Detention setting transfer data
				       Locate the juvenile�s Header record.  Change the NextHearingDate to the reset date for the current setting.*/

				    /*District court section 3.4.6.15.2
				     * U.S. 74417:  If this is the first court or detention hearing setting in the chain with a Result or Disposition that has the associated action of "Detained" and the juvenile is in a facility, 
				     * the Header record�s DetainedDate attribute should be populated with the setting�s court date/hearing date.*/

				    UpdateJJSHeaderFromDistrictCourtEvent updateHeaderEvt = (UpdateJJSHeaderFromDistrictCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSHEADERFROMDISTRICTCOURT);
				    if(currentSettingResp.getActionDate()!=null&&!currentSettingResp.getActionDate().isEmpty())//for 186495 for US 186451
					updateHeaderEvt.setNextHearingDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
				    //bug 81615 Fixed.
				    if(currentSettingResp.getHearingType()!=null && currentSettingResp.getHearingType().equalsIgnoreCase("PC")){
					updateHeaderEvt.setProbableCauseDate(DateUtil.getCurrentDate());
				    }
				    else
				    {
					if (docketResponses != null)
					{
					    Collections.sort((List<DocketEventResponseEvent>) docketResponses, new Comparator<DocketEventResponseEvent>() {
						@Override
						public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
						{
						    if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
							return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
						    else
							return -1;
						}
					    });

					    Iterator<DocketEventResponseEvent> dockResp = docketResponses.iterator();

					    if (dockResp != null)
					    {
						DocketEventResponseEvent responseEvent = dockResp.next();
						if (responseEvent.getSeqNum() != null && responseEvent.getSeqNum().equalsIgnoreCase("10") && responseEvent.getHearingType() != null && responseEvent.getHearingType().equalsIgnoreCase("PC"))
						{
						    updateHeaderEvt.setProbableCauseDate(DateUtil.stringToDate(responseEvent.getCourtDate(), DateUtil.DATE_FMT_1));
						}
					    }
					}
				    }
				    
				    updateHeaderEvt.setJuvenileNumber(currentSettingJuvenileNum);
				    CompositeResponse headerResponse = MessageUtil.postRequest(updateHeaderEvt);
				    JuvenileFacilityHeaderResponseEvent headerRespEvt = (JuvenileFacilityHeaderResponseEvent) MessageUtil.filterComposite(headerResponse, JuvenileFacilityHeaderResponseEvent.class);
				    Object headerErrorResp = MessageUtil.filterComposite(headerResponse, ErrorResponseEvent.class);
				    if (headerErrorResp != null)
				    {
					ErrorResponseEvent myEvt = (ErrorResponseEvent) headerErrorResp;
					try
					{
					    handleFatalUnexpectedException(myEvt.getMessage());
					}
					catch (GeneralFeedbackMessageException e)
					{
					    e.printStackTrace();
					}
				    }
				    if (currentSettingResp.getSeqNum() != null && currentSettingResp.getSeqNum().equalsIgnoreCase("10"))
				    {
					UpdateJJSDetentionFromDistrictCourtEvent updateJJSDetentionEvt = (UpdateJJSDetentionFromDistrictCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSDETENTIONFROMDISTRICTCOURT);
					updateJJSDetentionEvt.setJuvenileNumber(currentSettingJuvenileNum);
					updateJJSDetentionEvt.setDetainedDate(DateUtil.stringToDate(currentSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));

					if (currentSettingResp.getCourtResult() != null && (currentSettingResp.getCourtResult().equalsIgnoreCase("SRR") || currentSettingResp.getCourtResult().equalsIgnoreCase("SRA")))
					{
					    JuvenileFacilityHeaderResponseEvent header = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(currentSettingJuvenileNum);
					    if (header != null)
					    {
						if (header.getFacilityStatus()!=null && header.getFacilityStatus().equalsIgnoreCase("D"))
						{
						    /**
						     * US 73573: If RESULT was
						     * changed to �SRR� (Shelter
						     * Request - Released) or
						     * �SRA� (Shelter Request
						     * Accepted) and the
						     * FacilityStatus of the
						     * Header record = �D�,
						     * change the Admission
						     * Reason to �SHL� (Shelter)
						     * on the most recent
						     * facility record
						     * associated to the setting
						     * record: {where current
						     * juvenile number,
						     * JuvenileBookingReferral
						     * of the admission record =
						     * JUVENILE_DETENTION_HEARING
						     * .Juvenile Number and
						     * ReferralNumber} and the
						     * FacilityAdmitDate
						     * immediately precedes or
						     * is equal to the setting�s
						     * HearingDate.
						     */
						    updateJJSDetentionEvt.setAdmitReason("SHL");
						    updateJJSDetentionEvt.setAdmitDate(DateUtil.stringToDate(currentSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
						}
					    }
					}

					CompositeResponse response = MessageUtil.postRequest(updateJJSDetentionEvt);
					JuvenileDetentionFacilitiesResponseEvent jjsdetentionResp = (JuvenileDetentionFacilitiesResponseEvent) MessageUtil.filterComposite(response, JuvenileDetentionFacilitiesResponseEvent.class);
					Object errorResp = MessageUtil.filterComposite(response, ErrorResponseEvent.class);
					if (errorResp != null)
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
				    }
				} //3.4.6.22

				/*3.4.6.11 District Court Subflow: Determine Detention setting transfer status
				If the current setting is for a Detention Calendar record, has Result = Transfer, and the setting has been transferred to a Referee court (TRANSFER TO value associated to Referee Court = Yes), the application will need to upate the juvenile�s facility header (Next Hearing Date) with the ResetDate/screen Action Date.
				NOTE:  U.S. 78282 - Detention requirements document will process the following (current requirments resulted in creation of 2 new detention records):  In addition, the application will determine if there is a subsequent detention calendar record following this setting in the same chain as the current setting.  If so, update the next detention calendar record with the transfer request info. 
				 If there is no �next/subsequent� calendar record, a new calendar record will need to be created to capture the transfer.*/

				/*3.4.6.11.1 District Court:Activity: Update subsequent Detention setting with Detention setting transfer data
				  If a subsequent detention calander record (Chain Sequence number is larger than current chain sequence number AND is the next one in sequential order) in the same chain as the current setting, 
				  where the chain sequence number is greater than the current setting�s chain sequence number is located, change it�s HearingDate to the ResetDate/screen Action Date of the current setting.
				*/
				/*GetJuvenileCourtDecisionCodesEvent courtDisp = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
				courtDisp.setCode(currentSettingResp.getCourtResult());
				Collection<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = MessageUtil.postRequestListFilter(courtDisp, JuvenileCourtDecisionCodeResponseEvent.class);
				if (crtDecisions != null)
				{
				    Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
				    while (crtDecisionsItr.hasNext())
				    {
					JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();*/
				if (crtResultDecision != null)
				{
				    if (crtResultDecision.getResetAllowed()!=null && crtResultDecision.getResetAllowed().equalsIgnoreCase("Y") || crtResultDecision.getResetAllowed().equalsIgnoreCase("R")) //reset required is Y or R
				    {
					//if (currentSettingResp.getTransferTo() != null && !currentSettingResp.getTransferTo().isEmpty())
					//{
					    //TODO
					    // Confirmed with carla. No subsequent record will be created. IMplementation Bug : 
					    /*  GetJuvenileCourtsEvent courtEvent = (GetJuvenileCourtsEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTS);
					      courtEvent.setCourt(currentSettingResp.getTransferTo());
					      CompositeResponse courtRoomResp = MessageUtil.postRequest(courtEvent);
					      JuvenileCourtResponseEvent crtRespEvt = (JuvenileCourtResponseEvent) MessageUtil.filterComposite(courtRoomResp, JuvenileCourtResponseEvent.class);*/

					    /*  * 3.4.6.2 - district court document. Check carlas Email 
					      * If the court is a detention court: the application will default to the search detailed in the Process Juvenile Detention Hearings.doc*/

					    /*   if (crtRespEvt != null)
					    {
					    //TODO
					    if (crtRespEvt.getRefereesCourtInd() != null && !crtRespEvt.getRefereesCourtInd().equalsIgnoreCase("Y"))
					    {
					        GetJJSCLDetentionByChainAndSeqNumEvent detentionRec = (GetJJSCLDetentionByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAINANDSEQNUM);
					        detentionRec.setChainNumber(currentSettingResp.getChainNumber());
					        detentionRec.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum()) + 10));
					        dispatch.postEvent(detentionRec);
					        CompositeResponse resp = (CompositeResponse) dispatch.getReply();
					        if (resp != null)
					        {
					    	//update the subsequent record with current setting details.
					    	UpdateJJSCLDetentionSettingEvent updateEvt = (UpdateJJSCLDetentionSettingEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.UPDATEJJSCLDETENTIONSETTING);
					    	updateEvt.setChainNumber(currentSettingResp.getChainNumber());
					    	updateEvt.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum()) + 10));
					    	updateEvt.setHearingType("DT");
					    	updateEvt.setCourtId(currentSettingResp.getTransferTo());
					    	if (currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true"))
					    	{
					    	    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					    	    {
					    		updateEvt.setCourtDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
					    	    }
					    	    else
					    	    {
					    		updateEvt.setCourtDate(DateUtil.stringToDate(courtHearingForm.getCourtDate(), DateUtil.DATE_FMT_1));
					    	    }
					    	    //court Time
					    	    if (currentSettingResp.getActionTime() != null && !currentSettingResp.getActionTime().isEmpty() && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
					    	    {
					    		updateEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime()));
					    	    }
					    	    else
					    	    {
					    		updateEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getCourtTime()));
					    	    }
					    	}
					    	else
					    	{
					    	    updateEvt.setCourtDate(DateUtil.stringToDate(courtHearingForm.getCourtDate(), DateUtil.DATE_FMT_1));
					    	    updateEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getCourtTime()));
					    	}
					    	updateEvt.setHearingDisposition(null);
					    	updateEvt.setHearingResult(null);
					    	updateEvt.setNewRecordCreated(false);
					    	CompositeResponse compResp = MessageUtil.postRequest(updateEvt);
					    	DocketEventResponseEvent docketResp = (DocketEventResponseEvent) MessageUtil.filterComposite(compResp, DocketEventResponseEvent.class);
					    	Object errResp = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
					    	if (errResp != null)
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
					    	if (docketResp.getUpdateFlag().equalsIgnoreCase("true"))
					    	{
					    	    isRecordUpdated = true;
					    	}
					        }
					        else
					        {
					    	UpdateJJSCLDetentionSettingEvent updateEvt = (UpdateJJSCLDetentionSettingEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.UPDATEJJSCLDETENTIONSETTING);
					    	updateEvt.setChainNumber(currentSettingResp.getChainNumber());
					    	updateEvt.setSeqNumber(seqNumForNewRecord);
					    	updateEvt.setHearingType("DT");
					    	updateEvt.setCourtId(currentSettingResp.getTransferTo());
					    	if (currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true"))
					    	{
					    	    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					    	    {
					    		updateEvt.setCourtDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
					    	    }
					    	    else
					    	    {
					    		updateEvt.setCourtDate(DateUtil.stringToDate(courtHearingForm.getCourtDate(), DateUtil.DATE_FMT_1));
					    	    }
					    	    //court Time
					    	    if (currentSettingResp.getActionTime() != null && !currentSettingResp.getActionTime().isEmpty() && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
					    	    {
					    		updateEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime()));
					    	    }
					    	    else
					    	    {
					    		updateEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getCourtTime()));
					    	    }
					    	}
					    	else
					    	{
					    	    updateEvt.setCourtDate(DateUtil.stringToDate(courtHearingForm.getCourtDate(), DateUtil.DATE_FMT_1));
					    	    updateEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getCourtTime()));
					    	}
					    	updateEvt.setHearingDisposition(null);
					    	updateEvt.setHearingResult(null);

					    	//populate other values from the currentSetting.
					    	updateEvt.setAlternateSettingInd(currentSettingResp.getAlternateSettingInd());
					    	updateEvt.setAttorneyConnection(currentSettingResp.getAttorneyConnection());
					    	updateEvt.setAttorneyName(currentSettingResp.getAttorneyName());
					    	updateEvt.setBarNumber(currentSettingResp.getBarNum());
					    	updateEvt.setComments(currentSettingResp.getComments());
					    	updateEvt.setIssueFlag(currentSettingResp.getIssueFlag());
					    	updateEvt.setJuvenileNumber(currentSettingResp.getJuvenileNumber());
					    	updateEvt.setPetitionNumber(currentSettingResp.getJuvenileNumber());
					    	updateEvt.setReferralNumber(currentSettingResp.getReferralNum());
					    	updateEvt.setTransferTo(currentSettingResp.getTransferTo());
					    	updateEvt.setNewRecordCreated(true);

					    	CompositeResponse compResp = MessageUtil.postRequest(updateEvt);
					    	DocketEventResponseEvent docketResp = (DocketEventResponseEvent) MessageUtil.filterComposite(compResp, DocketEventResponseEvent.class);
					    	Object errResp = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
					    	if (errResp != null)
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
					        }
					    }
					    }*/
					    /*  U.S. 11646:  If the RESULT�s associated Require Reset = �Yes� or �Required� and user has supplied a valid reset date that is not the same as the Hearing date of the current setting a new Detention calendar record needs to be created:
					    Attributes will be populated with current setting�s data or new user-supplied data, except as follows:
					    Sequence Number = current setting�s value + 10.
					    Hearing Type = DT
					    CourtID = value of current setting�s TransferCourt
					    HearingDate = value of Reset To field for current setting�s
					    Disposition = NULL
					    Result = NULL */

					   /* if (currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true") && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
					    {
						isNewRecordCreated = createNewDetentionCalenderRecord(currentSettingResp, originalSettingResp, seqNumForNewRecord);
					    }*/
					//} //transfer create new record.

					//bug #83429
					

					    
					    //bug #83894
					    if (currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true") && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
					    {
							isNewRecordCreated = createNewDetentionCalenderRecord(currentSettingResp, originalSettingResp, seqNumForNewRecord, detentionId);
					    }

					/*if ((currentSettingResp.getHearingType()!=null && currentSettingResp.getHearingType().equalsIgnoreCase("DT") || currentSettingResp.getHearingType().equalsIgnoreCase("PC")) && currentSettingResp.getActionDate()!=null && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
					{
					    //private method to create new detention calender record.
					    isNewRecordCreated = createNewDetentionCalenderRecord(currentSettingResp, originalSettingResp, seqNumForNewRecord);
					}*/
				    } // reset date check.

				    if (crtResultDecision.getAction()!=null && crtResultDecision.getAction().equalsIgnoreCase("Released"))
				    {
					/**
					 * 4.9.13 4.9.14 Activity: Delete
					 * Released juvenile�s future settings
					 * If the associated *Action = Released
					 * for a Result, delete all future
					 * Detention setting records in the
					 * chain of the setting. See
					 * ....\Detention Court Decisions
					 * CORRECTED09_28_18.docx
					 */
					DeleteJJSCLDetentionSettingEvent deleteDetentionSettingEvt = (DeleteJJSCLDetentionSettingEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.DELETEJJSCLDETENTIONSETTING);
					deleteDetentionSettingEvt.setChainNumber(currentSettingResp.getChainNumber());

					CompositeResponse compResp = MessageUtil.postRequest(deleteDetentionSettingEvt);
					DocketEventResponseEvent docket = (DocketEventResponseEvent) MessageUtil.filterComposite(compResp, DocketEventResponseEvent.class);
					Object errResp = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
					if (errResp != null)
					{
					    ErrorResponseEvent myEvt = (ErrorResponseEvent) errResp;
					    try
					    {
						handleFatalUnexpectedException(myEvt.getMessage());
					    }
					    catch (GeneralFeedbackMessageException e)
					    {
						e.printStackTrace();
					    }
					}

					/**
					 * As of 7/13/2017: If the *Action
					 * associated to a DecisionCode =
					 * Released, send a release email to the
					 * *CLMs of the facility in which the
					 * juvenile is currently detained, as
					 * well as to each JPO assigned to an
					 * active/pending casefile for the
					 * juvenile. See ..\Detention Court
					 * Decisions CORRECTED09_28_18.docx
					 */

					// udpate is successful
					if (docEvtResp != null && docEvtResp.getUpdateFlag() != null && docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
					{
					    JJSFacility facility = JuvenileFacilityHelper.getCurrentFacilityRecord(currentSettingJuvenileNum);
					    String detainedFacilityDesc = "";
					    if (facility != null)
					    {
						detainedFacilityDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, facility.getDetainedFacility());
					    }

					    JuvenileProfileReferralListResponseEvent profileResp = JuvenileFacilityHelper.getCasefilesFromReferral(currentSettingJuvenileNum, currentSettingResp.getReferralNum());
					    Collection<JuvenileCasefileReferral> casefilesResp = profileResp.getCasefileReferrals();

					    Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
						@Override
						public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
						{
						    if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
							return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());
						    else
							return -1;
						}
					    }));

					    Iterator<JuvenileCasefileReferral> casefileItr = casefilesResp.iterator();
					    while (casefileItr.hasNext())
					    {
						JuvenileCasefileReferral casefileReferral = casefileItr.next();
						if (casefileReferral != null)
						{
						    //jpo notification
						    OfficerProfile officerProfile = casefileReferral.getCaseFile().getProbationOfficer();
						    if (officerProfile != null && officerProfile.getLogonId() != null)
						    {
							JuvenileDetentionNotificationResponseEvent respEvt = new JuvenileDetentionNotificationResponseEvent();
							respEvt.setSubject("RELEASE ordered for " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum + " from " + detainedFacilityDesc);
							respEvt.setIdentity(officerProfile.getLogonId());
							respEvt.setJuvenileNum(currentSettingJuvenileNum);

							StringBuffer message = new StringBuffer(100);
							message.append(" On ");
							message.append(currentSettingResp.getCourtDate());
							message.append(" Court 001");
							message.append(" ordered the release of ");
							message.append(currentSettingResp.getJuvenileName());
							message.append(" Juvenile#: ");
							message.append(currentSettingJuvenileNum);
							message.append(" from ");
							message.append(detainedFacilityDesc);
							respEvt.setNotificationMessage(message.toString());

							CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
							notificationEvent.setNotificationTopic("JC.DETENTION.JPO.DETAINED");
							notificationEvent.setSubject(respEvt.getSubject());
							notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
							notificationEvent.addContentBean(respEvt);
							CompositeResponse response = MessageUtil.postRequest(notificationEvent);
							MessageUtil.processReturnException(response);

							//send email
							Iterator<OfficerProfile> officerProfileDetails = OfficerProfile.findAll("logonId", officerProfile.getLogonId());
							if (officerProfileDetails != null)
							{
							    if (officerProfileDetails.hasNext())
							    {
								OfficerProfile profile = officerProfileDetails.next();
								if (profile != null && profile.getEmail() != null)
								{
								    SendEmailEvent sendEmailEvent = new SendEmailEvent();
								    sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
								    sendEmailEvent.addToAddress(profile.getEmail());
								    sendEmailEvent.setMessage(message.toString());
								    sendEmailEvent.setSubject("RELEASE ordered for " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum + " from " + detainedFacilityDesc);
								    dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
								    dispatch.postEvent(sendEmailEvent);
								}
							    }
							}
						    }
						    //CLM Notification
						    if (officerProfile != null && officerProfile.getManagerLogonId() != null)
						    {
							JuvenileDetentionNotificationResponseEvent respEvt = new JuvenileDetentionNotificationResponseEvent();
							respEvt.setSubject("RELEASE ordered for " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum + " from " + detainedFacilityDesc);
							respEvt.setIdentity(officerProfile.getManagerLogonId());
							respEvt.setJuvenileNum(currentSettingJuvenileNum);
							StringBuffer message = new StringBuffer(100);
							
							if (headerResp != null && headerResp.getFacilityStatus() != null && headerResp.getFacilityStatus().equalsIgnoreCase("N"))
							{
							    respEvt.setIdentity(officerProfile.getManagerLogonId());
							    respEvt.setJuvenileNum(currentSettingJuvenileNum);

							    message.append(" Court#: 001 ordered the release of ");
							    message.append(currentSettingResp.getJuvenileName());
							    message.append(", Juvenile #: " + currentSettingJuvenileNum);
							    message.append(" under supervision# ");
							    message.append(casefileReferral.getCaseFile().getCasefileId());
							    message.append(" and Referral#: ");
							    message.append(casefileReferral.getCaseFile().getCasefileControllingReferralId());
							    message.append(" from your facility.");
							    respEvt.setNotificationMessage(message.toString());

							    CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
							    notificationEvent.setNotificationTopic("JC.DETENTION.CLM.DETAINED");
							    notificationEvent.setSubject(respEvt.getSubject());
							    notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
							    notificationEvent.addContentBean(respEvt);
							    CompositeResponse response = MessageUtil.postRequest(notificationEvent);
							    MessageUtil.processReturnException(response);

							    //email
							    Iterator<OfficerProfile> officerProfileDetails = OfficerProfile.findAll("logonId", officerProfile.getManagerLogonId());
							    if (officerProfileDetails != null)
							    {
								if (officerProfileDetails.hasNext())
								{
								    OfficerProfile profile = officerProfileDetails.next();
								    if (profile != null && profile.getEmail() != null)
								    {
									SendEmailEvent sendEmailEvent = new SendEmailEvent();
									sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
									sendEmailEvent.addToAddress(profile.getEmail());
									sendEmailEvent.setMessage(message.toString());
									sendEmailEvent.setSubject("RELEASE ordered for " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum + " from " + detainedFacilityDesc);
									dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
									dispatch.postEvent(sendEmailEvent);
								    }
								}
							    }
							}
						    }
						}
					    }
					}
				    }
				    /**
				     * As of 7/13/2017: If the *Action
				     * associated to a DecisionCode = Detained,
				     * send a detention email to each JPO
				     * assigned to an active/pending casefile
				     * for the juvenile, as well as the *CLMs of
				     * the detention facility. See ..\Detention
				     * Court Decisions CORRECTED09_28_18.docx
				     */
				    if (crtResultDecision.getAction()!=null && crtResultDecision.getAction().equalsIgnoreCase("DETAINED"))
				    {
					if (docEvtResp.getUpdateFlag() != null && docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
					{

					    JuvenileProfileReferralListResponseEvent profileResp = JuvenileFacilityHelper.getCasefilesFromReferral(currentSettingJuvenileNum, currentSettingResp.getReferralNum());
					    Collection<JuvenileCasefileReferral> casefilesResp = profileResp.getCasefileReferrals();

					    Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
						@Override
						public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
						{
						    if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
							return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());
						    else
							return -1;
						}
					    }));

					    Iterator<JuvenileCasefileReferral> casefileItr = casefilesResp.iterator();
					    while (casefileItr.hasNext())
					    {
						JuvenileCasefileReferral casefileReferral = casefileItr.next();
						if (casefileReferral != null)
						{
						    //jpo notification
						    OfficerProfile officerProfile = casefileReferral.getCaseFile().getProbationOfficer();
						    if (officerProfile != null && officerProfile.getLogonId() != null)
						    {
							JuvenileDetentionNotificationResponseEvent respEvt = new JuvenileDetentionNotificationResponseEvent();
							respEvt.setSubject("DETAIN " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum);
							respEvt.setIdentity(officerProfile.getLogonId());
							respEvt.setJuvenileNum(currentSettingJuvenileNum);

							StringBuffer message = new StringBuffer(100);
							message.append(" On ");
							message.append(currentSettingResp.getCourtDate());
							message.append(" Court ");
							message.append(currentSettingResp.getCourt());
							message.append(" ordered ");
							message.append(currentSettingResp.getJuvenileName());
							message.append(" Juvenile#: ");
							message.append(currentSettingJuvenileNum);
							message.append(" to Be detained ");
							respEvt.setNotificationMessage(message.toString());

							CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
							notificationEvent.setNotificationTopic("JC.DETENTION.JPO.DETAINED");
							notificationEvent.setSubject(respEvt.getSubject());
							notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
							notificationEvent.addContentBean(respEvt);
							CompositeResponse response = MessageUtil.postRequest(notificationEvent);
							MessageUtil.processReturnException(response);

							//send email
							Iterator<OfficerProfile> officerProfileDetails = OfficerProfile.findAll("logonId", officerProfile.getLogonId());
							if (officerProfileDetails != null)
							{
							    if (officerProfileDetails.hasNext())
							    {
								OfficerProfile profile = officerProfileDetails.next();
								if (profile != null && profile.getEmail() != null)
								{
								    SendEmailEvent sendEmailEvent = new SendEmailEvent();
								    sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
								    sendEmailEvent.addToAddress(profile.getEmail());
								    sendEmailEvent.setMessage(message.toString());
								    sendEmailEvent.setSubject("DETAIN " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum);
								    dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
								    dispatch.postEvent(sendEmailEvent);
								}
							    }
							}
						    }
						    //CLM Notification
						    if (officerProfile != null && officerProfile.getManagerLogonId() != null)
						    {
							JuvenileDetentionNotificationResponseEvent respEvt = new JuvenileDetentionNotificationResponseEvent();
							respEvt.setSubject("DETAIN " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum);
							respEvt.setIdentity(officerProfile.getManagerLogonId());
							respEvt.setJuvenileNum(currentSettingJuvenileNum);
							StringBuffer message = new StringBuffer(100);
							if (headerResp.getFacilityStatus() != null  && headerResp.getFacilityStatus().equalsIgnoreCase("N"))
							{
							    respEvt.setIdentity(officerProfile.getManagerLogonId());
							    respEvt.setJuvenileNum(currentSettingJuvenileNum);

							    message.append(" Court#: ");
							    message.append(currentSettingResp.getCourt());
							    message.append(" ordered the detention of ");
							    message.append(currentSettingResp.getJuvenileName());
							    message.append(" under supervision# ");
							    message.append(casefileReferral.getCaseFile().getCasefileId());
							    message.append(" and Referral#: ");
							    message.append(casefileReferral.getCaseFile().getCasefileControllingReferralId());
							    message.append(" from your facility.");
							    respEvt.setNotificationMessage(message.toString());

							    CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
							    notificationEvent.setNotificationTopic("JC.DETENTION.CLM.DETAINED");
							    notificationEvent.setSubject(respEvt.getSubject());
							    notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
							    notificationEvent.addContentBean(respEvt);
							    CompositeResponse response = MessageUtil.postRequest(notificationEvent);
							    MessageUtil.processReturnException(response);

							    //email
							    Iterator<OfficerProfile> officerProfileDetails = OfficerProfile.findAll("logonId", officerProfile.getManagerLogonId());
							    if (officerProfileDetails != null)
							    {
								if (officerProfileDetails.hasNext())
								{
								    OfficerProfile profile = officerProfileDetails.next();
								    if (profile != null && profile.getEmail() != null)
								    {
									SendEmailEvent sendEmailEvent = new SendEmailEvent();
									sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
									sendEmailEvent.addToAddress(profile.getEmail());
									sendEmailEvent.setMessage(message.toString());
									sendEmailEvent.setSubject("DETAIN " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum);
									dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
									dispatch.postEvent(sendEmailEvent);
								    }
								}
							    }
							}
						    }
						}
					    }
					}
				    }
				}
			    }
			} //detention check
			  //update the original Setting map;
			if (isRecordUpdated)
			{
			    if (originalDktSearchResultsMap.containsKey(currentSettingResp.getDocketEventIdKey()))
			    {
				DocketEventResponseEvent origDocket = new DocketEventResponseEvent();
				try
				{
				    BeanUtils.copyProperties(origDocket, currentSettingResp);
				}
				catch (IllegalAccessException e)
				{
				    e.printStackTrace();
				}
				catch (InvocationTargetException e)
				{
				    e.printStackTrace();
				}
				originalDktSearchResultsMap.put(currentSettingResp.getDocketEventIdKey(), origDocket);
				aRequest.getSession().setAttribute("originalSetting", originalDktSearchResultsMap);
			    }
			}
		    } //else condition 
		}
	    }//for loop
	    if (!atleastOneSettingChanged || (!isRecordUpdated && !isNewRecordCreated && !isOnlySettingDeleted))
	    {
		ActionMessages messageHolder = new ActionMessages();
		messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.noChangeInSettingDetected"));
		aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
		saveMessages(aRequest, messageHolder);
		courtHearingForm.setCursorPosition("");
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    if (isRecordUpdated || isNewRecordCreated ||isOnlySettingDeleted)
	    {
		ActionMessages messageHolder = new ActionMessages();
		messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.settingRecordUpdated"));
		aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
		saveMessages(aRequest, messageHolder);
		courtHearingForm.setCursorPosition("");
		return refreshPage(aMapping, aForm, aRequest, aResponse);		
	    }
	}
	return aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY);
    }
//new submit
    
    public ActionForward submitafterConfirm(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse,String docketeventId, String byPass)//,String docketeventId
    //public ActionForward submitafterConfirm(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse,String juvNum,String courtDate, String byPass)//,String docketeventId
    {
	//add the bypass check and code like detention no confirmation flag save and bypass if (byPass == null)
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.setAction("updateCourtAction");
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	String pagerOffset = (String) aRequest.getParameter("pager.offset");
	courtHearingForm.setPagerOffset(pagerOffset);
	Map<String, DocketEventResponseEvent> originalDktSearchResultsMap = (Map<String, DocketEventResponseEvent>) aRequest.getSession().getAttribute("originalSetting");//courtHearingForm.getOriginalDktSearchResultsMap();
	
	String currentSettingJuvenileNum = null;
	boolean isRecordUpdated = false;
	boolean isNewRecordCreated = false;
	boolean isEmailsend = false;
	boolean isdispositionemailSend = false;	
	boolean isSubsequentRecUpdated = false;
	
	boolean isOnlySettingDeleted= false;

	boolean atleastOneSettingChanged = true;//set as true as needs to be saved for confirmation even if nothing changed.
	ArrayList<DocketEventResponseEvent> dockets=new ArrayList<DocketEventResponseEvent>();
	if(!docketeventId.isEmpty()&&docketeventId!=null)
	{
	      
	    Collection<DocketEventResponseEvent> docketEventList = courtHearingForm.getUpdatedDocketList() ;
		for( DocketEventResponseEvent currentEvent : docketEventList)
		{
			 if( currentEvent.getDocketEventId().equals(docketeventId))//change here to check juvnum and courtdate
			 {
			     dockets.add(currentEvent);
			     break;
			 }
		}
	    
	}
	/*if(!juvNum.isEmpty()&&juvNum!=null&&!courtDate.isEmpty()&&courtDate!=null)
	{
	      
	    Collection<DocketEventResponseEvent> docketEventList = courtHearingForm.getUpdatedDocketList() ;
		for( DocketEventResponseEvent currentEvent : docketEventList)
		{
			 if( currentEvent.getJuvenileNumber().equalsIgnoreCase(juvNum)&&currentEvent.getCourtDate().equalsIgnoreCase(courtDate))//change here to check juvnum and courtdate
			 {
			     dockets.add(currentEvent);
			     //break;
			 }
		}
	    
	}*/
	else
	    dockets = (ArrayList<DocketEventResponseEvent>) courtHearingForm.getUpdatedDocketList();
	if (dockets != null)
	{
	    for (int i = 0; i < dockets.size(); i++)
	    {		
		DocketEventResponseEvent currentSettingResp = dockets.get(i);		
		System.out.println("Chain number" +  currentSettingResp.getChainNumber());
		
		System.out.println("petitionAmendmentDate: " + currentSettingResp.getPetitionAmendmentDate());

		if (currentSettingResp != null)
		{
		    if (currentSettingResp.getRecType() != null && currentSettingResp.getRecType().contains("."))
		    {
			continue; //skip the i. and s. records for processing.
		    }		
		    
		    DocketEventResponseEvent originalSettingResp = new DocketEventResponseEvent();
		    if (originalDktSearchResultsMap != null)
		    {
			originalSettingResp = originalDktSearchResultsMap.get(currentSettingResp.getDocketEventIdKey());
			System.out.println("originalSettingResp amendmentDate: " + originalSettingResp.getPetitionAmendmentDate());
		    }
			atleastOneSettingChanged = true;
			isOnlySettingDeleted= false;

			if (currentSettingResp.getJuvenileNumber() != null && !currentSettingResp.getJuvenileNumber().isEmpty())
			{
			    currentSettingJuvenileNum = currentSettingResp.getJuvenileNumber().substring(1, currentSettingResp.getJuvenileNumber().length());
			}

			GetJuvenileCourtDecisionCodesEvent courtDecsn = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
			courtDecsn.setCode(currentSettingResp.getCourtResult());
			Collection<JuvenileCourtDecisionCodeResponseEvent> crtDescisions = MessageUtil.postRequestListFilter(courtDecsn, JuvenileCourtDecisionCodeResponseEvent.class);
			if (crtDescisions != null)
			{
			    Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDescisions.iterator();
			    while (crtDecisionsItr.hasNext())
			    {
				JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
				if (crtDecision != null)
				{
				    if (crtDecision.getResetAllowed()!=null && crtDecision.getResetAllowed().equalsIgnoreCase("Y") || crtDecision.getResetAllowed().equalsIgnoreCase("R"))
				    {
					currentSettingResp.setDisableResetDate("false");
					break;
				    }
				    else
				    {
					currentSettingResp.setDisableResetDate("true");
					break;
				    }
				}
			    }
			}

			/*********************************************************************** Validation Starts ***************************************************************************************/

			if (currentSettingResp.getActionTime() != null && !currentSettingResp.getActionTime().isEmpty())
			{
			    //validate length
			    if (currentSettingResp.getActionTime().length() < 4)
			    {
				courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
				courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Time Entered. Please Correct And Retry"));
				saveErrors(aRequest, errors);
				courtHearingForm.setAction("error");
				courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
				courtHearingForm.setPrevAction(courtHearingForm.getAction());
				return aMapping.findForward(UIConstants.FAILURE);
			    }
			    
			    String regex = "^[0-9]*$";
			    if (currentSettingResp.getActionTime().matches(regex))
			    {
				SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
				String actionTimeStr = JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime());
				Date actionTime;
				try
				{
				    actionTime = parser.parse(actionTimeStr);
				    Date startTime = parser.parse("06:00");
				    Date endTime = parser.parse("23:59");
				    if (actionTime.before(startTime) || actionTime.after(endTime))
				    {
					courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Time Entered. Please Correct And Retry"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				}
				catch (ParseException e)
				{
				    e.printStackTrace();
				    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
				    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
				    ActionErrors errors = new ActionErrors();
				    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Time Entered. Please Correct And Retry"));
				    saveErrors(aRequest, errors);
				    courtHearingForm.setAction("error");
				    courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
				    courtHearingForm.setPrevAction(courtHearingForm.getAction());
				    return aMapping.findForward(UIConstants.FAILURE);

				}
			    }
			    else
			    {
				courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
				courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Time Entered. Please Correct And Retry"));
				saveErrors(aRequest, errors);
				courtHearingForm.setAction("error");
				courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
				courtHearingForm.setPrevAction(courtHearingForm.getAction());
				return aMapping.findForward(UIConstants.FAILURE);

			    }
			}

			String actionTimeStr = JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime());
			Date actionTime = DateUtil.stringToDate(actionTimeStr, DateUtil.TIME24_FMT_1);

			String courtTimeStr = JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getCourtTime());
			Date courtTime = DateUtil.stringToDate(courtTimeStr, DateUtil.TIME24_FMT_1);

			if (currentSettingResp.getBarNum() != null && !currentSettingResp.getBarNum().isEmpty() && !currentSettingResp.getBarNum().equalsIgnoreCase(originalSettingResp.getBarNum()))
			{
			    //validate bar Number
			    GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
			    request.setBarNum(currentSettingResp.getBarNum());
			    dispatch.postEvent(request);
			    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

			    Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			    Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
			    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
			    Collection exceptions = (Collection) dataMap.get(ReturnException.RETURN_EXCEPTION_TOPIC);
			    if (error != null || attorneyDataList.isEmpty() || exceptions != null)
			    {
				courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
				courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Bar Number. Please Correct"));
				saveErrors(aRequest, errors);
				courtHearingForm.setAction("error");
				courtHearingForm.setCursorPosition("#barNum-" + currentSettingResp.getDocketEventIdKey());
				courtHearingForm.setPrevAction(courtHearingForm.getAction());
				return aMapping.findForward(UIConstants.FAILURE);
			    }
			}
			
			if (currentSettingResp.getGalBarNum() != null && !currentSettingResp.getGalBarNum().isEmpty() && !currentSettingResp.getGalBarNum().equalsIgnoreCase(originalSettingResp.getGalBarNum()))
			{
			    //validate bar Number
			    GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
			    request.setBarNum(currentSettingResp.getGalBarNum());
			    dispatch.postEvent(request);
			    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

			    Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			    Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
			    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
			    Collection exceptions = (Collection) dataMap.get(ReturnException.RETURN_EXCEPTION_TOPIC);
			    if (error != null || attorneyDataList.isEmpty() || exceptions != null)
			    {
				courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
				courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid GAL Bar Number. Please Correct"));
				saveErrors(aRequest, errors);
				courtHearingForm.setAction("error");
				courtHearingForm.setCursorPosition("#galBarNum-" + currentSettingResp.getDocketEventIdKey());
				courtHearingForm.setPrevAction(courtHearingForm.getAction());
				return aMapping.findForward(UIConstants.FAILURE);
			    }else{
				
				courtHearingForm.setAdlitemUpdateFlag(true);
			    }
			}
			//validate attorney 2 barnum
			if (currentSettingResp.getAttorney2BarNum() != null && !currentSettingResp.getAttorney2BarNum().isEmpty() && !currentSettingResp.getAttorney2BarNum().equalsIgnoreCase(originalSettingResp.getAttorney2BarNum()))
			{
			    //validate bar Number
			    GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
			    request.setBarNum(currentSettingResp.getAttorney2BarNum());
			    dispatch.postEvent(request);
			    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

			    Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			    Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
			    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
			    Collection exceptions = (Collection) dataMap.get(ReturnException.RETURN_EXCEPTION_TOPIC);
			    if (error != null || attorneyDataList.isEmpty() || exceptions != null)
			    {
				courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
				courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Attorney2 Bar Number. Please Correct"));
				saveErrors(aRequest, errors);
				courtHearingForm.setAction("error");
				courtHearingForm.setCursorPosition("#2ndAttorneyBarnum-" + currentSettingResp.getDocketEventIdKey());
				courtHearingForm.setPrevAction(courtHearingForm.getAction());
				return aMapping.findForward(UIConstants.FAILURE);
			    }else{
				
				courtHearingForm.setAdlitemUpdateFlag(true);
			    }
			}
			//
			//3.4.6.1 If screen Reset Hearing Type field is null, display �HEARING TYPE VALUE IS MISSING, PLEASE CORRECT AND RETRY.�
			if (currentSettingResp.getResetHearingType() != null && currentSettingResp.getResetHearingType().isEmpty() && !currentSettingResp.getResetHearingType().equalsIgnoreCase(originalSettingResp.getResetHearingType()))
			{
			    ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Hearing Type Value Is Missing, Please Correct And Retry"));
			    saveErrors(aRequest, errors);
			    courtHearingForm.setAction("error");
			    courtHearingForm.setCursorPosition("#hearingType-" + currentSettingResp.getDocketEventIdKey());
			    courtHearingForm.setPrevAction(courtHearingForm.getAction());
			    return aMapping.findForward(UIConstants.FAILURE);
			}
			

			//2)3.4.6.1 If the screen Action Date field is the same as the screen Court Date (inquiry date), 
			//determine if the Action Time is less than the CourtTime/HearingTime on the record AND the screen Result is not �Administrative Reset (A/R)� or �Transfer (TRN),�
			//display �INVALID HEARING DATE, TIME AND RESULT COMBINATION.�  Wait for user response.
			//referralDate = new Date(referralDate);

			if (courtTime != null && currentSettingResp.getActionDate() != null && currentSettingResp.getActionTime() != null && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()) && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
			{
			    if (currentSettingResp.getActionDate()!=null && currentSettingResp.getActionDate().equalsIgnoreCase(currentSettingResp.getCourtDate()) && actionTime.before(courtTime))
			    {
				if (currentSettingResp.getCourtResult()!=null && !currentSettingResp.getCourtResult().equalsIgnoreCase("A/R") || !currentSettingResp.getTransferFlag().equalsIgnoreCase("1"))//add checkbox change
				{
				    ActionErrors errors = new ActionErrors();
				    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Hearing Date, Time And Result Combination"));
				    saveErrors(aRequest, errors);
				    courtHearingForm.setAction("error");
				    courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
				    courtHearingForm.setPrevAction(courtHearingForm.getAction());
				    return aMapping.findForward(UIConstants.FAILURE);
				}
			    }
			}
			//83836
			/*// point d) added by cassandra after discussion.
			if ((currentSettingResp.getTransferTo() == null || currentSettingResp.getTransferTo() != null && currentSettingResp.getTransferTo().isEmpty()) 
				&& currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true") 
				&& courtHearingForm.getCourtDate() != null && currentSettingResp.getActionDate() != null  &&  courtHearingForm.getCourtDate().equalsIgnoreCase(currentSettingResp.getActionDate()))
			{
			    ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.generic", "Setting Currently Exists For This Date"));
			    saveErrors(aRequest, errors);
			    courtHearingForm.setAction("error");
			    courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
			    courtHearingForm.setPrevAction(courtHearingForm.getAction());
			    return aMapping.findForward(UIConstants.FAILURE);

			}*/

			/**
			 * If docketType is detention.
			 */
			if (currentSettingResp.getDocketType().equalsIgnoreCase("DETENTION"))
			{
			    if (currentSettingResp.getTransferTo() != null && !currentSettingResp.getTransferTo().equalsIgnoreCase(originalSettingResp.getTransferTo()))
			    {
				if (!currentSettingResp.getTransferTo().isEmpty())
				{
				    String districtCourts[] = { "313", "314", "315" };
				    //Case1:
				    //search district court + results -> detention Court.
				    //District Courts (300, 313, 314, 315) can transfer Detention hearings ONLY to 001 (Referee Court).
				    if (Arrays.asList(districtCourts).contains(courtHearingForm.getCourtId()))
				    {
					if (!currentSettingResp.getTransferTo().equalsIgnoreCase("001"))
					{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "District Courts (313, 314, 315) Can Transfer Detention Hearings ONLY To 001 (Referee Court)"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#transferTo-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				    //case 2:
				    //300th can transfer Detention hearings to Detention Court (001).
				    if (courtHearingForm.getCourtId()!=null && courtHearingForm.getCourtId().equalsIgnoreCase("300"))
				    {
					if (currentSettingResp.getTransferTo()!=null && !currentSettingResp.getTransferTo().equalsIgnoreCase("001"))
					{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "300th Can Transfer Detention Hearings To Detention Court (001)"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#transferTo-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				}
			    }

			    if (originalSettingResp != null)
			    {
				/**
				 * 3.4.6.2 8/10/18:�If Result, Disposition and
				 * Action Date/Time fields were populated during
				 * retrieval; Result has been changed to �OFF�;
				 * and user has NOT removed/erased the retrieved
				 * value from Dispostion, Action Date or Action
				 * Time {Legacy code: around line 1179}, display
				 * �INAPPROPRIATE RESULT CODE {Legacy code:
				 * around line 537.}� Place cursor in the screen
				 * RESULT field. Wait for user response.
				 */

				if (originalSettingResp.getCourtResult() != null && !originalSettingResp.getCourtResult().isEmpty() && originalSettingResp.getDisposition() != null && !originalSettingResp.getDisposition().isEmpty() && originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().isEmpty() && originalSettingResp.getActionTime() != null && !originalSettingResp.getActionTime().isEmpty())
				{
				    if (!originalSettingResp.getCourtResult().equalsIgnoreCase(currentSettingResp.getCourtResult()) && currentSettingResp.getCourtResult()!=null && currentSettingResp.getCourtResult().equalsIgnoreCase("OFF") && (currentSettingResp.getDisposition()!=null && !currentSettingResp.getDisposition().isEmpty() || (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true"))))
				    {
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropriate Result Code"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				}

				/**
				 * 4.9.4- detention document 1. If the Result,
				 * or Reset To, or Bar# or Attorney Name fields
				 * [unable to modify Attorney Name] are
				 * populated {even if populated with data
				 * entered prior to current session}: a)
				 * Determine if there is a juvenile master
				 * associated to the setting�s juvenile; If a
				 * Juvenile Master record is not located for the
				 * juvenile on the detention record, in the
				 * Juvenile Number field display: �CANNOT CHANGE
				 * INFORMATION � JUVENILE RECORD DOES NOT
				 * EXIST.�
				 */
				if (currentSettingResp.getCourtResult() != null && !currentSettingResp.getCourtResult().isEmpty() || (currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true") && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty()) || (currentSettingResp.getBarNum() != null && !currentSettingResp.getBarNum().isEmpty()))
				{
				    if (currentSettingJuvenileNum != null && !currentSettingJuvenileNum.isEmpty())
				    {
					boolean isJuvnileFound = JuvenileDistrictCourtHelper.isJuvenileFound(currentSettingJuvenileNum);

					if (!isJuvnileFound)
					{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Cannot Change Information - Juvenile Record Does Not Exist"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				}
			    }

			    /* 4.9.5 Detention Document (a).	If the screen Result field and screen Reset Date are populated for a setting,
			    OTES:  If user populated Reset Date, Result field must be populated.  If user populated Result field with A/R or TRN, then Reset Date must be populated.  If Reset Date is populated and Result is NOT populated, display error message: �RESULT CODE MUST BE ENTERED - CORRECT AND CONTINUE�
			    LEGAC PROCESS: If user populated the Reset To field without a RESULT value, a new hearing was created for the �Reset To� date. All attributes, except Hearing Date, Record ID, Transfer Court, Sequence Number, LCD and LCT and Reset To, are populated with data from the current hearing.*/

			    if ((currentSettingResp.getCourtResult() == null || (currentSettingResp.getCourtResult() != null && currentSettingResp.getCourtResult().isEmpty())) && (currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true") && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty()))
			    {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Result Code Must Be Entered - Correct And Continue"));
				saveErrors(aRequest, errors);
				courtHearingForm.setAction("error");
				courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventId());
				courtHearingForm.setPrevAction(courtHearingForm.getAction());
				return aMapping.findForward(UIConstants.FAILURE);
			    }

			    /*#83836 4.9.5 Detention Document c.If the current Court ID is the same as the Court ID entered in the Transfer To field and the current Hearing Date is the same as the value entered in the Reset To field,  for the same chain number, then display �CANNOT RESET TO SELECTED DATE AND COURT�.*/
			    if (courtHearingForm.getCourtId()!=null && courtHearingForm.getCourtId().equalsIgnoreCase(currentSettingResp.getTransferTo()) && currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true") && courtHearingForm.getCourtDate()!=null && courtHearingForm.getCourtDate().equalsIgnoreCase(currentSettingResp.getActionDate()))
			    {
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Cannot Reset To Selected Date And Court"));
				saveErrors(aRequest, errors);
				courtHearingForm.setAction("error");
				courtHearingForm.setCursorPosition("#transferTo-" + currentSettingResp.getDocketEventIdKey());
				courtHearingForm.setPrevAction(courtHearingForm.getAction());
				return aMapping.findForward(UIConstants.FAILURE);

			    }

			    /**
			     * NOTE: ResetDate for subsequent Detention Hearings
			     * in the same chain following a finding of Probable
			     * Cause cannot be more than 18 days in the future.
			     * Display error message: �RESET DATE CANNOT BE MORE
			     * THAN 10 DAYS BEYOND CURRENT DATE.�
			     */
			    //TODO Req Bug
			    if (currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true"))
			    {
				boolean resetDateError = false;
				Date resetDate = DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1);
				if (resetDate != null)
				{
				    Calendar cal = Calendar.getInstance();
				    cal.setTime(resetDate);
				    resetDate = cal.getTime();

				    Calendar today = Calendar.getInstance();
				    today.setTime(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
				    today.add(Calendar.DATE, 18);
				    Date todayDate18 = today.getTime();

				    if (resetDate.after(todayDate18))
				    {
					GetJuvenileCourtDecisionCodesEvent courtDisp = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
					courtDisp.setCode(currentSettingResp.getCourtResult());
					Collection<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = MessageUtil.postRequestListFilter(courtDisp, JuvenileCourtDecisionCodeResponseEvent.class);
					if (crtDecisions != null)
					{
					    Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
					    while (crtDecisionsItr.hasNext())
					    {
						JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
						if (crtDecision != null)
						{
						    if (crtDecision.getAction()!=null && crtDecision.getAction().equalsIgnoreCase("DETAINED"))
						    {
							if (crtDecision.getResetAllowed()!=null && crtDecision.getResetAllowed().equalsIgnoreCase("R"))
							{

							    resetDateError = true;
							    break;

							}
							if (currentSettingResp.getCourtResult()!=null && currentSettingResp.getCourtResult().equalsIgnoreCase("CPO") && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
							{
							    resetDateError = true;
							    break;
							}
						    }

						    if (crtDecision.getAction()!=null && crtDecision.getAction().equalsIgnoreCase("OFF DOCKET/RESET") /*&& (currentSettingResp.getTransferTo() != null && !currentSettingResp.getTransferTo().isEmpty())*/)
						    {
							if (crtDecision.getResetAllowed().equalsIgnoreCase("R"))
							{
							    resetDateError = true;
							    break;
							}
						    }
						}
					    }
					    if (resetDateError)
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Reset Date Outside Of Permissible Limits"));
						saveErrors(aRequest, errors);
						courtHearingForm.setAction("error");
						courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
						courtHearingForm.setPrevAction(courtHearingForm.getAction());
						return aMapping.findForward(UIConstants.FAILURE);
					    }
					}
				    }
				}
			    }

			    /**
			     * Validation added after discussing with Lakshmi.
			     * AS this is handled in detention side as well.
			     */
			    if (currentSettingResp.getCourtResult() != null && !currentSettingResp.getCourtResult().isEmpty() && currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true"))
			    {

				if (currentSettingResp.getTransferFlag().equalsIgnoreCase("1"))//add checkbox change
				{
				    if (currentSettingResp.getTransferTo() == null || currentSettingResp.getTransferTo() != null && currentSettingResp.getTransferTo().isEmpty())
				    {
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Transfer Court Must Be Populated - Correct And Continue"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#transferTo:" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				}

				GetJuvenileCourtDecisionCodesEvent courtDisp = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
				courtDisp.setCode(currentSettingResp.getCourtResult());
				Collection<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = MessageUtil.postRequestListFilter(courtDisp, JuvenileCourtDecisionCodeResponseEvent.class);
				if (crtDecisions != null)
				{
				    Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
				    while (crtDecisionsItr.hasNext())
				    {
					JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
					if (crtDecision != null)
					{
					    if (crtDecision.getResetAllowed() != null && crtDecision.getResetAllowed().equalsIgnoreCase("R") || crtDecision.getResetAllowed().equalsIgnoreCase("Y"))
					    {
						if (currentSettingResp.getActionDate() == null || (currentSettingResp.getActionDate() != null && currentSettingResp.getActionDate().isEmpty()))
						{
						    ActionErrors errors = new ActionErrors();
						    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Reset Date Must Be Entered - Correct And Continue"));
						    saveErrors(aRequest, errors);
						    courtHearingForm.setAction("error");
						    courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
						    courtHearingForm.setPrevAction(courtHearingForm.getAction());
						    return aMapping.findForward(UIConstants.FAILURE);
						}
						
						
						//if action Date is prior to the reset date, throw an error.
						if (currentSettingResp.getActionDate() != null  && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
						{
						    GetJJSCLDetentionByChainAndSeqNumEvent detentionEvt = (GetJJSCLDetentionByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAINANDSEQNUM);
						    detentionEvt.setChainNumber(currentSettingResp.getChainNumber());
						    dispatch.postEvent(detentionEvt);
						    CompositeResponse detentionResp = (CompositeResponse) dispatch.getReply();

						    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(detentionResp, DocketEventResponseEvent.class);
						    //Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

						    Collections.sort((List<DocketEventResponseEvent>) docketResponses, new Comparator<DocketEventResponseEvent>() {
							@Override
							public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
							{
							    if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
								return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
							    else
								return -1;
							}
						    });
						    Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
							//83836
						    while (docketResponsesItr.hasNext())
						    {
							DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
							/**
							  * #83836
							  * d.If the Court ID entered in the Transfer To field, along with the date entered in the Reset To field matches an existing record with the same Reset Date and Court ID, for the same chain number, then display �ANOTHER SETTING CURRENTLY EXISTS FOR THIS COURT AND DATE�.
							  */
							if (docResp.getCourt() != null && docResp.getCourt().equalsIgnoreCase(currentSettingResp.getTransferTo()) 
								&& currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true") 
								&& docResp.getCourtDate() != null && docResp.getCourtDate().equalsIgnoreCase(currentSettingResp.getActionDate()))
							{
							    ActionErrors errors = new ActionErrors();
							    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Another Setting currently Exists For This Court And Date"));
							    saveErrors(aRequest, errors);
							    courtHearingForm.setAction("error");
							    courtHearingForm.setCursorPosition("#transferTo-" + currentSettingResp.getDocketEventIdKey());
							    courtHearingForm.setPrevAction(courtHearingForm.getAction());
							    return aMapping.findForward(UIConstants.FAILURE);
							}
							
							//action date and time (derived value) gets persisted in the court date and court time field on update of court action //84105
							if (docResp != null && docResp.getSeqNum() != null && docResp.getSeqNum().equals("10") && !currentSettingResp.getCourt().equalsIgnoreCase("300"))
							{
							    if (docResp.getHearingType()!=null && docResp.getHearingType().equalsIgnoreCase("PC"))
							    {
								Date pcHearingDate = DateUtil.stringToDate(docResp.getCourtDate(), DateUtil.DATE_FMT_1);
								Date actionDate = DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1);
								if (actionDate.before(pcHearingDate))
								{
								    ActionErrors errors = new ActionErrors();
								    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Reset Date Cannot Precede The Court Date For The Associated Probable Cause Hearing"));
								    saveErrors(aRequest, errors);
								    courtHearingForm.setAction("error");
								    courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
								    courtHearingForm.setPrevAction(courtHearingForm.getAction());
								    return aMapping.findForward(UIConstants.FAILURE);
								}
							    }
							}
						    }
						}
					    }
					}
				    }
				}
			    }

			    /**
			     * 3.1.3.14 Determine Adjudicated Detention calendar
			     * record status Locate all Detention calendar
			     * records for the current juvenile where the
			     * assigned court is the same as the current
			     * setting�s court; and the court date as the same
			     * as the inquiry date. If a located record�s Result
			     * and HearingDecision matches the values in the
			     * screen Result and Disposition fields, the owning
			     * referral has already been adjudicated. The
			     * changes/entries to the current setting will not
			     * be saved. New as of 08/01/18: Display message,
			     * �REFERRAL WAS PREVIOUSLY ADJUDICATED.� If the
			     * screen Disposition is the same as the court ID
			     * used in the search, display �JUVENILE ALREADY HAS
			     * COURT HEARING FOR THIS DATE AND TIME.� Position
			     * the cursor in the Action Date field. Wait for
			     * user response.
			     */

			    if (currentSettingJuvenileNum != null && !currentSettingJuvenileNum.isEmpty())
			    {
				GetJJSCLDetentionByJuvNumEvent jjsdetnCrtEvent = (GetJJSCLDetentionByJuvNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYJUVNUM);
				jjsdetnCrtEvent.setJuvenileNumber(currentSettingJuvenileNum);
				dispatch.postEvent(jjsdetnCrtEvent);
				CompositeResponse clDetnResp = (CompositeResponse) dispatch.getReply();
				List<DocketEventResponseEvent> clDetnDktRespEvts = MessageUtil.compositeToList(clDetnResp, DocketEventResponseEvent.class);

				if (clDetnDktRespEvts != null && !clDetnDktRespEvts.isEmpty())
				{
				    Iterator<DocketEventResponseEvent> clDetnDktRespItr = clDetnDktRespEvts.iterator();
				    while (clDetnDktRespItr.hasNext())
				    {
					DocketEventResponseEvent respEvt = (DocketEventResponseEvent) clDetnDktRespItr.next();
					if (respEvt != null)
					{
					    if (respEvt.getCourt() != null && respEvt.getCourt().equalsIgnoreCase(currentSettingResp.getCourt()))
					    {
						if (respEvt.getCourtDate() != null && respEvt.getCourtDate().equalsIgnoreCase(currentSettingResp.getCourtDate()))
						{
						    //Referral Was Previously Adjudicated - A setting associated to the referral has a result and a disposition.
						    if (respEvt.getCourtResult()!=null && respEvt.getDisposition()!=null && respEvt.getCourtResult().equalsIgnoreCase(currentSettingResp.getCourtResult()) && respEvt.getDisposition().equalsIgnoreCase(currentSettingResp.getDisposition()))
						    {
							ActionErrors errors = new ActionErrors();
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Referral Was Previously Adjudicated"));
							saveErrors(aRequest, errors);
							courtHearingForm.setAction("error");
							courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
							courtHearingForm.setPrevAction(courtHearingForm.getAction());
							return aMapping.findForward(UIConstants.FAILURE);
						    }
						}
					    }
					   /* 83836*//**
					     * If the screen Disposition is the
					     * same as the court ID used in the
					     * search, display �JUVENILE ALREADY
					     * HAS COURT HEARING FOR THIS DATE
					     * AND TIME.� Position the cursor in
					     * the Action Date field. Wait for
					     * user response.
					     *//*
					    if (respEvt.getCourt() != null && respEvt.getCourt().equalsIgnoreCase(currentSettingResp.getTransferTo()))
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.generic", "Juvenile Already Has Court Hearing For This Date And Time"));
						saveErrors(aRequest, errors);
						courtHearingForm.setAction("error");
						courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
						courtHearingForm.setPrevAction(courtHearingForm.getAction());
						return aMapping.findForward(UIConstants.FAILURE);
					    }*/
					}
				    }
				}
			    }
			} //detention validations

			//validations for ancillary and court
			if (currentSettingResp.getDocketType().equalsIgnoreCase("ANCILLARY") || currentSettingResp.getDocketType().equalsIgnoreCase("COURT"))
			{
			    /** 83836
				 * 3.4.6.1 3) Locate the calendar record for the current
				 * setting using its Chain Number and Chain Sequence
				 * Number; determine if the information the user
				 * submitted (Date/Time/Juvenile #) is the same as the
				 * retrieved calendar record. If true, display �JUVENILE
				 * ALREADY HAS COURT HEARING FOR THIS DATE AND TIME.�
				 * Wait for user response.
				 */
				if (currentSettingResp.getActionDate() != null && currentSettingResp.getActionTime() != null && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()) && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
				{
				    if (currentSettingResp.getActionDate().equalsIgnoreCase(currentSettingResp.getCourtDate()) && currentSettingResp.getActionTime().equalsIgnoreCase(currentSettingResp.getCourtTime()))
				    {
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile Already Has Court Hearing For This Date And Time"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				}
				
			    //Legacy process}: User entered �TRN� in Result field and a valid referee court in the Disposition field. Populated File Date with reset date
			    //{JIMS2 process}: User will supply �TRN� for Result field, new court in �Transfer To� field.  Action date is also required.

			    if ((currentSettingResp.getCourtResult() != null && !currentSettingResp.getCourtResult().isEmpty()))
			    {
				if (currentSettingResp.getTransferFlag().equalsIgnoreCase("1"))//add checkbox change
				{
				    if (currentSettingResp.getTransferTo() == null || (currentSettingResp.getTransferTo() != null && currentSettingResp.getTransferTo().isEmpty()))
				    {
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Enter A Valid Court (313, 314, 315) In The Transfer To Field"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#transferTo-" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				    else
				    {
					String validCourts[] = {"313", "314", "315" };
					//generic case:
					if (!Arrays.asList(validCourts).contains(currentSettingResp.getTransferTo()))
					{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Court Id. Please Correct and Retry"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    //courtHearingForm.setCursorPosition("#transferTo-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				}
			    }

			    /**
			     * 3.4.6.3 1)a Activity: Validate Ancillary or Court
			     * calendar record entry/update {tested with records
			     * from 315/05-06-2016; 314/05-06-2015} 1) If screen
			     * Result and/or Disposition, and the Action Date
			     * are populated. And the screen Result�s and/or
			     * DispositionOption = �R� (reset) and � a) If
			     * screen Action Time is not populated, displays
			     * �FILE TIME MISSING, PLEASE CORRECT AND RETRY.�
			     * Wait for user response
			     */
			    if ((!currentSettingResp.getCourtResult().isEmpty() || !currentSettingResp.getDisposition().isEmpty()) && currentSettingResp.getActionDate()!=null && !currentSettingResp.getActionDate().isEmpty() && currentSettingResp.getActionTime().isEmpty())
			    {
				GetJuvenileDispositionCodeEvent courtDisp = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
				courtDisp.setCode(currentSettingResp.getCourtResult());
				CompositeResponse dispCompResp = postRequestEvent(courtDisp);
				Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
				if (juvDisp != null)
				{
				    Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvDisp.iterator();
				    if (juvCodeIter.hasNext())
				    {
					JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
					if (dispResp != null && dispResp.getOptionCode()!=null && dispResp.getOptionCode().equalsIgnoreCase("R"))
					{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "File Time Missing, Correct and Retry"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				}
			    }

			    /**
			     * * 3.4.6.3 - original docket comparison.
			     */

			    if (originalSettingResp != null)
			    {
				/**
				 * 3.4.6.5 If the Action Date and Action Time
				 * entered are the same as the current setting�s
				 * information (courtdate and time)and the
				 * Transfer To field contains the same value as
				 * the current setting�s CourtID, user is
				 * attempting to reset record to the exact same
				 * date and time, display: 'JUVENILE ALREADY HAS
				 * COURT HEARING FOR THIS 'DATE AND TIME.
				 * Position the cursor in Action Date field.
				 * Wait for user response.
				 */

				if (originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().isEmpty()&& originalSettingResp.getActionTime() != null && !originalSettingResp.getActionTime().isEmpty() && originalSettingResp.getTransferTo() != null)
				{
				    if (currentSettingResp.getActionDate() != null && currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getCourtDate())
					    && currentSettingResp.getActionTime() != null && currentSettingResp.getActionTime().equalsIgnoreCase(originalSettingResp.getCourtTime()) 
					    && currentSettingResp.getTransferTo() != null && currentSettingResp.getTransferTo().equalsIgnoreCase(originalSettingResp.getCourt()))
				    {
					courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile Already Has Court Hearing For This Date And Time"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				}

				/**
				 * 3.4.6.3 2) 08/10/18: If Result AND Action
				 * Date/Time fields were originally populated
				 * during retrieval; and, user has removed the
				 * original value in the Result field and,
				 * Result field has not been repopulated,
				 * display �RESULT VALUE MISSING, CORRECT AND
				 * RETRY.� Posititon cursor in the Result field.
				 * Wait for user response.
				 */
				if (originalSettingResp.getCourtResult() != null && !originalSettingResp.getCourtResult().isEmpty())
				{
				    if (originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().isEmpty() && originalSettingResp.getActionTime() != null && !originalSettingResp.getActionTime().isEmpty() && (originalSettingResp.getDisposition() == null || (originalSettingResp.getDisposition() != null && originalSettingResp.getDisposition().isEmpty())))
				    {
					if (currentSettingResp.getCourtResult() == null || (currentSettingResp.getCourtResult() != null && currentSettingResp.getCourtResult().isEmpty()) && (currentSettingResp.getDisposition() == null || (currentSettingResp.getDisposition() != null && currentSettingResp.getDisposition().isEmpty())))
					{
					    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Result Value Missing, Correct And Retry"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }

				    /**
				     * 5) 08/09/18: If Result, Disposition and
				     * Action Date fields were populated during
				     * retrieval; and, user removed the
				     * retrieved Result value and has not
				     * re-populated the field; but, the original
				     * disposition continues to populate
				     * Disposition field, display �DISPOSITION
				     * VALUE MUST BE BLANK WHEN RESULT AND DATE
				     * ARE BLANK.� Position the cursor in
				     * Disposition field. Wait for user
				     * response.
				     */
				    if (originalSettingResp.getDisposition() != null && !originalSettingResp.getDisposition().isEmpty() && originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().isEmpty())
				    {
					if (currentSettingResp.getCourtResult() == null || (currentSettingResp.getCourtResult() != null && currentSettingResp.getCourtResult().isEmpty()) && currentSettingResp.getDisposition()!=null && currentSettingResp.getDisposition().equalsIgnoreCase(originalSettingResp.getDisposition()) && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty())
					{
					    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Disposition Value Must be Blank When Result And Date Are Blank"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
					/**
					 * 08/16/18: If Result, Disposition and
					 * Action Date fields were populated
					 * during retrieval, and user has
					 * changed Result to �OFF� and removed
					 * the Disposition and Action Date/Time
					 * fields, system executes logic to
					 * delete the setting. If setting is not
					 * last record in chain and, display:
					 * �ONLY LAST SETTING MAY BE DELETED �
					 * SELECT SUBMIT TO CONTINUE.�
					 * [313/07-21-2016 (201602660J)]
					 */
					if (currentSettingResp.getCourtResult()!=null && originalSettingResp.getCourtResult()!=null && !originalSettingResp.getCourtResult().equalsIgnoreCase(currentSettingResp.getCourtResult()) && currentSettingResp.getCourtResult().equalsIgnoreCase("OFF"))
					{
					    if (currentSettingResp.getDisposition() == null || (currentSettingResp.getDisposition() != null && currentSettingResp.getDisposition().isEmpty()) && (currentSettingResp.getActionDate() == null || (currentSettingResp.getActionDate() != null && currentSettingResp.getActionDate().isEmpty())) || (currentSettingResp.getActionTime() == null || (currentSettingResp.getActionTime() != null && currentSettingResp.getActionTime().isEmpty())))
					    {
						if (currentSettingResp.getChainNumber() != null && currentSettingResp.getDocketType().equalsIgnoreCase("COURT"))
						{

						    GetJJSCLCourtByChainAndSeqNumEvent courtEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
						    courtEvt.setChainNumber(currentSettingResp.getChainNumber());
						    dispatch.postEvent(courtEvt);
						    CompositeResponse courtResp = (CompositeResponse) dispatch.getReply();
						    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtResp, DocketEventResponseEvent.class);

						    Collections.sort((List<DocketEventResponseEvent>) docketResponses, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
							@Override
							public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
							{
							    if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
								return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
							    else
								return -1;
							}
						    }));
						    Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

						    while (docketResponsesItr.hasNext())
						    {
							DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
							//action date and time (derived value) gets persisted in the court date and court time field on update of court action
							if (docResp != null && !docResp.getSeqNum().equals(Integer.parseInt(currentSettingResp.getSeqNum()) + 10))
							{
							    ActionErrors errors = new ActionErrors();
							    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Only Last Setting May Be Deleted - Select Submit To Continue"));
							    saveErrors(aRequest, errors);
							    courtHearingForm.setAction("error");
							    courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
							    courtHearingForm.setPrevAction(courtHearingForm.getAction());
							    return aMapping.findForward(UIConstants.FAILURE);
							}
						    }//while
						}

						if (currentSettingResp.getChainNumber() != null && currentSettingResp.getDocketType().equalsIgnoreCase("ANCILLARY"))
						{
						    GetJJSCLAncillaryCourtByChainAndSeqNumEvent ancillaryEvt = (GetJJSCLAncillaryCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYCHAINANDSEQNUM);
						    ancillaryEvt.setChainNumber(currentSettingResp.getChainNumber());
						    dispatch.postEvent(ancillaryEvt);
						    CompositeResponse ancillaryResp = (CompositeResponse) dispatch.getReply();
						    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(ancillaryResp, DocketEventResponseEvent.class);

						    Collections.sort((List<DocketEventResponseEvent>) docketResponses, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
							@Override
							public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
							{
							    if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
								return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
							    else
								return -1;
							}
						    }));

						    Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
						    while (docketResponsesItr.hasNext())
						    {
							DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
							if (docResp != null && !docResp.getSeqNum().equals(Integer.parseInt(currentSettingResp.getSeqNum()) + 10))
							{
							    ActionErrors errors = new ActionErrors();
							    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Only Last Setting May Be Deleted - Select Submit To Continue"));
							    saveErrors(aRequest, errors);
							    courtHearingForm.setAction("error");
							    courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
							    courtHearingForm.setPrevAction(courtHearingForm.getAction());
							    return aMapping.findForward(UIConstants.FAILURE);
							}
						    }//while
						}
					    }
					}
				    } //disposition,action date,results check

				    /**
				     * 14)08/09/18; If Result and Disposition
				     * were originally populated during
				     * retrieval�and user has removed only the
				     * Disposition value and has not
				     * re-populated the screen Disposition
				     * (tested with 201401872J, REV and CCP),
				     * display �INAPPROPRIATE RESULT;
				     * DISPOSITION AND ACTION DATE ARE BLANK.�
				     * Position cursor in screen Results field.
				     * Wait for user response.
				     */

				    if (originalSettingResp.getDisposition() != null && !originalSettingResp.getDisposition().isEmpty() && originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().isEmpty())
				    {
					if (currentSettingResp.getDisposition() == null || (currentSettingResp.getDisposition() != null && currentSettingResp.getDisposition().isEmpty()))
					{
					    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Result. Disposition And Action Date Are Blank"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				} //original court result not empty check

				/**
				 * 8) If the Disposition and Action Date were
				 * originally populated during retrieval, and
				 * screen Result was not and has not been
				 * populated, and user has removed original
				 * Disposition, display �DISPOSITION VALUE
				 * MISSING.� Position cursor in the Disposition
				 * field. Wait for user response.
				 */
				if (originalSettingResp.getCourtResult() == null || (originalSettingResp.getCourtResult() != null && originalSettingResp.getCourtResult().isEmpty()))
				{
				    if (originalSettingResp.getDisposition() != null && !originalSettingResp.getDisposition().isEmpty() && originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().isEmpty())
				    {
					if (currentSettingResp.getDisposition() == null || (currentSettingResp.getDisposition() != null && currentSettingResp.getDisposition().isEmpty()))
					{
					    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Disposition Value Missing"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				} //end if 

				/**
				 * 9) 08/10/2018: If user has entered �TRN� as
				 * Result {not populated during retrieval} and
				 * Action Date was not populated with retrieval
				 * and user has not populated Action Date,
				 * display �DATE VALUE MISSING, PLEASE CORRECT
				 * AND RETRY.� Wait for user response.
				 */
				if (originalSettingResp.getCourtResult() == null || (originalSettingResp.getCourtResult() != null && originalSettingResp.getCourtResult().isEmpty()) && originalSettingResp.getActionDate() == null || (originalSettingResp.getActionDate() != null && originalSettingResp.getActionDate().isEmpty()))
				{
				    if (currentSettingResp.getCourtResult() != null && currentSettingResp.getTransferFlag().equalsIgnoreCase("1") && currentSettingResp.getActionDate()!=null && currentSettingResp.getActionDate().isEmpty())//add checkbox change
				    {
					courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Date Value Missing, Please Correct And Retry"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				}

				/**
				 * If the Action date occurs before the inquiry
				 * Court Date AND if the screen Result is not an
				 * Administrative Reset (A/R) or a Transfer
				 * (TRN), display: DATE CANNOT BE LESS THAN
				 * {inquiry date} IF RESULT IS NOT
				 * ADMINISTRATIVE RESET (A/R) OR TRANSFER (TRN).
				 * Position the cursor in Action Date field.
				 * Wait for user response. NOTE: Only a judge�s
				 * reset (A/R or TRN) request can be backdated.
				 * Attorney�s reset requests cannot be
				 * backdated.
				 */
				if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && currentSettingResp.getCourtDate() != null && !currentSettingResp.getCourtDate().isEmpty())
				{
				    if (DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1).before(DateUtil.stringToDate(currentSettingResp.getCourtDate(), DateUtil.DATE_FMT_1)))
				    {
					if (currentSettingResp.getCourtResult() != null && (!currentSettingResp.getCourtResult().equalsIgnoreCase("A/R") && !currentSettingResp.getTransferFlag().equalsIgnoreCase("1")))//add checkbox change
					{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Date Cannot Be Less Than " + currentSettingResp.getCourtDate() + ". If Result Is Not Administrative(A/R) Or Transfer(TRN)"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#actionDate-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
				    }
				}

				/**
				 * 10) 08/10/2018: If user has entered �TRN� as
				 * Result {not populated during retrieval} and
				 * Action Time was not populated with retrieval
				 * and user has not populaated Action Time,
				 * display �FILE TIME MISSING, PLEASE CORRECT
				 * AND RETRY.� Position curson in the Action
				 * Time field. Wait for user response.
				 */
				if (originalSettingResp.getCourtResult() == null || (originalSettingResp.getCourtResult() != null && originalSettingResp.getCourtResult().isEmpty()) && originalSettingResp.getActionTime() == null || (originalSettingResp.getActionTime() != null && originalSettingResp.getActionTime().isEmpty()))
				{
				    if (currentSettingResp.getCourtResult() != null && currentSettingResp.getTransferFlag().equalsIgnoreCase("1"))//add checkbox change
				    {
					if (currentSettingResp.getActionTime() != null && currentSettingResp.getActionTime().isEmpty())
					{
					    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "File Time Missing, Please Correct And Retry"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#actionTime-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}
					/**
					 * 11) 08/10/2018: If screen Result
					 * contains =�TRN� and the Action Date
					 * and Time are populated, and
					 * TransferTo/Transfer Court is a valid
					 * juvenile Referee court, display �ONLY
					 * A DETENTION HEARING CAN BE
					 * TRANSFERRED TO REFEREE COURT.�
					 * Position cursor in
					 * TransferTo/Transfer Court field. Wait
					 * for user response.
					 */
					if (currentSettingResp.getActionDate()!=null && currentSettingResp.getActionTime()!=null && !currentSettingResp.getActionTime().isEmpty() && !currentSettingResp.getActionDate().isEmpty() && currentSettingResp.getTransferTo() != null && !currentSettingResp.getTransferTo().isEmpty())
					{
					    String refereeCourts[] = { "001", "002" };
					    if (Arrays.asList(refereeCourts).contains(currentSettingResp.getTransferTo()))
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Only A Detention Hearing Can Be Transferred To Referee Court."));
						saveErrors(aRequest, errors);
						courtHearingForm.setAction("error");
						courtHearingForm.setCursorPosition("#transferTo-" + currentSettingResp.getDocketEventIdKey());
						courtHearingForm.setPrevAction(courtHearingForm.getAction());
						return aMapping.findForward(UIConstants.FAILURE);
					    }
					}
				    }
				}

				/**
				 * 12) A) User has populated screen Result =
				 * �TRN� {Result was not populated during
				 * retrieval} and TransferTo/Transfer Court is
				 * populated with a valid juvenile court and has
				 * Disposition field is populated with a valid
				 * hearing decision, display �INVALID
				 * DISPOSITION FOR TRANSFER, PLEASE CORRECT AND
				 * RETRY� Position cursor in Disposition field.
				 * Wait for user response. {Scenario only in
				 * JIMS2; modified legacy process of identifying
				 * Transfers}
				 */
				String validJuvenileCourts[] = { "313", "314", "315", "300" };
				if (originalSettingResp.getCourtResult() != null && currentSettingResp.getCourtResult() != null && (!originalSettingResp.getCourtResult().equalsIgnoreCase("TRN") && currentSettingResp.getTransferFlag().equalsIgnoreCase("1")))//add checkbox change check if current docket has transfer to value 
				{
				    if (currentSettingResp.getTransferTo() != null && !currentSettingResp.getTransferTo().isEmpty() && Arrays.asList(validJuvenileCourts).contains(currentSettingResp.getTransferTo()) && currentSettingResp.getDisposition() != null && !currentSettingResp.getDisposition().isEmpty())
				    {
					courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Disposition For Transfer, Please Correct and Retry"));
					saveErrors(aRequest, errors);
					courtHearingForm.setAction("error");
					courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
					courtHearingForm.setPrevAction(courtHearingForm.getAction());
					return aMapping.findForward(UIConstants.FAILURE);
				    }
				}

				/**
				 * 12th B )- Requirement changed while
				 * implementing. Check user-story 59852
				 */
				if (currentSettingResp.getCourtResult() != null && !currentSettingResp.getCourtResult().isEmpty() && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && currentSettingResp.getDisposition() != null && !currentSettingResp.getDisposition().isEmpty())
				{
				    // court Decision Code table.
				    GetJuvenileDispositionCodeEvent dispositionEvt = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
				    dispositionEvt.setCode(currentSettingResp.getDisposition());
				    CompositeResponse dispCompResp = postRequestEvent(dispositionEvt);
				    Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
				    if (juvDisp != null)
				    {
					Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvDisp.iterator();
					if (juvCodeIter.hasNext())
					{
					    JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
					    /**
					     * 1. If the Option of the screen
					     * Disposition is �A� or, �R�,
					     * display: INAPPROPRIATE
					     * DISPOSITION, OPTION CANNOT BE 'A'
					     * or 'R�. Position cursor in
					     * Disposition field. Wait for user
					     * response.
					     */
					    if (dispResp != null && dispResp.getOptionCode()!=null && (dispResp.getOptionCode().equalsIgnoreCase("A") || dispResp.getOptionCode().equalsIgnoreCase("R")))
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Disposition, Option Cannot Be 'A' or 'R'"));
						saveErrors(aRequest, errors);
						courtHearingForm.setAction("error");
						courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
						courtHearingForm.setPrevAction(courtHearingForm.getAction());
						return aMapping.findForward(UIConstants.FAILURE);
					    }

					    /*3.4.6.3
					     *  2.	If the current setting is a Court calendar record, AND the Category associated to the value in the screen Disposition field is(U.S. 59852)  not  �N� or �P� or �B� or �C� or �0,� display:  INAPPROPRIATE DISPOSITION, CATEGORY MUST BE �N�, �P', 'B', 'C' OR '0�. Position cursor in Disposition field.  Wait for user response.  
					     {313  09202016 and Petiton# 201603979J)*/

					    if (currentSettingResp.getDocketType().equalsIgnoreCase("COURT") && dispResp != null && dispResp.getCategoryCode() != null 
						    && (!dispResp.getCategoryCode().equalsIgnoreCase("N") && !dispResp.getCategoryCode().equalsIgnoreCase("P") 
							    && !dispResp.getCategoryCode().equalsIgnoreCase("B") && !dispResp.getCategoryCode().equalsIgnoreCase("C") && !dispResp.getCategoryCode().equalsIgnoreCase("0")))
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Disposition, Category Must Be 'N','P','B','C' or '0'"));
						saveErrors(aRequest, errors);
						courtHearingForm.setAction("error");
						courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
						courtHearingForm.setPrevAction(courtHearingForm.getAction());
						return aMapping.findForward(UIConstants.FAILURE);
					    }

					    /**
					     * 12 C) 3. If the current setting
					     * is an Ancillary calendar record
					     * AND the Category associated to
					     * the value in the screen
					     * Disposition field is not �A� or
					     * �B� or �C� or �0,� display:
					     * INAPPROPRIATE DISPOSITION,
					     * CATEGORY MUST BE 'A', 'B', 'C' OR
					     * '0'. Position cursor in
					     * Disposition field. Wait for user
					     * response. NOTE: Entries on the
					     * COURT DECISION code table MUST
					     * have a value in the CATEGORY;
					     * blank is no longer possible.
					     */
					    if (currentSettingResp.getDocketType().equalsIgnoreCase("ANCILLARY") && dispResp != null 
						    && (dispResp.getCategoryCode()!=null && !dispResp.getCategoryCode().equalsIgnoreCase("A") && !dispResp.getCategoryCode().equalsIgnoreCase("B") 
							    && !dispResp.getCategoryCode().equalsIgnoreCase("C") && !dispResp.getCategoryCode().equalsIgnoreCase("0")))
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Disposition, Category Must Be 'A', 'B', 'C' Or '0'"));
						saveErrors(aRequest, errors);
						courtHearingForm.setAction("error");
						courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
						courtHearingForm.setPrevAction(courtHearingForm.getAction());
						return aMapping.findForward(UIConstants.FAILURE);
					    }
					}
				    }
				}

				/**
				 * 3.4.6.3 13)08/10/18: If screen Action
				 * Date/Time and Result were populated during
				 * original retrieval, but user has replaced
				 * original Result, locate the Option associated
				 * to the screen Result field value AND�
				 * a.08/10/18�If the Option is not �A� or �R,�
				 * display �INAPPROPRIATE RESULT, MUST HAVE
				 * OPTION VALUE OF, ''A'', ��R.� Wait for user
				 * response. NOTE: Entries on the COURT DECISION
				 * code table MUST have an OPTION value; blank
				 * is no longer possible.
				 */
				if (originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().isEmpty() && originalSettingResp.getActionTime() != null && !originalSettingResp.getActionTime().isEmpty() && originalSettingResp.getCourtResult() != null)
				{
				    //bug #81255
				    if (currentSettingResp.getCourtResult()!=null && !currentSettingResp.getCourtResult().isEmpty() && !originalSettingResp.getCourtResult().equalsIgnoreCase(currentSettingResp.getCourtResult()))
				    {

					/**
					 * 3) If Result AND Action Date/Time
					 * fields were populated during
					 * retrieval; Result has been changed to
					 * �OFF�; and {IF $VERIFY (%RESET.IND
					 * (%LINE.NUM), �NAB�*} user has NOT
					 * removed/erased the retrieved value
					 * from Action Date {Legacy code: around
					 * line 1179} �display �INAPPROPRIATE
					 * RESULT CODE.� Place cursor in the
					 * screen RESULT field. Wait for user
					 * response.
					 */
					if (currentSettingResp.getCourtResult()!=null && currentSettingResp.getCourtResult().equalsIgnoreCase("OFF") && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty())
					{
					    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
					    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Result Code"));
					    saveErrors(aRequest, errors);
					    courtHearingForm.setAction("error");
					    courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
					    courtHearingForm.setPrevAction(courtHearingForm.getAction());
					    return aMapping.findForward(UIConstants.FAILURE);
					}

					GetJuvenileDispositionCodeEvent resultEvt = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
					resultEvt.setCode(currentSettingResp.getCourtResult());
					CompositeResponse resultCompResp = postRequestEvent(resultEvt);
					Collection<JuvenileDispositionCodeResponseEvent> juvResult = MessageUtil.compositeToCollection(resultCompResp, JuvenileDispositionCodeResponseEvent.class);
					if (juvResult != null)
					{
					    Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvResult.iterator();
					    if (juvCodeIter.hasNext())
					    {
						JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
						/**
						 * 3.6.4.3 13)a If the Option of
						 * the screen Disposition is �A�
						 * or, �R�, display:
						 * INAPPROPRIATE DISPOSITION,
						 * OPTION CANNOT BE 'A' or 'R�.
						 * Position cursor in
						 * Disposition field. Wait for
						 * user response.
						 */
						if (dispResp != null && dispResp.getOptionCode()!=null && !dispResp.getOptionCode().equalsIgnoreCase("A") && !dispResp.getOptionCode().equalsIgnoreCase("R"))
						{
						    courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
						    courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
						    ActionErrors errors = new ActionErrors();
						    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Result, Must Have Option Value Of 'A','R'"));
						    saveErrors(aRequest, errors);
						    courtHearingForm.setAction("error");
						    courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
						    courtHearingForm.setPrevAction(courtHearingForm.getAction());
						    return aMapping.findForward(UIConstants.FAILURE);
						}

						/**
						 * 3.4.6.3 13) b.08/14/18�COURT
						 * ONLY�If the current setting
						 * is a Court calendar record,
						 * result�s Option = A, or R,
						 * AND the Category is not �N�
						 * or �P� or �B� or �C� or �0,�
						 * display �INAPPROPRIATE
						 * RESULT, CATEGORY MUST BE �N�,
						 * ''P'', ''B'', ''C'' OR ''0.''
						 * Position cursor in screen
						 * Results field. Wait for user
						 * response. NOTE: Entries on
						 * the COURT DECISION code table
						 * MUST have a value in the
						 * CATEGORY; blank is no longer
						 * possible. Created decision
						 * code �ZZZ� to test.
						 */
						if (currentSettingResp.getDocketType().equalsIgnoreCase("COURT") && dispResp != null && dispResp.getOptionCode()!=null && (dispResp.getOptionCode().equalsIgnoreCase("A") || dispResp.getOptionCode().equalsIgnoreCase("R")))
						{
						    if (dispResp.getCategoryCode()!=null && !dispResp.getCategoryCode().equalsIgnoreCase("N") 
							    && !dispResp.getCategoryCode().equalsIgnoreCase("P")
							    && !dispResp.getCategoryCode().equalsIgnoreCase("B") 
							    && !dispResp.getCategoryCode().equalsIgnoreCase("C") && !dispResp.getCategoryCode().equalsIgnoreCase("0"))
						    {
							courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
							courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
							ActionErrors errors = new ActionErrors();
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Result, Category Must Be 'N','P','B','C' or '0'"));
							saveErrors(aRequest, errors);
							courtHearingForm.setAction("error");
							courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
							courtHearingForm.setPrevAction(courtHearingForm.getAction());
							return aMapping.findForward(UIConstants.FAILURE);
						    }
						}

						/**
						 * 3.4.6.3 13)
						 * C.08/10/18�ANCILLARY ONLY: If
						 * the current setting is an
						 * Ancillary calendar record,
						 * result�s Option = A, R or O,
						 * AND the Category is not ��A�
						 * or �B� or �C� or �0,� display
						 * �INAPPROPRIATE RESULT,
						 * CATEGORY MUST BE, ''A'',
						 * ''B'', ''C'' OR ''0.''
						 * Position cursor in screen
						 * Results field. Wait for user
						 * response. NOTE: Entries on
						 * the COURT DECISION code table
						 * MUST have an OPTION value;
						 * blank is no longer possible.
						 */
						if (currentSettingResp.getDocketType().equalsIgnoreCase("ANCILLARY") && dispResp != null && dispResp.getOptionCode()!=null && 
							(dispResp.getOptionCode().equalsIgnoreCase("A") || dispResp.getOptionCode().equalsIgnoreCase("R") || dispResp.getOptionCode().equalsIgnoreCase("O")))
						{
						    if (dispResp.getCategoryCode()!=null && !dispResp.getCategoryCode().equalsIgnoreCase("A") 
							    && !dispResp.getCategoryCode().equalsIgnoreCase("B") 
							    && !dispResp.getCategoryCode().equalsIgnoreCase("C") 
							    && !dispResp.getCategoryCode().equalsIgnoreCase("0"))
						    {
							courtHearingForm.setDcktEvtId(currentSettingResp.getDocketEventId());
							courtHearingForm.setDcktEvtIdKey(currentSettingResp.getDocketEventIdKey());
							ActionErrors errors = new ActionErrors();
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Inappropiate Result, Category Must Be ''A'', ''B'', ''C'' OR ''0''"));
							saveErrors(aRequest, errors);
							courtHearingForm.setAction("error");
							courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
							courtHearingForm.setPrevAction(courtHearingForm.getAction());
							return aMapping.findForward(UIConstants.FAILURE);
						    }
						}// end if
					    }
					} //disp if
				    }// if
				} //action date,time, result not null check (13th)

				/**
				 * 15) COURT ONLY: If the Option for the screen
				 * Disposition = �F� AND the Option for the
				 * result is not �A�: Locate the calendar record
				 * for the setting by ChainNumber and
				 * ChainSequenceNumber. Retrieve the Petition
				 * Allegation, referral number and juvenile
				 * number associated to the calendar record. If
				 * the Petition Allegation is not �CHCUST
				 * (Change of Custody)", Locate the Referral
				 * record associated, by juvenile number and
				 * referral number, to the calendar record.
				 * Retrieve the Decision Type (CourtResultType)
				 * from the referral record. If the Decision
				 * type (CourtResultType) is not �A,� display
				 * �NO ADJUDICATION FOUND.� Wait for user
				 * response.
				 */
				if (currentSettingResp.getDocketType().equalsIgnoreCase("COURT"))
				{
				    /**
				     * 4) 09/06/18: If there is not referral
				     * record for the the setting�s identified
				     * referral, display �REFERRAL (referral #)
				     * NOT FOUND FOR THIS JUVENILE.�
				     */
				    if (currentSettingJuvenileNum != null && currentSettingResp.getReferralNum() != null)
				    {
					//String juvenileNum = currentSettingResp.getJuvenileNumber().substring(1, currentSettingResp.getJuvenileNumber().length());
					boolean doesReferralExists = JuvenileDistrictCourtHelper.doesReferralExists(currentSettingResp.getReferralNum(), currentSettingJuvenileNum);
					//referral not exist and user modifies any of those fields.
					if (!doesReferralExists)
					{
					    if ((originalSettingResp.getCourtResult() == null && currentSettingResp.getCourtResult()!=null && !currentSettingResp.getCourtResult().isEmpty()) 
						    || (originalSettingResp.getCourtResult() != null && !originalSettingResp.getCourtResult().equalsIgnoreCase(currentSettingResp.getCourtResult())) 
						    || ((originalSettingResp.getDisposition() == null && currentSettingResp.getDisposition()!=null && !currentSettingResp.getDisposition().isEmpty()) 
						    || (originalSettingResp.getDisposition() != null && !originalSettingResp.getDisposition().equalsIgnoreCase(currentSettingResp.getDisposition()))) 
						    || ((originalSettingResp.getTransferTo() == null && currentSettingResp.getTransferTo()!=null && !currentSettingResp.getTransferTo().isEmpty()) 
						    || (originalSettingResp.getTransferTo() != null && !originalSettingResp.getTransferTo().equalsIgnoreCase(currentSettingResp.getTransferTo()))) 
						    || ((originalSettingResp.getActionDate() == null && currentSettingResp.getActionDate()!=null && !currentSettingResp.getActionDate().isEmpty())
						    || (originalSettingResp.getActionDate() != null && !originalSettingResp.getActionDate().equalsIgnoreCase(currentSettingResp.getActionDate()))) 
						    || ((originalSettingResp.getActionTime() == null && currentSettingResp.getActionTime()!=null && !currentSettingResp.getActionTime().isEmpty()) 
						    || (originalSettingResp.getActionTime() != null && !originalSettingResp.getActionTime().equalsIgnoreCase(currentSettingResp.getActionTime()))) 
						    || ((originalSettingResp.getHearingType() == null && currentSettingResp.getHearingType()!=null && !currentSettingResp.getHearingType().isEmpty()) 
						    || (originalSettingResp.getHearingType() != null && !originalSettingResp.getHearingType().equalsIgnoreCase(currentSettingResp.getHearingType()))))
					    {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Referral (" + currentSettingResp.getReferralNum() + ") Not Found For This Juvenile"));
						saveErrors(aRequest, errors);
						courtHearingForm.setAction("error");
						courtHearingForm.setCursorPosition("#courtResult-" + currentSettingResp.getDocketEventIdKey());
						courtHearingForm.setPrevAction(courtHearingForm.getAction());
						return aMapping.findForward(UIConstants.FAILURE);
					    }
					}
				    }

				    if (currentSettingResp.getCourtResult()!=null && !currentSettingResp.getCourtResult().isEmpty() && currentSettingResp.getDisposition()!=null && !currentSettingResp.getDisposition().isEmpty())
				    {
					JuvenileProfileReferralListResponseEvent profileRefResp = null;
					//court Result
					GetJuvenileDispositionCodeEvent resultEvt = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
					resultEvt.setCode(currentSettingResp.getCourtResult());
					CompositeResponse resultCompResp = postRequestEvent(resultEvt);
					Collection<JuvenileDispositionCodeResponseEvent> juvResult = MessageUtil.compositeToCollection(resultCompResp, JuvenileDispositionCodeResponseEvent.class);

					//disposition
					GetJuvenileDispositionCodeEvent dispositionEvt = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
					dispositionEvt.setCode(currentSettingResp.getDisposition());
					CompositeResponse dispCompResp = postRequestEvent(dispositionEvt);
					Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
					if (juvDisp != null && juvResult != null && juvResult.size() == juvDisp.size())
					{
					    Iterator<JuvenileDispositionCodeResponseEvent> juvResultIter = juvResult.iterator();
					    Iterator<JuvenileDispositionCodeResponseEvent> dispositionsItr = juvDisp.iterator();
					    if (juvResultIter.hasNext())
					    {
						JuvenileDispositionCodeResponseEvent courtResult = juvResultIter.next();
						JuvenileDispositionCodeResponseEvent disp = dispositionsItr.next();

						/*15)	3.4.6.3 COURT ONLY: If the Option for the screen Disposition = �F� AND the Option for the result is not �A�: //changed this check to 99 task 183246
						    Locate the calendar record for the setting by ChainNumber and ChainSequenceNumber.  
						    Retrieve the Petition Allegation, referral number and juvenile number associated to the calendar record.

						    If the Petition Allegation is not �CHCUST (Change of Custody)", 	
						    Locate the Referral record associated, by juvenile number and referralnumber, to the calendar record.
						    Retrieve the Decision Type (CourtResultType) from the referral record. 
						    If the Decision type (CourtResultType) is not �A//changed this check to 99 task 183246 ,� display �NO ADJUDICATION FOUND.�  Wait for user response.*/

						//if (disp != null && disp.getOptionCode()!=null  && (disp.getOptionCode().equalsIgnoreCase("F") && courtResult!=null && courtResult.getOptionCode()!=null &&  !courtResult.getOptionCode().equalsIgnoreCase("A")))
						if (disp != null && disp.getOptionCode()!=null  && (disp.getOptionCode().equalsIgnoreCase("F") && courtResult!=null && courtResult.getOptionCode()!=null && !courtResult.getOptionCode().equalsIgnoreCase("A")&&  !courtResult.getCodeNum().equalsIgnoreCase("99")))
						{
						    GetJJSCLCourtByChainAndSeqNumEvent courtEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
						    courtEvt.setChainNumber(currentSettingResp.getChainNumber());
						    courtEvt.setSeqNumber(currentSettingResp.getSeqNum());
						    dispatch.postEvent(courtEvt);
						    CompositeResponse courtResp = (CompositeResponse) dispatch.getReply();
						    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtResp, DocketEventResponseEvent.class);
						    Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

						    while (docketResponsesItr.hasNext())
						    {
							DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
							if (docResp.getJuvenileNumber() != null && docResp.getReferralNum() != null)
							{
							    // Get petition
							    GetJJSPetitionsEvent petitionEvent = new GetJJSPetitionsEvent();
							    petitionEvent.setJuvenileNum(docResp.getJuvenileNumber());
							    petitionEvent.setReferralNum(docResp.getReferralNum());
							    Iterator<JJSPetition> petitionIter = JJSPetition.findAll(petitionEvent);
							    if (petitionIter != null && petitionIter.hasNext())
							    {
								JJSPetition petition = petitionIter.next();
								if (petition != null)
								{
								    if (petition.getOffenseCodeId() != null && !petition.getOffenseCodeId().equalsIgnoreCase("CHCUST"))
								    {
									profileRefResp = JuvenileFacilityHelper.getJuvReferralDetails(docResp.getJuvenileNumber(), docResp.getReferralNum());
									if (profileRefResp != null && profileRefResp.getCourtResultDisposition() != null)
									{
									    JuvenileDispositionCodeResponseEvent juvdisp = profileRefResp.getCourtResultDisposition();
									    if (juvdisp != null)
									    {
										//if (juvdisp.getOptionCode() != null && !juvdisp.getOptionCode().equalsIgnoreCase("A"))
										if (juvdisp.getCodeNum() != null && !juvdisp.getCodeNum().equalsIgnoreCase("99"))
										{
										    ActionErrors errors = new ActionErrors();
										    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No Adjudication Found"));
										    saveErrors(aRequest, errors);
										    courtHearingForm.setAction("error");
										    courtHearingForm.setCursorPosition("#disposition-" + currentSettingResp.getDocketEventIdKey());
										    courtHearingForm.setPrevAction(courtHearingForm.getAction());
										    return aMapping.findForward(UIConstants.FAILURE);
										}
									    }
									}
								    }
								}
							    }
							} //juv num check
						    }// end of while
						}//if disp check
					    }
					}
				    }
				}// court only check 15 th and 16th (a)
			    } //original docket null check 

			    /********************************************** Validation Complete ***************************************************************************************/
			    //    boolean isNewCourtRecordCreated = false;
			    //    boolean isNewAncillaryRecordCreated = false;

			    //    boolean isSubsequentCourtRecordAvail = false;
			    //    boolean isSubsequentAncillaryRecordAvail = false;

			    boolean isResetForCourt = false;
			    boolean isResetForAncillary = false;

			    /**
			     * NOTE: In legacy, JUVENILE_JJS_DETENTION_HEARING.
			     * AlternateSettingIndicator is not populated as
			     * part of any system-initiated process; it can be
			     * populated only through a Correction screen. If
			     * the Option value = �R� for a Result or
			     * Disposition, a new calendar record (of the same
			     * record type) must be created. All Calendar record
			     * types: Chain number is the same as Chain number
			     * in current setting. Chain Sequence number: find
			     * the last setting record in the current chain, Add
			     * +10 to the located setting�s Chain Sequence
			     * Number. Resulting value becomes new settings
			     * Chain Sequence number. ResetHearingType = current
			     * setting�s PetitionType if user has not supplied a
			     * value. LastChangeDate/Time = current system
			     * date/time LastChangeUser = current session user
			     * HearingCategory: Category for Disposition always
			     * takes precedence over the category associated to
			     * a Result value.
			     */
			    GetJuvenileDispositionCodeEvent dispositionEvt = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
			    JuvenileDispositionCodeResponseEvent resultDisp = null;
			    JuvenileDispositionCodeResponseEvent dispResp = null;

			    //  if(currentSettingResp.getCourtResult()!=null && (currentSettingResp.getCourtResult().equalsIgnoreCase("RST") || !currentSettingResp.getCourtResult().equalsIgnoreCase(originalSettingResp.getCourtResult()))){
			    //check for result.
			    if(currentSettingResp.getCourtResult()!=null&&!currentSettingResp.getCourtResult().isEmpty())
			    {
        			    dispositionEvt.setCode(currentSettingResp.getCourtResult());
        			    dispatch.postEvent(dispositionEvt);
        			    CompositeResponse dispCompResp = (CompositeResponse) dispatch.getReply();
        			    Collection<JuvenileDispositionCodeResponseEvent> juvResult = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
        
        			    if (juvResult != null)
        			    {
        				Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvResult.iterator();
        				if (juvCodeIter.hasNext())
        				{
        				    resultDisp = juvCodeIter.next();
        				    if (resultDisp != null)
        				    {
        					if (resultDisp.getOptionCode()!=null && resultDisp.getOptionCode().equalsIgnoreCase("R"))
        					{
        					    if (currentSettingResp.getDocketType().equalsIgnoreCase("COURT"))
        					    {
        						isResetForCourt = true;
        					    }
        
        					    if (currentSettingResp.getDocketType().equalsIgnoreCase("ANCILLARY"))
        					    {
        						isResetForAncillary = true; //ancillary
        					    }
        					}
        				    }
        				}
        			    }
			    }
			    //  }
			    //    if (currentSettingResp.getDisposition() != null && !currentSettingResp.getDisposition().equalsIgnoreCase(originalSettingResp.getDisposition()))
			    //    {
			    // for disposition
			    if (currentSettingResp.getDisposition() != null && !currentSettingResp.getDisposition().isEmpty())//!currentSettingResp.getDisposition().equalsIgnoreCase(originalSettingResp.getDisposition()))
			    {
        			    dispositionEvt.setCode(currentSettingResp.getDisposition());
        			    dispatch.postEvent(dispositionEvt);
        			    CompositeResponse compDisp = (CompositeResponse) dispatch.getReply();
        			    Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(compDisp, JuvenileDispositionCodeResponseEvent.class);
        
        			    if (juvDisp != null)
        			    {
        				Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvDisp.iterator();
        				if (juvCodeIter.hasNext())
        				{
        				    dispResp = juvCodeIter.next();
        				    if (dispResp != null)
        				    {
        					if (dispResp.getOptionCode()!=null && dispResp.getOptionCode().equalsIgnoreCase("R"))
        					{
        					    if (currentSettingResp.getDocketType().equalsIgnoreCase("COURT"))
        					    {
        						isResetForCourt = true;
        					    }
        
        					    if (currentSettingResp.getDocketType().equalsIgnoreCase("ANCILLARY"))
        					    {
        						isResetForAncillary = true; //ancillary
        					    }
        					}
        				    }
        				}
        			    }
			    }
			    //  }
			    /* if (isResetForCourt)
			     {
			    GetJJSCLCourtByChainAndSeqNumEvent courtEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
			    courtEvt.setChainNumber(currentSettingResp.getChainNumber());
			    courtEvt.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum()) + 10));
			    dispatch.postEvent(courtEvt);

			    CompositeResponse courtResp = (CompositeResponse) dispatch.getReply();
			    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtResp, DocketEventResponseEvent.class);
			    if (docketResponses == null || (docketResponses != null && docketResponses.isEmpty()))
			    {
			        isNewCourtRecordCreated = true;
			    }
			    else
			    {
			        isSubsequentCourtRecordAvail = true;
			    }
			     }
			     if (isResetForAncillary)
			     {
			    GetJJSCLAncillaryCourtByChainAndSeqNumEvent ancillaryEvt = (GetJJSCLAncillaryCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYCHAINANDSEQNUM);
			    ancillaryEvt.setChainNumber(currentSettingResp.getChainNumber());
			    ancillaryEvt.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum()) + 10));
			    dispatch.postEvent(ancillaryEvt);
			    CompositeResponse ancillaryCompResp = (CompositeResponse) dispatch.getReply();
			    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(ancillaryCompResp, DocketEventResponseEvent.class);
			    if (docketResponses == null || (docketResponses != null && docketResponses.isEmpty()))
			    {
			        isNewAncillaryRecordCreated = true;
			    }
			    else
			    {
			        isSubsequentAncillaryRecordAvail = true;
			    }
			     }*/
			    /////////////////////////////////////////////////////////////////ACTUAL UPDATE PLUS IT CREATE NEW RECORD IF CONDITION BECOMES TRUE////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			    /**
			     * same logic as in delete function. deletion
			     * happens during submission. section 3.4.6.20
			     * delete calendar record. Court Calendar record The
			     * entry of Result code � OFF� when current setting
			     * is the only setting in the chain will result in
			     * the deletion of the setting and no other settings
			     * will be associated to the chain number
			     * (subsequent settings will not be associated to
			     * the chain.
			     */
			    if (currentSettingResp.getDocketType().equalsIgnoreCase("COURT"))
			    {
				String seqNumForNewRec = null;
				GetJJSCLCourtByChainAndSeqNumEvent courtEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
				courtEvt.setChainNumber(currentSettingResp.getChainNumber());
				dispatch.postEvent(courtEvt);
				CompositeResponse courtResp = (CompositeResponse) dispatch.getReply();
				List<DocketEventResponseEvent> dktResps = MessageUtil.compositeToList(courtResp, DocketEventResponseEvent.class);

				Collections.sort((List<DocketEventResponseEvent>) dktResps, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
				    @Override
				    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
				    {
					if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
					    return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
					else
					    return -1;
				    }
				}));

				if (dktResps != null)
				{

				    if (dktResps.iterator().hasNext())
				    {
					DocketEventResponseEvent response = dktResps.iterator().next();
					if (response != null)
					{
					    seqNumForNewRec = String.valueOf(Integer.valueOf(response.getSeqNum()) + 10);
					}
				    }

				    if (currentSettingResp.getCourtResult()!=null && currentSettingResp.getCourtResult().equalsIgnoreCase("OFF"))
				    {
					//Is deleted setting the last setting
					boolean isOnlySetting = false;
					int dSize = dktResps.size();
					if (dSize == 1)
					{
					    isOnlySetting = true;
					}
					if (isOnlySetting)
					{
					    Map<String, DocketEventResponseEvent> dktSearchResultsMap = courtHearingForm.getDktSearchResultsMap();
					    DeleteJJSCLCourtSettingEvent deleteCourtSettingEvt = (DeleteJJSCLCourtSettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.DELETEJJSCLCOURTSETTING);
					    deleteCourtSettingEvt.setDocketEventId(currentSettingResp.getDocketEventId());

					    CompositeResponse compositeResponse = MessageUtil.postRequest(deleteCourtSettingEvt);
					    DocketEventResponseEvent dktResp = (DocketEventResponseEvent) MessageUtil.filterComposite(compositeResponse, DocketEventResponseEvent.class);
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
					    if (dktResp.getDeleteFlag() != null && dktResp.getDeleteFlag().equalsIgnoreCase("true"))
					    {
						isOnlySettingDeleted = true;
						// after deletion process.
						if (dktSearchResultsMap.containsKey(currentSettingResp.getDocketEventIdKey()))
						{
						    dktSearchResultsMap.remove(currentSettingResp.getDocketEventIdKey()); //remove from the main list after deletion.
						    courtHearingForm.setAllDktSearchResults(new ArrayList<DocketEventResponseEvent>(dktSearchResultsMap.values()));
						    courtHearingForm.setSearchResultSize(dktSearchResultsMap.size());
						    courtHearingForm.setDktSearchResultsMap(dktSearchResultsMap);
						}

						//update Juvenile Referral information.
						UpdateJuvenileReferralFromDistrictCourtEvent juvenileReferralUpdateEvent = (UpdateJuvenileReferralFromDistrictCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJUVENILEREFERRALFROMDISTRICTCOURT);
						juvenileReferralUpdateEvent.setDeleteFlag("true");
						juvenileReferralUpdateEvent.setIsOnlySetting("true");
						juvenileReferralUpdateEvent.setJuvenileNumber(currentSettingJuvenileNum);
						juvenileReferralUpdateEvent.setReferralNumber(currentSettingResp.getReferralNum());
						juvenileReferralUpdateEvent.setPetitionNum(currentSettingResp.getPetitionNumber());
						juvenileReferralUpdateEvent.setPetitionStatus(currentSettingResp.getPetitionStatus());
						CompositeResponse juvenileReferralCompResponse = postRequestEvent(juvenileReferralUpdateEvent);
					    }
					}
				    }
				}
				if (!isOnlySettingDeleted)
				{
				    /**
				     * All Calendar record types: Chain number
				     * is the same as Chain number in current
				     * setting. Chain Sequence number: find the
				     * last setting record in the current chain,
				     * Add +10 to the located setting�s Chain
				     * Sequence Number. Resulting value becomes
				     * new settings Chain Sequence number.
				     * ResetHearingType = current setting�s
				     * PetitionType if user has not supplied a
				     * value. LastChangeDate/Time = current
				     * system date/time LastChangeUser = current
				     * session user HearingCategory: Category
				     * for Disposition always takesRcour
				     * precedence over the category associated
				     * to a Result value.
				     */
				    //existing record.
				    UpdateJJSCLCourtSettingEvent updateCourtEvt = (UpdateJJSCLCourtSettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLCOURTSETTING);
				    updateCourtEvt.setDocketEventId(currentSettingResp.getDocketEventId());

				    updateCourtEvt.setAttorneyConnection(currentSettingResp.getAttorneyConnection());
				    updateCourtEvt.setAttorneyName(currentSettingResp.getAttorneyName());
				    updateCourtEvt.setBarNumber(currentSettingResp.getBarNum());				    
				    currentSettingResp.setOldattorneyName(currentSettingResp.getAttorneyName());
				    currentSettingResp.setOldattorneyConnection(currentSettingResp.getAttorneyConnection());
				    /*if (currentSettingResp.getBarNum() != null && currentSettingResp.getBarNum().equals(""))
				    {
					updateCourtEvt.setBarNumber(null);
				    }
				    else
				    {
					updateCourtEvt.setBarNumber(currentSettingResp.getBarNum());
				    }*/
				    if (byPass == null)
				    {
        				    updateCourtEvt.setAtyConfirmation("YES");
        				    currentSettingResp.setAtyConfirmation("YES");//setting the response to do color change in ui
				    }
				    else
				    {
					currentSettingResp.setAtyBypass("YES");
					//if attorney is not confirmed pull the existing attorney details from court
					if (currentSettingResp.getAtyConfirmation() == null || currentSettingResp.getAtyConfirmation().isEmpty())
					    {
					    	GetJJSCourtbyOIDEvent courtEvent = (GetJJSCourtbyOIDEvent) EventFactory
				                        .getInstance(JuvenileCaseControllerServiceNames.GETJJSCOURTBYOID);
					    	courtEvent.setId(currentSettingResp.getDocketEventId());
				                CompositeResponse officerComposite = MessageUtil.postRequest(courtEvent);

				                DocketEventResponseEvent resp= (DocketEventResponseEvent) MessageUtil.filterComposite(
				                        officerComposite, DocketEventResponseEvent.class);
						
				                if(resp!=null)
				                {
        						updateCourtEvt.setAttorneyName(resp.getAttorneyName());
        						updateCourtEvt.setAttorneyConnection(resp.getAttorneyConnection());
        						updateCourtEvt.setBarNumber(resp.getBarNum());
				                }
					    }	
				    }
				    updateCourtEvt.setGalName(currentSettingResp.getGalName());
				    if (currentSettingResp.getGalBarNum() != null && currentSettingResp.getGalBarNum().equals(""))
				    {
					updateCourtEvt.setGalBarNum(null);
				    }
				    else
				    {
					updateCourtEvt.setGalBarNum(currentSettingResp.getGalBarNum());
					updateCourtEvt.setUpdateSubAdLitem(true);
				    }				  
				    //null check
				    //attorney 2 details
				    updateCourtEvt.setAttorney2Name(currentSettingResp.getAttorney2Name());
				    updateCourtEvt.setAttorney2Connection(currentSettingResp.getAttorney2Connection());
				    updateCourtEvt.setAttorney2BarNum(currentSettingResp.getAttorney2BarNum());
				    /*if (currentSettingResp.getAttorney2BarNum() != null && currentSettingResp.getAttorney2BarNum().equals(""))
				    {
					updateCourtEvt.setAttorney2BarNum(null);
				    }
				    else
				    {
					updateCourtEvt.setAttorney2BarNum(currentSettingResp.getAttorney2BarNum());
				    }*/
				    //
				    if (StringUtil.isEmpty(currentSettingResp.getComments()))
					updateCourtEvt.setComments(null); 
				    else
					updateCourtEvt.setComments(currentSettingResp.getComments());
				    updateCourtEvt.setChainNumber(currentSettingResp.getChainNumber());
				    updateCourtEvt.setCourtId(currentSettingResp.getCourt());
				    updateCourtEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
				    updateCourtEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));

				    if (dispResp != null && currentSettingResp.getDisposition() != null && !currentSettingResp.getDisposition().isEmpty())
				    {
					updateCourtEvt.setHearingCategory(dispResp.getCategoryCode()); //category Code
				    }
				    else
					if (resultDisp != null && currentSettingResp.getCourtResult() != null && !currentSettingResp.getCourtResult().isEmpty())
					{
					    updateCourtEvt.setHearingCategory(resultDisp.getCategoryCode()); //category Code
					}
				    updateCourtEvt.setHearingDisposition(currentSettingResp.getDisposition());				   
				    UIJuvenileHelper.PopulateTJPCdisp(updateCourtEvt, currentSettingResp.getDisposition());  //US 179485 - save disposition code to TJPCDisp field in JJSCLCOURT table
				    updateCourtEvt.setHearingResult(currentSettingResp.getCourtResult());
				    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty())
					updateCourtEvt.setResetDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
				    updateCourtEvt.setHearingType(currentSettingResp.getHearingType()); //hearing Type
				    updateCourtEvt.setJuvenileNumber( currentSettingResp.getJuvenileNumber() );
				    updateCourtEvt.setPrevNotes(currentSettingResp.getPrevNotes());

				    updateCourtEvt.setResetHearingType(currentSettingResp.getResetHearingType());

				    updateCourtEvt.setTransferTo(currentSettingResp.getTransferTo());
				    updateCourtEvt.setNewRecordCreated(false);
				    updateCourtEvt.setAppAttorney(currentSettingResp.getAppAttorney());
				    // task 168662
				    updateCourtEvt.setInterpreter(currentSettingResp.getInterpreter());
				    CompositeResponse comp = MessageUtil.postRequest(updateCourtEvt);
				    DocketEventResponseEvent docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(comp, DocketEventResponseEvent.class);
				    Object errResponse = MessageUtil.filterComposite(comp, ErrorResponseEvent.class);
				    if (errResponse != null)
				    {
					ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
					try
					{
					    handleFatalUnexpectedException(myEvt.getMessage());
					}
					catch (GeneralFeedbackMessageException e)
					{
					    e.printStackTrace();
					}
				    } 				    
				    
				    if (docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
				    {
					isRecordUpdated = true;

					if (resultDisp != null && resultDisp.getOptionCode() != null && !resultDisp.getOptionCode().equalsIgnoreCase("R"))
					{
					    currentSettingResp.setActionDate("");
					    currentSettingResp.setActionTime("");
					}
					
					if(docEvtResp.getResetHearingUpdateFlag().equalsIgnoreCase("true") ){
					    if(currentSettingResp.getResetHearingType()!=null && !currentSettingResp.getResetHearingType().equalsIgnoreCase(originalSettingResp.getResetHearingType())){
						// court Decision Code table. Bug no: 
						GetJJSCLCourtByChainAndSeqNumEvent courtbyChainEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
						courtbyChainEvt.setChainNumber(currentSettingResp.getChainNumber());
						courtbyChainEvt.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum()) + 10));
						dispatch.postEvent(courtbyChainEvt);

						CompositeResponse courtchainResp = (CompositeResponse) dispatch.getReply();
						List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtchainResp, DocketEventResponseEvent.class);
						Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

						if (docketResponsesItr.hasNext())
						{
						    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
						    if (docResp != null)
						    {
							updateCourtEvt = (UpdateJJSCLCourtSettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLCOURTSETTING);
							updateCourtEvt.setDocketEventId(docResp.getDocketEventId());
							updateCourtEvt.setNewRecordCreated(false);
							//bug #83690
							if (currentSettingResp.getResetHearingType() != null && !currentSettingResp.getResetHearingType().equals(originalSettingResp.getResetHearingType()))
							{
							    updateCourtEvt.setHearingType(currentSettingResp.getResetHearingType());
							}
							comp = MessageUtil.postRequest(updateCourtEvt);
							docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(comp, DocketEventResponseEvent.class);
							errResponse = MessageUtil.filterComposite(comp, ErrorResponseEvent.class);
							if (errResponse != null)
							{
							    ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
							    try
							    {
								handleFatalUnexpectedException(myEvt.getMessage());
							    }
							    catch (GeneralFeedbackMessageException e)
							    {
								e.printStackTrace();
							    }
							}
							if (docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
							{
							  isSubsequentRecUpdated = true;
							}
						    } //if
						}
					    }
					}
				    }

				    //update subsequent record
				    /**
				     * 3.4.6.8 Activity: Update existing
				     * subsequent calendar record for resets If
				     * screen Action Date is populated and the
				     * Result has an Option = �R�, and a
				     * subsequent calender record is in the same
				     * chain as the current setting, (Chain
				     * Sequence number is larger than current
				     * chain sequence number AND is the next one
				     * in sequential order), change the
				     * subsequent calendar record�s
				     * CourtDate/HearingDate to the screen
				     * ResetDate/ActionDate of the current
				     * setting.
				     */
				    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
				    {
					// court Decision Code table. Bug no: 
					/*if (isSubsequentCourtRecordAvail)
					{
					GetJJSCLCourtByChainAndSeqNumEvent courtbyChainEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
					courtbyChainEvt.setChainNumber(currentSettingResp.getChainNumber());
					courtbyChainEvt.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum()) + 10));
					dispatch.postEvent(courtbyChainEvt);

					CompositeResponse courtchainResp = (CompositeResponse) dispatch.getReply();
					List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtchainResp, DocketEventResponseEvent.class);
					Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

					while (docketResponsesItr.hasNext())
					{
					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
					    if (docResp != null)
					    {

						updateCourtEvt = (UpdateJJSCLCourtSettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLCOURTSETTING);
						updateCourtEvt.setDocketEventId(docResp.getDocketEventId());
						updateCourtEvt.setChainNumber(currentSettingResp.getChainNumber());
						*//**
					 * change the subsequent calendar
					 * record�s CourtDate /HearingDate to
					 * the screen ResetDate /ActionDate of
					 * the current setting.
					 */
					/*
					if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					{
					 updateCourtEvt.setCourtDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
					}
					else
					{
					 updateCourtEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
					}
					//time
					if (currentSettingResp.getActionTime() != null && !currentSettingResp.getActionTime().isEmpty() && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
					{
					 updateCourtEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime()));
					}
					else
					{
					 updateCourtEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));
					}
					updateCourtEvt.setNewRecordCreated(false);

					comp = MessageUtil.postRequest(updateCourtEvt);
					docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(comp, DocketEventResponseEvent.class);
					errResponse = MessageUtil.filterComposite(comp, ErrorResponseEvent.class);
					if (errResponse != null)
					{
					 ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
					 try
					 {
					handleFatalUnexpectedException(myEvt.getMessage());
					 }
					 catch (GeneralFeedbackMessageException e)
					 {
					e.printStackTrace();
					 }
					}
					if (docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
					{
					 isSubsequentRecUpdated = true;
					}
					}
					} //while
					}*/
					/**
					 * Court Calendar record Populate the
					 * new calendar record with the values
					 * from the existing calendar record,
					 * except for those attributes modified
					 * or newly populated by the user. The
					 * new/modified values should populate
					 * the corresponding attributes on the
					 * new calendar record. Previous Notes,
					 * Comments, Result and Disposition,
					 * ActionDate/Time are NOT copied to new
					 * calendar record. ActionDate/Time on
					 * previous calendar record in same
					 * chain becomes the CourtDate/Time on
					 * the new record.
					 */
					if (isResetForCourt)
					{
					    updateCourtEvt = new UpdateJJSCLCourtSettingEvent();
					    updateCourtEvt.setChainNumber(currentSettingResp.getChainNumber());
					    updateCourtEvt.setSeqNumber(seqNumForNewRec);
					    /*if (dispResp != null && currentSettingResp.getDisposition() != null && !currentSettingResp.getDisposition().isEmpty())
					    {
					        updateCourtEvt.setHearingCategory(dispResp.getCategoryCode()); //category Code
					    }
					    else if (resultDisp != null && currentSettingResp.getCourtResult() != null && !currentSettingResp.getCourtResult().isEmpty())
					    {
					        updateCourtEvt.setHearingCategory(resultDisp.getCategoryCode()); //category Code
					    }*/

					    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					    {
						updateCourtEvt.setCourtDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
					    }
					    else
					    {
						updateCourtEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
					    }
					    //time
					    if (currentSettingResp.getActionTime() != null && !currentSettingResp.getActionTime().isEmpty() && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
					    {
						updateCourtEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime()));
					    }
					    else
					    {
						updateCourtEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));
					    }
					    if (StringUtil.isEmpty(currentSettingResp.getAttorneyConnection()))
						updateCourtEvt.setAttorneyConnection(null); 
					    else
						updateCourtEvt.setAttorneyConnection(currentSettingResp.getAttorneyConnection());
					    if (StringUtil.isEmpty(currentSettingResp.getAttorneyName()))
						updateCourtEvt.setAttorneyName(null); 
					    else
						updateCourtEvt.setAttorneyName(currentSettingResp.getAttorneyName());					    
					    if (currentSettingResp.getBarNum() != null && currentSettingResp.getBarNum().equals(""))
					    {
						updateCourtEvt.setBarNumber(null);
					    }
					    else
					    {
						updateCourtEvt.setBarNumber(currentSettingResp.getBarNum());
					    }
					 // attorney 2 details
					    if (StringUtil.isEmpty(currentSettingResp.getAttorney2Connection()))
						updateCourtEvt.setAttorney2Connection(null); 
					    else
						updateCourtEvt.setAttorney2Connection(currentSettingResp.getAttorney2Connection());
					    if (StringUtil.isEmpty(currentSettingResp.getAttorney2Name()))
						updateCourtEvt.setAttorney2Name(null); 
					    else
						updateCourtEvt.setAttorney2Name(currentSettingResp.getAttorney2Name());					    
					    if (currentSettingResp.getAttorney2BarNum() != null && currentSettingResp.getAttorney2BarNum().equals(""))
					    {
						updateCourtEvt.setAttorney2BarNum(null);
					    }
					    else
					    {
						updateCourtEvt.setAttorney2BarNum(currentSettingResp.getAttorney2BarNum());
					    }
					    //
					    updateCourtEvt.setAppAttorney(currentSettingResp.getAppAttorney());
					    //
					    updateCourtEvt.setInterpreter(currentSettingResp.getInterpreter());
					    //add attorney details from existing if not confirmed
					    if (currentSettingResp.getAtyConfirmation() == null || currentSettingResp.getAtyConfirmation().isEmpty())
					    {
					    	GetJJSCourtbyOIDEvent courtEvent = (GetJJSCourtbyOIDEvent) EventFactory
				                        .getInstance(JuvenileCaseControllerServiceNames.GETJJSCOURTBYOID);
					    	courtEvent.setId(currentSettingResp.getDocketEventId());
				                CompositeResponse officerComposite = MessageUtil.postRequest(courtEvent);

				                DocketEventResponseEvent resp= (DocketEventResponseEvent) MessageUtil.filterComposite(
				                        officerComposite, DocketEventResponseEvent.class);
						
				                if(resp!=null)
				                {
        						updateCourtEvt.setAttorneyName(resp.getAttorneyName());
        						updateCourtEvt.setAttorneyConnection(resp.getAttorneyConnection());
        						updateCourtEvt.setBarNumber(resp.getBarNum());
				                }
					    }	
					    //
					    if (currentSettingResp.getGalBarNum() != null && currentSettingResp.getGalBarNum().equals(""))
					    {
						updateCourtEvt.setGalBarNum(null);
					    }
					    else
					    {
						updateCourtEvt.setGalBarNum(currentSettingResp.getGalBarNum());
						updateCourtEvt.setGalName( currentSettingResp.getGalName());
						updateCourtEvt.setUpdateSubAdLitem(true);
					    }
					    
					    if (currentSettingResp.getTransferTo() != null && !currentSettingResp.getTransferTo().isEmpty())
					    {
						if (currentSettingResp.getTransferTo().contains("0"))
						{
						    String transferTo = currentSettingResp.getTransferTo().substring(2);
						    updateCourtEvt.setCourtId(transferTo);
						}
						else
						{
						    updateCourtEvt.setCourtId(currentSettingResp.getTransferTo());
						}
					    }
					    else
					    {
						updateCourtEvt.setCourtId(currentSettingResp.getCourt());
					    }
					    				    
					    updateCourtEvt.setFilingDate(DateUtil.stringToDate(currentSettingResp.getFilingDate(), DateUtil.DATE_FMT_1));
					    updateCourtEvt.setIssueFlag(currentSettingResp.getIssueFlag());
					    updateCourtEvt.setJuryFlag(currentSettingResp.getJuryFlag());
					    updateCourtEvt.setJuvenileNumber(currentSettingJuvenileNum);
					    updateCourtEvt.setOptionFlag(currentSettingResp.getOptionFlag());
					    updateCourtEvt.setPetitionAllegation(currentSettingResp.getPetitionAllegation());
					    //bug fix for 112974
					    if (StringUtil.isEmpty(currentSettingResp.getComments()))
						updateCourtEvt.setComments(null); 
					    else
						updateCourtEvt.setComments(currentSettingResp.getComments());
					    if (StringUtil.isEmpty(currentSettingResp.getPetitionAmendment()))
						updateCourtEvt.setPetitionAmendment(null); 
					    else
						updateCourtEvt.setPetitionAmendment(currentSettingResp.getPetitionAmendment());					    
					    //
					    updateCourtEvt.setPrevNotes(currentSettingResp.getPrevNotes());
					    updateCourtEvt.setPetitionNumber(currentSettingResp.getPetitionNumber());
					    updateCourtEvt.setPetitionStatus(currentSettingResp.getPetitionStatusCd());
					    updateCourtEvt.setRecType(currentSettingResp.getDocketType());
					    updateCourtEvt.setReferralNumber(currentSettingResp.getReferralNum());
					    updateCourtEvt.setResetHearingType(currentSettingResp.getResetHearingType());
					    updateCourtEvt.setHearingType(currentSettingResp.getHearingType()); //hearing Type 84103
					    if(currentSettingResp.getResetHearingType()!=null && !currentSettingResp.getResetHearingType().isEmpty()){
						 updateCourtEvt.setHearingType(currentSettingResp.getResetHearingType());
					    }
					    updateCourtEvt.setTjpcSeqNumber(currentSettingResp.getTjpcSeqNum());
					    updateCourtEvt.setAmendmentDate(DateUtil.stringToDate(currentSettingResp.getPetitionAmendmentDate(), DateUtil.DATE_FMT_1));
					    updateCourtEvt.setNewRecordCreated(true);

					    CompositeResponse compResponse = MessageUtil.postRequest(updateCourtEvt);
					    DocketEventResponseEvent docket = (DocketEventResponseEvent) MessageUtil.filterComposite(compResponse, DocketEventResponseEvent.class);
					    Object errorResponse = MessageUtil.filterComposite(comp, ErrorResponseEvent.class);
					    if (errorResponse != null)
					    {
						ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
						try
						{
						    handleFatalUnexpectedException(myEvt.getMessage());
						}
						catch (GeneralFeedbackMessageException e)
						{
						    e.printStackTrace();
						}
					    }
					    if (docket != null && docket.getIsNewRecordCreated().equalsIgnoreCase("true"))
					    {
						isNewRecordCreated = true;
						//code to email JPO task 148174
						isEmailsend = sendJPOemail(currentSettingResp, originalSettingResp);
					    }
					 // as part of task 150244 to reset the flag of temp release in jjs_header on reset 
					    UpdateJJSHeaderFromDetentionCourtEvent updateHeaderEvt = (UpdateJJSHeaderFromDetentionCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSHEADERFROMDETENTIONCOURT);
					    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
					    Date currDate = new Date();
					    String today = sdf.format(currDate).trim();
					    if (!("".equalsIgnoreCase(currentSettingResp.getCourtResult())) && currentSettingResp.getCourtResult() != null && currentSettingResp.getCourtDate().trim().equals(today))
						{						 
						    updateHeaderEvt.setDistreleasedecisionStatus(true);
						    updateHeaderEvt.setJuvenileNumber(currentSettingJuvenileNum);
						    CompositeResponse headerResponse = MessageUtil.postRequest(updateHeaderEvt);
						    JuvenileFacilityHeaderResponseEvent headerRespEvt = (JuvenileFacilityHeaderResponseEvent) MessageUtil.filterComposite(headerResponse, JuvenileFacilityHeaderResponseEvent.class);
						} 
					}
				    }

				    //update Juvenile Master Status.
				    if (isRecordUpdated)
				    {
					boolean updateMaster = false;

					//update juvenile master information.
					UpdateJuvenileMasterFromDistrictCourtEvent juvenileMasterUpdateEvent = (UpdateJuvenileMasterFromDistrictCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJUVENILEMASTERFROMDISTRICTCOURT);

					juvenileMasterUpdateEvent.setJuvenileNumber(currentSettingJuvenileNum);
					juvenileMasterUpdateEvent.setOriginalActionDate(originalSettingResp.getActionDate());
					if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					{
					    juvenileMasterUpdateEvent.setActionDate(currentSettingResp.getActionDate());
					}
					juvenileMasterUpdateEvent.setOriginalCourtResult(originalSettingResp.getCourtResult());
					juvenileMasterUpdateEvent.setOriginalDisposition(originalSettingResp.getDisposition());
					juvenileMasterUpdateEvent.setCourtDate(currentSettingResp.getCourtDate());
					juvenileMasterUpdateEvent.setCourtResult(currentSettingResp.getCourtResult());
					juvenileMasterUpdateEvent.setDisposition(currentSettingResp.getDisposition());
					CompositeResponse juvenileCompResponse = postRequestEvent(juvenileMasterUpdateEvent);

					//update Juvenile Referral information.
					UpdateJuvenileReferralFromDistrictCourtEvent juvenileReferralUpdateEvent = (UpdateJuvenileReferralFromDistrictCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJUVENILEREFERRALFROMDISTRICTCOURT);
					juvenileReferralUpdateEvent.setCourtId(courtHearingForm.getCourtId());
					juvenileReferralUpdateEvent.setCourtDate(currentSettingResp.getCourtDate());
					if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					{
					    juvenileReferralUpdateEvent.setActionDate(currentSettingResp.getActionDate());
					}
					juvenileReferralUpdateEvent.setCourtDisposition(currentSettingResp.getDisposition());
					juvenileReferralUpdateEvent.setCourtResult(currentSettingResp.getCourtResult());//if trn with checkbox pass that 
					juvenileReferralUpdateEvent.setJuvenileNumber(currentSettingJuvenileNum);
					juvenileReferralUpdateEvent.setReferralNumber(currentSettingResp.getReferralNum());
					juvenileReferralUpdateEvent.setOriginalCourtResult(originalSettingResp.getCourtResult());
					juvenileReferralUpdateEvent.setOriginalCourtDisposition(originalSettingResp.getDisposition());
					juvenileReferralUpdateEvent.setTransferTo(currentSettingResp.getTransferTo());
					juvenileReferralUpdateEvent.setPetitionNum(currentSettingResp.getPetitionNumber());
					juvenileReferralUpdateEvent.setPetitionStatus(currentSettingResp.getPetitionStatus());
					// task 164542
					if(currentSettingResp.getTransferFlag().equalsIgnoreCase("1"))
					    juvenileReferralUpdateEvent.setTransferFlag("1");
					else
					    juvenileReferralUpdateEvent.setTransferFlag("0");
					//
					CompositeResponse juvenileReferralCompResponse = postRequestEvent(juvenileReferralUpdateEvent);
					//
					if(isdispositionemailSend==false && currentSettingResp.getDisposition()!=null &&  (originalSettingResp.getDisposition()==null ||originalSettingResp.getDisposition().isEmpty()))
					{
					    isdispositionemailSend = senddispositionEmail(currentSettingResp, originalSettingResp);
					}
					
					//

				    }

				    //notification.
				    //section 3.4.16.14
				    /*Final Disposititon Alert: If the user-supplied disposition has Option = �F� and the setting�s previous Disposition did not have Option = �F�,   a final Disposition alert needs to be sent.*/
				    if (originalSettingResp.getDisposition() != null && !originalSettingResp.getDisposition().isEmpty())
				    {
					GetJuvenileDispositionCodeEvent juvDispositionEvent = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
					juvDispositionEvent.setCode(originalSettingResp.getDisposition());
					CompositeResponse resp = postRequestEvent(juvDispositionEvent);
					Collection<JuvenileDispositionCodeResponseEvent> juvDisposition = MessageUtil.compositeToCollection(resp, JuvenileDispositionCodeResponseEvent.class);
					JuvenileDispositionCodeResponseEvent responseEvt = null;

					if (juvDisposition != null)
					{
					    Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvDisposition.iterator();
					    if (juvCodeIter.hasNext())
					    {
						responseEvt = juvCodeIter.next();
						if (responseEvt != null)
						{
						    //final dispostion
						    if (dispResp != null && dispResp.getOptionCode()!=null && dispResp.getOptionCode().equalsIgnoreCase("F") && responseEvt.getOptionCode()!=null && !responseEvt.getOptionCode().equalsIgnoreCase("F"))
						    {
							JuvenileProfileReferralListResponseEvent profileResp = JuvenileFacilityHelper.getCasefilesFromReferral(currentSettingJuvenileNum, currentSettingResp.getReferralNum());
							Collection<JuvenileCasefileReferral> casefilesResp = profileResp.getCasefileReferrals();

							Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
							    @Override
							    public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
							    {
								if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
								    return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());
								else
								    return -1;
							    }
							}));

							Iterator<JuvenileCasefileReferral> casefileItr = casefilesResp.iterator();
							while (casefileItr.hasNext())
							{
							    JuvenileCasefileReferral casefileReferral = casefileItr.next();
							    if (casefileReferral != null)
							    {
								OfficerProfile officerProfile = casefileReferral.getCaseFile().getProbationOfficer();
								if (officerProfile != null && officerProfile.getLogonId() != null)
								{
								    JuvenileDistrictCourtNotificationResponseEvent respEvt = new JuvenileDistrictCourtNotificationResponseEvent();
								    respEvt.setSubject("Court Has Issued Final Disposition");
								    respEvt.setIdentity(officerProfile.getLogonId());
								    respEvt.setJuvenileNum(currentSettingJuvenileNum);

								    String notifMessage = "A final disposition was handed down by the court on " + currentSettingResp.getCourtDate() + " for " + currentSettingResp.getJuvenileName() + ", Juvenile# " + currentSettingJuvenileNum + " and Petition# " + currentSettingResp.getPetitionNumber();
								    respEvt.setNotificationMessage(notifMessage);

								    CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
								    notificationEvent.setNotificationTopic("JC.DISTRICT.COURT.FINAL.DISPOSITION.NOTIFICATION");
								    notificationEvent.setSubject(respEvt.getSubject());
								    notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
								    notificationEvent.addContentBean(respEvt);
								    CompositeResponse response = MessageUtil.postRequest(notificationEvent);
								    MessageUtil.processReturnException(response);
								}
								//CLMS
								if (officerProfile != null && officerProfile.getManagerLogonId() != null)
								{
								    JuvenileDistrictCourtNotificationResponseEvent respEvt = new JuvenileDistrictCourtNotificationResponseEvent();
								    respEvt.setSubject("Court Has Issued Final Disposition");
								    respEvt.setIdentity(officerProfile.getManagerLogonId());
								    respEvt.setJuvenileNum(currentSettingJuvenileNum);

								    String notifMessage = "A final disposition was handed down by the court on " + currentSettingResp.getCourtDate() + " for " + currentSettingResp.getJuvenileName() + ", Juvenile# " + currentSettingJuvenileNum + " and Petition# " + currentSettingResp.getPetitionNumber();
								    respEvt.setNotificationMessage(notifMessage);

								    CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
								    notificationEvent.setNotificationTopic("JC.DISTRICT.COURT.FINAL.DISPOSITION.NOTIFICATION");
								    notificationEvent.setSubject(respEvt.getSubject());
								    notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
								    notificationEvent.addContentBean(respEvt);
								    CompositeResponse response = MessageUtil.postRequest(notificationEvent);
								    MessageUtil.processReturnException(response);
								}
							    }
							}
						    }
						    //deferred disposition
						    if (dispResp != null && dispResp.getjPCCode() != null && dispResp.getjPCCode().equalsIgnoreCase("100") && responseEvt.getSubGroupInd() != null && responseEvt.getSubGroupInd().equalsIgnoreCase("I"))
						    {
							JuvenileProfileReferralListResponseEvent profileResp = JuvenileFacilityHelper.getCasefilesFromReferral(currentSettingJuvenileNum, currentSettingResp.getReferralNum());
							Collection<JuvenileCasefileReferral> casefilesResp = profileResp.getCasefileReferrals();

							Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
							    @Override
							    public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
							    {
								if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
								    return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());
								else
								    return -1;
							    }
							}));

							Iterator<JuvenileCasefileReferral> casefileItr = casefilesResp.iterator();
							while (casefileItr.hasNext())
							{
							    JuvenileCasefileReferral casefileReferral = casefileItr.next();
							    if (casefileReferral != null)
							    {
								OfficerProfile officerProfile = casefileReferral.getCaseFile().getProbationOfficer();
								if (officerProfile != null && officerProfile.getLogonId() != null)
								{
								    JuvenileDistrictCourtNotificationResponseEvent respEvt = new JuvenileDistrictCourtNotificationResponseEvent();
								    respEvt.setSubject("Court Has Issued Deferred Adjudication Order");
								    respEvt.setIdentity(officerProfile.getLogonId());
								    respEvt.setJuvenileNum(currentSettingJuvenileNum);

								    String notifMessage = "Court has ordered deferred adjudication for " + currentSettingResp.getJuvenileName() + ",Juvenile# " + currentSettingJuvenileNum + " and Petition# " + currentSettingResp.getPetitionNumber() + ". On " + currentSettingResp.getCourtDate();
								    respEvt.setNotificationMessage(notifMessage);

								    CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
								    notificationEvent.setNotificationTopic("JC.DISTRICT.COURT.DEFERRED.ADJUDICATION.NOTIFICATION");
								    notificationEvent.setSubject(respEvt.getSubject());
								    notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
								    notificationEvent.addContentBean(respEvt);
								    CompositeResponse response = MessageUtil.postRequest(notificationEvent);
								    MessageUtil.processReturnException(response);
								}
								//CLMS
								if (officerProfile != null && officerProfile.getManagerLogonId() != null)
								{
								    JuvenileDistrictCourtNotificationResponseEvent respEvt = new JuvenileDistrictCourtNotificationResponseEvent();
								    respEvt.setSubject("Court Has Issued Deferred Adjudication Order");
								    respEvt.setIdentity(officerProfile.getManagerLogonId());
								    respEvt.setJuvenileNum(currentSettingJuvenileNum);

								    String notifMessage = "Court has ordered deferred adjudication for " + currentSettingResp.getJuvenileName() + ",Juvenile# " + currentSettingJuvenileNum + " and Petition# " + currentSettingResp.getPetitionNumber() + ". On " + currentSettingResp.getCourtDate();
								    respEvt.setNotificationMessage(notifMessage);

								    CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
								    notificationEvent.setNotificationTopic("JC.DISTRICT.COURT.DEFERRED.ADJUDICATION.NOTIFICATION");
								    notificationEvent.setSubject(respEvt.getSubject());
								    notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
								    notificationEvent.addContentBean(respEvt);
								    CompositeResponse response = MessageUtil.postRequest(notificationEvent);
								    MessageUtil.processReturnException(response);
								}
							    }
							}
						    }
						}
					    }
					}
				    }
				}
			    } //court

			    /**
			     * Ancillary Calendar record The entry of Result
			     * code � OFF� when current setting is the only
			     * setting in the chain will result in the deletion
			     * of the setting and no other settings will be
			     * associated to the chain number (subsequent
			     * settings will not be associated to the chain).
			     */
			    if (currentSettingResp.getDocketType().equalsIgnoreCase("ANCILLARY"))
			    {
				String seqNumForNewRec = null;
				GetJJSCLAncillaryCourtByChainAndSeqNumEvent ancillaryEvt = (GetJJSCLAncillaryCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYCHAINANDSEQNUM);
				ancillaryEvt.setChainNumber(currentSettingResp.getChainNumber());
				dispatch.postEvent(ancillaryEvt);
				CompositeResponse ancillaryResp = (CompositeResponse) dispatch.getReply();

				List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(ancillaryResp, DocketEventResponseEvent.class);
				Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

				Collections.sort((List<DocketEventResponseEvent>) docketResponses, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
				    @Override
				    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
				    {
					if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
					    return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
					else
					    return -1;
				    }
				}));

				if (docketResponses != null)
				{
				    if (docketResponsesItr.hasNext())
				    {
					DocketEventResponseEvent evt = docketResponses.iterator().next();
					if (evt != null)
					{
					    seqNumForNewRec = String.valueOf(Integer.valueOf(evt.getSeqNum()) + 10);
					}
				    }

				    if (currentSettingResp.getCourtResult()!=null && currentSettingResp.getCourtResult().equalsIgnoreCase("OFF"))
				    {
					//Is deleted setting the only setting
					boolean isOnlySetting = false;
					int dSize = docketResponses.size();
					if (dSize == 1)
					{
					    isOnlySetting = true;
					}
					if (isOnlySetting)
					{
					    Map<String, DocketEventResponseEvent> dktSearchResultsMap = courtHearingForm.getDktSearchResultsMap();
					    DeleteJJSCLAncillarySettingEvent deleteAncillarySettingEvt = (DeleteJJSCLAncillarySettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.DELETEJJSCLANCILLARYSETTING);
					    deleteAncillarySettingEvt.setDocketEventId(currentSettingResp.getDocketEventId());

					    CompositeResponse compositeResponse = MessageUtil.postRequest(deleteAncillarySettingEvt);
					    DocketEventResponseEvent resp = (DocketEventResponseEvent) MessageUtil.filterComposite(compositeResponse, DocketEventResponseEvent.class);
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
					    if (resp.getDeleteFlag() != null && resp.getDeleteFlag().equalsIgnoreCase("true"))
					    {
						isOnlySettingDeleted = true;
						// after deletion process.
						if (dktSearchResultsMap.containsKey(currentSettingResp.getDocketEventIdKey()))
						{
						    dktSearchResultsMap.remove(currentSettingResp.getDocketEventIdKey()); //remove from the main list after deletion.
						    courtHearingForm.setDktSearchResultsMap(dktSearchResultsMap);
						    courtHearingForm.setAllDktSearchResults(new ArrayList<DocketEventResponseEvent>(dktSearchResultsMap.values()));
						    courtHearingForm.setSearchResultSize(dktSearchResultsMap.size());
						}
					    }
					}
				    } //last setting check
				}

				if (!isOnlySettingDeleted)
				{
				    /**
				     * All Calendar record types: Chain number
				     * is the same as Chain number in current
				     * setting. Chain Sequence number: find the
				     * last setting record in the current chain,
				     * Add +10 to the located setting�s Chain
				     * Sequence Number. Resulting value becomes
				     * new settings Chain Sequence number.
				     * ResetHearingType = current setting�s
				     * PetitionType if user has not supplied a
				     * value. LastChangeDate/Time = current
				     * system date/time LastChangeUser = current
				     * session user HearingCategory: Category
				     * for Disposition always takes precedence
				     * over the category associated to a Result
				     * value.
				     */
				    //update existing record
				    UpdateJJSCLAncillarySettingEvent updateAncillaryEvt = (UpdateJJSCLAncillarySettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLANCILLARYSETTING);
				    updateAncillaryEvt.setDocketEventId(currentSettingResp.getDocketEventId());
				    updateAncillaryEvt.setAttorneyConnection(currentSettingResp.getAttorneyConnection());
				    updateAncillaryEvt.setAttorneyName(currentSettingResp.getAttorneyName());
				    if (currentSettingResp.getBarNum() != null && currentSettingResp.getBarNum().equals(""))
				    {
					updateAncillaryEvt.setBarNum(null);
				    }
				    else
				    {
					updateAncillaryEvt.setBarNum(currentSettingResp.getBarNum());
				    }
				    
				   /* updateAncillaryEvt.setGalName(currentSettingResp.getGalName());
				    if (currentSettingResp.getGalBarNum() != null && currentSettingResp.getGalBarNum().equals(""))
				    {
					updateAncillaryEvt.setGalBarNum(null);
				    }
				    else
				    {
					updateAncillaryEvt.setGalBarNum(currentSettingResp.getGalBarNum());
				    }*/
				    updateAncillaryEvt.setComments(currentSettingResp.getComments());
				    updateAncillaryEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
				    updateAncillaryEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));
				    updateAncillaryEvt.setCourtId(currentSettingResp.getCourt());
				    updateAncillaryEvt.setHearingDisposition(currentSettingResp.getDisposition());
				    updateAncillaryEvt.setHearingResult(currentSettingResp.getCourtResult());
				    updateAncillaryEvt.setSettingReason(currentSettingResp.getSettingReason()); //hearing Type
				    updateAncillaryEvt.setPrevNotes(currentSettingResp.getPrevNotes());
				    updateAncillaryEvt.setResetHearingType(currentSettingResp.getResetHearingType());
				    updateAncillaryEvt.setTransferTo(currentSettingResp.getTransferTo());
				    updateAncillaryEvt.setRespondentName(currentSettingResp.getRespondantName());
				    // task 168662
				    updateAncillaryEvt.setInterpreter(currentSettingResp.getInterpreter());
				    updateAncillaryEvt.setNewRecordCreated(false);

				    CompositeResponse comp = MessageUtil.postRequest(updateAncillaryEvt);
				    DocketEventResponseEvent docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(comp, DocketEventResponseEvent.class);
				    Object errResponse = MessageUtil.filterComposite(comp, ErrorResponseEvent.class);
				    if (errResponse != null)
				    {
					ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
					try
					{
					    handleFatalUnexpectedException(myEvt.getMessage());
					}
					catch (GeneralFeedbackMessageException e)
					{
					    e.printStackTrace();
					}
				    }
				    if (docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
				    {
					isRecordUpdated = true;
					
					//83690
					if(docEvtResp.getResetHearingUpdateFlag().equalsIgnoreCase("true"))
					{
					    if(currentSettingResp.getResetHearingType()!=null && !currentSettingResp.getResetHearingType().equalsIgnoreCase(originalSettingResp.getResetHearingType()))
					    {
						GetJJSCLAncillaryCourtByChainAndSeqNumEvent ancillaryByChainEvt = (GetJJSCLAncillaryCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYCHAINANDSEQNUM);
						ancillaryByChainEvt.setChainNumber(currentSettingResp.getChainNumber());
						ancillaryByChainEvt.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum())+ 10));
						dispatch.postEvent(ancillaryByChainEvt);
						CompositeResponse ancillaryCompResp = (CompositeResponse) dispatch.getReply();

						List<DocketEventResponseEvent> docketResps = MessageUtil.compositeToList(ancillaryCompResp, DocketEventResponseEvent.class);
						Iterator<DocketEventResponseEvent> docEvtItr = docketResps.iterator();

						if (docEvtItr.hasNext())
						{
						    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docEvtItr.next();
						    //action date and time (derived value) gets persisted in the court date and court time field on update of court action
						    if (docResp != null)
						    {
							updateAncillaryEvt = (UpdateJJSCLAncillarySettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLANCILLARYSETTING);
							updateAncillaryEvt.setDocketEventId(docResp.getDocketEventId());
							updateAncillaryEvt.setNewRecordCreated(false);
							//bug #83690
							if (currentSettingResp.getResetHearingType() != null && !currentSettingResp.getResetHearingType().equals(originalSettingResp.getResetHearingType()))
							{
							    updateAncillaryEvt.setSettingReason(currentSettingResp.getResetHearingType());
							}
							comp = MessageUtil.postRequest(updateAncillaryEvt);
							docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(comp, DocketEventResponseEvent.class);
							errResponse = MessageUtil.filterComposite(comp, ErrorResponseEvent.class);
							if (errResponse != null)
							{
							    ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
							    try
							    {
								handleFatalUnexpectedException(myEvt.getMessage());
							    }
							    catch (GeneralFeedbackMessageException e)
							    {
								e.printStackTrace();
							    }
							}
							if (docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
							{
							  isSubsequentRecUpdated = true;
							}
						    }
						}
					    }
					 }
				    }

				    //update subsequent record
				    /**
				     * 3.4.6.8 Activity: Update existing
				     * subsequent calendar record for resets If
				     * screen Action Date is populated and the
				     * Result has an Option = �R�, and a
				     * subsequent calender record is in the same
				     * chain as the current setting, (Chain
				     * Sequence number is larger than current
				     * chain sequence number AND is the next one
				     * in sequential order), change the
				     * subsequent calendar record�s
				     * CourtDate/HearingDate to the screen
				     * ResetDate/ActionDate of the current
				     * setting.
				     */
				    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
				    {
					//ancillary
					/*   if (isSubsequentAncillaryRecordAvail)
					   {
					GetJJSCLAncillaryCourtByChainAndSeqNumEvent ancillaryByChainEvt = (GetJJSCLAncillaryCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYCHAINANDSEQNUM);
					ancillaryByChainEvt.setChainNumber(currentSettingResp.getChainNumber());
					ancillaryByChainEvt.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum())+ 10));
					dispatch.postEvent(ancillaryByChainEvt);
					CompositeResponse ancillaryCompResp = (CompositeResponse) dispatch.getReply();

					List<DocketEventResponseEvent> docketResps = MessageUtil.compositeToList(ancillaryCompResp, DocketEventResponseEvent.class);
					Iterator<DocketEventResponseEvent> docEvtItr = docketResps.iterator();

					while (docEvtItr.hasNext())
					{
					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docEvtItr.next();
					    //action date and time (derived value) gets persisted in the court date and court time field on update of court action
					    if (docResp != null)
					    {
						updateAncillaryEvt = (UpdateJJSCLAncillarySettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLANCILLARYSETTING);
						updateAncillaryEvt.setDocketEventId(docResp.getDocketEventId());
						*//**
					 * change the subsequent calendar
					 * record�s CourtDate /HearingDate to
					 * the screen ResetDate /ActionDate of
					 * the current setting.
					 */
					/*
					if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					{
					 updateAncillaryEvt.setCourtDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
					}
					else
					{
					 updateAncillaryEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
					}
					//time
					if (currentSettingResp.getActionTime() != null && !currentSettingResp.getActionTime().isEmpty() && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
					{
					 updateAncillaryEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime()));
					}
					else
					{
					 updateAncillaryEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));
					}

					updateAncillaryEvt.setNewRecordCreated(false);

					comp = MessageUtil.postRequest(updateAncillaryEvt);
					docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(comp, DocketEventResponseEvent.class);
					errResponse = MessageUtil.filterComposite(comp, ErrorResponseEvent.class);
					if (errResponse != null)
					{
					 ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
					 try
					 {
					handleFatalUnexpectedException(myEvt.getMessage());
					 }
					 catch (GeneralFeedbackMessageException e)
					 {
					e.printStackTrace();
					 }
					}
					if (docEvtResp != null && docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
					{
					 isSubsequentRecUpdated = true;
					}
					}
					} //while
					} //ancillary if
					*//**
					 * Court Calendar record Populate the
					 * new calendar record with the values
					 * from the existing calendar record,
					 * except for those attributes modified
					 * or newly populated by the user. The
					 * new/modified values should populate
					 * the corresponding attributes on the
					 * new calendar record. Previous Notes,
					 * Comments, Result and Disposition,
					 * ActionDate/Time are NOT copied to new
					 * calendar record. ActionDate/Time on
					 * previous calendar record in same
					 * chain becomes the CourtDate/Time on
					 * the new record.
					 */
					if (isResetForAncillary)
					{

					    updateAncillaryEvt = new UpdateJJSCLAncillarySettingEvent();
					    updateAncillaryEvt.setSeqNumber(seqNumForNewRec);

					    //date
					    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					    {
						updateAncillaryEvt.setCourtDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
					    }
					    else
					    {
						updateAncillaryEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
					    }
					    //time
					    if (currentSettingResp.getActionTime() != null && !currentSettingResp.getActionTime().isEmpty() && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
					    {
						updateAncillaryEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime()));
					    }
					    else
					    {
						updateAncillaryEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));
					    }

					    updateAncillaryEvt.setAttorneyConnection(currentSettingResp.getAttorneyConnection());
					    updateAncillaryEvt.setAttorneyName(currentSettingResp.getAttorneyName());
					    if (currentSettingResp.getBarNum() != null && currentSettingResp.getBarNum().equals(""))
					    {
						updateAncillaryEvt.setBarNum(null);
					    }
					    else
					    {
						updateAncillaryEvt.setBarNum(currentSettingResp.getBarNum());
					    }

					    if (currentSettingResp.getTransferTo() != null && !currentSettingResp.getTransferTo().isEmpty())
					    {
						if (currentSettingResp.getTransferTo().contains("0"))
						{
						    String transferTo = currentSettingResp.getTransferTo().substring(2);
						    updateAncillaryEvt.setCourtId(transferTo);
						}
						else
						{
						    updateAncillaryEvt.setCourtId(currentSettingResp.getTransferTo());
						}
					    }
					    else
					    {
						updateAncillaryEvt.setCourtId(currentSettingResp.getCourt());
					    }
					    updateAncillaryEvt.setComments(currentSettingResp.getComments());
					    updateAncillaryEvt.setFilingDate(DateUtil.stringToDate(currentSettingResp.getFilingDate(), DateUtil.DATE_FMT_1));
					    updateAncillaryEvt.setIssueFlag(currentSettingResp.getIssueFlag());
					    updateAncillaryEvt.setJuryFlag(currentSettingResp.getJuryFlag());
					    updateAncillaryEvt.setPetitionAmendment(currentSettingResp.getPetitionAmendment());
					    updateAncillaryEvt.setPetitionNum(currentSettingResp.getPetitionNumber());
					    updateAncillaryEvt.setRecType(currentSettingResp.getDocketType());
					    updateAncillaryEvt.setResetHearingType(currentSettingResp.getResetHearingType());
					    updateAncillaryEvt.setTjpcSeqNumber(currentSettingResp.getTjpcSeqNum());
					    //  updateAncillaryEvt.setTransferTo(currentSettingResp.getTransferTo());
					    updateAncillaryEvt.setRespondentName(currentSettingResp.getRespondantName());
					    updateAncillaryEvt.setChainNum(currentSettingResp.getChainNumber());
					  //task 168662
					    updateAncillaryEvt.setInterpreter(currentSettingResp.getInterpreter());
					    if (currentSettingResp.getResetHearingType() != null && !currentSettingResp.getResetHearingType().isEmpty())
					    {
						updateAncillaryEvt.setSettingReason(currentSettingResp.getResetHearingType());
					    }
					    else
					    {
						updateAncillaryEvt.setSettingReason(originalSettingResp.getSettingReason());
					    }
					    /**
					     * If the record�s TypeCase = �C�,
					     * then Set NOTES (temp) = �DFPS�.
					     * If the record�s TypeCase = �P�,
					     * then Set NOTES (temp) =
					     * �PRIVATE�. If the record�s
					     * TypeCase = I, display, then Set
					     * NOTES (temp) = �IMMIGRATION�.
					     */
					    updateAncillaryEvt.setTypeCase(currentSettingResp.getTypeCase());
					    updateAncillaryEvt.setNewRecordCreated(true);
					    CompositeResponse compResponse = MessageUtil.postRequest(updateAncillaryEvt);
					    DocketEventResponseEvent docket = (DocketEventResponseEvent) MessageUtil.filterComposite(compResponse, DocketEventResponseEvent.class);
					    Object errorResponse = MessageUtil.filterComposite(compResponse, ErrorResponseEvent.class);
					    if (errorResponse != null)
					    {
						ErrorResponseEvent myEvt = (ErrorResponseEvent) errResponse;
						try
						{
						    handleFatalUnexpectedException(myEvt.getMessage());
						}
						catch (GeneralFeedbackMessageException e)
						{
						    e.printStackTrace();
						}
					    }
					    if (docket != null && docket.getIsNewRecordCreated().equalsIgnoreCase("true"))
					    {
						isNewRecordCreated = true;
					    }					  
					}
				    }
				}
			    } //ancillary
			}//court / ancillary

			if (currentSettingResp.getDocketType().equalsIgnoreCase("DETENTION"))
			{
			    String seqNumForNewRecord = null;
			    String detentionId	      = "";
			    GetJJSCLDetentionByChainEvent getDetentionEvt = (GetJJSCLDetentionByChainEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAIN);
			    getDetentionEvt.setChainNumber(currentSettingResp.getChainNumber());
			    dispatch.postEvent(getDetentionEvt);
			    CompositeResponse detentionResp = (CompositeResponse) dispatch.getReply();
			    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(detentionResp, DocketEventResponseEvent.class);

			    Collections.sort((List<DocketEventResponseEvent>) docketResponses, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
				@Override
				public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
				{
				    if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
					return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
				    else
					return -1;
				}
			    }));

			    if (docketResponses != null)
			    {
				if (docketResponses.iterator().hasNext())
				{
				    DocketEventResponseEvent docRespEvt = docketResponses.iterator().next();
				    if (docRespEvt != null)
				    {
					seqNumForNewRecord = String.valueOf(Integer.valueOf(docRespEvt.getSeqNum()) + 10);
					detentionId 	   = docRespEvt.getDetentionId();
					
				    }
				}
			    }

			    boolean isReset = false;
			    JuvenileCourtDecisionCodeResponseEvent crtResultDecision = null;
			    GetJuvenileCourtDecisionCodesEvent courtResultDisp = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
			    courtResultDisp.setCode(currentSettingResp.getCourtResult());
			    Collection<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = MessageUtil.postRequestListFilter(courtResultDisp, JuvenileCourtDecisionCodeResponseEvent.class);
			    if (crtDecisions != null)
			    {
				Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
				if (crtDecisionsItr.hasNext())
				{
				    crtResultDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
				    if (crtResultDecision != null)
				    {
					if (crtResultDecision.getResetAllowed()!=null && crtResultDecision.getResetAllowed().equalsIgnoreCase("Y") || crtResultDecision.getResetAllowed().equalsIgnoreCase("R")) //reset required is Y or R
					{
					    isReset = true;
					}
				    }
				}
			    }

			    //update the existing record;
			    UpdateJJSCLDetentionSettingEvent detentionEvt = (UpdateJJSCLDetentionSettingEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.UPDATEJJSCLDETENTIONSETTING);
			    detentionEvt.setDocketEventId(currentSettingResp.getDocketEventId());
			    detentionEvt.setCourtId(currentSettingResp.getCourt());
			    detentionEvt.setHearingType(currentSettingResp.getHearingType());
			    detentionEvt.setDetentionId(detentionId);
			    if (isReset)
			    {
				detentionEvt.setTransferTo(currentSettingResp.getTransferTo());
			    }
			    else
			    {
				detentionEvt.setTransferTo(null);
			    }
			    detentionEvt.setHearingDisposition(currentSettingResp.getDisposition());
			    detentionEvt.setHearingResult(currentSettingResp.getCourtResult());
			  //task 187260
			    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty())
				detentionEvt.setResettoDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
			    detentionEvt.setGalName(currentSettingResp.getGalName());
			    detentionEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
			    detentionEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));
			    detentionEvt.setAttorneyConnection(currentSettingResp.getAttorneyConnection());
			    detentionEvt.setAttorneyName(currentSettingResp.getAttorneyName());
			    detentionEvt.setBarNumber(currentSettingResp.getBarNum());
			    currentSettingResp.setOldattorneyName(currentSettingResp.getAttorneyName());
			    currentSettingResp.setOldattorneyConnection(currentSettingResp.getAttorneyConnection());
			  //attorney 2 details
			    detentionEvt.setAttorney2Name(currentSettingResp.getAttorney2Name());
			    detentionEvt.setAttorney2Connection(currentSettingResp.getAttorney2Connection());
			    detentionEvt.setAttorney2BarNum(currentSettingResp.getAttorney2BarNum());
			    //task 168662
			    detentionEvt.setInterpreter(currentSettingResp.getInterpreter());
			    /*if (currentSettingResp.getAttorney2BarNum() != null && currentSettingResp.getAttorney2BarNum().equals(""))
			    {
				detentionEvt.setAttorney2BarNum(null);
			    }
			    else
			    {
				detentionEvt.setAttorney2BarNum(currentSettingResp.getAttorney2BarNum());
			    }*/
			    //
			    /*if (currentSettingResp.getBarNum() != null && currentSettingResp.getBarNum().equals(""))
			    {
				detentionEvt.setBarNumber(null);
			    }
			    else
			    {
				detentionEvt.setBarNumber(currentSettingResp.getBarNum());
			    }*/
			    if (byPass == null)
			    {
        			    detentionEvt.setAtyConfirmation("YES");
        			    currentSettingResp.setAtyConfirmation("YES");
			    }
			    else
			    {
				currentSettingResp.setAtyBypass("YES");
				//if attorney is not confirmed pull the existing attorney deatils from detention
				if (currentSettingResp.getAtyConfirmation() == null || currentSettingResp.getAtyConfirmation().isEmpty())
				    {
				    	GetJJSDetentionbyOIDEvent courtEvent = (GetJJSDetentionbyOIDEvent) EventFactory
			                        .getInstance(JuvenileCaseControllerServiceNames.GETJJSDETENTIONBYOID);
				    	courtEvent.setId(currentSettingResp.getDocketEventId());
			                CompositeResponse officerComposite = MessageUtil.postRequest(courtEvent);

			                DocketEventResponseEvent resp= (DocketEventResponseEvent) MessageUtil.filterComposite(
			                        officerComposite, DocketEventResponseEvent.class);
					
			                if(resp!=null)
			                {
			                    detentionEvt.setAttorneyName(resp.getAttorneyName());
			                    detentionEvt.setAttorneyConnection(resp.getAttorneyConnection());
			                    detentionEvt.setBarNumber(resp.getBarNum());
			                }
					
				    }				
			    }
			    
			    if (currentSettingResp.getGalBarNum() != null && currentSettingResp.getGalBarNum().equals(""))
			    {
				detentionEvt.setGalBarNum(null);
			    }
			    else
			    {
				detentionEvt.setGalBarNum(currentSettingResp.getGalBarNum());
			    }
			  
			    if (StringUtil.isEmpty(currentSettingResp.getComments()))
				detentionEvt.setComments(null); 
			    else
				detentionEvt.setComments(currentSettingResp.getComments());

			    detentionEvt.setNewRecordCreated(false);

			    CompositeResponse clDetnResp = MessageUtil.postRequest(detentionEvt);
			    DocketEventResponseEvent docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(clDetnResp, DocketEventResponseEvent.class);
			    Object errorResponse = MessageUtil.filterComposite(clDetnResp, ErrorResponseEvent.class);
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
			    if (docEvtResp != null && docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
			    {
				isRecordUpdated = true;
				/*//83690
				if (!currentSettingResp.getSeqNum().equals("10") && docEvtResp.getResetHearingUpdateFlag().equalsIgnoreCase("true"))
				{
				    if (currentSettingResp.getResetHearingType()!=null &&  !currentSettingResp.getResetHearingType().equalsIgnoreCase(originalSettingResp.getResetHearingType()))
				    {
					GetJJSCLDetentionByChainAndSeqNumEvent detentionRec = (GetJJSCLDetentionByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAINANDSEQNUM);
					detentionRec.setChainNumber(currentSettingResp.getChainNumber());
					detentionRec.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum()) + 10));
					dispatch.postEvent(detentionRec);
					CompositeResponse resp = (CompositeResponse) dispatch.getReply();

					List<DocketEventResponseEvent> docketResps = MessageUtil.compositeToList(resp, DocketEventResponseEvent.class);
					Iterator<DocketEventResponseEvent> docEvtItr = docketResps.iterator();

					if (docEvtItr.hasNext())
					{
					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docEvtItr.next();
					    //action date and time (derived value) gets persisted in the court date and court time field on update of court action
					    if (docResp != null)
					    {
						detentionEvt = (UpdateJJSCLDetentionSettingEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.UPDATEJJSCLDETENTIONSETTING);
						detentionEvt.setDocketEventId(docResp.getDocketEventId());
						detentionEvt.setNewRecordCreated(false);

						//bug #83690
						if (currentSettingResp.getResetHearingType() != null && !currentSettingResp.getResetHearingType().equals(originalSettingResp.getResetHearingType()))
						{
						    detentionEvt.setHearingType(currentSettingResp.getResetHearingType());
						}
						clDetnResp = MessageUtil.postRequest(detentionEvt);
						docEvtResp = (DocketEventResponseEvent) MessageUtil.filterComposite(clDetnResp, DocketEventResponseEvent.class);
						errorResponse = MessageUtil.filterComposite(clDetnResp, ErrorResponseEvent.class);
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
						if (docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
						{
						  isSubsequentRecUpdated = true;
						}
					    }
					}
				    }
				}*/
			    }
			    // after update
			    if (currentSettingResp.getCourtResult() != null && !currentSettingResp.getCourtResult().isEmpty())
			    {
				/**
				 * 4.9.12 - detention. US 71272: If the
				 * hearing�s RESULT or DISPOSITION has the
				 * *Action {DETENTION_COURT_DECISIONS.Action} of
				 * "Detained" and the juvenile�s header record
				 * FacilityStatus is not null; and, the setting
				 * is the first setting in the chain with
				 * �Action = Detained�, the hearing�s court date
				 * should be used to populate the juvenile�s
				 * Header.DetainedDate attribute. This
				 * information will be applied to the most
				 * recent facility admission_release record
				 * where the Booking Referral = referral of the
				 * calendar record. See ....\Detention Court
				 * Decisions CORRECTED09_28_18.docx
				 */
				/**
				 * 3.4.6.22 - district court U.S. 74417: If this
				 * is the first detention hearing setting in the
				 * chain with a Result or Disposition that has
				 * the associated action of "Detained" and the
				 * juvenile is in a facility, the Header
				 * record�s DetainedDate attribute should be
				 * populated with the setting�s court
				 * date/hearing date. This information will be
				 * applied to the most recent facility
				 * admission_release record where the Booking
				 * Referral = referral of the calendar record
				 * and the juvenile�s header record
				 * FacilityStatus is not null.
				 */

				boolean updateFacilityDetails = false;
				JuvenileFacilityHeaderResponseEvent headerResp = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(currentSettingJuvenileNum);
				//result.
				/* GetJuvenileCourtDecisionCodesEvent courtResultEvt = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
				 courtResultEvt.setCode(currentSettingResp.getCourtResult());
				 Collection<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = MessageUtil.postRequestListFilter(courtResultEvt, JuvenileCourtDecisionCodeResponseEvent.class);
				 if (crtDecisions != null)
				 {
				Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
				while (crtDecisionsItr.hasNext())
				{
				    JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();*/
				if (crtResultDecision != null)
				{
				    if (crtResultDecision.getAction()!=null && (crtResultDecision.getAction().equalsIgnoreCase("DETAINED")||crtResultDecision.getAction().equalsIgnoreCase("OFF DOCKET/RESET")))// added check "OFF DOCKET/RESET" for 186495 for US 186451
				    {
					if ((currentSettingResp.getHearingType()!=null && currentSettingResp.getHearingType().equalsIgnoreCase("DT") || currentSettingResp.getHearingType().equalsIgnoreCase("PC")) && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
					{

					    if (headerResp != null && headerResp.getFacilityStatus() != null && !headerResp.getFacilityStatus().isEmpty())
					    {
						updateFacilityDetails = true;
					    }
					}
				    }
				}
				/*}
				}*/
				//disposition
				if (currentSettingResp.getDisposition() != null && currentSettingResp.getDisposition().isEmpty())
				{
				    GetJuvenileCourtDecisionCodesEvent courtDisp = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
				    courtDisp.setCode(currentSettingResp.getDisposition());
				    Collection<JuvenileCourtDecisionCodeResponseEvent> crtDispDecisions = MessageUtil.postRequestListFilter(courtDisp, JuvenileCourtDecisionCodeResponseEvent.class);
				    if (crtDecisions != null)
				    {
					Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDispDecisions.iterator();
					if (crtDecisionsItr.hasNext())
					{
					    JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
					    if (crtDecision != null)
					    {
						if (crtDecision.getAction()!=null && crtDecision.getAction().equalsIgnoreCase("DETAINED"))
						{
						    if ((currentSettingResp.getHearingType()!=null && currentSettingResp.getHearingType().equalsIgnoreCase("DT") || currentSettingResp.getHearingType().equalsIgnoreCase("PC")) && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
						    {
							if (headerResp != null && headerResp.getFacilityStatus() != null && !headerResp.getFacilityStatus().isEmpty())
							{
							    updateFacilityDetails = true;
							}
						    }
						}
					    }
					}
				    }
				}
				if (updateFacilityDetails)
				{
				    /* 3.4.6.11.1 District Court:	Activity: Update juvenile�s Header with Detention setting transfer data
				       Locate the juvenile�s Header record.  Change the NextHearingDate to the reset date for the current setting.*/

				    /*District court section 3.4.6.15.2
				     * U.S. 74417:  If this is the first court or detention hearing setting in the chain with a Result or Disposition that has the associated action of "Detained" and the juvenile is in a facility, 
				     * the Header record�s DetainedDate attribute should be populated with the setting�s court date/hearing date.*/

				    UpdateJJSHeaderFromDistrictCourtEvent updateHeaderEvt = (UpdateJJSHeaderFromDistrictCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSHEADERFROMDISTRICTCOURT);
				    if(currentSettingResp.getActionDate()!=null&&!currentSettingResp.getActionDate().isEmpty())//  for 186495 for US 186451
					updateHeaderEvt.setNextHearingDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
				    //bug 81615 Fixed.
				    if(currentSettingResp.getHearingType()!=null && currentSettingResp.getHearingType().equalsIgnoreCase("PC")){
					updateHeaderEvt.setProbableCauseDate(DateUtil.getCurrentDate());
				    }
				    else
				    {
					if (docketResponses != null)
					{
					    Collections.sort((List<DocketEventResponseEvent>) docketResponses, new Comparator<DocketEventResponseEvent>() {
						@Override
						public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
						{
						    if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
							return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
						    else
							return -1;
						}
					    });

					    Iterator<DocketEventResponseEvent> dockResp = docketResponses.iterator();

					    if (dockResp != null)
					    {
						DocketEventResponseEvent responseEvent = dockResp.next();
						if (responseEvent.getSeqNum() != null && responseEvent.getSeqNum().equalsIgnoreCase("10") && responseEvent.getHearingType() != null && responseEvent.getHearingType().equalsIgnoreCase("PC"))
						{
						    updateHeaderEvt.setProbableCauseDate(DateUtil.stringToDate(responseEvent.getCourtDate(), DateUtil.DATE_FMT_1));
						}
					    }
					}
				    }
				    
				    updateHeaderEvt.setJuvenileNumber(currentSettingJuvenileNum);
				    CompositeResponse headerResponse = MessageUtil.postRequest(updateHeaderEvt);
				    JuvenileFacilityHeaderResponseEvent headerRespEvt = (JuvenileFacilityHeaderResponseEvent) MessageUtil.filterComposite(headerResponse, JuvenileFacilityHeaderResponseEvent.class);
				    Object headerErrorResp = MessageUtil.filterComposite(headerResponse, ErrorResponseEvent.class);
				    if (headerErrorResp != null)
				    {
					ErrorResponseEvent myEvt = (ErrorResponseEvent) headerErrorResp;
					try
					{
					    handleFatalUnexpectedException(myEvt.getMessage());
					}
					catch (GeneralFeedbackMessageException e)
					{
					    e.printStackTrace();
					}
				    }
				    if (currentSettingResp.getSeqNum() != null && currentSettingResp.getSeqNum().equalsIgnoreCase("10"))
				    {
					UpdateJJSDetentionFromDistrictCourtEvent updateJJSDetentionEvt = (UpdateJJSDetentionFromDistrictCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSDETENTIONFROMDISTRICTCOURT);
					updateJJSDetentionEvt.setJuvenileNumber(currentSettingJuvenileNum);
					updateJJSDetentionEvt.setDetainedDate(DateUtil.stringToDate(currentSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));

					if (currentSettingResp.getCourtResult() != null && (currentSettingResp.getCourtResult().equalsIgnoreCase("SRR") || currentSettingResp.getCourtResult().equalsIgnoreCase("SRA")))
					{
					    JuvenileFacilityHeaderResponseEvent header = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(currentSettingJuvenileNum);
					    if (header != null)
					    {
						if (header.getFacilityStatus()!=null && header.getFacilityStatus().equalsIgnoreCase("D"))
						{
						    /**
						     * US 73573: If RESULT was
						     * changed to �SRR� (Shelter
						     * Request - Released) or
						     * �SRA� (Shelter Request
						     * Accepted) and the
						     * FacilityStatus of the
						     * Header record = �D�,
						     * change the Admission
						     * Reason to �SHL� (Shelter)
						     * on the most recent
						     * facility record
						     * associated to the setting
						     * record: {where current
						     * juvenile number,
						     * JuvenileBookingReferral
						     * of the admission record =
						     * JUVENILE_DETENTION_HEARING
						     * .Juvenile Number and
						     * ReferralNumber} and the
						     * FacilityAdmitDate
						     * immediately precedes or
						     * is equal to the setting�s
						     * HearingDate.
						     */
						    updateJJSDetentionEvt.setAdmitReason("SHL");
						    updateJJSDetentionEvt.setAdmitDate(DateUtil.stringToDate(currentSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
						}
					    }
					}

					CompositeResponse response = MessageUtil.postRequest(updateJJSDetentionEvt);
					JuvenileDetentionFacilitiesResponseEvent jjsdetentionResp = (JuvenileDetentionFacilitiesResponseEvent) MessageUtil.filterComposite(response, JuvenileDetentionFacilitiesResponseEvent.class);
					Object errorResp = MessageUtil.filterComposite(response, ErrorResponseEvent.class);
					if (errorResp != null)
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
				    }
				} //3.4.6.22

				/*3.4.6.11 District Court Subflow: Determine Detention setting transfer status
				If the current setting is for a Detention Calendar record, has Result = Transfer, and the setting has been transferred to a Referee court (TRANSFER TO value associated to Referee Court = Yes), the application will need to upate the juvenile�s facility header (Next Hearing Date) with the ResetDate/screen Action Date.
				NOTE:  U.S. 78282 - Detention requirements document will process the following (current requirments resulted in creation of 2 new detention records):  In addition, the application will determine if there is a subsequent detention calendar record following this setting in the same chain as the current setting.  If so, update the next detention calendar record with the transfer request info. 
				 If there is no �next/subsequent� calendar record, a new calendar record will need to be created to capture the transfer.*/

				/*3.4.6.11.1 District Court:Activity: Update subsequent Detention setting with Detention setting transfer data
				  If a subsequent detention calander record (Chain Sequence number is larger than current chain sequence number AND is the next one in sequential order) in the same chain as the current setting, 
				  where the chain sequence number is greater than the current setting�s chain sequence number is located, change it�s HearingDate to the ResetDate/screen Action Date of the current setting.
				*/
				/*GetJuvenileCourtDecisionCodesEvent courtDisp = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
				courtDisp.setCode(currentSettingResp.getCourtResult());
				Collection<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = MessageUtil.postRequestListFilter(courtDisp, JuvenileCourtDecisionCodeResponseEvent.class);
				if (crtDecisions != null)
				{
				    Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
				    while (crtDecisionsItr.hasNext())
				    {
					JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();*/
				if (crtResultDecision != null)
				{
				    if (crtResultDecision.getResetAllowed()!=null && crtResultDecision.getResetAllowed().equalsIgnoreCase("Y") || crtResultDecision.getResetAllowed().equalsIgnoreCase("R")) //reset required is Y or R
				    {
					//if (currentSettingResp.getTransferTo() != null && !currentSettingResp.getTransferTo().isEmpty())
					//{
					    //TODO
					    // Confirmed with carla. No subsequent record will be created. IMplementation Bug : 
					    /*  GetJuvenileCourtsEvent courtEvent = (GetJuvenileCourtsEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTS);
					      courtEvent.setCourt(currentSettingResp.getTransferTo());
					      CompositeResponse courtRoomResp = MessageUtil.postRequest(courtEvent);
					      JuvenileCourtResponseEvent crtRespEvt = (JuvenileCourtResponseEvent) MessageUtil.filterComposite(courtRoomResp, JuvenileCourtResponseEvent.class);*/

					    /*  * 3.4.6.2 - district court document. Check carlas Email 
					      * If the court is a detention court: the application will default to the search detailed in the Process Juvenile Detention Hearings.doc*/

					    /*   if (crtRespEvt != null)
					    {
					    //TODO
					    if (crtRespEvt.getRefereesCourtInd() != null && !crtRespEvt.getRefereesCourtInd().equalsIgnoreCase("Y"))
					    {
					        GetJJSCLDetentionByChainAndSeqNumEvent detentionRec = (GetJJSCLDetentionByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAINANDSEQNUM);
					        detentionRec.setChainNumber(currentSettingResp.getChainNumber());
					        detentionRec.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum()) + 10));
					        dispatch.postEvent(detentionRec);
					        CompositeResponse resp = (CompositeResponse) dispatch.getReply();
					        if (resp != null)
					        {
					    	//update the subsequent record with current setting details.
					    	UpdateJJSCLDetentionSettingEvent updateEvt = (UpdateJJSCLDetentionSettingEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.UPDATEJJSCLDETENTIONSETTING);
					    	updateEvt.setChainNumber(currentSettingResp.getChainNumber());
					    	updateEvt.setSeqNumber(String.valueOf(Integer.valueOf(currentSettingResp.getSeqNum()) + 10));
					    	updateEvt.setHearingType("DT");
					    	updateEvt.setCourtId(currentSettingResp.getTransferTo());
					    	if (currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true"))
					    	{
					    	    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					    	    {
					    		updateEvt.setCourtDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
					    	    }
					    	    else
					    	    {
					    		updateEvt.setCourtDate(DateUtil.stringToDate(courtHearingForm.getCourtDate(), DateUtil.DATE_FMT_1));
					    	    }
					    	    //court Time
					    	    if (currentSettingResp.getActionTime() != null && !currentSettingResp.getActionTime().isEmpty() && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
					    	    {
					    		updateEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime()));
					    	    }
					    	    else
					    	    {
					    		updateEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getCourtTime()));
					    	    }
					    	}
					    	else
					    	{
					    	    updateEvt.setCourtDate(DateUtil.stringToDate(courtHearingForm.getCourtDate(), DateUtil.DATE_FMT_1));
					    	    updateEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getCourtTime()));
					    	}
					    	updateEvt.setHearingDisposition(null);
					    	updateEvt.setHearingResult(null);
					    	updateEvt.setNewRecordCreated(false);
					    	CompositeResponse compResp = MessageUtil.postRequest(updateEvt);
					    	DocketEventResponseEvent docketResp = (DocketEventResponseEvent) MessageUtil.filterComposite(compResp, DocketEventResponseEvent.class);
					    	Object errResp = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
					    	if (errResp != null)
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
					    	if (docketResp.getUpdateFlag().equalsIgnoreCase("true"))
					    	{
					    	    isRecordUpdated = true;
					    	}
					        }
					        else
					        {
					    	UpdateJJSCLDetentionSettingEvent updateEvt = (UpdateJJSCLDetentionSettingEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.UPDATEJJSCLDETENTIONSETTING);
					    	updateEvt.setChainNumber(currentSettingResp.getChainNumber());
					    	updateEvt.setSeqNumber(seqNumForNewRecord);
					    	updateEvt.setHearingType("DT");
					    	updateEvt.setCourtId(currentSettingResp.getTransferTo());
					    	if (currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true"))
					    	{
					    	    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
					    	    {
					    		updateEvt.setCourtDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
					    	    }
					    	    else
					    	    {
					    		updateEvt.setCourtDate(DateUtil.stringToDate(courtHearingForm.getCourtDate(), DateUtil.DATE_FMT_1));
					    	    }
					    	    //court Time
					    	    if (currentSettingResp.getActionTime() != null && !currentSettingResp.getActionTime().isEmpty() && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
					    	    {
					    		updateEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime()));
					    	    }
					    	    else
					    	    {
					    		updateEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getCourtTime()));
					    	    }
					    	}
					    	else
					    	{
					    	    updateEvt.setCourtDate(DateUtil.stringToDate(courtHearingForm.getCourtDate(), DateUtil.DATE_FMT_1));
					    	    updateEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getCourtTime()));
					    	}
					    	updateEvt.setHearingDisposition(null);
					    	updateEvt.setHearingResult(null);

					    	//populate other values from the currentSetting.
					    	updateEvt.setAlternateSettingInd(currentSettingResp.getAlternateSettingInd());
					    	updateEvt.setAttorneyConnection(currentSettingResp.getAttorneyConnection());
					    	updateEvt.setAttorneyName(currentSettingResp.getAttorneyName());
					    	updateEvt.setBarNumber(currentSettingResp.getBarNum());
					    	updateEvt.setComments(currentSettingResp.getComments());
					    	updateEvt.setIssueFlag(currentSettingResp.getIssueFlag());
					    	updateEvt.setJuvenileNumber(currentSettingResp.getJuvenileNumber());
					    	updateEvt.setPetitionNumber(currentSettingResp.getJuvenileNumber());
					    	updateEvt.setReferralNumber(currentSettingResp.getReferralNum());
					    	updateEvt.setTransferTo(currentSettingResp.getTransferTo());
					    	updateEvt.setNewRecordCreated(true);

					    	CompositeResponse compResp = MessageUtil.postRequest(updateEvt);
					    	DocketEventResponseEvent docketResp = (DocketEventResponseEvent) MessageUtil.filterComposite(compResp, DocketEventResponseEvent.class);
					    	Object errResp = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
					    	if (errResp != null)
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
					        }
					    }
					    }*/
					    /*  U.S. 11646:  If the RESULT�s associated Require Reset = �Yes� or �Required� and user has supplied a valid reset date that is not the same as the Hearing date of the current setting a new Detention calendar record needs to be created:
					    Attributes will be populated with current setting�s data or new user-supplied data, except as follows:
					    Sequence Number = current setting�s value + 10.
					    Hearing Type = DT
					    CourtID = value of current setting�s TransferCourt
					    HearingDate = value of Reset To field for current setting�s
					    Disposition = NULL
					    Result = NULL */

					   /* if (currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true") && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
					    {
						isNewRecordCreated = createNewDetentionCalenderRecord(currentSettingResp, originalSettingResp, seqNumForNewRecord);
					    }*/
					//} //transfer create new record.

					//bug #83429
					

					    
					    //bug #83894
					    if (currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true") && currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
					    {
							isNewRecordCreated = createNewDetentionCalenderRecord(currentSettingResp, originalSettingResp, seqNumForNewRecord, detentionId);
							
					    }

					/*if ((currentSettingResp.getHearingType()!=null && currentSettingResp.getHearingType().equalsIgnoreCase("DT") || currentSettingResp.getHearingType().equalsIgnoreCase("PC")) && currentSettingResp.getActionDate()!=null && !currentSettingResp.getActionDate().equalsIgnoreCase(originalSettingResp.getActionDate()))
					{
					    //private method to create new detention calender record.
					    isNewRecordCreated = createNewDetentionCalenderRecord(currentSettingResp, originalSettingResp, seqNumForNewRecord);
					}*/
				    } // reset date check.

				    if (crtResultDecision.getAction()!=null && crtResultDecision.getAction().equalsIgnoreCase("Released"))
				    {
					/**
					 * 4.9.13 4.9.14 Activity: Delete
					 * Released juvenile�s future settings
					 * If the associated *Action = Released
					 * for a Result, delete all future
					 * Detention setting records in the
					 * chain of the setting. See
					 * ....\Detention Court Decisions
					 * CORRECTED09_28_18.docx
					 */
					DeleteJJSCLDetentionSettingEvent deleteDetentionSettingEvt = (DeleteJJSCLDetentionSettingEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.DELETEJJSCLDETENTIONSETTING);
					deleteDetentionSettingEvt.setChainNumber(currentSettingResp.getChainNumber());

					CompositeResponse compResp = MessageUtil.postRequest(deleteDetentionSettingEvt);
					DocketEventResponseEvent docket = (DocketEventResponseEvent) MessageUtil.filterComposite(compResp, DocketEventResponseEvent.class);
					Object errResp = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
					if (errResp != null)
					{
					    ErrorResponseEvent myEvt = (ErrorResponseEvent) errResp;
					    try
					    {
						handleFatalUnexpectedException(myEvt.getMessage());
					    }
					    catch (GeneralFeedbackMessageException e)
					    {
						e.printStackTrace();
					    }
					}

					/**
					 * As of 7/13/2017: If the *Action
					 * associated to a DecisionCode =
					 * Released, send a release email to the
					 * *CLMs of the facility in which the
					 * juvenile is currently detained, as
					 * well as to each JPO assigned to an
					 * active/pending casefile for the
					 * juvenile. See ..\Detention Court
					 * Decisions CORRECTED09_28_18.docx
					 */

					// udpate is successful
					if (docEvtResp != null && docEvtResp.getUpdateFlag() != null && docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
					{
					    JJSFacility facility = JuvenileFacilityHelper.getCurrentFacilityRecord(currentSettingJuvenileNum);
					    String detainedFacilityDesc = "";
					    if (facility != null)
					    {
						detainedFacilityDesc = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, facility.getDetainedFacility());
					    }

					    JuvenileProfileReferralListResponseEvent profileResp = JuvenileFacilityHelper.getCasefilesFromReferral(currentSettingJuvenileNum, currentSettingResp.getReferralNum());
					    Collection<JuvenileCasefileReferral> casefilesResp = profileResp.getCasefileReferrals();

					    Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
						@Override
						public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
						{
						    if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
							return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());
						    else
							return -1;
						}
					    }));

					    Iterator<JuvenileCasefileReferral> casefileItr = casefilesResp.iterator();
					    while (casefileItr.hasNext())
					    {
						JuvenileCasefileReferral casefileReferral = casefileItr.next();
						if (casefileReferral != null)
						{
						    //jpo notification
						    OfficerProfile officerProfile = casefileReferral.getCaseFile().getProbationOfficer();
						    if (officerProfile != null && officerProfile.getLogonId() != null)
						    {
							JuvenileDetentionNotificationResponseEvent respEvt = new JuvenileDetentionNotificationResponseEvent();
							respEvt.setSubject("RELEASE ordered for " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum + " from " + detainedFacilityDesc);
							respEvt.setIdentity(officerProfile.getLogonId());
							respEvt.setJuvenileNum(currentSettingJuvenileNum);

							StringBuffer message = new StringBuffer(100);
							message.append(" On ");
							message.append(currentSettingResp.getCourtDate());
							message.append(" Court 001");
							message.append(" ordered the release of ");
							message.append(currentSettingResp.getJuvenileName());
							message.append(" Juvenile#: ");
							message.append(currentSettingJuvenileNum);
							message.append(" from ");
							message.append(detainedFacilityDesc);
							respEvt.setNotificationMessage(message.toString());

							CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
							notificationEvent.setNotificationTopic("JC.DETENTION.JPO.DETAINED");
							notificationEvent.setSubject(respEvt.getSubject());
							notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
							notificationEvent.addContentBean(respEvt);
							CompositeResponse response = MessageUtil.postRequest(notificationEvent);
							MessageUtil.processReturnException(response);

							//send email
							Iterator<OfficerProfile> officerProfileDetails = OfficerProfile.findAll("logonId", officerProfile.getLogonId());
							if (officerProfileDetails != null)
							{
							    if (officerProfileDetails.hasNext())
							    {
								OfficerProfile profile = officerProfileDetails.next();
								if (profile != null && profile.getEmail() != null)
								{
								    SendEmailEvent sendEmailEvent = new SendEmailEvent();
								    sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
								    sendEmailEvent.addToAddress(profile.getEmail());
								    sendEmailEvent.setMessage(message.toString());
								    sendEmailEvent.setSubject("RELEASE ordered for " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum + " from " + detainedFacilityDesc);
								    dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
								    dispatch.postEvent(sendEmailEvent);
								}
							    }
							}
						    }
						    //CLM Notification
						    if (officerProfile != null && officerProfile.getManagerLogonId() != null)
						    {
							JuvenileDetentionNotificationResponseEvent respEvt = new JuvenileDetentionNotificationResponseEvent();
							respEvt.setSubject("RELEASE ordered for " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum + " from " + detainedFacilityDesc);
							respEvt.setIdentity(officerProfile.getManagerLogonId());
							respEvt.setJuvenileNum(currentSettingJuvenileNum);
							StringBuffer message = new StringBuffer(100);
							
							if (headerResp != null && headerResp.getFacilityStatus() != null && headerResp.getFacilityStatus().equalsIgnoreCase("N"))
							{
							    respEvt.setIdentity(officerProfile.getManagerLogonId());
							    respEvt.setJuvenileNum(currentSettingJuvenileNum);

							    message.append(" Court#: 001 ordered the release of ");
							    message.append(currentSettingResp.getJuvenileName());
							    message.append(", Juvenile #: " + currentSettingJuvenileNum);
							    message.append(" under supervision# ");
							    message.append(casefileReferral.getCaseFile().getCasefileId());
							    message.append(" and Referral#: ");
							    message.append(casefileReferral.getCaseFile().getCasefileControllingReferralId());
							    message.append(" from your facility.");
							    respEvt.setNotificationMessage(message.toString());

							    CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
							    notificationEvent.setNotificationTopic("JC.DETENTION.CLM.DETAINED");
							    notificationEvent.setSubject(respEvt.getSubject());
							    notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
							    notificationEvent.addContentBean(respEvt);
							    CompositeResponse response = MessageUtil.postRequest(notificationEvent);
							    MessageUtil.processReturnException(response);

							    //email
							    Iterator<OfficerProfile> officerProfileDetails = OfficerProfile.findAll("logonId", officerProfile.getManagerLogonId());
							    if (officerProfileDetails != null)
							    {
								if (officerProfileDetails.hasNext())
								{
								    OfficerProfile profile = officerProfileDetails.next();
								    if (profile != null && profile.getEmail() != null)
								    {
									SendEmailEvent sendEmailEvent = new SendEmailEvent();
									sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
									sendEmailEvent.addToAddress(profile.getEmail());
									sendEmailEvent.setMessage(message.toString());
									sendEmailEvent.setSubject("RELEASE ordered for " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum + " from " + detainedFacilityDesc);
									dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
									dispatch.postEvent(sendEmailEvent);
								    }
								}
							    }
							}
						    }
						}
					    }
					}
				    }
				    /**
				     * As of 7/13/2017: If the *Action
				     * associated to a DecisionCode = Detained,
				     * send a detention email to each JPO
				     * assigned to an active/pending casefile
				     * for the juvenile, as well as the *CLMs of
				     * the detention facility. See ..\Detention
				     * Court Decisions CORRECTED09_28_18.docx
				     */
				    if (crtResultDecision.getAction()!=null && crtResultDecision.getAction().equalsIgnoreCase("DETAINED"))
				    {
					if (docEvtResp.getUpdateFlag() != null && docEvtResp.getUpdateFlag().equalsIgnoreCase("true"))
					{

					    JuvenileProfileReferralListResponseEvent profileResp = JuvenileFacilityHelper.getCasefilesFromReferral(currentSettingJuvenileNum, currentSettingResp.getReferralNum());
					    Collection<JuvenileCasefileReferral> casefilesResp = profileResp.getCasefileReferrals();

					    Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
						@Override
						public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
						{
						    if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
							return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());
						    else
							return -1;
						}
					    }));

					    Iterator<JuvenileCasefileReferral> casefileItr = casefilesResp.iterator();
					    while (casefileItr.hasNext())
					    {
						JuvenileCasefileReferral casefileReferral = casefileItr.next();
						if (casefileReferral != null)
						{
						    //jpo notification
						    OfficerProfile officerProfile = casefileReferral.getCaseFile().getProbationOfficer();
						    if (officerProfile != null && officerProfile.getLogonId() != null)
						    {
							JuvenileDetentionNotificationResponseEvent respEvt = new JuvenileDetentionNotificationResponseEvent();
							respEvt.setSubject("DETAIN " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum);
							respEvt.setIdentity(officerProfile.getLogonId());
							respEvt.setJuvenileNum(currentSettingJuvenileNum);

							StringBuffer message = new StringBuffer(100);
							message.append(" On ");
							message.append(currentSettingResp.getCourtDate());
							message.append(" Court ");
							message.append(currentSettingResp.getCourt());
							message.append(" ordered ");
							message.append(currentSettingResp.getJuvenileName());
							message.append(" Juvenile#: ");
							message.append(currentSettingJuvenileNum);
							message.append(" to Be detained ");
							respEvt.setNotificationMessage(message.toString());

							CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
							notificationEvent.setNotificationTopic("JC.DETENTION.JPO.DETAINED");
							notificationEvent.setSubject(respEvt.getSubject());
							notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
							notificationEvent.addContentBean(respEvt);
							CompositeResponse response = MessageUtil.postRequest(notificationEvent);
							MessageUtil.processReturnException(response);

							//send email
							Iterator<OfficerProfile> officerProfileDetails = OfficerProfile.findAll("logonId", officerProfile.getLogonId());
							if (officerProfileDetails != null)
							{
							    if (officerProfileDetails.hasNext())
							    {
								OfficerProfile profile = officerProfileDetails.next();
								if (profile != null && profile.getEmail() != null)
								{
								    SendEmailEvent sendEmailEvent = new SendEmailEvent();
								    sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
								    sendEmailEvent.addToAddress(profile.getEmail());
								    sendEmailEvent.setMessage(message.toString());
								    sendEmailEvent.setSubject("DETAIN " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum);
								    dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
								    dispatch.postEvent(sendEmailEvent);
								}
							    }
							}
						    }
						    //CLM Notification
						    if (officerProfile != null && officerProfile.getManagerLogonId() != null)
						    {
							JuvenileDetentionNotificationResponseEvent respEvt = new JuvenileDetentionNotificationResponseEvent();
							respEvt.setSubject("DETAIN " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum);
							respEvt.setIdentity(officerProfile.getManagerLogonId());
							respEvt.setJuvenileNum(currentSettingJuvenileNum);
							StringBuffer message = new StringBuffer(100);
							if (headerResp.getFacilityStatus() != null  && headerResp.getFacilityStatus().equalsIgnoreCase("N"))
							{
							    respEvt.setIdentity(officerProfile.getManagerLogonId());
							    respEvt.setJuvenileNum(currentSettingJuvenileNum);

							    message.append(" Court#: ");
							    message.append(currentSettingResp.getCourt());
							    message.append(" ordered the detention of ");
							    message.append(currentSettingResp.getJuvenileName());
							    message.append(" under supervision# ");
							    message.append(casefileReferral.getCaseFile().getCasefileId());
							    message.append(" and Referral#: ");
							    message.append(casefileReferral.getCaseFile().getCasefileControllingReferralId());
							    message.append(" from your facility.");
							    respEvt.setNotificationMessage(message.toString());

							    CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
							    notificationEvent.setNotificationTopic("JC.DETENTION.CLM.DETAINED");
							    notificationEvent.setSubject(respEvt.getSubject());
							    notificationEvent.addIdentity("respEvent", (IAddressable) respEvt);
							    notificationEvent.addContentBean(respEvt);
							    CompositeResponse response = MessageUtil.postRequest(notificationEvent);
							    MessageUtil.processReturnException(response);

							    //email
							    Iterator<OfficerProfile> officerProfileDetails = OfficerProfile.findAll("logonId", officerProfile.getManagerLogonId());
							    if (officerProfileDetails != null)
							    {
								if (officerProfileDetails.hasNext())
								{
								    OfficerProfile profile = officerProfileDetails.next();
								    if (profile != null && profile.getEmail() != null)
								    {
									SendEmailEvent sendEmailEvent = new SendEmailEvent();
									sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
									sendEmailEvent.addToAddress(profile.getEmail());
									sendEmailEvent.setMessage(message.toString());
									sendEmailEvent.setSubject("DETAIN " + currentSettingResp.getJuvenileName() + ", Juvenile#: " + currentSettingJuvenileNum);
									dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
									dispatch.postEvent(sendEmailEvent);
								    }
								}
							    }
							}
						    }
						}
					    }
					}
				    }
				}
			    }
			} //detention check
			  //update the original Setting map;
			if (isRecordUpdated)
			{
			    if (originalDktSearchResultsMap.containsKey(currentSettingResp.getDocketEventIdKey()))
			    {
				DocketEventResponseEvent origDocket = new DocketEventResponseEvent();
				try
				{
				    BeanUtils.copyProperties(origDocket, currentSettingResp);
				}
				catch (IllegalAccessException e)
				{
				    e.printStackTrace();
				}
				catch (InvocationTargetException e)
				{
				    e.printStackTrace();
				}
				originalDktSearchResultsMap.put(currentSettingResp.getDocketEventIdKey(), origDocket);
				aRequest.getSession().setAttribute("originalSetting", originalDktSearchResultsMap);
			    }
			}
		    //} //else condition 
		}
	    }//for loop
	    if (!atleastOneSettingChanged || (!isRecordUpdated && !isNewRecordCreated && !isOnlySettingDeleted))
	    {
		ActionMessages messageHolder = new ActionMessages();
		messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.noChangeInSettingDetected"));
		aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
		saveMessages(aRequest, messageHolder);
		courtHearingForm.setCursorPosition("");
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    if (isRecordUpdated || isNewRecordCreated ||isOnlySettingDeleted)
	    {
		ActionMessages messageHolder = new ActionMessages();
		messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.settingRecordUpdated"));
		aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
		saveMessages(aRequest, messageHolder);
		courtHearingForm.setCursorPosition("");		
		return refreshPage(aMapping, aForm, aRequest, aResponse);
	    }
	}
	return aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY);
    }

    //
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
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setAction("courtHearing");
	AttorneyNameAndAddressResponseEvent attorneyRespEvt = null;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	if(form.getBarNumber()!=null &&!form.getBarNumber().isEmpty())
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
        	    form.setCursorPosition("barNum");
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
        	    //reset back on the object 
        	    if (form.getDcktEvtIdKey() != null && !form.getDcktEvtIdKey().isEmpty())
        	    {
        		DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtIdKey());
        		if (respEvt != null)
        		{
        		    respEvt.setAttorneyName(attorneyRespEvt.getAttyName());
        		    respEvt.setBarNum(attorneyRespEvt.getBarNum());
        		    form.getDktSearchResultsMap().put(form.getDcktEvtIdKey(), respEvt);
        		}
        	    }
        	    //reset back on the object 
        	    ActionMessages messageHolder = new ActionMessages();
        	    form.setAttorneyName(attorneyRespEvt.getAttyName());
        	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.barNumberValid"));
        	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
        	    saveMessages(aRequest, messageHolder);
        	    form.setBarNumberValidated(true);
        	}
        	form.setPrevAction(form.getAction());
	}
	else
	{	    
	    form.setAction("validateBarNumber");
	    //reset back on the object 
	    if (form.getDcktEvtIdKey() != null && !form.getDcktEvtIdKey().isEmpty())
	    {
		DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtIdKey());
		if (respEvt != null)
		{
		    respEvt.setAttorneyName("");
		    respEvt.setBarNum("");
		    respEvt.setAttorneyConnection("");
		    respEvt.setAttorneyConnectionDesc("");
		    form.getDktSearchResultsMap().put(form.getDcktEvtIdKey(), respEvt);	
		    form.setBarNumberValidated(true);
		}
	    }
	}
	return aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY);
    }
    
    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward validateGALBarNumber(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setAction("courtHearing");
	AttorneyNameAndAddressResponseEvent attorneyRespEvt = null;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	if(form.getBarNumber()!=null &&!form.getBarNumber().isEmpty())
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
        	    form.setCursorPosition("#galBarNum-" + form.getDcktEvtIdKey());
        	    ActionErrors errors = new ActionErrors();
        	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Bar Number. Please Correct and Retry"));
        	    saveErrors(aRequest, errors);
        	    form.setAction("validateBarNumber");
        	    form.setGalName("");
        	    form.setPrevAction(form.getAction());
        	    //Bug 99389 clear out invalid bar num
        	    if (form.getDcktEvtIdKey() != null && !form.getDcktEvtIdKey().isEmpty())
        	    {
        		DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtIdKey());
        		if (respEvt != null)
        		{
        		    respEvt.setGalName( "" );
        		    respEvt.setGalBarNum( "" );
        		    form.getDktSearchResultsMap().put(form.getDcktEvtIdKey(), respEvt);
        		}
        	    }
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
        	    //reset back on the object 
        	    if (form.getDcktEvtIdKey() != null && !form.getDcktEvtIdKey().isEmpty())
        	    {
        		DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtIdKey());
        		if (respEvt != null)
        		{
        		    respEvt.setGalName( attorneyRespEvt.getAttyName() );
        		    respEvt.setGalBarNum( attorneyRespEvt.getBarNum() );
        		    form.getDktSearchResultsMap().put(form.getDcktEvtIdKey(), respEvt);
        		}
        	    }
        	    //reset back on the object 
        	    ActionMessages messageHolder = new ActionMessages();
        	    form.setGalName(attorneyRespEvt.getAttyName());
        	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.barNumberValid"));
        	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
        	    saveMessages(aRequest, messageHolder);
        	    form.setBarNumberValidated(true);
        	}
        	form.setPrevAction(form.getAction());
    	}
	else
	{	    
	    form.setAction("validateBarNumber");
	    //reset back on the object 
	    if (form.getDcktEvtIdKey() != null && !form.getDcktEvtIdKey().isEmpty())
	    {
		DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtIdKey());
		if (respEvt != null)
		{
		    respEvt.setGalName("");
		    respEvt.setGalBarNum("");		  
		    form.getDktSearchResultsMap().put(form.getDcktEvtIdKey(), respEvt);			    
		}
	    }
	}
	return aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY);
    }
  //add 2nd attorney bar validate
    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward validateAty2BarNumber(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setAction("courtHearing");
	AttorneyNameAndAddressResponseEvent attorneyRespEvt = null;
	if (form.getBarNumber() != null && !form.getBarNumber().isEmpty())
	{
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
	    form.setCursorPosition("#2ndAttorneyBarnum-" + form.getDcktEvtIdKey());
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Bar Number. Please Correct and Retry"));
	    saveErrors(aRequest, errors);
	    form.setAction("validateAty2BarNumber");
	    //form.setGalName("");
	    form.setPrevAction(form.getAction());
	    //Bug 99389 clear out invalid bar num
	    if (form.getDcktEvtIdKey() != null && !form.getDcktEvtIdKey().isEmpty())
	    {
		DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtIdKey());
		if (respEvt != null)
		{
		    respEvt.setAttorney2Name( "" );
		    respEvt.setAttorney2BarNum( "" );
		    form.getDktSearchResultsMap().put(form.getDcktEvtIdKey(), respEvt);
		}
	    }
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
	    form.setAction("validateAty2BarNumber");
	    //reset back on the object 
	    if (form.getDcktEvtIdKey() != null && !form.getDcktEvtIdKey().isEmpty())
	    {
		DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtIdKey());
		if (respEvt != null)
		{
		    respEvt.setAttorney2Name(attorneyRespEvt.getAttyName() );
		    respEvt.setAttorney2BarNum(attorneyRespEvt.getBarNum() );
		    form.getDktSearchResultsMap().put(form.getDcktEvtIdKey(), respEvt);
		}
	    }
	    //reset back on the object 
	    ActionMessages messageHolder = new ActionMessages();
	    //form.setGalName(attorneyRespEvt.getAttyName());
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.barNumberValid"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	    form.setBarNumberValidated(true);
	}
	form.setPrevAction(form.getAction());
    }
	else
	{
	    if (form.getDcktEvtIdKey() != null && !form.getDcktEvtIdKey().isEmpty())
	    {
		DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtIdKey());
		respEvt.setAttorney2Name("");
		respEvt.setAttorney2BarNum("");
		respEvt.setAttorney2Connection("");
		//respEvt.setAttorneyConnectionDesc(null);
		form.getDktSearchResultsMap().put(form.getDcktEvtIdKey(), respEvt);
	    }

	}
	return aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY);
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
	String pagerOffset = (String) aRequest.getParameter("pager.offset");
	HttpSession session = aRequest.getSession();
	session.setAttribute("Pager_Offset", pagerOffset);
	form.setAction("searchAttorneyCourtAction");
	//Bug #70017 - pagination issue - attorney name not getting set when single result - fixed
	if (form.getDktSearchResultsMap().size() == 1 && form.getAttorneyName()!=null && form.getAttorneyName().equalsIgnoreCase(""))
	{
	    Map.Entry<String, DocketEventResponseEvent> entry = form.getDktSearchResultsMap().entrySet().iterator().next();
	    DocketEventResponseEvent onlyRespEvt = entry.getValue();
	    form.setAttorneyName(onlyRespEvt.getAttorneyName());
	}
	form.setConfirmMsg(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setAttorneyDataList(new ArrayList<AttorneyNameAndAddressResponseEvent>());
	return aMapping.findForward(UIConstants.ATTORNEY_SEARCH_SUCCESS);
    }
    /**
     * SearchAttorney2
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward searchAttorney2(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	String pagerOffset = (String) aRequest.getParameter("pager.offset");
	HttpSession session = aRequest.getSession();
	session.setAttribute("Pager_Offset", pagerOffset);
	form.setAction("searchAttorney2CourtAction");
	//Bug #70017 - pagination issue - attorney name not getting set when single result - fixed
	if (form.getDktSearchResultsMap().size() == 1 && form.getAttorneyName()!=null && form.getAttorneyName().equalsIgnoreCase(""))
	{
	    Map.Entry<String, DocketEventResponseEvent> entry = form.getDktSearchResultsMap().entrySet().iterator().next();
	    DocketEventResponseEvent onlyRespEvt = entry.getValue();
	    form.setAttorneyName(onlyRespEvt.getAttorneyName());
	}
	form.setConfirmMsg(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setAttorneyDataList(new ArrayList<AttorneyNameAndAddressResponseEvent>());
	return aMapping.findForward(UIConstants.ATTORNEY_SEARCH_SUCCESS);
    }
    public ActionForward AttorneyConfirmation(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	String pagerOffset = (String) aRequest.getParameter("pager.offset");
	String docketEventId = (String) aRequest.getParameter("docketEventId");
	DocketEventResponseEvent resp=null;
	Collection<DocketEventResponseEvent> dockets = form.getUpdatedDocketList() ;
	for( DocketEventResponseEvent currentEvent : dockets)
	{
		 if( currentEvent.getDocketEventId().equals(docketEventId))
		 {
		     resp=currentEvent;
		     break;
		 }
	}
	//DocketEventResponseEvent resp = dockets.get(indxforPage);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	HttpSession session = aRequest.getSession();
	session.setAttribute("Pager_Offset", pagerOffset);
	form.setAction("AttorneyConfirmation");
	
	SaveJJSLastAttorneyEvent updatelstattorneyEvent =(SaveJJSLastAttorneyEvent)
		EventFactory.getInstance( JuvenileCaseControllerServiceNames.SAVEJJSLASTATTORNEY );
	if(resp!=null)	
	{
        	updatelstattorneyEvent.setJuvenileNumber(resp.getJuvNum());		
        	if(resp.getBarNum().isEmpty()||resp.getBarNum()==null)
        	    updatelstattorneyEvent.setBarNumber(null); 
        	else
        	    updatelstattorneyEvent.setBarNumber(resp.getBarNum());
        	updatelstattorneyEvent.setAttorneyName(resp.getAttorneyName());	
        	if(resp.getAttorneyConnection()==null)
        	    updatelstattorneyEvent.setAttorneyConnection(null); 
        	else
        	    updatelstattorneyEvent.setAttorneyConnection(resp.getAttorneyConnection());
        	updatelstattorneyEvent.setJjclcourtId(resp.getDocketEventId());
        	updatelstattorneyEvent.setRecType(resp.getRecType());
        	// gal changes for task 158461
        	if (resp.getGalBarNum() != null && !resp.getGalBarNum().isEmpty())
		    {
        	    	updatelstattorneyEvent.setGalName(resp.getGalName());
			updatelstattorneyEvent.setGalbarNumber(resp.getGalBarNum());
		    }
        	//
        	dispatch.postEvent( updatelstattorneyEvent );
        	CompositeResponse composite = (CompositeResponse)dispatch.getReply();
        				
        	MessageUtil.processReturnException( composite );
        	return submitafterConfirm(aMapping, aForm, aRequest, aResponse,resp.getDocketEventId(),null);//,resp.getDocketEventId() 
        	//return submitafterConfirm(aMapping, aForm, aRequest, aResponse,resp.getJuvenileNumber(),resp.getCourtDate(),null);//,resp.getDocketEventId()
	}
	return refreshPage(aMapping, aForm, aRequest, aResponse);
	
	}
    public ActionForward AttorneyBypass(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	String pagerOffset = (String) aRequest.getParameter("pager.offset");
	String docketEventId = (String) aRequest.getParameter("docketEventId");
	
	//String coc = (String) aRequest.getParameter("coc");
	DocketEventResponseEvent resp = null;
	Collection<DocketEventResponseEvent> dockets = form.getUpdatedDocketList();
	for (DocketEventResponseEvent currentEvent : dockets)
	{
	    if (currentEvent.getDocketEventId().equals(docketEventId))
	    {
		resp = currentEvent;
		break;
	    }
	}
	/*IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);*/
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	HttpSession session = aRequest.getSession();
	session.setAttribute("Pager_Offset", pagerOffset);
	form.setAction("AttorneyBypass");

	if (resp != null)
	{
	 // gal changes for task 158461
	    /*if (resp.getGalBarNum() != null && !resp.getGalBarNum().isEmpty())
	    {*/
		UpdateJJSLastAttorneyGALEvent updatelstattorneyEvent =(UpdateJJSLastAttorneyGALEvent)
			EventFactory.getInstance( JuvenileCaseControllerServiceNames.UPDATEJJSLASTATTORNEYGAL );
		updatelstattorneyEvent.setJuvenileNumber(resp.getJuvNum());
		updatelstattorneyEvent.setGalName(resp.getGalName());
		updatelstattorneyEvent.setGalbarNumber(resp.getGalBarNum());	
		/*updatelstattorneyEvent.setRecType(resp.getRecType());
		updatelstattorneyEvent.setJjclcourtId(resp.getDocketEventId());*/
		
		dispatch.postEvent( updatelstattorneyEvent );
        	CompositeResponse composite = (CompositeResponse)dispatch.getReply();
        				
        	MessageUtil.processReturnException( composite );
	    //}
	    //
	    return submitafterConfirm(aMapping, aForm, aRequest, aResponse,resp.getDocketEventId(), "YES");
	    //return submitafterConfirm(aMapping, aForm, aRequest, aResponse,resp.getJuvenileNumber(),resp.getCourtDate(),"YES");//,resp.getDocketEventId()
	}
	//return aMapping.findForward("backDetentionHearing");
	return refreshPage(aMapping, aForm, aRequest, aResponse);
    }
    
    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward searchGuardianAdlitem(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	String pagerOffset = (String) aRequest.getParameter("pager.offset");
	HttpSession session = aRequest.getSession();
	session.setAttribute("Pager_Offset", pagerOffset);
	form.setAction("searchGALCourtAction");
	//Bug #70017 - pagination issue - attorney name not getting set when single result - fixed
	if (form.getDktSearchResultsMap().size() == 1 && form.getGalName()!=null && form.getGalName().equalsIgnoreCase(""))
	{
	    Map.Entry<String, DocketEventResponseEvent> entry = form.getDktSearchResultsMap().entrySet().iterator().next();
	    DocketEventResponseEvent onlyRespEvt = entry.getValue();
	    form.setGalName(onlyRespEvt.getAttorneyName());
	}
	form.setConfirmMsg(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setAttorneyDataList(new ArrayList<AttorneyNameAndAddressResponseEvent>());
	return aMapping.findForward(UIConstants.ATTORNEY_SEARCH_SUCCESS);
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
	form.setAction("courtActionUpdate");
	return aMapping.findForward(UIConstants.COURT_MAIN_MENU);
    }

    /**
     * createNewDetentionCalenderRecord
     * 
     * @param currentSettingResp
     * @param originalSettingResp
     * @param seqNum
     * @return
     */
    private boolean createNewDetentionCalenderRecord(DocketEventResponseEvent currentSettingResp, DocketEventResponseEvent originalSettingResp, String seqNum, String detentionId)
    {
	// new Detention will be created.
	UpdateJJSCLDetentionSettingEvent updateEvt = (UpdateJJSCLDetentionSettingEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.UPDATEJJSCLDETENTIONSETTING);
	updateEvt.setChainNumber(currentSettingResp.getChainNumber());
	updateEvt.setSeqNumber(seqNum);
	updateEvt.setHearingType("DT");

	if (currentSettingResp.getCourtResult()!=null && currentSettingResp.getTransferFlag().equalsIgnoreCase("1"))//add checkbox change
	{
	    if (currentSettingResp.getTransferFlag().equalsIgnoreCase("1"))//add checkbox change
	    {
		if (currentSettingResp.getTransferTo().contains("0"))
		{
		    String transferTo = currentSettingResp.getTransferTo().substring(2);
		    updateEvt.setCourtId(transferTo);
		}
	    }
	}
	else
	{
	    //bug fix #83082
	    if (currentSettingResp.getCourt() != null && currentSettingResp.getCourt().equalsIgnoreCase("300")) //bug 83436
	    {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
		{
		    updateEvt.setCourtId(currentSettingResp.getCourt());
		}
		else
		{
		    updateEvt.setCourtId("1");
		    //84105
		    List<JuvenileDates> juvenileDatesList = PDCriminalCodeTableHelper.loadActiveHolidays();
		    
		    if (juvenileDatesList != null)
		    {
			Iterator<JuvenileDates> juvenileDateItr = juvenileDatesList.iterator();
			while (juvenileDateItr.hasNext())
			{
			    JuvenileDates juvenileDate = juvenileDateItr.next();
			    if (juvenileDate != null)
			    {
				Date juvenileDte = DateUtil.stringToDate(juvenileDate.getCode(), "yyyyMMdd");
				String juvenileDateStr = DateUtil.dateToString(juvenileDte, DateUtil.DATE_FMT_1);
				if (currentSettingResp.getActionDate().equalsIgnoreCase(juvenileDateStr))
				{
				    updateEvt.setCourtId("300");
				    break;
				}
			    }
			}
		    }
		}
	    }
	    else
	    {
		
		updateEvt.setCourtId(currentSettingResp.getCourt());
	    }
	}

	//action date
	if (currentSettingResp.getDisableResetDate() != null && !currentSettingResp.getDisableResetDate().equalsIgnoreCase("true"))
	{
	    if (currentSettingResp.getActionDate() != null && !currentSettingResp.getActionDate().isEmpty() && !currentSettingResp.getActionDate().equals(originalSettingResp.getActionDate()))
	    {
		updateEvt.setCourtDate(DateUtil.stringToDate(currentSettingResp.getActionDate(), DateUtil.DATE_FMT_1));
	    }
	    else
	    {
		updateEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
	    }
	    //court Time
	    if (currentSettingResp.getActionTime() != null && !currentSettingResp.getActionTime().isEmpty() && !currentSettingResp.getActionTime().equals(originalSettingResp.getActionTime()))
	    {
		updateEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(currentSettingResp.getActionTime()));
	    }
	    else
	    {
		updateEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));
	    }
	}
	else
	{
	    updateEvt.setCourtDate(DateUtil.stringToDate(originalSettingResp.getCourtDate(), DateUtil.DATE_FMT_1));
	    updateEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(originalSettingResp.getCourtTime()));
	}
	updateEvt.setHearingDisposition(null);
	updateEvt.setHearingResult(null);

	//populate other values from the currentSetting.
	updateEvt.setAlternateSettingInd(currentSettingResp.getAlternateSettingInd());
	updateEvt.setAttorneyConnection(currentSettingResp.getAttorneyConnection());
	updateEvt.setAttorneyName(currentSettingResp.getAttorneyName());
	//add attorney details from existing if not confirmed
	//if attorney is not confirmed pull the existing attorney deatils from detention
	if (currentSettingResp.getAtyConfirmation() == null || currentSettingResp.getAtyConfirmation().isEmpty())
	    {
	    	GetJJSDetentionbyOIDEvent courtEvent = (GetJJSDetentionbyOIDEvent) EventFactory
                        .getInstance(JuvenileCaseControllerServiceNames.GETJJSDETENTIONBYOID);
	    	courtEvent.setId(currentSettingResp.getDocketEventId());
                CompositeResponse officerComposite = MessageUtil.postRequest(courtEvent);

                DocketEventResponseEvent resp= (DocketEventResponseEvent) MessageUtil.filterComposite(
                        officerComposite, DocketEventResponseEvent.class);
		
                if(resp!=null)
                {
                    updateEvt.setAttorneyName(resp.getAttorneyName());
                    updateEvt.setAttorneyConnection(resp.getAttorneyConnection());
                    updateEvt.setBarNumber(resp.getBarNum());
                }		
	    }
	//
	//populate attorney2  values from the currentSetting.
		updateEvt.setAttorney2BarNum(currentSettingResp.getAttorney2BarNum());
		updateEvt.setAttorney2Connection(currentSettingResp.getAttorney2Connection());
		updateEvt.setAttorney2Name(currentSettingResp.getAttorney2Name());
		//
		//populate gal  values from the currentSetting.
		// gal changes for task 158461
		updateEvt.setGalName(currentSettingResp.getGalName());
		updateEvt.setGalBarNum(currentSettingResp.getGalBarNum());
		//
	if (StringUtil.isEmpty(currentSettingResp.getComments()))
	    updateEvt.setComments(null); 
	else
	    updateEvt.setComments(currentSettingResp.getComments());
	if (currentSettingResp.getBarNum() != null && currentSettingResp.getBarNum().equals(""))
	{
	    updateEvt.setBarNumber(null);
	}
	else
	{
	    updateEvt.setBarNumber(currentSettingResp.getBarNum());
	}

	updateEvt.setIssueFlag(currentSettingResp.getIssueFlag());
	updateEvt.setJuvenileNumber(currentSettingResp.getJuvenileNumber().substring(1, currentSettingResp.getJuvenileNumber().length()));
	updateEvt.setPetitionNumber(currentSettingResp.getPetitionNumber());
	updateEvt.setReferralNumber(currentSettingResp.getReferralNum());
	//task 168662
	updateEvt.setInterpreter(currentSettingResp.getInterpreter());
	updateEvt.setNewRecordCreated(true);
	updateEvt.setDetentionId(detentionId);

	CompositeResponse compResp = MessageUtil.postRequest(updateEvt);
	DocketEventResponseEvent docket = (DocketEventResponseEvent) MessageUtil.filterComposite(compResp, DocketEventResponseEvent.class);
	Object errResp = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
	if (errResp != null)
	{
	    ErrorResponseEvent myEvt = (ErrorResponseEvent) errResp;
	    try
	    {
		handleFatalUnexpectedException(myEvt.getMessage());
	    }
	    catch (GeneralFeedbackMessageException e)
	    {
		e.printStackTrace();
	    }
	}
	if (docket.getIsNewRecordCreated().equalsIgnoreCase("true"))
	{
	    return true;
	}

	return false;
    }
  //to email on reset
    private boolean sendJPOemail(DocketEventResponseEvent currentSettingResp, DocketEventResponseEvent originalSettingResp)
    {
	String fromEmail = "jims2notification@itc.hctx.net";
	String juvNum =currentSettingResp.getJuvenileNumber().substring(1, currentSettingResp.getJuvenileNumber().length());
	String juvName =currentSettingResp.getJuvenileName();
	
	List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvNum);
	Collection<JuvenileProfileReferralListResponseEvent> referralList = JuvenileFacilityHelper.getJuvReferralDetails(juvNum);	
	Collections.sort((List<JuvenileProfileReferralListResponseEvent>) referralList, Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
		@Override
		public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
		{
		    if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
			//return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());	
			return evt1.getReferralNumber().compareTo(evt2.getReferralNumber());
		    else
			return -1;
		}
	    }));
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
	  
	    
	}
		
	    //for each referral get the casefiles, then find the supervision Category, Supervision Type of the most recent casefile assign /highest seq Num - confirmed with Carla 7/17/2018 [email]
	    JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNum(juvNum);
	    Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();

	    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
		@Override
		public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
		{
		    if (evt1.getOfficerID() != null && evt2.getOfficerID() != null)
			//return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());	
			return evt1.getOfficerID().compareTo(evt2.getOfficerID());
		    else
			return -1;
		}
	    }));
	    Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
	    String prevOfficer="";
	    while (caseFileRefItr.hasNext())
	    {
		//add if to check active
		JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
		if(!caseFileReferral.getCaseStatusCd().equalsIgnoreCase("C")&&!caseFileReferral.getCaseStatusCd().equalsIgnoreCase("CS"))//not sure if to check this 
        	{
        		OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
        		if (officerProfileResponse != null)
        		{        		   
        		    String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
        		    
        		    if (officerProfileResponse.getEmail() != null && !"".equals(officerProfileResponse.getEmail()))
        		    {
        			if(!prevOfficer.equalsIgnoreCase(caseFileReferral.getOfficerID()))
        			{
                			//send email notification
                			StringBuffer message = new StringBuffer(100);
                			SendEmailEvent sendEmailEvent = new SendEmailEvent();
                			sendEmailEvent.setSubject("Court Reset for : " + juvName + " " + juvNum);
                			sendEmailEvent.setMessage(message.toString());
                			sendEmailEvent.setFromAddress(fromEmail);
                			sendEmailEvent.addToAddress(officerProfileResponse.getEmail());
                			setCommonMessage(message,sendEmailEvent,referralList,currentSettingResp);
                			sendEmailEvent.setContentType("text/html");
                			IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
                			dispatch1.postEvent(sendEmailEvent);
                			//CLM email                			
                			    String mngrUserId = officerProfileResponse.getManagerId();
                			    if (mngrUserId != null && !"".equals(mngrUserId))
                			    {
                				OfficerProfileResponseEvent respEvent = PDOfficerProfileHelper.getOfficerProfileByLogonId(mngrUserId);
                				if(respEvent!=null)
                				{
                				//send CLM email notification					
                        				StringBuffer mess = new StringBuffer(100);
                        				SendEmailEvent sendCLMEmailEvent = new SendEmailEvent();
                        				sendCLMEmailEvent.setSubject("Court Reset for : " + juvName + " " + juvNum);
                        				sendCLMEmailEvent.setMessage(mess.toString());
                        				sendCLMEmailEvent.setFromAddress(fromEmail);
                        				sendCLMEmailEvent.addToAddress(respEvent.getEmail());
                        				setCommonMessage(mess, sendCLMEmailEvent, referralList, currentSettingResp);
                        				sendCLMEmailEvent.setContentType("text/html");
                        				IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
                        				dispatch2.postEvent(sendCLMEmailEvent);
                				}
    
                			    }
                			    //
                			prevOfficer=caseFileReferral.getOfficerID();
        			}
        		    }
        		    
        		}        		
		}
	    }
	    
	return true;
    }
  //to email on reset
    private void sendemailonDelete(DocketEventResponseEvent deleteSettingResp)//, DocketEventResponseEvent originalSettingResp
    {
	//String fromEmail = "jims2notification@itc.hctx.net";
	String juvNum =deleteSettingResp.getJuvenileNumber();
	String juvName =deleteSettingResp.getJuvenileName();
	StringBuffer message = new StringBuffer(100);
	String fromEmail = "jims2notification@itc.hctx.net";
	SendEmailEvent sendEmailEvent = new SendEmailEvent();
	sendEmailEvent.setContentType("text/html; charset=utf-8");
	sendEmailEvent.setSubject("Deletion of JJSCLCOURT record for juvenile number "+ juvNum);
	sendEmailEvent.setFromAddress(fromEmail);
	sendEmailEvent.addToAddress("Data.Corrections@hcjpd.hctx.net");
	//sendEmailEvent.addToAddress("nekha.mathew@harriscountytx.gov");
	//sendEmailEvent.addToAddress("manisha.halapeti@harriscountytx.gov");
	setDeleteMessage(message,sendEmailEvent,deleteSettingResp);
	sendEmailEvent.setContentType("text/html");
	//sendEmailEvent.setMessage(message.toString());
	IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch1.postEvent(sendEmailEvent);		    
	
    }
    
    //to email on disposition change
    private boolean senddispositionEmail(DocketEventResponseEvent currentSettingResp, DocketEventResponseEvent originalSettingResp)
    {
	String fromEmail = "jims2notification@itc.hctx.net";
	String juvNum =currentSettingResp.getJuvenileNumber().substring(1, currentSettingResp.getJuvenileNumber().length());
	String juvName =currentSettingResp.getJuvenileName();
	String refNum =currentSettingResp.getReferralNum();
	
		
	    //for each referral get the casefiles, then find the supervision Category, Supervision Type of the most recent casefile assign /highest seq Num - confirmed with Carla 7/17/2018 [email]
	   JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(juvNum,refNum); 
	//JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNum(juvNum);
	    Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();

	    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
		@Override
		public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
		{
		    if (evt1.getOfficerID() != null && evt2.getOfficerID() != null)
			//return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());	
			return evt1.getOfficerID().compareTo(evt2.getOfficerID());
		    else
			return -1;
		}
	    }));
	    //referral table call for juvnum refnum- 

	Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
	String prevOfficer = "";
	while (caseFileRefItr.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent ref = null;
	    JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
	    if (caseFileReferral.getSupervisionTypeCd().equalsIgnoreCase("CSS"))//check refferaltypeind=TR and disposition code not null
	    {
		ref = JuvenileFacilityHelper.getJuvReferralDetails(juvNum, refNum);
		if (ref.getReferralTypeInd() != null && ref.getReferralTypeInd().equalsIgnoreCase("TR") && ref.getFinalDisposition() != null && !ref.getFinalDisposition().isEmpty() && ref.getFinalDisposition().equalsIgnoreCase(currentSettingResp.getDisposition()))
		{
		    OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
		    if (officerProfileResponse != null)
		    {
			String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();

			if (officerProfileResponse.getEmail() != null && !"".equals(officerProfileResponse.getEmail()))
			{
			    if (!prevOfficer.equalsIgnoreCase(caseFileReferral.getOfficerID()))
			    {
				//send JPO email notification
				StringBuffer message = new StringBuffer(100);
				SendEmailEvent sendEmailEvent = new SendEmailEvent();
				sendEmailEvent.setSubject("Juv # "+juvNum+ " Has Received In-County Disposition.");
				message.append("Juv Name: "+ juvName + ", Juv#: "+ juvNum +", has received an in-county disposition in our courts, and requires a new in-county casefile. Please close the existing interim casefile and activate the new casefile."); 
				sendEmailEvent.setMessage(message.toString());
				sendEmailEvent.setFromAddress(fromEmail);
				sendEmailEvent.addToAddress(officerProfileResponse.getEmail());
				//setCommonMessage(message,sendEmailEvent,referralList,currentSettingResp);
				sendEmailEvent.setContentType("text/html");
				IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch1.postEvent(sendEmailEvent);
				//CLM email                			
				String mngrUserId = officerProfileResponse.getManagerId();
				if (mngrUserId != null && !"".equals(mngrUserId))
				{
				    OfficerProfileResponseEvent respEvent = PDOfficerProfileHelper.getOfficerProfileByLogonId(mngrUserId);
				    //send CLM email notification					
				    StringBuffer message2 = new StringBuffer(100);
				    SendEmailEvent sendCLMEmailEvent = new SendEmailEvent();
				    sendCLMEmailEvent.setSubject("Juv # " + juvNum + " Has Received In-County Disposition.");
				    message2.append("Juv Name: " + juvName + ", Juv#: " + juvNum + ", has received an in-county disposition in our courts, and requires a new in-county casefile. Please close the existing interim casefile and activate the new casefile.");
				    sendCLMEmailEvent.setMessage(message2.toString());
				    sendCLMEmailEvent.setFromAddress(fromEmail);
				    sendCLMEmailEvent.addToAddress(respEvent.getEmail());
				    //setCommonMessage(message,sendEmailEvent,referralList,currentSettingResp);
				    sendCLMEmailEvent.setContentType("text/html");
				    IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
				    dispatch2.postEvent(sendCLMEmailEvent);
				}
				//
				prevOfficer = caseFileReferral.getOfficerID();
			    }
			}
		    }
		}
	    }
	}
	    
	return true;
    }
    private void setCommonMessage(StringBuffer message,SendEmailEvent sendEmailEvent,Collection<JuvenileProfileReferralListResponseEvent> referralCollection,DocketEventResponseEvent currentSettingResp ){
	   //referral History Details.
	String juvNum =currentSettingResp.getJuvenileNumber().substring(1, currentSettingResp.getJuvenileNumber().length());
	String juvName =currentSettingResp.getJuvenileName();
	String actnDate =currentSettingResp.getActionDate();
	String reffNum =currentSettingResp.getReferralNum();
	    if(referralCollection!=null && !referralCollection.isEmpty())
	    {
            	    message.append(juvName + " , " + juvNum + ", referral number " + reffNum + " has a new court date " + actnDate);        			
            	    message.append("<BR>");
            	    message.append("<BR>");
            	    message.append("<html><body>");
            	    message.append("<table border=\"1\" colspan=\"10\">");
            	    message.append("<tr><th colspan=\"10\">Referral Summary</th></tr>");
            	    message.append("<tr>");
            	    message.append("<td>Ref No </td>");
            	    message.append("<td>Referral Date</td>");
            	    message.append("<td>Offense/Petition Allegation</td>");
            	    message.append("<td>Intake Decision</td>");
            	    message.append("<td>Court Id</td>");
            	    message.append("<td>Previous Court Date</td>");
            	    message.append("<td>Decision</td>");
            	    message.append("<td>Petition</td>");
            	    message.append("<td>Date Closed</td>");
            	    message.append("</tr>");
	    
			   Iterator<JuvenileProfileReferralListResponseEvent> juvProfCaseFileListIter = referralCollection.iterator();
			   while(juvProfCaseFileListIter.hasNext())
			   {
			       JuvenileProfileReferralListResponseEvent juvProfCaseFileListResp =juvProfCaseFileListIter.next();
				    message.append("<tr>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getReferralNumber()!=null)
				    	message.append(juvProfCaseFileListResp.getReferralNumber());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getReferralDate()!=null)
				    	message.append(DateUtil.dateToString(juvProfCaseFileListResp.getReferralDate(), DateUtil.DATE_FMT_1));
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getOffense()!=null)
				    	message.append(juvProfCaseFileListResp.getOffense());
				    else
				    	message.append("");
				    message.append("/");
				    if(juvProfCaseFileListResp.getPetitionAllegation()!=null)
				    	message.append(juvProfCaseFileListResp.getPetitionAllegation());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getIntakeDecisionId()!=null)
				    	message.append(juvProfCaseFileListResp.getIntakeDecisionId());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getCourtId()!=null)
				    	message.append(juvProfCaseFileListResp.getCourtId());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getCourtDate()!=null)
				    	message.append(DateUtil.dateToString(juvProfCaseFileListResp.getCourtDate(), DateUtil.DATE_FMT_1));
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getFinalDisposition()!=null)				
				    	message.append(juvProfCaseFileListResp.getFinalDisposition());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");				    
				    if(juvProfCaseFileListResp.getPetitionNumber()!=null)
				    	message.append(juvProfCaseFileListResp.getPetitionNumber());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getCloseDate()!=null)
				    	message.append(DateUtil.dateToString(juvProfCaseFileListResp.getCloseDate(), DateUtil.DATE_FMT_1));
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("</tr>");
			   }
	    }
	    else
		message.append("Please refer to Data Corrections for details.");
	    message.append("</table></body></html>");
	    sendEmailEvent.setMessage(message.toString());
}

    private void setDeleteMessage(StringBuffer message,SendEmailEvent sendEmailEvent,DocketEventResponseEvent currentSettingResp )
    {
	   //referral History Details.	
	String juvNum = currentSettingResp.getJuvenileNumber();
	String reffNum = currentSettingResp.getReferralNum();
	String chainNum = currentSettingResp.getChainNumber();
	String seqNum = currentSettingResp.getSeqNum();
	String crtDate = currentSettingResp.getCourtDate();
	String crtID = currentSettingResp.getCourt();
	String deleteUser = SecurityUIHelper.getLogonId();
	String jjsclClourt_id = currentSettingResp.getDocketEventId();
        message.append("The following record was deleted:");        			
         	    
         	    message.append("<BR>");
         	    message.append("<BR>");
         	    message.append("<html><body>");
         	    message.append("<table border=\"1\" colspan=\"10\">");
         	    //message.append("<tr><th colspan=\"10\">Referral Summary</th></tr>");
         	    message.append("<tr>");
         	    message.append("<td>JUVENILENUM</td>");
         	    message.append("<td>REFERRALNUM</td>");
         	    message.append("<td>CHAINNUM</td>");
         	    message.append("<td>SEQNUM</td>");
         	    message.append("<td>COURTDATE</td>");
         	    message.append("<td>COURTID</td>");
         	    message.append("<td>DELETEUSER</td>");
         	    message.append("<td>JJSCLCOURT_ID</td>");
         	    message.append("</tr>");
         	    
         	    message.append("<tr>");
         	    message.append("<td>");
                 	    if(juvNum!=null)
                 		message.append(juvNum);
                 	    else
                 		message.append("");
         	    message.append("</td>");
         	    message.append("<td>");
                 	    if(reffNum!=null)
                 		message.append(reffNum);
                 	    else
                 		message.append("");
         	    message.append("</td>");
         	    message.append("<td>");
        	    if(chainNum!=null)
        		message.append(chainNum);
        	    else
        		message.append("");
        	    message.append("</td>");
        	    message.append("<td>");
        	    if(seqNum!=null)
        		message.append(seqNum);
        	    else
        		message.append("");
        	    message.append("</td>");
        	    message.append("<td>");
        	    if(crtDate!=null)
        		//message.append(DateUtil.stringToDate( crtDate, "MM/dd/yyyy" ) );
        	    	message.append(crtDate);
        	    else
        		message.append("");
        	    message.append("</td>");
        	    message.append("<td>");
        	    if(crtID!=null)
        		message.append(crtID);
        	    else
        		message.append("");
        	    message.append("</td>");
        	    message.append("<td>");
        	    if(deleteUser!=null)
        		message.append(deleteUser);
        	    else
        		message.append("");
        	    message.append("</td>");
        	    
        	    message.append("<td>");
        	    if(jjsclClourt_id != null)
        		message.append(jjsclClourt_id);
        	    else
        		message.append("");
        	    message.append("</td>");
        		
		message.append("</table></body></html>");
		message.append("<BR>");
		message.append("Please verify the court record information and the referral information related to this deleted record.");
	    
	    sendEmailEvent.setMessage(message.toString());
}
	//
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

}
