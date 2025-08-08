//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\transactions\\GetAgenciesCommand.java

package pd.contact.agency.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDContactConstants;
import pd.contact.PDContactHelper;
import pd.contact.agency.Agency;
import pd.contact.agency.Department;
import pd.contact.agency.helper.AgencySimpleListBuilder;
import pd.pattern.IBuilder;

/**
 * @author dgibler
 *
 */
public class GetAllAgenciesCommand implements ICommand
{

	/**
	@roseuid 4107F7FF02BA
	 */
	public GetAllAgenciesCommand()
	{

	}

	/**
	@param event
	@roseuid 4107F6F00208
	 */
	public void execute(IEvent event)
	{/*
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator agencies = Agency.findAll();
		
		IBuilder builder = new AgencySimpleListBuilder(agencies);
		builder.build();
		dispatch.postEvent((IEvent) builder.getResult());	*/	//87191
	}

	/**
	@param event
	@roseuid 4107F6F0020A
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	@param event
	@roseuid 4107F6F0020C
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	@param updateObject
	@roseuid 4107F7FF02CE
	 */
	public void update(Object updateObject)
	{

	}
}