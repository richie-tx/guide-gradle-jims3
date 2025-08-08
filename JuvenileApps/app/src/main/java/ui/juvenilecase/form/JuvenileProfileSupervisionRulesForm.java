package ui.juvenilecase.form;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;

/**
 * @author ldeen
 *
 */

/**
  * Updated Nov 28, 2005
  * @uma gopinath
  */
public class JuvenileProfileSupervisionRulesForm extends ActionForm
{
	// Fields to identify juvenile
	private String juvenileNum = "";
	
	// Casefiles
	private Collection casefiles = new ArrayList();
	
	// Selected casefile
	private String selectedValue;

	

	public JuvenileProfileSupervisionRulesForm()
	{
	}


	public void clearAll()
	{
		juvenileNum = "";
		casefiles.clear();
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	public Collection getCasefiles()
	{
		return casefiles;
	}


	/**
	 * @return
	 */
	public String getSelectedValue()
	{
		return selectedValue;
	}

	/**
	 * @param string
	 */
	public void setSelectedValue(String string)
	{
		selectedValue = string;
	}

}
