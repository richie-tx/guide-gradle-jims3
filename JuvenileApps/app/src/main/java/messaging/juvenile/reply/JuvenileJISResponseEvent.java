/*
 * Created on Oct 5th, 2016
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author ugopinath
 *
 */
public class JuvenileJISResponseEvent extends ResponseEvent implements Comparable
{

	private String juvenileNum;
	
	private Date entryDate;
	 
	 private String agency;
	 
	 private String otherAgency;
	 
	 private String comments;

	
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
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}


	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}


	/**
	 * @return the agency
	 */
	public String getAgency() {
		return agency;
	}


	/**
	 * @param agency the agency to set
	 */
	public void setAgency(String agency) {
		this.agency = agency;
	}


	/**
	 * @return the otherAgency
	 */
	public String getOtherAgency() {
		return otherAgency;
	}


	/**
	 * @param otherAgency the otherAgency to set
	 */
	public void setOtherAgency(String otherAgency) {
		this.otherAgency = otherAgency;
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


	/** Sorts by enrollment date
	 * @see Comparable#compareTo(Object)
	 */
	public int compareTo(Object jisRespObj)
	{
		JuvenileJISResponseEvent respEvent = (JuvenileJISResponseEvent) jisRespObj;
		return respEvent.getEntryDate().compareTo(this.entryDate);
	}	
}