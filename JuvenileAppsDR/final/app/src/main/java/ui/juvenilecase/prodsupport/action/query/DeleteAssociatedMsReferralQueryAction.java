package ui.juvenilecase.prodsupport.action.query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByJuvNumRefNumEvent;
import messaging.facility.GetJuvenileFacilityHeaderEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.GetCorespondentByJuvNumRefNumEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.GetJuvenileCasefileAssignmentsEvent;
import messaging.juvenilecase.GetJuvenileDetentionFacilitiesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileReferralVOPResponseEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantAssociatesByJuvNumAndRefNumEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantChargesByJuvAndRefEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantFieldsByJuvAndRefEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantRecallsByJuvAndRefEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantServesByWarrantIdEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantsForInactivateByJuvAndRefEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.productionsupport.GetJuvenileReferralOffensesQueryEvent;
import messaging.productionsupport.GetProductionSupportCasefilesByJuvEvent;
import messaging.productionsupport.GetTransOffenseReferralsQueryEvent;
import messaging.productionsupport.RetrieveJuvenileProgramReferralsEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileReferralResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.JuvenileFacilityControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.JJSCorespondent;
import pd.juvenilecase.PACTRiskNeedLevel;
import pd.juvenilecase.casefile.CasefileClosingInfo;
import pd.juvenilecase.mentalhealth.JuvenileMAYSI;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIMetadata;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.referral.JJSSVIntakeHistory;
import pd.juvenilecase.riskanalysis.RiskResponse;
import pd.juvenilewarrant.Charge;
import pd.juvenilewarrant.JuvenileAssociateAddress;
import pd.juvenilewarrant.JuvenileWarrant;
import pd.juvenilewarrant.JuvenileWarrantCautionsCode;
import pd.juvenilewarrant.JuvenileWarrantScarsMarksScarsMarksTattoosCode;
import pd.juvenilewarrant.JuvenileWarrantServiceOfficer;
import pd.juvenilewarrant.JuvenileWarrantTattoosScarsMarksTattoosCode;
import pd.supervision.programreferral.JuvenileProgramReferral;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.form.ProdSupportForm;

public class DeleteAssociatedMsReferralQueryAction extends Action
{
  
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	
	ProdSupportForm regform = (ProdSupportForm) form;
	String referralId = regform.getReferralId();
	String referralOID = regform.getReferralOID();
	ArrayList<ProductionSupportJuvenileReferralResponseEvent> referrals = regform.getJuvprogrefs();
	
	if ( referralId == null 
		|| referralId.length() == 0 ) {
	    if ( referrals!= null 
		    && referrals.size() > 0 ) {
		for (ProductionSupportJuvenileReferralResponseEvent referral : referrals ) {
		    if ( referralOID.equals(referral.getReferralOID()) ) {
			regform.setReferralId(referral.getReferralNum());
			break;
		    }
		}
	    }
	}
	
	retrieveAssocicatedReferralData(regform);
	String forward = "associatedSuccess";
	
