package ui.juvenilecase.interviewinfo.action;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.interviewinfo.GetInterviewQuestionsEvent;
import messaging.interviewinfo.reply.InterviewQuestionResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm.InterviewQuestion;

/**
 * 
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayJuvInterviewQuestionsAction extends LookupDispatchAction
{
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.next", "next");
		return keyMap;
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
	
	public ActionForward back(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}
		
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;
		
		GetInterviewQuestionsEvent event = new GetInterviewQuestionsEvent();
		
		List ids = event.getCategoryIds();
		
		String[] selectedCategories = form.getCurrentInterview().getSelectedCategories();
		if(selectedCategories != null && selectedCategories.length > 0)
		{
			ids.addAll(Arrays.asList(selectedCategories));
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(event);
			Collection qres = MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), InterviewQuestionResponseEvent.class );
			Collection interviewQuestions = new ArrayList();
			
			//copy Response Event into UI Object
			if(qres != null && qres.size() > 0)
			{
				for(Iterator qresIter = qres.iterator(); qresIter.hasNext();)
				{
					InterviewQuestionResponseEvent qre = (InterviewQuestionResponseEvent)qresIter.next();
					InterviewQuestion q = form.new InterviewQuestion(qre);
					interviewQuestions.add(q);
				}
				form.getCurrentInterview().setInterviewQuestions(interviewQuestions);
			}
			
				
		}
		
		form.setAction(UIConstants.NEXT);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	
		
	
}