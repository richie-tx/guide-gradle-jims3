/*
 * Created on Apr 30, 2004
 *
 */
package mojo.km.utilities;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import mojo.km.config.PropertyBundleProperties;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.messaging.Composite.CompositeRequest;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.tools.code.KeyWord;

/**
 * @author Jim Fisher
 * 
 */
public class MessageUtil
{

	/**
	 * Converts a Collection into a CompositeRequest.
	 * 
	 * @param event
	 * @param filterClass
	 * @return a CompositeRequest event
	 */
	public static CompositeRequest collectionToComposite(Collection collection)
	{
		CompositeRequest event = new CompositeRequest();
		Iterator collectionIterator = collection.iterator();
		while (collectionIterator.hasNext())
		{
			event.addRequest((IEvent) collectionIterator.next());
		}
		return event;
	}

	/**
	 * Converts responses in a CompositeResponse into a Collection
	 * 
	 * @param event
	 *            the CompositeResponse of events to convert
	 * @return collection of events
	 */
	public static Collection compositeToCollection(CompositeRequest event)
	{
		Collection eventCollection = new ArrayList();
		Enumeration eventEnum = event.getRequests();
		while (eventEnum.hasMoreElements())
		{
			Object eventObj = eventEnum.nextElement();
			eventCollection.add(eventObj);
		}

		return eventCollection;
	}

	/**
	 * Converts responses in a CompositeResponse into a Collection. This method also has the added
	 * benefit of filtering on a particular event class out of the CompositeResponse event.
	 * 
	 * @param event
	 *            the CompositeResponse of events to convert
	 * @param eventClass
	 *            the class to filter the Collection
	 * @return collection of response events
	 */
	public static Collection compositeToCollection(CompositeRequest event, Class eventClass)
	{
		Collection eventCollection = new ArrayList();
		Enumeration eventEnum = event.getRequests();
		while (eventEnum.hasMoreElements())
		{
			Object eventObj = eventEnum.nextElement();

			/*
			 * This check is necessary in case the response enumeration contains unwanted events
			 * (i.e. TerminationEvent).
			 */
			if (eventClass.isInstance(eventObj))
			{
				eventCollection.add(eventObj);
			}
		}

		return eventCollection;
	}

	/**
	 * Converts responses in a CompositeResponse into a Collection
	 * 
	 * @param event
	 *            the CompositeResponse of events to convert
	 * @return collection of events
	 */
	public static Collection compositeToCollection(CompositeResponse event)
	{
		return event.getResponses();
	}

	/**
	 * Converts responses in a CompositeResponse into a Collection. This method also has the added
	 * benefit of filtering on a particular event class out of the CompositeResponse event.
	 * 
	 * @param event
	 *            the CompositeResponse of events to convert
	 * @param eventClass
	 *            the class to filter the Collection
	 * @return collection of response events
	 */
	public static Collection compositeToCollection(CompositeResponse event, Class eventClass)
	{
		Collection eventCollection = new ArrayList();
		List events = event.getResponses();
		int len = events.size();
		for (int i = 0; i < len; i++)
		{
			Object eventObj = events.get(i);

			/*
			 * This check is necessary in case the response enumeration contains unwanted events
			 * (i.e. TerminationEvent).
			 */
			if (eventClass.isInstance(eventObj))
			{
				eventCollection.add(eventObj);
			}
		}

		return eventCollection;
	}

	/**
	 * Converts a CompositeResponse into a Collection while applying a filter on a particular topic.
	 * 
	 * @param event
	 * @param topic
	 * @return the filtered event
	 */
	public static Collection compositeToCollection(CompositeResponse anEvent, String topic)
	{
		Collection eventCollection = new ArrayList();
		List events = anEvent.getResponses();
		int len = events.size();
		for (int i = 0; i < len; i++)
		{
			IEvent event = (IEvent) events.get(i);

			if (event.getTopic().equals(topic))
			{
				eventCollection.add(event);
			}
		}

		return eventCollection;
	}

	/**
	 * Converts responses in a CompositeResponse into a Collection
	 * 
	 * @param event
	 *            the CompositeResponse of events to convert
	 * @return collection of events
	 */
	public static List compositeToList(CompositeRequest event)
	{
		List eventCollection = new ArrayList();
		Enumeration eventEnum = event.getRequests();
		while (eventEnum.hasMoreElements())
		{
			Object eventObj = eventEnum.nextElement();
			eventCollection.add(eventObj);
		}

		return eventCollection;
	}

	/**
	 * Converts responses in a CompositeResponse into a Collection. This method also has the added
	 * benefit of filtering on a particular event class out of the CompositeResponse event.
	 * 
	 * @param event
	 *            the CompositeResponse of events to convert
	 * @param eventClass
	 *            the class to filter the Collection
	 * @return collection of response events
	 */
	public static List compositeToList(CompositeRequest event, Class eventClass)
	{
		List eventCollection = new ArrayList();
		Enumeration eventEnum = event.getRequests();
		while (eventEnum.hasMoreElements())
		{
			Object eventObj = eventEnum.nextElement();

			/*
			 * This check is necessary in case the response enumeration contains unwanted events
			 * (i.e. TerminationEvent).
			 */
			if (eventClass.isInstance(eventObj))
			{
				eventCollection.add(eventObj);
			}
		}

		return eventCollection;
	}

