package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.family.GetBenefitsAssessmentDetailEvent;
import messaging.family.GetBenefitsAssessmentGuardiansEvent;
import messaging.juvenilecase.reply.BenefitsAssessmentDetailResponseEvent;
import messaging.juvenilecase.reply.BenefitsAssessmentGuardianResponseEvent;
import messaging.juvenilecase.reply.IndividualIncomeDeterminationResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.ProcessBenefitsAssessmentForm;

public class DisplayBenefitsAssessmentViewAction extends Action
{
	
   public DisplayBenefitsAssessmentViewAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 433C3D3D02B3
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		ProcessBenefitsAssessmentForm form = (ProcessBenefitsAssessmentForm)aForm;
   		
   		
   		//This if condition exist due to the fact that interim task browser needs to link
   		//directly to the review page without going through the summary page.
		String review = aRequest.getParameter("review");
		
		if(review != null && review.length() > 0)
		{
			if(review.equals(Boolean.TRUE.toString()))
				form.setReview(true);
			else
				form.setReview(false);
					
			aRequest.setAttribute("pageType", "review");
			form.getCurrentAssessment().setComments("");
		}
		else
   			form.setReview(false);
   	
   		
		//a generic dispatcher for all commands.
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	
		//	get benefits assessment details, for ADFC income determination worksheet 
		GetBenefitsAssessmentDetailEvent event = new GetBenefitsAssessmentDetailEvent(); 
		event.setAssessmentId( form.getCurrentAssessment().getAssessmentId() );
		//event.setGuardianId( form.getCurrentAssessment().getGuardian().getMemberNumber() ); //this must be stored in current assessment
		dispatch.postEvent(event);
		Collection benes =	MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), BenefitsAssessmentDetailResponseEvent.class );
		
		ProcessBenefitsAssessmentForm.BenefitAssessment ben = form.getCurrentAssessment();
		//AssessmentId is unique, therefore, the size of the collection has to be 1
		if(benes != null && benes.size() == 1)
		{
			Iterator beneIter = benes.iterator();
			BenefitsAssessmentDetailResponseEvent beneDetail = (BenefitsAssessmentDetailResponseEvent)beneIter.next();
			
			//Updating Header Information (juvenile and guardian lists)		
			ProcessBenefitsAssessmentForm.BenefitAssessment currentAssessment = form.getCurrentAssessment();
			
			//get current assessment, for header display
		  	String selBenefitAssessmentId = currentAssessment.getAssessmentId();
		  	if(selBenefitAssessmentId != null && selBenefitAssessmentId.length() > 0)
		  	{
			  //create header information
			  for(Iterator iter=form.getRequestList().iterator();iter.hasNext();)
			  {
				  ProcessBenefitsAssessmentForm.BenefitAssessment assessmentInfo = 
					  (ProcessBenefitsAssessmentForm.BenefitAssessment)iter.next();
				  if(assessmentInfo.getAssessmentId().equals(selBenefitAssessmentId))
				  {
					  currentAssessment.setListOfGuardians(assessmentInfo.getListOfGuardians());
					  currentAssessment.setJuvNumber(assessmentInfo.getJuvNumber());
					  currentAssessment.setJuvName(assessmentInfo.getJuvName());
					  currentAssessment.setCasefileId(assessmentInfo.getCasefileId());
					  break;
				  }
			  }
		  	}
		  	
			//populate data from response event
			ben.setChildMeetAFDCRequirement(beneDetail.isChildMeetsAFDCAndOrderRequirements());
			ben.setCourtOrderIncludeFindingChildCareAndPlacement(beneDetail.isOrdersIncludeResponsibilityForCareAndPlacement());
			
			ProcessBenefitsAssessmentForm.Guardian g = new ProcessBenefitsAssessmentForm.Guardian();
			g.setName(new Name(beneDetail.getGuardianFirstName(), "", beneDetail.getGuardianLastName()));
			ben.setGuardian(g); 
			
			ben.setInitialOrderRemovalContainBestInterest(beneDetail.isOrderContainsBestInterestLanguage());
			ben.setJuvName(new Name(beneDetail.getFirstName(), "", beneDetail.getLastName()));
			ben.setJuvNumber(beneDetail.getJuvenileId());
			
			//create a casefileForm so that casefile tabs can be displayed in jsp
			JuvenileCasefileForm casefileForm=UIJuvenileHelper.getJuvenileCasefileForm(aRequest,true);
			casefileForm.populateJuvenileCasefileForm(beneDetail.getCasefileId()); // load and populate the casefile header
			casefileForm.setJuvenileNum(beneDetail.getJuvenileId());
			aRequest.getSession().setAttribute("juvenileCasefileForm", casefileForm);
			
			ProcessBenefitsAssessmentForm.BenefitAssessment.TitleIVEQuestion question = ben.getQuestion();
			
			//Question 1
			question.setLegalResident(beneDetail.isLegalResident());
			
			//Question 2
			question.setOneParentIsStepparent(beneDetail.isOneParentIsStepparent());
			question.setDeathOrAbsence(beneDetail.isDeathOrAbsence());
			question.setIncapacityOrDisabilityOfParent(beneDetail.isIncapacityOrDisabilityOfParent());
			
			question.setPrimaryWageEarnerUnderemployment(beneDetail.isPrimaryWageEarnerUnderemployment());
			question.setPweWorkedLessThen100Hours(beneDetail.isPweWorkedLessThen100Hours());
			question.setPweIncomeLessThanUnderemployedLimit(beneDetail.isPweIncomeLessThanUnderemployedLimit());

			question.setPweRelationshipToJuvenileId(beneDetail.getPweRelationshipToJuvenileId());
			question.setPweRelationshipToJuvenile(beneDetail.getPweRelationshipToJuvenile());
			question.setPweWorkedLessThen100Hours(beneDetail.isPweWorkedLessThen100Hours());
			question.setPweIncomeLessThanUnderemployedLimit(beneDetail.isPweIncomeLessThanUnderemployedLimit());
			question.setPweWorkedSteadyLessThan100Hours(beneDetail.isPweWorkedSteadyLessThan100Hours());
			question.setPweWorkedIrregularLessThan100HoursAvg(beneDetail.isPweWorkedIrregularLessThan100HoursAvg());
			question.setPweGrossMonthlyIncomeForOver100Hours(beneDetail.getPweGrossMonthlyIncomeForOver100Hours()/100);
			
			//Question 3
			question.setWasChildLivingWithParent(beneDetail.isWasChildLivingWithParent());
			
			//Question 4
			question.setAfdcIncomeLimitsMet(beneDetail.isAfdcIncomeLimitsMet());
			question.setAfdcIncomeCertifiedGroupSize(beneDetail.getAfdcIncomeCertifiedGroupSize());
			question.setAfdcIncomeCertifiedGroupParentsSize(beneDetail.getAfdcIncomeCertifiedGroupParentsSize());
			question.setAfdcIncomeCertifiedGroupLimit(beneDetail.getAfdcIncomeCertifiedGroupLimit()/100);
			
			question.setAfdcIncomeStepparentsMonthlyGross(beneDetail.getAfdcIncomeStepparentsMonthlyGross()/100);
			question.setAfdcIncomeStepparentWorkRelatedExpenses(beneDetail.getAfdcIncomeStepparentWorkRelatedExpenses()/100);
			question.setAfdcIncomeStepparentCountableEarnedMonthy(beneDetail.getAfdcIncomeStepparentCountableEarnedMonthy()/100);
			question.setAfdcIncomeStepparentOtherMonthlyIncome(beneDetail.getAfdcIncomeStepparentOtherMonthlyIncome()/100);
			question.setAfdcIncomeStepparentTotalCountableMonthy(beneDetail.getAfdcIncomeStepparentTotalCountableMonthy()/100);
			question.setAfdcIncomeStepparentMonthyPaymentsToDependent(beneDetail.getAfdcIncomeStepparentMonthyPaymentsToDependent()/100);
			question.setAfdcIncomeStepparentMonthyAlimonyChildSupport(beneDetail.getAfdcIncomeStepparentMonthyAlimonyChildSupport()/100);
			question.setAfdcIncomeStepparentNoncertifiedCount(beneDetail.getAfdcIncomeStepparentNoncertifiedCount());
			question.setAfdcIncomeStepparentAllowanceAmount(beneDetail.getAfdcIncomeStepparentAllowanceAmount()/100);
			question.setAfdcIncomeStepparentAppliedIncome(beneDetail.getAfdcIncomeStepparentAppliedIncome()/100);
			question.setAfdcIncomeTotalMonthy(beneDetail.getAfdcIncomeTotalMonthy()/100);
			question.setAfdcIncomeTotalCountable(beneDetail.getAfdcIncomeTotalCountable()/100);
			
			//Question 5
			question.setUnder10KLimit(beneDetail.isUnder10KLimit());
			
			//Question 6
			question.setChildMeetsEligibilityCriteria(beneDetail.isChildMeetsEligibilityCriteria());
			
			
			//resolve info source	  	
			Collection infoSources = beneDetail.getSourcesForAFDCInformation();
			StringBuffer sb = new StringBuffer();
		
			for(Iterator iter = infoSources.iterator(); iter.hasNext();)
			{
				String sourceInfoId = (String)iter.next();
				
				//translate Id to description
				sb.append(CodeHelper.getCodeDescriptionByCode(form.getBenefitsAssessmentInfoSourceCodeTable(), sourceInfoId));
				if(iter.hasNext())
					sb.append(", ");
			}
			
			question.setInfoSource(sb.toString());
			
			
			//question.setPweName(beneDetail.getGuardianLastName()+", "+beneDetail.getGuardianFirstName());
			
			question.setDateTaken(beneDetail.getEntryDate());
			
			ben.setEligibleForMedicaid(beneDetail.isEligibleForMedicaid());
			ben.setEligibleForTitleIVe(beneDetail.isEligibleForTitleIVe());
			ben.setReceivingMedicaid(beneDetail.isReceivingMedicaid());
			ben.setReceivingTitleIVe(beneDetail.isReceivingTitleIVe());

			//Set data from Response Event to Custom UI Object
			Collection incomeWorksheetItemsRE = beneDetail.getAfdcIncomeWorksheetItems();
			Collection incomeWorksheetItems = new ArrayList();
			
			if(incomeWorksheetItemsRE != null && incomeWorksheetItemsRE.size() > 0)
			{

				for(Iterator iter = incomeWorksheetItemsRE.iterator(); iter.hasNext();)
				{
					IndividualIncomeDeterminationResponseEvent iidRE = 
						(IndividualIncomeDeterminationResponseEvent)iter.next();
					ProcessBenefitsAssessmentForm.IndividualIncomeDetermination iid = new ProcessBenefitsAssessmentForm.IndividualIncomeDetermination();
					iid.setJuvenileNumber(beneDetail.getJuvenileId());
					iid.setMemberId(iidRE.getMemberId());
					iid.setName(iidRE.getName());
					iid.setRelationshipToJuvenileId(iidRE.getRelationshipToJuvenileId());
					iid.setGrossMonthyIncome(iidRE.getGrossMonthyIncome());
					iid.setIncomeSourceId(iidRE.getIncomeSourceId());
					iid.setComments(iidRE.getComments());
					iid.setAge(iidRE.getAge());
					incomeWorksheetItems.add(iid);
				}
				Collections.sort((List)incomeWorksheetItems);
				ben.getQuestion().setAfdcIncomeWorksheetItems(incomeWorksheetItems);
			}
					
			form.setTitleIVeOfficerId(beneDetail.getTitleIVeOfficerId());
			
			if(beneDetail.getTitleIVeOfficerName() != null)
			{
			
				StringTokenizer strtok = new StringTokenizer(beneDetail.getTitleIVeOfficerName(), ", ");
				Name officerName = new Name();
				
				int sequenceCount = 0;
				
				final int LAST_NAME = 0;
				final int FIRST_NAME = 1;
				final int MIDDLE_NAME = 2;
				 
				while(strtok.hasMoreTokens())
				{
					if(sequenceCount == LAST_NAME)
					{
						officerName.setLastName(strtok.nextToken());
					}
					else if(sequenceCount == FIRST_NAME)
					{
						officerName.setFirstName(strtok.nextToken());
					}
					else
					{
						officerName.setMiddleName(strtok.nextToken());
					}
					
					sequenceCount++;
					
				}
				
				form.setTitleIVeOfficerName(officerName);
			}
			
			//Get list of Juvenile's Guardian(s) to be displayed in header
			GetBenefitsAssessmentGuardiansEvent request = new GetBenefitsAssessmentGuardiansEvent();
			request.setJuvenileId(form.getCurrentAssessment().getJuvNumber());
			dispatch.postEvent(request);
			Collection juvenileGuardiansRE = MessageUtil.compositeToCollection( (CompositeResponse)dispatch.getReply(), BenefitsAssessmentGuardianResponseEvent.class );
			
			Collection guardiansInfo = new ArrayList();		
			
			ProcessBenefitsAssessmentForm.Guardian currentGuardian = new ProcessBenefitsAssessmentForm.Guardian();
			currentGuardian.setName(new Name(beneDetail.getGuardianFirstName(),"",beneDetail.getGuardianLastName()));
			currentGuardian.setMemberNumber(beneDetail.getGuardianFamilyMemberId());
						
			for(Iterator guardianIter = juvenileGuardiansRE.iterator(); guardianIter.hasNext();)
			{
				BenefitsAssessmentGuardianResponseEvent guardian = 
					(BenefitsAssessmentGuardianResponseEvent) guardianIter.next();
	
				ProcessBenefitsAssessmentForm.Guardian guardianInfo = 
					new ProcessBenefitsAssessmentForm.Guardian();
				guardianInfo.setName(new Name(guardian.getFirstName(), "", guardian.getLastName()));
				guardianInfo.setPhoneNumber(guardian.getPhoneNumber());
				guardianInfo.setPhoneType(guardian.getPhoneType());
				guardianInfo.setMemberNumber(guardian.getMemberId());
				guardianInfo.setConstellationMemberId(guardian.getConstellationMemberId());
				guardianInfo.setRelationship(guardian.getRelationshipToJuvenile());
				guardiansInfo.add(guardianInfo); //add to list
				
				if(guardianInfo.getName().getFormattedName().equals(currentGuardian.getName().getFormattedName()))
				{
					currentGuardian.setRelationship(guardianInfo.getRelationship());
				}
			}
			
			currentAssessment.setGuardian(currentGuardian);

			form.setEmploymentInfoList(null);
			String selGuardianId = form.getCurrentAssessment().getGuardian().getMemberNumber();
			if(selGuardianId!=null && !selGuardianId.equals("")){
				JuvenileFamilyForm.Guardian guard=new JuvenileFamilyForm.Guardian();
				UIJuvenileFamilyHelper.getEmploymentMemberInfo(selGuardianId,guard);
				form.setEmploymentInfoList(guard.getEmploymentInfoList());
			}
			
			form.getCurrentAssessment().setListOfGuardians(guardiansInfo);
		}
		
		//set beneCalc to null, to signify that values would be from response event.
		form.getCurrentAssessment().getQuestion().setBeneCalc(null);


		form.setView(true);
		
		return aMapping.findForward(UIConstants.VIEW_SUCCESS);
	}
	
	
}