package mojo.km.messaging.exception;

public class MessagingException extends RuntimeException
{
	public MessagingException()
	{
		super();
	}

	public MessagingException(String s)
	{
		super(s);
	}

	public MessagingException(Exception messagingException, String s)
	{
		super(s);
		this.messagingException = messagingException;
	}

	private String messageId;
	private String address;

	private Exception messagingException;

	/**
	 * @return
	 */
	public String getMessageId()
	{
		return messageId;
	}

	/**
	 * @param string
	 */
	public void setMessageId(String string)
	{
		messageId = string;
	}

	/**
	 * @return
	 */
	public Exception getMessagingException()
	{
		return messagingException;
	}

	/**
	 * @param exception
	 */
	public void setMessagingException(Exception exception)
	{
		messagingException = exception;
	}

	/**
	 * @return
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * @param string
	 */
	public void setAddress(String string)
	{
		address = string;
	}

}