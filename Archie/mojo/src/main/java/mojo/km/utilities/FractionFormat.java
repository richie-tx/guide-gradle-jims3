package mojo.km.utilities;

import java.text.ParsePosition;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.util.Vector;
import java.io.Serializable;

/**
 * This specialization of NumberFormat supports the formatting and parsing
 * of fraction numbers in the format "W N/D" where "W" is the whole part,
 * "N" is the numerator, and "D" is the denominator.
 *
 * There may be a leading sign.
 * @author Mike Gavaghan
 * @see NumberFormatSpinner
 * @modelguid {5C1883CE-A589-41A0-980B-C62D77DB42BE}
 */
public class FractionFormat extends NumberFormat implements Serializable
{
    /**
     * Maximum value for denominator.
     * @modelguid {053D1D72-7CDC-4503-98BE-968C23E885B9}
     */
    private short   mMaxDenominator = 256;

    /**
     * Flag if numerator and denominator may be reduced on output
     * @modelguid {01A56C06-EC05-454A-9103-247351E012FC}
     */
    private boolean mReduce         = false;

    /**
     * Flag if a leading zero should be included for value in the
     * range 0 < value < 1.
     * @modelguid {F66C77CF-F907-46E9-A8F1-23E33DF8CE3F}
     */
    private boolean mShowZero       = false;

    /**
     * Flag if a leading sign should always appear.
     * @modelguid {48325B8C-AAD2-4DCA-BB55-8E06EC302CB2}
     */
    private boolean mShowSign       = false;
    
    /**
     * Flag if a separating space should always be inserted even if
     * it isn;t necessary
     * @modelguid {E125FD62-0EE4-4EE9-BD0F-C5D3818BBD0A}
     */
    private boolean mInsertSpace    = false;

    /**
     * The list of prime factors for this denominator.
     * @modelguid {6ECA5260-7A76-4D1E-9D6B-4BAACB2502B6}
     */
    private short[]   mFactors      = null;
    
    /**
     * The list of all possible prime factors for any short integer.
     * @modelguid {2939D6E1-CEF5-4498-9F45-131BFBC6CAE9}
     */
    static private short[] MFactors;
    
    //***** populate MFactors
    static
    {
        Vector factors = new Vector(100);
        short  max     = (short) Math.ceil(Math.sqrt(Short.MAX_VALUE));
        
        factors.add( new Short((short)2) );
        
        //***** build list of factors
        for (int test = 3; test <= max; test++)
        {
            boolean flunked = false;
            
            for (int i = 0; i < factors.size(); i++)
            {
                if (test % ((Short) factors.elementAt(i)).intValue() == 0)
                {
                    flunked = true;
                    break;
                }
            }
            
            if (!flunked)
            {
                factors.add( new Short( (short) test ) );
            }
        }
        
        //***** reduce list to populate MFactors
        MFactors = new short[factors.size()];
        
        for (int i = 0; i < MFactors.length; i++)
        {
            MFactors[i] = ((Short) factors.elementAt(i)).shortValue();
        }
        
        factors = null;
    }
    
    /**
     * Default constructor using the following parameters: max denominator = 256,
     * reduce flag = true, zero flag = false, sign flag = false;
     * @modelguid {15628120-21CC-4C4C-BED0-14611A5E383C}
     */
    public FractionFormat()
    {
        setMaxDenominator( (short) 256, true );
        mShowZero    = false;
        mShowSign    = false;
        mInsertSpace = false;
    }
    
    /**
     * Constructor to specify all parameters
     * @param maxDenominator Set the maximum allowable denominator.
     * @param reduce Indicate if maxDenominator may be reduced if possible.
     * @param showZero Indicate if a leading zero should appear in front of fractional values that are less than 1.
     * @param showSign Indicate that a leading "+" should appear before all positive values.
     * @modelguid {34ABAF1F-6310-4C70-9B3B-273C285C9BA5}
     */
    //***** constructor
    public FractionFormat( short   maxDenominator, 
                           boolean reduce, 
                           boolean showZero, 
                           boolean showSign )
    {
        setMaxDenominator( maxDenominator, reduce );
        mShowZero = showZero;
        mShowSign = showSign;
    }
    
    /**
     * Set a flag indicating whether a leading zero should be displayed
     * before fractional values that are less than 1.
     * 
     * @param showZero  If true, a leading zero will appear as necessary.
     * @modelguid {52ABFFFE-BC31-4C74-9D95-50CBF2FCA354}
     */
    //***** set show zero
    public void setShowZero( boolean showZero )
    {
        mShowZero = showZero;
    }
    
