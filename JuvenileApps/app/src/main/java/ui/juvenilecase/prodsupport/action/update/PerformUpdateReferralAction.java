package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.productionsupport.UpdateProductionSupportJuvenileProgramReferralEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
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

public class PerformUpdateReferralAction extends Action {
	private Logger log = Logger.getLogger("PerformUpdateReferralAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;

		String logonid = SecurityUIHelper.getLogonId();
		
		String referralNum = regform.getReferralNum();

		if (referralNum == null || referralNum.equals("")) {
			regform.setMsg("PerformUpdateReferralAction.java (62) - Referral Number was null.");
			return (mapping.findForward("error"));
		}
		
		String newStatuscd = regform.getStatusBox();
		String newSubstatuscd = regform.getSubstatusBox();
		String newOutcomecd = regform.getOutcomeBox();
		String newOutcomeDesccd = regform.getOutcomeDescBox();
		String newproviderPgmCd = regform.getProviderPgmDescBox(); //providerProgramBox //TO DO
		
		ProductionSupportJuvenileProgramReferralResponseEvent record = retrieveRecord(regform);
				
		if (record==null){
			regform.setMsg("PerformUpdateReferralAction.java (73) - Could not retrieve record.");
			return (mapping.findForward("error"));
		}
		
		// if all are null throw an error.. at least one must be chosen
		if(newOutcomecd == null && newStatuscd == null && newSubstatuscd == null && newOutcomeDesccd == null && newproviderPgmCd == null){
				regform.setMsg("No new codes selected.");
				return mapping.findForward("error");
		}
		
		// prepare event
		UpdateProductionSupportJuvenileProgramReferralEvent updateProgramReferral = 
				(UpdateProductionSupportJuvenileProgramReferralEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRAL) ;
			// set the referral number
				updateProgramReferral.setReferralNum(referralNum);
			// set values to update... for three of these "999" means empty value
			if(newStatuscd  != null && !newStatuscd.equals("")){
				updateProgramReferral.setStatusCd(newStatuscd);
			}
			if(newSubstatuscd != null && newSubstatuscd.equals("999")){
				updateProgramReferral.setSubStatusCd("");
			}else if(newSubstatuscd != null && !newSubstatuscd.equals("")){
				updateProgramReferral.setSubStatusCd(newSubstatuscd);
			}
			if(newOutcomecd != null && newOutcomecd.equals("999")){
				updateProgramReferral.setOutcomeCd("");
			}else if(newOutcomecd != null && !newOutcomecd.equals("")){
				updateProgramReferral.setOutcomeCd(newOutcomecd);
			}
			if(newOutcomeDesccd != null && newOutcomeDesccd.equals("999")){
				updateProgramReferral.setOutcomeSubCd("");
			}else if(newOutcomeDesccd != null && !newOutcomeDesccd.equals("")){
				updateProgramReferral.setOutcomeSubCd(newOutcomeDesccd);
			}
			if(newproviderPgmCd  != null && !newproviderPgmCd.equals("")){
				updateProgramReferral.setProvProgramId(newproviderPgmCd);
			}
			
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("UPDATE JUVENILE REFERRAL CODES: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateProgramReferral);
			
			regform.setMsg("");
			return mapping.findForward("success");
	}
	
	/**
	 * Returns first record only
	 * @param regform
	 * @return
	 */
	private ProductionSupportJuvenileProgramReferralResponseEvent retrieveRecord(ProdSupportForm regform){
		
		ArrayList juvprogrefs = regform.getJuvprogrefs();
		ProductionSupportJuvenileProgramReferralResponseEvent record = null;
		
		Iterator iter = juvprogrefs.iterator();
		if (iter.hasNext())
		{
			record = (ProductionSupportJuvenileProgramReferralResponseEvent)iter.next();
		}
		
		return record;
	}

}
