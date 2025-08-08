package ui.juvenilecase.interviewinfo.action;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.codetable.GetJuvenileCourtsEvent;
import messaging.codetable.criminal.reply.JuvenileCourtResponseEvent;
import messaging.family.GetActiveFamilyConstellationEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;
import ui.juvenilecase.interviewinfo.form.SocialHistoryForm;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayDetentionReasonAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.next", "next");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}
	
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)	
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}
		
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		SocialHistoryForm form = (SocialHistoryForm) aForm;
		//aRequest.setAttribute("state", "display");
		
		//HttpSession session = aRequest.getSession();
		//JuvenileInterviewForm juvInterviewForm = (JuvenileInterviewForm) session.getAttribute("juvenileInterviewForm");
		
		//get list of members in constellation
		GetActiveFamilyConstellationEvent event =  (GetActiveFamilyConstellationEvent)EventFactory.getInstance(
				   JuvenileFamilyControllerServiceNames.GETACTIVEFAMILYCONSTELLATION);
		//event.setJuvenileNum(juvInterviewForm.getJuvenileNum());
		event.setJuvenileNum(form.getJuvenileNum());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		// Perform Error handling
		MessageUtil.processReturnException(response); 
		Map dataMap = MessageUtil.groupByTopic(response);
		
		JuvenileFamilyForm.Constellation newFamily = null;
		
		if (dataMap != null)
		{
			Collection families  = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
			if(families!=null && families.size()>0){
				Iterator myIter=families.iterator();
				while(myIter.hasNext()){
					FamilyConstellationListResponseEvent myFamily=(FamilyConstellationListResponseEvent)myIter.next();
					if(myFamily.isActive()){
						newFamily=new JuvenileFamilyForm.Constellation();
						newFamily.setFamilyNumber(myFamily.getFamilyNum());
						newFamily.setActive(myFamily.isActive());
						//myForm.setCurrentConstellation(newFamily);
						
						Collection currentFamMembers = (Collection)dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
						UIJuvenileHelper.setJuvFamilyFormFROMMemberListRespEvt(newFamily,currentFamMembers);
						break;
					}
				}
			}
		}
		
		form.setNotifiedPersonList(newFamily.getMemberList());
		
		GetJuvenileCourtsEvent getJuvCourtsEvent =
			(GetJuvenileCourtsEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTS);
		dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(getJuvCourtsEvent);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
	
		Collection juvenileCourts =
			MessageUtil.compositeToCollection(compositeResponse, JuvenileCourtResponseEvent.class);
		
		Collections.sort((List) juvenileCourts);
		form.setJuvenileCourts(juvenileCourts);
		
		form.setAssignedCourtId(form.getSocialHistoryData().getPresentOffense().getCourtCodeId());
		
		return aMapping.findForward(UIConstants.NEXT);
	}
	
}