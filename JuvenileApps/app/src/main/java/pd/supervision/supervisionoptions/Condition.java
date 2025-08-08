package pd.supervision.supervisionoptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.supervisionoptions.GetSupervisionConditionsByIdsEvent;
import messaging.supervisionoptions.ValidateSupervisionConditionEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import messaging.supervisionorder.CreateSpecialConditionEvent;
import messaging.supervisionorder.CreateSpecialConditionsEvent;
import messaging.supervisionorder.GenerateSpecialConditionNameEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.codetable.supervision.SupervisionCode;
import pd.contact.agency.Agency;
import pd.supervision.Court;
import pd.supervision.Group;
import pd.supervision.supervisionorder.CourtSequenceGenerator;

/**
* @roseuid 42F8BE5102AF
*/
public class Condition extends PersistentObject
{
	/**
	* Properties for supervisionTypes
	* @detailerDoNotGenerate true
	* @referencedType pd.codetable.Code
	* @contextKey SUPERVISION_TYPE
	* @associationType simple
	*/
	// do not generate entity mapping again.
	// child's oid is not used in this association 
	private Collection supervisionTypes;
	
	/**
	* Properties for agency
	*/
	private Agency agency = null;
	private String agencyId;
	/**
	* Properties for agencyPolicies
	* @associationType simple
	* @referencedType pd.supervision.supervisionoptions.AgencyPolicy
	* @detailerDoNotGenerate false
	*/
	private Collection agencyPolicies = null;
	/**
	* Properties for conditionSupervisionOptions
	* @referencedType pd.supervision.supervisionoptions.ConditionSupervisionOption
	* @detailerDoNotGenerate false
	*/
	private Collection conditionSupervisionOptions = null;
	/**
	* Properties for courtPolicies
	* @associationType simple
	* @referencedType pd.supervision.supervisionoptions.CourtPolicy
	*/
	private Collection courtPolicies = null;
	private String description;
	private String unformattedDesc;
    private String spanishDescription;

	/**
	* Properties for documents
	* @associationType simple
	* @referencedType pd.codetable.supervision.SupervisionCode
	*/
	private Collection documents = null;
	private Date effectiveDate;
	private int eventCount;
	/**
	* Properties for group
	*/
	public Group group = null;
	private String groupId;
	private Date inactiveDate;
	private boolean isStandard;
	/**
	* Properties for jurisdiction
	* @contextKey JURISDICTION
	*/
	public Code jurisdiction = null;
	private String jurisdictionId;
	private String name;
	private String notes;
	/**
	* Properties for period
	* @contextKey SUPERVISION_PERIOD
	*/
	public Code period = null;
	private String periodId;
	private int periodValue;
	/**
	* Properties for severityLevel
	*/
	public SupervisionCode severityLevel = null;
	private String severityLevelId;
	/**
	* Properties for status
	* @contextKey CONDITION_STATUS
	*/
//	public Code status = null;
//	private String statusId;
	/**
	* Properties for supervisionEventTypes
	* @associationType simple
	* @referencedType pd.codetable.supervision.SupervisionCode
	* @detailerDoNotGenerate false
	*/
	private Collection supervisionEventTypes = null;
	
	private boolean isSpecialCondition;
	private boolean isArchived;

	//private boolean isDeleted;
	private String reasonToInactivate;
	
	private String group1Id;
	private String group2Id;
	private String group3Id;
	
	/**
	* @roseuid 42F8BE5102AF
	*/
	public Condition()
	{
	}
	/**
	* @roseuid 42F79A3902E0
	*/
	public void bind()
	{
		markModified();
	}
	
