package ui.supervision.supervisionorder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NumberSuffixUtil {
  
   private static final String[] NUMBERSUFFIXES = {"st","nd","rd","th"};
   private static final String DAYOF = " day of ";
   private static final String COMMA = ",";
   private static final String ZERODATE = "000000";
   
   public static String getSuffixForString(String number)
   {
       if(checkNumber(number))
       {
            number = getSuffix(Integer.valueOf(number.trim()).intValue());
       }
       return number;
   }
   
   public static boolean checkNumber(String number)
   {
       boolean isNumber = false;
       if(isNull(number))
       {
           isNumber = false;
       }
       else
       {
           try
           {
               Integer.valueOf(number.trim()).intValue();
               isNumber = true;    
           }
           catch(Exception e)
           {
               isNumber = false;    
           }
           
       }
       return isNumber;
   }
   
   /**
    * @param int to which the proper suffix is to be appended
    * @return String in the format 1st,2nd, 3rd, 4th... 
    */
   
   public  static String getSuffix(int number)
   {
       int numberMod = 0;
       StringBuffer ordinalNumber = new StringBuffer();
       String suffix = "";
	   
       numberMod = number % 100;
	   if(numberMod < 21)
       {
		   // if the modulus of the number is 0 to 20
		   if(numberMod == 0 || numberMod > 3)
		   {
			   suffix = NUMBERSUFFIXES[3]; 
		   }
		   else 
		   {
			   suffix = NUMBERSUFFIXES[numberMod-1];
		   }
	   }
	   else
       {
           numberMod = number % 10;
		   if (numberMod == 0 || numberMod >3)
		   {
			   suffix = NUMBERSUFFIXES[3];
		   }
		   else
		   {
				suffix = NUMBERSUFFIXES[numberMod-1];
		   }
       }
	   ordinalNumber.append(String.valueOf(number));
	   ordinalNumber.append(suffix);
       return  ordinalNumber.toString();
   }
   
   /*public  static String getSuffix(int number)
   {
       int numberMod = 0;
       if(number > 100)
       {
           numberMod = number % 100;
       }

       String suffix = "";
       if(number == 0 || number > 3 && number < 21)
       {
           suffix = NUMBERSUFFIXES[3]; 
       }
       else if(number >0 && number <= 3)
       {
           suffix = NUMBERSUFFIXES[number-1];
       }
       else if(numberMod == 0 || numberMod > 3 && numberMod < 21)
       {
           suffix = NUMBERSUFFIXES[3]; 
       }else if (numberMod < 100)
       {
           numberMod = number % 10;
           if(number < 20)
           {
              suffix = NUMBERSUFFIXES[3];
           }
           else if (numberMod == 1)
           suffix = NUMBERSUFFIXES[0];
           else if (numberMod == 2)
           suffix = NUMBERSUFFIXES[1];
           else if (numberMod == 3)
           suffix = NUMBERSUFFIXES[2];
           else if (numberMod == 0 || numberMod >3)
           suffix = NUMBERSUFFIXES[3];
       }
       
       return String.valueOf(number) + suffix;
   }*/
   
   
   /**
    * @param date as String in the format "MMM dd, yyyy  ----- May 1, 2006"
    * @return Date as String in the format May 1st, 2006 with appropriate suffix appended to the Day
    */
    public static String getDateSuffixForDateString(String date)
    {
        String dateSuffix = "";
        if(!isNull(date))
        {
            String dateDay = date.substring(date.trim().indexOf(" ")+1,date.indexOf(NumberSuffixUtil.COMMA));
            String month = date.substring(0,date.trim().indexOf(" "));
            String year = date.substring(date.indexOf(NumberSuffixUtil.COMMA)+1, date.length());
            //return TextUtil.searchAndReplace(date, dateDay + NumberSuffixUtil.COMMA, NumberSuffixUtil.getSuffixForString(dateDay) + NumberSuffixUtil.DAYOF + NumberSuffixUtil.COMMA,0);
            dateSuffix = NumberSuffixUtil.getSuffixForString(dateDay) + NumberSuffixUtil.DAYOF  + month + NumberSuffixUtil.COMMA + year ;
        }
        
        return dateSuffix;
    }

    /**
     * @param date as String in the format "MMM dd, yyyy  ----- May 1, 2006"
     * @return Date as String in the format May 1st, 2006 with appropriate suffix appended to the Day
     */
     public static String getDateSuffixFormatDateString(String date)
     {
        String dateSuffix = "";
         try
         {
             if(!isNull(date))
             {
                SimpleDateFormat sd =  new SimpleDateFormat("MM/dd/yyyy");
                     dateSuffix = getDateSuffixForDate(sd.parse(date));
             }
         }
         catch(ParseException pe)
         {
             dateSuffix = date;
         }
        
         return dateSuffix;
     }
     
    /**
     * @param date as java.util.Date
     * @return Date as String in the format May 1st, 2006 with appropriate suffix appended to the Day
     */
     public static String getDateSuffixForDate(Date date)
     {
        String dateSuffix = "";
         if(date != null)
         {
            //String dateString = DateFormat.getDateInstance(DateFormat.LONG).format(date);
            //String dateDay = dateString.substring(dateString.indexOf(" ")+1,dateString.indexOf(NumberSuffixUtil.COMMA));
            //return TextUtil.searchAndReplace(dateString, dateDay + NumberSuffixUtil.COMMA, NumberSuffixUtil.getSuffixForString(dateDay)+ NumberSuffixUtil.DAYOF + NumberSuffixUtil.COMMA,0);
            dateSuffix = getDateSuffixForDateString(DateFormat.getDateInstance(DateFormat.LONG).format(date));
         }
         return dateSuffix;
     }
     
    /**
     * @param date as String in the format "MMDDYY  ----- 011306"
     * @return Date as String in the format January 13th, 2012 with appropriate suffix appended to the Day
     */
     public static String getDateSuffixFormatStringMMDDYY(String date)
     {
         try
         {
             if(!isNull(date))
             {
                SimpleDateFormat sd =  new SimpleDateFormat("MMddyy");
                date = getDateSuffixForDate(sd.parse(date));
             }
         }
         catch(ParseException pe)
         {
         }
         return date;
     }

     public static String getLongDateString(Date date)
     {
        String dateString = null;
        if(date != null)
            dateString = DateFormat.getDateInstance(DateFormat.LONG).format(date);
        
        return dateString;
     }
     
    private static boolean isNull(String date)
    {
        boolean isNull = false;
        if(date == null || date.equals("") || date.equals(NumberSuffixUtil.ZERODATE))
        {
            isNull = true;
        }
        return isNull;
    }
      

    public static void main(String[] args) throws Exception
    {
       	System.out.println("*** 180 " + NumberSuffixUtil.getSuffix(180));
    	System.out.println("*** 1 " + NumberSuffixUtil.getSuffix(1));
        System.out.println("*** 1 " + NumberSuffixUtil.getSuffixForString(" 1"));
        System.out.println("*** 2 " + NumberSuffixUtil.getSuffix(2));
        System.out.println("*** 3 " + NumberSuffixUtil.getSuffix(3));
    	System.out.println("*** 16 " + NumberSuffixUtil.getSuffix(16));
        System.out.println("*** 11 " + NumberSuffixUtil.getSuffix(11));
        System.out.println("*** 13 " + NumberSuffixUtil.getSuffix(13));
        System.out.println("*** 22 " + NumberSuffixUtil.getSuffix(22));
        System.out.println("*** 21 " + NumberSuffixUtil.getSuffix(21));
        System.out.println("*** 45 " + NumberSuffixUtil.getSuffix(45));
    	System.out.println("*** 122 " + NumberSuffixUtil.getSuffix(122));
        System.out.println("*** 122 " + NumberSuffixUtil.getSuffixForString("Inf"));
        System.out.println(NumberSuffixUtil.getDateSuffixForDateString("May 2, 2003"));
        System.out.println(NumberSuffixUtil.getDateSuffixFormatDateString("05/02/2003"));
        System.out.println(NumberSuffixUtil.getDateSuffixForDate(new Date()));
        System.out.println(NumberSuffixUtil.getDateSuffixFormatStringMMDDYY("000000"));
        System.out.println("Null" + NumberSuffixUtil.getDateSuffixForDateString(null));
        System.out.println("Null" + NumberSuffixUtil.getDateSuffixFormatDateString(null));
        System.out.println("Null" + NumberSuffixUtil.getDateSuffixForDate(null));
        System.out.println("Null" + NumberSuffixUtil.getDateSuffixFormatStringMMDDYY(null));
        
	}
}