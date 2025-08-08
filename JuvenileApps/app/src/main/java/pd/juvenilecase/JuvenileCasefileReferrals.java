/*
 * Created on Sep 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author ryoung
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JuvenileCasefileReferrals extends PersistentObject implements Comparable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String casefileId;
	private String petitionNum;
	private String supervisionTypeCd;
	private String assignmentDate;
	private String supervisionTypeDesc;
	private String referralNum;
	private String assignOfficerId;
	private String juvenileId;
	private String officerLName;
	private String officerFName;
	private String officerMName;
	
	
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator casefiles = home.findAll(event, JuvenileCasefileReferrals.class);
		return casefiles;
	}	
	


	//added for Court Numbers Search
	
	public int compareTo(Object obj)
	{
		if(obj==null)
			return -1;
		JuvenileCasefileReferrals caseRef = (JuvenileCasefileReferrals)obj;
		return assignmentDate.compareToIgnoreCase(caseRef.getAssignmentDate());
	}





	public String getCasefileId() {
		return casefileId;
	}





	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}





	public String getPetitionNum() {
		return petitionNum;
	}





	public void setPetitionNum(String petitionNum) {
		this.petitionNum = petitionNum;
	}





	public String getSupervisionTypeCd() {
		return supervisionTypeCd;
	}





	public void setSupervisionTypeCd(String supervisionTypeCd) {
		this.supervisionTypeCd = supervisionTypeCd;
	}





	public String getAssignmentDate() {
		return assignmentDate;
	}





	public void setAssignmentDate(String assignmentDate) {
		this.assignmentDate = assignmentDate;
	}





	public String getSupervisionTypeDesc() {
		return supervisionTypeDesc;
	}





	public void setSupervisionTypeDesc(String supervisionTypeDesc) {
		this.supervisionTypeDesc = supervisionTypeDesc;
	}





	public String getReferralNum() {
		return referralNum;
	}





	public void setReferralNum(String referralNum) {
		this.referralNum = referralNum;
	}





	public String getAssignOfficerId() {
		return assignOfficerId;
	}





	public void setAssignOfficerId(String assignOfficerId) {
		this.assignOfficerId = assignOfficerId;
	}





	public String getJuvenileId() {
		return juvenileId;
	}





	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}





	public String getOfficerLName() {
		return officerLName;
	}





	public void setOfficerLName(String officerLName) {
		this.officerLName = officerLName;
	}





	public String getOfficerFName() {
		return officerFName;
	}





	public void setOfficerFName(String officerFName) {
		this.officerFName = officerFName;
	}





	public String getOfficerMName() {
		return officerMName;
	}





	public void setOfficerMName(String officerMName) {
		this.officerMName = officerMName;
	}
	
	
}
