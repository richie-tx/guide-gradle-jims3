package ui.juvenilecase.education.action;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.education.form.EducationCharterDetailsForm;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenile.GetEducationCharterDetailsEvent;
import messaging.juvenile.SaveCharterGEDEvent;
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

public class SubmitGEDCreateAction extends JIMSBaseAction
{
	public SubmitGEDCreateAction() {

	}
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");		
		keyMap.put("button.charterDetails", "charterDetails");
	}
	
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		EducationCharterDetailsForm eduForm = (EducationCharterDetailsForm) aForm ;
		eduForm.setAction("confirm");
		
		SaveCharterGEDEvent reqEvent = (SaveCharterGEDEvent)EventFactory.getInstance( JuvenileControllerServiceNames.SAVECHARTERGED ) ;
		reqEvent.setJuvenileNum(eduForm.getJuvenileNum());
		reqEvent.setMathAfterPlacement(eduForm.isMathAfterPlacement());
		reqEvent.setMathBeforePlacement(eduForm.isMathBeforePlacement());
		reqEvent.setMathPassFailIndicator(eduForm.isMathPassFailInd());
		reqEvent.setMathRetest(eduForm.isMathRetest());
		if ("".equals(eduForm.getMathScore() ) ) {
			reqEvent.setMathScore(0);
		} else {
			reqEvent.setMathScore(new Integer(eduForm.getMathScore()));
		}	
		reqEvent.setMathTestDate(eduForm.getMathTestDate());
		 /**Changes for ER: JIMS200077481 starts **/
		/*reqEvent.setReadingAfterPlacement(eduForm.isReadingAfterPlacement());
		reqEvent.setReadingBeforePlacement(eduForm.isReadingBeforePlacement());
		reqEvent.setReadingPassFailIndicator(eduForm.isReadingPassFailInd());
		reqEvent.setReadingRetest(eduForm.isReadingRetest());
		if ("".equals(eduForm.getReadingScore() ) ) {
			reqEvent.setReadingScore(0);
			reqEvent.setReadingTestDate(null);
		} else {
			reqEvent.setReadingScore(new Integer(eduForm.getReadingScore()));
			reqEvent.setReadingTestDate(eduForm.getReadingTestDate());
		}*/
		/**Changes for ER: JIMS200077481 ends **/
		reqEvent.setScienceAfterPlacement(eduForm.isScienceAfterPlacement());
		reqEvent.setScienceBeforePlacement(eduForm.isScienceBeforePlacement());
		reqEvent.setSciencePassFailIndicator(eduForm.isSciencePassFailInd());
		reqEvent.setScienceRetest(eduForm.isScienceRetest());
		if ("".equals(eduForm.getScienceScore() ) ) {
			reqEvent.setScienceScore(0);
		} else {
			reqEvent.setScienceScore(new Integer(eduForm.getScienceScore()));
		}
		reqEvent.setScienceTestDate(eduForm.getScienceTestDate());
		reqEvent.setSocialStudiesAfterPlacement(eduForm.isSocialStudiesAfterPlacement());
		reqEvent.setSocialStudiesBeforePlacement(eduForm.isSocialStudiesBeforePlacement());
		reqEvent.setSocialStudiesPassFailIndicator(eduForm.isSocialStudiesPassFailInd());
		reqEvent.setSocialStudiesRetest(eduForm.isSocialStudiesRetest());
		if ("".equals(eduForm.getSocialStudiesScore() ) ) {
			reqEvent.setSocialStudiesScore(0);
		} else {
			reqEvent.setSocialStudiesScore(new Integer(eduForm.getSocialStudiesScore()));
		}
		reqEvent.setSocialStudiesTestDate(eduForm.getSocialStudiesTestDate());
		//reqEvent.setTotalScore(new Integer(eduForm.getTotalScore()));
		//40495
		if(!eduForm.getTotalScoreScore().equalsIgnoreCase("")){
		reqEvent.setTotalScore(new Integer(eduForm.getTotalScoreScore()));
		}
		
		/**Changes for ER: JIMS200077481 ends **/
		/*reqEvent.setWritingAfterPlacement(eduForm.isWritingAfterPlacement());
		reqEvent.setWritingBeforePlacement(eduForm.isWritingBeforePlacement());
		reqEvent.setWritingPassFailIndicator(eduForm.isWritingPassFailInd());
		reqEvent.setWritingRetest(eduForm.isWritingRetest());
		if ("".equals(eduForm.getWritingScore() ) ) {
			reqEvent.setWritingScore(0);
		} else {
			reqEvent.setWritingScore(new Integer(eduForm.getWritingScore()));
		}
		reqEvent.setWritingTestDate(eduForm.getWritingTestDate());*/
		
		reqEvent.setRlaAfterPlacement(eduForm.isRlaAfterPlacement());
		reqEvent.setRlaBeforePlacement(eduForm.isRlaBeforePlacement());
		reqEvent.setRlaPassFailIndicator(eduForm.isRlaPassFailInd());
		reqEvent.setRlaRetest(eduForm.isRlaRetest());
		if ("".equals(eduForm.getRlaScore() ) ) {
			reqEvent.setRlaScore(0);
		} else {
			reqEvent.setRlaScore(new Integer(eduForm.getRlaScore()));
		}
		reqEvent.setRlaTestDate(eduForm.getRlaTestDate());
		/**Changes for ER: JIMS200077481 ends **/
		
		int version = 0;
		if (!eduForm.getVersion().equals("")) {
			version = new Integer(eduForm.getVersion());
		}
		reqEvent.setVersion(version + 1);
		reqEvent.setLockStatus(false);
//  05/17/2012	removed 3 test limit per activity 73498	
//		if ((reqEvent.getVersion() == 3) ||
		if (eduForm.isPassFailInd() ) {
			reqEvent.setLockStatus(true);
		}
		if(eduForm.getTotalIncomplete().equalsIgnoreCase("1")){
			reqEvent.setTotalIncomplete("1");
		}else{
		reqEvent.setGedPassFailIndicator(eduForm.isPassFailInd());
		}
		reqEvent.setGedProgramCodeId(eduForm.getHsepProgramCodeId());
		reqEvent.setOtherProgram(eduForm.getOtherProgramCode());
		
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
		dispatch.postEvent( reqEvent );
		CompositeResponse composite = (CompositeResponse)dispatch.getReply();
		MessageUtil.processReturnException( composite );
		        
		return aMapping.findForward(UIConstants.SUCCESS);
	   }
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			return aMapping.findForward(UIConstants.CANCEL);
		}

	public ActionForward back(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{	
		EducationCharterDetailsForm eduForm = (EducationCharterDetailsForm) aForm ;
		eduForm.clearAll( ) ;
			return aMapping.findForward(UIConstants.BACK);
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
					/**Changes for ER: JIMS200077481 starts **/
					Collections.sort((List) charterGEDs);
					/**Changes for ER: JIMS200077481 ends **/
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
//					form.setLockStatus(locked);
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
			return  aMapping.findForward(UIConstants.RETURN_SUCCESS);
		}
}
