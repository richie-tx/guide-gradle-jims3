//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\DisplayJuvServiceProviderSearchResultsAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.GetProviderServicesEvent;
import messaging.administerserviceprovider.GetServiceProvidersEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import mojo.km.security.IUserInfo;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import naming.ServiceProviderControllerServiceNames;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.supervision.administerserviceprovider.ProviderProgram;

import ui.common.CodeHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;


public class DisplayJuvServiceProviderSearchResultsAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 450ACC9F0180
    */
   public DisplayJuvServiceProviderSearchResultsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 450AA1790284
    */
   public ActionForward submit(ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
   {
   	ServiceProviderForm spForm = (ServiceProviderForm) aForm;
   	Collection results = new ArrayList();
   	
   	//store the search string so that it can be used later 
   	spForm.setProviderNameQueryString(spForm.getProviderName()); 
   	
   	if(spForm.getSearchTypeId().equals("PR"))
	{   		
   		try {
   			results = UIServiceProviderHelper.searchPrograms(spForm);
   		}
   		catch (GeneralFeedbackMessageException ex){
   			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ex.getMessage()));
			saveErrors(aRequest, errors);
			return aMapping.findForward("searchFailure");
   		}
   		
		/*Iterator iter = coll.iterator();
		
		while(iter.hasNext())
		{
			ProviderProgramResponseEvent resp = (ProviderProgramResponseEvent)iter.next();
			String targetInterventionString = CodeHelper.getCodeDescription( PDCodeTableConstants.JUV_PROGRAM_GROUP, resp.getTargetInterventionId());
			resp.setTargetInterventionId(targetInterventionString);
			results.add(resp);
		}*/
		spForm.setSearchResults(sortResults(results, spForm.getSearchTypeId()));
   		
	}
   	else if(spForm.getSearchTypeId().equals("FS"))
	{   		
   		try 
   		{
   		    if(spForm.getFundSource() != null && !spForm.getFundSource().equals("")){
   			
   			String fundSourceDescription = CodeHelper.getCodeDescription("JUVENILE_SOURCE_FUND", spForm.getFundSource());
   			spForm.setFundSourceDescription(fundSourceDescription);
   			
   		    	results = UIServiceProviderHelper.getProgramsByFundSource(spForm.getFundSource());
   		    }   		    
   		 
   		}
   		catch (Exception ex){
   			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ex.getMessage()));
			saveErrors(aRequest, errors);
			return aMapping.findForward("searchFailure");
   		}
   		
		spForm.setSearchResults(sortResults(results, spForm.getSearchTypeId()));
   		
	}
   	//Add program code as search criteria ER JIMS200075756 --- Start
   	else if(spForm.getSearchTypeId().equals("PC"))
	{   		
   		try {
   			results = UIServiceProviderHelper.searchProgramsByCode(spForm);
   		}
   		catch (GeneralFeedbackMessageException ex){
   			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(ex.getMessage()));
			saveErrors(aRequest, errors);
			return aMapping.findForward("searchFailure");
   		}
   		spForm.setSearchResults(sortResults(results, spForm.getSearchTypeId()));
   		
	}
   	//  Add program code as search criteria ER JIMS200075756 --- End
   	else if(spForm.getSearchTypeId().equals("SP"))
   	{
   		GetServiceProvidersEvent spEvent = (GetServiceProvidersEvent)EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDERS);
   		spEvent.setServiceProviderName(spForm.getProviderName());
   		spEvent.setStatusId(spForm.getStatusId());
   		IUserInfo user = UIServiceProviderHelper.getCreateUser();
   		spEvent.setAgencyId(user.getAgencyId());
   		
   		if(spForm.getIsInHouse().equals("Y"))
   		{
   			spEvent.setInHouse(true);
   			spEvent.setAllServiceProviders(false);
   		}
   		else if(spForm.getIsInHouse().equals("N"))
   		{
   			spEvent.setInHouse(false);
   			spEvent.setAllServiceProviders(false);
   		}   		
   		else
   			spEvent.setAllServiceProviders(true);
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   		dispatch.postEvent(spEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		//Handle error thrown as ErrorResponseEvent from the command, if there is any
		//Expected error: Number of juveniles matching this criteria is greater than 2000.
		ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
		if(error != null) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
			saveErrors(aRequest, errors);
			return aMapping.findForward("searchFailure");
		}
		else {
			
			MessageUtil.processReturnException(compositeResponse);
			Collection coll = MessageUtil.compositeToCollection(compositeResponse, ServiceProviderResponseEvent.class);
			Iterator iter = coll.iterator();
			
			while(iter.hasNext())
			{
				ServiceProviderResponseEvent resp = (ServiceProviderResponseEvent)iter.next();	
				String statusString = CodeHelper.getCodeDescription( "JUV_SERV_PROVIDER_STATUS", resp.getStatusId());
				resp.setStatusId(statusString);
				results.add(resp);
			}
		
			spForm.setSearchResults(sortResults(results, spForm.getSearchTypeId()));
		}
   	}
   	else if(spForm.getSearchTypeId().equals("SV"))
   	{
   		ServiceProviderForm.Program.Service service = spForm.getCurrentProgram().getProgramService();
   		GetProviderServicesEvent svEvent = (GetProviderServicesEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETPROVIDERSERVICES);
   		svEvent.setServiceName(service.getServiceName());
   		String serviceTypeId = UIServiceProviderHelper.getServiceTypeId(spForm, service.getTypeId(), false);
   		svEvent.setServiceTypeId(serviceTypeId);
   		svEvent.setStatusId(service.getStatusId());
   		IUserInfo user = UIServiceProviderHelper.getCreateUser();
   		svEvent.setAgencyId(user.getAgencyId());
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(svEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		//Handle error thrown as ErrorResponseEvent from the command, if there is any
		//Expected error: Number of juveniles matching this criteria is greater than 2000.
		ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
		if(error != null) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
			saveErrors(aRequest, errors);
			return aMapping.findForward("searchFailure");
		}
		else {
			
		
			MessageUtil.processReturnException(compositeResponse);
			Collection coll = MessageUtil.compositeToCollection(compositeResponse, ServiceResponseEvent.class);
			Iterator iter = coll.iterator();
			
			while(iter.hasNext())
			{
				ServiceResponseEvent resp = (ServiceResponseEvent)iter.next();	
				String serviceTypeString = UIServiceProviderHelper.getServiceType(spForm, resp.getServiceTypeId());
				resp.setServiceTypeId(serviceTypeString);
				String targetInterventionString = CodeHelper.getCodeDescription( PDCodeTableConstants.JUV_PROGRAM_GROUP, resp.getTargetInterventionId());
				resp.setTargetInterventionId(targetInterventionString);
				results.add(resp);
			}
			spForm.setSearchResults(sortResults(results, spForm.getSearchTypeId()));
		}
   	}
   spForm.setResultsSize(results.size());
   	return aMapping.findForward(UIConstants.SUCCESS);
   }
   public ActionForward create(ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
   {
   	return aMapping.findForward(UIConstants.CREATE_SUCCESS);
   }
   public ActionForward refresh(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ServiceProviderForm spForm = (ServiceProviderForm) aForm;
		spForm.clear();
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}
   
   private Collection sortResults(Collection coll, String searchTypeId)
   {		
   		Collection temp = new ArrayList();
   		Iterator iter = coll.iterator();
   	
   			if(searchTypeId.equals("SP"))
   			{
   				while(iter.hasNext())
   		   		{
		   			ServiceProviderResponseEvent spResp = (ServiceProviderResponseEvent) iter.next();
		   			if(!spResp.getStatusId().equalsIgnoreCase("INACTIVE"))
		   			{
		   				temp.add((ServiceProviderResponseEvent)spResp);   				
		   			}
   		   		}
   			}
   			else if(searchTypeId.equals("PR"))
   			{
   				while(iter.hasNext())
   		   		{
	   				ProviderProgramResponseEvent prResp = (ProviderProgramResponseEvent) iter.next();
		   			if(!prResp.getServiceProviderStatusId().equalsIgnoreCase("I"))
		   			{
		   				temp.add((ProviderProgramResponseEvent)prResp);   				
		   			}
   		   		}
   			}
   			else if(searchTypeId.equals("FS"))
   			{
   				while(iter.hasNext())
   		   		{
	   				ProviderProgramResponseEvent prResp = (ProviderProgramResponseEvent) iter.next();
		   			if(!prResp.getServiceProviderStatusId().equalsIgnoreCase("I"))
		   			{
		   				temp.add((ProviderProgramResponseEvent)prResp);   				
		   			}
   		   		}
   			}
   			else if(searchTypeId.equals("PC"))
   			{
   				while(iter.hasNext())
   		   		{
	   				ProviderProgramResponseEvent prResp = (ProviderProgramResponseEvent) iter.next();
		   			if(!prResp.getServiceProviderStatusId().equalsIgnoreCase("I"))
		   			{
		   				temp.add((ProviderProgramResponseEvent)prResp);   				
		   			}
   		   		}
   			}
   			
   			else if(searchTypeId.equals("SV"))
   			{
   				while(iter.hasNext())
   		   		{
		   			ServiceResponseEvent svResp = (ServiceResponseEvent) iter.next();
		   			if(!svResp.getServiceProviderStatusId().equalsIgnoreCase("I"))
		   			{
		   				temp.add((ServiceResponseEvent)svResp);   				
		   			}
   		   		}
   			}
   		if(searchTypeId.equals("SP"))
   			Collections.sort((ArrayList)temp);
   		else if(searchTypeId.equals("PR"))
   			Collections.sort((ArrayList)temp, ProviderProgramResponseEvent.providerNameComparator);
   		else if(searchTypeId.equals("FS"))
			Collections.sort((ArrayList)temp, ProviderProgramResponseEvent.providerNameComparator);
   		else if(searchTypeId.equals("PC"))
   			Collections.sort((ArrayList)temp, ProviderProgramResponseEvent.providerNameComparator);
   		
   		else if(searchTypeId.equals("SV"))
   			Collections.sort((ArrayList)temp, ServiceResponseEvent.providerNameComparator);
   		iter = coll.iterator();
   		Collection inactiveSPs = new ArrayList();
   		while(iter.hasNext())
   		{
   			if(searchTypeId.equals("SP"))
   			{
   				ServiceProviderResponseEvent spResp = (ServiceProviderResponseEvent) iter.next();
   	   			if(spResp.getStatusId().equalsIgnoreCase("INACTIVE"))
   	   			{
   	   				inactiveSPs.add((ServiceProviderResponseEvent)spResp);   				
   	   			}
   			}
   			else if(searchTypeId.equals("PR"))
   			{
   				
   				while(iter.hasNext())
   		   		{
	   				ProviderProgramResponseEvent prResp = (ProviderProgramResponseEvent) iter.next();
		   			if(prResp.getServiceProviderStatusId().equalsIgnoreCase("I"))
		   			{
		   				inactiveSPs.add((ProviderProgramResponseEvent)prResp);   				
		   			}
   		   		}
   			}
   			else if(searchTypeId.equals("FS"))
   			{
   				
   				while(iter.hasNext())
   		   		{
	   				ProviderProgramResponseEvent prResp = (ProviderProgramResponseEvent) iter.next();
		   			if(prResp.getServiceProviderStatusId().equalsIgnoreCase("I"))
		   			{
		   				inactiveSPs.add((ProviderProgramResponseEvent)prResp);   				
		   			}
   		   		}
   			}
   			else if(searchTypeId.equals("PC"))
   			{
   				
   				while(iter.hasNext())
   		   		{
	   				ProviderProgramResponseEvent prResp = (ProviderProgramResponseEvent) iter.next();
		   			if(prResp.getServiceProviderStatusId().equalsIgnoreCase("I"))
		   			{
		   				inactiveSPs.add((ProviderProgramResponseEvent)prResp);   				
		   			}
   		   		}
   			}
   			
   			else if(searchTypeId.equals("SV"))
   			{
   				while(iter.hasNext())
   		   		{
		   			ServiceResponseEvent svResp = (ServiceResponseEvent) iter.next();
		   			if(svResp.getServiceProviderStatusId().equalsIgnoreCase("I"))
		   			{
		   				inactiveSPs.add((ServiceResponseEvent)svResp);   				
		   			}
   		   		}
   			}
   	
   		}
   		//sort the inactive SPs
   		if(searchTypeId.equals("SP"))
   			Collections.sort((ArrayList)inactiveSPs);
   		else if(searchTypeId.equals("PR"))
   			Collections.sort((ArrayList)inactiveSPs, ProviderProgramResponseEvent.providerNameComparator);
   		else if(searchTypeId.equals("FS"))
			Collections.sort((ArrayList)inactiveSPs, ProviderProgramResponseEvent.providerNameComparator);
   		else if(searchTypeId.equals("PC"))
   			Collections.sort((ArrayList)inactiveSPs, ProviderProgramResponseEvent.providerNameComparator);
   		
   		else if(searchTypeId.equals("SV"))
   			Collections.sort((ArrayList)inactiveSPs, ServiceResponseEvent.providerNameComparator);
   		
   		
   		//iterate thru the inactive SPs and add to temp
   		Iterator inactiveIter = inactiveSPs.iterator();
   		while(inactiveIter.hasNext())
   		{
   			if(searchTypeId.equals("SP"))
   				temp.add((ServiceProviderResponseEvent) inactiveIter.next());
   			else if(searchTypeId.equals("PR"))
   				temp.add((ProviderProgramResponseEvent) inactiveIter.next());
   			else if(searchTypeId.equals("FS"))
				temp.add((ProviderProgramResponseEvent) inactiveIter.next());
   			else if(searchTypeId.equals("PC"))
   				temp.add((ProviderProgramResponseEvent) inactiveIter.next());
   			
   			else if(searchTypeId.equals("SV"))
   				temp.add((ServiceResponseEvent) inactiveIter.next());
   		}
		
   		return temp;
   }
   /* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.submit","submit");	
		keyMap.put("button.createHCJPD","create");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.back","back");
		return keyMap;
	}
	/**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward back(
	   		ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
	   {
		    return aMapping.findForward(UIConstants.BACK);
	   }  
	   /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward cancel(
	   		ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
	   {
		    return aMapping.findForward(UIConstants.CANCEL);
	   }  
}
