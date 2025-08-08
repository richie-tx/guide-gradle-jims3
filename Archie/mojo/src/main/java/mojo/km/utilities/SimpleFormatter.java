/* mojo.km.utilities.SimpleFormatter.java */

package mojo.km.utilities;

/* Class whose purpose is to transform values into a String based
 * on a provided format specification, where
 * Format specification = [+][0][width][.precision][*mult]type[:fmt]
 * where +         = prefix with +/- sign iff type is numeric (d or f)
 *   and 0         = leading zeroes iff type is numeric (d or f)
 *   and witdh     = final width of string regardless of decimal places
 *   and precision = number of decimal places when type is f
 *   and mult      = multiplier iff type is numeric (d or f)
 *   and fmt       = format specification for complex type, e.g., date
 * NOTE: A common use of multiplier is to expand a float's value
 *       prior to it being specified as an int. The code below is
 *       careful to check whether the passed-in object's type, Integer
 *       versus Float and whether or not a multiplier has been specified.
 *       If a Float is passed in, no multiplier is specified, and the
 *       type is "d", the Float's value will be rounded down to the
 *       nearest int. The same holds when a multiplier is specified,
 *       with the exception that the multiplier is applied against the
 *       Float prior to downward rounding (if any).
 * Currently supported types:
 * 1) s    = String
 * 2) d    = Integer
 * 3) f    = Float
 * 4) date = Date
 * 5) b    = boolean
 * NOTE: Strings are always left-justified. Numerics are always
 *       right-justified within the width. Truncation always
 *       occurs on the right.
*/
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;
import java.text.FieldPosition;
import java.math.BigDecimal;

/** @modelguid {87EB6F9B-E83E-4AFF-A507-DBD1031FCDAC} */
public class SimpleFormatter
{
	/** @modelguid {BFAD14B5-3BB8-4E7F-9B5D-879BF3718B8E} */
    static private String usage = 
         "usage = SimpleFormatter fmtString stringToFormat [dateStringFmt]\n" +
         "        where for date formatting, fmtString and dateStringFmt must be\n" +
         "        valid SimpleDateFormat specifications, stringToFormat must\n" +
         "        match the dateStringFmt, and fmtString must begin with 'date:'.\n" +
         "    EX: SimpleFormatter date:yyyy-MM-dd 12/01/2001 MM/dd/yyyy";

	/** @modelguid {16FA2423-6EDE-42E8-B54B-4B66CD566E7A} */
    final static public int TYPE_UNKNOWN = -1; 
	/** @modelguid {84CA9719-ED00-447B-A1CC-47F9E7DB34F8} */
    final static public int TYPE_DATE     = 0; // order matches types[]
	/** @modelguid {94394C59-D032-4974-AF1E-CEF04CA6A79B} */
    final static public int TYPE_INT      = 1;
	/** @modelguid {C1D27246-2644-4C14-A01B-7984CC73D289} */
    final static public int TYPE_STRING   = 2;
	/** @modelguid {53852FFB-7C18-42A6-B3D1-39C366691313} */
    final static public int TYPE_FLOAT    = 3;
	/** @modelguid {14BA8FE4-2E09-4573-82E8-BA3C4C2324A9} */
    final static public int TYPE_BOOLEAN  = 4;

	/** @modelguid {4404D9D8-B447-4606-8513-F8171A9032C9} */
    final static public int DAY_MILLIS = 24*60*60*1000;

	/** @modelguid {227DD68D-0D6B-4BFF-9459-D3202DCFD748} */
    final static private String[] types = { "date", "d", "s", "f", "b" };
     
	/** @modelguid {0908BA70-A36B-43FD-BDA6-A371E4D2097B} */
    final static private int STATE_WIDTH     = 1; 
	/** @modelguid {7ECB8EC5-A6D9-4655-96A8-5E893244B214} */
    final static private int STATE_PRECISION = 2;
	/** @modelguid {E7B19C67-7D39-4FCA-B0C6-E56BE7A3BB1A} */
    final static private int STATE_MULT      = 3;

