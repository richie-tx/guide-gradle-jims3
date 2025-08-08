//Source file: Z:/java.vob/PresentationLayer/UserInterfaceContext/UILog.java

package mojo.km.logging.multicast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.PrintStream;

/**
 *Opens a file based upon the current date.  check current data periodically to
 *ensure the log file is related to current date.  Creates new log file when date
 *has passed midnight.
 *
 *@author Eric A Amundson
 *@version 1.0
 * @modelguid {8583B7FA-3C07-425E-8159-026C07E383CA}
 */
public class RollingFileLog extends ContextLog {
    /**
     *	Default constructor
     * @modelguid {2E86CB95-FDE7-44D7-A699-D52261E00F6A}
     */
    public RollingFileLog() {
        try {
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdir();
            }
            SimpleDateFormat dFormat = new SimpleDateFormat("yyyyMMdd");
            String dateStr = dFormat.format(
                new Date());
            FileOutputStream fStream = new FileOutputStream("logs/ULOG" + dateStr + ".log", true);
            dailyOutputStream = new PrintStream( fStream );
            timeFormat = new SimpleDateFormat("hh:mm:ss");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *	Write a byte buffer to the log file as a new log event.
     *
     *	@param buffer - buffer that has log info.
     *
     *    @modelguid {C3ECCE11-6C97-45F8-95D9-4AB4CB6DFBBD}
     */
    public void write(byte[] buffer) {
        //String output = new String(buffer);
        String logTime = timeFormat.format(
            new Date()) + " : ";
        try {
            if (buffer.length > 2) {
                dailyOutputStream.write(logTime.getBytes());
            }
            dailyOutputStream.write( buffer );
            dailyOutputStream.println();
        } catch (IOException e) { }
    }

	/** @modelguid {5EF8B340-CB7A-4578-9828-F6E65A54F25D} */
    private PrintStream dailyOutputStream;
	/** @modelguid {A00AF81D-F3AC-45DC-8AA0-A5C9F6F92EFA} */
    private SimpleDateFormat timeFormat;
}
