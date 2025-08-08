package mojo.km.security.helper;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import mojo.km.config.AppProperties;
import mojo.km.security.AgencyEntityBean;
import mojo.km.security.DepartmentEntityBean;
import mojo.km.security.SecurityManagerBaseResponse;
import mojo.km.security.ServerTrustManger;
import mojo.km.security.Token;
import mojo.km.security.UpdatePasswordResponse;
import mojo.km.security.UserAuthenticationResponse;
import mojo.km.security.UserEntityBean;
import net.minidev.json.JSONObject;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


/** Webservice Helper class. 86322
 * 
 * @author sthyagarajan */
public class SecurityManagerWebServiceHelper
{

    //get Tokem authentication service.
    public static Token getAuthTokenURLConnection()
    {
	SSLContext sc = null;
	try
	{
	    sc = SSLContext.getInstance("SSL");
	}
	catch (NoSuchAlgorithmException e1)
	{
	    e1.printStackTrace();
	}
	ServerTrustManger serverTrustManager = new ServerTrustManger();
	try
	{
	    sc.init(null, new TrustManager[]
	    { serverTrustManager }, new java.security.SecureRandom());
	}
	catch (KeyManagementException e)
	{
	    e.printStackTrace();
	}

	HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

	// Create all-trusting host name verifier
	HostnameVerifier allHostsValid = new HostnameVerifier()
	{
	    @Override
	    public boolean verify(String hostname, SSLSession session)
	    {
		return true;
	    }
	};

	HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	Token token = null;
	URL url;
	try
	{
	    String securityManagerServerName = AppProperties.getInstance().getProperty("IdentityManagerServerName");
	    url = new URL("https://" + securityManagerServerName + "/idsrv/connect/token");
	    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
	    con.setRequestMethod("POST");
	    con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	    con.setRequestProperty("Content-Length", "0");
	    con.setRequestProperty("Accept", "application/json");

	    con.setUseCaches(false);
	    con.setDoInput(true);
	    con.setDoOutput(true);
	    
	    String postData = "grant_type=client_credentials&client_id=jims2client&client_secret=J1MS@L33T";                          
	    // Get the output stream to write the data            
	    try (OutputStream os = con.getOutputStream();                  
	    		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"))) {
	    	writer.write(postData);             
	    	}
	

	    InputStream in = new BufferedInputStream(con.getInputStream());
	    System.out.println("Response Code: " + con.getResponseCode());

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    token = mapper.readValue(in, Token.class);

	    System.out.println("url " + url);
	    System.out.println("token " + token.getAccess_token());
	    System.out.println("expiration " + token.getExpires_in());
	    System.out.println("type " + token.getToken_type());
	    in.close();
	}
	catch (MalformedURLException e)
	{
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
	return token;
    }

    /** Get user authentication
     * 
     * @param token
     * @param userId
     * @param password
     * @return user -SecurityManagerUser */
    public static SecurityManagerBaseResponse<UserAuthenticationResponse> getUserAuthentication(Token token, String userId, String password, String credentialType, String noOfAttempts)
    {
	HttpsURLConnection con = null;
	SecurityManagerBaseResponse<UserAuthenticationResponse> userInfo = null;

	try
	{
	    String securityManagerServerName = AppProperties.getInstance().getProperty("SecurityManagerServerName");
	    URL url = new URL("https://" + securityManagerServerName + "/securityapi/ValidateUserCred");
	    con = (HttpsURLConnection) url.openConnection();
	    con.setRequestMethod("POST");
	    con.setRequestProperty("Content-Length", "0");
	    con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    con.setRequestProperty("Accept", "application/json");
	    con.setRequestProperty("Authorization", "Bearer " + token.getAccess_token());

	    JSONObject cred = new JSONObject();
	    cred.put("username", userId);
	    cred.put("password", password);
	    cred.put("credentialtype", credentialType);
	    cred.put("numberofattempts", noOfAttempts);

	    con.setDoOutput(true);
	    OutputStream wr = con.getOutputStream();
	    wr.write(cred.toString().getBytes("UTF-8"));
	    wr.flush();
	    wr.close();

	    int responseCode = con.getResponseCode();
	    System.out.println("\nSending 'POST' request to URL : " + url);
	    System.out.println("Post parameters : " + cred.toJSONString());
	    System.out.println("Response Code : " + responseCode);

	    InputStream in = new BufferedInputStream(con.getInputStream());
	    System.out.println("Response Code: " + con.getResponseCode());

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    userInfo = mapper.readValue(in, new TypeReference<SecurityManagerBaseResponse<UserAuthenticationResponse>>(){});
	    in.close();
	}
	catch (ProtocolException e)
	{
	    e.printStackTrace();
	}
	catch (MalformedURLException e)
	{
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
	return userInfo;
    }

    /** updatePasswordServiceProvider
     * 
     * @param token
     * @param email
     * @param oldPassword
     * @param newPassword
     * @param smUserId
     * @return */
    public static SecurityManagerBaseResponse<UpdatePasswordResponse> updatePasswordServiceProvider(Token token, String email, String oldPassword, String newPassword, String smUserId)
    {
	HttpsURLConnection con = null;
	SecurityManagerBaseResponse<UpdatePasswordResponse> userInfo = null;

	try
	{

	    String securityManagerServerName = AppProperties.getInstance().getProperty("SecurityManagerServerName");
	    URL url = new URL("https://" + securityManagerServerName + "/securityapi/UpdateEmailPassword");
	    con = (HttpsURLConnection) url.openConnection();
	    con.setRequestMethod("POST");
	    con.setRequestProperty("Content-Length", "0");
	    con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    con.setRequestProperty("Accept", "application/json");
	    con.setRequestProperty("Authorization", "Bearer " + token.getAccess_token());

	    JSONObject cred = new JSONObject();
	    cred.put("userid", smUserId);
	    cred.put("email", email);
	    cred.put("oldpassword", oldPassword);
	    cred.put("newPassword", newPassword);

	    con.setDoOutput(true);
	    OutputStream wr = con.getOutputStream();
	    wr.write(cred.toString().getBytes("UTF-8"));
	    wr.flush();
	    wr.close();

	    int responseCode = con.getResponseCode();
	    System.out.println("\nSending 'POST' request to URL : " + url);
	    System.out.println("Post parameters : " + cred.toJSONString());
	    System.out.println("Response Code : " + responseCode);

	    InputStream in = new BufferedInputStream(con.getInputStream());
	    System.out.println("Response Code: " + con.getResponseCode());

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    userInfo = mapper.readValue(in, new TypeReference<SecurityManagerBaseResponse<UpdatePasswordResponse>>(){});
	    in.close();
	}
	catch (ProtocolException e)
	{
	    e.printStackTrace();
	}
	catch (MalformedURLException e)
	{
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
	return userInfo;
    }

    /** @param token
     * @param userId
     * @param password
     * @param credentialType
     * @param noOfAttempts
     * @return */
    @Deprecated
    public static SecurityManagerBaseResponse<UserEntityBean> getUserProfileBySMUserId(Token token, String smUserId)
    {
	HttpsURLConnection con = null;
	SecurityManagerBaseResponse<UserEntityBean> userInfo = null;
	try
	{
	    String securityManagerServerName = AppProperties.getInstance().getProperty("SecurityManagerServerName");
	    URL url = new URL("https://" + securityManagerServerName + "/securityapi/GetUserProfileByUserID/" + smUserId);
	    con = (HttpsURLConnection) url.openConnection();
	    con.setRequestMethod("GET");
	    con.setRequestProperty("Content-Length", "0");
	    con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    con.setRequestProperty("Accept", "application/json");
	    con.setRequestProperty("Authorization", "Bearer " + token.getAccess_token());

	    int responseCode = con.getResponseCode();
	    System.out.println("\nSending 'POST' request to URL : " + url);
	    System.out.println("Response Code : " + responseCode);

	    InputStream in = new BufferedInputStream(con.getInputStream());
	    System.out.println("Response Code: " + con.getResponseCode());

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    userInfo = mapper.readValue(in, new TypeReference<SecurityManagerBaseResponse<UserEntityBean>>() {});
	    in.close();
	}
	catch (ProtocolException e)
	{
	    e.printStackTrace();
	}
	catch (MalformedURLException e)
	{
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
	return userInfo;
    }

    /** getUserGroupByIdOrName
     * 
     * @param token
     * @param groupId
     *            ,groupName
     * @return SecurityManagerBaseResponse<UserEntityBean> */
    public static SecurityManagerBaseResponse<List<UserEntityBean>> getUserGroupByIdOrName(Token token, String groupId, String groupName)
    {
	HttpsURLConnection con = null;
	SecurityManagerBaseResponse<List<UserEntityBean>> userInfo = null;

	try
	{
	    String securityManagerServerName = AppProperties.getInstance().getProperty("SecurityManagerServerName");
	    URL url = new URL("https://" + securityManagerServerName + "/securityapi/GetUserGroupByIdOrName");
	    con = (HttpsURLConnection) url.openConnection();
	    con.setRequestMethod("POST");
	    con.setRequestProperty("Content-Length", "0");
	    con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    con.setRequestProperty("Accept", "application/json");
	    con.setRequestProperty("Authorization", "Bearer " + token.getAccess_token());

	    JSONObject cred = new JSONObject();
	    cred.put("groupname", groupId);
	    cred.put("groupdescription", groupName);

	    con.setDoOutput(true);
	    OutputStream wr = con.getOutputStream();
	    wr.write(cred.toString().getBytes("UTF-8"));
	    wr.flush();
	    wr.close();

	    int responseCode = con.getResponseCode();
	    System.out.println("\nSending 'POST' request to URL : " + url);
	    System.out.println("Post parameters : " + cred.toJSONString());
	    System.out.println("Response Code : " + responseCode);

	    InputStream in = new BufferedInputStream(con.getInputStream());
	    System.out.println("Response Code: " + con.getResponseCode());

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    userInfo = mapper.readValue(in, new TypeReference<SecurityManagerBaseResponse<List<UserEntityBean>>>(){});
	    in.close();
	}
	catch (ProtocolException e)
	{
	    e.printStackTrace();
	}
	catch (MalformedURLException e)
	{
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
	return userInfo;
    }
    
    
    /** getSecurityUserByJUCode
     * 
     * @param token
     * @param jucode
     * @return UserProfileResponse */
    public static SecurityManagerBaseResponse<UserEntityBean> getSecurityUserByJUCode(Token token, String jucode)
    {
	HttpsURLConnection con = null;
	SecurityManagerBaseResponse<UserEntityBean> userInfo = null;
	if (token != null && jucode != null && !jucode.isEmpty())
	{
	    try
	    {
		String securityManagerServerName = AppProperties.getInstance().getProperty("SecurityManagerServerName");
		URL url = new URL("https://" + securityManagerServerName + "/securityapi/GetSecurityUserByJUCode");
		con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Length", "0");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Authorization", "Bearer " + token.getAccess_token());

		JSONObject cred = new JSONObject();
		cred.put("username", jucode.trim());

		con.setDoOutput(true);
		OutputStream wr = con.getOutputStream();
		wr.write(cred.toString().getBytes("UTF-8"));
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + cred.toJSONString());
		System.out.println("Response Code : " + responseCode);

		InputStream in = new BufferedInputStream(con.getInputStream());
		System.out.println("Response Code: " + con.getResponseCode());

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		userInfo = mapper.readValue(in, new TypeReference<SecurityManagerBaseResponse<UserEntityBean>>(){});
		in.close();
	    }
	    catch (ProtocolException e)
	    {
		e.printStackTrace();
	    }
	    catch (MalformedURLException e)
	    {
		e.printStackTrace();
	    }
	    catch (IOException e)
	    {
		e.printStackTrace();
	    }
	}
	return userInfo;
    }

    /** Get User Profile by Department, name or uvCode
     * 
     * @param token
     * @param jucode
     * @return one or more user-profile */
    public static SecurityManagerBaseResponse<List<UserEntityBean>> getUsers(Token token, String jucode, String fName, String lName, String deptName, String departmentId)
    {
	HttpsURLConnection con = null;
	SecurityManagerBaseResponse<List<UserEntityBean>> userInfo = null;

	try
	{
	    String securityManagerServerName = AppProperties.getInstance().getProperty("SecurityManagerServerName");
	    URL url = new URL("https://" + securityManagerServerName + "/securityapi/GetUserProfile");
	    con = (HttpsURLConnection) url.openConnection();
	    con.setRequestMethod("POST");
	    con.setRequestProperty("Content-Length", "0");
	    con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    con.setRequestProperty("Accept", "application/json");
	    con.setRequestProperty("Authorization", "Bearer " + token.getAccess_token());

	    JSONObject cred = new JSONObject();
	    cred.put("deptname", deptName);
	    cred.put("username", jucode);
	    cred.put("firstname", fName);
	    cred.put("lastname", lName);
	    cred.put("deptcode", departmentId);

	    con.setDoOutput(true);
	    OutputStream wr = con.getOutputStream();
	    wr.write(cred.toString().getBytes("UTF-8"));
	    wr.flush();
	    wr.close();

	    int responseCode = con.getResponseCode();
	    System.out.println("\nSending 'POST' request to URL : " + url);
	    System.out.println("Post parameters : " + cred.toJSONString());
	    System.out.println("Response Code : " + responseCode);

	    InputStream in = new BufferedInputStream(con.getInputStream());
	    System.out.println("Response Code: " + con.getResponseCode());

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    userInfo = mapper.readValue(in, new TypeReference<SecurityManagerBaseResponse<List<UserEntityBean>>>(){});
	    in.close();
	}
	catch (ProtocolException e)
	{
	    e.printStackTrace();
	}
	catch (MalformedURLException e)
	{
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
	return userInfo;
    }

    /** getServiceProviderContacts
     * 
     * @param token
     * @param jucode
     * @return UserProfileResponse */
    public static SecurityManagerBaseResponse<List<UserEntityBean>> getServiceProviderContacts(Token token, String jucode)
    {
	HttpsURLConnection con = null;
	SecurityManagerBaseResponse<List<UserEntityBean>> userInfo = null;
	if (token != null && jucode != null && !jucode.isEmpty())
	{
	    try
	    {
		String securityManagerServerName = AppProperties.getInstance().getProperty("SecurityManagerServerName");
		URL url = new URL("https://" + securityManagerServerName + "/securityapi/GetAllUsersAssociatedWithOneJucode");
		con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Length", "0");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Authorization", "Bearer " + token.getAccess_token());

		JSONObject cred = new JSONObject();
		cred.put("username", jucode.trim());

		con.setDoOutput(true);
		OutputStream wr = con.getOutputStream();
		wr.write(cred.toString().getBytes("UTF-8"));
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + cred.toJSONString());
		System.out.println("Response Code : " + responseCode);

		InputStream in = new BufferedInputStream(con.getInputStream());
		System.out.println("Response Code: " + con.getResponseCode());

		ObjectMapper mapper = new ObjectMapper();
		userInfo = mapper.readValue(in, new TypeReference<SecurityManagerBaseResponse<List<UserEntityBean>>>(){});
		in.close();
	    }
	    catch (ProtocolException e)
	    {
		e.printStackTrace();
	    }
	    catch (MalformedURLException e)
	    {
		e.printStackTrace();
	    }
	    catch (IOException e)
	    {
		e.printStackTrace();
	    }
	}
	return userInfo;
    }

    /** Get Department information from SM.
     * 
     * @param token
     * @param departmentId
     * @return */
    public static SecurityManagerBaseResponse<List<DepartmentEntityBean>> getDepartmentById(Token token, String departmentId)
    {
	HttpsURLConnection con = null;
	SecurityManagerBaseResponse<List<DepartmentEntityBean>> department = null;
	if (token != null && departmentId != null && !departmentId.isEmpty())
	{
	    try
	    {
		String securityManagerServerName = AppProperties.getInstance().getProperty("SecurityManagerServerName");
		URL url = new URL("https://" + securityManagerServerName + "/securityapi/GetDepartmentsByCodeOrDescription");
		con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Length", "0");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Authorization", "Bearer " + token.getAccess_token());

		JSONObject cred = new JSONObject();
		cred.put("departmentid", departmentId.trim());
		cred.put("departmentdescription", "");

		con.setDoOutput(true);
		OutputStream wr = con.getOutputStream();
		wr.write(cred.toString().getBytes("UTF-8"));
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + cred.toJSONString());
		System.out.println("Response Code : " + responseCode);

		InputStream in = new BufferedInputStream(con.getInputStream());
		System.out.println("Response Code: " + con.getResponseCode());

		ObjectMapper mapper = new ObjectMapper();
		department = mapper.readValue(in, new TypeReference<SecurityManagerBaseResponse<List<DepartmentEntityBean>>>(){});
		in.close();
	    }
	    catch (ProtocolException e)
	    {
		e.printStackTrace();
	    }
	    catch (MalformedURLException e)
	    {
		e.printStackTrace();
	    }
	    catch (IOException e)
	    {
		e.printStackTrace();
	    }
	}
	return department;
    }

