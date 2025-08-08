/*
 * Created on Mar 27, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerprogramreferrals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import messaging.administerprogramreferrals.reply.SuperviseeCaseResponseEvent;
import messaging.administerprogramreferrals.reply.SupervisionOrderConditionResponseEvent;
import mojo.km.utilities.DateUtil;
import pd.criminalcase.CriminalCase;
import pd.supervision.administercaseload.CaseAssignmentOrder;
import pd.supervision.supervisionorder.SupervisionOrderCondition;
import pd.supervision.supervisionorder.SupervisionOrderConditionView;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSCaseHelper 
{

	/**
	 * Return list of active cases for given supervisee
	 * @param defendantId
	 * @return
	 */
	public static List getSuperviseeActiveCases(String defendantId)
	{
		return CaseAssignmentOrder.findAllBySupervisee(defendantId);
	}//end of getSuperviseeActiveCases()
	
	/**
	 * Create supervisee case response event from given case assignment
	 * @param caseAssignment
	 * @return
	 */
	public static SuperviseeCaseResponseEvent getSuperviseeCaseResponseEvent(
													CaseAssignmentOrder caseAssignment)
	{
		SuperviseeCaseResponseEvent supervisee_case_response = 
										new SuperviseeCaseResponseEvent();
		
			//retrieve case info for this active case assignment
		CriminalCase criminal_case = caseAssignment.getCriminalCase();
		
			//set response event properties
		supervisee_case_response.setCdi(criminal_case.getCourtDivisionId());
		supervisee_case_response.setCaseNumber(criminal_case.getCaseNum());
		supervisee_case_response.setCourtNumber(criminal_case.getCourtId());
		
		if (criminal_case.getOffenseCode() != null)
		{
			supervisee_case_response.setOffense(criminal_case.getOffenseCode().getDescription());			
		}
		
		supervisee_case_response.setCaseFiledDate(null);
		try
		{
			String filingDateStr = criminal_case.getFormattedFilingDate();
			Date filingDate = DateUtil.stringToDate(filingDateStr, DateUtil.DATE_FMT_1);
			supervisee_case_response.setCaseFiledDate(filingDate);
		}
		catch(Exception ex)
		{
			supervisee_case_response.setCaseFiledDate(null);
		}
		supervisee_case_response.setOrderStatusCd(caseAssignment.getOrderStatusCode());
		supervisee_case_response.setVersionCd(caseAssignment.getVersionTypeCode());
		supervisee_case_response.setOrderFiledDate(caseAssignment.getOrderFiledDate());
		
			//return case response event
		return supervisee_case_response;
	}//end of getSuperviseeCaseResponseEvent()
	
	/**
	 * Get order conditions for given case
	 * @param criminalCaseId
	 * @return
	 */
	public static List getOrderConditions(String criminalCaseId)
	{
		
		ArrayList conditionsList = new ArrayList();
		HashMap sprOrderCondIdSeqNoMap = new HashMap();
		
		
			//retrieve all order conditions for given case
			Iterator iter = SupervisionOrderConditionView.findAll("caseNumber", criminalCaseId);
			ArrayList supervisionOrderConditionIdsList = new ArrayList();
			
			 while(iter.hasNext())
			 {
				 SupervisionOrderConditionView orderCondition = (SupervisionOrderConditionView)iter.next();
				 supervisionOrderConditionIdsList.add(orderCondition.getOID());
				 sprOrderCondIdSeqNoMap.put(orderCondition.getOID(), orderCondition.getSequenceNum());
			 }
			 
			 if ( supervisionOrderConditionIdsList.size() > 0 ){
				
				 Iterator conditionsIter = SupervisionOrderCondition.findAllByIds(supervisionOrderConditionIdsList);
				 while(conditionsIter.hasNext())
				 {
					 SupervisionOrderCondition orderCondition = (SupervisionOrderCondition)conditionsIter.next();
					 
					 SupervisionOrderConditionResponseEvent responseEvent = new SupervisionOrderConditionResponseEvent();
					 responseEvent.setConditionSeqNum((Integer)sprOrderCondIdSeqNoMap.get(orderCondition.getOID()));
					 responseEvent.setConditionDescription(orderCondition.getResolvedDescription());
					 conditionsList.add(responseEvent);
				 }
			 }
			 
		 
		 return conditionsList;
		
	}//end of getOrderConditions()
	
}//end of CSCaseHelper
