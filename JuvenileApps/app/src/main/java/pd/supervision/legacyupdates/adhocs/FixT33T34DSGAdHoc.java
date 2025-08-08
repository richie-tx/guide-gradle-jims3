package pd.supervision.legacyupdates.adhocs;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import naming.LegacyUpdateConstants;
import naming.PDConstants;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import messaging.legacyupdates.GetLegacyUpdateRecordsToFixEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.transaction.multidatasource.TransactionManager;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import pd.codetable.supervision.CSProgramReferralType;
import pd.supervision.legacyupdates.LegacyUpdateLog;
import pd.supervision.legacyupdates.entities.ReferralSubmit;

public class FixT33T34DSGAdHoc {
	private static boolean isUpdate;
	BufferedWriter outputfile;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FixT33T34DSGAdHoc fixit = new FixT33T34DSGAdHoc();
		
		if (args[0].equals("UPDATE")){
			isUpdate = true;
		}
		
		GetLegacyUpdateRecordsToFixEvent gEvent = new GetLegacyUpdateRecordsToFixEvent();
		//RECSTATUS='E' AND RECTYPE IN ('T33','T34')
		List list = LegacyUpdateLog.findAll(gEvent);
		LegacyUpdateLog log = null;
		
		fixit.outputfile = createFile("LegacyUpdateLogCorrections.FixDSGAdHoc", fixit.outputfile);
		