    /**
     * Set a flag indicating whether a "+" or "-" should always appear.
     * 
     * @param showSign If true, a leading sign will always appear.
     * @modelguid {F493D88C-924B-4017-B8D8-97B666F48420}
     */
    public void setShowSign( boolean showSign )
    {
        mShowSign = showSign;
    }
    
    /**
     * Set a flag indicating whether a space separator shoudl be included
     * even if it isn;t necessary.
     * 
     * @param insertSpace  If true, a separator will always be added.
     * @modelguid {3127BE1C-EA82-4234-88B8-61039285BF09}
     */
    //***** set insert space
    public void setInsertSpace( boolean insertSpace )
    {
        mInsertSpace = insertSpace;
    }
    
    /**
     * Set the maximum denominator to use, and indicate if the formatter
     * should attempt to reduce fractions.
     * 
     * @param maxDenominator Set the maximum allowable denominator.
     * @param reduce Indicate if maxDenominator may be reduced if possible.
     * @modelguid {277AAFEF-EF71-472B-B78E-A5357902C3B0}
     */
    //***** set whether fraction should be reduced
    public void setMaxDenominator( short maxDenominator, boolean reduce )
    {
        if (maxDenominator < 2)
        {
            throw new IllegalArgumentException("Maximum denominator must be greater than 1");
        }
        
        mMaxDenominator = (short) Math.abs(maxDenominator);
        mReduce         = reduce;
        
        if (mReduce)
        {
            Vector factors = new Vector(100);
            short  max     = (short) Math.ceil(Math.sqrt(mMaxDenominator));
            short  current = mMaxDenominator;
            
            //***** build list of factors
            for (int testIndex = 0; testIndex < MFactors.length; testIndex++)
            {
                short test = MFactors[testIndex];
                
                if (Math.sqrt(current) < test)  break;
                
                //***** reduce on this factor
                while ((current % test) == 0)
                {
                    factors.add( new Short(test) );
                    current /= test;
                }
            }
            
            //***** reduce list to populate MFactors
            mFactors = new short[factors.size()];
            
            for (int i = 0; i < mFactors.length; i++)
            {
                mFactors[i] = ((Short) factors.elementAt(i)).shortValue();
            }
        }
    }
    
    /**
     * Return the show sign flag.
     * @modelguid {692992E3-65C6-4E42-8D86-4B61B9035A49}
     */
    public boolean getShowSign()
    {
        return mShowSign;
    }
    
    /**
     * Return the show zero flag.
     * @modelguid {D6533F26-8B77-4D4C-9FBC-29DCD11E41DD}
     */
    public boolean getShowZero()
    {
        return mShowZero;
    }
    
    /**
     * Return the insert space
     * @modelguid {5CE9AEBB-A078-4E07-AF9F-FD67DCD35514}
     */
    public boolean getInsertSpace()
    {
        return mInsertSpace;
    }
    
    /**
     * Return the maximum integer.
     * @modelguid {9BE11A08-544E-4DB1-BEFA-6E3E5173940B}
     */
    public short getMaxDenominator()
    {
        return mMaxDenominator;
    }
    
    //***** format a fractional portional


	/** @modelguid {B4E7569C-B0EF-40B0-AD36-C4C6890B5037} */
    private String formatFraction( double number )
    {
        int num;
        int den = mMaxDenominator;
        
        //***** check range
        if ((number < 0.0) || (number >= 1.0))
        {
            throw new IllegalArgumentException("Argument out of range [0,1)");
        }
        
        //***** calulcate nearest numerator
        num = (int) Math.round(number * mMaxDenominator);
        
        //***** see if we can (or need to) reduce
        if (mReduce)
        {
            //***** special case of zero
            if (num == 0)
            {
                den = 0;
            }
            
            //***** start reducing
            else
            {
                int lastFactor = -1;
                
                for (int i = 0; i < mFactors.length; i++)
                {
                    int factor = mFactors[i];
                    
                    if (num < factor)  break;
                    
                    if (factor != lastFactor)
                    {
                        //***** reduce if possible
                        if ((num % factor) == 0)
                        {
                            num /= factor;
                            den /= factor;
                            lastFactor = -1;
                        }
                        else
                        {
                            lastFactor = factor;
                        }
                    }
                }//loop over factors
            }//if non-zero
        }//if we reduce
        
        return (den != 0) ? Integer.toString(num) + "/" + Integer.toString(den) : "";
    }

