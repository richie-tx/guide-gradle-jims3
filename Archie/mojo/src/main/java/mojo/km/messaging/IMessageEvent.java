package mojo.km.messaging;

/**
 * 	Interface for MessageEvent class which provides methods to retrieve header,
 * 	property, and message source information.
 *
 * 	@author: D. Marple
 * @modelguid {39331700-B273-4949-9384-7278F0AA8CB2}
 */
public interface IMessageEvent {
    /**
     *    	Method to get message JMS message header information
     *    	@return
     * @modelguid {FAE4BA3E-C52F-4721-BC47-F5D20951C80B}
     */
    public IMapMessage getHeaderInformation();

    /**
     *    	Method to get JMS message property information
     *    	@return
     * @modelguid {AABAA4B9-4249-4E2B-9F71-E2B0C24DC9B9}
     */
    public IMapMessage getPropertyInformation();

    /**
     *    	Method to identify and return the source
     *    	@return
     * @modelguid {05EBE525-3E09-4C38-B84E-F6ADC815630F}
     */
    public String getSource();
}
