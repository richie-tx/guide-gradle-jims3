package messaging.refvariable.reply;

import java.util.HashMap;
import java.util.Map;

import mojo.km.messaging.ResponseEvent;


/**
 * @author bachwartz
 *
 */
public class ReferencesResponseEvent extends ResponseEvent
{
	private HashMap references = new HashMap();
	
	
	public void setReferences( Map otherReferences )
	{
		references.putAll( otherReferences );
	}

	public Map getReferences()
	{
		return references;
	}
	
	public Object getReference( String name )
	{
		return references.get( name );
	}

}
