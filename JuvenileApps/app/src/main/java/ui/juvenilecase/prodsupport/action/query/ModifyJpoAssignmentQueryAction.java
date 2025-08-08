package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileByCasefileIdEvent;
import messaging.juvenilecase.reply.JPOAssignmentHistoryResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.officer.GetOfficerProfilesByAttributeEvent;
import messaging.productionsupport.GetProductionSupportJpoAssignmentHistoryEvent;
import messaging.productionsupport.UpdateProductionSupportCasefileEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;


/**
 * @author rcarter 
 * This Action updates the OFFICER_ID column for the requested
 * casefileID, and then retrieves associated JCJPOASSNMNTHIST records
 * that match the casefileId.
 * 
 */
public class ModifyJpoAssignmentQueryAction extends Action {

	private Logger log = Logger.getLogger("ModifyJpoAssignmentQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;		
		/** Retrieve casefileId and new JPO userId from the form **/
		String casefileId = regform.getCasefileId();
		String newJpoUserCode = regform.getNewJpoId().toUpperCase();
		String newJpoUserId = "";

		if (casefileId == null || casefileId.equals(" ")) {
			regform.setMsg("CasefileId was null.");
			return mapping.findForward("error");
		}

		/** Verify that the new JPO is valid and Active**/
		OfficerProfileResponseEvent activeNewOfficerProfile = retrieveOfficerProfileByOfficerCode(newJpoUserCode);
		

		if (activeNewOfficerProfile != null && newJpoUserCode != null && activeNewOfficerProfile.getUserId() != null) {
			log.info("Officer verified. UserID " + newJpoUserCode
					+ " matches active database Officer_ID:" + activeNewOfficerProfile.getOfficerId() + " Login Id: " + SecurityUIHelper.getLogonId());
			newJpoUserId = activeNewOfficerProfile.getOfficerId();
		} else {
			regform.setMsg("No active Officer records for that UserID.");
			return mapping.findForward("error");
		}
		
		JuvenileCasefileResponseEvent casefile = getCurrentCasefile(casefileId);
		String currentOfficerId = casefile.getProbationOfficeId();
		String currentOfficerLogonCode = retrieveOfficerCodeByOfficerId(currentOfficerId);

		if(newJpoUserCode != null && currentOfficerLogonCode != null && newJpoUserCode.trim().equalsIgnoreCase(currentOfficerLogonCode)) {
			regform.setMsg("Please enter a different Officer UV Code. " + newJpoUserCode + " is current Code.");
			return mapping.findForward("error");
		}

		/** Clear the form and log the attempt **/
		regform.clearAllResults();

		log.info(" Modify JPO Casefile ID: "+ casefileId + " Login Id: " + SecurityUIHelper.getLogonId());

		/**
		 * Perform the JPO Update first, then retrieve history records. This is
		 * done because a trigger is set off when the JPO on a casefile is
		 * changed, and the resulting record may need to be deleted.
		 */
		updateJpo(SecurityUIHelper.getLogonId(), casefileId, regform, activeNewOfficerProfile);

		// retrieve the list of AssessmentHistory records, and set them on the form to display them.
		GetProductionSupportJpoAssignmentHistoryEvent getAssignmentHistoryEvent = (GetProductionSupportJpoAssignmentHistoryEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJPOASSIGNMENTHISTORY) ;
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		getAssignmentHistoryEvent.setCasefileId(casefileId);
		dispatch.postEvent(getAssignmentHistoryEvent);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		Collection<JPOAssignmentHistoryResponseEvent> assignmentHistoryEvents =
				MessageUtil.compositeToCollection(compositeResponse, JPOAssignmentHistoryResponseEvent.class);

		ArrayList<JPOAssignmentHistoryResponseEvent> jpoAssignmentHistoryResponseEventList =  new ArrayList<JPOAssignmentHistoryResponseEvent>();
		if (assignmentHistoryEvents!=null && assignmentHistoryEvents.size() > 0){
			for(JPOAssignmentHistoryResponseEvent event:assignmentHistoryEvents){
				event.setJpoOfficerUserId(retrieveOfficerCodeByOfficerId(event.getOfficerProfileId()));
				jpoAssignmentHistoryResponseEventList.add(event);
			}
		}
		// set on the bean for the screens
		if (jpoAssignmentHistoryResponseEventList.size() > 0) {
			regform.setAssnmnthistCount(jpoAssignmentHistoryResponseEventList.size());
			regform.setAssnmnthists(jpoAssignmentHistoryResponseEventList);
		}	
		// set current and previous officer uv codes
		regform.setPreviousJpoId(currentOfficerId);
		regform.setPreviousJpoCode(currentOfficerLogonCode);
		regform.setNewJpoId(newJpoUserId);
		regform.setNewJpoCode(newJpoUserCode);
		
		
		regform.setMsg("");
		return mapping.findForward("success");
	}
	
