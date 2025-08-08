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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyMemberTraitEvent;
import messaging.juvenilecase.reply.FamilyMemberTraitResponseEvent;
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
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;

/**
 * @author jjose
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayManageFamilyMemberTraitsAction extends LookupDispatchAction
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
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        // Sending PD Request Event
        GetFamilyMemberTraitEvent event = (GetFamilyMemberTraitEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERTRAIT);
        event.setFamilyMemberNum(myForm.getMemberNumber());
        dispatch.postEvent(event);

        // Getting PD Response Event
        CompositeResponse response = (CompositeResponse) dispatch.getReply();
        // Perform Error handling
        MessageUtil.processReturnException(response);
        Map dataMap = MessageUtil.groupByTopic(response);
        FamilyMemberTraitResponseEvent responseEvt;
        JuvenileFamilyForm.Trait myTrait;
        ArrayList myNewList = new ArrayList();
        if (dataMap != null)
        {

            Collection traits = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_TRAIT_TOPIC);
            if (traits != null && traits.size() > 0)
            {
                Iterator iter = traits.iterator();
                while (iter.hasNext())
                {
                    responseEvt = (FamilyMemberTraitResponseEvent) iter.next();
                    if (responseEvt != null)
                    {
                        myTrait = UIJuvenileHelper.getFamilyMemberFormTraitFROMTraitInfoRespEvt(responseEvt, true);
                        if (myTrait != null)
                            myNewList.add(myTrait);
                    }
                }
            }
        }

        myForm.setTraitList(UIJuvenileHelper.sortTraitsList(myNewList));

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

