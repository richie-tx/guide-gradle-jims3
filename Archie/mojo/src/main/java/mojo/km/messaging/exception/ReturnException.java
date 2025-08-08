package mojo.km.messaging.exception;

import mojo.km.messaging.IEvent;
import mojo.km.security.IUserInfo;
import mojo.km.security.helper.SecurityUtil;

/**
 * An exception returned from one context to another context for processing.
 */
public class ReturnException extends RuntimeException implements IEvent
{
    public static final String RETURN_EXCEPTION_TOPIC = "RETURN_EXCEPTION";

    private String errorDiagnosis;

    private String errorExceptionType;

    private String errorLogId;

    private String errorLogUser;

    private String errorReason;

    private String errorSolution;

    private IEvent event;

    private String mServer;

    private String service = RETURN_EXCEPTION_TOPIC;

    private String sourceID;

    private String stackTrace;

    protected String topicMain;

    public ReturnException()
    {
        super();
        init(null);
    }

    public ReturnException(Throwable t)
    {
        super(t);
        init(t);
    }

    /**
     * Constructor
     * 
     * @param msg
     *            error message.
     */
    public ReturnException(String msg)
    {
        super(msg);
        init(null);
    }

    public ReturnException(String msg, Throwable actual)
    {
        super(msg, actual);
        init(actual);
    }

    /**
     * Constructor
     * 
     * @param msg
     *            error message.
     * @param actual
     *            actual partion exception.
     * @param requestingEvent
     *            event that started workflow.
     */
    public ReturnException(String msg, Throwable actual, IEvent requestingEvent)
    {
        super(msg, actual);
        init(actual);
        event = requestingEvent;
    }

    /**
     * Returns the Error Diagnosis for the Throwable type that is represented in the Exception Properties
     * 
     * @return Diagnosis to the error
     */
    public String getErrorDiagnosis()
    {
        return this.errorDiagnosis;
    }

    /**
     * Returnes the class that created the initial exception
     * 
     * @return name of the initial exception class
     */
    public String getErrorExceptionType()
    {
        return errorExceptionType;
    }

    /**
     * returns the logid that was generated when this exception was created.
     * 
     * @return exceptionlogid
     */
    public String getErrorLogId()
    {
        return errorLogId;
    }

    /**
     * Returns the user that was logged into the system when the exception occurred
     * 
     * @return
     */
    public String getErrorLogUser()
    {
        return errorLogUser;
    }

    /**
     * Returns the Error Reason for the Throwable type that is represented in the Exception Properties
     * 
     * @return reason to the error
     */
    public String getErrorReason()
    {
        return this.errorReason;
    }

    /**
     * Returns the Error Solution for the Throwable type that is represented in the Exception Properties
     * 
     * @return solution to the error
     */
    public String getErrorSolution()
    {
        return this.errorSolution;
    }

    /**
     * @return
     */
    public IEvent getEvent()
    {
        return event;
    }

    public String getLocalStackTrace()
    {
        return stackTrace;
    }

    /**
     * Return the server context name.
     * 
     * @return the server name.
     */
    public String getServer()
    {
        return mServer;
    }

    /**
     * Return source ID.
     */
    public String getSourceID()
    {
        return sourceID;
    }

    /**
     * Accesses the service property
     * 
     * @return the event topic.
     */
    public String getTopic()
    {
        return this.service;
    }

    /**
     * Accesses the listener topic.
     * 
     * @return event hash ID.
     */
    public String hashKey()
    {
        return topicMain + getTopic() + "::" + getClass().getName();
    }

    private void init(Throwable t)
    {
        IUserInfo user = SecurityUtil.getCurrentUser("UNAUTH", "UNAUTH");
        StringBuffer buffer = new StringBuffer();
        buffer.append(System.currentTimeMillis());
        buffer.append("-");
        buffer.append(user.getJIMSLogonId());
        this.errorLogId = buffer.toString();
        this.errorLogUser = user.getJIMS2LogonId();
        if (t != null)
        {
            this.errorExceptionType = t.getClass().getName();
        }
    }

    /**
     * @param diagnosis
     */
    public void setErrorDiagnosis(String aDiagnosis)
    {
        this.errorDiagnosis = aDiagnosis;
    }

    /**
     * @param errorExceptionType
     *            The errorExceptionType to set.
     */
    public void setErrorExceptionType(String errorExceptionType)
    {
        this.errorExceptionType = errorExceptionType;
    }

    /**
     * @param string
     */
    public void setErrorLogId(String anErrorLogId)
    {
        this.errorLogId = anErrorLogId;
    }

    /**
     * @param reason
     */
    public void setErrorReason(String aReason)
    {
        this.errorReason = aReason;
    }

    /**
     * @param solution
     */
    public void setErrorSolution(String aSolution)
    {
        this.errorSolution = aSolution;
    }

    /**
     * @param anEvent
     */
    public void setEvent(IEvent anEvent)
    {
        event = anEvent;
    }

    /**
     * Set the value of the server context name.
     * 
     * @param name -
     *            server name to return exception to.
     */
    public void setServer(String name)
    {
        mServer = name;
    }

    /**
     * set source id of request. Also sets the topic to the sourceID.
     *  
     */
    public void setSource(Object source)
    {
        setSourceID("" + source.hashCode());
    }

    /**
     * set source id of request. Also sets the topic to the sourceID.
     *  
     */
    public void setSourceID(String sourceID)
    {
        this.sourceID = sourceID;
        setTopic(sourceID);
    }

    /**
     * Set the service the event is to be associated with.
     * 
     * @param aService -
     *            event topic.
     */
    public void setTopic(String aService)
    {
        this.service = aService;
    }
}
