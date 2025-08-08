package pd.juvenilecase.caseplan;

import pd.juvenilecase.rules.JuvenileCaseSupervisionRule;

public class GoalRulesJuvenileCaseSupervisionRule extends mojo.km.persistence.PersistentObject
{
	JuvenileCaseSupervisionRule child;
	Goal parent;
	private String childId;
	private String parentId;

	/**
	 * Set the reference value to class :: JuvenileCaseSupervisionRule
	 */
	public void setChildId(String childId)
	{
		if (this.childId == null || !this.childId.equals(childId))
		{
			markModified();
		}
		child = null;
		this.childId = childId;
	}

	/**
	 * Get the reference value to class :: JuvenileCaseSupervisionRule
	 */
	public String getChildId()
	{
		fetch();
		return childId;
	}

	/**
	 * Initialize class relationship to class JuvenileCaseSupervisionRule
	 */
	private void initChild()
	{
		if (child == null)
		{
			child = (JuvenileCaseSupervisionRule) new mojo.km.persistence.Reference(childId,
					JuvenileCaseSupervisionRule.class).getObject();
		}
	}

	/**
	 * Gets referenced type RuleGroupConditionView
	 */
	public JuvenileCaseSupervisionRule getChild()
	{
		fetch();
		initChild();
		return child;
	}

	/**
	 * set the type reference for class member child
	 */
	public void setChild(JuvenileCaseSupervisionRule child)
	{
		if (this.child == null || !this.child.equals(child))
		{
			markModified();
		}
		if (child.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(child);
		}
		setChildId("" + child.getOID());
		this.child = (JuvenileCaseSupervisionRule) new mojo.km.persistence.Reference(child)
				.getObject();
	}

	/**
	 * Set the reference value to class :: pd.juvenilecase.caseplan.Goal
	 */
	public void setParentId(String parentId)
	{
		if (this.parentId == null || !this.parentId.equals(parentId))
		{
			markModified();
		}
		parent = null;
		this.parentId = parentId;
	}

	/**
	 * Get the reference value to class :: pd.juvenilecase.caseplan.Goal
	 */
	public String getParentId()
	{
		fetch();
		return parentId;
	}

	/**
	 * Initialize class relationship to class pd.juvenilecase.caseplan.Goal
	 */
	private void initParent()
	{
		if (parent == null)
		{
			parent = (Goal) new mojo.km.persistence.Reference(parentId,
					Goal.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.juvenilecase.caseplan.Goal
	 */
	public Goal getParent()
	{
		fetch();
		initParent();
		return parent;
	}

	/**
	 * set the type reference for class member parent
	 */
	public void setParent(Goal parent)
	{
		if (this.parent == null || !this.parent.equals(parent))
		{
			markModified();
		}
		if (parent.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(parent);
		}
		setParentId("" + parent.getOID());
		this.parent = (Goal) new mojo.km.persistence.Reference(parent).getObject();
	}
}

