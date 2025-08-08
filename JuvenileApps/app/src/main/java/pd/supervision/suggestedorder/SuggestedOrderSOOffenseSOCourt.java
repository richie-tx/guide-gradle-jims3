package pd.supervision.suggestedorder;

import pd.supervision.Court;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.contact.agency.Agency;
import pd.codetable.criminal.OffenseCode;
import mojo.km.context.multidatasource.Home;
import java.util.Iterator;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 */
public class SuggestedOrderSOOffenseSOCourt extends PersistentObject
{

	/**
	 * @return 
	 * @param event
	 * @methodInvocation findAll
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(event, SuggestedOrderSOOffenseSOCourt.class);
		return iter;
	}
	/**
	 * Properties for agency
	 */
	private Agency agency = null;
	private String agencyId;
	/**
	 * Properties for court
	 */
	private Court court = null;
	private String courtId;
	private String description;
	private String name;
	/**
	 * Properties for offense
	 */
	private OffenseCode offense = null;
	private String offenseId;
	/**
	 * Properties for suggestedOrder
	 */
	private SuggestedOrder suggestedOrder = null;
	/**
	 * Properties for suggestedOrderCourt
	 */
	private SuggestedOrderCourt suggestedOrderCourt = null;
	private String suggestedOrderCourtId;
	private String suggestedOrderId;
	/**
	 * Properties for suggestedOrderOffense
	 */
	private SuggestedOrderOffense suggestedOrderOffense = null;
	private String suggestedOrderOffenseId;

	/**
	 * Gets referenced type pd.contact.agency.Agency
	 * @methodInvocation fetch
	 * @methodInvocation initAgency
	 */
	public Agency getAgency()
	{
		fetch();
		initAgency();
		return agency;
	}

	/**
	 * Get the reference value to class :: pd.contact.agency.Agency
	 * @methodInvocation fetch
	 */
	public String getAgencyId()
	{
		fetch();
		return agencyId;
	}

	/**
	 * Gets referenced type pd.supervision.Court
	 * @methodInvocation fetch
	 * @methodInvocation initCourt
	 */
	public Court getCourt()
	{
		fetch();
		initCourt();
		return court;
	}

	/**
	 * Get the reference value to class :: pd.supervision.Court
	 * @methodInvocation fetch
	 */
	public String getCourtId()
	{
		fetch();
		return courtId;
	}

