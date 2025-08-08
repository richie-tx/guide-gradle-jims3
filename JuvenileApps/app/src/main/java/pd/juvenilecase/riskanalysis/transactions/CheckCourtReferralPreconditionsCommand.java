//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\CheckResidentialPreConditionsCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.joda.time.DateTime;
import org.joda.time.Years;

import messaging.juvenilecase.reply.CourtReferralPreConditionFailedResponseEvent;
import messaging.juvenilecase.reply.CourtReferralPrefillResponseEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.GetJuvenileProfileReferralListEvent;
import messaging.riskanalysis.CheckCourtReferralPreconditionsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.util.MessageUtil;
import naming.RiskAnalysisConstants;
import naming.UIConstants;
import pd.codetable.Code;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskFormula;
import pd.juvenilewarrant.JuvenileOffenderTrackingCharge;
import pd.km.util.Name;

public class CheckCourtReferralPreconditionsCommand implements ICommand 
{
   
   /**
    * @roseuid 4357DD180205
    */
   public CheckCourtReferralPreconditionsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4357D9AF0229
    */
   public void execute(IEvent event) 
   {
		CheckCourtReferralPreconditionsEvent preCondEvent = (CheckCourtReferralPreconditionsEvent) event;
		JuvenileCasefile juvCaseFile = JuvenileCasefile.find(preCondEvent.getCaseFileId());
				
		//Find out if there any other pre-conditions and check for them
		if(juvCaseFile != null)
		{
			
			//Gather Pre-Filled Information
			CourtReferralPrefillResponseEvent courtReferralPrefillResponseEvent = new CourtReferralPrefillResponseEvent();
		
			//Pre-Fill Current Offense is a Violent Felony: 
			
				//Get All Referrals for a Casefile
			    boolean referralForCasefilehasFamilyAssaultOffense = false;
			    String referralForCasefileWithFamilyAssaultOffense = "";
			    StringBuffer referralsInformation = new StringBuffer();
			    int totalReferralsJuvenileCasefile = 0;
			    int totalReferralsJuvenile = 0;

			    //Pre-Fill get Referral Numbers and Date for Referral
			    //Referral Number(s)/Date(s):  1010 � 06/25/2008 , 1020 � 06/27/2008,  1030 � 06/29/2008
			    Iterator<JJSReferral> casefileReferralsIter1 = InterviewHelper.getReferralsForJuvenilesCasefile(juvCaseFile.getJuvenile(), juvCaseFile.getOID()).iterator();
			    
			    while (casefileReferralsIter1.hasNext())
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
			    
			    //Pre-Fill Total Referrals for a Juvenile for Current Casefile - PRIOR
				//courtReferralPrefillResponseEvent.setTotalReferralsToJuvenile(totalReferralsJuvenileCasefile);
				
				//Pre-Fill Total Referrals for a Juvenile 
				String juvNum = juvCaseFile.getJuvenile().getJuvenileNum();
				Iterator<JJSReferral> referralIter = JJSReferral.findAll("juvenileNum", juvNum );
				totalReferralsJuvenile = getTJPCTotalReferrals(referralIter);
				courtReferralPrefillResponseEvent.setTotalReferralsToJuvenile(totalReferralsJuvenile);
			    
			    Iterator<JJSReferral> casefileReferralsIter2 = InterviewHelper.getReferralsForJuvenilesCasefile(juvCaseFile.getJuvenile(), juvCaseFile.getOID()).iterator();
				
			    outerloop:
			    while (casefileReferralsIter2.hasNext())
				{
					
					JJSReferral referral = casefileReferralsIter2.next();
					
					//TODO may use JJSOffense instead of JuvenileOffenderTrackingCharge? would match on JJSOffense.referralNum
					//User Story 17112 to use JJSOffense instead of JuvenileOffenderTrackingCharge
					GetJuvenileCasefileOffensesEvent offEvent = new GetJuvenileCasefileOffensesEvent();
					offEvent.setJuvenileNum(juvNum);
					offEvent.setReferralNum(referral.getReferralNum());

					Iterator<JJSOffense> iter = JJSOffense.findAll(offEvent);
					while (iter.hasNext())
					{
						JJSOffense offense = (JJSOffense)iter.next();
						JuvenileOffenseCode offenseCode = offense.getOffenseCode();
						String severitySubtype = offenseCode.getSeveritySubtype();
						String category = offenseCode.getCategory();
						String severity = offenseCode.getSeverity();
						severity.replaceFirst("^0+(?!$)", ""); //Removes leading zeros but leaves one zero if all characters are zeros
						
						//1. Severity Subtype indicator = V 
						if (severitySubtype!=null && severitySubtype.equalsIgnoreCase(RiskAnalysisConstants.SERVERITYSUBTYPEINDICATOR_V))
						{
							      //2. AND Offense Catagory = CF, F1, F2, or F3     
							if ( category !=null && ( category.equalsIgnoreCase(RiskAnalysisConstants.OFFENSECATEGORY_CF) || 
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
						
					}
					/*if (referral.getDaLogNum() != null && referral.getDaLogNum().length() > 0) 
					{
						
						Iterator<JuvenileOffenderTrackingCharge> jotCharges = 
							new Home().findAll("daLogNum", referral.getDaLogNum(), JuvenileOffenderTrackingCharge.class);
						
						
						while (jotCharges.hasNext())
						{
							JuvenileOffenderTrackingCharge jotCharge = jotCharges.next();
														
							JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find(jotCharge.getOffenseCode().getJuvOffenseCode());
							
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
*/					
				} //end while - referrals
				
			// The following will get all of the referrals for the user, across casefiles
			GetJuvenileProfileReferralListEvent refEvent = new GetJuvenileProfileReferralListEvent();
			refEvent.setJuvenileNum(juvCaseFile.getJuvenileNum());

			//Pre-Fill Juvenile Name
			Name name = new Name(juvCaseFile.getJuvenile().getFirstName(), juvCaseFile.getJuvenile().getMiddleName(),
					juvCaseFile.getJuvenile().getLastName());
			courtReferralPrefillResponseEvent.setJuvnileName(name.getFormattedName());
			
			//Pre-Fill Date of Birth
			Date dob = juvCaseFile.getJuvenile().getDateOfBirth();
			courtReferralPrefillResponseEvent.setDateOfBirth(dob);
			
			//Pre-Fill Headerquarter County
			courtReferralPrefillResponseEvent.setHeadquarterCounty("Harris County");
			
			//Pre-Fill Headerquarter County
			courtReferralPrefillResponseEvent.setPidNumber(juvCaseFile.getJuvenile().getJuvenileNum());
			
			//Pre-Fill Gender Information
			Code sex = juvCaseFile.getJuvenile().getSex();
			String sexDescription = (sex == null) ? UIConstants.EMPTY_STRING : sex.getDescription();
			String sexId = (sex == null) ? UIConstants.EMPTY_STRING : sex.getCode();
			courtReferralPrefillResponseEvent.setJuvSex( sexDescription );
			courtReferralPrefillResponseEvent.setJuvSexId( sexId );
			
			//Pre-Fill Date of Assessment
			courtReferralPrefillResponseEvent.setDateOfReferral(new Date());
			
			//Pre-Fill Age First Referred
			referralIter = JJSReferral.findAll("juvenileNum", juvNum );
			courtReferralPrefillResponseEvent.setAgeOfFirstReferral( getTJPCAgeOfFirstReferral(referralIter, juvNum) );
				
			//Return pre-fill data
			MessageUtil.postReply( courtReferralPrefillResponseEvent );
			
			//Based on gender decide which court referral type to display.
			if (sexId.equalsIgnoreCase("M")) 
			{
				this.retrieveRiskQuestions(RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE);
			} 
			else
			{
				this.retrieveRiskQuestions(RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE);
			}
				
		}
		else
		{
			this.postFailureRespEvent("CaseFile " + preCondEvent.getCaseFileId() + " does not have case/supervision status Active for the Juvenile Number " + preCondEvent.getJuvenileNumber());
		}
   }
 
   /**
    * @param assessmentType
    */
   	private void retrieveRiskQuestions(String assessmentType){
	   
		RiskFormula activeFormula = PDRiskAnalysisHelper.getActiveFormulaByAssessmentType(assessmentType);
		if (activeFormula != null){
			PDRiskAnalysisHelper.retrieveRiskQuestionsByFormulaId(activeFormula.getOID());
		} else {
			this.postFailureRespEvent(RiskAnalysisConstants.NO_ACTIVE_FORMULA);
		}
   }
   
   /**
    * @param msg
    */
	private void postFailureRespEvent(String msg){
		CourtReferralPreConditionFailedResponseEvent preCondFailedEvent = new CourtReferralPreConditionFailedResponseEvent();
		preCondFailedEvent.setMessage(msg);
		MessageUtil.postReply(preCondFailedEvent); 
   }
   
   /**
	 * Returns Referrals From All Casefiles for a Juvenile
	 */
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
   
   /*
	 * 
	 */
	private static int getTJPCAgeOfFirstReferral( Iterator<JJSReferral> referralIter, String aJuvNumber )
	{

		int age = 0;
		JJSJuvenile juvenile = JJSJuvenile.find( aJuvNumber );
		
		SortedSet setReferralDate = new TreeSet();
		for( Iterator iter = referralIter;
				iter.hasNext(); /*empty*/ )
		{
			JJSReferral aReferral = (JJSReferral)iter.next();

			if( aReferral.getReferralDate() != null 
				&& 	aReferral.getReferralTypeInd() != null
				&& ( aReferral.getReferralTypeInd().equalsIgnoreCase("FM") ||
					 aReferral.getReferralTypeInd().equalsIgnoreCase("PF") ||
					 aReferral.getReferralTypeInd().equalsIgnoreCase("CS") ||
					 aReferral.getReferralTypeInd().equalsIgnoreCase("TR")
					)
				)
			{
				setReferralDate.add( aReferral.getReferralDate() );
			}
		}
		
		
		if ( juvenile.getBirthDate() != null && !setReferralDate.isEmpty() ) 
		{
			DateTime dateOfBirth = new DateTime(juvenile.getBirthDate());
			DateTime firstReferralDate = new DateTime((Date)setReferralDate.first());
			
			Years yearDifference = Years.yearsBetween(dateOfBirth, firstReferralDate);
			age = yearDifference.getYears();
		}
			
		
		return age;
	}
   
  
   /**
    * @param event
    * @roseuid 4357D9AF022B
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4357D9AF022D
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 4357D9AF0233
    */
   public void update(Object anObject) 
   {
    
   }
}
