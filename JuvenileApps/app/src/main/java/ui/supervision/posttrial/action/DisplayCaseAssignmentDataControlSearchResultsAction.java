//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\posttrial\\action\\DisplayCaseAssignmentDataControlSearchResultsAction.java

package ui.supervision.posttrial.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.posttrial.GetAssignmentAndHistoryEvent;
import messaging.posttrial.reply.CaseAssignmentDataControlResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.posttrial.form.CaseAssignmentDataControlForm;
import ui.supervision.posttrial.form.CaseAssignmentDataControlHeaderForm;

/*
 * 
 * @author cshimek
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class DisplayCaseAssignmentDataControlSearchResultsAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplayCaseAssignmentDataControlSearchResultsAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.submit", "submit");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		String forwardStr=UIConstants.SUCCESS;
		CaseAssignmentDataControlForm cadsForm = (CaseAssignmentDataControlForm) aForm;
		GetAssignmentAndHistoryEvent ahEvent = new GetAssignmentAndHistoryEvent();
		ahEvent.setCaseNumber(cadsForm.getCaseNum());
		ahEvent.setCdi(cadsForm.getCdi());
		
        CompositeResponse response = MessageUtil.postRequest(ahEvent);        
        CaseAssignmentDataControlResponseEvent resp = (CaseAssignmentDataControlResponseEvent) MessageUtil.filterComposite(response, CaseAssignmentDataControlResponseEvent.class);

        if(resp != null){
			cadsForm.setCurrentCaseAssignment(resp.getCurrentAssignment());
			cadsForm.setCaseAssignmentHistoryList(resp.getCaseAssignmentHistories());
			CaseAssignmentDataControlHeaderForm hdrForm = (CaseAssignmentDataControlHeaderForm) getSessionForm(aMapping, aRequest,	"caseAssignmentDataControlHeaderForm", true);
			hdrForm.setSuperviseeName(resp.getDefendantName());
			hdrForm.setSpn(resp.getDefendantId());
			String ssnStr = "";
			if (resp.getSsn() != null && !resp.getSsn().equals("")){
				StringBuffer aStr = new StringBuffer();
				for (int x=0; x< resp.getSsn().length(); x++){
					if (x == 3 || x == 5){
						aStr.append("-");
					}	
					aStr.append(resp.getSsn().substring(x,x+1));
				}
				ssnStr = aStr.toString();
			}
			hdrForm.setSsn(ssnStr);
			if (resp.getSexId() != null){
				hdrForm.setSex(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SEX, resp.getSexId()));
			}
			if (resp.getDob() != null) {
				hdrForm.setDob(DateUtil.dateToString(resp.getDob(), DateUtil.DATE_FMT_1));
			}
			hdrForm.setCaseNum(cadsForm.getCaseNum());
			hdrForm.setCdi(cadsForm.getCdi());
			String courtNum = resp.getCourt();
			if (courtNum != null && courtNum.length() > 3){
				courtNum = courtNum.substring(courtNum.length() - 3, courtNum.length());
			}
			hdrForm.setCourtNum(courtNum);
			hdrForm.setCaseActivelySupervised(resp.isCaseSupervised());
			hdrForm.setSuperviseeActivelySupervised(resp.isSuperviseeSupervised());
		} else {
			sendToErrorPage(aRequest,"error.no.search.results.found");
			forwardStr=UIConstants.FAILURE;
		}
		return aMapping.findForward(forwardStr);
	}

}