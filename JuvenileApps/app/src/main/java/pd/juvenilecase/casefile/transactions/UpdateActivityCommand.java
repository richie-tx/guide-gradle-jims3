//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\casefile\\transactions\\CreateActivityCommand.java

package pd.juvenilecase.casefile.transactions;

import pd.juvenilecase.casefile.Activity;
import messaging.casefile.UpdateActivityEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class UpdateActivityCommand implements ICommand
{

    /**
     * @param event
     * @roseuid 4521267803A5
     */
    public void execute(IEvent event)
    {
	UpdateActivityEvent request = (UpdateActivityEvent) event;

	Activity obj = Activity.find(request.getActivityId());

	if (obj != null)
	{

	    obj.setUpdateComments(request.getUpdateComments());
	}
    }

    /**
     * @param event
     * @roseuid 4521267803AD
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4521267803AF
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 452131F10168
     */
    public void update(Object updateObject)
    {

    }
}
