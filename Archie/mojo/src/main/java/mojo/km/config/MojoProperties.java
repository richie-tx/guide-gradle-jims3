package mojo.km.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mojo.km.config.CalendaringProperties;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.naming.ServerNames;
import mojo.km.context.ContextManager;

public class MojoProperties
{

    static public final String CONTEXTSEPARATOR = ";";

    static public final String MEMBERSEPARATOR = ",";

    public final String DEFAULT_PROFILE = "Default";

    private String activeServer;

    private String resourceName;

    private boolean readImports = true;

    private String importFile;

    private static final String MOJO_CONFIG_FILE = System.getProperty("mojo.config");

    private HashMap servers;

    private HashMap entityMappings;

    private HashMap pfProperties;

    private HashMap services;

    private HashMap connections;

    private ServerProperties defaultServer;

    private HashMap eventServices;

    private HashMap commandServices;

    private AppProperties appProperties;

    private BuildProperties buildProperties;

    private FolderProperties folderProperties;

    private SecurityProperties securityProperties;

    private CalendaringProperties calendaringProperties;

    private PropertyBundleProperties propertyBundleProperties;

    private VersionableProperties versionableProperties;

    private StateMachineProperties stateMachineProperties;

    private CacheProperties cacheProperties;

	private ArrayList entityMappingsList;

    private static MojoProperties instance;

    private static HashMap testDataClassifications;
    
    public MojoProperties(String aConfigName)
    {
        this(aConfigName, true);
    }

    /**
     * 
     * @param aConfigName
     * 
     * @param shouldReadImports
     * 
     * @roseuid 404DA1FE00CA
     *  
     */
    public MojoProperties(String aConfigName, boolean shouldReadImports)
    {
        resourceName = aConfigName;
        readImports = shouldReadImports;
        clear();
    }

    public static MojoProperties getInstance(String aResourceName)

    {
        if (aResourceName == null)
        {
            aResourceName = "Mojo.xml";
        }

        if (instance == null || !aResourceName.equals(instance.resourceName))
        {
            boolean lReadImports = true;
            if (System.getProperty("mojo.config.imports") != null
                    && System.getProperty("mojo.config.imports").equals("false"))
            {
                lReadImports = false;
            }
            instance = new MojoProperties(aResourceName, lReadImports);
            try
            {
                instance.loadProperties();
            }
            catch (Exception e)
            {
                System.err.println("Could not load contents of " + aResourceName);
                e.printStackTrace(System.err);
            }
        }
        return instance;
    }

    /**
     * 
     * @return mojo.km.config.MojoProperties
     * 
     * @roseuid 404DA1FE00E9
     *  
     */
    public static MojoProperties getInstance()
    {
        if (instance == null)
        {
            instance = getInstance(MOJO_CONFIG_FILE);
        }

        return instance;
    }

    /**
     * 
     * @return java.lang.String
     * 
     * 
     * @roseuid 404DA1FE00F2
     *  
     */
    public String getName()
    {
        return resourceName;
    }

    /**
     * 
     * @throws Exception
     * 
     * @roseuid 404DA1FE00FC
     *  
     */

    public void loadProperties() throws Exception

    {

        clear();

        getAdapter().loadProperties();

    }

    /**
     * 
     * @param aResourceName
     * 
     * @throws Exception
     * 
     * @roseuid 404DA1FE011A
     *  
     */
    public void loadProperties(String aResourceName) throws Exception
    {
        clear();
        getAdapter(aResourceName).loadProperties();
    }

    /**
     * 
     * @return mojo.km.config.IMojoPropertyAdapter
     * 
     * @roseuid 404DA1FE0142
     *  
     */
    private IMojoPropertyAdapter getAdapter()
    {
        return getAdapter(resourceName);
    }

    /**
     * 
     * @param aResourceName
     * 
     * @return mojo.km.config.IMojoPropertyAdapter
     * 
     * @roseuid 404DA1FE014C
     *  
     */
    private IMojoPropertyAdapter getAdapter(String aResourceName)
    {
        IMojoPropertyAdapter lAdapter = new mojo.km.config.xml.XMLPropertyAdapter(this, aResourceName);
        lAdapter.readImports(readImports);
        return lAdapter;
    }

    public void clearEntityMappings()
    {
        entityMappings.clear();
    }

    public void clearPfProperties()
    {
        pfProperties.clear();
    }

    public void saveMapping(String importName)
    {
        getAdapter().saveMapping(importName);
    }

    /**
     * @roseuid 404DA1FE0157
     */
    public void saveProperties()
    {
        saveProperties(getImportFile());
    }

