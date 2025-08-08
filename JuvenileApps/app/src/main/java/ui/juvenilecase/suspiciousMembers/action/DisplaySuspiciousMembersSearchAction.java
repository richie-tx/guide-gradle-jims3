//Source file: C:\\views\\juvenilecasework\\app\\src\\ui\\juvenilecase\\suspiciousMembers\\action\\DisplaySuspiciousMemberSearchAction.java

package ui.juvenilecase.suspiciousMembers.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.suspiciousMembers.form.SuspiciousMemberForm;


/*
 * 
 * @author cShimek
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class DisplaySuspiciousMembersSearchAction extends Action {

	/**
	 *  
	 */
	public DisplaySuspiciousMembersSearchAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
		smForm.clear();
		smForm.setCauseOfDeathList(CodeHelper.getCodes(PDCodeTableConstants.CAUSE_OF_DEATH, true));
		smForm.setDlClassList(CodeHelper.getCodes(PDCodeTableConstants.DRIVERS_LICENSE_CLASS,true) );
		smForm.setEthnicityList(CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_ETHNICITY,true));
		smForm.setLanguageList(CodeHelper.getCodes(PDCodeTableConstants.LANGUAGE,true));
		smForm.setMaritalStatusList(CodeHelper.getCodes(PDCodeTableConstants.MARITAL_STATUS,true));
		smForm.setNationalityList(CodeHelper.getCodes(PDCodeTableConstants.PLACE_OF_BIRTH,true));
		smForm.setRelationshipToJuvenileList(CodeHelper.getCodes(PDCodeTableConstants.RELATIONSHIP_JUVENILE,true));
		smForm.setRelationshipWithList(new ArrayList());
		smForm.setSexList(CodeHelper.getCodes(PDCodeTableConstants.SEX,true) );
		smForm.setStateList(CodeHelper.getCodes(PDCodeTableConstants.STATE_ABBR,true));
		smForm.setUsCitizenList(CodeHelper.getCodes(PDCodeTableConstants.ISUSCITIZEN,true));
		smForm.setSearchTypeId("FAM");
		return aMapping.findForward(UIConstants.SUCCESS);
	}

}