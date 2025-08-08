/**
 * 
 */
package pd.supervision.supplementaldocuments;

import java.util.Iterator;

import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.context.multidatasource.Home;

/** 
 * 
 * @stereotype entity
 * 
 * @author rcapestani
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class SupplementalDocuments extends PersistentObject {
	
	private Integer formCatId;
	private String formGroup;
	private String formTitle;
	private String url;
	private String userParm;
	private String passWordParm;

	/**
	 * 
	 * @return 
	 */
	static public SupplementalDocuments find(String OID)
	{
		IHome home = new Home();
		Object obj = home.find(OID, SupplementalDocuments.class);
		return (SupplementalDocuments) obj;
	}
	
	/**
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		return (Iterator) home.findAll(attrName, attrValue.toUpperCase(), SupplementalDocuments.class);
	}
	
	
	static public Iterator findAll()
	{
		IHome home = new Home();
		return (Iterator) home.findAll(SupplementalDocuments.class);
	}
	
	/** 
	 * @return the formCatId
	 *
	 */
	public Integer getFormCatId() {
		// begin-user-code
		fetch();
		return formCatId;
		// end-user-code
	}

	/** 
	 * @param formCatId the formCatId to set
	 *
	 */
	public void setFormCatId(Integer formCatId) {
		// begin-user-code
		if (this.formCatId == null || !this.formCatId.equals(formCatId))
		{
			markModified();
		}
		this.formCatId = formCatId;
		// end-user-code
	}

	/** 
	 * @return the formGroup
	 *
	 */
	public String getFormGroup() {
		// begin-user-code
		fetch();
		return formGroup;
		// end-user-code
	}

	/** 
	 * @param formGroup the formGroup to set
	 * 
	 */
	public void setFormGroup(String formGroup) {
		// begin-user-code
		if (this.formGroup == null || !this.formGroup.equals(formGroup))
		{
			markModified();
		}
		this.formGroup = formGroup;
		// end-user-code
	}

	/** 
	 * @return the formTitle
	 *
	 */
	public String getFormTitle() {
		// begin-user-code
		fetch();
		return formTitle;
		// end-user-code
	}

	/** 
	 * @param formTitle the formTitle to set
	 *
	 */
	public void setFormTitle(String formTitle) {
		// begin-user-code
		if (this.formTitle == null || !this.formTitle.equals(formTitle))
		{
			markModified();
		}
		this.formTitle = formTitle;
		// end-user-code
	}

	/** 
	 * @return the url
	 *
	 */
	public String getUrl() {
		// begin-user-code
		fetch();
		return url;
		// end-user-code
	}

	/** 
	 * @param url the url to set
	 *
	 */
	public void setUrl(String url) {
		// begin-user-code
		if (this.url == null || !this.url.equals(url))
		{
			markModified();
		}
		this.url = url;
		// end-user-code
	}

	/** 
	 * @return the userParm
	 *
	 */
	public String getUserParm() {
		// begin-user-code
		fetch();
		return userParm;
		// end-user-code
	}

	/** 
	 * @param userParm the userParm to set
	 *
	 */
	public void setUserParm(String userParm) {
		// begin-user-code
		if (this.userParm == null || !this.userParm.equals(userParm))
		{
			markModified();
		}
		this.userParm = userParm;
		// end-user-code
	}

	/** 
	 * @return the passWordParm
	 *
	 */
	public String getPassWordParm() {
		// begin-user-code
		fetch();
		return passWordParm;
		// end-user-code
	}

	/** 
	 * @param passWordParm the passWordParm to set
	 *
	 */
	public void setPassWordParm(String passWordParm) {
		// begin-user-code
		if (this.passWordParm == null || !this.passWordParm.equals(passWordParm))
		{
			markModified();
		}
		this.passWordParm = passWordParm;
		// end-user-code
	}
}