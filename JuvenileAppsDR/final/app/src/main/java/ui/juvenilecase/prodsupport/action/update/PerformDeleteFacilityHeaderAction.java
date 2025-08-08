package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.productionsupport.DeleteProductionSupportMaysiAssessmentEvent;
import messaging.productionsupport.GetProductionSupportMaysiAssessmentEvent;
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
 * @author rcarter
 * 
 * Perform a delete for a Facility Header record
 */

public class PerformDeleteFacilityHeaderAction extends Action {

	private Logger log = Logger.getLogger("PerformDeleteFacilityHeaderAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		String maysiassessmntId = regform.getMaysiassessmntId();

		if (maysiassessmntId == null || maysiassessmntId.equals("")) {
			regform.setMsg("Maysi Assessment ID was null. Please enter a valid Assessment ID.");
			return mapping.findForward("error");
		}	
		
		// delete maysi assessment
		deleteSingleMaysi(maysiassessmntId, SecurityUIHelper.getLogonId());
		
		//check if deletion was successful
		ArrayList<MAYSIDetailsResponseEvent> maysiAssessmentList = retrieveSingleMaysi(maysiassessmntId, SecurityUIHelper.getLogonId());
		
		if(maysiAssessmentList == null){
			log.info("MAYSI Assessment Query ID: " + maysiassessmntId + " LogonId: " + 
					SecurityUIHelper.getLogonId() + " successfully deleted.");
			regform.setMsg("");
			return mapping.findForward("success");
		}else{
			log.info("Error - MAYSI Assessment could not be deleted. Record Id for maysiassessmntId: " + maysiassessmntId + " LogonId: " + 
					SecurityUIHelper.getLogonId());
			regform.setMsg("Error - MAYSI Assessment could not be deleted. Record Id for maysiassessmntId: " + maysiassessmntId);
			return mapping.findForward("error");
		}
	}
	
	
	/**
	 * 
	 * @param assessmntId
	 * @param logonid
	 * @return
	 */
	private void deleteSingleMaysi(String assessmntId, String logonid) {

		log.info("About to DELETE MAYSI Assessment Query ID: " + assessmntId + " LogonId: " + 
				logonid);
		/**
		 * Delete JCMaysiAssessmnt record
		 */	
		DeleteProductionSupportMaysiAssessmentEvent deleteAssessmentEvent = (DeleteProductionSupportMaysiAssessmentEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTMAYSIASSESSMENT);
		deleteAssessmentEvent.setAssessmentId(assessmntId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(deleteAssessmentEvent);		

	}
	
	/**
	 * 
	 * @param assessmntId
	 * @param logonid
	 * @return
	 */
	private ArrayList retrieveSingleMaysi(String assessmntId, String logonid) {

		log.info("MAYSI Assessment Query ID: " + assessmntId + " LogonId: " + 
				logonid);
		/**
		 * Search for JCMAYSIASSESSMNT.
		 */
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetProductionSupportMaysiAssessmentEvent getMaysiAssessEvent = (GetProductionSupportMaysiAssessmentEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTMAYSIASSESSMENT);
		getMaysiAssessEvent.setJuvenileId(null);
		getMaysiAssessEvent.setReferralNumber(null);
		getMaysiAssessEvent.setMaysiAssessmentId(new Integer(assessmntId));

		dispatch.postEvent(getMaysiAssessEvent);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		Collection<MAYSIDetailsResponseEvent> maysiAssessmentEvents =
			MessageUtil.compositeToCollection(compositeResponse, MAYSIDetailsResponseEvent.class);
		ArrayList<MAYSIDetailsResponseEvent> maysiAssessmentList =  new ArrayList<MAYSIDetailsResponseEvent>();
		maysiAssessmentList.addAll(maysiAssessmentEvents);

		if (maysiAssessmentList.size() == 0) {
			maysiAssessmentList = null;
		}
		return maysiAssessmentList;

	}
}
