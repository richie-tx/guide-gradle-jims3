//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplaySupervisionConditionSummaryAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.reply.VariableElementResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.form.CourtVariableElementBean;
import ui.supervision.SupervisionOptions.form.SupervisionConditionForm;

public class DisplaySupervisionConditionSummaryAction extends Action
{
   
   /**
    * @roseuid 42F7C4950399
    */
   public DisplaySupervisionConditionSummaryAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A3A0282
    */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		
		SupervisionConditionForm form = (SupervisionConditionForm) aForm;
		
//		// get exception courts
//		Collection courtVarElemBeans = form.getExceptionCourtVarElemBeans();
//		Collection veres = form.getVariableElementResponseEvents();
//		if(courtVarElemBeans != null){
//			Iterator it = courtVarElemBeans.iterator();
//			while(it.hasNext()){
//				CourtVariableElementBean cveBean = (CourtVariableElementBean)it.next();
//				// add all the variable response events into the final collection
//				veres.addAll(cveBean.getVariableElements());
//			}
//		}
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

				
		if(form.getAction() != null && form.getAction().equalsIgnoreCase(UIConstants.DELETE))
		{
			form.setPageType(UIConstants.SUMMARY);
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
		}
		else
		{
			
			
			form.setPageType(UIConstants.SUMMARY);
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS,form.getAgencyId()));
		}
	}
	
	
}
