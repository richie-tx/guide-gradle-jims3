//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\DisplayJuvCreateUpdateServiceSummaryAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.administercontract.GetServiceContractsEvent;
import messaging.administerlocation.GetJuvenileLocationEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderErrorResponseEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.ServiceAttribute;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.common.CodeHelper;
import ui.juvenilecase.schedulecalendarevent.CalendarRetrieverFactory;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;

public class DisplayJuvServiceCreateUpdateSummaryAction extends LookupDispatchAction
{
	public DisplayJuvServiceCreateUpdateSummaryAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderForm providerForm = (ServiceProviderForm) aForm;

		ServiceProviderForm.Program.Service currentService = providerForm.getCurrentProgram().getProgramService();
		if (providerForm.getActionType().equals("createService"))
		{
			ServiceProviderErrorResponseEvent serviceError = UIServiceProviderHelper.validateServiceCode(providerForm);
			if (serviceError != null)
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(serviceError.getMessage(), "Duplicate name"));
				saveErrors(aRequest, errors);
				return aMapping.findForward("failure");
			}
		}
		if (!currentService.getUpdatedName().equals(currentService.getServiceName()))
		{
			ServiceProviderErrorResponseEvent serviceError = UIServiceProviderHelper.validateServiceName(providerForm);
			if (serviceError != null)
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(serviceError.getMessage(), "Duplicate name"));
				saveErrors(aRequest, errors);
				return aMapping.findForward("failure");
			}
			else
				currentService.setServiceName(currentService.getUpdatedName());
		}
		if (providerForm.getActionType().equals("updateService"))
		{
			ServiceProviderForm.Program.Service service = providerForm.getCurrentProgram().getProgramService();
			String[] strLocationNames = service.getLocationNames();
			ArrayList removedLocations = new ArrayList();
			if (service.getPreviousLocations() != null)
			{
				Iterator iter = service.getPreviousLocations().iterator();
				while (iter.hasNext())
				{
					LocationResponseEvent locUnit = (LocationResponseEvent) iter.next();
					boolean existLocation = false;
					if (strLocationNames != null)
					{
						for (int i = 0; i < strLocationNames.length; i++)
						{
							if (locUnit.getJuvLocationUnitId().equals(strLocationNames[i]))
							{
								existLocation = true;
								break;
							}
						}
						if (!existLocation)
						{
							removedLocations.add(locUnit);
						}
					}
				}
			}
			if (!removedLocations.isEmpty())
			{
				getFutureServiceEvents(providerForm, false, removedLocations);
			}
		}
		if (!providerForm.getCurrentProgram().getProgramService().getCost().equals(""))
			currentService.setDisplayCost("$" + currentService.getCost() + " /"
					+ CodeHelper.getCodeDescription("COST_BASIS", currentService.getCostBasisId()));

		else
			currentService.setDisplayCost("");
		if (providerForm.getServiceLocationNames() != null || providerForm.getServiceLocationNames().size() != 0)
			currentService.setLocationDescription(providerForm.getServiceLocationNames());
		if (!currentService.getLocationDescription().equals(""))
			currentService.setLocationString(currentService.getLocationDescription());
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward inactivate(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderForm sp = (ServiceProviderForm) aForm;
		if (sp.getServiceEvents() != null)
		{
			sp.setServiceEvents(new ArrayList());
		}
		getFutureServiceEvents(sp, true, null);
		sp.setContracts(getContracts(sp.getCurrentProgram().getProgramService().getServiceId()));
		sp.setActionType("inactivateService");
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	private static void getFutureServiceEvents(ServiceProviderForm sp, boolean fromInactivate,
			Collection removedLocations)
	{
		Collection attributes = new ArrayList();
		ServiceAttribute sa = new ServiceAttribute();
		sa.setServiceId(new Integer(sp.getCurrentProgram().getProgramService().getServiceId().trim()));
		attributes.add(sa);

		GetCalendarServiceEventsEvent gce = (GetCalendarServiceEventsEvent) EventFactory
				.getInstance(ServiceEventControllerServiceNames.GETCALENDARSERVICEEVENTS);
		gce.setRetriever(CalendarRetrieverFactory.getRetrieverName(CalendarRetrieverFactory.ACTIVEEVENTS_RETRIEVER));
		gce.setCalendarAttributes(attributes);
		gce.setStartDatetime(new Date());

		List events = MessageUtil.postRequestListFilter(gce, CalendarServiceEventResponseEvent.class);

		List eventsWithRemovedLocations = new ArrayList();
		if (events != null)
		{
			if (fromInactivate)
			{
				if (!events.isEmpty() && events != null)
				{
					setLocationName(events);
					sp.setServiceEvents(events);
				}
			}
			else
			{
				if (!removedLocations.isEmpty())
				{
					Iterator iterRemovedLocations = removedLocations.iterator();

					if (!events.isEmpty())
					{
						Iterator iterEvents = events.iterator();
						CalendarServiceEventResponseEvent[] arrayEvents = new CalendarServiceEventResponseEvent[events
								.size()];
						for (int i = 0; i < events.size(); i++)
						{
							arrayEvents[i] = (CalendarServiceEventResponseEvent) iterEvents.next();
						}

						while (iterRemovedLocations.hasNext())
						{
							LocationResponseEvent locationResp = (LocationResponseEvent) iterRemovedLocations.next();
							String removedLocationName = locationResp.getLocationUnitName().trim();
							for (int y = 0; y < arrayEvents.length; y++)
							{
								CalendarServiceEventResponseEvent eventResp = (CalendarServiceEventResponseEvent) arrayEvents[y];
								String eventLocationName = eventResp.getServiceLocationName().trim();
								if (removedLocationName.equalsIgnoreCase(eventLocationName))
								{
									eventsWithRemovedLocations.add(eventResp);
								}
							}
						}
					}
					if (!eventsWithRemovedLocations.isEmpty() && eventsWithRemovedLocations != null)
					{
						setLocationName(eventsWithRemovedLocations);
						sp.setServiceEvents(eventsWithRemovedLocations);
					}
				}
			}
		}
	}

	/**
	 * Method used to display location name along with for the corresponding events details in
	 * update and inactivate service summary screen
	 * 
	 * @param events
	 */
	private static void setLocationName(Collection events)
	{

		Iterator eventsIterator = events.iterator();
		while (eventsIterator.hasNext())
		{
			CalendarServiceEventResponseEvent calResp = (CalendarServiceEventResponseEvent) eventsIterator.next();
			String locationId = calResp.getLocationId();
			if (locationId != null && !locationId.equals(""))
			{
				LocationResponseEvent lre = null;
				GetJuvenileLocationEvent gslv = new GetJuvenileLocationEvent();
				gslv.setLocationId(locationId);
				lre = (LocationResponseEvent) MessageUtil.postRequest(gslv, LocationResponseEvent.class);
				calResp.setLocationName(lre.getLocationName());
			}
		}

	}

	private static Collection getContracts(String serviceId)
	{
		GetServiceContractsEvent sEvent = new GetServiceContractsEvent();
		sEvent.setServiceId(serviceId);	
		List currentContracts = MessageUtil.postRequestListFilter(sEvent, ContractResponseEvent.class);
		return currentContracts;

	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.inactivate", "inactivate");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}
}
