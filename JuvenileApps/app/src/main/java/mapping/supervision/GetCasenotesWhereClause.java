package mapping.supervision;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import messaging.administercasenotes.GetCasenotesByCollateralEvent;
import messaging.administercasenotes.GetCasenotesBySubjectEvent;
import messaging.administercasenotes.GetCasenotesBySupervisionOrderValuesEvent;
import messaging.administercasenotes.GetCasenotesEvent;
import messaging.spnsplit.GetOrderCaseNotesEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author jmcnabb
 * 
 * Responsible for building the 'where' clause for Casenote searches. This class
 * will only work with the JDBC mapping adapter.
 */
public class GetCasenotesWhereClause {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-dd-MM");

	private static String yyyyMMdd = "yyyy-MM-dd";

	private StringBuffer getStandardBeginningClause(String superviseeId,
			String supervisionPeriodId) {

		StringBuffer buf = new StringBuffer();
		// Supervisee
		String strVal = superviseeId;
		if (strVal != null && strVal.trim().length() > 0) {
		    StringBuffer paddedSpn = new StringBuffer(strVal);
		    while (paddedSpn.length() < 8){
		        paddedSpn.insert(0,"0");
		    }
			buf.append("DEFENDANT_ID = '");
			buf.append(paddedSpn.toString());
			buf.append("'");
		}

		// Supervision Period
		strVal = supervisionPeriodId;
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND SPRVSNPERIOD_ID = ");
			buf.append(strVal);
		}
		return buf;
	}

	/**
	 * Form whereclause for retrieving casenotes for a list of orders
	 * @param anEvent
	 * @return
	 */
	public String getCasenotesByOrdersClause(IEvent anEvent)
	{
		GetOrderCaseNotesEvent order_case_notes_event = (GetOrderCaseNotesEvent)anEvent;

		StringBuffer where_buffer = new StringBuffer();
		List<String> order_ids = order_case_notes_event.getOrderIds();
		int num_orders = order_ids.size();
		
		where_buffer.append(" SPRVISIONORDER_ID in ( ");
		for (int i=0;i<num_orders;i++)
		{
			where_buffer.append(order_ids.get(i));
			
			if (i<num_orders-1)
				where_buffer.append(", ");
		}
		
		where_buffer.append(" )");
		return where_buffer.toString();
	}//end of getCasenotesByOrdersClause()
	
	public String getCasenotesBySubjectClause(IEvent anEvent) {

		GetCasenotesBySubjectEvent evt = (GetCasenotesBySubjectEvent) anEvent;

		StringBuffer buf = this.getStandardBeginningClause(evt
				.getSuperviseeId(), evt.getSupervisionPeriodId());

		Collection subjects = evt.getSubjects();
		if (subjects != null && subjects.size() > 0) {
			buf.append(" AND SUBJECTCD_ID IN(");
			boolean hasFirst = false;
			Iterator iter = subjects.iterator();
			while (iter.hasNext()) {
				String subjectId = (String) iter.next();
				if (hasFirst) {
					buf.append(", ");
				}
				buf.append(subjectId);
				hasFirst = true;
			}
			buf.append(")");
		}

		return buf.toString();
	}
	public String getCasenotesByAssociateClause(IEvent anEvent) {

		GetCasenotesByCollateralEvent evt = (GetCasenotesByCollateralEvent) anEvent;

//		11/26/07 dag - Cannot use defendant_id in where clause when using this view.		
//		StringBuffer buf = this.getStandardBeginningClause(evt
//				.getSuperviseeId(), evt.getSupervisionPeriodId());
		StringBuffer buf = new StringBuffer();
		// Supervisee
		String strVal = evt.getSuperviseeId();
		if (strVal != null && strVal.trim().length() > 0) {
		    StringBuffer paddedSpn = new StringBuffer(strVal);
		    while (paddedSpn.length() < 8){
		        paddedSpn.insert(0,"0");
		    }
			buf.append("SUPERVISEE_ID = '");
			buf.append(paddedSpn.toString());
			buf.append("'");
		}

		// Supervision Period
		strVal = evt.getSupervisionPeriodId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND SPRVSNPERIOD_ID = ");
			buf.append(strVal);
		}
		 
		Collection associates = evt.getAssociateIds();
		if (associates != null && associates.size() > 0) {
			buf.append(" AND ASSOCIATE_ID IN(");
			boolean hasFirst = false;
			Iterator iter = associates.iterator();
			while (iter.hasNext()) {
				String associateId = (String) iter.next();
				if (hasFirst) {
					buf.append(", ");
				}
				buf.append(associateId);
				hasFirst = true;
			}
			buf.append(")");
		}

		return buf.toString();
	}

	public String getCasenotesBySupervisionOrderValuesClause(IEvent anEvent) {

		GetCasenotesBySupervisionOrderValuesEvent evt = (GetCasenotesBySupervisionOrderValuesEvent) anEvent;

		StringBuffer buf = new StringBuffer();
		// Supervisee
		String strVal = evt.getSuperviseeId();
//		11/26/07 dag - Cannot use defendant_id in where clause when using this view.		
		
		if (strVal != null && strVal.trim().length() > 0) {
		    StringBuffer paddedSpn = new StringBuffer(strVal);
		    while (paddedSpn.length() < 8){
		        paddedSpn.insert(0,"0");
		    }
			buf.append("SUPERVISEE_ID = '");
			buf.append(paddedSpn.toString());
			buf.append("'");
		}

		// Supervision Period
		strVal = evt.getSupervisionPeriodId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND SPRVSNPERIOD_ID = ");
			buf.append(strVal);
		}

		strVal = evt.getCourtNum();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND COURT_ID = '");
			buf.append(strVal);
			buf.append("'");
		}
		Collection caseIds = evt.getCaseIds();
		if (caseIds != null && caseIds.size() > 0) {
			buf.append(" AND CRIMINALCASE_ID IN('");
			boolean hasFirst = false;
			Iterator iter = caseIds.iterator();
			while (iter.hasNext()) {
				String caseId = (String) iter.next();
				if (hasFirst) {
					buf.append("', '");
				}
				buf.append(caseId);
				hasFirst = true;
			}
			buf.append("')");
		}
		if (evt.getBeginDate() != null && evt.getEndDate() != null) {
			buf.append(" AND ENTRYDATE BETWEEN '");
			buf.append(DateUtil.dateToString(evt.getBeginDate(), yyyyMMdd));
			buf.append("-00.00.00.000000");
			buf.append("' AND '");
			buf.append(DateUtil.dateToString(evt.getEndDate(), yyyyMMdd));
			buf.append("-23.59.59.999999");
			buf.append("'");
		}
		strVal = evt.getCollateralId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND COLLATERALCD = '");
			buf.append(strVal);
			buf.append("'");
		}
		return buf.toString();
	}

	public String getCasenotesClause(IEvent anEvent) {

		GetCasenotesEvent evt = (GetCasenotesEvent) anEvent;

		StringBuffer buf = this.getStandardBeginningClause(evt
				.getSuperviseeId(), evt.getSupervisionPeriodId());

		String strVal = evt.getHowGeneratedId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND HOWGENERATEDCD = '");
			buf.append(strVal);
			buf.append("'");
		}
		strVal = evt.getCasenoteTypeId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND CASENOTETYPECD = '");
			buf.append(strVal);
			buf.append("'");
		}
		strVal = evt.getCourtNum();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND COURT_ID = '");
			buf.append(strVal);
			buf.append("'");
		}
		Collection caseIds = evt.getCaseIds();
		if (caseIds != null && caseIds.size() > 0) {
			buf.append(" AND CRIMINALCASE_ID IN('");
			boolean hasFirst = false;
			Iterator iter = caseIds.iterator();
			while (iter.hasNext()) {
				String caseId = (String) iter.next();
				if (hasFirst) {
					buf.append("', '");
				}
				buf.append(caseId);
				hasFirst = true;
			}
			buf.append("')");
		}
		if (evt.getBeginDate() != null && evt.getEndDate() != null) {
			buf.append(" AND ENTRYDATE BETWEEN '");
			buf.append(DateUtil.dateToString(evt.getBeginDate(), yyyyMMdd));
			buf.append("-00.00.00.000000");
			buf.append("' AND '");
			buf.append(DateUtil.dateToString(evt.getEndDate(), yyyyMMdd));
			buf.append("-23.59.59.999999");
			buf.append("'");

		}

		return buf.toString();
	}

	public String getCasenotesForAdvancedSearchClause(IEvent anEvent) {
		GetCasenotesEvent evt = (GetCasenotesEvent) anEvent;

//		11/26/07 dag - Cannot use defendant_id in where clause when using this view.		
		//StringBuffer buf = this.getStandardBeginningClause(evt.getSuperviseeId(), evt.getSupervisionPeriodId());
		StringBuffer buf = new StringBuffer();
		// Supervisee
		String strVal = evt.getSuperviseeId();
		if (strVal != null && strVal.trim().length() > 0) {
		    StringBuffer paddedSpn = new StringBuffer(strVal);
		    while (paddedSpn.length() < 8){
		        paddedSpn.insert(0,"0");
		    }
			buf.append("SUPERVISEE_ID = '");
			buf.append(paddedSpn.toString());
			buf.append("'");
		}

		// Supervision Period
		strVal = evt.getSupervisionPeriodId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND SPRVSNPERIOD_ID = ");
			buf.append(strVal);
		}
		
		strVal = evt.getHowGeneratedId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND HOWGENERATEDCD = '");
			buf.append(strVal);
			buf.append("'");
		}
		strVal = evt.getCasenoteTypeId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND CASENOTETYPECD = '");
			buf.append(strVal);
			buf.append("'");
		}
		Collection caseIds = evt.getCaseIds();
		if (caseIds != null && caseIds.size() > 0) {
			buf.append(" AND CRIMINALCASE_ID IN('");
			boolean hasFirst = false;
			Iterator iter = caseIds.iterator();
			while (iter.hasNext()) {
				String caseId = (String) iter.next();
				if (hasFirst) {
					buf.append("', '");
				}
				buf.append(caseId);
				hasFirst = true;
			}
			buf.append("')");
		}
		strVal = evt.getCourtNum();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND COURT_ID = '");
			buf.append(strVal);
			buf.append("'");
		}
		if (evt.getBeginDate() != null && evt.getEndDate() != null) {
			buf.append(" AND ENTRYDATE BETWEEN '");
			buf.append(DateUtil.dateToString(evt.getBeginDate(), yyyyMMdd));
			buf.append("-00.00.00.000000");
			buf.append("' AND '");
			buf.append(DateUtil.dateToString(evt.getEndDate(), yyyyMMdd));
			buf.append("-23.59.59.999999");
			buf.append("'");
		}
		Collection subjects = evt.getSubjects();
		if (subjects != null && subjects.size() > 0) {
			buf.append(" AND SUBJECTCD_ID IN(");
			boolean hasFirst = false;
			Iterator iter = subjects.iterator();
			while (iter.hasNext()) {
				String subjectId = (String) iter.next();
				if (hasFirst) {
					buf.append(", ");
				}
				buf.append(subjectId);
				hasFirst = true;
			}
			buf.append(")");
		}
		Collection associates = evt.getAssociateIds();
		if (associates != null && associates.size() > 0) {
			buf.append(" AND ASSOCIATE_ID IN(");
			boolean hasFirst = false;
			Iterator iter = associates.iterator();
			while (iter.hasNext()) {
				String associateId = (String) iter.next();
				if (hasFirst) {
					buf.append(", ");
				}
				buf.append(associateId);
				hasFirst = true;
			}
			buf.append(")");
		}
		strVal = evt.getCollateralId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND COLLATERALCD = '");
			buf.append(strVal);
			buf.append("'");
		}

		return buf.toString();
	}
}
