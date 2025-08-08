package mojo.km.security.ldap;

/** @modelguid {9F6B53AD-3AFF-4919-B615-F4AF9D4DFA54} */
public final class DCDAccess 
{
	/** only needed for remote debugging. 
	 * @modelguid {24B1EB65-3EF7-49FF-A919-7659E1B3BF5F}
	 */
    private String description = "";
	/** @modelguid {5A8669C7-4CAC-4041-A55F-4BC44EDEED95} */
	private String filter = "";
	 
	/** @modelguid {DD31C021-8631-4A96-851D-0847F6AE284D} */
    public static final DCDAccess ALLOW = new DCDAccess("Allow", "(nwDCdirect=true)");
	/** @modelguid {EB99DE98-1C2D-4B50-8ECA-D2D2A705FE22} */
    public static final DCDAccess DENY = new DCDAccess("Deny", "(!(nwDCdirect=true))");

    /**
     * Creates a type-safe instance of AccessLevel.  The constructor is
     * only called from a publicly exposed class variable.
     * @param description - the access level description
     * @param compareWeight - an integer representing the description
     * @modelguid {2C5742F2-4978-45D0-AE36-01D91B46EB56}
     */
    private DCDAccess(String aDescription, String aFilter) {
        description = aDescription;
		filter = aFilter;
    }

	/** @modelguid {5548ACBF-DFC6-44AE-98AE-9114EA44B356} */
	public String getFilter()
	{
		return filter;
	}
	
	/** @modelguid {814C0ABA-B803-478A-9239-FDD33D858720} */
	public String toString()
	{
		return description + " " + filter;
	}
}	
