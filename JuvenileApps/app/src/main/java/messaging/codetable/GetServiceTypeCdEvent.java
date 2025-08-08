/*
 * Created on Jul 10, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.codetable;
import mojo.km.messaging.RequestEvent;
/**
 * @author C_NAggarwal
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetServiceTypeCdEvent extends RequestEvent
{

	private String codeTableName;

	/**
	 * @return
	 */
	public String getCodeTableName()
	{
		return codeTableName;
	}

	/**
	 * @param string
	 */
	public void setCodeTableName(String string)
	{
		codeTableName = string;
	}

}
