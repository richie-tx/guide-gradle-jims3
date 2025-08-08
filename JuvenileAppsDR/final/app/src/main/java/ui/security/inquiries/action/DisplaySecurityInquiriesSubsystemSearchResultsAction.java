//Source file: C:\\views\\Security\\app\\src\\ui\\security\\inquiries\\action\\DisplaySecurityInquiriesSubsystemSearchResultsAction.java

package ui.security.inquiries.action;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.security.GetFeatureByIdAndCategoryEvent;
import messaging.security.reply.FeaturesResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.SecurityAdminControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.SecurityUIHelper;
import ui.security.inquiries.form.SecurityInquiriesForm;

public class DisplaySecurityInquiriesSubsystemSearchResultsAction extends Action
{
	
   /**
    * @roseuid 44E9D1DD036B
    */
   public DisplaySecurityInquiriesSubsystemSearchResultsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 44D2218C0077
    */
   public ActionForward execute(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
	   	String forward = UIConstants.FAILURE;
	   	String msg = null;
	   	int size = 0;
	   	Collection emptyColl = new ArrayList();
	   	SecurityInquiriesForm securityInquiriesForm = (SecurityInquiriesForm) aForm; 
	   	securityInquiriesForm.setFeaturesList(emptyColl);
	   	securityInquiriesForm.setSearchResultsCount("");
	   	
	   	GetFeatureByIdAndCategoryEvent featureEvent =
			(GetFeatureByIdAndCategoryEvent) EventFactory.getInstance(SecurityAdminControllerServiceNames.GETFEATUREBYIDANDCATEGORY);
		featureEvent.setFeatureCategory(securityInquiriesForm.getFeatureCategoryId());
		featureEvent.setFeatureId(securityInquiriesForm.getFeatureId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(featureEvent);
				
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
	
		Collection features = MessageUtil.compositeToCollection(compositeResponse, FeaturesResponseEvent.class);
		if (features != null && !(features.isEmpty())){
			Collection featuresList = new ArrayList();
			Iterator iter = features.iterator();
			while (iter.hasNext())
			{
				FeaturesResponseEvent responseEvent = (FeaturesResponseEvent) iter.next();
				if (responseEvent.getParentId() == null){
					securityInquiriesForm.setFeatureCategoryName(responseEvent.getFeatureName());
				}
				else if(featureEvent.getFeatureId() != null && featureEvent.getFeatureId().equals(responseEvent.getFeatureId())){
					featuresList.add(responseEvent);
				}else if(featureEvent.getFeatureId() == null){
					featuresList.add(responseEvent);
				}
			}
			size = featuresList.size();
			if (size == 1){
				FeaturesResponseEvent feature = (FeaturesResponseEvent) featuresList.toArray()[0];
				securityInquiriesForm.setRoleId(feature.getFeatureId());
				forward = UIConstants.SUBSYSTEM_SUCCESS;
			}else{
		    	String searchResultSize = String.valueOf(size);
		    	securityInquiriesForm.setSearchResultsCount(searchResultSize);
				securityInquiriesForm.setFeaturesList(SecurityUIHelper.sortFeatures(features));
				forward = UIConstants.SUBSYSTEM_LIST_SUCCESS;
			}
		}
		else{
			ActionErrors errors = new ActionErrors();
			if (msg == null)
			{
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.features.found"));
			}
			saveErrors(aRequest, errors);
		}	
		return aMapping.findForward(forward);
	   }
}
