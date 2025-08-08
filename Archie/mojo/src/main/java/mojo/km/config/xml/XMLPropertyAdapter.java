package mojo.km.config.xml;

import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import org.jdom.Document;
import org.jdom.Element;

import mojo.km.config.CalendaringProperties;
import mojo.km.config.AppProperties;
import mojo.km.config.BuildProperties;
import mojo.km.config.CacheProperties;
import mojo.km.config.ConnectionPoolProperties;
import mojo.km.config.ConnectionProperties;
import mojo.km.config.DispatchProperties;
import mojo.km.config.EntityMappingProperties;
import mojo.km.config.FolderProperties;
import mojo.km.config.GenericProperties;
import mojo.km.config.IMojoPropertyAdapter;
import mojo.km.config.LogProperties;
import mojo.km.config.MojoProperties;
import mojo.km.config.PropertyBundleProperties;
import mojo.km.config.SecurityProperties;
import mojo.km.config.ServerProperties;
import mojo.km.config.ServiceProperties;
import mojo.km.config.ServiceRefProperties;
import mojo.km.config.SessionProperties;
import mojo.km.config.StateMachineProperties;
import mojo.km.config.TestDataClassificationProperties;
import mojo.km.config.URLResource;
import mojo.km.config.VersionableProperties;
import mojo.km.naming.ConnectionConstants;
import mojo.km.utilities.XMLManager;

public class XMLPropertyAdapter implements IMojoPropertyAdapter
{
    private Document doc;

    private String configFile;

    private boolean readImports = true;

    private MojoProperties properties;

    /** @modelguid {0459EAC4-68B1-47DA-8154-35792F6442F9} */
    public XMLPropertyAdapter(MojoProperties properties, String aResourceName)
    {
        configFile = aResourceName;
        this.properties = properties;
    }

    /** @modelguid {D798FC49-2CEB-45CF-8D5D-D8B1012A8AB0} */
    private Document readDocument() throws Exception
    {
        if (configFile.startsWith("file:/"))
        {
            return XMLManager.readXMLFile(configFile.substring(6));
        }
        else
        {
            return XMLManager.readXMLResource(configFile);
        }
    }

    /** @modelguid {DC231013-2F84-4AA3-9580-D04956701892} */
    public void readImports(boolean shouldReadImports)
    {
        readImports = shouldReadImports;
    }

    /** @modelguid {338F7051-0046-441F-A830-DDE6031B52BE} */
    public void loadProperties() throws Exception
    {
        doc = readDocument();
        String lImport = doc.getRootElement().getAttributeValue("import");
        if (lImport != null)
        {
            properties.setImportFile(lImport);
            if (readImports)
            {
                properties.loadProperties(lImport);
            }
        }
        readAppProperties();
        readBuildProperties();
        readFolderProperties();
        readServiceProperties();
        readProfileProperties();
        readPropertyBundleProperties();
        readVersionableProperties();
        readStateMachineProperties();
        readEntityMappingProperties();
        readConnectionProperties();
        readCacheProperties();
        readSecurityProperties();
        readCalendaringProperties();

        this.readTestMappingProperties();

    }

    /**
     *  
     */
    private void readTestMappingProperties()
    {
        Element element = doc.getRootElement().getChild(TestDataClassificationProperties.TEST_DATA_DICTIONARY_FILES);

        if (element != null)
        {
            XMLTestMappingPropertyAdapter.getInstance().load(TestDataClassificationProperties.TEST_DATA_DICTIONARY_FILE,
                    TestDataClassificationProperties.CLASSIFICATION, element, properties);
        }

    }

    private void readProperties(GenericProperties aProps, Element aNode)
    {
        for (Iterator i = aNode.getChildren().iterator(); i.hasNext();)
        {
            Element lElement = (Element) i.next();
            String lName = lElement.getName();
            String lValue = lElement.getAttributeValue("value");
            if (lValue != null)
            {
                aProps.setProperty(lName, lValue);
            }
        }
    }

    private void readLooseProperties(GenericProperties aProps, Element aNode)
    {
        for (Iterator i = aNode.getChildren("Property").iterator(); i.hasNext();)
        {
            Element lElement = (Element) i.next();
            String lName = lElement.getAttributeValue("name");
            String lValue = lElement.getAttributeValue("value");
            if (lName != null && lValue != null)
            {
                aProps.setProperty(lName, lValue);
            }
        }
    }

    private void readAppProperties()
    {
        Element lElement = doc.getRootElement().getChild("Application");
        if (lElement != null)
        {
            AppProperties lProps = properties.getAppProperties();
            readProperties(lProps, lElement);
        }
    }

    /** @modelguid {4ADB403A-32EA-4935-9DEC-E30426CC9954} */
    private void readBuildProperties()
    {
        Element lElement = doc.getRootElement().getChild("Build");
        if (lElement != null)
        {
            BuildProperties lProps = properties.getBuildProperties();
            readProperties(lProps, lElement);

            Element lListElement = lElement.getChild("ContextManagerList");
            if (lListElement != null)
            {
                for (Iterator i = lListElement.getChildren("Item").iterator(); i.hasNext();)
                {
                    Element lItemElement = (Element) i.next();
                    String lItem = lItemElement.getAttributeValue("className");
                    lProps.addContextManager(lItem);
                }
            }

            if (lListElement != null)
            {
                lListElement = lElement.getChild("RequestDispatchList");
                for (Iterator i = lListElement.getChildren("Item").iterator(); i.hasNext();)
                {
                    Element lItemElement = (Element) i.next();
                    String lItem = lItemElement.getAttributeValue("className");
                    lProps.addRequestDispatch(lItem);
                }
            }

            if (lListElement != null)
            {
                lListElement = lElement.getChild("ReplyDispatchList");
                for (Iterator i = lListElement.getChildren("Item").iterator(); i.hasNext();)
                {
                    Element lItemElement = (Element) i.next();
                    String lItem = lItemElement.getAttributeValue("className");
                    lProps.addReplyDispatch(lItem);
                }
            }            
        }
    }

