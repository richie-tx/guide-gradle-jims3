/*
 * Created on Mar 2, 2006
 *
 */
package pd.notification.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mojo.pattern.IBuilder;

/**
 * @author jfisher
 *
 */
public class RecipientListCreator
{
	private String delim;
	private List recipients;

	public RecipientListCreator(String delim)
	{
		this.delim = delim;
		this.recipients = new ArrayList();
	}

	public void addRecipient(String recipient)
	{
		if (recipient != null && recipient.equals("") == false)
		{
			this.recipients.add(recipient);
		}
	}

	public String getResult()
	{
		StringBuffer recipientBuffer = new StringBuffer();
		int size = this.recipients.size();
		
		Iterator i  = recipients.iterator();
		
		if(size == 1)
		{
			recipientBuffer.append(i.next());
		}
		else if(size > 1)
		{
			recipientBuffer.append(i.next());
			while(i.hasNext())
			{
				recipientBuffer.append(this.delim);
				recipientBuffer.append(i.next());
			}
		}
		return recipientBuffer.toString();
	}

}
