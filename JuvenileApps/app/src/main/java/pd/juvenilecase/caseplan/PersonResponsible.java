package pd.juvenilecase.caseplan;

import mojo.km.persistence.PersistentObject;
/**
 * @roseuid 4533B7EF0330
 */
public class PersonResponsible extends PersistentObject
{
	private String name;
	private String goalId;
	private String personResponsibleType;

	/**
	 * @roseuid 4533B7EF0330
	 */
	public PersonResponsible()
	{
	}

	/**
	 * @return Returns the goalId.
	 */
	public String getGoalId()
	{
		fetch();
		return goalId;
	}

	/**
	 * @param goalId The goalId to set.
	 */
	public void setGoalId(String goalId)
	{
		if (this.goalId == null || !this.goalId.equals(goalId))
		{
			markModified();
		}
		this.goalId = goalId;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName()
	{
		fetch();
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		if (this.name == null || !this.name.equals(name))
		{
			markModified();
		}
		this.name = name;
	}

	/**
	 * @return Returns the personResponsibleType.
	 */
	public String getPersonResponsibleType()
	{
		fetch();
		return personResponsibleType;
	}

	/**
	 * @param personResponsibleType The personResponsibleType to set.
	 */
	public void setPersonResponsibleType(String personResponsibleType)
	{
		if (this.personResponsibleType == null || !this.personResponsibleType.equals(personResponsibleType))
		{
			markModified();
		}
		this.personResponsibleType = personResponsibleType;
	}
}

