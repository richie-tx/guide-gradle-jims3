package ui.juvenilewarrant.action;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilewarrant.ProcessWarrantReturnServiceEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDJuvenileWarrantConstants;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author ryoung
 *
 */
public class SubmitWarrantProcessReturnOfServiceAction extends LookupDispatchAction
{

	/**
	 * @roseuid 41FFDF930203
	 */
	public SubmitWarrantProcessReturnOfServiceAction()
	{

	}
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new Hashtable();
		buttonMap.put("button.submit", UIConstants.SUBMIT);
		buttonMap.put("button.cancel", UIConstants.CANCEL);
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
	 * @roseuid 41FFC5E70188
	 */
	public ActionForward submit(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

		ProcessWarrantReturnServiceEvent requestEvent =
			(ProcessWarrantReturnServiceEvent) EventFactory.getInstance(
				JuvenileWarrantControllerServiceNames.PROCESSWARRANTRETURNSERVICE);

		requestEvent.setWarrantNum(jwForm.getWarrantNum());
		CompositeResponse response = MessageUtil.postRequest(requestEvent);

		JuvenileWarrantResponseEvent warrantResponse = (JuvenileWarrantResponseEvent) MessageUtil.filterComposite(response, JuvenileWarrantResponseEvent.class);

		jwForm.setServiceStatus(PDJuvenileWarrantConstants.WARRANT_SERVICE_SUCCESSFUL_DESC);
		jwForm.setServiceReturnGeneratedStatusId(warrantResponse.getServiceReturnGeneratedStatusId());
		jwForm.setServiceReturnGeneratedStatus(warrantResponse.getServiceReturnGeneratedStatus());
		jwForm.setServiceReturnSignatureStatusId(warrantResponse.getServiceReturnSignatureStatusId());
		jwForm.setServiceReturnSignatureStatus(warrantResponse.getServiceReturnSignatureStatus());

		forward = aMapping.findForward(UIConstants.SUCCESS);
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
}
