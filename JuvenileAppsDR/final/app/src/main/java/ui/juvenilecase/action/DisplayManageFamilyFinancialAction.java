package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyMemberEmploymentInfoEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberEmploymentInfoResponseEvent;
import messaging.family.GetFamilyConstellationGuardianEvent;
import messaging.family.GetFamilyConstellationGuardianFinancialEvent;
import messaging.juvenilecase.reply.FamilyConstellationGuardianResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberFinancialResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
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

import ui.common.Name;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileProfileForm;

public class DisplayManageFamilyFinancialAction extends LookupDispatchAction
{

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.link", "link");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        keyMap.put("button.go", "displayFinancialDetails");
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

    public ActionForward displayFinancialDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        // Sending PD Request Event
        GetFamilyConstellationGuardianFinancialEvent event = new GetFamilyConstellationGuardianFinancialEvent();

        event.setFinancialId(UIUtil.getIntFromString(myForm.getSelectedValue()));
        dispatch.postEvent(event);

        // Getting PD Response Event
        CompositeResponse response = (CompositeResponse) dispatch.getReply();
        // Perform Error handling
        MessageUtil.processReturnException(response);
        Map dataMap = MessageUtil.groupByTopic(response);
        FamilyConstellationMemberFinancialResponseEvent responseEvt;

