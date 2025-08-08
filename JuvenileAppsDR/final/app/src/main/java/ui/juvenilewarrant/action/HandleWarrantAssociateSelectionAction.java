package ui.juvenilewarrant.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilewarrant.GetJuvenileAssociateDataEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateAddressResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import mojo.pattern.ui.IFormDirector;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDJuvenileWarrantConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilewarrant.form.JuvenileAssociateBean;
import ui.juvenilewarrant.form.JuvenileAssociateForm;
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.juvenilewarrant.helper.JuvenileAssociateAddressRequestAssembler;
import ui.juvenilewarrant.helper.JuvenileWarrantFormDirector;

/**
 * @author ryoung
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class HandleWarrantAssociateSelectionAction extends LookupDispatchAction
{

    /**
     * a
     * 
     * @roseuid 41E6CCE20232
     */
    public HandleWarrantAssociateSelectionAction()
    {

    }

    protected Map getKeyMethodMap()
    {
        Map buttonMap = new HashMap();
        buttonMap.put("button.next", UIConstants.NEXT);
        //		buttonMap.put("button.create", UIConstants.CREATE);
        //		buttonMap.put("button.update", UIConstants.UPDATE);
        buttonMap.put("button.manageAddresses", UIConstants.MANAGE_ADDRESSES);
        buttonMap.put("button.continue", UIConstants.CONTINUE_SUCCESS);
        buttonMap.put("button.cancel", UIConstants.CANCEL);
        buttonMap.put("button.back", UIConstants.BACK);
        return buttonMap;
    }

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        System.err.println("NEXT BUTTON PRESSED -- ASSOCIATE CREATE/UPDATE PAGE");
        String forward = null;

        JuvenileAssociateForm jaForm = (JuvenileAssociateForm) aForm;

        // struts has a funny way of handling updates that occur inside of a
        // logic:iterate.
        // Struts knows how to read out of collections using logic:iterate,
        // but does not understand how to put the user changes/input back into
        // the
        // objects of the collection... in short, it does not know how to write
        // updates
        // back to the collection.
        // All the changes are in String Arrays on the request
        jaForm.initDateOfBirth();
        //jaForm.populateAddressValuesFromRequest(aRequest);
        JuvenileAssociateAddressRequestAssembler assembler = new JuvenileAssociateAddressRequestAssembler(aRequest,
                "associateAddresses", jaForm.getAssociateAddresses(), false);
        assembler.assemble();

        jaForm.removeBlankAddresses();
        String pageAction = jaForm.getAction();

        if (UIConstants.CREATE.equals(pageAction))
        {
            forward = UIConstants.CREATE_SUCCESS;
        }

        else
        {
            forward = UIConstants.UPDATE_SUCCESS;
        }
        return aMapping.findForward(forward);

    }

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward continueSuccess(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        String forward = null;
        JuvenileAssociateForm jaForm = (JuvenileAssociateForm) aForm;

        String pageAction = jaForm.getAction();
        if (UIConstants.CREATE.equals(pageAction))
        {
            forward = UIConstants.CREATE_SUCCESS;
        }

        else
        {
            //jaForm.populateAddressValuesFromRequest(aRequest);
            forward = UIConstants.UPDATE_SUCCESS;
        }
        return aMapping.findForward(forward);

    }

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward manageAddresses(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = new ActionForward();

        JuvenileAssociateForm jaForm = (JuvenileAssociateForm) aForm;
        //		jaForm.populateAddressValuesFromRequest(aRequest);
        JuvenileAssociateAddressRequestAssembler assembler = new JuvenileAssociateAddressRequestAssembler(aRequest,
                "associateAddresses", jaForm.getAssociateAddresses(), false);
        assembler.assemble();
        forward = aMapping.findForward(UIConstants.MANAGE_ADDRESS_SUCCESS);
        return forward;
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileAssociateForm jaForm = (JuvenileAssociateForm) aForm;

        IFormDirector director = new JuvenileWarrantFormDirector(aRequest);
        JuvenileWarrantForm form = (JuvenileWarrantForm) director.getForm();

        String flow = form.getWarrantTypeUI();

        String forwardStr = UIConstants.CANCEL;

        if (jaForm.isDetails())
        {
            forwardStr = "cancelDetails";
            //aRequest.setAttribute("associateNumber",
            // jaForm.getAssociateNum());
        }
        else if (UIConstants.UPDATE_VOP_SUCCESS.equals(flow) || UIConstants.UPDATE_VOP_CONFIRM.equals(flow))
        {
            jaForm.clear();
            forwardStr = UIConstants.UPDATE_VOP_SUCCESS;
        }
        else if (UIConstants.UPDATE_OIC_SUCCESS.equals(flow) || UIConstants.UPDATE_OIC_CONFIRM.equals(flow))
        {
            jaForm.clear();
            forwardStr = UIConstants.UPDATE_OIC_SUCCESS;
        }
        else
        {
            jaForm.clear();
            forwardStr = UIConstants.CANCEL;
        }

        return aMapping.findForward(forwardStr);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;
    }

    // This was added for handling the Cancel button from the update associate
    // details

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 41E59C150001
     */
    public ActionForward handleCancelDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileAssociateForm jaForm = (JuvenileAssociateForm) aForm;
        jaForm.clear();

        String relationshipId = aRequest.getParameter("relationshipId");
        String associateNum = aRequest.getParameter("associateNumber");
        jaForm.setAssociateNum(associateNum);

        IFormDirector director = new JuvenileWarrantFormDirector(aRequest);
        JuvenileWarrantForm warrantForm = (JuvenileWarrantForm) director.getForm();

        jaForm.setWarrantNum(warrantForm.getWarrantNum());
        JuvenileAssociateBean associateBean = null;

        // for Initiate screens, the associate does not have a number yet...
        // and all the associate data has already been brought over.
        // and is a part of juvenileWarrantform as the associate bean instance
        // use the relationship to lookup the associate bean.

        if (relationshipId != null)
        {
            associateBean = warrantForm.getAssociateByRelationshipId(relationshipId);
            jaForm.setAssociateProperties(associateBean);
        }
        else if (PDJuvenileWarrantConstants.MOTHER_ASSOCIATE_NUM.equals(associateNum)
                || PDJuvenileWarrantConstants.FATHER_ASSOCIATE_NUM.equals(associateNum)
                || PDJuvenileWarrantConstants.OTHER_ASSOCIATE_NUM.equals(associateNum))
        {
            associateBean = warrantForm.getAssociateByRelationshipId(associateNum);
            jaForm.setAssociateProperties(associateBean);
        }
        else
        {
            GetJuvenileAssociateDataEvent assoRequestEvent = (GetJuvenileAssociateDataEvent) EventFactory
                    .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEASSOCIATEDATA);

            assoRequestEvent.setAssociateNumber(associateNum);
            CompositeResponse response = MessageUtil.postRequest(assoRequestEvent);
            jaForm.setProperties(response);
        }

        return aMapping.findForward(UIConstants.SUCCESS);
    }

    private void retrieveAssociateAddress(JuvenileAssociateForm jaForm, ActionMapping aMapping,
            JuvenileAssociateBean associateBean)
    {
        GetJuvenileAssociateDataEvent requestEvent = (GetJuvenileAssociateDataEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEASSOCIATEDATA);

        requestEvent.setAssociateNumber(jaForm.getAssociateNum());

        CompositeResponse response = MessageUtil.postRequest(requestEvent);

        jaForm.clear();

        JuvenileAssociateResponseEvent associateResponse = (JuvenileAssociateResponseEvent) MessageUtil.filterComposite(response,
                JuvenileAssociateResponseEvent.class);
        associateBean.populateFromEventAttributes(associateResponse);

        associateBean.clearAssociateAddresses();

        List assocAddresses = MessageUtil.compositeToList(response, JuvenileAssociateAddressResponseEvent.class);
        Iterator i = assocAddresses.iterator();
        while (i.hasNext())
        {
            JuvenileAssociateAddressResponseEvent addressResponseEvent = (JuvenileAssociateAddressResponseEvent) i.next();
            associateBean.insertAddress(addressResponseEvent);
        }
    }

}
