/*
 * Created on Jan 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.common.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetOffenseCodesEvent;
import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.form.OffenseSearchForm;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplaySearchForOffense extends LookupDispatchAction {

	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	public ActionForward submit(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			OffenseSearchForm myForm = (OffenseSearchForm) aForm;
			myForm.setOffenseResultList(null);
			CompositeResponse myResp=retrieveOffenseCodes(myForm);
			myForm.setOffenseResultList(MessageUtil.compositeToCollection(myResp,
					OffenseCodeResponseEvent.class));
			if(myForm.getOffenseResultList()==null || myForm.getOffenseResultList().size()<=0){
				sendToErrorPage(aRequest,"error.no.search.results.found");
			}
			return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
		}
	
	public CompositeResponse retrieveOffenseCodes(OffenseSearchForm myForm) {
		GetOffenseCodesEvent request = (GetOffenseCodesEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETOFFENSECODES);

		// set the criteria from the form
		request.setOffenseCode(myForm.getOffenseCode());
		request.setOffenseDegree(myForm.getDegreeId());
		request.setOffenseLevel(myForm.getLevelId());
		request.setOffenseLiteral(myForm.getOffenseLiteral());
		request.setStateOffenseCode(myForm.getStateOffenseCode());
		request.setPenalCode(myForm.getPenalCode());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(request);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);

		return compositeResponse;
		
	}

		
		public ActionForward refresh(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
			{
				OffenseSearchForm myForm = (OffenseSearchForm) aForm;

				myForm.clearSearchFields();
			
				return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
			}
		
		
		public ActionForward back(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			return aMapping.findForward(UIConstants.BACK);
		}

		public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			return aMapping.findForward(UIConstants.CANCEL);
		}

		private void sendToErrorPage(HttpServletRequest aRequest, String msg)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
			saveErrors(aRequest, errors);
		}
	
}// END CLASS
