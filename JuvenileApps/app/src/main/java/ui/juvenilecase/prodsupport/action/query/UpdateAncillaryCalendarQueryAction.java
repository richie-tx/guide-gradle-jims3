package ui.juvenilecase.prodsupport.action.query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.productionsupport.GetJJSCLAncillaryCourtByPetitionNumAndHearingDateEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.form.ProdSupportForm;

public class UpdateAncillaryCalendarQueryAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	ProdSupportForm regform = (ProdSupportForm) form;
	
	/** Check for initial load of this page **/
	String clrChk = request.getParameter("clr");
	if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
		regform.clearAllResults();
		regform.setServeventId("");
		regform.setMsg("");
		return mapping.findForward("error");
	}
	String petitionNum = regform.getPetitionNum();
	String courtDate   = regform.getCourtDate();
	String docketId    = request.getParameter("docketId");
	ArrayList<DocketEventResponseEvent>ancillaryCalendarRecords = regform.getAncillaryCalendarRecords();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	
	if (docketId == null
		|| docketId.length() == 0 ) {
        	
        	    GetJJSCLAncillaryCourtByPetitionNumAndHearingDateEvent getJJSCLAncillaryEvent =(GetJJSCLAncillaryCourtByPetitionNumAndHearingDateEvent)
        		    										EventFactory.getInstance(ProductionSupportControllerServiceNames.GETJJSCLANCILLARYCOURTBYPETITIONNUMANDHEARINGDATE);
        	    getJJSCLAncillaryEvent.setPetitionNumber(petitionNum);
        	    if ( petitionNum != null 
            		&& petitionNum.length() >0 ) {
            	   
            	    if ( courtDate != null 
            		    && courtDate.length() > 0){
            		Date courtDt = DateUtil.stringToDate(courtDate, DateUtil.DATE_FMT_1);
            		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            		String formattedCourtDate  = sdf.format(courtDt);
            		getJJSCLAncillaryEvent.setCourtDate(formattedCourtDate);
            	    }
        	    
        	    dispatch.postEvent(getJJSCLAncillaryEvent);
        	    CompositeResponse resp = (CompositeResponse) dispatch.getReply();
        	    ancillaryCalendarRecords = (ArrayList) MessageUtil.compositeToCollection(resp, DocketEventResponseEvent.class);
        	    if (ancillaryCalendarRecords.size() > 0 ){
        		regform.setRespondentName(ancillaryCalendarRecords.get(0).getRespondantName());
        		regform.setAncillaryCalendarRecords(ancillaryCalendarRecords);
        		regform.setMsg(ancillaryCalendarRecords.size() + " RECORD(S) FOUND");
        	    } else {
        		regform.setPetitionNum("");
        		regform.setCourtDate("");
        		ActionErrors errors = new ActionErrors();
        		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "NO 'ANCILLARY CALENDAR RECORD' FOUND"));
        		saveErrors(request, errors);
        		return mapping.findForward("error");
        	    }
        	    
        	}
        	return mapping.findForward("listsuccess");
	} else {
	    if (ancillaryCalendarRecords.size() > 0 ){
		for (DocketEventResponseEvent ancillaryCalendarRecord : ancillaryCalendarRecords ) {
		    if ( docketId.equals(ancillaryCalendarRecord.getDocketEventId()) ) {
			ancillaryCalendarRecord.setOldattorneyName(ancillaryCalendarRecord.getAttorneyName());
			ancillaryCalendarRecord.setOldbarNum(ancillaryCalendarRecord.getBarNum());
			regform.setAncillaryCalendarRecord(ancillaryCalendarRecord);
		    }
		}
		List<JuvenileDispositionCodeResponseEvent> juvenileDispositions = JuvenileDistrictCourtHelper.getSortedCourtDecisions();
		regform.setCourtDecisionsResponses(juvenileDispositions);
		regform.setDetentionHearingResults(juvenileDispositions);
		regform.setCourtHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes("ANCILLARY"));
	    }
	    return mapping.findForward("success");
	    
	}
	
	
	
	
    }
    
}
