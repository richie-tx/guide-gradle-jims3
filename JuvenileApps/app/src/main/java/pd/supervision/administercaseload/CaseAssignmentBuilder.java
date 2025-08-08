package pd.supervision.administercaseload;

import java.util.Iterator;
import java.util.List;

import naming.PDConstants;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.to.CaseAssignmentTO;
import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.criminalcase.GetCriminalCaseDb2Event;
import mojo.km.utilities.CollectionUtil;
import mojo.pattern.IBuilder;
import pd.contact.party.Party;
import pd.contact.user.UserProfile;
import pd.criminalcase.CriminalCaseDb2;
import pd.supervision.Court;
import pd.supervision.supervisionorder.SupervisionOrder;
import pd.supervision.supervisionstaff.Organization;
import pd.supervision.supervisionstaff.cscdstaffposition.CSCDStaffPosition;

/**
 * @author Jim Fisher
 */
public class CaseAssignmentBuilder implements IBuilder {
	private ICaseAssignment bean;

	private CaseAssignment caseAssignment;

	private SupervisionOrder supervisionOrder;

	public CaseAssignmentBuilder(CaseAssignment aCaseAssignment) {
		this.caseAssignment = aCaseAssignment;
	}
	
	public CaseAssignmentBuilder(SupervisionOrder supervisionOrder) {
		this.supervisionOrder = supervisionOrder; 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.pattern.IBuilder#build()
	 */
	public void build() {
		if (caseAssignment != null) { // case assignment has already been made
			bean = caseAssignment.valueObject();
			setProgramUnitName();
			setAcknowledgeName();
			setDefendantName();
			setCourtDetails();
		} else if (supervisionOrder != null) {
			bean = new CaseAssignmentTO();
			setDefendantDetails();			
		}
	}

	private void setProgramUnitName() {
		Organization organization = caseAssignment.getProgramUnit();
		if (organization != null) {
			this.bean.setProgramUnitName(organization.getDescription());
		}
	}
	
	private void setDefendantName() {
		String defendantId = bean.getDefendantId();
        Party defendant = Party.find(defendantId);
        IName defendantName = null;
        if (defendant != null){
        	defendantName = new NameBean(defendant.getFirstName(), defendant.getMiddleName(), defendant.getLastName());
        	bean.setDefendantName(defendantName);
        } else if (caseAssignment != null){
        		GetCriminalCaseDb2Event anEvent = new GetCriminalCaseDb2Event();
        		anEvent.setCourtDivisionId(caseAssignment.getCriminalCaseId().substring(0,3));
        		anEvent.setCaseNum(caseAssignment.getCriminalCaseId().substring(3));
        		Iterator iter = CriminalCaseDb2.findAll(anEvent);
        		List aList = CollectionUtil.iteratorToList(iter);
        		if (aList.size() > 0){
        			CriminalCaseDb2 theCase = (CriminalCaseDb2) aList.get(0);
        			defendantName = new NameBean();
        			defendantName.setLastName(theCase.getDefendantName());
        			bean.setDefendantName(defendantName);
        		} else {
        			bean.setDefendantName(new NameBean(PDConstants.BLANK, PDConstants.BLANK, PDConstants.BLANK));
        		}
        } else {
        	 bean.setDefendantName(new NameBean(PDConstants.BLANK, PDConstants.BLANK, PDConstants.BLANK));
        }
	}

	private void setAcknowledgeName() {
		CSCDStaffPosition ackPosition = caseAssignment.getAcknowledgePosition();
		if (ackPosition != null) {
			bean.setAcknowledgePositionName(ackPosition.getPositionName());
			UserProfile userProfile = ackPosition.getUserProfile();
			if (userProfile != null) {
				bean.setAcknowledgeUserName(userProfile.getName());
			}
		}
		CSCDStaffPosition assignedStaffPosition = caseAssignment.getAssignedStaffPosition();
		if (assignedStaffPosition != null) {
			UserProfile userProfile = assignedStaffPosition.getUserProfile();
			if (userProfile != null) {
				bean.setOfficerName(userProfile.getName());
			}
		}
		CSCDStaffPosition allocatedStaffPosition = caseAssignment.getAllocatedStaffPosition();
		if (allocatedStaffPosition != null) {
			UserProfile userProfile = allocatedStaffPosition.getUserProfile();
			if (userProfile != null) {
				bean.setSupervisorName(userProfile.getName());
			}
		}
	}
	
	private void setCourtDetails() {
		String supervisionOrderId = bean.getSupervisionOrderId();
		SupervisionOrder so = SupervisionOrder.find(supervisionOrderId);
		//set court id to 3 digits
		Court court = so.getCurrentCourt();
		if ( court != null ) {
			StringBuffer padCrt = new StringBuffer( court.getCourtNumber() );
			while ( padCrt.length() < 3 ){
		    	padCrt.insert( 0, "0" );
		    }
			bean.setCourtId( padCrt.toString() );
		}
	}

	private void setDefendantDetails() {
		bean.setDefendantId(supervisionOrder.getDefendantId());
		bean.setCriminalCaseId(supervisionOrder.getCriminalCaseId());
		bean.setSupervisionOrderId(supervisionOrder.getOID());

        Party defendant = Party.find(supervisionOrder.getDefendantId());

		IName nameBean = null;
		if (defendant != null){
			nameBean = new NameBean(defendant.getFirstName(), defendant.getMiddleName(), defendant.getLastName());
		} else if (supervisionOrder != null){
				GetCriminalCaseDb2Event anEvent = new GetCriminalCaseDb2Event();
				anEvent.setCourtDivisionId(supervisionOrder.getCriminalCaseId().substring(0,3));
				anEvent.setCaseNum(supervisionOrder.getCriminalCaseId().substring(3));
				Iterator iter = CriminalCaseDb2.findAll(anEvent);
				List aList = CollectionUtil.iteratorToList(iter);
				if (aList.size() > 0){
					CriminalCaseDb2 theCase = (CriminalCaseDb2) aList.get(0);
					nameBean = new NameBean();
					nameBean.setLastName(theCase.getDefendantName());
				} else {
					nameBean = new NameBean(PDConstants.BLANK, PDConstants.BLANK, PDConstants.BLANK);
				}
		} else {
			nameBean = new NameBean(PDConstants.BLANK, PDConstants.BLANK, PDConstants.BLANK);
		}
		bean.setDefendantName(nameBean);
		
		Court court = supervisionOrder.getCurrentCourt();
		if (court != null) {
			this.bean.setCourtId(court.getCourtNumber());
		}
	}

	/**
	 * @see mojo.pattern.IBuilder#getResult()
	 */
	public Object getResult() {
		return bean;
	}

}
