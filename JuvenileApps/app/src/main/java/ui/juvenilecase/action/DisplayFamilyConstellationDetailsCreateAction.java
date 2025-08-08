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

/**
 * @author jjose
 *  
 */
public class DisplayFamilyConstellationDetailsCreateAction extends LookupDispatchAction
{
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.createNewConstellation", "createConstellation");
        keyMap.put("button.next", "next");
        keyMap.put("button.removeSelectedMembers", "removeSelectedMembers");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        keyMap.put("button.updateGuardians", "updateGuardians");
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
        JuvenileFamilyForm myFamForm = (JuvenileFamilyForm) aForm;
        JuvenileFamilyForm.Constellation myConstellation = myFamForm.getCurrentConstellation();
        myConstellation.setGuardiansList(new ArrayList());
        Collection members = myConstellation.getMemberList();
        Iterator iter;
        iter = members.iterator();
        JuvenileFamilyForm.MemberList myMember;
        
        // Reset all existing family member information that shoudl not exist
        boolean oneMemberSelected = false;
        boolean setRelationship = true;
        boolean setInvolvmentLevel = true;
        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
        while (iter.hasNext())
        {
            myMember = (JuvenileFamilyForm.MemberList) iter.next();
            if (!(myMember.isDelete()))
            {
                forward = aMapping.findForward(UIConstants.NEXT);
                oneMemberSelected = true;
                if (myMember.getRelationshipToJuvId() == null || myMember.getRelationshipToJuvId().equals(""))
                    setRelationship = false;
                if (myMember.getInvolvementLevelId() == null || myMember.getInvolvementLevelId().equals(""))
                    setInvolvmentLevel = false;
            }
        }
        if (!oneMemberSelected)
        {
            forward = aMapping.findForward("createFailure");
            sendToErrorPage(aRequest, "error.needOneFamilyMember");
        }
        if (!setRelationship)
        {
            forward = aMapping.findForward("createFailure");
            sendToErrorPage(aRequest, "error.mustHaveRelationToJuv");
        }
        if (!setInvolvmentLevel)
        {
            forward = aMapping.findForward("createFailure");
            sendToErrorPage(aRequest, "error.mustHaveInvolvementLevel");
        }
        return forward;
    }

    public ActionForward updateGuardians(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileFamilyForm myFamForm = (JuvenileFamilyForm) aForm;
        //String constellationNum=myFamForm.getCurrentConstellation().getFamilyNumber();
        //String juvenileNum=myFamForm.getJuvenileNumber();
        myFamForm.setCurrentConstellation(myFamForm.getCurrentActiveConstellation());
        processMembersForCreate(myFamForm, true);
        myFamForm.setPrimayContactMemberNumber("");
        Collection members = myFamForm.getCurrentConstellation().getMemberList();
        //save family number before clearing, in case user press back button
        myFamForm.setCurrentFamilyNumber(myFamForm.getCurrentConstellation().getFamilyNumber());
        myFamForm.clear();
        myFamForm.setCurrentConstellation(new JuvenileFamilyForm.Constellation());
        myFamForm.getCurrentConstellation().setMemberList(members);
        // Sending PD Request Event
        ActionForward forward = aMapping.findForward(UIConstants.NEXT);
        return forward;
    }

    public ActionForward createConstellation(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileFamilyForm myFamForm = (JuvenileFamilyForm) aForm;
        //String constellationNum=myFamForm.getCurrentConstellation().getFamilyNumber();
        //String juvenileNum=myFamForm.getJuvenileNumber();
        myFamForm.setCurrentConstellation(myFamForm.getCurrentActiveConstellation());
        processMembersForCreate(myFamForm, false);
        Collection members = myFamForm.getCurrentConstellation().getMemberList();
        myFamForm.setCurrentFamilyNumber(myFamForm.getCurrentConstellation().getFamilyNumber());
        myFamForm.clear();
        myFamForm.setCurrentConstellation(new JuvenileFamilyForm.Constellation());
        myFamForm.getCurrentConstellation().setMemberList(members);
        // Sending PD Request Event
        myFamForm.setAction(UIConstants.CREATE);
        ActionForward forward = aMapping.findForward(UIConstants.CREATE_SUCCESS);
        return forward;
    }

    private void processMembersForCreate(JuvenileFamilyForm myFamForm, boolean isUpdateGuard)
    {
        Collection members = myFamForm.getCurrentConstellation().getMemberList();
        Iterator iter;
        iter = members.iterator();
        JuvenileFamilyForm.MemberList myMember;
        // Reset all existing family member information that shoudl not exist
        while (iter.hasNext())
        {
            myMember = (JuvenileFamilyForm.MemberList) iter.next();
            if (myMember.isDeceased())
            {
                iter.remove();
            }
            else
            {
                myMember.setFamilyConstellationMemberNum("");
                myMember.setGuardian(false);
                myMember.setPrimaryContact(false);
                if (!isUpdateGuard)
                {
                    myMember.setInHomeStatusAsStr("");
                    myMember.setInvolvementLevelId("");
                    myMember.setParentalRightsTerminatedAsStr("");
                    myMember.setDetentionHearingAsStr(""); 
                    myMember.setPrimaryCareGiverAsStr("");
                    myMember.setDetentionVisitationAsStr(""); 
                    myMember.setPrimaryContactAsStr("");
                }
            }

        }
    }

    public ActionForward removeSelectedMembers(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileFamilyForm myFamForm = (JuvenileFamilyForm) aForm;
        JuvenileFamilyForm.Constellation currentConstellation = myFamForm.getCurrentConstellation();
        Collection members = currentConstellation.getMemberList();
        Iterator iter = members.iterator();
        JuvenileFamilyForm.MemberList myMember;
        while (iter.hasNext())
        {
            myMember = (JuvenileFamilyForm.MemberList) iter.next();
            if ((myMember.getMemberNumber().equalsIgnoreCase(myFamForm.getSelectedValue())))
            {
                iter.remove();
            }
        }
        ActionForward forward = aMapping.findForward(UIConstants.CREATE_SUCCESS);
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
