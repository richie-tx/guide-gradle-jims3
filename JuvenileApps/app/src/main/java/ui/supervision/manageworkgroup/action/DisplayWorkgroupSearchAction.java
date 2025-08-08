//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\manageworkgroup\\action\\DisplayWorkgroupSearchAction.java

package ui.supervision.manageworkgroup.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.manageworkgroup.form.WorkgroupSearchForm;

/**
 * @author hrodriguez
 */
public class DisplayWorkgroupSearchAction  extends JIMSBaseAction
{
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
   public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	WorkgroupSearchForm wgSearchForm = (WorkgroupSearchForm) aForm;
	wgSearchForm.clearAll();
	
	return aMapping.findForward(UIConstants.SUCCESS);
   }
}
