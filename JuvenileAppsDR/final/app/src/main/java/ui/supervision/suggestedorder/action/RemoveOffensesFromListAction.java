//Source file: C:\\views\\CommonSupervision\\app\\src\\action\\RemoveOffensesFromListAction.java

package ui.supervision.suggestedorder.action;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.suggestedorder.helper.UISuggestedOrderHelper;
import ui.supervision.suggestedorder.form.SuggestedOrderForm;

/**
 * @author dgibler
 *
 */
public class RemoveOffensesFromListAction extends Action
{

	/**
	 * @roseuid 433AF5100279
	 */
	public RemoveOffensesFromListAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 433AF04F0190
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;

		String idToBeRemoved = sugOrderForm.getOffenseId();

		Collection offensesSelected = UISuggestedOrderHelper.addTopicsToOffenses(sugOrderForm.getOffenseSelectedList());
		Map offensesSelectedMap = UISuggestedOrderHelper.buildResponseEventMap(offensesSelected);
		OffenseCodeResponseEvent objectToBeRemoved = (OffenseCodeResponseEvent) offensesSelectedMap.get(idToBeRemoved);

		offensesSelectedMap.remove(idToBeRemoved);
		Collection coll1 = offensesSelectedMap.values();
		ArrayList coll2 = new ArrayList();
		Object obj = null;
		Iterator iter = coll1.iterator();
		while (iter.hasNext())
		{
			obj = (Object) iter.next();
			coll2.add(obj);
		}

		Collections.sort((List) coll2);
		sugOrderForm.setOffenseSelectedList(coll2);

		Collection offenseResultList = sugOrderForm.getOffenseResultList();
		offenseResultList.add(objectToBeRemoved);
		sugOrderForm.setOffenseResultList(offenseResultList);

		forward = aMapping.findForward(UIConstants.REMOVE_FROM_LIST_SUCCESS);

		return forward;
	}
}
