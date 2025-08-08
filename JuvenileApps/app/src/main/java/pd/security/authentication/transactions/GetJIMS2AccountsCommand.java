//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\security\\authentication\\transactions\\GetJIMS2AccountCommand.java

package pd.security.authentication.transactions;

import java.util.Iterator;

import messaging.agency.GetDepartmentsEvent;
import messaging.authentication.GetJIMS2AccountsEvent;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
import messaging.security.authentication.reply.LoginErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import naming.AgencyControllerServiceNames;
import naming.ResponseLocatorConstants;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.contact.agency.Department;
import pd.exception.ReflectionException;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountType;
import pd.security.JIMS2AccountView;
import pd.security.PDSecurityHelper;
import pd.transferobjects.helper.DepartmentHelper;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class GetJIMS2AccountsCommand implements ICommand
{

    /**
     * @roseuid 4399BF8D02EE
     */
    public GetJIMS2AccountsCommand()
    {

    }

    /**
     * @param event
     * @roseuid 439711A8008C
     */
    public void execute(IEvent event)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetJIMS2AccountsEvent jEvent = (GetJIMS2AccountsEvent) event;

	if (PDSecurityHelper.isUserSA())
	{
	    GetDepartmentsEvent gEvent = (GetDepartmentsEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENTS);
	    gEvent.setAgencyId(PDSecurityHelper.getUserAgencyId());
	   // jEvent.setDepartmentId(this.getDepartments("","",gEvent.getAgencyId()));
	}
	else
	    if (PDSecurityHelper.isUserASA())
	    {
	//	jEvent.setDepartmentId(this.getDepartments()); //87191
	    }
	    else
		if (PDSecurityHelper.isUserLA())
		{
		    GetDepartmentsEvent gEvent = (GetDepartmentsEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENTS);
		    gEvent.setDepartmentId(PDSecurityHelper.getUser().getDepartmentId());
		    //jEvent.setDepartmentId(this.getDepartments(gEvent.getDepartmentId(),"",""));
		}
		else
		{
		    jEvent.setDepartmentId("");
		}

	if (jEvent.getJimsLogonId() != null && !jEvent.getJimsLogonId().equals(""))
	{
	    Iterator accountTypeIter = JIMS2AccountType.findAll("logonId", jEvent.getJimsLogonId().toUpperCase());
	    StringBuffer accountTypeIds = new StringBuffer();
	    while (accountTypeIter.hasNext())
	    {
		JIMS2AccountType accountType = (JIMS2AccountType) accountTypeIter.next();
		if (accountType != null)
		{
		    accountTypeIds.append("");
		    accountTypeIds.append(accountType.getJIMS2AccountId());
		    accountTypeIds.append("");
		    if (accountTypeIter.hasNext())
		    {
			accountTypeIds.append(",");
		    }
		}
	    }
	    if (accountTypeIds.toString().length() > 0)
	    {
		jEvent.setJimsAccountId(accountTypeIds.toString());
	    }
	    else
	    {
		return;
	    }
	}
	
	if (jEvent.getJims2LogonId() != null  && !jEvent.getJims2LogonId().isEmpty())
	{
	    JIMS2Account jims2Account = JIMS2Account.findByLogonId(jEvent.getJims2LogonId()); // for service providers.
	    dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    if (jims2Account != null)
	    {
		JIMS2AccountView jims2AcctView = null;
		Iterator<JIMS2AccountView> jims2AcctViewItr = JIMS2AccountView.findAll("OID", jims2Account.getOID());
		if (jims2AcctViewItr.hasNext())
		{
		    jims2AcctView = jims2AcctViewItr.next();
		    ResponseContextFactory respFac = new ResponseContextFactory();
		    ResponseCreator aCreator = null;
		    try
		    {
			aCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.JIMS2ACCOUNT_RESPONSE_LOCATOR);
		    }
		    catch (Exception e)
		    {
			LoginErrorResponseEvent error = new LoginErrorResponseEvent();
			error.setMessage("Error encountered accessing your JIMS2 Account. Exception is " + e);
			dispatch.postEvent(error);
			return;
		    }
		    JIMS2AccountResponseEvent resp = (JIMS2AccountResponseEvent) aCreator.create(jims2AcctView);
		    dispatch.postEvent(resp);
		}
	    }
	}

	Iterator<JIMS2AccountView> jims2Accounts = JIMS2AccountView.findAll(jEvent);
	if (jims2Accounts != null)
	{
	    ResponseContextFactory respFac = new ResponseContextFactory();
	    ResponseCreator aCreator = null;
	    try
	    {
		aCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.JIMS2ACCOUNT_RESPONSE_LOCATOR);
	    }
	    catch (ReflectionException e)
	    {
		e.printStackTrace();
	    }
	    while (jims2Accounts.hasNext())
	    {
		JIMS2AccountView jAcc = (JIMS2AccountView) jims2Accounts.next();
		if (jAcc != null)
		{
		    JIMS2AccountResponseEvent resp = (JIMS2AccountResponseEvent) aCreator.createThin(jAcc);
		    dispatch.postEvent(resp);
		}
	    }
	}
    }

    /**
     * 87191
     * @return
     */
  /*  private String getDepartments()
    {
	User user = User.find(PDSecurityHelper.getLogonId());
	StringBuffer deptIds = new StringBuffer("");
	if (user != null)
	{
	    Collection constraints = user.getConstraintsByConstrainerType(Department.class);
	    Iterator constraintsIterator = constraints.iterator();
	    while (constraintsIterator.hasNext())
	    {
		Constraint constraint = (Constraint) constraintsIterator.next();
		if (constraint != null && constraint.getConstrainerType().equalsIgnoreCase("DEPARTMENT"))
		{
		    DepartmentConstraintsForUserAdministrationResponseEvent rEvent = new DepartmentConstraintsForUserAdministrationResponseEvent();
		    rEvent.setDepartmentId(constraint.getConstrainerId());
		    Department department = Department.find(constraint.getConstrainerId());
		    if (department != null)
		    {
			deptIds.append("'");
			deptIds.append(department.getDepartmentId());
			deptIds.append("'");
			if (constraintsIterator.hasNext())
			{
			    deptIds.append(",");
			}
		    }
		}
	    }
	}
	return deptIds.toString();
    }
*/
    /**
	//87191
     * @param event
     */
 /*   private String getDepartments(String departmentId,String departmentName, String agencyId)
    {
	Iterator<Department> departmentIter = DepartmentHelper.getDepartments(departmentId, departmentName, agencyId)//Department.findAll(event);
	StringBuffer deptIds = new StringBuffer("");
	while (departmentIter.hasNext())
	{
	    deptIds.append("'");
	    Department dept = (Department) departmentIter.next();
	    deptIds.append(dept.getDepartmentId());
	    deptIds.append("'");
	    if (departmentIter.hasNext())
	    {
		deptIds.append(",");
	    }
	}
	return deptIds.toString();
    }*/

    /**
     * @param event
     * @roseuid 439711A8008E
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 439711A8009B
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 4399BF8D02FE
     */
    public void update(Object updateObject)
    {

    }
}