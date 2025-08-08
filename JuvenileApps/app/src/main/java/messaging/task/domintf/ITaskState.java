/*
 * Created on Mar 16, 2006
 *
 */
package messaging.task.domintf;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * @author Jim Fisher
 *
 */
public interface ITaskState
{
	final String DATE_FMT = "yyyyMMddHHmmssz";

	final String STRING_CLASS = String.class.getName();
	final String INTEGER_CLASS = Integer.class.getName();
	final String DATE_CLASS = Date.class.getName();

	Collection getItems();
	Object get(Object key);
	Set getKeys();
	HashMap getMap();
	/**
	 * @param key
	 * @param stringValue
	 */
	void addItem(String key, String stringValue);
	/**
	 * @param key
	 * @param obj
	 */
	void addItem(String key, Integer obj);
	/**
	 * @param key
	 * @param obj
	 */
	void addItem(String key, Date obj);
}
