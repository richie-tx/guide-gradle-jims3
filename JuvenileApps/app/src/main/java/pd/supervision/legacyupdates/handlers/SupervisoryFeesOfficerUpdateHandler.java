/*
 * Created on Dec 18, 2008
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
import pd.supervision.legacyupdates.entities.SupervisoryFees;

/**
 * @author mchowdhury
 */
public class SupervisoryFeesOfficerUpdateHandler extends LegacyUpdatesUtil implements ILegacyUpdateHandler {

	public String createLog(IEvent event) throws IOException  {
		UpdateAssignmentDataEvent reqEvent = (UpdateAssignmentDataEvent) event;
		GetLegacyUpdatesEvent g_Event = new GetLegacyUpdatesEvent();
		g_Event.setRecordType(reqEvent.getRecType());
		g_Event.setSourceOID(reqEvent.getJims2SourceId());
		g_Event.setSpn(reqEvent.getSpn());
		List list = LegacyUpdateLog.findAll(g_Event);
		LegacyUpdateLog uLog = null;	
		String legacyUpdateLogId = "";	
		//action will always be update of fascas.
		if(list != null && !list.isEmpty()){
            uLog = (LegacyUpdateLog) list.get(0);
            legacyUpdateLogId = uLog.getOID();
            uLog.setStatusId(LegacyUpdateConstants.STATUS_PENDING_UPDATE);
            uLog.setProcMessage(PDConstants.BLANK);
			uLog.setLegacyUpdateLogger(	reqEvent.getSpn(), 
					reqEvent.getRecType(), 
					LegacyUpdateConstants.STATUS_PENDING_UPDATE, 
					reqEvent.getJims2SourceId(), 
					reqEvent.getCriminalCaseId(), 
					reqEvent.getLogonId(), 
					xmlWriter(reqEvent, LegacyUpdateConstants.ADULT_FEE_UPDATE));

		}else{
			uLog = new LegacyUpdateLog();
			uLog.setLegacyUpdateLogger(	reqEvent.getSpn(), 
										reqEvent.getRecType(), 
										LegacyUpdateConstants.STATUS_PENDING_UPDATE, 
										reqEvent.getJims2SourceId(), 
										reqEvent.getCriminalCaseId(), 
										reqEvent.getLogonId(), 
										xmlWriter(reqEvent, LegacyUpdateConstants.ADULT_FEE_UPDATE));
			legacyUpdateLogId = uLog.getOID();
		}
		return legacyUpdateLogId;
	}

	
	public void deleteLog(String sourceOid, String recType, String spn) {
		// no delete
	}
	
	public void deleteLegacy(LegacyUpdateLog logger) {
		// no delete according to Mary
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
		    	SupervisoryFees sf = new SupervisoryFees();
		        String cdi = criminalCaseId.substring(ZERO,CDI_LENGTH);
		        sf.setCdi(cdi);
		        String caseNum = criminalCaseId.substring(CDI_LENGTH);
		        sf.setCaseNumber(caseNum);
		        sf.setOpId(logger.getOpId());
		                   
		        Element element = null;
				try {
					element = getXMLElement(recordData, LegacyUpdateConstants.XML_ADULTPROBATIONFEE);
				} catch (JDOMException e) {
					logger.setError(e.getCause().toString());
				}
			    
				if (element != null)
			    {
			        String poi = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_POI);
			        String replaceXML = poi.replaceAll( "AMP", "&");
			        sf.setProbationOfficerIndicator( replaceXML );
			        
			        String cjadNumber = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_CJADNUMBER);
			        /*if (cjadNumber.length() < OFFICER_NUMBER_LENGTH){
			            StringBuffer sb = new StringBuffer(cjadNumber);
			            while (sb.length() < OFFICER_NUMBER_LENGTH){
			                sb.insert(ZERO,ZERO);
			            }
			            cjadNumber = sb.toString();
			        }*/
			        sf.setOfficerNumber(cjadNumber);
			        try{
			        	logger.setFasSeqNo(sf.update());			        	
						logger.setProcMessage(PDConstants.BLANK);
						checkForExceptions();
						logger.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);
						logger.bind();
			        } catch(Exception e){
						logger.setError(e.getCause().toString());
						clearExceptions();
			        }			        
			    }
		    }
        }
	}
	
	public String xmlWriter(UpdateAssignmentDataEvent reqEvent, String recType) throws IOException{
		Element root = new Element(LegacyUpdateConstants.XML_ASSIGNMENTTDATA);
		Element child = new Element(LegacyUpdateConstants.XML_ADULTPROBATIONFEE);
		if (reqEvent.getCjadNum() == null){
			child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CJADNUMBER,PDConstants.BLANK);
		} else {
			child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CJADNUMBER,reqEvent.getCjadNum());
		}
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_POI,reqEvent.getProbationOfficerInd());		
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ASSIGNMENTDATE,(reqEvent.getAssignmentDate() == null)?"":DateUtil.dateToString(reqEvent.getAssignmentDate(), DATE_FORMAT));
		root.addContent(child);	
		return writeToXML(root);
	}
}
