// Source file:
// C:\\views\\MJCW\\app\\src\\ui\\juvenilecase\\programreferral\\action\\HandleProgramReferralAction.java

package ui.juvenilecase.programreferral.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.GetProgramByProgramIdEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.GetProgramReferralServiceEventsEvent;
import messaging.calendar.ServiceEventAttribute;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.juvenile.SearchJuvenileProfileCasefileListEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.programreferral.GetProgramReferralDetailsEvent;
import messaging.programreferral.SaveProgramReferralEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProgramReferralConstants;
import naming.ServiceEventControllerServiceNames;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.programreferral.ProgramReferralAction;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.programreferral.form.ProgramReferralForm;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;

public class HandleProgramReferralAction extends JIMSBaseAction
{
	/**
	 * @roseuid 463BA574006B
	 */
	public HandleProgramReferralAction()
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward acceptWithChanges( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm ;
		UIProgramReferralBean programReferral = form.getProgramReferral() ;
		programReferral.setEndDateStr(""); // Bug no: #46218
		programReferral.setOutComeCd("");
		programReferral.setOutComeDescription("");
		programReferral.setCurrentAction( ProgramReferralAction.ACCEPTWITHCHANGES ) ;
		form.setAction( UIConstants.UPDATE ) ;

		return( aMapping.findForward( UIConstants.UPDATE_SUCCESS ) ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward addComments( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm ;
		UIProgramReferralBean programReferral = form.getProgramReferral() ;
		//Bug #93713 - incorrectly added here - no changes should be made when adding comments
		/*programReferralBean.setEndDateStr(""); // Bug no: #46218
		programReferralBean.setOutComeCd("");
		programReferralBean.setOutComeDescription("");*/	
		
		//reset certain fields
		//programReferral.reset(); //commented for Bug #154229 NM
		
		programReferral.setCurrentAction( ProgramReferralAction.ADDCOMMENTS ) ;
		form.setAction( UIConstants.UPDATE ) ;

		return( aMapping.findForward( UIConstants.SUCCESS ) ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward cancelReferral( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm ;
		UIProgramReferralBean programReferral = form.getProgramReferral() ;
		
		//remove outcome code when operation is cancelled - US 109695
		programReferral.setOutComeCd(null);
		programReferral.setOutComeDescription(null);
		
		programReferral.setEndDateStr( DateUtil.dateToString( new Date(), DateUtil.DATE_FMT_1 ) ) ;
		programReferral.setCurrentAction( ProgramReferralAction.CANCEL ) ;
		form.setAction( UIConstants.CANCEL ) ;
		
		return( aMapping.findForward( UIConstants.SUCCESS ) ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward withdraw( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm ;
		UIProgramReferralBean programReferral = form.getProgramReferral() ;
		
		//reset certain fields
		programReferral.reset();
		
		programReferral.setEndDateStr( DateUtil.dateToString( new Date(), DateUtil.DATE_FMT_1 ) ) ;
		programReferral.setCurrentAction( ProgramReferralAction.WITHDRAW ) ;
		form.setAction( UIConstants.UPDATE ) ;
		
		return( aMapping.findForward( UIConstants.SUCCESS ) ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward complete( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm ;
		form.getProgramReferral().setOutComeCd("");
		UIProgramReferralBean programReferral = form.getProgramReferral() ;
		
		Date recentEventDate = null;
		Date currentDate = DateUtil.getCurrentDate();
		if (programReferral.getJuvenileEvents() != null && programReferral.getJuvenileEvents().size() > 0){
			for (int x = 0; x<programReferral.getJuvenileEvents().size(); x++){
				CalendarServiceEventResponseEvent csere = (CalendarServiceEventResponseEvent) programReferral.getJuvenileEvents().get(x);
				if (csere.getEventDate() != null && !"".equals(csere.getEventDate()) )	{
					if (currentDate.compareTo(csere.getEventDate()) > 0){ 
						if (recentEventDate == null){
							recentEventDate = csere.getEventDate();
						}
						if (recentEventDate.compareTo(csere.getEventDate()) < 0){
							recentEventDate = csere.getEventDate();
						}
					}	
				} 	
			}
		}
		if (recentEventDate != null){
			programReferral.setEndDateStr(DateUtil.dateToString( recentEventDate, DateUtil.DATE_FMT_1) );
		} else {
			programReferral.setEndDateStr("") ;
		}
		List outComeList = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.PROGRAM_REFERRAL_OUTCOME ) ;
		

		form.setOutComeList( outComeList ) ;
		List temp1 = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_OUTCOME_REF_DESC");
		List temp2 = new ArrayList();
		List temp3 = new ArrayList();
		for (int x =0; x< temp1.size(); x++)
		{
			JuvenileCodeTableChildCodesResponseEvent joscre = (JuvenileCodeTableChildCodesResponseEvent) temp1.get(x);
			if (UIConstants.OUTCOME_COMPLETE.equalsIgnoreCase(joscre.getParentId()) ) {
				temp2.add(joscre);
			}
			if (UIConstants.OUTCOME_FAILURE_TO_COMPLY.equalsIgnoreCase(joscre.getParentId()) ) {
				temp3.add(joscre);
			}
		}
		form.setOutComeSubcategoryOptList(temp2);
		form.setOutComeSubcategoryReqdList(temp3);
		temp1 = null;
		temp2 = null;
		temp3 = null;
	
		programReferral.setCurrentAction( ProgramReferralAction.COMPLETE ) ;
		form.setAction( UIConstants.UPDATE ) ;
		
		return( aMapping.findForward( UIConstants.UPDATE_SUCCESS ) ) ;
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
		ProgramReferralForm form = (ProgramReferralForm)aForm ;
		UIProgramReferralBean programReferral = form.getProgramReferral() ;
		programReferral.setEndDateStr(""); // Bug no: #46218

		programReferral.setCurrentAction( ProgramReferralAction.UPDATE ) ;
		form.setAction( UIConstants.UPDATE ) ;
		
		if( programReferral.getReferralState() != null && 
				programReferral.getReferralState().getStatus() != null )
		{
			if( programReferral.getReferralState().getStatus().equals( 
					ProgramReferralConstants.CLOSED ) &&
					programReferral.getReferralState().getSubStatus().equals( 
						ProgramReferralConstants.REJECTED ) )
			{
				programReferral.setCurrentAction( ProgramReferralAction.COMPLETE ) ;
			}
		}

		return( aMapping.findForward( UIConstants.UPDATE_SUCCESS ) ) ;
	}
	
	public ActionForward fix( ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
	    ProgramReferralForm form = (ProgramReferralForm)aForm ;
	    String referralId = aRequest.getParameter("selectedValue");
	    UIProgramReferralBean programReferral = null;
	    GetProgramReferralDetailsEvent gpdt = (GetProgramReferralDetailsEvent) EventFactory.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALDETAILS);
	    gpdt.setProgramReferralId(referralId);
	    CompositeResponse compositeResponse = (CompositeResponse) MessageUtil.postRequest(gpdt);

	    ProgramReferralResponseEvent respDetail = (ProgramReferralResponseEvent) MessageUtil.filterComposite(compositeResponse, ProgramReferralResponseEvent.class);
	    if (respDetail != null){
		programReferral = new UIProgramReferralBean(respDetail);
		programReferral.setCurrentAction( ProgramReferralAction.COMPLETE ) ;
		programReferral.setCurrentUserType(ProgramReferralConstants.JPO_USER);
		programReferral.setNextPossibleActions(programReferral.getReferralState().getPossibleNextActions(ProgramReferralConstants.JPO_USER));
		programReferral.setEndDateStr("");
	    }
	    
	   
	    
	    List temp1 = ComplexCodeTableHelper.getJuvenileCodeTableChildCodes("SUPERVISION_OUTCOME_REF_DESC");
	    List temp2 = new ArrayList();
	    List temp3 = new ArrayList();
		for (int x =0; x< temp1.size(); x++)
		{
			JuvenileCodeTableChildCodesResponseEvent joscre = (JuvenileCodeTableChildCodesResponseEvent) temp1.get(x);
			if (UIConstants.OUTCOME_COMPLETE.equalsIgnoreCase(joscre.getParentId()) ) {
				temp2.add(joscre);
			}
			if (UIConstants.OUTCOME_FAILURE_TO_COMPLY.equalsIgnoreCase(joscre.getParentId()) ) {
				temp3.add(joscre);
			}
		}
		form.setOutComeSubcategoryOptList(temp2);
		form.setOutComeSubcategoryReqdList(temp3);
		temp1 = null;
		temp2 = null;
		temp3 = null;
	
	    form.setProgramReferral(programReferral);
	    form.setAction( UIConstants.UPDATE ) ;

	    return( aMapping.findForward( UIConstants.UPDATE_SUCCESS ) ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward accept( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm ;
		UIProgramReferralBean programReferral = form.getProgramReferral() ;
		programReferral.setEndDateStr(""); // Bug no: #46218
		programReferral.setOutComeCd("");
		programReferral.setOutComeDescription("");
		
		programReferral.setCurrentAction( ProgramReferralAction.ACCEPT ) ;
		form.setAction( UIConstants.UPDATE ) ;
		
		return( aMapping.findForward( UIConstants.SUCCESS ) ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward submitChanges( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm ;
		
		form.setAction( UIConstants.SUMMARY ) ;
		
		return( aMapping.findForward( UIConstants.SUCCESS ) ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward finish( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm ;
		UIProgramReferralBean programReferral = form.getProgramReferral() ;
		

		//<KISHORE>JIMS200057235	MJCW Sch Cal Even and View Cal - Attend Status is incorrect
		if( programReferral != null )
		{
			GetProgramReferralServiceEventsEvent gprs = (GetProgramReferralServiceEventsEvent)
			EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMREFERRALSERVICEEVENTS);

			gprs.setJuvenileNum(programReferral.getJuvenileId());
			gprs.setProgramId(programReferral.getProviderProgramId());
			gprs.setProgramReferralId(programReferral.getReferralId());

			CompositeResponse compositeResponse = (CompositeResponse)MessageUtil.postRequest(gprs);			
			List calendarEvents = (List)
			MessageUtil.compositeToCollection(compositeResponse,CalendarServiceEventResponseEvent.class);

			if( calendarEvents != null )
			{
				Collections.sort( (List)calendarEvents );
				programReferral.setJuvenileEvents(calendarEvents);
			}
		}

		programReferral.executeAction() ;  //retrieve service provider info
		//bug fix 13556/22872 starts
		if(programReferral.getControllingReferralNum()==null || programReferral.getControllingReferralNum().isEmpty()){
			//retrieve cntrl num from casefile. if null.
			String controlRefNo = UIProgramReferralHelper.getControllingRefNumber(programReferral.getCasefileId()) ;
			if(	programReferral.getControllingReferralNum()==null)
			{
				if(controlRefNo!=null && !controlRefNo.isEmpty()){
					programReferral.setControllingReferralNum(controlRefNo.substring(0,4));
				}
			}
		}
		//bug fix 13556/22872 ends
		SaveProgramReferralEvent saveRefEvent = programReferral.getSaveProgramReferralEvent();
		
		if( saveRefEvent != null )
  		{
			MessageUtil.postRequest( saveRefEvent ) ;
		}
		
		if( programReferral.getReferralTaskInfo() != null )
		{
			programReferral.getReferralTaskInfo().createTask() ;
		}

		if( programReferral.getReferralNoticeInfo() != null )
		{
			programReferral.getReferralNoticeInfo().sendNotification() ;
		}

		if( form.getAction().equals( UIConstants.CANCEL ) )
		{
			form.setAction( UIConstants.CANCEL_CONFIRM ) ;
		}
		else
		{
			form.setAction( UIConstants.CONFIRM ) ;
		}	
		form.setOutComeSubcategoryReqdCd(null);
		form.setOutComeSubcategoryOptCd(null);
		return( aMapping.findForward( UIConstants.SUCCESS ) ) ;
	}
	
	
	public ActionForward transfer( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ProgramReferralForm form = (ProgramReferralForm)aForm;
		UIProgramReferralBean programReferralBean = form.getProgramReferral();
		programReferralBean.setEndDateStr(""); // Bug no: #46218
		programReferralBean.setOutComeCd("");
		programReferralBean.setOutComeDescription("");
		
				
		SearchJuvenileProfileCasefileListEvent search = (SearchJuvenileProfileCasefileListEvent)
		EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILEPROFILECASEFILELIST);

		search.setJuvenileId(programReferralBean.getJuvenileId());
		List caseFileCollection = MessageUtil.postRequestListFilter(search,	JuvenileProfileCasefileListResponseEvent.class);
		
		Iterator cases = caseFileCollection.iterator();
		ArrayList activeCasefiles = new ArrayList();
		
		//================ US 164826
		ProviderProgramResponseEvent spProgram = UIServiceProviderHelper.getServiceProviderProgram(programReferralBean.getProviderProgramId());		
		
        	if(spProgram != null && spProgram.getSupervisionCategory() != null && !spProgram.getSupervisionCategory().equals(""))
        	{
        	    String[] programSupervisionCategories = spProgram.getSupervisionCategory().split("\\s*,\\s*");
        		    
        	    	while (cases.hasNext())
        		{
        	    	    JuvenileProfileCasefileListResponseEvent juvenileCase = (JuvenileProfileCasefileListResponseEvent)cases.next();
        		    
        			if(juvenileCase != null && juvenileCase.getSupervisionCategory() != null && !juvenileCase.getSupervisionCategory().equals(""))
        			{ 
                		    for(int i = 0; i < programSupervisionCategories.length; i++){			
                			String programSupervisionCategory = programSupervisionCategories[i];
                			String casefileSupervisionCategory = juvenileCase.getSupervisionCategory();
                			
                			if ((juvenileCase.getCaseStatus().equals("ACTIVE")) &&
                				(!juvenileCase.getSupervisionNum().equals(programReferralBean.getCasefileId())) && 
                					casefileSupervisionCategory.equals(programSupervisionCategory))
                			{  
                			    activeCasefiles.add(juvenileCase);
                			    break;
                			}
                		    }	
        			}
        		}
        	    	
        	    if(activeCasefiles.size() > 0){
        		programReferralBean.setMatchedSelectedSupervisionCategory(true);
        	    }
        	    
		}
        	
        	
        	if(programReferralBean != null && !programReferralBean.getMatchedSelectedSupervisionCategory()) 
		{
        	    cases = caseFileCollection.iterator();
        	    
		    while (cases.hasNext())
		    {
			JuvenileProfileCasefileListResponseEvent juvenileCase = (JuvenileProfileCasefileListResponseEvent)cases.next();
			
			if(juvenileCase != null && juvenileCase.getCaseStatus() != null && !juvenileCase.getCaseStatus().equals("") && 
				juvenileCase.getSupervisionNum() != null && !juvenileCase.getSupervisionNum().equals("") &&
					programReferralBean != null && !programReferralBean.getCasefileId().equals(""))
			{ 
        			if ((juvenileCase.getCaseStatus().equals("ACTIVE")) &&
        				(!juvenileCase.getSupervisionNum().equals(programReferralBean.getCasefileId())))
        			{
        					activeCasefiles.add(juvenileCase);
        			}
			}
		    }
		    
		}
		
        	Collections.sort(activeCasefiles);
		programReferralBean.setCasefiles(activeCasefiles);
			
		return aMapping.findForward( UIConstants.TRANSFER_SUCCESS ) ;
	}	
	

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward showEventDetails( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		String eventId = (String)aRequest.getParameter( "eventId" ) ;
		ProgramReferralForm form = (ProgramReferralForm)aForm ;

		if( notNullNotEmptyString( eventId ) ) 
		{
			ArrayList attributes = new ArrayList() ;
			ServiceEventAttribute sa = new ServiceEventAttribute() ;
			sa.setServiceEventId( new Integer( eventId ) ) ;
			attributes.add( sa ) ;

			GetCalendarServiceEventsEvent gce = (GetCalendarServiceEventsEvent)
					EventFactory.getInstance( ServiceEventControllerServiceNames.GETCALENDARSERVICEEVENTS ) ;
			
			gce.setCalendarAttributes( attributes ) ;
			
			CalendarContextEvent context = new CalendarContextEvent();
			context.setJuvenileId(form.getProgramReferral().getJuvenileId());
			gce.setCalendarContextEvent(context);
			
			CompositeResponse response = (CompositeResponse)MessageUtil.postRequest( gce ) ;
			CalendarServiceEventResponseEvent resp = (CalendarServiceEventResponseEvent)
					MessageUtil.filterComposite( response, CalendarServiceEventResponseEvent.class ) ;
			if( resp != null )
			{
				form.setCurrentServiceEvent( resp ) ;
			}
		}

		return( aMapping.findForward( UIConstants.DETAIL_SUCCESS ) ) ;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 447F49960111
	 */
	public ActionForward returnToReferralList( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
	    ProgramReferralForm form = (ProgramReferralForm)aForm;
	    
	    //clear certain fields
	    form.setOutComeSubcategoryReqdCd(null);
	    form.setOutComeSubcategoryOptCd(null);
	    
	    return( aMapping.findForward( UIConstants.CANCEL ) ) ;

	}

	/*
	 * @param str
	 * @return
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null && str.length() > 0  ) ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping( Map keyMap )
	{
		keyMap.put( "button.details", "showEventDetails" ) ;
		keyMap.put( "button.acceptWithChanges", "acceptWithChanges" ) ;
		keyMap.put( "button.accept", "accept" ) ;
		keyMap.put( "button.update", "update" ) ;
		keyMap.put( "button.cancelReferral", "cancelReferral" ) ;
		keyMap.put( "button.withdraw", "withdraw" ) ;
		keyMap.put( "button.closeReferral", "complete" ) ;
		keyMap.put( "button.submit", "submitChanges" ) ;
		keyMap.put( "button.finish", "finish" ) ;
		keyMap.put( "button.addComments", "addComments" ) ;
		keyMap.put( "button.returnToReferralList", "returnToReferralList" ) ;
		keyMap.put( "button.transferReferral", "transfer" ) ;
		keyMap.put( "button.fix", "fix" ) ;
	}

}
