package pd.supervision.supervisionorder;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import pd.supervision.administercompliance.NonComplianceEvent;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionorder.SupervisionOrderCondition;

/**
* @roseuid 43B2E72E00AB
*/
public class SupervisionOrderConditionRel extends PersistentObject {
	private String supervisionOrderId;
	/**
	* Properties for orderConditionRelValues
	* @referencedType pd.supervision.supervisionorder.SupervisionOrderConditionRelValue
	*/
	private java.util.Collection orderConditionRelValues = null;
	private String supervisionOrderConditionId;
	/**
	* Properties for supervisionOrderCondition
	*/
	private SupervisionOrderCondition supervisionOrderCondition = null;
	private boolean wasDeleted;
	/**
	* Properties for supervisionOrder
	*/
	private SupervisionOrder supervisionOrder = null;
	private int sequenceNum;
	
	private boolean nonCompliant;
	private String complianceReasonId;
	private int ncCount;
	
	/**
	* @roseuid 43B2E72E00AB
	*/
	public SupervisionOrderConditionRel() {
	}
	/**
	* @roseuid 43B2B6EF00ED
	*/
	public void bind() {
		new Home().bind(this);
	}
	/**
	* Clears all pd.supervision.supervisionorder.SupervisionOrderConditionRelValue from class relationship collection.
	*/
	public void clearOrderConditionRelValues() {
		initOrderConditionRelValues();
		orderConditionRelValues.clear();
	}
	/**
	* returns a collection of pd.supervision.supervisionorder.SupervisionOrderConditionRelValue
	*/
	public java.util.Collection getOrderConditionRelValues() {
		initOrderConditionRelValues();
		return orderConditionRelValues;
	}
	/**
	* Gets referenced type pd.supervision.supervisionorder.SupervisionOrder
	*/
	public SupervisionOrder getSupervisionOrder() {
		initSupervisionOrder();
		return supervisionOrder;
	}
	/**
	* Gets referenced type pd.supervision.supervisionorder.SupervisionOrderCondition
	*/
	public SupervisionOrderCondition getSupervisionOrderCondition() {
		initSupervisionOrderCondition();
		return supervisionOrderCondition;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionorder.SupervisionOrderCondition
	*/
	public String getSupervisionOrderConditionId() {
		fetch();
		return supervisionOrderConditionId;
	}
	/**
	* Get the reference value to class :: pd.supervision.supervisionorder.SupervisionOrder
	*/
	public String getSupervisionOrderId() {
		fetch();
		return supervisionOrderId;
	}
	/**
	* @return 
	*/
	public boolean getWasDeleted() {
		fetch();
		return wasDeleted;
	}
	/**
	* Initialize class relationship implementation for pd.supervision.supervisionorder.SupervisionOrderConditionRelValue
	*/
	private void initOrderConditionRelValues() {
		if (orderConditionRelValues == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			orderConditionRelValues =
				new mojo.km.persistence.ArrayList(SupervisionOrderConditionRelValue.class, "supervisionOrderConditionRelId", "" + getOID());
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
	* Initialize class relationship to class pd.supervision.supervisionorder.SupervisionOrderCondition
	*/
	private void initSupervisionOrderCondition() {
		if (supervisionOrderCondition == null) {
			supervisionOrderCondition =
				(SupervisionOrderCondition) new mojo
					.km
					.persistence
					.Reference(supervisionOrderConditionId, SupervisionOrderCondition.class)
					.getObject();
		}
	}
	/**
	* insert a pd.supervision.supervisionorder.SupervisionOrderConditionRelValue into class relationship collection.
	*/
	public void insertOrderConditionRelValues(SupervisionOrderConditionRelValue anObject) {
		initOrderConditionRelValues();
		orderConditionRelValues.add(anObject);
	}
	/**
	* Removes a pd.supervision.supervisionorder.SupervisionOrderConditionRelValue from class relationship collection.
	*/
	public void removeOrderConditionRelValues(SupervisionOrderConditionRelValue anObject) {
		initOrderConditionRelValues();
		orderConditionRelValues.remove(anObject);
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
	* set the type reference for class member supervisionOrderCondition
	*/
	public void setSupervisionOrderCondition(SupervisionOrderCondition aSupervisionOrderCondition) {
		if (this.supervisionOrderCondition == null || !this.supervisionOrderCondition.equals(aSupervisionOrderCondition)) {
			markModified();
		}
		if (aSupervisionOrderCondition.getOID() == null) {
			new mojo.km.persistence.Home().bind(aSupervisionOrderCondition);
		}
		setSupervisionOrderConditionId("" + aSupervisionOrderCondition.getOID());
		this.supervisionOrderCondition =
			(SupervisionOrderCondition) new mojo.km.persistence.Reference(aSupervisionOrderCondition).getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.supervisionorder.SupervisionOrderCondition
	*/
	public void setSupervisionOrderConditionId(String aSupervisionOrderConditionId) {
		if (this.supervisionOrderConditionId == null || !this.supervisionOrderConditionId.equals(aSupervisionOrderConditionId)) {
			markModified();
		}
		supervisionOrderCondition = null;
		this.supervisionOrderConditionId = aSupervisionOrderConditionId;
	}
	/**
	* Set the reference value to class :: pd.supervision.supervisionorder.SupervisionOrder
	*/
	public void setSupervisionOrderId(String aSupervisionOrderId) {
		if (this.supervisionOrderId == null || !this.supervisionOrderId.equals(aSupervisionOrderId)) {
			markModified();
		}
		this.supervisionOrderId = aSupervisionOrderId;
	}
	/**
	* @param b
	*/
	public void setWasDeleted(boolean b) {
		if (this.wasDeleted != b) {
			markModified();
		}
		wasDeleted = b;
	}
	/**
	 * @return
	 */
	public int getSequenceNum()
	{
		fetch();
		return sequenceNum;
	}

	/**
	 * @param i
	 */
	public void setSequenceNum(int i)
	{
		if (this.sequenceNum == 0 || this.sequenceNum != i) {
			markModified();
		}
		sequenceNum = i;
	}
	
	/**
	 * @return Returns the compliant.
	 */
	public boolean isNonCompliant() {
		fetch();
		return nonCompliant;
	}
	/**
	 * @param compliant The compliant to set.
	 */
	public void setNonCompliant(boolean nonCompliant) {
		if (this.nonCompliant != nonCompliant) {
			markModified();
		}
		this.nonCompliant = nonCompliant;
	}
	/**
	 * @return Returns the ncCount.
	 */
	public int getNcCount() {
		fetch();
		return ncCount;
	}
	/**
	 * @param ncCount The ncCount to set.
	 */
	public void setNcCount(int ncCount) {
		if (this.ncCount != ncCount) {
			markModified();
		}
		this.ncCount = ncCount;
	}
	/**
	 * @return Returns the nonComplianceReasonId.
	 */
	public String getComplianceReasonId() {
		fetch();
		return complianceReasonId;
	}
	/**
	 * @param nonComplianceReasonId The nonComplianceReasonId to set.
	 */
	public void setComplianceReasonId(String complianceReasonId) {
		if (this.complianceReasonId == null || !this.complianceReasonId.equals(complianceReasonId)) {
			markModified();
		}
		this.complianceReasonId = complianceReasonId;
	}
	
	public static Iterator findAllByNumericParam(String attributeName, String attributeValue){
		return new Home().findAll(attributeName, new Integer(attributeValue), NonComplianceEvent.class);
	}
	
	public static Iterator findAll(IEvent event){
		return new Home().findAll(event, SupervisionOrderConditionRel.class);
	}
}
