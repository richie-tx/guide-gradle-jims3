package ui.juvenilecase.education.action;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.education.form.EducationCharterDetailsForm;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenile.GetEducationCharterDetailsEvent;
import messaging.juvenile.SaveCharterVEPEvent;
import messaging.juvenile.reply.CharterDetailsResponseEvent;
import messaging.juvenile.reply.CharterGEDResponseEvent;
import messaging.juvenile.reply.CharterPostReleaseResponseEvent;
import messaging.juvenilecase.GetJuvenileDetentionFacilitiesEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SubmitVEPUpdateAction extends JIMSBaseAction
{
	public SubmitVEPUpdateAction() {

	}
	
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		EducationCharterDetailsForm eduForm = (EducationCharterDetailsForm) aForm ;
		eduForm.setAction("confirm");
		
		SaveCharterVEPEvent reqEvent = (SaveCharterVEPEvent)EventFactory.getInstance( JuvenileControllerServiceNames.SAVECHARTERVEP ) ;
		reqEvent.setJuvenileCharterVEPId(eduForm.getVepProgramCode());
		reqEvent.setJuvenileNum(eduForm.getJuvenileNum());
		reqEvent.setProgramCodeId(eduForm.getProgramId());
		reqEvent.setStartDate(eduForm.getProgramStartDate());
		reqEvent.setCompleted(eduForm.isCompleted());
		reqEvent.setCompletionDate(eduForm.getProgramCompletionDate());
		
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
		dispatch.postEvent( reqEvent );
		CompositeResponse composite = (CompositeResponse)dispatch.getReply();
		MessageUtil.processReturnException( composite );
		
		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
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

	public ActionForward back(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			ActionForward forward = aMapping.findForward(UIConstants.BACK);
			return forward;
		}
	
	public ActionForward charterDetails(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
		EducationCharterDetailsForm form = (EducationCharterDetailsForm) aForm ;
		form.clearAll( ) ;
        String juvenileNum = UIJuvenileHelper.getJuvenileNumber(aRequest, true, false);
        form.setJuvenileNum(juvenileNum);
        
        GetEducationCharterDetailsEvent requestEvent = new GetEducationCharterDetailsEvent();
		requestEvent.setJuvenileNum(juvenileNum);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);

		CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException( replyEvent );
		
		List<CharterDetailsResponseEvent> charterDetails = MessageUtil.compositeToList(replyEvent, CharterDetailsResponseEvent.class);
		boolean locked = false;
		form.setVersion("0");
		if (charterDetails.size() > 0) {
			CharterDetailsResponseEvent charterDetailsRespEvent = charterDetails.get(0);

			Collection charterGEDs = charterDetailsRespEvent.getJuvenileCharterGEDs();
			
			if (charterGEDs != null) {
				form.setHsepProgramList(charterGEDs);
				form.setVersion(Integer.toString(charterGEDs.size()));

				Iterator charters = charterGEDs.iterator();
				while (charters.hasNext())
				{
					CharterGEDResponseEvent charterGED = (CharterGEDResponseEvent) charters.next();
					if (charterGED.isLockStatus()) {
						locked = true;
						break;
					}
				}
//				form.setLockStatus(locked);
			}

			Collection charterVEPs = charterDetailsRespEvent.getJuvenileCharterVEPs();
			if (charterVEPs != null) {
				form.setVepProgramList(charterVEPs);
			}

			Collection postReleases = charterDetailsRespEvent.getJuvenileCharterPostReleases();
			if (postReleases != null) {
				Iterator postReleaseTrackings = postReleases.iterator();
				while (postReleaseTrackings.hasNext())
				{
					CharterPostReleaseResponseEvent charterPR = (CharterPostReleaseResponseEvent) postReleaseTrackings.next();
					Iterator continuingEdCodes = charterPR.getContinuingEducationCodes().iterator();
					StringBuffer sbuf = new StringBuffer();
					while (continuingEdCodes.hasNext())
					{
						CodeResponseEvent codeResp = (CodeResponseEvent) continuingEdCodes.next();
						sbuf = sbuf.append(codeResp.getDescription());
						sbuf = sbuf.append(", ");
					}	
					if( sbuf != null  &&  (sbuf.length() > 0) )
					{
						int length = sbuf.length();
						if( length > (0) )
						{
							String continuingEdCodeStr = sbuf.substring( 0, length - 2 ) ;
							charterPR.setContinuingEducationCodesStr(continuingEdCodeStr);
						}
					} 
				}
				form.setPostReleaseTrackingList(postReleases);
			}
		}
		form.setLockStatus(locked);
		//determine if juvenile has been released from a facility
		boolean releasedFromFacility = false;
		GetJuvenileDetentionFacilitiesEvent reqEvent = new GetJuvenileDetentionFacilitiesEvent();
		reqEvent.setJuvenileNum(juvenileNum);
		dispatch.postEvent(reqEvent);
		CompositeResponse replies = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException( replies );
		
		List<JuvenileDetentionFacilitiesResponseEvent> facilities = MessageUtil.compositeToList(replies, JuvenileDetentionFacilitiesResponseEvent.class);
		if (facilities != null) {
			Iterator juvFacilities = facilities.iterator();
			while (juvFacilities.hasNext())
			{
				JuvenileDetentionFacilitiesResponseEvent juvFacility = (JuvenileDetentionFacilitiesResponseEvent) juvFacilities.next();
				if (juvFacility.getReleaseDate() != null) {
					releasedFromFacility = true;
					break;
				}
			}
		}
		form.setReleasedFromFacility(releasedFromFacility);
		ActionForward forward = aMapping.findForward(UIConstants.RETURN_SUCCESS);
		return forward;
	}
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");		
		keyMap.put("button.charterDetails", "charterDetails");
	}
}
