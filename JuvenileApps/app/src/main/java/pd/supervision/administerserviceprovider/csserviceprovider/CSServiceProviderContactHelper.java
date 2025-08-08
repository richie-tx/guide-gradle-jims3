/*
 * Created on Feb 4, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.csserviceprovider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import messaging.csserviceprovider.SaveSPContactEvent;
import messaging.csserviceprovider.reply.CSServiceProviderContactResponseEvent;
import naming.CSAdministerServiceProviderConstants;
import pd.common.util.StringUtil;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSServiceProviderContactHelper 
{
    /**
     * Retrieve the specified service provider contact
     * @param contactId
     * @return
     */
    public static CSServiceProviderContact getContact(String contactId)
    {
        return CSServiceProviderContact.find(contactId);
    }//end of getContact()
    
    public static CSServiceProviderContact saveServiceProviderContact(SaveSPContactEvent saveContactEvent)
    {
        CSServiceProviderContact spContact = null;
        boolean update_contact = false;
        
        	//check if getServiceProviderContactId() is set
		if (StringUtil.isEmpty(saveContactEvent.getServiceProviderContactId()))
		{
				// create a new service provider contact and initialize status to active
		    spContact = new CSServiceProviderContact();
		    spContact.setStatusCode(
			        CSAdministerServiceProviderConstants.ACTIVE_CONTACT_STATUS);
		}
		else
		{
				// update existing service provider contact if serviceProviderId is set
		    spContact = 
		        CSServiceProviderContact.find(saveContactEvent.getServiceProviderContactId());
		    update_contact = true;
		}
		  
	    	//set service provider contact properties
	    spContact.setServiceProviderId(saveContactEvent.getServiceProviderId());
	    spContact.setLastName(saveContactEvent.getLastName());
	    spContact.setFirstName(saveContactEvent.getFirstName());
	    spContact.setMiddleName(saveContactEvent.getMiddleName());
	    spContact.setIsAdminContact(saveContactEvent.getIsAdminContact());
	    spContact.setOfficeNumber(saveContactEvent.getOfficeNumber());	  
	    spContact.setExtension(saveContactEvent.getExtension());	  	   
	    spContact.setFaxNumber(saveContactEvent.getFaxNumber());		    
	    spContact.setCellNumber(saveContactEvent.getCellNumber());
	    spContact.setPagerNumber(saveContactEvent.getPagerNumber());
	    spContact.setEmailAddress(saveContactEvent.getEmailAddress());		    		    
	    spContact.setNotes(saveContactEvent.getNotes());
	    spContact.setJobTitle(saveContactEvent.getJobTitle());
	    spContact.setStatusCode(saveContactEvent.getStatusCode());
	    
			//bind program to DB
	    spContact.bind();
	    		    
			//return updated service provider contact
		return spContact;
    }

	/**
	 * Return a contact response event using the given contact
	 * @param spProgram
	 * @return
	 */
    public static CSServiceProviderContactResponseEvent getContactResponseEvent(
            								CSServiceProviderContact spContact)
    {
        CSServiceProviderContactResponseEvent contact_response_event = 
            						new CSServiceProviderContactResponseEvent();
        
        	//set contact response properties
        contact_response_event.setServiceProviderContactId(spContact.getOID());
        contact_response_event.setServiceProviderId(spContact.getServiceProviderId());
        contact_response_event.setLastName(spContact.getLastName());
        contact_response_event.setFirstName(spContact.getFirstName());
        contact_response_event.setMiddleName(spContact.getMiddleName());
        contact_response_event.setIsAdminContact(spContact.getIsAdminContact());
        contact_response_event.setOfficeNumber(spContact.getOfficeNumber());
        contact_response_event.setFaxNumber(spContact.getFaxNumber());
        contact_response_event.setCellNumber(spContact.getCellNumber());
        contact_response_event.setPagerNumber(spContact.getPagerNumber());
        contact_response_event.setEmailAddress(spContact.getEmailAddress());
        contact_response_event.setNotes(spContact.getNotes());
        contact_response_event.setJobTitle(spContact.getJobTitle());        
        contact_response_event.setStatusCode(spContact.getStatusCode());
        contact_response_event.setStatusChangeDate(spContact.getStatusChangeDate());
        contact_response_event.setStatusChangeComments(spContact.getStatusChangeComments());
        

        	//return contact response event
        return contact_response_event;
    }//end of getContactResponseEvent()
    
    /**
     * Return list of contact response events
     * @param programs
     * @return
     */
    public static List getContactResponseEvents(Collection contacts)
    {
        Object[] contact_array = contacts.toArray();
        ArrayList contact_responses = new ArrayList(contact_array.length);
        for (int i=0;i<contact_array.length;i++)
        {
            contact_responses.add(
                    getContactResponseEvent((CSServiceProviderContact)contact_array[i]));
        }
        
        	//return list of contact responses
        return contact_responses;
    }//end of addProgramResponseEvents()
    
}//end of CSServiceProviderContactHelper
