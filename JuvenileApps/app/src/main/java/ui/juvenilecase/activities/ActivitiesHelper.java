/*
 * Created on Sep 4, 2007
 *
 */
package ui.juvenilecase.activities;

import java.util.List;

import naming.JuvenileCasefileControllerServiceNames;

import messaging.casefile.GetActivitiesEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;

/**
 * @author jjose
 * 
 */
public class ActivitiesHelper
{

    /*
     * If JuvId is null all juveniles will be searched If any field is null all matches in that
     * field are searched
     */
    public static List getActivities(String aCasefileID, String aActivityTypeId, String aCategoryId,
            String aActivityCodeId, String aJuvId)
    {
        GetActivitiesEvent evt = 
			(GetActivitiesEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.GETACTIVITIES);
        evt.setCasefileID(aCasefileID);
        evt.setJuvenileNum(aJuvId);
        evt.setActivityCodeId(aActivityCodeId);
        evt.setActivityTypeId(aActivityTypeId);
        evt.setCategoryId(aCategoryId);
        
        List results = MessageUtil.postRequestListFilter(evt, ActivityResponseEvent.class);
        return results;

    }
}
