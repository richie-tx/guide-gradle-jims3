//Source file: C:\\views\\CommonSupervision\\app\\src\\action\\DisplaySuggestedOrderSelectOffenseAction.java

package ui.supervision.suggestedorder.action;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.OffenseCodeResponseEvent;
import messaging.suggestedorder.GetSuggestedOrderByNameEvent;
import messaging.suggestedorder.reply.SuggestedOrderDuplicateNameErrorEvent;
import messaging.supervisionoptions.reply.CourtResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SuggestedOrderControllerServiceNames;
import naming.SupervisionConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.suggestedorder.form.SuggestedOrderForm;
import ui.supervision.suggestedorder.helper.UISuggestedOrderHelper;

/**
 * @author dgibler
 *
 */
public class DisplaySuggestedOrderSelectOffenseAction extends LookupDispatchAction
{
	/**
		* @roseuid 42AF409F01B5
		*/
	public DisplaySuggestedOrderSelectOffenseAction()
	{

	}
	/**
	* @see LookupDispatchAction#getKeyMethodMap()
	* @return Map
	*/
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.addOffenses", "addOffenses");
		keyMap.put("button.noOffenses", "noOffenses");
		keyMap.put("button.skipOffenses", "noOffenses");
		keyMap.put("button.updateOffenses", "addOffenses");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.reset", "resetPage");
		return keyMap;
	}

	public ActionForward addOffenses(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		String action = sugOrderForm.getAction();
		sugOrderForm. setHasOffenses(true);
		
		boolean duplicateName = false;
		if (!action.equals(UIConstants.UPDATE)
			|| ((action.equals(UIConstants.UPDATE)
				&& !sugOrderForm.getOrderName().equalsIgnoreCase(sugOrderForm.getPreviousOrderName()))))
		{
			duplicateName = this.checkForDuplicateName(sugOrderForm.getOrderName());
		}

		if (duplicateName)
		{
			this.sendToErrorPage(aRequest, "error.name.exist");
			forward = aMapping.findForward(UIConstants.DUPLICATES);
		}
		else
		{
			if (action.equals(UIConstants.CREATE)
				|| action.equals(UIConstants.UPDATE) | action.equals(UIConstants.COPY))
			{
				forward = aMapping.findForward(UIConstants.OFFENSE_SUCCESS);
			}

			else
			{
				forward = aMapping.findForward(UIConstants.FAILURE);
			}
		}
		return forward;
	}
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		String action = sugOrderForm.getAction();

		if (action.equals(UIConstants.CREATE))
		{
			//forward = aMapping.findForward(UIConstants.CANCEL_CREATE);
			forward = aMapping.findForward(UIConstants.CANCEL_MAIN_PAGE_HOME);
		}
		else
		{
			forward = aMapping.findForward(UIConstants.CANCEL);
		}
		return forward;
	}

	public ActionForward noOffenses(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ActionForward forward = new ActionForward();
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		String action = sugOrderForm.getAction();
		sugOrderForm. setHasOffenses(false);
		
		boolean duplicateName = false;
		if (!action.equals(UIConstants.UPDATE)
			|| ((action.equals(UIConstants.UPDATE)
				&& !sugOrderForm.getOrderName().equalsIgnoreCase(sugOrderForm.getPreviousOrderName()))))
		{
			duplicateName = this.checkForDuplicateName(sugOrderForm.getOrderName());
		}
		if (duplicateName)
		{
			this.sendToErrorPage(aRequest, "error.name.exist");
			forward = aMapping.findForward(UIConstants.DUPLICATES);
		}
		else
		{
			//Build courts on first time in
			if (sugOrderForm.getAllCourts() == null || sugOrderForm.getAllCourts().size() == 0)
			{
				sugOrderForm.setAllCourtsSelected(true);
				Collection courtBeans = UISuggestedOrderHelper.getCourtBeans();
				sugOrderForm.setAllCourts(courtBeans);
				sugOrderForm.setCourts(courtBeans);
			}
			else
			{
				sugOrderForm.setCourts(sugOrderForm.getAllCourts());
			}
			if (action.equals(UIConstants.UPDATE) || action.equals(UIConstants.COPY))
			{
				Collection filteredCourts = new ArrayList();
				boolean feloniesSelected = this.feloniesSelected(sugOrderForm.getOffenseSelectedList());
				boolean misdemeanorsSelected = this.misdemeanorsSelected(sugOrderForm.getOffenseSelectedList());
				if (feloniesSelected && misdemeanorsSelected)
				{
					Collection felMisdCourts =
						UISuggestedOrderHelper.filterCourtsByOffenses(
							SupervisionConstants.BOTH,
							sugOrderForm.getAllCourts());
					filteredCourts.addAll(felMisdCourts);

				}
				else
				{
					if (feloniesSelected)
					{
						Collection felonyCourts =
							UISuggestedOrderHelper.filterCourtsByOffenses(
								SupervisionConstants.FELONY,
								sugOrderForm.getAllCourts());
						filteredCourts.addAll(felonyCourts);
					}
					else
						if (misdemeanorsSelected)
						{
							Collection misdCourts =
								UISuggestedOrderHelper.filterCourtsByOffenses(
									SupervisionConstants.MISDEMEANOR,
									sugOrderForm.getAllCourts());
							filteredCourts.addAll(misdCourts);
						}
						else
						{
							filteredCourts = sugOrderForm.getAllCourts();
						}
				}
				if (sugOrderForm.getSelectedCourts() != null && sugOrderForm.getSelectedCourts().size() > 0)
				{
					sugOrderForm.setAllCourtsSelected(true);
					// dp filter courts to be selected 
					if (filteredCourts != null && filteredCourts.size() > 0)
					{
						sugOrderForm.setCourts(filteredCourts);
					}
					else // dp If the user hasn't selected any offense yet, then all courts should be available
						{
						sugOrderForm.setCourts(sugOrderForm.getAllCourts());
					}
				}
				else
				{
					sugOrderForm.setAllCourtsSelected(false);
					Collection preselectedCourts =
						this.preSelectCourts(filteredCourts, sugOrderForm.getSelectedCourts());
					sugOrderForm.setCourts(preselectedCourts);

				}
			}
			if (action.equals(UIConstants.CREATE)
				|| action.equals(UIConstants.UPDATE)
				|| action.equals(UIConstants.COPY))
			{
				forward = aMapping.findForward(UIConstants.NO_OFFENSE_SUCCESS);
			}
			else
			{
				forward = aMapping.findForward(UIConstants.FAILURE);
			}
		}
		return forward;

	}
	
	// Defect 61432 Starts here 
	public ActionForward resetPage(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		SuggestedOrderForm sugOrderForm = (SuggestedOrderForm) aForm;
		this.sendToErrorPage(aRequest, "");
		sugOrderForm.clear();
		return aMapping.findForward("resetPage");
	}	
    // Defect 61432 Ends here	
	/**
	 * Determines if there is an existing SuggestedOrder with the same name.
	 * @param orderName
	 * @return
	 */
	private boolean checkForDuplicateName(String orderName)
	{

		boolean duplicateName = false;
		if (orderName != null && !orderName.equals(""))
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetSuggestedOrderByNameEvent requestEvent =
				(GetSuggestedOrderByNameEvent) EventFactory.getInstance(
					SuggestedOrderControllerServiceNames.GETSUGGESTEDORDERBYNAME);
			requestEvent.setSuggestedOrderName(orderName.toUpperCase());
			dispatch.postEvent(requestEvent);
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Object obj = MessageUtil.filterComposite(compositeResponse, SuggestedOrderDuplicateNameErrorEvent.class);
			if (obj != null)
			{
				duplicateName = true;
			}
		}
		return duplicateName;
	}
	/**
	 * @param collection
	 * @return
	 */
	private Collection preSelectCourts(Collection allCourts, Collection selectedCourts)
	{
		Map selectedCourtMap = UISuggestedOrderHelper.createCourtMap(selectedCourts);
		if (allCourts != null)
		{
			Iterator iter = allCourts.iterator();
			while (iter.hasNext())
			{
				CourtBean courtBean = (CourtBean) iter.next();
				Collection courts = courtBean.getCourts();
				courts = MessageUtil.processEmptyCollection(courts);
				boolean allCourtsSelected = true;
				CourtResponseEvent court = null;
				CourtResponseEvent selectedCourt = null;
				Iterator iterator = courts.iterator();
				while (iterator.hasNext())
				{
					court = (CourtResponseEvent) iterator.next();
					selectedCourt = (CourtResponseEvent) selectedCourtMap.get(court.getCourtId());
					if (selectedCourt != null)
					{
						court.setIsSelected(true);
					}
					else
					{
						allCourtsSelected = false;
					}
				}
				if (allCourtsSelected)
				{
					courtBean.setIsSelected(true);
				}
			}
		}
		return allCourts;
	}
	/**
	 * @param offenses
	 * @return
	 */
	private boolean feloniesSelected(Collection offenses)
	{
		return checkOffenseType(offenses, SupervisionConstants.FELONY);
	}

	/**
	 * @param offenses
	 * @return
	 */
	private boolean misdemeanorsSelected(Collection offenses)
	{
		return checkOffenseType(offenses, SupervisionConstants.MISDEMEANOR);
	}

	/**
	 * @param offenses
	 * @param offenseLevel
	 * @return
	 */
	private boolean checkOffenseType(Collection offenses, String offenseLevel)
	{
		boolean foundOffense = false;

		if (offenses != null)
		{
			OffenseCodeResponseEvent ocre = null;
			Iterator iter = offenses.iterator();
			while (iter.hasNext())
			{
				ocre = (OffenseCodeResponseEvent) iter.next();
				if (ocre.getLevel().equals(offenseLevel))
				{
					foundOffense = true;
					break;
				}
			}
		}
		return foundOffense;
	}
	/**
	 * @param aRequest
	 * @param msg
	 */
	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}
}
