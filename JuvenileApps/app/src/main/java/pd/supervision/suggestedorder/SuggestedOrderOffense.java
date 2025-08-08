package pd.supervision.suggestedorder;

import java.util.Iterator;

import mojo.km.context.multidatasource.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.criminal.OffenseCode;

/**
* @roseuid 433AB6160037
*/
public class SuggestedOrderOffense extends PersistentObject
{
	/**
	* Properties for offense
	* @referenceType pd.codetable.criminal.OffenseCode
	* @detailerDoNotGenerate false
	*/
	private OffenseCode offense = null;
	private String offenseId;

	/**
	* Properties for suggestedOrder
	* @referenceType pd.supervision.suggestedorder.SuggestedOrder
	* @detailerDoNotGenerate false
	*/
	private SuggestedOrder suggestedOrder = null;
	private String suggestedOrderId;

	private String suggestedOrderOffenseId;
	/**
	* @roseuid 433AB6160037
	*/
	public SuggestedOrderOffense()
	{
	}
	/**
	* @roseuid 433AA92801AF
	*/
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(attributeName, attributeValue, SuggestedOrderOffense.class);
		return iter;
	}
	/**
	* Get the reference value to class :: pd.supervision.suggestedorder.SuggestedOrder
	*/
	public String getSuggestedOrderId()
	{
		fetch();
		return suggestedOrderId;
	}
	/**
	* Access method for the suggestedOrderOffenseId property.
	* @return the current value of the suggestedOrderOffenseId property
	*/
	public String getSuggestedOrderOffenseId()
	{
		return "" + getOID();
	}
	/**
	* Set the reference value to class :: pd.supervision.suggestedorder.SuggestedOrder
	*/
	public void setSuggestedOrderId(String aSuggestedOrderId)
	{
		if (this.suggestedOrderId == null || !this.suggestedOrderId.equals(aSuggestedOrderId))
		{
			markModified();
		}
		this.suggestedOrderId = aSuggestedOrderId;
	}
	/**
	* Sets the value of the suggestedOrderOffenseId property.
	* @param aSuggestedOrderOffenseId the new value of the suggestedOrderOffenseId property
	*/
	public void setSuggestedOrderOffenseId(String aSuggestedOrderOffenseId)
	{
		if (this.suggestedOrderOffenseId == null || !this.suggestedOrderOffenseId.equals(aSuggestedOrderOffenseId))
		{
			markModified();
		}
		suggestedOrderOffenseId = aSuggestedOrderOffenseId;
	}
	/**
	* Set the reference value to class :: pd.codetable.criminal.OffenseCode
	*/
	public void setOffenseId(String aOffenseId)
	{
		if (this.offenseId == null || !this.offenseId.equals(aOffenseId))
		{
			markModified();
		}
		offense = null;
		this.offenseId = aOffenseId;
	}
	/**
	* Get the reference value to class :: pd.codetable.criminal.OffenseCode
	*/
	public String getOffenseId()
	{
		fetch();
		return offenseId;
	}
	/**
	* Initialize class relationship to class pd.codetable.criminal.OffenseCode
	*/
	private void initOffense()
	{
		if (offense == null)
		{
			offense =
				(OffenseCode) new mojo
					.km
					.persistence
					.Reference(offenseId, OffenseCode.class)
					.getObject();
		}
	}
	/**
	* Gets referenced type pd.codetable.criminal.OffenseCode
	*/
	public OffenseCode getOffense()
	{
		initOffense();
		return offense;
	}
	/**
	* set the type reference for class member offense
	*/
	public void setOffense(OffenseCode aOffense)
	{
		if (this.offense == null || !this.offense.equals(aOffense))
		{
			markModified();
		}
		if (aOffense.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aOffense);
		}
		setOffenseId("" + aOffense.getOID());
		this.offense = (OffenseCode) new mojo.km.persistence.Reference(aOffense).getObject();
	}
	/**
	* Initialize class relationship to class pd.supervision.suggestedorder.SuggestedOrder
	*/
	private void initSuggestedOrder()
	{
		if (suggestedOrder == null)
		{
			suggestedOrder =
				(SuggestedOrder) new mojo
					.km
					.persistence
					.Reference(suggestedOrderId, SuggestedOrder.class)
					.getObject();
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
		this.suggestedOrder =
			(SuggestedOrder) new mojo
				.km
				.persistence
				.Reference(aSuggestedOrder)
				.getObject();
	}
}
