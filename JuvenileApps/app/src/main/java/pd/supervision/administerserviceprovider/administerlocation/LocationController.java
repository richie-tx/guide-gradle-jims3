//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\administerlocation\\LocationController.java

package pd.supervision.administerserviceprovider.administerlocation;


/**
 * @stereotype control
 */
public class LocationController 
{
   
   /**
    * @roseuid 448F24E9038F
    */
   public LocationController() 
   {
    
   }
   
   /**
    * @stereotype design
    * @param agencyId
    * @roseuid 44734FE303D5
    */
   public void getLocationsByAgency(String agencyId) 
   {
    
   }
   
   /**
    * @stereotype design
    * @param agencyId
    * @param locationName
    * @param statudId
    * @param serviceTypeId
    * @param serviceProviderName
    * @param inHouse
    * @param streetNumber
    * @param streetName
    * @param city
    * @param stateId
    * @param zipcode
    * @roseuid 44734FE303D5
    */
   public void getLocations(String agencyId,String locationName, String statudId, String serviceTypeId,
   		String serviceProviderName, boolean inHouse, String streetNumber, 
		String streetName, String city, String stateId, String zipcode) 
   {
    
   }
   /**
    * @stereotype design
    * @param agencyId
    * @param locationName
    * @roseuid 44734FE303D5
    */
   public void validateLocation(String agencyId, String locationName){
   	
   }

   /**
    * @stereotype design
    * @param agencyId
    * @param locationId
    * @param locationName
    * @param isInHouse
    * @param facilityTypeId
    * @param statusId
    * @roseuid 44734FE303D5
    */
   

   public void createUpdateLocation(String locationId, String locationName,boolean isInHouse,
   		String facilityTypeId,String statusId,String agencyId){
   	
   }
  
   /**
    * @stereotype design
    * @param locationId
    * @param action    
    * @roseuid 44734FE303D5
    */
   

   public void activateInactivateLocation(String locationId, String action){
   	
   }
   
   /**
    * @stereotype design
    * @param locationId
    * @param action    
    * @roseuid 44734FE303D5
    */
   

   public void getLocation(String locationId){
   	
   }      
   
/**
    * @stereotype design
    * @param locationId
    * @param action    
    * @roseuid 44734FE303D5
    */
   

   public void getJuvenileLocation(String locationId){
   	
   }      
 /**
    * @stereotype design
    * @param locationId
    * @param action    
    * @roseuid 44734FE303D5
    */
   

   public void getJuvLocationUnits(String locationUnitName,	String locationId, String juvUnitCd, String statusId){
   	
   }      
   /**
    * @stereotype design
    * @param locationId
    * @param action    
    * @roseuid 44734FE303D5
    */
   

   public void getJuvLocationUnitsByAgency(String agencyId, String locationId){
   	
   } 
   
   /**
    * @stereotype design
    * @param locationId
    * @param action    
    * @roseuid 44734FE303D5
    */
   
   public void getJuvLocationUnitsByLocationId(String locationId){
   	
   }    
   /**
    * @stereotype design
    * @param action    
    * @roseuid 44734FE303D5
    */
   
   public void getAllJuvLocationUnitsByLocationId()
   {}
   
   /**
    * @stereotype design
    * @param action    
    * @roseuid 44734FE303D5
    */
   
   public void validateLocationUnitDetails()
   {}
   /**
    * @stereotype design
    * @param action    
    * @roseuid 44734FE303D5
    */ 
   public void createUpdateJuvLocationUnit()
   {}
   
   /**
    * @stereotype design
    * @param action    
    * @roseuid 44734FE303D5
    */
   public void activateInactivateLocationUnit()
   {}
   /**
    * @stereotype design
    * @param action    
    * @roseuid 44734FE303D5
    */
   public void getJuvLocationUnitDetails()
   {}
   /**
    * @stereotype design
    * @param action    
    * @roseuid 44734FE303D5
    */
   public void getLocationsForStaffPosition()
   {}

   /**
    * @stereotype design
    * @param action    
    * @roseuid 44734FE303D5
    */
   public void getLocationByLocationId()
   {}
   
   /**
    * @stereotype design
    * @param agencyId
    * @roseuid 44734FE303D5
    */
   public void getLocationByJuvLocUnitId(String juvLocUnitId) 
   {    
   }
}
