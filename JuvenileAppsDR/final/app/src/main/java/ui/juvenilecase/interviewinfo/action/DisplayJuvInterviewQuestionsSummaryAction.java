package ui.juvenilecase.interviewinfo.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.interviewinfo.CompleteInterviewEvent;
import messaging.interviewinfo.CreateInterviewTaskListEvent;
import messaging.interviewinfo.SaveInterviewEvent;
import messaging.interviewinfo.reply.InterviewTaskResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.InterviewConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.interviewinfo.form.JuvenileInterview;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm.InterviewQuestion;

/**
 * 
 * @author awidjaja
 *
 */
public class DisplayJuvInterviewQuestionsSummaryAction extends JIMSBaseAction
{
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.next", "next");
		keyMap.put("button.finish", "finish");
		keyMap.put("button.back", "back");
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward back( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.BACK) );
	}

	/*
	 * (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#cancel(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward cancel( ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.CANCEL) );
	}

	/*
	 * 
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;
	
		form.setAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/*
	 * 
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;
		CreateInterviewTaskListEvent event = new CreateInterviewTaskListEvent();
		
		event.setInterviewId( form.getCurrentInterview().getInterviewId());
		List ids = event.getQuestionIds();
		if(ids == null)
		{
			ids = new ArrayList();
		}
		
		//Extract the questions that are positively answered.
		Collection<InterviewQuestion> questions = form.getCurrentInterview().getInterviewQuestions() ;
		if( questions != null )
		{
			for( InterviewQuestion aQuestion : questions )
			{
				if( aQuestion.isSelected() )
				{
					ids.add( aQuestion.getQuestionId() );
				}
			}
		}
		
		// mark the interview completed if there's no positively answered question.
		if(ids == null || ids.isEmpty() )
		{
			CompleteInterviewEvent completeInterviewEvent = new CompleteInterviewEvent();
			completeInterviewEvent.setInterviewId( form.getCurrentInterview().getInterviewId() );
			
			CompositeResponse response = postRequestEvent(completeInterviewEvent);
			form.getCurrentInterview().setInterviewStatusCd(InterviewConstants.INTERVIEW_STATUS_COMPLETE);
		}
		else
		{
			CompositeResponse response = postRequestEvent(event);
			ErrorResponseEvent error = (ErrorResponseEvent) 
					MessageUtil.filterComposite(response, ErrorResponseEvent.class);
			if(error != null)
			{
				sendToErrorPage(aRequest, error.getMessage());
				return aMapping.findForward(UIConstants.FAILURE);
			}
			
			Collection tasks = MessageUtil.compositeToCollection( response, InterviewTaskResponseEvent.class );
			Collections.sort((List)tasks);
			form.getCurrentInterview().setInterviewTasks(tasks);
			form.getCurrentInterview().setTaskCount( tasks.size() ) ;
			form.getCurrentInterview().setInterviewStatusCd(InterviewConstants.INTERVIEW_STATUS_INCOMPLETE);
			
			updateInterview( form ) ;
		}
		
		form.setAction(UIConstants.CONFIRM);
		return( aMapping.findForward(UIConstants.CREATE_SUCCESS) );
	}
	
	/*
	 * 
	 */
	private boolean updateInterview( JuvenileInterviewForm jiForm )
	{
		JuvenileInterview interview = jiForm.getCurrentInterview() ;
		SaveInterviewEvent event = new SaveInterviewEvent() ;

		event.setInterviewId( interview.getInterviewId() ) ;
		event.setCasefileId( jiForm.getCasefileId() ) ;
		event.setInterviewDate( interview.getInterviewDate() ) ;
		event.setCalEventId( interview.getCalEventId() ) ;
		event.setJuvenileNum( jiForm.getJuvenileNum() ) ;
		
		{ List records = event.getInventoryRecordsIds() ;
			records.clear() ;
			records.addAll( Arrays.asList( interview.getRecordsInventoryIds() ) ) ;
		}

		event.setOtherInventoryRecords( interview.getOtherInventoryRecords() ) ;
		event.setSummaryNotes( interview.getSummaryNote() ) ;
		event.setUserComments(interview.getUserComments());
		event.setProgressReport(interview.getProgressReport());

		{ String locUnit = interview.getJuvLocUnitId().trim();
			if( locUnit.equals( "newaddress" ) )
			{
				event.setAddress( interview.getNewAddress() ) ;
			}
			else
			{
				event.setJuvLocUnitId( locUnit ) ;
				event.setLocationDescription( interview.getJuvLocUnitDescription() ) ;

				// set address to null so that a new entry wouldnt be created for custom address
				event.setAddress( null ) ;
			}
		}

		/* incomplete for scheduled interview and complete for unscheduled interviews. 
		 */
		event.setInterviewStatusCd( InterviewConstants.INTERVIEW_STATUS_INCOMPLETE ) ;

		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
		dispatch.postEvent( event ) ;

		// Get PD Response Event
		CompositeResponse response = (CompositeResponse)dispatch.getReply() ;
		MessageUtil.processReturnException( response ) ; // Perform Error handling
		
		return( true ) ;		
	}
	
}	