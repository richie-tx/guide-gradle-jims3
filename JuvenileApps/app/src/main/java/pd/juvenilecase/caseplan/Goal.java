package pd.juvenilecase.caseplan;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Date;
import java.util.Iterator;

import pd.codetable.Code;

/**
 * @roseuid 4533B7DB009B
 */
public class Goal extends PersistentObject
{
	private String supervisionNumber;
	private String goalDescription;
	private String goalId;
	private Date entryDate;
	private Date closedDate;
	private Date goalStatusChangeDate; //added new 
	private Date endRecommendationDate;
	private String progressNotes;
	private String goalIntervention; //added Intervention for ER JIMS200075816 
	private String endRecommendations;
	/**
	 * Properties for domainType
	 */
	private Code domainType = null;
	/**
	 * Properties for timeFrame
	 */
	private Code timeFrame = null;
	/**
	 * Properties for status
	 */
	private Code status = null;
	/**
	 * Properties for rules
	 * @referencedType pd.juvenilecase.rules.JuvenileCaseSupervisionRule
	 * @detailerDoNotGenerate false
	 * @associationType simple
	 */
	private java.util.Collection rules = null;
	/**
	 * Properties for personResponsible
	 * @referencedType pd.juvenilecase.caseplan.PersonResponsible
	 */
	private java.util.Collection personResponsible = null;
	//public pd.juvenilecase.caseplan.Goal[] theGoal;
	private String casePlanId;
	private String domainTypeId;
	private String timeFrameId;
	private String explainOtherTxt;
	private String statusId;

	/**
	 * @roseuid 4533B7DB009B
	 */
	public Goal()
	{
	}

	/**
	* Finds all Goals by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator rules = home.findAll(attributeName, attributeValue, Goal.class);
		return rules;
	}

	/*
	 * 
	 */
	static public Goal find(String goalID)
	{
		return (Goal) new Home().find(goalID, Goal.class);
	}

	/**
	 * Set the reference value to class :: pd.juvenilecase.caseplan.CasePlan
	 * @methodInvocation markModified
	 */
	public void setCasePlanId(String casePlanId)
	{
		if (this.casePlanId == null || !this.casePlanId.equals(casePlanId))
		{
			markModified();
		}
		this.casePlanId = casePlanId;
	}

