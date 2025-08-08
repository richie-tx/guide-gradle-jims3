package messaging.refvariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mojo.km.messaging.RequestEvent;

/**
 * 
 * @author bschwartz
 */
public class ResolveReferencesEvent extends RequestEvent 
{
	private String contextName;
	private ArrayList referenceNames = new ArrayList();
	private HashMap properties = new HashMap();
	private String casefileId;
   

	/**
	 * 
	 */
	public ResolveReferencesEvent() 
	{
	}
	
	public void setContextName( String name )
	{
		contextName = name;
	}
	
	public String getContextName()
	{
		return contextName; 
	}
	
	public void addReferenceName( String name )
	{
		referenceNames.add( name );
	}


	public List getReferenceNames()
	{
		return referenceNames;   
	}

	public void setProperty( String name, Object value )
	{
		properties.put( name, value );
	}
	
	public Map getProperties()
	{
		return properties;
	}
	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId The casefileId to set.
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
}

