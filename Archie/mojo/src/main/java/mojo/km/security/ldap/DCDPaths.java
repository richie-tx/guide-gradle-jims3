package mojo.km.security.ldap;

import java.util.Enumeration;
import java.util.Vector;

/** @modelguid {A843FA85-54D7-4D43-BAE0-5E64C694816A} */
public final class DCDPaths
{
	/** @modelguid {D57D8C17-B357-4AA5-BBE6-999E87230D6A} */
    private Vector paths = new Vector();
	/** @modelguid {428122F9-C156-4FB8-B364-B51DFBC5861E} */
    private Enumeration enumeration = null;
	/** @modelguid {28B9B894-0450-459F-B4E6-FC9FEEF09CB3} */
    private DCDPath current = null;

	/** @modelguid {A1E82132-E885-4996-A476-9CBEAE045CB4} */
    public DCDPaths()
    {
        add(DCDPath.INT);
        add(DCDPath.EXT);
    }

	/** @modelguid {31CB2A37-D46B-4A82-9D1B-C779945B6D79} */
    private void add(DCDPath aPath)
    {
        paths.add(aPath);
    }

	/** @modelguid {CDFF0BD1-E601-462F-8998-E974E9E4D0B0} */
    public boolean hasMoreElements()
    {
        if (enumeration == null)
        {
            first();
        }

        return enumeration.hasMoreElements();
    }

	/** @modelguid {88BD9AF9-7062-44BD-8DEF-A732436A7F16} */
    public DCDPath next()
    {
        return (DCDPath)enumeration.nextElement();
    }

	/** @modelguid {66DA2CFD-2298-43D2-9CC5-86FC8946FA6A} */
    public void first()
    {
        enumeration = paths.elements();
        current = null;
    }
}
