//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\casefile\\action\\RemoveSelectedTaskAction.java

package ui.juvenilecase.casefile.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UITaskBrowserHelper;
import ui.juvenilecase.form.TaskBrowserForm;


public class RemoveSelectedTaskAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 43E112AB0218
    */
   public RemoveSelectedTaskAction() 
   {
    
   }
   
   protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.removeSelectedTasks", "removeTasks");
		return buttonMap;
	}   
   
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	   return aMapping.findForward(UIConstants.CANCEL);
    }

    public ActionForward removeTasks(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
		TaskBrowserForm taskForm = (TaskBrowserForm)aForm;
		String selectedTasks[] = taskForm.getSelectedTasks();
		if(selectedTasks != null) {
			UITaskBrowserHelper.removeTasks(selectedTasks);
		}
	 	return aMapping.findForward(UIConstants.SUCCESS);
    } 
   
}
