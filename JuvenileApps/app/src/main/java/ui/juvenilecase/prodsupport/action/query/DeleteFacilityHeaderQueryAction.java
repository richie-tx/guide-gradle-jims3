package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
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
 * Query to retrieve Facility Header records to delete
 */

public class DeleteFacilityHeaderQueryAction extends Action {

	private Logger log = Logger.getLogger("DeleteFacilityHeaderQueryAction");
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
		/** Redirect if this comes from deleteMaysiAssesQuery2.jsp **/
		String editFlag = request.getParameter("edit");
		if (editFlag != null && editFlag.equalsIgnoreCase("Y")) {

			String assessmentId = regform.getMaysiassessmntId();
			if (assessmentId != null && assessmentId.equals("") == false) 
			{
				regform.clearAllResults();
				regform.setFromJuvenileId(juvenileId);
				
				// retrieve single maysi assessment record based on key
				ArrayList maysis = retrieveSingleMaysi(assessmentId, SecurityUIHelper.getLogonId());
				regform.setMaysis(maysis);

				if (maysis == null)
					return mapping.findForward("error");
				else
					return mapping.findForward("continue");
			} else {
				regform.setMsg("AssessmentId didn't pass through to the Action. DeleteMaysiAssessQueryAction (86)");
				return mapping.findForward("error");
			}

		}

		/** Regular query logic continues here **/

		regform.clearAllResults();

		regform.setReferralNum(referralnum);
		regform.setFromJuvenileId(juvenileId);

		ArrayList maysis = retrieveAllMaysis(juvenileId, referralnum, SecurityUIHelper.getLogonId());

		if (maysis != null) {
			regform.setMaysiCount(maysis.size());
			regform.setMaysis(maysis);
			regform.setMsg("");
			return mapping.findForward("success");
		} else {
			regform.setMaysiCount(0);
			regform.setMaysis(null);
			regform.setMsg("No MAYSI records found for JuvenileID "
					+ juvenileId + " and Referral " + referralnum + ".");
			return mapping.findForward("error");
		}

		

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
	
	/**
	 * 	
	 * @param juvenileId
	 * @param referralnum
	 * @param logonId
	 * @return
	 */
	private ArrayList retrieveAllMaysis(String juvenileId,
			String referralnum, String logonId) {

		log.info("MAYSI Query - JuvID: " + juvenileId
				+ " / ReferralNum: " + referralnum + " LogonId: " + SecurityUIHelper.getLogonId());
		/**
		 * Search for JCMAYSIASSESSMNT.
		 */
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetProductionSupportMaysiAssessmentEvent getMaysiAssessEvent = (GetProductionSupportMaysiAssessmentEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTMAYSIASSESSMENT);
		getMaysiAssessEvent.setJuvenileId(juvenileId);
		getMaysiAssessEvent.setReferralNumber(referralnum);
		getMaysiAssessEvent.setMaysiAssessmentId(null);

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
