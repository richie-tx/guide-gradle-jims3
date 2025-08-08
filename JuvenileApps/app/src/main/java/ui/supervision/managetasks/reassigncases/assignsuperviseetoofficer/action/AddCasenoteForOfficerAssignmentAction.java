package ui.supervision.managetasks.reassigncases.assignsuperviseetoofficer.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.reply.LightProgramUnitResponseEvent;
import messaging.administerprogramreferrals.GetProgramReferralsEvent;
import messaging.administerprogramreferrals.reply.CSProgramReferralResponseEvent;
import messaging.cscdstaffposition.GetLightProgramUnitEvent;
import messaging.csserviceprovider.GetProgramUnitProgramEvent;
import messaging.csserviceprovider.reply.CSProgramResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSAdministerServiceProviderConstants;
import naming.CSCDStaffPositionControllerServiceNames;
import naming.CSProgramReferralControllerServiceNames;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.administercaseload.form.CaseAssignmentForm;



public class AddCasenoteForOfficerAssignmentAction extends JIMSBaseAction {
	/**
	 * @roseuid 464368F103D5
	 */
	public AddCasenoteForOfficerAssignmentAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("assign.supervisee.setup", "displayCaseNote");				
		keyMap.put("button.assignsuperviseetoofficer.selectofficer.addCasenote", "addCaseNote");
	}

	public ActionForward displayCaseNote(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) aForm;
		
		//Clear switches
		caseAssignmentForm.setProgramUnitRef( false );
		caseAssignmentForm.setProgramRefInfo( false );
		caseAssignmentForm.setCloseProgramUnitRef( false );
		
		String currentStateCd = getStateReportingCode( caseAssignmentForm.getProgramUnitId() );
		caseAssignmentForm.setStateReportingCode( currentStateCd );
		
		if ( "Y".equals( currentStateCd) || "W".equals( currentStateCd ) ){
			
			caseAssignmentForm.setProgramRefInfo( true );
			caseAssignmentForm.setProgramUnitRef( true );
		}
	
		
		// Get existing program referrals
		GetProgramReferralsEvent reqEvent= ( GetProgramReferralsEvent ) 
					EventFactory.getInstance( CSProgramReferralControllerServiceNames.GETPROGRAMREFERRALS );
		
		reqEvent.setDefendantId( caseAssignmentForm.getDefendantId());
		
		List referralList = MessageUtil.postRequestListFilter( reqEvent, CSProgramReferralResponseEvent.class );
		
		for ( int x =0 ; x < referralList.size() ; x++ ){

			CSProgramReferralResponseEvent refResponse = (CSProgramReferralResponseEvent) referralList.get( x );
			
			if ( !"E".equals( refResponse.getReferralStatusCode() ) && refResponse.isProgramUnitRef() ){
				
				GetProgramUnitProgramEvent getEvent = new GetProgramUnitProgramEvent();
				
				// If going to a non-state reported close old PU referral
				if ( currentStateCd == null ){
					
					caseAssignmentForm.setCloseProgramUnitRef(true);
	      			caseAssignmentForm.setProgramRefInfo( true ); 
					caseAssignmentForm.setCsProgReferralToCloseId( refResponse.getProgramReferralId() );
				
					// Else if going to a new PU and have an old not in same program 
				}else {
					
					getEvent.setProgramUnitId( caseAssignmentForm.getProgramUnitId() );
     				getEvent.setProgramStatus( CSAdministerServiceProviderConstants.ACTIVE_PROG_STATUS );
     				
     				List PUProgramList = MessageUtil.postRequestListFilter(getEvent, CSProgramResponseEvent.class );
     				
     				// See if the referral is in the same Program Unit
     				for ( int pu =0; pu < PUProgramList.size(); pu ++ ){
     					
     					CSProgramResponseEvent csProgram = (CSProgramResponseEvent) PUProgramList.get( pu );
     					String progId = csProgram.getProgramId();
     					
     					if ( progId != null && !progId.equals(refResponse.getProgramId() )){
     						
     					// only if the PU's don't match
	     					caseAssignmentForm.setCloseProgramUnitRef(true);
			      			caseAssignmentForm.setProgramRefInfo( true ); 
							caseAssignmentForm.setCsProgReferralToCloseId( refResponse.getProgramReferralId() );
							if( StringUtils.isNotEmpty(caseAssignmentForm.getProgramUnitAllocationDate())){
								caseAssignmentForm.setReferralDateAsStr(caseAssignmentForm.getProgramUnitAllocationDate());
							} else {
								caseAssignmentForm.setReferralDateAsStr(DateUtil.dateToString(refResponse.getReferralDate(), "MM/dd/yyyy"));
							}
							caseAssignmentForm.setProgramEndDateAsStr(DateUtil.dateToString(refResponse.getProgramEndDate(), "MM/dd/yyyy"));
							caseAssignmentForm.setCloseProgramUnitName(refResponse.getReferralTypeDesc());
     						
     					}else {
     						
     						caseAssignmentForm.setProgramRefInfo( false );
	     					caseAssignmentForm.setProgramUnitRef( false );
     						
     					}
     				}
				}
			}
		}
		
		// Build casenote
		StringBuffer sb = new StringBuffer();
		String caseNum = "";
		Iterator iterator = caseAssignmentForm.getCaseAssignments().iterator();
		while (iterator.hasNext()) {
			ICaseAssignment activeCase = (ICaseAssignment) iterator.next();
			caseNum = activeCase.getCriminalCaseId();
			if (caseNum != null & caseNum.length() > 3){
				sb.append( caseNum.substring(0,3) );
				sb.append( " " );
				sb.append( caseNum.substring(3,caseNum.length()) );
			}
			sb.append(", ");
			sb.append(activeCase.getCourtId());
			sb.append(", ");
			caseAssignmentForm.setCaseNum( activeCase.getCriminalCaseId() );
		}
		sb.append(caseAssignmentForm.getSuperviseeNameStr());
		sb.append(" SPN ");
		sb.append(caseAssignmentForm.getDefendantId());
		sb.append(" has been reassigned to ");
		sb.append(caseAssignmentForm.getOfficerName().getLastName());
		sb.append(", ");
		sb.append(caseAssignmentForm.getOfficerName().getFirstName());
		sb.append(" on ");
		sb.append(caseAssignmentForm.getOfficerAssignmentDate());

		caseAssignmentForm.setCasenoteText(sb.toString());
		caseAssignmentForm.setCasenoteDate(caseAssignmentForm.getOfficerAssignmentDate());
		
		return aMapping.findForward("displayCaseNote");
	}

	public ActionForward addCaseNote(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward("summarize");
	}
	
	private String getStateReportingCode ( String pUnitId ){
		
		String stateRptCode = null;
		GetLightProgramUnitEvent getOrgEvent = ( GetLightProgramUnitEvent ) EventFactory.
		getInstance( CSCDStaffPositionControllerServiceNames.GETLIGHTPROGRAMUNIT );

		getOrgEvent.setOrganizationId( pUnitId );
		
		List orgList = MessageUtil.postRequestListFilter( getOrgEvent, LightProgramUnitResponseEvent.class );
		
		if ( orgList.size() > 0){
			
			LightProgramUnitResponseEvent response = (LightProgramUnitResponseEvent) orgList.get( 0 );
			stateRptCode = response.getStateReportingCD();
		}
		return stateRptCode;
	}

}
