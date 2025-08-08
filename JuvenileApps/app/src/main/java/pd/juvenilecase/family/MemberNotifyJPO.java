/*
 * Created on May 17, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family;

import java.util.Iterator;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;

/**
 * @author jjose
 * 
 */
public class MemberNotifyJPO extends PersistentObject {
	private String memberId;
	private String memberLastName;
	private String memberMiddleName;
	private String memberFirstName;
	private String memberSSN;
	private String relationshipToJuv;
	private String officerId;
	private String officerEmail;
	private String officerUserId;
	private String officerFirstName;
	private String officerMiddleName;
	private String officerLastName;
	private String juvenileId;
	//private Juvenile juvenile;
	private JuvenileCore juvenile;
	private String constellationId;

	public MemberNotifyJPO() {
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String amemberId) {
		this.memberId = amemberId;
	}

	/**
	 * 
	 * @param aofficerId
	 */
	public void setOfficerId(String aofficerId) {
		this.officerId = aofficerId;
	}

	/**
	 * 
	 * @return The officer id.
	 */
	public String getOfficerId() {
		fetch();
		return officerId;
	}

	/**
	 * 
	 * @param aofficerEmail
	 */
	public void setOfficerEmail(String aofficerEmail) {
		this.officerEmail = aofficerEmail;
	}

	/**
	 * 
	 * @return The officer email.
	 */
	public String getOfficerEmail() {
		fetch();
		return officerEmail;
	}

	/**
		* 
		*/
	public void setOfficerUserId(String aOfficerUserId) {
		officerUserId = null;
		this.officerUserId = aOfficerUserId;
	}

	/**
		* 
		*/
	public String getOfficerUserId() {
		fetch();
		return officerUserId;
	}

	/**
		* 
		*/
	static public Iterator findAll(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator MemberNotifyJPOes = home.findAll(attributeName,
				attributeValue, MemberNotifyJPO.class);
		return MemberNotifyJPOes;
	}

	/**
	 * @return
	 */
	public String getOfficerMiddleName() {
		fetch();
		return officerMiddleName;
	}

	/**
	 * @param string
	 */
	public void setOfficerMiddleName(String officerMiddleName) {
		this.officerMiddleName = officerMiddleName;
	}

	/**
	 * @return
	 */
	public String getOfficerLastName() {
		fetch();
		return officerLastName;
	}

	/**
	 * @param string
	 */
	public void setOfficerLastName(String officerLastName) {
		this.officerLastName = officerLastName;
	}

	/**
	 * @return
	 */
	public String getOfficerFirstName() {
		fetch();
		return officerFirstName;
	}

	/**
	 * @param string
	 */
	public void setOfficerFirstName(String officerFirstName) {
		this.officerFirstName = officerFirstName;
	}

	/**
	 * @return
	 */
	public String getJuvenileId() {
		fetch();
		return juvenileId;
	}

	/**
	 * @param date
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	/**
	 * @return
	 */
	public String getConstellationId() {
		fetch();
		return constellationId;
	}

	/**
	 * @param date
	 */
	public void setConstellationId(String constellationId) {
		this.constellationId = constellationId;
	}

	/**
	 * @return
	 */
	public String getMemberMiddleName() {
		fetch();
		return memberMiddleName;
	}

	/**
	 * @param string
	 */
	public void setMemberMiddleName(String memberMiddleName) {
		this.memberMiddleName = memberMiddleName;
	}

	/**
	 * @return
	 */
	public String getMemberLastName() {
		fetch();
		return memberLastName;
	}

	/**
	 * @param string
	 */
	public void setMemberLastName(String memberLastName) {
		this.memberLastName = memberLastName;
	}

	/**
	 * @return
	 */
	public String getMemberFirstName() {
		fetch();
		return memberFirstName;
	}

	/**
	 * @param string
	 */
	public void setMemberFirstName(String memberFirstName) {
		this.memberFirstName = memberFirstName;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initJuvenile() {
		if (juvenile == null) {
		    	// Profile stripping fix task 97538
			//juvenile = Juvenile.find(juvenileId);
		    	juvenile = JuvenileCore.findCore(juvenileId);
		    
		}
	}

	/**
	 * @return Returns the juvenile
	 */
	//public Juvenile getJuvenile() {
	public JuvenileCore getJuvenile() {
		initJuvenile();
		return juvenile;
	}

	/**
	 * 
	 * @param aofficerId
	 */
	public void setRelationshipToJuv(String arelationshipId) {
		this.relationshipToJuv = arelationshipId;
	}

	/**
	 * 
	 * @return The officer id.
	 */
	public String getRelationshipToJuv() {
		fetch();
		return relationshipToJuv;
	}

	/**
	 * 
	 * @param aofficerId
	 */
	public void setMemberSSN(String aSSN) {
		this.memberSSN = aSSN;
	}

	/**
	 * 
	 * @return The officer id.
	 */
	public String getMemberSSN() {
		fetch();
		return memberSSN;
	}
}
