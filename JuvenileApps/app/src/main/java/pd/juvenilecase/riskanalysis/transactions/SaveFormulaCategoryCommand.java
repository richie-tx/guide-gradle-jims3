package pd.juvenilecase.riskanalysis.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import naming.RiskAnalysisConstants;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisVersioningHelper;
import pd.juvenilecase.riskanalysis.RiskCategory;
import pd.juvenilecase.riskanalysis.RiskFormula;
import pd.juvenilecase.riskanalysis.RiskRecommendation;
import messaging.riskanalysis.SaveFormulaCategoryEvent;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;
import mojo.km.context.ICommand;

/**
 * @author dgibler
 */
public class SaveFormulaCategoryCommand implements ICommand
{
	private static final String FORMULA_MSG = "NEW RISK FORMULA CREATED-CATEGORY ADDED";
	private static final String CATEGORY_MSG = "NEW RISK CATEGORY CREATED-CATEGORY ADDED";

	
	public void execute(IEvent anEvent)
	{
		SaveFormulaCategoryEvent reqEvent = (SaveFormulaCategoryEvent) anEvent;
		
		RiskFormula rf = RiskFormula.find(reqEvent.getRiskFormulaId());
		
		boolean createNewVersion = this.isCreateNewVersion(reqEvent, rf);
		
		if (createNewVersion){
			rf = PDRiskAnalysisVersioningHelper.getNewRiskFormulaVersion(reqEvent.getRiskFormulaId());
			rf.setModificationReason(FORMULA_MSG);
			
			PDRiskAnalysisVersioningHelper.
				cloneFormulaRecommendationsCategoriesAndQuestions(
						reqEvent.getRiskFormulaId(), rf.getOID(), 
						new HashMap(), new HashMap(), 
						new HashMap(), new HashMap(), 
						CATEGORY_MSG);
		}
		
		String newCategoryId = null;
		RiskCategory category = null;
		ArrayList replies = new ArrayList();
		
		replies.add(rf.getResponseEvent());
		
		for (int i = 0; i < reqEvent.getCategories().size(); i++) {
			newCategoryId = (String) reqEvent.getCategories().get(i);
			category = RiskCategory.find(newCategoryId);
			category.setFormulaId(rf.getOID());
			replies.add(category.getResponseEvent());
		}
		
		List <RiskCategory> existingCategories = RiskCategory.findAll("formulaId", rf.getOID());
		
		for (int i = 0; i < existingCategories.size(); i++) {
			category = existingCategories.get(i);
			replies.add(category.getResponseEvent());
		}
		
		List <RiskRecommendation> existingRecommendations = CollectionUtil.iteratorToList(RiskRecommendation.findAll("riskFormulaId", rf.getOID()));
		RiskRecommendation recommendation = null;
		
		for (int i = 0; i < existingRecommendations.size(); i++) {
			recommendation = existingRecommendations.get(i);
			replies.add(recommendation.getResponseEvent());
		}
		
		MessageUtil.postReplies(replies);
		
		reqEvent = null;
		rf = null;
		newCategoryId = null;
		category = null;
		replies = null;
		existingCategories = null;
		recommendation = null;
		existingRecommendations = null;
	}

	private boolean isCreateNewVersion(SaveFormulaCategoryEvent reqEvent,
			RiskFormula rf) {

		boolean isCreateNewVersion = false;
		
		if (reqEvent.getCategories().size() > 0){
			if (rf.getStatusCd() != null 
					&& rf.getStatusCd().equals(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_ACTIVE)){
				isCreateNewVersion = true;
			}
		}
		
		return isCreateNewVersion;
	}

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}
