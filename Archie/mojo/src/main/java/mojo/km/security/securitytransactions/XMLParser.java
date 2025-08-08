package mojo.km.security.securitytransactions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * @author asrvastava
 *
 */
public class XMLParser{
	private String fileName;
	private Document document;

	/**
	 * 
	 */
	public XMLParser(String fileName){
		this.fileName = fileName;
		this.document = getDocument();
	}
	
	/**
	 * @return Document
	 */
	private Document getDocument(){
		Document doc = null;
		try {
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(this.fileName);
			SAXBuilder builder = new SAXBuilder();
			doc = builder.build(inStream);
			inStream.close();
		}catch ( Exception e ) {
			System.out.println( "Unable to read xml file: " + e );
			e.printStackTrace();
		}        
		return doc;
	}
	
	/**
	 * Returns the Root for the xml file
	 * @return Element the root element for this document
	 */
	public Element getRootElement() {
		Element element = null;
		if(document != null){
			element = document.getRootElement();
		}
		return element;
		
	}
	
}
