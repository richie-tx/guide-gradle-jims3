package ui.juvenilecase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.ujac.util.BeanComparator;

import antlr.StringUtils;

import ui.common.Name;
import ui.common.StringUtil;
import ui.security.SecurityUIHelper;
import messaging.juvenilecase.reply.RiskAnswerResponseEvent;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.juvenilecase.reply.RiskWeightedResponseEvent;
import messaging.riskanalysis.RiskQuestionAnswerEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.IUserInfo;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

public class UIRiskAnalysisHelper
{
	/**
	 * DRAFT IMPLEMENTATIon! NEEDS TO BE RE-WORKED/IMPROVED FOR MULTIPLE AND
	 * CHRONIC ANSWERS Merges User responses with their respective Questions for
	 * display
	 *  
	 * @param responseGroupIter
	 * @param questionAnswerGroupIter
	 * @return
	 */
	public static List mergeUserReponsesWithQuestions(
			RiskQuestionResponseEvent[] riskQuestionResponseEvents, 
			RiskAnswerResponseEvent[] riskAnswerResponseEvents )
	{
		List finalList = new ArrayList(); // Final List of Questions & Answers
		RiskQuestionResponseEvent question = null;
		RiskAnswerResponseEvent response = null;
		for(int i = 0; i < riskQuestionResponseEvents.length; i++)
		{
			question = riskQuestionResponseEvents[i];

			for(int j = 0; j < riskAnswerResponseEvents.length; j++)
			{
				response = riskAnswerResponseEvents[j];

				int reQuestionID = 0 ;
				try
				{
					reQuestionID = Integer.parseInt( response.getQuestionID() ) ;
				}
				catch( NumberFormatException nfe )
				{
					reQuestionID = 0 ;
				}
				
				if( question.getQuestionID() ==  reQuestionID )
				{
					//A CHECKBOX UIControlType means there are possible multiple answers
					//for the questions.
					if (question.getUiControlType().equalsIgnoreCase(UIConstants.CHECKBOX) 
							|| question.getUiControlType().equalsIgnoreCase(UIConstants.CHECKBOXWITHCHRONIC)
							|| question.getUiControlType().equalsIgnoreCase(UIConstants.LISTBOX)) {
						
						//Means the SelectedAnswerIDs array has not yet been populated.
						if (question.getSelectedAnswerIDs() == null) {
							if (response.getWeightIDs().size() > 0){
								//Executes this on first update display
								String[] selectedAnswerIDs = new String[response.getWeightIDs().size()];

								List <String> aList = response.getWeightIDs();
								for (int k = 0; k < aList.size(); k++) {
									String answerId = aList.get(k);
									selectedAnswerIDs [k]= answerId;
								}
								question.setSelectedAnswerIDs(selectedAnswerIDs);
								question.setSelectedAnswerWeight( String.valueOf(response.getWeight()));
								if (response.getChronicIDs().size() > 0){
									String[] selectedChronicIDs = new String[response.getChronicIDs().size()];
									aList = response.getChronicIDs();
									for (int k = 0; k < aList.size(); k++) {
										String chronicId = aList.get(k);
										selectedChronicIDs [k]= chronicId;
									}
									question.setSelectedChronicIDs(selectedChronicIDs);
									//question.setSelectedChronicID(String.valueOf(response.getWeight()));
								} else {
									question.setSelectedChronicIDs(new String[0]);
								}
								
							} else {
								question.setSelectedAnswerIDs(new String[0]);
//								String selectedAnswerID = response.getWeightID();
//								selectedAnswerIDs[0] = selectedAnswerID;
//								question.setSelectedAnswerIDs(selectedAnswerIDs);
								//question.setSelectedAnswerWeight( String.valueOf(response.getWeight() ) );
							}
						
						//Means the SelectedAnswerIDs array has already been populated.
						// The following code adds to this array.
						} else {
								
							/** The code below in this "else" is in prototype mode, will not know if it works until used with a question
							*   that has multiple possible answers.
							**/
							int oldSelectedAnswerIDsArrayLength = question.getSelectedAnswerIDs().length;
							int newSelectedAnswerIDsArrayLength = 1 + oldSelectedAnswerIDsArrayLength;
							int newSelectedAnswerIDPosition = newSelectedAnswerIDsArrayLength - 1;
							
							int totalWeight = Integer.parseInt(question.getSelectedAnswerWeight());
							
							String[] newSelectedAnswerIDsArray = new String[newSelectedAnswerIDsArrayLength];
							String[] oldSelectedAnswerIDsArray = question.getSelectedAnswerIDs();
							
							//Takes values from old array and places them into new array.
							for(int v = 0; v < oldSelectedAnswerIDsArray.length; v++) {
								newSelectedAnswerIDsArray[v] = oldSelectedAnswerIDsArray[v];
							}
							newSelectedAnswerIDsArray[newSelectedAnswerIDPosition] = response.getWeightID();
							
							question.setSelectedAnswerIDs(newSelectedAnswerIDsArray);
							
							totalWeight = totalWeight + response.getWeight();						    
							question.setSelectedAnswerWeight( String.valueOf(totalWeight) );
							
						}
						
					} else if ( question.getUiControlType().equalsIgnoreCase(UIConstants.TEXTAREA) || 
							question.getUiControlType().equalsIgnoreCase(UIConstants.TEXTBOX) || 
							question.getUiControlType().equalsIgnoreCase(UIConstants.DATE) ) {
						
						question.setSelectedAnswerID( response.getWeightID() );
						question.setSelectedAnswerWeight( String.valueOf(response.getWeight() ) );
						
						if (response.getAnswerText() != null && response.getAnswerText().length() > 0) {
							
							question.setUseAnswerText(true);
							//the following needs to be set for questions without ControlCode.
							question.setSelectedAnswerID(response.getAnswerText());
							
							//Iterator<RiskWeightedResponseEvent> answersIter = question.getAnswers().iterator();
							List <RiskWeightedResponseEvent> answersList = CollectionUtil.iteratorToList(question.getAnswers().iterator());
							for (int k = 0; k < answersList.size(); k++) {
									//while ( answersIter.hasNext() ) {
								//RiskWeightedResponseEvent riskWeightedResponseEvent = answersIter.next();
								RiskWeightedResponseEvent riskWeightedResponseEvent = answersList.get(k);
								 
								if (String.valueOf(riskWeightedResponseEvent.getWeightedResponseID()).equalsIgnoreCase(response.getWeightID()) ) {
									riskWeightedResponseEvent.setAnswerText(response.getAnswerText());
									if  (question.getUiControlType().equals(UIConstants.TEXTAREA)
											|| (question.getControlCode() != null && question.getControlCode().length() > 0)) {
										question.setSelectedAnswerID(response.getAnswerText());
									} else {
										riskWeightedResponseEvent.setAnswerText(response.getAnswerText());
										question.setSelectedAnswerID(response.getWeightID());
									}
									break;
								}
							}
						} 
						
					
					} else if ( ( question.getUiControlType().equalsIgnoreCase(UIConstants.DROPDOWN) || 
							question.getUiControlType().equalsIgnoreCase(UIConstants.RADIO) )
							&& (response.getAnswerText() != null && response.getAnswerText().length() > 0) ) {
						
						question.setSelectedAnswerID( response.getWeightID() );
						question.setSelectedAnswerWeight( String.valueOf(response.getWeight() ) );
						question.setUseAnswerText(true);
						
						//Iterator<RiskWeightedResponseEvent> answersIter = question.getAnswers().iterator();
						List <RiskWeightedResponseEvent>  answersList = CollectionUtil.iteratorToList(question.getAnswers().iterator());
						//while ( answersIter.hasNext() ) {
						for (int k = 0; k < answersList.size(); k++) {
							//RiskWeightedResponseEvent riskWeightedResponseEvent = answersIter.next();
							RiskWeightedResponseEvent riskWeightedResponseEvent = answersList.get(k);
							if (String.valueOf(riskWeightedResponseEvent.getWeightedResponseID()).equalsIgnoreCase(response.getWeightID()) ) {
								riskWeightedResponseEvent.setAnswerText(response.getAnswerText());
								break;
							}
						}
						
					} else {
						question.setSelectedAnswerID( response.getWeightID() );
						question.setSelectedAnswerWeight( String.valueOf(response.getWeight() ) );
					}
					break;
				}
			}

			finalList.add( question );
		}

		return finalList;
	}

