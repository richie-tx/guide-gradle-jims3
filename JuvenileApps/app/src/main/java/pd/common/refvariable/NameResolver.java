package pd.common.refvariable;

import java.util.Map;

/**
 * @author bschwartz
 *
 */
public interface NameResolver
{
	String getName();
	
	String getContextName();
	
	Object resolve( Map context );
	
}
