package ui.juvenilecase.referral.action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.contact.officer.form.OfficerForm;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.SearchJuvenileOfficerEvent;
import messaging.officer.SearchOfficerProfilesByNameEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.OfficerProfileControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.contact.officer.OfficerProfile;

public class SearchOfficerProfileAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	
	OfficerForm officerForm = (OfficerForm) form;
	/** Check for initial load of this page **/
	String clrChk 		= request.getParameter("clr");
	String requestOrigin	= request.getParameter("requestOrigin");
	
	if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
	    officerForm.clear();
	    officerForm.setRequestOrigin(requestOrigin);
	    return mapping.findForward("error");
	}
	
	if (clrChk != null && clrChk.equalsIgnoreCase("MA")) {
	    return mapping.findForward("manageAssignment");
	} else if (clrChk != null && clrChk.equalsIgnoreCase("CR")) {
	    return mapping.findForward("createReferral");
	} else if (clrChk != null && clrChk.equalsIgnoreCase("OA")){
	    return mapping.findForward("overrideAssignment");
	} else if (clrChk != null && clrChk.equalsIgnoreCase("DT")){
	    return mapping.findForward("createJuvenileDrugTesting");
	}
	
	String firstName 	= officerForm.getFirstName().toUpperCase();
	String middleName	= officerForm.getMiddleName().toUpperCase();
	String lastName		= officerForm.getLastName().toUpperCase();
	List<OfficerProfileResponseEvent>officerProfiles = null;
	if ( isDefined(firstName)
		|| isDefined(lastName)
		|| isDefined(middleName) ){
	    
	    officerProfiles = retrieveOfficerProfiles(firstName,middleName, lastName);
	    officerForm.setOfficerProfiles(officerProfiles);
	    return mapping.findForward("success");
	    
	} else {
	    return mapping.findForward("error");
	}
	
    }
    
    
    public List<OfficerProfileResponseEvent> 
    				retrieveOfficerProfiles(String firstName,
	    						String middleName,
	    						String lastName
	    						) throws Exception {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	List<OfficerProfileResponseEvent> officerProfiles = new ArrayList<OfficerProfileResponseEvent>();
	SearchOfficerProfilesByNameEvent searchEvent = (SearchOfficerProfilesByNameEvent)
									EventFactory.getInstance(OfficerProfileControllerServiceNames.SEARCHOFFICERPROFILESBYNAME);
	searchEvent.setFirstName(firstName);
	searchEvent.setMiddleName(middleName);
	searchEvent.setLastName(lastName);
	dispatch.postEvent(searchEvent);
	CompositeResponse compositeResponse  = (CompositeResponse) dispatch.getReply() ;
	if ( compositeResponse != null 
		&& compositeResponse.hasResponses() ){
	    officerProfiles = MessageUtil.compositeToList(compositeResponse , OfficerProfileResponseEvent.class);	
	}
	return  officerProfiles;
    }
    
    boolean isDefined(String value){
	return value != null && value.length() > 0;
    }
}
