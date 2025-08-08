/*
 * Created on Mar 5, 2008
 *
 */
package ui.supervision.cscdcalendar.fieldVisit.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdcalendar.reply.CSFieldVisitResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;

/**
 * @author awidjaja
 *
 */
public class DisplayCSFVReorderItineraryAction extends JIMSBaseAction{

	 public ActionForward reorderItinerary( 
	 		ActionMapping aMapping, 
			ActionForm aForm, 
			HttpServletRequest aRequest, 
			HttpServletResponse aResponse) {
	 	
	 	CSCalendarFVForm form = (CSCalendarFVForm)aForm;
	 	form.setActivityFlow("reorderItinerary");
	 	
	 	//Keep the original sequence number for future comparison
	 	List eventsList = new ArrayList();
		if (form.getEventsList() != null && form.getEventsList().size() > 0) {
			eventsList = form.getEventsList();
		} else {
			if (form.getCurrentEventsList() != null && form.getCurrentEventsList().size() > 0) {
				form.setEventDate(form.getCurrentEventDate());
				form.setEventsList(form.getCurrentEventsList());
				eventsList = form.getCurrentEventsList();
			}
		}
	 	List orderList = new ArrayList();
	 	if(eventsList != null) {
	 		for(Iterator eventIter = eventsList.iterator();eventIter.hasNext();) {
	 			CSFieldVisitResponseEvent event =
	 				(CSFieldVisitResponseEvent)eventIter.next();
	 			orderList.add(event.getSequenceNum());	 			
	 		}
	 		String[] sequenceNum = new String[orderList.size()]; 
	 		
	 		orderList.toArray(sequenceNum);
	 		form.setOrigSequenceNum( sequenceNum );
	 	}
	 	
	 	
	 	
	 	return aMapping.findForward(UIConstants.SUCCESS);
	 	
	 }
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.reorderItinerary","reorderItinerary");
	}
	

}
