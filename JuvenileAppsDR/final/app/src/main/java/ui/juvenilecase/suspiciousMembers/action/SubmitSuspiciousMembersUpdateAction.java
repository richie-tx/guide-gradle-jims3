//Source file: C:\\views\\juvenilecasework\\app\\src\\ui\\juvenilecase\\suspiciousMembers\\action\\DisplaySuspiciousMemberSearchAction.java

package ui.juvenilecase.suspiciousMembers.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.to.SocialSecurityBean;
import messaging.family.SaveSuspiciousFamilyMemberEvent;
import messaging.juvenilecase.reply.AssociatedJuvenilesResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.suspiciousMembers.form.SuspiciousMemberForm;


/*
 * 
 * @author cShimek
 * 
 */

public class SubmitSuspiciousMembersUpdateAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public SubmitSuspiciousMembersUpdateAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.backToResolutionSelection", "backToSelect");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.backToSearch", "backToSearch");
	}
	
	private static final String TRUE = "true";
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;

		SaveSuspiciousFamilyMemberEvent sfmEvent = (SaveSuspiciousFamilyMemberEvent) 
      		EventFactory.getInstance(JuvenileFamilyControllerServiceNames.SAVESUSPICIOUSFAMILYMEMBER);
    
      	sfmEvent.setFirstName( smForm.getMemberFirstName() );
		sfmEvent.setLastName( smForm.getMemberLastName() );
		sfmEvent.setMiddleName( smForm.getMemberMiddleName() );
		
		SocialSecurityBean ssnBean = new SocialSecurityBean(smForm.getMemberSSN1(), smForm.getMemberSSN2(), smForm.getMemberSSN3());
		sfmEvent.setSsn( ssnBean.toString() );
		sfmEvent.setDateOfBirth(DateUtil.stringToDate(smForm.getMemberDOB(),UIConstants.DATE_FMT_1) ); 
		sfmEvent.setSexId( smForm.getSexId() );
		sfmEvent.setSidNum( smForm.getSidNum() );
		sfmEvent.setAlienRegistrationNum( smForm.getAlienRegNumber() );
		sfmEvent.setIsUSCitizenId( smForm.getUsCitizenId() );
		sfmEvent.setNationalityId( smForm.getNationalityId() );
		sfmEvent.setEthnicityId( smForm.getEthnicityId() );
		sfmEvent.setPrimarylanguageId( smForm.getPrimaryLanguageId() );
		sfmEvent.setSecondaryLanguageId( smForm.getSecondaryLanguageId() );
		sfmEvent.setDeceasedInd(false);
		
		if (TRUE.equals(smForm.getDeceased() ) )
		{
			sfmEvent.setDeceasedInd(true);
			sfmEvent.setCauseOfDeathId( smForm.getCauseOfDeathId() );
		} else {
			smForm.setCauseOfDeathId( UIConstants.EMPTY_STRING );
		}
		sfmEvent.setIncarcerated( false );
		if (TRUE.equals(smForm.getIncarcerated() ) )
		{
			sfmEvent.setIncarcerated(true);
		}
		sfmEvent.setComments( smForm.getComments() );
// begin driver license info	
		sfmEvent.setDriverLicenceNumber( smForm.getDlNumber() );
		sfmEvent.setDriverLicenceStateId( smForm.getDlStateId() );
		sfmEvent.setDriverLicenceExpiryDate(DateUtil.stringToDate(smForm.getDlExpDateStr(), UIConstants.DATE_FMT_1 ));
		sfmEvent.setDriverLicenceClassId( smForm.getDlClassId() );
	
		sfmEvent.setIdCardNum( smForm.getStateIssuedCardNumber() );
		sfmEvent.setIdCardStateId( smForm.getStateIssuedCardStateId() );
		sfmEvent.setMemberId( smForm.getMemberNumber() );
// end driver license info
// begin juvenile relationship
		 if( smForm.getAssociatedJuvenilesList()!= null && !smForm.getAssociatedJuvenilesList().isEmpty() ) 
		 {
			 if( smForm.getAssociatedJuvenilesListOrig()!= null && !smForm.getAssociatedJuvenilesListOrig().isEmpty() )
			 {
				 AssociatedJuvenilesResponseEvent ajre = null;
				 AssociatedJuvenilesResponseEvent ajreOrig = null;
				 
				 for (int a=0; a<smForm.getAssociatedJuvenilesList().size(); a++)
				 {
					 ajre = (AssociatedJuvenilesResponseEvent) smForm.getAssociatedJuvenilesList().get(a);
					 ajreOrig = (AssociatedJuvenilesResponseEvent) smForm.getAssociatedJuvenilesListOrig().get(a);
					 if (!ajre.getRelationTypeId().equals(ajreOrig.getRelationTypeId()))
					 {
						 sfmEvent.addRequest(ajre);
					 }
				 }
				 ajre = null;
				 ajreOrig = null;
			 }
		 }
