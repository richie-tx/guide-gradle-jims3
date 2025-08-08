//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\SaveReferralAssessmentCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Calendar;

import messaging.juvenilecase.reply.RiskAssessmentListResponseEvent;
import messaging.riskanalysis.RiskQuestionAnswerEvent;
import messaging.riskanalysis.SaveDelinquencyHistEvent;
import messaging.riskanalysis.SaveReferralAssessmentEvent;
import messaging.riskanalysis.reply.RiskComputationReponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import naming.RiskAnalysisConstants;
import pd.exception.ComputationValidationException;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.riskanalysis.JuvenileDelinquencyHistory;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskAnalysis;
import pd.juvenilecase.riskanalysis.RiskAnalysisReferral;
import pd.juvenilecase.riskanalysis.RiskAnswer;
import pd.juvenilecase.riskanalysis.RiskResponse;

public class SaveReferralAssessmentCommand implements ICommand 
{
   
   /**
    * @roseuid 4342C33D0081
    */
   public SaveReferralAssessmentCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 433C3D3D0335
    */
   public void execute(IEvent event) throws ComputationValidationException 
   {
	   
	   SaveReferralAssessmentEvent saveRefAssessEvent = (SaveReferralAssessmentEvent)event;
	   
	   if ( saveRefAssessEvent.isUpdateOverRiddenStatus() ) {
	   
		   if ( (saveRefAssessEvent.getAssessmentID() != null) && (saveRefAssessEvent.getAssessmentID().length() > 0) ) {
			   PDRiskAnalysisHelper.updateReferralRiskAnalysisOverrideStatus(saveRefAssessEvent);
		   }
		   	   
	   } else {
		   if (saveRefAssessEvent.isNewReferral() == true) {
			   if (saveRefAssessEvent.isUpdate()) {
				   saveNewReferralUpdate(event);
			   } else {
				   saveNewReferral(event);
			   }
		   } else if (saveRefAssessEvent.isUpdate()){
			   saveOldReferralUpdate(event);
		   } else {
			   saveOldReferral(event);
		   }
	   }
		
   }
   
   public void saveNewReferralUpdate(IEvent event) throws ComputationValidationException {
	   
		SaveReferralAssessmentEvent saveRefAssessEvent = (SaveReferralAssessmentEvent)event;
		
		//Update Risk Analysis with latest Information
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveRefAssessEvent.getRiskAnalysisId() );
		//riskAnalysis.setEnteredDate(saveRefAssessEvent.getAssessmentDate());
		//riskAnalysis.setCreateUserID(saveRefAssessEvent.getJpoUserID());
		
		if ((saveRefAssessEvent.getModReason() != null) && (saveRefAssessEvent.getModReason().length() > 0)) {
        	riskAnalysis.setModReason(saveRefAssessEvent.getModReason());
        } else {
        	riskAnalysis.setModReason("");
        }
		
            boolean isDetFacility = false;
	   	
	   	//Insert RiskAnalysisId into JJS_DETENTION
	   	Iterator <JJSFacility> jjsFacilities = JJSFacility.findAll("bookingSupervisionNum", String.valueOf( riskAnalysis.getCasefileID() ));
		while ( jjsFacilities.hasNext() ) {
		    JJSFacility jjsFacility = jjsFacilities.next();
		    if ( jjsFacility.getDetainedFacility().equalsIgnoreCase("DET")) {
			isDetFacility = true;
			break;
		    }
		}
			
		IHome home=new Home();
		System.out.println("Id: " + riskAnalysis.getOID());
	   	home.bind(riskAnalysis);
	   	
	   	Calendar twentyFourHoursAgo = Calendar.getInstance();
		twentyFourHoursAgo.add(Calendar.HOUR, -24);
	   
