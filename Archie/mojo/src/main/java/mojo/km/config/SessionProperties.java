package mojo.km.config;

/** @modelguid {CA6C9C1E-8964-4BB5-8F33-68EC13C8A019} */
public class SessionProperties extends GenericProperties {
	/** @modelguid {67046C84-13FE-4B3E-BB09-4FFFAB1DEC13} */
	public static final String XA_DATASOURCE_CLASSNAME = "XADataSourceClassname";
	/** @modelguid {0B04139E-0FC7-4772-8E20-CD9FB43FD7E9} */
	public static final String CLIENT_URL = "ClientURL";
	/** @modelguid {99F4F987-CFB2-4EBB-BCC9-CFD3047CA79D} */
	public static final String CLUSTER_STRATEGY = "ClusterStrategy";
	/** @modelguid {395212A9-1174-400A-9A2A-511A2B8C8D42} */
	public static final String CONNECTION_POOL_ENABLED = "ConnectionPool";
	/** @modelguid {E562A332-CCED-4B68-A72D-43AD941F33B4} */
	public static final String DATASOURCE = "DataSource";
	/** @modelguid {BEC7ACD7-07D2-4E03-A03F-D979A847DE13} */
	public static final String DB_PASSWORD = "DbPassword";
	/** @modelguid {21EE8256-6BB3-418A-9E6B-C29A204B9E66} */
	public static final String DB_URL = "DbURL";
	/** @modelguid {4656E02C-D609-4D12-8AF2-9808E5383E52} */
	public static final String DB_USERID = "DbUserID";
	/** @modelguid {FC51F6B6-43C3-47C3-950E-BFA85CA24625} */
	public static final String DEBUG_MODE = "DebugMode";
	/** @modelguid {0BAB0FEB-98DB-49E3-80CF-EB804FC12375} */
	public static final String ENABLE_CONNECTION_POOL = "enableConnectionPool";
	/** @modelguid {4F756F7A-FE9D-451D-A359-CE678A7EBDBD} */
	public static final String HTTP_URL = "HttpURL";
	/** @modelguid {12C84E93-A835-4F77-9642-2AE0A07090B2} */
	public static final String LAST_ACCESSED = "LastAccessed";
	/** @modelguid {DD73A3A0-D2F9-43EB-BE26-6F3342269068} */
	public static final String LOGIN_CALLBACK = "LoginCallback";
	/** @modelguid {F6DEDF60-10B4-406A-8CBD-7EC52681147B} */
	public static final String PROJECT_CLASS = "ProjectClass";
	/** @modelguid {0F7AED11-B916-4FCF-BDA7-77CE3383A737} */
	public static final String PROJECT_PATH = "ProjectPath";
	/** @modelguid {F58D2FCF-0568-425F-B69F-757EDD003DE9} */
	public static final String SERVER_PASSWORD = "ServerPassword";
	/** @modelguid {F49D5FCB-C8C5-4A3F-9447-E9DD0B104A91} */
	public static final String SERVER_URL = "ServerURL";
	/** @modelguid {0977E3FA-F394-45D1-9B45-763D862CFD19} */
	public static final String SERVER_USERID = "ServerUserID";
	public static final String SERVER_CONTEXT_CLASS = "ServerContextClass";
	/** @modelguid {98FCBDB3-D6EB-49DA-ABBA-DB0015DD09D9} */
	public static final String WORKFLOW_CAPABLE = "workflowCapable";
	/** @modelguid {9A5E6B18-96A2-454E-853D-51DA6021AC56} */
	public static final String WORKFLOW_ENABLED = "WorkflowEnabled";

	/** @modelguid {9B1CF236-90CE-488F-93F8-C2469EAC46E2} */
	public static SessionProperties getInstance() {
		ServerProperties lServer = ServerProperties.getInstance();
		if (lServer != null) { 
			return lServer.getSessionProperties();
		} else {
			return null;
		}
	}
    
	/** @modelguid {EE2B97B4-AA8D-436B-81D2-8558050647BA} */
	public static SessionProperties getInstance(String aServerName) {
		ServerProperties lServer = ServerProperties.getInstance(aServerName);
		if (lServer != null) { 
			return lServer.getSessionProperties();
		} else {
			return null;
		}
	}
	
	/** @modelguid {A5CAB44F-B788-4735-B734-2794CCD3CC7D} */
	public boolean isWorkflowEnabled() {
		return getProperty(WORKFLOW_ENABLED) != null && getProperty(WORKFLOW_ENABLED).equals("true");
	}

	/** @modelguid {D46DC2A0-98F4-4CDD-83BF-9C07236C854D} */
	public boolean isWorkflowCapable() {
		return getProperty(WORKFLOW_CAPABLE) != null && getProperty(WORKFLOW_CAPABLE).equals("true");
	}

	/** @modelguid {34A5C50E-E743-4EF7-8832-56D409D1D9F3} */
	public String getServerUserID() {
		return getProperty(SERVER_USERID);
	}

	/** @modelguid {FA4D9217-5FA5-439F-AFEC-C686C3F3F761} */
	public String getServerURL() {
		return getProperty(SERVER_URL);
	}

	/** @modelguid {F68A79EA-450E-4798-95D7-85095B60000C} */
	public String getServerPassword() {
		return getProperty(SERVER_PASSWORD);
	}

	public String getServerContextClass() {
		return getProperty(SERVER_CONTEXT_CLASS);
	}
	/** @modelguid {DF2468CA-F228-490F-932E-3F65EC42CE21} */
	public String getProjectPath() {
		return getProperty(PROJECT_PATH);
	}