	/**
	 * Converts responses in a CompositeResponse into a Collection
	 * 
	 * @param event
	 *            the CompositeResponse of events to convert
	 * @return collection of events
	 */
	public static List compositeToList(CompositeResponse event)
	{
		return event.getResponses();
	}

	/**
	 * Converts responses in a CompositeResponse into a Collection. This method also has the added
	 * benefit of filtering on a particular event class out of the CompositeResponse event.
	 * 
	 * @param event
	 *            the CompositeResponse of events to convert
	 * @param eventClass
	 *            the class to filter the Collection
	 * @return collection of response events
	 */
	public static List compositeToList(CompositeResponse anEvent, Class eventClass)
	{
		List eventCollection = new ArrayList();
		List events = anEvent.getResponses();
		int len = events.size();
		for (int i = 0; i < len; i++)
		{
			Object eventObj = events.get(i);

			if (eventClass.isInstance(eventObj))
			{
				eventCollection.add(eventObj);
			}
		}

		return eventCollection;
	}

	/**
	 * Converts a CompositeResponse into a Collection while applying a filter on a particular topic.
	 * 
	 * @param event
	 * @param topic
	 * @return the filtered event
	 */
	public static List compositeToList(CompositeResponse anEvent, String topic)
	{
		List eventCollection = new ArrayList();
		List events = anEvent.getResponses();
		int len = events.size();
		for (int i = 0; i < len; i++)
		{
			IEvent event = (IEvent) events.get(i);

			if (event.getTopic().equals(topic))
			{
				eventCollection.add(event);
			}
		}

		return eventCollection;
	}

	/**
	 * Filters events from a particular class type out of a CompositeRequest
	 * 
	 * @param event
	 * @param filterClass
	 * @return the filtered event
	 */
	public static IEvent filterComposite(CompositeRequest event, Class filterClass)
	{
		IEvent object = null;
		Enumeration eventEnum = event.getRequests();
		while (eventEnum.hasMoreElements())
		{
			Object eventObj = eventEnum.nextElement();
			if (filterClass.isInstance(eventObj))
			{
				object = (IEvent) eventObj;
				break;
			}
		}

		return object;
	}

	/**
	 * Filters events from a particular class type out of a CompositeResponse
	 * 
	 * @param event
	 * @param filterClass
	 * @return the filtered event
	 */
	public static IEvent filterComposite(CompositeResponse anEvent, Class filterClass)
	{
		IEvent object = null;
		List responses = anEvent.getResponses();
		int len = responses.size();
		for (int i = 0; i < len; i++)
		{
			Object eventObj = responses.get(i);
			if (filterClass.isInstance(eventObj))
			{
				object = (IEvent) eventObj;
				break;
			}
		}

		return object;
	}

	/**
	 * Filters events from a particular class type out of a CompositeRequest
	 * 
	 * @param event
	 * @param topic
	 * @return the filtered event
	 */
	public static IEvent filterComposite(CompositeResponse anEvent, String topic)
	{
		IEvent retEvent = null;
		List responses = anEvent.getResponses();
		int len = responses.size();
		for (int i = 0; i < len; i++)
		{
			IEvent evt = (IEvent) responses.get(i);
			if (evt.getTopic().equals(topic))
			{
				retEvent = evt;
				break;
			}
		}

		return retEvent;
	}

	/**
	 * Acquires a request dispatch.
	 * 
	 * @return a shared instance of the request dispatch strategy
	 */
	public static IDispatch getRequestDispatch()
	{
		return EventManager.getSharedInstance(EventManager.REQUEST);
	}

	/**
	 * Groups events by topic into separate collections of objects.
	 * 
	 * @param compEvent
	 *            the Composite transfer object
	 * @return a Map of transfer object Collections keyed by the topic name
	 */
	public static Map groupByTopic(CompositeResponse compEvent)
	{
		List responses = compEvent.getResponses();
		return groupByTopic(responses);
	}

	/**
	 * Groups events by topic into separate collections of objects
	 * 
	 * @param compEvents
	 * @return
	 */
	private static Map groupByTopic(List compEvents)
	{
		HashMap<String, ArrayList> compositeMap = new HashMap<String, ArrayList>();
		int len = compEvents.size();
		for (int i = 0; i < len; i++)
		{
			IEvent event = (IEvent) compEvents.get(i);
			ArrayList bucketObj = compositeMap.get(event.getTopic());
			if (bucketObj == null)
			{
				bucketObj = new ArrayList();
				compositeMap.put(event.getTopic(), bucketObj);
			}			
			bucketObj.add(event);
		}

		return compositeMap;
	}

