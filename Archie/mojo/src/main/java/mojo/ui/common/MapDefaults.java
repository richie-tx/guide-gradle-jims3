package mojo.ui.common;

import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.awt.Container;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import java.util.Set;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.io.Serializable;

/**
 * Defaults stores the default values of an element and provides methods to store and retrieve these values.
 * @author Kurt Jacobs
 * @since 4.10.2000
 * @version 1.0
 * @modelguid {ECC2E432-AE27-4C18-ADB4-D85A6A086B34}
 */
public class MapDefaults implements Map, Serializable {
    // Stores defaults for an object
	/** @modelguid {BB4106DC-5F73-4721-AD74-D46F171B0A36} */
    HashMap dataStorage = new HashMap();

    /** Default Constructor 
     * @modelguid {780F348A-338B-4C0E-9A1B-8B21E54ACC61}
     */
    public MapDefaults() {
    }

    /**
     * Stores a container object.
     * @param key Key for object being stored
     * @param container Container object to be stored
     * @return Previous Container object stored using the same key value or   null if no previous Container object exists
     * @modelguid {869A18D9-61BA-43B1-BE2C-C631C82CC9A1}
     */
    public Container putContainer(String key, Container container) {
        Object object = dataStorage.get(key);
        dataStorage.put(key, container);
        return (object instanceof Container) ? (Container)object : null;
    }

    /**
     * Stores the dimension of a container.
     * @param key Key for object being stored
     * @param containerSize Size of container object being stored
     * @return Previous Dimension object stored using the same key value or   null if no previous Dimension object exist.
     * @modelguid {1B7C59D8-5C16-4D45-B7F9-CB5531D6AE42}
     */
    public Dimension putContainerSize(String key, Dimension containerSize) {
        Object object = dataStorage.get(key);
        dataStorage.put(key, containerSize);
        return (object instanceof Dimension) ? (Dimension)object : null;
    }

    /**
     * Takes a Point reference object that defines an element at an absolute
     * screen coordinate and stores the position in a screen resolution independent manner. (relative position)
     * @param key Key for object being stored
     * @param position Screen position of element to be saved
     * @return Previous Point object stored using same key value or null if no previous Point object exists
     * @modelguid {B703409D-7E01-4283-BB3D-31B63940E7EB}
     */
    public Point putScreenPosition(String key, Point position) {
        Object object = dataStorage.get(key);
        dataStorage.put(key, position);
        return (object instanceof Point) ? (Point)object : null;
    }

    /**
     * Stores the Color of an object
     * @param key Key for the object being stored
     * @param color Color object to store
     * @return Previous Color object stored using the same key or null if no  previous Color object with that key exists
     * @modelguid {47286AEF-EEF4-49C9-8124-B3D676C26FBE}
     */
    public Color putColor(String key, Color color) {
        Object object = dataStorage.get(key);
        dataStorage.put(key, color);
        return (object instanceof Color) ? (Color)object : null;
    }

    /**
     * Stores the Font used for a text object
     * @param key Key for the object being stored
     * @param font Font object to store
     * @return Previous Font object stored using the same key or null if no   previous Font object with that key exists
     * @modelguid {3B943259-40DB-42C2-B331-A2DDEE7E0E28}
     */
    public Font putFont(String key, Font font) {
        Object object = dataStorage.get(key);
        dataStorage.put(key, font);
        return (object instanceof Font) ? (Font)object : null;
    }

    /**
     * Stores a Hashtable used for storing a list of objects.
     * @param key Key for the object being stored
     * @param hashTable Hashtable object to store
     * @return Previous Hashtable object stored using the same key or null if no   previous Hashtable
     * object with that key exists
     * @modelguid {0FD2135C-63EF-4233-ADE0-CA2A1DF27476}
     */
    public Hashtable putHashtable(String key, Hashtable hashTable) {
        Object object = dataStorage.get(key);
        dataStorage.put(key, hashTable);
        return (object instanceof Hashtable) ? (Hashtable)object : null;
    }

    /**
     * Stores a UTDefaults used for storing objects.
     * @param key Key for the object being stored
     * @param list Defaults object to store
     * @return Previous Defaults object stored using the same key or null if no previous Defaults object with that key exists
     * @modelguid {9E195EC5-2D6C-43B5-ACDC-A39BF2CA5D2F}
     */
    public MapDefaults putDefaults(String key, MapDefaults utDefaults) {
        Object object = dataStorage.get(key);
        dataStorage.put(key, utDefaults);
        return (object instanceof MapDefaults) ? (MapDefaults)object : null;
    }

