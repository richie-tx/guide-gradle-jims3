//Source file: C:\\views\\CommonSupervision\\app\\src\\action\\RemoveConditionsFromListAction.java

package ui.supervision.suggestedorder.action;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.suggestedorder.reply.SuggestedOrderConditionResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.suggestedorder.form.SuggestedOrderForm;

/**
 * @author dgibler
 */
public class RemoveConditionsFromListAction extends Action
{

	/**
	 * @roseuid 433AF50301CD
	 */
	public RemoveConditionsFromListAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 433AF04F02AA
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;

		String idToBeRemoved = sugOrderForm.getConditionId();

		Collection selectedConditions = sugOrderForm.getConditionSelectedList();
		SuggestedOrderConditionResponseEvent objectToBeRemoved = getSugOrderRespEvent(selectedConditions, idToBeRemoved);
		selectedConditions.remove(objectToBeRemoved);
//		Collection selectedConditions = UISuggestedOrderHelper.addTopicsToConditions(sugOrderForm.getConditionSelectedList());
//		Map conditionsSelectedMap =
//			UISuggestedOrderHelper.buildResponseEventMap(selectedConditions);
//		SuggestedOrderConditionResponseEvent objectToBeRemoved =
//			(SuggestedOrderConditionResponseEvent) conditionsSelectedMap.get(idToBeRemoved);
//
//		conditionsSelectedMap.remove(idToBeRemoved);
//		Collection coll1 = conditionsSelectedMap.values();
//		ArrayList coll2 = new ArrayList();
//		Object obj = null;
//		Iterator iter = coll1.iterator();
//		while (iter.hasNext())
//		{
//			obj = (Object) iter.next();
//			coll2.add(obj);
//		}
//
//		sugOrderForm.setConditionSelectedList(coll2);

		Collection conditionResultList = sugOrderForm.getConditionResultList();
		conditionResultList.add(objectToBeRemoved);
//		sugOrderForm.setOffenseResultList(conditionResultList);

		forward = aMapping.findForward(UIConstants.REMOVE_FROM_LIST_SUCCESS);

		return forward;
	}
	
	private SuggestedOrderConditionResponseEvent getSugOrderRespEvent(Collection selectedConditions, String idToBeRemoved){
		SuggestedOrderConditionResponseEvent objectToBeRemoved = null;
		for(Iterator iter = selectedConditions.iterator(); iter.hasNext(); ){
			objectToBeRemoved = (SuggestedOrderConditionResponseEvent)iter.next(); 
			if(objectToBeRemoved.getConditionId().equals(idToBeRemoved)){
				break;
			}
		}
		
		return objectToBeRemoved;
	}
}
