package ui.juvenilewarrant.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilewarrant.RecallJuvenileWarrantEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author HRodriguez - 02/11/2005 - Create action 
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SubmitWarrantRecallAction extends LookupDispatchAction
{

	/**
	 * @roseuid 41F7C38C0268
	 */
	public SubmitWarrantRecallAction()
	{
	}

	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.finish", UIConstants.FINISH);
		buttonMap.put("button.next", UIConstants.NEXT);		
		buttonMap.put("button.cancel", UIConstants.CANCEL);
		buttonMap.put("button.back", UIConstants.BACK);
		buttonMap.put("button.mainPage","mainPage");
		return buttonMap;
	}
	
	public ActionForward mainPage(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
			throws Exception
		{
			ActionForward forward = aMapping.findForward(UIConstants.MAIN_PAGE);
			return forward;
		}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 41F7B6A200E2
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{		
		JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		RecallJuvenileWarrantEvent requestEvent =
			(RecallJuvenileWarrantEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.RECALLJUVENILEWARRANT);

		requestEvent.setWarrantNum(jwForm.getWarrantNum());
		requestEvent.setRecallReason(jwForm.getRecallReasonId());
		
		CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);

		JuvenileWarrantResponseEvent jwResponseEvent =
			(JuvenileWarrantResponseEvent) MessageUtil.filterComposite(replyEvent, JuvenileWarrantResponseEvent.class);

		jwForm.setRecallDate(jwResponseEvent.getRecallDate());

		jwForm.setWarrantStatusId(jwResponseEvent.getWarrantStatusId());
		jwForm.setRecallReasonId(jwResponseEvent.getRecallReasonId());
		jwForm.setWarrantActivationStatusId(jwResponseEvent.getWarrantActivationStatusId());
		jwForm.setAction("");
		jwForm.setBackToWarrantUrl(aMapping.findForward(UIConstants.SUCCESS).getPath());

		return aMapping.findForward(UIConstants.SUCCESS);
	}
    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = null;
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
        jwForm.setAction("summary");
        forward = aMapping.findForward(UIConstants.NEXT);
        return forward;
    }	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = null;
		forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = null;
		forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}

}
