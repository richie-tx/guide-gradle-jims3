/*

 * Created on Sept 04, 2013

 *

 * TODO To change the template for this generated file go to

 * Window - Preferences - Java - Code Style - Code Templates

  */

package ui.juvenilecase.caseplan.action;




import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.caseplan.GetGenerateCaseplanDetailsEvent;
import messaging.caseplan.reply.SaveCaseplanDataResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.UIJuvenileCaseplanHelper;
import ui.juvenilecase.caseplan.form.CaseplanForm;

//import com.ibm.ws.objectManager.Collection;

/**
 * @author
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
  * Code Style - Code Templates
 */

public class DisplayGenerateCaseplanDetailsAction extends JIMSBaseAction
{

  /*
     * (non-Javadoc)
     *  
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
	   protected void addButtonMapping(Map keyMap)
	    { 
	       keyMap.put("button.link", "link");
	        
	   }


	    public ActionForward link( 
				ActionMapping aMapping,
				 ActionForm aForm,
				 HttpServletRequest aRequest, 
				HttpServletResponse aResponse)
	    {	    	

	    CaseplanForm form = (CaseplanForm) aForm;
        form.setPriorServices(null); 
        form.setContactInformation(null);        
        
        UIJuvenileCaseplanHelper.fetchCaseplanDetails( form ) ; 
        String caseplanId = form.getCurrentCaseplan().getCaseplanId();
        if (caseplanId !=null && caseplanId != "")
        {
        Collection coll = CodeHelper.getActiveCodesByStatus( "JUV_SUPERVISION_LEVEL", true ) ;
    	form.setSupervisionLevelList(coll);
    	form.setSupervisionLevelId(null);
    	form.setJuvFosterCareCandidateStr(null);
    	
    		
    	//added for User story 11191 Add Title IV in caseplan
    	
    	form.setSocialHistDated(null);
    	form.setPsychologicalRepDated(null);
    	form.setRiskAssesmentDated(null);
    	form.setOtherDated(null);
    	form.setExplanation(null);
    	form.setTitleIVEComment(null);
    	form.setDtDeterminationMade(null);
    	//ended
    	
    	//added for user story 11146 
    	form.setOthername(null);
    	form.setChildDtNotified(null);
    	form.setFamilyDtNotified(null);
    	form.setCaregiverDtNotified(null);
    	form.setOtherDtNotified(null);
    	form.setChildNotifMethod(null);
    	form.setFamilyNotifMethod(null);
    	form.setCaregiverNotifMethod(null);
    	form.setOtherNameNotifMethod(null);
    	form.setChildDtOfParticipation(null);
    	form.setFamilyDtOfParticipation(null);
    	form.setCaregiverDtOfParticipation(null);
    	form.setOtherNameDtOfParticipation(null);
    	form.setChildMailedDt(null);
    	form.setFamilyMailedDt(null);
    	form.setCaregiverMailedDt(null);
    	form.setOtherNameMailedDt(null);
    	//ended
    	
        GetGenerateCaseplanDetailsEvent evt = new GetGenerateCaseplanDetailsEvent();
       
        	
        	evt.setCaseplanId(form.getCurrentCaseplan().getCaseplanId()); 
	        evt.setCasefileId(form.getCasefileId()); 
	        
	        CompositeResponse replyEvent = MessageUtil.postRequest(evt);
	
			        
	        SaveCaseplanDataResponseEvent respEvt = 
	        	(SaveCaseplanDataResponseEvent)MessageUtil.filterComposite(replyEvent,SaveCaseplanDataResponseEvent.class);
			
			
	        form.setCaseplanExist("Y");
	        form.setPriorServices(respEvt.getPriorServices()); 
	        form.setContactInformation(respEvt.getContactInfo());
	        form.setSupervisionLevelId(respEvt.getSupLevelId());
	        form.setSupervisionLevel(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUV_SUPERVISION_LEVEL, respEvt.getSupLevelId()));
	        //added for user story 11146
	        form.setOthername(respEvt.getOthername());
	        form.setChildDtNotified(respEvt.getChildDtNotified());
	        form.setFamilyDtNotified(respEvt.getFamilyDtNotified());
	        form.setCaregiverDtNotified(respEvt.getCaregiverDtNotified());
	        form.setOtherDtNotified(respEvt.getOtherDtNotified());
	        form.setChildNotifMethod(respEvt.getChildNotifMethod());
	        form.setFamilyNotifMethod(respEvt.getFamilyNotifMethod());
	        form.setCaregiverNotifMethod(respEvt.getCaregiverNotifMethod());
	        form.setOtherNameNotifMethod(respEvt.getOtherNameNotifMethod());
	        form.setChildDtOfParticipation(respEvt.getChildDtOfParticipation());
	        form.setFamilyDtOfParticipation(respEvt.getFamilyDtOfParticipation());
	        form.setCaregiverDtOfParticipation(respEvt.getCaregiverDtOfParticipation());
	        form.setOtherNameDtOfParticipation(respEvt.getOtherNameDtOfParticipation());
	        form.setChildMailedDt(respEvt.getChildMailedDt());
	        form.setFamilyMailedDt(respEvt.getFamilyMailedDt());
	        form.setCaregiverMailedDt(respEvt.getCaregiverMailedDt());
	        form.setOtherNameMailedDt(respEvt.getOtherNameMailedDt());
	        //ended
	        //added for title IV 	        
	        form.setJuvFosterCareCandidate(respEvt.isJuvFosterCareCandidate());
	    	form.setSocialHistDated(respEvt.getSocialHistDated());
	    	form.setPsychologicalRepDated(respEvt.getPsychologicalRepDated());
	    	form.setRiskAssesmentDated(respEvt.getRiskAssesmentDated());
	    	form.setOtherDated(respEvt.getOtherDated());
	    	form.setExplanation(respEvt.getExplanation());
	    	form.setTitleIVEComment(respEvt.getTitleIVEComment());
	    	form.setDtDeterminationMade(respEvt.getDtDeterminationMade());
	    	
	    	String determinationDt = form.getDtDeterminationMade();
	    	if(determinationDt == null || determinationDt.equals("")){
	    		form.setJuvFosterCareCandidateStr("");
	    	}
	        form.setCaseplanInfoFrmDb( "fromDb" ); 
	        //ended
        }
        else{
        		form.setCaseplanExist("N");
        	}
     return aMapping.findForward(UIConstants.SUCCESS);   
	    }
	    
	    
}