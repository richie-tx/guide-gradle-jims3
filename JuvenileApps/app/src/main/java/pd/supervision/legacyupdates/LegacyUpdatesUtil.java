/*
 * Created on Aug 21, 2008
 *
  */
package pd.supervision.legacyupdates;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import naming.LegacyUpdateConstants;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import pd.contact.user.UserProfile;


/**
 * @author mchowdhury
 *
 */
public class LegacyUpdatesUtil{
    
	protected static final String DATE_FORMAT = "yyyyMMdd";
    protected static final int ZERO = 0;
    protected static final int OFFICER_NUMBER_LENGTH = 9;
    protected static final int CDI_LENGTH = 3;
    
	protected static LegacyUpdateLog setLegacyUpdateLogger(String spn, String recordTypeId, String statusId, String sourceId, String caseId, String logonId, LegacyUpdateLog uLog) {
		if(uLog == null){
			uLog = new LegacyUpdateLog();
		}
		uLog.setSpn(spn);
		uLog.setRecordTypeId(recordTypeId);
		uLog.setSourceId(sourceId);
		uLog.setStatusId(statusId);
		uLog.setCriminalCaseId(caseId);
		UserProfile up = UserProfile.find(logonId);
		if(up != null){
		    uLog.setOpId(up.getOperatorId());
		}
		return uLog;
	}

	protected Element getXMLElement(String recordData, String elementName) throws JDOMException {
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
	
	protected String getAttributeVal(Element element, String attributeName){
		Attribute attr = element.getAttribute(attributeName);
		if(attr != null){
		    return attr.getValue();  
	    }else{
	    	return "";
	    }
	}

	public String writeToXML(Element root) throws IOException {		
		Document document = new Document(root);		
		XMLOutputter outputter = new XMLOutputter();
		outputter.setOmitDeclaration(true);
		OutputStream out = new ByteArrayOutputStream();
		outputter.output(document, out);
		return out.toString();	}	
	
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
    public void clearExceptions() {
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        CompositeResponse response = (CompositeResponse) dispatch.getReply();
        List responses = response.getResponses();

        for (int i = 0; i < responses.size(); i++) {
              Object eventObj = responses.get(i);
              if (ReturnException.class.isInstance(eventObj)) {
            	  ReturnException re = (ReturnException) eventObj;
            	  if (LegacyUpdateConstants.STATUS_PROCESSED.equals(re.getErrorExceptionType())){
            		  continue;
            	  } else {
            		  re.setErrorExceptionType(LegacyUpdateConstants.STATUS_PROCESSED);
            	  }
              }
        }
 
    }


}
