/*
 * Created on Mar 1, 2006
 *
 */
package ui.taglib;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.jsp.JspTagException;
import messaging.administerserviceprovider.GetServicesByServiceProviderEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.GetDocketEventsEvent;
import messaging.calendar.GetOfficerAssociatedJuvenileCasefilesEvent;
import messaging.calendar.GetViewCalendarDocketEventsByJuvenilesEvent;
import messaging.calendar.ServiceAttribute;
import messaging.calendar.ServiceLocationAttribute;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.officer.GetOfficerProfilesByManagerEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import messaging.calendar.ICalendarEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.utilities.MessageUtil;
import ui.security.SecurityUIHelper;
import ui.taglib.calendar.CalendarTag;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCalendarConstants;
import naming.ServiceEventControllerServiceNames;
import naming.ServiceProviderControllerServiceNames;
import ui.juvenilecase.schedulecalendarevent.CalendarRetrieverFactory;

/**
 * @author Jim Fisher
 * 
 */
public class AppCalendarTag extends CalendarTag
{
	private String locationId;

	private String officerId;

	private String juvenileNum;
	
	private String caseFileId;

	private boolean needsRefresh;

	private boolean docketEventsNeeded;

	private String responseType;

	private String serviceProviderId;

	public int doAfterBody() throws JspTagException
	{
		// If a service event is specified, retrieve the events
		// for the presentation month and put them on the session
		if (this.isNeedsRefresh())
		{
			if (this.getServiceEvent() != null && !this.getServiceEvent().equals(""))
			{
				Calendar startCalDay = getStartCalendarDay();
				Calendar endCalDay = getEndCalendarDay();

				// Get the event to post for retrieving the Calendar Events. Any
				// event
				// that is set via the ServiceEvent attribute must extend
				// GetCalendarEvents
				ICalendarEvent gce = (ICalendarEvent) this.getInstantiatedServiceEvent();
				gce.setStartDatetime(startCalDay.getTime());
				gce.setEndDatetime(endCalDay.getTime());

				if (getResponseType() != null && getResponseType().equals("short"))
				{
					GetCalendarServiceEventsEvent e = (GetCalendarServiceEventsEvent) gce;
					e.setResponseType("short");
				}

				Collection attributes = new ArrayList();
				if (getLocationId() != null && !"".equals(getLocationId()))
				{
					ServiceLocationAttribute serviceLocationRequestEvent = new ServiceLocationAttribute();
					serviceLocationRequestEvent.setServiceLocationId(Integer.parseInt(getLocationId()));
					attributes.add(serviceLocationRequestEvent);
				}
				
				CalendarContextEvent calendarContextEvent = new CalendarContextEvent();
				
				if (getOfficerId() != null && !"".equals(getOfficerId()))
				{
					calendarContextEvent.setProbationOfficerId(getOfficerId());
				}
				if (getJuvenileNum() != null && !"".equals(getJuvenileNum()))
				{
					calendarContextEvent.setJuvenileId(getJuvenileNum());
				}
				if (getCaseFileId() != null && !"".equals(getCaseFileId()))
				{
					calendarContextEvent.setCaseFileId(getCaseFileId());
				}

				if (this.getSessionAttributeName() != null
						&& !this.getSessionAttributeName().equals(PDCalendarConstants.CALENDAR_TYPE_PROVIDER))
				{
					gce.setRetriever(CalendarRetrieverFactory
							.getRetrieverName(CalendarRetrieverFactory.ACTIVEEVENTS_RETRIEVER));
				}

				//If logged in as CLM, this if statement gathers all JPOs that report to that CLM.
				//A CLM does not have calendar events attached to him/her. In order to find out which calendar events
				//are related to a CLM, those JPOs who report to the CLM are gathered and stored.
				if (this.getSessionAttributeName() != null
						&& this.getSessionAttributeName().equals(PDCalendarConstants.CALENDAR_TYPE_CLM))
				{
					if (getOfficerId() != null && !"".equals(getOfficerId()))
					{
						GetOfficerProfilesByManagerEvent offEvent = (GetOfficerProfilesByManagerEvent) EventFactory
								.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILESBYMANAGER);
						offEvent.setManagerId(getOfficerId());

						List officerResponses = MessageUtil.postRequestListFilter(offEvent,
								OfficerProfileResponseEvent.class);

						if (officerResponses == null)
						{
							return SKIP_BODY;
						}
						else
						{
							String[] officerIds = new String[officerResponses.size()];
							
							int index = 0;
							for ( Iterator iter = officerResponses.iterator(); iter.hasNext(); )
							{
								OfficerProfileResponseEvent offResp = (OfficerProfileResponseEvent) iter
										.next();
								officerIds[index] = offResp.getOfficerProfileId();
								index++;
							}
							calendarContextEvent.setCLMProbationOfficerIds(officerIds);
						}
					}
				}

				if (this.getSessionAttributeName() != null
						&& this.getSessionAttributeName().equals(PDCalendarConstants.CALENDAR_TYPE_PROVIDER))
				{
					gce.setRetriever(CalendarRetrieverFactory
							.getRetrieverName(CalendarRetrieverFactory.PROVIDER_EVENTS_RETRIEVER));
					if (serviceProviderId != null && !"".equals(serviceProviderId))
					{
						GetServicesByServiceProviderEvent serEvent = (GetServicesByServiceProviderEvent) EventFactory
								.getInstance(ServiceProviderControllerServiceNames.GETSERVICESBYSERVICEPROVIDER);
						serEvent.setServiceProviderId(serviceProviderId);

						List serviceResponses = MessageUtil.postRequestListFilter(serEvent, ServiceResponseEvent.class);

						if (serviceResponses == null)
						{
							return SKIP_BODY;
						}
						else
						{
							Iterator serviceIterator = serviceResponses.iterator();
							while (serviceIterator.hasNext())
							{
								ServiceResponseEvent servResp = (ServiceResponseEvent) serviceIterator.next();
								ServiceAttribute sa = new ServiceAttribute();
								sa.setServiceId(new Integer(servResp.getServiceId()));
								attributes.add(sa);
							}
						}
					}
				}

				if (calendarContextEvent != null)
				{
					gce.setCalendarContextEvent(calendarContextEvent);
				}

				if (attributes != null && attributes.size() >= 1)
				{
					gce.setCalendarAttributes(attributes);
				}

				List events = MessageUtil.postRequestListFilter((RequestEvent) gce,
						CalendarServiceEventResponseEvent.class);

				if (this.isDocketEventsNeeded() && getJuvenileNum() != null && !"".equals(getJuvenileNum()))
				{
					GetDocketEventsEvent getDocketEventsEvent = (GetDocketEventsEvent) EventFactory
							.getInstance(ServiceEventControllerServiceNames.GETDOCKETEVENTS);
					getDocketEventsEvent.setJuvenileId(getJuvenileNum());
					getDocketEventsEvent.setStartDate(startCalDay.getTime());
					getDocketEventsEvent.setEndDate(endCalDay.getTime());

					List docketEvents = MessageUtil.postRequestListFilter(getDocketEventsEvent,
							DocketEventResponseEvent.class);
					if (docketEvents != null)
					{
						events.addAll(docketEvents);
					}
				}

				if (this.getSessionAttributeName() != null && !this.getSessionAttributeName().equals(""))
				{
					setSessionAttributeValue(events);
				}
				
				//<KISHORE> JIMS200057010 : Display docket events on JPO view calendar-UI (KK)
				if(PDCalendarConstants.CALENDAR_TYPE_JPO.equalsIgnoreCase(this.getSessionAttributeName())){
					String jpoUserId = SecurityUIHelper.getJIMSLogonId();
					events.addAll(getDocketEventsByJPO(jpoUserId,startCalDay,endCalDay));
				}
				setCalendarEvents(events);
			}
		}
		else
		{
			Collection events = getSessionAttributeValue();
			setCalendarEvents(events);
		}

