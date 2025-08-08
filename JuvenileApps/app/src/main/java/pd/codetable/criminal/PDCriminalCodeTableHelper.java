/*
 * Created on Feb 7, 2005
 */
package pd.codetable.criminal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import messaging.codetable.GetJuvenileAdmitReasonsEvent;
import messaging.codetable.GetJuvenileDSM5CodeEvent;
import messaging.codetable.GetJuvenileFacilitiesEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.codetable.GetNonDetentionJuvenileFacilitiesEvent;
import messaging.codetable.GetServiceDeliveryWithoutFeeCodeEvent;
import messaging.codetable.criminal.reply.JuvenileAdmitReasonsResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCourtResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDSM5CodesResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.codetable.criminal.reply.JuvenileHearingTypeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.codetable.criminal.reply.ServiceDeliveryWithoutFeeResponseEvent;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;

/**
 * @author dnikolis
 * Creates response events for JuvenileOffenseCode and JuvenileCourt entities.
 */
public final class PDCriminalCodeTableHelper
{
	/**
	 * Constructor
	 */
	private PDCriminalCodeTableHelper()
	{
		super();
	}

	/**
	 * Posts a JuvenileOffenseCodeResponseEvent from a JuvenileOffenseCode entity
	 * @param offenseCode
	 * @return JuvenileOffenseCodeResponseEvent
	 */
	public static JuvenileOffenseCodeResponseEvent getJuvenileOffenseCodeResponseEvent(JuvenileOffenseCode offenseCode)
	{
		JuvenileOffenseCodeResponseEvent offenseReply = new JuvenileOffenseCodeResponseEvent();
		offenseReply.setCategory(offenseCode.getCategory());
		offenseReply.setCitationCode(offenseCode.getCitationCode());
		offenseReply.setDpsOffenseCode(offenseCode.getDpsOffenseCode());
		offenseReply.setLongDescription(offenseCode.getLongDescription());
		offenseReply.setNumericCode(offenseCode.getNumericCode());
		offenseReply.setOffenseCode(offenseCode.getOffenseCode());
		offenseReply.setReportGroup(offenseCode.getReportGroup());
		offenseReply.setSeverity(offenseCode.getSeverity());
		offenseReply.setShortDescription(offenseCode.getShortDescription());
		offenseReply.setTopic(PDCodeTableConstants.OFFENSE_LISTITEM);
		offenseReply.setOffenseCategory(offenseCode.getOffenseCategory());
		offenseReply.setSeveritySubtype(offenseCode.getSeverityType());
		offenseReply.setDiscontCode(offenseCode.getDiscontCode());// U.S 58355
		return offenseReply;
	}

	/**
	 * Creates a JuvenileCourtResponseEvent object
	 * @param court
	 * @return JuvenileCourtResponseEvent object
	 */
	public static JuvenileCourtResponseEvent getJuvenileCourtResponseEvent(JuvenileCourt court)
	{
		JuvenileCourtResponseEvent event = new JuvenileCourtResponseEvent();

		event.setCode(court.getCode());
		event.setDescription(court.getDescription());
		event.setJudgeName(court.getJudgesName());
		event.setTopic(PDCodeTableConstants.JUVENILE_COURT);
		event.setRefereesCourtInd(court.getRefereesCourtInd());

		return event;
	}

