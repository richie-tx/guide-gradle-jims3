package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.SaveJuvenilePhysicalAttributesEvent;
import messaging.juvenile.SaveJuvenileTattooAndScarsEvent;
import messaging.juvenilewarrant.ScarMarkRequestEvent;
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

public class SaveJuvenileTattooAndScarsCreateSummaryAction extends LookupDispatchAction{
	   
	   /**
	    * @roseuid 42AF40A2005D
	    */
	   public SaveJuvenileTattooAndScarsCreateSummaryAction() 
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
			   		
			SaveJuvenileTattooAndScarsEvent request = new SaveJuvenileTattooAndScarsEvent();
		
			request.setJuvenileId(jpForm.getJuvenileNum());
			request.setScars(jpForm.getSelectedScarsArray());
			request.setOtherTattooComments(jpForm.getNewOtherTattooComments());
			request.setTattoos(jpForm.getSelectedTattoosArray());
			request.setEntryDate(DateUtil.stringToDate(jpForm.getEntryDate(), UIConstants.DATETIME24_FMT_1));
				
			String[] tattoos = jpForm.getSelectedTattoosArray();
			String[] scars = jpForm.getSelectedScarsArray();
			
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
			
			if (scars != null)
			{
				ScarMarkRequestEvent scarMarkEvent = null;
				for(int i = 0; i < scars.length; i++)
				{
					String codeId = scars[i];
					if (codeId != null)
					{
						scarMarkEvent = new ScarMarkRequestEvent();
						scarMarkEvent.setCode(codeId);
						request.addRequest(scarMarkEvent);
					}
				}
			}		
			
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(request);
			CompositeResponse response = (CompositeResponse) dispatch.getReply();
						
			MessageUtil.processReturnException(response);
				
			jpForm.setAction(UIConstants.CONFIRM);
			jpForm.setStatus("update");
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
