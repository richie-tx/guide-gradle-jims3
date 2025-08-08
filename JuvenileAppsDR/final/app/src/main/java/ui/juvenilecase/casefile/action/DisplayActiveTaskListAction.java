//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\casefile\\action\\DisplayActiveTaskListAction.java

package ui.juvenilecase.casefile.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UITaskBrowserHelper;
import ui.juvenilecase.form.TaskBrowserForm;


public class DisplayActiveTaskListAction extends Action
{
   
   /**
    * @roseuid 43E1128500A1
    */
   public DisplayActiveTaskListAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 43DE8F51023E
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		TaskBrowserForm taskForm = (TaskBrowserForm)aForm;
   		Collection activeTasks = UITaskBrowserHelper.fetchAllActiveTasks();
   		taskForm.setTaskList(activeTasks);
    	return aMapping.findForward("success");
   }
}
