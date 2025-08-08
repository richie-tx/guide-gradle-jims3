/*
 * Created on Feb 13, 2008
 *
 */
package pd.supervision.administerassessments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import messaging.administerassessments.reply.CSCPossibleResponseEvent;
import messaging.administerassessments.reply.CSCQuestionAnswerResponseEvent;
import messaging.administerassessments.reply.CSCQuestionGroupResponseEvent;
import messaging.administerassessments.reply.CSCQuestionResponseEvent;
import mojo.km.config.EmptyMappingPropertyException;
import mojo.km.config.LoadMappingPropertyException;
import mojo.km.utilities.XMLManager;
import naming.CSCAssessmentConstants;

import org.jdom.Document;
import org.jdom.Element;

/**
 * @author dgibler
 *
 */
public class CSXMLQuestionAnswerPropertyAdapter {

    /** 
	 * @param fileName
	 * @description: load the xml file
	 */
	public CSCQuestionAnswerResponseEvent loadXML(String fileName) throws LoadMappingPropertyException, EmptyMappingPropertyException{
	   Document docs;
	   try{
		  docs = XMLManager.readXMLResource(fileName);
	   }
	   catch (Exception e){
		  throw new LoadMappingPropertyException("Exception occurred while loading the file: " + fileName);
	   }
	   CSCQuestionAnswerResponseEvent qAresponseEvent = new CSCQuestionAnswerResponseEvent();
       Element element = docs.getRootElement();
       qAresponseEvent.setName(element.getAttributeValue(CSCAssessmentConstants.NAME));
	   qAresponseEvent.setType(element.getAttributeValue(CSCAssessmentConstants.TYPE));
	   if (element != null){
		  Iterator iter = element.getChildren().iterator();
		  
		Collection qGroupResponseEvents = new ArrayList();
		CSCQuestionGroupResponseEvent qGroup = null;
		while (iter.hasNext()){
			 Element elem = (Element) iter.next();
			 // load Question response event
			 qGroup = this.loadQuestionGroup(elem);

			 Iterator questionIter = elem.getChildren().iterator();
			 Collection questions = new ArrayList();
 			 while(questionIter.hasNext()){
				Element questionElem = (Element) questionIter.next();
				// load question response event
				CSCQuestionResponseEvent qREvent = this.loadQuestion(questionElem);
			 
			    Iterator iterator = questionElem.getChildren().iterator();
			    Collection responses = new ArrayList();
			    CSCPossibleResponseEvent possibleResponseEvent = null;
			    while(iterator.hasNext()){
				    Element elemResp = (Element) iterator.next();
				    if(elemResp != null){
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
	 * @return
	 */
	private CSCPossibleResponseEvent loadPossibleResponse(Element elemResp)
	{
		CSCPossibleResponseEvent possibleResponseEvent = new CSCPossibleResponseEvent();
		possibleResponseEvent.setDefault(Boolean.valueOf(elemResp.getAttributeValue(CSCAssessmentConstants.IS_DEFAULT)).booleanValue());
		possibleResponseEvent.setDisplayText(elemResp.getAttributeValue(CSCAssessmentConstants.DISPLAY_TEXT));
		possibleResponseEvent.setId(elemResp.getAttributeValue(CSCAssessmentConstants.ID));
		possibleResponseEvent.setResponseValue(elemResp.getAttributeValue(CSCAssessmentConstants.RESPONSE_VALUE));
		return possibleResponseEvent;
	}

	/**
	 * @param questionElem
	 * @return
	 */
	private CSCQuestionResponseEvent loadQuestion(Element questionElem)
	{
		CSCQuestionResponseEvent qREvent = new CSCQuestionResponseEvent();
		qREvent.setColumnWidth(questionElem.getAttributeValue(CSCAssessmentConstants.COLUMN_WIDTH));
		if(questionElem.getAttributeValue(CSCAssessmentConstants.COLUMN_SEQUENCE) != null && !questionElem.getAttributeValue(CSCAssessmentConstants.COLUMN_SEQUENCE).equals("")){
			qREvent.setColumnSequence(Integer.parseInt(questionElem.getAttributeValue(CSCAssessmentConstants.COLUMN_SEQUENCE)));
		} else {
			qREvent.setColumnSequence(1);
		}
		qREvent.setDisplayText(questionElem.getAttributeValue(CSCAssessmentConstants.DISPLAY_TEXT));
		qREvent.setDisplayTextAlign(questionElem.getAttributeValue(CSCAssessmentConstants.DISPLAY_TEXT_ALIGN));;
		qREvent.setDisplayTextDetailHeader(Boolean.valueOf(questionElem.getAttributeValue(CSCAssessmentConstants.IS_DISPLAY_TEXT_DETAIL_HEADER)).booleanValue());
		qREvent.setEachResponseNewLine(Boolean.valueOf(questionElem.getAttributeValue(CSCAssessmentConstants.IS_EACH_RESPONSE_NEW_LINE)).booleanValue());
		qREvent.setFormatKey(questionElem.getAttributeValue(CSCAssessmentConstants.FORMAT_KEY));
		String questionId = questionElem.getAttributeValue(CSCAssessmentConstants.ID);
		qREvent.setId(questionId);
		qREvent.setRequired(Boolean.valueOf(questionElem.getAttributeValue(CSCAssessmentConstants.IS_REQUIRED)).booleanValue());
		qREvent.setRequiredImageShown(Boolean.valueOf(questionElem.getAttributeValue(CSCAssessmentConstants.IS_REQUIRED_IMAGE_SHOWN)).booleanValue());
		qREvent.setResponseDataType(questionElem.getAttributeValue(CSCAssessmentConstants.RESPONSE_DATA_TYPE));
		qREvent.setResponseNewLine(Boolean.valueOf(questionElem.getAttributeValue(CSCAssessmentConstants.IS_RESPONSE_NEW_LINE)).booleanValue());
		qREvent.setResponseUIControlType(questionElem.getAttributeValue(CSCAssessmentConstants.RESPONSE_UICONTROL_TYPE));
		qREvent.setRowSequence(new Integer(questionElem.getAttributeValue(CSCAssessmentConstants.ROW_SEQUENCE)).intValue());
		if(questionElem.getAttributeValue(CSCAssessmentConstants.SUMMARY_COL_SEQUENCE) != null && !questionElem.getAttributeValue(CSCAssessmentConstants.SUMMARY_COL_SEQUENCE).equals("")){
		    qREvent.setSummaryColSeq(Integer.parseInt(questionElem.getAttributeValue(CSCAssessmentConstants.SUMMARY_COL_SEQUENCE)));
		}
        else {
            qREvent.setSummaryColSeq(0);
        }
		qREvent.setText(questionElem.getAttributeValue(CSCAssessmentConstants.TEXT));
		qREvent.setTextLength(questionElem.getAttributeValue(CSCAssessmentConstants.TEXT_LENGTH));
		qREvent.setUiControlSize(questionElem.getAttributeValue(CSCAssessmentConstants.UICONTROL_SIZE));
		qREvent.setMinValue(questionElem.getAttributeValue(CSCAssessmentConstants.MIN_VALUE));
		qREvent.setMaxValue(questionElem.getAttributeValue(CSCAssessmentConstants.MAX_VALUE));
		qREvent.setSummaryRespColumnWidth(questionElem.getAttributeValue(CSCAssessmentConstants.SUMMARY_RESP_COLUMN_WIDTH));
		if(questionElem.getAttributeValue(CSCAssessmentConstants.SUMMARY_ROW_SEQUENCE) != null && !questionElem.getAttributeValue(CSCAssessmentConstants.SUMMARY_ROW_SEQUENCE).equals("")){
		    qREvent.setSummaryRowSeq(Integer.parseInt(questionElem.getAttributeValue(CSCAssessmentConstants.SUMMARY_ROW_SEQUENCE)));
		} else {
		    qREvent.setSummaryRowSeq(0);
		}
		return qREvent;
	}

	/**
	 * @param elem
	 * @return
	 */
	private CSCQuestionGroupResponseEvent loadQuestionGroup(Element elem)
	{
		CSCQuestionGroupResponseEvent qGroup = new CSCQuestionGroupResponseEvent();
		qGroup.setId(elem.getAttributeValue(CSCAssessmentConstants.ID));
		qGroup.setDisplayText(elem.getAttributeValue(CSCAssessmentConstants.DISPLAY_TEXT));
		qGroup.setDisplayTextAlign(elem.getAttributeValue(CSCAssessmentConstants.DISPLAY_TEXT_ALIGN));
		qGroup.setDisplayTextDetailHeader(Boolean.valueOf(elem.getAttributeValue(CSCAssessmentConstants.IS_DISPLAY_TEXT_DETAIL_HEADER)).booleanValue());
		if(elem.getAttributeValue(CSCAssessmentConstants.ID) != null && !elem.getAttributeValue(CSCAssessmentConstants.ID).equals("")){
			qGroup.setId(elem.getAttributeValue(CSCAssessmentConstants.ID));
		}
		if(elem.getAttributeValue(CSCAssessmentConstants.SEQUENCE) != null && !elem.getAttributeValue(CSCAssessmentConstants.SEQUENCE).equals("")){
			qGroup.setSequence(Integer.parseInt(elem.getAttributeValue(CSCAssessmentConstants.SEQUENCE)));
		}
        return qGroup;
	}

}
