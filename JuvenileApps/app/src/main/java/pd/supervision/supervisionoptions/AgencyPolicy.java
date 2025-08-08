package pd.supervision.supervisionoptions;

import messaging.supervisionoptions.GetByIdsEvent;
import messaging.supervisionoptions.ValidateDepartmentPolicyEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.contact.agency.Agency;
import pd.supervision.Court;
import pd.supervision.Group;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Collection;
import java.util.Iterator;

/**
 * @roseuid 42F8BE70036B
 */
public class AgencyPolicy extends PersistentObject {
	private Date inactiveDate;

	private String name;

	private String groupId;

	private String notes;

	private String agencyId;

	/**
	 * Properties for courts
	 * 
	 * @associationType simple
	 * @referencedType pd.supervision.Court
	 */
	private Collection courts = null;

	/**
	 * Properties for agency
	 */
	private Agency agency = null;

	/**
	 * Properties for courts
	 * 
	 * @associationType simple
	 * @referencedType pd.supervision.supervisionoptions.Condition
	 */
	private Collection conditions = null;

	private Date effectiveDate;

	/**
	 * Properties for group
	 */
	private Group group = null;

	private String description;

	private String conditionId;

	/**
	 * @roseuid 42FA0101038A
	 */
	public AgencyPolicy() {
	}

	/**
	 * @return AgencyPolicy
	 * @param anAgencyPolicyId
	 */
	static public AgencyPolicy find(String anAgencyPolicyId) {
		IHome home = new Home();
		AgencyPolicy policy = (AgencyPolicy) home.find(anAgencyPolicyId,
				AgencyPolicy.class);
		return policy;
	}

