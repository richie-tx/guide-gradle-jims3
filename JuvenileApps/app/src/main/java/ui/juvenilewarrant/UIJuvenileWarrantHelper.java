package ui.juvenilewarrant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import messaging.address.reply.AddressResponseEvent;
import messaging.agency.GetDepartmentEvent;
import messaging.agency.GetDepartmentsAndAgencyEvent;
import messaging.codetable.person.reply.JuvenileSchoolDistrictCodeResponseEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import messaging.family.GetFamilyConstellationsEvent;
import messaging.family.GetFamilyMemberAddressEvent;
import messaging.family.GetFamilyMemberContactEvent;
import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.juvenile.GetSchoolDistrictsEvent;
import messaging.juvenilecase.GetCasefileWithReferralsEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberEmailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberPhoneResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralDetailResponseEvent;
import messaging.juvenilewarrant.GetJJSDataEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantsForViewEvent;
import messaging.juvenilewarrant.reply.InvalidWarrantStageErrorEvent;
import messaging.juvenilewarrant.reply.JuvenileAssociateResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import naming.AgencyControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDJuvenileFamilyConstants;
import pd.contact.officer.OfficerProfile;
import ui.common.Name;
import ui.common.UINotificationHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileFamilyForm.MemberList;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileMemberForm.MemberContact;
import ui.juvenilewarrant.form.JuvenileAssociateBean;
import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author asrvastava
 * 
 * This is a utility class for common operations required in JuvenileWarrant
 * actions.
 */
public final class UIJuvenileWarrantHelper
{
    /**
     * private constructor
     */
    private UIJuvenileWarrantHelper()
    {
    }

    // TODO Move to a worker in the agency package
    static public DepartmentResponseEvent fetchDepartment(String departmentId)
    {
        GetDepartmentEvent getEvent = (GetDepartmentEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENT);
        getEvent.setDepartmentId(departmentId);
        getEvent.setGetAddressAndContact(false);
        CompositeResponse response = MessageUtil.postRequest(getEvent);
        return (DepartmentResponseEvent) MessageUtil.filterComposite(response, DepartmentResponseEvent.class);
    }

    static public Collection fetchAgencyDepartments(JuvenileWarrantForm jwForm)
    {
        GetDepartmentsAndAgencyEvent departmentEvent = (GetDepartmentsAndAgencyEvent) EventFactory
                .getInstance(AgencyControllerServiceNames.GETDEPARTMENTSANDAGENCY);
        departmentEvent.setDepartmentName(jwForm.getTransferOfficerDepartmentName());
        departmentEvent.setDepartmentId(jwForm.getTransferOfficerDepartmentId());
        
        CompositeResponse compositeResponse = MessageUtil.postRequest(departmentEvent);

        return MessageUtil.compositeToCollection(compositeResponse, AgencyResponseEvent.class);
    }

    static public Map fetchSchoolDistrictMap(List districts, Map districtMap)
    {
        GetSchoolDistrictsEvent getEvent = (GetSchoolDistrictsEvent) EventFactory
                .getInstance(JuvenileControllerServiceNames.GETSCHOOLDISTRICTS);

        CompositeResponse composite = MessageUtil.postRequest(getEvent);

        List schools = null;

        List districtsList = MessageUtil.compositeToList(composite, JuvenileSchoolDistrictCodeResponseEvent.class);

        Iterator i = districtsList.iterator();

        while (i.hasNext())
        {
            JuvenileSchoolDistrictCodeResponseEvent event = (JuvenileSchoolDistrictCodeResponseEvent) i.next();
            if(!"Y".equalsIgnoreCase( event.getInactiveInd())){
        	
        	Object bucketObj = districtMap.get(event.getDistrictCode());
                if (bucketObj == null)
                {
                    schools = new ArrayList(15);
                    districtMap.put(event.getDistrictCode(), schools);
                    districts.add(event);
                }
                else
                {
                    schools = (List) bucketObj;
                }
                schools.add(event);
            }            
        }

        Collections.sort(districts);

        i = districtMap.values().iterator();
        while (i.hasNext())
        {
            schools = (List) i.next();
            Collections.sort(schools);
        }

        return districtMap;
    }

