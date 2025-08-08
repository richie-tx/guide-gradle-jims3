/*
 * Created on Jan 31, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.csserviceprovider;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSProgramLocationHelper 
{
    /**
     * Create a program location entity
     * @param programId
     * @param locationId
     * @return
     */
    public static CSProgramLocation createProgramLocation(String programId, String locationId)
    {        
        CSProgramLocation new_location = new CSProgramLocation();
        
        	//set program location attributes
        new_location.setLocationId(locationId);
        new_location.setProgramId(programId);
        new_location.setValidLocStatus("A");
        
        //save program location entity
        new_location.bind();
        return new_location;
    }//end of createProgramLocation()
    
    /**
     * Update a program location entity
     * @param validLocStatus
     * @param locationId
     * @return
     */
    public static CSProgramLocation updateProgramLocation(String validLocStatus, String locationId)
    {        
        CSProgramLocation update_location = CSProgramLocation.find( locationId );
        
        //set valid location status
        update_location.setValidLocStatus( validLocStatus );
        
        //save program location entity
        update_location.bind();
        return update_location;
    }//end of updateProgramLocation()
    
}//end of CSProgramLocationHelper
