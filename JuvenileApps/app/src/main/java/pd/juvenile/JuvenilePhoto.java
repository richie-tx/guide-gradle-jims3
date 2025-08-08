package pd.juvenile;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.utilities.CollectionUtil;

/**
 * @roseuid 42B18E4E0232
 */
public class JuvenilePhoto extends PersistentObject {
	private Date entryDate;
	private byte[] picture;
	private String juvenileId;
	private byte[] thumbPic;
	String pictureName;

	/**
	 * @roseuid 42B18E4E0232
	 */
	public JuvenilePhoto() {
	}

	/**
	 * Access method for the picture property.
	 * 
	 * @return the current value of the picture property
	 */
	public byte[] getThumbPic() {
		fetch();
		return thumbPic;
	}

	/**
	 * Sets the value of the picture property.
	 * 
	 * @param aPicture
	 *            the new value of the picture property
	 */
	public void setThumbPic(byte[] aPicture) {

		thumbPic = aPicture;
	}

	/**
	 * Access method for the entryDate property.
	 * 
	 * @return the current value of the entryDate property
	 */
	public Date getEntryDate() {
		fetch();
		return entryDate;
	}

	/**
	 * Sets the value of the entryDate property.
	 * 
	 * @param aEntryDate
	 *            the new value of the entryDate property
	 */
	public void setEntryDate(Date aEntryDate) {
		this.entryDate = aEntryDate;
	}

	/**
	 * Access method for the picture property.
	 * 
	 * @return the current value of the picture property
	 */
	public byte[] getPicture() {
		fetch();
		return picture;
	}

	/**
	 * Sets the value of the picture property.
	 * 
	 * @param aPicture
	 *            the new value of the picture property
	 */
	public void setPicture(byte[] aPicture) {
		this.picture = aPicture;
	}

	/**
	 * @param juvenileNum
	 * @roseuid 42B1830700CE
	 */
	static public JuvenilePhoto find(String aPictureName) {
		JuvenilePhoto photo = null;
		IHome home = new Home();
		photo = (JuvenilePhoto) home.find(aPictureName, JuvenilePhoto.class);
		return photo;
	}

	/**
     *  
     */
	static public List findAll(String juvId) {
		IHome home = new Home();
		Iterator iter = home.findAll("juvenileId", juvId, JuvenilePhoto.class);

		List photos = CollectionUtil.iteratorToList(iter);

		Collections.sort(photos, new Comparator() {
			public int compare(Object o1, Object o2) {
				JuvenilePhoto p1 = (JuvenilePhoto) o1;
				JuvenilePhoto p2 = (JuvenilePhoto) o2;
				// Order reversed so that newest date is at [0].
				return p2.getEntryDate().compareTo(p1.getEntryDate());
			}
		});

		return photos;
	}

	/**
	 * Set the reference value to class :: pd.juvenile.Juvenile
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	/**
	 * Get the reference value to class :: pd.juvenile.Juvenile
	 */
	public String getJuvenileId() {
		fetch();
		return juvenileId;
	}

	/**
	 * @return
	 */
	public String getPictureName() {
		fetch();
		return pictureName;
	}

	/**
	 * @param string
	 */
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
}