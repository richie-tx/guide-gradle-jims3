package messaging.interviewinfo.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class JuvenileCourtDateEvent extends ResponseEvent
{
	private String casefileId;
	private String juvenileId;
	private String referralNum;
	private Date courtDate;
	private String courtName;

	/**
	 * @return
	 */
	public String getCasefileId()
	{
		return casefileId;
	}

	/**
	 * @param string
	 */
	public void setCasefileId(String string)
	{
		casefileId = string;
	}


	/**
	 * @return Returns the courtDate.
	 */
	public Date getCourtDate() {
		return courtDate;
	}
	/**
	 * @param courtDate The courtDate to set.
	 */
	public void setCourtDate(Date courtDate) {
		this.courtDate = courtDate;
	}
	/**
	 * @return Returns the courtName.
	 */
	public String getCourtName() {
		return courtName;
	}
	/**
	 * @param courtName The courtName to set.
	 */
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	/**
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId() {
		return juvenileId;
	}
	/**
	 * @param juvenileId The juvenileId to set.
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	/**
	 * @return Returns the referralNum.
	 */
	public String getReferralNum() {
		return referralNum;
	}
	/**
	 * @param referralNum The referralNum to set.
	 */
	public void setReferralNum(String referralNum) {
		this.referralNum = referralNum;
	}
}
