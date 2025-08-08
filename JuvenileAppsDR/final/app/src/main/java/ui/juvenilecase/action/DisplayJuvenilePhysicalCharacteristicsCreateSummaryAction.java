//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayJuvenilePhysicalCharacteristicsCreateSummaryAction.java

package ui.juvenilecase.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.Name;
import ui.juvenilecase.form.JuvenilePhysicalCharacteristicsForm;
import ui.security.SecurityUIHelper;

public class DisplayJuvenilePhysicalCharacteristicsCreateSummaryAction extends LookupDispatchAction 
{
   
   /**
    * @roseuid 42AF409E000F
    */
   public DisplayJuvenilePhysicalCharacteristicsCreateSummaryAction() 
   {
    
   }
   
   /**
	* @see LookupDispatchAction#getKeyMethodMap()
	* @return Map
	*/
   protected Map getKeyMethodMap()
   {
	   Map buttonMap = new HashMap();
	   buttonMap.put("button.back", "back");
	   buttonMap.put("button.next", "next");
	   buttonMap.put("button.cancel", "cancel");
	   buttonMap.put("button.back", "back");
	   return buttonMap;
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42AF3EE1007F
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		JuvenilePhysicalCharacteristicsForm jcForm = (JuvenilePhysicalCharacteristicsForm) aForm;
		String[] tattoos = aRequest.getParameterValues("selectedTattoos");
		String[] scars = aRequest.getParameterValues("selectedScars");
		
/*		
	   	String comments = jcForm.getOtherTattooComments();
		if (!comments.equals("") && comments != null) {
			IUserInfo user = SecurityUIHelper.getUser();
			Name userName = new Name(user.getFirstName(),"",user.getLastName());
			jcForm.setOtherTattooComments(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
		}
		
*/		// convert date into mm/dd/yyyy HH:mm format
		jcForm.processCodeDescriptions();
		jcForm.setEntryDate(DateUtil.dateToString(new Date(), "MM/dd/yyyy HH:mm"));		
		jcForm.setAction(UIConstants.SUMMARY);		
		return aMapping.findForward(UIConstants.SUCCESS);
   }
   
   	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}   	
	  	
   
}
