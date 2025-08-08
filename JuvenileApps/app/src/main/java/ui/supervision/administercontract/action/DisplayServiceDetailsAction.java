//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercontract\\HandleContractSelectionAction.java

package ui.supervision.administercontract.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.GetServiceByServiceIdEvent;
import messaging.administerserviceprovider.GetServiceProviderServiceByServiceIdEvent;
import messaging.administerserviceprovider.reply.ServiceProviderServiceResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.administercontract.form.ContractForm;


/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class DisplayServiceDetailsAction extends Action
{
   
   /**
    * @roseuid 451C4F0F0086
    */
   public DisplayServiceDetailsAction() 
   {
    
   }
    
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 451C0A650268
    */
   public ActionForward execute(
   		ActionMapping aMapping, 
		ActionForm aForm, 
		HttpServletRequest aRequest, 
		HttpServletResponse aResponse) 
   {
   	   ContractForm contractForm = (ContractForm) aForm;
   	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   	   GetServiceProviderServiceByServiceIdEvent reqEvent = (GetServiceProviderServiceByServiceIdEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDERSERVICEBYSERVICEID);
		reqEvent.setServiceId(Integer.parseInt(contractForm.getServiceId()));
		dispatch.postEvent(reqEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		Collection serviceProviderServices = MessageUtil.compositeToCollection(compositeResponse, ServiceProviderServiceResponseEvent.class);
		if(serviceProviderServices != null)
		{
			contractForm.setCurrentSPServices(serviceProviderServices);
		}
	   return aMapping.findForward(UIConstants.SUCCESS);
   }
}
