/*
 * Created on May 23, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.security.form.SAUsersForm;

/**
 * @author sprakash
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplaySAUserDeptUpdateAction extends Action
{
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		SAUsersForm saForm = (SAUsersForm) aForm;

		// retrieve depts collection and put it in a map
		Map deptMap = putCollectionIntoMap(saForm.getAllDepartments());

		Collection selectedDepts = new ArrayList();
		String[] selDepts = saForm.getSelectedDepartments();
		int i = 0;
		if (selDepts != null)
		{
			while (i < selDepts.length)
			{
				String deptId = selDepts[i++];
				DepartmentResponseEvent deptEvent = (DepartmentResponseEvent) deptMap.get(deptId);
				selectedDepts.add(deptEvent);
			}
			saForm.setSelDepartments(selectedDepts);
		}

		String forward = UIConstants.SUCCESS;
		return aMapping.findForward(forward);

	}

	private Map putCollectionIntoMap(Collection departments)
	{
		Map deptMap = new HashMap();
		if (departments == null)
		{
			return deptMap;
		}
		Iterator it = departments.iterator();
		while (it.hasNext())
		{
			DepartmentResponseEvent deptEvent = (DepartmentResponseEvent) it.next();
			deptMap.put(deptEvent.getDepartmentId(), deptEvent);
		}
		return deptMap;
	}
}