    /** Get Agency information from SM.
     * 
     * @param token
     * @param agencyId
     *            , agencyName
     * @return SecurityManagerBaseResponse<List<AgencyEntityBean>> */
    public static SecurityManagerBaseResponse<List<AgencyEntityBean>> getAgencyByCodeOrDescription(Token token, String agencyId, String agencyName)
    {
	HttpsURLConnection con = null;
	SecurityManagerBaseResponse<List<AgencyEntityBean>> agencyBaseResponse = null;
	try
	{
	    String securityManagerServerName = AppProperties.getInstance().getProperty("SecurityManagerServerName");
	    URL url = new URL("https://" + securityManagerServerName + "/securityapi/GetAgenciesByCodeOrDescription");
	    con = (HttpsURLConnection) url.openConnection();
	    con.setRequestMethod("POST");
	    con.setRequestProperty("Content-Length", "0");
	    con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    con.setRequestProperty("Accept", "application/json");
	    con.setRequestProperty("Authorization", "Bearer " + token.getAccess_token());

	    JSONObject cred = new JSONObject();
	    cred.put("agencyid", agencyId);
	    cred.put("agencyname", agencyName);

	    con.setDoOutput(true);
	    OutputStream wr = con.getOutputStream();
	    wr.write(cred.toString().getBytes("UTF-8"));
	    wr.flush();
	    wr.close();

	    int responseCode = con.getResponseCode();
	    System.out.println("\nSending 'POST' request to URL : " + url);
	    System.out.println("Post parameters : " + cred.toJSONString());
	    System.out.println("Response Code : " + responseCode);

	    InputStream in = new BufferedInputStream(con.getInputStream());
	    System.out.println("Response Code: " + con.getResponseCode());

	    ObjectMapper mapper = new ObjectMapper();
	    agencyBaseResponse = mapper.readValue(in, new TypeReference<SecurityManagerBaseResponse<List<AgencyEntityBean>>>(){});
	    in.close();
	}
	catch (ProtocolException e)
	{
	    e.printStackTrace();
	}
	catch (MalformedURLException e)
	{
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
	return agencyBaseResponse;
    }

    /** Get Agency By Agency Id
     * 
     * @param token
     * @param agencyId
     * @return AgencyEntityBean */
    public static SecurityManagerBaseResponse<AgencyEntityBean> getAgencyByAgencyId(Token token, String agencyId)
    {
	HttpsURLConnection con = null;
	SecurityManagerBaseResponse<AgencyEntityBean> agency = null;
	if (token != null && agencyId != null && !agencyId.isEmpty())
	{
	    try
	    {
		String securityManagerServerName = AppProperties.getInstance().getProperty("SecurityManagerServerName");
		URL url = new URL("https://" + securityManagerServerName + "/securityapi/GetAgencyByAgencyId");
		con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Length", "0");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Authorization", "Bearer " + token.getAccess_token());

		JSONObject cred = new JSONObject();
		cred.put("agencyid", agencyId);

		con.setDoOutput(true);
		OutputStream wr = con.getOutputStream();
		wr.write(cred.toString().getBytes("UTF-8"));
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + cred.toJSONString());
		System.out.println("Response Code : " + responseCode);

		InputStream in = new BufferedInputStream(con.getInputStream());
		System.out.println("Response Code: " + con.getResponseCode());

		ObjectMapper mapper = new ObjectMapper();
		agency = mapper.readValue(in, new TypeReference<SecurityManagerBaseResponse<AgencyEntityBean>>(){});
		in.close();
	    }
	    catch (ProtocolException e)
	    {
		e.printStackTrace();
	    }
	    catch (MalformedURLException e)
	    {
		e.printStackTrace();
	    }
	    catch (IOException e)
	    {
		e.printStackTrace();
	    }
	}
	return agency;
    }

    /** Get Department information from SM.
     * 
     * @param token
     * @param departmentId
     * @return */
    public static SecurityManagerBaseResponse<List<DepartmentEntityBean>> getDepartmentsByCodeOrDescription(Token token, String departmentId, String departmentName)

    {
	HttpsURLConnection con = null;
	SecurityManagerBaseResponse<List<DepartmentEntityBean>> department = null;
	try
	{
	    String securityManagerServerName = AppProperties.getInstance().getProperty("SecurityManagerServerName");
	    URL url = new URL("https://" + securityManagerServerName + "/securityapi/GetDepartmentsByCodeOrDescription");
	    con = (HttpsURLConnection) url.openConnection();
	    con.setRequestMethod("POST");
	    con.setRequestProperty("Content-Length", "0");
	    con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    con.setRequestProperty("Accept", "application/json");
	    con.setRequestProperty("Authorization", "Bearer " + token.getAccess_token());

	    JSONObject cred = new JSONObject();
	    cred.put("departmentid", departmentId);
	    cred.put("departmentdescription", departmentName);

	    con.setDoOutput(true);
	    OutputStream wr = con.getOutputStream();
	    wr.write(cred.toString().getBytes("UTF-8"));
	    wr.flush();
	    wr.close();

	    int responseCode = con.getResponseCode();
	    System.out.println("\nSending 'POST' request to URL : " + url);
	    System.out.println("Post parameters : " + cred.toJSONString());
	    System.out.println("Response Code : " + responseCode);

	    InputStream in = new BufferedInputStream(con.getInputStream());
	    System.out.println("Response Code: " + con.getResponseCode());

	    ObjectMapper mapper = new ObjectMapper();
	    department = mapper.readValue(in, new TypeReference<SecurityManagerBaseResponse<List<DepartmentEntityBean>>>(){});
	    in.close();
	}
	catch (ProtocolException e)
	{
	    e.printStackTrace();
	}
	catch (MalformedURLException e)
	{
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    e.printStackTrace();
	}
	return department;
    }
}
