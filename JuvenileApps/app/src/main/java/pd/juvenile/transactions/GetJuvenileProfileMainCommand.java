package pd.juvenile.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import messaging.codetable.reply.SocialElementCodeResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileAliasResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import naming.PDCodeTableConstants;
import pd.codetable.person.ScarsMarksTattoosCode;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileAlias;
import pd.juvenile.JuvenileBuilder;
import pd.juvenile.JuvenileCore;
import pd.juvenile.JuvenileHelper;
import pd.juvenile.JuvenileJIS;
import pd.juvenile.JuvenileStatus;
import pd.km.util.Name;

public class GetJuvenileProfileMainCommand implements ICommand
{

	// ------------------------------------------------------------------------
	// --- constructor ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 42A74EFF030D
	 */
	public GetJuvenileProfileMainCommand()
	{

	} // end of
		// pd.juvenile.transactions.GetJuvenileProfileMainCommand.GetJuvenileProfileMainCommand

	// ------------------------------------------------------------------------
	// --- methods ---
	// ------------------------------------------------------------------------

	/**
	 * 
	 * @param event
	 * @roseuid 42A5DD9000BD
	 */
	public void execute(IEvent event)
	{
		GetJuvenileProfileMainEvent juvEvent = (GetJuvenileProfileMainEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		JuvenileProfileDetailResponseEvent resp = null;
		String juvNum = juvEvent.getJuvenileNum();
		
		JuvenileCore juvCore = JuvenileCore.findCore(juvNum);
		String masterStatus = (juvCore != null)?juvCore.calculatemasterStatusId():null;		
		if (juvCore != null)
		{
			Juvenile juv = Juvenile.findJCJuvenile(juvNum);
			List<JuvenileAlias> juvAliasList = JuvenileAlias.find(juvNum);
			
			JuvenileBuilder builder = new JuvenileBuilder(juv, juvCore);
			builder.setStatusCodes();
			builder.build();
			String status = calculateStatus(juvNum);
			builder.setStatus(status);
			builder.setmasterStatus(masterStatus);
			resp = (JuvenileProfileDetailResponseEvent) builder.getResult();
			resp.setAliasList(getJuvenileAliasList(juvAliasList));
			if (juv == null){
			    resp.setFromM204Flag("Y");
			}else{
			    resp.setFromM204Flag("N");
			}
			// added to update the status field in jjs_ms_juvenile with the master status from new view JCJUVENILEMASTERSTATUS 
			if (!juvCore.getStatusId().equalsIgnoreCase(masterStatus))
			{
			    juvCore.setStatusId(masterStatus);
			    new Home().bind( juvCore );
			}
			//taken out on 04/11/2017 - bug #48050
			//hot fix - Bug #42651, will be changed after facility goes live			
			/*	if(juvEvent.getFromProfile()!=null && juvEvent.getFromProfile().equalsIgnoreCase("profileBriefingDetails"))
				{
					if(juv!=null){
						JJSJuvenile myJJSInfo = juv.getJjsJuvInfo();
						if(myJJSInfo!=null && myJJSInfo.getDetentionStatusId()!=null && !myJJSInfo.getDetentionStatusId().equals(""))
						{
							resp.setDetentionFacilityId(myJJSInfo.getDetentionFacilityId());
							resp.setDetentionStatusId(myJJSInfo.getDetentionStatusId());
						}
					}
				}
				else
				{
					//if coming from facility
					Iterator<JJSHeader> jjs_headerItr = JJSHeader.findAll("juvenileNumber", resp.getJuvenileNum()); //bug fix #40652 
					if(jjs_headerItr!=null){
						if(jjs_headerItr.hasNext()){
							JJSHeader jjs_headerInfo = jjs_headerItr.next();
							if (jjs_headerInfo!=null&& jjs_headerInfo.getFacilityStatus() != null && !jjs_headerInfo.getFacilityStatus().equals("")) {
								resp.setDetentionFacility(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY,jjs_headerInfo.getFacilityCode()));
								resp.setDetentionFacilityId(jjs_headerInfo.getFacilityCode());
								resp.setDetentionStatus(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.FACILITY_STATUS,jjs_headerInfo.getFacilityStatus()));
								resp.setDetentionStatusId(jjs_headerInfo.getFacilityStatus());
							}
						}
					}
				}*/
			
			//Added for US 37931 - JIS information
			Iterator jisIter = JuvenileJIS.findAll("juvenileNum", juvNum);
			Collection<JuvenileJISResponseEvent> jisResps = new ArrayList<JuvenileJISResponseEvent>();
			JuvenileJISResponseEvent jisResp;
			while(jisIter.hasNext())
			{
				JuvenileJIS jisRec = (JuvenileJIS)jisIter.next();
				jisResp = new JuvenileJISResponseEvent();
				jisResp.setAgency(jisRec.getAgency());
				jisResp.setComments(jisRec.getComments());
				jisResp.setOtherAgency(jisRec.getOtherAgency());
				jisResp.setEntryDate(jisRec.getEntryDate());
				jisResps.add(jisResp);
			}
			resp.setJisInfo(jisResps);
			
		}
		if (resp != null)
		{
			dispatch.postEvent(resp);
			processScarsMarksTattoosCodes(dispatch);

			// since getSocialElementCodes don't have his own command and action

			Collection religionCodes = JuvenileHelper.getSocialElementCodes("D");
			Iterator iter = religionCodes.iterator();
			while (iter.hasNext())
			{
				dispatch.postEvent((SocialElementCodeResponseEvent) iter.next());
			}

		}
	}

	private List<JuvenileAliasResponseEvent> getJuvenileAliasList(List<JuvenileAlias> juvAliasEntityList){
		
		Iterator <JuvenileAlias> iter = ((juvAliasEntityList != null)?juvAliasEntityList.iterator():null); 
		List<JuvenileAliasResponseEvent> aliasResponseList = new ArrayList<JuvenileAliasResponseEvent>();
		
		while(iter != null && iter.hasNext()){
			JuvenileAlias juv = iter.next();
			
			if(juv != null && (juv != null && juv.getJuvenileType() != null && juv.getJuvenileType().length() > 0)){
				JuvenileAliasResponseEvent aliasRecord = new JuvenileAliasResponseEvent();
				aliasRecord.setJuvenileNum(juv.getJuvenileNum());
				aliasRecord.setId(juv.getOID());
				Name name = new Name();
				name.setFirstName( nvl(juv.getFirstName()) );
				name.setLastName( nvl(juv.getLastName()) );
				name.setMiddleName( nvl(juv.getMiddleName()) );
				name.setSuffix( nvl(juv.getNameSuffix()) );
				aliasRecord.setAliasName( nvl(name.getFormattedName()) );
				aliasRecord.setEntryDate(juv.getAliasEntryDate());
				aliasRecord.setJuvenileType( nvl(juv.getJuvenileType()) );
				
				aliasRecord.setNotes( nvl(juv.getAliasNotes()) );
				aliasResponseList.add(aliasRecord);
			}
		}
		return aliasResponseList;
		
	}
	
	private String calculateStatus(String juvNum)
	{
		String status = "";

		JuvenileStatus juvStatus = JuvenileStatus.find(juvNum);
		
		if (juvStatus != null)
		{
			status = juvStatus.getStatusId();
		}		

		return status;
	}
	
	
	private String nvl(String value){
	    
	    return ( value != null && value.length() > 0) ? value : "";
	}

	/**
	 * 
	 * @param event
	 * @roseuid 42A5DD9000CB
	 */
	public void onRegister(IEvent event)
	{

	} // end of pd.juvenile.transactions.GetJuvenileProfileMainCommand.onRegister

	/**
	 * 
	 * @param event
	 * @roseuid 42A5DD9000CD
	 */
	public void onUnregister(IEvent event)
	{

	} // end of pd.juvenile.transactions.GetJuvenileProfileMainCommand.onUnregister

	/**
	 * 
	 * @param updateObject
	 *            The update object.
	 */
	public void update(Object updateObject)
	{
		// TODO Auto-generated method stub

	} // end of pd.juvenile.transactions.GetJuvenileProfileMainCommand.update

	/**
	 * 
	 * @param dispatch
	 *            The IDispatch.
	 */
	private void processScarsMarksTattoosCodes(IDispatch dispatch)
	{
		Collection tattoos = ScarsMarksTattoosCode.findAllTattoos();
		Iterator i = tattoos.iterator();
		while (i.hasNext())
		{
			ScarsMarksTattoosCode tattoo = (ScarsMarksTattoosCode) i.next();
			ScarsMarksTattoosCodeResponseEvent codeResponse = new ScarsMarksTattoosCodeResponseEvent();
			tattoo.fill(codeResponse);
			codeResponse.setTopic(PDCodeTableConstants.getCodeTableTopic(PDCodeTableConstants.TATTOOS));
			dispatch.postEvent(codeResponse);
		}

		Collection scarsMarks = ScarsMarksTattoosCode.findAllScarsMarks();
		i = scarsMarks.iterator();
		while (i.hasNext())
		{
			ScarsMarksTattoosCode scarMark = (ScarsMarksTattoosCode) i.next();
			ScarsMarksTattoosCodeResponseEvent codeResponse = new ScarsMarksTattoosCodeResponseEvent();
			scarMark.fill(codeResponse);
			codeResponse.setTopic(PDCodeTableConstants.getCodeTableTopic(PDCodeTableConstants.SCARS_MARKS));
			dispatch.postEvent(codeResponse);
		}
	}

} // end GetJuvenileProfileMainCommand