    /** @modelguid {1FAFF924-BEFD-4621-8819-9158647A4F82} */
    private void readFolderProperties()
    {
        Element lElement = doc.getRootElement().getChild("Folder");
        if (lElement != null)
        {
            FolderProperties lProps = properties.getFolderProperties();
            readLooseProperties(lProps, lElement);
        }
    }

    /** @modelguid {9E4BF934-2B28-4DF0-8C9A-18763B5FB7A6} */
    private void readPropertyBundleProperties()
    {
        Element lElement = doc.getRootElement().getChild("PropertyBundle");
        if (lElement != null)
        {
            PropertyBundleProperties lProps = properties.getPropertyBundleProperties();
            readLooseProperties(lProps, lElement);
        }
    }

    /** @modelguid {A71DBA88-D7F8-48AD-A4F2-EE926C830CE4} */
    private void readVersionableProperties()
    {
        Element lElement = doc.getRootElement().getChild("Versionable");
        if (lElement != null)
        {
            VersionableProperties lProps = properties.getVersionableProperties();
            readLooseProperties(lProps, lElement);
        }
    }

    /** @modelguid {14E6B8A7-A01B-43D0-A492-C0E59141ECCD} */
    private void readServiceProperties()
    {
        Element lElement = doc.getRootElement().getChild("Services");
        if (lElement != null)
        {
            Iterator i = lElement.getChildren("Service").iterator();
            while (i.hasNext())
            {
                Element lServiceElement = (Element) i.next();
                ServiceProperties lService = readService(lServiceElement);
                properties.addService(lService);
            }
        }
    }

    /** @modelguid {989DBB78-273D-4F83-BB9F-7544451F894D} */
    private void readEntityMappingProperties()
    {

        Element lElement = doc.getRootElement().getChild("EntityMappingFiles");
        if (lElement != null)
        {
            XMLMappingPropertyAdapter.getInstance().load(lElement, properties);
        }
        else
        {
            lElement = doc.getRootElement().getChild("EntityMappings");
            if (lElement != null)
            {
                Iterator i = lElement.getChildren("EntityMapping").iterator();
                while (i.hasNext())
                {
                    Element lEntityElement = (Element) i.next();
                    EntityMappingProperties lEntity = MappingUtility.readEntityMapping(lEntityElement);
                    properties.addEntityMapping(lEntity);
                }
            }
        }
    }

    /** @modelguid {14520BF6-E521-44DA-9ED9-D737E1661A32} */
    private void readConnectionProperties()
    {
        Element lElement = doc.getRootElement().getChild("Connections");
        if (lElement != null)
        {
            // Checks to see if the 'Connections' element has an absolute
            // pooling attribute
            // that overrides the pooling attribute of the individual
            // connections.
            String pooling = lElement.getAttributeValue(ConnectionProperties.POOLING);
            Iterator i = lElement.getChildren("Connection").iterator();
            while (i.hasNext())
            {
                Element lConnectionElement = (Element) i.next();
                ConnectionProperties lEntity = readConnection(lConnectionElement, pooling);
                properties.addConnection(lEntity);
            }
        }
    }

    private void readCacheProperties()
    {
        Element lElement = doc.getRootElement().getChild("Caching");
        if (lElement != null)
        {
            CacheProperties lCacheProps = properties.getCacheProperties();
            lCacheProps.loadProperties(lElement);
        }
    }

    /** @modelguid {C848BC93-18BD-4A6E-B2FD-773656D0422A} */
    private ConnectionProperties readConnection(Element pElement, String absolutePooling)
    {
        ConnectionProperties connection = new ConnectionProperties();
        connection.setName(pElement.getAttributeValue(ConnectionProperties.NAME));
        connection.setPassword(pElement.getAttributeValue(ConnectionProperties.PASSWORD));
        connection.setUserID(pElement.getAttributeValue(ConnectionProperties.USERID));
        connection.setURL(pElement.getAttributeValue(ConnectionProperties.URL));
        connection.setTestUrl(pElement.getAttributeValue(ConnectionProperties.TESTURL));
        connection.setPort(pElement.getAttributeValue(ConnectionProperties.PORT));
        connection.setDb2Schema(pElement.getAttributeValue(ConnectionProperties.DB2SCHEMA));
        connection.setRegion(pElement.getAttributeValue(ConnectionProperties.REGION));
        connection.setDriver(pElement.getAttributeValue(ConnectionProperties.DRIVER));
        connection.setConnectionAdapter(pElement.getAttributeValue(ConnectionProperties.CONNECTIONADAPTER));
        
        // connection pool override for all connections
        if (absolutePooling == null)
        {
            connection.setPooling(pElement.getAttributeValue(ConnectionProperties.POOLING));
        }
        else
        {
            connection.setPooling(absolutePooling);
        }
        
        ConnectionPoolProperties poolProps = new ConnectionPoolProperties();
        
        if(connection.getPooling().equalsIgnoreCase(ConnectionProperties.YES))
        {
            Element poolElement = pElement.getChild(ConnectionProperties.POOL);
            poolProps.setMaxActive(poolElement.getAttributeValue(ConnectionPoolProperties.MAX_ACTIVE));
            poolProps.setMaxIdle(poolElement.getAttributeValue(ConnectionPoolProperties.MAX_IDLE));
            poolProps.setMaxWaitTime(poolElement.getAttributeValue(ConnectionPoolProperties.MAX_WAIT_TIME));
            poolProps.setWhenExhausted(poolElement.getAttributeValue(ConnectionPoolProperties.WHEN_EXHAUSTED));
            poolProps.setEvictableIdleTime(poolElement.getAttributeValue(ConnectionPoolProperties.EVICTABLE_IDLE_TIME));
            poolProps.setMinIdle(poolElement.getAttributeValue(ConnectionPoolProperties.MIN_IDLE));
        }
        else
        {            
            poolProps.setMaxActive(ConnectionConstants.DEFAULT_MAX_ACTIVE);
            poolProps.setMaxIdle(ConnectionConstants.DEFAULT_MAX_IDLE);
            poolProps.setMaxWaitTime(ConnectionConstants.DEFAULT_MAX_WAIT_TIME);
            poolProps.setWhenExhausted(ConnectionConstants.DEFAULT_WHEN_EXHAUSTED);
            poolProps.setEvictableIdleTime(ConnectionConstants.DEFAULT_EVICTABLE_IDLE_TIME);
            poolProps.setMinIdle(ConnectionConstants.DEFAULT_MIN_IDLE);
        }
        
        connection.setPoolProperties(poolProps);
        
        return connection;
    }

