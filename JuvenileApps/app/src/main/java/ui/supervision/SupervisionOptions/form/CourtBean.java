/*
 * Created on Sep 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.supervision.SupervisionOptions.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import messaging.supervisionoptions.reply.CourtResponseEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CourtBean
{
	private String category;
	private String categoryDesc;
	private Collection courts = new ArrayList();
	private boolean isSelected;
	private static String CHECKED = "checked";
	/**
	 * @return
	 */
	public String getCategory()
	{
		return category;
	}

	/**
	 * @return
	 */
	public Collection getCourts()
	{
		Collections.sort((ArrayList) courts);
		return courts;
	}

	/**
	 * @param string
	 */
	public void setCategory(String string)
	{
		category = string;
	}

	/**
	 * @param collection
	 */
	public void setCourts(Collection collection)
	{
		courts = collection;
	}

	/**
	 * @param collection
	 */
	public void insertCourt(CourtResponseEvent courtRespEvent)
	{
		courts.add(courtRespEvent);
	}
	/**
	 * @return
	 */
	public boolean getIsSelected()
	{
		return isSelected;
	}

	/**
	 * @param b
	 */
	public void setIsSelected(boolean b)
	{
		isSelected = b;
	}
	/**
	 * @return
	 */
	public String getIsChecked(){
		String checked = "";
		if (isSelected){
			checked = CHECKED;
		}
		return checked;
	}
	/**
	 * @return
	 */
	public String getCategoryDesc()
	{
		return categoryDesc;
	}

	/**
	 * @param string
	 */
	public void setCategoryDesc(String string)
	{
		categoryDesc = string;
	}

}
