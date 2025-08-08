package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
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
import ui.security.SecurityUIHelper;

/**
 * @author jims2
 * 
 *         Takes in JUVENILE_ID and displays the record.
 */

public class MoveMaysiDetailQueryAction extends Action {

	private Logger log = Logger.getLogger("MoveMaysiDetailQueryAction");
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

		String detailId = regform.getMaysidetailId();
		
		if (detailId == null || detailId.equals("")) {
			regform.setMsg("You must enter a valid MAYSI Detail ID.");
			return mapping.findForward("error");
		}
		
		regform.clearAllResults();
		regform.setMaysidetailId(detailId);

		// Log the query attempt
		log.info("MAYSI Detail Query - DetailID:" + detailId + " LogonId: " + SecurityUIHelper.getLogonId());

		/**
		 * Search for JCMAYSIDETAIL.
		 */
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetProductionSupportMaysiDetailEvent getMaysiDetailEvent = (GetProductionSupportMaysiDetailEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTMAYSIDETAIL);
		getMaysiDetailEvent.setJuvenileId(null);
		getMaysiDetailEvent.setReferralNumber(null);
		getMaysiDetailEvent.setMaysiDetailId(new Integer(detailId));
		dispatch.postEvent(getMaysiDetailEvent);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		Collection<MAYSIDetailsResponseEvent> maysiEvents =
			MessageUtil.compositeToCollection(compositeResponse, MAYSIDetailsResponseEvent.class);
		ArrayList<MAYSIDetailsResponseEvent> maysiDetailsList =  new ArrayList<MAYSIDetailsResponseEvent>();
		maysiDetailsList.addAll(maysiEvents);
		
		if (maysiDetailsList.size() > 0) {
			regform.setMaysidetailCount(maysiDetailsList.size());
			regform.setMaysidetails(maysiDetailsList);
			regform.setMsg("");
			return mapping.findForward("success");
		} else{
			regform.setMaysidetailCount(0);
			regform.setMaysidetails(null);
			regform.setMsg("There were no Maysi Detail records retrieved in query.");
			return mapping.findForward("error");
		}
		
	}	
}
