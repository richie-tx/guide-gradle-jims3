//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\casefile\\action\\DisplayJuvenileCasefileJournalListAction.java

package ui.juvenilecase.casefile.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.casefile.form.JournalForm;



public class DisplayJuvenileCasefileJournalListAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 47E281630277
    */
   public DisplayJuvenileCasefileJournalListAction() 
   {
    
   }
   
   public ActionForward generate(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {	  
	   return aMapping.findForward(UIConstants.SUCCESS);
   }
   
    /* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.generateJournalReport", "generate");	
		keyMap.put("button.back", "back");		
		keyMap.put("button.cancel", "cancel");		
		
	}	

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 47E258D30320
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		JournalForm jf = (JournalForm)aForm ;
		jf.clearAll( ) ;
		return aMapping.findForward( UIConstants.BACK ) ;
	}
}

