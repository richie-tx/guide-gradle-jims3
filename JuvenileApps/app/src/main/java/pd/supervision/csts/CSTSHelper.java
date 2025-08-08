/*
 * Created on Apr 7, 2008
 *
 */
package pd.supervision.csts;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.administersupervisee.GetTransfersEvent;
import messaging.csserviceprovider.GetSPProgramLocationEvent;
import messaging.csts.GetCSTSStatusEvent;
import messaging.domintf.managesupervisioncase.ISupervisionCase;
import messaging.legacyupdates.GetLegacyUpdatesEvent;
import messaging.legacyupdates.UpdateReferralExitDataEvent;
import messaging.legacyupdates.UpdateReferralSubmitDataEvent;
import messaging.legacyupdates.UpdateRiskNeedScoreAssessmentDataEvent;
import messaging.legacyupdates.UpdateSCSAssessmentDataEvent;
import messaging.legacyupdates.UpdateTransferCaseDataEvent;
import mojo.km.security.helper.SecurityUtil;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import naming.CSCAssessmentConstants;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.UIConstants;
import pd.codetable.PDCodeHelper;
import pd.codetable.supervision.CSProgramReferralType;
import pd.common.util.StringUtil;
import pd.supervision.administerassessments.Assessment;
import pd.supervision.administerassessments.AssessmentQuestionAnswer;
import pd.supervision.administerassessments.LSIR;
import pd.supervision.administerassessments.StrategiesForCaseSupervision;
import pd.supervision.administerassessments.Wisconsin;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.administercaseload.SuperviseeHistory;
import pd.supervision.administerprogramreferrals.CSProgramReferral;
import pd.supervision.administerprogramreferrals.CSProgramReferralHelper;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProvider;
import pd.supervision.legacyupdates.ILegacyUpdates;
import pd.supervision.legacyupdates.LegacyUpdateLog;
import pd.supervision.legacyupdates.LegacyUpdatesImpl;
import pd.supervision.managesupervisioncase.OutOfCountyProbationCase;
import pd.supervision.supervisionorder.SupervisionOrderHelper;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;
import pd.supervision.transfers.CSTransfer;
import pd.supervision.transfers.TransferCase;

/**
 * @author dgibler
 *
 */
public class CSTSHelper {
	private static String CSTS_PENDING = "P";
	private static final String HARRIS_COUNTY_JURISDICTION_CODE = "101";
	private static final String INITIAL_ASSESSMENT = "I";
	private static final String REASSESSMENT = "R";
	private static String T = "T";
	private static String T11 = "11";
	private static String T21 = "21";
	private static String T22 = "22";
	private static String T30 = "30";
	private static final String TRANSFER_IN_TYPE_CODE="2";
	private static final String TRANSFER_OUT_TYPE_CODE="3";
	private static String TST20 = "20";
	private static String TST30 = "T30";
	private static String TST33 = "T33";
	private static String TST34 = "T34";
	private static final String ZERO = "0";
	public static final String CSTS_WORKGROUP_NAME = "CSTS WORKGROUP";
	public static final String WORKGROUP_PREFIX = "CSWORKGROUP_";
	public static final String SA = "SA";
	public static final String STATUS_O = "O";
	public static final String FOR = " for ";
	public static final String TO = " to ";
	public static final String SPACE = " ";

	
	
