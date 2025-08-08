package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.GetAssignmentsByCasefileIdEvent;
import messaging.juvenilecase.GetJuvenileCasefileByCasefileIdEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.activities.ActivitiesHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;



/**
 * @author rcarter
 * 
 * Add a row in the JCASSIGNMENT table.  
 * Use the same assmntadddate, serviceunitcd, and assmntlevelcd as the record that already exists.  
 * If more than one exists, use the oldest one, by createdate.
 * 
 */

public class CaseAddReferralQueryAction extends Action {

	private Logger log = Logger.getLogger("CaseAddReferralQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		
		
		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk!=null && clrChk.equalsIgnoreCase("Y"))
		{
			regform.clearAllResults();
			regform.setMsg("");
			return mapping.findForward("error");		
		}
		
		String casefileId = regform.getCasefileId();

		if (casefileId == null || casefileId.equals(" ")) {
			regform.setMsg("CasefileId was null.");
			return mapping.findForward("error");
		}
		
		//Clear the form		
		regform.clearAllResults();
		
		//Log the query attempt
		log.info("Casefile Query ID: "+ casefileId + " LogonId: " + SecurityUIHelper.getLogonId());
		
		//Search and populate the casefile, activities, assignments records
		regform = retrieveCasefileRecords(regform, casefileId);				
		if (regform.getCasefiles()==null || regform.getCasefileCount()==0){
			regform.setMsg("No casefile records returned for casefileID " + casefileId);
			return mapping.findForward("error");
		}
		
		regform = retrieveActivityRecords(regform, casefileId);	
		regform = retrieveAssignmentRecords(regform, casefileId);			
		regform.setMsg("");
		
		return mapping.findForward("success");
	}
	
	/**
	 * retrieve casefile records by casefileId
	 * @param regform
	 * @param casefileId
	 * @return
	 */
	public static ProdSupportForm retrieveCasefileRecords(ProdSupportForm regform, String casefileId)
	{
		ArrayList casefiles = new ArrayList();
		GetJuvenileCasefileByCasefileIdEvent getCasefileEvent = (GetJuvenileCasefileByCasefileIdEvent)
				EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEBYCASEFILEID ) ;
		getCasefileEvent.setSupervisionNumber( casefileId ) ;				
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( getCasefileEvent ) ;
		CompositeResponse aResponse = (CompositeResponse)dispatch.getReply( ) ;		
		JuvenileCasefileResponseEvent casefile = 
					(JuvenileCasefileResponseEvent)MessageUtil.filterComposite( aResponse, JuvenileCasefileResponseEvent.class );
		if(casefile != null && casefile.getSupervisionNum() != null){
			casefiles.add(casefile);
			regform.setCasefileCount(casefiles.size());	
			regform.setCasefiles(casefiles);
		}else{
			regform.setCasefileCount(0);	
			regform.setCasefiles(null);
		}
		

		return regform;
	
	}
	
	/**
	 * retrieve activity records by casefileId
	 * @param regform
	 * @param casefileId
	 * @return
	 */
	public static ProdSupportForm retrieveActivityRecords(ProdSupportForm regform, String casefileId)
	{	
		ArrayList caseFileActivitiesList = (ArrayList)ActivitiesHelper.getActivities(casefileId, null, null, null, null);
		if (caseFileActivitiesList != null){
			regform.setActivityCount(caseFileActivitiesList.size());
			regform.setActivities(caseFileActivitiesList);
		}

		return regform;
	
	}
	
	
	/**
	 * retrieve assignment records by casefileId
	 * @param regform
	 * @param casefileId
	 * @return
	 */
	public static ProdSupportForm retrieveAssignmentRecords(ProdSupportForm regform, String casefileId)
	{	
		GetAssignmentsByCasefileIdEvent getAssignments = (GetAssignmentsByCasefileIdEvent)
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETASSIGNMENTSBYCASEFILEID);
		getAssignments.setCasefileId(casefileId);
		CompositeResponse assignmentResponse = MessageUtil.postRequest(getAssignments);
		ArrayList<AssignmentResponseEvent> assignmentList = (ArrayList) MessageUtil.compositeToCollection(assignmentResponse,
			AssignmentResponseEvent.class);	
		Collections.sort(assignmentList);
		if (assignmentList != null){
			regform.setAssignmentCount(assignmentList.size());
			regform.setAssignments(assignmentList);
		}
	
		return regform;
	}

}
