package mojo.km.security.ldap;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

/** @modelguid {6E8AFF89-AA4C-4607-B450-35A6926DB1DF} */
public class DCDSearchResult
{
	/** @modelguid {0A5949B6-7F92-4566-8847-84D2D9E8F822} */
    private boolean dcdDirectAccess = false;
	/** @modelguid {297B48C3-ECC2-46A7-85C2-E89D016FE1ED} */
    private String userId = null;
	/** @modelguid {5EB0A8A1-485A-40CC-A3D9-9CA8FBD2C715} */
    private String firstName = null;
	/** @modelguid {E79EADC1-0637-41EF-9BE0-75AA68954CAF} */
    private String lastName = null;

	/** @modelguid {4C1FA52F-EA13-499C-8457-B6F926A25869} */
    public DCDSearchResult(Attributes attributes)
    {
        setDCDirectAccess(attributes.get(DCDAttribute.DCDIRECT.getName()));
        setUserId(attributes.get(DCDAttribute.USERID.getName()));
        setFirstName(attributes.get(DCDAttribute.FIRSTNAME.getName()));
        setLastName(attributes.get(DCDAttribute.SIRNAME.getName()));
    }

	/** @modelguid {DC935E74-865A-4A3A-8664-76881919EC00} */
    private void setDCDirectAccess(Object attribute)
    {
        String value = getValue(attribute);

        if (value != null && value.equals("true"))
        {
            dcdDirectAccess = true;
        }
        else
        {
            dcdDirectAccess = false;
        }
    }

	/** @modelguid {8FDE414B-E9DD-49A7-A538-FD49F1352A2B} */
    public boolean getDCDdirectAccess()
    {
        return dcdDirectAccess;
    }

	/** @modelguid {AD8FA613-4B9F-4A80-A265-AC69A95098AF} */
    private void setUserId(Object attribute)
    {
        userId = getValue(attribute);
    }

	/** @modelguid {0BEBD013-23ED-4DD8-9F57-DBA58D5D934F} */
    public String getUserId()
    {
        return userId;
    }

	/** @modelguid {A1D189A5-A27A-48A1-AB3B-D8B9D4CECB30} */
    private void setFirstName(Object attribute)
    {
        firstName = getValue(attribute);
    }

	/** @modelguid {782B5EE1-2D04-4E1B-AD38-DDAE1674C721} */
    public String getFirstName()
    {
        return firstName;
    }

	/** @modelguid {19AB8C17-B1D1-45E9-B2A2-632E0AAD727C} */
    private void setLastName(Object attribute)
    {
        lastName = getValue(attribute);
    }

	/** @modelguid {B04CF930-CC45-4A1C-ABAE-6A67662B201A} */
    public String getLastName()
    {
        return lastName;
    }

	/** @modelguid {34397CF1-3D24-4284-B87A-F0DCA951B453} */
    private String getValue(Object attribute)
    {
        String value = null;

        try
        {
            if (attribute != null)
            {
                value = (String)((Attribute)attribute).get();
            }
        }
        catch (Exception e)
        {
        }

        return value;
    }

	/** @modelguid {575F0314-D9EE-45CB-8E9B-FBFB4CF943EC} */
    public String toString()
    {
        return "UserId=" + getUserId() + " FirstName=" + getFirstName() + " LastName=" + getLastName() +
            " DCDdirectAccess=" + getDCDdirectAccess();
    }


}
