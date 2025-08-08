//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\SubmitJuvenileCasefileRiskNeedLvlAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.SaveJuvenileRiskNeedLevelEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.form.JuvenileCasefileForm;

/**
 * @author ugopinath
 * 
 */
public class SubmitJuvenileCasefileRiskNeedLvlAction  extends JIMSBaseAction
{
	public final static String JUVENILE_BEHAVIOR_HISTORY_FORM = "juvenileBehaviorHistoryForm";

	/**
	 * @roseuid 4278CA190105
	 */
	public SubmitJuvenileCasefileRiskNeedLvlAction()
	{
		
	}

	
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileCasefileForm form = (JuvenileCasefileForm)aForm;
		SaveJuvenileRiskNeedLevelEvent saveEvent = (SaveJuvenileRiskNeedLevelEvent)EventFactory.getInstance(JuvenileCaseControllerServiceNames.SAVEJUVENILERISKNEEDLEVEL);
		saveEvent.setCaseFileId(form.getSupervisionNum());
		saveEvent.setJuvenileNum(form.getJuvenileNum());
		saveEvent.setReferralNumber(form.getSelectedValue());
		saveEvent.setRiskLvl(form.getUserRiskLvl());
		saveEvent.setNeedsLvl(form.getUserNeedsLvl());
		saveEvent.setLastPactDate(DateUtil.stringToDate(form.getUserPACTDate(), DateUtil.DATE_FMT_1));
		saveEvent.setStatus("CURRENT");
		if( form.getUserPactId() != null){
		    saveEvent.setPactId(form.getUserPactId());
		}
		
		List numbers = new ArrayList();
		// check for r2c referrals
		 Collection assocatedReferrals = form.getJuvenileCasefileReferralsList();
		 
		 Iterator<JuvenileCasefileReferralsResponseEvent> iter = assocatedReferrals.iterator();
		 while( iter.hasNext()){
		     
		     JuvenileCasefileReferralsResponseEvent referral = iter.next();
		     numbers.add( referral.getReferralNumber() );
		 }
		 saveEvent.setReferralNumbers(numbers);
		 
		 CompositeResponse compositeResponse = MessageUtil.postRequest(saveEvent);
		 Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	        if (errorResponse != null)
	        {
	            ErrorResponseEvent errEvt = (ErrorResponseEvent) errorResponse;
	            try {
					handleFatalUnexpectedException(errEvt.getMessage());
				} catch (GeneralFeedbackMessageException e) {
					e.printStackTrace();
				}
	        }
	    form.setSecondaryAction("riskNeedConfirm");  
		return aMapping.findForward(UIConstants.FINISH_SUCCESS);
	}
	
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	@Override
	protected void addButtonMapping(Map keyMap) {
		 keyMap.put("button.back", "back");        
         keyMap.put("button.finish", "finish");
		
	}
	

}
