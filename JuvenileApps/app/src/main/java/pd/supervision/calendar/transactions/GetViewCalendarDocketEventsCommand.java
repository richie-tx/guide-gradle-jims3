package pd.supervision.calendar.transactions;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang.StringUtils;

import messaging.calendar.GetViewCalendarDocketEventsEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByDateRangeEvent;
import messaging.districtCourtHearings.GetJJSCourtByDateRangeEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDConstants;
import naming.UIConstants;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileEventTypeCode;
import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenile.Juvenile;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;

//TODO Remove getOfficerAssociatedJuveniles method from this class, search by JPO is done in another class now
public class GetViewCalendarDocketEventsCommand implements ICommand
{
	/**
	 * @roseuid 45F1B0D902AD
	 */
	public GetViewCalendarDocketEventsCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 45F080FA035F
	 */
	public void execute(IEvent event)
	{
		GetViewCalendarDocketEventsEvent searchDocketEvent = (GetViewCalendarDocketEventsEvent) event;

		String juvenileId = null ;
		List<Juvenile> juvenileProfiles = null; // hot fix : 83275
		ErrorResponseEvent errorResp = new ErrorResponseEvent();

		if (searchDocketEvent.getJuvenileNum() != null && 
				(searchDocketEvent.getJuvenileNum().length() > 0))
		{
			juvenileId =searchDocketEvent.getJuvenileNum(); // this.getFormattedJuvenileId(searchDocketEvent.getJuvenileNum()); //81390
		}
		else
		{
			//hot fix : 83275
			juvenileProfiles = this.getAllJuveniles(searchDocketEvent, errorResp);
		}

		//If no errors occur from above logic, get criteria from searchDocketEvent
		if (errorResp.getMessage() != null)
		{
			MessageUtil.postReply(errorResp);
		}
		else
		{
			String startDate = null;
			if (searchDocketEvent.getStartDate() != null)
			{
				startDate = DateUtil.dateToString(searchDocketEvent.getStartDate(), "yyyy-MM-dd");
			}

			String endDate = null;
			if (searchDocketEvent.getEndDate() == null)
			{
				endDate = startDate;
			}
			else
			{
				endDate = DateUtil.dateToString(searchDocketEvent.getEndDate(), "yyyy-MM-dd");
			}

			String eventTypeId = searchDocketEvent.getEventTypeId();
			String eventTypeCd = null;
			if (eventTypeId != null && (eventTypeId.length() >0))
			{
				JuvenileEventTypeCode eventType = JuvenileEventTypeCode.find(eventTypeId);
				if (eventType != null)
				{
					eventTypeCd = eventType.getCode();
				}
			}
			//	83275 starts
			// juvenileId set to "" represents no juvenile search criteria
			// juvenileId set to null represents no juveniles were found
			if (juvenileId != null && startDate != null)
			{
			    setSearchByDateRange(juvenileId,eventTypeCd,startDate,endDate,errorResp);
			}else{
			    if(juvenileProfiles!=null){
				
				Iterator<Juvenile> juveniles =  juvenileProfiles.iterator();
				while(juveniles.hasNext()){
				    Juvenile juvenile = juveniles.next();
				    if(juvenile!=null){
					  setSearchByDateRange(juvenile.getJuvenileNum(),eventTypeCd,startDate,endDate,errorResp);
				    }
				}
			    }
			}
		}
	}
//	83275 ends
	/**
	 * 83275
	 * setSearch
	 * @param juvenileId
	 * @param eventTypeCd
	 * @param startDate
	 * @param endDate
	 * @param errorResp
	 */
	private void setSearchByDateRange(String juvenileId, String eventTypeCd,String startDate,String endDate,ErrorResponseEvent errorResp){
	    int result = 0;

		if (eventTypeCd == null || 
				(eventTypeCd.length() == 0) || eventTypeCd.equals(UIConstants.COURT_DATE))
		{
			GetJJSCourtByDateRangeEvent jjs = new GetJJSCourtByDateRangeEvent();
			jjs.setJuvenileNumber(juvenileId);
			jjs.setStartDate(startDate);
			jjs.setEndDate(endDate);

			result += this.getJJSCourts(jjs);
		}

		if (result < PDConstants.SEARCH_LIMIT)
		{
			if (eventTypeCd == null || 
					(eventTypeCd.length() == 0) || eventTypeCd.equals(UIConstants.DETENTION_HEARING))
			{
				GetJJSCLDetentionByDateRangeEvent det = new GetJJSCLDetentionByDateRangeEvent();
				det.setJuvenileNumber(juvenileId);
				det.setStartDate(startDate);
				det.setEndDate(endDate);

				result += this.getJJSCLDetentions(det);
			}
		}

		if (result >= PDConstants.SEARCH_LIMIT)
		{
			errorResp.setMessage("Number of Docket Events matching this criteria is greater than " +PDConstants.SEARCH_LIMIT );
			MessageUtil.postReply(errorResp);
		}
	}
	