	   	//Insert RiskAnalysisId into JJS_DETENTION
	   	if(riskAnalysis != null && riskAnalysis.getJuvenileNum() != null && riskAnalysis.getOID() != null){
	   	    
	   	 if(isDetFacility && RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL.equalsIgnoreCase(riskAnalysis.getAssessmentType())
				&& riskAnalysis.getEnteredDate().after(twentyFourHoursAgo.getTime()))
	   	 {	   	    
	   	    	String juvNum = String.valueOf(riskAnalysis.getJuvenileNum());
		   	String riskAnalysisId_OID = riskAnalysis.getOID();
		   	this.SaveRiskAnalysisIdToJJSDetention(juvNum, riskAnalysisId_OID);
	   	 }
	   	}

	   	
	   //Insert RiskAnalysisId into JJS_DETENTION
//	   	Iterator <JJSFacility> jjsFacilities = JJSFacility.findAll("bookingSupervisionNum", String.valueOf( riskAnalysis.getCasefileID() ));
//		while ( jjsFacilities.hasNext() ) {
//		    JJSFacility jjsFacility = jjsFacilities.next();
//		    if ( jjsFacility.getAdmitDate() != null
//			    && jjsFacility.getReleaseDate() == null ) {
//			jjsFacility.setRiskAnalysisId( riskAnalysis.getOID());
//			break;
//		    }
//		}
		
	    //Update Risk Analysis Referral with latest Information   	
	   	RiskAnalysisReferral riskReferral = RiskAnalysisReferral.findByRiskAnalysisId( saveRefAssessEvent.getRiskAnalysisId() );
	   	
        //Delete all old RiskAnalysisRecommendations by RiskAnalysisId
	   	PDRiskAnalysisHelper.bindDeleteRiskAnalysisRecommendations(riskAnalysis);
		//Delete all old RiskFinalScores by RiskAnalysisId
	   	PDRiskAnalysisHelper.bindDeleteRiskFinalScores(riskAnalysis);

		if (riskReferral != null){
		   	riskReferral.setAdditionalCharges(saveRefAssessEvent.getAdditionalCharges());
			riskReferral.setRiskAnalysis(riskAnalysis);
			riskReferral.setNewReferral(saveRefAssessEvent.isNewReferral());
	       
	        if ((saveRefAssessEvent.getRiskMandatoryDetentionCd() != null) && (saveRefAssessEvent.getRiskMandatoryDetentionCd().length() > 0)) {
	     	    riskReferral.setRiskMandatoryDetentionCd(saveRefAssessEvent.getRiskMandatoryDetentionCd());
	        } else {
	        	riskReferral.setRiskMandatoryDetentionCd("");
	        }
	        
	       
	        if (saveRefAssessEvent.isMoreThanOneFailure()) {
	     	    riskReferral.setMoreThanOneFailure(true);
	        } else {
	       	    riskReferral.setMoreThanOneFailure(false);
	        }
		}
        //PDRiskAnalysisHelper.setCalculatedScoresNewReferral(riskReferral, saveRefAssessEvent);
        RiskComputationReponseEvent rcre = PDRiskAnalysisHelper.setCalculatedScores(saveRefAssessEvent);
        
		PDRiskAnalysisHelper.bindDeleteRiskResponses(riskAnalysis);
		
		//Insert new RiskReponses by RiskAnalysisId
	    Enumeration events = saveRefAssessEvent.getRequests();
	    PDRiskAnalysisHelper.bindCreateRiskReponses(riskAnalysis.getOID(), events);

	    boolean mandatoryDetention = false;
	    
		// Retrieves all the responses associated with the RISK_ANALYSIS_ID
		Iterator<RiskAnswer> riskAnswers = RiskAnswer.findAll( 
				RiskAnalysisConstants.RISK_ANALYSIS_ID, riskAnalysis.getOID() );
		

		/*
		 * Temporarily used to manually set a recommendation by it's text
		 * This is not to stay in use long term, just until futher development is done to dynamically tie answers to recommendations
		 * Currently on used in the Detention Risk Analysis.
		 */
		while(riskAnswers.hasNext())
		{
			RiskAnswer riskAnswer = riskAnswers.next();
			if (riskAnswer.getQuestionText().equalsIgnoreCase("Mandatory Detention?")) 
			{
				if (riskAnswer.getAnswerText() != null && riskAnswer.getAnswerText().equalsIgnoreCase("YES")) {
					mandatoryDetention = true;
					break;
				}
			}
		}
		
