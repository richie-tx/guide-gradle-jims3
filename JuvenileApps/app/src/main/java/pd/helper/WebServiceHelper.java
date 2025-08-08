package pd.helper;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.juvenile.UpdateJuvenileServiceCTSInfoEvent;
import messaging.juvenile.UpdateJuvenileServiceEvent;
import mojo.km.config.AppProperties;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ServerTrustManger;
import mojo.km.security.Token;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import net.minidev.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import pd.contact.officer.PDOfficerProfileHelper;
import ui.common.UINotificationHelper;

public class WebServiceHelper
{
    
    //get Tokem authentication service.
    private Token getAuthenticationToken()   
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
	    String securityManagerServerName = AppProperties.getInstance().getProperty("SecurityManagerServerName");
	    url = new URL("https://" + securityManagerServerName + "/securityapi/getbearertoken/hc.cts.eid/4ct531d/jwebpass");
	    System.out.println("Token URL: " + url.toString() );
	    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
	    con.setRequestMethod("GET");

	    InputStream in = new BufferedInputStream(con.getInputStream());
	    System.out.println("Response Code: " + con.getResponseCode());

	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    token = mapper.readValue(in, Token.class);

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
     * @return  
     * */
    public void updateJUVService( IEvent event )
    {
	HttpsURLConnection con = null;
	UpdateJuvenileServiceEvent evt = (UpdateJuvenileServiceEvent) event;
	boolean isException=false;
	Token token = this.getAuthenticationToken();
	//Token  token = SecurityManagerWebServiceHelper.getAuthTokenURLConnection(); // token authentication
	try
	{
	    String securityManagerServerName = AppProperties.getInstance().getProperty("SecurityManagerServerName");
	    URL url = new URL("https://" + securityManagerServerName + "/JWEBAPI/CTS/InsertService");
	    con = (HttpsURLConnection) url.openConnection();
	    con.setRequestMethod("POST");
	    con.setRequestProperty("Content-Length", "0");
	    con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    con.setRequestProperty("Accept", "application/json");
	    con.setRequestProperty("Authorization", "Bearer " + token.getAccess_token() );

	    JSONObject cred = new JSONObject();
	    cred.put("CASENUM", evt.getCaseNum());
	    cred.put("TRACKINGNUM", evt.getTrackingNum());
	    cred.put("LCUSER", evt.getLcUser());
	    cred.put("ISSUECLERK", evt.getIssueClerk());
	    cred.put("ISSUEDATE", evt.getIssueDate());
	    cred.put("APPEARANCEDATE", evt.getAppearanceDate());
	    cred.put("SERVICESTATUSCODE", evt.getServiceStatus());
	    cred.put("SERVICETYPE", evt.getServiceType());

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
	   // userInfo = mapper.readValue(in, new TypeReference<SecurityManagerBaseResponse<UserAuthenticationResponse>>(){});
	    in.close();
	}
	catch (ProtocolException e)
	{
	    isException=true;	    
	    e.printStackTrace();	    
	}
	catch (MalformedURLException e)
	{
	    isException=true;
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    isException=true;	    
	    e.printStackTrace();
	}
	/*catch (Exception e) 
	{
		isException=true;
	    	sendCTSfailureEmail(evt);
		e.printStackTrace();
	}*/
	finally 
	{
	    try {
		  if(isException)
		  {
		      sendCTSfailureEmail(evt);
		  }
		} 
	    catch (Exception e) 
	    {
		e.printStackTrace();
	    }
	}	
    }
    public boolean sendCTSfailureEmail( UpdateJuvenileServiceEvent evt )
    {
	Collection<OfficerProfileResponseEvent>   securityRespEvent =  PDOfficerProfileHelper.getOfficerProfilesInUserGroup("JIMS2 subpoena to CTS transfer failure");
	if(securityRespEvent!=null)
	{
	     Iterator<OfficerProfileResponseEvent> securityRespIter = securityRespEvent.iterator();
		
	     while(securityRespIter.hasNext())
	     {
		OfficerProfileResponseEvent securityResEvent =	securityRespIter.next();
		if(securityResEvent.getEmail()!=null && !securityResEvent.getEmail().equals(""))
	    	{
		    	SendEmailEvent sendEmailEvent = new SendEmailEvent();
		    	StringBuffer message = new StringBuffer(100);
		    	String fromEmail = "jims2notification@itc.hctx.net";
		    	 
		    	
		    		sendEmailEvent.setFromAddress(fromEmail);
		    		//UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent,currentUserEmail);
		    		UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent,securityResEvent.getEmail());
		    		message.append("The following record(s) failed to transfer to JWEB/CTS for JIMS2 subpoena tracking and requires review/correction: ");		    		
		    		message.append(System.getProperty("line.separator"));
		    		message.append("TRACKING NUM: ");
		    		message.append( evt.getTrackingNum());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("CASENUM: ");
		    		message.append( evt.getCaseNum());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("LCUSER: ");
		    		message.append( evt.getLcUser());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("ISSUECLERK: ");
		    		message.append( evt.getIssueClerk());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("ISSUEDATE: ");
		    		message.append( evt.getIssueDate());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("APPEARANCEDATE: ");
		    		message.append( evt.getAppearanceDate());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("SERVICESTATUSCODE: ");
		    		message.append( evt.getServiceStatus());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("SERVICETYPE: ");
		    		message.append( evt.getServiceType());
		    		message.append(System.getProperty("line.separator"));		    		
		    		sendEmailEvent.setMessage(message.toString());
		    		sendEmailEvent.setSubject("Information Transfer Failure:  Webservice InsertService");
		    		CompositeResponse res = MessageUtil.postRequest(sendEmailEvent);
		    		MessageUtil.processReturnException( res ) ;
			    
	    	}
	     }
	 } 
	return true;
    }

    /*
     * 
     */
    public void updateJuvServiceCTSInfo( IEvent event )
    {
	HttpsURLConnection con = null;
	UpdateJuvenileServiceCTSInfoEvent evt = (UpdateJuvenileServiceCTSInfoEvent) event;
	boolean isException=false;
	
	Token token = this.getAuthenticationToken();
	//Token  token = SecurityManagerWebServiceHelper.getAuthTokenURLConnection(); // token authentication
	try
	{
	    String securityManagerServerName = AppProperties.getInstance().getProperty("SecurityManagerServerName");
	    URL url = new URL("https://" + securityManagerServerName + "/JWEBAPI/CTS/InsertServiceCTSInfo");
	    con = (HttpsURLConnection) url.openConnection();
	    con.setRequestMethod("POST");
	    con.setRequestProperty("Content-Length", "0");
	    con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    con.setRequestProperty("Accept", "application/json");
	    con.setRequestProperty("Authorization", "Bearer " + token.getAccess_token() );

	    JSONObject cred = new JSONObject();
	    cred.put("CASENUM", evt.getCaseNum());
	    cred.put("COURTID", "DC");
	    cred.put("COURTNUM", evt.getCourtNum());
	    cred.put("CROSSREGIONUPDATE", "N");
	    cred.put("DEFENDANT", evt.getDefendant());
	    cred.put("NAMETYPE", evt.getNameType());
	    if(StringUtils.isNotEmpty(evt.getPhone())){
		String deformForm = evt.getPhone();
		deformForm = deformForm.replaceAll("-", "");
		cred.put("PHONE", deformForm);
	    }
	    cred.put("PLAINTIFF", evt.getPltName());
	    cred.put("PPREXPRTNDT", evt.getPaperExpirationDate());
	    cred.put("SERVENAME", evt.getServeName());
	    cred.put("STATE", evt.getState());
	    cred.put("STREETNAME", evt.getStreetName());
	    cred.put("STREETNUM", evt.getStreetNum());
	    cred.put("TRACKINGNUM", evt.getTrackingNum());
	    cred.put("ZIP", evt.getZip());
	    cred.put("SUBPOENAFORIND", evt.getSubpoenaForInd());
	    cred.put("JUVENILENUM", evt.getJuvenileNum());
	    cred.put("LCUSER", evt.getLcUser());

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
	   // userInfo = mapper.readValue(in, new TypeReference<SecurityManagerBaseResponse<UserAuthenticationResponse>>(){});
	    in.close();
	}
	catch (ProtocolException e)
	{
	    isException=true;	    
	    e.printStackTrace();	    
	}
	catch (MalformedURLException e)
	{
	    isException=true;
	    e.printStackTrace();
	}
	catch (IOException e)
	{
	    isException=true;	    
	    e.printStackTrace();
	}
	/*catch (Exception e) 
	{
		isException=true;
	    	sendCTSfailureEmail(evt);
		e.printStackTrace();
	}*/
	finally 
	{
	    try {
		  if(isException)
		  {
		      sendCTSInfofailureEmail(evt);
		  }
		} 
	    catch (Exception e) 
	    {
		e.printStackTrace();
	    }
	}	
    }
    public boolean sendCTSInfofailureEmail( UpdateJuvenileServiceCTSInfoEvent evt )
    {
	Collection<OfficerProfileResponseEvent>   securityRespEvent =  PDOfficerProfileHelper.getOfficerProfilesInUserGroup("JIMS2 subpoena to CTS Info transfer failure");
	if(securityRespEvent!=null)
	{
	     Iterator<OfficerProfileResponseEvent> securityRespIter = securityRespEvent.iterator();
		
	     while(securityRespIter.hasNext())
	     {
		OfficerProfileResponseEvent securityResEvent =	securityRespIter.next();
		if(securityResEvent.getEmail()!=null && !securityResEvent.getEmail().equals(""))
	    	{
		    	SendEmailEvent sendEmailEvent = new SendEmailEvent();
		    	StringBuffer message = new StringBuffer(100);
		    	String fromEmail = "jims2notification@itc.hctx.net";
		    	 
		    	
		    		sendEmailEvent.setFromAddress(fromEmail);
		    		//UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent,currentUserEmail);
		    		UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent,securityResEvent.getEmail());
		    		message.append("The following record(s) failed to transfer to JWEB/CTS for JIMS2 subpoena tracking and requires review/correction: ");		    		
		    		message.append(System.getProperty("line.separator"));
		    		message.append("JUVNUM: ");
		    		message.append( evt.getJuvenileNum());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("TRACKING NUM: ");
		    		message.append( evt.getTrackingNum());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("CASENUM: ");
		    		message.append( evt.getCaseNum());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("COURTID: DC");
		    		message.append(System.getProperty("line.separator"));
		    		message.append("COURTNUM: ");
		    		message.append( evt.getCourtNum());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("CROSSREGIONUPDATE: N");
		    		message.append(System.getProperty("line.separator"));
		    		message.append("DEFENDANT: ");
		    		message.append( evt.getDefendant());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("NAMETYPE: ");
		    		message.append( evt.getNameType());
		    		message.append(System.getProperty("line.separator"));
		    		if(StringUtils.isNotEmpty(evt.getPhone()))
		    		{
        		    		String deformForm = evt.getPhone();
        		    		deformForm = deformForm.replaceAll("-", "");
        		    		message.append("PHONE: ");
        		    		message.append( deformForm);
        		    		message.append(System.getProperty("line.separator"));
		    		}
		    		message.append("PLAINTIFF: ");
		    		message.append( evt.getPltName());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("PPREXPRTNDT: ");
		    		message.append( evt.getPaperExpirationDate());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("SERVENAME: ");
		    		message.append( evt.getServeName());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("STATE: ");
		    		message.append( evt.getState());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("STREETNAME: ");
		    		message.append( evt.getStreetName());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("STREETNUM: ");
		    		message.append( evt.getStreetNum());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("ZIP: ");
		    		message.append( evt.getZip());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("SUBPOENAFORIND: ");
		    		message.append( evt.getSubpoenaForInd());
		    		message.append(System.getProperty("line.separator"));
		    		message.append("LCUSER: ");
		    		message.append( evt.getLcUser());
		    		message.append(System.getProperty("line.separator"));
		    		sendEmailEvent.setMessage(message.toString());
		    		sendEmailEvent.setSubject("Information Transfer Failure:  Webservice InsertServiceCTSInfo");
		    		CompositeResponse res = MessageUtil.postRequest(sendEmailEvent);
		    		MessageUtil.processReturnException( res ) ;
			    
	    	}
	     }
	 } 
	return true;
    }
    
}
