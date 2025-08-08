package mojo.tools.code.audit.checkstyle.check.imports;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the result of an access check.
 *
 * @author Jim Fisher
 */
final class AccessResult
        implements Serializable
{
    /** Numeric value for access result ALLOWED. */
    private static final int CODE_ALLOWED = 10;
    /** Numeric value for access result DISALLOWED. */
    private static final int CODE_DISALLOWED = 20;
    /** Numeric value for access result UNKNOWN. */
    private static final int CODE_UNKNOWN = 30;
    /** Label for access result ALLOWED. */
    private static final String LABEL_ALLOWED = "ALLOWED";
    /** Label for access result DISALLOWED. */
    private static final String LABEL_DISALLOWED = "DISALLOWED";
    /** Label for access result UNKNOWN. */
    private static final String LABEL_UNKNOWN = "UNKNOWN";

    /** Represents that access is allowed. */
    public static final AccessResult ALLOWED = new AccessResult(CODE_ALLOWED,
            LABEL_ALLOWED);
    /** Represents that access is disallowed. */
    public static final AccessResult DISALLOWED = new AccessResult(
            CODE_DISALLOWED, LABEL_DISALLOWED);
    /** Represents that access is unknown. */
    public static final AccessResult UNKNOWN = new AccessResult(CODE_UNKNOWN,
            LABEL_UNKNOWN);

    /** map from results names to the respective result */
    private static final Map NAME_TO_LEVEL = new HashMap();
    static {
        NAME_TO_LEVEL.put(LABEL_ALLOWED, ALLOWED);
        NAME_TO_LEVEL.put(LABEL_DISALLOWED, DISALLOWED);
        NAME_TO_LEVEL.put(LABEL_UNKNOWN, UNKNOWN);
    }

    /** Code for the access result. */
    private final int mCode;
    /** Label for the access result. */
    private final String mLabel;

    /**
     * Constructs an instance.
     *
     * @param aCode the code for the result.
     * @param aLabel the label for the result.
     */
    private AccessResult(final int aCode, final String aLabel)
    {
        mCode = aCode;
        mLabel = aLabel.trim();
    }

    /**
     * @return the label for the result.
     */
    String getLabel()
    {
        return mLabel;
    }

    /** {@inheritDoc} */
    public String toString()
    {
        return getLabel();
    }

    /** {@inheritDoc} */
    public boolean equals(Object aObj)
    {
        boolean result = false;

        if ((aObj instanceof AccessResult)
                && (((AccessResult) aObj).mCode == this.mCode))
        {
            result = true;
        }

        return result;
    }

    /** {@inheritDoc} */
    public int hashCode()
    {
        return mCode;
    }

    /**
     * SeverityLevel factory method.
     *
     * @param aName access result name.
     * @return the {@link AccessResult} associated with the supplied name.
     */
    public static AccessResult getInstance(String aName)
    {
        // canonicalize argument
        final String arName = aName.trim();

        final AccessResult retVal = (AccessResult) NAME_TO_LEVEL.get(arName);
        if (retVal == null) {
            throw new IllegalArgumentException(arName);
        }
        return retVal;
    }

    /**
     * Ensures that we don't get multiple instances of one SeverityLevel
     * during deserialization. See Section 3.6 of the Java Object
     * Serialization Specification for details.
     *
     * @return the serialization replacement object
     */
    private Object readResolve()
    {
        return getInstance(mLabel);
    }

}
