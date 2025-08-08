package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.productionsupport.GetProductionSupportCalendarServiceEventsEvent;
import messaging.productionsupport.GetProductionSupportMaysiDetailEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.juvenilecase.prodsupport.helpers.Constants;
import ui.security.SecurityUIHelper;

/**
 * @author jims2
 * 
 *         Takes in JUVENILE_ID and SERVEVENT_ID and displays the record
 */

public class ViewMaysiDetailQueryAction extends Action {
	private Logger log = Logger.getLogger("ViewMaysiDetailQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		

		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
			regform.setReferralNum("");
			regform.clearAllResults();
			regform.setMsg("");
			return mapping.findForward("error");
		}

		String juvenileId = regform.getFromJuvenileId();

		if (juvenileId == null || juvenileId.equals("")) {
			regform.setMsg("You must enter a valid Juvenile ID.");
			return mapping.findForward("error");
		}

		String referralnum = regform.getReferralNum();
		//Referralnumber is optional for this action

		regform.clearAllResults();

		regform.setReferralNum(referralnum);
		regform.setFromJuvenileId(juvenileId);

		// Log the query attempt
		log.info("MAYSI Query - JuvID: " + juvenileId + " / ReferralNum: " + referralnum + " LogonId: " + SecurityUIHelper.getLogonId());

		ArrayList maysidetails = retrieveMaysiDetails(juvenileId, referralnum);

		if (maysidetails != null && maysidetails.size() > 0) {
			regform.setMaysidetailCount(maysidetails.size());
			regform.setMaysidetails(maysidetails);
		} else {
			regform.setMsg("No MAYSI Detail records found for JuvenileID "
					+ juvenileId + " and Referral " + referralnum + ".");
			return mapping.findForward("error");
		}

		regform.setMsg("");
		return mapping.findForward("success");

	}
	
	private ArrayList retrieveMaysiDetails(String juvenileId, String referralnum){

		/**
		 * Search for JCMAYSIDETAIL.
		 */
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetProductionSupportMaysiDetailEvent getMaysiEvent = (GetProductionSupportMaysiDetailEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTMAYSIDETAIL);
		getMaysiEvent.setJuvenileId(juvenileId);
		getMaysiEvent.setReferralNumber(referralnum);
		getMaysiEvent.setMaysiDetailId(null);

		dispatch.postEvent(getMaysiEvent);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		Collection<MAYSIDetailsResponseEvent> maysiEvents =
				MessageUtil.compositeToCollection(compositeResponse, MAYSIDetailsResponseEvent.class);

		ArrayList<MAYSIDetailsResponseEvent> maysiEventsList =  new ArrayList<MAYSIDetailsResponseEvent>();
		if (maysiEvents!=null && maysiEvents.size() > 0){
			maysiEventsList.addAll(maysiEvents);
		}
		return maysiEventsList;	
	}
}
