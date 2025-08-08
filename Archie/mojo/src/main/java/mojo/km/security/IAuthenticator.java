//Source file: C:\\VIEWS\\ARCHPRODUCTION\\FRAMEWORK\\MOJO-JIMS2\\MOJO.JAVA\\src\\mojo\\km\\security\\IAuthenticator.java

package mojo.km.security;

import java.util.Map;


public interface IAuthenticator 
{
   
   /**
    * //U.S #79250 ( modified
    * @param userID
    * @param password
    * @return mojo.km.security.ISecurityManager
    * @roseuid 422F62B400E7
    */
   public  Map<String,SecurityUser> authenticate(String userID, String password,String credentialType,String noOfAttempts) throws AuthenticationFailedException;
}
/**
 * IAuthenticator.IAuthenticator()
 */
