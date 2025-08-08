//Source file: z:/java.vob/CommonServiceLayer/logging/LogUtilities.java

package mojo.km.logging;

import mojo.km.utilities.TuxedoClient;

/**
 *    Utility class that hold various means of logging application status.
 * @author Eric A Amundson
 * @modelguid {2563F60E-B61D-44BE-8E13-03AB30D982BF}
 */
public class LogUtilities {
	/** @modelguid {23253182-A636-48E5-AD68-578F3F042EBB} */
    public LogUtilities() { }

    /**
 *     Utility to print messages to a tuxedo log file.
 *     @param msg - printed message.
 * @modelguid {36EE5EEF-2F70-485F-A429-3FC9E790F2B0}
    */
    public static void contextMessage(String msg) {
        TuxedoClient.getInstance().contextMessage(msg);
    }
}
