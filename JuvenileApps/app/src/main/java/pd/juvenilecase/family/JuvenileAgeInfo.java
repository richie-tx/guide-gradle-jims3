/*
 * Created on Dec 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.family;

import java.util.Iterator;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JuvenileAgeInfo extends PersistentObject {

	private String familyMemberId;
	private String juvenileNum;
	private String juvenileAge;
	/**
	 * 
	 */
	public JuvenileAgeInfo() {
	}
	
	/**
	* @param familyMemberNum The family member num.
	* @roseuid 4321A38C00DB
	*/
	static public JuvenileAgeInfo find(String memberId) {
		IHome home = new Home();
		JuvenileAgeInfo juvAgeInfo = (JuvenileAgeInfo) home.find("familyMemberId",memberId, JuvenileAgeInfo.class);
		return juvAgeInfo;
	}
	
	/**
	* @param oid
	* @roseuid 4321A38C00DB
	*//*
	static public JuvenileAgeInfo find(String oid) {
		IHome home = new Home();
		JuvenileAgeInfo juvAgeInfo = (JuvenileAgeInfo) home.find(oid, JuvenileAgeInfo.class);
		return juvAgeInfo;
	}*/
	
	/**
	 * @param event
	* @roseuid 4321A38C00EB
	*/
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileAgeInfo.class);
	}
	
	
	/**
	* @roseuid 4321A38C00EB
	*/
	static public Iterator findAll()
	{
		IHome home = new Home();
		return home.findAll(JuvenileAgeInfo.class);
	}
	
	/**
	 * @return Returns the familyMemberId.
	 */
	public String getFamilyMemberId() {
		fetch();
		return familyMemberId;
	}
	/**
	 * @param familyMemberId The familyMemberId to set.
	 */
	public void setFamilyMemberId(String familyMemberId) {
		this.familyMemberId = familyMemberId;
	}
	/**
	 * @return Returns the juvenileAge.
	 */
	public String getJuvenileAge() {
		fetch();
		return juvenileAge;
	}
	/**
	 * @param juvenileAge The juvenileAge to set.
	 */
	public void setJuvenileAge(String juvenileAge) {
		this.juvenileAge = juvenileAge;
	}
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		fetch();
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
}
