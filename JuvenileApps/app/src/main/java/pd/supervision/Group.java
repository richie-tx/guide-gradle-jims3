package pd.supervision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import mojo.km.context.multidatasource.Home;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.contact.agency.Agency;
import pd.supervision.administerserviceprovider.csserviceprovider.CSServiceProvider;

/**
* @roseuid 42F7C4110242
*/
public class Group extends PersistentObject {
	private String parentGroupId;
	private String agencyId;
	private String statusCd;
	private int groupLevel;
	private String groupName;
	private Collection subgroups = new ArrayList();
	
	/**
	* Resolved references.
	*/
	public Agency agency = null;
	public Group parentGroup = null;

	/**
	* @roseuid 42F7C4110242
	*/
	public Group() {
	}
	/**
	* Access method for the groupName property.
	* @return the current value of the groupName property
	*/
	public String getGroupName() {
		fetch();
		return groupName;
	}
	/**
	* Sets the value of the groupName property.
	* @param aGroupName the new value of the groupName property
	*/
	public void setGroupName(String aGroupName) {
		if (this.groupName != aGroupName) {
			markModified();
		}
		groupName = aGroupName;
	}
	/**
	* Access method for the parentGroupId property.
	* @return the current value of the parentGroupId property
	*/
	public String getParentGroupId() {
		fetch();
		return parentGroupId;
	}
	/**
	* Sets the value of the parentGroupId property.
	* @param aParentGroupId the new value of the parentGroupId property
	*/
	public void setParentGroupId(String aParentGroupId) {
		if (this.parentGroupId != aParentGroupId) {
			markModified();
		}
		parentGroupId = aParentGroupId;
	}
	/**
	* Access method for the groupLevel property.
	* @return the current value of the groupLevel property
	*/
	public int getGroupLevel() {
		fetch();
		return groupLevel;
	}
	/**
	* Sets the value of the groupLevel property.
	* @param aGroupLevel the new value of the groupLevel property
	*/
	public void setGroupLevel(int aGroupLevel) {
		if (this.groupLevel != aGroupLevel) {
			markModified();
		}
		groupLevel = aGroupLevel;
	}
	/**
	* Set the reference value to class :: pd.contact.agency.Agency
	*/
	public void setAgencyId(String agencyId) {
		if (this.agencyId == null || !this.agencyId.equals(agencyId)) {
			markModified();
		}
		agency = null;
		this.agencyId = agencyId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getStatusCd() {
		fetch();
		return statusCd;
	}
	
	/**
	 * 
	 * @param statusCd
	 */
	public void setStatusCd(String statusCd) {
		if (this.statusCd != statusCd) {
			markModified();
		}
		this.statusCd = statusCd;
	}
	
	/**
	* Get the reference value to class :: pd.contact.agency.Agency
	*/
	public String getAgencyId() {
		fetch();
		return agencyId;
	}
	/**
	* Initialize class relationship to class pd.contact.agency.Agency
	*/
	private void initAgency() {
		if (agency == null) {
			try {
				agency = (Agency) new mojo.km.persistence.Reference(agencyId, Agency.class).getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.contact.agency.Agency
	*/
	public Agency getAgency() {
		initAgency();
		return agency;
	}
	/**
	* set the type reference for class member agency
	*/
	public void setAgency(Agency agency) {
		if (this.agency == null || !this.agency.equals(agency)) {
			markModified();
		}
		if (agency.getOID() == null) {
			new mojo.km.persistence.Home().bind(agency);
		}
		setAgencyId("" + agency.getOID());
		this.agency = (Agency) new mojo.km.persistence.Reference(agency).getObject();
	}


	/**
	* Initialize class relationship to class pd.contact.agency.Agency
	*/
	private void initParentGroup() {
		if (parentGroup == null) {
			try {
				parentGroup = (Group) new mojo.km.persistence.Reference(parentGroupId, Group.class).getObject();
			} catch (Throwable t) {
			}
		}
	}
	/**
	* Gets referenced type pd.contact.agency.Agency
	*/
	public Group getParentGroup() {
		initParentGroup();
		return parentGroup;
	}


	public Collection getSubgroups()
	{
		return Collections.unmodifiableCollection(subgroups);
	}

	private void addSubgroup( Group subgroup )
	{
		subgroups.add( subgroup );
	}
	
	public static Group find( String groupId )
	{
		Home home = new Home();
		return (Group)home.find( groupId, Group.class );
	}
	
	public static Iterator findAll( String attrName, String attrId )
	{
		Home home = new Home();
		return home.findAll( attrName, attrId, Group.class );
	}
	
	/**
	* 
	*/
	public static Iterator findAll( String agencyId ) 
	{
	
		// First iteration - Add to id map and add to level 1 list if appropriate.
		Home home = new Home();
		Iterator groups = home.findAll( "agencyId", agencyId, Group.class );
//		Iterator groups = list.iterator();  

		return groups;
	}

	static public Iterator findAll(IEvent event)
	{
	    	//initialize lookup objects
		IHome home = new Home();
		
			//use delegate to lookup groups matching the given event values
		return home.findAll(event, Group.class);
	}//end of findAll()
	
	public Group[] getGroupList()
	{
		Group[] groups = new Group[3];
		
		Group group = this;
		while ( group != null )
		{
			if ( group.getGroupLevel() == 3 )
			{
				groups[2] = group;
				group = group.getParentGroup();
			}
			else if ( group.getGroupLevel() == 2 )
			{
				groups[1] = group;
				group = group.getParentGroup();
			}
			else
			{
				groups[0] = group;
				group = null;
			}
		}
		return groups;
	}
	
}
