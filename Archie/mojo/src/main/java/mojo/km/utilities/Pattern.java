/* mojo.km.utilities.Pattern  */

package mojo.km.utilities;

/*
 * Class that will ask as a stand-in until JDK 1.4 is used.
 * The implementation of matches() within this class is
 * (extremely) limited to the following patterns:
 *     pattern               meaning
 *      *foo       CharSequence must end in "foo"
 *       foo*      CharSequence must start with "foo"
 *      *foo*      CharSequence must contain "foo"
 * -------------------------------------------------------------
 * NOTE: for now, a String will be used for the second argument
 *       to matches as opposed to CharSequence, an interface
 *       defined as shown below:
 * -------------------------------------------------------------
 * interface CharSequence()
 * {
 *     public int length();
 *     public char charAt(int index);
 *     public CharSequence subSequence(int start, int end);
 *     public String toString();
 *  }
 * @modelguid {4BF7B856-B365-4EC7-997F-DFDB105FEEEE}
 */

public class Pattern
{
    // public static boolean matches( String regex, CharSequence input )
	/** @modelguid {A3290DBB-2F70-4EFF-94A3-1FF28DBA1559} */
    public static boolean matches( String regex, String input )
    {
        if( (regex!= null) && (input != null) )
        {
            if( regex.equals("*") ) {
                return true;
            }

            boolean endsWithSplat = regex.endsWith("*");
            if( endsWithSplat ) {
                regex = regex.substring( 0, regex.length()-1 );
            }

            boolean startsWithSplat = regex.startsWith("*");
            if( startsWithSplat ) {
                regex = regex.substring( 1 );
            }

            if( endsWithSplat && startsWithSplat )
            {
                return (input.indexOf(regex) >= 0);
            }
            else if( endsWithSplat )
            {
                return input.startsWith(regex);
            }
            else if( startsWithSplat )
            {
                return input.endsWith(regex);
            }
            else {  // the pattern contains no asterisks
                return input.equals(regex);
            }
        }
        return false;
    }

	/** @modelguid {0880F5A9-A0A0-4BC9-88E5-30DC40A5FC24} */
    final private static String usage = "usage: java Pattern regex input";

	/** @modelguid {F97BFDAB-E3BB-413E-B6EB-F4A16E061F73} */
    static public void main( String[] args )
    {
        if( args.length != 2 ) {
            System.err.println( usage );
        }
        else {
            String regex = args[0];
            if( regex.equals( "\"*\"" ) || regex.equals( "'*'" ) ) {
                regex = "*";
            }
            String input = args[1];
            System.out.println( input + " matches " + regex + " = " +
                                Pattern.matches( regex, input ) );
        }
    }
 }


