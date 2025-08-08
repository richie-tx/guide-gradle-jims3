package mojo.km.context.multidatasource;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Level;

import mojo.km.config.MojoProperties;
import mojo.km.config.ServiceProperties;
import mojo.km.context.ICommand;
import mojo.km.context.IEventBroadcaster;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.exceptionhandling.ExceptionHandler;
import mojo.km.logging.LogUtil;
import mojo.km.messaging.Composite.CompositeRequest;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.messaging.noop.NoReply;
import mojo.km.transaction.multidatasource.TransactionException;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.dispatch.ReplyUtility;
import mojo.km.dispatch.IGenericReply;
import mojo.tools.code.KeyWord;

/**
 * Is a non persistent event broadcast used to implement a default service configuration for
 * business context services.
 * 
 * @author Eric Amundson
 */
public class GenericEventBroadcaster implements IEventBroadcaster
{
	public class InternalException extends RuntimeException
	{
		Throwable theActual;

		public InternalException(Throwable actualException)
		{
			super();
			theActual = actualException;
		}

		public Throwable getActual()
		{
			return theActual;
		}
	}

	private class NoListenerException extends Exception
	{
		NoListenerException(String msg)
		{
			super(msg);
		}
	}

	/**
	 * Responsible for sending the reply from the context server back to the routing server.
	 * 
	 * @author Eric A Amundson
	 */
	public class ReplyDispatch implements IDispatch, IGenericReply
	{
		private IEvent reply = new NoReply();

		private ReplyUtility replyU = null;

		public ReplyDispatch()
		{
		}

		/**
		 * Return a reply event.
		 * 
		 * @return reply event.
		 */
		public IEvent getReply()
		{
			return reply;
		}

		/**
		 * Post an event to middleware layers.
		 * 
		 * @param event -
		 *            data being posted
		 */
		public void postEvent(IEvent event)
		{
			if ((event instanceof NoReply) || replyU == null)
			{
				replyU = new ReplyUtility();
				reply = replyU.current();
				return;
			}
			reply = replyU.postEvent(event);
		}
	}

	public class RequestDispatch implements IDispatch
	{
		public RequestDispatch()
		{
		}

		/**
		 * @return
		 */
		public IEvent getReply()
		{
			return EventManager.getSharedInstance(EventManager.REPLY).getReply();
		}

		/**
		 * @param event
		 */
		public void postEvent(IEvent event)
		{
			if (GenericEventBroadcaster.this.hasTransaction)
			{
				try
				{
					GenericEventBroadcaster.this.postEvent(event);
				}
				catch (InternalException e)
				{
					throw e;
				}
				catch (Throwable e)
				{
					throw new InternalException(e);
				}
			}
			else
			{
				GenericEventBroadcaster.this.fireEvent(event);
			}
		}
	}

	private static final String MAX_SERVICE_ELAPSED_TIME_STRING = System.getProperty("jims2.log.perf.service");

	private static long maxServiceElapsedTime = -1;

	private static final long MILLION = 1000000;

	private static boolean traceEnabled = LogUtil.getLogger().isTraceEnabled();

