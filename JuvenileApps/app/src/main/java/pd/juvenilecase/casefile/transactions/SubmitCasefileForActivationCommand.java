package pd.juvenilecase.casefile.transactions;



import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import naming.JuvenileCaseControllerServiceNames;

import messaging.casefile.SubmitCasefileForActivationEvent;
import messaging.casefile.reply.CasefileForActivationResponseEvent;
import messaging.juvenilecase.GetCasefileWithReferralsEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralDetailResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.referral.JJSReferral;
import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;
import ui.juvenilecase.casefile.form.CasefileActivationForm;

/**
 * 
 */
public class SubmitCasefileForActivationCommand implements ICommand
{
	public void execute(IEvent event)
	{
		SubmitCasefileForActivationEvent evt = (SubmitCasefileForActivationEvent) event;

		JuvenileCasefile casefile = JuvenileCasefile.find(evt.getSupervisionNum());
		JuvenileCore juvCore = JuvenileCore.findCore(casefile.getJuvenileNum());	

		casefile.activateCasefile(evt);
		
		/*Iterator  referrals = evt.getReferrals().iterator();
		while( referrals.hasNext() ) {
		    CasefileActivationForm.Refferal referral = (CasefileActivationForm.Refferal) referrals.next();
		    GetCasefileWithReferralsEvent req = (GetCasefileWithReferralsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCASEFILEWITHREFERRALS);
		    req.setJuvenileNum(casefile.getJuvenileNum());
		    req.setReferralNum(referral.getReferralNumber().substring(0,4));

		    List<JuvenileCasefileReferralDetailResponseEvent> casefileList = MessageUtil.postRequestListFilter(req, JuvenileCasefileReferralDetailResponseEvent.class);
		    if ( casefileList != null
			    && casefileList.size() > 0 ){
			Collections.sort(casefileList, new Comparator<JuvenileCasefileReferralDetailResponseEvent>(){
			    @Override
			    public int compare(JuvenileCasefileReferralDetailResponseEvent c1, JuvenileCasefileReferralDetailResponseEvent c2 ){
				return c1.getCaseFileId().compareTo(c2.getCaseFileId());
			    }
			});
			if ( casefileList.get(0).getCaseFileId() != null
				&& casefileList.get(0).getCaseFileId().length() > 0  ){
			    JuvenileCasefile juvenileCasefile = JuvenileCasefile.find(casefileList.get(0).getCaseFileId());
			    if ( juvenileCasefile != null 
				    && juvenileCasefile.getActivationDate() != null ){
				GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
				jjsEvt.setJuvenileNum(casefile.getJuvenileNum());
				jjsEvt.setReferralNum(referral.getReferralNumber().substring(0,4));
				Iterator iter = JJSReferral.findAll(jjsEvt);
				if(iter.hasNext()) {
				    JJSReferral jjs = (JJSReferral)iter.next();
				    jjs.setTJJDReferralDate(juvenileCasefile.getActivationDate());
				    IHome home = new Home();
				    home.bind( jjs );
				    jjs = null;
				}
			    }
			}
			
		    }
		}*/
		Iterator  referrals = evt.getReferrals().iterator();
		
		while( referrals.hasNext() ) {
		    CasefileActivationForm.Refferal referral = (CasefileActivationForm.Refferal) referrals.next();
		    if ( "1".equals( referral.getRefSequenceNum() ) ){
			if (casefile != null
				&& casefile.getJuvenileNum() != null
				&& casefile.getJuvenileNum().length() > 0 
				&& referral.getReferralNumber() != null
				&& referral.getReferralNumber().length() > 0 ){
			    
			    GetJJSReferralEvent getReferralEvent = new GetJJSReferralEvent();
			    getReferralEvent.setJuvenileNum(casefile.getJuvenileNum());
			    getReferralEvent.setReferralNum(referral.getReferralNumber().substring(0,4) );
			    Iterator referralIter = JJSReferral.findAll(getReferralEvent);
			    if ( referralIter.hasNext() ) {
				JJSReferral jjsReferral = (JJSReferral) referralIter.next();
				jjsReferral.setTJJDReferralDate(casefile.getActivationDate());
				IHome home = new Home();
				home.bind( jjsReferral );
				jjsReferral = null;
			    }
			}
			
		    }
		}

		CasefileForActivationResponseEvent reply = new CasefileForActivationResponseEvent();
		
		reply.setDateOfBirth(juvCore.getDateOfBirth());
		
		reply.setSupervisionNum(casefile.getOID());
		reply.setSupervisionStatusId(casefile.getCaseStatusId());
		reply.setMAYSINeeded(casefile.getIsMAYSINeeded());
		//taken out for US 14459
		//reply.setTitle4eNeeded(casefile.getIsBenefitsAssessmentNeeded());		
		reply.setRiskAnalysis(casefile.getIsRiskAssessmentNeeded());
		reply.setSupervisionTypeId(casefile.getSupervisionTypeId());
		reply.setSupervisionEndDate(casefile.getSupervisionEndDate());
		reply.setCourtOrderedProbationStartDate(casefile.getCourtOrderedProbationStartDate());
		
		reply.setActivationDate(casefile.getActivationDate());
		
		MessageUtil.postReply(reply);
	}

	/**
	 * @param event
	 * 
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * 
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * 
	 */
	public void update(Object anObject)
	{

	}

}
