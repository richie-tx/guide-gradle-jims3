//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\DisplayJuvCreateUpdateContactSummaryAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.calendar.GetServiceEventsByProviderProfileIdEvent;
// import messaging.calendar.GetServiceEventsByServiceIdEvent;
import messaging.calendar.reply.ServiceEventResponseEvent;
import messaging.contact.domintf.IPhoneNumber;
// import messaging.contact.to.NameBean;
import messaging.contact.user.reply.UserResponseEvent;
import messaging.user.GetUserProfileEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.Name;
import ui.common.PhoneNumber;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;

public class DisplayJuvContactCreateUpdateSummaryAction extends LookupDispatchAction
{

	/**
	 * @roseuid 447351D200B9
	 */
	public DisplayJuvContactCreateUpdateSummaryAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 44734FEC0384
	 */
	public ActionForward find( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ServiceProviderForm spForm = (ServiceProviderForm)aForm;
		spForm.getCurrentContact().setValidLogonId(false);
		String logonId = spForm.getCurrentContact().getLogonId();	
		
		Object obj = getUserResponse(logonId) ;
		if (obj != null)
		{
			UserResponseEvent resp = (UserResponseEvent) obj;
			if(resp.getGenericUserType().equalsIgnoreCase("N"))
			{
				spForm.getCurrentContact().setContactName(new Name(resp.getFirstName(),resp.getMiddleName(),resp.getLastName()));

				{ IPhoneNumber ph = new PhoneNumber(resp.getPhoneNum());
					ph.setExt(resp.getPhoneExt());
					spForm.getCurrentContact().setOfficePhone(ph);
				}

				spForm.getCurrentContact().setEmail(resp.getEmail());
				spForm.getCurrentContact().setLogonId( resp.getLogonId() ) ;
				spForm.getCurrentContact().setValidLogonId(true);

				spForm.setActionType("addContact");
			}
			else
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Generic User ID cannot be used as contact"));		
				saveErrors(aRequest, errors);
				spForm.getCurrentContact().setContactName(new Name());
				spForm.getCurrentContact().setLogonId("");

				return aMapping.findForward("failure");				
			}
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.invalid.userId","User ID not found"));		
			saveErrors(aRequest, errors);
			return aMapping.findForward("failure");
		}
		
		return aMapping.findForward(UIConstants.FIND_SUCCESS);
	}
	
	public ActionForward clear( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		((ServiceProviderForm)aForm).clearContact() ;

		return aMapping.findForward("failure");	
	}
	
	public ActionForward next( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ServiceProviderForm sp = (ServiceProviderForm)aForm;
		sp.getCurrentContact().setValidLogonId(false);

		//get the user profile
		if(!sp.getCurrentContact().getLogonId().equals(""))
		{
			Object obj = getUserResponse(sp.getCurrentContact().getLogonId()) ;
			if (obj != null)
			{
				UserResponseEvent resp = (UserResponseEvent) obj;
				boolean isSameAgency = checkUserDepartment(resp.getAgencyId());
				if(!isSameAgency)
				{
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.notSame.agency","User Agency not same as that of Service Provider"));		
					saveErrors(aRequest, errors);
					return aMapping.findForward("failure");
				}

				if(resp.getGenericUserType().equalsIgnoreCase("N"))
				{
					sp.getCurrentContact().setValidLogonId(true);
					// sp.getCurrentContact().setContactName(new Name(resp.getFirstName(),resp.getMiddleName(),resp.getLastName()));
				}
				else
				{
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic.userId","Generic User ID"));		
					saveErrors(aRequest, errors);
					return aMapping.findForward("failure");				
				}
			}
			else
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.invalid.userId","User ID not found"));		
				saveErrors(aRequest, errors);
				return aMapping.findForward("failure");
			}
		}
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	public ActionForward back( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward inactivate( ActionMapping aMapping, ActionForm aForm,
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ServiceProviderForm sp = (ServiceProviderForm)aForm;		
		GetServiceEventsByProviderProfileIdEvent reqEvent = (GetServiceEventsByProviderProfileIdEvent)
		    EventFactory.getInstance( ServiceEventControllerServiceNames.GETSERVICEEVENTSBYPROVIDERPROFILEID);

		reqEvent.setInstructorId(Integer.parseInt(sp.getCurrentContact().getContactId().trim()));
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(reqEvent);
		
		// Getting PD Response Event		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection events = MessageUtil.compositeToCollection(compositeResponse, ServiceEventResponseEvent .class);
		if(events != null || events.size() != 0)
		{
			sp.setServiceEvents(events);
		}
		sp.setActionType("inactivateContact");

		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();	
		keyMap.put("button.find","find");	
		keyMap.put("button.next","next");	
		keyMap.put("button.back","back");
		keyMap.put("button.clear","clear");
		keyMap.put("button.inactivate","inactivate");

		return keyMap;
	}

	private Object getUserResponse(String logonId)
	{
		GetUserProfileEvent userProfile =(GetUserProfileEvent) 
				EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILE);
		userProfile.setLogonId(logonId);

		//submit the request to the PD
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(userProfile);
		IEvent reply = dispatch.getReply();
		CompositeResponse compositeResponse = (CompositeResponse) reply;

		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		Object obj = MessageUtil.filterComposite(compositeResponse, UserResponseEvent.class);

		return obj;
	}

	private boolean checkUserDepartment(String agencyId)
	{
		//get the provider's agency
		ISecurityManager securityManager = (ISecurityManager)ContextManager.getSession().get("ISecurityManager");		
		IUserInfo userInfo = securityManager.getIUserInfo();
		String spAgencyId = userInfo.getAgencyId();		

		if(!spAgencyId.equals(agencyId))
			return false;

		return true;
	}
	
}