    /**
     * Stores a Vector used for storing a list of objects.
     * @param key Key for the object being stored
     * @param list Hashtable object to store
     * @return Previous Hashtable object stored using the same key or null if no   previous list object with that key exists
     * @modelguid {72EE5A04-EEE2-44DD-8AF8-09D0A7117B35}
     */
    public Vector putVector(String key, Vector vector) {
        Object object = dataStorage.get(key);
        dataStorage.put(key, vector);
        return (object instanceof Vector) ? (Vector)object : null;
    }

    /**
     * Stores a String used for storing a list of objects.
     * @param key Key for the object being stored
     * @param string String object to store
     * @return Previous String object stored using the same key or null if no   previous list object with that key exists
     * @modelguid {6D8ED641-AE30-44A5-A68B-7ED4BDD3BEB9}
     */
    public String putString(String key, String string) {
        Object object = dataStorage.get(key);
        dataStorage.put(key, string);
        return (object instanceof String) ? (String)object : null;
    }

    /**
     * Wraps an integer primitive as a convenience to the class user so the class
     * user doesn't have to create an Integer object to store a primitive value.
     * @param key Key for object being stored
     * @param intValue Integer primitive
     * @return Previous Integer object used to wrap primitive stored using   same key or null if no
     * previous object was stored.
     * @modelguid {6E60D2B2-DA81-4290-8BF1-77840AD01C6D}
     */
    public Integer putInt(String key, int intValue) {
        Object object = dataStorage.get(key);
        dataStorage.put(key, new Integer(intValue));
        return (object instanceof Integer) ? (Integer)object : null;
    }

    /**
     * Wraps a long primitive as a convenience to the class user so the class
     * user doesn't have to create a Long object to store a primitive value.
     * @param key Key for object being stored
     * @param longValue Long primitive
     * @return Previous Long object used to wrap primitive stored using   same key or null if no previous object was stored.
     * @modelguid {97914A36-0511-4C87-B498-26A59318AF76}
     */
    public Long putLong(String key, long longValue) {
        Object object = dataStorage.get(key);
        dataStorage.put(key, new Long(longValue));
        return (object instanceof Long) ? (Long)object : null;
    }

    /**
     * Wraps a double primitive as a convenience to the class user so the class
     * user doesn't have to create a Double object to store a primitive value.
     * @param key Key for object being stored
     * @param doubleValue Double primitive
     * @return Previous Double object used to wrap primitive stored using   same key or null if no previous object was stored.
     * @modelguid {EFF461F3-4E73-47DB-B15E-7008C37B2094}
     */
    public Double putDouble(String key, double doubleValue) {
        Object object = dataStorage.get(key);
        dataStorage.put(key, new Double(doubleValue));
        return (object instanceof Double) ? (Double)object : null;
    }

    /**
     * Wraps a float primitive as a convenience to the class user so the class
     * user doesn't have to create a Float object to store a primitive value.
     * @param key Key for object being stored
     * @param floatValue Float primitive
     * @return Previous Float object used to wrap primitive stored using   same key or null if no previous object was stored.
     * @modelguid {79494232-B5C3-4EC3-9EDA-01F19F2D7771}
     */
    public Float putFloat(String key, float floatValue) {
        Object object = dataStorage.get(key);
        dataStorage.put(key, new Float(floatValue));
        return (object instanceof Float) ? (Float)object : null;
    }

    /**
     * Wraps a boolean primitive as a convenience to the class user so the class
     * user doesn't have to create a Boolean object to store a primitive value.
     * @param key Key for object being stored
     * @param booleanValue Boolean primitive
     * @return Previous Boolean object used to wrap primitive stored using   same key or null if no
     * previous object was stored.
     * @modelguid {8260F239-1D4B-4F44-BCA0-0AE1D4FE1103}
     */
    public Boolean putBoolean(String key, boolean booleanValue) {
        Object object = dataStorage.get(key);
        dataStorage.put(key, new Boolean(booleanValue));
        return (object instanceof Boolean) ? (Boolean)object : null;
    }
    // ----------------------- gets -----------------------

    /**
     * Get a Container object from storage.
     * @param key Key for object being retrieved
     * @return Container object if the object is found and is an instance of Container.  Null if either the object is not
     * a Container or not found
     * @modelguid {2689E245-4E35-4314-B3B0-C97862E25793}
     */
    public Container getContainer(String key) {
        Object container = dataStorage.get(key);
        return (container instanceof Container) ? (Container)container : null;
    }

