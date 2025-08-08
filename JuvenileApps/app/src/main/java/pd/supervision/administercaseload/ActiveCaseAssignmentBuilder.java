package pd.supervision.administercaseload;

import java.util.Calendar;
import java.util.Date;

import messaging.administercaseload.domintf.ICaseAssignment;
import messaging.administercaseload.to.CaseAssignmentTO;
import mojo.km.utilities.DateUtil;
import mojo.pattern.IBuilder;
import naming.PDConstants;
import naming.UIConstants;
import pd.codetable.criminal.OffenseCode;

/**
 * @author Jim Fisher
 */
public class ActiveCaseAssignmentBuilder implements IBuilder
{
    private ICaseAssignment bean;

    private CaseAssignmentOrder caseAssignment;
    
    public ActiveCaseAssignmentBuilder()
    {
    }

    /* (non-Javadoc)
     * @see mojo.pattern.IBuilder#build()
     */
    public void build() {
        this.bean = new CaseAssignmentTO();

        String criminalCaseId = caseAssignment.getCriminalCaseId();
            		
        bean.setProgramUnitId(caseAssignment.getProgramUnitId());
        bean.setProgramUnitName(caseAssignment.getProgramUnitName());
        bean.setProgramUnitAssignDate(caseAssignment.getProgramUnitAssignDate());       
        bean.setCriminalCaseId(criminalCaseId);
        if ( !"".equals(criminalCaseId) && criminalCaseId.length() > 3 ){
        	
        	String caseNum = criminalCaseId.substring( 3 );
        	bean.setDisplayCaseNum( caseNum );
        	
        }
        bean.setDefendantId(caseAssignment.getDefendantId());   
        bean.setSuperviseeName( caseAssignment.getDefendantName() );
        bean.setOrderStatusCode(caseAssignment.getOrderStatusCode());
        bean.setSupervisionStyleCode(caseAssignment.getSupervisionStyleId());
        bean.setTerminationDate(caseAssignment.getTerminationDate());
        if (caseAssignment.getCaseAssignId() != null){
        	bean.setCaseAssignmentId(caseAssignment.getCaseAssignId());
        } else {
        	bean.setCaseAssignmentId(caseAssignment.getOID());
        }
        bean.setSupervisionOrderId(caseAssignment.getSupervisionOrderId());                
        bean.setAssignedStaffPositionId(caseAssignment.getAssignedStaffPositionId());
        bean.setAllocatedStaffPositionId(caseAssignment.getAllocatedStaffPositionId());
        bean.setSupervisorPositionId(caseAssignment.getSupervisorPositionId());
        bean.setCourtId(caseAssignment.getCourtId());   
        bean.setAcknowledgeStatusCode(caseAssignment.getAcknowledgeStatusId());
        setDegreeOfOffense(caseAssignment);
        setCriminalCaseInfo(caseAssignment);
        setProbationDateRange(caseAssignment.getSupervisionBeginDate(),caseAssignment.getSupervisionEndDate());
     }      
    
    public void setActiveCaseAssignment(CaseAssignmentOrder aCaseAssignment)
    {
        this.caseAssignment = aCaseAssignment;
    }

    /* (non-Javadoc)
     * @see mojo.pattern.IBuilder#getResult()
     */
    public Object getResult()
    {
        return this.bean;
    }    
    
    /**
     * Business Rule: 
     * "Days Left" is calculated from the case SupervisionEndDate and is 
     * only displayed when it's value is 90 days or less.
     *
     */
    private void setProbationDateRange(Date sdate, Date edate) {    	    	
        long diffInDays = Long.MAX_VALUE;
        if (edate != null) {
            Date today = new Date();           
            Calendar scal = Calendar.getInstance();
            scal.setTime(today); 
            Calendar ecal = Calendar.getInstance();
            ecal.setTime(edate);             
            
            diffInDays = (ecal.getTimeInMillis() - scal.getTimeInMillis())/(1000 * 60 * 60 * 24);        	        	

            bean.setProbationEndDate(DateUtil.dateToString(edate, UIConstants.DATE_FMT_1));
            bean.setDaysLeft(diffInDays);                	
        }
        if (sdate != null) {
            bean.setProbationStartDate(DateUtil.dateToString(sdate, UIConstants.DATE_FMT_1));        	
        }
    }
    
    private void setCriminalCaseInfo(CaseAssignmentOrder caseAssignment) {
    	String courtId = caseAssignment.getCourtId();
    	//Court id is of 3 characters in legacy and user expects to see it like that.
    	for (int i = courtId.length(); i < 3; i++) {
    		courtId = "0" + courtId;
    	}    	
    	bean.setCaseStatus(caseAssignment.getCaseStatusId());
    	bean.setDefendantStatus(caseAssignment.getDefendantStatusId());
    	bean.setCourtId(courtId);
    }
    
    private void setDegreeOfOffense(CaseAssignmentOrder caseAssignment) {
    	String level = "";
    	String degree = "";
    	if (caseAssignment.getOffenseCode() != null
    			&& !PDConstants.BLANK.equals(caseAssignment.getOffenseCode())){
    		OffenseCode offenseCode = OffenseCode.find(caseAssignment.getOffenseCode());
    		if (offenseCode != null) {
    			if (offenseCode.getLevel() != null) {
    				level = offenseCode.getLevel();
    			}
    			if (offenseCode.getDegree() != null) {
    				degree = offenseCode.getDegree();
    			}
    		}
    	}
    	String degreeOfOffense = level + degree;
        bean.setDegreeOfOffense(degreeOfOffense);    	
    }
}