		List recommendationList = null;
		if (mandatoryDetention){
			List forcedRecommendationLiterals = new ArrayList();
			forcedRecommendationLiterals.add(RiskAnalysisConstants.REFERRAL_MANDATORY_DETENTION_RECOMMENDATION);

			recommendationList = PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamicallyWithForcedRecommendationsAssessmentTypeAndRecommendationLiteral(riskAnalysis, forcedRecommendationLiterals);
		} else {
			//PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(riskAnalysis, RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL, false);
			recommendationList = PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(rcre.getTotalScores(), Integer.toString(riskAnalysis.getRiskFormulaId()), false);

		}
		
		//PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(riskAnalysis);
		PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(rcre.getTotalScores(), recommendationList);

   }
   
   public void saveNewReferral(IEvent event) throws ComputationValidationException {
	   
		SaveReferralAssessmentEvent saveRefAssessEvent = (SaveReferralAssessmentEvent)event;
		
		RiskAnalysis riskAnalysis = new RiskAnalysis();
	    
		riskAnalysis.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL);
		riskAnalysis.setJuvenileNum(saveRefAssessEvent.getJuvenileNum());
		riskAnalysis.setCasefileID(Integer.parseInt(saveRefAssessEvent.getCasefileID()));
		riskAnalysis.setEnteredDate(new Date());
		//riskAnalysis.setCreateUserID(saveRefAssessEvent.getJpoUserID());
		riskAnalysis.setRiskFormulaId(Integer.parseInt(saveRefAssessEvent.getRiskFormulaId()));
		riskAnalysis.setRecommendationOveridden(false);
		riskAnalysis.setCustody(true);
			
		IHome home=new Home();
	   	home.bind(riskAnalysis);
	   	
	   	boolean isDetFacility = false;
	   	
	   	//Insert RiskAnalysisId into JJS_DETENTION
	   	Iterator <JJSFacility> jjsFacilities = JJSFacility.findAll("bookingSupervisionNum", String.valueOf( riskAnalysis.getCasefileID() ));
		while ( jjsFacilities.hasNext() ) {
		    JJSFacility jjsFacility = jjsFacilities.next();
		    if ( jjsFacility.getDetainedFacility().equalsIgnoreCase("DET")) {
			isDetFacility = true;
			break;
		    }
		}
	   	

	   	Calendar twentyFourHoursAgo = Calendar.getInstance();
		twentyFourHoursAgo.add(Calendar.HOUR, -24);
	   	
		//Insert RiskAnalysisId into JJS_DETENTION
	   	if(riskAnalysis != null && riskAnalysis.getJuvenileNum() != null && riskAnalysis.getOID() != null){
	   	    
	   	 if(isDetFacility && RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL.equalsIgnoreCase(riskAnalysis.getAssessmentType()) 
				&& riskAnalysis.getEnteredDate().after(twentyFourHoursAgo.getTime()))
	   	 {
	   	    
	   	    	String juvNum = String.valueOf(riskAnalysis.getJuvenileNum());
		   	String riskAnalysisId_OID = riskAnalysis.getOID();
		   	this.SaveRiskAnalysisIdToJJSDetention(juvNum, riskAnalysisId_OID);
	   	 }
	   	}
	   	
	   	
