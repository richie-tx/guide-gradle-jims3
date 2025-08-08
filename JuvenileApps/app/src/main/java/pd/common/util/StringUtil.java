/*
 * Created on Jun 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.common.util;

/**
 * @author cc_rsojitrawala
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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

    public static String padSpn(String spn){
        if (spn != null && spn.length() < 8) {
            StringBuffer sb = new StringBuffer(spn);
            for (int i = 0; i < 8 - spn.length(); i++) {
                sb.insert(0, "0");
            }
            spn = sb.toString();
        }
        return spn;
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
    
    public static String nvl(String value){
	return ( value != null && value.length() > 0 ) ? value : "";
    }
    
    public static String nvl(String value, String alter){
	return ( value != null && value.length() > 0 ) ? value : alter;
    }
}
