package pd.supervision.suggestedorder;

import java.util.Iterator;

import pd.supervision.Court;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.contact.agency.Agency;

/**
 * @author dgibler
 */
public class SuggestedOrderSOCourt extends PersistentObject
{
	/**
	 * Properties for agency
	 */
	private Agency agency = null;
	/**
	 * Properties for court
	 */
	private Court court = null;
	/**
	 * Properties for suggestedOrder
	 */
	private SuggestedOrder suggestedOrder = null;
	private String description;
	private String name;
	private String agencyId;
	private String courtId;
	private String suggestedOrderId;

	/**
	 * @return Returns the description.
	 */
	public String getDescription()
	{
		fetch();
		return description;
	}

	/**
	 * @param aDescription
	 *            The description to set.
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
	 * @return Returns the name.
	 */
	public String getName()
	{
		fetch();
		return name;
	}

	/**
	 * @param aName
	 *            The name to set.
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
	 * Set the reference value to class :: pd.contact.agency.Agency
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
	 * Get the reference value to class :: pd.contact.agency.Agency
	 */
	public String getAgencyId()
	{
		fetch();
		return agencyId;
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
	 * Gets referenced type pd.contact.agency.Agency
	 */
	public Agency getAgency()
	{
		initAgency();
		return agency;
	}

	/**
	 * set the type reference for class member agency
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
	 * Set the reference value to class :: pd.supervision.Court
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
	 * Get the reference value to class :: pd.supervision.Court
	 */
	public String getCourtId()
	{
		fetch();
		return courtId;
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
	 * Gets referenced type pd.supervision.Court
	 */
	public Court getCourt()
	{
		initCourt();
		return court;
	}

	/**
	 * set the type reference for class member court
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
	 * Set the reference value to class ::
	 * pd.supervision.suggestedorder.SuggestedOrder
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
	 * Get the reference value to class ::
	 * pd.supervision.suggestedorder.SuggestedOrder
	 */
	public String getSuggestedOrderId()
	{
		fetch();
		return suggestedOrderId;
	}

	/**
	 * Initialize class relationship to class
	 * pd.supervision.suggestedorder.SuggestedOrder
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
	 * Gets referenced type pd.supervision.suggestedorder.SuggestedOrder
	 */
	public SuggestedOrder getSuggestedOrder()
	{
		initSuggestedOrder();
		return suggestedOrder;
	}

	/**
	 * set the type reference for class member suggestedOrder
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
	public static Iterator findAll(IEvent anEvent){
		IHome home = new Home();
		Iterator iter = home.findAll(anEvent, SuggestedOrderSOCourt.class);
		return iter;	
	}
}
