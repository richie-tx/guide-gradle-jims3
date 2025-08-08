package ui.juvenilecase.education.action;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.education.form.EducationCharterDetailsForm;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.codetable.reply.CodeResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DisplayPostReleaseCreateSummaryAction extends JIMSBaseAction
{
	public DisplayPostReleaseCreateSummaryAction() 
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
	    eduForm.setAction(UIConstants.SUMMARY);
	    
	    eduForm.setPostReleaseEmployedCode(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.POSTRELEASEEMPLOYED, eduForm.getPostReleaseEmployedCodeId() ));
    	List newList = new ArrayList();
	    if (eduForm.getSelectedIds() != null){
	    	int len = eduForm.getSelectedIds().length;
	    	int listSize = eduForm.getSelectedFromList().size();

	    	for (int x =0; x < listSize; x++){
	    		CodeResponseEvent mre = (CodeResponseEvent) eduForm.getSelectedFromList().get(x);
	    		for (int y =0; y < len; y++){
	                if (mre.getCode().equalsIgnoreCase(eduForm.getSelectedIds()[y])){
	                     newList.add(mre);
	                     break;
	                }    
	           }
	    	}
	    }	
	    eduForm.setSelectedList(newList);
 	    return aMapping.findForward(UIConstants.SUCCESS);
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
