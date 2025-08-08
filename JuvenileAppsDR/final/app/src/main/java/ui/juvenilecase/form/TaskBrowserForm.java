/*
 * Project: JIMS
 * Class:   ui.juvenilecase.form.TaskBrowserForm
 *
 * Date:    2005-06-29
 *
 * Author:  Dhanashree Apte	
 * Email:   DApte@jims.hctx.net
 */

package ui.juvenilecase.form;

import java.util.Collection;


/**
 *  
 * @author  dapte
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TaskBrowserForm extends org.apache.struts.action.ActionForm
{
	private Collection taskList;
	private String[] selectedTasks;

	/**
	 * @return
	 */
	public Collection getTaskList()
	{
		return taskList;
	}

	/**
	 * @param collection
	 */
	public void setTaskList(Collection collection)
	{
		taskList = collection;
	}

	/**
	 * @return
	 */
	public String[] getSelectedTasks()
	{
		return selectedTasks;
	}

	/**
	 * @param strings
	 */
	public void setSelectedTasks(String[] strings)
	{
		selectedTasks = strings;
	}

}