//Source file: C:\\views\\archproduction\\app\\src\\pd\\security\\transactions\\ActivateUserGroupCommand.java

package pd.security.transactions;

import pd.security.PDSecurityHelper;
import naming.PDSecurityConstants;
import messaging.security.ActivateUserGroupEvent;
import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.UserGroup;

public class ActivateUserGroupCommand implements ICommand
{

    /**
     * @roseuid 429721350360
     */
    public ActivateUserGroupCommand()
    {

    }

    /**
     * @param event
     * @roseuid 428B82BE0014
     */
    public void execute(IEvent event)
    {
	//87191
	/*IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	ActivateUserGroupEvent activateEvent = (ActivateUserGroupEvent) event;
	UserGroup userGroup = (UserGroup) UserGroup.find(activateEvent.getUserGroupId());

	if (userGroup != null)
	{
		userGroup.setStatusId(PDSecurityConstants.USER_GROUP_STATUS_ACTIVE);
		//create response event
		UserGroupResponseEvent ugrEvent = PDSecurityHelper.getUserGroupResponseEvent(userGroup);
		dispatch.postEvent(ugrEvent);
	}*///87191 taken care on the SM side.
    }

    /**
     * @param event
     * @roseuid 428B82BE0020
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 428B82BE0022
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 428B82BE0024
     */
    public void update(Object anObject)
    {

    }
}
