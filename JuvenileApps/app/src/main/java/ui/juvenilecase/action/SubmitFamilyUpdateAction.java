package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.family.SaveFamilyConstellationEvent;
import messaging.family.SaveFamilyConstellationMemberInfoEvent;
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
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm;

public class SubmitFamilyUpdateAction extends LookupDispatchAction
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
        keyMap.put("button.returnToConstellation", "goToConstellation");
        return keyMap;
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward goToConstellation(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward("toConstellationList");
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;
    }

    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileFamilyForm myFamForm = (JuvenileFamilyForm) aForm;
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        // Get to be deleted items
        JuvenileFamilyForm.Constellation currentConstellation = myFamForm.getCurrentConstellation();
        Collection members = currentConstellation.getMemberList();
        Iterator iter = members.iterator();
        JuvenileFamilyForm.MemberList myMember;
        //						Sending PD Request Event
        SaveFamilyConstellationEvent event = (SaveFamilyConstellationEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.SAVEFAMILYCONSTELLATION);
        event.setJuvNum(myFamForm.getJuvenileNumber());
        event.setConstellationNum(currentConstellation.getFamilyNumber());
        
        String familyMemberNumber = null;
        String juvenileId = null;
        String zipCode = null;
        
        SaveFamilyConstellationMemberInfoEvent constellationEvent = new SaveFamilyConstellationMemberInfoEvent();
        List<SaveFamilyConstellationMemberInfoEvent> constellationEventList = new ArrayList<SaveFamilyConstellationMemberInfoEvent>();
        
        while (iter.hasNext())
        {
            myMember = (JuvenileFamilyForm.MemberList) iter.next();
            
            boolean isCurrentGuardian = UIJuvenileHelper.isGuardianPrimaryContactAndInHome(myFamForm.getJuvenileNumber(), myMember.getMemberNumber());
            if(isCurrentGuardian)
            {
                UIJuvenileHelper.nullifyJuvenileZipCode(myFamForm.getJuvenileNumber());
            }
            
            //US 172691
            if(myMember.isGuardian() && myMember.isPrimaryContact() && myMember.isInHomeStatus())
            {
        	familyMemberNumber = myMember.getMemberNumber();
                juvenileId = myFamForm.getJuvenileNumber();
                JuvenileMemberForm.MemberAddress memberAddress = UIJuvenileHelper.getLatestFamilyMemberAddress(familyMemberNumber);
                if(memberAddress != null){
                    zipCode = memberAddress.getCompleteZipCode(); 
                }                
        	
            }
            //end
            
            constellationEvent = UIJuvenileHelper.getSaveFamilyConstellationMemberInfoEvent(myMember);

            event.addRequest(constellationEvent);
            constellationEventList.add(constellationEvent);
            
        }
        
        MessageUtil.postRequest(event);
        
        //update over21 field(JJCFAMMEMBER) for family member - US 181006
        if(constellationEventList.size() > 0){
            
            for(int i = 0; i < constellationEventList.size(); i++){
        	SaveFamilyConstellationMemberInfoEvent item = constellationEventList.get(i);
        	
        	if(item != null){
        	    
                    UIJuvenileHelper.updateOver21FamilyMember(item.getMemberNum(), item.getIsOver21());
        	}
        	
            }
            
        }
        
        //Todo - error handler required here
        if(familyMemberNumber != null && !"".equals(familyMemberNumber) && juvenileId != null && !"".equals(juvenileId)){    	
    	    //US 172691 - update JuvenileZip field in JJS_MS_Juvenile with primary guardian zip code
            UIJuvenileHelper.populateJuvenileTableWithZipCode(juvenileId, familyMemberNumber, zipCode);	   
        }

        myFamForm.setSecondaryAction(UIConstants.CONFIRM);
        aRequest.setAttribute("confirmFamilyUpdate", "Family constellation successfully updated");
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
