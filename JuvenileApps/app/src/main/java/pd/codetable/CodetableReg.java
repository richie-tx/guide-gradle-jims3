package pd.codetable;

import java.util.Collection;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * 
 * @roseuid 45BA30D1028E
 */
public class CodetableReg extends PersistentObject
{
	private String codetableName;

	private String codetableDescription;

	private String type;

	private String codetableContextKey;

	private String codetableEntityName;

	private Collection agencyList;

	private Collection<CodetableRegAgency> agencies = null;

	/**
	 * Properties for codetableRegAttributes
	 * 
	 * @detailerDoNotGenerate false
	 * @referencedType pd.codetable.CodetableRegAttribute
	 */
	private Collection codetableRegAttributes = null;

	/**
	 * 
	 * @roseuid 45BA30D1028E
	 */
	public CodetableReg()
	{
	}

	/**
	 * @return java.util.Iterator
	 * @param codetableRegId
	 * @roseuid 4107B06D01BB
	 */
	static public CodetableReg find(String codetableRegId)
	{
		IHome home = new Home();
		return (CodetableReg) home.find(codetableRegId, CodetableReg.class);
	}

	/**
	 * @roseuid 42E65EA6010F
	 */
	static public Iterator findAll()
	{
		IHome home = new Home();
		Iterator iter = home.findAll(CodetableReg.class);
		return iter;
	}

	/**
	 * @return java.util.Iterator
	 * @param event
	 * @roseuid 4107B06D01BB
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(event, CodetableReg.class);
		return iter;
	}

	/**
	 * @return java.util.Iterator
	 * @param attrName
	 * @param attrValue
	 * @roseuid 42E65EA6010F
	 */
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		return (Iterator) home.findAll(attrName, attrValue, CodetableReg.class);
	}

	/**
	 * @return Returns the codetableDescription.
	 */
	public String getCodetableDescription()
	{
		fetch();
		return codetableDescription;
	}

	/**
	 * @param codetableDescription
	 *            The codetableDescription to set.
	 */
	public void setCodetableDescription(String codetableDescription)
	{
		if (this.codetableDescription == null || !this.codetableDescription.equals(codetableDescription))
		{
			markModified();
		}
		this.codetableDescription = codetableDescription;
	}

	/**
	 * @return Returns the codetableName.
	 */
	public String getCodetableName()
	{
		fetch();
		return codetableName;
	}

	/**
	 * @param codetableName
	 *            The codetableName to set.
	 */
	public void setCodetableName(String codetableName)
	{
		if (this.codetableName == null || !this.codetableName.equals(codetableName))
		{
			markModified();
		}
		this.codetableName = codetableName;
	}

	/**
	 * @return Returns the contextKey.
	 */
	public String getCodetableContextKey()
	{
		fetch();
		return codetableContextKey;
	}

	/**
	 * @param contextKey
	 *            The contextKey to set.
	 */
	public void setCodetableContextKey(String codetableContextKey)
	{
		if (this.codetableContextKey == null || !this.codetableContextKey.equals(codetableContextKey))
		{
			markModified();
		}
		this.codetableContextKey = codetableContextKey;
	}

	/**
	 * @return Returns the entityName.
	 */
	public String getCodetableEntityName()
	{
		fetch();
		return codetableEntityName;
	}

	/**
	 * @param entityName
	 *            The entityName to set.
	 */
	public void setCodetableEntityName(String codetableEntityName)
	{
		if (this.codetableEntityName == null || !this.codetableEntityName.equals(codetableEntityName))
		{
			markModified();
		}
		this.codetableEntityName = codetableEntityName;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType()
	{
		fetch();
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type)
	{
		if (this.type == null || !this.type.equals(type))
		{
			markModified();
		}
		this.type = type;
	}

	/**
	 * Initialize class relationship implementation for pd.codetable.CodetableRegAttribute
	 */
	private void initCodetableRegAttributes()
	{
		if (codetableRegAttributes == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			codetableRegAttributes = new mojo.km.persistence.ArrayList(CodetableRegAttribute.class,
					"codetableRegId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.codetable.CodetableRegAttribute
	 */
	public Collection getCodetableRegAttributes()
	{
		initCodetableRegAttributes();
		return codetableRegAttributes;
	}

	/**
	 * insert a pd.codetable.CodetableRegAttribute into class relationship collection.
	 */
	public void insertCodetableRegAttributes(CodetableRegAttribute anObject)
	{
		initCodetableRegAttributes();
		codetableRegAttributes.add(anObject);
	}

	/**
	 * Removes a pd.codetable.CodetableRegAttribute from class relationship collection.
	 */
	public void removeCodetableRegAttributes(CodetableRegAttribute anObject)
	{
		initCodetableRegAttributes();
		codetableRegAttributes.remove(anObject);
	}

	/**
	 * Clears all pd.codetable.CodetableRegAttribute from class relationship collection.
	 */
	public void clearCodetableRegAttributes()
	{
		initCodetableRegAttributes();
		codetableRegAttributes.clear();
	}

	public Collection getAgencyList()
	{
		return agencyList;
	}

	public void setAgencyList(Collection agencyList)
	{
		this.agencyList = agencyList;
	}

	private void initAgencies()
	{
		if (agencies == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			try
			{
				agencies = new mojo.km.persistence.ArrayList(CodetableRegAgency.class, "parentId", ""
						+ getOID());
			}
			catch (Throwable t)
			{
				agencies = new java.util.ArrayList();
			}
		}
	}

	public Collection getAgencies()
	{
		initAgencies();
		java.util.ArrayList retVal = new java.util.ArrayList();
		Iterator i = agencies.iterator();
		while (i.hasNext())
		{
			CodetableRegAgency actual = (CodetableRegAgency) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}

	public void insertAgencies(pd.contact.agency.Agency anObject)
	{
		initAgencies();
		CodetableRegAgency actual = new CodetableRegAgency();
		if (this.getOID() == null)
		{
			new Home().bind(this);
		}
		/*if (anObject.getOID() == null)
		{
			new Home().bind(anObject);
		}*/
		//actual.setChild(anObject); 87191
		actual.setParent(this);
		agencies.add(actual);
	}

	public void removeAgencies(pd.contact.agency.Agency anObject)
	{
		initAgencies();
		try
		{
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) anObject.getAgencyId());
			assocEvent.setParentId((String) this.getOID());
			CodetableRegAgency actual = (CodetableRegAgency) new mojo.km.persistence.Reference(
					assocEvent, CodetableRegAgency.class).getObject();
			agencies.remove(actual);
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

}
