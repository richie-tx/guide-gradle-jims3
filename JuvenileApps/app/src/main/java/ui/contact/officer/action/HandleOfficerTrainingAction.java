package ui.contact.officer.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.officer.reply.OfficerTrainingResponseEvent;
import messaging.contact.officer.reply.TrainingTopicsResponseEvent;
import messaging.officer.GetAllTrainingTopicsEvent;
import messaging.officer.GetOfficerTrainingEvent;
import messaging.officer.UpdateOfficerTrainingEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.OfficerProfileControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.contact.officer.form.OfficerForm;

public class HandleOfficerTrainingAction extends LookupDispatchAction
{

    @Override
    protected Map getKeyMethodMap()
    {
	Map<String, String> keyMap = new HashMap<String, String>();
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.addSelected", "updateOfficerTraining");
	keyMap.put("button.link", "load");

	return keyMap;
    }
    
    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward load(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	OfficerForm officerForm = (OfficerForm) aForm;

	String officerProfileId = aRequest.getParameter("officerProfileId");
	String action = aRequest.getParameter("action");
	
	List profiles = officerForm.getOfficerProfiles();
	for( int x=0;x<profiles.size();x++){
	    
	    OfficerProfileResponseEvent officer = (OfficerProfileResponseEvent) profiles.get(x);
	    if( officer.getOfficerProfileId().equals( officerForm.getOfficerProfileId()) ){
		
		// Set officer to form
		officerForm.setLastName( officer.getLastName() );
		officerForm.setFirstName( officer.getFirstName() );
		officerForm.setMiddleName( officer.getMiddleName() );
		officerForm.setDepartmentId( officer.getDepartmentId() );
		officerForm.setDepartmentName( officer.getDepartmentName());
		officerForm.setOtherIdNum( officer.getOtherIdNum() );
		officerForm.setBadgeNum( officer.getBadgeNum() );
		officerForm.setUserId( officer.getUserId() );
		officerForm.setManagerId( officer.getManagerId() );
		officerForm.setStatus( officer.getStatus() );
		
	    }
	    
	}
	
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	GetAllTrainingTopicsEvent topicsEvent = (GetAllTrainingTopicsEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETALLTRAININGTOPICS);
	dispatch.postEvent(topicsEvent);


	CompositeResponse replyEvent = MessageUtil.postRequest(topicsEvent);
	Collection<TrainingTopicsResponseEvent> topics = MessageUtil.compositeToCollection(replyEvent, TrainingTopicsResponseEvent.class);	

	Collections.sort((ArrayList) topics); //US 187528 - add sort to the Training topic list
	officerForm.setTrainingTopics(topics);

	refreshOfficerTraining(officerForm, officerProfileId);

	return aMapping.findForward(naming.UIConstants.SUCCESS);
    }

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward updateOfficerTraining(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	OfficerForm trainingForm = (OfficerForm) aForm;
	String officerProfileId = aRequest.getParameter("officerProfileId");

	UpdateOfficerTrainingEvent uptEvent = new UpdateOfficerTrainingEvent();
	uptEvent.setTrainingTopicsId(Integer.parseInt(trainingForm.getTrainingTopicId()));
	uptEvent.setTrainingBeginDate(DateUtil.stringToDate(trainingForm.getTrainingBegDate(), DateUtil.DATE_FMT_1));
	uptEvent.setTrainingEndDate(DateUtil.stringToDate(trainingForm.getTrainingEndDate(), DateUtil.DATE_FMT_1));
	uptEvent.setTrainingHours(trainingForm.getTrainingHours());
	uptEvent.setOfficerId(Integer.parseInt(officerProfileId));

	MessageUtil.postRequest(uptEvent);
	
	refreshOfficerTraining(trainingForm, officerProfileId);

	return aMapping.findForward(naming.UIConstants.SUCCESS);
    }

    /**
     * 
     * @param form
     * @param officerProfileId
     */
    public void refreshOfficerTraining(OfficerForm form, String officerProfileId)
    {

	form.setOfficerTraining(new ArrayList());
	GetOfficerTrainingEvent getTraining = (GetOfficerTrainingEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERTRAINING);
	getTraining.setOfficerProfileId(officerProfileId);

	CompositeResponse replyEvent = MessageUtil.postRequest(getTraining);
	Collection<OfficerTrainingResponseEvent> trainingList = MessageUtil.compositeToCollection(replyEvent, OfficerTrainingResponseEvent.class);
	
	
	/*// Currently sorting by createDate desc
	 Collections.sort((List<OfficerTrainingResponseEvent>) trainingList, new Comparator<OfficerTrainingResponseEvent>() {
		@Override
		public int compare(OfficerTrainingResponseEvent evt1, OfficerTrainingResponseEvent evt2)
		{
		    if (evt1.getSortOrder() != null && evt2.getSortOrder() != null)
			return evt2.getSortOrder().compareTo(evt1.getSortOrder());
		    else
			return -1;
		}
	    });*/
	Collections.sort((List<OfficerTrainingResponseEvent>) trainingList,Collections.reverseOrder());
	form.setOfficerTraining(trainingList);
    }


}
