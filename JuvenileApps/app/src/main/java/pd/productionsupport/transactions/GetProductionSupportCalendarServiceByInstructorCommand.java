package pd.productionsupport.transactions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import pd.supervision.administerserviceprovider.Service;
import pd.supervision.calendar.ServiceEvent;
import pd.supervision.calendar.ServiceEventContext;
import messaging.calendar.GetServContextByServiceEventIdEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.productionsupport.GetProductionSupportCalendarServiceByInstructorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

public class GetProductionSupportCalendarServiceByInstructorCommand implements ICommand 
{
    public void execute(IEvent event){
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetProductionSupportCalendarServiceByInstructorEvent getEvent = (GetProductionSupportCalendarServiceByInstructorEvent)event;
	 
	if (getEvent.getFromStartDate() != null
		&& getEvent.getFromStartDate().length() > 0 
		&& getEvent.getToStartDate()!= null
		&& getEvent.getToStartDate().length() > 0 ){
	    List<String>serviceEventIds = new ArrayList<>();
	    Date fromStartDate = DateUtil.stringToDate( getEvent.getFromStartDate(), DateUtil.DATE_FMT_1 ) ;
	    Date toStartDate = DateUtil.stringToDate( getEvent.getToStartDate(), DateUtil.DATE_FMT_1 ) ;
	    
	    Iterator servContexIter = ServiceEventContext.findAll("instructorId", getEvent.getInstructorId() );
	    while ( servContexIter.hasNext() ) {
		ServiceEventContext servContext = (ServiceEventContext) servContexIter.next();
		if ( ( servContext.getStartDatetime().compareTo(fromStartDate) > 0
			|| servContext.getStartDatetime().compareTo(fromStartDate) == 0)
			&&  ( servContext.getStartDatetime().compareTo(toStartDate) < 0
				|| servContext.getStartDatetime().compareTo(toStartDate) == 0 )  ){
		    if (!serviceEventIds.contains( servContext.getServiceEventId() ) ){
			 CalendarServiceEventResponseEvent response =  new CalendarServiceEventResponseEvent();
			 serviceEventIds.add(servContext.getServiceEventId());
			 response.setStartDatetime(servContext.getStartDatetime());
			 response.setServiceEventId(servContext.getServiceEventId());
			 
			 Service service = Service.find( Integer.toString(servContext.getServiceId() ));
			 if ( service != null ) {
			     response.setServiceName( service.getServiceName() );
			 }
			 
			
			 
			 dispatch.postEvent(response);
			 
		    }
		   
		}
	    }
	    
	    
	} else {
	   
	    Iterator serviceIter = ServiceEvent.findAll("instructorId", getEvent.getInstructorId());
		 
	    while ( serviceIter.hasNext() ) {
		    ServiceEvent serviceResp = (ServiceEvent) serviceIter.next();
		    List<ServiceEventContext>serviceContexts = new ArrayList<>();
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime( new Date() );
		    String previousMonth = Integer.toString( calendar.get(Calendar.MONTH) );
		    String year = Integer.toString( calendar.get(Calendar.YEAR) );
		    
		    if ( calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
			year = Integer.toString( calendar.get(Calendar.YEAR) -1 );
			previousMonth = Integer.toString( Calendar.DECEMBER );
		    } 
		  
		    if ( serviceResp.getCreateTimestamp().compareTo( DateUtil.stringToDate(previousMonth + "/01/" + year, DateUtil.DATE_FMT_1)) > 0 ){
	        	    CalendarServiceEventResponseEvent response = serviceResp.getCalendarResponseEvent();
	        	    Service service = Service.find(serviceResp.getServiceId());
	        	    if ( service != null ) {
	        		response.setServiceName( service.getServiceName() );
	        	    }
	        	    
	        	   // Iterator servContexIter = ServiceEventContext.findAll("serviceEventId", response.getServiceEventId());
	        	    GetServContextByServiceEventIdEvent getServContextEvent = new GetServContextByServiceEventIdEvent();
	        	    getServContextEvent.setServiceEventId( response.getServiceEventId() );
	        	    Iterator servContexIter = ServiceEventContext.findAll(getServContextEvent);
	        	    while ( servContexIter.hasNext() ) {
	        		ServiceEventContext servContext = (ServiceEventContext) servContexIter.next();
	        		serviceContexts.add(servContext);
	        	    }
	        	    
	        	    if ( serviceContexts != null
	        		    && serviceContexts.size() > 0 ) {
	        		response.setServiceContexts(serviceContexts);
	        		response.setStartDatetime( serviceContexts.get(0).getStartDatetime() );
	        	    }
	        	   
	        	   // if ( response.getStartDatetime() != null ) {
	        		dispatch.postEvent(response);
	        	   // }
		   }
		    
		}
	}
	
    }

}
