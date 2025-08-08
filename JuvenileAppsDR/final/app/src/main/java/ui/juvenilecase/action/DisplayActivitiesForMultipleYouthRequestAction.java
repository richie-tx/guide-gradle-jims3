package ui.juvenilecase.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetJuvenileActivityTypeCodesEvent;
import messaging.codetable.criminal.reply.JuvenileActivityTypeCodeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.ProcessActivitiesForMultipleYouthForm;

public class DisplayActivitiesForMultipleYouthRequestAction extends Action
{
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
    {
	
	ProcessActivitiesForMultipleYouthForm form = (ProcessActivitiesForMultipleYouthForm) aForm;
	form.clear();
	GetJuvenileActivityTypeCodesEvent reqEvent = (GetJuvenileActivityTypeCodesEvent) EventFactory
	                							.getInstance(CodeTableControllerServiceNames.GETJUVENILEACTIVITYTYPECODES);
	reqEvent.setAction("add");
	reqEvent.setPermissionType("M");
	JuvenileActivityTypeCodeResponseEvent response = (JuvenileActivityTypeCodeResponseEvent) MessageUtil
	                							.postRequest(reqEvent, JuvenileActivityTypeCodeResponseEvent.class);
	if ( response.getReturnValues().size() > 0 ) {
	     for ( int i = 0 ; i < response.getReturnValues().size(); i++  ){
		 System.out.println("Code " + response.getReturnValues().get(i) );
	     }
	}
	 
	form.setActivityCodes(response.getReturnValues());	
	return aMapping.findForward(UIConstants.SUCCESS);
	
    }
}
