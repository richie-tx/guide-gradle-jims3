//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\CheckResidentialPreConditionsCommand.java

package pd.juvenilecase.riskanalysis.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.juvenilecase.GetJuvenileTraitTypesEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilecase.reply.ResidentialPreConditionFailedResponseEvent;
import messaging.juvenilecase.reply.ResidentialPrefillResponseEvent;
import messaging.juvenilecase.reply.TraitTypeResponseEvent;
import messaging.riskanalysis.CheckResidentialPreConditionsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.RiskAnalysisConstants;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.JuvenileTrait;
import pd.juvenilecase.TraitType;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskFormula;

public class CheckResidentialPreConditionsCommand implements ICommand 
{
   
   /**
    * @roseuid 4357DD180205
    */
   public CheckResidentialPreConditionsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4357D9AF0229
    */
   public void execute(IEvent event) 
   {
	    /*
		Casefile case status = active; supervision type=residential supervision
		JPO should complete Part 4b anytime < 14 days of casefile assignment, 
		and can be by same JPO as Part1.  If Part 4b is done, part 4a will not be since Community 
		and Residential supervision are mutally exclusive.
		It is not required that 1, 2 or 3 be done prior to doing parts 4a, 4 b or 5.
		*/
	
		//IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
				
		CheckResidentialPreConditionsEvent preCondEvent = (CheckResidentialPreConditionsEvent) event;
		JuvenileCasefile juvCaseFile = JuvenileCasefile.find(preCondEvent.getCaseFileId());
				
		if(juvCaseFile != null)
		{
			if(	
				juvCaseFile.getCaseStatusId().equals("A") 
				&& 
				(juvCaseFile.getSupervisionCategoryId().equals(PDCodeTableConstants.CASEFILE_SUPERVISION_CAT_POST_ADJ_RES))
			  )
			{
				
				boolean riskAnalysisExist = PDRiskAnalysisHelper.doesRiskAnalysisExistWithinTheHour(juvCaseFile.getOID(), RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL);
				if (riskAnalysisExist) {
					ResidentialPreConditionFailedResponseEvent preCondFailedEvent = new ResidentialPreConditionFailedResponseEvent();
					preCondFailedEvent.setMessage( RiskAnalysisConstants.SAME_HOUR_SAME_CASEFILE);
					MessageUtil.postReply( preCondFailedEvent );
				}
				
				/*
				JUVENILE_TRAITS.Type=PlacementAdmissionReason
				Educational Issues should display all educational traits (Type=Educational performance and School Behavior) 
				for the juvenile.  This is read only, the user will not select.
				*/
				
				/*GetJuvenileTraitsByTypeEvent riskAnalyTrait =
					(GetJuvenileTraitsByTypeEvent) EventFactory.getInstance(
						JuvenileCaseControllerServiceNames.GETJUVENILETRAITSBYTYPE); 
					
				riskAnalyTrait.setJuvenileNum(preCondEvent.getJuvenileNumber());*/
				RiskFormula activeFormula = PDRiskAnalysisHelper.getActiveFormulaByAssessmentType(RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL);
				if (activeFormula == null){
					ResidentialPreConditionFailedResponseEvent preCondFailedEvent = new ResidentialPreConditionFailedResponseEvent();
					preCondFailedEvent.setMessage( RiskAnalysisConstants.NO_ACTIVE_FORMULA);
					MessageUtil.postReply( preCondFailedEvent );
				}
			
				List juvTraitList = new ArrayList();
				
				ResidentialPrefillResponseEvent resiPrefillResp = new ResidentialPrefillResponseEvent();

				retrieveTraits(RiskAnalysisConstants.RESIDENTIAL_SCHOOLATTENDANCE_TRAIT, resiPrefillResp);
				retrieveTraits(RiskAnalysisConstants.RESIDENTIAL_SCHOOLBEHAVIOR_TRAIT, resiPrefillResp);
				retrieveTraits(RiskAnalysisConstants.RESIDENTIAL_EDUCATIONALPERFORMANCE_TRAIT, resiPrefillResp);
								
				//resiPrefillResp.setRootTraitList(schoolTraitList);
				
				Iterator iter = JuvenileTrait.findAllByAttributeName("juvenileNum", preCondEvent.getJuvenileNumber());
				
				 
				while (iter.hasNext())
				{
					JuvenileTrait trait = (JuvenileTrait) iter.next();
					JuvenileTraitResponseEvent replyEvent = trait.getValueObject();
					juvTraitList.add(replyEvent);
				}
			
				resiPrefillResp.setJuvTraitsDetails(juvTraitList);
				MessageUtil.postReply(resiPrefillResp);
				
				//PDRiskAnalysisHelper.retrieveRiskQuestions(RiskAnalysisConstants.RISK_TYPE_RESIDENTIAL, dispatch);
				PDRiskAnalysisHelper.retrieveRiskQuestionsByFormulaId(activeFormula.getOID());
			}
			else
			{
				ResidentialPreConditionFailedResponseEvent preCondFailedEvent = new ResidentialPreConditionFailedResponseEvent();
				preCondFailedEvent.setMessage("Residential Analysis not found for Juvenile Number " + preCondEvent.getJuvenileNumber());
				MessageUtil.postReply(preCondFailedEvent); 
			}
		}
		else
		{
			ResidentialPreConditionFailedResponseEvent preCondFailedEvent = new ResidentialPreConditionFailedResponseEvent();
			preCondFailedEvent.setMessage("CaseFile " + preCondEvent.getCaseFileId() + " does not have case/supervision status Active for the Juvenile Number " + preCondEvent.getJuvenileNumber());
			MessageUtil.postReply(preCondFailedEvent); 
		}
   }
   
	   public void retrieveTraits(String traitTypeName, ResidentialPrefillResponseEvent resiPrefillResp)
	   {
			List traitList = new ArrayList();
			//RiskAssessmentResidentialTraitsByTypeEvent traitEvent = new RiskAssessmentResidentialTraitsByTypeEvent();
			//traitEvent.setTraitType(traitTypeName);
			TraitType parentTrait = TraitType.findByAttributeName("name",traitTypeName);
			
			if(parentTrait != null){
			resiPrefillResp.setRootTraitList(parentTrait.getValueObject());
			
			GetJuvenileTraitTypesEvent juvTraitEvent = (GetJuvenileTraitTypesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITTYPES); 
			juvTraitEvent.setTraitType(parentTrait.getOID().toString());
			Iterator i = TraitType.findByType(juvTraitEvent);
			
			while (i.hasNext())
			{
				TraitType traitType = (TraitType) i.next();
				TraitTypeResponseEvent replyEvent = traitType.getValueObject();
				replyEvent.setTopic(traitType.getParentTypeId());
				traitList.add(replyEvent);
			}
			
			resiPrefillResp.setChildTraitList(traitList);
		}
			//return traitList;
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