	/**
	 * @return Returns the description.
	 * @methodInvocation fetch
	 */
	public String getDescription()
	{
		fetch();
		return description;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName()
	{
		fetch();
		return name;
	}

	/**
	 * Gets referenced type pd.codetable.criminal.OffenseCode
	 * @methodInvocation fetch
	 * @methodInvocation initOffense
	 */
	public OffenseCode getOffense()
	{
		fetch();
		initOffense();
		return offense;
	}

	/**
	 * Get the reference value to class :: pd.codetable.criminal.OffenseCode
	 * @methodInvocation fetch
	 */
	public String getOffenseId()
	{
		fetch();
		return offenseId;
	}

	/**
	 * Gets referenced type pd.supervision.suggestedorder.SuggestedOrder
	 * @methodInvocation fetch
	 * @methodInvocation initSuggestedOrder
	 */
	public SuggestedOrder getSuggestedOrder()
	{
		fetch();
		initSuggestedOrder();
		return suggestedOrder;
	}

	/**
	 * Gets referenced type pd.supervision.suggestedorder.SuggestedOrderCourt
	 * @methodInvocation fetch
	 * @methodInvocation initSuggestedOrderCourt
	 */
	public SuggestedOrderCourt getSuggestedOrderCourt()
	{
		fetch();
		initSuggestedOrderCourt();
		return suggestedOrderCourt;
	}

	/**
	 * Get the reference value to class :: pd.supervision.suggestedorder.SuggestedOrderCourt
	 * @methodInvocation fetch
	 */
	public String getSuggestedOrderCourtId()
	{
		fetch();
		return suggestedOrderCourtId;
	}

	/**
	 * Get the reference value to class :: pd.supervision.suggestedorder.SuggestedOrder
	 * @methodInvocation fetch
	 */
	public String getSuggestedOrderId()
	{
		fetch();
		return suggestedOrderId;
	}

	/**
	 * Gets referenced type pd.supervision.suggestedorder.SuggestedOrderOffense
	 * @methodInvocation fetch
	 * @methodInvocation initSuggestedOrderOffense
	 */
	public SuggestedOrderOffense getSuggestedOrderOffense()
	{
		fetch();
		initSuggestedOrderOffense();
		return suggestedOrderOffense;
	}

	/**
	 * Get the reference value to class :: pd.supervision.suggestedorder.SuggestedOrderOffense
	 * @methodInvocation fetch
	 */
	public String getSuggestedOrderOffenseId()
	{
		fetch();
		return suggestedOrderOffenseId;
	}

	/**
	 * Initialize class relationship to class pd.contact.agency.Agency
	 */
	private void initAgency()
	{
		if (agency == null)
		{
			agency = Agency.find(agencyId);///(pd.contact.agency.Agency) new mojo.km.persistence.Reference(agencyId,
					//pd.contact.agency.Agency.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.Court
	 */
	private void initCourt()
	{
		if (court == null)
		{
			court = (Court) new mojo.km.persistence.Reference(courtId, Court.class)
					.getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.codetable.criminal.OffenseCode
	 */
	private void initOffense()
	{
		if (offense == null)
		{
			offense = (OffenseCode) new mojo.km.persistence.Reference(offenseId,
					OffenseCode.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.suggestedorder.SuggestedOrder
	 */
	private void initSuggestedOrder()
	{
		if (suggestedOrder == null)
		{
			suggestedOrder = (SuggestedOrder) new mojo.km.persistence.Reference(
					suggestedOrderId, SuggestedOrder.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.suggestedorder.SuggestedOrderCourt
	 */
	private void initSuggestedOrderCourt()
	{
		if (suggestedOrderCourt == null)
		{
			suggestedOrderCourt = (SuggestedOrderCourt) new mojo.km.persistence.Reference(
					suggestedOrderCourtId, SuggestedOrderCourt.class).getObject();
		}
	}

	/**
	 * Initialize class relationship to class pd.supervision.suggestedorder.SuggestedOrderOffense
	 */
	private void initSuggestedOrderOffense()
	{
		if (suggestedOrderOffense == null)
		{
			suggestedOrderOffense = (SuggestedOrderOffense) new mojo.km.persistence.Reference(
					suggestedOrderOffenseId, SuggestedOrderOffense.class).getObject();
		}
	}

	/**
	 * set the type reference for class member agency
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setAgencyId
	 */
	public void setAgency(Agency anAgency)
	{
		if (this.agency == null || !this.agency.equals(anAgency))
		{
			markModified();
		}
		if (anAgency.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(anAgency);
		}
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
	 * set the type reference for class member court
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setCourtId
	 */
	public void setCourt(Court aCourt)
	{
		if (this.court == null || !this.court.equals(aCourt))
		{
			markModified();
		}
		if (aCourt.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aCourt);
		}
		setCourtId("" + aCourt.getOID());
		this.court = (Court) new mojo.km.persistence.Reference(aCourt).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.Court
	 * @methodInvocation markModified
	 */
	public void setCourtId(String aCourtId)
	{
		if (this.courtId == null || !this.courtId.equals(aCourtId))
		{
			markModified();
		}
		court = null;
		this.courtId = aCourtId;
	}

	/**
	 * @param aDescription The description to set.
	 * @methodInvocation markModified
	 */
	public void setDescription(String aDescription)
	{
		if (this.description == null || !this.description.equals(aDescription))
		{
			markModified();
		}
		this.description = aDescription;
	}

	/**
	 * @param aName The name to set.
	 */
	public void setName(String aName)
	{
		if (this.name == null || !this.name.equals(aName))
		{
			markModified();
		}
		this.name = aName;
	}

	/**
	 * set the type reference for class member offense
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setOffenseId
	 */
	public void setOffense(OffenseCode anOffense)
	{
		if (this.offense == null || !this.offense.equals(anOffense))
		{
			markModified();
		}
		if (anOffense.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(anOffense);
		}
		setOffenseId("" + anOffense.getOID());
		this.offense = (OffenseCode) new mojo.km.persistence.Reference(anOffense).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.criminal.OffenseCode
	 * @methodInvocation markModified
	 */
	public void setOffenseId(String anOffenseId)
	{
		if (this.offenseId == null || !this.offenseId.equals(anOffenseId))
		{
			markModified();
		}
		offense = null;
		this.offenseId = anOffenseId;
	}

	/**
	 * set the type reference for class member suggestedOrder
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setSuggestedOrderId
	 */
	public void setSuggestedOrder(SuggestedOrder aSuggestedOrder)
	{
		if (this.suggestedOrder == null || !this.suggestedOrder.equals(aSuggestedOrder))
		{
			markModified();
		}
		if (aSuggestedOrder.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aSuggestedOrder);
		}
		setSuggestedOrderId("" + aSuggestedOrder.getOID());
		this.suggestedOrder = (SuggestedOrder) new mojo.km.persistence.Reference(
				aSuggestedOrder).getObject();
	}

	/**
	 * set the type reference for class member suggestedOrderCourt
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setSuggestedOrderCourtId
	 */
	public void setSuggestedOrderCourt(SuggestedOrderCourt aSuggestedOrderCourt)
	{
		if (this.suggestedOrderCourt == null || !this.suggestedOrderCourt.equals(aSuggestedOrderCourt))
		{
			markModified();
		}
		if (aSuggestedOrderCourt.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aSuggestedOrderCourt);
		}
		setSuggestedOrderCourtId("" + aSuggestedOrderCourt.getOID());
		this.suggestedOrderCourt = (SuggestedOrderCourt) new mojo.km.persistence.Reference(
				aSuggestedOrderCourt).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.suggestedorder.SuggestedOrderCourt
	 * @methodInvocation markModified
	 */
	public void setSuggestedOrderCourtId(String aSuggestedOrderCourtId)
	{
		if (this.suggestedOrderCourtId == null || !this.suggestedOrderCourtId.equals(aSuggestedOrderCourtId))
		{
			markModified();
		}
		suggestedOrderCourt = null;
		this.suggestedOrderCourtId = aSuggestedOrderCourtId;
	}

	/**
	 * Set the reference value to class :: pd.supervision.suggestedorder.SuggestedOrder
	 * @methodInvocation markModified
	 */
	public void setSuggestedOrderId(String aSuggestedOrderId)
	{
		if (this.suggestedOrderId == null || !this.suggestedOrderId.equals(aSuggestedOrderId))
		{
			markModified();
		}
		suggestedOrder = null;
		this.suggestedOrderId = aSuggestedOrderId;
	}

	/**
	 * set the type reference for class member suggestedOrderOffense
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setSuggestedOrderOffenseId
	 */
	public void setSuggestedOrderOffense(SuggestedOrderOffense aSuggestedOrderOffense)
	{
		if (this.suggestedOrderOffense == null || !this.suggestedOrderOffense.equals(aSuggestedOrderOffense))
		{
			markModified();
		}
		if (aSuggestedOrderOffense.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aSuggestedOrderOffense);
		}
		setSuggestedOrderOffenseId("" + aSuggestedOrderOffense.getOID());
		this.suggestedOrderOffense = (SuggestedOrderOffense) new mojo.km.persistence.Reference(
				aSuggestedOrderOffense).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.suggestedorder.SuggestedOrderOffense
	 * @methodInvocation markModified
	 */
	public void setSuggestedOrderOffenseId(String aSuggestedOrderOffenseId)
	{
		if (this.suggestedOrderOffenseId == null || !this.suggestedOrderOffenseId.equals(aSuggestedOrderOffenseId))
		{
			markModified();
		}
		suggestedOrderOffense = null;
		this.suggestedOrderOffenseId = aSuggestedOrderOffenseId;
	}
}

