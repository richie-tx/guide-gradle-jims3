package pd.supervision.suggestedorder;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.contact.agency.Agency;
import naming.SupervisionConstants;
import messaging.suggestedorder.reply.SuggestedOrderResponseEvent;
import java.util.Iterator;
import pd.codetable.Code;
import mojo.km.messaging.IEvent;

/**
 * @roseuid 433AAC3A02E6
 */
public class SuggestedOrder extends PersistentObject
{
	/**
	 * Properties for agency
	 * @referencedType pd.contact.agency.Agency
	 * @detailerDoNotGenerate false
	 */
	private Agency agency = null;
	private String agencyId;
	/**
	 * Properties for conditions
	 * @referencedType pd.supervision.suggestedorder.SuggestedOrderCondition
	 * @detailerDoNotGenerate false
	 */
	private java.util.Collection conditions = null;
	/**
	 * Properties for courts
	 * @referencedType pd.supervision.suggestedorder.SuggestedOrderCourt
	 * @detailerDoNotGenerate false
	 */
	private java.util.Collection courts = null;
	/**
	 * Properties for includedConditions
	 * @referencedType pd.codetable.Code
	 * @detailerDoNotGenerate false
	 * @contextKey INCLUDED_CONDITIONS
	 */
	private Code includedConditions = null;
	private String includedConditionsId;
	/**
	 * Properties for offenses
	 * @referencedType pd.supervision.suggestedorder.SuggestedOrderOffense
	 * @detailerDoNotGenerate false
	 */
	private java.util.Collection offenses = null;
	private String orderDescription;
	private String orderName;
	private String suggestedOrderId;

	/**
	 * @roseuid 433AA92800B8
	 */
	static public SuggestedOrder find(String suggestedOrderId)
	{
		IHome home = new Home();
		SuggestedOrder suggestedOrder = (SuggestedOrder) home.find(suggestedOrderId, SuggestedOrder.class);
		return suggestedOrder;
	}

