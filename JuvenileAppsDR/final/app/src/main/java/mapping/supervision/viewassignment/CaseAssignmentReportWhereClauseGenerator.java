/*
 * Created on Jan 17, 2008
 *
 */
package mapping.supervision.viewassignment;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.viewassignment.CaseAssignmentReportEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.CaseloadConstants;

/**
 * @author cc_rbhat
 *  
 */
public class CaseAssignmentReportWhereClauseGenerator {
	public String getWhereClause(IEvent event) {
		CaseAssignmentReportEvent requestEvent = (CaseAssignmentReportEvent) event;
		String filterType = requestEvent.getSearchType();
		String whereClause = null;
		if (filterType.equals(CaseloadConstants.FILTER_BY_PROGRAMUNIT)) {
			whereClause = prepareProgramUnitFilter(requestEvent);
		} else if (filterType.equals(CaseloadConstants.FILTER_BY_USER)) {
			whereClause = prepareUserFilter(requestEvent);
		} else if (filterType.equals(CaseloadConstants.FILTER_BY_WORKGROUP)) {
			whereClause = prepareWorkgroupFilter(requestEvent);
		} else if (filterType.equals(CaseloadConstants.FILTER_BY_DEFENDANT)) {
			whereClause = prepareDefendantFilter(requestEvent);
		} else if (filterType.equals(CaseloadConstants.FILTER_BY_CASE)) {
			whereClause = prepareCaseFilter(requestEvent);
		} else if (filterType.equals(CaseloadConstants.FILTER_BY_PU_TRANSACTION_DATE)) {
			whereClause = getPUTransactionDateWhereClause(requestEvent);
		}
		return whereClause;
	}
	
	private String prepareProgramUnitFilter(CaseAssignmentReportEvent requestEvent) {
		Date startDate = requestEvent.getAssignmentStartDate();
		Date endDate = requestEvent.getAssignmentEndDate();
		List organizationIds = requestEvent.getProgramUnitIds();
		
		StringBuffer sb = new StringBuffer();
		String condition1 = "";
		if (startDate != null && endDate != null) {			
			String startDateStr = DateUtil.dateToString(startDate, "yyyy-MM-dd") + "-00.00.00.000000";
			String endDateStr = DateUtil.dateToString(endDate, "yyyy-MM-dd") + "-23.59.59.999999";
			sb.append(" PROGUNITASSGNDATE >= '" + startDateStr + "'");
			sb.append(" AND PROGUNITASSGNDATE <= '" + endDateStr + "'");
			condition1 = sb.toString();			
		}		
		String condition2 = "";
		if (organizationIds != null && organizationIds.size() > 0) {
			sb.setLength(0);
			sb.append(" ORGANIZATION_ID IN (");
			for (Iterator iterator = organizationIds.iterator(); iterator.hasNext();) {
				String id = (String) iterator.next();
				sb.append(id + ", ");
			}
			condition2 = sb.substring(0, sb.lastIndexOf(",")) + ")";			
		}			
		String whereClause = condition2;		   
		if (whereClause.length() > 0){   
		   if (condition1.length() > 0) {
			whereClause += " AND " + condition1;			
		   }
		   else {
			   whereClause = condition2;
		   }
		}
		return whereClause;		
	}

	private String prepareUserFilter(CaseAssignmentReportEvent requestEvent) {
		Date startDate = requestEvent.getAssignmentStartDate();
		Date endDate = requestEvent.getAssignmentEndDate();
		List organizationIds = requestEvent.getProgramUnitIds();
		String userId = requestEvent.getAcknowledgeUserId().toUpperCase();
		
		StringBuffer sb = new StringBuffer();
		String condition1 = "";
		if (startDate != null && endDate != null) {			
			String startDateStr = DateUtil.dateToString(startDate, "yyyy-MM-dd") + "-00.00.00.000000";
			String endDateStr = DateUtil.dateToString(endDate, "yyyy-MM-dd") + "-23.59.59.999999";
			sb.append(" PROGUNITASSGNDATE >= '" + startDateStr + "'");
			sb.append(" AND PROGUNITASSGNDATE <= '" + endDateStr + "'");
			condition1 = sb.toString();						
		}		
		String condition2 = "";
		if (organizationIds != null && organizationIds.size() > 0) {
			sb.setLength(0);
			sb.append(" ORGANIZATION_ID IN (");
			for (Iterator iterator = organizationIds.iterator(); iterator.hasNext();) {
				String id = (String) iterator.next();
				sb.append(id + ", ");
			}
			condition2 = sb.substring(0, sb.lastIndexOf(",")) + ")";			
		}
		String condition3 = "";
		if (userId != null) {
			sb.setLength(0);
			sb.append(" PAPERFILERECUSR_ID = '"); 
			sb.append(userId);
			sb.append("'");
			condition3 = sb.toString();
		}
		
		String whereClause = condition3;		
		if (condition1.length() > 0 && condition2.length() > 0) {
		    whereClause += " AND " + condition1 + " AND " + condition2;
		}      
		if (condition1.length() > 0 && condition2.length() == 0) {
		    whereClause += " AND " + condition1;
		}
		if (condition1.length() == 0 && condition2.length() > 0) {
		    whereClause += " AND " + condition2;
		}				
		return whereClause;		
	}

