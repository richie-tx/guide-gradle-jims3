/*
 * Created on Feb 14, 2005
 *
 */
package mojo.km.config.xml;

import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;

import mojo.km.config.AuditCheckProperties;
import mojo.km.config.AuditProperties;
import mojo.km.utilities.XMLManager;

/**
 * @author Jim Fisher
 *
 * Class responsible for managing xml files related to Audit configuration
 */
public class XMLAuditPropertyAdapter
{
    private String configFile;

    private AuditProperties auditProps;

    public XMLAuditPropertyAdapter(AuditProperties aProps, String aConfigFile)
    {
        this.auditProps = aProps;
        this.configFile = aConfigFile;
    }

    private Document readDocument() throws Exception
    {
        System.out.println(new java.io.File(".").getAbsolutePath());
        if (configFile.startsWith("file:/"))
        {
            System.out.println("loading: "+configFile.substring(6));
            return XMLManager.readXMLFile(configFile.substring(6));
        }
        else
        {
            System.out.println("loading: "+configFile);
            return XMLManager.readXMLFile(configFile);
        }
    }

    public void loadProperties() throws Exception
    {
        Document doc = this.readDocument();

        Element root = doc.getRootElement();

        Iterator children = root.getChildren().iterator();

        while (children.hasNext())
        {
            AuditCheckProperties checkProps = new AuditCheckProperties();

            // check module to use
            Element checkElement = (Element) children.next();
            String checkName = checkElement.getAttributeValue("name");
            checkProps.setCheckName(checkName);

            // child properties
            Iterator childProps = checkElement.getChildren().iterator();
            while (childProps.hasNext())
            {
                Element childProp = (Element) childProps.next();
                String propName = childProp.getAttributeValue("name");
                String propValue = childProp.getAttributeValue("value");
                checkProps.setProperty(propName, propValue);                               
            }
            
            this.auditProps.addCheck(checkProps);
        }
    }
}
