/*
 * Created on Nov 5th, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.casefile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.casefile.reply.DSMDiagnosisResponseEvent;
import messaging.mentalhealth.GetDSMIVResultsEvent;
import messaging.mentalhealth.GetMentalHealthDSMTestDataEvent;
import messaging.mentalhealth.reply.DSMIVTestResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.UIConstants;
import ui.common.ComplexCodeTableHelper;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.casefile.form.CommonAppForm;
import ui.juvenilecase.casefile.form.CommonAppForm.Diagnosis;

/**
 * @author ugopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CasefileClosingHelper {
	
	
	public static void getDSMDiagnosisResults(CommonAppForm commonAppForm) {
		GetDSMIVResultsEvent listEvent = (GetDSMIVResultsEvent)EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETDSMIVRESULTS);
		listEvent.setJuvenileNum(commonAppForm.getJuvenileNum());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(listEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Collection coll = MessageUtil.compositeToCollection(response, DSMIVTestResponseEvent.class);
		Collections.sort((List<DSMIVTestResponseEvent>) coll);//ER 13222 changes
		Iterator iter = coll.iterator();	
		boolean found;
		Collection testResults = new ArrayList();
		while(iter.hasNext())
		{
			DSMIVTestResponseEvent resp = (DSMIVTestResponseEvent)iter.next();
			found=false;
			if(commonAppForm.isDsmDiagnosisExists())
			{
				Collection existingDiagnosis = commonAppForm.getDsmResults();
				Iterator existingIter = existingDiagnosis.iterator();
				while(existingIter.hasNext())
				{
					Diagnosis diag = (Diagnosis)existingIter.next();
					if(diag.getTestId().equals(resp.getTestId()))
					{
						diag.setDsmDiagnosisFound(true);
						testResults.add(diag);							
						found=true;
						break;
					}					
					
				}
				if(!found){
					if(!getDSMDiagnosis(resp).getDiagnosisResults().isEmpty())
						testResults.add(getDSMDiagnosis(resp));
				}
			}
			else{
				if(!getDSMDiagnosis(resp).getDiagnosisResults().isEmpty())
					testResults.add(getDSMDiagnosis(resp));
			}
			break;//ER-13222 changes ( consider only the most recent DSM records)
		}
		commonAppForm.setDsmResults(testResults);
   }
	
	/**
	 * If exit report not completed, get the first five dsm diagnosis if the test date is after august 1 2013 else show the old logic. 
	 * @param resp
	 * @return Diagnosis
	 */
	public static Diagnosis getDSMDiagnosis(DSMIVTestResponseEvent resp)
	{
	   	Collection<DSMDiagnosisResponseEvent> diagnosisEvents = new ArrayList<DSMDiagnosisResponseEvent>();
	   	
	   	Diagnosis result = new Diagnosis();
	   	DSMDiagnosisResponseEvent diagnosis = null; 
	   	
	   	GetMentalHealthDSMTestDataEvent dsmTestDataEvent = (GetMentalHealthDSMTestDataEvent)EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHDSMTESTDATA);
	   	dsmTestDataEvent.setTestId(resp.getTestId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(dsmTestDataEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		DSMIVTestResponseEvent detailedResp = (DSMIVTestResponseEvent) MessageUtil.filterComposite(response, DSMIVTestResponseEvent.class);
		if(detailedResp != null)
		{
			result.setTestId(resp.getTestId());
			diagnosis = new DSMDiagnosisResponseEvent();
			Date effectiveDate = DateUtil.stringToDate(UIConstants.DSM_EFFECTIVE_DATE, DateUtil.DATE_FMT_1);
			if(detailedResp.getTestDate().after(effectiveDate))
			{
				if(detailedResp.getAxis1PrimaryScore()!=null && !detailedResp.getAxis1PrimaryScore().isEmpty()){
					diagnosis.setDiagnosisCd(detailedResp.getAxis1PrimaryScore()); //diagnosis 1-ER 13222 changes
					addDiagnosisEvents(diagnosis,result,diagnosisEvents,true); //axis 1 primary
				}
				if(detailedResp.getAxis1SecondaryScore()!=null && !detailedResp.getAxis1SecondaryScore().isEmpty()){
					diagnosis.setDiagnosisCd(detailedResp.getAxis1SecondaryScore()); //diagnosis 2
					addDiagnosisEvents(diagnosis,result,diagnosisEvents,true); //axis 1 secondary
				}
				if(detailedResp.getAxis1TertiaryScore()!=null && !detailedResp.getAxis1TertiaryScore().isEmpty()){
					diagnosis.setDiagnosisCd(detailedResp.getAxis1TertiaryScore()); //diagnosis 3
					addDiagnosisEvents(diagnosis,result,diagnosisEvents,true); //axis 1 tertiary
				}
				if(detailedResp.getAxis1FourthScore()!=null && !detailedResp.getAxis1FourthScore().isEmpty()){
					diagnosis.setDiagnosisCd(detailedResp.getAxis1FourthScore()); //diagnosis 4
					addDiagnosisEvents(diagnosis,result,diagnosisEvents,true); //axis 1Fourth
				}
				if(detailedResp.getAxis1FifthScore()!=null && !detailedResp.getAxis1FifthScore().isEmpty()){
					diagnosis.setDiagnosisCd(detailedResp.getAxis1FifthScore()); //diagnosis 5
					addDiagnosisEvents(diagnosis,result,diagnosisEvents,true); //axis 1fifth
				}
				
				
			}else{ //prior to august 1 show axis 1,2,3,primary score & axis 5 field values. [exclude 'No diagnosis'] changes 13222
				if(detailedResp.getAxis1PrimaryScore()!=null && !detailedResp.getAxis1PrimaryScore().isEmpty()){
					diagnosis.setDiagnosisCd(detailedResp.getAxis1PrimaryScore()); //axis 1
					addDiagnosisEvents(diagnosis,result,diagnosisEvents,false); //axis 1 primary
				}
				if(detailedResp.getAxis2PrimaryScore()!=null && !detailedResp.getAxis2PrimaryScore().isEmpty()){
					diagnosis.setDiagnosisCd(detailedResp.getAxis2PrimaryScore()); //axis 2
					addDiagnosisEvents(diagnosis,result,diagnosisEvents,false); //axis 2 primary
				}
				if(detailedResp.getAxis3PrimaryScore()!=null && !detailedResp.getAxis3PrimaryScore().isEmpty()){
					diagnosis.setDiagnosisCd(detailedResp.getAxis3PrimaryScore()); //axis 3
					addDiagnosisEvents(diagnosis,result,diagnosisEvents,false); //axis 3 primary
				}
				if(detailedResp.getAxis5Score()!=null && !detailedResp.getAxis5Score().isEmpty()){
					diagnosis.setDiagnosisCd(detailedResp.getAxis5Score()+".000"); //axis 5
					addDiagnosisEvents(diagnosis,result,diagnosisEvents,false); //axis 5 primary
				}
			}
		}	
		result.setDiagnosisResults(diagnosisEvents);
		
		return result;
   }
	
	/**
	 * added for bug fix 23314
	 * add to diagnosis response event
	 * @param diagnosis
	 * @param result
	 * @param diagnosisEvents
	 * @param isFifthScore
	 */
	public static void addDiagnosisEvents(DSMDiagnosisResponseEvent diagnosis,Diagnosis result,Collection<DSMDiagnosisResponseEvent> diagnosisEvents,boolean isNewDSM){
		if(!diagnosis.getDiagnosisCd().equals("V71.090"))
		{
			String diagnosisDesc;
			String diagnosisCode =diagnosis.getDiagnosisCd();
			
			if(isNewDSM){
				//diagnosisDesc = SimpleCodeTableHelper.getDescrByCode("DSM_V_CODES",diagnosisCode);
				diagnosisDesc=ComplexCodeTableHelper.getJuvenileDSM5CodeDescription(diagnosisCode); //US #40636
			}else{
				diagnosisDesc = SimpleCodeTableHelper.getDescrByCode("DSM_CODES",diagnosisCode);
			}
			
			if(diagnosisDesc!=null && !diagnosisDesc.equals("")){
				diagnosis = new DSMDiagnosisResponseEvent(); // add unique diagnosis.
				diagnosis.setDiagnosis(diagnosisDesc);
				diagnosis.setDiagnosisCd(diagnosisCode) ;
				diagnosisEvents.add(diagnosis);
				result.setDsmDiagnosisFound(true);
			}
		}
	}
}
