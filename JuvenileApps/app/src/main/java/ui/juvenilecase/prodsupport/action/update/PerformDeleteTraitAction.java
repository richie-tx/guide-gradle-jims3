package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.GetJuvenileSchoolByIDEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.productionsupport.DeleteProductionSupportJuvenileSchoolHistoryEvent;
import messaging.productionsupport.DeleteProductionSupportTraitEvent;
import messaging.productionsupport.GetProductionSupportTraitsEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 */

public class PerformDeleteTraitAction extends Action {

	private Logger log = Logger.getLogger("PerformDeleteTraitAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;

		String logonid = SecurityUIHelper.getLogonId();
	
		String traitId = regform.getTraitId();

		if (traitId == null || traitId.equals(" ")) {
			regform.setMsg("TraitId was null.");
			return mapping.findForward("error");
		}
		
		/**Delete the JCSCHOOL record **/
		log.info("About to perform a TRAIT DELETE for TRAIT_ID=" + traitId + " and LOGONID = " + logonid);
		DeleteProductionSupportTraitEvent deleteTraitEvent =
			(DeleteProductionSupportTraitEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTTRAIT);
		deleteTraitEvent.setTraitId(traitId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(deleteTraitEvent);			
		
		/** Check if the record was correctly deleted **/
		Boolean isTraitDeleted = isTraitIdDeleted(traitId, regform);
		if(isTraitDeleted){
		log.info("Sucessfully Performed a TRAIT DELETE for TRAIT_ID=" + traitId + " and LOGONID = " + logonid);
			regform.setMsg("");
			return mapping.findForward("success");
		}else{
			log.info("ERROR: Could not delete TRAIT DELETE for TRAIT_ID= " + traitId + " and LOGONID = " + logonid);
			regform.setMsg("ERROR: Could not delete TRAIT DELETE for TRAIT_ID= " + traitId);
			return mapping.findForward("error");
		}

	}
	
	
	/**
	 * retrieve all the traits for a given juvenile id in a collection
	 * @param juvenileId
	 * @return
	 */
	private boolean isTraitIdDeleted(String traitId, ProdSupportForm regform){

		boolean isDeleted = true;
		GetProductionSupportTraitsEvent getTraitsEvent = (GetProductionSupportTraitsEvent)EventFactory.
				getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTTRAITS );
		JuvenileTraitResponseEvent traitResponseEvent = (JuvenileTraitResponseEvent)regform.getTraits().get(0);
		String JuvenileNum = "";
		ArrayList<JuvenileTraitResponseEvent> getTraitsResponsesList = null;
		if(traitResponseEvent != null && traitResponseEvent.getJuvenileNum() != null){
			JuvenileNum = traitResponseEvent.getJuvenileNum();
			getTraitsEvent.setJuvenileId(JuvenileNum);
			CompositeResponse getTraitsResponse = MessageUtil.postRequest(getTraitsEvent);
			getTraitsResponsesList = (ArrayList) MessageUtil.compositeToCollection(getTraitsResponse, JuvenileTraitResponseEvent.class);
			Collections.sort(getTraitsResponsesList, JuvenileTraitResponseEvent.JuvenileTraitIdComparator);
			for(JuvenileTraitResponseEvent event:getTraitsResponsesList){
				if(event.getJuvenileTraitId().equals(traitId)){
					isDeleted = false;
					break;
				}
			}
		}else{
			isDeleted = false;
		}
			
		
		return isDeleted;
	}
	
}
