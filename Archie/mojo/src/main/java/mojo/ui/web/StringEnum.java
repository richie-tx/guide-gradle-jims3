package mojo.ui.web;

/** Base class for type-safe Strings
 * @modelguid {DDC419FE-DBD7-442C-87BD-8AA3E1E596C9}
*/
public class StringEnum
{
	/** @modelguid {CCE40FCC-29B7-41AA-8749-C7E4FF8DD24B} */
  private String _safeString;

	/** @modelguid {02D6CA4B-D9AD-403E-B938-39E14B0EEFE3} */
protected StringEnum(String s)
{
  _safeString = s;
}

	/** @modelguid {DED76651-E1DC-41F7-A6F4-E432FC04A24B} */
public String getStringValue()
{
  return _safeString;
}

	/** @modelguid {68E019C1-80E7-4944-942A-F75BDFC3CF6A} */
public boolean equals(Object obj)
{
  if (!(obj instanceof StringEnum))
    return false;
  return (((StringEnum) obj).getStringValue().equals(_safeString));
}

	/** @modelguid {0BB7A0D9-3951-42ED-B595-A22A3C7D54D4} */
public String toString()
{
  return _safeString;
}

}