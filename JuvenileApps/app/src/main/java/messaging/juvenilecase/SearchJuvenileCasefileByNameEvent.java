//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\SearchJuvenileCasefilesEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class SearchJuvenileCasefileByNameEvent extends RequestEvent 
{
   public String firstName;
   public String middleName;
   public String lastName;
   
   /**
    * @roseuid 4278C831024E
    */
   public SearchJuvenileCasefileByNameEvent() 
   {    
   }

   /**
    * @param firstName
    * @roseuid 4278C7B9010B
    */
   public void setFirstName(String aFirstName) 
   {
        this.firstName = aFirstName;
   }
   
   /**
    * @return String
    * @roseuid 4278C7B90115
    */
   public String getFirstName() 
   {
    return this.firstName;
   }
   
   /**
    * @param middleName
    * @roseuid 4278C7B9011F
    */
   public void setMiddleName(String aMiddleName) 
   {
    this.middleName = aMiddleName;
   }
   
   /**
    * @return String
    * @roseuid 4278C7B9012A
    */
   public String getMiddleName() 
   {
    return this.middleName;
   }
   
   /**
    * @param lastName
    * @roseuid 4278C7B9013D
    */
   public void setLastName(String aLastName) 
   {
    this.lastName = aLastName;
   }
   
   /**
    * @return String
    * @roseuid 4278C7B90147
    */
   public String getLastName() 
   {
    return this.lastName;
   }
}
