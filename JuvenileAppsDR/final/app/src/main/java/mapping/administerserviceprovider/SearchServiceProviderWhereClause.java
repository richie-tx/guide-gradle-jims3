/*
 * Created on Sep 27, 2006
 *
 */
package mapping.administerserviceprovider;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.administerserviceprovider.GetProviderProgramsByCodeEvent;
import messaging.administerserviceprovider.GetProviderProgramsEvent;
import messaging.administerserviceprovider.GetProviderServicesEvent;
import messaging.administerserviceprovider.GetServiceProviderFromServiceIdEvent;
import messaging.administerserviceprovider.GetServiceProvidersEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author C_NAggarwal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchServiceProviderWhereClause {

//	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss.000000");	
	String datePattern = new String("yyyy-MM-dd-hh.mm.ss.000000");
	String datePatternStart = new String("yyyy-MM-dd HH:mm:ss:000");
	String datePatternEnd = new String("yyyy-MM-dd HH:mm:ss:999");

	public String getProviderProgramsClause( IEvent anEvent )
	{
		GetProviderProgramsEvent evt = (GetProviderProgramsEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		String strProgramName = this.escape(evt.getProgramName());
		String strServiceProviderId = evt.getServiceProviderId();
		String strStateProgramCode = evt.getStateProgramCode();
		String strTargetInterventionId = evt.getTargetInterventionId();
		String strProgramScheduleTypeId = evt.getProgramScheduleTypeId(); //added for 11099
		String strStatusId = evt.getStatusId(); 
		Date endDateFrom = evt.getEndDateFrom();
		Date endDateTo = evt.getEndDateTo();
		String agencyId = evt.getAgencyId();
/*		String endDateFromStr = dateFormat.format(endDateFrom);
		String endDateToStr = dateFormat.format(endDateTo);
*/		
/*		
		String endDateFromStr = DateUtil.dateToString(endDateFrom, datePattern);
		String endDateToStr = DateUtil.dateToString(endDateTo, datePattern);
*/		
		String formattedName="";
		if ( agencyId != null && agencyId.trim().length() > 0) {
			buf.append( "ORIGINDEPARTMENT LIKE '"); 
			buf.append(agencyId.toUpperCase()); 
			buf.append( "' " );			
		}
		if ( strProgramName != null && strProgramName.trim().length() > 0 && !strProgramName.equals("*"))
		{
			formattedName = this.formatField(strProgramName);
			buf.append( "AND UPPER(PROGRAMNAME) like '" ); 
			buf.append( formattedName.toUpperCase()); 
			buf.append( "'" );
			/*strProgramName = strProgramName.toUpperCase();
			buf.append( "AND UPPER(PROGRAMNAME) like '" ); 
			buf.append( strProgramName ); 
			buf.append( "%'" );*/
		}
		else if (strProgramName != null && strProgramName.equals("*")) {
			if (buf.toString().length() > 0)
			{
				buf.append( "AND PROGRAMNAME like '%'" );
			} else {
				buf.append( "PROGRAMNAME like '%'" );
			}
		}
		else {
			if (buf.toString().length() > 0)
			{
				buf.append( "AND PROGRAMNAME like '%'" );
			} else {
				buf.append( "PROGRAMNAME like '%'" );
			}
		}

		if ( buf != null ) {
			if ( strServiceProviderId != null && strServiceProviderId.trim().length() > 0 )
			{
				buf.append( " and JUVSERVPROV_ID = " ); 
				buf.append( strServiceProviderId ); 
			}
		}
		else {
			if ( strServiceProviderId != null && strServiceProviderId.trim().length() > 0 )
			{
				buf.append( " JUVSERVPROV_ID = " ); 
				buf.append("'" + strServiceProviderId + "'"); 
			}
		}
		
		if ( buf != null ) {
			if ( strStateProgramCode != null && strStateProgramCode.trim().length() > 0 )
			{
				buf.append( " and STATEPROGCD = " ); 
				buf.append( "'" + strStateProgramCode + "'" ); 
			}
		}
		else {
			if ( strStateProgramCode != null && strStateProgramCode.trim().length() > 0 )
			{
				buf.append( "STATEPROGCD = " ); 
				buf.append( "'" + strStateProgramCode + "'" ); 
			}
		}
		//added for 11099
		if ( buf != null ) {
			if ( strProgramScheduleTypeId != null && strProgramScheduleTypeId.trim().length() > 0 )
			{
				buf.append( " and PRGMSCHTYPECD = " ); 
				buf.append( "'" + strProgramScheduleTypeId + "'" ); 
			}
		}
		else { //added for 11099
			if ( strProgramScheduleTypeId != null && strProgramScheduleTypeId.trim().length() > 0 )
			{
				buf.append( " PRGMSCHTYPECD = " ); 
				buf.append( "'" + strProgramScheduleTypeId + "'" ); 
			}
		}//added for 11099
		

		if ( buf != null ) {
			if ( strTargetInterventionId != null && strTargetInterventionId.trim().length() > 0 )
			{
				buf.append( " and TARGETINTERVCD = " ); 
				buf.append( "'" + strTargetInterventionId + "'" ); 
			}
		}
		else {
			if ( strTargetInterventionId != null && strTargetInterventionId.trim().length() > 0 )
			{
				buf.append( " TARGETINTERVCD = " ); 
				buf.append( "'" + strTargetInterventionId + "'" ); 
			}
		}
		
		if ( buf != null ) {
			if ( strStatusId != null && strStatusId.trim().length() > 0 )
			{
				buf.append( " and PROVPROGSTATUSCD = " ); 
				buf.append("'" + strStatusId + "'"); 
			}
		}
		else {
			if ( strStatusId != null && strStatusId.trim().length() > 0 )
			{
				buf.append( " PROVPROGSTATUSCD = " ); 
				buf.append("'" + strStatusId + "'"); 
			}
		}

		if ( buf != null ) {

			if ( endDateFrom != null && endDateTo != null )
			{
				endDateFrom.setHours(00);
				endDateFrom.setMinutes(00);
				endDateFrom.setSeconds(00);
								
				endDateTo.setHours(23); 
				endDateTo.setMinutes(59);
				endDateTo.setSeconds(59);
				
				buf.append( " and ENDDATE between '" ); 
				buf.append( DateUtil.dateToString(endDateFrom, datePatternStart) ); 
				buf.append( "' and '" ); 
				buf.append( DateUtil.dateToString(endDateTo, datePatternEnd) );
				buf.append( "'" ); 
			}
		}
		else {
			if ( endDateFrom != null && endDateTo != null )
			{
				endDateFrom.setHours(00);
				endDateFrom.setMinutes(00);
				endDateFrom.setSeconds(00);
								
				endDateTo.setHours(23); 
				endDateTo.setMinutes(59);
				endDateTo.setSeconds(59);
				
				buf.append( " ENDDATE between '" ); 
				buf.append( DateUtil.dateToString(endDateFrom, datePatternStart) ); 
				buf.append( "' and '" ); 
				buf.append( DateUtil.dateToString(endDateTo, datePatternEnd) );
				buf.append( "'" ); 
			}			
		}
		
		return buf.toString();
	}
	//Add program code as search criteria ER JIMS200075756 -- Start
	public String getProviderProgramsByCodeClause( IEvent anEvent )
	{
		GetProviderProgramsByCodeEvent evt = (GetProviderProgramsByCodeEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		
		String agencyId = evt.getAgencyId();
		String strProgramCode = evt.getProgramCode();
	
		String formattedName="";
		if ( agencyId != null && agencyId.trim().length() > 0) {
			buf.append( "ORIGINDEPARTMENT LIKE '"); 
			buf.append(agencyId.toUpperCase()); 
			buf.append( "' " );			
		}
		if ( strProgramCode != null && strProgramCode.trim().length() > 0 && !strProgramCode.equals("*"))
		{
			formattedName = this.formatField(strProgramCode);
			buf.append( " AND UPPER(PROGRAMCODE) like '" ); 
			buf.append( formattedName.toUpperCase()); 
			buf.append( "'" );
			
		}
		else if (strProgramCode != null && strProgramCode.equals("*")) {
			if (buf.toString().length() > 0)
			{
				buf.append( "AND PROGRAMCODE like '%'" );
			} else {
				buf.append( "PROGRAMCODE like '%'" );
			}
		}
		else {
			if (buf.toString().length() > 0)
			{
				buf.append( "AND PROGRAMCODE like '%'" );
			} else {
				buf.append( "PROGRAMCODE like '%'" );
			}
		}
		return buf.toString();
	}
	//Add program code as search criteria ER JIMS200075756 -- End

	public String escape(String str){
		StringBuffer sb = new StringBuffer();				
		for (int i=0;i<str.length();i++){			 
			if (str.charAt(i)=='\''){
				sb.append("\'\'");
			}else{
				sb.append(str.charAt(i));
			}
		}			    
	    return sb.toString();
	}
/*	
	public String getServiceProvidersClause( IEvent anEvent )
	{
		GetServiceProvidersEvent evt = (GetServiceProvidersEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		String strServiceProviderName = evt.getServiceProviderName();
		String strStatusId = evt.getStatusId(); 
		String agencyId = evt.getAgencyId();
		int inHouse = 0;
		boolean blnAllServiceProviders = false;
		
		blnAllServiceProviders = evt.isAllServiceProviders();	
		
		if(evt.isInHouse()){
			inHouse = 1;
		}
		
		if ( agencyId != null && agencyId.trim().length() > 0) {
			buf.append( "ORIGINDEPARTMENT LIKE '"); 
			buf.append(agencyId.toUpperCase()); 
			buf.append( "'" );			
		}
		if ( strServiceProviderName != null && strServiceProviderName.trim().length() > 0 && !strServiceProviderName.equals("*")) {
			strServiceProviderName = strServiceProviderName.toUpperCase();
			buf.append( " AND UPPER(NAME) like '" ); 
			buf.append( strServiceProviderName ); 
			buf.append( "%'" );
		}
		else if (strServiceProviderName != null && strServiceProviderName.equals("*")) {
			buf.append( " AND NAME like '%'" );
		}
		else {
			buf.append( " AND NAME like '%'" ); 
		}

		if ( buf != null ) {
			if ( strStatusId != null && strStatusId.trim().length() > 0 )
			{
				buf.append( " and STATUSCD = " ); 
				buf.append("'" + strStatusId + "'"); 
			}
		}
		else {
			if ( strStatusId != null && strStatusId.trim().length() > 0 )
			{
				buf.append( " STATUSCD = " ); 
				buf.append("'" + strStatusId + "'"); 
			}
		}
		
	
		if(!blnAllServiceProviders) {
			if ( buf != null ) {
				buf.append( " and ISINHOUSE = " ); 
				buf.append( inHouse ); 
			}
			else {
				buf.append( " ISINHOUSE = " ); 
				buf.append( inHouse ); 
			}
		}
	
		
		return buf.toString();
	}
*/	
	
	public String getServiceProvidersClause( IEvent anEvent )
	{
		GetServiceProvidersEvent evt = (GetServiceProvidersEvent)anEvent;
		StringBuffer buf = new StringBuffer();
			 
		String agencyId = evt.getAgencyId();
				 				
		if ( agencyId != null && agencyId.trim().length() > 0) {
			buf.append( "ORIGINDEPARTMENT LIKE '"); 
			buf.append(agencyId.toUpperCase()); 
			buf.append( "'" );			
		}
		
		String strServiceProviderName = evt.getServiceProviderName();
		String formattedName="";
		if (strServiceProviderName != null && strServiceProviderName.trim().length() > 0){	
			formattedName = this.escape(this.formatField(strServiceProviderName));
			buf.append(generateAndClause(buf));
			buf.append( " UPPER(NAME) like '" ); 
			buf.append( formattedName.toUpperCase()); 
			buf.append( "'" );
		}
		


		String strStatusId = evt.getStatusId();
		if ( strStatusId != null && strStatusId.trim().length() > 0 )
		{
				buf.append(generateAndClause(buf));
				buf.append( "STATUSCD = " ); 
				buf.append("'" + strStatusId + "'"); 
		}
		
					
		if(evt.isAllServiceProviders()) {
			buf.append(generateAndClause(buf));
			buf.append( "(ISINHOUSE = 1 OR ISINHOUSE = 0)" );  			
		}else{
			buf.append(generateAndClause(buf));
			buf.append( "ISINHOUSE = " ); 
			buf.append( evt.isInHouse()? 1 : 0);						
		}
			
		return buf.toString();
	}
	
	public String generateAndClause(StringBuffer buf){
		String andClause = " ";
		if (buf!=null && buf.length()>0 ){
			 andClause = " AND ";
		}
		return andClause;
	}
	
	/**
	 * @param string
	 * @param formatString
	 * @return
	 */
	private String formatField(String value)
	{
		
		if (value.indexOf("*") >= 0)
		{
			value = value.replace('*', '%');
		}
		else
		{
			value = "%" + value + "%";
		}
		

		return value;
	}
	
	public String getServiceProvidersFromServiceIdClause(IEvent anEvent){
		GetServiceProviderFromServiceIdEvent evt = (GetServiceProviderFromServiceIdEvent)anEvent;
		Collection servIdList = evt.getServiceIdList();
		StringBuffer buf = new StringBuffer();
		String comma="";
		if (servIdList!=null){
			buf.append("SERVICE_ID IN ( ");
			Iterator iter = servIdList.iterator();
			while (iter.hasNext()){
				buf.append(comma);				
				buf.append((String)iter.next());				
				comma=",";
			}
			buf.append(")");
		}
		return buf.toString();
	}
	
	public String getProviderServicesClause( IEvent anEvent )
	{
		GetProviderServicesEvent evt = (GetProviderServicesEvent)anEvent;
		StringBuffer buf = new StringBuffer();

		String strServiceName = this.escape(evt.getServiceName());
		String strServiceTypeId = evt.getServiceTypeId();
		String strStatusId = evt.getStatusId(); 
		String agencyId = evt.getAgencyId();
		String formattedName="";
		if ( agencyId != null && agencyId.trim().length() > 0) {
			buf.append( "ORIGINDEPARTMENT LIKE '"); 
			buf.append(agencyId.toUpperCase()); 
			buf.append( "' " );			
		}
		if ( strServiceName != null && strServiceName.trim().length() > 0 && !strServiceName.equals("*")) {
			formattedName = this.formatField(strServiceName);
			buf.append( "AND UPPER(SERVICENAME) like '" ); 
			buf.append( formattedName.toUpperCase()); 
			buf.append( "'" );
		}
		else if ( strServiceName != null && strServiceName.equals("*")) {
			buf.append( "AND SERVICENAME like '%'" );
		}
		else {
			buf.append( "AND SERVICENAME like '%'" ); 
		}
		
		if ( buf != null ) {
			if ( strServiceTypeId != null && strServiceTypeId.trim().length() > 0 )
			{
				buf.append( " and SERVICETYPECD = " ); 
				buf.append("'" + strServiceTypeId + "'"); 
			}
		}
		else {
			if ( strServiceTypeId != null && strServiceTypeId.trim().length() > 0 )
			{
				buf.append( " SERVICETYPECD = " ); 
				buf.append("'" + strServiceTypeId + "'"); 
			}
		}

		if ( buf != null ) {
			if ( strStatusId != null && strStatusId.trim().length() > 0 )
			{
				buf.append( " and SERVICESTATUSCD = " ); 
				buf.append("'" + strStatusId + "'"); 
			}
		}
		else {
			if ( strStatusId != null && strStatusId.trim().length() > 0 )
			{
				buf.append( " SERVICESTATUSCD = " ); 
				buf.append("'" + strStatusId + "'"); 
			}
		}
		
		return buf.toString();
	}	
}
