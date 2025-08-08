package messaging.juvenile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author sthyagarajan
 * JuvenileGangsResponseEvent
 */
public class JuvenileGangsResponseEvent extends ResponseEvent implements
		Comparable {

	private String juvenileNum;

	private Date entryDate;
	private String gangName;
	private String cliqueSet;
	private String currentStatus;
	private String associationType;
	private String aliasMoniker;
	private String rank;

	// Hidden field for other,on selection of other,show other text box.
	private String otherGangName;
	private String otherCliqueSet;

	// Hidden field for describe hybrid on selection of hybrid as the gang name;
	private String descHybrid;

	/**
	 * @return juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return gangName
	 */
	public String getGangName() {
		return gangName;
	}

	/**
	 * @param gangName
	 */
	public void setGangName(String gangName) {
		this.gangName = gangName;
	}

	/**
	 * @return cliqueSet
	 */
	public String getCliqueSet() {
		return cliqueSet;
	}

	/**
	 * @param cliqueSet
	 */
	public void setCliqueSet(String cliqueSet) {
		this.cliqueSet = cliqueSet;
	}

	/**
	 * @return currentStatus
	 */
	public String getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * @param currentStatus
	 */
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	/**
	 * @return associationType
	 */
	public String getAssociationType() {
		return associationType;
	}

	/**
	 * @param associationType
	 */
	public void setAssociationType(String associationType) {
		this.associationType = associationType;
	}

	/**
	 * @return the moniker
	 */
	public String getAliasMoniker() {
		return aliasMoniker;
	}

	/**
	 * @param moniker
	 */
	public void setAliasMoniker(String aliasMoniker) {
		this.aliasMoniker = aliasMoniker;
	}

	/**
	 * @return the rank
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * @param otherGangName
	 *            the otherGangName to set
	 */
	public void setOtherGangName(String otherGangName) {
		this.otherGangName = otherGangName;
	}

	/**
	 * @return the otherGangName
	 */
	public String getOtherGangName() {
		return otherGangName;
	}

	/**
	 * @param otherCliqueSet
	 *            the otherCliqueSet to set
	 */
	public void setOtherCliqueSet(String otherCliqueSet) {
		this.otherCliqueSet = otherCliqueSet;
	}

	/**
	 * @return the otherCliqueSet
	 */
	public String getOtherCliqueSet() {
		return otherCliqueSet;
	}

	/**
	 * @param descHybrid
	 *            the descHybrid to set
	 */
	public void setDescHybrid(String descHybrid) {
		this.descHybrid = descHybrid;
	}

	/**
	 * @return the descHybrid
	 */
	public String getDescHybrid() {
		return descHybrid;
	}

	/*
	 * (non-Javadoc)O
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) throws ClassCastException {
		if (obj == null)
			return -1;
		if (this.entryDate == null)
			return 1;
		JuvenileGangsResponseEvent evt = (JuvenileGangsResponseEvent) obj;
		return evt.getEntryDate().compareTo(entryDate);
	}
}
