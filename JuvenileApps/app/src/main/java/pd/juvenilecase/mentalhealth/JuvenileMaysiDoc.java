/*
 * Created on Jul 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.mentalhealth;

import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JuvenileMaysiDoc extends PersistentObject {
	
	private Object maysiDoc;
	
	
	static public JuvenileMaysiDoc find(String aJuvenileNumber) {
		return (JuvenileMaysiDoc) new Home().find(aJuvenileNumber, JuvenileMaysiDoc.class);
	}
	
	/**
	* Access method for the juvenileNum property.
	* @roseuid 42A882800204
	* @return the current value of the juvenileNum property
	*/
	public String getJuvenileNum()
	{
		fetch();
		return (String) getOID();
	}
	/**
	* Sets the value of the juvenileNum property.
	* @roseuid 42A882800213
	* @param aJuvenileNum the new value of the juvenileNum property
	*/
	public void setJuvenileNum(String aJuvenileNum)
	{		
		setOID(aJuvenileNum);		
	}
	
	public Object getMaysiDoc() 
	{
		fetch();
		return maysiDoc;
	}
	
	/**
	* 
	*/
	public void setMaysiDoc( Object aReport ) 
	{
		if ( maysiDoc == null || ! maysiDoc.equals(aReport) ) 
		{
			maysiDoc = aReport;
			markModified();
		}
	}
}
