package pd.juvenilecase.riskanalysis.transactions;

import java.util.ArrayList;
import java.util.List;

import pd.juvenilecase.riskanalysis.PDRiskQuestionHelper;
import pd.juvenilecase.riskanalysis.RiskCategory;
import pd.juvenilecase.riskanalysis.RiskQuestions;
import messaging.riskanalysis.GetCategoryDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.MessageUtil;

public class GetCategoryDetailsCommand implements ICommand {

	public void execute(IEvent event) {
		GetCategoryDetailsEvent reqEvent = (GetCategoryDetailsEvent) event;
		
		RiskCategory category = RiskCategory.find(reqEvent.getCategoryId());
		
		if (category != null){
			MessageUtil.postReply(category.getResponseEvent());
			
			List <RiskQuestions> questions = CollectionUtil.iteratorToList(category.getQuestions().iterator());
		
			RiskQuestions riskQuestion = null;
			
			List responses = new ArrayList();
			for (int i = 0; i < questions.size(); i++) {
				riskQuestion = questions.get(i);
				responses.add(PDRiskQuestionHelper.getQuestionResponseEvent(riskQuestion));
			}
			
			MessageUtil.postReplies(responses);
			
			reqEvent = null;
			category = null;
			questions = null;
			riskQuestion = null;
			responses = null;
		}

	}

}
