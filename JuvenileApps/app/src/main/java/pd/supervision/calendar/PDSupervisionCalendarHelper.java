/*
 * Created on Jul 7, 2006
 *
 */
package pd.supervision.calendar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.FastArrayList;
import org.apache.commons.collections.FastHashMap;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import messaging.administerlocation.GetAllJuvLocationUnitsByJuvLocIdEvent;
import messaging.administerserviceprovider.GetServiceProviderFromServiceIdEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.family.GetJuvenileMemberAddressEvent;
import messaging.interviewinfo.reply.InterviewPersonResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import pd.codetable.PDCodeHelper;
import pd.codetable.person.JuvenileSchoolDistrictCode;
import pd.juvenilecase.family.FamilyMemberAddressView;
import pd.juvenilecase.interviewinfo.Interview;
import pd.juvenilecase.interviewinfo.InterviewPerson;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.SP_Profile;
import pd.supervision.administerserviceprovider.administerlocation.JuvLocationUnit;

/**
 * @author C_NRaveendran
 */
public final class PDSupervisionCalendarHelper
{
	/**
     *  
     */
	private PDSupervisionCalendarHelper()
	{
		super();
	}

	/*
	 * 
	 */
	public static List getPreScheduledEventsDetails( 
			List preScheduledEvents, String aCalDetailLevel, 
			Map casefileMap, Map juvenileMap, Map spMap, Map contextMap )
	{
		List list = new ArrayList();
		Set juvLocIds = new HashSet();
		Set serviceIdSet = new HashSet();

		int len = preScheduledEvents.size();
		for(int i = 0; i < len; i++)
		{
			ServiceEventContext e = (ServiceEventContext)preScheduledEvents.get( i );
			serviceIdSet.add( String.valueOf( e.getServiceId() ) );
			juvLocIds.add( String.valueOf( e.getJuvLocUnitId() ) );
			list.add( e );
		}

		StringBuffer juvLocUnitIds = new StringBuffer();
		Iterator juvLocs = juvLocIds.iterator();
		while(juvLocs.hasNext())
		{
			String juvLocUnitId = (String)juvLocs.next();
			juvLocUnitIds.append( juvLocUnitId );
			if( juvLocs.hasNext() )
			{
				juvLocUnitIds.append( "," );
			}
		}

		Map juvLocMap = new FastHashMap();
		if( juvLocUnitIds.length() > 0 )
		{
			GetAllJuvLocationUnitsByJuvLocIdEvent jEvent = new GetAllJuvLocationUnitsByJuvLocIdEvent();
			jEvent.setJuvLocUnitId( juvLocUnitIds.toString() );
			Iterator juvIter = JuvLocationUnit.findAll( jEvent );
			while(juvIter.hasNext())
			{
				JuvLocationUnit j = (JuvLocationUnit)juvIter.next();
				if( !juvLocMap.containsKey( j.getOID() ) )
				{
					juvLocMap.put( j.getOID(), j );
				}
			}
		}

		GetServiceProviderFromServiceIdEvent getServiceProviderFromServiceIdEvent = 
				new GetServiceProviderFromServiceIdEvent();
		getServiceProviderFromServiceIdEvent.setServiceIdList( serviceIdSet );
		Iterator iter = JuvenileServiceProvider.findAll( getServiceProviderFromServiceIdEvent );

		Map serviceMap = new FastHashMap();
		while(iter.hasNext())
		{
			JuvenileServiceProvider jsp = (JuvenileServiceProvider)iter.next();
			serviceMap.put( String.valueOf( jsp.getServiceId() ), jsp );
		}

		List responseList = new FastArrayList();

		for(int i = 0; i < len; i++)
		{
			ServiceEventContext e = (ServiceEventContext)preScheduledEvents.get( i );
			CalendarServiceEventResponseEvent resp = e.getBasicCalendarServiceResponseEvent();

			JuvLocationUnit loc = (JuvLocationUnit)juvLocMap.get( String.valueOf( e.getJuvLocUnitId() ) );
			if( loc != null )
			{
				resp.setLocationId( loc.getLocationId() );
				resp.setJuvUnitCd( loc.getJuvUnitCd() );
				resp.setServiceLocationName( loc.getLocationUnitName() );
			}
			
			resp.setServiceLocationId( String.valueOf( e.getJuvLocUnitId() ) );
			resp.setLocationUnitId( String.valueOf( e.getJuvLocUnitId() ) );

			if( !(PDConstants.CAL_DETAIL_MEDIUM.equals( aCalDetailLevel ) || 
					PDConstants.CAL_DETAIL_SHORT.equals( aCalDetailLevel )) )
			{
				List associatedContexts = getAssociatedContexts( e, casefileMap, juvenileMap, contextMap );
				resp.setAssociatedContexts( associatedContexts );
				String instructorIdStr = String.valueOf( e.getInstructorId() );

				SP_Profile sp = (SP_Profile)spMap.get( instructorIdStr );
				if( sp == null )
				{
					sp = e.getInstructor();
					spMap.put( instructorIdStr, sp );
				}

				resp.setInstructorId( e.getInstructorId() );
				resp.setInstructorName( sp.getLastName() + ", " + sp.getFirstName() );
			}
			
			resp.setMaxAttendance( String.valueOf( e.getEventMaximum() ) );
			resp.setMinAttendance( String.valueOf( e.getEventMinimum() ) );
			resp.setCurrentEnrollment( String.valueOf( e.getCurrentEnrollment() ) );

			JuvenileServiceProvider jsp = (JuvenileServiceProvider)
					serviceMap.get( String.valueOf( e.getServiceId() ) );

			resp.setProgramId( String.valueOf( jsp.getProviderProgramId() ) );
			resp.setProgramName( jsp.getProgramName() );
			resp.setServiceName( jsp.getServiceName() );
			resp.setServiceId( String.valueOf( jsp.getServiceId() ) );
			resp.setServiceProviderId( Integer.parseInt( jsp.getServiceProviderId() ) );
			resp.setServiceProviderName( jsp.getServiceProviderName() );
			resp.setFax( jsp.getFax() );
			resp.setAdminUserProfileId( jsp.getAdminUserProfileId() );
			responseList.add( resp );
		}

		return responseList;
	}

