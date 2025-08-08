package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.productionsupport.SaveProductionSupportMaysiDetailEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author jims2
 */

public class PerformUpdateMaysiDetailAction extends Action {

	private Logger log = Logger.getLogger("PerformUpdateMaysiDetailAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		String logonid = SecurityUIHelper.getLogonId();
		
		String maysidetailId = regform.getMaysidetailId();
		String newReferralnum = regform.getNewReferralNum();
						
		if (maysidetailId == null || maysidetailId.equals("")) {
			regform.setMsg("PerformUpdateMaysiDetailAction.java - Maysi Detail ID was null.");
			return mapping.findForward("error");
		}
	
		if (newReferralnum == null || newReferralnum.equals("")) {
			regform.setMsg("Update value is required to process changes.");
			return mapping.findForward("error");
		}
		
		MAYSIDetailsResponseEvent record = retrieveRecord(regform);

		if (record==null){
			regform.setMsg("PerformUpdateMaysiDetailAction.java - Could not retrieve record.");
			return (mapping.findForward("error"));
		}
		
		// Call to update the maysi detail record
		log.info("BEGIN UPDATE JCMAYSIDETAIL Table for maysiDetailId: " + record.getMaysiDetailId() +  " LogonId : " +  logonid);
		
		SaveProductionSupportMaysiDetailEvent saveMaysiDetailEvent = (SaveProductionSupportMaysiDetailEvent)
			EventFactory.getInstance(ProductionSupportControllerServiceNames.SAVEPRODUCTIONSUPPORTMAYSIDETAIL);
		
		saveMaysiDetailEvent.setMaysiDetailId(record.getMaysiDetailId());
		saveMaysiDetailEvent.setReferralNumber(newReferralnum);
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(saveMaysiDetailEvent);
		regform.setMsg("");
		return mapping.findForward("success");
	}
	
	/**
	 * 
	 * @param regform
	 * @return
	 */
	private MAYSIDetailsResponseEvent retrieveRecord(ProdSupportForm regform){
		
		ArrayList maysis = regform.getMaysidetails();
		MAYSIDetailsResponseEvent record = null;
		
		Iterator iter = maysis.iterator();
		if (iter.hasNext())
		{
			record = (MAYSIDetailsResponseEvent)iter.next();
		}
		
		return record;

	}
}
