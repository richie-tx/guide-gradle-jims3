package pd.webfocus;


import java.util.Iterator;

import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.context.multidatasource.Home;

/**
 * 
 * @author RYoung
 TODO To change the template for this generated type comment go to
 Window - Preferences - Java - Code Style - Code Templates
 */
public class WebFocusReportCatalog extends PersistentObject
{
	private String webFocusCatId;
	private String reportId;
	private String reportDesc;
	private String webFocusName;
	private String url;
	private String userParm;
	private String passwordParm;

	/**
	 * 
	 * @return 
	 */
	static public WebFocusReportCatalog find(String OID)
	{
		IHome home = new Home();
		Object obj = home.find(OID, WebFocusReportCatalog.class);
		return (WebFocusReportCatalog) obj;
	}
	
	/**
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		return (Iterator) home.findAll(attrName, attrValue.toUpperCase(), WebFocusReportCatalog.class);
	}
	
	

	/**
	 * 
	 * @return Returns the passwordParm.
	 */
	public String getPasswordParm()
	{
		fetch();
		return passwordParm;
	}

	/**
	 * 
	 * @param passwordParm The passwordParm to set.
	 */
	public void setPasswordParm(String passwordParm)
	{
		if (this.passwordParm == null || !this.passwordParm.equals(passwordParm))
		{
			markModified();
		}
		this.passwordParm = passwordParm;
	}

	/**
	 * 
	 * @return Returns the reportDesc.
	 */
	public String getReportDesc()
	{
		fetch();
		return reportDesc;
	}

	/**
	 * 
	 * @param reportDesc The reportDesc to set.
	 */
	public void setReportDesc(String reportDesc)
	{
		if (this.reportDesc == null || !this.reportDesc.equals(reportDesc))
		{
			markModified();
		}
		this.reportDesc = reportDesc;
	}

	/**
	 * 
	 * @return Returns the reportId.
	 */
	public String getReportId()
	{
		fetch();
		return reportId;
	}

	/**
	 * 
	 * @param reportId The reportId to set.
	 */
	public void setReportId(String reportId)
	{
		if (this.reportId == null || !this.reportId.equals(reportId))
		{
			markModified();
		}
		this.reportId = reportId;
	}

	/**
	 * 
	 * @return Returns the url.
	 */
	public String getUrl()
	{
		fetch();
		return url;
	}

	/**
	 * 
	 * @param url The url to set.
	 */
	public void setUrl(String url)
	{
		if (this.url == null || !this.url.equals(url))
		{
			markModified();
		}
		this.url = url;
	}

	/**
	 * 
	 * @return Returns the userParm.
	 */
	public String getUserParm()
	{
		fetch();
		return userParm;
	}

	/**
	 * 
	 * @param userParm The userParm to set.
	 */
	public void setUserParm(String userParm)
	{
		if (this.userParm == null || !this.userParm.equals(userParm))
		{
			markModified();
		}
		this.userParm = userParm;
	}

	/**
	 * 
	 * @return Returns the webFocusCatId.
	 */
	public String getWebFocusCatId()
	{
		fetch();
		return webFocusCatId;
	}

	/**
	 * 
	 * @param webFocusCatId The webFocusCatId to set.
	 */
	public void setWebFocusCatId(String webFocusCatId)
	{
		if (this.webFocusCatId == null || !this.webFocusCatId.equals(webFocusCatId))
		{
			markModified();
		}
		this.webFocusCatId = webFocusCatId;
	}

	/**
	 * 
	 * @return Returns the webFocusName.
	 */
	public String getWebFocusName()
	{
		fetch();
		return webFocusName;
	}

	/**
	 * 
	 * @param webFocusName The webFocusName to set.
	 */
	public void setWebFocusName(String webFocusName)
	{
		if (this.webFocusName == null || !this.webFocusName.equals(webFocusName))
		{
			markModified();
		}
		this.webFocusName = webFocusName;
	}
}
