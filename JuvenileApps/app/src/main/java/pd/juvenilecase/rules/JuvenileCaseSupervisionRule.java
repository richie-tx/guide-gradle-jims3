package pd.juvenilecase.rules;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.supervision.supervisionoptions.Condition;
import pd.juvenilecase.JuvenileCasefile;
import java.util.Iterator;
import pd.codetable.Code;
import mojo.km.messaging.IEvent;

/**
 * @detailerDoNotGenerate false
 * @referencedType pd.codetable.Code
 * @contextKey COMP_STAT
 */
public class JuvenileCaseSupervisionRule extends PersistentObject
{
	/**
	 * @detailerDoNotGenerate false
	 * @referencedType pd.codetable.Code
	 * @contextKey COMP_STAT
	 */
	private Code completionStatus = null;
	private String completionStatusId;
	/**
	 * Properties for ruleValues
	 * @detailerDoNotGenerate false
	 * @referencedType pd.juvenilecase.JuvenileCaseSupervisionRuleValue
	 */
	private java.util.Collection ruleValues = null;
	private String monitorFrequencyId;
	/**
	 * Properties for casefile
	 * @detailerDoNotGenerate false
	 * @referencedType pd.juvenilecase.JuvenileCasefile
	 */
	public JuvenileCasefile casefile = null;
	/**
	 * @detailerDoNotGenerate false
	 * @referencedType pd.codetable.Code
	 * @contextKey MONITOR_FREQ
	 */
	public Code monitorFrequency = null;
	private String conditionId;
	private String casefileId;
	/**
	 * Properties for condition
	 * @detailerDoNotGenerate false
	 * @referencedType pd.supervision.supervisionoptions.Condition
	 */
	public Condition condition = null;
	private java.util.Date completionDate;
	/**
	 * Properties for ruleType
	 */
	private Code ruleType = null;
	private String ruleTypeId;
	private String additionalInformation;
	private String unformattedDesc;
	private String resolvedDesc;
	/**
	 * Properties for goals
	 * @detailerDoNotGenerate false
	 * @referencedType pd.juvenilecase.caseplan.Goal
	 * @associationType simple
	 */
	private java.util.Collection goals = null;

	/**
	 * @roseuid 43821D00008C
	 */
	public JuvenileCaseSupervisionRule()
	{
	}

