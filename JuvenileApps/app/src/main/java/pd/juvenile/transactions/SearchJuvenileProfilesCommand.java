/*
 * Project: JIMS
 * Class:   pd.juvenile.transactions.SearchJuvenileProfilesCommand
 *
 * Date:    2005-06-29
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package pd.juvenile.transactions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.GetJuvenileStatusEvent;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetJuvenileAliasEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;

import org.apache.commons.collections.FastArrayList;

import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileAlias;
import pd.juvenile.JuvenileBuilder;
import pd.juvenile.JuvenileCore;
import pd.juvenile.JuvenileMasterStatus;
import pd.juvenile.JuvenileStatus;
import pd.juvenilecase.referral.JJSReferral;
import pd.pattern.AbstractResultsTemplateCommand;

/**
 * Class SearchJuvenileProfilesCommand
 */
public class SearchJuvenileProfilesCommand extends AbstractResultsTemplateCommand implements ICommand
{
	// ------------------------------------------------------------------------
	// --- constructor ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 42A74F12006D
	 */
	public SearchJuvenileProfilesCommand()
	{
	} // end of

	// pd.juvenile.transactions.SearchJuvenileProfilesCommand.SearchJuvenileProfilesCommand

	// ------------------------------------------------------------------------
	// --- methods ---
	// ------------------------------------------------------------------------

