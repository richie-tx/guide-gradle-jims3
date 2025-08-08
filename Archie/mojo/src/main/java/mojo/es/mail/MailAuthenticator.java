package mojo.es.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import mojo.km.config.AppProperties;

public class MailAuthenticator extends Authenticator
{
	private PasswordAuthentication auth;

	private static MailAuthenticator instance;

	public static MailAuthenticator getInstance()
	{
		if (instance == null)
		{
			instance = new MailAuthenticator();
		}

		return instance;
	}

	private MailAuthenticator()
	{
		String userName = AppProperties.getInstance().getProperty("mail.send.username");
		if (userName == null)
		{
			throw new RuntimeException("'mail.send.username' is not specified.");
		}
		String password = AppProperties.getInstance().getProperty("mail.send.password");
		if (password == null)
		{
			throw new RuntimeException("'mail.send.password' is null.");
		}
		auth = new PasswordAuthentication(userName, password);
	}

	protected PasswordAuthentication getPasswordAuthentication()
	{
		return auth;
	}
}
