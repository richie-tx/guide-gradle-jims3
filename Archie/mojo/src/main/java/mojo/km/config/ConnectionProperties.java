package mojo.km.config;

/**
 * Responsible for housing connection meta data to be used to gain access to various data sources.
 *  
 */
public class ConnectionProperties extends GenericProperties
{
    public static final String CONNECTIONADAPTER = "connectionAdapter";

    public static final String DB2SCHEMA = "db2SchemaName";

    public static final String DRIVER = "driver";

    public static final String NAME = "name";

    public static final String PASSWORD = "password";

    public static final String POOL = "Pool";

    public static final String POOLING = "pooling";

    public static final String PORT = "port";

    public static final String REGION = "region";
    
    public static final String TESTURL = "testUrl";

    public static final String URL = "URL";

    public static final String USERID = "userID";

    public static final String YES = "yes";

    private String connectionAdapter;

    private String driver;

    private String name;

    private String password;

    private String pooling;

    public ConnectionPoolProperties poolProps;

    private String port;

    private String region;

    private String schema;

    private String testUrl;

    private String url;

    private String userId;

    public String getConnectionAdapter()
    {
        return this.connectionAdapter;
    }

    public String getDb2Schema()
    {
        return this.schema;
    }

    public String getDriver()
    {
        return this.driver;
    }

    /**
     * @return java.lang.String
     * @roseuid 404DA1FA0164
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * @return java.lang.String
     * @roseuid 404DA1FA0183
     */
    public String getPassword()
    {
        return this.password;
    }

    public String getPooling()
    {
        return this.pooling;
    }

    public ConnectionPoolProperties getPoolProperties()
    {
        return this.poolProps;
    }

    public String getPort()
    {
        return this.port;
    }

    public String getRegion()
    {
        return this.region;
    }

    /**
     * @return java.lang.String
     * @roseuid 404DA1FA016F
     */
    public String getURL()
    {
        return this.url;
    }

    /**
     * @return java.lang.String
     * @roseuid 404DA1FA0179
     */
    public String getUserID()
    {
        return this.userId;
    }

    /**
     * @param name
     * @roseuid 404DA1FA018E
     */
    public void setConnectionAdapter(String aConnectionAdapter)
    {
        this.connectionAdapter = aConnectionAdapter;
    }

    public void setDb2Schema(String aSchema)
    {
        this.schema = aSchema;
    }

    public void setDriver(String aDriver)
    {
        this.driver = aDriver;
    }

    /**
     * @roseuid 404DA1FA0165
     */
    public void setName(String aName)
    {
        this.name = aName;
    }

    /**
     * @param password
     * @roseuid 404DA1FA018C
     */
    public void setPassword(String aPassword)
    {
        this.password = aPassword;
    }

    public void setPooling(String aPooling)
    {
        this.pooling = aPooling;
    }

    public void setPoolProperties(ConnectionPoolProperties aPoolProps)
    {
        this.poolProps = aPoolProps;
    }

    public void setPort(String aPort)
    {
        this.port = aPort;
    }

    public void setRegion(String aRegion)
    {
        this.region = aRegion;
    }

    /**
     * @param theURL
     * @roseuid 404DA1FA0170
     */
    public void setURL(String aURL)
    {
        this.url = aURL;
    }

    /**
     * @param userID
     * @roseuid 404DA1FA017A
     */
    public void setUserID(String aUserID)
    {
        this.userId = aUserID;
    }

    /**
     * @param name
     * @param driver
     * @param url
     * @param userid
     * @param password
     * @param connectionAdapter
     * @roseuid 405883EE02AF
     */
    public void update(String name, String driver, String url, String userid, String password, String connectionAdapter,
            String CTGPort, String CTGServer)
    {

    }

    /**
     * @return Returns the testUrl.
     */
    public String getTestUrl()
    {
        return testUrl;
    }

    /**
     * @param testUrl
     *            The testUrl to set.
     */
    public void setTestUrl(String testUrl)
    {
        this.testUrl = testUrl;
    }
}
