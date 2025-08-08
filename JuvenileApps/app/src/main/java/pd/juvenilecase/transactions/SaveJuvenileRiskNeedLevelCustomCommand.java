//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\SaveJuvenileRiskNeedLevelCommand.java

package pd.juvenilecase.transactions;

import messaging.juvenilecase.SaveJuvenileRiskNeedLevelCustomEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.common.util.StringUtil;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.PACTRiskNeedLevel;

public class SaveJuvenileRiskNeedLevelCustomCommand implements ICommand
{

	/**
	 * @roseuid 42A75896000B
	 */
	public SaveJuvenileRiskNeedLevelCustomCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42A731DC0318
	 */
	public void execute(IEvent event)
	{
		
		SaveJuvenileRiskNeedLevelCustomEvent saveEvent = (SaveJuvenileRiskNeedLevelCustomEvent) event;
		//create the new record with status current, the previous current record becomes not current
				
		PACTRiskNeedLevel pactRNLevel = new PACTRiskNeedLevel();
		pactRNLevel.setCaseFileId(saveEvent.getCaseFileId());
		pactRNLevel.setJuvenileNumber(saveEvent.getJuvenileNum());
		pactRNLevel.setReferralNumber(saveEvent.getReferralNumber());
		pactRNLevel.setRiskLvl(saveEvent.getRiskLvl());
		pactRNLevel.setNeedsLvl(saveEvent.getNeedsLvl());
		pactRNLevel.setLastPactDate(saveEvent.getLastPactDate());
		pactRNLevel.setStatus(saveEvent.getStatus());
		if( saveEvent.getPactId()!= null && !StringUtil.isEmpty(saveEvent.getPactId())){
		    pactRNLevel.setPactId(Integer.parseInt(saveEvent.getPactId()));
		}
		
		
		//Reset RISKNeed Flag.
		JuvenileCasefile casefile = null;
		if(saveEvent.getCaseFileId() != null && !(saveEvent.getCaseFileId().equals(""))) {
			casefile = JuvenileCasefile.find(saveEvent.getCaseFileId());
			casefile.setRiskNeed(false);
			new mojo.km.persistence.Home().bind(casefile);
			
		}
			
	}

	/**
	 * @param event
	 * @roseuid 42A731DC031A
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42A731DC0325
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42A731DC0327
	 */
	public void update(Object anObject)
	{

	}

}
