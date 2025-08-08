// Source file:
// C:\\views\\MJCW\\app\\src\\ui\\supervision\\administerserviceprovider\\programreferral\\action\\DisplaySPProgramReferralListAction.java

package ui.supervision.administerserviceprovider.programreferral.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.codetable.GetCodeTableRecordsEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.codetable.reply.CodeTableResponseEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProgramReferralConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.programreferral.form.ProgramReferralForm;
import ui.juvenilecase.programreferral.form.ProgramReferralSearchForm;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;

public class DisplaySPProgramReferralSearchAction extends JIMSBaseAction {

	/**
	 * @roseuid 463BA54902FA
	 */
	public DisplaySPProgramReferralSearchAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward displayProgramSearch(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		
		ProgramReferralSearchForm form = (ProgramReferralSearchForm) aForm;
		form.clear();
		String forward = null;
		
		String serviceProviderId = SecurityUIHelper.getServiceProviderId();
		if(serviceProviderId == null){
			this.saveErrors(aRequest,"error.serviceProvider.invalidUser");
			forward = UIConstants.FAILURE;
		} else {
			
			//Set Program List
			Collection pgmList = UIServiceProviderHelper.getPrograms( serviceProviderId ) ;
			Collection sortedPgmList = UIServiceProviderHelper.sortResults(pgmList, "P");
			Collections.sort( (ArrayList)sortedPgmList );
			form.setPrograms( sortedPgmList ) ;
			
			//Set Program Referral Status List 
			//Get Referral Statuses 
			GetCodeTableRecordsEvent event = (GetCodeTableRecordsEvent) EventFactory.getInstance(
					CodeTableControllerServiceNames.GETCODETABLERECORDS);
			event.setCodeTableName(PDCodeTableConstants.PROGRAM_REFERRAL_STATUS);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(event);
			CompositeResponse programRefresponse = (CompositeResponse) dispatch.getReply();
			
			// Get Sub-Statuses
			event = (GetCodeTableRecordsEvent) EventFactory.getInstance(
					CodeTableControllerServiceNames.GETCODETABLERECORDS);
			event.setCodeTableName(PDCodeTableConstants.PROGRAM_REFERRAL_SUBSTATUS);
			dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(event);
			CompositeResponse programRefSubresponse = (CompositeResponse) dispatch.getReply();
			
			List programRefStatusRespList = (List) MessageUtil.compositeToCollection(
					programRefresponse, CodeResponseEvent.class);
			Collections.sort(programRefStatusRespList);
			
			List programRefSubStatusRespList = (List) MessageUtil.compositeToCollection(
					programRefSubresponse, CodeResponseEvent.class);
			Collections.sort(programRefSubStatusRespList);
			
			List mergedProgramReferralStatusAndSubStatus = new ArrayList();
						
			Iterator statusIter= programRefStatusRespList.iterator();
			while(statusIter.hasNext()){
				
				CodeResponseEvent programRefStatus = (CodeResponseEvent)statusIter.next();
				
				if (programRefStatus.getCode().equalsIgnoreCase(ProgramReferralConstants.CLOSED)) {
					
					//Add CLOSED by itself
					mergedProgramReferralStatusAndSubStatus.add(programRefStatus);
					
					Iterator subStatusIter= programRefSubStatusRespList.iterator();
					while(subStatusIter.hasNext()){
						CodeResponseEvent programRefSubStatus = (CodeResponseEvent)subStatusIter.next();
						
						String code = programRefSubStatus.getCode();
						
						if (code.equalsIgnoreCase(ProgramReferralConstants.REJECTED) ||
								code.equalsIgnoreCase(ProgramReferralConstants.WITHDRAWN) ||
								code.equalsIgnoreCase(ProgramReferralConstants.CANCELLED) ||
								code.equalsIgnoreCase(ProgramReferralConstants.COMPLETED) ||
								code.equalsIgnoreCase(ProgramReferralConstants.ACCEPTEDANDCLOSED) ||
								code.equalsIgnoreCase(ProgramReferralConstants.ACCEPTED)) 
						{
							
							CodeResponseEvent codeResponseEvent = new CodeResponseEvent();
							codeResponseEvent.setCode(programRefStatus.getCode() + programRefSubStatus.getCode());
							codeResponseEvent.setDescription(programRefStatus.getDescription() + " " + programRefSubStatus.getDescription());
							mergedProgramReferralStatusAndSubStatus.add(codeResponseEvent);
							
						} 
						
					}
					
				} else if (programRefStatus.getCode().equalsIgnoreCase(ProgramReferralConstants.TENTATIVE)) {
					
					//Add TENTATIVE by itself
					mergedProgramReferralStatusAndSubStatus.add(programRefStatus);
					
					Iterator subStatusIter= programRefSubStatusRespList.iterator();
					while(subStatusIter.hasNext()){
						CodeResponseEvent programRefSubStatus = (CodeResponseEvent)subStatusIter.next();
						
						String code = programRefSubStatus.getCode();
						
						if (code.equalsIgnoreCase(ProgramReferralConstants.REFERRED) ||
								code.equalsIgnoreCase(ProgramReferralConstants.ACCEPTEDWITHCHANGES) ) 
						{
							
							CodeResponseEvent codeResponseEvent = new CodeResponseEvent();
							codeResponseEvent.setCode(programRefStatus.getCode() + programRefSubStatus.getCode());
							codeResponseEvent.setDescription(programRefStatus.getDescription() + " " + programRefSubStatus.getDescription());
							mergedProgramReferralStatusAndSubStatus.add(codeResponseEvent);
							
						} 
					}
				
				//ProgramReferralConstants.ACCEPTED and all other future Program Referral Status would fall here
				} else {
					
					CodeResponseEvent codeResponseEvent = new CodeResponseEvent();
					codeResponseEvent.setCode(programRefStatus.getCode());
					codeResponseEvent.setDescription(programRefStatus.getDescription());
					
					mergedProgramReferralStatusAndSubStatus.add(codeResponseEvent);
				}
				
				Collections.sort(mergedProgramReferralStatusAndSubStatus);	
				
			}
			
			form.setProgramsReferralStatus(mergedProgramReferralStatusAndSubStatus) ;

			forward = UIConstants.SUCCESS;
		}
		
		return aMapping.findForward(forward);
	}
	
