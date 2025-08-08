/*
 * Created on Oct 3rd, 2016
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.SaveJuvenileJISEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.util.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileMainForm;
import ui.juvenilecase.form.JuvenileProfileForm;


/**
 * @author ugopinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SubmitJuvenileJISInformationCreateAction extends JIMSBaseAction 
{
	@Override
	protected void addButtonMapping(Map keyMap){
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}
	
	   
	   /**
	    * Set all the selected values in the summary page. 
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return
	    */
	   public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException{
		   
		   JuvenileMainForm mainForm = (JuvenileMainForm)aForm;
		   //get juvenileNum from header form
		   String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest,true,false);
		   SaveJuvenileJISEvent saveEvent = (SaveJuvenileJISEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEJIS);
		   saveEvent.setJuvenileNum(juvenileNum);
		   saveEvent.setAgency(mainForm.getCurrentJISInfo().getAgency());
		   saveEvent.setEntryDate(DateUtil.getCurrentDate());
		   saveEvent.setOtherAgency(mainForm.getCurrentJISInfo().getOtherAgency());
			IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
			dispatch.postEvent( saveEvent ) ;
		 //mainForm.getCurrentJISInfo().setOtherAgency("");
		 mainForm.setAction("confirmJIS");
		  return aMapping.findForward(UIConstants.SUCCESS);
	   }


	
	   /**
	    * Cancel the jis information create.
	    */
		public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,HttpServletRequest aRequest, HttpServletResponse aResponse) {
			ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
			return forward;
		}
}