    /**
     * 
     * @param anImportName
     * 
     * 
     * @roseuid 404DA1FE0160
     *  
     */
    public void saveProperties(String anImportName)
    {
        getAdapter().saveProperties(anImportName);
    }

    public void savePfProperties()
    {
        savePfProperties(getImportFile());
    }

    /**
     * 
     * @param anImportName
     * 
     * @roseuid 404DA1FE0160
     *  
     */

    public void savePfProperties(String anImportName)
    {
        getAdapter().savePfProperties(anImportName);
    }

    /**
     * 
     * @param aServer
     * 
     * @roseuid 404DA1FE016A
     *  
     */
    public void addServer(ServerProperties aServer)
    {
        String lServerName = aServer.getName();
        servers.put(lServerName, aServer);
    }

    /**
     * 
     * @param entityMappingProperty
     * 
     * @roseuid 404DA1FE0174
     *  
     */
    public String addEntityMapping(EntityMappingProperties entityMappingProperty, String contextKey)
    {
    	StringBuilder buffer = new StringBuilder();
    	buffer.append(contextKey);
        if (!contextKey.equals(""))
        {
        	buffer.append(CONTEXTSEPARATOR);
        }

        buffer.append(contextKey);
        buffer.append(MEMBERSEPARATOR);
        buffer.append(entityMappingProperty.getMemberName());

        contextKey = buffer.toString();
        
        entityMappings.put(contextKey, entityMappingProperty);
        entityMappingsList.add(entityMappingProperty);

        return contextKey;

    }

    public void addTestDataClassification(TestDataClassificationProperties tProps)
    {
        testDataClassifications.put(tProps.getName(), tProps);
    }

    public TestDataClassificationProperties getTestDataClassification(String name)
    {
        return (TestDataClassificationProperties) testDataClassifications.get(name);
    }

    /**
     * 
     * @param entityMappingProperty
     * 
     * @roseuid 404DA1FE0174
     *  
     */
    public void addEntityMapping(EntityMappingProperties entityMappingProperty)
    {
    	entityMappingsList.add(entityMappingProperty);
        entityMappings.put(entityMappingProperty.getContextKey(), entityMappingProperty);
    }

    public void addPfProperty(PFProperties pfProperty)
    {

    }

    /**
     * 
     * @param connectionProperty
     * 
     * 
     * @roseuid 404DA1FE017F
     *  
     */
    public void addConnection(ConnectionProperties connection)
    {
        connections.put(connection.getName(), connection);

    }

    /**
     * 
     * @param connectionName
     * 
     * @return mojo.km.config.ConnectionProperties
     * 
     * 
     * @roseuid 404DA1FE0192
     *  
     */
    public ConnectionProperties getConnectionProperties(String connectionName)
    {
        return (ConnectionProperties) connections.get(connectionName);
    }

    public Collection getAllConnectionProperties()
    {
        return connections.values();

    }

    public EntityMappingProperties getEntityMap(String entityName)
    {
        return (EntityMappingProperties) entityMappings.get(entityName);
    }
    
    public Map getEntityMapMap()
    {
    	return this.entityMappings;
    }

    public PFProperties getPfProperties(String pfName)
    {
        return (PFProperties) pfProperties.get(pfName);
    }

    public EntityMappingProperties getBufferMappingByFieldName(String aField)
    {
        EntityMappingProperties eProps = null;

        Iterator i = this.entityMappings.keySet().iterator();

        boolean done = false;

        while (i.hasNext() || done == true)
        {
            EntityMappingProperties tempProps = (EntityMappingProperties) entityMappings.get(i.next());
            if (tempProps.getContextKey().startsWith("BUFFER_MAPPING::"))
            {
                Iterator q = tempProps.getQueryCallbacks();
                while (q.hasNext() || done == true)
                {
                    EventQueryProperties qProps = (EventQueryProperties) q.next();
                    Iterator f = qProps.getFieldsIterator();
                    while (f.hasNext() || done == true)
                    {
                        FieldMappingProperties fProps = (FieldMappingProperties) f.next();
                        if (fProps.getName().equals(aField))
                        {
                            eProps = tempProps;
                        }
                    }
                }
            }
        }

        return eProps;
    }

    /**
     * 
     * @return Iterator
     * 
     * @roseuid 404DA1FE01A7
     *  
     */

    public Iterator getEntityMaps()
    {
        return entityMappings.values().iterator();
    }
    
    public List getEntityMapsList()
    {
    	return entityMappingsList;
    }

    public Iterator getPFProperties()
    {
        return pfProperties.values().iterator();
    }

