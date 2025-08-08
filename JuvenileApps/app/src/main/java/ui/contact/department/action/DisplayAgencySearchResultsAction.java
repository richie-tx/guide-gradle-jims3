//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\department\\action\\DisplayAgencySearchResultsAction.java

package ui.contact.department.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetAgenciesEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.info.reply.CountInfoMessage;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.InfoMessageEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.PDContactConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.contact.department.form.DepartmentForm;

public class DisplayAgencySearchResultsAction extends LookupDispatchAction
{

	/**
	 * @roseuid 430630E101EE
	 */
	public DisplayAgencySearchResultsAction()
	{

	}

	/** 
		* @see LookupDispatchAction#getKeyMethodMap()
		*/
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", UIConstants.NEXT);
		buttonMap.put("button.cancel", UIConstants.CANCEL);
		buttonMap.put("button.refresh", "refresh");
		buttonMap.put("button.findAgencies", UIConstants.FIND);
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 430628E101F8
	 */
	public ActionForward find(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm deptform = (DepartmentForm) aForm;

		deptform.setSelectedAgencyId("");
//		Collection agencies = UIDepartmentHelper.fetchThinAgencies(deptform.getAgencyName(), deptform.getAgencyId());
		GetAgenciesEvent requestEvent = (GetAgenciesEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETAGENCIES);

		requestEvent.setAgencyId(deptform.getAgencyId());
		requestEvent.setAgencyName(deptform.getAgencyName());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection agencies = (Collection) dataMap.get(PDContactConstants.AGENCY_EVENT_TOPIC);
		agencies = MessageUtil.processEmptyCollection(agencies);
		deptform.setAgencyList(agencies);
		if (deptform.getAgencyList() == null || deptform.getAgencyList().size() < 1)
		{
	       	CountInfoMessage infoEvent = new CountInfoMessage();
	       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,InfoMessageEvent.class);
	       	if (iMessage != null ){
	       		ActionErrors errors = new ActionErrors();
	       		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
	       		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
	       		saveErrors(aRequest, errors);
	       		return aMapping.findForward(UIConstants.FAILURE);
	       	} 
	       	else
	       	{			
	       		sendToErrorPage(aRequest, "error.no.search.results.found");
	       		return aMapping.findForward(UIConstants.FAILURE);
	       	}	
		}
		Collections.sort((List) agencies);
		return aMapping.findForward(UIConstants.FIND_SUCCESS);
	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	* @roseuid 430628E101F8
	*/
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm deptform = (DepartmentForm) aForm;
		this.setAgencyInfo(deptform);
		return aMapping.findForward(UIConstants.CREATE);
	}

	
	/**
	 * @param deptForm
	 */
	private void setAgencyInfo(DepartmentForm deptForm)
	{
		Iterator agenciesIte = deptForm.getAgencyList().iterator();
		AgencyResponseEvent foundAgency = null;
		while(agenciesIte.hasNext())
		{
			AgencyResponseEvent agency = (AgencyResponseEvent) agenciesIte.next();
			if(agency.getAgencyId().equalsIgnoreCase(deptForm.getSelectedAgencyId()))
			{
				foundAgency = agency;
			    break;	
			}
		}
		
		if(foundAgency != null)
		{
			deptForm.setAgencyId(foundAgency.getAgencyId());
			deptForm.setAgencyName(foundAgency.getAgencyName());
			deptForm.setAgencyTypeId(foundAgency.getAgencyTypeId());
		}
	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	* @roseuid 430628E101F8
	*/
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	* @roseuid 430628E101F8
	*/
	public ActionForward refresh(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		DepartmentForm deptform = (DepartmentForm) aForm;
		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		deptform.setAgencyList(emptyColl);		
		deptform.setAgencyId("");
		deptform.setAgencyName("");
		deptform.setSelectedAgencyId("");
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}	

	private void sendToErrorPage(HttpServletRequest aRequest, String msg)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msg));
		saveErrors(aRequest, errors);
	}

}