/**
 * 
 */
package pd.supervision.administercaseload;

import java.util.Date;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author cc_cwalters
 *
 */
public class SuperviseePhoto extends PersistentObject 
{
	private String spn;
	private String firstName;
	private String lastName;
	private byte[] superviseePhoto;
	private byte[] superviseeThumbnail;
	private Date datePrinted;
	private Date dateModified;
	private Date dateCreated;
	private Date dateIssued;
	private Date dateReplaced;
	
	
	public String getSpn() {
		return spn;
	}
	public void setSpn(String spn) {
		this.spn = spn;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public byte[] getSuperviseePhoto() {
		return superviseePhoto;
	}
	public void setSuperviseePhoto(byte[] superviseePhoto) {
		this.superviseePhoto = superviseePhoto;
	}
	public byte[] getSuperviseeThumbnail() {
		return superviseeThumbnail;
	}
	public void setSuperviseeThumbnail(byte[] superviseeThumbnail) {
		this.superviseeThumbnail = superviseeThumbnail;
	}
	public Date getDatePrinted() {
		return datePrinted;
	}
	public void setDatePrinted(Date datePrinted) {
		this.datePrinted = datePrinted;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateIssued() {
		return dateIssued;
	}
	public void setDateIssued(Date dateIssued) {
		this.dateIssued = dateIssued;
	}
	public Date getDateReplaced() {
		return dateReplaced;
	}
	public void setDateReplaced(Date dateReplaced) {
		this.dateReplaced = dateReplaced;
	}

    /**
     * Find SuperviseePhoto by OID
     */
	static public SuperviseePhoto find(String superviseeID)
	{
	    	//initialize lookup objects
	    SuperviseePhoto superviseePhoto = null;
		IHome home = new Home();

			//use delegate to locate given supervisee photo
		superviseePhoto = (SuperviseePhoto) home.find(superviseeID, SuperviseePhoto.class);
		return superviseePhoto ;
	}//end of find()
	
}
