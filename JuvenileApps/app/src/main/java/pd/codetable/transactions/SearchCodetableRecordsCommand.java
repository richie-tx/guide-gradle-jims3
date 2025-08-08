//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\codetable\\transactions\\GetCodeTableRecordCommand.java

package pd.codetable.transactions;

import java.util.Iterator;

import messaging.codetable.SearchCodetableNonMaRecordsEvent;
import messaging.codetable.SearchCodetableRecordsEvent;
import messaging.codetable.reply.CodetableRecordResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.info.reply.CountInfoMessage;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;
import naming.ResponseLocatorConstants;
import pd.codetable.CodetableReg;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.exception.ReflectionException;
import pd.security.PDSecurityHelper;

public class SearchCodetableRecordsCommand implements ICommand
{

	/**
	 * @roseuid 45B9521B01BF
	 */
	public SearchCodetableRecordsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 45B94F5000C2
	 */
	public void execute(IEvent event)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		SearchCodetableRecordsEvent sEvent = (SearchCodetableRecordsEvent) event;
		SearchCodetableNonMaRecordsEvent sEvent1 = new SearchCodetableNonMaRecordsEvent();

		sEvent1.setCodeTableName(sEvent.getCodeTableName());
		sEvent1.setCodeTableDescription(sEvent.getCodeTableDescription());
		sEvent1.setCodeTableType(sEvent.getCodeTableType());
		sEvent1.setCodeTableAgencyId(sEvent.getCodeTableAgencyId());
		ResponseContextFactory respFac = new ResponseContextFactory();
		ResponseCreator aCreator = null;
		try
		{
			aCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.CODETABLERECORD_RESPONSE_LOCATOR);
		}
		catch (ReflectionException e)
		{
			e.printStackTrace();
		}

		IHome home = new Home();
		MetaDataResponseEvent meta;
		if (PDSecurityHelper.isUserMA()
				&& (sEvent.getCodeTableAgencyId() == null || sEvent.getCodeTableAgencyId().equals("")))
		{
			meta = home.findMeta(sEvent, CodetableReg.class);
		}
		else
		{

			meta = home.findMeta(sEvent1, CodetableReg.class);
		}

		if (meta != null && meta.getCount() > 2000)
		{
			sendMaxCountExceeded();

			CountInfoMessage infoEvent = new CountInfoMessage();
			infoEvent.setCount(meta.getCount());
			dispatch.postEvent(infoEvent);
			return;
		}
		else
		{
			Iterator codeRecordIterator;
			if (PDSecurityHelper.isUserMA()
					&& (sEvent.getCodeTableAgencyId() == null || sEvent.getCodeTableAgencyId().equals("")))
			{
				codeRecordIterator = CodetableReg.findAll(sEvent);
			}
			else
			{

				codeRecordIterator = CodetableReg.findAll(sEvent1);
			}

			while (codeRecordIterator.hasNext())
			{
				CodetableReg reg = (CodetableReg) codeRecordIterator.next();
				if (reg != null)
				{
					CodetableRecordResponseEvent resp = (CodetableRecordResponseEvent) aCreator.create(reg);
					dispatch.postEvent(resp);
				}
			}
		}
	}

	/**
	 * @param event
	 * @roseuid 45B94F5000C4
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 45B94F5000D1
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 45B94F5000D3
	 */
	public void update(Object anObject)
	{

	}

	protected void sendMaxCountExceeded()
	{
		ErrorResponseEvent errorResp = new ErrorResponseEvent();
		errorResp.setMessage("error.max.limit.exceeded");
		MessageUtil.postReply(errorResp);
	}

}
