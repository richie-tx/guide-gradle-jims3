//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\SubmitJuvenilePhysicalCharacteristicsCreateAction.java

package ui.juvenilecase.action;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.SaveJuvenilePhysicalAttributesEvent;
import messaging.juvenilewarrant.TattooRequestEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.JuvenilePhysicalCharacteristicsForm;

public class SubmitJuvenilePhysicalCharacteristicsCreateAction extends LookupDispatchAction 
{
   
   /**
    * @roseuid 42AF40A2005D
    */
   public SubmitJuvenilePhysicalCharacteristicsCreateAction() 
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
	   buttonMap.put("button.finish", "finish");
	   buttonMap.put("button.cancel", "cancel");
	   buttonMap.put("button.juvenileProfileMasterDetails", "cancel");
	   return buttonMap;
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42AF3EE1012C
    */
   public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		JuvenilePhysicalCharacteristicsForm jpForm = (JuvenilePhysicalCharacteristicsForm) aForm;
		   		
		SaveJuvenilePhysicalAttributesEvent request = (SaveJuvenilePhysicalAttributesEvent)EventFactory.getInstance(
											JuvenileControllerServiceNames.SAVEJUVENILEPHYSICALATTRIBUTES);
	
		request.setBuild(jpForm.getBuildId());		
		request.setEyeColor(jpForm.getEyeColorId());		
		request.setHairColor(jpForm.getHairColorId());
		request.setHeightFeet(jpForm.getHeightFeet());
		request.setHeightInch(jpForm.getHeightInch());
		request.setJuvenileNum(jpForm.getJuvenileNum());
		request.setWeight(jpForm.getWeight());
//		request.setOtherTattooComments(jpForm.getOtherTattooComments());
		request.setEntryDate(DateUtil.stringToDate(jpForm.getEntryDate(), UIConstants.DATETIME24_FMT_1));
			
		String[] tattoos = jpForm.getSelectedTattoos();
		
		// Retrieve selected tattoos codes
		if (tattoos != null)
		{
			TattooRequestEvent tattoosEvent = null;
			for(int i = 0; i < tattoos.length; i++)
			{
				String codeId = tattoos[i];
				if (codeId != null)
				{
					tattoosEvent = new TattooRequestEvent();
					tattoosEvent.setCode(codeId);
					request.addRequest(tattoosEvent);
				}
			}
		}			
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(request);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
					
		MessageUtil.processReturnException(response);
			
		jpForm.setAction(UIConstants.CONFIRM);
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
