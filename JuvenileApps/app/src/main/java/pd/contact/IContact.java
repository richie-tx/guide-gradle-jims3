/*
 * Created on Jan 10, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.contact;

/**
 * @author dnikolis
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface IContact
{
	public String getCellPhone();
	public void setCellPhone(String aCellPhone);
	public String getContactId();
	public void setContactId(String aContactId);
	public String getEmail();
	public void setEmail(String aEmail);
	public String getFaxLocation();
	public void setFaxLocation(String aFaxLocation);
	public String getFaxNum();
	public void setFaxNum(String aFaxNum);
	public String getFirstName();
	public void setFirstName(String aFirstName);
	public String getHomePhoneNum();
	public void setHomePhoneNum(String aHomePhoneNum);
	public String getLastName();
	public void setLastName(String aLastName);
	public String getMiddleName();
	public void setMiddleName(String aMiddleName);
	public String getPager();
	public void setPager(String aPager);
	public String getPhoneExt();
	public void setPhoneExt(String aPhoneExt);
	public String getPhoneNum();
	public void setPhoneNum(String aPhoneNum);
	public String getTitle();
	public void setTitle(String aTitle);
	public String getWorkPhoneNum();
	public void setWorkPhoneNum(String aWorkPhoneNum);
}