	/** @modelguid {5DF86688-67BD-4E60-B573-59546EA3FCD1} */
    final static public String NOW       = "now";
	/** @modelguid {73A582F9-1706-4E04-A727-055AD6005A6B} */
    final static public String YESTERDAY = "yesterday";
	/** @modelguid {E14BC3EA-2F26-4B2B-ACFC-862115117402} */
    final static public String TOMORROW  = "tomorrow";

	/** @modelguid {F036D678-816E-4877-9983-243EF4D64A41} */
    final static private BigDecimal ONE = new BigDecimal(1.0d);;

	/** @modelguid {3C08D699-AB04-46C4-8170-D04D88383591} */
    private static boolean dbg = false;

	/** @modelguid {8E82499B-1FCA-402B-9E5A-97D18633D0F3} */
    public static Date stringToDate( String stringToFormat, String dateStringFmt )
    {
        if( dateStringFmt.startsWith("date:") ) {
             dateStringFmt = dateStringFmt.substring(5);
        }
        if(dbg) {
            System.out.println( "stringToDate: stringToFormat = " + stringToFormat );
            System.out.println( "stringToDate: dateStringFmt  = " + dateStringFmt  );
        }
        SimpleDateFormat sdf = new SimpleDateFormat( dateStringFmt, Locale.ENGLISH );
        ParsePosition    pos = new ParsePosition(0);
        Date date = sdf.parse( stringToFormat, pos );
        if(dbg) System.out.println( "stringToDate: returning date = " + date );
        return date;
    }

	/** @modelguid {AB66D9A3-D79B-45EF-BBAC-F98C1FA18863} */
    public static String dateToString( Date date, String fmtString )
    {
        if( fmtString.startsWith("date:") ) {
             fmtString = fmtString.substring(5);
        }
        if(dbg) {
            System.out.println( "dateToString: date = " + date );
            System.out.println( "dateToString: fmtString = " + fmtString );
        }
        SimpleDateFormat     sdf = new SimpleDateFormat( fmtString, Locale.ENGLISH );
        FieldPosition  yearField = new FieldPosition( DateFormat.YEAR_FIELD );
        StringBuffer appendToBuf = new StringBuffer();
        return sdf.format( date, appendToBuf, yearField ).toString();
    }

	/** @modelguid {3B2BE4DA-E7EE-4A84-BB65-ACFC8F42DE02} */
    static public String format( String fmtString, 
                                 Object objectToFormat )
    {
        return format( fmtString, objectToFormat, null, null );
    }

	/** @modelguid {DADF8F63-AD17-405B-9758-5ECE3804497C} */
    static public String format( String fmtString, 
                                 String stringToFormat,
                                 String dateStringFmt )
    {
        return format( fmtString, null, stringToFormat, dateStringFmt );
    }

    /** Method 
     * @modelguid {2A142D5A-324D-4ADB-AEAC-FDE16D1042C5}
     */
    public static Object getInstanceOfType( String fmtString, String value )
    {
       try {
           switch( getType(fmtString) ) {
           case TYPE_DATE:
               if( value != null ) {
                   if( value.equals(NOW) ) {
                       return new Date();
                   }
                   else if( value.equals(YESTERDAY) ) {
                       return new Date( System.currentTimeMillis()-DAY_MILLIS );
                   }
                   else if( value.equals(TOMORROW) ) {
                       return new Date( System.currentTimeMillis()+DAY_MILLIS );
                   }
                   else {
                       return stringToDate( value, fmtString );
                   }                  
               }
               break;
           case TYPE_INT:
               return new Integer(value);
           case TYPE_STRING:
               return value;
           case TYPE_FLOAT:
               return new Float(value);
           case TYPE_BOOLEAN:
               if( "false".equals(value) || "0".equals(value) ) {
                   return Boolean.FALSE;
               }
               return Boolean.TRUE;
           }
       }
       catch( Exception e ) {
           System.err.println( "[SimpleFormatter.getInstanceOfType] err = " + e );
       }
       return null;
    }

