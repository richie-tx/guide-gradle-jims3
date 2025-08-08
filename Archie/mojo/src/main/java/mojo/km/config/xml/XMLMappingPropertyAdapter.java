/*
 * Created on Feb 14, 2005
 *
 */
package mojo.km.config.xml;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.Level;

import org.jdom.Document;
import org.jdom.Element;

import mojo.km.config.EntityMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.logging.LogUtil;
import mojo.km.utilities.XMLManager;

/**
 * @author eamundson
 * 
 * Class responsible for managing xml files related to manipulating property meta data for
 * Persistence data source mapping.
 */
public class XMLMappingPropertyAdapter
{
    Map docs = new HashMap();

    Map setWriteable = new HashMap();

    Map fileEntries = new HashMap();

    static private XMLMappingPropertyAdapter instance = new XMLMappingPropertyAdapter();

    static public XMLMappingPropertyAdapter getInstance()
    {
        return instance;
    }

    public void load(Element mappingFiles, MojoProperties properties)
    {
        Iterator i = mappingFiles.getChildren("EntityMappingFile").iterator();
        URL locURL = null;
        String configFile = System.getProperty("mojo.config");
        LogUtil.log(Level.INFO, "Config File in XMLMappingPropertyAdapter: " + configFile);
        String directoryPath = null;
        if (configFile != null)
        {
            if (configFile.startsWith("file:/"))
            {
                try
                {
                    locURL = new URL(configFile);
                }
                catch (Exception e)
                {
                    LogUtil.log(Level.FATAL, e.getMessage());
                }
            }
            else
            {
                locURL = Thread.currentThread().getContextClassLoader().getResource(configFile);
            }
            if (locURL != null)
            {
                directoryPath = locURL.getPath().replace('\\', '/');
                if (directoryPath.startsWith("/"))
                {
                    directoryPath = directoryPath.substring(1);
                }

                directoryPath = directoryPath.substring(0, directoryPath.lastIndexOf("/") + 1);
            }
        }
        else
        {
            directoryPath = "";
        }
        while (i.hasNext())
        {
            Element lFileElement = (Element) i.next();
            String packageName = lFileElement.getAttributeValue("packageName");
            LogUtil.log(Level.INFO, "Package Name in XMLMappingPropertyAdapter: " + packageName);
            fileEntries.put(packageName, packageName);
            String fileName = lFileElement.getAttributeValue("fileName");
            LogUtil.log(Level.INFO, "File Name in XMLMappingPropertyAdapter: " + fileName);
            Document fileDoc = null;
            try
            {
                fileDoc = XMLManager.readXMLResource(fileName);
            }
            catch (Exception xmlReadException)
            {
                String msg = "Error loading DirectoryPath : " + directoryPath + " FileName : " + fileName
                        + " using readXMLResource >>>>>" + xmlReadException.getMessage();
                LogUtil.log(Level.FATAL, msg);
                try
                {
                    fileDoc = XMLManager.readXMLFile(fileName);
                }
                catch (Exception ex)
                {
                    msg = "Error loading DirectoryPath : " + directoryPath + " FileName : " + fileName
                            + " using readXMLFile >>>>>" + ex.getMessage();
                    LogUtil.log(Level.FATAL, msg);
                    continue;
                }
            }
            if (!docs.containsKey(packageName))
            {
                docs.put(packageName, fileDoc);
            }
            Element lElement = fileDoc.getRootElement().getChild("EntityMappings");
            if (lElement != null)
            {
                Iterator j = lElement.getChildren("EntityMapping").iterator();
                while (j.hasNext())
                {
                    Element lEntityElement = (Element) j.next();
                    EntityMappingProperties lEntity = MappingUtility.readEntityMapping(lEntityElement);
                    lEntity.unmarkModified();
                    properties.addEntityMapping(lEntity);
                }
            }
        }

    }

    public void saveAll(Document mainMappingDoc, MojoProperties properties)
    {
        setWriteable.clear();
        Iterator entityMaps = properties.getEntityMaps();
        while (entityMaps.hasNext())
        {
            setWriteable((EntityMappingProperties) entityMaps.next());
        }
        String configFile = System.getProperty("mojo.config");
        if (configFile == null)
        {
            return;
        }
        URL locURL = null;
        if (configFile.startsWith("file:/"))
        {
            try
            {
                locURL = new URL(configFile);
            }
            catch (Exception e)
            {
                LogUtil.log(Level.FATAL, e.getMessage());
            }
        }
        else
        {
            locURL = Thread.currentThread().getContextClassLoader().getResource(configFile);
        }
        Map entityEntries = new HashMap();
        String directoryPath = null;
        if (locURL != null)
        {
            directoryPath = locURL.getPath().replace('\\', '/');
            if (directoryPath.startsWith("/"))
            {
                directoryPath = directoryPath.substring(1);
            }

            directoryPath = directoryPath.substring(0, directoryPath.lastIndexOf("/") + 1);
        }
        Element lFilesElement = new Element("EntityMappingFiles");
        Element lElement = mainMappingDoc.getRootElement().addContent(lFilesElement);
        Iterator fEntries = fileEntries.values().iterator();
        while (fEntries.hasNext())
        {
            String packageName = (String) fEntries.next();
            Element lFileElement = new Element("EntityMappingFile");
            lFileElement.setAttribute("packageName", packageName);
            lFileElement.setAttribute("fileName", packageName + "Mapping.xml");
            lFilesElement.addContent(lFileElement);
        }
        entityMaps = properties.getEntityMaps();
        while (entityMaps.hasNext())
        {
            EntityMappingProperties eProp = (EntityMappingProperties) entityMaps.next();
            if (eProp.getEntity().lastIndexOf(".") < 0)
            {
                continue;
            }
            String packageName = MappingUtility.getPackage(eProp.getEntity());
            if (!fileEntries.containsKey(packageName))
            {
                Element lFileElement = new Element("EntityMappingFile");
                lFileElement.setAttribute("packageName", packageName);
                lFileElement.setAttribute("fileName", packageName + "Mapping.xml");
                lFilesElement.addContent(lFileElement);
                fileEntries.put(packageName, lFileElement);
            }
            if (needToWrite(packageName))
            {
                Element lEntityMappings = null;
                if (!entityEntries.containsKey(packageName))
                {
                    Element lRootElement = new Element("Mapping");
                    Document resourceDoc = new Document(lRootElement);
                    lEntityMappings = new Element("EntityMappings");
                    lRootElement.addContent(lEntityMappings);
                    entityEntries.put(packageName, resourceDoc);
                }
                else
                {
                    Document resourceDoc = (Document) entityEntries.get(packageName);
                    lEntityMappings = resourceDoc.getRootElement().getChild("EntityMappings");
                }
                MappingUtility.writeEntityMap(eProp, lEntityMappings);
            }
        }
        Iterator j = entityEntries.keySet().iterator();
        while (j.hasNext())
        {
            String packageName = (String) j.next();
            try
            {
                XMLManager.writeXMLToFile((Document) entityEntries.get(packageName), directoryPath + packageName
                        + "Mapping.xml");
            }
            catch (Exception e)
            {
                continue;
            }
        }
    }

    private boolean needToWrite(String packageName)
    {
        if (setWriteable.containsKey(packageName))
        {
            return true;
        }
        return false;
    }

    private void setWriteable(EntityMappingProperties eProp)
    {
        if (eProp.isModified())
        {
            if (eProp.getEntity().lastIndexOf(".") > -1)
            {
                String packageName = MappingUtility.getPackage(eProp.getEntity());
                setWriteable.put(packageName, packageName);
            }
        }
    }
}
