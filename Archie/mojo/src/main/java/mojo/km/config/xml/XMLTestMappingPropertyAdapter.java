/*
 * Created on Feb 14, 2005
 *
 */
package mojo.km.config.xml;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;

import mojo.km.config.EntityMappingProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.TestDataClassificationProperties;
import mojo.km.utilities.XMLManager;

/**
 * @author Jim Fisher
 *
 * Class responsible for managing xml files related to manipulating property meta data for Persistence
 * data source mapping.
 */
public class XMLTestMappingPropertyAdapter
{
	private Map docs;
	private Map setWriteable;
	private Map fileEntries;
	static private XMLTestMappingPropertyAdapter instance = new XMLTestMappingPropertyAdapter();

	static public XMLTestMappingPropertyAdapter getInstance()
	{
		return instance;
	}

	public XMLTestMappingPropertyAdapter()
	{
		this.docs = new HashMap();
		this.setWriteable = new HashMap();
		this.fileEntries = new HashMap();
	}

	public void load(String childAttribute, String childElement, Element childFiles, MojoProperties properties)
	{
		Iterator i = childFiles.getChildren(childAttribute).iterator();
		URL locURL = null;
		String configFile = System.getProperty("mojo.config");
		System.out.println("Config used in XMLTestMappingPropertyAdapter: " + configFile);
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
					System.err.println(e.getMessage());
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
			Element fileElement = (Element) i.next();
			String fileName = fileElement.getAttributeValue("fileName");
			System.out.println("File Name in XMLTestMappingPropertyAdapter: " + fileName);
			Document fileDoc = null;
			try
			{
				fileDoc = XMLManager.readXMLResource(fileName);
			}
			catch (Exception xmlReadException)
			{
				System.out.println(
					"Error loading DirectoryPath : "
						+ directoryPath
						+ " FileName : "
						+ fileName
						+ " using readXMLResource >>>>>"
						+ xmlReadException.getMessage());
				try
				{
					fileDoc = XMLManager.readXMLFile(fileName);
				}
				catch (Exception ex)
				{
					System.out.println(
						"Error loading DirectoryPath : "
							+ directoryPath
							+ " FileName : "
							+ fileName
							+ " using readXMLFile >>>>>"
							+ ex.getMessage());
					continue;
				}
			}
			if (!docs.containsKey(fileName))
			{
				docs.put(fileName, fileDoc);
			}
			Iterator j = fileDoc.getRootElement().getChildren(childElement).iterator();
			while (j.hasNext())
			{
				// TODO Generalize this logic
				Element objElement = (Element) j.next();
				
				if ("TestDataDictionaryFile".equals(childAttribute))
				{
					TestDataClassificationProperties testData =
						TestMappingUtility.readTestDataClassificationProperties(objElement);
					properties.addTestDataClassification(testData);					
				}

			}
		}
	}

	public void saveAll(Document mainMappingDoc, MojoProperties properties)
	{
	}

	private boolean needToWrite(String packageName)
	{
		return false;
	}

	private void setWriteable(EntityMappingProperties eProp)
	{
	}
}
