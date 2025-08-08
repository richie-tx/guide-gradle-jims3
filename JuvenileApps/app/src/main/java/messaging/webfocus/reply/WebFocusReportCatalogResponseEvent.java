/*
 * Created on Feb 20, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.webfocus.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author RYoung
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WebFocusReportCatalogResponseEvent extends ResponseEvent
{
    
    private String webFocusCatId;
    private String reportId;
    private String reportDesc;
    private String webFocusName;
    private String url;
    private String userParm;
    private String passwordParm;
    
    
    
    

    /**
     * @return Returns the passwordParm.
     */
    public String getPasswordParm()
    {
        return passwordParm;
    }
    /**
     * @param passwordParm The passwordParm to set.
     */
    public void setPasswordParm(String passwordParm)
    {
        this.passwordParm = passwordParm;
    }
    /**
     * @return Returns the reportDesc.
     */
    public String getReportDesc()
    {
        return reportDesc;
    }
    /**
     * @param reportDesc The reportDesc to set.
     */
    public void setReportDesc(String reportDesc)
    {
        this.reportDesc = reportDesc;
    }
    /**
     * @return Returns the reportId.
     */
    public String getReportId()
    {
        return reportId;
    }
    /**
     * @param reportId The reportId to set.
     */
    public void setReportId(String reportId)
    {
        this.reportId = reportId;
    }
    /**
     * @return Returns the url.
     */
    public String getUrl()
    {
        return url;
    }
    /**
     * @param url The url to set.
     */
    public void setUrl(String url)
    {
        this.url = url;
    }
    /**
     * @return Returns the userParm.
     */
    public String getUserParm()
    {
        return userParm;
    }
    /**
     * @param userParm The userParm to set.
     */
    public void setUserParm(String userParm)
    {
        this.userParm = userParm;
    }
    /**
     * @return Returns the webFocusCatId.
     */
    public String getWebFocusCatId()
    {
        return webFocusCatId;
    }
    /**
     * @param webFocusCatId The webFocusCatId to set.
     */
    public void setWebFocusCatId(String webFocusCatId)
    {
        this.webFocusCatId = webFocusCatId;
    }
    /**
     * @return Returns the webFocusName.
     */
    public String getWebFocusName()
    {
        return webFocusName;
    }
    /**
     * @param webFocusName The webFocusName to set.
     */
    public void setWebFocusName(String webFocusName)
    {
        this.webFocusName = webFocusName;
    }
}
