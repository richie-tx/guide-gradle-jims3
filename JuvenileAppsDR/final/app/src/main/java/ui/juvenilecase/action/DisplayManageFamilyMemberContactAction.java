package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyMemberContactEvent;
import messaging.juvenilecase.reply.FamilyMemberEmailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberPhoneResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileMemberForm;

public class DisplayManageFamilyMemberContactAction extends LookupDispatchAction
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
        myForm.clearContact();
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        // Sending PD Request Event
        GetFamilyMemberContactEvent event = (GetFamilyMemberContactEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERCONTACT);
        event.setMemberId(myForm.getMemberNumber());

        // Getting PD Response Event
        CompositeResponse response = MessageUtil.postRequest(event);
        // Perform Error handling
        Map dataMap = MessageUtil.groupByTopic(response);
        FamilyMemberPhoneResponseEvent responseEvt;
        FamilyMemberEmailResponseEvent emailResponseEvt;
        JuvenileMemberForm.MemberContact myContact;
        List myNewList = new ArrayList();
        if (dataMap != null)
        {
            Collection contacts = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_CONTACT_TOPIC);
            if (contacts != null && contacts.size() > 0)
            {
                Iterator iter = contacts.iterator();
                while (iter.hasNext())
                {
                    responseEvt = (FamilyMemberPhoneResponseEvent) iter.next();
                    if (responseEvt != null)
                    {
                        myContact = UIJuvenileHelper.getJuvMemberFormMemberContactFROMContactRespEvt(responseEvt);
                        if (myContact != null)
                            myNewList.add(myContact);
                    }
                }
            }
            contacts = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_EMAIL_TOPIC);
            if (contacts != null && contacts.size() > 0)
            {
                Iterator iter = contacts.iterator();
                while (iter.hasNext())
                {
                    emailResponseEvt = (FamilyMemberEmailResponseEvent) iter.next();
                    if (emailResponseEvt != null)
                    {
                        myContact = UIJuvenileHelper.getJuvMemberFormMemberContactFROMEmailRespEvt(emailResponseEvt);
                        if (myContact != null)
                            myNewList.add(myContact);
                    }
                }
            }
        }

        myForm.setContactList(UIJuvenileHelper.sortMemberContactList(myNewList));
        
        String source = aRequest.getParameter("source");
	if(source != null && !"".equals(source) && source.equalsIgnoreCase("MemberContactBtn"))
	{
	    myForm.setAction("update");
	}
	
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

