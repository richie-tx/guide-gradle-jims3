//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\SubmitJuvCreateServiceProviderAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.CreateServiceProviderEvent;
import messaging.administerserviceprovider.GetServiceProvidersEvent;
import messaging.administerserviceprovider.UpdateServiceProviderStatusRequestEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import pd.supervision.administerserviceprovider.JuvenileServiceProvider;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.PhoneNumber;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;

public class HandleServiceProviderDashboardAction extends LookupDispatchAction
{

	/**
	 * @roseuid 447351D50126
	 */
	public HandleServiceProviderDashboardAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 446A2E440317
	 */
	public ActionForward add( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ServiceProviderForm spForm =  (ServiceProviderForm)aForm;
		UIServiceProviderHelper.setContactCodes(spForm);
		if(spForm.getActionType().equals("addProgram"))
		{
			spForm.clearProgram();
			spForm.getCurrentProgram().clearServices();
			spForm.getCurrentProgram().setStatusId("P");
			Date currentDate = new Date();
			spForm.getCurrentProgram().setStartDate(DateUtil.dateToString(currentDate, DateUtil.DATE_FMT_1));
			spForm.getCurrentProgram().setFundStartDate(DateUtil.dateToString(currentDate, DateUtil.DATE_FMT_1));
			
			//================== US 174929 ==================
			List<CodeResponseEvent> supervisionCategories = UIServiceProviderHelper.getJuvenileSupervisionCategories();
			if(supervisionCategories != null && supervisionCategories.size() > 0){
			    spForm.getCurrentProgram().setSupervisionCategories(supervisionCategories);
			}
			
			return aMapping.findForward("addUpdateProgramSuccess");
		}
		//86318
		/*else if(spForm.getActionType().equals("addContact"))
		{
			spForm.clearContact();
			spForm.getCurrentContact().setIsAdminContact("NO");
			spForm.setSelectedValue( "" ) ;
			
			return aMapping.findForward("addUpdateContactSuccess");
		}*/ //86318

		return aMapping.findForward("addUpdateProgramSuccess"); //86318
	}

	public ActionForward update( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ServiceProviderForm spForm =  (ServiceProviderForm)aForm;
		UIServiceProviderHelper.setContactCodes(spForm);
		if(spForm.getActionType().equals("updateProgram"))
		{		
			spForm.setCurrentProgram(new ServiceProviderForm.Program());
			Collection coll = UIServiceProviderHelper.getPrograms(spForm, spForm.getProviderId());	
			spForm.getCurrentProgram().setProgramName(spForm.getSelectedValue());
			
			Iterator iter = coll.iterator();
			while(iter.hasNext())
			{
				ProviderProgramResponseEvent progResp = (ProviderProgramResponseEvent) iter.next();
				String progName = progResp.getProgramName().trim();
				String selectValue = spForm.getSelectedValue().trim();
				if(progName.equalsIgnoreCase(selectValue))
				{
					UIServiceProviderHelper.fillProgramDetails(spForm, progResp);
					break ;
				}			
			}
			
			//================== US 174929 ==================
			List<CodeResponseEvent> supervisionCategories = UIServiceProviderHelper.getJuvenileSupervisionCategories();
			if(supervisionCategories != null && supervisionCategories.size() > 0){
			    spForm.getCurrentProgram().setSupervisionCategories(supervisionCategories);
			}
			
			if(spForm.getCurrentProgram() != null && spForm.getCurrentProgram().getProgramId() != null && 
						!spForm.getCurrentProgram().getProgramId().equals(""))
			{
			    ProviderProgramResponseEvent spProgram = UIServiceProviderHelper.getServiceProviderProgram(spForm.getCurrentProgram().getProgramId());
			    
			    if(spProgram != null && spProgram.getSupervisionCategory() != null)
			    {
				//split supervisionCategory comma delimited string into an array
				String[] savedSupervisionCategories = spProgram.getSupervisionCategory().split("\\s*,\\s*"); // "\\" remove empty spaces
				spForm.getCurrentProgram().setSelectedSupervisionCategories(savedSupervisionCategories);
			    }
			}
				
			
			return aMapping.findForward("addUpdateProgramSuccess");
		}
		//86318
		/*else if(spForm.getActionType().equals("updateContact"))
		{
			spForm.setCurrentContact(new ServiceProviderForm.Contact());
			spForm.getCurrentContact().setEmployeeId(spForm.getSelectedValue());

			Collection coll = spForm.getContacts();
			Iterator iter = coll.iterator();
			while(iter.hasNext())
			{
				ServiceProviderContactResponseEvent contactResp = (ServiceProviderContactResponseEvent) iter.next();
				if(contactResp.getEmployeeId().equalsIgnoreCase(spForm.getSelectedValue()))
				{
					UIServiceProviderHelper.fillContactDetails(spForm.getCurrentContact(), contactResp);
					break ;
				}				
			}
			return aMapping.findForward("addUpdateContactSuccess");
		}*/
		else if(spForm.getActionType().equals("updateSP"))
		{
			JuvenileServiceProviderResponseEvent resp = UIServiceProviderHelper.getProvider(spForm.getProviderId());
			 UIServiceProviderHelper.fillServiceProviderDetails(spForm, resp);
			 spForm.setActionType("updateProvider");
			 return aMapping.findForward("updateSPSuccess");
		}

		return aMapping.findForward("addUpdateContactSuccess");
	}