	/**
	 * 
	 * @param event
	 * @roseuid 42A5DD8F031D
	 */
	public void execute(IEvent event) 
	{
		SearchJuvenileProfilesEvent searchEvent = (SearchJuvenileProfilesEvent) event;
		/*
		 * Client code needs to set the juvenileType value to 'alias'
		 * so that juv alias records are included in the result set.
		 * Send in an empty string, or something other than 'alias'
		 * to get a result set without alias records included
		 */
		boolean includeAliasRecords = (searchEvent.getSearchType() != null && 
				searchEvent.getSearchType().equalsIgnoreCase( "alias" )) ;
		
		String juvenileNum = searchEvent.getJuvenileNum();
		String ssn = searchEvent.getSsn();
		String dalogNum = searchEvent.getDalogNum();
		
		if (juvenileNum != null && (juvenileNum.length() > 0))
		{
			JuvenileCore juvCore = null;
			List juveniles = new FastArrayList(1);
			
			if( (juvCore = JuvenileCore.findCore(juvenileNum)) != null ) 
			{
				juveniles.add(juvCore);
			}
			
			this.processResults(event, juveniles.iterator());
		}  
		else if (ssn != null && (ssn.length() > 0))
		{
			IHome home = new Home();
			MetaDataResponseEvent juvenileCount = (MetaDataResponseEvent)home.findMeta(searchEvent, Juvenile.class);
			
			if((juvenileCount != null && juvenileCount.getCount() < 2000) )
			{
				Iterator<JuvenileCore> i = JuvenileCore.findAll(searchEvent);
				this.processResults(event, i);
			}
		}  
		//add dalognum task 171828
		else if (dalogNum != null && (dalogNum.length() > 0))
		{
		    	//get juvnum from referrals using dalog and then call juvnum
		    String juvNum=null;
		    Iterator<JJSReferral> refIter = JJSReferral.findAll("daLogNum", dalogNum);
			   
			   if( refIter.hasNext()){
			       
			       JJSReferral foundRec = refIter.next();
			       juvNum=foundRec.getJuvenileNum();
			   }

		    	JuvenileCore juvCore = null;
			List juveniles = new FastArrayList(1);
			if(juvNum!=null)
			{
        			if( (juvCore = JuvenileCore.findCore(juvNum)) != null ) 
        			{
        				juveniles.add(juvCore);
        			}
        			
        			this.processResults(event, juveniles.iterator());
			}
		}  
		else 
		{
			IHome home = new Home();
			MetaDataResponseEvent juvenileCount = (MetaDataResponseEvent)home.findMeta(searchEvent, Juvenile.class);
			
			if((juvenileCount != null && juvenileCount.getCount() < 2000) )
			{
				Iterator<JuvenileCore> i = JuvenileCore.findAll(searchEvent);
				this.processResults(event, i);
				
				// Get Aliases
				if( includeAliasRecords )
				{
					GetJuvenileAliasEvent aliasSearch = null;
					// Obtain count information for juvenile records in J2JJSMS & J2JJSNA
					aliasSearch = new GetJuvenileAliasEvent();
					aliasSearch.setFirstName(searchEvent.getFirstName());
					aliasSearch.setLastName(searchEvent.getLastName());
					aliasSearch.setMiddleName(searchEvent.getMiddleName());
					aliasSearch.setRaceId(searchEvent.getRaceId());
					aliasSearch.setSexId(searchEvent.getSexId());
					aliasSearch.setDateOfBirth(searchEvent.getDateOfBirth());
					aliasSearch.setStatusId(searchEvent.getStatusId());
					//setting it in search but not using as master status not in alias table(add it to alias table if we need to filter from page for that too and update from jjs_ms_juvenile table)
					MetaDataResponseEvent juvenileAliasCount = null ;
					aliasSearch.setJuvenileType("A"); //bug fix #29958
					//call to find the master status for the kid from jjs_ms_juvenile
					juvenileAliasCount = (MetaDataResponseEvent) home.findMeta(aliasSearch, JuvenileAlias.class);
					if( juvenileAliasCount != null && juvenileAliasCount.getCount() < 2000 )
					{
					    	aliasSearch.setJuvenileType("");
						i = JuvenileAlias.findAll(aliasSearch);
						this.processResults(event, i);
					}
					else
					{
						ErrorResponseEvent errorResp = new ErrorResponseEvent();
						errorResp.setMessage("error.max.limit.exceeded");
						IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
						dispatch.postEvent(errorResp);
					}
				}
			} 
			else 
			{
				ErrorResponseEvent errorResp = new ErrorResponseEvent();
				errorResp.setMessage("error.max.limit.exceeded");
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
				dispatch.postEvent(errorResp);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.pattern.ResultsCommandTemplate#handleZeroResults()
	 */
	public void handleZeroResult(IEvent event)
	{
	}

	/*
	 * (non-Javadoc)
	 * @see pd.pattern.AbstractResultsTemplateCommand#handleSingleResult(mojo.km.messaging.IEvent, java.lang.Object)
	 */
	public void handleSingleResult(IEvent event, Object pObj)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		JuvenileCore juvCore = null;
		JuvenileAlias juvAlias = null;
		JuvenileBuilder builder = null;
		SearchJuvenileProfilesEvent searchEvent = (SearchJuvenileProfilesEvent) event;
		
		if(pObj != null && pObj.getClass().equals(JuvenileCore.class))
		{
			juvCore = (JuvenileCore)pObj;
			
		} 
		else if(pObj != null && pObj.getClass().equals(JuvenileAlias.class))
		{
			juvAlias = (JuvenileAlias)pObj;
		}
		
		String juvenileNum = ((juvCore != null)?juvCore.getJuvenileNum():((juvAlias != null)?juvAlias.getJuvenileNum():null));
		Juvenile juv = Juvenile.findJCJuvenile(juvenileNum);
				
		if(juvCore != null) 
		{
			builder = new JuvenileBuilder(juv, juvCore);
		} 
		else if(juvAlias != null)
		{
			builder = new JuvenileBuilder(juv, juvAlias);
		}
		builder.build();		
		builder.setStatusCodes();		
		String status = ((juvCore != null)?juvCore.calculateStatusId():((juvAlias != null)?juvAlias.calculateStatusId():null));
		String masterStatus = ((juvCore != null)?juvCore.calculatemasterStatusId():((juvAlias != null)?juvAlias.calculatemasterStatusId():null));
				    
		if(status != null && searchEvent.getStatusId()!= null && (status.equals(searchEvent.getStatusId()) || searchEvent.getStatusId().equals("")) )
		{
		    	builder.setStatus(((status == null) || (status != null && status.equals("")))?"N":status);
			////add master status
			builder.setmasterStatus(masterStatus);			
			
			JuvenileProfileDetailResponseEvent resp = (JuvenileProfileDetailResponseEvent) builder.getResult();
			dispatch.postEvent(resp);
	
			if (juv != null) 
			{
				CompositeResponse scarsMarks = juv.getScarsMarksResponse();
				dispatch.postEvent(scarsMarks);
			}
		}
		else if(status != null)
		{
		    	builder.setStatus(((status == null) || (status != null && status.equals("")))?"N":status);
        		////add master status
        		builder.setmasterStatus(masterStatus);
        		
			JuvenileProfileDetailResponseEvent resp = (JuvenileProfileDetailResponseEvent) builder.getResult();
			dispatch.postEvent(resp);
	
			if (juv != null) 
			{
				CompositeResponse scarsMarks = juv.getScarsMarksResponse();
				dispatch.postEvent(scarsMarks);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see pd.pattern.AbstractResultsTemplateCommand#handleMultipleResults(mojo.km.messaging.IEvent, java.util.List)
	 */
	public void handleMultipleResults(IEvent event, List list) 
	{
		int len = list.size();
		Map<String, String> statusLookup = new HashMap<String, String>();
		JuvenileCore juvenile = null;
		JuvenileAlias juvenileAlias = null;
		
		Set juvenileNums = new HashSet();
		for (int i = 0; i < len; i++) 
		{
			Object obj = list.get(i);
			if(obj != null && obj.getClass().equals(JuvenileCore.class))
			{
				juvenile = (JuvenileCore)obj;
				juvenileAlias = null;
			} 
			else if(obj != null && obj.getClass().equals(JuvenileAlias.class))
			{
				juvenileAlias = (JuvenileAlias)obj;
				juvenile = null;
			}
			
			String juvenileNum = ((juvenile != null)?juvenile.getJuvenileNum():((juvenileAlias != null)?juvenileAlias.getJuvenileNum():null));	
			juvenileNums.add(juvenileNum);
			statusLookup.put(juvenileNum, "N");
		}

		GetJuvenileStatusEvent getStatus = new GetJuvenileStatusEvent();
		getStatus.setJuvenileNums(juvenileNums);
		IHome home = new Home();
		Iterator s = home.findAll(getStatus, JuvenileStatus.class);

		while (s.hasNext())
		{
			JuvenileStatus status = (JuvenileStatus) s.next();
			statusLookup.put(status.getJuvenileNum(), status.getStatusId());
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		JuvenileBuilder builder = new JuvenileBuilder();
		builder.setStatusCodes();

		for (int i = 0; i < len; i++) 
		{
			builder.clear();
			Object obj = list.get(i);
			if(obj != null && obj.getClass().equals(JuvenileCore.class))
			{
				juvenile = (JuvenileCore)obj;
				juvenileAlias = null;
			} 
			else if(obj != null && obj.getClass().equals(JuvenileAlias.class))
			{
				juvenileAlias = (JuvenileAlias)obj;
				juvenile = null;
			}
			
			String juvenileNum = ((juvenile != null)?juvenile.getJuvenileNum():((juvenileAlias != null)?juvenileAlias.getJuvenileNum():null));
			Juvenile juv = Juvenile.findJCJuvenile(juvenileNum);
			String status = statusLookup.get(juvenileNum);
			String masterStatus = ((juvenile != null)?juvenile.calculatemasterStatusId():((juvenileAlias != null)?juvenileAlias.calculatemasterStatusId():null));
			
			SearchJuvenileProfilesEvent searchEvent = (SearchJuvenileProfilesEvent) event;
			if(status != null && searchEvent.getStatusId()!=null && (status.equals(searchEvent.getStatusId()) || searchEvent.getStatusId().equals("")) )
			{
				if(juvenile != null)
				{
					//builder.setJuvenileCore(juvenile);
				    	builder = new JuvenileBuilder(juv, juvenile);
				} 
				else if(juvenileAlias != null)
				{
					//builder.setJuvenileAlias(juvenileAlias);
					builder = new JuvenileBuilder(juv, juvenileAlias);					
				}
				builder.build();
				builder.setStatusCodes();	
				builder.setStatus(((status == null) || (status != null && status.equals("")))?"N":status);
				////add master status
				builder.setmasterStatus(masterStatus);
				
				ResponseEvent response = (ResponseEvent) builder.getResult();
				dispatch.postEvent(response);
			}
			else if(status != null)
			{
				if(juvenile != null)
				{
					//builder.setJuvenileCore(juvenile);
				    	builder = new JuvenileBuilder(juv, juvenile);
				} 
				else if(juvenileAlias != null)
				{
					//builder.setJuvenileAlias(juvenileAlias);
				    	builder = new JuvenileBuilder(juv, juvenileAlias);				    						
				}
				builder.build();
				builder.setStatusCodes();	
				builder.setStatus(((status == null) || (status != null && status.equals("")))?"N":status);
				////add master status
				builder.setmasterStatus(masterStatus);
				
				ResponseEvent response = (ResponseEvent) builder.getResult();
				dispatch.postEvent(response);
			}
		}
	}

	/**
	 * 
	 * @param event
	 * @roseuid 42A5DD8F031F
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * 
	 * @param event
	 * @roseuid 42A5DD8F0321
	 */
	public void onUnregister(IEvent event)
	{
	}

	/**
	 * 
	 * @param anObject
	 * @roseuid 42A5DD8F0323
	 */
	public void update(Object anObject)
	{
	}

}
