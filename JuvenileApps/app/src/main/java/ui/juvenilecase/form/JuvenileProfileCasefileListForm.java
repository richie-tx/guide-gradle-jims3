/*
 * Created on Jun 22, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.form;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileProfileCasefileListForm extends ActionForm
{

	private Collection juvenileProfileCasefileList;
	int searchResultSize;
	
	/**
	 * @return
	 */
	public Collection getJuvenileProfileCasefileList()
	{
		return juvenileProfileCasefileList;
	}

	/**
	 * @param collection
	 */
	public void setJuvenileProfileCasefileList(Collection collection)
	{
		juvenileProfileCasefileList = collection;
	}

	/**
	 * @return
	 */
	public int getSearchResultSize()
	{
		return searchResultSize;
	}

	/**
	 * @param i
	 */
	public void setSearchResultSize(int i)
	{
		searchResultSize = i;
	}

}
