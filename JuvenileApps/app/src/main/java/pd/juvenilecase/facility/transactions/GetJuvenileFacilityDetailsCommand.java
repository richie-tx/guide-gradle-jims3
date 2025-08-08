package pd.juvenilecase.facility.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import messaging.casefile.reply.ActivityResponseEvent;
import messaging.facility.GetJuvenileFacilityDetailsEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.casefile.Activity;
import pd.juvenilewarrant.JJSPetition;


public class GetJuvenileFacilityDetailsCommand implements ICommand{

	
	/**
	 * Execute method
	 */
	public void execute(IEvent event)
	{
		GetJuvenileFacilityDetailsEvent facilityEvent = (GetJuvenileFacilityDetailsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator<JJSFacility> facilityItr = JJSFacility.findAll(facilityEvent);
		while (facilityItr.hasNext()) {
			JJSFacility fac = (JJSFacility)facilityItr.next();
			//RRY added value object
			JuvenileDetentionFacilitiesResponseEvent resp = fac.valueObject();
			//set the response of activities list to the array refer GetJuvenileCasefileReferralDetailsCommand
			//resp.setActivitiesByDetention(activitiesByDetention);
			List<ActivityResponseEvent> actList = new ArrayList<ActivityResponseEvent>();
			Iterator<Activity> actIter =  Activity.findAll("detentionId", fac.getOID()); //do an attribute  query to get the description 
			 
			while (actIter.hasNext())
			{
			    Activity act = actIter.next();
			    ActivityResponseEvent rep = act.valueObject();
			    actList.add(rep);
			}
			//bug fix for 149837
			Collections.sort((List<ActivityResponseEvent>)actList,Collections.reverseOrder(new Comparator<ActivityResponseEvent>() {
				@Override
				public int compare(ActivityResponseEvent evt1, ActivityResponseEvent evt2) {
					return Integer.valueOf(evt1.getActivityId()).compareTo(Integer.valueOf(evt2.getActivityId()));
				}
			}));
			//
			resp.setActivitiesByDetention(actList);
			dispatch.postEvent(resp);
		}
	}
	/**
	 * @param event
	 * @roseuid 42A70E2D02EC
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42A70E2D02EE
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42A70E2D02F0
	 */
	public void update(Object anObject)
	{

	}

}
