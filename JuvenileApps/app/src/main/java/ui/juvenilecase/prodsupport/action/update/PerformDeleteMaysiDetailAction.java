package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.productionsupport.DeleteProductionSupportMaysiDetailEvent;
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
 */

public class PerformDeleteMaysiDetailAction extends Action {

	private Logger log = Logger.getLogger("PerformDeleteMaysiDetailAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;		
		String maysidetailId = regform.getMaysidetailId();

		if (maysidetailId == null || maysidetailId.equals("")) {
			regform.setMsg("PerformUpdateMaysiDetailAction.java - MaysiDetailID was null.");
			return mapping.findForward("error");
		}
		
		// delete MAYSI detail record
		deleteSingleDetail(maysidetailId,  SecurityUIHelper.getLogonId());
		
		//check if deletion was successful
		ArrayList<MAYSIDetailsResponseEvent> maysiDetailList = retrieveSingleDetail(maysidetailId, SecurityUIHelper.getLogonId());
		
		if(maysiDetailList == null){
			log.info("MAYSI Detail ID: " + maysidetailId + " LogonId: " + 
					SecurityUIHelper.getLogonId() + " successfully deleted.");
			regform.setMsg("");
			return mapping.findForward("success");
		}else{
			log.info("Error - MAYSI Detail could not be deleted. Record Id for maysidetailId: " + maysidetailId + " LogonId: " + 
					SecurityUIHelper.getLogonId());
			regform.setMsg("Error - MAYSI Detail could not be deleted. Record Id for maysidetailId: " + maysidetailId);
			return mapping.findForward("error");
		}
	}
	
	/**
	 * 
	 * @param maysidetailId
	 * @param logonid
	 * @return
	 */
	private void deleteSingleDetail(String maysidetailId, String logonId){

		log.info("About to DELETE MAYSI Detail Query ID: " + maysidetailId + " LogonId: " + 
				logonId);		
		/**
		 * Search for JCMAYSIDETAIL.
		 */
		/**
		 * Delete JCMaysiDetail table record.
		 */	
		DeleteProductionSupportMaysiDetailEvent deleteMaysiDetailEvent = (DeleteProductionSupportMaysiDetailEvent)
			EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTMAYSIDETAIL);
		deleteMaysiDetailEvent.setMaysiDetailId(maysidetailId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(deleteMaysiDetailEvent);			
	}
	
	/**
	 * 
	 * @param maysidetailId
	 * @param logonid
	 * @return
	 */
	private ArrayList retrieveSingleDetail(String maysidetailId, String logonId){

		/**
		 * Search for JCMAYSIDETAIL.
		 */
		log.info("MAYSI Detail Query ID: " + maysidetailId + " LogonId: " + logonId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetProductionSupportMaysiDetailEvent getMaysiDetailEvent = (GetProductionSupportMaysiDetailEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTMAYSIDETAIL);
		getMaysiDetailEvent.setJuvenileId(null);
		getMaysiDetailEvent.setReferralNumber(null);
		getMaysiDetailEvent.setMaysiDetailId(new Integer(maysidetailId));
		dispatch.postEvent(getMaysiDetailEvent);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		Collection<MAYSIDetailsResponseEvent> maysiEvents =
			MessageUtil.compositeToCollection(compositeResponse, MAYSIDetailsResponseEvent.class);
		ArrayList<MAYSIDetailsResponseEvent> maysiDetailsList =  new ArrayList<MAYSIDetailsResponseEvent>();
		maysiDetailsList.addAll(maysiEvents);
		if (maysiDetailsList.size() == 0) {
			maysiDetailsList = null;
		}

		return maysiDetailsList;		
	}
}
