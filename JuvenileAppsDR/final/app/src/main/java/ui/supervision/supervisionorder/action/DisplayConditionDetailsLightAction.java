//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supervisionorder\\action\\DisplaySupervisionOrderSelectSugOrderAction.java

package ui.supervision.supervisionorder.action;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionorder.CreateSupervisionOrderEvent;
import messaging.supervisionorder.UpdateSupervisionOrderEvent;
import messaging.supervisionorder.reply.CaseOrderResponseEvent;
import messaging.supervisionorder.reply.OrderCreateErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.PDConstants;
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
 * @author dgibler
 *  
 */
public class DisplayConditionDetailsLightAction extends JIMSBaseAction
{

    /**
     * @roseuid 438F23F9012F
     */
    public DisplayConditionDetailsLightAction()
    {

    }

    public ActionForward saveContinue(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        //long timeStart = System.currentTimeMillis();

        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        UISupervisionOrderHelper.addNewPasoLightConditions(sof);
        Collection mySelCond = sof.getConditionSelectedList();
        if (mySelCond == null || mySelCond.size() < 1)
        {
            sendToErrorPage(aRequest, "error.noConditionsExist");
            return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE
                    + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
        }
        sof.clearDeleteBox();
        boolean historicalOrderCreated = false;
        CaseOrderResponseEvent orderRespEvent = null;
        if (!(sof.getIsPretrialInterventionOrder() && UIConstants.UPDATE.equals(sof.getAction())))
        {
            //The supervision order may have already been created depending on path taken
            // previously.
            if (sof.getOrderId() == null || sof.getOrderId().equals(PDConstants.BLANK))
            {
                if (!this.createSupervisionOrder(sof, aRequest))
                {
                    return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE
                            + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
                }
                historicalOrderCreated = true;
                // get Order
                orderRespEvent = (CaseOrderResponseEvent) MessageUtil.filterComposite(UISupervisionOrderHelper
                        .getCompositeResponse(EventManager.getSharedInstance(EventManager.REQUEST)),
                        CaseOrderResponseEvent.class);
            }
        }
        if (!historicalOrderCreated)
        {
            sof.setOrderStatusId(PDCodeTableConstants.STATUS_DRAFT_ID);
            sof.setStatusChangeDate(new Date()); 
            
            UpdateSupervisionOrderEvent supervisionOrderEvt = UISupervisionOrderHelper.updateSupervisionOrder(sof);
            orderRespEvent = postUpdateEvent(supervisionOrderEvt, aRequest);
        }
        //		set orderId in the form as we might have created a new version as a result of
        // save/Continue
        if (orderRespEvent != null)
        {
            sof.setOrderId(orderRespEvent.getOrderId());
            sof.setOrderStatusId(orderRespEvent.getOrderStatusId());
            sof.setStatusChangeDate(orderRespEvent.getStatusChangeDate()); 
            sof.setOrderTitleId(orderRespEvent.getOrderTitleId()); //01/29/07 dag
            sof.setLikeConditionInd(false);
        }
        //UISupervisionOrderHelper.postUpdateOrderStatusEvent(sof.getOrderId(),
        // PDCodeTableConstants.STATUS_DRAFT_ID);
        sof.setVersionTypeChangeAllowed(true);
        UISupervisionOrderHelper.getSupervisionOrderDetails(sof);
        forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SUCCESS
                + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
        //long timeEnd = System.currentTimeMillis();
        //System.out.println("Total Time(milli seconds) to display Condition detail page : " + (timeEnd - timeStart));
        return forward;
    }

    private CaseOrderResponseEvent postUpdateEvent(UpdateSupervisionOrderEvent supervisionOrder,
            HttpServletRequest aRequest)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(supervisionOrder);

        CompositeResponse compositeResponse = UISupervisionOrderHelper.getCompositeResponse(dispatch);
        MessageUtil.processReturnException(compositeResponse);

        Collection orderCreatedAlreadyErrs = MessageUtil.compositeToCollection(compositeResponse,
                OrderCreateErrorResponseEvent.class);
        if (orderCreatedAlreadyErrs != null && orderCreatedAlreadyErrs.size() > 0)
        {
            sendToErrorPage(aRequest, "error.supOrderAlreadyExists");
            return null;
        }
        Collection searchResults = MessageUtil.compositeToCollection(compositeResponse, CaseOrderResponseEvent.class);
        MessageUtil.processEmptyCollection(searchResults);
        Iterator iter = searchResults.iterator(); // only ome record
        CaseOrderResponseEvent orderRespEvent = null;
        if (iter.hasNext())
        {
            orderRespEvent = (CaseOrderResponseEvent) iter.next();
        }

        return orderRespEvent;

    }

    /**
     * Creates supervision order.
     * 
     * @param sof
     *            returns true if succcessful else returns false
     */
    private boolean createSupervisionOrder(SupervisionOrderForm sof, HttpServletRequest aRequest)
    {
        // post event to create Order
        CreateSupervisionOrderEvent supervisionOrder = UISupervisionOrderHelper.createSupervisionOrder(sof);
        if (sof.getIsPretrialInterventionOrder())
        {
            supervisionOrder.setHistoricalOrder(true);
            supervisionOrder.setHistoricalOrderStatus(PDCodeTableConstants.STATUS_DRAFT_ID);
        }
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(supervisionOrder);

        CompositeResponse compositeResponse = UISupervisionOrderHelper.getCompositeResponse(dispatch);
        Collection orderCreatedAlreadyErrs = MessageUtil.compositeToCollection(compositeResponse,
                OrderCreateErrorResponseEvent.class);
        if (orderCreatedAlreadyErrs != null && orderCreatedAlreadyErrs.size() > 0)
        {
            sendToErrorPage(aRequest, "error.supOrderAlreadyExists");
            return false;
        }
        //		// get Order
        CaseOrderResponseEvent core = (CaseOrderResponseEvent) MessageUtil.filterComposite(compositeResponse,
                CaseOrderResponseEvent.class);
        if (core != null)
        {
            sof.setOrderId(core.getOrderId());
        }
        return true;

    }

    public ActionForward addMoreConditions(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        //long timeStart = System.currentTimeMillis();

        ActionForward forward = new ActionForward();
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        UISupervisionOrderHelper.addNewPasoLightConditions(sof);
        forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.FAILURE
                + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));

        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.BACK
                + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        SupervisionOrderForm sof = (SupervisionOrderForm) aForm;
        ActionForward forward = aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(
                UIConstants.CANCEL + UIConstants.LIGHT, UIUtil.getCurrentUserAgencyID()));
        return forward;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
        keyMap.put("button.saveContinue", "saveContinue");
        keyMap.put("button.back", "back");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.addMoreConditions", "addMoreConditions");

    }

}