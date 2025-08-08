//Source file: Z:/java.vob/CommonServiceLayer/logging/ContextLog.java

package mojo.km.logging.multicast;

import mojo.km.context.ContextManager;
//import MessagingLayer.MessageProcessing.*;

/**
 *Defines an interface for writing buffers to related log locations.
 *@author Eric A Amundson
 * @modelguid {6567F066-ECB9-44CC-BC75-83FB3AF9E096}
 */
public abstract class ContextLog {
	/** @modelguid {4EBD3251-65C2-447F-A912-FAE7A0C8E762} */
    public ContextLog() {
    }

    /**
     *    Write buffer of data to log.
     *    @param buffer - log message.
     *    @modelguid {60D7EBC2-7B04-4824-8F64-D456B32BDE75}
     */
    public abstract void write(byte[] buffer);

    /**
     * Load the appropriate log utility.
     *     @return factoried instance of Context Log
     *    @modelguid {6C24F5DB-A60F-44A8-8500-5489C7D077CB}
     */
    public static ContextLog getInstance() {
        load();
        return instance;
    }

	/** @modelguid {BAD8044E-FA02-4927-9E02-A4C56F17CA88} */
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

	/** @modelguid {3D2231D5-1C53-4523-BE5F-3B33ADF8786D} */
    static ContextLog instance = null;
	/** @modelguid {37AB8301-BD21-4F8B-A24F-234AC7EBFC2F} */
    static DummyLog dummy = new DummyLog();
	/** @modelguid {2D3158C2-6BA7-4E7B-87CC-2347627B1BC3} */
    static RollingFileLog rolling = new RollingFileLog();
	/** @modelguid {EDAA9CCF-8385-4546-9E1E-19D0505A158C} */
    static IntegratedLog integrated = new IntegratedLog();

	/** @modelguid {F1A5522B-4966-443D-9CAD-860379ECCF93} */
    static class DummyLog extends ContextLog {
        /**
         * @param buffer 
         * @modelguid {66681A45-ACB5-4CFC-922E-EF83C8229028}
         */
        public void write(byte[] buffer) {
        }
    }
}