	public static void sendJuvenileCourts(IDispatch dispatch)
	{
		IHome home = new Home();
		Iterator i = home.findAll(JuvenileCourt.class);

		while (i.hasNext())
		{
			JuvenileCourt court = (JuvenileCourt) i.next();
			JuvenileCourtResponseEvent event = PDCriminalCodeTableHelper.getJuvenileCourtResponseEvent(court);
			dispatch.postEvent(event);
		}
	}
	
	
	/**
	 * Creates a JuvenileCourtResponseEvent object
	 * @param court
	 * @return JuvenileCourtResponseEvent object
	 */
	public static JuvenileFacilityResponseEvent getJuvenileFacilityResponseEvent(JuvenileFacility facility)
	{
		JuvenileFacilityResponseEvent event = new JuvenileFacilityResponseEvent();

		event.setCode(facility.getCode());
		event.setDescription(facility.getDescription());
		event.setDepartment(facility.getDepartment());
		event.setJuvTJPCPlacementType(facility.getJuvTJPCPlacementType()); //added  for JIMS200077404
		event.setAddress(facility.getAddress());
		event.setCity(facility.getCity());
		event.setZip(facility.getZip());
		event.setInactiveInd(facility.getInactiveInd());
		//<KISHORE>JIMS200059759 : MJCW - Common App Section 4 phone and LOC are missing
		event.setFacilityPhone(facility.getFacilityPhone());
		event.setLevelOfCare(facility.getLevelOfCare() == null?"":facility.getLevelOfCare().getDescription());
		event.setTopic(PDCodeTableConstants.JUVENILE_FACILITY);
		event.setRefereeCourt(facility.getRefereeCourt());
		event.setLocationOneLabel(facility.getLocationOne());
		event.setLocationTwoLabel(facility.getLocationTwo());
		event.setLocationThreeLabel(facility.getLocationThree());
		event.setSecureStatus(facility.getSecureInd());
		event.setJuvPlacementType(facility.getJuvenilePlacementType()); // juvenile placement Type added for #US.26457
		//Bug 91948
		event.setRectype( facility.getRectype() );
		event.setAvgCostPerDay(facility.getAvgCostPerDay() );
		event.setServiceType( facility.getServiceType() );
		event.setVendor( facility.getVendor() );
		event.setTjjdFacilityId( facility.getTjjdFacilityId());
		event.setTjjdFundingSrc(facility.getTjjdFundingSrc());
		//added for user Story - common app #U.S 26457
		if( "CNT".equalsIgnoreCase(facility.getJuvenilePlacementType()) ){
			event.setContactPerson("Deputy of Residential Services");
			event.setFacilityPhone("713.222.4100");
		}else if( "PRI".equalsIgnoreCase(facility.getJuvenilePlacementType()) ){
			event.setContactPerson("Deputy of Intake / Court Services");
			event.setFacilityPhone("713.222.4100");
		}
				
		return event;
	}
	
	public static void sendJuvenileFacilities(IDispatch dispatch, GetJuvenileFacilitiesEvent event)
	{
		IHome home = new Home();
		Iterator<JuvenileFacility> i = home.findAll(event, JuvenileFacility.class);

		while (i.hasNext())
		{
			JuvenileFacility facility = (JuvenileFacility) i.next();
			JuvenileFacilityResponseEvent response = PDCriminalCodeTableHelper.getJuvenileFacilityResponseEvent(facility);
			dispatch.postEvent(response);
		}
	}
	
	/*
	 * 	Added for 12533 user story
	 */
	public static void getJuvenileFacilityByCode(IDispatch dispatch,String facilityId)
	{
		JuvenileFacility facility = JuvenileFacility.find("code",facilityId);
		JuvenileFacilityResponseEvent response = PDCriminalCodeTableHelper.getJuvenileFacilityResponseEvent(facility);
		dispatch.postEvent(response);
	}
	
	/**
	 * Creates a JuvenileCourtResponseEvent object
	 * @param court
	 * @return JuvenileCourtResponseEvent object
	 */
	public static JuvenileAdmitReasonsResponseEvent getJuvenileAdmitReasonsResponseEvent(JuvenileAdmitReasons admitReasons)
	{
		JuvenileAdmitReasonsResponseEvent event = new JuvenileAdmitReasonsResponseEvent();

		event.setCode(admitReasons.getCode());		
    	event.setDescription(admitReasons.getDescription());
		event.setDetentionType(admitReasons.getDetentionType());
		event.setGenDetHearingChain(admitReasons.getGenDetHearingChain());
		event.setProbCauseHearingDays(admitReasons.getProbCauseHearingDays());
		event.setInactiveInd(admitReasons.getInactiveInd());
		return event;
	}
	