    /**
     * 
     * @param aService
     * 
     * @roseuid 404DA1FE01B0
     *  
     */
    public void addService(ServiceProperties aService)
    {
        String lServiceName = aService.getName();
        String lEventName = aService.getEventName();
        services.put(lServiceName, aService);
        eventServices.put(lEventName, aService);
        for (Iterator i = aService.getCommands(); i.hasNext();)
        {
            String lCommandName = (String) i.next();

            if (lCommandName != null)
            {
                commandServices.put(lCommandName, aService);
            }
        }
    }

    /**
     * 
     * @param aServerName
     * 
     * 
     * @roseuid 404DA1FE01C5
     *  
     */
    public void removeServer(String aServerName)
    {
        servers.remove(aServerName);
    }

    /**
     * 
     * @return mojo.km.config.ServerProperties
     * 
     */
    public ServerProperties getServer()
    {
        String lServerName = getActiveServerName();
        if (lServerName != null)
        {
            return getServer(lServerName);
        }
        else
        {
            return null;
        }
    }

    /**
     * 
     * @param aServerName
     * 
     * @return mojo.km.config.ServerProperties
     * 
     * @roseuid 404DA1FE0200
     *  
     */
    public ServerProperties getServer(String aServerName)
    {
        return (ServerProperties) servers.get(aServerName);
    }

    /**
     * 
     * @return Iterator
     * 
     * @roseuid 404DA1FE020A
     *  
     */
    public Iterator getServers()
    {
        return servers.values().iterator();
    }

    /**
     * 
     * @param aServiceName
     * 
     * @return mojo.km.config.ServiceProperties
     * 
     * @roseuid 404DA1FE0214
     *  
     */

    public ServiceProperties getService(String aServiceName)
    {
        return (ServiceProperties) services.get(aServiceName);
    }

    /**
     * 
     * @return mojo.km.config.ServerProperties
     * 
     * @modelguid {361C4D25-4C29-44D4-B6B1-378F0639A03B}
     * 
     * @roseuid 404DA1FE0228
     *  
     */

    public ServerProperties getActiveServer()

    {

        return getServer();

    }

    /**
     * 
     * @return java.lang.String
     * 
     * @roseuid 404DA1FE0232
     *  
     */
    public String getActiveServerName()
    {
        String serverName = System.getProperty(ContextManager.MOJO_SERVER_NAME_PROP);
        if (serverName == null || serverName.equals(""))
        {
            serverName = ServerNames.GUI;
        }
        activeServer = serverName;
        return activeServer;

    }

    /**
     * 
     * @param activeServerName
     * 
     * @modelguid {EE8E1AD0-187A-44E0-88D2-840CC5C723C4}
     * 
     * @roseuid 404DA1FE023C
     *  
     */

    public void setActiveServerName(String activeServerName)
    {
        activeServer = activeServerName;

    }

    /**
     * 
     * @return AppProperties
     * 
     * @modelguid {881E9A87-3EEA-4835-9860-5BC5BB4765AA}
     * 
     * @roseuid 404DA1FE0246
     *  
     */

    public AppProperties getAppProperties()
    {
        return appProperties;
    }

    /**
     * 
     * @return AppProperties
     * 
     * 
     * @roseuid 404DA1FE0250
     *  
     */
    public PropertyBundleProperties getPropertyBundleProperties()
    {
        return propertyBundleProperties;
    }

    /**
     * 
     * @return BuildProperties
     * 
     * 
     * @roseuid 404DA1FE025A
     *  
     */
    public BuildProperties getBuildProperties()
    {
        return buildProperties;
    }

    /**
     * 
     * @return FolderProperties
     * 
     * 
     * @roseuid 404DA1FE0264
     *  
     */
    public FolderProperties getFolderProperties()
    {
        return folderProperties;
    }

    public SecurityProperties getSecurityProperties()
    {

        return securityProperties;

    }

    /**
     * 
     * Returns the calendaring properties.
     * 
     * @return CalendaringProperties
     *  
     */

    public CalendaringProperties getCalendaringProperties()
    {
        return calendaringProperties;
    }

    /**
     * 
     * @return Iterator
     * 
     * 
     * @roseuid 404DA1FE0296
     *  
     */
    public Iterator getServices()
    {
        return services.values().iterator();
    }

    /**
     * 
     * @return Iterator
     * 
     * 
     * @roseuid 404DA1FE0296
     *  
     */
    public ServiceProperties getCommandService(String aCommand)
    {
        return (ServiceProperties) commandServices.get(aCommand);
    }

    /**
     * 
     * @return VersionableProperties
     * 
     * @roseuid 404DA1FE02A0
     *  
     */
    public VersionableProperties getVersionableProperties()
    {
        return versionableProperties;
    }

