/*
 * Created on May 22, 2006
 *
 */
package mojo.km.config;

/**
 * @author Jim Fisher
 *
 */
public class TestClassificationRangeProperties extends GenericProperties
{
	public static final String TYPE = "type";
	public static final String MIN = "min";
	public static final String MAX = "max";
	public static final String FORMAT = "format";
	public static final String DIFFERENCE = "difference";
	public static final String INCREMENT = "increment";
	
	public String getType()
	{
		return this.getProperty(TYPE);
	}
	
	public void setType(String value)
	{
		this.setProperty(TYPE, value);
	}
	
	public String getBegin()
	{
		return this.getProperty(MIN);		
	}
	
	public void setBegin(String value)
	{
		this.setProperty(MIN, value);
	}
	
	public String getEnd()
	{
		return this.getProperty(MAX);
	}
	
	public void setEnd(String value)
	{
		this.setProperty(MAX, value);
	}
	
	public String getFormat()
	{
		return this.getProperty(FORMAT);
	}
	
	public void setFormat(String format)
	{
		this.setProperty(FORMAT, format);
	}
	
	public String getDifference()
	{
		return this.getProperty(DIFFERENCE);
	}
	
	public void setDifference(String value)
	{
		this.setProperty(DIFFERENCE, value);
	}
	
	public String getIncrement()
	{
		return this.getProperty(INCREMENT);
	}
	
	public void setIncrement(String value)
	{
		this.setProperty(INCREMENT, value);
	}
}