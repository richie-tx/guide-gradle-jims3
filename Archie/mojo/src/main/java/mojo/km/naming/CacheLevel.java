package mojo.km.naming;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author jmcnabb
 *
 * Represents types of cachability for persistent objects.
 */
public class CacheLevel
{
	public static CacheLevel STATIC = new CacheLevel("static");
	public static CacheLevel NON_CACHABLE = new CacheLevel("none");
	public static CacheLevel MAPPING = new CacheLevel("mapping");
	
	private static Map types;
	
	static {
		types = new HashMap();
		types.put("static", STATIC);
		types.put("none", NON_CACHABLE);
		types.put("mapping", MAPPING);
	}
	
	public CacheLevel(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public static CacheLevel getCacheLevel(String name) {
		return (CacheLevel)types.get(name);
	}
	
	public static Iterator getCacheLevels() {
		return types.values().iterator();
	}
	
	private String name;
}
