package ui.juvenilecase.prodsupport.action.update;

import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.productionsupport.SaveProductionSupportServiceEventAttendanceEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import naming.ProductionSupportControllerServiceNames;
import naming.ServiceEventControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author jims2
 */

public class PerformModifyServEventStatusAction extends Action {
	private Logger log = Logger.getLogger("PerformModifyServEventStatusAction");
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;

		String logonid = SecurityUIHelper.getLogonId();
		
		String juvenileId = regform.getFromJuvenileId();
		String servEventId = regform.getServeventId();
		
		SaveProductionSupportServiceEventAttendanceEvent saveAttendanceEvent = (SaveProductionSupportServiceEventAttendanceEvent)
		EventFactory.getInstance(ProductionSupportControllerServiceNames.SAVEPRODUCTIONSUPPORTSERVICEEVENTATTENDANCE);

		// error checking
		if (juvenileId == null || juvenileId.equals("")) {
			regform.setMsg("JuvenileID was null. Please provide a valid JuvenileId.");
			return mapping.findForward("error");
		}
		if (servEventId == null || servEventId.equals("")) {
			regform.setMsg("Service Event ID was null. please provide a valid servEventId.");
			return mapping.findForward("error");
		}		
		
		saveAttendanceEvent.setServiceEventId(servEventId);
		saveAttendanceEvent.setJuvenileId(juvenileId);
		
		String newAttendstatuscd = regform.getAttendstatusBox();
		String newAddlAttendees = regform.getNewAddlAttendees();
		
		if((newAttendstatuscd == null || newAttendstatuscd.equals("")) && (newAddlAttendees == null || newAddlAttendees.equals(""))){
			regform.setMsg("There were no changes entered.");
			return mapping.findForward("error");
		}
		Integer newAttendeesNumber = new Integer(0);
		// check if addlAttendes entry, and if so set it
		if (newAddlAttendees != null && newAddlAttendees.length() > 0 ) {
			// check if proper numeric
			try{
				if(!newAttendeesNumber.equals(regform.getNewAddlAttendees())){
					newAttendeesNumber = Integer.parseInt(newAddlAttendees);
					saveAttendanceEvent.setAddlAttendees(newAttendeesNumber);
				}
			}catch(NumberFormatException nfe){
				regform.setMsg("The New Additional Attendees must be a numeric value.");
				return mapping.findForward("error");
			}
		}
		// check if a status cd changed, and if so set it.
		if (newAttendstatuscd != null && newAttendstatuscd.length() > 0 ) {
			saveAttendanceEvent.setAttendanceStatusCd(newAttendstatuscd);
		}
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(saveAttendanceEvent);		
		log.info("["+new Date()+"] ProdSupport  (" + logonid);
			
		regform.setMsg("");
		return mapping.findForward("success");

	}

}
