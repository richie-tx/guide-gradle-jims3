//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\SubmitJuvCreateUpdateProviderProgramAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.CreateProviderProgramEvent;
import messaging.administerserviceprovider.GetProviderProgramsEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDAdministerServiceProviderConstants;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.supervision.administerserviceprovider.JuvenileServiceProvider;

import ui.common.ComplexCodeTableHelper;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;

public class SubmitJuvProviderProgramCreateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 447351D601B3
	 */
	public SubmitJuvProviderProgramCreateAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 44734FEC00B6
	 */
	public ActionForward save(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ServiceProviderForm sp = (ServiceProviderForm)aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		// Sending PD Request Event
		if(!sp.getActionType().equalsIgnoreCase("inactivateProgram"))
		{
			CreateProviderProgramEvent createEvent = UIServiceProviderHelper.getCreateProviderProgramEvent(sp);
			dispatch.postEvent(createEvent);
	
			// Getting PD Response Event		
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);
			ProviderProgramResponseEvent program = (ProviderProgramResponseEvent)MessageUtil.filterComposite(compositeResponse, ProviderProgramResponseEvent.class);
			if(program != null)
				sp.getCurrentProgram().setProgramId(program.getProviderProgramId());
				    
		}
		else
		{
			
			CreateProviderProgramEvent createEvent = UIServiceProviderHelper.getCreateProviderProgramEvent(sp);
			dispatch.postEvent(createEvent);
			
			// Getting PD Response Event		
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);
			
			//update status of service provider to pending if all programs and services are inactive
			if(createEvent != null && createEvent.getJuvenileServiceProviderId() !=  null && !createEvent.getJuvenileServiceProviderId().equals(""))
			{
			    UIServiceProviderHelper.updateServiceProviderStatusOnInactivate(createEvent.getJuvenileServiceProviderId());
			}
			
		    	
			    
		}
		String forwardStr = "";
		if(sp.getActionType().equalsIgnoreCase("updateProgram"))
		{			
			sp.setConfirmMessage("Program successfully updated.");
			forwardStr="updateSuccess";
		}
		else if(sp.getActionType().equalsIgnoreCase("addProgram"))
		{		
			sp.setConfirmMessage("Program successfully created.");	
			forwardStr="success";
		}
		else
		{
			sp.setConfirmMessage("Program successfully inactivated.");
			forwardStr="success";
		}
		/*GetProviderProgramsEvent prEvent = (GetProviderProgramsEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROVIDERPROGRAMS);
   		prEvent.setServiceProviderId(sp.getSelectedValue());
   		IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch1.postEvent(prEvent);
		CompositeResponse compositeResponse1 = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse1);
		Collection programs = MessageUtil.compositeToCollection(compositeResponse1, ProviderProgramResponseEvent.class);
		sp.setPrograms(programs);*/
		//.clearProgram();
		//ServiceProviderForm.Program tempProgram = sp.getCurrentProgram();
		if(sp.getActionType().equalsIgnoreCase("updateProgram"))
			sp.setCurrentProgram(new ServiceProviderForm.Program());	
		Collection coll=UIServiceProviderHelper.getPrograms(sp, sp.getProviderId());		
		sp.setPrograms(UIServiceProviderHelper.sortResults(coll, "P"));
		if(sp.getActionType().equalsIgnoreCase("inactivateProgram"))
		{
			Iterator<ProviderProgramResponseEvent> programsIter = sp.getPrograms().iterator();
			while(programsIter.hasNext())
			{
				ProviderProgramResponseEvent progResp = programsIter.next();
				if(progResp.getProviderProgramId().equals(sp.getCurrentProgram().getProgramId()))
				{
					sp.getCurrentProgram().setFunds(progResp.getProgramSourceFundList());
				}
			}
		}
		sp.getCurrentProgram().setServices(new ArrayList());		
		
		/*Collection services = UIServiceProviderHelper.getServicesByProgram(tempProgram.getProgramId());
		Iterator servIter = services.iterator();
		while(servIter.hasNext())
		{
			ServiceResponseEvent servResp = (ServiceResponseEvent) servIter.next();
			servResp.setServiceType(UIServiceProviderHelper.getServiceType(sp, servResp.getServiceTypeId()));
			servResp.setLocationName(UIServiceProviderHelper.getLocationString(servResp));
		}
		sp.getCurrentProgram().setServices(services);*/
		
		return aMapping.findForward(forwardStr);
	}
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
	    ServiceProviderForm sp = (ServiceProviderForm)aForm;
	    
	    // repopulate supervision categories when back button is clicked - US 174929
	    List<CodeResponseEvent> supervisionCategories = UIServiceProviderHelper.getJuvenileSupervisionCategories();
	    	if(supervisionCategories != null && supervisionCategories.size() > 0){
		    sp.getCurrentProgram().setSupervisionCategories(supervisionCategories);
		}
	    
	    return aMapping.findForward(UIConstants.BACK);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();	
		keyMap.put("button.saveAndContinue","save");	
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.back","back");
		return keyMap;
	}
}
