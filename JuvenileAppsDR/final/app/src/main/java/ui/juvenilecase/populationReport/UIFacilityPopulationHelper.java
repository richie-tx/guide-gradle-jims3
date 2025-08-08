/*
 * Created on Oct 29, 2013
 *
 */
package ui.juvenilecase.populationReport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import messaging.codetable.GetJuvenileAdmitReasonsEvent;
import messaging.codetable.GetJuvenileFacilitiesEvent;
import messaging.codetable.criminal.reply.JuvenileAdmitReasonsResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.GetFacilityAdmitReasonPopEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetCustomFacilityAdmitReasonPopEvent;
import messaging.juvenilecase.GetFacilityCurrentObservationEvent;
import messaging.juvenilecase.GetFacilityCurrentPopEvent;
import messaging.juvenilecase.GetFacilityCurrentPopRptEvent;
import messaging.juvenilecase.GetFacilityPopTotalRptEvent;
import messaging.juvenilecase.GetJJSJuvenileEvent;
import messaging.juvenilecase.GetJuvenileDetentionFacilitiesByCodeEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.FacilityAdmitReasonResponseEvent;
import messaging.juvenilecase.reply.FacilityPopTotalRptResponseEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilitiesCurrPopResponseEvent;
import messaging.juvenilecase.reply.SearchResultsCountResponseEvent;
import messaging.populationReport.reply.FacilityPopulationTotalsResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import org.apache.commons.lang.StringUtils;

import ui.juvenilecase.populationReport.form.JuvenilePopulationForm;

/**
 */
public class UIFacilityPopulationHelper {

