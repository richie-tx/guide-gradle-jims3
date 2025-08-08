/*
 * Created on Dec 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.csserviceprovider;

import java.util.*;

import naming.CSAdministerServiceProviderConstants;
import pd.address.PDAddressHelper;
import pd.common.util.*;
import messaging.administerprogramreferrals.reply.ServiceProviderReftypeResponseEvent;
import messaging.csserviceprovider.*;
import messaging.csserviceprovider.reply.*;
import mojo.km.dispatch.EventManager;
import mojo.km.utilities.CollectionUtil;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSServiceProviderHelper  
{
    /**
     * Update service provider status
     * @param serviceProvider
     * @param status
     */
    public static void setServiceProviderStatus(CSServiceProvider serviceProvider, String status)
    {
        	//set service provider status and status change date
        serviceProvider.setServiceProviderStatus(status);
        serviceProvider.setStatusChangeDate(new Date());
    }//end of setServiceProviderStatus()
    
    /**
     * Determine whether the given service provider satisfies conditions for activation
     * @param serviceProvider
     * @return
     */
    public static boolean satisfyActivateCondition(CSServiceProvider serviceProvider)
    {
        boolean satisfies_activate_condition = false;
        
        	//check if at least 1 program exists for provider
        Collection sp_programs = serviceProvider.getPrograms();
        if ( sp_programs != null && sp_programs.size() > 0)
        {
            	//check service provider contacts
            Collection sp_contacts = serviceProvider.getContacts();
            if (sp_contacts != null)
            {
                Object[] sp_contact_array = sp_contacts.toArray();
                int num_contacts = sp_contacts.size();
                for (int i=0;i<num_contacts;i++)
                {
                    	//check if at least 1 administrative contact exists
                    CSServiceProviderContact sp_contact = 
                        		(CSServiceProviderContact)sp_contact_array[i];                    
                     if (sp_contact.getIsAdminContact())
                    {
                        satisfies_activate_condition = true;
                        //set local variables to null before break
                        sp_contacts = null;
                        sp_contact_array = null;
                        sp_contact = null;
                        break;
                    }
                }                
            }
        }
        
        	//return whether or not provider satisfies activate condition
        return satisfies_activate_condition;
    }//end of satisfyActivateCondition()

    /**
     * Determine whether the given service provider satisfies conditions for reset to pending
     * @param serviceProvider
     * @return
     */
    public static boolean satisfyResetPendingCondition(CSServiceProvider serviceProvider)
    {
        boolean satisfies_reset_pending_condition = true;
        
        	//retrieve programs for checking their statuses
        Collection sp_programs = serviceProvider.getPrograms();
        if ( sp_programs != null)
        {
        		//convert collection to more easily navigable array
        	Object[] sp_program_array = sp_programs.toArray();
        	int num_programs = sp_programs.size();
        	
        		//loop thru list of programs looking for at least 1 active or pending program
        	for (int i =0;i<num_programs;i++)
        	{
        		CSProgram this_program = (CSProgram)sp_program_array[i];
        		if (this_program.getStatusCode().
        				equals(CSAdministerServiceProviderConstants.ACTIVE_PROG_STATUS) ||
						this_program.getStatusCode().
							equals(CSAdministerServiceProviderConstants.PENDING_PROG_STATUS))
        		{
        			satisfies_reset_pending_condition = false;
        			//set local variables to null before break
        			sp_programs = null;
        			sp_program_array = null;
        			this_program = null;
        			break;
        		}
        	}
        }
        
        	//return whether or not provider satisfies "reset to pending" condition
        return satisfies_reset_pending_condition;
    }//end of satisfyResetPendingCondition()
    
    /**
     * Activate all programs for a given service provider
     * @param serviceProvider
     */
    private static void activatePrograms(CSServiceProvider serviceProvider)
    {
        Object[] programs_array = 
            serviceProvider.getPrograms().toArray();
        int arrayLength = programs_array.length;
        for (int i=0;i<arrayLength;i++)
        {
            	//iterate through programs and update status to active
            CSProgram this_program = (CSProgram)programs_array[i];
            
            	//set pending programs to active status
            if (this_program.getStatusCode().equals(
                    CSAdministerServiceProviderConstants.PENDING_PROG_STATUS))
            {
                this_program.setStatusCode(
                        CSAdministerServiceProviderConstants.ACTIVE_PROG_STATUS);
                this_program.setStatusChangeDate(new Date());
                
                	//set modified status for later persistence
                this_program.markModified();                
            }
        }
        //set local variable to null
        programs_array = null;
        
    }//end of activatePrograms()
    
    /**
     * Activate the given service provider if satisfies activate condition
     * @param serviceProvider
     */
    public static void activateServiceProvider(CSServiceProvider serviceProvider)
    {
        	//activate service provider if pending
        if (serviceProvider.getServiceProviderStatus().equals(
                CSAdministerServiceProviderConstants.PENDING_SP_STATUS))
        {
            setServiceProviderStatus(serviceProvider, 
                    CSAdministerServiceProviderConstants.ACTIVE_SP_STATUS);
            serviceProvider.bind();            
        }
        
        	//activate all programs for a given service provider
        activatePrograms(serviceProvider);
    }//end of activateServiceProvider()
    
    /**
     * Activate the given service provider if satisfies activate condition
     * @param serviceProvider
     */
    public static void inactivateServiceProvider(CSServiceProvider serviceProvider)
    {
            setServiceProviderStatus(serviceProvider, 
                    CSAdministerServiceProviderConstants.INACTIVE_SP_STATUS);
            serviceProvider.bind();
    }//end of inactivateServiceProvider()

    /**
     * Reset the given service provider to pending status
     * @param serviceProvider
     */
    public static void resetPendingServiceProvider(CSServiceProvider serviceProvider)
    {
            setServiceProviderStatus(serviceProvider, 
                    CSAdministerServiceProviderConstants.PENDING_SP_STATUS);
            serviceProvider.bind();
    }//end of resetPendingServiceProvider()
    
    /**
     * Save a CS Service Provider given the specifed event information
     * @param saveServiceProviderEvent
     * @return
     */
    public static CSServiceProvider saveServiceProvider(SaveServiceProviderEvent saveServiceProviderEvent)
    {
        CSServiceProvider csServiceProvider = null;
        boolean update_service_provider = false;
        
        	//check if serviceProviderId is set
		if (StringUtil.isEmpty(saveServiceProviderEvent.getServiceProviderId()))
		{
				// create a new cs service provider and initialize status to pending
			csServiceProvider = new CSServiceProvider();
			csServiceProvider.setServiceProviderStatus(
			        CSAdministerServiceProviderConstants.PENDING_SP_STATUS);
		}
		else
		{
				// update existing service provider if serviceProviderId is set
			csServiceProvider = 
			    CSServiceProvider.find(saveServiceProviderEvent.getServiceProviderId());
			update_service_provider = true;
		}
  
			//check if service provider name is a duplicate of an existing entry
		if (!isDuplicate(saveServiceProviderEvent.getServiceProviderName()) || update_service_provider)
		{
		    	//set service provider properties
		    csServiceProvider.setServiceProviderName(
		            saveServiceProviderEvent.getServiceProviderName());
		    csServiceProvider.setIsInHouse(saveServiceProviderEvent.isInHouse());
		    csServiceProvider.setStartDate(saveServiceProviderEvent.getStartDate());
		    csServiceProvider.setIfasNumber(saveServiceProviderEvent.getIfasNumber());
		    csServiceProvider.setPhoneNumber(saveServiceProviderEvent.getPhoneNumber());
		    csServiceProvider.setExtension(saveServiceProviderEvent.getExtension());
		    csServiceProvider.setFaxNumber(saveServiceProviderEvent.getFaxNumber());
		    csServiceProvider.setWebsite(saveServiceProviderEvent.getWebsite());
		    csServiceProvider.setEmailAddress(saveServiceProviderEvent.getEmailAddress());
		    csServiceProvider.setFtpSite(saveServiceProviderEvent.getFtpAddress());
		    csServiceProvider.setComments(saveServiceProviderEvent.getComments());
		    csServiceProvider.setIsInHouse(saveServiceProviderEvent.isInHouse());
		    csServiceProvider.setIsFaithBased(saveServiceProviderEvent.isFaithBased());
		    
		    	//inactivate service provider if requested
		    if (saveServiceProviderEvent.getStatusCode().equals(
		            CSAdministerServiceProviderConstants.INACTIVE_SP_STATUS))
		    {
		        inactivateServiceProvider(csServiceProvider);
		    }

		    //set composite billing and shipping address
		    if (saveServiceProviderEvent.getBillingAddress() != null) {
		    	csServiceProvider.setBillingAddress(
		            PDAddressHelper.getAddress(
		                    saveServiceProviderEvent.getBillingAddress()));
		    }	
		    csServiceProvider.setShippingAddress(
		            PDAddressHelper.getAddress(
		                    saveServiceProviderEvent.getShippingAddress()));
		    
			//set modified state for later binding to database
			csServiceProvider.bind();
		    
				//return updated service provider
			return csServiceProvider;
		}
		else
		{
		    	//post response event condition if duplicate service provider specified
		    DuplicateServiceProviderResponseEvent 
		    	duplicate_name_response = 
		    	    new DuplicateServiceProviderResponseEvent();
		    duplicate_name_response.setServiceProviderId(
		            saveServiceProviderEvent.getServiceProviderId());
		    duplicate_name_response.setServiceProviderName(
		            saveServiceProviderEvent.getServiceProviderName());
		    duplicate_name_response.setMessage(
		            				"Duplicate Service Provider Name Specified");
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(duplicate_name_response);		    
		}
			
			//return null in the event of a processing exception
		return null;
    }//end of saveServiceProvider()
 	
	/**
	 * Check if the specified service provider is a duplicate of an existing one
	 * @param validateEvent
	 * @return
	 */
	private static boolean isDuplicate(String name)
	{
	    	//initialize event for checking for validity of a service provider
		ValidateCSServiceProviderEvent validateEvent = new ValidateCSServiceProviderEvent();
		validateEvent.setServiceProviderName(name);
	    
	    	//check if any service providers exist with the given name
		Iterator service_providers_iter = CSServiceProvider.findAll(validateEvent);
		if (service_providers_iter.hasNext())
		{
		    	//is a duplicate name
			return true;
		}
		else
		{		//is not a duplicate name
		    return false;
		}
	}//end of isDuplicate()
    
    /**
     * Return a service provider response event given the specifed service provider entity
     * @return
     */
    public static CSServiceProviderResponseEvent getServiceProviderResponseEvent(CSServiceProvider csServiceProvider)
    {
        	//return a service provider response event given the specified service provider
        CSServiceProviderResponseEvent sp_response_event = 
            								new CSServiceProviderResponseEvent();

        	//set response event properties
        sp_response_event.setServiceProviderId(csServiceProvider.getServiceProviderId());
        sp_response_event.setServiceProviderName(
                csServiceProvider.getServiceProviderName());
        sp_response_event.setInHouse(csServiceProvider.getIsInHouse());
        sp_response_event.setStartDate(csServiceProvider.getStartDate());
        sp_response_event.setIfasNumber(csServiceProvider.getIfasNumber());
        sp_response_event.setPhoneNumber(csServiceProvider.getPhoneNumber());
        sp_response_event.setExtension(csServiceProvider.getExtension());
        sp_response_event.setFaxNumber(csServiceProvider.getFaxNumber());
        sp_response_event.setWebsite(csServiceProvider.getWebsite());
        sp_response_event.setEmailAddress(csServiceProvider.getEmailAddress());
        sp_response_event.setFtpAddress(csServiceProvider.getFtpSite());
        sp_response_event.setComments(csServiceProvider.getComments());
        sp_response_event.setBillingAddress(PDAddressHelper.getAddress(
                								csServiceProvider.getBillingAddress()));
        
        sp_response_event.setShippingAddress(PDAddressHelper.getAddress(
                								csServiceProvider.getShippingAddress()));
        sp_response_event.setPrograms(CSProgramHelper.getProgramResponseEvents(csServiceProvider.getPrograms()));
        sp_response_event.setContacts(
                CSServiceProviderContactHelper.getContactResponseEvents(
                        							csServiceProvider.getContacts()));
        Object[] program_array = sp_response_event.getPrograms().toArray();
        int arrayLength = program_array.length;
        //loop through programs to find a contract program set is contract program to true
        for (int i=0;i<arrayLength;i++) {
            CSProgramResponseEvent program =(CSProgramResponseEvent)program_array[i];
            if (program.isContractProgram()){
            	csServiceProvider.setIsContractProgram(program.isContractProgram());
            	//set local variables to null before break
            	program_array = null;
            	program = null;
            	break;
            }
        }
        sp_response_event.setHasContractProgram(csServiceProvider.getIsContractProgram());
        sp_response_event.setServiceProviderStatus(csServiceProvider.getServiceProviderStatus());
        sp_response_event.setStatusChangeDate(csServiceProvider.getStatusChangeDate());
        sp_response_event.setFaithBased(csServiceProvider.getIsFaithBased());
        
        	//attributes for search by program results - REALLY should be another event
        sp_response_event.setProgramIdentifier(csServiceProvider.getProgramIdentifier());
        sp_response_event.setProgramName(csServiceProvider.getProgramName());
        sp_response_event.setProgramGroupCode(csServiceProvider.getProgramGroupCode());
        sp_response_event.setProgramTypeCode(csServiceProvider.getProgramTypeCode());
        sp_response_event.setProgramStatusCode(csServiceProvider.getProgramStatusCode());
        sp_response_event.setIsContractProgram(csServiceProvider.getIsContractProgram());
        
        	//indicate that operation was successful
        sp_response_event.setOperationSuccessful(true);
        
        	//return populated response event
        return sp_response_event;
    }//end of getServiceProviderResponseEvent()
    
    /**
     * Convert list of service providers to service provider referral type response
     * @param serviceProviders
     * @return
     */
    public static List getServiceProviderReftypeResponses(List serviceProviders)
    {
    		//initialize vars
    	String active_service_provider_id = "";
    	boolean is_next_service_provider = true;    	
    	ServiceProviderReftypeResponseEvent sp_ref_type_response_event;
    	List program_referral_types = null;
    	List program_location_regions = null;
    	List service_provider_reftype_response_list = new ArrayList();
    	
    		//loop thru list of service providers and create response list
    	for (int i=0;i<serviceProviders.size();i++)
    	{
    			//retrieve properties of current service provider
    		CSServiceProvider this_service_provider = 
    			(CSServiceProvider)serviceProviders.get(i);
    		
    			//determine whether or not we've reached a new service provider
    		if (!("" + this_service_provider.getServiceProviderId()).
    				equals(active_service_provider_id))
    		{
    				//set attributes indicating new service provider encountered
    			active_service_provider_id = "" + this_service_provider.getServiceProviderId();
        		is_next_service_provider = true;
    		}
    		
    			//determine whether or not a new response event needs to be created
    		if (is_next_service_provider)
    		{
    				//add new instance of service provider reftype response to return list
    			sp_ref_type_response_event = new ServiceProviderReftypeResponseEvent();
    			service_provider_reftype_response_list.add(sp_ref_type_response_event);
    			
    				//set service provider properties
    			sp_ref_type_response_event.setServiceProviderId(
    					"" + this_service_provider.getServiceProviderId());
    			sp_ref_type_response_event.setServiceProviderName(
    										this_service_provider.getServiceProviderName());
    			sp_ref_type_response_event.setFaxNumber(this_service_provider.getFaxNumber());
    			sp_ref_type_response_event.setInHouse(this_service_provider.getIsInHouse());
    			sp_ref_type_response_event.setPhoneNumber(this_service_provider.getPhoneNumber());
    			sp_ref_type_response_event.setEmailAddress(this_service_provider.getEmailAddress());
    			
    				//initialize collections and set on response event
    			program_referral_types = new ArrayList();
    			program_location_regions = new ArrayList();
    			sp_ref_type_response_event.setProgramReferralTypes(program_referral_types);
    			sp_ref_type_response_event.setProgramLocationRegions(program_location_regions);
    			
    				//indicate that we haven't encountered a new service provider 
    			is_next_service_provider = false;
    		}
    		
    			//add to list of referral types if not previously added
    		String referral_type = 
    			this_service_provider.getProgramGroupCode() + " "
					+ this_service_provider.getProgramTypeCode();
    		if (!program_referral_types.contains(referral_type))
    			program_referral_types.add(referral_type);

				//add to list of program location regions if not previously added
    		String location_region = this_service_provider.getRegionCode();
    		if (location_region != null)
    			if (!program_location_regions.contains(location_region))
    				program_location_regions.add(location_region);
    	}
    	// set local variables to null before return
    	program_referral_types = null;
    	program_location_regions = null;
    		//return service provider referral type response list
    	return service_provider_reftype_response_list;
    }//end of getServiceProviderReftypeResponses()
    
    
    /**
     * Search for the specified CS Service Providers by program
     *
     */
    public List searchByProgram(SearchByProgramEvent searchServiceProviderEvent)
    {
        return null;
    }//end of searchByProgram()

    /**
     * Search for the specified CS Service Providers
     *
     */
    public static List searchServiceProvider(SearchServiceProviderEvent 
            									searchEvent)
    {
    		//retrieve all service providers matching event attributes
        return sortServiceProviderRefTypes(
        		CollectionUtil.iteratorToList(CSServiceProvider.findAll(searchEvent)));
    }//end of searchByServiceProviderName()
    
    public static List sortServiceProviderRefTypes(List serviceProviders)
    {
    		//create comparator for sorting list
    	Comparator sp_comp =
    		new Comparator()
	        {
	            public int compare(Object o1, Object o2)
	            {
	            	CSServiceProvider sp1 = (CSServiceProvider)o1;
	            	CSServiceProvider sp2 = (CSServiceProvider)o2;
	            	
	            	if (sp1.getServiceProviderId() == sp2.getServiceProviderId())
	            	{	//check other attributes if service provider equal
	            		
	            		int comp_prog = 
	            			("" + sp1.getProgramId()).compareTo(
	            					"" + sp2.getProgramId());
	            		if (comp_prog == 0)
	            		{
	            				//compare program locations
	            			int comp_loc = 
	            				(""+ sp1.getLocationId()).compareTo(
	            						"" + sp2.getLocationId());
	            			if (comp_loc == 0)
	            			{
	            					//compare program languages
	        					int comp_lang = 
	        						("" + sp1.getProgramLanguage()).compareTo(
	        								"" + sp2.getProgramLanguage());
	        						
	        							//return result of program language ID
	        					return comp_lang;            					
	            			}
	            			else	//return result of program location id
	            				return comp_loc;
	            		}
	            		else	//return result of program ID comparison
	            			return comp_prog;
	            	}
	            	else	//return comparison of service provider IDs 
	            		return 
	            		(sp1.getServiceProviderId() < sp2.getServiceProviderId()?-1:1);
	            	
	            }//end of compare()
	    
	        };

	        	//sort service provider ref type using specified comparator
        Collections.sort( serviceProviders, sp_comp);    
    	
        return serviceProviders;
    }
    
    /**
     * Search for the specified CS Service Providers
     *
     */
    public static List searchServiceProviderOrderByLoc(SearchServiceProviderEvent 
            									searchEvent)
    {
    		//retrieve all service providers matching event attributes
        return sortServiceProviderPgmLocByLocOrder(
        		CollectionUtil.iteratorToList(CSServiceProvider.findAll(searchEvent)));
    }//end of searchByServiceProviderName()
    
    
    public static List sortServiceProviderPgmLocByLocOrder(List serviceProviders)
    {
    		//create comparator for sorting list
    	Comparator sp_comp =
    		new Comparator()
	        {
	            public int compare(Object o1, Object o2)
	            {
	            	CSServiceProvider sp1 = (CSServiceProvider)o1;
	            	CSServiceProvider sp2 = (CSServiceProvider)o2;
	            	
	            	if (sp1.getServiceProviderId() == sp2.getServiceProviderId())
	            	{	//check other attributes if service provider equal
	            		
	            		//compare program locations
            			int comp_loc = 
            				(""+ sp1.getLocationId()).compareTo(
            						"" + sp2.getLocationId());
	            		if (comp_loc == 0)
	            		{
	            			int comp_prog = 
		            			("" + sp1.getProgramId()).compareTo(
		            					"" + sp2.getProgramId());
	            			if (comp_prog == 0)
	            			{
	            					//compare program languages
	        					int comp_lang = 
	        						("" + sp1.getProgramLanguage()).compareTo(
	        								"" + sp2.getProgramLanguage());
	        						
	        							//return result of program language ID
	        					return comp_lang;            					
	            			}
	            			else	//return result of program location id
	            				return comp_prog;
	            		}
	            		else	//return result of program ID comparison
	            			return comp_loc;
	            	}
	            	else	//return comparison of service provider IDs 
	            		return 
	            		(sp1.getServiceProviderId() < sp2.getServiceProviderId()?-1:1);
	            	
	            }//end of compare()
	    
	        };

	        	//sort service provider ref type using specified comparator
        Collections.sort( serviceProviders, sp_comp);    
    	
        return serviceProviders;
    }
    
    
    /**
     * Retrieve service providers for specified referral types
     * @param referralTypes
     * @return
     */
    public static List getServiceProvidersForReferralTypes(List referralTypes)
	{
		//retrieve service providers for specified referral types
		SearchByReferralTypesEvent ref_type_search_event = new SearchByReferralTypesEvent();
		ref_type_search_event.setReferralTypes(referralTypes);
		return sortServiceProviderRefTypes(
				CSServiceProviderHelper.searchServiceProvider(ref_type_search_event));    	
	}//end of getServiceProvidersForReferralTypes()

    /**
     * Retrieve program locations for specified service providers
     * @param referralTypes
     * @return
     */
    public static List getServiceProviderProgramLocations(List serviceProviderIds, List referralTypeCds)
	{
		//filter list by the specified service provider IDs
    	GetServiceProviderLocationsEvent get_sp_locations_event = new GetServiceProviderLocationsEvent();
    	get_sp_locations_event.setServiceProviderIds(serviceProviderIds);
    	get_sp_locations_event.setReferralTypeCds(referralTypeCds);
		return searchServiceProvider(get_sp_locations_event);    	
	}//end of filterByServiceProvider()
    
    
    /**
     * Retrieve program locations for specified service providers
     * @param referralTypes
     * @return
     */
    public static List getSPProgramLocationsOrderByLoc(List serviceProviderIds, List referralTypeCds)
	{
		//filter list by the specified service provider IDs
    	GetSPLocationsOrderByLocEvent get_sp_locations_event = new GetSPLocationsOrderByLocEvent();
    	get_sp_locations_event.setServiceProviderIds(serviceProviderIds);
    	get_sp_locations_event.setReferralTypeCds(referralTypeCds);
		return searchServiceProviderOrderByLoc(get_sp_locations_event);    	
	}//end of filterByServiceProvider()
    
    
    /**
     * Search for the specified CS Service Providers by name
     *
     */
    public static List searchByServiceProviderName(SearchByServiceProviderEvent 
            									searchByNameEvent)
    {
        	//retrieve all service providers matching event attributes
        Iterator service_providers_iter = CSServiceProvider.findAll(searchByNameEvent);
        
        	//convert result set into more easily navigable list
        List  service_providers_list = new ArrayList();
        while (service_providers_iter.hasNext())
        {
            service_providers_list.add((CSServiceProvider)service_providers_iter.next());
        }
        	//return list of service providers
        return service_providers_list;
    }//end of searchByServiceProviderName()
    
    /**
     * Retrieve the specified service provider
     * @param serviceProviderId
     * @return
     */
    public static CSServiceProvider getServiceProvider(String serviceProviderId)
    {
        return CSServiceProvider.find(serviceProviderId);
    }//end of getServiceProvider()
    
}//end of CSServiceProviderHelper
