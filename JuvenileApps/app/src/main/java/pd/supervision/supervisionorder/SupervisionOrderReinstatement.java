package pd.supervision.supervisionorder;

import messaging.supervisionorder.ReinstateSupervisionOrderEvent;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.codetable.supervision.SupervisionCode;

import java.util.Date;

/**
* @roseuid 442169BD0155
*/
public class SupervisionOrderReinstatement extends PersistentObject {
	/**
	* @return 
	* @param reinstateEvent
	*/
	static public SupervisionOrderReinstatement create(ReinstateSupervisionOrderEvent reinstateEvent) {
		SupervisionOrderReinstatement reinstatement = new SupervisionOrderReinstatement();
		reinstatement.setReinstatementDate(reinstateEvent.getReinstatementDate());
		reinstatement.setReinstatementReasonId(reinstateEvent.getReinstatementReason());
		return reinstatement;
	}
	private Date reinstatementDate;
	/**
	* Properties for reinstatementReason
	* @referencedType pd.codetable.Code
	* @contextKey REINSTATEMENT_REASON
	* @detailerDoNotGenerate true
	*/
	private SupervisionCode reinstatementReason = null;
	private String reinstatementReasonId;
	/**
	* Properties for supervisionOrder
	*/
	private SupervisionOrder supervisionOrder = null;
	private String supervisionOrderId;
	/**
	* @roseuid 442169BD0155
	*/
	public SupervisionOrderReinstatement() {
	}
	/**
	* @roseuid 442071B602A7
	*/
	public void bind() {
		markModified();
	}
	/**
	* @return 
	*/
	public Date getReinstatementDate() {
		fetch();
		return reinstatementDate;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public SupervisionCode getReinstatementReason() {
		fetch();
		initReinstatementReason();
		return reinstatementReason;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getReinstatementReasonId() {
		fetch();
		return reinstatementReasonId;
	}
	/**
	* Gets referenced type pd.supervision.supervisionorder.SupervisionOrder
	*/
	public SupervisionOrder getSupervisionOrder() {
		initSupervisionOrder();
		return supervisionOrder;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionorder.SupervisionOrder
	*/
	public String getSupervisionOrderId() {
		fetch();
		return supervisionOrderId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initReinstatementReason() {
		if (reinstatementReason == null)
		{
			try
			{
				reinstatementReason =
					(SupervisionCode) new mojo
						.km
						.persistence
						.Reference(reinstatementReasonId, SupervisionCode.class)
						.getObject();
			}
			catch (Throwable t)
			{
			}
		}
		
	}
	/**
	* Initialize class relationship to class pd.supervision.supervisionorder.SupervisionOrder
	*/
	private void initSupervisionOrder() {
		if (supervisionOrder == null) {
			supervisionOrder =
				(SupervisionOrder) new mojo
					.km
					.persistence
					.Reference(supervisionOrderId, SupervisionOrder.class)
					.getObject();
		}
	}
	/**
	* @param aDate
	*/
	public void setReinstatementDate(Date aDate) {
		if (this.reinstatementDate == null || !this.reinstatementDate.equals(aDate)) {
			markModified();
		}
		reinstatementDate = aDate;
	}
	/**
	* set the type reference for class member aReinstatementReason
	*/
	public void setReinstatementReason(SupervisionCode aReinstatementReason) {
		
		if (this.reinstatementReason == null || !this.reinstatementReason.equals(aReinstatementReason))
		{
			markModified();
		}
		if (aReinstatementReason.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aReinstatementReason);
		}
		setReinstatementReasonId("" + aReinstatementReason.getOID());
		aReinstatementReason.setContext("REINSTATEMENT_REASON");
		this.reinstatementReason =
			(SupervisionCode) new mojo.km.persistence.Reference(aReinstatementReason).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setReinstatementReasonId(String aReinstatementReasonId) {
		if (this.reinstatementReasonId == null || !this.reinstatementReasonId.equals(aReinstatementReasonId)) {
			markModified();
		}
		reinstatementReason = null;
		this.reinstatementReasonId = aReinstatementReasonId;
	}
	/**
	* set the type reference for class member supervisionOrder
	*/
	public void setSupervisionOrder(SupervisionOrder aSupervisionOrder) {
		if (this.supervisionOrder == null || !this.supervisionOrder.equals(aSupervisionOrder)) {
			markModified();
		}
		if (aSupervisionOrder.getOID() == null) {
			new mojo.km.persistence.Home().bind(aSupervisionOrder);
		}
		setSupervisionOrderId("" + aSupervisionOrder.getOID());
		this.supervisionOrder = (SupervisionOrder) new mojo.km.persistence.Reference(aSupervisionOrder).getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.supervisionorder.SupervisionOrder
	*/
	public void setSupervisionOrderId(String aSupervisionOrderId) {
		if (this.supervisionOrderId == null || !this.supervisionOrderId.equals(aSupervisionOrderId)) {
			markModified();
		}
		supervisionOrder = null;
		this.supervisionOrderId = aSupervisionOrderId;
	}
}
