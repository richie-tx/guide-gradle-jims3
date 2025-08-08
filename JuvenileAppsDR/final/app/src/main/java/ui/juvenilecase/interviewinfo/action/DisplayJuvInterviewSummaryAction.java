package ui.juvenilecase.interviewinfo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.utilities.DateUtil;
import naming.PDCalendarConstants;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm.PersonResponsible;
import messaging.interviewinfo.reply.InterviewPersonResponseEvent;

/**
 * 
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayJuvInterviewSummaryAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap() ;
		keyMap.put( "button.back", "back" ) ;
		keyMap.put( "button.cancel", "cancel" ) ;
		keyMap.put( "button.submit", "submit" ) ;
		keyMap.put( "button.validate", "validate" ) ;
		return keyMap ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		return( aMapping.findForward( UIConstants.CANCEL ) ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{		
		return( aMapping.findForward( UIConstants.BACK ) ) ;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward submit( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm)aForm ;
		
		//added for Bug #32185
		String temp="";
		for( int i = 0; i < form.getCurrentInterview().getPersonsInterviewed().length; i++ )
		{
			temp+=form.getCurrentInterview().getPersonsInterviewed()[i]+":";			
		}
		form.getCurrentInterview().setPersonsInterviewedStr(temp);
		if(form.getAction().equals("record"))
		{
			form.setAction("recordSumm");
			HashMap persons = new HashMap();
			String[] personsInterviewed = form.getCurrentInterview().getPersonsInterviewed();
			for( int i = 0; i < personsInterviewed.length; i++ )
			{
				for( Iterator iter = form.getCurrentInterview().getPersonsInterviewedList().iterator(); iter.hasNext(); /* empty */)
				{
					InterviewPersonResponseEvent persResponsible = (InterviewPersonResponseEvent)iter.next();
				
					if(persResponsible!=null)
					{
						String fullName = persResponsible.getFormattedName().trim() +" ("+persResponsible.getRelationship()+")";
						if( personsInterviewed[i] != null && personsInterviewed[i].equalsIgnoreCase(fullName) && 
								persons.get(fullName) == null )
						{
							persons.put( fullName, persResponsible );
						}
					}
				}
			}
			List personsList = new ArrayList();
			personsList.addAll(persons.values());
			Collections.sort(personsList);
			form.getCurrentInterview().setSelectedPersonsInterviewed(personsList);
		}
		
		{ String interviewType = CodeHelper.getCodeDescriptionByCode( 
					form.getCurrentInterview().getInterviewTypeList(), 
					form.getCurrentInterview().getInterviewTypeId() ) ;
			form.getCurrentInterview().setInterviewType( interviewType ) ;

			// combine interviewDate and interviewTime into a single date obj
			Date currentInterviewDate = form.getCurrentInterview().getInterviewDate() ;
			if( currentInterviewDate == null )
			{
				currentInterviewDate = new Date() ;
			}
	
			String interviewDateStr = DateUtil.dateToString( currentInterviewDate, UIConstants.DATE_FMT_1 ) ;
	
			Date interviewDate = null ; 
			if( notNullNotEmptyString( interviewDateStr ) )
			{
				interviewDate = DateUtil.stringToDate( interviewDateStr + " " + 
						form.getCurrentInterview().getInterviewTimeStr(), UIConstants.DATETIME_FMT_1AMPM ) ;
			}
			
			if( interviewDate == null )
			{
				interviewDate = new Date() ;
			}
			form.getCurrentInterview().setInterviewDate( interviewDate ) ;
		}

		
		//translate from ids -> user friendly display
		Collection displayList = new ArrayList() ;
		Collection inspectedCodeList = new ArrayList() ;
		String [ ] recordsInventoryList = form.getCurrentInterview().getRecordsInventoryIds() ;

		if( recordsInventoryList != null && recordsInventoryList.length > 0 )
		{
			for( String item : recordsInventoryList )
			{
				if( notNullNotEmptyString( item ) &&  !item.equals( "other" ) )
				{
					// only save non-empty values into inspectedCodeList
					inspectedCodeList.add( item ) ;
					String displayStr = CodeHelper.getCodeDescriptionByCode( 
							form.getRecordsInventoryList(), item ) ;
					
					if( notNullNotEmptyString( displayStr ) )
					{
						displayList.add( displayStr ) ;
					}
				}
			} // for
			form.getCurrentInterview().setRecordsInventoryDisplay( displayList ) ;
		}

		//get rid of empty string values
		recordsInventoryList = (String [ ])inspectedCodeList.toArray( new String [ 0 ] ) ;
		form.getCurrentInterview().setRecordsInventoryIds( recordsInventoryList ) ;
		
		//<KISHORE>JIMS200059211 : MJCW: Document attendance for all int.types(UI)-KK
		if(( PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED ).equalsIgnoreCase(form.getAttendanceStatusCd())||
				( PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT ).equalsIgnoreCase(form.getAttendanceStatusCd()) )
		{ 
			// Excused or Absent, so clear out these values
			clearExtraValues( form ) ;
		}
		else if( form.getSelectedAttendeeNames() != null && 
					form.getSelectedAttendeeNames().length > 0)
		{
			StringBuffer names = new StringBuffer( UIConstants.EMPTY_STRING );
			for( String str : form.getSelectedAttendeeNames() )
			{
				if(notNullNotEmptyString(str))
				{
					names.append( str ).append( ";" );
				}
			}
			form.setSelectedAttendeeNamesAsString( names.toString() );
		}
		
		if (form.getAttendanceStatusCd() == null || form.getAttendanceStatusCd().equals("")) {
			ActionMessage myError=new ActionMessage("error.casefileUnavailable",form.getCasefileId());
			ArrayList coll=new ArrayList();
			coll.add(myError);
			sendToErrorPage(aRequest,coll);
			return aMapping.findForward(UIConstants.CANCEL);
		} 
		
		form.setAttendanceStatus(CodeHelper.getCode(
				PDCodeTableConstants.SERVEVENT_ATTENDANCE_STATUS,form.getAttendanceStatusCd()).getDescription());

		form.setStatus( UIConstants.SUMMARY ) ;
		
		return aMapping.findForward( UIConstants.SUCCESS ) ;
	}
	
	/* given a string, return true if str is not null and not empty.
	 * @param str
	 * @return
	 */
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  str.length() > 0 ) ;
	}
	
	/* 
	 * if the attend status is excused or absent, clear out
	 * the values that don't make sense.
	 */
	private void clearExtraValues( JuvenileInterviewForm form )
	{
		if( form.getSelectedAttendeeNames() != null )
		{
			String[] mystr = new String[0] ;
			form.setSelectedAttendeeNames( mystr ) ;
		}

		if( form.getSelectedNamesList() != null )
		{
			form.getSelectedNamesList().clear() ;
		}

		if( notNullNotEmptyString(form.getAddlAttendees()))
		{
			form.setAddlAttendees( UIConstants.EMPTY_STRING ) ;
		}
		
		if( notNullNotEmptyString(form.getSelectedAttendeeNamesAsString()))
		{
			form.setSelectedAttendeeNamesAsString( UIConstants.EMPTY_STRING ) ;
		}
	}

	/**
	* @param aRequest
	*/
   private void sendToErrorPage(HttpServletRequest aRequest, Collection aActionErrors)
   {
		ActionErrors errors = new ActionErrors();
		if(aActionErrors!=null && aActionErrors.size()>0){
			Iterator i=aActionErrors.iterator();
			while(i.hasNext()){
				ActionMessage error=(ActionMessage)i.next();
				errors.add(ActionErrors.GLOBAL_MESSAGE,error);
			}
		   saveErrors(aRequest, errors);
		}
   }
}