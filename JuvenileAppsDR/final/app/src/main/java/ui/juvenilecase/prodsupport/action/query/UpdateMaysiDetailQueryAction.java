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
 *         Takes in JUVENILE_ID and REFERRALNUM and displays the record
 */

public class UpdateMaysiDetailQueryAction extends Action {

	private Logger log = Logger.getLogger("UpdateMaysiDetailQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		

		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
			regform.setReferralNum("");
			regform.clearAllResults();
			regform.setNewReferralNum("");
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
	
		/** Redirect if this comes from updateMaysiDetailQuery2.jsp **/
		String editFlag = request.getParameter("edit");
		if (editFlag != null && editFlag.equalsIgnoreCase("Y")) 
		{
			String detailId = regform.getMaysidetailId();

			if (detailId != null && detailId.equals("") == false) 
			{
				regform.clearAllResults();
				// retrieve unique detail record
				ArrayList maysidetails = retrieveSingleDetail(detailId, SecurityUIHelper.getLogonId());
				regform.setMaysidetails(maysidetails);

				if (maysidetails == null)
					return mapping.findForward("error");
				else
					return mapping.findForward("continue");
			} else {
				regform.setMsg("DetailId didn't pass through to the Action. UpdateMaysiDetailQueryAction.java");
				return mapping.findForward("error");
			}
		}

		/** Normal query logic continues here **/
		regform.clearAllResults();

		regform.setReferralNum(referralnum);
		regform.setFromJuvenileId(juvenileId);

		// retrieve multiple detail records
		ArrayList maysidetails = retrieveMaysiDetails(juvenileId, referralnum, SecurityUIHelper.getLogonId());

		if (maysidetails != null) {
			regform.setMaysidetailCount(maysidetails.size());
			regform.setMaysidetails(maysidetails);
			regform.setMsg("");
			return mapping.findForward("success");
		} else {
			regform.setMsg("No MAYSI Detail records found for JuvenileID "
					+ juvenileId + " and Referral " + referralnum + ".");
			regform.setMaysidetailCount(0);
			regform.setMaysidetails(null);
			return mapping.findForward("error");
		}

	}
	
	/**
	 * 
	 * @param juvenileId
	 * @param referralnum
	 * @param logonid
	 * @return
	 */
	private ArrayList retrieveMaysiDetails(String juvenileId, String referralnum, String logonId){

		/**
		 * Search for JCMAYSIDETAIL.
		 */
		log.info("MAYSI Detail Query - JuvID: " + juvenileId+ " / ReferralNum: " + referralnum + " LogonId: " + logonId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetProductionSupportMaysiDetailEvent getMaysiDetailEvent = (GetProductionSupportMaysiDetailEvent)
				EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTMAYSIDETAIL);
		getMaysiDetailEvent.setJuvenileId(juvenileId);
		getMaysiDetailEvent.setReferralNumber(referralnum);
		getMaysiDetailEvent.setMaysiDetailId(null);

		dispatch.postEvent(getMaysiDetailEvent);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		Collection<MAYSIDetailsResponseEvent> maysiEvents =
			MessageUtil.compositeToCollection(compositeResponse, MAYSIDetailsResponseEvent.class);
		ArrayList<MAYSIDetailsResponseEvent> maysiDetailsList =  new ArrayList<MAYSIDetailsResponseEvent>();
		maysiDetailsList.addAll(maysiEvents);
		
		return maysiDetailsList;	
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

		return maysiDetailsList;
		
	}
	
}