	return mapping.findForward(forward);

    }
    
    /**
     * @param regform
     */
    @SuppressWarnings("unchecked")
    public void retrieveAssocicatedReferralData(ProdSupportForm regform){
	String juvenileId = regform.getJuvenileId();
	String referralId = regform.getReferralId();
	
	//Get referrals
	ArrayList <JJSReferral> referrals =(ArrayList<JJSReferral>) CollectionUtil.iteratorToList(JJSReferral.findAll("juvenileNum", juvenileId ));
	if (referrals != null && referrals.size() > 0 ) {
	    Collections.sort(referrals, new Comparator<JJSReferral>(){
		
		@Override
		  public int compare(JJSReferral r1,JJSReferral r2)
		  {
		     return r2.getReferralNum().compareTo(r1.getReferralNum());
		  }
		});
	   regform.setJuvenileReferrals(referrals);
	} 
	//Get casefiles
	ArrayList<JuvenileCasefileResponseEvent>casefiles = new ArrayList<>();
	GetProductionSupportCasefilesByJuvEvent casefilesEvent = (GetProductionSupportCasefilesByJuvEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCASEFILESBYJUV);
	casefilesEvent.setJuvenileNumber(juvenileId);
	CompositeResponse casefilesResp = MessageUtil.postRequest( casefilesEvent );
	casefiles = (ArrayList)  MessageUtil.compositeToCollection(casefilesResp, JuvenileCasefileResponseEvent.class );
	
	
	
	if (casefiles.size() > 0) {
	    Collections.sort(casefiles, Collections.reverseOrder( new Comparator<JuvenileCasefileResponseEvent>(){
		public int compare(JuvenileCasefileResponseEvent c1, JuvenileCasefileResponseEvent c2){
		    return c1.getSupervisionNum().compareTo(c2.getSupervisionNum());
		}
	    }));
	    
	    regform.setCasefiles(casefiles);
	}
	
	
	//Get casefile assignemnts
	ArrayList<Assignment>casefileAssignments = new ArrayList<>();
	ArrayList<Assignment>casefileAssignmentsDelete = new ArrayList<>();
	ArrayList<String>filteredCasefileIds = new ArrayList<>();
	ArrayList<JuvenileCasefileResponseEvent>filteredCasefiles = new ArrayList<>();
	int totalCasefileOneReferralNum = 0;
	if (casefiles.size() > 0 ) {
	    for (JuvenileCasefileResponseEvent casefile : casefiles) {
		boolean moreThanOneReferralNum = false;
		List <Assignment> assignmentList = CollectionUtil.iteratorToList(Assignment.findAll("caseFileId", casefile.getSupervisionNum()));
		if (assignmentList != null 
			&& assignmentList.size() > 0) {
		    
		    
		    for (Assignment assignment : assignmentList ) {
			if (referralId.equals(assignment.getReferralNumber())) {
			    casefileAssignments.add(assignment);
			} else  {
			    moreThanOneReferralNum = true;
			}
		    }
		    
		    if ( !moreThanOneReferralNum ) {
			totalCasefileOneReferralNum++;
			for (Assignment assignment : assignmentList ) {
			    casefileAssignmentsDelete.add(assignment);
			}
		    }
		}
		
		casefile.setMoreThanOneReferralNum(moreThanOneReferralNum);

	    }
	}
	
	  /*for (JuvenileCasefileResponseEvent casefile : casefiles) {
	      System.out.println("Casefile id: " + casefile.getSupervisionNum() + " MORE THAN ONE REFERRAL NUM" + casefile.getMoreThanOneReferralNum());
	  }*/
	if (casefileAssignments.size() > 0){
	    
	    Collections.sort(casefileAssignments, Collections.reverseOrder( new Comparator<Assignment>(){
		public int compare(Assignment a1, Assignment a2){
		    return a1.getOID().compareTo(a2.getOID());
		}
	    }));
	    
	    regform.setAssignments(casefileAssignments);
	    regform.setAssignmentCount(casefileAssignments.size());
	    for (Assignment assignment : casefileAssignments ){
		if ( !filteredCasefileIds.contains( assignment.getCaseFileId() ) ){
		    filteredCasefileIds.add( assignment.getCaseFileId() );
		}
	    }
	    if ( filteredCasefileIds != null && filteredCasefileIds.size() > 0 ) {
		for (String filteredCasefileId : filteredCasefileIds ) {
		    for ( JuvenileCasefileResponseEvent casefile : casefiles ) {
			if ( filteredCasefileId.equals(casefile.getSupervisionNum()) ) {
			    filteredCasefiles.add(casefile);
			}
		    }
		}
		regform.setFilteredCasefileIds(filteredCasefileIds);
		regform.setCasefileCount(filteredCasefileIds.size());
		
		
		 Collections.sort(filteredCasefiles, Collections.reverseOrder( new Comparator<JuvenileCasefileResponseEvent>(){
		     @Override
		     public int compare(JuvenileCasefileResponseEvent c1, JuvenileCasefileResponseEvent c2){
			 return c1.getSupervisionNum().compareTo(c2.getSupervisionNum());
		     }
		 }));
		regform.setFilteredCasefiles(filteredCasefiles);
		
		
	    }
	    
	    
	}
	
	if (casefileAssignmentsDelete.size() > 0 ) {
	    regform.setAssignmentsDelete(casefileAssignmentsDelete);
	    regform.setAssignmentsDeleteCount(casefileAssignmentsDelete.size());
	    regform.setCasefileOneReferralDeleteCount(totalCasefileOneReferralNum);
	}

	
	
	//Get casefileClosing
	ArrayList<CasefileClosingInfo>casefileClosingInfos = new ArrayList<>();
	ArrayList<CasefileClosingInfo>casefileClosingInfosDelete = new ArrayList<>();
	ArrayList<String>filteredCasefileClosingIds = new ArrayList<>();
	ArrayList<CasefileClosingInfo>filteredCasefileClosings = new ArrayList<>();
	if (casefiles.size() > 0 ) {
	    for (JuvenileCasefileResponseEvent casefile : casefiles) {
		ArrayList<CasefileClosingInfo>casefileClosingInfoList =(ArrayList) CollectionUtil.iteratorToList(CasefileClosingInfo.findAll(PDJuvenileCaseConstants.SUPERVISION_NUMBER, casefile.getSupervisionNum()));
		if ( casefileClosingInfoList != null
			&& casefileClosingInfoList.size() > 0 ){
		    
		   
		    for ( CasefileClosingInfo casefileClosing : casefileClosingInfoList ) {
			casefileClosingInfos.add(casefileClosing);
			if ( !casefile.getMoreThanOneReferralNum() ) {
			    casefileClosingInfosDelete.add(casefileClosing);
			}
			
			for (String fiteredCasefileId : filteredCasefileIds ){
			    if (casefileClosing.getSupervisionNumber().equals(fiteredCasefileId)
				    && !filteredCasefileClosingIds.contains(casefileClosing.getOID()) ) {
				filteredCasefileClosingIds.add(casefileClosing.getOID());
				filteredCasefileClosings.add(casefileClosing);
			    }
			}
		    }
		}
	    }
	    
	   
	}
	
	if (filteredCasefileClosings.size() > 0){
	    Collections.sort(filteredCasefileClosings, Collections.reverseOrder( new Comparator<CasefileClosingInfo>(){
		@Override
		public int compare(CasefileClosingInfo c1, CasefileClosingInfo c2){
		    return c1.getOID().compareTo(c2.getOID());
		}
	    }));
	    regform.setCasefileclosings(casefileClosingInfos);
	    regform.setCasefileClosingCount(filteredCasefileClosingIds.size());
	    
	    regform.setCasefileclosingsDelete(casefileClosingInfosDelete);
	    regform.setCasefileclosingsDeleteCount(casefileClosingInfosDelete.size());
	    
	    Collections.sort(filteredCasefileClosingIds);
	    regform.setFilteredCasefileClosingIds(filteredCasefileClosingIds);
	    regform.setFilteredCasefileClosings(filteredCasefileClosings);
	}
		
	
	//Get juvenile program referrals
	ArrayList<JuvenileProgramReferral>juvenileProgramReferrals = new ArrayList<>();
	ArrayList<JuvenileProgramReferral>juvenileProgramReferralsDelete = new ArrayList<>();
	ArrayList<String>filteredProgramReferralIds = new ArrayList<>();
	ArrayList<JuvenileProgramReferral>filteredProgramReferrals = new ArrayList<>();
	if (casefiles.size() > 0 ) {
	    for (JuvenileCasefileResponseEvent casefile : casefiles ) {
		String casefileId = casefile.getSupervisionNum();
		RetrieveJuvenileProgramReferralsEvent getJuvProgramReferralEvent = new RetrieveJuvenileProgramReferralsEvent();
		getJuvProgramReferralEvent.setCasefileId(casefileId);
		ArrayList<JuvenileProgramReferral>juvenileProgramReferralList = ( ArrayList )CollectionUtil.iteratorToList(JuvenileProgramReferral.findAll(getJuvProgramReferralEvent));
		if ( juvenileProgramReferralList != null 
			&& juvenileProgramReferralList.size() > 0 ) {
		     
		    for (JuvenileProgramReferral programReferral : juvenileProgramReferralList ) {
			juvenileProgramReferrals.add(programReferral);
			if ( !casefile.getMoreThanOneReferralNum() ){
			    juvenileProgramReferralsDelete.add(programReferral);
			}
			
			for (String fiteredCasefileId : filteredCasefileIds ){
			   if (programReferral.getCasefileId().equals(fiteredCasefileId)
				   && !filteredProgramReferralIds.contains(programReferral.getOID()) ) {
			       filteredProgramReferralIds.add(programReferral.getOID());
			       filteredProgramReferrals.add(programReferral);
			   }
			}
			
		    }
		}
	    }
	}
	
	if ( filteredProgramReferrals.size() > 0 ){
	    Collections.sort(filteredProgramReferrals, Collections.reverseOrder(new Comparator<JuvenileProgramReferral>(){
		@Override
		public int compare(JuvenileProgramReferral p1, JuvenileProgramReferral p2){
		    return p1.getOID().compareTo(p2.getOID());
		}
	    }));
	    
	    regform.setProgramReferrals(juvenileProgramReferrals);
	    regform.setProgramReferralCount(filteredProgramReferralIds.size());
	    regform.setFilteredProgramReferralIds( filteredProgramReferralIds);
	    regform.setFilteredProgramReferrals(filteredProgramReferrals);
	} else {
	    regform.setProgramReferralCount(0);
	}
	
	if ( juvenileProgramReferralsDelete.size() > 0 ) {
	    regform.setProgramReferralsDelete(juvenileProgramReferralsDelete);
	    regform.setProgramReferralsDeleteCount(juvenileProgramReferralsDelete.size());
	}
	
	//Get pact risk need level
	ArrayList<PACTRiskNeedLevel> pactRiskNeedLevels = new ArrayList<>();
	List<PACTRiskNeedLevel>riskNeedLevelList = CollectionUtil.iteratorToList(PACTRiskNeedLevel.findAll("juvenileNumber", juvenileId ));
	if ( riskNeedLevelList!= null 
		&& riskNeedLevelList.size()>0){
	    
	    Collections.sort(riskNeedLevelList, Collections.reverseOrder( new Comparator<PACTRiskNeedLevel>(){
		@Override
		public int compare(PACTRiskNeedLevel r1, PACTRiskNeedLevel r2){
		    return r1.getRiskNeedLvlId().compareTo(r2.getRiskNeedLvlId());
		}
	    }));
	    
	    for (PACTRiskNeedLevel riskNeedLevel : riskNeedLevelList ) {
		if ( referralId.equals(riskNeedLevel.getReferralNumber())) {
		    pactRiskNeedLevels.add(riskNeedLevel);
		}
	    }
	}
	if (pactRiskNeedLevels.size() > 0 ) {
	    regform.setRiskNeedLevels(pactRiskNeedLevels);
	    regform.setRiskNeedLevelCount(pactRiskNeedLevels.size());
	}
	//Get maysi details
	ArrayList<JuvenileMAYSI>maysiDetails = new ArrayList<>();
	List<JuvenileMAYSI>maysiList = CollectionUtil.iteratorToList(JuvenileMAYSI.findAll("juvenileNumber", juvenileId ));
	if (maysiList != null 
		&& maysiList.size() > 0){
	    
	    Collections.sort(maysiList, Collections.reverseOrder( new Comparator<JuvenileMAYSI>(){
		@Override
		public int compare(JuvenileMAYSI m1, JuvenileMAYSI m2){
		    return m1.getOID().compareTo(m2.getOID());
		}
	    }));
	    
	    for (JuvenileMAYSI maysi : maysiList ) {
		if (referralId.equals(maysi.getReferralNum())) {
		    maysiDetails.add(maysi);
		}
	    }
	}
	
	if (maysiDetails.size() > 0 ){
	    regform.setMaysidetails(maysiDetails);
	    regform.setMaysidetailCount(maysiDetails.size());
	}
	
	//Get maysi assessment records
	ArrayList<JuvenileMAYSIMetadata> maysiAssessments = new ArrayList<>();
	List<JuvenileMAYSIMetadata>maysiAssessmentList = CollectionUtil.iteratorToList(JuvenileMAYSIMetadata.findAll("juvenileNumber", juvenileId ));
	if (maysiAssessmentList != null 
		&& maysiAssessmentList.size() > 0 ){
	    
	    Collections.sort(maysiAssessmentList, Collections.reverseOrder( new Comparator<JuvenileMAYSIMetadata>(){
		@Override
		public int compare(JuvenileMAYSIMetadata m1, JuvenileMAYSIMetadata m2){
		    return m1.getOID().compareTo(m2.getOID());
		}
	    }));
	    
	    for (JuvenileMAYSIMetadata maysiAssessment : maysiAssessmentList ) {
		if ( referralId.equals(maysiAssessment.getReferralNumber())) {
		    maysiAssessments.add(maysiAssessment);
		}
	    }
	}
	
	if ( maysiAssessments.size() > 0 ) {
	    regform.setMaysis(maysiAssessments);
	    regform.setMaysiCount(maysiAssessments.size());
	    
	}
	//Get juvenile warrants
	ArrayList<JuvenileWarrant>jWarrants = new ArrayList<>();
	List<JuvenileWarrant>jWarrantList = CollectionUtil.iteratorToList(JuvenileWarrant.findAll("juvenileNum", juvenileId ));
	
	if (jWarrantList != null && jWarrantList.size() > 0 ) {
	    
	    Collections.sort(jWarrantList, Collections.reverseOrder( new Comparator<JuvenileWarrant>(){
		@Override
		public int compare(JuvenileWarrant jw1, JuvenileWarrant jw2){
		    return jw1.getWarrantNum().compareTo(jw2.getWarrantNum());
		}
	    }));
	    
	    for (JuvenileWarrant jWarrant : jWarrantList) {
		if (referralId.equals(jWarrant.getReferralNum().toString())){
		    jWarrants.add(jWarrant);
		}
	    }
	}
	if (jWarrants.size() > 0){
	    regform.setJuvenileWarrants(jWarrants);
	    regform.setJuvenileWarrantCount(jWarrants.size());
	}
	
	
	//Get inactivated juvenile warrants
	
	GetJuvenileWarrantsForInactivateByJuvAndRefEvent getInactivatedJWarrantEvent = new GetJuvenileWarrantsForInactivateByJuvAndRefEvent();
	getInactivatedJWarrantEvent.setJuvenileNum(Integer.parseInt(juvenileId));
	getInactivatedJWarrantEvent.setReferralNum(Integer.parseInt(referralId));
	ArrayList<JuvenileWarrant> inactivatedJWarrantList = (ArrayList) CollectionUtil.iteratorToList(JuvenileWarrant.findAll(getInactivatedJWarrantEvent));
	if (inactivatedJWarrantList != null 
		&& inactivatedJWarrantList.size() > 0){
	    
	    Collections.sort(inactivatedJWarrantList, Collections.reverseOrder( new Comparator<JuvenileWarrant>(){
		@Override
		public int compare(JuvenileWarrant jw1, JuvenileWarrant jw2){
		    return jw1.getWarrantNum().compareTo(jw2.getWarrantNum());
		}
	    }));
	    
	    regform.setJuvenileInactivatedWarrants(inactivatedJWarrantList);
	    regform.setJuvenileInactivatedWarrantCount(inactivatedJWarrantList.size());
	}
	
	//Get juvenile warrant associates
	
	GetJuvenileWarrantAssociatesByJuvNumAndRefNumEvent getJWarrantAssociatesEvent = new GetJuvenileWarrantAssociatesByJuvNumAndRefNumEvent();
	getJWarrantAssociatesEvent.setJuvenileNum(Integer.parseInt(juvenileId));
	getJWarrantAssociatesEvent.setReferralNum(Integer.parseInt(referralId));
	ArrayList<JuvenileWarrant>jWarrantAssociateList = (ArrayList) CollectionUtil.iteratorToList(JuvenileWarrant.findAll(getJWarrantAssociatesEvent));
	if (jWarrantAssociateList != null 
		&& jWarrantAssociateList.size() > 0 ){
	    
	    Collections.sort(jWarrantAssociateList, Collections.reverseOrder( new Comparator<JuvenileWarrant>(){
		@Override
		public int compare(JuvenileWarrant jw1, JuvenileWarrant jw2){
		    return jw1.getWarrantNum().compareTo(jw2.getWarrantNum());
		}
	    }));
	    
	    regform.setJuvenileWarrantAssociates(jWarrantAssociateList);
	    regform.setJuvenileWarrantAssociateCount(jWarrantAssociateList.size());
	}
	
	
	//Get juvenile warrant charges
	GetJuvenileWarrantChargesByJuvAndRefEvent getJWarrantChargesEvent = new GetJuvenileWarrantChargesByJuvAndRefEvent();
	getJWarrantChargesEvent.setJuvenileNum(Integer.parseInt(juvenileId));
	getJWarrantChargesEvent.setReferralNum(Integer.parseInt(referralId));
	ArrayList<JuvenileWarrant>jWarrantChargesList = (ArrayList<JuvenileWarrant>) CollectionUtil.iteratorToList(JuvenileWarrant.findAll(getJWarrantChargesEvent));
	if ( jWarrantChargesList != null 
		&& jWarrantChargesList.size() > 0){

	    Collections.sort(jWarrantChargesList, Collections.reverseOrder( new Comparator<JuvenileWarrant>(){
		@Override
		public int compare(JuvenileWarrant jw1, JuvenileWarrant jw2){
		    return jw1.getWarrantNum().compareTo(jw2.getWarrantNum());
		}
	    }));
	    
	    regform.setJuvenileWarrantCharges(jWarrantChargesList);
	    regform.setJuvenileWarrantChargeCount(jWarrantChargesList.size());
	}
	
	//Get juvenile warrant fields
	GetJuvenileWarrantFieldsByJuvAndRefEvent getJWarrantFieldsEvent = new  GetJuvenileWarrantFieldsByJuvAndRefEvent();
	getJWarrantFieldsEvent.setJuvenileNum(Integer.parseInt(juvenileId));
	getJWarrantFieldsEvent.setReferralNum(Integer.parseInt(referralId));
	ArrayList<JuvenileWarrant>jWarrantFieldsList = (ArrayList) CollectionUtil.iteratorToList(JuvenileWarrant.findAll(getJWarrantFieldsEvent));
	if ( jWarrantFieldsList!= null 
		&& jWarrantFieldsList.size() > 0) {
	    
	    Collections.sort(jWarrantFieldsList, Collections.reverseOrder( new Comparator<JuvenileWarrant>(){
		@Override
		public int compare(JuvenileWarrant jw1, JuvenileWarrant jw2){
		    return jw1.getWarrantNum().compareTo(jw2.getWarrantNum());
		}
	    }));
	    regform.setJuvenileWarrantFields(jWarrantFieldsList);
	    regform.setJuvenileWarrantFieldCount(jWarrantFieldsList.size());
	}
	//Get juvenile warrant recalls
	GetJuvenileWarrantRecallsByJuvAndRefEvent getJWarrantRecallsEvent = new GetJuvenileWarrantRecallsByJuvAndRefEvent();
	getJWarrantRecallsEvent.setJuvenileNum(Integer.parseInt(juvenileId));
	getJWarrantRecallsEvent.setReferralNum(Integer.parseInt(referralId));
	ArrayList<JuvenileWarrant>jWarrantRecallsList = (ArrayList) CollectionUtil.iteratorToList(JuvenileWarrant.findAll(getJWarrantRecallsEvent));
	if ( jWarrantRecallsList!= null 
		&& jWarrantRecallsList.size() > 0){
	    
	    Collections.sort(jWarrantRecallsList, Collections.reverseOrder( new Comparator<JuvenileWarrant>(){
		@Override
		public int compare(JuvenileWarrant jw1, JuvenileWarrant jw2){
		    return jw1.getWarrantNum().compareTo(jw2.getWarrantNum());
		}
	    }));
	    
	    regform.setJuvenileWarrantRecalls(jWarrantRecallsList);
	    regform.setJuvenileWarrantRecallCount(jWarrantRecallsList.size());
	}
	//Get juvenile warrant service records
	GetJuvenileWarrantServesByWarrantIdEvent getJWarrantServesEvent = new GetJuvenileWarrantServesByWarrantIdEvent();
	if (jWarrants.size() > 0 ){
	    for (JuvenileWarrant jWarrant : jWarrants ) {
		getJWarrantServesEvent.setWarrantNum(jWarrant.getWarrantNum());
		ArrayList<JuvenileWarrant>jWarrantServesList = (ArrayList) CollectionUtil.iteratorToList(JuvenileWarrant.findAll(getJWarrantServesEvent));
		if (jWarrantServesList != null 
			&& jWarrantServesList.size() > 0 ){
		    
		    Collections.sort(jWarrantServesList, Collections.reverseOrder( new Comparator<JuvenileWarrant>(){
			@Override
			public int compare(JuvenileWarrant jw1, JuvenileWarrant jw2){
			    return jw1.getWarrantNum().compareTo(jw2.getWarrantNum());
			}
		    }));
		    
		    regform.setJuvenileWarrantServes(jWarrantServesList);
		    regform.setJuvenileWarrantServeCount(jWarrantServesList.size());
		}
	    }
	}
	
	//Get juvenile warrant service officer records
	 ArrayList<JuvenileWarrantServiceOfficer>jWarrantServiceOfficers = new ArrayList<>();
	if (jWarrants.size() > 0 ) {
	   for (JuvenileWarrant jWarrant : jWarrants ) {
	       JuvenileWarrantServiceOfficer jWarrantServOfficer =JuvenileWarrantServiceOfficer.findByWarrantNum(jWarrant .getWarrantNum());
	       if (jWarrantServOfficer != null){
		   jWarrantServiceOfficers.add(jWarrantServOfficer);
	       }
	   }
	}
	
	if (jWarrantServiceOfficers.size() > 0){
	    
	    Collections.sort(jWarrantServiceOfficers, Collections.reverseOrder( new Comparator<JuvenileWarrantServiceOfficer>(){
		@Override
		public int compare(JuvenileWarrantServiceOfficer o1, JuvenileWarrantServiceOfficer o2){
		    return o1.getWarrantNum().compareTo(o2.getWarrantNum());
		}
	    }));
	    
	    regform.setJuvenileWarrantServiceOfficers(jWarrantServiceOfficers);
	    regform.setJuvenileWarrantServiceOfficerCount(jWarrantServiceOfficers.size());
	}
	//Get referral offenses
	ArrayList juvReferralOffenses = null;
	
	GetJuvenileReferralOffensesQueryEvent getReferralOffenses = (GetJuvenileReferralOffensesQueryEvent)EventFactory.getInstance(ProductionSupportControllerServiceNames.GETJUVENILEREFERRALOFFENSESQUERY);
	getReferralOffenses.setJuvenileNum( juvenileId );
	getReferralOffenses.setReferralNum( referralId );
	CompositeResponse juvenileReferralsResp = MessageUtil.postRequest( getReferralOffenses );
	juvReferralOffenses = (ArrayList) MessageUtil.compositeToCollection(juvenileReferralsResp, JJSOffenseResponseEvent.class);
	
	if ( juvReferralOffenses!= null
		&& juvReferralOffenses.size() > 0 ) {
	    
	    Collections.sort(juvReferralOffenses, Collections.reverseOrder( new Comparator<JJSOffenseResponseEvent>(){
		@Override
		public int compare(JJSOffenseResponseEvent o1, JJSOffenseResponseEvent o2){
		    return o1.getOID().compareTo(o2.getOID());
		}
	    }));
	    regform.setReferralOffenses(juvReferralOffenses);
	    regform.setJuvRefOffensesCount(juvReferralOffenses.size());
	}
	
	//Get intake history record
	GetJuvenileCasefileAssignmentsEvent getAssignmentsEvent = new GetJuvenileCasefileAssignmentsEvent();
	getAssignmentsEvent.setJuvenileNum( juvenileId );
	getAssignmentsEvent.setReferralNum( referralId );

	Iterator<JJSSVIntakeHistory> intakeIter = JJSSVIntakeHistory.findAll(getAssignmentsEvent);
	//put the items into a collection (List)
	List<JJSSVIntakeHistory> intakeHistoryList = new ArrayList<JJSSVIntakeHistory> (); 
	while (intakeIter.hasNext())
	{
		JJSSVIntakeHistory intakeHistory = (JJSSVIntakeHistory) intakeIter.next();
		intakeHistoryList.add(intakeHistory);
	 }
	
	if (intakeHistoryList != null 
		&& intakeHistoryList.size() > 0 ) {
	    
	    Collections.sort(intakeHistoryList, Collections.reverseOrder( new Comparator<JJSSVIntakeHistory>(){
		@Override
		public int compare(JJSSVIntakeHistory h1, JJSSVIntakeHistory h2){
		    return h1.getOID().compareTo(h2.getOID());
		}
	    }));
	    regform.setIntakeHistoryRecords(intakeHistoryList);
	    regform.setIntakeHistCount(intakeHistoryList.size());
	}
	
	//Get transferred offense referrals
	ArrayList transOffenseReferrals = null;
	GetTransOffenseReferralsQueryEvent getTransOffenseReferrals = (GetTransOffenseReferralsQueryEvent)EventFactory.getInstance(ProductionSupportControllerServiceNames.GETTRANSOFFENSEREFERRALSQUERY);
	getTransOffenseReferrals.setJuvenileNumber(juvenileId);
	getTransOffenseReferrals.setReferralNumber(referralId);
	CompositeResponse transOffenseReferralsResp = MessageUtil.postRequest( getTransOffenseReferrals );
	transOffenseReferrals = (ArrayList) MessageUtil.compositeToCollection(transOffenseReferralsResp, JuvenileCasefileTransferredOffenseResponseEvent.class);
	if ( transOffenseReferrals!= null 
		&& transOffenseReferrals.size() > 0) {
	    
	    Collections.sort(transOffenseReferrals, Collections.reverseOrder( new Comparator<JuvenileCasefileTransferredOffenseResponseEvent>(){
		@Override
		public int compare(JuvenileCasefileTransferredOffenseResponseEvent r1, JuvenileCasefileTransferredOffenseResponseEvent r2){
		    return r1.getTransOffenseReferralId().compareTo(r2.getTransOffenseReferralId());
		}
	    }));
	    regform.setTransOffenseReferrals(transOffenseReferrals);
	    regform.setTransOffenseReferralsCount(transOffenseReferrals.size());
	}
	
	//Get JJS Header
	ArrayList<JuvenileFacilityHeaderResponseEvent> facilityHeaderRecords 	= new ArrayList<JuvenileFacilityHeaderResponseEvent>();
	ArrayList<JuvenileFacilityHeaderResponseEvent> tempFacilityHeaderRecords 	= null;
	GetJuvenileFacilityHeaderEvent headerEvent =
				(GetJuvenileFacilityHeaderEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.GETJUVENILEFACILITYHEADER);
			
	headerEvent.setJuvenileNum(juvenileId);
	CompositeResponse facilityHeaderResp = MessageUtil.postRequest( headerEvent );
	tempFacilityHeaderRecords = (ArrayList) MessageUtil.compositeToCollection(facilityHeaderResp, JuvenileFacilityHeaderResponseEvent.class);
	
	if (tempFacilityHeaderRecords != null 
		&& tempFacilityHeaderRecords.size() > 0) {
	    for ( JuvenileFacilityHeaderResponseEvent facilityHeader : tempFacilityHeaderRecords ) {
		if ( referralId.equals(facilityHeader.getReferralNo())){
		    facilityHeaderRecords.add(facilityHeader);
		}
	    }
	}
	
	if (facilityHeaderRecords.size() > 0) {
	    
	    Collections.sort(facilityHeaderRecords, Collections.reverseOrder( new Comparator<JuvenileFacilityHeaderResponseEvent>(){
		@Override
		public int compare(JuvenileFacilityHeaderResponseEvent f1, JuvenileFacilityHeaderResponseEvent f2){
		    return f1.getHeaderId().compareTo(f2.getHeaderId());
		}
	    }));
	   
	    regform.setFacilityHeaderList(facilityHeaderRecords);
	    regform.setFacilityHeaderCount(facilityHeaderRecords.size());
	}
	
	//Get detention facilities
	ArrayList<JuvenileDetentionFacilitiesResponseEvent>detentionFacilities = null;
	GetJuvenileDetentionFacilitiesEvent detEvent = (GetJuvenileDetentionFacilitiesEvent)EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILEDETENTIONFACILITIES );
	detEvent.setJuvenileNum(juvenileId);
	detEvent.setReferralNum(referralId);
	CompositeResponse detentionFacilityResp = MessageUtil.postRequest( detEvent );
	detentionFacilities = (ArrayList) MessageUtil.compositeToCollection(detentionFacilityResp, JuvenileDetentionFacilitiesResponseEvent.class );		
	if ( detentionFacilities != null
		&& detentionFacilities.size() > 0){
	    
	    Collections.sort(detentionFacilities, Collections.reverseOrder( new Comparator<JuvenileDetentionFacilitiesResponseEvent>(){
		@Override
		public int compare(JuvenileDetentionFacilitiesResponseEvent f1, JuvenileDetentionFacilitiesResponseEvent f2){
		    return f1.getDetentionId().compareTo(f2.getDetentionId());
		}
	    }));
	    
	    regform.setFacilityDetentionList(detentionFacilities);
	    regform.setFacilityDetentionCount(detentionFacilities.size());
	} 
	
	//Get risk response
	ArrayList<RiskResponse>riskResponses = new ArrayList<>();
	
	if (regform.getFacilityDetentionCount() > 0 ){
	    ArrayList<JuvenileDetentionFacilitiesResponseEvent> detentionFacilityResponses= regform.getFacilityDetentionList();
	    if ( detentionFacilityResponses != null ){
        	    for (JuvenileDetentionFacilitiesResponseEvent facility : detentionFacilityResponses ){
        		if (facility.getRiskAnalysisId() != null){
        		    
        		    ArrayList<RiskResponse>riskResponseList =( ArrayList ) CollectionUtil.iteratorToList( RiskResponse.findAll("riskAnalysisId", facility.getRiskAnalysisId() ));
        		    if (riskResponseList != null 
        			    && riskResponseList.size() > 0){
        			for (RiskResponse riskResponse : riskResponseList ) {
        			    riskResponses.add(riskResponse);
        			}
        		    }
        		    
        		}
        	    }
        	    
        	    if ( riskResponses != null && riskResponses.size() > 0 ) {
        		 Collections.sort(riskResponses, Collections.reverseOrder( new Comparator<RiskResponse>(){
			     	@Override
				public int compare(RiskResponse r1, RiskResponse r2){
				    return r1.getOID().compareTo(r2.getOID());
				}
			    }));
			 
			    regform.setRiskresponses(riskResponses);
			    regform.setRiskresponsesCount(riskResponses.size());
        	    }
	    }
	}
	
	
	// Get detention court record
	ArrayList<DocketEventResponseEvent>detentionCourtDateRecords = null;
	GetJJSCLDetentionByJuvNumRefNumEvent detentionEvt = (GetJJSCLDetentionByJuvNumRefNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYJUVNUMREFNUM);
	detentionEvt.setJuvenileNumber(juvenileId);
	detentionEvt.setReferralNumber(referralId);
	CompositeResponse detentionResp =  MessageUtil.postRequest( detentionEvt );
	detentionCourtDateRecords = (ArrayList) MessageUtil.compositeToCollection(detentionResp, DocketEventResponseEvent.class);
	if (detentionCourtDateRecords != null
		&& detentionCourtDateRecords.size() > 0){
	    
	    Collections.sort(detentionCourtDateRecords, Collections.reverseOrder( new Comparator<DocketEventResponseEvent>(){
		@Override
		public int compare(DocketEventResponseEvent d1, DocketEventResponseEvent d2){
		    return d1.getDocketEventId().compareTo(d2.getDocketEventId());
		}
	    }));
	    
	    regform.setJuvDetCourtRecords(detentionCourtDateRecords);
	    regform.setJuvDetCourtRecordCount(detentionCourtDateRecords.size());
	}
	
	//Get district court record
	ArrayList<DocketEventResponseEvent>distCourtRecords = new ArrayList<>();
	GetJJSCourtEvent jjsCLCrtEvent = (GetJJSCourtEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSCOURT);
	jjsCLCrtEvent.setJuvenileNumber(juvenileId);
	jjsCLCrtEvent.setReferralNumber(referralId);
	CompositeResponse distCourtResp = MessageUtil.postRequest( jjsCLCrtEvent );
	distCourtRecords = (ArrayList) MessageUtil.compositeToCollection(distCourtResp, DocketEventResponseEvent.class);
	
	
	if (distCourtRecords != null 
		&& distCourtRecords.size() > 0){
	    
	    Collections.sort(distCourtRecords, Collections.reverseOrder( new Comparator<DocketEventResponseEvent>(){
		@Override
		public int compare(DocketEventResponseEvent d1, DocketEventResponseEvent d2){
		    return d1.getDocketEventId().compareTo(d2.getDocketEventId());
		}
	    }));
	  	    
	    regform.setJuvDistCourtRecords(distCourtRecords);
	    regform.setJuvDistCourtRecordCount(distCourtRecords.size());
	}
	
	// Get pettition record
	Collection<PetitionResponseEvent>petitionRecords = null;
	GetJJSPetitionsEvent petitionEvent = (GetJJSPetitionsEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJJSPETITIONS);
	petitionEvent.setJuvenileNum(juvenileId);
	petitionEvent.setReferralNum(referralId);
	CompositeResponse petitionResp = MessageUtil.postRequest( petitionEvent  ); 
	petitionRecords = MessageUtil.compositeToCollection(petitionResp, PetitionResponseEvent.class );
	if (petitionRecords != null 
		&& petitionRecords.size() > 0 ){
	    Collections.sort((ArrayList)petitionRecords, Collections.reverseOrder( new Comparator<PetitionResponseEvent>(){
		@Override
		public int compare(PetitionResponseEvent p1, PetitionResponseEvent p2){
		    return p1.getOID().compareTo(p2.getOID());
		}
	    }));
	    regform.setPetitionDetails(petitionRecords);
	    regform.setPetitionRecordCount(petitionRecords.size());
	} else {
	    regform.setPetitionRecordCount(0);
	    regform.setPetitionDetails(null);
	}
	
	//Get Associated Caution record
	ArrayList<JuvenileWarrantCautionsCode>associatedCautionRecords = new ArrayList<>();
	
	if (jWarrants.size() > 0 ){
	    for (JuvenileWarrant jWarrant : jWarrants ) {
		ArrayList<JuvenileWarrantCautionsCode>associatedCautionList = (ArrayList) CollectionUtil.iteratorToList(JuvenileWarrantCautionsCode.findAll("parentId", jWarrant.getWarrantNum()));
		if (associatedCautionList != null 
			&& associatedCautionList.size() > 0 ){
		    for (JuvenileWarrantCautionsCode caution : associatedCautionList){
			 associatedCautionRecords.add( caution );
		    }
		}
	    }
	    
	    if ( associatedCautionRecords != null
		    && associatedCautionRecords.size() > 0 ) {
		
		   Collections.sort(associatedCautionRecords, Collections.reverseOrder( new Comparator<JuvenileWarrantCautionsCode>(){
		       @Override
			public int compare(JuvenileWarrantCautionsCode c1, JuvenileWarrantCautionsCode c2){
			    return c1.getOID().compareTo(c2.getOID());
			}
		    }));
		   
		regform.setAssociatedCautions(associatedCautionRecords);
		regform.setAssociatedCautionCount(associatedCautionRecords.size());
	    }
	}
	
	//Get Associated Charge record
	ArrayList<Charge>associatedChargeRecords = new ArrayList<>();
	if (jWarrants.size() > 0 ){
	    for (JuvenileWarrant jWarrant : jWarrants ) {
		ArrayList<Charge>associatedChargeList = (ArrayList) CollectionUtil.iteratorToList(Charge.findAll("warrantNum", jWarrant.getWarrantNum()));
		if (associatedChargeList != null 
			&& associatedChargeList.size() > 0 ){
		    for (Charge charge : associatedChargeList){
			associatedChargeRecords.add( charge );
		    }
		}
	    }
	    
	    if ( associatedChargeRecords != null
		    && associatedChargeRecords.size() > 0 ) {
		
		 Collections.sort(associatedChargeRecords, Collections.reverseOrder( new Comparator<Charge>(){
		     	@Override
			public int compare(Charge c1, Charge c2){
			    return c1.getOID().compareTo(c2.getOID());
			}
		 }));
		 
		regform.setAssociatedCharges(associatedChargeRecords);
		regform.setAssociatedChargeCount(associatedChargeRecords.size());
	    }
	}
	
	//Get Associated Scar Mark Record
	ArrayList<JuvenileWarrantScarsMarksScarsMarksTattoosCode>associatedScarMarkRecords = new ArrayList<>();
	if (jWarrants.size() > 0 ){
	    for (JuvenileWarrant jWarrant : jWarrants ) {
		ArrayList<JuvenileWarrantScarsMarksScarsMarksTattoosCode>associatedScarMarkList = (ArrayList) CollectionUtil.iteratorToList(JuvenileWarrantScarsMarksScarsMarksTattoosCode.findAll("parentId", jWarrant.getWarrantNum()));
		if (associatedScarMarkList != null 
			&& associatedScarMarkList.size() > 0 ){
		    for (JuvenileWarrantScarsMarksScarsMarksTattoosCode scarMark: associatedScarMarkList){
			associatedScarMarkRecords.add( scarMark );
		    }
		}
	    }
	    
	    if ( associatedScarMarkRecords != null
		    && associatedScarMarkRecords.size() > 0 ) {
		
		 Collections.sort(associatedScarMarkRecords, Collections.reverseOrder( new Comparator<JuvenileWarrantScarsMarksScarsMarksTattoosCode>(){
		     	@Override
			public int compare(JuvenileWarrantScarsMarksScarsMarksTattoosCode sm1, JuvenileWarrantScarsMarksScarsMarksTattoosCode sm2){
			    return sm1.getOID().compareTo(sm2.getOID());
			}
		 }));
		 
		regform.setAssociatedScarMarks(associatedScarMarkRecords);
		regform.setAssociatedScarMarkCount(associatedScarMarkRecords.size());
	    }
	}
	
	//Get Associated Tattoo Record
	ArrayList<JuvenileWarrantTattoosScarsMarksTattoosCode>associatedTatooRecords = new ArrayList<>();
	if (jWarrants.size() > 0 ){
	    for (JuvenileWarrant jWarrant : jWarrants ) {
		ArrayList<JuvenileWarrantTattoosScarsMarksTattoosCode>associatedTatooList = (ArrayList) CollectionUtil.iteratorToList(JuvenileWarrantTattoosScarsMarksTattoosCode.findAll("parentId", jWarrant.getWarrantNum()));
		if (associatedTatooList != null 
			&& associatedTatooList.size() > 0 ){
		    for (JuvenileWarrantTattoosScarsMarksTattoosCode tattoo: associatedTatooList){
			associatedTatooRecords.add( tattoo );
		    }
		}
	    }
	    
	    if ( associatedTatooRecords != null
		    && associatedTatooRecords.size() > 0 ) {
		
		 Collections.sort(associatedTatooRecords, Collections.reverseOrder( new Comparator<JuvenileWarrantTattoosScarsMarksTattoosCode>(){
		     	@Override
			public int compare(JuvenileWarrantTattoosScarsMarksTattoosCode t1, JuvenileWarrantTattoosScarsMarksTattoosCode t2){
			    return t1.getOID().compareTo(t2.getOID());
			}
		 }));
		 
		regform.setAssociatedTattoos( associatedTatooRecords );
		regform.setAssociatedTattooCount( associatedTatooRecords.size() );
	    }
	}
	
	//Get Associated Address Record
	ArrayList<JuvenileAssociateAddress>associatedAddressRecords = new ArrayList<>();
	if (jWarrantAssociateList.size() > 0 ){
	    for (JuvenileWarrant jWarrant : jWarrantAssociateList ) {
		ArrayList<JuvenileAssociateAddress>associatedAddressList = (ArrayList) CollectionUtil.iteratorToList(JuvenileAssociateAddress.findAll("associateNum", jWarrant.getOID()));
		if (associatedAddressList != null 
			&& associatedAddressList.size() > 0 ){
		    for (JuvenileAssociateAddress address: associatedAddressList){
			associatedAddressRecords.add( address );
		    }
		}
	    }
	    
	    if ( associatedAddressRecords != null
		    && associatedAddressRecords.size() > 0 ) {
		
		 Collections.sort(associatedAddressRecords, Collections.reverseOrder( new Comparator<JuvenileAssociateAddress>(){
		     	@Override
			public int compare(JuvenileAssociateAddress a1, JuvenileAssociateAddress a2){
			    return a1.getOID().compareTo(a2.getOID());
			}
		 }));
		 
		regform.setAssociatedAddresses(associatedAddressRecords);
		regform.setAssociatedAddressCount(associatedAddressRecords.size());
	    }
	    
	}
	
	//Get Corespondent Record
	ArrayList<JJSCorespondent>corespondentRecords = new ArrayList<>();
	GetCorespondentByJuvNumRefNumEvent getCorespondentEvent = new GetCorespondentByJuvNumRefNumEvent();
	getCorespondentEvent.setJuvenileNum(juvenileId);
	getCorespondentEvent.setReferralNum(referralId);
	corespondentRecords = (ArrayList<JJSCorespondent>) CollectionUtil.iteratorToList(JJSCorespondent.findAll(getCorespondentEvent));
	
	if (corespondentRecords != null 
		&& corespondentRecords.size() > 0 ) {
	    for (JJSCorespondent record : corespondentRecords ){
		
		Collections.sort(corespondentRecords, Collections.reverseOrder( new Comparator<JJSCorespondent>(){
		    	@Override
			public int compare(JJSCorespondent c1, JJSCorespondent c2){
			    return c1.getOID().compareTo(c2.getOID());
			}
		 }));
		
		regform.setCorespondents(corespondentRecords);
		regform.setCorespondentCount(corespondentRecords.size());
	    }
	}
	   
	//Get JCVOP Data
	
	Collection<JuvenileReferralVOPResponseEvent> juvNumRefNumVOP = JuvenileReferralHelper.getVOPRecordsForJuvNumRefNum(juvenileId, referralId);
	if (juvNumRefNumVOP != null && juvNumRefNumVOP.size() > 0)
	{
	    regform.setJcVOPs(juvNumRefNumVOP);
	    regform.setJcVOPCount(juvNumRefNumVOP.size());
	}
	/*JCVOP vopRecord = JuvenileReferralHelper.getJCVOPRecordForJuvNumRefNum(juvenileId, referralId);
	if (vopRecord != null )
	{
	  regform.setJcVOP(vopRecord);
	  regform.setJcVOPCount(1);
	}*/
    } //end of retrieveAssocicatedReferralData
    
}
