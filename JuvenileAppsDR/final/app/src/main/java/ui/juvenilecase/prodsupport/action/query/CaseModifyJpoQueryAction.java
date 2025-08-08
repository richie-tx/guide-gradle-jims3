package ui.juvenilecase.prodsupport.action.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileByCasefileIdEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.officer.GetOfficerProfilesByAttributeEvent;
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

import pd.juvenilecase.JuvenileCasefile;

import ui.juvenilecase.form.ProdSupportForm;
import ui.juvenilecase.prodsupport.helpers.QueryObject;
import ui.security.SecurityUIHelper;



/**
 * @author rcarter
 * 
 */

public class CaseModifyJpoQueryAction extends Action {
	
	private Logger log = Logger.getLogger("CaseModifyJpoQueryAction");
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
		log.info("["+new Date()+"] ProdSupport ("+SecurityUIHelper.getLogonId().toUpperCase()+") - Modify JPO Query Casefile ID: "+casefileId);
		
		
		//Search and populate the casefile record
		/**
		 * Search for Casefiles
		 */
		GetJuvenileCasefileByCasefileIdEvent getCasefileEvent = (GetJuvenileCasefileByCasefileIdEvent)
		EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEBYCASEFILEID ) ;
		getCasefileEvent.setSupervisionNumber( casefileId ) ;
		
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( getCasefileEvent ) ;
	
		CompositeResponse aResponse = (CompositeResponse)dispatch.getReply( ) ;
		
		JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent)
			MessageUtil.filterComposite( aResponse, JuvenileCasefileResponseEvent.class ) ;
		
		if(casefile != null){
			/** Retrieve Officer IDs for logging purposes **/
			String officerCode = retrieveOfficerId(casefile.getProbationOfficeId());
			casefile.setProbationOfficeId(casefile.getProbationOfficeId());
			casefile.setProbationOfficerLogonId(officerCode);
			
			ArrayList casefileList = new ArrayList();
			casefileList.add(casefile);
			regform.setCasefiles(casefileList);
		}else{
			regform.setCasefiles(null);
		}
		
		
		if (regform.getCasefiles() == null || regform.getCasefiles().size() == 0)
		{
			regform.setMsg("No casefile records returned for casefileID "+casefileId);
			regform.setCasefiles(null);
			return mapping.findForward("error");
		}

		regform.setMsg("");
		return mapping.findForward("success");

	}	
	
	/**
	 * 	Retrieve the officer based on the numeric value.
	 *  
	 * @param uvcode
	 * @return
	 */
	public static String retrieveOfficerId(String officerId){
		String existingOfficerId = null;

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
					existingOfficerId = event.getUserId();
			}	
		}
		return existingOfficerId;
	}

}
