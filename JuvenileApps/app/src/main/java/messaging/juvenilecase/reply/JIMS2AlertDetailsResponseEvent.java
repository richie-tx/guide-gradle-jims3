/*
 * Created on Sep 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenilecase.reply;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JIMS2AlertDetailsResponseEvent extends ResponseEvent implements IAddressable {
	private String juvenileNum;

	private String referralNum;

	private String alertType;

	private String jims2ExtractInd;

	private String releaseDate;

	private String releaseTime;

	private String dispositionDate;

	private String oid;

	private String seqNum;

	/**
	 * @return Returns the alertType.
	 */
	public String getAlertType() {
		return alertType;
	}

	/**
	 * @param alertType
	 *            The alertType to set.
	 */
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	/**
	 * @return Returns the dispositionDate.
	 */
	public String getDispositionDate() {
		return dispositionDate;
	}

	/**
	 * @param dispositionDate
	 *            The dispositionDate to set.
	 */
	public void setDispositionDate(String dispositionDate) {
		this.dispositionDate = dispositionDate;
	}

	/**
	 * @return Returns the jims2ExtractInd.
	 */
	public String getJims2ExtractInd() {
		return jims2ExtractInd;
	}

	/**
	 * @param jims2ExtractInd
	 *            The jims2ExtractInd to set.
	 */
	public void setJims2ExtractInd(String jims2ExtractInd) {
		this.jims2ExtractInd = jims2ExtractInd;
	}

	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum
	 *            The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return Returns the referralNum.
	 */
	public String getReferralNum() {
		return referralNum;
	}

	/**
	 * @param referralNum
	 *            The referralNum to set.
	 */
	public void setReferralNum(String referralNum) {
		this.referralNum = referralNum;
	}

	/**
	 * @return Returns the releaseDate.
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate
	 *            The releaseDate to set.
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return Returns the releaseTime.
	 */
	public String getReleaseTime() {
		return releaseTime;
	}

	/**
	 * @param releaseTime
	 *            The releaseTime to set.
	 */
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	/**
	 * @return Returns the oid.
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid
	 *            The oid to set.
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return Returns the seqNum.
	 */
	public String getSeqNum() {
		return seqNum;
	}

	/**
	 * @param seqNum
	 *            The seqNum to set.
	 */
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}
}
