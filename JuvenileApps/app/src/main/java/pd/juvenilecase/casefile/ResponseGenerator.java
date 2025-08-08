/*
 * Created on December 07, 2005
 * @author mchowdhury
 */
package pd.juvenilecase.casefile;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import messaging.casefile.GetResponseEvent;
import messaging.casefile.UpdateCasefileClosingEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.casefile.reply.QuestionAnswerResponseEvent;
import messaging.casefile.reply.QuestionGroupResponseEvent;
import messaging.casefile.reply.QuestionResponseEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import mojo.km.config.AppProperties;
import mojo.km.config.EmptyMappingPropertyException;
import mojo.km.config.LoadMappingPropertyException;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDJuvenileCaseConstants;

public final class ResponseGenerator
{
	/**
	 * 
	 */
	public ResponseGenerator()
	{
	}

	/**
	 * @param iter
	 * @param responseEvent
	 */
	public void postGenaratedResponse(Iterator iter, GetResponseEvent responseEvent)
	{
		SortedMap map = this.getResponses(iter);
		
		String responseTemplateLocation = responseEvent.getResponseTemplateLocation();
		if(responseTemplateLocation == null || responseTemplateLocation.equals("")){
			responseTemplateLocation = AppProperties.getInstance().getProperty(responseEvent.getResponseType().toLowerCase());	
		}
		
		XMLQuestionAnswerPropertyAdapter adapter = new XMLQuestionAnswerPropertyAdapter(); 
		QuestionAnswerResponseEvent questionAnswerResponseEvent = null;
		try
		{
			questionAnswerResponseEvent = adapter.loadXML(responseTemplateLocation);
			Iterator iterator = questionAnswerResponseEvent.getQuestionGroupResponseEvents().iterator();
			while(iterator.hasNext()){
				QuestionGroupResponseEvent qGREvent = (QuestionGroupResponseEvent) iterator.next();
				Iterator questionIter = qGREvent.getQuestionResponseEvents().iterator();
				while(questionIter.hasNext()){
					QuestionResponseEvent qREvent = (QuestionResponseEvent) questionIter.next();
					
					Response resp = (Response) map.get(qREvent.getId());
					if(resp != null) {
						qREvent.setResponseId(resp.getOID().toString());
						qREvent.setRealAnswerText(resp.getAnswerText());
					}
				}
			}
		}
		catch (LoadMappingPropertyException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (EmptyMappingPropertyException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Clear map
		map.clear();
		if(questionAnswerResponseEvent != null){
			questionAnswerResponseEvent.setTopic(PDJuvenileCaseConstants.QUESTION_ANSWER_EVENT_TOPIC);
			PDCasefileClosingHelper.sendResponseEvent(questionAnswerResponseEvent);
		}
		
	}

	/**
	 * @param iter
	 * @return
	 */
	private SortedMap getResponses(Iterator iter)
	{
		SortedMap map = new TreeMap();
		while(iter.hasNext()){
			Response response = (Response) iter.next();
			map.put(response.getQuestionId(),response);
		}
		return map;
	}
}
