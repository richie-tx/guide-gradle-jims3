//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplayDepartmentPolicySearchResultsAction.java

package ui.supervision.SupervisionOptions.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.GetDepartmentPoliciesEvent;
import messaging.supervisionoptions.reply.DepartmentPolicyResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.SupervisionOptions.form.DepartmentPolicyForm;

public class DisplayDepartmentPolicySearchResultsAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 42F7C48B01C5
    */
   public DisplayDepartmentPolicySearchResultsAction() 
   {
    
   }
   
   protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.submit", "submit");		
	}
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F79A0900CD
    */
   public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		DepartmentPolicyForm form = (DepartmentPolicyForm) aForm;
		GetDepartmentPoliciesEvent postEvt = new GetDepartmentPoliciesEvent();
			
		postEvt.setAgencyId( form.getAgencyId() );
		postEvt.setName( form.getName() );
		postEvt.setGroup1( form.getGroup1Id() );
		postEvt.setGroup2( form.getGroup2Id() );
		postEvt.setGroup3( form.getGroup3Id() );
			
		//TODO How should an invalid date be handled?
			
		postEvt.setEffectiveDate( UISupervisionOptionHelper.parseDate(form.getEffectiveDate()) );
		postEvt.setInactiveDate( UISupervisionOptionHelper.parseDate(form.getInactiveDate()) );
		postEvt.setStatus( form.getStatus() );
	

		// check if All courts have been selected
		boolean allSelected = form.isAllCourtsSelected();
		if(!allSelected)
		{
			// create a CourtRespEvent map to make search fast for the selected courts
			ArrayList selectedCourts = new ArrayList();
			Collection courtBeans = form.getCourts();
			if(courtBeans != null){
				Iterator it = courtBeans.iterator();
				while(it.hasNext()){
					CourtBean courtBean = (CourtBean)it.next();
					String[] selCourts = aRequest.getParameterValues(courtBean.getCategory());
					if(selCourts != null){
						selectedCourts.addAll(Arrays.asList(selCourts));
					}
				}
			}
			postEvt.setCourts(selectedCourts);	
		}	
	
		CompositeResponse response = MessageUtil.postRequest(postEvt);
	    Collection searchResults = MessageUtil.compositeToCollection(response, DepartmentPolicyResponseEvent.class);
	
		form.setSearchResults( searchResults );
		
		if(searchResults == null || searchResults.size() == 0)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.search.results.found", "No search results found"));
			saveErrors(aRequest, errors);
						
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SEARCH_FAILURE,form.getAgencyId()));
	
		}
		else{
				Collections.sort((List)searchResults);
			}

		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SEARCH_SUCCESS,form.getAgencyId()));
   }
}
