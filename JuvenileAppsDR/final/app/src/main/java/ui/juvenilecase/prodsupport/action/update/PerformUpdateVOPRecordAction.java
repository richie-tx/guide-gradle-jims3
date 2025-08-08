package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetAllJuvenileOffenseCodesEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import messaging.productionsupport.UpdateVOPProdSupportDetailsEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.juvenilecase.form.VOPOffenseForm;
import ui.juvenilecase.referral.form.JuvenileReferralForm;

public class PerformUpdateVOPRecordAction extends JIMSBaseAction
{
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception{
	ProdSupportForm regform = (ProdSupportForm) aForm;
	String forward = "success";
	
	String clrChk = aRequest.getParameter("clr");
	
	if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
	    regform.setMsg("");
	    return aMapping.findForward("error");
	}
	// prepare event
	UpdateVOPProdSupportDetailsEvent updateVOPEvent = (UpdateVOPProdSupportDetailsEvent)EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEVOPPRODSUPPORTDETAILS);
	updateVOPEvent.setVopOID(regform.getJcVOP().getOID());
	updateVOPEvent.setAdultIndicatorStr(regform.getJcVOP().getAdultIndicatorStr());
	updateVOPEvent.setReferralNum(regform.getJcVOP().getReferralNumber());
	updateVOPEvent.setReferralDate(DateUtil.stringToDate(regform.getRefDateVOP(), DateUtil.DATE_FMT_1 ));
	updateVOPEvent.setVopOffenseCode(regform.getJcVOP().getVOPOffenseCode());
	updateVOPEvent.setOffenseChargeDate(DateUtil.stringToDate(regform.getOffenseChargeDateVOP(), DateUtil.DATE_FMT_1 ));
	updateVOPEvent.setInCCountyOrigPetitionedRefNum(regform.getJcVOP().getInCCountyOrigPetitionedRefNum());
	updateVOPEvent.setAdultIndicator(regform.getJcVOP().getAdultIndicator());
	updateVOPEvent.setLocationIndicator(regform.getJcVOP().getLocationIndicator());
	
	if(regform.getJcVOP() != null && regform.getJcVOP().getJuvenileNumber() != null && regform.getJcVOP().getJuvenileNumber() != "" && regform.getJuvenileId() != regform.getJcVOP().getJuvenileNumber())
	{
	    GetJuvenileProfileMainEvent getJuvProfileEvent = new GetJuvenileProfileMainEvent();
	    getJuvProfileEvent.setJuvenileNum(regform.getJcVOP().getJuvenileNumber());
	    CompositeResponse response = MessageUtil.postRequest(getJuvProfileEvent);
	    JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
	    if (juvProfileRE == null)
	    {
		regform.setMsg("Update Failed! No juvenile record found for the specified juvenile number.");
		return aMapping.findForward("error");
	    }
	    else
	    {
		regform.setMsg("");
	    }
	    
	    
	    updateVOPEvent.setJuvenileNum(regform.getJcVOP().getJuvenileNumber());
	    regform.setJuvenileId(regform.getJcVOP().getJuvenileNumber());
	}
	else
	{	
	    updateVOPEvent.setJuvenileNum(regform.getJuvenileId());	
	}
	updateVOPEvent.setOffenseCharge(regform.getJcVOP().getOffenseCharge());
	
	MessageUtil.postRequest(updateVOPEvent);
	//regform.setMsg("Record successfully updated");
	return aMapping.findForward(forward);
    }
    
    /**
     * validateOffenseCode
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward validateOffenseCode(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;

	JuvenileOffenseCodeResponseEvent jocEvent = JuvenileReferralHelper.validateOffenseCd(form.getJcVOP().getOffenseCharge());
	if (jocEvent != null)
	{
	    String lit = jocEvent.getShortDescription() + " (" + jocEvent.getCategory() + ")";
	    ActionMessages messageHolder = new ActionMessages();
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.offenseCodeValid"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	}
	else
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Offense Code. Please Correct and Retry"));
	    saveErrors(aRequest, errors);
	    if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
		return aMapping.findForward("updateReferral");
	    else
		return aMapping.findForward(UIConstants.FAILURE);
	}
	String forward = "";
	if (form.getAction() != null && form.getAction().equalsIgnoreCase("updateReferral"))
	    forward = "updateReferral";
	else
	{
	    forward = UIConstants.SUCCESS;
	    form.setAction("validateOffenseCd");
	}
	return aMapping.findForward(forward);
    }
    
    
    public ActionForward goToOffenseSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	VOPOffenseForm form = (VOPOffenseForm) aForm;
	form.setConfirmMsg(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setAlphaCodeId(UIConstants.EMPTY_STRING);
	form.setShortDesc(UIConstants.EMPTY_STRING);
	form.setDpsCodeId(UIConstants.EMPTY_STRING);
	form.setCategoryId(UIConstants.EMPTY_STRING);
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setOffenseResultList(new ArrayList());
	
	GetAllJuvenileOffenseCodesEvent requestEvent = (GetAllJuvenileOffenseCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETALLJUVENILEOFFENSECODES);
	List codes = MessageUtil.postRequestListFilter(requestEvent, JuvenileCasefileOffenseCodeResponseEvent.class);

	Collections.sort(codes);

	form.setCodetableDataList(codes);

	return aMapping.findForward(UIConstants.OFFENSE_SUCCESS);
    }

    @Override
    protected void addButtonMapping(Map keyMap)
    {
	// TODO Auto-generated method stub
	keyMap.put("button.validateOffense", "validateOffenseCode");
	
    }
    
    
   
}