    /**
     * 
     * @return StateMachineProperties
     * 
     * @modelguid {D5F73D11-6DCA-40E6-9CEC-BFF6E086B9C2}
     * 
     * @roseuid 404DA1FE02AA
     *  
     */

    public StateMachineProperties getStateMachineProperties()
    {
        return stateMachineProperties;

    }

    /**
     * 
     * Returns the defaultServer.
     * 
     * @return ServerProperties
     * 
     * @roseuid 404DA1FE02C8
     *  
     */
    public ServerProperties getDefaultServer()
    {
        return defaultServer;
    }

    /**
     * 
     * Sets the defaultServer.
     * 
     * @param defaultServer
     *            The defaultServer to set
     * 
     * @param aDefaultServer
     * 
     * @roseuid 404DA1FE02D2
     *  
     */
    public void setDefaultServer(ServerProperties aDefaultServer)
    {
        defaultServer = aDefaultServer;
    }

    /**
     * 
     * Returns the importFile.
     * 
     * @return String
     * 
     * @roseuid 404DA1FE02DD
     *  
     */
    public String getImportFile()
    {
        return importFile;
    }

    /**
     * 
     * Sets the importFile.
     * 
     * @param importFile
     *            The importFile to set
     * 
     * @roseuid 404DA1FE02E6
     *  
     */

    public void setImportFile(String importFile)
    {
        this.importFile = importFile;
    }

    /**
     * 
     * @param aServiceName
     * 
     * @return java.lang.String
     * 
     * @roseuid 404DA1FE02FA
     *  
     */
    public String getServerForService(String aServiceName)
    {
        for (Iterator i = getServers(); i.hasNext();)
        {
            ServerProperties lServer = (ServerProperties) i.next();

            for (Iterator j = lServer.getServiceRefs(); j.hasNext();)
            {
                ServiceRefProperties lServiceRef = (ServiceRefProperties) j.next();

                if (lServiceRef.getName().equals(aServiceName))
                {
                    return lServer.getName();
                }
            }
        }

        ServiceProperties sProperties = MojoProperties.getInstance().getService(aServiceName);

        if (sProperties != null && sProperties.getEventName().toLowerCase().indexOf("proxy") > -1)
        {
            return "Proxy";
        }

        return "None";

    }

    /**
     * 
     * @param cacheProperties
     * 
     * @roseuid 404DA1FE017F
     *  
     */
    public void setCacheProperties(CacheProperties cacheProps)
    {
        cacheProperties = cacheProps;
    }

    /**
     * 
     * @return mojo.km.config.CacheProperties
     *  
     */
    public CacheProperties getCacheProperties()
    {
        return cacheProperties;
    }

    /**
     * 
     * @roseuid 404DA1FE0304
     *  
     */
    public void clear()
    {
        appProperties = new AppProperties();
        buildProperties = new BuildProperties();
        folderProperties = new FolderProperties();
        propertyBundleProperties = new PropertyBundleProperties();
        versionableProperties = new VersionableProperties();
        stateMachineProperties = new StateMachineProperties();
        defaultServer = new ServerProperties();
        cacheProperties = new CacheProperties();
        servers = new HashMap();
        services = new HashMap();
        eventServices = new HashMap();
        commandServices = new HashMap();
        connections = new HashMap();
        entityMappings = new HashMap();
        entityMappingsList = new ArrayList();
        pfProperties = new HashMap();
        securityProperties = new SecurityProperties();
        calendaringProperties = new CalendaringProperties();
        testDataClassifications = new HashMap();
    }

    /**
     * 
     * @modelguid {8B561397-FBFF-40FC-90CA-7CE75C501C26}
     * 
     * @roseuid 404DA1FE030E
     *  
     */
    public void clearServices()
    {
        services = new HashMap();
    }

    public ServiceProperties getServiceForEvent(IEvent anEvent)
    {
        if (anEvent == null)
        {
            return null;
        }

        String lEventName = anEvent.getClass().getName();

        return (ServiceProperties) eventServices.get(lEventName);
    }

    public ServiceProperties getServiceForCommand(ICommand aCommand)
    {
        if (aCommand == null)

            return null;

        String lCommandName = aCommand.getClass().getName();

        return (ServiceProperties) eventServices.get(lCommandName);
    }

    public String getServiceNameForEvent(IEvent anEvent)
    {
        ServiceProperties lServer = getServiceForEvent(anEvent);

        if (lServer != null)
        {
            return lServer.getName();
        }
        else
        {
            return "None";
        }
    }

    public String getServiceNameForCommand(ICommand aCommand)
    {
        ServiceProperties lServer = getServiceForCommand(aCommand);

        if (lServer != null)
        {
            return lServer.getName();
        }
        else
        {
            return "None";
        }
    }
}
