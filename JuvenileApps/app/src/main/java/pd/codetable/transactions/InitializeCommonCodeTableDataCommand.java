//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\transactions\\GetPartyInfoCommand.java

package pd.codetable.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import messaging.codetable.InitializeCommonCodeTableDataEvent;
import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.codetable.reply.CommonCollectionsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDCodeTableConstants;
import pd.codetable.Code;
import pd.codetable.person.ScarsMarksTattoosCode;

/**
 * @author dgibler
 *
 */
public class InitializeCommonCodeTableDataCommand implements ICommand
{
	/**
	 * @roseuid 416D2E6E02F4
	 */
	public InitializeCommonCodeTableDataCommand()
	{
	}

	/**
	 * @param event
	 * @roseuid 416BD7DE0376
	 */
	public void execute(IEvent event)
	{
		
		InitializeCommonCodeTableDataEvent incomingEvent = 
								(InitializeCommonCodeTableDataEvent)event;
		
		CommonCollectionsResponseEvent responseEvent = new  CommonCollectionsResponseEvent();
		String codeTableName;
		ArrayList codes;
		// for every code table, get the codes and add them to the response event.
		Iterator codeTableNames = incomingEvent.getCodeTableNames();
		while (codeTableNames.hasNext()) {
			codeTableName = codeTableNames.next().toString();
			codes = getCodeTableData(codeTableName);
			Collections.sort(codes);
			responseEvent.addCodeTableData(codeTableName, codes);													
		}
		// now go load the Scars and Marks code table data
		getScarsMarksTattoosCode(responseEvent);
		
		// post the reply event.
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(responseEvent);
		
	}

	/**
	 * Retrieves CodeTable objects and creates Code response events
	 * @param codeTableName
	 * @return ArrayList of Code response events.
	 */
	private ArrayList getCodeTableData(String codeTableName)
	{
		Collection codes = Code.findAll(codeTableName);
		ArrayList codeList = new ArrayList();
			
		Iterator i = codes.iterator();
			
		while (i.hasNext())
		{
			CodeResponseEvent codeReply = new CodeResponseEvent();
			codeReply.setTopic(PDCodeTableConstants.getCodeTableTopic(codeTableName));

			Code code = (Code) i.next();
			codeReply.setCodeId(code.getCodeId());
			codeReply.setActiveDate(code.getActiveDate());
			codeReply.setCode(code.getCode());
			codeReply.setCodeTableName(code.getCodeTableName());
			codeReply.setDescription(code.getDescription());
			codeReply.setInactiveDate(code.getInactiveDate());
			codeReply.setInactiveEffectiveDate(code.getInactiveEffectiveDate());
			codeReply.setStatus(code.getStatus());

			codeList.add(codeReply);
		}
		return codeList;		
	}

		/**
	 *
	 */
	private void getScarsMarksTattoosCode(CommonCollectionsResponseEvent responseEvent)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		ArrayList codeList = new ArrayList();

		Collection tattoos = ScarsMarksTattoosCode.findAllTattoos();
		Iterator i = tattoos.iterator();
		while (i.hasNext())
		{
			ScarsMarksTattoosCode tattoo = (ScarsMarksTattoosCode) i.next();
			ScarsMarksTattoosCodeResponseEvent codeResponse = new ScarsMarksTattoosCodeResponseEvent();
			tattoo.fill(codeResponse);
			codeResponse.setTopic(PDCodeTableConstants.getCodeTableTopic(PDCodeTableConstants.TATTOOS));
			codeList.add(codeResponse);
		}
		responseEvent.addCodeTableData(PDCodeTableConstants.TATTOOS, codeList);													
		
		codeList = new ArrayList();
		Collection scarsMarks = ScarsMarksTattoosCode.findAllScarsMarks();
		i = scarsMarks.iterator();
		while (i.hasNext())
		{
			ScarsMarksTattoosCode scarMark = (ScarsMarksTattoosCode) i.next();
			ScarsMarksTattoosCodeResponseEvent codeResponse = new ScarsMarksTattoosCodeResponseEvent();
			scarMark.fill(codeResponse);
			codeResponse.setTopic(PDCodeTableConstants.getCodeTableTopic(PDCodeTableConstants.SCARS_MARKS));
			codeList.add(codeResponse);
		}
		responseEvent.addCodeTableData(PDCodeTableConstants.SCARS_MARKS, codeList);													
	}
	
	/**
	 * fetches all Agencies and Divisions
	 */
//	private void getAgencyAndDivisionData() {
//		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
//
//		GetAllAgenciesAndDivisionsEvent event =
//			(GetAllAgenciesAndDivisionsEvent) EventFactory.getInstance(
//				AgencyControllerServiceNames.GETALLAGENCIESANDDIVISIONS);
//
//		dispatch.postEvent(event);
//
//		CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
//
//		MessageUtil.processReturnException(replyEvent);
//
//	}

	/**
	 * @param event
	 * @roseuid 416BD7DE0378
	 */
	public void onRegister(IEvent event)
	{
	}

	/**
	 * @param event
	 * @roseuid 416BD7DE0381
	 */
	public void onUnregister(IEvent event)
	{
	}

	/**
	 * @param anObject
	 * @roseuid 416BD7DE0383
	 */
	public void update(Object anObject)
	{
	}
}