	private String prepareWorkgroupFilter(CaseAssignmentReportEvent requestEvent) {
		Date startDate = requestEvent.getAssignmentStartDate();
		Date endDate = requestEvent.getAssignmentEndDate();
		List organizationIds = requestEvent.getProgramUnitIds();
		String workgroupId = requestEvent.getWorkgroupId();
		
		StringBuffer sb = new StringBuffer();
		String condition1 = "";
		if (startDate != null && endDate != null) {			
			String startDateStr = DateUtil.dateToString(startDate, "yyyy-MM-dd") + "-00.00.00.000000";
			String endDateStr = DateUtil.dateToString(endDate, "yyyy-MM-dd") + "-23.59.59.999999";
			sb.append(" PROGUNITASSGNDATE >= '" + startDateStr + "'");
			sb.append(" AND PROGUNITASSGNDATE <= '" + endDateStr + "'");
			condition1 = sb.toString();			
		}		
		String condition2 = "";
		if (organizationIds != null && organizationIds.size() > 0) {
			sb.setLength(0);
			sb.append(" ORGANIZATION_ID IN (");
			for (Iterator iterator = organizationIds.iterator(); iterator.hasNext();) {
				String id = (String) iterator.next();
				sb.append(id + ", ");
			}
			condition2 = sb.substring(0, sb.lastIndexOf(",")) + ")";		
		}
		String condition3 = "";
		if (workgroupId != null) {
			sb.setLength(0);
			sb.append(" PAPERFILERECUSR_ID IN ( SELECT USER_ID FROM JIMS2.CSWORKGROUPUSER WHERE WORKGROUP_ID = ");
			sb.append(workgroupId);
			sb.append(")");
			condition3 = sb.toString();
		}
		String whereClause = condition3;
		if (condition1.length() > 0 && condition2.length() > 0) {
		    whereClause += " AND " + condition1 + " AND " + condition2;
		}      
		if (condition1.length() > 0 && condition2.length() == 0) {
		    whereClause += " AND " + condition1;
		}
		if (condition1.length() == 0 && condition2.length() > 0) {
		    whereClause += " AND " + condition2;
		}		
		return whereClause;		
	}

	private String prepareDefendantFilter(CaseAssignmentReportEvent requestEvent) {
		Date startDate = requestEvent.getAssignmentStartDate();
		Date endDate = requestEvent.getAssignmentEndDate();
		String defendantId = requestEvent.getDefendantId();
		
		StringBuffer sb = new StringBuffer();
		String condition1 = "";
		if (startDate != null && endDate != null) {			
			String startDateStr = DateUtil.dateToString(startDate, "yyyy-MM-dd") + "-00.00.00.000000";
			String endDateStr = DateUtil.dateToString(endDate, "yyyy-MM-dd") + "-23.59.59.999999";
			sb.append(" PROGUNITASSGNDATE >= '" + startDateStr + "'");
			sb.append(" AND PROGUNITASSGNDATE <= '" + endDateStr + "'");
			condition1 = sb.toString();			
		}		
		String condition2 = "";
		if (defendantId != null && defendantId.length() > 0) {
			StringBuffer defSb = new StringBuffer(defendantId);
			while (defSb.length() < 8){
				defSb.insert(0, 0);
			}
			defendantId = defSb.toString();
			sb.setLength(0);
			sb.append(" DEFENDANT_ID = '");
			sb.append(defendantId);
			sb.append("'");
			condition2 = sb.toString();
		}
		String whereClause = condition2;
		if (condition1.length() > 0) {
			whereClause += " AND " + condition1;
		}
		return whereClause;		
	}

	private String prepareCaseFilter(CaseAssignmentReportEvent requestEvent) {
		String courtDivisionIndicator = requestEvent.getCourtDivisionIndicator();
		String criminalCaseId = requestEvent.getCriminalCaseId();	
		StringBuffer sb = new StringBuffer();
		String condition1 = "";
		if (criminalCaseId != null) {
			sb.setLength(0);
			sb.append(" CRIMINALCASE_ID = '");
			sb.append(courtDivisionIndicator + criminalCaseId);
			sb.append("'");
			condition1 = sb.toString();
		}
		String whereClause = condition1;
		return whereClause;		
	}

	private String getPUTransactionDateWhereClause(IEvent event) {
		CaseAssignmentReportEvent requestEvent = (CaseAssignmentReportEvent) event;
		List organizationIds = requestEvent.getProgramUnitIds();
		String defendantId = requestEvent.getDefendantId();
		StringBuffer defSb = new StringBuffer(defendantId);
		while (defSb.length() < 8){
			defSb.insert(0, 0);
		}
		defendantId = defSb.toString();

		String criminalCaseId = requestEvent.getCriminalCaseId();	
		Date programUnitAssignmentDate = requestEvent.getAssignmentStartDate();

		StringBuffer sb = new StringBuffer();
		//program unit
		String condition1 = "";
		sb.append(" ORGANIZATION_ID IN (");
		for (Iterator iterator = organizationIds.iterator(); iterator.hasNext();) {
			String id = (String) iterator.next();
			sb.append(id + ", ");
		}
		condition1 = sb.substring(0, sb.lastIndexOf(",")) + ")";	
		
		sb.setLength(0);
		sb.append(condition1 + " AND");
		
		sb.append(" DEFENDANT_ID = '");
		sb.append(defendantId);
		sb.append("' AND");
		sb.append(" CRIMINALCASE_ID = '");
		sb.append(criminalCaseId);
		sb.append("' AND");
		sb.append(" PROGUNITASSGNDATE >= '");
		sb.append(DateUtil.dateToString(programUnitAssignmentDate, "yyyy-MM-dd") + "-00.00.00.000000" + "' AND");
		sb.append(" PROGUNITASSGNDATE <= '");
		sb.append(DateUtil.dateToString(programUnitAssignmentDate, "yyyy-MM-dd") + "-23.59.59.999999" + "' AND");
		sb.append(" TYPE = 'PROGRAM_UNIT_ASSIGNED' ");
	
		return sb.toString();
	}

}