	/**
	 * Creates a JuvenileCourtResponseEvent object
	 * @param court
	 * @return JuvenileCourtResponseEvent object
	 */
	public static ServiceDeliveryWithoutFeeResponseEvent getServiceDeliveryWithoutFeeResponseEvent(ServiceDeliveryWithoutFeeCode servicesDelivered)
	{
		ServiceDeliveryWithoutFeeResponseEvent event = new ServiceDeliveryWithoutFeeResponseEvent();
		if(servicesDelivered!=null && servicesDelivered.getCode()!=null && !servicesDelivered.getCode().isEmpty()){
			event.setCode(servicesDelivered.getCode());		
			String codeDescVal = servicesDelivered.getDescription();
	    	event.setDescription(codeDescVal);
			event.setCategory(servicesDelivered.getCategory());
			event.setInactiveInd(servicesDelivered.getInactiveInd());
			//added after carla's review comments #36144
			event.setDescriptionWithCode(servicesDelivered.getCode()+"-"+codeDescVal);
		}
		return event;
	}
	
	//added as a part of defect Defect JIMS200077430 to read the RCL  read program for DETENTION.REASON values
	public static void sendJuvenileAdmitReasons(IDispatch dispatch, GetJuvenileAdmitReasonsEvent event)
	{
		if(event.getCode()!=null){
			JuvenileAdmitReasons reason = JuvenileAdmitReasons.find("code",event.getCode());
			JuvenileAdmitReasonsResponseEvent response = PDCriminalCodeTableHelper.getJuvenileAdmitReasonsResponseEvent(reason);
			dispatch.postEvent(response);
		}
		else
		{
			IHome home = new Home();
			Iterator<JuvenileAdmitReasons> i = home.findAll(event, JuvenileAdmitReasons.class);
		
			while (i.hasNext())
			{
				JuvenileAdmitReasons admitReasons = (JuvenileAdmitReasons) i.next();
				JuvenileAdmitReasonsResponseEvent response = PDCriminalCodeTableHelper.getJuvenileAdmitReasonsResponseEvent(admitReasons);
				dispatch.postEvent(response);
			}
		}
	}
	
	/**
	 * sendServiceDeliveryWithoutFee
	 * @param dispatch
	 * @param event
	 */
	public static void sendServiceDeliveryWithoutFee(IDispatch dispatch, GetServiceDeliveryWithoutFeeCodeEvent event)
	{
		if(event.getCode()!=null){
			IHome home = new Home();
			ServiceDeliveryWithoutFeeCode servicesDelivered= (ServiceDeliveryWithoutFeeCode)home.find(event.getCode(), ServiceDeliveryWithoutFeeCode.class);
			ServiceDeliveryWithoutFeeResponseEvent response = PDCriminalCodeTableHelper.getServiceDeliveryWithoutFeeResponseEvent(servicesDelivered);
			dispatch.postEvent(response);
		}
		else
		{
			IHome home = new Home();
			Iterator<ServiceDeliveryWithoutFeeCode> serviceDeliveryWithoutCodes = home.findAll(event, ServiceDeliveryWithoutFeeCode.class);
		
			while (serviceDeliveryWithoutCodes.hasNext())
			{
				ServiceDeliveryWithoutFeeCode ServiceDeliveryWithoutFeeCode = (ServiceDeliveryWithoutFeeCode) serviceDeliveryWithoutCodes.next();
				ServiceDeliveryWithoutFeeResponseEvent response = PDCriminalCodeTableHelper.getServiceDeliveryWithoutFeeResponseEvent(ServiceDeliveryWithoutFeeCode);
				dispatch.postEvent(response);
			}
		}
		
	}
	
