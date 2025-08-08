package ui.juvenilecase.education.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.education.form.EducationCharterDetailsForm;

public class DisplayVEPSummaryAction extends JIMSBaseAction {
	
	public DisplayVEPSummaryAction() 
	   {
	    
	   }
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put( "button.next", "next" );
		keyMap.put( "button.cancel", "cancel" );
		keyMap.put( "button.back", "back" );
	}
	
	/*
	 * 
	 */
	public ActionForward next( ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
	    EducationCharterDetailsForm eduForm = (EducationCharterDetailsForm) aForm ;
	    eduForm.setVepProgramCode(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.VEPPROGRAM, eduForm.getVepProgramCodeId() ));
	    eduForm.setAction(UIConstants.SUMMARY);
	    ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
	    return forward;
    }
	
	
	/*
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.CANCEL );
	}

	/*
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.BACK );
	}
}
