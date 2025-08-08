package ui.common;

public final class StringUtil
{

    /**
     * 
     */
    public StringUtil()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @param event
     */
    public static String padString(String field, String charToPadWith, int length)
    {
        String string = field;
        int len = length - field.length();
        if (len > 0)
        {
            StringBuffer buffer = new StringBuffer(length);
            for (int i = 0; i < len; i++)
            {
                buffer.append("0");
            }
            buffer.append(field);
            string = buffer.toString();
        }
        return string;
    }

    /**
     * Tests the given string to see if it is null or the empty string
     * @param testString
     * @return
     */
    public static boolean isEmpty(String testString)
    {
        if ((testString != null) && (!testString.trim().equals("")))
            return false;
        else
            return true;
    }//end of isEmpty()
    
    /**
     * Tests the given string to see if it is null or the empty string
     * and returns original value if not null, and empty string if null ""
     * @param valueToTest
     * @return
     */
    public static String isNotNull(String valueToTest)
    {
        if ((valueToTest != null) && (!valueToTest.trim().equals("")))
            return valueToTest;
        else
            return "";
    }//end of isNotNull()
    
    /**
     * rjc
     * trim off whitespace, and return empty string if null
     */
    public static String trimToEmpty(String originalString){
    	String resultString = "";
    	if(originalString != null){
    		resultString = originalString.trim();
    	}    	
    	return resultString;
    }
    
    
    //Added for user story 11031
    //anything typed within [] would be removed.
    public static String removeTimeStampFromComments(String commentsWithTimeStamp){
    	 String delims = "[\\[-\\]]";// regex to remove square brackets.
    	 String[] comments = commentsWithTimeStamp.split(delims);
    	 if(comments!=null && comments.length>0){
    		 return comments[0];
    	 }
    	 return commentsWithTimeStamp;
    }
}
