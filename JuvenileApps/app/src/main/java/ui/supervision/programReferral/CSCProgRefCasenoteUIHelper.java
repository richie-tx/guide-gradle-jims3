package ui.supervision.programReferral;

import java.util.Collection;
import java.util.List;

import messaging.administerprogramreferrals.SaveProgRefCasenoteEvent;
import naming.CSAdministerProgramReferralsConstants;
import naming.PDCodeTableConstants;
import ui.supervision.programReferral.form.CSCProgRefForm;

/**
 * 
 * @author cc_bjangay
 *
 */
public class CSCProgRefCasenoteUIHelper
{
	public static SaveProgRefCasenoteEvent getSaveCasenoteEventForCasenoteCreate(CSCProgRefForm progRefForm)
 	{
 		SaveProgRefCasenoteEvent saveCasenoteEvent = new SaveProgRefCasenoteEvent();
 		
 		saveCasenoteEvent.setProgramReferralId(progRefForm.getProgRefId());
 		
 		saveCasenoteEvent.setCasenoteSubjectCd(PDCodeTableConstants.PROGRAM_REFERRAL_SUBJECT_CD);
 		saveCasenoteEvent.setCasenoteTypeId(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
 		saveCasenoteEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_NONE);
 		saveCasenoteEvent.setHowGeneratedId(PDCodeTableConstants.CASENOTE_CREATED_BY_ID);
 		
 		saveCasenoteEvent.setCasenoteContext(CSAdministerProgramReferralsConstants.CASENOTE_CONTEXT_PROGRAM_REFERAL);
 		
 		saveCasenoteEvent.setCasenoteComments(progRefForm.getCasenoteComments());
 		
 		return saveCasenoteEvent;
 	}
 	
 	
 	public static SaveProgRefCasenoteEvent getSaveCasenoteEventForPrintPacket(CSCProgRefForm progRefForm)
 	{
 		SaveProgRefCasenoteEvent saveCasenoteEvent = new SaveProgRefCasenoteEvent();
 		
 		Collection progRefIdsList = progRefForm.getReferralTypeCdNPgmRefIdMap().values();
 		saveCasenoteEvent.setProgramReferralId((String)progRefIdsList.iterator().next());
 		
 		saveCasenoteEvent.setCasenoteSubjectCd(PDCodeTableConstants.PROGRAM_REFERRAL_SUBJECT_CD);
 		
 		saveCasenoteEvent.setCasenoteTypeId(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
 		saveCasenoteEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_INPERSON_OV);
 		saveCasenoteEvent.setHowGeneratedId(PDCodeTableConstants.CASENOTE_SYSTEM_GENERATED_ID);
 		
 		saveCasenoteEvent.setCasenoteContext(CSAdministerProgramReferralsConstants.CASENOTE_CONTEXT_SUPERVISEE);
 		
 		StringBuffer notes = new StringBuffer();
 		notes.append("A packet for the following referral type(s) has been provided to the supervisee: ");
 		List refTypesList = progRefForm.getSelectedReferralTypesList();
 		for(int index=0; index < refTypesList.size(); index++)
 		{
 			ReferralTypeBean refTypeBean = (ReferralTypeBean)refTypesList.get(index);
 			String refTypeDesc = refTypeBean.getReferralTypeDesc();
 			notes.append(refTypeDesc);
 			
 			if(index < refTypesList.size()-1)
 			{
 				notes.append(", ");
 			}
 		}
 		
 		saveCasenoteEvent.setCasenoteComments(notes.toString());
 		
 		return saveCasenoteEvent;
 	}
 	
 	
 	
