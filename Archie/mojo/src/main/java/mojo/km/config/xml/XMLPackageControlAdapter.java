package mojo.km.config.xml;

import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;

import mojo.km.config.PackageControlProperties;
import mojo.km.config.PackageGuardProperties;
import mojo.km.utilities.XMLManager;

/**
 * Responsible for loading the contents of the package control configuration file.
 * 
 * @author Jim Fisher
 */
public class XMLPackageControlAdapter
{
    private PackageControlProperties props;

    private String configFile;

    public XMLPackageControlAdapter(PackageControlProperties aProps, String configFile)
    {
        this.props = aProps;
        this.configFile = configFile;
    }

    private Document readDocument() throws Exception
    {
        System.out.println(new java.io.File(".").getAbsolutePath());
        if (configFile.startsWith("file:/"))
        {            
            return XMLManager.readXMLFile(configFile.substring(6));
        }
        else
        {
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
            PackageGuardProperties gProps = new PackageGuardProperties();

            // check module to use
            Element packageElement = (Element) children.next();
            String packageName = packageElement.getAttributeValue("name");
            String strict = packageElement.getAttributeValue("strict");
            gProps.setPackage(packageName);
            gProps.setStrict(Boolean.valueOf(strict).booleanValue());

            // child properties
            Iterator childProps = packageElement.getChildren().iterator();
            while (childProps.hasNext())
            {
                Element guardProp = (Element) childProps.next();
                String guardType = guardProp.getName();
                String guardPackageName = guardProp.getAttributeValue(PackageGuardProperties.NAME_ATTR);

                if (guardType.equalsIgnoreCase(PackageGuardProperties.ALLOW))
                {
                    gProps.addAllowGuard(guardPackageName);
                }
                else if (guardType.equalsIgnoreCase(PackageGuardProperties.DISALLOW))
                {
                    gProps.addDisallowGuard(guardPackageName);
                }
            }

            this.props.addPackageGuard(gProps);
        }
    }
}
