package ui.juvenilecase.prodsupport.action.update;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.productionsupport.UpdateProductionSupportDrugTestingInfoEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;

public class PerformUpdateDrugTestingAction extends Action
{
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception{
   	ProdSupportForm regform = (ProdSupportForm) aForm;
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   	UpdateProductionSupportDrugTestingInfoEvent updateEvent = (UpdateProductionSupportDrugTestingInfoEvent)EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTDRUGTESTINGINFO);
   									new UpdateProductionSupportDrugTestingInfoEvent();
   	updateEvent.setDrugTestingId( regform.getOriginalDrugTestingInfo().getDrugTestingId() );
   	updateEvent.setCasefileId( regform.getOriginalDrugTestingInfo().getAssociateCasefile() );
   	updateEvent.setMergeToCasefileId( regform.getNewcasefileId() );
   	updateEvent.setTestDate(regform.getTestDate());
   	updateEvent.setTestTime(regform.getTestTime());
   	dispatch.postEvent(updateEvent);
   	String forward = "success";
   	return aMapping.findForward(forward);
    }

}
