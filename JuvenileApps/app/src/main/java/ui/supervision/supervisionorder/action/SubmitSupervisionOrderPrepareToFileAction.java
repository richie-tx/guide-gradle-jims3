/*
 * Created on Dec 27, 2005
 */
package ui.supervision.supervisionorder.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionorder.PrepareToFileSupervisionOrderEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.supervisionorder.form.SupervisionOrderForm;

/**
 * @author h rodriguez
 */
public class SubmitSupervisionOrderPrepareToFileAction extends LookupDispatchAction {
    private static String BLANK = "";

    /**
     *
     */
    public SubmitSupervisionOrderPrepareToFileAction() {

    }

    /**
     * @see LookupDispatchAction#getKeyMethodMap()
     * @return Map
     */
    protected Map getKeyMethodMap() {
        Map keyMap = new HashMap();
        keyMap.put("button.finish", "finish");
        //keyMap.put("button.finishPrint", "finishPrint");
        keyMap.put("button.back", "back");
        keyMap.put("button.cancel", "cancel");
        return keyMap;
    }

    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        sof.setAction(UIConstants.CONFIRM_PREPARE_TO_FILE);

        PrepareToFileSupervisionOrderEvent requestEvent = this.getRequestEvent(sof);

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(requestEvent);

        CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
        Map responseMap = MessageUtil.groupByTopic(compositeResponse);
        MessageUtil.processReturnException(responseMap);

        sof.setOrderStatusId(PDCodeTableConstants.STATUS_PENDING_ID);
        sof.setOrderFileDate(new Date());
        sof.setStatusChangeDate(new Date()); 
        sof.updateReferenceVariableMap();

        forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CONFIRM_TO_FILE_SUCCESS, UIUtil.getCurrentUserAgencyID()));
        return forward;
    }

    /**
     * @param sof
     * @return
     */
    private PrepareToFileSupervisionOrderEvent getRequestEvent(SupervisionOrderForm sof) {
        PrepareToFileSupervisionOrderEvent requestEvent = (PrepareToFileSupervisionOrderEvent) EventFactory
                .getInstance(SupervisionOrderControllerServiceNames.PREPARETOFILESUPERVISIONORDER);

        requestEvent.setSupervisionOrderId(sof.getOrderId());
        //No need to retrieve the presentor information since we'd already
        // retrieved it to display name on web page.
        requestEvent.setPresentorFirstName(sof.getPresentedByFirstName());
        requestEvent.setPresentorLastName(sof.getPresentedByLastName());
        requestEvent.setSignedDate(sof.getSignedDate());
        requestEvent.setJudgeSignedDate(sof.getSignedByJudgeDate());

        if (!sof.getMagFirstName().equals(BLANK) && !sof.getMagLastName().equals(BLANK)) {
            requestEvent.setSignorFirstName(sof.getMagFirstName());
            requestEvent.setSignorLastName(sof.getMagLastName());
            requestEvent.setSignorType(PDCodeTableConstants.SIGNOR_MAGISTRATE_ID);
        } else if (!sof.getJudgeFirstName().equals(BLANK) && !sof.getJudgeLastName().equals(BLANK)) {
            requestEvent.setSignorFirstName(sof.getJudgeFirstName());
            requestEvent.setSignorLastName(sof.getJudgeLastName());
            requestEvent.setSignorType(PDCodeTableConstants.SIGNOR_JUDGE_ID);
        }

        return requestEvent;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
        return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK, UIUtil.getCurrentUserAgencyID()));
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
        ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.CANCEL, UIUtil.getCurrentUserAgencyID()));
        return forward;
    }

    private void sendToErrorPage(HttpServletRequest aRequest, String msg) {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
        saveErrors(aRequest, errors);
    }
}
