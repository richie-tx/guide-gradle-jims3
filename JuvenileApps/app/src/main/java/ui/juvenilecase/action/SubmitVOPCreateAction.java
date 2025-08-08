//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\referral\\action\\HandleJuvenileProfileTransferredOffensesSelectionAction.java

package ui.juvenilecase.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.referral.SaveVOPDetailsEvent;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.form.VOPOffenseForm;

/**
 * This action is used in profile and casefile transfer offense referral create
 * pages as both pages perform the same functionality but different tabs
 */
public class SubmitVOPCreateAction extends JIMSBaseAction
{
    /* (non-Javadoc)
    * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
    */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.finish", "finish");
	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "cancel");
    }
    
    /**
     * @roseuid 467FB5C80014
     */
    public SubmitVOPCreateAction()
    {

    }


    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	VOPOffenseForm form = (VOPOffenseForm) aForm;
	SaveVOPDetailsEvent saveEvent = new SaveVOPDetailsEvent();
	saveEvent.setJuvenileNum(form.getJuvenileNum());
	saveEvent.setReferralNum(form.getReferralNum());
	saveEvent.setReferralDate(form.getReferralDate());
	saveEvent.setVopOffenseCode(form.getVopOffenseCode());
	saveEvent.setOffenseCharge(form.getOffenseCharge());
	Date xDate = DateUtil.stringToDate(form.getOffenseChargeDate(), DateUtil.DATE_FMT_1);
	saveEvent.setOffenseChargeDate(xDate);
	saveEvent.setInCCountyOrigPetitionedRefNum(form.getInCCountyOrigPetitionedRefNum());
	
 	if (form.getAdultIndicatorStr() != null && form.getAdultIndicatorStr().equalsIgnoreCase("Yes"))
	{
 	   saveEvent.setAdultIndicatorStr("1");
	}
	else
	{
	    if (form.getAdultIndicatorStr() != null && form.getAdultIndicatorStr().equalsIgnoreCase("No"))
	    {
		saveEvent.setAdultIndicatorStr("0");
	    }
	    else{
		saveEvent.setAdultIndicatorStr(null);
	    }
		
	} 	    
	saveEvent.setLocationIndicator(form.getLocationIndicator());
	//saveEvent.setCasefileId(form.getCasefileId());
	saveEvent.setCasefileIds(form.getCasefileIds());
	MessageUtil.postRequest(saveEvent);
	
	form.setConfirmMsg("The Violation of Probation changes have been completed.");
	return aMapping.findForward("success");
    }

    
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	VOPOffenseForm form = (VOPOffenseForm) aForm;
	//form.clear();
	return aMapping.findForward(UIConstants.BACK);

    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	VOPOffenseForm form = (VOPOffenseForm) aForm;
	//form.clear();
	return aMapping.findForward(UIConstants.CANCEL);
    }
}