	/**
	 * sendNonDetentionJuvenileFacilities
	 * @param dispatch
	 * @param event
	 */
	public static void sendNonDetentionJuvenileFacilities(IDispatch dispatch, GetNonDetentionJuvenileFacilitiesEvent event)
	{
		String[] excludedFacilitiesTJPC = {"E", "H"}; //removed D for the bug #58612
		List<String> excludedFacilitiesTJPCList = new ArrayList<String>(Arrays.asList(excludedFacilitiesTJPC));
		IHome home = new Home();
		Iterator<JuvenileFacility> facilityItr = home.findAll(event, JuvenileFacility.class);
		while (facilityItr.hasNext())
		{
			JuvenileFacility facility = facilityItr.next();
			JuvenileFacilityResponseEvent response = PDCriminalCodeTableHelper.getJuvenileFacilityResponseEvent(facility);
			if(response!=null && !"Y".equalsIgnoreCase(response.getInactiveInd()) && !excludedFacilitiesTJPCList.contains(response.getJuvTJPCPlacementType())){
				dispatch.postEvent(response);
			}
		}
	}
	
	
	/**
	 * sendServiceDeliveryWithoutFee
	 * @param dispatch
	 * @param event
	 */
	public static void sendDSM5Code(IDispatch dispatch, GetJuvenileDSM5CodeEvent event)
	{
		if(event.getCode()!=null && !event.getCode().equals("")){
			IHome home = new Home();
			JuvenileDSM5Code dsm5Code= (JuvenileDSM5Code)home.find("code", event.getCode(), JuvenileDSM5Code.class);
			JuvenileDSM5CodesResponseEvent resp = new JuvenileDSM5CodesResponseEvent();
			//null check added for Bug #42504
			if(dsm5Code!=null)
			{
				resp.setCode(dsm5Code.getCode());
				resp.setDescription(dsm5Code.getDescription());
				resp.setStatus(dsm5Code.getStatus());
				resp.setTjjdId(dsm5Code.getTjjdDSMId());
			}
			dispatch.postEvent(resp);
		}
	/*	else
		{
			IHome home = new Home();
			Iterator<JuvenileDSM5Code> dsm5Codes = home.findAll(event, JuvenileDSM5Code.class);
			JuvenileDSM5CodesResponseEvent resp = new JuvenileDSM5CodesResponseEvent();
			while (dsm5Codes.hasNext())
			{
				JuvenileDSM5Code dsm5Code = (JuvenileDSM5Code) dsm5Codes.next();
				resp = new JuvenileDSM5CodesResponseEvent();
				resp.setCode(dsm5Code.getCode());
				resp.setDescription(dsm5Code.getDescription());
				dispatch.postEvent(resp);
			}
		}*/
	}
	
	//added for district courts implementation
	/**
	 * loadActiveHearingTypes
	 * @param 
	 * @param newAdmit
	 * @return Collection<JuvenileHearingTypeResponseEvent>
	 */
	public static Collection<JuvenileHearingTypeResponseEvent> loadActiveHearingTypes()
	{		
	    GetJuvenileHearingTypesEvent juvHearingTypesEvent = new GetJuvenileHearingTypesEvent();      
	    CompositeResponse response = MessageUtil.postRequest(juvHearingTypesEvent);
	    List<JuvenileHearingTypeResponseEvent> hearingTypes = MessageUtil.compositeToList(response, JuvenileHearingTypeResponseEvent.class);	
	    Collections.sort(hearingTypes,JuvenileHearingTypeResponseEvent.CodeComparator );
	    return hearingTypes;
	}
	
    /**
     * NM added for US 157620 loadHearingTypesWithCatIndABonlyActive
     * 
     * @return
     */
    public static Collection<JuvenileHearingTypeResponseEvent> loadHearingTypesonlyActive()
    {
	GetJuvenileHearingTypesEvent juvHearingTypesEvent = new GetJuvenileHearingTypesEvent();
	CompositeResponse response = MessageUtil.postRequest(juvHearingTypesEvent);
	List<JuvenileHearingTypeResponseEvent> hearingTypes = MessageUtil.compositeToList(response, JuvenileHearingTypeResponseEvent.class);
	List<JuvenileHearingTypeResponseEvent> hearingTypesCatABactive = new ArrayList<JuvenileHearingTypeResponseEvent>();
	for (JuvenileHearingTypeResponseEvent hearingType : hearingTypes)
	{
	    if (hearingType != null && hearingType.getInactiveInd() != null && !hearingType.getInactiveInd().equalsIgnoreCase("Y"))
	    {
		hearingTypesCatABactive.add(hearingType);
	    }
	}
	Collections.sort(hearingTypesCatABactive, JuvenileHearingTypeResponseEvent.CodeComparator);
	return hearingTypesCatABactive;
    }
	
