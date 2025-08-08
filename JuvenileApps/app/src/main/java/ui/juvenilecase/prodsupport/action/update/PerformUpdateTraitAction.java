package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetJuvenileTraitTypesEvent;
import messaging.juvenilecase.reply.TraitTypeResponseEvent;
import messaging.productionsupport.UpdateProductionSupportDrugTestingInfoEvent;
import messaging.productionsupport.UpdateProductionSupportTraitEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

public class PerformUpdateTraitAction extends Action
{
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {
	ProdSupportForm regform = (ProdSupportForm) aForm;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	if (regform.getToJuvenileId() != null
		&& regform.getOriginalTrait().getJuvenileNum() != null
		&& !regform.getToJuvenileId().equals("")
		&& !regform.getJuvenileId().equals(regform.getOriginalTrait().getJuvenileNum()))
	{
	    GetJuvenileProfileMainEvent getJuvProfileEvent = new GetJuvenileProfileMainEvent();
	    getJuvProfileEvent.setJuvenileNum(regform.getToJuvenileId());
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
	}

	String traitTypeDesc = "";

	if (regform.getTraitTypeId() != null
		&& regform.getOriginalTrait().getTraitTypeId() != null
		&& !regform.getTraitTypeId().equals("")
		&& !regform.getTraitTypeId().equals(regform.getOriginalTrait().getTraitTypeId()))
	{

	    regform.setTraitTypeId(regform.getTraitTypeId().toUpperCase());
	    GetJuvenileTraitTypesEvent event = (GetJuvenileTraitTypesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITTYPES);
	    event.setTraitType(null);
	    dispatch.postEvent(event);
	    IEvent replyEvent = dispatch.getReply();
	    CompositeResponse responses = (CompositeResponse) replyEvent;

	    if (responses != null && responses.hasResponses())
	    {
		boolean isMatchFound = false;

		for (TraitTypeResponseEvent responseEvent : (ArrayList<TraitTypeResponseEvent>) responses.getResponses())
		{
		    if (responseEvent.getTraitTypeId() != null
			    && responseEvent.getTraitTypeId().equals(regform.getTraitTypeId()))
		    {
			isMatchFound = true;
			traitTypeDesc = responseEvent.getTraitName();
			break;
		    }
		}

		if (!isMatchFound)
		{
		    regform.setMsg("Update Failed! Invalid Trait Type Id, Must be Active Trait Type From JCTRAITTYPE.");
		    return aMapping.findForward("error");
		}
		else
		{
		    regform.setMsg("");
		}
	    }

	}

	String statusDesc = "";
	if (regform.getStatusId() != null
		&& regform.getOriginalTrait().getStatusId() != null
		&& !regform.getStatusId().equals("")
		&& !regform.getStatusId().equals(regform.getOriginalTrait().getStatusId()))
	{
	    regform.setStatusId(regform.getStatusId().toUpperCase());
	    List<CodeResponseEvent> traitStatusList = CodeHelper.getCodes(PDCodeTableConstants.FAMILY_TRAIT_STATUS, true);

	    boolean isMatchFound = false;

	    for (CodeResponseEvent responseEvent : traitStatusList)
	    {
		if (responseEvent.getCode() != null
			&& responseEvent.getCode().equals(regform.getStatusId()))
		{
		    isMatchFound = true;
		    statusDesc = responseEvent.getDescription();
		    break;
		}
	    }

	    if (!isMatchFound)
	    {
		regform.setMsg("Update Failed! Invalid Trait Status, Must be Active Trait Status From FAMILY_TRAIT_STATUS Code Table.");
		return aMapping.findForward("error");
	    }
	    else
	    {
		regform.setMsg("");
	    }

	}

	UpdateProductionSupportTraitEvent updateTraitEvent = (UpdateProductionSupportTraitEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTTRAIT);

	updateTraitEvent.setJuvenileTraitId(regform.getOriginalTrait().getJuvenileTraitId());
	updateTraitEvent.setJuvenileNum(regform.getToJuvenileId());
	updateTraitEvent.setSupervisionNum(regform.getCurrentSupervisionNum());
	updateTraitEvent.setTraitTypeId(regform.getTraitTypeId());
	updateTraitEvent.setTraitStatus(regform.getStatusId());
	dispatch.postEvent(updateTraitEvent);

	if (traitTypeDesc != null && !traitTypeDesc.equals(""))
	{
	    regform.getOriginalTrait().setTraitTypeDescription(traitTypeDesc);
	}

	if (statusDesc != null && !statusDesc.equals(""))
	{
	    regform.getOriginalTrait().setStatus(statusDesc);
	}
	
	if(statusDesc.equals("") && regform.getStatusId().equals(""))
	{
	    regform.getOriginalTrait().setStatus("");
	}

	regform.setMsg("");
	String forward = "success";
	return aMapping.findForward(forward);
    }

}
