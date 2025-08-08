/*
 * Created on Aug 30, 2004
 *
 * 
 */
package mojo.km.utilities;

/**
 * @author mpatino
 *
 * This class will provide methods to format data strings coming from or
 * going to a VSAM data source. 
 */
public class LegacyDataFormatUtil
{
	public static String padField(String field, String padChar, int len)
	{
		
		for (int i = 0; i < len; i++)
		{
			//System.out.println("Pad length is: " + len);
			field = field + padChar;
//			System.out.println(
//				"Padding field " + field + "** length is " + field.length());
		}

		return field;
	}

	public static String stripLeadingZeroes(String field, int len){
		String newField = new String();
		System.out.println("######## Field to be stripped = " + field);
		
		int i = field.lastIndexOf("0");
		if(i == -1){
			System.out.println("####### Nothing to strip ");
			return field;
		}
		
		newField = field.substring(i + 1);
		System.out.println("######## Stripped field = " + newField);
		
		return newField;
	}
	public static String padLeading(String field, String padChar, int len)
	{
		String newField = new String();
		for (int i = 0; i < len; i++)
		{
			newField = newField + padChar;
//			System.out.println(
//				"Pad leading "
//					+ newField
//					+ "** length is "
//					+ newField.length());
		}
		return newField + field;
	}

	public static String formatDate(java.util.Date date, String format)
	{
		String stDate = new String();
		if ((format == null) || (format.equals("none")))
		{
			return null;
		}
		if (format.equals("mmddyy"))
		{
			stDate = DateUtil.dateToString(date, "MMddyy");
		}
		else if (format.equals("yymmdd"))
		{
			stDate = DateUtil.dateToString(date, "yyMMdd");
		}
		else if (format.equals("yyyymmdd"))
		{
			stDate = DateUtil.dateToString(date, "yyyyMMdd");
		}
		else if (format.equals("mmddyyyy"))
		{
			stDate = DateUtil.dateToString(date, "MMddyyyy");
		}
		return stDate;
	}

	public static String formatString(
		String field,
		String format,
		String padChar,
		String maxLen)
	{
		if (field == null){
			field="";
		}
		int len = field.length();
		String formattedField = field;

		if ((padChar == null) || (padChar.equals("zeroes")))
		{
			padChar = "0";
		}
		else
		{
			if (padChar.equals("space"))
			{
				padChar = " ";
			}
		}
		int max = Integer.parseInt(maxLen);
		int diff = 0;
		if (len < max)
		{
			diff = max - len;
		}
		else
		{
			return field;
		}

		if (format.equals("padLeading"))
		{
			formattedField = padLeading(field, padChar, diff);
		}
		else if (format.equals("padTrailing"))
		{
			formattedField = padField(field, padChar, diff);
		}

		return formattedField;
	}

}