	/*
     * 
     */
	public static List getAssociatedContexts( 
			ServiceEventContext e, Map casefileMap, 
			Map juvenileMap, Map contextMap )
	{
		List associatedContexts = new FastArrayList();

		return associatedContexts;
	}

	/*
     * 
     */
	public static List getNonInterviewEventsDetails( 
			List nonInterviewEvents, String aCalDetailLevel, 
			Map casefileMap, Map juvenileMap, Map locationMap, Map contextMap )
	{
		int nonLen = nonInterviewEvents.size();
		List responseList = new FastArrayList();

		for(int j = 0; j < nonLen; j++)
		{
			ServiceEventContext e = (ServiceEventContext)nonInterviewEvents.get( j );
			CalendarServiceEventResponseEvent resp = e.getBasicCalendarServiceResponseEvent();
			List associatedContexts = getAssociatedContexts( e, casefileMap, juvenileMap, contextMap );
			resp.setAssociatedContexts( associatedContexts );

			if( e.getSchoolCd() != null )
			{
				JuvenileSchoolDistrictCode schoolName = e.getSchoolName();
				resp.setSchoolName( schoolName.getSchoolDescription() );
				resp.setSchoolDistrictName( schoolName.getDistrictDescription() );
			}
			
			if( e.getContactFirstName() != null )
			{
				resp.setContactFirstName(e.getContactFirstName());			
			}
			
			if( e.getContactLastName() != null )
			{
				resp.setContactLastName(e.getContactLastName());			
			}
			
			if( e.getFacilityCd() != null )
			{
				resp.setFacilityCd( PDCodeHelper.getCodeDescriptionByCode( 
						PDCodeHelper.getCodes( PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, false ), 
						resp.getFacilityCd() ) );
			}
			
			if( e.getJuvLocUnitId() != 0 )
			{
				String juvLocUnitIdStr = String.valueOf( e.getJuvLocUnitId() );
				JuvLocationUnit locUnit = (JuvLocationUnit)locationMap.get( juvLocUnitIdStr );
				if( locUnit == null )
				{
					locUnit = e.getServiceLocation();
					locationMap.put( juvLocUnitIdStr, locUnit );
				}
				resp.setLocationId( locUnit.getLocationId() );
				resp.setLocationUnitId( juvLocUnitIdStr );
				resp.setJuvUnitCd( locUnit.getJuvUnitCd() );
				resp.setServiceLocationName( locUnit.getLocationUnitName() );
			}
			responseList.add( resp );
		}
		
		return responseList;
	}

