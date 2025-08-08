//Source file: C:\\views\\dev\\framework\\mojo-jims2\\mojo.java\\src\\mojo\\km\\config\\CacheProperties.java

package mojo.km.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mojo.km.naming.CacheLevel;

import org.jdom.Element;


/**
 * Responsible for handling properties related to 
 * caching of entities.
 */
public class CacheProperties extends GenericProperties {
	public static String CLASSNAME = "className";
	public static String REGION = "region";
	public static String IS_REMOTE = "isRemote";
	public static String LEVEL = "level";
	public static String OID_PROPERTY = "oidProperty";
   
	private static Map cachePropsMap = new HashMap();
   
   /**
    * @roseuid 4159A5100242
    */
	public CacheProperties() { }
   
	public static CacheProperties getInstance() {
		return MojoProperties.getInstance().getCacheProperties();
	}
   
	/**
     * @param className
	 */
	public void addClass(String className, ClassCacheProps cacheProps) {
		cachePropsMap.put(className, cacheProps);
	}

	/**
	 * @return String
	 */
	public String getRegion() {
		return getProperty(REGION);
	}

	/**
	 * @param region
	 */
	public void setRegion(String region) {
		setProperty(REGION, region);
	}

	/**
	 * @return CacheLevel
	 */
	public CacheLevel getCacheLevel() {
		String level = getProperty(LEVEL);
		CacheLevel cacheLevel = CacheLevel.NON_CACHABLE;
		if (level != null) {
			cacheLevel = CacheLevel.getCacheLevel(level);
		}
		return cacheLevel;
		
	}

	/**
	 * @param className
	 */
	public void setCacheLevel(CacheLevel cacheLevel) {
	   setProperty(LEVEL, cacheLevel.getName());
	}

	/**
	 * @param className
	 * @return CacheLevel
	 * @roseuid 4159A4510213
	 */
	public CacheLevel getCacheLevel(String className) {
		ClassCacheProps props = (ClassCacheProps)cachePropsMap.get(className);
		CacheLevel lLevel = null;
		if (props != null) {
			lLevel = props.getLevel();
		}
		return lLevel;
	}

	/**
	 * @param className
	 * @return String
	 * @roseuid 4159A4510213
	 */
	public String getRegion(String className) {
		ClassCacheProps props = (ClassCacheProps)cachePropsMap.get(className);
		String lRegion = null;
		if (props != null) {
			lRegion = props.getRegion();
		}
		return lRegion;
	}

	/**
	 * @param className
	 * @return String
	 * @roseuid 4159A4510213
	 */
	public boolean getIsRemote(String className) {
		ClassCacheProps props = (ClassCacheProps)cachePropsMap.get(className);
		if (props == null) {
			return false;
		}
		return props.getIsRemote();
	}
	
	/**
	 * @param className
	 * @return String
	 * @roseuid 4159A4510213
	 */
	public void setIsRemote(String className, boolean setVal) {
		ClassCacheProps props = (ClassCacheProps)cachePropsMap.get(className);
		if (props == null) {
			props.setIsRemote(setVal);
		}
		
	}
	/**
	 * @param className
	 * @return String
	 * @roseuid 4159A4510213
	 */
	public String getOidProperty(String className) {
		ClassCacheProps props = (ClassCacheProps)cachePropsMap.get(className);
		String OidPropertyName = null;
		if (props != null) {
			OidPropertyName = props.getOidPropertyName();
		}
		return OidPropertyName;
	}

	public void loadProperties(Element element) {
		if (element != null) {
			// load class properties for caching
			Element lClassesElement = element.getChild("Classes");
			for (Iterator i = lClassesElement.getChildren("Class").iterator(); i.hasNext();) {
				Element lClassElement = (Element) i.next();
				if (lClassElement != null) {
					String className = lClassElement.getAttributeValue(CLASSNAME);
					String region =	lClassElement.getAttributeValue(REGION);
					String cacheLevel = lClassElement.getAttributeValue(LEVEL);
					String oidPropertyName = lClassElement.getAttributeValue(OID_PROPERTY);
					String remoteable = lClassElement.getAttributeValue(IS_REMOTE);
					boolean isRemote = false;
					if (remoteable != null && remoteable.equals("true"))
					{
						isRemote = true;
					}
					addClass(className, new ClassCacheProps(region, cacheLevel, oidPropertyName, isRemote));
				}
			}
		}
	}
	
	class ClassCacheProps {
		ClassCacheProps(String region, String cacheLevel, String oidPropertyName, boolean isRemote) {
			this.region = region;
			this.oidPropertyName = oidPropertyName;
			this.level = CacheLevel.getCacheLevel(cacheLevel);
			this.isRemote = isRemote;
		}
		
		String getRegion() {
			return region;
		}
		
		CacheLevel getLevel() {
			return level;
		}
		
		String getOidPropertyName() {
			return oidPropertyName;
		}
		
		boolean getIsRemote()
		{
			return isRemote;
		}
		
		void setIsRemote(boolean setVal)
		{
			isRemote = setVal;
		} 
		
		private String region;
		private CacheLevel level;
		private String oidPropertyName;
		private boolean isRemote = false;
	}
}