    /** @modelguid {ED45B60B-AF35-4C52-A326-1689B480D5CA} */
    private ServiceProperties readService(Element aServiceElement)
    {
        ServiceProperties lServiceProperties = new ServiceProperties();
        String lEvent = aServiceElement.getAttributeValue(ServiceProperties.EVENT);
        String lServiceName = aServiceElement.getAttributeValue(ServiceProperties.NAME);
        String lCheckpoint = aServiceElement.getAttributeValue(ServiceProperties.CHECKPOINT);
        lServiceProperties.setName(lServiceName);
        lServiceProperties.setEventName(lEvent);
        lServiceProperties.setCheckPoint(lCheckpoint);

        //		Element lCommandsElement = aServiceElement.getChild("Commands");
        //		if (lCommandsElement != null) {
        Iterator i = aServiceElement.getChildren("Command").iterator();
        while (i.hasNext())
        {
            Element lCommandElement = (Element) i.next();
            String lCommand = lCommandElement.getAttributeValue("name");
            lServiceProperties.addCommand(lCommand);
        }
        //		}
        return lServiceProperties;
    }

    /** @modelguid {40F10876-3837-4770-90B8-8DE61399DD59} */
    private void readProfileProperties()
    {
        Iterator i = doc.getRootElement().getChildren("Servers").iterator();
        while (i.hasNext())
        {
            Element lProfileElement = (Element) i.next();

            Element lDefaultServer = lProfileElement.getChild("defaultServer");
            if (lDefaultServer != null)
            {
                ServerProperties lDefaultServerProps = readServer(lDefaultServer);
                properties.setDefaultServer(lDefaultServerProps);

                for (Iterator j = properties.getServers(); j.hasNext();)
                {
                    ServerProperties lInheritedServer = (ServerProperties) j.next();
                    inheritServer(lDefaultServerProps, lInheritedServer);
                }
            }

            List servers = lProfileElement.getChildren("Server");
            Iterator j = servers.iterator();
            while (j.hasNext())
            {
                Element lServerElement = (Element) j.next();
                ServerProperties lServer = readServer(lServerElement);
                properties.addServer(lServer);
            }

        }
    }

    /** @modelguid {4F643DAB-15D7-4262-A7C5-FB5F312C7F0A} */
    private void inheritProperties(GenericProperties aBaseProperties, GenericProperties aProperties)
    {
        if (readImports)
        {
            for (Iterator i = aBaseProperties.getProperties(); i.hasNext();)
            {
                String lPropName = (String) i.next();
                String lBaseValue = aBaseProperties.getProperty(lPropName);
                if (lBaseValue != null)
                {
                    aProperties.setProperty(lPropName, lBaseValue);
                }
            }
        }
    }

    /** @modelguid {FD0AB92E-8674-4B46-A4F2-C7C5FAF18062} */
    private void inheritServer(ServerProperties aDefaultServer, ServerProperties aServer)
    {
        if (readImports)
        {
            inheritProperties(aDefaultServer, aServer);
            inheritProperties(aDefaultServer.getDispatchProperties(), aServer.getDispatchProperties());
            inheritProperties(aDefaultServer.getSessionProperties(), aServer.getSessionProperties());
            inheritProperties(aDefaultServer.getLogProperties(), aServer.getLogProperties());
            for (Iterator i = aDefaultServer.getServiceRefs(); i.hasNext();)
            {
                ServiceRefProperties lSuperServiceRef = (ServiceRefProperties) i.next();
                aServer.addService(new ServiceRefProperties(lSuperServiceRef));
            }
        }
    }

    /** @modelguid {9D61AA3D-01ED-477B-A21B-B09FF6F195D8} */
    private ServerProperties readServer(Element aServerElement)
    {
        String lServerName = aServerElement.getAttributeValue(ServerProperties.NAME);

        ServerProperties lServer = null;
        if (lServerName != null)
        {
            lServer = properties.getServer(lServerName);
        }
        if (lServer == null)
        {
            lServer = new ServerProperties();
            if (lServerName != null)
            {
                lServer.setName(lServerName);
            }
        }
        if (lServerName != null && properties.getDefaultServer() != null)
        {
            inheritServer(properties.getDefaultServer(), lServer);
        }

        String lContextManager = aServerElement.getAttributeValue(ServerProperties.CONTEXT_MANAGER);
        if (lContextManager != null)
        {
            lServer.setContextManager(lContextManager);
        }
        String lPoolSize = aServerElement.getAttributeValue(ServerProperties.POOL_SIZE);
        if (lPoolSize != null)
        {
            lServer.setPoolSize(lPoolSize);
        }
        String lManageObjectManagerName = aServerElement.getAttributeValue(ServerProperties.MANAGEMENT_OBJECT_MANAGER);
        if (lManageObjectManagerName != null)
        {
            lServer.setManagementObjectManager(lManageObjectManagerName);
        }
        Element lSessionServiceElement = aServerElement.getChild("SessionService");
        if (lSessionServiceElement != null)
        {
            String lSessionService = lSessionServiceElement.getAttributeValue("adapter");
            lServer.setSessionServiceAdapter(lSessionService);
        }

        readDispatchProperties(lServer, aServerElement);
        readLogProperties(lServer, aServerElement);
        readSessionProperties(lServer, aServerElement);

        Element lServiceRefs = aServerElement.getChild("ServiceReferences");
        if (lServiceRefs != null)
        {
            Iterator i = lServiceRefs.getChildren("ServiceRef").iterator();
            while (i.hasNext())
            {
                Element lServiceRefElement = (Element) i.next();
                ServiceRefProperties lServiceRef = readServiceRef(lServiceRefElement);
                lServer.addService(lServiceRef);
            }
        }

        return lServer;
    }