	/**
	 * Evaluates the collection within the Map to determine if a ReturnException Event is one of the
	 * objects. If so, it will throw the ReturnException
	 * 
	 * @param dataMap
	 */
	public static void processReturnException(Map dataMap)
	{

		Collection exceptions = (Collection) dataMap.get(ReturnException.RETURN_EXCEPTION_TOPIC);
		if (exceptions != null)
		{
			Iterator i = exceptions.iterator();
			ReturnException returnException = (ReturnException) i.next();
			throw returnException;
		}
	}

	public static void postReplies(List aResponses)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		int len = aResponses.size();
		for (int i = 0; i < len; i++)
		{
			IEvent response = (IEvent) aResponses.get(i);
			dispatch.postEvent(response);
		}
	}

	public static void postReply(ResponseEvent responseEvent)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(responseEvent);
	}

	public static CompositeResponse postRequest(RequestEvent requestEvent)

	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		// same as processReturnException method
		ReturnException returnException = (ReturnException) filterComposite(response, ReturnException.class);

		if (returnException != null)
		{
			throw returnException;
		}

		return response;
	}

	public static IEvent postRequest(RequestEvent requestEvent, Class filterClass)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		IEvent responseEvent = null;
		List responses = response.getResponses();
		boolean done = false;
		int len = responses.size();
		for (int i = 0; i < len && done == false; i++)
		{
			Object eventObj = responses.get(i);
			if (filterClass.isInstance(eventObj))
			{
				responseEvent = (IEvent) eventObj;
				done = true;
			}
			else if (ReturnException.class.isInstance(eventObj))
			{
				throw (ReturnException) eventObj;
			}
		}

		return responseEvent;
	}

	public static List postRequestListFilter(RequestEvent requestEvent, Class filterClass)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		List responses = new ArrayList();
		List events = response.getResponses();
		int len = events.size();
		for(int i=0;i<len;i++)
		{
			Object eventObj = events.get(i);
			if (filterClass.isInstance(eventObj))
			{
				responses.add(eventObj);
			}
			else if (ReturnException.class.isInstance(eventObj))
			{
				throw (ReturnException) eventObj;
			}
		}

		return responses;
	}

	/**
	 * Processes a collection parameter of events and returns an empty Collection if the collection
	 * parameter is not null.
	 * 
	 * @param data
	 * @return
	 */
	public static Collection processEmptyCollection(Collection data)
	{
		Collection bucket;
		if (data == null)
		{
			bucket = new ArrayList();
		}
		else
		{
			bucket = data;
		}
		return bucket;
	}

	/**
	 * Filters a CompositeResponse for a ReturnException. If a ReturnException exists, then it is
	 * thrown.
	 * 
	 * @param response
	 */
	public static void processReturnException(CompositeResponse response)
	{
		ReturnException returnException = (ReturnException) MessageUtil
				.filterComposite(response, ReturnException.class);

		if (returnException != null)
		{
			throw returnException;
		}
	}

	public static String convertEventToString(IEvent event)
	{
		PropertyBundleProperties propBundle = PropertyBundleProperties.getInstance();
		String delim = propBundle.getProperty("EventSerializationDelim");
		if (delim == null)
		{
			delim = "|";
		}

		Class eventClass = event.getClass();
		StringBuilder retVal = new StringBuilder(eventClass.getName());
		retVal.append("::");
		List accessors = Reflection.getAccessors(eventClass);
		int len = accessors.size();
		for (int i = 0; i < len; i++)
		{
			Method aMethod = (Method) accessors.get(i);
			String propName = Reflection.getPropertyName(aMethod);
			retVal.append(propName);
			retVal.append("=");
			Object o = Reflection.invokeAccessorMethod(event, propName);
			if (o != null)
			{
				if (o instanceof String)
				{
					retVal.append((String) o);
				}
				else
				{
					retVal.append(o.toString());
				}
			}
			if (i < len - 1)
			{
				retVal.append(delim);
			}
		}

		return retVal.toString();
	}

	public static IEvent convertStringToEvent(String event) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException
	{
		PropertyBundleProperties propBundle = PropertyBundleProperties.getInstance();
		String delim = propBundle.getProperty("EventSerializationDelim");
		if (delim == null)
		{
			delim = "|";
		}

		int eventNameIdx = event.indexOf("::");
		String eventName = event.substring(0, eventNameIdx);
		String restOfData = event.substring(eventNameIdx + 2);
		StringTokenizer st = new StringTokenizer(restOfData, delim);
		IEvent retVal = null;

		retVal = (IEvent) Class.forName(eventName).newInstance();
		while (st.hasMoreTokens())
		{
			String tok = st.nextToken();
			int idx = tok.indexOf(KeyWord.EQUALS);
			String propName = tok.substring(0, idx);
			String value = tok.substring(idx + 1);
			Reflection.invokeMutatorMethod(retVal, propName, value);
		}

		return retVal;
	}
}
