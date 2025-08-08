package pd.supervision.supervisionoptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.supervisionoptions.GetByIdsEvent;
import messaging.supervisionoptions.ValidateCourtPolicyEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.contact.agency.Agency;
import pd.supervision.Group;

/**
* @roseuid 42F8BE8A0128
*/
public class CourtPolicy extends PersistentObject {
	/**
	* Properties for supervisionEventTypes
	* @associationType simple
	* @referencedType pd.codetable.supervision.SupervisionCode
	* @detailerDoNotGenerate false
	*/
	private Collection supervisionEventTypes = null;
	/**
	* Properties for policySupervisionOptions
	* @referencedType pd.supervision.supervisionoptions.PolicySupervisionOption
	* @detailerDoNotGenerate false
	*/
	private Collection policySupervisionOptions = null;
	private Date effectiveDate;
	private int eventCount;
	private String periodId;
	/**
	* Properties for conditions
	* @associationType simple
	* @referencedType pd.supervision.supervisionoptions.Condition
	*/
	private Collection conditions = null;
	/**
	* Properties for group
	*/
	public Group group = null;
	/**
	* Properties for agency
	*/
	public Agency agency = null;
	private String agencyId;
	private String groupId;
	private String notes;
	private Date inactiveDate;
	private int periodValue;
	private boolean isDepartmentPolicy;
	private boolean isStandard;
	private String description;
	/**
	* Properties for period
	* @contextKey SUPERVISION_PERIOD
	*/
	public Code period = null;
	private String name;
	/**
	* @return CourtPolicy
	* @param courtPolicyId
	*/
	static public CourtPolicy find(String courtPolicyId) {
		IHome home = new Home();
		CourtPolicy policy = (CourtPolicy) home.find(courtPolicyId, CourtPolicy.class);
		return policy;
	}
	/**
	* Finds courtPolicies by an event
	* @return Iterator of CourtPolicies
	* @param event
	*/
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		Iterator policies = home.findAll(event, CourtPolicy.class);
		return policies;
	}
	/**
	* Finds all courtPolicies by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator policies = home.findAll(attributeName, attributeValue, CourtPolicy.class);
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
	* @roseuid 42F8BE8A0128
	*/
	public CourtPolicy() {
	}
	/**
	* Access method for the name property.
	* @return the current value of the name property
	*/
	public String getName() {
		fetch();
		return name;
	}
	/**
	* Sets the value of the name property.
	* @param aName the new value of the name property
	*/
	public void setName(String aName) {
		if (this.name == null || !this.name.equals(aName)) {
			markModified();
		}
		name = aName;
	}
	/**
	* Determines if the isStandard property is true.
	* @return <code>true<code> if the isStandard property is true
	*/
	public boolean getIsStandard() {
		fetch();
		return isStandard;
	}
	/**
	* Sets the value of the isStandard property.
	* @param aIsStandard the new value of the isStandard property
	*/
	public void setIsStandard(boolean aIsStandard) {
		if (this.isStandard != aIsStandard) {
			markModified();
		}
		isStandard = aIsStandard;
	}
	/**
	* Access method for the description property.
	* @return the current value of the description property
	*/
	public String getDescription() {
		fetch();
		return description;
	}
	/**
	* Sets the value of the description property.
	* @param aDescription the new value of the description property
	*/
	public void setDescription(String aDescription) {
		if (this.description == null || !this.description.equals(aDescription)) {
			markModified();
		}
		description = aDescription;
	}
	/**
	* Access method for the effectiveDate property.
	* @return the current value of the effectiveDate property
	*/
	public Date getEffectiveDate() {
		fetch();
		return effectiveDate;
	}
	/**
	* Sets the value of the effectiveDate property.
	* @param aEffectiveDate the new value of the effectiveDate property
	*/
	public void setEffectiveDate(Date aEffectiveDate) {
		if (this.effectiveDate == null || !this.effectiveDate.equals(aEffectiveDate)) {
			markModified();
		}
		effectiveDate = aEffectiveDate;
	}
	/**
	* Access method for the notes property.
	* @return the current value of the notes property
	*/
	public String getNotes() {
		fetch();
		return notes;
	}
	/**
	* Sets the value of the notes property.
	* @param aNotes the new value of the notes property
	*/
	public void setNotes(String aNotes) {
		if (this.notes == null || !this.notes.equals(aNotes)) {
			markModified();
		}
		notes = aNotes;
	}
	/**
	* Access method for the inactiveDate property.
	* @return the current value of the inactiveDate property
	*/
	public Date getInactiveDate() {
		fetch();
		return inactiveDate;
	}
	/**
	* Sets the value of the inactiveDate property.
	* @param aInactiveDate the new value of the inactiveDate property
	*/
	public void setInactiveDate(Date aInactiveDate) {
		if (this.inactiveDate == null || !this.inactiveDate.equals(aInactiveDate)) {
			markModified();
		}
		inactiveDate = aInactiveDate;
	}
	/**
	* Access method for the eventCount property.
	* @return the current value of the eventCount property
	*/
	public int getEventCount() {
		fetch();
		return eventCount;
	}
	/**
	* Sets the value of the eventCount property.
	* @param aEventCount the new value of the eventCount property
	*/
	public void setEventCount(int aEventCount) {
		if (this.eventCount != aEventCount) {
			markModified();
		}
		eventCount = aEventCount;
	}
	/**
	* Access method for the periodValue property.
	* @return the current value of the periodValue property
	*/
	public int getPeriodValue() {
		fetch();
		return periodValue;
	}
	/**
	* Sets the value of the periodValue property.
	* @param aPeriodValue the new value of the periodValue property
	*/
	public void setPeriodValue(int aPeriodValue) {
		if (this.periodValue != aPeriodValue) {
			markModified();
		}
		periodValue = aPeriodValue;
	}
	/**
	* @roseuid 42F7997D01C5
	*/
	public void bind() {
		markModified();
	}
	/**
	* Set the reference value to class :: pd.contact.agency.Agency
	*/
	public void setAgencyId(String agencyId) {
		if (this.agencyId == null || !this.agencyId.equals(agencyId)) {
			markModified();
		}
		agency = null;
		this.agencyId = agencyId;
	}
	/**
	* Get the reference value to class :: pd.contact.agency.Agency
	*/
	public String getAgencyId() {
		fetch();
		return agencyId;
	}
	/**
	* Initialize class relationship to class pd.contact.agency.Agency
	*/
	private void initAgency() {
		if (agency == null) {
			try {
				agency = (Agency) new mojo.km.persistence.Reference(agencyId, Agency.class).getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.contact.agency.Agency
	*/
	public Agency getAgency() {
		fetch();
		initAgency();
		return agency;
	}
	/**
	* set the type reference for class member agency
	*/
	public void setAgency(Agency agency) {
		if (this.agency == null || !this.agency.equals(agency)) {
			markModified();
		}
		if (agency.getOID() == null) {
			new mojo.km.persistence.Home().bind(agency);
		}
		setAgencyId("" + agency.getOID());
		this.agency = (Agency) new mojo.km.persistence.Reference(agency).getObject();
	}
	/**
	* Initialize class relationship implementation for pd.supervision.supervisionoptions.Condition
	*/
	private void initConditions() {
		if (conditions == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
				conditions = new mojo.km.persistence.ArrayList(CourtPolicyConditionsCondition.class, "parentId", "" + getOID());
			} catch (Throwable t) {
				conditions = new ArrayList();
			}
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
			CourtPolicyConditionsCondition actual = (CourtPolicyConditionsCondition) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}
	/**
	* insert a pd.supervision.supervisionoptions.Condition into class relationship collection.
	*/
	public void insertConditions(Condition anObject) {
		initConditions();
		CourtPolicyConditionsCondition actual = new CourtPolicyConditionsCondition();
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
	* Removes a pd.supervision.supervisionoptions.Condition from class relationship collection.
	*/
	public void removeConditions(Condition anObject) {
		initConditions();
		try {
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) anObject.getOID());
			assocEvent.setParentId((String) this.getOID());
			CourtPolicyConditionsCondition actual =
				(CourtPolicyConditionsCondition) new mojo
					.km
					.persistence
					.Reference(assocEvent, CourtPolicyConditionsCondition.class)
					.getObject();
			conditions.remove(actual);
		} catch (Throwable t) {
		}
	}
	/**
	* Clears all pd.supervision.supervisionoptions.Condition from class relationship collection.
	*/
	public void clearConditions() {
		initConditions();
		conditions.clear();
	}
	/**
	* Set the reference value to class :: pd.supervision.Group
	*/
	public void setGroupId(String groupId) {
		if (this.groupId == null || !this.groupId.equals(groupId)) {
			markModified();
		}
		
//		validateAssociationsForGroups( groupId );
		
		group = null;
		this.groupId = groupId;
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
				group = (Group) new mojo.km.persistence.Reference(groupId, Group.class).getObject();
			} catch (Throwable t) {
			}
		}
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
	* set the type reference for class member group
	*/
	public void setGroup(Group group) {
		if (this.group == null || !this.group.equals(group)) {
			markModified();
		}
		if (group.getOID() == null) {
			new mojo.km.persistence.Home().bind(group);
		}
		setGroupId("" + group.getOID());
		this.group = (Group) new mojo.km.persistence.Reference(group).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setPeriodId(String periodId) {
		if (this.periodId == null || !this.periodId.equals(periodId)) {
			markModified();
		}
		period = null;
		this.periodId = periodId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getPeriodId() {
		fetch();
		return periodId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initPeriod() {
		if (period == null) {
			try {
				period = (Code) new mojo.km.persistence.Reference(periodId, Code.class, "SUPERVISION_PERIOD").getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getPeriod() {
		fetch();
		initPeriod();
		return period;
	}
	/**
	* set the type reference for class member period
	*/
	public void setPeriod(Code period) {
		if (this.period == null || !this.period.equals(period)) {
			markModified();
		}
		if (period.getOID() == null) {
			new mojo.km.persistence.Home().bind(period);
		}
		setPeriodId("" + period.getOID());
		this.period = (Code) new mojo.km.persistence.Reference(period).getObject();
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public String getStatus() {
		String status = PDCodeTableConstants.STATUS_CREATED;
		
		Calendar current = Calendar.getInstance();
		current.set( Calendar.HOUR_OF_DAY, 0 );
		current.set( Calendar.MINUTE, 0 );
		current.set( Calendar.MILLISECOND, 0 );
		
		if ( effectiveDate != null )
		{
			Calendar effective = Calendar.getInstance();
			effective.setTime( effectiveDate );
			effective.set( Calendar.HOUR_OF_DAY, 0 );
			effective.set( Calendar.MINUTE, 0 );
			effective.set( Calendar.MILLISECOND, 0 );
			
			if ( ! effective.after(current) )
			{
				// not after == before or on.
				status = PDCodeTableConstants.STATUS_ACTIVE;
			}
		}
		
		if ( inactiveDate != null )
		{
			Calendar inactive = Calendar.getInstance();
			inactive.setTime( inactiveDate );
			inactive.set( Calendar.HOUR_OF_DAY, 0 );
			inactive.set( Calendar.MINUTE, 0 );
			inactive.set( Calendar.MILLISECOND, 0 );
			
			if ( ! inactive.after(current) )
			{
				// not after == before or on.
				status = PDCodeTableConstants.STATUS_INACTIVE;
			}
		}
		
		return status;
	}
	/**
	* @return 
	*/
	public boolean isDepartmentPolicy() {
		fetch();
		return isDepartmentPolicy;
	}
	
	public boolean getIsDepartmentPolicy() {
		return isDepartmentPolicy();
	}
	/**
	* @param b
	*/
	public void setDepartmentPolicy(boolean b) {
		if (isDepartmentPolicy != b) {
			markModified();
			isDepartmentPolicy = b;
		}
	}
	public void setIsDepartmentPolicy(boolean b) {
		setDepartmentPolicy(b);
	}
	/**
	* Initialize class relationship implementation for pd.codetable.supervision.SupervisionCode
	*/
	private void initSupervisionEventTypes() {
		if (supervisionEventTypes == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
				supervisionEventTypes =
					new mojo.km.persistence.ArrayList(CourtPolicySupervisionEventTypesSupervisionCode.class, "parentId", "" + getOID());
			} catch (Throwable t) {
				supervisionEventTypes = new ArrayList();
			}
		}
	}
	/**
	* returns a collection of pd.codetable.supervision.SupervisionCode
	*/
	public Collection getSupervisionEventTypes() {
		initSupervisionEventTypes();
		ArrayList retVal = new ArrayList();
		Iterator i = supervisionEventTypes.iterator();
		while (i.hasNext()) {
			CourtPolicySupervisionEventTypesSupervisionCode actual =
				(CourtPolicySupervisionEventTypesSupervisionCode) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}
	/**
	* insert a pd.codetable.supervision.SupervisionCode into class relationship collection.
	*/
	public void insertSupervisionEventTypes(pd.codetable.supervision.SupervisionCode anObject) {
		initSupervisionEventTypes();
		CourtPolicySupervisionEventTypesSupervisionCode actual =
			new CourtPolicySupervisionEventTypesSupervisionCode();
		if (this.getOID() == null) {
			new Home().bind(this);
		}
		if (anObject.getOID() == null) {
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		supervisionEventTypes.add(actual);
	}
	/**
	* Removes a pd.codetable.supervision.SupervisionCode from class relationship collection.
	*/
	public void removeSupervisionEventTypes(pd.codetable.supervision.SupervisionCode anObject) {
		initSupervisionEventTypes();
		try {
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) anObject.getOID());
			assocEvent.setParentId((String) this.getOID());
			CourtPolicySupervisionEventTypesSupervisionCode actual =
				(CourtPolicySupervisionEventTypesSupervisionCode) new mojo
					.km
					.persistence
					.Reference(assocEvent, CourtPolicySupervisionEventTypesSupervisionCode.class)
					.getObject();
			supervisionEventTypes.remove(actual);
		} catch (Throwable t) {
		}
	}
	/**
	* Clears all pd.codetable.supervision.SupervisionCode from class relationship collection.
	*/
	public void clearSupervisionEventTypes() {
		initSupervisionEventTypes();
		supervisionEventTypes.clear();
	}
	/**
	* Initialize class relationship implementation for pd.supervision.supervisionoptions.PolicySupervisionOption
	*/
	private void initPolicySupervisionOptions() {
		if (policySupervisionOptions == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			try {
				policySupervisionOptions = new mojo.km.persistence.ArrayList(PolicySupervisionOption.class, "policyId", "" + getOID());
			} catch (Throwable t) {
				policySupervisionOptions = new ArrayList();
			}
		}
	}
	/**
	* returns a collection of pd.supervision.supervisionoptions.PolicySupervisionOption
	*/
	public Collection getPolicySupervisionOptions() {
		initPolicySupervisionOptions();
		return policySupervisionOptions;
	}
	/**
	* insert a pd.supervision.supervisionoptions.PolicySupervisionOption into class relationship collection.
	*/
	public void insertPolicySupervisionOptions(PolicySupervisionOption anObject) {
		initPolicySupervisionOptions();
		policySupervisionOptions.add(anObject);
	}
	/**
	* Removes a pd.supervision.supervisionoptions.PolicySupervisionOption from class relationship collection.
	*/
	public void removePolicySupervisionOptions(PolicySupervisionOption anObject) {
		initPolicySupervisionOptions();
		policySupervisionOptions.remove(anObject);
	}
	/**
	* Clears all pd.supervision.supervisionoptions.PolicySupervisionOption from class relationship collection.
	*/
	public void clearPolicySupervisionOptions() {
		initPolicySupervisionOptions();
		policySupervisionOptions.clear();
	}
	
	public boolean isDuplicate(String courtPolicyId, String name, String agencyId){
		boolean result = false;
		ValidateCourtPolicyEvent reqEvent = new ValidateCourtPolicyEvent();
		// OID is of type numeric, hence set some numeric value if it is null
		if(courtPolicyId == null || courtPolicyId.equals("")){
			courtPolicyId = "0";
		}
		reqEvent.setAgencyId(agencyId);
		reqEvent.setName(name);
		reqEvent.setPolicyId(courtPolicyId);
		Iterator it = CourtPolicy.findAll(reqEvent);
		if(it.hasNext()){
			result = true;
		}
		return result;	
	}

	/**
	 * Call this when courts (ConditionSupervisionOption) have been removed so that the associations 
	 * to AgencyPolicy and CourtPolicy can be validated and removed if necessary. This is done here 
	 * instead in the collection remove method because all of the court changes must be completed 
	 * before the associations can be validated.     
	 */
	public void validateAssociationsForCourts()
	{
		Iterator iter = disassociationsForCourts( getAllCourtIds() ).iterator();
		while ( iter.hasNext() )
		{
			Condition cond = (Condition)iter.next();
			removeConditions(cond); 
		}
	}

	/***
	 * See validateAssociationsForCourts(). This method determines the Conditions to remove 
	 * but does not remove them.
	 *    
	 * @param ids
	 * @return
	 */	
	public Collection disassociationsForCourts( Collection ids )
	{
		StringSet policyCourtIds = new StringSet( ids );
		ArrayList disassociatedConditions = new ArrayList();
		
		Iterator iter = getConditions().iterator();
		while ( iter.hasNext() )
		{
			Condition cond = (Condition)iter.next();
			StringSet condCourtIds = cond.getAllCourtIds();

			if ( policyCourtIds.intersection(condCourtIds).isEmpty() )
			{
				disassociatedConditions.add(cond); 
			}
		}
		
		return disassociatedConditions;
	}
	
	/**
	 * This is called internally every time a group changes. 
	 * @param newGroupId
	 */
	public void validateAssociationsForGroups( String newGroupId )
	{
		if ( groupId == null || newGroupId == null || groupId.equals(newGroupId) )
		{
			// 1. groupId == null means no associations. 
			// 2. newGroupId == null is invalid.
			// 3. groupId == newGroupId means no change.
			return;
		}
		
		Iterator iter = disassociationsForGroups( newGroupId ).iterator();
		while ( iter.hasNext() )
		{
			Condition cond = (Condition)iter.next();
			removeConditions(cond); 
		}
	}
	
	public Collection disassociationsForGroups( String newGroupId )
	{
		ArrayList disassociatedConditions = new ArrayList();
		
		if ( groupId == null || newGroupId == null || groupId.equals(newGroupId) )
		{
			// 1. groupId == null means no associations. 
			// 2. newGroupId == null is invalid.
			// 3. groupId == newGroupId means no change.
			return disassociatedConditions;
		}
		
		Group oldGroup = Group.find( groupId );
		Group newGroup = Group.find( newGroupId );
		
		Group[] oldGroups = oldGroup.getGroupList();
		Group[] newGroups = newGroup.getGroupList();
		
		if ( ! oldGroups[0].equals(newGroups[0]) )
		{
			disassociatedConditions.addAll( getConditions() );
		}
		else if ( newGroups[1] != null && ! newGroups[1].equals(oldGroups[1]) )
		{
			// Group 2 has changed and must be validated against each association.
			Group[] assocGroups = null;
			
			Iterator iter = getConditions().iterator();
			while ( iter.hasNext() )
			{
				Condition cond = (Condition)iter.next();
				assocGroups = cond.getGroup().getGroupList();
				// Reqs: when the G2 value is null, then there is no change to the association
				if ( assocGroups[1] != null/* && ! assocGroups[1].equals(newGroups[1])*/ )
				{
					// Group 2 exists and does not match, remove it.
					disassociatedConditions.add( cond );
				}
			}
		}
		return disassociatedConditions;
	}
	



	public StringSet getStandardCourtIds()
	{
		StringSet set = new StringSet();
		
		Iterator optionIter = getPolicySupervisionOptions().iterator();
		while ( optionIter.hasNext() )
		{
			CourtSupervisionOption option = (CourtSupervisionOption)optionIter.next();
			if ( ! option.getIsExceptionCourt() )
			{
				set.add( option.getCourtId() );
			}
		}
		return set;		
	}
	
	public StringSet getExceptionCourtIds()
	{
		StringSet set = new StringSet();
		
		Iterator optionIter = getPolicySupervisionOptions().iterator();
		while ( optionIter.hasNext() )
		{
			CourtSupervisionOption option = (CourtSupervisionOption)optionIter.next();
			if ( option.getIsExceptionCourt() )
			{
				set.add( option.getCourtId() );
			}
		}
		return set;		
	}
	
	public StringSet getAllCourtIds()
	{
		StringSet set = new StringSet();
		
		Iterator optionIter = getPolicySupervisionOptions().iterator();
		while ( optionIter.hasNext() )
		{
			CourtSupervisionOption option = (CourtSupervisionOption)optionIter.next();
			set.add( option.getCourtId() );
		}
		return set;		
	}
	
}