	public ActionForward populateProgramNameList(
                        		ActionMapping aMapping,
                        		ActionForm aForm,
                        		HttpServletRequest aRequest,
                        		HttpServletResponse aResponse){
	    
	    	ProgramReferralSearchForm form =  (ProgramReferralSearchForm) aForm;
		form.clear();
		String forward = null;
		
		String serviceProviderId = SecurityUIHelper.getServiceProviderId();
		if(serviceProviderId == null){
		    
			this.saveErrors(aRequest,"error.serviceProvider.invalidUser");
			forward = UIConstants.FAILURE;
			
		} else {
		    
		    	String referralStatus = aRequest.getParameter("status");
		    	
		    	if(referralStatus != null && !referralStatus.equals("")){
		    	    
		    	    form.setReferralStatusDescription(referralStatus);
		    	    
		    	    if(referralStatus.contains("AC") || referralStatus.contains("TN")){
		    		//Set Program List
				Collection pgmList = UIServiceProviderHelper.getActivePrograms( serviceProviderId ) ;
				Collection sortedPgmList = UIServiceProviderHelper.sortResults(pgmList, "P");
				Collections.sort( (ArrayList)sortedPgmList );
				form.setPrograms( sortedPgmList );
		    	    } else {
		    		
		    		Collection pgmList = UIServiceProviderHelper.getPrograms( serviceProviderId ) ;
				Collection sortedPgmList = UIServiceProviderHelper.sortResults(pgmList, "P");
				Collections.sort( (ArrayList)sortedPgmList );
				form.setPrograms( sortedPgmList );				
		    	    }
		    	} else {
		    	    
        		    	Collection pgmList = UIServiceProviderHelper.getPrograms( serviceProviderId ) ;
        			Collection sortedPgmList = UIServiceProviderHelper.sortResults(pgmList, "P");
        			Collections.sort( (ArrayList)sortedPgmList );
        			form.setPrograms( sortedPgmList );
		    	}
		    	
		    	forward = UIConstants.SUCCESS;
		}
		
		return aMapping.findForward(forward);
	}
	
	private void testTableData(Iterator codeTables)
	{
		while (codeTables.hasNext())
		{
			CodeTableResponseEvent codeTable =
				(CodeTableResponseEvent) codeTables.next();
			
			System.out.println(codeTable.getCodeTableName());
		}
	}
	
	/**
	 * @param aRequest
	 * @param errorkey
	 */
	private void saveErrors(HttpServletRequest aRequest, String errorKey)
	{
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey, SecurityUIHelper.getLogonId()));
		saveErrors(aRequest, errors);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "displayProgramSearch");
		keyMap.put("button.programlist", "populateProgramNameList");
	}
}
