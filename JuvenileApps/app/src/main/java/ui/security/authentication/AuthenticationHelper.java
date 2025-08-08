/*
 * Created on April 19, 2012
 * 
 */
package ui.security.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenilecase.GetJuvenileCaseLoadByOfficerIDEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import ui.common.UIUtil;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.security.authentication.form.LoginForm;

/**
 * @author ugopinath
 * 
 */
public class AuthenticationHelper {

	
	public static void getOfficerCaseload(LoginForm loginForm)
	{
		List<OfficerProfileResponseEvent> officerprofiles = JuvenileFacilityHelper.getOfficerProfiles("logonId", UIUtil.getCurrentUserID());
		Iterator<OfficerProfileResponseEvent> events = officerprofiles.iterator();
		String officerId="";
		if(events.hasNext()){
			OfficerProfileResponseEvent resp = events.next();
			officerId=resp.getOfficerId();
		}
	
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetJuvenileCaseLoadByOfficerIDEvent caseLoadEvent = (GetJuvenileCaseLoadByOfficerIDEvent) 
				EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASELOADBYOFFICERID );
		caseLoadEvent.setOfficerId(officerId);
		dispatch.postEvent(caseLoadEvent);				
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Collection files = MessageUtil.compositeToCollection(response, JuvenileCasefileResponseEvent.class);
		List<JuvenileCasefileResponseEvent> activeJuveniles = new ArrayList();
		List<JuvenileCasefileResponseEvent> pendingJuveniles = new ArrayList();
		int activeFileCount = 0;
		int pendingFileCount = 0;
		if (officerId != null && officerId !="")
		{
			Iterator filesIter = files.iterator();
			
			while (filesIter.hasNext())
			{
				JuvenileCasefileResponseEvent casefileNew = (JuvenileCasefileResponseEvent) filesIter.next();				
				String status = casefileNew.getCaseStatus();
									
				if(status.equalsIgnoreCase("A")){
					activeFileCount++;
					activeJuveniles.add(casefileNew);
				}
				if(status.equalsIgnoreCase("P")){
					pendingFileCount++;
					pendingJuveniles.add(casefileNew);
				}
				
			}
			
			loginForm.setActiveFileCount((activeFileCount));
			loginForm.setPendingFileCount(pendingFileCount);
			loginForm.setActiveJuveniles(activeJuveniles);
			loginForm.setPendingJuveniles(pendingJuveniles);
		}
	}
}
