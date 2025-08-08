package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import messaging.family.GetFamilyConstellationGuardianEvent;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.JuvenileFamilyHelper;

/**
 * Class GetFamilyConstellationGuardianCommand.
 *  
 * @author  mchowdhury
 * @version  1.0.0
 */
public class GetFamilyConstellationGuardianCommand
	implements mojo.km.context.ICommand, mojo.km.transaction.ReadOnlyTransactional
{

	/**
	 * @roseuid 432998C800AB
	 */
	public GetFamilyConstellationGuardianCommand()
	{

	} 
	
	/**
	 *  
	 * @param event @roseuid 432997A90244
	 */
	public void execute(IEvent event)
	{
		GetFamilyConstellationGuardianEvent reqEvent = (GetFamilyConstellationGuardianEvent) event;
		Iterator famIter = FamilyConstellation.findAll(reqEvent);
		while (famIter.hasNext())
		{
			FamilyConstellation fc = (FamilyConstellation) famIter.next();
			JuvenileFamilyHelper.sendFamilyConstellationGuardianEvent(fc);
		}
	} 
	
	/**
	 *  
	 * @param event @roseuid 432997A90246
	 */
	public void onRegister(IEvent event)
	{

	} 
	/**
	 *  
	 * @param event @roseuid 432997A90252
	 */
	public void onUnregister(IEvent event)
	{

	} 
	
	/**
	 *  
	 * @param anObject @roseuid 432997A90254
	 */
	public void update(Object anObject)
	{

	} 
} 
