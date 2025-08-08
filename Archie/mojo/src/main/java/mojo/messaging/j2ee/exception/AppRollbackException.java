package mojo.messaging.j2ee.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

import javax.ejb.EJBException;

import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;

/**
 *
 * @author Hadi Mallah
 */
public class AppRollbackException extends EJBException implements IEvent {

	private Throwable cause;

    public AppRollbackException(String msg) {
    	super(msg);
	}

	public AppRollbackException(String msg, Throwable cause) {
		super(msg);
		this.cause = cause;
	}

	public String getMessage() {
		if (cause != null) {
			return super.getMessage() + ": " + cause.getMessage();
		}
		else {
			return super.getMessage();
		}
	}

	public void printStackTrace() {
		super.printStackTrace();
		if (cause != null) {
			System.err.print("Root cause: ");
			cause.printStackTrace();
		}
	}

	public void printStackTrace(PrintStream ps) {
		super.printStackTrace(ps);
		if (cause != null) {
			ps.print("Root cause: ");
			cause.printStackTrace(ps);
		}
	}

	public void printStackTrace(PrintWriter pw) {
		super.printStackTrace(pw);
		if (cause != null) {
			pw.print("Root cause: ");
			cause.printStackTrace(pw);
		}
	}

	public Throwable getCause() {
		return cause;
	}

    /**
     * Set the value of the server context name.
     *    @param name - server name to return exception to.
     */
    public void setServer(String name) {
        mServer = name;
    }

    /**
     * Accesses the listener topic.
     *    @return event hash ID.
     */
    public String hashKey() {
        return (
            new String(topicMain + getTopic() + "::" + this.getClass().getName()));
    }

    /**
     * Return the server context name.
     *    @return the server name.
     */
    public String getServer() {
        return mServer;
    }

    /**
     * Accesses the service property
     *    @return the event topic.
     */
    public String getTopic() {
        return this.service;
    }

    /**
     * Set the service the event is to be associated with.
     *    @param aService - event topic.
     */
    public void setTopic(String aService) {
        this.service = aService;
    }


	public void setEvent(IEvent anEvent) { event = anEvent; }
      public IEvent getEvent() { return event; }

    private String mServer = "";
    private String service;
    protected String topicMain = "";
    private IEvent event;
}
