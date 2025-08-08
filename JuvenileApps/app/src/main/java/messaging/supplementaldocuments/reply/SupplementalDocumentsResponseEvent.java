/*
 * Created on March 16, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.supplementaldocuments.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author RCapestani
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class SupplementalDocumentsResponseEvent extends ResponseEvent{
	
	private Integer formCatId;
	private String formGroup;
	private String formTitle;
	private String url;
	private String userParm;
	private String passWordParm;
	
	/**
	 * @return the formCatId
	 */
	public Integer getFormCatId() {
		return formCatId;
	}
	
	/**
	 * @param formCatId the formCatId to set
	 */
	public void setFormCatId(Integer formCatId) {
		this.formCatId = formCatId;
	}

	/**
	 * @return the formGroup
	 */
	public String getFormGroup() {
		return formGroup;
	}

	/**
	 * @param formGroup the formGroup to set
	 */
	public void setFormGroup(String formGroup) {
		this.formGroup = formGroup;
	}

	/**
	 * @return the formTitle
	 */
	public String getFormTitle() {
		return formTitle;
	}

	/**
	 * @param formTitle the formTitle to set
	 */
	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the userParm
	 */
	public String getUserParm() {
		return userParm;
	}

	/**
	 * @param userParm the userParm to set
	 */
	public void setUserParm(String userParm) {
		this.userParm = userParm;
	}

	/**
	 * @return the passWordParm
	 */
	public String getPassWordParm() {
		return passWordParm;
	}

	/**
	 * @param passWordParm the passWordParm to set
	 */
	public void setPassWordParm(String passWordParm) {
		this.passWordParm = passWordParm;
	}
	
}
