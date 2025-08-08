package mojo.es.mail.mailrequests;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import messaging.notification.domintf.IAttachment;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.MessagingException;
import mojo.km.config.AppProperties;
import mojo.km.context.ICommand;
import mojo.km.exceptionhandling.ExceptionHandler;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import mojo.es.mail.MailAuthenticator;

/**
 * @author Jim Fisher
 */
public class SendEmailCommand implements ICommand
{
	private static Authenticator mailAuth;
	private static Properties mailProps;
	
	public SendEmailCommand()
	{
		String host = AppProperties.getInstance().getProperty("mail.send.server");

		mailProps = new Properties();
		host = host.trim();
		
		mailProps.put("mail.transport.protocol", "smtp");
		mailProps.put("mail.smtp.host", host);
		mailProps.put("mail.smtp.auth", "false");

		mailAuth = MailAuthenticator.getInstance();
	}

	public void execute(IEvent anEvent) throws Exception
	{
		SendEmailEvent event = (SendEmailEvent) anEvent;

		try
		{
			Session emailSession = Session.getInstance(mailProps, mailAuth);

			MimeMessage emailMsg = new MimeMessage(emailSession);

			this.setIdentities(event, emailMsg);

			this.setContent(event, emailMsg);

			Transport.send(emailMsg);
		}
		catch (Exception e)
		{
			// check if email was sent with Notification framework
			if (event.getMessageId() != null && event.getMessageId().equals("") == false)
			{
				MessagingException ex = new MessagingException(e.getMessage());
				ex.setMessageId(event.getMessageId());
				ex.setMessagingException(e);
				ExceptionHandler.executeCallbacks(ex);
			}
			throw e;
		}	
	}

	private void setContent(SendEmailEvent anEvent, MimeMessage emailMsg) throws javax.mail.MessagingException
	{
		// build multipart from contents
		Multipart multipart = new MimeMultipart();

		emailMsg.setSubject(anEvent.getSubject()); 
		
		BodyPart bodyPart = new MimeBodyPart();
		bodyPart.setText(anEvent.getMessage());
		//changes added for #13331
		if(anEvent.getContentType()!=null && !anEvent.getContentType().isEmpty()){
			bodyPart.setHeader("Content-Type",anEvent.getContentType());
			emailMsg.setHeader("Content-Type",anEvent.getContentType());
			emailMsg.setSubject(anEvent.getSubject(),"UTF-8"); //patch for rendering the subject.
		}
		multipart.addBodyPart(bodyPart);

		IAttachment attachment = anEvent.getAttachment();
		if (attachment != null)
		{
			bodyPart = new MimeBodyPart();

			String name = attachment.getName();
			byte[] content = attachment.getContent();
			String contentType = attachment.getContentType();

			DataSource source = new ByteArrayDataSource(name, content, contentType);
			bodyPart.setDataHandler(new DataHandler(source));
			bodyPart.setFileName(name);
			multipart.addBodyPart(bodyPart);
			
		}
		emailMsg.setContent(multipart);
		emailMsg.setSentDate(new Date());
		emailMsg.saveChanges(); //changes added for #13331
	}

	private void setIdentities(SendEmailEvent anEvent, MimeMessage emailMsg) throws AddressException,
			javax.mail.MessagingException
	{
		// From
		String from = anEvent.getFromAddress();
		emailMsg.setFrom(new InternetAddress(from));

		// To Recipients
		List<String> addresses = anEvent.getToAddresses();
		int len = addresses.size();
		for (int i = 0; i < len; i++)
		{
			String toAddress = addresses.get(i);
			emailMsg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
		}

		// cc Recipients
		addresses = anEvent.getCcAddresses();
		len = addresses.size();
		for (int i = 0; i < len; i++)
		{
			String ccAddress = addresses.get(i);
			emailMsg.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress));
		}

		// bcc Recipients
		addresses = anEvent.getBccAddresses();
		len = addresses.size();
		for (int i = 0; i < len; i++)
		{
			String bccAddress = addresses.get(i);
			emailMsg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bccAddress));
		}
	}

	/**
	 * Upon command registration with context this method will get executed
	 * 
	 * @param event -
	 *            sample event data.
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * Upon command unregistration from context this method will get executed
	 * 
	 * @param event -
	 *            sample event
	 */
	public void onUnregister(IEvent event)
	{
	}

	/**
	 * If command requires data before execute is called context will place the in command
	 * 
	 * @param object -
	 *            housing input data
	 */
	public void update(Object object)
	{
	}
}
