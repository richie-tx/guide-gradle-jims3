//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\DisplayJuvServiceProviderSearchAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.reply.JuvenileFacilityAdmissionCommentsResponseEvent;
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

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.SecurityUIHelper;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.facility.form.AdmitReleaseForm;
import ui.juvenilecase.form.JuvenileProfileSearchForm;

public class DisplayServiceProviderJuvenileSearchAction extends JIMSBaseAction
{
	private final boolean SORT_IT = true ;
	
	/**
	 * @roseuid 450AF15B02A0
	 */
	public DisplayServiceProviderJuvenileSearchAction()
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 450AA179017B
	 */
	public ActionForward link( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
	    	JuvenileProfileSearchForm form = (JuvenileProfileSearchForm) aForm;
	        // Clear form and set default search to "Juvenile Name"
	        form.clear();
	        form.setSearchType(PDJuvenileCaseConstants.SEARCH_JUVENILE_NAME);
		
		String departmentId = SecurityUIHelper.getUserDepartmentId();
		if( !departmentId.equalsIgnoreCase( "JUV" ) )
		{
			this.saveErrors( aRequest, "error.searchServiceProvider.invalidUser" );
			aRequest.setAttribute( "error", "true" );
			return aMapping.findForward( UIConstants.FAILURE );
		}		
		

		return aMapping.findForward( UIConstants.SUCCESS );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	/*protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();		
		return keyMap;
	}*/
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "link");
		keyMap.put("button.submit", "submit");
	}
	public ActionForward submit(ActionMapping aMapping,ActionForm aForm,HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
	    JuvenileProfileSearchForm form = (JuvenileProfileSearchForm) aForm;
	    HttpSession session = aRequest.getSession();
	    session.setAttribute("richBean", new JuvenileProfileDetailResponseEvent());
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    String forward="success";	    
	    SearchJuvenileProfilesEvent searchEvent = (SearchJuvenileProfilesEvent) 
			EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILEPROFILES);
		// not searching  alias records  
	    	searchEvent.setSearchType( UIConstants.EMPTY_STRING ) ;
		if( StringUtils.isNotEmpty(form.getFirstName())) {
		    
		    String lastName = form.getFirstName().replaceAll("'", "''");
		    searchEvent.setFirstName( lastName );
		}
		    
		if( StringUtils.isNotEmpty(form.getMiddleName())){
		    
		    String middleName = form.getMiddleName().replaceAll("'", "''");
		    searchEvent.setMiddleName(middleName);
		}			
		
		if( StringUtils.isNotEmpty(form.getLastName())){
		    
		    String lastName = form.getLastName().replaceAll("'", "''");
		    searchEvent.setLastName(lastName);
		}
		
		searchEvent.setSexId(form.getSexId());		
		searchEvent.setRaceId(form.getRaceId());
		searchEvent.setFrom("SP");
		
		dispatch.postEvent(searchEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		//JuvenileProfileDetailResponseEvent respEvent = (JuvenileProfileDetailResponseEvent)MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
		Object errorResp = MessageUtil.filterComposite(response, ErrorResponseEvent.class);
		if (errorResp != null)
		{
		  ErrorResponseEvent error = (ErrorResponseEvent) errorResp;
		  ActionErrors errors = new ActionErrors();
		  errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
		  saveErrors(aRequest, errors);
		  return aMapping.findForward(UIConstants.FAILURE);
		}
		Collection<JuvenileProfileDetailResponseEvent> juveniles = MessageUtil.compositeToCollection(response, JuvenileProfileDetailResponseEvent.class);
		ArrayList<JuvenileProfileDetailResponseEvent> dataList = new ArrayList<JuvenileProfileDetailResponseEvent>();
		Iterator juvenilesIter= juveniles.iterator();
		int resultsize=0;
		while(juvenilesIter.hasNext())
		{
		    JuvenileProfileDetailResponseEvent juvenilesResEvent = (JuvenileProfileDetailResponseEvent)juvenilesIter.next(); 
		    if(juvenilesResEvent.getMasterStatusId()!=null)
		    {
			if(!juvenilesResEvent.getMasterStatusId().equalsIgnoreCase("N")&&!juvenilesResEvent.getMasterStatusId().equalsIgnoreCase("C"))
			{
	        		    dataList.add(juvenilesResEvent);
	        		    resultsize=resultsize+1;
			}
		    }
		}
		//get error 
		form.setJuvenileProfiles(dataList);
		form.setSearchResultSize(resultsize);
		if (resultsize == 0)
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));
		    saveErrors(aRequest, errors);		    
		    forward = "failure";
		}		
		return (aMapping.findForward(forward));
	}
	private void saveErrors( HttpServletRequest aRequest, String errorKey )
	{
		ActionErrors errors = new ActionErrors();
		errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( errorKey, SecurityUIHelper.getLogonId() ) );
		saveErrors( aRequest, errors );
	}
	
}
