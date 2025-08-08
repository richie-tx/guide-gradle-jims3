package pd.contact.agency;

import java.util.Collection;

import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.transferobjects.helper.AgencyHelper;

/**
 * @author dgibler
 */
public class Agency extends PersistentObject
{

    /**
     * Properties for agencyType
     * 
     * @referencedType pd.codetable.Code
     * @contextKey AGENCY_TYPE
     * @detailerDoNotGenerate true
     */
    private Code agencyType = null;
    private String agencyTypeId;
    private String agencyName;
    private String agencyId;
    private MetaDataResponseEvent metaDataRespEvent;
    /**
     * Properties for departments
     * 
     * @referencedType pd.contact.agency.Department
     * @detailerDoNotGenerate true
     */
    private Collection<Department> departments = null;

    //	private String projectAnalystInd;

    /**
     * @roseuid 4107BF8402AF
     */
    public Agency()
    {
    }

    /**
     * @param departments
     *            the departments to set
     */
    public void setDepartments(Collection<Department> departments)
    {
	this.departments = departments;
    }

    /**
     * Clears all pd.contact.Department from class relationship collection.
     * 
     * @roseuid 4107DFB603E7
     */
    public void clearDepartments()
    {
	initDepartments();
	departments.clear();
    }

    /**
     * Gets referenced type pd.codetable.Code
     * 
     * @return Code
     * @roseuid 4107DFB60134
     */
    public Code getAgencyType()
    {
	//fetch();
	initAgencyType();
	return agencyType;
    }

    /**
     * Access method for the agencyTypeId property.
     * 
     * @return the current value of the agencyTypeId property
     */
    public String getAgencyTypeId()
    {
	//fetch();
	return agencyTypeId;
    }

    /**
     * Access method for the agencyId property.
     * 
     * @return the current value of the agencyId property
     */

    /**
     * Access method for the agencyName property.
     * 
     * @return the current value of the agencyName property
     */
    public String getAgencyName()
    {
	//fetch();
	return agencyName;
    }

    /**
     * returns a collection of pd.contact.Department
     * 
     * @return java.util.Collection
     * @roseuid 4107DFB603A1
     */
    public Collection<Department> getDepartments()
    {
	//fetch();
	initDepartments();
	return departments;
    }

    /**
     * Access method for the projectAnalystInd property.
     * 
     * @return the current value of the projectAnalystInd property
     */
    /*public java.lang.String getProjectAnalystInd()
    {
    	fetch();
    	return projectAnalystInd;
    }*///87191

    /**
     * Initialize class relationship to class pd.codetable.Code
     * 
     * @roseuid 4107DFB6012A
     */
    private void initAgencyType()
    {
	if (agencyType == null)
	{
	    try
	    {
		agencyType = (Code) new mojo.km.persistence.Reference(agencyTypeId, Code.class, "AGENCY_TYPE").getObject();
	    }
	    catch (Throwable t)
	    {
		agencyType = null;
	    }
	}
    }

    /**
     * Initialize class relationship implementation for
     * pd.contact.agency.Department
     * 
     * @roseuid 4107DFB60397
     */
    private void initDepartments()
    {
	if (departments == null)
	{
	 /*   if (this.getOID() == null)
	    {
		new mojo.km.persistence.Home().bind(this);
	    }*/
	    try
	    {
		departments = Agency.find(this.getAgencyId()).getDepartments();
		//new mojo.km.persistence.ArrayList(pd.contact.agency.Department.class, "Agency", "" + getOID());
	    }
	    catch (Throwable t)
	    {
		departments = new java.util.ArrayList();
	    }
	}
    }

    /**
     * insert a pd.contact.agency.Department into class relationship collection.
     * 
     * @param anObject
     * @roseuid 4107DFB603B5
     */
    /*	public void insertDepartments(pd.contact.agency.Department anObject)
    	{
    		initDepartments();
    		departments.add(anObject);
    	}*///87191

    /**
     * Removes a pd.contact.agency.Division from class relationship collection.
     * 
     * @param anObject
     * @roseuid 4107DFB603C9
     */
    /*	public void removeDepartments(pd.contact.agency.Department anObject)
    	{
    		initDepartments();
    		departments.remove(anObject);
    	}*///87191

    /**
     * set the type reference for class member agencyType
     * 
     * @param anAgencyType
     * @roseuid 41123AE201C8
     */
    public void setAgencyType(Code anAgencyType)
    {
	/*if (this.agencyType == null || !this.agencyType.equals(anAgencyType))
	{
		markModified(); //87191
	}*/
	setAgencyTypeId("" + anAgencyType.getOID());
	this.agencyType = (Code) new mojo.km.persistence.Reference((PersistentObject) anAgencyType).getObject();
    }

