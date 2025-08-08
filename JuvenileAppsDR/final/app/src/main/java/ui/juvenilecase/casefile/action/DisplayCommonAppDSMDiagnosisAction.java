//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\casefile\\action\\DisplayCommonAppPlacementHistoryAction.java

package ui.juvenilecase.casefile.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.UpdateExitReportDSMDiagnosisEvent;
import messaging.casefile.reply.DSMDiagnosisResponseEvent;
import messaging.mentalhealth.GetDSMIVResultsEvent;
import messaging.mentalhealth.GetMentalHealthDSMTestDataEvent;
import messaging.mentalhealth.reply.DSMIVTestResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.casefile.CasefileClosingHelper;
import ui.juvenilecase.casefile.form.CommonAppForm;

public class DisplayCommonAppDSMDiagnosisAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 46CF1AD503C3
    */
   public DisplayCommonAppDSMDiagnosisAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 46CF186B0099
    */
   public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		CommonAppForm commonAppForm = (CommonAppForm) aForm;
   		if(!commonAppForm.isDsmDiagnosisFilled()) 
   		{
	   		GetDSMIVResultsEvent listEvent = (GetDSMIVResultsEvent)EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETDSMIVRESULTS);
			listEvent.setJuvenileNum(commonAppForm.getJuvenileNum());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(listEvent);
			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			Collection coll = MessageUtil.compositeToCollection(response, DSMIVTestResponseEvent.class);
			Collections.sort((List<DSMIVTestResponseEvent>) coll);//ER Changes 13222(Show most recent DSM record)
			Iterator iter = coll.iterator();	
			boolean found;
			Collection testResults = new ArrayList();
			while(iter.hasNext())
			{
				DSMIVTestResponseEvent resp = (DSMIVTestResponseEvent)iter.next();
				found=false;
				if(commonAppForm.isDsmDiagnosisExists())
				{
					Collection existingDiagnosis = commonAppForm.getDsmResults();
					Iterator existingIter = existingDiagnosis.iterator();
					while(existingIter.hasNext())
					{
						CommonAppForm.Diagnosis diag = (CommonAppForm.Diagnosis)existingIter.next();
						if(diag.getTestId().equals(resp.getTestId()))
						{
							diag.setDsmDiagnosisFound(true);
							testResults.add(diag);							
							found=true;
							break;
						}					
					}
					if(!found){
						if(!CasefileClosingHelper.getDSMDiagnosis(resp).getDiagnosisResults().isEmpty())
							testResults.add(CasefileClosingHelper.getDSMDiagnosis(resp));
					}
				}
				else{
					if(!CasefileClosingHelper.getDSMDiagnosis(resp).getDiagnosisResults().isEmpty())
						testResults.add(CasefileClosingHelper.getDSMDiagnosis(resp));
				}
				break; //ER Changes 13222(Show most recent DSM record)
			}
			commonAppForm.setDsmResults(testResults);
   		}
		commonAppForm.setDsmDiagnosisFilled(true);	
		return aMapping.findForward(UIConstants.SUCCESS);
   }

   public ActionForward save(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	CommonAppForm commonAppForm = (CommonAppForm) aForm;
	commonAppForm.setAction(UIConstants.CONFIRM_UPDATE);
	commonAppForm.setDsmDiagnosisFilled(true);
	if(commonAppForm.getDsmResults()!=null && commonAppForm.getDsmResults().size()!=0)
	{
		getDSMDiagnosisDesc(commonAppForm.getDsmResults());
	}
	if(commonAppForm.isExitReportFilled() && commonAppForm.getDsmResults()!=null && commonAppForm.getDsmResults().size()!=0)
   	{
   		Collection coll = commonAppForm.getDsmResults();
    	Iterator iter = coll.iterator();
    	while(iter.hasNext())
    	{
    		CommonAppForm.Diagnosis diagnosis = (CommonAppForm.Diagnosis)iter.next();
    		String testId = diagnosis.getTestId();
    		Collection diagnosisResults = diagnosis.getDiagnosisResults();    		
    		Iterator diagnosisResultsIter = diagnosisResults.iterator();
    		while(diagnosisResultsIter.hasNext())
    		{
    			DSMDiagnosisResponseEvent diagnosisResp = (DSMDiagnosisResponseEvent)diagnosisResultsIter.next();
    			if(diagnosisResp.getDiagnosis()!=null && !diagnosisResp.getDiagnosis().equals(""))
    			{
	    			UpdateExitReportDSMDiagnosisEvent event =
	    	        	(UpdateExitReportDSMDiagnosisEvent) EventFactory.getInstance(
	    	        JuvenileCasefileControllerServiceNames.UPDATEEXITREPORTDSMDIAGNOSIS);
	    	        event.setCasefileCosingInfoId(commonAppForm.getClosingInfoId());
	    	        event.setTestSessId(testId);
	    	        event.setDiagnosisCd(diagnosisResp.getDiagnosisCd());
	    	        event.setConditionCd(diagnosisResp.getConditionCd());
	    	        event.setSeverityCd(diagnosisResp.getSeverityCd());
	    	        event.setDsmDiagnosisId(diagnosisResp.getDsmDiagnosisId());
	    	        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    	        dispatch.postEvent(event);
	    			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	    			ReturnException returnException = (ReturnException) MessageUtil.filterComposite(compositeResponse, ReturnException.class);
	    			if(returnException!=null)
	    			{
	    				sendToErrorPage(aRequest,"error.common");
	    			}
    			}
    		}
    	}
    	//ER changes 11046 starts
    	if(commonAppForm.getReportType().equals(UIConstants.CAFH)||commonAppForm.getReportType().equals(UIConstants.CASP)){
    		return aMapping.findForward(UIConstants.CA_FAMILYHISTORY_ERQ_SUMMARY_TAB);
    	}
    	//ER changes 11046 ends
		return aMapping.findForward("summarySuccess");
   	}
   	else if(commonAppForm.isExitReportFilled() && (commonAppForm.getDsmResults()==null || commonAppForm.getDsmResults().size()==0)){
   		//ER changes 11046 starts
   		if(commonAppForm.getReportType().equals(UIConstants.CAFH )|| commonAppForm.getReportType().equals(UIConstants.CASP)){
    		return aMapping.findForward(UIConstants.CA_FAMILYHISTORY_ERQ_SUMMARY_TAB);
   		}
   		//ER changes 11046 ends
   		
   		return aMapping.findForward("summarySuccess");
   	}else{
	   		//ER changes 11046 starts
	   		if(commonAppForm.getReportType().equals(UIConstants.CAFH) || commonAppForm.getReportType().equals(UIConstants.CASP)){
	   			return aMapping.findForward(UIConstants.CA_SCREENINGPROFILE_ERQ_TAB);
	   		}
	   		//ER changes 11046 starts
   		}
		if(commonAppForm.getReportType().equals(UIConstants.CAER)){
			return aMapping.findForward("toExitReport");   		
		}
		
		return null;
   }
   
   private void getDSMDiagnosisDesc(Collection dsmRecs)
   {   
		Iterator iter = dsmRecs.iterator();
		while(iter.hasNext())
		{
			CommonAppForm.Diagnosis diagnosis = (CommonAppForm.Diagnosis)iter.next();	
			Collection diagnosisResults = diagnosis.getDiagnosisResults();	
			Iterator diagnosisResultsIter = diagnosisResults.iterator();
			while(diagnosisResultsIter.hasNext())
			{
				DSMDiagnosisResponseEvent resp = (DSMDiagnosisResponseEvent)diagnosisResultsIter.next();
		   	 	resp.setSeverity(SimpleCodeTableHelper.getDescrByCode("HEALTH_CONDITION_SEVERITY",resp.getSeverityCd()));
		   	 	resp.setCondition(SimpleCodeTableHelper.getDescrByCode("HEALTH_CONDITION_LEVEL",resp.getConditionCd()));
		   	 	resp.setDiagnosis(resp.getDiagnosis());	   	 
			}
		}
   }
   public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
        HttpServletResponse aResponse)
	{
	    return aMapping.findForward(UIConstants.CANCEL);
	}
	
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
	    return aMapping.findForward(UIConstants.BACK);
	}
	
   /* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {	
		keyMap.put("button.saveChanges", "save");
		keyMap.put("button.link", "link");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");		
	}
}