	/**
	* @param conditionId
	* @roseuid 42F79A3902DE
	*/
	static public Condition find(String conditionId)
	{
		IHome home = new Home();
		return (Condition) home.find(conditionId, Condition.class);
	}
	
	
	/**
	* @return Iterator Condition
	* @param event
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, Condition.class);
	}
	/**
	* Finds all Conditions by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		Iterator conditions = home.findAll(attributeName, attributeValue, Condition.class);
		// add it into the returned collection only when it is not deleted
		Collection collection = new ArrayList();
		while(conditions.hasNext()){
			Condition condition = (Condition)conditions.next();
			//if(!condition.getIsDeleted()){
				collection.add(condition);
			//}
		}
		return collection.iterator();
	}
	
	static public Condition findConditionByConditionId(String conditionId)
	{
		IHome home = new Home();
		Iterator conditions = home.findAll("conditionId", new Integer(conditionId), Condition.class);
		if(conditions.hasNext()){
		    return (Condition) conditions.next();
		}else{
			return null;
		}
	}
	
	static public Iterator findAllByIds(Collection conditionIds, boolean searchInactive){
		GetSupervisionConditionsByIdsEvent reqEvent = new GetSupervisionConditionsByIdsEvent();
		reqEvent.setConditionIds(conditionIds);
		reqEvent.setSearchInactive(searchInactive);
		Iterator conditions = Condition.findAll(reqEvent);
		return conditions;
	}
	private static final String SPECIAL_CONDITION = "SPECIAL CONDITION ";
	private static final String CRT = " CRT ";
	private static final String CND = "CND";
	
	public static CourtSequenceGenerator getCourtSequenceGenerator(CreateSpecialConditionsEvent mainEvent){
		GenerateSpecialConditionNameEvent event = new GenerateSpecialConditionNameEvent();
		event.setCourtId(mainEvent.getCourtId()); 
		event.setAgencyId(mainEvent.getAgencyId());
		event.setType(CND);
		CourtSequenceGenerator generator = CourtSequenceGenerator.find( event );
		return generator;
	}
	
	public static Condition createSpecialCondition(CreateSpecialConditionsEvent mainEvent, 
			CreateSpecialConditionEvent reqEvent, CourtSequenceGenerator generator){
		Condition condition = new Condition();

		// set Condition properties	
		String courtNum = mainEvent.getCourtId().substring(3);
		StringBuffer condName = new StringBuffer(SPECIAL_CONDITION);
		condName.append(generator.nextSequenceAsString(4));
		condName.append(CRT);
		condName.append(courtNum);
		condition.setName(condName.toString());
		condition.setAgencyId(mainEvent.getAgencyId());
		condition.setGroupId(reqEvent.getGroup1());
		condition.setIsStandard(false);
		condition.setEffectiveDate(new Date());
		condition.setIsSpecialCondition(true);
		condition.setNotes(reqEvent.getNotes());
		condition.setDescription(reqEvent.getDescription());
		condition.setUnformattedDesc(reqEvent.getDescription());
		
		// set jurisdiction
		String jurisdictionId = getJurisdictionId(mainEvent.getCdi(), mainEvent.getCourtId());
		condition.setJurisdictionId(jurisdictionId);
		
		// create ConditionSupervisionOption
		ConditionSupervisionOption condSupOption = new ConditionSupervisionOption();
		condSupOption.setCourtId(mainEvent.getCourtId());
		condition.insertConditionSupervisionOptions(condSupOption);
		return condition;
	}
	
	private static String getJurisdictionId(String cdi, String courtId){
		String jurisdictionId = null;
		
		// get courtNum from courtId
		Court court = Court.find(courtId);
		String courtNum = court.getCourtNumber();
		Code courtCode = Code.find("CDI_COURTCTG", cdi);
		String category = courtCode.getDescription();
		
		// TODO check this logic with Mary
		if((cdi.equals("002") || cdi.equals("003")) || 
			(cdi.equals("010") && !category.equals("OC")) || 
			(cdi.equals("020") && !category.equals("OC")))
		{
			jurisdictionId = PDCodeTableConstants.JURISDICTION_HC;
		}else 
		if((cdi.equals("020") || cdi.equals("010")) && 
			(courtNum.equals("INM") && !category.equals("INF")))
		{
			jurisdictionId = PDCodeTableConstants.JURISDICTION_OC;
		}
		if((cdi.equals("020") || cdi.equals("010")) && 
			(courtNum.equals("OTM") && !category.equals("OTF")))
		{
			jurisdictionId = PDCodeTableConstants.JURISDICTION_OS;
		}
		return jurisdictionId;
	}
	
	/**
	* Clears all pd.supervision.supervisionoptions.AgencyPolicy from class relationship collection.
	*/
	public void clearAgencyPolicies()
	{
		initAgencyPolicies();
		agencyPolicies.clear();
	}
	/**
	* Clears all pd.supervision.supervisionoptions.ConditionSupervisionOption from class relationship collection.
	*/
	public void clearConditionSupervisionOptions()
	{
		initConditionSupervisionOptions();
		conditionSupervisionOptions.clear();
	}
	/**
	* Clears all pd.supervision.supervisionoptions.CourtPolicy from class relationship collection.
	*/
	public void clearCourtPolicies()
	{
		initCourtPolicies();
		courtPolicies.clear();
	}
	/**
	* Clears all pd.codetable.supervision.SupervisionCode from class relationship collection.
	*/
	public void clearDocuments()
	{
		initDocuments();
		documents.clear();
	}
	/**
	* Clears all pd.codetable.supervision.SupervisionCode from class relationship collection.
	*/
	public void clearSupervisionEventTypes()
	{
		initSupervisionEventTypes();
		supervisionEventTypes.clear();
	}
	/**
	* Gets referenced type pd.contact.agency.Agency
	*/
	public Agency getAgency()
	{
		fetch();
		initAgency();
		return agency;
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
	* returns a collection of pd.supervision.supervisionoptions.AgencyPolicy
	*/
	public Collection getAgencyPolicies()
	{
		initAgencyPolicies();
		ArrayList retVal = new ArrayList();
		Iterator i = agencyPolicies.iterator();
		while (i.hasNext())
		{
			ConditionAgencyPoliciesAgencyPolicy actual =
				(ConditionAgencyPoliciesAgencyPolicy) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}
	/**
	* returns a collection of pd.supervision.supervisionoptions.ConditionSupervisionOption
	*/
	public Collection getConditionSupervisionOptions()
	{
		initConditionSupervisionOptions();
		return conditionSupervisionOptions;
	}
	/**
	* returns a collection of pd.supervision.supervisionoptions.CourtPolicy
	*/
	public Collection getCourtPolicies()
	{
		initCourtPolicies();
		ArrayList retVal = new ArrayList();
		Iterator i = courtPolicies.iterator();
		while (i.hasNext())
		{
			ConditionCourtPoliciesCourtPolicy actual =
				(ConditionCourtPoliciesCourtPolicy) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}
	/**
	* Access method for the description property.
	* @return the current value of the description property
	*/
	public String getDescription()
	{
		fetch();
		return description;
	}
    
    /**
    * Access method for the Spanish description property.
    * @return the current value of the spanish description property
    */
    public String getSpanishDescription()
    {
        fetch();
        return spanishDescription;
    }
    
    
	/**
	* returns a collection of pd.codetable.supervision.SupervisionCode
	*/
	public Collection getDocuments()
	{
		initDocuments();
		ArrayList retVal = new ArrayList();
		Iterator i = documents.iterator();
		while (i.hasNext())
		{
			ConditionDocumentsSupervisionCode actual =
				(ConditionDocumentsSupervisionCode) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}
	/**
	* Access method for the effectiveDate property.
	* @return the current value of the effectiveDate property
	*/
	public Date getEffectiveDate()
	{
		fetch();
		return effectiveDate;
	}
	/**
	* Access method for the eventCount property.
	* @return the current value of the eventCount property
	*/
	public int getEventCount()
	{
		fetch();
		return eventCount;
	}
	/**
	* Gets referenced type pd.supervision.Group
	*/
	public Group getGroup()
	{
		fetch();
		initGroup();
		return group;
	}
	/**
	* Get the reference value to class :: pd.supervision.Group
	*/
	public String getGroupId()
	{
		fetch();
		return groupId;
	}
	/**
	* Access method for the inactiveDate property.
	* @return the current value of the inactiveDate property
	*/
	public Date getInactiveDate()
	{
		fetch();
		return inactiveDate;
	}
	/**
	* Determines if the isStandard property is true.
	* @return <code>true<code> if the isStandard property is true
	*/
	public boolean getIsStandard()
	{
		fetch();
		return isStandard;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getJurisdiction()
	{
		fetch();
		initJurisdiction();
		return jurisdiction;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getJurisdictionId()
	{
		fetch();
		return jurisdictionId;
	}
	/**
	* Access method for the name property.
	* @return the current value of the name property
	*/
	public String getName()
	{
		fetch();
		return name;
	}
	/**
	* Access method for the notes property.
	* @return the current value of the notes property
	*/
	public String getNotes()
	{
		fetch();
		return notes;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getPeriod()
	{
		fetch();
		initPeriod();
		return period;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getPeriodId()
	{
		fetch();
		return periodId;
	}
	/**
	* Access method for the periodValue property.
	* @return the current value of the periodValue property
	*/
	public int getPeriodValue()
	{
		fetch();
		return periodValue;
	}
	/**
	 * Creates response event
	 * @return
	 */
	public ConditionResponseEvent getResponseEvent()
	{
		ConditionResponseEvent cre = new ConditionResponseEvent();
		cre.setAgencyId(this.getAgencyId());
		cre.setConditionId(this.getOID().toString());
		cre.setConditionName(this.getName());
		cre.setStatus(this.getStatus());
		cre.setEffectiveDate(this.getEffectiveDate());
		cre.setInactiveDate(this.getInactiveDate());
		cre.setStandard(this.getIsStandard());
		return cre;
	}
	/**
	* Gets referenced type pd.codetable.supervision.SupervisionCode
	*/
	public SupervisionCode getSeverityLevel()
	{
		fetch();
		initSeverityLevel();
		return severityLevel;
	}
	/**
	* Get the reference value to class :: pd.codetable.supervision.SupervisionCode
	*/
	public String getSeverityLevelId()
	{
		fetch();
		return severityLevelId;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public String getStatus()
	{
		String status = PDCodeTableConstants.STATUS_CREATED;
		
		Calendar current = Calendar.getInstance();
		current.set( Calendar.HOUR_OF_DAY, 0 );
		current.set( Calendar.MINUTE, 0 );
		current.set( Calendar.SECOND, 0 );
		
		if ( effectiveDate != null )
		{
			Calendar effective = Calendar.getInstance();
			effective.setTime( effectiveDate );
			effective.set( Calendar.HOUR_OF_DAY, 0 );
			effective.set( Calendar.MINUTE, 0 );
			effective.set( Calendar.SECOND, 0 );
			
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
			inactive.set( Calendar.SECOND, 0 );
			if ( ! inactive.after(current) )
			{
				// not after == before or on.
				status = PDCodeTableConstants.STATUS_INACTIVE;
			}
		}
		
		return status;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
//	public String getStatusId()
//	{
//		fetch();
//		return statusId;
//	}
	/**
	* returns a collection of pd.codetable.supervision.SupervisionCode
	*/
	public Collection getSupervisionEventTypes()
	{
		initSupervisionEventTypes();
		ArrayList retVal = new ArrayList();
		Iterator i = supervisionEventTypes.iterator();
		while (i.hasNext())
		{
			ConditionSupervisionEventTypesSupervisionCode actual =
				(ConditionSupervisionEventTypesSupervisionCode) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}
	/**
	* Initialize class relationship to class pd.contact.agency.Agency
	*/
	private void initAgency()
	{
		if (agency == null)
		{
			try
			{
				agency =
					(Agency) new mojo
						.km
						.persistence
						.Reference(agencyId, Agency.class)
						.getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}
	/**
	* Initialize class relationship implementation for pd.supervision.supervisionoptions.AgencyPolicy
	*/
	private void initAgencyPolicies()
	{
		if (agencyPolicies == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			try
			{
				agencyPolicies =
					new mojo.km.persistence.ArrayList(
						ConditionAgencyPoliciesAgencyPolicy.class,
						"parentId",
						"" + getOID());
			}
			catch (Throwable t)
			{
				agencyPolicies = new ArrayList();
			}
		}
	}
	/**
	* Initialize class relationship implementation for pd.supervision.supervisionoptions.ConditionSupervisionOption
	*/
	private void initConditionSupervisionOptions()
	{
		if (conditionSupervisionOptions == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			try
			{
				conditionSupervisionOptions =
					new mojo.km.persistence.ArrayList(
						ConditionSupervisionOption.class,
						"conditionId",
						"" + getOID());
			}
			catch (Throwable t)
			{
				conditionSupervisionOptions = new ArrayList();
			}
		}
	}
	/**
	* Initialize class relationship implementation for pd.supervision.supervisionoptions.CourtPolicy
	*/
	private void initCourtPolicies()
	{
		if (courtPolicies == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			try
			{
				courtPolicies =
					new mojo.km.persistence.ArrayList(
						ConditionCourtPoliciesCourtPolicy.class,
						"parentId",
						"" + getOID());
			}
			catch (Throwable t)
			{
				courtPolicies = new ArrayList();
			}
		}
	}
	/**
	* Initialize class relationship implementation for pd.codetable.supervision.SupervisionCode
	*/
	private void initDocuments()
	{
		if (documents == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			try
			{
				documents =
					new mojo.km.persistence.ArrayList(
						ConditionDocumentsSupervisionCode.class,
						"parentId",
						"" + getOID());
			}
			catch (Throwable t)
			{
				documents = new ArrayList();
			}
		}
	}
	/**
	* Initialize class relationship to class pd.supervision.Group
	*/
	private void initGroup()
	{
		if (group == null)
		{
			try
			{
				group =
					(Group) new mojo
						.km
						.persistence
						.Reference(groupId, Group.class)
						.getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initJurisdiction()
	{
		if (jurisdiction == null)
		{
			try
			{
				jurisdiction =
					(Code) new mojo
						.km
						.persistence
						.Reference(jurisdictionId, Code.class, "JURISDICTION")
						.getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initPeriod()
	{
		if (period == null)
		{
			try
			{
				period =
					(Code) new mojo
						.km
						.persistence
						.Reference(periodId, Code.class, "SUPERVISION_PERIOD")
						.getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.supervision.SupervisionCode
	*/
	private void initSeverityLevel()
	{
		if (severityLevel == null)
		{
			try
			{
				severityLevel =
					(SupervisionCode) new mojo
						.km
						.persistence
						.Reference(severityLevelId, SupervisionCode.class)
						.getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	* /
	private void initStatus()
	{
		if (status == null)
		{
			try
			{
				status =
					(pd.codetable.Code) new mojo
						.km
						.persistence
						.Reference(statusId, pd.codetable.Code.class, "CONDITION_STATUS")
						.getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}
	*/

	/**
	* Initialize class relationship implementation for pd.codetable.supervision.SupervisionCode
	*/
	private void initSupervisionEventTypes()
	{
		if (supervisionEventTypes == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			try
			{
				supervisionEventTypes =
					new mojo.km.persistence.ArrayList(
						ConditionSupervisionEventTypesSupervisionCode.class,
						"parentId",
						"" + getOID());
			}
			catch (Throwable t)
			{
				supervisionEventTypes = new ArrayList();
			}
		}
	}
	/**
	* insert a pd.supervision.supervisionoptions.AgencyPolicy into class relationship collection.
	*/
	public void insertAgencyPolicies(AgencyPolicy anObject)
	{
		initAgencyPolicies();
		ConditionAgencyPoliciesAgencyPolicy actual =
			new ConditionAgencyPoliciesAgencyPolicy();
		if (this.getOID() == null)
		{
			new Home().bind(this);
		}
		if (anObject.getOID() == null)
		{
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		agencyPolicies.add(actual);
	}
	/**
	* insert a pd.supervision.supervisionoptions.ConditionSupervisionOption into class relationship collection.
	*/
	public void insertConditionSupervisionOptions(
		ConditionSupervisionOption anObject)
	{
		initConditionSupervisionOptions();
		conditionSupervisionOptions.add(anObject);
	}
	/**
	* insert a pd.supervision.supervisionoptions.CourtPolicy into class relationship collection.
	*/
	public void insertCourtPolicies(CourtPolicy anObject)
	{
		initCourtPolicies();
		ConditionCourtPoliciesCourtPolicy actual =
			new ConditionCourtPoliciesCourtPolicy();
		if (this.getOID() == null)
		{
			new Home().bind(this);
		}
		if (anObject.getOID() == null)
		{
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		courtPolicies.add(actual);
	}
	/**
	* insert a pd.codetable.supervision.SupervisionCode into class relationship collection.
	*/
	public void insertDocuments(SupervisionCode anObject)
	{
		initDocuments();
		ConditionDocumentsSupervisionCode actual =
			new ConditionDocumentsSupervisionCode();
		if (this.getOID() == null)
		{
			new Home().bind(this);
		}
		if (anObject.getOID() == null)
		{
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		documents.add(actual);
	}
	/**
	* insert a pd.codetable.supervision.SupervisionCode into class relationship collection.
	*/
	public void insertSupervisionEventTypes(SupervisionCode anObject)
	{
		initSupervisionEventTypes();
		ConditionSupervisionEventTypesSupervisionCode actual =
			new ConditionSupervisionEventTypesSupervisionCode();
		if (this.getOID() == null)
		{
			new Home().bind(this);
		}
		if (anObject.getOID() == null)
		{
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		supervisionEventTypes.add(actual);
	}

	public boolean isDuplicate(String objId, String name, String agencyId)
	{
		boolean result = false;
		ValidateSupervisionConditionEvent reqEvent = new ValidateSupervisionConditionEvent();
		// OID is of type numeric, hence set some numeric value if it is null
		if (objId == null || objId.equals(""))
		{
			objId = "0";
		}
		reqEvent.setAgencyId(agencyId);
		reqEvent.setName(name);
		reqEvent.setConditionId(objId);
		Iterator it = Condition.findAll(reqEvent);
		if (it.hasNext())
		{
			result = true;
		}
		return result;
	}
	/**
	* Removes a pd.supervision.supervisionoptions.AgencyPolicy from class relationship collection.
	*/
	public void removeAgencyPolicies(AgencyPolicy anObject)
	{
		initAgencyPolicies();
		try
		{
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) anObject.getOID());
			assocEvent.setParentId((String) this.getOID());
			ConditionAgencyPoliciesAgencyPolicy actual =
				(ConditionAgencyPoliciesAgencyPolicy) new mojo
					.km
					.persistence
					.Reference(assocEvent, ConditionAgencyPoliciesAgencyPolicy.class)
					.getObject();
			agencyPolicies.remove(actual);
		}
		catch (Throwable t)
		{
		}
	}
	/**
	* Removes a pd.supervision.supervisionoptions.ConditionSupervisionOption from class relationship collection.
	*/
	public void removeConditionSupervisionOptions(
		ConditionSupervisionOption anObject)
	{
		initConditionSupervisionOptions();
		conditionSupervisionOptions.remove(anObject);
	}
	/**
	* Removes a pd.supervision.supervisionoptions.CourtPolicy from class relationship collection.
	*/
	public void removeCourtPolicies(CourtPolicy anObject)
	{
		initCourtPolicies();
		try
		{
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) anObject.getOID());
			assocEvent.setParentId((String) this.getOID());
			ConditionCourtPoliciesCourtPolicy actual =
				(ConditionCourtPoliciesCourtPolicy) new mojo
					.km
					.persistence
					.Reference(assocEvent, ConditionCourtPoliciesCourtPolicy.class)
					.getObject();
			courtPolicies.remove(actual);
		}
		catch (Throwable t)
		{
		}
	}
	/**
	* Removes a pd.codetable.supervision.SupervisionCode from class relationship collection.
	*/
	public void removeDocuments(SupervisionCode anObject)
	{
		initDocuments();
		try
		{
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) anObject.getOID());
			assocEvent.setParentId((String) this.getOID());
			ConditionDocumentsSupervisionCode actual =
				(ConditionDocumentsSupervisionCode) new mojo
					.km
					.persistence
					.Reference(assocEvent, ConditionDocumentsSupervisionCode.class)
					.getObject();
			documents.remove(actual);
		}
		catch (Throwable t)
		{
		    System.out.println(t.getMessage());
		}
	}
	/**
	* Removes a pd.codetable.supervision.SupervisionCode from class relationship collection.
	*/
	public void removeSupervisionEventTypes(SupervisionCode anObject)
	{
		initSupervisionEventTypes();
		try
		{
			mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
			assocEvent.setChildId((String) anObject.getOID());
			assocEvent.setParentId((String) this.getOID());
			ConditionSupervisionEventTypesSupervisionCode actual =
				(ConditionSupervisionEventTypesSupervisionCode) new mojo
					.km
					.persistence
					.Reference(
						assocEvent,
						ConditionSupervisionEventTypesSupervisionCode.class)
					.getObject();
			supervisionEventTypes.remove(actual);
		}
		catch (Throwable t)
		{
		}
	}
	/**
	* set the type reference for class member agency
	*/
	public void setAgency(Agency agency)
	{
		if (this.agency == null || !this.agency.equals(agency))
		{
			markModified();
		}
		if (agency.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(agency);
		}
		setAgencyId("" + agency.getOID());
		this.agency = (Agency) new mojo.km.persistence.Reference(agency).getObject();
	}
	/**
	* Set the reference value to class :: pd.contact.agency.Agency
	*/
	public void setAgencyId(String agencyId)
	{
		if (this.agencyId == null || !this.agencyId.equals(agencyId))
		{
			markModified();
		}
		agency = null;
		this.agencyId = agencyId;
	}
	/**
	* Sets the value of the description property.
	* @param aDescription the new value of the description property
	*/
	public void setDescription(String aDescription)
	{
		if (this.description == null || !this.description.equals(aDescription))
		{
			markModified();
		}
		description = aDescription;
	}
	
	/**
	 * @return Returns the reasonToInactivate.
	 */
	public String getReasonToInactivate() {
		fetch();
		return reasonToInactivate;
	}
	/**
	 * @param reasonToInactivate The reasonToInactivate to set.
	 */
	public void setReasonToInactivate(String reasonToInactivate) {
		if (this.reasonToInactivate == null || !this.reasonToInactivate.equals(reasonToInactivate))
		{
			markModified();
		}
		this.reasonToInactivate = reasonToInactivate;
	}

	/**
	* Sets the value of the effectiveDate property.
	* @param aEffectiveDate the new value of the effectiveDate property
	*/
	public void setEffectiveDate(Date aEffectiveDate)
	{
		if (this.effectiveDate == null || !this.effectiveDate.equals(aEffectiveDate))
		{
			markModified();
		}
		effectiveDate = aEffectiveDate;
	}
    
    /**
     * Sets the value of the Spanish description property.
     * @param aDescription the new value of the spanish description property
     */
     public void setSpanishDescription(String aDescription)
     {
         if (this.spanishDescription == null || !this.spanishDescription.equals(aDescription))
         {
             markModified();
         }
         spanishDescription = aDescription;
     }
     
    
	/**
	* Sets the value of the eventCount property.
	* @param aEventCount the new value of the eventCount property
	*/
	public void setEventCount(int aEventCount)
	{
		if (this.eventCount != aEventCount)
		{
			markModified();
		}
		eventCount = aEventCount;
	}
	/**
	* set the type reference for class member group
	*/
	public void setGroup(Group group)
	{
		if (group.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(group);
		}
		setGroupId( group.getOID().toString() );
		this.group = (Group) new mojo.km.persistence.Reference(group).getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.Group
	*/
	public void setGroupId(String groupId)
	{
		if (this.groupId == null || !this.groupId.equals(groupId))
		{
			markModified();
		}
		
//		validateAssociationsForGroups( groupId );
		
		group = null;
		this.groupId = groupId;
	}
	/**
	* Sets the value of the inactiveDate property.
	* @param aInactiveDate the new value of the inactiveDate property
	*/
	public void setInactiveDate(Date aInactiveDate)
	{
		if (this.inactiveDate == null || !this.inactiveDate.equals(aInactiveDate))
		{
			markModified();
		}
		inactiveDate = aInactiveDate;
	}
	/**
	* Sets the value of the isStandard property.
	* @param aIsStandard the new value of the isStandard property
	*/
	public void setIsStandard(boolean aIsStandard)
	{
		if (this.isStandard != aIsStandard)
		{
			markModified();
		}
		isStandard = aIsStandard;
	}
	/**
	* set the type reference for class member jurisdiction
	*/
	public void setJurisdiction(Code jurisdiction)
	{
		if (this.jurisdiction == null || !this.jurisdiction.equals(jurisdiction))
		{
			markModified();
		}
		if (jurisdiction.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(jurisdiction);
		}
		setJurisdictionId("" + jurisdiction.getOID());
		this.jurisdiction = (Code) new mojo.km.persistence.Reference(jurisdiction).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setJurisdictionId(String jurisdictionId)
	{
		if (this.jurisdictionId == null || !this.jurisdictionId.equals(jurisdictionId))
		{
			markModified();
		}
		jurisdiction = null;
		this.jurisdictionId = jurisdictionId;
	}
	/**
	* Sets the value of the name property.
	* @param aName the new value of the name property
	*/
	public void setName(String aName)
	{
		if (this.name == null || !this.name.equals(aName))
		{
			markModified();
		}
		name = aName;
	}
	/**
	* Sets the value of the notes property.
	* @param aNotes the new value of the notes property
	*/
	public void setNotes(String aNotes)
	{
		if (this.notes == null || !this.notes.equals(aNotes))
		{
			markModified();
		}
		notes = aNotes;
	}
	/**
	* set the type reference for class member period
	*/
	public void setPeriod(Code period)
	{
		if (this.period == null || !this.period.equals(period))
		{
			markModified();
		}
		if (period.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(period);
		}
		setPeriodId("" + period.getOID());
		this.period = (Code) new mojo.km.persistence.Reference(period).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setPeriodId(String periodId)
	{
		if (this.periodId == null || !this.periodId.equals(periodId))
		{
			markModified();
		}
		period = null;
		this.periodId = periodId;
	}
	/**
	* Sets the value of the periodValue property.
	* @param aPeriodValue the new value of the periodValue property
	*/
	public void setPeriodValue(int aPeriodValue)
	{
		if (this.periodValue != aPeriodValue)
		{
			markModified();
		}
		periodValue = aPeriodValue;
	}
	/**
	* set the type reference for class member severityLevel
	*/
	public void setSeverityLevel(SupervisionCode severityLevel)
	{
		if (this.severityLevel == null || !this.severityLevel.equals(severityLevel))
		{
			markModified();
		}
		if (severityLevel.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(severityLevel);
		}
		setSeverityLevelId("" + severityLevel.getOID());
		severityLevel.setContext("SEVERITY_LEVEL");
		this.severityLevel =
			(SupervisionCode) new mojo.km.persistence.Reference(severityLevel).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.supervision.SupervisionCode
	*/
	public void setSeverityLevelId(String severityLevelId)
	{
		if (this.severityLevelId == null || !this.severityLevelId.equals(severityLevelId))
		{
			markModified();
		}
		severityLevel = null;
		this.severityLevelId = severityLevelId;
	}
	
	/**
	 * @return
	 */
	public boolean getIsSpecialCondition()
	{
		fetch();
		return isSpecialCondition;
	}

	/**
	 * @param b
	 */
	public void setIsSpecialCondition(boolean b)
	{
		if (this.isSpecialCondition != b)
		{
			markModified();
		}
		isSpecialCondition = b;
	}

	/**
	 * @return
	 */
	public boolean getIsArchived()
	{
		fetch();
		return isArchived;
	}

	/**
	 * @param b
	 */
	public void setIsArchived(boolean b)
	{
		if (this.isArchived != b)
		{
			markModified();
		}
		isArchived = b;
	}

	/**
	 * @return Returns the isDeleted.
	 */
//	public boolean getIsDeleted() {
//		fetch();
//		return isDeleted;
//	}
	
	/**
	 * @param isDeleted The isDeleted to set.
	 */
//	public void setIsDeleted(boolean b) {
//		if (this.isDeleted != b)
//		{
//			markModified();
//		}
//		this.isDeleted = b;
//	}
	
	
	/**
	 * Call this when courts (ConditionSupervisionOption) have been removed so that the associations 
	 * to AgencyPolicy and CourtPolicy can be validated and removed if necessary. This is done here 
	 * instead in the collection remove method because all of the changes must be made before the 
	 * associations can be validated.     
	 */
	public void validateAssociationsForCourts()
	{
		StringSet condCourtIds = getAllCourtIds();
		
		Iterator iter = disassociatedDepartmentPoliciesForCourts(condCourtIds).iterator();
		while ( iter.hasNext() )
		{
			AgencyPolicy policy = (AgencyPolicy)iter.next();
			removeAgencyPolicies(policy); 
		}
		
		iter = disassociatedCourtPoliciesForCourts(condCourtIds).iterator();
		while ( iter.hasNext() )
		{
			CourtPolicy policy = (CourtPolicy)iter.next();
			removeCourtPolicies(policy); 
		}
	}
	
	public Collection disassociatedCourtPoliciesForCourts( Collection courtIds )
	{
		StringSet condCourtIds = new StringSet( courtIds );
		ArrayList disassociatedCourtPolicies = new ArrayList();
		
		Iterator iter = getCourtPolicies().iterator();
		while ( iter.hasNext() )
		{
			CourtPolicy policy = (CourtPolicy)iter.next();
			StringSet policyCourtIds = policy.getAllCourtIds();

			if ( condCourtIds.intersection(policyCourtIds).isEmpty() )
			{
				// No common courts, remove association.
				disassociatedCourtPolicies.add(policy); 
			}
		}
		return disassociatedCourtPolicies;
	}
	
	public Collection disassociatedDepartmentPoliciesForCourts( Collection courtIds )
	{
		StringSet condCourtIds = new StringSet( courtIds );
		ArrayList disassociatedDepartmentPolicies = new ArrayList();
		
		Iterator iter = getAgencyPolicies().iterator();
		while ( iter.hasNext() )
		{
			AgencyPolicy policy = (AgencyPolicy)iter.next();
			StringSet policyCourtIds = policy.getAllCourtIds();

			if ( condCourtIds.intersection(policyCourtIds).isEmpty() )
			{
				// No common courts, remove association.
				disassociatedDepartmentPolicies.add(policy); 
			}
		}
		
		return disassociatedDepartmentPolicies;
	}
	
	/**
	 * This is called internally every time a group changes. 
	 * @param newGroupId
	 */
	public void validateAssociationsForGroups(String newGroupId )
	{
		if ( groupId == null || newGroupId == null || groupId.equals(newGroupId) )
		{
			// 1. groupId == null means no associations. 
			// 2. newGroupId == null is invalid.
			// 3. groupId == newGroupId means no change.
			return;
		}
		
		Iterator iter = disassociatedDepartmentPoliciesForGroups(newGroupId).iterator();
		while ( iter.hasNext() )
		{
			AgencyPolicy policy = (AgencyPolicy)iter.next();
			removeAgencyPolicies(policy); 
		}
		
		iter = disassociatedCourtPoliciesForGroups(newGroupId).iterator();
		while ( iter.hasNext() )
		{
			CourtPolicy policy = (CourtPolicy)iter.next();
			removeCourtPolicies(policy); 
		}
	}
	
	public Collection disassociatedCourtPoliciesForGroups( String newGroupId )
	{
		ArrayList disassociatedCourtPolicies = new ArrayList();
		
		if ( groupId == null || newGroupId == null || groupId.equals(newGroupId) )
		{
			// 1. groupId == null means no associations. 
			// 2. newGroupId == null is invalid.
			// 3. groupId == newGroupId means no change.
			return disassociatedCourtPolicies;
		}
		
		Group oldGroup = Group.find( groupId );
		Group newGroup = Group.find( newGroupId );
		
		Group[] oldGroups = oldGroup.getGroupList();
		Group[] newGroups = newGroup.getGroupList();
		
		if ( ! oldGroups[0].equals(newGroups[0]) )
		{
			disassociatedCourtPolicies.addAll( getCourtPolicies() );
		}
		else if ( newGroups[1] != null && ! newGroups[1].equals(oldGroups[1]) )
		{
			// Group 2 has changed and must be validated against each association.
			Group[] assocGroups = null;
			
			Iterator iter = getCourtPolicies().iterator();
			while ( iter.hasNext() )
			{
				CourtPolicy policy = (CourtPolicy)iter.next();
				assocGroups = policy.getGroup().getGroupList();
				
				// Reqs: when the G2 value is null, then there is no change to the association
				if ( assocGroups[1] != null/* && ! assocGroups[1].equals(newGroups[1])*/ )
				{
					// Group 2 exists and does not match, remove it.
					disassociatedCourtPolicies.add(policy); 
				}
			}
		}
		return disassociatedCourtPolicies;
	}
	
	public Collection disassociatedDepartmentPoliciesForGroups(String newGroupId )
	{
		ArrayList disassociatedDepartmentPolicies = new ArrayList();
		
		if ( groupId == null || newGroupId == null || groupId.equals(newGroupId) )
		{
			// 1. groupId == null means no associations. 
			// 2. newGroupId == null is invalid.
			// 3. groupId == newGroupId means no change.
			return disassociatedDepartmentPolicies;
		}
		
		Group oldGroup = Group.find( groupId );
		Group newGroup = Group.find( newGroupId );
		
		Group[] oldGroups = oldGroup.getGroupList();
		Group[] newGroups = newGroup.getGroupList();
		
		if ( ! oldGroups[0].equals(newGroups[0]) )
		{
			disassociatedDepartmentPolicies.addAll( getAgencyPolicies() );
		}
		else if ( newGroups[1] != null && ! newGroups[1].equals(oldGroups[1]) )
		{
			// Group 2 has changed and must be validated against each association.
			Group[] assocGroups = null;
			
			Iterator iter = getAgencyPolicies().iterator();
			while ( iter.hasNext() )
			{
				AgencyPolicy policy = (AgencyPolicy)iter.next();
				assocGroups = policy.getGroup().getGroupList();
				
				// Reqs: when the G2 value is null, then there is no change to the association
				if ( assocGroups[1] != null/* && ! assocGroups[1].equals(newGroups[1])*/ )
				{
					// Group 2 exists and does not match, remove it.
					disassociatedDepartmentPolicies.add(policy); 
				}
			}
		}
		return disassociatedDepartmentPolicies;
	}
	
	
	
	
	public StringSet getStandardCourtIds()
	{
		StringSet set = new StringSet();
		
		Iterator optionIter = getConditionSupervisionOptions().iterator();
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
		
		Iterator optionIter = getConditionSupervisionOptions().iterator();
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
		
		Iterator optionIter = getConditionSupervisionOptions().iterator();
		while ( optionIter.hasNext() )
		{
			CourtSupervisionOption option = (CourtSupervisionOption)optionIter.next();
			set.add( option.getCourtId() );
		}
		return set;		
	}

	/**
	* Initialize class relationship implementation for pd.codetable.Code
	*/
	private void initSupervisionTypes() {
		if (supervisionTypes == null) {
			if (this.getOID() == null) {
				new mojo.km.persistence.Home().bind(this);
			}
			supervisionTypes = new mojo.km.persistence.ArrayList(ConditionSupervisionTypesCode.class, "parentId",  getOID().toString());
		}
	}
	/**
	* returns a collection of pd.codetable.Code
	*/
	public Collection getSupervisionTypes() {
		initSupervisionTypes();
		ArrayList retVal = new ArrayList();
		Iterator i = supervisionTypes.iterator();
		while (i.hasNext()) {
			ConditionSupervisionTypesCode actual = (ConditionSupervisionTypesCode) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}
	/**
	* insert a pd.codetable.Code into class relationship collection.
	*/
	public void insertSupervisionTypes(Code anObject) {
		initSupervisionTypes();
		ConditionSupervisionTypesCode actual = new ConditionSupervisionTypesCode();
		if (this.getOID() == null) {
			new Home().bind(this);
		}
		if (anObject.getOID() == null) {
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		supervisionTypes.add(actual);
	}
	
	/**
	* Removes a pd.codetable.Code from class relationship collection.
	* 
	*/
	// do not generate this method again
	public void removeSupervisionTypes(Code anObject) {
		initSupervisionTypes();
		mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
		/*
		 * child's oid is not used in this association
		 */	
		assocEvent.setChildId((String) anObject.getCode());
		assocEvent.setParentId((String) this.getOID());
		ConditionSupervisionTypesCode actual =
			(ConditionSupervisionTypesCode) new mojo
				.km
				.persistence
				.Reference(assocEvent, ConditionSupervisionTypesCode.class)
				.getObject();
		supervisionTypes.remove(actual);
	}
	
	/**
	* Clears all pd.codetable.Code from class relationship collection.
	*/
	public void clearSupervisionTypes() {
		initSupervisionTypes();
		supervisionTypes.clear();
	}

	public void updateCourtPolicyAssociations(Collection courtPolicyIds){
		StringSet changedPolicyIds = new StringSet( courtPolicyIds );
		
		StringSet currPolicyIds = new StringSet( this.getCourtPolicies(), new StringSet.Stringizer() {
			public String toString( Object obj )
			{
				return ((CourtPolicy)obj).getOID().toString();
			}
		});
		
		// new associations
		StringSet newAssociations = changedPolicyIds.complement(currPolicyIds);
		Iterator policyIds = newAssociations.iterator();
		while ( policyIds.hasNext() )
		{
			String policyId = (String)policyIds.next();
			CourtPolicy policy = CourtPolicy.find( policyId );
			this.insertCourtPolicies(policy);
			// TESTING to avoid deadlock issue
//			new Home().bind(policy);
		}
		
		// removed associations 
		StringSet removedAssociations = currPolicyIds.complement(changedPolicyIds);
		policyIds = removedAssociations.iterator();
		while ( policyIds.hasNext() )
		{
			String policyId = (String)policyIds.next();
			CourtPolicy policy = CourtPolicy.find( policyId );
			this.removeCourtPolicies(policy);
		}
			
	}
	/**
	 * @return Returns the unformattedDesc.
	 */
	public String getUnformattedDesc() {
		fetch();
		return unformattedDesc;
	}
	/**
	 * @param unformattedDesc The unformattedDesc to set.
	 */
	public void setUnformattedDesc(String aUnformattedDesc) {
		if (this.unformattedDesc == null || !this.unformattedDesc.equals(aUnformattedDesc))
		{
			markModified();
		}
		this.unformattedDesc = aUnformattedDesc;
	}
	public String getGroup1Id() {
		fetch();
		return group1Id;
	}
	public void setGroup1Id(String group1Id) {
		if (this.group1Id == null || !this.group1Id.equals(group1Id))
		{
			markModified();
		}
		this.group1Id = group1Id;
	}
	public String getGroup2Id() {
		fetch();
		return group2Id;
	}
	public void setGroup2Id(String group2Id) {
		if (this.group2Id == null || !this.group2Id.equals(group2Id))
		{
			markModified();
		}
		this.group2Id = group2Id;
	}
	public String getGroup3Id() {
		fetch();
		return group3Id;
	}
	public void setGroup3Id(String group3Id) {
		if (this.group3Id == null || !this.group3Id.equals(group3Id))
		{
			markModified();
		}
		this.group3Id = group3Id;
	}
}