 	public static SaveProgRefCasenoteEvent getSaveCasenoteEventForSubmitRef(CSCProgRefForm progRefForm)
 	{
 		SaveProgRefCasenoteEvent saveCasenoteEvent = new SaveProgRefCasenoteEvent();
 		
 		saveCasenoteEvent.setProgramReferralId(progRefForm.getProgRefId());
 		
 		saveCasenoteEvent.setCasenoteSubjectCd(PDCodeTableConstants.PROGRAM_REFERRAL_SUBJECT_CD);
 		
 		saveCasenoteEvent.setCasenoteTypeId(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
 		saveCasenoteEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_NONE);
 		saveCasenoteEvent.setHowGeneratedId(PDCodeTableConstants.CASENOTE_SYSTEM_GENERATED_ID);
 		
 		saveCasenoteEvent.setCasenoteContext(CSAdministerProgramReferralsConstants.CASENOTE_CONTEXT_PROGRAM_REFERAL);
 		
 		StringBuffer notes = new StringBuffer();
 		notes.append("Program Referral Submitted for Case#: ");
// 		notes.append(progRefForm.getCriminalCaseId()); // 01/07/10 added space between cdi and case per #63376
 		String cdiCaseNum = progRefForm.getCriminalCaseId();
		if (cdiCaseNum == null){
 			cdiCaseNum = "";
 		}	
 		if (cdiCaseNum.length() > 2){
 			cdiCaseNum = cdiCaseNum.substring(0, 3) + " " + cdiCaseNum.substring(3);
 		}

 		notes.append(cdiCaseNum);
 		CSCServiceProviderProgLocBean refProgLocBean = progRefForm.getReferralProgramLocBean();
 		
 		notes.append(", Referral Type: ");
 		notes.append(refProgLocBean.getReferralTypeDesc());
 		
 		notes.append(", Service Provider Name: ");
 		notes.append(refProgLocBean.getServiceProviderName());
 		
 		notes.append(", Program Name: ");
 		notes.append(refProgLocBean.getProgramName());
 		
 		notes.append(", Reason for Placement: ");
 		notes.append(progRefForm.getReasonForPlacementDesc());
 		
 		notes.append(", Begin Date: ");
 		notes.append(progRefForm.getProgramBeginDateAsStr());
 		
 		notes.append(", Comment: ");
 		notes.append(progRefForm.getSubmitComments());
 		
 		saveCasenoteEvent.setCasenoteComments(notes.toString());
 		
 		return saveCasenoteEvent;
 	}
 	
 	
 	
 	
 	public static SaveProgRefCasenoteEvent getSaveCasenoteEventForExitRef(CSCProgRefForm progRefForm)
 	{
 		SaveProgRefCasenoteEvent saveCasenoteEvent = new SaveProgRefCasenoteEvent();
 		
 		saveCasenoteEvent.setProgramReferralId(progRefForm.getProgRefId());
 		
 		saveCasenoteEvent.setCasenoteSubjectCd(PDCodeTableConstants.PROGRAM_REFERRAL_SUBJECT_CD);
 		
 		saveCasenoteEvent.setCasenoteTypeId(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
 		saveCasenoteEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_NONE);
 		saveCasenoteEvent.setHowGeneratedId(PDCodeTableConstants.CASENOTE_SYSTEM_GENERATED_ID);
 		
 		saveCasenoteEvent.setCasenoteContext(CSAdministerProgramReferralsConstants.CASENOTE_CONTEXT_PROGRAM_REFERAL);
 		
 		StringBuffer notes = new StringBuffer();
 		notes.append("Program Referral Exited for Case#: ");
 		String cdiCaseNum = progRefForm.getCriminalCaseId();
		if (cdiCaseNum == null){
 			cdiCaseNum = "";
 		}	
 		if (cdiCaseNum.length() > 2){
 			cdiCaseNum = cdiCaseNum.substring(0, 3) + " " + cdiCaseNum.substring(3);
 		}
 		notes.append(cdiCaseNum);
 		
 		CSCServiceProviderProgLocBean refProgLocBean = progRefForm.getReferralProgramLocBean();
 		
 		notes.append(", Referral Type: ");
 		notes.append(refProgLocBean.getReferralTypeDesc());
 		
 		notes.append(", Service Provider Name: ");
 		notes.append(refProgLocBean.getServiceProviderName());
 		
 		notes.append(", Program Name: ");
 		notes.append(refProgLocBean.getProgramName());
 		
 		notes.append(", Begin Date: ");
 		notes.append(progRefForm.getProgramBeginDateAsStr());
 		
 		notes.append(", Reason for Discharge: ");
 		notes.append(progRefForm.getReasonForDischargeDesc());
 		
 		notes.append(", End Date: ");
 		notes.append(progRefForm.getProgramEndDateAsStr());
 		
 		notes.append(", Comment: ");
 		notes.append(progRefForm.getExitComments());
 		
 		saveCasenoteEvent.setCasenoteComments(notes.toString());
 		
 		return saveCasenoteEvent;
 	}
 	
 	
 	
