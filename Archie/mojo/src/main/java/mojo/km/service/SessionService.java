//Source file: Z:/java.vob/MessagingLayer/MessageProcessing/SessionService.java

package mojo.km.service;

import java.util.*;

import mojo.km.config.MojoProperties;
import mojo.km.config.ServerProperties;
import mojo.km.messaging.Composite.CompositeResponse;
import javax.security.auth.Subject;

/**
 * Responsible for the aquisition of all responses from a server process.
 *
 *@author Eric A Amundson
 *@version 1.0
 * @modelguid {396A67D3-FCA8-4E23-8F8D-5C0C7A67092D}
 */
abstract public class SessionService implements IService, Runnable {
    
    //public final static String VITRIA = "Vitria";
    
	/** @modelguid {00A8157B-6DB6-4C80-823C-2E5E13240072} */
    public final static String DATAGRAM = "Datagram";
	/** @modelguid {87339562-F6C4-4F96-9D3A-C121C8D5049B} */
    public final static String SOCKET = "Socket";
	/** @modelguid {43002C03-B753-4AA5-9F71-59CB3B6A6E6D} */
    public final static String TUXUNSOL = "Unsol";
	/** @modelguid {2C954D72-2210-40DF-82A4-68655C9286BE} */
    public final static String DEQUEUE = "Dequeue";
	/** @modelguid {717BC275-5885-4FB2-B8FB-1F668B4EFCD2} */
    public final static String MULTI = "Multicast";
	/** @modelguid {B5BCE965-6F43-4D6F-BC9F-C1AB37B2744F} */
    public final static String WEBLOGIC = "WebLogic";
	/** @modelguid {EC109DD3-62DA-4731-A2B6-F01B07B65385} */
    protected static String userID = "";
	/** @modelguid {E0B2AB34-D2DC-462A-981D-B704EC96A2B2} */
    protected static String password = "";
	/** @modelguid {7C73A1A6-E0F9-4772-95C6-12F76F437215} */
    protected static String role = "";
    // set when connected
	/** @modelguid {C008950F-C343-4DCD-8AEB-95DF27D381A0} */
    protected static Subject user = null;
    // set when connected
    
	/** @modelguid {49165E58-EA17-4927-99FB-80A8FA189A29} */
    static CompositeResponse orgCompositeResponse = new CompositeResponse();
    
	/** @modelguid {06E3BBD0-ACBD-4318-99CE-3E73327FCE3E} */
    public SessionService() {
    }
    
    /**
     *    Start the service.
     * @modelguid {8C2E03FF-5313-41D5-B803-E83532FA5614}
     */
    abstract public void start();
    
    /**
     *     Stop session service from processing event loop.
     * @modelguid {8B0FF50F-F46F-4179-A51F-A8B3D19B71C1}
     */
    abstract public void stop();
    
    /**
     *     Set the session specific name this service will key off of.
     * @modelguid {91EEFAFF-5F45-4B9D-B0AE-97FE44B0E093}
     */
    abstract public boolean setSessionID(String aSessionID);
    
    /**
     *     @return the unique identifier for the user session.
     * @modelguid {6FCD44F0-CF6A-46DF-A078-DD8E855D01C7}
     */
    abstract public String getSessionID();
    
    /**
     *     Set the current user for ui.
     * @modelguid {A239D24E-C9A5-4BD2-9C04-CCBBB8356507}
     */
    static public void setUserID(String _userID) {
        userID = _userID;
    }
    
    /**
     *     Get the current application user.
     * @modelguid {28115518-52BC-4ECC-B68D-3A0880430A9A}
     */
    static public String getUserID() {
        return userID;
    }
    
    /**
     *     Set the current user for ui.
     * @modelguid {1A713896-90BF-4DEE-95B7-0716A4B63CEE}
     */
    static public void setPassword(String _password) {
        password = _password;
    }
    
    /**
     *     Get the current application user.
     * @modelguid {AFCCC924-EC80-48A9-BC64-28903D357936}
     */
    static public String getPassword() {
        return password;
    }
    
    /**
     *
     *     Execute the additional services related to establishing a valid session.
     * @modelguid {23BE785F-3D37-4F30-9DE5-6E9880EA1D48}
     */
    public void executeServices() { }
    
	/** @modelguid {A14CD824-CADF-44EB-9097-CBE7EEADCBAC} */
    static public CompositeResponse getLoginResponse() {
        return orgCompositeResponse;
    }
    
	/** @modelguid {D1669D94-E742-4AAC-8A33-55AF8D3FCF11} */
    abstract public void run();
    
    /**
     *     Returns a singleton instance of this class.  This is the only way to access an instance
     *     of this class.
     * @modelguid {753970B8-564A-418F-8B78-37CC84E1616A}
     */
    static public SessionService getSharedInstance() {

        return (SessionService) sharedInstance.get(serviceType);
    }
    
    /**
     *     Returns a singleton instance of this class.  This is the only way to access an instance
     *     of this class.
     *
     *     @param adapterName - name of concrete implementation, use: VITRIA, DATAGRAM, SOCKET
     * @modelguid {579DD60B-E169-4037-BD68-8C6573592433}
     */
    static public SessionService getSharedInstance(String adapterName) {
        serviceType = adapterName;
        setAdapter(adapterName);
        return (SessionService) sharedInstance.get(adapterName);
    }
    
	/** @modelguid {5F71CD6F-36FD-43D8-98AF-9B386B86C62A} */
    static public String getTopicType() {
        return serviceType;
    }
    
    /**
     *     Set the current application user.
     * @modelguid {F7176B4C-338D-4CBC-A872-4E6EC06AEC46}
     */
    static public void setUser(Subject aUser) {
        user = aUser;
    }
    
    /**
     *     Get the current application user.
     * @modelguid {0F0E721E-AFED-4C37-B812-BDF3FE941963}
     */
    static public Subject getUser() {
        return user;
    }
    
    /**
     *     Set the current role for the application user.
     * @modelguid {DCA0AAEF-E27D-4D58-A080-219FFAC81ABD}
     */
    static public void setRole(String aRole) {
        role = aRole;
    }
    
    /**
     *     Get the current role for the application user.
     * @modelguid {B5937B57-4AE4-49D7-A285-B1F78F1F8A6D}
     */
    static public String getRole() {
        return role;
    }
    
	/** @modelguid {D48D98C7-2015-455E-A04D-2FF293B98F1A} */
    static private void setAdapter(String serverName) {
        Class aClass = null;
        String className = null;
        try {
        	ServerProperties lServer = MojoProperties.getInstance().getServer(serverName);
        	if (lServer == null) {
        		throw new Exception("Server "+serverName+" is undefined.");
        	}
            className = lServer.getSessionServiceAdapter();
            aClass = Class.forName((String)className);
        } catch (Exception x) {
            System.out.println("\nUnable to load Service Adapter :: " + className + ".");
        }
        if (aClass != null) {
            try {
                sharedInstance.put(serverName, aClass.newInstance());
            } catch (Exception e) {
                System.out.println("\nUnable to load Service Adapter :: " + className + ".");
                throw new RuntimeException("Session was not established!");
            }
        }
    }


    
	/** @modelguid {A243518C-C3F6-4390-BE86-944EDDEE3F7B} */
    static Hashtable sharedInstance = new Hashtable();
	/** @modelguid {E1A2EE14-CC48-4984-98FB-1FADD63BB4BB} */
    static String serviceType = SOCKET;
}