	/**
	 * Get JJSCourtEvents & post them to dispatch
	 * @param jjs GetJJSCourtEventByDateEvent
	 * @return JJSCourtEvent count
	 * 81390 changes
	 */
	private int getJJSCourts(GetJJSCourtByDateRangeEvent jjs)
	{
		IHome home = new Home();
		MetaDataResponseEvent resp = home.findMeta(jjs, JJSCourt.class);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if (resp.getCount() < PDConstants.SEARCH_LIMIT)
		{
			Iterator i = home.findAll(jjs, JJSCourt.class);
			while (i.hasNext())
			{
				JJSCourt court = (JJSCourt) i.next();
				DocketEventResponseEvent docketResp = court.valueObject();
				// add here
				GetJuvenileHearingTypesEvent hearingTypeEvt = new GetJuvenileHearingTypesEvent();
				Iterator<JuvenileHearingTypeCode> hearingTypeCodes = new Home().findAll(hearingTypeEvt, JuvenileHearingTypeCode.class);
				List<JuvenileHearingTypeCode> hearingTypeCodesList = IteratorUtils.toList(hearingTypeCodes);
				if (resp != null)
				{
				    if (docketResp.getCourtResult() != null && !docketResp.getCourtResult().isEmpty())
				    {
					JuvenileDispositionCode juvenileDispositionCode = JuvenileDispositionCode.find("codeAlpha", docketResp.getCourtResult());
					if (juvenileDispositionCode != null)
					{
					    docketResp.setCourtResultDesc(juvenileDispositionCode.getShortDesc());
					}
				    }
				    if (docketResp.getDisposition() != null && !docketResp.getDisposition().isEmpty())
				    {
					JuvenileDispositionCode juvenileDispositionCode = JuvenileDispositionCode.find("codeAlpha", docketResp.getDisposition());
					if (juvenileDispositionCode != null)
					{
					    docketResp.setDispositionDesc(juvenileDispositionCode.getShortDesc());
					}
				    }
				    if (hearingTypeCodes != null)
				    {
					hearingTypeCodes = hearingTypeCodesList.iterator();//83426
					while (hearingTypeCodes.hasNext())
					{
					    JuvenileHearingTypeCode hearingTypeCode = hearingTypeCodes.next();
					    if (hearingTypeCode != null && hearingTypeCode.getInactiveInd() != null && !hearingTypeCode.getInactiveInd().equalsIgnoreCase("Y"))
					    {
						if (docketResp.getHearingType() != null && hearingTypeCode.getCode() != null && hearingTypeCode.getCode().equalsIgnoreCase(docketResp.getHearingType()))
						{
						    docketResp.setHearingTypeDesc(hearingTypeCode.getDescription());
						}
					    }
					}
				    }
				    if (docketResp.getAllegation() != null && StringUtils.isNotEmpty(docketResp.getAllegation()))
				    {

					    JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode",docketResp.getAllegation());
					    if (offCode != null)
					    {
						// Default to these else show transferred
						docketResp.setPetitionAllegationDesc(offCode.getShortDescription());
					    }
				    }
				    dispatch.postEvent(docketResp);
				}
			}
		}

		return resp.getCount();
	}

	/**
	 * Get JJSDetentionEvents & post them to dispatch
	 * @param jjs GetJJSDetentionEventByDateEvent
	 * @return JJSDetentionEvent count
	 */
	private int getJJSCLDetentions(GetJJSCLDetentionByDateRangeEvent det)
	{
		IHome home = new Home();
		MetaDataResponseEvent resp = home.findMeta(det, JJSCLDetention.class);

		if (resp.getCount() < PDConstants.SEARCH_LIMIT)
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			Iterator i = home.findAll(det, JJSCLDetention.class);
			while (i.hasNext())
			{
			    	JJSCLDetention detention = (JJSCLDetention) i.next();
				DocketEventResponseEvent docketResp = detention.valueObject();
				// add here
				GetJuvenileHearingTypesEvent hearingTypeEvt = new GetJuvenileHearingTypesEvent();
				Iterator<JuvenileHearingTypeCode> hearingTypeCodes = new Home().findAll(hearingTypeEvt, JuvenileHearingTypeCode.class);
				List<JuvenileHearingTypeCode> hearingTypeCodesList = IteratorUtils.toList(hearingTypeCodes);
				if (resp != null)
				{
				    if (docketResp.getCourtResult() != null && !docketResp.getCourtResult().isEmpty())
				    {
					JuvenileDispositionCode juvenileDispositionCode = JuvenileDispositionCode.find("codeAlpha", docketResp.getCourtResult());
					if (juvenileDispositionCode != null)
					{
					    docketResp.setCourtResultDesc(juvenileDispositionCode.getShortDesc());
					}
				    }
				    if (docketResp.getDisposition() != null && !docketResp.getDisposition().isEmpty())
				    {
					JuvenileDispositionCode juvenileDispositionCode = JuvenileDispositionCode.find("codeAlpha", docketResp.getDisposition());
					if (juvenileDispositionCode != null)
					{
					    docketResp.setDispositionDesc(juvenileDispositionCode.getShortDesc());
					}
				    }
				    if (hearingTypeCodes != null)
				    {
					hearingTypeCodes = hearingTypeCodesList.iterator();//83426
					while (hearingTypeCodes.hasNext())
					{
					    JuvenileHearingTypeCode hearingTypeCode = hearingTypeCodes.next();
					    if (hearingTypeCode != null && hearingTypeCode.getInactiveInd() != null && !hearingTypeCode.getInactiveInd().equalsIgnoreCase("Y"))
					    {
						if (docketResp.getHearingType() != null && hearingTypeCode.getCode() != null && hearingTypeCode.getCode().equalsIgnoreCase(docketResp.getHearingType()))
						{
						    docketResp.setHearingTypeDesc(hearingTypeCode.getDescription());
						}
					    }
					}
				    }
				    if (docketResp.getAllegation() != null && StringUtils.isNotEmpty(docketResp.getAllegation()))
				    {

					    JuvenileOffenseCode offCode = JuvenileOffenseCode.find("offenseCode",docketResp.getAllegation());
					    if (offCode != null)
					    {
						// Default to these else show transferred
						docketResp.setPetitionAllegationDesc(offCode.getShortDescription());
					    }
				    }
				    //
				dispatch.postEvent(docketResp);
				}
			}
		}
		
