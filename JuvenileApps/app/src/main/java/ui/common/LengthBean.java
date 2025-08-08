/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common;

/**
 * @author jfisher
 *
 */
public class LengthBean
{
	public static final int TOTAL_INCHES = 0x01;
	public static final int FEET = 0x02;
	public static final int INCHES = 0x03;

	private Integer length;

	public LengthBean()
	{
	}

	public void setLength(String length) throws NumberFormatException
	{
		this.setLength(length, false);
	}

	public void setLength(String length, boolean legacy) throws NumberFormatException
	{
		if (length != null && length.equals("") == false)
		{
			if (legacy == true)
			{
				this.length = this.computeLegacyInches(length);
			}
			else
			{
				this.length = new Integer(length);
			}
		}
	}

	public void setLength(String feetString, String inchesString) throws NumberFormatException
	{
		if (feetString == null || feetString.trim().equals(""))
		{
			feetString = "0";
		}
		if (inchesString == null || inchesString.trim().equals(""))
		{
			inchesString = "0";
		}

		int feet = Integer.parseInt(feetString);
		int inches = Integer.parseInt(inchesString);
		int totalInches = feet * 12 + inches;
		this.setLength(totalInches);

	}

	public void setLength(int length)
	{
		this.length = new Integer(length);
	}

	public void setLength(Integer length)
	{
		this.length = length;
	}

	public Integer getTotalInches()
	{
		return this.length;
	}

	/**
	 * @return
	 */
	public Integer getFeet()
	{
		Integer feetInteger = null;
		if (this.length != null)
		{
			int totalInches = this.length.intValue();
			int feet = totalInches / 12;
			feetInteger = new Integer(feet);
		}
		return feetInteger;
	}

	public Integer getInches()
	{
		Integer inchesInteger = null;
		if (this.length != null)
		{
			int totalInches = this.length.intValue();
			int feet = totalInches / 12;
			int inches = totalInches - (feet * 12);
			inchesInteger = new Integer(inches);
		}
		return inchesInteger;
	}

	/**
	 * Convert legacy FEET and INCHES format (3 character) to Integer inches
	 * @param length
	 * @return
	 */
	private Integer computeLegacyInches(String length)
	{
		Integer inches = null;
		if (length != null && length.length() == 3)
		{
			int feetInt = Integer.parseInt(length.substring(0, 1));
			int inchesInt = Integer.parseInt(length.substring(1, 3));

			inches = new Integer(feetInt * 12 + inchesInt);
		}
		return inches;
	}

	/**
	 * @return
	 */
	public String getFeetString()
	{
		String feetString = null;
		Integer feet = this.getFeet();
		if (feet != null)
		{
			feetString = feet.toString();
		}
		return feetString;
	}

	/**
	 * @return
	 */
	public String getInchesString()
	{
		String inchesString = null;
		Integer inches = this.getInches();
		if (inches != null)
		{
			inchesString = inches.toString();
		}
		return inchesString;
	}

	/**
	 * @return
	 */
	public String getTotalInchesString()
	{
		String inchesString = null;
		Integer inches = this.getTotalInches();
		if (inches != null)
		{
			inchesString = inches.toString();
		}
		return inchesString;
	}
}
