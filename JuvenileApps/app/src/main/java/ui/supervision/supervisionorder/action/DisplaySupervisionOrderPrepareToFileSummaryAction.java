/*
 * Created on Dec 27, 2005

 */
package ui.supervision.supervisionorder.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionorder.PrepareSignaturePageEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import messaging.supervisionorder.reply.OrderCreateErrorResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.UISupervisionOrderHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author h rodriguez
 */
public class DisplaySupervisionOrderPrepareToFileSummaryAction extends JIMSBaseAction
{
	private static String BLANK = "";
	/**
	 * @roseuid 438F23F301FA
	 */
	public DisplaySupervisionOrderPrepareToFileSummaryAction()
	{

	}
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
		if (sof.getSecondaryAction() != null && !sof.getSecondaryAction().equals("") ){
			sof.setAction(sof.getSecondaryAction());
			sof.setSecondaryAction("");
		}
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil.getCurrentUserAgencyID()));
	}

	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
		return forward;
	}

	/**
	* @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	* @return Map
	*/
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.saveAndContinue", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		return keyMap;
	}


	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SupervisionOrderForm sof = (SupervisionOrderForm) aForm;

		UISupervisionOrderHelper.setPresentorAndJudgeInfo(sof);
// ER 61950 Defendant Signature displays on Prepare to File so always validate and not just when action equals Print Signature
		PrepareSignaturePageEvent requestEvent = this.getRequestEvent(sof);
        CompositeResponse compositeResponse = MessageUtil.postRequest(requestEvent);
        OrderCreateErrorResponseEvent ere = (OrderCreateErrorResponseEvent)MessageUtil.filterComposite(compositeResponse, OrderCreateErrorResponseEvent.class);
        if (ere != null){
        	sendToErrorPage(aRequest, "error.supOrderAlreadyExists");
        } else {
        	CaseOrderResponseEvent cre = (CaseOrderResponseEvent)MessageUtil.filterComposite(compositeResponse, CaseOrderResponseEvent.class);
        	sof.setOrderId(cre.getOrderId());
        	sof.setOrderStatusId(PDCodeTableConstants.STATUS_INCOMPLETE_ID);
        	sof.setStatusChangeDate(null); 
    		if (sof.getAction().equals(UIConstants.PRINT_SIGNATURE)){
    			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CONFIRM_TO_FILE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
    		} else {
    			forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS, UIUtil.getCurrentUserAgencyID()));
    		}
        }
		return forward;
	}
	/**
     * @param sof
     * @return
     */
    private PrepareSignaturePageEvent getRequestEvent(SupervisionOrderForm sof) {
        PrepareSignaturePageEvent requestEvent = (PrepareSignaturePageEvent) EventFactory
        .getInstance(SupervisionOrderControllerServiceNames.PREPARESIGNATUREPAGE);
       //may not be null if re-printing signature page.
        requestEvent.setSupervisionOrderId(sof.getOrderId());
        requestEvent.setComments(sof.getComments());
        requestEvent.setPresentorFirstName(sof.getPresentedByFirstName());
        requestEvent.setPresentorLastName(sof.getPresentedByLastName());
        requestEvent.setOrderSignatureDate(sof.getSignedDate());
        requestEvent.setDefendantSignatureDate(sof.getSignedByDefendantDate());
	    if ( sof.getDefendantSignature() != null
	    		&& sof.getDefendantSignature().equalsIgnoreCase("signed")) {
	    	requestEvent.setDefendantSignatureInd(true);
	    } else {
	    	requestEvent.setDefendantSignatureInd(false);
		}
        if (!sof.getMagFirstName().equals(BLANK) && !sof.getMagLastName().equals(BLANK)) {
            requestEvent.setSignorFirstName(sof.getMagFirstName());
            requestEvent.setSignorLastName(sof.getMagLastName());
        } else if (!sof.getJudgeFirstName().equals(BLANK) && !sof.getJudgeLastName().equals(BLANK)) {
            requestEvent.setSignorFirstName(sof.getJudgeFirstName());
            requestEvent.setSignorLastName(sof.getJudgeLastName());
        }
        requestEvent.setJudgeSignatureDate(sof.getSignedByJudgeDate());
        requestEvent.setAgencyId(sof.getAgencyId());
        requestEvent.setOrderCourtId(sof.getCourtId());
        requestEvent.setCurrentCourtId(sof.getCurrentCourtId());
        requestEvent.setCriminalCaseId(sof.getCaseId());
        requestEvent.setVersionNum(sof.getVersionNum());
        requestEvent.setOrderChainNum(sof.getOrderChainNum());

		String defendantId = sof.getDefendantId();
		if (defendantId != null && !defendantId.equals("")
				&& defendantId.length() < 8) {
			StringBuffer sb = new StringBuffer(defendantId);
			for (int i = 0; i < 8 - defendantId.length(); i++) {
				sb.insert(0, "0");
			}
			defendantId = sb.toString();
		}
		requestEvent.setDefendantId(defendantId);
		requestEvent.setOrderTitle(sof.getOrderTitleId());
		//double fa = new Double(sof.getFineAmountTotal()).doubleValue();
		//requestEvent.setFineAmount(fa);
		if ( sof.getFineAmountTotal() == null || sof.getFineAmountTotal().trim().equals( PDConstants.BLANK ) ){
			requestEvent.setFineAmount( 0 );
		} else {
	        double fa = new Double( sof.getFineAmountTotal() ).doubleValue();
	        requestEvent.setFineAmount( fa );
		}
		requestEvent.setOrigJudgeFirstName(sof.getOriginalJudgeFirstName());
		requestEvent.setOrigJudgeLastName(sof.getOriginalJudgeLastName());
		requestEvent.setPlea(sof.getPleaId());
		requestEvent.setJuvCourtId(sof.getJuvCourtId());
		requestEvent.setJuvSupervisionBeginDate(sof.getJuvSupervisionBeginDate());
		if (sof.getJuvSupervisionLengthDays() != null
				&& !sof.getJuvSupervisionLengthDays().equals("")){
			requestEvent.setJuvSupervisionLengthDays(new Integer(sof.getJuvSupervisionLengthDays()));
		} else {
			requestEvent.setJuvSupervisionLengthDays(0);
		}
		if (sof.getJuvSupervisionLengthMonths() != null
				&& !sof.getJuvSupervisionLengthMonths().equals("")){
			requestEvent.setJuvSupervisionLengthMonths(new Integer(sof.getJuvSupervisionLengthMonths()));
		} else {
			requestEvent.setJuvSupervisionLengthMonths(0);
		}
		if (sof.getJuvSupervisionLengthYears() != null
				&& !sof.getJuvSupervisionLengthYears().equals("")){
			requestEvent.setJuvSupervisionLengthYears(new Integer(sof.getJuvSupervisionLengthYears()));
		} else {
			requestEvent.setJuvSupervisionLengthYears(0);
		}
		requestEvent.setSpecialCourtCd(sof.getSpecialCourtCd());
		requestEvent.setPrintedName(sof.getPrintedName());
		requestEvent.setPrintedOffenseDesc(sof.getPrintedOffenseDesc());
		requestEvent.setVersionType(sof.getVersionTypeId());

		boolean limitedSupPeriod = sof.isLimitedSupervisonPeriod();
		//		boolean limitedSupPeriod = sof.isLimitedSupervisionOrderTemp();
		if (limitedSupPeriod) {
			requestEvent.setLimitedSupervisionPeriod(limitedSupPeriod);
			requestEvent.setLimitedSupervisionBeginDate(sof.getSupervisionBeginDate());
			requestEvent.setLimitedSupervisionEndDate(sof.getSupervisionEndDate());
		} else {
			requestEvent.setLimitedSupervisionBeginDate(null);
			requestEvent.setLimitedSupervisionEndDate(null);
		}

		requestEvent.setCaseSupervisionBeginDate(sof.getCaseSupervisionBeginDate());
		requestEvent.setCaseSupervisionEndDate(sof.getCaseSupervisionEndDate());
		if (sof.getConfinementLengthDays() != null
				&& !sof.getConfinementLengthDays().equals("")) {
			requestEvent.setConfinementLengthDays(new Integer(sof.getConfinementLengthDays()).intValue());
		} else {
			requestEvent.setConfinementLengthDays(0);
		}
		if (sof.getConfinementLengthMonths() != null
				&& !sof.getConfinementLengthMonths().equals("")) {
			requestEvent.setConfinementLengthMonths(new Integer(sof.getConfinementLengthMonths()).intValue());
		} else {
			requestEvent.setConfinementLengthMonths(0);
		}
		if (sof.getConfinementLengthYears() != null
				&& !sof.getConfinementLengthYears().equals("")) {
			requestEvent.setConfinementLengthYears(new Integer(sof.getConfinementLengthYears()).intValue());
		} else {
			requestEvent.setConfinementLengthYears(0);
		}
		requestEvent.setDispositionTypeId(sof.getDispositionTypeId());
		//requestEvent.setOffenseId(sof.getOffenseId());
		if (sof.getSupervisionLengthDays() != null
				&& !sof.getSupervisionLengthDays().equals("")) {
			requestEvent.setSupervisionLengthDays(new Integer(sof.getSupervisionLengthDays()).intValue());
		} else {
			requestEvent.setSupervisionLengthDays(0);
		}
		if (sof.getSupervisionLengthMonths() != null
				&& !sof.getSupervisionLengthMonths().equals("")) {
			requestEvent.setSupervisionLengthMonths(new Integer(sof.getSupervisionLengthMonths()).intValue());
		} else {
			requestEvent.setSupervisionLengthMonths(0);
		}
		if (sof.getSupervisionLengthYears() != null
				&& !sof.getSupervisionLengthYears().equals("")) {
			requestEvent.setSupervisionLengthYears(new Integer(sof.getSupervisionLengthYears()).intValue());
		} else {
			requestEvent.setSupervisionLengthYears(0);
		}
		requestEvent.setStatus(sof.getOrderStatusId());
		requestEvent.setMigratedOrder(sof.getIsMigratedOrder());
		if(PDCodeTableConstants.VERSION_TYPE_ID_AMMENDED.equals(sof.getVersionTypeId()) ||
				 (PDCodeTableConstants.VERSION_TYPE_ID_MODIFIED.equals(sof.getVersionTypeId()))){
			requestEvent.setSummaryOfChanges(sof.getSummaryOfChanges());
		}
        return requestEvent;
    }

 	@Override
	protected void addButtonMapping(Map keyMap) {

	}
}
