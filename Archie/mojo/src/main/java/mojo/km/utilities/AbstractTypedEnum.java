package mojo.km.utilities;

import java.io.Serializable;
import java.io.ObjectStreamException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.io.InvalidObjectException;

/**
 * Abstract base class that other type-safe enums that wrap a string can
 * extend.  This provides services such as automatic registration of static
 * singleton patern instances, resolution during deserialization to ensure
 * that the '==' operator can be used between tpe instances and static methods
 * for retrieving instances based on value or all instances defined in a subclass.
 *
 * @author <a href="mbrink@the401k.com">Michael Brinkman</a>
 * @modelguid {14F6339B-9D67-4A6B-BEFB-D0CE53784722}
 */
public abstract class AbstractTypedEnum implements Serializable, Comparable {

	// =========================================================================
	//						Static Implementation
	// =========================================================================

	/**
	 * Map that holds keys of Class names mapped to Map values.  The Map values represent a map of
	 * keys of <code>AbstractTypedStringEnum.values</code> mapped to the appropriate
	 * AbstractTypedStringEnum subclass instances.
	 * @modelguid {FFECB01D-A1A8-426E-B29D-202D379A662F}
	 */
	private static Map valueMapFromSubclassNameMap = new HashMap();

	/**
	 * Gets all the subtype instances of an <code>AbstractTypedStringEnum</code>
	 * based on the name of the given class.
	 * @modelguid {BABCB0E7-2295-4DE7-AB50-2793BB3A61D3}
	 */
	protected static Iterator getSubTypeInstances(Class subclass) {
		Map valueMapForSubclassName = (Map)AbstractTypedEnum.valueMapFromSubclassNameMap.get(subclass.getName());

		if (valueMapForSubclassName == null) {
			throw (new IllegalArgumentException("The Class '" + subclass.getName() + "' has not been registered"));
		}

		return valueMapForSubclassName.values().iterator();
	}

	/**
	 * Gets a particular subtype instance of an <code>AbstractTypedStringEnum</code>
	 * based on the name of the given class and the given value or returns <code>null</code>
	 * if the value parameter is <code>null</code>
	 *
	 * @param		subclass the subclass of AbstractTypedStringEnum to look for an instance
	 * 				of.
	 * @param		value the value to search for.
	 * @return		The subclass instance that has a value equal to the input value or
	 * 				<code>null</code> if the value parameter was <code>null</code>.
	 * @exception	IllegalArgumentException is thrown if the subclass has not had
	 * 				any instances registered or if no instance exists whose value matches the
	 * 				given value parameter.
	 * @modelguid {15D9C310-F897-44F0-9907-D778EE116E54}
	 */
	protected static AbstractTypedEnum getSubTypeInstance(Class subclass, Object value) {
		if (value == null) {
			return null;
		}

		Map valueMapForSubclassName = (Map)AbstractTypedEnum.valueMapFromSubclassNameMap.get(subclass.getName());

		if (valueMapForSubclassName == null) {
			throw new IllegalArgumentException("The Class '" + subclass.getName() + "' has not been registered");
		}

		AbstractTypedEnum returnValue = (AbstractTypedEnum)valueMapForSubclassName.get(value);

		if (returnValue == null) {
			throw new IllegalArgumentException(
					"No value of '" + value + "' was found registered for the " + "class '" + subclass.getName() + "'.  Check the enum class definition.");
		}

		if (!subclass.isInstance(returnValue)) {
			throw (
				new IllegalStateException(
					"AbstractTypedStringEnum for class '"
						+ subclass.getName()
						+ "' and value '"
						+ value
						+ "' had type of '"
						+ returnValue.getClass().getName()
						+ "'"));
		}

		return returnValue;
	}

	// =========================================================================
	//						Instance Implementation
	// =========================================================================

	/**
	 * Holds the actual String value the enum instance represents.
	 * @modelguid {ECD9E106-7124-49F1-B054-63565E4AB25C}
	 */
	private Comparable value;

	/**
	 * Protected constructor used by subclasses to instantiate new
	 * enum instances.
	 * @modelguid {CE537031-9202-4355-87FB-3293C4435017}
	 */
	protected AbstractTypedEnum(Comparable value) {
		this.value = value;

		String lClassName = this.getClass().getName();

		//	Get the submap for this class
		Map submap = (Map)AbstractTypedEnum.valueMapFromSubclassNameMap.get(lClassName);

		//	If there's not an existing submap, create one.
		if (submap == null) {
			//	Create a new submap for the class who'se instances we're creating.
			submap = new HashMap();
			AbstractTypedEnum.valueMapFromSubclassNameMap.put(lClassName, submap);
		}

		//	Now just register this in the submap.
		if (submap.containsKey(this.value)) {
			throw new RuntimeException("AbstractTypedStringEnum for class '"
						+ lClassName
						+ "' and value '"
						+ value
						+ "' already exists.");
		} else {
			submap.put(this.value, this);
		}
	}

	/**
	 * Gets the actual String value the enum instance represents.
	 * @modelguid {312169F6-636D-4828-9B17-2B04E4E6D1EF}
	 */
	public Object getValue() {
		return this.value;
	}

	/**
	 * Compares the instance classes and the held values of the two instances.
	 *
	 * @return		true if the instance classes and the internally held values
	 * 				are equal and false if they are not.
	 * @modelguid {8A0CD55D-0316-4121-8F3B-AA5890A0FD21}
	 */
	public boolean equals(Object o) {
		return this == o;
	}

	/** @modelguid {48E81291-BB74-4103-A215-56C562799473} */
	public String toString() {
		return getValue() != null ? getValue().toString() : null;
	}

	/**
	 * Returns the result of <code>this.value.compareTo(o.value)</code>,
	 * unless o is null, in which case the comparison
	 * <code>this.value.compareTo(null)</code> is performed.
	 * @modelguid {73A1C4F0-A666-4FF6-B8FF-AA3CAC8BDC37}
	 */
	public int compareTo(Object o) {
		AbstractTypedEnum en = (AbstractTypedEnum)o;

		Object enumValue = null;

		if (en != null) {
			enumValue = en.value;
		}

		return this.value.compareTo(enumValue);
	}

	/**
	 * Replaces the given deserialized instance with one of the actual enumeration
	 * subtype instances in order to ensure that the '==' operator can always be
	 * used between enumeration instances.
	 * @modelguid {167BC021-B710-439C-A0E1-A8398B15B9D6}
	 */
	public Object readResolve() throws ObjectStreamException {
		Class thisClass = this.getClass();

		try {
			return AbstractTypedEnum.getSubTypeInstance(thisClass, this.value);
		} catch (Exception e) {
			System.out.println(
				"We tried to resolve an instance for class '"
					+ thisClass.getName()
					+ "' with value '"
					+ this.value
					+ "' and failed because: "
					+ e.getMessage());
			e.printStackTrace();
			throw new InvalidObjectException(e.getMessage());
		}
	}
}
