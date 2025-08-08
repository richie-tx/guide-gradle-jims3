/*
 * Created on July 10, 2007
 */
package messaging.juvenilewarrant.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author ugopinath
 */
public class JuvenileOffenderTrackingPropertyResponseEvent extends ResponseEvent
{
	private String description;
	private Double value;
	private String sequenceNum;
	private String daLogNum;
	

	
	
	/**
	 * @return Returns the daLogNum.
	 */
	public String getDaLogNum() {
		return daLogNum;
	}
	/**
	 * @param daLogNum The daLogNum to set.
	 */
	public void setDaLogNum(String daLogNum) {
		this.daLogNum = daLogNum;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the sequenceNum.
	 */
	public String getSequenceNum() {
		return sequenceNum;
	}
	/**
	 * @param sequenceNum The sequenceNum to set.
	 */
	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	/**
	 * @return Returns the value.
	 */
	public Double getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(Double value) {
		this.value = value;
	}
}