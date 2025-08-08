package mojo.km.security.ldap;

/** @modelguid {4C5F460D-E413-4B67-80E4-8905B0D81CC2} */
public final class DCDPath
{
	/** @modelguid {FDF03F56-CB52-4968-AAD1-7B0629B1BDD8} */
    public static final DCDPath INT = new DCDPath("INT", "Internal", "ou=Int,ou=people,dc=nationwidedir,dc=lab");
	/** @modelguid {9AE6C4DA-0926-48B5-B07C-C266C206EE69} */
    public static final DCDPath EXT = new DCDPath("EXT", "External", "ou=Ext,ou=people,dc=nationwidedir,dc=lab");

	/** @modelguid {26C56EFC-43FC-4684-9A0D-2FF5E546BCE2} */
    private String shortDescription = "";
	/** @modelguid {2AB09B70-6BDD-49EF-9253-2124B15C5E72} */
    private String description = "";
	/** @modelguid {F10721E7-3023-46C6-971A-2809E91DE19D} */
    private String path = "";

    /**
     * Creates a type-safe instance of DCDPath.  The constructor is only called from a publicly exposed class variable.
     * @param description - the access level description
     * @modelguid {64CAE4EF-55A8-4046-B7D1-40A882010A27}
     */
    private DCDPath(String aShortDescription, String aDescription, String aPath)
    {
        shortDescription = aShortDescription;
        description = aDescription;
        path = aPath;
    }

	/** @modelguid {F518ED68-5514-40F5-9591-34A8591FED95} */
    public String getShortDescription()
    {
        return shortDescription;
    }

	/** @modelguid {4843CB52-B8E9-4275-AFA4-72E491D8A198} */
    public String getDescription()
    {
        return description;
    }

	/** @modelguid {4EBCAD4B-F8B1-41BF-87E3-47B6D8EED05A} */
    public String getPath()
    {
        return path;
    }

    /**
     * @param shortDescription - an String representing the DCDPath
     * @return an DCDPath
     * @modelguid {BB4F4FAD-7FF7-46DC-970D-4DDC3449D572}
     */
    public static DCDPath getDCDPath(String aShortDescription)
    {
        if (aShortDescription.equals(DCDPath.EXT.getShortDescription()))
        {
            return DCDPath.EXT;
        }

        return DCDPath.INT;
    }

	/** @modelguid {9EC97AEF-7EC8-48B4-B5AF-51AE2D01FB39} */
    public String toString()
    {
        return shortDescription + " " + description + " " + path;
    }
}
