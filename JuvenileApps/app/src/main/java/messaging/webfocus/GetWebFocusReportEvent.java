/*
 * Created on Feb 20, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.webfocus;

import mojo.km.messaging.RequestEvent;

/**
 * @author RYoung
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetWebFocusReportEvent extends RequestEvent
{
    private String webFocusCatId;
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
    private String reportId;
	private String webFocusName;

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
