package pd.supervision.administercaseload;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

public class CaseloadInOutActivity extends PersistentObject 
{
	private String caseAssignIoId;
	private String defendantId;
	private String criminalCaseId;
	private Date beginDate;
	private Date endDate;
	private String assignStaffPositionId;
	private String inOut;
	private String supervisionOrderId;
	private String courtId;

	public String getCaseAssignIoId() {
		return caseAssignIoId;
	}
	public void setCaseAssignIoId(String caseAssignIoId) {
		this.caseAssignIoId = caseAssignIoId;
	}
	public String getDefendantId() {
		return defendantId;
	}
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getAssignStaffPositionId() {
		return assignStaffPositionId;
	}
	public void setAssignStaffPositionId(String assignStaffPositionId) {
		this.assignStaffPositionId = assignStaffPositionId;
	}
	public String getInOut() {
		return inOut;
	}
	public void setInOut(String inOut) {
		this.inOut = inOut;
	}

	/*************** Retrieval Methods ********************************/
    public static CaseloadInOutActivity find(String anOid)
    {
        IHome home = new Home();
        return (CaseloadInOutActivity) home.find(anOid, CaseloadInOutActivity.class);
    }
    
    public static Iterator findAll(IEvent anEvent)
    {
        IHome home = new Home();
        return home.findAll(anEvent, CaseloadInOutActivity.class);
    }
    
    public static Iterator findAll(String attrName, String attrValue)
    {
        IHome home = new Home();
        return home.findAll(attrName, attrValue, CaseloadInOutActivity.class);
    }
	public String getSupervisionOrderId() {
		return supervisionOrderId;
	}
	public void setSupervisionOrderId(String supervisionOrderId) {
		this.supervisionOrderId = supervisionOrderId;
	}
	public String getCourtId() {
		return courtId;
	}
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	

}