	/** @modelguid {6F653FDD-1839-44B5-AACC-1A94DF118F98} */
	public String getProjectClass() {
		return getProperty(PROJECT_CLASS);
	}

	/** @modelguid {CB083A91-3ABD-42E1-BB93-BCC73331CD66} */
	public String getLoginCallback() {
		return getProperty(LOGIN_CALLBACK);
	}

	/** @modelguid {07D2F251-8463-4141-8776-AFEA87464AC7} */
	public String getLastAccessed() {
		return getProperty(LAST_ACCESSED);
	}

	/** @modelguid {D40F8878-64BD-4944-A361-6A1E4CE94C72} */
	public String getHttpURL() {
		return getProperty(HTTP_URL);
	}

	/** @modelguid {A11B8E57-8AE6-4EC5-A5ED-6729BFCA264B} */
	public boolean enableConnectionPool() {
		return getProperty(ENABLE_CONNECTION_POOL) != null && getProperty(ENABLE_CONNECTION_POOL).equals("true");
	}

	/** @modelguid {F5158831-FB57-4F9B-AF38-126EA3FF4353} */
	public boolean isDebugMode() {
		return getProperty(DEBUG_MODE) != null && getProperty(DEBUG_MODE).equals("true");
	}

	/** @modelguid {BCE0A758-E858-4914-91A8-DA6D293DA973} */
	public String getDbUserID() {
		return getProperty(DB_USERID);
	}

	/** @modelguid {1CA50181-91D8-4F02-A587-0EA325EE6B5F} */
	public String getDbURL() {
		return getProperty(DB_URL);
	}

	/** @modelguid {A3206262-D99D-4593-8389-D1A214ECB81E} */
	public String getDbPassword() {
		return getProperty(DB_PASSWORD);
	}

	/** @modelguid {5A365437-709B-4A7D-AE6C-41BFDFA2BDB5} */
	public String getDataSource() {
		return getProperty(DATASOURCE);
	}

	/** @modelguid {8F14C841-BB8E-468F-82BE-CC14E6F315E3} */
	public boolean isConnectionPoolEnabled() {
		return getProperty(CONNECTION_POOL_ENABLED) != null && getProperty(CONNECTION_POOL_ENABLED).equals("true");
	}

	/** @modelguid {63EA105A-C524-4621-BE28-83B58B7AEBB2} */
	public String getClusterStrategy() {
		return getProperty(CLUSTER_STRATEGY);
	}

	/** @modelguid {55407B50-6D74-4F57-95FF-F905508FE9C3} */
	public String getClientURL() {
		return getProperty(CLIENT_URL);
	}

	/** @modelguid {8F9AA1FF-1F1C-49C9-9A0F-845E924D304A} */
	public String getXADatasourceClassname() {
		return getProperty(XA_DATASOURCE_CLASSNAME);
	}

	/** @modelguid {5BFFAF1B-7315-4285-9B9E-B131C7E73349} */
	public void setDbURL(String aString) {
		setProperty(DB_URL, aString);
	}

	/** @modelguid {B30AAA7E-F7FC-429A-84E2-C50BEF38A76D} */
	public void setDbUserID(String aString) {
		setProperty(DB_USERID, aString);
	}

	/** @modelguid {A439450C-5E8D-4155-AAD7-89C3B70D8C51} */
	public void setDbPassword(String aString) {
		setProperty(DB_PASSWORD, aString);
	}

	/** @modelguid {15892F10-E41A-4C78-A8FE-81EBD7EC1F7A} */
	public void setServerURL(String aString) {
		setProperty(SERVER_URL, aString);
	}

	/** @modelguid {549562EB-403B-424D-849C-9F89C9236873} */
	public void setServerUserID(String aString) {
		setProperty(SERVER_USERID, aString);
	}

	/** @modelguid {F5773FDC-67A9-4544-93A1-CB2B42DDA48B} */
	public void setServerPassword(String aString) {
		setProperty(SERVER_PASSWORD, aString);
	}

	public void setServerContextClass(String aString) {
		setProperty(SERVER_CONTEXT_CLASS, aString);
	}
	/** @modelguid {5164D1B6-247E-4FE0-8328-8A107C8AA4CE} */
	public void setHttpURL(String aString) {
		setProperty(HTTP_URL, aString);
	}

	/** @modelguid {1C7F165A-2840-408D-8D49-C47AE017043D} */
	public void setProjectClass(String aString) {
		setProperty(PROJECT_CLASS, aString);
	}

	/**
	 * @param flag
	 * @modelguid {CDE85FC1-3381-4558-858E-47F556CC0AAE}
	 */
	public void setDebugMode(boolean flag) {
		setProperty(DEBUG_MODE, flag+"");
	}

	/**
	 * @param string
	 * @modelguid {CF6454F5-C528-4766-B430-FABD60195709}
	 */
	public void setXADatasourceClassname(String aString) {
		setProperty(XA_DATASOURCE_CLASSNAME, aString);
	}

	/**
	 * @param flag
	 * @modelguid {ED3D8F6F-D703-4328-97C9-A27AFB2D27DC}
	 */
	public void enableConnectionPool(boolean flag) {
		setProperty(ENABLE_CONNECTION_POOL, flag+"");
	}

	/**
	 * @param string
	 * @modelguid {679D7FD8-3F96-48AE-9EF7-52217C875F03}
	 */
	public void setClusterStrategy(String aString) {
		setProperty(CLUSTER_STRATEGY, aString);
	}

}
