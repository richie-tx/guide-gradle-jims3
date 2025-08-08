package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.SaveFamilyMemberAdditionalInfoEvent;
import messaging.family.SaveFamilyMemberInsuranceEvent;
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

/**
 * @author jjose
 *  
 */
public class SubmitFamilyMemberInsuranceAction extends LookupDispatchAction
{

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.addToList", "addInsurance");
        keyMap.put("button.remove", "removeInsurance");
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

    public ActionForward removeInsurance(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        String myInsurancePos = myForm.getSelectedValue();
        if (myInsurancePos != null && !(myInsurancePos.equals("")))
        {
            if (myForm.getInsuranceInfoList() != null & myForm.getInsuranceInfoList().size() > 0)
                ((List) myForm.getInsuranceInfoList()).remove((Integer.valueOf(myInsurancePos)).intValue());
        }
        ActionForward forward = aMapping.findForward(UIConstants.ADD_SUCCESS);
        myForm.clearInsurance();
        return forward;
    }

    public ActionForward addInsurance(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        myForm.setAction(UIConstants.UPDATE);
        JuvenileMemberForm.MemberInsurance myInsurance = myForm.getCurrentInsurance();
        if (myInsurance != null)
        {
            if (myInsurance.getInsuranceType() != null && !(myInsurance.getInsuranceType().equals("")))
            {
                if (myForm.getInsuranceInfoList() == null)
                    myForm.setInsuranceInfoList(new ArrayList());
                myForm.getInsuranceInfoList().add(myInsurance);
            }
        }
        ActionForward forward = aMapping.findForward(UIConstants.ADD_SUCCESS);

        List insuranceInfo = myForm.getInsuranceInfoList();

        List sortedList = UIJuvenileHelper.sortMemberInsuranceList(insuranceInfo);
        myForm.setInsuranceInfoList(sortedList);
        myForm.clearInsurance();
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
        List myInsurances = myForm.getInsuranceInfoList();
        SaveFamilyMemberInsuranceEvent saveInsuranceEvent;
        JuvenileMemberForm.MemberInsurance mySaveInsurance;
        if (myInsurances != null && myInsurances.size() > 0)
        {
            Iterator iter = myInsurances.iterator();
            while (iter.hasNext())
            {
                mySaveInsurance = (JuvenileMemberForm.MemberInsurance) iter.next();
                if (mySaveInsurance.getMemberInsuranceId() == null || mySaveInsurance.getMemberInsuranceId().equals(""))
                {
                    saveInsuranceEvent = UIJuvenileHelper.getSaveFamilyInsuranceEvent(mySaveInsurance);
                    event.addRequest(saveInsuranceEvent);
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

