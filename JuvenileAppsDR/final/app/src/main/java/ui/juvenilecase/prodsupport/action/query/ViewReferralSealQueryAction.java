package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileReferralControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author ryoung
 * 
 *         Takes in JUVENILE_ID and REFERRALNUM and displays the record
 */

public class ViewReferralSealQueryAction extends Action {
	private Logger log = Logger.getLogger("ViewReferralSealQueryAction");
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

		regform.clearAllResults();

		regform.setReferralNum(referralnum);
		regform.setFromJuvenileId(juvenileId);

		// Log the query attempt
		log.info("REFERRAL Query - JuvID: " + juvenileId + " / ReferralNum: " + referralnum + " LogonId: " + SecurityUIHelper.getLogonId());

		ArrayList referralData = retrieveReferralDetails(juvenileId, referralnum);

		if (referralData != null && referralData.size() > 0) {
			regform.setEventreferralCount(referralData.size());
			regform.setEventreferrals(referralData);
		} else {
			regform.setMsg("No REFERRAL Detail records found for JuvenileID "
					+ juvenileId + " and Referral " + referralnum + ".");
			return mapping.findForward("error");
		}

		regform.setMsg("");
		return mapping.findForward("success");

	}
	
	private ArrayList retrieveReferralDetails(String juvenileId, String referralnum){

		/**
		 * Search for JCMAYSIDETAIL.
		 */
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetJJSReferralEvent referralEvent = (GetJJSReferralEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJJSREFERRAL);
		  referralEvent.setJuvenileNum(juvenileId);
		  referralEvent.setReferralNum(referralnum);

		dispatch.postEvent( referralEvent );		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		Collection<JuvenileProfileReferralListResponseEvent> respEvent =
				MessageUtil.compositeToCollection(compositeResponse, JuvenileProfileReferralListResponseEvent.class);

		ArrayList<JuvenileProfileReferralListResponseEvent> respEventsList =  new ArrayList<JuvenileProfileReferralListResponseEvent>();
		if (respEvent!=null && respEvent.size() > 0){
		    respEventsList.addAll(respEvent);
		}
		return respEventsList;	
	}
}