    /** @modelguid {DA6C88C6-DBB6-46C5-B1AF-732E0AFEC0F3} */
    private void readDispatchProperties(ServerProperties aServer, Element aServerElement)
    {
        DispatchProperties lProperties = aServer.getDispatchProperties();
        Element lElement = aServerElement.getChild("EventManager");
        if (lElement != null)
        {
            readProperties(lProperties, lElement);
        }
    }

    /** @modelguid {06AB76FE-4599-452C-B50B-8C270E3B8219} */
    private void readLogProperties(ServerProperties aServer, Element aServerElement)
    {
        LogProperties lProperties = aServer.getLogProperties();
        Element lElement = aServerElement.getChild("LogManager");
        if (lElement != null)
        {
            readProperties(lProperties, lElement);
        }
    }

    /** @modelguid {F9EFB80D-052C-4A9B-8B68-B3141BA234DD} */
    private void readSessionProperties(ServerProperties aServer, Element aServerElement)
    {
        SessionProperties lProperties = aServer.getSessionProperties();
        Element lElement = aServerElement.getChild("Session");
        if (lElement != null)
        {
            readProperties(lProperties, lElement);
        }
    }

    /** @modelguid {C6156AA9-E814-47EF-A512-6AB3882C8BC0} */
    private ServiceRefProperties readServiceRef(Element aServiceRefElement)
    {
        ServiceRefProperties lServiceRefProperties = new ServiceRefProperties();
        String lDispatch = aServiceRefElement.getAttributeValue(ServiceRefProperties.DISPATCH);
        String lServiceName = aServiceRefElement.getAttributeValue(ServiceRefProperties.NAME);
        lServiceRefProperties.setName(lServiceName);
        lServiceRefProperties.setDispatch(lDispatch);
        return lServiceRefProperties;
    }

    /** @modelguid {F05B8BC2-8800-4EB9-9AD5-9C426F1AA57D} */
    private void readStateMachineProperties()
    {
        Element lElement = doc.getRootElement().getChild("StateMachine");
        if (lElement != null)
        {
            lElement = lElement.getChild("Machines");
        }
        if (lElement != null)
        {
            StateMachineProperties lProps = properties.getStateMachineProperties();
            for (Iterator i = lElement.getChildren("Machine").iterator(); i.hasNext();)
            {
                Element lMachineElement = (Element) i.next();
                Element lStatesElement = lMachineElement.getChild("States");
                if (lStatesElement != null)
                {
                    String lMachineClass = lMachineElement.getAttributeValue("class");
                    String lMachineStateField = lMachineElement.getAttributeValue("StateField");
                    String lMachineDefaultStartState = lMachineElement.getAttributeValue("DefaultStartState");
                    lProps.addMachine(lMachineClass);
                    for (Iterator j = lStatesElement.getChildren("State").iterator(); j.hasNext();)
                    {
                        Element lStateElement = (Element) j.next();
                        String lStateName = lStateElement.getAttributeValue("name");
                        lProps.addState(lMachineClass, lStateName);
                        Element lTransitionsElement = lStateElement.getChild("Transitions");
                        if (lTransitionsElement != null)
                        {
                            for (Iterator k = lTransitionsElement.getChildren("Transition").iterator(); k.hasNext();)
                            {
                                Element lTransitionElement = (Element) k.next();
                                String lTransitionToState = lTransitionElement.getAttributeValue("toState");
                                lProps.addTransition(lMachineClass, lStateName, lTransitionToState);
                            }
                        }
                    }
                    lProps.setStateField(lMachineClass, lMachineStateField);
                    lProps.setDefaultStartState(lMachineClass, lMachineDefaultStartState);
                }
            }
        }
    }

 
    /** @modelguid {29D4F523-C7F4-45D1-B3FA-EB7BC1142ED7} */
    public void saveProperties()
    {
        saveProperties(null);
    }

    /** @modelguid {BE2DEE53-F774-415A-B20B-3154D5627A2A} */
    public void saveMapping(String anImportName)
    {
        Element lRootElement = new Element("Mojo");
        doc = new Document(lRootElement);
        //		doc.setDocType(new DocType("Mojo", "mojo.dtd"));
        if (anImportName != null)
        {
            lRootElement.setAttribute("import", anImportName);
        }
        writeConnectionProperties();
        writeEntityMappingProperties();
        try
        {
            writeDocument(doc);
        }
        catch (Exception e)
        {
            System.out.println("Could not write to " + configFile);
            System.out.println("Attempting save to alternate location.");
            try
            {
                URL lMojoURL = Thread.currentThread().getContextClassLoader().getResource("Mojo.xml");
                String lFilename = lMojoURL.getFile();
                lFilename = lFilename.substring(0, lFilename.length() - 8) + configFile;
                configFile = "file:/" + lFilename;
                writeDocument(doc);
            }
            catch (Exception ex)
            {
                System.out.println("Alternate save attempt failed for " + configFile);
                //				ex.printStackTrace();
                //				System.err.println("Original Error:");
                //				e.printStackTrace();
            }
        }
    }

    public void saveProperties(String anImportName)
    {
        Element lRootElement = new Element("Mojo");
        doc = new Document(lRootElement);
        //		doc.setDocType(new DocType("Mojo", "mojo.dtd"));
        if (anImportName != null)
        {
            lRootElement.setAttribute("import", anImportName);
        }
        writeAppProperties();
        writeBuildProperties();
        writeFolderProperties();
        writePropertyBundleProperties();
        writeServiceProperties();
        writeStateMachineProperties();
        writeVersionableProperties();
        writeProfileProperties();
        writeConnectionProperties();
        writeEntityMappingProperties();
        writeCacheProperties();

        try
        {
            writeDocument(doc);
        }
        catch (Exception e)
        {
            System.out.println("Could not write to " + configFile);
            System.out.println("Attempting save to alternate location.");
            try
            {
                URL lMojoURL = Thread.currentThread().getContextClassLoader().getResource("Mojo.xml");
                String lFilename = lMojoURL.getFile();
                lFilename = lFilename.substring(0, lFilename.length() - 8) + configFile;
                configFile = "file:/" + lFilename;
                writeDocument(doc);
            }
            catch (Exception ex)
            {
                System.out.println("Alternate save attempt failed for " + configFile);
                ex.printStackTrace();
                System.err.println("Original Error:");
                e.printStackTrace();
            }
        }
    }

