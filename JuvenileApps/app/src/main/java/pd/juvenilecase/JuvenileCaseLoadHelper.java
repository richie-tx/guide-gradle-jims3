package pd.juvenilecase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import messaging.juvenilecase.reply.JuvenileCaseLoadRepsonseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileEvent;
import messaging.juvenilecase.reply.JuvenileReferralCourtDateResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import naming.PDJuvenileCaseConstants;

import org.apache.commons.lang.StringUtils;

import pd.codetable.Code;
import pd.common.util.DateFormatter;
import pd.juvenilecase.referral.JJSReferral;

public final class JuvenileCaseLoadHelper {
	   
	private JuvenileCaseLoadHelper()
	{
	}
	
	/**
	 * @param casefile
	 * @param caseloads
	 */
	public static void buildCaseloadResponses(JuvenileCasefile casefile,HashMap caseloads){
		List casefiles = null;
		JuvenileCaseLoadRepsonseEvent resp = getCaseloadResponseEvent(casefile);
		JuvenileCasefileEvent casefileAssignment = getCasefileEvent(casefile);
		
		if(!caseloads.isEmpty() && caseloads.containsKey(casefile.getJuvenileId())){
			resp = (JuvenileCaseLoadRepsonseEvent) caseloads.get(casefile.getJuvenileId());
			casefiles = resp.getCasefileAssignments();
			//casefileAssignment = getCasefileEvent(casefile);
			casefiles.add(casefileAssignment);
			resp.setCasefileAssignments(casefiles);
			caseloads.put(casefile.getJuvenileId(), resp);
		}else{
			resp = getCaseloadResponseEvent(casefile);
			casefiles = new ArrayList<JuvenileCasefileEvent>();
			//casefileAssignment = getCasefileEvent(casefile);
			casefiles.add(casefileAssignment);
			resp.setCasefileAssignments(casefiles);
			caseloads.put(casefile.getJuvenileId(), resp);
		}
	}

	/**
	 * @param casefile
	 * @return JuvenileCaseLoadRepsonseEvent
	 */
	private static JuvenileCaseLoadRepsonseEvent getCaseloadResponseEvent(JuvenileCasefile casefile) {
		JuvenileCaseLoadRepsonseEvent resp = new JuvenileCaseLoadRepsonseEvent();
		resp.setJuvenileNum(casefile.getJuvenileNum());
		resp.setProbationOfficerId(casefile.getProbationOfficerId());
		
		return resp;
	}
	
