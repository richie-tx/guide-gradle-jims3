/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyMemberInsuranceEvent;
import messaging.juvenilecase.reply.FamilyMemberInsuranceResponseEvent;
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
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayManageFamilyMemberInsuranceAction extends LookupDispatchAction
{

    //	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest
    // aRequest, HttpServletResponse aResponse)
    //			  {
    //				   return next(aMapping,aForm,aRequest,aResponse);
    //			  }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.next", "next");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        keyMap.put("button.link", "next");
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

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        myForm.clearInsurance();

        // Sending PD Request Event
        GetFamilyMemberInsuranceEvent event = (GetFamilyMemberInsuranceEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERINSURANCE);
        event.setFamilyMemberId(myForm.getMemberNumber());

        FamilyMemberInsuranceResponseEvent responseEvt;
        JuvenileMemberForm.MemberInsurance myInsurance;
        List myNewList = new ArrayList();

        List employments = MessageUtil.postRequestListFilter(event, FamilyMemberInsuranceResponseEvent.class);
        if (employments.size() > 0)
        {
            Iterator iter = employments.iterator();
            while (iter.hasNext())
            {
                responseEvt = (FamilyMemberInsuranceResponseEvent) iter.next();
                if (responseEvt != null)
                {
                    myInsurance = UIJuvenileHelper.getJuvMemberFormMemberInsuranceFROMInsuranceRespEvt(responseEvt);
                    if (myInsurance != null)
                        myNewList.add(myInsurance);
                }
            }
        }

        myForm.setInsuranceInfoList(UIJuvenileHelper.sortMemberInsuranceList(myNewList));

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

