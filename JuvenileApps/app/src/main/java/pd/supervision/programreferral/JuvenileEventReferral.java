// Source file:
// C:\\views\\MJCW\\app\\src\\pd\\supervision\\calendar\\JuvenileProgramReferral.java

package pd.supervision.programreferral;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class JuvenileEventReferral extends PersistentObject {
	
	private String programReferralId;
	private String serviceEventId;
	

	/**
	 * @return Returns the serviceEventId.
	 */
	public String getServiceEventId() {
		fetch();
		return serviceEventId;
	}
	/**
	 * @param serviceEventId The serviceEventId to set.
	 */
	public void setServiceEventId(String serviceEventId) {
		if (this.serviceEventId == null || !this.serviceEventId.equals(serviceEventId)){
			markModified();
		}
		this.serviceEventId = serviceEventId;
	}
			
	/**
	* @return JuvenileProgramReferral
	* @param programReferralId
	* @roseuid 4177C29D03A9
	*/
	static public JuvenileEventReferral find(String oid) {
		IHome home = new Home();
		return (JuvenileEventReferral) home.find(oid, JuvenileEventReferral.class);
	}	
	
	/**
	* @return java.util.Iterator
	* @param departmentId
	* @roseuid 4177C29D03A9
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		Iterator eventReferrals = null;
		eventReferrals = home.findAll(attrName, attrValue, JuvenileEventReferral.class);
		return eventReferrals;
	}
	/**
	 * @return Returns the programReferralId.
	 */
	public String getProgramReferralId() {
		fetch();
		return programReferralId;
	}
	/**
	 * @param programReferralId The programReferralId to set.
	 */
	public void setProgramReferralId(String programReferralId) {
		if (this.programReferralId == null || !this.programReferralId.equals(programReferralId)){
			markModified();
		}		
		this.programReferralId = programReferralId;
	}
}
