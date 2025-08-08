/*
 * Created on Apr 26, 2007
 *
 */
package mapping.supervision;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import naming.PDConstants;

import messaging.cscdstaffposition.CreateWorkgroupUsersEvent;
import messaging.cscdstaffposition.GetCourtStaffPositionEvent;
import messaging.cscdstaffposition.GetCourtsStaffPositionEvent;
import messaging.cscdstaffposition.GetOrganizationPostionEvent;
import messaging.cscdstaffposition.GetStaffPositionsByUserIdEvent;
import messaging.cscdstaffposition.GetStaffPositionsEvent;
import messaging.cscdstaffposition.GetStaffPositionsWithoutWorkGroupEvent;
import messaging.cscdstaffposition.StaffPositionReportingEvent;
import messaging.cscdstaffposition.VerifyProbationOfficerIndAndCjadEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.CollectionUtil;

/**
 * @author dgibler
 *
 */
public class GetSupervisionStaffWhereClause {
	/**
	 * @param evt
	 * @return
	 */
	private static final String BLANK = "";
	private static final SimpleDateFormat dateFormatInMillis = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	/**
	 * @param event
	 * @return
	 */
	public String getStaffPositionsClause(IEvent event)
	{
	    GetStaffPositionsEvent evt = (GetStaffPositionsEvent) event;
	    StringBuffer buf = new StringBuffer();
	    buf.append("AGENCY_ID = '");
	    buf.append(evt.getAgencyId());
	    buf.append("'");
		Collection logonIds = evt.getLogonIds(); 
		if (logonIds != null && logonIds.size() > 0)
		{
			List aList = CollectionUtil.iteratorToList(logonIds.iterator());
			String logonId = null;
			if (aList.size() > 1){
				buf.append(" AND USERPROFILE_ID IN("); 
				boolean hasFirst = false;
				for (int i = 0; i < aList.size(); i++) {
					logonId = (String)aList.get(i);
					if ( hasFirst )
					{
						buf.append(","); 
					}
					buf.append("'");
					buf.append( logonId ); 
					buf.append("'");
					hasFirst = true;
				}
				buf.append(")");
			} else {
				logonId = (String) aList.get(ZERO);
				buf.append(" AND USERPROFILE_ID = '");
				buf.append(logonId);
				buf.append("'");
			}
		}

		String strVal = evt.getCjadNum();
		if (strVal != null && strVal.trim().length() > 0) {
		    strVal = strVal.replace('*', '%');
			buf.append(" AND CJADNUM LIKE '");
			buf.append(strVal);
			buf.append("'");
		}
		
		strVal = evt.getPositionName();
		if (strVal != null && strVal.trim().length() > 0) {
		    strVal = strVal.replace('*', '%');
			buf.append(" AND POSITIONNAME LIKE '");
			buf.append(strVal);
			buf.append("'");
		}
		strVal = evt.getWorkgroupName();
		if (strVal != null && strVal.trim().length() > 0) {
			strVal = strVal.replace('*', '%');
			buf.append(" AND NAME LIKE ");
			buf.append("'" + strVal + "'"); 
		}
		strVal = evt.getStaffPositionId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND STAFFPOSITION_ID = ");
			buf.append("'" + strVal + "'"); 
		} 
		