    public static List filterAssociates(CompositeResponse aResponse)
    {
        List associates = new ArrayList();
        // mother
        List associatesList = MessageUtil.compositeToList(aResponse, JuvenileAssociateResponseEvent.class);
        Iterator i = associatesList.iterator();

        while (i.hasNext())
        {
            JuvenileAssociateResponseEvent associateResponse = (JuvenileAssociateResponseEvent) i.next();
            JuvenileAssociateBean associateBean = new JuvenileAssociateBean();
            associateBean.populateFromEventAttributes(associateResponse);
            AddressResponseEvent addressResponse = (AddressResponseEvent) associateResponse.getAddress();
            if (addressResponse != null)
            {
                associateBean.insertAddress(addressResponse);
            }
            associates.add(associateBean);
        }

        return associates;
     }
    
    public static List<JuvenileAssociateBean> getAssociates(String juvNumber){	

        List associates = new ArrayList();
        
        List<MemberList> associatesList = getResponsibleAdults(juvNumber);
        
        if(associatesList.size() > 0){
            
            Collection sortedAssociatesList = getSortedByGuardian(associatesList);
            Iterator i = sortedAssociatesList.iterator();          
                     
            while (i.hasNext())
            {               

                MemberList member = (MemberList)i.next();
                
                if(member != null){
                    
                    	JuvenileAssociateBean associateBean = new JuvenileAssociateBean();

                	associateBean.setAssociateName(new Name(member.getMemberName().getFirstName(), member.getMemberName().getMiddleName(), member.getMemberName().getLastName()));
                	associateBean.setAssociateNum(member.getMemberNumber());
                	associateBean.setRelationshipToJuvenile(member.getRelationshipToJuv());
                	associateBean.setRelationshipToJuvenileId(member.getRelationshipToJuvId());
                	associateBean.setDateOfBirthString(member.getDateOfBirth());
                	associateBean.setSsn(member.getSocialSecurity());
                	associateBean.setDlNumber(member.getDriverLicenseNum());
                	associateBean.setDlState(member.getDriverLicenseState());
                	associateBean.setDlStateId(member.getDriverLicenseStateId());
                	 
                    associates.add(associateBean);
                } 
    	    
            }   
            
        } 
        
        //add dummy family member - used for serving warrant to family member not listed in constellation
        associates.add(DummyFamilyMember());
        
        return associates;
	
    }
    
    public static JuvenileAssociateBean DummyFamilyMember(){
	
	JuvenileAssociateBean associateBean = new JuvenileAssociateBean();
	  
	associateBean.setAssociateName(new Name("FirstName-Unknown", "MiddleName-Unknown", "LastName-Unknown"));
	associateBean.setAssociateNum("1111111");
	associateBean.setRelationshipToJuvenile("Other-Relative");
	associateBean.setRelationshipToJuvenileId("OtherRelativeId");
	associateBean.setDateOfBirthString("");
	
	return associateBean;      
    }
    
    public static FamilyMemberDetailResponseEvent getFamilyMemberDetails(String memberNumber){
	
	 // Sending PD Request Event
        GetFamilyMemberDetailsEvent event = (GetFamilyMemberDetailsEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
        event.setMemberNum(memberNumber);
        CompositeResponse response = MessageUtil.postRequest(event);

        FamilyMemberDetailResponseEvent resp = (FamilyMemberDetailResponseEvent) MessageUtil.filterComposite(response,
                FamilyMemberDetailResponseEvent.class);
        
        return resp;
    }
    
    public static List getFamilyMemberAddresses(String memberNumber){
	
	GetFamilyMemberAddressEvent event = (GetFamilyMemberAddressEvent)
		EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERADDRESS);
            event.setMemberNum(memberNumber);
            
            CompositeResponse response = MessageUtil.postRequest(event);
            Map dataMap = MessageUtil.groupByTopic(response);
            
            ArrayList addressList = new ArrayList();
            if( dataMap != null )
            {
                Collection addresses = (Collection)dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_ADDRESS_TOPIC);
                
                if( addresses != null && addresses.size() > 0 )
                {
                	AddressResponseEvent responseEvt;
                	Iterator iter = addresses.iterator();
                	while( iter.hasNext() )
                	{
                		responseEvt = (AddressResponseEvent)iter.next();
                		if( responseEvt != null )
                		{
                			JuvenileMemberForm.MemberAddress myAddress = 
                					UIJuvenileHelper.getJuvMemberFormMemberAddresFROMAddrRespEvt(responseEvt);
                			if( myAddress != null )
                			{              			                  			    
                			    addressList.add(myAddress);
                		        }                			
                		}
                    	} // while
                 }
            
            }
            
