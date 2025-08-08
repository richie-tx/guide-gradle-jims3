package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralsEvent;
import messaging.productionsupport.ListProductionSupportJuvenileReferralsEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileReferralResponseEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
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
 */

public class UpdateReferralDateQueryAction extends Action {

	private Logger log = Logger.getLogger("UpdateReferralDateQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ProdSupportForm regform = (ProdSupportForm) form;
		
		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk!=null && clrChk.equalsIgnoreCase("Y"))
		{
			regform.clearAllResults();
			regform.setMsg("");
			regform.setNewcasefileId("");
			return mapping.findForward("error");		
		}
		
		String referralNum = regform.getReferralNum();

		if (referralNum == null || referralNum.equals("")) {
			regform.setMsg("Please enter a Referral Number.");
			return (mapping.findForward("error"));
		}
		
		try {
		    Integer.parseInt(referralNum);
		} catch (Exception e ){
		    regform.setMsg("Referral Number is invalid. Please input a valid Referral Number.");
			return (mapping.findForward("error"));
		}
		
		// Clearing Calendar Default values
		regform.clearSpecialProcessingResults();
		//Log the query attempt
		log.info("Referral Date Query ID: " + referralNum + " LogonId: " + SecurityUIHelper.getLogonId());
		
		/**
		 * Search for juvprogrefs
		 */
		ProductionSupportJuvenileProgramReferralResponseEvent referralResponse = getReferralResponseById(referralNum);
		
		if(referralResponse!=null)
		{
		regform.setRefBeginDate(DateUtil.dateToString(referralResponse.getBeginDate(), DateUtil.DATE_FMT_1));
		regform.setRefEndDate(DateUtil.dateToString(referralResponse.getEndDate(), DateUtil.DATE_FMT_1));
		regform.setRefAckDate(DateUtil.dateToString(referralResponse.getAckDate(), DateUtil.DATE_FMT_1));
		regform.setRefSentDate(DateUtil.dateToString(referralResponse.getSentDate(), DateUtil.DATE_FMT_1));
		regform.setNewControllingReferral(referralResponse.getControllingReferralNum());
		regform.setNewcasefileId(referralResponse.getCaseFileId());
		regform.setProgramReferralAssignmentDate(DateUtil.dateToString(referralResponse.getProgramReferralAssignmentDate(), DateUtil.DATE_FMT_1));
		regform.setOriginalProgramReferralAssignmentDate(DateUtil.dateToString(referralResponse.getProgramReferralAssignmentDate(), DateUtil.DATE_FMT_1));
		regform.setProgramReferralAssignmentId(referralResponse.getProgramReferralAssignmentId());
		//add fund source for US 180996
		regform.setNewFundSource(referralResponse.getFundSource());
		}
		else{
		regform.setRefBeginDate(null);
		regform.setRefEndDate(null);
		regform.setRefAckDate(null);
		regform.setRefSentDate(null);
		regform.setNewControllingReferral(null);
		regform.setProgramReferralAssignmentDate(null);
		}
		
		ArrayList juvprogrefs = getReferralsById(referralNum);
		if (juvprogrefs!=null
			&& juvprogrefs.size() > 0){
			regform.setJuvprogrefCount(juvprogrefs.size());
		
			regform.setJuvprogrefs(juvprogrefs);
		}
		else{
			regform.setMsg("No referral records found for referralID " + referralNum);
			return mapping.findForward("error");
		}
		
		ArrayList<ProductionSupportJuvenileReferralResponseEvent> controllingReferrals = getControllingReferralList(referralNum);
		if ( controllingReferrals != null 
				&& controllingReferrals.size() > 0) {
		    regform.setControllingReferrals(controllingReferrals);
		}
		
		
		
