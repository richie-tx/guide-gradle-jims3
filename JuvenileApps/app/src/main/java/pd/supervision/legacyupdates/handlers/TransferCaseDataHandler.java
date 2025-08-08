package pd.supervision.legacyupdates.handlers;

import java.io.IOException;
import java.util.List;

import messaging.legacyupdates.GetLegacyUpdatesEvent;
import messaging.legacyupdates.UpdateTransferCaseDataEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import naming.LegacyUpdateConstants;

import org.jdom.Element;
import org.jdom.JDOMException;

import pd.supervision.legacyupdates.ILegacyUpdateHandler;
import pd.supervision.legacyupdates.LegacyUpdateLog;
import pd.supervision.legacyupdates.LegacyUpdatesUtil;
import pd.supervision.legacyupdates.entities.TransferCaseData;



/**
 * 
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TransferCaseDataHandler extends LegacyUpdatesUtil implements ILegacyUpdateHandler
{

	
	public void createLegacy(LegacyUpdateLog logger) 
	{
		String recordData = logger.getRecordData();
		if (recordData != null) 
		{
			TransferCaseData transferCaseData = new TransferCaseData();
			transferCaseData.setSpn(logger.getSpn());
			transferCaseData.setCdi(logger.getCriminalCaseId().substring(0, 3));
			transferCaseData.setCas(logger.getCriminalCaseId().substring(3));
			transferCaseData.setRecordType(logger.getRecordTypeId());
			transferCaseData.setOpId(logger.getOpId());
			
			getValueFromXML(transferCaseData, logger);
	       
			try {
				logger.setCstsSeqNo(transferCaseData.bind());
				logger.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);
				checkForExceptions();
			} catch (Exception e) {
				e.printStackTrace();
				logger.setStatusId(LegacyUpdateConstants.STATUS_ERROR);
				logger.setError(e.getCause().getMessage());
				clearExceptions();
			}  finally{
				logger.bind();
			}
		}
	}

	
	
	public void deleteLegacy(LegacyUpdateLog logger) 
	{
		String recordData = logger.getRecordData();
		if (recordData != null) 
		{
			TransferCaseData transferCaseData = new TransferCaseData();
			transferCaseData.setSpn(logger.getSpn());
			transferCaseData.setAction(logger.getStatusId());
			transferCaseData.setSeqNumber(logger.getCstsSeqNo());
			transferCaseData.setCdi(logger.getCriminalCaseId().substring(0, 3));
			transferCaseData.setCas(logger.getCriminalCaseId().substring(4));
			transferCaseData.setRecordType(logger.getRecordTypeId());
			getValueFromXML(transferCaseData, logger);
	        
			try {
				transferCaseData.delete();
				checkForExceptions();
				logger.delete();
			}  catch (Exception e) {
				e.printStackTrace();
				logger.setStatusId(LegacyUpdateConstants.STATUS_ERROR);
				logger.setError(e.getCause().getMessage());
				clearExceptions();
			}
		}
	}

	

	public void updateLegacy(LegacyUpdateLog logger) 
	{
		createLegacy(logger);
	}
	
	
	
	public String createLog(IEvent event) throws IOException 
	{
		UpdateTransferCaseDataEvent reqEvent = (UpdateTransferCaseDataEvent) event;
		GetLegacyUpdatesEvent gEvent = new GetLegacyUpdatesEvent();
		gEvent.setRecordType(reqEvent.getRecType());
		gEvent.setSourceOID(reqEvent.getJims2SourceId());
		gEvent.setSpn(reqEvent.getSpn());
		List list = LegacyUpdateLog.findAll(gEvent);
		LegacyUpdateLog uLog = null;
		String legacyUpdateLogId = "";
		if (list != null && !list.isEmpty()) 
		{
			uLog = (LegacyUpdateLog) list.get(0);
			legacyUpdateLogId = uLog.getOID();
			if (uLog.getCstsSeqNo() != null && !uLog.getCstsSeqNo().equals("")) {
				uLog.updateLegacyUpdateLogger(xmlWriter(reqEvent, reqEvent
						.getRecType()));
			} else {
				uLog.updateUpdateLoggerOnly(xmlWriter(reqEvent, reqEvent
						.getRecType()));
			}
		}
		else 
		{
			uLog = new LegacyUpdateLog();
			uLog.setLegacyUpdateLogger(reqEvent.getSpn(),
					reqEvent.getRecType(), LegacyUpdateConstants.STATUS_NEW,
					reqEvent.getJims2SourceId(), reqEvent.getCriminalCaseId(),
					reqEvent.getLogonId(), xmlWriter(reqEvent,
							LegacyUpdateConstants.TST30_UPDATE));
			legacyUpdateLogId = uLog.getOID();
		}
		return legacyUpdateLogId;
	}

	
	public void deleteLog(String sourceOid, String recType, String spn) 
	{
		GetLegacyUpdatesEvent gEvent = new GetLegacyUpdatesEvent();
		gEvent.setRecordType(recType);
		gEvent.setSourceOID(sourceOid);
		gEvent.setSpn(spn);
		List<LegacyUpdateLog> list = LegacyUpdateLog.findAll(gEvent);
		if (list != null && !list.isEmpty()) {
			LegacyUpdateLog log = list.get(0);
			if (log.getCstsSeqNo() != null) {
				log.setStatusId(LegacyUpdateConstants.STATUS_PENDING_DELETE);
			} else {
				log.delete();
			}
		}
	}
	
	private String xmlWriter(UpdateTransferCaseDataEvent reqEvent, String recType)throws IOException  
	{
		Element root = new Element(LegacyUpdateConstants.XML_TRANSFERCASEDATA);
		Element firstChild = new Element(LegacyUpdateConstants.XML_TRANSFERCASE); 
		
//		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_OOCCASEACTION,
//				(reqEvent.getAction() == null) ? "" : reqEvent.getAction());
		String action = "";
		if((reqEvent.getAction().equalsIgnoreCase(LegacyUpdateConstants.OOC_CASE_CREATE)) ||
				(reqEvent.getAction().equalsIgnoreCase(LegacyUpdateConstants.OOC_CASE_REACTIVATE)) ||
				(reqEvent.getAction().equalsIgnoreCase(LegacyUpdateConstants.OOC_CASE_CLOSE)))
		{
			action = LegacyUpdateConstants.STATUS_NEW;
		} else if((reqEvent.getAction().equalsIgnoreCase(LegacyUpdateConstants.OOC_CASE_UPDATE)) ||
			(reqEvent.getAction().equalsIgnoreCase(LegacyUpdateConstants.OOC_CASE_UPDATE_CLOSURE)))
		{
			action= LegacyUpdateConstants.STATUS_PENDING_UPDATE;
		} else { 
			action = reqEvent.getAction();
		}
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_OOCCASEACTION,
				(action == null) ? "" : action);

		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_TRANSFERDATE,
				(reqEvent.getTransferDate() == null) ? "" : DateUtil.dateToString(reqEvent.getTransferDate(), DATE_FORMAT));
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_TRANSFERINGCNTYJURISCD,
				(reqEvent.getTransfrCntyJurisCode() == null) ? "" : reqEvent.getTransfrCntyJurisCode());
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_RECEIVINGCNTYJURISCD,
				(reqEvent.getReceivngCntyJurisCode() == null) ? "" : reqEvent.getReceivngCntyJurisCode());
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_TRANSFERTYPECODE,
				(reqEvent.getTransferTypeCode() == null) ? "" : reqEvent.getTransferTypeCode());
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ORICNTYPERSONID,
				(reqEvent.getOriCntyPersonId() == null) ? "" : reqEvent.getOriCntyPersonId());
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_SPRVSNGCNTYPERSONID,
				(reqEvent.getSuprvsngCntyPersonId() == null) ? "" : reqEvent.getSuprvsngCntyPersonId());
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ISJURISDICTIONCDCHANGED,
				(reqEvent.isJurisCodeChanged()) ? "true" : "false");
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ISSUPERVISIONREJECTED,
				(reqEvent.isSupervisionRejected()) ? "true" : "false");
		
		root.addContent(firstChild);
		return writeToXML(root);
	}
	
	
	
	private void getValueFromXML(TransferCaseData transferCaseData, LegacyUpdateLog logger)  {
		String recordData = logger.getRecordData();
		Element element = null;
		try 
		{
			element = getXMLElement(recordData,
					LegacyUpdateConstants.XML_TRANSFERCASE);
		} catch (JDOMException e) {
			e.printStackTrace();
			logger.setError(e.getMessage());
		}

		if (element != null) 
		{
			String action = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_OOCCASEACTION);
//			if((action.equalsIgnoreCase(LegacyUpdateConstants.OOC_CASE_CREATE)) ||
//					(action.equalsIgnoreCase(LegacyUpdateConstants.OOC_CASE_REACTIVATE)) ||
//					(action.equalsIgnoreCase(LegacyUpdateConstants.OOC_CASE_CLOSE)))
//			{
//				transferCaseData.setAction(LegacyUpdateConstants.STATUS_NEW);
//				transferCaseData.setSeqNumber("");
//			} 
//			else if((action.equalsIgnoreCase(LegacyUpdateConstants.OOC_CASE_UPDATE)) ||
//				(action.equalsIgnoreCase(LegacyUpdateConstants.OOC_CASE_UPDATE_CLOSURE)))
//			{
//				transferCaseData.setAction(LegacyUpdateConstants.STATUS_PENDING_UPDATE);
//				transferCaseData.setSeqNumber(logger.getCstsSeqNo());
//			} 
//			else 
//			{
//				transferCaseData.setAction(action);
//				transferCaseData.setSeqNumber(logger.getCstsSeqNo());
//			}
			if (action.equals(LegacyUpdateConstants.STATUS_NEW)){
				transferCaseData.setSeqNumber("");
			} else {
				transferCaseData.setSeqNumber(logger.getCstsSeqNo());
			}
			transferCaseData.setSeqNumber(logger.getCstsSeqNo());
			String transferDate = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_TRANSFERDATE);
			transferCaseData.setTransferDate(DateUtil.stringToDate(transferDate,
					DATE_FORMAT));
			
			String transferringCntySprvnCode = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_TRANSFERINGCNTYJURISCD);
			transferCaseData.setTransferringCntySprvnCode(transferringCntySprvnCode);
			
			String receivingCntySprvnCode = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_RECEIVINGCNTYJURISCD);
			transferCaseData.setReceivingCntySprvnCode(receivingCntySprvnCode);
			
			String transferTypeCode = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_TRANSFERTYPECODE);
			transferCaseData.setTransferTypeCode(transferTypeCode);
			
			String oriCntyPersonId = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_ORICNTYPERSONID);
			transferCaseData.setOriCntyPersonId(oriCntyPersonId);
			
			String supervisingCntyPersonId = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_SPRVSNGCNTYPERSONID);
			transferCaseData.setSupervisingCntyPersonId(supervisingCntyPersonId);
			
			String isJurisCodeChanged = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_ISJURISDICTIONCDCHANGED);
			if(isJurisCodeChanged.equalsIgnoreCase("true")){
				transferCaseData.setJurisdictionCodeChanged("Y");
			}
			else if (isJurisCodeChanged.equals("false"))
			{
				transferCaseData.setJurisdictionCodeChanged("N");
			}
			
			String isSupervisionRejected = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_ISSUPERVISIONREJECTED);
			if(isSupervisionRejected.equalsIgnoreCase("true"))
			{
				transferCaseData.setSupervisionRejected("Y");
			}
			else
			{
				transferCaseData.setSupervisionRejected("N");
			}
		}
	}
}