    /** @modelguid {CCE3B024-BF09-4C5F-A726-DED9C7C13C03} */
    private void writeDocument(Document aDocument) throws Exception
    {
        if (configFile.startsWith("file:"))
        {
            String filename = configFile.substring(5);
            char ch = filename.charAt(0);
            if(ch == '/' || ch =='\\')
            {
                filename = configFile.substring(1);
            }
            XMLManager.writeXMLToFile(aDocument, filename);
        }
        else
        {
            XMLManager.writeXMLToResource(aDocument, configFile);
        }
    }

    /** @modelguid {435D9926-62DA-4A21-B456-62684E64EA53} */
    private void writeProperties(GenericProperties aProps, Element aNode)
    {
        for (Iterator i = aProps.getProperties(); i.hasNext();)
        {
            String lName = (String) i.next();
            String lValue = aProps.getProperty(lName);
            Element lElement = new Element(lName);
            if (lValue != null)
            {
                lElement.setAttribute("value", lValue);
                aNode.addContent(lElement);
            }
        }
    }

    /** @modelguid {3F61C2A7-1187-4FAA-9FDF-E355CE4287C8} */
    private void writeServerProperties(GenericProperties aProps, Element aNode)
    {
        for (Iterator i = aProps.getProperties(); i.hasNext();)
        {
            String lName = (String) i.next();
            String lValue = aProps.getProperty(lName);
            Element lElement = new Element(lName);
            if (lValue != null)
            {
                lElement.setAttribute("value", lValue);
                aNode.addContent(lElement);
            }
        }
    }

    /** @modelguid {7CE77252-D2BB-4409-80AA-6ACC8779937D} */
    private void writeLooseProperties(GenericProperties aProps, Element aNode)
    {
        for (Iterator i = aProps.getProperties(); i.hasNext();)
        {
            String lName = (String) i.next();
            String lValue = aProps.getProperty(lName);
            Element lElement = new Element("Property");
            if (lName != null)
            {
                lElement.setAttribute("name", lName);
            }
            if (lValue != null)
            {
                lElement.setAttribute("value", lValue);
            }
            aNode.addContent(lElement);
        }
    }

    /** @modelguid {C55C8144-424A-41CA-A757-87AD7D735698} */
    private void writeAppProperties()
    {
        AppProperties lProperties = properties.getAppProperties();
        if (lProperties.hasProperties())
        {
            Element lElement = new Element("Application");
            doc.getRootElement().addContent(lElement);
            writeProperties(lProperties, lElement);
        }
    }

    /** @modelguid {094A73D5-9584-47CB-9AC3-7D87A305A672} */
    private void writeBuildProperties()
    {
        BuildProperties lProperties = properties.getBuildProperties();
        if (lProperties.hasProperties())
        {
            Element lElement = new Element("Build");
            doc.getRootElement().addContent(lElement);
            writeProperties(lProperties, lElement);

            Iterator i = lProperties.getContextManagerList();
            if (i.hasNext())
            {
                Element lListElement = new Element("ContextManagerList");
                lElement.addContent(lListElement);
                while (i.hasNext())
                {
                    String lItem = (String) i.next();
                    Element lItemElement = new Element("Item");
                    lListElement.addContent(lItemElement);
                    lItemElement.setAttribute("className", lItem);
                }
            }

            i = lProperties.getRequestDispatchList();
            if (i.hasNext())
            {
                Element lListElement = new Element("RequestDispatchList");
                lElement.addContent(lListElement);
                while (i.hasNext())
                {
                    String lItem = (String) i.next();
                    Element lItemElement = new Element("Item");
                    lListElement.addContent(lItemElement);
                    lItemElement.setAttribute("className", lItem);
                }
            }

            i = lProperties.getRequestDispatchList();
            if (i.hasNext())
            {
                Element lListElement = new Element("ReplyDispatchList");
                lElement.addContent(lListElement);
                while (i.hasNext())
                {
                    String lItem = (String) i.next();
                    Element lItemElement = new Element("Item");
                    lListElement.addContent(lItemElement);
                    lItemElement.setAttribute("className", lItem);
                }
            }

            i = lProperties.getRequestDispatchList();
            if (i.hasNext())
            {
                Element lListElement = new Element("TOPLinkMapList");
                lElement.addContent(lListElement);
                while (i.hasNext())
                {
                    String lItem = (String) i.next();
                    Element lItemElement = new Element("Item");
                    lListElement.addContent(lItemElement);
                    lItemElement.setAttribute("className", lItem);
                }
            }
        }
    }

    /** @modelguid {102B7EA7-3C14-4951-ABEB-5FDAFDB396F2} */
    private void writeFolderProperties()
    {
        FolderProperties lProperties = properties.getFolderProperties();
        if (lProperties.hasProperties())
        {
            Element lElement = new Element("Folder");
            doc.getRootElement().addContent(lElement);
            writeLooseProperties(lProperties, lElement);
        }
    }

    /** @modelguid {5E54F28B-BA6A-40A7-A159-34E7FA94A0CC} */
    private void writePropertyBundleProperties()
    {
        PropertyBundleProperties lProperties = properties.getPropertyBundleProperties();
        if (lProperties.hasProperties())
        {
            Element lElement = new Element("PropertyBundle");
            doc.getRootElement().addContent(lElement);
            writeLooseProperties(lProperties, lElement);
        }
    }

    /** @modelguid {4F7CC9D6-4986-40CA-9DA6-FED1239A01A7} */
    private void writeVersionableProperties()
    {
        VersionableProperties lProperties = properties.getVersionableProperties();
        if (lProperties.hasProperties())
        {
            Element lElement = new Element("Versionable");
            doc.getRootElement().addContent(lElement);
            writeLooseProperties(lProperties, lElement);
        }
    }

    /** @modelguid {A88EA8DB-AB79-48CE-B25B-8419B774E3EB} */
    private void writeServiceProperties()
    {
        Element lElement = new Element("Services");
        Iterator i = properties.getServices();
        if (i.hasNext())
        {
            doc.getRootElement().addContent(lElement);
            while (i.hasNext())
            {
                ServiceProperties lProperties = (ServiceProperties) i.next();
                writeService(lProperties, lElement);
            }
        }
    }

