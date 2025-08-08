package ui.juvenilecase.interviewinfo.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.interviewinfo.CompleteInterviewTaskEvent;
import messaging.interviewinfo.reply.InterviewDetailResponseEvent;
import messaging.interviewinfo.reply.InterviewTaskResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.interviewinfo.form.InterviewChecklistPrintBean;
import ui.juvenilecase.interviewinfo.form.JuvenileInterview;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;

/**
 * @author awidjaja
 *
 */
public class SubmitCompleteJuvInterviewTasksAction extends JIMSBaseAction
{
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.completeSelectedTasks", "completeSelectedTasks");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.generateChecklist", "generateChecklist");
		
	}
	
	public ActionForward generateChecklist(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm)aForm;
		String templateName = "REPORTING::INTERVIEW_TASK_CHECKLIST";
		
		InterviewChecklistPrintBean printBean = new InterviewChecklistPrintBean();
		printBean.setJuvenileName(form.getJuvenileName());
		printBean.setJuvenileNumber(form.getJuvenileNum());
		printBean.setCasefileNumber(form.getCasefileId());
		printBean.setChecklist(form.getCurrentInterview().getInterviewTasks());
				
		StringBuffer sb = new StringBuffer();
		sb.append(aRequest.getScheme()+"://");
		sb.append(aRequest.getServerName());
		sb.append(":");
		sb.append(aRequest.getServerPort());
		printBean.setUrlPrefix(sb.toString());
		
		
		
		CompositeResponse compResp = sendPrintRequest(templateName, 
				printBean, null);
		try {
			setPrintContentResp(aResponse, compResp, "INTERVIEW_TASK_CHECKLIST", UIConstants.PRINT_AS_PDF_DOC);
		}
		catch(GeneralFeedbackMessageException e) {
			sendToErrorPage(aRequest, "");
		}
		return aMapping.findForward(null);
		
	}
	
	public ActionForward completeSelectedTasks(
				ActionMapping aMapping,
				ActionForm aForm,
				HttpServletRequest aRequest,
				HttpServletResponse aResponse)
	{
		String forward = UIConstants.SUCCESS;
		
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm; 
		JuvenileInterview interview = form.getCurrentInterview();
		
		String[] selectedTasks = form.getCurrentInterview().getSelectedTasks();
		
		if(selectedTasks == null || selectedTasks.length < 1)
		{
			return aMapping.findForward(forward);
		}
		
		CompleteInterviewTaskEvent event = new CompleteInterviewTaskEvent();
		List ids = event.getTaskIds();
		
		event.setInterviewId( form.getCurrentInterview().getInterviewId() );
		
		if(selectedTasks != null && selectedTasks.length > 0)
		{
			for(int i=0; i<selectedTasks.length;i++)
			{
				ids.add( selectedTasks[i] );
			}
		}
		
		
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		
		for(Iterator taskIter = form.getCurrentInterview().getInterviewTasks().iterator(); taskIter.hasNext();) {
			InterviewTaskResponseEvent taskRE = (InterviewTaskResponseEvent) taskIter.next();
			if(ids.contains(taskRE.getTaskId())){
				taskRE.setCompleted(true);
			}
		}
		
		
		InterviewDetailResponseEvent interviewRE = (InterviewDetailResponseEvent)
			MessageUtil.filterComposite( (CompositeResponse)dispatch.getReply(), InterviewDetailResponseEvent.class );
		form.getCurrentInterview().setInterviewStatusCd(interviewRE.getInterviewStatusCd());
		form.getCurrentInterview().setInterviewStatusDescription(interviewRE.getInterviewStatusDescription());
		
		
		return aMapping.findForward(forward);
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
		
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
	HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}


	
	
	
		
}