		return SKIP_BODY;
	}

	public List getDocketEventsByJPO(String jpoUserId,Calendar startDate,Calendar endDate){
		
		List docketEventsList = new ArrayList();

		//Get Juveniles associated with JPO
		GetOfficerAssociatedJuvenileCasefilesEvent oajc = (GetOfficerAssociatedJuvenileCasefilesEvent)
		EventFactory.getInstance( ServiceEventControllerServiceNames.GETOFFICERASSOCIATEDJUVENILECASEFILES ) ;
		
		oajc.setJpoUserId(jpoUserId) ;
		
		CompositeResponse response = MessageUtil.postRequest( oajc ) ; 
		oajc = null ;
		
		ErrorResponseEvent errResp = (ErrorResponseEvent)MessageUtil.filterComposite( response, ErrorResponseEvent.class ) ; 
		if( errResp == null ) 
		{
			if( !response.hasResponses() )
			{ 
				return docketEventsList;
			}				
			List allJuvCasefileResponses = MessageUtil.compositeToList( response, JuvenileCasefileResponseEvent.class ) ;
			if( allJuvCasefileResponses != null )
			{
				HashMap juvCasefilesIndex = new HashMap();
				{ 
					List indexJuvCaseFileResponses = new ArrayList();
					int pageIndex = 1, listSize = allJuvCasefileResponses.size();
					for( int i = 0; i < listSize; i++ )
					{
						JuvenileCasefileResponseEvent juvCaseFileResp = (JuvenileCasefileResponseEvent)allJuvCasefileResponses.get(i);
						indexJuvCaseFileResponses.add(juvCaseFileResp);

						if( (indexJuvCaseFileResponses.size() == 30) || ( (i + 1) == listSize ) ) 
						{ 
							juvCasefilesIndex.put(pageIndex++, indexJuvCaseFileResponses);
							indexJuvCaseFileResponses = new ArrayList();
						}					
					}
				}
				int listSize = juvCasefilesIndex.size() ;
				for( int i = 0; i < listSize; i++ ) 
				{
					List juvCasefileResponses = (List)juvCasefilesIndex.get(i + 1);

					GetViewCalendarDocketEventsByJuvenilesEvent gvevByJuveniles = (GetViewCalendarDocketEventsByJuvenilesEvent)
					EventFactory.getInstance( ServiceEventControllerServiceNames.GETVIEWCALENDARDOCKETEVENTSBYJUVENILES ) ;

					gvevByJuveniles.setJuvCasefileResponses(juvCasefileResponses) ;
					gvevByJuveniles.setStartDate(startDate.getTime()) ;
					gvevByJuveniles.setEndDate(endDate.getTime()) ;

					response = MessageUtil.postRequest( gvevByJuveniles ) ;
					errResp = (ErrorResponseEvent)MessageUtil.filterComposite( response, ErrorResponseEvent.class ) ;

					if( errResp == null )
					{
						List docketResponses = MessageUtil.compositeToList( response, DocketEventResponseEvent.class ) ;
						for( Iterator j = docketResponses.iterator(); j.hasNext(); )  
						{
							docketEventsList.add( j.next() );
						}
					}
				}
			}
		}
		return docketEventsList;
	}

	public void clearSessionAttributeValue()
	{
	}

	/**
	 * @return
	 */
	public String getLocationId()
	{
		return locationId;
	}

	/**
	 * @param string
	 */
	public void setLocationId(String string)
	{
		locationId = string;
	}

	/**
	 * @return Returns the needsRefresh.
	 */
	public boolean isNeedsRefresh()
	{
		return needsRefresh;
	}

	/**
	 * @param needsRefresh
	 *            The needsRefresh to set.
	 */
	public void setNeedsRefresh(boolean needsRefresh)
	{
		this.needsRefresh = needsRefresh;
	}

	/**
	 * @return Returns the officerId.
	 */
	public String getOfficerId()
	{
		return officerId;
	}

	/**
	 * @param officerId
	 *            The officerId to set.
	 */
	public void setOfficerId(String officerId)
	{
		this.officerId = officerId;
	}

	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param juvenileNum
	 *            The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum)
	{
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return Returns the responseType.
	 */
	public String getResponseType()
	{
		return responseType;
	}

	/**
	 * @param responseType
	 *            The responseType to set.
	 */
	public void setResponseType(String responseType)
	{
		this.responseType = responseType;
	}

	/**
	 * @return Returns the serviceProviderId.
	 */
	public String getServiceProviderId()
	{
		return serviceProviderId;
	}

	/**
	 * @param serviceProviderId
	 *            The serviceProviderId to set.
	 */
	public void setServiceProviderId(String serviceProviderId)
	{
		this.serviceProviderId = serviceProviderId;
	}

	/**
	 * @return Returns the docketEventsNeeded.
	 */
	public boolean isDocketEventsNeeded()
	{
		return docketEventsNeeded;
	}

	/**
	 * @param docketEventsNeeded
	 *            The docketEventsNeeded to set.
	 */
	public void setDocketEventsNeeded(boolean docketEventsNeeded)
	{
		this.docketEventsNeeded = docketEventsNeeded;
	}
	
	/**
	 * @return caseFileId
	 */
	public String getCaseFileId() {
		return caseFileId;
	}

	/**
	 * @param caseFileId
	 */
	public void setCaseFileId(String caseFileId) {
		this.caseFileId = caseFileId;
	}
	
}