    /**
     * Format an integer according to the specified options.
     * 
     * @param number The integer to format.
     * @param toAppendTo StringBuffer to append the formatted data to.
     * @param pos FieldPosition to populate during formatting.
     * @return toAppendTo
     * @modelguid {AAB680B2-D4EE-4C47-A2FF-2382B7C92E19}
     */
    public StringBuffer format( long number, StringBuffer toAppendTo, FieldPosition pos )
    {
        String wholePart;
        String sign;
        
        wholePart = Long.toString(number);
        
        //***** handle sign
        if (mShowSign && (number > 0))
        {
            wholePart = "+" + wholePart;
        }
        
        //***** set field position
        if (pos.getField() == NumberFormat.INTEGER_FIELD)
        {
            pos.setBeginIndex(toAppendTo.length());
            pos.setEndIndex(toAppendTo.length() + wholePart.length());
        }
        else if (pos.getField() == NumberFormat.FRACTION_FIELD)
        {
            pos.setBeginIndex(toAppendTo.length()+wholePart.length()+1);
            pos.setEndIndex(toAppendTo.length()+wholePart.length()+1);
        }
        
        toAppendTo.append(wholePart);
        
        //***** add trailing space if required
        if (mInsertSpace)
        {
            toAppendTo.append(" ");
        }
        
        return toAppendTo;
    }
    
