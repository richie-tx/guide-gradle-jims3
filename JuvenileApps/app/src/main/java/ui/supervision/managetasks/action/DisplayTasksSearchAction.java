/*
 * Created on Mar 9, 2007
 */
package ui.supervision.managetasks.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.managetasks.form.TasksSearchForm;

/**
 * @author hrodriguez
 */
public class DisplayTasksSearchAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "link");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45DB5B2501CD
	 */
	//Called from NavigationMenu, TasksTab and other usecases
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		TasksSearchForm tsForm = (TasksSearchForm) aForm;
	
		tsForm.setAction(UIConstants.BASIC);
		return aMapping.findForward(UIConstants.BASIC_SEARCH_SUCCESS);
	}

}
