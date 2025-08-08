//Source file: C:\\views\\dev\\app\\src\\pd\\juvenile\\transactions\\SaveJuvenileSchoolHistoryCommand.java

package pd.juvenile.transactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import messaging.juvenile.GetJuvenileSchoolEvent;
import messaging.juvenile.SaveJuvenileSchoolHistoryEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.address.Address;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenile.JuvenileSchoolHistory;

public class SaveJuvenileSchoolHistoryCommand implements ICommand {

	/**
	 * @roseuid 42BC4D31002A
	 */
	public SaveJuvenileSchoolHistoryCommand() {

	}

	/**
	 * @param event
	 * @roseuid 42BC4BE0005B
	 */
	public void execute(IEvent event) {
		SaveJuvenileSchoolHistoryEvent saveEvent = (SaveJuvenileSchoolHistoryEvent) event;
		Enumeration<JuvenileSchoolHistoryResponseEvent> events = saveEvent.getRequests();
		String juvenileNum = null;
		String exitTypeCodeId = null;
		while (events.hasMoreElements()) {
			JuvenileSchoolHistoryResponseEvent schoolEvent = (JuvenileSchoolHistoryResponseEvent) events.nextElement();
			juvenileNum = schoolEvent.getJuvenileNum();

			JuvenileSchoolHistory school = new JuvenileSchoolHistory();
			
			school.setAppropriateLevelId(schoolEvent.getAppropriateLevelCode());
			school.setExitTypeId(schoolEvent.getExitTypeCode());
			school.setGradeLevelId(schoolEvent.getGradeLevelCode());
			school.setJuvenileNum(schoolEvent.getJuvenileNum());
			school.setLastAttendedDate(schoolEvent.getLastAttendedDate());
			school.setSchoolDistrictId(schoolEvent.getSchoolDistrictId());
			school.setSchoolId(schoolEvent.getSchoolId());
			school.setTeaSchoolNumber(schoolEvent.getTEASchoolNumber());
			school.setRuleInfractionId(schoolEvent.getRuleInfractionCode());
			school.setGradeAverage(schoolEvent.getGradeAverage());
			school.setGradesRepeatNumber(schoolEvent.getGradesRepeatNumber());
			school.setGradesRepeatedId(schoolEvent.getGradesRepeatedCode());
			school.setGradeRepeatTotal(schoolEvent.getGradeRepeatTotal());
			if ( schoolEvent.getGradeChangeReason() != null  && (StringUtils.isEmpty( schoolEvent.getGradeChangeReason()))){
			    school.setGradeChangeReason(null);
			} else {
			    school.setGradeChangeReason(schoolEvent.getGradeChangeReason());
			}			
			school.setParticipationId(schoolEvent.getParticipationCode());
			school.setProgramAttendingId(schoolEvent.getProgramAttendingCode());
			school.setSplEduCategoryId(schoolEvent.getSplEduCategoryCode());
			school.setSchoolAttendanceStatusId(schoolEvent.getSchoolAttendanceStatusCode());
			school.setVerifiedDate(schoolEvent.getVerifiedDate());
			school.setEligibilityEnrollmentDate(schoolEvent.getEligibilityEnrollmentDate());
			school.setTruancyHistory(schoolEvent.getTruancyHistory());
			school.setHomeSchoolDistrictId(schoolEvent.getHomeSchoolDistrictId());
			school.setHomeSchoolId(schoolEvent.getHomeSchoolId());
			school.setSpecifiedSchoolName(schoolEvent.getSpecificSchoolName());
			school.setAddressId(schoolEvent.getAddressId());
			school.setEducationService(schoolEvent.getEducationService());
			school.setAcademicPerformance( schoolEvent.getAcademicPerformance());
			school.setSchoolInfoVerifiedBy( schoolEvent.getSchoolInfoVerifiedBy());
			school.setTruancy(schoolEvent.getTruancy());
			school.setVerifiedByOther(schoolEvent.getVerifiedByOther());
			school.setCompletionDate(schoolEvent.getCompletionDate());
			school.setGedAwarded(schoolEvent.isGedAwarded());
			school.setGedAwardedDate(schoolEvent.getGedAwardedDate());
			school.setGedCompleted(schoolEvent.isGedCompleted());

			boolean addressValuePresent = school.checkAddressFields (schoolEvent);
			if (addressValuePresent){
				Address address = new Address();
				address.setStreetNum(schoolEvent.getSchoolStreetNum());
				address.setStreetName(schoolEvent.getSchoolStreetName());
				address.setStateId(schoolEvent.getSchoolState());
				address.setCity(schoolEvent.getSchoolCity());
				address.setZipCode(schoolEvent.getSchoolZip());
				address.setAdditionalZipCode(schoolEvent.getSchoolZipCodeExt());
				new Home().bind(address);
				schoolEvent.setAddressId(address.getOID());
			}
			/*school.hydrate(schoolEvent);
			 * deprecated
			 */
			exitTypeCodeId = schoolEvent.getExitTypeCode();
			//==============ERJIMS200077237 writing the school id and district id in JJS M204
			if ("C".equals(exitTypeCodeId)){
				JuvenileCore juvenile = new JuvenileCore();
				juvenile = JuvenileCore.findCore(juvenileNum);	
				juvenile.setSchoolId(school.getSchoolId());
				juvenile.setSchoolDistrictId(school.getSchoolDistrictId());	
				// Profile stripping fix
				//Juvenile juv = Juvenile.find(juvenileNum);
				Juvenile juv = Juvenile.findJCJuvenile(juvenileNum);
				//
				if(juv.getHispanic()!=null && juv.getHispanic().equalsIgnoreCase("Y"))
				    juvenile.setRaceIdForHispanic("L");
			}
			//---------Ended--------------
		}

		if (juvenileNum != null) {
			if ("C".equals(exitTypeCodeId) || "W".equals(exitTypeCodeId) ||
				"G".equals(exitTypeCodeId) || "E".equals(exitTypeCodeId) ||
				"R".equals(exitTypeCodeId) || "X".equals(exitTypeCodeId) ) {
				GetJuvenileSchoolEvent evt = new GetJuvenileSchoolEvent();
				evt.setJuvenileNum(juvenileNum);
				Iterator<JuvenileSchoolHistory> ite = JuvenileSchoolHistory.findJuvenileSchoolHistory(evt);	
				
				JuvenileSchoolHistory lastAddedSchoolHistory = getLastAddedSchoolHistory(ite);
				
				if(lastAddedSchoolHistory != null){
				    
				    if ("C".equals(lastAddedSchoolHistory.getExitTypeId()) || "W".equals(lastAddedSchoolHistory.getExitTypeId()) ||
						"G".equals(lastAddedSchoolHistory.getExitTypeId()) || "E".equals(lastAddedSchoolHistory.getExitTypeId()) ||
						"R".equals(lastAddedSchoolHistory.getExitTypeId())) {
					
							lastAddedSchoolHistory.setExitTypeId("X");
					}
				}
				
//				while (ite.hasNext()) {
//					JuvenileSchoolHistory history = (JuvenileSchoolHistory) ite.next();	
//										
//					if ("C".equals(history.getExitTypeId()) || "W".equals(history.getExitTypeId()) ||
//						"G".equals(history.getExitTypeId()) || "E".equals(history.getExitTypeId()) ||
//						"R".equals(history.getExitTypeId())) {
//							history.setExitTypeId("X");
//					}
//				}
			}
		}
	}
	
	public JuvenileSchoolHistory getLastAddedSchoolHistory(Iterator<JuvenileSchoolHistory> it){
	    
	    JuvenileSchoolHistory schoolHistoryWithMaxCreateDate = null;
	    ArrayList<JuvenileSchoolHistory> list = new ArrayList<JuvenileSchoolHistory>();
	    while (it.hasNext()){
		list.add(it.next());
	    }
	    
	    if(list.size() >0) {
		
		schoolHistoryWithMaxCreateDate = Collections.max(list); 
	    }
	       	
	    
	    return schoolHistoryWithMaxCreateDate;
	}

	/**
	 * @param event
	 * @roseuid 42BC4BE0005D
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 42BC4BE0006A
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 * @roseuid 42BC4D31003A
	 */
	public void update(Object updateObject) {

	}
}