	public static void createCSTS(CSCDStaffPosition staffPosition){
        
    }
	/**
     * @param lsirAssessment
     */
    public static void createCSTS(LSIR lsirAssessment){
        //If assessmentId = masterAssessmentId (new assessment) create new TST21 to LegacyUpdateLog.
        //If updating an assessment:
        //Execute attribute query using masterAssessmentId against jims2 legacy update table.
    	UpdateRiskNeedScoreAssessmentDataEvent assessmentRequest = new UpdateRiskNeedScoreAssessmentDataEvent();
    	ILegacyUpdates assessmentHandler = new LegacyUpdatesImpl();
    	try{    		
    		Assessment assessment = lsirAssessment.getAssessment(); 
    		assessmentRequest.setJims2SourceId(assessment.getMasterAssessmentId());
    		assessmentRequest.setLogonId(SecurityUtil.getCurrentUser().getJIMSLogonId());
    		assessmentRequest.setRecType("T21");
    		assessmentRequest.setSpn(assessment.getDefendantId());
    		assessmentRequest.setCriminalCaseId(SupervisionOrderHelper.getActiveCaseId(assessment.getDefendantId()));

    		if(lsirAssessment.getIsReassessment())
    		{
    			assessmentRequest.setAssessmentType(REASSESSMENT);
    		}
    		else
    		{
    			assessmentRequest.setAssessmentType(INITIAL_ASSESSMENT);
    		}
    		assessmentRequest.setAssessmentDate(assessment.getAssessmentDate());
    		List qaList = CollectionUtil.iteratorToList(assessment.getAssessmentQuestionAnswers().iterator());
    		
    		for(int i=0;i<qaList.size();i++){
    			AssessmentQuestionAnswer ans = (AssessmentQuestionAnswer) qaList.get(i);
    			if("LSIR_Q1".equals(ans.getQuestionId())){
    				assessmentRequest.setRiskScore(ans.getAnswerText());
    			} else {
    				assessmentRequest.setNeedsScore(ans.getAnswerText());
    			}
    		}
    		assessmentHandler.create(assessmentRequest,"T21");
    	} catch (Exception e) {
			e.printStackTrace();			
		}    	    	
        
    }

	public static void createCSTS(LSIR lsirAssessment, String newNeedsScore, String newRiskScore) {
        //If assessmentId = masterAssessmentId (new assessment) create new TST21 to LegacyUpdateLog.
        //If updating an assessment:
        //Execute attribute query using masterAssessmentId against jims2 legacy update table.
    	UpdateRiskNeedScoreAssessmentDataEvent assessmentRequest = new UpdateRiskNeedScoreAssessmentDataEvent();
    	ILegacyUpdates assessmentHandler = new LegacyUpdatesImpl();
    	try{    		
    		Assessment assessment = lsirAssessment.getAssessment(); 
    		assessmentRequest.setJims2SourceId(assessment.getMasterAssessmentId());
    		assessmentRequest.setLogonId(SecurityUtil.getCurrentUser().getJIMSLogonId());
    		assessmentRequest.setRecType("T21");
    		assessmentRequest.setSpn(assessment.getDefendantId());
    		assessmentRequest.setCriminalCaseId(SupervisionOrderHelper.getActiveCaseId(assessment.getDefendantId()));

    		if(lsirAssessment.getIsReassessment())
    		{
    			assessmentRequest.setAssessmentType(REASSESSMENT);
    		}
    		else
    		{
    			assessmentRequest.setAssessmentType(INITIAL_ASSESSMENT);
    		}
    		assessmentRequest.setAssessmentDate(assessment.getAssessmentDate());
			assessmentRequest.setRiskScore(newRiskScore);
			assessmentRequest.setNeedsScore(newNeedsScore);
    		assessmentHandler.create(assessmentRequest,"T21");
    	} catch (Exception e) {
			e.printStackTrace();			
		}  
		
	}

    /**
     * @param scsAssessment
     */
    public static void createCSTS(StrategiesForCaseSupervision scsAssessment){
        //If assessmentId = masterAssessmentId (new assessment) create new TST22  to LegacyUpdateLog.
        //If updating an assessment:
        //Execute attribute query using masterAssessmentId against jims2 legacy update table.
    	
    	UpdateSCSAssessmentDataEvent assessmentRequest = new UpdateSCSAssessmentDataEvent();
    	ILegacyUpdates assessmentHandler = new LegacyUpdatesImpl();
    	try{    		
    		Assessment assessment = scsAssessment.getAssessment(); 
    		assessmentRequest.setJims2SourceId(assessment.getMasterAssessmentId());
    		assessmentRequest.setLogonId(SecurityUtil.getCurrentUser().getJIMSLogonId());
    		assessmentRequest.setRecType("T22");
    		assessmentRequest.setSpn(assessment.getDefendantId());
    		assessmentRequest.setCriminalCaseId(SupervisionOrderHelper.getActiveCaseId(assessment.getDefendantId()));
    		assessmentRequest.setAssessmentCode(PDConstants.BLANK);
    		assessmentRequest.setAssessmentDate(assessment.getAssessmentDate());  
    		if (scsAssessment.getPrimaryClassificationId().startsWith("S")){
    			String desc = PDCodeHelper.getCodeDescription(
    					PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_CLASSIFICATION, 
    					scsAssessment.getPrimaryClassificationId());
  				desc = desc.replaceAll("-", PDConstants.BLANK);
    			assessmentRequest.setScsClassification(desc);
    		} else {
    			assessmentRequest.setScsClassification(scsAssessment.getPrimaryClassificationId());
    		}

    		assessmentHandler.create(assessmentRequest,"T22");
    	} catch (Exception e) {
			e.printStackTrace();			
		}
    	

    }

