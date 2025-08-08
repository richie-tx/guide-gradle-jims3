//Source file: C:\\views\\juvenilecasework\\app\\src\\ui\\juvenilecase\\suspiciousMembers\\action\\DisplaySuspiciousMemberSearchAction.java

package ui.juvenilecase.suspiciousMembers.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.to.NameBean;
import messaging.contact.to.SocialSecurityBean;
import messaging.juvenilecase.reply.AssociatedJuvenilesResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.suspiciousMembers.form.SuspiciousMemberForm;


/*
 * 
 * @author cShimek
 * 
 */

public class DisplaySuspiciousMembersUpdateSummaryAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplaySuspiciousMembersUpdateSummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");

	}
	private static final String U = "U";
	private static final String UNKNOWN = "UNKNOWN";
	private static final String TRUE = "true";
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
		SocialSecurityBean ssn = new SocialSecurityBean(smForm.getMemberSSN1(),  smForm.getMemberSSN2(), smForm.getMemberSSN3());
        smForm.setPopup(UIConstants.EMPTY_STRING);		
		/*List similarMembers = UIJuvenileFamilyHelper.searchForSimilarMembers(smForm.getSelectedValue(),ssn.toString());
		if (similarMembers.size() > 0){
            sendToErrorPage(aRequest, "error.family.samePerson");
            smForm.setPopup("true");
            Collections.sort(similarMembers);
            smForm.setSimilarMembers(similarMembers);
            return aMapping.findForward(UIConstants.FAILURE); 
        }*/
		
		smForm.setMemberSSN(ssn.getFormattedSsn());
        
		NameBean memberName = new NameBean(smForm.getMemberFirstName(), smForm.getMemberMiddleName(), smForm.getMemberLastName());
		smForm.setMemberName(memberName.getFormattedName());
		
		smForm.setSexLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SEX, smForm.getSexId() ) );
		smForm.setUsCitizenLit(UIConstants.EMPTY_STRING);
		if (smForm.getUsCitizenId() != null)
		{	
			if (UIConstants.YES.equalsIgnoreCase(smForm.getUsCitizenId() ) )
			{
				smForm.setUsCitizenLit(UIConstants.YES_FULL_TEXT);
			}
			if (UIConstants.NO.equalsIgnoreCase(smForm.getUsCitizenId() ) )
			{
				smForm.setUsCitizenLit(UIConstants.NO_FULL_TEXT);
			}
			if (U.equalsIgnoreCase(smForm.getUsCitizenId() ) )
			{
				smForm.setUsCitizenLit(UNKNOWN);
			}
		}
		smForm.setNationalityLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PLACE_OF_BIRTH, smForm.getNationalityId() ) );
		smForm.setEthnicityLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_ETHNICITY, smForm.getEthnicityId() ) );
		smForm.setPrimaryLanguageLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.LANGUAGE, smForm.getPrimaryLanguageId() ) );
		smForm.setSecondaryLanguageLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.LANGUAGE, smForm.getSecondaryLanguageId() ) );

		smForm.setDeceasedLit(UIConstants.NO_FULL_TEXT);
		if (TRUE.equals(smForm.getDeceased() ) )
		{
			smForm.setDeceasedLit(UIConstants.YES_FULL_TEXT);
		}
		smForm.setCauseOfDeathLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CAUSE_OF_DEATH, smForm.getCauseOfDeathId() ) );
		smForm.setIncarcetatedLit(UIConstants.NO_FULL_TEXT);
		if (TRUE.equals(smForm.getIncarcerated() ) )
		{
			smForm.setIncarcetatedLit(UIConstants.YES_FULL_TEXT);
		}
		smForm.setDlStateLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STATE_ABBR, smForm.getDlStateId() ) );
		smForm.setDlClassIdLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.DRIVERS_LICENSE_CLASS, smForm.getDlClassId() ) );
		smForm.setStateIssuedCardStateLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STATE_ABBR,smForm.getStateIssuedCardStateId() ));
		List wrkList = new ArrayList();
		if (smForm.getAssociatedJuvenilesList() != null)
		{	
			for (int z=0; z<smForm.getAssociatedJuvenilesList().size(); z++)
			{
				AssociatedJuvenilesResponseEvent ajre = (AssociatedJuvenilesResponseEvent) smForm.getAssociatedJuvenilesList().get(z);
				wrkList.add(ajre);
				if (ajre.getRelationTypeId() != null && !ajre.getRelationTypeId().equals(UIConstants.EMPTY_STRING)){
					if (ajre.getRelationType() == null || ajre.getRelationType().equals(UIConstants.EMPTY_STRING)){
						ajre.setRelationType(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RELATIONSHIP_JUVENILE, ajre.getRelationTypeId()) );
					}
				}
			}
		}
		smForm.setAssociatedJuvenilesListOrig(wrkList);  // this is for display only
		wrkList = null;
// marital status removed from input jsp	01-30-2012	
/**		smForm.setMaritalStatusLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.MARITAL_STATUS, smForm.getMaritalStatusId() ) );
		StringBuffer aLit = new StringBuffer();
		if (smForm.getRelationshipWithList() != null)
		{	
			for (int x=0; x<smForm.getRelationshipWithList().size(); x++)
			{
				JuvenileFamilyMemberViewResponseEvent jfmEvent = (JuvenileFamilyMemberViewResponseEvent) smForm.getRelationshipWithList().get(x);
				if (smForm.getRelationshipWithId().equals(jfmEvent.getMemberNum() ) )
				{	
					aLit.append(smForm.getRelationshipWithId()).append(" - ").append(jfmEvent.getFullName() );
					break;
				}	
			}
		}
		smForm.setRelationshipWithLit(aLit.toString()); */
		smForm.setSecondaryAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.SUCCESS); 
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
		smForm.clear();
		return aMapping.findForward(UIConstants.CANCEL);
	}	
}