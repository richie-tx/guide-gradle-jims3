/*
 * Created on Jan 18, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.csserviceprovider;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author cc_cwalters
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSProgramLocation extends PersistentObject 
{
    private String programLocationId;
    private String programId;
    private String locationId;
    private String validLocStatus;

    /**
     * @return Returns the programLocationId.
     */
    public String getProgramLocationId() {
        return programLocationId;
    }
    /**
     * @param programLocationId The programLocationId to set.
     */
    public void setProgramLocationId(String programLocationId) {
        this.programLocationId = programLocationId;
    }
    /**
     * @return Returns the locationId.
     */
    public String getLocationId() {
        return locationId;
    }
    /**
     * @param locationId The locationId to set.
     */
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    /**
     * @return Returns the programId.
     */
    public String getProgramId() {
        return programId;
    }
    /**
     * @param programId The programId to set.
     */
    public void setProgramId(String programId) {
        this.programId = programId;
    }
    /**
	 * @return the validLocStatus
	 */
	public String getValidLocStatus() {
		return validLocStatus;
	}
	/**
	 * @param validLocStatus the validLocStatus to set
	 */
	public void setValidLocStatus(String validLocStatus) {
		this.validLocStatus = validLocStatus;
	}
	/**
     * Find CSProgramLocation by OID
     */
	static public CSProgramLocation find(String programLocationId)
	{
	    	//initialize lookup objects
	    CSProgramLocation programLocation = null;
		IHome home = new Home();

			//use delegate to locate given program location entity
		programLocation = (CSProgramLocation) home.find(programLocationId, CSProgramLocation.class);
		return programLocation;
	}//end of find()  
	
	
	/**
     * Find all CSProgramReferral objects matching the given event attributes
     */
	static public Iterator findAll(IEvent event)
	{
	    	//initialize lookup objects
		IHome home = new Home();
		
			//use delegate to lookup program referrals matching the given event values
		return home.findAll(event, CSProgramLocation.class);
	}//end of findAll()
	
	
	/**
	 * 
	 * @param attrName
	 * @param attrValue
	 * @return
	 */
	public static Iterator findAll(String attrName, String attrValue)
	{
	    IHome home = new Home();
		return home.findAll(attrName, attrValue, CSProgramLocation.class);
	}
	
	/**
	 * Bind entity to database thus creating an OID
	 *
	 */
    public void bind()
    {
        IHome home = new Home();
        home.bind(this);
    }//end of bind()
    
	
}//end of CSProgramLocation
