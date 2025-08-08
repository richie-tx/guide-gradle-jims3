/*
 * Created on Apr 22, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.administerprogramreferrals;

import java.util.List;

import naming.CSAdministerProgramReferralsConstants;

import messaging.administerprogramreferrals.FilterProgramReferralsEvent;
import messaging.administerprogramreferrals.GetProgRefByCaseloadEvent;
import messaging.administerprogramreferrals.InitNOpenRefsForRefTypesEvent;
import mojo.km.messaging.IEvent;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSProgramReferralWhereClause 
{

    /**
     * Tests the given string to see if it is null or the empty string
     * NOTE: Temporarily placed here to avoid partition break issue
     * @param testString
     * @return
     */
    private boolean isEmpty(String testString)
    {
        if ((testString != null) && (!testString.equals("")))
            return false;
        else
            return true;
    }//end of isEmpty()
    
	
	/**
	 * Generate where clause for filtering a set of program referrals
	 * @param anEvent
	 * @return
	 */
    public String filterProgramReferralsClause( IEvent anEvent )
	{
    	FilterProgramReferralsEvent filter_referrals_event = 
            							(FilterProgramReferralsEvent)anEvent;
        StringBuffer where_buffer = new StringBuffer();

    		//add defendant ID to where clause
        if (!isEmpty(filter_referrals_event.getDefendantId()))
        {
            where_buffer.append(" DEFENDANT_ID = '").
            	append(filter_referrals_event.getDefendantId()).append("'");
        }

        	//add criminal case ID to where clause
        if (!isEmpty(filter_referrals_event.getDefendantId()))
        {
            where_buffer.append(" AND CRIMINALCASE_ID = '").
            	append(filter_referrals_event.getCriminalCaseId()).append("'");
        }
        
        	//add status codes filter to where clause
        List filter_status_codes = filter_referrals_event.getReferralStatusCodes();
        int num_status_codes = filter_status_codes.size();
        
        	//begin status code where clause if any codes specified
        if (num_status_codes > 0)
        	where_buffer.append(" AND ( ");
        for (int i=0;i<num_status_codes;i++)
        {
        	where_buffer.append(" REFERRALSTATUSCD = '" + filter_status_codes.get(i) + "' ");
        	
        	if (i<num_status_codes-1)
        	{
        		where_buffer.append(" OR ");
        	}
        }    		
        	//end status code where clause if any codes specified
        if (num_status_codes > 0)        
        	where_buffer.append(") ");
        
    		//return where clause
        return where_buffer.toString();
        
	}
    
    
    /**
	 * Generate where clause for filtering a set of program referrals
	 * @param anEvent
	 * @return
	 */
    public String getInitNOpenRefsForRefTypesClause( IEvent anEvent )
	{
    	InitNOpenRefsForRefTypesEvent referrals_event = 
            							(InitNOpenRefsForRefTypesEvent)anEvent;
    	
        StringBuffer where_buffer = new StringBuffer();

    		//add defendant ID to where clause
        if (!isEmpty(referrals_event.getDefendantId()))
        {
            where_buffer.append(" DEFENDANT_ID = '").
            	append(referrals_event.getDefendantId()).append("'");
        }

        	//add status codes filter to where clause
        List refTypesCdList = referrals_event.getRefTypesCdList();
        int refTypesSize = refTypesCdList.size();
        
        	//add referral Type Codes
        if (refTypesSize > 0)
        {
        	where_buffer.append(" AND REFERRALTYPECD IN ( ");
        	
        	for (int i=0;i<refTypesSize;i++)
            {
            	where_buffer.append("'" + refTypesCdList.get(i) + "' ");
            	
            	if (i<refTypesSize-1)
            	{
            		where_buffer.append(" , ");
            	}
            	else
            	{
            		where_buffer.append(" ) ");
            	}
            }    		
        }
        
//        query only initiate and open program referrals
        where_buffer.append(" AND  PROGENDDATE IS NULL");
        
    		//return where clause
        return where_buffer.toString();
	}
    
    
    /**
	 * Generate where clause for filtering a set of program referrals by Caseload
	 * @param anEvent
	 * @return
	 */
    public String getProgramReferralsByCaseloadClause( IEvent anEvent )
	{
    	GetProgRefByCaseloadEvent event = (GetProgRefByCaseloadEvent)anEvent;
    	
    	StringBuffer where_buffer = new StringBuffer();
    	
    	if(event.getSearchBy().equalsIgnoreCase(CSAdministerProgramReferralsConstants.SERVICE_PROVIDER_SEARCH))
    	{
    		List defendantIdsList = event.getDefendantIdsList();
    		if((defendantIdsList!=null) && (!defendantIdsList.isEmpty()))
    		{
    			 where_buffer.append(" DEFENDANT_ID IN (");
    			 
    			 for(int index=0; index < defendantIdsList.size(); index++)
    			 {
    				 where_buffer.append("'" + defendantIdsList.get(index) + "'");
    				 
    				 if(index < defendantIdsList.size()-1)
    				 {
    					 where_buffer.append(",");
    				 }
    				 else
    				 {
    					 where_buffer.append(")");
    				 }
    			 }
    			 
    			String spProviderName = event.getServiceProviderName();
	    		if((spProviderName!=null) && (!spProviderName.trim().equalsIgnoreCase("")))
	    		{
	    			spProviderName = spProviderName.replace("*", "%").replaceAll( "'", "''");
	    			where_buffer.append(" AND SERVICEPROVIDERNAME LIKE UPPER('").append(spProviderName).append("')");
	    		}
	    		
	    		String isInHouse = event.getIsInHouse();
	    		if((isInHouse!=null) && (!isInHouse.trim().equalsIgnoreCase("")))
	    		{
	    			where_buffer.append(" AND ISINHOUSE = ");
	    			where_buffer.append((isInHouse.equalsIgnoreCase("Y"))?"1":"0");
	    		}
	    		
	    		String regionCd = event.getRegionCd();
	    		if(regionCd!=null && (!regionCd.trim().equalsIgnoreCase("")))
	    		{
	    			where_buffer.append(" AND REGIONCD = '").append(regionCd).append("'");;
	    		}
    		}
    	}
    	else
    	if(event.getSearchBy().equalsIgnoreCase(CSAdministerProgramReferralsConstants.PROGRAM_SEARCH))
    	{
    		List defendantIdsList = event.getDefendantIdsList();
    		if((defendantIdsList!=null) && (!defendantIdsList.isEmpty()))
    		{
    			 where_buffer.append(" DEFENDANT_ID IN (");
    			 
    			 for(int index=0; index < defendantIdsList.size(); index++)
    			 {
    				 where_buffer.append("'" + defendantIdsList.get(index) + "'");
    				 
    				 if(index < defendantIdsList.size()-1)
    				 {
    					 where_buffer.append(",");
    				 }
    				 else
    				 {
    					 where_buffer.append(")");
    				 }
    			 }
    			 
    			String programName = event.getProgramName();
	    		if((programName!=null) && (!programName.trim().equalsIgnoreCase("")))
	    		{
	    			programName = programName.replace("*", "%").replaceAll( "'", "''");
	    			where_buffer.append(" AND PROGRAMNAME LIKE UPPER('").append(programName).append("')");
	    		}
	    		
	    		String cstsCode = event.getCstsCode();
	    		if(cstsCode!=null && (!cstsCode.trim().equalsIgnoreCase("")))
	    		{
	    			where_buffer.append(" AND CTSCODE = '").append(cstsCode).append("'");;
	    		}
	    		
	    		String programGroupCd = event.getProgramGroupId();
	    		if(programGroupCd!=null && (!programGroupCd.trim().equalsIgnoreCase("")))
	    		{
	    			where_buffer.append(" AND PROGRAMGROUPCD = '").append(programGroupCd).append("'");;
	    		}
	    		
	    		String programTypeCd = event.getProgramTypeId();
	    		if(programTypeCd!=null && (!programTypeCd.trim().equalsIgnoreCase("")))
	    		{
	    			where_buffer.append(" AND PROGRAMTYPECD = '").append(programTypeCd).append("'");;
	    		}
    		}
    	}
    	else
    	if(event.getSearchBy().equalsIgnoreCase(CSAdministerProgramReferralsConstants.PROGRAM_LOCATION_SEARCH))
    	{
    		List defendantIdsList = event.getDefendantIdsList();
    		if((defendantIdsList!=null) && (!defendantIdsList.isEmpty()))
    		{
    			 where_buffer.append(" DEFENDANT_ID IN (");
    			 
    			 for(int index=0; index < defendantIdsList.size(); index++)
    			 {
    				 where_buffer.append("'" + defendantIdsList.get(index) + "'");
    				 
    				 if(index < defendantIdsList.size()-1)
    				 {
    					 where_buffer.append(",");
    				 }
    				 else
    				 {
    					 where_buffer.append(")");
    				 }
    			 }
    			 
    			 String programId = event.getProgramId();
	    		if(programId!=null && (!programId.trim().equalsIgnoreCase("")))
	    		{
	    			where_buffer.append(" AND PROGRAM_ID = ").append(programId);
	    		}
	    		
	    		String locationId = event.getLocationId();
	    		if(locationId!=null && (!locationId.trim().equalsIgnoreCase("")))
	    		{
	    			where_buffer.append(" AND LOCATION_ID = ").append(locationId);
	    		}
    		}
    	}
    	return where_buffer.toString();
	}
}
