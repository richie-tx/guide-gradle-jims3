package ui.juvenilecase.districtCourtHearings.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.AbstractResultsTemplateAction;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;

/**
 * 
 * @author nmathew
 *
 */
public class DisplayJuvenileDistrictCourtNameSearchResultsAction extends  AbstractResultsTemplateAction
{



    /**
     * @roseuid 467FB5C80014
     */
    public DisplayJuvenileDistrictCourtNameSearchResultsAction()
    {

    }
    
    /**
     * execute
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	CourtHearingForm form = (CourtHearingForm) aForm;
    	String queryString = aRequest.getParameter("submitAction");
    	if(queryString !=null)
    	{
			if (queryString.equalsIgnoreCase("cancel")) {
				form.setResultsPage(""); // to hide the results page when NO records are found
				form.reset();
				return (aMapping.findForward(UIConstants.CANCEL));
			}
			if (queryString.equalsIgnoreCase("refresh")) {
				String formAction = form.getAction();
				form.reset();
				form.setAction(formAction);//to retain the action(Name Search/DOB search upon refresh)
				return (aMapping.findForward(UIConstants.REFRESH));
			}
			if (queryString.equalsIgnoreCase("court")) {
				form.setResultsPage(""); // to hide the results page when NO records are found
				form.reset();
				return (aMapping.findForward(UIConstants.CANCEL));
			}
			if (queryString.equalsIgnoreCase("detention")) {
				String formAction = form.getAction();
				form.reset();
				form.setAction(formAction);//to retain the action(Name Search/DOB search upon refresh)
				return (aMapping.findForward(UIConstants.DETENTION));
			}			
    	}
    	if(form==null)
    		form = new CourtHearingForm();
    	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		if ((form.getJuvenileDOB() == null || form.getJuvenileDOB().equals("")))
		{
		    if ((form.getJuvenileLastName() == null || form.getJuvenileLastName().equals("")) ){
			   ActionMessage myError=new ActionMessage("error.common");
			   ArrayList coll=new ArrayList();
			   coll.add(myError);
			   sendToErrorPage(aRequest,coll);
			   return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		    }
			
		}
		form.setPrevAction("JuvNameSearch");
		IEvent searchEvent = createSearchJuvenileProfileEvent(form);
		dispatch.postEvent(searchEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		ActionForward forward = null;
		Collection<JuvenileProfileDetailResponseEvent> juveniles = MessageUtil.compositeToCollection(response, JuvenileProfileDetailResponseEvent.class);
		
		if( juveniles.isEmpty() )
		{
			forward = this.handleZeroResults(aMapping, aForm, aRequest, aResponse, response);
		} else if (juveniles.size() == 1){
			
			Iterator<JuvenileProfileDetailResponseEvent> i = juveniles.iterator();
			IEvent responseEvent = (IEvent) i.next();
			forward = this.handleSingleResult( aMapping, aForm, aRequest,aResponse, response, responseEvent);
		} else {
			forward = this.handleMultipleResults(aMapping, aForm, aRequest, aResponse, response, juveniles);
		}
		/* Handle error thrown as ErrorResponseEvent from the command, 
		 * if there is any. Expected error: Number of juveniles matching 
		 * this criteria is greater than 2000.
		 */
		ErrorResponseEvent error = (ErrorResponseEvent) 
				MessageUtil.filterComposite(response, ErrorResponseEvent.class);
		if( error != null )
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
			saveErrors(aRequest, errors);
			forward = aMapping.findForward( UIConstants.SEARCH_FAILURE );
		}

		return( forward ) ;
	}
 
	/* (non-Javadoc)handleZeroResults
	 * @see ui.action.IResultsActionTemplate#handleZeroResults(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, mojo.km.messaging.Composite.CompositeResponse)
	 */
	@Override
	public ActionForward handleZeroResults(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse, CompositeResponse event) {

		CourtHearingForm form = (CourtHearingForm) aForm;
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage( UIConstants.NO_RECORDS_ERROR ));
		saveErrors(aRequest, errors);
		form.setResultsPage("");     //to hide the results page when NO records are found
		return( aMapping.findForward(UIConstants.SEARCH_FAILURE) );
	}

	/* (non-Javadoc)
	 * @see ui.action.IResultsActionTemplate#handleSingleResult(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, mojo.km.messaging.Composite.CompositeResponse, mojo.km.messaging.IEvent)
	 */
	@Override
	public ActionForward handleSingleResult(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse, CompositeResponse event,
			IEvent data) {
		JuvenileProfileDetailResponseEvent respEvent = (JuvenileProfileDetailResponseEvent)data;
		CourtHearingForm form = (CourtHearingForm) aForm;
		JuvenileDistrictCourtHelper.setProfileDetail(respEvent, form);
		ActionForward forward = aMapping.findForward( UIConstants.SUCCESS );
		return forward;
	}

	/* (non-Javadoc)
	 * @see ui.action.IResultsActionTemplate#handleMultipleResults(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, mojo.km.messaging.Composite.CompositeResponse, java.util.Collection)
	 */
	@Override
	public ActionForward handleMultipleResults(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse, CompositeResponse event,
			Collection data) {
		CourtHearingForm form = (CourtHearingForm) aForm;
		form.setSearchResultSize(data.size());
		form.setJuvenileProfiles( data );
		form.setResultsPage("resultsPage");
		return( aMapping.findForward(UIConstants.LISTSUCCESS) );
	}

	/**
	 * sendToErrorPage
	 * @param aRequest
	 * @param aActionErrors
	 */
	private void sendToErrorPage(HttpServletRequest aRequest, Collection aActionErrors)
	   {
			ActionErrors errors = new ActionErrors();
			if(aActionErrors!=null && aActionErrors.size()>0){
				Iterator<ActionMessage>actionErrors=aActionErrors.iterator();
				while(actionErrors.hasNext()){
					ActionMessage error=actionErrors.next();
					errors.add(ActionErrors.GLOBAL_MESSAGE,error);
				}
			   saveErrors(aRequest, errors);
			}
	   }
		


	@Override
	public void processBusinessExceptions(HttpServletRequest aRequest, CompositeResponse response, ActionErrors errors) {
		// TODO Auto-generated method stub
		
	}


	/**createSearchJuvenileProfileEvent
	 * @param form
	 * @return
	 */
	private  IEvent createSearchJuvenileProfileEvent(CourtHearingForm form)
	{
		SearchJuvenileProfilesEvent searchEvent = (SearchJuvenileProfilesEvent) 
				EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILEPROFILES);

		if( form.getSearchType().equals(PDJuvenileCaseConstants.SEARCH_JUVENILE_NAME) )
		{
			// "alias" string: include alias records  
			searchEvent.setSearchType( "alias" ) ; 
//			searchEvent.setAlienNumber(this.getAlienNumber());
			if( notNullNotEmptyString( form.getDateOfBirth() ) )
			{
				searchEvent.setDateOfBirth(DateUtil.stringToDate(form.getDateOfBirth(), UIConstants.DATE_FMT_1));
			}
			searchEvent.setFirstName(form.getJuvenileFirstName());
			searchEvent.setMiddleName(form.getJuvenileMiddleName());
			searchEvent.setLastName(form.getJuvenileLastName());
			searchEvent.setSexId(form.getSexId());
			searchEvent.setRaceId(form.getRaceId());
			form.setAction("NameSearch");
		}
		if( form.getSearchType().equals(PDJuvenileCaseConstants.SEARCH_DATE_OF_BIRTH) )
		{
			//searchEvent.setLastName("*");
			searchEvent.setDateOfBirth(DateUtil.stringToDate(form.getJuvenileDOB(), UIConstants.DATE_FMT_1));
			form.setAction("DOBSearch");
		}
	
		return searchEvent;
	}
    
	
	/*
	 * given a String, return true if it's not null and not empty
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  (str.length() > 0) ) ; 
	}
    

	

}