		regform.setMsg("");
		return mapping.findForward("success");

	}	
	
	/**
	 * 
	 * @param referralNum
	 * @return
	 */
	public static ArrayList getReferralsById(String referralNum){
		ArrayList juvprogrefs = null;
		
		// Get and set Associated JuvProgRefs
		GetProductionSupportJuvenileProgramReferralsEvent getJuvenileProgramRerralsEvent = (GetProductionSupportJuvenileProgramReferralsEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRALS );
		getJuvenileProgramRerralsEvent.setReferralNum(referralNum );
		CompositeResponse juvenileProgramReferralsResp = MessageUtil.postRequest(getJuvenileProgramRerralsEvent);
		juvprogrefs = (ArrayList) MessageUtil.compositeToCollection(juvenileProgramReferralsResp, ProductionSupportJuvenileProgramReferralResponseEvent.class);
		return juvprogrefs;
	}

	public static ProductionSupportJuvenileProgramReferralResponseEvent getReferralResponseById(String referralNum){
		ArrayList<ProductionSupportJuvenileProgramReferralResponseEvent> juvprogrefs = null;
		ProductionSupportJuvenileProgramReferralResponseEvent responseEvent = null;
		GetProductionSupportJuvenileProgramReferralsEvent getJuvenileProgramRerralsEvent = (GetProductionSupportJuvenileProgramReferralsEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRALS );
		getJuvenileProgramRerralsEvent.setReferralNum(referralNum );
		CompositeResponse juvenileProgramReferralsResp = MessageUtil.postRequest(getJuvenileProgramRerralsEvent);
		juvprogrefs = (ArrayList) MessageUtil.compositeToCollection(juvenileProgramReferralsResp, ProductionSupportJuvenileProgramReferralResponseEvent.class);
		
		for(ProductionSupportJuvenileProgramReferralResponseEvent event: juvprogrefs){
			responseEvent = (ProductionSupportJuvenileProgramReferralResponseEvent)event;
			break;
		}
		
		return responseEvent;
	}
	
	private ArrayList getControllingReferralList(String juvprogrefId)
	{
		ArrayList juvControllingReferrals = null;
		ProductionSupportJuvenileProgramReferralResponseEvent juvprogref = new ProductionSupportJuvenileProgramReferralResponseEvent();
		GetProductionSupportJuvenileProgramReferralsEvent getJuvenileProgramRerralsEvent = (GetProductionSupportJuvenileProgramReferralsEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRALS );
		getJuvenileProgramRerralsEvent.setReferralNum(juvprogrefId);
		getJuvenileProgramRerralsEvent.setIsFromCSJUVPROGREF(true);
		juvprogref = (ProductionSupportJuvenileProgramReferralResponseEvent)  MessageUtil.postRequest(getJuvenileProgramRerralsEvent, ProductionSupportJuvenileProgramReferralResponseEvent.class );
		
		if (juvprogref != null ) {
        		String juvenileId = juvprogref.getJuvenileNum();
        		if ( juvenileId != null 
        			&& juvenileId.length() > 0 ) {
                		// Get and set Associated JuvProgRefs
                		ListProductionSupportJuvenileReferralsEvent getJuvenileRerralsEvent = (ListProductionSupportJuvenileReferralsEvent) 
                							    EventFactory.getInstance(ProductionSupportControllerServiceNames.LISTPRODUCTIONSUPPORTJUVENILEREFERRALS);
                		getJuvenileRerralsEvent.setJuvenileId(juvenileId);
                		CompositeResponse juvenileReferralsResp = MessageUtil.postRequest(getJuvenileRerralsEvent);
                		juvControllingReferrals = (ArrayList) MessageUtil.compositeToCollection(juvenileReferralsResp, ProductionSupportJuvenileReferralResponseEvent.class);
                		if (juvControllingReferrals != null
                			&& juvControllingReferrals.size() > 1){
                		    Collections.sort(juvControllingReferrals,  new Comparator<ProductionSupportJuvenileReferralResponseEvent>(){
                			public int compare(ProductionSupportJuvenileReferralResponseEvent referral1, ProductionSupportJuvenileReferralResponseEvent referral2){
                			    return referral1.getReferralNum().compareTo(referral2.getReferralNum());
                			}
                		    });
                		}
        		}
		}

		return juvControllingReferrals;
	}
}
