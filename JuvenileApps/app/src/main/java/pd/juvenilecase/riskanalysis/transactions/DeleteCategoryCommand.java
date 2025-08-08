package pd.juvenilecase.riskanalysis.transactions;

import java.util.List;

import naming.PDConstants;
import naming.RiskAnalysisConstants;
import pd.juvenilecase.riskanalysis.RiskCategory;
import pd.juvenilecase.riskanalysis.RiskFormula;
import pd.juvenilecase.riskanalysis.RiskQuestions;
import messaging.riskanalysis.DeleteCategoryEvent;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;

public class DeleteCategoryCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		
		DeleteCategoryEvent reqEvent = (DeleteCategoryEvent) anEvent;
		RiskCategory category = RiskCategory.find(reqEvent.getCategoryId());
		RiskFormula riskFormula = null;
		
		if (category.getFormulaId() != null && !category.getFormulaId().equals(PDConstants.BLANK)){
			riskFormula = category.getFormula();
		}
		
		if (riskFormula == null || riskFormula.getStatusCd().equals(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_PENDING)){
			IHome home = new Home();			
			List <RiskQuestions> questionList = CollectionUtil.iteratorToList(category.getQuestions().iterator());
			RiskQuestions question = null;
			
			for (int i = 0; i < questionList.size(); i++) {
				question = questionList.get(i);
				question.setRiskCategoryId(null);
				home.bind(question);
			}
			category.delete();
			home.bind(category);
			
			//Delete the formula if there are no more categories associated to it.
			if (riskFormula != null){
				List categoryList = RiskCategory.findAll("formulaId", riskFormula.getOID());
				if (categoryList.size() == 0){
					riskFormula.delete();
					home.bind(riskFormula);
				}
				categoryList = null;
			}
			question = null;
			questionList = null;
			home = null;
		} else {
			ReturnException re = new ReturnException("FORMULA IS ACTIVE-DELETE NOT ALLOWED");
			EventManager.getSharedInstance(EventManager.REPLY).postEvent(re);
			re = null;
		}
		
		riskFormula = null;
		reqEvent = null;
		category = null;
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
