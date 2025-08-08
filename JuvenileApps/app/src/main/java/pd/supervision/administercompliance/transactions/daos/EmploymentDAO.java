package pd.supervision.administercompliance.transactions.daos;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import messaging.administercompliance.GetNCEmploymentsEvent;
import messaging.administercompliance.GetNCResponseComponentsEvent;
import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.UpdateNCEmploymentEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.administercompliance.reply.NCEmploymentResponseEvent;
import messaging.contact.GetEmployerByDateEvent;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.ViolationReportConstants;
import pd.common.DAOHandler;
import pd.contact.Employer;
import pd.security.PDSecurityHelper;
import pd.supervision.administercompliance.Comment;
import pd.supervision.administercompliance.Employment;
import pd.supervision.administercompliance.ViolationReport;
import pd.supervision.administercompliance.ViolationReportUtility;
import pd.supervision.supervisionorder.SupervisionPeriod;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/**
 * When a condition is set to non-compliant, the event(s) leading to this are
 * documented.  Event Types come from Events configured in the Condition in MSO.
 */
public class EmploymentDAO extends ViolationReportUtility implements DAOHandler
{
	/**
	 * 
	 * @roseuid 473B85EB0040
	 */
	public EmploymentDAO()
	{
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#execute(java.lang.Object)
	 */
	public void execute(Object object) {
		postDb2Data(object.toString());   
	}
	
	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#remove(java.lang.Object)
	 */
	public void remove(Object object) {
		deleteJims2Data(object.toString());
	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#update(java.lang.Object)
	 */
	public void update(Object object) {
		UpdateNCResponseEvent reqEvent = (UpdateNCResponseEvent) object;
		createViolationReport(reqEvent);
		
		Enumeration enumer = reqEvent.getRequests();
		Employment emp = null;
		
		Map existingMap = Employment.findAllByNumericParameter(ViolationReportConstants.PARAM_NCRESPONSE_ID, reqEvent.getNcResponseId()); 
		
		while(enumer.hasMoreElements()){
			UpdateNCEmploymentEvent eEvent = (UpdateNCEmploymentEvent) enumer.nextElement();
			if(eEvent.getEmploymentId() == null || eEvent.getEmploymentId().equals("")){
				emp = new Employment();
				emp.setEmployment(eEvent,reqEvent.getNcResponseId());
				emp.commit();
			}else{
				emp = Employment.find(eEvent.getEmploymentId());
				if(emp != null && existingMap.containsKey(emp.getOID())){
					existingMap.remove(emp.getOID());
					emp.commit();
				}
			}
		}
		setComments (ViolationReportConstants.REQUEST_EMPLOYMENT, reqEvent.getNcResponseId(), reqEvent);

		// at this point existingmap only contains unwanted stuffs
		Iterator unwantedIter = existingMap.values().iterator();
		while(unwantedIter.hasNext()){
			emp = (Employment) unwantedIter.next();
			if(emp != null){
				emp.delete();
				emp.commit();
			}
		}
		postDb2Data(reqEvent.getNcResponseId());
	}

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#get(java.lang.Object)
	 */
	public void get(Object object) {
		GetNCResponseComponentsEvent reqEvent = (GetNCResponseComponentsEvent) object;
		if(ViolationReportConstants.CREATE_MODE.equalsIgnoreCase(reqEvent.getMode())){
			GetNCEmploymentsEvent gEvent = (GetNCEmploymentsEvent) reqEvent.getRequests().nextElement();
			postLegacyData(reqEvent.getNcResponseId(),gEvent);			
		}else{
			this.postDb2Data(reqEvent.getNcResponseId());
			postComments(ViolationReportConstants.REQUEST_EMPLOYMENT,reqEvent.getNcResponseId());
		}
	}
	

	/* (non-Javadoc)
	 * @see pd.common.DAOHandler#refresh(java.lang.Object)
	 */
	public void refresh(Object object) {
		RefreshNCResponseComponentsEvent refEvent = (RefreshNCResponseComponentsEvent) object;
		
		// delete jims2 data
		ViolationReport vr = ViolationReport.find(refEvent.getNcResponseId());
		if(vr != null){
			deleteJims2Data(refEvent.getNcResponseId());
			Comment comment = getComments(ViolationReportConstants.REQUEST_EMPLOYMENT, refEvent.getNcResponseId());
			if(comment != null){
				comment.delete();
			}		
		}
		
		// retrieve from Legacy
		GetNCEmploymentsEvent gEvent = new GetNCEmploymentsEvent();
		gEvent.setDefandantId(refEvent.getDefendantId());
		postLegacyData(refEvent.getNcResponseId(),gEvent);
	}
	
	/**
	 * @param ncResponseId
	 * @return
	 */
	private boolean postDb2Data(String ncResponseId){
		boolean isExist = false;
		Iterator iterator = Employment.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			Employment e = (Employment) iterator.next();
			NCEmploymentResponseEvent resp = e.getResponse();
			MessageUtil.postReply(resp);
			isExist = true;
		}
		return isExist;
	}
	
	/**
	 * @param event
	 * @param ncResponseId
	 */
	private void postLegacyData(String ncResponseId, Object object) {
		GetNCEmploymentsEvent gEvent = (GetNCEmploymentsEvent) object;
		SupervisionPeriod sp = SupervisionPeriod.findActiveSupervisionPeriod(gEvent.getDefandantId(), PDSecurityHelper.getUserAgencyId());
        if(sp == null){
        	sp = SupervisionPeriod.findLatestSupervisionPeriod(gEvent.getDefandantId(), PDSecurityHelper.getUserAgencyId());
        }
        if(sp != null){
			GetEmployerByDateEvent empEvent = new GetEmployerByDateEvent();
            empEvent.setStartDate(DateUtil.dateToString(sp.getSupervisionBeginDate(), ViolationReportConstants.DATE_FORMAT_YYYYMMDD));			
			empEvent.setDefendantId(gEvent.getDefandantId());
            Iterator iterator = Employer.findAll(empEvent); 
			while(iterator.hasNext()){
				Employer e = (Employer) iterator.next();
				NCEmploymentResponseEvent response = e.getResponse();
				response.setNcResponseId(ncResponseId);
				response.setEmploymentId(new StringBuffer("L").append(response.getEmploymentId()).toString());				
				MessageUtil.postReply(response);
			}
	    }
	}
	
	/**
	 * @param ncResponseId
	 */
	private void deleteJims2Data(String ncResponseId) {
		Iterator iterator = Employment.findAllByNumericParam(ViolationReportConstants.PARAM_NCRESPONSE_ID, ncResponseId); 
		while(iterator.hasNext()){
			Employment e = (Employment) iterator.next();
		    e.delete();
		}
	}
}
