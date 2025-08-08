package ui.juvenilecase.prodsupport.action.update;

import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.programreferral.reply.ProgramReferralAssignmentHistoryResponseEvent;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

public class PerformReferralSealAction extends Action
{

    private Logger log = Logger.getLogger("PerformReferralSealAction");

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

	ProdSupportForm regform = (ProdSupportForm) form;
	ActionErrors errors = new ActionErrors();

	String juvenileId = regform.getFromJuvenileId();
	String referralNum = regform.getReferralNum();

	if (juvenileId == null || juvenileId.equals(""))
	{
	    regform.setMsg("PerformReferralSealAction.java (63) - JuvenileID was null.");
	    return (mapping.findForward("error"));
	}

	log.info("BEGIN JUVENILE SEAL - " + " LogonId: " + SecurityUIHelper.getLogonId());

	/**
	 * First, log all the IDs of the dependent files that will be deleted
	 * automatically via RI
	 **/
	writeLogEntries(regform, SecurityUIHelper.getLogonId(), juvenileId);

	/** Update Juvenile to S.JUVENILE record (and children by default) **/
	/*UpdateProductionSupportReferralSealEvent updateRefSealEvent = (UpdateProductionSupportReferralSealEvent) 
		EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTREFERRALSEAL) ;		
	
	log.info(" SEALED USING JUVENILE NUM: " + juvenileId + "Logon Id: " + 
			SecurityUIHelper.getLogonId());
	updateRefSealEvent.setJuvenileId(juvenileId);
	updateRefSealEvent.setReferralNum(referralNum);
	//CompositeResponse event =  MessageUtil.postRequest(updateRefSealEvent);
	
		
	/** Log for auditing purposes **/
	log.info("SCENARIO REFERRAL SEAL - Performed a REFERRAL SEAL for juvenileID=" + regform.getFromJuvenileId() + "ReferralNum " + regform.getReferralNum() + "LogonId: " + SecurityUIHelper.getLogonId());

	regform.setMsg("");
	return mapping.findForward("success");

    }

    /**
     * log all the records that are going to be deleted with casefile
     * 
     * @param regform
     * @param logonid
     */
    private void writeLogEntries(ProdSupportForm regform, String logonid, String casefileId)
    {

	if (regform.getProgrfasgnhists() != null)
	{
	    Iterator iter = regform.getProgrfasgnhists().iterator();
	    while (iter.hasNext())
	    {
		ProgramReferralAssignmentHistoryResponseEvent programReferralAssignmentHistoryResponse = (ProgramReferralAssignmentHistoryResponseEvent) iter.next();
		log.info(" DELETE - CSPROGRFASGNHIST ID: " + programReferralAssignmentHistoryResponse.getProgramReferralAssignmentHistoryId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

    }
}
