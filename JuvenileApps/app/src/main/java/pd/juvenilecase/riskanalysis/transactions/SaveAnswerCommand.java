package pd.juvenilecase.riskanalysis.transactions;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;

import naming.PDConstants;
import naming.RiskAnalysisConstants;

import pd.juvenilecase.riskanalysis.RiskQuestions;
import pd.juvenilecase.riskanalysis.RiskWeightedResponse;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import messaging.riskanalysis.SaveAnswerEvent;
import messaging.riskanalysis.reply.SaveAnswerResponseEvent;
/**
 * 
 * @author palcocer
 * When creating answers, command expects to take in a single SaveAnswerEvent that 
 * has either a single or multiple SaveAnswerEvent(s) within the request attribute
 * 
 * When updating answers, command expects to take in a single SaveAnswerEvent 
 * with the no SaveAnswerEvent(s) in the request attribute. The attributes on 
 * the SaveAnswerEvent coming in will be used since only one answer can be updated
 * at a time.
 * 
 * (see code below) 
 */
public class SaveAnswerCommand implements ICommand
{
	
	public void execute(IEvent anEvent)
	{
		//Get Single Answer Event
		SaveAnswerEvent saveAnswerEvents = (SaveAnswerEvent)anEvent;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		//Creates Multiple Answers
		if (saveAnswerEvents.isACreate()) 
		{
		
			//Cycles through the Answer events request attribute (SaveAnswerEvent(s)) and inserts them into the database
			Enumeration events = saveAnswerEvents.getRequests();
			while (events.hasMoreElements())
			{
				Object obj = events.nextElement();
				if( obj instanceof SaveAnswerEvent ) 
				{	
					
					SaveAnswerEvent saveAnswerEvent = (SaveAnswerEvent)obj;
					
					RiskWeightedResponse riskWeightedResponse =  new RiskWeightedResponse();
					
					//Set riskWeightedResponse with saveAnswerEvent
					setRiskWeightedReponse(saveAnswerEvent,
							riskWeightedResponse);
					
					IHome homeRiskWeightedResponse=new Home();
					homeRiskWeightedResponse.bind(riskWeightedResponse);
					
					//Set saveAnswerResponseEvent with riskWeightedResponse
					SaveAnswerResponseEvent saveAnswerResponseEvent = setSaveAnswerReponseEvent(riskWeightedResponse);
					
					dispatch.postEvent(saveAnswerResponseEvent);
					
				}
			}
			
		}
		//Updates Single Answer
		else
		{
			RiskWeightedResponse riskWeightedResponse =  RiskWeightedResponse.find(saveAnswerEvents.getRiskAnswerId()); 
			
			//Set riskWeightedResponse with saveAnswerEvent
			setRiskWeightedReponse(saveAnswerEvents,
					riskWeightedResponse);
			
			IHome homeRiskWeightedResponse=new Home();
			homeRiskWeightedResponse.bind(riskWeightedResponse);
			
			//Set saveAnswerResponseEvent with riskWeightedResponse
			SaveAnswerResponseEvent saveAnswerResponseEvent = setSaveAnswerReponseEvent(riskWeightedResponse);
			
			dispatch.postEvent(saveAnswerResponseEvent);

		}
	}

	private SaveAnswerResponseEvent setSaveAnswerReponseEvent(
			RiskWeightedResponse riskWeightedResponse) {
		SaveAnswerResponseEvent saveAnswerResponseEvent = new SaveAnswerResponseEvent();
		saveAnswerResponseEvent.setRiskAnswerId(riskWeightedResponse.getOID());
		saveAnswerResponseEvent.setAction(riskWeightedResponse.getAction());
		saveAnswerResponseEvent.setAnswerEntryDate(RiskAnalysisConstants.DATE_FORMAT.format(riskWeightedResponse.getCreateTimestamp()));
		saveAnswerResponseEvent.setAnswerText(riskWeightedResponse.getResponse());
		saveAnswerResponseEvent.setSortOrder(String.valueOf(riskWeightedResponse.getSortOrder()));
		
		saveAnswerResponseEvent.setSubordinateQuestionId(riskWeightedResponse.getSubordinateQuestionId());
		if (saveAnswerResponseEvent.getSubordinateQuestionId() != null 
				&& !saveAnswerResponseEvent.getSubordinateQuestionId().equals(PDConstants.BLANK))
		{
			RiskQuestions question = RiskQuestions.find(saveAnswerResponseEvent.getSubordinateQuestionId());
			saveAnswerResponseEvent.setSubordinateQuestionName(question.getQuestionName());
		}
		
		saveAnswerResponseEvent.setWeight(String.valueOf(riskWeightedResponse.getWeight()));
		return saveAnswerResponseEvent;
	}

	private void setRiskWeightedReponse(SaveAnswerEvent saveAnswerEvent,
			RiskWeightedResponse riskWeightedResponse) {
		
		riskWeightedResponse.setResponse(saveAnswerEvent.getAnswerText());
		riskWeightedResponse.setAction(saveAnswerEvent.getAction());
		riskWeightedResponse.setRiskQuestionId(saveAnswerEvent.getRiskQuestionId());
		if (saveAnswerEvent.getSortOrder() != null & saveAnswerEvent.getSortOrder().length() > 0) 
		{ 
			riskWeightedResponse.setSortOrder(Integer.parseInt(saveAnswerEvent.getSortOrder()));
		}
		riskWeightedResponse.setResponse(saveAnswerEvent.getAnswerText());
		if (saveAnswerEvent.getSubordinateQuestionId() != null & !saveAnswerEvent.getSubordinateQuestionId().equals(PDConstants.BLANK)) 
		{ 
			riskWeightedResponse.setSubordinateQuestionId(saveAnswerEvent.getSubordinateQuestionId());
		}
		if (saveAnswerEvent.getWeight() != null & saveAnswerEvent.getWeight().length() > 0) 
		{ 
			riskWeightedResponse.setWeight(Integer.parseInt(saveAnswerEvent.getWeight()));
		}
		riskWeightedResponse.setCreateUserID(saveAnswerEvent.getCreateUserID());
		riskWeightedResponse.setCreateTimestamp(new Timestamp((new Date()).getTime()));
		
		//riskWeightedResponse.setSuggestedCasePlainDomiainId(); will eventually add this
	}

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}
