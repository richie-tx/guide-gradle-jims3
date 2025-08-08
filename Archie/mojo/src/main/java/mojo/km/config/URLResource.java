//Source file: C:\\VIEWS\\ARCHPRODUCTION\\FRAMEWORK\\MOJO-JIMS2\\MOJO.JAVA\\src\\mojo\\km\\config\\URLResource.java

package mojo.km.config;

import java.util.*;

public class URLResource 
{
	public static final int URL = 0;
	public static final int PREFIX = 1;
	public static final int EXTENSION = 2;

   	private String url;
   	private String requiredFeatures[];
   	private String requiredUserTypes[];
   	private HashMap queryParameters = new HashMap ();
   	private int type;
   
   
   
   /**
    * @roseuid 42495EEC02AF
    */
   public URLResource() 
   {
    
   }
   
   public void setType(int type) {
   	this.type = type;
   }
   public int getType() {
   	return type;
   }
   
   /**
    * @return String
    * @roseuid 42495DD80138
    */
   public String getURL() 
   {
    return url;    
   }
   
   /**
    * @param name
    * @roseuid 42495DE1013E
    */
   public void setURL(String name) 
   {
    url = name;    
   }
   
   /**
    * @return HashMap
    * @roseuid 42495E0C018D
    */
   public HashMap getQueryParameters() 
   {
    return queryParameters;    
   }
   
   /**
    * @param name
    * @param value
    * @roseuid 42495E38015D
    */
   public void addQueryParameter(String name, String value) 
   {
    queryParameters.put(name, value);    
   }
   
   /**
    * @return String[]
    * @roseuid 42495E7000D1
    */
   public String[] getRequiredFeatures() 
   {
    return requiredFeatures;    
   }
   
   /**
    * @param requiredFeatures
    * @roseuid 42495F870249
    */
   public void setRequiredFeatures(String[] requiredFeatures) 
   {
    this.requiredFeatures = requiredFeatures;    
   }
   
   /**
    * @return String[]
    * @roseuid 42495E8001BA
    */
   public String[] getRequiredUserTypes() 
   {
    return requiredUserTypes;    
   }
   
   /**
    * @param userTypes
    * @roseuid 42495F780131
    */
   public void setRequiredUserTypes(String[] userTypes) 
   {
    this.requiredUserTypes = userTypes;    
   }
   
   /**
    * @param request
    * @param url
    * @param queryParams
    * @return boolean
    * @roseuid 4249982E02FC
    */
   public boolean matchesRequest(String url, Map passedInQueryParams) 
   {
   	/*
   	 * A match is present if the required path+document matches and if all
   	 * the URLResource query params are present with the correct values.
   	 * This URLResource can be either an exact url match, a prefix match or
   	 * extension match and we have to compare with the passed url accordingly
   	 */
   	 boolean urlMatch = false;
   	 if (this.getType() == URLResource.URL) {
   	 	urlMatch = this.url.toLowerCase().equals(url.toLowerCase()); 
	 } else if (this.getType() == URLResource.PREFIX) {
		urlMatch = url.toLowerCase().startsWith(this.url.toLowerCase());
	 } else if (this.getType() == URLResource.EXTENSION) {
		urlMatch = url.toLowerCase().endsWith(this.url.toLowerCase());
   	 }
   	if (urlMatch) {
   		// The URL matches. Now check the query parameters (if applicable)
   		if (queryParameters != null && queryParameters.keySet().size() > 0) {
   			// If the passed in query params are empty and the security.xml
   			// specifies it should have parameters then return false (access denied)
			if (passedInQueryParams.isEmpty()) {
				return false;
			}
			
			Iterator iQueryParams = queryParameters.keySet().iterator();
			while (iQueryParams.hasNext()) {
				String key = (String) iQueryParams.next();
				String value = (String) queryParameters.get(key);
				
				// Is the query parameter present on the query string?
				String passedInValue = ((String[]) passedInQueryParams.get(key))[0];
				
				/*
				 * Make sure the query parameter exists. If the query string does not
				 * have a value for the param or if the value does not match, it is
				 * not a match.
				 * For an example, if this URLRequest has a URL of 'http://test.com'
				 * and a query parameter
				 * called 'abc' with a value of '123' then the following URLs
				 * would not be a match:
				 * http://test.com
				 * http://test.com?abc=321
				 * 
				 * However the following URLs would be a match
				 * http://test.com?abc=123
				 * http://test.com?abc=123&def=456
				 */
				if (passedInValue == null
				    || !(passedInValue.toLowerCase().equals(value.toLowerCase()))) {
				    	return false; 
			    }
			}
   		}
   		return true;
   	}
    return false;    
   }
}
