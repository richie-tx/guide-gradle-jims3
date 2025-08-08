/*
 * Project: JIMS
 * Class:   ui.juvenilecase.action.DisplayJuvenileProfileUpdateSummaryAction
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package ui.juvenilecase.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenile.CreateJuvenileSsnViewLogEvent;
import messaging.juvenile.SaveJuvSSNUserAccessEvent;
import messaging.juvenile.SaveMatchingJuvenilesEvent;
import messaging.juvenile.ValidateJuvDOBDocumentEvent;
import messaging.juvenile.ValidateJuvSSNNameEvent;
import messaging.juvenile.reply.JuvDOBDocumentResponseEvent;
import messaging.juvenile.reply.JuvenileProfilesResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileMainForm;
import ui.juvenilecase.form.JuvenileProfileForm;

/**
 * Class DisplayJuvenileProfileUpdateSummaryAction.
 * 
 * @author Anand Thorat
 */
public class DisplayJuvenileProfileUpdateSummaryAction extends LookupDispatchAction{

	// ------------------------------------------------------------------------
	// --- constructor ---
	// ------------------------------------------------------------------------
	/**
	 * @roseuid 42A9C0DA02AF
	 */
	public DisplayJuvenileProfileUpdateSummaryAction()
	{
	}

	// ui.juvenilecase.action.DisplayJuvenileProfileUpdateSummaryAction.DisplayJuvenileProfileUpdateSummaryAction

	// ------------------------------------------------------------------------
	// --- method ---
	// ------------------------------------------------------------------------

	/**
	 * @param aMapping - The a mapping.
	 * @param aForm - The form.
	 * @param aRequest - The request.
	 * @param aResponse - The response.
	 * @return ActionForward
	 * @roseuid 42A9B4880159
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileMainForm juvMainForm = (JuvenileMainForm)aForm;

		JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);
		String juvenileId = headerForm.getJuvenileNum();

		if (!juvMainForm.getBirthCountry().equalsIgnoreCase("UNITED STATES"))
		{
			juvMainForm.setBirthCountyId("");
			juvMainForm.setStateId("");
		}
		if(juvMainForm.getStateId() != null && !juvMainForm.getStateId().equals("") && !juvMainForm.getStateId().equalsIgnoreCase("TX"))
			juvMainForm.setBirthCountyId("");

		juvMainForm.setAction(PDConstants.UPDATE);

		// these will return a 'Y' or 'N' in the string
        	{
        	    if (aRequest.getParameter("hispanic") != null && !aRequest.getParameter("hispanic").isEmpty())
        	    {
        		boolean hispanic = PDConstants.YES.equals(aRequest.getParameter("hispanic")) ? true : false;
        		if (!hispanic)
        		{
        		    juvMainForm.setHispanic(PDConstants.NO);
        		}
        		else
        		{
        		    juvMainForm.setHispanic(PDConstants.YES);
        		}
        	    }
        	    else
        	    {
        		juvMainForm.setHispanic("");
        	    }
        	    boolean multiracial = PDConstants.YES.equals(aRequest.getParameter("multiracial")) ? true : false;
        	    boolean verifiedDOB = PDConstants.YES.equals(aRequest.getParameter("verifiedDOB")) ? true : false;
        
        	    juvMainForm.setRace(CodeHelper.getCodeDescription(PDCodeTableConstants.RACE, juvMainForm.getOriginalRaceId()));
        	    if (!multiracial)
        	    {
        		juvMainForm.setMultiracial(PDConstants.NO);
        	    }
        
        	    if (!verifiedDOB)
        	    {
        		juvMainForm.setVerifiedDOB(PDConstants.NO);
        	    }
        	}

		// begin
		if( juvMainForm.getVerifiedDOB().equalsIgnoreCase("Y") )
		{
			ValidateJuvDOBDocumentEvent validateRequestEvent = (ValidateJuvDOBDocumentEvent) 
					EventFactory.getInstance(JuvenileControllerServiceNames.VALIDATEJUVDOBDOCUMENT);

			validateRequestEvent.setJuvenileNum(juvenileId);

			// Getting PD Response Event
			JuvDOBDocumentResponseEvent detail = (JuvDOBDocumentResponseEvent) 
					MessageUtil.postRequest(validateRequestEvent, JuvDOBDocumentResponseEvent.class);
			if (detail.isAvailable() == false)
			{
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noBirthCertificate"));
				saveErrors(aRequest, errors);
				
				return aMapping.findForward(UIConstants.SEARCH_FAILURE);
			}
		}
		
		//Taken out for US 39892
		/*
		juvMainForm.setMatchDetectedSSN(false);
		// if the current SSN doesnt match form's SSN
		if( !juvMainForm.getCurrentSSN().getSSN().equals( juvMainForm.getSSN().getSSN() ) )
		{
			ValidateJuvSSNNameEvent validateSSNRequestEvent = (ValidateJuvSSNNameEvent) 
					EventFactory.getInstance(JuvenileControllerServiceNames.VALIDATEJUVSSNNAME);*/

