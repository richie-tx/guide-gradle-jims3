package pd.supervision.administerserviceprovider.transactions;

import messaging.administerserviceprovider.ValidateAdminUserIdEvent;
import messaging.administerserviceprovider.reply.ServiceProviderErrorResponseEvent;
import mojo.km.config.SecurityProperties;
import mojo.km.context.ContextManager;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.ISecurityManager;
import pd.contact.user.UserProfile;
import pd.transferobjects.helper.UserProfileHelper;

/**
 * Validate admin userId
 */
public class ValidateAdminUserIdCommand implements ICommand
{

    /**
     * @roseuid 4473538E00B6
     */
    public ValidateAdminUserIdCommand()
    {

    }

    @Override
    public void execute(IEvent event) throws Exception
    {

	ValidateAdminUserIdEvent validateEvent = (ValidateAdminUserIdEvent) event;

	String juCode = validateEvent.getAdminUserId();

	UserProfile userProfile = UserProfileHelper.getUserProfileFromJUCode(juCode);
	if (userProfile != null)
	{
	    ISecurityManager securityManager = null;
	    String securityManagerImpl = SecurityProperties.getInstance().getSecurityManagerImpl();
	    if (securityManagerImpl == null)
	    {
		throw new RuntimeException("No SecurityManager found. You must register an SecurityManager in the Security section in mojo.xml");
	    }
	    try
	    {
		securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		if (securityManager == null)
		{
		    securityManager = (ISecurityManager) Class.forName(securityManagerImpl).newInstance();
		}

	    }
	    catch (Exception e)
	    {
		e.printStackTrace(System.out);
		throw new RuntimeException("Could not create instance of ISecurityManager. Exception is " + e);
	    }
	    if (securityManager.getIUserInfo() != null)
	    {
		if (securityManager.getIUserInfo().getDepartmentId() != null)
		{
		    if (securityManager.getIUserInfo().getDepartmentId().equalsIgnoreCase(userProfile.getDepartmentId()))
		    {
			return;
		    }
		    else
		    {
			this.sendServiceProviderErrorResponseEvent("error.invalid.department");
		    }
		}
	    }

	}

	else
	{
	    this.sendServiceProviderErrorResponseEvent("error.user.notfound");
	}

    }

    /**
     * sendServiceProviderErrorResponseEvent
     * 
     * @param errorKey
     */
    private void sendServiceProviderErrorResponseEvent(String errorKey)
    {
	ServiceProviderErrorResponseEvent errorEvent = new ServiceProviderErrorResponseEvent();
	errorEvent.setMessage(errorKey);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	dispatch.postEvent(errorEvent);
    }
}
