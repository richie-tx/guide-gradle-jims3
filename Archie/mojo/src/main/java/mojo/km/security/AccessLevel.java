/*
 * Class AccessLevel.java
 * Created on May 9, 2002, 2:34:35 PM
 */

package mojo.km.security;
import java.io.Serializable;

/**
 * Type-Safe class used by the SecurityController to determine if the given user
 * has permission for a protected resource
 * @see mojo.km.security.SecurityController
 * @author Chad Oliver
 * @modelguid {7DE7EDCB-17AB-48D9-B3FE-1D89308D9821}
 */

public final class AccessLevel implements Comparable, Serializable {
	/** @modelguid {CC721687-6470-4BD3-9A37-1FCBF8C27AA2} */
    public static final AccessLevel NONE = new AccessLevel("None", -1);
	/** @modelguid {1BC5646C-6322-4E48-A587-5874D98D3871} */
    public static final AccessLevel READ = new AccessLevel("Read Only", 0);
	/** @modelguid {79ACA188-E8B4-48E6-B1D2-59C16DDE3B6A} */
    public static final AccessLevel READ_WRITE = new AccessLevel("Read/Write", 1);
	/** @modelguid {2A48CE08-0176-48A6-84EB-5852CBC1D301} */
    public static final AccessLevel EXECUTE = new AccessLevel("Execute", 2);


    /** only needed for remote debugging. 
     * @modelguid {9079CB85-1DDC-43F0-8A5B-BFF800072BD9}
     */
    private String description;

    /** used to compare one permission with another. 
     * @modelguid {0EE72FC7-5A5B-41C9-B85D-409DF3D5AC64}
     */
    private int compareWeight;

    /**
     * Creates a type-safe instance of AccessLevel.  The constructor is
     * only called from a publicly exposed class variable.
     * @param description - the access level description
     * @param compareWeight - an integer representing the description
     * @modelguid {901EF6C4-EF18-4280-A303-6045F653EFC1}
     */
    private AccessLevel(String description, int compareWeight) {
        this.description = description;
        this.compareWeight = compareWeight;
    }

    /** @return an integer representing the access level 
     * @modelguid {F9A57472-4DCE-44CD-86A0-4DD795EDCBB3}
     */
    public int getCompareWeight() {
        return this.compareWeight;
    }
	 
	/** @modelguid {7290B1EF-E37C-4274-9070-114275E65A42} */
	public String getDescription() {
		return this.description;
	}

    /**
     * @param compareWeight - an integer representing the Access Level
     * @return an Access Level represented by the compareWeight
     * @modelguid {9C48EF27-966A-445B-91AC-BE6202C35B4B}
     */
    public static AccessLevel getAccessLevel(int compareWeight) {
        if (compareWeight == AccessLevel.READ.getCompareWeight()) {
            return AccessLevel.READ;
        }
        else if (compareWeight == AccessLevel.READ_WRITE.getCompareWeight()) {
            return AccessLevel.READ_WRITE;
        }
	   else if (compareWeight == AccessLevel.EXECUTE.getCompareWeight()) {
            return AccessLevel.EXECUTE;
        }

        return AccessLevel.NONE;
    }

    /**
     * @param accessLevel the first object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     *         first argument is less than, equal to, or greater than the
     *         second.
     * @throws ClassCastException if the arguments' types prevent them from
     *         being compared by this Comparator.
     * @modelguid {17FC1D93-3E54-4917-AFCB-B153EA1F8543}
     */

    public int compareTo(Object accessLevel) throws ClassCastException {
        if (accessLevel == null) {
            return -1;
        }

        if (accessLevel instanceof AccessLevel) {
            AccessLevel newAccessLevel = (AccessLevel)accessLevel;

            if (newAccessLevel.getCompareWeight() == this.getCompareWeight()) {
                return 0;
            }

            if (newAccessLevel.getCompareWeight() > this.getCompareWeight()) {
                return 1;
            }

            if (newAccessLevel.getCompareWeight() < this.getCompareWeight()) {
                return -1;
            }
        }

        throw new ClassCastException("Can only compare AccessLevel objects");
    }

    /**
     * Return a string representation of this PermissionType.
     * @overrides toString in class java.lang.Object
     * @return Return a string representation of this PermissionType.
     * @modelguid {4D787D94-3BB8-4DE4-A0F1-26049D072D6D}
     */
    public String toString() {
        StringBuffer out = new StringBuffer("Permission=").append(this.description).append(",Weight=").append(this.getCompareWeight());

        return out.toString();
    }
}
