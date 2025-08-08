package pd.supervision.calendar.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import naming.PDConstants;

import messaging.calendar.GetOfficerAssociatedJuvenileCasefilesEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenilecase.SearchCasefileByJuvenileAndOfficerEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import pd.juvenile.Juvenile;
import pd.juvenilecase.JuvenileCasefile;

public class GetOfficerAssociatedJuvenileCasefilesCommand implements ICommand
{
	/**
	 * @roseuid 45F1B0D902AD
	 */
	public GetOfficerAssociatedJuvenileCasefilesCommand()
	{
	}

	/**
	 * Initializes a SearchJuvenileProfilesEvent and sets its values
	 * @param searchDocketEvent
	 * @return
	 */
	private SearchJuvenileProfilesEvent createSearchEvent(GetOfficerAssociatedJuvenileCasefilesEvent searchDocketEvent)
	{
		SearchJuvenileProfilesEvent juvProfSearch = new SearchJuvenileProfilesEvent();
		juvProfSearch.setLastName(searchDocketEvent.getJuvenileLastName());
		juvProfSearch.setFirstName(searchDocketEvent.getJuvenileFirstName());
		juvProfSearch.setMiddleName(searchDocketEvent.getJuvenileMiddleName());
		
		return juvProfSearch;
	}

	/**
	 * Executes command
	 * @param event
	 * @roseuid 45F080FA035F
	 */
	public void execute(IEvent event)
	{
		GetOfficerAssociatedJuvenileCasefilesEvent searchDocketEvent = (GetOfficerAssociatedJuvenileCasefilesEvent) event;
		ErrorResponseEvent errorResp = new ErrorResponseEvent();
			
		this.getOfficerAssociatedJuvenileCasefiles(searchDocketEvent, errorResp);
	}

	/**
	 * Returns OfficerAssociatedJuveniles as JuvenileCasefileResponseEvent(s)
	 * @param searchDocketEvent
	 * @param errorResp
	 */
	private void getOfficerAssociatedJuvenileCasefiles(
			GetOfficerAssociatedJuvenileCasefilesEvent searchDocketEvent,
			ErrorResponseEvent errorResp)
	{
		List juvNumList = new ArrayList();

		if (searchDocketEvent.hasJuvenileName())
		{
			SearchJuvenileProfilesEvent juvProfSearch = this.createSearchEvent(searchDocketEvent);

			IHome home = new Home();
			MetaDataResponseEvent metaData = (MetaDataResponseEvent) home.findMeta(juvProfSearch, Juvenile.class);

			if( metaData.getCount() > PDConstants.SEARCH_LIMIT)
			{
				errorResp.setMessage("Number of juveniles matching this criteria is greater than " + PDConstants.SEARCH_LIMIT);
			}
			else
			{ // we're clear to get the records
				Iterator iter = Juvenile.findAll(juvProfSearch) ;
				while( iter.hasNext() )
				{
					Juvenile juv = (Juvenile) iter.next();
					juvNumList.add(juv.getJuvenileNum());
				}
			}
		}

		if (errorResp.getMessage() == null)
		{
			SearchCasefileByJuvenileAndOfficerEvent caseEvent = new SearchCasefileByJuvenileAndOfficerEvent();
			caseEvent.setOfficerUserId(searchDocketEvent.getJpoUserId()); // 
			caseEvent.setJuvNumList(juvNumList);
					
			Iterator iter = JuvenileCasefile.findAll(caseEvent); // Finds all Juveniles associated with JPO ID
			List juvenileCaseFiles = CollectionUtil.iteratorToList(iter);
			
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			
			int len = juvenileCaseFiles.size();
			for (int i = 0; i < len; i++)
			{
				JuvenileCasefile casefile = (JuvenileCasefile) juvenileCaseFiles.get(i);

				if( "A".equalsIgnoreCase(casefile.getCaseStatusId()))
				{
					JuvenileCasefileResponseEvent casefileResponse = new JuvenileCasefileResponseEvent();
					casefileResponse.setJuvenileNum(casefile.getJuvenileId());
					casefileResponse.setProbationOfficeId(casefile.getProbationOfficerId());
					casefileResponse.setCaseStatusId(casefile.getCaseStatusId());
					dispatch.postEvent(casefileResponse);
				}
			}
		} 
		else 
		{
			MessageUtil.postReply(errorResp);
		}
	}

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
