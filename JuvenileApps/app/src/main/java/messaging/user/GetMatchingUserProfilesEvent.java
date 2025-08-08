//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\user\\GetMatchingUserProfilesEvent.java

package messaging.user;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class GetMatchingUserProfilesEvent extends RequestEvent 
{
   public String logonId;
   public Date dateOfBirth;
   public String middleName;
   public String lastName;
   public String firstName;
   public String ssn;
   
   /**
    * @roseuid 442B041E009C
    */
   public GetMatchingUserProfilesEvent() 
   {
    
   }
   
   /**
    * Access method for the logonId property.
    * 
    * @return   the current value of the logonId property
    */
   public String getLogonId()
   {
      return logonId;
   }
   
   /**
    * Sets the value of the logonId property.
    * 
    * @param aLogonId the new value of the logonId property
    */
   public void setLogonId(String aLogonId)
   {
      logonId = aLogonId;
   }
   
   /**
    * Access method for the dateOfBirth property.
    * 
    * @return   the current value of the dateOfBirth property
    */
   public Date getDateOfBirth() 
   {
      return dateOfBirth;
   }
   
   /**
    * Sets the value of the dateOfBirth property.
    * 
    * @param aDateOfBirth the new value of the dateOfBirth property
    */
   public void setDateOfBirth(Date aDateOfBirth) 
   {
      dateOfBirth = aDateOfBirth;
   }
   
   /**
    * Access method for the middleName property.
    * 
    * @return   the current value of the middleName property
    */
   public String getMiddleName()
   {
      return middleName;
   }
   
   /**
    * Sets the value of the middleName property.
    * 
    * @param aMiddleName the new value of the middleName property
    */
   public void setMiddleName(String aMiddleName)
   {
      middleName = aMiddleName;
   }
   
   /**
    * Access method for the lastName property.
    * 
    * @return   the current value of the lastName property
    */
   public String getLastName()
   {
      return lastName;
   }
   
   /**
    * Sets the value of the lastName property.
    * 
    * @param aLastName the new value of the lastName property
    */
   public void setLastName(String aLastName)
   {
      lastName = aLastName;
   }
   
   /**
    * Access method for the firstName property.
    * 
    * @return   the current value of the firstName property
    */
   public String getFirstName()
   {
      return firstName;
   }
   
   /**
    * Sets the value of the firstName property.
    * 
    * @param aFirstName the new value of the firstName property
    */
   public void setFirstName(String aFirstName)
   {
      firstName = aFirstName;
   }
   
   /**
    * Access method for the ssn property.
    * 
    * @return   the current value of the ssn property
    */
   public String getSsn()
   {
      return ssn;
   }
   
   /**
    * Sets the value of the ssn property.
    * 
    * @param aSsn the new value of the ssn property
    */
   public void setSsn(String aSsn)
   {
      ssn = aSsn;
   }
}