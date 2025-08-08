package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.JuvenileFamilyForm;

public class SubmitFamilyGuardianSelectionAction extends LookupDispatchAction
{

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
        return keyMap;
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileFamilyForm myFamForm = (JuvenileFamilyForm) aForm;
        JuvenileFamilyForm.Constellation myConstellation = myFamForm.getCurrentConstellation();
        myConstellation.setGuardiansList(new ArrayList());
        Collection members = myConstellation.getMemberList();
        Iterator iter;
        iter = members.iterator();
        JuvenileFamilyForm.MemberList myMember;
//        boolean atLeastOneGuardian = false;
//        int guardianCount = 0;
        // Reset all existing family member information that shoudl not exist
        while (iter.hasNext())
        {
            myMember = (JuvenileFamilyForm.MemberList) iter.next();
            if (myMember.isGuardian())
            {
                JuvenileFamilyForm.Guardian myGuardian = new JuvenileFamilyForm.Guardian();
                myGuardian.setDeceased(myMember.getDeceasedYesNo());
                myGuardian.setMemberNumber(myMember.getMemberNumber());
                myGuardian.setName(myMember.getMemberName());
                myGuardian.setRelationshipToJuv(myMember.getRelationshipToJuv());
                myConstellation.getGuardiansList().add(myGuardian);
//                guardianCount++;
//                atLeastOneGuardian = true;
            }
            // set primary contact on members list
            /*if (myFamForm.getPrimayContactMemberNumber().equals(myMember.getMemberNumber() ) ) {
            	myMember.setPrimaryContact(true);
            } else {
            	myMember.setPrimaryContact(false);
            }*/
        }
//        if (atLeastOneGuardian == false || guardianCount > 2)
//        {
//            sendToErrorPage(aRequest, "error.needAtLeastOneGuardian");
//            return aMapping.findForward(UIConstants.FAILURE);
//        }
        myFamForm.setCurrentGuardian((JuvenileFamilyForm.Guardian) (((ArrayList) myConstellation.getGuardiansList())
                .get(0)));
        myFamForm.setSecondaryAction("summary");
        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
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
