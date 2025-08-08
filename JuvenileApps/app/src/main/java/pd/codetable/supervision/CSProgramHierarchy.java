/*
 * Created on Jan 28, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.codetable.supervision;

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
public class CSProgramHierarchy extends PersistentObject 
{
    private String progHierarchyId;
    private String progHierarchyCode;
    private String programGroupCode;
    private String programTypeCode;
    private String programSubtypeCode;

    
    /**
     * @return Returns the progHierarchyCode.
     */
    public String getProgHierarchyCode() {
        return progHierarchyCode;
    }
    /**
     * @param progHierarchyCode The progHierarchyCode to set.
     */
    public void setProgHierarchyCode(String progHierarchyCode) {
        this.progHierarchyCode = progHierarchyCode;
    }
    /**
     * @return Returns the progHierarchyId.
     */
    public String getProgHierarchyId() {
        return progHierarchyId;
    }
    /**
     * @param progHierarchyId The progHierarchyId to set.
     */
    public void setProgHierarchyId(String progHierarchyId) {
        this.progHierarchyId = progHierarchyId;
    }
    /**
     * @return Returns the programGroupCode.
     */
    public String getProgramGroupCode() {
        return programGroupCode;
    }
    /**
     * @param programGroupCode The programGroupCode to set.
     */
    public void setProgramGroupCode(String programGroupCode) {
        this.programGroupCode = programGroupCode;
    }
    /**
     * @return Returns the programSubtypeCode.
     */
    public String getProgramSubtypeCode() {
        return programSubtypeCode;
    }
    /**
     * @param programSubtypeCode The programSubtypeCode to set.
     */
    public void setProgramSubtypeCode(String programSubtypeCode) {
        this.programSubtypeCode = programSubtypeCode;
    }
    /**
     * @return Returns the programTypeCode.
     */
    public String getProgramTypeCode() {
        return programTypeCode;
    }
    /**
     * @param programTypeCode The programTypeCode to set.
     */
    public void setProgramTypeCode(String programTypeCode) {
        this.programTypeCode = programTypeCode;
    }
    
    /************ CSProgramHierarchy Lookup Methods ***********************/

    /**
     * Find CSProgramHierarchy by OID
     */
	static public CSProgramHierarchy find(String refTypeId)
	{
	    	//initialize lookup objects
	    CSProgramHierarchy refType = null;
		IHome home = new Home();

			//use delegate to locate given cs service provider entity
		refType = (CSProgramHierarchy) home.find(refTypeId, CSProgramHierarchy.class);
		return refType;
	}//end of find()

    /**
     * Find all CSProgramHierarchy objects
     */
	static public Iterator findAll()
	{
	    	//initialize lookup objects
	    IHome home = new Home();
	    
	    	//use delegate to locate all referral type objects
		Iterator iter = home.findAll(CSProgramHierarchy.class);
		return iter;
	}//end of findAll()
	
    /**
     * Find all CSProgramHierarchy objects matching the given event attributes
     */
	static public Iterator findAll(IEvent event)
	{
	    	//initialize lookup objects
		IHome home = new Home();
		
			//use delegate to lookup program referral types matching the given event values
		return home.findAll(event, CSProgramHierarchy.class);
	}//end of findAll()

    /**
     * Find all CSProgramHierarchy objects matching the given event attributes
     */	
	static public Iterator findAll(String attrName, String attrValue) {
    		
	    	//initialize lookup objects
	    IHome home = new Home();
		Iterator refTypes = null;
		
			//use delegate to lookup service provider programs with the given attribute/value matches
		refTypes = home.findAll(attrName, attrValue, CSProgramHierarchy.class);
		return refTypes;
	}
    
}//end of CSProgramHierarchy
