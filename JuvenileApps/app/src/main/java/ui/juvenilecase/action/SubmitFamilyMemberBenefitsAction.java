package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.SaveFamilyMemberAdditionalInfoEvent;
import messaging.family.SaveFamilyMemberBenefitsEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileMemberForm;

public class SubmitFamilyMemberBenefitsAction extends LookupDispatchAction
{
    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.addToList", "addBenefit");
        keyMap.put("button.remove", "removeBenefit");
        keyMap.put("button.update", "update");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        return keyMap;
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;
    }

    public ActionForward removeBenefit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        String myBenefitPos = myForm.getSelectedValue();
        if (myBenefitPos != null && !(myBenefitPos.equals("")))
        {
            if (myForm.getBenefitsInfoList() != null & myForm.getBenefitsInfoList().size() > 0)
                ((List) myForm.getBenefitsInfoList()).remove((Integer.valueOf(myBenefitPos)).intValue());
        }
        ActionForward forward = aMapping.findForward(UIConstants.ADD_SUCCESS);
        myForm.clearBenefit();
        myForm.getCurrentBenefit().setReceivedBy("");
        myForm.getCurrentBenefit().setReceivedByFirstName("");
        myForm.getCurrentBenefit().setReceivedByMiddleName("");
        myForm.getCurrentBenefit().setReceivedByLastName("");
        return forward;
    }

    public ActionForward addBenefit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        myForm.setAction(UIConstants.UPDATE);
        JuvenileMemberForm.MemberBenefit myBenefit = myForm.getCurrentBenefit();
        if(!myBenefit.isReceivingBenefits())
        {
        	 myForm.getCurrentBenefit().setReceivedBy("");
             myForm.getCurrentBenefit().setReceivedByFirstName("");
             myForm.getCurrentBenefit().setReceivedByMiddleName("");
             myForm.getCurrentBenefit().setReceivedByLastName("");
        }
        if (myBenefit != null)
        {
            if (myBenefit.getEligibilityTypeId() != null && !(myBenefit.getEligibilityTypeId().equals("")))
            {
                if (myForm.getBenefitsInfoList() == null)
                    myForm.setBenefitsInfoList(new ArrayList());
                myForm.getBenefitsInfoList().add(myBenefit);
            }
        }
        ActionForward forward = aMapping.findForward(UIConstants.ADD_SUCCESS);
        myForm.setBenefitsInfoList(UIJuvenileHelper.sortMemberBenefitsList((ArrayList) myForm.getBenefitsInfoList()));
        myForm.clearBenefit();
        return forward;
    }

    public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {

        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        // Sending PD Request Event
        SaveFamilyMemberAdditionalInfoEvent event = (SaveFamilyMemberAdditionalInfoEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.SAVEFAMILYMEMBERADDITIONALINFO);
        event.setMemberId(myForm.getMemberNumber());
        Collection myBenefits = myForm.getBenefitsInfoList();
        SaveFamilyMemberBenefitsEvent saveBenefitEvent;
        JuvenileMemberForm.MemberBenefit mySaveBenefit;
        if (myBenefits != null && myBenefits.size() > 0)
        {
            Iterator iter = myBenefits.iterator();
            while (iter.hasNext())
            {
                mySaveBenefit = (JuvenileMemberForm.MemberBenefit) iter.next();
                if (mySaveBenefit.getMemberBenefitId() == null || mySaveBenefit.getMemberBenefitId().equals(""))
                {
                    saveBenefitEvent = UIJuvenileHelper.getSaveFamilyBenefitEvent(mySaveBenefit);
                    event.addRequest(saveBenefitEvent);
                }
            }

        }
        
        MessageUtil.postRequest(event);

        myForm.setAction("createMemberConfirmation");
        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
        return forward;
    }

    /**
     * @param aRequest
     */
    private void sendToErrorPage(HttpServletRequest aRequest, String msg)
    {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
        saveErrors(aRequest, errors);
    }
}// END CLASS

