//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\ui\\security\\action\\DisplayRoleSummaryAction.java

package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetAgenciesEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.security.DeleteRoleEvent;
import messaging.security.GetFeaturesEvent;
import messaging.security.GetRoleUsersAndUserGroupsEvent;
import messaging.security.GetSARoleFeaturesEvent;
import messaging.security.reply.FeaturesResponseEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.naming.SecurityConstants;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.IShoppingCart;
import ui.common.ShoppingCartImpl;
import ui.security.FeatureShoppingCartImpl;
import ui.security.SecurityUIHelper;
import ui.security.form.RoleForm;

public class DisplayRoleSummaryAction extends LookupDispatchAction
{

	/**
	* @roseuid 425AB84E001F
	*/
	public DisplayRoleSummaryAction()
	{

	}
	/**
	* @return buttonMap
	*/
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.findAgencies", "findAgencies");
		buttonMap.put("button.findFeatures", "findFeatures");
		buttonMap.put("button.addAgencies", "addAgencies");
		buttonMap.put("button.find", "find");
		buttonMap.put("button.addFeatures", "addFeatures");
		buttonMap.put("button.removeSelected", "removeFeatures");
		buttonMap.put("button.edit", "edit");
		buttonMap.put("button.copy", "copy");
		buttonMap.put("button.delete", "delete");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.refresh", "refresh");
		return buttonMap;
	}
	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	* @roseuid 425551F8010A
	*/
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		String forward = UIConstants.FAILURE;
		String action = roleForm.getAction();

		if (action.equalsIgnoreCase(UIConstants.CREATE) || action.equalsIgnoreCase(UIConstants.COPY))
		{
			forward = UIConstants.CREATE_SUCCESS;
		}
		else
			if (action.equalsIgnoreCase(UIConstants.UPDATE))
			{
				if (SecurityUIHelper.isUserMA())
				{
					roleForm.setAgencyName("");
				}
				forward = UIConstants.UPDATE_SUCCESS;
			}
			else
				if (action.equalsIgnoreCase(UIConstants.DELETE))
				{
					forward = UIConstants.DELETE_SUCCESS;
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
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		String forward = UIConstants.FAILURE;
		String masterAdmin = roleForm.getMasterAdmin();
		roleForm.clear();
		roleForm.setMasterAdmin(masterAdmin);
		forward = UIConstants.CANCEL;
		return aMapping.findForward(forward);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward copy(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
			RoleForm roleForm = (RoleForm) aForm;
			roleForm.clearStringArray(roleForm.getSelectedFeatures());
			roleForm.setOriginalRoleName(roleForm.getRoleName());
		    roleForm.setAction( UIConstants.COPY ) ;
			return aMapping.findForward(UIConstants.COPY);
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
		RoleForm roleForm = (RoleForm) aForm;
		roleForm.setAction(UIConstants.DELETE);

		/** forward values to be based of value of action */
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		// get the user and user groups
		GetRoleUsersAndUserGroupsEvent event = new GetRoleUsersAndUserGroupsEvent();
		event.setRoleId(roleForm.getRoleId());
		dispatch.postEvent(event);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection users = (Collection) dataMap.get(SecurityConstants.USER_EVENT_TOPIC);
		users = MessageUtil.processEmptyCollection(users);
		roleForm.setUsers(users);

		Collection userGroups = (Collection) dataMap.get(SecurityConstants.USERGROUP_EVENT_TOPIC);
		userGroups = MessageUtil.processEmptyCollection(userGroups);
		roleForm.setUserGroups(userGroups);
		if (users == null || users.isEmpty() && (userGroups == null || userGroups.isEmpty()))
		{
			DeleteRoleEvent deleteEvent = new DeleteRoleEvent();
			deleteEvent.setRoleId(roleForm.getRoleId());
			dispatch.postEvent(deleteEvent);
			// Get the reply
			compositeResponse = (CompositeResponse) dispatch.getReply();
			// Code for ReturnExceptions returned from persistence layer
			dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);
			return aMapping.findForward(UIConstants.CONFIRM_DELETE_SUCCESS);
		}
		else
			return aMapping.findForward(UIConstants.DELETE_SUCCESS);
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
		RoleForm roleForm = (RoleForm) aForm;
		//	roleForm.setEveryoneRole("No"); 
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		roleForm.setFeatures(emptyColl);
		roleForm.setFeatureName("");
		roleForm.setSubSystemName("");
		if (SecurityUIHelper.isUserMA())
			roleForm.setMasterAdmin("Y");
		else
			roleForm.setMasterAdmin("");
		roleForm.setAction(UIConstants.UPDATE);
		return aMapping.findForward(UIConstants.UPDATE);
	}

	/**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	*/
	public ActionForward findFeatures(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		roleForm.setFeatureFindErrorMessage("");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		String featureName = roleForm.getFeatureName();
		String featureCategory = roleForm.getFeatureCategoryId();
		if (featureName != null && !(featureName.equals(""))) {
			featureName = featureName.toUpperCase();
		}
		if (featureCategory != null && !(featureCategory.equals(""))) {
			featureCategory = featureCategory.toUpperCase();
		}
		if (SecurityUIHelper.isUserMA()) {
			GetFeaturesEvent requestEvent = new GetFeaturesEvent();
			requestEvent.setFeatureName(featureName);
			requestEvent.setFeatureCategory(featureCategory);
			dispatch.postEvent(requestEvent);
		} else {
			GetSARoleFeaturesEvent requestEvent = new GetSARoleFeaturesEvent();
			requestEvent.setAgencyId(SecurityUIHelper.getUserAgencyId());
		//	requestEvent.setAgencyId("CSC");
			requestEvent.setFeatureName(featureName);
			requestEvent.setFeatureCategory(featureCategory);
			dispatch.postEvent(requestEvent);
		}

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection features = MessageUtil.compositeToCollection(compositeResponse, FeaturesResponseEvent.class);
		
// logic added for defect #55960
// some subsystems feature records have values in parentId which were blank in initial design. The presence of this
// value causes erratic return values in SecurityUIHelper.createFeatureTreeData()
		if (features != null){
	        Iterator fIter = features.iterator();
	        while (fIter.hasNext())
	        {
	            FeaturesResponseEvent featureEvent = (FeaturesResponseEvent) fIter.next();
	            if (featureEvent.getParentId() == null){
	            	featureEvent.setParentId("");
	            } else if ( (featureEvent.getFeatureId().equalsIgnoreCase(featureEvent.getFeatureType() )) &&
	            			(featureEvent.getFeatureId().equalsIgnoreCase(featureEvent.getParentId()) )){
	            				featureEvent.setParentId("");
	            }
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
					roleForm.getCurrentFeatures(),
					SecurityUIHelper.createFeatureTreeData(features));
			ArrayList temp = new ArrayList(availableFeatureList);
			Collections.sort(temp);
			roleForm.setAvailableFeatures(temp);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ReturnException returnException = new ReturnException(e.getMessage());
			throw returnException;
		}
		int size = 0;
		if (features != null)
		{
			size = features.size();
		}
		roleForm.setFeatureSearchResultSize(String.valueOf(size));
		if (size == 0 || features == null)
		{
//			CountInfoMessage infoEvent = new CountInfoMessage();
	       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,CountInfoMessage.class);
	       	if (iMessage != null ){
	       		String msg=getResources( aRequest ).getMessage("error.max.limit.exceeded");   	       		
				roleForm.setFeatureFindErrorMessage(msg);  
	       	}	
	       	else
	       	{			
	       		String msg=getResources( aRequest ).getMessage("error.no.features.found");
	       		roleForm.setFeatureFindErrorMessage(msg);			
	       		if (roleForm.getAction().equals(UIConstants.UPDATE))
	       		{
	       			return aMapping.findForward(UIConstants.UPDATE_FEATURE_FAILURE);
	       		}
	       		return aMapping.findForward(UIConstants.CREATE_FEATURE_FAILURE);
	       	}
		}   	
		if (roleForm.getAction().equals(UIConstants.UPDATE))
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
	public ActionForward findAgencies(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		roleForm.setAgencyFindErrorMessage("");
		/** use agnecy name, agency type, and/or jmc representative values to find agency information from form Collection roleNames for display */
		GetAgenciesEvent requestEvent = new GetAgenciesEvent();
		requestEvent.setJmcRepId(roleForm.getJmcRepId());
		requestEvent.setAgencyName(roleForm.getAgencyName());
		requestEvent.setAgencyTypeId(roleForm.getAgencyTypeId());
		requestEvent.setAgencyId(roleForm.getAgencyCode());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
		roleForm.setAgencyCode("");
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection agencies = MessageUtil.compositeToCollection(compositeResponse, AgencyResponseEvent.class);

		IShoppingCart sCart = new ShoppingCartImpl();
		String agencyEventName = UIConstants.AGENCY_RESPONSE_EVENT;
		String agencyEventIdName = UIConstants.AGENCY_RESPONSE_EVENT_ID;
		Collection availableAgencyList;
		try
		{
			availableAgencyList =
				sCart.removeFromAvailableShoppingItemList(
					agencyEventName,
					agencyEventIdName,
					roleForm.getCurrentAgencies(),
					agencies);
			roleForm.setAvailableAgencies(availableAgencyList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			ReturnException returnException = new ReturnException(e.getMessage());
			throw returnException;
		}
		int size = 0;
		if (agencies != null)
		{
			size = agencies.size();
			roleForm.setAgencySearchResultSize(String.valueOf(size));
		}
		if (size == 0)
		{
			roleForm.setAgencyFindErrorMessage("No matching Agencies found.");
		}
		return aMapping.findForward(UIConstants.UPDATE_AGENCY_SUCCESS);
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
		RoleForm roleForm = (RoleForm) aForm;
		Collection availableFeatureList = roleForm.getAvailableFeatures();
		Collection currentList = roleForm.getCurrentFeatures();
		String[] selectedFeaturesId = roleForm.getSelectedFeatures();
		String[] selectedTemp = roleForm.getSelectedFeatures();
		
// get list of parent featureIds to remove from selected list
		String[] parentFeatureIds;
		parentFeatureIds = new String[20];
		Iterator afIter = roleForm.getAvailableFeatures().iterator();
		int indx = 0;
		while (afIter.hasNext()){
			FeaturesResponseEvent featureEvent = (FeaturesResponseEvent) afIter.next();
			parentFeatureIds[indx] =  featureEvent.getFeatureId();
			indx++;
		}
	
		if (indx != 0 && selectedTemp.length > 0){
			boolean matchFound = false;
			int indx2 = 0;
			for (int s = 0; s < selectedTemp.length; s++){
				matchFound = false;
				for (int x =0; x < indx; x++){
			        if (parentFeatureIds[x].equalsIgnoreCase(selectedTemp[s])) {
			        	matchFound = true;
			        	break;
			        }
				}
				if (matchFound == false){
					selectedFeaturesId[indx2] = selectedTemp[s];
					indx2++;
				}
			}
		}
	
		if (roleForm.getSelectedFeatures() != null)
		{
			IShoppingCart sCart = new FeatureShoppingCartImpl();
			try
			{
				currentList = sCart.addToShoppingCart("", "", selectedFeaturesId, currentList, availableFeatureList);
				roleForm.setCurrentFeatures(currentList);

				availableFeatureList =
					sCart.removeFromAvailableShoppingItemList(
						"",
						"",
						roleForm.getCurrentFeatures(),
						availableFeatureList);
				ArrayList temp = new ArrayList(availableFeatureList);
				Collections.sort(temp);
				roleForm.setAvailableFeatures(temp);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				ReturnException returnException = new ReturnException(e.getMessage());
				throw returnException;
			}
		}
		if (roleForm.getAction().equals(UIConstants.UPDATE))
		{

			return aMapping.findForward(UIConstants.UPDATE_FEATURE_SUCCESS);
		}
		return aMapping.findForward(UIConstants.CREATE_FEATURE_SUCCESS);
	}
	
	public ActionForward removeFeatures (
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			RoleForm roleForm = (RoleForm) aForm;
			Collection availableFeatureList = roleForm.getAvailableFeatures();
			Collection currentList = roleForm.getCurrentFeatures();			
			String[] selectedFeaturesId = roleForm.getSelectedFeatures();
//			String[] selectedTemp = roleForm.getSelectedFeatures();
			
			// get list of parent featureIds to remove from selected list
//			String[] parentFeatureIds;
//			parentFeatureIds = new String[20];
//			Iterator afIter = roleForm.getAvailableFeatures().iterator();
//			int indx = 0;
//			while (afIter.hasNext()){
//				FeaturesResponseEvent featureEvent = (FeaturesResponseEvent) afIter.next();
//				parentFeatureIds[indx] =  featureEvent.getFeatureId();
//				indx++;
//			}
		
//			if (indx != 0 && selectedTemp.length > 0){
//				boolean matchFound = false;
//				int indx2 = 0;
//				for (int s = 0; s < selectedTemp.length; s++){
//					matchFound = false;
//					for (int x =0; x < indx; x++){
//				        if (parentFeatureIds[x].equalsIgnoreCase(selectedTemp[s])) {
//				        	matchFound = true;
//				        	break;
//				        }
//					}
//					if (matchFound == false){
//						selectedFeaturesId[indx2] = selectedTemp[s];
//						indx2++;
//					}
//				}
//			}
						
			if (roleForm.getSelectedFeatures() != null)
			{
				IShoppingCart sCart = new FeatureShoppingCartImpl();
				try
				{
					availableFeatureList = sCart.addToAvailableShoppingItemList("", "", selectedFeaturesId, roleForm.getCurrentFeatures(), availableFeatureList);
					roleForm.setAvailableFeatures(availableFeatureList);
					
					currentList =
						sCart.removeFromShoppingCart(
							"",
							"",
							roleForm.getSelectedFeatures(),
							currentList,							
							availableFeatureList);
					
					roleForm.setCurrentFeatures(currentList);					
					
				}
				catch (Exception e)
				{
					e.printStackTrace();
					ReturnException returnException = new ReturnException(e.getMessage());
					throw returnException;
				}
			}
			
			
			if (roleForm.getAction().equals(UIConstants.UPDATE))
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
	public ActionForward addAgencies(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		RoleForm roleForm = (RoleForm) aForm;
		Collection availableAgencyList = roleForm.getAvailableAgencies();
		Collection currentList = roleForm.getCurrentAgencies();
		String[] selectedAgenciesId = roleForm.getSelectedAgencies();
		if (roleForm.getSelectedAgencies() != null)
		{
			IShoppingCart sCart = new ShoppingCartImpl();
			String agencyEventName = UIConstants.AGENCY_RESPONSE_EVENT;
			String agencyEventIdName = UIConstants.AGENCY_RESPONSE_EVENT_ID;
			try
			{
				currentList =
					sCart.addToShoppingCart(
						agencyEventName,
						agencyEventIdName,
						selectedAgenciesId,
						currentList,
						availableAgencyList);
				roleForm.setCurrentAgencies(currentList);

				availableAgencyList =
					sCart.removeFromAvailableShoppingItemList(
						agencyEventName,
						agencyEventIdName,
						currentList,
						availableAgencyList);
				roleForm.setAvailableAgencies(availableAgencyList);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				ReturnException returnException = new ReturnException(e.getMessage());
				throw returnException;
			}
		}
		return aMapping.findForward(UIConstants.UPDATE_AGENCY_SUCCESS);
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
		RoleForm roleForm = (RoleForm) aForm;
		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		String refresh = roleForm.getRefreshButton();
	    roleForm.setRefreshButton("");
	    if (refresh != null){
	        if (refresh.equals("Agency")){
	            roleForm.setAvailableAgencies(emptyColl);
	            roleForm.setAgencyName("");
	            roleForm.setAgencyCode("");
	            roleForm.setAgencyTypeId("");
	            roleForm.setJmcRepId("");
	            roleForm.setAgencyFindErrorMessage("");
	        } else {
	            roleForm.setAvailableFeatures(emptyColl);
	            roleForm.setFeatureName("");
	            roleForm.setFeatureCategory("");
	            roleForm.setFeatureCategoryId("");
	            roleForm.setFeatureFindErrorMessage("");
	        }    
		} 
	    if (refresh == null){    
            roleForm.setAvailableFeatures(emptyColl);
            roleForm.setFeatureName("");
            roleForm.setFeatureCategory("");
		}
// this if is not best practice but it works		
		if (refresh != null){
		    return aMapping.findForward(UIConstants.UPDATE_AGENCY_SUCCESS);
		}
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}
		
}