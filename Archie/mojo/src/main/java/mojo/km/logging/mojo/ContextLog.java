//Source file: Z:/java.vob/CommonServiceLayer/logging/ContextLog.java

package mojo.km.logging.mojo;

import mojo.km.context.ContextManager;
//import MessagingLayer.MessageProcessing.*;

/**
 *Defines an interface for writing buffers to related log locations.
 *@author Eric A Amundson
 * @modelguid {6C40EC35-1B84-4663-ADD8-6C213F67C92D}
 */
public abstract class ContextLog {
	/** @modelguid {6F62BE3D-1AAD-4FFD-88BC-16DE32CA3C31} */
    public ContextLog() {
    }

    /**
     *    Write buffer of data to log.
     *    @param buffer - log message.
     *    @modelguid {94940BDE-8DBA-46ED-BA47-E0C6CDB713CC}
     */
    public abstract void write(byte[] buffer);

    /**
     * Load the appropriate log utility.
     *     @return factoried instance of Context Log
     *    @modelguid {91C7E0F4-4103-4556-962B-E87175E38A21}
     */
    public static ContextLog getInstance() {
        load();
        return instance;
    }

	/** @modelguid {E2CEBFC8-EE62-4DF3-8BD6-FAEF9DFD163F} */
    private static void load() {
        ContextLog retVal = null;
        if (!ContextManager.isSet()) {
            retVal = dummy;
        } else {
            if (ContextManager.getServerName().startsWith("Swing")) {
                retVal = rolling;
            } else {
                retVal = integrated;
            }
        }
        instance = retVal;
    }

	/** @modelguid {6156B503-E819-4B5B-BEB5-1B83A1BE465D} */
    static ContextLog instance = null;
	/** @modelguid {B246D446-FF86-44C1-B95A-ACBAFE84A4B5} */
    static DummyLog dummy = new DummyLog();
	/** @modelguid {B8E87303-C974-4B6D-83CF-0B725985526D} */
    static RollingFileLog rolling = new RollingFileLog();
	/** @modelguid {4A3A0CC0-928F-469A-8B44-F66EEE48A035} */
    static IntegratedLog integrated = new IntegratedLog();

	/** @modelguid {CA8FD6D4-8C91-482D-AE2A-6ED53AF3679A} */
    static class DummyLog extends ContextLog {
        /**
         * @param buffer 
         * @modelguid {350DD143-D81A-466E-81C4-FCCF211AA628}
         */
        public void write(byte[] buffer) {
        }
    }
}
