//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceProviderServicesEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetServiceProvidersByLocationUnitEvent extends RequestEvent
{
    public void setJuvLocUnitId(int juvLocUnitId)
    {
	this.juvLocUnitId = juvLocUnitId;
    }

    private int juvLocUnitId;

    public GetServiceProvidersByLocationUnitEvent()
    {

    }

    /**
     * @return Returns the juvLocUnitId.
     */
    public int getJuvLocUnitId()
    {
	return juvLocUnitId;
    }

}