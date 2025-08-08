package messaging.populationReport.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author UGopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class FacilityPopulationTotalsResponseEvent extends ResponseEvent{
	
	private String race;
	private int secureMaleCount;
	private int secureFemaleCount;
	private int nonSecureMaleCount;
	private int nonSecureFemaleCount;
	private int diversionMaleCount;
	private int diversionFemaleCount;
	private int tempReleaseMaleCount;
	private int tempReleaseFemaleCount;
	
	
	/**
	 * @return Returns the race.
	 */
	public String getRace() {
		return race;
	}
	/**
	 * @param race The race to set.
	 */
	public void setRace(String race) {
		this.race = race;
	}
	
	/**
	 * @return Returns the secureMaleCount.
	 */
	public int getSecureMaleCount() {
		return secureMaleCount;
	}
	/**
	 * @param secureMaleCount The secureMaleCount to set.
	 */
	public void setSecureMaleCount(int secureMaleCount) {
		this.secureMaleCount = secureMaleCount;
	}
	
	/**
	 * @return Returns the secureFemaleCount.
	 */
	public int getSecureFemaleCount() {
		return secureFemaleCount;
	}
	/**
	 * @param secureFemaleCount The secureFemaleCount to set.
	 */
	public void setSecureFemaleCountCount(int secureFemaleCount) {
		this.secureFemaleCount = secureFemaleCount;
	}
	/**
	 * @return Returns the nonSecureMaleCount.
	 */
	public int getNonSecureMaleCount() {
		return nonSecureMaleCount;
	}
	/**
	 * @param nonSecureMaleCount The nonSecureMaleCount to set.
	 */
	public void setNonSecureMaleCount(int nonSecureMaleCount) {
		this.nonSecureMaleCount = nonSecureMaleCount;
	}
	
	/**
	 * @return Returns the nonSecureFemaleCount.
	 */
	public int getNonSecureFemaleCount() {
		return nonSecureFemaleCount;
	}
	/**
	 * @param nonSecureFemaleCount The nonSecureFemaleCount to set.
	 */
	public void setNonSecureFemaleCount(int nonSecureFemaleCount) {
		this.nonSecureFemaleCount = nonSecureFemaleCount;
	}
	
	/**
	 * @return Returns the diversionMaleCount.
	 */
	public int getDiversionMaleCount() {
		return diversionMaleCount;
	}
	/**
	 * @param diversionMaleCount The diversionMaleCount to set.
	 */
	public void setDiversionMaleCount(int diversionMaleCount) {
		this.diversionMaleCount = diversionMaleCount;
	}
	
	/**
	 * @return Returns the diversionFemaleCount.
	 */
	public int getDiversionFemaleCount() {
		return diversionFemaleCount;
	}
	/**
	 * @param diversionFemaleCount The diversionFemaleCount to set.
	 */
	public void setDiversionFemaleCount(int diversionFemaleCount) {
		this.diversionFemaleCount = diversionFemaleCount;
	}
	public int getTempReleaseMaleCount()
	{
	    return tempReleaseMaleCount;
	}
	public void setTempReleaseMaleCount(int tempReleaseMaleCount)
	{
	    this.tempReleaseMaleCount = tempReleaseMaleCount;
	}
	public int getTempReleaseFemaleCount()
	{
	    return tempReleaseFemaleCount;
	}
	public void setTempReleaseFemaleCount(int tempReleaseFemaleCount)
	{
	    this.tempReleaseFemaleCount = tempReleaseFemaleCount;
	}

}
