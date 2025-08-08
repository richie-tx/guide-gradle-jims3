package pd.codetable.criminal;

import mojo.km.persistence.PersistentObject;
import java.util.Date;

/**
 * 
 * @roseuid 467FBA7601FF
 */
public class JuvenileFeeCode extends PersistentObject
{
	private String code;
	private String feeRevisionNum;
	private String description;
	private String baseFeeAmount;
	private Date feeEffectiveDate;

	/**
	 * 
	 * @roseuid 467FBA7601FF
	 */
	public JuvenileFeeCode()
	{
	}

	/**
	 * 
	 * @return Returns the baseFeeAmount.
	 */
	public String getBaseFeeAmount()
	{
		fetch();
		return baseFeeAmount;
	}

	/**
	 * 
	 * @param baseFeeAmount The baseFeeAmount to set.
	 */
	public void setBaseFeeAmount(String baseFeeAmount)
	{
		if (this.baseFeeAmount == null || !this.baseFeeAmount.equals(baseFeeAmount))
		{
			markModified();
		}
		this.baseFeeAmount = baseFeeAmount;
	}

	/**
	 * 
	 * @return Returns the code.
	 */
	public String getCode()
	{
		fetch();
		return code;
	}

	/**
	 * 
	 * @param code The code to set.
	 */
	public void setCode(String code)
	{
		if (this.code == null || !this.code.equals(code))
		{
			markModified();
		}
		this.code = code;
	}

	/**
	 * 
	 * @return Returns the description.
	 */
	public String getDescription()
	{
		fetch();
		return description;
	}

	/**
	 * 
	 * @param description The description to set.
	 */
	public void setDescription(String description)
	{
		if (this.description == null || !this.description.equals(description))
		{
			markModified();
		}
		this.description = description;
	}

	/**
	 * 
	 * @return Returns the feeEffectiveDate.
	 */
	public Date getFeeEffectiveDate()
	{
		fetch();
		return feeEffectiveDate;
	}

	/**
	 * 
	 * @param feeEffectiveDate The feeEffectiveDate to set.
	 */
	public void setFeeEffectiveDate(Date feeEffectiveDate)
	{
		if (this.feeEffectiveDate == null || !this.feeEffectiveDate.equals(feeEffectiveDate))
		{
			markModified();
		}
		this.feeEffectiveDate = feeEffectiveDate;
	}

	/**
	 * 
	 * @return Returns the feeRevisionNum.
	 */
	public String getFeeRevisionNum()
	{
		fetch();
		return feeRevisionNum;
	}

	/**
	 * 
	 * @param feeRevisionNum The feeRevisionNum to set.
	 */
	public void setFeeRevisionNum(String feeRevisionNum)
	{
		if (this.feeRevisionNum == null || !this.feeRevisionNum.equals(feeRevisionNum))
		{
			markModified();
		}
		this.feeRevisionNum = feeRevisionNum;
	}
}
