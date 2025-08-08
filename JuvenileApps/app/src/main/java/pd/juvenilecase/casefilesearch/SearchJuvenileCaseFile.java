package pd.juvenilecase.casefilesearch;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.SearchResultsCountResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.logging.LogUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDJuvenileCaseConstants;

import org.apache.log4j.Level;

import pd.codetable.Code;
import pd.contact.officer.OfficerProfile;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileAlias;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefile;
import pd.km.util.Name;

/**
 * 
 * @author mchowdhury
 *  
 */
public class SearchJuvenileCaseFile
{

    /**
     * @roseuid 4278CAAC00FD
     */
    public SearchJuvenileCaseFile()
    {
    }

    /**
     * Posts the number of results returned by the search
     * 
     * @param numberOfResults
     */
    protected void postResultsCount(int numberOfResults)
    {
        SearchResultsCountResponseEvent event = new SearchResultsCountResponseEvent();
        event.setNumberOfResults(numberOfResults);
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        dispatch.postEvent(event);
    }

    protected void postSearchResponseEvents(JuvenileCasefile casefile, Map juveniles, boolean retrieveOfficerData,String searchType){
        try{
         	JuvenileCasefileSearchResponseEvent resp = new JuvenileCasefileSearchResponseEvent();
        	resp.setTopic(PDJuvenileCaseConstants.SEARCH_CASEFILE_RESULTS_TOPIC);

        	//Juvenile juv = null;
        	JuvenileCore juv = null;
        	JuvenileAlias juvAlias = null;
        	
        	String juvenileNum = casefile.getJuvenileNum();
        	
        	Object obj = juveniles.get(juvenileNum);
        	if (obj != null) {
        		if(obj.getClass().equals(JuvenileAlias.class)) {
        			juvAlias = (JuvenileAlias)obj;
        		} else {
        			juv = (Juvenile)obj;
        		}
        	} else {
        	    	//Profile stripping fix task 97536
        		//juv = casefile.getJuvenile();
        	    	juv = casefile.getJuvenile();
        		if (juv != null){
        			juveniles.put(juv.getJuvenileNum(), juv);
        		}
        	}

        	//Clear map
        	juveniles.clear();
        	
        	
        	
        if (juv != null || juvAlias != null){
            resp.setJuvenileNum(casefile.getJuvenileNum());
            
            if(juv != null){
            	resp.setJuvenileFirstName(juv.getFirstName());
                resp.setJuvenileMiddleName(juv.getMiddleName());
                resp.setJuvenileLastName(juv.getLastName());
                resp.setNameSuffix(juv.getNameSuffix());
    			resp.setJuvenileNameType(juv.getJuvenileType());
                Name name = new Name(juv.getFirstName(), juv.getMiddleName(), juv.getLastName(), juv.getNameSuffix());
                resp.setJuvenileFullName(name.getFormattedName());
                if (juv.getDateOfBirth() != null) {
                    resp.setJuvenileCurrentAge(String.valueOf(JuvenileCaseHelper.getAgeInYears(juv.getDateOfBirth())));
                }
                resp.setJuvRectype(juv.getRecType());
    			
            } else if (juvAlias != null){
            	resp.setJuvenileFirstName(juvAlias.getFirstName());
                resp.setJuvenileMiddleName(juvAlias.getMiddleName());
                resp.setJuvenileLastName(juvAlias.getLastName());
    			resp.setNameSuffix(juvAlias.getNameSuffix());
    			resp.setJuvenileNameType(juvAlias.getJuvenileType());
                Name name = new Name(juvAlias.getFirstName(), juvAlias.getMiddleName(), juvAlias.getLastName(), juvAlias.getNameSuffix());
                resp.setJuvenileFullName(name.getFormattedName());
                resp.setPreferredFirstName( juvAlias.getAliasNotes() );
                if (juvAlias.getDateOfBirth() != null) {
                    resp.setJuvenileCurrentAge(String.valueOf(JuvenileCaseHelper.getAgeInYears(juvAlias.getDateOfBirth())));
                }
            }
            
            if (retrieveOfficerData) {
                OfficerProfile officer = casefile.getProbationOfficer();
                Name officerName = new Name(officer.getFirstName(), officer.getMiddleName(), officer.getLastName());
                resp.setProbationOfficerFullName(officerName.getFormattedName());
                resp.setProbationOfficerFirstName( officer.getFirstName( ) ) ;
                resp.setProbationOfficerMiddleName( officer.getMiddleName( ) ) ;
                resp.setProbationOfficerLastName( officer.getLastName( ) ) ;
                resp.setJpoId(officer.getOfficerProfileId());
                resp.setOfficerLoginId(officer.getLogonId());
            } else {
                resp.setProbationOfficerFirstName(casefile.getOfficerFirstNameData());
                resp.setProbationOfficerLastName(casefile.getOfficerLastNameData());
                resp.setProbationOfficerMiddleName(casefile.getOfficerMiddleNameData());
                Name officerName = new Name(casefile.getOfficerFirstNameData(), casefile.getOfficerMiddleNameData(),
                        casefile.getOfficerLastNameData());
                resp.setProbationOfficerFullName(officerName.getFormattedName());
                resp.setJpoId(casefile.getProbationOfficerId());
                resp.setOfficerLoginId("");
            }
            resp.setZipCode(casefile.getZipCode()); //#32659 changes
            // Added for task #35786 US #34655
            resp.setDispositionDate(JuvenileCaseHelper.getDispositionDate(casefile));
            resp.setPrimaryContact(casefile.getIsPrimaryContact());//#32659 Hot fix changes.
            resp.setFmMemCreateDate(casefile.getFamMemCreateDate()); //#32659 Hot fix changes.
            resp.setInHomeStatus(casefile.getIsInHomeStatus()); //#32659 Hot fix changes.
            resp.setLocation(casefile.getJuvLocation());
            if(searchType.equals(PDJuvenileCaseConstants.SEARCH_CASE_STATUS)){
            	resp.setSupervisionNum(casefile.getCasefileId()); //for officer casefile.using auto generated oid
        	}else{
        		resp.setSupervisionNum(casefile.getOID()); // rest of other search uses casefileId as OID
        	}
            resp.setSequenceNum(casefile.getSequenceNumber());
            
            resp.setCasefileCreateDate(casefile.getCreateTimestamp());

            Code caseStatus = casefile.getCaseStatus();
            if (caseStatus != null)
            {
                resp.setCaseStatus(caseStatus.getDescription());
            }

            Code superType = casefile.getSupervisionType();
            if (superType != null)
            {
                resp.setSupervisionType(superType.getDescription());
                resp.setSupervisionCategory(casefile.getSupervisionCategoryId());
                resp.setSupervisionTypeId(casefile.getSupervisionTypeId());
            }
            //added for facility US 11300
            resp.setActivationDate(casefile.getActivationDate());
            //resp.setActivationDate(casefile.getAssignmentAddDate()); //added to get the begin date JIMS200076597 
            resp.setSupervisionEndDate( casefile.getSupervisionEndDate( ) ) ;
            
            //added for US 40492
            //find assignements for the casefile and get the most recent
            Iterator assignmentIter = Assignment.findAll("caseFileId", casefile.getCasefileId());
          
                Date latestAssignmentDate = null;               
                while (assignmentIter.hasNext())
                {
                    Assignment assignment = (Assignment) assignmentIter.next();
                    if (latestAssignmentDate == null)
                    {
                    	latestAssignmentDate = assignment.getAssignmentAddDate();
                    }
                    else if (assignment.getAssignmentAddDate().after(latestAssignmentDate))
                    {
                    	latestAssignmentDate = assignment.getAssignmentAddDate();
                    }
                }
                resp.setAssignmentDate(latestAssignmentDate);
                   
        
            MessageUtil.postReply(resp);
        }
        else
        {
            String msg = SearchJuvenileCaseFile.class.getName() + " Juvenile not found: " + casefile.getJuvenileNum();
            LogUtil.log(Level.WARN, msg);
        }
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            throw e;
        }        
    }

    protected void sendMaxCountExceeded()
    {
        ErrorResponseEvent errorResp = new ErrorResponseEvent();
        errorResp.setMessage("error.max.limit.exceeded");
        MessageUtil.postReply(errorResp);
    }

}
