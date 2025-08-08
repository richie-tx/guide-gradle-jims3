package pd.supervision.cscdcalendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import naming.PDCodeTableConstants;

import org.apache.commons.lang.StringUtils;

import pd.codetable.supervision.PDSupervisionCodeHelper;
import pd.codetable.supervision.SupervisionCode;
import pd.contact.party.Party;
import messaging.cscdcalendar.GetCSEventsReportEvent;
import messaging.cscdcalendar.GetCalendarEventsReportEvent;
import messaging.cscdcalendar.reply.CSEventsReportReponseEvent;
import messaging.cscdstaffposition.GetStaffPositionsByUserIdEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.utilities.MessageUtil;
import mojo.km.utilities.Name;

/**
 * 
 * @author cc_bjangay
 *
 */
public class CSEventsReportPDHelper
{
	
	/**
	 * 
	 * @param csEventsReportEvent
	 * @return
	 */
	public static GetCalendarEventsReportEvent getCalendarEventsReportQueryEvent(GetCSEventsReportEvent csEventsReportEvent)
	{
		GetCalendarEventsReportEvent reportQueryEvent = new GetCalendarEventsReportEvent();
		reportQueryEvent.setSupervisorPositionId(csEventsReportEvent.getPositionId());
		reportQueryEvent.setCsCalendarCategory(csEventsReportEvent.getCsEventCategory());
		reportQueryEvent.setStartDate(csEventsReportEvent.getStartDate());
		reportQueryEvent.setEndDate(csEventsReportEvent.getEndDate());
		
		return reportQueryEvent;
	}//end of getCalendarEventsReportQueryEvent()
	
	
	/**
	 * 
	 * @param csEventsReportList
	 * @return
	 */
	public static List getCSEventsReportResponseEvent(List csEventsReportList)
	{  
		ArrayList responseEvtList = new ArrayList();
		HashMap defendantIdNameMap = new HashMap();
		Iterator iter = csEventsReportList.iterator();
		if( csEventsReportList != null )
		{
			HashMap FV_TYPES = new HashMap();
			HashMap FV_PURPOSE = new HashMap();
			HashMap SEX_OFFENDER_TYPES = new HashMap();
			
			Iterator fvTypeIter = PDSupervisionCodeHelper.getSupervisionCodes(PDCodeTableConstants.FV_TYPES);			
			while ( fvTypeIter.hasNext()){
				
				SupervisionCode fv_code = (SupervisionCode) fvTypeIter.next();
				FV_TYPES.put(fv_code.getCode(), fv_code.getDescription());
			}

			Iterator fvPurposeIter = PDSupervisionCodeHelper.getSupervisionCodes(PDCodeTableConstants.FV_PURPOSE);			
			while ( fvPurposeIter.hasNext()){
				
				SupervisionCode fv_code = (SupervisionCode) fvPurposeIter.next() ;
				FV_PURPOSE.put(fv_code.getCode(), fv_code.getDescription());
			}
			Iterator sexOffenderTypeIter = PDSupervisionCodeHelper.getSupervisionCodes(PDCodeTableConstants.SEX_OFFENDER_TYPES);
			while ( sexOffenderTypeIter.hasNext()){
				
				SupervisionCode fv_code = (SupervisionCode) sexOffenderTypeIter.next() ;
				SEX_OFFENDER_TYPES.put(fv_code.getCode(), fv_code.getDescription());
			}

			while(iter.hasNext())
			{
				CSEventsReport csEventsReport = (CSEventsReport)iter.next();
				CSEventsReportReponseEvent responseEvt = new CSEventsReportReponseEvent();
				responseEvt.setCsEventId(csEventsReport.getOID());
				String defendantId = csEventsReport.getSuperviseeId();
				responseEvt.setDefendantId(defendantId);
				String defendantName = "";
				if((defendantId!=null) && (defendantId.trim().length()>0))
				{
					if(defendantIdNameMap.containsKey(defendantId))
					{
						defendantName = (String)defendantIdNameMap.get(defendantId);
					}
					else
					{
						defendantName = getDefendantName(defendantId);
						defendantIdNameMap.put(defendantId, defendantName.trim());
					}
				}
				responseEvt.setDefendantName(defendantName);
				responseEvt.setOutcomeCd(csEventsReport.getOutcomeCd());
				if(StringUtils.isNotEmpty(csEventsReport.getresultUserId())){
					String resultUserId = csEventsReport.getresultUserId();
					responseEvt.setResultUserId(resultUserId);
					if(resultUserId.equals(csEventsReport.getPositionId())){
						responseEvt.setResultPositionName(csEventsReport.getPositionName());
					}else{
						GetStaffPositionsByUserIdEvent getStaffEvent = new GetStaffPositionsByUserIdEvent();
						 List idlist = new ArrayList();
					     
						    getStaffEvent.setAgencyId("CSC");
						    idlist.add(resultUserId);
						    getStaffEvent.setLogonIds(idlist);
						List aList = MessageUtil.postRequestListFilter(getStaffEvent, CSCDSupervisionStaffResponseEvent.class);
						if( aList != null ){
							Iterator staffIter = aList.iterator();
							while( staffIter.hasNext() ){
								CSCDSupervisionStaffResponseEvent staff = (CSCDSupervisionStaffResponseEvent) staffIter.next();
								responseEvt.setResultPositionName(staff.getPositionName());
							}
						
						}
					}					
				}
				responseEvt.setCsEventTypeId(csEventsReport.getEventTypeId());
				if ( StringUtils.isNotEmpty(csEventsReport.getEventTypeId()) && csEventsReport.getEventTypeId().equals("FV") ) {
					StringBuffer fvTypeDesc = new StringBuffer();
					if ( StringUtils.isNotEmpty(csEventsReport.getFvType()) ) {
						String fvTypeCd = csEventsReport.getFvType();
						String type = "";
						type = (String) FV_TYPES.get( fvTypeCd );
						responseEvt.setFvType(type);
						fvTypeDesc.append(type);
						if ( StringUtils.isNotEmpty(csEventsReport.getFvPurpose()) && csEventsReport.getFvType().equals("SO") ) {
							String purposeCd = csEventsReport.getFvPurpose();
							String purpose = (String) FV_PURPOSE.get( purposeCd );
							if( StringUtils.isNotEmpty(purpose)) {
								responseEvt.setFvPurpose(purpose);
								if( StringUtils.isNotEmpty(csEventsReport.getSexOffenderType())) {
									String sexOffenderCd = csEventsReport.getSexOffenderType();
									String sexOffType = (String) SEX_OFFENDER_TYPES.get( sexOffenderCd );
									responseEvt.setFvPurpose(sexOffType);
									fvTypeDesc.append(" (");
									fvTypeDesc.append(sexOffType);
									fvTypeDesc.append(")");
								}
							}
						}	
						responseEvt.setCsEventTypeDesc(fvTypeDesc.toString());
					}
				} else {
					responseEvt.setCsEventTypeDesc(csEventsReport.getEventTypeDesc());
				}
				responseEvt.setCsEventDate(csEventsReport.getEventDate());
				responseEvtList.add(responseEvt);
			}
		}
		return responseEvtList;
	}//end of getCSEventsReportResponseEvent()
	
	
	
	/**
	 * 
	 * @param defendantId
	 * @return
	 */
	public static String getDefendantName(String defendantId)
	{
		String partyName = "NAME NOT FOUND";
		Party defendant = Party.find(defendantId);
		
		if ( defendant != null ){
			
			Name defendantName = new Name(defendant.getFirstName(), defendant.getMiddleName(), defendant.getLastName());
			partyName = defendantName.getFormattedName();
		}
		return partyName;
	}//end of getDefendantName()
	
}