	public ActionForward addService( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ServiceProviderForm spForm =  (ServiceProviderForm)aForm;
		//set the current program
		// 16 may 2008 - mjt - never used?: String selectedProgram = spForm .getSelectedValue();
		spForm.setCurrentProgram(new ServiceProviderForm.Program());
		spForm.getCurrentProgram().setProgramName(spForm.getSelectedValue());

		Collection coll = UIServiceProviderHelper.getPrograms(spForm, spForm.getProviderId());
		Iterator iter = coll.iterator();
		while(iter.hasNext())
		{
			ProviderProgramResponseEvent progResp = (ProviderProgramResponseEvent) iter.next();
			if(progResp.getProgramName().equalsIgnoreCase(spForm.getSelectedValue()))
			{
				UIServiceProviderHelper.fillProgramDetails(spForm, progResp);
				break ;
			}
		}
			
		Collection locationResponses = UIServiceProviderHelper.setServiceLocations(spForm);
		spForm.setServiceLocationNames(locationResponses);		

		return aMapping.findForward("addServiceSuccess");
	}

	public ActionForward view( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ServiceProviderForm spForm =  (ServiceProviderForm)aForm;		
		if(spForm.getActionType().equalsIgnoreCase("viewProgram"))
		{
			// set the current program
			spForm.setCurrentProgram(new ServiceProviderForm.Program());
			spForm.getCurrentProgram().setServices(new ArrayList());
			spForm.getCurrentProgram().setProgramName(spForm.getSelectedValue());

			Collection coll = spForm.getPrograms();
			Iterator iter = coll.iterator();
			while(iter.hasNext())
			{
				ProviderProgramResponseEvent progResp = (ProviderProgramResponseEvent) iter.next();
				if(progResp.getProgramName().equalsIgnoreCase(spForm.getSelectedValue()))
				{
					UIServiceProviderHelper.fillProgramDetails(spForm, progResp);
					Collection services = UIServiceProviderHelper.sortResults(UIServiceProviderHelper.getServicesByProgram(spForm.getCurrentProgram().getProgramId()), "S");

					Iterator servIter = services.iterator();
					ServiceProviderForm.Program.Service someService = new ServiceProviderForm.Program.Service();
					Collection temp = new ArrayList(); 
					while(servIter.hasNext())
					{
						ServiceResponseEvent servResp = (ServiceResponseEvent) servIter.next();
						someService.setServiceName(servResp.getServiceName());
						someService.setServiceId(servResp.getServiceId());
						someService.setCode(servResp.getServiceCode());
					
						someService.setType(UIServiceProviderHelper.getServiceType(spForm, servResp.getServiceTypeId()));
						someService.setPreviousLocations(UIServiceProviderHelper.getLocations(servResp));
						someService.setPrevLocationsSize(someService.getPreviousLocations().size());
						someService.setStatusId(servResp.getStatusId());
						// 20may2008 - mjt - not sure why this is commented out:
						/* servResp.setServiceType(UIServiceProviderHelper.getServiceType(spForm, servResp.getServiceTypeId()));
						servResp.setLocationName(UIServiceProviderHelper.getLocationString(servResp));
						*/
						
						someService.setMaxEnrollment(servResp.getMaxEnrollment());
						
						temp.add(someService);
						someService = new ServiceProviderForm.Program.Service();
					}
					// 20may2008 - mjt - not sure why this is commented out:
					// spForm.getCurrentProgram().setServices(services);
					
					spForm.getCurrentProgram().setServices(temp);
				}
			}									
			spForm.setConfirmMessage("");
			
			//================== US 174929 ==================
			List<CodeResponseEvent> supervisionCategories = UIServiceProviderHelper.getJuvenileSupervisionCategories();
			
			ProviderProgramResponseEvent spProgram = UIServiceProviderHelper.getServiceProviderProgram(spForm.getCurrentProgram().getProgramId());
			    
			if(spProgram != null && spProgram.getSupervisionCategory() != null)
			{
				//split supervisionCategory comma delimited string into an array
				String[] savedSupervisionCategories = spProgram.getSupervisionCategory().split("\\s*,\\s*"); // "\\" remove empty spaces
				spForm.getCurrentProgram().setSelectedSupervisionCategories(savedSupervisionCategories);
			}
			    
			if(supervisionCategories != null && supervisionCategories.size() > 0 &&
				spProgram.getSupervisionCategory() != null && spForm.getCurrentProgram().getSelectedSupervisionCategories().length > 0)
			{			    
			    String codeValues = StringUtils.join(spForm.getCurrentProgram().getSelectedSupervisionCategories(), ", ");
			    spForm.getCurrentProgram().setSelectedSupervisions(codeValues);
				
			    Iterator iterSP =  supervisionCategories.iterator();
			    List<CodeResponseEvent> selectedCategories = new ArrayList<CodeResponseEvent>();
				
				while(iterSP.hasNext())
				{
				    CodeResponseEvent sprvEvent = (CodeResponseEvent) iterSP.next();
					
				    for(int i = 0; i < spForm.getCurrentProgram().getSelectedSupervisionCategories().length; i++){
					
					String selectedItem = spForm.getCurrentProgram().getSelectedSupervisionCategories()[i];
					
					if(sprvEvent != null && sprvEvent.getCode() != null && selectedItem != null)
					{				   
	        				if(sprvEvent.getCode().equals(selectedItem))
	        				{
	        				    selectedCategories.add(sprvEvent);
	        				    break;
	        				}
					}
				    }
				
				}		
				
				spForm.getCurrentProgram().setSupervisionCategories(selectedCategories);
			    
			}
			
			return aMapping.findForward("viewProgramSuccess");
		}
		else
		{	
			//set the current contact	TODO
			spForm.getCurrentContact().setEmployeeId(spForm.getSelectedValue());
			Collection<ServiceProviderContactResponseEvent> coll = spForm.getContacts();
			Iterator<ServiceProviderContactResponseEvent> iter = coll.iterator();
			while(iter.hasNext())
			{
				ServiceProviderContactResponseEvent contactResp = (ServiceProviderContactResponseEvent) iter.next();
				if(contactResp.getEmployeeId().equalsIgnoreCase(spForm.getSelectedValue()))
				{
					UIServiceProviderHelper.fillContactDetails(spForm.getCurrentContact(), contactResp);
					break ;
				}				
			}
			spForm.setConfirmMessage("");
			return aMapping.findForward("viewContactSuccess");
		}
	}

	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	public ActionForward back( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward reactivate( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
	    ServiceProviderForm sp = (ServiceProviderForm)aForm;	    	
	  	   	  		  	
	  	JuvenileServiceProviderResponseEvent resp = UIServiceProviderHelper.getProvider(sp.getProviderId()); 	
	  		  	
	  	if(sp.getActionType().equalsIgnoreCase("reactivate")){
	  	    
	  	  Collection results = new ArrayList();
		  if(resp != null)
		  {
		  	UIServiceProviderHelper.fillServiceProviderDetails(sp, resp);
		  }  		  
		  
		  ServiceProviderResponseEvent spEvent = UIServiceProviderHelper.getServiceProviderById(sp);
		  if(spEvent != null){
		      sp.setStatusChangeDate(spEvent.getStatusChangeDate());
		  }
		  
		  sp.setStatusId("A");
		  
		  sp.setActionType("reviewServiceProviderReactivate");
		  
		  sp.setConfirmMessage("");
		  
		  return aMapping.findForward("reactivateSP");
		  
	  	} else if(sp.getActionType().equalsIgnoreCase("saveReactivatedSP")){
	  	 
	  	  JuvenileServiceProviderResponseEvent response = UIServiceProviderHelper.getProvider(sp.getProviderId());
	  	  UIServiceProviderHelper.fillServiceProviderDetails(sp, response);
			
	  	  IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	  	  CreateServiceProviderEvent createEvent = UIServiceProviderHelper.getCreateServiceProviderEvent(sp);
	  	  
	  	  dispatch.postEvent(createEvent);
	  	  CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	  	  
			
	  	  	sp.setActionType("reactivateSPSuccess");
	  	  	
	  	  	//repopulate with new saved info
	  	  	JuvenileServiceProviderResponseEvent savedResponse = UIServiceProviderHelper.getProvider(sp.getProviderId());
		  	UIServiceProviderHelper.fillServiceProviderDetails(sp, savedResponse);
		  	
		  	ServiceProviderResponseEvent spEvent = UIServiceProviderHelper.getServiceProviderById(sp);
			  if(spEvent != null){
			      sp.setStatusChangeDate(spEvent.getStatusChangeDate());
			  }
			
	  	    sp.setConfirmMessage("Service Provider has been successfully activated");
	  	    
	  	    return  aMapping.findForward("reactivateSPSuccess");
	  	} 				
			
			
	    return aMapping.findForward("reactivateSP");
		  
		 
	}
	
	public ActionForward returnToSPSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
		HttpServletResponse aResponse) {
        	
        	ServiceProviderForm spForm = (ServiceProviderForm) aForm;
           	Collection results = new ArrayList();
        	GetServiceProvidersEvent spEvent = (GetServiceProvidersEvent)EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEPROVIDERS);
   		spEvent.setServiceProviderName(spForm.getProviderNameQueryString());
   		//spEvent.setStatusId(spForm.getStatusId());
   		IUserInfo user = UIServiceProviderHelper.getCreateUser();
   		spEvent.setAgencyId(user.getAgencyId());
   		
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
		
		spForm.setResultsSize(results.size());
	
		return aMapping.findForward("returnToSPSearch");
	
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
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.back","back");
		keyMap.put("button.add","add");
		keyMap.put("button.update","update");
		keyMap.put("button.delete","delete");
		keyMap.put("button.addService","addService");
		keyMap.put("button.view", "view");
		keyMap.put("button.updateSP","update");
		keyMap.put("button.reactivate","reactivate");
		keyMap.put("button.saveAndContinue","reactivate"); 
		keyMap.put("button.returnToServiceProvider","returnToSPSearch");

		return keyMap;
	}
}
