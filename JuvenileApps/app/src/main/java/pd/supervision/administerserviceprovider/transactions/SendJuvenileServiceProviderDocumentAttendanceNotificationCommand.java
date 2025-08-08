package pd.supervision.administerserviceprovider.transactions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import naming.PDCalendarConstants;
import naming.PDConstants;
import naming.PDTaskConstants;
import pd.codetable.criminal.JuvenileEventTypeCode;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.ServiceProvider;
import pd.supervision.calendar.ServiceEvent;
import pd.supervision.calendar.ServiceEventAttendance;
import pd.task.Task;
import messaging.administerserviceprovider.SendJuvenileServiceProviderDocumentAttendanceNotificationEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.util.DateUtil;

/**
 * @author dgibler
 * This notification is sent if attendance has not been documented within 5 days of service provider event date.
 */
public class SendJuvenileServiceProviderDocumentAttendanceNotificationCommand implements
		ICommand {

	public void execute(IEvent event) throws Exception {
		SendJuvenileServiceProviderDocumentAttendanceNotificationEvent reqEvent = (SendJuvenileServiceProviderDocumentAttendanceNotificationEvent) event;

		ServiceEvent serviceEvent = (ServiceEvent) ServiceEvent.find(reqEvent.getServiceEventId());
		
		if (serviceEvent != null 
				&& (serviceEvent.getEventStatusId().equals(PDCalendarConstants.SERVICE_EVENT_STATUS_AVAILABLE)
						|| serviceEvent.getEventStatusId().equals(PDCalendarConstants.SERVICE_EVENT_STATUS_SCHEDULED))){
			
			List <ServiceEventAttendance> attendees = 
				CollectionUtil.iteratorToList(
						ServiceEventAttendance.findAll("serviceEventId", reqEvent.getServiceEventId()));
			
			ServiceEventAttendance sea = null;
			boolean attendenceShouldBeDocumented = false;
			
			for (int i = 0; i < attendees.size(); i++) {
				sea = attendees.get(i); 
				if (sea.getAttendanceStatusCd() != null 
						&& (sea.getAttendanceStatusCd().equals(PDCalendarConstants.JUV_ATTEND_STATUS_CONFIRMED)
						||sea.getAttendanceStatusCd().equals(PDCalendarConstants.JUV_ATTEND_STATUS_UNCONFIRMED))){
					attendenceShouldBeDocumented = true;
					break;
				}
			}
			
			if (attendenceShouldBeDocumented){
				HashMap map = new HashMap();
	            map.put("submitAction", "View Details");
	            map.put("eventId", reqEvent.getServiceEventId());
	            
	            ServiceProvider sp = (ServiceProvider) JuvenileServiceProvider.find(reqEvent.getServiceProviderId());
	           
	            String adminUserId = PDConstants.BLANK;
	            String contactUserId = PDConstants.BLANK;
	            
	            if (sp != null && sp.getAdminUserProfileId() != null){
	            	adminUserId = sp.getAdminUserProfileId();
	            } 
	            
	            if (sp != null && sp.getContactUserProfileId() != null){
	            	contactUserId = sp.getContactUserProfileId();
	            } 
	
	            Task.createTask(adminUserId, PDTaskConstants.SP_DOCUMENT_ATTENDANCE_TASK,
	            		this.getTaskTitle(serviceEvent.getEventType(), reqEvent.getEventDate()), map);
	            
	            if (!contactUserId.equals(adminUserId) && !contactUserId.equals(PDConstants.BLANK)){
	                Task.createTask(contactUserId, PDTaskConstants.SP_DOCUMENT_ATTENDANCE_TASK,
	                		this.getTaskTitle(serviceEvent.getEventType(), reqEvent.getEventDate()), map);
	            }
	            
	            map.clear();
	            map = null;
	            adminUserId = null;
	            contactUserId = null;
			}
			
			attendees = null;
			sea = null;
		}
		
		reqEvent = null;
		serviceEvent = null;
		
	}
	   private String getTaskTitle(JuvenileEventTypeCode eventType, Date eventDate) {
		   
		   StringBuffer title = new StringBuffer("Attendance needs to be documented for ");
		   title.append(eventType.getDescription());
		   title.append(" on ");
		   title.append(DateUtil.dateToString(eventDate, DateUtil.DATE_FMT_1));
		   
		   return title.toString();
	   }

}