//	   	Iterator <JJSFacility> jjsFacilities = JJSFacility.findAll("bookingSupervisionNum", String.valueOf( riskAnalysis.getCasefileID() ));
//		while ( jjsFacilities.hasNext() ) {
//		    JJSFacility jjsFacility = jjsFacilities.next();
//		    if ( jjsFacility.getAdmitDate() != null
//			    && jjsFacility.getReleaseDate() == null ) {
//			jjsFacility.setRiskAnalysisId( riskAnalysis.getOID());
//			break;
//		    }
//		}
		
	   	RiskAnalysisReferral riskReferral = new RiskAnalysisReferral();
		riskReferral.setAdditionalCharges(saveRefAssessEvent.getAdditionalCharges());
        riskReferral.setRiskAnalysis(riskAnalysis);
        riskReferral.setNewReferral(saveRefAssessEvent.isNewReferral());
        
        if ((saveRefAssessEvent.getRiskMandatoryDetentionCd() != null) && (saveRefAssessEvent.getRiskMandatoryDetentionCd().length() > 0)) {
        	riskReferral.setRiskMandatoryDetentionCd(saveRefAssessEvent.getRiskMandatoryDetentionCd());
        }
        
        if (saveRefAssessEvent.isMoreThanOneFailure()) {
        	riskReferral.setMoreThanOneFailure(true);
        } else {
        	riskReferral.setMoreThanOneFailure(false);
        }
        
       // Used passed in saved values to store new record in Juvenile Deliquency history table instead
       JuvenileDelinquencyHistory jdh=null;
       Enumeration eventsAll = saveRefAssessEvent.getRequests();
       while (eventsAll.hasMoreElements())
		{
	    	Object obj=eventsAll.nextElement();
	    	if(obj instanceof SaveDelinquencyHistEvent){
				SaveDelinquencyHistEvent histEvent = (SaveDelinquencyHistEvent)obj;
				jdh=new JuvenileDelinquencyHistory();
				jdh.setRiskAnalysisId(riskAnalysis.getOID());
				jdh.setAgeFirstReferral(stringToInt(histEvent.getAgeFirstReferred()));
				jdh.setCapFelonyTotal(stringToInt(histEvent.getTotalCapitalFelony()));
				jdh.setFelony1Total(stringToInt(histEvent.getTotalFelony1()));
				jdh.setFelony2Total(stringToInt(histEvent.getTotalFelony2()));
				jdh.setFelony3Total(stringToInt(histEvent.getTotalFelony3()));
				jdh.setJailFelonyTotal(stringToInt(histEvent.getTotalStateJailFelony()));
//				jdh.setJuvenileNumber(saveRefAssessEvent.getJuvenileNum());
				jdh.setLevelTotal(stringToInt(histEvent.getTotalLevel()));
				jdh.setMisdABTotal(stringToInt(histEvent.getTotalClassAB()));
				jdh.setMisdCTotal(stringToInt(histEvent.getTotalClassC()));
				jdh.setReferralHistoryTotal(stringToInt(histEvent.getTotalReferralsHistory()));
				jdh.setScoOffensesTotal(stringToInt(histEvent.getTotalStatusCO()));
				jdh.setSeriousnessIndex(stringToInt(histEvent.getSeriousnessIndex()));
				jdh.setTotalOffenses(stringToInt(histEvent.getTotalOffenses()));
		       	home.bind(jdh);
				break;
	    	}
		}
		// END COMMENT
       

		
  	
        saveRefAssessEvent.setRiskAnalysisId(riskAnalysis.getOID());
		//PDRiskAnalysisHelper.setCalculatedScoresNewReferral(riskReferral, saveRefAssessEvent);
		RiskComputationReponseEvent rcre = PDRiskAnalysisHelper.setCalculatedScores(saveRefAssessEvent);
		
		riskReferral.setCustodyStatus(RiskAnalysisConstants.CUSTODY) ;
		riskReferral.setTotalCapitalFelony(jdh.getCapFelonyTotal() );
		riskReferral.setTotalClassAB(jdh.getMisdABTotal());
		riskReferral.setTotalClassC(jdh.getMisdCTotal());
		riskReferral.setTotalCurrentStatus(jdh.getScoOffensesTotal());
		riskReferral.setTotalFelony1(jdh.getFelony1Total());
		riskReferral.setTotalFelony2(jdh.getFelony2Total());
		riskReferral.setTotalFelony3(jdh.getFelony3Total());
		int total = jdh.getSeriousnessIndex() + saveRefAssessEvent.getAdditionalCharges(); 
		riskReferral.setTotalLevel(total);
		riskReferral.setTotalReferralsHistory( jdh.getReferralHistoryTotal());
		riskReferral.setTotalStateJailFelony(jdh.getJailFelonyTotal());
		riskReferral.setTotalStatusCO(jdh.getScoOffensesTotal());
		riskReferral.setSeriousnessIndex(jdh.getSeriousnessIndex());
		riskReferral.setAdditionalCharges(saveRefAssessEvent.getAdditionalCharges());
		riskReferral.setOnProbation( Boolean.parseBoolean(saveRefAssessEvent.getProbationStatus()) );
		riskReferral.setVOPPendingCourt(Boolean.parseBoolean(saveRefAssessEvent.getVopPendingCourt()));
		riskReferral.setPendingCourt(Boolean.parseBoolean(saveRefAssessEvent.getPendingCourt()));	
		
		total = 0;
		if (riskReferral.isOnProbation()){
			total = total + 3;
		}
		if (riskReferral.isPendingCourt()){
			total = total + 3;
		}
		riskReferral.setTotalSupervision(total);
		
	    Enumeration events = saveRefAssessEvent.getRequests();
	    PDRiskAnalysisHelper.bindCreateRiskReponses(riskAnalysis.getOID(), events);
	    
	    boolean mandatoryDetention = false;
	    
		// Retrieves all the responses associated with the RISK_ANALYSIS_ID
		Iterator riskAnswersIter = RiskAnswer.findAll( 
				RiskAnalysisConstants.RISK_ANALYSIS_ID, riskAnalysis.getOID() );
		List <RiskAnswer> riskAnswers = CollectionUtil.iteratorToList(riskAnswersIter);

		/*
		 * Temporarily used to manually set a recommendation by it's text
		 * This is not to stay in use long term, just until futher development is done to dynamically tie answers to recommendations
		 * Currently used in the Detention Risk Analysis only.
		 */
		RiskAnswer riskAnswer = null;
		for (int i = 0; i < riskAnswers.size(); i++) 
		{
			riskAnswer = riskAnswers.get(i);
			if (riskAnswer.getQuestionText().equalsIgnoreCase("Mandatory Detention?")) 
			{
				if (riskAnswer.getAnswerText() != null && riskAnswer.getAnswerText().equalsIgnoreCase("YES")) {
					mandatoryDetention = true;
					break;
				}
			}
		}

		List recommendationList = null;
		if (mandatoryDetention){
			List forcedRecommendationLiterals = new ArrayList();
			//change this to loop through newreferral recommendations
			forcedRecommendationLiterals.add(RiskAnalysisConstants.REFERRAL_MANDATORY_DETENTION_RECOMMENDATION);

			recommendationList = PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamicallyWithForcedRecommendationsAssessmentTypeAndRecommendationLiteral(riskAnalysis, forcedRecommendationLiterals);
		} else {
			//PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(riskAnalysis, RiskAnalysisConstants.RISK_TYPE_CUSTODY_REFERRAL, false);
			recommendationList = PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamically(rcre.getTotalScores(), Integer.toString(riskAnalysis.getRiskFormulaId()), false);
		
		}

		//PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(riskAnalysis);
		PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(rcre.getTotalScores(), recommendationList);
		
