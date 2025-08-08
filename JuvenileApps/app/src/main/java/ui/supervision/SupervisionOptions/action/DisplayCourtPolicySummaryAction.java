//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplayCourtPolicySummaryAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.CourtPolicyForm;
import ui.supervision.SupervisionOptions.form.CourtVariableElementBean;

public class DisplayCourtPolicySummaryAction extends Action
{
   
   /**
    * @roseuid 42F7C4840186
    */
   public DisplayCourtPolicySummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F7997C030F
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		CourtPolicyForm form = (CourtPolicyForm)aForm;
   		
		// for exception courts 
		Collection courtVarElemBeans = form.getExceptionCourtVarElemBeans();
		if(courtVarElemBeans != null){
			Iterator iter = courtVarElemBeans.iterator();
			while(iter.hasNext()){
				CourtVariableElementBean cveBean = (CourtVariableElementBean)iter.next();
				//get all the VariableElementResponseEvents
				Collection ceres = cveBean.getVariableElements();
				if(ceres != null){
					Iterator it = ceres.iterator();
					while(it.hasNext()){
						VariableElementResponseEvent vere = (VariableElementResponseEvent)it.next();
						// set code description if variableElement is of type enumeration
						if(vere.isEnumeration()){
							vere.setValueByValueId();
						}
							
					}
				}
			}
		}
		
		form.setPageType(UIConstants.SUMMARY);
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
			
				
		
		
   }
}