    /** @modelguid {2B24EEB6-7BB3-440A-B356-1390D419A88D} */
    private void writeService(ServiceProperties aProperties, Element anElement)
    {
        Element lServiceElement = new Element("Service");
        anElement.addContent(lServiceElement);
        String lName = aProperties.getName();
        String lEvent = aProperties.getEventName();
        if (lName != null)
        {
            lServiceElement.setAttribute(ServiceProperties.NAME, lName);
        }
        if (lEvent != null)
        {
            lServiceElement.setAttribute(ServiceProperties.EVENT, lEvent);
        }
        boolean checkPoint = aProperties.isCheckPoint();
        if (checkPoint)
        {
            lServiceElement.setAttribute(ServiceProperties.CHECKPOINT, "" + checkPoint);
        }
        //		Element lCommandsElement = new Element("Commands");
        //		lServiceElement.addContent(lCommandsElement);
        for (Iterator i = aProperties.getCommands(); i.hasNext();)
        {
            String lCommand = (String) i.next();
            Element lCommandElement = new Element("Command");
            lServiceElement.addContent(lCommandElement);
            if (lCommand != null)
            {
                lCommandElement.setAttribute("name", lCommand);
            }
        }
    }

    /**
     * 03/29/2004 - Add method to write ConnectionProperties - mpatino & rcooper
     */
    private void writeConnectionProperties()
    {
        Element lElement = new Element("Connections");
        //Iterator i = (Iterator) properties.getAllConnectionProperties();
        Collection connMap = properties.getAllConnectionProperties();
        Iterator i = connMap.iterator();
        if (i.hasNext())
        {
            doc.getRootElement().addContent(lElement);
            while (i.hasNext())
            {
                ConnectionProperties lProperties = (ConnectionProperties) i.next();
                writeConnection(lProperties, lElement);
            }
        }
    }

    /**
     * 03/29/2004 - Add method to write ConnectionProperties - mpatino & rcooper
     */
    /** @modelguid {2B24EEB6-7BB3-440A-B356-1390D419A88D} */
    private void writeConnection(ConnectionProperties aProperties, Element anElement)
    {

        Element lConnectionElement = new Element("Connection");

        anElement.addContent(lConnectionElement);
        String lName = aProperties.getName();
        String lUserID = aProperties.getUserID();
        String lPassword = aProperties.getPassword();
        String lURL = aProperties.getURL();
        String lSchema = aProperties.getDb2Schema();
        String lPort = aProperties.getPort();
        String lRegion = aProperties.getRegion();
        String lDriver = aProperties.getDriver();
        String lPooling = aProperties.getPooling();
        String lConnectionAdapter = aProperties.getConnectionAdapter();
        if (lName != null)
        {
            lConnectionElement.setAttribute(ConnectionProperties.NAME, lName);
        }
        if (lDriver != null)
        {
            lConnectionElement.setAttribute(ConnectionProperties.DRIVER, lDriver);
        }
        if (lUserID != null)
        {
            lConnectionElement.setAttribute(ConnectionProperties.USERID, lUserID);

            if (lPassword != null)
            {
                lConnectionElement.setAttribute(ConnectionProperties.PASSWORD, lPassword);
            }
        }
        if (lConnectionAdapter != null)
        {
            lConnectionElement.setAttribute(ConnectionProperties.CONNECTIONADAPTER, lConnectionAdapter);
        }

        if (lURL != null)
        {
            lConnectionElement.setAttribute(ConnectionProperties.URL, lURL);
        }
        if (lPort != null)
        {
            lConnectionElement.setAttribute(ConnectionProperties.PORT, lPort);
        }

        if (lSchema != null)
        {
            lConnectionElement.setAttribute(ConnectionProperties.DB2SCHEMA, lSchema);
        }

        if (lRegion != null)
        {
            lConnectionElement.setAttribute(ConnectionProperties.REGION, lRegion);
        }

        if (lPooling != null)
        {
            lConnectionElement.setAttribute(ConnectionProperties.POOLING, lPooling);
        }
        //anElement.addContent(lConnectionElement);

    }

    /**
     * 03/30/2004 - Add method to write EntityMappingProperties - mpatino &
     * rcooper
     */
    private void writeEntityMappingProperties()
    {
        XMLMappingPropertyAdapter.getInstance().saveAll(doc, properties);
    }

    /** @modelguid {B0BEB42F-0A42-484F-8C42-024694FABD9E} */
    private void writeProfileProperties()
    {
        Element anElement = doc.getRootElement();
        Element lProfileElement = new Element("Servers");
        anElement.addContent(lProfileElement);

        ServerProperties lDefaultServer = properties.getDefaultServer();
        if (lDefaultServer != null)
        {
            writeServer(lDefaultServer, lProfileElement);
        }
        for (Iterator i = properties.getServers(); i.hasNext();)
        {
            ServerProperties lServer = (ServerProperties) i.next();
            writeServer(lServer, lProfileElement);
        }
    }

