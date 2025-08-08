/*
 * Created on December 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.casefile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import messaging.casefile.reply.PossibleResponseEvent;
import messaging.casefile.reply.QuestionAnswerResponseEvent;
import messaging.casefile.reply.QuestionGroupResponseEvent;
import messaging.casefile.reply.QuestionResponseEvent;
import mojo.km.config.EmptyMappingPropertyException;
import mojo.km.config.LoadMappingPropertyException;
import mojo.km.utilities.XMLManager;
import naming.PDJuvenileCaseConstants;

import org.jdom.Document;
import org.jdom.Element;

/**
 * @author mchowdhury
 *
 * Class responsible for reading question-answer xmlfile
 */
 public class XMLQuestionAnswerPropertyAdapter {

 	
 	public static final String CODE_TABLE_NAME_ATTR = "codeTableName";
     /** <p>name: XMLQuestionAnswerPropertyAdapter description: constructor */
    public XMLQuestionAnswerPropertyAdapter(){
 	}

	/** 
	 * @param fileName
	 * @description: load the xml file
	 */
	public QuestionAnswerResponseEvent loadXML(String fileName) throws LoadMappingPropertyException, EmptyMappingPropertyException{
	   Document docs;
	   try{
		  docs = XMLManager.readXMLResource(fileName);
	   }
	   catch (Exception e){
		  e.printStackTrace();
		  throw new LoadMappingPropertyException("Exception occurred while loading the file: " + fileName);
	   }
	   QuestionAnswerResponseEvent qAresponseEvent = new QuestionAnswerResponseEvent();
       Element element = docs.getRootElement();
       qAresponseEvent.setName(element.getAttributeValue(PDJuvenileCaseConstants.NAME));
	   qAresponseEvent.setType(element.getAttributeValue(PDJuvenileCaseConstants.TYPE));
	   if (element != null){
		  Iterator iter = element.getChildren().iterator();
		  
		Collection qGroupResponseEvents = new ArrayList();
		QuestionGroupResponseEvent qGroup = null;
		while (iter.hasNext()){
			 Element elem = (Element) iter.next();
			 // load Question response event
			 qGroup = this.loadQuestionGroup(elem);

			 Iterator questionIter = elem.getChildren().iterator();
			 Collection questions = new ArrayList();
 			 while(questionIter.hasNext()){
				Element questionElem = (Element) questionIter.next();
				// load question response event
				QuestionResponseEvent qREvent = this.loadQuestion(questionElem);
			 
			    Iterator iterator = questionElem.getChildren().iterator();
			    Collection responses = new ArrayList();
			    PossibleResponseEvent possibleResponseEvent = null;
			    while(iterator.hasNext()){
				    Element elemResp = (Element) iterator.next();
				    if(elemResp != null && elemResp.getName().equals(PDJuvenileCaseConstants.RESPONSE_DEPENDENCY)){
						// set added parameters in questionResponseEvent
						qREvent = this.loadQuestionDependencyParameters(elemResp,qREvent);
				    }else if(elemResp != null){
					    //set possible response event
						possibleResponseEvent = this.loadPossibleResponse(elemResp);
					    responses.add(possibleResponseEvent);
				    }
			    }
				qREvent.setPossibleResponseEvents(responses);
				questions.add(qREvent);
			 }
			 qGroup.setQuestionResponseEvents(questions);
			 qGroupResponseEvents.add(qGroup);
           }
		   qAresponseEvent.setQuestionGroupResponseEvents(qGroupResponseEvents);
       }
	   return qAresponseEvent;
	}

	/**
	 * @param elemResp
	 * @param qREvent
	 * @return
	 */
	private QuestionResponseEvent loadQuestionDependencyParameters(Element elemResp, QuestionResponseEvent qREvent)
	{
		qREvent.setSelectedResponseId(elemResp.getAttributeValue(PDJuvenileCaseConstants.SELECTED_RESPONSE_ID));
        qREvent.setDependentQuestionId(elemResp.getAttributeValue(PDJuvenileCaseConstants.QUESTION_ID));
        return qREvent;
	}

	/**
	 * @param elemResp
	 * @return
	 */
	private PossibleResponseEvent loadPossibleResponse(Element elemResp)
	{
		PossibleResponseEvent possibleResponseEvent = new PossibleResponseEvent();
		possibleResponseEvent.setDefault(Boolean.valueOf(elemResp.getAttributeValue(PDJuvenileCaseConstants.IS_DEFAULT)).booleanValue());
		possibleResponseEvent.setDisplayText(elemResp.getAttributeValue(PDJuvenileCaseConstants.DISPLAY_TEXT));
		possibleResponseEvent.setId(elemResp.getAttributeValue(PDJuvenileCaseConstants.ID));
		possibleResponseEvent.setResponseValue(elemResp.getAttributeValue(PDJuvenileCaseConstants.RESPONSE_VALUE));
		possibleResponseEvent.setCodeTableName(elemResp.getAttributeValue(XMLQuestionAnswerPropertyAdapter.CODE_TABLE_NAME_ATTR));
		return possibleResponseEvent;
	}

	/**
	 * @param questionElem
	 * @return
	 */
	private QuestionResponseEvent loadQuestion(Element questionElem)
	{
		QuestionResponseEvent qREvent = new QuestionResponseEvent();
		String questionId = questionElem.getAttributeValue(PDJuvenileCaseConstants.ID);
		qREvent.setId(questionId);
		qREvent.setText(questionElem.getAttributeValue(PDJuvenileCaseConstants.TEXT));
		qREvent.setResponseUIControlType(questionElem.getAttributeValue(PDJuvenileCaseConstants.RESPONSE_UICONTROL_TYPE));
		qREvent.setRequired(Boolean.valueOf(questionElem.getAttributeValue(PDJuvenileCaseConstants.IS_REQUIRED)).booleanValue());
		qREvent.setRenderQuesNum(Boolean.valueOf(questionElem.getAttributeValue(PDJuvenileCaseConstants.IS_RENDER_QUES_NUM)).booleanValue());
		qREvent.setResponseNewLine(Boolean.valueOf(questionElem.getAttributeValue(PDJuvenileCaseConstants.IS_RESPONSE_NEW_LINE)).booleanValue());
		qREvent.setRowSequence(questionElem.getAttributeValue(PDJuvenileCaseConstants.ROW_SEQUENCE));
		qREvent.setUiControlSize(questionElem.getAttributeValue(PDJuvenileCaseConstants.UICONTROL_SIZE));
		qREvent.setTextLength(questionElem.getAttributeValue(PDJuvenileCaseConstants.TEXT_LENGTH));
		if(questionElem.getAttributeValue(PDJuvenileCaseConstants.COLUMN_SEQUENCE) != null && !questionElem.getAttributeValue(PDJuvenileCaseConstants.COLUMN_SEQUENCE).equals("")){
			qREvent.setColumnSequence(Integer.parseInt(questionElem.getAttributeValue(PDJuvenileCaseConstants.COLUMN_SEQUENCE)));
		}
		qREvent.setResponseDataType(questionElem.getAttributeValue(PDJuvenileCaseConstants.RESPONSE_DATA_TYPE));
		qREvent.setValidationDesc(questionElem.getAttributeValue(PDJuvenileCaseConstants.VALIDATION_DESCRIPTION));
		qREvent.setFormatKey(questionElem.getAttributeValue(PDJuvenileCaseConstants.FORMAT_KEY));
        return qREvent;
	}

	/**
	 * @param elem
	 * @return
	 */
	private QuestionGroupResponseEvent loadQuestionGroup(Element elem)
	{
		QuestionGroupResponseEvent qGroup = new QuestionGroupResponseEvent();
		qGroup.setId(elem.getAttributeValue(PDJuvenileCaseConstants.ID));
		qGroup.setSelectedResponseId(elem.getAttributeValue(PDJuvenileCaseConstants.SELECTED_RESPONSE_ID));
		qGroup.setDependentQuestionId(elem.getAttributeValue(PDJuvenileCaseConstants.QUESTION_ID));
		if(elem.getAttributeValue(PDJuvenileCaseConstants.ID) != null && !elem.getAttributeValue(PDJuvenileCaseConstants.ID).equals("")){
			qGroup.setSequence(Integer.parseInt(elem.getAttributeValue(PDJuvenileCaseConstants.ID)));
		}
		if(elem.getAttributeValue(PDJuvenileCaseConstants.SEQUENCE) != null && !elem.getAttributeValue(PDJuvenileCaseConstants.SEQUENCE).equals("")){
			qGroup.setSequence(Integer.parseInt(elem.getAttributeValue(PDJuvenileCaseConstants.SEQUENCE)));
		}
        return qGroup;
	}
}
