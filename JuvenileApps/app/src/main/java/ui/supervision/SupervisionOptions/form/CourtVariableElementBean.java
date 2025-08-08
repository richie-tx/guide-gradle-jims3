/*
 * Created on Sep 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.supervision.SupervisionOptions.form;

import java.util.ArrayList;
import java.util.Collection;

import messaging.supervisionoptions.reply.VariableElementResponseEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CourtVariableElementBean
{
	private String courtNumber;
	private Collection variableElements = new ArrayList();

	/**
	 * @return
	 */
	public String getCourtNumber()
	{
		return courtNumber;
	}

	/**
	 * @return
	 */
	public Collection getVariableElements()
	{
		return variableElements;
	}

	/**
	 * @param string
	 */
	public void setCourtNumber(String string)
	{
		courtNumber = string;
	}

	/**
	 * @param collection
	 */
	public void setVariableElements(Collection collection)
	{
		variableElements = collection;
	}

	/**
	 * @param collection
	 */
	public void insertVariableElement(VariableElementResponseEvent vre)
	{
		variableElements.add(vre);
	}
}