//		JuvenileCasefile myCasefile=JuvenileCasefile.find(saveRefAssessEvent.getCasefileID());
//		myCasefile.setIsReferralRiskNeeded(false);
		//PDRiskAnalysisHelper.turnRiskFlagOffOnAllCasefiles(riskAnalysis.getJuvenileNum(),riskAnalysis.getAssessmentType(),myCasefile.getSupervisionCategoryId());
		PDRiskAnalysisHelper.turnRiskFlagOffOnAllCasefiles(riskAnalysis);
		PDRiskAnalysisHelper.setFlagsBasedOnNewRiskAssessmentType(null, riskAnalysis.getAssessmentType(), String.valueOf(riskAnalysis.getCasefileID()));
   
   }
   
   public void saveOldReferral(IEvent event) throws ComputationValidationException {
	   
	   SaveReferralAssessmentEvent saveRefAssessEvent = (SaveReferralAssessmentEvent)event;
		
		RiskAnalysis riskAnalysis = new RiskAnalysis();
	    //boolean isCustody = isCustody(saveRefAssessEvent.getRequests());
		 //riskAnalysis.setCustody(isCustody);
		 
		riskAnalysis.setCustody(false);
		
		riskAnalysis.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_NON_CUSTODY_REFERRAL);
		riskAnalysis.setJuvenileNum(saveRefAssessEvent.getJuvenileNum());
		riskAnalysis.setCasefileID(Integer.parseInt(saveRefAssessEvent.getCasefileID()));
		riskAnalysis.setEnteredDate(new Date());
		//riskAnalysis.setCreateUserID(saveRefAssessEvent.getJpoUserID());
		riskAnalysis.setRiskFormulaId(Integer.parseInt(saveRefAssessEvent.getRiskFormulaId()));
			
		IHome home=new Home();
	   	home.bind(riskAnalysis);
		RiskAnalysisReferral riskReferral = new RiskAnalysisReferral();
		 riskReferral.setAdditionalCharges(saveRefAssessEvent.getAdditionalCharges());
		 riskReferral.setRiskAnalysis(riskAnalysis);

       // Used passed in saved values to store new record in Juvenile Deliquency history table instead
       JuvenileDelinquencyHistory jdh=null;
       Enumeration eventsAll = saveRefAssessEvent.getRequests();
       while (eventsAll.hasMoreElements())
		{
	    	Object obj=eventsAll.nextElement();
	    	if(obj instanceof SaveDelinquencyHistEvent){
				SaveDelinquencyHistEvent histEvent = (SaveDelinquencyHistEvent)obj;
				jdh=new JuvenileDelinquencyHistory();
				jdh.setRiskAnalysisId(riskAnalysis.getOID().toString());
				jdh.setAgeFirstReferral(stringToInt(histEvent.getAgeFirstReferred()));
				jdh.setCapFelonyTotal(stringToInt(histEvent.getTotalCapitalFelony()));
				jdh.setFelony1Total(stringToInt(histEvent.getTotalFelony1()));
				jdh.setFelony2Total(stringToInt(histEvent.getTotalFelony2()));
				jdh.setFelony3Total(stringToInt(histEvent.getTotalFelony3()));
				jdh.setJailFelonyTotal(stringToInt(histEvent.getTotalStateJailFelony()));
//				jdh.setJuvenileNumber(saveRefAssessEvent.getJuvenileNum());
				jdh.setLevelTotal(stringToInt(histEvent.getTotalLevel()));
				jdh.setMisdABTotal(stringToInt(histEvent.getTotalClassAB()));
				jdh.setMisdCTotal(stringToInt(histEvent.getTotalClassC()));
				jdh.setReferralHistoryTotal(stringToInt(histEvent.getTotalReferralsHistory()));
				jdh.setScoOffensesTotal(stringToInt(histEvent.getTotalStatusCO()));
				jdh.setSeriousnessIndex(stringToInt(histEvent.getSeriousnessIndex()));
				jdh.setTotalOffenses(stringToInt(histEvent.getTotalOffenses()));
				break;
	    	}	    	
		}
		// END COMMENT
        IHome home2 = new Home();
      	home2.bind(jdh);
		
        saveRefAssessEvent.setRiskAnalysisId(riskAnalysis.getOID());
		//PDRiskAnalysisHelper.setCalculatedScoresReferral(riskReferral, saveRefAssessEvent);
		RiskComputationReponseEvent rcre = PDRiskAnalysisHelper.setCalculatedScores(saveRefAssessEvent);

		List recommendations = new ArrayList();
		recommendations.add(RiskAnalysisConstants.REFFERAL_NON_CUSTODY_RECOMMENDATION);
		recommendations = PDRiskAnalysisHelper.bindCreateRiskAnalysisRecommendationsDynamicallyWithForcedRecommendationsByAssessmentType(riskAnalysis, recommendations);

	    Enumeration events = saveRefAssessEvent.getRequests();


	    while (events.hasMoreElements())
		{
	    	Object obj = events.nextElement();
			if( obj instanceof RiskQuestionAnswerEvent ) {	
				RiskQuestionAnswerEvent riskReqEvent = (RiskQuestionAnswerEvent)obj;
	    		
				RiskResponse riskResponse =  new RiskResponse();
				riskResponse.setRiskAnalysisId(riskAnalysis.getOID());
				riskResponse.setText(riskReqEvent.getText());
				riskResponse.setWeightedResponseID(String.valueOf(riskReqEvent.getWeightedResponseID()));
			}
		}
	    
		/*There will be one indicator (boolean) per type of Risk Assessment on the casefile 
		 * (These are new attributes that need to be added to the casefile table). Whenever a particular 
		 * type of Risk Assessment is done, you go in and set the indicator to denote that the assessment is done...
		 *  example, we will have a RiskReferralCompleted boolean field on casefile. Whenever a new RiskReferralAssessment 
		 * is done, this flag will be set to true on the casefile that we chose
		 */
		 
		 //TBD.. Need to add columns for all the assessments in JuvenileCaseFile table and then modify this entity
		//JuvenileCasefile juvCaseFile = JuvenileCasefile.find(saveRefAssessEvent.getCasefileID());
		//juvCaseFile.setRiskAssessmentReferral(true);
		
		/* RiskRecommendationResponseEvent riskRecommendScore = new RiskRecommendationResponseEvent();
		riskRecommendScore.setRiskAnalysisRecommendation(riskAnalysis.getRecommendation());
		riskRecommendScore.setRiskAnalysisScore(riskAnalysis.getFinalScore());
		riskRecommendScore.setRiskAnalysisId(riskAnalysis.getOID().toString());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		dispatch.postEvent(riskRecommendScore);
		*/
	    
		//PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(riskAnalysis);
	    PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(rcre.getTotalScores(), recommendations);

