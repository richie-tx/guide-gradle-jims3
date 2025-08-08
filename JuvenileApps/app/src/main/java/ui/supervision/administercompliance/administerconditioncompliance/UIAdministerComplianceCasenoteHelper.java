/*
 * Created on Jan 2, 2008
 *
 */
package ui.supervision.administercompliance.administerconditioncompliance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import messaging.administercasenotes.GetCasenotesEvent;
import messaging.administercasenotes.domintf.ICasenote;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import messaging.manageassociate.reply.AssociateResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CasenoteControllerServiceNames;
import naming.UIConstants;
import ui.common.UIUtil;
import ui.supervision.Casenote;
import ui.supervision.administercasenotes.UICasenoteHelper;
import ui.supervision.administercasenotes.form.CasenoteSearchForm;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceForm;
import ui.supervision.manageassociate.UIManageAssociateHelper;

/**
 * @author cshimek
 * 
 */
public class UIAdministerComplianceCasenoteHelper {

    /**
     * @param form
     */ 	
	public static void advancedSearch(ComplianceForm compForm) {
        compForm.clearCasenoteSearch();
        compForm.setDisplayAction(UIConstants.ADVANCED);
    }

    /**
     * @param form
     */ 
	public static void basicSearch(ComplianceForm compForm) {
        compForm.clearCasenoteSearch();
        compForm.setDisplayAction(UIConstants.BASIC);
    }	

    /**
     * @param form
     */    
//  casenoteSearch changed to static to remove compile error, may need to be changed	    
    public static void casenoteSearch(ComplianceForm compForm) {
		GetCasenotesEvent request =
			(GetCasenotesEvent)EventFactory.getInstance(CasenoteControllerServiceNames.GETCASENOTES);
		request.setSearchBy(UIUtil.blankToNull(compForm.getSearchById()));
		request.setAll(false);
		request.setAssociateIds(UIUtil.convertStringArr2Coll(compForm.getSearchAssociateIds()));
		request.setBeginDate(UIAdministerComplianceHelper.convertToDateTime(compForm.getSearchBeginDateAsString(), "00:00"));
		request.setEndDate(UIAdministerComplianceHelper.convertToDateTime(compForm.getSearchEndDateAsString(), "00:00"));
		request.setCaseIds(UIUtil.convertStringArr2Coll(compForm.getSearchCaseNumberIds()));
		request.setCasenoteTypeId(UIUtil.blankToNull(compForm.getSearchCasenoteTypeId()));
		request.setCollateralId(UIUtil.blankToNull(compForm.getSearchCollateralId()));
		request.setCourtNum(UIUtil.blankToNull(compForm.getSearchCourt()));
		request.setHowGeneratedId(UIUtil.blankToNull(compForm.getSearchGeneratedById()));
		request.setSubjects(UIUtil.convertStringArr2Coll(compForm.getSubjectIds()));
		request.setSuperviseeId(compForm.getSuperviseeId());
        
		// request.setSupervisionPeriodId(compForm.getSupervisionPeriodId());
		// send to PD search criteria, evaluate search results and return to proper place
		CompositeResponse response = MessageUtil.postRequest(request);
	    // process results from lookup
	    Collection casenotes = MessageUtil.compositeToCollection(response, CasenoteResponseEvent.class);		
	    ArrayList matchingCasenotes = new ArrayList();
	    if (casenotes!=null && !casenotes.isEmpty()){
			CasenoteSearchForm searchForm = new CasenoteSearchForm();
			Collection casenoteSubjectList = searchForm.getCasenoteSubjectList();
			Collection casenoteTypeList = searchForm.getCasenoteTypeList();
			Collection collateralList = searchForm.getCollateralList();
			Collection contactMethodList = searchForm.getContactMethodList();
			String currentUserId = UIUtil.getCurrentUserID();

	    	Iterator iter = casenotes.iterator();
	    	while (iter.hasNext()){
	    		ICasenote casenote = (CasenoteResponseEvent)iter.next();
	    		//matchingCasenotes.add(UICasenoteHelper.getCasenote(casenote));
				Casenote newCasenote = UICasenoteHelper.getCasenote(casenote, currentUserId);
				newCasenote.setCasenoteTypeId(casenote.getCasenoteTypeId(), casenoteTypeList);
				String[] associatesArr = UICasenoteHelper.getArrayFromCollection(casenote.getAssociates());
				newCasenote.setAssociateIds(associatesArr, collateralList);
				String[] cnSubjs = UICasenoteHelper.getArrayFromCollection(casenote.getSubjects());
				newCasenote.setSubjectIds(cnSubjs, casenoteSubjectList);
				newCasenote.setAssociateIds(associatesArr, collateralList);
				newCasenote.setContactMethodId(casenote.getContactMethodId(), contactMethodList);
				matchingCasenotes.add(newCasenote);
	    	}
	    	UICasenoteHelper.resolveCreatorNames(matchingCasenotes);
	    	Collections.sort(matchingCasenotes);
	   	}     			
	    compForm.setCurrentCasenotes(matchingCasenotes);
    }

    /**
     * @param form
     */  
    public static void associateAddhandler(ComplianceForm compForm) {    
    	int size = 0;
    	if (compForm.getAssociatesList() != null)
    	{	
    		size = compForm.getAssociatesList().size();
    	}	
    	compForm.setAssociatesList(UIManageAssociateHelper.fetchAssociatesListSortedOnDisplayName(compForm.getSuperviseeId()));
    	if (compForm.getAssociatesList().size() > size)
    	{
    		compForm.setConfirmMessage("Associate successfully added.");
    	}
    }
    
    /**
     * @param form
     */  
    public static void associateNameRelationshipFormatter(ComplianceForm compForm) {    
		String[] collaterals = new String[compForm.getSelectedAssociateIds().length];
        StringBuffer aStr = new StringBuffer();
		for (int x =0; x < compForm.getSelectedAssociateIds().length; x++ )
		{
			Iterator areIter = compForm.getAssociatesList().iterator();
			while (areIter.hasNext())
			{
				AssociateResponseEvent are = (AssociateResponseEvent)areIter.next();
				if (are.getAssociateId() != null && are.getAssociateId().equalsIgnoreCase(compForm.getSelectedAssociateIds()[x]))
				{	
					String[] associateStr = are.getDisplayLabel().split("\\|");
					aStr.append(associateStr[0]);
					aStr.append(" Relationship:");
					aStr.append(associateStr[1]);
					aStr.append("<br>");
					break;
				}				
			}
		}
		compForm.setCollateral(aStr.toString());

    }
    /**
     * @param form
     */ 
	public static void resetInputs(ComplianceForm compForm) {
	    compForm.setContactMethodId("");
	    compForm.setSubjectIds(new String[0]);
	    compForm.setCollateralId("");
	    compForm.setCasenoteText("");
    }
}// END CLASS