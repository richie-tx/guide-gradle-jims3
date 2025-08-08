package mojo.km.security;


/**
 * 
 * 86322
 * Base class for all the web-services for security manager.
 * @param <T>
 */
public  class SecurityManagerBaseResponse<T>
{
    private String[] messages;
    private boolean isIsSuccess;
    private boolean isRecFound;
    private T data; //generic response object.
    private boolean isMaxRecCountExceeded;
    private int recCount;
    
    /**
     * @return the isMaxRecCountExceeded
     */
    public boolean isMaxRecCountExceeded()
    {
        return isMaxRecCountExceeded;
    }
    /**
     * @param isMaxRecCountExceeded the isMaxRecCountExceeded to set
     */
    public void setMaxRecCountExceeded(boolean isMaxRecCountExceeded)
    {
        this.isMaxRecCountExceeded = isMaxRecCountExceeded;
    }
    /**
     * @return the messages
     */
    public String[] getMessages()
    {
        return messages;
    }
    /**
     * @param messages the messages to set
     */
    public void setMessages(String[] messages)
    {
        this.messages = messages;
    }
    /**
     * @return the isIsSuccess
     */
    public boolean isIsSuccess()
    {
        return isIsSuccess;
    }
    /**
     * @param isIsSuccess the isIsSuccess to set
     */
    public void setIsSuccess(boolean isIsSuccess)
    {
        this.isIsSuccess = isIsSuccess;
    }
    /**
     * @return the isRecFound
     */
    public boolean isRecFound()
    {
        return isRecFound;
    }
    /**
     * @param isRecFound the isRecFound to set
     */
    public void setRecFound(boolean isRecFound)
    {
        this.isRecFound = isRecFound;
    }
    public T getData()
    {
	return data;
    }
    public void setData(T data)
    {
	this.data = data;
    }
    /**
     * @return the recCount
     */
    public int getRecCount()
    {
	return recCount;
    }
    /**
     * @param recCount the recCount to set
     */
    public void setRecCount(int recCount)
    {
	this.recCount = recCount;
    }
}
