/*
 * Created on Dec 18, 2007
 *
 */
package pd.supervision.legacyupdates.handlers;

import java.io.IOException;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;

import naming.LegacyUpdateConstants;
import naming.PDConstants;
import messaging.legacyupdates.GetLegacyUpdatesEvent;
import messaging.legacyupdates.UpdateReferralSubmitDataEvent;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import pd.contact.user.UserProfile;
import pd.security.PDSecurityHelper;
import pd.supervision.legacyupdates.ILegacyUpdateHandler;
import pd.supervision.legacyupdates.LegacyUpdateLog;
import pd.supervision.legacyupdates.LegacyUpdatesUtil;
import pd.supervision.legacyupdates.entities.ReferralSubmit;

/**
 * @author Raja Veerappan
 * 
 */
public class ReferralSubmitHandler extends LegacyUpdatesUtil implements
		ILegacyUpdateHandler {

	private final String LEGACY_DATE_FORMAT = "MMddyyyy";

	private final String ZEROES = "00";
	public void createLegacy(LegacyUpdateLog logger) {
		String recordData = logger.getRecordData();
		if (recordData != null) {
			ReferralSubmit referralData = new ReferralSubmit();
			referralData.setSpn(logger.getSpn());
			referralData.setAction(logger.getStatusId());
			referralData.setSeqNumber(logger.getCstsSeqNo());
			referralData.setCdi(logger.getCriminalCaseId().substring(0, 3));
			referralData.setCas(logger.getCriminalCaseId().substring(3));
			referralData.setRecordType(logger.getRecordTypeId());
			referralData.setOpId(logger.getOpId());
			
			getValueFromXML(referralData, logger);
			
	        if (referralData.getConfineDays() != null){
	        	if (referralData.getConfineDays().length() < 2){
	        		StringBuffer sb = new StringBuffer(referralData.getConfineDays());
	        		while (sb.length() < 2){
	                sb.insert(ZERO,ZERO);
	        		}
	        		referralData.setConfineDays( sb.toString());
	        	}
	        } else {
	        	referralData.setConfineDays(ZEROES);
	        }
	        if (referralData.getConfineMonths() != null) {
	        	if (referralData.getConfineMonths().length() < 2){
	        		StringBuffer sb = new StringBuffer(referralData.getConfineMonths());
	        		while (sb.length() < 2){
	        			sb.insert(ZERO,ZERO);
	        		}
	        		referralData.setConfineMonths( sb.toString());
	        	}
	        } else {
	        	referralData.setConfineMonths(ZEROES);
	        }
	        if (referralData.getConfineYears() !=  null) {
	        	if (referralData.getConfineYears() !=  null && referralData.getConfineYears().length() < 2){
	        		StringBuffer sb = new StringBuffer(referralData.getConfineYears());
	        		while (sb.length() < 2){
	        			sb.insert(ZERO,ZERO);
	        		}
	        		referralData.setConfineYears( sb.toString());
	        	}
	        } else {
	        	referralData.setConfineYears(ZEROES);
	        }
	        //Non-incarceration referrals should have the confinement fields set to blank.
	        if (referralData.getConfineDays().equals(ZEROES)
	        		&& referralData.getConfineMonths().equals(ZEROES)
	        		&& referralData.getConfineYears().equals(ZEROES)){
	        	referralData.setConfineDays(PDConstants.BLANK);
	        	referralData.setConfineMonths(PDConstants.BLANK);
	        	referralData.setConfineYears(PDConstants.BLANK);
	        }
	        if (referralData.getDesignator() != null && referralData.getDesignator().length() < 2){
	            StringBuffer sb = new StringBuffer(referralData.getDesignator());
	            while (sb.length() < 2){
	                sb.insert(ZERO,ZERO);
	            }
	            referralData.setDesignator( sb.toString());
	        }
			try {
				logger.setProcMessage(PDConstants.BLANK);
				logger.setCstsSeqNo(referralData.bind());
				checkForExceptions();
				if (referralData.getMsg() != null && !referralData.getMsg().trim().equals(PDConstants.BLANK)){
					logger.setError(referralData.getMsg());
				} else {
					logger.setStatusId(LegacyUpdateConstants.STATUS_PROCESSED);		
					logger.bind();
				}
			}  catch (Exception e) {
				logger.setError(e.getCause().toString());
				clearExceptions();
			} 
		}
	}

	public void deleteLegacy(LegacyUpdateLog logger)  {
		String recordData = logger.getRecordData();
		if (recordData != null) {
			ReferralSubmit referralData = new ReferralSubmit();
			referralData.setSpn(logger.getSpn());
			referralData.setAction(logger.getStatusId());
			referralData.setSeqNumber(logger.getCstsSeqNo());
			referralData.setCdi(logger.getCriminalCaseId().substring(0, 3));
			referralData.setCas(logger.getCriminalCaseId().substring(3));
			referralData.setRecordType(logger.getRecordTypeId());
			getValueFromXML(referralData, logger);
	        if (referralData.getConfineDays() != null && referralData.getConfineDays().length() < 2){
	            StringBuffer sb = new StringBuffer(referralData.getConfineDays());
	            while (sb.length() < 2){
	                sb.insert(ZERO,ZERO);
	            }
	            if(sb.toString().equals("00"))
	            {
	            	referralData.setConfineDays(null);
	            }
	            else
	            {
	            	referralData.setConfineDays( sb.toString());
	            }
	        }
	        if (referralData.getConfineMonths() != null && referralData.getConfineMonths().length() < 2){
	            StringBuffer sb = new StringBuffer(referralData.getConfineMonths());
	            while (sb.length() < 2){
	                sb.insert(ZERO,ZERO);
	            }
	            if(sb.toString().equals("00"))
	            {
	            	referralData.setConfineMonths(null);
	            }
	            else
	            {
	            	referralData.setConfineMonths( sb.toString());
	            }
	        }
	        if (referralData.getConfineYears() != null && referralData.getConfineYears().length() < 2){
	            StringBuffer sb = new StringBuffer(referralData.getConfineYears());
	            while (sb.length() < 2){
	                sb.insert(ZERO,ZERO);
	            }
	            if(sb.toString().equals("00"))
	            {
	            	referralData.setConfineYears(null);
	            }
	            else
	            {
	            	referralData.setConfineYears(sb.toString());
	            }
	        }

			try {
				logger.setProcMessage(PDConstants.BLANK);
				referralData.bind();
				checkForExceptions();
				logger.delete();
			} catch (Exception e) {
				logger.setError(e.getCause().toString());
				clearExceptions();
			}
		}

	}

	public void updateLegacy(LegacyUpdateLog logger) {
		createLegacy(logger);
	}

	public String createLog(IEvent event) throws IOException {
		UpdateReferralSubmitDataEvent reqEvent = (UpdateReferralSubmitDataEvent) event;
		GetLegacyUpdatesEvent gEvent = new GetLegacyUpdatesEvent();
		gEvent.setRecordType(reqEvent.getRecType());
		gEvent.setSourceOID(reqEvent.getJims2SourceId());
		gEvent.setSpn(reqEvent.getSpn());
		List list = LegacyUpdateLog.findAll(gEvent);
		LegacyUpdateLog uLog = null;
		String legacyUpdateLogId = "";
		if (list != null && !list.isEmpty()) {
			uLog = (LegacyUpdateLog) list.get(0);
			legacyUpdateLogId = uLog.getOID();
			uLog.setProcMessage(PDConstants.BLANK);
            uLog.setCriminalCaseId(reqEvent.getCriminalCaseId());
			UserProfile up = UserProfile.find(reqEvent.getLogonId());
			if(up != null){
			    uLog.setOpId(up.getOperatorId());
			}
			if (uLog.getCstsSeqNo() != null && !uLog.getCstsSeqNo().equals(PDConstants.BLANK)) {
				reqEvent.setAction(LegacyUpdateConstants.CHANGE);
			} else {
				reqEvent.setAction(LegacyUpdateConstants.ADD);
			}
			uLog.setLegacyUpdateLogger(reqEvent.getSpn(),
					reqEvent.getRecType(), 
					reqEvent.getAction(),
					reqEvent.getJims2SourceId(), 
					reqEvent.getCriminalCaseId(),
					reqEvent.getLogonId(), 
					xmlWriter(reqEvent,	reqEvent.getRecType()));
		} else {
			uLog = new LegacyUpdateLog();
			reqEvent.setAction(LegacyUpdateConstants.ADD);
			uLog.setLegacyUpdateLogger(reqEvent.getSpn(),
					reqEvent.getRecType(), 
					LegacyUpdateConstants.STATUS_NEW,
					reqEvent.getJims2SourceId(), 
					reqEvent.getCriminalCaseId(),
					reqEvent.getLogonId(), 
					xmlWriter(reqEvent,LegacyUpdateConstants.TST33_UPDATE));
			legacyUpdateLogId = uLog.getOID();
		}
		return legacyUpdateLogId;
	}

	public void deleteLog(String sourceOid, String recType, String spn) {
		GetLegacyUpdatesEvent gEvent = new GetLegacyUpdatesEvent();
		gEvent.setRecordType(recType);
		gEvent.setSourceOID(sourceOid);
		gEvent.setSpn(spn);
		List<LegacyUpdateLog> list = LegacyUpdateLog.findAll(gEvent);
		if (list != null && !list.isEmpty()) {
			LegacyUpdateLog log = list.get(0);
			log.setProcMessage(PDConstants.BLANK);
			if (log.getCstsSeqNo() != null) {
				UserProfile user = UserProfile.find(PDSecurityHelper.getLogonId());
				if (user != null){
					log.setOpId(user.getOperatorId());
				}
				log.setStatusId(LegacyUpdateConstants.STATUS_PENDING_DELETE);
				String newRecData = getRecData(log.getRecordData());
				log.setRecordData(newRecData);
				log.bind();
			} else {
				log.delete();
			}
		}
	}

	private String xmlWriter(UpdateReferralSubmitDataEvent reqEvent,
			String recType)throws IOException  {
		Element root = new Element(LegacyUpdateConstants.XML_REFERRALDATA);
		Element firstChild = new Element(
				LegacyUpdateConstants.XML_REFERRALSUBMIT); 
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,
				reqEvent.getAction());
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CTSCODE,
				(reqEvent.getCtsCode() == null) ? "" : reqEvent.getCtsCode());
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_PFSCODE,
				(reqEvent.getPfsCode() == null) ? "" : reqEvent.getPfsCode());
		firstChild
				.setAttribute(LegacyUpdateConstants.ATTRIBUTE_PROGBEGINDATE,
						(reqEvent.getProgBeginDate() == null) ? "" : DateUtil
								.dateToString(reqEvent.getProgBeginDate(),
										LEGACY_DATE_FORMAT));
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_DESIGNATOR,
				(reqEvent.getDesignator() == null) ? "" : reqEvent
						.getDesignator());	
		firstChild.setAttribute(
				LegacyUpdateConstants.ATTRIBUTE_PLACEMENTREASON, (reqEvent
						.getPlacementReason() == null) ? "" : reqEvent
						.getPlacementReason());
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CONFINEDAYS,
				new Integer(reqEvent.getConfineDays()).toString());
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CONFINEMONTHS,
				new Integer(reqEvent.getConfineMonths()).toString());
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CONFINEYEARS,
				new Integer(reqEvent.getConfineYears()).toString());
		firstChild.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ISCOMMJUSTICEPLAN,
				(reqEvent.isInHouse()) ? "Y" : "N");
		root.addContent(firstChild);
		return writeToXML(root);
	}

	private void getValueFromXML(ReferralSubmit referralData,
			LegacyUpdateLog logger)  {
		String recordData = logger.getRecordData();
		Element element = null;
		try {
			element = getXMLElement(recordData,
					LegacyUpdateConstants.XML_REFERRALSUBMIT);
		} catch (JDOMException e) {
			e.printStackTrace();
			logger.setError(e.getMessage());
		}

		if (element != null) {
			String action = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ACTION);
	        referralData.setAction(action);

			String ctsCode = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_CTSCODE);
			referralData.setCtsCode(ctsCode);
			
			String pfsCode = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_PFSCODE);
			referralData.setPfsCode( pfsCode );

			String progBeginDate = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_PROGBEGINDATE);
			referralData.setProgBeginDate( progBeginDate );

			String designator = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_DESIGNATOR);
			referralData.setDesignator(designator);
			
			String placementReason = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_PLACEMENTREASON);
			referralData.setPlacementReason(placementReason);

			String confineDays = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_CONFINEDAYS);
			referralData.setConfineDays(confineDays);

			String confineMonths = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_CONFINEMONTHS);
			referralData.setConfineMonths(confineMonths);

			String confineYears = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_CONFINEYEARS);
			referralData.setConfineYears(confineYears);

			String inHouse = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_ISCOMMJUSTICEPLAN);
			referralData.setIsCommJusticePlan(("Y".equals(inHouse)) ? true : false);

		}
	}
	private  String getRecData(String recData){
		Element root = new Element(LegacyUpdateConstants.XML_REFERRALDATA);
		Element child = new Element(LegacyUpdateConstants.XML_REFERRALSUBMIT);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,LegacyUpdateConstants.DELETE);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CTSCODE,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_CTSCODE));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_PFSCODE,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_PFSCODE));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_PROGBEGINDATE,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_PROGBEGINDATE));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_DESIGNATOR,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_DESIGNATOR));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_PLACEMENTREASON,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_PLACEMENTREASON));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CONFINEDAYS,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_CONFINEDAYS));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CONFINEMONTHS,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_CONFINEMONTHS));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CONFINEYEARS,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_CONFINEYEARS));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ISCOMMJUSTICEPLAN,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_ISCOMMJUSTICEPLAN));
		root.addContent(child);	
		String newRecData = null;
		try {
			newRecData = writeToXML(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newRecData;
	}
}
