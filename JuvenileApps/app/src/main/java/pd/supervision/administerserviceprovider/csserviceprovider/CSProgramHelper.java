/*
 * Created on Jan 15, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.csserviceprovider;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import naming.CSAdministerServiceProviderConstants;
import naming.CSCAssessmentConstants;
import pd.common.util.StringUtil;
import pd.supervision.Group;
import pd.supervision.administercaseload.CaseAssignmentOrder;
import pd.supervision.administerprogramreferrals.CSProgramReferral;
import pd.supervision.managetask.PDTaskHelper;
import pd.supervision.supervisionoptions.StringSet;
import pd.supervision.supervisionstaff.Organization;
import messaging.administercaseload.GetActiveCasesByCaseEvent;
import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.csserviceprovider.ActiveIncarcerationProgramEvent;
import messaging.csserviceprovider.ProgramUnitProgramQueryProgramEvent;
import messaging.csserviceprovider.SaveProgramEvent;
import messaging.csserviceprovider.ValidateCSProgramEvent;
import messaging.csserviceprovider.reply.CSProgramLocationResponseEvent;
import messaging.csserviceprovider.reply.CSProgramResponseEvent;
import messaging.csserviceprovider.reply.DuplicateServiceProviderProgramResponseEvent;
import messaging.managetask.CreateCSTaskEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.util.DateUtil;
import mojo.km.utilities.CollectionUtil;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSProgramHelper 
{

    /**
     * Retrieve the specified service provider program
     * @param programId
     * @return
     */
    public static CSProgram getProgram(String programId)
    {
        return CSProgram.find(programId);
    }//end of getProgram()

    /**
     * Update program status
     * @param spProgram
     * @param saveProgramEvent
     */
    private static void updateProgramStatus(CSProgram spProgram, 
            									SaveProgramEvent saveProgramEvent)
    {        
        	//update program status history if status has changed
        String event_prog_status = saveProgramEvent.getProgramStatus();
        if ( event_prog_status != null && !event_prog_status.equals(spProgram.getStatusCode()) ) {
            CSProgramStatusHistory prog_status_hist = new CSProgramStatusHistory();
            prog_status_hist.setProgramId(spProgram.getOID());
            prog_status_hist.setStatusCode(saveProgramEvent.getProgramStatus());
            prog_status_hist.setStatusChangeDate(saveProgramEvent.getStatusChangeDate());
            prog_status_hist.setStatusChangeComments(saveProgramEvent.getStatusChangeComments());
            	//save program status history to database
            prog_status_hist.bind();
            	//update program status fields
    	    spProgram.setStatusCode(saveProgramEvent.getProgramStatus());
    	    spProgram.setStatusChangeDate(saveProgramEvent.getStatusChangeDate());
    	    spProgram.setStatusChangeComments(saveProgramEvent.getStatusChangeComments());
        }
    }//end of updateProgramStatus()
    
    /**
     * Save a service provider program using the supplied event information
     * @param saveProgramEvent
     */
    public static CSProgram saveServiceProviderProgram(SaveProgramEvent saveProgramEvent)
    {
        CSProgram spProgram = null;
        boolean update_program = false;
        	//check if programId is set
		if ( StringUtil.isEmpty(saveProgramEvent.getProgramId()) ) {
				// create a new service provider program and initialize status to pending
		    spProgram = new CSProgram();
		    //spProgram.setStatusCode(CSAdministerServiceProviderConstants.PENDING_PROG_STATUS);
		    spProgram.setStatusCode(saveProgramEvent.getProgramStatus());
		} else {
				// update existing service provider if serviceProviderId is set
		    spProgram = 
		        CSProgram.find(saveProgramEvent.getProgramId());
		    update_program = true;
		}
			//check if program is a duplicate of an existing entry
		if (!isDuplicate(saveProgramEvent.getServiceProviderId(), 
		        saveProgramEvent.getProgramIdentifier(), 
		        	saveProgramEvent.getProgramName())
		        	|| update_program) {
		    	//set service provider program properties
		    spProgram.setServiceProviderId(saveProgramEvent.getServiceProviderId());
		    spProgram.setProgramIdentifier(saveProgramEvent.getProgramIdentifier());
		    spProgram.setProgramName(saveProgramEvent.getProgramName());
		    spProgram.setIsContractProgram(saveProgramEvent.isContractProgram());
		    spProgram.setReferralTypeCode(saveProgramEvent.getProgramGroupCode() 
					+ CSAdministerServiceProviderConstants.PROGRAM_SEPARATOR 
					+ saveProgramEvent.getProgramTypeCode());		    
		    spProgram.setProgramHierarchyCode(saveProgramEvent.getProgramGroupCode() 
					+ CSAdministerServiceProviderConstants.PROGRAM_SEPARATOR 
					+ saveProgramEvent.getProgramTypeCode() 
					+ CSAdministerServiceProviderConstants.PROGRAM_SEPARATOR 
					+ saveProgramEvent.getProgramSubTypeCode());
		    spProgram.setProgramStartDate(saveProgramEvent.getProgramStartDate());
		    spProgram.setProgramEndDate(saveProgramEvent.getProgramEndDate());
		    spProgram.setSexSpecificCode(saveProgramEvent.getSexSpecificCode());		    
		    spProgram.setOfficeHours(saveProgramEvent.getOfficeHours());
		    spProgram.setProgramDescription(saveProgramEvent.getProgramDescription());		    		    
		    spProgram.setStatusChangeComments(saveProgramEvent.getStatusChangeComments());		    		    
		    spProgram.setProgramUnitId(saveProgramEvent.getProgramUnitId());
		    spProgram.setIncarcerationConditionId(saveProgramEvent.getIncarcerationConditionId());
		    spProgram.setPrice(Float.parseFloat(saveProgramEvent.getPrice()));
		    if (update_program) {
		        updateProgramStatus(spProgram, saveProgramEvent);
		    }
				//bind program to DB
		    spProgram.bind();
		    	//save program collections
		    saveProgramLocations( spProgram, saveProgramEvent);
		    saveProgramLanguages( spProgram, saveProgramEvent);
				//return updated service provider program
			return spProgram;
		} else {
		    	//post response event condition if duplicate program specified
		    DuplicateServiceProviderProgramResponseEvent 
		    	duplicate_name_response = 
		    	    new DuplicateServiceProviderProgramResponseEvent();
		    duplicate_name_response.setProgramIdentifier(
					saveProgramEvent.getProgramIdentifier());
		    duplicate_name_response.setProgramName(
					saveProgramEvent.getProgramName());
		    duplicate_name_response.setMessage(
		            				"Duplicate Service Provider Program Specified");
            EventManager.getSharedInstance(EventManager.REPLY).postEvent(duplicate_name_response);		    
		}
			//return null in the event of a processing exception
		return null;        
    }//end of saveServiceProviderProgram()

    /**
     * Save new program locations and change old program locations status
     * @param spProgram
     * @param saveProgramEvent
     */
    private static void saveProgramLocations(CSProgram spProgram, SaveProgramEvent saveProgramEvent)
    {
    	
    	Object[] oldProglocations = saveProgramEvent.getOldProgLocations();
        
    	int num_locations = 0;
    	if ( oldProglocations != null ){
    		
    		num_locations = oldProglocations.length;
    	}

        //iterate thru program location objects and populate list of old program location ids
        ArrayList oldLocations_ids = new ArrayList(num_locations);
        ArrayList invalidLocations_ids = new ArrayList(num_locations);
        for ( int i=0;i<num_locations;i++ ) {
        	String validLocStatus = ((CSProgramLocation)oldProglocations[i]).getValidLocStatus();
         	if ( StringUtils.isNotEmpty(validLocStatus) && validLocStatus.equals("A") ) {
         		oldLocations_ids.add(((CSProgramLocation)oldProglocations[i]).getLocationId());
         	} else {
         		invalidLocations_ids.add(((CSProgramLocation)oldProglocations[i]).getLocationId());
         	}
        }
    		//retrieve saved program locations and locations specified in event
        StringSet oldLocations = new StringSet(oldLocations_ids);
        StringSet invalid_locations = new StringSet(invalidLocations_ids);
        StringSet event_prog_locations = new StringSet(saveProgramEvent.getProgramLocations());
       
        	//determine new locations and locations to activate or inactivate using set arithmetic
        StringSet inactivate_Locations = oldLocations.complement(event_prog_locations);
        StringSet activate_Locations = event_prog_locations.intersection(invalid_locations);
        StringSet eventProgLocations = event_prog_locations.complement(activate_Locations);
        StringSet new_locations = eventProgLocations.complement(oldLocations);
    	if ( new_locations.size() > 0 ) {
        	//insert all new program locations
        	insertNewProgramLocations(spProgram, new_locations);
        }
        	//activate old program locations
        Iterator activate = activate_Locations.iterator();
        List activateList = CollectionUtil.iteratorToList(activate);
        ArrayList activateLoc_oids = new ArrayList(activateList.size());
	    if ( activateList.size() > 0 ) {
	    	for (int h=0; h<activateList.size(); h++) {
	    	String this_location = (String) activateList.get(h);
		    	for ( int i=0;i<num_locations;i++ ) {
		    		String validLocStatus = ((CSProgramLocation)oldProglocations[i]).getValidLocStatus();
		    		String locationId = ((CSProgramLocation)oldProglocations[i]).getLocationId();
		         	if ( StringUtils.isNotEmpty(locationId) && locationId.equals(this_location) ) {
		         		if ( StringUtils.isNotEmpty(validLocStatus) && validLocStatus.equals("I") ) {
		         			activateLoc_oids.add(((CSProgramLocation)oldProglocations[i]).getProgramLocationId());
		         		}
		         	}
		        }
	    	}
	    	StringSet activate_locations = new StringSet( activateLoc_oids );
	    	updateProgramLocations(activate_locations, "A");
	    }
	    //inactivate old program locations
	    Iterator inactivate = inactivate_Locations.iterator();
	    List inactivateList = CollectionUtil.iteratorToList(inactivate);
        ArrayList inactivateLoc_oids = new ArrayList(inactivateList.size());
	    if (inactivateList.size() > 0){
	    	for (int h=0; h<inactivateList.size(); h++) {
	    	String this_location = (String) inactivateList.get(h);
		    	for (int i=0;i<num_locations;i++)
		        {
		    		String validLocStatus = ((CSProgramLocation)oldProglocations[i]).getValidLocStatus();
		    		String locationId = ((CSProgramLocation)oldProglocations[i]).getLocationId();
		         	if ( StringUtils.isNotEmpty(locationId) && locationId.equals(this_location) ) {
		         		if ( StringUtils.isNotEmpty(validLocStatus) && validLocStatus.equals("A") ) {
		         			inactivateLoc_oids.add(((CSProgramLocation)oldProglocations[i]).getProgramLocationId());
		         		}
		         	}
		        }
	    	}
	    	StringSet inactivate_locations = new StringSet(inactivateLoc_oids);
	    	updateProgramLocations(inactivate_locations, "I");
	    }
	   /* if(inactivateLoc_oids.size() > 0){
		    // create notification tasks for inactive locations
	    	createNoticeCsTask( saveProgramEvent, inactivate_Locations );
		}else if(saveProgramEvent.getProgramStatus().equals("I")){
		    // create notification tasks for inactive program
			createNoticeCsTask( saveProgramEvent, inactivate_Locations );
		}else if(saveProgramEvent.getProgramStatus().equalsIgnoreCase("S")){
		    // create notification tasks for suspended program
			createNoticeCsTask( saveProgramEvent, inactivate_Locations );
		}else if( saveProgramEvent.getProgramStatus().equals("UI") ) {
		    // create notification tasks for programs under investigation
			createNoticeCsTask( saveProgramEvent, inactivate_Locations );
		}*/
    }//end of saveProgramLocations()
    
    public static void createNoticeCsTask( SaveProgramEvent saveProgramEvent, StringSet inactivate_Locations ) {
    	
    	Object[] oldProglocations = saveProgramEvent.getOldProgLocations();
        
    	int num_locations = 0;
    	if ( oldProglocations != null ){
    		
    		num_locations = oldProglocations.length;
    	}
        //iterate thru program location objects and populate list of old program location ids
       StringSet progLocationIds = new StringSet();
        for ( int i=0;i<num_locations;i++ ) {
        	String progLocationId = ((CSProgramLocation)oldProglocations[i]).getProgramLocationId();
        	String validLocStatus = ((CSProgramLocation)oldProglocations[i]).getValidLocStatus();
         	if ( StringUtils.isNotEmpty(progLocationId) && StringUtils.isNotEmpty(validLocStatus) && validLocStatus.equals("A")) {
         		progLocationIds.add(progLocationId);
         	} 
        }

        List referral_list = new ArrayList();
        HashMap assStaffPosId_map = new HashMap();
		Iterator locationIds = progLocationIds.iterator();
		List locationIdsList = CollectionUtil.iteratorToList(locationIds);
	    if ( locationIdsList.size() > 0 ) {
	    	for (int h=0; h<locationIdsList.size(); h++) {
	    		String progLocationId = (String) locationIdsList.get(h);
					//retrieve list of referrals
				referral_list.addAll( CollectionUtil.iteratorToList( CSProgramReferral.findAll( "programLocationId", progLocationId ) ) );
					//convert to more manageable List
				
	    	}
	    	for (int x=0; x<referral_list.size(); x++) {
	    		
	    		CSProgramReferral referral = (CSProgramReferral) referral_list.get( x );
	    		String crimCase = referral.getCriminalCaseId();
	    		
	    		// find case assignment
	    		
	    		GetActiveCasesByCaseEvent reqEvt = new GetActiveCasesByCaseEvent();
	    		reqEvt.setCriminalCaseId( crimCase );
	    		
	    		Iterator iter = CaseAssignmentOrder.findAllByEvent( reqEvt );
	    		while ( iter.hasNext() ){
	    			
	    			CaseAssignmentOrder caseAssignment = (CaseAssignmentOrder) iter.next();
	                ICaseAssignment assignmentBean = caseAssignment.detailsValueObject();
	                String assignedStaffPositionId = assignmentBean.getAssignedStaffPositionId();
	                assStaffPosId_map.put(assignedStaffPositionId, assignedStaffPositionId);
	    		}
	    	}
	    	Set assStaffPosId_set = assStaffPosId_map.entrySet();
	    	Iterator assStaffPosId_iter = assStaffPosId_set.iterator();
	    	while ( assStaffPosId_iter.hasNext() ) {
			    CreateCSTaskEvent createCSTaskEvent = new CreateCSTaskEvent();     
		        //due date
		        Calendar calendar = Calendar.getInstance();
		        Date today = calendar.getTime();
		        String changeDate = DateUtil.dateToString(today, DateUtil.DATE_FMT_1);
		        // Get the weekday and print it 
		        int weekday = calendar.get(Calendar.DAY_OF_WEEK); 
		        
		        switch ( weekday) {
		                        
		        case 5:
		                        calendar.add(Calendar.DATE, 4);
		                        break;
		                        
		        case 6:
		                        calendar.add(Calendar.DATE, 4);
		                        break;
		                        
		        default:
		                        calendar.add(Calendar.DATE, 2);
		                        break;
		                        
		        }
		        Map.Entry staffPositionId = (Map.Entry)assStaffPosId_iter.next();
		        createCSTaskEvent.setStaffPositionId((String) staffPositionId.getValue());
		        createCSTaskEvent.setWorkGroupId((String) staffPositionId.getValue());
		        createCSTaskEvent.setDueDate( calendar.getTime() );
		        createCSTaskEvent.setStatusId( "O" );
		        
		        //createCSTaskEvent.setScenario( CaseloadConstants.REALLOCATE_SUPERVISOR_PAGEFLOW );
		        
		        StringBuffer subject2 = new StringBuffer();
		        String programName = saveProgramEvent.getProgramName();
		        createCSTaskEvent.setSuperviseeName( programName );
		        createCSTaskEvent.setCaseAssignId( saveProgramEvent.getProgramId() );
		        StringBuffer taskSubject = new StringBuffer();
		    	taskSubject.append(programName);
		    	StringBuffer taskText = new StringBuffer();
		    	taskText.append( programName );
		        if(saveProgramEvent.getProgramStatus().equals("I")){
				    // create notification tasks for inactive program
		        	taskSubject.append(" Inactivated");
		        	taskText.append( " has been inactivated on ");
		            taskText.append( changeDate );
		            taskText.append( ".  Please exit all supervisees assigned to this program." );
			        subject2.append("Check supervisees in this program.");
		            createCSTaskEvent.setTopic( CSCAssessmentConstants.CS_CASELOAD_BY_PROG_REFER_PROV );
		            createCSTaskEvent.setScenario( CSCAssessmentConstants.PAGEFLOW_SCENERIO );
					
				}else if(saveProgramEvent.getProgramStatus().equalsIgnoreCase("S")){
				    // create notification tasks for suspended program
					taskSubject.append(" Suspended");
					taskText.append( " has been suspended on ");
		            taskText.append( changeDate );
		            taskText.append( ".  No future referrals are allowed to this program.  Please investigate court policy." );
			        subject2.append("Check supervisees in this program.");
		            createCSTaskEvent.setTopic( CSCAssessmentConstants.CS_CASELOAD_BY_PROG_REFER_PROV );
		            createCSTaskEvent.setScenario( CSCAssessmentConstants.PAGEFLOW_SCENERIO );
					
				}else if( saveProgramEvent.getProgramStatus().equals("UI") ) {
				    // create notification tasks for programs under investigation
					taskSubject.append(" Under Investigation");
					taskText.append( " has been placed under investigation on ");
		            taskText.append( changeDate );
		            taskText.append( "." );
			        
					
				}
		        createCSTaskEvent.setSubject2( subject2.toString() );
		        createCSTaskEvent.setTaskSubject( taskSubject.toString() );
		        createCSTaskEvent.setTastText( taskText.toString() );
		        PDTaskHelper.createCSTask(createCSTaskEvent);
	    	}
	    }
        
    }
    
    /**
     * Insert new program locations
     * @param spProgram
     * @param newLocations
     */
    private static void  insertNewProgramLocations(CSProgram spProgram, StringSet newLocations)
    {
		Object[] new_locations_array = newLocations.toArray();
		for (int i=0;i<newLocations.size();i++) {
		    	//create program location object for associating with program
		    CSProgramLocation this_location = 
		        CSProgramLocationHelper.createProgramLocation(spProgram.getOID(), 
		                					(String)new_locations_array[i]);
		    	//add program location to program
		    spProgram.insertProgramLocation(this_location);
		}        
    }//end of insertNewProgramLocations()
    
    /**
     * Update program locations
     * @param updateLocations
     * @param validLocStatus
     */
    private static void  updateProgramLocations(StringSet updateLocations, String validLocStatus)
    {
		Object[] update_locations_array = updateLocations.toArray();
		for (int i=0;i<updateLocations.size();i++) {
		    	//update program location object for valid location status
		    CSProgramLocationHelper.updateProgramLocation( validLocStatus, (String) update_locations_array[i]);
		}        
    }//end of updateProgramLocations()
        
    /**
     * Save program languages
     * @param spProgram
     * @param saveProgramEvent
     */
    private static void saveProgramLanguages(CSProgram spProgram, SaveProgramEvent saveProgramEvent)
    {
        	//retrieve saved program languages and  languages specified in event
        StringSet db_prog_languages = new StringSet(spProgram.getProgramLanguageCodes());
        StringSet event_prog_languages = new StringSet(saveProgramEvent.getProgramLanguages());
        	//determine new languages and languages to remove using set arithmetic
		StringSet new_languages = event_prog_languages.complement(db_prog_languages);
		StringSet remove_languages = db_prog_languages.complement(event_prog_languages);
			//insert all new program languages
		insertNewProgramLanguages(spProgram, new_languages);
			//remove all old program languages
		removeOldProgramLanguages(spProgram, remove_languages);
    }//end of saveProgramLanguages()

    /**
     * Insert new program languages
     * @param spProgram
     * @param newLanguages
     */
    private static void  insertNewProgramLanguages(CSProgram spProgram, StringSet newLanguages)
    {
		Object[] new_languages_array = newLanguages.toArray();
		for (int i=0;i<newLanguages.size();i++) {
		    CSProgramLanguage this_language = 
		        CSProgramLanguageHelper.createProgramLanguage(spProgram.getOID(), 
    					(String)new_languages_array[i]);
		    spProgram.insertProgramLanguage(this_language);
		}
    }//end of insertNewProgramLanguages()
    
    /**
     * Remove old program languages
     * @param spProgram
     * @param oldLanguages
     */
    private static void  removeOldProgramLanguages(CSProgram spProgram, StringSet oldLanguages)
    {
		Object[] old_languages_array = oldLanguages.toArray();
		for (int i=0;i<oldLanguages.size();i++) {
		    CSProgramLanguage this_language = 
		    	CSProgramLanguage.findByProgramAndLanguage(
		    			spProgram.getOID(),
		    				(String)old_languages_array[i]);
		    
		    if (this_language != null) {
		    	spProgram.removeProgramLanguage(this_language);
		    }
		}
        
    }//end of removeOldProgramLanguages()  
    
	/**
	 * Check if the specified service provider program is a duplicate of an existing one
	 * @param serviceProviderId
	 * @param programIdentifier
	 * @param programName
	 * @return
	 */
	private static boolean isDuplicate(String serviceProviderId, String programIdentifier, String programName)
	{
	    	//initialize event for checking for validity of a service provider program
		ValidateCSProgramEvent validateEvent = new ValidateCSProgramEvent();
		validateEvent.setProgramIdentifier(programIdentifier);
		validateEvent.setProgramName(programName);
		validateEvent.setServiceProviderId(serviceProviderId);
		boolean returnBoolean = false;
	    	//check if any programs exist with the given name
		Iterator sp_programs_iter = CSProgram.findAll(validateEvent);
		if (sp_programs_iter.hasNext()) {
		    	//is a duplicate program
			returnBoolean = true;
		} else {		
				//is not a duplicate program
			returnBoolean = false;
		}
		return returnBoolean;
	}//end of isDuplicate()

	/**
	 * Retrieve Program for specified group2 ID
	 * @param group2_Id
	 * @return
	 */
	public static CSProgram getIncarcerationProgram(String group2_Id)
	{
		ActiveIncarcerationProgramEvent inc_prog_event = 
											new ActiveIncarcerationProgramEvent();
		inc_prog_event.setIncarcerationConditionId(group2_Id);
		inc_prog_event.setStatusCode(CSAdministerServiceProviderConstants.INACTIVE_PROG_STATUS);
		List<CSProgram> program_list = CollectionUtil.iteratorToList(
				CSProgram.findAll(inc_prog_event));
					
		if (program_list.size() > 0) {
				//return program with the specified condition ID if found
			return program_list.get(0);
		} else {		
				//return null indicating no such program found
			return null;
		}
	}//end of getIncarcerationProgram()
	
	/**
	 * Retrieve non-inactive programs for the given program unit program
	 * @param program_id
	 * @param program_unit_id
	 * @return
	 */
	public static CSProgram getNonInactiveProgramUnitProgram(String program_id, String program_unit_id)
	{
		ProgramUnitProgramQueryProgramEvent pup_event = 
											new ProgramUnitProgramQueryProgramEvent();
		pup_event.setProgramId(program_id);
		pup_event.setProgramUnitId(program_unit_id);
		pup_event.setProgramStatus(CSAdministerServiceProviderConstants.INACTIVE_PROG_STATUS);
		
		List<CSProgram> program_list = CollectionUtil.iteratorToList(
				CSProgram.findAll(pup_event));
					
		if (program_list.size() > 0) {
				//return program with the specified program unit if found
			return program_list.get(0);
		} else {		
				//return null indicating no such program found
			return null;
		}
	}//end of getNonInactiveProgramUnitProgram()
	
	/**
	 * Retrieve Program for specified program unit
	 * @param program_unit_id
	 * @return
	 */
	public static CSProgram getProgramUnitProgram(String program_unit_id)
	{
		ProgramUnitProgramQueryProgramEvent prog_unit_prog_event = 
											new ProgramUnitProgramQueryProgramEvent();
		prog_unit_prog_event.setProgramUnitId(program_unit_id);
		
		List<CSProgram> program_list = CollectionUtil.iteratorToList(
				CSProgram.findAll(prog_unit_prog_event));
		if (program_list.size() > 0)
		{
				//return program with the specified program unit ID if found
			return program_list.get(0);
		} else {		
				//return null indicating no such program found
			return null;
		}

	}//end of getProgramUnitProgram()
	
	/**
	 * Determine program group from given program hierarchy code
	 * @param programHierarchyCode
	 * @return
	 */
	public static String retrieveProgramGroupCode( String programHierarchyCode )
	{
		StringTokenizer stg = new StringTokenizer( programHierarchyCode );
		 Map tokMapg = new HashMap();
		 
		 int count = 0;
		 while (stg.hasMoreElements() ) {

			 tokMapg.put( count , stg.nextToken() );
			 count++;
	     }
		 return (String) tokMapg.get(0);
	}//end of retrieveProgramGroupCode()
	
	/**
	 * Determine program type from given program hierarchy code
	 * @param programHierarchyCode
	 * @return
	 */
	public static String retrieveProgramTypeCode(String programHierarchyCode)
	{
	    	
		 StringTokenizer stt = new StringTokenizer( programHierarchyCode );
		 Map tokMapt = new HashMap();
		 
		 int count = 0;
		 while (stt.hasMoreElements() ) {

			 tokMapt.put( count , stt.nextToken() );
			 count++;
	     }
		 return (String) tokMapt.get(1);

	}//end of retrieveProgramTypeCode()

	/**
	 * Determine program type from given program hierarchy code
	 * @param programHierarchyCode
	 * @return
	 */
	public static String retrieveProgramSubtypeCode(String programHierarchyCode)
	{
		StringTokenizer sts = new StringTokenizer( programHierarchyCode );
		 Map tokMaps = new HashMap();
		 
		 int count = 0;
		 while (sts.hasMoreElements() ) {

			 tokMaps.put( count , sts.nextToken() );
			 count++;
	     }
		 return (String) tokMaps.get(2);
	}
	//end of retrieveProgramTypeCode()
	
	/**
	 * Return a program response event using the given program
	 * @param spProgram
	 * @return
	 */
    public static CSProgramResponseEvent getProgramResponseEvent(CSProgram spProgram)
    {
        CSProgramResponseEvent prog_response_event = new CSProgramResponseEvent();
        if (spProgram != null) {
        	//set response event properties
            prog_response_event.setProgramId(spProgram.getOID());
            prog_response_event.setProgramIdentifier(spProgram.getProgramIdentifier());
            prog_response_event.setProgramName(spProgram.getProgramName());
            prog_response_event.setServiceProviderId(spProgram.getServiceProviderId());
            prog_response_event.setContractProgram(spProgram.getIsContractProgram());
            prog_response_event.setReferralTypeCode(spProgram.getReferralTypeCode());
            prog_response_event.setProgramHierarchyCode(spProgram.getProgramHierarchyCode());
            	//determine program group, type, and subtype codes from program hierarchy code
            prog_response_event.setProgramGroupCode(
                    retrieveProgramGroupCode(spProgram.getProgramHierarchyCode()));
            prog_response_event.setProgramTypeCode(
                    retrieveProgramTypeCode(spProgram.getProgramHierarchyCode()));
            prog_response_event.setProgramSubtypeCode(
                    retrieveProgramSubtypeCode(spProgram.getProgramHierarchyCode()));
            prog_response_event.setProgramStartDate(spProgram.getProgramStartDate());
            prog_response_event.setProgramEndDate(spProgram.getProgramEndDate());
            prog_response_event.setSexSpecificCode(spProgram.getSexSpecificCode());
            prog_response_event.setProgramLanguageCodes(spProgram.getProgramLanguageCodes());
            prog_response_event.setProgramLanguages(spProgram.getProgramLanguages());
            prog_response_event.setProgramLocationIds(spProgram.getProgramLocationIds());
            prog_response_event.setProgramLocations(spProgram.getProgramLocations());
            	//retrieve program location objects
            Object[] prog_locations = spProgram.getProgramLocations().toArray();
            prog_response_event.setOldProgLocations(prog_locations);
            int num_locations = prog_locations.length;
            	//iterate thru program location objects and populate list of location ids
            ArrayList location_ids = new ArrayList(num_locations);
            ArrayList progLocation_ids = new ArrayList(num_locations);
            for (int i=0;i<num_locations;i++) {
            	String validLocStatus = ((CSProgramLocation)prog_locations[i]).getValidLocStatus();
            	if ( StringUtils.isNotEmpty(validLocStatus) && validLocStatus.equals("A") ) {
            		location_ids.add(((CSProgramLocation)prog_locations[i]).getLocationId());
            		progLocation_ids.add(((CSProgramLocation)prog_locations[i]).getProgramLocationId());
            	}                 
            }
            	//return location IDs
            prog_response_event.setLocationIds(location_ids);
            prog_response_event.setSelectedProglocationIds(progLocation_ids);
            prog_response_event.setOfficeHours(spProgram.getOfficeHours());
            prog_response_event.setProgramDescription(spProgram.getProgramDescription());
            prog_response_event.setStatusCode(spProgram.getStatusCode());
            prog_response_event.setStatusChangeComments(spProgram.getStatusChangeComments());
            prog_response_event.setStatusChangeDate(spProgram.getStatusChangeDate());
            	//set incarceration condition values
            prog_response_event.setIncarcerationConditionId(spProgram.getIncarcerationConditionId());
            if (!StringUtil.isEmpty(spProgram.getIncarcerationConditionId())) {
                Group incarceration_condition = 
            		Group.find(spProgram.getIncarcerationConditionId());
	            if (incarceration_condition != null) {
	            	prog_response_event.setIncarcerationConditionName(
	            			incarceration_condition.getGroupName());
	            }            	
            }                        
            String program_unit_id = spProgram.getProgramUnitId();
            if (!StringUtil.isEmpty(program_unit_id)) {
                prog_response_event.setProgramUnitId(program_unit_id);            	
                Organization selected_program_unit = Organization.find(spProgram.getProgramUnitId());
                if (selected_program_unit != null) {
                	prog_response_event.setProgramUnitName(selected_program_unit.getDescription());
                	
                	prog_response_event.setDivisionId(selected_program_unit.getParentOrganizationId());
                	if (selected_program_unit.getParentOrganization() != null) {
                		prog_response_event.setDivisionName(selected_program_unit.getParentOrganization().getDescription());
                	}                	
                }            	
            }
            	//represent in currency format after removing $ sign
            	//Workaround to deal with trimming trailing zero in decimal            
            prog_response_event.setPrice(
            		((NumberFormat.getCurrencyInstance().
            				format(spProgram.getPrice())).substring(1)).
            					replace(new StringBuffer(","),new StringBuffer("")));
        }
        	//return populated response event
        return prog_response_event;
    }	
    
    /**
     * Return list of program response events
     * @param programs
     * @return
     */
    public static List getProgramResponseEvents(Collection programs)
    {
        Object[] program_array = programs.toArray();
        ArrayList program_responses = new ArrayList(program_array.length);
        for (int i=0;i<program_array.length;i++) {
            program_responses.add(getProgramResponseEvent((CSProgram)program_array[i]));
        }
        return program_responses;
    }//end of getProgramResponseEvents()

    /**
     * Retrieve program locations indirectly associated with the list of given service providers
     * @param serviceProviderIds
     * @return
     */
    public static List getProgramLocationsBySerProvNRefTypes(List serviceProviderIds, List referralTypeCds, boolean isOderByLoc)
    {
    	List matching_sp_program_locs = new ArrayList();
    	if(isOderByLoc) {
    		matching_sp_program_locs = 
	    		CSServiceProviderHelper.getSPProgramLocationsOrderByLoc(serviceProviderIds, referralTypeCds);
    	} else {
	    	matching_sp_program_locs = 
	    		CSServiceProviderHelper.getServiceProviderProgramLocations(serviceProviderIds, referralTypeCds);
    	}
    	return matching_sp_program_locs;
    }//end of getProgramLocationsByServiceProvider()
    
    /**
     * REturn list of program location response events
     * @param spProgramLocations
     * @return
     */
    public static List getProgramLocationResponseEvents(List spProgramLocations)
    {
    		//initialize variables
    	List program_location_response_events = new ArrayList();
    	CSProgramLocationResponseEvent prog_loc_response_event = null;
    	List service_provider_programs = null;
    	List program_locations = null;
    	List program_languages = null;
    	Hashtable this_program = null ;	
    	String this_service_provider_id = "";
    	String this_program_id = "";
    	String this_location_id = "";
		//loop thru list of service provider locations and create response list
    	for (int i=0;i<spProgramLocations.size();i++) {
    			//retrieve properties of current service provider location
    		CSServiceProvider this_service_provider_loc = 
    			(CSServiceProvider)spProgramLocations.get(i);
    		
    			//create one of these when we encounter a new service provider
    		if ( !( "" + this_service_provider_loc.getServiceProviderId() ).
    				equals(this_service_provider_id) ) {
    				//assign value of newly encountered service provider id
    			this_service_provider_id = "" + 
									this_service_provider_loc.getServiceProviderId();
    			
	    			//set service provider properties
	    		prog_loc_response_event = new CSProgramLocationResponseEvent();
	    		program_location_response_events.add(prog_loc_response_event);
	    		
	    			//set service provider properties
	    		prog_loc_response_event.setServiceProviderId(
	    				"" + this_service_provider_loc.getServiceProviderId());
	    		prog_loc_response_event.setServiceProviderName(
	    				this_service_provider_loc.getServiceProviderName());
	    		
	    			//create new programs list
	    		service_provider_programs = new ArrayList();
	    		prog_loc_response_event.setServiceProviderPrograms(service_provider_programs);
	    		
	    		this_program_id = "";
	    		this_location_id="";
    		}
    			//create entry for newly encountered program
    		if (!("" + this_service_provider_loc.getProgramId()).
    				equals(this_program_id)) {
    				//assign value of newly encountered program id
    			this_program_id = "" + 
									this_service_provider_loc.getProgramId();
    				//create program properties hashtable and add to list of programs
	    		this_program = new Hashtable();
	    		service_provider_programs.add(this_program);
	    			//set program properties
	    		this_program.put("programId",
	    				this_service_provider_loc.getProgramId());
	    		String program_identifier = (this_service_provider_loc.getProgramIdentifier() == null)? " ":
					this_service_provider_loc.getProgramIdentifier();
	    		this_program.put("programIdentifier",program_identifier);
	    		String program_name = (this_service_provider_loc.getProgramName() == null)? " ":
					this_service_provider_loc.getProgramName();
	    		this_program.put("programName",	program_name);
	    		String program_status_cd = (this_service_provider_loc.getProgramStatusCode() == null)? " ":
					this_service_provider_loc.getProgramStatusCode();
	    		this_program.put("programStatusId",program_status_cd);
	    		this_program.put("referralTypeCd",
	    				this_service_provider_loc.getProgramGroupCode() 
							+ " " + this_service_provider_loc.getProgramTypeCode());
	    		String csts_code = 
	    			(this_service_provider_loc.getCstsCode() == null)?" ":
	    				this_service_provider_loc.getCstsCode();
	    		this_program.put("cstsCode",csts_code);
	    		String sex_specific_code = 
	    			(this_service_provider_loc.getSexSpecificCode() == null)?" ":
	    				this_service_provider_loc.getSexSpecificCode();
	    		this_program.put("sexSpecificCode",sex_specific_code);
	    		this_program.put("isContractProgram",
	    				"" + this_service_provider_loc.getIsContractProgram());
	    			//create list for program locations
	    		program_locations = new ArrayList();
	    		this_program.put("programLocations", program_locations);
	    			//create list for program languages
	    		program_languages = new ArrayList();		    		
	    		this_program.put("programLanguages", program_languages);
	    		this_location_id="";
    		}	
    			//if new location encountered
    		if (!("" + this_service_provider_loc.getLocationId()).
    				equals(this_location_id)) {
					//assign value of newly encountered location id
    			this_location_id = "" + 
									this_service_provider_loc.getLocationId();
    				//create hashtable for storing program location attributes and add to list
    			Hashtable this_location = new Hashtable();
				program_locations.add(this_location);
					//set location properties
	    		String location_id = 
	    			(this_service_provider_loc.getLocationId() == null)?"":
	    				this_service_provider_loc.getLocationId();
				this_location.put("locationId",location_id);
	    		String street_number = 
	    			(this_service_provider_loc.getStreetNumber() == null)?"":
	    				this_service_provider_loc.getStreetNumber();
				this_location.put("streetNumber",street_number);
	    		String street_name = 
	    			(this_service_provider_loc.getStreetName() == null)?"":
	    				this_service_provider_loc.getStreetName();
				this_location.put("streetName",street_name);
				String street_type = 
	    			(this_service_provider_loc.getStreetTypeCd() == null)?"":
	    				this_service_provider_loc.getStreetTypeCd();
				this_location.put("streetType",street_type);
				String apt_num = 
	    			(this_service_provider_loc.getAptNum() == null)?"":
	    				this_service_provider_loc.getAptNum();
				this_location.put("aptNum",apt_num);
	    		String city = 
	    			(this_service_provider_loc.getCity() == null)?"":
	    				this_service_provider_loc.getCity();
				this_location.put("city",city);
	    		String state = 
	    			(this_service_provider_loc.getState() == null)?"":
	    				this_service_provider_loc.getState();
				this_location.put("state",state);
	    		String zip_code = 
	    			(this_service_provider_loc.getZipCode() == null)?"":
	    				this_service_provider_loc.getZipCode();
				this_location.put("zipCode",zip_code);
	    		String phone_Number = 
	    			(this_service_provider_loc.getPhoneNumber() == null)?"":
	    				this_service_provider_loc.getPhoneNumber();
				this_location.put("phoneNumber",phone_Number);
				String fax_Number = 
	    			(this_service_provider_loc.getFaxNumber() == null)?"":
	    				this_service_provider_loc.getFaxNumber();
				this_location.put("faxNumber",fax_Number);
				String valid_loc_status = 
	    			(this_service_provider_loc.getValidLocStatus() == null)?"":
	    				this_service_provider_loc.getValidLocStatus();
				this_location.put("validLocStatus",valid_loc_status);
			}
				//add to list of languages if not presently in list
    		String this_language_code = 
    			(this_service_provider_loc.getProgramLanguage() == null)?"":this_service_provider_loc.getProgramLanguage();
    		if ( !program_languages.contains(this_language_code) ) {
    			program_languages.add(this_language_code);
    		}
    	}
    		//return program location response event
    	return program_location_response_events;
    }//end of getProgramLocationResponseEvents()
    
    /**
     * REturn list of program location response events
     * @param spProgramLocations
     * @return
     */
    public static List getProgramLocationResponseEventsOrderByLoc(List spProgramLocations)
    {
    		//initialize variables
    	List program_location_response_events = new ArrayList();	
    	CSProgramLocationResponseEvent prog_loc_response_event = null;
    	List service_provider_programs = null;
    	List program_locations = null;
    	List program_languages = null;
    	Hashtable this_program = null ;
    	String this_service_provider_id = "";
    	String this_program_id = "";
    	String this_location_id = "";
		//loop thru list of service provider locations and create response list
    	for ( int i=0;i<spProgramLocations.size();i++ ) {
    			//retrieve properties of current service provider location
    		CSServiceProvider this_service_provider_loc = 
    			(CSServiceProvider)spProgramLocations.get(i);
    			//create one of these when we encounter a new service provider
    		if (!("" + this_service_provider_loc.getServiceProviderId()).
    				equals(this_service_provider_id) ) {
    				//assign value of newly encountered service provider id
    			this_service_provider_id = "" + 
									this_service_provider_loc.getServiceProviderId();
    				//set service provider properties
	    		prog_loc_response_event = new CSProgramLocationResponseEvent();
	    		program_location_response_events.add(prog_loc_response_event);
	    			//set service provider properties
	    		prog_loc_response_event.setServiceProviderId(
	    				"" + this_service_provider_loc.getServiceProviderId());
	    		prog_loc_response_event.setServiceProviderName(
	    				this_service_provider_loc.getServiceProviderName());
	    		prog_loc_response_event.setFaithBased(this_service_provider_loc.getIsFaithBased());
	    			//create new programs list
	    		program_locations = new ArrayList();
	    		prog_loc_response_event.setServiceProviderLocations(program_locations);
	    		this_program_id = "";
	    		this_location_id="";
    		}
    			//create entry for newly encountered location
    		if (!("" + this_service_provider_loc.getLocationId()).
    				equals(this_location_id)) {
    				//assign value of newly encountered location id
    			this_location_id = "" + 
									this_service_provider_loc.getLocationId();
    				//create hashtable for storing program location attributes and add to list
    			Hashtable this_location = new Hashtable();
				program_locations.add(this_location);
					//set location properties
	    		String location_id = 
	    			(this_service_provider_loc.getLocationId() == null)?"":
	    				this_service_provider_loc.getLocationId();
				this_location.put("locationId",location_id);
	    		String street_number = 
	    			(this_service_provider_loc.getStreetNumber() == null)?"":
	    				this_service_provider_loc.getStreetNumber();
				this_location.put("streetNumber",street_number);
	    		String street_name = 
	    			(this_service_provider_loc.getStreetName() == null)?"":
	    				this_service_provider_loc.getStreetName();
				this_location.put("streetName",street_name);
				String street_type = 
	    			(this_service_provider_loc.getStreetTypeCd() == null)?"":
	    				this_service_provider_loc.getStreetTypeCd();
				this_location.put("streetType",street_type);
				String apt_num = 
	    			(this_service_provider_loc.getAptNum() == null)?"":
	    				this_service_provider_loc.getAptNum();
				this_location.put("aptNum",apt_num);
	    		String city = 
	    			(this_service_provider_loc.getCity() == null)?"":
	    				this_service_provider_loc.getCity();
				this_location.put("city",city);
	    		String state = 
	    			(this_service_provider_loc.getState() == null)?"":
	    				this_service_provider_loc.getState();
				this_location.put("state",state);
	    		String zip_code = 
	    			(this_service_provider_loc.getZipCode() == null)?"":
	    				this_service_provider_loc.getZipCode();
				this_location.put("zipCode",zip_code);
	    		String location_phone = 
	    			(this_service_provider_loc.getLocationPhone() == null)?"":
	    				this_service_provider_loc.getLocationPhone();
				this_location.put("locationPhone",location_phone);
				String location_Fax = 
	    			(this_service_provider_loc.getLocationFax() == null)?"":
	    				this_service_provider_loc.getLocationFax();
				this_location.put("locationFax",location_Fax);
				String locationRegionCd = 
	    			(this_service_provider_loc.getRegionCode() == null)?"":
	    				this_service_provider_loc.getRegionCode();
				this_location.put("locationRegionCd",locationRegionCd);
				//create list for program locations
				service_provider_programs = new ArrayList();
				this_location.put("programs", service_provider_programs);
	    		this_program_id = "";
    		}
    			//if new program encountered
    		if (!("" + this_service_provider_loc.getProgramId()).
    				equals(this_program_id)) {
					//assign value of newly encountered program id
    			this_program_id = "" + 
									this_service_provider_loc.getProgramId();
    				//create program properties hashtable and add to list of programs
	    		this_program = new Hashtable();
	    		service_provider_programs.add(this_program);
	    			//set program properties
	    		this_program.put("programId",
	    				this_service_provider_loc.getProgramId());
	    		String program_name = (this_service_provider_loc.getProgramName() == null)? " ":
					this_service_provider_loc.getProgramName();
	    		this_program.put("programName",	program_name);
	    		String program_price = (this_service_provider_loc.getProgramPrice() == null)? " ":
					this_service_provider_loc.getProgramPrice();
	    		this_program.put("programPrice",	program_price);
	    			//create list for program languages
	    		program_languages = new ArrayList();		    		
	    		this_program.put("programLanguages", program_languages);
			}
    			//add to list of languages if not presently in list
    		String this_language_code = 
    			(this_service_provider_loc.getProgramLanguage() == null)?"":this_service_provider_loc.getProgramLanguage();
    		if (!program_languages.contains(this_language_code)) {
    			program_languages.add(this_language_code);
    		}
    	}
    		//return program location response event
    	return program_location_response_events;
    }//end of getProgramLocationResponseEventsOrderByLoc()
    
}//end of CSProgramHelper class
