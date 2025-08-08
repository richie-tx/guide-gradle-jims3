// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\CheckResidentialPreConditionsCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenilecase.reply.GangRiskPreConditionFailedResponseEvent;
import messaging.juvenilecase.reply.GangRiskPrefillResponseEvent;
import messaging.juvenilecase.reply.ProgressPreConditionFailedResponseEvent;
import messaging.riskanalysis.CheckGangRiskPreConditionsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.RiskAnalysisConstants;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskFormula;
import ui.common.CodeHelper;
import ui.common.UIUtil;
import ui.juvenilecase.SchoolHistoryComparator;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;

public class CheckGangRiskPreConditionsCommand implements ICommand {

	/**
	 * @roseuid 4357DD180205
	 */
	public CheckGangRiskPreConditionsCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4357D9AF0229
	 */
	public void execute(IEvent event) {

	    String ctrlReferralNo="";
	    String currentCourt="";
	  
		CheckGangRiskPreConditionsEvent preCondEvent = (CheckGangRiskPreConditionsEvent) event;
		//Call profile event to fill profile information.
	    GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
	    requestEvent.setJuvenileNum(preCondEvent.getJuvenileNumber());
	    CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);
	    JuvenileProfileDetailResponseEvent juvProfile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent,JuvenileProfileDetailResponseEvent.class);
	    //Get the controlling referral no
	    ctrlReferralNo= UIProgramReferralHelper.getControllingRefNumber(preCondEvent.getCaseFileId());
	    //get Casefile details
		JuvenileCasefile casefile = JuvenileCasefile.find( preCondEvent.getCaseFileId() );
		// Profile stripping fix task 97536
		//Juvenile juvenile = casefile.getJuvenile();
		JuvenileCore juvenile = casefile.getJuvenile();
		//
		Iterator referrals = InterviewHelper.getReferralsForJuvenilesCasefile(juvenile, preCondEvent.getCaseFileId()).iterator();
		while(referrals.hasNext())
		{
			JJSReferral referral = (JJSReferral)referrals.next();
			if (referral.getReferralNum().equals(ctrlReferralNo)) {
	
				//Get current Court
				if(referral.getCourt() != null){
					currentCourt=referral.getCourt().getDescription();
					break;
				}
			}
		}
				
		//Find out if there any other pre-conditions and check for them
		RiskFormula activeFormula = null;
		
		if(preCondEvent.getFormula()!=null && preCondEvent.getFormula().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_MH_SCREEN)){
			 activeFormula = PDRiskAnalysisHelper.getActiveFormulaByAssessmentType(RiskAnalysisConstants.RISK_TYPE_MH_SCREEN);
		}else{
			activeFormula = PDRiskAnalysisHelper.getActiveFormulaByAssessmentType(RiskAnalysisConstants.RISK_TYPE_GANG);
			 
				//Prefill Gang
				GangRiskPrefillResponseEvent responseEvent = new GangRiskPrefillResponseEvent();
				
				// Get Profile Information
				if(juvProfile != null)
				{
					responseEvent.setJuvenileName(juvProfile.getFormattedName());
					responseEvent.setGender(juvProfile.getSex());
					responseEvent.setDob(DateUtil.dateToString(juvProfile.getDateOfBirth(),DateUtil.DATE_FMT_1));
					responseEvent.setJuvNum(juvProfile.getJuvenileNum());
					//bug fix 16063
					
                        		if (juvProfile.getHispanic() != null && juvProfile.getHispanic().equalsIgnoreCase("Y"))
                        		{ //U.S 88526
                        		    responseEvent.setEthnicity("HISPANIC");
                        		}
                        		else
                        		{
                        		    responseEvent.setEthnicity(juvProfile.getRace());
                        		}
					
				}
				//GET SCHOOL DETAILS:
				Collection schoolHistories = UIJuvenileHelper.fetchSchoolHistory(preCondEvent.getJuvenileNumber());
				if (schoolHistories != null
						&& schoolHistories.size() > 0 ) {
					Collections.sort((List) schoolHistories, new SchoolHistoryComparator());
				
					JuvenileSchoolHistoryResponseEvent mySchoolHistoryResp =(JuvenileSchoolHistoryResponseEvent) ((ArrayList) schoolHistories).get(0);
				
					if(mySchoolHistoryResp!=null){
					    responseEvent.setSchool(mySchoolHistoryResp.getSchool());
					    responseEvent.setGrade(mySchoolHistoryResp.getGradeLevelDescription());
					}
				}
				//Current LogOnOfficerID:
				responseEvent.setAssessingJPO(UIUtil.getCurrentUserName());
				//set current court.
				responseEvent.setCourt(currentCourt.toString());
				responseEvent.setDateOfAssessment(DateUtil.dateToString(DateUtil.getCurrentDate(),DateUtil.DATE_FMT_1));
				//Return pre-fill data
				MessageUtil.postReply( responseEvent );
			 
		}
		if(activeFormula!=null){
			PDRiskAnalysisHelper.retrieveRiskQuestionsByFormulaId(activeFormula.getOID());	
		}else {
			this.postFailureRespEvent(RiskAnalysisConstants.NO_ACTIVE_FORMULA);
		}
	}

	/**
	 * @param string
	 * @param dispatch
	 */
	private void postFailureRespEvent(String msg) {
	    	GangRiskPreConditionFailedResponseEvent gangRiskPreCondFailedEvent = new GangRiskPreConditionFailedResponseEvent();
	    	gangRiskPreCondFailedEvent.setMessage(msg);
		MessageUtil.postReply(gangRiskPreCondFailedEvent);
	}

	

	/**
	 * @param event
	 * @roseuid 4357D9AF022B
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4357D9AF022D
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4357D9AF0233
	 */
	public void update(Object anObject) {

	}
}
