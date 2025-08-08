/*
 * Created on Jun 28, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.action;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.AbusePerpetrator;
import ui.juvenilecase.form.JuvenileJobForm;

/**
 * @author sprakash
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayJuvenileJobCreateSummaryAction extends Action
{
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.SUCCESS;

		JuvenileJobForm juvjobForm = (JuvenileJobForm) aForm;
		if(juvjobForm.getSupervisorFamilyNum()!=null && !juvjobForm.getSupervisorFamilyNum().equals("")){
			if(juvjobForm.getActiveFamMembers()!=null && !juvjobForm.getActiveFamMembers().isEmpty()){
				Iterator iter=juvjobForm.getActiveFamMembers().iterator();
				while(iter.hasNext()){
					AbusePerpetrator perp=(AbusePerpetrator)iter.next();
					if(juvjobForm.getSupervisorFamilyNum().equals(perp.getMemberId())){
						juvjobForm.setSupervisorFirstName(perp.getName().getFirstName());
						juvjobForm.setSupervisorMiddleName(perp.getName().getMiddleName());
						juvjobForm.setSupervisorLastName(perp.getName().getLastName());
					}
				}
			}
		}
		
		juvjobForm.setAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

}