		for (int i = 0; i < list.size(); i++) {
			log = (LegacyUpdateLog) list.get(i);
			fixit.updateLegacy(log);
		}

	}
	private void updateLegacy(LegacyUpdateLog log) {
		if (log.getRecordTypeId().equals("T33")){
			ReferralSubmit refData = getT33ValueFromXML(log.getRecordData());
			if (refData.getDesignator()!= null && !refData.getDesignator().equals(PDConstants.BLANK)){
				String newDesignator = getDesignator(refData.getDesignator());
				if (newDesignator != null){
					String oldRecData = getT33RecData(log.getRecordData());
					String newRecData = setT33RecData(oldRecData, newDesignator);
					if (isUpdate){
						log.setRecordData(newRecData);
						log.setStatusId(refData.getAction());
						log.setProcMessage(PDConstants.BLANK);
						log.bind();
					}
					writeAuditRec(log, outputfile);
				}
			}
			TransactionManager.getInstance().removeUpdated(refData);
		} else if (log.getRecordTypeId().equals("T34")){
				ReferralSubmit refData = getT34ValueFromXML(log.getRecordData());
				if (refData.getDesignator()!= null && !refData.getDesignator().equals(PDConstants.BLANK)){
					String newDesignator = getDesignator(refData.getDesignator());
					if (newDesignator != null){
						String oldRecData = getT34RecData(log.getRecordData());
						String newRecData = setT34RecData(oldRecData, newDesignator);
						
						if (isUpdate){
							log.setRecordData(newRecData);
							log.setStatusId(refData.getAction());
							log.setProcMessage(PDConstants.BLANK);
							log.bind();
						}
						writeAuditRec(log, outputfile);
					}				
				}
				TransactionManager.getInstance().removeUpdated(refData);
		}
	}
	Map aMap;
    private String getDesignator(String ctsCode) {
    	String designator = null;
    	CSProgramReferralType progRefType = null;
    	if (aMap == null){
    		aMap = new HashMap();
    	}
    	if (aMap.get(ctsCode) != null){
    		progRefType = (CSProgramReferralType) aMap.get(ctsCode);
    	} else {
    		Iterator iter = CSProgramReferralType.findAll("ctsCode", ctsCode);
    		if (iter.hasNext()){
    			List aList = CollectionUtil.iteratorToList(iter);
    			if (aList.size() == 1){
    				progRefType = (CSProgramReferralType) aList.get(0);
    				aMap.put(ctsCode, progRefType.getDesignator());
    				if (!progRefType.getDesignator().equals(ctsCode)){
    					designator = progRefType.getDesignator();
    				}
    			} else if (aList.size() == 0){
    				System.out.println("CTSCode not found in CSPROGREFTYPE: " + ctsCode);
    			} else if (aList.size() > 1){
    				System.out.println("Multiple records found in CSPROGREFTYPE: " + ctsCode);
    			}
    		}
    	}
		return designator;
	}
	private static String getT34RecData(String recData){
		
		Element root = new Element(LegacyUpdateConstants.XML_REFERRALDATA);
		Element child = new Element(LegacyUpdateConstants.XML_REFERRALEXIT);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,LegacyUpdateConstants.DELETE);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CTSCODE,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_CTSCODE));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_PROGBEGINDATE,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_PROGBEGINDATE));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_DESIGNATOR,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_DESIGNATOR));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_PROGENDDATE,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_PROGENDDATE));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_DISCHARGEREASON,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_DISCHARGEREASON));
		root.addContent(child);	
		String newRecData = null;
		try {
			newRecData = writeToXML(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newRecData;
	}
	private static String setT34RecData(String recordData, String newDesignator) {
		Element root = new Element(LegacyUpdateConstants.XML_REFERRALDATA);
		Element child = new Element(LegacyUpdateConstants.XML_REFERRALEXIT);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,LegacyUpdateConstants.DELETE);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CTSCODE,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_CTSCODE));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_PROGBEGINDATE,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_PROGBEGINDATE));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_DESIGNATOR,newDesignator);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_PROGENDDATE,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_PROGENDDATE));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_DISCHARGEREASON,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_DISCHARGEREASON));
		root.addContent(child);	
		String newRecData = null;
		try {
			newRecData = writeToXML(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newRecData;
	}
	private static String setT33RecData(String recordData, String newDesignator) {
		Element root = new Element(LegacyUpdateConstants.XML_REFERRALDATA);
		Element child = new Element(LegacyUpdateConstants.XML_REFERRALSUBMIT);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,LegacyUpdateConstants.DELETE);
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CTSCODE,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_CTSCODE));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_PROGBEGINDATE,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_PROGBEGINDATE));
		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_DESIGNATOR,newDesignator);
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

	private static String getT33RecData(String recordData) {
    		Element root = new Element(LegacyUpdateConstants.XML_REFERRALDATA);
    		Element child = new Element(LegacyUpdateConstants.XML_REFERRALSUBMIT);
    		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_ACTION,LegacyUpdateConstants.DELETE);
    		child.setAttribute(LegacyUpdateConstants.ATTRIBUTE_CTSCODE,getAttributeVal(child, LegacyUpdateConstants.ATTRIBUTE_CTSCODE));
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
	private static ReferralSubmit getT33ValueFromXML(String recordData)  {

		Element element = null;
		try {
			element = getXMLElement(recordData,
					LegacyUpdateConstants.XML_REFERRALSUBMIT);
		} catch (JDOMException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		ReferralSubmit referralData = new ReferralSubmit();
		if (element != null) {
			String action = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ACTION);
	        referralData.setAction(action);

			String designator = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_DESIGNATOR);
			referralData.setDesignator(designator);
			String ctsCode = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_CTSCODE);
			referralData.setCtsCode(ctsCode);
			
			String pfsCode = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_PFSCODE);
			referralData.setPfsCode( pfsCode );

		}
		return referralData;
	}
	private static ReferralSubmit getT34ValueFromXML(String recordData)  {

		Element element = null;
		try {
			element = getXMLElement(recordData,
					LegacyUpdateConstants.XML_REFERRALEXIT);
		} catch (JDOMException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		ReferralSubmit referralData = new ReferralSubmit();
		if (element != null) {
			String action = getAttributeVal(element, LegacyUpdateConstants.ATTRIBUTE_ACTION);
	        referralData.setAction(action);

			String designator = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_DESIGNATOR);
			referralData.setDesignator(designator);
			String ctsCode = getAttributeVal(element,
					LegacyUpdateConstants.ATTRIBUTE_CTSCODE);
			referralData.setCtsCode(ctsCode);

		}
		return referralData;
	}
	private static BufferedWriter createFile(String fileName, BufferedWriter bufferedWriter){
        StringBuffer sb = new StringBuffer(fileName);
        sb.append(".");
        sb.append(DateUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
        sb.append(".txt");
        File file = new File(sb.toString());
        bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }    
        return bufferedWriter;
    }
	public FixT33T34DSGAdHoc() {
		super();
		System.setProperty("mojo.config", "SRVPJ1P.xml");
		//System.setProperty("mojo.config", "multisource.xml");
		mojo.km.context.ContextManager.currentContext();
	}
    public void checkForExceptions() {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        CompositeResponse response = (CompositeResponse) dispatch.getReply();
        List responses = response.getResponses();
        ReturnException exceptionToBeThrown = null;
        for (Iterator iterator = responses.iterator(); iterator.hasNext();) {
              Object eventObj = iterator.next();
              if (ReturnException.class.isInstance(eventObj)) {
            	  ReturnException re = (ReturnException) eventObj;
            	  if (LegacyUpdateConstants.STATUS_PROCESSED.equals(re.getErrorExceptionType())){
            		  continue;
            	  } else {
            		  re.setErrorExceptionType(LegacyUpdateConstants.STATUS_PROCESSED);
            		  exceptionToBeThrown = re;
            	  }
              }
        }
        if (exceptionToBeThrown != null){
        	throw exceptionToBeThrown;
        }
    }
	private static Element getXMLElement(String recordData, String elementName) throws JDOMException {
        SAXBuilder saxReader = new SAXBuilder();
    	ByteArrayInputStream bais = new ByteArrayInputStream(recordData.trim().getBytes());
        InputStream is = new BufferedInputStream(bais);
        Document document = saxReader.build(is);
	    Element root = document.getRootElement();
	    if(root != null){
	        return root.getChild(elementName);	
	    }
	    return null;
	}
	private static String writeToXML(Element root) throws IOException {		
		Document document = new Document(root);		
		XMLOutputter outputter = new XMLOutputter();
		outputter.setOmitDeclaration(true);
		OutputStream out = new ByteArrayOutputStream();
		outputter.output(document, out);
		return out.toString();	}	
	
	private static String getAttributeVal(Element element, String attributeName){
		Attribute attr = element.getAttribute(attributeName);
		if(attr != null){
		    return attr.getValue();  
	    }else{
	    	return "";
	    }
	}

	private static void writeAuditRec(LegacyUpdateLog log, BufferedWriter bufferedWriter){
	     try {
	            StringBuffer sb = new StringBuffer();
	            sb.append("LEGACYUPDATLOG_ID=" + log.getOID());
	            sb.append(" SPN=" + log.getSpn());
	            sb.append(" RECTYPE=" + log.getRecordTypeId());
	            sb.append(" SOURCEOID=" + log.getSourceId());
	            sb.append(" CRIMINALCASE_ID=" + log.getCriminalCaseId());
	            sb.append(" CSTSSEQNO=" + log.getCstsSeqNo());
	            sb.append(" OPID=" + log.getOpId());
	            sb.append(" RECDATA=" + log.getRecordData());
	            bufferedWriter.write(sb.toString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

}
