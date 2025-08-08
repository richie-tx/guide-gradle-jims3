/*
 * Created on Sep 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import mojo.km.messaging.Composite.CompositeRequest;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveFamilyConstellationEvent extends CompositeRequest
{
	private String  juvNum;
	private String  constellationNum;
	private boolean createNew; 
	

	/**
	 * @return
	 */
	public String getJuvNum()
	{
		return juvNum;
	}

	/**
	 * @param string
	 */
	public void setJuvNum(String string)
	{
		juvNum = string;
	}

	/**
	 * @return
	 */
	public String getConstellationNum()
	{
		return constellationNum;
	}

	/**
	 * @param string
	 */
	public void setConstellationNum(String string)
	{
		constellationNum = string;
	}

}
