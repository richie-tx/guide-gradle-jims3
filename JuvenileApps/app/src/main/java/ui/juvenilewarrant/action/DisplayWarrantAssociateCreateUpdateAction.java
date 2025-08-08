package ui.juvenilewarrant.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilewarrant.GetJuvenileAssociateDataEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantAssociatesEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import mojo.pattern.ui.IFormDirector;
import naming.JuvenileWarrantControllerServiceNames;
import naming.SessionAttributeNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilewarrant.UIJuvenileWarrantHelper;
import ui.juvenilewarrant.form.JuvenileAssociateBean;
import ui.juvenilewarrant.form.JuvenileAssociateForm;
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.juvenilewarrant.helper.JuvenileWarrantFormDirector;

/**
 * @author ldeen
 */
public class DisplayWarrantAssociateCreateUpdateAction extends JIMSBaseAction
{

    /**
     * @roseuid 41E6CCE20232
     */
    public DisplayWarrantAssociateCreateUpdateAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 41E59C150001
     */
    protected void addButtonMapping(Map buttonMap)
    {
        buttonMap.put("button.createAssociate", UIConstants.CREATE);
        buttonMap.put("button.updateAssociate", UIConstants.UPDATE);
        buttonMap.put("button.cancel", UIConstants.CANCEL);
        buttonMap.put("button.backToWarrant", "backToWarrant");
    }

    public ActionForward backToWarrant(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        IFormDirector director = new JuvenileWarrantFormDirector(aRequest);
        JuvenileWarrantForm form = (JuvenileWarrantForm) director.getForm();

        ActionForward forward = new ActionForward();
        forward.setPath(form.getBackToWarrantUrl());
        forward.setName(SessionAttributeNames.JUVENILE_WARRANT_FORM);

        return forward;
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileAssociateForm form = (JuvenileAssociateForm) aForm;
        
        ActionForward forward = null;

        boolean hasCancel = "Cancel".equals(aRequest.getParameter("submitAction"));

        boolean isBackToWarrant = "Back To Warrant".equals(aRequest.getParameter("submitAction"));
        if (isBackToWarrant)
            forward = backToWarrant(aMapping, aForm, aRequest, aResponse);

        if (hasCancel == true)
        {
            form.clear();
            forward = cancel(aMapping, aForm, aRequest, aResponse);
        }
        else if (form.getAction().equals(UIConstants.CREATE))
        {
            forward =  create(aMapping, aForm, aRequest, aResponse);
        }
        else if (form.getAction().equals(UIConstants.UPDATE))
        {
            forward = update(aMapping, aForm, aRequest, aResponse);
        }
        else if (form.getAction().equals(UIConstants.UPDATE_LIST))
        {
            form.clear();
            forward = update(aMapping, aForm, aRequest, aResponse);
        }
        return forward;
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        IFormDirector director = new JuvenileWarrantFormDirector(aRequest);
        JuvenileWarrantForm form = (JuvenileWarrantForm) director.getForm();

        String flow = form.getWarrantTypeUI();

        String forwardStr = UIConstants.CANCEL;

        if (UIConstants.UPDATE_VOP_SUCCESS.equals(flow) || UIConstants.UPDATE_VOP_CONFIRM.equals(flow))
        {
            forwardStr = UIConstants.UPDATE_VOP_SUCCESS;
        }
        else if (UIConstants.UPDATE_OIC_SUCCESS.equals(flow) || UIConstants.UPDATE_OIC_CONFIRM.equals(flow))
        {
            forwardStr = UIConstants.UPDATE_OIC_SUCCESS;
        }
        return aMapping.findForward(forwardStr);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward create(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();

        JuvenileAssociateForm jaForm = (JuvenileAssociateForm) aForm;
        // save assocate number for cancel button in jsp
        String assocNum = jaForm.getAssociateNum();
     
        jaForm.clear();
        jaForm.setAssociateNum(assocNum);

        String action = aRequest.getParameter("action");
        jaForm.setAction(action);
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aRequest.getSession().getAttribute("juvenileWarrantForm");
        jaForm.setWarrantNum(jwForm.getWarrantNum());

        // TODO Refactor with an assembler

        // create place holders for two blank addresses
        List twoBlankAddresses = new ArrayList();
        JuvenileAssociateAddressResponseEvent evt = new JuvenileAssociateAddressResponseEvent();
        // TODO Change to use (U)NPROCESSED constant
        evt.setAddressStatus("U");
        twoBlankAddresses.add(evt);

        evt = new JuvenileAssociateAddressResponseEvent();
        evt.setAddressStatus("U");
        twoBlankAddresses.add(evt);

        jaForm.setAssociateAddresses(twoBlankAddresses);
        forward = aMapping.findForward(UIConstants.SUCCESS);
        return forward;

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();

        JuvenileAssociateForm jaForm = (JuvenileAssociateForm) aForm;
        
        String action = aRequest.getParameter("action");
        jaForm.setAction(action);
        String assocNum = aRequest.getParameter("associateNum");

        if (assocNum == null || assocNum.equals(""))
        {
            assocNum = jaForm.getAssociateNum();
        }

        if (assocNum == null || assocNum.equals(""))
        {
            /*
             * When in here, we know that an associate was not selected out of
             * the list.. In this case, we need to display the list of
             * associates for this warrant.
             */
            aRequest.removeAttribute("action");
            JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aRequest.getSession().getAttribute("juvenileWarrantForm");
            // get the associates for that warrant
            GetJuvenileWarrantAssociatesEvent jwRequestEvent = (GetJuvenileWarrantAssociatesEvent) EventFactory
                    .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTASSOCIATES);
            jwRequestEvent.setWarrantNum(jwForm.getWarrantNum());
            
            CompositeResponse replyEvent = MessageUtil.postRequest(jwRequestEvent);
            
            List associates = UIJuvenileWarrantHelper.filterAssociates(replyEvent);

            jwForm.setAssociates(associates);

            forward = aMapping.findForward(UIConstants.LISTSUCCESS);
        }
        else
        {
            /*
             * we know an associate was selected from the list.. so go get the
             * details for that associate, make an associate form and display
             * the update page prepopulated with the associate's data
             */
            GetJuvenileAssociateDataEvent request = new GetJuvenileAssociateDataEvent();
            request.setAssociateNumber(assocNum);
            
            // get associate details            
            CompositeResponse replies = MessageUtil.postRequest(request);

            // All is well.. get the associate information
            JuvenileAssociateResponseEvent associate = (JuvenileAssociateResponseEvent) MessageUtil.filterComposite(replies,
                    JuvenileAssociateResponseEvent.class);
            JuvenileAssociateBean assocBean = new JuvenileAssociateBean();
            assocBean.populateFromEventAttributes(associate);
            List assocAddr = MessageUtil.compositeToList(replies, JuvenileAssociateAddressResponseEvent.class);
            assocBean.setAssociateAddresses(assocAddr);
            assocBean.populateJuvenileAssociateForm(jaForm);
            aRequest.getSession().setAttribute("juvenileAssociateForm", jaForm);
            forward = aMapping.findForward(UIConstants.SUCCESS);
        }

        return forward;
    }

}
