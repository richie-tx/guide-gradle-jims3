//Source file: C:\\views\\archproduction\\app\\src\\pd\\security\\transactions\\UpdateUserGroupCommand.java

package pd.security.transactions;

import java.util.Iterator;
import pd.contact.agency.Agency;
import messaging.security.UpdateUserGroupEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.security.Constraint;
import mojo.km.security.UserGroup;

public class UpdateUserGroupCommand implements ICommand
{

	/**
	 * @roseuid 42972136015C
	 */
	public UpdateUserGroupCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 428B82BD00CE
	 */
	public void execute(IEvent event)
	{
		UpdateUserGroupEvent updateEvent = (UpdateUserGroupEvent) event;
		/*UserGroup userGroup = UserGroup.find(updateEvent.getUserGroupId());
		this.setUserGroupFields(updateEvent, userGroup);
		this.updateUserGroupConstraints(updateEvent, userGroup);*/ //87191

	}
	private void setUserGroupFields(UpdateUserGroupEvent updateEvent, UserGroup userGroup)
	{
		/*userGroup.setDescription(updateEvent.getUserGroupDescription());
		userGroup.setName(updateEvent.getUserGroupName());*/ //87191
	}

	private void updateUserGroupConstraints(UpdateUserGroupEvent updateEvent, UserGroup userGroup)
	{
	    //87191
/*		//Retrieve the constraints currently associated to user group			
		Iterator currentAgencies = userGroup.getConstraints().iterator();
		String currentAgency = null;
		Constraint constraint = null;
		while (currentAgencies.hasNext())
		{
			constraint = ((Constraint) currentAgencies.next());
			currentAgency = constraint.getConstrainerId();
		}

		if (currentAgency != null)
		{
			if (!currentAgency.equals(updateEvent.getAgencyId()))
			{
				userGroup.removeConstraint(constraint.getConstrainerId(), Agency.class);
				userGroup.addConstraint(updateEvent.getAgencyId(), Agency.class);
			}
		}
		else
		{
			String newAgency = updateEvent.getAgencyId();
			if (newAgency != null && !newAgency.equals(""))
			{
				userGroup.addConstraint(newAgency, Agency.class);
			}
		}*/ //87191
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