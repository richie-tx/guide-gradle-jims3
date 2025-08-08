package pd.codetable.criminal;

import java.util.Iterator;

import messaging.codetable.criminal.reply.JuvenileReferralDecisionResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JuvenileReferralDecision extends PersistentObject  {

	private String code; 
	private String description;
	private String category;
	private String department;
	private String dpsCode;
	private String fund;
	private String TJPCCode;
	private String vendor;
	private String dispositionCode;
	private String inactiveInd;
	
	public String getCode() {
		fetch();
		return code;
	}
	public void setCode(String code) {
		if (this.code == null || !this.code.equals(code))
		{
			markModified();
		}
		this.code = code;
	}
	public String getDescription() {
		fetch();
		return description;
	}
	public void setDescription(String description) {
		if (this.description == null || !this.description.equals(description))
		{
			markModified();
		}
		this.description = description;
	}
	public String getCategory() {
		fetch();
		return category;
	}
	public void setCategory(String category) {
		if (this.category == null || !this.category.equals(category))
		{
			markModified();
		}
		this.category = category;
	}
	public String getDepartment() {
		fetch();
		return department;
	}
	public void setDepartment(String department) {
		if (this.department == null || !this.department.equals(department))
		{
			markModified();
		}
		this.department = department;
	}
	public String getDpsCode() {
		fetch();
		return dpsCode;
	}
	public void setDpsCode(String dpsCode) {
		if (this.dpsCode == null || !this.dpsCode.equals(dpsCode))
		{
			markModified();
		}
		this.dpsCode = dpsCode;
	}
	public String getFund() {
		fetch();
		return fund;
	}
	public void setFund(String fund) {
		if (this.fund == null || !this.fund.equals(fund))
		{
			markModified();
		}
		this.fund = fund;
	}
	public String getTJPCCode() {
		fetch();
		return TJPCCode;
	}
	public void setTJPCCode(String TJPCCode) {
		if (this.TJPCCode == null || !this.TJPCCode.equals(TJPCCode))
		{
			markModified();
		}
		this.TJPCCode = TJPCCode;
	}
	public String getVendor() {
		fetch();
		return vendor;
	}
	public void setVendor(String vendor) {
		if (this.vendor == null || !this.vendor.equals(vendor))
		{
			markModified();
		}
		this.vendor = vendor;
	}
	public String getDispositionCode() {
		fetch();
		return dispositionCode;
	}
	public void setDispositionCode(String dispositionCode) {
		if (this.dispositionCode == null || !this.dispositionCode.equals(dispositionCode))
		{
			markModified();
		}
		this.dispositionCode = dispositionCode;
	}
	public String getInactiveInd() {
		fetch();
		return inactiveInd;
	}
	public void setInactiveInd(String inactiveInd) {
		if (this.inactiveInd == null || !this.inactiveInd.equals(inactiveInd))
		{
			markModified();
		}
		this.inactiveInd = inactiveInd;
	}
	
	/**
	 * Find all Juvenile Referral Decision.
	 * @return java.util.Iterator
	 */
	public static Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(JuvenileReferralDecision.class);
	}
	/**
	* @roseuid 41AC81DE0186
	* @param code
	* @return a Juvenile Referral Decision object
	*/
	public static JuvenileReferralDecision find(String code)
	{
		JuvenileReferralDecision jc = null;
		IHome home = new Home();

		jc = (JuvenileReferralDecision) home.find(code, JuvenileReferralDecision.class);

		return jc;
	}

	/**
	 * Find all Juvenile Referral Decision.
	 * @return java.util.Iterator
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, attributeValue, JuvenileReferralDecision.class);
	}
	
	public JuvenileReferralDecisionResponseEvent valueObject(){
	    
	    JuvenileReferralDecisionResponseEvent response = new JuvenileReferralDecisionResponseEvent();
	    
	    response.setCategory(this.getCategory());
	    response.setCode(this.getCode());
	    response.setDepartment(this.getDepartment());
	    response.setDescription(this.getDescription());
	    response.setDispositionCode(this.getDispositionCode());
	    response.setDpsCode(this.getDpsCode());
	    response.setFund(this.getFund());
	    response.setInactiveInd(this.getInactiveInd());
	    response.setTJPCCode(this.getTJPCCode());
	    response.setVendor(this.getVendor());
    
	    return response;
	}
}
