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

import ui.common.CodeHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author jims2
 * 
 *         Takes in JUVENILE_ID and REFERRAL NUMBER and displays the record, with a
 *         codetable dropdown to modify the column ASSESSOPTIONS.
 */

public class UpdateMaysiAssessQueryAction extends Action {

	private Logger log = Logger.getLogger("UpdateMaysiAssessQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
			regform.setReferralNum("");
			regform.setNewReferralNum("");
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
		// referral number is optional

		/** Redirect if this comes from updateMaysiAssesQuery2.jsp **/
		String editFlag = request.getParameter("edit");
		if (editFlag != null && editFlag.equalsIgnoreCase("Y")) {

			String assessmentId = regform.getMaysiassessmntId();

			if (assessmentId != null && assessmentId.equals("") == false) {
				regform.clearAllResults();
				regform.setFromJuvenileId(juvenileId);

				// Put in a code table helper... 
				ArrayList assessoptionCodes = (ArrayList) CodeHelper.getCodes("MAYSI_ASSESSMENT_OPTIONS");
				if (assessoptionCodes != null)
					regform.setAssessoptionCodes(assessoptionCodes);
				else {
					regform.setMsg("Error - Couldn't retrieve AssessOption Codetables. UpdateMaysiAssessQueryAction (90)");
					regform.setAssessoptionCodes(null);
					return mapping.findForward("error");
				}
				// retrieve unique assessment record
				ArrayList maysis = retrieveSingleMaysi(assessmentId, SecurityUIHelper.getLogonId());
				if (maysis == null){
					regform.setMsg("No records returned for assessmntId " + assessmentId);
					regform.setMaysis(null);
					regform.setMaysiCount(0);
					return mapping.findForward("error");
				}else{
					regform.setMaysis(maysis);
					regform.setMaysiCount(maysis.size());
					MAYSIDetailsResponseEvent um = ((MAYSIDetailsResponseEvent)maysis.get(0));
					regform.setNewReferralNum(um.getReferralNumber());
					regform.setAssessOfficerId(um.getAssessOfficerId());
					regform.setHasPreviousMaysi(um.isHasPreviousMAYSI());
					regform.setIsAdministered(um.isAdministered());
					regform.setReasonNotDone(um.getReasonNotDoneId());
					regform.setOtherReasonNotDone(um.getOtherReasonNotDone());
					return mapping.findForward("continue");
				}
				
			} else {
				regform.setMsg("AssessmentId didn't pass through to the Action. UpdateMaysiAssessQueryAction (86)");
				return mapping.findForward("error");
			}

		}

		/** Regular query logic continues here **/

		regform.clearAllResults();

		regform.setReferralNum(referralnum);
		regform.setFromJuvenileId(juvenileId);
		//Search for multiple maysi detail records
		ArrayList maysis = retrieveAllMaysis(juvenileId, referralnum, SecurityUIHelper.getLogonId());

		if (maysis != null) {
			regform.setMaysiCount(maysis.size());
			regform.setMaysis(maysis);
		} else {
			regform.setMsg("No MAYSI records found for JuvenileID "
					+ juvenileId + " and Referral " + referralnum + ".");
			return mapping.findForward("error");
		}

		regform.setMsg("");
		return mapping.findForward("success");

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
			String referralnum,String logonId) {

		// Log the query attempt
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
