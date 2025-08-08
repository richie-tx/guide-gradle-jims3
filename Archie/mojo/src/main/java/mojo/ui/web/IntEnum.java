package mojo.ui.web;

/** Base class for type-safe ints
 * @modelguid {3B2510CC-E39A-482C-9BA5-87800825EDC2}
*/
public class IntEnum
{
	/** @modelguid {5D493CFF-26F2-4638-9641-B064754201AD} */
  private int _safeInt;

	/** @modelguid {271CC53C-FB70-4FA2-B26E-E6FF34E2CF29} */
protected IntEnum(int s)
{
  _safeInt = s;
}

	/** @modelguid {D4F1068F-0F6D-4EB2-97C9-F45D6760ED66} */
public int getIntValue()
{
  return _safeInt;
}

	/** @modelguid {3D85EA1B-296E-422C-A913-81E311A9D91B} */
public boolean equals(Object obj)
{
  if (!(obj instanceof IntEnum))
    return false;

  return (((IntEnum) obj).getIntValue() == _safeInt);
}

//override Object hashcode
	/** @modelguid {21295534-10AC-4D8F-B2EC-0617DF080C66} */
public int hashCode()
{
  Integer i = new Integer(_safeInt);
  return i.hashCode();
}

	/** @modelguid {5A2BD1D2-1CB5-40FC-B36B-FA01142CC870} */
public String toString()
{
  return "" + _safeInt;
}

}