/*
 * Created on Nov 9, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.struts.action.ActionForm;

import ui.common.CodeHelper;
import ui.juvenilecase.Rule;

/**
 * @author ldeen
 *
  * Updated Nov 28, 2005
  * @uma gopinath
  */
public class SupervisionRulesForm extends ActionForm
{
	//Default Fields
	private String action = "";
	private String secondaryAction = "";
	private boolean update = false;
	private boolean delete = false;
	private String selectedValue = "";
	

	//Fields to identify casefile & juvenile
	private String juvenileNum = "";
	private String casefileID = "";

	//Fields for ruleList.jsp
	private boolean allowUpdates;

	//Collections & Drop Down Lists;
	private Collection assignedRulesList = new ArrayList();
	private Collection goalList = new ArrayList();
	private Collection completionStatusCodes = new ArrayList();

	private Rule selectedRule = null;
	private String completionDate = "";
	
	//US 22839
	private String[] selectedRulesToTransfer;

	/**
	 *
	 */
	public SupervisionRulesForm()
	{
	}

	public void clearAll()
	{
		selectedValue = "";
		assignedRulesList.clear();
		selectedRule = null;
		completionStatusCodes.clear();
		
		action = "";
		secondaryAction = "";
		update = false;
		delete = false;
		juvenileNum = "";
		casefileID = "";
		goalList.clear();
		completionDate = "";
	}

	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @return
	 */
	public String getCasefileID()
	{
		return casefileID;
	}
	/**
	 * @return
	 */
	public Collection getGoalList()
	{
		return goalList;
	}

	/**
	 * @return
	 */
	public boolean isDelete()
	{
		return delete;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return
	 */
	public String getSecondaryAction()
	{
		return secondaryAction;
	}

	/**
	 * @return
	 */
	public String getSelectedValue()
	{
		return selectedValue;
	}

	/**
	 * @return
	 */
	public Collection getAssignedRulesList()
	{
		if ( ! assignedRulesList.isEmpty() )
		{
			Collections.sort( (List)assignedRulesList, Collections.reverseOrder() );
		}
		return assignedRulesList;
	}

	/**
	 * @return
	 */
	public boolean isUpdate()
	{
		return update;
	}

	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		action = string;
	}

	/**
	 * @param string
	 */
	public void setCasefileID(String string)
	{
		casefileID = string;
	}

	/**
	 * @param b
	 */
	public void setDelete(boolean b)
	{
		delete = b;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/*
	* @param string
	*/
	public void setGoalList(Collection goalColl)
	{
		goalList = goalColl;
	}

	/**
	 * @param string
	 */
	public void setSecondaryAction(String string)
	{
		secondaryAction = string;
	}

	/**
	 * @param string
	 */
	public void setSelectedValue(String string)
	{
		selectedValue = string;
	}

	/**
	 * @param collection
	 */
	public void setAssignedRulesList(Collection collection)
	{
		assignedRulesList = collection;
	}

	/**
	 * @param b
	 */
	public void setUpdate(boolean b)
	{
		update = b;
	}

	/**
	 * @param object
	 */
	public void addAssignedRulesList( Rule rule )
	{
		assignedRulesList.add(rule);

	}

	/**
	 * @return
	 */
	public Rule getSelectedRule()
	{
		return selectedRule;
	}

	/**
	 * @param rule
	 */
	public void setSelectedRule(Rule rule)
	{
		selectedRule = rule;
	}

	

	/**
	 * @return
	 */
	public Collection getCompletionStatusCodes()
	{
		if ( completionStatusCodes.isEmpty() )
		{
			completionStatusCodes = CodeHelper.getCompletionStatusCodes();
			Collections.sort((ArrayList)completionStatusCodes);
		}
		
		return completionStatusCodes;
	}

	/**
	 * @param string
	 */
	public void setCompletionDate(String string)
	{
		completionDate = string;
	}

	/**
	 * @return
	 */
	public String getCompletionDate()
	{
		return completionDate;
	}

	/**
	 * @return the allowUpdates
	 */
	public boolean isAllowUpdates() {
		return allowUpdates;
	}

	/**
	 * @param allowUpdates the allowUpdates to set
	 */
	public void setAllowUpdates(boolean allowUpdates) {
		this.allowUpdates = allowUpdates;
	}

	/**
	 * @return the selectedRulesToTransfer
	 */
	public String[] getSelectedRulesToTransfer() {
		return selectedRulesToTransfer;
	}

	/**
	 * @param selectedRulesToTransfer the selectedRulesToTransfer to set
	 */
	public void setSelectedRulesToTransfer(String[] selectedRulesToTransfer) {
		this.selectedRulesToTransfer = selectedRulesToTransfer;
	}


}
