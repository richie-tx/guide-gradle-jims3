package pd.juvenilecase.riskanalysis.transactions;

import java.util.ArrayList;
import java.util.List;

import pd.juvenilecase.riskanalysis.RiskCategory;
import messaging.riskanalysis.GetCategoriesEvent;
import mojo.km.messaging.IEvent;
import mojo.km.util.MessageUtil;
import mojo.km.context.ICommand;

public class GetCategoriesCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		GetCategoriesEvent reqEvent = (GetCategoriesEvent) anEvent;
		List <RiskCategory> aList = RiskCategory.findAll(reqEvent);
		
		RiskCategory category = null;
		List creList = new ArrayList();
		
		for (int i = 0; i < aList.size(); i++) {
			category = aList.get(i);
			creList.add(category.getResponseEvent());
		}
		
		MessageUtil.postReplies(creList);
		
		reqEvent = null;
		aList = null;
		category = null;
		creList = null;
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
