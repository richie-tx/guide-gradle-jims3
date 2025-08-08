/*
 * Created on Nov 15, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.codetable.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.codetable.form.CodetableRegistrationForm;
import ui.common.CodeHelper;

/**
 * @author Nagalla
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandleCodetableRegistrationEntityAction extends JIMSBaseAction {
	
   /**
    * @roseuid 
    */
   public HandleCodetableRegistrationEntityAction() {
    
   }
   
   /**
	 * @return Map
	 * @roseuid 
	 */
   protected void addButtonMapping(Map keyMap) {
   		keyMap.put("button.next", "next");	
	}	

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) {
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		List codetableTypes = CodeHelper.getCodetableTypeCodes();
		cForm.setCodetableTypeList(codetableTypes);	
		cForm.resetForCreate();
		return aMapping.findForward(UIConstants.BACK);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) {
		CodetableRegistrationForm cForm = (CodetableRegistrationForm) aForm;
		if(cForm.getCodetableType().equalsIgnoreCase("SL")) {
			cForm.setCodetableContextKey(cForm.getSelectedContextOrEntity());
			cForm.setCodetableEntityName("pd.codetable.Code");
		} else {	
			cForm.setCodetableEntityName(cForm.getSelectedContextOrEntity());
		}	
		cForm.setOpStatus("summary");							
		cForm.setShowAttributes(false);
		return aMapping.findForward(UIConstants.NEXT);
	}
	
}