	/**
	 * Called by the Interview Assessment Action
	 * 
	 * @param response
	 * @return
	 */
	public static List groupQuestionsAndAnswers( CompositeResponse response )
	{
		List questions = MessageUtil.compositeToList( response, RiskQuestionResponseEvent.class );
		Collections.sort( questions );
		List answers = MessageUtil.compositeToList( response, RiskWeightedResponseEvent.class );

		//Iterator<RiskWeightedResponseEvent> rwreIte = answers.iterator();
		List <RiskWeightedResponseEvent> rwreList = CollectionUtil.iteratorToList(answers.iterator());

		RiskWeightedResponseEvent answer = null;
		RiskQuestionResponseEvent question = null;
		for (int i = 0; i < rwreList.size(); i++)
//			array_type array_element = array[i];
//			
//		}
//		while(rwreIte.hasNext())
		{
			//answer = rwreIte.next();
			answer = rwreList.get(i);
			String questionID = answer.getRiskQuestionsId();
			question = filterQuestionByID( questionID, questions );
			if( question != null )
			{
				question.addAnswer( answer );
			}
		}

		//Iterator<RiskQuestionResponseEvent> iteQues = questions.iterator();
		List <RiskQuestionResponseEvent> quesList = CollectionUtil.iteratorToList(questions.iterator());
		RiskQuestionResponseEvent sortedQuestion = null;		
//		while(iteQues.hasNext())
//		{
		for (int i = 0; i < quesList.size(); i++) 
		{
			//RiskQuestionResponseEvent sortedQuestion = iteQues.next();
			sortedQuestion = quesList.get(i);
			Collections.sort( (ArrayList)sortedQuestion.getAnswers() );
		}

		return questions;
	}

