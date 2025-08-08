// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\managesupervisioncase\\action\\DisplayOutOfCountyCaseSummaryAction.java

package ui.supervision.managesupervisioncase.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import messaging.managesupervisioncase.ValidateOutOfCountyCaseEvent;
import messaging.managesupervisioncase.reply.OutOfCountyCaseEvent;
import messaging.managesupervisioncase.reply.ValidateResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;
import naming.OutOfCountyCaseControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.OffenseHelper;
import ui.common.form.OffenseSearchForm;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.managesupervisioncase.form.OutOfCountyCaseForm;
import ui.supervision.managesupervisioncase.helper.OutOfCountyCaseUIHelper;

public class DisplayOutOfCountyCaseSummaryAction extends LookupDispatchAction {

	/**
	 * @roseuid 4443EFD203A6
	 */
	public DisplayOutOfCountyCaseSummaryAction() {

	}

	/**
	 * @see LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.validate", "validate");
		keyMap.put("prompt.findOffenseCode", "findOffenseCode");
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}

	/**
	 * This validates the Offense Code that was entered by the user.
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return the appropriate ActionForward
	 */
	public ActionForward validate(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		OutOfCountyCaseForm ooc = (OutOfCountyCaseForm) aForm;

		//		OutOfCountyCaseHelper helper =
		// OutOfCountyCaseHelper.getInstance(aRequest.getSession().getId());
		//		String message = helper.validateOffense(ooc.getOffenseId(),
		// ooc.getCaseTypeId());
		String message = UICommonSupervisionHelper.validateOffense(ooc.getOffenseId(), ooc.getCourtId(),false);
		if (message != null) {
			sendToErrorPage(aRequest, message);
		}

		String action = ooc.getAction();
		if ((action.equals(UIConstants.CREATE))
				|| (action.equals(UIConstants.UPDATE))) {
			forward = aMapping.findForward(UIConstants.VALIDATE_SUCCESS);
		} else if ((action.equals(UIConstants.PRETRIAL_CREATE))
				|| (action.equals(UIConstants.PRETRIAL_UPDATE))) {
			forward = aMapping
					.findForward(UIConstants.VALIDATE_PRETRIAL_SUCCESS);
		}
		return forward;
	}

	/**
	 * This opens a dialog to allow the user to search for an Offense by
	 * multiple criteria.
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return the appropriate ActionForward
	 */
	public ActionForward findOffenseCode(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		OutOfCountyCaseForm ooc = (OutOfCountyCaseForm) aForm;
		OffenseSearchForm searchOffenseForm = (OffenseSearchForm) UICommonSupervisionHelper
				.getOffenseSearchForm(aRequest, true);
		String action = ooc.getAction();
		// make sure the offense search criteria is initialized
		searchOffenseForm.clearSearchFields();

		if ((action.equals(UIConstants.CREATE))
				|| (action.equals(UIConstants.UPDATE))) {
			forward = aMapping.findForward(UIConstants.FIND_OFFENSE_SUCCESS);
		} else if ((action.equals(UIConstants.PRETRIAL_CREATE))
				|| (action.equals(UIConstants.PRETRIAL_UPDATE))) {
			forward = aMapping
					.findForward(UIConstants.FIND_OFFENSE_PRETRIAL_SUCCESS);
		} else {
			forward = aMapping.findForward(UIConstants.FAILURE);
		}

		return forward;
	}

