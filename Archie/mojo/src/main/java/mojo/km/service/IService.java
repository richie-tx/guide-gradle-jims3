//Source file: Z:/java.vob/MessagingLayer/MessageProcessing/Service.java

package mojo.km.service;

/**
 * Interface that defines a Service for data aquisition.
 * @modelguid {60AD3EED-5339-49CE-A35E-9ED7118320DD}
 */
public interface IService {
    /**
     *    Start the data aquisition service.
     *    @modelguid {C50DCCF4-3669-4681-9EBC-67B8BF7FEFC8}*/
    public void start();

    /**
     *    Stop the data aquisition service.
     *    @modelguid {DCF8FC91-1CDE-4E5C-9C65-1EBD6E0D865C}*/
    public void stop();

}
