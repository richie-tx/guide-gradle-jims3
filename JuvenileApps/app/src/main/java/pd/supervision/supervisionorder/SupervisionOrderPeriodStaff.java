package pd.supervision.supervisionorder;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;

/**
 * @author mchowdhury
 */
public class SupervisionOrderPeriodStaff extends PersistentObject implements Comparable
{
	private String defendantId;
	private String staffPositionId;
	private String userProfileId;
	private String criminalCaseId;
	private String orderStatusId;
	private String currentCourtId;
	private int sprPeriodId;
	
	public String getDefendantId() {
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}

	public String getStaffPositionId() {
		return staffPositionId;
	}

	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}

	public String getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	public String getCriminalCaseId() {
		return criminalCaseId;
	}

	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}

	public String getOrderStatusId() {
		return orderStatusId;
	}

	public void setOrderStatusId(String orderStatusCd) {
		this.orderStatusId = orderStatusCd;
	}

	public String getCurrentCourtId() {
		return currentCourtId;
	}

	public void setCurrentCourtId(String currentCourtId) {
		this.currentCourtId = currentCourtId;
	}

	public int getSprPeriodId() {
		return sprPeriodId;
	}

	public void setSprPeriodId(int sprPeriodId) {
		this.sprPeriodId = sprPeriodId;
	}

	public static Iterator findAll(IEvent event){
		return new Home().findAll(event, SupervisionOrderPeriodStaff.class);
	}
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}

