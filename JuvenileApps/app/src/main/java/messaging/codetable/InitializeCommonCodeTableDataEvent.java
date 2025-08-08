/*
 * Created on Oct 28, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.codetable;

import java.util.ArrayList;
import java.util.Iterator;

import mojo.km.messaging.RequestEvent;

/**
 * @author dapte
 *
 * Carries the names of all Code tables that are frequently used to the 
 * InitializeCommonCodeTableDataCommand for one-time initialization.
 * 
 */
public class InitializeCommonCodeTableDataEvent extends RequestEvent 
{
   
	private ArrayList codeTableNames;
	   
	/**
	* @roseuid 416428290039
	*/
	public InitializeCommonCodeTableDataEvent() 
	{
	   
	}
	   
	/**
	* @param codeTableName
	* @roseuid 416427E302F9
	*/
	public void setCodeTableNames(ArrayList codeTableNames) 
	{
		this.codeTableNames = codeTableNames;
	}
	   
	/**
	* @return String
	* @roseuid 416427E302FB
	*/
	public Iterator getCodeTableNames() 
	{
		return codeTableNames.iterator();
	}
	
}
