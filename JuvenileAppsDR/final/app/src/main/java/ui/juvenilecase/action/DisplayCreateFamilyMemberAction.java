/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.family.UpdateFamilyMemberMatchesEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileFamilyMemberViewResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.common.Name;
import ui.common.SocialSecurity;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileMemberSearchForm;
import ui.juvenilecase.form.JuvenileProfileForm;

/**
 * @author jjose
 * 
 */
public class DisplayCreateFamilyMemberAction extends LookupDispatchAction
{

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.addNewMember", "addNewMember");
        keyMap.put("button.update", "updateMember");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        keyMap.put("button.select", "selectMember");
        return keyMap;
    }

    public ActionForward selectMember(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        JuvenileFamilyForm myFamForm = UIJuvenileHelper.getFamilyForm(aRequest);
        JuvenileMemberSearchForm mySearchForm = UIJuvenileHelper.getJuvenileMemberSeachForm(aRequest, true);
        mySearchForm.setPopUp(false);
        String memberNum = myForm.getSelectedValue();
        myForm.setSelectedValue("");//check for the FORMs match type here

        if (memberNum != null && !(memberNum.equals(UIConstants.EMPTY_STRING)))
        {
            // if in the create or update constellation flow path use the selected member because
            // you could
            // have only been adding not updating a member
            if (myFamForm.getAction() != null && !(myFamForm.getAction().equals(UIConstants.EMPTY_STRING)))
                return addToConstellation(aMapping, aForm, aRequest, aResponse, memberNum);
            else
            { // not in create or update constellation path
                // Save original member info everything but name and SSN#
                FamilyMemberDetailResponseEvent returnEvent = saveMember(myForm, aRequest);
                Name origName = new Name(myForm.getName().getFirstName(), myForm.getName().getMiddleName(), myForm
                        .getName().getLastName());
                SocialSecurity origSSN = new SocialSecurity(myForm.getSsn().getSSN1(), myForm.getSsn().getSSN2(),
                        myForm.getSsn().getSSN3());
                String origMemberId = myForm.getMemberNumber();
                if (returnEvent == null)
                {
                    sendToErrorPage(aRequest, "error.common");
                    return aMapping.findForward(UIConstants.FAILURE);
                }
                // Set flags on both members, mark both as suspicious
                flagBothMembers(memberNum, myForm.getMemberNumber(), UIUtil.getCurrentUserID());
                refreshCurrentConstMembers(myFamForm);
                notifyJPOsOfDuplicateMembers(origName, origSSN, origMemberId, memberNum);
                // if original member that was being edited is in the constellation -- can assume
                // yes
                if (isMemberInConst(myFamForm.getCurrentConstellation(), myForm.getMemberNumber()))
                {
                    //	set family action to update constellation
                    myFamForm.setAction(UIConstants.UPDATE);
                    // add member to constellation
                    return addToConstellation(aMapping, aForm, aRequest, aResponse, memberNum);
                }
                else
                {
                    //	if original member not in constellation do nothing return as normal.
                }

            }

        }
        sendToErrorPage(aRequest, "error.family.samePerson");

        return aMapping.findForward(UIConstants.SUCCESS);
    }

    private void notifyJPOsOfDuplicateMembers(Name aOrigName, SocialSecurity aOrigSSN, String aOrigId, String aMatchId)
    {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        GetFamilyMemberDetailsEvent event = (GetFamilyMemberDetailsEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
        event.setMemberNum(aMatchId);
        dispatch.postEvent(event);

        // Getting PD Response Event
        CompositeResponse response = (CompositeResponse) dispatch.getReply();
        // Perform Error handling
        MessageUtil.processReturnException(response);

        Map dataMap = MessageUtil.groupByTopic(response);

        FamilyMemberDetailResponseEvent resp = null;
        if (dataMap != null && response != null)
        {
            Object refObj = MessageUtil.filterComposite(response, FamilyMemberDetailResponseEvent.class);
            if (refObj != null)
            {

                resp = (FamilyMemberDetailResponseEvent) refObj;
                if (resp != null)
                {
                    Name matchMemberName = new Name(resp.getFirstName(), resp.getMiddleName(), resp.getLastName());
                    SocialSecurity matchSSN = new SocialSecurity(resp.getSsn());
                    UIJuvenileHelper.sendOutJPONotificationForDuplicateMemberAlert(matchMemberName, matchSSN, aMatchId,
                            aOrigName, aOrigSSN, aOrigId);
                }
            }
        }
    }

    private void flagBothMembers(String memberA, String memberB, String foundByUserId)
    {
        if (memberA != null && memberB != null && !(memberA.equalsIgnoreCase(memberB)))
        {
            UpdateFamilyMemberMatchesEvent event = new UpdateFamilyMemberMatchesEvent();
            event.setMemberA(memberA);
            event.setMemberB(memberB);
            event.setCreateUser(foundByUserId);
            event.setStatus("UNRESOLVED");
            event.setMatchType("SSN");  //Added for US 181437
            event.setNotes("DisplayCreateFamilyMemAction System Generated Notes: Match found when user " + foundByUserId
                    + " was trying to update member number " + memberB);
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
            try
            {
                dispatch.postEvent(event);
            }
            catch (Exception e)
            {
                // ignore any exception as this is not visible to the user
            }
            // Getting PD Response Event
            //CompositeResponse response = (CompositeResponse) dispatch.getReply();
        }
    }

    private boolean isMemberInConst(JuvenileFamilyForm.Constellation aConstellation, String aMemberNumber)
    {
        if (aConstellation == null || aConstellation.getMemberList() == null
                || aConstellation.getMemberList().size() <= 0)
            return false;
        Iterator iter = aConstellation.getMemberList().iterator();
        JuvenileFamilyForm.MemberList memberObj = null;
        while (iter.hasNext())
        {
            memberObj = (JuvenileFamilyForm.MemberList) iter.next();
            if (memberObj.getMemberNumber().equalsIgnoreCase(aMemberNumber))
            {
                return true;
            }
        }
        return false;
    }

    private void refreshCurrentConstMembers(JuvenileFamilyForm myFamForm)
    {

        // reload all family members to get latest info
        UIJuvenileFamilyHelper.getCurrentConstFamilyMembers(myFamForm);

    }

    private FamilyMemberDetailResponseEvent saveMember(JuvenileMemberForm myForm, HttpServletRequest aRequest)
    {
        myForm.getName().setFirstName(myForm.getOrigName().getFirstName());
        myForm.getName().setMiddleName(myForm.getOrigName().getMiddleName());
        myForm.getName().setLastName(myForm.getOrigName().getLastName());
        myForm.getSsn().setSSN(myForm.getOrigSSN().getSSN());
        FamilyMemberDetailResponseEvent returnEvent = UIJuvenileFamilyHelper.saveMember(myForm, aRequest, null);
        return returnEvent;
    }

    public ActionForward addToConstellation(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse, String aMemberNum)
    {
        JuvenileMemberSearchForm mySearchForm = UIJuvenileHelper.getJuvenileMemberSeachForm(aRequest, false);
        JuvenileFamilyForm myFamForm = UIJuvenileHelper.getFamilyForm(aRequest);
        JuvenileFamilyForm.Constellation myConstellation = myFamForm.getCurrentConstellation();
        ArrayList membersList = (ArrayList) myConstellation.getMemberList();
        Collection selectedMembers = mySearchForm.getMemberSearchResults();
        JuvenileFamilyForm.MemberList myFamilyMember = null;
        JuvenileFamilyForm.MemberList reloadedFamilyMember = null;
        JuvenileMemberSearchForm.MemberSearchResult myMemberSearchResult = null;
        // Retrieve Member add them to constellation;
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        // Sending PD Request Event
        GetFamilyMemberDetailsEvent event = (GetFamilyMemberDetailsEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
        event.setMemberNum(aMemberNum);
        dispatch.postEvent(event);

        // Getting PD Response Event
        CompositeResponse response = (CompositeResponse) dispatch.getReply();
        // Perform Error handling
        MessageUtil.processReturnException(response);

        Map dataMap = MessageUtil.groupByTopic(response);

        FamilyMemberDetailResponseEvent resp = null;
        if (dataMap != null && response != null)
        {
            Object refObj = MessageUtil.filterComposite(response, FamilyMemberDetailResponseEvent.class);
            if (refObj != null)
            {

                resp = (FamilyMemberDetailResponseEvent) refObj;
            }
        }

        if (selectedMembers != null && selectedMembers.size() > 0)
        {
            Iterator iter = selectedMembers.iterator();
            while (iter.hasNext())
            {
                myMemberSearchResult = (JuvenileMemberSearchForm.MemberSearchResult) iter.next();
                boolean found = false;
                if (myMemberSearchResult.getMemberNumber().equalsIgnoreCase(aMemberNum))
                {
                    if (membersList != null && membersList.size() > 0)
                    {
                        Iterator memberIter = membersList.iterator();
                        while (memberIter.hasNext() && !found)
                        {
                            myFamilyMember = (JuvenileFamilyForm.MemberList) memberIter.next();
                            if (myFamilyMember.getMemberNumber().equals(myMemberSearchResult.getMemberNumber()))
                            {
                                if (resp != null)
                                {
                                    myFamilyMember.setSuspiciousMember(resp.getSuspiciousMember());
                                }
                                found = true;
                            }
                        }
                    }
                    if (!found)
                    {
                        myFamilyMember = new JuvenileFamilyForm.MemberList();
                        myFamilyMember.setMemberNumber(myMemberSearchResult.getMemberNumber());
                        myFamilyMember.setMemberName(myMemberSearchResult.getName());
                        myFamilyMember.setGuardian(false);
                        myFamilyMember.setRelationshipToJuvId(myMemberSearchResult.getRelationshipId());
                        myFamilyMember.setDeceased(myMemberSearchResult.isDeceased());
                        if (resp != null)
                        {
                            myFamilyMember.setSuspiciousMember(resp.getSuspiciousMember());
                        }
                        membersList.add(myFamilyMember);
                    }
                }
            }
        }
        return aMapping.findForward("addSuccess");
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.CANCEL);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }

    public ActionForward addNewMember(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        JuvenileProfileForm myJuvForm = (JuvenileProfileForm) UIJuvenileHelper.getHeaderForm(aRequest);
        JuvenileMemberSearchForm mySearchForm = UIJuvenileHelper.getJuvenileMemberSeachForm(aRequest, true);
        mySearchForm.setPopUp(false);
        myForm.clearAll();
        myForm.setSearchJuvenileNumber(UIConstants.EMPTY_STRING);
        myForm.setAction("createMember");
        List juvFamMembers = UIJuvenileFamilyHelper.getAllJuvenilesFamilyMembers(myJuvForm.getJuvenileNum());
        myForm.setMaritalRelWithList(juvFamMembers);
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    public ActionForward updateMember(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        JuvenileMemberSearchForm mySearchForm = UIJuvenileHelper.getJuvenileMemberSeachForm(aRequest, true);
        JuvenileProfileForm myJuvForm = (JuvenileProfileForm) UIJuvenileHelper.getHeaderForm(aRequest);

        mySearchForm.setPopUp(false);
        myForm.setMaritalStatus(UIConstants.EMPTY_STRING);
        myForm.setMaritalStatusId(UIConstants.EMPTY_STRING);
        myForm.setMarriageDate(UIConstants.EMPTY_STRING);
        myForm.setDivorceDate(UIConstants.EMPTY_STRING);
        myForm.setNumOfChildren(UIConstants.EMPTY_STRING);
        myForm.setRelatedFamMemId(UIConstants.EMPTY_STRING);
//        if (myForm.getMaritalList() != null && myForm.getMaritalList().size() > 0)
//        {
//            JuvenileMemberForm.MaritalList myLastMarriage = (JuvenileMemberForm.MaritalList) ((ArrayList) myForm
//                    .getMaritalList()).get(0);
//            myForm.setMaritalStatusId(myLastMarriage.getMaritalStatusId());
//            myForm.setMarriageDate(myLastMarriage.getMarriageDate());
//            myForm.setDivorceDate(myLastMarriage.getDivorceDate());
//            myForm.setNumOfChildren(myLastMarriage.getNumOfChildren());
//            myForm.setRelatedFamMemId(myLastMarriage.getRelatedFamMemId());
//        }
        myForm.setMaritalRelWithList(UIJuvenileFamilyHelper.getAllJuvenilesFamilyMembers(myJuvForm.getJuvenileNum()));
//        ;

        Iterator iter = myForm.getMaritalRelWithList().iterator();
        	while(iter.hasNext()) {
        		JuvenileFamilyMemberViewResponseEvent famMemberResp = (JuvenileFamilyMemberViewResponseEvent) iter.next();
        		String relatedMemId = famMemberResp.getMemberNum();
        		String memNum = myForm.getMemberNumber();
        		if
        		(relatedMemId.equals(memNum))
        			iter.remove();
        	}
        myForm.setAction("updateMember");
        return aMapping.findForward(UIConstants.SUCCESS);
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
