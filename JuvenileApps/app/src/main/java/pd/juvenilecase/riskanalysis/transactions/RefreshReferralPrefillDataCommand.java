package pd.juvenilecase.riskanalysis.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import naming.RiskAnalysisConstants;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.juvenilewarrant.JuvenileOffenderTrackingCharge;
import messaging.juvenilecase.reply.CourtReferralPrefillResponseEvent;
import messaging.riskanalysis.RefreshReferralPrefillDataEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.util.MessageUtil;

public class RefreshReferralPrefillDataCommand implements ICommand {

	public void execute(IEvent event) {
		
		RefreshReferralPrefillDataEvent reqEvent = (RefreshReferralPrefillDataEvent) event;
		
		RiskAnalysis thisRisk = RiskAnalysis.find(reqEvent.getAssessmentId());
		JuvenileCasefile juvCaseFile = JuvenileCasefile.find(new Integer(thisRisk.getCasefileID()).toString());
		CourtReferralPrefillResponseEvent courtReferralPrefillResponseEvent = new CourtReferralPrefillResponseEvent();
		
	    StringBuffer referralsInformation = new StringBuffer();
	    int totalReferralsJuvenileCasefile = 0;
	    int totalReferralsJuvenile = 0;

	    //Pre-Fill get Referral Numbers and Date for Referral
	    //Referral Number(s)/Date(s):  1010 � 06/25/2008 , 1020 � 06/27/2008,  1030 � 06/29/2008
	    Iterator<JJSReferral> casefileReferralsIter1 = InterviewHelper.getReferralsForJuvenilesCasefile(juvCaseFile.getJuvenile(), juvCaseFile.getOID()).iterator();
	    
	    while (casefileReferralsIter1 != null && casefileReferralsIter1.hasNext())
		{
	    	totalReferralsJuvenileCasefile++;
			JJSReferral referral = casefileReferralsIter1.next();
			
			//TODO: create referral numbers and their date info here
			if (referralsInformation.length() < 1) {
				referralsInformation.append(referral.getReferralNum()).append(" - ").append(RiskAnalysisConstants.DATE_FORMAT.format(referral.getReferralDate()));
			} else {
				referralsInformation.append(" , ").append(referral.getReferralNum()).append(" - ").append(RiskAnalysisConstants.DATE_FORMAT.format(referral.getReferralDate()));
			}
			
		}
		courtReferralPrefillResponseEvent.setReferralsInformation(referralsInformation.toString());
	    
		//Pre-Fill Total Referrals for a Juvenile 
		String juvNum = juvCaseFile.getJuvenile().getJuvenileNum();
		Iterator<JJSReferral> referralIter = JJSReferral.findAll("juvenileNum", juvNum );
		totalReferralsJuvenile = getTJPCTotalReferrals(referralIter);
		courtReferralPrefillResponseEvent.setTotalReferralsToJuvenile(totalReferralsJuvenile);
	    
	    Iterator<JJSReferral> casefileReferralsIter2 = InterviewHelper.getReferralsForJuvenilesCasefile(juvCaseFile.getJuvenile(), juvCaseFile.getOID()).iterator();
		
	    outerloop:
	    while (casefileReferralsIter2 != null && casefileReferralsIter2.hasNext())
		{
			
			JJSReferral referral = casefileReferralsIter2.next();
			
			//TODO may use JJSOffense instead of JuvenileOffenderTrackingCharge? would match on JJSOffense.referralNum
			
			if (referral.getDaLogNum() != null && referral.getDaLogNum().length() > 0) 
			{
				
				Iterator<JuvenileOffenderTrackingCharge> jotCharges = 
					new Home().findAll("daLogNum", referral.getDaLogNum(), JuvenileOffenderTrackingCharge.class);
				
				
				while (jotCharges != null && jotCharges.hasNext())
				{
					JuvenileOffenderTrackingCharge jotCharge = jotCharges.next();
												
					JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode",jotCharge.getOffenseCode().getJuvOffenseCode());
					
					String severitySubtype = offenseCode.getSeveritySubtype();
					String category = offenseCode.getCategory();
					String severity = offenseCode.getSeverity();
					severity.replaceFirst("^0+(?!$)", ""); //Removes leading zeros but leaves one zero if all characters are zeros
					
					//1. Severity Subtype indicator = V 
					if (severitySubtype.equalsIgnoreCase(RiskAnalysisConstants.SERVERITYSUBTYPEINDICATOR_V))
					{
						      //2. AND Offense Catagory = CF, F1, F2, or F3     
						if (( category.equalsIgnoreCase(RiskAnalysisConstants.OFFENSECATEGORY_CF) || 
							  category.equalsIgnoreCase(RiskAnalysisConstants.OFFENSECATEGORY_F1) ||
							  category.equalsIgnoreCase(RiskAnalysisConstants.OFFENSECATEGORY_F2) ||
							  category.equalsIgnoreCase(RiskAnalysisConstants.OFFENSECATEGORY_F3))
							  
							  || 
							  
							  //3. or Severity code = 06, 07, 08, or 09, then Violent Felony = Yes
							( offenseCode.getSeverity().equalsIgnoreCase(RiskAnalysisConstants.SEVERITY_6) ||	  
							  offenseCode.getSeverity().equalsIgnoreCase(RiskAnalysisConstants.SEVERITY_7) ||
							  offenseCode.getSeverity().equalsIgnoreCase(RiskAnalysisConstants.SEVERITY_8) ||
							  offenseCode.getSeverity().equalsIgnoreCase(RiskAnalysisConstants.SEVERITY_9) ))
						{
							courtReferralPrefillResponseEvent.setVoilentFelony(true);
							break outerloop;
						}
						
					}
					
				} //end while - jot chargers
				
			} //end if - DA Log Number
			
		} //end while - referrals
	    
	    MessageUtil.postReply(courtReferralPrefillResponseEvent);
	}
	public static int getTJPCTotalReferrals(Iterator<JJSReferral> referralIter)
	{
		List referralList = new ArrayList();
		
		while (referralIter.hasNext())
		{
			JJSReferral aReferral = referralIter.next();
			if( aReferral.getReferralDate() != null 
					&& 	aReferral.getReferralTypeInd() != null
					&& ( aReferral.getReferralTypeInd().equalsIgnoreCase("FM") ||
						 aReferral.getReferralTypeInd().equalsIgnoreCase("PF") ||
						 aReferral.getReferralTypeInd().equalsIgnoreCase("CS") ||
						 aReferral.getReferralTypeInd().equalsIgnoreCase("TR")
						)
					)
				{
					referralList.add(aReferral);
				}
		}
		
		return referralList.size();
	}
}
