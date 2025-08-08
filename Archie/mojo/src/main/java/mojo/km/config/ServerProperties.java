package mojo.km.config;

import java.util.*;

import mojo.km.messaging.IEvent;

/** @modelguid {7D794459-1167-4D16-B89B-1D4A1D063549} */
public class ServerProperties extends GenericProperties {

	
	/** @modelguid {EC459CD9-EC1C-4DF9-94A5-0B14A78F3D7A} */
	public void removeService(String aServiceName) {
        services.remove(aServiceName);
    }

	/** @modelguid {29BA81C7-FF01-4A32-BD5D-F982CA272EBA} */
	public void addService(ServiceRefProperties aServiceRef) {
    	String lServiceRefName = aServiceRef.getProperty(ServiceRefProperties.NAME);
        services.put(lServiceRefName, aServiceRef);
    }

	/** @modelguid {D0B9F812-5E92-4A1B-926E-A370685E30CD} */
	public ServiceRefProperties getServiceRef(String aServiceName) {
    	return (ServiceRefProperties)services.get(aServiceName);
    }

	/** @modelguid {89558527-AF64-43A4-BED7-813263DF834E} */
	public Iterator getServiceRefs() {
   		return services.values().iterator();
    }
    
	/** @modelguid {7F9B1202-E7F5-4F48-8D0D-4C20BABBFB12} */
    public DispatchProperties getDispatchProperties(){ return dispatchProperties; }

	/** @modelguid {5445D594-3655-4348-BE03-D287150657CB} */
    public void setDispatchProperties(DispatchProperties dispatchProperties){ this.dispatchProperties = dispatchProperties; }

	/** @modelguid {BC4BC67F-786E-437E-90E3-88B5D4761B18} */
    public SessionProperties getSessionProperties(){ return sessionProperties; }

	/** @modelguid {697352CB-A53F-40FC-A074-C3D5686AA8B8} */
    public void setSessionProperties(SessionProperties sessionProperties){ this.sessionProperties = sessionProperties; }

	/** @modelguid {30C3B397-ECB8-4BDA-9FD4-74975CAF29E3} */
	public static final String NAME = "name";
	/** @modelguid {13AF5596-7D93-46D8-A74D-B1AE552D90A8} */
    public static final String CONTEXT_MANAGER = "contextClassName";
	/** @modelguid {E7BBEE7F-D30D-42E5-A4E2-3E5EBCD2600B} */
	public static final String SESSION_SERVICE_ADAPTER = "sessionServiceAdapter";
	
	
	public static final String MANAGEMENT_OBJECT_MANAGER = "manageObjectManagerAdapter";
	

	/** @modelguid {E7BBEE7F-D30D-42E5-A4E2-3E5EBCD2600B} */
	public static final String POOL_SIZE = "poolSize";
	
	
	/** @modelguid {F7F21B5E-3B27-4658-ABC9-5DA662A13511} */
	public static ServerProperties getInstance() {
		return MojoProperties.getInstance().getActiveServer();
	}
    
	/** @modelguid {11043EBE-2657-43CA-ACD8-99236C98119D} */
	public static ServerProperties getInstance(String aServerName) {
		return MojoProperties.getInstance().getServer(aServerName);
	}

    /**
     * @supplierCardinality 1 
     * @modelguid {956107F7-BD5E-4BFF-ACDF-C65F303D3D1E}
     */
    private DispatchProperties dispatchProperties = new DispatchProperties();

    /**
     * @supplierCardinality 1 
     * @modelguid {CA02C09D-9F4B-43D4-9C36-20EB750A83E6}
     */
    private SessionProperties sessionProperties = new SessionProperties();

	/**
	 * @supplierCardinality 1 
	 * @modelguid {65BC5051-1E1A-4A69-A9F2-56BC68810016}
	 */
	private LogProperties logProperties = new LogProperties();

    /**
     * @associates <{ServiceRefProperties}>
     * @supplierCardinality 0..* 
     * @modelguid {1083C75E-2662-4AC1-B213-B9A8AF28B387}
     */
    private Map services = new HashMap();
    
	/**
	 * @param event
	 * @modelguid {B00764F7-13F6-48D6-9A69-6339B0402934}
	 */
	public ServiceRefProperties getServiceRefForEvent(IEvent event) {
		ServiceProperties lService = MojoProperties.getInstance().getServiceForEvent(event);
		if (lService == null) {
			return null;
		} else {
			return getServiceRef(lService.getName());
		}
	}

	/** @modelguid {C21E5413-775B-452F-A66B-8CEC45ABD0E7} */
	public void clearServices() {
		services.clear();
	}

	/**
	 * @return String
	 * @modelguid {0DB1A096-E8DF-4C33-8010-2AA82F7D3D40}
	 */
	public String getName() {
		return getProperty(NAME);
	}

	/**
	 * @return String
	 * @modelguid {F6AE8721-9F54-4DA5-85E7-82574D5C7904}
	 */
	public String getContextManager() {
		return getProperty(CONTEXT_MANAGER);
	}

	/**
	 * @return LogProperties
	 * @modelguid {9AEDD743-55CE-44F3-8014-A77B0F09909F}
	 */
	public LogProperties getLogProperties() {
		return logProperties;
	}

	/**
	 * @param lValue
	 * @modelguid {610F91D8-4579-42AD-959F-22659EC52A54}
	 */
	public void setContextManager(String lValue) {
		setProperty(CONTEXT_MANAGER, lValue);
	}

	/**
	 * @param lServerName
	 * @modelguid {FB64379A-6B8F-4E28-9156-BA053235B068}
	 */
	public void setName(String lServerName) {
		setProperty(NAME, lServerName);
		
	}

	/**
	 * @param lServerName
	 * @modelguid {C2BF6D4E-947E-44EE-91C7-391E001D2DB6}
	 */
	public void setSessionServiceAdapter(String anAdapter) {
		setProperty(SESSION_SERVICE_ADAPTER, anAdapter);
		
	}

	/**
	 * @param lLogProperties
	 * @modelguid {8E75BA79-F983-4AAD-BA64-229DC0B8CFCA}
	 */
	public void setLogProperties(LogProperties aLogProperties) {
		logProperties = aLogProperties;
		
	}

	/**
	 * @return String
	 * @modelguid {BF494E54-F61C-440A-A126-B4D2D0865D07}
	 */
	public String getSessionServiceAdapter() {
		return getProperty(SESSION_SERVICE_ADAPTER);
	}
	
	/** @modelguid {9F7A7C95-84AE-42DB-B9D7-7148EEF0BBE8} */
	public String toString() {
		return getName() == null ? "Default" : getName();
	}
	/**
	 * @return
	 */
	public String getPoolSize()
	{
		return getProperty(POOL_SIZE);
	}

	/**
	 * @param string
	 */
	public void setPoolSize(String string)
	{
		setProperty(POOL_SIZE, string);
	}

	/**
	 * @return
	 */
	public String getManagementObjectManager()
	{
		return getProperty(MANAGEMENT_OBJECT_MANAGER);
	}

	/**
	 * @param string
	 */
	public void setManagementObjectManager(String string)
	{
		setProperty(MANAGEMENT_OBJECT_MANAGER, string);
	}

}