    /** @modelguid {85181868-E102-4BD6-ADFB-75543CFA579F} */
    private void writeServer(ServerProperties aProperties, Element anElement)
    {
        Element lServerElement = null;
        if (aProperties.getProperty(ServerProperties.NAME) == null)
        {
            lServerElement = new Element("defaultServer");
        }
        else
        {
            lServerElement = new Element("Server");
        }
        String lName = aProperties.getProperty(ServerProperties.NAME);
        if (lName != null)
        {
            lServerElement.setAttribute(ServerProperties.NAME, lName);
        }
        String lContextManager = aProperties.getProperty(ServerProperties.CONTEXT_MANAGER);
        if (lContextManager != null)
        {
            lServerElement.setAttribute(ServerProperties.CONTEXT_MANAGER, lContextManager);
        }
        String aPoolSize = aProperties.getProperty(ServerProperties.POOL_SIZE);
        if (aPoolSize != null)
        {
            lServerElement.setAttribute(ServerProperties.POOL_SIZE, aPoolSize);
        }
        String lManagementObjectManagerName = aProperties.getProperty(ServerProperties.MANAGEMENT_OBJECT_MANAGER);
        if (lManagementObjectManagerName != null)
        {
            lServerElement.setAttribute(ServerProperties.MANAGEMENT_OBJECT_MANAGER, lManagementObjectManagerName);
        }
        String lSessionService = aProperties.getSessionServiceAdapter();
        if (lSessionService != null)
        {
            Element lSessionServiceElement = new Element("SessionService");
            lServerElement.addContent(lSessionServiceElement);
            lSessionServiceElement.setAttribute("adapter", lSessionService);
        }

        writeDispatchProperties(aProperties.getDispatchProperties(), lServerElement);
        writeLogProperties(aProperties.getLogProperties(), lServerElement);
        writeSessionProperties(aProperties.getSessionProperties(), lServerElement);

        Iterator lServiceRefs = aProperties.getServiceRefs();
        if (lServiceRefs.hasNext())
        {
            Element lServerRefsElement = new Element("ServiceReferences");
            lServerElement.addContent(lServerRefsElement);
            while (lServiceRefs.hasNext())
            {
                ServiceRefProperties lServiceRef = (ServiceRefProperties) lServiceRefs.next();
                writeServiceRef(lServiceRef, lServerRefsElement);
            }
        }

        boolean hasProps = false;
        for (Iterator i = aProperties.getProperties(); i.hasNext();)
        {
            String lPropName = (String) i.next();
            if (!lPropName.equals("name"))
            {
                hasProps = true;
            }
        }
        if (!lServerElement.getContent().isEmpty() || hasProps)
        {
            anElement.addContent(lServerElement);
        }
    }

    /** @modelguid {61138372-7F5F-407E-847B-A2F25AF5DBF4} */
    private void writeDispatchProperties(DispatchProperties aProperties, Element anElement)
    {
        if (aProperties.hasProperties())
        {
            Element lElement = new Element("EventManager");
            anElement.addContent(lElement);
            writeServerProperties(aProperties, lElement);
        }
    }

    /** @modelguid {22A5B906-21CD-484B-AE1B-7A79046AC72F} */
    private void writeLogProperties(LogProperties aProperties, Element anElement)
    {
        if (aProperties.hasProperties())
        {
            Element lElement = new Element("LogManager");
            anElement.addContent(lElement);
            writeServerProperties(aProperties, lElement);
        }
    }

    /** @modelguid {98FB0E00-26F9-468C-963A-63DC30F233FA} */
    private void writeSessionProperties(SessionProperties aProperties, Element anElement)
    {
        if (aProperties.hasProperties())
        {
            Element lElement = new Element("Session");
            anElement.addContent(lElement);
            writeServerProperties(aProperties, lElement);
        }
    }

    /** @modelguid {28750637-8DCC-4D27-BCDD-A3899E0C3A59} */
    private void writeServiceRef(ServiceRefProperties aProperties, Element anElement)
    {
        Element lElement = new Element("ServiceRef");
        anElement.addContent(lElement);
        String lServiceName = aProperties.getName();
        String lDispatch = aProperties.getDispatchClass();
        if (lServiceName != null)
        {
            lElement.setAttribute(ServiceRefProperties.NAME, lServiceName);
        }
        if (lDispatch != null)
        {
            lElement.setAttribute(ServiceRefProperties.DISPATCH, lDispatch);
        }
    }

