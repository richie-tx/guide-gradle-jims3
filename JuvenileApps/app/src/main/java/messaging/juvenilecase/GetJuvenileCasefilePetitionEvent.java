//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefilePetitionEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileCasefilePetitionEvent extends RequestEvent
{
	public String petitionNum;
	public String daLogNum;

	/**
	 * @roseuid 42A9A16D0190
	 */
	public GetJuvenileCasefilePetitionEvent()
	{
	}

	/**
	 * Access method for the petitionNum property.
	 * 
	 * @return   the current value of the petitionNum property
	 */
	public String getPetitionNum()
	{
		return petitionNum;
	}

	/**
	 * Sets the value of the petitionNum property.
	 * 
	 * @param aPetitionNum the new value of the petitionNum property
	 */
	public void setPetitionNum(String aPetitionNum)
	{
		petitionNum = aPetitionNum;
	}

	/**
	 * @return the daLogNum
	 */
	public String getDaLogNum() {
		return daLogNum;
	}

	/**
	 * @param daLogNum the daLogNum to set
	 */
	public void setDaLogNum(String daLogNum) {
		this.daLogNum = daLogNum;
	}
}
