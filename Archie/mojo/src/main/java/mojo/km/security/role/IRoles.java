/*
 * Class IRoles.java
 * Created on May 22, 2002, 3:41:22 PM
 */

package mojo.km.security.role;

import java.util.Enumeration;

/** @modelguid {8DD9AE29-4CA9-4DF2-B31E-AC6E9841D17C} */
public interface IRoles {
	/** @modelguid {DD61C3AD-B6C0-4234-B33D-B5910D2C7AED} */
    public Enumeration getEnumeration();

	/** @modelguid {C4A470A6-0C5B-46BA-A0AD-6E03546B92E4} */
    public void copyInto(Object[] anArray);

	/** @modelguid {EDEFABCB-1D82-4FA8-A068-E3212F0925B2} */
    public int size();
}