     //=============================================================
     //                      PRIVATE METHODS
     //=============================================================

	/** @modelguid {4EF26F73-DC60-47BB-8B0D-C3D1842F7604} */
     static private String format( String  fmtString, 
                                   Object  objectToFormat,
                                   String  stringToFormat,
                                   String  dateStringFmt )
    {
        String ret = "???";

        if( fmtString == null ) {
             System.err.println( "fmtString cannot be null" );
             return ret;
        }

        if( (objectToFormat == null) && (stringToFormat == null) ) {
             System.err.println( "format: both objectToFormat and stringToFormat are null" );
             return ret;
        }

        int type = getType(fmtString);
        if( type == TYPE_UNKNOWN ) {
            if(dbg) System.out.println( "format: unrecognized type for fmt = " + fmtString );
            return ret;
        }
        if(dbg) System.out.println( "type = " + type );

        boolean formatObject = (objectToFormat != null);
        if(dbg) System.out.println( "formatObject = " + formatObject );

        boolean typeIsNumeric = ((type == TYPE_INT)||(type == TYPE_FLOAT));
        if( formatObject && typeIsNumeric )
        {
            if( !(objectToFormat instanceof Number) )
            {
                boolean isBad = true;
                // Try to convert a String to a BigDecimal. Downstream, type == TYPE_INT
                // will accept BigDecimal. Suppose a multiplier has been specified?
                if( objectToFormat instanceof String ) {
                    try {
                        objectToFormat = new BigDecimal((String)objectToFormat);
                        isBad = false;
                    }
                    catch( NumberFormatException nfe ) {
                        isBad = true;
                    }
                }
                if( isBad ) {
                    System.err.println( "format: numeric type yet objectToFormat not Number" );
                    return ret;
                }
            }
            else if( !(objectToFormat instanceof BigDecimal) ) {
                double val = ((Number)objectToFormat).doubleValue();
                objectToFormat = new BigDecimal(val);
            }
        }
        //-------------------------------------------------
        // Dates are a special case: width does not apply 
        // except as described by the fmtString's own width.
        //-------------------------------------------------
        if( type == TYPE_DATE ) {
            if(dbg) {
                System.out.println( "type is DATE, fmtString     = [" + fmtString + "]");
                System.out.println( "type is DATE, dateStringFmt = [" + dateStringFmt + "]");
            }
            try {
                Date date = (formatObject ? (Date)objectToFormat
                            : stringToDate( stringToFormat, dateStringFmt ));
                fmtString = fmtString.substring( types[TYPE_DATE].length()+1 );
                if(dbg) {
                    System.out.println( "fmtString now = [" + fmtString + "]");
                    System.out.println( "Will format date = " + date );
                }
                ret = dateToString( date, fmtString );
                if(dbg) System.out.println( "date ret = " + ret );
            }
            catch( Exception e ) {
                if( formatObject ) {
                    System.err.println( "Bad date info:"
                                      + "\nfmtString      = " + fmtString
                                      + "\nobjectToFormat = " + objectToFormat );
                }
                else {
                    System.err.println( "Bad date info:"
                                      + "\nfmtString      = " + fmtString
                                      + "\nstringToFormat = " + stringToFormat
                                      + "\ndateStringFmt  = " + dateStringFmt );
                }
            }
            if(dbg) System.out.println( "exiting TYPE_DATE, ret = " + ret );
            return ret;
        }

        //-------------------------------------------------
        // Now concentrate on Strings, ints, and floats.
        // Since the type specified is a single character,
        // [s|d|f], we can remove it from the end.
        //-------------------------------------------------
        fmtString = fmtString.substring( 0, fmtString.length()-1 );

        boolean wasPlusPreceded     = false;
        boolean wasZeroPreceded     = false;
        boolean precisionSpecified  = false;
        boolean multiplierSpecified = false;

        StringBuffer widthSpec  = null;
        StringBuffer precision  = null;
        String       multiplier = null;

        int state = STATE_WIDTH;

        if(dbg) System.out.println( "fmtString = " + fmtString);
        int len = fmtString.length();
        if(dbg) System.out.println( "fmtString len = " + len);

        for( int i = 0; i < len; i++  ) 
        {
            char c = fmtString.charAt(i);
            if(dbg) System.out.println( "i = " + i + ", c = " + c );

            if( (i == 0) && (c == '+') ) {
                wasPlusPreceded = true;
                if(dbg) System.out.println( "wasPlusPreceded = true" );
            }
            else if( (i == 0) && (c == '0') ) {
                wasZeroPreceded = true;
                if(dbg) System.out.println( "wasZeroPreceded = true" );
            }
            else if( wasPlusPreceded && (i == 1) && (c == '0') ) {
                wasZeroPreceded = true;
                if(dbg) System.out.println( "wasZeroPreceded = true" );
            }
            else if( Character.isDigit(c) ) {
                if( state == STATE_WIDTH ) {
                    if( widthSpec == null ) widthSpec = new StringBuffer();
                    widthSpec.append(c);
                }
                else if( state == STATE_PRECISION ) {
                    if( precision == null ) precision = new StringBuffer();
                    precision.append(c);
                    if(dbg) System.out.println( "precision now = " + precision );
                }
                else if( state == STATE_MULT ) {
                    // If the type is numeric and we have encountered a multiplier
                    // the rest of the fmtString must be the multipler.
                    multiplier = fmtString.substring(i);
                    if(dbg) System.out.println( "multiplier = " + multiplier );
                    break;
                }
            }
            else if( (c == '.') && typeIsNumeric ) {
                state = STATE_PRECISION;
                precisionSpecified = true;
                if(dbg) System.out.println( "precisionSpecified = true...");
            }
            else if( (c == '*') && typeIsNumeric ) {
                state = STATE_MULT;
                multiplierSpecified = true;
                if(dbg) System.out.println( "multiplierSpecified = true...");
            }
        }

        //-------------------------------------------------
        //  Apply the parsed formatting information. 
        //-------------------------------------------------
        if(dbg) System.out.println( "format: made it type..." );
        boolean widthSpecified = (widthSpec != null) && (widthSpec.length() > 0);
        int width = -1;
        if( widthSpecified ) {
            try {
                width = Integer.parseInt(widthSpec.toString());
                if(dbg) System.out.println( "width = " + width);
                if( wasPlusPreceded ) {
                    width--;
                }
            }
            catch( Exception e ) {
                System.err.println( "Bad width = " + widthSpec );
                widthSpecified = false;
            }
        }

        String sign = "+";
        BigDecimal factor = ONE;
        if( multiplierSpecified ) {
            factor = new BigDecimal(multiplier);
        }
        if(dbg) System.out.println( "factor = " + factor );

        switch( type ) {
        case TYPE_BOOLEAN:
            // should we return "1" or "true"?
            ret = getInstanceOfType( fmtString, stringToFormat ).toString();
            break;
        case TYPE_INT:
            long longVal = -1;
            if(dbg) System.out.println( "processing TYPE_INT" );
            try {
                if( formatObject ) {
                    //Assert: objectToFormat instanceof BigDecimal (see above)
                    BigDecimal num = (BigDecimal)objectToFormat;
                    if( multiplierSpecified ) {
                        num = num.multiply(factor);
                    }
                    longVal = num.longValue();
                    if(dbg) System.out.println( "longVal = " + longVal );
                }
                else {
                    longVal = Long.parseLong(stringToFormat);
                }
            }
            catch( Exception e ) {
                System.err.println( "Bad int = " + (formatObject ? stringToFormat 
                                                                 : objectToFormat) );
                break;
            }
            if( wasPlusPreceded && (longVal < 0) ) {
                longVal = Math.abs(longVal);
                sign = "-";
            }

            if(dbg) System.out.println( "format: longVal = " + longVal );
            if( widthSpecified && (width >= 0) )
            {
                if(dbg) System.out.println( "format: non-zero width = " + width );
                String strRep = new Long(longVal).toString();
                StringBuffer buf = new StringBuffer(strRep);

                for( int j = 0; (j < width) && (buf.length() < width); j++ ) 
                {
                    char c = (wasZeroPreceded ? '0' : ' ');
                    // if(dbg) System.out.println( "format: c = " + c );
                    buf.insert( 0, c );
                }
                if(dbg) System.out.println( "format: after prepending buf = " + buf );
                if( buf.length() > width ) {
                    buf.setLength( width );
                }
                ret = buf.toString();
            }
            else {
                if(dbg) System.out.println( "format: no width specified for intVal.." );
                ret = stringToFormat;
            }
            break;
        case TYPE_STRING: 
            String str = stringToFormat;
            if( formatObject ) {
                try {
                    str = (String)objectToFormat;
                }
                catch( ClassCastException e ) {
                    System.err.println( "Object: " + objectToFormat + 
                            " is not a String yet fmt = " + fmtString );
                    break;
                }
            }    
            if( widthSpecified )
            {
                StringBuffer buf = new StringBuffer(str);
                for( int j = 0; (j < width) && (buf.length() < width); j++ ) {
                    buf.append( ' ' );
                }
                if( buf.length() > width ) {
                    buf.setLength( width );
                }
                ret = buf.toString();
            }
            else {
                ret = str;
            }
            break;
        case TYPE_FLOAT:
            BigDecimal bigDecimalVal = ONE;
            try {
                if( formatObject ) {
                    //Assert: objectToFormat instanceof BigDecimal (see above)
                    BigDecimal num = (BigDecimal)objectToFormat;
                    if( multiplierSpecified ) {
                        num = num.multiply(factor);
                    }
                    bigDecimalVal = num;
                }
                else {
                    bigDecimalVal = new BigDecimal(stringToFormat);
                }
            }
            catch( Exception e ) {
                System.err.println( "Bad float = " + (formatObject ? stringToFormat 
                                                                   : objectToFormat) );
                break;
            }
            if( wasPlusPreceded && (bigDecimalVal.doubleValue() < 0) ) {
                bigDecimalVal = bigDecimalVal.abs();
                sign = "-";
            }
            if(dbg) System.out.println( "initial bigDecimalVal = " + bigDecimalVal );

            // limit or enforce the number of decimal places
            int precLen = 0;
            if(dbg) {
                System.out.println( "precisionSpecified = " + precisionSpecified );
                System.out.println( "precision          = " + precision          );
            }

            if( precisionSpecified && (precision != null) 
                                   && (precision.length() > 0) ) 
            {
                try {
                    precLen = Integer.parseInt(precision.toString());
                    if(dbg) System.out.println( "precLen = " + precLen );
                }
                catch( Exception e ) {
                    System.err.println( "Bad precision = " + precision );
                    break;
                }
            }

            // Important float/decimal value cleanup occurs below:
            bigDecimalVal = bigDecimalVal.divide(ONE, precLen, BigDecimal.ROUND_HALF_UP);

            String strRep = bigDecimalVal.toString();
            StringBuffer buf = new StringBuffer(strRep);
            int periodPos = buf.toString().indexOf('.');  // English Locale?
            int truncLen = 0;
            if( periodPos >= 0 ) {
				truncLen = periodPos + precLen + 1;       // extra 1 = period
				if(dbg) {
					System.out.println( "buf before = [" + buf + "]");
					System.out.println( "periodPos  = " + periodPos );
					System.out.println( "truncLen   = " + truncLen  );
				}
				buf.setLength(truncLen);
				if(dbg) System.out.println( "truncated buf = [" + buf + "]");
            }

            // Append zeros... (does this work?)
            // StringBuffer.setLength() appends ASCII 0's when length is
            // longer than the original string.
            while( buf.length() < truncLen ) {
                buf.append( '0' );
            }

            // replace ASCII zeros or spaces with ASCII zeros = 48
            periodPos = buf.toString().indexOf('.');  // English Locale?
            truncLen  = buf.length();

            if(dbg) {
                System.out.println( "0-appended buf before = [" + buf + "]");
                System.out.println( "periodPos  = " + periodPos );
                System.out.println( "truncLen   = " + truncLen  );
            }
            for( int n = periodPos+1; n < truncLen; n++ ) {
                char c = buf.charAt(n);
                if(dbg) System.out.println( "c = [" + c + "] = " + (int)c );
                if( (c == 0) || (c == ' ') ) {
                    buf.setCharAt(n,'0');
                    if(dbg) System.out.println( "c replaced by 0..." );
                }
            }
            if(dbg) System.out.println( "0-appended buf after = [" + buf + "]");

            if( widthSpecified && (width >= 0) )
            {
                for( int j = 0; (j < width) && (buf.length() < width); j++ ) {
                    char c = (wasZeroPreceded ? '0' : ' ');
                    buf.insert( 0, c );
                }
                if(dbg) System.out.println( "width-expanded buf = [" + buf + "]");

                if( buf.length() > width ) {
                    buf.setLength( width );
                }
                if(dbg) System.out.println( "width-limited buf = [" + buf + "]");
            }

            ret = buf.toString();
            break;  // TYPE_FLOAT
        }

        if(dbg) System.out.println( "Exiting format()..." );
       
        return (wasPlusPreceded ? sign + ret : ret);
    }

