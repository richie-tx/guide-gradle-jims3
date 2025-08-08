package pd.productionsupport.transactions;

import java.util.ArrayList;
import java.util.List;

import naming.UIConstants;

import pd.juvenilecase.Assignment;
import pd.juvenilecase.JJSCorespondent;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.PACTRiskNeedLevel;
import pd.juvenilecase.casefile.CasefileClosingInfo;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.juvenilecase.mentalhealth.JuvenileMAYSI;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIMetadata;
import pd.juvenilecase.referral.JCVOP;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.referral.JJSSVIntakeHistory;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;
import pd.juvenilecase.riskanalysis.RiskResponse;
import pd.juvenilewarrant.Charge;
import pd.juvenilewarrant.JJSPetition;
import pd.juvenilewarrant.JuvenileAssociateAddress;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantCautionsCode;
import pd.juvenilewarrant.JuvenileWarrantScarsMarksScarsMarksTattoosCode;
import pd.juvenilewarrant.JuvenileWarrantServiceOfficer;
import pd.juvenilewarrant.JuvenileWarrantTattoosScarsMarksTattoosCode;
import pd.supervision.programreferral.JuvenileProgramReferral;
import ui.common.Name;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileReferralVOPResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.DeleteAssociatedMsReferalEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.DateUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