            return addressList;
    }
    
    public static List getFamilyMemberContacts(String memberNumber){
	
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

        // Sending PD Request Event
        GetFamilyMemberContactEvent event = (GetFamilyMemberContactEvent) EventFactory
                .getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERCONTACT);
        event.setMemberId(memberNumber);

        // Getting PD Response Event
        CompositeResponse response = MessageUtil.postRequest(event);
        // Perform Error handling
        Map dataMap = MessageUtil.groupByTopic(response);
        FamilyMemberPhoneResponseEvent responseEvt;
        FamilyMemberEmailResponseEvent emailResponseEvt;
        MemberContact myContact;
        
        List contactList = new ArrayList();
        if (dataMap != null)
        {
            Collection contacts = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_CONTACT_TOPIC);
            if (contacts != null && contacts.size() > 0)
            {
                Iterator iter = contacts.iterator();
                while (iter.hasNext())
                {
                    responseEvt = (FamilyMemberPhoneResponseEvent) iter.next();
                    if (responseEvt != null)
                    {
                        myContact = UIJuvenileHelper.getJuvMemberFormMemberContactFROMContactRespEvt(responseEvt);
                        if (myContact != null)
                            contactList.add(myContact);
                    }
                }
            }
            contacts = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_EMAIL_TOPIC);
            if (contacts != null && contacts.size() > 0)
            {
                Iterator iter = contacts.iterator();
                while (iter.hasNext())
                {
                    emailResponseEvt = (FamilyMemberEmailResponseEvent) iter.next();
                    if (emailResponseEvt != null)
                    {
                        myContact = UIJuvenileHelper.getJuvMemberFormMemberContactFROMEmailRespEvt(emailResponseEvt);
                        if (myContact != null)
                            contactList.add(myContact);
                    }
                }
            }
        }
        
        return contactList;
        
    }
    
    public static OfficerProfile GetOfficerByWarrantId(String warrantId){
	
	Integer juvNumber = 0;
	Integer referralNumber = 0;
	String OfficerId = null;
	OfficerProfile officer = null;
	
	if(warrantId != null && !warrantId.equals("")){
	    
	
    	GetJuvenileWarrantsForViewEvent jwRequestEvent = (GetJuvenileWarrantsForViewEvent) EventFactory
    		.getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTSFORVIEW);
    	
    	jwRequestEvent.setWarrantNum(warrantId);
    	
    	CompositeResponse response = MessageUtil.postRequest(jwRequestEvent);
    	
    	List<JuvenileWarrantResponseEvent> warrants = new ArrayList();
    	
    	warrants = MessageUtil.compositeToList(response, JuvenileWarrantResponseEvent.class);
    	
    	Iterator warrantIterator = warrants.iterator();
    	
    	if (warrants != null && warrantIterator.hasNext()) {
    		// process juvenile warrant data
    		JuvenileWarrantResponseEvent jwResponseEvent = (JuvenileWarrantResponseEvent) warrantIterator.next();
    		
    		//re-initializing juvNumber and referralNumber in the event that they've been set to null as a result of the operation above
    		if(jwResponseEvent != null && (jwResponseEvent.getJuvenileNum() != null || !jwResponseEvent.getJuvenileNum().equals("")) && 
    			(jwResponseEvent.getReferralNumber() != null || !jwResponseEvent.getReferralNumber().equals(""))){
    		    
        		juvNumber = jwResponseEvent.getJuvenileNum();
        		referralNumber = jwResponseEvent.getReferralNumber();
        		
    		} else {
    		    
        		juvNumber = 0;
        		referralNumber = 0;
    		}
    		
    	}
	
	
	if(juvNumber > 0 && referralNumber > 0){
	    
	    GetCasefileWithReferralsEvent req = (GetCasefileWithReferralsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCASEFILEWITHREFERRALS);
	    req.setJuvenileNum(juvNumber.toString());
	    req.setReferralNum(referralNumber.toString());
	    
	    List casefiles = MessageUtil.postRequestListFilter(req, JuvenileCasefileReferralDetailResponseEvent.class);
	    
	    Iterator casefilesIter = casefiles.iterator();
	    
	    if(casefilesIter.hasNext()){
		
		JuvenileCasefileReferralDetailResponseEvent responseEvent = (JuvenileCasefileReferralDetailResponseEvent)casefilesIter.next();
		
		OfficerId = responseEvent.getAssignOfficerId();
	    }
	    
	}
	
	if(OfficerId != null && !OfficerId.equals("")){	    
	
        	Iterator<OfficerProfile> officers = OfficerProfile.findAll("logonId", OfficerId);
        	    if (officers.hasNext())
        	    {
        		while (officers.hasNext())
        		{
        		    OfficerProfile jpoOfficer = officers.next();
        		    String status = jpoOfficer.getStatusId();
        		    if (status != null && status.equalsIgnoreCase("A"))
        		    {
        			officer = jpoOfficer;
        		    }
        		}
        	    }  	
		}			  
		    
	}
	
	return officer;
	
    }
    
    public static void SendEmailWarrantAddressUpdate(JuvenileAssociateBean associate, JuvenileWarrantForm jwForm){
	
	String firstName = "";
	String lastName = "";
	String relationship = "";
	
	if(associate == null){
	    
	    firstName = "FirstName-Unknown";
	    lastName = "LastName-Unknown";
	    relationship = "Other-Relative";	    
	} else {
	    
	    firstName = associate.getAssociateName().getFirstName();
	    lastName = associate.getAssociateName().getLastName();
	    relationship = associate.getRelationshipToJuvenile();
	}
	
	if(jwForm != null){	    

		  StringBuilder sbMessage = new StringBuilder();    

		      sbMessage.append("\n");
		      sbMessage.append(System.getProperty("line.separator"));
		      sbMessage.append("Warrant service (warrant Id: " + jwForm.getWarrantNum() + ") was completed at a different address for ");
		      sbMessage.append(firstName + " " + lastName + ", " + relationship + " of youth ");
		      sbMessage.append(jwForm.getJuvenileName() + "(" + jwForm.getJuvenileNum() + ").");
		      sbMessage.append(" Please note the details below and update your records accordingly.");
		      sbMessage.append(System.getProperty("line.separator"));		      
		      sbMessage.append(System.getProperty("line.separator"));
		      if(jwForm.getApartmentNum() != null && !jwForm.getApartmentNum().equals("")){
			  sbMessage.append(jwForm.getStreetNum() + " " + jwForm.getStreetName() + "  Apt " + jwForm.getApartmentNum());
		      } else {
			  sbMessage.append(jwForm.getStreetNum() + " " + jwForm.getStreetName());
		      }	      
		      sbMessage.append(System.getProperty("line.separator"));
		      sbMessage.append(jwForm.getCity() + ", " + jwForm.getState() + " " + jwForm.getZipCode());
		      sbMessage.append(System.getProperty("line.separator"));
		      sbMessage.append(System.getProperty("line.separator"));	     

		    String emailDataCorrections = "Data.Corrections@hcjpd.hctx.net";
		    OfficerProfile jpOfficer = UIJuvenileWarrantHelper.GetOfficerByWarrantId(jwForm.getWarrantNum());		    
		    String emailJPOOfficer = null;
		    if(jpOfficer != null){
			emailJPOOfficer = jpOfficer.getEmail();
		    }		    
		    
		    List<String> emailList = new ArrayList<String>();
		    
		    emailList.add(emailDataCorrections);
		    emailList.add(emailJPOOfficer);
		    
		    for(String email: emailList){
			
			if(email != null && !email.equals("")){
			    
			    SendEmailEvent sendEmailEvent = new SendEmailEvent();
		        	sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
		        	UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, email); 
		        	sendEmailEvent.setSubject(jwForm.getJuvenileName() + "(" + jwForm.getJuvenileNum() + ") ");

		        	sendEmailEvent.setMessage(sbMessage.toString());
				
		        	CompositeResponse res = MessageUtil.postRequest(sendEmailEvent);
		        	MessageUtil.processReturnException( res ) ;
			}
		    }	    
		}
    }
    
    public static JuvenileAssociateBean GetJuvenileFamilyMemberById(String juvNumber, String associateNum){
	
	JuvenileAssociateBean famMember = null;
	
	if(juvNumber != null && !juvNumber.equals("") && associateNum != null && !associateNum.equals("")){
	    
	    List<JuvenileAssociateBean> familyMemberList = new ArrayList<JuvenileAssociateBean>();
	        familyMemberList = UIJuvenileWarrantHelper.getAssociates(juvNumber);
	        
	        Iterator<JuvenileAssociateBean> iter = familyMemberList.iterator();      
	        
	      	while(iter.hasNext()) {
	      	    
	      	JuvenileAssociateBean famAssociate = (JuvenileAssociateBean) iter.next();
	      		
	      		if(famAssociate != null && famAssociate.getAssociateNum() != null && !famAssociate.getAssociateNum().equals("")){
	          		if(famAssociate.getAssociateNum().equals(associateNum)){          		    
	          		   famMember = famAssociate;
	          		    break;
	          		}
	      		}
	      		
	      	}	      	
	}	 
	      	
	 return famMember;
    }

    /**
     * Checks to see if a InvalidWarrantStageErrorEvent was return. If so,
     * returns a message about the warrant state.
     * 
     * @param response
     * @return error message
     */
    public static String getWarrantStageError(CompositeResponse response)
    {
        InvalidWarrantStageErrorEvent stageError = (InvalidWarrantStageErrorEvent) MessageUtil.filterComposite(response,
                InvalidWarrantStageErrorEvent.class);
        String error = null;
        if (stageError != null)
        {
            StringBuffer buffer = new StringBuffer();
            buffer.append(" The current status of Warrant No = " + stageError.getWarrantNum() + " is ( ");
            if (stageError.getWarrantStatus() != null)
            {
                buffer.append(" Warrant Status = " + stageError.getWarrantStatus());
            }
            if (stageError.getWarrantActivationStatus() != null)
            {
                buffer.append(" Warrant Activation Status = " + stageError.getWarrantActivationStatus());
            }
            if (stageError.getWarrantAcknowledgeStatus() != null)
            {
                buffer.append(" Warrant Acknowledge Status = " + stageError.getWarrantAcknowledgeStatus());
            }
            if (stageError.getWarrantSignatureStatus() != null)
            {
                buffer.append(" Warrant Signature Status = " + stageError.getWarrantSignatureStatus());
            }
            buffer.append(" ) ");

            error = buffer.toString();
        }
        return error;
    }

    public static CompositeResponse fetchJJSWarrantData(String referralNum, String juvenileNum, String petitionNum,
            String warrantType)
    {
        // TODO Put this in a helper
        GetJJSDataEvent requestEvent = (GetJJSDataEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETJJSDATA);

        requestEvent.setJuvenileNum(juvenileNum);
        requestEvent.setReferralNum(referralNum);
        requestEvent.setPetitionNum(petitionNum);
        requestEvent.setWarrantType(warrantType);

        return MessageUtil.postRequest(requestEvent);
    }

    public static CompositeResponse fetchJJSPetitions(String juvenileNum, String petitionNum, String referralNum)
    {
        GetJJSPetitionsEvent event = (GetJJSPetitionsEvent) EventFactory
                .getInstance(JuvenileWarrantControllerServiceNames.GETJJSPETITIONS);
        event.setJuvenileNum(juvenileNum);
        event.setPetitionNum(petitionNum);
        event.setReferralNum(referralNum);
        return MessageUtil.postRequest(event);
    }
    
    
    private static List<MemberList> getResponsibleAdults(String juvNumber){
	
	 List<MemberList> associates = new ArrayList();
	
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

   	// Send PD Request Event
   	GetFamilyConstellationsEvent event = (GetFamilyConstellationsEvent)
   			EventFactory.getInstance( JuvenileFamilyControllerServiceNames.GETFAMILYCONSTELLATIONS);
   	event.setJuvenileNum(juvNumber);
   	dispatch.postEvent(event);

   	// Get PD Response Event	
   	CompositeResponse response = (CompositeResponse) dispatch.getReply();

   	// Perform Error handling
   	MessageUtil.processReturnException(response);

   	Map dataMap = MessageUtil.groupByTopic(response);   	
   	
   	if( dataMap != null )
   	{			
   		Collection families  = (Collection)dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC); 				

   		Collection currentFamMembers = (Collection)dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
   		JuvenileFamilyForm.Constellation newFamily = new JuvenileFamilyForm.Constellation();
   				
		UIJuvenileHelper.setJuvFamilyFormFROMMemberListRespEvt(newFamily,currentFamMembers);
					
		Collection memberList = newFamily.getMemberList();
					
		Iterator famIter = memberList.iterator();
					
		JuvenileAssociateBean associateBean = new JuvenileAssociateBean();
					
		while(famIter.hasNext()){
					    
		    MemberList member = (MemberList)famIter.next();
					    
		    associates.add(member);
		}

   	}
   	
   	return associates;
   	
     }
    
    private static Collection getSortedByGuardian( Collection memberList )
	{
		List guardianList = new ArrayList();
		List nonGuardianList = new ArrayList();
		List sortedList = new ArrayList();
		Iterator iter =  memberList.iterator();
		Map guardianMap = new HashMap();
		Map nonGuardianMap = new HashMap();

		while(iter.hasNext()) {
			MemberList myMember = (MemberList)iter.next();
			if(myMember.isGuardian())
			{
				//guardianList.add(myMember);	
				//myMember.getMemberName().getFirstName();		
	            String memerLastName = myMember.getMemberName().getLastName();
	            String memerFirstName = myMember.getMemberName().getFirstName();
				String memberFullName = memerLastName + memerFirstName+myMember.getMemberNumber();
				guardianMap.put(memberFullName, myMember);
				//guardianMap.put(myMember.getMemberNumber(), myMember);
				
			}else{
				//nonGuardianList.add(myMember);	
				
				String memerLastName = myMember.getMemberName().getLastName();
				 String memerFirstName = myMember.getMemberName().getFirstName();
				String memberFullName = memerLastName + memerFirstName+myMember.getMemberNumber();
				nonGuardianMap.put(memberFullName, myMember);
				//nonGuardianMap.put(myMember.getMemberNumber(), myMember);
			}
		}
		/*Sorting both the list seperately and appending*/
		SortedSet<String> guardianKeys = new TreeSet<String>(guardianMap.keySet());
		SortedSet<String> nonGuardianKeys = new TreeSet<String>(nonGuardianMap.keySet());
		for (String key : guardianKeys) { 
			MemberList currMember = (MemberList)guardianMap.get(key);
			guardianList.add(currMember);
		}
		for (String key : nonGuardianKeys) { 
			MemberList currMember = (MemberList)nonGuardianMap.get(key);
			nonGuardianList.add(currMember);
		}
		/*Collections.sort((List) guardianList);
		Collections.sort((List) nonGuardianList);*/
		sortedList.addAll(guardianList);
		sortedList.addAll(nonGuardianList);
		return sortedList;
	}
    
    
}
