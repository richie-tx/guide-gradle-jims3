package messaging.codetable.criminal.reply;

import mojo.km.messaging.ResponseEvent;

public class JuvenileDispositionCodeResponseEvent extends ResponseEvent
		implements Comparable {

	private String optionCode;
	private String shortDesc;
	private String codeNum;
	private String codeAlpha; // OID
	private String subGroupInd;
	private String numericCode;
	private String jPCCode;
	private String longDesc;
	private String dpsCode;
	private String dispositionCode; // Final Disposition Code
	private String categoryCode;
	private String inactiveInd;
	private String descriptionWithCode;
	private String codeType;
	private String codeTypeDescription;
	

	public int compareTo(Object o) {
		JuvenileDispositionCodeResponseEvent evt = (JuvenileDispositionCodeResponseEvent) o;
		return longDesc.compareTo(evt.getLongDesc());
	}

	public String getOptionCode() {
		return optionCode;
	}

	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}

	public String getCodeAlpha() {
		return codeAlpha;
	}

	public void setCodeAlpha(String codeAlpha) {
		this.codeAlpha = codeAlpha;
	}

	public String getSubGroupInd() {
		return subGroupInd;
	}

	public void setSubGroupInd(String subGroupInd) {
		this.subGroupInd = subGroupInd;
	}

	public String getNumericCode() {
		return numericCode;
	}

	public void setNumericCode(String numericCode) {
		this.numericCode = numericCode;
	}

	public String getjPCCode() {
		return jPCCode;
	}

	public void setjPCCode(String jPCCode) {
		this.jPCCode = jPCCode;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	public String getDpsCode() {
		return dpsCode;
	}

	public void setDpsCode(String dpsCode) {
		this.dpsCode = dpsCode;
	}

	public String getDispositionCode() {
		return dispositionCode;
	}

	public void setDispositionCode(String dispositionCode) {
		this.dispositionCode = dispositionCode;
	}

	public String getCategoryCode()
	{
	    return categoryCode;
	}

	public void setCategoryCode(String categoryCode)
	{
	    this.categoryCode = categoryCode;
	}

	public String getInactiveInd()
	{
	    return inactiveInd;
	}

	public void setInactiveInd(String inactiveInd)
	{
	    this.inactiveInd = inactiveInd;
	}

	public String getDescriptionWithCode() {
		return descriptionWithCode;
	}

	public void setDescriptionWithCode(String descriptionWithCode) {
		this.descriptionWithCode = descriptionWithCode;
	}

	public String getCodeType()
	{
	    return codeType;
	}

	public void setCodeType(String codeType)
	{
	    this.codeType = codeType;
	}

	public String getCodeTypeDescription()
	{
	    return codeTypeDescription;
	}

	public void setCodeTypeDescription(String codeTypeDescription)
	{
	    this.codeTypeDescription = codeTypeDescription;
	}

}
