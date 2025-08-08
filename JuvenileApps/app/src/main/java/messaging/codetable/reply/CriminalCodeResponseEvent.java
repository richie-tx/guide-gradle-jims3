/*
 * Created on Sep 17, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.codetable.reply;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CriminalCodeResponseEvent extends CodeResponseEvent
{
	private String category;

	/**
	 * @return
	 */
	public String getCategory()
	{
		return category;
	}

	/**
	 * @param string
	 */
	public void setCategory(String string)
	{
		category = string;
	}

}
