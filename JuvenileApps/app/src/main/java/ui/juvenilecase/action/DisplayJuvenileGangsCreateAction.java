package ui.juvenilecase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileGangsForm;
/**
 * ER_GANG-JIMS200074578 CHANGES.
 * @author sthyagarajan
 * DisplayJuvenileGangsCreateAction
 */
public class DisplayJuvenileGangsCreateAction extends Action{
	
	  /**
	   * Default constructor
	   */
	   public DisplayJuvenileGangsCreateAction() 
	   {
		       
	   }
	   
	   /**
	    * Executed when the gang is added to the list. 
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		   JuvenileGangsForm gangForm = (JuvenileGangsForm) aForm;
		   gangForm.clear();
	       String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
	       gangForm.setJuvenileNum(juvenileNum);
	       return aMapping.findForward(UIConstants.SUCCESS);
	   }
}
