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
 * @author jims2
 * 
 *         Takes in JUVENILE_ID and displays the record.
 */

public class MoveMaysiAssessQueryAction extends Action {

	private Logger log = Logger.getLogger("MoveMaysiAssessQueryAction");
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

		String assessmentId = regform.getMaysiassessmntId();
		
		if (assessmentId == null || assessmentId.equals("")) {
			regform.setMsg("You must enter a valid AssessmentID.");
			return mapping.findForward("error");
		}
		
		regform.clearAllResults();

		regform.setMaysiassessmntId(assessmentId);

		// Log the query attempt
		log.info("MAYSI Query - AssessmentID: " + assessmentId + " LogonId: " + SecurityUIHelper.getLogonId());

		/**
		 * Search for JCMAYSIASSESSMNT.
		 */
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetProductionSupportMaysiAssessmentEvent getMaysiAssessEvent = (GetProductionSupportMaysiAssessmentEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTMAYSIASSESSMENT);
		getMaysiAssessEvent.setJuvenileId(null);
		getMaysiAssessEvent.setReferralNumber(null);
		getMaysiAssessEvent.setMaysiAssessmentId(new Integer(assessmentId));

		dispatch.postEvent(getMaysiAssessEvent);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		Collection<MAYSIDetailsResponseEvent> maysiAssessmentEvents =
			MessageUtil.compositeToCollection(compositeResponse, MAYSIDetailsResponseEvent.class);
		ArrayList<MAYSIDetailsResponseEvent> maysiAssessmentList =  new ArrayList<MAYSIDetailsResponseEvent>();
		maysiAssessmentList.addAll(maysiAssessmentEvents);

		if (maysiAssessmentList.size() > 0) {
			regform.setMaysiCount(maysiAssessmentList.size());
			regform.setMaysis(maysiAssessmentList);
			regform.setMsg("");
			return mapping.findForward("success");
		} else{
			regform.setMaysiCount(0);
			regform.setMaysis(null);
			regform.setMsg("There were no Maysi Assessments records returned for this query.");
			return mapping.findForward("error");
		}
		
	}	
}
