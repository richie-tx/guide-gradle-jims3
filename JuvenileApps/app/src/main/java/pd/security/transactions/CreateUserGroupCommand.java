/*
 * Created on Jun 1, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.security.transactions;

import naming.PDSecurityConstants;
import pd.contact.agency.Agency;
import pd.security.PDSecurityHelper;
import messaging.security.CreateUserGroupEvent;
import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.security.UserGroup;

/**
 * @author dnikolis To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CreateUserGroupCommand implements ICommand
{
    /**
     * @roseuid 42972136015C
     */
    public CreateUserGroupCommand()
    {
    }

    /**
     * @param event
     * @roseuid 428B82BD00CE
     */
    public void execute(IEvent event)
    {
	CreateUserGroupEvent createEvent = (CreateUserGroupEvent) event;
	UserGroup userGroup = new UserGroup();
	/*this.setUserGroupFields(createEvent, userGroup);
	IHome home = new Home();
	home.bind(userGroup);*/
	//87191
	/*if (createEvent.getAgencyId() != null)
	{
		userGroup.addConstraint(createEvent.getAgencyId(), Agency.class);		
	}*///87191 Taken care on the SM side.			
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	/*UserGroupResponseEvent ugrEvent = PDSecurityHelper.getUserGroupResponseEvent(userGroup);
	dispatch.postEvent(ugrEvent);*///87191
    }

    private void setUserGroupFields(CreateUserGroupEvent updateEvent, UserGroup userGroup)
    {
	//87191
	/*userGroup.setDescription(updateEvent.getUserGroupDescription());
	userGroup.setName(updateEvent.getUserGroupName());
	userGroup.setCategory(updateEvent.getCategory());
	userGroup.setGroupType(PDSecurityConstants.USER_GROUP_TYPE_SECURITY);
	userGroup.setStatusId(PDSecurityConstants.USER_GROUP_STATUS_ACTIVE);
	userGroup.setCreateUserID(PDSecurityHelper.getLogonId());*///87191
    }

    /**
     * @param event
     * @roseuid 428B82BD00DA
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 428B82BD00DC
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 428B82BD00DE
     */
    public void update(Object anObject)
    {

    }
}