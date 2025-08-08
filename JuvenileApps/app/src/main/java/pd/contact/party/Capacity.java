package pd.contact.party;

import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
* @author dgibler
*/
public class Capacity extends PersistentObject
{
	/**
	* Properties for capacity
	* @referencedType pd.codetable.Code
	* @contextKey *,CAPACITY,pd.codetable.Code
	*/
	private Code capacity = null;
	/**
	* Properties for party
	* @referencedType pd.contact.party.Party
	*/
	protected Party party = null;
	private String capacityId;
	private String partyId;
	/**
	* @roseuid 419A223D02FD
	*/
	public Capacity()
	{
	}
	/**
	* Set the reference value to class :: pd.contact.party.Party
	* @param aPartyId
	*/
	public void setPartyId(String aPartyId)
	{
		if (this.partyId == null || !this.partyId.equals(aPartyId))
		{
			markModified();
		}
		party = null;
		this.partyId = aPartyId;
	}
	/**
	* Get the reference value to class :: pd.contact.party.Party
	* @return partyId
	*/
	public String getPartyId()
	{
		fetch();
		return partyId;
	}
	/**
	* Initialize class relationship to class pd.contact.party.Party
	*/
	private void initParty()
	{
		if (party == null)
		{
			try
			{
				party =
					(Party) new mojo
						.km
						.persistence
						.Reference(partyId, Party.class)
						.getObject();
			}
			catch (Throwable t)
			{
				party = null;
			}
		}
	}
	/**
	* Gets referenced type pd.contact.party.Party
	* @return pd.contact.party.Party
	*/
	public Party getParty()
	{
		fetch();
		initParty();
		return party;
	}
	/**
	* set the type reference for class member party
	* @param aParty
	*/
	public void setParty(Party aParty)
	{
		if (this.party == null || !this.party.equals(aParty))
		{
			markModified();
		}
		if (aParty.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aParty);
		}
		setPartyId("" + aParty.getOID());
		this.party =
			(Party) new mojo.km.persistence.Reference(aParty).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param aCapacityId
	*/
	public void setCapacityId(String aCapacityId)
	{
		if (this.capacityId == null || !this.capacityId.equals(aCapacityId))
		{
			markModified();
		}
		capacity = null;
		this.capacityId = aCapacityId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return capacityId
	*/
	public String getCapacityId()
	{
		return "" + getOID();
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initCapacity()
	{
		if (capacity == null)
		{
			try
			{
				capacity =
					(Code) new mojo
						.km
						.persistence
						.Reference(capacityId, Code.class, "CAPACITY")
						.getObject();
			}
			catch (Throwable t)
			{
				capacity = null;
			}
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return pd.codetable.Code
	*/
	public Code getCapacity()
	{
		fetch();
		initCapacity();
		return capacity;
	}
	/**
	* set the type reference for class member capacity
	* @param aCapacity
	*/
	public void setCapacity(Code aCapacity)
	{
		if (this.capacity == null || !this.capacity.equals(aCapacity))
		{
			markModified();
		}
		if (aCapacity.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aCapacity);
		}
		setCapacityId("" + aCapacity.getOID());
		this.capacity =
			(Code) new mojo.km.persistence.Reference(aCapacity).getObject();
	}
}