			/* ER JIMS200071929 eliminates name in ssn validation */
//			validateSSNRequestEvent.setFirstName(juvMainForm.getFirstName());
//			validateSSNRequestEvent.setLastName(juvMainForm.getLastName());
		/*	validateSSNRequestEvent.setJuvenileNum(juvenileId);
			if (juvMainForm.getSSN() != null)
			{
				validateSSNRequestEvent.setSsn(juvMainForm.getSSN().getSSN());
			}

			String tSSN = validateSSNRequestEvent.getSsn() ;
			if( notNullNotEmptyString( tSSN ) && 
					! tSSN.equals(PDConstants.SSN_666666666) && 
					! tSSN.equals(PDConstants.SSN_777777777) && 
					! tSSN.equals(PDConstants.SSN_888888888) && 
					! tSSN.equals(PDConstants.SSN_999999999))
			{
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(validateSSNRequestEvent);

				// Get PD Response Event
				CompositeResponse response = (CompositeResponse) dispatch.getReply();
				MessageUtil.processReturnException(response);
				Collection<JuvenileProfilesResponseEvent> myMatchingJuvs = MessageUtil.compositeToCollection(response, JuvenileProfilesResponseEvent.class);
				if( myMatchingJuvs != null && myMatchingJuvs.size() > 0 )
				{
					juvMainForm.setMatchingJuvs(myMatchingJuvs);
					juvMainForm.setMatchDetectedSSN(true);
					
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage("error.family.samePerson"));
					saveErrors(aRequest, errors);
					Iterator<JuvenileProfilesResponseEvent> myIterMatch = myMatchingJuvs.iterator();
					while (myIterMatch.hasNext())
					{
						JuvenileProfilesResponseEvent myMatchRespEvt = myIterMatch.next();
						if( myMatchRespEvt != null )
						{
							flagBothJuveniles(myMatchRespEvt.getJuvenileNum(), juvenileId, juvMainForm.getSSN().getSSN(), UIUtil.getCurrentUserID());
							// SEND OUT NOTIFICATION TO EACH DEPT SA stating the 2 juvneiles #'s that
							// had the conflict and their SSNs and who the officer was that discovered it.
						}
					}

					return aMapping.findForward(UIConstants.SEARCH_FAILURE);
				}
			}
		}*/
		
		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
	}

	/*
	 * 
	 */
	private void flagBothJuveniles(String juvA, String juvB, String ssn, String foundByUserId)
	{
		if (juvA != null && juvB != null )
		{
			String tJuvA = "", tJuvB = "" ;
			int juvALength = juvA.trim().length() ;
			int juvBLength = juvB.trim().length() ;
			
			if( juvALength > juvBLength)
			{
				tJuvA = juvA.trim().substring(0, juvBLength ) ;
				tJuvB = juvB.trim() ;
			}
			else if( juvBLength > juvALength )
			{
				tJuvB = juvB.trim().substring(0, juvALength ) ;
				tJuvA = juvA.trim() ;
			}
			else
			{
				tJuvA = juvA.trim() ;
				tJuvB = juvB.trim() ;
			}
			
			if( ! tJuvA.equalsIgnoreCase(tJuvB) ) 
			{
				SaveMatchingJuvenilesEvent event = (SaveMatchingJuvenilesEvent) 
				EventFactory.getInstance(JuvenileControllerServiceNames.SAVEMATCHINGJUVENILES);

				event.setJuvA(tJuvA);
				event.setJuvB(tJuvB);
				event.setCreateUser(foundByUserId);
				event.setStatus("UNRESOLVED");
				event.setNotes("System Generated Notes: Juvenile Match found when user " 
						+ foundByUserId + " was trying to update juvenile# " + tJuvB + "  to ssn# " + ssn);
		
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				try
				{
					dispatch.postEvent(event);
				}
				catch (Exception e)
				{
					// ignore any exception as this is not visible to the user
				}
			}
		}
	}

	/*
	 * given a String, return true if it's not null and not empty
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  (str.length() > 0) ) ;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward update( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		JuvenileMainForm juvMainForm = (JuvenileMainForm) aForm;
		juvMainForm.setAction("updateSSN");
		
		JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);
		String juvenileId = headerForm.getJuvenileNum();
		//record who accessed the ssn
		//SaveJuvSSNUserAccessEvent saveEvent = (SaveJuvSSNUserAccessEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVSSNUSERACCESS);
		
		//saveEvent.setJuvenileNum(juvenileId);
		//saveEvent.setSsn(juvMainForm.getCompleteSSN().getSSN());
		//saveEvent.setAccessedBy(UIUtil.getCurrentUserID());
		CreateJuvenileSsnViewLogEvent createViewLogEvent = (CreateJuvenileSsnViewLogEvent)
			EventFactory.getInstance(JuvenileControllerServiceNames.CREATEJUVENILESSNVIEWLOG);
		createViewLogEvent.setJuvenileNum(juvenileId);
		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( createViewLogEvent ) ;
		return( aMapping.findForward( UIConstants.UPDATE_SSN ) ) ;

	}
	
	/**
     * 
     */
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();       
        keyMap.put("button.back", "back");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.next", "next");
        keyMap.put("button.update", "update");
        return keyMap;
    }
} // end DisplayJuvenileProfileUpdateSummaryAction
