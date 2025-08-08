package pd.juvenilecase.riskanalysis.riskanalysiscomputation;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import naming.UIConstants;

import pd.juvenilecase.riskanalysis.PDRiskAnalysisHelper;
import pd.juvenilecase.riskanalysis.RiskCategory;
import pd.juvenilecase.riskanalysis.RiskQuestions;

import messaging.riskanalysis.RiskQuestionAnswerEvent;
import mojo.km.messaging.Composite.CompositeRequest;
import mojo.km.util.CollectionUtil;

public class RiskComputationUtil {
	
	public static int calculateScore(HashMap hm){
		
		Collection coll = hm.values();
		
		List <Integer> weightList = CollectionUtil.iteratorToList(coll.iterator());
		
		Integer thisWeight = new Integer(0);
		Integer finalScore = new Integer(0);
		
		for (int i = 0; i < weightList.size(); i++) {
			thisWeight = weightList.get(i);
			if (thisWeight != null){
				finalScore = finalScore + thisWeight;
			}
		}
		
		return finalScore;
	}
	
	/**
	 * Build a map of risk answer weights using uestionId as key.
	 * @param cr
	 * @return
	 */
	public static HashMap getQuestionWeights(CompositeRequest cr) {
		
		Enumeration riskQuestionAnswerEvents = cr.getRequests();
		HashMap hmQuestionWeights = new HashMap ( ) ; 
		
		List <RiskQuestionAnswerEvent> questionAnswerReList = CollectionUtil.enumerationToList(riskQuestionAnswerEvents);
		
		RiskQuestionAnswerEvent riskQAEvent = null;
		Integer mapWeightTotal = null;
		int totQuestionWeight = 0;
		Object obj;
		
		for (int i = 0; i < questionAnswerReList.size(); i++) {
			obj = questionAnswerReList.get(i);
			if (!(obj instanceof RiskQuestionAnswerEvent)){
				continue;
			}
			riskQAEvent = questionAnswerReList.get(i);
			
			mapWeightTotal = (Integer) hmQuestionWeights.get(riskQAEvent.getQuestionId());
			
			if (mapWeightTotal == null){
				mapWeightTotal = new Integer(0);
			}
			
//			if ( (riskQAEvent.getControlCode() != null && riskQAEvent.getControlCode().length() > 0)
//					&& (riskQAEvent.getUiControlType().equalsIgnoreCase(UIConstants.TEXTBOX))
//					&& (PDRiskAnalysisHelper.valueIsAllDigits(riskQAEvent.getAnswerText())) ) {
//				if (riskQAEvent.getAnswerText() != null && riskQAEvent.getAnswerText().length() > 0 ) {
//					thisQuestionWeight = Integer.parseInt(riskQAEvent.getAnswerText());
//				} 
//				totQuestionWeight = mapWeightTotal.intValue() + thisQuestionWeight;
//			} else {
//				//if logic passes, it means a textbox is being used which stores a user defined numeric value 
//				//from which weights are based on.
//				if ( riskQAEvent.getUiControlType().equalsIgnoreCase(UIConstants.TEXTBOX)
//						&& riskQAEvent.getWeight() == 0
//						&& PDRiskAnalysisHelper.valueIsAllDigits(riskQAEvent.getAnswerText())) {
//					totQuestionWeight = mapWeightTotal.intValue() + Integer.parseInt(riskQAEvent.getAnswerText());
//				} else {
//////////			totQuestionWeight = mapWeightTotal.intValue() + riskQAEvent.getWeight();
//				}
//			}
			//totQuestionWeight = mapWeightTotal.intValue() + riskQAEvent.getWeight();
			if ( (riskQAEvent.getControlCode() != null && riskQAEvent.getControlCode().length() > 0)
				&& riskQAEvent.getUiControlType().equalsIgnoreCase(UIConstants.TEXTBOX)
				&& riskQAEvent.isNumeric()
				&& (riskQAEvent.getAnswerText() != null && riskQAEvent.getAnswerText().length() > 0 )){ 
					totQuestionWeight = mapWeightTotal.intValue() + Integer.parseInt(riskQAEvent.getAnswerText());
			} else {
				totQuestionWeight = mapWeightTotal.intValue() + riskQAEvent.getWeight();
			}
			hmQuestionWeights.put(riskQAEvent.getQuestionId(), new Integer(totQuestionWeight));
		}

		return hmQuestionWeights;
	}	
	

