package mojo.km.logging;

import java.net.URL;

import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

/**
 * @author Jim Fisher
 */
public final class LogUtil
{
    private static Level DEFAULT_LEVEL = Level.DEBUG;

    private static final String LOG_LAYOUT_PROPERTY = "jims2.log.layout";
    
    private static final String LOG_MAINFRAME = "jims2.log.mainframe";

    private static final String JIMS_LOG_LAYOUT = "%p %d{yyyyMMdd HH:mm:ss} [%t] %m%n";

    public static final String EXECUTE_PROCESS = "executeProcess";

    public static final int EXECUTE_PROCESS_LEN = EXECUTE_PROCESS.length();

    public static final String EXECUTE_QUERY = "executeQuery";

    public static final int EXECUTE_QUERY_LEN = EXECUTE_QUERY.length();

    public static final String EXECUTE_SERVICE = "executeService";

    public static final int EXECUTE_SERVICE_LEN = EXECUTE_SERVICE.length();

    public static final String EXECUTE_UPDATE = "executeUpdate";

    public static final int EXECUTE_UPDATE_LEN = EXECUTE_UPDATE.length();

    private static final String JIMS_LOG_LEVEL_PROPERTY = "jims2.log.level";

    private static final String LOG_PROPERTIES_FILE = "log4j.properties";

    private static final String LOGGER_NAME = "jims2";

    private static Logger logger = Logger.getLogger(LOGGER_NAME);

    static
    {
        init();
    }

    public static boolean isTraceEnabled()
    {
        return logger.isTraceEnabled();
    }

    public static boolean isDebugEnabled()
    {
        return logger.isDebugEnabled();
    }

    public static Logger getLogger()
    {
        return logger;
    }

    private static void init()
    {
        URL propertyUrl = Loader.getResource(LOG_PROPERTIES_FILE);
        if (propertyUrl == null)
        {
            System.out.println("logger properties file not found: " + LOG_PROPERTIES_FILE);
        }
        else
        {
            PropertyConfigurator.configure(propertyUrl);
        }
        
        initLevel();
        
        initLayout();
    }

    private static void initLayout()
    {
        String layoutString = System.getProperty(LOG_LAYOUT_PROPERTY);

        if (layoutString == null)
        {
            String hasMainframeLog = System.getProperty(LOG_MAINFRAME);
            if("true".equalsIgnoreCase(hasMainframeLog))
            {
                layoutString = JIMS_LOG_LAYOUT;
            }
        }
        
        if(layoutString != null)
        {
            Layout layout = new PatternLayout(layoutString);
            Appender appender = Logger.getRootLogger().getAppender(LOGGER_NAME);
            if(appender == null)
            {
                System.err.println(LOGGER_NAME + " logger has not been configured properly.  Check server property settings.");
            }
            appender.setLayout(layout);
        }
    }

    /**
     *  
     */
    private static void initLevel()
    {
        String logLevel = System.getProperty(JIMS_LOG_LEVEL_PROPERTY);

        if (logLevel != null)
        {
            if (Level.DEBUG.toString().equals(logLevel))
            {
                logger.setLevel(Level.DEBUG);
            }
            else if (Level.ERROR.toString().equals(logLevel))
            {
                logger.setLevel(Level.ERROR);
            }
            else if (Level.FATAL.toString().equals(logLevel))
            {
                logger.setLevel(Level.FATAL);
            }
            else if (Level.INFO.toString().equals(logLevel))
            {
                logger.setLevel(Level.INFO);
            }
            else if (Level.OFF.toString().equals(logLevel))
            {
                logger.setLevel(Level.OFF);
            }
            else if (Level.TRACE.toString().equals(logLevel))
            {
                logger.setLevel(Level.TRACE);
            }
            else if (Level.WARN.toString().equals(logLevel))
            {
                logger.setLevel(Level.WARN);
            }
        }
        else
        {
        	logger.setLevel(DEFAULT_LEVEL);
        }
    }

    /**
     * Logs a basic text message at the specified logging level on the default logger.
     * 
     * @param aLevel
     *            the level to log this message at
     * @param aMessage
     *            the message to log
     */
    public static void log(Level aLevel, String aMessage)
    {
        logger.log(aLevel, aMessage);
    }

    public static void log(Level aLevel, Throwable t)
    {
        logger.log(aLevel, t.getMessage(), t);
    }

    public static void main(String[] args)
    {
        System.setProperty("mojo.config", "multisource.xml");

        LogUtil.log(Level.DEBUG, "test");
    }

    /**
     * Private default constructor ensures that this remains utility class that cannot be instantiated.
     */
    private LogUtil()
    {
    }

}
