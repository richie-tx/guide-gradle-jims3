//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayJuvenileDrugsCreateAction.java

package ui.juvenilecase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileDrugForm;


public class DisplayJuvenileDrugsCreateAction extends Action
{
   
   /**
    * @roseuid 42AF40930280
    */
   public DisplayJuvenileDrugsCreateAction() 
   {
	       
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42AF3EE0017B
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   JuvenileDrugForm drugForm = (JuvenileDrugForm) aForm;
       drugForm.clear();
       drugForm.clearDrugCreateInfo();
       String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
       drugForm.setJuvenileNum(juvenileNum);
        return aMapping.findForward("success");
   }
}
