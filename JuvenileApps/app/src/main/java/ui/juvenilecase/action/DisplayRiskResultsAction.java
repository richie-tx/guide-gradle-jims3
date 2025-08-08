//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\DisplayRiskResultsAction.java

package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.RiskAssessmentListResponseEvent;
import messaging.riskanalysis.GetAllRiskAssessmentsEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIJuvenileCasefileStatusHelper;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;

public class DisplayRiskResultsAction extends Action 
{
   
   /**
    * @roseuid 433D8A0C0343
    */
   public DisplayRiskResultsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 433C3D3E0032
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
		RiskAnalysisForm riskForm = (RiskAnalysisForm)aForm;
		riskForm.setAction(UIConstants.EMPTY_STRING);
		riskForm.setSecondaryAction(UIConstants.EMPTY_STRING);
		riskForm.setRiskAssessmentType(UIConstants.EMPTY_STRING);
		riskForm.setRiskFormulaId(UIConstants.EMPTY_STRING);
		// get the juvenileNumber from the casefile form
		String juvNum = aRequest.getParameter("juvenileNum");
		if(juvNum != null && !juvNum.equals(UIConstants.EMPTY_STRING)){
			riskForm.setJuvenileNum(juvNum);
		}
		
		// get the juvenileNumber from the casefile form
		String casefileId = aRequest.getParameter("casefileID");
		if(casefileId != null && !casefileId.equals(UIConstants.EMPTY_STRING)){
			riskForm.setCasefileID(casefileId);
		}
	  	
		GetAllRiskAssessmentsEvent event =
			(GetAllRiskAssessmentsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETALLRISKASSESSMENTS);
		
		event.setJuvenileNumber(juvNum);
		event.setCaseFileID(casefileId);
		
		CompositeResponse composite = MessageUtil.postRequest(event);

		Collection riskAssessments = MessageUtil.compositeToCollection(composite, RiskAssessmentListResponseEvent.class);
		ArrayList riskAssessmentsList = new ArrayList();
		for(Iterator iter = riskAssessments.iterator(); iter.hasNext();)
		{	//49800 Remove Residential requirement
			RiskAssessmentListResponseEvent risk = (RiskAssessmentListResponseEvent)iter.next();
			if(!risk.getAssessmentType().equalsIgnoreCase("RESIDENTIAL")){
			    riskAssessmentsList.add(risk);
			}
			
		}
	
		Collections.sort(riskAssessmentsList);
		Collections.reverse(riskAssessmentsList);
		riskForm.setAssessmentList(riskAssessmentsList);

        riskForm.setAllowUpdates(UIJuvenileCasefileStatusHelper.casefileStatusClosed(aRequest));
		
		return aMapping.findForward("success");
   }
}