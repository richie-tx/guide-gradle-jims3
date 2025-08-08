package mojo.ui.common;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * <B>Currency</B> is used for currency data. It returns a value 
 * in the format of XXXX.XX or (XXXX.XX) if the value is negative.
 *
 * @author Kurt Jacobs
 * @version 1.0
 *
 * @modelguid {8822EB2B-5B7C-4C5B-B131-322B4D2E382D}
 */

public class Currency  {
	/** @modelguid {634B3A09-8CE0-457E-A171-10F7FAA3EBC0} */
   Locale location=Locale.US;
	/** @modelguid {B1E93EE2-E972-4AF8-A13B-8B52A1AADCBD} */
   double value=0;
   
	/** @modelguid {07D31C59-CF8B-4A77-9671-3D1206D7BB1E} */
   public Currency(double _value) {
     setValue(_value);
   }
   
	/** @modelguid {692E277A-D3D4-4065-BE2A-9BF33371BEA3} */
   public void setValue(double _value) {
     NumberFormat form;
     form = NumberFormat.getCurrencyInstance(location); 
     String formattedString=form.format(_value);
     // if a value is significantly less than 0 than the formatter
     // may show value as a negative zero ($0.00). Correct this
     // by setting value to positive zero
     if (formattedString.equals("($0.00)")) 
     {
       value=0.0;
     }
     else 
       value=_value;   
   }
   
	/** @modelguid {116A5891-3260-4F88-93DE-8DA04E074330} */
   public double getValue() {
     return value;
   }
   
	/** @modelguid {13057856-00C3-423D-878F-C7A6B47318D1} */
   void setLocale(Locale _location) {
      location=_location;
   } // end : setLocale
    
   
	/** @modelguid {9811D361-F61A-4C0B-92DE-2C72AFDFAFDC} */
   public String toString() {
     NumberFormat form;
     
     form = NumberFormat.getCurrencyInstance(location);
     return (form.format(value));    
   }  // end : toString
    
    
	/** @modelguid {E4F0981B-F87C-4B5F-A06B-F390D5620FC3} */
   public static void main (String[] args) {
      double testTable[] = {-0.0005,10,10.00556,0.455,0.044,0.555,-0.35,-0.0355,-0.034,-0.0055};

      for (int i=0;i<testTable.length;++i)
      {
        Currency currencyValue= new Currency(testTable[i]);
        System.out.println("Your value " + testTable[i] + " = " + currencyValue);
      }
      
   }
    
}