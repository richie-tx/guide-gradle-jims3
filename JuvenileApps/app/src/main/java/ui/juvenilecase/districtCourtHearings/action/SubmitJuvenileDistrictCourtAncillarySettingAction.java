package ui.juvenilecase.districtCourtHearings.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import messaging.codetable.GetAttorneyNameAndBarNumEvent;
import messaging.districtCourtHearings.GetJJSCLAncillarySettingEvent;
import messaging.districtCourtHearings.SaveAncillarySettingEvent;
import messaging.districtCourtHearings.UpdateJJSCLAncillarySettingEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.GetDetailsbyPetitionNumEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.reply.AttorneyNameAndAddressResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.PDCriminalCodeTableHelper;
import pd.juvenilecase.JJSChainNumControl;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.districtCourtHearings.JJSCLAncillary;
import pd.security.PDSecurityHelper;
import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;


/**
 * @author nemathew
 *
 */
public class SubmitJuvenileDistrictCourtAncillarySettingAction extends JIMSBaseAction
{
    
    /**
     * submit
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
    {
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
    CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	GetJJSCLAncillarySettingEvent ancillarySettingEvt = (GetJJSCLAncillarySettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYSETTING);
	String courtDate = courtHearingForm.getCourtDate();
	String petWithoutspace = courtHearingForm.getPetitionNumber().replaceAll("-","");
    ancillarySettingEvt.setCourtDate(DateUtil.stringToDate( courtDate, DateUtil.DATE_FMT_1));
    ancillarySettingEvt.setCourtId(courtHearingForm.getCourtId());
    ancillarySettingEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(courtHearingForm.getCourtTime()));
    //ancillarySettingEvt.setPetitionNumber(courtHearingForm.getPetitionNumber());
    ancillarySettingEvt.setPetitionNumber(petWithoutspace);
    ancillarySettingEvt.setIssueFlag(courtHearingForm.getIssueFlag());
    CompositeResponse compResp = MessageUtil.postRequest(ancillarySettingEvt);
    List<DocketEventResponseEvent> ancillaryDktsResp = MessageUtil.compositeToList(compResp, DocketEventResponseEvent.class);
	if (ancillaryDktsResp != null && !ancillaryDktsResp.isEmpty()){
		ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "HEARING ALREADY SCHEDULED FOR THIS DAY"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	else{
		//save the ancillary setting
	    	//add code to check if petition given is suffixed by J and has an active deliquency case 122618
	    //IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);	
	    if(courtHearingForm.getHearingType().equalsIgnoreCase("SR")&&petWithoutspace.endsWith("J"))
	    	{
	    	    GetDetailsbyPetitionNumEvent petitionEvt = (GetDetailsbyPetitionNumEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETDETAILSBYPETITIONNUM);
	    	    petitionEvt.setPetitionNum(petWithoutspace);
	    	    dispatch.postEvent(petitionEvt);
	    	    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	    	    List<PetitionResponseEvent> petresEvts = MessageUtil.compositeToList(compositeResponse, PetitionResponseEvent.class);
	    	    if(petresEvts.size()>0)
	    	    {
	    		ActionErrors errors = new ActionErrors();
	    		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Youth has a district court setting associated to this petition number. Contact HCJPD for RESEAL referral creation to continue district court docketing"));
	    		saveErrors(aRequest, errors);
	    		return aMapping.findForward(UIConstants.FAILURE);
	    	    
	    	    }
        		
	    	}		
		SaveAncillarySettingEvent event = (SaveAncillarySettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.SAVEANCILLARYSETTING);
		event.setCourtId(courtHearingForm.getCourtId());
		event.setCourtDate(DateUtil.stringToDate(courtHearingForm.getCourtDate(), DateUtil.DATE_FMT_1));
		event.setCourtTime(JuvenileFacilityHelper.getDateWithColon(courtHearingForm.getCourtTime()));
		event.setPetitionNum(petWithoutspace);
		event.setTypeCase(courtHearingForm.getTypeCaseStr());
		event.setRespondentName(courtHearingForm.getRespondentName());
		event.setIssueFlag(courtHearingForm.getIssueFlag());
		event.setSettingReason(courtHearingForm.getHearingType());
		event.setBarNum(courtHearingForm.getBarNumber());
		event.setAttorneyName(courtHearingForm.getAttorneyName());
		event.setAttorneyConnection(courtHearingForm.getAttorneyConnection());
		event.setFilingDate(DateUtil.stringToDate(courtHearingForm.getFilingDate(),DateUtil.DATE_FMT_1));
		event.setLcDate(DateUtil.getCurrentDate());
		event.setLcTime(Calendar.getInstance().getTime());
		event.setLcUser(PDSecurityHelper.getLogonId());
		event.setSeqNumber("10");
		event.setRecType("ANCILLARY");
		CompositeResponse compositeResponse = MessageUtil.postRequest(event);
		Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
		if (errorResponse != null)
		{
		  ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
		   try {
			handleFatalUnexpectedException(myEvt.getMessage());
		   } 
		   catch (GeneralFeedbackMessageException e) {
				e.printStackTrace();
		   }
		} 
		IHome home = new Home();
		JJSChainNumControl chainNumControl = null;
		Iterator<JJSChainNumControl> chainNumIter = JJSChainNumControl.findAll();
		//update chain num
		if(chainNumIter.hasNext())
		{
			chainNumControl = (JJSChainNumControl) chainNumIter.next();
			String nextChainNum = chainNumControl.getNextChainNum();
			if(nextChainNum!=null && !nextChainNum.equals(" "))
			{
			    int num = Integer.parseInt(nextChainNum);
			    chainNumControl.setNextChainNum(""+(num+1));
			    chainNumControl.setLcDate(DateUtil.getCurrentDate());
			    chainNumControl.setLcTime(Calendar.getInstance().getTime());
			    chainNumControl.setLcUser(PDSecurityHelper.getLogonId());
			    home.bind(chainNumControl);
			}
		}
			ActionMessages messageHolder = new ActionMessages();
		    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.recordsAddedAncillaryCourt"));
		    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
		    saveMessages(aRequest, messageHolder);
		    courtHearingForm.reset();
		    courtHearingForm.setCourtDate(courtDate);
		    courtHearingForm.setCourtId(event.getCourtId());
		    courtHearingForm.setAction("ancillarySetting");
		    courtHearingForm.setFilingDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));
			return aMapping.findForward(UIConstants.ANCILLARY_SETTING_ADD_SUCCESS);
		}
	}
    
    /**
     * update
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
    {
    	
    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
    	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
    	String courtDate = courtHearingForm.getCourtDate();
    	String courtId = courtHearingForm.getCourtId();
    	String isAncillaryDocketEdited ="false";
    	String isAncillaryDocketEditedAtAll ="false";
    	String isAttNameBarNumRespNameATYChanged ="false";
    	String isCrtTimePetNumChanged ="false";  
    	DocketEventResponseEvent selectedDocket =  courtHearingForm.getSelectedDocket();
    	if(selectedDocket!=null){
    		//check the following conditions
    		if(!selectedDocket.getPetitionNumber().trim().equalsIgnoreCase(courtHearingForm.getPetitionNumber())){
	    		isAncillaryDocketEdited = "true";
	    		isCrtTimePetNumChanged ="true";
    		//}else if(!selectedDocket.getCourtTime().equalsIgnoreCase(courtHearingForm.getCourtTime())){ //commented on 5/17/2018
    		}else if(!selectedDocket.getFormattedCourtTime().equalsIgnoreCase(courtHearingForm.getCourtTime())){    			
	    		isAncillaryDocketEdited = "true";
	    		isCrtTimePetNumChanged ="true";
	    	}else if(!selectedDocket.getIssueFlag().equalsIgnoreCase(courtHearingForm.getIssueFlag())){
	    		isAncillaryDocketEdited = "true";
	    	}
    		if(!selectedDocket.getSettingReason().equalsIgnoreCase(courtHearingForm.getHearingType())){
	    		isAncillaryDocketEditedAtAll = "true";
	    	}
    		if(!selectedDocket.getTypeCase().equalsIgnoreCase(courtHearingForm.getTypeCaseStr())){
	    		isAncillaryDocketEditedAtAll = "true";
	    	}
    		if(!selectedDocket.getRespondantName().trim().equalsIgnoreCase(courtHearingForm.getRespondentName())){
	    		isAncillaryDocketEditedAtAll = "true";
	    		isAttNameBarNumRespNameATYChanged = "true";
	    	}else if(selectedDocket.getBarNum() != null){ //null check for BarNum (some Migrated records does NOT have a BarNum)
	    		if (selectedDocket.getBarNum().equals("0")){
	    			selectedDocket.setBarNum("");
	    		}
	    		if(!selectedDocket.getBarNum().trim().equalsIgnoreCase(courtHearingForm.getBarNumber())){
	    		isAncillaryDocketEditedAtAll = "true";
	    		isAttNameBarNumRespNameATYChanged = "true";
	    		}
	    	}
    		if (selectedDocket.getAttorneyName() != null){ //null check for Attorney Name (some Migrated records does NOT have a Attorney Name)
	    		if(!selectedDocket.getAttorneyName().trim().equalsIgnoreCase(courtHearingForm.getAttorneyName())){
	    		isAncillaryDocketEditedAtAll = "true";
	    		isAttNameBarNumRespNameATYChanged = "true";
	    		}
	    	}
    		if(selectedDocket.getAttorneyConnection() != null){  //null check for Attorney Connection (some Migrated records does NOT have a BarNum)
	    		if(!selectedDocket.getAttorneyConnection().trim().equalsIgnoreCase(courtHearingForm.getAttorneyConnection())){
	    		isAncillaryDocketEditedAtAll = "true";
	    		isAttNameBarNumRespNameATYChanged = "true";
	    		}
	    	}
	    }
	    if(isAncillaryDocketEdited.equalsIgnoreCase("true")){
		    GetJJSCLAncillarySettingEvent ancillarySettingEvt = (GetJJSCLAncillarySettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYSETTING);
		    ancillarySettingEvt.setCourtDate(DateUtil.stringToDate( courtDate, DateUtil.DATE_FMT_1));
		    ancillarySettingEvt.setCourtId(courtHearingForm.getCourtId());
		    ancillarySettingEvt.setCourtTime(JuvenileFacilityHelper.getDateWithColon(courtHearingForm.getCourtTime()));
		    ancillarySettingEvt.setPetitionNumber(courtHearingForm.getPetitionNumber());		    
		    ancillarySettingEvt.setIssueFlag(courtHearingForm.getIssueFlag());
		    CompositeResponse compResp = MessageUtil.postRequest(ancillarySettingEvt);
		    List<DocketEventResponseEvent> ancillaryDktsResp = MessageUtil.compositeToList(compResp, DocketEventResponseEvent.class);
		    
			if (ancillaryDktsResp != null && ancillaryDktsResp.size()>1){
				ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "HEARING ALREADY SCHEDULED FOR THIS DAY"));
			    saveErrors(aRequest, errors);
			    /*courtHearingForm.reset(); bug fix for 144626
				courtHearingForm.setCourtDate(courtDate);
				courtHearingForm.setCourtId(courtId);
				courtHearingForm.setAction("ancillarySetting");
				courtHearingForm.setFilingDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));*/
			    return aMapping.findForward(UIConstants.FAILURE);
			} 
	    }
	    if(isAncillaryDocketEdited.equalsIgnoreCase("false") && isAncillaryDocketEditedAtAll.equalsIgnoreCase("false")){
	    	ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "HEARING ALREADY SCHEDULED FOR THIS DAY"));
		    saveErrors(aRequest, errors);
		    /*courtHearingForm.setAction("ancillarySetting"); bug fix for 144626
		    courtHearingForm.reset();
		    courtHearingForm.setCourtDate(courtDate);
		    courtHearingForm.setCourtId(courtId);
		    courtHearingForm.setFilingDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));*/
		    return aMapping.findForward(UIConstants.FAILURE);
	    }
	    if(isAttNameBarNumRespNameATYChanged.equalsIgnoreCase("false")){
	    	if(isAncillaryDocketEdited.equalsIgnoreCase("false") && isAncillaryDocketEditedAtAll.equalsIgnoreCase("true")){
	    		ActionErrors errors = new ActionErrors();
	    		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "HEARING ALREADY SCHEDULED FOR THIS DAY"));
	    		saveErrors(aRequest, errors);
	    		return aMapping.findForward(UIConstants.FAILURE);
	    	}
	    }
	    
		if (selectedDocket != null) {
			// null check if needed.
			String seqNum = selectedDocket.getSeqNum();
			Iterator<JJSCLAncillary> ancillaryItr = JJSCLAncillary.findAll("chainNumber", selectedDocket.getChainNumber());
			List<DocketEventResponseEvent> dockets = new ArrayList<DocketEventResponseEvent>();
			while (ancillaryItr.hasNext()) {
				JJSCLAncillary ancillaryCrt = (JJSCLAncillary) ancillaryItr.next();
				if (ancillaryCrt != null) {
					DocketEventResponseEvent resp = ancillaryCrt.valueObject();
					if (resp != null) {
						dockets.add(resp);
					}
				}
			}
			// sort by highest sequence number
			Collections.sort((List<DocketEventResponseEvent>) dockets, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
			@Override
			public int compare(DocketEventResponseEvent evt1,DocketEventResponseEvent evt2)
			{
			    if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
					return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
				    else
					return -1;
				}
			}));
			// check if the seqNum of the current DocketRespEvent, is the
			// highest for that chainNum
			Iterator<DocketEventResponseEvent> docketRespItr = dockets.iterator();
			while (docketRespItr.hasNext()) {
				DocketEventResponseEvent respEvt = (DocketEventResponseEvent) docketRespItr.next();
				if (respEvt != null) {
				    if(seqNum != null){
					if (!seqNum.equalsIgnoreCase(respEvt.getSeqNum())) {
						ActionErrors errors = new ActionErrors();
						errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "ONLY THE LAST APPEARANCE MAY BE CHANGED"));
						saveErrors(aRequest, errors);
						return aMapping.findForward(UIConstants.FAILURE);
					}
					break;
				    }//null check added 12/14/2018, to handle any bad data
				}
			}
		} //if(3)
		
		UpdateJJSCLAncillarySettingEvent updateEvent = (UpdateJJSCLAncillarySettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSCLANCILLARYSETTING);
		updateEvent.setCourtId(courtHearingForm.getCourtId());
		updateEvent.setCourtDate(DateUtil.stringToDate(courtHearingForm.getCourtDate(), DateUtil.DATE_FMT_1));
		updateEvent.setCourtTime(JuvenileFacilityHelper.getDateWithColon(courtHearingForm.getCourtTime()));
		updateEvent.setPetitionNum(courtHearingForm.getPetitionNumber().replaceAll("-",""));
		//TYPE CASE  and SETTING REASON can be changed <on the last setting in a chain> 
		//only in association with a new court, date and/or time; or a modified petition number.
		if (isCrtTimePetNumChanged.equalsIgnoreCase("true")){
		updateEvent.setTypeCase(courtHearingForm.getTypeCaseStr());
		updateEvent.setSettingReason(courtHearingForm.getHearingType());
		} else {
			updateEvent.setTypeCase(selectedDocket.getTypeCase());
			updateEvent.setSettingReason(selectedDocket.getSettingReason());
		}
		updateEvent.setRespondentName(courtHearingForm.getRespondentName());
		if (courtHearingForm.getIssueFlag().equalsIgnoreCase("J")){
			updateEvent.setIssueFlag("I");
		}else {
		updateEvent.setIssueFlag(courtHearingForm.getIssueFlag());
		}
		updateEvent.setBarNum(courtHearingForm.getBarNumber());
		updateEvent.setAttorneyName(courtHearingForm.getAttorneyName());
		updateEvent.setAttorneyConnection(courtHearingForm.getAttorneyConnection());
		updateEvent.setFilingDate(DateUtil.stringToDate(courtHearingForm.getFilingDate(), DateUtil.DATE_FMT_1));
		updateEvent.setLcDate(DateUtil.getCurrentDate());
		updateEvent.setLcTime(Calendar.getInstance().getTime());
		updateEvent.setLcUser(PDSecurityHelper.getLogonId());
		updateEvent.setSeqNumber(courtHearingForm.getSeqNumber());
		updateEvent.setRecType("ANCILLARY");
		updateEvent.setDocketEventId(courtHearingForm.getDcktEvtId());
		CompositeResponse compositeResponse = MessageUtil.postRequest(updateEvent);
		Object errorResponse = MessageUtil.filterComposite(compositeResponse,ErrorResponseEvent.class);
		if (errorResponse != null) {
			ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
			try {
				handleFatalUnexpectedException(myEvt.getMessage());
			} catch (GeneralFeedbackMessageException e) {
				e.printStackTrace();
			}
		}
		ActionMessages messageHolder = new ActionMessages();
		if(isAncillaryDocketEditedAtAll.equalsIgnoreCase("false") && isAttNameBarNumRespNameATYChanged.equalsIgnoreCase("true")){
		messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.recordNameBarNumChangedAncillaryCourt"));	//Name(S)/Bar Number Changed
		}
		else if(isAncillaryDocketEditedAtAll.equalsIgnoreCase("true") && isAttNameBarNumRespNameATYChanged.equalsIgnoreCase("true")){
		messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.recordUpdatedAncillaryCourt"));//Hearing already scheduled. Name(S)/Bar Number Changed
		} else if(isAncillaryDocketEditedAtAll.equalsIgnoreCase("true") && isCrtTimePetNumChanged.equalsIgnoreCase("false")){
			ActionErrors errors = new ActionErrors();
    		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "HEARING ALREADY SCHEDULED FOR THIS DAY"));
    		saveErrors(aRequest, errors);
    		return aMapping.findForward(UIConstants.FAILURE);
		} else {
			messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.recordUpdatedAncillaryCourtCompleted"));//transaction Complete. Continue Processing or Click on Ancillary Display button for Ancillary Display. 
		}
		aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
		saveMessages(aRequest, messageHolder);
		//commented out as page needs to default to the given values from update bug 144256
		/*courtHearingForm.reset();
		courtHearingForm.setCourtDate(courtDate);
		courtHearingForm.setCourtId(courtId);
		courtHearingForm.setFilingDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));*/
		courtHearingForm.setAction("ancillarySettingUpdate");		
		return aMapping.findForward(UIConstants.FAILURE);
	}
	
    
    
    /**
     * updateSetting(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward updateSetting(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
    {
 	   CourtHearingForm form = (CourtHearingForm)aForm;
 	   form.setAction("ancillarySettingUpdate");
 	   String selectedDocketEvt = form.getDocketEventsRec();
 	   Collection<DocketEventResponseEvent> ancillaryDktsResp = form.getDktSearchResults();
 	   Iterator<DocketEventResponseEvent> dcktIter =  ancillaryDktsResp.iterator();
 	  	while (dcktIter.hasNext()){
     	DocketEventResponseEvent dcktRecEvnt = dcktIter.next();
     	
     	if(selectedDocketEvt.equals(dcktRecEvnt.getDocketEventId())){
     		    form.setSelectedDocket(dcktRecEvnt);
     			form.setCourtId(dcktRecEvnt.getCourt());
     			form.setCourtDate(DateUtil.dateToString(dcktRecEvnt.getEventDate(), "MM/dd/yyyy"));
     			//form.setCourtDate(dcktRecEvnt.getCourtDate());
     			//form.setCourtTime(dcktRecEvnt.getCourtTime());
     			form.setCourtTime(dcktRecEvnt.getFormattedCourtTime());
     			if(dcktRecEvnt.getPetitionNumber() != null){
     			    form.setPetitionNumber(dcktRecEvnt.getPetitionNumber().trim());
     			}
     			form.setTypeCaseStr(dcktRecEvnt.getTypeCase());
     			if(dcktRecEvnt.getRespondantName() != null){
     			    form.setRespondentName(dcktRecEvnt.getRespondantName().trim());
     			}
     			form.setHearingType(dcktRecEvnt.getSettingReason());
     			form.setBarNumber(dcktRecEvnt.getBarNum());
     			form.setAttorneyConnection(dcktRecEvnt.getAttorneyConnection());
     			if (dcktRecEvnt.getAttorneyName() != null){
     			    form.setAttorneyName(dcktRecEvnt.getAttorneyName().trim());
     			} //12/14/2018 added NULL check before all the trim methods
     			form.setSeqNumber(dcktRecEvnt.getSeqNum());
     			form.setFilingDate(dcktRecEvnt.getFilingDate());
     			form.setIssueFlag(dcktRecEvnt.getIssueFlag());
     			form.setTypeCase(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.TYPE_CASE, true));
     			form.setHearingTypes(PDCriminalCodeTableHelper.loadHearingTypesWithCatIndABonlyActive()); //edited for US 157620
     			form.setDcktEvtId(dcktRecEvnt.getDocketEventId());
     		}
 	  	}
 	   return aMapping.findForward(UIConstants.UPDATE); 
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
     * ancillarySettingDisplay
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward ancillarySettingDisplay(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
    {
 	   CourtHearingForm form = (CourtHearingForm)aForm;
 	   form.setAction("ancillarySettingDisplay");
 	   return aMapping.findForward(UIConstants.ANCILLARY_SETTING_DISPLAY); 
    }
    
    /** ancillaryUpdate
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward ancillaryUpdate(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
    {
 	   CourtHearingForm form = (CourtHearingForm)aForm;
 	   form.setAction("ancillaryUpdateFromCourtActivity");
 	   DocketEventResponseEvent selectedDckt = form.getSelectedDocket();
 	   form.setCourtId(selectedDckt.getCourt());
 	   form.setCourtDate(DateUtil.dateToString(selectedDckt.getEventDate(), "MM/dd/yyyy"));
 	   form.setCourtDate(selectedDckt.getCourtDate());
 	   form.setCourtTime(selectedDckt.getCourtTime());
 	   form.setPetitionNumber(selectedDckt.getPetitionNumber());
 	   form.setTypeCaseStr(selectedDckt.getTypeCase());
 	   form.setRespondentName(selectedDckt.getRespondantName());
 	   form.setHearingType(selectedDckt.getSettingReason());
 	   form.setBarNumber(selectedDckt.getBarNum());
 	   form.setAttorneyConnection(selectedDckt.getAttorneyConnection());
 	   form.setAttorneyName(selectedDckt.getAttorneyName());
 	   form.setSeqNumber(selectedDckt.getSeqNum());
 	   form.setFilingDate(selectedDckt.getFilingDate());
 	   form.setIssueFlag(selectedDckt.getIssueFlag());
 	   form.setTypeCase(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.TYPE_CASE, true));
 	   form.setHearingTypes(PDCriminalCodeTableHelper.loadHearingTypesWithCatIndABonlyActive()); //edited for US 157620
 	   form.setDcktEvtId(selectedDckt.getDocketEventId());
 	   return aMapping.findForward(UIConstants.UPDATE);
    }
    
    /** validateBarNumber
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward validateBarNumber(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
    	CourtHearingForm form = (CourtHearingForm)aForm;
    	//form.setAction("courtHearing");   
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
     	    //form.setAction("validateBarNumber");
     	    form.setAttorneyName("");
     	    return aMapping.findForward(UIConstants.FAILURE);
     	}
     	
     	
     	if (attorneyDataList != null & !attorneyDataList.isEmpty())
     	{
     	    for (int x = 0; x < attorneyDataList.size();)
     	    {
     		AttorneyNameAndAddressResponseEvent respEvent =  ((List<AttorneyNameAndAddressResponseEvent>) attorneyDataList).get(x);
     		attorneyRespEvt = new AttorneyNameAndAddressResponseEvent();
     		attorneyRespEvt.setAttyName(respEvent.getAttyName());
     		attorneyRespEvt.setBarNum(respEvent.getBarNum());
     		attorneyRespEvt.setAttyNameHistory(respEvent.getAttyNameHistory());
     		break;
     	    }
     	}
     	if (attorneyRespEvt != null)
     	{
     	   //form.setAction("validateBarNumber");
     	   ActionMessages messageHolder = new ActionMessages();
     	   form.setAttorneyName(attorneyRespEvt.getAttyName());
     	   messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.barNumberValid"));
     	   aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
    	   saveMessages(aRequest, messageHolder);
    	}
     	return aMapping.findForward(UIConstants.ANCILLARY_SETTING_ADD); 
        }
    
    /**
     * SearchAttorney
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */ 
    public ActionForward searchAttorney(ActionMapping aMapping,ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	//form.setAction("ancillarySetting");
	form.setConfirmMsg(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	return aMapping.findForward(UIConstants.ATTORNEY_SEARCH_SUCCESS);
    }
    
    /** courtMainMenu
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward courtAction(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
    {
 	   CourtHearingForm form = (CourtHearingForm)aForm;
 	   //Get the docket search results and sort then in ascending order by court date
 	  List docketRespList = (List)form.getDktSearchResults();
	 	
 	 Collections.sort((List<DocketEventResponseEvent>) docketRespList, new Comparator<DocketEventResponseEvent>() {
		    @Override
		    public int compare(DocketEventResponseEvent evt1,
			    DocketEventResponseEvent evt2)
		    {
		    	 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				 Date date1 = new Date();
				 Date date2 = new Date();
				 try {
					 date1 = sdf.parse(evt1.getCourtDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    try {
					 date2 = sdf.parse(evt2.getCourtDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	return new CompareToBuilder().append(date2, date1)
					.toComparison();
		    }
		});
 	 DocketEventResponseEvent mostRecent = new DocketEventResponseEvent();
 	   if(!docketRespList.isEmpty())
 		   mostRecent = (DocketEventResponseEvent)docketRespList.get(0);
 	   form.setCourtId(mostRecent.getCourt());
 	   form.setCourtDate(mostRecent.getCourtDate()); 	  
 	   return aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY); 
    }
    
    /** courtMainMenu
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward ancillaryCourtActivity(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	String petitionNum = form.getPetitionNumber();
	form.reset();
	form.setJuvenileNumber(petitionNum);
	return aMapping.findForward(UIConstants.ANCILLARY_COURT_ACTIVITY);
    }



 
    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.submit","submit");
	keyMap.put("button.ancillarySettingDisplay","ancillarySettingDisplay");
	keyMap.put("button.updateSetting","updateSetting");
	keyMap.put("button.courtMainMenu","courtMainMenu");
	keyMap.put("button.update","update");
	keyMap.put("button.courtAction2","courtAction");
	keyMap.put("button.ancillaryUpdate","ancillaryUpdate");
	keyMap.put("button.validateBarNumber","validateBarNumber");
	keyMap.put("button.searchAttorney","searchAttorney");
	keyMap.put("button.go","ancillaryCourtActivity");
	}
}
