/*
 * Created on Nov 5, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.config;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author mpatino
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PFProperties extends GenericProperties
{
	public static final String NAME = "name";

	public static final String TEXT = "text";

	public static final String PACKAGE = "package";

	private ArrayList pfProps = new ArrayList();

	public void addPfProperty(PFProperty pfProperty)
	{
		pfProps.add(pfProperty);
	}

	public Iterator getPfProperty(String pf)
	{
		return pfProps.iterator();
	}

	public String getName()
	{
		return getProperty(NAME);
	}

	public void setName(String name)
	{
		setProperty(NAME, name);
	}
	public String getText()
	{
		return getProperty(TEXT);
	}

	public void setText(String text)
	{
		setProperty(TEXT, text);
	}

	public String getPackage()
	{
		return getProperty(PACKAGE);
	}

	public void setPackage(String pkg)
	{
		setProperty(PACKAGE, pkg);
	}

}
