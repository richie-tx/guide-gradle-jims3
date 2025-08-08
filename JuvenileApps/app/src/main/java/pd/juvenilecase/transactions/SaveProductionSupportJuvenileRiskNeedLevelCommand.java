//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\SaveJuvenileRiskNeedLevelCommand.java

package pd.juvenilecase.transactions;

import org.apache.commons.lang.StringUtils;

import messaging.juvenilecase.SaveProductionSupportJuvenileRiskNeedLevelEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.PACTRiskNeedLevel;

public class SaveProductionSupportJuvenileRiskNeedLevelCommand implements ICommand
{

    /**
     * @roseuid 42A75896000B
     */
    public SaveProductionSupportJuvenileRiskNeedLevelCommand()
    {

    }

    /**
     * @param event
     * @roseuid 42A731DC0318
     */
    public void execute(IEvent event)
    {

	SaveProductionSupportJuvenileRiskNeedLevelEvent saveEvent = (SaveProductionSupportJuvenileRiskNeedLevelEvent) event;

	PACTRiskNeedLevel pactfile = null;
	pactfile = PACTRiskNeedLevel.find(saveEvent.getRiskNeedLvlId());
	if (pactfile != null)
	{

	    pactfile.setCaseFileId(saveEvent.getCaseFileId());
	    //pactfile.setJuvenileNumber(saveEvent.getJuvenileNum());
	    pactfile.setReferralNumber(saveEvent.getReferralNumber());
	    pactfile.setRiskLvl(saveEvent.getRiskLvl());
	    pactfile.setNeedsLvl(saveEvent.getNeedsLvl());
	    pactfile.setLastPactDate(saveEvent.getLastPactDate());
	    pactfile.setRiskNeedLvlId(saveEvent.getRiskNeedLvlId());
	    pactfile.setStatus(saveEvent.getStatus());
	    if (StringUtils.isEmpty(saveEvent.getPactId()))
	    {
		pactfile.setPactId(0);
	    }else {
		
		pactfile.setPactId( Integer.parseInt(saveEvent.getPactId()) );
	    }
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
