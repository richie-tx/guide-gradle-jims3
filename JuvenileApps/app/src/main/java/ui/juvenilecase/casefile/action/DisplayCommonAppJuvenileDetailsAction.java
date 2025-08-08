/*
 * Created on Dec 14, 2005
 *
 */
package ui.juvenilecase.casefile.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetActiveFamilyConstellationEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileAbuseResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.form.CommonAppForm;
import ui.juvenilecase.form.JuvenileAbuseForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenileFamilyForm;

/**
 * @author jjose
 * 
 */
public class DisplayCommonAppJuvenileDetailsAction extends LookupDispatchAction
{
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.CANCEL);
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        return aMapping.findForward(UIConstants.BACK);
    }

    public ActionForward displayJuvenileDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        CommonAppForm myCommonAppForm = (CommonAppForm) aForm;
        JuvenileCasefileForm myCasefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
        
        // TODO RETRIEVE Common App disposition Information
        myCommonAppForm.setAction("");
        myCommonAppForm.setSecondaryAction("");
        myCommonAppForm.setSelectedValue("");
        getJuvenileFamilyDetails(myCommonAppForm, myCasefileForm);
        getJuvenileAbuseDetails(aRequest, myCommonAppForm, myCasefileForm);
        getJuvenileSchoolHistory(myCommonAppForm, myCasefileForm);

        GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) EventFactory
                .getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

        requestEvent.setJuvenileNum(myCasefileForm.getJuvenileNum());

        CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);

        JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent,
                JuvenileProfileDetailResponseEvent.class);

        UIJuvenileHelper.putHeaderForm(aRequest, detail);
        return aMapping.findForward(UIConstants.SUCCESS);
    }

    private void getJuvenileSchoolHistory(CommonAppForm aForm, JuvenileCasefileForm aJuvForm)
    {
        Collection schoolHistories = UIJuvenileHelper.fetchSchoolHistory(aJuvForm.getJuvenileNum());
        ArrayList mySchoolHistories = new ArrayList();
        aForm.setSchoolHistory(mySchoolHistories);
        if (schoolHistories == null || schoolHistories.size() <= 0)
            return;
        Iterator myIter = schoolHistories.iterator();
        while (myIter.hasNext())
        {
            JuvenileSchoolHistoryResponseEvent mySchoolHistoryResp = (JuvenileSchoolHistoryResponseEvent) myIter.next();
            CommonAppForm.SchoolHistory mySchoolHistory = new CommonAppForm.SchoolHistory();
            UIJuvenileCasefileClosingHelper.setCommonAppSchoolHistFROMJuvenileSchoolHistRespEvt(mySchoolHistory,
                    mySchoolHistoryResp);
            mySchoolHistories.add(mySchoolHistory);
        }
        return;
    }

    private void getJuvenileAbuseDetails(HttpServletRequest aRequest, CommonAppForm aForm, JuvenileCasefileForm aJuvForm)
    {
        Collection abuses = UIJuvenileHelper.fetchJuvenileAbuses(aJuvForm.getJuvenileNum());
        ArrayList myAbuses = new ArrayList();
        aForm.setAbuses(myAbuses);
        if (abuses == null || abuses.size() <= 0)
            return;
        JuvenileAbuseForm myJuvAbuseForm = UIJuvenileHelper.getJuvenileAbuseForm(aRequest, true);
        myJuvAbuseForm.setAbuses(abuses);
        Iterator myIter = abuses.iterator();
        while (myIter.hasNext())
        {
            JuvenileAbuseResponseEvent myAbuseReponse = (JuvenileAbuseResponseEvent) myIter.next();
            CommonAppForm.Abuse myAbuse = new CommonAppForm.Abuse();
            UIJuvenileCasefileClosingHelper.setCommonAppAbuseFROMJuvenileAbuseRespEvt(myAbuse, myAbuseReponse);
            myAbuses.add(myAbuse);
        }
        return;
    }

    private void getJuvenileFamilyDetails(CommonAppForm aForm, JuvenileCasefileForm aJuvForm)
    {
        aForm.setCurrentConstellation(null);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        // Sending PD Request Event
        GetActiveFamilyConstellationEvent event = (GetActiveFamilyConstellationEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.GETACTIVEFAMILYCONSTELLATION);
        event.setJuvenileNum(aJuvForm.getJuvenileNum());
        dispatch.postEvent(event);

        // Getting PD Response Event
        CompositeResponse response = (CompositeResponse) dispatch.getReply();
        // Perform Error handling
        MessageUtil.processReturnException(response);
        Map dataMap = MessageUtil.groupByTopic(response);
        if (dataMap != null)
        {
            Collection families = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
            if (families != null && families.size() > 0)
            {
                Iterator myIter = families.iterator();
                while (myIter.hasNext())
                {
                    FamilyConstellationListResponseEvent myFamily = (FamilyConstellationListResponseEvent) myIter.next();
                    if (myFamily.isActive())
                    {
                        JuvenileFamilyForm.Constellation newFamily = new JuvenileFamilyForm.Constellation();
                        newFamily.setFamilyNumber(myFamily.getFamilyNum());
                        newFamily.setActive(myFamily.isActive());
                        aForm.setCurrentConstellation(newFamily);
                        Collection currentFamMembers = (Collection) dataMap
                                .get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
                        UIJuvenileHelper.setJuvFamilyFormFROMMemberListRespEvt(newFamily, currentFamMembers);
                        break;
                    }
                }
            }
        }
    }

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        CommonAppForm myCommonAppForm = (CommonAppForm) aForm;
        // TODO RETRIEVE Common App disposition Information
        myCommonAppForm.setAction("");
        myCommonAppForm.setSecondaryAction("");
        myCommonAppForm.setSelectedValue("");
        return aMapping.findForward(UIConstants.NEXT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map buttonMap = new HashMap();
        buttonMap.put("button.cancel", "cancel");
        buttonMap.put("button.back", "back");
        buttonMap.put("button.link", "displayJuvenileDetails");
        buttonMap.put("button.next", "next");
        buttonMap.put("button.refresh", "displayJuvenileDetails");
        return buttonMap;
    }

}
