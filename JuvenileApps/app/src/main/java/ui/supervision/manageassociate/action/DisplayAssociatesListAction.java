//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\manageassociate\\action\\DisplayAssociatesListAction.java

package ui.supervision.manageassociate.action;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.manageassociate.UIManageAssociateHelper;
import ui.supervision.manageassociate.form.AssociateForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class DisplayAssociatesListAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.associatesList", "associatesList");
	}
	
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		AssociateForm myForm=(AssociateForm)aForm;
		myForm.clear();
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward associatesList(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
		{
		  	AssociateForm associateForm = (AssociateForm)aForm;
		  	String spnString = aRequest.getParameter("superviseeId");
		  	 if(spnString == null){
		  		spnString = associateForm.getSpn();
		  	 }
			//		  	associateForm.setSpn(associateForm.getSelectedValue());		  	
		  	
		  	associateForm.setFromPath(UIConstants.FROM_CASELOAD);
		  	Collection associates = UIManageAssociateHelper.fetchAssociatesListSortedOnStatusAndLastNameAndFirstName(spnString);
		  	associateForm.setAssociatesList(associates);

		  	SuperviseeHeaderForm shForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);		  	
		  	while (spnString.length() < 8) {
		  		spnString = "0" + spnString;
			}
// need to reload compliance value in case value has changed.		
			shForm.setCompliant(UICommonSupervisionHelper.isSuperviseeCompliant(spnString));		  

		  	return aMapping.findForward(UIConstants.VIEW_SUCCESS);	  
		}
   
}
