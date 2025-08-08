package pd.codetable.criminal;

import java.util.Iterator;

import messaging.codetable.GetAttorneyNameAndBarNumEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.ICodetable;

/**
 * AttorneyNameAndBarNum
 * @author sthyagarajan
 *
 */
public class AttorneyNameAndBarNum extends PersistentObject implements ICodetable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String attyName;
    private String barNum;
    private String attyNameHistory;
    
    /**
	 * @roseuid 45771CA9003F
	 *  
	 */
	public AttorneyNameAndBarNum() {
	}
    
    /**
     * @return the attyName
     */
    public String getAttyName()
    {
	fetch();
        return attyName;
    }
    /**
     * @param attyName the attyName to set
     */
    public void setAttyName(String attyName)
    {
	if (this.attyName != attyName) {
		markModified();
	}
        this.attyName = attyName;
    }
    /**
     * @return the barNum
     */
    public String getBarNum()
    {
	fetch();
        return barNum;
    }
    /**
     * @param barNum the barNum to set
     */
    public void setBarNum(String barNum)
    {
	if (this.barNum != barNum) {
		markModified();
	}
        this.barNum = barNum;
    }
    /**
     * @return the hAttyName
     */
    public String getAttyNameHistory()
    {
	fetch();
        return attyNameHistory;
    }
    /**
     * @param hAttyName the hAttyName to set
     */
    public void setAttyNameHistory(String attyNameHistory)
    {
	if (this.attyNameHistory != attyNameHistory) {
		markModified();
	}
        this.attyNameHistory = attyNameHistory;
    }
    
   
    /**
     * Finds all AttorneyNameAndBarNum by an attribute value
     * @param attributeName
     * @param attributeValue
     * @return 
     */
     static public Iterator<AttorneyNameAndBarNum> findAll(IEvent event) {
  	   IHome home = new Home();
  	   return home.findAll(event, AttorneyNameAndBarNum.class);
     }
    
     
     /**
      * @return pd.codetable.criminal.AttorneyNameAndBarNum
      * @param barNum
      * @roseuid 4236ED9502A8
      */
     static public AttorneyNameAndBarNum find(String barNum) {
  	return (AttorneyNameAndBarNum) new Home().find(barNum, AttorneyNameAndBarNum.class);
     }

     
     
     /**
      * Finds all AttorneyNameAndBarNum by an attribute value
      * @param attributeName
      * @param attributeValue
      * @return 
      */
     static public Iterator<AttorneyNameAndBarNum> findAll(String attributeName, String attributeValue) {
 	  IHome home = new Home();
 	   return home.findAll(attributeName, attributeValue, AttorneyNameAndBarNum.class);
     }
 	
     
     /**
	 * 
	 * @param event
	 * @return
	 */
	static public Iterator findAll(GetAttorneyNameAndBarNumEvent event) {
		IHome home = new Home();
		return home.findAll(event, AttorneyNameAndBarNum.class);	
	}
     
    /**
     * Finds all AttorneyNameAndBarNum
     * @return 
     */
     public Iterator<AttorneyNameAndBarNum> findAll() {
	   return new Home().findAll(AttorneyNameAndBarNum.class);
    }
	   
  
    @Override
    public void inActivate()
    {
        // TODO Auto-generated method stub
        
    }
		
}