	/*
	 * 
	 */
	private static RiskQuestionResponseEvent filterQuestionByID( String id, Collection questions )
	{
		RiskQuestionResponseEvent riskQuestionResponseEvent = null;
		List <RiskQuestionResponseEvent> rqreList = CollectionUtil.iteratorToList(questions.iterator());
		RiskQuestionResponseEvent tempRiskQuestionResponseEvent = null;
		String qId = null;

		for (int i = 0; i < rqreList.size(); i++) 
		{
			tempRiskQuestionResponseEvent = rqreList.get(i);
			qId = Integer.toString( tempRiskQuestionResponseEvent.getQuestionID() );
			if( qId.equals( id ) )
			{
				riskQuestionResponseEvent = tempRiskQuestionResponseEvent;
				break;
			}
		}
		
		return riskQuestionResponseEvent;
	}

/*	public static String getTestingPageTitle( List qans, int questionNum )
	{
		String pageTitle = "";
		try
		{
			RiskQuestionAnswerEvent qa = (RiskQuestionAnswerEvent)qans.get( questionNum +1 );
			if( qa != null )
			{
				pageTitle = qa.getQuestionText();
			}
		}
		catch( IndexOutOfBoundsException ioobe )
		{ // there's the possibility the index is wacked out,
			// so just leave the pageTitle as is.
		}
		
		return pageTitle;
	}
*/
	/**
	 * Takes in a List of RiskQuestionResponseEvents and converts them into a list
	 * of veiw only RiskQuestionAnswerEvent consolidated by Question number based
	 * on the question number
	 * 
	 * @param riskQuestionResponseEvents
	 * @return
	 */
	public static List viewOnlyMultiQuestionAnswers( List riskQuestionResponseEvents )
	{
		List riskQuestionAnswerEvents = new ArrayList();

		// Create temporary hashmap to hold Question/Answers until they are processed
		HashMap map = new HashMap();

		// Iterate through RiskQuestionResponseEvents 
		// (User responses to Questions), one by one.
		//Iterator<RiskQuestionAnswerEvent> rqaIter = riskQuestionResponseEvents.iterator();
		List <RiskQuestionAnswerEvent> rqaList = CollectionUtil.iteratorToList(riskQuestionResponseEvents.iterator());
		RiskQuestionAnswerEvent singleProcessedRiskQuestionAnswerEvent = null;
		RiskQuestionAnswerEvent newRiskQuestionAnswerEvent = null;
		//while(rqaIter.hasNext())
		
		for (int i = 0; i < rqaList.size(); i++)
		{
			//RiskQuestionAnswerEvent singleProcessedRiskQuestionAnswerEvent = rqaIter.next();
			singleProcessedRiskQuestionAnswerEvent = rqaList.get(i);
			// Create new Question/Answer
			newRiskQuestionAnswerEvent = new RiskQuestionAnswerEvent();

			// Find out if answer is chronic
			boolean isChronic = (singleProcessedRiskQuestionAnswerEvent.getText() != null && 
					singleProcessedRiskQuestionAnswerEvent.getText().equals( UIConstants.ISCHRONIC ) 
					&& singleProcessedRiskQuestionAnswerEvent.getUiControlType().equalsIgnoreCase(UIConstants.CHECKBOXWITHCHRONIC) ) ;
			
			String strAnwserText;
			if (singleProcessedRiskQuestionAnswerEvent.getAnswerText() == null) {
				strAnwserText = "";
			} else {
				strAnwserText = singleProcessedRiskQuestionAnswerEvent.getAnswerText();
			}

			{ StringBuffer answerText = new StringBuffer( strAnwserText );
				//Checks to see if the controlCode is empty, if not this means it's not a true chronic answer
				//rather it's other free text
				if( isChronic && (singleProcessedRiskQuestionAnswerEvent.getControlCode() == null 
						|| (singleProcessedRiskQuestionAnswerEvent.getControlCode() != null && singleProcessedRiskQuestionAnswerEvent.getControlCode().length() < 1) ) )
				{
					answerText.append( " [Chronic] " );
				}
				newRiskQuestionAnswerEvent.setFilteredAnswerText( answerText.toString().replaceAll("\\s*\\[.*?\\]\\s*", " ") );
				newRiskQuestionAnswerEvent.setAnswerText( answerText.toString()  );
			}
			
			newRiskQuestionAnswerEvent.setQuestionText( singleProcessedRiskQuestionAnswerEvent.getQuestionText() );
			newRiskQuestionAnswerEvent.setQuestionName( singleProcessedRiskQuestionAnswerEvent.getQuestionName());
			newRiskQuestionAnswerEvent.setQuestionNumber( singleProcessedRiskQuestionAnswerEvent.getQuestionNumber() );
			newRiskQuestionAnswerEvent.setUiControlType( singleProcessedRiskQuestionAnswerEvent.getUiControlType() );
			newRiskQuestionAnswerEvent.setQuestionId( singleProcessedRiskQuestionAnswerEvent.getQuestionId());
			newRiskQuestionAnswerEvent.setIsAllowPrint(singleProcessedRiskQuestionAnswerEvent.getIsAllowPrint());
			
			// Checks to see if Question/Answer already exist in hashmap
			RiskQuestionAnswerEvent priorRiskQuestionAnswerEvent = (RiskQuestionAnswerEvent)
					map.get( singleProcessedRiskQuestionAnswerEvent.getQuestionNumber() );

			// Consolidates Question/Answer if it existed in hashmap
			if( priorRiskQuestionAnswerEvent != null )
			{
				// Add New Answer Text To Answer Text
				StringBuffer answerTextsForQuestionNumber = new StringBuffer();
				answerTextsForQuestionNumber.append( priorRiskQuestionAnswerEvent.getAnswerText() ).append( ", " ).append( singleProcessedRiskQuestionAnswerEvent.getAnswerText() );

				// Add isChronic to answer when needed
				if( isChronic )
				{
					answerTextsForQuestionNumber.append( " [Chronic] " );
				}

				// Set consolidated answer text for viewing purposes
				priorRiskQuestionAnswerEvent.setAnswerText( answerTextsForQuestionNumber.toString() );

				// Place new consolidated Question/Answer in hashmap
				map.put( singleProcessedRiskQuestionAnswerEvent.getQuestionNumber(), priorRiskQuestionAnswerEvent );
			}
			else
			{
				// Place new Question/Answer in hashmap
				map.put( singleProcessedRiskQuestionAnswerEvent.getQuestionNumber(), newRiskQuestionAnswerEvent );
				System.out.println("Question: " + newRiskQuestionAnswerEvent.getQuestionName() + " Is allow print " + newRiskQuestionAnswerEvent.getIsAllowPrint()  );
			}
		} // while

		// Move Question/Answers to list
		Collection c = map.values();
		//Iterator<RiskQuestionAnswerEvent> itr = c.iterator();
		List itrList = CollectionUtil.iteratorToList(c.iterator());
		//while(itr.hasNext())
		for (int i = 0; i < itrList.size(); i++)
		{
			riskQuestionAnswerEvents.add( itrList.get(i) );
		}

		return riskQuestionAnswerEvents;
	}

