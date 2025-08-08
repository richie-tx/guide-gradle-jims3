package mojo.km.security.ldap;

/** @modelguid {637A0288-AA93-466D-8204-7A2D3AD77C89} */
public class DCDFilter 
{
	/** @modelguid {EF854B9D-33D8-4E43-86C5-F68CCE1C6236} */
	private String uid = "(uid=*)";
	/** @modelguid {D51EFA19-6918-44C4-A505-9EFA405EEAF5} */
	private String first = null;
	/** @modelguid {3AB163AE-3FB3-4FF8-88EE-27404915EAC2} */
	private String last = null;
	/** @modelguid {2DCF41BC-9903-4209-AFB7-7F2D8214EA69} */
	private DCDAccess access = null;

	/** @modelguid {2B63E5A7-108E-4241-B2D6-71D9CC6D584F} */
    public DCDFilter() { }

	/** @modelguid {6FE094A3-EB79-4880-9C68-3CD9C8ACC0EE} */
    public DCDFilter(String searchUserID,String firstName,String lastName)
    {
		setUserId(searchUserID);
        setFirstName(firstName);
        setLastName(lastName);
    }

	/** @modelguid {A3129BD7-65F6-4CA8-BA5A-5AF72B0C3829} */
	public void setUserId(String filter)
	{
        if (vailidate(filter))
		{
			uid = createFilter(DCDAttribute.USERID, filter);
        }
	}

	/** @modelguid {386F209A-8CCB-4A2F-BD65-F30898C9B4AD} */
	private String getUserId()
	{
		return uid;
	}
	
	/** @modelguid {FA47353D-4B3E-436B-9BBC-FFB3CCD98D62} */
	public void setFirstName(String filter)
	{
        if (vailidate(filter))
		{
			first = createFilter(DCDAttribute.FIRSTNAME, filter);
        }
	}

	/** @modelguid {B550AFAA-62E4-4706-B671-247D882B9996} */
	private String getFirstName()
	{
		return first;
	}
	
	/** @modelguid {0705583C-1A8F-4896-8E7E-42BF8304D81E} */
    public void setLastName(String filter)
	{
        if (vailidate(filter))
		{
			last = createFilter(DCDAttribute.SIRNAME, filter);
        }
	}

	/** @modelguid {5F38EE6B-1DCC-4038-99CF-CC5A6CB78C4D} */
	private String getLastName()
	{
		return last;
	}
	
	/** @modelguid {6CEE8B54-DE74-4A52-8100-4BF6AD8D5BBD} */
	public void setAccess(boolean allow)
	{
		if (allow)
		{
			access = DCDAccess.ALLOW;
		}
		else 
		{
			access = DCDAccess.DENY;
		}
	}
	
	/** @modelguid {5481597A-DEFD-45D7-BEC7-5E8C6F425FA6} */
	private DCDAccess getAccess()
	{
		return access;
	}

	/** @modelguid {AAD0BE28-8335-47D2-8355-EF43634DC8AB} */
	public String getFilter()
	{
		if (getFirstName() == null && getLastName() == null && getAccess() == null)
		{
			return getUserId();
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("(&");
		sb.append(getUserId());
		
		if (getFirstName() != null)
		{
			sb.append(getFirstName());
		}

		if (getLastName() != null)
		{
			sb.append(getLastName());
		}

		if (getAccess() != null)
		{
			sb.append(getAccess().getFilter());
		}

		sb.append(")");

		return sb.toString();
	}

	/** @modelguid {DE9B812D-0E7C-4C8B-A2AE-B2AF75BFE370} */
	private static boolean vailidate(String filter)
    {
        if (filter != null && filter.trim().length() > 0)
		{
            return true;
		}

        return false;
    }

	/** @modelguid {5D698BF2-BF09-48EE-9539-257EBB3583D6} */
	private static String createFilter(DCDAttribute attribute, String filter)
	{
		StringBuffer sb = new StringBuffer();

		sb.append("(");
		sb.append(attribute.getName());
		sb.append("=");
		sb.append(filter);
		sb.append(")");
		
		return sb.toString();
	}
}