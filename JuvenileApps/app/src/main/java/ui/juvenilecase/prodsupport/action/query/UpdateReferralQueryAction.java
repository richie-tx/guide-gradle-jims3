package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;
import java.lang.Integer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralsEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDAdministerServiceProviderConstants;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.supervision.administerserviceprovider.ProviderProgram;

import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;


/**
 * @author rcarter
 */

public class UpdateReferralQueryAction extends Action {

	private Logger log = Logger.getLogger("UpdateReferralQueryAction");
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
		
		//Log the query attempt
		log.info("["+new Date()+"] ProdSupport ("+SecurityUIHelper.getLogonId().toUpperCase()+") - Referral Query ID: "+referralNum);
		
		ArrayList juvprogrefs = getReferralsById(referralNum);
		//US 176693 changes start
		ProductionSupportJuvenileProgramReferralResponseEvent refRespEvent = (ProductionSupportJuvenileProgramReferralResponseEvent) juvprogrefs.get(0);
		String providerProgramId = refRespEvent.getProviderProgramId();
		// CSPROVPROGRAM for providerPgmId and get the JUVSERVPROV_ID
		ProviderProgram program = ProviderProgram.find(providerProgramId);
		String juvServProvID = program.getJuvenileServiceProviderId();
		//get all the programNames from CSPROVPROGRAM where JUVSERVPROV_ID = program.getJuvenileServiceProviderId();
		Iterator iter = ProviderProgram.findAll(PDAdministerServiceProviderConstants.JUV_SERVICE_PROVIDER_ID, juvServProvID);
		ArrayList<ProviderProgram> listProgramNames = new ArrayList<ProviderProgram>();
		while(iter.hasNext()){
			ProviderProgram providerProgram = (ProviderProgram)iter.next();
			if(program.getStatusId() != PDAdministerServiceProviderConstants.INACTIVE) {
			    listProgramNames.add(providerProgram);
			}
		}
		regform.setProvProgramsList(listProgramNames);
		//US 176693 changes ENDS
		if (juvprogrefs!=null){
			regform.setJuvprogrefCount(juvprogrefs.size());
		
			regform.setJuvprogrefs(juvprogrefs);
		}
		else{
			regform.setMsg("No referral records found for referralID " + referralNum);
			return mapping.findForward("error");
		}
		
		/** Populate Drop-downs **/
		ArrayList outcomeCodes = (ArrayList)SimpleCodeTableHelper.getCodesSortedByCodeId("PROGRAM_REFERRAL_OUTCOME");
		ArrayList statusCodes = (ArrayList)SimpleCodeTableHelper.getCodesSortedByCodeId("PROGRAM_REFERRAL_STATUS");
		ArrayList substatusCodes = (ArrayList)SimpleCodeTableHelper.getCodesSortedByCodeId("PROGRAM_REFERRAL_SUBSTATUS");	
		ArrayList subcatCodes = (ArrayList)ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_OUTCOME_REF_DESC");
		
		if (outcomeCodes!=null&&statusCodes!=null&&substatusCodes!=null&&subcatCodes!=null){
			regform.setOutcomeCodes(outcomeCodes);
			regform.setStatusCodes(statusCodes);
			regform.setSubstatusCodes(substatusCodes);
			regform.setOutcomeDescCodes(subcatCodes);
		}
		else{
			regform.setMsg("Error - Couldn't retrieve codetables. UpdateReferralQueryAction");
			return mapping.findForward("error");
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
	
}
