/*
 * Created on Dec 18, 2007
 *
 */
package pd.supervision.legacyupdates.handlers;

import java.io.IOException;
import java.util.List;

import messaging.legacyupdates.GetLegacyUpdatesEvent;
import messaging.legacyupdates.UpdateAssignmentDataEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.LegacyUpdateConstants;
import naming.PDConstants;

import org.jdom.Element;
import org.jdom.JDOMException;

import pd.supervision.legacyupdates.ILegacyUpdateHandler;
import pd.supervision.legacyupdates.LegacyUpdateLog;
import pd.supervision.legacyupdates.LegacyUpdatesUtil;
import pd.supervision.legacyupdates.entities.SupervisingOfficer;


/**
 * @author mchowdhury
 *
 */
public class CSTSProbationOfficerUpdateHandler extends LegacyUpdatesUtil implements ILegacyUpdateHandler {
   
	public String createLog(IEvent event) throws IOException  {
		UpdateAssignmentDataEvent reqEvent = (UpdateAssignmentDataEvent) event;
		GetLegacyUpdatesEvent gEvent = new GetLegacyUpdatesEvent();
		gEvent.setRecordType(reqEvent.getRecType());
		gEvent.setSourceOID(reqEvent.getJims2SourceId());
		List list = LegacyUpdateLog.findAll(gEvent);
		LegacyUpdateLog uLog = null;	
		String legacyUpdateLogId = "";		
		if(list != null && !list.isEmpty()){
            uLog = (LegacyUpdateLog) list.get(0);
            legacyUpdateLogId = uLog.getOID();
            uLog.setProcMessage(PDConstants.BLANK);
 			uLog.setLegacyUpdateLogger(reqEvent.getSpn(), 
					reqEvent.getRecType(), 
					LegacyUpdateConstants.STATUS_NEW, 
					reqEvent.getJims2SourceId(), 
					reqEvent.getCriminalCaseId(), 
					reqEvent.getLogonId(), 
					xmlWriter(reqEvent, LegacyUpdateConstants.TST24_UPDATE));
		}else{
			uLog = new LegacyUpdateLog();
			uLog.setLegacyUpdateLogger(reqEvent.getSpn(), 
					reqEvent.getRecType(), 
					LegacyUpdateConstants.STATUS_NEW, 
					reqEvent.getJims2SourceId(), 
					reqEvent.getCriminalCaseId(), 
					reqEvent.getLogonId(), 
					xmlWriter(reqEvent, LegacyUpdateConstants.TST24_UPDATE));
			legacyUpdateLogId = uLog.getOID();
		}		
		return legacyUpdateLogId;
	}

	public void deleteLog(String sourceOid, String recType, String spn) {
        // no delete
	}
	
	public void createLegacy(LegacyUpdateLog logger) {
		String recordData = logger.getRecordData();
        if (recordData != null)
        {          
		    String criminalCaseId = logger.getCriminalCaseId();	        
		    if (criminalCaseId != null && criminalCaseId.length() > CDI_LENGTH){
		    	SupervisingOfficer so = new SupervisingOfficer();
		    	String cdi = criminalCaseId.substring(ZERO,CDI_LENGTH);
		        so.setCdi(cdi);
		        String caseNum = criminalCaseId.substring(CDI_LENGTH);
		        so.setCaseNumber(caseNum);
		        so.setLegacyAssignmentLogId(logger.getSourceId());   
		        so.setOpId(logger.getOpId());            
		        Element element = null;
				try {
					element = getXMLElement(recordData, LegacyUpdateConstants.XML_SUPERVISINGOFFICERINFO);
				} catch (JDOMException e) {
					logger.setError("Error parsing the XML");
				}
			    
				if (element != null)
			    {
			        String  cjadNumber = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_CJADNUMBER);
			        /*if (cjadNumber.length() < OFFICER_NUMBER_LENGTH){
			            StringBuffer sb = new StringBuffer(cjadNumber);
			            while (sb.length() < OFFICER_NUMBER_LENGTH){
			                sb.insert(ZERO,ZERO);
			            }
			            cjadNumber = sb.toString();
			        }*/
			        so.setOfficerNumber(cjadNumber);
			        
			        String poi = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_POI);
			        String replaceXML = poi.replaceAll( "AMP", "&");
			        so.setProbationOfficerIndicator( replaceXML );
			        
			        String assignmentDate = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ASSIGNMENTDATE);
			        so.setOfficerBeginDate(DateUtil.stringToDate(assignmentDate, DATE_FORMAT));
			        try {
						logger.setProcMessage(PDConstants.BLANK);
			        	logger.setCstsSeqNo(so.update());
						checkForExceptions();
						logger.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);
						logger.bind();						
					}  catch (Exception e) {
						logger.setError(e.getCause().toString());
						clearExceptions();
					}					
			    }
		    }
        }
    }

	public void updateLegacy(LegacyUpdateLog logger) {

	}	

	public void deleteLegacy(LegacyUpdateLog logger) {
		// no delete according to Mary
	}
	
	public String xmlWriter(UpdateAssignmentDataEvent reqEvent, String recType) throws IOException{
		Element root = new Element(LegacyUpdateConstants.XML_ASSIGNMENTTDATA);
		Element child = null;
		if(LegacyUpdateConstants.PMJZP_UPDATE.equalsIgnoreCase(recType)){
			child = new Element(LegacyUpdateConstants.XML_PERSONMASTERUPDATE);
			child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CJADNUMBER,reqEvent.getCjadNum());
			child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_TRANSACTIONDATE, (reqEvent.getTransactionDate() == null)?"":DateUtil.dateToString(reqEvent.getTransactionDate(), DATE_FORMAT));
			child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_POI,reqEvent.getProbationOfficerInd());			
		}else if(LegacyUpdateConstants.TST24_UPDATE.equalsIgnoreCase(recType)){
			child = new Element(LegacyUpdateConstants.XML_SUPERVISINGOFFICERINFO);
			child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CJADNUMBER,reqEvent.getCjadNum());
			child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_POI,reqEvent.getProbationOfficerInd());		
			child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSIGNMENTDATE,(reqEvent.getAssignmentDate() == null)?"":DateUtil.dateToString(reqEvent.getAssignmentDate(), DATE_FORMAT));
		}else if(LegacyUpdateConstants.ADULT_FEE_UPDATE.equalsIgnoreCase(recType)){
			child = new Element(LegacyUpdateConstants.XML_ADULTPROBATIONFEE);
			child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_POI,reqEvent.getProbationOfficerInd());	
			child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CJADNUMBER,reqEvent.getCjadNum());
		}else{
			return "";
		}
		root.addContent(child);	
		return writeToXML(root);
	}
}
