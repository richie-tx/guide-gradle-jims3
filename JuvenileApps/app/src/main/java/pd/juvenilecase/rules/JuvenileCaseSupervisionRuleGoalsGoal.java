package pd.juvenilecase.rules;

import pd.juvenilecase.caseplan.Goal;

public class JuvenileCaseSupervisionRuleGoalsGoal extends mojo.km.persistence.PersistentObject
{
	Goal child;
	JuvenileCaseSupervisionRule parent;
	private String childId;
	private String parentId;

	/**
	 * Set the reference value to class :: pd.juvenilecase.caseplan.Goal
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
	 * Get the reference value to class :: pd.juvenilecase.caseplan.Goal
	 */
	public String getChildId()
	{
		fetch();
		
		return childId;
	}

	/**
	 * Initialize class relationship to class pd.juvenilecase.caseplan.Goal
	 */
	private void initChild()
	{
		if (child == null)
		{
			child = (Goal) new mojo.km.persistence.Reference(childId,
					Goal.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.juvenilecase.caseplan.Goal
	 */
	public Goal getChild()
	{
		fetch();
		initChild();
		return child;
	}

	/**
	 * set the type reference for class member child
	 */
	public void setChild(Goal child)
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
		this.child = (Goal) new mojo.km.persistence.Reference(child).getObject();
	}

	/**
	 * Set the reference value to class :: pd.juvenilecase.rules.JuvenileCaseSupervisionRule
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
	 * Get the reference value to class :: pd.juvenilecase.rules.JuvenileCaseSupervisionRule
	 */
	public String getParentId()
	{
		fetch();
		return parentId;
	}

	/**
	 * Initialize class relationship to class pd.juvenilecase.rules.JuvenileCaseSupervisionRule
	 */
	private void initParent()
	{
		if (parent == null)
		{
			parent = (JuvenileCaseSupervisionRule) new mojo.km.persistence.Reference(parentId,
					JuvenileCaseSupervisionRule.class).getObject();
		}
	}

	/**
	 * Gets referenced type pd.juvenilecase.rules.JuvenileCaseSupervisionRule
	 */
	public JuvenileCaseSupervisionRule getParent()
	{
		fetch();
		initParent();
		return parent;
	}

	/**
	 * set the type reference for class member parent
	 */
	public void setParent(JuvenileCaseSupervisionRule parent)
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
		this.parent = (JuvenileCaseSupervisionRule) new mojo.km.persistence.Reference(parent)
				.getObject();
	}
}

