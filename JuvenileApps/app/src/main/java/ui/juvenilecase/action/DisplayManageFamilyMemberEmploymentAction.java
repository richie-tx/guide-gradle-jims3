package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyMemberEmploymentInfoEvent;
import messaging.juvenilecase.reply.FamilyMemberEmploymentInfoResponseEvent;
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
public class DisplayManageFamilyMemberEmploymentAction extends LookupDispatchAction
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
        myForm.clearEmployment();
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        // Sending PD Request Event
        GetFamilyMemberEmploymentInfoEvent event = (GetFamilyMemberEmploymentInfoEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBEREMPLOYMENTINFO);
        event.setMemberNum(myForm.getMemberNumber());
        FamilyMemberEmploymentInfoResponseEvent responseEvt;
        JuvenileMemberForm.MemberEmployment myEmployment;
        List myNewList = new ArrayList();

        List employments = MessageUtil.postRequestListFilter(event, FamilyMemberEmploymentInfoResponseEvent.class);
        if (employments != null && employments.size() > 0)
        {
            Iterator iter = employments.iterator();
            while (iter.hasNext())
            {
                responseEvt = (FamilyMemberEmploymentInfoResponseEvent) iter.next();
                if (responseEvt != null)
                {
                    myEmployment = UIJuvenileHelper
                            .getJuvMemberFormMemberEmploymentInfoFROMEmploymentInfoRespEvt(responseEvt);
                    if (myEmployment != null)
                        myNewList.add(myEmployment);
                }
            }
        }

        myForm.setEmploymentInfoList(UIJuvenileHelper.sortMemberEmploymentList(myNewList));
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

