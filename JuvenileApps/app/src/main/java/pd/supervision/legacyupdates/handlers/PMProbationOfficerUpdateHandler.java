/*
 * Created on Sep 16, 2008
 *
 */
package pd.supervision.legacyupdates.handlers;

import java.io.IOException;

import messaging.legacyupdates.UpdateAssignmentDataEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.LegacyUpdateConstants;

import org.jdom.Element;
import org.jdom.JDOMException;

import pd.contact.user.UserProfile;
import pd.supervision.legacyupdates.ILegacyUpdateHandler;
import pd.supervision.legacyupdates.LegacyUpdateLog;
import pd.supervision.legacyupdates.LegacyUpdatesUtil;
import pd.supervision.legacyupdates.entities.PersonMasterUpdate;

/**
 * @author mchowdhury
 *
 */
public class PMProbationOfficerUpdateHandler extends LegacyUpdatesUtil implements ILegacyUpdateHandler {

	public String createLog(IEvent event) throws IOException  {
		String legacyUpdateLogId = "";
		UpdateAssignmentDataEvent reqEvent = (UpdateAssignmentDataEvent) event;
		PersonMasterUpdate pm = new PersonMasterUpdate();
		 pm.setCdi(reqEvent.getCriminalCaseId().substring(ZERO,CDI_LENGTH));
		 pm.setCaseNumber(reqEvent.getCriminalCaseId().substring(CDI_LENGTH));
		 UserProfile up = UserProfile.find(reqEvent.getLogonId());
			if(up != null){
			    pm.setOpId(up.getOperatorId());
			}	     
		String transactionDate = (reqEvent.getTransactionDate() == null)?"":DateUtil.dateToString(reqEvent.getTransactionDate(), DATE_FORMAT);
	     pm.setTransactionDate(DateUtil.stringToDate(transactionDate, DATE_FORMAT));
	     pm.setOfficerNumber(reqEvent.getCjadNum());
	     pm.setProbationOfficerIndicator(reqEvent.getProbationOfficerInd());
	     pm.setTransactionDate(DateUtil.stringToDate(transactionDate, DATE_FORMAT));
	     try {
	    	 legacyUpdateLogId = pm.update();
	    	 checkForExceptions();
	     }catch(Exception io){
	    	 throw new IOException("Update Failed");
	     }
	     
//		GetLegacyUpdatesEvent g_Event = new GetLegacyUpdatesEvent();
//		g_Event.setRecordType(reqEvent.getRecType());
//		g_Event.setSourceOID(reqEvent.getJims2SourceId());
//		g_Event.setSpn(reqEvent.getSpn());
//		List list = LegacyUpdateLog.findAll(g_Event);
//		LegacyUpdateLog uLog = null;	
//		String legacyUpdateLogId = "";		
//		if(list != null && !list.isEmpty()){
//            uLog = (LegacyUpdateLog) list.get(0);
//            legacyUpdateLogId = uLog.getOID();
//			if (uLog.getCstsSeqNo() != null && !uLog.getCstsSeqNo().equals("")) {
//				uLog.updateLegacyUpdateLogger(xmlWriter(reqEvent, reqEvent
//						.getRecType()));
//			} else {
//				uLog.updateUpdateLoggerOnly(xmlWriter(reqEvent, reqEvent
//						.getRecType()));
//			}
//		}else{
//			uLog = new LegacyUpdateLog();
//			uLog.setLegacyUpdateLogger(	reqEvent.getSpn(), 
//										reqEvent.getRecType(), 
//										LegacyUpdateConstants.STATUS_NEW, 
//										reqEvent.getJims2SourceId(), 
//										reqEvent.getCriminalCaseId(), 
//										reqEvent.getLogonId(), 
//										xmlWriter(reqEvent, LegacyUpdateConstants.PMJZP_UPDATE));
//			legacyUpdateLogId = uLog.getOID();
//		}
		return legacyUpdateLogId;
	}

	public void deleteLog(String sourceOid, String recType, String spn) {
		// There is no such deletion of Log since there is no such deletion of Legacy
	}
	
	public void deleteLegacy(LegacyUpdateLog logger) {
		// no delete legacy according to Mary.
	}
	
	public void createLegacy(LegacyUpdateLog logger) {
		updateLegacy(logger);
	}

	public void updateLegacy(LegacyUpdateLog logger) {
		String recordData = logger.getRecordData();
        if (recordData != null)
        {          
		    String criminalCaseId = logger.getCriminalCaseId();	        
		    if (criminalCaseId != null && criminalCaseId.length() > CDI_LENGTH){
		    	PersonMasterUpdate pm = new PersonMasterUpdate();
		        String cdi = criminalCaseId.substring(ZERO,CDI_LENGTH);
		        pm.setCdi(cdi);
		        String caseNum = criminalCaseId.substring(CDI_LENGTH);
		        pm.setCaseNumber(caseNum);
		        pm.setOpId(logger.getOpId());
		        
		                   
		        Element element = null;
				try {
					element = getXMLElement(recordData, LegacyUpdateConstants.XML_PERSONMASTERUPDATE);
				} catch (JDOMException e) {
					e.printStackTrace();
					logger.setError("Error pasring the xml");
				}
			    
				if (element != null)
			    {
			        String poi = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_POI);
			        String replaceXML = poi.replaceAll( "AMP", "&");
			        pm.setProbationOfficerIndicator( replaceXML );
			        
			        String transactionDate = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_TRANSACTIONDATE);
			        pm.setTransactionDate(DateUtil.stringToDate(transactionDate, DATE_FORMAT));
			        //pm.setTransDate(transactionDate);
			        
			        String cjadNumber = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_CJADNUMBER);
			        /*if (cjadNumber.length() < OFFICER_NUMBER_LENGTH){
			            StringBuffer sb = new StringBuffer(cjadNumber);
			            while (sb.length() < OFFICER_NUMBER_LENGTH){
			                sb.insert(ZERO,ZERO);
			            }
			            cjadNumber = sb.toString();
			        }*/
			        pm.setOfficerNumber(cjadNumber);
			        
			        try{
			        	logger.setPmSeqNo(pm.update());
			        	checkForExceptions();
						logger.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);
						logger.bind();
			        }catch(Exception rE){
						logger.setStatusId(LegacyUpdateConstants.STATUS_ERROR);						
			        	rE.printStackTrace();
						logger.setError(rE.getCause().toString());
			        }
			    }
		    }
        }
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
