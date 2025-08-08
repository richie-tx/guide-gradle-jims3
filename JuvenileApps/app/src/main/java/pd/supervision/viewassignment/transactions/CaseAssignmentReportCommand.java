/*
 * Created on Jan 17, 2008
 *
 */
package pd.supervision.viewassignment.transactions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.contact.to.NameBean;
import messaging.viewassignment.CaseAssignmentReportEvent;
import messaging.viewassignment.CaseAssignmentReportResponseEvent;
import messaging.viewassignment.SuperviseeTO;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import pd.supervision.administercaseload.SuperviseeName;
import pd.supervision.viewassignment.CaseAssignmentReportItem;

/**
 * @author cc_rbhat
 *
 */
public class CaseAssignmentReportCommand implements ICommand {

	public CaseAssignmentReportCommand() {		
	}
	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		Iterator iterator = CaseAssignmentReportItem.findAll(event);
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FMT_1);
        StringBuffer sb = new StringBuffer();
        Map puAssignTransactionDateMap = new HashMap();
		Map resultMap = new HashMap();
		int noOfRecords = 0;
		for (;iterator.hasNext();) {			
			if (noOfRecords++ > 2000) {
				break;
			}
			CaseAssignmentReportItem item = (CaseAssignmentReportItem) iterator.next();
			ICaseAssignment caseAssignment = item.valueObject();
			
			if(item.getCaseAssignmentHistoryType().equals(CaseloadConstants.PROGRAM_UNIT_ASSIGNED)) {
				sb.setLength(0);
				sb.append(item.getDefendantId());
				sb.append("|");
				sb.append(item.getCriminalCaseId());
				sb.append("|");
				sb.append(item.getProgramUnitId());
				sb.append("|");
				sb.append(sdf.format(item.getProgramUnitAssignDate()));
				
				Date transactionDate = item.getHistoryTransactionDate();
				puAssignTransactionDateMap.put(sb.toString(), transactionDate);			
			} 
			String defendantId = caseAssignment.getDefendantId();
			SuperviseeTO supervisee = (SuperviseeTO) resultMap.get(defendantId);
			if (supervisee == null) {
				supervisee = new SuperviseeTO();
				supervisee.setDefendantId(defendantId);
				supervisee.getActiveCases().add(caseAssignment);
				resultMap.put(defendantId, supervisee);
			} else {
				supervisee.getActiveCases().add(caseAssignment);				
			}
		}
		
		CaseAssignmentReportResponseEvent responseEvent = new CaseAssignmentReportResponseEvent();
		if (noOfRecords > 2000) {
			responseEvent.setResultSetExceedMaxLimit(true);
			MessageUtil.postReply(responseEvent);
		} else {
			Set defendantIds = resultMap.keySet();
			Map superviseeNames = getSuperviseeNames(defendantIds);
						
			for (Iterator nameItr = superviseeNames.keySet().iterator(); nameItr.hasNext();) {
				//Update the results with supervisee names.
				String key = (String) nameItr.next();
				NameBean defendantName = (NameBean) superviseeNames.get(key);
				SuperviseeTO supervisee = (SuperviseeTO) resultMap.get(key);
				supervisee.setDefendantName(defendantName);
				
				//set transaction date of program unit assignment
				for (Iterator i = supervisee.getActiveCases().iterator(); i.hasNext();) {
					ICaseAssignment ca = (ICaseAssignment) i.next();
					if (ca.getOfficerAssignDate() != null) { //for records with history type OFFICER_ASSIGNED
						sb.setLength(0);
						sb.append(ca.getDefendantId());
						sb.append("|");
						sb.append(ca.getCriminalCaseId());
						sb.append("|");
						sb.append(ca.getProgramUnitId());
						sb.append("|");
						sb.append(sdf.format(ca.getProgramUnitAssignDate()));

						Date transactionDate = (Date) puAssignTransactionDateMap.get(sb.toString());
						if (transactionDate == null) {
							//Query case assign history to get the program unit assignment transaction date.
							CaseAssignmentReportEvent reqEvent = new CaseAssignmentReportEvent();
							reqEvent.setSearchType(CaseloadConstants.FILTER_BY_PU_TRANSACTION_DATE);
							reqEvent.setDefendantId(ca.getDefendantId());
							reqEvent.setCriminalCaseId(ca.getCriminalCaseId());
							List programUnits = new ArrayList();
							programUnits.add(ca.getProgramUnitId());
							reqEvent.setProgramUnitIds(programUnits);
							reqEvent.setAssignmentStartDate(ca.getProgramUnitAssignDate());
							
							Iterator results = CaseAssignmentReportItem.findAll(reqEvent);
							for (;results.hasNext();) {
								CaseAssignmentReportItem item = (CaseAssignmentReportItem) results.next();
								transactionDate = item.getHistoryTransactionDate();
							}
							//Also set in the hash map
							puAssignTransactionDateMap.put(sb.toString(), transactionDate);										
						}						
						ca.setProgramUnitAssignmentTranactionDate(transactionDate);
					}
				}
			}
			List results = new ArrayList(resultMap.values());
			responseEvent.setResults(results);
			responseEvent.setResultSetExceedMaxLimit(false);
		}
		MessageUtil.postReply(responseEvent);
	}
	
	private Map getSuperviseeNames(Set defendantIds) {
		Map superviseeNames = new HashMap();
		for (Iterator iterator = defendantIds.iterator(); iterator.hasNext();) {
			String defendantId = (String) iterator.next();
			SuperviseeName sname = SuperviseeName.findByDefendantId(defendantId);
			NameBean nameBean = new NameBean();
			if (sname != null){
				nameBean = new NameBean(sname.getFirstName(), sname.getMiddleName(), sname.getLastName());
			}
			superviseeNames.put(defendantId, nameBean);
		}		
		return superviseeNames;
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
	}

}
