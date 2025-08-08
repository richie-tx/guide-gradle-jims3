package pd.common.refvariable;


/**
 * @author bschwartz
 *
 */
public abstract class AbstractResolver implements NameResolver
{
	private String name;
	private String contextName;
	

	public AbstractResolver( String aName, String aContextName )
	{
		name = aName;
		contextName = aContextName;
	}
	 	

	/* (non-Javadoc)
	 * @see pd.common.refvariable.NameResolver#getName()
	 */
	public String getName()
	{
		return name;
	}

	/* (non-Javadoc)
	 * @see pd.common.refvariable.NameResolver#getContextName()
	 */
	public String getContextName()
	{
		return contextName;
	}

}