	/**
	 * Takes in a List of RiskQuestionResponseEvents and converts them into a list
	 * of veiw only RiskQuestionAnswerEvent consolidated by Question number based
	 * on the question number
	 * 
	 * @param riskQuestionResponseEvents
	 * @return
	 */
	public static List viewModifiedMultiQuestionAnswers( List riskQuestionResponseEvents )
	{
		List riskQuestionAnswerEvents = new ArrayList();
		System.out.println("viewModifiedMultiQuestionAnswers");
		// Create temporary hashmap to hold Question/Answers until they are processed
		HashMap map = new HashMap();

		// Iterate through RiskQuestionResponseEvents 
		// (User responses to Questions), one by one.
		//Iterator<RiskQuestionAnswerEvent> rqaIter = riskQuestionResponseEvents.iterator();
		List <RiskQuestionAnswerEvent> rqaList = CollectionUtil.iteratorToList(riskQuestionResponseEvents.iterator());
		RiskQuestionAnswerEvent singleProcessedRiskQuestionAnswerEvent = null;
		RiskQuestionAnswerEvent newRiskQuestionAnswerEvent = null;
		//while(rqaIter.hasNext())
		
		for (int i = 0; i < rqaList.size(); i++)
		{
			//RiskQuestionAnswerEvent singleProcessedRiskQuestionAnswerEvent = rqaIter.next();
			singleProcessedRiskQuestionAnswerEvent = rqaList.get(i);
			// Create new Question/Answer
			newRiskQuestionAnswerEvent = new RiskQuestionAnswerEvent();

			// Find out if answer is chronic
			boolean isChronic = (singleProcessedRiskQuestionAnswerEvent.getText() != null && 
					singleProcessedRiskQuestionAnswerEvent.getText().equals( UIConstants.ISCHRONIC ) 
					&& singleProcessedRiskQuestionAnswerEvent.getUiControlType().equalsIgnoreCase(UIConstants.CHECKBOXWITHCHRONIC) ) ;
			
			String strAnwserText;
			if (singleProcessedRiskQuestionAnswerEvent.getAnswerText() == null) {
				strAnwserText = "";
			} else {
				strAnwserText = singleProcessedRiskQuestionAnswerEvent.getAnswerText();
			}

			{ StringBuffer answerText = new StringBuffer( strAnwserText );
				//Checks to see if the controlCode is empty, if not this means it's not a true chronic answer
				//rather it's other free text
				if( isChronic && (singleProcessedRiskQuestionAnswerEvent.getControlCode() == null 
						|| (singleProcessedRiskQuestionAnswerEvent.getControlCode() != null && singleProcessedRiskQuestionAnswerEvent.getControlCode().length() < 1) ) )
				{
					answerText.append( " [Chronic] " );
				}
				if(singleProcessedRiskQuestionAnswerEvent.getUiControlType().equalsIgnoreCase( UIConstants.TEXTAREA ))
				{
				    String result = answerText.toString();
				    if(result!=null && result.equals(" "))
					result="NONE IDENTIFIED";
				    newRiskQuestionAnswerEvent.setFilteredAnswerText(result.replaceAll("\\s*\\[.*?\\]\\s*", " "));
				    newRiskQuestionAnswerEvent.setAnswerText(result);
				}
				else
				    newRiskQuestionAnswerEvent.setAnswerText( answerText.toString() );
				    newRiskQuestionAnswerEvent.setFilteredAnswerText(answerText.toString().replaceAll("\\s*\\[.*?\\]\\s*", " "));
			}
			
			newRiskQuestionAnswerEvent.setQuestionText( singleProcessedRiskQuestionAnswerEvent.getQuestionText() );
			newRiskQuestionAnswerEvent.setQuestionName( singleProcessedRiskQuestionAnswerEvent.getQuestionName() );
			newRiskQuestionAnswerEvent.setQuestionNumber( singleProcessedRiskQuestionAnswerEvent.getQuestionNumber() );
			newRiskQuestionAnswerEvent.setUiControlType( singleProcessedRiskQuestionAnswerEvent.getUiControlType() );
			newRiskQuestionAnswerEvent.setQuestionId( singleProcessedRiskQuestionAnswerEvent.getQuestionId());
			newRiskQuestionAnswerEvent.setIsAllowPrint(singleProcessedRiskQuestionAnswerEvent.getIsAllowPrint());
			// Checks to see if Question/Answer already exist in hashmap
			RiskQuestionAnswerEvent priorRiskQuestionAnswerEvent = (RiskQuestionAnswerEvent)
					map.get( singleProcessedRiskQuestionAnswerEvent.getQuestionNumber() );

			// Consolidates Question/Answer if it existed in hashmap
			if( priorRiskQuestionAnswerEvent != null )
			{
				// Add New Answer Text To Answer Text
				StringBuffer answerTextsForQuestionNumber = new StringBuffer();
				answerTextsForQuestionNumber.append( priorRiskQuestionAnswerEvent.getAnswerText() ).append( ", " ).append( singleProcessedRiskQuestionAnswerEvent.getAnswerText() );

				// Add isChronic to answer when needed
				if( isChronic )
				{
					answerTextsForQuestionNumber.append( " [Chronic] " );
				}

				// Set consolidated answer text for viewing purposes
				priorRiskQuestionAnswerEvent.setAnswerText( answerTextsForQuestionNumber.toString() );

				// Place new consolidated Question/Answer in hashmap
				map.put( singleProcessedRiskQuestionAnswerEvent.getQuestionNumber(), priorRiskQuestionAnswerEvent );
			}
			else
			{
				// Place new Question/Answer in hashmap
				map.put( singleProcessedRiskQuestionAnswerEvent.getQuestionNumber(), newRiskQuestionAnswerEvent );
			}
		} // while

		// Move Question/Answers to list
		Collection c = map.values();
		//Iterator<RiskQuestionAnswerEvent> itr = c.iterator();
		List itrList = CollectionUtil.iteratorToList(c.iterator());
		//while(itr.hasNext())
		for (int i = 0; i < itrList.size(); i++)
		{
			riskQuestionAnswerEvents.add( itrList.get(i) );
		}

		return riskQuestionAnswerEvents;
	}