	/**
	 * Get the reference value to class :: pd.juvenilecase.caseplan.CasePlan
	 * @methodInvocation fetch	
	 */
	public String getCasePlanId()
	{
		fetch();
		return casePlanId;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @methodInvocation markModified	
	 */
	public void setDomainTypeId(String domainTypeId)
	{
		if (this.domainTypeId == null || !this.domainTypeId.equals(domainTypeId))
		{
			markModified();
		}
		domainType = null;
		this.domainTypeId = domainTypeId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch	
	 */
	public String getDomainTypeId()
	{
		fetch();
		return domainTypeId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initDomainType()
	{
		if (domainType == null)
		{
			domainType = (Code) new mojo.km.persistence.Reference(domainTypeId, Code.class)
					.getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation initDomainType	 
	 */
	public Code getDomainType()
	{
		fetch();
		initDomainType();
		return domainType;
	}

	/**
	 * set the type reference for class member domainType
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setDomainTypeId	
	 */
	public void setDomainType(Code domainType)
	{
		if (this.domainType == null || !this.domainType.equals(domainType))
		{
			markModified();
		}
		if (domainType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(domainType);
		}
		setDomainTypeId("" + domainType.getOID());
		this.domainType = (Code) new mojo.km.persistence.Reference(domainType).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @methodInvocation markModified	
	 */
	public void setTimeFrameId(String timeFrameId)
	{
		if (this.timeFrameId == null || !this.timeFrameId.equals(timeFrameId))
		{
			markModified();
		}
		timeFrame = null;
		this.timeFrameId = timeFrameId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch	
	 */
	public String getTimeFrameId()
	{
		fetch();
		return timeFrameId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initTimeFrame()
	{
		if (timeFrame == null)
		{
			timeFrame = (Code) new mojo.km.persistence.Reference(
					timeFrameId, Code.class, "GOAL_TIMEFRAME")
					.getObject();
			
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation initTimeFrame	
	 */
	public Code getTimeFrame()
	{
		fetch();
		initTimeFrame();
		return timeFrame;
	}

	/**
	 * set the type reference for class member timeFrame
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setTimeFrameId	
	 */
	public void setTimeFrame(Code timeFrame)
	{
		if (this.timeFrame == null || !this.timeFrame.equals(timeFrame))
		{
			markModified();
		}
		if (timeFrame.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(timeFrame);
		}
		setTimeFrameId("" + timeFrame.getOID());
		this.timeFrame = (Code) new mojo.km.persistence.Reference(timeFrame).getObject();
	}

	/**
	 * @return the explainOtherTxt
	 */
	public String getExplainOtherTxt() {
		fetch();
		return explainOtherTxt;
	}

	/**
	 * @param explainOtherTxt the aExplainOtherTxt to set
	 */
	public void setExplainOtherTxt(String aExplainOtherTxt) {
		if (this.explainOtherTxt == null || !this.explainOtherTxt.equals(aExplainOtherTxt))
		{
			markModified();
		}
		this.explainOtherTxt = aExplainOtherTxt;
	}
	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @methodInvocation markModified	
	 */
	public void setStatusId(String statusId)
	{
		if (this.statusId == null || !this.statusId.equals(statusId))
		{
			markModified();
		}
		status = null;
		this.statusId = statusId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch	
	 */
	public String getStatusId()
	{
		fetch();
		return statusId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initStatus()
	{
		if (status == null)
		{
			status = (Code) new mojo.km.persistence.Reference(
					statusId, Code.class, "GOAL_STATUS")
					.getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation initStatus	
	 */
	public Code getStatus()
	{
		fetch();
		initStatus();
		return status;
	}

	/**
	 * set the type reference for class member status
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setStatusId	
	 */
	public void setStatus(Code status)
	{
		if (this.status == null || !this.status.equals(status))
		{
			markModified();
		}
		if (status.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(status);
		}
		setStatusId("" + status.getOID());
		this.status = (Code) new mojo.km.persistence.Reference(status).getObject();
	}

	/**
	 * Initialize class relationship implementation for pd.juvenilecase.rules.RuleGroupConditionView
	 * @methodInvocation bind	
	 */
	private void initRules()
	{
		if (rules == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			rules = new mojo.km.persistence.ArrayList(
					GoalRulesJuvenileCaseSupervisionRule.class, "parentId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.juvenilecase.rules.GoalRulesJuvenileCaseSupervisionRule
	 * @methodInvocation initRules
	 * @methodInvocation iterator
	 * @methodInvocation add
	 * @methodInvocation fetch	
	 */
	public java.util.Collection getRules()
	{
		initRules();
		java.util.ArrayList retVal = new java.util.ArrayList();
		Iterator i = rules.iterator();
		while (i.hasNext())
		{
			GoalRulesJuvenileCaseSupervisionRule actual = (GoalRulesJuvenileCaseSupervisionRule) i
					.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}

	/**
	 * insert a pd.juvenilecase.rules.RuleGroupConditionView into class relationship collection.
	 * @methodInvocation initRules
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation setChild
	 * @methodInvocation setParent
	 * @methodInvocation add	
	 */
	public void insertRules(pd.juvenilecase.rules.JuvenileCaseSupervisionRule anObject)
	{
		initRules();
		GoalRulesJuvenileCaseSupervisionRule actual = new GoalRulesJuvenileCaseSupervisionRule();
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
		rules.add(actual);
	}

	/**
	 * Removes a pd.juvenilecase.rules.JuvenileCaseSupervisionRule from class relationship collection.
	 * @methodInvocation initRules
	 * @methodInvocation setChildId
	 * @methodInvocation setParentId
	 * @methodInvocation remove
	 * @methodInvocation initRules
	 * @methodInvocation remove
	 * @methodInvocation initRules
	 * @methodInvocation remove
	 * @methodInvocation initRules
	 * @methodInvocation remove
	 * @methodInvocation initRules
	 * @methodInvocation remove
	 */
	public void removeRules(pd.juvenilecase.rules.JuvenileCaseSupervisionRule anObject)
	{
		initRules();
		mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
		assocEvent.setChildId((String) anObject.getOID());
		assocEvent.setParentId((String) this.getOID());
		GoalRulesJuvenileCaseSupervisionRule actual = (GoalRulesJuvenileCaseSupervisionRule) new mojo.km.persistence.Reference(
				assocEvent, GoalRulesJuvenileCaseSupervisionRule.class).getObject();
		rules.remove(actual);
	}

	/**
	 * Clears all pd.juvenilecase.rules.JuvenileCaseSupervisionRule from class relationship collection.
	 * @methodInvocation initRules
	 * @methodInvocation clear
	 * @methodInvocation initRules
	 * @methodInvocation clear
	 * @methodInvocation initRules
	 * @methodInvocation clear
	 * @methodInvocation initRules
	 * @methodInvocation clear
	 * @methodInvocation initRules
	 * @methodInvocation clear
	 */
	public void clearRules()
	{
		initRules();
		rules.clear();
	}

	/**
	 * Initialize class relationship implementation for pd.contact.Contact
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 * @methodInvocation bind
	 */
	private void initPersonResponsible()
	{
		if (personResponsible == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			personResponsible = new mojo.km.persistence.ArrayList(PersonResponsible.class,
					"goalId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.contact.Contact
	 * @methodInvocation fetch
	 * @methodInvocation initPersonResponsible
	 * @methodInvocation fetch
	 * @methodInvocation initPersonResponsible
	 * @methodInvocation fetch
	 * @methodInvocation initPersonResponsible
	 * @methodInvocation initPersonResponsible
	 * @methodInvocation initPersonResponsible
	 */
	public java.util.Collection getPersonResponsible()
	{
		fetch();
		initPersonResponsible();
		return personResponsible;
	}

	/**
	 * Clears all pd.contact.Contact from class relationship collection.
	 * @methodInvocation initPersonResponsible
	 * @methodInvocation clear
	 * @methodInvocation initPersonResponsible
	 * @methodInvocation clear
	 * @methodInvocation initPersonResponsible
	 * @methodInvocation clear
	 * @methodInvocation initPersonResponsible
	 * @methodInvocation clear
	 * @methodInvocation initPersonResponsible
	 * @methodInvocation clear
	 */
	public void clearPersonResponsible()
	{
		initPersonResponsible();
		personResponsible.clear();
	}

	/**
	 * @return Returns the endRecommendationDate.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public Date getEndRecommendationDate()
	{
		fetch();
		return endRecommendationDate;
	}

	/**
	 * @param endRecommendationDate The endRecommendationDate to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setEndRecommendationDate(Date endRecommendationDate)
	{
		if (this.endRecommendationDate == null || !this.endRecommendationDate.equals(endRecommendationDate))
		{
			markModified();
		}
		this.endRecommendationDate = endRecommendationDate;
	}

	/**
	 * @return Returns the endRecommendations.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getEndRecommendations()
	{
		fetch();
		return endRecommendations;
	}

	/**
	 * @param endRecommendations The endRecommendations to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setEndRecommendations(String endRecommendations)
	{
		if (this.endRecommendations == null || !this.endRecommendations.equals(endRecommendations))
		{
			markModified();
		}
		this.endRecommendations = endRecommendations;
	}

	/**
	 * @return Returns the entryDate.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public Date getEntryDate()
	{
		fetch();
		return entryDate;
	}

	/**
	 * @param entryDate The entryDate to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setEntryDate(Date entryDate)
	{
		if (this.entryDate == null || !this.entryDate.equals(entryDate))
		{
			markModified();
		}
		this.entryDate = entryDate;
	}

	/**
	 * @return Returns the goalDescription.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getGoalDescription()
	{
		fetch();
		return goalDescription;
	}

	/**
	 * @param goalDescription The goalDescription to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setGoalDescription(String goalDescription)
	{
		if (this.goalDescription == null || !this.goalDescription.equals(goalDescription))
		{
			markModified();
		}
		this.goalDescription = goalDescription;
	}

	/**
	 * @return Returns the goalId.
	 */
	public String getGoalId()
	{
		return getOID().toString();
	}

	/**
	 * @param goalId The goalId to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setGoalId(String goalId)
	{
		if (this.goalId == null || !this.goalId.equals(goalId))
		{
			markModified();
		}
		this.setOID(goalId);
		this.goalId = goalId;
	}
	
	/**
	 * @return Returns the goalIntervention.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getGoalIntervention()
	{
		fetch();
		return goalIntervention;
	}

	/**
	 * @param goalIntervention The goalIntervention to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setGoalIntervention(String goalIntervention)
	{
		if (this.goalIntervention == null || !this.goalIntervention.equals(goalIntervention))
		{
			markModified();
		}
		this.goalIntervention = goalIntervention;
	}

	/**
	 * @return Returns the progressNotes.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getProgressNotes()
	{
		fetch();
		return progressNotes;
	}

	/**
	 * @param progressNotes The progressNotes to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setProgressNotes(String progressNotes)
	{
		if (this.progressNotes == null || !this.progressNotes.equals(progressNotes))
		{
			markModified();
		}
		this.progressNotes = progressNotes;
	}

	/**
	 * @return Returns the supervisionNumber.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	public String getSupervisionNumber()
	{
		fetch();
		return supervisionNumber;
	}

	/**
	 * @param supervisionNumber The supervisionNumber to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	public void setSupervisionNumber(String supervisionNumber)
	{
		if (this.supervisionNumber == null || !this.supervisionNumber.equals(supervisionNumber))
		{
			markModified();
		}
		this.supervisionNumber = supervisionNumber;
	}

	/**
	 * @return Returns the theGoal.
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 * @methodInvocation fetch
	 */
	/*
	public pd.juvenilecase.caseplan.Goal[] getTheGoal()
	{
		fetch();
		return theGoal;
	}*/

	/**
	 * @param theGoal The theGoal to set.
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 * @methodInvocation markModified
	 */
	/*
	public void setTheGoal(pd.juvenilecase.caseplan.Goal[] theGoal)
	{
		if (this.theGoal == null || !this.theGoal.equals(theGoal))
		{
			markModified();
		}
		this.theGoal = theGoal;
	}*/

	/**
	 * insert a pd.juvenilecase.caseplan.PersonResponsible into class relationship collection.
	 * @methodInvocation initPersonResponsible
	 * @methodInvocation add
	 * @methodInvocation initPersonResponsible
	 * @methodInvocation add
	 */
	public void insertPersonResponsible(PersonResponsible anObject)
	{
		initPersonResponsible();
		personResponsible.add(anObject);
	}

	
	/**
	 * Removes a pd.juvenilecase.caseplan.PersonResponsible from class relationship collection.
	 * @methodInvocation initPersonResponsible
	 * @methodInvocation remove
	 * @methodInvocation initPersonResponsible
	 * @methodInvocation remove
	 */
	public void removePersonResponsible(PersonResponsible anObject)
	{
		initPersonResponsible();
		personResponsible.remove(anObject);
	}

	/**
	 * @return Returns the closedDate.
	 */
	public Date getClosedDate()
	{
		fetch();
		return closedDate;
	}
	

	/**
	 * @return Returns the goalStatusChangeDate.
	 */
	public Date getGoalStatusChangeDate()
	{
		fetch();
		return goalStatusChangeDate;
	}
	/**
	 * @param closedDate The closedDate to set.
	 */
	public void setClosedDate(Date closedDate)
	{
		if (this.closedDate == null || !this.closedDate.equals(closedDate))
		{
			markModified();
		}
		this.closedDate = closedDate;
	}
	/**
	 * @param goalStatusChangeDate The goalStatusChangeDate to set.
	 */
	public void setGoalStatusChangeDate(Date goalStatusChangeDate)
	{
		if (this.goalStatusChangeDate == null || !this.goalStatusChangeDate.equals(goalStatusChangeDate))
		{
			markModified();
		}
		this.goalStatusChangeDate = goalStatusChangeDate;
	}
}