//		JuvenileCasefile myCasefile=JuvenileCasefile.find(saveRefAssessEvent.getCasefileID());
//		if(myCasefile!=null){
//			myCasefile.setIsReferralRiskNeeded(false);
//			PDRiskAnalysisHelper.turnRiskFlagOffOnAllCasefiles(riskAnalysis.getJuvenileNum(),riskAnalysis.getAssessmentType(),myCasefile.getSupervisionCategoryId());
//		}
		PDRiskAnalysisHelper.turnRiskFlagOffOnAllCasefiles(riskAnalysis);
		PDRiskAnalysisHelper.setFlagsBasedOnNewRiskAssessmentType(null, riskAnalysis.getAssessmentType(), String.valueOf(riskAnalysis.getCasefileID()));

   }
   public void saveOldReferralUpdate(IEvent event) throws ComputationValidationException {
	   
	   SaveReferralAssessmentEvent saveRefAssessEvent = (SaveReferralAssessmentEvent)event;
	   
		//Update Risk Analysis with latest Information
		RiskAnalysis riskAnalysis = RiskAnalysis.find( saveRefAssessEvent.getRiskAnalysisId() );
		//riskAnalysis.setCreateUserID(saveRefAssessEvent.getJpoUserID());
		
		if ((saveRefAssessEvent.getModReason() != null) && (saveRefAssessEvent.getModReason().length() > 0)) {
			riskAnalysis.setModReason(saveRefAssessEvent.getModReason());
		} else {
			riskAnalysis.setModReason("");
		}
			
		IHome home=new Home();
	   	home.bind(riskAnalysis);		
			
	    //Update Risk Analysis Referral with latest Information   	
	   	//RiskAnalysisReferral riskReferral = RiskAnalysisReferral.findByRiskAnalysisId( saveRefAssessEvent.getRiskAnalysisId() );

        //DAG - We don't have to delete the recommendation on update since it's always the default recommendation.
	   	//Delete all old RiskAnalysisRecommendations by RiskAnalysisId
	    //	PDRiskAnalysisHelper.bindDeleteRiskAnalysisRecommendations(riskAnalysis);
	   	
		//Delete all old RiskFinalScores by RiskAnalysisId
	   	PDRiskAnalysisHelper.bindDeleteRiskFinalScores(riskAnalysis);
 		
        saveRefAssessEvent.setRiskAnalysisId(riskAnalysis.getOID());
		//PDRiskAnalysisHelper.setCalculatedScoresReferral(riskReferral, saveRefAssessEvent);
        RiskComputationReponseEvent rcre = PDRiskAnalysisHelper.setCalculatedScores(saveRefAssessEvent);
		
		PDRiskAnalysisHelper.bindDeleteRiskResponses(riskAnalysis);
		
	    Enumeration events = saveRefAssessEvent.getRequests();
	    PDRiskAnalysisHelper.bindCreateRiskReponses(riskAnalysis.getOID(), events);
	    
		//PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(riskAnalysis);
	    //Recommendation never changes.  On update retrieve from risk analysis instead of deleting and re-inserting.
	    Collection coll = riskAnalysis.getRecommendations();;
	    List recommendationList = CollectionUtil.iteratorToList(coll.iterator());
	    
	    PDRiskAnalysisHelper.sendRecommendationsAndFinalScores(rcre.getTotalScores(), recommendationList);


   }
   
   private int stringToInt(String aVal){
   	
   	if(aVal==null || aVal.trim().equals("") || aVal.trim().equals("0.0")){
   		return 0;
   	}
   	try{
   		return (new Integer(aVal)).intValue();
   	}
   	catch(Exception e){
   		return 0;
   	}
   }
   
   public void SaveRiskAnalysisIdToJJSDetention(String juvenileNumber, String riskAnalysisId){
       
       Iterator <JJSFacility> jjsFacilities = JJSFacility.findAll("juvenileNumber", juvenileNumber);
  	JJSFacility jjsFacilityLatestRecord = this.getLatestDetentionRecord(jjsFacilities, juvenileNumber);
  	
  	if(jjsFacilityLatestRecord != null){
	   	jjsFacilityLatestRecord.setRiskAnalysisId(riskAnalysisId);	   	
	   	IHome homeFacility = new Home();
	   	homeFacility.bind(jjsFacilityLatestRecord);
	   	System.out.println("RiskAnalysisId: " + riskAnalysisId + " was saved to JJSDetention successfully");
  	} else {
  	    
  	  System.out.println("=== Could not save RiskAnalysisId: " + riskAnalysisId + " to JJSDetention. There is no matching facility record. ====");
  	  
  	}  	
  	
   }
   
   public JJSFacility getLatestDetentionRecord(Iterator facilitiesList, String juvenileNumber){       
       
       ArrayList<JJSFacility> facilityRecords = new ArrayList<JJSFacility>();
       JJSFacility latestFacilityRecord = null;
       
	while ( facilitiesList.hasNext() ) {
	    JJSFacility jjsFacility = (JJSFacility)facilitiesList.next();
	    
	    if ( jjsFacility.getAdmitDate() != null && jjsFacility.getReleaseDate() == null 
		    && jjsFacility.getJuvenileNumber() != null && jjsFacility.getJuvenileNumber().equals(juvenileNumber)) {
		
		facilityRecords.add(jjsFacility);
	    }
	}
	
	//if there're multiple records sort and get the latest
	if(facilityRecords.size() > 1){
	    
	    Date admitTime = StringToTime("00:00:00");      	
		Date facAdmitTime = null;
		Iterator facIter = facilityRecords.iterator();		
		
		    while(facIter.hasNext()){		
			JJSFacility fac = (JJSFacility)facIter.next();		
			
			if(fac.getAdmitTime() != null && admitTime != null){
			    facAdmitTime = StringToTime(fac.getAdmitTime());
			    
			    if(facAdmitTime.after(admitTime)){
				admitTime = facAdmitTime;
				latestFacilityRecord = fac;
			    }
			}
			
		    }	    
	} else {
	    
	    if(facilityRecords.size() == 1){
		
		 latestFacilityRecord = facilityRecords.get(0);
	    }	   
	}
	
	    
	return latestFacilityRecord;
   }
   
   public Date StringToDate(String s){

       Date result = null;
       try{
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           result  = dateFormat.parse(s);
       }

       catch(ParseException e){
           e.printStackTrace();

       }
       return result ;
   }
   
   public Date StringToTime(String s){

       Date result = null;
       try{
           SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
           result  = dateFormat.parse(s);
       }

       catch(ParseException e){
           e.printStackTrace();

       }
       return result ;
   }
   
   /**
	 * @param events
	 */
	/* private boolean isCustody(Enumeration events)
	{
		while( events.hasMoreElements() ) {
			Object obj = events.nextElement();
			if( obj instanceof RiskQuestionAnswerEvent ) {	
				RiskQuestionAnswerEvent riskReqEvent = (RiskQuestionAnswerEvent)obj;
				if(riskReqEvent.getQuestionNumber() == 5 && 
						riskReqEvent.getText().equals(RiskAnalysisConstants.NON_CUSTODY)){
					return false;
				}
			}
		}
		return true;
	}*/

/**
    * @param event
    * @roseuid 433C3D3D033E
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 433C3D3D0340
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 433C3D3D0342
    */
   public void update(Object anObject) 
   {
    
   }

}
