package ui.juvenilecase.prodsupport.action.query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByJuvNumEvent;
import messaging.districtCourtHearings.GetJJSCLAncillarySettingEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByJuvNumEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.productionsupport.GetJJSCLAncillaryCourtByPetitionNumAndHearingDateEvent;
import messaging.productionsupport.GetProductionSupportCourtRecordsEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProductionSupportControllerServiceNames;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.codetable.SimpleCodeTableHelper;
import pd.juvenilecase.districtCourtHearings.JJSCLAncillary;

import ui.common.CodeHelper;
import ui.common.Name;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.ProdSupportForm;

//import mojo.km.utilities.DateUtil;

public class DeleteAncillaryCourtCalendarRecordQueryAction extends Action
{

    /* protected void addButtonMapping(Map keyMap)
     {	
    keyMap.put("button.submit", "submit");
    keyMap.put("button.cancel", "cancel");
    keyMap.put("button.refresh", "refresh");
    keyMap.put("button.back", "back");
      }*/
    private Logger log = Logger.getLogger("DeleteAncillaryCourtCalendarRecordQueryAction");

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
	ProdSupportForm regform = (ProdSupportForm) form;	
	
	 String docketId = null;
	 String forward = "success";
	 
	 docketId = request.getParameter("docketId");		    
	
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
	
	if (petitionNum == null || petitionNum.equals(""))
	{
	    regform.setMsg("DeleteAncillaryCourtCalendarRecordQueryAction.java - petition number is null");
	    return (mapping.findForward("error"));
	}
    		
	ArrayList<DocketEventResponseEvent> ancillaryCalendarRecords = regform.getAncillaryCalendarRecords();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	
	//clear any previous form values
	regform.clear();
	
	if ( petitionNum != null && petitionNum.length() > 0) 
	{
	    GetJJSCLAncillaryCourtByPetitionNumAndHearingDateEvent getJJSCLAncillaryEvent =(GetJJSCLAncillaryCourtByPetitionNumAndHearingDateEvent)
		    										EventFactory.getInstance(ProductionSupportControllerServiceNames.GETJJSCLANCILLARYCOURTBYPETITIONNUMANDHEARINGDATE);
	    getJJSCLAncillaryEvent.setPetitionNumber(petitionNum);
	    
	    if ( courtDate != null && courtDate.length() > 0){
    		Date courtDt = DateUtil.stringToDate(courtDate, DateUtil.DATE_FMT_1);
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		String formattedCourtDate  = sdf.format(courtDt);
    		getJJSCLAncillaryEvent.setCourtDate(formattedCourtDate);
    	    }
	    
	    dispatch.postEvent(getJJSCLAncillaryEvent);
	    CompositeResponse resp = (CompositeResponse) dispatch.getReply();
	    ancillaryCalendarRecords = (ArrayList) MessageUtil.compositeToCollection(resp, DocketEventResponseEvent.class);
	}
	
	if(docketId == null || docketId.equals(""))
	{		    
		    if(ancillaryCalendarRecords.size() > 0 )
		    {
			 regform.setPetitionNum(ancillaryCalendarRecords.get(0).getPetitionNumber());
			 regform.setRespondentName(ancillaryCalendarRecords.get(0).getRespondantName());
			    regform.setAncillaryCalendarRecords(ancillaryCalendarRecords);
			    regform.setMsg(ancillaryCalendarRecords.size() + " RECORD(S) FOUND");
			    
			    forward = "success";
			    
		    } else {
			
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "NO 'ANCILLARY CALENDAR RECORD' FOUND"));
			saveErrors(request, errors);
			
			forward = "error";
		    }
		    
	    
	} else {
	    
	    if (ancillaryCalendarRecords.size() > 0 ){
		    
		    ArrayList<DocketEventResponseEvent> ancillaryCourtDetail = new ArrayList<DocketEventResponseEvent>();
		    Iterator<DocketEventResponseEvent> docketIter = ancillaryCalendarRecords.iterator();
		    
		    while (docketIter.hasNext())
		    {
			DocketEventResponseEvent docRespEvent = docketIter.next();
			if (docketId.equals(docRespEvent.getDocketEventId()))
			{
			    ancillaryCourtDetail.add(docRespEvent);
			    regform.setRespondentName(docRespEvent.getRespondantName());
			    regform.setPetitionNum(docRespEvent.getPetitionNumber());
			    break;
					
			}
		    }		    
		    
		    if(ancillaryCourtDetail.size() > 0)
		    {
			regform.setAncillaryCalendarRecords(ancillaryCourtDetail);				
			forward = "listsuccess";
		    }  
			    
	    	}	    
	
	}
	
	//sort ancillaryCalendarRecords array by JJSCLANCILLARY_ID (docketEventId) - Bug 154330
	if (ancillaryCalendarRecords.size() > 0) {
	    Collections.sort(ancillaryCalendarRecords, new Comparator<DocketEventResponseEvent>() {
	        @Override
	        public int compare(DocketEventResponseEvent obj1, DocketEventResponseEvent obj2) {
	            return Integer.parseInt(obj1.getDocketEventId()) == Integer.parseInt(obj2.getDocketEventId()) ? 0 : Integer.parseInt(obj1.getDocketEventId()) < Integer.parseInt(obj2.getDocketEventId()) ? 1 : -1;
	        }
	    });
	  }
	

        return mapping.findForward(forward);
        
     }
    
    
    
  }
