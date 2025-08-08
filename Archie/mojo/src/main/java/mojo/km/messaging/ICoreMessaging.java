package mojo.km.messaging;

import mojo.km.messaging.exception.*;

/** @modelguid {D3D289F0-4478-468C-8C5A-D505F22AF962} */
public interface ICoreMessaging {
    /**
     *    @param listener
     * @modelguid {D8AD30F9-71AC-47FE-9A6F-FAFBD06785EE}
     */
    public void addExceptionListener(IExceptionListener listener);

    /**
     *    @modelguid {FAF12C65-C473-4A70-958B-82EDD3A60529}
     */
    public void close();

    /**
     *    @param sID
     * @throws InvalidSessionException
     * @modelguid {A5860CEF-86F4-4D61-8D82-3CA1A2BB8FFE}
     */
    public void closeSession(String sID) throws InvalidSessionException;

    /**
     *    @param sID
     * @throws InvalidSessionException
     * @modelguid {257B2AD3-6CFE-4E8F-99C1-3640CB1CE714}
     */
    public void commit(String sID) throws InvalidSessionException;

    /**
     * Insert the method's description here.
     * Creation date: (6/20/00 1:19:56 PM)
     * @param type java.lang.String
     * @return <{Object}>
     * @modelguid {1DDA0F37-F5AA-40A6-B045-5E44FCC31687}
     */
    public Object createJMSMessage(String type);

    /**
     *    @return
     * @modelguid {EC501B9C-DDC1-4AA6-A903-EDF8E412D702}
     */
    public String getClientID();

    /**
     *    @return
     * @modelguid {A5C9A201-236A-4CA9-A347-3C53618A6517}
     */
    public IMapMessage getConnectionInformation();

    /**
     *    @param sID
     * @param selectorName
     * @return
     * @modelguid {1D19187A-B240-40E6-BCC0-6B579D9746C6}
     */
    public IMessageListener getMessageListener(String sID, String selectorName);

    /**
     *    @param sID
     * @return
     * @throws InvalidSessionException
     * @modelguid {C0DF2257-2823-463E-B4FF-F259D4D595D5}
     */
    public boolean isTransacted(String sID) throws InvalidSessionException;

    /**
     *    @param transacted
     * @return
     * @modelguid {6CCAD7C6-BF66-4A93-81FC-36328159B975}
     */
    public String openSession(boolean transacted);

    /**
     *    @modelguid {350FB135-93AD-4B30-8B0A-CA4203ECCF8C}
     */
    public void recover();

    /**
     *    @param sID
     * @throws InvalidSessionException
     * @modelguid {3682C3C9-6C4B-4AB1-BE11-71BFE91C6765}
     */
    public void rollback(String sID) throws InvalidSessionException;

    /**
     *    @modelguid {DFBEC36D-6B1B-4B7E-905F-E7C3FDB12E82}
     */
    public void run();
}