 	public static SaveProgRefCasenoteEvent getSaveCasenoteEventForRereferral(CSCProgRefForm progRefForm)
 	{
 		SaveProgRefCasenoteEvent saveCasenoteEvent = new SaveProgRefCasenoteEvent();
 		
 		saveCasenoteEvent.setProgramReferralId(progRefForm.getProgRefId());
 		
 		saveCasenoteEvent.setCasenoteSubjectCd(PDCodeTableConstants.PROGRAM_REFERRAL_SUBJECT_CD);
 		
 		saveCasenoteEvent.setCasenoteTypeId(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
 		saveCasenoteEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_NONE);
 		saveCasenoteEvent.setHowGeneratedId(PDCodeTableConstants.CASENOTE_SYSTEM_GENERATED_ID);
 		
 		saveCasenoteEvent.setCasenoteContext(CSAdministerProgramReferralsConstants.CASENOTE_CONTEXT_PROGRAM_REFERAL);
 		
 		StringBuffer notes = new StringBuffer();
 		notes.append("Program Re-referral for Case#: ");
 		String cdiCaseNum = progRefForm.getCriminalCaseId();
		if (cdiCaseNum == null){
 			cdiCaseNum = "";
 		}	
 		if (cdiCaseNum.length() > 2){
 			cdiCaseNum = cdiCaseNum.substring(0, 3) + " " + cdiCaseNum.substring(3);
 		}
 		notes.append(cdiCaseNum);
 		
 		CSCServiceProviderProgLocBean refProgLocBean = progRefForm.getReferralProgramLocBean();
 		
 		notes.append(", Referral Type: ");
 		notes.append(refProgLocBean.getReferralTypeDesc());
 		
 		notes.append(", Service Provider Name: ");
 		notes.append(refProgLocBean.getServiceProviderName());
 		
 		notes.append(", Program Name: ");
 		notes.append(refProgLocBean.getProgramName());
 		
 		notes.append(", Reason for Placement: ");
 		notes.append(progRefForm.getReasonForPlacementDesc());
 		
 		notes.append(", Begin Date: ");
 		notes.append(progRefForm.getProgramBeginDateAsStr());
 		
 		notes.append(", Comment: ");
 		notes.append(progRefForm.getSubmitComments());
 		
 		saveCasenoteEvent.setCasenoteComments(notes.toString());
 		
 		return saveCasenoteEvent;
 	}
 	
 	
 	
 	public static SaveProgRefCasenoteEvent getSaveCasenoteEventForGenerateForm(CSCProgRefForm progRefForm)
 	{
 		SaveProgRefCasenoteEvent saveCasenoteEvent = new SaveProgRefCasenoteEvent();
 		
 		saveCasenoteEvent.setProgramReferralId(progRefForm.getProgRefId());
 		
 		saveCasenoteEvent.setCasenoteSubjectCd(PDCodeTableConstants.PROGRAM_REFERRAL_SUBJECT_CD);
 		
 		saveCasenoteEvent.setCasenoteTypeId(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
 		saveCasenoteEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_INPERSON_OV);
 		saveCasenoteEvent.setHowGeneratedId(PDCodeTableConstants.CASENOTE_SYSTEM_GENERATED_ID);
 		
 		saveCasenoteEvent.setCasenoteContext(CSAdministerProgramReferralsConstants.CASENOTE_CONTEXT_PROGRAM_REFERAL);
 		
 		StringBuffer notes = new StringBuffer();
 		notes.append("A Referral Form for ");
 		
 		String refTypeDesc = "";
 		CSCServiceProviderProgLocBean refProgLocBean = progRefForm.getReferralProgramLocBean();
 		if(refProgLocBean!=null)
 		{
 			refTypeDesc = refProgLocBean.getReferralTypeDesc();
 		}
 		else
 		{
 			refTypeDesc = progRefForm.getReferralTypeDesc();
 		}
 		
 		notes.append(refTypeDesc);
 		notes.append(" has been provided to the supervisee. Comment: ");
 		
// 		retrieve the referral form comments
 		List referFormFieldsList = progRefForm.getReferralformFieldsList();
 		for(int index=referFormFieldsList.size()-1; index>=0; index--)
 		{
 			ReferralFormField formField = (ReferralFormField)referFormFieldsList.get(index);
 			if((formField.getFieldLabel().trim().equalsIgnoreCase(CSAdministerProgramReferralsConstants.REFERRAL_FORM_COMMENT_LABEL)) ||
 			(formField.getFieldLabel().trim().equalsIgnoreCase(CSAdministerProgramReferralsConstants.SEX_OFFENDER_REF_FORM_COMMENT_LABEL)))
 			{
 				notes.append(formField.getResponseText());
 			}
 		}
 		
 		notes.append(". Form Title: ");
 		notes.append(progRefForm.getReferralFormName());
 		
 		saveCasenoteEvent.setCasenoteComments(notes.toString());
 		
 		return saveCasenoteEvent;
 	}
 	
 	
 	