	/**
	 * @param facilityId
	 */
	public static void getFacilityPopulationTotalsInfo(JuvenilePopulationForm  form) {
		
		TreeMap<String, JuvenilePopulationForm.FacilityTotal> secureFacility = initializeMap();
		TreeMap<String, JuvenilePopulationForm.FacilityTotal> nonSecureFacility = initializeMap();
		TreeMap<String, JuvenilePopulationForm.FacilityTotal> diversionFacility = initializeMap();
		TreeMap<String, JuvenilePopulationForm.FacilityTotal> tempRelease = initializeMap();
		List<FacilityPopTotalRptResponseEvent> facilityTotalInfos = getFacilityTotalInfos(form.getFacilityId());
		Collection juveniles = getJuvenileFacilities(form.getFacilityId(), form.getSearchTypeId());
		
		
		int totalSecureMalecount	= 0;
		int totalFemaleSecureCount	= 0;
		int totalNonSecMaleCount	= 0;
		int totalNonSecFemaleCount	= 0;
		int totalDivMaleCount		= 0;
		int totalDivFemaleCount		= 0;
		int totalTempMaleCount		= 0;
		int totalTempFemaleCount	= 0;
		
		double highHighCount 		= 0;
		double highModerateCount 	= 0;
		double highLowCount 		= 0;
		
		double moderateHighCount 	= 0;
		double moderateModerateCount	= 0;
		double moderateLowCount 	= 0;
		
		double lowHighCount 		= 0;
		double lowModerateCount 	= 0;
		double lowLowCount 		= 0;
		double missingScoreCount 	= 0;
		
		double totalCount		= 0;
		
		double highHighPercent 		= 0.00;
		double highModeratePercent 	= 0.00;
		double highLowPercent		= 0.00;
		    
		double moderateHighPercent 	= 0.00;
		double moderateModeratePercent	= 0.00;
		double moderateLowPercent	= 0.00;
		    
		double lowHighPercent		= 0.00;
		double lowModeratePercent	= 0.00;
		double lowLowPercent		= 0.00;
		    
		double missingScorePercent	= 0.00;
		
		if ( facilityTotalInfos != null 
			&& facilityTotalInfos.size() > 0 ) {
		    totalCount = facilityTotalInfos.size();
		    for ( FacilityPopTotalRptResponseEvent response : facilityTotalInfos ) {
			String risk = nvl(response.getRisk());
			String need = nvl(response.getNeed());
			if ( risk.equals("High")
				&& need.equals("High") ) {
			    highHighCount++;
			} else if (risk.equals("High")
					&& need.equals("Moderate") ) {
			    highModerateCount++;
			} else if (risk.equals("High")
					&& need.equals("Low") ){
			    highLowCount++;
			} else if (risk.equals("Moderate")
					&& need.equals("High") ){
			    moderateHighCount++;
			} else if (risk.equals("Moderate")
					&& need.equals("Moderate") ){
			    moderateModerateCount++;
			} else if (risk.equals("Moderate")
					&& need.equals("Low") ){
			    moderateLowCount++;
			} else if (risk.equals("Low")
					&& need.equals("High") ) {
			    lowHighCount++;
			} else if (risk.equals("Low")
					&& need.equals("Moderate") ) {
			    lowModerateCount++;
			} else if (risk.equals("Low")
					&& need.equals("Low") ){
			    lowLowCount++;
			} else {
			    missingScoreCount++;
			}
		    }
		    
		    
		    highHighPercent 			= calculatePercentage(highHighCount,totalCount);
		    highModeratePercent			= calculatePercentage(highModerateCount, totalCount);
		    highLowPercent			= calculatePercentage(highLowCount, totalCount);
		    
		    moderateHighPercent 		= calculatePercentage(moderateHighCount,totalCount);
		    moderateModeratePercent		= calculatePercentage(moderateModerateCount,totalCount);
		    moderateLowPercent			= calculatePercentage(moderateLowCount,totalCount);
		    
		    lowHighPercent			= calculatePercentage(lowHighCount,totalCount);
		    lowModeratePercent			= calculatePercentage(lowModerateCount,totalCount);
		    lowLowPercent			= calculatePercentage(lowLowCount,totalCount);
			    
		    missingScorePercent			= calculatePercentage(missingScoreCount,totalCount);
		    
		    form.setHighHighPercent(highHighPercent);
		    form.setHighModeratePercent(highModeratePercent);
		    form.setHighLowPercent(highLowPercent);
		    
		    form.setModerateHighPercent(moderateHighPercent);
		    form.setModerateModeratePercent(moderateModeratePercent);
		    form.setModerateLowPercent(moderateLowPercent);
		    
		    form.setLowHighPercent(lowHighPercent);
		    form.setLowModeratePercent(lowModeratePercent);
		    form.setLowLowPercent(lowLowPercent);
		    
		    form.setMissingScorePercent(missingScorePercent);
		    
		   
		} else {
		    form.setHighHighPercent(0.00);
		    form.setHighModeratePercent(0.00);
		    form.setHighLowPercent(0.00);
		    
		    form.setModerateHighPercent(0.00);
		    form.setModerateModeratePercent(0.00);
		    form.setModerateLowPercent(0.00);
		    
		    form.setLowHighPercent(0.00);
		    form.setLowModeratePercent(0.00);
		    form.setLowLowPercent(0.00);
		    
		    form.setMissingScorePercent(0.00);
		}
		
		List<JJSJuvenileResponseEvent> masterJuveniles = getJuveniles("T", form.getFacilityId());
		
		
		
		
		for ( JJSJuvenileResponseEvent juvenile :  masterJuveniles ){
		    String raceId = juvenile.getRaceId();
		    String sex = juvenile.getSexId();
		
		    if ( "M".equals(sex) ){
			tempRelease.put(raceId,new JuvenilePopulationForm.
								FacilityTotal(raceId, 
										tempRelease.get(raceId).getMaleCount()+1,
										tempRelease.get(raceId).getFemaleCount()));
		    } else if ("F".equals(sex)) {
			tempRelease.put(raceId,new JuvenilePopulationForm.
								FacilityTotal(raceId, 
										tempRelease.get(raceId).getMaleCount(),
										tempRelease.get(raceId).getFemaleCount()+1));
		    }
		    
		    
		}
		
		
		//Collection juveniles = new ArrayList();
		Iterator iter = juveniles.iterator();
	
		while(iter.hasNext())
		{
			JuvenileDetentionFacilitiesResponseEvent resp = (JuvenileDetentionFacilitiesResponseEvent)iter.next();
			
			if(resp.getSecureStatus()!=null || !resp.getSecureStatus().equals(""))
			{
				if(resp.getSecureStatus().equalsIgnoreCase("s"))
				{
					if(resp.getjuvSex()!=null || !resp.getjuvSex().equals(""))
					{
					        String raceCode = resp.getJuvRace();
					        if(StringUtils.isNotEmpty(raceCode) || raceCode != null){
					           
					            if(secureFacility.get( raceCode )!=null )
							{
								if(resp.getjuvSex().equalsIgnoreCase("m"))
									secureFacility.put(raceCode,
											new JuvenilePopulationForm.FacilityTotal(raceCode, 
													secureFacility.get(raceCode).getMaleCount()+1,secureFacility.get(raceCode).getFemaleCount()));
								else
									secureFacility.put(raceCode,
											new JuvenilePopulationForm.FacilityTotal(raceCode, 
													secureFacility.get(raceCode).getMaleCount(),secureFacility.get(raceCode).getFemaleCount()+1));
							}
					        }						
					}
					
				}
				else if(resp.getSecureStatus().equalsIgnoreCase("n"))
				{
					if(resp.getjuvSex()!=null || !resp.getjuvSex().equals(""))
					{
						if(nonSecureFacility.get(resp.getJuvRace())!=null)
						{
							if(resp.getjuvSex().equalsIgnoreCase("m"))
								nonSecureFacility.put(resp.getJuvRace(),
										new JuvenilePopulationForm.FacilityTotal(resp.getJuvRace(), 
												nonSecureFacility.get(resp.getJuvRace()).getMaleCount()+1,nonSecureFacility.get(resp.getJuvRace()).getFemaleCount()));
							else
								nonSecureFacility.put(resp.getJuvRace(),
										new JuvenilePopulationForm.FacilityTotal(resp.getJuvRace(), 
												nonSecureFacility.get(resp.getJuvRace()).getMaleCount(),nonSecureFacility.get(resp.getJuvRace()).getFemaleCount()+1));
						}
					}
				
				}
				else if(resp.getSecureStatus().equalsIgnoreCase("v"))
				{
					if(resp.getjuvSex()!=null || !resp.getjuvSex().equals(""))
					{
						if(diversionFacility.get(resp.getJuvRace())!=null)
						{
							if(resp.getjuvSex().equalsIgnoreCase("m"))
								diversionFacility.put(resp.getJuvRace(),
										new JuvenilePopulationForm.FacilityTotal(resp.getJuvRace(), 
												diversionFacility.get(resp.getJuvRace()).getMaleCount()+1,diversionFacility.get(resp.getJuvRace()).getFemaleCount()));
							else
								diversionFacility.put(resp.getJuvRace(),
										new JuvenilePopulationForm.FacilityTotal(resp.getJuvRace(), 
												diversionFacility.get(resp.getJuvRace()).getMaleCount(),diversionFacility.get(resp.getJuvRace()).getFemaleCount()+1));
						}
					}
				
				}
			}
		}
		
		Iterator iter1 = secureFacility.entrySet().iterator();
		Iterator iter2 = nonSecureFacility.entrySet().iterator();
		Iterator iter3 = diversionFacility.entrySet().iterator();
		Iterator iter4 = tempRelease.entrySet().iterator();
		ArrayList facilityRecs = new ArrayList();
		FacilityPopulationTotalsResponseEvent resp = new FacilityPopulationTotalsResponseEvent();
	
		while(iter1.hasNext())
		{
			Map.Entry me1 = (Map.Entry)iter1.next();
			String race = (String)me1.getKey();
			resp.setRace(race);
			JuvenilePopulationForm.FacilityTotal pop = (JuvenilePopulationForm.FacilityTotal)me1.getValue();
			resp.setSecureMaleCount(pop.getMaleCount());
			totalSecureMalecount+=pop.getMaleCount();
			resp.setSecureFemaleCountCount(pop.getFemaleCount());
			totalFemaleSecureCount += pop.getFemaleCount();
			
			me1 = (Map.Entry)iter2.next();
			race = (String)me1.getKey();
			pop = (JuvenilePopulationForm.FacilityTotal)me1.getValue();
			resp.setNonSecureMaleCount(pop.getMaleCount());
			resp.setNonSecureFemaleCount(pop.getFemaleCount());
			totalNonSecMaleCount += pop.getMaleCount();
			totalNonSecFemaleCount += pop.getFemaleCount();
			
			me1 = (Map.Entry)iter3.next();
			race = (String)me1.getKey();
			pop = (JuvenilePopulationForm.FacilityTotal)me1.getValue();
			resp.setDiversionMaleCount(pop.getMaleCount());
			resp.setDiversionFemaleCount(pop.getFemaleCount());
			totalDivMaleCount += pop.getMaleCount();
			totalDivFemaleCount += pop.getFemaleCount();
			

			me1 = (Map.Entry)iter4.next();
			race = (String)me1.getKey();
			pop = (JuvenilePopulationForm.FacilityTotal)me1.getValue();
			resp.setTempReleaseMaleCount(pop.getMaleCount());
			resp.setTempReleaseFemaleCount(pop.getFemaleCount());
			totalTempMaleCount += pop.getMaleCount();
			totalTempFemaleCount += pop.getFemaleCount();
			
			facilityRecs.add(resp);
			resp = new FacilityPopulationTotalsResponseEvent();
		}	
		form.setTotalSecureMalecount(totalSecureMalecount);
		form.setTotalFemaleSecureCount(totalFemaleSecureCount);
		form.setTotalNonSecMaleCount(totalNonSecMaleCount);
		form.setTotalNonSecFemaleCount(totalNonSecFemaleCount);
		form.setTotalDivMaleCount(totalDivMaleCount);
		form.setTotalDivFemaleCount(totalDivFemaleCount);
		form.setTotalTempReleaseMaleCount(totalTempMaleCount);
		form.setTotalTempReleaseFemaleCount(totalTempFemaleCount);
		form.setTotalSecureInmates(totalSecureMalecount + totalFemaleSecureCount);
		form.setTotalNonSecInmates(totalNonSecMaleCount + totalNonSecFemaleCount);
		form.setTotalDivInmates(totalDivMaleCount + totalDivFemaleCount);
		form.setTotalTempReleaseInmates(totalTempMaleCount + totalTempFemaleCount);
		form.setFacilityPopTots(facilityRecs);
		
	}
	private static Collection getJuvenileFacilities(String facilityId, String searchType)
	{
		GetJuvenileDetentionFacilitiesByCodeEvent evt =(GetJuvenileDetentionFacilitiesByCodeEvent)EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILEDETENTIONFACILITIESBYCODE);		
		evt.setFacilityCode(facilityId);
		evt.setSearchType(searchType);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(evt);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		Collection juveniles = MessageUtil.compositeToCollection(compositeResponse,JuvenileDetentionFacilitiesResponseEvent.class);	
		return juveniles;
	}
	
	private static List<FacilityPopTotalRptResponseEvent> getFacilityTotalInfos(String facilityId)
	{	
	    	List<FacilityPopTotalRptResponseEvent> facilityTotalInfos = null;
	    	GetFacilityPopTotalRptEvent evt =(GetFacilityPopTotalRptEvent)EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETFACILITYPOPTOTALRPT);		
		evt.setFacilityCode(facilityId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(evt);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		facilityTotalInfos = MessageUtil.compositeToList(compositeResponse,FacilityPopTotalRptResponseEvent.class);	
		return facilityTotalInfos;
	}
	private static List<JJSJuvenileResponseEvent>  getJuveniles(String detentionStatus,
									String detentionFacilityId)
	{

		/**
		 * Search for jcjuvenile.
		 */
	    	List<JJSJuvenileResponseEvent>fileredJuveniles = new ArrayList<JJSJuvenileResponseEvent>();
		GetJJSJuvenileEvent request = (GetJJSJuvenileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSJUVENILE);
		request.setDetentionStatusId(detentionStatus);

		CompositeResponse response =  MessageUtil.postRequest(request);
		List<JJSJuvenileResponseEvent> juveniles = MessageUtil.compositeToList(response, JJSJuvenileResponseEvent.class );
		for ( JJSJuvenileResponseEvent juvenile : juveniles ){
		    if ( juvenile.getDetentionFacilityId().equals(detentionFacilityId)){
			fileredJuveniles.add(juvenile);
		    }
		}
		return fileredJuveniles;
	}
	
	
	private static TreeMap initializeMap()
	{
		TreeMap<String, JuvenilePopulationForm.FacilityTotal> facility = new TreeMap<String, JuvenilePopulationForm.FacilityTotal>();
		String[] raceCodes = {"A","B","L","O","W","X"};
		for(int i=0; i<6; i++)
		{
			facility.put(raceCodes[i], new JuvenilePopulationForm.FacilityTotal(raceCodes[i],0,0));
		}
		return facility;
	}

	/**
	 * @param facilityId
	 * 
	 */
	public static void getFacilityStatusInfo(JuvenilePopulationForm  form)  {

		Collection juveniles = getJuvenileFacilities(form.getFacilityId(), form.getSearchTypeId());
		Iterator iter = juveniles.iterator();
		List residents = new ArrayList();
		List residentsWithOtherChanges = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date currDate = new Date();
		String today = sdf.format(currDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtil.getCurrentDate());
		cal.add(Calendar.DAY_OF_YEAR,-1);
		String dayBefore = sdf.format(cal.getTime());	
	
		while(iter.hasNext())
		{
			JuvenileDetentionFacilitiesResponseEvent resp = (JuvenileDetentionFacilitiesResponseEvent)iter.next();
			/*String admit = new StringBuffer(resp.getAdmitTime()).insert(2,":").toString();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			
				try {
					 admitTime = sdf.parse(admit);
					 checkTime = sdf.parse("06:01");
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			
			String returnDate=null;
			if(resp.getReturnDate()!=null)
				returnDate=sdf.format(resp.getReturnDate());
			String lastChangeDate=null;
			if(resp.getLastChangeDate()!= null)
				lastChangeDate = sdf.format(resp.getLastChangeDate());
			String admitDate=null;
			if(resp.getAdmitDate()!= null)
				admitDate = sdf.format(resp.getAdmitDate());
			String releaseDate = null;
			if(resp.getReleaseDate()!=null)
				releaseDate = sdf.format(resp.getReleaseDate());
		
			if((lastChangeDate!=null && (lastChangeDate.equals(today) || lastChangeDate.equals(dayBefore)))
					&& (admitDate.equals(today) || admitDate.equals(dayBefore))
					&& (resp.isLocationChangeFlag() || resp.isReasonChangeFlag()||resp.isSecureIndicatorChangeFlag() || resp.isOtherChangeFlag()) )
				
				residentsWithOtherChanges.add(resp);
			
			residents.add(resp);
			
			//totResidents++;
			
				
		/*	if(resp.getFacilityStatus() != null)
				{
						//new admissions
						if((resp.getFacilityStatus().equalsIgnoreCase("V") || resp.getFacilityStatus().equalsIgnoreCase("R"))
						&& (resp.getAdmitDate()!=null && (admitDate.equals(dayBefore) || admitDate.equals(today)))
						)
						
						{
							residents.add(resp);
							totResidents++;
						}
						//releases
						if(resp.getFacilityStatus().equalsIgnoreCase("E") 
							|| resp.getFacilityStatus().equalsIgnoreCase("N") ||  resp.getFacilityStatus().equalsIgnoreCase("T"))				
							
						{
							residents.add(resp);
							totResidents++;
						}
						//return
						if((resp.getFacilityStatus().equalsIgnoreCase("D") || resp.getFacilityStatus().equalsIgnoreCase("P") )
								&& 
								((resp.getReturnReason()!=null && (returnDate!=null && (returnDate.equals(today) || returnDate.equals(dayBefore)))) 
								|| (resp.getAdmitDate()!=null && (admitDate.equals(today) || admitDate.equals(dayBefore))))
							)
						{
							residents.add(resp);
							totResidents++;
						}
					
						//check for other changes
						if((lastChangeDate!=null && (lastChangeDate.equals(today) || lastChangeDate.equals(dayBefore)))
								&& (admitDate.equals(today) || admitDate.equals(dayBefore))
								&& (resp.isLocationChangeFlag() || resp.isReasonChangeFlag()||resp.isSecureIndicatorChangeFlag()) || resp.isOtherChangeFlag())
							
						{				
							residentsWithOtherChanges.add(resp);
							//totResidents++;  already been added to the total
						}
						
					
					//returns
					//else if(resp.getFacilityStatus().equalsIgnoreCase("D") && resp.getr)
				}
				//hard release
				else if(resp.getReleaseDate()!=null && (releaseDate.equals(today) || releaseDate.equals(dayBefore)))
				{
					residents.add(resp);
					totResidents++;
				}*/
				
		
		
				
		//String juvNum = resp.get
		}
		form.setResidentsStatusList(residents);
		form.setResidentWithOtherStatusChanges(residentsWithOtherChanges);
		form.setListTotal(juveniles.size());
		
	}
	
	/**
	 * @param facilityId
	 */
	public static void getFacilityCurrentPopulationInfo(JuvenilePopulationForm  form) {
		
		GetFacilityCurrentPopEvent evt = (GetFacilityCurrentPopEvent) EventFactory
        .getInstance(JuvenileCaseControllerServiceNames.GETFACILITYCURRENTPOP);
		
		evt.setFacilityCode(form.getFacilityId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(evt);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		List juveniles = MessageUtil.compositeToList(compositeResponse,JuvenileFacilitiesCurrPopResponseEvent.class);
		SearchResultsCountResponseEvent countResp = 
				(SearchResultsCountResponseEvent) MessageUtil.filterComposite(compositeResponse, SearchResultsCountResponseEvent.class);
	
		if(countResp != null)
			form.setListTotal(countResp.getNumberOfResults());
		//setting the list in the form
		form.setCurrentPopulations(juveniles);
		
		}
	
	/**
	 * new Population Query
	 * @param facilityId
	 */
	public static void getFacilityCurrentPopulationReport(JuvenilePopulationForm  form) {
		
		GetFacilityCurrentPopRptEvent evt = (GetFacilityCurrentPopRptEvent) EventFactory
				.getInstance(JuvenileCaseControllerServiceNames.GETFACILITYCURRENTPOPRPT);
		
		evt.setFacilityCode(form.getFacilityId());
		//get risk and need level
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(evt);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		List juveniles = MessageUtil.compositeToList(compositeResponse,JuvenileFacilitiesCurrPopResponseEvent.class);
		SearchResultsCountResponseEvent countResp = 
				(SearchResultsCountResponseEvent) MessageUtil.filterComposite(compositeResponse, SearchResultsCountResponseEvent.class);
	
		if(countResp != null)
			form.setListTotal(countResp.getNumberOfResults());
		//setting the list in the form
		form.setCurrentPopulations(juveniles);
		
		}
	
	/**
	 * new Population Query
	 * @param facilityId
	 */
	public static void getCustomFacilityPopulationReport(JuvenilePopulationForm  form) {
		
	    GetCustomFacilityAdmitReasonPopEvent event = (GetCustomFacilityAdmitReasonPopEvent) EventFactory
			.getInstance(JuvenileCaseControllerServiceNames.GETCUSTOMFACILITYADMITREASONPOP);
	    
	    event.setFacilityId(form.getFacilityId());
	    event.setSecuredFacility(form.getSecuredFacility());
	    //event.setAdmitReasonId(form.getAdmitReasonId());
	    if ( form.getAdmitReasonIds() != null
		    && form.getAdmitReasonIds().length > 0 ){
	    	event.setAdmitReasonIds(form.getAdmitReasonIds());
	    }
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		
		List juvFacilities = MessageUtil.compositeToList(compositeResponse,FacilityAdmitReasonResponseEvent.class);
		
		form.setJuvenileAdmits(juvFacilities);
		form.setListTotal(juvFacilities.size());

		
	}
	
	/**
	 * @param facilityId
	 */
	public static void getFacilityCurrentObservation(JuvenilePopulationForm  form) {
		
		GetFacilityCurrentObservationEvent evt = (GetFacilityCurrentObservationEvent) EventFactory
				.getInstance(JuvenileCaseControllerServiceNames.GETFACILITYCURRENTOBSERVATION);
		
		evt.setFacilityCode(form.getFacilityId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(evt);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);

		List juveniles = MessageUtil.compositeToList(compositeResponse,JuvenileFacilitiesCurrPopResponseEvent.class);
		SearchResultsCountResponseEvent countResp = 
				(SearchResultsCountResponseEvent) MessageUtil.filterComposite(compositeResponse, SearchResultsCountResponseEvent.class);
		
		if(countResp != null)
			form.setListTotal(countResp.getNumberOfResults());
		//setting the list in the form
		form.setCurrentPopulations(juveniles);
		
		}

	
	private static void getOfficerUVCode(JuvenileFacilitiesCurrPopResponseEvent resp)
	{
		//check for booking supervision number
		//JuvenileDetentionFacilitiesResponseEvent facResp = JuvenileFacilityHelper.getInFacilityDetails(resp.getJuvenileNum());
		SearchJuvenileCasefilesEvent searchForCasefiles = (SearchJuvenileCasefilesEvent) EventFactory
				.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);
		if(resp.getBookingSupervisionNum()!=null && !resp.getBookingSupervisionNum().equalsIgnoreCase(""))
		{
			searchForCasefiles.setSearchType("SNUM");
			searchForCasefiles.setSupervisionNum(resp.getBookingSupervisionNum());			
		}
		else
		{	
			searchForCasefiles.setSearchType("JNUM");
			searchForCasefiles.setJuvenileNum(resp.getJuvenileNum());
		}
		
		CompositeResponse response = MessageUtil.postRequest(searchForCasefiles);
			
		ErrorResponseEvent error = 
				(ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
		if (error == null) {
			Date checkDate= null;
			JuvenileCasefileSearchResponseEvent recentCasefile = new JuvenileCasefileSearchResponseEvent();
			List<JuvenileCasefileSearchResponseEvent> casefiles = MessageUtil.compositeToList(response, JuvenileCasefileSearchResponseEvent.class);
			if(casefiles.size()>0)
			{
				Iterator casefileIter = casefiles.iterator();
				if(casefiles.size()==1)
				{
					JuvenileCasefileSearchResponseEvent casefileResp = (JuvenileCasefileSearchResponseEvent) casefileIter.next();
					checkDate=casefileResp.getActivationDate();
					recentCasefile=casefileResp;
				}
				else
				{
					while(casefileIter.hasNext())
					{
						JuvenileCasefileSearchResponseEvent casefileResp = (JuvenileCasefileSearchResponseEvent) casefileIter.next();
						if(casefileResp.getCaseStatus()!=null && casefileResp.getCaseStatus().equalsIgnoreCase("ACTIVE"))
						{
							if((checkDate==null ||  (casefileResp.getActivationDate()!=null && casefileResp.getActivationDate().after(checkDate))) && casefileResp.getSupervisionCategory().equalsIgnoreCase("AR"))
							{
								checkDate=casefileResp.getActivationDate();
								recentCasefile=casefileResp;
							}	
						}
					}
				}
			}
			if(checkDate!=null)
			{
				resp.setOfficerUVCode(recentCasefile.getOfficerLoginId());
				resp.setOfficerFullName(recentCasefile.getOfficerFullName());
			}
		}
			
	}
	/**
	 * @param facilityId
	 * @param secureInd
	 * @param admitReasonId
	 */
public static void getFacilityReasonPopulationInfo(JuvenilePopulationForm  form) {

		
		
		GetFacilityAdmitReasonPopEvent event = (GetFacilityAdmitReasonPopEvent) EventFactory
         .getInstance(JuvenileControllerServiceNames.GETFACILITYADMITREASONPOP);
		
		
		event.setFacilityId(form.getFacilityId());
		event.setSecuredFacility(form.getSecuredFacility());
		event.setAdmitReasonId(form.getAdmitReasonId());		
		CompositeResponse compositeResponse = (CompositeResponse)MessageUtil.postRequest(event);	
		Collection juvFacilities = MessageUtil.compositeToCollection(compositeResponse,FacilityAdmitReasonResponseEvent.class);
		Iterator iter = juvFacilities.iterator();
		ArrayList facilityAdmitReasonRecs = new ArrayList();
		int reasonRecsCount = 0;
		while(iter.hasNext())
		{
			FacilityAdmitReasonResponseEvent resp = (FacilityAdmitReasonResponseEvent)iter.next();
			//for each juvenile set the date of birth - US 14463
			resp.setDob(getJuvDOB(resp.getJuvenileNum()));
			facilityAdmitReasonRecs.add(resp);
			reasonRecsCount++;
		}		
		form.setJuvenileAdmits(facilityAdmitReasonRecs);
		form.setListTotal(reasonRecsCount);	
	}
	

	private static Date getJuvDOB(String juvNum)
	{
		GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory
				.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

		reqProfileMain.setJuvenileNum(juvNum);
		CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
		JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil
				.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
		if(juvProfileRE!=null && juvProfileRE.getDateOfBirth()!=null)
			return juvProfileRE.getDateOfBirth();
		
		return null;
	}
	public static String getCodeDescription(Collection codes, String facilityCode)
	{
		Iterator iter = codes.iterator();
		while(iter.hasNext())
		{
			JuvenileFacilityResponseEvent resp = (JuvenileFacilityResponseEvent)iter.next();
			if(resp.getCode().equals(facilityCode))
				return resp.getDescription();
		}
		return null;
	}
	// just for UI development testing	
	public static Collection loadFacilites()
	{		
		GetJuvenileFacilitiesEvent facilityEvent = new GetJuvenileFacilitiesEvent();      
        CompositeResponse response = MessageUtil.postRequest(facilityEvent);
        List facilityColl = MessageUtil.compositeToList(response, JuvenileFacilityResponseEvent.class);	 
        List activeFacilities = new ArrayList();
        //go through the list and remove inactive facilities
        Iterator iter = facilityColl.iterator();
	    while(iter.hasNext())
	    {
	    	JuvenileFacilityResponseEvent resp = (JuvenileFacilityResponseEvent)iter.next();
	    	if(!"Y".equalsIgnoreCase( resp.getInactiveInd() ))
	    	{
	    		resp.setDescriptionWithCode( resp.getDescription() + " (" +resp.getCode()+")");
	    		activeFacilities.add(resp);	    		
	    	}
	    }
	    Collections.sort(activeFacilities,JuvenileFacilityResponseEvent.CodeComparator );
	  
	    return activeFacilities;
	}

	public static Collection<JuvenileAdmitReasonsResponseEvent> loadAdmitReasons()
	{		
		  
		GetJuvenileAdmitReasonsEvent admitReasonsEvent = (GetJuvenileAdmitReasonsEvent) EventFactory
        .getInstance(CodeTableControllerServiceNames.GETJUVENILEADMITREASONS);		
		
        CompositeResponse response = MessageUtil.postRequest(admitReasonsEvent);
        List admitReasonsColl = MessageUtil.compositeToList(response, JuvenileAdmitReasonsResponseEvent.class);	 
        List activeAdmitReasons = new ArrayList();
        //go through the list and remove inactive admit reasons.
        Iterator iter = admitReasonsColl.iterator();
	    while(iter.hasNext())
	    {
	    	JuvenileAdmitReasonsResponseEvent resp = (JuvenileAdmitReasonsResponseEvent)iter.next();
	    	if( !"Y".equalsIgnoreCase(resp.getInactiveInd()) )
	    	{
	    		resp.setDescriptionWithCode( resp.getCode()+ " - " + resp.getDescription() );
	    		resp.setCodeWithDetType(resp.getCode() + "|" + resp.getDetentionType());
	    		activeAdmitReasons.add(resp);	    		
	    	}
	    }
	    Collections.sort(activeAdmitReasons,JuvenileAdmitReasonsResponseEvent.CodeComparator );
	  
	    return activeAdmitReasons;
	}
	
	public static String nvl(String value ) {
	    return ( (value != null && value.length() > 0 ) ? value : "" );
	}
	
	private static double calculatePercentage(double value, double total){
	    double percent = 0.00;
	    if ( total > 0 ){
		percent  =  (value*100)/total;
		percent  = (double) Math.round(percent*100)/100;
		return percent;
	    } else {
		return 0;
	    }
	}
}
