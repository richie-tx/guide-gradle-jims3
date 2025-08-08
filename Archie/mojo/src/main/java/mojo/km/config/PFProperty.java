/*
 * Created on Nov 5, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.config;

/**
 * @author mpatino
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PFProperty extends GenericProperties
{
	public static final String NAME = "name";

	public static final String TEXT = "text";

	public static final String PACKAGE = "package";
	public static final String DATASOURCE = "datasource";
	public static final String PICTURE = "picture";
	public static final String LENGTH = "length";
	public static final String MINIMUMLENGTH = "minimumlength";
	public static final String DATAFORMAT = "dataformat";
	public static final String PADCHAR = "padchar";
	public static final String M204KEYTYPE = "m204keytype";
	public static final String POSITION = "position";
	public static final String KEYFIELD = "keyfield";
	public static final String ELEMENTREQUIRED = "elementrequired";
	public static final String VALIDVALUETYPE = "validvaluetype";
	public static final String VALIDVALUES = "validvalues";
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
	public String getDataSource()
	{
		return getProperty(DATASOURCE);
	}

	public void setDataSource(String ds)
	{
		setProperty(DATASOURCE, ds);
	}
	public String getPicture()
	{
		return getProperty(PICTURE);
	}

	public void setPicture(String pic)
	{
		setProperty(PICTURE, pic);
	}
	public String getLength()
	{
		return getProperty(LENGTH);
	}

	public void setLength(String len)
	{
		setProperty(LENGTH, len);
	}
	public String getMinimumLength()
	{
		return getProperty(MINIMUMLENGTH);
	}

	public void setMinimumLength(String mlen)
	{
		setProperty(MINIMUMLENGTH, mlen);
	}
	public String getDataFormat()
	{
		return getProperty(DATAFORMAT);
	}

	public void setDataFormat(String dataFormat)
	{
		setProperty(DATAFORMAT, dataFormat);
	}
	public String getPadChar()
	{
		return getProperty(PADCHAR);
	}

	public void setPadChar(String padChar)
	{
		setProperty(PADCHAR, padChar);
	}
	public String getM204KeyType()
	{
		return getProperty(M204KEYTYPE);
	}

	public void setM204KeyType(String keyType)
	{
		setProperty(M204KEYTYPE, keyType);
	}
	public String getPosition()
	{
		return getProperty(POSITION);
	}

	public void setPosition(String pos)
	{
		setProperty(POSITION, pos);
	}

	public String getKeyField()
	{
		return getProperty(KEYFIELD);
	}

	public void setKeyField(String keyf)
	{
		setProperty(KEYFIELD, keyf);
	}

	public String getElementRequired()
	{
		return getProperty(ELEMENTREQUIRED);
	}

	public void setElementRequired(String keyf)
	{
		setProperty(ELEMENTREQUIRED, keyf);
	}

	public String getValidValueType()
	{
		return getProperty(VALIDVALUETYPE);
	}

	public void setValidValueType(String validvt)
	{
		setProperty(VALIDVALUETYPE, validvt);
	}

	public String getValidValues()
	{
		return getProperty(VALIDVALUES);
	}

	public void setValidValues(String validvalues)
	{
		setProperty(VALIDVALUES, validvalues);
	}

}
