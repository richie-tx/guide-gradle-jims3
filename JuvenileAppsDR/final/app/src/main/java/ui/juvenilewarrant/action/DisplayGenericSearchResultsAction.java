//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilewarrant\\action\\DisplayGenericSearchResultsAction.java
//
//HRodriguez - 02/04/2005 - Add Warrant Recall & Inactivate logic 

package ui.juvenilewarrant.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.juvenilewarrant.form.JuvenileWarrantForm;

/**
 * @author HRodriguez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayGenericSearchResultsAction extends Action
{

	/**
	 * @roseuid 416D2AFE012F
	 */
	public DisplayGenericSearchResultsAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 416D2A0C006A
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{	
		JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

		// default value to failure in case no warrantType present
		String success = UIConstants.FAILURE;

		/* 14 july 2006 - mjt - no comments as to 
			 why the next line is commented out:
		*/
		// jwForm.clear();

		/* 14 july 2006 - mjt - refactored the rest of the 
		   lines of code so as to short-circuit if logic 
		*/

		// Refresh button pressed		
		if (jwForm.getRefreshButton().equalsIgnoreCase("Y"))
		{
			String wtId = jwForm.getWarrantTypeId();
			jwForm.clear();
			jwForm.setWarrantTypeId(wtId);
			success = UIConstants.REFRESH_SUCCESS;
		}		
		else
		{
	  		// getting warrantType out of request and setting it in the form
	  		if (jwForm.getWarrantTypeUI() == null)
	  		{
	  			String warrantTypeUI = aRequest.getParameter(UIConstants.WARRANT_TYPE_UI);
	  			jwForm.setWarrantTypeUI(warrantTypeUI);
	  		}// the following dependent on warrantTypeUI set correct forward for struts-config.xml
			else if (jwForm.getWarrantTypeUI().equals(UIConstants.ACTARR))
	  		{
	  			success = UIConstants.ACTARR_SUCCESS; // Activate Arrest (ARR) Warrant
	  		}
			else if (jwForm.getWarrantTypeUI().equals(UIConstants.ACTDTA))
	  		{
	  			success = UIConstants.ACTDTA_SUCCESS; // Activate DTA Warrant
	  		}
	  		else if (jwForm.getWarrantTypeUI().equals(UIConstants.ACTVOP))
	  		{
	  			success = UIConstants.ACTVOP_SUCCESS; // Activate VOP Warrant
	  		}
	  		else if (jwForm.getWarrantTypeUI().equals(UIConstants.INACTIVATE))
	  		{
	  			success = UIConstants.INACTIVATE_SUCCESS; // Inactivate Warrant
	  		}
	  		else if (jwForm.getWarrantTypeUI().equals(UIConstants.RECALL))
	  		{
	  			success = UIConstants.RECALL_SUCCESS; // Recall Warrant
	  		}
			else if (jwForm.getWarrantTypeUI().equals(UIConstants.REQACKDTA))
	  		{
	  			success = UIConstants.REQACKDTA_SUCCESS; // Request Acknowledgement of DTA Warrant
	  		}
			else if (jwForm.getWarrantTypeUI().equals(UIConstants.REQSIGOIC))
	  		{			
	  			success = UIConstants.REQSIGOIC_SUCCESS; // Request Signature of OIC Warrant
	  		}
			else if (jwForm.getWarrantTypeUI().equals(UIConstants.UPDATE_OIC))
	  		{
				jwForm.setSelectedCharge("");
	  			success = UIConstants.UPDATE_OIC_SUCCESS; // Update OIC Warrant
	  		}
			else if (jwForm.getWarrantTypeUI().equals(UIConstants.RELEASE_DECISION))
	  		{
	  			success = UIConstants.RELEASE_DECISION_SUCCESS; // Release Decision
	  		}
			else if (jwForm.getWarrantTypeUI().equals(UIConstants.RELEASE_TOPERSON))
	  		{
	  			success = UIConstants.RELEASE_TOPERSON_SUCCESS; // Release To Person
	  		}
			else if (jwForm.getWarrantTypeUI().equals(UIConstants.RELEASE_TOJP))
	  		{
				jwForm.setTransferOfficerLogonId("");
				jwForm.setTransferOfficerDepartmentId("");
				jwForm.setTransferOfficerDepartmentName("");
				jwForm.setTransferCustodyDate(new Date());
				jwForm.setTransferCustodyDateString("");
				jwForm.setTransferCustodyTimeString("");
				jwForm.setTransferLocationId("");
				jwForm.setSearch("");
	  			success = UIConstants.RELEASE_TOJP_SUCCESS; // Release to JP
	  		}
			else if (jwForm.getWarrantTypeUI().equals(UIConstants.RETURN_SIGNATURE_STATUS))
	  		{
	  			success = UIConstants.RETURN_SIGNATURE_STATUS_SUCCESS; // Return of Service Signature Status
	  		}
			else if (jwForm.getWarrantTypeUI().equals(UIConstants.PROCESS_RETURN))
	  		{
	  			success = UIConstants.PROCESS_RETURN_SUCCESS; // Process Return Of Service
	  		}
			else if (jwForm.getWarrantTypeUI().equals(UIConstants.WARRANT_SERVICE))
	 		{
				jwForm.setUserId("");
				jwForm.setOfficerAgencyId("");
				jwForm.setOfficerAgencyName("");
				jwForm.setSearch("");
				success = UIConstants.WARRANT_SERVICE_SUCCESS; // Manage Warrant Service
	  		}
			else if (jwForm.getWarrantTypeUI().equals(UIConstants.UPDATE_VOP))
	  		{
				jwForm.setSelectedCharge("");
				success = UIConstants.UPDATE_VOP_SUCCESS; // Update VOP Warrant
	  		}
		}// else not refresh button

		return aMapping.findForward(success);

	}// execute()

}// class

