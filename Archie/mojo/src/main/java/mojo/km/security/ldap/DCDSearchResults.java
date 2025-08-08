package mojo.km.security.ldap;

import java.util.Enumeration;
import java.util.Vector;

/** @modelguid {21615FBB-8F6B-4A0B-BAC4-6B6A30DA4B66} */
public class DCDSearchResults implements Enumeration {

	/** @modelguid {0E927A93-15C7-4ED5-B5FD-0CE23871CE57} */
	private Vector list = new Vector();
	/** @modelguid {29F84F1B-715F-4B9A-ABE3-C009D8F77E61} */
	private Enumeration enumeration = null;
	/** @modelguid {E81B6E47-194B-4762-B19C-1A62C3EE21E5} */
    private int size=0;
	 	
	/** @modelguid {0577B894-A525-4FBE-8106-201212601B9A} */
	public void add(DCDSearchResult dCDSearchResult)
	{
		if (dCDSearchResult != null)
		{
			list.add(dCDSearchResult);
			size+=1;
		}
	}
	
	/** @modelguid {3330F4DB-97AF-4C28-96D1-E0482E81DE1D} */
	public boolean hasMoreElements() 
	{
		if (enumeration == null)
		{
			first();
		}
		
		return enumeration.hasMoreElements();
	}
	
	/** @modelguid {016416D4-E923-4417-ADD7-9AD6659E42E0} */
	public Object nextElement() 
	{
		return enumeration.nextElement();
	}

	/** @modelguid {D496584E-ED7E-4E48-BA01-D03E9F55CA07} */
	public void first()
	{
		enumeration = list.elements();
	}

	/** @modelguid {34545BA2-3B65-4006-9716-C36BF73D4465} */
	public int getSize()
	{
		return size;
	}
}