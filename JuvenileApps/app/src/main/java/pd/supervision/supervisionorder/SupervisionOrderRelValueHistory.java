package pd.supervision.supervisionorder;

import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
 * @roseuid 452158FB031E
 */
public class SupervisionOrderRelValueHistory extends PersistentObject {
	//private String currentValue; //Can retrieve from SupervisionOrderRelValue.

	private String priorValue;

	/**
	 * Properties for supervisionOrderConditionRelValue
	 */
	private SupervisionOrderConditionRelValue supervisionOrderConditionRelValue = null;

	/**
	 * Properties for transactionType
	 */
	private Code transactionType = null;

	private String supervisionOrderConditionRelValueId;

	private String transactionTypeId;

	/**
	 * @roseuid 452158FB031E
	 */
	public SupervisionOrderRelValueHistory() {
	}

	/**
	 * @roseuid 4521564B00E1
	 */
	public void bind() {
		markModified();
	}

	/**
	 * Set the reference value to class ::
	 * pd.supervision.supervisionorder.SupervisionOrderConditionRelValue
	 */
	public void setSupervisionOrderConditionRelValueId(
			String supervisionOrderConditionRelValueId) {
		if (this.supervisionOrderConditionRelValueId == null
				|| !this.supervisionOrderConditionRelValueId
						.equals(supervisionOrderConditionRelValueId)) {
			markModified();
		}
		supervisionOrderConditionRelValue = null;
		this.supervisionOrderConditionRelValueId = supervisionOrderConditionRelValueId;
	}

	/**
	 * Get the reference value to class ::
	 * pd.supervision.supervisionorder.SupervisionOrderConditionRelValue
	 */
	public String getSupervisionOrderConditionRelValueId() {
		fetch();
		return supervisionOrderConditionRelValueId;
	}

	/**
	 * Initialize class relationship to class
	 * pd.supervision.supervisionorder.SupervisionOrderConditionRelValue
	 */
	private void initSupervisionOrderConditionRelValue() {
		if (supervisionOrderConditionRelValue == null) {
			supervisionOrderConditionRelValue = (SupervisionOrderConditionRelValue) new mojo.km.persistence.Reference(
					supervisionOrderConditionRelValueId,
					SupervisionOrderConditionRelValue.class)
					.getObject();
		}
	}

	/**
	 * Gets referenced type
	 * pd.supervision.supervisionorder.SupervisionOrderConditionRelValue
	 */
	public SupervisionOrderConditionRelValue getSupervisionOrderConditionRelValue() {
		initSupervisionOrderConditionRelValue();
		return supervisionOrderConditionRelValue;
	}

	/**
	 * set the type reference for class member supervisionOrderConditionRelValue
	 */
	public void setSupervisionOrderConditionRelValue(
			SupervisionOrderConditionRelValue supervisionOrderConditionRelValue) {
		if (this.supervisionOrderConditionRelValue == null
				|| !this.supervisionOrderConditionRelValue
						.equals(supervisionOrderConditionRelValue)) {
			markModified();
		}
		if (supervisionOrderConditionRelValue.getOID() == null) {
			new mojo.km.persistence.Home()
					.bind(supervisionOrderConditionRelValue);
		}
		setSupervisionOrderConditionRelValueId(""
				+ supervisionOrderConditionRelValue.getOID());
		this.supervisionOrderConditionRelValue = (SupervisionOrderConditionRelValue) new mojo.km.persistence.Reference(
				supervisionOrderConditionRelValue).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setTransactionTypeId(String transactionTypeId) {
		if (this.transactionTypeId == null
				|| !this.transactionTypeId.equals(transactionTypeId)) {
			markModified();
		}
		transactionType = null;
		this.transactionTypeId = transactionTypeId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getTransactionTypeId() {
		fetch();
		return transactionTypeId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initTransactionType() {
		if (transactionType == null) {
			transactionType = (Code) new mojo.km.persistence.Reference(
					transactionTypeId, Code.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getTransactionType() {
		initTransactionType();
		return transactionType;
	}

	/**
	 * set the type reference for class member transactionType
	 */
	public void setTransactionType(Code transactionType) {
		if (this.transactionType == null
				|| !this.transactionType.equals(transactionType)) {
			markModified();
		}
		if (transactionType.getOID() == null) {
			new mojo.km.persistence.Home().bind(transactionType);
		}
		setTransactionTypeId("" + transactionType.getOID());
		this.transactionType = (Code) new mojo.km.persistence.Reference(
				transactionType).getObject();
	}

	public static SupervisionOrderRelValueHistory create(
			String transactionType, String prevValue) {
		SupervisionOrderRelValueHistory sorvh = new SupervisionOrderRelValueHistory();
		sorvh.setTransactionTypeId(transactionType);
		sorvh.setPriorValue(prevValue);

		return sorvh;
	}

//	/**
//	 * @return Returns the currentValue.
//	 */
//	public String getCurrentValue() {
//		fetch();
//		return currentValue;
//	}

//	/**
//	 * @param currentValue
//	 *            The currentValue to set.
//	 */
//	public void setCurrentValue(String aCurrentValue) {
//		if (this.currentValue == null
//				|| !this.currentValue.equals(aCurrentValue)) {
//			markModified();
//		}
//		this.currentValue = aCurrentValue;
//	}

	/**
	 * @return Returns the priorValue.
	 */
	public String getPriorValue() {
		fetch();
		return priorValue;
	}

	/**
	 * @param priorValue
	 *            The priorValue to set.
	 */
	public void setPriorValue(String aPriorValue) {
		if (this.priorValue == null || !this.priorValue.equals(aPriorValue)) {
			markModified();
		}
		this.priorValue = aPriorValue;
	}
}
