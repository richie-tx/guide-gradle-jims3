/*
 * Created on Mar 18, 2008
 *
 */
package ui.supervision.cscdcalendar.officeVisit.action;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdcalendar.GetCSOfficeVisitsEvent;
import messaging.cscdcalendar.reply.CSEventTypeResponseEvent;
import messaging.cscdcalendar.reply.CSGroupOfficeVisitResponseEvent;
import messaging.cscdcalendar.reply.CSOfficeVisitResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSEventControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;
import ui.supervision.cscdcalendar.form.CSCalendarOVForm;

/**
 * @author awidjaja
 * This class will handle the display of office visits & 
 * group office visits for a particular day & positionId
 */
public class DisplayCSOVEventsAction extends JIMSBaseAction {

	public ActionForward link(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		
		//take priority over date being passed in through query string
   		String calendarDate = aRequest.getParameter("calendardate");
   		if(calendarDate != null) {
   			Date date = new Date();
   			date.setTime(Long.parseLong(calendarDate));
   			form.setEventDate(date);
   		}
   		
   		if(form.getEventDate() == null) {
   			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Event Date is missing.");
//   			return aMapping.findForward(UIConstants.SUCCESS);
   		}
   		
   		//get positionId & context from displayForm
		CSCalendarDisplayForm displayForm = 
			(CSCalendarDisplayForm)getSessionForm(aMapping, aRequest,"csCalendarDisplayForm", true);
		form.setContext(displayForm.getContext());
		form.setPositionId(displayForm.getPositionId());
		form.setAgencyId(displayForm.getAgencyId());

		GetCSOfficeVisitsEvent getOfficeVisits = 
			(GetCSOfficeVisitsEvent)EventFactory.getInstance(CSEventControllerServiceNames.GETCSOFFICEVISITS);
		getOfficeVisits.setCurrentContext(form.getContext());
		getOfficeVisits.setEventDate(form.getEventDate());
		
		
		if(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(displayForm.getContext())){
			getOfficeVisits.setSuperviseeId(displayForm.getSuperviseeId());
		
		} else if(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION.equals(displayForm.getContext())) {
			getOfficeVisits.setPositionId(displayForm.getPositionId());
		}
		
		HashMap eventTypeCodesMap = getEventTypes( form );
		CompositeResponse response = postRequestEvent(getOfficeVisits);
		Collection officeVisits = 
			MessageUtil.compositeToCollection(response, CSOfficeVisitResponseEvent.class);
		
		Iterator officeVisitIter = officeVisits.iterator();
		
		while ( officeVisitIter.hasNext()){
			
			CSOfficeVisitResponseEvent OVResponse = (CSOfficeVisitResponseEvent) officeVisitIter.next();
			String outCome = OVResponse.getOutcome();
			if ( "RE".equalsIgnoreCase( outCome ) ){
				officeVisitIter.remove();
			}else{
				String desc = (String) eventTypeCodesMap.get(OVResponse.getEventType());
				OVResponse.setEventTypeDesc( desc );
			}
		}
			
	
		Collection groupOfficeVisits = 
			MessageUtil.compositeToCollection(response, CSGroupOfficeVisitResponseEvent.class);
		
		Iterator groupVisitIter = groupOfficeVisits.iterator();
		
		while ( groupVisitIter.hasNext()){
			
			CSGroupOfficeVisitResponseEvent OVResponse = ( CSGroupOfficeVisitResponseEvent ) groupVisitIter.next();
			
			List officeVisitz = (List) OVResponse.getOfficeVisits();
			for ( int ov =0; ov < officeVisitz.size(); ov ++ ){
				CSOfficeVisitResponseEvent OVspnse = (CSOfficeVisitResponseEvent) officeVisitz.get(ov);
				String outCome = OVspnse.getOutcome();
				if ( "RE".equalsIgnoreCase( outCome ) ){
					officeVisitz.remove(ov);
					ov--;
				}else {
					String desc = (String) eventTypeCodesMap.get(OVspnse.getEventType());
					OVspnse.setEventTypeDesc( desc );
				}
			}
		}
		
		form.setOfficeVisits((List)officeVisits);
		form.setGroupOfficeVisits((List)groupOfficeVisits);
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	private static HashMap getEventTypes( CSCalendarOVForm myForm){
			
			HashMap categoryMap = new HashMap();
			List catagoryList = myForm.getEventTypeList();
			// Save list for later use
			myForm.setEventTypeList( catagoryList );
				
			for ( int i =0; i < catagoryList.size(); i ++ ){
				CSEventTypeResponseEvent codeResponse = ( CSEventTypeResponseEvent ) catagoryList.get( i );
			    categoryMap.put( codeResponse.getEventType(), codeResponse.getDescription() );
			}
			return categoryMap;
	}
	
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link","link");

	}

}