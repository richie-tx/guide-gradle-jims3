//Source file: C:\\views\\CommonSupervision\\app\\src\\action\\DisplaySuggestedOrderSelectCourtAction.java
package ui.supervision.suggestedorder.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetOffenseCodeEvent;
import messaging.codetable.GetOffenseCodesEvent;
import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.ResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.PDConstants;
import naming.SupervisionConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.suggestedorder.helper.UISuggestedOrderHelper;
import ui.supervision.suggestedorder.form.SuggestedOrderForm;

/**
 * @author dgibler
 * 
 */
public class DisplaySuggestedOrderSelectCourtAction extends
		LookupDispatchAction {
	private static String FIELD_NAME = "offenseCodeId";

	private static String LIST_NAME = "offenseResultListIndex";

	private static String PERIOD = ".";

	/**
	 * @roseuid 433AF4BE01BD
	 */
	public DisplaySuggestedOrderSelectCourtAction() {

	}

	/**
	 * @see LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.addToList", "addToList");
		keyMap.put("button.submit", "submit");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.addSelected", "addSelected");
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward addSelected(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;

		Collection resultList = MessageUtil.processEmptyCollection(sugOrderForm
				.getOffenseResultList());
		Map resultListMap = UISuggestedOrderHelper
				.buildResponseEventMap(resultList);
		Collection selectedList = new ArrayList();

		int counter = 0;
		OffenseCodeResponseEvent cre = null;
		StringBuffer sb = null;
		String check = null;
		Iterator iter = resultList.iterator();
		while (iter.hasNext()) {

			cre = (OffenseCodeResponseEvent) iter.next();

			sb = new StringBuffer(LIST_NAME);
			sb.append(UIConstants.BRACKET_LEFT);
			sb.append(new Integer(counter).toString());
			sb.append(UIConstants.BRACKET_RIGHT);
			sb.append(PERIOD);
			sb.append(FIELD_NAME);
			check = aRequest.getParameter(sb.toString());
			if (check != null) {
				selectedList.add(cre);
				resultListMap.remove(cre.getTopic());
			}
			counter++;
		}

		sugOrderForm.setOffenseResultList(UISuggestedOrderHelper
				.buildNewCollection(resultListMap.values()));

		Collection offensesSelected = sugOrderForm.getOffenseSelectedList();

		if (offensesSelected != null) {
			Map map = UISuggestedOrderHelper
					.buildResponseEventMap(offensesSelected);
			selectedList = this.filterOutDuplicates(map, selectedList);
			selectedList = UISuggestedOrderHelper
					.buildNewCollection(selectedList);
			offensesSelected.addAll(selectedList);
			sugOrderForm.setOffenseSelectedList(offensesSelected);
		} else {
			sugOrderForm.setOffenseSelectedList(selectedList);
		}

		forward = aMapping.findForward(UIConstants.ADD_SELECTED_SUCCESS);
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward addToList(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;

		GetOffenseCodeEvent getOffenseCodeRequestEvent = (GetOffenseCodeEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETOFFENSECODE);

		getOffenseCodeRequestEvent.setOffenseCode(sugOrderForm.getOffenseId());

		Collection offenseCodeResponseEvents = UISuggestedOrderHelper
				.retrieveOffenses(getOffenseCodeRequestEvent);
		offenseCodeResponseEvents = MessageUtil
				.processEmptyCollection(offenseCodeResponseEvents);
		offenseCodeResponseEvents = UISuggestedOrderHelper
				.addTopicsToOffenses(offenseCodeResponseEvents);

		if (offenseCodeResponseEvents.size() > 0) {
			Collection offensesSelected = sugOrderForm.getOffenseSelectedList();
			if (offensesSelected != null) {
				Map map = UISuggestedOrderHelper
						.buildResponseEventMap(offensesSelected);
				offenseCodeResponseEvents = this.filterOutDuplicates(map,
						offenseCodeResponseEvents);
				offensesSelected.addAll(offenseCodeResponseEvents);
				sugOrderForm.setOffenseSelectedList(offensesSelected);
			} else {
				sugOrderForm.setOffenseSelectedList(offenseCodeResponseEvents);
			}
			forward = aMapping.findForward(UIConstants.ADD_TO_LIST_SUCCESS);
		} else {
			this.sendToErrorPage(aRequest, "error.noRecords");
			forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
		}

		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		String action = sugOrderForm.getAction();

		if (action.equals(UIConstants.CREATE)) {
			forward = aMapping.findForward(UIConstants.CANCEL_CREATE);
		} else {
			forward = aMapping.findForward(UIConstants.CANCEL);
		}
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		String action = sugOrderForm.getAction();

		// Build courts on first time in
		if (sugOrderForm.getAllCourts() == null
				|| sugOrderForm.getAllCourts().size() == 0) {
			sugOrderForm.setAllCourtsSelected(true);
			Collection courtBeans = UISuggestedOrderHelper.getCourtBeans();
			sugOrderForm.setAllCourts(courtBeans);
		}
		
		Collection filteredCourts = new ArrayList();
		boolean feloniesSelected = this.feloniesSelected(sugOrderForm
				.getOffenseSelectedList());
		boolean misdemeanorsSelected = this.misdemeanorsSelected(sugOrderForm
				.getOffenseSelectedList());
		if (feloniesSelected && misdemeanorsSelected) {
			Collection felMisdCourts = UISuggestedOrderHelper
					.filterCourtsByOffenses(SupervisionConstants.BOTH,
							sugOrderForm.getAllCourts());
			filteredCourts.addAll(felMisdCourts);

		} else {
			if (feloniesSelected) {
				Collection felonyCourts = UISuggestedOrderHelper
						.filterCourtsByOffenses(SupervisionConstants.FELONY,
								sugOrderForm.getAllCourts());
				filteredCourts.addAll(felonyCourts);
			} else if (misdemeanorsSelected) {
				Collection misdCourts = UISuggestedOrderHelper
						.filterCourtsByOffenses(
								SupervisionConstants.MISDEMEANOR, sugOrderForm
										.getAllCourts());
				filteredCourts.addAll(misdCourts);
			} else {
				filteredCourts = sugOrderForm.getAllCourts();
			}
		}

		if (action.equals(UIConstants.UPDATE)
				|| action.equals(UIConstants.COPY)) {
			
			if (sugOrderForm.getSelectedCourts() != null
					&& sugOrderForm.getSelectedCourts().size() > 0) {
				sugOrderForm.setAllCourtsSelected(true);
				// filter courts to be selected
				if (filteredCourts != null && filteredCourts.size() > 0) {
					sugOrderForm.setCourts(filteredCourts);
				} else // If the user hasn't selected any offense yet, then all
						// courts should be available
				{
					sugOrderForm.setCourts(sugOrderForm.getAllCourts());
				}
			} else {
				Collection preselectedCourts = this.preSelectCourts(
						filteredCourts, sugOrderForm.getSelectedCourts());
				sugOrderForm.setAllCourtsSelected(false);
				sugOrderForm.setCourts(preselectedCourts);

			}
		} else {
			sugOrderForm.setAllCourtsSelected(true);

			// filter courts to be selected
			if (filteredCourts != null && filteredCourts.size() > 0) {
				sugOrderForm.setCourts(filteredCourts);
			} else // If the user hasn't selected any offense yet, then all
					// courts should be available
			{
				sugOrderForm.setCourts(sugOrderForm.getAllCourts());
			}
		}

		if (action.equals(UIConstants.CREATE)
				|| action.equals(UIConstants.COPY)
				|| action.equals(UIConstants.UPDATE)) {
			forward = aMapping.findForward(UIConstants.SELECT_SUCCESS);
		} else {
			forward = aMapping.findForward(UIConstants.FAILURE);
		}
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;

		GetOffenseCodesEvent getOffenseCodesRequestEvent = (GetOffenseCodesEvent) EventFactory
				.getInstance(CodeTableControllerServiceNames.GETOFFENSECODES);

		getOffenseCodesRequestEvent.setLimitSearch(PDConstants.NO);
		getOffenseCodesRequestEvent.setOffenseCode(sugOrderForm
				.getSearchOffenseId());
		getOffenseCodesRequestEvent.setOffenseLevel(sugOrderForm
				.getLevelCodeId());
		getOffenseCodesRequestEvent.setPenalCode(sugOrderForm.getPenalCodeId());
		getOffenseCodesRequestEvent.setOffenseDegree(sugOrderForm
				.getDegreeCodeId());

		getOffenseCodesRequestEvent.setStateOffenseCode(sugOrderForm
				.getStateOffenseCodeId());
		getOffenseCodesRequestEvent.setOffenseLiteral(sugOrderForm
				.getOffenseLiteral());

		CompositeResponse cr = MessageUtil
				.postRequest(getOffenseCodesRequestEvent);
		if (MessageUtil.filterComposite(cr, CountInfoMessage.class) != null) {
			this.sendToErrorPage(aRequest, "error.max.limit.exceeded");
			forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
		} else {
			Collection offenseCodeResponseEvents = MessageUtil
					.compositeToCollection(cr, OffenseCodeResponseEvent.class);
			UISuggestedOrderHelper
					.addTopicsToOffenses(offenseCodeResponseEvents);
			Collection selectedOffenses = sugOrderForm.getOffenseSelectedList();
			selectedOffenses = MessageUtil
					.processEmptyCollection(selectedOffenses);
			UISuggestedOrderHelper.addTopicsToOffenses(selectedOffenses);

			if (offenseCodeResponseEvents != null
					&& offenseCodeResponseEvents.size() > 0) {
				sugOrderForm.setOffenseResultList(offenseCodeResponseEvents);
				forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
			} else {
				this.sendToErrorPage(aRequest, "error.noRecords");
				forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
			}
		}

		return forward;
	}

	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;

		sugOrderForm.setOffenseId("");
		sugOrderForm.setLevelCodeId("");
		sugOrderForm.setDegreeCodeId("");
		sugOrderForm.setSearchOffenseId("");
		sugOrderForm.setPenalCodeId("");
		sugOrderForm.setOffenseLiteral("");
		sugOrderForm.setStateOffenseCodeId("");

		forward = aMapping.findForward(UIConstants.REFRESH_SUCCESS);
		return forward;
	}

	/**
	 * @param offenses
	 * @param offenseLevel
	 * @return
	 */
	private boolean checkOffenseType(Collection offenses, String offenseLevel) {
		boolean foundOffense = false;

		if (offenses != null) {
			OffenseCodeResponseEvent ocre = null;
			Iterator iter = offenses.iterator();
			while (iter.hasNext()) {
				ocre = (OffenseCodeResponseEvent) iter.next();
				if (ocre.getLevel().equals(offenseLevel)) {
					foundOffense = true;
					break;
				}
			}
		}
		return foundOffense;
	}

	/**
	 * @param offenses
	 * @return
	 */
	private boolean feloniesSelected(Collection offenses) {
		return checkOffenseType(offenses, SupervisionConstants.FELONY);
	}

	/**
	 * @param map
	 * @param responseEvents
	 * @return
	 */
	private Collection filterOutDuplicates(Map map, Collection responseEvents) {
		Collection filteredCollection = new ArrayList();
		ResponseEvent re = null;
		Object obj = null;
		Iterator iter = responseEvents.iterator();
		while (iter.hasNext()) {
			re = (ResponseEvent) iter.next();
			obj = map.get(re.getTopic());
			if (obj == null) {
				filteredCollection.add(re);
			}
		}
		return filteredCollection;
	}

	/**
	 * @param collection
	 * @return
	 */
	private Collection preSelectCourts(Collection allCourts,
			Collection selectedCourts) {
		Map selectedCourtMap = UISuggestedOrderHelper
				.createCourtMap(selectedCourts);
		if (allCourts != null) {
			CourtBean courtBean = null;
			Collection courts = null;
			Iterator iter = allCourts.iterator();
			while (iter.hasNext()) {
				courtBean = (CourtBean) iter.next();
				courts = courtBean.getCourts();
				courts = MessageUtil.processEmptyCollection(courts);
				boolean allCourtsSelected = true;
				CourtResponseEvent court = null;
				CourtResponseEvent selectedCourt = null;
				Iterator iterator = courts.iterator();
				while (iterator.hasNext()) {
					court = (CourtResponseEvent) iterator.next();
					selectedCourt = (CourtResponseEvent) selectedCourtMap
							.get(court.getCourtId());
					if (selectedCourt != null) {
						court.setIsSelected(true);
					} else {
						allCourtsSelected = false;
					}
				}
				if (allCourtsSelected) {
					courtBean.setIsSelected(true);
				}
			}
		}
		return allCourts;
	}

	/**
	 * @param offenses
	 * @return
	 */
	private boolean misdemeanorsSelected(Collection offenses) {
		return checkOffenseType(offenses, SupervisionConstants.MISDEMEANOR);
	}

	/**
	 * @param aRequest
	 * @param msg
	 */
	private void sendToErrorPage(HttpServletRequest aRequest, String msg) {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}
}
