package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.GetJuvenileAttendanceEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ServiceEventControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.juvenilecase.prodsupport.helpers.Constants;
import ui.security.SecurityUIHelper;


/**
 * @author jims2
 * 
 * Takes in JUVENILE_ID and SERVEVENT_ID and displays the record,
 * with a codetable dropdown to modify the column ATTENDSTATUSCD.
 */

public class ModifyServEventStatusQueryAction extends Action {

	private Logger log = Logger.getLogger("ModifyServEventStatusQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		
		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk!=null && clrChk.equalsIgnoreCase("Y"))
		{
			regform.clearAllResults();
			regform.setMsg("");
			return mapping.findForward("error");		
		}
		
		
		String juvenileId = regform.getFromJuvenileId();

		if (juvenileId == null || juvenileId.equals("")) {
			regform.setMsg("You must enter a valid Juvenile ID.");
			return mapping.findForward("error");
		}
		
		String serveventId = regform.getServeventId();

		if (serveventId == null || serveventId.equals("")) {
			regform.setMsg("You must enter a valid Service Event ID.");
			return mapping.findForward("error");
		}
		
		//Clear the form, then reset the key value		
		regform.clearAllResults();
		
		regform.setServeventId(serveventId);
		regform.setFromJuvenileId(juvenileId);
		
		//Log the query attempt
		log.info(" ServEventStatus Query - JuvID: " + juvenileId + " / ServeventID: " + serveventId + " LogonId" + SecurityUIHelper.getLogonId());
		
		/**
		 * Populate AttendanceStatusCD Drop-down
		 */
		List attendstatusCodes = CodeHelper.getCodes("SERVEVENT_ATTENDANCE_STATUS");

		if (attendstatusCodes!=null){
			regform.setAttendstatusCodes(new ArrayList(attendstatusCodes));
		}
		else{
			regform.setMsg("Error - Couldn't retrieve AttendStatusCD Codetables. ModifyServEventStatusQueryAction (90)");
			return mapping.findForward("error");
		}		
		/**
		 * Search for csservattend records
		 */
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		
		GetJuvenileAttendanceEvent getAttendanceEvent = (GetJuvenileAttendanceEvent)
				EventFactory.getInstance(ServiceEventControllerServiceNames.GETJUVENILEATTENDANCE);
		getAttendanceEvent.setJuvenileId(juvenileId);
				
		getAttendanceEvent.setServiceEventId(serveventId);

		dispatch.postEvent(getAttendanceEvent);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		Collection<ServiceEventAttendanceResponseEvent> attendanceEvents =
				MessageUtil.compositeToCollection(compositeResponse, ServiceEventAttendanceResponseEvent.class);

		ArrayList<ServiceEventAttendanceResponseEvent> servattends =  new ArrayList<ServiceEventAttendanceResponseEvent>();
		if (attendanceEvents!=null && attendanceEvents.size() > 0){
			for(ServiceEventAttendanceResponseEvent event:attendanceEvents){
				if(event.getAddlAttendees()!= null && event.getAddlAttendees().equalsIgnoreCase("")){
					event.setAddlAttendees("0");
				}
			}
			servattends.addAll(attendanceEvents);
			regform.setServattendCount(servattends.size());
			regform.setServattends(servattends);
		}	
		else
		{
			regform.setMsg("No program referral records found for JuvenileID " + juvenileId + " and Service Event "+
					serveventId + ".");
			return mapping.findForward("error");
		}
		
		regform.setMsg("");
		return mapping.findForward("success");

	}

}