 	public static SaveProgRefCasenoteEvent getSaveCasenoteEventForRemoveEntry(CSCProgRefForm progRefForm)
 	{
 		SaveProgRefCasenoteEvent saveCasenoteEvent = new SaveProgRefCasenoteEvent();
 		
 		saveCasenoteEvent.setProgramReferralId(progRefForm.getProgRefId());
 		
 		saveCasenoteEvent.setCasenoteSubjectCd(PDCodeTableConstants.PROGRAM_REFERRAL_SUBJECT_CD);
 		
 		saveCasenoteEvent.setCasenoteTypeId(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
 		saveCasenoteEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_NONE);
 		saveCasenoteEvent.setHowGeneratedId(PDCodeTableConstants.CASENOTE_SYSTEM_GENERATED_ID);
 		
 		saveCasenoteEvent.setCasenoteContext(CSAdministerProgramReferralsConstants.CASENOTE_CONTEXT_PROGRAM_REFERAL);
 		
 		StringBuffer notes = new StringBuffer();
 		notes.append("Program Referral Entry Removed for Case#: ");
 		String cdiCaseNum = progRefForm.getCriminalCaseId();
		if (cdiCaseNum == null){
 			cdiCaseNum = "";
 		}	
 		if (cdiCaseNum.length() > 2){
 			cdiCaseNum = cdiCaseNum.substring(0, 3) + " " + cdiCaseNum.substring(3);
 		}
 		notes.append(cdiCaseNum);
 		
 		CSCServiceProviderProgLocBean refProgLocBean = progRefForm.getReferralProgramLocBean();
 		
 		notes.append(", Referral Type: ");
 		notes.append(refProgLocBean.getReferralTypeDesc());
 		
 		notes.append(", Service Provider Name: ");
 		notes.append(refProgLocBean.getServiceProviderName());
 		
 		notes.append(", Program Name: ");
 		notes.append(refProgLocBean.getProgramName());
 		
 		saveCasenoteEvent.setCasenoteComments(notes.toString());
 		return saveCasenoteEvent;
 	}
 	
 	
 	
 	public static SaveProgRefCasenoteEvent getSaveCasenoteEventForRemoveExit(CSCProgRefForm progRefForm)
 	{
 		SaveProgRefCasenoteEvent saveCasenoteEvent = new SaveProgRefCasenoteEvent();
 		
 		saveCasenoteEvent.setProgramReferralId(progRefForm.getProgRefId());
 		
 		saveCasenoteEvent.setCasenoteSubjectCd(PDCodeTableConstants.PROGRAM_REFERRAL_SUBJECT_CD);
 		
 		saveCasenoteEvent.setCasenoteTypeId(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
 		saveCasenoteEvent.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_NONE);
 		saveCasenoteEvent.setHowGeneratedId(PDCodeTableConstants.CASENOTE_SYSTEM_GENERATED_ID);
 		
 		saveCasenoteEvent.setCasenoteContext(CSAdministerProgramReferralsConstants.CASENOTE_CONTEXT_PROGRAM_REFERAL);
 		
 		StringBuffer notes = new StringBuffer();
 		notes.append("Program Referral Exit Removed for Case#: ");

 		String cdiCaseNum = progRefForm.getCriminalCaseId();
		if (cdiCaseNum == null){
 			cdiCaseNum = "";
 		}	
 		if (cdiCaseNum.length() > 2){
 			cdiCaseNum = cdiCaseNum.substring(0, 3) + " " + cdiCaseNum.substring(3);
 		}
 		notes.append(cdiCaseNum);
 		
 		CSCServiceProviderProgLocBean refProgLocBean = progRefForm.getReferralProgramLocBean();
 		
 		notes.append(", Referral Type: ");
 		notes.append(refProgLocBean.getReferralTypeDesc());
 		
 		notes.append(", Service Provider Name: ");
 		notes.append(refProgLocBean.getServiceProviderName());
 		
 		notes.append(", Program Name: ");
 		notes.append(refProgLocBean.getProgramName());
 		
 		saveCasenoteEvent.setCasenoteComments(notes.toString());
 		
 		return saveCasenoteEvent;
 	}
}
