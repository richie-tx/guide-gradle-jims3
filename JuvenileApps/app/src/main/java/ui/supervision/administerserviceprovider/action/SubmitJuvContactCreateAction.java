//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\SubmitJuvCreateUpdateContactAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.administerserviceprovider.CreateContactFundSourceEvent;
import messaging.administerserviceprovider.GetContactFundSourceEvent;

import messaging.administerserviceprovider.reply.ContactFundSourceResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.PhoneNumber;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;

public class SubmitJuvContactCreateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 447351D50349
	 */
	public SubmitJuvContactCreateAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 44734FED0172
	 */
	public ActionForward save( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ServiceProviderForm sp = (ServiceProviderForm)aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		
		CreateContactFundSourceEvent event = (CreateContactFundSourceEvent) 
								EventFactory.getInstance(ServiceProviderControllerServiceNames.CREATECONTACTFUNDSOURCE);
		//event.setProgramId(sp.getCurrentContact().getContactId());
		event.setProfileId(sp.getCurrentContact().getContactId());
		event.setFundSource(sp.getFundSource());
		
		if (sp.getFundStartDate() != null
			&& sp.getFundStartDate().length() > 0 ){
		    event.setFundStartDate(DateUtil.stringToDate(sp.getFundStartDate(), DateUtil.DATE_FMT_1));
		}
		
		if (sp.getFundEndDate() != null
			&& sp.getFundEndDate().length() > 0 ){
		    event.setFundEndDate(DateUtil.stringToDate(sp.getFundEndDate(), DateUtil.DATE_FMT_1));
		}
		
		event.setComments(sp.getComments());
		
		dispatch.postEvent(event);
		sp.setConfirmMessage("Contact Fund Source successfully created.");

		//88553 contact is going away
		/*// Sending PD Request Event
		CreateServiceProviderContactEvent createEvent = UIServiceProviderHelper.getCreateProviderContactEvent(sp);
		dispatch.postEvent(createEvent);

		// Getting PD Response Event		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		ServiceProviderContactResponseEvent contactResp = (ServiceProviderContactResponseEvent)MessageUtil.filterComposite(compositeResponse, ServiceProviderContactResponseEvent.class);
		if(contactResp != null)
		{
			if(sp.getActionType().equals("inactivateContact"))
			{
				if(contactResp.isInactivated())
				{
					sp.getCurrentContact().setContactId(contactResp.getJuvServProviderProfileId());
					sp.getCurrentContact().setEmployeeId(contactResp.getEmployeeId());
				}
				else
				{
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.last.adminContact","Duplicate name"));		
					saveErrors(aRequest, errors);
					return aMapping.findForward("failure");
				}
			}
			else
				sp.getCurrentContact().setContactId(contactResp.getJuvServProviderProfileId());
		}
	
		Collection coll = UIServiceProviderHelper.getContacts(sp.getProviderId());
//		format the phone number
		Iterator iter = coll.iterator();
		while(iter.hasNext())
		{
			ServiceProviderContactResponseEvent conResp = (ServiceProviderContactResponseEvent)iter.next();
			PhoneNumber p = new PhoneNumber(conResp.getWorkPhone());
			conResp.setWorkPhone(p+"");
		}
		sp.setContacts(UIServiceProviderHelper.sortResults(coll, "C"));
		coll = UIServiceProviderHelper.getPrograms(sp, sp.getProviderId());
		sp.setPrograms(UIServiceProviderHelper.sortResults(coll, "P"));

		if(sp.getActionType().equalsIgnoreCase("updateContact"))
			sp.setConfirmMessage("Contact successfully updated.");
		else if(sp.getActionType().equalsIgnoreCase("inactivateContact"))
			sp.setConfirmMessage("Contact successfully inactivated.");
		else
			sp.setConfirmMessage("Contact successfully created with Employee ID: " + sp.getCurrentContact().getContactId());*/

		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward createFundSource( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
	    ServiceProviderForm sp = (ServiceProviderForm)aForm;
	    sp.setFundSource("");
	    sp.setFundStartDate("");
	    sp.setFundEndDate("");
	    sp.setComments("");
	    sp.setConfirmMessage("");
	    
	    GetContactFundSourceEvent getEvent = (GetContactFundSourceEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETCONTACTFUNDSOURCE);
	    getEvent.setProfileId(sp.getCurrentContact().getContactId());
	    CompositeResponse compResponse = MessageUtil.postRequest(getEvent);
	    if (compResponse != null ) {
		List<ContactFundSourceResponseEvent> contactFundSources = MessageUtil.compositeToList(compResponse, ContactFundSourceResponseEvent.class);
		
	    	sp.setContactFundSourceList(processAndSortContactFundSources(contactFundSources));
	    	
	    }
	   
	    return aMapping.findForward("createSuccess");
	}
	
	public List<ContactFundSourceResponseEvent> processAndSortContactFundSources(List<ContactFundSourceResponseEvent> contactFundSources){
	    
	    List<ContactFundSourceResponseEvent> activeContactFundSources = new ArrayList<ContactFundSourceResponseEvent>();
	    List<ContactFundSourceResponseEvent> inactiveContactFundSources = new ArrayList<ContactFundSourceResponseEvent>();
	    List<ContactFundSourceResponseEvent> allContactFundSources = new ArrayList<ContactFundSourceResponseEvent>();
	    
	    Iterator<ContactFundSourceResponseEvent> iter = contactFundSources.iterator();
	    
	    while(iter.hasNext()){
		
		ContactFundSourceResponseEvent event = iter.next();
		
		if(event.getStatus() != null && !event.getStatus().equals("") && event.getStatus().equalsIgnoreCase("active")){
		    activeContactFundSources.add(event);
		}
		
		if(event.getStatus() != null && !event.getStatus().equals("") && event.getStatus().equalsIgnoreCase("inactive")){
		    inactiveContactFundSources.add(event);
		}
	    }
	    
	    
	    //sort active
	    Collections.sort(activeContactFundSources, new Comparator<ContactFundSourceResponseEvent>(){
		    @Override
		    public int compare(ContactFundSourceResponseEvent f1, ContactFundSourceResponseEvent f2){
			int result = 0;
			if(f1.getStatus() != null && f2.getStatus() != null){
			    
			    int testValue = f1.getStatus().compareTo(f2.getStatus());
			    
			    if(testValue > 0){
				result = 1;
			    }
			    
			    if(testValue < 0){
				result = -1;
			    }
			    
			}
			
			return result;
		    }
		});
	    
	    //sort inactive
	    Collections.sort(inactiveContactFundSources, new Comparator<ContactFundSourceResponseEvent>(){
		    @Override
		    public int compare(ContactFundSourceResponseEvent f1, ContactFundSourceResponseEvent f2){
			int result = 0;
			
			if(f2.getStartDate() != null && f1.getStartDate() != null){
			    
			    int testValue = f2.getStartDate().compareTo(f1.getStartDate());
			    
			    if(testValue > 0){
				result = 1;
			    }
			    
			    if(testValue < 0){
				result = -1;
			    }			    
			}
			
			return result;
		    }
		});
	    
	    //aggregate active & inactive contact fund sources
	    Iterator<ContactFundSourceResponseEvent> iterActive = activeContactFundSources.iterator();
	    
	    if(iterActive != null && iterActive.hasNext()){
		
		 while(iterActive.hasNext()){
			ContactFundSourceResponseEvent activeEvent = iterActive.next();
			allContactFundSources.add(activeEvent);
		    }		
	    }	   
	    
	    Iterator<ContactFundSourceResponseEvent> iterInactive = inactiveContactFundSources.iterator();
	    
	    if(iterInactive != null && iterInactive.hasNext()){
        	    while(iterInactive.hasNext()){
        		ContactFundSourceResponseEvent inactiveEvent = iterInactive.next();
        		allContactFundSources.add(inactiveEvent);
        	    }
	    }
	    
	    return allContactFundSources;	    
	}
	
	public ActionForward next( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
	    return aMapping.findForward("next");
	}
	
	public ActionForward saveAndContinue( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
	    return aMapping.findForward("next");
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

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();	
		keyMap.put("button.saveAndContinue","save");	
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.back","back");
		keyMap.put("button.createFundSource","createFundSource");
		keyMap.put("button.next","next");
		return keyMap;
	}
}
