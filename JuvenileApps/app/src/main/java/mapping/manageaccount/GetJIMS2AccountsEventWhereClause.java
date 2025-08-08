/*
 * Created on Feb 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mapping.manageaccount;

import messaging.authentication.GetJIMS2AccountsEvent;
import mojo.km.messaging.IEvent;


public class GetJIMS2AccountsEventWhereClause
{

    /**
     * @param event
     * @return String
     */
    public String getJIMS2AccountsEventWhereClause(IEvent event)
    {
	GetJIMS2AccountsEvent jEvent = (GetJIMS2AccountsEvent) event;
	String jims2LogonId = jEvent.getJims2LogonId();
	String firstName = jEvent.getFirstName();
	String lastName = jEvent.getLastName();
	String departmentId = jEvent.getDepartmentId();
	String jimsAccountId = jEvent.getJimsAccountId();
	if (jimsAccountId != null && !jimsAccountId.equals(""))
	{
	    return this.jimsLogonIdBuildSql(jimsAccountId, departmentId);
	}
	else
	{
	    return this.buildSql(jims2LogonId, jimsAccountId, firstName, lastName, departmentId);
	}
    }

    /**
     * @param jimsAccountId
     * @param departmentId
     * @return String
     */
    private String jimsLogonIdBuildSql(String jimsAccountId, String departmentId)
    {
	StringBuffer sql = new StringBuffer();
	if (jimsAccountId != null && !jimsAccountId.trim().equals("") && !jimsAccountId.trim().equals("*"))
	{
	    sql.append(" JIMS2ACCOUNT_ID IN (");
	    sql.append(jimsAccountId.toUpperCase());
	    sql.append(")");
	}

	if (departmentId != null && !departmentId.trim().equals(""))
	{
	    sql.append(" AND DEPT_ID IN (");
	    sql.append(departmentId.toUpperCase());
	    sql.append(")");
	}
	return sql.toString();
    }

    /**
     * @param jims2LogonId
     * @param jimsAccountId
     * @param firstName
     * @param lastName
     * @param departmentId
     * @return String
     */
    private String buildSql(String jims2LogonId, String jimsAccountId, String firstName, String lastName, String departmentId)
    {
	StringBuffer sql = new StringBuffer();
	if (jims2LogonId != null && !jims2LogonId.trim().equals(""))
	{
	    sql.append(" UPPER(JIMSLOGONID) LIKE '");
	    sql.append(jims2LogonId);
	    sql.append("%'");
	}
	else
	{
	    sql.append(" JIMSLOGONID LIKE '");
	    sql.append("%");
	    sql.append("'");
	}
	if (firstName != null && !firstName.trim().equals("") && !firstName.trim().equals("*"))
	{
	    sql.append(" AND UPPER(FIRSTNAME) LIKE '");
	    sql.append(firstName.toUpperCase());
	    sql.append("%'");
	}
	else
	    if (firstName != null && firstName.trim().equals("*"))
	    {
		sql.append(" AND FIRSTNAME LIKE '%'");
	    }
	if (lastName != null && !lastName.trim().equals("") && !lastName.trim().equals("*"))
	{
	    sql.append(" AND UPPER(LASTNAME) LIKE '");
	    sql.append(lastName.toUpperCase());
	    sql.append("%'");
	}
	else
	    if (lastName != null && lastName.trim().equals("*"))
	    {
		sql.append(" AND LASTNAME LIKE '%'");
	    }
	if (departmentId != null && !departmentId.trim().equals(""))
	{
	    sql.append(" AND DEPT_ID IN (");
	    sql.append(departmentId.toUpperCase());
	    sql.append(")");
	}
	return sql.toString();
    }
}