    /**
     * Sets the value of the agencyTypeId property.
     * 
     * @param aAgencyTypeId
     *            the new value of the agencyTypeId property
     */
    public void setAgencyTypeId(String aAgencyTypeId)
    {
	/*if (this.agencyTypeId == null || !this.agencyTypeId.equals(agencyTypeId))
	{
		markModified(); //87191
	}*/
	//agencyType = null;
	this.agencyTypeId = aAgencyTypeId;
    }

    /**
     * Sets the value of the agencyName property.
     * 
     * @param aAgencyName
     *            the new value of the agencyName property
     */
    public void setAgencyName(String aAgencyName)
    {
	/*if (this.agencyName == null || !this.agencyName.equals(aAgencyName))
	{
		markModified(); //87191
	}*/
	agencyName = aAgencyName;
    }

    /**
     * Sets the value of the projectAnalystInd property.
     * 
     * @param aProjectAnalystInd
     *            the new value of the projectAnalystInd property
     */
    /*	public void setProjectAnalystInd(java.lang.String aProjectAnalystInd)
    	{
    		if (this.projectAnalystInd == null || !this.projectAnalystInd.equals(aProjectAnalystInd))
    		{
    			markModified(); //87191
    		}
    		projectAnalystInd = aProjectAnalystInd;
    	}*/

    /**
     * @return pd.contact.Agency
     * @param agencyId
     * @roseuid 4107B06D01B5
     */
    static public Agency find(String agencyId)
    {
	Agency agency = null;
	//IHome home = new Home(); //87191
	if(agencyId!=null && !agencyId.isEmpty()){
	    agency = AgencyHelper.getAgencyById(agencyId);//(Agency) home.find(agencyId, Agency.class); //87191
	}
	return agency;
    }

    /**
     * @return java.util.Iterator
     * @roseuid 41123AE00111
     */
    /*static public Iterator findAll() //87191
    {
    	IHome home = new Home();
    	Iterator iter = home.findAll(Agency.class);
    	return iter;
    }*///87191

    /**
     * @return the agencyId
     */
    public String getAgencyId()
    {
	return agencyId;
    }

    /**
     * @param agencyId
     *            the agencyId to set
     */
    public void setAgencyId(String agencyId)
    {
	this.agencyId = agencyId;
    }

    /**
     * @return the metaDataRespEvent
     */
    public MetaDataResponseEvent getMetaDataRespEvent()
    {
	return metaDataRespEvent;
    }

    /**
     * @param metaDataRespEvent the metaDataRespEvent to set
     */
    public void setMetaDataRespEvent(MetaDataResponseEvent metaDataRespEvent)
    {
	this.metaDataRespEvent = metaDataRespEvent;
    }

    /**
     * @return java.util.Iterator
     * @param attrName
     * @param attrValue
     * @roseuid 4236ED950316
     */
    //87191
    /*static public Iterator findAll(String attrName, String attrValue) {
    	IHome home = new Home();
    	return (Iterator) home.findAll(attrName,attrValue,Agency.class);
    }*///87191

    /**
     * @return java.util.Iterator
     * @param event
     * @roseuid 4107B06D01BB
     */
    //87191
    /*static public Iterator findAll(IEvent event)
    {
    	IHome home = new Home();
    	Iterator iter = home.findAll(event, Agency.class);
    	return iter;
    }*///87191

    /**
     * @roseuid 4107B06D01BB //87191
     */
    /*public void create(){
    	IHome home = new Home();
    	home.bind(this);
    }*///87191

    /**
     * Currently the agencies that are represented on the JMC board directly or
     * indirectly are: County Criminal Courts at Law, Constables Pct.1 thru
     * Pct.8, CrimeStoppers, Community Supervision and Corrections, District
     * Attorney's Office, District Court Criminal Administration, District Court
     * Civil Administration, District Clerk - Family Division, District Clerk -
     * Jury Division, District Clerk - Criminal Division, District Clerk - Civil
     * Division, Gulf Coast Task Force, High Intensity Drug Trafficking Area,
     * Justice of the Peace Courts (Precincts 1 thru 8, Place 1 and 2),
     * Organized Crime Unit (DA and SHF Division), Office of Court Services
     * (formerly, Pre-Trial Services), Harris County Sheriff, First Court of
     * Appeals, 14th Court of Appeals. Note: in JIMS2 some of these
     * organizations will be set up as departments under a parent agency.
     */
    //87191
    /*	public boolean hasJMCBoardRep()
    	{
    		if (projectAnalystInd.equalsIgnoreCase(PDSecurityConstants.Y) ||
    			projectAnalystInd.equalsIgnoreCase(PDSecurityConstants.YES))
    		{
    			return true;
    		}
    		return false;	
    	}*/

    /**
     * @param deptEvent
     * @param class1
     * @return
     */
    /*	public static MetaDataResponseEvent findMeta(IEvent agencyEvent ) {
    		IHome home = new Home();
    		MetaDataResponseEvent iter = home.findMeta(agencyEvent, Agency.class);
    		return iter;
    	}*///87191
}