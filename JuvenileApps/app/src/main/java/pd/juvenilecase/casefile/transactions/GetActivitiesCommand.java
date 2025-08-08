package pd.juvenilecase.casefile.transactions;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.FastArrayList;
import org.apache.commons.collections.FastHashMap;

import messaging.casefile.GetActivitiesEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.codetable.criminal.JuvenileActivityTypeCode;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.casefile.Activity;

public class GetActivitiesCommand implements ICommand
{

    /**
     * @roseuid 452131F900B6
     */
    public GetActivitiesCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4521267802C6
     */
    public void execute(IEvent event)
    {
        GetActivitiesEvent request = (GetActivitiesEvent) event;

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        Map activityMap = this.buildActivityTypeMap();
        

        if (request.getJuvenileNum() != null)
        {
        	// find all casefiles for this juvenile
            Iterator iter = JuvenileCasefile.findAll("juvenileId", request.getJuvenileNum());
            
            while(iter.hasNext())
            {
                JuvenileCasefile juvenileCasefile = (JuvenileCasefile) iter.next();
                request.setCasefileID(juvenileCasefile.getOID());

                // find all activities for a casefile
                Iterator actIte = Activity.findAll(request);
                List activities = this.filterCategoryType(request, actIte, activityMap);
                int len = activities.size();
                for (int j = 0; j < len; j++)
                {
                	Activity activity = (Activity) activities.get(j);
                    ActivityResponseEvent response = activity.valueObject();
                    dispatch.postEvent(response);
                }
            }        	
        }
        else
        {
            Iterator iter = Activity.findAll(request);
            List activities = this.filterCategoryType(request, iter, activityMap);
            int len = activities.size();
            for (int j = 0; j < len; j++)
            {
                Activity activity = (Activity) activities.get(j);
                ActivityResponseEvent response = activity.valueObject(activityMap);
                dispatch.postEvent(response);
            }
        }
    }

    private Map buildActivityTypeMap()
    {
        Map activityMap = new FastHashMap();
        IHome home = new Home();
        Iterator i = home.findAll(JuvenileActivityTypeCode.class);
        while (i.hasNext())
        {
            JuvenileActivityTypeCode activityCode = (JuvenileActivityTypeCode) i.next();
            activityMap.put(activityCode.getCode(), activityCode);
        }
        return activityMap;
    }

    /**
     * @param request
     * @param activityMap
     * @param iter
     * @return
     */
    private List filterCategoryType(GetActivitiesEvent request, Iterator i, Map activityMap)
    {
        String categoryId = request.getCategoryId();
        boolean hasCategory = categoryId != null && categoryId.equals("") == false;
        String typeId = request.getActivityTypeId();
        boolean hasType = typeId != null && typeId.equals("") == false;

        List activities = new FastArrayList();

        while (i.hasNext())
        {
            Activity activity = (Activity) i.next();

            JuvenileActivityTypeCode activityCode = (JuvenileActivityTypeCode) activityMap.get(activity
                    .getActivityCodeId());

            if (hasCategory == true && hasType == true && activityCode!=null  &&categoryId.equals(activityCode.getCategoryId())
                    && typeId.equals(activityCode.getTypeId()))
            {
                activities.add(activity);
            }
            else if (hasCategory == true && hasType == false && activityCode!=null && categoryId.equals(activityCode.getCategoryId())) //facility null fix on activity code
            {
                activities.add(activity);
            }            
            else if (hasCategory == false && hasType == false)
            {
                activities.add(activity);
            }
        }

        return activities;
    }

    /**
     * @param event
     * @roseuid 4521267802C8
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4521267802D0
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 4521267802D2
     */
    public void update(Object anObject)
    {

    }

}