	/**
	 * Finds agencyPolicies by an event
	 * 
	 * @return Iterator of AgencyPolicies
	 * @param event
	 */
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		Iterator policies = home.findAll(event, AgencyPolicy.class);
		return policies;
	}

	/**
	* Finds courtPolicies by an event
	* @return Iterator of CourtPolicies
	* @param event
	*/
	static public Iterator findAllByIds(Collection policyIds) {
	    GetByIdsEvent reqEvent = new GetByIdsEvent();
	    reqEvent.setOids(policyIds);
		Iterator policies = findAll(reqEvent);
		return policies;
	}
	
	/**
	 * Finds all agencyPolicies by an attribute value
	 * 
	 * @return
	 * @param attributeName
	 * @param attributeValue
	 */
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator policies = home.findAll(attributeName, attributeValue,
				AgencyPolicy.class);
		return policies;
	}

	/**
	 * @roseuid 42FA0102002F
	 */
	public void bind() {
		markModified();
	}

	/**
	 * Set the reference value to class :: pd.supervision.Group
	 * 
	 * @param aGroupId
	 * @roseuid 42FA0102003E
	 */
	public void setGroupId(String aGroupId) {
		if (this.groupId == null || !this.groupId.equals(aGroupId)) {
			markModified();
		}

//		validateAssociationsForGroups(aGroupId);

		group = null;
		this.groupId = aGroupId;
	}

	/**
	 * Get the reference value to class :: pd.supervision.Group
	 * 
	 * @return java.lang.String
	 * @roseuid 42FA0102004F
	 */
	public String getGroupId() {
		fetch();
		return groupId;
	}

	/**
	 * Initialize class relationship to class pd.supervision.Group
	 * 
	 * @roseuid 42FA0102005D
	 */
	private void initGroup() {
		if (group == null) {
			group = (Group) new mojo.km.persistence.Reference(
					groupId, Group.class).getObject();
		}
	}

	/**
	 * Set the reference value to class :: pd.contact.agency.Agency
	 * 
	 * @param anAgencyId
	 * @roseuid 42FA010200FB
	 */
	public void setAgencyId(String anAgencyId) {
		if (this.agencyId == null || !this.agencyId.equals(anAgencyId)) {
			markModified();
		}
		agency = null;
		this.agencyId = anAgencyId;
	}

	/**
	 * Get the reference value to class :: pd.contact.agency.Agency
	 * 
	 * @return java.lang.String
	 * @roseuid 42FA01020119
	 */
	public String getAgencyId() {
		fetch();
		return agencyId;
	}

	/**
	 * Initialize class relationship to class pd.contact.agency.Agency
	 * 
	 * @roseuid 42FA01020128
	 */
	private void initAgency() {
		if (agency == null) {
			agency = (Agency) new mojo.km.persistence.Reference(
					agencyId, Agency.class).getObject();
		}
	}

	/**
	 * Access method for the name property.
	 * 
	 * @return the current value of the name property
	 * @roseuid 42FA03C003B9
	 */
	public String getName() {
		fetch();
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param aName
	 *            the new value of the name property
	 * @roseuid 42FA03C003D8
	 */
	public void setName(String aName) {
		if (this.name == null || !this.name.equals(aName)) {
			markModified();
		}
		name = aName;
	}

	/**
	 * Access method for the description property.
	 * 
	 * @return the current value of the description property
	 * @roseuid 42FA03C10001
	 */
	public String getDescription() {
		fetch();
		return description;
	}

	/**
	 * Sets the value of the description property.
	 * 
	 * @param aDescription
	 *            the new value of the description property
	 * @roseuid 42FA03C1000F
	 */
	public void setDescription(String aDescription) {
		if (this.description == null || !this.description.equals(aDescription)) {
			markModified();
		}
		description = aDescription;
	}

	/**
	 * Access method for the effectiveDate property.
	 * 
	 * @return the current value of the effectiveDate property
	 * @roseuid 42FA03C1002E
	 */
	public Date getEffectiveDate() {
		fetch();
		return effectiveDate;
	}

	/**
	 * Sets the value of the effectiveDate property.
	 * 
	 * @param aEffectiveDate
	 *            the new value of the effectiveDate property
	 * @roseuid 42FA03C1003E
	 */
	public void setEffectiveDate(Date aEffectiveDate) {
		if (this.effectiveDate == null
				|| !this.effectiveDate.equals(aEffectiveDate)) {
			markModified();
		}
		effectiveDate = aEffectiveDate;
	}

	/**
	 * Access method for the notes property.
	 * 
	 * @return the current value of the notes property
	 * @roseuid 42FA03C1004E
	 */
	public String getNotes() {
		fetch();
		return notes;
	}

	/**
	 * Sets the value of the notes property.
	 * 
	 * @param aNotes
	 *            the new value of the notes property
	 * @roseuid 42FA03C1005D
	 */
	public void setNotes(String aNotes) {
		if (this.notes == null || !this.notes.equals(aNotes)) {
			markModified();
		}
		notes = aNotes;
	}

	/**
	 * Gets referenced type pd.supervision.Group
	 * 
	 * @return pd.supervision.Group
	 * @roseuid 42FA03C100BB
	 */
	public Group getGroup() {
		fetch();
		initGroup();
		return group;
	}

	/**
	 * set the type reference for class member group
	 * 
	 * @param aGroup
	 * @roseuid 42FA03C100DA
	 */
	public void setGroup(Group aGroup) {
		if (this.group == null || !this.group.equals(aGroup)) {
			markModified();
		}
		if (aGroup.getOID() == null) {
			new mojo.km.persistence.Home().bind(aGroup);
		}
		setGroupId("" + aGroup.getOID());
		this.group = (Group) new mojo.km.persistence.Reference(
				aGroup).getObject();
	}

	/**
	 * Gets referenced type pd.contact.agency.Agency
	 * 
	 * @return pd.contact.agency.Agency
	 * @roseuid 42FA03C101A5
	 */
	public Agency getAgency() {
		fetch();
		initAgency();
		return agency;
	}

	/**
	 * set the type reference for class member agency
	 * 
	 * @param anAgency
	 * @roseuid 42FA03C101C5
	 */
	public void setAgency(Agency anAgency) {
		if (this.agency == null || !this.agency.equals(anAgency)) {
			markModified();
		}
		if (anAgency.getOID() == null) {
			new mojo.km.persistence.Home().bind(anAgency);
		}
		setAgencyId("" + anAgency.getOID());
		this.agency = (Agency) new mojo.km.persistence.Reference(
				anAgency).getObject();
	}

	/**
	 * Set the reference value to class ::
	 * pd.supervision.supervisionoptions.Condition
	 */
	public void setConditionId(String aConditionId) {
		if (this.conditionId == null || !this.conditionId.equals(aConditionId)) {
			markModified();
		}
		this.conditionId = aConditionId;
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
	 * Gets referenced type pd.codetable.Code
	 */
	public String getStatus() {
		String status = PDCodeTableConstants.STATUS_CREATED;

		Calendar current = Calendar.getInstance();
		current.set(Calendar.HOUR_OF_DAY, 0);
		current.set(Calendar.MINUTE, 0);
		current.set(Calendar.MILLISECOND, 0);

		if (effectiveDate != null) {
			Calendar effective = Calendar.getInstance();
			effective.setTime(effectiveDate);
			effective.set(Calendar.HOUR_OF_DAY, 0);
			effective.set(Calendar.MINUTE, 0);
			effective.set(Calendar.MILLISECOND, 0);

			if (!effective.after(current)) {
				// not after == before or on.
				status = PDCodeTableConstants.STATUS_ACTIVE;
			}
		}

		if (inactiveDate != null) {
			Calendar inactive = Calendar.getInstance();
			inactive.setTime(inactiveDate);
			inactive.set(Calendar.HOUR_OF_DAY, 0);
			inactive.set(Calendar.MINUTE, 0);
			inactive.set(Calendar.MILLISECOND, 0);

			if (!inactive.after(current)) {
				// not after == before or on.
				status = PDCodeTableConstants.STATUS_INACTIVE;
			}
		}

		return status;
	}

	/**
	 * @return
	 */
	public Date getInactiveDate() {
		fetch();
		return inactiveDate;
	}

	/**
	 * @param string
	 */
	public void setInactiveDate(Date date) {
		if (this.inactiveDate == null || !this.inactiveDate.equals(date)) {
			markModified();
		}
		inactiveDate = date;
	}

	public boolean isDuplicate(String aPolicyId, String aName, String anAgencyId) {
		boolean result = false;
		ValidateDepartmentPolicyEvent reqEvent = new ValidateDepartmentPolicyEvent();
		// OID is of type numeric, hence set some numeric value if it is null
		if (aPolicyId == null || aPolicyId.equals("")) {
			aPolicyId = "0";
		}
		reqEvent.setAgencyId(anAgencyId);
		reqEvent.setName(aName);
		reqEvent.setPolicyId(aPolicyId);
		Iterator it = AgencyPolicy.findAll(reqEvent);
		if (it.hasNext()) {
			result = true;
		}
		return result;
	}

	/**
	 * Initialize class relationship implementation for pd.supervision.Court
	 */
	private void initCourts() {
		if (courts == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			courts = new mojo.km.persistence.ArrayList(
					AgencyPolicyCourtsCourt.class,
					"parentId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.supervision.Court
	 */
	public Collection getCourts() {
		initCourts();
		ArrayList retVal = new ArrayList();
		Iterator i = courts.iterator();
		while (i.hasNext()) {
			AgencyPolicyCourtsCourt actual = (AgencyPolicyCourtsCourt) i
					.next();
			if(actual.getChild()!=null){
				retVal.add(actual.getChild());
			}
		}
		return retVal;
	}

	/**
	 * insert a pd.supervision.Court into class relationship collection.
	 */
	public void insertCourts(Court anObject) {
		initCourts();
		AgencyPolicyCourtsCourt actual = new AgencyPolicyCourtsCourt();
		if (this.getOID() == null) {
			new Home().bind(this);
		}
		if (anObject.getOID() == null) {
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		courts.add(actual);
	}

	/**
	 * Removes a pd.supervision.Court from class relationship collection.
	 */
	public void removeCourts(Court anObject) {
		initCourts();
		mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
		assocEvent.setChildId((String) anObject.getOID());
		assocEvent.setParentId((String) this.getOID());
		AgencyPolicyCourtsCourt actual = (AgencyPolicyCourtsCourt) new mojo.km.persistence.Reference(
				assocEvent,
				AgencyPolicyCourtsCourt.class)
				.getObject();
		courts.remove(actual);
	}

	/**
	 * Clears all pd.supervision.Court from class relationship collection.
	 */
	public void clearCourts() {
		initCourts();
		courts.clear();
	}

	/**
	 * Initialize class relationship implementation for
	 * pd.supervision.supervisionoptions.Condition
	 */
	private void initConditions() {
		if (conditions == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			conditions = new mojo.km.persistence.ArrayList(
					AgencyPolicyConditionsCondition.class,
					"parentId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.supervision.supervisionoptions.Condition
	 */
	public Collection getConditions() {
		initConditions();
		ArrayList retVal = new ArrayList();
		Iterator i = conditions.iterator();
		while (i.hasNext()) {
			AgencyPolicyConditionsCondition actual = (AgencyPolicyConditionsCondition) i
					.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}

	/**
	 * insert a pd.supervision.supervisionoptions.Condition into class
	 * relationship collection.
	 */
	public void insertConditions(
			Condition anObject) {
		initConditions();
		AgencyPolicyConditionsCondition actual = new AgencyPolicyConditionsCondition();
		if (this.getOID() == null) {
			new Home().bind(this);
		}
		if (anObject.getOID() == null) {
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		conditions.add(actual);
	}

	/**
	 * Removes a pd.supervision.supervisionoptions.Condition from class
	 * relationship collection.
	 */
	public void removeConditions(
			Condition anObject) {
		initConditions();
		mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
		assocEvent.setChildId((String) anObject.getOID());
		assocEvent.setParentId((String) this.getOID());
		AgencyPolicyConditionsCondition actual = (AgencyPolicyConditionsCondition) new mojo.km.persistence.Reference(
				assocEvent,
				AgencyPolicyConditionsCondition.class)
				.getObject();
		conditions.remove(actual);
	}

	/**
	 * Clears all pd.supervision.supervisionoptions.Condition from class
	 * relationship collection.
	 */
	public void clearConditions() {
		initConditions();
		conditions.clear();
	}

	/**
	 * Call this when courts have been removed so that the associations to
	 * AgencyPolicy and CourtPolicy can be validated and removed if necessary.
	 * This is done here instead in the collection remove method because all of
	 * the changes must be made before the associations can be validated.
	 */
	public void validateAssociationsForCourts() {
		Iterator iter = disassociationsForCourts(getAllCourtIds()).iterator();
		while (iter.hasNext()) {
			Condition cond = (Condition) iter.next();
			removeConditions(cond);
		}
	}

	public Collection disassociationsForCourts(Collection ids) {
		StringSet policyCourtIds = new StringSet(ids);
		ArrayList disassociatedConditions = new ArrayList();

		Iterator iter = getConditions().iterator();
		while (iter.hasNext()) {
			Condition cond = (Condition) iter.next();
			StringSet condCourtIds = cond.getAllCourtIds();

			if (policyCourtIds.intersection(condCourtIds).isEmpty()) {
				disassociatedConditions.add(cond);
			}
		}
		return disassociatedConditions;
	}

	/**
	 * This is called internally every time a group changes.
	 * 
	 * @param newGroupId
	 */
	public void validateAssociationsForGroups(String newGroupId) {
		if (groupId == null || newGroupId == null || groupId.equals(newGroupId)) {
			// 1. groupId == null means no associations.
			// 2. newGroupId == null is invalid.
			// 3. groupId == newGroupId means no change.
			return;
		}

		Iterator iter = disassociationsForGroups(newGroupId).iterator();
		while (iter.hasNext()) {
			Condition cond = (Condition) iter.next();
			removeConditions(cond);
		}
	}

	public Collection disassociationsForGroups(String newGroupId) {
		ArrayList disassociatedConditions = new ArrayList();

		if (groupId == null || newGroupId == null || groupId.equals(newGroupId)) {
			// 1. groupId == null means no associations.
			// 2. newGroupId == null is invalid.
			// 3. groupId == newGroupId means no change.
			return disassociatedConditions;
		}

		Group oldGroup = Group.find(groupId);
		Group newGroup = Group.find(newGroupId);

		Group[] oldGroups = oldGroup.getGroupList();
		Group[] newGroups = newGroup.getGroupList();

		if (!oldGroups[0].equals(newGroups[0])) {
			disassociatedConditions.addAll(getConditions());
		} else if (newGroups[1] != null && !newGroups[1].equals(oldGroups[1])) {
			// Group 2 has changed and must be validated against each
			// association.
			Group[] assocGroups = null;

			Iterator iter = getConditions().iterator();
			while (iter.hasNext()) {
				Condition cond = (Condition) iter.next();
				assocGroups = cond.getGroup().getGroupList();

				// Reqs: when the G2 value is null, then there is no change to
				// the association
				if (assocGroups[1] != null/*
										   * && !
										   * assocGroups[1].equals(newGroups[1])
										   */) {
					// Group 2 exists and does not match, remove it.
					disassociatedConditions.add(cond);
				}
			}
		}
		return disassociatedConditions;
	}

	public StringSet getAllCourtIds() {
		StringSet courtIds = new StringSet();

		Iterator courts = getCourts().iterator();
		while (courts.hasNext()) {
			courtIds.add(((Court) courts.next()).getOID().toString());
		}

		return courtIds;
	}

}