    /** @modelguid {940842B4-EDB1-4016-89A8-F3DFC3158BEA} */
    private void writeStateMachineProperties()
    {
        StateMachineProperties lProps = properties.getStateMachineProperties();
        Iterator i = lProps.getMachines();
        if (i.hasNext())
        {
            Element lElement = doc.getRootElement();
            Element lStateMachineElement = new Element("StateMachine");
            lElement.addContent(lStateMachineElement);
            Element lMachinesElement = new Element("Machines");
            lStateMachineElement.addContent(lMachinesElement);

            while (i.hasNext())
            {
                String lMachineClass = (String) i.next();
                Element lMachineElement = new Element("Machine");
                lMachinesElement.addContent(lMachineElement);
                String lMachineStateField = lProps.getStateField(lMachineClass);
                String lMachineDefaultStartState = lProps.getDefaultStartState(lMachineClass);
                if (lMachineClass != null)
                {
                    lMachineElement.setAttribute("class", lMachineClass);
                }
                if (lMachineStateField != null)
                {
                    lMachineElement.setAttribute("StateField", lMachineStateField);
                }
                if (lMachineDefaultStartState != null)
                {
                    lMachineElement.setAttribute("DefaultStartState", lMachineDefaultStartState);
                }

                Iterator j = lProps.getStates(lMachineClass);
                if (j.hasNext())
                {
                    Element lStatesElement = new Element("States");
                    lMachineElement.addContent(lStatesElement);
                    while (j.hasNext())
                    {
                        String lStateName = (String) j.next();
                        Element lStateElement = new Element("State");
                        lStatesElement.addContent(lStateElement);
                        if (lStateName != null)
                        {
                            lStateElement.setAttribute("name", lStateName);
                        }

                        Iterator k = lProps.getTransitions(lMachineClass, lStateName);
                        if (k.hasNext())
                        {
                            Element lTransitionsElement = new Element("Transitions");
                            lStateElement.addContent(lTransitionsElement);
                            while (k.hasNext())
                            {
                                String lTransitionToState = (String) k.next();
                                Element lTransitionElement = new Element("Transition");
                                lTransitionsElement.addContent(lTransitionElement);
                                if (lTransitionToState != null)
                                {
                                    lTransitionElement.setAttribute("toState", lTransitionToState);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void writeCacheProperties()
    {
        CacheProperties lProperties = properties.getCacheProperties();
        if (lProperties.hasProperties())
        {
            Element lElement = new Element("Caching");
            doc.getRootElement().addContent(lElement);
            writeLooseProperties(lProperties, lElement);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see mojo.km.config.IMojoPropertyAdapter#savePfProperties(java.lang.String)
     */
    public void savePfProperties(String anImportName)
    {
        // TODO Auto-generated method stub

    }

    private void readCalendaringProperties()
    {
        Element element = doc.getRootElement().getChild("Calendaring");
        if (element != null)
        {
            CalendaringProperties props = properties.getCalendaringProperties();
            readLooseProperties(props, element);
            Element calendarContexts = element.getChild("Contexts");
            if (calendarContexts != null)
            {
                List contexts = calendarContexts.getChildren("Context");
                if (contexts != null)
                {
                    for (int i = 0; i < contexts.size(); i++)
                    {
                        Element context = (Element) contexts.get(i);
                        if (context != null)
                        {
                            //URLResource urlResource = new URLResource();
                            String contextType = context.getAttributeValue("contextType");
                            String contextClass = context.getAttributeValue("contextClass");
                            if (contextType != null && contextClass != null)
                            {
                                props.addContextClass(contextType, contextClass);
                            }
                        }
                    }
                }
            }
        }
    }

    private void readSecurityProperties()
    {
        Element lElement = doc.getRootElement().getChild("Security");
        if (lElement != null)
        {
            SecurityProperties sProps = properties.getSecurityProperties();
            readLooseProperties(sProps, lElement);
            Element urlResources = lElement.getChild("URLResources");
            if (urlResources != null)
            {
                List resources = urlResources.getChildren("Resource");
                if (resources != null)
                {
                    for (int i = 0; i < resources.size(); i++)
                    {
                        Element resourceElement = (Element) resources.get(i);
                        if (resourceElement != null)
                        {
                            URLResource urlResource = new URLResource();
                            /*
                             * Determine the type of the URL resource. It could
                             * be an exact URL match, a prefix match or an
                             * extension match. If it is a URL match, the 'url'
                             * attribute will be present. If it is a prefix
                             * match, the 'prefix' attribute will be present. If
                             * it is a extension match, the 'extension'
                             * attribute will be present.
                             */
                            String theUrl = null;
                            if (resourceElement.getAttributeValue("url") != null)
                            {
                                theUrl = resourceElement.getAttributeValue("url");
                                urlResource.setType(URLResource.URL);
                            }
                            else if (resourceElement.getAttributeValue("extension") != null)
                            {
                                theUrl = resourceElement.getAttributeValue("extension");
                                urlResource.setType(URLResource.EXTENSION);
                            }
                            else if (resourceElement.getAttributeValue("prefix") != null)
                            {
                                theUrl = resourceElement.getAttributeValue("prefix");
                                urlResource.setType(URLResource.PREFIX);
                            }
                            if (theUrl == null || theUrl.trim().length() == 0)
                            {
                                throw new RuntimeException(
                                        "Configuration error. A URLResource element in your mojo configuration is missing an attribute. It needs to have one of the three attributes 'url', 'prefix' or 'extension' specified. Perhaps a typo?");
                            }
                            urlResource.setURL(theUrl);
                            urlResource
                                    .setRequiredFeatures(getAsArray(resourceElement.getAttributeValue("requiredFeatures"), ","));
                            urlResource.setRequiredUserTypes(getAsArray(resourceElement.getAttributeValue("requiredUserTypes"),
                                    ","));
                            List queryParams = resourceElement.getChildren("QueryParam");
                            for (int j = 0; j < queryParams.size(); j++)
                            {
                                Element qpe = (Element) queryParams.get(j);
                                String name = qpe.getAttributeValue("name");
                                String value = qpe.getAttributeValue("value");
                                urlResource.addQueryParameter(name, value);
                            }
                            sProps.addURLResource(urlResource);
                        }
                    }
                }
            }

            Element unprotectedResourcesMappings = lElement.getChild("UnprotectedResources");
            if (unprotectedResourcesMappings != null)
            {
                List unprotectedResources = unprotectedResourcesMappings.getChildren("Unprotected");
                if (unprotectedResources != null)
                {
                    for (int i = 0; i < unprotectedResources.size(); i++)
                    {
                        Element unprotectedResourceElement = (Element) unprotectedResources.get(i);
                        if (unprotectedResourceElement != null)
                        {
                            String url = unprotectedResourceElement.getAttributeValue("url");
                            sProps.addUnprotectedResource(url);
                        }
                    }
                }
                List unprotectedExtensions = unprotectedResourcesMappings.getChildren("UnprotectedExtension");
                if (unprotectedExtensions != null)
                {
                    for (int i = 0; i < unprotectedExtensions.size(); i++)
                    {
                        Element unprotectedExtensionElement = (Element) unprotectedExtensions.get(i);
                        if (unprotectedExtensionElement != null)
                        {
                            String extension = unprotectedExtensionElement.getAttributeValue("extension");
                            sProps.addUnprotectedExtension(extension.toLowerCase());
                        }
                    }
                }
            }

            Element constraintMappings = lElement.getChild("ConstraintMappings");
            if (constraintMappings != null)
            {
                List constraintTypes = constraintMappings.getChildren("ConstraintType");
                if (constraintTypes != null)
                {
                    for (int i = 0; i < constraintTypes.size(); i++)
                    {
                        Element resourceElement = (Element) constraintTypes.get(i);
                        if (resourceElement != null)
                        {
                            String type = resourceElement.getAttributeValue("type");
                            String className = resourceElement.getAttributeValue("className");
                            sProps.addConstraintMapping(type, className);
                            sProps.addClassToTypeConstraintMapping(className, type);
                        }
                    }
                }
            }

            Element fullAccessMappings = lElement.getChild("FullAccessUserTypes");
            if (fullAccessMappings != null)
            {
                List userTypes = fullAccessMappings.getChildren("UserType");
                if (userTypes != null)
                {
                    for (int i = 0; i < userTypes.size(); i++)
                    {
                        Element resourceElement = (Element) userTypes.get(i);
                        if (resourceElement != null)
                        {
                            String type = resourceElement.getText();
                            sProps.addFullAccessUserType(type);
                        }
                    }
                }
            }
        }
    }

    private String[] getAsArray(String delimitedString, String delimiter)
    {
        if (delimitedString == null)
        {
            return new String[0];
        }
        StringTokenizer st = new StringTokenizer(delimitedString, delimiter);
        Vector items = new Vector();
        while (st.hasMoreTokens())
        {
            String token = st.nextToken();
            items.add(token);
        }
        String[] retArray = new String[items.size()];
        items.copyInto(retArray);
        return retArray;
    }

}
