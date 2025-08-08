package pd.supervision.supervisionorder;

import messaging.supervisionorder.GetOrderConditionsFromIdsEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.supervision.Group;
import pd.supervision.supervisionoptions.Condition;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @roseuid 43B2E727030D
 */
public class SupervisionOrderCondition extends PersistentObject {
	private Date inactiveDate;

	private String name;

	private boolean isStandard;

	/**
	 * Properties for condition
	 */
	private Condition condition = null;

	private boolean isSpecialCondition;

	private String notes;

	private Date effectiveDate;

	private String description;

	private String resolvedDescription;

	private String conditionId;

	/**
	 * Properties for group
	 */
	private Group group = null;

	private String groupId;

	/**
	 * @roseuid 43B2E727030D
	 */
	public SupervisionOrderCondition() {
	}

	/**
	* @return 
	* @param event
	* @roseuid 438F22CA0277
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, SupervisionOrderCondition.class);
	}
	
	static public Iterator findAllByIds(List orderCondIds){
		GetOrderConditionsFromIdsEvent orderCondsEvent = new GetOrderConditionsFromIdsEvent();
		orderCondsEvent.setOrderConditionIds(orderCondIds);
		return findAll(orderCondsEvent);
	}
	
	static public SupervisionOrderCondition find(String oid){
		return (SupervisionOrderCondition) new Home().find(oid, SupervisionOrderCondition.class);
	}
	
	/**
	 * Finds all SupervisionOrderCondition by an attribute value
	 * 
	 * @return
	 * @param attributeName
	 * @param attributeValue
	 */
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator orderConditions = home.findAll(attributeName, attributeValue,
				SupervisionOrderCondition.class);
		return orderConditions;
	}

	/**
	 * Finds a SupervisionOrderCondition by an conditionId
	 * 
	 * @return
	 * @param attributeName
	 * @param attributeValue
	 */
	static public SupervisionOrderCondition findBySpecialCondition(
			String conditionId) {
		Iterator orderConditions = findAll("conditionId", conditionId);
		SupervisionOrderCondition orderCond = null;
		// There will be only one entry here as special condition is created
		// within the context of a SupervisionOrder
		if (orderConditions.hasNext()) {
			orderCond = (SupervisionOrderCondition) orderConditions.next();
		}
		return orderCond;
	}

	public static SupervisionOrderCondition create(Condition condition) {
		SupervisionOrderCondition supOrderCondition = new SupervisionOrderCondition();
		supOrderCondition.setConditionId(condition.getOID().toString());
		supOrderCondition.setDescription(condition.getDescription());
		supOrderCondition.setEffectiveDate(condition.getEffectiveDate());
		supOrderCondition.setInactiveDate(condition.getInactiveDate());
		supOrderCondition.setIsStandard(condition.getIsStandard());
		supOrderCondition.setName(condition.getName());
		supOrderCondition.setNotes(condition.getNotes());
		supOrderCondition.setIsSpecialCondition(condition.getIsSpecialCondition());
		supOrderCondition.setGroupId(condition.getGroupId());
		// generate OID
//		new Home().bind(supOrderCondition);

		return supOrderCondition;
	}

	/**
	 * Gets referenced type pd.supervision.supervisionoptions.Condition
	 */
	public Condition getCondition() {
		fetch();
		initCondition();
		return condition;
	}

	/**
	 * Get the reference value to class ::
	 * pd.supervision.supervisionoptions.Condition
	 */
	public String getConditionId() {
		fetch();
		return conditionId;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		fetch();
		return description;
	}

	/**
	 * @return
	 */
	public Date getEffectiveDate() {
		fetch();
		return effectiveDate;
	}

	/**
	 * @return
	 */
	public Date getInactiveDate() {
		fetch();
		return inactiveDate;
	}

	/**
	 * @return
	 */
	public String getName() {
		fetch();
		return name;
	}

	/**
	 * @return
	 */
	public String getNotes() {
		fetch();
		return notes;
	}

	/**
	 * Initialize class relationship to class
	 * pd.supervision.supervisionoptions.Condition
	 */
	private void initCondition() {
		if (condition == null) {
			condition = (Condition) new mojo.km.persistence.Reference(
					conditionId,
					Condition.class)
					.getObject();
		}
	}

	/**
	 * @return
	 */
	public boolean getIsSpecialCondition() {
		fetch();
		return isSpecialCondition;
	}

	/**
	 * @return
	 */
	public boolean getIsStandard() {
		fetch();
		return isStandard;
	}

	/**
	 * set the type reference for class member condition
	 */
	public void setCondition(
			Condition aCondition) {
		if (this.condition == null || !this.condition.equals(aCondition)) {
			markModified();
		}
		if (aCondition.getOID() == null) {
			new mojo.km.persistence.Home().bind(aCondition);
		}
		setConditionId("" + aCondition.getOID());
		this.condition = (Condition) new mojo.km.persistence.Reference(
				aCondition).getObject();
	}

	/**
	 * Set the reference value to class ::
	 * pd.supervision.supervisionoptions.Condition
	 */
	public void setConditionId(String aConditionId) {
		if (this.conditionId == null || !this.conditionId.equals(aConditionId)) {
			markModified();
		}
		condition = null;
		this.conditionId = aConditionId;
	}

	/**
	 * @param aDescription
	 */
	public void setDescription(String aDescription) {
		if (this.description == null || !this.description.equals(aDescription)) {
			markModified();
		}
		description = aDescription;
	}

	/**
	 * @param anEffectiveDate
	 */
	public void setEffectiveDate(Date anEffectiveDate) {
		if (this.effectiveDate == null
				|| !this.effectiveDate.equals(anEffectiveDate)) {
			markModified();
		}
		effectiveDate = anEffectiveDate;
	}

	/**
	 * @param aDate
	 */
	public void setInactiveDate(Date aDate) {
		if (this.inactiveDate == null || !this.inactiveDate.equals(aDate)) {
			markModified();
		}
		inactiveDate = aDate;
	}

	/**
	 * @param aName
	 */
	public void setName(String aName) {
		if (this.name == null || !this.name.equals(aName)) {
			markModified();
		}
		name = aName;
	}

	/**
	 * @param theNotes
	 */
	public void setNotes(String theNotes) {
		if (this.notes == null || !this.notes.equals(theNotes)) {
			markModified();
		}
		notes = theNotes;
	}

	/**
	 * @param b
	 */
	public void setIsSpecialCondition(boolean b) {
		if (this.isSpecialCondition != b) {
			markModified();
		}
		isSpecialCondition = b;
	}

	/**
	 * @param b
	 */
	public void setIsStandard(boolean b) {
		if (this.isStandard != b) {
			markModified();
		}
		isStandard = b;
	}

	/**
	 * @return
	 */
	public String getResolvedDescription() {
		fetch();
		return resolvedDescription;
	}

	/**
	 * @param string
	 */
	public void setResolvedDescription(String string) {
		if (this.resolvedDescription == null
				|| !this.resolvedDescription.equals(string)) {
			markModified();
		}
		resolvedDescription = string;
	}

	/**
	 * Gets referenced type pd.supervision.Group
	 */
	public Group getGroup() {
		fetch();
		initGroup();
		return group;
	}

	/**
	 * Get the reference value to class :: pd.supervision.Group
	 */
	public String getGroupId() {
		fetch();
		return groupId;
	}

	/**
	 * Initialize class relationship to class pd.supervision.Group
	 */
	private void initGroup() {
		if (group == null) {
			try {
				group = (Group) new mojo.km.persistence.Reference(
						groupId, Group.class).getObject();
			} catch (Throwable t) {
				group = null;
			}
		}
	}

	/**
	 * set the type reference for class member group
	 */
	public void setGroup(Group aGroup) {
		if (aGroup.getOID() == null) {
			new mojo.km.persistence.Home().bind(aGroup);
		}
		setGroupId(aGroup.getOID().toString());
		this.group = (Group) new mojo.km.persistence.Reference(
				aGroup).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.Group
	 */
	public void setGroupId(String aGroupId) {
		if (this.groupId == null || !this.groupId.equals(aGroupId)) {
			markModified();
		}
		group = null;
		this.groupId = aGroupId;
	}
}
