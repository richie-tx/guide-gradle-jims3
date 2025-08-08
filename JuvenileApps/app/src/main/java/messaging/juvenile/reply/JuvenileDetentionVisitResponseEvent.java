/*
 * Created on Mar 17, 2017
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author ugopinath
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileDetentionVisitResponseEvent extends ResponseEvent
{
	
	
	private String juvenileNum;
	private String memOrContactName;
	private String relationship;
	private String idType;
	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	/**
	 * @return the memOrContactName
	 */
	public String getMemOrContactName() {
		return memOrContactName;
	}
	/**
	 * @param memOrContactName the memOrContactName to set
	 */
	public void setMemOrContactName(String memOrContactName) {
		this.memOrContactName = memOrContactName;
	}
	/**
	 * @return the relationship
	 */
	public String getRelationship() {
		return relationship;
	}
	/**
	 * @param relationship the relationship to set
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	/**
	 * @return the idType
	 */
	public String getIdType() {
		return idType;
	}
	/**
	 * @param idType the idType to set
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}
	

}
