//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\DisplaySARoleSummaryAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.agency.GetAgenciesByJmcRepEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.security.GetFeaturesEvent;
import messaging.security.reply.FeaturesResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.InfoMessageEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.common.IShoppingCart;
import ui.security.FeatureShoppingCartImpl;
import ui.security.SecurityUIHelper;
import ui.security.form.RoleSAForm;

public class DisplaySARoleSummaryAction extends LookupDispatchAction
{

	/**
		* @roseuid 425AB71301E4
		*/
	public DisplaySARoleSummaryAction()
	{

	}

	/**
		* @return buttonMap
		*/
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.find", "find");
		buttonMap.put("button.edit", "edit");
		buttonMap.put("button.delete", "delete");
		buttonMap.put("button.addFeatures", "addFeatures");
		buttonMap.put("button.removeSelected", "removeFeatures");
		buttonMap.put("button.refresh", "refresh");		
		return buttonMap;
	}
	/**
		* @param aMapping
		* @param aForm
		* @param aRequest
		* @param aResponse
		* @return ActionForward
		* @roseuid 4256D63001D8
		*/
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		String forward = UIConstants.FAILURE;
		String action = saRoleForm.getAction();
		if (action == null)
		{
			action = aRequest.getParameter("action");
			if (action == null)
			{
				action = "";
			}
		}
		if (action.equalsIgnoreCase(UIConstants.UPDATE))
		{
			if (saRoleForm.getSelectedAgencies() != null)
			{
				boolean isSelectedAgency = false;
				String[] selectedAgenciesId = saRoleForm.getSelectedAgencies();
				Collection agenciesId = saRoleForm.getJmcAgencies();
				Iterator agItr = agenciesId.iterator();
				while (agItr.hasNext())
				{
					AgencyResponseEvent event = (AgencyResponseEvent) agItr.next();
					for (int i = 0; i < selectedAgenciesId.length; i++)
					{
						if (event.getAgencyId().equals(selectedAgenciesId[i]))
						{
							saRoleForm.setAgencyName(event.getAgencyName());
							saRoleForm.setAgencyId(event.getAgencyId());
							isSelectedAgency = true;
							break;
						}
					}
					if (isSelectedAgency)
						break;
				}
			}
			forward = UIConstants.UPDATE_SUCCESS;
		}
		if (action.equalsIgnoreCase(UIConstants.VIEW))
		{
			forward = UIConstants.SUCCESS;
		}
		if (action.equalsIgnoreCase(UIConstants.CREATE))
		{
			forward = UIConstants.CREATE_SUCCESS;
		}
		return aMapping.findForward(forward);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		/** may need to get action session value here */
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		saRoleForm.setAction(UIConstants.CREATE);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward delete(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		saRoleForm.setAction(UIConstants.DELETE);
		return aMapping.findForward(UIConstants.DELETE_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		saRoleForm.clear();
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward edit(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		saRoleForm.setAllSecurityAdmin("No");
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		saRoleForm.setFeatures(emptyColl);
		saRoleForm.setAvailableFeatures(emptyColl);
		saRoleForm.setFeatureName("");
		saRoleForm.setSubSystemName("");
		saRoleForm.setAction(UIConstants.UPDATE);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetAgenciesByJmcRepEvent event = new GetAgenciesByJmcRepEvent();
		event.setJmcRepId("Y");
		dispatch.postEvent(event);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection agencies = MessageUtil.compositeToCollection(compositeResponse, AgencyResponseEvent.class);
		agencies = SecurityUIHelper.getAgenciesWhichDoesNotHaveSARole(agencies);
		saRoleForm.setJmcAgencies(agencies);
		return aMapping.findForward(UIConstants.UPDATE);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward find(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		GetFeaturesEvent requestEvent = new GetFeaturesEvent();

		String featureName = saRoleForm.getFeatureName();
		if (featureName != null && !(featureName.equals("")))
		{
			featureName = featureName.trim();
		}
		requestEvent.setFeatureName(featureName);
			
		String featureCategory = saRoleForm.getFeatureCategoryId();
		if (featureCategory != null && !(featureCategory.equals("")))
		{
			featureCategory = featureCategory.toUpperCase();
		}
		requestEvent.setFeatureCategory(featureCategory);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection features = MessageUtil.compositeToCollection(compositeResponse, FeaturesResponseEvent.class);
// check for max. limit exceeded
		if (features != null){
			CountInfoMessage infoEvent = new CountInfoMessage();
			CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,InfoMessageEvent.class);
			if (iMessage != null ){
				ActionErrors errors = new ActionErrors();
				ActionMessage error = new ActionMessage("error.max.limit.exceeded");
				errors.add(ActionErrors.GLOBAL_MESSAGE, error);
				saveErrors(aRequest, errors);
				if (saRoleForm.getAction().equals(UIConstants.UPDATE))
				{
					return aMapping.findForward(UIConstants.UPDATE_FEATURE_FAILURE);
				}
				return aMapping.findForward(UIConstants.CREATE_FEATURE_FAILURE);
			}			
		}
		IShoppingCart sCart = new FeatureShoppingCartImpl();
		Collection availableFeatureList;
		try
		{
			availableFeatureList =
				sCart.removeFromAvailableShoppingItemList(
					"",
					"",
					saRoleForm.getCurrentFeatures(),
					SecurityUIHelper.createFeatureTreeData(features));
			saRoleForm.setAvailableFeatures(availableFeatureList);		
		}
		catch (Exception e){
		   e.printStackTrace();
		   ReturnException returnException = new ReturnException(e.getMessage());
		   throw returnException;
		}

		int size = 0;
		if (features != null)
		{
			size = features.size();
		}
		saRoleForm.setFeatureSearchResultSize(String.valueOf(size));
		if (size == 0)
		{
			saRoleForm.setAvailableFeatures(new ArrayList());
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.features.found"));
			saveErrors(aRequest, errors);
			if (saRoleForm.getAction().equals(UIConstants.UPDATE))
			{
				return aMapping.findForward(UIConstants.UPDATE_FEATURE_FAILURE);
			}
			return aMapping.findForward(UIConstants.CREATE_FEATURE_FAILURE);
		}
		if (saRoleForm.getAction().equals(UIConstants.UPDATE))
		{
			return aMapping.findForward(UIConstants.UPDATE_FEATURE_SUCCESS);
		}
		return aMapping.findForward(UIConstants.CREATE_FEATURE_SUCCESS);
	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	* @roseuid 4256D630011A
	*/
	public ActionForward addFeatures(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)	
	{
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		Collection availableFeatureList = saRoleForm.getAvailableFeatures();
		Collection currentList = saRoleForm.getCurrentFeatures();
		String[] selectedFeaturesId = saRoleForm.getSelectedFeatures();
		
		if (saRoleForm.getSelectedFeatures() != null)
		{			
			IShoppingCart sCart = new FeatureShoppingCartImpl();
			try
			{
				currentList = sCart.addToShoppingCart("", "", selectedFeaturesId, currentList, availableFeatureList);
				saRoleForm.setCurrentFeatures(currentList);

				availableFeatureList =
					sCart.removeFromAvailableShoppingItemList(
						"",
						"",
						saRoleForm.getCurrentFeatures(),
						availableFeatureList);
				ArrayList temp = new ArrayList(availableFeatureList);
				Collections.sort(temp);
				saRoleForm.setAvailableFeatures(temp);				
			}
			catch (Exception e)
			{
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(e.getMessage()));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.FAILURE);
			}
		}
		if (saRoleForm.getAction().equals(UIConstants.UPDATE))
		{
			return aMapping.findForward(UIConstants.UPDATE_FEATURE_SUCCESS);
		}
		return aMapping.findForward(UIConstants.CREATE_FEATURE_SUCCESS);
	}

	/**
		* @param aMapping
		* @param aForm
		* @param aRequest
		* @param aResponse
		* @return ActionForward
		* @roseuid 4256D630011A
		*/
	public ActionForward removeFeatures(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		Collection availableFeatureList = saRoleForm.getAvailableFeatures();
		Collection currentList = saRoleForm.getCurrentFeatures();
		String[] selectedFeaturesId = saRoleForm.getSelectedFeatures();
		

		if (saRoleForm.getSelectedFeatures() != null)
		{	
			IShoppingCart sCart = new FeatureShoppingCartImpl();
			try
			{
				availableFeatureList =
					sCart.addToAvailableShoppingItemList(
					"",
					"",
					selectedFeaturesId,
					saRoleForm.getCurrentFeatures(),					
					availableFeatureList);
				    saRoleForm.setAvailableFeatures(availableFeatureList);
										
				currentList = sCart.removeFromShoppingCart(
					"", 
					"",
					saRoleForm.getSelectedFeatures(),
					currentList,
					availableFeatureList);
				    
				    saRoleForm.setCurrentFeatures(currentList);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(e.getMessage()));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.FAILURE);
			}
		}
		if (saRoleForm.getAction().equals(UIConstants.UPDATE))
		{
			return aMapping.findForward(UIConstants.UPDATE_FEATURE_SUCCESS);
		}
		return aMapping.findForward(UIConstants.CREATE_FEATURE_SUCCESS);
	}
		
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward refresh(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleSAForm saRoleForm = (RoleSAForm) aForm;
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		saRoleForm.setAvailableFeatures(emptyColl);
		saRoleForm.setFeatureName("");
		saRoleForm.setFeatureCategory("");
		if (saRoleForm.getAction().equals(UIConstants.UPDATE))
		{
			return aMapping.findForward(UIConstants.UPDATE_REFRESH_SUCCESS);
		}
		return aMapping.findForward(UIConstants.CREATE_REFRESH_SUCCESS);
		
	}	
}