	/**
	 * @roseuid 433AA92800B7
	 */
	static public Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(SuggestedOrder.class);
	}

	/**
	 * @roseuid 433AA92800B7
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, SuggestedOrder.class);
	}

	/**
	 * @roseuid 433AA92800B8
	 * @methodInvocation findAll
	 * @methodInvocation findAll
	 * @methodInvocation findAll
	 * @methodInvocation findAll
	 * @methodInvocation findAll
	 * @methodInvocation findAll
	 * @methodInvocation findAll
	 * @methodInvocation findAll
	 * @methodInvocation findAll
	 */
	static public Iterator findByAgency(String anAgency)
	{
		IHome home = new Home();
		Iterator iter = home.findAll("agencyId", anAgency, SuggestedOrder.class);
		return iter;
	}

	/**
	 * @roseuid 433AAC3A02E6
	 */
	public SuggestedOrder()
	{
	}

	/**
	 * Clears all pd.supervision.suggestedorder.SuggestedOrderCondition from class relationship collection.
	 * @methodInvocation initConditions
	 * @methodInvocation clear
	 * @methodInvocation initConditions
	 * @methodInvocation clear
	 * @methodInvocation initConditions
	 * @methodInvocation clear
	 * @methodInvocation initConditions
	 * @methodInvocation clear
	 * @methodInvocation initConditions
	 * @methodInvocation clear
	 * @methodInvocation initConditions
	 * @methodInvocation clear
	 * @methodInvocation initConditions
	 * @methodInvocation clear
	 * @methodInvocation initConditions
	 * @methodInvocation clear
	 * @methodInvocation initConditions
	 * @methodInvocation clear
	 */
	public void clearConditions()
	{
		initConditions();
		conditions.clear();
	}

	/**
	 * Clears all pd.supervision.suggestedorder.SuggestedOrderCourt from class relationship collection.
	 * @methodInvocation initCourts
	 * @methodInvocation clear
	 * @methodInvocation initCourts
	 * @methodInvocation clear
	 * @methodInvocation initCourts
	 * @methodInvocation clear
	 * @methodInvocation initCourts
	 * @methodInvocation clear
	 * @methodInvocation initCourts
	 * @methodInvocation clear
	 * @methodInvocation initCourts
	 * @methodInvocation clear
	 * @methodInvocation initCourts
	 * @methodInvocation clear
	 * @methodInvocation initCourts
	 * @methodInvocation clear
	 * @methodInvocation initCourts
	 * @methodInvocation clear
	 */
	public void clearCourts()
	{
		initCourts();
		courts.clear();
	}

	/**
	 * Clears all pd.supervision.suggestedorder.SuggestedOrderOffense from class relationship collection.
	 * @methodInvocation initOffenses
	 * @methodInvocation clear
	 * @methodInvocation initOffenses
	 * @methodInvocation clear
	 * @methodInvocation initOffenses
	 * @methodInvocation clear
	 * @methodInvocation initOffenses
	 * @methodInvocation clear
	 * @methodInvocation initOffenses
	 * @methodInvocation clear
	 * @methodInvocation initOffenses
	 * @methodInvocation clear
	 * @methodInvocation initOffenses
	 * @methodInvocation clear
	 * @methodInvocation initOffenses
	 * @methodInvocation clear
	 * @methodInvocation initOffenses
	 * @methodInvocation clear
	 */
	public void clearOffenses()
	{
		initOffenses();
		offenses.clear();
	}

	/**
	 * Gets referenced type pd.contact.agency.Agency
	 * @methodInvocation initAgency
	 * @methodInvocation initAgency
	 * @methodInvocation initAgency
	 * @methodInvocation initAgency
	 * @methodInvocation initAgency
	 * @methodInvocation initAgency
	 * @methodInvocation initAgency
	 * @methodInvocation initAgency
	 * @methodInvocation initAgency
	 */
	public Agency getAgency()
	{
		initAgency();
		return agency;
	}

	/**
	 * Get the reference value to class :: pd.contact.agency.Agency
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getAgencyId()
	{
		fetch();
		return agencyId;
	}

	/**
	 * returns a collection of pd.supervision.suggestedorder.SuggestedOrderCondition
	 * @methodInvocation fetch
	 * @methodInvocation initConditions
	 * @methodInvocation fetch
	 * @methodInvocation initConditions
	 * @methodInvocation fetch
	 * @methodInvocation initConditions
	 * @methodInvocation fetch
	 * @methodInvocation initConditions
	 * @methodInvocation fetch
	 * @methodInvocation initConditions
	 * @methodInvocation fetch
	 * @methodInvocation initConditions
	 * @methodInvocation fetch
	 * @methodInvocation initConditions
	 * @methodInvocation fetch
	 * @methodInvocation initConditions
	 * @methodInvocation fetch
	 * @methodInvocation initConditions
	 */
	public java.util.Collection getConditions()
	{
		fetch();
		initConditions();
		return conditions;
	}

	/**
	 * returns a collection of pd.supervision.suggestedorder.SuggestedOrderCourt
	 * @methodInvocation fetch
	 * @methodInvocation initCourts
	 * @methodInvocation fetch
	 * @methodInvocation initCourts
	 * @methodInvocation fetch
	 * @methodInvocation initCourts
	 * @methodInvocation fetch
	 * @methodInvocation initCourts
	 * @methodInvocation fetch
	 * @methodInvocation initCourts
	 * @methodInvocation fetch
	 * @methodInvocation initCourts
	 * @methodInvocation fetch
	 * @methodInvocation initCourts
	 * @methodInvocation fetch
	 * @methodInvocation initCourts
	 * @methodInvocation fetch
	 * @methodInvocation initCourts
	 */
	public java.util.Collection getCourts()
	{
		fetch();
		initCourts();
		return courts;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation initIncludedConditions
	 * @methodInvocation fetch
	 * @methodInvocation initIncludedConditions
	 * @methodInvocation fetch
	 * @methodInvocation initIncludedConditions
	 * @methodInvocation fetch
	 * @methodInvocation initIncludedConditions
	 * @methodInvocation fetch
	 * @methodInvocation initIncludedConditions
	 * @methodInvocation fetch
	 * @methodInvocation initIncludedConditions
	 * @methodInvocation fetch
	 * @methodInvocation initIncludedConditions
	 * @methodInvocation fetch
	 * @methodInvocation initIncludedConditions
	 * @methodInvocation fetch
	 * @methodInvocation initIncludedConditions
	 */
	public Code getIncludedConditions()
	{
		fetch();
		initIncludedConditions();
		return includedConditions;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getIncludedConditionsId()
	{
		fetch();
		return includedConditionsId;
	}

	/**
	 * returns a collection of pd.supervision.suggestedorder.SuggestedOrderOffense
	 * @methodInvocation fetch
	 * @methodInvocation initOffenses
	 * @methodInvocation fetch
	 * @methodInvocation initOffenses
	 * @methodInvocation fetch
	 * @methodInvocation initOffenses
	 * @methodInvocation fetch
	 * @methodInvocation initOffenses
	 * @methodInvocation fetch
	 * @methodInvocation initOffenses
	 * @methodInvocation fetch
	 * @methodInvocation initOffenses
	 * @methodInvocation fetch
	 * @methodInvocation initOffenses
	 * @methodInvocation fetch
	 * @methodInvocation initOffenses
	 * @methodInvocation fetch
	 * @methodInvocation initOffenses
	 */
	public java.util.Collection getOffenses()
	{
		fetch();
		initOffenses();
		return offenses;
	}

	/**
	 * Access method for the orderDescription property.
	 * @return the current value of the orderDescription property
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getOrderDescription()
	{
		fetch();
		return orderDescription;
	}

	/**
	 * Access method for the orderName property.
	 * @return the current value of the orderName property
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getOrderName()
	{
		fetch();
		return orderName;
	}

	/**
	 * Creates response event of SuggestedOrder
	 * @return 
	 * @methodInvocation fetch
	 * @methodInvocation setSuggestedOrderId
	 * @methodInvocation setIncludedConditionsId
	 * @methodInvocation setOrderDescription
	 * @methodInvocation setOrderName
	 * @methodInvocation setTopic
	 * @methodInvocation fetch
	 * @methodInvocation setSuggestedOrderId
	 * @methodInvocation setIncludedConditionsId
	 * @methodInvocation setOrderDescription
	 * @methodInvocation setOrderName
	 * @methodInvocation setTopic
	 * @methodInvocation fetch
	 * @methodInvocation setSuggestedOrderId
	 * @methodInvocation setIncludedConditionsId
	 * @methodInvocation setOrderDescription
	 * @methodInvocation setOrderName
	 * @methodInvocation setTopic
	 * @methodInvocation fetch
	 * @methodInvocation setSuggestedOrderId
	 * @methodInvocation setIncludedConditionsId
	 * @methodInvocation setOrderDescription
	 * @methodInvocation setOrderName
	 * @methodInvocation setTopic
	 * @methodInvocation fetch
	 * @methodInvocation setSuggestedOrderId
	 * @methodInvocation setIncludedConditionsId
	 * @methodInvocation setOrderDescription
	 * @methodInvocation setOrderName
	 * @methodInvocation setTopic
	 * @methodInvocation fetch
	 * @methodInvocation setSuggestedOrderId
	 * @methodInvocation setIncludedConditionsId
	 * @methodInvocation setOrderDescription
	 * @methodInvocation setOrderName
	 * @methodInvocation setTopic
	 * @methodInvocation fetch
	 * @methodInvocation setSuggestedOrderId
	 * @methodInvocation setIncludedConditionsId
	 * @methodInvocation setOrderDescription
	 * @methodInvocation setOrderName
	 * @methodInvocation setTopic
	 * @methodInvocation fetch
	 * @methodInvocation setSuggestedOrderId
	 * @methodInvocation setIncludedConditionsId
	 * @methodInvocation setOrderDescription
	 * @methodInvocation setOrderName
	 * @methodInvocation setTopic
	 * @methodInvocation fetch
	 * @methodInvocation setSuggestedOrderId
	 * @methodInvocation setIncludedConditionsId
	 * @methodInvocation setOrderDescription
	 * @methodInvocation setOrderName
	 * @methodInvocation setTopic
	 */
	public SuggestedOrderResponseEvent getResponseEvent()
	{
		fetch();
		SuggestedOrderResponseEvent sore = new SuggestedOrderResponseEvent();
		sore.setSuggestedOrderId(this.getSuggestedOrderId());
		sore.setIncludedConditionsId(this.getIncludedConditionsId());
		sore.setOrderDescription(this.getOrderDescription());
		sore.setOrderName(this.getOrderName());
		sore.setTopic(SupervisionConstants.SUGGESTED_ORDER_EVENT_TOPIC);
		return sore;
	}

	/**
	 * Access method for the suggestedOrderId property.
	 * @return the current value of the suggestedOrderId property
	 */
	public String getSuggestedOrderId()
	{
		return "" + getOID();
	}

	/**
	 * Initialize class relationship to class pd.contact.agency.Agency
	 */
	private void initAgency()
	{
		if (agency == null)
		{
			agency = Agency.find(agencyId);//(pd.contact.agency.Agency) new mojo.km.persistence.Reference(agencyId,
					//pd.contact.agency.Agency.class).getObject();
		}
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.suggestedorder.SuggestedOrderCondition
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 */
	private void initConditions()
	{
		if (conditions == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			conditions = new mojo.km.persistence.ArrayList(SuggestedOrderCondition.class,
					"suggestedOrderId", "" + getOID());
		}
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.suggestedorder.SuggestedOrderCourt
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 */
	private void initCourts()
	{
		if (courts == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			courts = new mojo.km.persistence.ArrayList(SuggestedOrderCourt.class,
					"suggestedOrderId", "" + getOID());
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initIncludedConditions()
	{
		if (includedConditions == null)
		{
			includedConditions = (Code) new mojo.km.persistence.Reference(includedConditionsId,
					Code.class, "INCLUDED_CONDITIONS").getObject();
		}
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.suggestedorder.SuggestedOrderOffense
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 */
	private void initOffenses()
	{
		if (offenses == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			offenses = new mojo.km.persistence.ArrayList(SuggestedOrderOffense.class,
					"suggestedOrderId", "" + getOID());
		}
	}

	/**
	 * insert a pd.supervision.suggestedorder.SuggestedOrderCondition into class relationship collection.
	 * @methodInvocation initConditions
	 * @methodInvocation add
	 * @methodInvocation initConditions
	 * @methodInvocation add
	 * @methodInvocation initConditions
	 * @methodInvocation add
	 * @methodInvocation initConditions
	 * @methodInvocation add
	 * @methodInvocation initConditions
	 * @methodInvocation add
	 * @methodInvocation initConditions
	 * @methodInvocation add
	 * @methodInvocation initConditions
	 * @methodInvocation add
	 * @methodInvocation initConditions
	 * @methodInvocation add
	 * @methodInvocation initConditions
	 * @methodInvocation add
	 */
	public void insertConditions(SuggestedOrderCondition anObject)
	{
		initConditions();
		conditions.add(anObject);
	}

	/**
	 * insert a pd.supervision.suggestedorder.SuggestedOrderCourt into class relationship collection.
	 * @methodInvocation initCourts
	 * @methodInvocation add
	 * @methodInvocation initCourts
	 * @methodInvocation add
	 * @methodInvocation initCourts
	 * @methodInvocation add
	 * @methodInvocation initCourts
	 * @methodInvocation add
	 * @methodInvocation initCourts
	 * @methodInvocation add
	 * @methodInvocation initCourts
	 * @methodInvocation add
	 * @methodInvocation initCourts
	 * @methodInvocation add
	 * @methodInvocation initCourts
	 * @methodInvocation add
	 * @methodInvocation initCourts
	 * @methodInvocation add
	 */
	public void insertCourts(SuggestedOrderCourt anObject)
	{
		initCourts();
		courts.add(anObject);
	}

	/**
	 * insert a pd.supervision.suggestedorder.SuggestedOrderOffense into class relationship collection.
	 * @methodInvocation initOffenses
	 * @methodInvocation add
	 * @methodInvocation initOffenses
	 * @methodInvocation add
	 * @methodInvocation initOffenses
	 * @methodInvocation add
	 * @methodInvocation initOffenses
	 * @methodInvocation add
	 * @methodInvocation initOffenses
	 * @methodInvocation add
	 * @methodInvocation initOffenses
	 * @methodInvocation add
	 * @methodInvocation initOffenses
	 * @methodInvocation add
	 * @methodInvocation initOffenses
	 * @methodInvocation add
	 * @methodInvocation initOffenses
	 * @methodInvocation add
	 */
	public void insertOffenses(SuggestedOrderOffense anObject)
	{
		initOffenses();
		offenses.add(anObject);
	}

	/**
	 * Removes a pd.supervision.suggestedorder.SuggestedOrderCondition from class relationship collection.
	 * @methodInvocation initConditions
	 * @methodInvocation remove
	 * @methodInvocation initConditions
	 * @methodInvocation remove
	 * @methodInvocation initConditions
	 * @methodInvocation remove
	 * @methodInvocation initConditions
	 * @methodInvocation remove
	 * @methodInvocation initConditions
	 * @methodInvocation remove
	 * @methodInvocation initConditions
	 * @methodInvocation remove
	 * @methodInvocation initConditions
	 * @methodInvocation remove
	 * @methodInvocation initConditions
	 * @methodInvocation remove
	 * @methodInvocation initConditions
	 * @methodInvocation remove
	 */
	public void removeConditions(SuggestedOrderCondition anObject)
	{
		initConditions();
		conditions.remove(anObject);
	}

	/**
	 * Removes a pd.supervision.suggestedorder.SuggestedOrderCourt from class relationship collection.
	 * @methodInvocation initCourts
	 * @methodInvocation remove
	 * @methodInvocation initCourts
	 * @methodInvocation remove
	 * @methodInvocation initCourts
	 * @methodInvocation remove
	 * @methodInvocation initCourts
	 * @methodInvocation remove
	 * @methodInvocation initCourts
	 * @methodInvocation remove
	 * @methodInvocation initCourts
	 * @methodInvocation remove
	 * @methodInvocation initCourts
	 * @methodInvocation remove
	 * @methodInvocation initCourts
	 * @methodInvocation remove
	 * @methodInvocation initCourts
	 * @methodInvocation remove
	 */
	public void removeCourts(SuggestedOrderCourt anObject)
	{
		initCourts();
		courts.remove(anObject);
	}

	/**
	 * Removes a pd.supervision.suggestedorder.SuggestedOrderOffense from class relationship collection.
	 * @methodInvocation initOffenses
	 * @methodInvocation remove
	 */
	public void removeOffenses(SuggestedOrderOffense anObject)
	{
		initOffenses();
		offenses.remove(anObject);
	}

	/**
	 * set the type reference for class member agency
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setAgencyId
	 */
	public void setAgency(Agency anAgency)
	{
		/*if (this.agency == null || !this.agency.equals(anAgency))
		{
			markModified();
		}
		if (anAgency.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(anAgency);
		}*/
		setAgencyId("" + anAgency.getAgencyId());
		this.agency = anAgency;//(pd.contact.agency.Agency) new mojo.km.persistence.Reference(anAgency).getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.agency.Agency
	 * @methodInvocation markModified
	 */
	public void setAgencyId(String anAgencyId)
	{
		if (this.agencyId == null || !this.agencyId.equals(anAgencyId))
		{
			markModified();
		}
		agency = null;
		this.agencyId = anAgencyId;
	}

	/**
	 * set the type reference for class member includedConditions
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setIncludedConditionsId
	 */
	public void setIncludedConditions(Code includedConditionInd)
	{
		if (this.includedConditions == null || !this.includedConditions.equals(includedConditionInd))
		{
			markModified();
		}
		if (includedConditionInd.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(includedConditionInd);
		}
		setIncludedConditionsId("" + includedConditionInd.getOID());
		this.includedConditions = (Code) new mojo.km.persistence.Reference(includedConditionInd)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setIncludedConditionsId(String includedConditionIndId)
	{
		if (this.includedConditionsId == null || !this.includedConditionsId.equals(includedConditionIndId))
		{
			markModified();
		}
		includedConditions = null;
		this.includedConditionsId = includedConditionIndId;
	}

	/**
	 * Sets the value of the orderDescription property.
	 * @param aOrderDescription the new value of the orderDescription property
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setOrderDescription(String aOrderDescription)
	{
		if (this.orderDescription == null || !this.orderDescription.equals(aOrderDescription))
		{
			markModified();
		}
		orderDescription = aOrderDescription;
	}

	/**
	 * Sets the value of the orderName property.
	 * @param aOrderName the new value of the orderName property
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setOrderName(String aOrderName)
	{
		if (this.orderName == null || !this.orderName.equals(aOrderName))
		{
			markModified();
		}
		orderName = aOrderName;
	}

	/**
	 * Sets the value of the suggestedOrderId property.
	 * @param aSuggestedOrderId the new value of the suggestedOrderId property
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setSuggestedOrderId(String aSuggestedOrderId)
	{
		if (this.suggestedOrderId == null || !this.suggestedOrderId.equals(aSuggestedOrderId))
		{
			markModified();
		}
		suggestedOrderId = aSuggestedOrderId;
	}
}

