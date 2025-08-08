/*
 * Created on Jul 26, 2011
 */
package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * Returns a basic populated version of a sanction code table record
 * 
 */
public class JuvenileCasefileSanctionsResponseEvent extends ResponseEvent 
{
	private String sanctionId; 
	private String sanctionLevel;
	private String sanctionDesc;
	private String sanctionType;
	private String comments;

	/**
	 * @return the sanctionId
	 */
	public String getSanctionId() {
		return sanctionId;
	}
	/**
	 * @param sanctionId the sanctionId to set
	 */
	public void setSanctionId(String sanctionId) {
		this.sanctionId = sanctionId;
	}
	/**
	 * @return the sanctionLevel
	 */
	public String getSanctionLevel() {
		return sanctionLevel;
	}
	/**
	 * @param sanctionLevel the sanctionLevel to set
	 */
	public void setSanctionLevel(String sanctionLevel) {
		this.sanctionLevel = sanctionLevel;
	}
	/**
	 * @return the sanctionDesc
	 */
	public String getSanctionDesc() {
		return sanctionDesc;
	}
	/**
	 * @param sanctionDesc the sanctionDesc to set
	 */
	public void setSanctionDesc(String sanctionDesc) {
		this.sanctionDesc = sanctionDesc;
	}
	/**
	 * @return the sanctionType
	 */
	public String getSanctionType() {
		return sanctionType;
	}
	/**
	 * @param sanctionType the sanctionType to set
	 */
	public void setSanctionType(String sanctionType) {
		this.sanctionType = sanctionType;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
}