    /**
     * Get a Dimension object from storage.
     * @param key Key for object being retrieved
     * @return Dimension object if the object is found and is an instance of Dimension.  Null if either the object is not a
     * Dimension object or or is not found
     * @modelguid {9DCF32BD-ECEA-4115-9657-375D106903E2}
     */
    public Dimension getContainerSize(String key) {
        Object dimension = dataStorage.get(key);
        return (dimension instanceof Dimension) ? (Dimension)dimension : null;
    }

    /**
     * Get a Point object from storage that defines where an element is to to be positioned.
     * @param key Key for object being retrieved
     * @return Point object of null if no Point object is not found
     * @modelguid {886E18E2-323B-4592-BB7A-6EB15D93F1D8}
     */
    public Point getScreenPosition(String key) {
        Object value = dataStorage.get(key);
        return (value instanceof Point) ? (Point)value : null;
    }

    /**
     * Get a Color object from storage.
     * @param key Key for object being retrieved
     * @return Color object if the object is found and is an instance of Color.  Null if either the object is
     * not a Color or not found
     * @modelguid {6062BCBC-1DAC-4343-A8FB-770CA280FAE3}
     */
    public Color getColor(String key) {
        Object value = dataStorage.get(key);
        return (value instanceof Color) ? (Color)value : null;
    }

    /**
     * Get a Font object from storage.
     * @param key Key for object being retrieved
     * @return Font object if the object is found and is an instance of Font.  Null if either the object is
     * not a Font or not found
     * @modelguid {1221F9B6-A516-4110-8DEF-901D9A708F28}
     */
    public Font getFont(String key) {
        Object value = dataStorage.get(key);
        return (value instanceof Font) ? (Font)value : null;
    }

    /**
     * Get a Hashtable object from storage.
     * @param key Key for object being retrieved
     * @return Hashtable object if the object is found and is an instance of Hashtable.  Null if either the object is not
     * a Hashtable or not found
     * @modelguid {A0F91160-485E-48E3-A9B1-1D807381FBBD}
     */
    public Hashtable getHashtable(String key) {
        Object value = dataStorage.get(key);
        return (value instanceof Hashtable) ? (Hashtable)value : null;
    }

    /**
     * Get a Defaults object from storage
     * @param key Key for the object being retrieved
     * @return UTDefaults object if the object is found and is an instance of Defautlts.  Null if either the object is not
     * a Defaults or not found
     * @modelguid {797DA0A0-C99A-450D-B136-B7E1F17B0842}
     */
    public MapDefaults getDefaults(String key) {
        Object value = dataStorage.get(key);
        return (value instanceof MapDefaults) ? (MapDefaults)value : null;
    }

    /**
     * Get a Vector object from storage.
     * @param key Key for object being retrieved
     * @return Vector object if the object is found and is ainstance of
     * Vector.  Null if either the object is not a Vector or not found
     * @modelguid {84150B36-D134-469E-9F75-A29386E15326}
     */
    public Vector getVector(String key) {
        Object value = dataStorage.get(key);
        return (value instanceof Vector) ? (Vector)value : null;
    }

    /**
     * Get a String object from storage.
     * @param key Key for object being retrieved
     * @return String object if the object is found and is an instance of String.  Null if either the object is
     * not a String or not found
     * @modelguid {7D86CF13-5D97-453E-AE37-FD1398FCB1DF}
     */
    public String getString(String key) {
        Object value = dataStorage.get(key);
        return (value instanceof String) ? (String)value : null;
    }

    /**
     * Get an integer primitive from storage
     * @param key Key for object being retrieved
     * @return An integer primitive if the wrapper was an instance of Integer.
     * @throws RuntimeException exception if wrapper object was not found or was not an instance of Integer
     * @modelguid {D82CED2C-2E2C-4395-BFD9-6D85800C9E65}
     */
    public int getInt(String key) {
        Object intObject = dataStorage.get(key);
        if ((intObject instanceof Integer) == false) {
            throw new RuntimeException("\ngetInt-Key \"" + key + "\"does not reference an int or value not found");
        }
        return ((Integer)intObject).intValue();
    }

    /**
     * Get an long primitive from storage
     * @param key Key for object being retrieved
     * @return A long primitive if the wrapper was an instance of Long.
     * @throws RuntimeException exception if wrapper object was not found or was not a an instance of Long
     * @modelguid {1C796030-1ED7-4CAB-9CD5-8F490FCAFF55}
     */
    public long getLong(String key) {
        Object longObject = dataStorage.get(key);
        if ((longObject instanceof Long) == false) {
            throw new RuntimeException("\ngetLong-Key \"" + key + "\" does not reference a long or value not found");
        }
        return ((Long)longObject).longValue();
    }

