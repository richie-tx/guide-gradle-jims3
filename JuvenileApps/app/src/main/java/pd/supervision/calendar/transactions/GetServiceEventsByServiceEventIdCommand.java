package pd.supervision.calendar.transactions;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import naming.PDCalendarConstants;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import messaging.calendar.GetServiceEventsByServiceEventIdEvent;
import messaging.calendar.reply.ServiceAttendanceContextResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.contact.officer.OfficerProfile;
import pd.juvenile.Juvenile;
import pd.supervision.calendar.ServiceAttendanceContext;

public class GetServiceEventsByServiceEventIdCommand implements ICommand{

	public GetServiceEventsByServiceEventIdCommand() 
	{

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) {
		GetServiceEventsByServiceEventIdEvent reqEvent = (GetServiceEventsByServiceEventIdEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		Iterator iter = ServiceAttendanceContext.findAll(reqEvent);

		while(iter.hasNext()){
			ServiceAttendanceContext serviceAttendEvent = (ServiceAttendanceContext) iter.next();		
			ServiceAttendanceContextResponseEvent resp = new ServiceAttendanceContextResponseEvent();
			try {
				BeanUtils.copyProperties(resp, serviceAttendEvent);
				if(StringUtils.isNotEmpty(serviceAttendEvent.getJuvenileId()) && 
						!StringUtils.equals(serviceAttendEvent.getJuvenileId(), PDCalendarConstants.INVALID_CONTEXT_ID)){
				    JuvenileProfileDetailResponseEvent juvResp = Juvenile.findDetailJuvenile(serviceAttendEvent.getJuvenileId());
					if(juvResp != null){
						resp.setJuvenileFirstName(juvResp.getFirstName());
						resp.setJuvenileLastName(juvResp.getLastName());
						resp.setJuvenileMiddleName(juvResp.getMiddleName());
					}
				}
				if(StringUtils.isNotEmpty(serviceAttendEvent.getProbationOfficerId())&& 
						!StringUtils.equals(serviceAttendEvent.getProbationOfficerId(), PDCalendarConstants.INVALID_CONTEXT_ID)){
					OfficerProfile officerProfile = OfficerProfile.find(serviceAttendEvent.getProbationOfficerId());
					resp.setProbationOfficerLogonId((officerProfile != null) ? officerProfile.getLogonId() : "" );
					resp.setProbationOfficerFirstName(officerProfile.getFirstName());
					resp.setProbationOfficerMiddleName(officerProfile.getMiddleName());
					resp.setProbationOfficerLastName(officerProfile.getLastName());
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			dispatch.postEvent(resp);	 
		}		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}

}
