package pd.commonfunctionality.spnconsolidation;

import messaging.spnconsolidation.UpdateSpnConsolidationLogEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @roseuid 452BA26303CF
 */
public class SpnConsolidationLog extends PersistentObject
{
	private String baseSpn;
	private String aliasSpn;
	private String status;
	private String logDetail;
	private String invocSource;

	/**
	 * @return Returns the invocSource.
	 */
	public String getInvocSource() {
		return invocSource;
	}
	/**
	 * @param invocSource The invocSource to set.
	 */
	public void setInvocSource(String invocSource) {
		this.invocSource = invocSource;
	}
	/**
	 * @return Returns the logDetail.
	 */
	public String getLogDetail() {
		return logDetail;
	}
	/**
	 * @param logDetail The logDetail to set.
	 */
	public void setLogDetail(String logDetail) {
		this.logDetail = logDetail;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @roseuid 452BA26303CF
	 */
	public SpnConsolidationLog()
	{
	}

	/**
	 * @roseuid 4526B08303AB
	 */
	public void bind()
	{
		markModified();
	}

	/**
	 * @return 
	 * @param spnConsolLogId
	 */
	static public SpnConsolidationLog find(String oid)
	{
		IHome home = new Home();
		SpnConsolidationLog log = (SpnConsolidationLog) home.find(oid, SpnConsolidationLog.class);
		return log;
	}

	/**
	 * @return Returns the aliasSpn.
	 */
	public String getAliasSpn()
	{
		fetch();
		return aliasSpn;
	}

	/**
	 * @param aliasSpn The aliasSpn to set.
	 */
	public void setAliasSpn(String aliasSpn)
	{
		if (this.aliasSpn == null || !this.aliasSpn.equals(aliasSpn))
		{
			markModified();
		}
		this.aliasSpn = aliasSpn;
	}

	/**
	 * @return Returns the baseSpn.
	 */
	public String getBaseSpn()
	{
		fetch();
		return baseSpn;
	}

	/**
	 * @param baseSpn The baseSpn to set.
	 */
	public void setBaseSpn(String baseSpn)
	{
		if (this.baseSpn == null || !this.baseSpn.equals(baseSpn))
		{
			markModified();
		}
		this.baseSpn = baseSpn;
	}


	/**
	 * @param updateEvent
	 */
	public void updateSpnConsolidationLog(UpdateSpnConsolidationLogEvent updateEvent) {

			setAliasSpn(updateEvent.getAliasSpn());
			setBaseSpn(updateEvent.getBaseSpn());
		
	}
}

