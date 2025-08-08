//Source file: C:\\views\\dev\\app\\src\\ui\\supervision\\SupervisionOptions\\action\\DisplayCourtPolicySearchResultsAction.java

package ui.supervision.SupervisionOptions.action;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionoptions.GetCourtPoliciesEvent;
import messaging.supervisionoptions.reply.CourtPolicyResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.SupervisionOptions.UISupervisionOptionHelper;
import ui.supervision.SupervisionOptions.form.CourtBean;
import ui.supervision.SupervisionOptions.form.CourtPolicyForm;


public class DisplayCourtPolicySearchResultsAction extends Action
{
   
   /**
    * @roseuid 42F7C482034B
    */
   public DisplayCourtPolicySearchResultsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 42F7997E00A0
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		CourtPolicyForm form = (CourtPolicyForm) aForm;
		GetCourtPoliciesEvent postEvt = new GetCourtPoliciesEvent();
			
		postEvt.setAgencyId( form.getAgencyId() );
		postEvt.setName( form.getCourtPolicyName() );
		postEvt.setDescription( form.getCourtPolicyLiteral() );
		postEvt.setGroup1( form.getGroup1Id() );
		postEvt.setGroup2( form.getGroup2Id() );
		postEvt.setGroup3( form.getGroup3Id() );
			
		//TODO How should an invalid date be handled?
			
		postEvt.setEffectiveDate( UISupervisionOptionHelper.parseDate(form.getEffectiveDate()) );
		postEvt.setInactiveDate( UISupervisionOptionHelper.parseDate(form.getInactiveDate()) );
		postEvt.setStandardSelected(true);
		
		String standardSearchCriteria = form.getStandardSearchCriteria();
		
		if(standardSearchCriteria != null && standardSearchCriteria.length() > 0)
		{
			postEvt.setStandardSelected(true);
			postEvt.setStandard(Boolean.valueOf(standardSearchCriteria).booleanValue());
		}
		else
		{
			postEvt.setStandardSelected(false);	
		}
		
		postEvt.setStatus( form.getCourtPolicyStatus() );
		
		// check if All courts haveb een selected
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
			
			
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(postEvt);
		Collection searchResults =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), CourtPolicyResponseEvent.class );
	
		form.setCourtPolicySearchResults( searchResults );
		
		if(searchResults == null || searchResults.size() == 0)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.search.results.found", "No search result found"));
			saveErrors(aRequest, errors);
								
			return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SEARCH_FAILURE,form.getAgencyId()));
			
		}
		else{
			Collections.sort((List)searchResults);
		}
		if (searchResults.size() == 1)
		{
			form.setOnlyMatch(true);	
		}else {
			form.setOnlyMatch(false);
		}
		return aMapping.findForward(UICommonSupervisionHelper.computeCSMultiDeptForward(UIConstants.SEARCH_SUCCESS,form.getAgencyId()));
   }

}
