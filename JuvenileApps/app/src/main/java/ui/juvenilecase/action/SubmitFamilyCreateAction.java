/*
 * Created on Oct 5, 2005
 *
 */
package ui.juvenilecase.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import messaging.family.SaveFamilyConstellationEvent;
import messaging.juvenilecase.SaveJJSJuvenileFamilyInfoEvent;
import messaging.juvenilecase.reply.FamilyConstealltionResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.UIConstants;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;

/**
 * @author jjose
 * 
 */
public class SubmitFamilyCreateAction extends LookupDispatchAction
{

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.finish", "finish");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        keyMap.put("button.familyConstellationList", "toConstellationList");

        return keyMap;
    }

    public ActionForward toConstellationList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward("toConstellationList");
        return forward;
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;
    }

    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileFamilyForm myFamForm = (JuvenileFamilyForm) aForm;
// should only be true if user clicked browser back button then finish button on page
        if (UIConstants.CONFIRM.equals(myFamForm.getSecondaryAction() ) ) {
            sendToErrorPage(aRequest, "error.generic","Finish button selected on expired page, no updates occurred");
            return aMapping.findForward("toConstellationList");
        }
        JuvenileFamilyForm.Constellation myConstellation = myFamForm.getCurrentConstellation();
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        Collection members = myConstellation.getMemberList();
        JuvenileFamilyForm.MemberList myConstMember;
        String familyMemberNumber = null;
        String juvenileId = null;
        String zipCode = null;
        
        if (members != null)
        {
            Iterator iter = members.iterator();
            while (iter.hasNext())
            {
                myConstMember = (JuvenileFamilyForm.MemberList) iter.next();
                
                boolean isCurrentGuardian = UIJuvenileHelper.isGuardianPrimaryContactAndInHome(myFamForm.getJuvenileNumber(), myConstMember.getMemberNumber());
                if(isCurrentGuardian)
                {
                    UIJuvenileHelper.nullifyJuvenileZipCode(myFamForm.getJuvenileNumber());
                }
                
                //get or update modified guardian info 
                if(myConstMember.isGuardian() && myConstMember.isPrimaryContact() && myConstMember.isInHomeStatus()){
                    familyMemberNumber = myConstMember.getMemberNumber();
                    juvenileId = myFamForm.getJuvenileNumber();
                    JuvenileMemberForm.MemberAddress memberAddress = UIJuvenileHelper.getLatestFamilyMemberAddress(familyMemberNumber);
                    if(memberAddress != null){
                	zipCode = memberAddress.getCompleteZipCode(); 
                    }
                     
                    break;
                }
                
                
                if (myConstMember.isDelete())
                    iter.remove();
            }
            
            
        }
        // Sending PD Request Event
        SaveFamilyConstellationEvent event = UIJuvenileHelper.getSaveFamilyConstellationEvent(myConstellation, myFamForm
                .getJuvenileNumber());
        if (event == null)
        {
            sendToErrorPage(aRequest, "error.generic","Constellation may have been empty and was not saved");
            return aMapping.findForward("toConstellationList");
        }
        CompositeResponse response = MessageUtil.postRequest(event);

        FamilyConstealltionResponseEvent returnEvent = (FamilyConstealltionResponseEvent) MessageUtil.filterComposite(response,
                FamilyConstealltionResponseEvent.class);
        if (returnEvent == null)
        {
            sendToErrorPage(aRequest, "error.common");
            return aMapping.findForward(UIConstants.FAILURE);
        }
        else
        {
            myConstellation.setFamilyNumber(returnEvent.getConstelltionId());
            
            if(familyMemberNumber != null && !"".equals(familyMemberNumber) && juvenileId != null && !"".equals(juvenileId)){
        	
        	//US 172691 - update JuvenileZip field in JJS_MS_Juvenile with primary guardian zip code
     	   	UIJuvenileHelper.populateJuvenileTableWithZipCode(juvenileId, familyMemberNumber, zipCode);
     	   	
            }
            
          //Save Youth Lives with
 	   SaveJJSJuvenileFamilyInfoEvent saveEvent = (SaveJJSJuvenileFamilyInfoEvent) 
 	   					EventFactory.getInstance(JuvenileCaseControllerServiceNames.SAVEJJSJUVENILEFAMILYINFO);
 	   saveEvent.setJuvenileNum( myFamForm.getJuvenileNumber() );
 	   saveEvent.setYouthLivesWithId( myFamForm.getYouthLivesWithId() );
 	   
 	   MessageUtil.postRequest(saveEvent);
        }
        
        myFamForm.setSecondaryAction(UIConstants.CONFIRM);
        aRequest.setAttribute("confirmFamilyCreate","Family constellation successfully created");
//        ActionForward forward = aMapping.findForward("toConstellationList");
        return aMapping.findForward("toConstellationList");
    }    
    
    protected void sendToErrorPage(HttpServletRequest aRequest, String msgKey, String param) {
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msgKey,param));
	    saveErrors(aRequest, errors);
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
