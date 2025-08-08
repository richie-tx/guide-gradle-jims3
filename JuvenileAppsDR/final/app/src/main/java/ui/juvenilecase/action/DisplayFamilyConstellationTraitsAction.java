package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.GetFamilyTraitsEvent;
import messaging.family.SaveFamilyConstellationEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.IUserInfo;
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
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.security.SecurityUIHelper;

/**
 * @author jjose
 *  
 */
public class DisplayFamilyConstellationTraitsAction extends LookupDispatchAction
{

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.go", "go");
        keyMap.put("button.next", "next");
        keyMap.put("button.addToList", "addDynamic");
        keyMap.put("button.remove", "removeDynamic");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        keyMap.put("button.saveAndContinue", "saveAndContinue");
        return keyMap;
    }

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {

        ActionForward forward = aMapping.findForward("createConstellationSuccess");
        return forward;
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward go(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileFamilyForm myFamForm = (JuvenileFamilyForm) aForm;

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        GetFamilyTraitsEvent event = (GetFamilyTraitsEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYTRAITS);
        event.setFamilyNum(myFamForm.getCurrentConstellation().getFamilyNumber());
        dispatch.postEvent(event);

        // Getting PD Response Event
        CompositeResponse response = (CompositeResponse) dispatch.getReply();
        // Perform Error handling
        MessageUtil.processReturnException(response);
        Map dataMap = MessageUtil.groupByTopic(response);
        if (dataMap != null)
        {
            Collection familiesTraits = (Collection) dataMap
                    .get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_TRAIT_LIST_TOPIC);
            UIJuvenileHelper.setJuvFamilyFormCurConstFROMTraitListRespEvt(myFamForm.getCurrentConstellation(),
                    familiesTraits);

        }
        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
        return forward;
    }

    public ActionForward removeDynamic(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileFamilyForm myFamForm = (JuvenileFamilyForm) aForm;
        JuvenileFamilyForm.Constellation currentConstellation = myFamForm.getCurrentConstellation();
        Collection traits = currentConstellation.getTraitList();
        if (traits != null && traits.size() > 0 && myFamForm.getSelectedValue() != null
                && !(myFamForm.getSelectedValue().equals("")))
        {
            ArrayList traitList = (ArrayList) traits;
            traitList.remove(Integer.parseInt(myFamForm.getSelectedValue()));
        }
        myFamForm.setSelectedValue("");
        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
        return forward;
    }

    public ActionForward addDynamic(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileFamilyForm myFamForm = (JuvenileFamilyForm) aForm;
        JuvenileFamilyForm.Constellation currentConstellation = myFamForm.getCurrentConstellation();
        Collection traits = currentConstellation.getTraitList();
        if(myFamForm.getCurrentTrait() != null) {
/*        	
			String comments = myFamForm.getCurrentTrait().getTraitComments();
			if (!comments.equals("") && comments != null) {
				IUserInfo user = SecurityUIHelper.getUser();
				Name userName = new Name(user.getFirstName(),"",user.getLastName());
				myFamForm.getCurrentTrait().setTraitComments(comments + " [" + DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) + " - " + userName.getFormattedName() + "]");
			}
			*/
        }
        traits.add(myFamForm.getCurrentTrait());
        myFamForm.setCurrentTrait(new JuvenileFamilyForm.Trait());
        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
        currentConstellation.setTraitList(UIJuvenileHelper.sortTraitsList((ArrayList) currentConstellation
                .getTraitList()));
        return forward;
    }

    public ActionForward saveAndContinue(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileFamilyForm myFamForm = (JuvenileFamilyForm) aForm;
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        // Get to be deleted items
        JuvenileFamilyForm.Constellation currentConstellation = myFamForm.getCurrentConstellation();
        Collection traits = currentConstellation.getTraitList();
        Iterator iter = traits.iterator();
        JuvenileFamilyForm.Trait myTrait;
        //				Sending PD Request Event
        SaveFamilyConstellationEvent event = (SaveFamilyConstellationEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.SAVEFAMILYCONSTELLATION);
        event.setJuvNum(myFamForm.getJuvenileNumber());
        event.setConstellationNum(currentConstellation.getFamilyNumber());
        while (iter.hasNext())
        {
            myTrait = (JuvenileFamilyForm.Trait) iter.next();
            if (myTrait.getTraitId() == null || myTrait.getTraitId().trim().equals(""))
                event.addRequest(UIJuvenileHelper.getSaveFamilyConstellationTraitEvent(myTrait));
        }
        MessageUtil.postRequest(event);
        ActionForward forward = aMapping.findForward("display");
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

