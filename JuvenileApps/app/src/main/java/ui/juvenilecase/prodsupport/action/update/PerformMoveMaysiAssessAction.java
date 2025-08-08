package ui.juvenilecase.prodsupport.action.update;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.reply.JuvenileProfilesResponseEvent;
import messaging.productionsupport.GetProductionSupportJuvenileEvent;
import messaging.productionsupport.MoveProductionSupportMaysiAssessmentEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
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

public class PerformMoveMaysiAssessAction extends Action {

	private Logger log = Logger.getLogger("PerformMoveMaysiAssessAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		String maysiassessmntId = regform.getMaysiassessmntId();
		String newJuvenileId = regform.getToJuvenileId();

		
		if (maysiassessmntId == null || maysiassessmntId.equals("")) {
			regform.setMsg("PerformMoveMaysiAssessAction.java - Maysi Assessment ID was null.");
			return mapping.findForward("error");
		}
	
		if (newJuvenileId == null || newJuvenileId.equals("")) {
			regform.setMsg("PerformMoveMaysiAssessAction.java - New JuvenileID was null.");
			return mapping.findForward("error");
		}
		
		//Verify valid JuvenileID		
		JuvenileProfilesResponseEvent juvenile = retrieveJuvenile(newJuvenileId);
		if (juvenile==null || juvenile.getJuvenileNum() == null || juvenile.getJuvenileNum().equals("")){
			regform.setToJuvenileId("");
			regform.setMsg("Juvenile not found. You must enter a valid JuvenileID.");
			return mapping.findForward("error");	
		}	
				
		/**
		 * Update JCMaysiAssessmnt table.
		 */	
		MoveProductionSupportMaysiAssessmentEvent moveAssessmentEvent = (MoveProductionSupportMaysiAssessmentEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.MOVEPRODUCTIONSUPPORTMAYSIASSESSMENT);
		moveAssessmentEvent.setAssessmentId(maysiassessmntId);
		moveAssessmentEvent.setJuvenileNumber(newJuvenileId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(moveAssessmentEvent);
		log.info("Prod Support MAYSI Assessment Move Completed - AssessmentID:" + maysiassessmntId  + "NewJuvNumber: " + newJuvenileId
				+ " LogonId: " + SecurityUIHelper.getLogonId());
		regform.setMsg("");
		return mapping.findForward("success");
	}
	
	/**
	 * returns juvenile number if it finds it
	 * @param juvenileNumber
	 * @return
	 */
	private JuvenileProfilesResponseEvent retrieveJuvenile(String juvenileNumber){

		/**
		 * Search for jcjuvenile.
		 */	
		GetProductionSupportJuvenileEvent getJuvenileEvent = (GetProductionSupportJuvenileEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILE);
		getJuvenileEvent.setJuvenileId(juvenileNumber);
		JuvenileProfilesResponseEvent resp = (JuvenileProfilesResponseEvent) MessageUtil.postRequest(getJuvenileEvent, JuvenileProfilesResponseEvent.class);		
			
		return resp;
	}
}
