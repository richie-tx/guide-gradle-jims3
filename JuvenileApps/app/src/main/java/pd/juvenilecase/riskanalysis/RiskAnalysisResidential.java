package pd.juvenilecase.riskanalysis;

import messaging.juvenilecase.GetJuvenileTraitTypesEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilecase.reply.TraitTypeResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.JuvenileCaseControllerServiceNames;
import naming.RiskAnalysisConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import pd.juvenilecase.JuvenileTrait;
import pd.juvenilecase.TraitType;

/**
* @roseuid 434C02500003
*/
public class RiskAnalysisResidential extends PersistentObject {
	/**
	* Properties for riskAnalysis
	* @referencedType pd.juvenilecase.RiskAnalysis
	* @detailerDoNotGenerate false
	*/
	private RiskAnalysis riskAnalysis = null;
	private String juvenileNumber;
	private int totalReview;
	private int totalEvaluation;
	private int totalSchoolRecords;
	private int educationalIssues;
	private String riskAnalysisId;

	/**
	* @roseuid 434C02500003
	*/
	public RiskAnalysisResidential() {
	}
	/**
	* @return 
	*/
	public int getEducationalIssues() {
		fetch();
		return educationalIssues;
	}
	/**
	* @return 
	*/
	public String getJuvenileNumber() {
		fetch();
		return juvenileNumber;
	}
	/**
	* @return 
	*/
	/**
	* @return 
	*/
	public RiskAnalysis getRiskAnalysis() {
		fetch();
		initRiskAnalysis();
		return riskAnalysis;
	}
	/**
	* @return 
	*/
	public int getTotalEvaluation() {
		fetch();
		return totalEvaluation;
	}
	/**
	* @return 
	*/
	public int getTotalReview() {
		fetch();
		return totalReview;
	}
	/**
	* @return 
	*/
	public int getTotalSchoolRecords() {
		fetch();
		return totalSchoolRecords;
	}
	/**
	* @param i
	*/
	public void setEducationalIssues(int i) {
		if (this.educationalIssues != i) {
			markModified();
		}
		educationalIssues = i;
	}
	/**
	* @param string
	*/
	public void setJuvenileNumber(String string) {
		if (this.juvenileNumber == null || !this.juvenileNumber.equals(string)) {
			markModified();
		}
		juvenileNumber = string;
	}
	/**
	* @param i
	*/
	public void setTotalEvaluation(int i) {
		if (this.totalEvaluation != i) {
			markModified();
		}
		totalEvaluation = i;
	}
	/**
	* @param i
	*/
	public void setTotalReview(int i) {
		if (this.totalReview != i) {
			markModified();
		}
		totalReview = i;
	}
	/**
	* @param i
	*/
	public void setTotalSchoolRecords(int i) {
		if (this.totalSchoolRecords != i) {
			markModified();
		}
		totalSchoolRecords = i;
	}
	/**
	* Set the reference value to class :: pd.juvenilecase.RiskAnalysis
	*/
	public void setRiskAnalysisId(String riskAnalysisId) {
		if (this.riskAnalysisId == null || !this.riskAnalysisId.equals(riskAnalysisId)) {
			markModified();
		}
		riskAnalysis = null;
		this.riskAnalysisId = riskAnalysisId;
	}
	/**
	* Get the reference value to class :: pd.juvenilecase.RiskAnalysis
	*/
	public String getRiskAnalysisId() {
		fetch();
		return riskAnalysisId;
	}
	/**
	* Initialize class relationship to class pd.juvenilecase.RiskAnalysis
	*/
	private void initRiskAnalysis() {
		if (riskAnalysis == null) {
			riskAnalysis = (RiskAnalysis) new mojo.km.persistence.Reference(riskAnalysisId, RiskAnalysis.class).getObject();
		}
	}
	/**
	* set the type reference for class member riskAnalysis
	*/
	public void setRiskAnalysis(RiskAnalysis riskAnalysis) {
		if (this.riskAnalysis == null || !this.riskAnalysis.equals(riskAnalysis)) {
			markModified();
		}
		if (riskAnalysis.getOID() == null) {
			new mojo.km.persistence.Home().bind(riskAnalysis);
		}
		setRiskAnalysisId("" + riskAnalysis.getOID());
		this.riskAnalysis = (RiskAnalysis) new mojo.km.persistence.Reference(riskAnalysis).getObject();
	}
	
	public static int getEducationTotalForResidential(String juvenileNum) {
			 int total = 0;
			Collection eduTraits  = new ArrayList();
   		
			retrieveTraitsAndDescriptionsByType(RiskAnalysisConstants.RESIDENTIAL_SCHOOLATTENDANCE_TRAIT, eduTraits);
			retrieveTraitsAndDescriptionsByType(RiskAnalysisConstants.RESIDENTIAL_SCHOOLBEHAVIOR_TRAIT, eduTraits);
			retrieveTraitsAndDescriptionsByType(RiskAnalysisConstants.RESIDENTIAL_EDUCATIONALPERFORMANCE_TRAIT, eduTraits);
		    retrieveTraitsAndDescriptionsByType(RiskAnalysisConstants.RESIDENTIAL_SCHOOLPROGRAMTYPE_TRAIT, eduTraits);
		    
			Map juvTraitMap = retrieveAllTraitsForJuvenile(juvenileNum);
			Iterator ite = eduTraits.iterator();
			while(ite.hasNext()) {
				TraitTypeResponseEvent tEvt = (TraitTypeResponseEvent)ite.next();
				if(juvTraitMap.containsKey(tEvt.getTraitTypeId())) {
					total += Integer.parseInt(tEvt.getRiskPoints());
				}
			}
			 return total;
		}
	
		public static Map retrieveAllTraitsForJuvenile(String juvenileNum)
	   {
			Map juvTraitMap = new HashMap();
			Iterator iter = JuvenileTrait.findAllByAttributeName("juvenileNum", juvenileNum);
			while (iter.hasNext())
			{
				JuvenileTrait trait = (JuvenileTrait) iter.next();
				JuvenileTraitResponseEvent replyEvent = trait.getValueObject();
				juvTraitMap.put(replyEvent.getTraitTypeId(), replyEvent);
			}
			return juvTraitMap;
	   }
	   
		private static void retrieveTraitsAndDescriptionsByType(String traitTypeName, Collection eduTraits) {
			TraitType parentTrait = TraitType.findByAttributeName("name",traitTypeName);
	
			GetJuvenileTraitTypesEvent juvTraitEvent = (GetJuvenileTraitTypesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITTYPES); 
			juvTraitEvent.setTraitType(parentTrait.getOID().toString());
			Iterator i = TraitType.findByType(juvTraitEvent);
	
			while (i.hasNext())
			{
				TraitType traitType = (TraitType) i.next();
				TraitTypeResponseEvent replyEvent = traitType.getValueObject();
				replyEvent.setTopic(traitType.getParentTypeId());
				eduTraits.add(replyEvent);
			}
		}
		static public RiskAnalysisResidential findByRiskAnalysisId(String attributeValue) {
			IHome home = new Home();
			return (RiskAnalysisResidential)home.find(RiskAnalysisConstants.RISK_ANALYSIS_ID, attributeValue, RiskAnalysisResidential.class);
		}
}
