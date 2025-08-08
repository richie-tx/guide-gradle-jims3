/*
 * Created on Nov 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase;

import java.util.Collection;
import java.util.Date;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Condition
{
	String 	conditionId;
	Date 	effectiveDate;
	Date 	inactiveDate;
	String 	name;
	String 	literal;
	Collection varElements;

	/**
	 * @return
	 */
	public String getConditionId()
	{
		return conditionId;
	}

	/**
	 * @return
	 */
	public Date getEffectiveDate()
	{
		return effectiveDate;
	}

	/**
	 * @return
	 */
	public Date getInactiveDate()
	{
		return inactiveDate;
	}

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return
	 */
	public String getLiteral()
	{
		return literal;
	}

	/**
	 * @return
	 */
	public Collection getVarElements()
	{
		return varElements;
	}

	/**
	 * @param string
	 */
	public void setConditionId(String string)
	{
		conditionId = string;
	}

	/**
	 * @param date
	 */
	public void setEffectiveDate(Date date)
	{
		effectiveDate = date;
	}

	/**
	 * @param date
	 */
	public void setInactiveDate(Date date)
	{
		inactiveDate = date;
	}

	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
	}

	/**
	 * @param string
	 */
	public void setLiteral(String string)
	{
		literal = string;
	}

	/**
	 * @param collection
	 */
	public void setVarElements(Collection collection)
	{
		varElements = collection;
	}


}