	/**
	 * @param casefile
	 * @return JuvenileCasefileAssignmentEvent
	 */
	private static JuvenileCasefileEvent getCasefileEvent(JuvenileCasefile casefile) {
		JuvenileCasefileEvent resp = new JuvenileCasefileEvent();
		resp.setJuvenileNum(casefile.getJuvenileId());
		resp.setProbationOfficeId(casefile.getProbationOfficerId());
		resp.setSupervisionNum(casefile.getOID());
		resp.setSequenceNum(casefile.getSequenceNumber());
		
		Code caseStatus = casefile.getCaseStatus();
		if (caseStatus != null)
			resp.setCaseStatus(caseStatus.getDescription());
		
		Code superType = casefile.getSupervisionType();
		if (superType != null)
			resp.setSupervisionType(superType.getDescription());
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		resp.setSupervisionEndDate(casefile.getSupervisionEndDate() != null?df.format(casefile.getSupervisionEndDate()):"") ;
		resp.setActivationDate(casefile.getActivationDate() != null?df.format(casefile.getActivationDate()):"");
		resp.setJpoAssignmentDate(casefile.getJpoAssignmentDate() != null?df.format(casefile.getJpoAssignmentDate()):"");
		
		if(StringUtils.equals(PDJuvenileCaseConstants.CASE_STATUS_CLOSE, caseStatus.getCode())){
			// Just for display purpose,assigning to a random number
			resp.setDaysLeft("999999");
		}else{
			Integer days = DateFormatter.getDayDiff(cal.getTime(), casefile.getSupervisionEndDate());
			resp.setDaysLeft(days != null?Integer.toString(days):"");
		}
		
		resp.setMAYSINeeded(casefile.getIsMAYSINeeded());
		resp.setTitleIVNeeded(casefile.getIsBenefitsAssessmentNeeded());
		//changed for US 14459
		/*if(casefile.getIsCommunityRiskNeeded() || casefile.getIsInterviewRiskNeeded() || casefile.getIsProgressRiskNeeded() || 
				casefile.getIsReferralRiskNeeded() || casefile.getIsResidentialRiskNeeded() || casefile.getIsRiskAssessmentNeeded() ||
				casefile.getIsTestingRiskNeeded())*/
		if(casefile.getIsCommunityRiskNeeded() || casefile.getIsProgressRiskNeeded() || 
				casefile.getIsReferralRiskNeeded() || casefile.getIsResidentialRiskNeeded() || casefile.getIsRiskAssessmentNeeded())
			resp.setRiskNeeded(true);
		else
			resp.setRiskNeeded(false);
		Iterator referrals = Assignment.findAll("caseFileId", resp.getSupervisionNum());
		List refCrtDates = new ArrayList();
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd"); 
		Date curDate = new Date();
		String curDateStr = sdfDate.format(curDate);
		String crtDateStr = "";
        while (referrals.hasNext())
        {
			Assignment ref = (Assignment) referrals.next();
			JuvenileReferralCourtDateResponseEvent resp2 = new JuvenileReferralCourtDateResponseEvent();
			GetJJSReferralEvent jjsEvt = new GetJJSReferralEvent();
			jjsEvt.setJuvenileNum(resp.getJuvenileNum());
			jjsEvt.setReferralNum(ref.getReferralNumber());
			Iterator i = JJSReferral.findAll(jjsEvt);
			if(i.hasNext()) {
				JJSReferral jjs = (JJSReferral)i.next();
				if (jjs.getCourtDate() != null){
					crtDateStr = sdfDate.format(jjs.getCourtDate());
					// only return values where court date is current or future date
					if (crtDateStr.compareTo(curDateStr) >= 0) {
					resp2.setCourtDate(jjs.getCourtDate());
					resp2.setReferralNumber(ref.getReferralNumber());
					resp2.setCourtId(jjs.getCourtId());
					resp2.setSequenceNum(ref.getSeqNum());
					refCrtDates.add(resp2);
					}
				}
			}
        } 
        Collections.sort(refCrtDates);
		resp.setReferralNumCourtDates(refCrtDates);
		return resp;
	}
	
	/**
	 * MasterStatus	: This attribute indicates of the juvenile record is associated with any active or pending casefiles.
	 * Valid Values=Active; Pending; Closed, No Casefile.
	 * @param juvNum
	 * @param casefiles
	 * @return masterStatus
	 */
	public static String getJuvenileMasterStatus(String juvNum,ArrayList<JuvenileCasefile> casefiles){
		boolean hasActiveCase = false;
		boolean hasPendingCase = false;
		boolean hasCasefile = false;
		Iterator<JuvenileCasefile> iter = casefiles.iterator();
		while (iter != null && iter.hasNext() && StringUtils.isNotEmpty(juvNum))
		{
			JuvenileCasefile casefile = iter.next();
			if(StringUtils.equalsIgnoreCase(juvNum, casefile.getJuvenileId())){
				if(PDJuvenileCaseConstants.CASESTATUS_ACTIVE.equalsIgnoreCase(casefile.getCaseStatusId())){
					hasActiveCase = true;
					break;
				}else if(PDJuvenileCaseConstants.CASESTATUS_PENDING.equalsIgnoreCase(casefile.getCaseStatusId())){
					hasPendingCase = true;
				}else{
					hasCasefile = true;
				}
			}
		}
		//Active, if at least one casefile for the juvenile is active.  
		//Pending, if no active and at least one pending casefile for the juvenile.  
		//Closed, if there are no casefiles either active or pending for the juvenile.
		//No Casefile, if no casefile or assignment record is associated to the juvenile master.
		String status = hasActiveCase?PDJuvenileCaseConstants.CASESTATUS_ACTIVE_DESCRIPTION:
						hasPendingCase?PDJuvenileCaseConstants.CASESTATUS_PENDING_DESCRIPTION:
						hasCasefile?PDJuvenileCaseConstants.CASESTATUS_CLOSED_DESCRIPTION:
						PDJuvenileCaseConstants.NO_CASEFILE;
		return status;
	}

}