	/**added for us 58302, District Courts Implementation
	 * loadActiveHearingTypesWithCatIndAB
	 * @param 
	 * @param newAdmit
	 * @return Collection<JuvenileHearingTypeResponseEvent>
	 */
	public static Collection<JuvenileHearingTypeResponseEvent> loadActiveHearingTypesWithCatIndAB()
	{		
	    GetJuvenileHearingTypesEvent juvHearingTypesEvent = new GetJuvenileHearingTypesEvent();      
	    CompositeResponse response = MessageUtil.postRequest(juvHearingTypesEvent);
	    List<JuvenileHearingTypeResponseEvent> hearingTypes = MessageUtil.compositeToList(response, JuvenileHearingTypeResponseEvent.class);
	    List<JuvenileHearingTypeResponseEvent> hearingTypesCatAB = new ArrayList<JuvenileHearingTypeResponseEvent>();
	    for (JuvenileHearingTypeResponseEvent hearingType: hearingTypes){
	    	if (hearingType.getCategoryInd().equalsIgnoreCase("A")|| hearingType.getCategoryInd().equalsIgnoreCase("B")){
	    		hearingTypesCatAB.add(hearingType);
	    	}
	    }
	    Collections.sort(hearingTypesCatAB,JuvenileHearingTypeResponseEvent.CodeComparator );
	    return hearingTypesCatAB;
	}
	
	
    /**
     * added for US 157620 loadHearingTypesWithCatIndABonlyActive
     * 
     * @return
     */
    public static Collection<JuvenileHearingTypeResponseEvent> loadHearingTypesWithCatIndABonlyActive()
    {
	GetJuvenileHearingTypesEvent juvHearingTypesEvent = new GetJuvenileHearingTypesEvent();
	CompositeResponse response = MessageUtil.postRequest(juvHearingTypesEvent);
	List<JuvenileHearingTypeResponseEvent> hearingTypes = MessageUtil.compositeToList(response, JuvenileHearingTypeResponseEvent.class);
	List<JuvenileHearingTypeResponseEvent> hearingTypesCatABactive = new ArrayList<JuvenileHearingTypeResponseEvent>();
	for (JuvenileHearingTypeResponseEvent hearingType : hearingTypes)
	{
	    if (hearingType != null && hearingType.getInactiveInd() != null && !hearingType.getInactiveInd().equalsIgnoreCase("Y"))
	    {
		if (hearingType.getCategoryInd().equalsIgnoreCase("A") || hearingType.getCategoryInd().equalsIgnoreCase("B"))
		{
		    hearingTypesCatABactive.add(hearingType);
		}
	    }
	}
	Collections.sort(hearingTypesCatABactive, JuvenileHearingTypeResponseEvent.CodeComparator);
	return hearingTypesCatABactive;
    }
	
	/**
	 * Added sorting for 84105. Should be ok everywhere.
	 * loadActiveHolidays
	 * @param isHasPostAdjCatCasefile
	 * @param newAdmit
	 * @return Collection<JuvenileFacilityResponseEvent>
	 */
    public static ArrayList<JuvenileDates> loadActiveHolidays()
    {
	ArrayList<JuvenileDates> juvenileDatesList = new ArrayList<JuvenileDates>();
	Iterator<JuvenileDates> juvenileDatesItr = JuvenileDates.findAll();

	while (juvenileDatesItr.hasNext())
	{
	    JuvenileDates date = juvenileDatesItr.next();
	    if (!"Y".equalsIgnoreCase(date.getInactiveInd()))
	    {
		juvenileDatesList.add(date);
	    }
	}
	
	// Code: date most recent on top.
	Collections.sort((List<JuvenileDates>) juvenileDatesList, Collections.reverseOrder(new Comparator<JuvenileDates>() {
	    @Override
	    public int compare(JuvenileDates evt1, JuvenileDates evt2)
	    {
		if (evt1.getCode() != null && evt2.getCode() != null)
		    return Integer.valueOf(evt1.getCode()).compareTo(Integer.valueOf(evt2.getCode()));
		else
		    return -1;
	    }
	}));
	return juvenileDatesList;
    }
}