        JuvenileFamilyForm.Guardian myGuardian;
        myForm.setCurrentGuardian(new JuvenileFamilyForm.Guardian());
        myForm.getCurrentGuardian().setAnnualNetIncome("0.00");
        if (dataMap != null)
        {
            Collection guardianInfo = (Collection) dataMap
                    .get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_GUARDIAN_FINANCIAL_TOPIC);
            if (guardianInfo != null && guardianInfo.size() > 0)
            {
                Iterator iter = guardianInfo.iterator();
                if (iter.hasNext())
                {
                    responseEvt = (FamilyConstellationMemberFinancialResponseEvent) iter.next();
                    if (responseEvt != null)
                    {
                        myGuardian = UIJuvenileHelper
                                .getFamilyFormGuardianFROMFamilyConstellationFinancialRespEvt(responseEvt);
                        if (myGuardian != null)
                        {
                            myGuardian.setMemberNumber(myForm.getMemberNumber());
                            myGuardian.setName(myForm.getName());
                            myGuardian.setDeceased(myForm.getDeceasedYesNo());
                            
                            String dateString = DateUtil.dateToString(responseEvt.getEntryDate(),
                                    UIConstants.DATE_FMT_1);
                            myGuardian.setEntryDate(dateString);
                            myGuardian.setRelationshipToJuv(myForm.getRelationToJuv());
                            //added for Bug #33954
                            //get the constellation number from JuvenileFamilyForm
                            JuvenileFamilyForm famForm = UIJuvenileHelper.getFamilyForm(aRequest);
                            myGuardian.setNumberInFamily(famForm.getCurrentActiveConstellation().getFamilyNumber());
                            myForm.setCurrentGuardian(myGuardian);
                            if (myForm.getEmploymentInfoList() != null && myForm.getEmploymentInfoList().size() > 0)
                            {
                                List employmentInfo = (List) myForm.getEmploymentInfoList();
                                JuvenileMemberForm.MemberEmployment memberEmp = (JuvenileMemberForm.MemberEmployment) employmentInfo
                                        .get(0);

                                myGuardian.setAnnualNetIncome(memberEmp.getAnnualNetIncome());
                            }
                        }
                    }
                }
            }
        }
        ActionForward forward = aMapping.findForward("details");
        return forward;
    }

    public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        myForm.setSelectedConsRelationId("");
        myForm.setSelectedEntryDate("");
        myForm.setCurrentGuardian(null);
        myForm.setGuardianList(new ArrayList());
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        GetFamilyMemberEmploymentInfoEvent event1 = (GetFamilyMemberEmploymentInfoEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBEREMPLOYMENTINFO);
        event1.setMemberNum(myForm.getMemberNumber());
        dispatch.postEvent(event1);

        // Getting PD Response Event
        CompositeResponse response1 = (CompositeResponse) dispatch.getReply();
        // Perform Error handling
        MessageUtil.processReturnException(response1);
        Map dataMap1 = MessageUtil.groupByTopic(response1);
        FamilyMemberEmploymentInfoResponseEvent responseEvt1;
        JuvenileMemberForm.MemberEmployment myEmployment;
        ArrayList myNewList1 = new ArrayList();
        if (dataMap1 != null)
        {

            Collection employments = (Collection) dataMap1
                    .get(PDJuvenileFamilyConstants.FAMILY_MEMBER_EMPLOYMENT_TOPIC);
            if (employments != null && employments.size() > 0)
            {
                Iterator iter = employments.iterator();
                while (iter.hasNext())
                {
                    responseEvt1 = (FamilyMemberEmploymentInfoResponseEvent) iter.next();
                    if (responseEvt1 != null)
                    {
                        myEmployment = UIJuvenileHelper
                                .getJuvMemberFormMemberEmploymentInfoFROMEmploymentInfoRespEvt(responseEvt1);
                        if (myEmployment != null)
                            myNewList1.add(myEmployment);
                    }
                }
            }
        }

        myForm.setEmploymentInfoList(UIJuvenileHelper.sortMemberEmploymentList(myNewList1));

        // Sending PD Request Event
        GetFamilyConstellationGuardianEvent event = (GetFamilyConstellationGuardianEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONGUARDIAN);
        event.setMemberNum(Integer.parseInt(myForm.getMemberNumber()));
        dispatch.postEvent(event);

        // Getting PD Response Event
        CompositeResponse response = (CompositeResponse) dispatch.getReply();
        // Perform Error handling
        MessageUtil.processReturnException(response);
        Map dataMap = MessageUtil.groupByTopic(response);
        FamilyConstellationGuardianResponseEvent responseEvt;
        JuvenileFamilyForm.Guardian myGuardian;
        ArrayList myNewList = new ArrayList();
        if (dataMap != null)
        {

            Collection guardianInfo = (Collection) dataMap
                    .get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_GUARDIAN_TOPIC);
            if (guardianInfo != null && guardianInfo.size() > 0)
            {
                Iterator iter = guardianInfo.iterator();
                JuvenileProfileForm myJuvForm = (JuvenileProfileForm) UIJuvenileHelper.getHeaderForm(aRequest);
                HashMap myJuvNameMap = new HashMap();
                if (myJuvForm != null)
                {
                    if (myJuvForm.getJuvenileNum() != null && !myJuvForm.getJuvenileNum().equals(""))
                    {
                        GetJuvenileProfileMainEvent getJuvProfileEvent = new GetJuvenileProfileMainEvent();
                        getJuvProfileEvent.setJuvenileNum(myJuvForm.getJuvenileNum());
                        CompositeResponse response2 = MessageUtil.postRequest(getJuvProfileEvent);
                        JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil
                                .filterComposite(response2, JuvenileProfileDetailResponseEvent.class);
                        if (juvProfileRE != null)
                        {
                            Name myName = new Name("", "", "");
                            myName.setLastName(juvProfileRE.getLastName());
                            myName.setFirstName(juvProfileRE.getFirstName());
                            myName.setMiddleName(juvProfileRE.getMiddleName());
                            myJuvNameMap.put(myJuvForm.getJuvenileNum(), myName);
                        }

                    }
                }
                while (iter.hasNext())
                {
                    responseEvt = (FamilyConstellationGuardianResponseEvent) iter.next();
                    if (responseEvt != null && responseEvt.getJuvenileId().equals(myJuvForm.getJuvenileNum()))
                    {
                        myGuardian = UIJuvenileHelper
                                .getFamilyFormGuardianFROMFamilyConstellationGuardianRespEvt(responseEvt);
                        if (myGuardian != null)
                        {
                            myGuardian.setMemberNumber(myForm.getMemberNumber());
                            myGuardian.setName(myForm.getName());
                            myGuardian.setDeceased(myForm.getDeceasedYesNo());
                            String juvenileNumFromGuard = responseEvt.getJuvenileId();
                            if (juvenileNumFromGuard != null && !myJuvNameMap.containsKey(juvenileNumFromGuard))
                            {
                                if (juvenileNumFromGuard != null && !"".equals(juvenileNumFromGuard))
                                {

                                    GetJuvenileProfileMainEvent getJuvProfileEvent = new GetJuvenileProfileMainEvent();
                                    getJuvProfileEvent.setJuvenileNum(juvenileNumFromGuard);
                                    CompositeResponse response2 = MessageUtil.postRequest(getJuvProfileEvent);
                                    JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil
                                            .filterComposite(response2, JuvenileProfileDetailResponseEvent.class);
                                    if (juvProfileRE != null)
                                    {
                                        Name myName = new Name("", "", "");
                                        myName.setLastName(juvProfileRE.getLastName());
                                        myName.setFirstName(juvProfileRE.getFirstName());
                                        myName.setMiddleName(juvProfileRE.getMiddleName());
                                        myJuvNameMap.put(juvenileNumFromGuard, myName);
                                        myGuardian.setJuvenileName(myName);
                                    }
                                }
                            }
                            else
                            {

                                myGuardian.setJuvenileName((Name) myJuvNameMap.get(juvenileNumFromGuard));
                            }
                            myNewList.add(myGuardian);
                        }
                    }
                }
            }
        }
        myForm.setGuardianList(UIJuvenileHelper.sortGuardianList(myNewList));
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

