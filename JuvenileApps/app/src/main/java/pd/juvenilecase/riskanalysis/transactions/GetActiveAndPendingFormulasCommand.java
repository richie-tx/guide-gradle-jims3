package pd.juvenilecase.riskanalysis.transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import naming.RiskAnalysisConstants;

import pd.juvenilecase.riskanalysis.RiskCategory;
import pd.juvenilecase.riskanalysis.RiskFormula;
import messaging.riskanalysis.GetActiveAndPendingFormulasEvent;
import messaging.riskanalysis.reply.FormulaResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.MessageUtil;

/**
 * @author dgibler
 *
 */
public class GetActiveAndPendingFormulasCommand implements ICommand {

	private static final String FORMULA_ID = "formulaId";
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 * Retrieve active and pending formulas by assessmentTypeCd.
	 */
	public void execute(IEvent event) {
		
		GetActiveAndPendingFormulasEvent reqEvent = (GetActiveAndPendingFormulasEvent) event;
		
		List <RiskFormula> formulas = RiskFormula.findAll(reqEvent);
		
		RiskFormula formula = null;
		RiskCategory category = null;
		List replies = new ArrayList();
		FormulaResponseEvent fre = null;
		Map formulaMap = new HashMap();
		
		for (int i = 0; i < formulas.size(); i++) {
			formula = formulas.get(i);
			
			if (formula.getStatusCd() != null){
				if (formula.getStatusCd().equals(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_ACTIVE)){
					if (formulaMap.get(formula.getOID()) == null){
						formulaMap.put(formula.getOID(), formula);
					}
				} else {
					formulaMap.put(formula.getOID(), formula);
				}
			}
		}
		
		if (formulaMap.values() != null){
			formulas = CollectionUtil.iteratorToList(formulaMap.values().iterator());
		} else {
			formulas = new ArrayList();
		}
		
		for (int i = 0; i < formulas.size(); i++) {
			formula = formulas.get(i);
			List <RiskCategory> categories = RiskCategory.findAll(FORMULA_ID, formula.getOID());
			List categoryResponses = new ArrayList();
			
			for (int j = 0; j < categories.size(); j++) {
				category = categories.get(j);
				categoryResponses.add(category.getResponseEvent());
			}
			
			fre = formula.getResponseEvent();
			fre.setCategories(categoryResponses);
			replies.add(fre);
			
			categories = null;
			categoryResponses = null;
		}

		MessageUtil.postReplies(replies);
		
		formulaMap = null;
		reqEvent = null;
		formulas = null;
		formula = null;
		replies = null;
		fre = null;
	}

}
