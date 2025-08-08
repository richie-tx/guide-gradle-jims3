package mojo.ui.common;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * <B>Percent</B> is used display data in a percent format
 *
 * @version 1.0
 *
 * @modelguid {38506291-B7E3-4878-89F9-6E949B5CF752}
 */

public class Percent  {
	/** @modelguid {589159DF-1128-442D-A057-4479536FE9C8} */
   Locale location=Locale.ENGLISH;
	/** @modelguid {3EE2E25E-FDD2-4344-834E-4B53FD77C92E} */
   double value=0.0;
   
	/** @modelguid {2C92EAD7-5131-4C6D-9E17-635042781A4E} */
   public Percent(double _value) {
     value=_value;
   }
   
	/** @modelguid {C48CBBCE-EA57-4552-ABCE-7A16945B6507} */
   public void setValue(double _value) {
     value=_value; 
   }
   
	/** @modelguid {142509A3-5129-4688-9C8F-2935505B9CAB} */
   void setLocale(Locale _location) {
      location=_location;
   } // end : setLocale
    
   
	/** @modelguid {B084DE1A-A192-4826-A54E-33D506F78D45} */
   public String toString() {
     NumberFormat form;
     String formattedString="";
 
     form = NumberFormat.getPercentInstance(location);  
     formattedString = form.format(value);
     
      return(formattedString); 
    
   }  // end : toString
    
    
	/** @modelguid {FE6CDC4D-B796-43F4-AFA1-C2B101EFE201} */
   public static void main (String[] args) {
      Percent percentValue = new Percent(0.876);
      System.out.println("Your percent = " + percentValue);
   }
    
}
