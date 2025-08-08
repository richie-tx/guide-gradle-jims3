package mojo.km.config.xml;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import mojo.km.config.ExceptionProperties;
import mojo.km.config.IPropertyAdapter;
import mojo.km.utilities.XMLManager;

import org.jdom.*;

/** @modelguid {AB5C036E-854C-4DD4-A976-DC246C9B20D0} */
public class XMLExceptionPropertyAdapter implements IPropertyAdapter {
	/** @modelguid {65D66D3B-25DA-4649-8855-85DF1BBC4594} */
	Document doc;
	
	/** @modelguid {61896770-F9FF-42EB-9388-C1C247D0379A} */
	private Document readDocument() throws Exception {
		return XMLManager.readXMLResource("ExceptionData.xml");
	}
	
	/** @modelguid {0D8433EE-D98F-4629-A642-6464ED78E554} */
	public void loadProperties() {
		try {
			doc = readDocument();
		} catch (Exception e) {
			System.err.println("Could not load ExceptionData.xml");
			e.printStackTrace();
			return;
		}
		readExceptionProperties();
    }

	/** @modelguid {583445B2-608B-419E-B228-CC1B9BE281D3} */
	private void readExceptionProperties() {
		Element lElement = doc.getRootElement().getChild("Exceptions");
		for (Iterator i = lElement.getChildren("Locale").iterator(); i.hasNext(); ) {
			Element lLocationElem = (Element)i.next();
			readLocationProperties(lLocationElem);
		}
	}
	
	/** @modelguid {0A39DAA0-F805-4719-9699-8A2E66E97305} */
	private void readLocationProperties(Element aLocationElement) {
		String lLocationName = aLocationElement.getAttributeValue("name");
		for (Iterator i = aLocationElement.getChildren("Exception").iterator(); i.hasNext(); ) {
			Element lExceptionElem = (Element)i.next();
			readExceptionDataProperties(lLocationName, lExceptionElem);
		}
	}
	
	/** @modelguid {B5DC8598-6306-4D5A-90DA-0811B4B764D5} */
	private void readExceptionDataProperties(String aLocationName, Element anExceptionElement) {
		String lExceptionName = anExceptionElement.getAttributeValue("name");
		Element lElem = null;
		String lReason = null;
		String lDiagnosis = null;
		String lCode = null;
		String lSolution = null;
		lElem = anExceptionElement.getChild("Reason");
		if (lElem != null) lReason = lElem.getAttributeValue("value");
		lElem = anExceptionElement.getChild("Diagnosis");
		if (lElem != null) lDiagnosis = lElem.getAttributeValue("value");
		lElem = anExceptionElement.getChild("Code");
		if (lElem != null) lCode = lElem.getAttributeValue("value");
		lElem = anExceptionElement.getChild("Solution");
		if (lElem != null) lSolution = lElem.getAttributeValue("value");
		List lCallbacks = new Vector();
		Element lCallbackElem = anExceptionElement.getChild("Callbacks");
		if (lCallbackElem != null) { 
			for (Iterator i = lCallbackElem.getChildren("Callback").iterator(); i.hasNext(); ) {
				lCallbacks.add(((Element)i.next()).getAttributeValue("className")); 
			}
		}
		ExceptionProperties.getInstance().setException(aLocationName, lExceptionName, lReason, lCode, lDiagnosis, lSolution, lCallbacks);
	}
    
	/** @modelguid {F215365A-F622-418C-97B2-6218C1C13FB5} */
	public void saveProperties() {
		Element lRootElement = new Element("ExceptionData");
		doc = new Document(lRootElement);
		writeExceptionProperties();
		try {
			writeDocument(doc);
		} catch (Exception e) {
			System.out.println("Could not write to ExceptionData.xml");
			e.printStackTrace();
		}
	}
	
	/** @modelguid {759886C9-B467-4505-AE71-FF52D7696D69} */
	private void writeExceptionProperties() {
		Element lExceptions = new Element("Exceptions");
		doc.getRootElement().addContent(lExceptions);
		for (Iterator i = ExceptionProperties.getInstance().getDefinedLocales(); i.hasNext(); ) {
			String lLocaleName = (String)i.next();
			writeLocaleProperties(lLocaleName, lExceptions);
		}
	}
	
	/** @modelguid {2B747C04-E6E7-4002-8D45-A0F9706048B1} */
	private void writeLocaleProperties(String aLocaleName, Element anExceptionsElement) {
		Element lLocaleElem = new Element("Locale");
		anExceptionsElement.addContent(lLocaleElem);
		lLocaleElem.setAttribute("name", aLocaleName);
		for (Iterator i = ExceptionProperties.getInstance().getLocaleExceptions(aLocaleName); i.hasNext(); ) {
			String lExceptionName = (String)i.next();
			writeExceptionDataProperties(aLocaleName, lExceptionName, lLocaleElem);
		}
	}
	
	/** @modelguid {E1BDD156-1ACA-4BCA-992F-587FF29D3246} */
	private void writeExceptionDataProperties(String aLocaleName, String anExceptionName, Element aLocaleElement) {
		Element lExceptionElem = new Element("Exception");
		aLocaleElement.addContent(lExceptionElem);
		lExceptionElem.setAttribute("name", anExceptionName);
		ExceptionProperties.ExceptionData lData = ExceptionProperties.getInstance().getExceptionData(aLocaleName, anExceptionName);
		Element lReasonElem = new Element("Reason");
		lReasonElem.setAttribute("value", lData.getReason());
		lExceptionElem.addContent(lReasonElem);
		Element lCodeElem = new Element("Code");
		lCodeElem.setAttribute("value", lData.getCode());
		lExceptionElem.addContent(lCodeElem);
		Element lDiagnosisElem = new Element("Diagnosis");
		lDiagnosisElem.setAttribute("value", lData.getDiagnosis());
		lExceptionElem.addContent(lDiagnosisElem);
		Element lSolutionElem = new Element("Solution");
		lSolutionElem.setAttribute("value", lData.getSolution());
		lExceptionElem.addContent(lSolutionElem);
		Iterator lCallbacks = lData.getCallbacks();
		if (lCallbacks.hasNext()) {
			Element lCallbacksElem = new Element("Callbacks");
			lExceptionElem.addContent(lCallbacksElem);
			while (lCallbacks.hasNext()) {
				String lCallbackName = (String)lCallbacks.next();
				Element lCallback = new Element("Callback");
				lCallback.setAttribute("className", lCallbackName);
				lCallbacksElem.addContent(lCallback);
			}
		}
	}
	
	/** @modelguid {6D3C76F2-69D6-4639-8F3E-93644606A8B3} */
	private void writeDocument(Document aDocument) throws Exception {
		XMLManager.writeXMLToResource(aDocument, "ExceptionData.xml");
    }

}