	/**
	 * This validates the Out of County case data that was entered / changed by
	 * the user.
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return the appropriate ActionForward
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = new ActionForward();
		OutOfCountyCaseForm ooc = (OutOfCountyCaseForm) aForm;

		String action = ooc.getAction();
		if (action.equals(UIConstants.CREATE)) {
			forward = aMapping.findForward(UIConstants.CREATE_SUCCESS);
			String message = validateCJIS(ooc.getCjis(), ooc.getCaseNum());
			if (message != null) {
				sendToErrorPage(aRequest, message);
				forward = aMapping.findForward(UIConstants.VALIDATE_SUCCESS);
			} else {
				message = this.getPartyName(ooc);
				if (message == null) {
					sendToErrorPage(aRequest, "error.noNameFoundforSNU");
					forward = aMapping.findForward(UIConstants.VALIDATE_SUCCESS);
				}
			}
			
		} else if (action.equals(UIConstants.UPDATE)) {
			forward = aMapping.findForward(UIConstants.UPDATE_SUCCESS);
			String message = validateCJIS(ooc.getCjis(), ooc.getCaseNum());
			if (message == null) {
				message = validateUpdateReason(aRequest, ooc);
				if (message != null) {
					sendToErrorPage(aRequest, message);
					forward = aMapping.findForward(UIConstants.VALIDATE_SUCCESS);
				} else {
					message = this.getPartyName(ooc);
					if (message == null) {
						sendToErrorPage(aRequest, "error.noNameFoundforSNU");
						forward = aMapping.findForward(UIConstants.VALIDATE_SUCCESS);
					}	
				}
			} else {
				sendToErrorPage(aRequest, message);
				forward = aMapping.findForward(UIConstants.VALIDATE_SUCCESS);
			}
		} else if (action.equals(UIConstants.PRETRIAL_CREATE)) {
			forward = aMapping.findForward(UIConstants.CREATE_PRETRIAL_SUCCESS);
		} else if (action.equals(UIConstants.PRETRIAL_UPDATE)) {
			forward = aMapping.findForward(UIConstants.UPDATE_PRETRIAL_SUCCESS);
		} else {
			forward = aMapping.findForward(UIConstants.FAILURE);
		}

		String message = UICommonSupervisionHelper.validateOffense(ooc.getOffenseId(), ooc.getCourtId(),false);
// 11-11-09  Special offense validation for Misdemeanor degree C on courts CSR and HCT -- see defect 62584 isolation
		if (message == null) {
			if ("CSR".equals(ooc.getCaseTypeId()) || "HCT".equals(ooc.getCaseTypeId()) ){
				OffenseCodeResponseEvent offense = OffenseHelper.retrieveOffenseCode(ooc.getOffenseId());
				if (!"C".equals(offense.getDegree()) || !"M".equals(offense.getLevel())){
					sendToErrorPage(aRequest, "error.managesupervisioncase.offensecodelevelinvalid");
					forward = aMapping.findForward(UIConstants.VALIDATE_SUCCESS);
				}
			}
		}
		if (message != null) {
			sendToErrorPage(aRequest, message);
			if (action.equals(UIConstants.CREATE)|| action.equals(UIConstants.UPDATE)) {
				forward = aMapping.findForward(UIConstants.VALIDATE_SUCCESS);
			} else {
				forward = aMapping.findForward(UIConstants.VALIDATE_PRETRIAL_SUCCESS);
			}
		}

		return forward;
	}

	private String getPartyName(OutOfCountyCaseForm oocForm) {
		String nameFound = "";
		//commented out.  causing problems if back button his hit from next page.
		/*if (oocForm.getNamePtr() == null ||
			oocForm.getNamePtr().trim().equals("") ||
			(oocForm.getCurrentNamePtr() != null && oocForm.getCurrentNamePtr().equals(oocForm.getNamePtr()) )){
			return nameFound;  
		} */
		if (oocForm.getNameSeqNum() == null || oocForm.getNameSeqNum().equals("")){
			oocForm.setNameSeqNum(oocForm.getCurrentNameSeqNum());
			oocForm.setNamePtr(oocForm.getCurrentNamePtr());
		}
		Name aName = OutOfCountyCaseUIHelper.getPartyName(oocForm.getSpn(), oocForm.getNameSeqNum().trim());
		if (aName == null){
			nameFound = null;
		} else {
			oocForm.setFirstName(aName.getFirstName());
			oocForm.setMiddleName(aName.getMiddleName());
			oocForm.setLastName(aName.getLastName());
		}
		return nameFound;
	}

	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}

	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

	private void sendToErrorPage(HttpServletRequest aRequest, String msg) {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}

	private String validateUpdateReason(HttpServletRequest aRequest,
			OutOfCountyCaseForm ooc) {
		if (caseWasModified(aRequest, ooc)
				&& (ooc.getReasonForUpdateId() == null || ooc
						.getReasonForUpdateId().equals(""))) {
			return "error.managesupervisioncase.reasonforupdaterequired";
		}

		return null;
	}

	private String validateCJIS(String cjisNum, String caseNum) {
		if (cjisNum != null && !cjisNum.trim().equals("")) {
			ValidateOutOfCountyCaseEvent request = (ValidateOutOfCountyCaseEvent) EventFactory
					.getInstance(OutOfCountyCaseControllerServiceNames.VALIDATEOUTOFCOUNTYCASE);

			// set the criteria from the form
			request.setCJISNum(cjisNum);
			request.setCaseNum(caseNum);

			IDispatch dispatch = EventManager
					.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(request);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch
					.getReply();
			MessageUtil.processReturnException(compositeResponse);

			ValidateResponseEvent response = (ValidateResponseEvent) MessageUtil
					.compositeToCollection(compositeResponse,
							ValidateResponseEvent.class).iterator().next();

			if (response != null) {
				return response.getMessage();
			}
		}
		return null;
	}

	private boolean caseWasModified(HttpServletRequest aRequest,
			OutOfCountyCaseForm oocForm) {
		// get original values for the case that were cached before the user
		// made changes
		Map cases = (Map) aRequest.getSession().getAttribute(
				"outOfCountyCaseValues");
		String caseKey = oocForm.getPrimaryKey();
		if (cases.containsKey(caseKey)) {
			OutOfCountyCaseEvent oocCase = (OutOfCountyCaseEvent) cases
					.get(caseKey);
			// now determine if any changes were made
			if (isChanged(oocCase.getDispositionDate(), oocForm
					.getDispositionDate())) {
				return true;
			}
			if (isChanged(oocCase.getDispositionTypeId(), oocForm
					.getDispositionTypeId())) {
				return true;
			}
			if (isChanged(oocCase.getArrestDate(), oocForm.getArrestDate())) {
				return true;
			}
			if (isChanged(oocCase.getCjisNum(), oocForm.getCjis().toUpperCase())) {
				return true;
			}
			if (isChanged(oocCase.getContactFirstName(), oocForm
					.getContactFirstName().toUpperCase())) {
				return true;
			}
			if (isChanged(oocCase.getContactLastName(), oocForm
					.getContactLastName().toUpperCase())) {
				return true;
			}
			if (isChanged(oocCase.getContactMiddleName(), oocForm
					.getContactMiddleName().toUpperCase())) {
				return true;
			}
			if (isChanged(oocCase.getContactJobTitle(), oocForm
					.getContactJobTitle().toUpperCase())) {
				return true;
			}
			if (isChanged(oocCase.getContactPhoneNum(), oocForm
					.getContactPhone())) {
				return true;
			}
			if (isChanged(oocCase.getContactPhoneExt(), oocForm
					.getContactPhoneExt())) {
				return true;
			}
			if (isChanged(oocCase.getOriginalCountyId(), oocForm
					.getOrgJurisCountyId().toUpperCase())) {
				return true;
			}
			if (isChanged(oocCase.getOffenseDate(), oocForm.getOffenseDate())) {
				return true;
			}
			if (isChanged(oocCase.getOriginalAgencyName(), oocForm
					.getAgencyName().toUpperCase())) {
				return true;
			}
			if (isChanged(oocCase.getOriginalCaseNum(), oocForm
					.getOrgJurisCaseNum().toUpperCase())) {
				return true;
			}
			if (isChanged(oocCase.getOriginalCourtNum(), oocForm
					.getOrgJurisCourt().toUpperCase())) {
				return true;
			}
			if (isChanged(oocCase.getPersonId(), oocForm.getOrgJurisPID()
					.toUpperCase())) {
				return true;
			}
			if (isChanged(oocCase.getOriginalStateId(), oocForm.getStateId()
					.toUpperCase())) {
				return true;
			}
			if (isChanged(oocCase.getSupervisionBeginDate(), oocForm
					.getSupervisionBeginDate())) {
				return true;
			}
			if (isChanged(oocCase.getSupervisionEndDate(), oocForm
					.getSupervisionEndDate())) {
				return true;
			}
			if (isChanged(oocCase.getPretrialInterventionBeginDate(), oocForm
					.getPretrialInterventionBegin())) {
				return true;
			}
			if (isChanged(oocCase.getPretrialInterventionEndDate(), oocForm
					.getPretrialInterventionEnd())) {
				return true;
			}
			if (isChanged(oocCase.getSentenceDate(), oocForm.getSentenceDate())) {
				return true;
			}
			if (isChanged(oocCase.getStateOffenseCodeId(), oocForm
					.getOffenseId())) {
				return true;
			}
			if (isChanged(oocCase.getTransferInDate(), oocForm
					.getTransferInDate())) {
				return true;
			}
			if (isChanged(new Integer(oocCase.getConfinementDays()),
					stringToInteger(oocForm.getConfinementLengthDay()))) {
				return true;
			}
			if (isChanged(new Integer(oocCase.getConfinementMonths()),
					stringToInteger(oocForm.getConfinementLengthMonth()))) {
				return true;
			}
			if (isChanged(new Integer(oocCase.getConfinementYears()),
					stringToInteger(oocForm.getConfinementLengthYear()))) {
				return true;
			}
			if (isChanged(new Integer(oocCase.getSupervisionDays()),
					stringToInteger(oocForm.getSupervisionLengthDay()))) {
				return true;
			}
			if (isChanged(new Integer(oocCase.getSupervisionMonths()),
					stringToInteger(oocForm.getSupervisionLengthMonth()))) {
				return true;
			}
			if (isChanged(new Integer(oocCase.getSupervisionYears()),
					stringToInteger(oocForm.getSupervisionLengthYear()))) {
				return true;
			}
		}
		return false;
	}

	private boolean isChanged(String oldValue, String newValue) {
		if ((oldValue == null && (newValue != null && !newValue.equals("")))
				|| (oldValue != null && !oldValue.equals(newValue))) {
			return true;
		}
		return false;
	}

	private boolean isChanged(Object oldValue, Object newValue) {
		if ((oldValue == null && newValue != null)
				|| (oldValue != null && !oldValue.equals(newValue))) {
			return true;
		}
		return false;
	}

	private Integer stringToInteger(String value) {
		Integer returnVal = new Integer("0");
		if (value != null && !value.trim().equals("")) {
			returnVal = new Integer(value);
		}
		return returnVal;
	}
}
