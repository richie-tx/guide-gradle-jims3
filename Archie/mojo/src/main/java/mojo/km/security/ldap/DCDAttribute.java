package mojo.km.security.ldap;

/** @modelguid {697FF10D-1A07-464E-BC25-13A5FF00AC8A} */
public final class DCDAttribute
{
	/** @modelguid {F33585B5-EE34-4046-80C9-93DC0331C372} */
	public static final DCDAttribute DCDIRECT = new DCDAttribute("nwDCdirect");
	/** @modelguid {E43B74F0-B17C-4BDE-A26B-AEDBA8356A7A} */
	public static final DCDAttribute USERID = new DCDAttribute("uid");
	/** @modelguid {EE6B83E6-CD02-4706-8B6B-69D05B6C9C78} */
	public static final DCDAttribute SIRNAME = new DCDAttribute("sn");
	/** @modelguid {F7D7BC4F-B9CD-4E92-99CB-1E5DF5CF07DC} */
	public static final DCDAttribute FIRSTNAME = new DCDAttribute("givenName");
	
	/** @modelguid {890D6003-11F3-4E7E-9BAC-5264EC02A14B} */
    private String name = "";
	 
    /**
     * Creates a type-safe instance of DCDAttributes.  The constructor is
     * only called from a publicly exposed class variable.
     * @param description - the DCD attribute description
     * @modelguid {1AAC4D2F-8DDE-494E-9334-DA674517DDB6}
     */
    private DCDAttribute(String aName) {
        name = aName;
    }

	/** @modelguid {B845F275-F98B-43EC-9989-E9EFD5A00265} */
	public String getName()
	{
		return name;
	}
	
	/** @modelguid {7EAC899D-397A-4009-AD3D-42BFE03B9500} */
	public String toString()
	{
		return name;
	}
}	
