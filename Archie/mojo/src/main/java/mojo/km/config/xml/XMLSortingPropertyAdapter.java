/*
 * Created on July 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.config.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mojo.km.config.EmptyMappingPropertyException;
import mojo.km.config.ISortingConstants;
import mojo.km.config.LoadMappingPropertyException;
import mojo.km.config.SortingEntity;
import mojo.km.utilities.XMLManager;

import org.jdom.Document;
import org.jdom.Element;

/**
 * @author mchowdhury
 *
 * Class responsible for reading mojo.km.sort.xml file and convert them to SortingEntity so that UI can use those data.
 */
 public class XMLSortingPropertyAdapter {
	Map map = new HashMap();
	static private XMLSortingPropertyAdapter instance = null;

	/**
	 * <p>name: getInstance description: Other classes call this method to get the XMLSortingPropertyAdapter
	 * @return XMLSortingPropertyAdapter
	 */
	public synchronized static XMLSortingPropertyAdapter getInstance() throws LoadMappingPropertyException, EmptyMappingPropertyException{
	   if (instance == null) {
		   instance = new XMLSortingPropertyAdapter();
	   }
	   return instance;
	}
	

	 /** <p>name: XMLSortingPropertyAdapter description: constructor */
	private XMLSortingPropertyAdapter() throws LoadMappingPropertyException, EmptyMappingPropertyException{
	   this.load();
	}

	/** 
	 * @description: load the xml file
	 */
	private void load() throws LoadMappingPropertyException, EmptyMappingPropertyException{
	   Document docs;
	   try{
		  docs = XMLManager.readXMLResource(ISortingConstants.XML_DATA_FILE);
	   }
	   catch (Exception e){
		  e.printStackTrace();
		  throw new LoadMappingPropertyException("Exception occurred while loading the mojo.km.sort.xml file");
	   }
	   Element element = docs.getRootElement().getChild(ISortingConstants.ELEMENT_NAME);
	   if (element != null){
		  Iterator iter = element.getChildren(ISortingConstants.JSP).iterator();
		  SortingEntity sEntity = null;
		  while (iter.hasNext()){
			 Element elem = (Element) iter.next();
			 sEntity = new SortingEntity();
			 sEntity.setId(elem.getAttributeValue(ISortingConstants.ID));
			 sEntity.setPrimarySortBy(elem.getAttributeValue(ISortingConstants.PRIMARY_SORT_BY));
			 sEntity.setSecondarySortBy(elem.getAttributeValue(ISortingConstants.SECONDARY_SORT_BY));
			 sEntity.setReturnPath(elem.getAttributeValue(ISortingConstants.RETURN_PATH));
			 sEntity.setResponseEvent(elem.getAttributeValue(ISortingConstants.RESPONSE_EVENT));
			 sEntity.setResponseEventId(elem.getAttributeValue(ISortingConstants.RESPONSE_EVENT_ID));
			 sEntity.setActionForm(elem.getAttributeValue(ISortingConstants.ACTION_FORM));
			 sEntity.setCollectionData(elem.getAttributeValue(ISortingConstants.COLLECTION_DATA));
			 sEntity.setActionFormIdentifier(elem.getAttributeValue(ISortingConstants.ACTION_FORM_IDENTIFIER));
			 sEntity.setSortingDataType(elem.getAttributeValue(ISortingConstants.SORTING_DATA_TYPE));
			 if(!this.map.containsKey(sEntity.getId())){
				this.map.put(sEntity.getId(),sEntity);	 
			 }
		  }
	   }
	}

	public Map getSortingProperties(){
	   return this.map;	
	}

	public SortingEntity getSortingEntity(String jspName){
	   return (SortingEntity) this.map.get(jspName);	
	}
 }
