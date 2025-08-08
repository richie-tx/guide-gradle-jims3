package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.SaveFamilyMemberAdditionalInfoEvent;
import messaging.family.SaveFamilyMemberContactEvent;
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
import ui.juvenilecase.form.JuvenileMemberForm.MemberContact;

/**
 * @author jjose
 *  
 */
public class SubmitFamilyMemberContactsAction extends LookupDispatchAction
{

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.remove", "removeContact");
        keyMap.put("button.addToList", "addContact");
        keyMap.put("button.update", "update");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        return keyMap;
    }

    public ActionForward addContact(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        myForm.setAction(UIConstants.UPDATE);
        MemberContact myContact = myForm.getCurrentContact();
        if (myContact != null)
        {
            if (myContact.getContactPhoneNumber().getPrefix() != null
                    && !(myContact.getContactPhoneNumber().getPrefix().equals("")))
            {
                myContact.setPhone(true);
                myContact.setPrimaryInd(myForm.getPrimaryInd());
                if (myForm.getContactList() == null)
                    myForm.setContactList(new ArrayList());
                myForm.getContactList().add(myContact);
            }
            else if (myContact.getEmailAddress() != null && !myContact.getEmailAddress().equals(""))
            {
                myContact.setPhone(false);
                myContact.setPrimaryIndEmail(myForm.getPrimaryIndEmail());
                if (myForm.getContactList() == null)
                    myForm.setContactList(new ArrayList());
                myForm.getContactList().add(myContact);
            }
        }
        ActionForward forward = aMapping.findForward(UIConstants.ADD_SUCCESS);
        myForm.setContactList(UIJuvenileHelper.sortMemberContactList(myForm.getContactList()));
        myForm.clearContact();
        myForm.setPrimaryInd("");
        myForm.setPrimaryIndEmail("");
        return forward;
    }

    public ActionForward removeContact(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        String myContactPos = myForm.getSelectedValue();
        if (myContactPos != null && !(myContactPos.equals("")))
        {
            if (myForm.getContactList() != null & myForm.getContactList().size() > 0)
                ((List) myForm.getContactList()).remove((Integer.valueOf(myContactPos)).intValue());
        }
        ActionForward forward = aMapping.findForward(UIConstants.ADD_SUCCESS);
        myForm.clearContact();
        return forward;
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

    public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {

        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        // Sending PD Request Event
        SaveFamilyMemberAdditionalInfoEvent event = (SaveFamilyMemberAdditionalInfoEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.SAVEFAMILYMEMBERADDITIONALINFO);
        event.setMemberId(myForm.getMemberNumber());
        Collection myContacts = myForm.getContactList();
        SaveFamilyMemberContactEvent saveContactEvent;
        MemberContact mySaveContact;
        
        if (myContacts != null && myContacts.size() > 0)
        {
            Iterator iter = myContacts.iterator();
            //changes for Defect:JIMS200077386 starts.
            int mostRecentRecordIndex=0;
            while (iter.hasNext())
            {
            	mostRecentRecordIndex++;
                mySaveContact = (MemberContact) iter.next();
                if(mostRecentRecordIndex > 1 && (mySaveContact.getMemberContactId() == null || mySaveContact.getMemberContactId().equals("")) && mySaveContact.getPrimaryInd().equalsIgnoreCase(UIConstants.YES_FULL_TEXT)){
                	mySaveContact.setPrimaryInd("Primary");
                }
                //US 181863
                if(mostRecentRecordIndex > 1 && (mySaveContact.getMemberContactId() == null || mySaveContact.getMemberContactId().equals("")) && mySaveContact.getPrimaryIndEmail().equalsIgnoreCase(UIConstants.YES_FULL_TEXT)){
                	mySaveContact.setPrimaryIndEmail("Primary");
                }
                
                saveContactEvent = UIJuvenileHelper.getSaveFamilyContactEvent(mySaveContact);
                event.addRequest(saveContactEvent);
                 
            }
            //changes for Defect:JIMS200077386 ends 
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

