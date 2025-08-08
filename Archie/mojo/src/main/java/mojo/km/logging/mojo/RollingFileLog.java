//Source file: Z:/java.vob/PresentationLayer/UserInterfaceContext/UILog.java

package mojo.km.logging.mojo;

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
 * @modelguid {1DC576E0-1913-462E-975E-A0D7D59F0909}
 */
public class RollingFileLog extends ContextLog {
    /**
     *	Default constructor
     * @modelguid {CC1C7B49-3D9D-4865-8B0C-C627A186DAFF}
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
     *    @modelguid {F12B7085-4958-4338-8C52-D3D2BEA6B13E}
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

	/** @modelguid {A74B7A5D-6B50-4948-8561-0F287894916C} */
    private PrintStream dailyOutputStream;
	/** @modelguid {4E18E9DD-627C-4C5E-A72D-4B7D948F810B} */
    private SimpleDateFormat timeFormat;
}
