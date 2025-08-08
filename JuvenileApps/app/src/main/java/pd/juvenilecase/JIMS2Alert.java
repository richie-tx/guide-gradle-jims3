/*
 * Created on Sep 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase;

import java.util.Iterator;
import messaging.juvenilecase.reply.JIMS2AlertDetailsResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JIMS2Alert extends PersistentObject {

	private String juvenileNum;

	private String referralNum;

	private String alertType;

	private String jims2ExtractInd;

	private String releaseDate;

	private String releaseTime;

	private String dispositionDate;

	private String seqNum;

	private String lcUser;

	private String lcTime;

	private String lcDate;

	/**
	 * @return
	 * @param event
	 */
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, JIMS2Alert.class);
	}

	/**
	 * @return
	 */
	public String getJuvenileNum() {
		fetch();
		return juvenileNum;
	}

	/**
	 * @param theJuvenileNum
	 */
	public void setJuvenileNum(String theJuvenileNum) {
		if (this.juvenileNum == null || !this.juvenileNum.equals(theJuvenileNum)) {
			markModified();
		}
		juvenileNum = theJuvenileNum;
	}

	/**
	 * @return
	 */
	public String getReferralNum() {
		fetch();
		return referralNum;
	}

	/**
	 * @param theReferralNum
	 */
	public void setReferralNum(String theReferralNum) {
		if (this.referralNum == null || !this.referralNum.equals(theReferralNum)) {
			markModified();
		}
		referralNum = theReferralNum;
	}

	/**
	 * @return
	 */
	public String getAlertType() {
		fetch();
		return alertType;
	}

	/**
	 * @param theAlertType
	 */
	public void setAlertType(String theAlertType) {
		if (this.alertType == null || !this.alertType.equals(theAlertType)) {
			markModified();
		}
		alertType = theAlertType;
	}

	/**
	 * @return
	 */
	public String getDispositionDate() {
		fetch();
		return dispositionDate;
	}

	/**
	 * @param theDispositionDate
	 */
	public void setDispositionDate(String theDispositionDate) {
		if (this.dispositionDate == null || !this.dispositionDate.equals(theDispositionDate)) {
			markModified();
		}
		dispositionDate = theDispositionDate;
	}

	/**
	 * @return
	 */
	public String getReleaseDate() {
		fetch();
		return releaseDate;
	}

	/**
	 * @param theReleaseDate
	 */
	public void setReleaseDate(String theReleaseDate) {
		if (this.releaseDate == null || !this.releaseDate.equals(theReleaseDate)) {
			markModified();
		}
		releaseDate = theReleaseDate;
	}

	/**
	 * @return
	 */
	public String getReleaseTime() {
		fetch();
		return releaseTime;
	}

	/**
	 * @param theReleaseTime
	 */
	public void setReleaseTime(String theReleaseTime) {
		if (this.releaseTime == null || !this.releaseTime.equals(theReleaseTime)) {
			markModified();
		}
		releaseTime = theReleaseTime;
	}

	/**
	 * @return
	 */
	public String getJims2ExtractInd() {
		fetch();
		return jims2ExtractInd;
	}

	/**
	 * @param theJims2ExtractInd
	 */
	public void setJims2ExtractInd(String theJims2ExtractInd) {
		if (this.jims2ExtractInd == null || !this.jims2ExtractInd.equals(theJims2ExtractInd)) {
			markModified();
		}
		jims2ExtractInd = theJims2ExtractInd;
	}

	/**
	 * @return Returns the seqNum.
	 */
	public String getSeqNum() {
		fetch();
		return seqNum;
	}

	/**
	 * @param seqNum
	 *            The seqNum to set.
	 */
	public void setSeqNum(String seqNum) {
		if (this.seqNum == null || !this.seqNum.equals(seqNum)) {
			markModified();
		}
		this.seqNum = seqNum;
	}

	/**
	 * @return Returns the lcDate.
	 */
	public String getLcDate() {
		fetch();
		return lcDate;
	}

	/**
	 * @param lcDate
	 *            The lcDate to set.
	 */
	public void setLcDate(String lcDate) {
		if (this.lcDate == null || !this.lcDate.equals(lcDate)) {
			markModified();
		}
		this.lcDate = lcDate;
	}

	/**
	 * @return Returns the lcTime.
	 */
	public String getLcTime() {
		fetch();
		return lcTime;
	}

	/**
	 * @param lcTime
	 *            The lcTime to set.
	 */
	public void setLcTime(String lcTime) {
		if (this.lcTime == null || !this.lcTime.equals(lcTime)) {
			markModified();
		}
		this.lcTime = lcTime;
	}

	/**
	 * @return Returns the lcUser.
	 */
	public String getLcUser() {
		fetch();
		return lcUser;
	}

	/**
	 * @param lcUser
	 *            The lcUser to set.
	 */
	public void setLcUser(String lcUser) {
		if (this.lcUser == null || !this.lcUser.equals(lcUser)) {
			markModified();
		}
		this.lcUser = lcUser;
	}

	/**
	 * @return
	 */
	public JIMS2AlertDetailsResponseEvent getValueObject() {
		JIMS2AlertDetailsResponseEvent respEvent = new JIMS2AlertDetailsResponseEvent();
		respEvent.setAlertType(this.getAlertType());
		respEvent.setDispositionDate(this.getDispositionDate());
		respEvent.setJims2ExtractInd(this.getJims2ExtractInd());
		respEvent.setJuvenileNum(this.getJuvenileNum());
		respEvent.setReferralNum(this.getReferralNum());
		respEvent.setReleaseDate(this.getReleaseDate());
		respEvent.setReleaseTime(this.getReleaseTime());
		respEvent.setOid(this.getOID().toString());
		respEvent.setSeqNum(this.getSeqNum());
		return respEvent;
	}

}
