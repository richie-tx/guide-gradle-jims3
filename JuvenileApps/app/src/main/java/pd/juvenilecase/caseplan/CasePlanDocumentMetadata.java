package pd.juvenilecase.caseplan;

import mojo.km.persistence.PersistentObject;

/**
 * @roseuid 4533B7B7002A
 */
public class CasePlanDocumentMetadata extends PersistentObject
{
	private String casePlanId;

	/**
	 * @roseuid 4533B7B7002A
	 */
	public CasePlanDocumentMetadata()
	{
	}

	/**
	 * Set the reference value to class :: pd.juvenilecase.caseplan.CasePlan
	 * @methodInvocation markModified
	 */
	public void setCasePlanId(String casePlanId)
	{
		if (this.casePlanId == null || !this.casePlanId.equals(casePlanId))
		{
			markModified();
		}
		this.casePlanId = casePlanId;
	}

	/**
	 * Get the reference value to class :: pd.juvenilecase.caseplan.CasePlan
	 * @methodInvocation fetch
	 */
	public String getCasePlanId()
	{
		fetch();
		return casePlanId;
	}
}

