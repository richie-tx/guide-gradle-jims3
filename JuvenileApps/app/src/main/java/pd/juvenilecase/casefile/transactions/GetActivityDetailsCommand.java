/*
 * Created on Nov 28, 2006
 *
 */
package pd.juvenilecase.casefile.transactions;

import pd.juvenilecase.casefile.Activity;
import messaging.casefile.GetActivityDetailsEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author C_NAggarwal
 * 
 */
public class GetActivityDetailsCommand implements ICommand
{

    /**
     *  
     */
    public GetActivityDetailsCommand()
    {
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
     */
    public void execute(IEvent event)
    {
        GetActivityDetailsEvent request = (GetActivityDetailsEvent) event;

        Activity activity = Activity.find(request.getActivityId());
        ActivityResponseEvent response = activity.valueObject();
        MessageUtil.postReply(response);
        
        /*
         * IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY); String
         * categoryId = activity.getActivityCategoryId(); String typeId =
         * activity.getActivityTypeId(); String codeId = activity.getActivityCodeId();
         * 
         * ActivityResponseEvent response = new ActivityResponseEvent();
         * response.setActivityDate(activity.getActivityDate());
         * response.setActivityId(activity.getOID().toString());
         * response.setCategoryId(activity.getActivityCategoryId());
         * response.setTypeId(activity.getActivityTypeId());
         * response.setCodeId(activity.getActivityCodeId());
         * response.setComments(activity.getComments());
         * response.setCasefileId(activity.getSupervisionNumber()); dispatch.postEvent(response);
         */

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
     */
    public void onRegister(IEvent event)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
     */
    public void onUnregister(IEvent event)
    {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.context.ICommand#update(java.lang.Object)
     */
    public void update(Object updateObject)
    {
        // TODO Auto-generated method stub

    }
}