	static
	{
		if (MAX_SERVICE_ELAPSED_TIME_STRING != null)
		{
			try
			{
				maxServiceElapsedTime = Long.valueOf(MAX_SERVICE_ELAPSED_TIME_STRING).longValue();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private IEvent finalReply;

	private boolean hasTransaction = false;

	/**
	 * The listeners waiting for events to be broadcast.
	 */
	private Hashtable<String, List> listeners = new Hashtable<String, List>();

	public GenericEventBroadcaster()
	{
		IDispatch request = new RequestDispatch();
		IDispatch reply = new ReplyDispatch();
		EventManager.addStrategy(EventManager.REQUEST, request);
		EventManager.addStrategy(EventManager.REPLY, reply);
	}

	/**
	 * Adds a listener.
	 * 
	 * @param listener
	 *            the listener to add
	 * @param hashKey
	 *            of the item to be added.
	 * @see #removeListener
	 */
	public void addListener(ICommand listener, String hashKey)
	{
		List list = null;
		if (!listeners.containsKey(hashKey))
		{
			list = new ArrayList();
			listeners.put(hashKey, list);
		}
		else
		{
			list = listeners.get(hashKey);
		}
		list.add(listener);
	}

	/**
	 * @param event
	 */
	public void addResponse(IEvent event)
	{
	}

	/**
	 * invoke event related behaviors.
	 * 
	 * @param event
	 */
	public void fireEvent(IEvent event)
	{
		IDispatch replyDispatch = EventManager.getSharedInstance(EventManager.REPLY);
		replyDispatch.postEvent(new NoReply());

		try
		{
			hasTransaction = true;
			postEvent(event);
			hasTransaction = false;
			IEvent aReply = replyDispatch.getReply();
			setFinalReply(aReply);
			sendReply(aReply);
		}
		catch (TransactionException e)
		{
			// TODO improve logic
			EventManager.getSharedInstance(EventManager.REPLY).postEvent(new NoReply());

			IEvent exception = new ReturnException(e.getMessage(), e.getCause(), event);
			hasTransaction = false;
			replyDispatch.postEvent(exception);
			IEvent aReply = EventManager.getSharedInstance(EventManager.REPLY).getReply();
			setFinalReply(aReply);
			try
			{
				sendReply(aReply);
			}
			catch (Exception ie)
			{
				ExceptionHandler.executeCallbacks(ie);
			}
		}
		catch (Throwable ex)
		{
			// TODO improve logic

			replyDispatch.postEvent(new NoReply());
			if (ex instanceof InternalException)
			{
				ex = ((InternalException) ex).getActual();
			}
			IEvent exception = new ReturnException(ExceptionHandler.getProperties(ex), ex, event);
			hasTransaction = false;
			replyDispatch.postEvent(exception);
			IEvent aReply = replyDispatch.getReply();
			setFinalReply(aReply);
			try
			{
				sendReply(aReply);
			}
			catch (Exception ie)
			{
				ExceptionHandler.executeCallbacks(ie);
			}
		}
		finally
		{
			hasTransaction = false;
		}
	}

	public IEvent getFinalReply()
	{
		return finalReply;
	}

	/**
	 * Check for a listener with the given hashkey.
	 * 
	 * @param hashKey -
	 *            event class name and topic pair
	 * @return true if listener is registered.
	 */
	public synchronized boolean hasListener(String hashKey)
	{
		return listeners.containsKey(hashKey);
	}

	private void postEvent(IEvent event) throws NoListenerException, Exception, RuntimeException
	{
		// TODO improve logic/exception handling to reduce try-catch blocks

		ICommand listener = null;

		String hashKey = event.hashKey();

		List theListeners = listeners.get(hashKey);
		if (theListeners == null)
		{
			ServiceProperties service = MojoProperties.getInstance().getServiceForEvent(event);
			if (service != null)
			{
				Iterator commandNames = service.getCommands();
				try
				{
					while (commandNames.hasNext())
					{
						String commandName = (String) commandNames.next();
						Class commandClass = Class.forName(commandName);
						listener = (ICommand) commandClass.newInstance();
						this.addListener(listener, hashKey);
					}
					theListeners = listeners.get(hashKey);
					if (theListeners == null)
					{
						theListeners = new ArrayList();
					}
				}
				catch (Exception e)
				{
					throw new NoListenerException("Has no listeners");
				}
			}
			else if (event instanceof CompositeRequest)
			{
				Enumeration en = ((CompositeRequest) event).getRequests();
				while (en.hasMoreElements())
				{
					IEvent anEvent = (IEvent) en.nextElement();
					postEvent(anEvent);
				}
				theListeners = new ArrayList();
			}
		}

		int len = 0;
		if (theListeners != null)
		{
			len = theListeners.size();
		}

		for (int i = 0; i < len; i++)
		{
			listener = (ICommand) theListeners.get(i);

			long starttime = System.nanoTime();

			StringBuilder baseBuffer = null;
			StringBuilder executeBuffer;

			if (traceEnabled == true)
			{
				baseBuffer = new StringBuilder(70);
				baseBuffer.append(LogUtil.EXECUTE_SERVICE);
				baseBuffer.append(KeyWord.SPACE);
				baseBuffer.append(listener.getClass().getName());

				executeBuffer = new StringBuilder(70);
				executeBuffer.append("BEGIN ");
				executeBuffer.append(baseBuffer);
				LogUtil.log(Level.TRACE, executeBuffer.toString());
			}

			try
			{
				listener.execute(event);
				TransactionManager.getInstance().commit();

				long endtime = System.nanoTime();

				long elapsedTime = (endtime - starttime) / MILLION;

				if (traceEnabled == true)
				{
					executeBuffer = new StringBuilder(70);
					executeBuffer.append("END ");
					executeBuffer.append(baseBuffer);
					executeBuffer.append(" ");
					executeBuffer.append(elapsedTime);
					executeBuffer.append(" ms");
					LogUtil.log(Level.TRACE, executeBuffer.toString());
				}

				if (maxServiceElapsedTime != -1 && elapsedTime > maxServiceElapsedTime)
				{
					String longDesc = listener.getClass().getName();
					String shortDesc = "poor performance: " + elapsedTime + " ms";
					ExceptionHandler.addWarning("Service Perf", null, null, elapsedTime, shortDesc, longDesc);
				}
			}
			catch (RuntimeException rE)
			{
				System.err.println("Error in GenericEventBroadcaster - check Composite response for Return Exception:"+ rE.getMessage());
				System.err.println("Error is: " + rE.toString());
				rE.printStackTrace();
				TransactionManager.getInstance().rollback();
				throw rE;
			}
		}
	}

	private void sendReply(IEvent anEvent) throws Exception
	{
		if (anEvent instanceof CompositeResponse)
		{
			List responses = ((CompositeResponse) anEvent).getResponses();
			int len = responses.size();
			for (int i = 0; i < len; i++)
			{
				IEvent event = (IEvent) responses.get(i);
				postEvent(event);
			}
		}
		else
		{
			postEvent(anEvent);
		}
	}

	public void setFinalReply(IEvent finalReply)
	{
		this.finalReply = finalReply;
	}
}
