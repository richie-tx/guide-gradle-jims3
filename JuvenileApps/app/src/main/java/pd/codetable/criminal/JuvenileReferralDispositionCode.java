package pd.codetable.criminal;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

import java.util.Comparator;
import java.util.Iterator;

/**
* @author palcocer
* M204 REFERRAL.DECISION complex code table
JuvenileDispositionCode entity
*/
public class JuvenileReferralDispositionCode extends PersistentObject{
	
	private String category;
	private String code; //OID
	private String department;
	private String description; 
	private String dpsCode;
	private String fund;
	private String tjpcCode;
	private String vendor;
	private String dispositionCode;
	
	//US 71173
	private String inactiveInd;
	private String codeWithDescription;
	
	
	/**
	* @roseuid 41ACCAE60161
	*/
	public JuvenileReferralDispositionCode() {
	}
	/**
	* @return JuvenileDispositionCode object
	* @param codeNum
	* @roseuid 41ACA9680353
	Find JuvenileDispositionCode object by codeId
	*/
	static public JuvenileReferralDispositionCode find(String codeNum) {
		JuvenileReferralDispositionCode jdc = null;
		IHome home = new Home();
		jdc = (JuvenileReferralDispositionCode) home.find(codeNum, JuvenileReferralDispositionCode.class);
		return jdc;
	}
	/**
	* Finds all JuvenileDispositionCode objects
	* @return all JuvenileDispositionCode objects
	*/
	static public Iterator findAll() {
		IHome home = new Home();
		return home.findAll(JuvenileReferralDispositionCode.class);
	}

	/**
	* Finds all JuvenileDispositionCode by an attribute value
	* @param attributeName
	* @param attributeValue
	* @return 
	*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		return new Home().findAll(attributeName, attributeValue, JuvenileReferralDispositionCode.class);
	}
	public void setCategory(String string) {
		if (this.category == null || !this.category.equals(string)) {
			markModified();
		}
		category = string;
	}
	public String getCategory() {
		fetch();
		return category;
	}
	public void setCode(String string) {
		if (this.code == null || !this.code.equals(string)) {
			markModified();
		}
		code = string;
	}
	public String getCode() {
		fetch();
		return code;
	}
	public void setDepartment(String string) {
		if (this.department == null || !this.department.equals(string)) {
			markModified();
		}
		department = string;
	}
	public String getDepartment() {
		fetch();
		return department;
	}
	public void setDescription(String string) {
		if (this.description == null || !this.description.equals(string)) {
			markModified();
		}
		description = string;		
	}
	public String getDescription() {
		fetch();
		return description;
	}
	public void setDpsCode(String string) {
		if (this.dpsCode == null || !this.dpsCode.equals(string)) {
			markModified();
		}
		dpsCode = string;		
	}
	public String getDpsCode() {
		fetch();
		return dpsCode;
	}
	public void setFund(String string) {
		if (this.fund == null || !this.fund.equals(string)) {
			markModified();
		}
		fund = string;
	}
	public String getFund() {
		fetch();
		return fund;
	}
	public void setTjpcCodeString(String string) {
		if (this.tjpcCode == null || !this.tjpcCode.equals(string)) {
			markModified();
		}
		tjpcCode = string;
	}
	public void setTjpcCode(String string) {
		if (this.tjpcCode == null || !this.tjpcCode.equals(string)) {
			markModified();
		}
		tjpcCode = string;
	}
	public String getTjpcCode() {
		fetch();
		return tjpcCode;
	}
	public void setVendor(String string) {
		if (this.vendor == null || !this.vendor.equals(string)) {
			markModified();
		}
		vendor = string;
	}
	public String getVendor() {
		fetch();
		return vendor;
	}
	public void setDispositionCode(String string) {
		if (this.dispositionCode == null || !this.dispositionCode.equals(string)) {
			markModified();
		}
		dispositionCode = string;
	}
	public String getDispositionCode() {
		fetch();
		return dispositionCode;
	}
	
	public static Comparator CodeComparator = new Comparator(){
		public int compare(Object obj1, Object obj2){
			
			if(obj1==null || !(obj1 instanceof JuvenileReferralDispositionCode))
				return 0;
			if(obj2==null || !(obj2 instanceof JuvenileReferralDispositionCode))
				return 0;
			
			String code1= ((JuvenileReferralDispositionCode)obj1).getDescription();
			String code2= ((JuvenileReferralDispositionCode)obj2).getDescription();
			if(code1==null) return 1;
			if(code2==null) return -1;
			
			return code1.compareTo(code2);
			
		}
	};


	/**
	 * @return the inactiveInd
	 */
	public String getInactiveInd()
	{
	    fetch();
	    return inactiveInd;
	}
	/**
	 * @param inactiveInd the inactiveInd to set
	 */
	public void setInactiveInd(String inactiveInd)
	{
	    this.inactiveInd = inactiveInd;
	}
	/**
	 * @return the codeWithDescription
	 */
	public String getCodeWithDescription()
	{
	    return codeWithDescription;
	}
	/**
	 * @param codeWithDescription the codeWithDescription to set
	 */
	public void setCodeWithDescription(String codeWithDescription)
	{
	    this.codeWithDescription = codeWithDescription;
	}

}