	/**
	 * Finds rules by a certain event
	 * @param event
	 * @return Iterator of casefiles
	 * @methodInvocation findAll
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator rules = home.findAll(event, JuvenileCaseSupervisionRule.class);
		return rules;
	}

	/**
	 * Finds all rules by an attribute value
	 * @param attributeName
	 * @param attributeValue
	 * @return 
	 * @methodInvocation findAll
	 */
	static public Iterator findAll(String attributeName, String attributeValue)
	{
		IHome home = new Home();
		Iterator rules = home.findAll(attributeName, attributeValue, JuvenileCaseSupervisionRule.class);
		return rules;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @methodInvocation markModified
	 */
	public void setMonitorFrequencyId(String monitorFrequencyId)
	{
		if (this.monitorFrequencyId == null || !this.monitorFrequencyId.equals(monitorFrequencyId))
		{
			markModified();
		}
		monitorFrequency = null;
		this.monitorFrequencyId = monitorFrequencyId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch
	 */
	public String getMonitorFrequencyId()
	{
		fetch();
		return monitorFrequencyId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initMonitorFrequency()
	{
		if (monitorFrequency == null)
		{
			monitorFrequency = (Code) new mojo.km.persistence.Reference(monitorFrequencyId,
					Code.class, "MONITOR_FREQ").getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation fetch
	 * @methodInvocation initMonitorFrequency
	 */
	public Code getMonitorFrequency()
	{
		fetch();
		initMonitorFrequency();
		return monitorFrequency;
	}

	/**
	 * set the type reference for class member monitorFrequency
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setMonitorFrequencyId
	 * @methodInvocation setContext
	 */
	public void setMonitorFrequency(Code monitorFrequency)
	{
		if (this.monitorFrequency == null || !this.monitorFrequency.equals(monitorFrequency))
		{
			markModified();
		}
		if (monitorFrequency.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(monitorFrequency);
		}
		setMonitorFrequencyId("" + monitorFrequency.getOID());
		monitorFrequency.setContext("MONITOR_FREQ");
		this.monitorFrequency = (Code) new mojo.km.persistence.Reference(monitorFrequency).getObject();
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @methodInvocation markModified
	 */
	public void setCompletionStatusId(String aCompletionStatusId)
	{
		if (this.completionStatusId == null || !this.completionStatusId.equals(aCompletionStatusId))
		{
			markModified();
		}
		completionStatus = null;
		this.completionStatusId = aCompletionStatusId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 * @methodInvocation fetch
	 */
	public String getCompletionStatusId()
	{
		fetch();
		return completionStatusId;
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @methodInvocation initCompletionStatus
	 * @methodInvocation fetch
	 */
	public Code getCompletionStatus()
	{
		initCompletionStatus();
		fetch();
		return completionStatus;
	}

	/**
	 * set the type reference for class member completionStatus
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setCompletionStatusId
	 * @methodInvocation setContext
	 */
	public void setCompletionStatus(Code aCompletionStatus)
	{
		if (this.completionStatus == null || !this.completionStatus.equals(aCompletionStatus))
		{
			markModified();
		}
		if (aCompletionStatus.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aCompletionStatus);
		}
		setCompletionStatusId("" + aCompletionStatus.getOID());
		aCompletionStatus.setContext("COMP_STAT");
		this.completionStatus = (Code) new mojo.km.persistence.Reference(aCompletionStatus).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.supervisionoptions.Condition
	 * @methodInvocation markModified
	 */
	public void setConditionId(String conditionId)
	{
		if (this.conditionId == null || !this.conditionId.equals(conditionId))
		{
			markModified();
		}
		condition = null;
		this.conditionId = conditionId;
	}

	/**
	 * Get the reference value to class :: pd.supervision.supervisionoptions.Condition
	 * @methodInvocation fetch
	 */
	public String getConditionId()
	{
		fetch();
		return conditionId;
	}

	/**
	 * Initialize class relationship to class pd.supervision.supervisionoptions.Condition
	 */
	private void initCondition()
	{
		if (condition == null)
		{
			condition = (Condition) new mojo.km.persistence.Reference(conditionId,
					Condition.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.supervision.supervisionoptions.Condition
	 * @methodInvocation fetch
	 * @methodInvocation initCondition
	 */
	public Condition getCondition()
	{
		fetch();
		initCondition();
		return condition;
	}

	/**
	 * set the type reference for class member condition
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setConditionId
	 */
	public void setCondition(Condition condition)
	{
		if (this.condition == null || !this.condition.equals(condition))
		{
			markModified();
		}
		if (condition.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(condition);
		}
		setConditionId("" + condition.getOID());
		this.condition = (Condition) new mojo.km.persistence.Reference(condition)
				.getObject();
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 */
	public java.util.Date getCompletionDate()
	{
		fetch();
		return completionDate;
	}

	/**
	 * @param date
	 * @methodInvocation markModified
	 */
	public void setCompletionDate(java.util.Date date)
	{
		if (this.completionDate == null || !this.completionDate.equals(date))
		{
			markModified();
		}
		completionDate = date;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initCompletionStatus()
	{
		if (completionStatus == null)
		{
			completionStatus = (Code) new mojo.km.persistence.Reference(completionStatusId,
					Code.class, "COMPLETION_STATUS").getObject();
		}
	}

	/**
	 * Set the reference value to class :: pd.juvenilecase.JuvenileCasefile
	 * @methodInvocation markModified
	 */
	public void setCasefileId(String casefileId)
	{
		if (this.casefileId == null || !this.casefileId.equals(casefileId))
		{
			markModified();
		}
		casefile = null;
		this.casefileId = casefileId;
	}

	/**
	 * Get the reference value to class :: pd.juvenilecase.JuvenileCasefile
	 * @methodInvocation fetch
	 */
	public String getCasefileId()
	{
		fetch();
		return casefileId;
	}

	/**
	 * Initialize class relationship to class pd.juvenilecase.JuvenileCasefile
	 */
	private void initCasefile()
	{
		if (casefile == null)
		{
			casefile = (JuvenileCasefile) new mojo.km.persistence.Reference(casefileId,
					JuvenileCasefile.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.juvenilecase.JuvenileCasefile
	 * @methodInvocation fetch
	 * @methodInvocation initCasefile
	 */
	public JuvenileCasefile getCasefile()
	{
		fetch();
		initCasefile();
		return casefile;
	}

	/**
	 * set the type reference for class member casefile
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setCasefileId
	 */
	public void setCasefile(JuvenileCasefile casefile)
	{
		if (this.casefile == null || !this.casefile.equals(casefile))
		{
			markModified();
		}
		if (casefile.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(casefile);
		}
		setCasefileId("" + casefile.getOID());
		this.casefile = (JuvenileCasefile) new mojo.km.persistence.Reference(casefile).getObject();
	}

	/**
	 * @return JuvenileCaseSupervisionRule
	 * @param ruleId
	 */
	static public JuvenileCaseSupervisionRule find(String ruleId)
	{
		IHome home = new Home();
		JuvenileCaseSupervisionRule rule = (JuvenileCaseSupervisionRule) home.find(ruleId,
				JuvenileCaseSupervisionRule.class);
		return rule;
	}

	/**
	 * Initialize class relationship implementation for pd.juvenilecase.JuvenileCaseSupervisionRuleValue
	 * @methodInvocation bind
	 */
	private void initRuleValues()
	{
		if (ruleValues == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			ruleValues = new mojo.km.persistence.ArrayList(
					JuvenileCaseSupervisionRuleValue.class, "juvenileCaseSupervisionRuleId", ""
							+ getOID());
		}
	}

	/**
	 * returns a collection of pd.juvenilecase.JuvenileCaseSupervisionRuleValue
	 * @methodInvocation initRuleValues
	 */
	public java.util.Collection getRuleValues()
	{
		fetch();
		initRuleValues();
		return ruleValues;
	}

	/**
	 * insert a pd.juvenilecase.JuvenileCaseSupervisionRuleValue into class relationship collection.
	 * @methodInvocation initRuleValues
	 * @methodInvocation add
	 */
	public void insertRuleValues(JuvenileCaseSupervisionRuleValue anObject)
	{
		initRuleValues();
		ruleValues.add(anObject);
	}

	/**
	 * Removes a pd.juvenilecase.JuvenileCaseSupervisionRuleValue from class relationship collection.
	 * @methodInvocation initRuleValues
	 * @methodInvocation remove
	 */
	public void removeRuleValues(JuvenileCaseSupervisionRuleValue anObject)
	{
		initRuleValues();
		ruleValues.remove(anObject);
	}

	/**
	 * Clears all pd.juvenilecase.JuvenileCaseSupervisionRuleValue from class relationship collection.
	 * @methodInvocation initRuleValues
	 * @methodInvocation clear
	 */
	public void clearRuleValues()
	{
		initRuleValues();
		ruleValues.clear();
	}

	/**
	 * @methodInvocation markModified
	 */
	public void setRuleTypeId(String aRuleTypeId)
	{
		if (this.ruleTypeId == null || !this.ruleTypeId.equals(aRuleTypeId))
		{
			markModified();
		}
		ruleType = null;
		this.ruleTypeId = aRuleTypeId;
	}

	/**
	 * @methodInvocation fetch
	 */
	public String getRuleTypeId()
	{
		fetch();
		return ruleTypeId;
	}

	/**
	 * Initialize class relationship to class pd.juvenilecase.JuvenileCasefile
	 */
	private void initRuleType()
	{
		if (ruleType == null)
		{
			ruleType = (Code) new mojo.km.persistence.Reference(ruleTypeId, Code.class)
					.getObject();
		}
	}

	/**
	 * Gets referenced type pd.juvenilecase.JuvenileCasefile
	 * @methodInvocation initRuleType
	 */
	public Code getRuleType()
	{
		fetch();
		initRuleType();
		return ruleType;
	}

	/**
	 * set the type reference for class member casefile
	 * @methodInvocation markModified
	 * @methodInvocation bind
	 * @methodInvocation setRuleTypeId
	 */
	public void setRuleType(Code aRuleType)
	{
		if (this.ruleType == null || !this.ruleType.equals(aRuleType))
		{
			markModified();
		}
		if (aRuleType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aRuleType);
		}
		setRuleTypeId("" + aRuleType.getOID());
		this.ruleType = (Code) new mojo.km.persistence.Reference(aRuleType).getObject();
	}

	/**
	 * @return 
	 * @methodInvocation fetch
	 */
	public String getAdditionalInformation()
	{
		fetch();
		return additionalInformation;
	}

	/**
	 * @param string
	 * @methodInvocation markModified
	 */
	public void setAdditionalInformation(String string)
	{
		if (this.additionalInformation == null || !this.additionalInformation.equals(string))
		{
			markModified();
		}
		additionalInformation = string;
	}

	/**
	 * set the type reference for class member ruleType
	 *//*
	public void setRuleType(pd.codetable.Code ruleType)
	{
		if (this.ruleType == null || !this.ruleType.equals(ruleType))
		{
			markModified();
		}
		if (ruleType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(ruleType);
		}
		setRuleTypeId("" + ruleType.getOID());
		this.ruleType = (pd.codetable.Code) new mojo.km.persistence.Reference(ruleType).getObject();
	}*/

	/**
	 * Initialize class relationship implementation for pd.juvenilecase.caseplan.Goal
	 */
	private void initGoals()
	{
		if (goals == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			goals = new mojo.km.persistence.ArrayList(JuvenileCaseSupervisionRuleGoalsGoal.class,
					"parentId", "" + getOID());
		}
	}

	/**
	 * returns a collection of pd.juvenilecase.caseplan.Goal
	 */
	public java.util.Collection getGoals()
	{
		initGoals();
		java.util.ArrayList retVal = new java.util.ArrayList();
		Iterator i = goals.iterator();
		while (i.hasNext())
		{
			JuvenileCaseSupervisionRuleGoalsGoal actual = (JuvenileCaseSupervisionRuleGoalsGoal) i
					.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}

	/**
	 * insert a pd.juvenilecase.caseplan.Goal into class relationship collection.
	 */
	public void insertGoals(pd.juvenilecase.caseplan.Goal anObject)
	{
		initGoals();
		JuvenileCaseSupervisionRuleGoalsGoal actual = new JuvenileCaseSupervisionRuleGoalsGoal();
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
		goals.add(actual);
	}

	/**
	 * Removes a pd.juvenilecase.caseplan.Goal from class relationship collection.
	 */
	public void removeGoals(pd.juvenilecase.caseplan.Goal anObject)
	{
		initGoals();
		mojo.km.persistence.GetAssociationEvent assocEvent = new mojo.km.persistence.GetAssociationEvent();
		assocEvent.setChildId((String) anObject.getOID());
		assocEvent.setParentId((String) this.getOID());
		JuvenileCaseSupervisionRuleGoalsGoal actual = (JuvenileCaseSupervisionRuleGoalsGoal) new mojo.km.persistence.Reference(
				assocEvent, JuvenileCaseSupervisionRuleGoalsGoal.class).getObject();
		goals.remove(actual);
	}

	/**
	 * Clears all pd.juvenilecase.caseplan.Goal from class relationship collection.
	 */
	public void clearGoals()
	{
		initGoals();
		goals.clear();
	}
	/**
	 * @return Returns the resolvedDesc.
	 */
	public String getResolvedDesc() {
		fetch();
		return resolvedDesc;
	}
	/**
	 * @param resolvedDesc The resolvedDesc to set.
	 */
	public void setResolvedDesc(String resolvedDesc) {
		if (this.resolvedDesc == null || !this.resolvedDesc.equals(resolvedDesc))
		{
			markModified();
		}
		this.resolvedDesc = resolvedDesc;
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
	public void setUnformattedDesc(String unformattedDesc) {
		if (this.unformattedDesc == null || !this.unformattedDesc.equals(unformattedDesc))
		{
			markModified();
		}
		this.unformattedDesc = unformattedDesc;
	}
}

