/*
 * Created on Jan 17, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.administerserviceprovider.csserviceprovider;


import java.util.List;

import org.apache.commons.lang.StringUtils;

import naming.CSAdministerServiceProviderConstants;

import messaging.csserviceprovider.FilterByServiceProviderEvent;
import messaging.csserviceprovider.GetLocationByCSServiceProviderEvent;
import messaging.csserviceprovider.GetSPLocationsOrderByLocEvent;
import messaging.csserviceprovider.GetServiceProviderLocationsEvent;
import messaging.csserviceprovider.ProgramUnitProgramQueryProgramEvent;
import messaging.csserviceprovider.SearchByProgramEvent;
import messaging.csserviceprovider.SearchByReferralTypesEvent;
import messaging.csserviceprovider.SearchByServiceProviderEvent;
import mojo.km.messaging.IEvent;


/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSSearchServiceProviderWhereClause 
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
     * Generate where clause for searching for a service provider by name and status
     * @param anEvent
     * @return
     */
    public String getServiceProviderByNameClause( IEvent anEvent )
	{
        SearchByServiceProviderEvent search_sp_event = 
            							(SearchByServiceProviderEvent)anEvent;
        StringBuffer where_buffer = new StringBuffer();
                
        	//replace * with % for wildcard searches
        if (!isEmpty(search_sp_event.getServiceProviderName()))
        {	
            String sp_name_search = search_sp_event.getServiceProviderName();
            sp_name_search = sp_name_search.replace('*', '%');
            where_buffer.append("upper(SERVICEPROVIDERNAME) like upper('").
        	append(sp_name_search).append("')");
        }
        else
        {		//add dummy true clause to keep sql syntax correct if no service provider name specified
            where_buffer.append("1 = 1");
        }

        	//add service provider status to where clause if selected
        if (!isEmpty(search_sp_event.getServiceProviderStatus()))
        {
            where_buffer.append(" AND SRVPROVSTATUSCD = '").
            	append(search_sp_event.getServiceProviderStatus()).append("'");
        }
        
        	//add service provider in house status to where clause if selected
        if (!isEmpty(search_sp_event.getIsInHouse()))
        {
            	//append 0 or 1 to query based on boolean value
            where_buffer.append(" AND ISINHOUSE = ").
            	append((search_sp_event.getIsInHouse().equals("YES"))?"1":"0");
        }
        
        	//add service provider program contract status to where clause if selected        
        if (!isEmpty(search_sp_event.getHasContractProgram()))
        {
        		//append 0 or 1 to query based on boolean value
            where_buffer.append(" AND ISCONTRACT = ").
            	append((search_sp_event.getHasContractProgram().equals("true"))?"1":"0");           
        }
        	
        	//sort by service provider name
        where_buffer.append(" ORDER BY SERVICEPROVIDERNAME ");
        
        	//return where clause
        return where_buffer.toString();
	}//end of getServiceProviderByNameClause()
    
    /**
     * Generate where clause for searching for a service provider by program
     * @param anEvent
     * @return
     */
    public String getServiceProviderByProgramClause( IEvent anEvent )
	{
        SearchByProgramEvent search_sp_event = 
            							(SearchByProgramEvent)anEvent;
        StringBuffer where_buffer = new StringBuffer();
        
			//replace * with % for wildcard searches
        if (!isEmpty(search_sp_event.getProgramName()))
        {
            String sp_prog_search = search_sp_event.getProgramName();
            sp_prog_search = sp_prog_search.replace('*', '%');
            where_buffer.append("upper(PROGRAMNAME) like upper('").
            	append(sp_prog_search).append("')");            
        }
        else
        {		//add dummy true clause to keep sql syntax correct if no program name specified
            where_buffer.append("1 = 1");
        }

        	//add program group code to where clause if selected
        if (!isEmpty(search_sp_event.getProgramGroupCode()))
        {
            where_buffer.append(" AND PROGRAMGROUPCD = '").
            	append(search_sp_event.getProgramGroupCode()).append("'");
        }
        
    		//add program type code to where clause if selected
        if (!isEmpty(search_sp_event.getProgramTypeCode()))
        {
            where_buffer.append(" AND PROGRAMTYPECD = '").
            	append(search_sp_event.getProgramTypeCode()).append("'");
        }

    		//add program contract status to where clause if selected        
        if (!isEmpty(search_sp_event.getIsContractProgram()))
        {
        		//append 0 or 1 to query based on boolean value
            where_buffer.append(" AND ISCONTRACT = ").
            	append((search_sp_event.getIsContractProgram().equals("YES"))?"1":"0");           
        }
        
			//add program status code to where clause if selected
        if (!isEmpty(search_sp_event.getProgramStatus()))
        {
            where_buffer.append(" AND PROGSTATUSCD = '").
            	append(search_sp_event.getProgramStatus()).append("'");
        }
        
        	//sort by service provider name
        where_buffer.append(" ORDER BY SERVICEPROVIDERNAME ");
                
        	//return where clause
        return where_buffer.toString();
	}//end of getServiceProviderByNameClause()
        
    /**
     * Generate where clause for searching for a service providers by given referral types
     * @param anEvent
     * @return
     */
    public String getServiceProvidersByRefTypesClause( IEvent anEvent )
	{
    	SearchByReferralTypesEvent search_sp_event = 
            							(SearchByReferralTypesEvent)anEvent;
        StringBuffer where_buffer = new StringBuffer();
        
        	//add list of referral types to where clause
        List referral_types = search_sp_event.getReferralTypes();
        int num_referral_types = referral_types.size();
        for (int i=0;i<num_referral_types;i++)
        {
        		//parse program group and type code from referral type
        	String referral_type = (String)referral_types.get(i);        	
        	int separator_indx = 
        		referral_type.indexOf(CSAdministerServiceProviderConstants.PROGRAM_SEPARATOR);        	
        	String program_group_code = referral_type.substring(0,separator_indx);
        	String program_type_code = referral_type.substring(separator_indx + 1);
        	
        	if(i==0)
        	{
        		where_buffer.append(" (");
        	}
        		//add referral type group and type to where clause
        	where_buffer.append("( PROGRAMGROUPCD = '");
        	where_buffer.append(program_group_code);
        	where_buffer.append("'");
        	where_buffer.append(" AND PROGRAMTYPECD = '");
        	where_buffer.append(program_type_code);
        	where_buffer.append("') ");
        	
        		//add 'or' statement if further referral types need to be processed
        	if (i<num_referral_types-1)
        	{
        		where_buffer.append(" OR ");
        	}
        	else
        	{
        		where_buffer.append(") ");
        	}
        }
        
    	//check only active service providers
    	where_buffer.append(" AND ( SRVPROVSTATUSCD = '"); 
    	where_buffer.append(CSAdministerServiceProviderConstants.ACTIVE_SP_STATUS);
    	where_buffer.append("') ");
    	
    	//check only ACTIVE or UNDER INVESTIGATION programs
    	where_buffer.append(" AND PROGSTATUSCD IN ( '"); 
    	where_buffer.append(CSAdministerServiceProviderConstants.ACTIVE_PROG_STATUS);
    	where_buffer.append("','");
    	where_buffer.append(CSAdministerServiceProviderConstants.UNDER_INVESTIGATION_PROG_STATUS);
    	where_buffer.append("' )");
        
    		//add order by clause
        where_buffer.append(" ORDER BY SERVPROVIDER_ID, PROGRAM_ID, LOCATION_ID, LANGUAGECD ");

        	//return where clause
        return where_buffer.toString();        
	}//end of getServiceProvidersByReferralTypes()    
    
    
    /**
     * Generate where clause for searching for a service providers by given referral types
     * @param anEvent
     * @return
     */
    public String getFilterServiceProvidersClause( IEvent anEvent )
	{
    	FilterByServiceProviderEvent filter_sp_event = 
            							(FilterByServiceProviderEvent)anEvent;
    	
        StringBuffer where_buffer = new StringBuffer();
        
        	//add list of referral types to where clause
        List referral_types = filter_sp_event.getReferralTypes();
        int num_referral_types = referral_types.size();
        
        for (int i=0;i<num_referral_types;i++)
        {
        		//parse program group and type code from referral type
        	String referral_type = (String)referral_types.get(i);        	
        	int separator_indx = 
        		referral_type.indexOf(CSAdministerServiceProviderConstants.PROGRAM_SEPARATOR);        	
        	String program_group_code = referral_type.substring(0,separator_indx);
        	String program_type_code = referral_type.substring(separator_indx + 1);
        	
        	if(i==0)
        	{
        		where_buffer.append(" (");
        	}
        	
        		//add referral type group and type to where clause
        	where_buffer.append("( PROGRAMGROUPCD = '");
        	where_buffer.append(program_group_code);
        	where_buffer.append("'");
        	where_buffer.append(" AND PROGRAMTYPECD = '");
        	where_buffer.append(program_type_code);
        	where_buffer.append("') ");
        	
        		//add 'or' statement if further referral types need to be processed
        	if (i<num_referral_types-1)
        	{
        		where_buffer.append(" OR ");
        	}
        	else
        	{
        		where_buffer.append(") ");
        	}
        }
        
    	//check only active service providers
    	where_buffer.append(" AND ( SRVPROVSTATUSCD = '"); 
    	where_buffer.append(CSAdministerServiceProviderConstants.ACTIVE_SP_STATUS);
    	where_buffer.append("') ");
    	
    	//check only ACTIVE or UNDER INVESTIGATION programs
    	where_buffer.append(" AND PROGSTATUSCD IN ( '"); 
    	where_buffer.append(CSAdministerServiceProviderConstants.ACTIVE_PROG_STATUS);
    	where_buffer.append("','");
    	where_buffer.append(CSAdministerServiceProviderConstants.UNDER_INVESTIGATION_PROG_STATUS);
    	where_buffer.append("' )");
    	
//    	filter by selected regionCds
    	List regionCdsList = filter_sp_event.getRegiondCdsList();
    	int regionCdsLen = regionCdsList.size();
    	if(regionCdsLen>0)
    	{
    		where_buffer.append(" AND REGIONCD IN ( ");
    		
    		for(int index=0; index < regionCdsLen; index++)
    		{
    			String regionCd = (String)regionCdsList.get(index);
    			where_buffer.append("'");
    			where_buffer.append(regionCd);
    			where_buffer.append("'");
    			if(index<regionCdsLen-1)
    			{
    				where_buffer.append(" , ");
    			}
    		}
    		where_buffer.append(" )");
    	}
    	
//    	filter by selected langauges
    	List languagesCdList = filter_sp_event.getLanguagesOfferedList();
    	int langCdLen = languagesCdList.size();
    	if(langCdLen>0)
    	{
    		where_buffer.append(" AND LANGUAGECD IN ( ");
    		
    		for(int index=0; index < langCdLen; index++)
    		{
    			String languageCd = (String)languagesCdList.get(index);
    			where_buffer.append("'");
    			where_buffer.append(languageCd);
    			where_buffer.append("'");
    			if(index<langCdLen-1)
    			{
    				where_buffer.append(" , ");
    			}
    		}
    		where_buffer.append(" )");
    	}
    	
//    	filter by sex code
    	String sexSpecificCd = filter_sp_event.getSexSpecific();
    	if( StringUtils.isNotEmpty(sexSpecificCd) )
    	{
    		where_buffer.append(" AND SEXSPECIFICCD = '");
    		where_buffer.append(sexSpecificCd);
    		where_buffer.append("' ");
    	}
    	
//    	filter by contract program
    	String contractProgram = filter_sp_event.getContractProgram();
    	if( StringUtils.isNotEmpty(contractProgram) )
    	{
    		if(contractProgram.equalsIgnoreCase("true"))
    		{
    			where_buffer.append(" AND ISCONTRACT = 1 ");
    		}
    		else if(contractProgram.equalsIgnoreCase("false"))
    		{
    			where_buffer.append(" AND ISCONTRACT = 0 ");
    		}
    	}
    	
    	
    		//add order by clause
        where_buffer.append(" ORDER BY SERVPROVIDER_ID, PROGRAM_ID, LOCATION_ID, LANGUAGECD ");

        	//return where clause
        return where_buffer.toString();        
	}//end of getServiceProvidersByReferralTypes()  
    
    
    
    /**
     * Generate where clause for searching for a service providers by given referral types
     * @param anEvent
     * @return
     */
    public String getServiceProviderLocationsClause( IEvent anEvent )
	{
    	GetServiceProviderLocationsEvent filter_sp_event = 
            							(GetServiceProviderLocationsEvent)anEvent;
        StringBuffer where_buffer = new StringBuffer();
        
        	//add list of service provider ids to where clause
        List service_provider_ids = filter_sp_event.getServiceProviderIds();
        int num_service_providers = service_provider_ids.size();
        
        	//begin where clause
        where_buffer.append(" SERVPROVIDER_ID in (");
        for (int i=0;i<num_service_providers;i++)
        {
        		//add service provider id to where clause
        	String service_provider_id = (String)service_provider_ids.get(i);        	
        	where_buffer.append(service_provider_id );
        	
        		//add ',' statement if further service provider IDs need to be processed
        	if (i<num_service_providers-1)
        	{
        		where_buffer.append(" , ");
        	}
        }    	
        	//close where clause
        where_buffer.append(" )");
        
//        add list of referral type ids to where clause
        List referral_types = filter_sp_event.getReferralTypeCds();
        for(int j=0; j < referral_types.size(); j++)
        {
        	String referralType = (String)referral_types.get(j);
        	int separator_index = referralType.indexOf(CSAdministerServiceProviderConstants.PROGRAM_SEPARATOR);
        	String program_code = referralType.substring(0, separator_index).trim();
        	String program_type = referralType.substring(separator_index).trim();
        	
        	if(j==0)
        	{
        		where_buffer.append(" AND (");
        	}
        	
        	where_buffer.append("( PROGRAMGROUPCD = '");
        	where_buffer.append(program_code);
        	where_buffer.append("'");        	
        	where_buffer.append(" AND PROGRAMTYPECD = '");
        	where_buffer.append(program_type);
        	where_buffer.append("' )");
        	
        	if(j<referral_types.size()-1)
        	{
        		where_buffer.append(" OR ");
        	}
        	else
        	{
        		where_buffer.append(") ");
        	}
        }
        
    	//check only ACTIVE or UNDER INVESTIGATION programs
    	where_buffer.append(" AND PROGSTATUSCD IN ( '"); 
    	where_buffer.append(CSAdministerServiceProviderConstants.ACTIVE_PROG_STATUS);
    	where_buffer.append("','");
    	where_buffer.append(CSAdministerServiceProviderConstants.UNDER_INVESTIGATION_PROG_STATUS);
    	where_buffer.append("' )");
    	
    	//check only ACTIVE VALID LOCATION STATUS 
    	where_buffer.append(" AND VALIDLOCSTATUS = 'A' ");
    	
    	//add order by clause
        where_buffer.append(" ORDER BY SERVPROVIDER_ID, PROGRAM_ID, LOCATION_ID, LANGUAGECD ");
        
        	//return where clause
        return where_buffer.toString();        
	}//end of getServiceProviderLocations() 
    
    
    
    /**
     * Generate where clause for searching for a service providers by given referral types
     * @param anEvent
     * @return
     */
    public String getSPLocationsOrderByLocClause( IEvent anEvent )
	{
    	GetSPLocationsOrderByLocEvent filter_sp_event = 
            							(GetSPLocationsOrderByLocEvent)anEvent;
        StringBuffer where_buffer = new StringBuffer();
        
        	//add list of service provider ids to where clause
        List service_provider_ids = filter_sp_event.getServiceProviderIds();
        int num_service_providers = service_provider_ids.size();
        
        	//begin where clause
        where_buffer.append(" SERVPROVIDER_ID in (");
        for (int i=0;i<num_service_providers;i++)
        {
        		//add service provider id to where clause
        	String service_provider_id = (String)service_provider_ids.get(i);        	
        	where_buffer.append(service_provider_id );
        	
        		//add ',' statement if further service provider IDs need to be processed
        	if (i<num_service_providers-1)
        	{
        		where_buffer.append(" , ");
        	}
        }    	
        	//close where clause
        where_buffer.append(" )");
        
        
//        add list of referral type ids to where clause
        List referral_types = filter_sp_event.getReferralTypeCds();
        for(int j=0; j < referral_types.size(); j++)
        {
        	String referralType = (String)referral_types.get(j);
        	int separator_index = referralType.indexOf(CSAdministerServiceProviderConstants.PROGRAM_SEPARATOR);
        	String program_code = referralType.substring(0, separator_index).trim();
        	String program_type = referralType.substring(separator_index).trim();
        	
        	if(j==0)
        	{
        		where_buffer.append(" AND (");
        	}
        	
        	where_buffer.append("( PROGRAMGROUPCD = '");
        	where_buffer.append(program_code);
        	where_buffer.append("'");        	
        	where_buffer.append(" AND PROGRAMTYPECD = '");
        	where_buffer.append(program_type);
        	where_buffer.append("' )");
        	
        	if(j<referral_types.size()-1)
        	{
        		where_buffer.append(" OR ");
        	}
        	else
        	{
        		where_buffer.append(") ");
        	}
        }
        
    	//check only ACTIVE or UNDER INVESTIGATION programs
    	where_buffer.append(" AND PROGSTATUSCD IN ( '");
    	where_buffer.append(CSAdministerServiceProviderConstants.ACTIVE_PROG_STATUS);
    	where_buffer.append("','");
    	where_buffer.append(CSAdministerServiceProviderConstants.UNDER_INVESTIGATION_PROG_STATUS);
    	where_buffer.append("' )");
        
        	//add order by clause
        where_buffer.append(" ORDER BY SERVPROVIDER_ID, LOCATION_ID, PROGRAM_ID, LANGUAGECD ");
        
        	//return where clause
        return where_buffer.toString();        
	}//end of getServiceProviderLocations()    
    
    /**
     * Generate where clause for retrieving a program by program unit
     * @param anEvent
     * @return
     */
    public String getProgramUnitProgramClause( IEvent anEvent )
	{
    	ProgramUnitProgramQueryProgramEvent pup_event = 
            							(ProgramUnitProgramQueryProgramEvent)anEvent;
        StringBuffer where_buffer = new StringBuffer();

        	//add always true clause to account for missing data which may cause SQL error
        where_buffer.append(" 1 = 1 ");
        
    	//add program id to where clause
	    if (!isEmpty(pup_event.getProgramId()))
	    {
	        where_buffer.append(" AND PROGRAM_ID != ").append(pup_event.getProgramId());
	    }
	    
        	//add program unit to where clause
	    if (!isEmpty(pup_event.getProgramUnitId()))
	    {
	        where_buffer.append(" AND ORGANIZATION_ID = ").append(pup_event.getProgramUnitId());
	    }
    
			//add program status to query if specified
        if (!isEmpty(pup_event.getProgramStatus()))
        {
            String not_prog_status = pup_event.getProgramStatus();
            where_buffer.append(" AND STATUSCD != '").
            	append(not_prog_status).append("'");            
        }
                        
        	//return where clause
        return where_buffer.toString();
	}//end of getProgramUnitProgramClause()   
    
    
    public String getLocationByServiceProviderClause( IEvent anEvent )
    {
    	GetLocationByCSServiceProviderEvent get_sp_event = 
			(GetLocationByCSServiceProviderEvent)anEvent;
    	
		StringBuffer where_buffer = new StringBuffer();
		
			//add always true clause to enable placing 'AND' on all clauses
		where_buffer.append(" AGENCYCD = 'CSC' ");
		
			//add service provider to where clause
		if (!isEmpty(get_sp_event.getServiceProviderName()))
		{
	    	//replace * with % for wildcard searches
	        String sp_name_search = get_sp_event.getServiceProviderName().replace('*', '%');		
			where_buffer.append(" AND upper(SERVICEPROVIDERNAME) like upper('").append(sp_name_search).append("')");
		}

			//add inhouse to where clause
		if (!isEmpty(get_sp_event.getIsInHouse()))
		{
			where_buffer.append(" AND ISINHOUSE  = ").append(get_sp_event.getIsInHouse());
		}		

			//add location status code where clause
		if (!isEmpty(get_sp_event.getLocationStatusCode()))
		{
			where_buffer.append(" AND LOCATIONSTATUSCODE = '").append(get_sp_event.getLocationStatusCode()).append("'");
		}

			//add location  code where clause
		if (!isEmpty(get_sp_event.getLocationCode()))
		{
	    	//replace * with % for wildcard searches
	        String location_code_search = get_sp_event.getLocationCode().replace('*', '%');		
			where_buffer.append(" AND upper(LOCATIONCD) like upper('").append(location_code_search).append("')");
		}		

			//add region code where clause
		if (!isEmpty(get_sp_event.getRegionCode()))
		{
			where_buffer.append(" AND REGIONCD = '").append(get_sp_event.getRegionCode()).append("'");
		}		

			//add location name where clause
		if (!isEmpty(get_sp_event.getLocationName()))
		{
	    	//replace * with % for wildcard searches
	        String loc_name_search = get_sp_event.getLocationName().replace('*', '%');				
			where_buffer.append(" AND upper(LOCATIONNAME) like upper('").append(loc_name_search).append("')");
		}		
		
			//add street number where clause
		if (!isEmpty(get_sp_event.getStreetNum()))
		{
			//replace * with % for wildcard searches
			String street_num_search = get_sp_event.getStreetNum().replace('*', '%');
			where_buffer.append(" AND STREETNUMBER like '").append(street_num_search).append("'");
		}		
		
			//add street name where clause
		if (!isEmpty(get_sp_event.getStreetName()))
		{
			//replace * with % for wildcard searches
			String street_name_search = get_sp_event.getStreetName().replace('*', '%');
			where_buffer.append(" AND upper(STREETNAME) like upper('").append(street_name_search).append("')");
		}		

			//add city to where clause
		if (!isEmpty(get_sp_event.getCity()))
		{
			//replace * with % for wildcard searches
			String city_search = get_sp_event.getCity().replace('*', '%');
			where_buffer.append(" AND upper(CITY) like upper('").append(city_search).append("')");
		}		

			//add state to where clause
		if (!isEmpty(get_sp_event.getStateCode()))
		{
			where_buffer.append(" AND STATE = '").append(get_sp_event.getStateCode()).append("'");
		}		

			//add zip code to where clause
		if (!isEmpty(get_sp_event.getZipCode()))
		{
			//replace * with % for wildcard searches
			String zip_search = get_sp_event.getZipCode().replace('*', '%');
			where_buffer.append(" AND ZIPCODE like '").append(zip_search).append("'");
		}		
		
		
		//return where clause
		return where_buffer.toString();
    }//end of getLocationByServiceProviderClause()
    
}//end of CSSearchServiceProviderWhereClause
