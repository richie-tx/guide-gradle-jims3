package pd.juvenilecase.riskanalysis.transactions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import naming.RiskAnalysisConstants;
import pd.juvenilecase.riskanalysis.PDRiskAnalysisVersioningHelper;
import pd.juvenilecase.riskanalysis.RiskCategory;
import pd.juvenilecase.riskanalysis.RiskFormula;
import messaging.riskanalysis.RemoveFormulaCategoryEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.MessageUtil;
import mojo.km.context.ICommand;

public class RemoveFormulaCategoryCommand implements ICommand
{
	private static final String FORMULA_MSG = "NEW RISK FORMULA CREATED-CATEGORY REMOVED";
	private static final String CATEGORY_MSG = "NEW RISK CATEGORY CREATED-CATEGORY REMOVED";
	
	public void execute(IEvent anEvent)
	{
		RemoveFormulaCategoryEvent reqEvent = (RemoveFormulaCategoryEvent) anEvent;
		
		RiskCategory category = RiskCategory.find(reqEvent.getCategoryId());
		
		RiskFormula rf = RiskFormula.find(category.getFormulaId());
		IHome home = new Home();
		
		if (rf != null && rf.getStatusCd().equals(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_ACTIVE)){
			rf = this.createNewVersion(reqEvent, rf);
		} else {
			category.setFormulaId(null);
			home.bind(category);
		}
		
		MessageUtil.postReply(rf.getResponseEvent());
		
		List <RiskCategory> categories = RiskCategory.findAll("formulaId", rf.getOID());
		for (int i = 0; i < categories.size(); i++) {
			MessageUtil.postReply(categories.get(i).getResponseEvent());
		}

		reqEvent = null;
		category = null;
		rf = null;
	}

	private RiskFormula createNewVersion(RemoveFormulaCategoryEvent reqEvent, RiskFormula rf) {
		
		String oldFormulaId = rf.getOID();
		rf = PDRiskAnalysisVersioningHelper.getNewRiskFormulaVersion(rf.getOID());
		rf.setModificationReason(FORMULA_MSG);
		
		Map oldCategoryToNewCategoryMap = new HashMap();
		
		PDRiskAnalysisVersioningHelper.
			cloneFormulaRecommendationsCategoriesAndQuestions(
					oldFormulaId, rf.getOID(), oldCategoryToNewCategoryMap, new HashMap(), 
					new HashMap(), new HashMap(), CATEGORY_MSG);
		
		String newCategoryId = (String) oldCategoryToNewCategoryMap.get(reqEvent.getCategoryId());
		RiskCategory category = RiskCategory.find(newCategoryId);
		
		PDRiskAnalysisVersioningHelper.deleteCategoryQuestionsAndAnswers(category);
		
		oldFormulaId = null;
		oldCategoryToNewCategoryMap = null;
		newCategoryId = null;
		category = null;
		
		return rf;
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
