package messaging.juvenile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author sthyagarajan
 *
 */

public class JuvenileGangRequestEvent extends RequestEvent {

	private String juvenileNum;
	
	private Date entryDate = null;
	
	private String gangName = null;
	private String cliqueSet = null;
	private String currentStatus = null;
	private String associationType = null;
	
	private String aliasMoniker = null;
	private String rank;
	
	//Hidden field for other,on selection of other,show other text box.
	private String otherGangName;
	private String otherCliqueSet;
	
	//Hidden field for describe hybrid on selection of hybrid as the gang name;
	private String descHybrid;

	//just display descriptions of the dropdowns in the gang info list.
	//values set in the gang form.
	private String associationTypeDesc;
	private String gangNameDesc;
	private String cliqueSetDesc;
	private String currentStatusDesc;

	/**
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
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
	 * @return
	 */
	public String getAliasMoniker() {
		return aliasMoniker;
	}

	/**
	 * @param aliasMoniker
	 */
	public void setAliasMoniker(String aliasMoniker) {
		this.aliasMoniker = aliasMoniker;
	}

	/**
	 * @return
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
	 * @return
	 */
	public String getRank() {
		return rank;
	}

	/**
	 * @param rank
	 */
	public void setRank(String rank) {
		this.rank = rank;
	}

	/**
	 * @return
	 */
	public String getAssociationTypeDesc() {
		return associationTypeDesc;
	}

	/**
	 * @param associationTypeDesc
	 */
	public void setAssociationTypeDesc(String associationTypeDesc) {
		this.associationTypeDesc = associationTypeDesc;
	}

	/**
	 * @return
	 */
	public String getGangNameDesc() {
		return gangNameDesc;
	}

	/**
	 * @param gangNameDesc
	 */
	public void setGangNameDesc(String gangNameDesc) {
		this.gangNameDesc = gangNameDesc;
	}

	/**
	 * @return cliqueSetDesc
	 */
	public String getCliqueSetDesc() {
		return cliqueSetDesc;
	}

	/**
	 * @param cliqueSetDesc
	 */
	public void setCliqueSetDesc(String cliqueSetDesc) {
		this.cliqueSetDesc = cliqueSetDesc;
	}

	/**
	 * @return currentStatusDesc
	 */
	public String getCurrentStatusDesc() {
		return currentStatusDesc;
	}

	/**
	 * @param currentStatusDesc
	 */
	public void setCurrentStatusDesc(String currentStatusDesc) {
		this.currentStatusDesc = currentStatusDesc;
	}


	/**
	 * @param otherGangName the otherGangName to set
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
	 * @param otherCliqueSet the otherCliqueSet to set
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
	 * @param descHybrid the descHybrid to set
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
}
