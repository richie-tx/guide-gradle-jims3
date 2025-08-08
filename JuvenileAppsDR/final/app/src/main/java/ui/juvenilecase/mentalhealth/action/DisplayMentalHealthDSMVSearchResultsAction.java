package ui.juvenilecase.mentalhealth.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.JuvenileDSM5CodesResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.mentalhealth.form.TestingSessionForm;

public class DisplayMentalHealthDSMVSearchResultsAction extends JIMSBaseAction 
{

	/**
	 * @roseuid 430630EA0355
	 */
	public DisplayMentalHealthDSMVSearchResultsAction()
	{

	}

	/**
	 * @return Map
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.findDiagnosis", "findDiagnosis");
		keyMap.put("button.refresh", "refresh");		
		keyMap.put("button.select", "select");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 430628E50216
	 */
	public ActionForward findDiagnosis(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{	
		TestingSessionForm dsmvForm = (TestingSessionForm) aForm;
		
		//Added to make it not case sensitive
		String dsmvCode = dsmvForm.getSearchDSMV().getDsmCode().trim().toUpperCase();
		String dsmvCodeDesc = dsmvForm.getSearchDSMV().getDsmCodeDescription().trim().toUpperCase();

		String [] codeSearchValues ;		
		String [] codeDescSearchValues ;
		boolean codePartialSearch = false;
		boolean descPartialSearch = false;
		if(dsmvCode.indexOf("*") != -1){
			//if user entered * in code, splitting to array
			codeSearchValues = dsmvCode.split("\\*");
			//codePartialSearch = true;
		}else{
			codeSearchValues = new String[1];
			codeSearchValues[0] = dsmvCode;
		}
		if(dsmvCodeDesc.indexOf("*") != -1){
			//if user entered * in code desc, splitting to array
			codeDescSearchValues = dsmvCodeDesc.split("\\*");
			//descPartialSearch = true;
		}else{
			codeDescSearchValues = new String[1];
			codeDescSearchValues[0] = dsmvCodeDesc;
		}
		//changed for US #40636
		//List allCodes = CodeHelper.getCodes("DSM_V_CODES", true);
		List allCodes = ComplexCodeTableHelper.getJuvenileDSM5Codes();
		boolean matched = false;		
		Collection dsmvCodesColl = new ArrayList();
		if((dsmvCode != null && dsmvCode.length()>0)){
			if((dsmvCodeDesc != null && dsmvCodeDesc.length()>0)){
				//both code and description search
				dsmvCodesColl = getMatchingCodeAndDescValues(allCodes, codeSearchValues, codeDescSearchValues);
			}else{
				//only code is entered
				dsmvCodesColl = getMatchingCodeOrDescValues(allCodes, codeSearchValues, "Code");
			}
		}else if((dsmvCodeDesc != null && dsmvCodeDesc.length()>0)){
			// only description is entered
			dsmvCodesColl = getMatchingCodeOrDescValues(allCodes, codeDescSearchValues, "Description");
		}
				
		dsmvForm.setDsmVcodes(dsmvCodesColl);
		
		
		if(dsmvForm.getDsmVcodes().size()==0)
			sendToErrorPage(aRequest, "error.noRecords");
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public Collection getActiveCodes(Collection allCodes){
	    Collection activeCodes = new ArrayList();
	    
	    if(allCodes != null){

		Iterator allCodesItr = allCodes.iterator();
		while(allCodesItr.hasNext()) {
		    JuvenileDSM5CodesResponseEvent cre = (JuvenileDSM5CodesResponseEvent)allCodesItr.next();
		    
		    if(cre != null && cre.getStatus() != null && !cre.getStatus().equals("INACTIVE")){
			
			activeCodes.add(cre);
		    }
		}
	    }
	    
	    return activeCodes;
	}
	
	
	public Collection getMatchingCodeOrDescValues(List allCodes, String [] searchValues, String sSearchType){
		Collection coll = new ArrayList();
		boolean matched = false;
		if(allCodes != null){

			Iterator allCodesItr = allCodes.iterator();
			while(allCodesItr.hasNext()) {
				JuvenileDSM5CodesResponseEvent cre = (JuvenileDSM5CodesResponseEvent)allCodesItr.next();
				String eachCode = (String)cre.getCode().toUpperCase();
				String eachDesc = (String)cre.getDescription().toUpperCase();				
				if(sSearchType.equals("Code")){					
					
						for(int i= 0; i<searchValues.length;i++){
							String aSearchCodeItem = searchValues[i];
							int aSearchItemLength = aSearchCodeItem.length();
							if(i == 0 && aSearchItemLength > 0){
								//first element is not empty means no * in front, so need to find all items beginning with first char string
								if(!eachCode.startsWith(aSearchCodeItem)){
									matched = false;									
									break;
								}else{
									matched = true;
									//substring the code for further search
									eachCode = eachCode.substring(aSearchItemLength, eachCode.length());
								}
							}else{
								int index = eachCode.indexOf(aSearchCodeItem);
								//int aSearchItemLength = aSearchCodeItem.length();
								if(index == -1){
									matched = false;
									break;
								}else{
									matched = true;
									//substring the code for further search
									eachCode = eachCode.substring((index+aSearchItemLength), eachCode.length());							
								}
							}
						}			
				}
				else if(sSearchType.equals("Description")){	
					//if(partialSearch){
						//iterate and match description value entered
						for(int i= 0; i<searchValues.length;i++){
							String aSearchDescItem = searchValues[i];
							int aSearchItemLength = aSearchDescItem.length();
							if(i == 0 && aSearchItemLength > 0){	
								//first element is not empty means no * in front, so need to find all items beginning with first char string
								if(!eachDesc.startsWith(aSearchDescItem)){
									matched = false;									
									break;
								}else{
									matched = true;
									//substring the code for further search
									eachDesc = eachDesc.substring(aSearchItemLength, eachDesc.length());
								}
							}else{								
								int index = eachDesc.indexOf(aSearchDescItem);								
								if(index == -1){
									matched = false;
									break;
								}else{
									matched = true;
									eachDesc = eachDesc.substring((index+aSearchItemLength), eachDesc.length());							
								}
							}
						}
				}				
				if(matched){
					coll.add(cre);
				}												
			}//end of while		
		}
		return coll;
	}
	
	public Collection getMatchingCodeAndDescValues(List allCodes, String [] codeSearchValues, String [] descSearchValues){
		Collection coll = new ArrayList();
		boolean matched = false;
			
		if(allCodes != null){			
			Iterator allCodesItr = allCodes.iterator();
			while(allCodesItr.hasNext()) {
				JuvenileDSM5CodesResponseEvent cre = (JuvenileDSM5CodesResponseEvent)allCodesItr.next();
				String eachCode = (String)cre.getCode().toUpperCase();
				String eachDesc = (String)cre.getDescription().toUpperCase();				
				
				//if(codePartialSearch){
					//iterate and match code value entered
					for(int i= 0; i<codeSearchValues.length;i++){
						String aSearchCodeItem = codeSearchValues[i];
						int aSearchItemLength = aSearchCodeItem.length();						
						if(i == 0 && aSearchItemLength > 0){
							//first element is not empty means no * in front, so need to find all items beginning with first char string
							if(!eachCode.startsWith(aSearchCodeItem)){
								matched = false;									
								break;
							}else{
								matched = true;
								//substring the code for further search
								eachCode = eachCode.substring(aSearchItemLength, eachCode.length());
							}
						}else{
							int index = eachCode.indexOf(aSearchCodeItem);						
							//int aSearchItemLength = aSearchCodeItem.length();
							if(index == -1){
								matched = false;
								break;
							}else{
								matched = true;
								//substring the code for further search
								eachCode = eachCode.substring((index+aSearchItemLength), eachCode.length());							
							}
						}
						
					}			
				if(matched){
					//if(descPartialSearch){
						//iterate and match description value entered
						for(int i= 0; i<descSearchValues.length;i++){
							String aSearchDescItem = descSearchValues[i];
							int aSearchItemLength = aSearchDescItem.length();
															
							if(i == 0 && aSearchItemLength > 0){	
								//first element is not empty means no * in front, so need to find all items beginning with first char string
								if(!eachDesc.startsWith(aSearchDescItem)){
									matched = false;									
									break;
								}else{
									matched = true;
									//substring the code for further search
									eachDesc = eachDesc.substring(aSearchItemLength, eachDesc.length());
								}
							}else{
								int index = eachDesc.indexOf(aSearchDescItem);
								//int aSearchItemLength = aSearchDescItem.length();
								if(index == -1){
									matched = false;
									break;
								}else{
									matched = true;
									eachDesc = eachDesc.substring((index+aSearchItemLength), eachDesc.length());							
								}
							}														
						}
				}
				if(matched){
					coll.add(cre);
				}												
			}//end of while
		}
		return coll;
	}
	
	
	
	public ActionForward select(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			ActionForward forward = aMapping.findForward(UIConstants.SELECT_SUCCESS);			
			TestingSessionForm dsmvForm = (TestingSessionForm)aForm;
			
			String selectedDSMCdDesc = dsmvForm.getSelectedDsmCode();
			String [] dsmvCodeDescValues = new String[2];
			if(selectedDSMCdDesc != null && selectedDSMCdDesc.indexOf("|") != -1){
				dsmvCodeDescValues = selectedDSMCdDesc.split("\\|");

				if(dsmvCodeDescValues[0] == null){
					dsmvCodeDescValues[0] = "";
				}
				if(dsmvCodeDescValues[1] == null){
					dsmvCodeDescValues[1] = "";
				}
			}
			
			String diagnosisField = dsmvForm.getDiagnosisField();
			if(diagnosisField.equals("axisIPrimaryScoreDesc")){
				
				dsmvForm.getDsmRec().setAxisIPrimaryScoreDesc(dsmvCodeDescValues[1]);
				dsmvForm.getDsmRec().setAxisIPrimaryScore(dsmvCodeDescValues[0]);
				dsmvForm.setActionType("validateCode1");
			}
			if(diagnosisField.equals("axisISecondaryScoreDesc")){
				
				dsmvForm.getDsmRec().setAxisISecondaryScoreDesc(dsmvCodeDescValues[1]);
				dsmvForm.getDsmRec().setAxisISecondaryScore(dsmvCodeDescValues[0]);
				dsmvForm.setActionType("validateCode2");
			}
			if(diagnosisField.equals("axisITertiaryScoreDesc")){
				
				dsmvForm.getDsmRec().setAxisITertiaryScoreDesc(dsmvCodeDescValues[1]);
				dsmvForm.getDsmRec().setAxisITertiaryScore(dsmvCodeDescValues[0]);
				dsmvForm.setActionType("validateCode3");
			}
			if(diagnosisField.equals("axisIFourthDesc")){
				
				dsmvForm.getDsmRec().setAxisIFourthDesc(dsmvCodeDescValues[1]);
				dsmvForm.getDsmRec().setAxisIFourth(dsmvCodeDescValues[0]);
				dsmvForm.setActionType("validateCode4");
			}
			if(diagnosisField.equals("axisIFifthDesc")){
				
				dsmvForm.getDsmRec().setAxisIFifthDesc(dsmvCodeDescValues[1]);
				dsmvForm.getDsmRec().setAxisIFifth(dsmvCodeDescValues[0]);
				dsmvForm.setActionType("validateCode5");
			}
			if(diagnosisField.equals("axisIIPrimaryScoreDesc")){
	
				dsmvForm.getDsmRec().setAxisIIPrimaryScoreDesc(dsmvCodeDescValues[1]);
				dsmvForm.getDsmRec().setAxisIIPrimaryScore(dsmvCodeDescValues[0]);
				dsmvForm.setActionType("validateCode6");
			}
			if(diagnosisField.equals("axisIISecondaryScoreDesc")){
				
				dsmvForm.getDsmRec().setAxisIISecondaryScoreDesc(dsmvCodeDescValues[1]);
				dsmvForm.getDsmRec().setAxisIISecondaryScore(dsmvCodeDescValues[0]);
				dsmvForm.setActionType("validateCode7");
			}
			if(diagnosisField.equals("axisIIIPrimaryScoreDesc")){
				
				dsmvForm.getDsmRec().setAxisIIIPrimaryScoreDesc(dsmvCodeDescValues[1]);
				dsmvForm.getDsmRec().setAxisIIIPrimaryScore(dsmvCodeDescValues[0]);
				dsmvForm.setActionType("validateCode8");
			}
			if(diagnosisField.equals("axisIIISecondaryScoreDesc")){
				
				dsmvForm.getDsmRec().setAxisIIISecondaryScoreDesc(dsmvCodeDescValues[1]);
				dsmvForm.getDsmRec().setAxisIIISecondaryScore(dsmvCodeDescValues[0]);
				dsmvForm.setActionType("validateCode9");
			}
			if(diagnosisField.equals("diagnosis10Desc")){
				
				dsmvForm.getDsmRec().setDiagnosis10Desc(dsmvCodeDescValues[1]);
				dsmvForm.getDsmRec().setDiagnosis10(dsmvCodeDescValues[0]);
				dsmvForm.setActionType("validateCode10");
			}
			
			return forward;
		}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward refresh(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		TestingSessionForm testSessForm = (TestingSessionForm) aForm;
		testSessForm.getSearchDSMV().clearSearchCriteria();		
		testSessForm.setDsmVcodes(new ArrayList());
		return aMapping.findForward(UIConstants.SUCCESS);
	}	
}
