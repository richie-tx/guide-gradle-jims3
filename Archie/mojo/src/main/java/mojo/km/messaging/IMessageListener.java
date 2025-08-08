package mojo.km.messaging;

/** @modelguid {CAB079E4-D7FA-4495-A0AB-EF60163699B4} */
public interface IMessageListener {
    /**
     *    @param msgEvent
     * @param msg
     * @modelguid {690F6CF2-604B-4991-9010-8E1E64B12E8D}
     */
    public void messageReceived(IMessageEvent msgEvent, Object msg);
}