		strVal = evt.getDivisionId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND ORGANIZATION_ID = ");
			buf.append(strVal); 
		}		
		strVal = evt.getProgramUnitId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND UNIT_ID = ");
			buf.append(strVal); 
		}
		strVal = evt.getProgramSectionId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND SECTION_ID = ");
			buf.append(strVal); 
		}
		strVal = evt.getCstsOfficerTypeId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND CSTSOFFICERTYPE_ID = ");
			buf.append(strVal); 
		}
		strVal = evt.getStatusId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND STATUS_ID = ");
			buf.append(strVal); 
		}

		return buf.toString();
	}
	/**
	 * @param event
	 * @return
	 */

	public String getStaffPositionHistoryClause(IEvent event)
	{
	    StaffPositionReportingEvent evt = (StaffPositionReportingEvent) event;
	    StringBuffer buf = new StringBuffer();
	    buf.append("AGENCY_ID = '");
	    buf.append(evt.getAgencyId());
	    buf.append("'");
	    String strVal = evt.getPositionStatusId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append("AND STATUS_ID =");
			buf.append(strVal); 
		}
		if (evt.getBeginDate() != null && evt.getEndDate() != null) {
			buf.append(" AND EFFECTIVEDATE >= '");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(evt.getBeginDate());
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.clear(Calendar.MINUTE);
            calendar.clear(Calendar.SECOND);
            calendar.clear(Calendar.MILLISECOND);
			String dateStr = dateFormatInMillis.format(calendar.getTime());
			buf.append(dateStr);
			
			buf.append("' AND EFFECTIVEDATE <= '");
			calendar.setTime(evt.getEndDate());
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.clear(Calendar.MINUTE);
            calendar.clear(Calendar.SECOND);
            calendar.clear(Calendar.MILLISECOND);
			dateStr = dateFormatInMillis.format(calendar.getTime());
			buf.append(dateStr);
			buf.append("'");
		}
		strVal = evt.getProgramUnitId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND UNIT_ID = ");
			buf.append(strVal); 
		}
		strVal = evt.getStaffPositionId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND STAFFPOS_ID = ");
			buf.append(strVal); 
		}
		strVal = evt.getStaffLogonId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND USERPROFILE_ID = '");
			buf.append(strVal); 
			buf.append("'");
		}
		return buf.toString();
	}
	/**
	 * @param event
	 * @return
	 */
	public String getPoiAndCjadClause(IEvent event)
	{
	    VerifyProbationOfficerIndAndCjadEvent evt = (VerifyProbationOfficerIndAndCjadEvent) event;
	    StringBuffer buf = new StringBuffer();
	    buf.append("AGENCY_ID = '");
	    buf.append(evt.getAgencyId());
	    buf.append("'");
		String strVal = evt.getCjadNum();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND CJADNUM = '");
			buf.append(strVal);
			buf.append("'");
		}
		strVal = evt.getProbationOfficerInd();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND PROBOFFICERIND = '");
			buf.append(strVal);
			buf.append("'");
		}
		strVal = evt.getStaffPositionId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND STAFFPOSITION_ID <> ");
			buf.append(strVal);
		} 
		buf.append(" AND STATUS_ID = ");
		buf.append(evt.getStatusId());
		return buf.toString();
	}
	/**
	 * @param event
	 * @return
	 */
	private static final int ONE = 1;
	private static final int ZERO = 0;
	public String getOrganizationsPositionsClause(IEvent event)
	{
	    GetOrganizationPostionEvent evt = (GetOrganizationPostionEvent) event;
	    StringBuffer buf = new StringBuffer();
	    buf.append("AGENCY_ID = '");
	    buf.append(evt.getAgencyId());
	    buf.append("'");

	    if (evt.getDivisionId() != null && !evt.getDivisionId().equals(BLANK)){
	        buf.append(" AND PARENTORG_ID = ");
	        buf.append(evt.getDivisionId());
	    }
	    if (evt.getProgramUnitId() != null && !evt.getProgramUnitId().equals(BLANK)){
	        buf.append(" AND UNIT_ID = ");
	        buf.append(evt.getProgramUnitId());
	    }
	    if (evt.getProgramSectionId() != null && !evt.getProgramSectionId().equals(BLANK)){
	        buf.append(" AND SECTION_ID = ");
	        buf.append(evt.getProgramSectionId());
	    }
	    if (evt.getPositionTypes() != null && evt.getPositionTypes().size() > ZERO){
	    	List aList = CollectionUtil.iteratorToList(evt.getPositionTypes().iterator());
	    	if (aList.size() > ONE){
	    		buf.append(" AND POSITIONTYPE IN("); 
	    		boolean hasFirst = false;
	    		String positionTypeId = null;
	    		for (int i = ZERO; i < aList.size(); i++) {
	    			positionTypeId = (String) aList.get(i);
	    			if ( hasFirst )
	    			{
	    				buf.append(","); 
	    			}
	    			buf.append( positionTypeId ); 
	    			hasFirst = true;
	    		}
	    		buf.append(")");
	    	} else {
	    		buf.append(" AND POSITIONTYPE = "); 
	    		buf.append( (String) aList.get(ZERO));
	    	}
	    }
	    if (evt.getJobTitles() != null && evt.getJobTitles().size() > 0){
			List aList = CollectionUtil.iteratorToList(evt.getJobTitles().iterator());
			if (aList.size() > ONE){
				buf.append(" AND JOBTITLE_ID IN("); 
				boolean hasFirst = false;
				String jobTitleId = null;
				for (int i = 0; i < aList.size(); i++) {
	
					jobTitleId = (String) aList.get(i);
					if ( hasFirst )
					{
						buf.append(","); 
					}
					buf.append( jobTitleId ); 
					hasFirst = true;
				}
				buf.append(")");
			} else {
	    		buf.append(" AND JOBTITLE_ID = "); 
	    		buf.append( (String) aList.get(ZERO));
			}
	    }
	    if (evt.getStatusId() != null && !evt.getStatusId().equals(BLANK)){
	        buf.append(" AND STATUS_ID = ");
	        buf.append(evt.getStatusId());
	    }

		return buf.toString();
	}
	
	/**
	 * @param event
	 * @return
	 */
	public String getOrgStaffToCreateWorkGroup(IEvent event){
		CreateWorkgroupUsersEvent evt = (CreateWorkgroupUsersEvent) event;
		
		StringBuffer whereClause = new StringBuffer();
		whereClause.append("AGENCY_ID = '");
		whereClause.append(evt.getAgencyId());
		whereClause.append("'");
		
		String strVal = evt.getDivisionId();
		if (strVal != null && strVal.trim().length() > 0) {
			whereClause.append(" AND ORGANIZATION_ID = ");
			whereClause.append(strVal); 
		}		
		
		strVal = evt.getProgramUnitId();
		if (strVal != null && strVal.trim().length() > 0) {
			whereClause.append(" AND UNIT_ID = ");
			whereClause.append(strVal); 
		}		

		strVal = evt.getJobTitleId();
		if (strVal != null && strVal.trim().length() > 0) {
			whereClause.append(" AND JOBTITLE_ID = ");
			whereClause.append(strVal); 
		}		
		
		strVal = evt.getPositionTypeId();
		if (strVal != null && strVal.trim().length() > 0) {
			whereClause.append(" AND POSITIONTYPE = ");
			whereClause.append(strVal); 
		}		

		whereClause.append(" AND USERPROFILE_ID IS NOT NULL AND USERPROFILE_ID != ' ' ");

		return whereClause.toString();
	}
	
	/**
	 * Returns CourtStaffPosition objects for a given court and job titles.
	 * @param event
	 * @return
	 */
	public String getCourtStaffPositionClause(IEvent event){
	    GetCourtStaffPositionEvent evt = (GetCourtStaffPositionEvent) event;
		StringBuffer whereClause = new StringBuffer();

		if (evt.getAgencyId() != null && !evt.getAgencyId().equals(PDConstants.BLANK)){
			whereClause.append("AGENCY_ID = '");
			whereClause.append(evt.getAgencyId()); 
			whereClause.append("'");
		}
		if (evt.getCourtId() != null && !evt.getCourtId().equals(PDConstants.BLANK)){
			whereClause.append("AND COURT_ID = '");
			whereClause.append(evt.getCourtId()); 
			whereClause.append("'");
		}
		if (evt.getJobTitleId() != null && !evt.getJobTitleId().equals(PDConstants.BLANK)){
		    whereClause.append(" AND JOBTITLE_ID = ");
		    whereClause.append(evt.getJobTitleId());
		}
		if (evt.isReturnAssignedPositionsOnly()){
		    whereClause.append(" AND USERPROFILE_ID IS NOT NULL AND USERPROFILE_ID != ''");
		}
		if (evt.getStatusId() != null && !evt.getStatusId().equals(PDConstants.BLANK)){
		    whereClause.append(" AND STATUS_ID = ");
		    whereClause.append(evt.getStatusId());
		}
	    return whereClause.toString();
	}
	
	/**
	 * Returns CourtStaffPosition objects for given courtIds.
	 * @param event
	 * @return
	 */
	public String getCourtsStaffPositionClause(IEvent event){
	    
	    GetCourtsStaffPositionEvent evt = (GetCourtsStaffPositionEvent) event;
		StringBuffer whereClause = new StringBuffer();
		
		if (evt.getAgencyId() != null && !evt.getAgencyId().equals(PDConstants.BLANK)){
			whereClause.append("AGENCY_ID = '");
			whereClause.append(evt.getAgencyId()); 
			whereClause.append("'");
		}
		if (evt.getJobTitleId() != null){
		    whereClause.append(" AND JOBTITLE_ID = ");
		    whereClause.append(evt.getJobTitleId());
		}
		if (evt.getCourts() != null && evt.getCourts().size() > 0){
			List aList = CollectionUtil.iteratorToList(evt.getCourts().iterator());
			String courtId = null;
			if (aList.size() > 1){
				whereClause.append(" AND COURT_ID IN (");
		    	boolean hasFirst = false;
		    	for (int i = 0; i < aList.size(); i++) {
		    		courtId = (String) aList.get(i);
		    		if (hasFirst){
		    			whereClause.append(", ");
		    		} else {
		    			hasFirst = true;
		    		}
		    		whereClause.append("'");
		    		whereClause.append(courtId);
		    		whereClause.append("'");
		    	}
		    	whereClause.append(")");
			} else {
				whereClause.append(" AND COURT_ID ='");
				courtId = (String) aList.get(ZERO);
				whereClause.append(courtId);
				whereClause.append("'");
			}
		}
		if (evt.getStatusId() != null && !evt.getStatusId().equals(PDConstants.BLANK)){
		    whereClause.append(" AND STATUS_ID = ");
		    whereClause.append(evt.getStatusId());
		}
		if (evt.getStaffPositionId() != null && !evt.getStaffPositionId().equals(PDConstants.BLANK)){
		    whereClause.append((" AND STAFFPOSITION_ID <> "));
		    whereClause.append(evt.getStaffPositionId());
		}
		
		return whereClause.toString();
	}	
	/**
	 * Returns CourtStaffPosition objects for a given court and job titles.
	 * @param event
	 * @return
	 */
	public String getStaffPositionByUserIdsClause(IEvent event){
	    GetStaffPositionsByUserIdEvent evt = (GetStaffPositionsByUserIdEvent) event;
		StringBuffer whereClause = new StringBuffer();

		if (evt.getAgencyId() != null && !evt.getAgencyId().equals(PDConstants.BLANK)){
			whereClause.append("AGENCY_ID = '");
			whereClause.append(evt.getAgencyId()); 
			whereClause.append("'");
		}
	    if (evt.getLogonIds() != null && evt.getLogonIds().size() > 0){
			whereClause.append(" AND USERPROFILE_ID IN("); 
			boolean hasFirst = false;
			Iterator iter = evt.getLogonIds().iterator();
			String logonId = null;
			while ( iter.hasNext() )
			{
			    logonId = (String)iter.next();
				if ( hasFirst )
				{
				    whereClause.append(","); 
				}
				whereClause.append("'");
				whereClause.append( logonId ); 
				whereClause.append("'");
				hasFirst = true;
			}
			whereClause.append(")");
	    }

		if (evt.getStatusId() != null && !evt.getStatusId().equals(PDConstants.BLANK)){
		    whereClause.append(" AND STATUS_ID = ");
		    whereClause.append(evt.getStatusId());
		}
	    return whereClause.toString();
	}
	public String getStaffPositionsOnlyClause(IEvent event)
	{
	    GetStaffPositionsEvent evt = (GetStaffPositionsEvent) event;
	    StringBuffer buf = new StringBuffer();
	    boolean hasOne=false;
	    if(evt.getAgencyId()!=null && !evt.getAgencyId().trim().equals("")){
		    buf.append("AGENCY_ID = '");
		    buf.append(evt.getAgencyId());
		    buf.append("'");
		    hasOne=true;
	    }
		Collection logonIds = evt.getLogonIds(); 
		if (logonIds != null && logonIds.size() > 0)
		{
			if(hasOne){
				buf.append(" AND ");
			}
			hasOne=true;
			buf.append("USERPROFILE_ID IN("); 
			boolean hasFirst = false;
			Iterator iter = logonIds.iterator();
			String logonId = null;
			while ( iter.hasNext() )
			{
				logonId = (String)iter.next();
				if ( hasFirst )
				{
					buf.append(","); 
				}
				buf.append("'");
				buf.append( logonId ); 
				buf.append("'");
				hasFirst = true;
			}
			buf.append(")");
		}

		
		String strVal = evt.getPositionName();
		if (strVal != null && strVal.trim().length() > 0) {
		    strVal = strVal.replace('*', '%');
		    if(hasOne){
				buf.append(" AND ");
			}
		    hasOne=true;
			buf.append("POSITIONNAME LIKE '");
			buf.append(strVal);
			buf.append("'");
		}
		
		strVal = evt.getStaffPositionId();
		if (strVal != null && strVal.trim().length() > 0) {
			if(hasOne){
				buf.append(" AND ");
			}
			hasOne=true;
			buf.append("STAFFPOSITION_ID = ");
			buf.append("'" + strVal + "'"); 
		} else {
			if(hasOne){
				buf.append(" AND ");
			}
		    buf.append("STAFFPOSITION_ID IS NOT NULL");  
		}
		strVal = evt.getDivisionId();
		if (strVal != null && strVal.trim().length() > 0) {
			if(hasOne){
				buf.append(" AND ");
			}
			hasOne=true;
			buf.append("ORGANIZATION_ID = ");
			buf.append(strVal); 
		}		
		
		strVal = evt.getCstsOfficerTypeId();
		if (strVal != null && strVal.trim().length() > 0) {
			if(hasOne){
				buf.append(" AND ");
			}
			hasOne=true;
			buf.append("CSTSOFFICERTYPE_ID = ");
			buf.append(strVal); 
		}
		return buf.toString();
	}
	/**
	 * @param event
	 * @return
	 */
	public String getStaffPositionsWithoutWorkgroupClause(IEvent event)
	{
	    GetStaffPositionsWithoutWorkGroupEvent evt = (GetStaffPositionsWithoutWorkGroupEvent) event;
	    StringBuffer buf = new StringBuffer();
	    buf.append("AGENCY_ID = '");
	    buf.append(evt.getAgencyId());
	    buf.append("'");
		Collection logonIds = evt.getLogonIds(); 
		if (logonIds != null && logonIds.size() > 0)
		{
			buf.append(" AND USERPROFILE_ID IN("); 
			boolean hasFirst = false;
			Iterator iter = logonIds.iterator();
			String logonId = null;
			while ( iter.hasNext() )
			{
				logonId = (String)iter.next();
				if ( hasFirst )
				{
					buf.append(","); 
				}
				buf.append("'");
				buf.append( logonId ); 
				buf.append("'");
				hasFirst = true;
			}
			buf.append(")");
		}

		String strVal = evt.getCjadNum();
		if (strVal != null && strVal.trim().length() > 0) {
		    strVal = strVal.replace('*', '%');
			buf.append(" AND CJADNUM LIKE '");
			buf.append(strVal);
			buf.append("'");
		}
		
		strVal = evt.getPositionName();
		if (strVal != null && strVal.trim().length() > 0) {
		    strVal = strVal.replace('*', '%');
			buf.append(" AND POSITIONNAME LIKE '");
			buf.append(strVal);
			buf.append("'");
		}
		strVal = evt.getStaffPositionId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND STAFFPOSITION_ID = ");
			buf.append("'" + strVal + "'"); 
		} 
		
		strVal = evt.getDivisionId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND ORGANIZATION_ID = ");
			buf.append(strVal); 
		}		
		strVal = evt.getProgramUnitId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND UNIT_ID = ");
			buf.append(strVal); 
		}
		strVal = evt.getProgramSectionId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND SECTION_ID = ");
			buf.append(strVal); 
		}
		strVal = evt.getCstsOfficerTypeId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND CSTSOFFICERTYPE_ID = ");
			buf.append(strVal); 
		}
		strVal = evt.getStatusId();
		if (strVal != null && strVal.trim().length() > 0) {
			buf.append(" AND STATUS_ID = ");
			buf.append(strVal); 
		}

		return buf.toString();
	}
}
