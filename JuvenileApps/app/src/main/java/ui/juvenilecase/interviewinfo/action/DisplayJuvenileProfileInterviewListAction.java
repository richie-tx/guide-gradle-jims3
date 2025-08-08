package ui.juvenilecase.interviewinfo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.Name;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenilePhotoForm;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.interviewinfo.form.JuvenileInterview;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;

/**
 * 
 * @author awidjaja
 *
 * This action is for listing interviews by casefiles (used in the Profile view of
 * Interviews) 
 */
public class DisplayJuvenileProfileInterviewListAction extends DisplayJuvInterviewListAction
{
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward displayJuvInterviewList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;
		form.clear();
		
		//initiate JuvenilePhotoForm for displaying photo 
		JuvenilePhotoForm myPhotoForm=UIJuvenileHelper.getJuvPhotoForm(aRequest,true);
		
		//getting juvenileNum from session
		HttpSession session = aRequest.getSession();
		JuvenileProfileForm profileForm = (JuvenileProfileForm) session.getAttribute("juvenileProfileHeader");
		if(profileForm != null)
		{
		 	form.setJuvenileNum(profileForm.getJuvenileNum());
		 	form.setJuvenileName(new Name(profileForm.getJuvenileName().split(",")[1],"",profileForm.getJuvenileName().split(",")[0]));
			
		}
		else
		{
			//return failure?;
		}
		
		Collection interviewsList = getAllInterviews(null, form.getJuvenileNum());
		
		Collection interviews = JuvenileInterview.convertRE(interviewsList, 
				form.getInterviewLocationList(), form.getPersonsInterviewedList());
		form.setAllInterviews(interviews);
			
		//Sort the interviews per casefileId
		form.setInterviewsGroupedByCasefileId(
				groupInterviewsByCasefileId(form.getAllInterviews()));
		
		form.setTodayAppointments(getTodayAppointments(form, 
				form.getInterviewLocationList(), form.getPersonsInterviewedList()));
		form.setTodaysAppointmentGroupedByCasefileId(
				groupInterviewsByCasefileId(form.getTodayAppointments()));
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.link", "displayJuvInterviewList");
		return keyMap;
	}
	
	private HashMap groupInterviewsByCasefileId(Collection interviews) {
		HashMap interviewMap = new HashMap();
		for(Iterator iter = interviews.iterator(); iter.hasNext();)
		{
			JuvenileInterview interview = 
				(JuvenileInterview)iter.next();
			if(!interviewMap.containsKey(interview.getCasefileId()))
			{
				Collection intCol = new ArrayList();
				intCol.add(interview);
				interviewMap.put(interview.getCasefileId(), intCol);
			}
			else
			{
				Collection intCol = 
					(Collection) interviewMap.get(interview.getCasefileId());
				intCol.add(interview);
				
			}
		}
		return interviewMap;
	}
	
	
	
		
	
}