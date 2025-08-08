/*
 * Created on Oct 06, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.administerserviceprovider.administercontract;

import java.util.Date;

import messaging.administercontract.GetContractsEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetContractsEventWhereClause {

	String datePattern = new String("yyyy-MM-dd HH:mm:ss:SSS");

	/**
	 * @param event
	 * @return sql
	 */
    public String getContractsEventWhereClause( IEvent event )
	{
		GetContractsEvent cEvent = (GetContractsEvent) event;
		String contractName = cEvent.getContractName();
		String contractTypeId = cEvent.getContractTypeId();
		String fundingProgranDescription = cEvent.getFundingProgranDescription();
		Date fromDate = cEvent.getFromDate();
		Date toDate = cEvent.getToDate();
		String number = cEvent.getNumber();
		String agencyId = cEvent.getAgencyId();
		String contractId = cEvent.getContractId();
		boolean isExpired = cEvent.isExpired();
		
		return this.buildSql(contractName,contractTypeId,fundingProgranDescription,fromDate,toDate,number,agencyId,contractId,isExpired);
	}
	
	/**
	 * @param contractName
	 * @param contractTypeId
	 * @param fundingProgranDescription
	 * @param fromDate
	 * @param toDate
	 * @param number
	 * @param agencyId
	 * @param contractId
	 * @param isExpired
	 * @return
	 */
	private String buildSql(String contractName, String contractTypeId, String fundingProgranDescription, Date fromDate, Date toDate, String number, String agencyId, String contractId, boolean isExpired) {
		StringBuffer sql = new StringBuffer();
		String formattedName="";
		if(contractId != null && !contractId.trim().equals("")){
			sql.append(" CONTRACT_ID = "); 
			sql.append(contractId); 
			sql.append( "" );
		}else{
			if(contractName != null && !contractName.trim().equals("") && !contractName.trim().equals("*"))
			{
				formattedName = this.escape(this.formatField(contractName));
				sql.append("CONTRACTNAME LIKE '"); 
				sql.append(formattedName.toUpperCase()); 
				sql.append( "'" );
			}else if(contractName != null && !contractName.trim().equals("") && contractName.trim().equals("*")){
				sql.append("CONTRACTNAME LIKE '%'"); 
			}else{
				sql.append("CONTRACTNAME LIKE '%'"); 
			}

			if(contractTypeId != null && !contractTypeId.trim().equals(""))
			{
				sql.append(" AND CONTRACTTYPECD = '"); 
				sql.append(contractTypeId.toUpperCase()); 
				sql.append( "'" );
			}
	
			if (fundingProgranDescription != null && !fundingProgranDescription.trim().equals(""))
			{
				sql.append(" AND PROGFUNDINGDESC LIKE '"); 
				sql.append(fundingProgranDescription.toUpperCase()); 
				sql.append( "%'" );
			}
	
			if (fromDate != null)
			{
				sql.append( " AND STARTDATE >= '" ); 
				sql.append(DateUtil.dateToString(fromDate, datePattern)); 
				sql.append( "'");
			}
			
			if (toDate != null)
			{
				sql.append( " AND STARTDATE <= '" ); 
				sql.append(DateUtil.dateToString(toDate, datePattern) ); 
				sql.append( "'");
			}
			
			if (number != null && !number.trim().equals("") )
			{
				sql.append(" AND CAST(CONTRACTNUM as char(30)) LIKE '"); 
				sql.append(number.toUpperCase()); 
				sql.append( "%'" );
			}
			
			if (agencyId != null && !agencyId.trim().equals(""))
			{
				sql.append(" AND AGENCYCD LIKE '"); 
				sql.append(agencyId.toUpperCase()); 
				sql.append( "'" );
			}
			
			if (!isExpired)
			{
				sql.append(" AND (ENDDATE IS NULL ");
				sql.append(" OR ENDDATE > '"); 
				sql.append(DateUtil.dateToString(DateUtil.getCurrentDate(), datePattern)); 
				sql.append( "')" );
			}
		}
		return sql.toString();		
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
			value = value + "%";
		}
		

		return value;
	}
	
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
}
