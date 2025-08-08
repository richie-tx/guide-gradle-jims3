/*
 * Created on Dec 14, 2006
 *
 */
package pd.supervision.administercasenotes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.administercasenotes.GetCasenoteAssociatesByCasenoteIdsEvent;
import messaging.administercasenotes.GetCasenoteSubjectsByCasenoteIdsEvent;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.utilities.Name;
import pd.contact.user.UserProfile;
import pd.supervision.administercaseload.Supervisee;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;

/**
 * @author dgibler
 *  
 */
public class CasenoteHelper {

	private static CasenoteHelper casenoteHelper;
	private static boolean serviceInitialized = false;
	 
	private CasenoteHelper(){
		
	}
	
	public static CasenoteHelper getInstance() {
		if ( casenoteHelper == null ) {
			synchronized ( CasenoteHelper.class ) {
				
				if (!serviceInitialized) {
					casenoteHelper = new CasenoteHelper();
					serviceInitialized = true;
				}
			}
		}
		return casenoteHelper;
	}
	
    /**
     * Creates CasenoteResponseEvent from Casenote entity object.
     * 
     * @param casenote
     */
    public CasenoteResponseEvent getCasenoteResponse(Casenote casenote) {
        CasenoteResponseEvent response = new CasenoteResponseEvent();
        casenote.fillCasenote(response);
        return response;
    }
    
    public static CasenoteResponseEvent getCasenoteResponse(Casenote casenote, List subjects, List associates) {
        CasenoteResponseEvent response = new CasenoteResponseEvent();
        casenote.fillCasenote(response, subjects, associates);
        return response;
    }

    /**
     * Bulds a collection of CaseNoteResponseEvents from Casenote objects.
     * 
     * @param iter
     * @return
     */
    public Collection getCasenoteResponses(Iterator iter) {
        Collection responses = new ArrayList();
        if (iter != null && iter.hasNext()) {
            CasenoteResponseEvent response = null;
            Casenote casenote = null;
            StringBuffer casenotes = new StringBuffer("");
            Map map = new HashMap();
            while (iter.hasNext()) {
                casenote = (Casenote) iter.next();
                if(!map.containsKey(casenote.getOID())){
                	casenotes.append(casenote.getOID());
                	casenotes.append(",");
                	map.put(casenote.getOID(), casenote);
                }
            }
            
            Map subjects = new HashMap();
            Map associates = new HashMap();            
            String casenoteIds = casenotes.toString();
            casenoteIds = casenoteIds.substring(0,casenoteIds.length()-1);
            if(casenoteIds.length() > 0){                
            	GetCasenoteAssociatesByCasenoteIdsEvent gcaEvent = new GetCasenoteAssociatesByCasenoteIdsEvent();
                gcaEvent.setCasenoteIds(casenoteIds);
                Iterator iterAssociates = CasenoteSuperviseeAssociates.findAll(gcaEvent);
                while(iterAssociates.hasNext()){
                	CasenoteSuperviseeAssociates  ca = (CasenoteSuperviseeAssociates) iterAssociates.next();
                	if(!associates.containsKey(ca.getParentId())){
                		List assocList = new ArrayList();
                		assocList.add(ca.getChildId());
                		associates.put(ca.getParentId(), assocList);
                	}else{
                		List assocOldList = (List) associates.get(ca.getParentId());
                		assocOldList.add(ca.getChildId());
                		associates.put(ca.getParentId(), assocOldList);
                	}                	
                }
                
                GetCasenoteSubjectsByCasenoteIdsEvent gcsEvent = new GetCasenoteSubjectsByCasenoteIdsEvent();
                gcsEvent.setCasenoteIds(casenoteIds);
                Iterator iterSubjects = CasenoteSubjects.findAll(gcsEvent);
                while(iterSubjects.hasNext()){
                	CasenoteSubjects  cs = (CasenoteSubjects) iterSubjects.next();
                	if(!subjects.containsKey(cs.getParentId())){
                		List subjectList = new ArrayList();
                		subjectList.add(cs.getChildId());
                		subjects.put(cs.getParentId(), subjectList);
                	}else{
                		List subjectList = (List) subjects.get(cs.getParentId());
                		subjectList.add(cs.getChildId());
                		subjects.put(cs.getParentId(), subjectList);
                	}                	
                }
                
                Iterator listiterator = map.values().iterator();
                while (listiterator.hasNext()) {
                    casenote = (Casenote) listiterator.next();
                    List subjectList = (List) subjects.get(casenote.getOID());
                    List associatesList = (List) associates.get(casenote.getOID());
                    response = CasenoteHelper.getCasenoteResponse(casenote,subjectList,associatesList);
                    responses.add(response);
                }
                response = null;
                iterSubjects = null;
                listiterator = null;
                casenote = null;
                subjects = null;
                associates = null;
            }    
        }
        return responses;
        
    }

    /**
     * Posts CasenoteResponse objects.
     * 
     * @param casenoteResponses
     */
    public void postCasenoteResponses(Collection casenoteResponses) {
        if (casenoteResponses != null) {
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
            Iterator iter = casenoteResponses.iterator();
            CasenoteResponseEvent cre = null;
            while (iter.hasNext()) {
                cre = (CasenoteResponseEvent) iter.next();
                dispatch.postEvent(cre);
            }
            iter = null;
            cre = null;
        }
        
        casenoteResponses = null;
    }

    /**
     * Posts CasenoteResponse objects.
     * 
     * @param iter
     */
    public void postCasenoteResponses(Iterator iter) {
        if (iter != null) {
            Collection responses = getCasenoteResponses(iter);
            postCasenoteResponses(responses);
        }
    }
    
    /**
     * Posts CasenoteResponse objects.
     * 
     * @param iter
     */
    public void postCasenoteResponse(CasenoteResponseEvent response) {
    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
        dispatch.postEvent(response);
    }
    
    /**
     * @param probOffId
     * @return
     */
    public String getProbationOfficerNameAsString(String spn){
    	Name aName = getProbationOfficer(spn);
    	return aName.getFormattedName();
    }
    
    /**
     * @param probOffId
     * @return
     */
    public Name getProbationOfficer(String spn){
    	Name aName = new Name();
/*		if (probOffId.equals("**")){
			aName.setLastName("Unassigned");
		} else if (probOffId != null && !probOffId.trim().equals(PDConstants.BLANK)) {
			CSCDOrganizationStaffPosition staffPos = CSCDStaffPositionHelper.getStaffPositionByPOI(probOffId);
			if (staffPos != null){
			    UserProfile userProfile = staffPos.getUserProfile();
			    if (userProfile != null){
			        aName.setFirstName(userProfile.getFirstName());
			        aName.setMiddleName(userProfile.getMiddleName());
			        aName.setLastName(userProfile.getLastName());
			    }
			}
		}
		*/
        StringBuffer paddedSpn = new StringBuffer(spn);
        while (paddedSpn.length() < 8){
            paddedSpn.insert(0,"0");
        }
        Supervisee supervisee = Supervisee.findByDefendantId(paddedSpn.toString());
        if (supervisee != null){
            CSCDStaffPosition staffPos = CSCDStaffPosition.find(supervisee.getAssignedStaffPositionId());
            if (staffPos != null && staffPos.getUserProfileId() != null){
                UserProfile userProfile = staffPos.getUserProfile();
                aName.setFirstName(userProfile.getFirstName());
                aName.setMiddleName(userProfile.getMiddleName());
                aName.setLastName(userProfile.getLastName());
            } else {
                aName.setLastName("No Officer Assigned");
            }
        } else {
            aName.setLastName("No Officer Assigned");
        }

    	return aName;
    }
}