// end juvenile relationship
// begin marital status info		

/**		 if( smForm.getMaritalStatusId() != null && !UIConstants.EMPTY_STRING.equals(smForm.getMaritalStatusId() ) ) 
		 {	
    	  boolean updateMaritalStatus = false;
    	  if (smForm.getMaritalStatusDetailsList() != null && !smForm.getMaritalStatusDetailsList().isEmpty())
    	  	{
// msEvent contains the original marital status information
	          FamilyMemberMartialStatusResponseEvent msEvent = (FamilyMemberMartialStatusResponseEvent) smForm.getMaritalStatusDetailsList().get(0);          
	          if( !msEvent.getMartialStatusId().equalsIgnoreCase(smForm.getMaritalStatusId()) || 
	       		  (msEvent.getRelatedFamMemId() != null &&!msEvent.getRelatedFamMemId().trim().equalsIgnoreCase(smForm.getRelationshipWithId()) ) ||
	       		  (msEvent.getNumberOfChildren() != null && !msEvent.getNumberOfChildren().trim().equalsIgnoreCase(smForm.getNumberOfChildren()) ||
	          	  (msEvent.getMarriageDate() != null && !DateUtil.dateToString(msEvent.getMarriageDate(), DateUtil.DATE_FMT_1).equalsIgnoreCase(smForm.getMarriageDateStr() ) ) || 
	        	  (msEvent.getDivorceDate()!= null && !DateUtil.dateToString(msEvent.getDivorceDate(), DateUtil.DATE_FMT_1).equalsIgnoreCase(smForm.getDivorceDateStr()) ) ) )
	          	{
	        	  updateMaritalStatus = true;
	          	}
    	  } else {
    		  updateMaritalStatus = true;
    	  }	  
    	  if (updateMaritalStatus)
    	  {	  
				SaveFamilyMemberMaritalStatusEvent sfmmEvent = new SaveFamilyMemberMaritalStatusEvent();
				sfmmEvent.setEntryDate( DateUtil.getCurrentDate());
				sfmmEvent.setMaritalStatusId( smForm.getMaritalStatusId() );
				sfmmEvent.setMarriageDate( DateUtil.stringToDate( smForm.getMarriageDateStr(), UIConstants.DATE_FMT_1 ) );
				sfmmEvent.setDivorceDate( DateUtil.stringToDate( smForm.getDivorceDateStr(), UIConstants.DATE_FMT_1 ) );
				sfmmEvent.setNoOfChildren( smForm.getNumberOfChildren() );
				
				if( (smForm.getRelationshipWithId() != null && !smForm.getRelationshipWithId().isEmpty() ) && 
					!(smForm.getRelationshipWithId().equals( PDCodeTableConstants.MARITAL_STATUS_SINGLE )) )
				{
					sfmmEvent.setRelatedFamMemId( smForm.getRelationshipWithId() );
				}
				sfmEvent.addRequest(sfmmEvent);
		 }
      } */
      
      MessageUtil.postRequest(sfmEvent);

  
      UIJuvenileHelper.sendOutJPONotificationForSuspiciousMemberUpdate(smForm.getMemberNumber(), smForm.getAssociatedJuvenilesListOrig());
      
      smForm.setConfirmationMsg("Family Member has been successfully updated and notices sent.");
      smForm.setSecondaryAction(UIConstants.CONFIRM);
      smForm.setReturnToSearch(UIConstants.YES);
      
      return aMapping.findForward(UIConstants.SUCCESS);
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward backToSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		SuspiciousMemberForm smForm = (SuspiciousMemberForm) aForm;
		smForm.setSelectedOrigMemberNum(UIConstants.EMPTY_STRING);  // need to fix this
		smForm.clearSearch();
		return aMapping.findForward(UIConstants.BACK_TO_SEARCH);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward backToSelect(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		return aMapping.findForward(UIConstants.BACK_TO_SELECT);
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
		return aMapping.findForward(UIConstants.CANCEL);
	}	
}