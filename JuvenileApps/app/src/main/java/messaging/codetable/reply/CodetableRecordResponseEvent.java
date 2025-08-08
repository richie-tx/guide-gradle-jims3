/*
 * Created on Jan 30, 2007
 *
 */
package messaging.codetable.reply;

import java.util.ArrayList;
import java.util.Collection;

import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 * 
 */
public class CodetableRecordResponseEvent extends ResponseEvent implements Comparable
{
	private String codetableName;

	private String codetableDescription;

	private String codetableRegId;

	private String type;

	private String entityName;

	private String contextKey;

	private Collection agencies = new ArrayList();

	public Collection getAgencies()
	{
		return agencies;
	}

	public void setAgencies(Collection agencies)
	{
		this.agencies = agencies;
	}

	/**
	 * @return Returns the codetableDescription.
	 */
	public String getCodetableDescription()
	{
		return codetableDescription;
	}

	/**
	 * @param codetableDescription
	 *            The codetableDescription to set.
	 */
	public void setCodetableDescription(String codetableDescription)
	{
		this.codetableDescription = codetableDescription;
	}

	/**
	 * @return Returns the codetableName.
	 */
	public String getCodetableName()
	{
		return codetableName;
	}

	/**
	 * @param codetableName
	 *            The codetableName to set.
	 */
	public void setCodetableName(String codetableName)
	{
		this.codetableName = codetableName;
	}

	/**
	 * @return Returns the codetableRegId.
	 */
	public String getCodetableRegId()
	{
		return codetableRegId;
	}

	/**
	 * @param codetableRegId
	 *            The codetableRegId to set.
	 */
	public void setCodetableRegId(String codetableRegId)
	{
		this.codetableRegId = codetableRegId;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj)
	{
		if (obj == null)
			return -1;
		CodetableRecordResponseEvent evt = (CodetableRecordResponseEvent) obj;

		if (evt.getCodetableName() == null)
		{
			return -1;
		}
		if (this.getCodetableName() == null)
		{
			return 1;
		}
		return this.getCodetableName().compareToIgnoreCase(evt.getCodetableName().trim());
	}

	/**
	 * @return Returns the contextKey.
	 */
	public String getContextKey()
	{
		return contextKey;
	}

	/**
	 * @param contextKey
	 *            The contextKey to set.
	 */
	public void setContextKey(String contextKey)
	{
		this.contextKey = contextKey;
	}

	/**
	 * @return Returns the entityName.
	 */
	public String getEntityName()
	{
		return entityName;
	}

	/**
	 * @param entityName
	 *            The entityName to set.
	 */
	public void setEntityName(String entityName)
	{
		this.entityName = entityName;
	}
}
