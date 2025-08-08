/*

 * Created on Oct 22, 2013

 *

 * TODO To change the template for this generated file go to

 * Window - Preferences - Java - Code Style - Code Templates

  */

package ui.juvenilecase.caseplan.action;




import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.caseplan.GetGenerateCaseplanDetailsEvent;
import messaging.caseplan.reply.SaveCaseplanDataResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.juvenilecase.caseplan.form.CaseplanForm;

//import com.ibm.ws.objectManager.Collection;

/**
 * @author
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
  * Code Style - Code Templates
 */

public class DisplayCreateGenerateCaseplanConfirmAction extends JIMSBaseAction
{

  /*
     * (non-Javadoc)
     *  
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
	 protected void addButtonMapping(Map keyMap)
	    {
	        keyMap.put("button.next", "next");
	        keyMap.put("button.cancel", "cancel");
	        keyMap.put("button.back", "back");
	        keyMap.put( "button.update", "updateCaseplan" ) ;
	    }




	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		CaseplanForm form = (CaseplanForm)aForm ;
		String forwardString = UIConstants.CANCEL ;
		if( form.isJuvProfile( ) )
		{
			forwardString = "cancel" ;
		}

		ActionForward forward = aMapping.findForward( forwardString ) ;
		return forward ;
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return aMapping.findForward( UIConstants.BACK ) ;
	}
	
	
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 42F79A090282
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
	{
        CaseplanForm form = (CaseplanForm) aForm;
        
        //added for User story 11191 Add Title IV in caseplan: wipe out the non required fields when juv is NOT foster care
        if (form.getJuvFosterCareCandidateStr() != null && form.getJuvFosterCareCandidateStr().equalsIgnoreCase("No") ){
        	form.setPsychologicalRepDated(null);
        	form.setRiskAssesmentDated(null);
        	form.setOtherDated(null);
        	form.setExplanation(null);
        	form.setSocialHistDated(null);
        	
        }
        //ended
        
        form.setSecondaryAction(UIConstants.EMPTY_STRING);
        CaseplanForm.GoalInfo newGoal = form.getCurrentGoalInfo();
        /*if (newGoal.getStatusCd() != null && newGoal.getStatusCd().equals(PDCodeTableConstants.GOAL_STATUS_APPROVED))
        {
            if (newGoal.majorGoalChange())
            {
                String endRecomm = newGoal.getEndRecommendations();
                if (endRecomm != null && endRecomm.trim().length() != 0)
                {
                    sendToErrorPage(aRequest, "error.generic", "This caseplan cannot be both modified and have end recommendations at the same time");
                    return aMapping.findForward(UIConstants.FAILURE);
                }
            }
        }*/
        form.setSecondaryAction("CASEPLANFLOW");        
        aRequest.setAttribute("status", "summary");
        form.setCaseplanInfoFrmDb( "notDb" );
        return aMapping.findForward(UIConstants.SUCCESS);
    }
	       /* if (myForm.getAction().equals("createMember"))
	            myForm.setAction("createMemberSummary");
	        else if (myForm.getAction().equals("updateMember"))
	            myForm.setAction("updateMemberSummary");
	        ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
	        return forward;*/
    
    public ActionForward updateCaseplan( 
			ActionMapping aMapping,
			 ActionForm aForm,
			 HttpServletRequest aRequest, 
			HttpServletResponse aResponse)
    {	    	

    CaseplanForm form = (CaseplanForm) aForm;
    form.setPriorServices(null); 
    form.setContactInformation(null);
    Collection coll = CodeHelper.getCodes( "JUV_SUPERVISION_LEVEL", true ) ;
	form.setSupervisionLevelList(coll);
	
	
	form.setJuvFosterCareCandidateStr(null);
	form.setPsychologicalRepDated(null);
	form.setRiskAssesmentDated(null);
	form.setOtherDated(null);
	form.setExplanation(null);
	form.setSocialHistDated(null);
	form.setTitleIVEComment(null);
    
    
    GetGenerateCaseplanDetailsEvent evt = new GetGenerateCaseplanDetailsEvent();
    evt.setCaseplanId(form.getCurrentCaseplan().getCaseplanId()); 
    evt.setCasefileId(form.getCasefileId()); 
    
    CompositeResponse replyEvent = MessageUtil.postRequest(evt);

	        
    SaveCaseplanDataResponseEvent respEvt = 
    	(SaveCaseplanDataResponseEvent)MessageUtil.filterComposite(replyEvent,SaveCaseplanDataResponseEvent.class);
	
	

    form.setPriorServices(respEvt.getPriorServices()); 
    form.setContactInformation(respEvt.getContactInfo());
    form.setSupervisionLevelId(respEvt.getSupLevelId());
    form.setJuvFosterCareCandidate(respEvt.isJuvFosterCareCandidate());
	form.setPsychologicalRepDated(respEvt.getPsychologicalRepDated());
	form.setRiskAssesmentDated(respEvt.getRiskAssesmentDated());
	form.setOtherDated(respEvt.getOtherDated());
	form.setExplanation(respEvt.getExplanation());
	form.setSocialHistDated(respEvt.getSocialHistDated());
	form.setTitleIVEComment(respEvt.getTitleIVEComment());
    return aMapping.findForward("updateCaseplan");
}
	    
	 

}