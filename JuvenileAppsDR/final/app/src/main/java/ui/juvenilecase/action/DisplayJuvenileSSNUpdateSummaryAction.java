/*
 * Project: JIMS
 * Class:   ui.juvenilecase.action.DisplayJuvenileProfileUpdateAction
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

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.SaveJuvSSNEvent;
import messaging.juvenile.SaveJuvenileProfileMainEvent;
import messaging.juvenile.SaveMatchingJuvenilesEvent;
import messaging.juvenile.ValidateJuvSSNNameEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.JuvenileProfilesResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.Features;
import naming.JuvenileControllerServiceNames;
import naming.PDConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileMainForm;
import ui.juvenilecase.form.JuvenileProfileForm;

/**
 * Class DisplayJuvenileSSNUpdateSummaryAction.
 *  
 * @author  ugopinath
 */
public class DisplayJuvenileSSNUpdateSummaryAction extends LookupDispatchAction
{

	// ------------------------------------------------------------------------
	// --- constructor                                                      ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 42A9C0DD02CE
	 */
	public DisplayJuvenileSSNUpdateSummaryAction()
	{

	} //end of ui.juvenilecase.action.DisplayJuvenileSSNUpdateSummaryAction

	// ------------------------------------------------------------------------
	// --- method                                                           ---
	// ------------------------------------------------------------------------

	/**
	 *  
	 * @param aMapping The a mapping.
	 * @param aForm The a form.
	 * @param aRequest The a request.
	 * @param aResponse The a response.
	 * @return  ActionForward
	 * @roseuid 42A9B48800CD
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileMainForm juvMainForm = (JuvenileMainForm) aForm;
		
		juvMainForm.setMatchDetectedSSN(false);
		
		JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);
		String juvenileId = headerForm.getJuvenileNum();
		
		// if the current SSN doesnt match form's SSN
				if( !juvMainForm.getCurrentSSN().getSSN().equals( juvMainForm.getCompleteSSN().getSSN() ) )
				{
					ValidateJuvSSNNameEvent validateSSNRequestEvent = (ValidateJuvSSNNameEvent) 
							EventFactory.getInstance(JuvenileControllerServiceNames.VALIDATEJUVSSNNAME);

					/* ER JIMS200071929 eliminates name in ssn validation */
//					validateSSNRequestEvent.setFirstName(juvMainForm.getFirstName());
//					validateSSNRequestEvent.setLastName(juvMainForm.getLastName());
					validateSSNRequestEvent.setJuvenileNum(juvenileId);
					if (juvMainForm.getCompleteSSN() != null)
					{
						validateSSNRequestEvent.setSsn(juvMainForm.getCompleteSSN().getSSN());
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
							errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.family.samePerson"));
							saveErrors(aRequest, errors);
							Iterator<JuvenileProfilesResponseEvent> myIterMatch = myMatchingJuvs.iterator();
							while (myIterMatch.hasNext())
							{
								JuvenileProfilesResponseEvent myMatchRespEvt = myIterMatch.next();
								if( myMatchRespEvt != null )
								{
									flagBothJuveniles(myMatchRespEvt.getJuvenileNum(), juvenileId, juvMainForm.getCompleteSSN().getSSN(), UIUtil.getCurrentUserID());
									// SEND OUT NOTIFICATION TO EACH DEPT SA stating the 2 juvneiles #'s that
									// had the conflict and their SSNs and who the officer was that discovered it.
								}
							}

							return aMapping.findForward(UIConstants.SEARCH_FAILURE);
						}
					}
				}
				//juvMainForm.setAction("confirmSSN");

		return aMapping.findForward(UIConstants.SUCCESS);
		
	} //end of ui.juvenilecase.action.DisplayJuvenileSSNUpdateSummaryAction.execute
	
	/*
	 * given a String, return true if it's not null and not empty
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  (str.length() > 0) ) ;
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
	
	/**
	 *  
	 * @param aMapping The a mapping.
	 * @param aForm The a form.
	 * @param aRequest The a request.
	 * @param aResponse The a response.
	 * @return  ActionForward
	 * @roseuid 42A9B48800CD
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JuvenileMainForm juvMainForm = (JuvenileMainForm) aForm;
		juvMainForm.setAction("");
		SaveJuvSSNEvent requestEvent = (SaveJuvSSNEvent) EventFactory
	                .getInstance(JuvenileControllerServiceNames.SAVEJUVSSN);
	       

		JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);
		String juvenileId = headerForm.getJuvenileNum();
		
	        requestEvent.setJuvenileNum(juvenileId);  
	        requestEvent.setSsn(juvMainForm.getCompleteSSN().getFormattedSSN());
	        CompositeResponse response=MessageUtil.postRequest(requestEvent);
	        
	        ErrorResponseEvent errorEvt = (ErrorResponseEvent) MessageUtil.filterComposite(response,
	        		ErrorResponseEvent.class);
	        if(errorEvt!=null){
	        	sendToErrorPage(aRequest,"error.generic","An error has occurred: " + errorEvt.getMessage());
	        	errorEvt.getMessage();
	        	 return aMapping.findForward(UIConstants.CONFIRM_SUCCESS);
	        }
	        String firstChar = juvMainForm.getCompleteSSN().getSSN().substring(0, 1);
	        String matchingChars[] = juvMainForm.getCompleteSSN().getSSN().split(firstChar+ "+");
	        boolean repeatChars = (matchingChars.length == 0);
	        if (!repeatChars)//Individual has never had a social security number.
		  {
	            juvMainForm.getSSN().setSSN("XXXXX"+juvMainForm.getCompleteSSN().getSSN().substring(5));
		  }
		  else 
		      juvMainForm.setSSN(juvMainForm.getCompleteSSN());
	    juvMainForm.getSSN().setSsn3(juvMainForm.getCompleteSSN().getSsn3());
		juvMainForm.setAction("confirmSSN");
		return aMapping.findForward(UIConstants.CONFIRM_SUCCESS);
	}
	
	 /**
		 * Method that adds a message to the request for sending back to the user takes 1 parameter, message key's message is diplayed as is 
		 * to the user after substitution of the parameter values
		 * @param aRequest -- a Request
		 * @param msgKey -- error message key to add 
		 * @param param -- a single parameter value contained in the message key
		 */
		protected void sendToErrorPage(HttpServletRequest aRequest, String msgKey, String param) {
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(msgKey,param));
		    saveErrors(aRequest, errors);
		}
		
		/**
		 * 89662 changes
		 * @param aMapping
		 * @param aForm
		 * @param aRequest
		 * @param aResponse
		 * @return ActionForward
		 */
		 public ActionForward returnToMasterPage(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
		    {
			return aMapping.findForward("returnSuccess");
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
        keyMap.put("button.finish", "finish");
        keyMap.put("button.returnToJuvenileProfileMasterUpdate", "returnToMasterPage");
        return keyMap;
    }

} // end DisplayJuvenileSSNUpdateSummaryAction
