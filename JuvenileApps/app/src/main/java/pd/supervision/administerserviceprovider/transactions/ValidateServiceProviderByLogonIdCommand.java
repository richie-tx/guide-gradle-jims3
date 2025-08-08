//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\ValidateServiceProviderCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import messaging.administerserviceprovider.ValidateServiceProviderByLogonIdEvent;
import messaging.administerserviceprovider.reply.ServiceProviderErrorResponseEvent;
import messaging.user.GetUsersEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.contact.user.UserProfile;
import pd.supervision.administerserviceprovider.InhouseSP_Profile;
import pd.supervision.administerserviceprovider.OutSourcedSP_Profile;
import pd.transferobjects.helper.UserProfileHelper;

/* 
  author: mchowdhury 
*/

public class ValidateServiceProviderByLogonIdCommand implements ICommand
{

	/**
	 * @roseuid 4473538E0385
	 */
	public ValidateServiceProviderByLogonIdCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 446A2E440110
	 */
	public void execute(IEvent event)
	{
		/*ValidateServiceProviderByLogonIdEvent validateEvent = (ValidateServiceProviderByLogonIdEvent) event;
		boolean isPresent = false;  
		if(this.isLogonIdGeneric(validateEvent.getLogonId())){
			Iterator oSPIter = OutSourcedSP_Profile.findAll("serviceProviderId", "" + validateEvent.getServiceProviderId());
			while(oSPIter.hasNext()){
				OutSourcedSP_Profile out = (OutSourcedSP_Profile) oSPIter.next();
				if(validateEvent.getLogonId().equalsIgnoreCase(out.getEmployeeId())){
					isPresent = true;
					break;
				} 	
			}
		}else{
			Iterator iSPIter = InhouseSP_Profile.findAll("serviceProviderId", "" + validateEvent.getServiceProviderId());
			while(iSPIter.hasNext()){
				InhouseSP_Profile in = (InhouseSP_Profile) iSPIter.next();
				if(validateEvent.getEmployeeId().equalsIgnoreCase(in.getLogonId())){
					isPresent = true;
					break;
				} 	
			}
		}
		if(!isPresent){
			sendServiceProviderErrorResponseEvent("error.serviceProvider.invalidUser", validateEvent.getLogonId());
		}*/ //87191
 	}

	/**
	 * @param logonId
	 */
	private boolean isLogonIdGeneric(String logonId)
	{
		/*IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetUsersEvent userEvent =  new GetUsersEvent();
		userEvent.setLogonId(logonId);
		Iterator userProfiles = UserProfileHelper.getUserProfileFromJUCode(uvCode)//UserProfile.findAll(userEvent);
		while (userProfiles.hasNext())
		{
			UserProfile userProfile = (UserProfile) userProfiles.next();
			if (userProfile != null && !userProfile.getGenericUserTypeId().equalsIgnoreCase("N"))
			{
				return true;
			}
		}*/ // 87191
        return false;
	}

	/**
	 * @param errorKey
	 * @param userId
	 * @param dispatch
	 */
	private void sendServiceProviderErrorResponseEvent(String errorKey, String userId)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		ServiceProviderErrorResponseEvent errorEvent = new ServiceProviderErrorResponseEvent();		
		errorEvent.setMessage(errorKey);
		errorEvent.setUserId(userId);
		dispatch.postEvent(errorEvent);
	}

	/**
	 * @param event
	 * @roseuid 446A2E440112
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 446A2E44011F
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 446A2E440121
	 */
	public void update(Object anObject)
	{

	}
}