	/*
	 * 
	 */
	public static List getInterviewEventsDetails( 
			List interviewEvents, String aCalDetailLevel, 
			Map casefileMap, Map juvenileMap, Map contextMap )
	{
		List responseList = new ArrayList();

		int len = interviewEvents.size();
		for(int i = 0; i < len; i++)
		{
			ServiceEventContext e = (ServiceEventContext)interviewEvents.get( i );
			CalendarServiceEventResponseEvent resp = e.getBasicCalendarServiceResponseEvent();

			if( !(PDConstants.CAL_DETAIL_MEDIUM.equals( aCalDetailLevel ) || 
					PDConstants.CAL_DETAIL_SHORT.equals( aCalDetailLevel )) )
			{
				List associatedContexts = getAssociatedContexts( e, casefileMap, juvenileMap, contextMap );
				resp.setAssociatedContexts( associatedContexts );
			}

			if( e.getInterviewId() != null )
			{
				Interview interview = e.getInterview();
				List personList = new ArrayList();
				resp.setInterviewId( interview.getOID() );
				if( interview.getInterviewPersons() != null && 
						interview.getInterviewPersons().size() > 0 )
				{
					Iterator<InterviewPerson> iPersonIter = interview.getInterviewPersons().iterator();
					while(iPersonIter.hasNext())
					{
						InterviewPerson person = iPersonIter.next();

						InterviewPersonResponseEvent personResponse = new InterviewPersonResponseEvent();
						personResponse.setInterviewId( null );
						personResponse.setFirstName( person.getFirstName() );
						personResponse.setMiddleName( person.getMiddleName() );
						personResponse.setLastName( person.getLastName() );
						personList.add( personResponse );
					}
				}

				resp.setInterviewPersons( personList );
				if( e.getJuvLocUnitId() != 0 )
				{
					resp.setLocationId( e.getServiceLocation().getLocationId() );
					resp.setLocationUnitId( Integer.toString( e.getJuvLocUnitId() ) );
					resp.setJuvUnitCd( e.getServiceLocation().getJuvUnitCd() );
					resp.setServiceLocationName( e.getServiceLocation().getLocationUnitName() );
				}
				else
				{
					pd.address.Address address = interview.getCustomAddress();

					StringBuffer sb = new StringBuffer();
					if( address != null )
					{
						sb.append( address.getStreetNum() );
						sb.append( " " );
						sb.append( address.getStreetName() );
						sb.append( " " );
						if( address.getCity() != null )
						{
							sb.append( address.getCity() );
							sb.append( ", " );
						}
						if( address.getStateId() != null && 
								address.getStateId().trim().length() > 0 )
						{
							if( address.getState() != null )
							{
								sb.append( address.getState().getDescription() );
								sb.append( " " );
							}
						}
						sb.append( address.getZipCode() );

						if( address.getAdditionalZipCode() != null && 
								address.getAdditionalZipCode().trim().length() > 0 )
						{
							sb.append( "-" );
							sb.append( address.getAdditionalZipCode() );
						}
					}
					
					resp.setServiceLocationName( sb.toString() );
				}
				
				if( interview.getInterviewTypeId() != null )
				{
					resp.setInterviewType( interview.getInterviewType().getDescription() );
				}
				
				resp.setInterviewStatusCd( interview.getInterviewStatusCd() );
				resp.setInterviewStatusDescription( interview.getInterviewStatus().getDescription() );
				resp.setCasefileId( interview.getCasefileId() );
			}

			responseList.add( resp );
		}
		
		return responseList;
	}

	/*
     * 
     */
	public static List getCalendarServiceEventResponseEvents( 
			List serviceEventContexts, String aCalDetailLevel )
	{
		List preScheduledEvents = new FastArrayList();
		List nonInterviewEvents = new FastArrayList();
		List interviewEvents = new FastArrayList();
		List responseList = new FastArrayList();

		int len = serviceEventContexts.size();
		for(int i = 0; i < len; i++)
		{
			ServiceEventContext e = (ServiceEventContext)serviceEventContexts.get( i );
			String eventCategory = e.getEventType().getGroup();
			if( eventCategory.equals( "P" ) )
			{
				preScheduledEvents.add( e );
			}
			else if( eventCategory.equals( "N" ) )
			{
				nonInterviewEvents.add( e );
			}
			else if( eventCategory.equals( "I" ) )
			{
				interviewEvents.add( e );
			}
		}

		Map casefileMap = new FastHashMap();
		Map juvenileMap = new FastHashMap();
		Map spMap = new FastHashMap();
		Map locationMap = new FastHashMap();
		Map contextMap = new FastHashMap();
		List events;

		if( preScheduledEvents.size() > 0 )
		{
			events = getPreScheduledEventsDetails( preScheduledEvents, 
					aCalDetailLevel, casefileMap, juvenileMap, spMap, contextMap );
			responseList.addAll( events );
		}

		if( nonInterviewEvents.size() > 0 )
		{
			events = getNonInterviewEventsDetails( nonInterviewEvents, 
					aCalDetailLevel, casefileMap, juvenileMap, locationMap, contextMap );
			responseList.addAll( events );
		}

		if( interviewEvents.size() > 0 )
		{
			events = getInterviewEventsDetails( interviewEvents, 
					aCalDetailLevel, casefileMap, juvenileMap, contextMap );
			responseList.addAll( events );
		}

		return responseList;
	}
}
