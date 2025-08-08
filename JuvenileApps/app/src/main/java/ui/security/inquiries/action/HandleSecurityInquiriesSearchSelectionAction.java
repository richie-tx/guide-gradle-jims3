//Source file: C:\\views\\Security\\app\\src\\ui\\security\\inquiries\\action\\HandleSecurityInquiriesSearchSelectionAction.java

package ui.security.inquiries.action;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.security.GetFeaturesEvent;
import messaging.security.reply.FeaturesResponseEvent;

import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.SecurityUIHelper;
import ui.security.inquiries.form.SecurityInquiriesForm;

public class HandleSecurityInquiriesSearchSelectionAction extends LookupDispatchAction
{
	
   /**
    *
    */
   public HandleSecurityInquiriesSearchSelectionAction() 
   {
    
   }
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();

		buttonMap.put("button.submit", "submit");
		buttonMap.put("button.refresh", "refresh");
		buttonMap.put("button.go", "go");

		return buttonMap;
	}   
	
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward submit(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
   	String forward = UIConstants.FAILURE;
   	SecurityInquiriesForm securityInquiriesForm = (SecurityInquiriesForm) aForm;  
   	String searchType = securityInquiriesForm.getSearchTypeId();
   	if (searchType != null){
   		if (searchType.equalsIgnoreCase("AG")){
   			forward = UIConstants.AGENCY_SUCCESS;
   		}
  		else if (searchType.equalsIgnoreCase("EM")){
   			forward = UIConstants.EMPLOYEE_ID_SUCCESS;  
   		} 
   		else if (searchType.equalsIgnoreCase("RO")){
   			forward = UIConstants.ROLE_SUCCESS;  
   		}   		
   		else if (searchType.equalsIgnoreCase("SS")){
			String featureId = securityInquiriesForm.getFeatureId();
			if (featureId == null || featureId.equals("")){
				Collection emptyColl = new ArrayList();				
				securityInquiriesForm.setFeatures(emptyColl);
			}
   			forward = UIConstants.SUBSYSTEM_SUCCESS;        
   		}
   		else if (searchType.equalsIgnoreCase("UG")){
   			forward = UIConstants.USERGROUP_SUCCESS;        
   		}   		
   		else if (searchType.equalsIgnoreCase("UI")){
   			forward = UIConstants.USERPROFILE_SUCCESS;        
   		}
   		else if (searchType.equalsIgnoreCase("UP")){
   			forward = UIConstants.USERPROFILE_SUCCESS;        
   		}   		
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
   public ActionForward refresh(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
    SecurityInquiriesForm securityInquiriesForm = (SecurityInquiriesForm) aForm;
    String searchType = securityInquiriesForm.getSearchTypeId();
    securityInquiriesForm.clear();
    securityInquiriesForm.setSearchTypeId(searchType);
	return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
   }   

   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
   public ActionForward go(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
   	SecurityInquiriesForm securityInquiriesForm = (SecurityInquiriesForm) aForm;  
	Collection emptyColl = new ArrayList();
	securityInquiriesForm.setFeatures(emptyColl);
	/** add code to find features based on FeatureCategoryId value and add to features list */
	String featureCategoryId = securityInquiriesForm.getFeatureCategoryId();
  	if (featureCategoryId != null){
  		if (!featureCategoryId.equals("")){
  			GetFeaturesEvent requestEvent = new GetFeaturesEvent();
  			requestEvent.setFeatureCategory(featureCategoryId);
  			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
  			dispatch.postEvent(requestEvent);
  			
  			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
  			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
  			MessageUtil.processReturnException(dataMap);

  			Collection features = MessageUtil.compositeToCollection(compositeResponse, FeaturesResponseEvent.class);
/** remove parent record from results */
  			if (features != null){
  				String parentId = "";
  				Collection featuresList = new ArrayList();
  				Iterator iter = features.iterator();
  				while (iter.hasNext())
  				{
  					FeaturesResponseEvent responseEvent = (FeaturesResponseEvent) iter.next();
  					parentId = responseEvent.getParentId();
  					if (parentId != null){
  						featuresList.add(responseEvent);
  					}
  				}
  				if (featuresList != null){
  					securityInquiriesForm.setFeatures(SecurityUIHelper.sortFeatures(featuresList));
  				}	
  			}
  		}
  	}
	return aMapping.findForward(UIConstants.GO);
   }      
}