	/**
	 * Build hashmap of questions associated to categories using categoryId as the key.
	 * @param categoryList
	 * @return
	 */
	public static HashMap getQuestionsByCategories(List categoryList){
		
		RiskCategory category = null;
		HashMap hm = new HashMap();
	
		for (int i = 0; i < categoryList.size(); i++) {
			
			category = (RiskCategory) categoryList.get(i);
			
			Iterator iter =  RiskQuestions.findAll("riskCategoryId", category.getOID());

			List <RiskQuestions> categoryQuestionList = CollectionUtil.iteratorToList(iter);
			
			hm.put(category.getOID(), categoryQuestionList);
		}
		
		return hm;
	}
	

	/**
	 * @deprecated
	 * @param riskQuestionAnswerEvents
	 * @return
	 */
	public HashMap getQuestionWeights(Enumeration riskQuestionAnswerEvents) {
		int questionNumberPlaceHolder = -1;		
		HashMap hmQuestionWeights = new HashMap ( ) ; 
		
		while( riskQuestionAnswerEvents.hasMoreElements() ) {
			Object obj = riskQuestionAnswerEvents.nextElement();
			if( obj instanceof RiskQuestionAnswerEvent ) {	
				RiskQuestionAnswerEvent riskReqEvent = (RiskQuestionAnswerEvent)obj;
				if (questionNumberPlaceHolder == riskReqEvent.getQuestionNumber()) {
					Integer priorQuestionNumberWeight = (Integer) hmQuestionWeights.get(questionNumberPlaceHolder);
					int newQuestionNumberWeight = 0;
					//The logic below finds out if the answerText has been used to store a weight
					//This is for preset values set in the UI
					if ( (riskReqEvent.getControlCode() != null && riskReqEvent.getControlCode().length() > 0)
							&& (riskReqEvent.getUiControlType().equalsIgnoreCase("TEXTBOX"))
							&& (PDRiskAnalysisHelper.valueIsAllDigits(riskReqEvent.getAnswerText())) ) {
						int tempNewQuestionNumberWeight = 0;
						if (riskReqEvent.getAnswerText() != null && riskReqEvent.getAnswerText().length() > 0 ) {
							tempNewQuestionNumberWeight = Integer.parseInt(riskReqEvent.getAnswerText());
						} 
						newQuestionNumberWeight = priorQuestionNumberWeight.intValue() + tempNewQuestionNumberWeight;
					} else {
						
						//if logic paasses, it means a textbox is being used which stores a user defined numeric value 
						//from which weights are based on.
						if ( riskReqEvent.getUiControlType().equalsIgnoreCase("TEXTBOX")
								&& riskReqEvent.getWeight() == 0
								&& PDRiskAnalysisHelper.valueIsAllDigits(riskReqEvent.getAnswerText())) {
							newQuestionNumberWeight = priorQuestionNumberWeight.intValue() + Integer.parseInt(riskReqEvent.getAnswerText());
						} else {
							newQuestionNumberWeight = priorQuestionNumberWeight.intValue() + riskReqEvent.getWeight();
						}
					}
					
					hmQuestionWeights.put(questionNumberPlaceHolder, newQuestionNumberWeight);
					questionNumberPlaceHolder = riskReqEvent.getQuestionNumber();
				} else {
					//hmQuestionWeights.put(riskReqEvent.getQuestionNumber(), riskReqEvent.getWeight());					
					//The logic below finds out if the answerText has been used to store a weight
					//This is for preset values set in the UI
					if ( (riskReqEvent.getControlCode() != null && riskReqEvent.getControlCode().length() > 0)
							&& (riskReqEvent.getUiControlType().equalsIgnoreCase("TEXTBOX"))
							&& (PDRiskAnalysisHelper.valueIsAllDigits(riskReqEvent.getAnswerText())) ) {
						int tempNewQuestionNumberWeight = 0;
						if (riskReqEvent.getAnswerText() != null && riskReqEvent.getAnswerText().length() > 0) {
							tempNewQuestionNumberWeight = Integer.parseInt(riskReqEvent.getAnswerText());
						} 
						hmQuestionWeights.put(riskReqEvent.getQuestionNumber(), tempNewQuestionNumberWeight);
					} else {
						//if logic paasses, it means a textbox is being used which stores a user defined numeric value 
						//from which weights are based on.
						if ( riskReqEvent.getUiControlType().equalsIgnoreCase("TEXTBOX")
								&& riskReqEvent.getWeight() == 0
								&& PDRiskAnalysisHelper.valueIsAllDigits(riskReqEvent.getAnswerText())) {
							hmQuestionWeights.put(riskReqEvent.getQuestionNumber(), Integer.parseInt(riskReqEvent.getAnswerText()));
						} else {
							hmQuestionWeights.put(riskReqEvent.getQuestionNumber(), riskReqEvent.getWeight());
						}
					}
					questionNumberPlaceHolder = riskReqEvent.getQuestionNumber();
					
				}
			}
		}
		return hmQuestionWeights;
	}

}
