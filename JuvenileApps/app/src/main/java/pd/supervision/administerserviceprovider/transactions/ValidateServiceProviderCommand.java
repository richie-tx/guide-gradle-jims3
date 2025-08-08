//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\ValidateServiceProviderCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.administerserviceprovider.ValidateServiceProviderEvent;
import messaging.administerserviceprovider.reply.ServiceProviderErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDAdministerServiceProviderConstants;
import pd.contact.agency.Department;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.transferobjects.helper.DepartmentHelper;

public class ValidateServiceProviderCommand implements ICommand
{

    /**
     * @roseuid 4473538E0385
     */
    public ValidateServiceProviderCommand()
    {

    }

    /**
     * @param event
     * @roseuid 446A2E440110
     */
    public void execute(IEvent event)
    {
	ValidateServiceProviderEvent validateEvent = (ValidateServiceProviderEvent) event;

	// check agencyId
	if (validateEvent.getAgencyId() != null && !(validateEvent.getAgencyId().equals("")))
	{
	    if (this.checkServiceProviderName(validateEvent))
	    {
		return;
	    }
	}
    }

    /**
     * @param validateEvent
     * @return boolean true/false
     */
    private boolean checkServiceProviderName(ValidateServiceProviderEvent validateEvent)
    {
	List<Department> departments = DepartmentHelper.getDepartmentsByAgencyId(validateEvent.getAgencyId());
	if (departments != null)
	{
	    Iterator<Department> iter = departments.iterator();//Department.findAll(PDAdministerServiceProviderConstants.AGENCY_ID, validateEvent.getAgencyId());87191
	    ArrayList<String> listDepartmentId = new ArrayList<String>();
	    while (iter.hasNext())
	    {
		Department department = (Department) iter.next();
		listDepartmentId.add(department.getDepartmentId());
	    }

	    ArrayList<String> listJuvenileServiceProviderNames = new ArrayList<String>();
	    for (int i = 0; i < listDepartmentId.size(); i++)
	    {
		Iterator<JuvenileServiceProvider> iterJuvenileServiceProviders = JuvenileServiceProvider.findAll(PDAdministerServiceProviderConstants.DEPARTMENT_ID, listDepartmentId.get(i).toString());

		while (iterJuvenileServiceProviders.hasNext())
		{
		    JuvenileServiceProvider juvenileServiceProvider = (JuvenileServiceProvider) iterJuvenileServiceProviders.next();
		    if (!"I".equals(juvenileServiceProvider.getStatusId()))
		    {
			listJuvenileServiceProviderNames.add(juvenileServiceProvider.getServiceProviderName());
		    }
		}
	    }
	    for (int j = 0; j < listJuvenileServiceProviderNames.size(); j++)
	    {
		if (listJuvenileServiceProviderNames.get(j).toString().equalsIgnoreCase(validateEvent.getServiceProviderName()))
		{
		    this.sendServiceProviderErrorResponseEvent("error.duplicate.serviceProviderName");
		    return true;
		}
	    }
	    return false;
	}
	return false;
    }

    /**
     * @param errorKey
     */
    private void sendServiceProviderErrorResponseEvent(String errorKey)
    {
	//		DuplicateRecordErrorResponseEvent errorEvent = new DuplicateRecordErrorResponseEvent();
	ServiceProviderErrorResponseEvent errorEvent = new ServiceProviderErrorResponseEvent();
	errorEvent.setMessage(errorKey);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	dispatch.postEvent(errorEvent);
    }

    /**
     * @param event
     * @roseuid 446A2E440112
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 446A2E44011F
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param anObject
     * @roseuid 446A2E440121
     */
    public void update(Object anObject)
    {

    }
}