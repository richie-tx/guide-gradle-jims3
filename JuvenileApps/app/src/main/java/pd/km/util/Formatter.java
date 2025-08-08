/*
 * Created on Jan 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.km.util;

/**
 *  Utility class to help with formatting strings
 */
public final class Formatter
{
	
	private Formatter() {}

	/**
	 * This method will pad a passed string with spaces.  The string
	 * will be truncated if the pad width is less than the string
	 * length else the pad width less the string length number of 
	 * spaces will be added to the end of the string and returned
	 * @param string
	 * @param width
	 * @return String pad
	 */
	public static String pad(String stringToPad, int width, boolean prePad) {
		return Formatter.pad(stringToPad, width,' ', prePad);
	}
	
	/**
	 * This method will pre or post pad a string with a specific char as the padding.
	 * A boolean indicates if the pad is pre or post pad.  Pre pad inserts the char
	 * before the string to pad, a post pad appends the char.
	 *
	 * @param stringToPad
	 * @param width
	 * @param padChar
	 * @param prePad
	 * @return String pad
	 */
	public static String pad(String stringToPad, int width, char padChar, boolean prePad)
	{
		if (stringToPad == null)
		{
			return stringToPad;
		}
		if (stringToPad.length() >= width) {
		  return stringToPad.substring(0, width);
		}	
		
		StringBuffer output = new StringBuffer(stringToPad);
		for (int i=0; i < (width - stringToPad.length()); i++) {
			if (prePad)
			{
				output.insert(i, padChar);
			} else {
				output.append(padChar);
			}
		}

		return output.toString();	
	}
}
