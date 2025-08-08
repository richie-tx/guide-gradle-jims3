/*
 * Created on March 15, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.supplementaldocuments;

import mojo.km.messaging.RequestEvent;

/**
 * @author RCapestani
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class GetSupplementalDocumentsEvent extends RequestEvent
{
	private String formCat_Id = "";
	
	private String formGroup = "";
	
	private String formTitle = "";
	
	private String url = "";
	
	private String userParm = "";
	
	private String passWordParam = "";

	/**
	 * @return the formCat_Id
	 */
	public String getFormCat_Id() {
		return formCat_Id;
	}
	
	/**
	 * @param formCat_Id the formCat_Id to set
	 */
	public void setFormCat_Id(String formCat_Id) {
		this.formCat_Id = formCat_Id;
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
	 * @return the passWordParam
	 */
	public String getPassWordParam() {
		return passWordParam;
	}

	/**
	 * @param passWordParam the passWordParam to set
	 */
	public void setPassWordParam(String passWordParam) {
		this.passWordParam = passWordParam;
	}
}
