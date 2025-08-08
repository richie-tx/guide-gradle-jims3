//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\ValidateOfficerEvent.java

package messaging.officer;

import mojo.km.messaging.RequestEvent;

public class ValidateOfficerOrUserEvent extends RequestEvent 
{
   public String departmentId;
   public String officerId;
   public String officerIdType;
   public String logonId;  
   /**
    * @roseuid 4186999B0015
    */
   public ValidateOfficerOrUserEvent() 
   {
    
   }
   
   /**
    * Access method for the agencyId property.
    * 
    * @return   the current value of the agencyId property
    */
   public String getDepartmentId()
   {
      return departmentId;    
   }
   
   /**
    * Access method for the officerId property.
    * 
    * @return   the current value of the officerId property
    */
   public String getOfficerId()
   {
      return officerId;
   }
   
   /**
    * Access method for the officerIdType property.
    * 
    * @return   the current value of the officerIdType property
    */
   public String getOfficerIdType()
   {
      return officerIdType;
   }
   
   /**
    * @param agencyId
    * @roseuid 418692BA0239
    */
   public void setDepartmentId(String agencyId) 
   {
  		this.departmentId =  agencyId; 
   }
   
   /**
    * @param officerId
    * @roseuid 418692BA024B
    */
   public void setOfficerId(String officerId) 
   {
		this.officerId = officerId;    
   }
   
   /**
    * @param officerIdType
    * @roseuid 418692BA025B
    */
   public void setOfficerIdType(String officerIdType) 
   {
		this.officerIdType = officerIdType;    
   }
/**
 * @return
 */
public String getLogonId()
{
	return logonId;
}

/**
 * @param string
 */
public void setLogonId(String string)
{
	logonId = string;
}

}
