package messaging.family;

import java.sql.Timestamp;

import mojo.km.messaging.RequestEvent;

/**
 * Class GetFamilyConstellationGuardianFinancialEvent.
 *  
 * @author  mchowdhury
 * @version  1.0.0
 */
public class GetFamilyConstellationGuardianFinancialEvent extends mojo.km.messaging.RequestEvent {

	private int financialId;
	

    /**
     * @roseuid 43299A4D0157
     */
    public GetFamilyConstellationGuardianFinancialEvent() {

    }
	/**
	 * @return
	 */
	public int getFinancialId()
	{
		return financialId;
	}

	/**
	 * @param i
	 */
	public void setFinancialId(int i)
	{
		financialId = i;
	}
} 
