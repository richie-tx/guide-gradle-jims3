package pd.codetable.person;

import java.util.Iterator;
import pd.codetable.ICodetable;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 4279069203C8
*/
public class SocialElementCode extends PersistentObject implements ICodetable{
	private String inactiveInd;
	private String element;
	private String reportGroup;
	private String code;
	private String codeDescription;
	private String socialElementCodeId;
	private String elementDescription;
	/**
	* @roseuid 4279069203C8
	*/
	public SocialElementCode() {
	}
	/**
	* @roseuid 427295FF01E4
	*/
	public static SocialElementCode find(String oid) {
		return (SocialElementCode) new Home().find(oid, SocialElementCode.class);
	}
	/**
	* @return java.lang.String
	*/
	public String getCode() {
		fetch();
		return code;
	}
	/**
	* @return java.lang.String
	*/
	public String getCodeDescription() {
		fetch();
		return codeDescription;
	}
	/**
	* @return java.lang.String
	*/
	public String getElement() {
		fetch();
		return element;
	}
	/**
	* @return java.lang.String
	*/
	public String getElementDescription() {
		fetch();
		return elementDescription;
	}
	/**
	* @return java.lang.String
	*/
	public String getInactiveInd() {
		fetch();
		return inactiveInd;
	}
	/**
	* @return java.lang.String
	*/
	public String getReportGroup() {
		fetch();
		return reportGroup;
	}
	/**
	* @param theCode
	*/
	public void setCode(String theCode) {
		if (this.code == null || !this.code.equals(theCode)) {
			markModified();
		}
		this.code = theCode;
	}
	/**
	* @param theDescription
	*/
	public void setCodeDescription(String theDescription) {
		if (this.codeDescription == null || !this.codeDescription.equals(theDescription)) {
			markModified();
		}
		codeDescription = theDescription;
	}
	/**
	* @param theElement
	*/
	public void setElement(String theElement) {
		if (this.element == null || !this.element.equals(theElement)) {
			markModified();
		}
		this.element = theElement;
	}
	/**
	* @param theDescription
	*/
	public void setElementDescription(String theDescription) {
		if (this.elementDescription == null || !this.elementDescription.equals(theDescription)) {
			markModified();
		}
		elementDescription = theDescription;
	}
	/**
	* @param theInactiveInd
	*/
	public void setInactiveInd(String theInactiveInd) {
		if (this.inactiveInd == null || !this.inactiveInd.equals(theInactiveInd)) {
			markModified();
		}
		this.inactiveInd = theInactiveInd;
	}
	/**
	* @param theReportGroup
	*/
	public void setReportGroup(String theReportGroup) {
		if (this.reportGroup == null || !this.reportGroup.equals(theReportGroup)) {
			markModified();
		}
		this.reportGroup = theReportGroup;
	}
	
	/**
	* @return 
	*/
	public String getSocialElementCodeId() {
		return "" + getOID();
	}
	/**
	* @param codeId
	*/
	public void setSocialElementCodeId(String codeId) {
		if (this.socialElementCodeId == null || !this.socialElementCodeId.equals(codeId)) {
			markModified();
		}
		socialElementCodeId = codeId;
	}
	
	/**
	* Finds all SocialElementCodes by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator casefiles = home.findAll(attributeName, attributeValue, SocialElementCode.class);
		return casefiles;
	}
	/* (non-Javadoc)
	 * @see pd.codetable.ICodetable#findAll()
	 */
	public Iterator findAll() {
		return new Home().findAll(SocialElementCode.class);
	}
	/* (non-Javadoc)
	 * @see pd.codetable.ICodetable#inActivate()
	 */
	public void inActivate() {
		// TODO Auto-generated method stub
		
	}
}