		return resp.getCount();
	}

	/**
	 * Creates a SearchJuvenileProfilesEvent
	 * @param searchDocketEvent GetViewCalendarDocketEventsEvent
	 * @return SearchJuvenileProfilesEvent
	 */
	private SearchJuvenileProfilesEvent createSearchEvent(GetViewCalendarDocketEventsEvent searchDocketEvent)
	{
		SearchJuvenileProfilesEvent juvProfSearch = new SearchJuvenileProfilesEvent();
		juvProfSearch.setLastName(searchDocketEvent.getJuvenileLastName());
		juvProfSearch.setFirstName(searchDocketEvent.getJuvenileFirstName());
		juvProfSearch.setMiddleName(searchDocketEvent.getJuvenileMiddleName());
		
		return juvProfSearch;
	}

	/**
	 * 83275 starts
	 * Builds a string of Juveniles
	 * @param results
	 * @return String
	 *//*
	private String buildJuvenileIdByJuvenile(List results)
	{
		StringBuffer sb = new StringBuffer();
		String pattern = "00000000";
		int len = results.size();
		for (int i = 0; i < len; i++)
		{
			Juvenile juv = (Juvenile) results.get(i);
			try
			{
				//sb.append(pattern.substring(0, 8 - juv.getJuvenileNum().length())); 81390
				sb.append(juv.getJuvenileNum());
			}
			catch( IndexOutOfBoundsException ioobe )
			{
			}
		}
		
		return sb.toString();
	}*/

	/**
	 * 83275 logic changed.
	 * Gets All Juveniles
	 * @param searchDocketEvent GetViewCalendarDocketEventsEvent
	 * @param errorResp ErrorResponseEvent
	 * @return String
	 */
        private List<Juvenile> getAllJuveniles(GetViewCalendarDocketEventsEvent searchDocketEvent, ErrorResponseEvent errorResp)
        {
    	if (searchDocketEvent.getJuvenileLastName() != null && (searchDocketEvent.getJuvenileLastName().length() > 0))
    	{
    	    SearchJuvenileProfilesEvent juvProfSearch = this.createSearchEvent(searchDocketEvent);
    
    	    Iterator<Juvenile> iter = Juvenile.findAll(juvProfSearch);
    	    List<Juvenile> results = CollectionUtil.iteratorToList(iter);
    	    int len = results.size();
    
    	    if (len > 31)
    	    {
    		errorResp.setMessage("Number of juvenile casefiles matching this criteria exceeds limitation. Please refine your search");
    	    }
    	    else
    		if (len > 0)
    		{
    		    return results;
    		}
    	}
    	return null;
        }

	/**
	 *83275
	 * Formats a Juvenile ID
	 * @param juvenileNum
	 * @return String
	 *//*
	private String getFormattedJuvenileId(String juvenileNum)
	{
		StringBuffer sb = new StringBuffer();
		String pattern = "00000000";
		
		sb.append(pattern.substring(0, 8 - juvenileNum.length()));
		sb.append(juvenileNum);
		
		return sb.toString();
	}*/

	/**
	 * @param event
	 * @roseuid 45F080FA036D
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * @param event
	 * @roseuid 45F080FA036F
	 */
	public void onUnregister(IEvent event)
	{
	}

	/**
	 * @param anObject
	 * @roseuid 45F080FA037C
	 */
	public void update(Object anObject)
	{
	}
}
