package pd.supervision.supervisionorder;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
* @roseuid 43B2E74200DA
*/
public class SupervisionOrderPrintTemplate extends PersistentObject {

	private String printTemplateId;
	private String subSystem;
	private String agencyID;
	private String courtCategory;
	private String courtId;
	private String orderType;
	private String versionType;
	private String versionNumber;
	private String versionDate;
	private String templateLang;
	private String orderTitle;

	
	/**
	* @roseuid 43B2E74200DA
	*/
	public SupervisionOrderPrintTemplate() {
	}
	/**
	* @roseuid 43B2B6EF01D4
	*/
	public void bind() {
		//markModified();
	}
	
	/**
	* @return 
	* @param event
	* @roseuid 438F22CA0277
	*/
	static public Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(SupervisionOrderPrintTemplate.class);
	}
	
	/**
	* Finds all OrderTitles/PrintTemplates by an attribute value
	* @return 
	* @param attributeName
	* @param attributeValue
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator orderTitles = home.findAll(attributeName, attributeValue, SupervisionOrderPrintTemplate.class);
		return orderTitles;
	}	
	
	/**
	* Get the reference value to class :: pd.supervision.Court
	*/
	public String getCourtId() {
		fetch();
		return courtId;
	}


	/**
	 * @return
	 */
	public String getCourtCategory()
	{
		fetch();
		return courtCategory;
	}

	/**
	 * @return
	 */
	public String getOrderType()
	{
		fetch();
		return orderType;
	}

	/**
	 * @return
	 */
	public String getPrintTemplateId()
	{
		fetch();
		return printTemplateId;
	}

	/**
	 * @return
	 */
	public String getTemplateLang()
	{
		fetch();
		return templateLang;
	}

	/**
	 * @return
	 */
	public String getVersionDate()
	{
		fetch();
		return versionDate;
	}

	/**
	 * @return
	 */
	public String getVersionNumber()
	{
		fetch();
		return versionNumber;
	}

	/**
	 * @return
	 */
	public String getVersionType()
	{
		fetch();
		return versionType;
	}

	/**
	 * @param string
	 */
	public void setCourtCategory(String string)
	{
		courtCategory = string;
	}

	/**
	 * @param string
	 */
	public void setCourtId(String string)
	{
		courtId = string;
	}

	/**
	 * @param string
	 */
	public void setOrderType(String string)
	{
		orderType = string;
	}

	/**
	 * @param string
	 */
	public void setPrintTemplateId(String string)
	{
		printTemplateId = string;
	}

	/**
	 * @param string
	 */
	public void setTemplateLang(String string)
	{
		templateLang = string;
	}

	/**
	 * @param string
	 */
	public void setVersionDate(String string)
	{
		versionDate = string;
	}

	/**
	 * @param string
	 */
	public void setVersionNumber(String string)
	{
		versionNumber = string;
	}

	/**
	 * @param string
	 */
	public void setVersionType(String string)
	{
		versionType = string;
	}

	/**
	 * @return
	 */
	public String getAgencyID()
	{
		fetch();
		return agencyID;
	}

	/**
	 * @return
	 */
	public String getOrderTitle()
	{
		fetch();
		return orderTitle;
	}

	/**
	 * @return
	 */
	public String getSubSystem()
	{
		fetch();
		return subSystem;
	}

	/**
	 * @param string
	 */
	public void setAgencyID(String string)
	{
		agencyID = string;
	}

	/**
	 * @param string
	 */
	public void setOrderTitle(String string)
	{
		orderTitle = string;
	}

	/**
	 * @param string
	 */
	public void setSubSystem(String string)
	{
		subSystem = string;
	}

}
