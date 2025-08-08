package pd.supervision.administerserviceprovider;

import java.util.Iterator;

import messaging.administerserviceprovider.CreateServiceProviderContactEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

/**
* @roseuid 447EFA4500C3
*/
public class OutSourcedSP_Profile extends SP_Profile {
	private String jobTitle;
	/**
	* @roseuid 447EFA4500C3
	*/
	public OutSourcedSP_Profile() {
	}

	/**
	* Access method for the jobTitle property.
	* @return the current value of the jobTitle property
	*/
	public String getJobTitle() {
		fetch();
		return jobTitle;
	}
	/**
	* Sets the value of the jobTitle property.
	* @param aJobTitle the new value of the jobTitle property
	*/
	public void setJobTitle(String aJobTitle) {
		if (this.jobTitle == null || !this.jobTitle.equals(aJobTitle)) {
			markModified();
		}
		jobTitle = aJobTitle;
	}
	/**
	* @param CreateServiceProviderContactEvent
	*/
	public void setOutSourcedSP_Profile(CreateServiceProviderContactEvent spProfileEvent, boolean update) {
		
		this.setInHouse(spProfileEvent.isInHouse());		
		this.setFirstName(spProfileEvent.getFullName().getFirstName());
		this.setMiddleName(spProfileEvent.getFullName().getMiddleName());
		this.setLastName(spProfileEvent.getFullName().getLastName());
		//this.setNotes(spProfileEvent.getNotes()); //88615
		this.setIsAdminContact(spProfileEvent.getIsAdminContact());
		this.setEmployeeId(spProfileEvent.getEmployeeId());
		this.setServiceProviderId(spProfileEvent.getServiceProviderId());
		if(spProfileEvent.getWorkPhone().getAreaCode()!=null&&spProfileEvent.getWorkPhone().getPrefix()!=null&&spProfileEvent.getWorkPhone().getLast4Digit()!=null)
		{
		    this.setPhoneNum(spProfileEvent.getWorkPhone().getAreaCode()+spProfileEvent.getWorkPhone().getPrefix()+spProfileEvent.getWorkPhone().getLast4Digit());
		}
		else
		    this.setPhoneNum(null);
		//this.setExtnNum(spProfileEvent.getWorkPhone().getExt());
		this.setEmail(spProfileEvent.getEmail());
		//this.setCellPhone(spProfileEvent.getCellPhone().getAreaCode()+spProfileEvent.getCellPhone().getPrefix()+spProfileEvent.getCellPhone().getLast4Digit());
		//this.setPager(spProfileEvent.getPager().getAreaCode()+spProfileEvent.getPager().getPrefix()+spProfileEvent.getPager().getLast4Digit());
		//this.setFaxNum(spProfileEvent.getFax().getAreaCode()+spProfileEvent.getFax().getPrefix()+spProfileEvent.getFax().getLast4Digit());
		//this.setPrefix(spProfileEvent.getFullName().getPrefix());
		//this.setSuffix(spProfileEvent.getFullName().getSuffix());
		//this.setJobTitle(spProfileEvent.getJobTitle());	
		this.setStatusId(spProfileEvent.getStatusId());
		/*if(update) {
			this.setEmployeeId(spProfileEvent.getJuvServProvProfId());
		}*/
	}
	
	public void createOID() {
		markModified();
		IHome home = new Home();
		home.bind(this);
	}
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		Iterator serviceProviderProfiles = null;
		serviceProviderProfiles = home.findAll(attrName, attrValue, OutSourcedSP_Profile.class);
		return serviceProviderProfiles;
	}
	
	static public Iterator findAllByNumericAttribute(String attrName, String attrValue) {
		IHome home = new Home();
		return home.findAll(attrName, new Integer(attrValue), OutSourcedSP_Profile.class);
	}
		
	static public SP_Profile find(String serviceProviderProfileId) {
		IHome home = new Home();
		return (SP_Profile) home.find(serviceProviderProfileId, OutSourcedSP_Profile.class);
	}	
	public void updateOutSourcedProfileStatus(CreateServiceProviderContactEvent createEvent) {
		this.setStatusId(createEvent.getStatusId());
	}		
}
