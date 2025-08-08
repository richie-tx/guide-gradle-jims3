/*
 * Created on Jun 16, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeRequest;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveJuvenileTraitsEvent extends CompositeRequest
{	
	private String juvenileNum;
	private String supervisionNum;
	private String dispositionNum;
	
	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @return
	 */
	public String getSupervisionNum()
	{
		return supervisionNum;
	}

	/**
	 * @param string
	 */
	public void setSupervisionNum(String string)
	{
		supervisionNum = string;
	}

	/**
	 * @return
	 */
	public String getDispositionNum()
	{
		return dispositionNum;
	}

	/**
	 * @param string
	 */
	public void setDispositionNum(String string)
	{
		dispositionNum = string;
	}

}
