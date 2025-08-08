//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\DisplayJuvServiceProviderSearchAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;

public class DisplayJuvServiceProviderSearchAction extends LookupDispatchAction
{
	private final boolean SORT_IT = true ;
	
	/**
	 * @roseuid 450AF15B02A0
	 */
	public DisplayJuvServiceProviderSearchAction()
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 450AA179017B
	 */
	public ActionForward execute( ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse )
	{
		ServiceProviderForm providerForm = (ServiceProviderForm)aForm;
		providerForm.clear();
		
		String departmentId = SecurityUIHelper.getUserDepartmentId();
		if( !departmentId.equalsIgnoreCase( "JUV" ) )
		{
			this.saveErrors( aRequest, "error.searchServiceProvider.invalidUser" );
			aRequest.setAttribute( "error", "true" );
			return aMapping.findForward( UIConstants.FAILURE );
		}
		
		providerForm.setSearchTypeId( "SP" );
		//bug Fix:13165.Showing only the codes with are active by status
		Collection coll = CodeHelper.getActiveCodesByStatus( PDCodeTableConstants.JUV_PROGRAM_GROUP, SORT_IT );
		providerForm.setTargetInterventionList( coll );
		
		coll = CodeHelper.getActiveCodesByStatus( PDCodeTableConstants.STATE_PROGRAM_CODE, SORT_IT );
		providerForm.setStateProgramCodeList( coll );
		coll = CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.TYPE_PROGRAM_CODE, SORT_IT);
		providerForm.setTypeProgramCodeList(coll);
		
		coll = CodeHelper.getCodes( "JUV_SERV_PROVIDER_STATUS", SORT_IT );
		providerForm.setStatusTypes( coll );
		
		coll = CodeHelper.getCodes( "JUV_SERV_PROVIDER_SEARCH", SORT_IT );
		providerForm.setSpSearchTypes( coll );
		
		coll = CodeHelper.getCodes( "INHOUSE", SORT_IT );
		providerForm.setSpTypes( coll );
			
		coll = CodeHelper.getCodes( PDCodeTableConstants.PROGRAM_SCHEDULE_TYPE, true ); //added for user-story 11099
		providerForm.setProgramScheduleTypeList(coll); //added for user-story 11099
		
		
		//added for US #11376
		coll = CodeHelper.getActiveCodesByStatus( "JUVENILE_SOURCE_FUND", SORT_IT );
		providerForm.setFundSourceList(coll );
		
		coll = CodeHelper.getActiveCodesByStatus( "JUVENILE_PROGRAM_CATEGORY", SORT_IT );
		providerForm.setProgramCategoryList(coll );
		
		coll = CodeHelper.getActiveCodesByStatus( "JUVENILE_PROGRAM_LOCATION", SORT_IT );
		providerForm.setProgramLocationList(coll );
		
		
		
		UIServiceProviderHelper.setServiceCodes( providerForm );
		UIServiceProviderHelper.getAddressCodes( providerForm );

		return aMapping.findForward( UIConstants.SUCCESS );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		return keyMap;
	}

	private void saveErrors( HttpServletRequest aRequest, String errorKey )
	{
		ActionErrors errors = new ActionErrors();
		errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( errorKey, SecurityUIHelper.getLogonId() ) );
		saveErrors( aRequest, errors );
	}
}
