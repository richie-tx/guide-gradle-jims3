package ui.juvenilecase.facility.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.facility.form.AdmitReleaseForm;


public class DisplayJuvenileFacilityReleaseSummaryAction extends LookupDispatchAction{
	
	
	
	 // ------------------------------------------------------------------------
    // --- constructor ---
    // ------------------------------------------------------------------------

    /**
     * @roseuid 42A898CE034B
     */
    public DisplayJuvenileFacilityReleaseSummaryAction()
    {

    }
    
    /**
     * 
     * @param aMapping
     *            The a mapping.
     * @param aForm
     *            The a form.
     * @param aRequest
     *            The a request.
     * @param aResponse
     *            The a response.
     * @return ActionForward
     * @roseuid 42A46D8E0234
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	AdmitReleaseForm form = (AdmitReleaseForm) aForm;
    	
    	String transferToRef[]= StringUtils.split(form.getTransferToReferral(), '-');
    	int transferToRefLength = StringUtils.countMatches(form.getTransferToReferral(), "-");
    	if(transferToRef!=null && transferToRefLength==1){
			form.setTransferToReferral(transferToRef[0]);
		}
    	
    	String currentReferral[]= StringUtils.split(form.getCurrentReferral(), '-');
    	int currentReferralLength = StringUtils.countMatches(form.getCurrentReferral(), "-");
		if(currentReferralLength==2){
			form.setCurrentReferral(currentReferral[0]);
			if(currentReferral.length>1){
				form.setCurrentSupervisionNum(currentReferral[1]);
			}
		}else if(form.getCurrentReferral()!=null){
			form.setCurrentReferral(currentReferral[0]);
		}else{
			form.setCurrentReferral(form.getBookingReferral());
		}
		
		//offense Record set,booking referral, booking supervision, offense Level
		if(form.getReferrals()!=null && !form.getReferrals().isEmpty()){
			List<JuvenileProfileReferralListResponseEvent>  profileRespListDetails =(List<JuvenileProfileReferralListResponseEvent>)form.getReferrals();
			if(profileRespListDetails!=null){
				Iterator<JuvenileProfileReferralListResponseEvent> juvProfResItr = profileRespListDetails.iterator();
				while (juvProfResItr.hasNext()){
					JuvenileProfileReferralListResponseEvent profileRespEvent = juvProfResItr.next();
					if(form.getCurrentReferral().equals(profileRespEvent.getReferralNumber())){
						 form.setCurrentPetitionNum(profileRespEvent.getPetitionNumber());
				  	 	 form.setCurrentOffense(profileRespEvent.getMostSevereOffense().getOffenseDescription());
				  	 	 form.setCurrentOffenseCd(profileRespEvent.getMostSevereOffense().getOffenseCodeId());
				  	   	 form.setCurrentOffenseLevel(profileRespEvent.getMostSevereOffense().getOffenseLevelId());
				  	   	 form.setSeveritySubType(profileRespEvent.getMostSevereOffense().getSeveritySubtype());
				  	   	 
						if(profileRespEvent.getCasefiles()!=null){
					  	{
					  		Iterator<JuvenileCasefileResponseEvent> casefilesIter = profileRespEvent.getCasefiles().iterator();
							while(casefilesIter.hasNext())
							{
								JuvenileCasefileResponseEvent casefileResp = (JuvenileCasefileResponseEvent)casefilesIter.next();
							  	if(casefileResp!=null && casefileResp.getSupervisionNum()!=null){ 
							  	  if(form.getCurrentSupervisionNum().equals(casefileResp.getSupervisionNum())){
							  	 	  form.setCurrentPetitionNum(profileRespEvent.getPetitionNumber());
							  	 	  form.setCurrentOffense(profileRespEvent.getMostSevereOffense().getOffenseDescription());
							  	 	  form.setCurrentOffenseCd(profileRespEvent.getMostSevereOffense().getOffenseCodeId());
							  	   	  form.setCurrentOffenseLevel(profileRespEvent.getMostSevereOffense().getOffenseLevelId());
							  	   	  form.setSeveritySubType(profileRespEvent.getMostSevereOffense().getSeveritySubtype());
							  	   	  break;
								   }
							  	}
							}
					  	}
					}
				}
			}
		}
	}
    
	form.setHideFacilityTraitsLink(false);
	form.setAction("referralTransferView");
	
    return( aMapping.findForward(UIConstants.SUCCESS) );
   }
    
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse){
			return( aMapping.findForward(UIConstants.CANCEL ) ) ;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse){
			return( aMapping.findForward(UIConstants.BACK )) ;
	}


        
    /**
     * 
     */
    protected Map getKeyMethodMap()
    {
        Map<String,String>  keyMap = new HashMap<String,String> ();        
        keyMap.put("button.next", "next");
        keyMap.put("button.back", "back");
        keyMap.put("button.cancel", "cancel");
        return keyMap;
    }
   
}
