package ui.juvenilecase.workshopcalendar.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.ServiceLocationAttribute;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.juvenilecase.schedulecalendarevent.CalendarRetrieverFactory;
import ui.juvenilecase.workshopcalendar.form.CalendarEventListForm;

public class DisplayServiceEventListAction extends LookupDispatchAction
{

    /**
     * @author awidjaja
     * @roseuid 44805C3A0154
     */
    public DisplayServiceEventListAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49A50033
     */
    public ActionForward displayServiceEventList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CalendarEventListForm form = (CalendarEventListForm) aForm;
        GetCalendarServiceEventsEvent gce = new GetCalendarServiceEventsEvent();

        gce.setRetriever(CalendarRetrieverFactory.getRetrieverName(CalendarRetrieverFactory.PROVIDER_EVENTS_RETRIEVER));
        
        Calendar requestedDate = Calendar.getInstance();
        requestedDate.setTime(form.getEventDate());
        requestedDate.set(Calendar.HOUR_OF_DAY, 0);
        requestedDate.set(Calendar.MINUTE, 0);
        requestedDate.set(Calendar.SECOND, 0);
        requestedDate.set(Calendar.MILLISECOND, 0);

        gce.setStartDatetime(requestedDate.getTime());

        requestedDate.set(Calendar.HOUR_OF_DAY, 23);
        requestedDate.set(Calendar.MINUTE, 59);
        requestedDate.set(Calendar.SECOND, 59);
        requestedDate.set(Calendar.MILLISECOND, 0);

        gce.setEndDatetime(requestedDate.getTime());

        List attributes = new ArrayList();
        String locationId = aRequest.getParameter("locationId");
        if( locationId != null && (locationId.length() > 0) )
        {
            ServiceLocationAttribute locAttribute = new ServiceLocationAttribute();
            int locID = 0 ;
            try
            {
            	locID = Integer.parseInt(locationId) ;
            }
            catch( NumberFormatException nfe )
            { /*empty*/
            }

            locAttribute.setServiceLocationId( locID );
            attributes.add(locAttribute);
        }
        gce.setCalendarAttributes(attributes);  
        gce.setCalendarContextEvent(new CalendarContextEvent());

        List events = MessageUtil.postRequestListFilter(gce, CalendarServiceEventResponseEvent.class);

        Collections.sort(events);
        form.setDayEvents(events);

        return( aMapping.findForward(UIConstants.SUCCESS) );
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map buttonMap = new HashMap();
        buttonMap.put("button.back", "back");
        buttonMap.put("button.cancel", "cancel");
        buttonMap.put("button.displayServiceEventList", "displayServiceEventList");
        return buttonMap;
    }
}
