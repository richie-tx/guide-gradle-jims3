package mojo.km.messaging;

/** @modelguid {4876F5A0-E689-4F40-B523-5E0852E7E98D} */
public class UITestEvent extends RequestEvent {
	/** @modelguid {44A5427B-EA79-4D8A-91A2-4C15C77E12EA} */
    private IEvent postedEvent;

    /**
     * @return 
     * @modelguid {4BC3C5AB-2DAE-4280-86AF-ED44513C0812}
     */
    public IEvent getPostedEvent(){ return postedEvent; }

    /**
     * @param postedEvent 
     * @modelguid {EFB3DFC1-249D-4B84-8B23-D68D11120A6D}
     */
    public void setPostedEvent(IEvent postedEvent){ this.postedEvent = postedEvent; }
}