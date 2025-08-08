//Source file: C:\\views\\Security\\app\\src\\pd\\security\\transactions\\GetUserGroupSecurityInfoCommand.java

package pd.security.inquiries.transactions;

import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class GetUserGroupSecurityInfoCommand implements ICommand
{

    /**
     * @roseuid 44E9D2A10300
     */
    public GetUserGroupSecurityInfoCommand()
    {

    }

    /**
     * @param event
     * @roseuid 44E9B4B00226
     */
    public void execute(IEvent event)
    {
	//87191
	/*IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetUserGroupSecurityInfoEvent gGetUserGroupSecurityInfo = (GetUserGroupSecurityInfoEvent) event;
	UserGroup userGroup = UserGroup.find(gGetUserGroupSecurityInfo.getUserGroupId());
		
	if(userGroup != null){
	   ResponseContextFactory respFac = new ResponseContextFactory();
	    ResponseCreator ugCreator =  null;
	   try {
		   ugCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.USER_GROUP_SECURITY_RESPONSE_LOCATOR);
	   } catch (ReflectionException e) {
		   e.printStackTrace();
	   }
	   UserGroupSecurityInfoResponseEvent resp = (UserGroupSecurityInfoResponseEvent) ugCreator.create(userGroup);
	   dispatch.postEvent(resp);
	}*///87191
    }

    /**
     * @param event
     * @roseuid 44E9B4B00228
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 44E9B4B002C1
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 44E9B4B002C3
     */
    public void update(Object anObject)
    {

    }
}