    /**
     * Format a floating point value according to the specified options.
     * 
     * @param number The value to format.
     * @param toAppendTo StringBuffer to append the formatted data to.
     * @param pos FieldPosition to populate during formatting.
     * @return toAppendTo
     * @modelguid {C1A8830D-69D9-4700-9CD2-88E12D60E72E}
     */
    public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos)
    {
        double absNumber    = Math.abs(number);
        long   integerValue = 0;
        String separator;
        String wholePart;
        String fracPart;
        
        //***** case of :INF
        if (Double.isInfinite(absNumber))
        {
            wholePart = "INF";
            fracPart  = "";
        }
        
        //***** case of: NAN
        else if (Double.isNaN(absNumber))
        {
            wholePart = "NAN";
            fracPart  = "";
        }
        
        //***** case of: real numbers
        else
        {
            integerValue = (long) Math.floor(absNumber);
            wholePart    = Long.toString( integerValue );
            fracPart     = formatFraction( absNumber - integerValue );
        }
        
        //***** handle sign
        if (number > 0)
        {
            if (mShowSign)
            {
                wholePart = "+" + wholePart;
            }
        }
        else if (number < 0)
        {
            // Added mShowSign qualifier 6/17/2000 - kj
            if (mShowSign==true)
            {
              wholePart = "-" + wholePart;
            }
        }
        
        //***** case: less than 1, and no zero
        if (!mShowZero && (integerValue == 0) && (fracPart.length() != 0))
        {
            if (number > 0)
            {
                if (mShowSign)
                {
                    wholePart = "+";
                }
                else
                {
                    wholePart = "";
                }
            }
            else
            {
                if (mShowSign)
                  wholePart = "-";
                else wholePart = "";
            }
        }
        
        //***** case: handle separator
        if (((wholePart.length() != 0) && (fracPart.length() != 0)) || mInsertSpace)
        {
            separator = " ";
        }
        else
        {
            separator = "";
        }
        
        //***** set field position
        int sepAdj   = ((wholePart.length() == 0) && mInsertSpace) ? 1 : 0;
        int wholeAdj = ((wholePart.length() == 0) && (mShowZero == true)) ? 0 : 1;
        int baseLen  = toAppendTo.length();
        int wholeLen = wholePart.length();
        int fracLen  = fracPart.length();
        
        if (pos.getField() == NumberFormat.INTEGER_FIELD)
        {
            pos.setBeginIndex( baseLen + wholeAdj - 1 + sepAdj );
            pos.setEndIndex( baseLen + wholeLen + wholeAdj - 1 + sepAdj );
        }
        else if (pos.getField() == NumberFormat.FRACTION_FIELD)
        {
            pos.setBeginIndex( baseLen + wholeLen + wholeAdj + sepAdj );
            pos.setEndIndex( baseLen + wholeLen + fracLen + wholeAdj + sepAdj );
        }
                
        return toAppendTo.append( wholePart + separator + fracPart );
    }
    
    //***** parse a whole number


	/** @modelguid {045BA96E-1908-4583-B9AB-0A64AD0A1B13} */
    private long parseWhole(String text, ParsePosition parsePosition)
    {
        int     pos;
        boolean foundNumber = false;
        boolean foundSign   = false;
        boolean positive    = true;
        
        //***** skip whitespace
        for (pos = parsePosition.getIndex(); pos < text.length(); pos++)
        {
            char c = text.charAt(pos);
            
            if (Character.isDigit(c))
            {
                foundNumber = true;
                break;
            }
            if (c == '+')
            {
                if (foundSign)  return 0;
                foundSign = true;
                continue;
            }
            if (c == '-')
            {
                if (foundSign)  return 0;
                foundSign = true;
                positive = false;
                continue;
            }
            if (Character.isWhitespace(c))  continue;
        }
        
        //***** bail if we never found a number
        if (foundNumber == false)  return 0;
        
        long value = 0;
        long oldValue = 0;
        
        //***** loop over digits
        for (; pos < text.length(); pos++)
        {
            char c = text.charAt(pos);
            
            if (!Character.isDigit(c))  break;
            
            c -= '0';
            
            value *= 10;
            value += c;
            
            if (oldValue > value)  return 0;    //overflow
            
            oldValue = value;
        }

        parsePosition.setIndex(pos);
        
        if (!positive)  value = -value;
        
        return value;
    }

    //***** parse a whole number


	/** @modelguid {AEF5A4B2-37B5-411E-8F99-56C340C9FD45} */
    private double parseFraction( String text, ParsePosition parsePosition, long whole )
    {
        long    num = 0, den = 0, intPart;
        int     pos;
        boolean foundNumerator = false;
        boolean foundNumber = false;
        boolean positive;
        
        //***** get sign
        if (whole >= 0)
        {
            positive = true;
            intPart = whole;
        }
        else
        {
            positive = false;
            intPart  = -whole;
        }
                
        //***** skip whitespace
        for (pos = parsePosition.getIndex(); pos < text.length(); pos++)
        {
            char c = text.charAt(pos);
            
            if (Character.isDigit(c))  
            {
                foundNumber = true;
                break;
            }
            if (c == '/')
            {
                num = intPart;
                intPart = 0;
                foundNumber    = true;
                foundNumerator = true;
                break;
            }
            if (Character.isWhitespace(c))  continue;
            
            return whole;
        }
        
        if (!foundNumber)
        {
            return whole;
        }
        
        //***** get the numerator
        long oldValue = 0;
        
        if (!foundNumerator)
        {
            for (; pos < text.length(); pos++)
            {
                char c = text.charAt(pos);
                
                if (!Character.isDigit(c))  break;
                
                foundNumerator = true;
                c -= '0';
                
                num *= 10;
                num += c;
                
                if (oldValue > num)  return whole;    //overflow
                
                oldValue = num;
            }
        }
        
        if (!foundNumerator)
        {
            return whole;
        }
        
        //***** scan for fraction separator
        for (; pos < text.length(); pos++)
        {
            if (text.charAt(pos) == '/')  break;
        }
        
        if (text.charAt(pos) != '/')  return whole;
        pos++;
        
        //***** skip whitespace
        for (; pos < text.length(); pos++)
        {
            if (!Character.isWhitespace(text.charAt(pos)))  break;
        }
        
        if (!Character.isDigit(text.charAt(pos)))  return whole;
                
        //***** get the denominator
        oldValue    = 0;
        foundNumber = false;
        
        for (; pos < text.length(); pos++)
        {
            char c = text.charAt(pos);
                
            if (!Character.isDigit(c))  break;
                
            foundNumber = true;
            c -= '0';
                
            den *= 10;
            den += c;
                
            if (oldValue > den)  return whole;    //overflow
                
            oldValue = den;
        }
        
        if (!foundNumber)
        {
            return whole;
        }
        
        parsePosition.setIndex(pos);
        
        double retval = ((double) intPart) + (((double) num) / ((double) den));
        
        if (!positive)
        {
            retval = -retval;
        }
        
        return retval;
    }
    
    
    /**
     * Parse a string into a number.
     * 
     * @param text The string to be parsed
     * @param parsePosition ParsePosition to populate during parsing.  If parsing fails, index
     * remains unchanged
     * @return Long or Double object as appropriate, or null if parsing fails.
     * @modelguid {5229015A-5F98-4F08-8C06-AA04017E7C8E}
     */
    //***** parse a string
    public Number parse( String text, ParsePosition parsePosition )
    {
        Number  retval    = null;
        int     pos       = parsePosition.getIndex();
        boolean positive;
        
        //***** start parsing a whole number
        long whole = parseWhole(text,parsePosition);
        if (parsePosition.getIndex() == pos)  
        {
            return null;
        }
        
        retval = new Long(whole);
        
        //***** stop here?
        if (!isParseIntegerOnly())
        {
            double floating;
            
            // if form of decimal i.e 5.875
            if (text.indexOf(".") != -1)
            {
              retval = new Double(text);  
            }
            
            // else form of fraction i.e 5 7/8
            else 
            {
              floating = parseFraction( text, parsePosition, whole );
              if (floating != whole)
              {
                  retval = new Double(floating);
              }
            }
        }
        
        return retval;
    }
}