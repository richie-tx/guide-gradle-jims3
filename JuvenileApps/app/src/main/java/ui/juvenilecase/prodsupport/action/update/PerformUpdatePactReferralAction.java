package ui.juvenilecase.prodsupport.action.update;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import messaging.juvenilecase.SaveProductionSupportJuvenileRiskNeedLevelEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import ui.juvenilecase.form.ProdSupportForm;

/**
 * @author jims2
 */

public class PerformUpdatePactReferralAction extends Action
{

    private Logger log = Logger.getLogger("PerformUpdatePactReferral");

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

	ProdSupportForm regform = (ProdSupportForm) form;
	String referralNum = regform.getPactRecord().getReferralNumber().trim();
	String casefileId = regform.getPactRecord().getCaseFileId().trim();
	String juvNum = regform.getPactRecord().getJuvenileNumber().trim();
	String riskneedID = regform.getRiskneedID();
	boolean isChange = false;

	SaveProductionSupportJuvenileRiskNeedLevelEvent updateEvent = (SaveProductionSupportJuvenileRiskNeedLevelEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SAVEPRODUCTIONSUPPORTJUVENILERISKNEEDLEVEL);
	//updateEvent.setCaseFileId(casefileId);
	updateEvent.setJuvenileNum(juvNum);
	updateEvent.setRiskNeedLvlId(riskneedID);
	//updateEvent.setReferralNumber(referralNum);
	String pactVal = regform.getPactDate().trim();
	String riskVal = regform.getRiskValue().trim();
	String needVal = regform.getNeedValue().trim();
	String statusValue = regform.getPactStatus().trim();
	String pactOID = "";
	if (regform.getPactId() != null)
	{
	    pactOID = regform.getPactId().trim();
	}
	String newRef = regform.getNewReferralID().trim();
	String newCase = regform.getNewCaseID().trim();
	String newStatus = regform.getNewPactStatus().trim();
	String newPactVal = regform.getNewPactDate().trim();
	String newRiskVal = regform.getRiskLevel().trim();
	String newNeedVal = regform.getNeedLevel().trim();
	String newPactOID = regform.getNewPactId().trim();

	//Referral update
	boolean isRefChanged = checkIfTwoValuesChanged(newRef, referralNum);
	if (isRefChanged)
	{
	    updateEvent.setReferralNumber(newRef);
	    isChange = true;
	}
	else
	    if (referralNum != null)
	    {
		updateEvent.setReferralNumber(referralNum);
		regform.setNewReferralID(null);
	    }

	//CasefileID update
	boolean isCaseIDChanged = checkIfTwoValuesChanged(newCase, casefileId);
	if (isCaseIDChanged)
	{
	    updateEvent.setCaseFileId(newCase);
	    isChange = true;
	}
	else
	    if (casefileId != null)
	    {
		updateEvent.setCaseFileId(casefileId);
		regform.setNewCaseID(null);
	    }

	//Pact and RiskNeed update
	boolean isPactDateChanged = checkIfTwoValuesChanged(newPactVal, pactVal);
	if (isPactDateChanged)
	{
	    updateEvent.setLastPactDate(DateUtil.stringToDate(newPactVal, DateUtil.DATE_FMT_1));
	    isChange = true;
	}
	else
	    if (pactVal != null)
	    {
		updateEvent.setLastPactDate(DateUtil.stringToDate(pactVal, DateUtil.DATE_FMT_1));
		regform.setNewPactDate(null);
	    }

	boolean isRiskValChanged = checkIfTwoValuesChanged(newRiskVal, riskVal);
	if (isRiskValChanged)
	{
	    updateEvent.setRiskLvl(newRiskVal);
	    isChange = true;
	}
	else
	    if (riskVal != null)
	    {
		updateEvent.setRiskLvl(riskVal);
		regform.setRiskLevel(null);
	    }
	boolean isNeedValChanged = checkIfTwoValuesChanged(newNeedVal, needVal);
	if (isNeedValChanged)
	{
	    updateEvent.setNeedsLvl(newNeedVal);
	    isChange = true;
	}
	else
	    if (needVal != null)
	    {

		updateEvent.setNeedsLvl(needVal);
		regform.setNeedLevel(null);
	    }
	boolean isStatusChanged = checkIfTwoValuesChanged(newStatus, statusValue);
	if (isStatusChanged)
	{
	    updateEvent.setStatus(newStatus);
	    isChange = true;
	}
	else
	    if (statusValue != null)
	    {
		updateEvent.setStatus(statusValue);
		regform.setNewPactStatus(null);
	    }
	boolean isPactOIDChanged = checkTwoValuesNotEqual(newPactOID, pactOID);
	if (isPactOIDChanged)
	{
	    if (StringUtils.isEmpty(newPactOID))
	    {
		newPactOID = "0";
	    }
	    updateEvent.setPactId(newPactOID);
	    regform.setPactOIDChanged(true);
	    isChange = true;
	}
	else
	{
	    updateEvent.setPactId(pactOID);
	    regform.setNewPactId(null);
	}

	// check that a value changed before updating
	if (!isChange)
	{
	    regform.setMsg("At least one value needs to be changed.");
	    return (mapping.findForward("error"));
	}

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(updateEvent);
	CompositeResponse composite = (CompositeResponse) dispatch.getReply();

	MessageUtil.processReturnException(composite);
	regform.setMsg("");
	return mapping.findForward("success");
    }

    /**
     * ( compare two string values and determine if they are equal
     * 
     * @param value
     * @param compareValue
     * @return
     */
    private boolean checkIfTwoValuesChanged(String newValue, String OlderValue)
    {
	boolean result = false;

	if (newValue != null && OlderValue != null && !newValue.equals(""))
	{
	    if (!newValue.equals(OlderValue))
	    {
		result = true;
	    }
	}
	else
	    if (newValue != null && OlderValue == null)
	    {
		result = true;
	    }
	    else
		if (StringUtils.isNotBlank(newValue) && StringUtils.isBlank(OlderValue))
		{
		    result = true;
		}

	return result;
    }

    private boolean checkTwoValuesNotEqual(String newValue, String OlderValue)
    {
	boolean result = false;
	if ( !newValue.equals( OlderValue ))
	{
	    result = true;
	}	

	return result;
    }

}
