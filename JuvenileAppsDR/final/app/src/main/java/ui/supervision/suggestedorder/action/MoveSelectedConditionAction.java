//Source file: C:\\views\\CommonSupervision\\app\\src\\action\\MoveSelectedConditionAction.java

package ui.supervision.suggestedorder.action;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
 *
 */
public class MoveSelectedConditionAction extends Action
{

	/**
	 * @roseuid 433AF4F60075
	 */
	public MoveSelectedConditionAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 433AF0510104
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;

		Collection selectedConditions = sugOrderForm.getConditionSelectedList();

		Map conditionMap = this.createConditionMap(selectedConditions);
		SuggestedOrderConditionResponseEvent condMoved = (SuggestedOrderConditionResponseEvent)conditionMap.get(sugOrderForm.getConditionId());
		// get its sequence num
		int currSeqNum = Integer.parseInt(condMoved.getSeqNum());
		if(sugOrderForm.isOrder()){// move up
			// if its not the first one
			if(currSeqNum > 1){
				// set new seq num
				condMoved.setSeqNum(String.valueOf(currSeqNum - 1));			

				// set the seq num for the previous
				int originalPos = ((List)selectedConditions).lastIndexOf(condMoved);
				if (originalPos > 0){
					SuggestedOrderConditionResponseEvent socreToBeMovedDown = (SuggestedOrderConditionResponseEvent)((List)selectedConditions).get(originalPos - 1);
					socreToBeMovedDown.setSeqNum(String.valueOf(currSeqNum));
				}
			}
		}else{ // move down
			// if its not the last one
			if(currSeqNum < selectedConditions.size()){
				// set new seq num
				condMoved.setSeqNum(String.valueOf(currSeqNum + 1));			

				// set the seq num for the next one
				int originalPos = ((List)selectedConditions).lastIndexOf(condMoved);
				if (originalPos < selectedConditions.size() - 1){
					SuggestedOrderConditionResponseEvent socreToBeMoved = (SuggestedOrderConditionResponseEvent)((List)selectedConditions).get(originalPos + 1);
					socreToBeMoved.setSeqNum(String.valueOf(currSeqNum));
				}
				
			}
			
		}
		
		// sort it based on sequence number
		Collections.sort((List)selectedConditions);
		
//		sugOrderForm.setConditionSelectedList(reorderedConditions);
		
//		int counter = 0;
//		SuggestedOrderConditionResponseEvent socre = null;
//		Integer integer = null;
//		String string = null;
//		Iterator iter = reorderedConditions.iterator();
//
//		while (iter.hasNext())
//		{
//			counter++;
//			socre = (SuggestedOrderConditionResponseEvent) iter.next();
//			socre.setSeqNum(String.valueOf(counter));
//		}


		forward = aMapping.findForward(UIConstants.SUCCESS);

		return forward;
	}

	/**
	 * @param selectedList
	 * @return
	 */
	private Map createConditionMap(Collection selectedList)
	{
		HashMap map = new HashMap();

		if (selectedList != null)
		{
			SuggestedOrderConditionResponseEvent cre = null;
			Iterator iter = selectedList.iterator();
			while (iter.hasNext())
			{
				cre = (SuggestedOrderConditionResponseEvent) iter.next();
				map.put(cre.getConditionId(), cre);
			}
		}
		return map;
	}
}
