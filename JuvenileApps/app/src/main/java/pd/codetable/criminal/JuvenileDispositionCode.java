package pd.codetable.criminal;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import java.util.Iterator;

/**
 * @author glyons M204 COURT.DECISION complex code table JuvenileDispositionCode
 *         entity
 */
public class JuvenileDispositionCode extends PersistentObject
{
    private String optionCode;
    private String shortDesc;
    private String codeNum;
    private String codeAlpha; //OID
    private String subGroupInd;
    private String numericCode;
    private String jPCCode;
    private String longDesc;
    private String dpsCode;
    private String dispositionCode; //Final Disposition Code
    private String categoryCode;
    private String inactiveInd;
    private String codeType;
    private String codeTypeDescription;
    /**
     * @roseuid 41ACCAE60161
     */
    public JuvenileDispositionCode()
    {
    }

    /**
     * @return JuvenileDispositionCode object
     * @param codeNum
     * @roseuid 41ACA9680353 Find JuvenileDispositionCode object by codeId
     */
    static public JuvenileDispositionCode find(String oid)
    {
	JuvenileDispositionCode jdc = null;
	IHome home = new Home();
	jdc = (JuvenileDispositionCode) home.find(oid, JuvenileDispositionCode.class);
	return jdc;
    }

    /**
     * Finds all JuvenileDispositionCode objects
     * 
     * @return all JuvenileDispositionCode objects
     */
    static public Iterator findAll()
    {
	IHome home = new Home();
	return home.findAll(JuvenileDispositionCode.class);
    }

    /**
     * Finds all JuvenileDispositionCode by an attribute value
     * 
     * @param attributeName
     * @param attributeValue
     * @return
     */
    static public Iterator findAll(String attributeName, String attributeValue)
    {
	return new Home().findAll(attributeName, attributeValue, JuvenileDispositionCode.class);
    }
    
    /**
     * 
     * @param attributeName
     * @param attributeValue
     * @return
     */
    static public JuvenileDispositionCode find(String attributeName, String attributeValue)
    {
	return (JuvenileDispositionCode) new Home().find(attributeName, attributeValue, JuvenileDispositionCode.class);
    }

    /**
     * @return codeAlpha
     */
    public String getCodeAlpha()
    {
	fetch();
	return codeAlpha;
    }

    /**
     * @return codeNum
     */
    public String getCodeNum()
    {
	fetch();
	return codeNum;
    }

    /**
     * @return dpsCode
     */
    public String getDpsCode()
    {
	fetch();
	return dpsCode;
    }

    /**
     * @return jPCCode
     */
    public String getJPCCode()
    {
	fetch();
	return jPCCode;
    }

    /**
     * @return longDesc
     */
    public String getLongDesc()
    {
	fetch();
	return longDesc;
    }

    /**
     * @return numericCode
     */
    public String getNumericCode()
    {
	fetch();
	return numericCode;
    }

    /**
     * @return optionCode
     */
    public String getOptionCode()
    {
	fetch();
	return optionCode;
    }

    /**
     * @return shortDesc
     */
    public String getShortDesc()
    {
	fetch();
	return shortDesc;
    }

    /**
     * @return
     */
    public String getSubGroupInd()
    {
	fetch();
	return subGroupInd;
    }

    /**
     * @param string
     */
    public void setCodeAlpha(String aCodeAlpha)
    {
	if (this.codeAlpha == null || !this.codeAlpha.equals(aCodeAlpha))
	{
	    markModified();
	}
	codeAlpha = aCodeAlpha;
    }

    /**
     * @param string
     */
    public void setCodeNum(String aCodeNum)
    {
	if (this.codeNum == null || !this.codeNum.equals(aCodeNum))
	{
	    markModified();
	}
	codeNum = aCodeNum;
    }

    /**
     * @param string
     */
    public void setDpsCode(String aDpsCode)
    {
	if (this.dpsCode == null || !this.dpsCode.equals(aDpsCode))
	{
	    markModified();
	}
	dpsCode = aDpsCode;
    }

    /**
     * @param string
     */
    public void setJPCCode(String aJPCCode)
    {
	if (this.jPCCode == null || !this.jPCCode.equals(aJPCCode))
	{
	    markModified();
	}
	jPCCode = aJPCCode;
    }

    /**
     * @param string
     */
    public void setLongDesc(String aLongDesc)
    {
	if (this.longDesc == null || !this.longDesc.equals(aLongDesc))
	{
	    markModified();
	}
	longDesc = aLongDesc;
    }

    /**
     * @param string
     */
    public void setNumericCode(String aNumericCode)
    {
	if (this.numericCode == null || !this.numericCode.equals(aNumericCode))
	{
	    markModified();
	}
	numericCode = aNumericCode;
    }

    /**
     * @param string
     */
    public void setOptionCode(String aOptionCode)
    {
	if (this.optionCode == null || !this.optionCode.equals(aOptionCode))
	{
	    markModified();
	}
	optionCode = aOptionCode;
    }

    /**
     * @param string
     */
    public void setShortDesc(String aShortDesc)
    {
	if (this.shortDesc == null || !this.shortDesc.equals(aShortDesc))
	{
	    markModified();
	}
	shortDesc = aShortDesc;
    }

    /**
     * @param string
     */
    public void setSubGroupInd(String aSubGroupInd)
    {
	if (this.subGroupInd == null || !this.subGroupInd.equals(aSubGroupInd))
	{
	    markModified();
	}
	subGroupInd = aSubGroupInd;
    }

    public String getDispositionCode()
    {
	return dispositionCode;
    }

    public void setDispositionCode(String dispositionCode)
    {
	this.dispositionCode = dispositionCode;
    }

    public String getCategoryCode()
    {
	fetch();
	return categoryCode;
    }

    public void setCategoryCode(String aCategoryCode)
    {
	if (this.categoryCode == null || !this.categoryCode.equals(aCategoryCode))
	{
	    markModified();
	}
	categoryCode = aCategoryCode;
    }

    public String getInactiveInd()
    {
	fetch();
	return inactiveInd;
    }

    public void setInactiveInd(String inactiveInd)
    {
	if (this.inactiveInd == null || !this.inactiveInd.equals(inactiveInd))
	{
	    markModified();
	}
	this.inactiveInd = inactiveInd;
    }

    public String getCodeType()
    {
	fetch();
        return codeType;
    }

    public void setCodeType(String codeType)
    {
	if (this.codeType == null || !this.codeType.equals(codeType))
	{
	    markModified();
	}
	this.codeType = codeType;
    }

    public String getCodeTypeDescription()
    {
	fetch();
        return codeTypeDescription;
    }

    public void setCodeTypeDescription(String codeTypeDescription)
    {
	if (this.codeTypeDescription == null || !this.codeTypeDescription.equals(codeTypeDescription))
	{
	    markModified();
	}
        this.codeTypeDescription = codeTypeDescription;
    }
    
}