    /**
     * Get an double primitive from storage
     * @param key Key for object being retrieved
     * @return A double primitive if the wrapper was an instance of Double.
     * @throws RuntimeException exception if wrapper object was not found or was not an instance of Double
     * @modelguid {90525BFD-1835-4FF8-B13F-16B3001F3983}
     */
    public double getDouble(String key) {
        Object doubleObject = dataStorage.get(key);
        if ((doubleObject instanceof Double) == false) {
            throw new RuntimeException("\ngetDouble--Key \"" + key + "\" does not reference a double or value not found");
        }
        return ((Double)doubleObject).doubleValue();
    }

    /**
     * Get an float primitive from storage
     * @param key Key for object being retrieved
     * @return A float primitive if the wrapper was an instance of Float.
     * @throws RuntimeException exception if wrapper object was not found or was not an instance of Float
     * @modelguid {947D58DB-D684-44A4-BEB2-3FA5C8F33D1F}
     */
    public float getFloat(String key) {
        Object floatObject = dataStorage.get(key);
        if ((floatObject instanceof Float) == false) {
            throw new RuntimeException("\ngetFloat-Key \"" + key + "\" does not reference a float or value not found");
        }
        return ((Float)floatObject).floatValue();
    }

    /**
     * Get an boolean primitive from storage
     * @param key Key for object being retrieved
     * @return A boolean primitive if the wrapper was an instance of Boolean.
     * @throws RuntimeException exception if wrapper object was not found or was not an instance of Boolean
     * @modelguid {AD1D96BF-AEBD-492B-98D7-FE0BAE2E1F3C}
     */
    public boolean getBoolean(String key) {
        Object booleanObject = dataStorage.get(key);
        if ((booleanObject instanceof Boolean) == false) {
            throw new RuntimeException("\ngetBoolean-Key \"" + key + "\" does not reference a boolean or value not found");
        }
        return ((Boolean)booleanObject).booleanValue();
    }
    // --------- implementation of Map interface methods -----------------

    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns <tt>Integer.MAX_VALUE</tt>.
     * @return the number of key-value mappings in this map.
     * @modelguid {49DCF709-BD10-4D5D-BA04-8142138FFDD9}
     */
    public int size() {
        return (dataStorage.size());
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     * @return <tt>true</tt> if this map contains no key-value mappings.
     * @modelguid {9DA9B5D5-0D72-4FE7-AF30-7B4AAA4954A6}
     */
    public boolean isEmpty() {
        return (dataStorage.isEmpty());
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified key.
     * @param key key whose presence in this map is to be tested.
     * @return <tt>true</tt> if this map contains a mapping for the specified key.
     * @throws ClassCastException if the key is of an inappropriate type for this map.
     * @throws NullPointerException if the key is <tt>null</tt> and this map does not not permit <tt>null</tt> keys.
     * @modelguid {EFF858EF-078F-4004-ADE0-56E0E09CE0E7}
     */
    public boolean containsKey(Object key) {
        return (dataStorage.containsKey(key));
    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only if
     * this map contains at least one mapping to a value <tt>v</tt> such that
     * <tt>(value==null ? v==null : value.equals(v))</tt>.  This operation
     * will probably require time linear in the map size for most implementations of the <tt>Map</tt> interface.
     * @param value value whose presence in this map is to be tested.
     * @return <tt>true</tt> if this map maps one or more keys to the specified value.
     * @modelguid {8CC26E3B-8C03-417C-AD12-3C2959A4F74B}
     */
    public boolean containsValue(Object value) {
        return (dataStorage.containsValue(value));
    }

    /**
     * Returns the value to which this map maps the specified key.  Returns
     * <tt>null</tt> if the map contains no mapping for this key.  A return
     * value of <tt>null</tt> does not <i>necessarily</i> indicate that the
     * map contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to <tt>null</tt>.  The <tt>containsKey</tt>
     * operation may be used to distinguish these two cases.
     * @param key key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or
     * <tt>null</tt> if the map contains no mapping for this key.
     * @throws ClassCastException if the key is of an inappropriate type for this map.
     * @throws NullPointerException key is <tt>null</tt> and this map does not not permit <tt>null</tt> keys.
     * @see #containsKey(Object)
     * @modelguid {22F31AD1-4121-48DB-B3A0-4E2245494770}
     */
    public Object get(Object key) {
        return (dataStorage.get(key));
    }
    // Modification Operations

    /**
     * Associates the specified value with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for this key, the old value is replaced.
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return previous value associated with specified key, or <tt>null</tt>
     * if there was no mapping for key.  A <tt>null</tt> return can
     * also indicate that the map previously associated <tt>null</tt>
     * with the specified key, if the implementation supports <tt>null</tt> values.
     * @throws UnsupportedOperationException if the <tt>put</tt> operation is not supported by this map.
     * @throws ClassCastException if the class of the specified key or value prevents it from being stored in this map.
     * @throws IllegalArgumentException if some aspect of this key or value prevents it from being stored in this map.
     * @throws NullPointerException this map does not permit <tt>null</tt>
     * keys or values, and the specified key or value is <tt>null</tt>.
     * @modelguid {0F63B929-D631-412A-8442-33461A666256}
     */
    public Object put(Object key, Object value) {
        return (dataStorage.put(key, value));
    }

    /**
     * Removes the mapping for this key from this map if present (optional operation).
     * @param key key whose mapping is to be removed from the map.
     * @return previous value associated with specified key, or <tt>null</tt>
     * if there was no mapping for key.  A <tt>null</tt> return can
     * also indicate that the map previously associated <tt>null</tt>
     * with the specified key, if the implementation supports <tt>null</tt> values.
     * @throws UnsupportedOperationException if the <tt>remove</tt> method is not supported by this map.
     * @modelguid {FBC2C66E-BA2E-48B7-9124-58464F7E2FB3}
     */
    public Object remove(Object key) {
        return (dataStorage.remove(key));
    }
    // Bulk Operations

    /**
     * Copies all of the mappings from the specified map to this map
     * (optional operation).  These mappings will replace any mappings that
     * this map had for any of the keys currently in the specified map.
     * @param t Mappings to be stored in this map.
     * @throws UnsupportedOperationException if the <tt>putAll</tt> method is not supported by this map.
     * @throws ClassCastException if the class of a key or value in the
     * specified map prevents it from being stored in this map.
     * @throws IllegalArgumentException some aspect of a key or value in the
     * specified map prevents it from being stored in this map.
     * @throws NullPointerException this map does not permit <tt>null</tt>
     * keys or values, and the specified key or value is <tt>null</tt>.
     * @modelguid {BD64EA8F-FCC7-4928-B03A-9EE2CF8C8ADE}
     */
    public void putAll(Map t) {
        dataStorage.putAll(t);
    }

    /**
     * Removes all mappings from this map (optional operation).
     * @throws UnsupportedOperationException clear is not supported by this map.
     * @modelguid {C9692BA2-4E46-4E82-BBF9-D13336E14196}
     */
    public void clear() {
        dataStorage.clear();
    }
    // Views

    /**
     * Returns a set view of the keys contained in this map.  The set is
     * backed by the map, so changes to the map are reflected in the set, and
     * vice-versa.  If the map is modified while an iteration over the set is
     * in progress, the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding mapping from
     * the map, via the <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt> <tt>retainAll</tt>, and <tt>clear</tt> operations.
     * It does not support the add or <tt>addAll</tt> operations.
     * @return a set view of the keys contained in this map.
     * @modelguid {11A45DE7-A727-4F8C-8F30-F250AA825522}
     */
    public Set keySet() {
        return (dataStorage.keySet());
    }

    /**
     * Returns a collection view of the values contained in this map.  The
     * collection is backed by the map, so changes to the map are reflected in
     * the collection, and vice-versa.  If the map is modified while an
     * iteration over the collection is in progress, the results of the
     * iteration are undefined.  The collection supports element removal,
     * which removes the corresponding mapping from the map, via the <tt>Iterator.remove</tt>, <tt>Collection.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt> and <tt>clear</tt> operations.
     * It does not support the add or <tt>addAll</tt> operations.
     * @return a collection view of the values contained in this map.
     * @modelguid {0B27C264-A08E-4FF4-8316-E9877A28D8A6}
     */
    public Collection values() {
        return (dataStorage.values());
    }

    /**
     * Returns a set view of the mappings contained in this map.  Each element
     * in the returned set is a <tt>Map.Entry</tt>.  The set is backed by the
     * map, so changes to the map are reflected in the set, and vice-versa.
     * If the map is modified while an iteration over the set is in progress,
     * the results of the iteration are undefined.  The set supports element
     * removal, which removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not support the <tt>add</tt> or <tt>addAll</tt> operations.
     * @return a set view of the mappings contained in this map.
     * @modelguid {C89C2E21-FAEB-4240-9D99-5F90C1F2C178}
     */
    public Set entrySet() {
        return (dataStorage.entrySet());
    }
}
