package ui.juvenilewarrant.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.juvenilewarrant.ChargeRequestEvent;
import messaging.juvenilewarrant.JuvenileAssociateRequestEvent;
import messaging.juvenilewarrant.JuvenileAssociateAddressRequestEvent;
import messaging.juvenilewarrant.CreateJuvenileWarrantEvent;
import messaging.juvenilewarrant.reply.ChargeResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import messaging.juvenilewarrant.reply.ActiveWarrantErrorEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDJuvenileWarrantConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilewarrant.form.JuvenileAssociateBean;
import ui.juvenilewarrant.form.JuvenileWarrantForm;
import ui.security.authentication.form.LoginForm;

/**
 * @author ryoung
 *  
 */
public class SubmitJuvWarrantCreateAction extends JIMSBaseAction
{
    /**
     * Not from rose
     *  
     */
    public SubmitJuvWarrantCreateAction()
    {

    }

    public void addButtonMapping(Map buttonMap)
    {
        
        buttonMap.put("button.finish", "finish");
        buttonMap.put("button.back", "back");
        buttonMap.put("button.mainPage", "mainPage");
        buttonMap.put("button.backToWarrant", "confirmPage");
        buttonMap.put("button.link", "link");
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        // TODO Put this setter logic in an assembler
        CreateJuvenileWarrantEvent createWarrant = (CreateJuvenileWarrantEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.CREATEJUVENILEWARRANT);

        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

        createWarrant.setHeight(jwForm.getHeight());
        createWarrant.setAliasName(jwForm.getAliasName());
        createWarrant.setFirstName(jwForm.getFirstName());
        createWarrant.setMiddleName(jwForm.getMiddleName());
        createWarrant.setLastName(jwForm.getLastName());
        createWarrant.setNameSuffix(jwForm.getNameSuffix());
        createWarrant.setWeight(jwForm.getWeight());
        createWarrant.setDaLogNum(jwForm.getDaLogNum());
        createWarrant.setTransactionNum(jwForm.getTransactionNum());
        createWarrant.setJuvenileNum(jwForm.getJuvenileNumInteger());
        createWarrant.setReferralNum(jwForm.getReferralNumInteger());
        createWarrant.setFbiNum(jwForm.getFbiNum());
        createWarrant.setOfficerDepartmentId(jwForm.getOfficerAgencyId());
        createWarrant.setProbationOfficerOfRecordId(jwForm.getProbationOfficerOfRecordId());
        createWarrant.setSidNum(jwForm.getSid());

        createWarrant.setBuild(jwForm.getBuildId());
        createWarrant.setRace(jwForm.getRaceId());
        createWarrant.setSex(jwForm.getSexId());
        createWarrant.setComplexion(jwForm.getComplexionId());
        createWarrant.setEyeColor(jwForm.getEyeColorId());
        createWarrant.setHairColor(jwForm.getHairColorId());
        createWarrant.setSchoolDistrict(jwForm.getSchoolDistrictId());
        createWarrant.setSchoolName(jwForm.getSchoolCodeId());
        createWarrant.setWarrantType(jwForm.getWarrantTypeUI().toUpperCase());
        createWarrant.setComments(jwForm.getCautionComments());
        createWarrant.setPhone(jwForm.getPhoneNum().getPhoneNumber());
        String joinedSSNStr = jwForm.getSSN1() + jwForm.getSSN2() + jwForm.getSSN3();
        createWarrant.setSsn(joinedSSNStr);
        createWarrant.setStatement(jwForm.getAffidavitStatement());
        createWarrant.setSidNum(jwForm.getSid());
        createWarrant.setOfficerId(jwForm.getOfficerOID());
        createWarrant.setWarrantOriginatorId(jwForm.getWarrantOriginatorId());

        createWarrant.setWarrantOriginatorName(jwForm.getWarrantOriginatorName());
        createWarrant.setCourt(jwForm.getCourtId());
        createWarrant.setJudgeName(jwForm.getWarrantOriginatorJudge());

        createWarrant.setDateOfBirth(jwForm.getDateOfBirth());
        createWarrant.setDateOfBirthSource(jwForm.getDateOfBirthSource());

        // Set Selected Cautions codes
        createWarrant.setCautions(jwForm.getSelectedCautions());

        // Set selected scars/marks codes
        createWarrant.setScarsMarks(jwForm.getSelectedScars());

        // Retrieve selected tattoos codes
        createWarrant.setTattoos(jwForm.getSelectedTattoos());

        // Retrieve Charge information
        List charges = new ArrayList();
        if (jwForm.getChargesSelected() != null)
        {
            int len = jwForm.getChargesSelected().size();            
            ChargeRequestEvent chargeEvent;
            ChargeResponseEvent chargeResponse;
            for(int i=0;i<len;i++)
            {    
            	Object theCharges =  jwForm.getChargesSelected().get(i);
            	
            	if ( theCharges instanceof PetitionResponseEvent ){
            		
            		PetitionResponseEvent petitionEvt = (PetitionResponseEvent) jwForm.getChargesSelected().get(i);
                    chargeEvent = new ChargeRequestEvent();
                    chargeResponse = new ChargeResponseEvent();
                    chargeEvent.setTopic(PDJuvenileWarrantConstants.CHARGE_EVENT_TOPIC);
                    chargeResponse.setTopic(PDJuvenileWarrantConstants.CHARGE_EVENT_TOPIC);
                    chargeEvent.setCourtId(petitionEvt.getCourtId());
                    chargeResponse.setCourtId(petitionEvt.getCourtId());
                    chargeResponse.setCourt(petitionEvt.getCourt());
                    chargeResponse.setSequenceNum(petitionEvt.getSequenceNum());
                    if (petitionEvt.getOffenseCodeId() == null || petitionEvt.getOffenseCodeId().equals(""))
                    {
                        chargeEvent.setOffenseCodeId(petitionEvt.getNcicOffenseCode());
                        chargeResponse.setOffenseCodeId(petitionEvt.getNcicOffenseCode());
                    }
                    else
                    {
                        chargeEvent.setOffenseCodeId(petitionEvt.getOffenseCodeId());
                        chargeResponse.setOffenseCodeId(petitionEvt.getOffenseCodeId());
                    }
                    chargeResponse.setOffense(petitionEvt.getOffense());
                    
                    chargeEvent.setOffenseDate(petitionEvt.getOffenseDate());
                    chargeResponse.setOffenseDate(petitionEvt.getOffenseDate());
                    chargeEvent.setPetitionNum(petitionEvt.getPetitionNum());
                    chargeResponse.setPetitionNum(petitionEvt.getPetitionNum());
                    chargeEvent.setSequenceNum(petitionEvt.getSequenceNum());
                    chargeResponse.setSequenceNum(petitionEvt.getSequenceNum());
                    chargeResponse.setLevel(petitionEvt.getLevel());
                    chargeResponse.setDegree(petitionEvt.getDegree());
                    createWarrant.addRequest(chargeEvent);
                    charges.add(chargeResponse);
            	}else {
            		
            		ActionErrors errors = new ActionErrors();
                    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenilewarrant.activeorpending.warrant.found"));
                    saveErrors(aRequest, errors);
                    aRequest.setAttribute("ERROR_MESSAGE", "isPresent");
                    return aMapping.findForward("createFailure");
            	}
            	 
                
            }
        }

        jwForm.setChargesSelected(charges);

        // Retrieve associate information event
        if (jwForm.getAssociates() != null)
        {
            Iterator associateItr = jwForm.getAssociates().iterator();
            JuvenileAssociateRequestEvent associateEvent;
            JuvenileAssociateBean assocBean;
            while (associateItr.hasNext())
            {
                assocBean = (JuvenileAssociateBean) associateItr.next();
                associateEvent = assocBean.getJuvenileAssociateRequestEvent();
                createWarrant.addRequest(associateEvent);
                // get the addresses for that associate
                if (assocBean.getAddressesAsRequestEvents() != null)
                {
	                Iterator addrItr = assocBean.getAddressesAsRequestEvents().iterator();
	                while (addrItr.hasNext())
	                {
	                    JuvenileAssociateAddressRequestEvent addrEvt = (JuvenileAssociateAddressRequestEvent) addrItr.next();
	                    createWarrant.addRequest(addrEvt);
	                }
                }
            }
        }

        CompositeResponse replies = MessageUtil.postRequest(createWarrant);

        // active warrant error event
        ActiveWarrantErrorEvent error = (ActiveWarrantErrorEvent) MessageUtil.filterComposite(replies,
                ActiveWarrantErrorEvent.class);

        if (error != null)
        {
            ActionErrors errors = new ActionErrors();
            errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.juvenilewarrant.activeorpending.warrant.found"));
            saveErrors(aRequest, errors);
            return aMapping.findForward(UIConstants.SEARCH_FAILURE);
        }
        
        JuvenileWarrantResponseEvent jw = (JuvenileWarrantResponseEvent) MessageUtil.filterComposite(replies,
                JuvenileWarrantResponseEvent.class);
        jwForm.setWarrantNum(jw.getWarrantNum());
        jwForm.setFileStampDate(jw.getFileStampDate());
        jwForm.setAssociateUpdatable(true);
        
        // set the associate ids
        List assoc = MessageUtil.compositeToList(replies, JuvenileAssociateResponseEvent.class);        
        JuvenileAssociateResponseEvent jare;
        int len = assoc.size();
        for(int i=0;i<len;i++)
        {
            jare = (JuvenileAssociateResponseEvent) assoc.get(i);
            JuvenileAssociateBean bean = jwForm.getAssociateByRelationshipId(jare.getRelationshipToJuvenileId());
            bean.setAssociateNum(jare.getAssociateNum());
        }

        jwForm.setBackToWarrantUrl(aMapping.findForward(UIConstants.SUCCESS).getPath());

        return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * Currently going back to search page but should go to main page. Still
     * trying to work out problem in action-mapping forward path to point to jsp
     * outside of JuvenileWarrants. This is also needed on "Cancel" button
     * actions becuase current method using href which means Form is not being
     * cleared.
     */

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward mainPage(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
        jwForm.clear();
        
        return aMapping.findForward(UIConstants.MAIN_PAGE);
    }

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        String success = UIConstants.FAILURE;
        JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

        if (jwForm.isJOT())
        {
            success = "jotBack";
        }
        else if (jwForm.isJJS())
        {
            success = "jjsBack";
        }

        return aMapping.findForward(success);
    }

    public ActionForward confirmPage(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        JuvenileWarrantForm form = (JuvenileWarrantForm) aForm;
        String forwardStr;

        if (form.getBackForwardString() != null && form.getBackForwardString().equals("") == false)
        {
            forwardStr = form.getBackForwardString();
        }
        else
        {
            forwardStr = UIConstants.SUCCESS;
        }
        return aMapping.findForward(forwardStr);
    }
}
