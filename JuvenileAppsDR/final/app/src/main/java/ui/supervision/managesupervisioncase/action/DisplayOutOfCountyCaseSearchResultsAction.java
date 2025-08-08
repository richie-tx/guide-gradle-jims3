// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\managesupervisioncase\\action\\DisplayOutOfCountyCaseSearchResultsAction.java

package ui.supervision.managesupervisioncase.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.domintf.managesupervisioncase.IParty;
import messaging.managesupervisioncase.GetOutOfCountyCasesEvent;
import messaging.managesupervisioncase.reply.CaseListResponseEvent;
import messaging.managesupervisioncase.reply.CaseNotFoundEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.OutOfCountyCaseControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.SecurityUIHelper;
import ui.supervision.managesupervisioncase.form.OutOfCountyCaseSearchForm;

public class DisplayOutOfCountyCaseSearchResultsAction extends LookupDispatchAction {
	/**
	 * @roseuid 4443F97300CC
	 */
	public DisplayOutOfCountyCaseSearchResultsAction() {

	}

	/**
	 * @see LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.advancedSearch", "advancedSearch");
		keyMap.put("button.submit", "submit");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.refresh", "refresh");
		return keyMap;
	}

	public ActionForward advancedSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		forward = aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);
		return forward;
	}

	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		OutOfCountyCaseSearchForm oocSearchForm = (OutOfCountyCaseSearchForm) aForm;

		// post the request to PD
		GetOutOfCountyCasesEvent request = (GetOutOfCountyCasesEvent) EventFactory
				.getInstance(OutOfCountyCaseControllerServiceNames.GETOUTOFCOUNTYCASES);
		// set the search criteria
		request.setSpn(oocSearchForm.getSpn());
		// Defect 35698
		// request.setDefendantId(oocSearchForm.getPartyOid());
		request.setUserAgencyId(SecurityUIHelper.getUserAgencyId());
		request.setCaseNum(oocSearchForm.getCaseNum());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(request);

		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// check for errors
		MessageUtil.processReturnException(response);

		// clear any previous search results
		oocSearchForm.clearCaseList();

		// process results from lookup
		if (MessageUtil.filterComposite(response, CaseNotFoundEvent.class) != null) {
			sendToErrorPage(aRequest, "error.no.search.results.found");
			return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}

		Collection cases = MessageUtil.compositeToCollection(response, CaseListResponseEvent.class);
		if (!cases.isEmpty()) {
			CaseListResponseEvent listResponse = (CaseListResponseEvent) cases.iterator().next();

			oocSearchForm.setCaseList(listResponse.getCases());
			IParty party = listResponse.getParty();
			String partyOID = party.getPartyOid();
			if (partyOID == null) {
				sendToErrorPage(aRequest, "error.managesupervisioncase.partynotfound");
			} else {
				oocSearchForm.setPartyInfo(party);
				oocSearchForm.setCurrentNamePtr(party.getPartyNamePtr());
				oocSearchForm.setCurrentNameSeqNum(party.getPartyNameSeqNum());
			}

		} else {
			// no cases and no existing party
			// clear the search form
			oocSearchForm.clear(true, true);
			sendToErrorPage(aRequest, "error.no.search.results.found");
			return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}
		
		// cache the search form
		aRequest.getSession().setAttribute("outOfCountyCaseSearchForm", oocSearchForm);
		return aMapping.findForward(UIConstants.CASE_SEARCH_SUCCESS);
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.REFRESH_SUCCESS);
		return forward;
	}

	private void sendToErrorPage(HttpServletRequest aRequest, String msg) {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}

	
}
