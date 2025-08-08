package ui.juvenilecase.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenile.reply.JJSReferralResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefilePetitionsEvent;
import messaging.juvenilewarrant.GetJOTDataEvent;
import messaging.juvenilewarrant.reply.JJSChargeResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingResponseEvent;
import messaging.referral.GetJOTDetailsEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.GetJuvenileCasefileReferralDetailsEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.nimbusds.jose.util.DateUtils;

import ui.common.UIUtil;
import ui.juvenilecase.form.AssignedReferralsForm;

public class DisplayJuvenileCasefileReferralDetailsAction extends Action
{
    /**
     * @roseuid 42A9A1E302F8
     */
    public DisplayJuvenileCasefileReferralDetailsAction()
    {
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42A99B970297
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, 
        HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
        // clean the form
        AssignedReferralsForm form = (AssignedReferralsForm) aForm;
        form.clear();
        String actionType = form.getActionType();
        form.setActionType("");

        String juvenileNum = aRequest.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM);
        String referralNum = aRequest.getParameter(PDJuvenileCaseConstants.REFERRALNUM_PARAM);

        getReferralDetails(juvenileNum, referralNum, form);
        getOffenses(juvenileNum, referralNum, form);
        getPetitions(juvenileNum, referralNum, form);

        if (actionType.equalsIgnoreCase("caseloadMgrView"))
            return aMapping.findForward("CLMsuccess");
        else
            return aMapping.findForward("success");
    }

    /**
     * Retrieves the Assigned Referral Details information by juv# and ref#
     * 
     * @param juvenileNum
     * @param referralNum
     * @param form
     */
    private void getReferralDetails(String juvenileNum, String referralNum, AssignedReferralsForm form)
    {
        GetJuvenileCasefileReferralDetailsEvent ref = (GetJuvenileCasefileReferralDetailsEvent)
				EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILECASEFILEREFERRALDETAILS);
        ref.setJuvenileNum(juvenileNum);
        ref.setReferralNum(referralNum);

        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        dispatch.postEvent(ref);
        CompositeResponse response = (CompositeResponse) dispatch.getReply();
        JJSReferralResponseEvent refresp = (JJSReferralResponseEvent)MessageUtil.postRequest(ref, JJSReferralResponseEvent.class);
        if (refresp != null)
        {
            form.setCloseDate(UIUtil.parseDate(refresp.getCloseDate()));
            form.setCourtId(refresp.getCourtId());
            form.setCourtDate(UIUtil.parseDate(refresp.getCourtDate()));
            form.setCourtDispositionDesc(refresp.getCourtDispositionDesc());
            form.setCourtResultDesc(refresp.getCourtResultDesc());
            form.setIntakeDate(UIUtil.parseDate(refresp.getIntakeDate()));
            form.setIntakeDecision(refresp.getIntakeDecision());
            form.setJuvenileNum(refresp.getJuvenileNum());
            form.setReferralNum(refresp.getReferralNum());
            form.setSequenceNum(refresp.getSequenceNum());
            form.setTransactionNum(refresp.getTransactionNum());
            form.setReferralDate(UIUtil.parseDate(refresp.getReferralDate()));
            form.setDaLogNum(refresp.getDaLogNum());
            
            form.setSupervisionBeginDate(UIUtil.parseDate(refresp.getProbationStartDate()) ) ;
            form.setSupervisionEndDate(UIUtil.parseDate(refresp.getProbationEndDate()) );
            form.setCaseType(refresp.getPIADescription());
            // full description will not fit in code table with max. size of 100, using local method
            // to set value.
            form.setLevelOfCare(this.getLevelOfCareFullDesc(refresp.getLevelOfCare()));
            form.setReferralTypeInd(refresp.getReferralTypeInd());
            
            form.setTerminationDate(refresp.getTerminationDate());
            
            //task 149504
            if(form.getDaLogNum()!=null&&!form.getDaLogNum().isEmpty())
            {
                /*GetJOTDataEvent jotEvent =
        		(GetJOTDataEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJOTDATA);*/
        	GetJOTDetailsEvent jotEvent =
        		(GetJOTDetailsEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJOTDETAILS);
        	jotEvent.setDaLogNum(form.getDaLogNum());
                CompositeResponse jotResponse = MessageUtil.postRequest(jotEvent);
                JuvenileOffenderTrackingResponseEvent juvenileOffenderTrackingResponse = (JuvenileOffenderTrackingResponseEvent) MessageUtil.filterComposite(jotResponse, JuvenileOffenderTrackingResponseEvent.class);
                if ( juvenileOffenderTrackingResponse  != null )  
                {
                    try {
                    	String daDateOut = String.valueOf(juvenileOffenderTrackingResponse.getDaDateOut() );  
                    	 if ( daDateOut != null || daDateOut.length() > 0 ) {
                    	     	form.setDAdateOut(new SimpleDateFormat("yyyyMMdd").parse(daDateOut));	        		
	        	    } 
                    	 else
	        		form.setDAdateOut(null);	        	     
                    	}
                    	catch (Exception e){}
            		form.setDaStatus(juvenileOffenderTrackingResponse.getLogStatus());
                }
                else   
                {
            	 	form.setDaStatus(null);
            	 	form.setDAdateOut(null);
                }
            }
            //
        
        }
    }

    /**
     * Retrieves all the offenses for a juvenile by referral num
     * 
     * @param aRequest
     */
    private void getOffenses(String juvenileNum, String referralNum, AssignedReferralsForm form)
    {
        GetJuvenileCasefileOffensesEvent off = (GetJuvenileCasefileOffensesEvent) 
				EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILECASEFILEOFFENSES);
        off.setJuvenileNum(juvenileNum);
        off.setReferralNum(referralNum);

        List offenses = MessageUtil.postRequestListFilter(off, JJSOffenseResponseEvent.class);
        form.setOffenses(offenses);
    }

    /**
     * Retrieve Petitons for a Juvenile/Referral
     * 
     * @param juvenileNum
     * @param referralNum
     * @param form
     */
    public void getPetitions(String juvenileNum, String referralNum, AssignedReferralsForm form)
    {
        GetJuvenileCasefilePetitionsEvent pet = (GetJuvenileCasefilePetitionsEvent) 
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEPETITIONS);
        pet.setJuvenileNum(juvenileNum);
        pet.setReferralNum(referralNum);

        List petitions = MessageUtil.postRequestListFilter(pet, JJSChargeResponseEvent.class);
        form.setPetitions(petitions);
    }

    /**
     * Set full description value for Level Of Care
     * 
     * @param level
     *            of care short description from code table return level of care full description
     *            defined in requirements
     */
    public String getLevelOfCareFullDesc(String levelOfCare)
    {
        String loc = "";
        if (levelOfCare != null && !levelOfCare.equals(""))
        {
            loc = levelOfCare.toUpperCase();
            switch (loc.charAt(0))
            {
            case 'B':
                loc = UIConstants.BASIC_LEVEL_OF_CARE_DESCRIPTION;
                break;
            case 'M':
                loc = UIConstants.MODERATE_LEVEL_OF_CARE_DESCRIPTION;
                break;
            case 'S':
                loc = UIConstants.SPECIALIZED_LEVEL_OF_CARE_DESCRIPTION;
                break;
            case 'I':
                loc = UIConstants.INTENSE_LEVEL_OF_CARE_DESCRIPTION;
                break;
            default:
                loc = levelOfCare;
            }
        }
        return loc;
    }
}