	/** @modelguid {823C1FDB-EE0E-4919-968B-20DD94250682} */
    public static int getType( String format )
    {
       if( format.startsWith("date") ) {
           return TYPE_DATE;
       }
       else if( format.endsWith("d") ) {
           return TYPE_INT;
       } 
       else if( format.endsWith("s") ) {
           return TYPE_STRING;
       } 
       else if( format.endsWith("f") ) {
           return TYPE_FLOAT;
       }
       else if( format.equals("b") ) {
           return TYPE_BOOLEAN;
       }
       return TYPE_UNKNOWN;
    }

    /*
    static public void main( String[] args )
    {
        if( args.length < 2 ) {
            System.err.println( usage );
        }
        else {
            String dateStringFmt = (args.length == 3 ? args[2] : null );
            String s = SimpleFormatter.format( args[0], args[1], dateStringFmt );
            System.out.println( "out = [" + s + "]" );
        }
    }
	 * @modelguid {AFA35D00-7CD3-4AD5-AD83-6EA6C39770EA}
    */

    static public void main( String[] args )
    {
        // unit testing stuff
        // Object objectToFormat = "12345.325";
        // String format = "012*1000d";

        Object objectToFormat = "111111111.11";
        String format = "+015.2f";

        System.out.println( "format = " + format );
        System.out.println( "value  = " + objectToFormat );
        String s = SimpleFormatter.format( format, objectToFormat );
        System.out.println( "out = [" + s + "]" );

        // objectToFormat = new Float("12345.325");
        objectToFormat = new Float("111111111.11");
        System.out.println( "value  = " + objectToFormat );
        s = SimpleFormatter.format( format, objectToFormat );
        System.out.println( "out = [" + s + "]" );

        // objectToFormat = new Double("12345.325");
        objectToFormat = new Double("111111111.11");
        System.out.println( "value  = " + objectToFormat );
        s = SimpleFormatter.format( format, objectToFormat );
        System.out.println( "out = [" + s + "]" );

        objectToFormat = new BigDecimal(((Number)objectToFormat).doubleValue());
        System.out.println( "value  = " + objectToFormat );
        s = SimpleFormatter.format( format, objectToFormat );
        System.out.println( "out = [" + s + "]" );
    }
}
