package pd.productionsupport.transactions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import naming.PDCodeTableConstants;

import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.JuvenileProfilesResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.productionsupport.GetProdSupportJJSJuvenileEvent;
import messaging.productionsupport.reply.ProductionSupportCalendarResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.DateUtil;
import pd.common.calendar.CalendarEvent;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JJSJuvenile;
import pd.km.util.Name;
import ui.common.SimpleCodeTableHelper;

public class GetProdSupportJJSJuvenileCommand implements ICommand
{

    /**
     * @roseuid 452BA24800A2
     */
    public GetProdSupportJJSJuvenileCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4526B083011C
     */
    public void execute(IEvent event)
    {
		GetProdSupportJJSJuvenileEvent getJuvenileEvent = (GetProdSupportJJSJuvenileEvent) event;  
		JuvenileCore juvCore = JuvenileCore.findCore(getJuvenileEvent.getJuvenileId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			
		JuvenileProfileDetailResponseEvent response = new JuvenileProfileDetailResponseEvent();
			
		if(juvCore != null){		   
			response.setFirstName(juvCore.getFirstName());
			response.setJuvenileNum(juvCore.getJuvenileNum());
			response.setLastName(juvCore.getLastName());
			response.setMiddleName(juvCore.getMiddleName());
			response.setNameSuffix(juvCore.getNameSuffix());
			response.setRaceId(juvCore.getRaceId());
			response.setOriginalRaceId(juvCore.getOriginalRaceId());
			response.setRace(juvCore.getRaceCodeDescriptionProdSupport());//race code description for prod support pages - it doesn't factor in original race
			response.setOriginalRace(juvCore.getOriginalRaceCodeDescription());
			response.setSex(juvCore.getSexCodeDescription());
			response.setSexId(juvCore.getSexId());
			response.setDateOfBirth(juvCore.getDateOfBirth());
			response.setRealDOB(juvCore.getRealDOB());
			response.setLcuser(juvCore.getLcuser());
			response.setLcDate(juvCore.getLcDate());
			 SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
			 if(juvCore.getLcTime() != null)
			 {
			     String lctime = dateFormat.format(juvCore.getLcTime());
			     response.setLcTime(DateUtil.stringToDate(lctime, DateUtil.TIME_FMT_1));
			 }
			response.setLcuser(juvCore.getLcuser());	
			response.setCheckedOutTo(juvCore.getCheckOutTo());
			response.setCheckedOutDate(juvCore.getCheckedOutDate());
			response.setCompleteSSN(juvCore.getSSN());
			Name name = new Name(response.getFirstName(), response.getMiddleName(), response.getLastName(), response.getNameSuffix() );
			response.setFormattedName(name.getFormattedName());
			
			if(juvCore.getRecType() != null)
			    response.setRecType(juvCore.getRecType().toUpperCase());
			JJSJuvenile myJJSInfo = JJSJuvenile.find(getJuvenileEvent.getJuvenileId());
			response.setPurgeBoxNum(myJJSInfo.getPurgeBoxNum());
			response.setPurgeSerNum(myJJSInfo.getPurgeSerNum());
			response.setPurgeDate(DateUtil.dateToString(myJJSInfo.getPurgeDate(), DateUtil.DATE_FMT_1));
			response.setCaseNotePart1(myJJSInfo.getCaseNotePart1());
			response.setCaseNotePart2(myJJSInfo.getCaseNotePart2());
			response.setDetentionStatusId(myJJSInfo.getDetentionStatusId());
			response.setDetentionFacilityId(myJJSInfo.getDetentionFacilityId());
			response.setLastReferral(myJJSInfo.getLastReferralNum());
			response.setOldStatusId(myJJSInfo.getOldStatusId());
			response.setStatusId(myJJSInfo.getStatusId());
			response.setPurgeFlag(myJJSInfo.getPurgeFlag());
			response.setSealedDate(myJJSInfo.getSealedDate());
			response.setSealComments(juvCore.getSealComments());
			response.setLiveWithTjjd(juvCore.getLiveWithTjjd());
			response.setDeathAge(juvCore.getDeathAge());
			response.setDeathDate(juvCore.getDeathDate());
			response.setJuvExcluded(juvCore.getJuvExcludedReporting());
			response.setYouthDeathReason(juvCore.getYouthDeathReason());
			response.setYouthDeathVerification(juvCore.getYouthDeathVerification());
			dispatch.postEvent(response);
		}
    }
		/**
		 * Get facilty name and id from header
		 * @param juvNum
		 * @return
		 */
		private static JuvenileDetentionFacilitiesResponseEvent getJuvenileFacility(String juvNum)
		{
			Iterator<JJSHeader>  jjsHeaderItr = JJSHeader.findAll("juvenileNumber", juvNum) ;
			JuvenileDetentionFacilitiesResponseEvent detentionFacility = new JuvenileDetentionFacilitiesResponseEvent();
			if (jjsHeaderItr.hasNext()) {
				JJSHeader header =  jjsHeaderItr.next();
				detentionFacility.setDetentionStatus(header.getFacilityStatus());
				detentionFacility.setDetainedFacility(header.getFacilityCode());
				detentionFacility.setDetainedFacilityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY,header.getFacilityCode())); //bug fix #51529
			}
			return detentionFacility;
		}


    /**
     * @param event
     * @roseuid 4526B083011E
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4526B083012B
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 4526B083012D
     */
    public void update(Object anObject)
    {

    }
    
    private ProductionSupportCalendarResponseEvent getCalendarResponseEvent(CalendarEvent serviceEvent){
    	ProductionSupportCalendarResponseEvent calendarEvent = new ProductionSupportCalendarResponseEvent();
    	
    	calendarEvent.setCalendarEventId(new Integer(serviceEvent.getOID()));
    	calendarEvent.setBodyText(serviceEvent.getBodyText());
    	calendarEvent.setCalendarEventType(serviceEvent.getCalendarEventType());
    	
    	calendarEvent.setStartDatetime(serviceEvent.getStartDatetime());
    	calendarEvent.setEndDatetime(serviceEvent.getEndDatetime());
    	calendarEvent.setLocation(serviceEvent.getLocation());
    	calendarEvent.setStatus(serviceEvent.getStatus());
    	calendarEvent.setSubject(serviceEvent.getSubject());
    	calendarEvent.setTimeZone(serviceEvent.getTimeZone());
    	calendarEvent.setCalendarEventId(serviceEvent.getCalendarEventId());
    	calendarEvent.setCalendarEventDate(serviceEvent.getCalendarEventDate());
    	calendarEvent.setCalendarSeriesId(serviceEvent.getCalendarEventSeriesId());
    	calendarEvent.setCreatedBy(serviceEvent.getCreatedBy());
		if(serviceEvent.getCreateUserID() != null){
			calendarEvent.setCreateUser(serviceEvent.getCreateUserID());
		}
		if(serviceEvent.getCreateTimestamp() != null){
			calendarEvent.setCreateDate(new Date(serviceEvent.getCreateTimestamp().getTime()));
		}
		if(serviceEvent.getUpdateUserID() != null){
			calendarEvent.setUpdateUser(serviceEvent.getUpdateUserID());
		}
		if(serviceEvent.getUpdateTimestamp() != null){
			calendarEvent.setUpdateDate(new Date(serviceEvent.getUpdateTimestamp().getTime()));
		}
		if(serviceEvent.getCreateJIMS2UserID() != null){
			calendarEvent.setCreateJims2User(serviceEvent.getCreateJIMS2UserID());
		}
		if(serviceEvent.getUpdateJIMS2UserID() != null){
			calendarEvent.setUpdateJims2User(serviceEvent.getUpdateJIMS2UserID());
		}
    	
    	return calendarEvent;
    	
    }

}
