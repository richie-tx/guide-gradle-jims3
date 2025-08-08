package pd.codetable.criminal;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JuvenileReferralSource extends PersistentObject{

	private String code; 
	private String description;
	private String reportGroup;
	private String inactiveInd;
	private String otherSource1;
	private String otherSource2;
	private String otherSource3;
	private String otherSource4;
	private String otherSource5;
	private String otherSource6;
	private String otherSource7;
	private String otherSource8;
	private String otherSource9;
	private String otherSource10;
	
	
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
	public String getReportGroup() {
		fetch();
		return reportGroup;
	}
	public void setReportGroup(String reportGroup) {
		if (this.reportGroup == null || !this.reportGroup.equals(reportGroup))
		{
			markModified();
		}
		this.reportGroup = reportGroup;
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
	public String getOtherSource1() {
		fetch();
		return otherSource1;
	}
	public void setOtherSource1(String otherSource1) {
		if (this.otherSource1 == null || !this.otherSource1.equals(otherSource1))
		{
			markModified();
		}
		this.otherSource1 = otherSource1;
	}
	public String getOtherSource2() {
		fetch();
		return otherSource2;
	}
	public void setOtherSource2(String otherSource2) {
		if (this.otherSource2 == null || !this.otherSource2.equals(otherSource2))
		{
			markModified();
		}
		this.otherSource2 = otherSource2;
	}
	public String getOtherSource3() {
		fetch();
		return otherSource3;
	}
	public void setOtherSource3(String otherSource3) {
		if (this.otherSource3 == null || !this.otherSource3.equals(otherSource3))
		{
			markModified();
		}
		this.otherSource3 = otherSource3;
	}
	public String getOtherSource4() {
		fetch();
		return otherSource4;
	}
	public void setOtherSource4(String otherSource4) {
		if (this.otherSource4 == null || !this.otherSource4.equals(otherSource4))
		{
			markModified();
		}
		this.otherSource4 = otherSource4;
	}
	public String getOtherSource5() {
		fetch();
		return otherSource5;
	}
	public void setOtherSource5(String otherSource5) {
		if (this.otherSource5 == null || !this.otherSource5.equals(otherSource5))
		{
			markModified();
		}
		this.otherSource5 = otherSource5;
	}
	public String getOtherSource6() {
		fetch();
		return otherSource6;
	}
	public void setOtherSource6(String otherSource6) {
		if (this.otherSource6 == null || !this.otherSource6.equals(otherSource6))
		{
			markModified();
		}
		this.otherSource6 = otherSource6;
	}
	public String getOtherSource7() {
		fetch();
		return otherSource7;
	}
	public void setOtherSource7(String otherSource7) {
		if (this.otherSource7 == null || !this.otherSource7.equals(otherSource7))
		{
			markModified();
		}
		this.otherSource7 = otherSource7;
	}
	public String getOtherSource8() {
		fetch();
		return otherSource8;
	}
	public void setOtherSource8(String otherSource8) {
		if (this.otherSource8 == null || !this.otherSource8.equals(otherSource8))
		{
			markModified();
		}
		this.otherSource8 = otherSource8;
	}
	public String getOtherSource9() {
		fetch();
		return otherSource9;
	}
	public void setOtherSource9(String otherSource9) {
		if (this.otherSource9 == null || !this.otherSource9.equals(otherSource9))
		{
			markModified();
		}
		this.otherSource9 = otherSource9;
	}
	public String getOtherSource10() {
		fetch();
		return otherSource10;
	}
	public void setOtherSource10(String otherSource10) {
		if (this.otherSource10 == null || !this.otherSource10.equals(otherSource10))
		{
			markModified();
		}
		this.otherSource10 = otherSource10;
	}
	
	/**
	 * Find all Juvenile Referral Source.
	 * @return java.util.Iterator
	 */
	public static Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(JuvenileReferralSource.class);
	}
	/**
	* @roseuid 41AC81DE0186
	* @param code
	* @return a Juvenile Referral Source object
	*/
	public static JuvenileReferralSource find(String code)
	{
		JuvenileReferralSource jc = null;
		IHome home = new Home();

		jc = (JuvenileReferralSource) home.find(code, JuvenileReferralSource.class);

		return jc;
	}

	/**
	 * Find all Juvenile Referral Source.
	 * @return java.util.Iterator
	 */
	public static Iterator findAll(String attributeName, String attributeValue)
	{
		return new Home().findAll(attributeName, attributeValue, JuvenileReferralSource.class);
	}
}
