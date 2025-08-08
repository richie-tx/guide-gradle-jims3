package messaging.juvenilecase.reply;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * A fully populated JuvenileCasefile value object
 * 
 * @author glyons
 */
public class JuvenileCasefileRulesResponseEvent extends ResponseEvent
{
	private String supervisionNum;
	private String juvenileNum;
	private Collection rules = new ArrayList();

	/**
	 * @return activationDate
	 */

	/**
	 * @return juvenileNum
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param juvenileNum
	 */
	public void setJuvenileNum(String aJuvenileNum)
	{
		juvenileNum = aJuvenileNum;
	}

	/**
	 * @return supervisionNum
	 */
	public String getSupervisionNum()
	{
		return supervisionNum;
	}

	/**
	 * @param supervisionNum
	 */
	public void setSupervisionNum(String aSupervisionNum)
	{
		supervisionNum = aSupervisionNum;
	}


	public Collection getRules()
	{
		return rules;
	}
}