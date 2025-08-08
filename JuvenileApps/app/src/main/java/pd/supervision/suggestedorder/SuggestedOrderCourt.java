package pd.supervision.suggestedorder;

import java.util.Iterator;

import naming.SupervisionConstants;
import messaging.suggestedorder.reply.SuggestedOrderCourtResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.supervision.Court;

/**
* @roseuid 433AADCE0094
*/
public class SuggestedOrderCourt extends PersistentObject
{
	/**
	* Properties for court
	* @referencedType pd.supervisionoptions.Court
	* @detailerDoNotGenerate false
	*/
	private Court court = null;
	private String courtId;
	/**
	* Properties for suggestedOrder
	* @referenceType pd.supervision.suggestedorder.SuggestedOrder
	* @detailerDoNotGenerate false
	*/
	private SuggestedOrder suggestedOrder = null;
	private String suggestedOrderId;
	private String suggestedOrderCourtId;

	
	/**
	* @roseuid 433AA9280112
	*/
	public static Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		return home.findAll(attrName, attrValue, SuggestedOrderCourt.class);
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
	* Get the reference value to class :: pd.supervision.Court
	*/
	public String getCourtId()
	{
		fetch();
		return courtId;
	}
	/**
	 * Creates response event
	 * @return
	 */
	public SuggestedOrderCourtResponseEvent getResponseEvent()
	{
		Court aCourt = this.getCourt();
		SuggestedOrderCourtResponseEvent socre = new SuggestedOrderCourtResponseEvent();
		socre.setSuggestedOrderId(this.getSuggestedOrderId());
		socre.setSuggestedOrderCourtId(this.getSuggestedOrderCourtId());
		socre.setCourtId(this.getCourtId());

		if (aCourt != null)
		{
			socre.setCourtCategory(aCourt.getCourtCategory());
		}

		socre.setTopic(SupervisionConstants.SUGGESTED_ORDER_COURT_EVENT_TOPIC);

		return socre;
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
	* Access method for the suggestedOrderCourtId property.
	* @return the current value of the suggestedOrderCourtId property
	*/
	public String getSuggestedOrderCourtId()
	{
		return "" + getOID();
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
	* Initialize class relationship to class pd.supervision.Court
	*/
	private void initCourt()
	{
		if (court == null)
		{
			court =
				(Court) new mojo
					.km
					.persistence
					.Reference(courtId, Court.class)
					.getObject();
		}
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
	/**
	* Sets the value of the suggestedOrderCourtId property.
	* @param aSuggestedOrderCourtId the new value of the suggestedOrderCourtId property
	*/
	public void setSuggestedOrderCourtId(String aSuggestedOrderCourtId)
	{
		if (this.suggestedOrderCourtId == null || !this.suggestedOrderCourtId.equals(aSuggestedOrderCourtId))
		{
			markModified();
		}
		suggestedOrderCourtId = aSuggestedOrderCourtId;
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
}
