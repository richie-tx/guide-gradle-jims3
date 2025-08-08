package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.SaveFamilyMemberAdditionalInfoEvent;
import messaging.family.SaveFamilyMemberTraitEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
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
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.security.SecurityUIHelper;

public class SubmitFamilyMemberTraitsAction extends LookupDispatchAction
{

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.remove", "removeTrait");
        keyMap.put("button.addToList", "addTrait");
        keyMap.put("button.update", "update");
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

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;
    }

    public ActionForward removeTrait(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        String myTraitPos = myForm.getSelectedValue();
        if (myTraitPos != null && !(myTraitPos.equals("")))
        {
            if (myForm.getTraitList() != null & myForm.getTraitList().size() > 0)
                ((List) myForm.getTraitList()).remove((Integer.valueOf(myTraitPos)).intValue());
        }
        ActionForward forward = aMapping.findForward(UIConstants.ADD_SUCCESS);
        myForm.clearTrait();
        return forward;
    }

    public ActionForward addTrait(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileMemberForm myForm = (JuvenileMemberForm) aForm;
        myForm.setAction(UIConstants.UPDATE);
        JuvenileFamilyForm.Trait myTrait = myForm.getCurrentTrait();
        if (myTrait != null)
        {
/*        	
    		String comments = myTrait.getTraitComments();
    		if (!comments.equals("") && comments != null) {
    			IUserInfo user = SecurityUIHelper.getUser();
    			Name userName = new Name(user.getFirstName(),"",user.getLastName());
    			myTrait.setTraitComments(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
    		}
    		*/
        	if (myTrait.getTraitDescId() != null && !(myTrait.getTraitDescId().equals("")))
            {
                if (myForm.getTraitList() == null)
                    myForm.setTraitList(new ArrayList());
                myForm.getTraitList().add(myTrait);
            }
        }
        ActionForward forward = aMapping.findForward(UIConstants.ADD_SUCCESS);
        List sortedList = UIJuvenileHelper.sortTraitsList(myForm.getTraitList());
        myForm.setTraitList(sortedList);
        myForm.clearTrait();
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
        Collection myTraits = myForm.getTraitList();
        SaveFamilyMemberTraitEvent saveTraitEvent;
        JuvenileFamilyForm.Trait mySaveTrait;
        if (myTraits != null && myTraits.size() > 0)
        {
            Iterator iter = myTraits.iterator();
            while (iter.hasNext())
            {
                mySaveTrait = (JuvenileFamilyForm.Trait) iter.next();
                if (mySaveTrait.getTraitId() == null || mySaveTrait.getTraitId().equals(""))
                {
                    saveTraitEvent = UIJuvenileHelper.getSaveFamilyMemberTraitEvent(mySaveTrait);
                    event.addRequest(saveTraitEvent);
                }
            }

        }
        dispatch.postEvent(event);
        // Getting PD Response Event
        CompositeResponse response = (CompositeResponse) dispatch.getReply();
        // Perform Error handling
        MessageUtil.processReturnException(response);
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

