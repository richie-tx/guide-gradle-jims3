package mojo.km.messaging;

/** @modelguid {216906A6-F4B5-4AB5-818D-101670FECCDE} */
public interface IExceptionListener {
    /**
     *    @param eMsg
     * @modelguid {4E2E29DE-7202-41D5-BBB4-F55FAFDC2378}
     */
    public void exceptionReceived(IMapMessage eMsg);
}