	/**
	 * Takes in a List of RiskQuestionResponseEvents and converts them into a list
	 * of individual RiskQuestionAnswerEvent ready to send to back-end for further
	 * PD processing
	 * 
	 * @param riskQuestionResponseEvents
	 * @return
	 */
	public static List processIndividualQuestionAnswers( List riskQuestionResponseEvents )
	{
		List weightResponses = new ArrayList();
		// Iterate through RiskQuestionResponseEvents (User responses to Questions), one by one.
		Iterator<RiskQuestionResponseEvent> rqreIter = riskQuestionResponseEvents.iterator();
		List <RiskQuestionResponseEvent> rqreList = CollectionUtil.iteratorToList(riskQuestionResponseEvents.iterator());
		RiskQuestionResponseEvent riskQuestionResponseEvent = null;
		String controlType = null;
		ArrayList<String> questionToBeRemoved= new ArrayList<String>();
		String controlCode = null;
//		while(rqreIter.hasNext())
		for (int i = 0; i < rqreList.size(); i++) 
		{
			/* RiskQuestionResponseEvent: The Question, Possible Answer(s),
			 * Selected Answers(s), and Possible Selected Chronic Answer(s)
			 * Single Response Event
			 */
			//RiskQuestionResponseEvent riskQuestionResponseEvent = rqreIter.next();
			riskQuestionResponseEvent = rqreList.get(i);

			// All Questions have a UIControlType
			controlType = riskQuestionResponseEvent.getUiControlType();
			controlCode = riskQuestionResponseEvent.getControlCode();

			/* A UiControlType of NOANSNEEDED, means the RiskQuestionResponseEvent
			 * is not a question from the database and carries no valid weight id
			 * In other words, it's a static question created in the application.
			 */
			if( !controlType.equalsIgnoreCase( UIConstants.NOANSNEEDED ) )
			{
				// Possible Answers to the Question
				RiskWeightedResponseEvent[] riskWeightedResponseEvents = (RiskWeightedResponseEvent[])
						riskQuestionResponseEvent.getAnswers().toArray( new RiskWeightedResponseEvent[ riskQuestionResponseEvent.getAnswers().size() ] );

				if( controlType.equalsIgnoreCase( UIConstants.DATE ) || 
						controlType.equalsIgnoreCase( UIConstants.TEXTBOX ) || 
						controlType.equalsIgnoreCase( UIConstants.TEXTAREA ) )
				{
					// Create a single RiskQuestionAnswerEvent
					// Single Answer Event
					RiskQuestionAnswerEvent riskQuestionAnswerEvent = new RiskQuestionAnswerEvent();

					riskQuestionAnswerEvent.setUiControlType( riskQuestionResponseEvent.getUiControlType() );
					riskQuestionAnswerEvent.setControlCode( riskQuestionResponseEvent.getControlCode() );
					riskQuestionAnswerEvent.setQuestionNumber( riskQuestionResponseEvent.getQuestionNbr() );
					riskQuestionAnswerEvent.setQuestionText( riskQuestionResponseEvent.getQuestionText() );
					riskQuestionAnswerEvent.setQuestionName( riskQuestionResponseEvent.getQuestionName());					
					riskQuestionAnswerEvent.setQuestionId(new Integer(riskQuestionResponseEvent.getQuestionID()).toString());
					riskQuestionAnswerEvent.setNumeric(riskQuestionResponseEvent.isNumeric());
					riskQuestionAnswerEvent.setIsAllowPrint(riskQuestionResponseEvent.getIsAllowPrint());
					
					/* A UiControlType of DATE, TEXTAREA, TEXTBOX will have a single
					 * riskWeightedResponseEvent in answers collection of
					 * riskQuestionResponseEvent. Use it to get weight of response.
					 */
					int weightedResponseID = riskWeightedResponseEvents[0].getWeightedResponseID();
					riskQuestionAnswerEvent.setWeightedResponseID( weightedResponseID );

					/* A UiControlType of DATE, TEXTAREA, TEXTBOX will store
					 * the date or text in the selectedAnswerID of
					 * the riskQuestionAnswerEvent
					 */
					String textOrDate = riskQuestionResponseEvent.getSelectedAnswerID();

					// A UiControlType needs to have the date attached
					if( controlType.equalsIgnoreCase( UIConstants.TEXTAREA ) )
					{
						// Tags Date to the end of TEXTAREA content
						IUserInfo user = SecurityUIHelper.getUser();
						Name userName = new Name( user.getFirstName(), UIConstants.EMPTY_STRING, user.getLastName() );
						// textOrDate = textOrDate + " [" +
						// DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) +
						// " - " + userName.getFormattedName() + "]";
						textOrDate = new StringBuilder( textOrDate ).append( " [" ).append( DateUtil.getCurrentDateString( UIConstants.DATETIME24_FMT_1 ) ).append( " - " ).append( userName.getFormattedName() ).append( "]" ).toString();
					}
					
					if( controlType.equalsIgnoreCase( UIConstants.TEXTBOX ) &&  (controlCode==null || StringUtil.isEmpty(controlCode)) )
					{
						// Tags Date to the end of TEXTAREA content
						IUserInfo user = SecurityUIHelper.getUser();
						Name userName = new Name( user.getFirstName(), UIConstants.EMPTY_STRING, user.getLastName() );
						// textOrDate = textOrDate + " [" +
						// DateUtil.getCurrentDateString(UIConstants.DATETIME24_FMT_1) +
						// " - " + userName.getFormattedName() + "]";
						textOrDate = new StringBuilder( textOrDate ).append( " [" ).append( DateUtil.getCurrentDateString( UIConstants.DATETIME24_FMT_1 ) ).append( " - " ).append( userName.getFormattedName() ).append( "]" ).toString();
					}

					riskQuestionAnswerEvent.setText( textOrDate );
					riskQuestionAnswerEvent.setAnswerText( textOrDate );

					// Add to List
					if (textOrDate != null && textOrDate.length() > 0) {
						weightResponses.add( riskQuestionAnswerEvent );
					}
				}
				else if( controlType.equals( UIConstants.LISTBOX ) || 
						controlType.equals( UIConstants.CHECKBOX ) || 
						controlType.equals( UIConstants.CHECKBOXWITHCHRONIC ))
				{
					
					//Run for single checkbox question
					/*
					if ( (riskQuestionResponseEvent.getSelectedAnswerID() != null && riskQuestionResponseEvent.getSelectedAnswerID().length() > 0 ) &&
							(riskQuestionResponseEvent.getSelectedAnswerIDs() == null || (riskQuestionResponseEvent.getSelectedAnswerIDs() != null && riskQuestionResponseEvent.getSelectedAnswerIDs().length == 0) )) {
						
						String[] selectedAnswerIDs = new String[1];
						selectedAnswerIDs[0] = riskQuestionResponseEvent.getSelectedAnswerID();
						riskQuestionResponseEvent.setSelectedAnswerIDs(selectedAnswerIDs);
						
					}
					*/
					
					// If it is a multi select box then need to get all the selected answer id's
					String[] selectedAnswerIDs = riskQuestionResponseEvent.getSelectedAnswerIDs();
					String[] selectedChronicIDs = riskQuestionResponseEvent.getSelectedChronicIDs();

					if( selectedAnswerIDs != null )
					{
						// Outer Loop Through AnswerIds
						for(int j = 0; j < selectedAnswerIDs.length; j++)
						{
							// Create a single RiskQuestionAnswerEvent
							// Single Answer Event
							RiskQuestionAnswerEvent riskQuestionAnswerEvent = new RiskQuestionAnswerEvent();

							int weightedResponseID = 0 ;
							try
							{
								weightedResponseID = Integer.parseInt( selectedAnswerIDs[j] );
							}
							catch( NumberFormatException nfe )
							{
								weightedResponseID = 0;
							}
							
							// Set responseWeightId on riskQuestionAnswerEvent
							riskQuestionAnswerEvent.setWeightedResponseID( weightedResponseID );

							riskQuestionAnswerEvent.setUiControlType( riskQuestionResponseEvent.getUiControlType() );

							riskQuestionAnswerEvent.setQuestionNumber( riskQuestionResponseEvent.getQuestionNbr() );
							riskQuestionAnswerEvent.setQuestionText( riskQuestionResponseEvent.getQuestionText() );
							riskQuestionAnswerEvent.setQuestionName( riskQuestionResponseEvent.getQuestionName());	
							riskQuestionAnswerEvent.setQuestionId(new Integer(riskQuestionResponseEvent.getQuestionID()).toString());
							riskQuestionAnswerEvent.setIsAllowPrint(riskQuestionResponseEvent.getIsAllowPrint());
							
							// Set AnswerText on riskQuestionAnswerEvent
							for(int k = 0; k < riskWeightedResponseEvents.length; k++)
							{
								int possibleSelectedWeightResponseid = riskWeightedResponseEvents[k].getWeightedResponseID();
								int possibleSelectedWeightResponse = riskWeightedResponseEvents[k].getWeight();
								String possibleSelectedWeightResponseText = riskWeightedResponseEvents[k].getAnswerText();

								if( weightedResponseID == possibleSelectedWeightResponseid )
								{
									riskQuestionAnswerEvent.setWeight( possibleSelectedWeightResponse );
									riskQuestionAnswerEvent.setAnswerText( possibleSelectedWeightResponseText );
									break;
								}
							}
							riskQuestionAnswerEvent.setText(UIConstants.EMPTY_STRING);
							if( selectedChronicIDs != null )
							{
								for(int k = 0; k < selectedChronicIDs.length; k++)
								{
									int chronicWeightedResponseID = 0;
									
									try
									{
										chronicWeightedResponseID = Integer.parseInt( selectedChronicIDs[k] );
									}
									catch( NumberFormatException nfe )
									{
										chronicWeightedResponseID = 0;
									}
									
									if( weightedResponseID == chronicWeightedResponseID )
									{
										// Chronic Adds +1 to the weight which is needed
										// for some risk computations
										riskQuestionAnswerEvent.setText( UIConstants.ISCHRONIC );
										riskQuestionAnswerEvent.setWeight( riskQuestionAnswerEvent.getWeight() + 1 );
										break;
									}
								} // End Inner Loop
							}

							weightResponses.add( riskQuestionAnswerEvent );
						} // End Outer Loop
					} else {
						RiskQuestionAnswerEvent riskQuestionAnswerEvent = new RiskQuestionAnswerEvent();

						// Set responseWeightId on riskQuestionAnswerEvent
						riskQuestionAnswerEvent.setWeightedResponseID( 0 );

						riskQuestionAnswerEvent.setUiControlType( riskQuestionResponseEvent.getUiControlType() );

						riskQuestionAnswerEvent.setQuestionNumber( riskQuestionResponseEvent.getQuestionNbr() );
						riskQuestionAnswerEvent.setQuestionText( riskQuestionResponseEvent.getQuestionText() );
						riskQuestionAnswerEvent.setQuestionName( riskQuestionResponseEvent.getQuestionName());	
						riskQuestionAnswerEvent.setQuestionId(new Integer(riskQuestionResponseEvent.getQuestionID()).toString());
						riskQuestionAnswerEvent.setWeight(0);
						riskQuestionAnswerEvent.setIsAllowPrint(riskQuestionResponseEvent.getIsAllowPrint());
					}
				}
				else if( controlType.equals( UIConstants.RADIO ) || 
						controlType.equals( UIConstants.DROPDOWN ) ||
						controlType.equals( UIConstants.QUESTIONHEADER ))
				{
					int weightedResponseID = 0;
					if( riskQuestionResponseEvent.getSelectedAnswerID() != null )
					{
						try
						{
							weightedResponseID = Integer.parseInt( riskQuestionResponseEvent.getSelectedAnswerID() );
						}
						catch( NumberFormatException nfe )
						{
							weightedResponseID = 0;
						}
					}

					/* Some questions are not answered and have a 0 set
					 * as the weight ID. This means the question was not
					 * answered so no response will be stored
					 */
					if( weightedResponseID > 0 )
					{
						// Create a single RiskQuestionAnswerEvent
						// Single Answer Event
						RiskQuestionAnswerEvent riskQuestionAnswerEvent = new RiskQuestionAnswerEvent(); 
						riskQuestionAnswerEvent.setWeightedResponseID( weightedResponseID );

						riskQuestionAnswerEvent.setUiControlType( riskQuestionResponseEvent.getUiControlType() );

						riskQuestionAnswerEvent.setQuestionNumber( riskQuestionResponseEvent.getQuestionNbr() );
						riskQuestionAnswerEvent.setQuestionText( riskQuestionResponseEvent.getQuestionText() );
						riskQuestionAnswerEvent.setQuestionName( riskQuestionResponseEvent.getQuestionName());	
						riskQuestionAnswerEvent.setQuestionId(new Integer(riskQuestionResponseEvent.getQuestionID()).toString());
						riskQuestionAnswerEvent.setIsAllowPrint(riskQuestionResponseEvent.getIsAllowPrint());
						
						// Set AnswerText on riskQuestionAnswerEvent
						for(int k = 0; k < riskWeightedResponseEvents.length; k++)
						{
//							if (riskQuestionResponseEvent.getControlCode() != null 
//									&& riskQuestionResponseEvent.getControlCode().equals(UIConstants.EMPTY_STRING)){
//								int possibleSelectedWeightResponseid = riskWeightedResponseEvents[k].getWeightedResponseID();
//								int possibleSelectedWeightResponse = riskWeightedResponseEvents[k].getWeight();
//								String possibleSelectedWeightResponseText = riskWeightedResponseEvents[k].getAnswerText();
//
//								if( weightedResponseID == possibleSelectedWeightResponseid )
//								{
//									riskQuestionAnswerEvent.setWeight( possibleSelectedWeightResponse );
//									riskQuestionAnswerEvent.setAnswerText( possibleSelectedWeightResponseText );
//									break;
//								}
//							} else {
							//{
								int possibleSelectedWeightResponseid = riskWeightedResponseEvents[k].getWeightedResponseID();
								int possibleSelectedWeightResponse = riskWeightedResponseEvents[k].getWeight();
								String possibleSelectedWeightResponseText = riskWeightedResponseEvents[k].getAnswerText();

								if( weightedResponseID == possibleSelectedWeightResponseid )
								{
									riskQuestionAnswerEvent.setWeight( possibleSelectedWeightResponse );
									riskQuestionAnswerEvent.setAnswerText( possibleSelectedWeightResponseText );
									if(riskWeightedResponseEvents[k].getAction()!=null 
									&& riskWeightedResponseEvents[k].getAction().equalsIgnoreCase("hide"))
									{
										String subRespId = riskWeightedResponseEvents[k].getSubordinateQuestionId();
										questionToBeRemoved.add(subRespId);	
									}
									break;
								}
								else
								{
									if(controlType.equals( UIConstants.RADIO ) && riskWeightedResponseEvents[k].getAction()!=null 
											&& riskWeightedResponseEvents[k].getAction().equalsIgnoreCase("show"))
									{
										
										String subRespId = riskWeightedResponseEvents[k].getSubordinateQuestionId();
										questionToBeRemoved.add(subRespId);								
										
									}
										
								}
							//}
						}

						weightResponses.add( riskQuestionAnswerEvent );
					}
				} // if control type is radio or drop down
			}
		} // while iterator
		
		//go through weightResponses and remove the response if previously selected but user changed mind
		
	
		for(int i=0; i< weightResponses.size(); i++)
		{
			for(String s : questionToBeRemoved)
			{
				RiskQuestionAnswerEvent riskQuest= (RiskQuestionAnswerEvent) weightResponses.get(i);
				if(riskQuest.getQuestionId().equals(s))
				{
					weightResponses.remove(i);
					
				}
			}
		}
		return weightResponses;
	}
	public static String ninetyDayCheck(Date enteredDate){
		//Check to see if entered date is passed 90 days, if it is, an update will not be allowed
		Date now = new Date();
		
		Calendar c1 = Calendar.getInstance(); 
		c1.setTime(enteredDate);
		
		Calendar c2 = Calendar.getInstance(); 
		c2.setTime(now);
		c2.add(Calendar.DATE, -90);
		Date threeMonthsAgo = c2.getTime();
	    c2.setTime(threeMonthsAgo);
	    
	    String result = "";
	    if(c1.after(c2)) {
			result = UIConstants.YES_FULL_TEXT; 
		} else {
			result = UIConstants.NO_FULL_TEXT;
		}
		//End date over 90 days check
	    return result;
	}
	
    public static List sortQuestions(List <GetQuestionResponseEvent> catQuestions) {
		List sortedList = new ArrayList(catQuestions);
		ArrayList sortFields = new ArrayList();
		sortFields.add(new ComparatorChain(new BeanComparator("uiDisplayOrderAsInt")));
		ComparatorChain cc = new ComparatorChain(sortFields);
		Collections.sort(sortedList, cc);
		return sortedList;
	}
}