    public static void createCSTS(Supervisee supervisee, SuperviseeHistory history){}
    /**
     * @param wisconsinAssessment
     */
    public static void createCSTS(Wisconsin wisconsinAssessment){
        //If assessmentId = masterAssessmentId (new assessment) create new TST21 to LegacyUpdateLog.
        //If updating an assessment:
        //Execute attribute query using masterAssessmentId against jims2 legacy update table.
    	UpdateRiskNeedScoreAssessmentDataEvent assessmentRequest = new UpdateRiskNeedScoreAssessmentDataEvent();
    	ILegacyUpdates assessmentHandler = new LegacyUpdatesImpl();
    	try{    		
    		Assessment assessment = wisconsinAssessment.getAssessment(); 
    		assessmentRequest.setJims2SourceId(assessment.getMasterAssessmentId());
    		assessmentRequest.setLogonId(SecurityUtil.getCurrentUser().getJIMSLogonId());
    		assessmentRequest.setRecType("T21");
    		assessmentRequest.setSpn(assessment.getDefendantId());
    		assessmentRequest.setCriminalCaseId(SupervisionOrderHelper.getActiveCaseId(assessment.getDefendantId()));

    		if(wisconsinAssessment.getIsReassessment())
    		{
    			assessmentRequest.setAssessmentType(REASSESSMENT);
    		}
    		else
    		{
    			assessmentRequest.setAssessmentType(INITIAL_ASSESSMENT);
    		}
    		assessmentRequest.setAssessmentDate(assessment.getAssessmentDate()); 
    		assessmentRequest.setRiskScore(new Integer(wisconsinAssessment.getTotalRiskScore()).toString());
    		assessmentRequest.setNeedsScore(new Integer(wisconsinAssessment.getTotalNeedsScore()).toString());
    		
    		assessmentHandler.create(assessmentRequest,"T21");
    	} catch (Exception e) {
			e.printStackTrace();			
		}    	

    }
    public static void createCSTSRecordForHarrisCountyCase(TransferCase transferCase)
    {
//       create T30 record
       	try 
       	{
       		UpdateTransferCaseDataEvent updateTransferCaseEvt = new UpdateTransferCaseDataEvent();
    		ILegacyUpdates legacyUpdateHandler = new LegacyUpdatesImpl();  
    		
    		updateTransferCaseEvt.setAction(transferCase.getActionType());
    		updateTransferCaseEvt.setTransferDate(DateUtil.stringToDate(transferCase.getTransactionDate(), "yyyyMMdd"));
    		
    		String transferringCntyCode = transferCase.getTransferringCountyCode();
    		try
    		{
    			Integer.parseInt(transferringCntyCode);
//        		county code(numeric) should be 3 characters
    			while(transferringCntyCode.length() < 3)
    			{
    				transferringCntyCode = ZERO + transferringCntyCode;
    			}
    		}
    		catch(Exception ex)
    		{
//    			do nothing for state code (state code is alphabetic e.g. "TX")
    		}
			updateTransferCaseEvt.setTransfrCntyJurisCode(transferringCntyCode);
			
			String receivingCntyCode = transferCase.getReceivingCountyCode();
			try
    		{
    			Integer.parseInt(receivingCntyCode);
//        		county code(numeric) should be 3 characters
    			while(receivingCntyCode.length() < 3)
    			{
    				receivingCntyCode = ZERO + receivingCntyCode;
    			}
    		}
    		catch(Exception ex)
    		{
//    			do nothing for state code (state code is alphabetic e.g. "TX")
    		}
    		updateTransferCaseEvt.setReceivngCntyJurisCode(receivingCntyCode);
    		updateTransferCaseEvt.setTransferTypeCode(transferCase.getTransferType());
    		updateTransferCaseEvt.setOriCntyPersonId(transferCase.getOriginationCountyPersonId());
    		updateTransferCaseEvt.setSuprvsngCntyPersonId(transferCase.getSupervisingCountyPersonId());
    		updateTransferCaseEvt.setSpn(transferCase.getOriginationCountyPersonId());
     		updateTransferCaseEvt.setRecType(TST30);
     		StringBuffer crimcaseOID = new StringBuffer();
    		if (transferCase.getCriminalCaseId().length() > 12){
    			crimcaseOID.append(transferCase.getCriminalCaseId());
    		} else {
    			crimcaseOID.append(transferCase.getCdi());
    			crimcaseOID.append(transferCase.getCriminalCaseId());
    		}
    		updateTransferCaseEvt.setCriminalCaseId(crimcaseOID.toString());
    		//updateTransferCaseEvt.setJims2SourceId(crimcaseOID.toString());
    		//Append jzt info to jims2SourceOid
    		StringBuffer jims2SourceOid = new StringBuffer(crimcaseOID.toString());
    		//JZT info will not be present on add.
    		if (transferCase.getCicsRecordClassSeqNumber() != null 
    				&& !transferCase.getCicsRecordClassSeqNumber().equals(PDConstants.BLANK)
    				&& transferCase.getCicsRecordSubclassSeqNumber() != null
    				&& !transferCase.getCicsRecordSubclassSeqNumber().equals(PDConstants.BLANK)){
    			jims2SourceOid.append(transferCase.getCicsRecordClassSeqNumber());
    			jims2SourceOid.append(transferCase.getCicsRecordSubclassSeqNumber());
    		}
    		updateTransferCaseEvt.setJims2SourceId(jims2SourceOid.toString());
    		updateTransferCaseEvt.setLogonId(SecurityUtil.getCurrentUser().getJIMSLogonId());
    		
    		legacyUpdateHandler.create(updateTransferCaseEvt,TST30);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    public static void createCSTSRecordForOOC(OutOfCountyProbationCase outOfCountyProbCase, String action, boolean isTransferIn, boolean isJurisCdChanged)
    {
//       create T30 record
       	try 
       	{
       		UpdateTransferCaseDataEvent updateTransferCaseEvt = new UpdateTransferCaseDataEvent();
    		ILegacyUpdates legacyUpdateHandler = new LegacyUpdatesImpl();  
    		
    		StringBuffer defendantIdBuffer = new StringBuffer(outOfCountyProbCase.getSpn());
    		while(defendantIdBuffer.length()<8)
    		{
    			defendantIdBuffer.insert(0, ZERO);
    		}
    		
    		String jurisdictionCode = outOfCountyProbCase.getOriginalCountyId();
    		if(jurisdictionCode==null || jurisdictionCode.trim().equalsIgnoreCase(""))
    		{
    			jurisdictionCode = outOfCountyProbCase.getStateId();
    		}
    		else
    		{
//        		county code should be 3 characters
    			while(jurisdictionCode.length() < 3)
    			{
    				jurisdictionCode = ZERO + jurisdictionCode;
    			}
    		}

    		updateTransferCaseEvt.setAction(action);
    		
    		if(isTransferIn)
    		{
    			updateTransferCaseEvt.setTransferDate(outOfCountyProbCase.getTransferInDate());
    			updateTransferCaseEvt.setTransferTypeCode(TRANSFER_IN_TYPE_CODE);
    			
    			updateTransferCaseEvt.setTransfrCntyJurisCode(jurisdictionCode);
        		updateTransferCaseEvt.setReceivngCntyJurisCode(HARRIS_COUNTY_JURISDICTION_CODE);
    		}
    		else
    		{
    			updateTransferCaseEvt.setTransferDate(outOfCountyProbCase.getDispositionDate());
    			updateTransferCaseEvt.setTransferTypeCode(TRANSFER_OUT_TYPE_CODE);
    			
    			updateTransferCaseEvt.setTransfrCntyJurisCode(HARRIS_COUNTY_JURISDICTION_CODE);
        		updateTransferCaseEvt.setReceivngCntyJurisCode(jurisdictionCode);
    		}
    		
    		updateTransferCaseEvt.setOriCntyPersonId(outOfCountyProbCase.getPersonId());
    		updateTransferCaseEvt.setSuprvsngCntyPersonId(defendantIdBuffer.toString());
    		
    		updateTransferCaseEvt.setJurisCodeChanged(isJurisCdChanged);
    		
    		updateTransferCaseEvt.setRecType(TST30);
    		
    		updateTransferCaseEvt.setSpn(defendantIdBuffer.toString());
    		updateTransferCaseEvt.setJims2SourceId(outOfCountyProbCase.getCriminalCaseId());
    		updateTransferCaseEvt.setLogonId(SecurityUtil.getCurrentUser().getJIMSLogonId());
    		updateTransferCaseEvt.setCriminalCaseId(outOfCountyProbCase.getCriminalCaseId());
    		
    		legacyUpdateHandler.create(updateTransferCaseEvt,TST30);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * 
     * @param programReferral
     * @param programReferralType
     * @param cstsDischargeReason
     */
    public static void createCSTSRecordOnExit(CSProgramReferral programReferral, CSProgramReferralType programReferralType, String cstsDischargeReason)
    {
//    	On Exit of Program Referrals(status=open), a T34 record is created and sent to state when the Program has a 
//    	StateProgramCode and a corresponding T33 has been previously generated.
       	try {
    		UpdateReferralExitDataEvent updateReferralExit = new UpdateReferralExitDataEvent();
    		ILegacyUpdates assessmentHandler = new LegacyUpdatesImpl();  
    		updateReferralExit.setCtsCode(programReferralType.getCtsCode());
    		updateReferralExit.setProgBeginDate(programReferral.getProgramBeginDate());
    		updateReferralExit.setDesignator(programReferralType.getDesignator());
    		updateReferralExit.setProgEnDate(programReferral.getProgramEndDate());
    		updateReferralExit.setDischargeReason(cstsDischargeReason);
    		updateReferralExit.setRecType(TST34);
    		updateReferralExit.setSpn(programReferral.getDefendantId());
    		updateReferralExit.setJims2SourceId(programReferral.getOID());
    		updateReferralExit.setLogonId(SecurityUtil.getCurrentUser().getJIMSLogonId());
    		updateReferralExit.setCriminalCaseId(programReferral.getCriminalCaseId());

    		assessmentHandler.create(updateReferralExit,TST34);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    
    
    /**
     * 
     * @param programReferral
     * @param programReferralType
     */
    public static void createCSTSRecordOnSubmit(CSProgramReferral programReferral, CSProgramReferralType programReferralType)
    {
//       create T33 record
       	try {
       		UpdateReferralSubmitDataEvent updateReferralSubmit = new UpdateReferralSubmitDataEvent();
    		ILegacyUpdates assessmentHandler = new LegacyUpdatesImpl();  
    		updateReferralSubmit.setCtsCode(programReferralType.getCtsCode());
    		updateReferralSubmit.setProgBeginDate(programReferral.getProgramBeginDate());
    		updateReferralSubmit.setDesignator(programReferralType.getDesignator());
    		updateReferralSubmit.setContractProg(programReferral.isContractProgram());
    		updateReferralSubmit.setPlacementReason(programReferral.getPlacementReasonCd());
    		updateReferralSubmit.setConfineDays(programReferral.getConfinementDays());
    		updateReferralSubmit.setConfineMonths(programReferral.getConfinementMonths());
    		updateReferralSubmit.setConfineYears(programReferral.getConfinementYears());
    		updateReferralSubmit.setInHouse(programReferralType.getCjp());
    		updateReferralSubmit.setRecType(TST33);
    		updateReferralSubmit.setSpn(programReferral.getDefendantId());
    		updateReferralSubmit.setJims2SourceId(programReferral.getOID());
    		updateReferralSubmit.setLogonId(SecurityUtil.getCurrentUser().getJIMSLogonId());
    		updateReferralSubmit.setCriminalCaseId(programReferral.getCriminalCaseId());
    		updateReferralSubmit.setPfsCode( programReferralType.getPfsCode() );
    		assessmentHandler.create(updateReferralSubmit,TST33);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    public static void updateReferralSubmitCSTSRecord(CSProgramReferral programReferral, CSProgramReferralType refTypeCode)
    {
//       update T33 record
       	try {
       		UpdateReferralSubmitDataEvent updateReferralSubmit = new UpdateReferralSubmitDataEvent();
    		ILegacyUpdates referralHandler = new LegacyUpdatesImpl(); 
    		updateReferralSubmit.setCtsCode(refTypeCode.getCtsCode());
    		updateReferralSubmit.setDesignator(refTypeCode.getDesignator());
    		updateReferralSubmit.setProgBeginDate(programReferral.getProgramBeginDate());
    		updateReferralSubmit.setPlacementReason(programReferral.getPlacementReasonCd());
    		// for incarceration referrals
            updateReferralSubmit.setConfineDays( programReferral.getConfinementDays() );
            updateReferralSubmit.setConfineMonths( programReferral.getConfinementMonths() );
            updateReferralSubmit.setConfineYears( programReferral.getConfinementYears() );

     		updateReferralSubmit.setRecType(TST33);
    		updateReferralSubmit.setSpn(programReferral.getDefendantId());
    		updateReferralSubmit.setJims2SourceId(programReferral.getOID());
    		updateReferralSubmit.setLogonId(SecurityUtil.getCurrentUser().getJIMSLogonId());
    		updateReferralSubmit.setCriminalCaseId(programReferral.getCriminalCaseId());
    		updateReferralSubmit.setContractProg(programReferral.isContractProgram());
    		updateReferralSubmit.setInHouse(refTypeCode.getCjp());
    		
    		referralHandler.create(updateReferralSubmit,TST33);

    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    public static void updateReferralExitCSTSRecord(CSProgramReferral programReferral, CSProgramReferralType refTypeCode)
    {
//       update T34 record
       	try {
       		UpdateReferralExitDataEvent updateReferralExit = new UpdateReferralExitDataEvent();
    		ILegacyUpdates referralHandler = new LegacyUpdatesImpl();  
    		updateReferralExit.setProgBeginDate(programReferral.getProgramBeginDate());
    		updateReferralExit.setProgEnDate(programReferral.getProgramEndDate());
			String cstsDischargeReasCd = CSProgramReferralHelper.convertJims2DisReasCdToCstsDisReasCd(programReferral.getDischargeReasonCd());
    		updateReferralExit.setDischargeReason(cstsDischargeReasCd);
    		updateReferralExit.setRecType(TST34);
    		updateReferralExit.setSpn(programReferral.getDefendantId());
    		updateReferralExit.setJims2SourceId(programReferral.getOID());
    		updateReferralExit.setLogonId(SecurityUtil.getCurrentUser().getJIMSLogonId());
    		updateReferralExit.setCriminalCaseId(programReferral.getCriminalCaseId());
    		updateReferralExit.setDesignator(refTypeCode.getDesignator());
    		updateReferralExit.setCtsCode(refTypeCode.getCtsCode());
    		referralHandler.create(updateReferralExit,TST34);

    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
   
    /**
     * 
     * @param programReferral
     * @return
     */
    /* public static String getJims2ReasonForDischargeCd(CSProgramReferral programReferral)
    {
    	/* Read T35/T36 records for TST code,
    	//convert TST code to JIMS2 DischargeReason
    	//return JIMS2 DischargeReason
    	
    	//If TST code not obtained from the T-records, then return Jims2DischargeReason="Unknown" 
    	
    	String jims2DischrgReasCd = PDCodeTableConstants.JIMS2_DISCHRG_REAS_UNKNOWN;
		LegacyDischargeReason ldr = LegacyDischargeReason.find(programReferral.getDefendantId(), programReferral.getCriminalCaseId());
		if (ldr != null){
			GetRelativeCodeEvent event = new GetRelativeCodeEvent();
			event.setConversionType(PDCodeTableConstants.CONVERSION_TYPE_HC_AUTO_EXIT);
			event.setCode(ldr.getLegacyDischargeReasonCode());
			
			Iterator iterator = CSDischargeReasonCode.findAll(event);
			if(iterator != null && iterator.hasNext())
			{
				CSDischargeReasonCode dischargeReasonRecord = (CSDischargeReasonCode)iterator.next();
				String dischargeReasonCd = dischargeReasonRecord.getRelativeCode();
				programReferral.setDischargeReasonCd(dischargeReasonCd);
			}
			
		}

    	return jims2DischrgReasCd;
    }*/
    
    public boolean hasT11RecordBeenSentToState(String spn, String caseNum){
		boolean sentToState = hasRecordBeenSentToState(spn, caseNum, T11);
       	return sentToState;
    }
    public static boolean hasT30RecordBeenSentToState(String spn, String caseNum){
		boolean sentToState = hasRecordBeenSentToState(spn, caseNum, T30);
       	return sentToState;
    }
 
    public static boolean hasRecordBeenSentToState(String spn, String caseNum, String recType){
		boolean sentToState = false;
        GetCSTSStatusEvent getCSTSStatusEvent = new GetCSTSStatusEvent();
        if (spn.length() < 8){
        	StringBuffer sb = new StringBuffer(spn);
        	while (sb.length() < 8){
        		sb.insert(0, 0);
        	}
        	spn = sb.toString();
        }
        getCSTSStatusEvent.setSpn(spn);
        getCSTSStatusEvent.setCaseNum(caseNum);
       	getCSTSStatusEvent.setRecordType(recType);
       	getCSTSStatusEvent.setSeqNum(PDConstants.BLANK);
       	sentToState = hasCSTSRecordBeenSentToState(getCSTSStatusEvent);
       	return sentToState;
    }

    /**
     * @param assessment
     * @return
     */
    public static boolean hasCSTSRecordBeenSentToState(Assessment assessment){
        //if assessmentType = LSIR or WISCONSIN CSTSStatus lookup in LegacyUpdateLog for TST21 using masterAssessmentId
        //if assessmentType = SCS CSTSStatus lookup in LegacyUpdateLog for TST22 using masterAssessmentId
        //if status = "sent to state" return true.
        //Otherwise return false.
		boolean sentToState = false;
        GetCSTSStatusEvent getCSTSStatusEvent = new GetCSTSStatusEvent();
        getCSTSStatusEvent.setSpn(assessment.getDefendantId());
        
        if (assessment.getAssessmentTypeId().equals(CSCAssessmentConstants.ASSESSMENTTYPE_SCS)){
        	getCSTSStatusEvent.setRecordType(T22);
        } else {
        	getCSTSStatusEvent.setRecordType(T21);
        }
        
		GetLegacyUpdatesEvent getLegacyLogEvt = new GetLegacyUpdatesEvent();
		getLegacyLogEvt.setRecordType(T + getCSTSStatusEvent.getRecordType());
		getLegacyLogEvt.setSourceOID(assessment.getMasterAssessmentId());
		getLegacyLogEvt.setSpn(assessment.getDefendantId());
		
		List list = LegacyUpdateLog.findAll(getLegacyLogEvt);
		
		if (list != null && list.size() > 0){
			LegacyUpdateLog legUpdateLog = (LegacyUpdateLog) list.get(0);
			String seqNum = legUpdateLog.getCstsSeqNo();
			if (seqNum==null){
				seqNum = " ";
			}
			getCSTSStatusEvent.setSeqNum(seqNum);  
			sentToState = hasCSTSRecordBeenSentToState(getCSTSStatusEvent);
		}
        
    	return sentToState;
    }
    /**
     * @param assessment
     * @return
     */
    public static boolean hasCSTSRecordBeenSentToState(String defendantId, SuperviseeHistory losHistory){
		boolean sentToState = false;
       
		GetLegacyUpdatesEvent getLegacyLogEvt = new GetLegacyUpdatesEvent();
		getLegacyLogEvt.setRecordType(T + TST20);
		getLegacyLogEvt.setSourceOID(losHistory.getOID());
		getLegacyLogEvt.setSpn(defendantId);
		
		List list = LegacyUpdateLog.findAll(getLegacyLogEvt);
		
		if (list != null && list.size() > 0){
			LegacyUpdateLog legUpdateLog = (LegacyUpdateLog) list.get(0);
			String seqNum = legUpdateLog.getCstsSeqNo();
			if (seqNum != null && !seqNum.trim().equals(PDConstants.BLANK)){
				GetCSTSStatusEvent getCSTSStatusEvent = new GetCSTSStatusEvent();
				getCSTSStatusEvent.setSpn(defendantId);
				getCSTSStatusEvent.setRecordType(TST20);
				getCSTSStatusEvent.setSeqNum(seqNum);  
				sentToState = hasCSTSRecordBeenSentToState(getCSTSStatusEvent);
			}
		}
        
    	return sentToState;
    }   
    
    
    /**
	 * @param getStatusEvent
	 * @return
	 */
	public static boolean hasCSTSRecordBeenSentToState(GetCSTSStatusEvent getStatusEvent){
		boolean sentToState = false;
		if (getStatusEvent.getCaseNum() == null){
			getStatusEvent.setCaseNum(PDConstants.BLANK);
		}
		if (getStatusEvent.getSeqNum() == null){
			getStatusEvent.setSeqNum(PDConstants.BLANK);
		}
		List aList = CSTSStatus.findAll(getStatusEvent);
		if (aList != null && aList.size() > 0){
			CSTSStatus cstsStatus = (CSTSStatus) aList.get(0);
			if (!cstsStatus.getStatus().equals(CSTS_PENDING)){ //Status = "S" (sent) or "R" (sent and rejected)
				sentToState = true;
			}
		}
		return sentToState;
	}
    /**
     * @param assessment
     * @return
     */
    public static boolean hasCSTSRecordBeenSentToState(Supervisee supervisee, SuperviseeHistory superviseeHistory){
   	
        GetCSTSStatusEvent getCSTSStatusEvent = new GetCSTSStatusEvent();
        getCSTSStatusEvent.setRecordType(TST20);
        getCSTSStatusEvent.setSpn(supervisee.getDefendantId());
    	return hasCSTSRecordBeenSentToState(getCSTSStatusEvent);
    }
	/**
     * 
     * @param programReferral
     * @param isSubmitRecord
     * @return
     */
    public static boolean isCSTSRecordBeenSentToState(CSProgramReferral programReferral, boolean isSubmitRecord)
    {
    	GetLegacyUpdatesEvent legacyEvent = new GetLegacyUpdatesEvent();
    	
    	legacyEvent.setSpn(programReferral.getDefendantId());
    	legacyEvent.setSourceOID(programReferral.getOID());
    	if(isSubmitRecord)
    	{
    		legacyEvent.setRecordType(TST33);
    	}
    	else
    	{
    		legacyEvent.setRecordType(TST34);
    	}
    	boolean sentToState = false;
    	
    	List<LegacyUpdateLog> list = LegacyUpdateLog.findAll(legacyEvent);
		if (list != null && !list.isEmpty())
		{
			LegacyUpdateLog log = list.get(0);
			if(!StringUtil.isEmpty(log.getCstsSeqNo()))
			{
				String seqNum = log.getCstsSeqNo();
				if (seqNum != null && !seqNum.trim().equals(PDConstants.BLANK)){
					GetCSTSStatusEvent getCSTSStatusEvent = new GetCSTSStatusEvent();
					getCSTSStatusEvent.setSpn(log.getSpn());
					getCSTSStatusEvent.setRecordType(log.getRecordTypeId().substring(1));
					getCSTSStatusEvent.setSeqNum(log.getCstsSeqNo());  
					sentToState = hasCSTSRecordBeenSentToState(getCSTSStatusEvent);
				}
			}
		}
    	return sentToState;
    }
    
    /**
     * 
     * @param oocCase
     * @return
     * rry -Do not create a T30-2 if one already exists for the county/StateCd
     */
    public String isCurrentCaseForJurisdiction( ISupervisionCase oocCase ){
    	
    	// Default to Y
		String updateT30Record = UIConstants.YES;
		
    	GetTransfersEvent event = new GetTransfersEvent();
		event.setSuperviseeId( oocCase.getSpn() );
		event.setSearchType("S"); // search by SPN instead of Case
		String origCountyState = "";
		
		if ( oocCase.getStateId()!= null && !"".equals( oocCase.getStateId() )){
			
			origCountyState = oocCase.getStateId();
		}else {
			
			origCountyState = oocCase.getOriginalCountyId();
		}
		
		
		Iterator iter = CSTransfer.findAll( event );
		while ( iter.hasNext()){
			
			CSTransfer transfers = (CSTransfer) iter.next();
			
			Date transOutDate = DateUtil.stringToDate( transfers.getOutOfCountyTransferOutDate().trim(), "yyyyMMdd" );
			if ( !origCountyState.matches("[a-zA-Z]*")){
				
				StringBuffer padCountyId = new StringBuffer( oocCase.getOriginalCountyId() );
				while ( padCountyId.length() < 3 ){
					padCountyId.insert( 0, "0" );
		    	}
				origCountyState = padCountyId.toString();
			}
							
			if ( origCountyState.equalsIgnoreCase( transfers.getTransferringCountyState().trim() )){
				
				if ( transOutDate == null )
					updateT30Record = "";				
					System.out.println( " T30-2 not built! " + transfers.getTransferringCountyState().trim() + " Date : " + transOutDate );
					break;
			}
		}
    	
    	return updateT30Record;
    }
}
