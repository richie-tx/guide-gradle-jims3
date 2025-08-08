/*
 * Created on Feb 6, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.csserviceprovider;

import java.util.Date;
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
public class CSProgramStatusHistory extends PersistentObject 
{
    	//member variables
    private String programStatusHistoryId;
    private String programId;
    private String statusCode;
    private String statusChangeComments;
    private Date statusChangeDate;

    
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
         * @return Returns the programStatusHistoryId.
         */
        public String getProgramStatusHistoryId() {
            return programStatusHistoryId;
        }
        /**
         * @param programStatusHistoryId The programStatusHistoryId to set.
         */
        public void setProgramStatusHistoryId(String programStatusHistoryId) {
            this.programStatusHistoryId = programStatusHistoryId;
        }
    /**
     * @return Returns the statusChangeComments.
     */
    public String getStatusChangeComments() {
        return statusChangeComments;
    }
    /**
     * @param statusChangeComments The statusChangeComments to set.
     */
    public void setStatusChangeComments(String statusChangeComments) {
        this.statusChangeComments = statusChangeComments;
    }
    /**
     * @return Returns the statusChangeDate.
     */
    public Date getStatusChangeDate() {
        return statusChangeDate;
    }
    /**
     * @param statusChangeDate The statusChangeDate to set.
     */
    public void setStatusChangeDate(Date statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }
    /**
     * @return Returns the statusCode.
     */
    public String getStatusCode() {
        return statusCode;
    }
    /**
     * @param statusCode The statusCode to set.
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    
    /************ CSProgramStatusHistory Lookup Methods ***********************/

    /**
     * Find CSProgramStatusHistory by OID
     */
	static public CSProgramStatusHistory find(String programStatusHistoryId)
	{
	    	//initialize lookup objects
	    CSProgramStatusHistory programStatusHistory = null;
		IHome home = new Home();

			//use delegate to locate given program status history entity
		programStatusHistory = (CSProgramStatusHistory) 
						home.find(programStatusHistoryId, CSProgramStatusHistory.class);
		return programStatusHistory;
	}//end of find()

    /**
     * Find all CSProgramStatusHistory objects
     */
	static public Iterator findAll()
	{
	    	//initialize lookup objects
	    IHome home = new Home();
	    
	    	//use delegate to locate all program status history objects
		Iterator iter = home.findAll(CSProgramStatusHistory.class);
		return iter;
	}//end of findAll()
	
    /**
     * Find all CSProgramStatusHistory objects matching the given event attributes
     */
	static public Iterator findAll(IEvent event)
	{
	    	//initialize lookup objects
		IHome home = new Home();
		
			//use delegate to lookup program status history objects matching the given event values
		return home.findAll(event, CSProgramStatusHistory.class);
	}//end of findAll()

    /**
     * Find all CSProgramStatusHistory objects matching the given event attributes
     */	
	static public Iterator findAll(String attrName, String attrValue) {
    		
	    	//initialize lookup objects
	    IHome home = new Home();
		Iterator programStatuses = null;
		
			//use delegate to lookup program statuses with the given attribute/value matches
		programStatuses = home.findAll(attrName, attrValue, CSProgramStatusHistory.class);
		return programStatuses;
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
    
}
