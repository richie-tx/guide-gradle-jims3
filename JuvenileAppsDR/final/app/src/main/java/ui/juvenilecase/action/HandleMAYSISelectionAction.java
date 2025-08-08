//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\HandleMAYSISelectionAction.java

package ui.juvenilecase.action;

import java.util.Map;
import java.util.Date ;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.mentalhealth.GetMAYSITextFileDataEvent;
import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.mentalhealth.reply.MAYSIDocResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.MentalHealthForm;

/**
 * @author dgibler
 * 
 */
public class HandleMAYSISelectionAction extends JIMSBaseAction
{
	/**
	 * @roseuid 42791FCF034B
	 */
	public HandleMAYSISelectionAction()
	{
	}

	/**
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 * @return Map
	 */
	protected void addButtonMapping(Map buttonMap)
	{
		buttonMap.put("button.newAssessment", "newAssessment");
		buttonMap.put("button.maysiTextFile", "maysiTextFile");
		buttonMap.put("button.back", "back");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.link", "linkFromTask");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42791D570223
	 */
	public ActionForward newAssessment( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		// clear user input
		MentalHealthForm form = (MentalHealthForm)aForm;
		if( form.getAction().equalsIgnoreCase("update") )
		{
			HttpSession session = aRequest.getSession();
			JuvenileProfileForm profileForm = (JuvenileProfileForm)session.getAttribute("juvenileProfileHeader");
			if( profileForm == null )
			{
				UIJuvenileHelper.populateJuvenileProfileHeaderForm(aRequest, form.getJuvenileNum());
			}

			String sequenceNum = form.getMaysiId();
			MAYSIDetailsResponseEvent detail = fetchMAYSIDetails(sequenceNum);

			if( detail == null )
			{ // ERROR OCCURRED
				return aMapping.findForward(UIConstants.FAILURE);
			}

			// populate the form with the response
			form.populateFromMAYSIDetailsEvent(detail);
		}
		else
		{
			form.clearForNewAssessment();
			GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent)
					EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

			requestEvent.setJuvenileNum(form.getJuvenileNum());

			CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);
			JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent)
					MessageUtil.filterComposite(replyEvent, JuvenileProfileDetailResponseEvent.class);
			
			form.setRace("");
			form.setRaceId("");
			form.setSex("");
			form.setSexId("");
			
			if( detail != null )
			{
				form.setRace(detail.getRace());
				form.setRaceId(detail.getRaceId());
				form.setSex(detail.getSex());
				form.setSexId(detail.getSexId());
				form.setHispanic(detail.getHispanic()); //U.S 88526
				form.setEthnicity(detail.getEthnicity());
				
				//U.S 88526
				if(detail.getHispanic()!=null && detail.getHispanic().equalsIgnoreCase("Y"))
				{
					form.setEthnicityHispanic("YES");
				}
				else
				{
					form.setEthnicityHispanic("NO");
				}

				Date DOB = detail.getDateOfBirth() ;
				form.setJuvenileDOB( DOB );

				int age = UIUtil.getAgeInYears( DOB );
				if( age == 0 )
				{
					form.setTestAge("");
				}
				else
				{
					form.setTestAge(Integer.toString(age));
				}
			}
		}

		return aMapping.findForward(UIConstants.NEW_ASSESSMENT);
	}

	public ActionForward linkFromTask(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException
	{
		// clear user input
		MentalHealthForm form = (MentalHealthForm)aForm;
		if( form.getAction().equalsIgnoreCase("update") )
		{
			UIJuvenileHelper.populateJuvenileProfileHeaderForm(aRequest, form.getJuvenileNum());

			String sequenceNum = form.getMaysiId();
			MAYSIDetailsResponseEvent detail = fetchMAYSIDetails(sequenceNum);

			if( detail == null )
			{ // ERROR OCCURRED
				return aMapping.findForward(UIConstants.FAILURE);
			}

			// populate the form with the response
			form.populateFromMAYSIDetailsEvent(detail);
			form.setAdminister(false);
			form.setLocationUnitId(aRequest.getParameter("locationUnitId"));
			form.setFacilityTypeId(aRequest.getParameter("facilityTypeId"));
			form.setLengthOfStayId(aRequest.getParameter("lengthOfStayId"));
			form.setTask(true);
		}
		return aMapping.findForward(UIConstants.NEW_ASSESSMENT);
	}

	/**
	 * Breaks down a sequence number (MAYSID) & returns a MAYSIDetailResponseEvent
	 * 
	 * @param form
	 *          String MAYSID made up of assessmentId, subAssessId, &
	 *          maysiDetailId (format example - 488-0-56)
	 * @return detail MAYSIDetailsResponseEvent
	 */
	private MAYSIDetailsResponseEvent fetchMAYSIDetails(String sequenceNum)
	{
		int indexOfFirstDash = -1 ;
		int indexOfSecondDash = -1 ;

		for( int i = 0; i < sequenceNum.length(); i++ )
		{
			if( sequenceNum.charAt(i) == '-' )
			{
				if( indexOfFirstDash == -1 )
				{
					indexOfFirstDash = i;
				}
				if( indexOfFirstDash != -1 )
				{
					indexOfSecondDash = i;
				}
			}
		}

		if( indexOfFirstDash == (-1) || indexOfSecondDash == (-1) )
		{ // sequence# formatted incorrectly. 
			return( (MAYSIDetailsResponseEvent)null ) ; 			
		}
			
			
		String assessmentId = sequenceNum.substring(0, indexOfFirstDash);
		String subAssessId = sequenceNum.substring((indexOfFirstDash + 1), (indexOfSecondDash));
		String maysiDetailId = sequenceNum.substring((indexOfSecondDash + 1), sequenceNum.length());

		if( subAssessId.equals("0") )
		{
			subAssessId = "";
		}

		if( maysiDetailId.equals("0") )
		{
			maysiDetailId = "";
		}

		MAYSIDetailsResponseEvent detail = 
				UIJuvenileCaseworkHelper.fetchMAYSIDetails(assessmentId, subAssessId, maysiDetailId);

		return detail;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42791D570223
	 */
	public ActionForward maysiTextFile(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		MentalHealthForm form = (MentalHealthForm)aForm;
		GetMAYSITextFileDataEvent requestMaysi = (GetMAYSITextFileDataEvent)
				EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMAYSITEXTFILEDATA);

		requestMaysi.setJuvenileNumber(form.getJuvenileNum());

		CompositeResponse responseMaysi = postRequestEvent(requestMaysi);
		Object myDoc = MessageUtil.filterComposite(responseMaysi, MAYSIDocResponseEvent.class);
		form.setMaysiTextFileContents("");

		if( myDoc == null )
		{
			sendToErrorPage(aRequest, "error.generic", "No MAYSI Text File found");
			return aMapping.findForward(UIConstants.FAILURE);
		}

		MAYSIDocResponseEvent myMaysiDoc = (MAYSIDocResponseEvent)myDoc;
		form.setMaysiTextFileContents(myMaysiDoc.getMaysiDoc().toString());

		return aMapping.findForward(UIConstants.MAYSI_TEXT_FILE);
	}

}