	/**
	 * Update the officer id on the casefile record with the new verified officer id
	 * @param logonid
	 * @param casefileId
	 * @param regform
	 */
	private void updateJpo(String logonid, String casefileId,
			ProdSupportForm regform, OfficerProfileResponseEvent  verifiedNewOfficer) {

		String newJPOfficer = null;
		newJPOfficer = regform.getNewJpoId().toUpperCase();

		/** Update the Casefile Record with new JPO **/
		UpdateProductionSupportCasefileEvent updateEvent = (UpdateProductionSupportCasefileEvent)
		EventFactory.getInstance( ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTCASEFILE );
		
		updateEvent.setSupervisionId(casefileId);
		updateEvent.setJpoOfficerId(verifiedNewOfficer.getOfficerId());
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( updateEvent ) ;		
		log.info(" - Performed a MODIFY JPO for CASEFILE_ID=" + casefileId
				+ ", OFFICER_ID to " + verifiedNewOfficer.getOfficerId() + " LogonId: " + logonid);
			
		regform.clearAllResults();
		
		JuvenileCasefileResponseEvent casefile = getCurrentCasefile(casefileId);
		ArrayList casefileList = new ArrayList();
		casefileList.add(casefile);
		if (casefile != null) {
			regform.setCasefiles(casefileList);
		}else{
			regform.setCasefiles(null);
		}
	}

	/**
	 * 	Retrieve the Numerical (OID)officerId by looking up officer profile by alpha logonId
	 *  
	 * @param 
	 * @return
	 */
	public static OfficerProfileResponseEvent retrieveOfficerProfileByOfficerCode(String logonId){

		OfficerProfileResponseEvent activeOfficerProfile = null;
		List officerprofiles = new ArrayList<OfficerProfileResponseEvent>();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetOfficerProfilesByAttributeEvent getOfficerEvent = new GetOfficerProfilesByAttributeEvent();
		
		getOfficerEvent.setAttributeName("logonId");
		getOfficerEvent.setAttributeValue(logonId);
		dispatch.postEvent(getOfficerEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		officerprofiles = MessageUtil.compositeToList(response, OfficerProfileResponseEvent.class);
		
		if (officerprofiles!=null){
			Iterator iter = officerprofiles.iterator();
			while (iter.hasNext())
			{
				OfficerProfileResponseEvent event = (OfficerProfileResponseEvent)iter.next();
				if(event.getStatusId().equalsIgnoreCase("A")){
					activeOfficerProfile = event;
					break;
				}
			}	
		}
		return activeOfficerProfile;
	}
	
	/**
	 * 	Retrieve the alpha logonId by looking up officer profile by Numerical (OID)officerId
	 *  
	 * @param 
	 * @return
	 */
	public static String retrieveOfficerCodeByOfficerId(String officerId){

		OfficerProfileResponseEvent activeOfficerProfile = null;
		List officerprofiles = new ArrayList<OfficerProfileResponseEvent>();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetOfficerProfilesByAttributeEvent getOfficerEvent = new GetOfficerProfilesByAttributeEvent();
		
		getOfficerEvent.setAttributeName("OID");
		getOfficerEvent.setAttributeValue(officerId);
		dispatch.postEvent(getOfficerEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		officerprofiles = MessageUtil.compositeToList(response, OfficerProfileResponseEvent.class);
		
		if (officerprofiles!=null){
			Iterator iter = officerprofiles.iterator();
			if (iter.hasNext())
			{
				OfficerProfileResponseEvent event = (OfficerProfileResponseEvent)iter.next();
					activeOfficerProfile = event;
			}	
		}
		return activeOfficerProfile.getUserId();
	}
	
	/**
	 * Retrieve current casefile
	 * on the final summary page.
	 * @param regform
	 */
	private JuvenileCasefileResponseEvent getCurrentCasefile(String casefileId) {
		/** Retrieve fresh copy of updated casefile record **/
		GetJuvenileCasefileByCasefileIdEvent getCasefileEvent = (GetJuvenileCasefileByCasefileIdEvent)
		EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEBYCASEFILEID ) ;
		getCasefileEvent.setSupervisionNumber( casefileId ) ;
		
		IDispatch dispatchRetrieve = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatchRetrieve.postEvent( getCasefileEvent ) ;
	
		CompositeResponse aResponse = (CompositeResponse)dispatchRetrieve.getReply( ) ;
		
		JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent)
			MessageUtil.filterComposite( aResponse, JuvenileCasefileResponseEvent.class ) ;
		
		return casefile;

		}

}
