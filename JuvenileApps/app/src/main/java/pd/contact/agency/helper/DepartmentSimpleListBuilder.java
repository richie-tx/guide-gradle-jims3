/*
 * Created on Oct 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.contact.agency.helper;

import java.util.Iterator;

import naming.PDContactConstants;

import messaging.contact.agency.reply.DepartmentResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import pd.contact.agency.Department;
import pd.pattern.IBuilder;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DepartmentSimpleListBuilder implements IBuilder
{
	private Iterator departments;
	private CompositeResponse response;

	public DepartmentSimpleListBuilder(Iterator departments)
	{
		this.response = new CompositeResponse();
		this.departments = departments;
	}

	public void build()
	{
		if (departments != null)
		{
			while (departments.hasNext())
			{
				Department department = (Department) departments.next();
				if (department.getDepartmentId() != null && department.getDepartmentName() != null)
				{
					DepartmentResponseEvent departmentResponse = new DepartmentResponseEvent();
					departmentResponse.setTopic(PDContactConstants.DEPARTMENT_LISTITEM_EVENT_TOPIC);
					departmentResponse.setDepartmentId(department.getDepartmentId());
					departmentResponse.setDepartmentName(department.getDepartmentName());
					response.addResponse(departmentResponse);
				}
			}
		}
	}

	public Object getResult()
	{
		return this.response;
	}

}