public class DeleteAssociatedMsReferalCommand implements ICommand 
{
    public void execute(IEvent event) {
	DeleteAssociatedMsReferalEvent deleteReferralEvent = (DeleteAssociatedMsReferalEvent) event;
	ProdSupportForm regform = (ProdSupportForm) deleteReferralEvent.getProdSupportForm();
	String juvenileId = regform.getJuvenileId();
	String referralId = regform.getReferralId();
	String delComments = regform.getDelComments();
	StringBuffer message = new StringBuffer();
	
	 IUserInfo user = SecurityUIHelper.getUser();
	 Name userName = new Name( user.getFirstName(), UIConstants.EMPTY_STRING, user.getLastName() );
	 message.append("<p>DELETE BY: " + userName.getFormattedName() + "</p>");
	 message.append("<p>DATE: " + DateUtil.getCurrentDateString( UIConstants.DATETIME24_FMT_1 ) + "</p>");
	 message.append("<p>JUVENILE #:" + juvenileId  + "</p>");
	 message.append("<p>REFERRAL #:" + referralId + "</p>");
	 message.append("<p>COMMENTS #:" + delComments + "</p>");
	 message.append("<hr>");
	
	//Delete referral
	ArrayList<JJSReferral> referrals =  regform.getJuvenileReferrals();
	String lastReferral 	= ""; 
	String newLastReferral 	= "";
	int referralSize	= 0;
	if ( referrals != null ) {
	    referralSize = referrals.size();
	}
	JJSReferral deletedReferral = null;
	if ( referralSize  > 0 ) {
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Referral ID</font></th>");
	    message.append("</tr>");
	    int lastRefPos = referralSize-1;
	    int newLastRefPos =  referralSize-2;
	    if (lastRefPos < 0){
		lastRefPos = 0;
	    }
	    
	    if (newLastRefPos < 0){
		newLastRefPos = 0;
	    }
	    lastReferral = referrals.get(lastRefPos).getReferralNum();
	    newLastReferral = referrals.get(newLastRefPos).getReferralNum();
	    for ( JJSReferral referral: referrals ){
		if (referralId.equals(referral.getReferralNum())){
		    deletedReferral = referral;
		    break;
		}
	    }
	    
	    if(delComments != null && deletedReferral != null)
	    {
		deletedReferral.setDelComments(delComments);
		new Home().bind(deletedReferral);
	    }
	    
	    if (deletedReferral != null) {
		deletedReferral.delete();
		new Home().bind(deletedReferral);
		
		JJSJuvenile juvenile = JJSJuvenile.find(juvenileId);
		if (juvenile!= null){
		    juvenile.setLastReferralNum(lastReferral);
		    juvenile.setReferralCount(String.valueOf(referralSize - 1));
		    new Home().bind(juvenile);
		
		
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + deletedReferral.getOID() + "</td>");
		message.append("</tr>");
		}
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete referral offense
	if (regform.getJuvRefOffensesCount() > 0 ) {
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Offense ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JJSOffenseResponseEvent>offenses = regform.getReferralOffenses();
	    for (JJSOffenseResponseEvent offenseResp : offenses ){
		JJSOffense offense = JJSOffense.find(offenseResp.getOID());
		if ( offense != null) {
		    offense.delete();
		    new Home().bind(offense);
		
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + offense.getOID() + "</td>");
		message.append("</tr>");
		}
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete intake history
	if (regform.getIntakeHistCount() > 0 ) {
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Intake History ID</font></th>");
	    message.append("</tr>");
	    List<JJSSVIntakeHistory> intakeHistories = regform.getIntakeHistoryRecords();
	    for (JJSSVIntakeHistory intakeHistory : intakeHistories){
		if (intakeHistory != null) {
		    intakeHistory.delete();
		     new Home().bind(intakeHistory);
		
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + intakeHistory.getOID() + "</td>");
		message.append("</tr>");
		}
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	//Delete transferred offense referral
	if (regform.getTransOffenseReferralsCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Transferred Offense Referral ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileCasefileTransferredOffenseResponseEvent>transOffenseReferrals = regform.getTransOffenseReferrals();
            for (JuvenileCasefileTransferredOffenseResponseEvent referral : transOffenseReferrals )  {
        	JJSTransferredOffenseReferral transOffenseReferral = JJSTransferredOffenseReferral.find(referral.getTransOffenseReferralId());
        	if (transOffenseReferral!= null){
        	    transOffenseReferral.delete();
        	    new Home().bind(transOffenseReferral);
        	
        	message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + transOffenseReferral.getOID() + "</td>");
		message.append("</tr>");
        	}
            }
            message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete petition
	if ( regform.getPetitionDetails() != null ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Petition ID</font></th>");
	    message.append("</tr>");
	    ArrayList<PetitionResponseEvent>petitions = ( ArrayList<PetitionResponseEvent>) regform.getPetitionDetails();
	    for (PetitionResponseEvent petitionRsp : petitions ) {
		JJSPetition petition = JJSPetition.findById(petitionRsp.getOID());
		if (petition != null){
		    petition.delete();
		    new Home().bind(petition);
		
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + petition.getOID() + "</td>");
		message.append("</tr>");
		}
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	
	//Delete detention facility
	if (regform.getFacilityDetentionList() != null){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Detention Facility ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileDetentionFacilitiesResponseEvent>detentionFacilities = regform.getFacilityDetentionList();
	    for(JuvenileDetentionFacilitiesResponseEvent facilityRsp : detentionFacilities ) {
		JJSFacility facility = JJSFacility.find("OID", facilityRsp.getDetentionId() );
		if ( facility != null) {
		    facility.delete();
		    new Home().bind(facility);
		
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + facility.getOID() + "</td>");
		message.append("</tr>");
		}
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete juvenile header
	if (regform.getFacilityHeaderCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Facility Header ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileFacilityHeaderResponseEvent>facilityHeaders = regform.getFacilityHeaderList();
	    for (JuvenileFacilityHeaderResponseEvent facilityHeaderRsp : facilityHeaders ){
		JJSHeader header = JJSHeader.findById(facilityHeaderRsp.getHeaderId());
		if (header != null){
		    header.delete();
		    new Home().bind(header);
		
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + header.getOID() + "</td>");
		message.append("</tr>");
		}
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete detention court record
	if (regform.getJuvDetCourtRecordCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Detention Court ID</font></th>");
	    message.append("</tr>");
	    ArrayList<DocketEventResponseEvent>detentionCourtDateRecords = regform.getJuvDetCourtRecords();
	    for (DocketEventResponseEvent docketRsp : detentionCourtDateRecords  ){
		JJSCLDetention clDetention = JJSCLDetention.find(docketRsp.getDocketEventId());
		if (clDetention!= null){
		    clDetention.delete();
		    new Home().bind(clDetention);
		
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + clDetention.getOID() + "</td>");
		message.append("</tr>");
		}
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	//Delete district court record
	if (regform.getJuvDistCourtRecordCount() > 0){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated District Court ID</font></th>");
	    message.append("</tr>");
	    ArrayList<DocketEventResponseEvent>distCourtRecords = regform.getJuvDistCourtRecords();
	    for(DocketEventResponseEvent docketRsp : distCourtRecords ){
		JJSCourt clCourt = JJSCourt.find( docketRsp.getDocketEventId() );
		if (clCourt != null){
		    clCourt.delete();
		    new Home().bind(clCourt);
		
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + clCourt.getOID() + "</td>");
		message.append("</tr>");
		}
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete casefile closing record
	if (regform.getCasefileclosingsDeleteCount() > 0 ){
	    ArrayList<CasefileClosingInfo>casefileClosingInfosDelete = regform.getCasefileclosingsDelete();
	    for (CasefileClosingInfo casefileClosingInfo : casefileClosingInfosDelete ){
		if ( casefileClosingInfo!= null ){
		    casefileClosingInfo.delete();
		    new Home().bind(casefileClosingInfo);
		}
	    }
	   
	}
	
	if (regform.getFilteredCasefileClosings() != null
		&& regform.getFilteredCasefileClosings().size() > 0  ) {
	    ArrayList<CasefileClosingInfo>casfileClosings = regform.getFilteredCasefileClosings();
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Casefile Closing ID</font></th>");
	    message.append("</tr>");
	    for (CasefileClosingInfo casfileClosing : casfileClosings  ){
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + casfileClosing.getOID() + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete assignment record
	if (regform.getAssignmentCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Assignment ID</font></th>");
	    message.append("</tr>");
	    ArrayList<Assignment>casefileAssignments = regform.getAssignments();
	    for ( Assignment assignment : casefileAssignments ) {
		if ( assignment != null){
		    assignment.delete();
		    new Home().bind(assignment);
		
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + assignment.getOID()  + "</td>");
		message.append("</tr>");
		}
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete juvenile program referral
	if (regform.getProgramReferralsDeleteCount() > 0 ){
	    ArrayList<JuvenileProgramReferral>juvenileProgramReferralsDelete = regform.getProgramReferralsDelete();
	    for (JuvenileProgramReferral juvenileProgramReferral : juvenileProgramReferralsDelete){
		if(juvenileProgramReferral != null){
		    juvenileProgramReferral.delete();
		    new Home().bind(juvenileProgramReferral);
		}
		
	    }
	   
	}
	
	if ( regform.getFilteredProgramReferrals() != null
		&& regform.getFilteredProgramReferrals().size() > 0  ){
	    ArrayList<JuvenileProgramReferral>programReferrals = regform.getFilteredProgramReferrals();
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Program Referral ID</font></th>");
	    message.append("</tr>");
	    for (JuvenileProgramReferral programReferral : programReferrals ) {
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + programReferral.getOID()  + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete casefile record
		if ( regform.getCasefileOneReferralDeleteCount()> 0 ){
		    ArrayList<JuvenileCasefileResponseEvent>casefiles = regform.getCasefiles();
		    for (JuvenileCasefileResponseEvent casefileRsp : casefiles){
			JuvenileCasefile casefile  = JuvenileCasefile.find(casefileRsp.getSupervisionNum());
			if (casefile != null && !casefileRsp.getMoreThanOneReferralNum()){
			    casefile.delete();
			    new Home().bind(casefile);
			}
			
		    }
		    
		}
	
		if (regform.getFilteredCasefiles() != null
			&&  regform.getFilteredCasefiles().size() > 0 ) {
		    ArrayList<JuvenileCasefileResponseEvent> casefiles = regform.getFilteredCasefiles();
		    message.append("<table>");
		    message.append("<tr>");
		    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Casefile ID</font></th>");
		    message.append("</tr>");
		    for (JuvenileCasefileResponseEvent casefile : casefiles ) {
			message.append("<tr>");
			message.append("<td style=' border-bottom: 1px solid #ddd;'>" + casefile.getSupervisionNum()   + "</td>");
			message.append("</tr>");
		    }
		    message.append("</table>");
		    message.append("<br>");
		}
		
	//Delete pact risk need level
	if (regform.getRiskNeedLevelCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Risk Need Level ID</font></th>");
	    message.append("</tr>");
	    ArrayList<PACTRiskNeedLevel> pactRiskNeedLevels = regform.getRiskNeedLevels();
	    for (PACTRiskNeedLevel pactRiskNeedLevel : pactRiskNeedLevels ){
		if (pactRiskNeedLevel != null){
		    pactRiskNeedLevel.delete();
		    new Home().bind(pactRiskNeedLevel);
		
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + pactRiskNeedLevel.getOID()  + "</td>");
		message.append("</tr>");
		}
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete risk response
	if (regform.getRiskresponsesCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Risk Response ID</font></th>");
	    message.append("</tr>");
	    ArrayList<RiskResponse>riskRsponses = regform.getRiskresponses();
	    for (RiskResponse riskResponse : riskRsponses ){
		if (riskResponse != null){
		    riskResponse.delete();
		    new Home().bind(riskResponse);
		
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + riskResponse.getOID() + "</td>");
		message.append("</tr>");
		}
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete maysi details
	if (regform.getMaysidetailCount() > 0 ) {
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated MAYSI Detail ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileMAYSI>maysiDetails = regform.getMaysidetails();
	    for (JuvenileMAYSI maysiDetail : maysiDetails){
		if (maysiDetail!= null){
		    maysiDetail.delete();
		    new Home().bind(maysiDetail);
		
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + maysiDetail.getOID() + "</td>");
		message.append("</tr>");
		}
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete maysi assessment record
	if ( regform.getMaysiCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated MAYSI Assessment ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileMAYSIMetadata> maysiAssessments = regform.getMaysis();
	    for ( JuvenileMAYSIMetadata maysiAssessment : maysiAssessments ) {
		if (maysiAssessment!= null){
		    maysiAssessment.delete();
		    new Home().bind(maysiAssessment);
		
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + maysiAssessment.getOID() + "</td>");
		message.append("</tr>");
		}
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete juvenile warrant charge
	if (regform.getJuvenileWarrantChargeCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Juvenile Warrant Charge Id</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileWarrant>jWarrantCharges = regform.getJuvenileWarrantCharges();
	    for (JuvenileWarrant jWarrantCharge : jWarrantCharges  ){
		/*if (jWarrantCharge != null){
		    jWarrantCharge.delete();
		    new Home().bind(jWarrantCharge);
		}*/
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + jWarrantCharge.getOID() + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete juvenile warrant field
	if (regform.getJuvenileWarrantFieldCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Juvenile Warrant Field ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileWarrant>jWarrantFields = regform.getJuvenileWarrantFields();
	    for (JuvenileWarrant jWarrantField : jWarrantFields ){
		/*if (jWarrantField != null){
		    jWarrantField.delete();
		    new Home().bind(jWarrantField);
		}*/
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + jWarrantField.getOID() + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete juvenile inactive warrant
	if (regform.getJuvenileInactivatedWarrantCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Juvenile Inactive Warrant ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileWarrant> inactivatedJWarrants = regform.getJuvenileInactivatedWarrants();
	    for (JuvenileWarrant inactivatedJWarrant : inactivatedJWarrants ) {
		/*if (inactivatedJWarrant != null){
		    inactivatedJWarrant.delete();
		    new Home().bind(inactivatedJWarrant);
		}*/
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + inactivatedJWarrant.getOID() + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete juvenile warrant recall
	if (regform.getJuvenileWarrantRecallCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Juvenile Warrant Recall ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileWarrant>jWarrantRecalls = regform.getJuvenileWarrantRecalls();
	    for (JuvenileWarrant jWarrantRecall : jWarrantRecalls) {
		/*if(jWarrantRecall != null){
		    jWarrantRecall.delete();
		    new Home().bind(jWarrantRecall);
		}*/
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + jWarrantRecall.getOID() + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete juvenile warrant associate
	if (regform.getJuvenileWarrantAssociateCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Juvenile Warrant Associate ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileWarrant>jWarrantAssociates = regform.getJuvenileWarrantAssociates();
	    for (JuvenileWarrant jWarrantAssociate : jWarrantAssociates ){
		/*if (jWarrantAssociate != null){
		    jWarrantAssociate.delete();
		    new Home().bind(jWarrantAssociate);
		}*/
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + jWarrantAssociate.getOID() + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete juvenile warrant service
	if (regform.getJuvenileWarrantServeCount() > 0){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Juvenile Warrant Service ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileWarrant>jWarrantServes = regform.getJuvenileWarrantServes();
	    for (JuvenileWarrant jWarrantService : jWarrantServes){
		/*if (jWarrantService != null){
		    jWarrantService.delete();
		    new Home().bind(jWarrantService);
		}*/
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + jWarrantService.getOID() + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	//Delete juvenile warrant service officer 
	if (regform.getJuvenileWarrantServiceOfficerCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Juvenile Warrant Service Officer ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileWarrantServiceOfficer>jWarrantServiceOfficers = regform.getJuvenileWarrantServiceOfficers();
	    for (JuvenileWarrantServiceOfficer jWarrantServiceOfficer : jWarrantServiceOfficers){
		/*if (jWarrantServiceOfficer != null){
		    jWarrantServiceOfficer.delete();
		    new Home().bind(jWarrantServiceOfficer);
		}*/
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + jWarrantServiceOfficer.getOID() + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete associated cautions
	if (regform.getAssociatedCautionCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Caution ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileWarrantCautionsCode>cautions = regform.getAssociatedCautions();
	    for (JuvenileWarrantCautionsCode caution : cautions){
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + caution.getOID() + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete associated charges
	if (regform.getAssociatedChargeCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated charge ID</font></th>");
	    message.append("</tr>");
	    ArrayList<Charge>charges = regform.getAssociatedCharges();
	    for (Charge charge : charges){
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + charge.getOID() + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete associated scar marks
	if (regform.getAssociatedScarMarkCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated scar mark ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileWarrantScarsMarksScarsMarksTattoosCode>scarMarks = regform.getAssociatedScarMarks();
	    for (JuvenileWarrantScarsMarksScarsMarksTattoosCode scarMark : scarMarks){
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + scarMark.getOID() + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete associated tattoos
	if (regform.getAssociatedTattooCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated tattoo ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileWarrantTattoosScarsMarksTattoosCode>tattoos = regform.getAssociatedTattoos();
	    for (JuvenileWarrantTattoosScarsMarksTattoosCode tattoo : tattoos){
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + tattoo.getOID() + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete associated addresses
	if (regform.getAssociatedAddressCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated address ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileAssociateAddress>addresses = regform.getAssociatedAddresses();
	    for (JuvenileAssociateAddress address : addresses){
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + address.getOID() + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	//Delete juvenile warrant
	if (regform.getJuvenileWarrantCount() > 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Juvenile Warrant ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileWarrant>jWarrants = regform.getJuvenileWarrants();
	    for (JuvenileWarrant jWarrant : jWarrants){
		if (jWarrant != null){
		    jWarrant.delete();
		    new Home().bind(jWarrant);
		}
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + jWarrant.getOID() + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete associated corespondents
	if (regform.getCorespondentCount()> 0 ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated Corespondent ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JJSCorespondent>corespondents = regform.getCorespondents();
	    for (JJSCorespondent corespondent : corespondents){
		if (corespondent != null){
		    corespondent.delete();
		    new Home().bind(corespondent);
		}
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + corespondent.getOID() + "</td>");
		message.append("</tr>");
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	
	//Delete JCVOP US 164319 Starts
	if ( regform.getJcVOPs() != null ){
	    message.append("<table>");
	    message.append("<tr>");
	    message.append("<th style=' border-bottom: 1px solid #ddd;' bgcolor='gray'><font color='white' face='bold' size='-1'>Associated VOP ID</font></th>");
	    message.append("</tr>");
	    ArrayList<JuvenileReferralVOPResponseEvent>vops = ( ArrayList<JuvenileReferralVOPResponseEvent>) regform.getJcVOPs();
	    for (JuvenileReferralVOPResponseEvent vopRsp : vops ) {
		JCVOP vop = JCVOP.findById(vopRsp.getOID());
		if (vop != null){
		    vop.delete();
		    new Home().bind(vop);
		
		message.append("<tr>");
		message.append("<td style=' border-bottom: 1px solid #ddd;'>" + vop.getOID() + "</td>");
		message.append("</tr>");
		}
	    }
	    message.append("</table>");
	    message.append("<br>");
	}
	//Delete JCVOP US 164319 ENDS	
	
	sendNotification(regform, message.toString());
    }
    
    private void sendNotification(ProdSupportForm regform, String message){
	String fromEmail = "jims2notification@itc.hctx.net";
	SendEmailEvent sendEmailEvent = new SendEmailEvent();
	sendEmailEvent.setContentType("text/html; charset=utf-8");
	sendEmailEvent.setSubject("Deleted Referral Records via Production Support");
	sendEmailEvent.setFromAddress(fromEmail);
	sendEmailEvent.addToAddress("Data.Corrections@hcjpd.hctx.net");
	//sendEmailEvent.addToAddress("sravanthi.nunna@harriscountytx.gov");
	//sendEmailEvent.addToAddress("dustin.nguyen@us.hctx.net");
	sendEmailEvent.setMessage(message);
	IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch1.postEvent(sendEmailEvent);
	
    }

}
